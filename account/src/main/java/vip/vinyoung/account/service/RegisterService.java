package vip.vinyoung.account.service;

import vip.vinyoung.account.params.UserDetailParam;
import vip.vinyoung.tools.bean.basic.CommonResult;

public interface RegisterService {
    CommonResult<String> mailCode(String mailAddr);

    CommonResult<String> register(UserDetailParam param);
}
