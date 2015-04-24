package com.app.rhanfe006.ineaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {
    // Declaramos los botones y los editText.

    Button Ingresar;
    EditText Usuario, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mostrar los elementos recuperados de R por su Id.
        Ingresar = (Button) findViewById (R.id.Ingresar);
        Usuario = (EditText) findViewById (R.id.Usuario);
        Password = (EditText) findViewById (R.id.Password);
        Ingresar.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) { //Tomar los elementos por el Id.
            case R.id.Ingresar: //En caso de presionar el boton
                String nombre = Usuario.getText().toString();
                if (Usuario.getText().toString().equals("ranfe") && Password.getText().toString().equals("1414"))
                {

                    Intent intent = new Intent("com.app.rhanfe006.ineaapp.bienvenido");
                    Bundle bolsa = new Bundle();
                    bolsa.putString("NOMBRE", nombre);
                    intent.putExtras(bolsa);
                    startActivity(intent);
                }

                else {
                    Toast toast = Toast.makeText(this, "Contraseña o Usuario incorrectos, Verifique sus datos", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break; //Romper la actividad.
        }

    }
}
