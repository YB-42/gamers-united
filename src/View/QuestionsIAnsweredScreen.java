package View;

import Controller.DBController;
import Model.AnswerButton;
import Model.Post;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuestionsIAnsweredScreen extends JFrame {
    Container con=getContentPane();
    JPanel buttonsPanel=new JPanel();
    JPanel quesPanel =new JPanel();
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
    ArrayList<Post> posts=new ArrayList<>();

    public QuestionsIAnsweredScreen(User user){
        this.user=user;
        welcome.setText("Welcome \n"+user.getName());
        question.setEditable(false);
        answer.setEditable(false);
        DBController db=new DBController();
        posts=db.getUserAnsweredQuestions(user);
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

        JLabel userProfile=new JLabel("User Profile");
        userProfile.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        userProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel userName=new JLabel("User Name: "+user.getName());
        userName.setMaximumSize(new Dimension(Integer.MAX_VALUE, userName.getMaximumSize().height));
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel userEmail=new JLabel("User Name: "+user.getEmail());
        userEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        userEmail.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel label3=new JLabel("Questions I Answered",SwingConstants.CENTER);
        label3.setFont(new Font("Sans Serif", Font.PLAIN,15));
        label3.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMaximumSize().height));
        quesPanel.add(label);

        if(posts!=null) {
            for (int i = 0; i < posts.size(); i++) {
                JTextArea questionText = new JTextArea();
                questionText.append("Question: " + posts.get(i).getQuestion() + "  (" + posts.get(i).getPoster().getEmail() + ")" + "\n");
                questionText.append("Body: " + posts.get(i).getBody() + "\n");
                questionText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                questionText.setEditable(false);
                quesPanel.add(questionText);

            }
        }
        rightPanel.add(userProfile);
        rightPanel.add(userName);
        rightPanel.add(userEmail);

        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        logout.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(changePassword);
        rightPanel.add(logout);

        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JScrollPane scrollPane=new JScrollPane(quesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        con.add(BorderLayout.CENTER,scrollPane);
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
        quesPanel.setLayout(new BoxLayout(quesPanel,BoxLayout.PAGE_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

    }

    private void setUpQuestionsPanel(){
        JLabel label=new JLabel("All Questions",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,15));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMaximumSize().height));



    }

}
