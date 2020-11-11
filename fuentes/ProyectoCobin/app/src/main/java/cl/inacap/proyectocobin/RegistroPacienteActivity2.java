package cl.inacap.proyectocobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class RegistroPacienteActivity2 extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente2);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);

    }
}