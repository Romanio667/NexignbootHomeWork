package pro.learnup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.learnup.api.serializers.LocalDateTimeDeserializer;
import pro.learnup.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order{

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonProperty("dateCreated")
	private LocalDateTime dateCreated;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("price")
	private int price;

	@JsonProperty("name")
	private String name;
}