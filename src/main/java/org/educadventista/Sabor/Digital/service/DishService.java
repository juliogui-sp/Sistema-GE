package org.educadventista.Sabor.Digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.educadventista.Sabor.Digital.model.Dish;
import org.educadventista.Sabor.Digital.repository.DishRepository;

import java.io.IOException;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ImageDownloadService imageDownloadService;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public Dish create(Dish dish) {
        try {
            String storedImagePath = imageDownloadService.downloadImage(dish.getUrlImage());
            dish.setUrlImage(storedImagePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com exceções conforme necessário
        }
        return dishRepository.save(dish);
    }

    public Dish update(Long id, Dish dishDetails) {
        Dish dish = findById(id);

        // Atualizar apenas os atributos especificados na requisição, se não forem nulos
        if (dishDetails.getName() != null) {
            dish.setName(dishDetails.getName());
        }
        if (dishDetails.getDescription() != null) {
            dish.setDescription(dishDetails.getDescription());
        }
        if (dishDetails.getPrice() != null) {
            dish.setPrice(dishDetails.getPrice());
        }
        dish.setCategoria(dishDetails.getCategoria()); // Atualizar a categoria

        // Update the image only if the URL has changed (commented out for now)
        /*if (!dish.getUrlImage().equals(dishDetails.getUrlImage())) {
            try {
                String storedImagePath = imageDownloadService.downloadImage(dishDetails.getUrlImage());
                dish.setUrlImage(storedImagePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com exceções conforme necessário
            }
        }*/

        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        Dish dish = findById(id);
        dishRepository.delete(dish);
    }
}