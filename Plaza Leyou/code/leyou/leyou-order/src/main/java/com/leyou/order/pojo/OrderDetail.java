package com.leyou.order.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_order_detail")
@Entity
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;// Id del pedido

    @Column(name = "sku_id")
    private Long skuId;// Id del artículo

    private Integer num;// Cantidad comprada

    private String title;// Título del artículo

    private Long price;// Precio unitario del artículo

    @Column(name = "own_spec")
    private String ownSpec;// Especificaciones especiales del artículo

    private String image;// Image

}
