package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestDTO.SchoolRequest;
import com.school.sba.responseDTO.SchoolResponse;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

public interface SchoolService {

	ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@Valid SchoolRequest schoolRequest, int userId);

}