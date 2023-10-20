package am.info.contact.model.dto.response.client;

import am.info.contact.model.Email;
import am.info.contact.model.Phone;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientDto {

	private Long id;
	private String name;
	private List<Email> emails;
	private List<Phone> phones;
}
