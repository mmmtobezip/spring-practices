package com.poscodx.container.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // 스프링 통합 환경 구축을 위한 애노테이션
@ContextConfiguration(locations = {"classpath:com/poscodx/container/config/soundsystem/applicationContext.xml"}) // 어떤 configuration으로 빈 객체를 가져올지 명시
public class CDPlayerXmlConfigTest {
  
  @Autowired 
  private CDPlayer cdPlayer;
  
  
  @Test
  public void testCDPlayNotNull() {
    assertNotNull(cdPlayer); //return: null이면 주입 실패한 것 
  }

  @Test
  public void testPlay() {
    assertEquals("Playing 붕붕 by 김하온", cdPlayer.play());
  }
}
