package com.taxi.taxista.DTO;

import com.taxi.taxista.entity.enums.VehiculeStatus;
import com.taxi.taxista.entity.enums.VehiculeType;
import lombok.Data;

import java.util.Map;

@Data
public class VehiculeAnalyticsDTO {

    private Map<VehiculeType, Double> averageMileageByType;
    private Map<VehiculeType, Double> utilizationRateByType;
    private Map<VehiculeStatus, Long> fleetStatusCount;

}
