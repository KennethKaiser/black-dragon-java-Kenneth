package dk.acto.blackdragon.implementation;

import dk.acto.blackdragon.model.Model;
import dk.acto.blackdragon.model.Stats;
import dk.acto.blackdragon.service.ModelTransformer;
import io.vavr.collection.List;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ModelTransformerImpl implements ModelTransformer<Model, Stats> {
    /**
     * Author: Kenneth Kaiser
     *
     * Transforms a list of Model objects we got from model factory into a Stats that can show statistics
     * from the black dragon inventory. The calculated stats are:
     * - even and odd ID counts
     * - mean cost per item in dollars
     * - weighted inventory
     * - total inventory
     *
     * @return a Stats object with the information
     */
    @Override
    public Stats transform(List<Model> model) {
        BigInteger evenIdCount = BigInteger.ZERO;
        BigInteger oddIdCount = BigInteger.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal totalWeightedInventory = BigDecimal.ZERO;
        BigInteger totalInventory = BigInteger.ZERO;
        BigDecimal meanCost;

        for (int i = 0; i < model.size(); i++) {

            Model m = model.get(i);

            // Check if ID number is even or odd
            if (m.getId() % 2 == 0) {
                evenIdCount = evenIdCount.add(BigInteger.ONE);
            } else {
                oddIdCount = oddIdCount.add(BigInteger.ONE);
            }

            // Getting the total cost of items in dollars from item i
            BigDecimal costInDollars = BigDecimal.valueOf((m.getCost()*m.getInventory())/100);
            // Add to total cost in cents
            totalCost = totalCost.add(costInDollars);

            // Calculate weighted inventory for item i
            BigDecimal totalWeightItem = BigDecimal.valueOf(m.getWeight()*m.getInventory());
            // Adding weighted inventory to total
            totalWeightedInventory = totalWeightedInventory.add(totalWeightItem);

            // Getting the total count of item i
            BigInteger totalInventoryItem = BigInteger.valueOf(m.getInventory());
            // Add to total inventory count
            totalInventory = totalInventory.add(totalInventoryItem);
        }

        // Calculating mean cost
        BigDecimal totalInventoryDec = new BigDecimal(totalInventory);
        meanCost = totalCost.divide(totalInventoryDec, RoundingMode.DOWN);


        return Stats.builder()
                .evenIds(evenIdCount)
                .oddIds(oddIdCount)
                .meanCost(meanCost)
                .weightedInventory(totalWeightedInventory)
                .totalInventory(totalInventory)
                .build();


    }
}
