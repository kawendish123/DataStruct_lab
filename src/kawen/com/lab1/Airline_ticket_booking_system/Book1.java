package kawen.com.lab1.Airline_ticket_booking_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Book1 extends JFrame{
    private JComboBox<String> flightNumberComboBox;  // 添加航线号选择框
//    private JTextField quantityField;
    private JTextField nameField;
    private JLabel availabilityLabel;  // 修改成员变量名为availabilityLabel
    private JComboBox<String> cabinClassComboBox;
    private JTextField idField;
    private String selectedFlightNumber;
    private Map<String, Map<String, Integer>> flightCabinAvailability;  // 存储航线号和舱位余票量的映射
    public Book1() {
        setTitle("机票订购系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        flightCabinAvailability = new HashMap<>();
        Map<String, Integer> cabinAvailability1 = new HashMap<>();
        cabinAvailability1.put("经济舱", 10);
        cabinAvailability1.put("商务舱", 120);
        cabinAvailability1.put("头等舱", 110);
        flightCabinAvailability.put("航线号1", cabinAvailability1);

        Map<String, Integer> cabinAvailability2 = new HashMap<>();
        cabinAvailability2.put("经济舱", 30);
        cabinAvailability2.put("商务舱", 20);
        cabinAvailability2.put("头等舱", 10);
        flightCabinAvailability.put("航线号2", cabinAvailability2);

        Map<String, Integer> cabinAvailability3 = new HashMap<>();
        cabinAvailability3.put("经济舱", 4);
        cabinAvailability3.put("商务舱", 2);
        cabinAvailability3.put("头等舱", 1);
        flightCabinAvailability.put("航线号3", cabinAvailability3);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {

        flightNumberComboBox = new JComboBox<>(new String[]{"航线号1", "航线号2", "航线号3"});
//        quantityField = new JTextField(10);
        nameField = new JTextField(10);

        String[] cabinClasses = {"经济舱", "商务舱", "头等舱"};
        cabinClassComboBox = new JComboBox<>(cabinClasses);

        idField = new JTextField(10);

        JButton bookButton = new JButton("订票");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTicket();
            }
        });

        availabilityLabel = new JLabel("剩余票数：");

        // 使用GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // 靠左对齐


        add(new JLabel("航线号:"), gbc);
        gbc.gridx = 1;
        add(flightNumberComboBox, gbc);
        flightNumberComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedFlightNumber = (String) e.getItem();  // 获取用户选择的航线号
                }
            }
        });

        /*gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("订购数量:"), gbc);
        gbc.gridx = 1;
        add(quantityField, gbc);*/

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("姓名:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("舱位等级:"), gbc);
        gbc.gridx = 1;
        add(cabinClassComboBox, gbc);
        cabinClassComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCabin = (String) e.getItem();  // 获取舱位等级
                    updateAvailabilityLabel(selectedFlightNumber, selectedCabin);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(availabilityLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(bookButton, gbc);
    }

    private void bookTicket() {
        // 获取文本框的输入值
        String selectedFlightNumber = (String) flightNumberComboBox.getSelectedItem();
        //        String quantity = quantityField.getText();
        String name = nameField.getText();
        String cabinClass = (String) cabinClassComboBox.getSelectedItem();
        String id = idField.getText();

        // 在这里可以执行订票的相关逻辑，例如打印订单信息或发送请求到服务器等
        // 这里只是简单地弹出一个对话框显示订票信息
        String message = "\nID: " + id +
                "\n姓名: " + name +
                "\n舱位等级: " + cabinClass +
                "\n航线号: " + selectedFlightNumber;
        updateAvailabilityAfterBooking(selectedFlightNumber,cabinClass);

        try (FileWriter writer = new FileWriter("booking_info.txt", true)) {
            writer.write(message);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, message, "订票信息", JOptionPane.INFORMATION_MESSAGE);
    }
    public void updateAvailabilityLabel(String selectedFlightNumber, String selectedCabin) {
        if (flightCabinAvailability.containsKey(selectedFlightNumber)) {
            Map<String, Integer> cabinMap = flightCabinAvailability.get(selectedFlightNumber);
            if (cabinMap.containsKey(selectedCabin)) {
                int remainingTickets = cabinMap.get(selectedCabin);
                availabilityLabel.setText("剩余票数: " + remainingTickets);
            } else {
                availabilityLabel.setText("剩余票数: 未知舱位");
            }
        } else {
            availabilityLabel.setText("剩余票数: 未知航线");
        }
    }

    private void updateAvailabilityAfterBooking(String selectedFlightNumber, String selectedCabin) {
        if (flightCabinAvailability.containsKey(selectedFlightNumber)) {
            Map<String, Integer> cabinMap = flightCabinAvailability.get(selectedFlightNumber);
            if (cabinMap.containsKey(selectedCabin)) {
                int remainingTickets = cabinMap.get(selectedCabin);
                if (remainingTickets > 0) {
                    // 更新余票数量
                    cabinMap.put(selectedCabin, remainingTickets - 1);
                    // 刷新显示
                    updateAvailabilityLabel(selectedFlightNumber, selectedCabin);
                } else {
                    JOptionPane.showMessageDialog(this, "该舱位余票不足", "提示", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "未知舱位", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "未知航线", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }
}
