package pairmatching.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.domain.Crew;
import pairmatching.enums.Course;

class CrewRepositoryTest {

    @Test
    void 이미_존재하는_이름() {
        // given
        CrewRepository repository = new CrewRepository();
        repository.add(new Crew("철수", Course.BACKEND));

        // when
        Assertions.assertThatThrownBy(() -> repository.add(new Crew("철수", Course.BACKEND)))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatCode(() -> repository.add(new Crew("철수", Course.FRONTEND))).doesNotThrowAnyException();
    }

}