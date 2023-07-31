package com.wanted.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootTest
public class MyBatisConnectionTest {

    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    public MyBatisConnectionTest(DataSource dataSource, SqlSessionFactory sqlSessionFactory) {
        this.dataSource = dataSource;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    void connectionTest() {
        // given
        SqlSession session = sqlSessionFactory.openSession();
        // when
        Connection connection = session.getConnection();
        // then
        Assertions.assertThat(connection).isNotNull();
    }
}
