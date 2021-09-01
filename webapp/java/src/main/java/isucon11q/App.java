package isucon11q;

import isucon11q.model.Isu;
import isucon11q.repository.IsuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	IsuRepository isuRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Isu> hello() {
		List<Isu> isuList = isuRepository.findById(1);
		return isuList;
	}
}
