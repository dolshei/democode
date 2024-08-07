package com.example.democode.domain.membership.controller;

import com.example.democode.domain.membership.dto.MembershipRequest;
import com.example.democode.domain.membership.dto.MembershipAddResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.example.democode.domain.membership.model.MembershipConstants.USER_ID_HEADER;

@RestController
public class MembershipController {
    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipAddResponse> addMembership(
            @RequestHeader(USER_ID_HEADER) final String userId,
            @RequestBody @Valid final MembershipRequest membershipRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}