package com.lg.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author L
 * @version 1.0
 * @ClassName: Product
 * @date: 2019/12/23 8:42
 * @since JDK 1.8
 */

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    private String pid;
    private String pname;
    private Double marketPrice;
    private Double shopPrice;
    private String pimage;
    private Date pdate;
    private Integer isHot;
    private String pdesc;
    private Integer pflag;
    private String cid;

//    @ManyToOne(targetEntity = Category.class)
//    @JoinColumn(name = "cid", referencedColumnName = "cid")
//    private Category category;

}
