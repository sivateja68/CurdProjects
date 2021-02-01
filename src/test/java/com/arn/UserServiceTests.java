package com.arn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.arn.model.User;
import com.arn.repo.UserRepository;
import com.arn.service.IUserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private IUserService service;

	@MockBean
	private UserRepository repo;

	@Test
	public void testGetAll() {
		when(repo.findAll()).thenReturn(Stream.of(new User(1,"teja","arnsivateja@gmail.com","siva"))
				.collect(Collectors.toList()));
		assertEquals(1,service.getAllUser().size());
	}
	
	@Test
	public void testGetAll2() {
		List<User> user = new ArrayList<User>();
		user.add(new User(1,"teja","arnsivateja@gmail.com","siva"));
		user.add(new User(2,"teja","arnsivateja@gmail.com","siva"));
		
		when(repo.findAll()).thenReturn(user);
		assertEquals(2,service.getAllUser().size());
	}

	@Test
	public void testSave() {
		User user = new User(1,"teja","arnsivateja@gmail.com","siva");
		when(repo.save(user)).thenReturn(user);
		assertEquals(user.getId(),service.saveUser(user));
	}

	@Test
	public void testDelete() {
		User user = new User(101,"teja","arnsivateja@gmail.com","siva");
		service.deteleUser(user.getId());
		verify(repo,times(1)).deleteById(user.getId());
	}

	@Test
	public void testUpdate() {
		User user = new User(1,"teja","arnsivateja@gmail.com","siva");
		service.updateUser(user);
		verify(repo,times(1)).save(user);
		
	}
	
	@Test
	public void testGetOne() {
		Optional<User> user = Optional.of(new User(1,"teja","arnsivateja@gmail.com","siva"));
		when(repo.findById(1)).thenReturn(user);
		assertEquals(user, service.getOneUser(1));
		
	}
	@Test
	public void testIsExist() {		
		//User user = new User(1,"teja","arnsivateja@gmail.com","siva");
		when(repo.existsById(1)).thenReturn(true);
		//assertEquals(true, service.isExist(1));
		//assertTrue(service.isExist(1));
		//assertThat(service.isExist(1)).isFalse();
		assertThat(service.isExist(1)).isTrue();
	}

	
}



