package red.lisgar.contactos.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import red.lisgar.contactos.modelo.Usuario;

public class DbUsuario extends DbHelper {
    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }
// Insertar usuario para registrarse
    public long insertarUsuario(Usuario usuario) {


        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("nom", usuario.getNombre());
            contentValues.put("apellido", usuario.getApellido());
            contentValues.put("email", usuario.getEmail());
            contentValues.put("pass", usuario.getPass());

            id = db.insert(TABLE_USUARIOS, null, contentValues);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;

    }
// Validacion de correo y contraseña del usuario
    public boolean validarAdmin(Usuario usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", usuario.getNombre());
        cv.put("apellido", usuario.getApellido());
        cv.put("email", usuario.getEmail());
        cv.put("pass", usuario.getPass());


        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE " + "email" + " = '" + usuario.getEmail() +
                "' and " + "pass" + " = '" + usuario.getPass() + "' ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }

    public boolean validarUsua(Usuario usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", usuario.getNombre());
        cv.put("apellido", usuario.getApellido());
        cv.put("email", usuario.getEmail());
        cv.put("pass", usuario.getPass());


        String query = "SELECT * FROM " + TABLE_USUA + " WHERE " + "email" + " = '" + usuario.getEmail() +
                "' and " + "pass" + " = '" + usuario.getPass() + "' ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }
}