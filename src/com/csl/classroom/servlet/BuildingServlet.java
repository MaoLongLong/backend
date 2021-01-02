package com.csl.classroom.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.common.RestfulPage;
import com.csl.classroom.mapper.BuildingMapper;
import com.csl.classroom.model.Building;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.MyBatisUtil;
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
@SuppressWarnings("DuplicatedCode")
public class BuildingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        Map<String, String> param = ServletUtil.getParamMap(req);
        Boolean all = Convert.toBool(param.get("all"));

        if (all != null && all) {
            List<Building> buildings = mapper.findAll(null);
            JsonUtil.write(resp, ResponseEntity.ok("查询成功", buildings));
        } else {
            String pageNum = param.get("pageNum");
            String pageSize = param.get("pageSize");
            String keyword = param.get("keyword");

            PageInfo<Building> pageInfo = MyBatisUtil.pagingQuery(pageNum, pageSize,
                    () -> mapper.findAll(keyword));

            RestfulPage<Building> page = RestfulPage.of(pageInfo);
            JsonUtil.write(resp, ResponseEntity.ok("查询成功", page));
        }

        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        Building record = JsonUtil.read(req, Building.class);
        if (record.getId() == null) {
            record.setId(null);
        }

        int ok = mapper.insert(record);

        ResponseEntity result;
        if (ok == 1) {
            result = ResponseEntity.ok("添加成功", record);
        } else {
            result = ResponseEntity.error("添加失败");
        }
        JsonUtil.write(resp, result);

        sqlSession.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

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
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        Building record = JsonUtil.read(req, Building.class);

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
