package View;

import Controller.DBController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordScreen extends JFrame {
    Container container=getContentPane();
    JPanel panel=new JPanel();
    JLabel nameLabel =new JLabel("EMAIL");
    JLabel emailLabel =new JLabel("OLD PASSWORD");
    JLabel passwordLabel=new JLabel("NEW PASSWORD");
    JTextField emailTextField =new JTextField();
    JTextField oldPasswordTextField =new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton changePasswordButton =new JButton("Submit");

    public ChangePasswordScreen(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    public void setLayoutManager()
    {
        container.setLayout(new BorderLayout());
        panel.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        nameLabel.setBounds(250,80,100,30);
        emailLabel.setBounds(250,150,100,30);
        passwordLabel.setBounds(250,220,100,30);
        emailTextField.setBounds(350,80,150,30);
        oldPasswordTextField.setBounds(350,150,150,30);
        passwordField.setBounds(350,220,150,30);
        changePasswordButton.setBounds(350,300,100,30);
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBController db=new DBController();
                db.changePassword(emailTextField.getText(),oldPasswordTextField.getText(),passwordField.getText());
                dispose();
                LoginScreen frame=new LoginScreen();
                frame.setTitle("Login");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });


    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        panel.add(nameLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(emailTextField);
        panel.add(oldPasswordTextField);
        panel.add(passwordField);
        panel.add(changePasswordButton);
        JLabel label=new JLabel("Gamers United",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,30));
        container.add(label,BorderLayout.NORTH);
        container.add(panel,SwingConstants.CENTER);
    }

}
