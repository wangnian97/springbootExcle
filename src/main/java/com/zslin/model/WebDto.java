package com.zslin.model;

import com.zslin.utils.ExcelResources;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 22:19.
 */
public class WebDto {

    private String jauto;

    private String oe;

    private  Integer Id;

    private Integer Isshowdata;

    private String Epc;

    private String Detail;

    private String Factory;

    private String Brand;

    private String Model;

    private String Series;

    private String Model_year;

    private String Sales_version;

    private String Cc;

    private String Engine_no;

    private String Fuel_type;

    private String Air_intake;

   // private String kw;

    private String Transmission_detail;

    private String Gear_num;

    private String Door_num;

    private String Seat_num;

    private String Body_type;

    private String Driven_model;

    private  String Date_begin;

    private String Date_end;

    private String Price;

    private String Autohome_id;

    public WebDto() {}

    @ExcelResources(title="Jauto型号",order=1)
    public String getJauto() {
        return jauto;
    }

    public void setJauto(String jauto) {
        this.jauto = jauto;
    }

    @ExcelResources(title="oe号",order=2)
    public String getOe() {
        return oe;
    }

    public void setOe(String oe) {
        this.oe = oe;
    }

    @ExcelResources(title="车型id",order=3)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @ExcelResources(title="车型下是否有配件数据，0：没有，1：有",order=4)
    public Integer getIsshowdata() {
        return Isshowdata;
    }

    public void setIsshowdata(Integer isshowdata) {
        Isshowdata = isshowdata;
    }

    @ExcelResources(title="品牌对应的epc",order=5)
    public String getEpc() {
        return Epc;
    }

    public void setEpc(String epc) {
        Epc = epc;
    }

    @ExcelResources(title="车型详细名称",order=6)
    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    @ExcelResources(title="厂家",order=7)
    public String getFactory() {
        return Factory;
    }

    public void setFactory(String factory) {
        Factory = factory;
    }

    @ExcelResources(title="品牌",order=8)
    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    @ExcelResources(title="车型",order=9)
    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    @ExcelResources(title="车系",order=10)
    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    @ExcelResources(title="车型年款",order=11)
    public String getModel_year() {
        return Model_year;
    }

    public void setModel_year(String model_year) {
        Model_year = model_year;
    }

    @ExcelResources(title="销售版本",order=12)
    public String getSales_version() {
        return Sales_version;
    }

    public void setSales_version(String sales_version) {
        Sales_version = sales_version;
    }

    @ExcelResources(title="排量",order=13)
    public String getCc() {
        return Cc;
    }

    public void setCc(String cc) {
        Cc = cc;
    }

    @ExcelResources(title="发动机号码",order=14)
    public String getEngine_no() {
        return Engine_no;
    }

    public void setEngine_no(String engine_no) {
        Engine_no = engine_no;
    }

    @ExcelResources(title="燃油类型",order=15)
    public String getFuel_type() {
        return Fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        Fuel_type = fuel_type;
    }

    @ExcelResources(title="进气形式",order=16)
    public String getAir_intake() {
        return Air_intake;
    }

    public void setAir_intake(String air_intake) {
        Air_intake = air_intake;
    }

//    @ExcelResources(title="功率",order=16)
//    public String getKW() {
//        return kw;
//    }
//
//    public void setKW(String KW) {
//        this.kw = KW;
//    }

    @ExcelResources(title="变速箱说明",order=17)
    public String getTransmission_detail() {
        return Transmission_detail;
    }

    public void setTransmission_detail(String transmission_detail) {
        Transmission_detail = transmission_detail;
    }

    @ExcelResources(title="档位数",order=18)
    public String getGear_num() {
        return Gear_num;
    }

    public void setGear_num(String gear_num) {
        Gear_num = gear_num;
    }

    @ExcelResources(title="车身类型",order=19)
    public String getDoor_num() {
        return Door_num;
    }

    public void setDoor_num(String door_num) {
        Door_num = door_num;
    }

    @ExcelResources(title="座位数",order=20)
    public String getSeat_num() {
        return Seat_num;
    }

    public void setSeat_num(String seat_num) {
        Seat_num = seat_num;
    }

    @ExcelResources(title="车身类型",order=21)
    public String getBody_type() {
        return Body_type;
    }

    public void setBody_type(String body_type) {
        Body_type = body_type;
    }

    @ExcelResources(title="驱动类型",order=22)
    public String getDriven_model() {
        return Driven_model;
    }

    public void setDriven_model(String driven_model) {
        Driven_model = driven_model;
    }

    @ExcelResources(title="上市日期",order=23)
    public String getDate_begin() {
        return Date_begin;
    }

    public void setDate_begin(String date_begin) {
        Date_begin = date_begin;
    }

    @ExcelResources(title="停产日期",order=24)
    public String getDate_end() {
        return Date_end;
    }

    public void setDate_end(String date_end) {
        Date_end = date_end;
    }

    @ExcelResources(title="4S店参考价格（元）",order=25)
    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @ExcelResources(title="汽车之家参考信息id",order=26)
    public String getAutohome_id() {
        return Autohome_id;
    }

    public void setAutohome_id(String autohome_id) {
        Autohome_id = autohome_id;
    }

    public WebDto(String jauto, String oe, Integer id, Integer isshowdata, String epc, String detail, String factory, String brand, String model, String series, String model_year, String sales_version, String cc, String engine_no, String fuel_type, String air_intake, String transmission_detail, String gear_num, String door_num, String seat_num, String body_type, String driven_model, String date_begin, String date_end, String price, String autohome_id) {
        this.jauto = jauto;
        this.oe = oe;
        Id = id;
        Isshowdata = isshowdata;
        Epc = epc;
        Detail = detail;
        Factory = factory;
        Brand = brand;
        Model = model;
        Series = series;
        Model_year = model_year;
        Sales_version = sales_version;
        Cc = cc;
        Engine_no = engine_no;
        Fuel_type = fuel_type;
        Air_intake = air_intake;
        Transmission_detail = transmission_detail;
        Gear_num = gear_num;
        Door_num = door_num;
        Seat_num = seat_num;
        Body_type = body_type;
        Driven_model = driven_model;
        Date_begin = date_begin;
        Date_end = date_end;
        Price = price;
        Autohome_id = autohome_id;
    }

    @Override
    public String toString() {
        return "WebDto{" +
                "oe='" + oe + '\'' +
                ", Id=" + Id +
                ", Isshowdata=" + Isshowdata +
                ", Epc='" + Epc + '\'' +
                ", Detail='" + Detail + '\'' +
                ", Factory='" + Factory + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Model='" + Model + '\'' +
                ", Series='" + Series + '\'' +
                ", Model_year='" + Model_year + '\'' +
                ", Sales_version='" + Sales_version + '\'' +
                ", Cc='" + Cc + '\'' +
                ", Engine_no='" + Engine_no + '\'' +
                ", Fuel_type='" + Fuel_type + '\'' +
                ", Air_intake='" + Air_intake + '\'' +
                ", Transmission_detail='" + Transmission_detail + '\'' +
                ", Gear_num='" + Gear_num + '\'' +
                ", Door_num='" + Door_num + '\'' +
                ", Seat_num='" + Seat_num + '\'' +
                ", Body_type='" + Body_type + '\'' +
                ", Driven_model='" + Driven_model + '\'' +
                ", Date_begin='" + Date_begin + '\'' +
                ", Date_end='" + Date_end + '\'' +
                ", Price='" + Price + '\'' +
                ", Autohome_id='" + Autohome_id + '\'' +
                '}';
    }
}
