package ru.sbt;

/**
 * Created by Sania on 18.09.2016.
 */
public class Person {
    private final boolean man;

    private final String name;

    private Person spouse;


    public Person(boolean man, String name) {

        this.man = man;

        this.name = name;

    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * <p>
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife.
     * Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */

    public boolean marry(Person person) {
        if (man != person.isMan() && spouse != person) {
            if (spouse == null) {
                spouse = person;
                System.out.println(this.name + " married " + person.getName());
                person.marry(this);
            }
            else {
                spouse.divorce();
                divorce();
                if (person.getSpouse() != null) {
                    person.getSpouse().divorce();
                    person.divorce();
                }
                spouse = person;
                System.out.println(this.name + " married " + person.getName());
                spouse.marry(this);
            }
        }
        return false;
    }


    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */

    public boolean divorce() {
        if (spouse != null) {
            System.out.println(this.name + " divorced with " + spouse.getName());
            spouse = null;
            return true;
        }
        return false;
    }


    public boolean isMan() {
        return man;
    }

    public String getName() {
        return name;
    }

    public Person getSpouse() {
        return spouse;
    }

}
