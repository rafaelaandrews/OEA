package trabalho_oea;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	//@SuppressWarnings({ "resource", "unused" })
	
	public static void main(String[] args){	
		
		try {
		Scanner teclado = new Scanner(System.in);
		RandomAccessFile arquivo = new RandomAccessFile("cep_ordenado.dat", "rw");
		Endereco endereco = new Endereco();
		
		String cep_escolhido, cep, auxiliar;    	
		long inicio = 0;	
		long fim = (arquivo.length() / endereco.tamanho_linha())-1;
		long meio=0, achou=0;

		System.out.print("Digite o CEP: ");
		cep_escolhido = teclado.nextLine();
		cep = cep_escolhido;
		
		//Busca Binaria
		while (inicio <= fim){
			meio = (inicio + fim) / 2;
			arquivo.seek(meio * endereco.tamanho_linha());
			endereco.leEndereco(arquivo);
			//DEBUG \/
			//System.out.println("CEP que foi encontrado encontrado: " + endereco.getCep());
			auxiliar = endereco.getCep();
			//System.out.println("Auxiliar: "+auxiliar);

			if (auxiliar.compareTo(cep) < 0){
				inicio = meio + 1;
			} else{
			         if (auxiliar.compareTo(cep) > 0){
				        fim = meio - 1;
			        } else {
				        achou = meio;
				        System.out.println("CEP Encontrado\n\nEndereco completo:\n");
				        break;
			        } 
		        }
		}
		if(achou==meio){

		//Imprimindo os Valores encontrados
		System.out.println("Logradouro: " + endereco.getLogradouro());
		System.out.println("Bairro: " + endereco.getBairro());
		System.out.println("Cidade: " + endereco.getCidade());
		System.out.println("Estado: " + endereco.getEstado());
		System.out.println("Sigla: " + endereco.getSigla());
		System.out.println("CEP: " + endereco.getCep());
		arquivo.close();
	        }else{
	                System.out.println("Cep "+cep_escolhido+" nao foi encontrado");
	        }
	        
		}catch(IOException erro) {
			System.err.println(erro.getMessage());			
		}
	}
}
