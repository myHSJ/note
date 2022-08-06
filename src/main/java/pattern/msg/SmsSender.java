package pattern.msg;

public class SmsSender implements Sender {

    @Override
    public void send(SendMsgReq sendMsgReq) {
        System.out.println("发送短信消息：" + sendMsgReq);
    }
}
