package com.school.sba.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.Enum.UserRole;
import com.school.sba.entity.School;

import com.school.sba.exception.UnauthorizedException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestDTO.SchoolRequest;
import com.school.sba.responseDTO.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseEntityProxy;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class SchoolServiceImpl implements SchoolService {
	@Autowired
	private SchoolRepository schoolRepository;
	@Autowired
	private UserRepository userRepository;
	
	//Mapper Methods
	private School mapToSchool(SchoolRequest schoolRequest) 
	{
		return School.builder()
				.schoolName(schoolRequest.getSchoolName())
				.contactNo(schoolRequest.getContactNo())
				.emailId(schoolRequest.getEmailId())
				.address(schoolRequest.getAddress())
				.build();
	}
	
	private SchoolResponse mapToSchoolResponse(School school) 
	{
		return SchoolResponse.builder()
				.schoolId(school.getSchoolId())
				.schoolName(school.getSchoolName())
				.contactNo(school.getContactNo())
				.emailId(school.getEmailId())
				.address(school.getAddress())
				.build();
	}
	
	@Override	
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@Valid SchoolRequest schoolRequest, int userId) {
	    return userRepository.findById(userId)
	            .map(user -> {
	                if (user.getUserRole().equals(UserRole.ADMIN)) {
	                    if (user.getSchool() == null) {
	                        School school = schoolRepository.save(mapToSchool(schoolRequest));
	                        user.setSchool(school);
	                        userRepository.save(user);
	                        return ResponseEntityProxy.getResponseEntity(HttpStatus.CREATED, "School data saved successfully.", mapToSchoolResponse(school));
	                    } else {
	                        throw new IllegalArgumentException("Cannot create more than one school.");
	                    }
	                } else {
	                    throw new UnauthorizedException("Only admins are allowed to create schools.");
	                }
	            })
	            .orElseThrow(() -> new UserNotFoundByIdException("Invalid User Id"));
	}

}