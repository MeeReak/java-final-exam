import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoApp {

    private JFrame frame;
    private JLabel idLabel, lastNameLabel, firstNameLabel, phoneLabel;
    private JTextField idField, lastNameField, firstNameField, phoneField;
    private JButton previousButton, nextButton;

    private String[][] customerData = {
            {"1", "Chenda", "Sovisal", "092888999"},
            {"2", "Kom", "Lina", "092000999"},
            {"3", "Chan", "Seyha", "092777666"}
    };

    private int currentCustomerIndex = 0;

    public CustomerInfoApp() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Customer Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        idLabel = new JLabel("ID:");
        idField = new JTextField(customerData[currentCustomerIndex][0]);
        idField.setEditable(false);
        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(customerData[currentCustomerIndex][1]);
        lastNameField.setEditable(false);
        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(customerData[currentCustomerIndex][2]);
        firstNameField.setEditable(false);
        phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField(customerData[currentCustomerIndex][3]);
        phoneField.setEditable(false);

        previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousCustomer();
            }
        });

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCustomer();
            }
        });

        frame.add(idLabel);
        frame.add(idField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(firstNameLabel);
        frame.add(firstNameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(previousButton);
        frame.add(nextButton);

        frame.setVisible(true);
    }

    private void previousCustomer() {
        if (currentCustomerIndex > 0) {
            currentCustomerIndex--;
            updateCustomerInfo();
        }
    }

    private void nextCustomer() {
        if (currentCustomerIndex < customerData.length - 1) {
            currentCustomerIndex++;
            updateCustomerInfo();
        }
    }

    private void updateCustomerInfo() {
        idField.setText(customerData[currentCustomerIndex][0]);
        lastNameField.setText(customerData[currentCustomerIndex][1]);
        firstNameField.setText(customerData[currentCustomerIndex][2]);
        phoneField.setText(customerData[currentCustomerIndex][3]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerInfoApp();
            }
        });
    }
}