package com.csl.classroom.servlet;

import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.mapper.BuildingMapper;
import com.csl.classroom.mapper.ClassroomMapper;
import com.csl.classroom.mapper.UserMapper;
import com.csl.classroom.model.Dashboard;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MaoLongLong
 * @date 2021-01-05 14:05
 */
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        BuildingMapper buildingMapper = sqlSession.getMapper(BuildingMapper.class);
        ClassroomMapper classroomMapper = sqlSession.getMapper(ClassroomMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Dashboard status = Dashboard.builder()
                .userCount(userMapper.count())
                .buildingCount(buildingMapper.count())
                .classroomCount(classroomMapper.count())
                .build();

        JsonUtil.write(resp, ResponseEntity.ok("查询成功", status));

        sqlSession.close();
    }
}
