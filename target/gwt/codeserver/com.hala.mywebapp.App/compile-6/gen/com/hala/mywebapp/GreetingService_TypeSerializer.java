package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.impl.TypeHandler;
import java.util.HashMap;
import java.util.Map;
import com.google.gwt.core.client.GwtScriptOnly;

public class GreetingService_TypeSerializer extends com.google.gwt.user.client.rpc.impl.SerializerBase {
  private static final MethodMap methodMapNative;
  private static final JsArrayString signatureMapNative;
  
  static {
    methodMapNative = loadMethodsNative();
    signatureMapNative = loadSignaturesNative();
  }
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native MethodMap loadMethodsNative() /*-{
    var result = {};
    result["com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533"] = [
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;)
      ];
    
    result["com.google.gwt.user.client.rpc.RpcTokenException/2345075298"] = [
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/RpcTokenException;),
      ];
    
    result["com.google.gwt.user.client.rpc.XsrfToken/4254043109"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/XsrfToken;)
      ];
    
    result["com.hala.mywebapp.Scenario/2299152811"] = [
        ,
        ,
        @com.hala.mywebapp.Scenario_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/Scenario;)
      ];
    
    result["com.hala.mywebapp.ScenarioIndovinello/2313063543"] = [
        ,
        ,
        @com.hala.mywebapp.ScenarioIndovinello_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/ScenarioIndovinello;)
      ];
    
    result["com.hala.mywebapp.ScenarioOggetto/3253565822"] = [
        ,
        ,
        @com.hala.mywebapp.ScenarioOggetto_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/ScenarioOggetto;)
      ];
    
    result["com.hala.mywebapp.Storia/702985664"] = [
        ,
        ,
        @com.hala.mywebapp.Storia_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/Storia;)
      ];
    
    result["com.hala.mywebapp.TipologiaScenario/3725881314"] = [
        ,
        ,
        @com.hala.mywebapp.TipologiaScenario_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/TipologiaScenario;)
      ];
    
    result["com.hala.mywebapp.Utente/3312155184"] = [
        @com.hala.mywebapp.Utente_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.hala.mywebapp.Utente_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/hala/mywebapp/Utente;),
        @com.hala.mywebapp.Utente_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/Utente;)
      ];
    
    result["java.lang.String/2004016611"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/String;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/String;)
      ];
    
    return result;
  }-*/;
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native JsArrayString loadSignaturesNative() /*-{
    var result = [];
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException::class)] = "com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.RpcTokenException::class)] = "com.google.gwt.user.client.rpc.RpcTokenException/2345075298";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.google.gwt.user.client.rpc.XsrfToken::class)] = "com.google.gwt.user.client.rpc.XsrfToken/4254043109";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Scenario::class)] = "com.hala.mywebapp.Scenario/2299152811";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.ScenarioIndovinello::class)] = "com.hala.mywebapp.ScenarioIndovinello/2313063543";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.ScenarioOggetto::class)] = "com.hala.mywebapp.ScenarioOggetto/3253565822";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Storia::class)] = "com.hala.mywebapp.Storia/702985664";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.TipologiaScenario::class)] = "com.hala.mywebapp.TipologiaScenario/3725881314";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Utente::class)] = "com.hala.mywebapp.Utente/3312155184";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.lang.String::class)] = "java.lang.String/2004016611";
    return result;
  }-*/;
  
  public GreetingService_TypeSerializer() {
    super(null, methodMapNative, null, signatureMapNative);
  }
  
}
