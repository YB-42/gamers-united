package Controller;

import Model.Answer;
import Model.Post;
import Model.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class DBController {

    public User login(String email, String password) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="Select * from users where email=? and password=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                User user=new User(rs.getString(1),rs.getString(2),rs.getString(3));
                con.close();
                stmt.close();
                rs.close();
                return user;
            }
            con.close();
            stmt.close();
            rs.close();
            return null;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public void signup(String name, String email,String password) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="insert into users (name,email,password) values(?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,email);
            stmt.setString(3,password);
            stmt.execute();
            con.close();
            stmt.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public ArrayList<Post> getQuestions() {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from questions";
            PreparedStatement stmt=con.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            ArrayList<Post> posts=new ArrayList<>();
            while(rs.next()){
                User poster=new User(" ",rs.getString(4)," ");
                Post post=new Post(rs.getString(2),rs.getString(3),poster);
                posts.add(post);
            }
            rs.close();
            con.close();
            stmt.close();
            return posts;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public void updateAnswer(int questionIndex, String answer,User user) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="insert into answers(question,text,answerer,date) values(?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,questionIndex);
            stmt.setString(2,answer);
            stmt.setString(3,user.getEmail());
            Date date=new Date();
            Timestamp timestamp=new Timestamp(date.getTime());
            stmt.setObject(4,timestamp);
            stmt.execute();
            con.close();
            stmt.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public void postQuestion(String question, String body,User user) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="insert into questions(statement,body,poster) values(?,?,?)";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,question);
            stmt.setString(2,body);
            stmt.setString(3,user.getEmail());
            stmt.execute();
            con.close();
            stmt.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public int getTotalQuestions(User user) {
        int ques=0;
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select *from questions where poster=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,user.getEmail());
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                ques++;
            }
            rs.close();
            con.close();
            stmt.close();
            return ques;

        }
        catch(Exception e)
        {
            System.out.println(e);
            return ques;
        }

    }

    public int getTotalAnswers(User user) {
        int ans=0;
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select *from answers where answerer=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,user.getEmail());
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                ans++;
            }
            rs.close();
            con.close();
            stmt.close();
            return ans;

        }
        catch(Exception e)
        {
            System.out.println(e);
            return ans;
        }

    }

    public void changePassword(String email, String old, String newPW) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="update users set password=? where email=? and password=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,newPW);
            stmt.setString(2,email);
            stmt.setString(3,old);
            stmt.executeUpdate();
            con.close();
            stmt.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public ArrayList<Post> getUserQuestions(User user) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from questions where poster=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,user.getEmail());
            ResultSet rs=stmt.executeQuery();
            ArrayList<Post> posts=new ArrayList<>();
            while(rs.next()){
                User poster=new User(" ",rs.getString(4)," ");
                Post post=new Post(rs.getString(2),rs.getString(3),poster);
                posts.add(post);
            }
            rs.close();
            con.close();
            stmt.close();
            return posts;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public ArrayList<Answer> getQuesAnswers(int quesIndex){
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from answers where question=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,quesIndex);
            ResultSet rs=stmt.executeQuery();
            ArrayList<Answer> answers=new ArrayList<>();
            while(rs.next()){

                Answer answer=new Answer(rs.getInt(1),rs.getString(3),rs.getString(4), (LocalDateTime) rs.getObject(5));
                answers.add(answer);
            }
            rs.close();
            con.close();
            stmt.close();
            return answers;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public ArrayList<Post> getUserAnsweredQuestions(User user) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from answers where answerer=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(1,user.getEmail());
            ResultSet rs=stmt.executeQuery();
            ArrayList<Post> posts=new ArrayList<>();
            ArrayList<Integer> quesIDs=new ArrayList<>();
            while(rs.next()){
                quesIDs.add(rs.getInt(1));
            }
            rs.close();
            stmt.close();


            sql="select * from questions";
            stmt=con.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()){
                if(quesIDs.contains(rs.getInt(1))) {
                    User poster = new User(" ", rs.getString(4), " ");
                    Post post = new Post(rs.getString(2), rs.getString(3), poster);
                    posts.add(post);
                }
            }
            rs.close();
            con.close();
            stmt.close();
            return posts;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public String getVotes(int id) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from votes where answer=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            int votes=0;
            while(rs.next()){
                if(rs.getInt(2)==1){
                    votes++;
                }
                else{
                    votes--;
                }
            }
            rs.close();
            stmt.close();
            con.close();
            return String.valueOf(votes);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }

    }

    public void deleteVote(int voteId) {
        try
        {

            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="DELETE FROM votes WHERE votes.id = ?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,voteId);
            stmt.execute();
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }


    }

    public void voteUP(User user, int ansID,int voteId) {
        try
        {
            int type = 1;
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="insert into votes(id,type,user,answer) values(?,?,?,?) ON DUPLICATE KEY UPDATE type=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,voteId);
            stmt.setInt(2,type);
            stmt.setString(3,user.getEmail());
            stmt.setInt(4,ansID);
            stmt.setInt(5,type);
            stmt.execute();

            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }


    }

    public void voteDown(User user, int ansID,int voteId) {
        try
        {
            int type = 2;
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="insert into votes(id,type,user,answer) values(?,?,?,?) ON DUPLICATE KEY UPDATE type=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,voteId);
            stmt.setInt(2,type);
            stmt.setString(3,user.getEmail());
            stmt.setInt(4,ansID);
            stmt.setInt(5,type);
            stmt.execute();
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }


    }

    /// returns 1 for +1
    /// 2 for -1 and 0
    // for error
    public int[] hasUserVoted(User user, int ansID) {
        try
        {
            // type 1 => +
            // type 2 => -
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gamers","root2","12345");
            String sql="select * from votes where answer=? and user=?";
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setInt(1,ansID);
            stmt.setString(2, user.getEmail());
            ResultSet rs=stmt.executeQuery();
            int type = 0;
            int voteId = 0;
            while (rs.next()) {
                type=rs.getInt("type");
                voteId=rs.getInt("id");
            }
            int[] info = {voteId,type};

            rs.close();
            stmt.close();
            con.close();
            return info;
        }
        catch(Exception e)
        {
            System.out.println(e);
            int[] info = {0,0};
            return info;
        }

    }
}
