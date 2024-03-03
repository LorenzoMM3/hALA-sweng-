// .ui.xml template last modified: 1709460274235
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

public class Collegamenti_CollegamentiUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.hala.mywebapp.Collegamenti>, com.hala.mywebapp.Collegamenti.CollegamentiUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Setta scenario iniziale")
    SafeHtml html1();
     
    @Template("Setta come precedente")
    SafeHtml html2();
     
    @Template("Setta come successivo")
    SafeHtml html3();
     
    @Template("Passa allo scenario successivo")
    SafeHtml html4();
     
    @Template("Back")
    SafeHtml html5();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.hala.mywebapp.Collegamenti owner) {


    return new Widgets(owner).get_pagina();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.hala.mywebapp.Collegamenti owner;


    public Widgets(final com.hala.mywebapp.Collegamenti owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }
    SafeHtml template_html2() {
      return template.html2();
    }
    SafeHtml template_html3() {
      return template.html3();
    }
    SafeHtml template_html4() {
      return template.html4();
    }
    SafeHtml template_html5() {
      return template.html5();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.hala.mywebapp.Collegamenti_CollegamentiUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.hala.mywebapp.Collegamenti_CollegamentiUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.hala.mywebapp.Collegamenti_CollegamentiUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.hala.mywebapp.Collegamenti_CollegamentiUiBinderImpl_GenBundle) GWT.create(com.hala.mywebapp.Collegamenti_CollegamentiUiBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for pagina called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HorizontalPanel get_pagina() {
      return build_pagina();
    }
    private com.google.gwt.user.client.ui.HorizontalPanel build_pagina() {
      // Creation section.
      final com.google.gwt.user.client.ui.HorizontalPanel pagina = (com.google.gwt.user.client.ui.HorizontalPanel) GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
      // Setup section.
      pagina.add(get_ScenariDaCollegare());
      pagina.add(get_ScenarioInizialePanel());
      pagina.add(get_CollegamentiPanel());
      pagina.add(get_ScenarioSucc());
      pagina.add(get_backButton());
      pagina.setSpacing(100);

      this.owner.pagina = pagina;

      return pagina;
    }

    /**
     * Getter for ScenariDaCollegare called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_ScenariDaCollegare() {
      return build_ScenariDaCollegare();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_ScenariDaCollegare() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel ScenariDaCollegare = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      ScenariDaCollegare.add(get_MenuLateraleScenari());
      ScenariDaCollegare.add(get_listaScenari());
      ScenariDaCollegare.setSpacing(30);

      return ScenariDaCollegare;
    }

    /**
     * Getter for MenuLateraleScenari called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_MenuLateraleScenari() {
      return build_MenuLateraleScenari();
    }
    private com.google.gwt.user.client.ui.Label build_MenuLateraleScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label MenuLateraleScenari = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      MenuLateraleScenari.setText("Menu Scenari:");

      this.owner.MenuLateraleScenari = MenuLateraleScenari;

      return MenuLateraleScenari;
    }

    /**
     * Getter for listaScenari called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_listaScenari() {
      return build_listaScenari();
    }
    private com.google.gwt.user.client.ui.ListBox build_listaScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox listaScenari = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.

      this.owner.listaScenari = listaScenari;

      return listaScenari;
    }

    /**
     * Getter for ScenarioInizialePanel called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_ScenarioInizialePanel() {
      return build_ScenarioInizialePanel();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_ScenarioInizialePanel() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel ScenarioInizialePanel = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      ScenarioInizialePanel.add(get_scenarioIniziale());
      ScenarioInizialePanel.add(get_menuScenari());
      ScenarioInizialePanel.add(get_buttonSettaScenarioIniziale());
      ScenarioInizialePanel.add(get_messageLabel());
      ScenarioInizialePanel.setSpacing(30);

      this.owner.ScenarioInizialePanel = ScenarioInizialePanel;

      return ScenarioInizialePanel;
    }

    /**
     * Getter for scenarioIniziale called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_scenarioIniziale() {
      return build_scenarioIniziale();
    }
    private com.google.gwt.user.client.ui.Label build_scenarioIniziale() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label scenarioIniziale = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      scenarioIniziale.setText("Scegli lo scenario iniziale:");

      this.owner.scenarioIniziale = scenarioIniziale;

      return scenarioIniziale;
    }

    /**
     * Getter for menuScenari called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuScenari() {
      return build_menuScenari();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuScenari = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.

      this.owner.menuScenari = menuScenari;

      return menuScenari;
    }

    /**
     * Getter for buttonSettaScenarioIniziale called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_buttonSettaScenarioIniziale() {
      return build_buttonSettaScenarioIniziale();
    }
    private com.google.gwt.user.client.ui.Button build_buttonSettaScenarioIniziale() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button buttonSettaScenarioIniziale = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      buttonSettaScenarioIniziale.setHTML(template_html1().asString());

      this.owner.buttonSettaScenarioIniziale = buttonSettaScenarioIniziale;

      return buttonSettaScenarioIniziale;
    }

    /**
     * Getter for messageLabel called 1 times. Type: DEFAULT. Build precedence: 3.
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

    /**
     * Getter for CollegamentiPanel called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_CollegamentiPanel() {
      return build_CollegamentiPanel();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_CollegamentiPanel() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel CollegamentiPanel = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      CollegamentiPanel.add(get_f_Label1());
      CollegamentiPanel.add(get_testoScenarioDaCollegare());
      CollegamentiPanel.add(get_scenariConCuiCollegare());
      CollegamentiPanel.add(get_settaPrecedente());
      CollegamentiPanel.add(get_settaSuccessivo());
      CollegamentiPanel.setSpacing(30);

      this.owner.CollegamentiPanel = CollegamentiPanel;

      return CollegamentiPanel;
    }

    /**
     * Getter for f_Label1 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label1() {
      return build_f_Label1();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label1() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label1 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label1.setText("Collega il seguente scenario:");

      return f_Label1;
    }

    /**
     * Getter for testoScenarioDaCollegare called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_testoScenarioDaCollegare() {
      return build_testoScenarioDaCollegare();
    }
    private com.google.gwt.user.client.ui.Label build_testoScenarioDaCollegare() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label testoScenarioDaCollegare = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.testoScenarioDaCollegare = testoScenarioDaCollegare;

      return testoScenarioDaCollegare;
    }

    /**
     * Getter for scenariConCuiCollegare called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_scenariConCuiCollegare() {
      return build_scenariConCuiCollegare();
    }
    private com.google.gwt.user.client.ui.ListBox build_scenariConCuiCollegare() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox scenariConCuiCollegare = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.

      this.owner.scenariConCuiCollegare = scenariConCuiCollegare;

      return scenariConCuiCollegare;
    }

    /**
     * Getter for settaPrecedente called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_settaPrecedente() {
      return build_settaPrecedente();
    }
    private com.google.gwt.user.client.ui.Button build_settaPrecedente() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button settaPrecedente = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      settaPrecedente.setHTML(template_html2().asString());

      this.owner.settaPrecedente = settaPrecedente;

      return settaPrecedente;
    }

    /**
     * Getter for settaSuccessivo called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_settaSuccessivo() {
      return build_settaSuccessivo();
    }
    private com.google.gwt.user.client.ui.Button build_settaSuccessivo() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button settaSuccessivo = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      settaSuccessivo.setHTML(template_html3().asString());

      this.owner.settaSuccessivo = settaSuccessivo;

      return settaSuccessivo;
    }

    /**
     * Getter for ScenarioSucc called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Button get_ScenarioSucc() {
      return build_ScenarioSucc();
    }
    private com.google.gwt.user.client.ui.Button build_ScenarioSucc() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button ScenarioSucc = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      ScenarioSucc.setHTML(template_html4().asString());

      this.owner.ScenarioSucc = ScenarioSucc;

      return ScenarioSucc;
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
      backButton.setHTML(template_html5().asString());

      this.owner.backButton = backButton;

      return backButton;
    }
  }
}
