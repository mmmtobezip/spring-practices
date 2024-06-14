package com.poscodx.container.videosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.poscodx.container.config.videosystem.Avengers;
import com.poscodx.container.config.videosystem.DVDPlayer;
import com.poscodx.container.config.videosystem.DigitalVideoDisc;

@Configuration // 명시적 빈 설정 - component 안붙임 -> <bean /> 대신하는 곳
public class DVDPlayerConfig {

  @Bean
  public DigitalVideoDisc avengers() {
    return new Avengers();
  }

  /*
   * 자동 주입의 경우 생성자를 만들지 않아도 @Autowired가 자동으로 해주지만(setter, 생성자 없어도)
   * 컨테이너 내 DVDPlayer가 만들어지고, Avengers 빈 객체들이 만들어짐.
   * DVDPlayer의 dvd와 Avengers는 Avengers()에서 만든 걸로  DI 관계를 맺고, 
   * 즉, DI를 생성자 또는 setter를 이용해 맺어야 함. @Autowired는 relfection을 이용했지만 이건 명시적 설정이니 사용x 
   * DVDPlayer에서 생성자를 만들었음. 
   * 
   */


  // 1. DI Injection(주입) 1
  // Bean 생성 메서드를 직접 호출하는 방법
  // dvdPlayer1 = 고유한 Bean Id
  @Bean("dvdPlayer") // Bean Id 직접 명시도 가능
  public DVDPlayer dvdPlayer1() {
    DVDPlayer dvdPlayer = new DVDPlayer(avengers()); // new DVDPlayer(new Avengers()); 불가

    return dvdPlayer;
  }

  // 2. DI Injection(주입) 2
  // Parameter로 Bean을 전달하는 방법
  @Bean
  public DVDPlayer dvdPlayer2(DigitalVideoDisc dvd) {
    return new DVDPlayer(dvd);
  }


}
