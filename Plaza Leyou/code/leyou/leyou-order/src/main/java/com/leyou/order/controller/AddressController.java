package com.leyou.order.controller;


import com.leyou.order.pojo.Address;
import com.leyou.order.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order/address")
@Api("Address API")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * Crear una dirección del envío nueva
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Crear una dirección del envío nueva",notes = "Crear una dirección del envío nueva")
    @ApiImplicitParam(name = "address",required = true,value = "json of address")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Dirección creada"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<Void> addAddressByUserId(@RequestBody Address address){
        this.addressService.addAddressByUserId(address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Buscar lista de direcciones del envío con userId
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Buscar lista de direcciones del envío con userI, return lista de direcciones del envío",notes = "Buscar lista de direcciones del envío con userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de direcciones del envío"),
            @ApiResponse(code = 404,message = "No resultados encontrados"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<List<Address>> queryAddressByUserId(){
        List<Address> addresses = this.addressService.queryAddressByUserId();
        if (CollectionUtils.isEmpty(addresses)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addresses);
    }

    /**
     * Modificar dirección del envío
     * @param address
     * @return
     */
    @PutMapping
    @ApiOperation(value = "Modificar dirección del envío", notes = "Modificar dirección del envío")
    @ApiImplicitParam(name = "address", required=true, value = "address")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Modificación con éxito"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<Void> updateAddressByUserId(@RequestBody Address address){
        if (address == null){
            return ResponseEntity.badRequest().build();
        }
        this.addressService.updateAddressByUserId(address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Eliminar dirección del envío
     * @param addressId
     * @return
     */
    @DeleteMapping("{addressId}")
    @ApiOperation(value = "Eliminar dirección del envío",notes = "Eliminar dirección del envío")
    @ApiImplicitParam(name = "addressId", required=true, value = "addressId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Eliminación con éxito"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Long addressId){
        this.addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Buscar dirección del envío con addressId
     * @param addressId
     * @return
     */
    @GetMapping("{addressId}")
    @ApiOperation(value = "Buscar dirección del envío con addressId",notes = "Buscar dirección del envío con addressId")
    @ApiImplicitParam(name = "addressId", required=true, value = "addressId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Búsqueda con éxito"),
            @ApiResponse(code = 404, message = "Búsqueda fallada"),
            @ApiResponse(code = 500,message = "Error del servidor")
    })
    public ResponseEntity<Address> queryAddressById(@PathVariable("addressId") Long addressId){
        Address address = this.addressService.queryAddressById(addressId);
        if (address == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(address);
    }
}
