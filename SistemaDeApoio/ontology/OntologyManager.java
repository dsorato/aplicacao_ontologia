import java.io.ByteArrayOutputStream;
import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class OntologyManager {

	public static OWLOntology loadOntology() throws OWLOntologyCreationException {
		/*
		 * Since we wont load our ontology from web, we have to load the file
		 * that contains the ontology
		 */
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		// TODO: find the current directory and make this path relative
		File file = new File(
				"/home/rr/workspace/aplicacao_ontologia/SistemaDeApoio/ontology/ontologia_aplicacao.owl");
		// loading the local ontology
		OWLOntology localOntology = manager
				.loadOntologyFromOntologyDocument(file);
		System.out.println("Loaded ontology: " + localOntology);
		// We can always obtain the location where an ontology was loaded from
		IRI documentIRI = manager.getOntologyDocumentIRI(localOntology);
		System.out.println("    from: " + documentIRI);
		return localOntology;
	}

	

	public static void main(String[] args) {
		try {
			OntologyManager.loadOntology();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
