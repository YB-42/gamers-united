package View;

import Controller.DBController;
import Model.Answer;
import Model.Post;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AnswerQuestionScreen extends JFrame {
    Container con=getContentPane();
    JPanel buttonsPanel=new JPanel();
    JPanel questionPanel=new JPanel();
    JButton profile=new JButton("Profile");
    JButton ask=new JButton("Ask a question");
    JButton allQuestions =new JButton("All Questions");
    JLabel welcome=new JLabel();
    JTextArea question=new JTextArea();
    JTextArea questionBody=new JTextArea();
    JTextArea answer=new JTextArea(7,0);
    JButton submit=new JButton("Submit");
    User user;
    Post post;
    int questionIndex;
    ArrayList<Answer> answers;
    DBController db=new DBController();

    public AnswerQuestionScreen(User user, Post post, int questionIndex){
        this.user=user;
        this.post=post;
        this.questionIndex=questionIndex;
        welcome.setText("Welcome "+user.getName());
        Font font = new Font("Sans Serif", Font.BOLD, 18);
        question.setFont(font);
        question.append("Q: "+post.getQuestion());
        questionBody.append(post.getBody()+"\nSubmitted by: "+post.getPoster().getEmail());
        questionBody.setEditable(false);
        question.setEditable(false);


        setLayoutManager();
        setLocationAndSize();
        setUpQuestionsPanel();
        addComponentsToContainer();

        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        ask.setAlignmentX(Component.CENTER_ALIGNMENT);
        allQuestions.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.add(welcome);
        buttonsPanel.add(profile);
        buttonsPanel.add(allQuestions);
        buttonsPanel.add(ask);
        con.add(buttonsPanel,BorderLayout.WEST);
        JLabel label=new JLabel("Gamers United",SwingConstants.CENTER);
        label.setFont(new Font("Sans Serif", Font.PLAIN,30));
        welcome.setFont(new Font("Sans Serif", Font.PLAIN,20));
        JLabel quesLabel=new JLabel("Question");
        quesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(quesLabel);
        questionPanel.add(question);
        questionPanel.add(questionBody);
        answers=db.getQuesAnswers(questionIndex);

        if(answers!=null){
            for(int i=0;i<answers.size();i++){
                VotingPanel ans=new VotingPanel();
                ans.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
                ans.answer.append("Answer: "+answers.get(i).getText()+"\nSubmitted by: "+answers.get(i).getUserEmail());
                //ans.answer.setEnabled(false);
                questionPanel.add(Box.createRigidArea(new Dimension(0,10)));
                questionPanel.add(ans);
                int ansID=answers.get(i).getId();
                ans.upVote.setAnswerID(ansID);
                ans.downVote.setAnswerID(ansID);

                ans.votes.setText(db.getVotes(ansID));
                ans.time.setText(answers.get(i).getTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
                ans.upVote.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       int[] info = db.hasUserVoted(user,ansID);
                       int voteId = info[0];
                       int type = info[1];
                        if(type == 2)  {
                            db.deleteVote(voteId);
                            ans.votes.setText(db.getVotes(ansID));
                        }else if(type == 0)
                        {
                            db.voteUP(user, ansID,voteId);
                            ans.votes.setText(db.getVotes(ansID));
                        }
                    }
                });

                ans.downVote.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int[] info = db.hasUserVoted(user,ansID);
                        int voteId = info[0];
                        int type = info[1];
                        if(type == 1)  {
                            db.deleteVote(voteId);
                            ans.votes.setText(db.getVotes(ansID));
                        }else if(type == 0)
                        {
                            db.voteDown(user, ansID,voteId);
                            ans.votes.setText(db.getVotes(ansID));
                        }
                    }
                });

            }
        }
        JLabel ansLabel=new JLabel("Answer");
        ansLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(Box.createRigidArea(new Dimension(0,10)));
        questionPanel.add(ansLabel);
        questionPanel.add(answer);
        questionPanel.add(submit);
        JScrollPane scrollPane=new JScrollPane(questionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        con.add(BorderLayout.CENTER,scrollPane);
        con.add(label,BorderLayout.NORTH);
    }

    private void setLocationAndSize() {
        welcome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        question.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        questionBody.setMaximumSize(new Dimension(Integer.MAX_VALUE,70));
        answer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
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
                if(!answer.getText().isEmpty()) {
                    DBController db = new DBController();
                    db.updateAnswer(questionIndex,answer.getText(),user);
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
