package pairmatching.enums;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course findByName(String name) {
        for (Course course : values()) {
            if (course.name.equals(name.trim())) {
                return course;
            }
        }
        throw new IllegalArgumentException("없는 코스 이름입니다");
    }
}
