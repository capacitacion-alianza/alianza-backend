package co.com.alianza.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.alianza.entities.UsuarioEntity;
import co.com.alianza.repository.UsuarioRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioEntity> usuarioEntity = null;
        if (repo.findByUsername(username).isPresent() || repo.findByEmail(username).isPresent()) {
            usuarioEntity = repo.existsByEmail(username) ? repo.findByEmail(username) : repo.findByUsername(username);
            new AccountStatusUserDetailsChecker().check(usuarioEntity.get());
            return usuarioEntity.get();
        }
        throw new BadCredentialsException("Usuario y/o contraseña incorrecto");
	}

}
