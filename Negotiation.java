import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// The negotiation class is the main class of this app. This is where info is cached, and controls what pages are brought up, and with what information.

public class Negotiation extends JFrame implements ActionListener

{
    private boolean loggedIn;
    private String userName = "Anonymous";
    private String theme;
    private String[] interests;
    private Main main;
    private JPanel pane;
    
    private JMenuBar navigation = new JMenuBar();
    //login/username profile
    
    //private Page page // depending on how we set up the pages
    
    
    public static void main(String[] args) throws IOException {
        new Negotiation();
    }

    public void addDialogue(Dialogue d) {
        pane.remove(main);
        pane.add(d);
        this.setPreferredSize(d.getPreferredSize());
        pane.revalidate();
        this.pack();
        this.revalidate();
        
    }
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        JOptionPane message = new JOptionPane();
        Popup pop;
        if (action.equals("login")){
            pop = new Popup("login");
            message.showMessageDialog(null, pop);
        }
        else pop = null;
        
    }

    
    public int getCount() throws FileNotFoundException {
        Scanner read = new Scanner(new File("counter.txt"));
        int count = Integer.parseInt(read.nextLine());
        return count;
        
    }

    public Negotiation() throws FileNotFoundException {
        this.setJMenuBar(navigation);
        JMenuItem login = new JMenuItem("Login");
        login.addActionListener(this);
        login.setActionCommand("login");
        JMenuItem profile = new JMenuItem("Profile");
        navigation.add(login); navigation.add(profile);
        
        
        this.setContentPane(pane = new JPanel());
        this.setPreferredSize(new Dimension(500,500));
        main = new Main(this);
        pane.add(main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
    }

public String getUserName(){
return userName;
}

public void setUserName(String name){
userName = name;
}

    
public void addEntry(String title) throws FileNotFoundException, IOException {
    //increment counter
    int count = incrementCounter();
    //add a new entry to the dialogues file
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("dialogues.txt", true));
        bw.newLine();
        bw.write(count+":"+title+":"+userName+"!"+count+":"+"comments"+count);
        bw.flush();
        bw.close();
    } catch (IOException o) {
         System.out.println("IOException");
    }
    //create file
    createFile("comments"+count);

    
}
    
    
    public void createFile(String filename) throws IOException {
        //done
        //creates a file with filename in this folder
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.close();
        
    }
    

    public void append(String filename, String text) {
        //done
        
        try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
        bw.newLine();
        bw.write(text+"\n");
        bw.flush();
        bw.close();
        
        } catch (IOException o) {
            System.out.println("IOException");
            
        }
        
        
        
        
    }
    
    public int incrementCounter() throws FileNotFoundException, IOException {
        //done
        
        //read the count number
        Scanner read = new Scanner(new File("counter.txt"));
        int count = Integer.parseInt(read.nextLine());
        
        //increment the count number
        File superinfo = new File("counter.txt");
        FileWriter write = new FileWriter(superinfo);
        PrintWriter printwriter = new PrintWriter(write);
        printwriter.print(count+1);
        printwriter.close();
        
        return count;
    }
    
    
    public String read(String filename) throws FileNotFoundException {
        //done
        Scanner read = new Scanner(new File(filename));
        String text = "";
        while (read.hasNextLine()) {
            text += read.nextLine();
        }
        
        return text;
        
        
    }
    
    public String readComments(String filename) throws FileNotFoundException {
        Scanner read = new Scanner(new File(filename));
        String text = "";
        while (read.hasNextLine()) {
            text += read.nextLine() + "\n";
        }
        return text;
        
        
    }
    
    
    public void updateEntry(String id) throws FileNotFoundException, IOException {
        //done
        //iterate through dialogues file, find that id
        //get the line in a string
        //split the string around that particular colon
        //and insert current username + id
        Scanner read = new Scanner(new File("dialogues.txt"));
        String replacefrom = "";
        String fin = "";
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] split = line.split(":");
            if (split[0].equals(id)) {
                replacefrom = line;
                fin = "";
                for (int i = 0; i < 3; i++) {
                    fin += split[i]+":";
                }
                fin += userName+"!"+id+":";
                fin += split[3];
               
            }
        }
        read.close();
        
        Scanner reader = new Scanner(new File("dialogues.txt"));
        String full = "";
        
        while (reader.hasNextLine()) {
            full += reader.nextLine() + "\n";
        
        }
        reader.close();
        full = full.replace(replacefrom, fin);
        File done = new File("dialogues.txt");
        FileWriter write = new FileWriter(done);
        PrintWriter printwriter = new PrintWriter(write);
        printwriter.print(full);
        printwriter.close();
        
            
    }
   
    
        


}