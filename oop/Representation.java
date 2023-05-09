package oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Representation {

  private Date date;
  private List<Hour> hours = new ArrayList<Hour>();

  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

  public void setDate(String d) {
    try {
      this.date = format.parse(d);
    } catch (ParseException e) {
      System.out.println("Error date format");
    }
  }

  public Representation(String[] d) {
    setDate(d[1]);
    setHour(d[2]);
  }

  public Date getDate() {
    return this.date;
  }

  public String getDateStr() {
    return format.format(this.date);
  }

  public void setHour(String d) {
    Hour h = new Hour(d);
    this.hours.add(h);
  }

  public List<Hour> getHours() {
    return this.hours;
  }

  public void addHour(String s) {
    Hour h = new Hour(s);
    this.hours.add(h);
  }
}
