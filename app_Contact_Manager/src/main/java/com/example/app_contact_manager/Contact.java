package com.example.app_contact_manager;

/**
 * Created by AlTamash on 25/03/14.
 */
public class Contact {
    /** Private Variables */
    private String _name;
    private String _phoneNumber;
    private String _emailId;
    private String _address;

    /** Properties only get because we will only pass values we will */
    public String get_name() {
        return _name;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public String get_emailId() {
        return _emailId;
    }

    public String get_address() {
        return _address;
    }


    /** Constructor */

    public Contact(String name, String phoneNumber, String emailId, String address )
    {
        _name = name;
        _phoneNumber=phoneNumber;
        _emailId=emailId;
        _address=address;
    }



}
