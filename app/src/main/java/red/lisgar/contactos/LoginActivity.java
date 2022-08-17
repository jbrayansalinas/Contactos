package red.lisgar.contactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import red.lisgar.contactos.basedatos.DbUsuario;
import red.lisgar.contactos.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail;
    EditText txtPass;
    Button btnIngresar;
    Button btnRegistrar;
    SharedPreference sharedPreference;
    DbUsuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Se llama las clases para traer la informacion que necesitamos
        sharedPreference = new SharedPreference(this);
        dbUsuario = new DbUsuario(this);
        Usuario usuario = new Usuario();

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        String usua_t;
        String usuario_t;
        String pass;


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //El usuario guarda las cajas de texto
                usuario.setEmail(txtEmail.getText().toString());
                usuario.setPass(txtPass.getText().toString());

                if (txtEmail.getText().toString().isEmpty() && txtPass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (dbUsuario.validarAdmin(usuario)) {
                        Toast.makeText(getApplicationContext(), "La validacion del administrador fue exitosa", Toast.LENGTH_SHORT).show();
                        sharedPreference.setSharedPreference(txtEmail.getText().toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        if (dbUsuario.validarUsua(usuario)){
                            Toast.makeText(getApplicationContext(), "La validacion del usuario fue exitosa", Toast.LENGTH_SHORT).show();
                            sharedPreference.setSharedPreference(txtEmail.getText().toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(), " error de validacion", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }


            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ingreso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, RegistroPersonActivity.class);
                startActivity(intent);
            }
        });

    }
}