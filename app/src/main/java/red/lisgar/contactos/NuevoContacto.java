package red.lisgar.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import red.lisgar.contactos.basedatos.Dbcontacto;
import red.lisgar.contactos.modelo.Contacto;

public class NuevoContacto extends AppCompatActivity {
    EditText txtNombre;
    EditText txtTelefono;
    EditText txtCorreoElectronico;
    String agrego_correo;
    Button btnGuardar;
    FloatingActionButton fabEditar;
    FloatingActionButton fabEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contacto);

        Dbcontacto dbcontacto = new Dbcontacto(NuevoContacto.this);

        Contacto contacto = new Contacto();

        SharedPreference sharedPreference = new SharedPreference(this);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.GONE);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.GONE);
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // de la clase contactos se guarda la caja de texto
                contacto.setNombre(txtNombre.getText().toString());
                contacto.setTelefono(txtTelefono.getText().toString());
                contacto.setCorreo_electronico(txtCorreoElectronico.getText().toString());
                contacto.setAgrego_correo(sharedPreference.getSharedPreference());

                long id = dbcontacto.insertarContacto( contacto);

                if (id > 0) {
                    Toast.makeText(NuevoContacto.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                    btnGuardar();
                } else {
                    Toast.makeText(NuevoContacto.this, "ERROR AL GUARDAR ", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    private void btnGuardar(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}
