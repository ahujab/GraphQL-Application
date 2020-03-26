package com.example.GraphQLDemo;
import com.example.GraphQLDemo.exception.GraphQLErrorAdapter;
import com.example.GraphQLDemo.model.Artist;
import com.example.GraphQLDemo.model.Song;
import com.example.GraphQLDemo.repository.ArtistRepository;
import com.example.GraphQLDemo.repository.SongRepository;
import com.example.GraphQLDemo.resolver.SongResolver;
import com.example.GraphQLDemo.resolver.Mutation;
import com.example.GraphQLDemo.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GraphQlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphQlDemoApplication.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public SongResolver artistResolver(ArtistRepository artistRepository) {
		return new SongResolver(artistRepository);
	}

	@Bean
	public Query query(ArtistRepository artistRepository,SongRepository songRepository ) {
		return new Query(songRepository, artistRepository);
	}

	@Bean
	public Mutation mutation(ArtistRepository artistRepository, SongRepository songRepository) {
		return new Mutation(artistRepository, songRepository);
	}

	@Bean
	public CommandLineRunner demo(ArtistRepository artistRepository, SongRepository songRepository) {
		return (args) -> {
			Artist artist = new Artist("Herbert", "China");
			artistRepository.save(artist);

			songRepository.save(new Song("Rockon", "rock", 728, artist));
		};
	}
}
