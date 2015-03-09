import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Dialogue extends JPanel implements ActionListener
{
// the main class creates dialogue objects
    // each dialogue object displays information it was passed
        // (dialogue name, A, B, mode, comments)
        // the Dname is the title of the dialogue
        // A and B are the filenames of the users' messages
        // and mode shows if it's user A or user B, or a commenter
    
// Dialogue object is a panel, filled with 2 adjacent panels
            // A textfield, and I guess another panel underneith for comments.

    private String mode = "C";
    private String title = "";
    private String afile = "none";
    private String bfile = "none";
    private String cfile = "none";
    private Negotiation negotiation = null;
    private int id = 0;
    private String userA = "";
    private String userB = "";
    private String[] info;
    
    private JPanel a;
    private JPanel b;
    private JPanel c;
    
    private JTextArea atext;
    private JTextArea btext;
    private JTextArea comments;
    private JTextArea textenter;
    private boolean debug = false;
    
    
    public Dialogue ()
    {
        this.setPreferredSize(new Dimension(1000,1000));
        a = new JPanel();
        b = new JPanel();
        c = new JPanel();
        this.add(a);
        this.add(b);
        this.add(c);
        
        
        comments = new JTextArea("comments");
        comments.setPreferredSize(new Dimension(200, 200));
        comments.setEnabled(false);
        
        atext = new JTextArea(20, 20);
        atext.setEnabled(false);
        a.add(atext);
        
        btext = new JTextArea(20, 20);
        btext.setEnabled(false);
        b.add(btext);
        
        a.add(atext);
        b.add(btext);
        c.add(comments);
        
        textenter = new JTextArea(1, 1);
        textenter.setText("Type Here");
        textenter.setEnabled(true);
        this.add(textenter);
        
        JTextArea enter_a = new JTextArea("Person A");
        enter_a.setEnabled(false);
        a.add(enter_a);
        
        JTextArea enter_b = new JTextArea("Person B");
        enter_b.setEnabled(false);
        b.add(enter_b);
        
        
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        enter.setPreferredSize(new Dimension(2,2));
        this.add(enter);
        this.setPreferredSize(new Dimension(1000,1000));
        this.setLayout(new GridLayout(2,2));

    }
    
    public Dialogue (String[] dinfo, Negotiation n) throws FileNotFoundException
    {
        //String dname, String afile, String bfile, String cfile
        
        
        this();
        info = dinfo;
        negotiation = n;
        id = Integer.parseInt(dinfo[0]);
        title = dinfo[1];
        afile = dinfo[2] + ".txt";
        userA = parseName(afile);
        
       this.initializeTextA(afile);
      
        if (dinfo.length == 5) {
        bfile = dinfo[3] + ".txt";
        userB = parseName(bfile);
       this.initializeTextB(bfile);
            
        cfile = dinfo[4] + ".txt";
        this.initializeComments(cfile);
            
        }
        
        else {
            cfile = dinfo[3] + ".txt";
           this.initializeComments(cfile);
            
        }
        
       
        
        if (negotiation.getUserName().toLowerCase().equals(userA.toLowerCase()))
            mode = "A";
        else if (negotiation.getUserName().toLowerCase().equals(userB.toLowerCase()))
            mode = "B";
        else
            mode = "C";
        
        if(debug) {
            
        System.out.println("User A: "+ userA);
        System.out.println("User B: "+ userB);
        System.out.println("Mode: "+ mode);
        }

        
        
        
    }
    
    private String parseName(String namefile){
        int exclaim = namefile.indexOf('!');
        String name = namefile.substring(0,exclaim);
        return name;
        
    }

    
    
 /*   public static void main(String[] args) throws FileNotFoundException{
        
        
        Dialogue dialogue = new Dialogue();
       dialogue.setNegotiation();
        dialogue.setPreferredSize(new Dimension(1000,1000));
        dialogue.setLayout(new GridLayout(2,2));
        JFrame test = new JFrame("Testing Dialogue Panels");
        test.getContentPane().add(dialogue);
        
        
        test.pack();
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    */
    
    
    public void actionPerformed(ActionEvent e){
        add();
        
    }
    
    private void add(){
        if (mode.equals("A"))
            addA();
        else if (mode.equals("B"))
            addB();
        else
            addC();
    }
    
    private void addA(){
        String text;
        atext.setText(text = atext.getText() + textenter.getText() + "\n\n");
        negotiation.append(info[2]+".txt", text);
        
    }
    
    private void addB(){
        String text;
        btext.setText(text = btext.getText() + textenter.getText() + "\n\n");
        negotiation.append(info[3]+".txt", text);
    }
    
    private void addC(){
        String text;
        comments.setText(text = comments.getText() + "\n\n" + negotiation.getUserName() +
                          ":\n" + textenter.getText());
        int index = info.length-1;
        negotiation.append(info[index]+".txt", text);
        
    }
    
    
    public void initializeTextA(String filename) throws FileNotFoundException {
        System.out.println(filename);
        atext.setText(negotiation.read(filename));
        
        
    }
    
    public void initializeTextB(String filename) throws FileNotFoundException{
        System.out.println(filename);
        btext.setText(negotiation.read(filename));
        
        
    }
    
    public void initializeComments(String filename) throws FileNotFoundException{
       /* String str = negotiation.read(filename);
        String[] fin = str.split("\\n+");
        String forreal = "";
        for (String s: fin) {
            forreal += s + "\n";
        }
        */
        String forreal = negotiation.readComments(filename);
            comments.setText(forreal);
       
        
    }
    
    
    /*
    public void setNegotiation() throws FileNotFoundException
    {
        negotiation = new Negotiation();
    }
    
    */

    
    
    

}