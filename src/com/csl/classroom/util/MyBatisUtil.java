package com.csl.classroom.util;

import cn.hutool.core.convert.Convert;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author MaoLongLong
 * @date 2020-12-26 16:16
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MyBatisUtil() {
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static SqlSession getSqlSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

    public static <T> PageInfo<T> pagingQuery(String pageNum, String pageSize, ISelect iSelect) {
        return PageHelper.startPage(
                Optional.ofNullable(Convert.toInt(pageNum)).orElse(1),
                Optional.ofNullable(Convert.toInt(pageSize)).orElse(10))
                .doSelectPageInfo(iSelect);
    }
}
