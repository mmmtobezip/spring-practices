package com.poscodx.guestbook.repository.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcContext {
  private DataSource dataSource;

  // 생성자 의존 주입
  public JdbcContext(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
    // -- 리턴타입 <T> List<T>의 단점 --
    // 쿼리가 파라미터가 있으면 안됨
    // 리스트 길이가 1이어도 리스트로 만들어짐
    return executeQueryWithStatementStrategy(new StatementStrategy() {
      @Override
      public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt;
      }
    }, rowMapper);
  }

  private <E> List<E> executeQueryWithStatementStrategy(StatementStrategy statementStrategy,
      RowMapper<E> rowMapper) {
    List<E> result = new ArrayList<>();

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = DataSourceUtils.getConnection(dataSource);
      pstmt = statementStrategy.makeStatement(conn);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        E e = rowMapper.mapRow(rs, rs.getRow()); // RowMapper의 타입으로 만들어줌
        result.add(e);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          DataSourceUtils.releaseConnection(conn, dataSource);
        }
      } catch (SQLException ignored) {
      }
    }
    return result;
  }

  public int update(String sql, Object[] parameters) {
    return executeUpdateWithStatementStrategy(new StatementStrategy() {
      @Override
      public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        for (int i = 0; i < parameters.length; i++) {
          pstmt.setObject(i + 1, parameters[i]);
        }
        return pstmt;
      }
    });
  }

  // 바인딩이 필요없는 경우
  public int update(String sql) {
    return executeUpdateWithStatementStrategy(new StatementStrategy() {
      @Override
      public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        return pstmt;
      }
    });
  }

  private int executeUpdateWithStatementStrategy(StatementStrategy statementStrategy) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = DataSourceUtils.getConnection(dataSource);
      pstmt = statementStrategy.makeStatement(conn);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (pstmt != null) {
          pstmt.close();
        }
        if (conn != null) {
          DataSourceUtils.releaseConnection(conn, dataSource);
        }
      } catch (SQLException ignored) {
      }
    }
    return result;
  }

  // public <T> T executeQueryForObject(String sql) {
  // return null;
  // }
  //
  // public <T> List<T> executeQueryForObject(String sql, Object[] parameter) {
  // return null;
  // }
}
