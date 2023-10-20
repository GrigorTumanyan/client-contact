package am.info.contact.model.dto.request.client;

import am.info.contact.model.Email;
import am.info.contact.model.Phone;
import java.util.List;
import lombok.Getter;

@Getter
public class RequestClientDto {

	private String name;
	private List<Email> emails;
	private List<Phone> phones;

}
