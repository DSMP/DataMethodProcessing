package com.company.Selectors;

import com.company.model.RecordData;
import com.company.service.FileReaderService;

import java.io.IOException;
import java.util.ArrayList;

public class SFS {
    FileReaderService fileReaderService;
    public SFS() {
        fileReaderService = new FileReaderService();
        try {
            fileReaderService.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<RecordData> data = fileReaderService.getRecordDataArrayList();
    }
}
