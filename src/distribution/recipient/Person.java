package distribution.recipient;

import fileio.input.ChildInputData;

/**
 * Abstract class which defines a person
 */
public abstract class Person {
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;

    /**
     * Constructor which creates a Person object from the child input data
     */
    public Person(final ChildInputData childInputData) {
        this.lastName = childInputData.getLastName();
        this.firstName = childInputData.getFirstName();
        this.age = childInputData.getAge();
        this.city = childInputData.getCity();
    }

    /**
     * Increases age by 1
     */
    public final void increaseAge() {
        ++age;
    }

    public final String getLastName() {
        return lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final Integer getAge() {
        return age;
    }

    public final String getCity() {
        return city;
    }
}
