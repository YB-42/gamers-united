package View;

import Controller.DBController;
import Model.User;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    Container container=getContentPane();
    JPanel panel=new JPanel();
    JLabel emailLabel =new JLabel("EMAIL");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel loginLabel=new JLabel("LOGIN");
    JTextField emailTextField =new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton signUpButton =new JButton("SIGNUP");

    public LoginScreen(){
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
        loginLabel.setBounds(350,30,100,30);
        emailLabel.setBounds(250,150,100,30);
        passwordLabel.setBounds(250,220,100,30);
        emailTextField.setBounds(350,150,220,30);
        passwordField.setBounds(350,220,220,30);
        loginButton.setBounds(350,300,100,30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBController db=new DBController();
                User user=null;
                user=db.login(emailTextField.getText(),passwordField.getText());
                if(user!=null){
                    dispose();
                    HomeScreen frame=new HomeScreen(user);
                    frame.setTitle("Home");
                    frame.setVisible(true);
                    frame.setBounds(10,10,800,600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                }
            }
        });
        signUpButton.setBounds(470,300,100,30);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignupScreen frame=new SignupScreen();
                frame.setTitle("Signup");
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
        panel.add(loginLabel);
        panel.add(emailLabel);
        panel.add(passwordLabel);
        panel.add(emailTextField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);
        JLabel label=new JLabel("Gamers United",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,30));
        container.add(label,BorderLayout.NORTH);
        container.add(panel,SwingConstants.CENTER);
    }

    public static void main(String[]args){
        LoginScreen frame=new LoginScreen();
        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(10,10,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}
