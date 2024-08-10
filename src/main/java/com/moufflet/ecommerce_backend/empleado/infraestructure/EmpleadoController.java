package com.moufflet.ecommerce_backend.empleado.infraestructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moufflet.ecommerce_backend.empleado.application.EmpleadoService;
import com.moufflet.ecommerce_backend.empleado.model.Empleado;
import com.moufflet.ecommerce_backend.empleado.model.EmpleadoDTO;
import com.moufflet.ecommerce_backend.oficina.application.OficinaService;

@RestController
@RequestMapping("/admin/empleados")
public class EmpleadoController {

  @Autowired
  private EmpleadoService empleadoService;

  @Autowired
  private OficinaService oficinaService;

  @PostMapping("/crear")
  public ResponseEntity<Empleado> crearEmpleado(@RequestBody EmpleadoDTO empleado) {
    System.out.println(empleado);
    return ResponseEntity.ok(empleadoService.save(empleado));
  }

  @GetMapping("/buscar")
  public ResponseEntity<Empleado> buscarEmpleado(@RequestParam String id) {
    return ResponseEntity.ok(empleadoService.getById(id));
  }

  @DeleteMapping("/borrar")
  public ResponseEntity<Void> borrarEmpleado(@RequestParam String id) {
    empleadoService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/buscarPorOficina")
  public ResponseEntity<List<EmpleadoDTO>> buscarEmpleadoPorOficina(@RequestParam String oficina) {
    return ResponseEntity.ok(oficinaService.getByOficina(oficina));
  }

  @GetMapping("/buscarPorCiudad")
  public ResponseEntity<List<Empleado>> buscarEmpleadoPorCiudad(@RequestParam String ciudad) {
    return ResponseEntity.ok(oficinaService.getByDireccionCiudadNombre(ciudad).getEmpleados());
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<EmpleadoDTO>> getAllEmpleados() {
    return ResponseEntity.ok(empleadoService.findAll());
  }

  @PutMapping("/update")
  public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String empleado,
      @RequestBody EmpleadoDTO empleadoDTO) {
    return ResponseEntity.ok(empleadoService.update(empleado, empleadoDTO));
  }

}
