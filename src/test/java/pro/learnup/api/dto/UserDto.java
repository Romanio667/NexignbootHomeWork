package pro.learnup.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto{

	@JsonProperty("password")
	private String password;

	@JsonProperty("address")
	private String address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("orders")
	private List<Order> orders;

	@JsonProperty("id")
	private String id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	@JsonProperty("token")
	private String token;


	public Header authHeader() {
		return new Header("Authorization", "Bearer " + this.getToken());
	}
}