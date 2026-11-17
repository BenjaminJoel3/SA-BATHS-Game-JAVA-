package wars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/02/12
 */
public class GameGUI 
{
    private BATHS gp = new SeaBattles("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight Encounter");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();
    private JScrollPane textScroll = new JScrollPane(listing, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        //myFrame.add(listing,BorderLayout.CENTER);
        //textScroll.setVisible(false);
        myFrame.add(textScroll);
        textScroll.setVisible(false);
        
        contentPane.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));

        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearHandler());
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightHandler());
        eastPanel.add(quitBtn);

        clearBtn.setVisible(true);
        quitBtn.setVisible(true);
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
        // Set the size to be bigger
        myFrame.setSize(500, 500);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Ships");
        menubar.add(fileMenu);
        
        JMenuItem stateOfGame = new JMenuItem("View State");
        stateOfGame.addActionListener(new ViewStateHandler());
        fileMenu.add(stateOfGame);
        
        JMenuItem listSquadronItem = new JMenuItem("List squadron Ships");
        listSquadronItem.addActionListener(new ListSquadronHandler());
        fileMenu.add(listSquadronItem);
        
        JMenuItem listShipItem = new JMenuItem("List reserve Ships");
        listShipItem.addActionListener(new ListFleetHandler());
        fileMenu.add(listShipItem);
        
        JMenuItem shipView = new JMenuItem("View a ship");
        shipView.addActionListener(new ShipViewHandler());
        fileMenu.add(shipView);
        
        JMenuItem commission = new JMenuItem("Commission Ship");
        commission.addActionListener(new CommissionHandler());
        fileMenu.add(commission);
        
        JMenuItem decommission = new JMenuItem("De-commission Ship");
        decommission.addActionListener(new DecommissionHandler());
        fileMenu.add(decommission);
        
        
        
    }

    private class ViewStateHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) { 
            listing.setText(gp.toString());
            textScroll.setVisible(true);
        }
    }


    
    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            textScroll.setVisible(true);
            String xx = gp.getReserveFleet();
            listing.setText(xx);
            
        }
    }
    
    private class ListSquadronHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            textScroll.setVisible(true);
            String xx = gp.getSquadron();
            listing.setText(xx);
            
        }
    }

    
    private class ClearHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText("");
            textScroll.setVisible(false);            
        }
    }

    private class ShipViewHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            
            String inputValue = JOptionPane.showInputDialog("Ship name: ");
            String result = gp.getShipDetails(inputValue);
            
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
    
    private class CommissionHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            String shipName = JOptionPane.showInputDialog("Ship Name: ");
            
            String commissionResult = gp.commissionShip(shipName);
            
            JOptionPane.showMessageDialog(myFrame, commissionResult);    
        }
    }
    
    private class DecommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            String inputValue = JOptionPane.showInputDialog("Ship name: ");
            
            String result = (gp.decommissionShip(inputValue)) ? inputValue + " is decommissioned." : inputValue + " not in fleet";
            
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }
    
    
    
    private class FightHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            String encounterNo = JOptionPane.showInputDialog("Encounter: ");
            int encNo = Integer.parseInt(encounterNo);
            String fightResult = gp.fightEncounter(encNo);
            
            JOptionPane.showMessageDialog(myFrame, fightResult);
        }
    }
    
    public static void main(String[] args)
    {
        new GameGUI();
    }
}
   
