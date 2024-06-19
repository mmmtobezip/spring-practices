package com.poscodx.container.config.videosystem.mixing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;

/*
 * JavaConfig2 <-- JavaConfig1(외부에 있는 DVDConfig를 의미)
 * '<---': import를 의미, 즉 외부에 있는 빈을 가져와 DI 주입?
 * 
 * DVDPlayerConfig를 통해 DVD 전체의 설정을 끝내는 
 */

@Configuration
@Import({DVDConfig.class})
public class DVDPlayerConfig {
  public DVDPlayer dvdPlayer(@Qualifier("avengers") DigitalVideoDisc dvd) {
    return new DVDPlayer(dvd); //생성자 주입 
  }

}
