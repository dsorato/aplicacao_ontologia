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
       this._area = area;
       this._idade = idade;
       this._prazo = prazo;
       this._risco = risco;
       this._salario = salario;
       this._opcao = opcao;
       this._tolerancia = tolerancia;
       
          
      }
      
      public void createAllPropertiesForInstance() throws OWLOntologyStorageException, IOException, Exception{
          String path = "/home/rr/NetBeansProjects/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl";
          OntologyManager om = new OntologyManager();
          try {
              System.out.println(_area);
              System.out.println(_idade);
              System.out.println(_prazo);
              System.out.print(_risco);
              om.loadOntology(path);
              om.createReasoner();
              om.createDataProperty("tem_risco", "i1", _risco.toLowerCase(), OWL2Datatype.XSD_STRING);
              om.showInstancesDataProperty();
              
          } catch (OWLOntologyCreationException ex) {
              Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
          
      }

}
