package com.moufflet.ecommerce_backend.producto.infraestructure.adapter.in;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moufflet.ecommerce_backend.producto.application.ProductoService;
import com.moufflet.ecommerce_backend.producto.model.Producto;
import com.moufflet.ecommerce_backend.producto.model.ProductoDTO;

@RequestMapping("/admin/producto")
@RestController
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @PostMapping("/crear")
  public ResponseEntity<Producto> createProducto(
      @RequestPart("producto") ProductoDTO productoDTO,
      @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {
    System.out.println("ProductoDTO: " + productoDTO);
    System.out.println("Imagen: " + (imagen != null ? imagen.getOriginalFilename() : "No imagen"));
    return productoService.createProducto(productoDTO, imagen);
  }

  @GetMapping("/getAll")
  public List<ProductoDTO> getAllProducto() {
    return productoService.getAll();
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Producto> updateProduct(@PathVariable Long id,
      @RequestPart("producto") ProductoDTO productoDTO,
      @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {
    byte[] imagenBytes = imagen != null ? imagen.getBytes() : null;
    Producto updatedProduct = productoService.updateProducto(id, productoDTO, imagenBytes);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productoService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/getByGama/{gama}")
  public List<ProductoDTO> getByGamaNombre(@PathVariable String gama) {
    return productoService.getByGama(gama);
  }

  @GetMapping("/getByStoack/{stock}")
  public List<ProductoDTO> getByStock(@PathVariable Integer stock) {
    return productoService.getByStock(stock);
  }

  @GetMapping("getByGamaId/{gamaId}")
  public List<ProductoDTO> getByGamaId(@PathVariable Long gamaId) {
    return productoService.getByGamaId(gamaId);
  }

}
