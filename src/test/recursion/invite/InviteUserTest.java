package test.recursion.invite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InviteUserTest {

    //获取注册用户的所有邀请人
    public void findInviteUsers(User user, List<Integer> res) {
        // 当查找到用户没有上级用户时，说明已经没有上级（邀请人）
        User inviteUser = user.getInviteUser();
        if (inviteUser == null) return;
        res.add(inviteUser.getUserId());
        findInviteUsers(inviteUser, res);
    }

    public static void main(String[] args) {
        // u3邀请u2，u2邀请u1
        User u3 = new User(20, null);
        User u2 = new User(10, u3);
        User u1 = new User(1, u2);

        //获取u1所有上级邀请人
        List<Integer> res = new ArrayList<>();
        new InviteUserTest().findInviteUsers(u1, res);
        System.out.print("u1所有邀请人的ID列表：");
        if (res.size() == 0) return;
        for (int i = 0; i < res.size() - 1; i++) {
            System.out.print(res.get(i) + ", ");
        }
        System.out.print(res.get(res.size() - 1));
    }

}
