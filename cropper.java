import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class cropper {
	
	
	public static final String ARG_HELP_KEY = "help"; 
	public static final String ARG_PATH_KEY = "-p";
	public static final String ARG_CROP_WIDTH_KEY = "-w";
	public static final String ARG_CROP_HEIGHT_KEY = "-h";
	
	
	public static void main(String[] args) throws IOException  {
		
		String arg_path = "";
		int arg_crop_width = 0;
		int arg_crop_height = 0;
		
		if (args.length<2) 
		{
			System.out.println("This program takes a photo and crops it in subimages. You must specify the following parameters: ");
			System.out.println("-p : path of the photo you wanna crop");
			System.out.println("-w : width of the subimages");
			System.out.println("-h : height of the subimages");
			System.out.print("Programmed by Mattia Papaccioli. Github: https://github.com/ElPettego");
			System.exit(0);
		}
		
		else
			for (int i = 0; i <args.length;i += 2)
			{
				String key = args [i];
				String value = args [i+1];
				
				switch (key)
				{
					case ARG_HELP_KEY : 
						System.out.println("This program takes a photo and crops it in subimages. You must specify the following parameters: ");
						System.out.println("-p : path of the photo you wanna crop");
						System.out.println("-w : width of the subimages");
						System.out.println("-h : height of the subimages");
						break;
					case ARG_PATH_KEY : arg_path = value; break;
					case ARG_CROP_WIDTH_KEY : arg_crop_width = Integer.parseInt(value); break;
					case ARG_CROP_HEIGHT_KEY : arg_crop_height= Integer.parseInt(value); break;
				}
			}
		
		
				
		int photo_width = 1 ;
		int photo_height = 1;
		
		int crop_width = arg_crop_width;
		int crop_height = arg_crop_height;
		
		int count = 0;
		
		String path = arg_path;
		
		File photo_to_crop = new File(path);
		
		BufferedImage temp = new BufferedImage(photo_width, photo_height, 1);
		
		temp = ImageIO.read(photo_to_crop);
		
		photo_width = temp.getWidth();
		
		photo_height = temp.getHeight();		
		
		for(int j = 0;j<photo_height;j=j+crop_height) {
			
			for(int i = 0;i<photo_width;i=i+crop_width) {
				
			count++;
				
			BufferedImage crop = temp.getSubimage(i,j,crop_width,crop_height);
			
			try 
			{
				RenderedImage bi = crop;
				String file_name = ""+ count+".png";
				File output = new File(file_name);
				ImageIO.write(bi , "png", output);
			} 
			catch (IOException e) {}
			}
		}
		
	}
			
}
