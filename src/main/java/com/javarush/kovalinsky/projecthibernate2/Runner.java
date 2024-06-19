package com.javarush.kovalinsky.projecthibernate2;

import com.javarush.kovalinsky.projecthibernate2.config.SessionCreator;
import com.javarush.kovalinsky.projecthibernate2.dao.*;
import com.javarush.kovalinsky.projecthibernate2.entity.*;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class Runner {

    private static final Session session = SessionCreator.getSession();

    private static final ActorDAO actorDAO = new ActorDAO(session);
    private static final AddressDAO addressDAO = new AddressDAO(session);
    private static final CategoryDAO categoryDAO = new CategoryDAO(session);
    private static final CityDAO cityDAO = new CityDAO(session);
    private static final CountryDAO countryDAO = new CountryDAO(session);
    private static final CustomerDAO customerDAO = new CustomerDAO(session);
    private static final FilmDAO filmDAO = new FilmDAO(session);
    private static final FilmTextDAO filmTextDAO = new FilmTextDAO(session);
    private static final InventoryDAO inventoryDAO = new InventoryDAO(session);
    private static final LanguageDAO languageDAO = new LanguageDAO(session);
    private static final PaymentDAO paymentDAO = new PaymentDAO(session);
    private static final RentalDAO rentalDAO = new RentalDAO(session);
    private static final StaffDAO staffDAO = new StaffDAO(session);
    private static final StoreDAO storeDAO = new StoreDAO(session);

    public Runner() {
    }

    public static void main(String[] args) {
        try (session) {
            Runner runner = new Runner();
            Customer customer = runner.createCustomer();
            runner.returnRentalFilm();
            runner.rentFilmInventory(customer);
            runner.createNewFilmForRent();
        }
    }

    private void createNewFilmForRent() {
        Language language = languageDAO.getItems(0, 10).getFirst();
        List<Category> categories = categoryDAO.getItems(0, 5);
        List<Actor> actors = actorDAO.getItems(0, 20);
        String filmTitle = "Filmus";
        String filmDescription = "Descriptionus";
        Film film = Film.builder()
                .title(filmTitle)
                .description(filmDescription)
                .year((short) 2025)
                .language(language)
                .originalLanguage(language)
                .rentalDuration((byte) 4)
                .rentalRate(BigDecimal.valueOf(2.20))
                .length((short) 120)
                .replacementCost(BigDecimal.valueOf(10.8))
                .rating(Rating.NC17)
                .actors(actors)
                .categories(categories)
                .build();
        film.setSpecialFeatures(Set.of(Feature.TRAILERS, Feature.COMMENTARIES));
        filmDAO.save(film);

        FilmText filmText = FilmText.builder()
                .film(film)
                .title(filmTitle)
                .description(filmDescription)
                .build();
        filmTextDAO.save(filmText);
    }

    private void rentFilmInventory(Customer customer) {
        Film film = filmDAO.getAnyAvailableFilmForRent();
        Store store = storeDAO.getItems(0, 1).getFirst();

        Inventory inventory = Inventory.builder()
                .film(film)
                .store(store)
                .build();
        inventoryDAO.save(inventory);

        Staff staff = store.getStaff();
        Rental rental = Rental.builder()
                .rentalDate(LocalDateTime.now())
                .inventory(inventory)
                .customer(customer)
                .staff(staff)
                .build();
        rentalDAO.save(rental);

        Payment payment = Payment.builder()
                .customer(customer)
                .staff(staff)
                .rental(rental)
                .amount(BigDecimal.valueOf(5.7))
                .paymentDate(LocalDateTime.now())
                .build();
        paymentDAO.save(payment);
    }

    private void returnRentalFilm() {
        Rental rental = rentalDAO.getAnyUnreturnedRental();
        rental.setReturnDate(LocalDateTime.now());
        rentalDAO.save(rental);
    }


    private Customer createCustomer() {
        City city = cityDAO.getByName("Kansas City");

        Address address = Address.builder()
                .address("1210 Admiral Blvd")
                .phone("555-555-1234")
                .city(city)
                .district("Paseo West")
                .build();
        addressDAO.save(address);

        Store store = storeDAO.getItems(0, 1).getFirst();
        Customer customer = Customer.builder()
                .address(address)
                .store(store)
                .firstName("Jack")
                .lastName("Benton")
                .email("benton@gmail.com")
                .isActive(true)
                .build();
        customerDAO.save(customer);

        return customer;
    }
}
