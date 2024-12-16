package cop2805;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FinalProjectGUI extends JFrame {
    JTextField textField;
    JLabel label2;

    public FinalProjectGUI(){
        super();
        init();
    }
    private void init(){
        int frameWidth=500;
        int frameHeight=150;
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle(" Fibonacci Sequence");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add a JLabel that says Welcome
        this.setLayout(new GridLayout(2,2));

        //Creates and adds a label
        JLabel label = new JLabel(" Enter Fibonacci Number");
        this.add(label);

        // Creates and adds a text field
        textField = new JTextField();
        this.add(textField);

        //Creates and adds a button
        JButton calculateButton = new JButton("Calculate");
        this.add(calculateButton);
        MyActionListener action = new MyActionListener(this);
        calculateButton.addActionListener(action);

        //Creates and adds a label
        label2 = new JLabel(" Server Answer: "+ MyActionListener.serverResponse());
        this.add(label2);

        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        // sets frame to the middle of the screen
        this.setBounds((int) screenSize.getWidth()/2 - frameWidth/2,
                (int)screenSize.getHeight()/2-frameHeight/2, frameWidth, frameHeight);

        this.setVisible(true);
    }

    public static void constructGUI(){
        FinalProjectGUI newWindow = new FinalProjectGUI();
    }
}
class MyActionListener implements ActionListener {
    FinalProjectGUI GUI;
    private static int inputValue;
    static long serverResponse;

    public MyActionListener(FinalProjectGUI frame){
        GUI = frame;
    }
    public static long serverResponse(){
        return serverResponse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        // try catch for user error handling, making sure the input is a number
        try {
            inputValue = Integer.parseInt(GUI.textField.getText());
            if(inputValue<0){
                JOptionPane.showMessageDialog(null,
                        "Please enter only positive numbers");
                System.out.println("User entered an invalid number");

            }else if(inputValue>=93) {
                JOptionPane.showMessageDialog(null," Can not Calculate numbers larger than 92");
            }
        }catch(NumberFormatException E){
            JOptionPane.showMessageDialog(null,
                    "Please only enter numbers");
            System.out.println("User entered an invalid text");
        }
        // only send valid numbers to the server
        if(inputValue>0 && inputValue<93) {
            serverResponse = FinalProjectClient.toSever(inputValue);
            GUI.label2.setText(" Server Answer: " + MyActionListener.serverResponse());
        }

    }
}