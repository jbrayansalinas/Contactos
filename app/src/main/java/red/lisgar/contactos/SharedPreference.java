package red.lisgar.contactos;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    //se llama las clases con sus atributos
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public SharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    //
    public void setSharedPreference(String datoEmail){
        editor.putString("dato",datoEmail);
        editor.apply();
    }
    //Retorna
    public String getSharedPreference(){
        return sharedPreferences.getString("dato", "dato no encontrado");
    }
}
