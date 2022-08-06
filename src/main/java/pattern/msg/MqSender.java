package pattern.msg;

public class MqSender implements Sender {

    @Override
    public void send(SendMsgReq sendMsgReq) {
        System.out.println("发送消息到消息队列："  + sendMsgReq);
    }
}
