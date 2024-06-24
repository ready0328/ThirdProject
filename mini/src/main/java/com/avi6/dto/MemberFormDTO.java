package com.avi6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 외부에서 전달받기 위한 dto
public class MemberFormDTO {
	
	private String email;
    private String username;
    private String password;

}
