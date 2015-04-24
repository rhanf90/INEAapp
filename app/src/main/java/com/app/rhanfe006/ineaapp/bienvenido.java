package com.app.rhanfe006.ineaapp;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class bienvenido extends Activity implements View.OnClickListener {

    EditText et_idusuario, et_user, et_password, et_nombre, et_apellidop, et_apellidom, et_email;

    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        nombre = (TextView) findViewById(R.id.tvNombre);
        Bundle bolsa = getIntent().getExtras();
        nombre.setText(bolsa.getString("NOMBRE"));

        et_idusuario = (EditText) findViewById(R.id.et_idusuario);
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellidop = (EditText) findViewById(R.id.et_apellidop);
        et_apellidom = (EditText) findViewById(R.id.et_apellidom);

    }
    public void alta (View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String user = et_user.getText().toString();
        String password = et_password.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidop = et_apellidop.getText().toString();
        String apellidom = et_apellidom.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("id_usuario", idusuario);
        registro.put("user", user);
        registro.put("password", password);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);


        bd.insert("usuarios", null, registro);
        bd.close();
        et_idusuario.setText("");
        et_user.setText("");
        et_password.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");


        Toast.makeText(this, "Actividad de Educando Registrada", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();
        String idusuario = et_idusuario.getText().toString();
        // muestra el registro guardado
        Cursor fila = bd.rawQuery("select user, password, nombre, apellido_p, apellido_m from usuarios where id_usuario=" + idusuario, null);
        if (fila.moveToFirst()) {
            et_user.setText(fila.getString(0));
            et_password.setText(fila.getString(1));
            et_nombre.setText(fila.getString(2));
            et_apellidop.setText(fila.getString(3));
            et_apellidom.setText(fila.getString(4));

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
        et_user.setText("");
        et_password.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el Registro", Toast.LENGTH_SHORT).show();

        }
    }

    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1); SQLiteDatabase bd = admin.getWritableDatabase();

        String idusuario = et_idusuario.getText().toString();
        String user = et_user.getText().toString();
        String password = et_password.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidop = et_apellidop.getText().toString();
        String apellidom = et_apellidom.getText().toString();


        ContentValues registro = new ContentValues();
// nombre de los campos y variables
        registro.put("id_usuario", idusuario);
        registro.put("user", user);
        registro.put("password", password);
        registro.put("nombre", nombre);
        registro.put("apellido_p", apellidop);
        registro.put("apellido_m", apellidom);

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
        et_user.setText("");
        et_password.setText("");
        et_nombre.setText("");
        et_apellidop.setText("");
        et_apellidom.setText("");


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

