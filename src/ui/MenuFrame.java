package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MenuFrame extends JFrame {

    private JPanel menuPanel;
    private JButton mainTriggerButton;
    private JButton proceedButton;
    
    private ArrayList<FoodItem> foodList = new ArrayList<>();
    private ArrayList<JButton> itemButtons = new ArrayList<>(); 

    private static class FoodItem {
        String name;
        int price;
        Color buttonColor;
        boolean isSelected = false;

        FoodItem(String name, int price, Color buttonColor) {
            this.name = name;
            this.price = price;
            this.buttonColor = buttonColor;
        }
    }

    public MenuFrame() {
        setTitle("✨ Premium Food Menu ✨");
        setSize(550, 500); // বাটনগুলো ফ্রেশ দেখানোর জন্য সাইজ সামান্য বাড়ানো হলো
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // ⚪ ব্যাকগ্রাউন্ড একদম পরিষ্কার সাদা
        getContentPane().setBackground(Color.WHITE); 

        // আইটেম এবং তাদের জন্য চমৎকার ভাইব্রেন্ট কালার
        foodList.add(new FoodItem("Burger", 100, new Color(230, 126, 34)));       // অরেঞ্জ
        foodList.add(new FoodItem("Pizza", 250, new Color(231, 76, 60)));        // লাল
        foodList.add(new FoodItem("Pasta", 180, new Color(155, 89, 182)));       // পার্পল
        foodList.add(new FoodItem("Fried Chicken", 150, new Color(211, 166, 14))); // ডার্ক গোল্ড/ইয়েলো
        foodList.add(new FoodItem("French Fries", 80, new Color(41, 128, 185)));   // ডার্ক ব্লু
        foodList.add(new FoodItem("Soft Drink", 40, new Color(22, 160, 133)));    // ডার্ক গ্রিন


        // 🌟 প্রথম স্ক্রিনের মেইন ট্রিগার বাটন
        mainTriggerButton = new JButton("🍔 View Food Items & Prices 🍕");
        mainTriggerButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        mainTriggerButton.setBackground(new Color(255, 99, 71)); 
        mainTriggerButton.setForeground(Color.WHITE);
        mainTriggerButton.setFocusPainted(false);
        mainTriggerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // খাবারের কার্ড প্যানেল
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 2, 15, 15)); 
        menuPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        menuPanel.setBackground(Color.WHITE); 

        for (FoodItem item : foodList) {
            JPanel card = createFoodCard(item);
            menuPanel.add(card);
        }

        // 🛒 নিচের অর্ডার প্লেস বাটন (টেক্সট একদম স্পষ্ট কালো করা হয়েছে)
        proceedButton = new JButton("🛒 Proceed to Order (0 Items)");
        proceedButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        proceedButton.setBackground(new Color(46, 204, 113)); // সবুজ
        proceedButton.setForeground(Color.BLACK); // 👈 টেক্সট কালার কালো করা হয়েছে যাতে স্পষ্ট দেখা যায়
        proceedButton.setFocusPainted(false);
        proceedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        proceedButton.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        proceedButton.addActionListener(e -> processMultipleOrders());

        mainTriggerButton.addActionListener(e -> {
            remove(mainTriggerButton); 
            add(menuPanel, BorderLayout.CENTER); 
            add(proceedButton, BorderLayout.SOUTH); 
            revalidate(); 
            repaint();    
        });

        add(mainTriggerButton, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFoodCard(FoodItem item) {
        JPanel cardPanel = new JPanel(new BorderLayout(15, 0));
        cardPanel.setBackground(Color.WHITE); 
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 205, 210), 1, true), // বর্ডার একটু গাঢ় করা হয়েছে
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        // খাবারের নাম ও দাম
        JLabel label = new JLabel("<html><b><font color='#2c3e50'>" + item.name + "</font></b><br><font color='#7f8c8d'>" + item.price + " tk</font></html>");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        // 🎨 কালারফুল সিলেক্ট বাটন (উইন্ডোজ থিম ওভাররাইড ফিক্স)
        JButton selectBtn = new JButton("Select");
        selectBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        selectBtn.setBackground(item.buttonColor); 
        selectBtn.setForeground(Color.WHITE); // 👈 টেক্সট কালার সবসময় সাদা থাকবে
        
        // ⚠️ এই লাইনগুলো উইন্ডোজের ডিফল্ট সাদা থিমকে জোর করে আটকে দেবে:
        selectBtn.setContentAreaFilled(true);
        selectBtn.setOpaque(true);
        selectBtn.setBorderPainted(false); 
        
        selectBtn.setFocusPainted(false);
        selectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemButtons.add(selectBtn);

        // ক্লিক লজিক
        selectBtn.addActionListener(e -> {
            item.isSelected = !item.isSelected;
            if (item.isSelected) {
                selectBtn.setText("✓ Selected");
                selectBtn.setBackground(new Color(44, 62, 80)); // সিলেক্ট হলে চমৎকার নেভি ব্লু/ডার্ক গ্রে
                selectBtn.setForeground(Color.WHITE);
            } else {
                selectBtn.setText("Select");
                selectBtn.setBackground(item.buttonColor); // আগের সুন্দর কালার ফিরবে
                selectBtn.setForeground(Color.WHITE);
            }
            updateOrderButtonText();
        });

        cardPanel.add(label, BorderLayout.CENTER);
        cardPanel.add(selectBtn, BorderLayout.EAST);

        return cardPanel;
    }

    private void updateOrderButtonText() {
        int count = 0;
        for (FoodItem item : foodList) {
            if (item.isSelected) count++;
        }
        proceedButton.setText("🛒 Proceed to Order (" + count + " Items)");
    }

    private void processMultipleOrders() {
        ArrayList<String> selectedNames = new ArrayList<>();
        int totalPrice = 0;

        for (FoodItem item : foodList) {
            if (item.isSelected) {
                selectedNames.add(item.name);
                totalPrice += item.price;
            }
        }

        if (selectedNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one food item!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String finalFoodItems = String.join(", ", selectedNames);
        new OrderFrame(finalFoodItems, totalPrice);
        this.dispose(); 
    }

    public static void main(String[] args) {
        // ❌ উইন্ডোজ থিম লুকে বাটন টেক্সট হাইড হয়ে যাচ্ছিল, তাই মেইন মেথড থেকে LookAndFeel পুরোপুরি রিমুভ করা হয়েছে।
        new MenuFrame();
    }
}