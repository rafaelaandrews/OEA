import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Principal {

	public static String cep_escolhido, auxiliar;    	
	public static long inicio = 0;	
	public static long fim;
	public static long meio=0;
	public static RandomAccessFile arquivo;
	public static int contador = 0;
	
	public static long buscaBinaria(Endereco endereco) throws Exception{
		fim = (arquivo.length() / endereco.tamanho_linha())-1;
		while (inicio <= fim){
			meio = (inicio + fim) / 2;
			arquivo.seek(meio * endereco.tamanho_linha());
			endereco.leEndereco(arquivo);
			//DEBUG \/
			//System.out.println("CEP que foi encontrado encontrado: " + endereco.getCep());
			auxiliar = endereco.getCep();
			//System.out.println("Auxiliar: "+auxiliar);
			if(auxiliar.compareTo(cep_escolhido) < 0){
				inicio = meio + 1;
			}else{
			         if (auxiliar.compareTo(cep_escolhido) > 0){
				        fim = meio - 1;
			        }else{
					return meio;				        
			        } 
		        }
			contador++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception{	
		
		try {
			Scanner teclado = new Scanner(System.in);
			arquivo = new RandomAccessFile("cep_ordenado.dat", "rw");
			Endereco endereco = new Endereco();
		
			System.out.print("Digite o CEP: ");
			cep_escolhido = teclado.nextLine();
			fim = 0;
		
			long linha = buscaBinaria(endereco);
			System.out.println("Foi realizada a iteração " + contador + " vezes");
				
			if(linha>0){
				arquivo.seek(linha*300);
				endereco.leEndereco(arquivo);

				//Imprimindo os Valores encontrados
				System.out.println("CEP Encontrado\n\nEndereco completo:\n");
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
