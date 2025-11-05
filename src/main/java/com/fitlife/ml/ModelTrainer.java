package com.fitlife.ml;


import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48; 
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Random;


public class ModelTrainer {

    
    public static void main(String[] args) {
        try {
          
            String dataFilePath = "src/main/resources/data/workouts.arff";
            DataSource source = new DataSource(dataFilePath);
            Instances dataset = source.getDataSet();

           
            dataset.setClassIndex(dataset.numAttributes() - 1);

            System.out.println("Loaded dataset: " + dataFilePath);
            System.out.println("Number of instances: " + dataset.numInstances());
            System.out.println("------------------------------------");

           
            J48 classifier = new J48();
            classifier.buildClassifier(dataset);

            System.out.println("Classifier trained successfully.");
            System.out.println("------------------------------------");

           
            Evaluation eval = new Evaluation(dataset);

           
            eval.crossValidateModel(classifier, dataset, 10, new Random(1));

            System.out.println("--- MODEL EVALUATION (FOR REPORT) ---");
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toMatrixString());
            System.out.println("------------------------------------");


         
            String modelFilePath = "activity_model.model";
            SerializationHelper.write(modelFilePath, classifier);

            System.out.println("Trained model saved to: " + modelFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}