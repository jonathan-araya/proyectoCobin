package cl.inacap.proyectocobin.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.inacap.proyectocobin.dto.Paciente;

public class PacientesDAOLista implements PacientesDAO {

    private List<Paciente> pacientes = new ArrayList<>();

    private static PacientesDAOLista instancia;

    private PacientesDAOLista(){
        Paciente p = new Paciente();
        p.setRut("19013317-6");
        p.setNombre("jontan");
        p.setApellido("Araia");
        p.setFecha(agarrarDate());
        p.setArea("otro");
        p.setSintomas(true);
        p.setTemperatura(38.5);
        p.setTos(true);
        p.setPresion(130);

        pacientes.add(p);
    }

    private Date agarrarDate() {
        Date myDate = new Date(2014, 02, 11);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return myDate;
    }

    private Date laWea(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return date;
    }

    public static PacientesDAOLista getInstance(){
        if(instancia == null){
            instancia = new PacientesDAOLista();
        }
        return instancia;
    }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        pacientes.add(p);
        return p;
    }
}
