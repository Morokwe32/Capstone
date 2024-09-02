package za.ac.cput.service;

import za.ac.cput.domain.AboutUs;

import java.util.List;

public interface IAboutUsService {

    AboutUs create(AboutUs aboutUs);
    AboutUs read(int id);
    AboutUs update(AboutUs aboutUs);
    boolean delete(int id);
    List<AboutUs> getAll();
}
