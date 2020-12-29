package com.csl.classroom.servlet;

import cn.hutool.core.lang.Dict;
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
 * @date 2020-12-28 16:03
 */
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String username = (String) req.getAttribute("username");
        User user = mapper.findOneByUsername(username);

        Dict dict = Dict.create();
        dict.set("avatar", user.getAvatar());
        dict.set("nickname", user.getNickname());

        ResponseUtil.writeJson(resp, CommonResult.ok("获取成功", dict));

        sqlSession.close();
    }
}
