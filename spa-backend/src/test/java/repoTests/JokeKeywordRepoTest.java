package repoTests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.bigshots.spabackend.SpaBackendApplication;
import com.bigshots.spabackend.repo.JokeKeywordRepo;
import static org.junit.Assert.assertTrue;
 
@RunWith(SpringRunner.class) 
@SpringBootTest(classes=SpaBackendApplication.class)
public class JokeKeywordRepoTest {
	@Autowired
	private JokeKeywordRepo repoTest;
	
	@Test
	void checkIfKeywordExists() {
		//checking repo by a known id
		boolean expected = repoTest.existsById("97") != null;
		//asserting that it exists and returns a keyword
		assertTrue(expected);
	}

}
