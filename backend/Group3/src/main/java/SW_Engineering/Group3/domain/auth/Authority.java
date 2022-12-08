package SW_Engineering.Group3.domain.auth;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_NONMEMBER(1), ROLE_MEMBER(2), ROLE_MANAGER(3), ROLE_PRESIDENT(4);

    private final int rank;

    Authority(int rank) {
        this.rank = rank;
    }

}
