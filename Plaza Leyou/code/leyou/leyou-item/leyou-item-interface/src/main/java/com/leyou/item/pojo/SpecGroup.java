package com.leyou.item.pojo;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "tb_spec_group")
@Entity
@Data
@ToString
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;

    private String name;

    @Transient //ignore this field(the database doesn't contain it)
    private List<SpecParam> params;

}