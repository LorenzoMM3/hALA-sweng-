package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class TipologiaScenario_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.TipologiaScenario instance) throws SerializationException {
    // Enum deserialization is handled via the instantiate method
  }
  
  public static com.hala.mywebapp.TipologiaScenario instantiate(SerializationStreamReader streamReader) throws SerializationException {
    int ordinal = streamReader.readInt();
    com.hala.mywebapp.TipologiaScenario[] values = com.hala.mywebapp.TipologiaScenario.values();
    assert (ordinal >= 0 && ordinal < values.length);
    return values[ordinal];
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.TipologiaScenario instance) throws SerializationException {
    assert (instance != null);
    streamWriter.writeInt(instance.ordinal());
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.TipologiaScenario_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.TipologiaScenario_FieldSerializer.deserialize(reader, (com.hala.mywebapp.TipologiaScenario)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.TipologiaScenario_FieldSerializer.serialize(writer, (com.hala.mywebapp.TipologiaScenario)object);
  }
  
}
