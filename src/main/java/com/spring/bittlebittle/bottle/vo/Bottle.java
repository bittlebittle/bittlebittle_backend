package com.spring.bittlebittle.bottle.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bottle {
	
	private int bottleNo;
	private String bottleName;
	private String bottleContent;
	private String bottleBrand;
	private int viewCnt;
	private String createDate;
	private String imgUrl;
	private String imgCusUrl;
	private String status;
	
}
