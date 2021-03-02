package array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

	

public class FileP {
	  public static BufferedImage joinBufferedImage(BufferedImage img1,
    	      BufferedImage img2) {
    	    int offset = 5	;
    	    int width = img1.getWidth() + img2.getWidth()+ offset;
    	    int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
    	    BufferedImage newImage = new BufferedImage(85,50,
    	        BufferedImage.TYPE_INT_ARGB);
    	    Graphics2D g2 = newImage.createGraphics();
    	    Color oldColor = g2.getColor();
    	    g2.setPaint(Color.WHITE);
    	    g2.fillRect(0, 0, width, height);
    	    g2.setColor(oldColor);
    	    g2.drawImage(img1, null, -10,-10);
    	    g2.drawImage(img2, null, img1.getWidth()-20 + offset, 0);
    	    g2.dispose();
    	    return newImage;}
    public static void main(String[] args) throws IOException, DocumentException {
        Scanner read = new Scanner(new File("src\\codelist\\sayfa-19.txt")); /*QR kodun oluþturuðu txtin ismi deðiþtirilmesi gerek */
        ArrayList<String> myList = new ArrayList<String>();
        while(read.hasNextLine()) {
            myList.add(read.nextLine());
        }
       System.out.println(myList);
      for(int i=0; i < myList.size(); i++)
        {
        	String qrCodeData = myList.get(i);
			
	    	BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = img.createGraphics();
	        Font font = new Font("Consolas", Font.PLAIN, 17);
	        g2d.setFont(font);
	        FontMetrics fm = g2d.getFontMetrics();
	        int width = fm.stringWidth(qrCodeData);
	        int height = fm.getHeight();
	        g2d.dispose();
	        img = new BufferedImage(width/3, height*3, BufferedImage.TYPE_INT_ARGB);
	        g2d = img.createGraphics();
	        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
	            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
	            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
	            RenderingHints.VALUE_DITHER_ENABLE);
	        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
	            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	            RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
	            RenderingHints.VALUE_STROKE_PURE);
	        g2d.setFont(font);
	        fm = g2d.getFontMetrics();
	        g2d.setColor(Color.black);
	        g2d.drawString(qrCodeData.substring(1, 4), 0, fm.getAscent());
	        g2d.drawString(qrCodeData.substring(4, 7), 0, fm.getAscent()+15);
	        g2d.drawString(qrCodeData.substring(7, 10), 0, fm.getAscent()+30);
	        g2d.dispose();
	        ImageIO.write(img, "png", new File("src\\QRcodes\\"+qrCodeData.substring(1, 10)+".png"));
	        try {
	            
	            String filePath = "src\\QRcodes\\"+qrCodeData+".png";
	            String charset = "UTF-8"; // or "ISO-8859-1"
	            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
	            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	            BitMatrix matrix = new MultiFormatWriter().encode(
	                new String(qrCodeData.getBytes(charset), charset),
	                BarcodeFormat.QR_CODE, 70, 70, hintMap);
	            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
	                .lastIndexOf('.') + 1), new File(filePath));
	            System.out.println(qrCodeData+" "+"QR Code image created successfully!");
	        } catch (Exception e) {
	            System.err.println(e);
	        }
	   	 BufferedImage img1 = ImageIO.read(new File("src\\QRcodes\\"+qrCodeData+".png"));
	    	    BufferedImage img2 = ImageIO.read(new File("src\\QRcodes\\"+qrCodeData.substring(1, 10)+".png"));
	    	    BufferedImage joinedImg = joinBufferedImage(img1, img2);
	    	    ImageIO.write(joinedImg, "png", new File("src\\QRcodes\\"+qrCodeData+".png"));
        }
      
  	Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("src\\pdf\\85.pdf")); /*pdf oluþturulurken deðiþtirilmesi gerek*/
    document.open();
   
    while(read.hasNextLine()) {
        myList.add(read.nextLine());}
    
  float BASLANGIC_NOKTASI_YATAY=13.322835f ;  
   	float BASLANGIC_NOKTASI_DUSEY=26.929134f ;
   	float ETIKET_GENISLIK=63.212598f;
   	float ETIKET_YUKSEKLIK=34.015748f;
   	float ETIKET_YATAY_ARALIK=9.92126f;
   	float ETIKET_DUSEY_ARALIK=5.669291f;
   	float GUNCEL_KONUM_YATAY = 0.0F;
   	float GUNCEL_KONUM_DUSEY = 0.0F;
   	int ETIKET_SUTUN_ADEDI = 8;
   	int ETIKET_SATIR_ADEDI = 20;	
   	GUNCEL_KONUM_YATAY = BASLANGIC_NOKTASI_YATAY;
   	GUNCEL_KONUM_DUSEY = BASLANGIC_NOKTASI_DUSEY;

    for(int i=0; i < ETIKET_SATIR_ADEDI; i++)
    {     	        System.out.println(i);

    	  for(int j=0; j < ETIKET_SUTUN_ADEDI; j++)
	        { 
  	        System.out.println(j);

    	String qrCodeData = myList.get((8*i)+j);
    
   Image img2 = Image.getInstance("src\\QRcodes\\"+qrCodeData+".png");
 img2.scaleToFit(ETIKET_GENISLIK,ETIKET_YUKSEKLIK);
    img2.setAbsolutePosition(GUNCEL_KONUM_YATAY, GUNCEL_KONUM_DUSEY);
    document.add(img2);
   	GUNCEL_KONUM_YATAY = GUNCEL_KONUM_YATAY + ETIKET_GENISLIK + ETIKET_YATAY_ARALIK;
   	}
    		GUNCEL_KONUM_YATAY = BASLANGIC_NOKTASI_YATAY;
   	GUNCEL_KONUM_DUSEY = GUNCEL_KONUM_DUSEY + ETIKET_YUKSEKLIK + ETIKET_DUSEY_ARALIK;


  
    }
    
    document.close();
    System.out.println("Done");
    
    	 
            
        }}
    
