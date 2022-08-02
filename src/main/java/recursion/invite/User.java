package recursion.invite;

class User {
    private Integer userId;//用户的id
    private User inviteUser;//邀请用户的id

    public User(Integer userId, User inviteUser) {
        this.userId = userId;
        this.inviteUser = inviteUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(User inviteUser) {
        this.inviteUser = inviteUser;
    }
}
