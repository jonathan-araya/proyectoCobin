package cl.inacap.proyectocobin.dao;

import java.util.List;

import cl.inacap.proyectocobin.dto.Paciente;

public interface PacientesDAO {

    List<Paciente> getAll();

    Paciente save(Paciente p);
}
