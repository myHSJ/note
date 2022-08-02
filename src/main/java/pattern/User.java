package pattern;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String isVip;//Y是 N不是
    private String newFlag;//Y是 N不是

    public User(String userId, String isVip, String newFlag) {
        this.userId = userId;
        this.isVip = isVip;
        this.newFlag = newFlag;

    }

    /**
     * PU VU NU
     *
     * @return
     */
    public String getUserType() {
        if ("N".equals(this.getNewFlag()) && "Y".equals(this.getIsVip())) {
            return "PU";
        } else if ("N".equals(this.getNewFlag()) && "Y".equals(this.getIsVip())) {
            return "VU";
        } else if ("Y".equals(this.getNewFlag())) {
            return "NU";
        }
        return null;
    }

}
