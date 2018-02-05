package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Url {
    private JTextField textField1;
    private JButton button1;
    private static   JTextField textField2;

    public Url() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main(textField1.toString());
            }
        });
    }
    public static void display(String l){
        textField2.setText(l);
    }
    public static void main(String args){
        new Url();

    }
}
