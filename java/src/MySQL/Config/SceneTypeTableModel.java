package MySQL.Config;

import java.sql.Timestamp;

public class SceneTypeTableModel {
	
	private Integer id = 0;
	private Integer home_id = 0;
	private String name = "";
	private String icon = ""; 
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

	public void setname(String name) {
		this.name = name;
	}

	public String getname() {
		return this.name;
	}

	public void seticon(String icon) {
		this.icon = icon;
	}

	public String geticon() {
		return this.icon;
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
