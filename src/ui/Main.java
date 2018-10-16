package ui;

import ast.PROGRAM;
import ast.QuestionSetTest;
import libs.Tokenizer;
import libs.PDFConverter;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Main extends JPanel {
    public static Map<String,Object> symbolTable = new HashMap<>();
    private String inputText;
    private PROGRAM p;

    protected JTextArea textArea;

    public Main() {
        super(new GridBagLayout());
        List<String> literals = Arrays.asList("MAKEPHRASE", "MAKEQUESTION", "MAKESET", "MAKETEST", "MAKEOUTPUT", "{", "}", ",", "[", "]");
        textArea = new JTextArea(30, 60);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton submitButton = new JButton("Create Test");
        this.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Running!");
                inputText = textArea.getText();
                if (inputText.length() < 1) {
                    JOptionPane.showMessageDialog(textArea, "Empty Input!");
                    return;
                }
                symbolTable.clear();
                //Send to parser
                Tokenizer.makeTokenizer(inputText,literals);
                p = new PROGRAM();
                p.parse();
                p.evaluate();
                // From here when it reaches the place where it parses the token that calls to make
                // the test, the logic there will compile the completed question objects and send to pdf maker
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
