package jeff.question1.process;

import jeff.question1.objects.Difference;
import jeff.question1.objects.Employee;

import java.lang.reflect.Field;
import java.util.List;

/**
 * This is the heart of the program
 * Compares given Source and Target Employee data
 * Checks all fields and set the Diff
 */
public class DifferenceGenerator {


    /**
     * Get all fields and then generate Diff
     * @param source
     * @param target
     * @param diff
     * @return
     */
    public List<Difference> compareAndSet(Employee source, Employee target, List<Difference> diff) {
        Field[] fields = source.getClass().getDeclaredFields();
        for(Field field : fields){
            Difference d = null;
            if(field.getName().equalsIgnoreCase("id"))
                continue;
            try {
                d = getDiff(source.getId(), source, target, field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if(d  != null)
                diff.add(d);
        }
        return diff;
    }

    /**
     * Create Difference for given property
     * @param id
     * @param source
     * @param target
     * @param name
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private Difference getDiff(long id, Employee source, Employee target, String name) throws IllegalAccessException, NoSuchFieldException {
        Object val1 = getFieldValue(source, name);
        Object val2 = getFieldValue(target, name);
        if((val1 == null && val2 == null) || val1.equals(val2))
            return null;
        if(val1 == null)
            return new Difference(id, name,"MISSING", val2.toString());
        if(val1 == null)
            return new Difference(id, name, val1.toString(),"MISSING");
        return new Difference(id, name, val1.toString(),val2.toString());
    }

    /**
     * Use Reflection to fetch the field data
     * @param emp
     * @param name
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private Object getFieldValue(Employee emp, String name) throws NoSuchFieldException, IllegalAccessException {
        Field f = emp.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(emp);
    }
}
