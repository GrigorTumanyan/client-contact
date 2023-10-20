package am.info.contact.model.dto.response.client;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientContactsDto {

	private List<EmailDto> emails;
	private List<PhoneDto> phones;


}
