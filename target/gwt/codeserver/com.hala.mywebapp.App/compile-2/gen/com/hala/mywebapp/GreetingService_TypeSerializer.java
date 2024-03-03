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
    
    result["com.hala.mywebapp.Scenario/1122198369"] = [
        ,
        ,
        @com.hala.mywebapp.Scenario_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/Scenario;)
      ];
    
    result["com.hala.mywebapp.ScenarioAScelta/2177547446"] = [
        ,
        ,
        @com.hala.mywebapp.ScenarioAScelta_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/ScenarioAScelta;)
      ];
    
    result["com.hala.mywebapp.ScenarioIndovinello/583431578"] = [
        ,
        ,
        @com.hala.mywebapp.ScenarioIndovinello_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/ScenarioIndovinello;)
      ];
    
    result["com.hala.mywebapp.Storia/702985664"] = [
        ,
        ,
        @com.hala.mywebapp.Storia_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/hala/mywebapp/Storia;)
      ];
    
    result["com.hala.mywebapp.TipologiaScenario/2870396318"] = [
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
    
    result["java.util.HashMap/1797211028"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.HashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/HashMap;)
      ];
    
    result["java.util.LinkedHashMap/3008245022"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.LinkedHashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/LinkedHashMap;)
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
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Scenario::class)] = "com.hala.mywebapp.Scenario/1122198369";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.ScenarioAScelta::class)] = "com.hala.mywebapp.ScenarioAScelta/2177547446";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.ScenarioIndovinello::class)] = "com.hala.mywebapp.ScenarioIndovinello/583431578";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Storia::class)] = "com.hala.mywebapp.Storia/702985664";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.TipologiaScenario::class)] = "com.hala.mywebapp.TipologiaScenario/2870396318";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@com.hala.mywebapp.Utente::class)] = "com.hala.mywebapp.Utente/3312155184";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.lang.String::class)] = "java.lang.String/2004016611";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.HashMap::class)] = "java.util.HashMap/1797211028";
    result[@javaemul.internal.HashCodes::getObjectIdentityHashCode(*)(@java.util.LinkedHashMap::class)] = "java.util.LinkedHashMap/3008245022";
    return result;
  }-*/;
  
  public GreetingService_TypeSerializer() {
    super(null, methodMapNative, null, signatureMapNative);
  }
  
}
