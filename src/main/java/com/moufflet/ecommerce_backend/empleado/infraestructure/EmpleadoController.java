package com.moufflet.ecommerce_backend.empleado.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.empleado.application.EmpleadoService;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.oficina.application.OficinaService;

@RestController
@RequestMapping("/admin/empleados")
public class EmpleadoController {

  @Autowired
  private EmpleadoService empleadoService;

  @Autowired
  private OficinaService oficinaService;

  @GetMapping("/crear")
  public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
    return ResponseEntity.ok(empleadoService.save(empleado));
  }

  @GetMapping("/buscar")
  public ResponseEntity<Empleado> buscarEmpleado(@RequestParam String id) {
    return ResponseEntity.ok(empleadoService.getById(id));
  }

  @GetMapping("/borrar")
  public ResponseEntity<Void> borrarEmpleado(@RequestParam String id) {
    empleadoService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/buscarPorOficina")
  public ResponseEntity<List<Empleado>> buscarEmpleadoPorOficina(@RequestParam String oficina) {
    return ResponseEntity.ok(oficinaService.getByNombreOficina(oficina).getEmpleados());
  }

  @GetMapping("/buscarPorCiudad")
  public ResponseEntity<List<Empleado>> buscarEmpleadoPorCiudad(@RequestParam String ciudad) {
    return ResponseEntity.ok(oficinaService.getByDireccionCiudadNombre(ciudad).getEmpleados());
  }

}
