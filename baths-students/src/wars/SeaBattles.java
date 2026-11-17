package wars;

import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS
{
    private String admiral;
    private double warChest;
    private HashMap<Integer, Encounter> allEncounters = new HashMap<Integer, Encounter>();
    private ArrayList<Ship> squadron = new ArrayList<Ship>();
    private ArrayList<Ship> reserve = new ArrayList<Ship>();
    

//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SeaBattles(String adm)
    {
      admiral = adm;
      warChest = 1000;
      setupShips();
      setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admiral the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
        admiral = admir;
        warChest = 1000;
        setupShips();
        readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {   
        String playerState;
        if (isDefeated()) playerState = "Defeated";
        else playerState = "Is OK";
        
        return "#############################" +"\n" +
               "Admiral: " + admiral + "\n" +
               "War Chest: " + warChest + "\n" +
               "Player State: " + playerState + "\n" +
               "Squadron: \n[" + getSquadron() + "]\n" +
               "Reserve: \n[" + getReserveFleet() + "]\n" +
               "#############################";
    }          
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        if (warChest <= 0) {
            for (Ship s : squadron) {
                // In squadron, there's no reserve state, thus only need to check if it's not sunk
                if (s.getState() != ShipState.SUNK) {
                    return false;
                }    
            }
            return true;
        }
        return false;
    }
        
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return warChest;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {
        String returnString = "";
        for (Ship ship: reserve) {
            returnString += ship.toString() + "\n";
        }
        if (returnString.equals("")) {
            return "No ships in reserve.";
        }
        
        return returnString;
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        String returnString = "";
        for (Ship ship: squadron) {
            if (ship.getState() == ShipState.ACTIVE) {
                returnString += ship.toString() + "\n";
            }
        }
        if (returnString.equals("")) {
            return "No ships commissioned";
        }
        
        return returnString;
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
        String returnString = "";
        for (Ship ship: squadron) {
            if (ship.getState() == ShipState.SUNK)
            returnString += ship.toString() + "\n";
        }
        if (returnString.equals("")) {
            return "No ships commissioned";
        }
        
        return returnString;
    }
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        String returnString = "";
        for (Ship ship: squadron) {
            returnString += ship.toString();
        }
        for (Ship ship: reserve) {
            returnString += ship.toString();
        }
        return (returnString.equals("")) ? "No Ships" : returnString;
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
        Ship ship = getSquadronShip(nme);
        if (ship == null) {
            ship = getReserveShip(nme);
            if (ship == null) return "Ship not found.";
        }
        
        return ship.toString();
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be comissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        Ship ship = getReserveShip(nme);
        if (ship == null) {
            ship = getSquadronShip(nme);
            if (ship == null) return "Not found";
            
            return "Not available";
        }
        
        int shipCommissionFee = ship.getCommissionFee();
        if (shipCommissionFee > warChest) return "Not enough money";
        
        reserve.remove(ship);
        squadron.add(ship);
        ship.changeState(ShipState.ACTIVE);
        warChest -= shipCommissionFee;
        return "Ship commissioned";
    }
        
    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        Ship ship = getSquadronShip(nme);
        if (ship == null){
            return false;
        }
        return ship.getState() == ShipState.ACTIVE;
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
        Ship ship = getSquadronShip(nme);
        //Cannot decommision sunk ship
        if (ship == null || ship.getState() == ShipState.SUNK) {
            return false;
        }
        else {
            int shipCommissionFee = ship.getCommissionFee();
            reserve.add(ship);
            squadron.remove(ship);
            ship.changeState(ShipState.RESERVE);
            warChest += 0.5 * shipCommissionFee;
            return true;
        }
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
        Ship ship = getSquadronShip(ref);
        if (ship == null) return;
        
        if (ship.getState() == ShipState.RESTING) {
            ship.changeState(ShipState.ACTIVE);
        }
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         return allEncounters.containsKey(num);
     }
     
     
    /** Retrieves the encounter represented by the encounter number.
      * Finds a ship from the fleet which can fight the encounter.
      * The results of fighting an encounter will be one of the following:
      * 0-Encounter won by...(ship reference and name)-add prize money to War 
      * Chest and ship's state is set to RESTING
      * 1-Encounter lost as no ship available - deduct prize from the War Chest
      * 2-Encounter lost on battle skill and (ship name) sunk" - deduct
      *  plunder from War Chest and ship state set to sunk
      * If an encounter is lost and admiral is completely defeated because there're
      * no ships to decommission, add "You have been defeated" to the message, 
      * -1 No such encounter
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
        Encounter encounter = allEncounters.get(encNo);
        Ship shpToFight = null;
        if (encounter == null){return "No such encounter";}
        // Find ship avalailable to fight
        for (Ship sh: squadron){
            if(sh.getState() == ShipState.ACTIVE && sh.canFightEncounter(encounter.getType())){
                shpToFight = sh;
                break;
            }
        }
        String resultMessage = "";
        // No ship available
        if (shpToFight == null){
            warChest -= encounter.getPrize();
            resultMessage = "Encounter lost at no ship available.";
        }
        else if(shpToFight.getBattleSkill() < encounter.getSkill()){
            //Lose
            shpToFight.changeState(ShipState.SUNK);
            warChest -= encounter.getPrize();
            resultMessage = "Encounter lost on battle skill " + shpToFight.getName() + " sunk.";
        }
        else{
            //Won
            warChest += encounter.getPrize();
            shpToFight.changeState(ShipState.RESTING);
            resultMessage = "Encounter won by " + shpToFight.getName();
        }
        if (isDefeated()){
            resultMessage += ", You have been defeated";
        }
        return resultMessage;
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        if (isEncounter(num)) {
            Encounter selectedEncounter = allEncounters.get(num);
            return selectedEncounter.toString();
        }
        else return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        String returnString = "";
        for (Encounter encounter : allEncounters.values()) {
            returnString += encounter.toString() + "\n";
        }
        return ("".equals(returnString)) ? "No encounters" : returnString;
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
       Ship s1 = new ManOWar("Victory", "Alan Aikin", 3,3,30);
       Ship s2 = new Frigate("Sophie", "Ben Baggins", 8,16, true);
       Ship s3 = new ManOWar("Endeavour", "Col Cannon", 4,2,20);
       Ship s4 = new Sloop("Arrow", "Dan Dare", 150, true);
       Ship s5 = new ManOWar("Belerophon", "Ed Evans", 8,3,50);
       Ship s6 = new Frigate("Surprise", "Fred Fox", 6,10, false);
       Ship s7 = new Frigate("Jupiter", "Gil Gamage", 7,20, false);
       Ship s8 = new Sloop("Paris", "Hal Henry", 200, true);
       Ship s9 = new Sloop("Beast", "Ian Idle", 400, false);
       Ship s10 = new Sloop("Athena", "John Jones", 100, true);
       
       reserve.add(s1);
       reserve.add(s2);
       reserve.add(s3);
       reserve.add(s4);
       reserve.add(s5);
       reserve.add(s6);
       reserve.add(s7);
       reserve.add(s8);
       reserve.add(s9);
       reserve.add(s10);

     }
     
    private void setupEncounters()
    {
        allEncounters.put(1, new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
        allEncounters.put(2, new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
        allEncounters.put(3, new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
        allEncounters.put(4, new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200));
        allEncounters.put(5, new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90));
        allEncounters.put(6, new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
        allEncounters.put(7, new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
        allEncounters.put(8, new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
        allEncounters.put(9, new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
        allEncounters.put(10, new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
    }
        
    // Useful private methods to "get" objects from collections/maps
    private Ship getSquadronShip(String name) {
        for (Ship ship: squadron) {
            if (ship.getName().equals(name)) {
                return ship;
            }
        }
        return null;
    }
    
    private Ship getReserveShip(String name) {
        for (Ship ship: reserve) {
            if (ship.getName().equals(name)) {
                return ship;
            }
        }
        return null;
    }
    

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param fileName name of the file to be read
     */
    public void readEncounters(String filename)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = br.readLine();
            int encounterCount = 1;
            while(line != null){
                // Split value by comma
                String[] values = line.split(",");
                List<String> encounterValue = new ArrayList<>(Arrays.asList(values));
                // Add encounter to list
                allEncounters.put(encounterCount, new Encounter(
                    encounterCount,
                    EncounterType.valueOf(encounterValue.get(0).toUpperCase()),
                    encounterValue.get(1),
                    Integer.parseInt(encounterValue.get(2)),
                    Integer.parseInt(encounterValue.get(3))
                ));
                line = br.readLine();
                encounterCount += 1;

            }
        }
        catch (Exception e){
        }
    }   
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
        try {
            FileOutputStream file = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
        }
        catch (IOException ex){}
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation
        SeaBattles game = null;
        try {
            FileInputStream file = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(file);
            game = (SeaBattles) in.readObject();
            in.close();
            file.close();
         }
        catch (Exception ex){}
        return game;
    } 
    
 
}



