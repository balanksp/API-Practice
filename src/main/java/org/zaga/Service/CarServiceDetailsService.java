package org.zaga.Service;

import org.zaga.Entity.CarEnum;
import org.zaga.Entity.CarServiceDetails;
import org.zaga.Repository.CarServiceDetailsRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CarServiceDetailsService {

    @Inject
    CarServiceDetailsRepo repository;

    @Inject
    EntityManager entityManager;

    @Transactional
    public CarServiceDetails createCarDetails(CarServiceDetails details) {

        System.out.println("-------------" + details);
        return entityManager.merge(details);

    }

    @Transactional
    public CarServiceDetails viewDetails(String carNumber, String customerName) {
        return repository.getDetailsByNumberAndName(carNumber, customerName);
    }

    @Transactional
    public void deleteServiceDetails(boolean deliveryStatus) {
        // List<CarServiceDetails> details =
        // CarServiceDetails.getdetailsbydeliveryStatus(deliveryStatus);
        if (deliveryStatus == true) {
            CarServiceDetails.delete("deliveryStatus = ?1", true);
        }

    }

    @Transactional
    public CarServiceDetails updateCarServiceDetails(String carNumber, String customerName, CarEnum carEnum) {

        CarServiceDetails details = repository.getDetailsByNumberAndName(carNumber, customerName);
        details.setServiceStatus(carEnum);
        if (carEnum.equals(CarEnum.DONE)) {
            details.setDeliveryStatus(true);
            details.setDeliveryAvailableStatus(true);
        }
        return details;

    }

}