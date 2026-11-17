/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author benjoel
 */
public class ManOWar extends Ship {
    
    private int cannonDecks;
    private int marines;
    
    
    /** Constructor requires the name of the Ship, the name of the captain and the
     * int value of the Ships battleSKill
     * @param name the name of the Ship
     * @param captain name of the captain of the Ship
     * * @param battleSkill battleSkill of the Ship
     * @param cannonDecks commissionFee of the Ship
     * @param marines number of marines onboard the ManOWar
     */  
    public ManOWar (String name, String captain, int battleSkill, int cannonDecks, int marines) {
    
        super(name, captain, battleSkill);
        this.cannonDecks = cannonDecks;
        this.marines = marines;
        
        if (this.cannonDecks <= 2) {
            this.setCommissionFee(300);
        }
        else {
            this.setCommissionFee(500);
        }
    }
    
    
    /** returns number of cannonDecks (int) the ManOWar has
    * @returns number of cannonDecks (int) the ManOWar has
    */
    public int getNoOfCannonDecks () {
        return cannonDecks;
    }
    
    
    /** returns number of marines (int) onboard the ManOWar
    * @returns number of marines (int) onboard the ManOWar
    */
    public int getNoOfMarines () {
        return marines;
    }
    
    
    /** returns true/false if sloop can fight EncounterType
    * @param encounter type of encounter to check
    * @returns true/false if ManOWar can fight EncounterType
    */
    public boolean canFightEncounter (EncounterType encounter) {
        if (encounter == EncounterType.SKIRMISH) {
            return false;
        }
        else {
            return true;
        }
    }
    
    
    /**Returns a String representation of the details of the ManOWar,including the name of the 
     * Ship, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * the number of cannonDecks the ManOWar has, the number of marines onboard
     * @return a String representation of the details of the ManOWar,including the name of the 
     * Ship, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * the number of cannonDecks the ManOWar has, the number of marines onboard
     **/
    public String toString () {
        
        return
        "###############################" + "\n" +
        "Name: " + this.getName() + "\n" +
        "Ship Type: ManOWar\n" +
        "Captain: " + this.getCaptain() + "\n" +
        "Battle Skill: " + this.getBattleSkill() + "\n" +
        "Commission Fee: " + this.getCommissionFee() + "\n" +
        "Marines Onboard: " + this.getNoOfMarines() + "\n" +
        "Cannon Decks: " + this.getNoOfCannonDecks() + "\n" +
        "Ship State: " + this.getState() + "\n" + 
        "###############################";
    }
}
