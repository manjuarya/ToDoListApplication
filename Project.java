/**
 * This class is used to access the project data
 * and manipulation of this data
 */

public class Project {
    private int id;
    private String name;

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return   id +"\t"+ name;
    }
}
