package com.library_management_system.library_management_system.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Librarian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "* Librarian ID is required")
	private String librarianId;
	
	@NotBlank(message = "* Full name is required")
	@Size(min = 3, max = 25, message = "Enter between 3-25 characters")
	private String fullName;
	
	@Email(message = "* Enter valid email")
	@NotEmpty(message = "* It is Required")
	private String email;
	
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "* Enter proper phone number")
	private String phone;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
	         message = "Password must contain:\n" +
	                  "- At least 8 characters\n" +
	                  "- One uppercase letter\n" +
	                  "- One lowercase letter\n" +
	                  "- One number\n" +
	                  "- One special character (@#$%^&+=)\n" +
	                  "- No spaces")

	private String password;
	
	@Transient
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
    message = "Password must contain:\n" +
             "- At least 8 characters\n" +
             "- One uppercase letter\n" +
             "- One lowercase letter\n" +
             "- One number\n" +
             "- One special character (@#$%^&+=)\n" +
             "- No spaces")
	private String confirmPassword;
	
}
