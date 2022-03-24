package happyhouse_team02.land.landservice.exception.handler.advice;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import happyhouse_team02.land.landservice.exception.DuplicatedMemberException;
import happyhouse_team02.land.landservice.exception.NoSuchBookmarkException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.exception.PasswordDoesNotMatchException;
import happyhouse_team02.land.landservice.exception.handler.FailResponseResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalApiExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public FailResponseResult exceptionHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		return new FailResponseResult(FAIL, GLOBAL_MESSAGE);
	}

	/**
	 * MemberExceptionHandler
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public FailResponseResult noSuchMemberExceptionHandler(NoSuchMemberException e) {
		log.error("[noSuchMemberExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	@ExceptionHandler
	public FailResponseResult duplicatedMemberExceptionHandler(DuplicatedMemberException e) {
		log.error("[DuplicatedMemberException] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	@ExceptionHandler
	public FailResponseResult passwordDoesNotMatchExceptionHandler(PasswordDoesNotMatchException e) {
		log.error("[passwordDoesNotMatchException] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	/**
	 * BookmarkExceptionHandler
	 */
	@ExceptionHandler
	public FailResponseResult noSuchBookmarkExceptionHandler(NoSuchBookmarkException e) {
		log.error("[noSuchBookmarkExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	@ExceptionHandler
	public FailResponseResult duplicatedBookmarkExceptionHandler(DuplicatedBookmarkException e){
		log.error("[duplicatedBookmarkExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}
}
