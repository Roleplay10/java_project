package oop;

import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    List<Show> s = new ArrayList<Show>();
    List<Ticket> t = new ArrayList<Ticket>();
    try {
      File myFile = new File("shows.txt");
      Scanner scan = new Scanner(myFile);
      while (scan.hasNextLine()) {
        String[] temp = scan.nextLine().split(" ");
        Show p = new Show(temp);
        boolean ok = false;
        for (Show sh : s) {
          if (sh.getName().equals(temp[0])) {
            sh.addRep(temp);
            ok = true;
          }
        }
        if (!ok) {
          s.add(p);
        }
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
    }
    List<String> reservations = new ArrayList<>();
    try {
      File reserv = new File("reservation.txt");
      Scanner myReader = new Scanner(reserv);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        reservations.add(data);
        String[] data_split = data.split(" ");
        for (Show p : s) {
          if (p.getName().equals(data_split[2])) {
            for (Representation r : p.getReprez()) {
              if (r.getDateStr().equals(data_split[4])) {
                for (Hour h : r.getHours()) {
                  if (h.getHour().equals(data_split[6])) {
                    for (Seat st : h.getSeats()) {
                      if (st.getNr() == Integer.parseInt(data_split[8])) {
                        st.setOccupied(true);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      myReader.close();
    } catch (Exception a) {}
    new Box(s, t,reservations);
  }
}
