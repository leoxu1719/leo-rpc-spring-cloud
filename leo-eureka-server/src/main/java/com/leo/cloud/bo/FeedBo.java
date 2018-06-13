package com.leo.cloud.bo;

import lombok.Data;

import java.util.Date;

@Data
public class FeedBo {

    private Long id;

    private String name;

    private Date createDate;
}
