package jeff.question1.process;

import jeff.question1.objects.Difference;
import jeff.question1.objects.Employee;
import jeff.question1.process.xml.XMLMarshaller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static jeff.question1.util.Utils.convertToCSV;

/**
 * Write a Java program to compare two log files with multiple xml document and create a report to show
 * all the differences:
 * Expected Input:
 * • 2 log files which each of them contains multiple employee xml in it
 * • <id> is the unique id for employee
 * • Xml record can be of different sequence or missing in either side
 * • There is no max limit of xml in a file. Can be > 100,000 records
 *
 * This file take runtime arguments to know the 2 log files named source and target
 * also takes filePath of CSV gile to be generated
 */
public class Main {

    //Differciator Utility to generate the diff
    public static DifferenceGenerator differenceGenerator = new DifferenceGenerator();

    /**
     * Validates the inputs before processing
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        validate(args[0], "-s");
        validate(args[2], "-t");
        validate(args[4], "-d");
        validateFilePath(args[1]);
        validateFilePath(args[3]);
        //validateFilePath(args[5]);

        try {
            new Main().process(args[1],args[3],args[5]);
        } catch (JAXBException e) {
           throw new IllegalArgumentException("Please check if the file format is correct",e);
        }
    }

    /**
     * Check if the filePath is correct; means file is present at the location
     * @param filePathString
     * @throws FileNotFoundException
     */
    private static void validateFilePath(String filePathString) throws FileNotFoundException {
        Path path = Paths.get(filePathString);
        if (!Files.exists(path))
            throw new FileNotFoundException("File path is worong : "+filePathString);
    }

    /**
     * Validate the run-time arguments
     * @param arg
     * @param s
     */
    private static void validate(String arg, String s) {
        if(!arg.equalsIgnoreCase(s)){
            System.out.println("Please use below patten to provide commandLineArgs");
            System.out.println("java Main -s <absoluteFilePathForSource> -t <absoluteFilePathForTarget> -d <absoluteFilePathOfOutputCSV>");
            throw new IllegalArgumentException("Wrong Arguments");
        }

    }

    /**
     * This is the main method which takes on the files and process them
     * It uses Java JAXB to unmarshell the bytes read to Java Objects.
     *
     * In order to compare the data, first load the files in-memory.
     * (Please note we can use SAX pareser to compare withoutloading but it will have high time complexity
     *
     * Once loaded, create a map from source data for fast lookup.
     * After that iterate over the target date in order to find matching Employees
     * Once found send the data to DifferenceGenerator in order to get the results.
     *
     * Finally output to given CSV file-location
     * @param source
     * @param target
     * @param destination
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    private void process(String source, String target, String destination) throws JAXBException, FileNotFoundException {
        List<Employee> sourceList = XMLMarshaller.getInstance().getEmployees(source);
        List<Employee> targetList = XMLMarshaller.getInstance().getEmployees(target);

        //create a map out of source file
        Map<Long, Employee> map = sourceList.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::self));

        //empty list for collecting diff in data
        List<Difference>  diff = new ArrayList<>();

        //iterate over the target data to find common Employee data followed by diff generation
        targetList.stream()
                .forEach( emp -> {
                    if(map.containsKey(emp.getId())){
                        differenceGenerator.compareAndSet(map.get(emp.getId()), emp, diff);
                        sourceList.remove(emp);
                    }else{
                        diff.add(new Difference(emp.getId(),"","MISSING",""));
                    }
                });

        //Add the items not present in Target
        sourceList.stream()
                .forEach(emp -> diff.add(new Difference(emp.getId(),"","","MISSING")));

        //Sort the Diff list
        diff.stream()
                .sorted(Comparator.comparingLong(Difference::getId))
                .forEach(System.out::println);

        //For csv data holder
        List<String> data = new ArrayList<>();
        //add the header
        data.add(convertToCSV(new String[]{"employee id","diff tag","value in source","value in target"}));
        //Iterate over the diff and add to CSV data holder
        diff.stream()
                .forEach(d -> data.add(d.toString()));

        //Use CSV utility for generating output file
        CSVGenerator.generateCSV(data, destination);
    }
}
