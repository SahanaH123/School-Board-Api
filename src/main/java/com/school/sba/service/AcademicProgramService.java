package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestDTO.AcademicProgramRequest;
import com.school.sba.responseDTO.AcademicProgramResponse;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AcademicProgramService {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveAcademicProgram(
			@Valid AcademicProgramRequest academicProgramRequest,int schoolId);

	ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAllAcademicProgram(int schoolId);

}