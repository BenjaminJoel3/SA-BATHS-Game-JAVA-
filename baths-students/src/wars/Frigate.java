/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author benjoel
 */
public class Frigate extends Ship {
    
    private int cannons;
    private boolean hasPinnace;
    
    
    /** Constructor requires the name of the Ship, the name of the captain and the
     * int value of the Ships battleSKill
     * @param name the name of the Ship
     * @param captain name of the captain of the Ship
     * @param battleSkill battleSkill of the Ship
     * @param cannons number of cannons the Frigate has
     * @param hasPinnace true/false if Frigate has/hasn't a pinnace respectively
     */  
    public Frigate (String name, String captain, int battleSkill, int cannons, boolean hasPinnace) {
        super(name, captain, battleSkill);
        this.cannons = cannons;
        this.hasPinnace = hasPinnace;
        this.setCommissionFee(10 * this.cannons);
    }
    
    
    /** returns number of cannons (int) the frigate has
    * @returns number of cannons (int) the frigate has
    */
    public int getNoOfCannons () {
        return cannons;   
    }
    
    
    /** returns true/false if sloop has has/hasn't a pinnace respectively
    * @returns true/false if sloop has has/hasn't a pinnace respectively
    */
    public boolean checkHasPinnace () {
        return hasPinnace;
    }
    
    
    /** returns true/false if sloop can fight EncounterType
    * @param encounter type of encounter to check
    * @returns true/false if Frigate can fight EncounterType
    */
    public boolean canFightEncounter (EncounterType encounter) {
        if (encounter == EncounterType.BLOCKADE) {
            if (this.checkHasPinnace() == true) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
    
    /**Returns a String representation of the details of the Frigate,including the name of the 
     * Ship, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * if the Frigate has a pinnace, the number of cannons the Frigate has
     * @return a String representation of the details of the Frigate,including the name of the 
     * Ship, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * if the Frigate has a pinnace, the number of cannons the Frigate has
     **/
    public String toString () {
        
        return
        "###############################" + "\n" +
        "Name: " + this.getName() + "\n" +
        "Ship Type: Frigate\n" +
        "Captain: " + this.getCaptain() + "\n" +
        "Battle Skill: " + this.getBattleSkill() + "\n" +
        "Commission Fee: " + this.getCommissionFee() + "\n" +
        "Pinnace: " + this.checkHasPinnace() + "\n" +
        "Cannons : " + this.getNoOfCannons() + "\n" +
        "Ship State: " + this.getState() + "\n" + 
        "###############################";
    }
    
}
