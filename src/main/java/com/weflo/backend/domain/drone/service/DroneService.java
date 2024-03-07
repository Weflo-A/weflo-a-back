package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.request.SearchDroneRequest;
import com.weflo.backend.domain.drone.dto.response.DroneGroupResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.SearchDroneResponse;
import com.weflo.backend.domain.drone.repository.DroneGroupInfoRepository;
import com.weflo.backend.domain.drone.repository.DroneGroupRepository;
import com.weflo.backend.domain.drone.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private final DroneGroupInfoRepository droneGroupInfoRepository;
    private final DroneGroupRepository droneGroupRepository;
    public List<SearchDroneResponse> searchDrone(SearchDroneRequest searchDroneRequest){
        return createSearchDroneResponse(searchDroneRequest);
    }
    private List<SearchDroneResponse> createSearchDroneResponse(SearchDroneRequest searchDroneRequest){
        List<Drone> drones = droneRepository.findAllByNameContaining(searchDroneRequest.getName());
        List<Drone> searchResults = new ArrayList<>();
        List<DroneGroupResponse> groupInfo = createDroneGroupInfoResponses();
        for(Drone drone : drones){
            List<DroneGroup> droneGroups = droneGroupInfoRepository.findAllDroneGroupByDroneId(drone.getId());
            if (searchDroneRequest.getModel().stream().anyMatch(model -> model.equalsIgnoreCase(String.valueOf(drone.getModel())))) {
                searchResults.add(drone);
            }
            else if(searchDroneRequest.getYear().stream().anyMatch(year -> year.equals(drone.getProductionYear().getYear()))){
                searchResults.add(drone);
            }
            else if (searchDroneRequest.getGroup().stream()
                    .anyMatch(group -> droneGroups.stream().map(DroneGroup::getName).collect(Collectors.toList()).contains(group))) {
                searchResults.add(drone);
            }
        }
        return searchResults.stream()
                .map(searchResult ->
                        SearchDroneResponse.of(groupInfo,
                                searchResult, getGroupsWithDroneId(searchResult.getId())
                        ))
                .collect(Collectors.toList());
    }
    private List<DroneGroupResponse> createDroneGroupInfoResponses(){
        List<DroneGroup> droneGroups = droneGroupRepository.findAll();
        return droneGroups.stream().map(droneGroup -> DroneGroupResponse.of(droneGroup)).collect(Collectors.toList());
    }
    private List<String> getGroupsWithDroneId(Long droneId){
        List<DroneGroup> droneGroups = droneGroupInfoRepository.findAllDroneGroupByDroneId(droneId);
        return droneGroups.stream()
                .map(droneGroup -> droneGroup.getName())
                .collect(Collectors.toList());
    }
}
