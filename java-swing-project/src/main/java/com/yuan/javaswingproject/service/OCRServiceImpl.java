package com.yuan.javaswingproject.service;


import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import org.springframework.stereotype.Service;

@Service
public class OCRServiceImpl {
    public String ocr(String imgPath) {
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V3);
        OcrResult ocrResult = engine.runOcr(imgPath);
        return ocrResult.getStrRes().trim();
    }
}
