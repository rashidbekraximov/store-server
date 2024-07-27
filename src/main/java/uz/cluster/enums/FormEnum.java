package uz.cluster.enums;

public enum FormEnum {

    SELL(1),
    INCOME(2),
    WAREHOUSE(3),
    REPORT(4);

    private final int value;

    FormEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
