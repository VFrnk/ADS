import java.lang.StringBuilder;

public class TemplateSimples {
	String template;

	TemplateSimples(String template) {
		this.template = template;
	}

	String preencher(String nome, int idade, String cidade) {
		StringBuilder saida = new StringBuilder();

		for(int i = 0; i < template.length(); i++) {
			if(template.charAt(i) == '{') {
			    i++;
			    int inicio = i;
			    
			    while(i < template.length() && template.charAt(i) != '}'){
				    i++;
				}
				
				if(i < template.length() && template.charAt(i) == '}'){
    				String[] parametros = template.substring(inicio, i).split("\\|");
    				
    				String campo = parametros[0];
    				String padrao = parametros.length >= 2 ? parametros[1] : null;
				    
    				switch(campo){
    				    case "nome":
    				        if(nome != null){
    				            saida.append(nome);
    				            break;
    				        }
    				        
    				        if(padrao != null){
    				            saida.append(padrao);
    				            break;
    				        }
    				        
    				        saida.append("NULL");
    				        break;
    				        
    				    case "idade":
    				        if(idade > 0){
    				            saida.append(idade);
    				            break;
    				        }
    				        
    				        if(padrao != null){
    				            saida.append(padrao);
    				            break;
    				        }
    				        
    				        saida.append("-1");
    				        break;
    				    case "cidade":
    				        if(cidade != null){
    				            saida.append(cidade);
    				            break;
    				        }
    				        
    				        if(padrao != null){
    				            saida.append(padrao);
    				            break;
    				        }
    				        
    				        saida.append("NULL");
    				        break;
    }
				    
				} else {
				    saida.append(template.substring(inicio, i));
				    i = inicio - 1;
				}
			} else {
				saida.append(template.charAt(i));
			}
		}

		return saida.toString();
	}
	
	public static void main(String[] args) {
		var template = new TemplateSimples("meu nome eh {nome}, tenho {idade|20} anos e moro em {cidade|PHB}");
		System.out.println(template.preencher("joao", 0, null));
	}
}