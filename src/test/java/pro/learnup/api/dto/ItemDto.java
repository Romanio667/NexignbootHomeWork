package pro.learnup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

	@JsonProperty("product")
	private PhoneDto product;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("_id")
	private String id;
}