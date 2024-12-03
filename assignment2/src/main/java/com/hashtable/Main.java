package com.hashtable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // To work without static key word and to keep functionality of create other
        // tables dynamically.
        HashTable Table = new HashTable(1009);
        Table.insertEntry("Amr", "23011400");
        Table.insertEntry("Yasser", "23011507");
        Table.insertEntry("Hossam", "23011471");
        Table.insertEntry("Moaz", "23011552");

        JFrame frame = new JFrame();
        frame.setSize(854, 480);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 20, 20));

        JPanel header = new JPanel();
        header.setBackground(new Color(30, 30, 30));
        header.setBounds(0, 0, 854, 30);
        header.setLayout(null);

        JPanel input = new JPanel();
        input.setBackground(new Color(60, 60, 60));
        input.setBounds(0, 30, 412, 450);
        input.setLayout(null);

        JPanel output = new JPanel();
        output.setBackground(new Color(60, 60, 60));
        output.setBounds(input.getWidth() + 1, 30, 441, 450);
        output.setLayout(null);
        JTextField message = createTextField(Color.WHITE, output.getBackground());
        message.setEditable(false);
        message.setBorder(null);

        JButton insert = createButton("Insert", header.getBackground(), Color.LIGHT_GRAY);
        insert.setBounds(0, 0, insert.getPreferredSize().width, 30);
        JButton remove = createButton("Remove", header.getBackground(), Color.LIGHT_GRAY);
        remove.setBounds(insert.getWidth(), 0, remove.getPreferredSize().width, 30);
        JButton update = createButton("Update", header.getBackground(), Color.LIGHT_GRAY);
        update.setBounds(remove.getX() + remove.getWidth(), 0, update.getPreferredSize().width, 30);
        JButton search = createButton("Search", header.getBackground(), Color.LIGHT_GRAY);
        search.setBounds(update.getX() + update.getWidth(), 0, search.getPreferredSize().width, 30);
        JButton print = createButton("Print", header.getBackground(), Color.LIGHT_GRAY);
        print.setBounds(search.getX() + search.getWidth(), 0, print.getPreferredSize().width, 30);
        JButton exit = createButton("X", header.getBackground(), Color.RED);
        exit.setBounds(809, 0, 45, 30);

        JLabel name1 = createLabel("Name");
        name1.setBounds(116, 90, 43, 27);
        JTextField name2 = createTextField(Color.LIGHT_GRAY, output.getBackground());
        name2.setBounds(206, 90, 150, 27);

        JLabel phoneNumber1 = createLabel("Phone Number");
        phoneNumber1.setBounds(81, 167, 113, 27);
        JTextField phoneNumber2 = createTextField(Color.LIGHT_GRAY, output.getBackground());
        phoneNumber2.setBounds(206, 167, 150, 27);

        insert.addActionListener(e -> {
            input.removeAll();
            output.removeAll();
            output.repaint();
            input.add(name1);
            input.add(name2);
            input.add(phoneNumber1);
            input.add(phoneNumber2);
            JButton insertButton = createButton("Insert", header.getBackground(), Color.LIGHT_GRAY);
            insertButton.setBounds(101, 300, 250, 37);
            insertButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.insertEntry(name, phoneNumber)) {
                        message.setText(name + " is successfully Inserted");
                        message.setBounds(95, 27, 250, 27);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Table is full");
                        message.setBounds(95, 27, 250, 27);
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
            JButton removeButton = createButton("Remove", header.getBackground(), Color.LIGHT_GRAY);
            removeButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    removeButton.getPreferredSize().height);
            removeButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText();
                    if (Table.removeEntry(name)) {
                        message.setText(name + " is successfully Removed");
                        message.setBounds(95, 27, 250, 27);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(95, 27, 250, 27);
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
            JButton updateButton = createButton("Update", header.getBackground(), Color.LIGHT_GRAY);
            updateButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    updateButton.getPreferredSize().height);
            updateButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty() && !phoneNumber2.getText().isEmpty()) {
                    String name = name2.getText();
                    String phoneNumber = phoneNumber2.getText();
                    if (Table.updateEntry(name, phoneNumber)) {
                        message.setText(name + " updated");
                        message.setBounds(95, 27, 250, 27);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(95, 27, 250, 27);
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
            JButton searchButton = createButton("Search", header.getBackground(), Color.LIGHT_GRAY);
            searchButton.setBounds((input.getWidth() / 2) - 105, input.getHeight() * 2 / 3, 250,
                    searchButton.getPreferredSize().height);
            searchButton.addActionListener(e1 -> {
                if (!name2.getText().isEmpty()) {
                    String name = name2.getText();
                    if (Table.searchEntry(name) != null) {
                        message.setText(name + "’s phone number is " + Table.searchEntry(name));
                        message.setBounds(95, 27, 250, 27);
                        output.add(message);
                        output.repaint();
                    } else {
                        message.setText("Contact is not found");
                        message.setBounds(95, 27, 250, 27);
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
            JButton close = createButton("X", header.getBackground(), Color.RED);
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
        frame.add(input);
        frame.add(output);
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
        label.setForeground(Color.WHITE);
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