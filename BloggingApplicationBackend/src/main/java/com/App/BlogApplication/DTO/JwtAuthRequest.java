package com.App.BlogApplication.DTO;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;
}
