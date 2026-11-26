package com.accesodatos.hibernate.veterinaria;

import java.time.LocalDate;
import java.util.Optional;

import com.accesodatos.hibernate.veterinaria.dao.MascotaDao;
import com.accesodatos.hibernate.veterinaria.dao.MicrochipDao;
import com.accesodatos.hibernate.veterinaria.dao.PropietarioDao;
import com.accesodatos.hibernate.veterinaria.dao.TratamientoDao;
import com.accesodatos.hibernate.veterinaria.dominio.Mascota;
import com.accesodatos.hibernate.veterinaria.dominio.Microchip;
import com.accesodatos.hibernate.veterinaria.dominio.Propietario;
import com.accesodatos.hibernate.veterinaria.dominio.Tratamiento;

public class App {
	public static void main(String[] args) {

		PropietarioDao propietarioDao = new PropietarioDao();
		MascotaDao mascotaDao = new MascotaDao();
		MicrochipDao microchipDao = new MicrochipDao();
		TratamientoDao tratamientoDao = new TratamientoDao();

		Propietario p1 = new Propietario("12345678A", "Laura Gómez", "600112233");
		Propietario p2 = new Propietario("87654321B", "Carlos Ruiz", "699445566");

		Mascota m1 = new Mascota("Max", "Perro", 24.5, p1);
		Mascota m2 = new Mascota("Luna", "Gato", 4.2, p1);
		Mascota m3 = new Mascota("Toby", "Perro", 12.0, p2);

		p1.addMascota(m1);
		p1.addMascota(m2);
		p2.addMascota(m3);

		propietarioDao.save(p1);
		propietarioDao.save(p2);
		mascotaDao.save(m1);
		mascotaDao.save(m2);
		mascotaDao.save(m3);

		Microchip mc1 = new Microchip("CHIP-9901", LocalDate.now(), m1);
		Microchip mc2 = new Microchip("CHIP-9902", LocalDate.now(), m2);

		mc1.setMascota(m1);
		mc2.setMascota(m3);

		microchipDao.save(mc1);
		microchipDao.save(mc2);

		Tratamiento t1 = new Tratamiento("Vacuna Rabia", 35.0, " Inmunización anual obligatoria");
		Tratamiento t2 = new Tratamiento("Limpieza Dental", 85.0, "Profilaxis con ultrasonidos");
		Tratamiento t3 = new Tratamiento("Desparasitación interna", 20.0, "Comprimido antiparasitario");

		t1.addMascota(m1);
		t2.addMascota(m1);
		t1.addMascota(m3);
		t3.addMascota(m2);

		tratamientoDao.save(t1);
		tratamientoDao.save(t2);
		tratamientoDao.save(t3);

		Optional<Mascota> recuperarMascota = mascotaDao.get(m2.getId());
		if (recuperarMascota.isPresent()) {
			Mascota mascotaBD = recuperarMascota.get();

			mascotaBD.setPeso(6.5);

			mascotaDao.update(mascotaBD);
		}

		Optional<Tratamiento> recuperarTratamiento = tratamientoDao.get(t3.getId());
		if (recuperarTratamiento.isPresent()) {
			Tratamiento tratamientoBD = recuperarTratamiento.get();

			tratamientoDao.delete(tratamientoBD);
		}

	}
}
