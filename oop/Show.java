package oop;

import java.util.*;

public class Show {

  private String name;
  private List<Representation> representation = new ArrayList<Representation>();

  public Show() {}

  public void setName(String n) {
    this.name = n;
  }

  public void addRep(String[] s) {
    Representation r = new Representation(s);
    boolean ok = false;
    for (Representation rep : representation) {
      if (rep.getDateStr().equals(s[1])) {
        rep.addHour(s[2]);
        ok = true;
      }
    }
    if (!ok) {
      this.representation.add(r);
    }
  }

  public Show(String[] n) {
    setName(n[0]);
    addRep(n);
  }

  public String getName() {
    return this.name;
  }

  public List<Representation> getReprez() {
    return this.representation;
  }
}
