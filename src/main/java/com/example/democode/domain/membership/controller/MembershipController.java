package com.example.democode.domain.membership.controller;

import com.example.democode.domain.membership.dto.MembershipAddResponse;
import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.dto.MembershipRequest;
import com.example.democode.domain.membership.service.MembershipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.democode.domain.membership.model.MembershipConstants.USER_ID_HEADER;

@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/api/v1/memberships")
    public ResponseEntity<MembershipAddResponse> addMembership(
            @RequestHeader(USER_ID_HEADER) final String userId,
            @RequestBody @Valid final MembershipRequest membershipRequest) {

        final MembershipAddResponse membershipResponse =
                membershipService.addMembership(userId, membershipRequest.getMembershipType(), membershipRequest.getPoint());

        return ResponseEntity.status(HttpStatus.CREATED).body(membershipResponse);
    }

    @GetMapping("/api/v1/memberships")
    public ResponseEntity<List<MembershipDetailResponse>> getMemberships(@RequestHeader(USER_ID_HEADER) final String userId) {
        return ResponseEntity.ok(membershipService.getMembershipList(userId));
    }
}