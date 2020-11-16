package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_spu")
@Entity
@Data
@ToString
public class Spu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    private Boolean saleable;// alta o baja
    private Boolean valid;// eliminado o no ->eliminación lógica, en lugar de física
    private Date createTime;
    private Date lastUpdateTime;
}