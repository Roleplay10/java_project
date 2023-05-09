package oop;

public class Seat {

  private int number;
  private boolean occupied;

  public void setNr(int n) {
    this.number = n;
  }

  public Seat(int n) {
    setNr(n);
    this.occupied = false;
  }

  public int getNr() {
    return this.number;
  }

  public boolean getState() {
    return this.occupied;
  }

  public void setOccupied(boolean b) {
    this.occupied = b;
  }
}
