package red.lisgar.contactos.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //Inicializacion para la base de datos
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTATOS = "t_contactos";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_USUA = "t_usua";

    //el public retorna parta encontrar los datos
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //  el oncreate  crear la tabla
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    // metodo para llamar a los contactos
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTATOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "agrego_correo TEXT)");

    //tabla y columna de usuario
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "pass TEXT)");
    // metodo para validar el usuario
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "pass TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    //llamar tablas
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTATOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUA);
        onCreate(sqLiteDatabase);

    }
}
