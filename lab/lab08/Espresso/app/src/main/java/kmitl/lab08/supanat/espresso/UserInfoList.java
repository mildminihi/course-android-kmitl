package kmitl.lab08.supanat.espresso;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    private List<UserInfo> userInfoList;

    public void clearList(List<UserInfo> userInfoList){
        userInfoList.clear();
    }
}
