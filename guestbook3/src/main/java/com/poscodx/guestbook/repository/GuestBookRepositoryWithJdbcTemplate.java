package com.poscodx.guestbook.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.poscodx.guestbook.vo.GuestBookVo;

@Repository
public class GuestBookRepositoryWithJdbcTemplate {
  private JdbcTemplate jdbcTemplate;

  public GuestBookRepositoryWithJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<GuestBookVo> findAll() {
    return jdbcTemplate
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

  public int insert(GuestBookVo vo) {
    // 가변 파라미터
    return jdbcTemplate.update("insert into guestbook values(null, ?, ?, ?, now())",
        new Object[] {vo.getName(), vo.getPassword(), vo.getContents()});
  }

  public int deleteByNoAndPassword(Long no, String password) {
    // 가변 파라미터
    return jdbcTemplate.update("delete from guestbook where no = ? and password = ?",
        new Object[] {no, password});
  }
}
