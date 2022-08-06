package pattern.processor;


import pattern.SendRequest;
import pattern.SendRst;
import pattern.msg.MessageSender;
import pattern.msg.SendMsgReq;
import pattern.msg.SendType;

//装饰者
public class EnhanceExProcessor extends ExProcessor {
    SendRst execute(SendRequest sendRequest) {
        SendRst sendRst = super.execute(sendRequest);
        //发送短信
        sendSmsMsg(sendRst);
        return sendRst;
    }

    //发送短信
    private void sendSmsMsg(SendRst sendRst) {
        //System.out.println();
        String msg = String.format("[%s]发送短信给 ", sendRst.getCoupon().getName()) + sendRst.getUserId() + "用户";
        MessageSender.send(
                SendMsgReq.builder()
                        .userId(sendRst.getUserId())
                        .msgBody(msg)
                        .sendType(SendType.SMS)
                        .build()
        );
    }
}
