import com.escom.wad.model.EntidadFederativa;
import com.escom.wad.model.Municipio;
import com.escom.wad.model.dao.EntidadFederativaDAO;
import com.escom.wad.model.dao.MunicipioDAO;
import com.escom.wad.model.dto.EntidadFederativaDTO;
import com.escom.wad.model.dto.MunicipioDTO;
import java.io.File;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aarongarcia
 */
public class App {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
        // Path para estados
        StringBuilder path_estados = new StringBuilder();
	path_estados.append(".");
	path_estados.append(File.separator);
        path_estados.append("banco");
        path_estados.append(File.separator);
        path_estados.append("estados.json");
        
        // Path para municipios
        StringBuilder path_municipios = new StringBuilder();
	path_municipios.append(".");
	path_municipios.append(File.separator);
        path_municipios.append("banco");
        path_municipios.append(File.separator);
        path_municipios.append("municipios.json");
        
        // Control de ejecucion
        Boolean llenar_estados = Boolean.FALSE;
        Boolean llenar_municipios = Boolean.FALSE;
        
        ArrayList<String> nombres_estados = new ArrayList<>();
        
        // llenar estados
        if(llenar_estados){
            EntidadFederativaDAO dao = new EntidadFederativaDAO();
            EntidadFederativaDTO dto = new EntidadFederativaDTO();
            EntidadFederativa entidadFederativa = new EntidadFederativa();

            JSONParser jsonP = new JSONParser();

            try(FileReader reader = new FileReader(path_estados.toString())){
                Object obj = jsonP.parse(reader);
                JSONArray estados_lista = (JSONArray) obj;

                for(Integer i=1; i<=estados_lista.size(); i++){
                    entidadFederativa.setIdEntidadFederativa(i);
                    entidadFederativa.setAbreviatura((String) ((JSONObject)estados_lista.get(i-1)).get("clave"));
                    entidadFederativa.setNombre((String) ((JSONObject)estados_lista.get(i-1)).get("nombre"));
                    
                    nombres_estados.add(entidadFederativa.getNombre());

                    dto.setEntidad(entidadFederativa);
                    dao.create(dto);
                }

            }catch (FileNotFoundException e) {
                      e.printStackTrace();
                  } catch (IOException e) {
                      e.printStackTrace();
                  } catch (ParseException e) {
                      e.printStackTrace();
            }
        }
        
        
        // llenar municipios
        if(llenar_municipios){
            MunicipioDAO dao = new MunicipioDAO();
            MunicipioDTO dto = new MunicipioDTO();
            Municipio municipio = new Municipio();
            
            FileReader reader = new FileReader(path_municipios.toString());
            JSONParser jsonParser = new JSONParser();
            
            JSONObject objetos_json = ((JSONObject)jsonParser.parse(reader));
            
            for(String nombre_estado : nombres_estados){
                //System.out.println(((JSONArray)objetos_json.get(nombre_estado)).get(0));
                municipio.setIdEntidadFederativa(nombres_estados.indexOf(nombre_estado)+1);
                for(Object objeto_json_municipo : ((JSONArray)objetos_json.get(nombre_estado))){
                    municipio.setNombre((String) objeto_json_municipo);
                    municipio.setIdMunicipio(((JSONArray)objetos_json.get(nombre_estado)).indexOf(objeto_json_municipo)+1);

                    dto.setEntidad(municipio);
                    dao.create(dto);
                }
            }
        }
    }
}
