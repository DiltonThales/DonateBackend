package br.fai.backend.donate.backend.main.port.service.crud;

public interface CrudService<T> extends
        CreateService<T>,
        ReadService<T>,
        UpdateService<T>,
        DeleteService{
}
