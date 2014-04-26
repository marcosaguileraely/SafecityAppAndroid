package com.cool4code.safecity.safecity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity implements OnClickListener {
    // Declare Variables
    Button loginbutton;
    Button signup_button;
    Button newAccount;
    Button alreadyAccount;
    EditText password;
    EditText username;
    EditText seguridad_correo;
    EditText seguridad_movil;
    EditText seguridad_edad;
    TextView etiqueta_usuario;
    TextView etiqueta_clave;
    TextView etiqueta_correo;
    TextView etiqueta_movil;
    TextView etiqueta_edad;
    String usertxt;
    String passtxt;
    String emailtxt;
    String moviltxt;
    String edadtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username            = (EditText) findViewById(R.id.username);
        password            = (EditText) findViewById(R.id.password);
        seguridad_correo    = (EditText) findViewById(R.id.seguridad_correo);
        seguridad_movil     = (EditText) findViewById(R.id.seguridad_nomovil);
        seguridad_edad      = (EditText) findViewById(R.id.seguridad_edad);

        loginbutton         = (Button) findViewById(R.id.login);
        newAccount          = (Button) findViewById(R.id.seguridad_no_tienes_cuenta_boton);
        alreadyAccount      = (Button) findViewById(R.id.seguridad_ya_tienes_cuenta_button);
        signup_button       = (Button) findViewById(R.id.signupToParse);

        etiqueta_usuario    = (TextView) findViewById(R.id.etiqueta_usuario);
        etiqueta_clave      = (TextView) findViewById(R.id.etiqueta_clave);
        etiqueta_correo     = (TextView) findViewById(R.id.etiqueta_email);
        etiqueta_movil      = (TextView) findViewById(R.id.etiqueta_movil);
        etiqueta_edad       = (TextView) findViewById(R.id.etiqueta_edad);

        username.setVisibility(View.INVISIBLE);
        password.setVisibility(View.INVISIBLE);
        seguridad_correo.setVisibility(View.INVISIBLE);
        seguridad_movil.setVisibility(View.INVISIBLE);
        seguridad_edad.setVisibility(View.INVISIBLE);
        loginbutton.setVisibility(View.INVISIBLE);
        signup_button.setVisibility(View.INVISIBLE);
        etiqueta_usuario.setVisibility(View.INVISIBLE);
        etiqueta_clave.setVisibility(View.INVISIBLE);
        etiqueta_correo.setVisibility(View.INVISIBLE);
        etiqueta_edad.setVisibility(View.INVISIBLE);
        etiqueta_movil.setVisibility(View.INVISIBLE);

        newAccount.setOnClickListener(this);
        alreadyAccount.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
        signup_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if (v.getId()==findViewById(R.id.seguridad_no_tienes_cuenta_boton).getId()){
           Log.d("Action", "--> Registro de cuenta");
           etiqueta_usuario.setVisibility(View.VISIBLE);
           etiqueta_clave.setVisibility(View.VISIBLE);
           etiqueta_correo.setVisibility(View.VISIBLE);
           etiqueta_movil.setVisibility(View.VISIBLE);
           etiqueta_edad.setVisibility(View.VISIBLE);

           username.setVisibility(View.VISIBLE);
           password.setVisibility(View.VISIBLE);
           seguridad_correo.setVisibility(View.VISIBLE);
           seguridad_movil.setVisibility(View.VISIBLE);
           seguridad_edad.setVisibility(View.VISIBLE);
           signup_button.setVisibility(View.VISIBLE);

           loginbutton.setVisibility(View.INVISIBLE);
           newAccount.setVisibility(View.INVISIBLE);
           alreadyAccount.setVisibility(View.INVISIBLE);
       }

       if (v.getId()==findViewById(R.id.seguridad_ya_tienes_cuenta_button).getId()) {
           Log.d("Action", "--> Acceso a cuenta");
           etiqueta_usuario.setVisibility(View.VISIBLE);
           etiqueta_clave.setVisibility(View.VISIBLE);
           username.setVisibility(View.VISIBLE);
           password.setVisibility(View.VISIBLE);
           loginbutton.setVisibility(View.VISIBLE);

           etiqueta_correo.setVisibility(View.INVISIBLE);
           seguridad_correo.setVisibility(View.INVISIBLE);
           etiqueta_movil.setVisibility(View.INVISIBLE);
           etiqueta_edad.setVisibility(View.INVISIBLE);

           signup_button.setVisibility(View.INVISIBLE);
           newAccount.setVisibility(View.INVISIBLE);
           alreadyAccount.setVisibility(View.INVISIBLE);
       }

       if (v.getId()==findViewById(R.id.login).getId()){
           Log.d("Action", "==> Acceso de usuario");
           usertxt= username.getText().toString();
           passtxt= password.getText().toString();

           ParseUser.logInInBackground(usertxt, passtxt,
           new LogInCallback() {
               public void done(ParseUser parseUser, ParseException e) {
                   if (parseUser != null) {
                       // If user exist and authenticated, send user to Welcome.class
                       /*Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                       startActivity(intent);*/
                       Toast.makeText(getApplicationContext(),
                               "Acceso exitoso",
                               Toast.LENGTH_LONG).show();
                       finish();
                   } else {
                       Toast.makeText(
                               getApplicationContext(),
                               "El usuario no existe, por favor registrese.",
                               Toast.LENGTH_LONG).show();
                   }
               }
           });
       }

       if (v.getId()==findViewById(R.id.signupToParse).getId()){
           Log.d("Action", "==> Registro de usuario");
           // Retrieve the text entered from the EditText
           usertxt = username.getText().toString();
           passtxt = password.getText().toString();
           emailtxt = seguridad_correo.getText().toString();
           moviltxt = seguridad_movil.getText().toString();
           edadtxt = seguridad_edad.getText().toString();
           // Force user to fill up the form
           if (usertxt.equals("") && passtxt.equals("") && emailtxt.equals("") && moviltxt.equals("") && edadtxt.equals("")) {
               Toast.makeText(getApplicationContext(),
                       "¡Por favor completa todos los campos de registro!",
                       Toast.LENGTH_LONG).show();
           }else {
               // Save new user data into Parse.com Data Storage
               ParseUser user = new ParseUser();
               user.setUsername(usertxt);
               user.setPassword(passtxt);
               user.setEmail(emailtxt);
               user.put("mobileNumber", moviltxt);
               user.put("age", edadtxt);
               user.signUpInBackground(new SignUpCallback() {
                   public void done(ParseException e) {
                       if (e == null) {
//                           Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
//                           startActivity(intent);
                           // Show a simple Toast message upon successful registration
                           Toast.makeText(getApplicationContext(),
                                   "Registro exitoso. Por favor ingresa a SafeCity.",
                                   Toast.LENGTH_LONG).show();
                       } else {
                           Log.d("Action", e.toString());
                           Toast.makeText(getApplicationContext(),
                                   "Error en el registro.", Toast.LENGTH_LONG)
                                   .show();
                       }
                   }
               });
           }
       }
    }
}
