package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "tb_sku")
@Entity
@Data
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "spu_id")
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    @Column(name = "own_spec")
    private String ownSpec;
    private String indexes;
    private Boolean enable;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    @Transient
    private Integer stock;
}