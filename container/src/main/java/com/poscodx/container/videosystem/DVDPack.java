package com.poscodx.container.videosystem;

import java.util.List;

public class DVDPack {
  private String title;
  private List<DigitalVideoDisc> dvds;

  public DVDPack(String title, List<DigitalVideoDisc> dvds) {
    this.title = title;
    this.dvds = dvds;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDvds(List<DigitalVideoDisc> dvds) {
    this.dvds = dvds;
  }

}
