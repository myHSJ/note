package pattern.processor;


import pattern.*;

public class ScoreProcessor extends Processor {
    @Override
    SendRst execute(SendRequest sendRequest) {
        Coupon coupon = DB.findCouponByType(CouponType.SC);
        System.out.println(String.format("[%s]发送积分给 ", coupon.getName()) + sendRequest.getUserId() + "用户");

        SendRst sendRst = newSendRst(sendRequest);
        sendRst.setCoupon(coupon);

        //保存发放记录
        CouponLine couponLine = new CouponLine();
        couponLine.setCouponCode(coupon.getCode());
        couponLine.setCouponType(coupon.getType());
        couponLine.setId(sendRst.getLineId());
        DB.saveCouponLine(sendRequest.getUserId(), couponLine);

        sendRst.setSendResult(true);


        return sendRst;
    }
}
