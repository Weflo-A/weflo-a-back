package com.weflo.backend.domain.drone.service;

import com.weflo.backend.domain.drone.domain.Drone;
import com.weflo.backend.domain.drone.domain.DroneGroup;
import com.weflo.backend.domain.drone.dto.request.SearchDroneRequest;
import com.weflo.backend.domain.drone.dto.response.DroneGroupResponse;
import com.weflo.backend.domain.drone.dto.response.DroneListResponse;
import com.weflo.backend.domain.drone.dto.response.onBoarding.SearchDroneListResponse;
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
    public SearchDroneResponse searchDrone(SearchDroneRequest searchDroneRequest){
            SearchDroneResponse searchDroneResponse = createSearchDroneResponse(searchDroneRequest);
            return searchDroneResponse;
    }
    private SearchDroneResponse createSearchDroneResponse(SearchDroneRequest searchDroneRequest){
        List<Drone> drones = droneRepository.findAllByNameContaining(searchDroneRequest.getName());
        List<Drone> searchResults = new ArrayList<>();
        List<DroneGroupResponse> groupInfo = createDroneGroupInfoResponses();
        for(Drone drone : drones){
            List<DroneGroup> droneGroups = droneGroupInfoRepository.findAllDroneGroupByDroneId(drone.getId());
            if (searchDroneRequest.getModel().stream().anyMatch(model -> model.equalsIgnoreCase(String.valueOf(drone.getModel())))) {
                searchResults.add(drone);
            }
            else if(searchDroneRequest.getYear().stream().anyMatch(year -> year.equals(drone.getProductionYear()))){
                searchResults.add(drone);
            }
            else if (searchDroneRequest.getGroup().stream()
                    .anyMatch(group -> droneGroups.stream().map(DroneGroup::getName).collect(Collectors.toList()).contains(group))) {
                searchResults.add(drone);
            }
        }
        if(searchDroneRequest.getName().isEmpty()&&searchDroneRequest.getModel().isEmpty()&&searchDroneRequest.getGroup().isEmpty()&&searchDroneRequest.getYear().isEmpty()){
            searchResults = droneRepository.findAll();
        }
        if(searchDroneRequest.getModel().isEmpty()&&searchDroneRequest.getGroup().isEmpty()&&searchDroneRequest.getYear().isEmpty()){
            searchResults = drones;
        }
        List<SearchDroneListResponse> searchDroneListResponses = createSearchDroneListResponses(searchResults);
        return SearchDroneResponse.of(groupInfo,searchDroneListResponses);
    }
    private List<SearchDroneListResponse> createSearchDroneListResponses(List<Drone> drones){
        return drones.stream()
                .map(drone ->
                        SearchDroneListResponse.of(drone,
                                getGroupsWithDroneId(drone.getId())
                        ))
                .collect(Collectors.toList());
    }
    private List<DroneGroupResponse> createDroneGroupInfoResponses(){
        List<DroneGroup> droneGroups = droneGroupRepository.findAll();
        List<DroneGroupResponse> droneGroupResponses = droneGroups.stream().map(droneGroup -> DroneGroupResponse.of(droneGroup)).collect(Collectors.toList());
        droneGroupResponses.add(DroneGroupResponse.create("전체"));
        return droneGroupResponses;
    }
    private List<String> getGroupsWithDroneId(Long droneId){
        List<DroneGroup> droneGroups = droneGroupInfoRepository.findAllDroneGroupByDroneId(droneId);
        return droneGroups.stream()
                .map(droneGroup -> droneGroup.getName())
                .collect(Collectors.toList());
    }

    public List<DroneListResponse> getAllDrones() {
        List<Drone> allDrones = droneRepository.findAll();
        return allDrones.stream().map(DroneListResponse::of).toList();
    }
}
