package com.example.comparator.repos;

import com.example.comparator.models.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/** Репозиторий для работы с валютами */
@Repository
public interface CurrencyRepo extends CrudRepository<Currency, Integer> {
    @Query("SELECT currency FROM Currency currency WHERE currency.name=:name")
    Currency findCurrencyByName(@Param("name") String name);

    @Query("SELECT currency FROM Currency currency")
    List<Currency> getAllCurrencies();
}
