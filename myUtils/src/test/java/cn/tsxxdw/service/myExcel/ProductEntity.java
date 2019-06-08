/**
 * WingSing CONFIDENTIAL
 * _____________________
 * <p>
 * [2014] - [2015] WingSing Supply Chain Management Co. (Shenzhen) Ltd.
 * All Rights Reserved.
 * <p>
 * NOTICE: All information contained herein is, and remains the property of
 * WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary
 * to WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 * may be covered by China and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from WingSing Supply Chain Management Co. (Shenzhen)
 * Ltd.
 */

package cn.tsxxdw.service.myExcel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 货品
 * 
 * @author
 * @date 2019/01/03
 *
 */
@Setter
@Getter
public class ProductEntity  {
	private Integer id;// int
	private String sku;// varchar sku
	private String ean; // EAN码（69码）
	private String cnName;// varchar 名称(中文)
	private String enName;// varchar 名称(英文)
	private String owner; // 货主编码
	private String hsCode; // 海关编码
	private String unit; // 单位
	private Double length; // 长(mm)
	private Double width; // 宽(mm)
	private Double height; // 高(mm)
	private Double weight;// 重量kg（0.001）
	private String volume; // 体积
	private String description;// text 说明
	private String jsonInfo; // 扩展数据
	private String createUser; // 创建人
	private Date createDate;// 创建时间
	private String lastUpdateUser; // 最后操作人
	private Date lastUpdateDate; // 最后修改时间
	private String brand; // 品牌
	private String spec;// varchar 规格
	private String category; // 类别
	private Integer status;// int 状态 0失效 1生效
	private Double price;// 价格(1.11)
	private String images;// 图片列表
	private String materielCode; // 物料编码=自定义编码
	private String barCode; // barcode
	private String currency; // 币种
	private Boolean useWssku; // 是否使用我们公司自己的唯一编码
	private Boolean hasUniqueCode; // 产品是否有唯一码（例如手机有IMEI码）
	private Boolean hasLabel;// 是否需要贴标
	private String labelCode; // 标签编码
	private String labelName; // 标签名称（将labelCode转换成名称）
	// Andre add：2017-3-11
	private String model;// 型号
	private String pickStrategy;
	private String categoryName;// 品类
	private String declaredPriceinfo;// 申报价格信息
	private String declareCnname;// 中文申报名称
	private String declareEnname;// 英文申报名称
	private Double declarePrice;// 申报价格
	private String pictureAddress;// 图片地址
	private String wssku;
	private String wmsInfo;// 仓库重量,存储不同仓库的重量（数据库存json格式）
	private String productUrl; // 产品地址URL
	private String log;// 日志信息
}