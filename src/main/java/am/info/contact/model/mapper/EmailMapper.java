package am.info.contact.model.mapper;

import am.info.contact.model.Email;
import am.info.contact.model.dto.response.client.EmailDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailMapper {

	private final ModelMapper modelMapper;

	public EmailDto toEmailDto(Email email) {
		return modelMapper.map(email, EmailDto.class);
	}
}
