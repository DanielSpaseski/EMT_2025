package com.example.emt_lab_b_backend.config.initialization;

import com.example.emt_lab_b_backend.model.domain.Accommodation;
import com.example.emt_lab_b_backend.model.domain.Host;
import com.example.emt_lab_b_backend.model.domain.User;
import com.example.emt_lab_b_backend.model.enumerations.Role;
import com.example.emt_lab_b_backend.repository.AccommodationRepository;
import com.example.emt_lab_b_backend.repository.HostRepository;
import com.example.emt_lab_b_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public DataInitializer(PasswordEncoder passwordEncoder, UserRepository userRepository, AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @PostConstruct
    public void init(){
        User customer = userRepository.save(new User(
                "customer",
                passwordEncoder.encode("customer"),
                "customer",
                "customer",
                "customer@email.com",
                Role.ROLE_CUSTOMER
        ));

        User owner = userRepository.save(new User(
                "owner",
                passwordEncoder.encode("owner"),
                "owner",
                "owner",
                "owner@email.com",
                Role.ROLE_OWNER
        ));

        User admin = userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "admin",
                "admin",
                "admin@email.com",
                Role.ROLE_ADMIN
        ));

        Host host1 = hostRepository.save(new Host("Daniel", "Spaseski"));
        Host host2 = hostRepository.save(new Host("Ana", "Petrova"));
        Host host3 = hostRepository.save(new Host("Marko", "Jovanov"));
        Host host4 = hostRepository.save(new Host("Elena", "Ilievska"));
        Host host5 = hostRepository.save(new Host("Stefan", "Georgiev"));
        Host host6 = hostRepository.save(new Host("Ivana", "Todorovska"));
        Host host7 = hostRepository.save(new Host("Nikola", "Veljanoski"));
        Host host8 = hostRepository.save(new Host("Marija", "Kostova"));
        Host host9 = hostRepository.save(new Host("Petar", "Micevski"));
        Host host10 = hostRepository.save(new Host("Simona", "Ristovska"));

        accommodationRepository.save(new Accommodation(
                "Seaside Escape",
                "A cozy beachside apartment with a beautiful sea view.",
                120.0,
                5,
                host1));

        accommodationRepository.save(new Accommodation(
                "Mountain Retreat",
                "A peaceful cabin in the mountains, perfect for hiking lovers.",
                95.0,
                3,
                host2));

        accommodationRepository.save(new Accommodation(
                "City Lights Loft",
                "Modern loft in the heart of the city, close to all attractions.",
                150.0,
                2,
                host3));

        accommodationRepository.save(new Accommodation(
                "Countryside Villa",
                "Spacious villa surrounded by vineyards and scenic landscapes.",
                200.0,
                4,
                host4));

        accommodationRepository.save(new Accommodation(
                "Lakeview Cottage",
                "Charming cottage with a private dock on the lake.",
                110.0,
                6,
                host5));

        accommodationRepository.save(new Accommodation(
                "Desert Hideaway",
                "Unique desert-style bungalow with stargazing rooftop.",
                130.0,
                2,
                host6));

        accommodationRepository.save(new Accommodation(
                "Forest Lodge",
                "Secluded lodge deep in the forest with fireplace and sauna.",
                175.0,
                3,
                host7));

        accommodationRepository.save(new Accommodation(
                "Historic Guesthouse",
                "Beautifully restored 19th-century guesthouse with vintage decor.",
                90.0,
                5,
                host8));

        accommodationRepository.save(new Accommodation(
                "Modern Studio",
                "Compact and stylish studio for solo travelers and couples.",
                80.0,
                8,
                host9));

        accommodationRepository.save(new Accommodation(
                "Eco Farm Stay",
                "Sustainable farm stay experience with homegrown meals.",
                100.0,
                4,
                host10));


    }

}
