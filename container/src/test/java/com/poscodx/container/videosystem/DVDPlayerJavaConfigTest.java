package com.poscodx.container.videosystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.poscodx.container.config.videosystem.DVDPlayerConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DVDPlayerConfig.class})
public class DVDPlayerJavaConfigTest {

  @Autowired
  @Qualifier("dvdPlayer") // 사용할 Bean 객체 선택, DVDPlayerConfig 내 생성한 빈 메서드 = id
  private DVDPlayer dvdPlayer01;

  @Autowired
  @Qualifier("dvdPlayer2")
  // 설정 클래스의 빈 생성 메서드의 이름으로 Qualifier 하기
  private DVDPlayer dvdPlayer02;

  @Autowired
  @Qualifier("dvdPlayer03")
  private DVDPlayer dvdPlayer03;

  @Test
  public void testDVDPlayer01NotNull() {
    assertNotNull(dvdPlayer01);
  }

  @Test
  public void testDVDPlayer02NotNull() {
    assertNotNull(dvdPlayer02);
  }

  @Test
  public void testDVDPlayer03NotNull() {
    assertNotNull(dvdPlayer03);
  }

  @Test
  public void testPlay01() {
    assertEquals("Playing Movie Marvel's Avengers", dvdPlayer01.play());
  }

  @Test
  public void testPlay02() {
    assertEquals("Playing Movie Marvel's Avengers", dvdPlayer02.play());
  }

  @Test
  public void testPlay03() {
    assertEquals("Playing Movie Marvel's Avengers", dvdPlayer03.play());
  }
}
