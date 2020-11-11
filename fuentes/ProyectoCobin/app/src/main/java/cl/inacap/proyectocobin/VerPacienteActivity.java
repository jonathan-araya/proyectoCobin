package cl.inacap.proyectocobin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import cl.inacap.proyectocobin.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private TextView rutTxt;
    private TextView nombTxt;
    private TextView apellTxt;
    private TextView fechaTxt;
    private TextView areaTxt;
    private TextView sintomasTxt;
    private TextView tosTxt;
    private TextView tempTxt;
    private TextView presionTxt;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.rutTxt = findViewById(R.id.rut_ver);
        this.nombTxt = findViewById(R.id.nombre_ver);
        this.apellTxt = findViewById(R.id.apellido_ver);
        this.fechaTxt = findViewById(R.id.fecha_ver);
        this.areaTxt = findViewById(R.id.area_ver);
        this.sintomasTxt = findViewById(R.id.sintomas_ver);
        this.tosTxt = findViewById(R.id.tos_ver);
        this.tempTxt = findViewById(R.id.temperatura_ver);
        this.presionTxt = findViewById(R.id.presion_ver);
        this.imageView = findViewById(R.id.ver_imagen);
        if (getIntent() != null){
            Paciente paciente = (Paciente) getIntent()
                    .getSerializableExtra("paciente");
            this.rutTxt.setText(paciente.getRut());
            this.nombTxt.setText(paciente.getNombre());
            this.apellTxt.setText(paciente.getApellido());
            this.fechaTxt.setText(paciente.getFecha().toString());
            this.areaTxt.setText(paciente.getArea());
            if (paciente.isSintomas()== true){
                this.sintomasTxt.setText("Si");
            }else {
                this.sintomasTxt.setText("No");
            }
            if (paciente.isTos()== true){
                this.tosTxt.setText("Si");
            }else {
                this.tosTxt.setText("No");
            }
            double total = paciente.getTemperatura();
            this.tempTxt.setText(String.valueOf(total));
            int total1 = paciente.getPresion();
            this.presionTxt.setText(String.valueOf(total1));
            if (paciente.isSintomas()==true){
                this.imageView.setImageResource(R.drawable.ic_you_died);
            }else{
                this.imageView.setImageResource(R.drawable.ic_baseline_sano);
            }

        }
    }
}