package pattern;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Coupon {

    private String code;
    private CouponType type;
    private String name;
    private Integer count;

    @Override
    public String toString() {
        return "Coupon{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
