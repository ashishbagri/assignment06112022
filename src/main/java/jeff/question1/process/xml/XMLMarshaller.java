package jeff.question1.process.xml;

import jeff.question1.objects.Employee;
import jeff.question1.objects.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Represent JAXB Marshaller
 * Reads from file and user JAXB annotation to consturct Java Objects.
 */
public class XMLMarshaller {

    private Unmarshaller unmarshaller;
    public static final XMLMarshaller xmlMarshaller = new XMLMarshaller();

    /**
     * Keeping private to reuse unmarshaller
     * we have to load multiple files of same structure
     */
    private XMLMarshaller() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * to fetch instance of Marshaller
     * @return
     */
    public static XMLMarshaller getInstance(){
        return xmlMarshaller;
    }

    /**
     * Decodes Employee class from given XML filePath
     * @param filePath
     * @return
     * @throws FileNotFoundException
     * @throws JAXBException
     */
    public List<Employee> getEmployees(final String filePath) throws FileNotFoundException, JAXBException {
        return getEmployees(new FileReader(filePath));
    }

    /**
     * Decodes Employee class from given XML file.
     * @param reader
     * @return
     * @throws JAXBException
     */
    public List<Employee> getEmployees(final FileReader reader) throws JAXBException {
        Employees employees = (Employees)unmarshaller.unmarshal(reader);
        return employees.getEmployees();
    }
}
