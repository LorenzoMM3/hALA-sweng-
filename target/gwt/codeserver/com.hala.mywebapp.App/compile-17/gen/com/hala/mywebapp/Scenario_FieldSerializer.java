package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Scenario_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.Scenario instance) throws SerializationException {
    instance.domandaCambioScenario = streamReader.readString();
    instance.sceltaGiocatore = streamReader.readBoolean();
    instance.testoScena = streamReader.readString();
    instance.tipologia = (com.hala.mywebapp.TipologiaScenario) streamReader.readObject();
    
  }
  
  public static com.hala.mywebapp.Scenario instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.Scenario();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.Scenario instance) throws SerializationException {
    streamWriter.writeString(instance.domandaCambioScenario);
    streamWriter.writeBoolean(instance.sceltaGiocatore);
    streamWriter.writeString(instance.testoScena);
    streamWriter.writeObject(instance.tipologia);
    
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
