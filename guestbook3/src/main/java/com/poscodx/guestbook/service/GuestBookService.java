package com.poscodx.guestbook.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.poscodx.guestbook.repository.GuestBookLogRepository;
import com.poscodx.guestbook.repository.GuestBookRepository;
import com.poscodx.guestbook.vo.GuestBookVo;

@Service
public class GuestBookService {
  @Autowired
  private DataSource dataSource;

  @Autowired
  private GuestBookRepository guestBookRepository;

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private GuestBookLogRepository guestBookLogRepository;

  // 전체 게시글 가져오기
  public List<GuestBookVo> getContentsList() {
    return guestBookRepository.findAll();
  }

  // 게시글 지우기 - no & password 이용
  public void deleteContents(Long no, String password) {
    // TX: BEGIN //
    TransactionStatus status =
        transactionManager.getTransaction(new DefaultTransactionDefinition());
    try {
      guestBookLogRepository.update(no);
      guestBookRepository.deleteByNoAndPassword(no, password);

      // TX: END(SUCCESS) //
      transactionManager.commit(status);
    } catch (Throwable e) {
      // TX: END(FAIL) //
      transactionManager.rollback(status);
    }
  }

  // 게시글 추가
  public void addContents(GuestBookVo vo) {
    // 트랜잭션 동기(Connection) 초기화
    TransactionSynchronizationManager.initSynchronization(); // static function -> bean 생성x -> 스레드
                                                             // 로컬 초기화 목적

    // 스레드로컬에 커넥션 놓고, 트랜잭션 경계를 잡아주고(서비스 계층에선), 스레드로컬엔 query를 jdbcContext가 빼올 수 있도록 커넥션을 동기화 시켜주는 작업.
    Connection conn = DataSourceUtils.getConnection(dataSource);

    try {
      // TX:BEGIN //
      conn.setAutoCommit(false);

      int count = guestBookLogRepository.update();

      if (count == 0) {
        guestBookLogRepository.insert();
      }
      guestBookRepository.insert(vo);

      // TX:END(SUCCESS) //
      conn.commit();
    } catch (Throwable e) {
      // TX:END(FAIL) //
      try {
        conn.rollback();
      } catch (SQLException ignored) {
      }
    } finally {
      DataSourceUtils.releaseConnection(conn, dataSource);
    }
  }
}

