package oop;

public class Ticket {

  private String name;
  private String representation;
  private String hour;
  private int locScaun;
  private int cost = 25;

  public Ticket() {}

  public void setTicket(String name, String date, String hour, int l) {
    this.name = name;
    this.representation = date;
    this.hour = hour;
    locScaun = l;
  }

  public String toString() {
    return String.format(
      "Ticket for %s on %s at %s seat %d",
      getName(),
      getRepre(),
      getHour(),
      getSeat()
    );
  }

  public String getName() {
    return this.name;
  }

  public String getRepre() {
    return this.representation;
  }

  public String getHour() {
    return this.hour;
  }

  public int getSeat() {
    return this.locScaun;
  }

  public int getCost() {
    return this.cost;
  }
}
