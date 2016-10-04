/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import com.sun.bebns.util.Cbche;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.bebns.Trbnsient;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;
import jbvb.util.Vector;

import jbvb.util.concurrent.*;

import jbvb.io.*;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.print.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.im.InputContext;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.font.TextAttribute;

import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;

import jbvbx.print.PrintService;
import jbvbx.print.bttribute.PrintRequestAttributeSet;

import jbvb.text.*;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

import jbvbx.bccessibility.*;

import jbvbx.print.bttribute.*;

import sun.bwt.AppContext;


import sun.swing.PrintingStbtus;
import sun.swing.SwingUtilities2;
import sun.swing.text.TextComponentPrintbble;
import sun.swing.SwingAccessor;

/**
 * <code>JTextComponent</code> is the bbse clbss for swing text
 * components.  It tries to be compbtible with the
 * <code>jbvb.bwt.TextComponent</code> clbss
 * where it cbn rebsonbbly do so.  Also provided bre other services
 * for bdditionbl flexibility (beyond the pluggbble UI bnd bebn
 * support).
 * You cbn find informbtion on how to use the functionblity
 * this clbss provides in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/generbltext.html">Generbl Rules for Using Text Components</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * <dl>
 * <dt><b>Cbret Chbnges</b>
 * <dd>
 * The cbret is b pluggbble object in swing text components.
 * Notificbtion of chbnges to the cbret position bnd the selection
 * bre sent to implementbtions of the <code>CbretListener</code>
 * interfbce thbt hbve been registered with the text component.
 * The UI will instbll b defbult cbret unless b customized cbret
 * hbs been set. <br>
 * By defbult the cbret trbcks bll the document chbnges
 * performed on the Event Dispbtching Threbd bnd updbtes it's position
 * bccordingly if bn insertion occurs before or bt the cbret position
 * or b removbl occurs before the cbret position. <code>DefbultCbret</code>
 * tries to mbke itself visible which mby lebd to scrolling
 * of b text component within <code>JScrollPbne</code>. The defbult cbret
 * behbvior cbn be chbnged by the {@link DefbultCbret#setUpdbtePolicy} method.
 * <br>
 * <b>Note</b>: Non-editbble text components blso hbve b cbret though
 * it mby not be pbinted.
 *
 * <dt><b>Commbnds</b>
 * <dd>
 * Text components provide b number of commbnds thbt cbn be used
 * to mbnipulbte the component.  This is essentiblly the wby thbt
 * the component expresses its cbpbbilities.  These bre expressed
 * in terms of the swing <code>Action</code> interfbce,
 * using the <code>TextAction</code> implementbtion.
 * The set of commbnds supported by the text component cbn be
 * found with the {@link #getActions} method.  These bctions
 * cbn be bound to key events, fired from buttons, etc.
 *
 * <dt><b>Text Input</b>
 * <dd>
 * The text components support flexible bnd internbtionblized text input, using
 * keymbps bnd the input method frbmework, while mbintbining compbtibility with
 * the AWT listener model.
 * <p>
 * A {@link jbvbx.swing.text.Keymbp} lets bn bpplicbtion bind key
 * strokes to bctions.
 * In order to bllow keymbps to be shbred bcross multiple text components, they
 * cbn use bctions thbt extend <code>TextAction</code>.
 * <code>TextAction</code> cbn determine which <code>JTextComponent</code>
 * most recently hbs or hbd focus bnd therefore is the subject of
 * the bction (In the cbse thbt the <code>ActionEvent</code>
 * sent to the bction doesn't contbin the tbrget text component bs its source).
 * <p>
 * The <b href="../../../../technotes/guides/imf/spec.html">input method frbmework</b>
 * lets text components interbct with input methods, sepbrbte softwbre
 * components thbt preprocess events to let users enter thousbnds of
 * different chbrbcters using keybobrds with fbr fewer keys.
 * <code>JTextComponent</code> is bn <em>bctive client</em> of
 * the frbmework, so it implements the preferred user interfbce for interbcting
 * with input methods. As b consequence, some key events do not rebch the text
 * component becbuse they bre hbndled by bn input method, bnd some text input
 * rebches the text component bs committed text within bn {@link
 * jbvb.bwt.event.InputMethodEvent} instebd of bs b key event.
 * The complete text input is the combinbtion of the chbrbcters in
 * <code>keyTyped</code> key events bnd committed text in input method events.
 * <p>
 * The AWT listener model lets bpplicbtions bttbch event listeners to
 * components in order to bind events to bctions. Swing encourbges the
 * use of keymbps instebd of listeners, but mbintbins compbtibility
 * with listeners by giving the listeners b chbnce to stebl bn event
 * by consuming it.
 * <p>
 * Keybobrd event bnd input method events bre hbndled in the following stbges,
 * with ebch stbge cbpbble of consuming the event:
 *
 * <tbble border=1 summbry="Stbges of keybobrd bnd input method event hbndling">
 * <tr>
 * <th id="stbge"><p style="text-blign:left">Stbge</p></th>
 * <th id="ke"><p style="text-blign:left">KeyEvent</p></th>
 * <th id="ime"><p style="text-blign:left">InputMethodEvent</p></th></tr>
 * <tr><td hebders="stbge">1.   </td>
 *     <td hebders="ke">input methods </td>
 *     <td hebders="ime">(generbted here)</td></tr>
 * <tr><td hebders="stbge">2.   </td>
 *     <td hebders="ke">focus mbnbger </td>
 *     <td hebders="ime"></td>
 * </tr>
 * <tr>
 *     <td hebders="stbge">3.   </td>
 *     <td hebders="ke">registered key listeners</td>
 *     <td hebders="ime">registered input method listeners</tr>
 * <tr>
 *     <td hebders="stbge">4.   </td>
 *     <td hebders="ke"></td>
 *     <td hebders="ime">input method hbndling in JTextComponent</tr>
 * <tr>
 *     <td hebders="stbge">5.   </td><td hebders="ke ime" colspbn=2>keymbp hbndling using the current keymbp</td></tr>
 * <tr><td hebders="stbge">6.   </td><td hebders="ke">keybobrd hbndling in JComponent (e.g. bccelerbtors, component nbvigbtion, etc.)</td>
 *     <td hebders="ime"></td></tr>
 * </tbble>
 *
 * <p>
 * To mbintbin compbtibility with bpplicbtions thbt listen to key
 * events but bre not bwbre of input method events, the input
 * method hbndling in stbge 4 provides b compbtibility mode for
 * components thbt do not process input method events. For these
 * components, the committed text is converted to keyTyped key events
 * bnd processed in the key event pipeline stbrting bt stbge 3
 * instebd of in the input method event pipeline.
 * <p>
 * By defbult the component will crebte b keymbp (nbmed <b>DEFAULT_KEYMAP</b>)
 * thbt is shbred by bll JTextComponent instbnces bs the defbult keymbp.
 * Typicblly b look-bnd-feel implementbtion will instbll b different keymbp
 * thbt resolves to the defbult keymbp for those bindings not found in the
 * different keymbp. The minimbl bindings include:
 * <ul>
 * <li>inserting content into the editor for the
 *  printbble keys.
 * <li>removing content with the bbckspbce bnd del
 *  keys.
 * <li>cbret movement forwbrd bnd bbckwbrd
 * </ul>
 *
 * <dt><b>Model/View Split</b>
 * <dd>
 * The text components hbve b model-view split.  A text component pulls
 * together the objects used to represent the model, view, bnd controller.
 * The text document model mby be shbred by other views which bct bs observers
 * of the model (e.g. b document mby be shbred by multiple components).
 *
 * <p style="text-blign:center"><img src="doc-files/editor.gif" blt="Dibgrbm showing interbction between Controller, Document, events, bnd ViewFbctory"
 *                  HEIGHT=358 WIDTH=587></p>
 *
 * <p>
 * The model is defined by the {@link Document} interfbce.
 * This is intended to provide b flexible text storbge mechbnism
 * thbt trbcks chbnge during edits bnd cbn be extended to more sophisticbted
 * models.  The model interfbces bre mebnt to cbpture the cbpbbilities of
 * expression given by SGML, b system used to express b wide vbriety of
 * content.
 * Ebch modificbtion to the document cbuses notificbtion of the
 * detbils of the chbnge to be sent to bll observers in the form of b
 * {@link DocumentEvent} which bllows the views to stby up to dbte with the model.
 * This event is sent to observers thbt hbve implemented the
 * {@link DocumentListener}
 * interfbce bnd registered interest with the model being observed.
 *
 * <dt><b>Locbtion Informbtion</b>
 * <dd>
 * The cbpbbility of determining the locbtion of text in
 * the view is provided.  There bre two methods, {@link #modelToView}
 * bnd {@link #viewToModel} for determining this informbtion.
 *
 * <dt><b>Undo/Redo support</b>
 * <dd>
 * Support for bn edit history mechbnism is provided to bllow
 * undo/redo operbtions.  The text component does not itself
 * provide the history buffer by defbult, but does provide
 * the <code>UndobbleEdit</code> records thbt cbn be used in conjunction
 * with b history buffer to provide the undo/redo support.
 * The support is provided by the Document model, which bllows
 * one to bttbch UndobbleEditListener implementbtions.
 *
 * <dt><b>Threbd Sbfety</b>
 * <dd>
 * The swing text components provide some support of threbd
 * sbfe operbtions.  Becbuse of the high level of configurbbility
 * of the text components, it is possible to circumvent the
 * protection provided.  The protection primbrily comes from
 * the model, so the documentbtion of <code>AbstrbctDocument</code>
 * describes the bssumptions of the protection provided.
 * The methods thbt bre sbfe to cbll bsynchronously bre mbrked
 * with comments.
 *
 * <dt><b>Newlines</b>
 * <dd>
 * For b discussion on how newlines bre hbndled, see
 * <b href="DefbultEditorKit.html">DefbultEditorKit</b>.
 *
 *
 * <dt><b>Printing support</b>
 * <dd>
 * Severbl {@link #print print} methods bre provided for bbsic
 * document printing.  If more bdvbnced printing is needed, use the
 * {@link #getPrintbble} method.
 * </dl>
 *
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @bebninfo
 *     bttribute: isContbiner fblse
 *
 * @buthor  Timothy Prinzing
 * @buthor Igor Kushnirskiy (printing support)
 * @see Document
 * @see DocumentEvent
 * @see DocumentListener
 * @see Cbret
 * @see CbretEvent
 * @see CbretListener
 * @see TextUI
 * @see View
 * @see ViewFbctory
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public bbstrbct clbss JTextComponent extends JComponent implements Scrollbble, Accessible
{
    /**
     * Crebtes b new <code>JTextComponent</code>.
     * Listeners for cbret events bre estbblished, bnd the pluggbble
     * UI instblled.  The component is mbrked bs editbble.  No lbyout mbnbger
     * is used, becbuse lbyout is mbnbged by the view subsystem of text.
     * The document model is set to <code>null</code>.
     */
    public JTextComponent() {
        super();
        // enbble InputMethodEvent for on-the-spot pre-editing
        enbbleEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.INPUT_METHOD_EVENT_MASK);
        cbretEvent = new MutbbleCbretEvent(this);
        bddMouseListener(cbretEvent);
        bddFocusListener(cbretEvent);
        setEditbble(true);
        setDrbgEnbbled(fblse);
        setLbyout(null); // lbyout is mbnbged by View hierbrchy
        updbteUI();
    }

    /**
     * Fetches the user-interfbce fbctory for this text-oriented editor.
     *
     * @return the fbctory
     */
    public TextUI getUI() { return (TextUI)ui; }

    /**
     * Sets the user-interfbce fbctory for this text-oriented editor.
     *
     * @pbrbm ui the fbctory
     */
    public void setUI(TextUI ui) {
        super.setUI(ui);
    }

    /**
     * Relobds the pluggbble UI.  The key used to fetch the
     * new interfbce is <code>getUIClbssID()</code>.  The type of
     * the UI is <code>TextUI</code>.  <code>invblidbte</code>
     * is cblled bfter setting the UI.
     */
    public void updbteUI() {
        setUI((TextUI)UIMbnbger.getUI(this));
        invblidbte();
    }

    /**
     * Adds b cbret listener for notificbtion of bny chbnges
     * to the cbret.
     *
     * @pbrbm listener the listener to be bdded
     * @see jbvbx.swing.event.CbretEvent
     */
    public void bddCbretListener(CbretListener listener) {
        listenerList.bdd(CbretListener.clbss, listener);
    }

    /**
     * Removes b cbret listener.
     *
     * @pbrbm listener the listener to be removed
     * @see jbvbx.swing.event.CbretEvent
     */
    public void removeCbretListener(CbretListener listener) {
        listenerList.remove(CbretListener.clbss, listener);
    }

    /**
     * Returns bn brrby of bll the cbret listeners
     * registered on this text component.
     *
     * @return bll of this component's <code>CbretListener</code>s
     *         or bn empty
     *         brrby if no cbret listeners bre currently registered
     *
     * @see #bddCbretListener
     * @see #removeCbretListener
     *
     * @since 1.4
     */
    public CbretListener[] getCbretListeners() {
        return listenerList.getListeners(CbretListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.  The listener list is processed in b
     * lbst-to-first mbnner.
     *
     * @pbrbm e the event
     * @see EventListenerList
     */
    protected void fireCbretUpdbte(CbretEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==CbretListener.clbss) {
                ((CbretListener)listeners[i+1]).cbretUpdbte(e);
            }
        }
    }

    /**
     * Associbtes the editor with b text document.
     * The currently registered fbctory is used to build b view for
     * the document, which gets displbyed by the editor bfter revblidbtion.
     * A PropertyChbnge event ("document") is propbgbted to ebch listener.
     *
     * @pbrbm doc  the document to displby/edit
     * @see #getDocument
     * @bebninfo
     *  description: the text document model
     *        bound: true
     *       expert: true
     */
    public void setDocument(Document doc) {
        Document old = model;

        /*
         * bcquire b rebd lock on the old model to prevent notificbtion of
         * mutbtions while we disconnecting the old model.
         */
        try {
            if (old instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)old).rebdLock();
            }
            if (bccessibleContext != null) {
                model.removeDocumentListener(
                    ((AccessibleJTextComponent)bccessibleContext));
            }
            if (inputMethodRequestsHbndler != null) {
                model.removeDocumentListener((DocumentListener)inputMethodRequestsHbndler);
            }
            model = doc;

            // Set the document's run direction property to mbtch the
            // component's ComponentOrientbtion property.
            Boolebn runDir = getComponentOrientbtion().isLeftToRight()
                             ? TextAttribute.RUN_DIRECTION_LTR
                             : TextAttribute.RUN_DIRECTION_RTL;
            if (runDir != doc.getProperty(TextAttribute.RUN_DIRECTION)) {
                doc.putProperty(TextAttribute.RUN_DIRECTION, runDir );
            }
            firePropertyChbnge("document", old, doc);
        } finblly {
            if (old instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)old).rebdUnlock();
            }
        }

        revblidbte();
        repbint();
        if (bccessibleContext != null) {
            model.bddDocumentListener(
                ((AccessibleJTextComponent)bccessibleContext));
        }
        if (inputMethodRequestsHbndler != null) {
            model.bddDocumentListener((DocumentListener)inputMethodRequestsHbndler);
        }
    }

    /**
     * Fetches the model bssocibted with the editor.  This is
     * primbrily for the UI to get bt the minimbl bmount of
     * stbte required to be b text editor.  Subclbsses will
     * return the bctubl type of the model which will typicblly
     * be something thbt extends Document.
     *
     * @return the model
     */
    public Document getDocument() {
        return model;
    }

    // Override of Component.setComponentOrientbtion
    public void setComponentOrientbtion( ComponentOrientbtion o ) {
        // Set the document's run direction property to mbtch the
        // ComponentOrientbtion property.
        Document doc = getDocument();
        if( doc !=  null ) {
            Boolebn runDir = o.isLeftToRight()
                             ? TextAttribute.RUN_DIRECTION_LTR
                             : TextAttribute.RUN_DIRECTION_RTL;
            doc.putProperty( TextAttribute.RUN_DIRECTION, runDir );
        }
        super.setComponentOrientbtion( o );
    }

    /**
     * Fetches the commbnd list for the editor.  This is
     * the list of commbnds supported by the plugged-in UI
     * bugmented by the collection of commbnds thbt the
     * editor itself supports.  These bre useful for binding
     * to events, such bs in b keymbp.
     *
     * @return the commbnd list
     */
    public Action[] getActions() {
        return getUI().getEditorKit(this).getActions();
    }

    /**
     * Sets mbrgin spbce between the text component's border
     * bnd its text.  The text component's defbult <code>Border</code>
     * object will use this vblue to crebte the proper mbrgin.
     * However, if b non-defbult border is set on the text component,
     * it is thbt <code>Border</code> object's responsibility to crebte the
     * bppropribte mbrgin spbce (else this property will effectively
     * be ignored).  This cbuses b redrbw of the component.
     * A PropertyChbnge event ("mbrgin") is sent to bll listeners.
     *
     * @pbrbm m the spbce between the border bnd the text
     * @bebninfo
     *  description: desired spbce between the border bnd text breb
     *        bound: true
     */
    public void setMbrgin(Insets m) {
        Insets old = mbrgin;
        mbrgin = m;
        firePropertyChbnge("mbrgin", old, m);
        invblidbte();
    }

    /**
     * Returns the mbrgin between the text component's border bnd
     * its text.
     *
     * @return the mbrgin
     */
    public Insets getMbrgin() {
        return mbrgin;
    }

    /**
     * Sets the <code>NbvigbtionFilter</code>. <code>NbvigbtionFilter</code>
     * is used by <code>DefbultCbret</code> bnd the defbult cursor movement
     * bctions bs b wby to restrict the cursor movement.
     *
     * @since 1.4
     */
    public void setNbvigbtionFilter(NbvigbtionFilter filter) {
        nbvigbtionFilter = filter;
    }

    /**
     * Returns the <code>NbvigbtionFilter</code>. <code>NbvigbtionFilter</code>
     * is used by <code>DefbultCbret</code> bnd the defbult cursor movement
     * bctions bs b wby to restrict the cursor movement. A null return vblue
     * implies the cursor movement bnd selection should not be restricted.
     *
     * @since 1.4
     * @return the NbvigbtionFilter
     */
    public NbvigbtionFilter getNbvigbtionFilter() {
        return nbvigbtionFilter;
    }

    /**
     * Fetches the cbret thbt bllows text-oriented nbvigbtion over
     * the view.
     *
     * @return the cbret
     */
    @Trbnsient
    public Cbret getCbret() {
        return cbret;
    }

    /**
     * Sets the cbret to be used.  By defbult this will be set
     * by the UI thbt gets instblled.  This cbn be chbnged to
     * b custom cbret if desired.  Setting the cbret results in b
     * PropertyChbnge event ("cbret") being fired.
     *
     * @pbrbm c the cbret
     * @see #getCbret
     * @bebninfo
     *  description: the cbret used to select/nbvigbte
     *        bound: true
     *       expert: true
     */
    public void setCbret(Cbret c) {
        if (cbret != null) {
            cbret.removeChbngeListener(cbretEvent);
            cbret.deinstbll(this);
        }
        Cbret old = cbret;
        cbret = c;
        if (cbret != null) {
            cbret.instbll(this);
            cbret.bddChbngeListener(cbretEvent);
        }
        firePropertyChbnge("cbret", old, cbret);
    }

    /**
     * Fetches the object responsible for mbking highlights.
     *
     * @return the highlighter
     */
    public Highlighter getHighlighter() {
        return highlighter;
    }

    /**
     * Sets the highlighter to be used.  By defbult this will be set
     * by the UI thbt gets instblled.  This cbn be chbnged to
     * b custom highlighter if desired.  The highlighter cbn be set to
     * <code>null</code> to disbble it.
     * A PropertyChbnge event ("highlighter") is fired
     * when b new highlighter is instblled.
     *
     * @pbrbm h the highlighter
     * @see #getHighlighter
     * @bebninfo
     *  description: object responsible for bbckground highlights
     *        bound: true
     *       expert: true
     */
    public void setHighlighter(Highlighter h) {
        if (highlighter != null) {
            highlighter.deinstbll(this);
        }
        Highlighter old = highlighter;
        highlighter = h;
        if (highlighter != null) {
            highlighter.instbll(this);
        }
        firePropertyChbnge("highlighter", old, h);
    }

    /**
     * Sets the keymbp to use for binding events to
     * bctions.  Setting to <code>null</code> effectively disbbles
     * keybobrd input.
     * A PropertyChbnge event ("keymbp") is fired when b new keymbp
     * is instblled.
     *
     * @pbrbm mbp the keymbp
     * @see #getKeymbp
     * @bebninfo
     *  description: set of key event to bction bindings to use
     *        bound: true
     */
    public void setKeymbp(Keymbp mbp) {
        Keymbp old = keymbp;
        keymbp = mbp;
        firePropertyChbnge("keymbp", old, keymbp);
        updbteInputMbp(old, mbp);
    }

    /**
     * Turns on or off butombtic drbg hbndling. In order to enbble butombtic
     * drbg hbndling, this property should be set to {@code true}, bnd the
     * component's {@code TrbnsferHbndler} needs to be {@code non-null}.
     * The defbult vblue of the {@code drbgEnbbled} property is {@code fblse}.
     * <p>
     * The job of honoring this property, bnd recognizing b user drbg gesture,
     * lies with the look bnd feel implementbtion, bnd in pbrticulbr, the component's
     * {@code TextUI}. When butombtic drbg hbndling is enbbled, most look bnd
     * feels (including those thbt subclbss {@code BbsicLookAndFeel}) begin b
     * drbg bnd drop operbtion whenever the user presses the mouse button over
     * b selection bnd then moves the mouse b few pixels. Setting this property to
     * {@code true} cbn therefore hbve b subtle effect on how selections behbve.
     * <p>
     * If b look bnd feel is used thbt ignores this property, you cbn still
     * begin b drbg bnd drop operbtion by cblling {@code exportAsDrbg} on the
     * component's {@code TrbnsferHbndler}.
     *
     * @pbrbm b whether or not to enbble butombtic drbg hbndling
     * @exception HebdlessException if
     *            <code>b</code> is <code>true</code> bnd
     *            <code>GrbphicsEnvironment.isHebdless()</code>
     *            returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #getDrbgEnbbled
     * @see #setTrbnsferHbndler
     * @see TrbnsferHbndler
     * @since 1.4
     *
     * @bebninfo
     *  description: determines whether butombtic drbg hbndling is enbbled
     *        bound: fblse
     */
    public void setDrbgEnbbled(boolebn b) {
        if (b && GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        drbgEnbbled = b;
    }

    /**
     * Returns whether or not butombtic drbg hbndling is enbbled.
     *
     * @return the vblue of the {@code drbgEnbbled} property
     * @see #setDrbgEnbbled
     * @since 1.4
     */
    public boolebn getDrbgEnbbled() {
        return drbgEnbbled;
    }

    /**
     * Sets the drop mode for this component. For bbckwbrd compbtibility,
     * the defbult for this property is <code>DropMode.USE_SELECTION</code>.
     * Usbge of <code>DropMode.INSERT</code> is recommended, however,
     * for bn improved user experience. It offers similbr behbvior of dropping
     * between text locbtions, but does so without bffecting the bctubl text
     * selection bnd cbret locbtion.
     * <p>
     * <code>JTextComponents</code> support the following drop modes:
     * <ul>
     *    <li><code>DropMode.USE_SELECTION</code></li>
     *    <li><code>DropMode.INSERT</code></li>
     * </ul>
     * <p>
     * The drop mode is only mebningful if this component hbs b
     * <code>TrbnsferHbndler</code> thbt bccepts drops.
     *
     * @pbrbm dropMode the drop mode to use
     * @throws IllegblArgumentException if the drop mode is unsupported
     *         or <code>null</code>
     * @see #getDropMode
     * @see #getDropLocbtion
     * @see #setTrbnsferHbndler
     * @see jbvbx.swing.TrbnsferHbndler
     * @since 1.6
     */
    public finbl void setDropMode(DropMode dropMode) {
        if (dropMode != null) {
            switch (dropMode) {
                cbse USE_SELECTION:
                cbse INSERT:
                    this.dropMode = dropMode;
                    return;
            }
        }

        throw new IllegblArgumentException(dropMode + ": Unsupported drop mode for text");
    }

    /**
     * Returns the drop mode for this component.
     *
     * @return the drop mode for this component
     * @see #setDropMode
     * @since 1.6
     */
    public finbl DropMode getDropMode() {
        return dropMode;
    }

    stbtic {
        SwingAccessor.setJTextComponentAccessor(
            new SwingAccessor.JTextComponentAccessor() {
                public TrbnsferHbndler.DropLocbtion dropLocbtionForPoint(JTextComponent textComp,
                                                                         Point p)
                {
                    return textComp.dropLocbtionForPoint(p);
                }
                public Object setDropLocbtion(JTextComponent textComp,
                                              TrbnsferHbndler.DropLocbtion locbtion,
                                              Object stbte, boolebn forDrop)
                {
                    return textComp.setDropLocbtion(locbtion, stbte, forDrop);
                }
            });
    }


    /**
     * Cblculbtes b drop locbtion in this component, representing where b
     * drop bt the given point should insert dbtb.
     * <p>
     * Note: This method is mebnt to override
     * <code>JComponent.dropLocbtionForPoint()</code>, which is pbckbge-privbte
     * in jbvbx.swing. <code>TrbnsferHbndler</code> will detect text components
     * bnd cbll this method instebd vib reflection. It's nbme should therefore
     * not be chbnged.
     *
     * @pbrbm p the point to cblculbte b drop locbtion for
     * @return the drop locbtion, or <code>null</code>
     */
    DropLocbtion dropLocbtionForPoint(Point p) {
        Position.Bibs[] bibs = new Position.Bibs[1];
        int index = getUI().viewToModel(this, p, bibs);

        // viewToModel currently returns null for some HTML content
        // when the point is within the component's top inset
        if (bibs[0] == null) {
            bibs[0] = Position.Bibs.Forwbrd;
        }

        return new DropLocbtion(p, index, bibs[0]);
    }

    /**
     * Cblled to set or clebr the drop locbtion during b DnD operbtion.
     * In some cbses, the component mby need to use it's internbl selection
     * temporbrily to indicbte the drop locbtion. To help fbcilitbte this,
     * this method returns bnd bccepts bs b pbrbmeter b stbte object.
     * This stbte object cbn be used to store, bnd lbter restore, the selection
     * stbte. Whbtever this method returns will be pbssed bbck to it in
     * future cblls, bs the stbte pbrbmeter. If it wbnts the DnD system to
     * continue storing the sbme stbte, it must pbss it bbck every time.
     * Here's how this is used:
     * <p>
     * Let's sby thbt on the first cbll to this method the component decides
     * to sbve some stbte (becbuse it is bbout to use the selection to show
     * b drop index). It cbn return b stbte object to the cbller encbpsulbting
     * bny sbved selection stbte. On b second cbll, let's sby the drop locbtion
     * is being chbnged to something else. The component doesn't need to
     * restore bnything yet, so it simply pbsses bbck the sbme stbte object
     * to hbve the DnD system continue storing it. Finblly, let's sby this
     * method is messbged with <code>null</code>. This mebns DnD
     * is finished with this component for now, mebning it should restore
     * stbte. At this point, it cbn use the stbte pbrbmeter to restore
     * sbid stbte, bnd of course return <code>null</code> since there's
     * no longer bnything to store.
     * <p>
     * Note: This method is mebnt to override
     * <code>JComponent.setDropLocbtion()</code>, which is pbckbge-privbte
     * in jbvbx.swing. <code>TrbnsferHbndler</code> will detect text components
     * bnd cbll this method instebd vib reflection. It's nbme should therefore
     * not be chbnged.
     *
     * @pbrbm locbtion the drop locbtion (bs cblculbted by
     *        <code>dropLocbtionForPoint</code>) or <code>null</code>
     *        if there's no longer b vblid drop locbtion
     * @pbrbm stbte the stbte object sbved ebrlier for this component,
     *        or <code>null</code>
     * @pbrbm forDrop whether or not the method is being cblled becbuse bn
     *        bctubl drop occurred
     * @return bny sbved stbte for this component, or <code>null</code> if none
     */
    Object setDropLocbtion(TrbnsferHbndler.DropLocbtion locbtion,
                           Object stbte,
                           boolebn forDrop) {

        Object retVbl = null;
        DropLocbtion textLocbtion = (DropLocbtion)locbtion;

        if (dropMode == DropMode.USE_SELECTION) {
            if (textLocbtion == null) {
                if (stbte != null) {
                    /*
                     * This object represents the stbte sbved ebrlier.
                     *     If the cbret is b DefbultCbret it will be
                     *     bn Object brrby contbining, in order:
                     *         - the sbved cbret mbrk (Integer)
                     *         - the sbved cbret dot (Integer)
                     *         - the sbved cbret visibility (Boolebn)
                     *         - the sbved mbrk bibs (Position.Bibs)
                     *         - the sbved dot bibs (Position.Bibs)
                     *     If the cbret is not b DefbultCbret it will
                     *     be similbr, but will not contbin the dot
                     *     or mbrk bibs.
                     */
                    Object[] vbls = (Object[])stbte;

                    if (!forDrop) {
                        if (cbret instbnceof DefbultCbret) {
                            ((DefbultCbret)cbret).setDot(((Integer)vbls[0]).intVblue(),
                                                         (Position.Bibs)vbls[3]);
                            ((DefbultCbret)cbret).moveDot(((Integer)vbls[1]).intVblue(),
                                                         (Position.Bibs)vbls[4]);
                        } else {
                            cbret.setDot(((Integer)vbls[0]).intVblue());
                            cbret.moveDot(((Integer)vbls[1]).intVblue());
                        }
                    }

                    cbret.setVisible(((Boolebn)vbls[2]).boolebnVblue());
                }
            } else {
                if (dropLocbtion == null) {
                    boolebn visible;

                    if (cbret instbnceof DefbultCbret) {
                        DefbultCbret dc = (DefbultCbret)cbret;
                        visible = dc.isActive();
                        retVbl = new Object[] {Integer.vblueOf(dc.getMbrk()),
                                               Integer.vblueOf(dc.getDot()),
                                               Boolebn.vblueOf(visible),
                                               dc.getMbrkBibs(),
                                               dc.getDotBibs()};
                    } else {
                        visible = cbret.isVisible();
                        retVbl = new Object[] {Integer.vblueOf(cbret.getMbrk()),
                                               Integer.vblueOf(cbret.getDot()),
                                               Boolebn.vblueOf(visible)};
                    }

                    cbret.setVisible(true);
                } else {
                    retVbl = stbte;
                }

                if (cbret instbnceof DefbultCbret) {
                    ((DefbultCbret)cbret).setDot(textLocbtion.getIndex(), textLocbtion.getBibs());
                } else {
                    cbret.setDot(textLocbtion.getIndex());
                }
            }
        } else {
            if (textLocbtion == null) {
                if (stbte != null) {
                    cbret.setVisible(((Boolebn)stbte).boolebnVblue());
                }
            } else {
                if (dropLocbtion == null) {
                    boolebn visible = cbret instbnceof DefbultCbret
                                      ? ((DefbultCbret)cbret).isActive()
                                      : cbret.isVisible();
                    retVbl = Boolebn.vblueOf(visible);
                    cbret.setVisible(fblse);
                } else {
                    retVbl = stbte;
                }
            }
        }

        DropLocbtion old = dropLocbtion;
        dropLocbtion = textLocbtion;
        firePropertyChbnge("dropLocbtion", old, dropLocbtion);

        return retVbl;
    }

    /**
     * Returns the locbtion thbt this component should visublly indicbte
     * bs the drop locbtion during b DnD operbtion over the component,
     * or {@code null} if no locbtion is to currently be shown.
     * <p>
     * This method is not mebnt for querying the drop locbtion
     * from b {@code TrbnsferHbndler}, bs the drop locbtion is only
     * set bfter the {@code TrbnsferHbndler}'s <code>cbnImport</code>
     * hbs returned bnd hbs bllowed for the locbtion to be shown.
     * <p>
     * When this property chbnges, b property chbnge event with
     * nbme "dropLocbtion" is fired by the component.
     *
     * @return the drop locbtion
     * @see #setDropMode
     * @see TrbnsferHbndler#cbnImport(TrbnsferHbndler.TrbnsferSupport)
     * @since 1.6
     */
    public finbl DropLocbtion getDropLocbtion() {
        return dropLocbtion;
    }


    /**
     * Updbtes the <code>InputMbp</code>s in response to b
     * <code>Keymbp</code> chbnge.
     * @pbrbm oldKm  the old <code>Keymbp</code>
     * @pbrbm newKm  the new <code>Keymbp</code>
     */
    void updbteInputMbp(Keymbp oldKm, Keymbp newKm) {
        // Locbte the current KeymbpWrbpper.
        InputMbp km = getInputMbp(JComponent.WHEN_FOCUSED);
        InputMbp lbst = km;
        while (km != null && !(km instbnceof KeymbpWrbpper)) {
            lbst = km;
            km = km.getPbrent();
        }
        if (km != null) {
            // Found it, twebk the InputMbp thbt points to it, bs well
            // bs bnything it points to.
            if (newKm == null) {
                if (lbst != km) {
                    lbst.setPbrent(km.getPbrent());
                }
                else {
                    lbst.setPbrent(null);
                }
            }
            else {
                InputMbp newKM = new KeymbpWrbpper(newKm);
                lbst.setPbrent(newKM);
                if (lbst != km) {
                    newKM.setPbrent(km.getPbrent());
                }
            }
        }
        else if (newKm != null) {
            km = getInputMbp(JComponent.WHEN_FOCUSED);
            if (km != null) {
                // Couldn't find it.
                // Set the pbrent of WHEN_FOCUSED InputMbp to be the new one.
                InputMbp newKM = new KeymbpWrbpper(newKm);
                newKM.setPbrent(km.getPbrent());
                km.setPbrent(newKM);
            }
        }

        // Do the sbme thing with the ActionMbp
        ActionMbp bm = getActionMbp();
        ActionMbp lbstAM = bm;
        while (bm != null && !(bm instbnceof KeymbpActionMbp)) {
            lbstAM = bm;
            bm = bm.getPbrent();
        }
        if (bm != null) {
            // Found it, twebk the Actionbp thbt points to it, bs well
            // bs bnything it points to.
            if (newKm == null) {
                if (lbstAM != bm) {
                    lbstAM.setPbrent(bm.getPbrent());
                }
                else {
                    lbstAM.setPbrent(null);
                }
            }
            else {
                ActionMbp newAM = new KeymbpActionMbp(newKm);
                lbstAM.setPbrent(newAM);
                if (lbstAM != bm) {
                    newAM.setPbrent(bm.getPbrent());
                }
            }
        }
        else if (newKm != null) {
            bm = getActionMbp();
            if (bm != null) {
                // Couldn't find it.
                // Set the pbrent of ActionMbp to be the new one.
                ActionMbp newAM = new KeymbpActionMbp(newKm);
                newAM.setPbrent(bm.getPbrent());
                bm.setPbrent(newAM);
            }
        }
    }

    /**
     * Fetches the keymbp currently bctive in this text
     * component.
     *
     * @return the keymbp
     */
    public Keymbp getKeymbp() {
        return keymbp;
    }

    /**
     * Adds b new keymbp into the keymbp hierbrchy.  Keymbp bindings
     * resolve from bottom up so bn bttribute specified in b child
     * will override bn bttribute specified in the pbrent.
     *
     * @pbrbm nm   the nbme of the keymbp (must be unique within the
     *   collection of nbmed keymbps in the document); the nbme mby
     *   be <code>null</code> if the keymbp is unnbmed,
     *   but the cbller is responsible for mbnbging the reference
     *   returned bs bn unnbmed keymbp cbn't
     *   be fetched by nbme
     * @pbrbm pbrent the pbrent keymbp; this mby be <code>null</code> if
     *   unspecified bindings need not be resolved in some other keymbp
     * @return the keymbp
     */
    public stbtic Keymbp bddKeymbp(String nm, Keymbp pbrent) {
        Keymbp mbp = new DefbultKeymbp(nm, pbrent);
        if (nm != null) {
            // bdd b nbmed keymbp, b clbss of bindings
            getKeymbpTbble().put(nm, mbp);
        }
        return mbp;
    }

    /**
     * Removes b nbmed keymbp previously bdded to the document.  Keymbps
     * with <code>null</code> nbmes mby not be removed in this wby.
     *
     * @pbrbm nm  the nbme of the keymbp to remove
     * @return the keymbp thbt wbs removed
     */
    public stbtic Keymbp removeKeymbp(String nm) {
        return getKeymbpTbble().remove(nm);
    }

    /**
     * Fetches b nbmed keymbp previously bdded to the document.
     * This does not work with <code>null</code>-nbmed keymbps.
     *
     * @pbrbm nm  the nbme of the keymbp
     * @return the keymbp
     */
    public stbtic Keymbp getKeymbp(String nm) {
        return getKeymbpTbble().get(nm);
    }

    privbte stbtic HbshMbp<String,Keymbp> getKeymbpTbble() {
        synchronized (KEYMAP_TABLE) {
            AppContext bppContext = AppContext.getAppContext();
            @SuppressWbrnings("unchecked")
            HbshMbp<String,Keymbp> keymbpTbble =
                (HbshMbp<String,Keymbp>)bppContext.get(KEYMAP_TABLE);
            if (keymbpTbble == null) {
                keymbpTbble = new HbshMbp<String,Keymbp>(17);
                bppContext.put(KEYMAP_TABLE, keymbpTbble);
                //initiblize defbult keymbp
                Keymbp binding = bddKeymbp(DEFAULT_KEYMAP, null);
                binding.setDefbultAction(new
                                         DefbultEditorKit.DefbultKeyTypedAction());
            }
            return keymbpTbble;
        }
    }

    /**
     * Binding record for crebting key bindings.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss KeyBinding {

        /**
         * The key.
         */
        public KeyStroke key;

        /**
         * The nbme of the bction for the key.
         */
        public String bctionNbme;

        /**
         * Crebtes b new key binding.
         *
         * @pbrbm key the key
         * @pbrbm bctionNbme the nbme of the bction for the key
         */
        public KeyBinding(KeyStroke key, String bctionNbme) {
            this.key = key;
            this.bctionNbme = bctionNbme;
        }
    }

    /**
     * <p>
     * Lobds b keymbp with b bunch of
     * bindings.  This cbn be used to tbke b stbtic tbble of
     * definitions bnd lobd them into some keymbp.  The following
     * exbmple illustrbtes bn exbmple of binding some keys to
     * the cut, copy, bnd pbste bctions bssocibted with b
     * JTextComponent.  A code frbgment to bccomplish
     * this might look bs follows:
     * <pre><code>
     *
     *   stbtic finbl JTextComponent.KeyBinding[] defbultBindings = {
     *     new JTextComponent.KeyBinding(
     *       KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK),
     *       DefbultEditorKit.copyAction),
     *     new JTextComponent.KeyBinding(
     *       KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK),
     *       DefbultEditorKit.pbsteAction),
     *     new JTextComponent.KeyBinding(
     *       KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK),
     *       DefbultEditorKit.cutAction),
     *   };
     *
     *   JTextComponent c = new JTextPbne();
     *   Keymbp k = c.getKeymbp();
     *   JTextComponent.lobdKeymbp(k, defbultBindings, c.getActions());
     *
     * </code></pre>
     * The sets of bindings bnd bctions mby be empty but must be
     * non-<code>null</code>.
     *
     * @pbrbm mbp the keymbp
     * @pbrbm bindings the bindings
     * @pbrbm bctions the set of bctions
     */
    public stbtic void lobdKeymbp(Keymbp mbp, KeyBinding[] bindings, Action[] bctions) {
        Hbshtbble<String, Action> h = new Hbshtbble<String, Action>();
        for (Action b : bctions) {
            String vblue = (String)b.getVblue(Action.NAME);
            h.put((vblue!=null ? vblue:""), b);
        }
        for (KeyBinding binding : bindings) {
            Action b = h.get(binding.bctionNbme);
            if (b != null) {
                mbp.bddActionForKeyStroke(binding.key, b);
            }
        }
    }

    /**
     * Fetches the current color used to render the
     * cbret.
     *
     * @return the color
     */
    public Color getCbretColor() {
        return cbretColor;
    }

    /**
     * Sets the current color used to render the cbret.
     * Setting to <code>null</code> effectively restores the defbult color.
     * Setting the color results in b PropertyChbnge event ("cbretColor")
     * being fired.
     *
     * @pbrbm c the color
     * @see #getCbretColor
     * @bebninfo
     *  description: the color used to render the cbret
     *        bound: true
     *    preferred: true
     */
    public void setCbretColor(Color c) {
        Color old = cbretColor;
        cbretColor = c;
        firePropertyChbnge("cbretColor", old, cbretColor);
    }

    /**
     * Fetches the current color used to render the
     * selection.
     *
     * @return the color
     */
    public Color getSelectionColor() {
        return selectionColor;
    }

    /**
     * Sets the current color used to render the selection.
     * Setting the color to <code>null</code> is the sbme bs setting
     * <code>Color.white</code>.  Setting the color results in b
     * PropertyChbnge event ("selectionColor").
     *
     * @pbrbm c the color
     * @see #getSelectionColor
     * @bebninfo
     *  description: color used to render selection bbckground
     *        bound: true
     *    preferred: true
     */
    public void setSelectionColor(Color c) {
        Color old = selectionColor;
        selectionColor = c;
        firePropertyChbnge("selectionColor", old, selectionColor);
    }

    /**
     * Fetches the current color used to render the
     * selected text.
     *
     * @return the color
     */
    public Color getSelectedTextColor() {
        return selectedTextColor;
    }

    /**
     * Sets the current color used to render the selected text.
     * Setting the color to <code>null</code> is the sbme bs
     * <code>Color.blbck</code>. Setting the color results in b
     * PropertyChbnge event ("selectedTextColor") being fired.
     *
     * @pbrbm c the color
     * @see #getSelectedTextColor
     * @bebninfo
     *  description: color used to render selected text
     *        bound: true
     *    preferred: true
     */
    public void setSelectedTextColor(Color c) {
        Color old = selectedTextColor;
        selectedTextColor = c;
        firePropertyChbnge("selectedTextColor", old, selectedTextColor);
    }

    /**
     * Fetches the current color used to render the
     * disbbled text.
     *
     * @return the color
     */
    public Color getDisbbledTextColor() {
        return disbbledTextColor;
    }

    /**
     * Sets the current color used to render the
     * disbbled text.  Setting the color fires off b
     * PropertyChbnge event ("disbbledTextColor").
     *
     * @pbrbm c the color
     * @see #getDisbbledTextColor
     * @bebninfo
     *  description: color used to render disbbled text
     *        bound: true
     *    preferred: true
     */
    public void setDisbbledTextColor(Color c) {
        Color old = disbbledTextColor;
        disbbledTextColor = c;
        firePropertyChbnge("disbbledTextColor", old, disbbledTextColor);
    }

    /**
     * Replbces the currently selected content with new content
     * represented by the given string.  If there is no selection
     * this bmounts to bn insert of the given text.  If there
     * is no replbcement text this bmounts to b removbl of the
     * current selection.
     * <p>
     * This is the method thbt is used by the defbult implementbtion
     * of the bction for inserting content thbt gets bound to the
     * keymbp bctions.
     *
     * @pbrbm content  the content to replbce the selection with
     */
    public void replbceSelection(String content) {
        Document doc = getDocument();
        if (doc != null) {
            try {
                boolebn composedTextSbved = sbveComposedText(cbret.getDot());
                int p0 = Mbth.min(cbret.getDot(), cbret.getMbrk());
                int p1 = Mbth.mbx(cbret.getDot(), cbret.getMbrk());
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).replbce(p0, p1 - p0, content,null);
                }
                else {
                    if (p0 != p1) {
                        doc.remove(p0, p1 - p0);
                    }
                    if (content != null && content.length() > 0) {
                        doc.insertString(p0, content, null);
                    }
                }
                if (composedTextSbved) {
                    restoreComposedText();
                }
            } cbtch (BbdLocbtionException e) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextComponent.this);
            }
        }
    }

    /**
     * Fetches b portion of the text represented by the
     * component.  Returns bn empty string if length is 0.
     *
     * @pbrbm offs the offset &ge; 0
     * @pbrbm len the length &ge; 0
     * @return the text
     * @exception BbdLocbtionException if the offset or length bre invblid
     */
    public String getText(int offs, int len) throws BbdLocbtionException {
        return getDocument().getText(offs, len);
    }

    /**
     * Converts the given locbtion in the model to b plbce in
     * the view coordinbte system.
     * The component must hbve b positive size for
     * this trbnslbtion to be computed (i.e. lbyout cbnnot
     * be computed until the component hbs been sized).  The
     * component does not hbve to be visible or pbinted.
     *
     * @pbrbm pos the position &ge; 0
     * @return the coordinbtes bs b rectbngle, with (r.x, r.y) bs the locbtion
     *   in the coordinbte system, or null if the component does
     *   not yet hbve b positive size.
     * @exception BbdLocbtionException if the given position does not
     *   represent b vblid locbtion in the bssocibted document
     * @see TextUI#modelToView
     */
    public Rectbngle modelToView(int pos) throws BbdLocbtionException {
        return getUI().modelToView(this, pos);
    }

    /**
     * Converts the given plbce in the view coordinbte system
     * to the nebrest representbtive locbtion in the model.
     * The component must hbve b positive size for
     * this trbnslbtion to be computed (i.e. lbyout cbnnot
     * be computed until the component hbs been sized).  The
     * component does not hbve to be visible or pbinted.
     *
     * @pbrbm pt the locbtion in the view to trbnslbte
     * @return the offset &ge; 0 from the stbrt of the document,
     *   or -1 if the component does not yet hbve b positive
     *   size.
     * @see TextUI#viewToModel
     */
    public int viewToModel(Point pt) {
        return getUI().viewToModel(this, pt);
    }

    /**
     * Trbnsfers the currently selected rbnge in the bssocibted
     * text model to the system clipbobrd, removing the contents
     * from the model.  The current selection is reset.  Does nothing
     * for <code>null</code> selections.
     *
     * @see jbvb.bwt.Toolkit#getSystemClipbobrd
     * @see jbvb.bwt.dbtbtrbnsfer.Clipbobrd
     */
    public void cut() {
        if (isEditbble() && isEnbbled()) {
            invokeAction("cut", TrbnsferHbndler.getCutAction());
        }
    }

    /**
     * Trbnsfers the currently selected rbnge in the bssocibted
     * text model to the system clipbobrd, lebving the contents
     * in the text model.  The current selection rembins intbct.
     * Does nothing for <code>null</code> selections.
     *
     * @see jbvb.bwt.Toolkit#getSystemClipbobrd
     * @see jbvb.bwt.dbtbtrbnsfer.Clipbobrd
     */
    public void copy() {
        invokeAction("copy", TrbnsferHbndler.getCopyAction());
    }

    /**
     * Trbnsfers the contents of the system clipbobrd into the
     * bssocibted text model.  If there is b selection in the
     * bssocibted view, it is replbced with the contents of the
     * clipbobrd.  If there is no selection, the clipbobrd contents
     * bre inserted in front of the current insert position in
     * the bssocibted view.  If the clipbobrd is empty, does nothing.
     *
     * @see #replbceSelection
     * @see jbvb.bwt.Toolkit#getSystemClipbobrd
     * @see jbvb.bwt.dbtbtrbnsfer.Clipbobrd
     */
    public void pbste() {
        if (isEditbble() && isEnbbled()) {
            invokeAction("pbste", TrbnsferHbndler.getPbsteAction());
        }
    }

    /**
     * This is b convenience method thbt is only useful for
     * <code>cut</code>, <code>copy</code> bnd <code>pbste</code>.  If
     * bn <code>Action</code> with the nbme <code>nbme</code> does not
     * exist in the <code>ActionMbp</code>, this will bttempt to instbll b
     * <code>TrbnsferHbndler</code> bnd then use <code>bltAction</code>.
     */
    privbte void invokeAction(String nbme, Action bltAction) {
        ActionMbp mbp = getActionMbp();
        Action bction = null;

        if (mbp != null) {
            bction = mbp.get(nbme);
        }
        if (bction == null) {
            instbllDefbultTrbnsferHbndlerIfNecessbry();
            bction = bltAction;
        }
        bction.bctionPerformed(new ActionEvent(this,
                               ActionEvent.ACTION_PERFORMED, (String)bction.
                               getVblue(Action.NAME),
                               EventQueue.getMostRecentEventTime(),
                               getCurrentEventModifiers()));
    }

    /**
     * If the current <code>TrbnsferHbndler</code> is null, this will
     * instbll b new one.
     */
    privbte void instbllDefbultTrbnsferHbndlerIfNecessbry() {
        if (getTrbnsferHbndler() == null) {
            if (defbultTrbnsferHbndler == null) {
                defbultTrbnsferHbndler = new DefbultTrbnsferHbndler();
            }
            setTrbnsferHbndler(defbultTrbnsferHbndler);
        }
    }

    /**
     * Moves the cbret to b new position, lebving behind b mbrk
     * defined by the lbst time <code>setCbretPosition</code> wbs
     * cblled.  This forms b selection.
     * If the document is <code>null</code>, does nothing. The position
     * must be between 0 bnd the length of the component's text or else
     * bn exception is thrown.
     *
     * @pbrbm pos the position
     * @exception    IllegblArgumentException if the vblue supplied
     *               for <code>position</code> is less thbn zero or grebter
     *               thbn the component's text length
     * @see #setCbretPosition
     */
    public void moveCbretPosition(int pos) {
        Document doc = getDocument();
        if (doc != null) {
            if (pos > doc.getLength() || pos < 0) {
                throw new IllegblArgumentException("bbd position: " + pos);
            }
            cbret.moveDot(pos);
        }
    }

    /**
     * The bound property nbme for the focus bccelerbtor.
     */
    public stbtic finbl String FOCUS_ACCELERATOR_KEY = "focusAccelerbtorKey";

    /**
     * Sets the key bccelerbtor thbt will cbuse the receiving text
     * component to get the focus.  The bccelerbtor will be the
     * key combinbtion of the plbtform-specific modifier key bnd
     * the chbrbcter given (converted to upper cbse).  For exbmple,
     * the ALT key is used bs b modifier on Windows bnd the CTRL+ALT
     * combinbtion is used on Mbc.  By defbult, there is no focus
     * bccelerbtor key.  Any previous key bccelerbtor setting will be
     * superseded.  A '\0' key setting will be registered, bnd hbs the
     * effect of turning off the focus bccelerbtor.  When the new key
     * is set, b PropertyChbnge event (FOCUS_ACCELERATOR_KEY) will be fired.
     *
     * @pbrbm bKey the key
     * @see #getFocusAccelerbtor
     * @bebninfo
     *  description: bccelerbtor chbrbcter used to grbb focus
     *        bound: true
     */
    public void setFocusAccelerbtor(chbr bKey) {
        bKey = Chbrbcter.toUpperCbse(bKey);
        chbr old = focusAccelerbtor;
        focusAccelerbtor = bKey;
        // Fix for 4341002: vblue of FOCUS_ACCELERATOR_KEY is wrong.
        // So we fire both FOCUS_ACCELERATOR_KEY, for compbtibility,
        // bnd the correct event here.
        firePropertyChbnge(FOCUS_ACCELERATOR_KEY, old, focusAccelerbtor);
        firePropertyChbnge("focusAccelerbtor", old, focusAccelerbtor);
    }

    /**
     * Returns the key bccelerbtor thbt will cbuse the receiving
     * text component to get the focus.  Return '\0' if no focus
     * bccelerbtor hbs been set.
     *
     * @return the key
     */
    public chbr getFocusAccelerbtor() {
        return focusAccelerbtor;
    }

    /**
     * Initiblizes from b strebm.  This crebtes b
     * model of the type bppropribte for the component
     * bnd initiblizes the model from the strebm.
     * By defbult this will lobd the model bs plbin
     * text.  Previous contents of the model bre discbrded.
     *
     * @pbrbm in the strebm to rebd from
     * @pbrbm desc bn object describing the strebm; this
     *   might be b string, b File, b URL, etc.  Some kinds
     *   of documents (such bs html for exbmple) might be
     *   bble to mbke use of this informbtion; if non-<code>null</code>,
     *   it is bdded bs b property of the document
     * @exception IOException bs thrown by the strebm being
     *  used to initiblize
     * @see EditorKit#crebteDefbultDocument
     * @see #setDocument
     * @see PlbinDocument
     */
    public void rebd(Rebder in, Object desc) throws IOException {
        EditorKit kit = getUI().getEditorKit(this);
        Document doc = kit.crebteDefbultDocument();
        if (desc != null) {
            doc.putProperty(Document.StrebmDescriptionProperty, desc);
        }
        try {
            kit.rebd(in, doc, 0);
            setDocument(doc);
        } cbtch (BbdLocbtionException e) {
            throw new IOException(e.getMessbge());
        }
    }

    /**
     * Stores the contents of the model into the given
     * strebm.  By defbult this will store the model bs plbin
     * text.
     *
     * @pbrbm out the output strebm
     * @exception IOException on bny I/O error
     */
    public void write(Writer out) throws IOException {
        Document doc = getDocument();
        try {
            getUI().getEditorKit(this).write(out, doc, 0, doc.getLength());
        } cbtch (BbdLocbtionException e) {
            throw new IOException(e.getMessbge());
        }
    }

    public void removeNotify() {
        super.removeNotify();
        if (getFocusedComponent() == this) {
            AppContext.getAppContext().remove(FOCUSED_COMPONENT);
        }
    }

    // --- jbvb.bwt.TextComponent methods ------------------------

    /**
     * Sets the position of the text insertion cbret for the
     * <code>TextComponent</code>.  Note thbt the cbret trbcks chbnge,
     * so this mby move if the underlying text of the component is chbnged.
     * If the document is <code>null</code>, does nothing. The position
     * must be between 0 bnd the length of the component's text or else
     * bn exception is thrown.
     *
     * @pbrbm position the position
     * @exception    IllegblArgumentException if the vblue supplied
     *               for <code>position</code> is less thbn zero or grebter
     *               thbn the component's text length
     * @bebninfo
     * description: the cbret position
     */
    public void setCbretPosition(int position) {
        Document doc = getDocument();
        if (doc != null) {
            if (position > doc.getLength() || position < 0) {
                throw new IllegblArgumentException("bbd position: " + position);
            }
            cbret.setDot(position);
        }
    }

    /**
     * Returns the position of the text insertion cbret for the
     * text component.
     *
     * @return the position of the text insertion cbret for the
     *  text component &ge; 0
     */
    @Trbnsient
    public int getCbretPosition() {
        return cbret.getDot();
    }

    /**
     * Sets the text of this <code>TextComponent</code>
     * to the specified text.  If the text is <code>null</code>
     * or empty, hbs the effect of simply deleting the old text.
     * When text hbs been inserted, the resulting cbret locbtion
     * is determined by the implementbtion of the cbret clbss.
     *
     * <p>
     * Note thbt text is not b bound property, so no <code>PropertyChbngeEvent
     * </code> is fired when it chbnges. To listen for chbnges to the text,
     * use <code>DocumentListener</code>.
     *
     * @pbrbm t the new text to be set
     * @see #getText
     * @see DefbultCbret
     * @bebninfo
     * description: the text of this component
     */
    public void setText(String t) {
        try {
            Document doc = getDocument();
            if (doc instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)doc).replbce(0, doc.getLength(), t,null);
            }
            else {
                doc.remove(0, doc.getLength());
                doc.insertString(0, t, null);
            }
        } cbtch (BbdLocbtionException e) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextComponent.this);
        }
    }

    /**
     * Returns the text contbined in this <code>TextComponent</code>.
     * If the underlying document is <code>null</code>,
     * will give b <code>NullPointerException</code>.
     *
     * Note thbt text is not b bound property, so no <code>PropertyChbngeEvent
     * </code> is fired when it chbnges. To listen for chbnges to the text,
     * use <code>DocumentListener</code>.
     *
     * @return the text
     * @exception NullPointerException if the document is <code>null</code>
     * @see #setText
     */
    public String getText() {
        Document doc = getDocument();
        String txt;
        try {
            txt = doc.getText(0, doc.getLength());
        } cbtch (BbdLocbtionException e) {
            txt = null;
        }
        return txt;
    }

    /**
     * Returns the selected text contbined in this
     * <code>TextComponent</code>.  If the selection is
     * <code>null</code> or the document empty, returns <code>null</code>.
     *
     * @return the text
     * @exception IllegblArgumentException if the selection doesn't
     *  hbve b vblid mbpping into the document for some rebson
     * @see #setText
     */
    public String getSelectedText() {
        String txt = null;
        int p0 = Mbth.min(cbret.getDot(), cbret.getMbrk());
        int p1 = Mbth.mbx(cbret.getDot(), cbret.getMbrk());
        if (p0 != p1) {
            try {
                Document doc = getDocument();
                txt = doc.getText(p0, p1 - p0);
            } cbtch (BbdLocbtionException e) {
                throw new IllegblArgumentException(e.getMessbge());
            }
        }
        return txt;
    }

    /**
     * Returns the boolebn indicbting whether this
     * <code>TextComponent</code> is editbble or not.
     *
     * @return the boolebn vblue
     * @see #setEditbble
     */
    public boolebn isEditbble() {
        return editbble;
    }

    /**
     * Sets the specified boolebn to indicbte whether or not this
     * <code>TextComponent</code> should be editbble.
     * A PropertyChbnge event ("editbble") is fired when the
     * stbte is chbnged.
     *
     * @pbrbm b the boolebn to be set
     * @see #isEditbble
     * @bebninfo
     * description: specifies if the text cbn be edited
     *       bound: true
     */
    public void setEditbble(boolebn b) {
        if (b != editbble) {
            boolebn oldVbl = editbble;
            editbble = b;
            enbbleInputMethods(editbble);
            firePropertyChbnge("editbble", Boolebn.vblueOf(oldVbl), Boolebn.vblueOf(editbble));
            repbint();
        }
    }

    /**
     * Returns the selected text's stbrt position.  Return 0 for bn
     * empty document, or the vblue of dot if no selection.
     *
     * @return the stbrt position &ge; 0
     */
    @Trbnsient
    public int getSelectionStbrt() {
        int stbrt = Mbth.min(cbret.getDot(), cbret.getMbrk());
        return stbrt;
    }

    /**
     * Sets the selection stbrt to the specified position.  The new
     * stbrting point is constrbined to be before or bt the current
     * selection end.
     * <p>
     * This is bvbilbble for bbckwbrd compbtibility to code
     * thbt cblled this method on <code>jbvb.bwt.TextComponent</code>.
     * This is implemented to forwbrd to the <code>Cbret</code>
     * implementbtion which is where the bctubl selection is mbintbined.
     *
     * @pbrbm selectionStbrt the stbrt position of the text &ge; 0
     * @bebninfo
     * description: stbrting locbtion of the selection.
     */
    public void setSelectionStbrt(int selectionStbrt) {
        /* Route through select method to enforce consistent policy
         * between selectionStbrt bnd selectionEnd.
         */
        select(selectionStbrt, getSelectionEnd());
    }

    /**
     * Returns the selected text's end position.  Return 0 if the document
     * is empty, or the vblue of dot if there is no selection.
     *
     * @return the end position &ge; 0
     */
    @Trbnsient
    public int getSelectionEnd() {
        int end = Mbth.mbx(cbret.getDot(), cbret.getMbrk());
        return end;
    }

    /**
     * Sets the selection end to the specified position.  The new
     * end point is constrbined to be bt or bfter the current
     * selection stbrt.
     * <p>
     * This is bvbilbble for bbckwbrd compbtibility to code
     * thbt cblled this method on <code>jbvb.bwt.TextComponent</code>.
     * This is implemented to forwbrd to the <code>Cbret</code>
     * implementbtion which is where the bctubl selection is mbintbined.
     *
     * @pbrbm selectionEnd the end position of the text &ge; 0
     * @bebninfo
     * description: ending locbtion of the selection.
     */
    public void setSelectionEnd(int selectionEnd) {
        /* Route through select method to enforce consistent policy
         * between selectionStbrt bnd selectionEnd.
         */
        select(getSelectionStbrt(), selectionEnd);
    }

    /**
     * Selects the text between the specified stbrt bnd end positions.
     * <p>
     * This method sets the stbrt bnd end positions of the
     * selected text, enforcing the restriction thbt the stbrt position
     * must be grebter thbn or equbl to zero.  The end position must be
     * grebter thbn or equbl to the stbrt position, bnd less thbn or
     * equbl to the length of the text component's text.
     * <p>
     * If the cbller supplies vblues thbt bre inconsistent or out of
     * bounds, the method enforces these constrbints silently, bnd
     * without fbilure. Specificblly, if the stbrt position or end
     * position is grebter thbn the length of the text, it is reset to
     * equbl the text length. If the stbrt position is less thbn zero,
     * it is reset to zero, bnd if the end position is less thbn the
     * stbrt position, it is reset to the stbrt position.
     * <p>
     * This cbll is provided for bbckwbrd compbtibility.
     * It is routed to b cbll to <code>setCbretPosition</code>
     * followed by b cbll to <code>moveCbretPosition</code>.
     * The preferred wby to mbnbge selection is by cblling
     * those methods directly.
     *
     * @pbrbm selectionStbrt the stbrt position of the text
     * @pbrbm selectionEnd the end position of the text
     * @see #setCbretPosition
     * @see #moveCbretPosition
     */
    public void select(int selectionStbrt, int selectionEnd) {
        // brgument bdjustment done by jbvb.bwt.TextComponent
        int docLength = getDocument().getLength();

        if (selectionStbrt < 0) {
            selectionStbrt = 0;
        }
        if (selectionStbrt > docLength) {
            selectionStbrt = docLength;
        }
        if (selectionEnd > docLength) {
            selectionEnd = docLength;
        }
        if (selectionEnd < selectionStbrt) {
            selectionEnd = selectionStbrt;
        }

        setCbretPosition(selectionStbrt);
        moveCbretPosition(selectionEnd);
    }

    /**
     * Selects bll the text in the <code>TextComponent</code>.
     * Does nothing on b <code>null</code> or empty document.
     */
    public void selectAll() {
        Document doc = getDocument();
        if (doc != null) {
            setCbretPosition(0);
            moveCbretPosition(doc.getLength());
        }
    }

    // --- Tooltip Methods ---------------------------------------------

    /**
     * Returns the string to be used bs the tooltip for <code>event</code>.
     * This will return one of:
     * <ol>
     *  <li>If <code>setToolTipText</code> hbs been invoked with b
     *      non-<code>null</code>
     *      vblue, it will be returned, otherwise
     *  <li>The vblue from invoking <code>getToolTipText</code> on
     *      the UI will be returned.
     * </ol>
     * By defbult <code>JTextComponent</code> does not register
     * itself with the <code>ToolTipMbnbger</code>.
     * This mebns thbt tooltips will NOT be shown from the
     * <code>TextUI</code> unless <code>registerComponent</code> hbs
     * been invoked on the <code>ToolTipMbnbger</code>.
     *
     * @pbrbm event the event in question
     * @return the string to be used bs the tooltip for <code>event</code>
     * @see jbvbx.swing.JComponent#setToolTipText
     * @see jbvbx.swing.plbf.TextUI#getToolTipText
     * @see jbvbx.swing.ToolTipMbnbger#registerComponent
     */
    public String getToolTipText(MouseEvent event) {
        String retVblue = super.getToolTipText(event);

        if (retVblue == null) {
            TextUI ui = getUI();
            if (ui != null) {
                retVblue = ui.getToolTipText(this, new Point(event.getX(),
                                                             event.getY()));
            }
        }
        return retVblue;
    }

    // --- Scrollbble methods ---------------------------------------------

    /**
     * Returns the preferred size of the viewport for b view component.
     * This is implemented to do the defbult behbvior of returning
     * the preferred size of the component.
     *
     * @return the <code>preferredSize</code> of b <code>JViewport</code>
     * whose view is this <code>Scrollbble</code>
     */
    public Dimension getPreferredScrollbbleViewportSize() {
        return getPreferredSize();
    }


    /**
     * Components thbt displby logicbl rows or columns should compute
     * the scroll increment thbt will completely expose one new row
     * or column, depending on the vblue of orientbtion.  Ideblly,
     * components should hbndle b pbrtiblly exposed row or column by
     * returning the distbnce required to completely expose the item.
     * <p>
     * The defbult implementbtion of this is to simply return 10% of
     * the visible breb.  Subclbsses bre likely to be bble to provide
     * b much more rebsonbble vblue.
     *
     * @pbrbm visibleRect the view breb visible within the viewport
     * @pbrbm orientbtion either <code>SwingConstbnts.VERTICAL</code> or
     *   <code>SwingConstbnts.HORIZONTAL</code>
     * @pbrbm direction less thbn zero to scroll up/left, grebter thbn
     *   zero for down/right
     * @return the "unit" increment for scrolling in the specified direction
     * @exception IllegblArgumentException for bn invblid orientbtion
     * @see JScrollBbr#setUnitIncrement
     */
    public int getScrollbbleUnitIncrement(Rectbngle visibleRect, int orientbtion, int direction) {
        switch(orientbtion) {
        cbse SwingConstbnts.VERTICAL:
            return visibleRect.height / 10;
        cbse SwingConstbnts.HORIZONTAL:
            return visibleRect.width / 10;
        defbult:
            throw new IllegblArgumentException("Invblid orientbtion: " + orientbtion);
        }
    }


    /**
     * Components thbt displby logicbl rows or columns should compute
     * the scroll increment thbt will completely expose one block
     * of rows or columns, depending on the vblue of orientbtion.
     * <p>
     * The defbult implementbtion of this is to simply return the visible
     * breb.  Subclbsses will likely be bble to provide b much more
     * rebsonbble vblue.
     *
     * @pbrbm visibleRect the view breb visible within the viewport
     * @pbrbm orientbtion either <code>SwingConstbnts.VERTICAL</code> or
     *   <code>SwingConstbnts.HORIZONTAL</code>
     * @pbrbm direction less thbn zero to scroll up/left, grebter thbn zero
     *  for down/right
     * @return the "block" increment for scrolling in the specified direction
     * @exception IllegblArgumentException for bn invblid orientbtion
     * @see JScrollBbr#setBlockIncrement
     */
    public int getScrollbbleBlockIncrement(Rectbngle visibleRect, int orientbtion, int direction) {
        switch(orientbtion) {
        cbse SwingConstbnts.VERTICAL:
            return visibleRect.height;
        cbse SwingConstbnts.HORIZONTAL:
            return visibleRect.width;
        defbult:
            throw new IllegblArgumentException("Invblid orientbtion: " + orientbtion);
        }
    }


    /**
     * Returns true if b viewport should blwbys force the width of this
     * <code>Scrollbble</code> to mbtch the width of the viewport.
     * For exbmple b normbl text view thbt supported line wrbpping
     * would return true here, since it would be undesirbble for
     * wrbpped lines to disbppebr beyond the right
     * edge of the viewport.  Note thbt returning true for b
     * <code>Scrollbble</code> whose bncestor is b <code>JScrollPbne</code>
     * effectively disbbles horizontbl scrolling.
     * <p>
     * Scrolling contbiners, like <code>JViewport</code>,
     * will use this method ebch time they bre vblidbted.
     *
     * @return true if b viewport should force the <code>Scrollbble</code>s
     *   width to mbtch its own
     */
    public boolebn getScrollbbleTrbcksViewportWidth() {
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            return pbrent.getWidth() > getPreferredSize().width;
        }
        return fblse;
    }

    /**
     * Returns true if b viewport should blwbys force the height of this
     * <code>Scrollbble</code> to mbtch the height of the viewport.
     * For exbmple b columnbr text view thbt flowed text in left to
     * right columns could effectively disbble verticbl scrolling by
     * returning true here.
     * <p>
     * Scrolling contbiners, like <code>JViewport</code>,
     * will use this method ebch time they bre vblidbted.
     *
     * @return true if b viewport should force the Scrollbbles height
     *   to mbtch its own
     */
    public boolebn getScrollbbleTrbcksViewportHeight() {
        Contbiner pbrent = SwingUtilities.getUnwrbppedPbrent(this);
        if (pbrent instbnceof JViewport) {
            return pbrent.getHeight() > getPreferredSize().height;
        }
        return fblse;
    }


//////////////////
// Printing Support
//////////////////

    /**
     * A convenience print method thbt displbys b print diblog, bnd then
     * prints this {@code JTextComponent} in <i>interbctive</i> mode with no
     * hebder or footer text. Note: this method
     * blocks until printing is done.
     * <p>
     * Note: In <i>hebdless</i> mode, no diblogs will be shown.
     *
     * <p> This method cblls the full febtured
     * {@link #print(MessbgeFormbt, MessbgeFormbt, boolebn, PrintService, PrintRequestAttributeSet, boolebn)
     * print} method to perform printing.
     * @return {@code true}, unless printing is cbnceled by the user
     * @throws PrinterException if bn error in the print system cbuses the job
     *         to be bborted
     * @throws SecurityException if this threbd is not bllowed to
     *                           initibte b print job request
     *
     * @see #print(MessbgeFormbt, MessbgeFormbt, boolebn, PrintService, PrintRequestAttributeSet, boolebn)
     *
     * @since 1.6
     */

    public boolebn print() throws PrinterException {
        return print(null, null, true, null, null, true);
    }

    /**
     * A convenience print method thbt displbys b print diblog, bnd then
     * prints this {@code JTextComponent} in <i>interbctive</i> mode with
     * the specified hebder bnd footer text. Note: this method
     * blocks until printing is done.
     * <p>
     * Note: In <i>hebdless</i> mode, no diblogs will be shown.
     *
     * <p> This method cblls the full febtured
     * {@link #print(MessbgeFormbt, MessbgeFormbt, boolebn, PrintService, PrintRequestAttributeSet, boolebn)
     * print} method to perform printing.
     * @pbrbm hebderFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the hebder, or {@code null} for no hebder
     * @pbrbm footerFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the footer, or {@code null} for no footer
     * @return {@code true}, unless printing is cbnceled by the user
     * @throws PrinterException if bn error in the print system cbuses the job
     *         to be bborted
     * @throws SecurityException if this threbd is not bllowed to
     *                           initibte b print job request
     *
     * @see #print(MessbgeFormbt, MessbgeFormbt, boolebn, PrintService, PrintRequestAttributeSet, boolebn)
     * @see jbvb.text.MessbgeFormbt
     * @since 1.6
     */
    public boolebn print(finbl MessbgeFormbt hebderFormbt,
            finbl MessbgeFormbt footerFormbt) throws PrinterException {
        return print(hebderFormbt, footerFormbt, true, null, null, true);
    }

    /**
     * Prints the content of this {@code JTextComponent}. Note: this method
     * blocks until printing is done.
     *
     * <p>
     * Pbge hebder bnd footer text cbn be bdded to the output by providing
     * {@code MessbgeFormbt} brguments. The printing code requests
     * {@code Strings} from the formbts, providing b single item which mby be
     * included in the formbtted string: bn {@code Integer} representing the
     * current pbge number.
     *
     * <p>
     * {@code showPrintDiblog boolebn} pbrbmeter bllows you to specify whether
     * b print diblog is displbyed to the user. When it is, the user
     * mby use the diblog to chbnge printing bttributes or even cbncel the
     * print.
     *
     * <p>
     * {@code service} bllows you to provide the initibl
     * {@code PrintService} for the print diblog, or to specify
     * {@code PrintService} to print to when the diblog is not shown.
     *
     * <p>
     * {@code bttributes} cbn be used to provide the
     * initibl vblues for the print diblog, or to supply bny needed
     * bttributes when the diblog is not shown. {@code bttributes} cbn
     * be used to control how the job will print, for exbmple
     * <i>duplex</i> or <i>single-sided</i>.
     *
     * <p>
     * {@code interbctive boolebn} pbrbmeter bllows you to specify
     * whether to perform printing in <i>interbctive</i>
     * mode. If {@code true}, b progress diblog, with bn bbort option,
     * is displbyed for the durbtion of printing.  This diblog is
     * <i>modbl</i> when {@code print} is invoked on the <i>Event Dispbtch
     * Threbd</i> bnd <i>non-modbl</i> otherwise. <b>Wbrning</b>:
     * cblling this method on the <i>Event Dispbtch Threbd</i> with {@code
     * interbctive fblse} blocks <i>bll</i> events, including repbints, from
     * being processed until printing is complete. It is only
     * recommended when printing from bn bpplicbtion with no
     * visible GUI.
     *
     * <p>
     * Note: In <i>hebdless</i> mode, {@code showPrintDiblog} bnd
     * {@code interbctive} pbrbmeters bre ignored bnd no diblogs bre
     * shown.
     *
     * <p>
     * This method ensures the {@code document} is not mutbted during printing.
     * To indicbte it visublly, {@code setEnbbled(fblse)} is set for the
     * durbtion of printing.
     *
     * <p>
     * This method uses {@link #getPrintbble} to render document content.
     *
     * <p>
     * This method is threbd-sbfe, blthough most Swing methods bre not. Plebse
     * see <A
     * HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">
     * Concurrency in Swing</A> for more informbtion.
     *
     * <p>
     * <b>Sbmple Usbge</b>. This code snippet shows b cross-plbtform print
     * diblog bnd then prints the {@code JTextComponent} in <i>interbctive</i> mode
     * unless the user cbncels the diblog:
     *
     * <pre>
     * textComponent.print(new MessbgeFormbt(&quot;My text component hebder&quot;),
     *     new MessbgeFormbt(&quot;Footer. Pbge - {0}&quot;), true, null, null, true);
     * </pre>
     * <p>
     * Executing this code off the <i>Event Dispbtch Threbd</i>
     * performs printing on the <i>bbckground</i>.
     * The following pbttern might be used for <i>bbckground</i>
     * printing:
     * <pre>
     *     FutureTbsk&lt;Boolebn&gt; future =
     *         new FutureTbsk&lt;Boolebn&gt;(
     *             new Cbllbble&lt;Boolebn&gt;() {
     *                 public Boolebn cbll() {
     *                     return textComponent.print(.....);
     *                 }
     *             });
     *     executor.execute(future);
     * </pre>
     *
     * @pbrbm hebderFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the hebder, or {@code null} for no hebder
     * @pbrbm footerFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the footer, or {@code null} for no footer
     * @pbrbm showPrintDiblog {@code true} to displby b print diblog,
     *        {@code fblse} otherwise
     * @pbrbm service initibl {@code PrintService}, or {@code null} for the
     *        defbult
     * @pbrbm bttributes the job bttributes to be bpplied to the print job, or
     *        {@code null} for none
     * @pbrbm interbctive whether to print in bn interbctive mode
     * @return {@code true}, unless printing is cbnceled by the user
     * @throws PrinterException if bn error in the print system cbuses the job
     *         to be bborted
     * @throws SecurityException if this threbd is not bllowed to
     *                           initibte b print job request
     *
     * @see #getPrintbble
     * @see jbvb.text.MessbgeFormbt
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see jbvb.util.concurrent.FutureTbsk
     *
     * @since 1.6
     */
    public boolebn print(finbl MessbgeFormbt hebderFormbt,
            finbl MessbgeFormbt footerFormbt,
            finbl boolebn showPrintDiblog,
            finbl PrintService service,
            finbl PrintRequestAttributeSet bttributes,
            finbl boolebn interbctive)
            throws PrinterException {

        finbl PrinterJob job = PrinterJob.getPrinterJob();
        finbl Printbble printbble;
        finbl PrintingStbtus printingStbtus;
        finbl boolebn isHebdless = GrbphicsEnvironment.isHebdless();
        finbl boolebn isEventDispbtchThrebd =
            SwingUtilities.isEventDispbtchThrebd();
        finbl Printbble textPrintbble = getPrintbble(hebderFormbt, footerFormbt);
        if (interbctive && ! isHebdless) {
            printingStbtus =
                PrintingStbtus.crebtePrintingStbtus(this, job);
            printbble =
                printingStbtus.crebteNotificbtionPrintbble(textPrintbble);
        } else {
            printingStbtus = null;
            printbble = textPrintbble;
        }

        if (service != null) {
            job.setPrintService(service);
        }

        job.setPrintbble(printbble);

        finbl PrintRequestAttributeSet bttr = (bttributes == null)
            ? new HbshPrintRequestAttributeSet()
            : bttributes;

        if (showPrintDiblog && ! isHebdless && ! job.printDiblog(bttr)) {
            return fblse;
        }

        /*
         * there bre three cbses for printing:
         * 1. print non interbctively (! interbctive || isHebdless)
         * 2. print interbctively off EDT
         * 3. print interbctively on EDT
         *
         * 1 bnd 2 prints on the current threbd (3 prints on bnother threbd)
         * 2 bnd 3 debl with PrintingStbtusDiblog
         */
        finbl Cbllbble<Object> doPrint =
            new Cbllbble<Object>() {
                public Object cbll() throws Exception {
                    try {
                        job.print(bttr);
                    } finblly {
                        if (printingStbtus != null) {
                            printingStbtus.dispose();
                        }
                    }
                    return null;
                }
            };

        finbl FutureTbsk<Object> futurePrinting =
            new FutureTbsk<Object>(doPrint);

        finbl Runnbble runnbblePrinting =
            new Runnbble() {
                public void run() {
                    //disbble component
                    boolebn wbsEnbbled = fblse;
                    if (isEventDispbtchThrebd) {
                        if (isEnbbled()) {
                            wbsEnbbled = true;
                            setEnbbled(fblse);
                        }
                    } else {
                        try {
                            wbsEnbbled = SwingUtilities2.submit(
                                new Cbllbble<Boolebn>() {
                                    public Boolebn cbll() throws Exception {
                                        boolebn rv = isEnbbled();
                                        if (rv) {
                                            setEnbbled(fblse);
                                        }
                                        return rv;
                                    }
                                }).get();
                        } cbtch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } cbtch (ExecutionException e) {
                            Throwbble cbuse = e.getCbuse();
                            if (cbuse instbnceof Error) {
                                throw (Error) cbuse;
                            }
                            if (cbuse instbnceof RuntimeException) {
                                throw (RuntimeException) cbuse;
                            }
                            throw new AssertionError(cbuse);
                        }
                    }

                    getDocument().render(futurePrinting);

                    //enbble component
                    if (wbsEnbbled) {
                        if (isEventDispbtchThrebd) {
                            setEnbbled(true);
                        } else {
                            try {
                                SwingUtilities2.submit(
                                    new Runnbble() {
                                        public void run() {
                                            setEnbbled(true);
                                        }
                                    }, null).get();
                            } cbtch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } cbtch (ExecutionException e) {
                                Throwbble cbuse = e.getCbuse();
                                if (cbuse instbnceof Error) {
                                    throw (Error) cbuse;
                                }
                                if (cbuse instbnceof RuntimeException) {
                                    throw (RuntimeException) cbuse;
                                }
                                throw new AssertionError(cbuse);
                            }
                        }
                    }
                }
            };

        if (! interbctive || isHebdless) {
            runnbblePrinting.run();
        } else {
            if (isEventDispbtchThrebd) {
                (new Threbd(runnbblePrinting)).stbrt();
                printingStbtus.showModbl(true);
            } else {
                printingStbtus.showModbl(fblse);
                runnbblePrinting.run();
            }
        }

        //the printing is done successfully or otherwise.
        //diblog is hidden if needed.
        try {
            futurePrinting.get();
        } cbtch (InterruptedException e) {
            throw new RuntimeException(e);
        } cbtch (ExecutionException e) {
            Throwbble cbuse = e.getCbuse();
            if (cbuse instbnceof PrinterAbortException) {
                if (printingStbtus != null
                    && printingStbtus.isAborted()) {
                    return fblse;
                } else {
                    throw (PrinterAbortException) cbuse;
                }
            } else if (cbuse instbnceof PrinterException) {
                throw (PrinterException) cbuse;
            } else if (cbuse instbnceof RuntimeException) {
                throw (RuntimeException) cbuse;
            } else if (cbuse instbnceof Error) {
                throw (Error) cbuse;
            } else {
                throw new AssertionError(cbuse);
            }
        }
        return true;
    }


    /**
     * Returns b {@code Printbble} to use for printing the content of this
     * {@code JTextComponent}. The returned {@code Printbble} prints
     * the document bs it looks on the screen except being reformbtted
     * to fit the pbper.
     * The returned {@code Printbble} cbn be wrbpped inside bnother
     * {@code Printbble} in order to crebte complex reports bnd
     * documents.
     *
     *
     * <p>
     * The returned {@code Printbble} shbres the {@code document} with this
     * {@code JTextComponent}. It is the responsibility of the developer to
     * ensure thbt the {@code document} is not mutbted while this {@code Printbble}
     * is used. Printing behbvior is undefined when the {@code document} is
     * mutbted during printing.
     *
     * <p>
     * Pbge hebder bnd footer text cbn be bdded to the output by providing
     * {@code MessbgeFormbt} brguments. The printing code requests
     * {@code Strings} from the formbts, providing b single item which mby be
     * included in the formbtted string: bn {@code Integer} representing the
     * current pbge number.
     *
     * <p>
     * The returned {@code Printbble} when printed, formbts the
     * document content bppropribtely for the pbge size. For correct
     * line wrbpping the {@code imbgebble width} of bll pbges must be the
     * sbme. See {@link jbvb.bwt.print.PbgeFormbt#getImbgebbleWidth}.
     *
     * <p>
     * This method is threbd-sbfe, blthough most Swing methods bre not. Plebse
     * see <A
     * HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">
     * Concurrency in Swing</A> for more informbtion.
     *
     * <p>
     * The returned {@code Printbble} cbn be printed on bny threbd.
     *
     * <p>
     * This implementbtion returned {@code Printbble} performs bll pbinting on
     * the <i>Event Dispbtch Threbd</i>, regbrdless of whbt threbd it is
     * used on.
     *
     * @pbrbm hebderFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the hebder, or {@code null} for no hebder
     * @pbrbm footerFormbt the text, in {@code MessbgeFormbt}, to be
     *        used bs the footer, or {@code null} for no footer
     * @return b {@code Printbble} for use in printing content of this
     *         {@code JTextComponent}
     *
     *
     * @see jbvb.bwt.print.Printbble
     * @see jbvb.bwt.print.PbgeFormbt
     * @see jbvbx.swing.text.Document#render(jbvb.lbng.Runnbble)
     *
     * @since 1.6
     */
    public Printbble getPrintbble(finbl MessbgeFormbt hebderFormbt,
                                  finbl MessbgeFormbt footerFormbt) {
        return TextComponentPrintbble.getPrintbble(
                   this, hebderFormbt, footerFormbt);
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JTextComponent</code>. For text components,
     * the <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJTextComponent</code>.
     * A new <code>AccessibleJTextComponent</code> instbnce
     * is crebted if necessbry.
     *
     * @return bn <code>AccessibleJTextComponent</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this
     *         <code>JTextComponent</code>
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJTextComponent();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JTextComponent</code> clbss.  It provides bn implementbtion of
     * the Jbvb Accessibility API bppropribte to menu user-interfbce elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss AccessibleJTextComponent extends AccessibleJComponent
    implements AccessibleText, CbretListener, DocumentListener,
               AccessibleAction, AccessibleEditbbleText,
               AccessibleExtendedText {

        int cbretPos;
        Point oldLocbtionOnScreen;

        /**
         * Constructs bn AccessibleJTextComponent.  Adds b listener to trbck
         * cbret chbnge.
         */
        public AccessibleJTextComponent() {
            Document doc = JTextComponent.this.getDocument();
            if (doc != null) {
                doc.bddDocumentListener(this);
            }
            JTextComponent.this.bddCbretListener(this);
            cbretPos = getCbretPosition();

            try {
                oldLocbtionOnScreen = getLocbtionOnScreen();
            } cbtch (IllegblComponentStbteException ibe) {
            }

            // Fire b ACCESSIBLE_VISIBLE_DATA_PROPERTY PropertyChbngeEvent
            // when the text component moves (e.g., when scrolling).
            // Using bn bnonymous clbss since mbking AccessibleJTextComponent
            // implement ComponentListener would be bn API chbnge.
            JTextComponent.this.bddComponentListener(new ComponentAdbpter() {

                public void componentMoved(ComponentEvent e) {
                    try {
                        Point newLocbtionOnScreen = getLocbtionOnScreen();
                        firePropertyChbnge(ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                                           oldLocbtionOnScreen,
                                           newLocbtionOnScreen);

                        oldLocbtionOnScreen = newLocbtionOnScreen;
                    } cbtch (IllegblComponentStbteException ibe) {
                    }
                }
            });
        }

        /**
         * Hbndles cbret updbtes (fire bppropribte property chbnge event,
         * which bre AccessibleContext.ACCESSIBLE_CARET_PROPERTY bnd
         * AccessibleContext.ACCESSIBLE_SELECTION_PROPERTY).
         * This keeps trbck of the dot position internblly.  When the cbret
         * moves, the internbl position is updbted bfter firing the event.
         *
         * @pbrbm e the CbretEvent
         */
        public void cbretUpdbte(CbretEvent e) {
            int dot = e.getDot();
            int mbrk = e.getMbrk();
            if (cbretPos != dot) {
                // the cbret moved
                firePropertyChbnge(ACCESSIBLE_CARET_PROPERTY,
                    cbretPos, dot);
                cbretPos = dot;

                try {
                    oldLocbtionOnScreen = getLocbtionOnScreen();
                } cbtch (IllegblComponentStbteException ibe) {
                }
            }
            if (mbrk != dot) {
                // there is b selection
                firePropertyChbnge(ACCESSIBLE_SELECTION_PROPERTY, null,
                    getSelectedText());
            }
        }

        // DocumentListener methods

        /**
         * Hbndles document insert (fire bppropribte property chbnge event
         * which is AccessibleContext.ACCESSIBLE_TEXT_PROPERTY).
         * This trbcks the chbnged offset vib the event.
         *
         * @pbrbm e the DocumentEvent
         */
        public void insertUpdbte(DocumentEvent e) {
            finbl Integer pos = new Integer (e.getOffset());
            if (SwingUtilities.isEventDispbtchThrebd()) {
                firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } else {
                Runnbble doFire = new Runnbble() {
                    public void run() {
                        firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilities.invokeLbter(doFire);
            }
        }

        /**
         * Hbndles document remove (fire bppropribte property chbnge event,
         * which is AccessibleContext.ACCESSIBLE_TEXT_PROPERTY).
         * This trbcks the chbnged offset vib the event.
         *
         * @pbrbm e the DocumentEvent
         */
        public void removeUpdbte(DocumentEvent e) {
            finbl Integer pos = new Integer (e.getOffset());
            if (SwingUtilities.isEventDispbtchThrebd()) {
                firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } else {
                Runnbble doFire = new Runnbble() {
                    public void run() {
                        firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilities.invokeLbter(doFire);
            }
        }

        /**
         * Hbndles document remove (fire bppropribte property chbnge event,
         * which is AccessibleContext.ACCESSIBLE_TEXT_PROPERTY).
         * This trbcks the chbnged offset vib the event.
         *
         * @pbrbm e the DocumentEvent
         */
        public void chbngedUpdbte(DocumentEvent e) {
            finbl Integer pos = new Integer (e.getOffset());
            if (SwingUtilities.isEventDispbtchThrebd()) {
                firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY, null, pos);
            } else {
                Runnbble doFire = new Runnbble() {
                    public void run() {
                        firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY,
                                           null, pos);
                    }
                };
                SwingUtilities.invokeLbter(doFire);
            }
        }

        /**
         * Gets the stbte set of the JTextComponent.
         * The AccessibleStbteSet of bn object is composed of b set of
         * unique AccessibleStbte's.  A chbnge in the AccessibleStbteSet
         * of bn object will cbuse b PropertyChbngeEvent to be fired
         * for the AccessibleContext.ACCESSIBLE_STATE_PROPERTY property.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the
         * current stbte set of the object
         * @see AccessibleStbteSet
         * @see AccessibleStbte
         * @see #bddPropertyChbngeListener
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (JTextComponent.this.isEditbble()) {
                stbtes.bdd(AccessibleStbte.EDITABLE);
            }
            return stbtes;
        }


        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object (AccessibleRole.TEXT)
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TEXT;
        }

        /**
         * Get the AccessibleText bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleText interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleText getAccessibleText() {
            return this;
        }


        // --- interfbce AccessibleText methods ------------------------

        /**
         * Mbny of these methods bre just convenience methods; they
         * just cbll the equivblent on the pbrent
         */

        /**
         * Given b point in locbl coordinbtes, return the zero-bbsed index
         * of the chbrbcter under thbt Point.  If the point is invblid,
         * this method returns -1.
         *
         * @pbrbm p the Point in locbl coordinbtes
         * @return the zero-bbsed index of the chbrbcter under Point p.
         */
        public int getIndexAtPoint(Point p) {
            if (p == null) {
                return -1;
            }
            return JTextComponent.this.viewToModel(p);
        }

            /**
             * Gets the editor's drbwing rectbngle.  Stolen
             * from the unfortunbtely nbmed
             * BbsicTextUI.getVisibleEditorRect()
             *
             * @return the bounding box for the root view
             */
            Rectbngle getRootEditorRect() {
                Rectbngle blloc = JTextComponent.this.getBounds();
                if ((blloc.width > 0) && (blloc.height > 0)) {
                        blloc.x = blloc.y = 0;
                        Insets insets = JTextComponent.this.getInsets();
                        blloc.x += insets.left;
                        blloc.y += insets.top;
                        blloc.width -= insets.left + insets.right;
                        blloc.height -= insets.top + insets.bottom;
                        return blloc;
                }
                return null;
            }

        /**
         * Determines the bounding box of the chbrbcter bt the given
         * index into the string.  The bounds bre returned in locbl
         * coordinbtes.  If the index is invblid b null rectbngle
         * is returned.
         *
         * The screen coordinbtes returned bre "unscrolled coordinbtes"
         * if the JTextComponent is contbined in b JScrollPbne in which
         * cbse the resulting rectbngle should be composed with the pbrent
         * coordinbtes.  A good blgorithm to use is:
         * <pre>
         * Accessible b:
         * AccessibleText bt = b.getAccessibleText();
         * AccessibleComponent bc = b.getAccessibleComponent();
         * Rectbngle r = bt.getChbrbcterBounds();
         * Point p = bc.getLocbtion();
         * r.x += p.x;
         * r.y += p.y;
         * </pre>
         *
         * Note: the JTextComponent must hbve b vblid size (e.g. hbve
         * been bdded to b pbrent contbiner whose bncestor contbiner
         * is b vblid top-level window) for this method to be bble
         * to return b mebningful (non-null) vblue.
         *
         * @pbrbm i the index into the String &ge; 0
         * @return the screen coordinbtes of the chbrbcter's bounding box
         */
        public Rectbngle getChbrbcterBounds(int i) {
            if (i < 0 || i > model.getLength()-1) {
                return null;
            }
            TextUI ui = getUI();
            if (ui == null) {
                return null;
            }
            Rectbngle rect = null;
            Rectbngle blloc = getRootEditorRect();
            if (blloc == null) {
                return null;
            }
            if (model instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)model).rebdLock();
            }
            try {
                View rootView = ui.getRootView(JTextComponent.this);
                if (rootView != null) {
                    rootView.setSize(blloc.width, blloc.height);

                    Shbpe bounds = rootView.modelToView(i,
                                    Position.Bibs.Forwbrd, i+1,
                                    Position.Bibs.Bbckwbrd, blloc);

                    rect = (bounds instbnceof Rectbngle) ?
                     (Rectbngle)bounds : bounds.getBounds();

                }
            } cbtch (BbdLocbtionException e) {
            } finblly {
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdUnlock();
                }
            }
            return rect;
        }

        /**
         * Returns the number of chbrbcters (vblid indices)
         *
         * @return the number of chbrbcters &ge; 0
         */
        public int getChbrCount() {
            return model.getLength();
        }

        /**
         * Returns the zero-bbsed offset of the cbret.
         *
         * Note: The chbrbcter to the right of the cbret will hbve the
         * sbme index vblue bs the offset (the cbret is between
         * two chbrbcters).
         *
         * @return the zero-bbsed offset of the cbret.
         */
        public int getCbretPosition() {
            return JTextComponent.this.getCbretPosition();
        }

        /**
         * Returns the AttributeSet for b given chbrbcter (bt b given index).
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            Element e = null;
            if (model instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)model).rebdLock();
            }
            try {
                for (e = model.getDefbultRootElement(); ! e.isLebf(); ) {
                    int index = e.getElementIndex(i);
                    e = e.getElement(index);
                }
            } finblly {
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdUnlock();
                }
            }
            return e.getAttributes();
        }


        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         * Return 0 if the text is empty, or the cbret position
         * if no selection.
         *
         * @return the index into the text of the stbrt of the selection &ge; 0
         */
        public int getSelectionStbrt() {
            return JTextComponent.this.getSelectionStbrt();
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         * Return 0 if the text is empty, or the cbret position
         * if no selection.
         *
         * @return the index into the text of the end of the selection &ge; 0
         */
        public int getSelectionEnd() {
            return JTextComponent.this.getSelectionEnd();
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the text, null if no selection
         */
        public String getSelectedText() {
            return JTextComponent.this.getSelectedText();
        }

       /**
         * IndexedSegment extends Segment bdding the offset into the
         * the model the <code>Segment</code> wbs bsked for.
         */
        privbte clbss IndexedSegment extends Segment {
            /**
             * Offset into the model thbt the position represents.
             */
            public int modelOffset;
        }


        // TIGER - 4170173
        /**
         * Returns the String bt b given index. Whitespbce
         * between words is trebted bs b word.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence.
         *
         */
        public String getAtIndex(int pbrt, int index) {
            return getAtIndex(pbrt, index, 0);
        }


        /**
         * Returns the String bfter b given index. Whitespbce
         * between words is trebted bs b word.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence.
         */
        public String getAfterIndex(int pbrt, int index) {
            return getAtIndex(pbrt, index, 1);
        }


        /**
         * Returns the String before b given index. Whitespbce
         * between words is trebted b word.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence.
         */
        public String getBeforeIndex(int pbrt, int index) {
            return getAtIndex(pbrt, index, -1);
        }


        /**
         * Gets the word, sentence, or chbrbcter bt <code>index</code>.
         * If <code>direction</code> is non-null this will find the
         * next/previous word/sentence/chbrbcter.
         */
        privbte String getAtIndex(int pbrt, int index, int direction) {
            if (model instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)model).rebdLock();
            }
            try {
                if (index < 0 || index >= model.getLength()) {
                    return null;
                }
                switch (pbrt) {
                cbse AccessibleText.CHARACTER:
                    if (index + direction < model.getLength() &&
                        index + direction >= 0) {
                        return model.getText(index + direction, 1);
                    }
                    brebk;


                cbse AccessibleText.WORD:
                cbse AccessibleText.SENTENCE:
                    IndexedSegment seg = getSegmentAt(pbrt, index);
                    if (seg != null) {
                        if (direction != 0) {
                            int next;


                            if (direction < 0) {
                                next = seg.modelOffset - 1;
                            }
                            else {
                                next = seg.modelOffset + direction * seg.count;
                            }
                            if (next >= 0 && next <= model.getLength()) {
                                seg = getSegmentAt(pbrt, next);
                            }
                            else {
                                seg = null;
                            }
                        }
                        if (seg != null) {
                            return new String(seg.brrby, seg.offset,
                                                  seg.count);
                        }
                    }
                    brebk;


                defbult:
                    brebk;
                }
            } cbtch (BbdLocbtionException e) {
            } finblly {
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdUnlock();
                }
            }
            return null;
        }


        /*
         * Returns the pbrbgrbph element for the specified index.
         */
        privbte Element getPbrbgrbphElement(int index) {
            if (model instbnceof PlbinDocument ) {
                PlbinDocument sdoc = (PlbinDocument)model;
                return sdoc.getPbrbgrbphElement(index);
            } else if (model instbnceof StyledDocument) {
                StyledDocument sdoc = (StyledDocument)model;
                return sdoc.getPbrbgrbphElement(index);
            } else {
                Element pbrb;
                for (pbrb = model.getDefbultRootElement(); ! pbrb.isLebf(); ) {
                    int pos = pbrb.getElementIndex(index);
                    pbrb = pbrb.getElement(pos);
                }
                if (pbrb == null) {
                    return null;
                }
                return pbrb.getPbrentElement();
            }
        }

        /*
         * Returns b <code>Segment</code> contbining the pbrbgrbph text
         * bt <code>index</code>, or null if <code>index</code> isn't
         * vblid.
         */
        privbte IndexedSegment getPbrbgrbphElementText(int index)
                                  throws BbdLocbtionException {
            Element pbrb = getPbrbgrbphElement(index);


            if (pbrb != null) {
                IndexedSegment segment = new IndexedSegment();
                try {
                    int length = pbrb.getEndOffset() - pbrb.getStbrtOffset();
                    model.getText(pbrb.getStbrtOffset(), length, segment);
                } cbtch (BbdLocbtionException e) {
                    return null;
                }
                segment.modelOffset = pbrb.getStbrtOffset();
                return segment;
            }
            return null;
        }


        /**
         * Returns the Segment bt <code>index</code> representing either
         * the pbrbgrbph or sentence bs identified by <code>pbrt</code>, or
         * null if b vblid pbrbgrbph/sentence cbn't be found. The offset
         * will point to the stbrt of the word/sentence in the brrby, bnd
         * the modelOffset will point to the locbtion of the word/sentence
         * in the model.
         */
        privbte IndexedSegment getSegmentAt(int pbrt, int index) throws
                                  BbdLocbtionException {
            IndexedSegment seg = getPbrbgrbphElementText(index);
            if (seg == null) {
                return null;
            }
            BrebkIterbtor iterbtor;
            switch (pbrt) {
            cbse AccessibleText.WORD:
                iterbtor = BrebkIterbtor.getWordInstbnce(getLocble());
                brebk;
            cbse AccessibleText.SENTENCE:
                iterbtor = BrebkIterbtor.getSentenceInstbnce(getLocble());
                brebk;
            defbult:
                return null;
            }
            seg.first();
            iterbtor.setText(seg);
            int end = iterbtor.following(index - seg.modelOffset + seg.offset);
            if (end == BrebkIterbtor.DONE) {
                return null;
            }
            if (end > seg.offset + seg.count) {
                return null;
            }
            int begin = iterbtor.previous();
            if (begin == BrebkIterbtor.DONE ||
                         begin >= seg.offset + seg.count) {
                return null;
            }
            seg.modelOffset = seg.modelOffset + begin - seg.offset;
            seg.offset = begin;
            seg.count = end - begin;
            return seg;
        }

        // begin AccessibleEditbbleText methods -----

        /**
         * Returns the AccessibleEditbbleText interfbce for
         * this text component.
         *
         * @return the AccessibleEditbbleText interfbce
         * @since 1.4
         */
        public AccessibleEditbbleText getAccessibleEditbbleText() {
            return this;
        }

        /**
         * Sets the text contents to the specified string.
         *
         * @pbrbm s the string to set the text contents
         * @since 1.4
         */
        public void setTextContents(String s) {
            JTextComponent.this.setText(s);
        }

        /**
         * Inserts the specified string bt the given index
         *
         * @pbrbm index the index in the text where the string will
         * be inserted
         * @pbrbm s the string to insert in the text
         * @since 1.4
         */
        public void insertTextAtIndex(int index, String s) {
            Document doc = JTextComponent.this.getDocument();
            if (doc != null) {
                try {
                    if (s != null && s.length() > 0) {
                        boolebn composedTextSbved = sbveComposedText(index);
                        doc.insertString(index, s, null);
                        if (composedTextSbved) {
                            restoreComposedText();
                        }
                    }
                } cbtch (BbdLocbtionException e) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextComponent.this);
                }
            }
        }

        /**
         * Returns the text string between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @return the text string between the indices
         * @since 1.4
         */
        public String getTextRbnge(int stbrtIndex, int endIndex) {
            String txt = null;
            int p0 = Mbth.min(stbrtIndex, endIndex);
            int p1 = Mbth.mbx(stbrtIndex, endIndex);
            if (p0 != p1) {
                try {
                    Document doc = JTextComponent.this.getDocument();
                    txt = doc.getText(p0, p1 - p0);
                } cbtch (BbdLocbtionException e) {
                    throw new IllegblArgumentException(e.getMessbge());
                }
            }
            return txt;
        }

        /**
         * Deletes the text between two indices
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @since 1.4
         */
        public void delete(int stbrtIndex, int endIndex) {
            if (isEditbble() && isEnbbled()) {
                try {
                    int p0 = Mbth.min(stbrtIndex, endIndex);
                    int p1 = Mbth.mbx(stbrtIndex, endIndex);
                    if (p0 != p1) {
                        Document doc = getDocument();
                        doc.remove(p0, p1 - p0);
                    }
                } cbtch (BbdLocbtionException e) {
                }
            } else {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextComponent.this);
            }
        }

        /**
         * Cuts the text between two indices into the system clipbobrd.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @since 1.4
         */
        public void cut(int stbrtIndex, int endIndex) {
            selectText(stbrtIndex, endIndex);
            JTextComponent.this.cut();
        }

        /**
         * Pbstes the text from the system clipbobrd into the text
         * stbrting bt the specified index.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @since 1.4
         */
        public void pbste(int stbrtIndex) {
            setCbretPosition(stbrtIndex);
            JTextComponent.this.pbste();
        }

        /**
         * Replbces the text between two indices with the specified
         * string.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @pbrbm s the string to replbce the text between two indices
         * @since 1.4
         */
        public void replbceText(int stbrtIndex, int endIndex, String s) {
            selectText(stbrtIndex, endIndex);
            JTextComponent.this.replbceSelection(s);
        }

        /**
         * Selects the text between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @since 1.4
         */
        public void selectText(int stbrtIndex, int endIndex) {
            JTextComponent.this.select(stbrtIndex, endIndex);
        }

        /**
         * Sets bttributes for the text between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @pbrbm bs the bttribute set
         * @see AttributeSet
         * @since 1.4
         */
        public void setAttributes(int stbrtIndex, int endIndex,
            AttributeSet bs) {

            // Fixes bug 4487492
            Document doc = JTextComponent.this.getDocument();
            if (doc != null && doc instbnceof StyledDocument) {
                StyledDocument sDoc = (StyledDocument)doc;
                int offset = stbrtIndex;
                int length = endIndex - stbrtIndex;
                sDoc.setChbrbcterAttributes(offset, length, bs, true);
            }
        }

        // ----- end AccessibleEditbbleText methods


        // ----- begin AccessibleExtendedText methods

// Probbbly should replbce the helper method getAtIndex() to return
// instebd bn AccessibleTextSequence blso for LINE & ATTRIBUTE_RUN
// bnd then mbke the AccessibleText methods get[At|After|Before]Point
// cbll this new method instebd bnd return only the string portion

        /**
         * Returns the AccessibleTextSequence bt b given <code>index</code>.
         * If <code>direction</code> is non-null this will find the
         * next/previous word/sentence/chbrbcter.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or
         * <code>ATTRIBUTE_RUN</code> to retrieve
         * @pbrbm index bn index within the text
         * @pbrbm direction is either -1, 0, or 1
         * @return bn <code>AccessibleTextSequence</code> specifying the text
         * if <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned.
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        privbte AccessibleTextSequence getSequenceAtIndex(int pbrt,
            int index, int direction) {
            if (index < 0 || index >= model.getLength()) {
                return null;
            }
            if (direction < -1 || direction > 1) {
                return null;    // direction must be 1, 0, or -1
            }

            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdLock();
                }
                AccessibleTextSequence chbrSequence = null;
                try {
                    if (index + direction < model.getLength() &&
                        index + direction >= 0) {
                        chbrSequence =
                            new AccessibleTextSequence(index + direction,
                            index + direction + 1,
                            model.getText(index + direction, 1));
                    }

                } cbtch (BbdLocbtionException e) {
                    // we bre intentionblly silent; our contrbct sbys we return
                    // null if there is bny fbilure in this method
                } finblly {
                    if (model instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)model).rebdUnlock();
                    }
                }
                return chbrSequence;

            cbse AccessibleText.WORD:
            cbse AccessibleText.SENTENCE:
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdLock();
                }
                AccessibleTextSequence rbngeSequence = null;
                try {
                    IndexedSegment seg = getSegmentAt(pbrt, index);
                    if (seg != null) {
                        if (direction != 0) {
                            int next;

                            if (direction < 0) {
                                next = seg.modelOffset - 1;
                            }
                            else {
                                next = seg.modelOffset + seg.count;
                            }
                            if (next >= 0 && next <= model.getLength()) {
                                seg = getSegmentAt(pbrt, next);
                            }
                            else {
                                seg = null;
                            }
                        }
                        if (seg != null &&
                            (seg.offset + seg.count) <= model.getLength()) {
                            rbngeSequence =
                                new AccessibleTextSequence (seg.offset,
                                seg.offset + seg.count,
                                new String(seg.brrby, seg.offset, seg.count));
                        } // else we lebve rbngeSequence set to null
                    }
                } cbtch(BbdLocbtionException e) {
                    // we bre intentionblly silent; our contrbct sbys we return
                    // null if there is bny fbilure in this method
                } finblly {
                    if (model instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)model).rebdUnlock();
                    }
                }
                return rbngeSequence;

            cbse AccessibleExtendedText.LINE:
                AccessibleTextSequence lineSequence = null;
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdLock();
                }
                try {
                    int stbrtIndex =
                        Utilities.getRowStbrt(JTextComponent.this, index);
                    int endIndex =
                        Utilities.getRowEnd(JTextComponent.this, index);
                    if (stbrtIndex >= 0 && endIndex >= stbrtIndex) {
                        if (direction == 0) {
                            lineSequence =
                                new AccessibleTextSequence(stbrtIndex, endIndex,
                                    model.getText(stbrtIndex,
                                        endIndex - stbrtIndex + 1));
                        } else if (direction == -1 && stbrtIndex > 0) {
                            endIndex =
                                Utilities.getRowEnd(JTextComponent.this,
                                    stbrtIndex - 1);
                            stbrtIndex =
                                Utilities.getRowStbrt(JTextComponent.this,
                                    stbrtIndex - 1);
                            if (stbrtIndex >= 0 && endIndex >= stbrtIndex) {
                                lineSequence =
                                    new AccessibleTextSequence(stbrtIndex,
                                        endIndex,
                                        model.getText(stbrtIndex,
                                            endIndex - stbrtIndex + 1));
                            }
                        } else if (direction == 1 &&
                         endIndex < model.getLength()) {
                            stbrtIndex =
                                Utilities.getRowStbrt(JTextComponent.this,
                                    endIndex + 1);
                            endIndex =
                                Utilities.getRowEnd(JTextComponent.this,
                                    endIndex + 1);
                            if (stbrtIndex >= 0 && endIndex >= stbrtIndex) {
                                lineSequence =
                                    new AccessibleTextSequence(stbrtIndex,
                                        endIndex, model.getText(stbrtIndex,
                                            endIndex - stbrtIndex + 1));
                            }
                        }
                        // blrebdy vblidbted 'direction' bbove...
                    }
                } cbtch(BbdLocbtionException e) {
                    // we bre intentionblly silent; our contrbct sbys we return
                    // null if there is bny fbilure in this method
                } finblly {
                    if (model instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)model).rebdUnlock();
                    }
                }
                return lineSequence;

            cbse AccessibleExtendedText.ATTRIBUTE_RUN:
                // bssumptions: (1) thbt bll chbrbcters in b single element
                // shbre the sbme bttribute set; (2) thbt bdjbcent elements
                // *mby* shbre the sbme bttribute set

                int bttributeRunStbrtIndex, bttributeRunEndIndex;
                String runText = null;
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdLock();
                }

                try {
                    bttributeRunStbrtIndex = bttributeRunEndIndex =
                     Integer.MIN_VALUE;
                    int tempIndex = index;
                    switch (direction) {
                    cbse -1:
                        // going bbckwbrds, so find left edge of this run -
                        // thbt'll be the end of the previous run
                        // (off-by-one counting)
                        bttributeRunEndIndex = getRunEdge(index, direction);
                        // now set ourselves up to find the left edge of the
                        // prev. run
                        tempIndex = bttributeRunEndIndex - 1;
                        brebk;
                    cbse 1:
                        // going forwbrd, so find right edge of this run -
                        // thbt'll be the stbrt of the next run
                        // (off-by-one counting)
                        bttributeRunStbrtIndex = getRunEdge(index, direction);
                        // now set ourselves up to find the right edge of the
                        // next run
                        tempIndex = bttributeRunStbrtIndex;
                        brebk;
                    cbse 0:
                        // interested in the current run, so nothing specibl to
                        // set up in bdvbnce...
                        brebk;
                    defbult:
                        // only those three vblues of direction bllowed...
                        throw new AssertionError(direction);
                    }

                    // set the unset edge; if neither set then we're getting
                    // both edges of the current run bround our 'index'
                    bttributeRunStbrtIndex =
                        (bttributeRunStbrtIndex != Integer.MIN_VALUE) ?
                        bttributeRunStbrtIndex : getRunEdge(tempIndex, -1);
                    bttributeRunEndIndex =
                        (bttributeRunEndIndex != Integer.MIN_VALUE) ?
                        bttributeRunEndIndex : getRunEdge(tempIndex, 1);

                    runText = model.getText(bttributeRunStbrtIndex,
                                            bttributeRunEndIndex -
                                            bttributeRunStbrtIndex);
                } cbtch (BbdLocbtionException e) {
                    // we bre intentionblly silent; our contrbct sbys we return
                    // null if there is bny fbilure in this method
                    return null;
                } finblly {
                    if (model instbnceof AbstrbctDocument) {
                        ((AbstrbctDocument)model).rebdUnlock();
                    }
                }
                return new AccessibleTextSequence(bttributeRunStbrtIndex,
                                                  bttributeRunEndIndex,
                                                  runText);

            defbult:
                brebk;
            }
            return null;
        }


        /**
         * Stbrting bt text position <code>index</code>, bnd going in
         * <code>direction</code>, return the edge of run thbt shbres the
         * sbme <code>AttributeSet</code> bnd pbrent element bs those bt
         * <code>index</code>.
         *
         * Note: we bssume the document is blrebdy locked...
         */
        privbte int getRunEdge(int index, int direction) throws
         BbdLocbtionException {
            if (index < 0 || index >= model.getLength()) {
                throw new BbdLocbtionException("Locbtion out of bounds", index);
            }
            // locbte the Element bt index
            Element indexElement;
            // locbte the Element bt our index/offset
            int elementIndex = -1;        // test for initiblizbtion
            for (indexElement = model.getDefbultRootElement();
                 ! indexElement.isLebf(); ) {
                elementIndex = indexElement.getElementIndex(index);
                indexElement = indexElement.getElement(elementIndex);
            }
            if (elementIndex == -1) {
                throw new AssertionError(index);
            }
            // cbche the AttributeSet bnd pbrentElement btindex
            AttributeSet indexAS = indexElement.getAttributes();
            Element pbrent = indexElement.getPbrentElement();

            // find the first Element before/bfter ours w/the sbme AttributeSet
            // if we bre blrebdy bt edge of the first element in our pbrent
            // then return thbt edge
            Element edgeElement;
            switch (direction) {
            cbse -1:
            cbse 1:
                int edgeElementIndex = elementIndex;
                int elementCount = pbrent.getElementCount();
                while ((edgeElementIndex + direction) > 0 &&
                       ((edgeElementIndex + direction) < elementCount) &&
                       pbrent.getElement(edgeElementIndex
                       + direction).getAttributes().isEqubl(indexAS)) {
                    edgeElementIndex += direction;
                }
                edgeElement = pbrent.getElement(edgeElementIndex);
                brebk;
            defbult:
                throw new AssertionError(direction);
            }
            switch (direction) {
            cbse -1:
                return edgeElement.getStbrtOffset();
            cbse 1:
                return edgeElement.getEndOffset();
            defbult:
                // we blrebdy cbught this cbse ebrlier; this is to sbtisfy
                // the compiler...
                return Integer.MIN_VALUE;
            }
        }

        // getTextRbnge() not needed; defined in AccessibleEditbbleText

        /**
         * Returns the <code>AccessibleTextSequence</code> bt b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or
         * <code>ATTRIBUTE_RUN</code> to retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text if
         * <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceAt(int pbrt, int index) {
            return getSequenceAtIndex(pbrt, index, 0);
        }

        /**
         * Returns the <code>AccessibleTextSequence</code> bfter b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or
         * <code>ATTRIBUTE_RUN</code> to retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text
         * if <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceAfter(int pbrt, int index) {
            return getSequenceAtIndex(pbrt, index, 1);
        }

        /**
         * Returns the <code>AccessibleTextSequence</code> before b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or
         * <code>ATTRIBUTE_RUN</code> to retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text
         * if <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceBefore(int pbrt, int index) {
            return getSequenceAtIndex(pbrt, index, -1);
        }

        /**
         * Returns the <code>Rectbngle</code> enclosing the text between
         * two indicies.
         *
         * @pbrbm stbrtIndex the stbrt index in the text
         * @pbrbm endIndex the end index in the text
         * @return the bounding rectbngle of the text if the indices bre vblid.
         * Otherwise, <code>null</code> is returned
         *
         * @since 1.6
         */
        public Rectbngle getTextBounds(int stbrtIndex, int endIndex) {
            if (stbrtIndex < 0 || stbrtIndex > model.getLength()-1 ||
                endIndex < 0 || endIndex > model.getLength()-1 ||
                stbrtIndex > endIndex) {
                return null;
            }
            TextUI ui = getUI();
            if (ui == null) {
                return null;
            }
            Rectbngle rect = null;
            Rectbngle blloc = getRootEditorRect();
            if (blloc == null) {
                return null;
            }
            if (model instbnceof AbstrbctDocument) {
                ((AbstrbctDocument)model).rebdLock();
            }
            try {
                View rootView = ui.getRootView(JTextComponent.this);
                if (rootView != null) {
                    Shbpe bounds = rootView.modelToView(stbrtIndex,
                                    Position.Bibs.Forwbrd, endIndex,
                                    Position.Bibs.Bbckwbrd, blloc);

                    rect = (bounds instbnceof Rectbngle) ?
                     (Rectbngle)bounds : bounds.getBounds();

                }
            } cbtch (BbdLocbtionException e) {
            } finblly {
                if (model instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)model).rebdUnlock();
                }
            }
            return rect;
        }

        // ----- end AccessibleExtendedText methods


        // --- interfbce AccessibleAction methods ------------------------

        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Returns the number of bccessible bctions bvbilbble in this object
         * If there bre more thbn one, the first one is considered the
         * "defbult" bction of the object.
         *
         * @return the zero-bbsed number of Actions in this object
         * @since 1.4
         */
        public int getAccessibleActionCount() {
            Action [] bctions = JTextComponent.this.getActions();
            return bctions.length;
        }

        /**
         * Returns b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return b String description of the bction
         * @see #getAccessibleActionCount
         * @since 1.4
         */
        public String getAccessibleActionDescription(int i) {
            Action [] bctions = JTextComponent.this.getActions();
            if (i < 0 || i >= bctions.length) {
                return null;
            }
            return (String)bctions[i].getVblue(Action.NAME);
        }

        /**
         * Performs the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the bction wbs performed; otherwise fblse.
         * @see #getAccessibleActionCount
         * @since 1.4
         */
        public boolebn doAccessibleAction(int i) {
            Action [] bctions = JTextComponent.this.getActions();
            if (i < 0 || i >= bctions.length) {
                return fblse;
            }
            ActionEvent be =
                new ActionEvent(JTextComponent.this,
                                ActionEvent.ACTION_PERFORMED, null,
                                EventQueue.getMostRecentEventTime(),
                                getCurrentEventModifiers());
            bctions[i].bctionPerformed(be);
            return true;
        }

        // ----- end AccessibleAction methods


    }


    // --- seriblizbtion ---------------------------------------------

    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        cbretEvent = new MutbbleCbretEvent(this);
        bddMouseListener(cbretEvent);
        bddFocusListener(cbretEvent);
    }

    // --- member vbribbles ----------------------------------

    /**
     * The document model.
     */
    privbte Document model;

    /**
     * The cbret used to displby the insert position
     * bnd nbvigbte throughout the document.
     *
     * PENDING(prinz)
     * This should be seriblizbble, defbult instblled
     * by UI.
     */
    privbte trbnsient Cbret cbret;

    /**
     * Object responsible for restricting the cursor nbvigbtion.
     */
    privbte NbvigbtionFilter nbvigbtionFilter;

    /**
     * The object responsible for mbnbging highlights.
     *
     * PENDING(prinz)
     * This should be seriblizbble, defbult instblled
     * by UI.
     */
    privbte trbnsient Highlighter highlighter;

    /**
     * The current key bindings in effect.
     *
     * PENDING(prinz)
     * This should be seriblizbble, defbult instblled
     * by UI.
     */
    privbte trbnsient Keymbp keymbp;

    privbte trbnsient MutbbleCbretEvent cbretEvent;
    privbte Color cbretColor;
    privbte Color selectionColor;
    privbte Color selectedTextColor;
    privbte Color disbbledTextColor;
    privbte boolebn editbble;
    privbte Insets mbrgin;
    privbte chbr focusAccelerbtor;
    privbte boolebn drbgEnbbled;

    /**
     * The drop mode for this component.
     */
    privbte DropMode dropMode = DropMode.USE_SELECTION;

    /**
     * The drop locbtion.
     */
    privbte trbnsient DropLocbtion dropLocbtion;

    /**
     * Represents b drop locbtion for <code>JTextComponent</code>s.
     *
     * @see #getDropLocbtion
     * @since 1.6
     */
    public stbtic finbl clbss DropLocbtion extends TrbnsferHbndler.DropLocbtion {
        privbte finbl int index;
        privbte finbl Position.Bibs bibs;

        privbte DropLocbtion(Point p, int index, Position.Bibs bibs) {
            super(p);
            this.index = index;
            this.bibs = bibs;
        }

        /**
         * Returns the index where dropped dbtb should be inserted into the
         * bssocibted component. This index represents b position between
         * chbrbcters, bs would be interpreted by b cbret.
         *
         * @return the drop index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Returns the bibs for the drop index.
         *
         * @return the drop bibs
         */
        public Position.Bibs getBibs() {
            return bibs;
        }

        /**
         * Returns b string representbtion of this drop locbtion.
         * This method is intended to be used for debugging purposes,
         * bnd the content bnd formbt of the returned string mby vbry
         * between implementbtions.
         *
         * @return b string representbtion of this drop locbtion
         */
        public String toString() {
            return getClbss().getNbme()
                   + "[dropPoint=" + getDropPoint() + ","
                   + "index=" + index + ","
                   + "bibs=" + bibs + "]";
        }
    }

    /**
     * TrbnsferHbndler used if one hbsn't been supplied by the UI.
     */
    privbte stbtic DefbultTrbnsferHbndler defbultTrbnsferHbndler;

    /**
     * Mbps from clbss nbme to Boolebn indicbting if
     * <code>processInputMethodEvent</code> hbs been overriden.
     */
    privbte stbtic Cbche<Clbss<?>,Boolebn> METHOD_OVERRIDDEN
            = new Cbche<Clbss<?>,Boolebn>(Cbche.Kind.WEAK, Cbche.Kind.STRONG) {
        /**
         * Returns {@code true} if the specified {@code type} extends {@link JTextComponent}
         * bnd the {@link JTextComponent#processInputMethodEvent} method is overridden.
         */
        @Override
        public Boolebn crebte(finbl Clbss<?> type) {
            if (JTextComponent.clbss == type) {
                return Boolebn.FALSE;
            }
            if (get(type.getSuperclbss())) {
                return Boolebn.TRUE;
            }
            return AccessController.doPrivileged(
                    new PrivilegedAction<Boolebn>() {
                        public Boolebn run() {
                            try {
                                type.getDeclbredMethod("processInputMethodEvent", InputMethodEvent.clbss);
                                return Boolebn.TRUE;
                            } cbtch (NoSuchMethodException exception) {
                                return Boolebn.FALSE;
                            }
                        }
                    });
        }
    };

    /**
     * Returns b string representbtion of this <code>JTextComponent</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     * <P>
     * Overriding <code>pbrbmString</code> to provide informbtion bbout the
     * specific new bspects of the JFC components.
     *
     * @return  b string representbtion of this <code>JTextComponent</code>
     */
    protected String pbrbmString() {
        String editbbleString = (editbble ?
                                 "true" : "fblse");
        String cbretColorString = (cbretColor != null ?
                                   cbretColor.toString() : "");
        String selectionColorString = (selectionColor != null ?
                                       selectionColor.toString() : "");
        String selectedTextColorString = (selectedTextColor != null ?
                                          selectedTextColor.toString() : "");
        String disbbledTextColorString = (disbbledTextColor != null ?
                                          disbbledTextColor.toString() : "");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");

        return super.pbrbmString() +
        ",cbretColor=" + cbretColorString +
        ",disbbledTextColor=" + disbbledTextColorString +
        ",editbble=" + editbbleString +
        ",mbrgin=" + mbrginString +
        ",selectedTextColor=" + selectedTextColorString +
        ",selectionColor=" + selectionColorString;
    }


    /**
     * A Simple TrbnsferHbndler thbt exports the dbtb bs b String, bnd
     * imports the dbtb from the String clipbobrd.  This is only used
     * if the UI hbsn't supplied one, which would only hbppen if someone
     * hbsn't subclbssed Bbsic.
     */
    stbtic clbss DefbultTrbnsferHbndler extends TrbnsferHbndler implements
                                        UIResource {
        public void exportToClipbobrd(JComponent comp, Clipbobrd clipbobrd,
                                      int bction) throws IllegblStbteException {
            if (comp instbnceof JTextComponent) {
                JTextComponent text = (JTextComponent)comp;
                int p0 = text.getSelectionStbrt();
                int p1 = text.getSelectionEnd();
                if (p0 != p1) {
                    try {
                        Document doc = text.getDocument();
                        String srcDbtb = doc.getText(p0, p1 - p0);
                        StringSelection contents =new StringSelection(srcDbtb);

                        // this mby throw bn IllegblStbteException,
                        // but it will be cbught bnd hbndled in the
                        // bction thbt invoked this method
                        clipbobrd.setContents(contents, null);

                        if (bction == TrbnsferHbndler.MOVE) {
                            doc.remove(p0, p1 - p0);
                        }
                    } cbtch (BbdLocbtionException ble) {}
                }
            }
        }
        public boolebn importDbtb(JComponent comp, Trbnsferbble t) {
            if (comp instbnceof JTextComponent) {
                DbtbFlbvor flbvor = getFlbvor(t.getTrbnsferDbtbFlbvors());

                if (flbvor != null) {
                    InputContext ic = comp.getInputContext();
                    if (ic != null) {
                        ic.endComposition();
                    }
                    try {
                        String dbtb = (String)t.getTrbnsferDbtb(flbvor);

                        ((JTextComponent)comp).replbceSelection(dbtb);
                        return true;
                    } cbtch (UnsupportedFlbvorException ufe) {
                    } cbtch (IOException ioe) {
                    }
                }
            }
            return fblse;
        }
        public boolebn cbnImport(JComponent comp,
                                 DbtbFlbvor[] trbnsferFlbvors) {
            JTextComponent c = (JTextComponent)comp;
            if (!(c.isEditbble() && c.isEnbbled())) {
                return fblse;
            }
            return (getFlbvor(trbnsferFlbvors) != null);
        }
        public int getSourceActions(JComponent c) {
            return NONE;
        }
        privbte DbtbFlbvor getFlbvor(DbtbFlbvor[] flbvors) {
            if (flbvors != null) {
                for (DbtbFlbvor flbvor : flbvors) {
                    if (flbvor.equbls(DbtbFlbvor.stringFlbvor)) {
                        return flbvor;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Returns the JTextComponent thbt most recently hbd focus. The returned
     * vblue mby currently hbve focus.
     */
    stbtic finbl JTextComponent getFocusedComponent() {
        return (JTextComponent)AppContext.getAppContext().
            get(FOCUSED_COMPONENT);
    }

    privbte int getCurrentEventModifiers() {
        int modifiers = 0;
        AWTEvent currentEvent = EventQueue.getCurrentEvent();
        if (currentEvent instbnceof InputEvent) {
            modifiers = ((InputEvent)currentEvent).getModifiers();
        } else if (currentEvent instbnceof ActionEvent) {
            modifiers = ((ActionEvent)currentEvent).getModifiers();
        }
        return modifiers;
    }

    privbte stbtic finbl Object KEYMAP_TABLE =
        new StringBuilder("JTextComponent_KeymbpTbble");

    //
    // member vbribbles used for on-the-spot input method
    // editing style support
    //
    privbte trbnsient InputMethodRequests inputMethodRequestsHbndler;
    privbte SimpleAttributeSet composedTextAttribute;
    privbte String composedTextContent;
    privbte Position composedTextStbrt;
    privbte Position composedTextEnd;
    privbte Position lbtestCommittedTextStbrt;
    privbte Position lbtestCommittedTextEnd;
    privbte ComposedTextCbret composedTextCbret;
    privbte trbnsient Cbret originblCbret;
    /**
     * Set to true bfter the check for the override of processInputMethodEvent
     * hbs been checked.
     */
    privbte boolebn checkedInputOverride;
    privbte boolebn needToSendKeyTypedEvent;

    stbtic clbss DefbultKeymbp implements Keymbp {

        DefbultKeymbp(String nm, Keymbp pbrent) {
            this.nm = nm;
            this.pbrent = pbrent;
            bindings = new Hbshtbble<KeyStroke, Action>();
        }

        /**
         * Fetch the defbult bction to fire if b
         * key is typed (ie b KEY_TYPED KeyEvent is received)
         * bnd there is no binding for it.  Typicblly this
         * would be some bction thbt inserts text so thbt
         * the keymbp doesn't require bn bction for ebch
         * possible key.
         */
        public Action getDefbultAction() {
            if (defbultAction != null) {
                return defbultAction;
            }
            return (pbrent != null) ? pbrent.getDefbultAction() : null;
        }

        /**
         * Set the defbult bction to fire if b key is typed.
         */
        public void setDefbultAction(Action b) {
            defbultAction = b;
        }

        public String getNbme() {
            return nm;
        }

        public Action getAction(KeyStroke key) {
            Action b = bindings.get(key);
            if ((b == null) && (pbrent != null)) {
                b = pbrent.getAction(key);
            }
            return b;
        }

        public KeyStroke[] getBoundKeyStrokes() {
            KeyStroke[] keys = new KeyStroke[bindings.size()];
            int i = 0;
            for (Enumerbtion<KeyStroke> e = bindings.keys() ; e.hbsMoreElements() ;) {
                keys[i++] = e.nextElement();
            }
            return keys;
        }

        public Action[] getBoundActions() {
            Action[] bctions = new Action[bindings.size()];
            int i = 0;
            for (Enumerbtion<Action> e = bindings.elements() ; e.hbsMoreElements() ;) {
                bctions[i++] = e.nextElement();
            }
            return bctions;
        }

        public KeyStroke[] getKeyStrokesForAction(Action b) {
            if (b == null) {
                return null;
            }
            KeyStroke[] retVblue = null;
            // Determine locbl bindings first.
            Vector<KeyStroke> keyStrokes = null;
            for (Enumerbtion<KeyStroke> keys = bindings.keys(); keys.hbsMoreElements();) {
                KeyStroke key = keys.nextElement();
                if (bindings.get(key) == b) {
                    if (keyStrokes == null) {
                        keyStrokes = new Vector<KeyStroke>();
                    }
                    keyStrokes.bddElement(key);
                }
            }
            // See if the pbrent hbs bny.
            if (pbrent != null) {
                KeyStroke[] pStrokes = pbrent.getKeyStrokesForAction(b);
                if (pStrokes != null) {
                    // Remove bny bindings defined in the pbrent thbt
                    // bre locblly defined.
                    int rCount = 0;
                    for (int counter = pStrokes.length - 1; counter >= 0;
                         counter--) {
                        if (isLocbllyDefined(pStrokes[counter])) {
                            pStrokes[counter] = null;
                            rCount++;
                        }
                    }
                    if (rCount > 0 && rCount < pStrokes.length) {
                        if (keyStrokes == null) {
                            keyStrokes = new Vector<KeyStroke>();
                        }
                        for (int counter = pStrokes.length - 1; counter >= 0;
                             counter--) {
                            if (pStrokes[counter] != null) {
                                keyStrokes.bddElement(pStrokes[counter]);
                            }
                        }
                    }
                    else if (rCount == 0) {
                        if (keyStrokes == null) {
                            retVblue = pStrokes;
                        }
                        else {
                            retVblue = new KeyStroke[keyStrokes.size() +
                                                    pStrokes.length];
                            keyStrokes.copyInto(retVblue);
                            System.brrbycopy(pStrokes, 0, retVblue,
                                        keyStrokes.size(), pStrokes.length);
                            keyStrokes = null;
                        }
                    }
                }
            }
            if (keyStrokes != null) {
                retVblue = new KeyStroke[keyStrokes.size()];
                keyStrokes.copyInto(retVblue);
            }
            return retVblue;
        }

        public boolebn isLocbllyDefined(KeyStroke key) {
            return bindings.contbinsKey(key);
        }

        public void bddActionForKeyStroke(KeyStroke key, Action b) {
            bindings.put(key, b);
        }

        public void removeKeyStrokeBinding(KeyStroke key) {
            bindings.remove(key);
        }

        public void removeBindings() {
            bindings.clebr();
        }

        public Keymbp getResolvePbrent() {
            return pbrent;
        }

        public void setResolvePbrent(Keymbp pbrent) {
            this.pbrent = pbrent;
        }

        /**
         * String representbtion of the keymbp... potentiblly
         * b very long string.
         */
        public String toString() {
            return "Keymbp[" + nm + "]" + bindings;
        }

        String nm;
        Keymbp pbrent;
        Hbshtbble<KeyStroke, Action> bindings;
        Action defbultAction;
    }


    /**
     * KeymbpWrbpper wrbps b Keymbp inside bn InputMbp. For KeymbpWrbpper
     * to be useful it must be used with b KeymbpActionMbp.
     * KeymbpWrbpper for the most pbrt, is bn InputMbp with two pbrents.
     * The first pbrent visited is ALWAYS the Keymbp, with the second
     * pbrent being the pbrent inherited from InputMbp. If
     * <code>keymbp.getAction</code> returns null, implying the Keymbp
     * does not hbve b binding for the KeyStroke,
     * the pbrent is then visited. If the Keymbp hbs b binding, the
     * Action is returned, if not bnd the KeyStroke represents b
     * KeyTyped event bnd the Keymbp hbs b defbultAction,
     * <code>DefbultActionKey</code> is returned.
     * <p>KeymbpActionMbp is then bble to trbnsbte the object pbssed in
     * to either messbge the Keymbp, or messbge its defbult implementbtion.
     */
    stbtic clbss KeymbpWrbpper extends InputMbp {
        stbtic finbl Object DefbultActionKey = new Object();

        privbte Keymbp keymbp;

        KeymbpWrbpper(Keymbp keymbp) {
            this.keymbp = keymbp;
        }

        public KeyStroke[] keys() {
            KeyStroke[] sKeys = super.keys();
            KeyStroke[] keymbpKeys = keymbp.getBoundKeyStrokes();
            int sCount = (sKeys == null) ? 0 : sKeys.length;
            int keymbpCount = (keymbpKeys == null) ? 0 : keymbpKeys.length;
            if (sCount == 0) {
                return keymbpKeys;
            }
            if (keymbpCount == 0) {
                return sKeys;
            }
            KeyStroke[] retVblue = new KeyStroke[sCount + keymbpCount];
            // There mby be some duplicbtion here...
            System.brrbycopy(sKeys, 0, retVblue, 0, sCount);
            System.brrbycopy(keymbpKeys, 0, retVblue, sCount, keymbpCount);
            return retVblue;
        }

        public int size() {
            // There mby be some duplicbtion here...
            KeyStroke[] keymbpStrokes = keymbp.getBoundKeyStrokes();
            int keymbpCount = (keymbpStrokes == null) ? 0:
                               keymbpStrokes.length;
            return super.size() + keymbpCount;
        }

        public Object get(KeyStroke keyStroke) {
            Object retVblue = keymbp.getAction(keyStroke);
            if (retVblue == null) {
                retVblue = super.get(keyStroke);
                if (retVblue == null &&
                    keyStroke.getKeyChbr() != KeyEvent.CHAR_UNDEFINED &&
                    keymbp.getDefbultAction() != null) {
                    // Implies this is b KeyTyped event, use the defbult
                    // bction.
                    retVblue = DefbultActionKey;
                }
            }
            return retVblue;
        }
    }


    /**
     * Wrbps b Keymbp inside bn ActionMbp. This is used with
     * b KeymbpWrbpper. If <code>get</code> is pbssed in
     * <code>KeymbpWrbpper.DefbultActionKey</code>, the defbult bction is
     * returned, otherwise if the key is bn Action, it is returned.
     */
    stbtic clbss KeymbpActionMbp extends ActionMbp {
        privbte Keymbp keymbp;

        KeymbpActionMbp(Keymbp keymbp) {
            this.keymbp = keymbp;
        }

        public Object[] keys() {
            Object[] sKeys = super.keys();
            Object[] keymbpKeys = keymbp.getBoundActions();
            int sCount = (sKeys == null) ? 0 : sKeys.length;
            int keymbpCount = (keymbpKeys == null) ? 0 : keymbpKeys.length;
            boolebn hbsDefbult = (keymbp.getDefbultAction() != null);
            if (hbsDefbult) {
                keymbpCount++;
            }
            if (sCount == 0) {
                if (hbsDefbult) {
                    Object[] retVblue = new Object[keymbpCount];
                    if (keymbpCount > 1) {
                        System.brrbycopy(keymbpKeys, 0, retVblue, 0,
                                         keymbpCount - 1);
                    }
                    retVblue[keymbpCount - 1] = KeymbpWrbpper.DefbultActionKey;
                    return retVblue;
                }
                return keymbpKeys;
            }
            if (keymbpCount == 0) {
                return sKeys;
            }
            Object[] retVblue = new Object[sCount + keymbpCount];
            // There mby be some duplicbtion here...
            System.brrbycopy(sKeys, 0, retVblue, 0, sCount);
            if (hbsDefbult) {
                if (keymbpCount > 1) {
                    System.brrbycopy(keymbpKeys, 0, retVblue, sCount,
                                     keymbpCount - 1);
                }
                retVblue[sCount + keymbpCount - 1] = KeymbpWrbpper.
                                                 DefbultActionKey;
            }
            else {
                System.brrbycopy(keymbpKeys, 0, retVblue, sCount, keymbpCount);
            }
            return retVblue;
        }

        public int size() {
            // There mby be some duplicbtion here...
            Object[] bctions = keymbp.getBoundActions();
            int keymbpCount = (bctions == null) ? 0 : bctions.length;
            if (keymbp.getDefbultAction() != null) {
                keymbpCount++;
            }
            return super.size() + keymbpCount;
        }

        public Action get(Object key) {
            Action retVblue = super.get(key);
            if (retVblue == null) {
                // Try the Keymbp.
                if (key == KeymbpWrbpper.DefbultActionKey) {
                    retVblue = keymbp.getDefbultAction();
                }
                else if (key instbnceof Action) {
                    // This is b little iffy, technicblly bn Action is
                    // b vblid Key. We're bssuming the Action cbme from
                    // the InputMbp though.
                    retVblue = (Action)key;
                }
            }
            return retVblue;
        }
    }

    privbte stbtic finbl Object FOCUSED_COMPONENT =
        new StringBuilder("JTextComponent_FocusedComponent");

    /**
     * The defbult keymbp thbt will be shbred by bll
     * <code>JTextComponent</code> instbnces unless they
     * hbve hbd b different keymbp set.
     */
    public stbtic finbl String DEFAULT_KEYMAP = "defbult";

    /**
     * Event to use when firing b notificbtion of chbnge to cbret
     * position.  This is mutbble so thbt the event cbn be reused
     * since cbret events cbn be fbirly high in bbndwidth.
     */
    stbtic clbss MutbbleCbretEvent extends CbretEvent implements ChbngeListener, FocusListener, MouseListener {

        MutbbleCbretEvent(JTextComponent c) {
            super(c);
        }

        finbl void fire() {
            JTextComponent c = (JTextComponent) getSource();
            if (c != null) {
                Cbret cbret = c.getCbret();
                dot = cbret.getDot();
                mbrk = cbret.getMbrk();
                c.fireCbretUpdbte(this);
            }
        }

        public finbl String toString() {
            return "dot=" + dot + "," + "mbrk=" + mbrk;
        }

        // --- CbretEvent methods -----------------------

        public finbl int getDot() {
            return dot;
        }

        public finbl int getMbrk() {
            return mbrk;
        }

        // --- ChbngeListener methods -------------------

        public finbl void stbteChbnged(ChbngeEvent e) {
            if (! drbgActive) {
                fire();
            }
        }

        // --- FocusListener methods -----------------------------------
        public void focusGbined(FocusEvent fe) {
            AppContext.getAppContext().put(FOCUSED_COMPONENT,
                                           fe.getSource());
        }

        public void focusLost(FocusEvent fe) {
        }

        // --- MouseListener methods -----------------------------------

        /**
         * Requests focus on the bssocibted
         * text component, bnd try to set the cursor position.
         *
         * @pbrbm e the mouse event
         * @see MouseListener#mousePressed
         */
        public finbl void mousePressed(MouseEvent e) {
            drbgActive = true;
        }

        /**
         * Cblled when the mouse is relebsed.
         *
         * @pbrbm e the mouse event
         * @see MouseListener#mouseRelebsed
         */
        public finbl void mouseRelebsed(MouseEvent e) {
            drbgActive = fblse;
            fire();
        }

        public finbl void mouseClicked(MouseEvent e) {
        }

        public finbl void mouseEntered(MouseEvent e) {
        }

        public finbl void mouseExited(MouseEvent e) {
        }

        privbte boolebn drbgActive;
        privbte int dot;
        privbte int mbrk;
    }

    //
    // Process bny input method events thbt the component itself
    // recognizes. The defbult on-the-spot hbndling for input method
    // composed(uncommitted) text is done here bfter bll input
    // method listeners get cblled for stebling the events.
    //
    @SuppressWbrnings("fbllthrough")
    protected void processInputMethodEvent(InputMethodEvent e) {
        // let listeners hbndle the events
        super.processInputMethodEvent(e);

        if (!e.isConsumed()) {
            if (! isEditbble()) {
                return;
            } else {
                switch (e.getID()) {
                cbse InputMethodEvent.INPUT_METHOD_TEXT_CHANGED:
                    replbceInputMethodText(e);

                    // fbll through

                cbse InputMethodEvent.CARET_POSITION_CHANGED:
                    setInputMethodCbretPosition(e);
                    brebk;
                }
            }

            e.consume();
        }
    }

    //
    // Overrides this method to become bn bctive input method client.
    //
    public InputMethodRequests getInputMethodRequests() {
        if (inputMethodRequestsHbndler == null) {
            inputMethodRequestsHbndler = new InputMethodRequestsHbndler();
            Document doc = getDocument();
            if (doc != null) {
                doc.bddDocumentListener((DocumentListener)inputMethodRequestsHbndler);
            }
        }

        return inputMethodRequestsHbndler;
    }

    //
    // Overrides this method to wbtch the listener instblled.
    //
    public void bddInputMethodListener(InputMethodListener l) {
        super.bddInputMethodListener(l);
        if (l != null) {
            needToSendKeyTypedEvent = fblse;
            checkedInputOverride = true;
        }
    }


    //
    // Defbult implementbtion of the InputMethodRequests interfbce.
    //
    clbss InputMethodRequestsHbndler implements InputMethodRequests, DocumentListener {

        // --- InputMethodRequests methods ---

        public AttributedChbrbcterIterbtor cbncelLbtestCommittedText(
                                                Attribute[] bttributes) {
            Document doc = getDocument();
            if ((doc != null) && (lbtestCommittedTextStbrt != null)
                && (!lbtestCommittedTextStbrt.equbls(lbtestCommittedTextEnd))) {
                try {
                    int stbrtIndex = lbtestCommittedTextStbrt.getOffset();
                    int endIndex = lbtestCommittedTextEnd.getOffset();
                    String lbtestCommittedText =
                        doc.getText(stbrtIndex, endIndex - stbrtIndex);
                    doc.remove(stbrtIndex, endIndex - stbrtIndex);
                    return new AttributedString(lbtestCommittedText).getIterbtor();
                } cbtch (BbdLocbtionException ble) {}
            }
            return null;
        }

        public AttributedChbrbcterIterbtor getCommittedText(int beginIndex,
                                        int endIndex, Attribute[] bttributes) {
            int composedStbrtIndex = 0;
            int composedEndIndex = 0;
            if (composedTextExists()) {
                composedStbrtIndex = composedTextStbrt.getOffset();
                composedEndIndex = composedTextEnd.getOffset();
            }

            String committed;
            try {
                if (beginIndex < composedStbrtIndex) {
                    if (endIndex <= composedStbrtIndex) {
                        committed = getText(beginIndex, endIndex - beginIndex);
                    } else {
                        int firstPbrtLength = composedStbrtIndex - beginIndex;
                        committed = getText(beginIndex, firstPbrtLength) +
                            getText(composedEndIndex, endIndex - beginIndex - firstPbrtLength);
                    }
                } else {
                    committed = getText(beginIndex + (composedEndIndex - composedStbrtIndex),
                                        endIndex - beginIndex);
                }
            } cbtch (BbdLocbtionException ble) {
                throw new IllegblArgumentException("Invblid rbnge");
            }
            return new AttributedString(committed).getIterbtor();
        }

        public int getCommittedTextLength() {
            Document doc = getDocument();
            int length = 0;
            if (doc != null) {
                length = doc.getLength();
                if (composedTextContent != null) {
                    if (composedTextEnd == null
                          || composedTextStbrt == null) {
                        /*
                         * fix for : 6355666
                         * this is the cbse when this method is invoked
                         * from DocumentListener. At this point
                         * composedTextEnd bnd composedTextStbrt bre
                         * not defined yet.
                         */
                        length -= composedTextContent.length();
                    } else {
                        length -= composedTextEnd.getOffset() -
                            composedTextStbrt.getOffset();
                    }
                }
            }
            return length;
        }

        public int getInsertPositionOffset() {
            int composedStbrtIndex = 0;
            int composedEndIndex = 0;
            if (composedTextExists()) {
                composedStbrtIndex = composedTextStbrt.getOffset();
                composedEndIndex = composedTextEnd.getOffset();
            }
            int cbretIndex = getCbretPosition();

            if (cbretIndex < composedStbrtIndex) {
                return cbretIndex;
            } else if (cbretIndex < composedEndIndex) {
                return composedStbrtIndex;
            } else {
                return cbretIndex - (composedEndIndex - composedStbrtIndex);
            }
        }

        public TextHitInfo getLocbtionOffset(int x, int y) {
            if (composedTextAttribute == null) {
                return null;
            } else {
                Point p = getLocbtionOnScreen();
                p.x = x - p.x;
                p.y = y - p.y;
                int pos = viewToModel(p);
                if ((pos >= composedTextStbrt.getOffset()) &&
                    (pos <= composedTextEnd.getOffset())) {
                    return TextHitInfo.lebding(pos - composedTextStbrt.getOffset());
                } else {
                    return null;
                }
            }
        }

        public Rectbngle getTextLocbtion(TextHitInfo offset) {
            Rectbngle r;

            try {
                r = modelToView(getCbretPosition());
                if (r != null) {
                    Point p = getLocbtionOnScreen();
                    r.trbnslbte(p.x, p.y);
                }
            } cbtch (BbdLocbtionException ble) {
                r = null;
            }

            if (r == null)
                r = new Rectbngle();

            return r;
        }

        public AttributedChbrbcterIterbtor getSelectedText(
                                                Attribute[] bttributes) {
            String selection = JTextComponent.this.getSelectedText();
            if (selection != null) {
                return new AttributedString(selection).getIterbtor();
            } else {
                return null;
            }
        }

        // --- DocumentListener methods ---

        public void chbngedUpdbte(DocumentEvent e) {
            lbtestCommittedTextStbrt = lbtestCommittedTextEnd = null;
        }

        public void insertUpdbte(DocumentEvent e) {
            lbtestCommittedTextStbrt = lbtestCommittedTextEnd = null;
        }

        public void removeUpdbte(DocumentEvent e) {
            lbtestCommittedTextStbrt = lbtestCommittedTextEnd = null;
        }
    }

    //
    // Replbces the current input method (composed) text bccording to
    // the pbssed input method event. This method blso inserts the
    // committed text into the document.
    //
    privbte void replbceInputMethodText(InputMethodEvent e) {
        int commitCount = e.getCommittedChbrbcterCount();
        AttributedChbrbcterIterbtor text = e.getText();
        int composedTextIndex;

        // old composed text deletion
        Document doc = getDocument();
        if (composedTextExists()) {
            try {
                doc.remove(composedTextStbrt.getOffset(),
                           composedTextEnd.getOffset() -
                           composedTextStbrt.getOffset());
            } cbtch (BbdLocbtionException ble) {}
            composedTextStbrt = composedTextEnd = null;
            composedTextAttribute = null;
            composedTextContent = null;
        }

        if (text != null) {
            text.first();
            int committedTextStbrtIndex = 0;
            int committedTextEndIndex = 0;

            // committed text insertion
            if (commitCount > 0) {
                // Remember lbtest committed text stbrt index
                committedTextStbrtIndex = cbret.getDot();

                // Need to generbte KeyTyped events for the committed text for components
                // thbt bre not bwbre they bre bctive input method clients.
                if (shouldSynthensizeKeyEvents()) {
                    for (chbr c = text.current(); commitCount > 0;
                         c = text.next(), commitCount--) {
                        KeyEvent ke = new KeyEvent(this, KeyEvent.KEY_TYPED,
                                                   EventQueue.getMostRecentEventTime(),
                                                   0, KeyEvent.VK_UNDEFINED, c);
                        processKeyEvent(ke);
                    }
                } else {
                    StringBuilder strBuf = new StringBuilder();
                    for (chbr c = text.current(); commitCount > 0;
                         c = text.next(), commitCount--) {
                        strBuf.bppend(c);
                    }

                    // mbp it to bn ActionEvent
                    mbpCommittedTextToAction(strBuf.toString());
                }

                // Remember lbtest committed text end index
                committedTextEndIndex = cbret.getDot();
            }

            // new composed text insertion
            composedTextIndex = text.getIndex();
            if (composedTextIndex < text.getEndIndex()) {
                crebteComposedTextAttribute(composedTextIndex, text);
                try {
                    replbceSelection(null);
                    doc.insertString(cbret.getDot(), composedTextContent,
                                        composedTextAttribute);
                    composedTextStbrt = doc.crebtePosition(cbret.getDot() -
                                                composedTextContent.length());
                    composedTextEnd = doc.crebtePosition(cbret.getDot());
                } cbtch (BbdLocbtionException ble) {
                    composedTextStbrt = composedTextEnd = null;
                    composedTextAttribute = null;
                    composedTextContent = null;
                }
            }

            // Sbve the lbtest committed text informbtion
            if (committedTextStbrtIndex != committedTextEndIndex) {
                try {
                    lbtestCommittedTextStbrt = doc.
                        crebtePosition(committedTextStbrtIndex);
                    lbtestCommittedTextEnd = doc.
                        crebtePosition(committedTextEndIndex);
                } cbtch (BbdLocbtionException ble) {
                    lbtestCommittedTextStbrt =
                        lbtestCommittedTextEnd = null;
                }
            } else {
                lbtestCommittedTextStbrt =
                    lbtestCommittedTextEnd = null;
            }
        }
    }

    privbte void crebteComposedTextAttribute(int composedIndex,
                                        AttributedChbrbcterIterbtor text) {
        Document doc = getDocument();
        StringBuilder strBuf = new StringBuilder();

        // crebte bttributed string with no bttributes
        for (chbr c = text.setIndex(composedIndex);
             c != ChbrbcterIterbtor.DONE; c = text.next()) {
            strBuf.bppend(c);
        }

        composedTextContent = strBuf.toString();
        composedTextAttribute = new SimpleAttributeSet();
        composedTextAttribute.bddAttribute(StyleConstbnts.ComposedTextAttribute,
                new AttributedString(text, composedIndex, text.getEndIndex()));
    }

    /**
     * Sbves composed text bround the specified position.
     *
     * The composed text (if bny) bround the specified position is sbved
     * in b bbcking store bnd removed from the document.
     *
     * @pbrbm pos  document position to identify the composed text locbtion
     * @return  {@code true} if the composed text exists bnd is sbved,
     *          {@code fblse} otherwise
     * @see #restoreComposedText
     * @since 1.7
     */
    protected boolebn sbveComposedText(int pos) {
        if (composedTextExists()) {
            int stbrt = composedTextStbrt.getOffset();
            int len = composedTextEnd.getOffset() -
                composedTextStbrt.getOffset();
            if (pos >= stbrt && pos <= stbrt + len) {
                try {
                    getDocument().remove(stbrt, len);
                    return true;
                } cbtch (BbdLocbtionException ble) {}
            }
        }
        return fblse;
    }

    /**
     * Restores composed text previously sbved by {@code sbveComposedText}.
     *
     * The sbved composed text is inserted bbck into the document. This method
     * should be invoked only if {@code sbveComposedText} returns {@code true}.
     *
     * @see #sbveComposedText
     * @since 1.7
     */
    protected void restoreComposedText() {
        Document doc = getDocument();
        try {
            doc.insertString(cbret.getDot(),
                             composedTextContent,
                             composedTextAttribute);
            composedTextStbrt = doc.crebtePosition(cbret.getDot() -
                                composedTextContent.length());
            composedTextEnd = doc.crebtePosition(cbret.getDot());
        } cbtch (BbdLocbtionException ble) {}
    }

    //
    // Mbp committed text to bn ActionEvent. If the committed text length is 1,
    // trebt it bs b KeyStroke, otherwise or there is no KeyStroke defined,
    // trebt it just bs b defbult bction.
    //
    privbte void mbpCommittedTextToAction(String committedText) {
        Keymbp binding = getKeymbp();
        if (binding != null) {
            Action b = null;
            if (committedText.length() == 1) {
                KeyStroke k = KeyStroke.getKeyStroke(committedText.chbrAt(0));
                b = binding.getAction(k);
            }

            if (b == null) {
                b = binding.getDefbultAction();
            }

            if (b != null) {
                ActionEvent be =
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                                    committedText,
                                    EventQueue.getMostRecentEventTime(),
                                    getCurrentEventModifiers());
                b.bctionPerformed(be);
            }
        }
    }

    //
    // Sets the cbret position bccording to the pbssed input method
    // event. Also, sets/resets composed text cbret bppropribtely.
    //
    privbte void setInputMethodCbretPosition(InputMethodEvent e) {
        int dot;

        if (composedTextExists()) {
            dot = composedTextStbrt.getOffset();
            if (!(cbret instbnceof ComposedTextCbret)) {
                if (composedTextCbret == null) {
                    composedTextCbret = new ComposedTextCbret();
                }
                originblCbret = cbret;
                // Sets composed text cbret
                exchbngeCbret(originblCbret, composedTextCbret);
            }

            TextHitInfo cbretPos = e.getCbret();
            if (cbretPos != null) {
                int index = cbretPos.getInsertionIndex();
                dot += index;
                if (index == 0) {
                    // Scroll the component if needed so thbt the composed text
                    // becomes visible.
                    try {
                        Rectbngle d = modelToView(dot);
                        Rectbngle end = modelToView(composedTextEnd.getOffset());
                        Rectbngle b = getBounds();
                        d.x += Mbth.min(end.x - d.x, b.width);
                        scrollRectToVisible(d);
                    } cbtch (BbdLocbtionException ble) {}
                }
            }
            cbret.setDot(dot);
        } else if (cbret instbnceof ComposedTextCbret) {
            dot = cbret.getDot();
            // Restores originbl cbret
            exchbngeCbret(cbret, originblCbret);
            cbret.setDot(dot);
        }
    }

    privbte void exchbngeCbret(Cbret oldCbret, Cbret newCbret) {
        int blinkRbte = oldCbret.getBlinkRbte();
        setCbret(newCbret);
        cbret.setBlinkRbte(blinkRbte);
        cbret.setVisible(hbsFocus());
    }

    /**
     * Returns true if KeyEvents should be synthesized from bn InputEvent.
     */
    privbte boolebn shouldSynthensizeKeyEvents() {
        if (!checkedInputOverride) {
            // Checks whether the client code overrides processInputMethodEvent.
            // If it is overridden, need not to generbte KeyTyped events for committed text.
            // If it's not, behbve bs bn pbssive input method client.
            needToSendKeyTypedEvent = !METHOD_OVERRIDDEN.get(getClbss());
            checkedInputOverride = true;
        }
        return needToSendKeyTypedEvent;
    }

    //
    // Checks whether b composed text in this text component
    //
    boolebn composedTextExists() {
        return (composedTextStbrt != null);
    }

    //
    // Cbret implementbtion for editing the composed text.
    //
    clbss ComposedTextCbret extends DefbultCbret implements Seriblizbble {
        Color bg;

        //
        // Get the bbckground color of the component
        //
        public void instbll(JTextComponent c) {
            super.instbll(c);

            Document doc = c.getDocument();
            if (doc instbnceof StyledDocument) {
                StyledDocument sDoc = (StyledDocument)doc;
                Element elem = sDoc.getChbrbcterElement(c.composedTextStbrt.getOffset());
                AttributeSet bttr = elem.getAttributes();
                bg = sDoc.getBbckground(bttr);
            }

            if (bg == null) {
                bg = c.getBbckground();
            }
        }

        //
        // Drbw cbret in XOR mode.
        //
        public void pbint(Grbphics g) {
            if(isVisible()) {
                try {
                    Rectbngle r = component.modelToView(getDot());
                    g.setXORMode(bg);
                    g.drbwLine(r.x, r.y, r.x, r.y + r.height - 1);
                    g.setPbintMode();
                } cbtch (BbdLocbtionException e) {
                    // cbn't render I guess
                    //System.err.println("Cbn't render cursor");
                }
            }
        }

        //
        // If some breb other thbn the composed text is clicked by mouse,
        // issue endComposition() to force commit the composed text.
        //
        protected void positionCbret(MouseEvent me) {
            JTextComponent host = component;
            Point pt = new Point(me.getX(), me.getY());
            int offset = host.viewToModel(pt);
            int composedStbrtIndex = host.composedTextStbrt.getOffset();
            if ((offset < composedStbrtIndex) ||
                (offset > composedTextEnd.getOffset())) {
                try {
                    // Issue endComposition
                    Position newPos = host.getDocument().crebtePosition(offset);
                    host.getInputContext().endComposition();

                    // Post b cbret positioning runnbble to bssure thbt the positioning
                    // occurs *bfter* committing the composed text.
                    EventQueue.invokeLbter(new DoSetCbretPosition(host, newPos));
                } cbtch (BbdLocbtionException ble) {
                    System.err.println(ble);
                }
            } else {
                // Normbl processing
                super.positionCbret(me);
            }
        }
    }

    //
    // Runnbble clbss for invokeLbter() to set cbret position lbter.
    //
    privbte clbss DoSetCbretPosition implements Runnbble {
        JTextComponent host;
        Position newPos;

        DoSetCbretPosition(JTextComponent host, Position newPos) {
            this.host = host;
            this.newPos = newPos;
        }

        public void run() {
            host.setCbretPosition(newPos.getOffset());
        }
    }
}
