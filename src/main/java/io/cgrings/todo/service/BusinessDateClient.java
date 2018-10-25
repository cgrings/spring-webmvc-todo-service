package io.cgrings.todo.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "businessDate", url = "${business-date-service}", fallbackFactory = BusinessDateClientFallbackFactory.class)
public interface BusinessDateClient {

	@GetMapping(value = "/enddate/{start}/{duration}")
	LocalDate endDate(@PathVariable("start") @DateTimeFormat(iso = ISO.DATE) final LocalDate start, @PathVariable("duration") final Integer duration);

}

@Component
class BusinessDateClientFallbackFactory implements FallbackFactory<BusinessDateClient> {
	private final Logger logger = LoggerFactory.getLogger(BusinessDateClient.class);

	@Override
	public BusinessDateClient create(Throwable cause) {
		logger.error("Fallback: ", cause);
		return new BusinessDateClient() {
			@Override
			public LocalDate endDate(LocalDate start, Integer duration) {
				return start.plusDays(duration);
			}
		};
	}
	
}