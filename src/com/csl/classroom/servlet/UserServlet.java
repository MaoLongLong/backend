package com.csl.classroom.servlet;

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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MaoLongLong
 * @date 2021-01-02 22:09
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.findAll();
        List<User> users = userList.stream()
                .skip(1)
                .peek(u -> u.setPassword(null))
                .collect(Collectors.toList());

        JsonUtil.write(resp, ResponseEntity.ok("成功", users));

        sqlSession.close();
    }
}
