package com.app.ecommerce;

import com.app.ecommerce.repositories.CategoryRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class ECommerceApplication {

//	@Autowired
//	private ProductRepository productRepository;
//	@Autowired
//	private CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Random rnd = new Random();
//		categoryRepository.save(new Category(null, "smartphones", null, null, null));
//		categoryRepository.save(new Category(null, "computers", null, null, null));
//		categoryRepository.save(new Category(null, "tvs", null, null, null));
//
//		categoryRepository.findAll().forEach(c -> {
//			for (int i = 0; i < 10; i++){
//				Product p = new Product(null, RandomString.make(10), null, rnd.nextDouble(), rnd.nextInt(2), "unknown.png", rnd.nextBoolean(), c);
//				productRepository.save(p);
//			}
//		});
//	}
}
