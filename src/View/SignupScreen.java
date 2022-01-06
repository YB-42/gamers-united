package View;

import Controller.DBController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupScreen extends JFrame {
    Container container=getContentPane();
    JPanel panel=new JPanel();
    JLabel nameLabel =new JLabel("NAME");
    JLabel emailLabel =new JLabel("EMAIL");
    JLabel signupLabel=new JLabel("SIGN UP");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField emailTextField =new JTextField();
    JTextField nameTextField =new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton signUpButton =new JButton("SIGNUP");
    JButton loginButton=new JButton("LOGIN");

    public SignupScreen(){
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
        signupLabel.setBounds(350,30,100,30);
        nameLabel.setBounds(250,80,100,30);
        emailLabel.setBounds(250,150,100,30);
        passwordLabel.setBounds(250,220,100,30);
        nameTextField.setBounds(350,80,220,30);
        emailTextField.setBounds(350,150,220,30);
        passwordField.setBounds(350,220,220,30);
        loginButton.setBounds(350,300,100,30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    dispose();
                    LoginScreen frame=new LoginScreen();
                    frame.setTitle("Login");
                    frame.setVisible(true);
                    frame.setBounds(10,10,800,600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);


            }
        });
        signUpButton.setBounds(470,300,100,30);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBController db=new DBController();
                db.signup(nameTextField.getText(),emailTextField.getText(),passwordField.getText());
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
        panel.add(signupLabel);
        panel.add(nameLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(nameTextField);
        panel.add(emailTextField);
        panel.add(passwordField);
        panel.add(signUpButton);
        panel.add(loginButton);
        JLabel label=new JLabel("Gamers United",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,30));
        container.add(label,BorderLayout.NORTH);
        container.add(panel,SwingConstants.CENTER);
    }

}
