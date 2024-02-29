package com.hala.mywebapp;

public class ScriviStoria_ScriviStoriaUiBinderImpl_TemplateImpl implements com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl.Template {
  
  public com.google.gwt.safehtml.shared.SafeHtml html1(java.lang.String arg0) {
    StringBuilder sb = new java.lang.StringBuilder();
    sb.append("<div class='formPanel'> <label id='titoloStoria'>Scrivi il titolo della tua storia</label> <span id='");
    sb.append(com.google.gwt.safehtml.shared.SafeHtmlUtils.htmlEscape(arg0));
    sb.append("'></span> </div>");
return new com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml(sb.toString());
}
}
