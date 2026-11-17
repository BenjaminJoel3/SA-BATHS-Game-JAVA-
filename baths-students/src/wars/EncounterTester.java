/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author Lenovo
 */
public class EncounterTester {
    public static void main(String[] args){
        Encounter e1 =  new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300);
        System.out.println("getBattleSkill()\nExpected: 3, Actual: " + e1.getSkill());
        System.out.println("\ngetEncounterNo()\nExpected: 1, Actual: " + e1.getID());
        System.out.println("\ngetBattleType()\nExpected: Battle, Actual: "+ e1.getType());
        System.out.println("\ngetPrize()\nExpected: 300, Actual: "+ e1.getPrize());
        System.out.println("\ntoString()\n" + e1.toString());
        
    }
}
