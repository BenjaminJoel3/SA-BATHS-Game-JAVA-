/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author benjoel
 */
public class Sloop extends Ship {
    
    private boolean hasDoctor;
    
    
    /** Constructor requires the name of the Ship, the name of the captain and the
     * int value of the Ships battleSKill
     * @param name the name of the Ship
     * @param captain name of the captain of the Ship
     * @param commissionFee commissionFee of the Ship
     * @param hasDoctor true/false if  Sloop has/hasn't doctor respectively
     */  
    public Sloop (String name, String captain, int commissionFee, boolean hasDoctor) {
        super(name, captain, 5);
        this.hasDoctor = hasDoctor;
        this.setCommissionFee(commissionFee);
    }
    
    
    /** returns true/false if sloop has has/hasn't a doctor respectively
    * @returns true/false if sloop has has/hasn't a doctor respectively
    */
    public boolean checkHasDoctor () {
        return hasDoctor;
    }
    
    
    /** returns true/false if sloop can fight EncounterType
    * @param encounter type of encounter to check
    * @returns true/false if Sloop can fight EncounterType
    */
    public boolean canFightEncounter (EncounterType encounter) {
        if (encounter == EncounterType.BLOCKADE) {
            return false;
        }
        else {
            return true;
        }
    }
    
    
    /**Returns a String representation of the details of the Sloop,including the name of the 
     * Sloop, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * if the Sloop has a doctor onboard
     * @return a String representation of the details of the Sloop,including the name of the 
     * Sloop, the name of the captain of the Ship, and the ships battleSkill
     * the commissionFee of the Ship, the state of the Ship
     * if the Sloop has a doctor onboard
     **/
    public String toString () {
        
        return
        "###############################" + "\n" +
        "Name: " + this.getName() + "\n" +
        "Ship Type: Sloop\n" +
        "Captain: " + this.getCaptain() + "\n" +
        "Battle Skill: " + this.getBattleSkill() + "\n" +
        "Commission Fee: " + this.getCommissionFee() + "\n" +
        "Doctor Onboard: " + this.checkHasDoctor() + "\n" +
        "Ship State: " + this.getState() + "\n" + 
        "###############################";
    }
    
}
