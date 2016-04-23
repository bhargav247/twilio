/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twilioapp;
import com.twilio.sdk.LookupsClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.lookups.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bhargav
 */
public class PhoneNumber {
    // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC4b30ff9e021159ad2808c3bfe5153719";
  public static final String AUTH_TOKEN = "d49964c2cfd003eaefc275aa20404b3e";
  //public Set<PhoneNumber> getValidPhoneNumber(final Collection<String> phoneNumbers);
  public static void main(String[] args) throws TwilioRestException {
        try{
        //  1. Get a connection to database
        Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdem","root","bhargav");
        //2. Create a statement
        Statement stmt= myconn.createStatement();
         //3. Execute a SQL Query
        ResultSet myRs= stmt.executeQuery("select phonenumber from jdbcdem.phonenum");
        LookupsClient client = new LookupsClient(ACCOUNT_SID, AUTH_TOKEN);
                        // Process the result
         List<String> arr = new ArrayList<>();
         int i=1,n=0;
         while(myRs.next()){
        
         arr.add(myRs.getString(i));
         String[] strarray = arr.toArray(new String[i]);
         PhoneNumber number=client.getPhoneNumber(strarray[n],true);
         
         System.out.println(number.getType());
      
        }
    }
    
    catch(Exception e){
        e.printStackTrace();
    }   
    
  
}
}