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
public class PhoneDto{

	@JsonProperty("_id")
	private String id;

	@JsonProperty("info")
	private Info info;

	@JsonProperty("tags")
	private Tags tags;
}