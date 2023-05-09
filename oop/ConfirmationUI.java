package oop;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.FileWriter;
import java.util.List;
import javax.swing.*;

class ConfirmationUI extends JFrame {

  private JLabel confirmationLabel;
  private JButton printButton;
  private JLabel label_cost;
  private int h;

  public ConfirmationUI(List<Ticket> t, int cost, String nume, String numar) {
    setTitle("Order Confirmation");
    setSize(450, 200);
    setPreferredSize(new Dimension(450, 200));
    setMaximumSize(new Dimension(450, 2000));
    setLayout(new FlowLayout());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(true);
    setLocationRelativeTo(null);

    try {
      FileWriter reserv = new FileWriter("reservation.txt");
      for (Ticket tick : t) {
        reserv.write(tick.toString() + "\n");
      }
      reserv.close();
    } catch (Exception a) {}

    confirmationLabel =
      new JLabel(
        "Ticket(s) reserved successfully for " + nume + "Phone number: " + numar
      );
    add(confirmationLabel);
    for (Ticket tt : t) {
      add(new JLabel(tt.toString()));
    }
    label_cost = new JLabel("Cost: " + cost);
    add(label_cost);

    printButton = new JButton("Print");
    add(printButton);

    printButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          PrinterJob job = PrinterJob.getPrinterJob();
          if (job.printDialog()) {
            job.setPrintable(
              new Printable() {
                @Override
                public int print(
                  Graphics graphics,
                  PageFormat pageFormat,
                  int pageIndex
                ) throws PrinterException {
                  if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                  }
                  graphics.drawString("Name: " + nume, 100, 90);
                  graphics.drawString("Phone number: " + numar, 100, 105);
                  h = 125;
                  for (Ticket tt : t) {
                    graphics.drawString(tt.toString(), 75, h);
                    h = h + 15;
                  }
                  graphics.drawString("Cost total: " + cost, 75, h + 10);
                  return PAGE_EXISTS;
                }
              }
            );

            try {
              job.print();
            } catch (PrinterException ex) {
              ex.printStackTrace();
            }
          }
        }
      }
    );
  }
}
