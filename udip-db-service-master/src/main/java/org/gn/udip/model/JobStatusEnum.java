package org.gn.udip.model;

import org.gn.udip.exception.ValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;



public enum JobStatusEnum {
	
		FETCHER_INITIALIZED("FETCHER INITIALIZED"),
		FETCHER_RUNNING("FETCHER RUNNING"),
		FETCHER_COMPLETED("FETCHER COMPLETED"),
		FETCHER_FAILED("FETCHER FAILED"),
		PARSER_INITIALIZED("PARSER INITIALIZED"),
		PARSER_COMPLETED("PARSER COMPLETED"),
		PARSER_RUNNING("PARSER RUNNING"),
		PARSER_FAILED("PARSER FAILED");
		
		
		private String statusValue;

		private JobStatusEnum(String value) {
			this.statusValue = value;
		}

		@JsonCreator
		public static JobStatusEnum fromValue(String value) throws Exception {
			for (JobStatusEnum jsonStatus : values()) {
				if (jsonStatus.statusValue.equalsIgnoreCase(value)) {
					return jsonStatus;
				}
			}
			throw new ValidationException(
					"Unknown JobStatus type " + value );
		}
		
		@Override
		public String toString() {
			return statusValue;
		}
		
}
