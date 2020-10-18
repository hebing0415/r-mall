package com.robot.notify.service.impl;

import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.pojo.Code;
import com.robot.api.util.RandomCodeUtil;
import com.robot.api.util.StaticUtil;
import com.robot.notify.dao.CodeDao;
import com.robot.notify.service.RandomCodeService;
import com.robot.notify.util.BaseRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RandomCodeServiceImpl implements RandomCodeService {

    @Resource
    private CodeDao codeDao;

    @Resource
    private BaseRedis baseRedis;

    @Override
    public String createCode(String str) {
        Code code = new Code();
        String codeStr = RandomCodeUtil.createCode();
        code.setCode(codeStr);
        code.setNumber(str);
        code.setCreateTime(new Date());
        code.setUpdateTime(new Date());
        int result = codeDao.insertCode(code);
        if (result == 1) {
            baseRedis.set(redisFormat(str), codeStr, StaticUtil.codeExpiryTime);
            return codeStr;
        }
        return null;
    }

    @Override
    public boolean verify(String str, String input) {
        boolean result;
        String code = baseRedis.get(redisFormat(str));
        result = StringUtils.equals(code, input);
//        if (result) {
//            baseRedis.del(redisFormat(str));
//        }
        return result;
    }

    private static String redisFormat(String str) {
        return String.format(RedisKeyEnum.CODE_KEY.getKey(), str);
    }
}
