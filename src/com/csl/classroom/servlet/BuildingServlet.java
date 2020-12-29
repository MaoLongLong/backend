package com.csl.classroom.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.csl.classroom.common.CommonPage;
import com.csl.classroom.common.CommonResult;
import com.csl.classroom.dto.SingleId;
import com.csl.classroom.mapper.BuildingMapper;
import com.csl.classroom.model.Building;
import com.csl.classroom.util.MyBatisUtil;
import com.csl.classroom.util.ResponseUtil;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:08
 */
public class BuildingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        Map<String, String> param = ServletUtil.getParamMap(req);
        Boolean all = Convert.toBool(param.get("all"));

        if (all != null && all) {
            List<Building> buildings = mapper.findAll(null);
            ResponseUtil.writeJson(resp, CommonResult.ok("查询成功", buildings));
        } else {
            String pageNum = param.get("pageNum");
            String pageSize = param.get("pageSize");
            String keyword = param.get("keyword");

            PageInfo<Building> pageInfo = MyBatisUtil.pagingQuery(pageNum, pageSize,
                    () -> mapper.findAll(keyword));

            ResponseUtil.writeJson(resp, CommonResult.ok("查询成功", CommonPage.restfulPage(pageInfo)));
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        String body = ServletUtil.getBody(req);
        Building record = JSON.parseObject(body, Building.class);
        if (record.getId() == null) {
            record.setId(null);
        }

        int ok = mapper.insert(record);

        CommonResult result;
        if (ok == 1) {
            result = CommonResult.ok("添加成功", record);
        } else {
            result = CommonResult.error("添加失败");
        }
        ResponseUtil.writeJson(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

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
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        String body = ServletUtil.getBody(req);
        Building record = JSON.parseObject(body, Building.class);

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
