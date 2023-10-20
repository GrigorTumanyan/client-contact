package am.info.contact.model.dto.response.client;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto extends ContactDto {

	private String emailAddress;

}
