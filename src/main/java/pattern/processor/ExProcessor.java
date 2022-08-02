package pattern.processor;


import pattern.*;

public class ExProcessor extends Processor {

    @Override
    public void check(SendRequest sendRequest) {
        System.out.println(String.format("[兑换券]特殊检查用户 %s 是否合法", sendRequest.getUserId()));
    }

    @Override
    SendRst execute(SendRequest sendRequest) {
        Coupon coupon = DB.findCouponByType("EX");
        System.out.println(String.format("[%s] 发送兑换券给 ", coupon.getName()) + sendRequest.getUserId() + "用户");
        SendRst sendRst = newSendRst(sendRequest);

        if (isEnough(coupon)) {//检查数量是否足够
            sendRst.setSendResult(true);
            coupon.setCount(coupon.getCount() - 1);
            DB.update(coupon);
            //保存发放记录
            CouponLine couponLine = new CouponLine();
            couponLine.setCouponCode(coupon.getCode());
            couponLine.setCouponType(coupon.getType());
            couponLine.setId(sendRst.getLineId());
            DB.saveCouponLine(sendRequest.getUserId(), couponLine);
        }

        sendRst.setCoupon(coupon);
        return sendRst;
    }

    private boolean isEnough(Coupon coupon) {
        return coupon.getCount() > 0;
    }
}
