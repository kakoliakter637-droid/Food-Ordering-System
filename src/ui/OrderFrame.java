package ui;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class OrderFrame extends JFrame {

    JTextField name;
    JTextField foodField;
    JTextField priceField;

    JComboBox<String> qtyBox;
    JComboBox<String> paymentBox;

    JButton btn;

    int unitPrice;

    public OrderFrame(String food, int price) {

        this.unitPrice = price;

        setTitle("Order System");
        setSize(400, 380);
        setLayout(null);

        getContentPane().setBackground(
                new java.awt.Color(173, 216, 230));

        // NAME
        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 30, 100, 25);
        add(l1);

        name = new JTextField();
        name.setBounds(130, 30, 180, 25);
        add(name);

        // FOOD (AUTO)
        JLabel l2 = new JLabel("Food:");
        l2.setBounds(30, 70, 100, 25);
        add(l2);

        foodField = new JTextField(food);
        foodField.setBounds(130, 70, 180, 25);
        foodField.setEditable(false);
        add(foodField);

        // PRICE (AUTO)
        JLabel l3 = new JLabel("Price:");
        l3.setBounds(30, 110, 100, 25);
        add(l3);

        priceField = new JTextField(String.valueOf(price));
        priceField.setBounds(130, 110, 180, 25);
        priceField.setEditable(false);
        add(priceField);

        // QUANTITY
        JLabel l4 = new JLabel("Qty:");
        l4.setBounds(30, 150, 100, 25);
        add(l4);

        String[] qty = {"1", "2", "3", "4", "5"};
        qtyBox = new JComboBox<>(qty);
        qtyBox.setBounds(130, 150, 180, 25);
        add(qtyBox);

        // PAYMENT - Cash অপশনটি এখানে যোগ করা হয়েছে
        JLabel l5 = new JLabel("Payment:");
        l5.setBounds(30, 190, 100, 25);
        add(l5);

        String[] methods = {
            "bKash",
            "Nagad",
            "Rocket",
            "Online Card",
            "Cash"
        };

        paymentBox = new JComboBox<>(methods);
        paymentBox.setBounds(130, 190, 180, 25);
        add(paymentBox);

        // BUTTON
        btn = new JButton("ORDER & PAY");
        btn.setBounds(120, 240, 150, 35);
        btn.setBackground(new java.awt.Color(0, 153, 76));
        btn.setForeground(java.awt.Color.WHITE);
        add(btn);

        btn.addActionListener(e -> saveOrder());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void saveOrder() {

        try {

            Connection con = DBConnection.getConnection();

            int q = Integer.parseInt(qtyBox.getSelectedItem().toString());

            int total = q * unitPrice;

            String payment = paymentBox.getSelectedItem().toString();

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO orders(customer_name,food_item,quantity,total) VALUES(?,?,?,?)");

            ps.setString(1, name.getText());
            ps.setString(2, foodField.getText());
            ps.setInt(3, q);
            ps.setInt(4, total);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Order Successful!\n"
                    + "Food: " + foodField.getText()
                    + "\nPrice: " + unitPrice
                    + "\nQty: " + q
                    + "\nTotal: " + total
                    + "\nPayment: " + payment);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new OrderFrame("Burger", 100);
    }
}