package kettlebell.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import static kettlebell.utils.HibernateRunner.getInstanceSession;

import javax.persistence.Query;

import org.hibernate.Session;

public class LoadingBasicDataService {
	private Session session;
	
	public void loadingBasicData() {

		session = getInstanceSession();
		try {
			session.getTransaction().begin();
			// formatter:off
			String sql = new String(Files.readAllBytes(Paths.get(Objects
					.requireNonNull(LoadingBasicDataService.class.getClassLoader().getResource("data.sql")).toURI())));
			// formatter:on
			Query query = session.createNativeQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (IOException | URISyntaxException e) {
			new RuntimeException(e);
		}

		
	}
}
