package com.csl.classroom.servlet;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.csl.classroom.common.CommonResult;
import com.csl.classroom.dto.LoginParam;
import com.csl.classroom.mapper.UserMapper;
import com.csl.classroom.model.User;
import com.csl.classroom.util.JwtUtil;
import com.csl.classroom.util.MyBatisUtil;
import com.csl.classroom.util.ResponseUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2020-12-28 12:46
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String body = ServletUtil.getBody(req);
        LoginParam param = JSON.parseObject(body, LoginParam.class);

        User record = mapper.findOneByUsername(param.getUsername());

        CommonResult result;
        if (checkPassword(record.getPassword(), param.getPassword())) {
            String token = JwtUtil.generateToken(param);
            result = CommonResult.ok("登录成功", token);
        } else {
            result = CommonResult.error("用户名或密码错误");
        }

        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }

    private boolean checkPassword(String pwd, String raw) {
        return SecureUtil.md5(raw).equals(pwd);
    }
}
