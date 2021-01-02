package com.csl.classroom.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import com.csl.classroom.common.ResponseEntity;
import com.csl.classroom.mapper.BuildingMapper;
import com.csl.classroom.model.BuildingDetails;
import com.csl.classroom.util.JsonUtil;
import com.csl.classroom.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author MaoLongLong
 * @date 2020-12-26 17:59
 */
public class BuildingDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        BuildingMapper mapper = sqlSession.getMapper(BuildingMapper.class);

        Map<String, String> param = ServletUtil.getParamMap(req);
        String id = param.get("id");

        BuildingDetails detail = mapper.getBuildingDetail(Convert.toInt(id));

        ResponseEntity result;
        if (detail != null) {
            result = ResponseEntity.ok("查询成功", detail);
        } else {
            result = ResponseEntity.error("查询失败");
        }
        JsonUtil.write(resp, result);

        sqlSession.close();
    }
}
