package in.asterisc.myasterisc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class AdmissionActivity extends AppCompatActivity {

    EditText editTextFullName, editTextEmail,editTextMobile, editTextQualification, editTextCollege;
    Button buttonSubmit;
    FloatingActionButton callbtn, whatsappbtn, facebookBtn, instagramBtn, youtubeBtn, locationBtn;
    RadioGroup radioGroup,radioGroup2;
    RadioButton radioButtonF,radioButtonM ;
    LinearLayout linearLayout;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10;
    String gender;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);

        editTextFullName = (EditText) findViewById(R.id.fullname);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextMobile = (EditText) findViewById(R.id.mymobile);
        editTextQualification = (EditText) findViewById(R.id.qualification);
        editTextCollege = (EditText) findViewById(R.id.college);
        buttonSubmit = (Button) findViewById(R.id.submit);

        radioGroup=(RadioGroup)findViewById(R.id.radioG);
        radioButtonF=(RadioButton)findViewById(R.id.rFemale);
        radioButtonM=(RadioButton)findViewById(R.id.rMale);

        linearLayout=(LinearLayout)findViewById(R.id.linearLay) ;
        cb1=(CheckBox)findViewById(R.id.chb1);
        cb2=(CheckBox)findViewById(R.id.chb2);
        cb3=(CheckBox)findViewById(R.id.chb3);
        cb4=(CheckBox)findViewById(R.id.chb4);
        cb5=(CheckBox)findViewById(R.id.chb5);
        cb6=(CheckBox)findViewById(R.id.chb6);
        cb7=(CheckBox)findViewById(R.id.chb7);
        cb8=(CheckBox)findViewById(R.id.chb8);
        cb9=(CheckBox)findViewById(R.id.chb9);
        cb10=(CheckBox)findViewById(R.id.chb10);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rFemale:
                        gender="female";
                        break;

                    case R.id.rMale:
                        gender="male";
                        break;
                }
            }
        });

        //floating action button
        callbtn = (FloatingActionButton) findViewById(R.id.fab_call);
        whatsappbtn = (FloatingActionButton) findViewById(R.id.fab_whatsapp);
        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:7744822228"));
                startActivity(callIntent);
                if (ActivityCompat.checkSelfPermission(AdmissionActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Please allow CALL permission", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });
        whatsappbtn = (FloatingActionButton) findViewById(R.id.fab_whatsapp);
        whatsappbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = "I need help regarding admission";
                    String toNumber = "+917744822228";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });

        facebookBtn = (FloatingActionButton) findViewById(R.id.fab_facebook);
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/asterisccomputer/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.facebook.katana");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/asterisccomputer/")));
                }
            }
        });
        instagramBtn = (FloatingActionButton) findViewById(R.id.fab_instagram);
        instagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.instagram.com/asterisc_computer/?hl=bg");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/asterisc_computer/?hl=bg")));
                }
            }
        });

        youtubeBtn = (FloatingActionButton) findViewById(R.id.fab_youtube);
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC874req7IgQzGOZ1MfTMlVA")));
            }
        });

        locationBtn=(FloatingActionButton)findViewById(R.id.fab_location);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://g.page/asterisc-computer?we"));
                startActivity(intent);
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate(view))
                {
                    addItemToSheet();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"sorry no reg",Toast.LENGTH_LONG).show();
                }


            }

        });


    }

    public boolean validate(View view) {
        boolean bl = true;
        String mobileNo = editTextMobile.getText().toString().trim();
        String userEmail = editTextEmail.getText().toString();


        if (editTextFullName.getText().length() <= 2) {
            editTextFullName.setBackgroundColor(Color.argb(50, 255, 0, 0));
            editTextFullName.setError((CharSequence)"Please enter Your Full Name");
            // Snackbar.make(view, "Please Enter your FULL NAME", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            // bl = false;
        }
        else
        {
            editTextFullName.setBackgroundResource(R.drawable.rounded_edittext);
        }



        if(radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            radioGroup.setBackgroundColor(Color.argb(50,255,0,0));
            // bl = false;

        }
        else{
            radioGroup.setBackgroundResource(R.drawable.rounded_edittext);

        }



        if (mobileNo.isEmpty() || mobileNo.length() > 11 || mobileNo.length() < 10) {
            editTextMobile.setError((CharSequence)"Please enter valid mobile no.");
            editTextMobile.setBackgroundColor(Color.argb(50, 255, 0, 0));
            Snackbar.make(view, "Please Enter your correct Mobile No.", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            // bl = false;
        }

        else
        {
            editTextMobile.setBackgroundResource(R.drawable.rounded_edittext);
        }




        if (userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher((CharSequence)userEmail).matches()) {
            editTextEmail.setBackgroundColor(Color.argb(50, 255, 0, 0));
            editTextEmail.setError((CharSequence)"Enter Valid Email Address");
            // bl = false;
        }
        else
        {
            editTextEmail.setBackgroundResource(R.drawable.rounded_edittext);
        }




        if(editTextQualification.getText().length()<=0)
        {
            editTextQualification.setBackgroundColor(Color.argb(50, 255, 0, 0));
            editTextQualification.setError((CharSequence)"Enter Your Qualification");
            Snackbar.make(view, "Please Enter your Qualification", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            //  bl = false;
        }
        else
        {
            editTextQualification.setBackgroundResource(R.drawable.rounded_edittext);
        }



        if(editTextCollege.getText().length()<=0)
        {
            editTextCollege.setBackgroundColor(Color.argb(50, 255, 0, 0));
            editTextCollege.setError((CharSequence)"Enter Your College Name");
            Snackbar.make(view, "Please Enter your College", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            //  bl = false;
        }
        else
        {
            editTextCollege.setBackgroundResource(R.drawable.rounded_edittext);
        }




        if(cb1.isChecked()||cb2.isChecked()||cb3.isChecked()
                ||cb4.isChecked()||cb5.isChecked()||cb6.isChecked()||cb7.isChecked()||cb8.isChecked()||cb9.isChecked()) {
            linearLayout.setBackgroundResource(R.drawable.rounded_edittext);
            // bl = false;
        }

        else
        {

            linearLayout.setBackgroundColor(Color.argb(50,255,0,0));
        }


        return bl; }

    private void addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this, "Uploading Data....", "Please wait");
        final String fullname=editTextFullName.getText().toString().trim();
        final String email =editTextEmail.getText().toString().trim();
        final String mobile =editTextMobile.getText().toString().trim();
        final String qualification =editTextQualification.getText().toString().trim();
        final String college =editTextCollege.getText().toString().trim();




        final StringBuilder sb=new StringBuilder("");

        if(cb1.isChecked()){
            String s2=cb1.getText().toString();
            sb.append(s2);
        }

        if(cb2.isChecked()){
            String s2=cb2.getText().toString();
            sb.append(" , "+s2);

        }

        if(cb3.isChecked()){
            String s2=cb3.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb4.isChecked()){
            String s2=cb4.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb5.isChecked()){
            String s2=cb5.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb6.isChecked()){
            String s2=cb6.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb7.isChecked()){
            String s2=cb7.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb8.isChecked()){
            String s2=cb8.getText().toString();
            sb.append(" , "+s2);

        }
        if(cb9.isChecked()){
            String s2=cb9.getText().toString();
            sb.append(" , "+s2);

        }

        if(cb10.isChecked()){
            String s2=cb10.getText().toString();
            sb.append(" , "+s2);

        }


        if(sb!=null && !sb.toString().equals("")){
            course=sb.toString();

        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://script.google.com/macros/s/AKfycbxSIT79qC9B3Rr5jWW6afP3VD2kADoOjn5ERznJhXPqfHoirkvL6d3dsXli0658LQ8N/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        myToast("Thank you for Register");
                        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);*/
                       // launchHomeScreen();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        myToast("Sorry! something went wrong....");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action", "addItem");
                parmas.put("fullname",fullname );
                parmas.put("gender",gender );
                parmas.put("email", email);
                parmas.put("mobile", mobile);
                parmas.put("qualification", qualification);
                parmas.put("college", college);
                parmas.put("course", course);
                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }

    //custom Toast with color
    void myToast(String msg) {
        Toast toast = Toast.makeText(AdmissionActivity.this, msg, Toast.LENGTH_LONG);
        View view = toast.getView();
        //To change the Background of Toast
        view.setBackgroundResource(R.drawable.rounded);
        view.setAlpha(0.5f);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        //Shadow of the Of the Text Color
        text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        text.setTextColor(Color.WHITE);
        text.setTextSize(Integer.valueOf(16));
        toast.show();
    }
    /*
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(RegsterActivity.this, MainActivity.class));
        finish();
    }*/
}

