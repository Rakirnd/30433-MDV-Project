package data.entity;

import javax.persistence.*;

@Entity
public class Owner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "bank_account", nullable = false, unique = true)
	private String bankAccount;
	
	@Column(name = "is_admin", nullable = false)
	private boolean isAdmin;
	
	public Owner() {
		
	}
	
	public Owner(int id, String name, String password, String bankAccount, boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.bankAccount = bankAccount;
		this.isAdmin = isAdmin;
	}

	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	

}
