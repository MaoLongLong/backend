package com.csl.classroom.servlet;

import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.dto.UserInfo;
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
 * @date 2020-12-28 16:03
 */
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String username = (String) req.getAttribute("username");
        User user = mapper.findOneByUsername(username);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setNickname(user.getNickname());

        JsonUtil.write(resp, ResponseEntity.ok("获取成功", userInfo));

        sqlSession.close();
    }
}
