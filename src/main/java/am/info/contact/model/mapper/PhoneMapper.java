package am.info.contact.model.mapper;

import am.info.contact.model.Phone;
import am.info.contact.model.dto.response.client.PhoneDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PhoneMapper {

	private final ModelMapper modelMapper;

	public PhoneDto toPhoneDto(Phone phone) {
		return modelMapper.map(phone, PhoneDto.class);
	}

}
