package org.zaga.Service;

import org.zaga.Entity.CarEnum;
import org.zaga.Entity.CarServiceDetails;
import org.zaga.Repository.CarServiceDetailsRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

@ApplicationScoped
public class CarServiceDetailsService {

    @Inject
    CarServiceDetailsRepo repository;

    @Transactional
    public CarServiceDetails createCarDetails(CarServiceDetails details) {
        CarServiceDetails.persist(details);
        return details;

    }

    @Transactional
    public CarServiceDetails viewDetails(String carNumber, String customerName) {
        return repository.getDetailsByNumberAndName(carNumber, customerName);
    }

    @Transactional
    public void deleteServiceDetails(String carNumber, String customerName) {
        long details = repository.delete("carNumber=?1 and customerName=?2", carNumber, customerName);
        System.out.println(details);
        // repository.delete(details);

    }

    @Transactional
    public CarServiceDetails updateCarServiceDetails(String carNumber, String customerName, CarEnum carEnum) {

        CarServiceDetails details = repository.getDetailsByNumberAndName(carNumber, customerName);
        details.setServiceStatus(carEnum);
        // // details.update(details.isDeliveryStatus());
        if (carEnum.equals(CarEnum.DONE)) {
            details.setDeliveryStatus(true);
            details.setDeliveryAvailableStatus(true);
        }
        return details;

    }

}