package MySQL.Config;

import java.sql.Timestamp;

public class ScenesTableModel {

	private Integer id = 0;
	private Integer home_id = 0;
	private Integer scene_type_id = 0;
	private String component_img = "";
	private double component_price = 0;
	private String card_name = "";
	private String card_img = "";
	private double card_price = 0;
	private String type = "";
	private Timestamp created_at = null;
	private Timestamp updated_at = null;

	public void setid(Integer id) {
		this.id = id;
	}
	
	public Integer getid() {
		return this.id;
	}
	
	public void sethome_id(Integer home_id) {
		this.home_id = home_id;
	}

	public Integer gethome_id() {
		return this.home_id;
	}

	public void setscene_type_id(Integer scene_type_id) {
		this.scene_type_id = scene_type_id;
	}

	public Integer getscene_type_id() {
		return this.scene_type_id;
	}

	public void setcomponent_img(String component_img) {
		this.component_img = component_img;
	}

	public String getcomponent_img() {
		return this.component_img;
	}

	public void setcomponent_price(Double component_price) {
		this.component_price = component_price;
	}

	public Double getcomponent_price() {
		return this.component_price;
	}

	public void setcard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getcard_name() {
		return this.card_name;
	}

	public void setcard_img(String card_img) {
		this.card_img = card_img;
	}

	public String getcard_img() {
		return this.card_img;
	}

	public void setcard_price(Double card_price) {
		this.card_price = card_price;
	}

	public Double getcard_price() {
		return this.card_price;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String gettype() {
		return this.type;
	}

	public void setcreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getcreated_at() {
		return this.created_at;
	}

	public void setupdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	public Timestamp getupdated_at() {
		return this.updated_at;
	}
}
