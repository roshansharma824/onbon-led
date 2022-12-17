package com.stackfusion.onbon.data;

import java.io.Serializable;
import java.util.List;

public class JetsonData {
	  private Integer aoi_site_id;

	  private String site_logo_url;

	  private List<? extends All_gate_data> all_gate_data;

	  private String jetson_ip_address;

	  private String gate_name;

	  private String led_tv_ip;

	  private int font_size;

	  private int display_brightness;

	  private Boolean automatic_gateopening_bybarrier_flag;

	  private String aoi_site_name;

	  private Integer anpr_matching_max_accuracy;

	  private Boolean automatic_gateopening_bysite_pass_flag;

	  private Boolean is_jetson_attached;

	  private Integer gate_id;

	  private String gate_type;

	  private boolean payment_at_entry;

	  private String gate;

	  public Integer getAoi_site_id() {
	    return this.aoi_site_id;
	  }

	  public boolean getPayment_at_entry(){
		  return payment_at_entry;
	  }
	  public void setPayment_at_entry(boolean payment_at_entry){
		  this.payment_at_entry = payment_at_entry;
	  }

	  public void setAoi_site_id(Integer aoi_site_id) {
	    this.aoi_site_id = aoi_site_id;
	  }

	  public String getSite_logo_url() {
	    return this.site_logo_url;
	  }

	  public void setSite_logo_url(String site_logo_url) {
	    this.site_logo_url = site_logo_url;
	  }

	  public List<? extends All_gate_data> getAll_gate_data() {
	    return this.all_gate_data;
	  }

	  public void setAll_gate_data(List<? extends All_gate_data> all_gate_data) {
	    this.all_gate_data = all_gate_data;
	  }

	  public String getJetson_ip_address() {
	    return this.jetson_ip_address;
	  }

	  public void setJetson_ip_address(String jetson_ip_address) {
	    this.jetson_ip_address = jetson_ip_address;
	  }

	  public String getGate_name() {
	    return this.gate_name;
	  }

	  public void setGate_name(String gate_name) {
	    this.gate_name = gate_name;
	  }

	  public String getLed_tv_ip() {
	    return this.led_tv_ip;
	  }

	  public void setLed_tv_ip(String led_tv_ip) {
	    this.led_tv_ip = led_tv_ip;
	  }

	  public void setFont_size(int font_size){
		  this.font_size = font_size;
	  }

	public int getFont_size() {
		return this.font_size;
	}

	public void setDisplay_brightness(int display_brightness) {
		this.display_brightness = display_brightness;
	}

	public int getDisplay_brightness() {
		return this.display_brightness;
	}

	public Boolean getAutomatic_gateopening_bybarrier_flag() {
	    return this.automatic_gateopening_bybarrier_flag;
	  }

	  public void setAutomatic_gateopening_bybarrier_flag(Boolean automatic_gateopening_bybarrier_flag) {
	    this.automatic_gateopening_bybarrier_flag = automatic_gateopening_bybarrier_flag;
	  }

	  public String getAoi_site_name() {
	    return this.aoi_site_name;
	  }

	  public void setAoi_site_name(String aoi_site_name) {
	    this.aoi_site_name = aoi_site_name;
	  }

	  public Integer getAnpr_matching_max_accuracy() {
	    return this.anpr_matching_max_accuracy;
	  }

	  public void setAnpr_matching_max_accuracy(Integer anpr_matching_max_accuracy) {
	    this.anpr_matching_max_accuracy = anpr_matching_max_accuracy;
	  }

	  public Boolean getAutomatic_gateopening_bysite_pass_flag() {
	    return this.automatic_gateopening_bysite_pass_flag;
	  }

	  public void setAutomatic_gateopening_bysite_pass_flag(Boolean automatic_gateopening_bysite_pass_flag) {
	    this.automatic_gateopening_bysite_pass_flag = automatic_gateopening_bysite_pass_flag;
	  }

	  public Boolean getIs_jetson_attached() {
	    return this.is_jetson_attached;
	  }

	  public void setIs_jetson_attached(Boolean is_jetson_attached) {
	    this.is_jetson_attached = is_jetson_attached;
	  }

	  public Integer getGate_id() {
	    return this.gate_id;
	  }

	  public void setGate_id(Integer gate_id) {
	    this.gate_id = gate_id;
	  }

	  public String getGate_type() {
	    return this.gate_type;
	  }

	  public void setGate_type(String gate_type) {
	    this.gate_type = gate_type;
	  }

	  public String getGate() {
	    return this.gate;
	  }

	  public void setGate(String gate) {
	    this.gate = gate;
	  }

	  public static class All_gate_data implements Serializable {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String gate_type;

	    private List<? extends Jetson_details> jetson_details;

	    private String name;

	    private Integer id;

	    public String getGate_type() {
	      return this.gate_type;
	    }

	    public void setGate_type(String gate_type) {
	      this.gate_type = gate_type;
	    }

	    public List<? extends Jetson_details> getJetson_details() {
	      return this.jetson_details;
	    }

	    public void setJetson_details(List<? extends Jetson_details> jetson_details) {
	      this.jetson_details = jetson_details;
	    }

	    public String getName() {
	      return this.name;
	    }

	    public void setName(String name) {
	      this.name = name;
	    }

	    public Integer getId() {
	      return this.id;
	    }

	    public void setId(Integer id) {
	      this.id = id;
	    }

	    public static class Jetson_details implements Serializable {
	      /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		private Integer anpr_matching_max_accuracy;

	      private Boolean automatic_gateopening_bysitepass_flag;

	      private Boolean is_jetson_attached;

	      private Integer port;

	      private String mac_address;

	      private Object jetson_ip_address;

	      private Object zerotier_ip_address;

	      private String device_type;

	      private Integer gate;

	      private Boolean automatic_gateopening_bybarrier_flag;

	      private Boolean automatic_gateopening_on_zero_amount;

	      private String ip_used_by_device;

	      public Integer getAnpr_matching_max_accuracy() {
	        return this.anpr_matching_max_accuracy;
	      }

	      public void setAnpr_matching_max_accuracy(Integer anpr_matching_max_accuracy) {
	        this.anpr_matching_max_accuracy = anpr_matching_max_accuracy;
	      }

	      public Boolean getAutomatic_gateopening_bysitepass_flag() {
	        return this.automatic_gateopening_bysitepass_flag;
	      }

	      public void setAutomatic_gateopening_bysitepass_flag(Boolean automatic_gateopening_bysitepass_flag) {
	        this.automatic_gateopening_bysitepass_flag = automatic_gateopening_bysitepass_flag;
	      }

	      public Boolean getIs_jetson_attached() {
	        return this.is_jetson_attached;
	      }

	      public void setIs_jetson_attached(Boolean is_jetson_attached) {
	        this.is_jetson_attached = is_jetson_attached;
	      }

	      public Integer getPort() {
	        return this.port;
	      }

	      public void setPort(Integer port) {
	        this.port = port;
	      }

	      public String getMac_address() {
	        return this.mac_address;
	      }

	      public void setMac_address(String mac_address) {
	        this.mac_address = mac_address;
	      }

	      public Object getJetson_ip_address() {
	        return this.jetson_ip_address;
	      }

	      public void setJetson_ip_address(Object jetson_ip_address) {
	        this.jetson_ip_address = jetson_ip_address;
	      }

	      public Object getZerotier_ip_address() {
	        return this.zerotier_ip_address;
	      }

	      public void setZerotier_ip_address(Object zerotier_ip_address) {
	        this.zerotier_ip_address = zerotier_ip_address;
	      }

	      public String getDevice_type() {
	        return this.device_type;
	      }

	      public void setDevice_type(String device_type) {
	        this.device_type = device_type;
	      }

	      public Integer getGate() {
	        return this.gate;
	      }

	      public void setGate(Integer gate) {
	        this.gate = gate;
	      }

	      public Boolean getAutomatic_gateopening_bybarrier_flag() {
	        return this.automatic_gateopening_bybarrier_flag;
	      }

	      public void setAutomatic_gateopening_bybarrier_flag(Boolean automatic_gateopening_bybarrier_flag) {
	        this.automatic_gateopening_bybarrier_flag = automatic_gateopening_bybarrier_flag;
	      }

	      public Boolean getAutomatic_gateopening_on_zero_amount() {
	        return this.automatic_gateopening_on_zero_amount;
	      }

	      public void setAutomatic_gateopening_on_zero_amount(Boolean automatic_gateopening_on_zero_amount) {
	        this.automatic_gateopening_on_zero_amount = automatic_gateopening_on_zero_amount;
	      }

	      public String getIp_used_by_device() {
	        return this.ip_used_by_device;
	      }

	      public void setIp_used_by_device(String ip_used_by_device) {
	        this.ip_used_by_device = ip_used_by_device;
	      }
	    }
	  }
	}