package com.o2.myo2;

import com.o2.registration.adapter.CompanionAdapter;
import com.o2.registration.adapter.CompanionAdapterHome;
import com.o2.registration.phase2.exception.CompanionAdapterException;
import com.o2.registration.phase2.exception.RegistrationException;
import com.o2.registration.phase2.exception.UsernameIsReservedException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

public class EJBConnectorUtility {
    public static void main(String args[]) throws IOException, NamingException, CompanionAdapterException, CreateException, UsernameIsReservedException, RegistrationException {
        String ejbXmlPart1 = "<users><userDetails action=\"Create\"><user custnum=\"0\" IdentityUID=\"2000000077799999999\" UID=\"2000000077799999999\" origUid=\"2000000077799999999\" id=";
        String ejbXmlPart2= "\" domain=\"stf.ref.o2.co.uk\" password=\"Password123\" forename=\"Trail1\" lastname=\"LastShot1\" title=\"MS\" alternativeEmail=";
        String ejbXmlPart3=" alternativeContactNumber=";
        String ejbXmlPart4= " dateOfBirth=\"1976-03-24\" partner=\"CPW\" MSISDN=";
        String ejbXmlPart5=" MSISDNvalid=\"Yes\" wantsGenieMarketting=\"No\" wantsOtherMarketting=\"No\" segmentation=\"none\" customerType=\"CON\" contactMedium=\"Email\" saleDate=\"2013-05-31\" owningBusinessDivision=\"O2UK\" SecurityAnswer=\"cts\" SecurityQuestion=\"Name of your company\"><Address PTCABS=\"MATCHED\"><houseName>41</houseName><addressLine1>High Street</addressLine1><townCity>LONDON</townCity><postcode>NW1 2PL</postcode><country>UK</country></Address></user></userDetails></users>";
        String objectName = "CompanionAdapterBean";
        Properties prop = new Properties();
        prop.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        prop.put("java.naming.provider.url", "t3://localhost:8080");
        Context ic = new InitialContext(prop);

        CompanionAdapterHome home = (CompanionAdapterHome) ic.lookup(objectName);  // lookup the bean
        CompanionAdapter companionAdapter = home.create();   // create a bean instance

        for(int count=0;count <= 2 ;count++)
        {
            String finalEJB= ejbXmlPart1 +"\"P2IncidentUsers"+count + ejbXmlPart2 + "\"P2IncidentUsers"+count+"@gmail.com\"" +
                    ejbXmlPart3 + "\"0744222"+(count+1000)+ "\"" + ejbXmlPart4 + "\"0744222"+(count+1000)+ "\"" +ejbXmlPart5;
            companionAdapter.createOrUpdateUser(finalEJB);
        }
    }
}