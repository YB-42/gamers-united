package View;

import Controller.DBController;
import Model.Post;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskQuestionScreen extends JFrame {
    Container con=getContentPane();
    JPanel buttonsPanel=new JPanel();
    JPanel questionPanel=new JPanel();
    JButton profile=new JButton("Profile");
    JButton ask=new JButton("Ask a question");
    JButton allQuestions =new JButton("All Questions");
    JLabel welcome=new JLabel();
    JTextArea question=new JTextArea();
    JTextArea questionBody=new JTextArea();
    JButton submit=new JButton("Submit");
    User user;

    public AskQuestionScreen(User user){
        this.user=user;
        welcome.setText("Welcome \n"+user.getName());
        DBController db=new DBController();
        setLayoutManager();
        setLocationAndSize();
        setUpQuestionsPanel();
        addComponentsToContainer();

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
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

        JLabel label2=new JLabel("Ask Question");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(label2);

        JLabel quesLabel=new JLabel("Question");
        quesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(quesLabel);
        questionPanel.add(question);

        JLabel bodyLabel=new JLabel("Body");
        bodyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(bodyLabel);
        questionPanel.add(questionBody);
        questionPanel.add(submit);
        con.add(questionPanel,BorderLayout.CENTER);
        con.add(label,BorderLayout.NORTH);
    }

    private void setLocationAndSize() {
        welcome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        questionBody.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        ask.setMaximumSize(new Dimension(Integer.MAX_VALUE, ask.getMaximumSize().height));
        allQuestions.setMaximumSize(new Dimension(Integer.MAX_VALUE, allQuestions.getMaximumSize().height));
        profile.setMaximumSize(new Dimension(Integer.MAX_VALUE, profile.getMaximumSize().height));
        submit.setMaximumSize(new Dimension(Integer.MAX_VALUE, submit.getMaximumSize().height));
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void setLayoutManager() {
        con.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));

    }

    private void setUpQuestionsPanel(){
        JLabel label=new JLabel("All Questions",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,15));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMaximumSize().height));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!question.getText().isEmpty()) {
                    DBController db = new DBController();
                    db.postQuestion(question.getText(),questionBody.getText(),user);
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


    }
}
