package com.bapps.saisathvik.nirmaan.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bapps.saisathvik.nirmaan.Home;
import com.bapps.saisathvik.nirmaan.Mysingleton;
import com.bapps.saisathvik.nirmaan.R;
import com.bapps.saisathvik.nirmaan.constants;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    static  final int REQUEST_LOCATION=1;
    double lattitude;
    double longitude;



    private ImageView choose;
    public MaterialEditText email, password, companyname, agencyowner, phone, agencyid, areas_served,wagesforskilled,wagesforsemiskilled;
    private ProgressDialog signup_dialog;
    private Button submit;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    CircleImageView acc_dp;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView id_agency_areas_servied;

    // private String  url="http://uniqueideas.in/hackathon/photoupload/upload.php";


    private static SignUpFragment INSTANCE = null;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new SignUpFragment();
        return INSTANCE;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                acc_dp.setImageBitmap(bitmap);
                acc_dp.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        choose = (ImageView) view.findViewById(R.id.choose);
        acc_dp = (CircleImageView) view.findViewById(R.id.acc_dp);
        wagesforskilled = (MaterialEditText)view.findViewById(R.id.wagesforskilled);
        wagesforsemiskilled = (MaterialEditText)view.findViewById(R.id.wagesforsemiskilled);
        id_agency_areas_servied = (TextView)view.findViewById(R.id.id_agency_areas_servied);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        getlocation();







        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

            }
            private void selectImage() {

                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);

            }
        });



        email=(MaterialEditText)view.findViewById(R.id.id_email);
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
        password=(MaterialEditText)view.findViewById(R.id.id_password);
        companyname=(MaterialEditText)view.findViewById(R.id.id_comapany_name);
        agencyowner=(MaterialEditText)view.findViewById(R.id.id_agency_owner);
        phone=(MaterialEditText)view.findViewById(R.id.id_phone);
        agencyid=(MaterialEditText)view.findViewById(R.id.id_agency_id);
        areas_served=(MaterialEditText)view.findViewById(R.id.id_agency_areas_served);


        signup_dialog=new ProgressDialog(getActivity());
        submit=(Button)view.findViewById(R.id.id_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agency_signup();
            }
        });

        return  view;

    }



//    private void UploadImage() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,constants.REGISTER_AGENCY_URL  , new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String Response = jsonObject.getString("message");
//                    acc_dp.setImageResource(0);
//                    acc_dp.setVisibility(View.GONE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("image",ImageToString(bitmap));
//                return params;
//            }
//        };
//
//
//    }



    void getlocation()
    {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }
        else
        {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if(location !=null)
            {
                lattitude = location.getLatitude();
                longitude = location.getLongitude();
                // id_agency_areas_servied.setText(String.valueOf(lattitude));
                getCompleteAddressString(lattitude,longitude);
//

            }
            else
            {
                id_agency_areas_servied.setText("error");
//                Log.i("error lattitude","error in lattitude");
//                Log.i("error longi","eror in longitude");
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_LOCATION :
                getlocation();
                break;
        }
    }


    private String ImageToString(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebytes =byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagebytes,Base64.DEFAULT);


    }

    private void agency_signup() {


        final String getemail=email.getText().toString().trim();
        final String getpassword=password.getText().toString().trim();
        final String getcompany_name=companyname.getText().toString().trim();
        final String getagency_owner=agencyowner.getText().toString().trim();
        final String getphone = phone.getText().toString().trim();
        final String getagency_id=agencyid.getText().toString().trim();
        final String getarea_served=areas_served.getText().toString().trim();
        final String getwagesfor_skilled = wagesforskilled.getText().toString().trim();
        final String getwagesfor_semiskilled = wagesforsemiskilled.getText().toString().trim();

        signup_dialog.setMessage("Please Hold On");
        signup_dialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, constants.REGISTER_AGENCY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    signup_dialog.dismiss();
                    acc_dp.setImageResource(0);
                    acc_dp.setVisibility(View.GONE);
                    Toast.makeText(getActivity().getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
                    getActivity().finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        signup_dialog.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG);

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("agency_email",getemail);
                params.put("agency_password",getpassword);
                params.put("agency_name",getcompany_name);
                params.put("agency_owner",getagency_owner);
                params.put("agency_phone",getphone);
                params.put("agency_id",getagency_id);
                params.put("agency_service_area",getarea_served);
                params.put("image",ImageToString(bitmap));
                params.put("photo_id",getagency_id);
                params.put("latitude",String.valueOf(lattitude));
                params.put("longitude",String.valueOf(longitude));
                params.put("skill_wage",getwagesfor_skilled);
                params.put("noskilled_wage",getwagesfor_semiskilled);
                return params;
            }
        };
        //requesthandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
        Mysingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }



    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
//                Log.w("My Current loction address", strReturnedAddress.toString());
                id_agency_areas_servied.setText(strReturnedAddress.toString());
            } else {
//                Log.w("My Current loction address", "No Address returned!");
                id_agency_areas_servied.setText("No address");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.w("My Current loction address", "Canont get Address!");
            id_agency_areas_servied.setText("Canont get Address!");
        }
        return strAdd;
    }





    public void isEmailValid(EditText editText)
    {
        if(editText.getText().toString()==null)
        {
            editText.setError("Email is Empty");
        }
        else if(isEmail_Valid(editText.getText().toString())==false)
        {
            editText.setError("Invalid email");
        }
    }
    boolean isEmail_Valid(CharSequence email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }





}
