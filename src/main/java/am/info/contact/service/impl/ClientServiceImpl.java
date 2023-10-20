package am.info.contact.service.impl;

import am.info.contact.exception.NotFoundException;
import am.info.contact.model.Client;
import am.info.contact.model.Email;
import am.info.contact.model.Phone;
import am.info.contact.model.dto.request.client.RequestClientDto;
import am.info.contact.model.dto.response.client.ClientContactsDto;
import am.info.contact.model.dto.response.client.ContactDto;
import am.info.contact.model.dto.response.client.EmailDto;
import am.info.contact.model.dto.response.client.PhoneDto;
import am.info.contact.model.dto.response.client.ResponseClientDto;
import am.info.contact.model.mapper.ClientMapper;
import am.info.contact.model.mapper.EmailMapper;
import am.info.contact.model.mapper.PhoneMapper;
import am.info.contact.repo.ClientRepository;
import am.info.contact.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private static final String EMAILS = "emails";
	private static final String PHONES = "phones";
	private final ClientRepository repository;
	private final ObjectMapper objectMapper;
	private final ClientMapper clientMapper;
	private final PhoneMapper phoneMapper;
	private final EmailMapper emailMapper;

	@Override
	public ResponseClientDto add(RequestClientDto clientDto) {
		var client = new Client(clientDto.getName(), clientDto.getEmails(), clientDto.getPhones());
		var savedClient = repository.save(client);
		return clientMapper.toResponseClientDto(savedClient);
	}

	@Override
	public ResponseClientDto addContact(Long id, Map<String, Object> contacts) {
		var dbClient = repository.findById(id)
			.orElseThrow(() -> new NotFoundException("Id :" + id, repository.getClass().getName()));
		Client client = inferContactsType(dbClient, contacts);

		repository.save(client);
		return clientMapper.toResponseClientDto(client);
	}

	@Override
	public List<ResponseClientDto> getAll() {
		var clients = repository.findAll();
		return clients.stream().map(clientMapper::toResponseClientDto).toList();
	}

	@Override
	public ResponseClientDto getById(Long id) {
		return repository.findById(id)
			.map(clientMapper::toResponseClientDto).orElse(null);
	}

	@Override
	public ClientContactsDto getContactsByClientId(Long clientId) {

		var phoneNumbers = repository.findPhoneNumberByClientId(clientId);
		List<PhoneDto> phoneDtoList = phoneNumbers.stream()
			.map(phoneMapper::toPhoneDto).toList();

		var emails = repository.findEmailsByClientId(clientId);
		List<EmailDto> emailDtoList = emails.stream()
			.map(emailMapper::toEmailDto).toList();

		return new ClientContactsDto(emailDtoList, phoneDtoList);
	}

	@Override
	public List<ContactDto> getContactByClientId(Long clientId, String contactType) {
		List<ContactDto> contactDtos;
		switch (contactType) {
			case EMAILS -> contactDtos = new ArrayList<>(repository.findEmailsByClientId(clientId).stream()
				.map(emailMapper::toEmailDto).toList());
			case PHONES -> contactDtos = new ArrayList<>(repository.findPhoneNumberByClientId(clientId).stream()
				.map(phoneMapper::toPhoneDto).toList());
			default -> throw new NotFoundException(contactType, repository.getClass().getName());
		}
		return contactDtos;
	}

	private Client inferContactsType(Client client, Map<String, Object> contacts) {
		for (String s : contacts.keySet()) {
			switch (s) {
				case EMAILS -> client.getEmails().add(new Email(convertValue(contacts.get(s), String.class)));
				case PHONES -> client.getPhones().add(new Phone(convertValue(contacts.get(s), String.class)));
				default -> throw new NotFoundException(s, repository.getClass().getName());
			}
		}
		return client;
	}

	private <T> T convertValue(Object value, Class<T> clazz) {

		return objectMapper.convertValue(value, clazz);
	}
}