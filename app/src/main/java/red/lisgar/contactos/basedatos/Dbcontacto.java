package red.lisgar.contactos.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import red.lisgar.contactos.modelo.Contacto;

public class Dbcontacto extends DbHelper {

    Context context;

    //Constructor
    public Dbcontacto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //retorna el long para insertar variables enteras
    public long insertarContacto(Contacto contacto) {

        long id = 0;
        try {

            //Se llama la clase de la base de datos
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Instancia
            ContentValues values = new ContentValues();
            values.put("nombre", contacto.getNombre());
            values.put("telefono", contacto.getTelefono());
            values.put("correo_electronico", contacto.getCorreo_electronico());
            values.put("agrego_correo", contacto.getAgrego_correo());

            //Captura de respuesta del insertar en la tabla
            id = db.insert(TABLE_CONTATOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    // el public retorna un Arraylist para mostrar los contactos
    public ArrayList<Contacto> mostrarContactos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // se llama la clase con sus atributos
        ArrayList<Contacto> listaContacto = new ArrayList<>();
        Contacto contacto = null;
        Cursor cursorContactos = null;

        // instruccion para selecionar un dato de la tabla en este caso se seleccionan todos
        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTATOS, null);

        //valida si la tabla esta vacia
        if (cursorContactos.moveToFirst()) {
            do {
                //Instancia
                contacto = new Contacto();
                //Guarda la informacion de la posicion de las columnas en contactos
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                listaContacto.add(contacto);
                //repetir la accion
            } while (cursorContactos.moveToNext());
        }
        // se cierra el cursor de contactos
        cursorContactos.close();

        //retorna la lista de contacto
        return listaContacto;
    }


    public ArrayList<Contacto> mostrarcontactousua(Contacto contactos) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ArrayList<Contacto> listaContacto = new ArrayList<>();
        //permite que podamos movernos
        Cursor cursorContactos = null;


        String query = "SELECT * FROM " + TABLE_CONTATOS + " WHERE " + "correo_electronico" + " = '" + contactos.getAgrego_correo() + "' ";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            //Este metodo devuelve false si el cursor esta vacio
            while (cursor.moveToNext()) {
                contactos = new Contacto();
                contactos.setId(cursorContactos.getInt(0));
                contactos.setNombre(cursorContactos.getString(1));
                contactos.setTelefono(cursorContactos.getString(2));
                contactos.setCorreo_electronico(cursorContactos.getString(3));
                listaContacto.add(contactos);
            }
        }
        return listaContacto;
    }

    //la clase retorna las variables que estan en contactos
    public Contacto verContacto(int id) {
        //se llama la clase con los atributos de la base de datos
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contacto contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTATOS + " WHERE id = " + id + " LIMIT 1", null);

        //Este método devuelve false si el cursor está vacío
        if (cursorContactos.moveToFirst()) {
            contacto = new Contacto();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
        }
        //se cierra el cursor que lleva a la tabla contactos
        cursorContactos.close();

        return contacto;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTATOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "'");
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTATOS + " WHERE id = '" + id + "'");
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
