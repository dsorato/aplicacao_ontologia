package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Preferences {
      private String _risco, _prazo, _tolerancia, _area, _idade, _salario, _opcao, _rendaFixa;
      public Preferences(String risco, String prazo, String tolerancia,
              String area, String  idade, String  salario, String opcao, String rendaFixa){
       this._area = area.toLowerCase();
       this._idade = idade.toLowerCase();
       this._prazo = prazo.toLowerCase();
       this._risco = risco.toLowerCase();
       this._salario = salario.toLowerCase();
       this._opcao = opcao.toLowerCase();
       this._tolerancia = tolerancia.toLowerCase();
       this._rendaFixa = rendaFixa.toLowerCase();
       
          
      }
      public static void main(String [] args) throws IOException, Exception{
          Preferences pf = new Preferences("alto", "longo", "alta", "bolsa_de_valores", "17", "10000", "casa", "sim");
          pf.createAllPropertiesForInstance();
      }
      
      public void createAllPropertiesForInstance() throws OWLOntologyStorageException, IOException, Exception{
          String path = "/home/rr/NetBeansProjects/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl";
          OntologyManager om = new OntologyManager();
          try {
              System.out.println("area "+_area); //mudar áreas
              System.out.println("idade "+_idade);
              System.out.println("prazo "+_prazo);
              System.out.println("risco "+_risco);
              System.out.println("tolerancia "+_tolerancia);
              System.out.println(defineFaixaEtaria());
              System.out.println(defineRendaFixa());
              System.out.println(defineRetorno());
              System.out.println(defineDinheiro());
              
              om.loadOntology(path);
              om.createReasoner();
              om.createPropertyAssertions("faixa_etaria", "i3", defineFaixaEtaria());
              om.createPropertyAssertions("tem_conhecimento_previo", "i3", "Sem_Conhecimento");
              
              om.createDataProperty("tem_risco", "i3", _risco, OWL2Datatype.XSD_STRING);
              om.createDataProperty("tolerancia_risco", "i3", _tolerancia, OWL2Datatype.XSD_STRING);
              om.createDataProperty("tem_prazo", "i3", _prazo, OWL2Datatype.XSD_STRING);
              om.createDataProperty("tem_retorno", "i3", defineRetorno(), OWL2Datatype.XSD_STRING);
              om.createDataProperty(defineDinheiro(), "i3", _salario, OWL2Datatype.XSD_DOUBLE);
              
              om.showClassAfterReasoning("i3");
              //om.showInstancesDataProperty();
              //om.showInstancesProperties();
              om.saveOntology();
              
          } catch (OWLOntologyCreationException ex) {
              Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          
      }
      public String defineFaixaEtaria(){
          int idade = Integer.parseInt(_idade);
          if(idade < 20){
              
              return "jovem";
          }else{
              if(idade >= 20 && idade < 59){
                  return "adulto";
              }
          }
          return "idoso";
      }
      public String defineRendaFixa(){
          if(_rendaFixa.equals("sim")){
              return "true";
          }
          return "false";
      }
      
      public String defineDinheiro(){
          double valor = Double.parseDouble(_salario);
          if(valor  < 5000){
              return "tem_pouco_dinheiro";
          }
          if(valor >= 5000 && valor < 10000){
              return "tem_dinheiro_razoavel";
          }
          return "tem_muito_dinheiro";
      }
      
      public String defineRetorno(){
          if(_opcao.equals("carro") || _opcao.equals("viagem nacional")){
              return "pequeno";
          }
          if(_opcao.equals("casa")){
              return "grande";
          }
          return "medio";
      }

}
