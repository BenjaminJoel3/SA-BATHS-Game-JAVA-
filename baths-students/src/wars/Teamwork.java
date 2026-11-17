package wars; 


/**
 * Details of your team
 * 
 * @author Viet 
 * @version 20/03/25
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "32";
        
        details[1] = "Hoang";
        details[2] = "Viet Anh";
        details[3] = "22085746";

        details[4] = "Ithal";
        details[5] = "Ansa";
        details[6] = "22101575";

        details[7] = "Joel";
        details[8] = "Benjamin";
        details[9] = "19054139";


        details[10] = "Wood";
        details[11] = "Adrian";
        details[12] = "22060655";

	
	   // only if applicable
        details[13] = "surname of member5";
        details[14] = "first name of member5";
        details[15] = "SRN of member5";


    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
