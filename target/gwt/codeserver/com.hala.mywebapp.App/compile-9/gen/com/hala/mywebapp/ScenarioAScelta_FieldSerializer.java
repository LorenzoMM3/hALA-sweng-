package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class ScenarioAScelta_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.ScenarioAScelta instance) throws SerializationException {
    instance.opzioniScelta = (java.util.HashMap) streamReader.readObject();
    instance.tipologiaScenario = (com.hala.mywebapp.TipologiaScenario) streamReader.readObject();
    
    com.hala.mywebapp.Scenario_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static com.hala.mywebapp.ScenarioAScelta instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.ScenarioAScelta();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.ScenarioAScelta instance) throws SerializationException {
    streamWriter.writeObject(instance.opzioniScelta);
    streamWriter.writeObject(instance.tipologiaScenario);
    
    com.hala.mywebapp.Scenario_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.ScenarioAScelta_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.ScenarioAScelta_FieldSerializer.deserialize(reader, (com.hala.mywebapp.ScenarioAScelta)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.ScenarioAScelta_FieldSerializer.serialize(writer, (com.hala.mywebapp.ScenarioAScelta)object);
  }
  
}
