package com.library_management_system.library_management_system.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "* Full name is required")
	@Size(min = 3, max = 25, message = "* Enter between 3-25 characters")
	private String fullName;

	@NotBlank(message = "* Email is required")
	@Email(message = "* Enter valid email")
	private String email;

	@Pattern(regexp = "^[6-9]\\d{9}$", message = "* Enter proper phone number")
	private String phone;

	@NotBlank(message = "* Address is required")
	@Size(min = 10, max = 45, message = "* Enter between 10-45 characters")
	private String address;

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
