package cl.inacap.proyectocobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.proyectocobin.adapters.PacientesArrayAdapter;
import cl.inacap.proyectocobin.dao.PacientesDAO;
import cl.inacap.proyectocobin.dao.PacientesDAOLista;
import cl.inacap.proyectocobin.dao.PacientesDAOSQLite;
import cl.inacap.proyectocobin.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {

    private FloatingActionButton agregarBtn;
    private Toolbar toolbar;
    private ListView pacientesLv;
    private List<Paciente> pacientes;
    private PacientesArrayAdapter adaptador;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.agregarBtn = findViewById(R.id.agregarBtn);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this
                , RegistroPacienteActivity2.class));
            }
        });
    }
    protected void onResume(){
        super.onResume();
        pacientes = pacientesDAO.getAll();
        adaptador = new PacientesArrayAdapter(this,
                R.layout.pacientes_list, pacientes);
        pacientesLv = findViewById(R.id.pacientes_lv);
        pacientesLv.setAdapter(adaptador);
        pacientesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PrincipalActivity.this,
                        VerPacienteActivity.class);
                Paciente pacienteActual = pacientes.get(i);
                intent.putExtra("paciente", pacienteActual);
                startActivity(intent);
            }
        });
    }
}