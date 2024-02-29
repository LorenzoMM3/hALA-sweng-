// .ui.xml template last modified: 1709224623451
package com.hala.mywebapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class Logout_LogoutUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.hala.mywebapp.Logout>, com.hala.mywebapp.Logout.LogoutUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Logout")
    SafeHtml html1();
     
    @Template("Back")
    SafeHtml html2();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.hala.mywebapp.Logout owner) {


    return new Widgets(owner).get_f_VerticalPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.hala.mywebapp.Logout owner;


    public Widgets(final com.hala.mywebapp.Logout owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
    }

    SafeHtml template_html1() {
      return template.html1();
    }
    SafeHtml template_html2() {
      return template.html2();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenBundle) GWT.create(com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 1 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenCss_style get_style() {
      return build_style();
    }
    private com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenCss_style build_style() {
      // Creation section.
      final com.hala.mywebapp.Logout_LogoutUiBinderImpl_GenCss_style style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();

      return style;
    }

    /**
     * Getter for f_VerticalPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_f_VerticalPanel1() {
      return build_f_VerticalPanel1();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_f_VerticalPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel f_VerticalPanel1 = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      f_VerticalPanel1.add(get_f_Label2());
      f_VerticalPanel1.add(get_sendButton());
      f_VerticalPanel1.add(get_backButton());
      f_VerticalPanel1.add(get_messageLabel());

      return f_VerticalPanel1;
    }

    /**
     * Getter for f_Label2 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label2() {
      return build_f_Label2();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label2() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label2 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label2.setText("Sei sicuro di voler eseguire il Logout?");

      return f_Label2;
    }

    /**
     * Getter for sendButton called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Button get_sendButton() {
      return build_sendButton();
    }
    private com.google.gwt.user.client.ui.Button build_sendButton() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button sendButton = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      sendButton.setHTML(template_html1().asString());
      sendButton.setStyleName("" + get_style().Logout() + "");

      this.owner.sendButton = sendButton;

      return sendButton;
    }

    /**
     * Getter for backButton called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Button get_backButton() {
      return build_backButton();
    }
    private com.google.gwt.user.client.ui.Button build_backButton() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button backButton = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      backButton.setHTML(template_html2().asString());

      this.owner.backButton = backButton;

      return backButton;
    }

    /**
     * Getter for messageLabel called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Label get_messageLabel() {
      return build_messageLabel();
    }
    private com.google.gwt.user.client.ui.Label build_messageLabel() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label messageLabel = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.messageLabel = messageLabel;

      return messageLabel;
    }
  }
}
