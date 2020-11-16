package com.leyou.item.bo;

import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.List;

@Data
@ToString
public class SpuBo extends Spu {

    private String cname;

    private String bname;

    private SpuDetail spuDetail;

    private List<Sku> skus;
}
