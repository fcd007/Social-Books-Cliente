package com.ufersacc.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ufersacc.socialbooks.cliente.LivroCliente;
import com.ufersacc.socialbooks.cliente.domain.Livro;

public class Aplicacao {
	
	public static void main(String[] args) throws ParseException {
		
		
		LivroCliente cliente = 
				new LivroCliente("http://localhost:8080", "admin","1234");
		
		List<Livro> listarLivros = cliente.listar();
		for (Livro livro: listarLivros) {
			System.out.println("Livro " + livro.getNome());
		}
		
		Livro livro = new Livro();
		livro.setNome("Git Passo A passo");
		livro.setEditora("novatec");
		
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyy");
		livro.setPublicacao(publicacao.parse("09/01/2019"));
		livro.setResumo("Livro do Git para desenvolvedores de APi");
		
		String localizada = cliente.salvar(livro);
		System.out.println("URI do livro salvo: " +  localizada);
		
		Livro livroBuscado = cliente.buscar(localizada);
		
		System.out.println("Livro buscado : " + livroBuscado.getNome());
	}

}
