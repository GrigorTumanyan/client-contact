package am.info.contact.model.mapper;

import am.info.contact.model.Client;
import am.info.contact.model.dto.response.client.ResponseClientDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClientMapper {

	private final ModelMapper modelMapper;

	public ResponseClientDto toResponseClientDto(Client client) {
		return modelMapper.map(client, ResponseClientDto.class);
	}


}
