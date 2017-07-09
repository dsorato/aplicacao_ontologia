package model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Preferences {
      private String _risco, _prazo, _tolerancia, _area, _idade, _salario, _opcao;
      public Preferences(String risco, String prazo, String tolerancia,
              String area, String  idade, String  salario, String opcao){
       this._area = area.toLowerCase();
       this._idade = idade.toLowerCase();
       this._prazo = prazo.toLowerCase();
       this._risco = risco.toLowerCase();
       this._salario = salario.toLowerCase();
       this._opcao = opcao.toLowerCase();
       this._tolerancia = tolerancia.toLowerCase();
       
          
      }
      
      public void createAllPropertiesForInstance() throws OWLOntologyStorageException, IOException, Exception{
          String path = "/home/rr/NetBeansProjects/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl";
          OntologyManager om = new OntologyManager();
          try {
              System.out.println(_area);
              System.out.println(_idade);
              System.out.println(_prazo);
              System.out.println(_risco);
              System.out.println(_tolerancia);
              om.loadOntology(path);
              om.createReasoner();
              om.createPropertyAssertions("tem_conhecimento_previo", "i1", _area);
              om.createDataProperty("tolerancia_risco", "i1", _tolerancia, OWL2Datatype.XSD_STRING);
              om.showInstancesDataProperty();
              om.saveOntology();
              
          } catch (OWLOntologyCreationException ex) {
              Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          
      }

}
