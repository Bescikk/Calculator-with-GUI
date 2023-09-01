import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[13];
    JButton addButton,subButton,mulButton,divButton, pow2Button, powButton, perButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    ImageIcon icon;
    JButton historyButton;

    Font myFont = new Font("Arial",Font.BOLD,30);

    double num1=0, num2=0, result=0;
    char operator;
    boolean equPressed = false;

    private final ArrayList<String> historyList = new ArrayList<>();


    public Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 500);
        frame.setMinimumSize(new Dimension(420, 500));
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(0, 50));
        textField.setFont(myFont);
        textField.setEditable(false);

        icon = new ImageIcon("src/resources/history.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        historyButton = new JButton(icon);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("C");
        clrButton = new JButton("CE");
        negButton = new JButton("(-)");
        pow2Button = new JButton("^2");
        powButton = new JButton("^");
        perButton = new JButton("%");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = powButton;
        functionButtons[10] = pow2Button;
        functionButtons[11] = perButton;
        functionButtons[12] = historyButton;

        historyButton.addActionListener(new ActionListener() {
            private JDialog dialog = null;
            @Override
            public void actionPerformed(ActionEvent e) {

                if (dialog != null) {
                    dialog.dispose();
                }
                dialog = createDialog();
                dialog.setVisible(true);
            }
            private JDialog createDialog() {
                JDialog dialog = new JDialog(frame, "History", false);
                dialog.setSize(300, 300);
                dialog.setLocation(frame.getLocation().x + frame.getWidth(), frame.getLocation().y);

                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                for(String operation : historyList) {
                    textArea.append(operation + "\n");
                }

                JScrollPane scrollPane = new JScrollPane(textArea);
                dialog.add(scrollPane);

                return dialog;
            }
        });

        for (int i = 0; i < 13; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel(new GridLayout(5,4,10,10));
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(5,4,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(powButton);
        panel.add(pow2Button);
        panel.add(perButton);
        panel.add(historyButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        bottomPanel.add(negButton);
        bottomPanel.add(delButton);
        bottomPanel.add(clrButton);

        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    private void checkForError() {
        if ("Error".equals(textField.getText())) {
            textField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkForError();

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (textField.getText().isEmpty() || "Error".equals(textField.getText())) {
            return;
        }

        if (e.getSource() == decButton && !textField.getText().contains(".")) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            try {
                operator = '+';
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
            equPressed = false;
        }

        if (e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
            equPressed = false;
        }

        if (e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
            equPressed = false;
        }

        if (e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
            equPressed = false;
        }

        if (e.getSource() == powButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '^';
            textField.setText("");
            equPressed = false;
        }

        if (e.getSource() == equButton){
            num2 = Double.parseDouble(textField.getText());
            if (operator != '\0') {
                try {
                    switch (operator) {
                        case '+' -> result = num1 + num2;
                        case '-' -> result = num1 - num2;
                        case '*' -> result = num1 * num2;
                        case '/' -> {
                            if (num2 == 0) throw new ArithmeticException();
                            result = num1 / num2;
                        }
                        case '^' -> {
                            if (num1 < 0 && num2 < 1 && num2 > -1) throw new ArithmeticException();
                            result = Math.pow(num1, num2);
                        }
                    }
                    textField.setText(String.valueOf(result));
                    historyList.add(num1 + " " + operator + " " + num2 + " = " + result);
                    num1 = 0;
                    operator = '\0';
                } catch (ArithmeticException ex) {
                    textField.setText("Error");
                }
            }
            equPressed = true;
        }

        if (e.getSource() == clrButton){
            textField.setText("");
        }

        if (e.getSource() == delButton){
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length()-1; i++) {
                textField.setText(textField.getText()+string.charAt(i));
            }
        }

        if (e.getSource() == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp*=-1;
            textField.setText(String.valueOf(temp));
        }

        if (e.getSource() == pow2Button){
            double temp = Double.parseDouble(textField.getText());
            temp = Math.pow(temp, 2);
            textField.setText(String.valueOf(temp));
            equPressed = false;
        }

        if (e.getSource() == perButton) {
            if (equPressed) {
                equPressed = false;
                return;
            }
            if (textField.getText().isEmpty() || num1 == 0) {
                textField.setText("0");
            } else {
                num2 = Double.parseDouble(textField.getText());
                num2 = (num1 * num2) / 100;
                textField.setText(String.valueOf(num2));
            }
        }
    }
}