package drg0n20petclinic.services;

import drg0n20petclinic.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
