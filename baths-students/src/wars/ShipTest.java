/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author Adrian
 */
public class ShipTest {
    public static void main(String[] args) {
        /*
        Testing all "get" methods work as proper, including the toString method.
        This needs manual inspection with the results, and calculations from the arguments given below
        */
        Ship testFrigate1 = new Frigate("Frigate Test 1", "AAA", 6, 4, true);
        Ship testFrigate2 = new Frigate("Frigate Test 2", "BBB", 3, 6, false);
        
        Ship testManowar1 = new ManOWar("ManOWar Test 1", "CCC", 6, 2, 25);
        Ship testManowar2 = new ManOWar("ManOWar Test 2", "DDD", 8, 3, 40);
        
        Ship testSloop1 = new Sloop("Sloop Test 1", "EEE", 150, true);
        Ship testSloop2 = new Sloop("Sloop Test 2", "FFF", 250, false);
        
        PrintExpectedValues(testFrigate1);
        System.out.println(testFrigate1);
        
        PrintExpectedValues(testFrigate2);
        System.out.println(testFrigate2);
        
        PrintExpectedValues(testManowar1);
        System.out.println(testManowar1);
        
        PrintExpectedValues(testManowar2);
        System.out.println(testManowar2);
        
        PrintExpectedValues(testSloop1);
        System.out.println(testSloop1);
        
        PrintExpectedValues(testSloop2);
        System.out.println(testSloop2);
        
        //testing functionality below
        
        //test canFightEncounter
        System.out.println("Testing ship encounter types:\nResults are tests for Blockade, Battle, and Skirmish in that order.");
        
        System.out.println("\nFrigate 1:\nExpected Values: true, true, true\nActual Values: "
                + testFrigate1.canFightEncounter(EncounterType.BLOCKADE) + ", "
                + testFrigate1.canFightEncounter(EncounterType.BATTLE) + ", "
                + testFrigate1.canFightEncounter(EncounterType.SKIRMISH)
        );
        assert(testFrigate1.canFightEncounter(EncounterType.BLOCKADE));
        assert(testFrigate1.canFightEncounter(EncounterType.BATTLE));
        assert(testFrigate1.canFightEncounter(EncounterType.SKIRMISH));
        
        System.out.println("\nFrigate 2:\nExpected Values: false, true, true\nActual Values: "
                + testFrigate2.canFightEncounter(EncounterType.BLOCKADE) + ", "
                + testFrigate2.canFightEncounter(EncounterType.BATTLE) + ", "
                + testFrigate2.canFightEncounter(EncounterType.SKIRMISH)
        );
        assert(!testFrigate2.canFightEncounter(EncounterType.BLOCKADE));
        assert(testFrigate2.canFightEncounter(EncounterType.BATTLE));
        assert(testFrigate2.canFightEncounter(EncounterType.SKIRMISH));
        
        System.out.println("\nManOWar 1:\nExpected Values: true, true, false\nActual Values: "
                + testManowar1.canFightEncounter(EncounterType.BLOCKADE) + ", "
                + testManowar1.canFightEncounter(EncounterType.BATTLE) + ", "
                + testManowar1.canFightEncounter(EncounterType.SKIRMISH)
        );
        assert(testManowar1.canFightEncounter(EncounterType.BLOCKADE));
        assert(testManowar1.canFightEncounter(EncounterType.BATTLE));
        assert(!testManowar1.canFightEncounter(EncounterType.SKIRMISH));
        
        System.out.println("\nSloop 1:\nExpected Values: false, true, true\nActual Values: "
                + testSloop1.canFightEncounter(EncounterType.BLOCKADE) + ", "
                + testSloop1.canFightEncounter(EncounterType.BATTLE) + ", "
                + testSloop1.canFightEncounter(EncounterType.SKIRMISH)
        );
        assert(!testSloop1.canFightEncounter(EncounterType.BLOCKADE));
        assert(testSloop1.canFightEncounter(EncounterType.BATTLE));
        assert(testSloop1.canFightEncounter(EncounterType.SKIRMISH));
    }
    
    //Function to allow me to be lazy and not write prints for each individual ship
    //NOTE: usage of instanceof should be kept to a minimum, to reduce places that need to be updated if a new ship type is added
    private static void PrintExpectedValues(Ship ship) {
        String printString = "----------------------\nValues expected below:\n" +
                "Name: " + ship.getName() + "\n" + 
                "Captain: " + ship.getCaptain() + "\n" + 
                "Battle Skill: " + ship.getBattleSkill() + "\n" +
                "Commission Fee: " + ship.getCommissionFee() + "\n";
        
        //using switch statement below would be better, but this was programmed
        //in java 17 so it wouldnt compile for me
        if (ship instanceof Frigate frigate) {
            printString += "Pinnace: " + frigate.checkHasPinnace() + "\n" +
                "Cannons: " + frigate.getNoOfCannons() + "\n";
        }
        else if (ship instanceof ManOWar manowar) {
            printString += "Marines Onboard: " + manowar.getNoOfMarines() + "\n" +
                "Cannon Decks: " + manowar.getNoOfCannonDecks() + "\n";
        }
        else if (ship instanceof Sloop sloop) {
            printString += "Doctor Onboard: " + sloop.checkHasDoctor() + "\n";
        }
        
        printString += "Ship State: " + ship.getState() + "\n" + 
            "Real values shown below\n----------------------\n";
        
        System.out.println(printString);
    }
}
