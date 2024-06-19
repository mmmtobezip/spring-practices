package com.poscodx.container.videosystem.mixing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.poscodx.container.config.videosystem.mixing.DVDPlayerConfig;
import com.poscodx.container.videosystem.DVDPlayer;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DVDPlayerConfig.class})
public class DVDPlayerMixngConfingTest1 {

  @Autowired
  private DVDPlayer dvdPlayer;

  @Test
  public void TestPlay() {
    assertEquals("Playing Move Mavel's Iron Man", dvdPlayer.play());
  }

}
