package cycling;

import java.io.*;


public class Serialiser implements java.io.Serializable{
        
    //create an object of the implementation to save
    BadCyclingPortalImpl impl = new BadCyclingPortalImpl(); 


    public void Serialising()
    {
        
    }

    /**
     * 
     * @throws IOException
     * 
     * A method to save the implementation of the interface
     */
    public void saveFile(String fileName) throws IOException
    {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(impl);
            out.close();
            file.close();
        } catch (IOException e) {
            throw e;
        }
    }
    
    public void eraseFile() 
    {

    }

    public void loadFile(String fileName) throws IOException, ClassNotFoundException
    {   
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            impl = (BadCyclingPortalImpl) in.readObject();
            in.close();
            file.close();

        } catch (Exception e) {
            throw e;
        }
    }

}