package com.robot.notify.provider.impl;

import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.pojo.Code;
import com.robot.api.util.RandomCodeUtil;
import com.robot.api.util.StaticUtil;
import com.robot.notify.dao.CodeDao;
import com.robot.notify.provider.RandomCodeProvider;
import com.robot.notify.util.BaseRedis;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service(version = "1.0.0", timeout = 5000)
public class RandomCodeProviderImpl implements RandomCodeProvider {

    @Resource
    private CodeDao codeDao;

    @Resource
    private BaseRedis baseRedis;

    @Override
    public boolean createCode(String str) {
        Code code = new Code();
        String codeStr = RandomCodeUtil.createCode();
        code.setCode(codeStr);
        code.setNumber(str);
        code.setCreateTime(new Date());
        code.setUpdateTime(new Date());
        int result = codeDao.insertCode(code);
        if (result == 1) {
            baseRedis.set(redisFormat(str), codeStr, StaticUtil.codeExpiryTime);
            return true;
        }
        return false;
    }

    @Override
    public boolean verify(String str, String input) {
        String code = baseRedis.get(redisFormat(str));
        return StringUtils.equals(code, input);
    }

    private static String redisFormat(String str) {
        return String.format(RedisKeyEnum.CODE_KEY.getKey(), str);
    }
}
