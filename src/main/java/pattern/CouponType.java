package pattern;

public enum CouponType {
    SC("SC", "积分券"), FR("FR", "满减券"), EX("EX", "兑换券");
    private String code;
    private String name;
    CouponType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
