package com.company.Adapters;

import com.company.model.DataToTask3Model;
import com.company.model.FeatureModel;

import java.util.ArrayList;

public class AdapterNM {
    ArrayList<FeatureModel> allData;
    ArrayList<FeatureModel> arrayListTraining;
    ArrayList<DataToTask3Model> arrayListTest;
    boolean isOutputPrepared = false;
    int mod;

    public AdapterNM(ArrayList<FeatureModel> allDatam, int mod) {
        arrayListTraining = new ArrayList<>();
        arrayListTest = new ArrayList<>();
        this.allData = allDatam;
        this.mod = mod;
    }

    public ArrayList<FeatureModel> getTrainingList()
    {
        preparedOutput(mod);
        return arrayListTraining;
    }

    public ArrayList<DataToTask3Model> getTestList() {
        preparedOutput(mod);
        return arrayListTest;
    }

    private void preparedOutput(int mod)
    {
        if (isOutputPrepared) return;
        for (FeatureModel featureModel: allData) {
            int index = 0;
            double[][] featureDataOut = new double[featureModel.getFeatureMatrix().length-(featureModel.getFeatureMatrix().length/mod)][64];
            for (int i = 0; i < featureModel.getFeatureMatrix().length; i++) {
                if ((i % mod) == 0)
                    arrayListTest.add(new DataToTask3Model(featureModel.getFeatureName(),featureModel.getFeatureMatrix()[i]));
                else
                {
                    for (int j = 0; j < 64; j++) {
                        featureDataOut[index][j] = featureModel.getFeatureMatrix()[i][j];
                    }
                    index++;
                }
            }
            arrayListTraining.add(new FeatureModel(featureModel.getFeatureName(),featureDataOut));
        }
        isOutputPrepared = true;
    }
}
