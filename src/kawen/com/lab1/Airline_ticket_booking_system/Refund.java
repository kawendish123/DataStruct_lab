package kawen.com.lab1.Airline_ticket_booking_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Refund extends JFrame {
    private JTextField idField;
    private JComboBox<String> flightNumberComboBox;

    public Refund() {
        setTitle("机票退订系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        setLayout(new GridLayout(3, 2));

        idField = new JTextField(10);
        flightNumberComboBox = new JComboBox<>(new String[]{"航线号1", "航线号2", "航线号3"});

        JButton refundButton = new JButton("退票");
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refundTicket();
            }
        });

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("选择航班号:"));
        add(flightNumberComboBox);
        add(new JLabel());
        add(refundButton);
    }

    private void refundTicket() {
        String refundId = idField.getText();
        String selectedFlightNumber = (String) flightNumberComboBox.getSelectedItem();

        try (BufferedReader reader = new BufferedReader(new FileReader("booking_info.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + refundId) && line.contains("航线号: " + selectedFlightNumber)) {
                    // Skip this line (delete the entry)
                    found = true;
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "未找到匹配订单", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (FileWriter writer = new FileWriter("booking_info.txt")) {
                writer.write(fileContent.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "删除订单失败", "错误", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "订单删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "读取订单信息失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Refund();
    }
}
