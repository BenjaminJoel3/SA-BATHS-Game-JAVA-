package wars;
import java.io.Serializable;
/**
 * This class simply implement encounters object
 * @author Viet
 * @version 19/03/25
 */
public class Encounter implements Serializable{
    private int encounterNo;
    private EncounterType type;
    private String location;
    private int battleSkill;
    private int prizeMoney;
    
    public Encounter(int id, EncounterType battleType, String loc, int skill, int prize){
        encounterNo = id;
        type = battleType;
        location = loc;
        battleSkill = skill;
        prizeMoney = prize;
    }
    
    /**
     * return the encounter number
     * @return the encounter number
     */
    public int getID(){
        return encounterNo;
    }
    
    /**
     * return the enum EncounterType 
     * @return the encounter type as an enum object 
     */
    public EncounterType getType(){
        return type;
    }
    
    /**
     * return the battle skill of encounter
     * @return the battle skill of encounter
     */
    public int getSkill(){
        return battleSkill;
    }
    
    /**
     * return the prize money of the encounter
     * @return the prize money of the encounter
     */
    public int getPrize(){
        return prizeMoney;
    }
    
    /**
     * Returns a String representation of an Encounter
     * @return a String of all details of an Encounter
     */
    public String toString(){
        return "##########################" + "\n"
             + "Encounter No: " + encounterNo     + "\n"
             + "Encounter Type: " + type        + "\n"
             + "Location: " + location   + "\n"
             + "Skill level: " + battleSkill   + "\n"
             + "Prize money: " + prizeMoney   + "\n"
             + "##########################" + "\n\n";
    }
}
