package guru.springframework.msscbrewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// IL PROGETTO RICHIEDE CHE NEI PLUGIN
// DI INTELLJ SIANO INSTALLATI lombok(oramai di default in spring)
// e il supporto MapStruct
// IL BRANCH MASTER ARRIVA FINO ALLA LEZIONE 76
// DEL REPOSITORY brewery-repo
@SpringBootApplication
public class MsscBreweryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscBreweryApplication.class, args);
	}

}
