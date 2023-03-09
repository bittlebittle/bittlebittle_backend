package com.spring.bittlebittle.bottle.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bottle {

	private int bottleNo;
	private String bottleName;
	private String bottleComment;
	private int viewCount;
	private Date createDate;
	private String imgUrl;
	private String imgEncurl;
	
	public Bottle() {
		// TODO Auto-generated constructor stub
	}

	public Bottle(int bottleNo, String bottleName, String bottleComment, int viewCount, Date createDate, String imgUrl,
			String imgEncurl) {
		super();
		this.bottleNo = bottleNo;
		this.bottleName = bottleName;
		this.bottleComment = bottleComment;
		this.viewCount = viewCount;
		this.createDate = createDate;
		this.imgUrl = imgUrl;
		this.imgEncurl = imgEncurl;
	}

	public int getBottleNo() {
		return bottleNo;
	}

	public void setBottleNo(int bottleNo) {
		this.bottleNo = bottleNo;
	}

	public String getBottleName() {
		return bottleName;
	}

	public void setBottleName(String bottleName) {
		this.bottleName = bottleName;
	}

	public String getBottleComment() {
		return bottleComment;
	}

	public void setBottleComment(String bottleComment) {
		this.bottleComment = bottleComment;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgEncurl() {
		return imgEncurl;
	}

	public void setImgEncurl(String imgEncurl) {
		this.imgEncurl = imgEncurl;
	}
	
	
	
	
}
