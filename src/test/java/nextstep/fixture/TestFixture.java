package nextstep.fixture;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class TestFixture {

    public static Question BADAJIGI_QUESTION;
    public static Question JAVAJIGI_QUESTION;
    public static Question SANJIGI_QUESTION;

    public static Answer JAVAJIGI_ANSWER;
    public static Answer SANJIGI_ANSWER;
    public static Answer BADAJIGI_ANSWER;

    public static NsUser JAVAJIGI;
    public static NsUser SANJIGI;
    public static NsUser BADAJIGI;

    static {
        fixtureInit();
    }

    public static void fixtureInit() {
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "king of clean code", "javajigi@slipp.net");
        SANJIGI = new NsUser(2L, "sanjigi", "password", "sanjigi", "sanjigi@slipp.net");
        BADAJIGI = new NsUser(3L, "badajigi", "pacific", "Poseidon", "Poseidon@marine.io");

        JAVAJIGI_QUESTION = new Question(1L, JAVAJIGI, "성공하는 프로그래밍 학습법은 뭐가있을까", "제곧내");
        SANJIGI_QUESTION = new Question(2L, SANJIGI, "관악산 연주대에 오르는 최단경로는 어디인가요", "등산허쉴?");
        BADAJIGI_QUESTION = new Question(2L, BADAJIGI, "deep deep sea", "giant octopus");

        JAVAJIGI_ANSWER = new Answer(11L, JAVAJIGI, JAVAJIGI_QUESTION, "Answers Contents1");
        SANJIGI_ANSWER = new Answer(22L, SANJIGI, SANJIGI_QUESTION, "클린코드는 요구사항을 빠르게 추가하기 위한 유일한 방법이다");
        BADAJIGI_ANSWER = new Answer(33L, BADAJIGI, BADAJIGI_QUESTION, "여름에는 바다를 가야한다");
    }
}