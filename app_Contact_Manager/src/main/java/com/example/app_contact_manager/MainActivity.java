package com.example.app_contact_manager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    EditText txtName,txtPhone,txtEmail,txtAddress;
    List<Contact> lstConact= new ArrayList<Contact>();
    ListView lstViewContact;

    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cntContactCreator, new PlaceholderFragment())
                    .commit();
        }

        txtName = (EditText)findViewById(R.id.txtname);
        txtPhone = (EditText)findViewById(R.id.txtphone);
        txtEmail= (EditText)findViewById(R.id.txtemail);
        txtAddress = (EditText)findViewById(R.id.txtaddress);
        lstViewContact = (ListView) findViewById(R.id.listView);
        TabHost tbHost = (TabHost) findViewById(R.id.tabHost);

        tbHost.setup();

        TabHost.TabSpec tbspec= tbHost.newTabSpec("creator");
        tbspec.setContent(R.id.creatortab);
        tbspec.setIndicator("Creator");
        tbHost.addTab(tbspec);

        tbspec= tbHost.newTabSpec("contactlist");
        tbspec.setContent(R.id.contactlist);
        tbspec.setIndicator("contactlist");
        tbHost.addTab(tbspec);

        btnAdd = (Button) findViewById(R.id.btnAddcontact);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact(txtName.toString(),txtPhone.toString(),txtEmail.toString(),txtAddress.toString());
                populateContactinlstViewContact();
                Toast.makeText(getApplicationContext(),"Your contact has been successfully created",Toast.LENGTH_SHORT).show();
            }
        });

        /** event to enable add button */
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                btnAdd.setEnabled(txtName.getText().toString().trim().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void addContact(String name,String phone, String email, String address)
    {
        lstConact.add(new Contact(name,phone,email,address));
    }
    public void populateContactinlstViewContact()
    {
        /**To view each contact detail we use array adapter.
         * to get view of all contact in layout_res_contactitem form */

        ArrayAdapter<Contact> adapter_contact = new ContactArrayAdapter();
        lstViewContact.setAdapter(adapter_contact);
    }

    public class ContactArrayAdapter extends ArrayAdapter<Contact>
    {
    /** Constructor*/
       /** we have to create list item layout(Resources)*/
        public  ContactArrayAdapter ()
        {
            super(MainActivity.this,R.layout.layout_res_contactitem,lstConact);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.layout_res_contactitem,parent,false);

            Contact objContact = lstConact.get(position);

            TextView name = (TextView) view.findViewById(R.id.txtName_res);
            name.setText(objContact.get_name());

            TextView phone = (TextView) view.findViewById(R.id.txt_PhoneNumber_res);
            phone.setText(objContact.get_phoneNumber());

            TextView email = (TextView) view.findViewById(R.id.txtemailid_res);
            email.setText(objContact.get_emailId());

            TextView address = (TextView) view.findViewById(R.id.txtaddress_res);
            address.setText(objContact.get_address());

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
