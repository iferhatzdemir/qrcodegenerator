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
import java.awt.Color;

	

public class FileP {
	  public static BufferedImage joinBufferedImage(BufferedImage img1,
    	      BufferedImage img2) {
    	    int offset = 5	;
    	    int width = img1.getWidth() + img2.getWidth()+ offset;
    	    int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
    	    BufferedImage newImage = new BufferedImage(width, height,
    	        BufferedImage.TYPE_INT_ARGB);
    	    Graphics2D g2 = newImage.createGraphics();
    	    Color oldColor = g2.getColor();
    	    g2.setPaint(Color.WHITE);
    	    g2.fillRect(0, 0, width, height);
    	    g2.setColor(oldColor);
    	    g2.drawImage(img1, null, 0, 0);
    	    g2.drawImage(img2, null, img1.getWidth()-20 + offset, 0);
    	    g2.dispose();
    	    return newImage;}
    public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(new File("..\\inventory\\codelists\\sayfa-1.txt"));
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
	        Font font = new Font("Consolas", Font.PLAIN, 50);
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
	        g2d.drawString(qrCodeData.substring(1, 4), 0, fm.getAscent()+25);
	        g2d.drawString(qrCodeData.substring(4, 7), 0, fm.getAscent()+70);
	        g2d.drawString(qrCodeData.substring(7, 10), 0, fm.getAscent()+120);
	        g2d.dispose();
	        ImageIO.write(img, "png", new File("C:\\Users\\User\\Desktop\\test\\"+qrCodeData.substring(1, 10)+".png"));
	        try {
	            
	            String filePath = "C:\\Users\\User\\Desktop\\test\\"+qrCodeData+".png";
	            String charset = "UTF-8"; // or "ISO-8859-1"
	            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
	            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	            BitMatrix matrix = new MultiFormatWriter().encode(
	                new String(qrCodeData.getBytes(charset), charset),
	                BarcodeFormat.QR_CODE, 200, 200, hintMap);
	            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
	                .lastIndexOf('.') + 1), new File(filePath));
	            System.out.println("QR Code image created successfully!");
	        } catch (Exception e) {
	            System.err.println(e);
	        }
	   	 BufferedImage img1 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\test\\"+qrCodeData+".png"));
	    	    BufferedImage img2 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\test\\"+qrCodeData.substring(1, 10)+".png"));
	    	    BufferedImage joinedImg = joinBufferedImage(img1, img2);
	    	    ImageIO.write(joinedImg, "png", new File("C:\\Users\\User\\Desktop\\test\\"+qrCodeData+".png"));
        }
    
    	 
            
        }}
    
