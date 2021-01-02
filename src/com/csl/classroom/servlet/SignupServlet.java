package com.csl.classroom.servlet;

import cn.hutool.crypto.SecureUtil;
import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.mapper.UserMapper;
import com.csl.classroom.model.User;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.MyBatisUtil;
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

        User user = JsonUtil.read(req, User.class);

        String username = user.getUsername();

        ResponseEntity result;
        if (mapper.findOneByUsername(username) != null) {
            result = ResponseEntity.error("用户名重复");
        } else {
            User record = new User();
            record.setUsername(username);
            record.setPassword(SecureUtil.md5(user.getPassword()));
            record.setAvatar(user.getAvatar());
            record.setNickname(user.getNickname());
            mapper.insert(record);
            result = ResponseEntity.ok("注册成功");
        }

        JsonUtil.write(resp, result);

        sqlSession.close();
    }
}
