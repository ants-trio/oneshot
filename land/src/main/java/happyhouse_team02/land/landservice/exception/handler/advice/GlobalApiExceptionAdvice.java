package happyhouse_team02.land.landservice.exception.handler.advice;

import static happyhouse_team02.land.landservice.exception.ExceptionMessage.*;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

import happyhouse_team02.land.landservice.exception.DuplicatedBookmarkException;
import happyhouse_team02.land.landservice.exception.DuplicatedMemberException;
import happyhouse_team02.land.landservice.exception.NoLoginException;
import happyhouse_team02.land.landservice.exception.NoSuchBookmarkException;
import happyhouse_team02.land.landservice.exception.NoSuchCommentException;
import happyhouse_team02.land.landservice.exception.NoSuchMemberException;
import happyhouse_team02.land.landservice.exception.NoSuchPostException;
import happyhouse_team02.land.landservice.exception.PasswordDoesNotMatchException;
import happyhouse_team02.land.landservice.exception.UnauthorizedAccessException;
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

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult illegalStateExceptionHandler(IllegalStateException e) {
		log.error("[illegalStateExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, ILLEGAL_MESSAGE);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult notValidExceptionHandler(JsonProcessingException e) {
		log.error("[notValidExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, "[ERROR] 잘못된 입력입니다.");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult notValidExceptionHandler(HttpMessageConversionException e) {
		log.error("[notValidExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, "[ERROR] 잘못된 입력입니다.");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult typeMismatchExceptionHandler(TypeMismatchException e) {
		log.error("[typeMismatchExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, "[ERROR] 잘못된 입력입니다.");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult servletRequestBindingExceptionHandler(ServletRequestBindingException e) {
		log.error("[servletRequestBindingExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, "[ERROR] 잘못된 입력입니다.");
	}


	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailResponseResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		log.error("[notValidExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, "[ERROR] 해당 메서드는 지원하지 않습니다.");
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
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public FailResponseResult noLoginExceptionHandler(NoLoginException e){
		log.error("[noLoginExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public FailResponseResult unauthorizedAccessExceptionHandler(UnauthorizedAccessException e){
		log.error("[noLoginExceptionHandler] ex", e);
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
	public FailResponseResult duplicatedBookmarkExceptionHandler(DuplicatedBookmarkException e) {
		log.error("[duplicatedBookmarkExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	/**
	 * PostExceptionHandler
	 */
	@ExceptionHandler
	public FailResponseResult noSuchPostExceptionHandler(NoSuchPostException e){
		log.error("[noSuchPostExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}

	/**
	 * CommentExceptionHandler
	 */
	@ExceptionHandler
	public FailResponseResult noSuchCommentExceptionHandler(NoSuchCommentException e){
		log.error("[noSuchCommentExceptionHandler] ex", e);
		return new FailResponseResult(FAIL, e.getMessage());
	}
}
