package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class Enrollment {
    private final SessionType sessionType;
    private UserCount maxUserCount;
    private Price price;
    private final NsUsers nsUsers = new NsUsers();

    public Enrollment(SessionType sessionType, UserCount maxUserCount, Price price) {
        this.sessionType = sessionType;
        this.maxUserCount = maxUserCount;
        this.price = price;
    }

    public void signUp(Long sessionId, NsUser nsUser, Payment payment) {
        if (sessionType.equals(SessionType.PAY)) {
            checkSessionUserCount();
            payment.checkPayment(sessionId, nsUser.getId(), this.price);
        }
        this.nsUsers.add(nsUser);
    }

    private void checkSessionUserCount() {
        if (!maxUserCount.biggerThan(this.nsUsers.size())) {
            throw new IllegalArgumentException("유료 강의의 최대 수강인원을 초과할 수 없습니다.");
        }
    }
}
