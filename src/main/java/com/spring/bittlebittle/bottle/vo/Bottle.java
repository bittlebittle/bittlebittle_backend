package com.spring.bittlebittle.bottle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bottle {

    private int     bottle_no_pk;
    private String  bottle_name;
    private String  bottle_content;
    private String  bottle_brand;
    private int     view_cnt;
    private String  img_url;
    private String  img_cus_url;
    private String  status;


}
