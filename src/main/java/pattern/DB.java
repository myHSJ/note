package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    public static Map<String, List<CouponLine>> couponSendTable = new HashMap<>();

    private static Map<CouponType, Coupon> couponTable = new HashMap<>();

    static {

        Coupon frCoupon = new Coupon();
        frCoupon.setCode("FR001");
        frCoupon.setType(CouponType.FR);
        frCoupon.setName("满减券");
        couponTable.put(frCoupon.getType(), frCoupon);

        Coupon exCoupon = new Coupon();
        exCoupon.setCode("EX001");
        exCoupon.setType(CouponType.EX);
        exCoupon.setCount(10);
        exCoupon.setName("兑换券");
        couponTable.put(exCoupon.getType(), exCoupon);

        Coupon scoreCoupon = new Coupon();
        scoreCoupon.setCode("SC001");
        scoreCoupon.setType(CouponType.SC);
        scoreCoupon.setName("积分券");
        couponTable.put(scoreCoupon.getType(), scoreCoupon);

    }

    public static Coupon findCouponByType(CouponType type) {
        return couponTable.get(type);
    }

    public static void saveCouponLine(String userId, CouponLine couponLine) {
        List<CouponLine> couponLineList = couponSendTable.get(userId);
        if (couponLineList == null) {
            couponLineList = new ArrayList<>();
        }
        couponLineList.add(couponLine);
        couponSendTable.put(userId, couponLineList);
    }

    public static void info() {
        System.out.println("优惠券表：" + couponTable);
        System.out.println("优惠券发放表：" + couponSendTable);
    }

    public static void update(Coupon coupon) {
        couponTable.put(coupon.getType(), coupon);
    }
}
