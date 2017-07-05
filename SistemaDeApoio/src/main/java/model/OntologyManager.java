package model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public class OntologyManager {
	private OWLOntologyManager manager;
	private IRI documentIRI;
	private OWLOntology localOntology;
	
	public OntologyManager() {
		
	}

	public void loadOntology() throws OWLOntologyCreationException {
		/*
		 * Since we wont load our ontology from web, we have to load the file
		 * that contains the ontology
		 */
		this.manager = OWLManager.createOWLOntologyManager();
		// TODO: find the current directory and make this path relative
		File file = new File(
				"/home/rr/workspace/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl");
		// loading the local ontology
		this.localOntology = manager.loadOntologyFromOntologyDocument(file);
		
		System.out.println("Loaded ontology: " + localOntology);
		// We can always obtain the location where an ontology was loaded from
		this.documentIRI = manager.getOntologyDocumentIRI(localOntology);
		System.out.println("    from: " + documentIRI);
	}
	
	public void createPropertyAssertions(String proprerty) throws Exception {
        // We can specify the properties of an individual using property
        
        OWLDataFactory factory = manager.getOWLDataFactory();
        
        OWLIndividual investidor = factory.getOWLNamedIndividual(IRI.create(documentIRI+ "#investidor"));
        OWLIndividual banco = factory.getOWLNamedIndividual(IRI.create(documentIRI+ "#bancos"));
        OWLObjectProperty temConhecimento = factory.getOWLObjectProperty(IRI.create(documentIRI+ "#tem_conhecimento_previo"));
        
                
        OWLObjectPropertyAssertionAxiom propertyAssertion = factory.getOWLObjectPropertyAssertionAxiom(temConhecimento, investidor, banco);
        AddAxiom addAxiomChange = new AddAxiom(localOntology, propertyAssertion);
        manager.applyChange(addAxiomChange);
        
        
		OWLClass personClass = factory.getOWLClass(IRI.create(documentIRI+ "#Investidor"));
        OWLClassAssertionAxiom ax = factory.getOWLClassAssertionAxiom(personClass, investidor);
        // Add this axiom to our ontology - with a convenience method
        manager.addAxiom(localOntology, ax);
        
        manager.saveOntology(localOntology, new StreamDocumentTarget(new ByteArrayOutputStream()));
        saveOntology();
}	
	public void saveOntology() throws IOException, OWLOntologyStorageException{
		 File output = File.createTempFile("new_ontology", "owl");
	        IRI documentIRI2 = IRI.create(output);
	        // save in OWL/XML format
	        manager.saveOntology(localOntology, new OWLXMLOntologyFormat(), documentIRI2);
	        // save in RDF/XML
	        manager.saveOntology(localOntology, documentIRI2);
	        // print out the ontology
	        StreamDocumentTarget target = new StreamDocumentTarget(
	                new ByteArrayOutputStream());
	        manager.saveOntology(localOntology, target);
	        // Remove the ontology from the manager
	        manager.removeOntology(localOntology);
	}

	

	public static void main(String[] args) {
		
			OntologyManager manager = new OntologyManager();
			try {
				manager.loadOntology();
				manager.createPropertyAssertions("tem_conhecimento_previo");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
}
