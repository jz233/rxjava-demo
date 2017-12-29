package cn.zjj;

import cn.zjj.entity.School;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

public class BufferTest {

    @Test
    public void test1() {
        Observable.just(1, 2, 3).buffer(2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                System.out.println("size: " + integers.size());
            }
        });
    }

    @Test
    public void test2() {
        List<School> schoolList = new ArrayList<>();

        List<School.Student> studentList1 = new ArrayList<>();
        studentList1.add(new School.Student("student A"));
        studentList1.add(new School.Student("student C"));
        List<School.Student> studentList2 = new ArrayList<>();
        studentList2.add(new School.Student("student B"));
        studentList2.add(new School.Student("student D"));

        School s1 = new School("school 1", studentList1);
        School s2 = new School("school 2", studentList2);
        schoolList.add(s1);
        schoolList.add(s2);

        Observable.from(schoolList).map(new Func1<School, School>() {
            @Override
            public School call(School school) {
                school.setName("NB School");
                return school;
            }
        }).buffer(schoolList.size())
        .subscribe(new Action1<List<School>>() {
            @Override
            public void call(List<School> schools) {
                // ....
            }
        });

    }

}
