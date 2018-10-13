package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel {
    protected JTextArea textArea;

    public Main() {
        super(new GridBagLayout());
        textArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton submitButton = new JButton("Create Test");
        this.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textArea.getText();
                //Send to parser
            }
        });
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(submitButton, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(scrollPane, constraints);
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Main());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createWindow();
            }
        });
    }

}
