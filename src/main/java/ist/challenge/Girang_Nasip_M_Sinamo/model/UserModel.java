package ist.challenge.Girang_Nasip_M_Sinamo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "ist")

public class UserModel {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	
	public UserModel(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
