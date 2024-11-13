package dk.acto.blackdragon.implementation;

import dk.acto.blackdragon.model.AuthorData;
import dk.acto.blackdragon.service.AuthorDataFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class AuthorDataFactoryImpl implements AuthorDataFactory {


    /**
     * Author: Kenneth Kaiser
     *
     * This method creates and returns an instance of AuthorData
     * populated with my own information; my name, my LinkedIn profile,
     * and my solution repository URL.
     *
     * @return AuthorData object with my information
     */
    @Override
    public AuthorData create() {
        try {
            return AuthorData.builder()
                    .name("Kenneth Kaiser")
                    .linkedInProfile(new URL("https://www.linkedin.com/in/kenneth-kaiser-a0502732b/"))
                    .solutionRepository(new URL("https://github.com/KennethKaiser/black-dragon-java-Kenneth"))
                    .build();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
