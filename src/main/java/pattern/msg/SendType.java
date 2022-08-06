package pattern.msg;

public enum SendType {
    SMS("SMS", "短信类型"), MQ("MQ", "消息队列类型");

    private String code;
    private String remark;

    SendType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }
}
