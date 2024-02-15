package com.app.booktaxi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.booktaxi.dto.AuthSignInDTO;
import com.app.booktaxi.dto.SigninResponse;
import com.app.booktaxi.service.AdminService;
import com.app.booktaxi.service.CustomerService;
import com.app.booktaxi.service.DriverService;
import com.app.booktaxi.service.OwnerService;
import com.app.booktaxi.service.UserEntityService;
import com.app.security.JwtUtils;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserEntityService userService;
	// dep : auth mgr
	@Autowired
	private AuthenticationManager mgr;
	// jwt utils
	@Autowired
	private JwtUtils utils;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService custService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private DriverService driverService;

	// URL : http://host:port/home/signin , method : POST
	// payload : em n pwd n role: AuthSignInDTO
	// resp : JWT in resp dto
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateLogin(@RequestBody @Valid AuthSignInDTO reqDTO) {
//		System.out.println("in signin " + reqDTO);
//		// simply invoke authentucate(...) on AuthMgr
//		// i/p : Authentication => un verifed credentials
//		// i/f --> Authentication --> imple by UsernamePasswordAuthToken
//		// throws exc OR rets : verified credentials (UserDetails i.pl class: custom
//		// user details)
//
//		if (reqDTO.getRole().equalsIgnoreCase("admin")) {
//			AdminRespDTO adminDto = adminService.doLogin(reqDTO);
//			if (adminDto != null) {
//				Authentication verifiedAuth = mgr
//						.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));
//				System.out.println(verifiedAuth.getClass());// Custom user details
//				// => auth success
//				return ResponseEntity
//						.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
//				// return ResponseEntity.ok(adminDto);
//
//			} else
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//		} else if (reqDTO.getRole().equalsIgnoreCase("customer")) {
//			CustomerRespDTO custDto = custService.doLogin(reqDTO);
//			if (custDto != null) {
//				Authentication verifiedAuth = mgr
//						.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));
//				System.out.println(verifiedAuth.getClass());// Custom user details
//				// => auth success
//				return ResponseEntity
//						.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
//				// return ResponseEntity.ok(custDto);
//			} else
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//		} else if (reqDTO.getRole().equalsIgnoreCase("owner")) {
//			OwnerRespDTO ownerDto = ownerService.doLogin(reqDTO);
//			if (ownerDto != null) {
//				Authentication verifiedAuth = mgr
//						.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));
//				System.out.println(verifiedAuth.getClass());// Custom user details
//				// => auth success
//				return ResponseEntity
//						.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
//				// return ResponseEntity.ok(ownerDto);
//			} else
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//		} else if (reqDTO.getRole().equalsIgnoreCase("driver")) {
//			DriverRespDTO driverDto = driverService.doLogin(reqDTO);
//			if (driverDto != null) {
//				Authentication verifiedAuth = mgr
//						.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));
//				System.out.println(verifiedAuth.getClass());// Custom user details
//				// => auth success
//				return ResponseEntity
//						.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
//				// return ResponseEntity.ok(driverDto);
//			} else
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
//		} else
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
//	}

//	// add REST api end point user reg
//	// URL : http://host:port/users/signup
//	// payload : sign up dto
//	// resp : sign up dto
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerNewUser(@RequestBody @Valid Signup request) {
//		System.out.println("in user signup " + request);
//		// invoke service method
//		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(request));
//	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateLogin(@RequestBody @Valid AuthSignInDTO reqDTO) {
		System.out.println("in signin " + reqDTO);

		String role = reqDTO.getRole().toLowerCase();
		ResponseEntity<?> response = authenticateAndGenerateToken(reqDTO, role);

		if (response != null) {
			return response;
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
		}
	}

	private ResponseEntity<?> authenticateAndGenerateToken(AuthSignInDTO reqDTO, String role) {
		Object responseDto = null;
		switch (role) {
		case "admin":
			responseDto = adminService.doLogin(reqDTO);
			break;
		case "customer":
			responseDto = custService.doLogin(reqDTO);
			break;
		case "owner":
			responseDto = ownerService.doLogin(reqDTO);
			break;
		case "driver":
			responseDto = driverService.doLogin(reqDTO);
			break;
		}
		System.out.println("auth " + responseDto);
		if (responseDto != null) {
			System.out.println("hi");
			Authentication verifiedAuth = mgr.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(),
					reqDTO.getPassword(), AuthorityUtils.createAuthorityList(reqDTO.getRole())));
			System.out.println(verifiedAuth.getClass()); // Custom user details
			// => auth success
			return ResponseEntity
					.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
			// return ResponseEntity.ok(responseDto);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}
	}

}
