package vip.vinyoung.account.service;

import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.tools.bean.basic.CommonResult;

public interface LoginService {
    CommonResult login(UserParam param);

    CommonResult check(String account);
}
