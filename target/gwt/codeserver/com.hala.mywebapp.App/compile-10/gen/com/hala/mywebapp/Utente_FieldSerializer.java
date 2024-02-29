package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Utente_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.hala.mywebapp.Utente instance) throws SerializationException {
    instance.isLogged = streamReader.readBoolean();
    instance.password = streamReader.readString();
    instance.username = streamReader.readString();
    
  }
  
  public static com.hala.mywebapp.Utente instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new com.hala.mywebapp.Utente();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.hala.mywebapp.Utente instance) throws SerializationException {
    streamWriter.writeBoolean(instance.isLogged);
    streamWriter.writeString(instance.password);
    streamWriter.writeString(instance.username);
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.hala.mywebapp.Utente_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.hala.mywebapp.Utente_FieldSerializer.deserialize(reader, (com.hala.mywebapp.Utente)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.hala.mywebapp.Utente_FieldSerializer.serialize(writer, (com.hala.mywebapp.Utente)object);
  }
  
}
