package com.app.rhanfe006.ineaapp;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class bienvenido extends Activity implements View.OnClickListener {

    EditText et_idusuario, et_nombres, et_apellidos, et_actividades, et_nivel, et_modulo, et_fecha;

    TextView nombre;
    String actividadM, nivelE;
    CheckBox inicial, intermedio, avanzado;
    CheckBox estudiar, aplicarexamen, consultapersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        nombre = (TextView) findViewById(R.id.tvNombre);
        Bundle bolsa = getIntent().getExtras();
        nombre.setText(bolsa.getString("NOMBRE"));

        et_idusuario = (EditText) findViewById(R.id.et_idusuario);
        et_nombres = (EditText) findViewById(R.id.et_nombres);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos);
        et_actividades = (EditText) findViewById(R.id.et_actividades);
        et_nivel = (EditText) findViewById(R.id.et_nivel);
        et_modulo = (EditText) findViewById(R.id.et_modulo);
        et_fecha = (EditText) findViewById(R.id.et_fecha);

        inicial= (CheckBox) findViewById(R.id.rdinicial);
        intermedio= (CheckBox) findViewById(R.id.rdintermedio);
        avanzado= (CheckBox) findViewById(R.id.rdavanzado);
        estudiar = (CheckBox) findViewById(R.id.rdestudiar);
        aplicarexamen = (CheckBox) findViewById(R.id.rdaplicarexamen);
        consultapersonal = (CheckBox) findViewById(R.id.rdconsultapersonal);


    }
    public void alta (View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String user = et_nombres.getText().toString();
        String password = et_apellidos.getText().toString();
        String nombre = et_actividades.getText().toString();
        String nivelE = et_nivel.getText().toString();
        String apellidom = et_modulo.getText().toString();
        String fecha = et_fecha.getText().toString();


        if (inicial.isChecked() == true) {
            nivelE = "Inicial";
        }
        else if (intermedio.isChecked() == true) {
            nivelE = "Intermedio";
        }
        else if (avanzado.isChecked() == true) {
            nivelE = "Avanzado";
        }

        if (estudiar.isChecked() == true) {
            actividadM = "Estudiar";
        }
        else if (aplicarexamen.isChecked() == true) {
            actividadM = "Aplicar Examen";
        }
        else if (consultapersonal.isChecked() == true) {
            actividadM = "Consulta Personal";
        }


        ContentValues registro = new ContentValues();
        registro.put("id_usuario", idusuario);
        registro.put("user", user);
        registro.put("password", password);
        registro.put("nombre", actividadM);
        registro.put("apellido_p", nivelE);
        registro.put("apellido_m", apellidom);
        registro.put("modulo",fecha);


        bd.insert("usuarios", null, registro);
        bd.close();
        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_actividades.setText("");
        et_nivel.setText("");
        et_modulo.setText("");
        et_fecha.setText("");
        inicial.setChecked(false);
        intermedio.setChecked(false);
        avanzado.setChecked(false);
        estudiar.setChecked(false);
        aplicarexamen.setChecked(false);
        consultapersonal.setChecked(false);


        Toast.makeText(this, "Actividad de Educando Registrada", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        // muestra el registro guardado
        Cursor fila = bd.rawQuery("select user, password, nombre, apellido_p, apellido_m, modulo from usuarios where id_usuario=" + idusuario, null);
        if (fila.moveToFirst()) {
            et_nombres.setText(fila.getString(0));
            et_apellidos.setText(fila.getString(1));
            et_actividades.setText(fila.getString(2));
            et_nivel.setText(fila.getString(3));
            et_modulo.setText(fila.getString(4));
            et_fecha.setText(fila.getString(5));


            if (et_actividades.getText().toString().equals("Estudiar")) {
                estudiar.setChecked(true);

            } else if (et_actividades.getText().toString().equals("Aplicar Examen")) {
                aplicarexamen.setChecked(true);

            } else if (et_actividades.getText().toString().equals("Consulat Personal")) {
                consultapersonal.setChecked(true);
            }

            if (et_nivel.getText().toString().equals("Inicial")) {
                inicial.setChecked(true);

            } else if (et_nivel.getText().toString().equals("Intermedio")) {
                intermedio.setChecked(true);
            } else if (et_nivel.getText().toString().equals("Avanzado")) {
                avanzado.setChecked(true);
            } else {
                Toast.makeText(this, "No Existe El Registro", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this,"No hay registro del Educando",Toast.LENGTH_SHORT).show();
        } bd.close();
    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        // nombre de la tabla(usuarios) y la condicion(id_usuario)
        int cant = bd.delete("usuarios", "id_usuario=" + idusuario, null);
        bd.close();

        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_actividades.setText("");
        et_nivel.setText("");
        et_modulo.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el Registro", Toast.LENGTH_SHORT).show();

        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String user = et_nombres.getText().toString();
        String password = et_apellidos.getText().toString();
        String actividadM = et_actividades.getText().toString();
        String nivelE = et_nivel.getText().toString();
        String apellidom = et_modulo.getText().toString();
        String fecha = et_fecha.getText().toString();

        if (inicial.isChecked() == true) {
            nivelE = "Inicial";
        }
        else if (intermedio.isChecked() == true) {
            nivelE = "Intermedio";
        }
        else if (avanzado.isChecked() == true) {
            nivelE = "Avanzado";
        }

        if (estudiar.isChecked() == true) {
            actividadM = "Estudiar";
        }
        else if (aplicarexamen.isChecked() == true) {
            actividadM = "Aplicar Examen";
        }
        else if (consultapersonal.isChecked() == true) {
            actividadM = "Consulta Personal";
        }


        ContentValues registro = new ContentValues();
// nombre de los campos y variables
        registro.put("id_usuario", idusuario);
        registro.put("user", user);
        registro.put("password", password);
        registro.put("nombre", actividadM);
        registro.put("apellido_p", nivelE);
        registro.put("apellido_m", apellidom);
        registro.put("modulo", fecha);

// nombre de la tabla(usuarios) y la condicion(id_usuario)
        int cant = bd.update("usuarios", registro, "id_usuario=" + idusuario, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modifico la Actividad del Educando",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el Registro",Toast.LENGTH_SHORT).show();

        }
    }

    public void limpia (View v) {
        et_idusuario.setText("");
        et_nombres.setText("");
        et_apellidos.setText("");
        et_actividades.setText("");
        et_nivel.setText("");
        et_modulo.setText("");
        et_fecha.setText("");
        inicial.setChecked(false);
        intermedio.setChecked(false);
        avanzado.setChecked(false);
        estudiar.setChecked(false);
        aplicarexamen.setChecked(false);
        consultapersonal.setChecked(false);

    }
    public void  ver(View v) {
        Intent intent = new Intent(this, registros.class);
        startActivity(intent);
    }
    public void inicial (View v){
        inicial.setChecked(true);
        intermedio.setChecked(false);
        avanzado.setChecked(false);

    }
    public void intermedio (View v){

        inicial.setChecked(false);
        intermedio.setChecked(true);
        avanzado.setChecked(false);

    }
    public void avanzado (View v){

        inicial.setChecked(false);
        intermedio.setChecked(false);
        avanzado.setChecked(true);
    }

    public void estudiar (View v){
        estudiar.setChecked(true);
        aplicarexamen.setChecked(false);
        consultapersonal.setChecked(false);

    }
    public void aplicarexamen (View v){

        estudiar.setChecked(false);
        aplicarexamen.setChecked(true);
        consultapersonal.setChecked(false);

    }
    public void consultapersonal (View v){

        estudiar.setChecked(false);
        aplicarexamen.setChecked(false);
        consultapersonal.setChecked(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bienvenido, menu);
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

    }
}

