package ui;

import javax.swing.*;

public class LoginFrame extends JFrame {

    JTextField user;
    JPasswordField pass;

    public LoginFrame() {

        setTitle("Food Login");
        setSize(350, 250);
        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(255, 204, 153));

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(40, 40, 100, 25);
        add(l1);

        user = new JTextField();
        user.setBounds(140, 40, 150, 25);
        add(user);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(40, 80, 100, 25);
        add(l2);

        pass = new JPasswordField();
        pass.setBounds(140, 80, 150, 25);
        add(pass);

        JButton btn = new JButton("LOGIN");
        btn.setBounds(120, 130, 100, 30);
        btn.setBackground(new java.awt.Color(255, 102, 0));
        btn.setForeground(java.awt.Color.WHITE);
        add(btn);

        btn.addActionListener(e -> {
            if(user.getText().equals("admin") &&
               new String(pass.getPassword()).equals("1234")) {

                new MenuFrame().setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Wrong Login!");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
