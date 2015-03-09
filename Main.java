import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;



public class Main extends JPanel implements ActionListener {

    
    Negotiation negotiation;
    Scanner scanner;
    JButton createButton;
    File dialogues;
    File superinfo;
    JOptionPane message;
    
    

    public Main(Negotiation comp) throws FileNotFoundException {
        
        negotiation = comp;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new BorderLayout());
       // setLayout(null);
        setBorder(new EmptyBorder(100,100,100,100));
        setVisible(true);
        add (createButton = new JButton("Create Dialogue"));
        createButton.addActionListener(this);
        createButton.setVisible(true);
        dialogues = new File("dialogues.txt");
        scanner = new Scanner(dialogues);
        
        //loop that goes through each line in dialogues and creates a new dItem with each piece of info
        //goes through each line in the file:
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            //loop that goes through string input and seperates the dialogue id, the title, and A and B
            String[] vars = input.split(":");
            add(new DItem(vars, negotiation));
            
            
            
        }
        
    }
    
    
    
    public void actionPerformed (ActionEvent e) {
        //dinfo = id, dtitle, username a, username b, comments+id
        if (e.getSource().equals(createButton)) {
            try {
            new Popup("title");
            message = new JOptionPane();
            Popup popup = new Popup("title");
            message.showMessageDialog(null, popup);
            negotiation.addEntry(popup.getTitle());
            String[] dinfo = new String[4];
            dinfo[0] = Integer.toString(negotiation.getCount());
            dinfo[1] = popup.getTitle();
            dinfo[2] = negotiation.getUserName()+"!"+dinfo[0];
            dinfo[3] = "comments"+dinfo[0];
            negotiation.createFile(dinfo[2] + ".txt");
            negotiation.createFile(dinfo[3]+".txt");
            negotiation.addDialogue(new Dialogue(dinfo, negotiation));
            /*
            //take care of this with popup (and increment)
            negotiation.createFile(negotiation.getUserName()+"!"+.txt");
            negotiation
            */
            } catch (Exception o) {
                o.printStackTrace();
                
                
            }
            
        }
        
        
        
        
    }
    
    
    
    
    
    
    
    

}

//to pass to dialogue object: dialogue title, filenames of responses (make sure name is username + dialogue id), filename of comments, and the mode (resume, view, or join)