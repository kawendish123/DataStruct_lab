package kawen.com.lab1.Airline_ticket_booking_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

public class Menu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("航空机票订票系统");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        JLabel titleLabel = new JLabel("尊敬的用户，你好！欢迎使用航空机票订票系统");
        titleLabel.setFont(new Font("SimSun", Font.BOLD, 18));  // 使用宋体字体
        JButton bookButton = new JButton("订票");
        JButton queryButton = new JButton("查询机票");
        JButton refundButton = new JButton("退票");

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Book1().setVisible(true);
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Research().setVisible(true);
            }
        });

        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Refund().setVisible(true);
            }
        });

        // 设置按钮大小
        Dimension buttonSize = new Dimension(100, 50);
        bookButton.setPreferredSize(buttonSize);
        queryButton.setPreferredSize(buttonSize);
        refundButton.setPreferredSize(buttonSize);

        // 使用GridBagLayout作为布局管理器
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;  // 标题占据3列
        gbc.insets = new Insets(10, 10, 10, 10);  // 设置标题和按钮之间的间距

        frame.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // 恢复为默认值
        frame.add(bookButton, gbc);

        gbc.gridx = 1;
        frame.add(queryButton, gbc);

        gbc.gridx = 2;
        frame.add(refundButton, gbc);

        frame.setVisible(true);
    }
    /*private static void createAndShowGUI(){
        JFrame frame = new JFrame("航空机票订票系统");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        JLabel titleLabel = new JLabel("尊敬的用户，你好！欢迎使用航空机票订票系统");
        titleLabel.setFont(new Font("SimSun", Font.BOLD, 18));  // 使用宋体字体
        JButton bookButton = new JButton("订票");
        JButton queryButton = new JButton("查询机票");
        JButton refundButton = new JButton("退票");

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Book1();
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Research();
            }
        });

        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Refund();
            }
        });

        // 设置按钮大小
        Dimension buttonSize = new Dimension(100, 50);
        bookButton.setPreferredSize(buttonSize);
        queryButton.setPreferredSize(buttonSize);
        refundButton.setPreferredSize(buttonSize);

        // 使用GridBagLayout作为布局管理器
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;  // 标题占据3列
        gbc.insets = new Insets(10, 10, 10, 10);  // 设置标题和按钮之间的间距

        frame.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // 恢复为默认值
        frame.add(bookButton, gbc);

        gbc.gridx = 1;
        frame.add(queryButton, gbc);

        gbc.gridx = 2;
        frame.add(refundButton, gbc);

        frame.setVisible(true);
    }*/
}
