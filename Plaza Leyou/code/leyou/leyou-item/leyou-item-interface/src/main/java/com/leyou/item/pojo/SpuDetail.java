package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_spu_detail")
@Entity
@Data
@ToString
public class SpuDetail {
    @Id
    @Column(name = "spu_id")
    private Long spuId;
    private String description;// descripción del artículo
    @Column(name = "special_spec")
    private String specialSpec;// los valores de especificación especial del artículos para la selección de ususarios
    @Column(name = "generic_spec")
    private String genericSpec;// especificación general de spu
    @Column(name = "packing_list")
    private String packingList;
    @Column(name = "after_service")
    private String afterService;
}