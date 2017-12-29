package cn.zjj;

import cn.zjj.entity.School;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

public class FlapMapTest {

    private List<School> schoolList;

    @Before
    public void setUp() {
        schoolList = new ArrayList<>();
        School s1 = new School();
        s1.setName("school 1");
        School s2 = new School();
        s2.setName("school 2");
//        School.Student stu1 = new School.Student("student A");
//        stu1.setName("student A");
//        School.Student stu2 = new School.Student("student A");
//        stu2.setName("student B");

        List<School.Student> studentList1 = new ArrayList<>();
        studentList1.add(new School.Student("student A"));
        studentList1.add(new School.Student("student C"));
        List<School.Student> studentList2 = new ArrayList<>();
        studentList2.add(new School.Student("student B"));
        studentList2.add(new School.Student("student D"));

        s1.setStudentList(studentList1);
        s2.setStudentList(studentList2);
        schoolList.add(s1);
        schoolList.add(s2);


    }

    @Test
    public void test1() {
        Observable.from(schoolList).map(new Func1<School, String>() {
            @Override
            public String call(School school) {
                return school.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String schoolName) {
                System.out.println(schoolName);
            }
        });
    }

    /**
     * school
     */
    @Test
    public void test2() {
        Observable.from(schoolList).flatMap(new Func1<School, Observable<School.Student>>() {
            @Override
            public Observable<School.Student> call(School school) {
                return Observable.from(school.getStudentList());
            }
        }).subscribe(new Action1<School.Student>() {
            @Override
            public void call(School.Student student) {
                System.out.println(student.getName());
            }
        });

    }

}
