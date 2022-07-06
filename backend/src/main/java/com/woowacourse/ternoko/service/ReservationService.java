package com.woowacourse.ternoko.service;

import com.woowacourse.ternoko.domain.FormItem;
import com.woowacourse.ternoko.domain.Interview;
import com.woowacourse.ternoko.domain.Location;
import com.woowacourse.ternoko.domain.Member;
import com.woowacourse.ternoko.domain.Reservation;
import com.woowacourse.ternoko.dto.FormItemRequest;
import com.woowacourse.ternoko.dto.ReservationRequest;
import com.woowacourse.ternoko.repository.FormItemRepository;
import com.woowacourse.ternoko.repository.InterviewRepository;
import com.woowacourse.ternoko.repository.MemberRepository;
import com.woowacourse.ternoko.repository.ReservationRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final FormItemRepository formItemRepository;
    private final ReservationRepository reservationRepository;
    private final InterviewRepository interviewRepository;

    public Long create(Long coachId, ReservationRequest reservationRequest) {
        final List<FormItemRequest> interviewQuestions = reservationRequest.getInterviewQuestions();

        final Interview interview = convertInterview(coachId, reservationRequest,
                interviewQuestions);

        interviewRepository.save(interview);

        return reservationRepository.save(new Reservation(interview, false)).getId();
    }

    private Interview convertInterview(Long coachId, ReservationRequest reservationRequest,
                                       List<FormItemRequest> interviewQuestions) {
        final List<FormItem> formItems = convertFormItem(interviewQuestions);

        final LocalDateTime reservationDatetime = reservationRequest.getReservationDatetime();

        final Member coach = memberRepository.findById(coachId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 코치를 찾을 수 없습니다."));

        return new Interview(reservationDatetime.toLocalDate(),
                reservationDatetime.toLocalTime(),
                reservationDatetime.toLocalTime().plusMinutes(30),
                coach,
                reservationRequest.getCrewNickname(),
                Location.of(reservationRequest.getLocation()),
                formItems);
    }

    private List<FormItem> convertFormItem(List<FormItemRequest> interviewQuestions) {
        final List<FormItem> formItems = interviewQuestions.stream()
                .map(FormItemRequest::toFormItem)
                .collect(Collectors.toList());

        formItemRepository.saveAll(formItems);
        return formItems;
    }
}
