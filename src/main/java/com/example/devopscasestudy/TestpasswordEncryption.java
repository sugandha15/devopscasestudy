package com.example.devopscasestudy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestpasswordEncryption {

	
		public static void main(String[] args) {

			int i = 0;
			
				String password = "pass2";
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(password);

				System.out.println(hashedPassword);
				

				  
		}
	}
