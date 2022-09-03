package com.woowacourse.ternoko.comment.application;

import static com.woowacourse.ternoko.common.exception.type.ExceptionType.COMMENT_NOT_FOUND;
import static com.woowacourse.ternoko.common.exception.type.ExceptionType.INTERVIEW_NOT_FOUND;
import static com.woowacourse.ternoko.common.exception.type.ExceptionType.INVALID_STATUS_FIND_COMMENT;

import com.woowacourse.ternoko.comment.domain.Comment;
import com.woowacourse.ternoko.comment.dto.CommentRequest;
import com.woowacourse.ternoko.comment.dto.CommentsResponse;
import com.woowacourse.ternoko.comment.exception.CommentNotFoundException;
import com.woowacourse.ternoko.comment.exception.InvalidStatusFindCommentException;
import com.woowacourse.ternoko.comment.repository.CommentRepository;
import com.woowacourse.ternoko.domain.member.MemberType;
import com.woowacourse.ternoko.interview.domain.Interview;
import com.woowacourse.ternoko.interview.domain.InterviewRepository;
import com.woowacourse.ternoko.interview.exception.InterviewNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {

    private final InterviewRepository interviewRepository;
    private final CommentRepository commentRepository;

    public Long create(final Long memberId, final Long interviewId, final CommentRequest commentRequest) {
        final Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewNotFoundException(INTERVIEW_NOT_FOUND, interviewId));

        final MemberType memberType = interview.findMemberType(memberId);
        final Comment comment = commentRepository.save(
                Comment.create(memberId, interview, commentRequest.getComment(), memberType));

        interview.complete(memberType);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public CommentsResponse findComments(final Long memberId, final Long interviewId) {
        final Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new InterviewNotFoundException(INTERVIEW_NOT_FOUND, interviewId));
        interview.findMemberType(memberId);
        validateFindStatus(interview);
        final List<Comment> comments = commentRepository.findByInterviewId(interviewId);
        return CommentsResponse.of(comments, interview);
    }

    private void validateFindStatus(final Interview interview) {
        if (!interview.canFindCommentBy()) {
            throw new InvalidStatusFindCommentException(INVALID_STATUS_FIND_COMMENT);
        }
    }

    public void update(final Long memberId,
                       final Long interviewId,
                       final Long commentId,
                       final CommentRequest commentRequest) {
        final Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND, commentId));
        comment.update(memberId, interviewId, commentRequest.getComment());
    }
}
