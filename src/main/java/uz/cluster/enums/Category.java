package uz.cluster.enums;

public enum Category {

    RECOMMENDED(1),
    PHONE(2),
    COMPUTER(3),
    MAKE_UP(4),
    MANS(5),
    MARKET(6),
    WOMAN(7),
    SHOES_AND_BAG(8),
    SPORT(9),
    UNDERWEAR(10),
    ELECTR_DEVICE(11),
    ACSESSUAR(12),
    MAM_AND_BABY(13),
    FURNITURE(14),
    HOME_DECORATION(15),
    HOME_TEXTILE(16),
    CAR_ACSESSUAR(17),
    FOREIGN_PURCHASE(18);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
