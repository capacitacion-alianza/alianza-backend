package co.com.alianza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.com.alianza.entities.UserEntity;
import co.com.alianza.entities.UsuarioEntity;
import co.com.alianza.repository.UsuarioRepository;

@SpringBootApplication
public class BackendAlianzaApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder encoder;
//	
//	@Autowired
//	private UsuarioRepository usuarioReository;
	
    public static void main(String[] args) {
        SpringApplication.run(BackendAlianzaApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		String encode = encoder.encode("alianza");
		System.out.println(encode);
//
//		UsuarioEntity u = new UsuarioEntity();
//		u.setUsername("admin");
//		u.setPassword(encoder.encode("admin"));
//		u.setAccountNonExpired(false);
//		u.setAccountNonLocked(false);
//		u.setEnabled(true);
//		u.setCredentialsNonExpired(false);
//		
//		
//		UsuarioEntity guardado = usuarioReository.save(u);		
//		
	}

}
