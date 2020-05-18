package fuyu.util;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLUtil {
	@Autowired
	GraphQLDataFetchers graphQLDataFetchers;

	public Map<String, Object> graphQLTest(String query) throws IOException {
		URL url = Resources.getResource("schema.graphqls");
		String schema = Resources.toString(url, Charsets.UTF_8);

		GraphQLSchema graphQLSchema = buildSchema(schema);
		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();

		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
		ExecutionResult executionResult = build.execute(executionInput);
		System.out.println(executionResult.getData().toString());
		
		Map<String, Object> toSpecificationResult = executionResult.toSpecification();
		return toSpecificationResult;
	}

	private GraphQLSchema buildSchema(String schema) {
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
	}

	private RuntimeWiring buildWiring() {
		RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type("Query",
						typeWiring -> typeWiring.dataFetcher("hello", new StaticDataFetcher("world"))
								.dataFetcher("me", graphQLDataFetchers.getFirstUser())
								.dataFetcher("user", graphQLDataFetchers.getUserById())
								.dataFetcher("users", graphQLDataFetchers.getAllUsers())
								.dataFetcher("post", graphQLDataFetchers.getPostById())
								.dataFetcher("posts", graphQLDataFetchers.getAllPosts()))
				.type("User", typeWiring -> typeWiring.dataFetcher("friends", graphQLDataFetchers.getUserByFriends()))
				.type("Post", typeWiring -> typeWiring.dataFetcher("author", graphQLDataFetchers.getUserByPostAuthor())
						.dataFetcher("likeGivers", graphQLDataFetchers.getUserByPostLikeGivers()))
				.build();
		return runtimeWiring;
	}
}
