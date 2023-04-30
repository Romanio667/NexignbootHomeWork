package pro.learnup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	@JsonProperty("__v")
	private int V;

	@JsonProperty("_id")
	private String id;

	@JsonProperty("user")
	private String user;

	@JsonProperty("items")
	private List<ItemDto> items;
}