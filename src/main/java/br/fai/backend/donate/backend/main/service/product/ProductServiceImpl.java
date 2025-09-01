package br.fai.backend.donate.backend.main.service.product;

import br.fai.backend.donate.backend.main.domain.Product;
import br.fai.backend.donate.backend.main.port.service.crud.CreateService;

public class ProductServiceImpl implements CreateService<Product> {
    @Override
    public int create(Product entity) {
        return 0;
    }
}
