package geektime.spring.springbucks;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import geektime.spring.springbucks.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBucksApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args){
        List<Student> list =new ArrayList<>();
        list.add(new Student("张三", new BigDecimal("99.01")));
		list.add(new Student("李四", new BigDecimal("80")));
		list.add(new Student("王五", new BigDecimal("90.85")));
		list.add(new Student("赵六", new BigDecimal("60")));

		//lambda表达式简写格式
		list.sort(new Comparator<Student>() {
			@Override
			public int compare(Student student1, Student student2) {
				BigDecimal score1 = student1.getScore();
				BigDecimal score2 = student2.getScore();
				return score2.compareTo(score1);
			}
		});

		for (Student stu : list) {
			System.out.println(stu);
		}
	}

}

