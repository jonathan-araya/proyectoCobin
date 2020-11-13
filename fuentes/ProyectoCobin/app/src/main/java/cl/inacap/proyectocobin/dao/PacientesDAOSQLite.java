package cl.inacap.proyectocobin.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.inacap.proyectocobin.dto.Paciente;
import cl.inacap.proyectocobin.helpers.PacientesSQLiteHelper;

public class PacientesDAOSQLite implements PacientesDAO{

    private PacientesSQLiteHelper pacienteHelper;

    public PacientesDAOSQLite(Context context){
        this.pacienteHelper = new PacientesSQLiteHelper(context, "dbByron"
                , null, 1);
    }
    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.pacienteHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try {
            if (reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fecha,area,sintomas,temperatura,tos,presion" +
                        " FROM pacientes", null);
                if (c.moveToFirst()){
                    do {
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFecha(agarrarFecha(c.getString(4)));
                        p.setArea(c.getString(5));
                        p.setSintomas(agarrarboolean(c.getInt(6)));
                        p.setTemperatura(c.getDouble(7));
                        p.setTos(agarrarboolean(c.getInt(8)));
                        p.setPresion(c.getInt(9));
                        pacientes.add(p);

                    }while (c.moveToNext());
                }
                reader.close();
            }
        }catch (Exception ex){
            Log.e("PRODUCTOSDAO", ex.toString());
            pacientes = null;
        }
        return pacientes;
    }

    private boolean agarrarboolean(int anInt) {
        boolean myBoolean = true;
        int myInt = myBoolean ? 1 : 0;
        return myBoolean;
    }

    private Date agarrarFecha(String string) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateTime = dateFormat.format(date);
            return date;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public Paciente save(Paciente p) {
        SQLiteDatabase writer = this.pacienteHelper.getWritableDatabase();
        String sql = String.format("INSERT INTO pacientes (rut,nombre,apellido,fecha,area,sintomas,temperatura,tos,presion)" +
                " VALUES ('%s','%s','%s','%s','%s','%d','%d','%d','%d')",p.getRut(), p.getNombre(), p.getApellido(), p.getFecha(), p.getArea(), p.isSintomas()
                , p.getTemperatura(), p.isTos(), p.getPresion());
        writer.execSQL(sql);
        writer.close();
        return p;
    }
}
