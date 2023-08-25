package org.zaga.Repository;

import org.zaga.Entity.CarServiceDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarServiceDetailsRepo implements PanacheRepository<CarServiceDetails> {

    public CarServiceDetails getDetailsByNumberAndName(String carNumber, String customerName) {
        CarServiceDetails details = CarServiceDetails.find("carNumber=?1 and customerName=?2", carNumber, customerName)
                .firstResult();
        return details;
    }
}
