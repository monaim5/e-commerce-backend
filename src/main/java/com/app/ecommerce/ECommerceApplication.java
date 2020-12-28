package com.app.ecommerce;

import com.app.ecommerce.models.Photo;
import com.app.ecommerce.repositories.CategoryRepository;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.*;

@SpringBootApplication
@EnableAsync
public class ECommerceApplication {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PhotoRepository photoRepository;


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
//				Product p = Product.builder()
//					.id(null)
//					.name(RandomString.make(10))
//					.designation(null)
//					.price(rnd.nextDouble())
//					.quantity(rnd.nextInt(2))
//					.category(c)
//					.build();
//				productRepository.save(p);
//
//				Photo photo = new Photo();
//				photo.setTitle("unknown");
//				photo.setUrl("unknown.jpg");
//				photo.setProduct(p);
//				photo = photoRepository.save(photo);
//
//			}
//		});
//	}
}
