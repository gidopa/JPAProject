package Team.project.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
}
