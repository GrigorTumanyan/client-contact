package am.info.contact.controller;

import am.info.contact.model.dto.request.client.RequestClientDto;
import am.info.contact.model.dto.response.client.ClientContactsDto;
import am.info.contact.model.dto.response.client.ContactDto;
import am.info.contact.model.dto.response.client.ResponseClientDto;
import am.info.contact.service.ClientService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clients")
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	public ResponseEntity<ResponseClientDto> add(@RequestBody RequestClientDto clientDto) {
		var responseDto = clientService.add(clientDto);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@PatchMapping("{id}/contact")
	public ResponseEntity<ResponseClientDto> addContact(@PathVariable Long id,
		@RequestBody Map<String, Object> contacts) {
		var responseDto = clientService.addContact(id, contacts);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ResponseClientDto>> getAll() {
		var responseDto = clientService.getAll();
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ResponseClientDto> getById(@PathVariable Long id) {
		var responseDto = clientService.getById(id);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping(value = "{clientId}/contacts")
	public ResponseEntity<ClientContactsDto> getContactsByClientId(@PathVariable Long clientId) {
		var responseDto = clientService.getContactsByClientId(clientId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("{clientId}/contact")
	public ResponseEntity<List<ContactDto>> getContactByClientId(@PathVariable Long clientId,
		@RequestParam String contactType) {
		var responseDto = clientService.getContactByClientId(clientId, contactType);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
