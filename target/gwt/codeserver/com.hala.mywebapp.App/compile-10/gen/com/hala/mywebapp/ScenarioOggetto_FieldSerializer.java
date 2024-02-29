package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class ScenarioOggetto_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.ScenarioOggetto instance) throws SerializationException {
    instance.oggettoNecessario = streamReader.readString();
    
    com.hala.mywebapp.Scenario_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static com.hala.mywebapp.ScenarioOggetto instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.ScenarioOggetto();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.ScenarioOggetto instance) throws SerializationException {
    streamWriter.writeString(instance.oggettoNecessario);
    
    com.hala.mywebapp.Scenario_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.ScenarioOggetto_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.ScenarioOggetto_FieldSerializer.deserialize(reader, (com.hala.mywebapp.ScenarioOggetto)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.ScenarioOggetto_FieldSerializer.serialize(writer, (com.hala.mywebapp.ScenarioOggetto)object);
  }
  
}
