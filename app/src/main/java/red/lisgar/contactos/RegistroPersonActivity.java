package red.lisgar.contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import red.lisgar.contactos.basedatos.DbUsuario;
import red.lisgar.contactos.modelo.Usuario;

public class RegistroPersonActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtNombre;
    EditText txtApellido;
    EditText txtEmail;
    EditText txtPass;
    Button btnReusuario;
    Button btnCancelar;
    DbUsuario dbUsuario;
    RadioButton btnCliente;
    RadioButton btnAdministrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_person);

        dbUsuario = new DbUsuario(this);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnReusuario = findViewById(R.id.btnReusuario);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCliente = findViewById(R.id.btnCliente);
        btnAdministrador = findViewById(R.id.btnAdministrador);

        btnReusuario.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btnReusuario:

                Usuario usuario = new Usuario();

                usuario.setNombre(txtNombre.getText().toString());
                usuario.setApellido(txtApellido.getText().toString());
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setPass(txtPass.getText().toString());

                //Registro de usuario
                if (usuario.getNombre().isEmpty() || usuario.getApellido().isEmpty() || usuario.getEmail().isEmpty() || usuario.getPass().isEmpty()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                } else if (dbUsuario.insertarUsuario(usuario) > 0) {

                    Toast.makeText(this, "El registro fue exitoso", Toast.LENGTH_SHORT).show();
                    saliRegistro();
                } else {

                    Toast.makeText(this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancelar:
                Intent intent = new Intent(RegistroPersonActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    public void saliRegistro() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }


}