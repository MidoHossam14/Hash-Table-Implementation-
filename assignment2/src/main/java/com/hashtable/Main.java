package com.hashtable;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {

        // To work without static key word and to keep functionality of create other tables dynamically.
        HashTable Table = new HashTable(1009);

        JFrame frame = new JFrame();
        frame.setSize(854, 480);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20));

        JPanel header = new JPanel();
        header.setBackground(new Color(30, 30, 30));
        header.setBounds(0, 0, frame.getWidth(), 50);
        header.setLayout(null);

        JPanel input = new JPanel();
        input.setBackground(new Color(60, 60, 60));
        input.setBounds(0, header.getHeight(), 412, frame.getHeight() - header.getHeight());
        input.setLayout(null);

        JPanel output = new JPanel();
        output.setBackground(new Color(60, 60, 60));
        output.setBounds(input.getWidth() + 1, header.getHeight(), frame.getWidth(),
                frame.getHeight() - header.getHeight());
        output.setLayout(null);
        JLabel message = createLabel(null);

        JButton insert = createButton("Insert", header.getBackground());
        insert.setBounds(0, 0, insert.getPreferredSize().width, header.getHeight());
        JButton remove = createButton("Remove", header.getBackground());
        remove.setBounds(insert.getWidth(), 0, remove.getPreferredSize().width, header.getHeight());
        JButton update = createButton("Update", header.getBackground());
        update.setBounds(remove.getX() + remove.getWidth(), 0, update.getPreferredSize().width, header.getHeight());
        JButton search = createButton("Search", header.getBackground());
        search.setBounds(update.getX() + update.getWidth(), 0, search.getPreferredSize().width, header.getHeight());
        JButton print = createButton("Print", header.getBackground());
        print.setBounds(search.getX() + search.getWidth(), 0, print.getPreferredSize().width, header.getHeight());
        JButton exit = createButton("X", header.getBackground());
        exit.setBounds(frame.getWidth() - exit.getPreferredSize().width, 0, exit.getPreferredSize().width,
                header.getHeight());

        JLabel name1 = createLabel("Name");
        name1.setBounds((input.getWidth() / 3) - (name1.getPreferredSize().width / 2), input.getHeight() / 5,
                name1.getPreferredSize().width,
                name1.getPreferredSize().height);
        JTextField name2 = createTextField(input.getBackground());
        name2.setBounds(input.getWidth() / 2, name1.getY(), 150, name1.getHeight());

        JLabel phoneNumber1 = createLabel("Phone Number");
        phoneNumber1.setBounds((input.getWidth() / 3) - (phoneNumber1.getPreferredSize().width / 2),
                name1.getY() + name1.getHeight() + 50,
                phoneNumber1.getPreferredSize().width,
                phoneNumber1.getPreferredSize().height);
        JTextField phoneNumber2 = createTextField(input.getBackground());
        phoneNumber2.setBounds(input.getWidth() / 2, phoneNumber1.getY(), 150, phoneNumber1.getHeight());

        insert.addActionListener(e -> {
            input.removeAll();
            output.removeAll();
            output.repaint();
            input.add(name1);
            input.add(name2);
            input.add(phoneNumber1);
            input.add(phoneNumber2);
            JButton insertButton = createButton("Insert", header.getBackground());
            insertButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    insertButton.getPreferredSize().height);
            insertButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.insertEntry(name, phoneNumber)) {
                        message.setText(name + " is successfully Inserted");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Table is full");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    }
                    name2.setText("");
                    phoneNumber2.setText("");
                }
            });
            input.add(insertButton);
            input.repaint();
        });
        header.add(insert);

        remove.addActionListener(e -> {
            input.removeAll();
            output.removeAll();
            output.repaint();
            input.add(name1);
            input.add(name2);
            JButton removeButton = createButton("Remove", header.getBackground());
            removeButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    removeButton.getPreferredSize().height);
            removeButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText();
                    if (Table.removeEntry(name)) {
                        message.setText(name + " is successfully Removed");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    }
                    name2.setText("");
                }
            });
            input.add(removeButton);
            input.repaint();
        });
        header.add(remove);

        update.addActionListener(e -> {
            input.removeAll();
            output.removeAll();
            output.repaint();
            input.add(name1);
            input.add(name2);
            input.add(phoneNumber1);
            input.add(phoneNumber2);
            JButton updateButton = createButton("Update", header.getBackground());
            updateButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    updateButton.getPreferredSize().height);
            updateButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.updateEntry(name, phoneNumber)) {
                        message.setText(name + " updated");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    }
                    name2.setText("");
                    phoneNumber2.setText("");
                }
            });
            input.add(updateButton);
            input.repaint();
        });
        header.add(update);

        search.addActionListener(e -> {
            input.removeAll();
            output.removeAll();
            output.repaint();
            input.add(name1);
            input.add(name2);
            JButton searchButton = createButton("Search", header.getBackground());
            searchButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    searchButton.getPreferredSize().height);
            searchButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText();
                    if (Table.searchEntry(name) != null) {
                        message.setText("Phone Number of " + name + " is " + Table.searchEntry(name));
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(output.getWidth() / 6, message.getPreferredSize().height,
                                message.getPreferredSize().width, message.getPreferredSize().height);
                        output.add(message);
                        output.repaint();
                    }
                    name2.setText("");
                }
            });
            input.add(searchButton);
            input.repaint();
        });
        header.add(search);

        print.addActionListener(e -> {
            frame.setVisible(false);
            Object[][] data = Table.getTableData();
            String[] columns = { "Index", "Key", "Value" };
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
            JButton close = createButton("X", header.getBackground());
            close.setBounds(newFrame.getWidth() - close.getPreferredSize().width, 0, close.getPreferredSize().width,
                    newHeader.getHeight());
            close.addActionListener(e1 -> {
                newFrame.setVisible(false);
                frame.setVisible(true);
            });
            newHeader.add(close);
            newFrame.add(newHeader);

            // Mohamed Hossam Editing : dispaly instead of table
            JTable display = new JTable(data, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            display.setFont(new Font("MV Boli", Font.PLAIN, 14));
            display.setRowHeight(30);
            display.setFillsViewportHeight(true);
            display.setBackground(Color.LIGHT_GRAY);
            JScrollPane scrollPane = new JScrollPane(display);
            scrollPane.setBounds(10, 50, newFrame.getWidth() - 20, newFrame.getHeight() - 60);
            newFrame.add(scrollPane);
            newFrame.repaint();
        });
        header.add(print);

        exit.addActionListener(e -> System.exit(0));
        header.add(exit);

        frame.add(header);
        frame.add(input);
        frame.add(output);
        frame.setVisible(true);
    }

    public static JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(new Font("MV Boli", Font.PLAIN, 16));
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    public static JTextField createTextField(Color background) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("MV Boli", Font.PLAIN, 14));
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setBackground(background);
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }
}