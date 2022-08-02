package pattern;

public class SendRst {
    private String userId;
    private Coupon coupon;
    private String lineId;//发放流水号
    private boolean sendResult;//发送结果

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public boolean isSendResult() {
        return sendResult;
    }

    public void setSendResult(boolean sendResult) {
        this.sendResult = sendResult;
    }

    @Override
    public String toString() {
        return "SendRst{" +
                "userId='" + userId + '\'' +
                ", lineId='" + lineId + '\'' +
                ", sendResult=" + sendResult +
                '}';
    }
}
