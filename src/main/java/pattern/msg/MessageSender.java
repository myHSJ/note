package pattern.msg;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/*策略模式*/
public class MessageSender {

    public static Map<SendType, Sender> senderMap = new HashMap<>();

    static {
        senderMap.put(SendType.SMS, new SmsSender());
        senderMap.put(SendType.MQ, new MqSender());
    }

    public static void send(SendMsgReq sendMsgReq) {
        assert Objects.nonNull(sendMsgReq.getSendType());

        //获取发送给的策略
        Sender sender = senderMap.get(sendMsgReq.getSendType());
        if(sender == null) return;

        //得到发送实现类后，将消息发送出去
        sender.send(sendMsgReq);
    }
}
