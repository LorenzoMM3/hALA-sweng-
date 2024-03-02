// .ui.xml template last modified: 1709398054998
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
     
    @Template("Setta scome precedente")
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


    return new Widgets(owner).get_f_HorizontalPanel1();
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
     * Getter for f_HorizontalPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HorizontalPanel get_f_HorizontalPanel1() {
      return build_f_HorizontalPanel1();
    }
    private com.google.gwt.user.client.ui.HorizontalPanel build_f_HorizontalPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HorizontalPanel f_HorizontalPanel1 = (com.google.gwt.user.client.ui.HorizontalPanel) GWT.create(com.google.gwt.user.client.ui.HorizontalPanel.class);
      // Setup section.
      f_HorizontalPanel1.add(get_Scenari());
      f_HorizontalPanel1.add(get_vpCollegamenti());
      f_HorizontalPanel1.add(get_ScenarioSucc());
      f_HorizontalPanel1.add(get_messageLabel());
      f_HorizontalPanel1.add(get_backButton());
      f_HorizontalPanel1.setSpacing(100);

      return f_HorizontalPanel1;
    }

    /**
     * Getter for Scenari called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_Scenari() {
      return build_Scenari();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_Scenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel Scenari = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      Scenari.add(get_ScenariCollegati());
      Scenari.add(get_ScenariDaCollegare());
      Scenari.setSpacing(30);

      this.owner.Scenari = Scenari;

      return Scenari;
    }

    /**
     * Getter for ScenariCollegati called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_ScenariCollegati() {
      return build_ScenariCollegati();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_ScenariCollegati() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel ScenariCollegati = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      ScenariCollegati.setSpacing(30);

      this.owner.ScenariCollegati = ScenariCollegati;

      return ScenariCollegati;
    }

    /**
     * Getter for ScenariDaCollegare called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_ScenariDaCollegare() {
      return build_ScenariDaCollegare();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_ScenariDaCollegare() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel ScenariDaCollegare = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      ScenariDaCollegare.add(get_menuScenariSinistra());
      ScenariDaCollegare.setSpacing(30);

      this.owner.ScenariDaCollegare = ScenariDaCollegare;

      return ScenariDaCollegare;
    }

    /**
     * Getter for menuScenariSinistra called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuScenariSinistra() {
      return build_menuScenariSinistra();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuScenariSinistra() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuScenariSinistra = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      menuScenariSinistra.addItem("Scenario uno");
      menuScenariSinistra.addItem("Scenario due");

      this.owner.menuScenariSinistra = menuScenariSinistra;

      return menuScenariSinistra;
    }

    /**
     * Getter for vpCollegamenti called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_vpCollegamenti() {
      return build_vpCollegamenti();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_vpCollegamenti() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel vpCollegamenti = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      vpCollegamenti.add(get_ScenarioInizialePanel());
      vpCollegamenti.add(get_altriScenariPanel());
      vpCollegamenti.add(get_buttonSuccessivo());
      vpCollegamenti.setSpacing(30);

      this.owner.vpCollegamenti = vpCollegamenti;

      return vpCollegamenti;
    }

    /**
     * Getter for ScenarioInizialePanel called 1 times. Type: DEFAULT. Build precedence: 3.
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
      ScenarioInizialePanel.add(get_buttonIniziale());
      ScenarioInizialePanel.add(get_testoScenarioIniziale());
      ScenarioInizialePanel.add(get_menuAltriScenari());
      ScenarioInizialePanel.setSpacing(30);

      this.owner.ScenarioInizialePanel = ScenarioInizialePanel;

      return ScenarioInizialePanel;
    }

    /**
     * Getter for scenarioIniziale called 1 times. Type: DEFAULT. Build precedence: 4.
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
     * Getter for menuScenari called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuScenari() {
      return build_menuScenari();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuScenari = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      menuScenari.addItem("Scenario a scelta");
      menuScenari.addItem("Scenario con indovinello");

      this.owner.menuScenari = menuScenari;

      return menuScenari;
    }

    /**
     * Getter for buttonIniziale called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Button get_buttonIniziale() {
      return build_buttonIniziale();
    }
    private com.google.gwt.user.client.ui.Button build_buttonIniziale() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button buttonIniziale = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      buttonIniziale.setHTML(template_html1().asString());

      this.owner.buttonIniziale = buttonIniziale;

      return buttonIniziale;
    }

    /**
     * Getter for testoScenarioIniziale called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Label get_testoScenarioIniziale() {
      return build_testoScenarioIniziale();
    }
    private com.google.gwt.user.client.ui.Label build_testoScenarioIniziale() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label testoScenarioIniziale = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.testoScenarioIniziale = testoScenarioIniziale;

      return testoScenarioIniziale;
    }

    /**
     * Getter for menuAltriScenari called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuAltriScenari() {
      return build_menuAltriScenari();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuAltriScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuAltriScenari = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      menuAltriScenari.addItem("Scenario a scelta");
      menuAltriScenari.addItem("Scenario con indovinello");

      this.owner.menuAltriScenari = menuAltriScenari;

      return menuAltriScenari;
    }

    /**
     * Getter for altriScenariPanel called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_altriScenariPanel() {
      return build_altriScenariPanel();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_altriScenariPanel() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel altriScenariPanel = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      altriScenariPanel.add(get_altriScenari());
      altriScenariPanel.add(get_testoScenario1());
      altriScenariPanel.add(get_nodo2());
      altriScenariPanel.add(get_buttonPrecedente());
      altriScenariPanel.setSpacing(30);

      this.owner.altriScenariPanel = altriScenariPanel;

      return altriScenariPanel;
    }

    /**
     * Getter for altriScenari called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Label get_altriScenari() {
      return build_altriScenari();
    }
    private com.google.gwt.user.client.ui.Label build_altriScenari() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label altriScenari = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      altriScenari.setText("Scegli il seguente scenario:");

      this.owner.altriScenari = altriScenari;

      return altriScenari;
    }

    /**
     * Getter for testoScenario1 called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Label get_testoScenario1() {
      return build_testoScenario1();
    }
    private com.google.gwt.user.client.ui.Label build_testoScenario1() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label testoScenario1 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.testoScenario1 = testoScenario1;

      return testoScenario1;
    }

    /**
     * Getter for nodo2 called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.ListBox get_nodo2() {
      return build_nodo2();
    }
    private com.google.gwt.user.client.ui.ListBox build_nodo2() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox nodo2 = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      nodo2.addItem("Scenario a scelta");
      nodo2.addItem("Scenario con indovinello");

      this.owner.nodo2 = nodo2;

      return nodo2;
    }

    /**
     * Getter for buttonPrecedente called 1 times. Type: DEFAULT. Build precedence: 4.
     */
    private com.google.gwt.user.client.ui.Button get_buttonPrecedente() {
      return build_buttonPrecedente();
    }
    private com.google.gwt.user.client.ui.Button build_buttonPrecedente() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button buttonPrecedente = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      buttonPrecedente.setHTML(template_html2().asString());

      this.owner.buttonPrecedente = buttonPrecedente;

      return buttonPrecedente;
    }

    /**
     * Getter for buttonSuccessivo called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_buttonSuccessivo() {
      return build_buttonSuccessivo();
    }
    private com.google.gwt.user.client.ui.Button build_buttonSuccessivo() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button buttonSuccessivo = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      buttonSuccessivo.setHTML(template_html3().asString());

      this.owner.buttonSuccessivo = buttonSuccessivo;

      return buttonSuccessivo;
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
