package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Storia_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.Storia instance) throws SerializationException {
    instance.creatore = (com.hala.mywebapp.Utente) streamReader.readObject();
    instance.iniziata = streamReader.readBoolean();
    instance.nome = streamReader.readString();
    instance.scenarioIniziale = (com.hala.mywebapp.Scenario) streamReader.readObject();
    
  }
  
  public static com.hala.mywebapp.Storia instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.Storia();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.Storia instance) throws SerializationException {
    streamWriter.writeObject(instance.creatore);
    streamWriter.writeBoolean(instance.iniziata);
    streamWriter.writeString(instance.nome);
    streamWriter.writeObject(instance.scenarioIniziale);
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.Storia_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.Storia_FieldSerializer.deserialize(reader, (com.hala.mywebapp.Storia)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.Storia_FieldSerializer.serialize(writer, (com.hala.mywebapp.Storia)object);
  }
  
}
