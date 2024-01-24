package kawen.com.lab1.Airline_ticket_booking_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Research extends JFrame {
    private JTextField idField;
    private JTextArea resultArea;

    public Research() {
        setTitle("信息查询系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        idField = new JTextField(10);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookingInfo();
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("输入ID号:"));
        add(idField);
        add(searchButton);
        add(new JScrollPane(resultArea));
    }

    private void searchBookingInfo() {
        String idToSearch = idField.getText();
        resultArea.setText("");  // Clear previous results

        try (BufferedReader reader = new BufferedReader(new FileReader("booking_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("ID: " + idToSearch)) {

                    resultArea.append(line + "\n");
                    for (int i = 1; i < 4; i++) {
                        line = reader.readLine();
                        resultArea.append(line + "\n");
                    }

                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "查询失败", "错误", JOptionPane.ERROR_MESSAGE);
        }

        if (resultArea.getText().isEmpty()) {
            resultArea.setText("未找到匹配的信息");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Research());
    }
}
