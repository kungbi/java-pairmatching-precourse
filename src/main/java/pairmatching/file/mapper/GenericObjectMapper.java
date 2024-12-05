package pairmatching.file.mapper;

import java.lang.reflect.Constructor;
import java.util.List;

public class GenericObjectMapper<T> {

    private final Class<T> targetClass;

    public GenericObjectMapper(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public T map(List<String> fields) {
        try {
            // 가장 적합한 생성자를 가져옵니다.
            Constructor<?>[] constructors = targetClass.getDeclaredConstructors();
            Constructor<?> constructor = findConstructorWithMatchingParameterCount(constructors, fields.size());

            // 생성자 파라미터 타입 확인
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = convertValue(parameterTypes[i], fields.get(i));
            }

            // 인스턴스 생성
            @SuppressWarnings("unchecked")
            T instance = (T) constructor.newInstance(args);

            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Failed to map fields to object", e);
        }
    }

    private Constructor<?> findConstructorWithMatchingParameterCount(Constructor<?>[] constructors, int fieldCount) {
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == fieldCount) {
                constructor.setAccessible(true);
                return constructor;
            }
        }
        throw new IllegalArgumentException("No constructor with " + fieldCount + " parameters found for " + targetClass.getName());
    }

    private Object convertValue(Class<?> targetType, String value) {
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (Enum.class.isAssignableFrom(targetType)) {
            @SuppressWarnings("unchecked")
            Class<? extends Enum> enumType = (Class<? extends Enum>) targetType;
            return Enum.valueOf(enumType, value.toUpperCase());
        }
        return value; // 기본적으로 문자열로 처리
    }
}
