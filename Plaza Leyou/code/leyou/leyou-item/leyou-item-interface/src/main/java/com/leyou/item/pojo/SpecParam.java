package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "tb_spec_param")
@Entity
@Data
@ToString
public class SpecParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    @Column(name = "group_id")
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")//numeric is a keyword in mySql.`numeric` --> this is a field, instead of a keyword
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;

}