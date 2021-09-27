package isucon11_qualify.isucondition;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import isucon11_qualify.isucondition.model.Isu;
import isucon11_qualify.isucondition.model.User;
import isucon11_qualify.isucondition.repository.IsuRepository;
import isucon11_qualify.isucondition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public static final String frontEndContentsPath = "../public";

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
	void postAuthentication(@RequestHeader(value="Authorization") String authorization) {
		String reqJwt = authorization.replaceFirst("^Bearer ", "");

		PublicKey jwtSigningKey = init();
		Jws<Claims> jwt = Jwts
				.parserBuilder()
				.setSigningKey(jwtSigningKey)
				.build()
				.parseClaimsJws(reqJwt);

		String jiaUserId = jwt.getBody().get("jia_user_id", String.class);
		if (Objects.isNull(jiaUserId)) {
			throw new BadRequestError();
		}

		userRepository.insert(jiaUserId);

		// set session
		session.setAttribute("jia_user_id", jiaUserId);
	}

	private PublicKey init() {
		String key;
		try {
			key = Files.readString(Paths.get("../ec256-public.pem"));
		} catch(IOException e) {
			throw new InternalServerError();
		}

		String publicKey = key
				.replaceAll("\\r\\n", "")
				.replaceAll("\\n", "")
				.replaceAll("-----BEGIN PUBLIC KEY-----", "")
				.replaceAll("-----END PUBLIC KEY-----", "");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
		PublicKey jwtSigningKey;
		try {
			jwtSigningKey = KeyFactory.getInstance("EC").generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new InternalServerError();
		} catch (InvalidKeySpecException e) {
			throw new ForbiddenError();
		}
		return jwtSigningKey;
	}

	// GET /api/user/me
    // サインインしている自分自身の情報を取得
	@RequestMapping(value = "/api/user/me", method = RequestMethod.GET)
	GetMeResponse getMe() {
		String jiaUserID = getUserIdFromSession();

		return new GetMeResponse(jiaUserID);
	}

	// GET /tmp
	// FIXME tmpなエンドポイント
	@RequestMapping(value = "/tmp", method = RequestMethod.GET)
	List<Isu> hello() {
		List<Isu> isuList = isuRepository.findById(1);
		return isuList;
	}
	
	@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public Resource getIndex() {
		return new FileSystemResource(frontEndContentsPath + "/index.html");
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(Unauthorized.class)
	String unauthorized() {
		return "you are not signed in";
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenError.class)
	String forbidden() {
		return "forbidden";
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestError.class)
	String badRequest() {
		return "bad request";
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerError.class)
	void internalServerError() {
	}

	public static class UnauthorizedError extends RuntimeException {
	}

	public static class ForbiddenError extends RuntimeException {
	}

	public static class BadRequestError extends RuntimeException {
	}

	public static class Unauthorized extends RuntimeException {
	}
}
