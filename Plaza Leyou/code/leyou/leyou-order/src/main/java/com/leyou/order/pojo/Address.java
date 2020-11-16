package com.leyou.order.pojo;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Table(name = "tb_address")
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String name;

    private String phone;

    @Column(name = "zip_code")
    private String zipCode;

    private String state;

    private String city;

    private String district;

    private String address;

    @Column(name = "default_address")
    private boolean defaultAddress;

}