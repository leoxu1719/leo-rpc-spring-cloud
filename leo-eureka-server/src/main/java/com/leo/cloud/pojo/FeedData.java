package com.leo.cloud.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class FeedData {

    private Long id;

    private String name;

    private Date createDate;

    private Double price;

    private Double salePrice;


}
