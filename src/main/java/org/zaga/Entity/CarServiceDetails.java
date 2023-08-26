package org.zaga.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarServiceDetails extends PanacheEntity  {

    private String carNumber;
    private String customerName;
    private CarEnum serviceStatus;
    private boolean deliveryAvailableStatus;
    private boolean deliveryStatus;

}
