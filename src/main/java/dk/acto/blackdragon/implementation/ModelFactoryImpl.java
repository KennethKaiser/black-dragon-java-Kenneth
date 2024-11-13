package dk.acto.blackdragon.implementation;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.service.ModelFactory;
import io.vavr.collection.List;

public class ModelFactoryImpl implements ModelFactory {
    /**
     * Author: Kenneth Kaiser
     *
     * Parsing the string input we got from DataFetcher into a list of Model object.
     *
     * @return model object list
     */
    public List<Model> parse(String string) {

        List<Model> models = List.empty();

        // Getting lines from string input by seperating with \n"
        String[] lines = string.trim().split("\n");

        for (int i = 1; i < lines.length; i++) { // i = 1 to skip the header (id, weight, cost, inventory )

            // Splitting the line with comma
            String[] values = lines[i].split(",");
            // Putting in the values and trimming
            long id = Long.parseLong(values[0].trim());
            double weight = Double.parseDouble(values[1].trim());
            int cost = Integer.parseInt(values[2].trim());
            long inventory = Long.parseLong(values[3].trim());

            // Create a new Model object and adding it to the list
            models = models.append(new Model(id, cost, inventory, weight));
        }

        return models;
    }
}
