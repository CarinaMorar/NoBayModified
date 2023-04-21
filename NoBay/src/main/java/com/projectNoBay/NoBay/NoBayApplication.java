package com.projectNoBay.NoBay;

import com.projectNoBay.NoBay.mapper.AdminMapper;
import com.projectNoBay.NoBay.model.Admin;
import com.projectNoBay.NoBay.model.BeautyProducts;
import com.projectNoBay.NoBay.model.Orders;
import com.projectNoBay.NoBay.model.User;
import com.projectNoBay.NoBay.repository.AdminRepository;
import com.projectNoBay.NoBay.repository.BeautyProductsRepository;
import com.projectNoBay.NoBay.repository.UserRepository;
import com.projectNoBay.NoBay.repository.OrderRepository;
import com.projectNoBay.NoBay.service.AdminService;
import com.projectNoBay.NoBay.service.BeautyProductsService;
import com.projectNoBay.NoBay.service.OrderService;
import com.projectNoBay.NoBay.service.UserService;
import com.projectNoBay.NoBay.serviceImplementation.AdminServiceImplementation;
import com.projectNoBay.NoBay.serviceImplementation.BeautyProductsServiceImplementation;
import com.projectNoBay.NoBay.serviceImplementation.OrderServiceImplementation;
import com.projectNoBay.NoBay.serviceImplementation.UserServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan({"com.projectNoBay.NoBay.repository"})

public class NoBayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoBayApplication.class, args);
	}

	@Bean
	CommandLineRunner init (AdminRepository adminRepository, UserRepository userRepository, BeautyProductsRepository beautyProductsRepository, OrderRepository orderRepository) {
		return args -> {

			AdminService adminService = new AdminServiceImplementation(adminRepository, beautyProductsRepository, userRepository, orderRepository);
			UserService userService=new UserServiceImplementation(userRepository);
			OrderService orderService=new OrderServiceImplementation();
			BeautyProductsService beautyProductsService=new BeautyProductsServiceImplementation();


			//BD Admin
			Admin admin = new Admin();
			admin.setAdminName("carina.morar");
			admin.setEmail("carina@gmail.com");
			admin.setFirstName("Carina");
			admin.setLastName("Morar");
			admin.setPassword("password");
			adminRepository.save(admin);

			Admin admin2 = new Admin();
			admin2.setAdminName("carina.morar2");
			admin2.setEmail("carina2@gmail.com");
			admin2.setFirstName("Carina2");
			admin2.setLastName("Morar2");
			admin2.setPassword("password2");
			adminRepository.save(admin2);

			//Bd Beauty
			BeautyProducts product1 = new BeautyProducts();
			product1.setNameProduct("La Vie Est Belle");
			product1.setBrand("Lancome");
			product1.setPrice(650);
			product1.setQuantity(32);
			beautyProductsRepository.save(product1);

			BeautyProducts product2 = new BeautyProducts();
			product2.setNameProduct("L'Interdit");
			product2.setBrand("Givenchy");
			product2.setPrice(569);
			product2.setQuantity(20);
			beautyProductsRepository.save(product2);

			BeautyProducts product3 = new BeautyProducts();
			product3.setNameProduct("The Scent");
			product3.setBrand("Hugo Boss");
			product3.setPrice(270);
			product3.setQuantity(18);
			beautyProductsRepository.save(product3);

			//Bd User
			User user1 = new User();
			user1.setEmail("ana.banana@gmail.com");
			user1.setPassword("password");
			user1.setFirstName("Ana");
			user1.setLastName("Banana");
			user1.setUsername("ana.banana");
			user1.setOrders(null);
			userRepository.save(user1);

			User user2 = new User();
			user2.setEmail("alina.maslina@gmail.com");
			user2.setPassword("password");
			user2.setFirstName("Alina");
			user2.setLastName("Maslina");
			user2.setUsername("alina.maslina");
			user2.setOrders(null);
			userRepository.save(user2);

			List<BeautyProducts> beautyProductsList1 = new ArrayList<>();
			beautyProductsList1.add(product1);
			beautyProductsList1.add(product2);
			beautyProductsList1.add(product3);

			List<Orders> ordersList = new ArrayList<>();
			//ordersList.add()

			User user3 = new User();
			user3.setEmail("dana.lana@gmail.com");
			user3.setPassword("password");
			user3.setFirstName("Dana");
			user3.setLastName("Lana");
			user3.setUsername("dana.lana");
			user3.setOrders(ordersList);
			userRepository.save(user3);


			Orders orders1 = new Orders();
			orders1.setId(1L);
			orders1.setQuantity(12);
			orders1.setPrice(1000);
			orders1.setBeautyProductsCart(null);
			orders1.setUser(user3);
			log.info(String.valueOf(orders1));
			orderRepository.save(orders1);

			try
			{
				adminService.logIn(admin,"password", "carina@gmail.com");
			}catch (Exception e){
				e.printStackTrace();
			}

		//	System.out.println(adminService.findAllAdmins());
		};


	}
}
