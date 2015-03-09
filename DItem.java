import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;


public class DItem extends JButton implements ActionListener {

    private JLabel label;
    Negotiation negotiation;
    String[] dinfo;
    Dialogue d;
    JButton enter;
    

    public DItem(String[] vars, Negotiation comp) {
        add (label = new JLabel(vars[1]));
        negotiation = comp;
        dinfo = vars;
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        //layout.setAlignment(FlowLayout.TRAILING);
        if (dinfo.length == 4) {
          add(enter = new JButton("Join"));
             enter.addActionListener(this);
            
        }
        else if ((negotiation.getUserName()+"!"+dinfo[0]).equals(dinfo[2]) || (negotiation.getUserName()+"!"+dinfo[0]).equals(dinfo[3])) {
                 add(enter = new JButton("Resume"));
             enter.addActionListener(this);
            
        }
      
        
       
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(enter) && dinfo.length == 4) {
            String[] newinfo = new String[5];
            //iterate through dinfo until 3 and copy, for four put in the ame of current user in format and create file/update entry, then copy five
            for (int i = 0; i < 3; i++) {
                newinfo[i] = dinfo[i];
            }
            newinfo[3] = negotiation.getUserName()+"!"+newinfo[0];
            try {
            negotiation.createFile(newinfo[3] + ".txt");
            negotiation.updateEntry("1");
            newinfo[4] = dinfo[3];
            negotiation.addDialogue((d = new Dialogue(newinfo, negotiation)));
            } catch (Exception a) {
                System.out.println("Exception");
            }
            
            
            
        }
        
        
        
        try {
            negotiation.addDialogue((d = new Dialogue(dinfo, negotiation)));
        }
        catch (Exception o) {
            System.out.println("There's an exception dude");
            
        }
        
        
        //if join is pressed, make a b file and insert it into the dinfo array, and create new dialogue object
        
    }
    
    
    
    
    
    
    
    
    
    
}















