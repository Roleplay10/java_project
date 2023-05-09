package oop;

import java.util.ArrayList;
import java.util.List;

public class Hour {

  private String hour;
  private List<Seat> seats = new ArrayList<Seat>();

  public Hour(String d) {
    this.hour = d;
    setSeat();
  }

  public void setSeat() {
    for (int i = 1; i <= 36; i++) {
      Seat s = new Seat(i);
      this.seats.add(s);
    }
  }

  public List<Seat> getSeats() {
    return this.seats;
  }

  public String getHour() {
    return this.hour;
  }
}
