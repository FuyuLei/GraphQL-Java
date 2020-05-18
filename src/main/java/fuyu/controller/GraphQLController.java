package fuyu.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fuyu.util.GraphQLUtil;

@Controller
public class GraphQLController {
	@Autowired
	GraphQLUtil graphQLUtil;

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/data", method = { RequestMethod.POST })
	public ResponseEntity<?> getGraphQLData(@RequestBody Map<String, Object> body) throws IOException {
		String query = (String) body.get("query");
		Map<String, Object> result = graphQLUtil.graphQLTest(query);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
