package za.ac.cput.factory;

import za.ac.cput.domain.AboutUs;

public class AboutUsFactory
{

    public AboutUs create(){
        return new AboutUs.Builder().build();
    }

    public static AboutUs createAboutUs(int id, String address, String officeHours, String email, String telephone, String whatsApp){
        return new AboutUs.Builder()
                .setId(id)
                .setAddress(address)
                .setOfficeHours(officeHours)
                .setEmail(email)
                .setTelephone(telephone)
                .setWhatsApp(whatsApp)
                .build();
    }
}


