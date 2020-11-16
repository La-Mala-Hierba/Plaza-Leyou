package com.leyou.order.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_order_status")
@Entity
@Data
public class OrderStatus {

    @Id
    @Column(name = "order_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    /**
     * Fase inicial：1、Pago pendiente、Envío pendiente
     * Fase de pago：2、Pago hecho、Envío pendiente；Modificar paymentTime
     * Fase de envío：3、Envío hecho；Modificar consignTime, shippingName y shippingCode
     * Fase de Recepción：4、Recepción confirmada，Comentario pendiente；Modificar endTime
     * Fase de cierre：5、Cierre； Modificar closeTime
     * Fase de comentario：6、Comentario hecho
     */
    private Integer status; // Estado del pedido

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "payment_time")
    private Date paymentTime;

    @Column(name = "consign_time")
    private Date consignTime;// ShippingTime

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "close_time")
    private Date closeTime;

    @Column(name = "comment_time")
    private Date commentTime;
}
