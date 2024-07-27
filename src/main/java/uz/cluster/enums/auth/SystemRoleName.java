package uz.cluster.enums.auth;

public enum SystemRoleName {

    SYSTEM_ROLE_SUPER_ADMIN(1),
    SYSTEM_ROLE_ADMIN(2);

    private final int value;

    SystemRoleName(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
