package pattern;

import lombok.Data;

@Data
public class CouponLine {
    private String id;
    private String couponCode;
    private CouponType couponType;
}
