package com.ednadev.book;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ednadev.book.domain.Book;
import com.ednadev.book.domain.BookUser;

@SpringBootTest
class BackEndApplicationTests {

	@Test
	void contextLoads() throws IOException {
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		SqlSession session = factory.openSession();
		
//		BookUser bookUser = new BookUser();
//		bookUser.setUserEmail("ednadev123@naver.com");
//		bookUser.setUserName("김미경");
//		bookUser.setUserImg("이미지가 들어가는 곳");
//		session.insert("UserMapper.insertUser", bookUser);
//		session.commit();
//		System.out.println("회원가입");
		
//		bookUser.setUserEmail("test01@naver.com");
		//bookUser.setUserPass("1234");
//		session.insert("UserMapper.insertUser", bookUser);
//		session.commit();
//		BookUser user = session.selectOne("UserMapper.login", bookUser);
//		System.out.println(user);
		
//		Book book = new Book();
//		book.setBookIsbn("ej23f2f");
//		book.setBookTitle("ajflk");
//		session.insert("BookMapper.insertBook", book);
//		session.commit();
//		System.out.println("책 추가");
		
		BookUser user = session.selectOne("UserMapper.selectUser", "ednadev1023@gmail.com");
		System.out.println(user);
		
		
	}

}
