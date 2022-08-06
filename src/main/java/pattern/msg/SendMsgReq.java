package pattern.msg;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendMsgReq {
    private SendType sendType;//发送的类型
    private String userId;
    private String receiveUser;
    private String msgBody;
}
