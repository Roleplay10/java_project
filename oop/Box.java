package oop;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;

public class Box {

  private String datas1, datas2, datas3, item_list, temp_st;
  private int dat;
  private String[] split;

  public Box(List<Show> s, List<Ticket> t,List<String> res) {
    JFrame frame = new JFrame("Ticket Booking Agency");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    JPanel shows = new JPanel();
    shows.setBackground(Color.yellow);
    shows.setVisible(false);

    JPanel tickets = new JPanel();
    tickets.setBackground(Color.cyan);
    tickets.setVisible(false);

    JMenuBar mb = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem i1 = new JMenuItem("Shows");
    JMenuItem i2 = new JMenuItem("Ticket");
    menu.add(i1);
    menu.add(i2);
    mb.add(menu);

    JPanel panel1 = new JPanel(new GridLayout(2, 4));
    JComboBox<String> comboBox1 = new JComboBox<>();
    JComboBox<String> comboBox2 = new JComboBox<>();
    JComboBox<String> comboBox3 = new JComboBox<>();
    JComboBox<Integer> comboBox4 = new JComboBox<>();
    JLabel label1 = new JLabel("Show: ");
    JLabel label2 = new JLabel("Date: ");
    JLabel label3 = new JLabel("Hour: ");
    JLabel label4 = new JLabel("Seat: ");

    panel1.add(label1);
    panel1.add(label2);
    panel1.add(label3);
    panel1.add(label4);
    panel1.add(comboBox1);
    panel1.add(comboBox2);
    panel1.add(comboBox3);
    panel1.add(comboBox4);

    JPanel panel2 = new JPanel(new GridLayout(2,1));
    JLabel seatlabel = new JLabel("Seat placement: ");
    panel2.setBackground(Color.yellow);
    panel2.setBorder(new EmptyBorder(5, 100, 5, 100));
    JTable table = new JTable(6, 6) {
      @Override
      public int getRowHeight(int row) {
        return getWidth() / getColumnCount();
      }
    };
    TableColumnModel columnModel = table.getColumnModel();
    for (int i = 0; i < table.getColumnCount(); i++) {
      columnModel.getColumn(i).setPreferredWidth(21);
    }
    table.setEnabled(false);

    panel2.add(seatlabel);
    panel2.add(table);

    JButton button = new JButton("Buy");

    for (Show sw : s) {
      comboBox1.addItem(sw.getName());
    }
    comboBox1.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            datas1 = (String) comboBox1.getItemAt(comboBox1.getSelectedIndex());
            //System.out.println(datas);
            comboBox2.removeAllItems();
            for (Show p : s) {
              if (p.getName().equals(datas1)) {
                for (Representation r : p.getReprez()) {
                  comboBox2.addItem(r.getDateStr());
                }
              }
            }
          } catch (Exception a) {}
        }
      }
    );
    comboBox1.setSelectedIndex(0);
    comboBox2.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            datas2 = (String) comboBox2.getItemAt(comboBox2.getSelectedIndex());
            //System.out.println(datas2);
            comboBox3.removeAllItems();
            for (Show p : s) {
              if (p.getName().equals(datas1)) {
                for (Representation r : p.getReprez()) {
                  if (r.getDateStr().equals(datas2)) {
                    for (Hour h : r.getHours()) {
                      comboBox3.addItem(h.getHour());
                    }
                  }
                }
              }
            }
          } catch (Exception a) {
            
          }
        }
      }
    );
    comboBox2.setSelectedIndex(0);
    comboBox3.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            datas3 = (String) comboBox3.getItemAt(comboBox3.getSelectedIndex());
            //System.out.println(datas2);
            comboBox4.removeAllItems();
            for (Show p : s) {
              if (p.getName().equals(datas1)) {
                for (Representation r : p.getReprez()) {
                  if (r.getDateStr().equals(datas2)) {
                    for (Hour h : r.getHours()) {
                      if (h.getHour().equals(datas3)) {
                        for (Seat st : h.getSeats()) {
                          if (st.getState() == false) {
                            comboBox4.addItem(st.getNr());
                          }
                          int[] num = ceva(st.getNr());
                          setTableCellValue(
                            table,
                            num[1],
                            num[0],
                            st.getState()
                          );
                        }
                      }
                    }
                  }
                }
              }
            }
          } catch (Exception a) {}
        }
      }
    );
    comboBox3.setSelectedIndex(0);
    comboBox4.setSelectedIndex(0);
    i1.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            frame.getContentPane().removeAll();
            shows.setVisible(true);
            tickets.setVisible(false);
            frame.add(shows, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            setIdx(comboBox1);
            setIdx(comboBox2);
            setIdx(comboBox3);
            setIdx(comboBox4);
          } catch (Exception a) {}
        }
      }
    );
    i2.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            frame.getContentPane().removeAll();
            shows.setVisible(false);
            tickets.setVisible(true);
            frame.add(tickets, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
          } catch (Exception a) {}
        }
      }
    );
    DefaultListModel<String> temp = new DefaultListModel<>();
    JList<String> listTick = new JList<>(temp);
    button.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            dat = comboBox4.getItemAt(comboBox4.getSelectedIndex());
            for (Show p : s) {
              if (p.getName().equals(datas1)) {
                for (Representation r : p.getReprez()) {
                  if (r.getDateStr().equals(datas2)) {
                    for (Hour h : r.getHours()) {
                      if (h.getHour().equals(datas3)) {
                        for (Seat st : h.getSeats()) {
                          if (st.getState() == false && st.getNr() == dat) {
                            Ticket tt = new Ticket();
                            tt.setTicket(
                              p.getName(),
                              r.getDateStr(),
                              h.getHour(),
                              st.getNr()
                            );
                            st.setOccupied(true);
                            comboBox4.removeItem(st.getNr());
                            t.add(tt);
                            temp.addElement(tt.toString());
                          }
                          int[] num = ceva(st.getNr());
                          setTableCellValue(
                            table,
                            num[1],
                            num[0],
                            st.getState()
                          );
                        }
                      }
                    }
                  }
                }
              }
            }
          } catch (Exception a) {}
        }
      }
    );
    listTick.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listTick.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            item_list = listTick.getSelectedValue();
            temp_st = item_list;
            split = temp_st.split(" ");
          } catch (Exception a) {}
        }
      }
    );
    JButton button3 = new JButton("Remove");
    button3.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            for (Ticket tick : t) {
              if (
                tick.getName().equals(split[2]) &&
                tick.getRepre().equals(split[4]) &&
                tick.getHour().equals(split[6]) &&
                tick.getSeat() == Integer.parseInt(split[8])
              ) {
                for (Show p : s) {
                  if (p.getName().equals(split[2])) {
                    for (Representation r : p.getReprez()) {
                      if (r.getDateStr().equals(split[4])) {
                        for (Hour h : r.getHours()) {
                          if (h.getHour().equals(split[6])) {
                            for (Seat st : h.getSeats()) {
                              if (
                                st.getState() == true &&
                                st.getNr() == Integer.parseInt(split[8])
                              ) {
                                st.setOccupied(false);
                                //System.out.println(st.getState());
                                //t.remove(tick);
                                temp.removeElement(tick.toString());
                                if (t.size() > 1) {
                                  eraseElem(t, tick);
                                } else {
                                  t.removeAll(t);
                                }
                                if (listTick.getModel().getSize() > 0) {
                                  listTick.setSelectedIndex(0);
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                comboBox4.removeAllItems();
                for (Show p : s) {
                  if (p.getName().equals(datas1)) {
                    for (Representation r : p.getReprez()) {
                      if (r.getDateStr().equals(datas2)) {
                        for (Hour h : r.getHours()) {
                          if (h.getHour().equals(datas3)) {
                            for (Seat st : h.getSeats()) {
                              if (st.getState() == false) {
                                comboBox4.addItem(st.getNr());
                              }
                              int[] num = ceva(st.getNr());
                              setTableCellValue(
                                table,
                                num[1],
                                num[0],
                                st.getState()
                              );
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          } catch (Exception a) {}
        }
      }
    );

    JButton button2 = new JButton("Buy");
    button2.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            BuyUI buy_ui = new BuyUI(t);
            buy_ui.setVisible(true);
          } catch (Exception a) {}
        }
      }
    );
    JScrollPane scrollPane = new JScrollPane(listTick);
    scrollPane.setPreferredSize(new Dimension(300, 330));
    tickets.add(scrollPane, BorderLayout.EAST);
    tickets.add(button2);
    tickets.add(button3);

    frame.setJMenuBar(mb);
    shows.add(panel1, BorderLayout.NORTH);
    shows.add(panel2, BorderLayout.EAST);
    shows.add(button);
    frame.getContentPane().add(shows);
    frame.getContentPane().add(tickets);

    //frame.pack();
    frame.setVisible(true);
  }

  public void eraseElem(List<Ticket> t, Ticket tick) {
    for (Ticket tt : t) {
      if (
        tt.getName().equals(tick.getName()) &&
        tt.getRepre().equals(tick.getRepre()) &&
        tt.getHour().equals(tick.getHour()) &&
        tt.getSeat() == tick.getSeat()
      ) {
        t.remove(tick);
      }
    }
  }

  public void setTableCellValue(JTable table, int row, int col, boolean value) {
    //System.out.println(row+" "+col);
    int s = row*6 + col + 1;
    table.setValueAt(value ? "X" : s, row, col);
  }

  public int[] ceva(int n) {
    int r = 0, c = 0;
    r = (n - 1) % 6;
    c = (n - 1) / 6;
    int[] num = { r, c };
    return num;
  }

  public void setIdx(JComboBox j) {
    int ceva = j.getSelectedIndex();
    j.setSelectedIndex(ceva);
  }
}
