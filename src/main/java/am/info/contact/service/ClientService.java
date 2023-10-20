package am.info.contact.service;

import am.info.contact.model.dto.request.client.RequestClientDto;
import am.info.contact.model.dto.response.client.ClientContactsDto;
import am.info.contact.model.dto.response.client.ContactDto;
import am.info.contact.model.dto.response.client.ResponseClientDto;
import java.util.List;
import java.util.Map;

public interface ClientService {

	ResponseClientDto add(RequestClientDto clientDto);

	ResponseClientDto addContact(Long id, Map<String, Object> contacts);

	List<ResponseClientDto> getAll();

	ResponseClientDto getById(Long id);

	ClientContactsDto getContactsByClientId(Long clientId);

	List<ContactDto> getContactByClientId(Long clientId, String contactType);
}
