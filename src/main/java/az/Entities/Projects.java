package az.Entities;

public class Projects {

    private static  int id;
    private  static String project_name;
    private static  String title;

    public Projects(int id,String project_name, String title) {
        this.id = id;
        this.project_name = project_name;
        this.title = title;
    }

    public Projects(String project_name, String title) {
        this.project_name = project_name;
        this.title = title;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        Projects.id = id;
    }

    public  String getProject_name() {
        return project_name;
    }

    public  void setProject_name(String project_name) {
        Projects.project_name = project_name;
    }

    public  String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        Projects.title = title;
    }
}
