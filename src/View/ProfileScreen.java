package View;

import Controller.DBController;
import Model.Post;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileScreen extends JFrame {
    Container con=getContentPane();
    JPanel buttonsPanel=new JPanel();
    JPanel profilePanel =new JPanel();
    JPanel rightPanel=new JPanel();
    JButton profile=new JButton("Profile");
    JButton ask=new JButton("Ask a question");
    JButton allQuestions =new JButton("All Questions");
    JLabel welcome=new JLabel();
    JTextArea question=new JTextArea();
    JTextArea answer=new JTextArea();
    JButton logout =new JButton("Logout");
    JButton changePassword =new JButton("Change Password");
    User user;
    int totalQues=0,totalAns=0;


    public ProfileScreen(User user){
        this.user=user;
        welcome.setText("Welcome \n"+user.getName());
        question.setEditable(false);
        answer.setEditable(false);
        DBController db=new DBController();
        totalQues=db.getTotalQuestions(user);
        totalAns=db.getTotalAnswers(user);
        setLayoutManager();
        setLocationAndSize();
        setUpQuestionsPanel();
        addComponentsToContainer();
        logout.addActionListener(new ActionListener() {
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
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChangePasswordScreen frame=new ChangePasswordScreen();
                frame.setTitle("Welcome");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });
        ask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AskQuestionScreen frame=new AskQuestionScreen(user);
                frame.setTitle("Ask a question");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setResizable(false);
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProfileScreen frame=new ProfileScreen(user);
                frame.setTitle("Ask a question");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setResizable(false);
            }
        });
        allQuestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeScreen frame=new HomeScreen(user);
                frame.setTitle("Home");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });


    }

    private void addComponentsToContainer() {
        buttonsPanel.add(welcome);
        buttonsPanel.add(profile);
        buttonsPanel.add(allQuestions);
        buttonsPanel.add(ask);
        con.add(buttonsPanel,BorderLayout.WEST);
        JLabel label=new JLabel("Gamers United",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,30));
        welcome.setFont(new Font("Sans Serif", Font.PLAIN,20));
        JLabel dashboard=new JLabel("Dashboard");
        dashboard.setBounds(250,50,100,30);

        JButton questions=new JButton("Questions");
        JButton answers=new JButton("Answers");
        questions.setBounds(150,100,100,50);
        answers.setBounds(300,100,100,50);
        JLabel quesLabel=new JLabel(String.valueOf(totalQues));
        JLabel ansLabel=new JLabel(String.valueOf(totalAns));
        quesLabel.setBounds(200,170,100,50);
        ansLabel.setBounds(350,170,100,50);
        profilePanel.add(quesLabel);
        profilePanel.add(ansLabel);
        profilePanel.add(questions);
        profilePanel.add(answers);
        profilePanel.add(dashboard);
        questions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuestionsIAskedScreen frame=new QuestionsIAskedScreen(user);
                frame.setTitle("Questions I Asked");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });
        answers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                QuestionsIAnsweredScreen frame=new QuestionsIAnsweredScreen(user);
                frame.setTitle("Questions I Answered");
                frame.setVisible(true);
                frame.setBounds(10,10,800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
            }
        });
        JLabel userProfile=new JLabel("User Profile");
        userProfile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        userProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel userName=new JLabel("User Name: "+user.getName());
        userName.setMaximumSize(new Dimension(Integer.MAX_VALUE, userName.getMaximumSize().height));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel userEmail=new JLabel("User Name: "+user.getEmail());
        userEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        userEmail.setAlignmentX(Component.CENTER_ALIGNMENT);


        rightPanel.add(userProfile);
        rightPanel.add(userName);
        rightPanel.add(userEmail);

        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(changePassword);
        rightPanel.add(logout);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        profilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        con.add(profilePanel,BorderLayout.CENTER);
        con.add(label,BorderLayout.NORTH);
        con.add(rightPanel,BorderLayout.EAST);
    }

    private void setLocationAndSize() {
        welcome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        answer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        ask.setMaximumSize(new Dimension(Integer.MAX_VALUE, ask.getMaximumSize().height));
        allQuestions.setMaximumSize(new Dimension(Integer.MAX_VALUE, allQuestions.getMaximumSize().height));
        profile.setMaximumSize(new Dimension(Integer.MAX_VALUE, profile.getMaximumSize().height));
        logout.setMaximumSize(new Dimension(Integer.MAX_VALUE, logout.getMaximumSize().height));
        changePassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, changePassword.getMaximumSize().height));
    }

    private void setLayoutManager() {
        con.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        profilePanel.setLayout(null);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

    }

    private void setUpQuestionsPanel(){
        JLabel label=new JLabel("All Questions",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,15));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMaximumSize().height));



    }

}
