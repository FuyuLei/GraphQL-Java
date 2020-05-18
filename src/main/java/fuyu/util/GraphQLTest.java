package fuyu.util;

import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;

public class GraphQLTest {
	static GraphQLDataFetchers graphQLDataFetchers = new GraphQLDataFetchers();

	public static void main(String[] args) throws IOException {
		URL url = Resources.getResource("schema.graphqls");
		String schema = Resources.toString(url, Charsets.UTF_8);

		GraphQLSchema graphQLSchema = buildSchema(schema);
		GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();

		ExecutionResult executionResult = build.execute("{ post(id: \"3\") { title content likeGivers { name } } }");
		System.out.println(executionResult.getData().toString());
		List<GraphQLError> errors = executionResult.getErrors();
		System.out.println("errors: " + errors);
	}

	private static GraphQLSchema buildSchema(String schema) {
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
	}

	public static RuntimeWiring buildWiring() {
		RuntimeWiring runtimeWiring = newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("hello", new StaticDataFetcher("world"))
						.dataFetcher("me", graphQLDataFetchers.getFirstUser())
						.dataFetcher("user", graphQLDataFetchers.getUserById())
						.dataFetcher("users", graphQLDataFetchers.getAllUsers())
						.dataFetcher("post", graphQLDataFetchers.getPostById())
						.dataFetcher("posts", graphQLDataFetchers.getAllPosts()))
				.type("User", typeWiring -> typeWiring
						.dataFetcher("friends", graphQLDataFetchers.getUserByFriends()))
				.type("Post", typeWiring -> typeWiring
						.dataFetcher("author", graphQLDataFetchers.getUserByPostAuthor())
						.dataFetcher("likeGivers", graphQLDataFetchers.getUserByPostLikeGivers()))
				.build();
		return runtimeWiring;
	}
}