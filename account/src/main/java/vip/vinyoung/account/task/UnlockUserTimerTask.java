package vip.vinyoung.account.task;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import vip.vinyoung.account.dao.UserInfoDao;
import vip.vinyoung.tools.service.Log;
import java.util.TimerTask;

@Slf4j
@Component
public class UnlockUserTimerTask extends TimerTask implements Log {
    private String userId;

    private static UserInfoDao userInfoDao;

    public UnlockUserTimerTask() {

    }

    @Resource
    public void setUserMapper(UserInfoDao userInfoDao) {
        UnlockUserTimerTask.userInfoDao = userInfoDao;
    }

    public UnlockUserTimerTask(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        info("解锁用户: {}", userId);
        UnlockUserTimerTask.userInfoDao.unlockUser(userId);
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
