package Team.project.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;

    public void updateAddress(String city, String street){
        this.city = city;
        this.street = street;
    }
}
