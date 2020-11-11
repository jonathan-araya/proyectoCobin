package cl.inacap.proyectocobin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import cl.inacap.proyectocobin.R;
import cl.inacap.proyectocobin.dto.Paciente;

public class PacientesArrayAdapter extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> pacientes;
    public PacientesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.pacientes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();

        View fila = inflater.inflate(R.layout.pacientes_list, null, true);


        TextView rut_Lv = fila.findViewById(R.id.rut_prod_Lv);
        TextView nomb_lv = fila.findViewById(R.id.nomb_prod_Lv);
        TextView apellido_Lv = fila.findViewById(R.id.apellido_prod_Lv);
        TextView fecha_lv = fila.findViewById(R.id.fecha_prod_Lv);
        ImageView imagen_lv = fila.findViewById(R.id.imagen_prod_Lv);

        Paciente actual = pacientes.get(position);

        rut_Lv.setText(actual.getRut());
        nomb_lv.setText(actual.getNombre());
        apellido_Lv.setText(actual.getApellido());
        fecha_lv.setText(actual.getFecha().toString());
        if (actual.isSintomas()==true){
            imagen_lv.setImageResource(R.drawable.ic_you_died);
        }else {
            imagen_lv.setImageResource(R.drawable.ic_baseline_sano);
        }

        return fila;
    }

}
