package com.hashtable;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Main {
    public static void main(String[] args) {

        HashTable Table = new HashTable(1009);

        Table.insertEntry("Amr", "23011400");
        Table.insertEntry("Yasser", "23011507");
        Table.insertEntry("Hossam", "23011471");
        Table.insertEntry("Moaz", "23011552");
        Table.insertEntry("Jamal", "23011000");
        Table.insertEntry("Belal", "01025478");

        Table.updateEntry("Jamal", "01204578");

        Table.removeEntry("Belal");

        

        JFrame frame = new JFrame();
        frame.setSize(854, 480);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20));

        JPanel header = new JPanel();
        header.setBackground(new Color(34, 85, 44));
        header.setBounds(0, 0, 854, 30);
        header.setLayout(null);

        JPanel content = new JPanel();
        content.setBackground(new Color(239, 240, 232));
        content.setBounds(0, 30, 854, 450);
        content.setLayout(null);
        JTextField message = createTextField(Color.BLACK, content.getBackground());
        message.setEditable(false);
        message.setBorder(null);

        JButton insert = createButton("Insert", header.getBackground(), new Color(184, 218, 186));
        insert.setBounds(50, 0, 100, 30);
        JButton remove = createButton("Remove", header.getBackground(), new Color(184, 218, 186));
        remove.setBounds(insert.getX()+insert.getWidth()+50, 0, 100, 30);
        JButton update = createButton("Update", header.getBackground(), new Color(184, 218, 186));
        update.setBounds(remove.getX() + remove.getWidth()+50, 0, 100, 30);
        JButton search = createButton("Search", header.getBackground(), new Color(184, 218, 186));
        search.setBounds(update.getX() + update.getWidth()+50, 0, 100, 30);
        JButton print = createButton("Print", header.getBackground(), new Color(184, 218, 186));
        print.setBounds(search.getX() + search.getWidth()+50, 0, 100, 30);
        JButton exit = createButton("X", header.getBackground(), new Color(212, 62, 62));
        exit.setBounds(809, 0, 45, 30);

        JLabel name1 = createLabel("Name");
        name1.setBounds(353, 90, 43, 27);
        JTextField name2 = createTextField(Color.BLACK, content.getBackground());
        name2.setBounds(443, 90, 150, 27);

        JLabel phoneNumber1 = createLabel("Phone Number");
        phoneNumber1.setBounds(318, 167, 113, 27);
        JTextField phoneNumber2 = createTextField(Color.BLACK, content.getBackground());
        phoneNumber2.setBounds(443, 167, 150, 27);

        insert.addActionListener(e -> {
            content.removeAll();
            content.repaint();
            content.add(name1);
            name2.setText(null);
            content.add(name2);
            content.add(phoneNumber1);
            phoneNumber2.setText(null);
            content.add(phoneNumber2);
            JButton insertButton = createButton("Insert", header.getBackground(), new Color(184, 218, 186));
            insertButton.setBounds(353, 250, 200, 37);
            insertButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.insertEntry(name, phoneNumber)) {
                        message.setText(name + " is successfully Inserted");
                        message.setForeground(Color.GREEN);
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    } else {
                        message.setText("Table is full");
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    }
                    name2.setText("");
                    phoneNumber2.setText("");
                }
            });
            content.add(insertButton);
            content.repaint();
        });
        header.add(insert);

        remove.addActionListener(e -> {
            content.removeAll();
            content.repaint();
            content.add(name1);
            name2.setText(null);
            content.add(name2);
            JButton removeButton = createButton("Remove", header.getBackground(), new Color(184, 218, 186));
            removeButton.setBounds(353, 250, 200, 37);
            removeButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText().stripIndent();
                    if (Table.removeEntry(name)) {
                        message.setText(name + " is successfully Removed");
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setForeground(Color.RED);
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    }
                    name2.setText("");
                }
            });
            content.add(removeButton);
            content.repaint();
        });
        header.add(remove);

        update.addActionListener(e -> {
            content.removeAll();
            content.repaint();
            content.add(name1);
            name2.setText(null);
            content.add(name2);
            content.add(phoneNumber1);
            phoneNumber2.setText(null);
            content.add(phoneNumber2);
            JButton updateButton = createButton("Update", header.getBackground(), new Color(184, 218, 186));
            updateButton.setBounds(353, 250, 200, 37);
            updateButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText().stripIndent();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.updateEntry(name, phoneNumber)) {
                        message.setText(name + " updated");
                        message.setForeground(Color.GREEN);
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setForeground(Color.RED);
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    }
                    name2.setText("");
                    phoneNumber2.setText("");
                }
            });
            content.add(updateButton);
            content.repaint();
        });
        header.add(update);

        search.addActionListener(e -> {
            content.removeAll();
            content.repaint();
            content.add(name1);
            name2.setText(null);
            content.add(name2);
            JButton searchButton = createButton("Search", header.getBackground(), new Color(184, 218, 186));
            searchButton.setBounds(353, 250, 200, 37);
            searchButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText().stripIndent();
                    if (Table.searchEntry(name) != null) {
                        message.setText(name + "’s phone number is " + Table.searchEntry(name));
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setForeground(Color.RED);
                        message.setBounds(318, 350, 250, 27);
                        content.add(message);
                        content.repaint();
                    }
                    name2.setText("");
                }
            });
            content.add(searchButton);
            content.repaint();
        });
        header.add(search);

        print.addActionListener(e -> {
            name2.setText(null);
            phoneNumber2.setText(null);
            frame.setVisible(false);
            Object[][] data = Table.getTableData();
            String[] columns = { "Key", "Index", "Value" };
            JFrame newFrame = new JFrame();
            newFrame.setSize(frame.getWidth(), frame.getHeight());
            newFrame.setLayout(null);
            newFrame.setUndecorated(true);
            newFrame.setLocationRelativeTo(null);
            newFrame.setResizable(false);
            newFrame.setVisible(true);
            newFrame.setShape(new RoundRectangle2D.Double(0, 0, newFrame.getWidth(), newFrame.getHeight(), 20, 20));

            JPanel newHeader = new JPanel();
            newHeader.setBackground(header.getBackground());
            newHeader.setBounds(0, 0, newFrame.getWidth(), 30);
            newHeader.setLayout(null);
            JButton close = createButton("X", header.getBackground(), new Color(212, 62, 62));
            close.setBounds(newFrame.getWidth() - close.getPreferredSize().width, 0, close.getPreferredSize().width,
                    newHeader.getHeight());
            close.addActionListener(e1 -> {
                newFrame.setVisible(false);
                frame.setVisible(true);
            });
            newHeader.add(close);
            newFrame.add(newHeader);

            JTable display = new JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            centerRenderer.setVerticalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < display.getColumnCount(); i++) {
                display.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            display.setFont(new Font("MV Boli", Font.PLAIN, 14));
            display.setRowHeight(30);
            display.setFillsViewportHeight(true);
            display.setBackground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(display);
            scrollPane.setBounds(10, 50, newFrame.getWidth() - 20, newFrame.getHeight() - 60);
            newFrame.add(scrollPane);
            newFrame.repaint();
        });
        header.add(print);

        exit.addActionListener(e -> System.exit(0));
        header.add(exit);

        frame.add(header);
        frame.add(content);
        frame.setVisible(true);
    }

    public static JButton createButton(String text, Color background, Color mouseEntered) {
        JButton button = new JButton(text);
        button.setFont(new Font("MV Boli", Font.PLAIN, 16));
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(mouseEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(background);
            }
        });
        return button;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        label.setForeground(Color.BLACK);
        return label;
    }

    public static JTextField createTextField(Color foreground, Color background) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("MV Boli", Font.PLAIN, 14));
        textField.setForeground(foreground);
        textField.setBackground(background);
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }
}
