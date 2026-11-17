/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;
import java.util.*;
import java.io.Serializable;

/**
 *
 * @author benjoel
 */
abstract class Ship implements Serializable{
    
    private String name;
    private String captain;
    private int battleSkill;
    private int commissionFee;
    private ShipState state;
    
    
    /** Constructor requires the name of the Ship, the name of the captain and the
     * int value of the Ships battleSKill
     * @param name the name of the Ship
     * @param captain name of the captain of the Ship
     * * @param battleSkill battleSkill of the Ship
     */  
    public Ship (String name, String captain, int battleSkill) {
        
        this.name = name;
        this.captain = captain;
        this.battleSkill = battleSkill;
        this.commissionFee = 0;
        this.state = ShipState.RESERVE;
    }
    
    
    /** returns name of Ship (String)
    * @returns name of Ship (String)
    */
    public String getName () {
        return name;
    }
    
    
    /** returns captain (String) of the Ship
    * @returns captain (String) of the Ship
    */
    public String getCaptain () {
        return captain;
    }
    
    
    /** returns battleSkill (int/10) of the Ship
    * @returns battleSkill (int/10) of the Ship
    */
    public int getBattleSkill () {
        return battleSkill;
    }
    
    
    /** setCommissionFee sets new commissionFee for ship
     * @param fee the new commissionFee
     */  
    public void setCommissionFee (int fee) {
        commissionFee = fee;
    }
    
    
    /** returns commissionFee (int) of the Ship
    * @returns commissionFee (int) of the Ship
    */
    public int getCommissionFee () {
        return commissionFee;
    }
    
    
    /** returns State (String) of the Ship
    * @returns State (String) of the Ship
    */
    public ShipState getState () {
        return state;
    }
    
    
    
    public void changeState (ShipState newState) {
        state = newState;
    }
    
    
    
    public abstract boolean canFightEncounter(EncounterType encounter);
    public abstract String toString ();
    
}
