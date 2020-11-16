package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_stock")
@Entity
@Data
public class Stock {
    @Id
    @Column(name = "sku_id")
    private Long skuId;
    @Column(name = "seckill_stock")
    private Integer seckillStock;
    @Column(name = "seckill_total")
    private Integer seckillTotal;
    private Integer stock;
}