import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class myFrame extends JFrame implements ActionListener{

    String operation = "";
    String[] signs = {"*", "/", "+", "-"};
    JPanel buttonPanel;
    JPanel utilPanel;
    JTextField entryBox;
    JButton clearButton, enterButton, deleteButton, decimalButton, negativeButton;
    JButton[] numberButtons = new JButton[10];
    JButton[] operatorButtons = new JButton[4];
    Font myFont = new Font("Ebrima", Font.BOLD, 24);


    public myFrame(){

        entryBox = new JTextField();
        entryBox.setBounds(20, 20, 372, 60);
        entryBox.setEditable(false);
        entryBox.setFont(myFont);

        for (int i=0; i<numberButtons.length; i++){
            numberButtons[i] = new JButton();
            numberButtons[i].setText(String.valueOf(i));
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(Color.lightGray);
        }

        for (int i=0; i<operatorButtons.length; i++){
            operatorButtons[i] = new JButton();
            operatorButtons[i].setText(signs[i]);
            operatorButtons[i].setFont(myFont);
            operatorButtons[i].setFocusable(false);
            operatorButtons[i].addActionListener(this);
            operatorButtons[i].setBackground(Color.lightGray);
        }

        negativeButton = new JButton();
        negativeButton = new JButton();
        negativeButton.setText("(-)");
        negativeButton.setFont(myFont);
        negativeButton.setFocusable(false);
        negativeButton.addActionListener(this);
        negativeButton.setBackground(Color.lightGray);

        decimalButton = new JButton();
        decimalButton = new JButton();
        decimalButton.setText(".");
        decimalButton.setFont(myFont);
        decimalButton.setFocusable(false);
        decimalButton.addActionListener(this);
        decimalButton.setBackground(Color.lightGray);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(new GridLayout(4 , 4, 5, 5));
        buttonPanel.setBounds(20, 90, 372, 350);
        buttonPanel.setVisible(true);
 
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(operatorButtons[0]);

        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(operatorButtons[1]);

        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(operatorButtons[2]);

        buttonPanel.add(negativeButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(decimalButton);
        buttonPanel.add(operatorButtons[3]);

        enterButton = new JButton();
        enterButton.setFont(myFont);
        enterButton.setText("ENTER");
        enterButton.setFocusable(false);
        enterButton.setBackground(Color.lightGray);
        enterButton.addActionListener(this);
        enterButton.setPreferredSize(new Dimension(114, 60));

        clearButton = new JButton();
        clearButton.setFont(myFont);
        clearButton.setText("CE");
        clearButton.setFocusable(false);
        clearButton.setBackground(Color.lightGray);
        clearButton.addActionListener(this);
        clearButton.setPreferredSize(new Dimension(114, 60));

        deleteButton = new JButton();
        deleteButton.setFont(myFont);
        deleteButton.setText("DEL");
        deleteButton.setFocusable(false);
        deleteButton.setBackground(Color.lightGray);
        deleteButton.addActionListener(this);
        deleteButton.setPreferredSize(new Dimension(114, 60));

        utilPanel = new JPanel();
        utilPanel.setBackground(Color.GRAY);
        utilPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        utilPanel.setBounds(20, 460, 372, 100);

        utilPanel.add(deleteButton);
        utilPanel.add(enterButton);
        utilPanel.add(clearButton);
        utilPanel.setVisible(true);

        this.setTitle("Calculator App");
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        this.setLayout(null);
        this.setSize(425, 575);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(entryBox);
        this.add(buttonPanel);
        this.add(utilPanel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Check for number event
        for (int i=0; i<numberButtons.length; i++){
            if(e.getSource() == numberButtons[i]){

                entryBox.setText(entryBox.getText().concat(numberButtons[i].getText()));

                enterButton.setEnabled(true);

            }
        }

        for (int i = 0; i<4; i++) {
            if (e.getSource() == operatorButtons[i]) {

                if (!entryBox.getText().equals("")) {
                    operation = operation.concat(entryBox.getText() + ((operatorButtons[i].getText() == "-") ? "~" : operatorButtons[i].getText()));
                    System.out.println(operation);
                    entryBox.setText("");
                }

            }
        }


        //Enters a negative sign
        if (e.getSource() == negativeButton){
            if (!entryBox.getText().contains("-")){
                entryBox.setText("-" + entryBox.getText());
            }
        }

        //Enter a decimal point
        if (e.getSource() == decimalButton){
            if (!entryBox.getText().contains(".")){
                entryBox.setText(entryBox.getText().concat(decimalButton.getText()));
            }
        }

        //Deleting a single value
        if (e.getSource() == deleteButton){
            try{

                int bound = (entryBox.getText().length() - 1) > - 1 ? (entryBox.getText().length() - 1) : 0;

                entryBox.setText(entryBox.getText(0, bound));

                if (entryBox.getText() == ""){
                    deleteButton.setEnabled(false);
                }

            } catch (Exception exception){
                System.out.println("WOW");
            }
        }

        if (e.getSource() == clearButton){
            entryBox.setText("");
            operation = "";
        }

        if (e.getSource() == enterButton){

            operation += entryBox.getText();

            System.out.println(operation);

            entryBox.setText(String.valueOf(calculations.calculating(operation.split("\\+"), '+')));

            operation = "";

        }
    }
}
