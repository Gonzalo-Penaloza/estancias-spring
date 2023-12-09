package com.tadd.estancias.service;

import com.tadd.estancias.enums.Rol;
import com.tadd.estancias.model.Usuario;
import com.tadd.estancias.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Taddeu's
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
	}

	@Override
	public void save(String alias, String email, String clave, String clave2, String imagen) throws Exception {
		if (!clave.equals(clave2))
			throw new Exception("Las contraseñas no coinciden!");

		Usuario usuario = new Usuario();

		usuario.setAlias(alias);
		usuario.setEmail(email);
		usuario.setClave(new BCryptPasswordEncoder().encode(clave));
		usuario.setFechaAlta(new Date());	
		usuario.setImage(imagen);
		usuario.setRol(Rol.USER);

		usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> getById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> getByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUser = usuarioRepository.findByEmail(username);
		
		if(optUser.isEmpty()) throw new UsernameNotFoundException("Usuario no encontrado");
		
		Usuario logged = optUser.get();

		List<GrantedAuthority> access = new ArrayList<>();

		GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + logged.getRol().toString());

		access.add(p);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

		HttpSession session = attr.getRequest().getSession(true);

		session.setAttribute("userSession", logged);

		return new User(logged.getEmail(), logged.getClave(), access);
	}

}
