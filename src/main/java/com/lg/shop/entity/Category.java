package com.lg.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L
 * @version 1.0
 * @ClassName: Category
 * @date: 2019/12/23 8:42
 * @since JDK 1.8
 */
@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    private String cid;
    private String cname;

//    @OneToMany(targetEntity = Product.class,fetch = FetchType.EAGER)
//    @JoinColumn(name = "cid", referencedColumnName = "cid")
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    @JsonIgnoreProperties(value = {"category"})

}
