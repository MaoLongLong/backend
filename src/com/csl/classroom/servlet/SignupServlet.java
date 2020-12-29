package com.csl.classroom.servlet;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.csl.classroom.common.CommonResult;
import com.csl.classroom.mapper.UserMapper;
import com.csl.classroom.model.User;
import com.csl.classroom.util.MyBatisUtil;
import com.csl.classroom.util.ResponseUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2020-12-27 18:56
 */
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String body = ServletUtil.getBody(req);
        User user = JSON.parseObject(body, User.class);

        String username = user.getUsername();

        CommonResult result;
        if (mapper.findOneByUsername(username) != null) {
            result = CommonResult.error("用户名重复");
        } else {
            User record = new User();
            record.setUsername(username);
            record.setPassword(SecureUtil.md5(user.getPassword()));
            record.setAvatar(user.getAvatar());
            record.setNickname(user.getNickname());
            mapper.insert(record);
            result = CommonResult.ok("注册成功");
        }

        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }
}
