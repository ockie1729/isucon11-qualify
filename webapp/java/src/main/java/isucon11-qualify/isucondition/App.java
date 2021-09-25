package isucondition;

import isucondition.model.Isu;
import isucondition.model.User;
import isucondition.repository.IsuRepository;
import isucondition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	HttpSession session;

	@Autowired
	IsuRepository isuRepository;

	@Autowired
	UserRepository userRepository;

	public class GetMeResponse {

		String jiaUserID;

		public GetMeResponse(String jiaUserID) {
			this.jiaUserID = jiaUserID;
		}

		public String getJiaUserID() {
			return jiaUserID;
		}

		public void setJiaUserID(String jiaUserID) {
			this.jiaUserID = jiaUserID;
		}
	}

	String getUserIdFromSession() {
		Object rawJIAUserID = session.getAttribute("jia_user_id");
		if (rawJIAUserID == null) {
			throw new Unauthorized();
		}
		String jiaUserID = String.class.cast(rawJIAUserID);

		List<User> users = userRepository.findByJIAUserID(jiaUserID);
		if (users.size() == 0) {
			throw new Unauthorized();
		}

		return jiaUserID;
	}

	// POST /api/auth
    // サインアップ・サインイン
	@RequestMapping(value = "/api/auth", method = RequestMethod.POST)
	void postAuthentication() {
		session.setAttribute("jia_user_id", "isucon");  // FIXME
	}

	// GET /api/user/me
    // サインインしている自分自身の情報を取得
	@RequestMapping(value = "/api/user/me", method = RequestMethod.GET)
	GetMeResponse getMe() {
		String jiaUserID = getUserIdFromSession();

		return new GetMeResponse(jiaUserID);
	}

	// GET /
	// tmpなエンドポイント
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Isu> hello() {
		List<Isu> isuList = isuRepository.findById(1);
		return isuList;
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(Unauthorized.class)
	String unauthorized() {
		return "you are not signed in!!!in!! thank you Jessy";
	}

	public static class Unauthorized extends RuntimeException {
	}
}
