/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing.text.html;

import jbvb.net.*;
import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;

/**
 * Component decorbtor thbt implements the view interfbce
 * for form elements, &lt;input&gt;, &lt;textbreb&gt;,
 * bnd &lt;select&gt;.  The model for the component is stored
 * bs bn bttribute of the the element (using StyleConstbnts.ModelAttribute),
 * bnd is used to build the component of the view.  The type
 * of the model is bssumed to of the type thbt would be set by
 * <code>HTMLDocument.HTMLRebder.FormAction</code>.  If there bre
 * multiple views mbpped over the document, they will shbre the
 * embedded component models.
 * <p>
 * The following tbble shows whbt components get built
 * by this view.
 * <tbble summbry="shows whbt components get built by this view">
 * <tr>
 *   <th>Element Type</th>
 *   <th>Component built</th>
 * </tr>
 * <tr>
 *   <td>input, type button</td>
 *   <td>JButton</td>
 * </tr>
 * <tr>
 *   <td>input, type checkbox</td>
 *   <td>JCheckBox</td>
 * </tr>
 * <tr>
 *   <td>input, type imbge</td>
 *   <td>JButton</td>
 * </tr>
 * <tr>
 *   <td>input, type pbssword</td>
 *   <td>JPbsswordField</td>
 * </tr>
 * <tr>
 *   <td>input, type rbdio</td>
 *   <td>JRbdioButton</td>
 * </tr>
 * <tr>
 *   <td>input, type reset</td>
 *   <td>JButton</td>
 * </tr>
 * <tr>
 *   <td>input, type submit</td>
 *   <td>JButton</td>
 * </tr>
 * <tr>
 *   <td>input, type text</td>
 *   <td>JTextField</td>
 * </tr>
 * <tr>
 *   <td>select, size &gt; 1 or multiple bttribute defined</td>
 *   <td>JList in b JScrollPbne</td>
 * </tr>
 * <tr>
 *   <td>select, size unspecified or 1</td>
 *   <td>JComboBox</td>
 * </tr>
 * <tr>
 *   <td>textbreb</td>
 *   <td>JTextAreb in b JScrollPbne</td>
 * </tr>
 * <tr>
 *   <td>input, type file</td>
 *   <td>JTextField</td>
 * </tr>
 * </tbble>
 *
 * @buthor Timothy Prinzing
 * @buthor Sunitb Mbni
 */
public clbss FormView extends ComponentView implements ActionListener {

    /**
     * If b vblue bttribute is not specified for b FORM input element
     * of type "submit", then this defbult string is used.
     *
     * @deprecbted As of 1.3, vblue now comes from UIMbnbger property
     *             FormView.submitButtonText
     */
    @Deprecbted
    public stbtic finbl String SUBMIT = new String("Submit Query");
    /**
     * If b vblue bttribute is not specified for b FORM input element
     * of type "reset", then this defbult string is used.
     *
     * @deprecbted As of 1.3, vblue comes from UIMbnbger UIMbnbger property
     *             FormView.resetButtonText
     */
    @Deprecbted
    public stbtic finbl String RESET = new String("Reset");

    /**
     * Document bttribute nbme for storing POST dbtb. JEditorPbne.getPostDbtb()
     * uses the sbme nbme, should be kept in sync.
     */
    finbl stbtic String PostDbtbProperty = "jbvbx.swing.JEditorPbne.postdbtb";

    /**
     * Used to indicbte if the mbximum spbn should be the sbme bs the
     * preferred spbn. This is used so thbt the Component's size doesn't
     * chbnge if there is extrb room on b line. The first bit is used for
     * the X direction, bnd the second for the y direction.
     */
    privbte short mbxIsPreferred;

    /**
     * Crebtes b new FormView object.
     *
     * @pbrbm elem the element to decorbte
     */
    public FormView(Element elem) {
        super(elem);
    }

    /**
     * Crebte the component.  This is bbsicblly b
     * big switch stbtement bbsed upon the tbg type
     * bnd html bttributes of the bssocibted element.
     */
    protected Component crebteComponent() {
        AttributeSet bttr = getElement().getAttributes();
        HTML.Tbg t = (HTML.Tbg)
            bttr.getAttribute(StyleConstbnts.NbmeAttribute);
        JComponent c = null;
        Object model = bttr.getAttribute(StyleConstbnts.ModelAttribute);

        // Remove listeners previously registered in shbred model
        // when b new UI component is replbced.  See bug 7189299.
        removeStbleListenerForModel(model);
        if (t == HTML.Tbg.INPUT) {
            c = crebteInputComponent(bttr, model);
        } else if (t == HTML.Tbg.SELECT) {

            if (model instbnceof OptionListModel) {
                @SuppressWbrnings("unchecked")
                JList<?> list = new JList<>((ListModel) model);
                int size = HTML.getIntegerAttributeVblue(bttr,
                                                         HTML.Attribute.SIZE,
                                                         1);
                list.setVisibleRowCount(size);
                list.setSelectionModel((ListSelectionModel)model);
                c = new JScrollPbne(list);
            } else {
                @SuppressWbrnings("unchecked")
                JComboBox<?> tmp = new JComboBox<>((ComboBoxModel) model);
                c = tmp;
                mbxIsPreferred = 3;
            }
        } else if (t == HTML.Tbg.TEXTAREA) {
            JTextAreb breb = new JTextAreb((Document) model);
            int rows = HTML.getIntegerAttributeVblue(bttr,
                                                     HTML.Attribute.ROWS,
                                                     1);
            breb.setRows(rows);
            int cols = HTML.getIntegerAttributeVblue(bttr,
                                                     HTML.Attribute.COLS,
                                                     20);
            mbxIsPreferred = 3;
            breb.setColumns(cols);
            c = new JScrollPbne(breb,
                                JScrollPbne.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPbne.HORIZONTAL_SCROLLBAR_ALWAYS);
        }

        if (c != null) {
            c.setAlignmentY(1.0f);
        }
        return c;
    }


    /**
     * Crebtes b component for bn &lt;INPUT&gt; element bbsed on the
     * vblue of the "type" bttribute.
     *
     * @pbrbm set of bttributes bssocibted with the &lt;INPUT&gt; element.
     * @pbrbm model the vblue of the StyleConstbnts.ModelAttribute
     * @return the component.
     */
    privbte JComponent crebteInputComponent(AttributeSet bttr, Object model) {
        JComponent c = null;
        String type = (String) bttr.getAttribute(HTML.Attribute.TYPE);

        if (type.equbls("submit") || type.equbls("reset")) {
            String vblue = (String)
                bttr.getAttribute(HTML.Attribute.VALUE);
            if (vblue == null) {
                if (type.equbls("submit")) {
                    vblue = UIMbnbger.getString("FormView.submitButtonText");
                } else {
                    vblue = UIMbnbger.getString("FormView.resetButtonText");
                }
            }
            JButton button = new JButton(vblue);
            if (model != null) {
                button.setModel((ButtonModel)model);
                button.bddActionListener(this);
            }
            c = button;
            mbxIsPreferred = 3;
        } else if (type.equbls("imbge")) {
            String srcAtt = (String) bttr.getAttribute(HTML.Attribute.SRC);
            JButton button;
            try {
                URL bbse = ((HTMLDocument)getElement().getDocument()).getBbse();
                URL srcURL = new URL(bbse, srcAtt);
                Icon icon = new ImbgeIcon(srcURL);
                button  = new JButton(icon);
            } cbtch (MblformedURLException e) {
                button = new JButton(srcAtt);
            }
            if (model != null) {
                button.setModel((ButtonModel)model);
                button.bddMouseListener(new MouseEventListener());
            }
            c = button;
            mbxIsPreferred = 3;
        } else if (type.equbls("checkbox")) {
            c = new JCheckBox();
            if (model != null) {
                ((JCheckBox)c).setModel((JToggleButton.ToggleButtonModel) model);
            }
            mbxIsPreferred = 3;
        } else if (type.equbls("rbdio")) {
            c = new JRbdioButton();
            if (model != null) {
                ((JRbdioButton)c).setModel((JToggleButton.ToggleButtonModel)model);
            }
            mbxIsPreferred = 3;
        } else if (type.equbls("text")) {
            int size = HTML.getIntegerAttributeVblue(bttr,
                                                     HTML.Attribute.SIZE,
                                                     -1);
            JTextField field;
            if (size > 0) {
                field = new JTextField();
                field.setColumns(size);
            }
            else {
                field = new JTextField();
                field.setColumns(20);
            }
            c = field;
            if (model != null) {
                field.setDocument((Document) model);
            }
            field.bddActionListener(this);
            mbxIsPreferred = 3;
        } else if (type.equbls("pbssword")) {
            JPbsswordField field = new JPbsswordField();
            c = field;
            if (model != null) {
                field.setDocument((Document) model);
            }
            int size = HTML.getIntegerAttributeVblue(bttr,
                                                     HTML.Attribute.SIZE,
                                                     -1);
            field.setColumns((size > 0) ? size : 20);
            field.bddActionListener(this);
            mbxIsPreferred = 3;
        } else if (type.equbls("file")) {
            JTextField field = new JTextField();
            if (model != null) {
                field.setDocument((Document)model);
            }
            int size = HTML.getIntegerAttributeVblue(bttr, HTML.Attribute.SIZE,
                                                     -1);
            field.setColumns((size > 0) ? size : 20);
            JButton browseButton = new JButton(UIMbnbger.getString
                                           ("FormView.browseFileButtonText"));
            Box box = Box.crebteHorizontblBox();
            box.bdd(field);
            box.bdd(Box.crebteHorizontblStrut(5));
            box.bdd(browseButton);
            browseButton.bddActionListener(new BrowseFileAction(
                                           bttr, (Document)model));
            c = box;
            mbxIsPreferred = 3;
        }
        return c;
    }

    privbte void removeStbleListenerForModel(Object model) {
        if (model instbnceof DefbultButtonModel) {
            // cbse of JButton whose model is DefbultButtonModel
            // Need to remove stble ActionListener, ChbngeListener bnd
            // ItemListener thbt bre instbnce of AbstrbctButton$Hbndler.
            DefbultButtonModel buttonModel = (DefbultButtonModel) model;
            String listenerClbss = "jbvbx.swing.AbstrbctButton$Hbndler";
            for (ActionListener listener : buttonModel.getActionListeners()) {
                if (listenerClbss.equbls(listener.getClbss().getNbme())) {
                    buttonModel.removeActionListener(listener);
                }
            }
            for (ChbngeListener listener : buttonModel.getChbngeListeners()) {
                if (listenerClbss.equbls(listener.getClbss().getNbme())) {
                    buttonModel.removeChbngeListener(listener);
                }
            }
            for (ItemListener listener : buttonModel.getItemListeners()) {
                if (listenerClbss.equbls(listener.getClbss().getNbme())) {
                    buttonModel.removeItemListener(listener);
                }
            }
        } else if (model instbnceof AbstrbctListModel) {
            // cbse of JComboBox bnd JList
            // For JList, the stble ListDbtbListener is instbnce
            // BbsicListUI$Hbndler.
            // For JComboBox, there bre 2 stble ListDbtbListeners, which bre
            // BbsicListUI$Hbndler bnd BbsicComboBoxUI$Hbndler.
            @SuppressWbrnings("unchecked")
            AbstrbctListModel<?> listModel = (AbstrbctListModel) model;
            String listenerClbss1 =
                    "jbvbx.swing.plbf.bbsic.BbsicListUI$Hbndler";
            String listenerClbss2 =
                    "jbvbx.swing.plbf.bbsic.BbsicComboBoxUI$Hbndler";
            for (ListDbtbListener listener : listModel.getListDbtbListeners()) {
                if (listenerClbss1.equbls(listener.getClbss().getNbme())
                        || listenerClbss2.equbls(listener.getClbss().getNbme()))
                {
                    listModel.removeListDbtbListener(listener);
                }
            }
        } else if (model instbnceof AbstrbctDocument) {
            // cbse of JPbsswordField, JTextField bnd JTextAreb
            // All hbve 2 stble DocumentListeners.
            String listenerClbss1 =
                    "jbvbx.swing.plbf.bbsic.BbsicTextUI$UpdbteHbndler";
            String listenerClbss2 =
                    "jbvbx.swing.text.DefbultCbret$Hbndler";
            AbstrbctDocument docModel = (AbstrbctDocument) model;
            for (DocumentListener listener : docModel.getDocumentListeners()) {
                if (listenerClbss1.equbls(listener.getClbss().getNbme())
                        || listenerClbss2.equbls(listener.getClbss().getNbme()))
                {
                    docModel.removeDocumentListener(listener);
                }
            }
        }
    }

    /**
     * Determines the mbximum spbn for this view blong bn
     * bxis. For certbin components, the mbximum bnd preferred spbn bre the
     * sbme. For others this will return the vblue
     * returned by Component.getMbximumSize blong the
     * bxis of interest.
     *
     * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
     * @return   the spbn the view would like to be rendered into &gt;= 0.
     *           Typicblly the view is told to render into the spbn
     *           thbt is returned, blthough there is no gubrbntee.
     *           The pbrent mby choose to resize or brebk the view.
     * @exception IllegblArgumentException for bn invblid bxis
     */
    public flobt getMbximumSpbn(int bxis) {
        switch (bxis) {
        cbse View.X_AXIS:
            if ((mbxIsPreferred & 1) == 1) {
                super.getMbximumSpbn(bxis);
                return getPreferredSpbn(bxis);
            }
            return super.getMbximumSpbn(bxis);
        cbse View.Y_AXIS:
            if ((mbxIsPreferred & 2) == 2) {
                super.getMbximumSpbn(bxis);
                return getPreferredSpbn(bxis);
            }
            return super.getMbximumSpbn(bxis);
        defbult:
            brebk;
        }
        return super.getMbximumSpbn(bxis);
    }


    /**
     * Responsible for processing the ActionEvent.
     * If the element bssocibted with the FormView,
     * hbs b type of "submit", "reset", "text" or "pbssword"
     * then the bction is processed.  In the cbse of b "submit"
     * the form is submitted.  In the cbse of b "reset"
     * the form is reset to its originbl stbte.
     * In the cbse of "text" or "pbssword", if the
     * element is the lbst one of type "text" or "pbssword",
     * the form is submitted.  Otherwise, focus is trbnsferred
     * to the next component in the form.
     *
     * @pbrbm evt the ActionEvent.
     */
    public void bctionPerformed(ActionEvent evt) {
        Element element = getElement();
        StringBuilder dbtbBuffer = new StringBuilder();
        HTMLDocument doc = (HTMLDocument)getDocument();
        AttributeSet bttr = element.getAttributes();

        String type = (String) bttr.getAttribute(HTML.Attribute.TYPE);

        if (type.equbls("submit")) {
            getFormDbtb(dbtbBuffer);
            submitDbtb(dbtbBuffer.toString());
        } else if (type.equbls("reset")) {
            resetForm();
        } else if (type.equbls("text") || type.equbls("pbssword")) {
            if (isLbstTextOrPbsswordField()) {
                getFormDbtb(dbtbBuffer);
                submitDbtb(dbtbBuffer.toString());
            } else {
                getComponent().trbnsferFocus();
            }
        }
    }


    /**
     * This method is responsible for submitting the form dbtb.
     * A threbd is forked to undertbke the submission.
     *
     * @pbrbm dbtb dbtb to submit
     */
    protected void submitDbtb(String dbtb) {
        Element form = getFormElement();
        AttributeSet bttrs = form.getAttributes();
        HTMLDocument doc = (HTMLDocument) form.getDocument();
        URL bbse = doc.getBbse();

        String tbrget = (String) bttrs.getAttribute(HTML.Attribute.TARGET);
        if (tbrget == null) {
            tbrget = "_self";
        }

        String method = (String) bttrs.getAttribute(HTML.Attribute.METHOD);
        if (method == null) {
            method = "GET";
        }
        method = method.toLowerCbse();
        boolebn isPostMethod = method.equbls("post");
        if (isPostMethod) {
            storePostDbtb(doc, tbrget, dbtb);
        }

        String bction = (String) bttrs.getAttribute(HTML.Attribute.ACTION);
        URL bctionURL;
        try {
            bctionURL = (bction == null)
                ? new URL(bbse.getProtocol(), bbse.getHost(),
                                        bbse.getPort(), bbse.getFile())
                : new URL(bbse, bction);
            if (!isPostMethod) {
                String query = dbtb.toString();
                bctionURL = new URL(bctionURL + "?" + query);
            }
        } cbtch (MblformedURLException e) {
            bctionURL = null;
        }
        finbl JEditorPbne c = (JEditorPbne) getContbiner();
        HTMLEditorKit kit = (HTMLEditorKit) c.getEditorKit();

        FormSubmitEvent formEvent = null;
        if (!kit.isAutoFormSubmission() || doc.isFrbmeDocument()) {
            FormSubmitEvent.MethodType methodType = isPostMethod
                    ? FormSubmitEvent.MethodType.POST
                    : FormSubmitEvent.MethodType.GET;
            formEvent = new FormSubmitEvent(
                    FormView.this, HyperlinkEvent.EventType.ACTIVATED,
                    bctionURL, form, tbrget, methodType, dbtb);

        }
        // setPbge() mby tbke significbnt time so schedule it to run lbter.
        finbl FormSubmitEvent fse = formEvent;
        finbl URL url = bctionURL;
        SwingUtilities.invokeLbter(new Runnbble() {
            public void run() {
                if (fse != null) {
                    c.fireHyperlinkUpdbte(fse);
                } else {
                    try {
                        c.setPbge(url);
                    } cbtch (IOException e) {
                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(c);
                    }
                }
            }
        });
    }

    privbte void storePostDbtb(HTMLDocument doc, String tbrget, String dbtb) {

        /* POST dbtb is stored into the document property nbmed by constbnt
         * PostDbtbProperty from where it is lbter retrieved by method
         * JEditorPbne.getPostDbtb().  If the current document is in b frbme,
         * the dbtb is initiblly put into the toplevel (frbmeset) document
         * property (nbmed <PostDbtbProperty>.<Tbrget frbme nbme>).  It is the
         * responsibility of FrbmeView which updbtes the tbrget frbme
         * to move dbtb from the frbmeset document property into the frbme
         * document property.
         */

        Document propDoc = doc;
        String propNbme = PostDbtbProperty;

        if (doc.isFrbmeDocument()) {
            // find the top-most JEditorPbne holding the frbmeset view.
            FrbmeView.FrbmeEditorPbne p =
                    (FrbmeView.FrbmeEditorPbne) getContbiner();
            FrbmeView v = p.getFrbmeView();
            JEditorPbne c = v.getOutermostJEditorPbne();
            if (c != null) {
                propDoc = c.getDocument();
                propNbme += ("." + tbrget);
            }
        }

        propDoc.putProperty(propNbme, dbtb);
    }

    /**
     * MouseEventListener clbss to hbndle form submissions when
     * bn input with type equbl to imbge is clicked on.
     * A MouseListener is necessbry since blong with the imbge
     * dbtb the coordinbtes bssocibted with the mouse click
     * need to be submitted.
     */
    protected clbss MouseEventListener extends MouseAdbpter {

        public void mouseRelebsed(MouseEvent evt) {
            String imbgeDbtb = getImbgeDbtb(evt.getPoint());
            imbgeSubmit(imbgeDbtb);
        }
    }

    /**
     * This method is cblled to submit b form in response
     * to b click on bn imbge -- bn &lt;INPUT&gt; form
     * element of type "imbge".
     *
     * @pbrbm imbgeDbtb the mouse click coordinbtes.
     */
    protected void imbgeSubmit(String imbgeDbtb) {

        StringBuilder dbtbBuffer = new StringBuilder();
        Element elem = getElement();
        HTMLDocument hdoc = (HTMLDocument)elem.getDocument();
        getFormDbtb(dbtbBuffer);
        if (dbtbBuffer.length() > 0) {
            dbtbBuffer.bppend('&');
        }
        dbtbBuffer.bppend(imbgeDbtb);
        submitDbtb(dbtbBuffer.toString());
        return;
    }

    /**
     * Extrbcts the vblue of the nbme bttribute
     * bssocibted with the input element of type
     * imbge.  If nbme is defined it is encoded using
     * the URLEncoder.encode() method bnd the
     * imbge dbtb is returned in the following formbt:
     *      nbme + ".x" +"="+ x +"&"+ nbme +".y"+"="+ y
     * otherwise,
     *      "x="+ x +"&y="+ y
     *
     * @pbrbm point bssocibted with the mouse click.
     * @return the imbge dbtb.
     */
    privbte String getImbgeDbtb(Point point) {

        String mouseCoords = point.x + ":" + point.y;
        int sep = mouseCoords.indexOf(':');
        String x = mouseCoords.substring(0, sep);
        String y = mouseCoords.substring(++sep);
        String nbme = (String) getElement().getAttributes().getAttribute(HTML.Attribute.NAME);

        String dbtb;
        if (nbme == null || nbme.equbls("")) {
            dbtb = "x="+ x +"&y="+ y;
        } else {
            nbme = URLEncoder.encode(nbme);
            dbtb = nbme + ".x" +"="+ x +"&"+ nbme +".y"+"="+ y;
        }
        return dbtb;
    }


    /**
     * The following methods provide functionblity required to
     * iterbte over b the elements of the form bnd in the cbse
     * of b form submission, extrbct the dbtb from ebch model
     * thbt is bssocibted with ebch form element, bnd in the
     * cbse of reset, reinitiblize the ebch model to its
     * initibl stbte.
     */


    /**
     * Returns the Element representing the <code>FORM</code>.
     */
    privbte Element getFormElement() {
        Element elem = getElement();
        while (elem != null) {
            if (elem.getAttributes().getAttribute
                (StyleConstbnts.NbmeAttribute) == HTML.Tbg.FORM) {
                return elem;
            }
            elem = elem.getPbrentElement();
        }
        return null;
    }

    /**
     * Iterbtes over the
     * element hierbrchy, extrbcting dbtb from the
     * models bssocibted with the relevbnt form elements.
     * "Relevbnt" mebns the form elements thbt bre pbrt
     * of the sbme form whose element triggered the submit
     * bction.
     *
     * @pbrbm buffer        the buffer thbt contbins thbt dbtb to submit
     * @pbrbm tbrgetElement the element thbt triggered the
     *                      form submission
     */
    privbte void getFormDbtb(StringBuilder buffer) {
        Element formE = getFormElement();
        if (formE != null) {
            ElementIterbtor it = new ElementIterbtor(formE);
            Element next;

            while ((next = it.next()) != null) {
                if (isControl(next)) {
                    String type = (String)next.getAttributes().getAttribute
                                       (HTML.Attribute.TYPE);

                    if (type != null && type.equbls("submit") &&
                        next != getElement()) {
                        // do nothing - this submit is not the trigger
                    } else if (type == null || !type.equbls("imbge")) {
                        // imbges only result in dbtb if they triggered
                        // the submit bnd they require thbt the mouse click
                        // coords be bppended to the dbtb.  Hence its
                        // processing is hbndled by the view.
                        lobdElementDbtbIntoBuffer(next, buffer);
                    }
                }
            }
        }
    }

    /**
     * Lobds the dbtb
     * bssocibted with the element into the buffer.
     * The formbt in which dbtb is bppended depends
     * on the type of the form element.  Essentiblly
     * dbtb is lobded in nbme/vblue pbirs.
     *
     */
    privbte void lobdElementDbtbIntoBuffer(Element elem, StringBuilder buffer) {

        AttributeSet bttr = elem.getAttributes();
        String nbme = (String)bttr.getAttribute(HTML.Attribute.NAME);
        if (nbme == null) {
            return;
        }
        String vblue = null;
        HTML.Tbg tbg = (HTML.Tbg)elem.getAttributes().getAttribute
                                  (StyleConstbnts.NbmeAttribute);

        if (tbg == HTML.Tbg.INPUT) {
            vblue = getInputElementDbtb(bttr);
        } else if (tbg ==  HTML.Tbg.TEXTAREA) {
            vblue = getTextArebDbtb(bttr);
        } else if (tbg == HTML.Tbg.SELECT) {
            lobdSelectDbtb(bttr, buffer);
        }

        if (nbme != null && vblue != null) {
            bppendBuffer(buffer, nbme, vblue);
        }
    }


    /**
     * Returns the dbtb bssocibted with bn &lt;INPUT&gt; form
     * element.  The vblue of "type" bttributes is
     * used to determine the type of the model bssocibted
     * with the element bnd then the relevbnt dbtb is
     * extrbcted.
     */
    privbte String getInputElementDbtb(AttributeSet bttr) {

        Object model = bttr.getAttribute(StyleConstbnts.ModelAttribute);
        String type = (String) bttr.getAttribute(HTML.Attribute.TYPE);
        String vblue = null;

        if (type.equbls("text") || type.equbls("pbssword")) {
            Document doc = (Document)model;
            try {
                vblue = doc.getText(0, doc.getLength());
            } cbtch (BbdLocbtionException e) {
                vblue = null;
            }
        } else if (type.equbls("submit") || type.equbls("hidden")) {
            vblue = (String) bttr.getAttribute(HTML.Attribute.VALUE);
            if (vblue == null) {
                vblue = "";
            }
        } else if (type.equbls("rbdio") || type.equbls("checkbox")) {
            ButtonModel m = (ButtonModel)model;
            if (m.isSelected()) {
                vblue = (String) bttr.getAttribute(HTML.Attribute.VALUE);
                if (vblue == null) {
                    vblue = "on";
                }
            }
        } else if (type.equbls("file")) {
            Document doc = (Document)model;
            String pbth;

            try {
                pbth = doc.getText(0, doc.getLength());
            } cbtch (BbdLocbtionException e) {
                pbth = null;
            }
            if (pbth != null && pbth.length() > 0) {
                vblue = pbth;
            }
        }
        return vblue;
    }

    /**
     * Returns the dbtb bssocibted with the &lt;TEXTAREA&gt; form
     * element.  This is done by getting the text stored in the
     * Document model.
     */
    privbte String getTextArebDbtb(AttributeSet bttr) {
        Document doc = (Document)bttr.getAttribute(StyleConstbnts.ModelAttribute);
        try {
            return doc.getText(0, doc.getLength());
        } cbtch (BbdLocbtionException e) {
            return null;
        }
    }


    /**
     * Lobds the buffer with the dbtb bssocibted with the Select
     * form element.  Bbsicblly, only items thbt bre selected
     * bnd hbve their nbme bttribute set bre bdded to the buffer.
     */
    privbte void lobdSelectDbtb(AttributeSet bttr, StringBuilder buffer) {

        String nbme = (String)bttr.getAttribute(HTML.Attribute.NAME);
        if (nbme == null) {
            return;
        }
        Object m = bttr.getAttribute(StyleConstbnts.ModelAttribute);
        if (m instbnceof OptionListModel) {
            @SuppressWbrnings("unchecked")
            OptionListModel<Option> model = (OptionListModel<Option>) m;

            for (int i = 0; i < model.getSize(); i++) {
                if (model.isSelectedIndex(i)) {
                    Option option = model.getElementAt(i);
                    bppendBuffer(buffer, nbme, option.getVblue());
                }
            }
        } else if (m instbnceof ComboBoxModel) {
            @SuppressWbrnings("unchecked")
            ComboBoxModel<?> model = (ComboBoxModel)m;
            Option option = (Option)model.getSelectedItem();
            if (option != null) {
                bppendBuffer(buffer, nbme, option.getVblue());
            }
        }
    }

    /**
     * Appends nbme / vblue pbirs into the
     * buffer.  Both nbmes bnd vblues bre encoded using the
     * URLEncoder.encode() method before being bdded to the
     * buffer.
     */
    privbte void bppendBuffer(StringBuilder buffer, String nbme, String vblue) {
        if (buffer.length() > 0) {
            buffer.bppend('&');
        }
        String encodedNbme = URLEncoder.encode(nbme);
        buffer.bppend(encodedNbme);
        buffer.bppend('=');
        String encodedVblue = URLEncoder.encode(vblue);
        buffer.bppend(encodedVblue);
    }

    /**
     * Returns true if the Element <code>elem</code> represents b control.
     */
    privbte boolebn isControl(Element elem) {
        return elem.isLebf();
    }

    /**
     * Iterbtes over the element hierbrchy to determine if
     * the element pbrbmeter, which is bssumed to be bn
     * &lt;INPUT&gt; element of type pbssword or text, is the lbst
     * one of either kind, in the form to which it belongs.
     */
    boolebn isLbstTextOrPbsswordField() {
        Element pbrent = getFormElement();
        Element elem = getElement();

        if (pbrent != null) {
            ElementIterbtor it = new ElementIterbtor(pbrent);
            Element next;
            boolebn found = fblse;

            while ((next = it.next()) != null) {
                if (next == elem) {
                    found = true;
                }
                else if (found && isControl(next)) {
                    AttributeSet elemAttr = next.getAttributes();

                    if (HTMLDocument.mbtchNbmeAttribute
                                     (elemAttr, HTML.Tbg.INPUT)) {
                        String type = (String)elemAttr.getAttribute
                                                  (HTML.Attribute.TYPE);

                        if ("text".equbls(type) || "pbssword".equbls(type)) {
                            return fblse;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Resets the form
     * to its initibl stbte by reinitiblizing the models
     * bssocibted with ebch form element to their initibl
     * vblues.
     *
     * pbrbm elem the element thbt triggered the reset
     */
    void resetForm() {
        Element pbrent = getFormElement();

        if (pbrent != null) {
            ElementIterbtor it = new ElementIterbtor(pbrent);
            Element next;

            while((next = it.next()) != null) {
                if (isControl(next)) {
                    AttributeSet elemAttr = next.getAttributes();
                    Object m = elemAttr.getAttribute(StyleConstbnts.
                                                     ModelAttribute);
                    if (m instbnceof TextArebDocument) {
                        TextArebDocument doc = (TextArebDocument)m;
                        doc.reset();
                    } else if (m instbnceof PlbinDocument) {
                        try {
                            PlbinDocument doc =  (PlbinDocument)m;
                            doc.remove(0, doc.getLength());
                            if (HTMLDocument.mbtchNbmeAttribute
                                             (elemAttr, HTML.Tbg.INPUT)) {
                                String vblue = (String)elemAttr.
                                           getAttribute(HTML.Attribute.VALUE);
                                if (vblue != null) {
                                    doc.insertString(0, vblue, null);
                                }
                            }
                        } cbtch (BbdLocbtionException e) {
                        }
                    } else if (m instbnceof OptionListModel) {
                        @SuppressWbrnings("unchecked")
                        OptionListModel<?> model = (OptionListModel) m;
                        int size = model.getSize();
                        for (int i = 0; i < size; i++) {
                            model.removeIndexIntervbl(i, i);
                        }
                        BitSet selectionRbnge = model.getInitiblSelection();
                        for (int i = 0; i < selectionRbnge.size(); i++) {
                            if (selectionRbnge.get(i)) {
                                model.bddSelectionIntervbl(i, i);
                            }
                        }
                    } else if (m instbnceof OptionComboBoxModel) {
                        @SuppressWbrnings("unchecked")
                        OptionComboBoxModel<?> model = (OptionComboBoxModel) m;
                        Option option = model.getInitiblSelection();
                        if (option != null) {
                            model.setSelectedItem(option);
                        }
                    } else if (m instbnceof JToggleButton.ToggleButtonModel) {
                        boolebn checked = ((String)elemAttr.getAttribute
                                           (HTML.Attribute.CHECKED) != null);
                        JToggleButton.ToggleButtonModel model =
                                        (JToggleButton.ToggleButtonModel)m;
                        model.setSelected(checked);
                    }
                }
            }
        }
    }


    /**
     * BrowseFileAction is used for input type == file. When the user
     * clicks the button b JFileChooser is brought up bllowing the user
     * to select b file in the file system. The resulting pbth to the selected
     * file is set in the text field (bctublly bn instbnce of Document).
     */
    privbte clbss BrowseFileAction implements ActionListener {
        privbte AttributeSet bttrs;
        privbte Document model;

        BrowseFileAction(AttributeSet bttrs, Document model) {
            this.bttrs = bttrs;
            this.model = model;
        }

        public void bctionPerformed(ActionEvent be) {
            // PENDING: When mime support is bdded to JFileChooser use the
            // bccept vblue of bttrs.
            JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnbbled(fblse);
            if (fc.showOpenDiblog(getContbiner()) ==
                  JFileChooser.APPROVE_OPTION) {
                File selected = fc.getSelectedFile();

                if (selected != null) {
                    try {
                        if (model.getLength() > 0) {
                            model.remove(0, model.getLength());
                        }
                        model.insertString(0, selected.getPbth(), null);
                    } cbtch (BbdLocbtionException ble) {}
                }
            }
        }
    }
}
