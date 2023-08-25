package org.zaga.Repository;

import org.zaga.Entity.CarServiceDetails;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarServiceDetailsRepo implements PanacheRepository<CarServiceDetails> {

    public CarServiceDetails getDetailsByNumberAndName(String carNumber, String customerName) {
        CarServiceDetails details = CarServiceDetails.find("carNumber=?1 and customerName=?2", carNumber, customerName)
                .firstResult();
        return details;
    }

    public CarServiceDetails getdetailsbydeliveryStatus(boolean deliveryStatus){
           PanacheQuery<CarServiceDetails> details = CarServiceDetails.find("deliveryStatus=?1", deliveryStatus);
          CarServiceDetails cc = (CarServiceDetails) details;
          CarServiceDetails detail = details.firstResult();
        System.out.println("---------list of all details -------"+cc);
        return detail;
    }
}
