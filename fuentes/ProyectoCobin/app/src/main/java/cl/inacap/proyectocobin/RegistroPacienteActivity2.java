package cl.inacap.proyectocobin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.inacap.proyectocobin.dto.Paciente;

public class RegistroPacienteActivity2 extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private EditText rutTxt;
    private EditText fechaTxt;
    private Spinner areaSp;
    private Switch sintomasSw;
    private EditText temperaturaTxt;
    private Switch tosSw;
    private EditText presion;
    private Button botonBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente2);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.nombreTxt = findViewById(R.id.nombreEdit);
        this.rutTxt = findViewById(R.id.rutEdit);
        this.apellidoTxt = findViewById(R.id.apellidoEdit);
        this.fechaTxt = findViewById(R.id.fechaEdit);
        this.areaSp = findViewById(R.id.areaSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
        ,R.array.area_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSp.setAdapter(adapter);
        this.sintomasSw = findViewById(R.id.sintomasSwitch);
        this.temperaturaTxt = findViewById(R.id.temperaturaEdit);
        this.tosSw = findViewById(R.id.tosSwitch);
        this.presion = findViewById(R.id.presionEdit);
        this.botonBtn = findViewById(R.id.agregarBtn);
        this.sintomasSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.sintomasSwitch){
                    if (sintomasSw.isChecked()){

                    }else {

                    }
                }
            }
        });
        this.botonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String nombreStr = nombreTxt.getText().toString();
                String rutStr = rutTxt.getText().toString();
                String apellidoStr = apellidoTxt.getText().toString();
                String fechaStr = fechaTxt.getText().toString();
                String areaStr = areaSp.getSelectedItem().toString();
                String temperaturaStr = temperaturaTxt.getText().toString();
                String presionStr = presion.getText().toString();
                try{
                    if (rutStr.equalsIgnoreCase("")){
                        throw  new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no ha puesto el rut del paciente");
                }
                try{
                    if (nombreStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no ha puesto nombre del paciente");
                }
                try{
                    if (apellidoStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no ha puesto el apellido");
                }
                try{
                    if (fechaStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no ha puesto la fecha");
                }
                try{
                    if (temperaturaStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no se agrego la temperatura");
                }
                try{
                    if (presionStr.equalsIgnoreCase("")){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("no se agrego la presion");
                }

                if (errores.isEmpty()){

                    Paciente p = new Paciente();
                    p.setRut(rutStr);
                    p.setNombre(nombreStr);
                    p.setApellido(apellidoStr);
                    String dtStart = fechaStr;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    try {
                        Date dataso = format.parse(dtStart);
                        p.setFecha(dataso);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    p.setArea(areaStr);
                    p.setTemperatura(Integer.parseInt(temperaturaStr));

                }
            }
        });

        EditText fechaTxt = (EditText) findViewById(R.id.fechaEdit);
        fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fechaEdit:
                        showDatePickerDialog();
                        break;
                }
            }
        });
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                final String selectedDate= year + " / "+(month+1) + " / " + day;
                fechaTxt.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
            DatePickerFragment fragment;
            fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener){
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle saverInstanceState) {
            final Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            return new DatePickerDialog(getActivity(), listener, day, month, year);
        }

    }

}