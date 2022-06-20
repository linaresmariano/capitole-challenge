package com.inditex.pricing.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class APIErrorResponse {

	private String status;
	private String message;
	private List<String> errors;
	private String timestamp;
	private String path;

}
