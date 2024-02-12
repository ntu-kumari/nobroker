package com.nobroker.service.impl;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.repository.OwnerPlanRepository;
import com.nobroker.service.OwnerPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerPlanServiceImpl implements OwnerPlanService {

    private final OwnerPlanRepository ownerPlanRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OwnerPlanServiceImpl(OwnerPlanRepository ownerPlanRepository, ModelMapper modelMapper) {
        this.ownerPlanRepository = ownerPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OwnerPlanDto createOwnerPlans(OwnerPlanDto ownerPlanDto) {
        OwnerPlan ownerPlan = mapToEntity(ownerPlanDto);
        OwnerPlan savedOwnerPlan = ownerPlanRepository.save(ownerPlan);
        return mapToDto(savedOwnerPlan);
    }

    @Override
    public List<OwnerPlanDto> getAllOwnerPlan() {
        List<OwnerPlan> ownerPlans = ownerPlanRepository.findAll();
        List<OwnerPlanDto> ownerPlanDtos = ownerPlans.stream().map(plan -> mapToDto(plan)).collect(Collectors.toList());
    return ownerPlanDtos;
    }

    private OwnerPlan mapToEntity(OwnerPlanDto ownerPlanDto) {
        return modelMapper.map(ownerPlanDto, OwnerPlan.class);
    }

    private OwnerPlanDto mapToDto(OwnerPlan ownerPlan) {
        return modelMapper.map(ownerPlan, OwnerPlanDto.class);
    }
}
