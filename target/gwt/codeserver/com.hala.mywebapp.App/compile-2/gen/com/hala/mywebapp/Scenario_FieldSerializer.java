package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Scenario_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.Scenario instance) throws SerializationException {
    instance.domandaCambioScenario = streamReader.readString();
    instance.nomeStoria = streamReader.readString();
    instance.precedente = (java.util.ArrayList) streamReader.readObject();
    instance.successivo = (java.util.ArrayList) streamReader.readObject();
    instance.testoScena = streamReader.readString();
    instance.tipologiaScenario = (com.hala.mywebapp.TipologiaScenario) streamReader.readObject();
    
  }
  
  public static com.hala.mywebapp.Scenario instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.Scenario();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.Scenario instance) throws SerializationException {
    streamWriter.writeString(instance.domandaCambioScenario);
    streamWriter.writeString(instance.nomeStoria);
    streamWriter.writeObject(instance.precedente);
    streamWriter.writeObject(instance.successivo);
    streamWriter.writeString(instance.testoScena);
    streamWriter.writeObject(instance.tipologiaScenario);
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.Scenario_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.Scenario_FieldSerializer.deserialize(reader, (com.hala.mywebapp.Scenario)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.Scenario_FieldSerializer.serialize(writer, (com.hala.mywebapp.Scenario)object);
  }
  
}
