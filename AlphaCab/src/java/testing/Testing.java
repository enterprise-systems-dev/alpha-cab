package testing;

import com.alphacab.model.UserDao;
import com.alphacab.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Testing {

    String driver;
    String url;
    String user;
    String pass;
    
    Connection con = null;
    
    //my utils
    MyShit s;
    
    public static void main(String[] args) {
        Testing p = new Testing();
        
        //set alphaCab database connection parameters
        p.driver = "org.apache.derby.jdbc.ClientDriver";
        p.url = "jdbc:derby://localhost:1527/AlphaCab";
        p.user = "username";
        p.pass = "password";
        
        //set my utils object
        p.s = new MyShit();
        
        boolean connected = p.connect();
        if(connected)
            p.start();
        else
            System.out.println("Failed to connect to database, quitting.");
    }
    
    //create con
    public boolean connect(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException e){
            System.out.println("Failed to connect to the database - SQLException: " + e);
            return false;
        }
        catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException: " + e);
            return false;
        }
        
        System.out.println("Connected to alphaCab database.");
        return true;
    }
    
    //start
    public void start(){
        testGetAllUsers();
    }
    
    //test UserDao 
    public void testGetAllUsers(){
        
        //header list
        String[] header = {"userID","username","password","role"};
        //row list
        ArrayList<String[]> userList = new ArrayList<>();

        //create new db connection
        UserDao db = new UserDao();
        db.connect(con);

        //users to list
        for (User u : db.getAllUsers()){
            //convert necessary fields to string
            String id = Integer.toString(u.getId());
            userList.add( new String[] { id, u.getUsername(), u.getPassword(), u.getRole()} );
        }
        
        //print table
        printTable(header, userList);
    }
    
    //print table
    public void printTable(String[] header,ArrayList<String[]> rows){
        
        //table format variables
        int pad = 15; //column padding
        String cDiv = ""; //column divider char
        String rDiv = "-"; //row divider char
        String rowDivider; //complete row divider string
        
        String whiteColour = "\u001B[0m"; //default column colour
        String altColour = "\u001B[31m"; //alternate column colour - //change the number before the m to change the colour (i.e. \u001B[32m) - reset:0/black:30/red:31/green:32/yellow:33/blue:34/purple:35/cyan:36/white:37
        
        int columnNumber = header.length;
        
        //generate header
        String h = "";
        for(int c = 0; c <= columnNumber-1; c++){
            if (c < columnNumber)
                h += String.format("%"+pad+"s" + cDiv, header[c]);
            else
                h += String.format("%"+pad+"s", header[c]);
        }
        
        //print header
        System.out.println(h);
        
        //generate row divider
        rowDivider = new String(new char[h.length()]).replace("\0", rDiv);
        
        //generate row strings
        for (String[] row : rows){
            
            //insert row divider
            System.out.println(altColour + rowDivider + whiteColour);
            
            //generate row
            String r = "";
            for(int c = 0; c <= columnNumber-1; c++){
                if (c < columnNumber)
                    r += String.format("%"+pad+"s" + cDiv, row[c]);
                else
                    r += String.format("%"+pad+"s", row[c]);
            }
            
            //print row
            System.out.println(r);
        }
    }
    
    //print string[] list
    public void printList(ArrayList<String[]> list){
        for(String[] row : list){
            for(String value : row){
                System.out.print(value + ", ");
            }
            System.out.println();
        }
    }
}
