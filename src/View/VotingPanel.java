package View;

import Model.VoteButton;

import javax.swing.*;
import java.awt.*;

public class VotingPanel extends JPanel {
    JTextArea answer=new JTextArea();
    VoteButton upVote=new VoteButton("^");
    VoteButton downVote=new VoteButton("⌄");
    JLabel votes=new JLabel("0",SwingConstants.LEADING);
    JLabel time=new JLabel("Random Time",SwingConstants.RIGHT);

    public VotingPanel(){

        setBorder(BorderFactory.createLoweredBevelBorder());
        setLayout(new BorderLayout());

        JPanel leftPanel=new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS));
        leftPanel.add(upVote);
        leftPanel.add(votes);
        leftPanel.add(downVote);
        upVote.setMaximumSize(new Dimension(Integer.MAX_VALUE, upVote.getMaximumSize().height));
        votes.setMaximumSize(new Dimension(Integer.MAX_VALUE, votes.getMaximumSize().height));
        downVote.setMaximumSize(new Dimension(Integer.MAX_VALUE, downVote.getMaximumSize().height));

        upVote.setAlignmentX(Component.CENTER_ALIGNMENT);
        downVote.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel centerPanel=new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(answer,BorderLayout.CENTER);
        add(time,BorderLayout.SOUTH);
        add(centerPanel,BorderLayout.CENTER);
        add(leftPanel,BorderLayout.WEST);


    }
}
