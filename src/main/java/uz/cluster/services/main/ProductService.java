package uz.cluster.services.main;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cluster.entity.main.Product;
import uz.cluster.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FetchProductService fetchProductService;

    public List<Product> getList(String category) {
        return productRepository.findAllByCategory(category);
    }

    public String load() {
        ExecutorService executor = Executors.newFixedThreadPool(4); // Adjust the pool size as needed
        try {
            List<Future<List<Product>>> futures = new ArrayList<>();

            for (int i = 1; i <= 20; i++) {
                final int index = i;
                Callable<List<Product>> task = () -> {
                    List<Product> products;
                    if (index == 1) {
                        products = fetchProductService.fetchAndSaveGoods(1);
                    } else {
                        products = fetchProductService.fetchAndSaveGoods(index * 60);
                    }
                    return products;
                };
                futures.add(executor.submit(task));
            }

            for (Future<List<Product>> future : futures) {
                List<Product> products = future.get(); // Wait for each task to complete and get the result
                productRepository.saveAll(products);
            }
        } catch (Exception e) {
            System.out.println("Productlarni yuklashda xatolik yuzaga keldi !");
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shut down the executor service
        }

        return "Productlar yuklandi !";
    }


    public String loadMainProduct() {
        ExecutorService executor = Executors.newFixedThreadPool(20); // Adjust the pool size as needed

        try {
            List<Future<List<Product>>> futures = new ArrayList<>();

            for (int i = 0; i <= 1000; i++) {
                final int index = i;
                Future<List<Product>> future = executor.submit(() -> {
                    List<Product> products = new ArrayList<>();
                    if (index == 0) {
                        products = fetchProductService.fetchAndSaveMainGoods(index);
                    } else {
                        products = fetchProductService.fetchAndSaveMainGoods(index * 40);
                    }
                    productRepository.saveAll(products);
                    return products;
                });
                futures.add(future);
            }

            // Process all futures to ensure all tasks are complete
            for (Future<List<Product>> future : futures) {
                future.get(); // This will block until the task is complete
            }

        } catch (Exception e) {
            System.out.println("Productlarni yuklashda xatolik yuzaga keldi !");
            return "Xatolik";
        } finally {
            executor.shutdown(); // Properly shut down the executor
        }
        return "Productlar yuklandi !";
    }

}
