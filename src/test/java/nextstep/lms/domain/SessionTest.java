package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    public static final Session CLASS_ONE = new Session(
            1L, LocalDate.of(2023, 5, 22), LocalDate.of(2023, 5, 22),
            SessionState.RECRUITING, SessionType.FREE, 0, 5, 0L);

    private Session session;

    void setUp(int studentCapacity) {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 23);
        Long imageCover = 0L;

        session = new Session(startDate, endDate, imageCover, SessionType.FREE, studentCapacity);
    }

    @Test
    @DisplayName("강의 시작일과 종료일 유효성 체크")
    void validateSessionDateTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 21);

        assertThatThrownBy(() -> new Session(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("종료 날짜가 시작 날짜보다 앞일 수 없습니다.");
    }

    @Test
    @DisplayName("강의 이미지 커버 수정 기능")
    void changeImageCoverTest() {
        setUp(0);
        Long changeCover = 1L;

        assertThat(session.changeImageCover(changeCover))
                .isEqualTo(changeCover);
    }

    @Test
    @DisplayName("강의 모집중으로 변경")
    void recruitStudentsTest() {
        setUp(0);

        assertThat(session.recruitStudents())
                .isEqualTo(SessionState.RECRUITING);
    }

    @Test
    @DisplayName("종료일이 지났을 때, 강의 상태 종료로 변경")
    void finishSessionTest() {
        setUp(0);

        LocalDate now = LocalDate.of(2023, 5, 24);

        assertThat(session.changeFinishSessionState(now))
                .isEqualTo(SessionState.FINISH);
    }

    @Test
    @DisplayName("종료일이 지나지 않았으면 강의 상태가 종료로 바뀌지 않음")
    void notFinishSessionTest() {
        setUp(0);

        LocalDate now = LocalDate.of(2023, 5, 23);

        assertThat(session.changeFinishSessionState(now))
                .isEqualTo(SessionState.READY);
    }

    @Test
    @DisplayName("모집 중이 아닐 때 수강 신청 에러")
    void notRecruitingErrorTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);

        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청 인원 초과 시 수강 신청 에러")
    void overStudentCapacityErrorTest() {
        int studentCapacity = 2;
        setUp(studentCapacity);

        session.recruitStudents();
        session.enroll(NsUserTest.JAVAJIGI);
        session.enroll(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> session.enroll(NsUserTest.BADAJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("학생 강의 수강 신청 기능")
    void registerTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);
        session.recruitStudents();
        session.enroll(NsUserTest.JAVAJIGI);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("학생 수강 취소 기능")
    void cancelTest() {
        int studentCapacity = 5;
        setUp(studentCapacity);
        session.recruitStudents();
        Student javajigiStudent = session.enroll(NsUserTest.JAVAJIGI);
        session.enroll(NsUserTest.SANJIGI);

        session.cancel(javajigiStudent);

        assertThat(session.getStudentCapacity().getRegisteredStudent())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("강의 상태가 준비중이 아닐 때 강의 타입 변경 에러")
    void notReadySessionTypeErrorTest() {
        setUp(0);
        session.recruitStudents();

        assertThatThrownBy(() -> session.changeSessionType(SessionType.PAID))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 타입 변경")
    void changeSessionTypeTest() {
        setUp(0);

        assertThat(session.changeSessionType(SessionType.PAID))
                .isEqualTo(SessionType.PAID);
    }

    @Test
    @DisplayName("강의 개설 기능")
    void createSessionTest() {
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 5, 25);
        Long aLong = 0L;
        SessionType sessionType = SessionType.FREE;
        int studentCapacity = 5;


        Session session = Session.createSession(startDate, endDate, aLong, sessionType, studentCapacity);

        assertThat(session)
                .isInstanceOf(Session.class);
    }

}