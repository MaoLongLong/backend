package com.csl.classroom.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.common.RestfulPage;
import com.csl.classroom.mapper.ClassroomMapper;
import com.csl.classroom.model.Classroom;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.MyBatisUtil;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author MaoLongLong
 * @date 2020-12-26 17:46
 */
@SuppressWarnings("DuplicatedCode")
public class ClassroomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        Map<String, String> param = ServletUtil.getParamMap(req);
        String pageNum = param.get("pageNum");
        String pageSize = param.get("pageSize");
        String keyword = param.get("keyword");

        PageInfo<Classroom> pageInfo = MyBatisUtil.pagingQuery(pageNum, pageSize,
                () -> mapper.findAll(keyword));

        JsonUtil.write(resp, ResponseEntity.ok("查询成功", RestfulPage.of(pageInfo)));

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        Classroom record = JsonUtil.read(req, Classroom.class);
        if (record.getId() != null) {
            record.setId(null);
        }

        int ok = mapper.insertSelective(record);

        ResponseEntity result;
        if (ok == 1) {
            result = ResponseEntity.ok("插入成功", record);
        } else {
            result = ResponseEntity.error("插入失败");
        }
        JsonUtil.write(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        Integer id = Convert.toInt(req.getParameter("id"));

        int ok = mapper.deleteByPrimaryKey(id);

        ResponseEntity result;
        if (ok == 1) {
            result = ResponseEntity.ok("删除成功");
        } else {
            result = ResponseEntity.error("删除失败");
        }
        JsonUtil.write(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        Classroom record = JsonUtil.read(req, Classroom.class);

        int ok = mapper.updateByPrimaryKeySelective(record);

        ResponseEntity result;
        if (ok == 1) {
            result = ResponseEntity.ok("更新成功", record);
        } else {
            result = ResponseEntity.error("更新失败");
        }
        JsonUtil.write(resp, result);

        sqlSession.close();
    }
}
