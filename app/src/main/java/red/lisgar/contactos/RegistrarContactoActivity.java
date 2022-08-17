package red.lisgar.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import red.lisgar.contactos.basedatos.Dbcontacto;
import red.lisgar.contactos.modelo.Contacto;

public class RegistrarContactoActivity extends AppCompatActivity {

    EditText txtNombre;
    EditText txtTelefono;
    EditText txtCorreo;
    Button btnGuardar;
    boolean correcto = false;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;
    Contacto contactos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contacto);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono =findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.GONE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.GONE);
        btnGuardar = findViewById(R.id.btnGuardar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final Dbcontacto dbContacto = new Dbcontacto(this);
        contactos = dbContacto.verContacto(id);

        if (contactos != null) {
            txtNombre.setText(contactos.getNombre());
            txtTelefono.setText(contactos.getTelefono());
            txtCorreo.setText(contactos.getCorreo_electronico());

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {
                    correcto = dbContacto.editarContacto(id, txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(RegistrarContactoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(RegistrarContactoActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(RegistrarContactoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
    public void verRegistro(){
        Intent intent = new Intent(this,RegistrarContactoActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);

    }
}