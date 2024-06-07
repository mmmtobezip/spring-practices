package com.poscodx.guestbook.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
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

  public List<GuestBookVo> findAll() {
    return jdbcContext
        .query("select no, name, contents, date_format(reg_date, '%Y/%m/%d %H:%i:%s') "
            + "from guestbook " + "order by reg_date desc", new RowMapper<GuestBookVo>() {

              @Override
              public GuestBookVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                GuestBookVo vo = new GuestBookVo();
                vo.setNo(rs.getLong(1));
                vo.setName(rs.getString(2));
                vo.setContents(rs.getString(3));
                vo.setRegDate(rs.getString(4));
                return vo; // vo가 rowMapper로 들어감
              }
            });
  }

  public int deleteByNoAndPassword(Long no, String password) {
    // 2. JdbcContext(Template)
    return jdbcContext.update("delete from guestbook where no = ? and password = ?",
        new Object[] {no, password});
  }

  public int insert(GuestBookVo vo) {
    // 2. JdbcContext(Template)
    return jdbcContext.update("insert into guestbook values(null, ?, ?, ?, now())",
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
