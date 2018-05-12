package com.bapps.saisathvik.nirmaan.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bapps.saisathvik.nirmaan.Home;
import com.bapps.saisathvik.nirmaan.R;
import com.bapps.saisathvik.nirmaan.constants;
import com.bapps.saisathvik.nirmaan.requesthandler;
import com.bapps.saisathvik.nirmaan.sharedpreference_agencylogin;
import com.bapps.saisathvik.nirmaan.sharedpreferencemanager;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private MaterialEditText email, password;
    private Button login,btn_guest_user;
    private ProgressDialog homedialog;
    private String valid_email;
    public String agency_id_for_data;

    private static SignInFragment INSTANCE = null;


    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new SignInFragment();
        return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        if (sharedpreference_agencylogin.getInstance(getActivity()).isAgencylogedin()) {
            getActivity().finish();
            startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
        }


          email = (MaterialEditText) view.findViewById(R.id.loginmail);
          email.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void afterTextChanged(Editable editable) {
                  isEmailValid(email);

              }
          });
        password = (MaterialEditText) view.findViewById(R.id.loginpassword);
        login = (Button) view.findViewById(R.id.loginbutton);
        btn_guest_user =  (Button)view.findViewById(R.id.btn_guest_user) ;



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agency_login();
            }
        });
        btn_guest_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getActivity().getApplicationContext(),Home.class);
                in.putExtra("guest","guest");
                startActivity(in);
                //startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
                getActivity().finish();

            }
        });



        return view;
    }


    public void agency_login() {
        final String agency_email = email.getText().toString().trim();
        final String agency_password = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.LOGIN_AGENCY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")) {
                        Log.i("hi", "error");
                        sharedpreference_agencylogin.getInstance(getContext()).agencylogin(jsonObject.getString("agency_id"),
                                jsonObject.getString("agency_email"), jsonObject.getString("agency_password"),
                                jsonObject.getString("agency_name"), jsonObject.getString("agency_owner"),
                                jsonObject.getString("agency_phone"), jsonObject.getString("agency_service_area")
                                // jsonObject.getString("agency_rating")
                        );

                        startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
                        getActivity().finish();
                    } else if (jsonObject.getBoolean("error")) {
                        Toast.makeText(getActivity().getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("agency_email", agency_email);
                params.put("agency_password", agency_password);
                return params;
            }
        };
        requesthandler.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    public void isEmailValid(EditText editText)
    {
        if(editText.getText().toString()==null)
        {
            editText.setError("Email is Empty");
            valid_email=null;
        }
        else if(isEmail_Valid(editText.getText().toString())==false)
        {
            editText.setError("Invalid Email");
        }
    }
    boolean isEmail_Valid(CharSequence email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
