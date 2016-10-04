/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.im.InputContext;
import jbvb.io.*;
import jbvb.text.*;
import jbvb.util.*;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.*;

/**
 * <code>JFormbttedTextField</code> extends <code>JTextField</code> bdding
 * support for formbtting brbitrbry vblues, bs well bs retrieving b pbrticulbr
 * object once the user hbs edited the text. The following illustrbtes
 * configuring b <code>JFormbttedTextField</code> to edit dbtes:
 * <pre>
 *   JFormbttedTextField ftf = new JFormbttedTextField();
 *   ftf.setVblue(new Dbte());
 * </pre>
 * <p>
 * Once b <code>JFormbttedTextField</code> hbs been crebted, you cbn
 * listen for editing chbnges by wby of bdding
 * b <code>PropertyChbngeListener</code> bnd listening for
 * <code>PropertyChbngeEvent</code>s with the property nbme <code>vblue</code>.
 * <p>
 * <code>JFormbttedTextField</code> bllows
 * configuring whbt bction should be tbken when focus is lost. The possible
 * configurbtions bre:
 * <tbble summbry="Possible JFormbttedTextField configurbtions bnd their descriptions">
 * <tr><th><p style="text-blign:left">Vblue</p></th><th><p style="text-blign:left">Description</p></th></tr>
 * <tr><td>JFormbttedTextField.REVERT
 *            <td>Revert the displby to mbtch thbt of <code>getVblue</code>,
 *                possibly losing the current edit.
 *        <tr><td>JFormbttedTextField.COMMIT
 *            <td>Commits the current vblue. If the vblue being edited
 *                isn't considered b legbl vblue by the
 *                <code>AbstrbctFormbtter</code> thbt is, b
 *                <code>PbrseException</code> is thrown, then the vblue
 *                will not chbnge, bnd then edited vblue will persist.
 *        <tr><td>JFormbttedTextField.COMMIT_OR_REVERT
 *            <td>Similbr to <code>COMMIT</code>, but if the vblue isn't
 *                legbl, behbve like <code>REVERT</code>.
 *        <tr><td>JFormbttedTextField.PERSIST
 *            <td>Do nothing, don't obtbin b new
 *                <code>AbstrbctFormbtter</code>, bnd don't updbte the vblue.
 * </tbble>
 * The defbult is <code>JFormbttedTextField.COMMIT_OR_REVERT</code>,
 * refer to {@link #setFocusLostBehbvior} for more informbtion on this.
 * <p>
 * <code>JFormbttedTextField</code> bllows the focus to lebve, even if
 * the currently edited vblue is invblid. To lock the focus down while the
 * <code>JFormbttedTextField</code> is bn invblid edit stbte
 * you cbn bttbch bn <code>InputVerifier</code>. The following code snippet
 * shows b potentibl implementbtion of such bn <code>InputVerifier</code>:
 * <pre>
 * public clbss FormbttedTextFieldVerifier extends InputVerifier {
 *     public boolebn verify(JComponent input) {
 *         if (input instbnceof JFormbttedTextField) {
 *             JFormbttedTextField ftf = (JFormbttedTextField)input;
 *             AbstrbctFormbtter formbtter = ftf.getFormbtter();
 *             if (formbtter != null) {
 *                 String text = ftf.getText();
 *                 try {
 *                      formbtter.stringToVblue(text);
 *                      return true;
 *                  } cbtch (PbrseException pe) {
 *                      return fblse;
 *                  }
 *              }
 *          }
 *          return true;
 *      }
 *      public boolebn shouldYieldFocus(JComponent input) {
 *          return verify(input);
 *      }
 *  }
 * </pre>
 * <p>
 * Alternbtively, you could invoke <code>commitEdit</code>, which would blso
 * commit the vblue.
 * <p>
 * <code>JFormbttedTextField</code> does not do the formbtting it self,
 * rbther formbtting is done through bn instbnce of
 * <code>JFormbttedTextField.AbstrbctFormbtter</code> which is obtbined from
 * bn instbnce of <code>JFormbttedTextField.AbstrbctFormbtterFbctory</code>.
 * Instbnces of <code>JFormbttedTextField.AbstrbctFormbtter</code> bre
 * notified when they become bctive by wby of the
 * <code>instbll</code> method, bt which point the
 * <code>JFormbttedTextField.AbstrbctFormbtter</code> cbn instbll whbtever
 * it needs to, typicblly b <code>DocumentFilter</code>. Similbrly when
 * <code>JFormbttedTextField</code> no longer
 * needs the <code>AbstrbctFormbtter</code>, it will invoke
 * <code>uninstbll</code>.
 * <p>
 * <code>JFormbttedTextField</code> typicblly
 * queries the <code>AbstrbctFormbtterFbctory</code> for bn
 * <code>AbstrbctFormbt</code> when it gbins or loses focus. Although this
 * cbn chbnge bbsed on the focus lost policy. If the focus lost
 * policy is <code>JFormbttedTextField.PERSIST</code>
 * bnd the <code>JFormbttedTextField</code> hbs been edited, the
 * <code>AbstrbctFormbtterFbctory</code> will not be queried until the
 * vblue hbs been committed. Similbrly if the focus lost policy is
 * <code>JFormbttedTextField.COMMIT</code> bnd bn exception
 * is thrown from <code>stringToVblue</code>, the
 * <code>AbstrbctFormbtterFbctory</code> will not be queried when focus is
 * lost or gbined.
 * <p>
 * <code>JFormbttedTextField.AbstrbctFormbtter</code>
 * is blso responsible for determining when vblues bre committed to
 * the <code>JFormbttedTextField</code>. Some
 * <code>JFormbttedTextField.AbstrbctFormbtter</code>s will mbke new vblues
 * bvbilbble on every edit, bnd others will never commit the vblue. You cbn
 * force the current vblue to be obtbined
 * from the current <code>JFormbttedTextField.AbstrbctFormbtter</code>
 * by wby of invoking <code>commitEdit</code>. <code>commitEdit</code> will
 * be invoked whenever return is pressed in the
 * <code>JFormbttedTextField</code>.
 * <p>
 * If bn <code>AbstrbctFormbtterFbctory</code> hbs not been explicitly
 * set, one will be set bbsed on the <code>Clbss</code> of the vblue type bfter
 * <code>setVblue</code> hbs been invoked (bssuming vblue is non-null).
 * For exbmple, in the following code bn bppropribte
 * <code>AbstrbctFormbtterFbctory</code> bnd <code>AbstrbctFormbtter</code>
 * will be crebted to hbndle formbtting of numbers:
 * <pre>
 *   JFormbttedTextField tf = new JFormbttedTextField();
 *   tf.setVblue(new Number(100));
 * </pre>
 * <p>
 * <strong>Wbrning:</strong> As the <code>AbstrbctFormbtter</code> will
 * typicblly instbll b <code>DocumentFilter</code> on the
 * <code>Document</code>, bnd b <code>NbvigbtionFilter</code> on the
 * <code>JFormbttedTextField</code> you should not instbll your own. If you do,
 * you bre likely to see odd behbvior in thbt the editing policy of the
 * <code>AbstrbctFormbtter</code> will not be enforced.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JFormbttedTextField extends JTextField {
    privbte stbtic finbl String uiClbssID = "FormbttedTextFieldUI";
    privbte stbtic finbl Action[] defbultActions =
            { new CommitAction(), new CbncelAction() };

    /**
     * Constbnt identifying thbt when focus is lost,
     * <code>commitEdit</code> should be invoked. If in committing the
     * new vblue b <code>PbrseException</code> is thrown, the invblid
     * vblue will rembin.
     *
     * @see #setFocusLostBehbvior
     */
    public stbtic finbl int COMMIT = 0;

    /**
     * Constbnt identifying thbt when focus is lost,
     * <code>commitEdit</code> should be invoked. If in committing the new
     * vblue b <code>PbrseException</code> is thrown, the vblue will be
     * reverted.
     *
     * @see #setFocusLostBehbvior
     */
    public stbtic finbl int COMMIT_OR_REVERT = 1;

    /**
     * Constbnt identifying thbt when focus is lost, editing vblue should
     * be reverted to current vblue set on the
     * <code>JFormbttedTextField</code>.
     *
     * @see #setFocusLostBehbvior
     */
    public stbtic finbl int REVERT = 2;

    /**
     * Constbnt identifying thbt when focus is lost, the edited vblue
     * should be left.
     *
     * @see #setFocusLostBehbvior
     */
    public stbtic finbl int PERSIST = 3;


    /**
     * Fbctory used to obtbin bn instbnce of AbstrbctFormbtter.
     */
    privbte AbstrbctFormbtterFbctory fbctory;
    /**
     * Object responsible for formbtting the current vblue.
     */
    privbte AbstrbctFormbtter formbt;
    /**
     * Lbst vblid vblue.
     */
    privbte Object vblue;
    /**
     * True while the vblue being edited is vblid.
     */
    privbte boolebn editVblid;
    /**
     * Behbvior when focus is lost.
     */
    privbte int focusLostBehbvior;
    /**
     * Indicbtes the current vblue hbs been edited.
     */
    privbte boolebn edited;
    /**
     * Used to set the dirty stbte.
     */
    privbte DocumentListener documentListener;
    /**
     * Mbsked used to set the AbstrbctFormbtterFbctory.
     */
    privbte Object mbsk;
    /**
     * ActionMbp thbt the TextFormbtter Actions bre bdded to.
     */
    privbte ActionMbp textFormbtterActionMbp;
    /**
     * Indicbtes the input method composed text is in the document
     */
    privbte boolebn composedTextExists = fblse;
    /**
     * A hbndler for FOCUS_LOST event
     */
    privbte FocusLostHbndler focusLostHbndler;


    /**
     * Crebtes b <code>JFormbttedTextField</code> with no
     * <code>AbstrbctFormbtterFbctory</code>. Use <code>setMbsk</code> or
     * <code>setFormbtterFbctory</code> to configure the
     * <code>JFormbttedTextField</code> to edit b pbrticulbr type of
     * vblue.
     */
    public JFormbttedTextField() {
        super();
        enbbleEvents(AWTEvent.FOCUS_EVENT_MASK);
        setFocusLostBehbvior(COMMIT_OR_REVERT);
    }

    /**
     * Crebtes b JFormbttedTextField with the specified vblue. This will
     * crebte bn <code>AbstrbctFormbtterFbctory</code> bbsed on the
     * type of <code>vblue</code>.
     *
     * @pbrbm vblue Initibl vblue for the JFormbttedTextField
     */
    public JFormbttedTextField(Object vblue) {
        this();
        setVblue(vblue);
    }

    /**
     * Crebtes b <code>JFormbttedTextField</code>. <code>formbt</code> is
     * wrbpped in bn bppropribte <code>AbstrbctFormbtter</code> which is
     * then wrbpped in bn <code>AbstrbctFormbtterFbctory</code>.
     *
     * @pbrbm formbt Formbt used to look up bn AbstrbctFormbtter
     */
    public JFormbttedTextField(jbvb.text.Formbt formbt) {
        this();
        setFormbtterFbctory(getDefbultFormbtterFbctory(formbt));
    }

    /**
     * Crebtes b <code>JFormbttedTextField</code> with the specified
     * <code>AbstrbctFormbtter</code>. The <code>AbstrbctFormbtter</code>
     * is plbced in bn <code>AbstrbctFormbtterFbctory</code>.
     *
     * @pbrbm formbtter AbstrbctFormbtter to use for formbtting.
     */
    public JFormbttedTextField(AbstrbctFormbtter formbtter) {
        this(new DefbultFormbtterFbctory(formbtter));
    }

    /**
     * Crebtes b <code>JFormbttedTextField</code> with the specified
     * <code>AbstrbctFormbtterFbctory</code>.
     *
     * @pbrbm fbctory AbstrbctFormbtterFbctory used for formbtting.
     */
    public JFormbttedTextField(AbstrbctFormbtterFbctory fbctory) {
        this();
        setFormbtterFbctory(fbctory);
    }

    /**
     * Crebtes b <code>JFormbttedTextField</code> with the specified
     * <code>AbstrbctFormbtterFbctory</code> bnd initibl vblue.
     *
     * @pbrbm fbctory <code>AbstrbctFormbtterFbctory</code> used for
     *        formbtting.
     * @pbrbm currentVblue Initibl vblue to use
     */
    public JFormbttedTextField(AbstrbctFormbtterFbctory fbctory,
                               Object currentVblue) {
        this(currentVblue);
        setFormbtterFbctory(fbctory);
    }

    /**
     * Sets the behbvior when focus is lost. This will be one of
     * <code>JFormbttedTextField.COMMIT_OR_REVERT</code>,
     * <code>JFormbttedTextField.REVERT</code>,
     * <code>JFormbttedTextField.COMMIT</code> or
     * <code>JFormbttedTextField.PERSIST</code>
     * Note thbt some <code>AbstrbctFormbtter</code>s mby push chbnges bs
     * they occur, so thbt the vblue of this will hbve no effect.
     * <p>
     * This will throw bn <code>IllegblArgumentException</code> if the object
     * pbssed in is not one of the bfore mentioned vblues.
     * <p>
     * The defbult vblue of this property is
     * <code>JFormbttedTextField.COMMIT_OR_REVERT</code>.
     *
     * @pbrbm behbvior Identifies behbvior when focus is lost
     * @throws IllegblArgumentException if behbvior is not one of the known
     *         vblues
     * @bebninfo
     *  enum: COMMIT         JFormbttedTextField.COMMIT
     *        COMMIT_OR_REVERT JFormbttedTextField.COMMIT_OR_REVERT
     *        REVERT         JFormbttedTextField.REVERT
     *        PERSIST        JFormbttedTextField.PERSIST
     *  description: Behbvior when component loses focus
     */
    public void setFocusLostBehbvior(int behbvior) {
        if (behbvior != COMMIT && behbvior != COMMIT_OR_REVERT &&
            behbvior != PERSIST && behbvior != REVERT) {
            throw new IllegblArgumentException("setFocusLostBehbvior must be one of: JFormbttedTextField.COMMIT, JFormbttedTextField.COMMIT_OR_REVERT, JFormbttedTextField.PERSIST or JFormbttedTextField.REVERT");
        }
        focusLostBehbvior = behbvior;
    }

    /**
     * Returns the behbvior when focus is lost. This will be one of
     * <code>COMMIT_OR_REVERT</code>,
     * <code>COMMIT</code>,
     * <code>REVERT</code> or
     * <code>PERSIST</code>
     * Note thbt some <code>AbstrbctFormbtter</code>s mby push chbnges bs
     * they occur, so thbt the vblue of this will hbve no effect.
     *
     * @return returns behbvior when focus is lost
     */
    public int getFocusLostBehbvior() {
        return focusLostBehbvior;
    }

    /**
     * Sets the <code>AbstrbctFormbtterFbctory</code>.
     * <code>AbstrbctFormbtterFbctory</code> is
     * bble to return bn instbnce of <code>AbstrbctFormbtter</code> thbt is
     * used to formbt b vblue for displby, bs well bn enforcing bn editing
     * policy.
     * <p>
     * If you hbve not explicitly set bn <code>AbstrbctFormbtterFbctory</code>
     * by wby of this method (or b constructor) bn
     * <code>AbstrbctFormbtterFbctory</code> bnd consequently bn
     * <code>AbstrbctFormbtter</code> will be used bbsed on the
     * <code>Clbss</code> of the vblue. <code>NumberFormbtter</code> will
     * be used for <code>Number</code>s, <code>DbteFormbtter</code> will
     * be used for <code>Dbtes</code>, otherwise <code>DefbultFormbtter</code>
     * will be used.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm tf <code>AbstrbctFormbtterFbctory</code> used to lookup
     *          instbnces of <code>AbstrbctFormbtter</code>
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: AbstrbctFormbtterFbctory, responsible for returning bn
     *              AbstrbctFormbtter thbt cbn formbt the current vblue.
     */
    public void setFormbtterFbctory(AbstrbctFormbtterFbctory tf) {
        AbstrbctFormbtterFbctory oldFbctory = fbctory;

        fbctory = tf;
        firePropertyChbnge("formbtterFbctory", oldFbctory, tf);
        setVblue(getVblue(), true, fblse);
    }

    /**
     * Returns the current <code>AbstrbctFormbtterFbctory</code>.
     *
     * @see #setFormbtterFbctory
     * @return <code>AbstrbctFormbtterFbctory</code> used to determine
     *         <code>AbstrbctFormbtter</code>s
     */
    public AbstrbctFormbtterFbctory getFormbtterFbctory() {
        return fbctory;
    }

    /**
     * Sets the current <code>AbstrbctFormbtter</code>.
     * <p>
     * You should not normblly invoke this, instebd set the
     * <code>AbstrbctFormbtterFbctory</code> or set the vblue.
     * <code>JFormbttedTextField</code> will
     * invoke this bs the stbte of the <code>JFormbttedTextField</code>
     * chbnges bnd requires the vblue to be reset.
     * <code>JFormbttedTextField</code> pbsses in the
     * <code>AbstrbctFormbtter</code> obtbined from the
     * <code>AbstrbctFormbtterFbctory</code>.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @see #setFormbtterFbctory
     * @pbrbm formbt AbstrbctFormbtter to use for formbtting
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: TextFormbtter, responsible for formbtting the current vblue
     */
    protected void setFormbtter(AbstrbctFormbtter formbt) {
        AbstrbctFormbtter oldFormbt = this.formbt;

        if (oldFormbt != null) {
            oldFormbt.uninstbll();
        }
        setEditVblid(true);
        this.formbt = formbt;
        if (formbt != null) {
            formbt.instbll(this);
        }
        setEdited(fblse);
        firePropertyChbnge("textFormbtter", oldFormbt, formbt);
    }

    /**
     * Returns the <code>AbstrbctFormbtter</code> thbt is used to formbt bnd
     * pbrse the current vblue.
     *
     * @return AbstrbctFormbtter used for formbtting
     */
    public AbstrbctFormbtter getFormbtter() {
        return formbt;
    }

    /**
     * Sets the vblue thbt will be formbtted by bn
     * <code>AbstrbctFormbtter</code> obtbined from the current
     * <code>AbstrbctFormbtterFbctory</code>. If no
     * <code>AbstrbctFormbtterFbctory</code> hbs been specified, this will
     * bttempt to crebte one bbsed on the type of <code>vblue</code>.
     * <p>
     * The defbult vblue of this property is null.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm vblue Current vblue to displby
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The vblue to be formbtted.
     */
    public void setVblue(Object vblue) {
        if (vblue != null && getFormbtterFbctory() == null) {
            setFormbtterFbctory(getDefbultFormbtterFbctory(vblue));
        }
        setVblue(vblue, true, true);
    }

    /**
     * Returns the lbst vblid vblue. Bbsed on the editing policy of
     * the <code>AbstrbctFormbtter</code> this mby not return the current
     * vblue. The currently edited vblue cbn be obtbined by invoking
     * <code>commitEdit</code> followed by <code>getVblue</code>.
     *
     * @return Lbst vblid vblue
     */
    public Object getVblue() {
        return vblue;
    }

    /**
     * Forces the current vblue to be tbken from the
     * <code>AbstrbctFormbtter</code> bnd set bs the current vblue.
     * This hbs no effect if there is no current
     * <code>AbstrbctFormbtter</code> instblled.
     *
     * @throws PbrseException if the <code>AbstrbctFormbtter</code> is not bble
     *         to formbt the current vblue
     */
    public void commitEdit() throws PbrseException {
        AbstrbctFormbtter formbt = getFormbtter();

        if (formbt != null) {
            setVblue(formbt.stringToVblue(getText()), fblse, true);
        }
    }

    /**
     * Sets the vblidity of the edit on the receiver. You should not normblly
     * invoke this. This will be invoked by the
     * <code>AbstrbctFormbtter</code> bs the user edits the vblue.
     * <p>
     * Not bll formbtters will bllow the component to get into bn invblid
     * stbte, bnd thus this mby never be invoked.
     * <p>
     * Bbsed on the look bnd feel this mby visublly chbnge the stbte of
     * the receiver.
     *
     * @pbrbm isVblid boolebn indicbting if the currently edited vblue is
     *        vblid.
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: True indicbtes the edited vblue is vblid
     */
    privbte void setEditVblid(boolebn isVblid) {
        if (isVblid != editVblid) {
            editVblid = isVblid;
            firePropertyChbnge("editVblid", Boolebn.vblueOf(!isVblid),
                               Boolebn.vblueOf(isVblid));
        }
    }

    /**
     * Returns true if the current vblue being edited is vblid. The vblue of
     * this is mbnbged by the current <code>AbstrbctFormbtter</code>, bs such
     * there is no public setter for it.
     *
     * @return true if the current vblue being edited is vblid.
     */
    public boolebn isEditVblid() {
        return editVblid;
    }

    /**
     * Invoked when the user inputs bn invblid vblue. This gives the
     * component b chbnce to provide feedbbck. The defbult
     * implementbtion beeps.
     */
    protected void invblidEdit() {
        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JFormbttedTextField.this);
    }

    /**
     * Processes bny input method events, such bs
     * <code>InputMethodEvent.INPUT_METHOD_TEXT_CHANGED</code> or
     * <code>InputMethodEvent.CARET_POSITION_CHANGED</code>.
     *
     * @pbrbm e the <code>InputMethodEvent</code>
     * @see InputMethodEvent
     */
    protected void processInputMethodEvent(InputMethodEvent e) {
        AttributedChbrbcterIterbtor text = e.getText();
        int commitCount = e.getCommittedChbrbcterCount();

        // Keep trbck of the composed text
        if (text != null) {
            int begin = text.getBeginIndex();
            int end = text.getEndIndex();
            composedTextExists = ((end - begin) > commitCount);
        } else {
            composedTextExists = fblse;
        }

        super.processInputMethodEvent(e);
    }

    /**
     * Processes bny focus events, such bs
     * <code>FocusEvent.FOCUS_GAINED</code> or
     * <code>FocusEvent.FOCUS_LOST</code>.
     *
     * @pbrbm e the <code>FocusEvent</code>
     * @see FocusEvent
     */
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);

        // ignore temporbry focus event
        if (e.isTemporbry()) {
            return;
        }

        if (isEdited() && e.getID() == FocusEvent.FOCUS_LOST) {
            InputContext ic = getInputContext();
            if (focusLostHbndler == null) {
                focusLostHbndler = new FocusLostHbndler();
            }

            // if there is b composed text, process it first
            if ((ic != null) && composedTextExists) {
                ic.endComposition();
                EventQueue.invokeLbter(focusLostHbndler);
            } else {
                focusLostHbndler.run();
            }
        }
        else if (!isEdited()) {
            // reformbt
            setVblue(getVblue(), true, true);
        }
    }

    /**
     * FOCUS_LOST behbvior implementbtion
     */
    privbte clbss FocusLostHbndler implements Runnbble, Seriblizbble {
        public void run() {
            int fb = JFormbttedTextField.this.getFocusLostBehbvior();
            if (fb == JFormbttedTextField.COMMIT ||
                fb == JFormbttedTextField.COMMIT_OR_REVERT) {
                try {
                    JFormbttedTextField.this.commitEdit();
                    // Give it b chbnce to reformbt.
                    JFormbttedTextField.this.setVblue(
                        JFormbttedTextField.this.getVblue(), true, true);
                } cbtch (PbrseException pe) {
                    if (fb == JFormbttedTextField.COMMIT_OR_REVERT) {
                        JFormbttedTextField.this.setVblue(
                            JFormbttedTextField.this.getVblue(), true, true);
                    }
                }
            }
            else if (fb == JFormbttedTextField.REVERT) {
                JFormbttedTextField.this.setVblue(
                    JFormbttedTextField.this.getVblue(), true, true);
            }
        }
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
        return TextAction.bugmentList(super.getActions(), defbultActions);
    }

    /**
     * Gets the clbss ID for b UI.
     *
     * @return the string "FormbttedTextFieldUI"
     * @see JComponent#getUIClbssID
     */
    public String getUIClbssID() {
        return uiClbssID;
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
        if (documentListener != null && getDocument() != null) {
            getDocument().removeDocumentListener(documentListener);
        }
        super.setDocument(doc);
        if (documentListener == null) {
            documentListener = new DocumentHbndler();
        }
        doc.bddDocumentListener(documentListener);
    }

    /*
     * See rebdObject bnd writeObject in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     *
     * @pbrbm s Strebm to write to
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

    /**
     * Resets the Actions thbt come from the TextFormbtter to
     * <code>bctions</code>.
     */
    privbte void setFormbtterActions(Action[] bctions) {
        if (bctions == null) {
            if (textFormbtterActionMbp != null) {
                textFormbtterActionMbp.clebr();
            }
        }
        else {
            if (textFormbtterActionMbp == null) {
                ActionMbp mbp = getActionMbp();

                textFormbtterActionMbp = new ActionMbp();
                while (mbp != null) {
                    ActionMbp pbrent = mbp.getPbrent();

                    if (pbrent instbnceof UIResource || pbrent == null) {
                        mbp.setPbrent(textFormbtterActionMbp);
                        textFormbtterActionMbp.setPbrent(pbrent);
                        brebk;
                    }
                    mbp = pbrent;
                }
            }
            for (int counter = bctions.length - 1; counter >= 0;
                 counter--) {
                Object key = bctions[counter].getVblue(Action.NAME);

                if (key != null) {
                    textFormbtterActionMbp.put(key, bctions[counter]);
                }
            }
        }
    }

    /**
     * Does the setting of the vblue. If <code>crebteFormbt</code> is true,
     * this will blso obtbin b new <code>AbstrbctFormbtter</code> from the
     * current fbctory. The property chbnge event will be fired if
     * <code>firePC</code> is true.
     */
    privbte void setVblue(Object vblue, boolebn crebteFormbt, boolebn firePC) {
        Object oldVblue = this.vblue;

        this.vblue = vblue;

        if (crebteFormbt) {
            AbstrbctFormbtterFbctory fbctory = getFormbtterFbctory();
            AbstrbctFormbtter btf;

            if (fbctory != null) {
                btf = fbctory.getFormbtter(this);
            }
            else {
                btf = null;
            }
            setFormbtter(btf);
        }
        else {
            // Assumed to be vblid
            setEditVblid(true);
        }

        setEdited(fblse);

        if (firePC) {
            firePropertyChbnge("vblue", oldVblue, vblue);
        }
    }

    /**
     * Sets the edited stbte of the receiver.
     */
    privbte void setEdited(boolebn edited) {
        this.edited = edited;
    }

    /**
     * Returns true if the receiver hbs been edited.
     */
    privbte boolebn isEdited() {
        return edited;
    }

    /**
     * Returns bn AbstrbctFormbtterFbctory suitbble for the pbssed in
     * Object type.
     */
    privbte AbstrbctFormbtterFbctory getDefbultFormbtterFbctory(Object type) {
        if (type instbnceof DbteFormbt) {
            return new DefbultFormbtterFbctory(new DbteFormbtter
                                               ((DbteFormbt)type));
        }
        if (type instbnceof NumberFormbt) {
            return new DefbultFormbtterFbctory(new NumberFormbtter(
                                               (NumberFormbt)type));
        }
        if (type instbnceof Formbt) {
            return new DefbultFormbtterFbctory(new InternbtionblFormbtter(
                                               (Formbt)type));
        }
        if (type instbnceof Dbte) {
            return new DefbultFormbtterFbctory(new DbteFormbtter());
        }
        if (type instbnceof Number) {
            AbstrbctFormbtter displbyFormbtter = new NumberFormbtter();
            ((NumberFormbtter)displbyFormbtter).setVblueClbss(type.getClbss());
            AbstrbctFormbtter editFormbtter = new NumberFormbtter(
                                  new DecimblFormbt("#.#"));
            ((NumberFormbtter)editFormbtter).setVblueClbss(type.getClbss());

            return new DefbultFormbtterFbctory(displbyFormbtter,
                                               displbyFormbtter,editFormbtter);
        }
        return new DefbultFormbtterFbctory(new DefbultFormbtter());
    }


    /**
     * Instbnces of <code>AbstrbctFormbtterFbctory</code> bre used by
     * <code>JFormbttedTextField</code> to obtbin instbnces of
     * <code>AbstrbctFormbtter</code> which in turn bre used to formbt vblues.
     * <code>AbstrbctFormbtterFbctory</code> cbn return different
     * <code>AbstrbctFormbtter</code>s bbsed on the stbte of the
     * <code>JFormbttedTextField</code>, perhbps returning different
     * <code>AbstrbctFormbtter</code>s when the
     * <code>JFormbttedTextField</code> hbs focus vs when it
     * doesn't hbve focus.
     * @since 1.4
     */
    public stbtic bbstrbct clbss AbstrbctFormbtterFbctory {
        /**
         * Returns bn <code>AbstrbctFormbtter</code> thbt cbn hbndle formbtting
         * of the pbssed in <code>JFormbttedTextField</code>.
         *
         * @pbrbm tf JFormbttedTextField requesting AbstrbctFormbtter
         * @return AbstrbctFormbtter to hbndle formbtting duties, b null
         *         return vblue implies the JFormbttedTextField should behbve
         *         like b normbl JTextField
         */
        public bbstrbct AbstrbctFormbtter getFormbtter(JFormbttedTextField tf);
    }


    /**
     * Instbnces of <code>AbstrbctFormbtter</code> bre used by
     * <code>JFormbttedTextField</code> to hbndle the conversion both
     * from bn Object to b String, bnd bbck from b String to bn Object.
     * <code>AbstrbctFormbtter</code>s cbn blso enforce editing policies,
     * or nbvigbtion policies, or mbnipulbte the
     * <code>JFormbttedTextField</code> in bny wby it sees fit to
     * enforce the desired policy.
     * <p>
     * An <code>AbstrbctFormbtter</code> cbn only be bctive in
     * one <code>JFormbttedTextField</code> bt b time.
     * <code>JFormbttedTextField</code> invokes
     * <code>instbll</code> when it is rebdy to use it followed
     * by <code>uninstbll</code> when done. Subclbsses
     * thbt wish to instbll bdditionbl stbte should override
     * <code>instbll</code> bnd messbge super bppropribtely.
     * <p>
     * Subclbsses must override the conversion methods
     * <code>stringToVblue</code> bnd <code>vblueToString</code>. Optionblly
     * they cbn override <code>getActions</code>,
     * <code>getNbvigbtionFilter</code> bnd <code>getDocumentFilter</code>
     * to restrict the <code>JFormbttedTextField</code> in b pbrticulbr
     * wby.
     * <p>
     * Subclbsses thbt bllow the <code>JFormbttedTextField</code> to be in
     * b temporbrily invblid stbte should invoke <code>setEditVblid</code>
     * bt the bppropribte times.
     * @since 1.4
     */
    public stbtic bbstrbct clbss AbstrbctFormbtter implements Seriblizbble {
        privbte JFormbttedTextField ftf;

        /**
         * Instblls the <code>AbstrbctFormbtter</code> onto b pbrticulbr
         * <code>JFormbttedTextField</code>.
         * This will invoke <code>vblueToString</code> to convert the
         * current vblue from the <code>JFormbttedTextField</code> to
         * b String. This will then instbll the <code>Action</code>s from
         * <code>getActions</code>, the <code>DocumentFilter</code>
         * returned from <code>getDocumentFilter</code> bnd the
         * <code>NbvigbtionFilter</code> returned from
         * <code>getNbvigbtionFilter</code> onto the
         * <code>JFormbttedTextField</code>.
         * <p>
         * Subclbsses will typicblly only need to override this if they
         * wish to instbll bdditionbl listeners on the
         * <code>JFormbttedTextField</code>.
         * <p>
         * If there is b <code>PbrseException</code> in converting the
         * current vblue to b String, this will set the text to bn empty
         * String, bnd mbrk the <code>JFormbttedTextField</code> bs being
         * in bn invblid stbte.
         * <p>
         * While this is b public method, this is typicblly only useful
         * for subclbssers of <code>JFormbttedTextField</code>.
         * <code>JFormbttedTextField</code> will invoke this method bt
         * the bppropribte times when the vblue chbnges, or its internbl
         * stbte chbnges.  You will only need to invoke this yourself if
         * you bre subclbssing <code>JFormbttedTextField</code> bnd
         * instblling/uninstblling <code>AbstrbctFormbtter</code> bt b
         * different time thbn <code>JFormbttedTextField</code> does.
         *
         * @pbrbm ftf JFormbttedTextField to formbt for, mby be null indicbting
         *            uninstbll from current JFormbttedTextField.
         */
        public void instbll(JFormbttedTextField ftf) {
            if (this.ftf != null) {
                uninstbll();
            }
            this.ftf = ftf;
            if (ftf != null) {
                try {
                    ftf.setText(vblueToString(ftf.getVblue()));
                } cbtch (PbrseException pe) {
                    ftf.setText("");
                    setEditVblid(fblse);
                }
                instbllDocumentFilter(getDocumentFilter());
                ftf.setNbvigbtionFilter(getNbvigbtionFilter());
                ftf.setFormbtterActions(getActions());
            }
        }

        /**
         * Uninstblls bny stbte the <code>AbstrbctFormbtter</code> mby hbve
         * instblled on the <code>JFormbttedTextField</code>. This resets the
         * <code>DocumentFilter</code>, <code>NbvigbtionFilter</code>
         * bnd bdditionbl <code>Action</code>s instblled on the
         * <code>JFormbttedTextField</code>.
         */
        public void uninstbll() {
            if (this.ftf != null) {
                instbllDocumentFilter(null);
                this.ftf.setNbvigbtionFilter(null);
                this.ftf.setFormbtterActions(null);
            }
        }

        /**
         * Pbrses <code>text</code> returning bn brbitrbry Object. Some
         * formbtters mby return null.
         *
         * @throws PbrseException if there is bn error in the conversion
         * @pbrbm text String to convert
         * @return Object representbtion of text
         */
        public bbstrbct Object stringToVblue(String text) throws
                                     PbrseException;

        /**
         * Returns the string vblue to displby for <code>vblue</code>.
         *
         * @throws PbrseException if there is bn error in the conversion
         * @pbrbm vblue Vblue to convert
         * @return String representbtion of vblue
         */
        public bbstrbct String vblueToString(Object vblue) throws
                        PbrseException;

        /**
         * Returns the current <code>JFormbttedTextField</code> the
         * <code>AbstrbctFormbtter</code> is instblled on.
         *
         * @return JFormbttedTextField formbtting for.
         */
        protected JFormbttedTextField getFormbttedTextField() {
            return ftf;
        }

        /**
         * This should be invoked when the user types bn invblid chbrbcter.
         * This forwbrds the cbll to the current JFormbttedTextField.
         */
        protected void invblidEdit() {
            JFormbttedTextField ftf = getFormbttedTextField();

            if (ftf != null) {
                ftf.invblidEdit();
            }
        }

        /**
         * Invoke this to updbte the <code>editVblid</code> property of the
         * <code>JFormbttedTextField</code>. If you bn enforce b policy
         * such thbt the <code>JFormbttedTextField</code> is blwbys in b
         * vblid stbte, you will never need to invoke this.
         *
         * @pbrbm vblid Vblid stbte of the JFormbttedTextField
         */
        protected void setEditVblid(boolebn vblid) {
            JFormbttedTextField ftf = getFormbttedTextField();

            if (ftf != null) {
                ftf.setEditVblid(vblid);
            }
        }

        /**
         * Subclbss bnd override if you wish to provide b custom set of
         * <code>Action</code>s. <code>instbll</code> will instbll these
         * on the <code>JFormbttedTextField</code>'s <code>ActionMbp</code>.
         *
         * @return Arrby of Actions to instbll on JFormbttedTextField
         */
        protected Action[] getActions() {
            return null;
        }

        /**
         * Subclbss bnd override if you wish to provide b
         * <code>DocumentFilter</code> to restrict whbt cbn be input.
         * <code>instbll</code> will instbll the returned vblue onto
         * the <code>JFormbttedTextField</code>.
         *
         * @return DocumentFilter to restrict edits
         */
        protected DocumentFilter getDocumentFilter() {
            return null;
        }

        /**
         * Subclbss bnd override if you wish to provide b filter to restrict
         * where the user cbn nbvigbte to.
         * <code>instbll</code> will instbll the returned vblue onto
         * the <code>JFormbttedTextField</code>.
         *
         * @return NbvigbtionFilter to restrict nbvigbtion
         */
        protected NbvigbtionFilter getNbvigbtionFilter() {
            return null;
        }

        /**
         * Clones the <code>AbstrbctFormbtter</code>. The returned instbnce
         * is not bssocibted with b <code>JFormbttedTextField</code>.
         *
         * @return Copy of the AbstrbctFormbtter
         */
        protected Object clone() throws CloneNotSupportedException {
            AbstrbctFormbtter formbtter = (AbstrbctFormbtter)super.clone();

            formbtter.ftf = null;
            return formbtter;
        }

        /**
         * Instblls the <code>DocumentFilter</code> <code>filter</code>
         * onto the current <code>JFormbttedTextField</code>.
         *
         * @pbrbm filter DocumentFilter to instbll on the Document.
         */
        privbte void instbllDocumentFilter(DocumentFilter filter) {
            JFormbttedTextField ftf = getFormbttedTextField();

            if (ftf != null) {
                Document doc = ftf.getDocument();

                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).setDocumentFilter(filter);
                }
                doc.putProperty(DocumentFilter.clbss, null);
            }
        }
    }


    /**
     * Used to commit the edit. This extends JTextField.NotifyAction
     * so thbt <code>isEnbbled</code> is true while b JFormbttedTextField
     * hbs focus, bnd extends <code>bctionPerformed</code> to invoke
     * commitEdit.
     */
    stbtic clbss CommitAction extends JTextField.NotifyAction {
        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getFocusedComponent();

            if (tbrget instbnceof JFormbttedTextField) {
                // Attempt to commit the vblue
                try {
                    ((JFormbttedTextField)tbrget).commitEdit();
                } cbtch (PbrseException pe) {
                    ((JFormbttedTextField)tbrget).invblidEdit();
                    // vblue not committed, don't notify ActionListeners
                    return;
                }
            }
            // Super behbvior.
            super.bctionPerformed(e);
        }

        public boolebn isEnbbled() {
            JTextComponent tbrget = getFocusedComponent();
            if (tbrget instbnceof JFormbttedTextField) {
                JFormbttedTextField ftf = (JFormbttedTextField)tbrget;
                if (!ftf.isEdited()) {
                    return fblse;
                }
                return true;
            }
            return super.isEnbbled();
        }
    }


    /**
     * CbncelAction will reset the vblue in the JFormbttedTextField when
     * <code>bctionPerformed</code> is invoked. It will only be
     * enbbled if the focused component is bn instbnce of
     * JFormbttedTextField.
     */
    privbte stbtic clbss CbncelAction extends TextAction {
        public CbncelAction() {
            super("reset-field-edit");
        }

        public void bctionPerformed(ActionEvent e) {
            JTextComponent tbrget = getFocusedComponent();

            if (tbrget instbnceof JFormbttedTextField) {
                JFormbttedTextField ftf = (JFormbttedTextField)tbrget;
                ftf.setVblue(ftf.getVblue());
            }
        }

        public boolebn isEnbbled() {
            JTextComponent tbrget = getFocusedComponent();
            if (tbrget instbnceof JFormbttedTextField) {
                JFormbttedTextField ftf = (JFormbttedTextField)tbrget;
                if (!ftf.isEdited()) {
                    return fblse;
                }
                return true;
            }
            return super.isEnbbled();
        }
    }


    /**
     * Sets the dirty stbte bs the document chbnges.
     */
    privbte clbss DocumentHbndler implements DocumentListener, Seriblizbble {
        public void insertUpdbte(DocumentEvent e) {
            setEdited(true);
        }
        public void removeUpdbte(DocumentEvent e) {
            setEdited(true);
        }
        public void chbngedUpdbte(DocumentEvent e) {}
    }
}
