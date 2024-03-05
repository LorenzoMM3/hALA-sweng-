// .ui.xml template last modified: 1709625500695
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
     
    @Template("Aggiungi scelta")
    SafeHtml html2();
     
    @Template("Crea")
    SafeHtml html3();
     
    @Template("Crea")
    SafeHtml html4();
     
    @Template("Back")
    SafeHtml html5();
     
    @Template("Crea collegamenti")
    SafeHtml html6();
     
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
    SafeHtml template_html6() {
      return template.html6();
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
      f_HorizontalPanel1.add(get_creaCollegamenti());
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
      vpScenario.add(get_scriviScenarioLabel());
      vpScenario.add(get_tipologiaLabel());
      vpScenario.add(get_menuTipoScenario());
      vpScenario.add(get_labelTestoScenario());
      vpScenario.add(get_testoScenarioField());
      vpScenario.add(get_labelDomandaAScelta());
      vpScenario.add(get_domandaFieldAScelta());
      vpScenario.add(get_labelScelta());
      vpScenario.add(get_scelta());
      vpScenario.add(get_labelSceltaOggetto());
      vpScenario.add(get_oggetto());
      vpScenario.add(get_altraScelta());
      vpScenario.add(get_creaScenarioAScelta());
      vpScenario.add(get_labelDomandaIndovinello());
      vpScenario.add(get_domandaFieldIndovinello());
      vpScenario.add(get_labelRispostaIndovinello());
      vpScenario.add(get_rispostaFieldIndovinello());
      vpScenario.add(get_creaScenarioIndovinello());
      vpScenario.add(get_message());
      vpScenario.setSpacing(30);

      this.owner.vpScenario = vpScenario;

      return vpScenario;
    }

    /**
     * Getter for scriviScenarioLabel called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_scriviScenarioLabel() {
      return build_scriviScenarioLabel();
    }
    private com.google.gwt.user.client.ui.Label build_scriviScenarioLabel() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label scriviScenarioLabel = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      scriviScenarioLabel.setText("Scrivi lo scenario:");

      this.owner.scriviScenarioLabel = scriviScenarioLabel;

      return scriviScenarioLabel;
    }

    /**
     * Getter for tipologiaLabel called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_tipologiaLabel() {
      return build_tipologiaLabel();
    }
    private com.google.gwt.user.client.ui.Label build_tipologiaLabel() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label tipologiaLabel = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      tipologiaLabel.setText("Che tipologia di scenario vuoi inserire?");

      this.owner.tipologiaLabel = tipologiaLabel;

      return tipologiaLabel;
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

      this.owner.menuTipoScenario = menuTipoScenario;

      return menuTipoScenario;
    }

    /**
     * Getter for labelTestoScenario called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelTestoScenario() {
      return build_labelTestoScenario();
    }
    private com.google.gwt.user.client.ui.Label build_labelTestoScenario() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelTestoScenario = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelTestoScenario.setText("Inserisci testo scenario:");

      this.owner.labelTestoScenario = labelTestoScenario;

      return labelTestoScenario;
    }

    /**
     * Getter for testoScenarioField called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_testoScenarioField() {
      return build_testoScenarioField();
    }
    private com.google.gwt.user.client.ui.TextBox build_testoScenarioField() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox testoScenarioField = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.testoScenarioField = testoScenarioField;

      return testoScenarioField;
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
     * Getter for labelScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelScelta() {
      return build_labelScelta();
    }
    private com.google.gwt.user.client.ui.Label build_labelScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelScelta = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelScelta.setText("Inserisci opzione scelta:");

      this.owner.labelScelta = labelScelta;

      return labelScelta;
    }

    /**
     * Getter for scelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_scelta() {
      return build_scelta();
    }
    private com.google.gwt.user.client.ui.TextBox build_scelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox scelta = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.scelta = scelta;

      return scelta;
    }

    /**
     * Getter for labelSceltaOggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_labelSceltaOggetto() {
      return build_labelSceltaOggetto();
    }
    private com.google.gwt.user.client.ui.Label build_labelSceltaOggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label labelSceltaOggetto = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.
      labelSceltaOggetto.setText("Inserisci oggetto (se necessario):");

      this.owner.labelSceltaOggetto = labelSceltaOggetto;

      return labelSceltaOggetto;
    }

    /**
     * Getter for oggetto called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_oggetto() {
      return build_oggetto();
    }
    private com.google.gwt.user.client.ui.TextBox build_oggetto() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox oggetto = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.oggetto = oggetto;

      return oggetto;
    }

    /**
     * Getter for altraScelta called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_altraScelta() {
      return build_altraScelta();
    }
    private com.google.gwt.user.client.ui.Button build_altraScelta() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button altraScelta = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      altraScelta.setHTML(template_html2().asString());

      this.owner.altraScelta = altraScelta;

      return altraScelta;
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
      creaScenarioAScelta.setHTML(template_html3().asString());

      this.owner.creaScenarioAScelta = creaScenarioAScelta;

      return creaScenarioAScelta;
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
     * Getter for rispostaFieldIndovinello called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.TextBox get_rispostaFieldIndovinello() {
      return build_rispostaFieldIndovinello();
    }
    private com.google.gwt.user.client.ui.TextBox build_rispostaFieldIndovinello() {
      // Creation section.
      final com.google.gwt.user.client.ui.TextBox rispostaFieldIndovinello = (com.google.gwt.user.client.ui.TextBox) GWT.create(com.google.gwt.user.client.ui.TextBox.class);
      // Setup section.

      this.owner.rispostaFieldIndovinello = rispostaFieldIndovinello;

      return rispostaFieldIndovinello;
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
      creaScenarioIndovinello.setHTML(template_html4().asString());

      this.owner.creaScenarioIndovinello = creaScenarioIndovinello;

      return creaScenarioIndovinello;
    }

    /**
     * Getter for message called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Label get_message() {
      return build_message();
    }
    private com.google.gwt.user.client.ui.Label build_message() {
      // Creation section.
      final com.google.gwt.user.client.ui.Label message = (com.google.gwt.user.client.ui.Label) GWT.create(com.google.gwt.user.client.ui.Label.class);
      // Setup section.

      this.owner.message = message;

      return message;
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

    /**
     * Getter for creaCollegamenti called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.Button get_creaCollegamenti() {
      return build_creaCollegamenti();
    }
    private com.google.gwt.user.client.ui.Button build_creaCollegamenti() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button creaCollegamenti = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      creaCollegamenti.setHTML(template_html6().asString());

      this.owner.creaCollegamenti = creaCollegamenti;

      return creaCollegamenti;
    }
  }
}
