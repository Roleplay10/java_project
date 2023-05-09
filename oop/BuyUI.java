package oop;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

class BuyUI extends JFrame {

  private JLabel label_nume;
  private JTextField field_nume;
  private JLabel label_numar;
  private JTextField field_numar;
  private JLabel label_bani;
  private JTextField field_bani;
  private JLabel label_cost;
  private JButton buyButton;

  private int cost = 0;

  public BuyUI(List<Ticket> t) {
    setTitle("Details Confirmation");
    setSize(400, 200);
    setLayout(new GridLayout(4, 2));
    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    for (Ticket tt : t) {
      this.cost += tt.getCost();
    }

    label_nume = new JLabel("Name: ");
    field_nume = new JTextField(20);
    label_numar = new JLabel("Phone number: ");
    field_numar = new JTextField(20);
    label_bani = new JLabel("Insert funds: ");
    field_bani = new JTextField(20);
    label_cost = new JLabel("Total cost: " + cost);
    buyButton = new JButton("Buy!");

    add(label_nume);
    add(field_nume);
    add(label_numar);
    add(field_numar);
    add(label_bani);
    add(field_bani);
    add(label_cost);
    add(buyButton);

    buyButton.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String nume = field_nume.getText();
          String numar = field_numar.getText();
          String bani = field_bani.getText();
          int nr=0,bn=0;
          try {
            nr = Integer.parseInt(numar);
            bn = Integer.parseInt(bani);;
          } catch (Exception a) {
            JOptionPane.showMessageDialog(rootPane, "Invalid numbers");
          }
          if(nume.length()>0&&nr>111111111&&bn>=cost){
            dispose();
            ConfirmationUI confirmation = new ConfirmationUI(
              t,
              cost,
              nume,
              numar
            );
            confirmation.setVisible(true);
          }
          else{
            JOptionPane.showMessageDialog(rootPane, "Wrong numbers");
          }
          
        }
      }
    );
  }
}
