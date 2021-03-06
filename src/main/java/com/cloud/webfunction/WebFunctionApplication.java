package com.cloud.webfunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.cloud.webfunction.toll.TollRecord;
import com.cloud.webfunction.toll.TollStation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WebFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFunctionApplication.class, args);
	}

	List<TollStation> tollStations;

	public WebFunctionApplication() {

		tollStations = new ArrayList<TollStation>();
		tollStations.add((new TollStation("100A", 112.5f, 2)));
		tollStations.add((new TollStation("111C", 124f, 4)));
		tollStations.add((new TollStation("112C", 126f, 2)));

	}

	@Bean
	public Function<String,TollStation> retrieveStation()
	{
		return value-> {
			System.out.println("received request for station - " + value);

			return tollStations
				.stream()
				.filter(toll -> toll.getStationId().equals(value))
				.findAny()
				.orElse(null);

		};
	}

	@Bean
	public Consumer<TollRecord> processTollRecord()
	{
		//logic to save the toll
		return value ->	  System.out.println(value);
	}

	@Bean
	public Function<TollRecord, Mono<Void>> processTollRecordReactive ()
    {
        return value ->
        {
            System.out.println("Received reactive toll for car: " + value);
            return Mono.empty();
        };
    }

	@Bean
	public Consumer<Flux<TollRecord>> processListOfTollRecordsReactive()
	{
		return value ->
		{
			value.subscribe(System.out::println);
		};
	}
	
	@Bean
	public Supplier<Flux<TollStation>> getTollStattions()
	{
		tollStations.stream()
					.forEach(System.out::println);

		return () -> Flux.fromIterable(tollStations);
	}


}
