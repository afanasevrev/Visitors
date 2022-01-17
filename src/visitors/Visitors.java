/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import CvUtils.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author rewoly
 */
public class Visitors extends Application{
    static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    } 

    @Override
    public void start(Stage primaryStage) throws Exception {
        Mat img = Imgcodecs.imread("E:\\Java_Projects\\Visitors\\tolpa2.jpg");
        if (img.empty()) {
            System.out.println("Картинка не подгрузилась!"); 
            return;
        }
        CascadeClassifier face_detector = new CascadeClassifier();
        String path = "E:\\Java_Projects\\LIBRARIES\\opencv_3_3\\sources\\data\\haarcascades\\";
        String name = "haarcascade_frontalface_alt.xml";
        if (!face_detector.load(path + name)) {
            System.out.println("Не удалось загрузить классификатор " + name);
            return;
        }
        MatOfRect faces = new MatOfRect();
        face_detector.detectMultiScale(img, faces);
        for (Rect r: faces.toList()) {
           Imgproc.rectangle(img, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), CvUtils.COLOR_WHITE, 2);
        }
        CvUtilsFX.showImage(img, "Результат");
        img.release();
        System.out.println(faces.toList().size());
    }
}
