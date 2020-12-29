package com.csl.classroom.servlet;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.csl.classroom.common.CommonPage;
import com.csl.classroom.common.CommonResult;
import com.csl.classroom.dto.SingleId;
import com.csl.classroom.mapper.ClassroomMapper;
import com.csl.classroom.model.Classroom;
import com.csl.classroom.util.MyBatisUtil;
import com.csl.classroom.util.ResponseUtil;
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

        ResponseUtil.writeJson(resp, CommonResult.ok("查询成功", CommonPage.restfulPage(pageInfo)));

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        String body = ServletUtil.getBody(req);
        Classroom record = JSON.parseObject(body, Classroom.class);
        if (record.getId() == null) {
            record.setId(null);
        }

        int ok = mapper.insert(record);

        CommonResult result;
        if (ok == 1) {
            result = CommonResult.ok("插入成功", record);
        } else {
            result = CommonResult.error("插入失败");
        }
        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        String body = ServletUtil.getBody(req);
        SingleId param = JSON.parseObject(body, SingleId.class);

        int ok = mapper.deleteByPrimaryKey(param.getId());

        CommonResult result;
        if (ok == 1) {
            result = CommonResult.ok("删除成功");
        } else {
            result = CommonResult.error("删除失败");
        }
        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        ClassroomMapper mapper = sqlSession.getMapper(ClassroomMapper.class);

        String body = ServletUtil.getBody(req);
        Classroom record = JSON.parseObject(body, Classroom.class);

        int ok = mapper.updateByPrimaryKeySelective(record);

        CommonResult result;
        if (ok == 1) {
            result = CommonResult.ok("更新成功", record);
        } else {
            result = CommonResult.error("更新失败");
        }
        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }
}
