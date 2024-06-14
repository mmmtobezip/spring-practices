package com.poscodx.container.config.videosystem;

public class Avengers implements DigitalVideoDisc {
  private String title = "Avengers";
  private String studio = "Marvel";

  @Override
  public String play() {
    return "Playing Movie " + studio + "'s " + title;
  }

}
