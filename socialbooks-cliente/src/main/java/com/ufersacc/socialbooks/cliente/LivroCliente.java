package com.ufersacc.socialbooks.cliente;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ufersacc.socialbooks.cliente.domain.Livro;

public class LivroCliente {
	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/livros";
	
	private String credencial;
	
	public LivroCliente(String url, String usuario, String senha) {
		
		restTemplate = new RestTemplate();
		
		URI_BASE = url.concat(URN_BASE);
		
		String credenciaAux = usuario + ":" + senha;
		
		credencial = "Basic " + Base64.getEncoder().encodeToString(credenciaAux.getBytes());
		
		
		
	}
	public List<Livro> listar(){
		
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(URI_BASE))
				.header("Authorization", credencial).build();
		
		
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);
		return Arrays.asList(response.getBody());
	}

	public String salvar(Livro livro) {
		
		RequestEntity<Livro> request = RequestEntity
				.post(URI.create(URI_BASE))
				.header("Authorization", credencial)
				.body(livro);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri) {
		
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(uri))
				.header("Authorization", credencial)
				.build();
		
		ResponseEntity<Livro> response =  restTemplate.exchange(request, Livro.class);
		return response.getBody();
	}
}
