package am.info.contact.model;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "emails", joinColumns = @JoinColumn(name = "client_id"))
	private List<Email> emails;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "client_id"))
	private List<Phone> phones;

	public Client(String name, List<Email> emails, List<Phone> phones) {
		this.name = name;
		this.emails = emails;
		this.phones = phones;
	}
}
