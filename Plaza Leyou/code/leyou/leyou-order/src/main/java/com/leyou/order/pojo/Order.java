package com.leyou.order.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name = "tb_order")
@Entity
@Data
public class Order {

    @Id
    @Column(name = "order_id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;// id

    @NotNull
    @Column(name = "total_pay")
    private Long totalPay;// Importe total

    @NotNull
    @Column(name = "actual_pay")
    private Long actualPay;// Importe pagado

    @NotNull
    @Column(name = "payment_type")
    private Integer paymentType; // Tipo de pago: 1、pago online，2、portes debidos

    @Column(name = "promotion_ids")
    private String promotionIds; // Id de promociones
    @Column(name = "post_fee")
    private String postFee;// Gasto del envío
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "shipping_name")
    private String shippingName;// Nombre de Logística
    @Column(name = "shipping_code")
    private String shippingCode;// Código de Logística
    @Column(name = "user_id")
    private Long userId;// Id del usuario
    @Column(name = "buyer_message")
    private String buyerMessage;// Mensajes de comprador
    @Column(name = "buyer_nick")
    private String buyerNick;// Nickname de comprador
    @Column(name = "buyer_rate")
    private Boolean buyerRate;// Ha valorado el comprador sobre el artículo
    private String receiver; // Nombre de destinatario
    @Column(name = "receiver_mobile")
    private String receiverMobile; // Móvil de destinatario
    @Column(name = "receiver_state")
    private String receiverState; // Provencia
    @Column(name = "receiver_city")
    private String receiverCity; // Ciudad
    @Column(name = "receiver_district")
    private String receiverDistrict; // Districto
    @Column(name = "receiver_address")
    private String receiverAddress; // Dirección del envío
    @Column(name = "receiver_zip")
    private String receiverZip; // Código postal
    @Column(name = "invoice_type")
    private Integer invoiceType;// Tipo de facturación，0 no hay factura，1 factura normal，2 factura electrónica，3 factura VAT
    @Column(name = "source_type")
    private Integer sourceType;// Origen del pedido  1:app，2：pc，3：Móvil，4：Wechat，5：QQ

    @Transient
    private List<OrderDetail> orderDetails;

    @Transient
    private Integer status;

}
