package com.example.democode.domain.membership.service;

import com.example.democode.domain.membership.dto.MembershipAddResponse;
import com.example.democode.domain.membership.dto.MembershipDetailResponse;
import com.example.democode.domain.membership.entity.Membership;
import com.example.democode.domain.membership.exception.MembershipException;
import com.example.democode.domain.membership.model.MembershipErrorResult;
import com.example.democode.domain.membership.model.MembershipType;
import com.example.democode.domain.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MembershipService {

    private final PointService ratePointService;
    private final MembershipRepository membershipRepository;

//    @RequiredArgsConstructor 추가로 생성자 주입을 위한 아래 코드 주석 처리
//    public MembershipService(MembershipRepository membershipRepository) {
//        this.membershipRepository = membershipRepository;
//    }

    /**
     * 멤버쉽 추가
     * @param userId 사용자 ID
     * @param membershipType 멤버쉽 타입
     * @param point 포인트
     * @return 결과값
     */
    @Transactional
    public MembershipAddResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();

        final Membership savedMembership = membershipRepository.save(membership);

        return MembershipAddResponse.builder()
                .id(savedMembership.getId())
                .membershipType(savedMembership.getMembershipType())
                .build();
    }

    /**
     * 멤버쉽 목록 가져오기
     * @param userId 사용자 ID
     * @return 결과값
     */
    public List<Membership> getMembershipList(String userId) {

        final List<Membership> membershipList = membershipRepository.findAllByUserId(userId);
        return membershipList.stream()
                .map(v -> Membership.builder()
                        .id(v.getId())
                        .membershipType(v.getMembershipType())
                        .point(v.getPoint())
                        .createdAt(v.getCreatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    /**
     * 멤버쉽 상세 정보
     * @param membershipId 멤버쉽 고유번호
     * @param userId 사용자 ID
     * @return 상세 정보
     */
    public MembershipDetailResponse getMembership(final Long membershipId, final String userId) {
        final Optional<Membership> optionalMembership = membershipRepository.findById(membershipId);
        final Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));

        if (!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        return MembershipDetailResponse.builder()
                .id(membership.getId())
                .membershipType(membership.getMembershipType())
                .point(membership.getPoint())
                .createdAt(membership.getCreatedAt())
                .build();
    }

    /**
     * 멤버쉽 삭제
     * @param membershipId 멤버쉽 고유번호
     * @param userId 사용자 ID
     */
    @Transactional
    public void removeMembership(Long membershipId, String userId) {
        final Optional<Membership> optionalMembership = membershipRepository.findById(membershipId);
        final Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));

        if (!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        membershipRepository.deleteById(membershipId);
    }

    /**
     * 멤버쉽 포인트 적립
     * @param membershipId 멤버쉽 고유번호
     * @param userId 사용자 ID
     * @param amount 포인트 금액
     */
    @Transactional
    public void accumulateMembershipPoint(final Long membershipId, final String userId, final int amount) {
        final Optional<Membership> optionalMembership = membershipRepository.findById(membershipId);
        final Membership membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));

        if (!membership.getUserId().equals(userId)) {
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }

        final int additionalAmount = ratePointService.calculateAmount(amount);

        membership.setPoint(membership.getPoint() + additionalAmount);
    }
}
