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

	public void loadOntology(String path) throws OWLOntologyCreationException {
		/* Since we wont load our ontology from web, we have to load from a file */
		this.manager = OWLManager.createOWLOntologyManager();
		// TODO: find the current directory and make this path relative
		File file = new File(path);
		// loading the local ontology
		this.localOntology = manager.loadOntologyFromOntologyDocument(file);

		System.out.println("Loaded ontology: " + localOntology);
		// We can always obtain the location where an ontology was loaded from
		//this.documentIRI = manager.getOntologyDocumentIRI(localOntology);
		this.documentIRI = IRI.create("http://www.semanticweb.org/root/ontologies/2017/5/untitled-ontology-2");
		
		System.out.println("    from: " + documentIRI);
	}

	public void createPropertyAssertions(String proprerty, String instance1,
			String instance2) throws Exception {
		// We can specify the properties of an individual using property

		OWLDataFactory factory = manager.getOWLDataFactory();

		OWLIndividual inst1 = factory.getOWLNamedIndividual(IRI
				.create(documentIRI + "#" + instance1));
		OWLIndividual inst2 = factory.getOWLNamedIndividual(IRI
				.create(documentIRI + "#" + instance2));
		OWLObjectProperty temConhecimento = factory.getOWLObjectProperty(IRI
				.create(documentIRI + "#" + proprerty));

		OWLObjectPropertyAssertionAxiom propertyAssertion = factory
				.getOWLObjectPropertyAssertionAxiom(temConhecimento,
						inst1, inst2);
		AddAxiom addAxiomChange = new AddAxiom(localOntology, propertyAssertion);
		manager.applyChange(addAxiomChange);

		/*isnt required to define a class that a instance belongs, the reasoner should do this*/
		manager.saveOntology(localOntology, new StreamDocumentTarget(
				new ByteArrayOutputStream()));
		saveOntology();
	}

	public void saveOntology() throws IOException, OWLOntologyStorageException {
		File output = File.createTempFile("new_ontology", "owl");
		IRI documentIRI2 = IRI.create(output);
		// save in OWL/XML format
		manager.saveOntology(localOntology, new OWLXMLOntologyFormat(),
				documentIRI2);
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
			manager.loadOntology("/home/rr/workspace/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl");
			manager.createPropertyAssertions("tem_conhecimento_previo", "investidor", "bancos");
			//manager.createPropertyAssertions();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
