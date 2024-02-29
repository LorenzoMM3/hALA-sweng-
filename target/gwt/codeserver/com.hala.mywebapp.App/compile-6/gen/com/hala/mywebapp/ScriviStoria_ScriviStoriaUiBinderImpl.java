// .ui.xml template last modified: 1709220177954
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

public class ScriviStoria_ScriviStoriaUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, com.hala.mywebapp.ScriviStoria>, com.hala.mywebapp.ScriviStoria.ScriviStoriaUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Crea")
    SafeHtml html1();
     
    @Template("Crea")
    SafeHtml html2();
     
    @Template("Crea")
    SafeHtml html3();
     
    @Template("Crea")
    SafeHtml html4();
     
    @Template("Back")
    SafeHtml html5();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final com.hala.mywebapp.ScriviStoria owner) {


    return new Widgets(owner).get_f_HorizontalPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final com.hala.mywebapp.ScriviStoria owner;


    public Widgets(final com.hala.mywebapp.ScriviStoria owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
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
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenBundle) GWT.create(com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenBundle.class);
      // Setup section.

      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 0 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenCss_style get_style() {
      return build_style();
    }
    private com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenCss_style build_style() {
      // Creation section.
      final com.hala.mywebapp.ScriviStoria_ScriviStoriaUiBinderImpl_GenCss_style style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();

      return style;
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
      f_HorizontalPanel1.add(get_titoloS());
      f_HorizontalPanel1.add(get_vpScenario());
      f_HorizontalPanel1.add(get_messageLabel());
      f_HorizontalPanel1.add(get_backButton());
      f_HorizontalPanel1.setSpacing(100);

      return f_HorizontalPanel1;
    }

    /**
     * Getter for titoloS called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_titoloS() {
      return build_titoloS();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_titoloS() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel titoloS = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      titoloS.add(get_f_Label2());
      titoloS.add(get_titoloStoria());
      titoloS.add(get_inserisciStoria());
      titoloS.add(get_messageLabelCreate());
      titoloS.setSpacing(30);

      this.owner.titoloS = titoloS;

      return titoloS;
    }

    /**
     * Getter for f_Label2 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label2() {
      return build_f_Label2();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label2() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label2 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label2.setText("Scrivi il titolo della storia:");

      return f_Label2;
    }

    /**
     * Getter for titoloStoria called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_titoloStoria() {
      return build_titoloStoria();
    }
    private com.google.gwt.user.client.ui.TextBox build_titoloStoria() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox titoloStoria = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.titoloStoria = titoloStoria;

      return titoloStoria;
    }

    /**
     * Getter for inserisciStoria called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_inserisciStoria() {
      return build_inserisciStoria();
    }
    private com.google.gwt.user.client.ui.Button build_inserisciStoria() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button inserisciStoria = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      inserisciStoria.setHTML(template_html1().asString());

      this.owner.inserisciStoria = inserisciStoria;

      return inserisciStoria;
    }

    /**
     * Getter for messageLabelCreate called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_messageLabelCreate() {
      return build_messageLabelCreate();
    }
    private com.google.gwt.user.client.ui.Label build_messageLabelCreate() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label messageLabelCreate = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.messageLabelCreate = messageLabelCreate;

      return messageLabelCreate;
    }

    /**
     * Getter for vpScenario called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.VerticalPanel get_vpScenario() {
      return build_vpScenario();
    }
    private com.google.gwt.user.client.ui.VerticalPanel build_vpScenario() {
      // Creation section.
      final com.google.gwt.user.client.ui.VerticalPanel vpScenario = (com.google.gwt.user.client.ui.VerticalPanel) GWT.create(com.google.gwt.user.client.ui.VerticalPanel.class);
      // Setup section.
      vpScenario.add(get_f_Label3());
      vpScenario.add(get_f_Label4());
      vpScenario.add(get_menuSceltaScenario());
      vpScenario.add(get_f_Label5());
      vpScenario.add(get_menuTipoScenario());
      vpScenario.add(get_labelTestoScenarioAScelta());
      vpScenario.add(get_testoScenarioFieldAScelta());
      vpScenario.add(get_labelDomandaAScelta());
      vpScenario.add(get_domandaFieldAScelta());
      vpScenario.add(get_labelSceltaUno());
      vpScenario.add(get_sceltaUnoField());
      vpScenario.add(get_labelSceltaDue());
      vpScenario.add(get_sceltaDueField());
      vpScenario.add(get_creaScenarioAScelta());
      vpScenario.add(get_labelTestoScenarioIndovinello());
      vpScenario.add(get_testoScenarioFieldIndovinello());
      vpScenario.add(get_labelDomandaIndovinello());
      vpScenario.add(get_domandaFieldIndovinello());
      vpScenario.add(get_labelRispostaIndovinello());
      vpScenario.add(get_rispostaIndovinelloField());
      vpScenario.add(get_creaScenarioIndovinello());
      vpScenario.add(get_labelTestoScenarioOggetto());
      vpScenario.add(get_testoScenarioFieldOggetto());
      vpScenario.add(get_labelOggetto());
      vpScenario.add(get_oggettoNecessario());
      vpScenario.add(get_creaScenarioOggetto());
      vpScenario.setSpacing(30);

      this.owner.vpScenario = vpScenario;

      return vpScenario;
    }

    /**
     * Getter for f_Label3 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label3() {
      return build_f_Label3();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label3() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label3 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label3.setText("Scrivi lo scenario:");

      return f_Label3;
    }

    /**
     * Getter for f_Label4 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label4() {
      return build_f_Label4();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label4() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label4 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label4.setText("Che scenario vuoi inserire?");

      return f_Label4;
    }

    /**
     * Getter for menuSceltaScenario called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuSceltaScenario() {
      return build_menuSceltaScenario();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuSceltaScenario() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuSceltaScenario = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      menuSceltaScenario.addItem("Scenario iniziale");
      menuSceltaScenario.addItem("Scenario intermedio");
      menuSceltaScenario.addItem("Scenario finale");

      return menuSceltaScenario;
    }

    /**
     * Getter for f_Label5 called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_f_Label5() {
      return build_f_Label5();
    }
    private com.google.gwt.user.client.ui.Label build_f_Label5() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label f_Label5 = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      f_Label5.setText("Che tipologia?");

      return f_Label5;
    }

    /**
     * Getter for menuTipoScenario called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_menuTipoScenario() {
      return build_menuTipoScenario();
    }
    private com.google.gwt.user.client.ui.ListBox build_menuTipoScenario() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox menuTipoScenario = (com.google.gwt.user.client.ui.ListBox) GWT.create(com.google.gwt.user.client.ui.ListBox.class);
      // Setup section.
      menuTipoScenario.addItem("Scenario a scelta");
      menuTipoScenario.addItem("Scenario con indovinello");
      menuTipoScenario.addItem("Scenario con oggetto");

      this.owner.menuTipoScenario = menuTipoScenario;

      return menuTipoScenario;
    }

    /**
     * Getter for labelTestoScenarioAScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelTestoScenarioAScelta() {
      return build_labelTestoScenarioAScelta();
    }
    private com.google.gwt.user.client.ui.Label build_labelTestoScenarioAScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelTestoScenarioAScelta = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelTestoScenarioAScelta.setText("Inserisci testo scenario:");

      this.owner.labelTestoScenarioAScelta = labelTestoScenarioAScelta;

      return labelTestoScenarioAScelta;
    }

    /**
     * Getter for testoScenarioFieldAScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_testoScenarioFieldAScelta() {
      return build_testoScenarioFieldAScelta();
    }
    private com.google.gwt.user.client.ui.TextBox build_testoScenarioFieldAScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox testoScenarioFieldAScelta = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.testoScenarioFieldAScelta = testoScenarioFieldAScelta;

      return testoScenarioFieldAScelta;
    }

    /**
     * Getter for labelDomandaAScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelDomandaAScelta() {
      return build_labelDomandaAScelta();
    }
    private com.google.gwt.user.client.ui.Label build_labelDomandaAScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelDomandaAScelta = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelDomandaAScelta.setText("Inserisci domanda per il cambio di scenario:");

      this.owner.labelDomandaAScelta = labelDomandaAScelta;

      return labelDomandaAScelta;
    }

    /**
     * Getter for domandaFieldAScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_domandaFieldAScelta() {
      return build_domandaFieldAScelta();
    }
    private com.google.gwt.user.client.ui.TextBox build_domandaFieldAScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox domandaFieldAScelta = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.domandaFieldAScelta = domandaFieldAScelta;

      return domandaFieldAScelta;
    }

    /**
     * Getter for labelSceltaUno called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelSceltaUno() {
      return build_labelSceltaUno();
    }
    private com.google.gwt.user.client.ui.Label build_labelSceltaUno() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelSceltaUno = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelSceltaUno.setText("Inserisci scelta uno:");

      this.owner.labelSceltaUno = labelSceltaUno;

      return labelSceltaUno;
    }

    /**
     * Getter for sceltaUnoField called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_sceltaUnoField() {
      return build_sceltaUnoField();
    }
    private com.google.gwt.user.client.ui.TextBox build_sceltaUnoField() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox sceltaUnoField = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.sceltaUnoField = sceltaUnoField;

      return sceltaUnoField;
    }

    /**
     * Getter for labelSceltaDue called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelSceltaDue() {
      return build_labelSceltaDue();
    }
    private com.google.gwt.user.client.ui.Label build_labelSceltaDue() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelSceltaDue = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelSceltaDue.setText("Inserisci scelta due:");

      this.owner.labelSceltaDue = labelSceltaDue;

      return labelSceltaDue;
    }

    /**
     * Getter for sceltaDueField called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_sceltaDueField() {
      return build_sceltaDueField();
    }
    private com.google.gwt.user.client.ui.TextBox build_sceltaDueField() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox sceltaDueField = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.sceltaDueField = sceltaDueField;

      return sceltaDueField;
    }

    /**
     * Getter for creaScenarioAScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_creaScenarioAScelta() {
      return build_creaScenarioAScelta();
    }
    private com.google.gwt.user.client.ui.Button build_creaScenarioAScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button creaScenarioAScelta = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      creaScenarioAScelta.setHTML(template_html2().asString());

      this.owner.creaScenarioAScelta = creaScenarioAScelta;

      return creaScenarioAScelta;
    }

    /**
     * Getter for labelTestoScenarioIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelTestoScenarioIndovinello() {
      return build_labelTestoScenarioIndovinello();
    }
    private com.google.gwt.user.client.ui.Label build_labelTestoScenarioIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelTestoScenarioIndovinello = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelTestoScenarioIndovinello.setText("Inserisci testo scenario:");

      this.owner.labelTestoScenarioIndovinello = labelTestoScenarioIndovinello;

      return labelTestoScenarioIndovinello;
    }

    /**
     * Getter for testoScenarioFieldIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_testoScenarioFieldIndovinello() {
      return build_testoScenarioFieldIndovinello();
    }
    private com.google.gwt.user.client.ui.TextBox build_testoScenarioFieldIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox testoScenarioFieldIndovinello = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.testoScenarioFieldIndovinello = testoScenarioFieldIndovinello;

      return testoScenarioFieldIndovinello;
    }

    /**
     * Getter for labelDomandaIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelDomandaIndovinello() {
      return build_labelDomandaIndovinello();
    }
    private com.google.gwt.user.client.ui.Label build_labelDomandaIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelDomandaIndovinello = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelDomandaIndovinello.setText("Inserisci l'indovinello per il cambio di scenario:");

      this.owner.labelDomandaIndovinello = labelDomandaIndovinello;

      return labelDomandaIndovinello;
    }

    /**
     * Getter for domandaFieldIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_domandaFieldIndovinello() {
      return build_domandaFieldIndovinello();
    }
    private com.google.gwt.user.client.ui.TextBox build_domandaFieldIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox domandaFieldIndovinello = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.domandaFieldIndovinello = domandaFieldIndovinello;

      return domandaFieldIndovinello;
    }

    /**
     * Getter for labelRispostaIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelRispostaIndovinello() {
      return build_labelRispostaIndovinello();
    }
    private com.google.gwt.user.client.ui.Label build_labelRispostaIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelRispostaIndovinello = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelRispostaIndovinello.setText("Inserisci risposta corretta:");

      this.owner.labelRispostaIndovinello = labelRispostaIndovinello;

      return labelRispostaIndovinello;
    }

    /**
     * Getter for rispostaIndovinelloField called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_rispostaIndovinelloField() {
      return build_rispostaIndovinelloField();
    }
    private com.google.gwt.user.client.ui.TextBox build_rispostaIndovinelloField() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox rispostaIndovinelloField = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.rispostaIndovinelloField = rispostaIndovinelloField;

      return rispostaIndovinelloField;
    }

    /**
     * Getter for creaScenarioIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_creaScenarioIndovinello() {
      return build_creaScenarioIndovinello();
    }
    private com.google.gwt.user.client.ui.Button build_creaScenarioIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button creaScenarioIndovinello = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      creaScenarioIndovinello.setHTML(template_html3().asString());

      this.owner.creaScenarioIndovinello = creaScenarioIndovinello;

      return creaScenarioIndovinello;
    }

    /**
     * Getter for labelTestoScenarioOggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelTestoScenarioOggetto() {
      return build_labelTestoScenarioOggetto();
    }
    private com.google.gwt.user.client.ui.Label build_labelTestoScenarioOggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelTestoScenarioOggetto = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelTestoScenarioOggetto.setText("Inserisci testo scenario:");

      this.owner.labelTestoScenarioOggetto = labelTestoScenarioOggetto;

      return labelTestoScenarioOggetto;
    }

    /**
     * Getter for testoScenarioFieldOggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_testoScenarioFieldOggetto() {
      return build_testoScenarioFieldOggetto();
    }
    private com.google.gwt.user.client.ui.TextBox build_testoScenarioFieldOggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox testoScenarioFieldOggetto = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.testoScenarioFieldOggetto = testoScenarioFieldOggetto;

      return testoScenarioFieldOggetto;
    }

    /**
     * Getter for labelOggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelOggetto() {
      return build_labelOggetto();
    }
    private com.google.gwt.user.client.ui.Label build_labelOggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelOggetto = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelOggetto.setText("Inserisci l'oggetto necessario per il cambio di scenario:");

      this.owner.labelOggetto = labelOggetto;

      return labelOggetto;
    }

    /**
     * Getter for oggettoNecessario called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_oggettoNecessario() {
      return build_oggettoNecessario();
    }
    private com.google.gwt.user.client.ui.TextBox build_oggettoNecessario() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox oggettoNecessario = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.oggettoNecessario = oggettoNecessario;

      return oggettoNecessario;
    }

    /**
     * Getter for creaScenarioOggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_creaScenarioOggetto() {
      return build_creaScenarioOggetto();
    }
    private com.google.gwt.user.client.ui.Button build_creaScenarioOggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button creaScenarioOggetto = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      creaScenarioOggetto.setHTML(template_html4().asString());

      this.owner.creaScenarioOggetto = creaScenarioOggetto;

      return creaScenarioOggetto;
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
