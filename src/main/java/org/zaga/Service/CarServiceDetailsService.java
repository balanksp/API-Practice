package org.zaga.Service;

import org.zaga.Entity.CarEnum;
import org.zaga.Entity.CarServiceDetails;
import org.zaga.Repository.CarServiceDetailsRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

@ApplicationScoped
public class CarServiceDetailsService {

    @Inject
    CarServiceDetailsRepo repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public CarServiceDetails createCarDetails(CarServiceDetails details) {
      
        System.out.println("-------------"+details);
        return entityManager.merge(details);

    }

    @Transactional
    public CarServiceDetails viewDetails(String carNumber, String customerName) {
        return repository.getDetailsByNumberAndName(carNumber, customerName);
    }

    @Transactional
    public void deleteServiceDetails(boolean deliveryStatus) {
      CarServiceDetails details = repository.getdetailsbydeliveryStatus(deliveryStatus);
      System.out.println(details+"........>>>>>><<<<<<...........");
                   if(details != null && details.isDeliveryStatus()){
                    details.delete();
                   }
    //   return details;
    }

    @Transactional
    public CarServiceDetails updateCarServiceDetails(String carNumber, String customerName, CarEnum carEnum) {

        CarServiceDetails details = repository.getDetailsByNumberAndName(carNumber, customerName);
        details.setServiceStatus(carEnum);
        // details.update(details.isDeliveryStatus());
        if (carEnum.equals(CarEnum.DONE)) {
            details.setDeliveryStatus(true);
            details.setDeliveryAvailableStatus(true);
        }
        return details;

    }

}