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

public class HomeScreen extends JFrame {
    Container con=getContentPane();
    JPanel quesPanel=new JPanel();
    JPanel buttonsPanel=new JPanel();
    JButton profile=new JButton("Profile");
    JButton ask=new JButton("Ask a question");
    JButton allQuestions =new JButton("All Questions");
    JLabel welcome=new JLabel();
    User user;
    ArrayList<Post> posts=new ArrayList<>();

    public HomeScreen(User user){
        this.user=user;
        welcome.setText("Welcome \n"+user.getName());
        DBController db=new DBController();
        posts=db.getQuestions();
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
        setLayoutManager();
        setLocationAndSize();
        setUpQuestionsPanel();
        addComponentsToContainer();
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
        con.add(label,BorderLayout.NORTH);
        JScrollPane scrollPane=new JScrollPane(quesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        con.add(BorderLayout.CENTER,scrollPane);
    }

    private void setLocationAndSize() {
        welcome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        ask.setMaximumSize(new Dimension(Integer.MAX_VALUE, ask.getMaximumSize().height));
        allQuestions.setMaximumSize(new Dimension(Integer.MAX_VALUE, allQuestions.getMaximumSize().height));
        profile.setMaximumSize(new Dimension(Integer.MAX_VALUE, profile.getMaximumSize().height));
    }

    private void setLayoutManager() {
        con.setLayout(new BorderLayout());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        quesPanel.setLayout(new BoxLayout(quesPanel,BoxLayout.PAGE_AXIS));
    }

    private void setUpQuestionsPanel(){
        JLabel label=new JLabel("All Questions",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,15));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getMaximumSize().height));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        quesPanel.add(label);

        if(posts!=null) {
            for (int i = 0; i < posts.size(); i++) {
                JTextArea questionText = new JTextArea();
                JTextArea quesBody=new JTextArea();
                questionText.append("Q: " + posts.get(i).getQuestion());
                quesBody.append(posts.get(i).getBody() + "\n"+ "Submitted by:" + posts.get(i).getPoster().getEmail());
                questionText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                quesBody.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
                questionText.setEditable(false);
                quesBody.setEditable(false);
                AnswerButton button = new AnswerButton(i);
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMaximumSize().height));
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        AnswerQuestionScreen frame = new AnswerQuestionScreen(user, posts.get(button.getQuestionIndex()), button.getQuestionIndex());
                        frame.setTitle("Answer a question");
                        frame.setVisible(true);
                        frame.setBounds(10, 10, 800, 600);
                        frame.setResizable(false);
                    }
                });

                Font font = new Font("Sans Serif", Font.BOLD, 18);
                questionText.setFont(font);
                quesPanel.add(questionText);
                quesPanel.add(quesBody);
                quesPanel.add(button);
            }
        }


    }



}
