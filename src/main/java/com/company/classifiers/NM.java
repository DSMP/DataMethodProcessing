package com.company.classifiers;

import com.company.model.*;
import com.company.service.FisherMethod;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NM {
    private float procent;

    public float getProcent() {
        return procent;
    }

    public void setProcent(float procent) {
        this.procent = procent;
    }

    public double[] calculateVector(SimpleMatrix featureList) {
        double[] result = new double[featureList.numCols()];
        double[] result1 = new double[featureList.numCols()];
        for (int i = 0; i < featureList.numCols(); i++) {
            for (int j = 0; j < featureList.numRows(); j++) {
                result[j] = featureList.get(j, i);
            }

            for (int j = 0; j < result.length; j++) {
                result1[i] = result[j] / featureList.numRows();
            }
        }
        return result1;
    }

    public double calculateVector(double[] featureList) {
        double result = 0.0;
        for (int i = 0; i < featureList.length; i++) {
            result += featureList[i];
        }
        return result / featureList.length;
    }

    public double calculateTheDistanceForTheSample(double[] vector,
                                                   double[] coordinatesOfSample) {
        double result = 0.0;
        for (int i = 0; i < coordinatesOfSample.length; i++) {
            result += Math.pow(coordinatesOfSample[i] - vector[i], 2);
        }
        return Math.sqrt(result);
    }

    private String sampleSelectionForTheClassNM(ArrayList<NNmodel> listOfDistances) {
        ArrayList<Double> listOfDistancesDoubles = new ArrayList<>();
        for (NNmodel n : listOfDistances) {
            listOfDistancesDoubles.add(n.getDistance());
        }
        int index = listOfDistancesDoubles.indexOf(Collections.min(listOfDistancesDoubles));
        return listOfDistances.get(index).getFeature();
    }

    public Set<String> getNames(ArrayList<DataToTask3Model> arrayListTest) {
        Set<String> featureNamesInArray = new HashSet<>();
        for (int i = 0; i < arrayListTest.size(); i++) {
            featureNamesInArray.add(arrayListTest.get(i).getFeatureName());
        }
        return featureNamesInArray;
    }

    public SimpleMatrix convertToSimpleMatrix(ArrayList<DataToTask3Model> arrayListWithRecords) {
        double[][] matrix = new double[arrayListWithRecords.size()][];
        for (int i = 0; i < arrayListWithRecords.size(); i++) {
            matrix[i] = arrayListWithRecords.get(i).getFeatureList();
        }
        return new SimpleMatrix(matrix);
    }
    // arrayListTraining - zbór obiektow sklasyfikowanych, arrayListTest = zbior nie skalsyfikowanych, ale z wiadomą informacją do ktorej klasy należy
    public void validateNM(ArrayList<FeatureModel> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest)
    {
        ArrayList<NMAvgModelExt> testObjects = new ArrayList<>();
        ArrayList<NMAvgModel> vectorAvgsTrening = calcVectorfeaturesAvgs(arrayListTraining);
        for (int i = 0; i < arrayListTest.size(); i++) {
            NMAvgModelExt newClassifiedObject = classify(vectorAvgsTrening,arrayListTest.get(i));
            testObjects.add(newClassifiedObject);
            vectorAvgsTrening.forEach(nmAvgModel ->
            {
                if (nmAvgModel.getFeatureName().equals(newClassifiedObject.getFeatureName())) {
                    nmAvgModel.setCenterPointfeature(calcCenterClass(nmAvgModel.getCenterPointfeature(),newClassifiedObject.getCenterPointfeature()));
                }
            });
        }
        double percentage = 0.0;
        int goodClasifing = 0;
        for (int i = 0; i < testObjects.size(); i++) {
            if (testObjects.get(i).getFeatureName().equals(testObjects.get(i).getFeatureNameOld()))
            {
                goodClasifing++;
            }
        }
        percentage = (goodClasifing*100.0)/testObjects.size();
        procent = Float.parseFloat("" + percentage);
        System.out.println("NM result (%): " + percentage);
    }

    private NMAvgModelExt classify(ArrayList<NMAvgModel> vectorTraining, DataToTask3Model objectTest)
    {
        NMAvgModelExt nmAvgModelExt = new NMAvgModelExt(objectTest.getFeatureName(),null, objectTest.getFeatureList());
        double theMinestAvg = distanceBetweenPoints(vectorTraining.get(0).getCenterPointfeature(), objectTest.getFeatureList());
        int classIndex = 0;
        double tempMinAvg = 0.0;
        for (int i = 1; i < vectorTraining.size(); i++) {
            tempMinAvg = distanceBetweenPoints(vectorTraining.get(i).getCenterPointfeature(),objectTest.getFeatureList());
            if (tempMinAvg < theMinestAvg)
            {
                theMinestAvg = tempMinAvg;
                classIndex = i;
            }
        }
        nmAvgModelExt.setFeatureName(vectorTraining.get(classIndex).getFeatureName());
        return nmAvgModelExt;
    }

    public double distanceBetweenPoints(double[] pointA, double[] pointB)
    {
        double result = 0.0;
        for (int i = 0; i < pointA.length; i++) {
            result += Math.pow(pointB[i]-pointA[i],2);
        }
        return Math.sqrt(result);
    }

    private ArrayList<NMAvgModel> calcVectorfeaturesAvgs(ArrayList<FeatureModel> nModels)
    {
        ArrayList<NMAvgModel> result = new ArrayList<>();
        for (int i = 0; i < nModels.size(); i++) {
            result.add(new NMAvgModel(nModels.get(i).getFeatureName(), calcAvgPoint(nModels.get(i).getFeatureMatrix())));
        }
        return result;
    }

    private double[] calcAvgPoint(double[][] feature) {
        double[] avgPoint = new double[feature[0].length];
        for (int i = 0; i < feature[0].length; i++) {
            avgPoint[i] = feature[0][i];
        }
        for (int i = 1; i < feature.length; i++) {
            calcCenterClass(feature[i], avgPoint);
        }
        return avgPoint;
    }

    private double[] calcCenterClass(double[] doubles, double[] avgPoint) {
        for (int j = 0; j < doubles.length; j++) {
            avgPoint[j] = Math.abs(avgPoint[j]- doubles[j]);
        }
        return avgPoint;
    }

//    public void testNM(ArrayList<DataToTask3Model> arrayListTraining, ArrayList<DataToTask3Model> arrayListTest) {
//        ArrayList<DataToTask3Model> distancesSamples = new ArrayList<>();
//        ArrayList<NMmodel> gggg = new ArrayList<>();
//
//        ArrayList<Boolean> trueArray = new ArrayList<>();
//        ArrayList<Boolean> array = new ArrayList<>();
//        Set<String> featureNamesInArray = getNames(arrayListTest);
//
//        Object[] names = featureNamesInArray.toArray();
//
//        for (int i = 0; i < names.length; i++) {
//            ArrayList<DataToTask3Model> arr = new ArrayList<>();
//            for (int j = 0; j < arrayListTest.size(); j++) {
//                if (names[i] == arrayListTest.get(j).getFeatureName()) {
//                    arr.add(arrayListTest.get(i));
//                }
//            }
//            gggg.add(new NMmodel(arr.get(0).getFeatureName(), convertToSimpleMatrix(arr)));
//            double[][] matrix = new double[arr.size()][64];
//            for (int j = 0; j < arr.size(); j++) {
//                matrix[j] = arr.get(j).getFeatureList();
//                distancesSamples.add(new DataToTask3Model(arr.get(j).getFeatureName(), calculateVector(gggg.get(gggg.size() - 1).getVector())));
//            }
//        }
//
//        System.out.println(calculateVector(gggg.get(0).getVector()));

//        for (int i = 0; i < gggg.size() - 1; i++) {
//            for (int j = 0; j < arrayListTest.size() - 1; j++) {
//                double distance = calculateTheDistanceForTheSample(arrayListTraining.get(i).getFeatureList(),
//                        arrayListTest.get(j).getFeatureList());
//                distancesSamples.add(new NNmodel(distance, arrayListTest.get(j).getFeatureName()));
//            }
//            if (sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName())) {
//                trueArray.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//            } else {
//                array.add(sampleSelectionForTheClassNM(distancesSamples).equals(arrayListTraining.get(i).getFeatureName()));
//            }
//            distancesSamples.clear();
//        }
//
//
//        Integer a = trueArray.size();
//        Integer b = array.size();
//        float procent = a.floatValue() / b.floatValue();
//        setProcent(procent);
//        System.out.println("Wynik dla NM: " + procent * 100 + "%");
//    }
//
    //srednia z cech, srednia z kolumn
    private double[] calcAvgVector(double[][] matrixF)
    {
        double[] vectorResult = new double[matrixF[0].length];
        for (int i = 0; i < matrixF[0].length; i++) {
            for (int j = 0; j < matrixF.length; j++) {
                vectorResult[i] += matrixF[j][i];
            }
            vectorResult[i] /= matrixF.length;
        }
        return vectorResult;
    }

}
