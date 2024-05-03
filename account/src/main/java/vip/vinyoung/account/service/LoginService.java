package vip.vinyoung.account.service;

import vip.vinyoung.account.params.basic.UserParam;
import vip.vinyoung.tools.bean.basic.CommonResult;

public interface LoginService {
    CommonResult<String> login(UserParam param);

    CommonResult<Boolean> check(String account);
}
