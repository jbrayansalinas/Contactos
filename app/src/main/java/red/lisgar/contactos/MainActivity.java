package red.lisgar.contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

import red.lisgar.contactos.adaptadores.ListaContactosAdapter;
import red.lisgar.contactos.basedatos.Dbcontacto;
import red.lisgar.contactos.modelo.Contacto;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ArrayList<Contacto> contactosArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = findViewById(R.id.ListaContactos);

        //Intanciar
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        Contacto contactos = new Contacto();

        Dbcontacto dbContactos = new Dbcontacto(MainActivity.this);
        contactosArrayList = new ArrayList<>();

        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarcontactousua(contactos));
        listaContactos.setAdapter(adapter);


    }
    //Esta es la que crea los tres puntos
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // agregar nuevo registro de contactos
    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoContacto.class);
        startActivity(intent);
    }
}