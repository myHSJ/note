package pattern.processor;


import pattern.SendRequest;
import pattern.SendRst;

import java.util.UUID;

/**
 * 模板方法
 */
public abstract class Processor {

    private Processor next;

    public void check(SendRequest sendRequest) {
        System.out.println(String.format("检查用户 %s 是否合法", sendRequest.getUserId()));
    }

    abstract SendRst execute(SendRequest sendRequest);

    private void sendMsgToMQ(SendRst sendRst) {
        System.out.println(String.format("[%s]将发放的消息通知给MQ: ", sendRst.getCoupon().getName()) + sendRst);
        System.out.println();
    }

    public void apply(SendRequest sendRequest) {
        check(sendRequest);
        //添加一条记录
        SendRst sendRst = execute(sendRequest);

        sendMsgToMQ(sendRst);
    }

    public SendRst newSendRst(SendRequest sendRequest) {

        SendRst sendRst = new SendRst();
        sendRst.setUserId(sendRequest.getUserId());
        sendRst.setLineId(UUID.randomUUID() + "");
        return sendRst;

    }
}
