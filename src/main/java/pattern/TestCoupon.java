package pattern;

import pattern.factory.ProcessorFactory;
import pattern.processor.Processor;
import java.util.List;


public class TestCoupon {

    public static void main(String[] args) {


        User newUser = new User("test1", "N", "Y");
        User vipUser = new User("test2", "Y", "N");
        User normalUser = new User("test3", "N", "N");

        TestCoupon testCoupon = new TestCoupon();
        testCoupon.sendCoupon(newUser);

        System.out.println("==打印数据库信息==");
        DB.info();

    }

    private void sendCoupon(User user) {

        List<Processor> processors = ProcessorFactory.getProcessor(user.getUserType());
        if (processors == null) return;
        for (Processor processor : processors) {
            SendRequest sendRequest = new SendRequest();
            sendRequest.setUserId(user.getUserId());
            processor.apply(sendRequest);
        }

    }


}
