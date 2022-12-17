package com.stackfusion.onbon.data;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;

public class AdList implements Serializable {
  private String end_date;

  private String updated_on;

  private String image;

  private Integer aoi_site;

  private Object description;

  private Object video;

  private String title;

  private String created_on;

  private Object normal_rate;

  private Object special_rate;

  private Integer id;

  private String brand;

  private String start_date;

  public String getEnd_date() {
    return this.end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public String getUpdated_on() {
    return this.updated_on;
  }

  public void setUpdated_on(String updated_on) {
    this.updated_on = updated_on;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getAoi_site() {
    return this.aoi_site;
  }

  public void setAoi_site(Integer aoi_site) {
    this.aoi_site = aoi_site;
  }

  public Object getDescription() {
    return this.description;
  }

  public void setDescription(Object description) {
    this.description = description;
  }

  public Object getVideo() {
    return this.video;
  }

  public void setVideo(Object video) {
    this.video = video;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCreated_on() {
    return this.created_on;
  }

  public void setCreated_on(String created_on) {
    this.created_on = created_on;
  }

  public Object getNormal_rate() {
    return this.normal_rate;
  }

  public void setNormal_rate(Object normal_rate) {
    this.normal_rate = normal_rate;
  }

  public Object getSpecial_rate() {
    return this.special_rate;
  }

  public void setSpecial_rate(Object special_rate) {
    this.special_rate = special_rate;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getStart_date() {
    return this.start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }
}
