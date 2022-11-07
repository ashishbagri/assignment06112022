package jeff.question1.objects;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * <employee>
 *  <id>1001</id>
 *  <name>John</name>
 *  <department>Support</department>
 *  <phone>2200399</phone>
 *  </employee>
 */
public class Employee {
    private Long id;
    private String name;
    private String department;
    private String phone;

    public Long getId() {
        return id;
    }

    @XmlElement
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    @XmlElement
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    @XmlElement
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Employee self(){
        return this;
    }
}
