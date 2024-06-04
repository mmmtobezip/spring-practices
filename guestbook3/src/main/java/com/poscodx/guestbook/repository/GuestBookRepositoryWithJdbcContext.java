package com.poscodx.guestbook.repository;

import org.springframework.stereotype.Repository;
import com.poscodx.guestbook.repository.template.JdbcContext;
import com.poscodx.guestbook.vo.GuestBookVo;

// JdbcContext를 사용한 Repository
@Repository
public class GuestBookRepositoryWithJdbcContext {

  private JdbcContext jdbcContext;

  public GuestBookRepositoryWithJdbcContext(JdbcContext jdbcContext) {
    this.jdbcContext = jdbcContext;
  }

  public int deleteByNoAndPassword(Long no, String password) {
    // 2. JdbcContext(Template)
    return jdbcContext.executeUpdate("delete from guestbook where no = ? and password = ?",
        new Object[] {no, password});
  }

  public int insert(GuestBookVo vo) {
    // 2. JdbcContext(Template)
    return jdbcContext.executeUpdate("insert into guestbook values(null, ?, ?, ?, now())",
        new Object[] {vo.getName(), vo.getPassword(), vo.getContents()});
    // 1. WithStatement Strategy(callback)
    // return jdbcContext.executeUpdateWithStatementStrategy(new StatementStrategy() {
    //
    // @Override
    // public PreparedStatement makeStatement(Connection connection) throws SQLException {
    // PreparedStatement pstmt =
    // connection.prepareStatement("insert into guestbook values(null, ?, ?, ?, now())");
    // pstmt.setString(1, vo.getName());
    // pstmt.setString(2, vo.getPassword());
    // pstmt.setString(3, vo.getContents());
    // return pstmt;
    // }
    // });
  }
}
