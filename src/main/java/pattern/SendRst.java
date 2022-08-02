package pattern;

import lombok.Data;

@Data
public class SendRst {
    private String userId;
    private Coupon coupon;
    private String lineId;//发放流水号
    private boolean sendResult;//发送结果

    @Override
    public String toString() {
        return "SendRst{" +
                "userId='" + userId + '\'' +
                ", lineId='" + lineId + '\'' +
                ", sendResult=" + sendResult +
                '}';
    }
}
