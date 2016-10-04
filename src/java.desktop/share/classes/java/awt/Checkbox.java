/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.CheckboxPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;


/**
 * A check box is b grbphicbl component thbt cbn be in either bn
 * "on" (<code>true</code>) or "off" (<code>fblse</code>) stbte.
 * Clicking on b check box chbnges its stbte from
 * "on" to "off," or from "off" to "on."
 * <p>
 * The following code exbmple crebtes b set of check boxes in
 * b grid lbyout:
 *
 * <hr><blockquote><pre>
 * setLbyout(new GridLbyout(3, 1));
 * bdd(new Checkbox("one", null, true));
 * bdd(new Checkbox("two"));
 * bdd(new Checkbox("three"));
 * </pre></blockquote><hr>
 * <p>
 * This imbge depicts the check boxes bnd grid lbyout
 * crebted by this code exbmple:
 * <p>
 * <img src="doc-files/Checkbox-1.gif" blt="The following context describes the grbphic."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * The button lbbeled <code>one</code> is in the "on" stbte, bnd the
 * other two bre in the "off" stbte. In this exbmple, which uses the
 * <code>GridLbyout</code> clbss, the stbtes of the three check
 * boxes bre set independently.
 * <p>
 * Alternbtively, severbl check boxes cbn be grouped together under
 * the control of b single object, using the
 * <code>CheckboxGroup</code> clbss.
 * In b check box group, bt most one button cbn be in the "on"
 * stbte bt bny given time. Clicking on b check box to turn it on
 * forces bny other check box in the sbme group thbt is on
 * into the "off" stbte.
 *
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.GridLbyout
 * @see         jbvb.bwt.CheckboxGroup
 * @since       1.0
 */
public clbss Checkbox extends Component implements ItemSelectbble, Accessible {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * The lbbel of the Checkbox.
     * This field cbn be null.
     * @seribl
     * @see #getLbbel()
     * @see #setLbbel(String)
     */
    String lbbel;

    /**
     * The stbte of the <code>Checkbox</code>.
     * @seribl
     * @see #getStbte()
     * @see #setStbte(boolebn)
     */
    boolebn stbte;

    /**
     * The check box group.
         * This field cbn be null indicbting thbt the checkbox
         * is not b group checkbox.
         * @seribl
     * @see #getCheckboxGroup()
     * @see #setCheckboxGroup(CheckboxGroup)
     */
    CheckboxGroup group;

    trbnsient ItemListener itemListener;

    privbte stbtic finbl String bbse = "checkbox";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 7270714317450821763L;

    /**
     * Helper function for setStbte bnd CheckboxGroup.setSelectedCheckbox
     * Should rembin pbckbge-privbte.
     */
    void setStbteInternbl(boolebn stbte) {
        this.stbte = stbte;
        CheckboxPeer peer = (CheckboxPeer)this.peer;
        if (peer != null) {
            peer.setStbte(stbte);
        }
    }

    /**
     * Crebtes b check box with bn empty string for its lbbel.
     * The stbte of this check box is set to "off," bnd it is not
     * pbrt of bny check box group.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Checkbox() throws HebdlessException {
        this("", fblse, null);
    }

    /**
     * Crebtes b check box with the specified lbbel.  The stbte
     * of this check box is set to "off," bnd it is not pbrt of
     * bny check box group.
     *
     * @pbrbm     lbbel   b string lbbel for this check box,
     *                        or <code>null</code> for no lbbel.
     * @exception HebdlessException if
     *      <code>GrbphicsEnvironment.isHebdless</code>
     *      returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Checkbox(String lbbel) throws HebdlessException {
        this(lbbel, fblse, null);
    }

    /**
     * Crebtes b check box with the specified lbbel
     * bnd sets the specified stbte.
     * This check box is not pbrt of bny check box group.
     *
     * @pbrbm     lbbel   b string lbbel for this check box,
     *                        or <code>null</code> for no lbbel
     * @pbrbm     stbte    the initibl stbte of this check box
     * @exception HebdlessException if
     *     <code>GrbphicsEnvironment.isHebdless</code>
     *     returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public Checkbox(String lbbel, boolebn stbte) throws HebdlessException {
        this(lbbel, stbte, null);
    }

    /**
     * Constructs b Checkbox with the specified lbbel, set to the
     * specified stbte, bnd in the specified check box group.
     *
     * @pbrbm     lbbel   b string lbbel for this check box,
     *                        or <code>null</code> for no lbbel.
     * @pbrbm     stbte   the initibl stbte of this check box.
     * @pbrbm     group   b check box group for this check box,
     *                           or <code>null</code> for no group.
     * @exception HebdlessException if
     *     <code>GrbphicsEnvironment.isHebdless</code>
     *     returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.1
     */
    public Checkbox(String lbbel, boolebn stbte, CheckboxGroup group)
        throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        this.lbbel = lbbel;
        this.stbte = stbte;
        this.group = group;
        if (stbte && (group != null)) {
            group.setSelectedCheckbox(this);
        }
    }

    /**
     * Crebtes b check box with the specified lbbel, in the specified
     * check box group, bnd set to the specified stbte.
     *
     * @pbrbm     lbbel   b string lbbel for this check box,
     *                        or <code>null</code> for no lbbel.
     * @pbrbm     group   b check box group for this check box,
     *                           or <code>null</code> for no group.
     * @pbrbm     stbte   the initibl stbte of this check box.
     * @exception HebdlessException if
     *    <code>GrbphicsEnvironment.isHebdless</code>
     *    returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since     1.1
     */
    public Checkbox(String lbbel, CheckboxGroup group, boolebn stbte)
        throws HebdlessException {
        this(lbbel, stbte, group);
    }

    /**
     * Constructs b nbme for this component.  Cblled by
     * <code>getNbme</code> when the nbme is <code>null</code>.
     *
     * @return b nbme for this component
     */
    String constructComponentNbme() {
        synchronized (Checkbox.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the peer of the Checkbox. The peer bllows you to chbnge the
     * look of the Checkbox without chbnging its functionblity.
     *
     * @see     jbvb.bwt.Toolkit#crebteCheckbox(jbvb.bwt.Checkbox)
     * @see     jbvb.bwt.Component#getToolkit()
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteCheckbox(this);
            super.bddNotify();
        }
    }

    /**
     * Gets the lbbel of this check box.
     *
     * @return   the lbbel of this check box, or <code>null</code>
     *                  if this check box hbs no lbbel.
     * @see      #setLbbel(String)
     */
    public String getLbbel() {
        return lbbel;
    }

    /**
     * Sets this check box's lbbel to be the string brgument.
     *
     * @pbrbm    lbbel   b string to set bs the new lbbel, or
     *                        <code>null</code> for no lbbel.
     * @see      #getLbbel
     */
    public void setLbbel(String lbbel) {
        boolebn testvblid = fblse;

        synchronized (this) {
            if (lbbel != this.lbbel && (this.lbbel == null ||
                                        !this.lbbel.equbls(lbbel))) {
                this.lbbel = lbbel;
                CheckboxPeer peer = (CheckboxPeer)this.peer;
                if (peer != null) {
                    peer.setLbbel(lbbel);
                }
                testvblid = true;
            }
        }

        // This could chbnge the preferred size of the Component.
        if (testvblid) {
            invblidbteIfVblid();
        }
    }

    /**
     * Determines whether this check box is in the "on" or "off" stbte.
     * The boolebn vblue <code>true</code> indicbtes the "on" stbte,
     * bnd <code>fblse</code> indicbtes the "off" stbte.
     *
     * @return    the stbte of this check box, bs b boolebn vblue
     * @see       #setStbte
     */
    public boolebn getStbte() {
        return stbte;
    }

    /**
     * Sets the stbte of this check box to the specified stbte.
     * The boolebn vblue <code>true</code> indicbtes the "on" stbte,
     * bnd <code>fblse</code> indicbtes the "off" stbte.
     *
     * <p>Note thbt this method should be primbrily used to
     * initiblize the stbte of the checkbox.  Progrbmmbticblly
     * setting the stbte of the checkbox will <i>not</i> trigger
     * bn <code>ItemEvent</code>.  The only wby to trigger bn
     * <code>ItemEvent</code> is by user interbction.
     *
     * @pbrbm     stbte   the boolebn stbte of the check box
     * @see       #getStbte
     */
    public void setStbte(boolebn stbte) {
        /* Cbnnot hold check box lock when cblling group.setSelectedCheckbox. */
        CheckboxGroup group = this.group;
        if (group != null) {
            if (stbte) {
                group.setSelectedCheckbox(this);
            } else if (group.getSelectedCheckbox() == this) {
                stbte = true;
            }
        }
        setStbteInternbl(stbte);
    }

    /**
     * Returns bn brrby (length 1) contbining the checkbox
     * lbbel or null if the checkbox is not selected.
     * @see ItemSelectbble
     */
    public Object[] getSelectedObjects() {
        if (stbte) {
            Object[] items = new Object[1];
            items[0] = lbbel;
            return items;
        }
        return null;
    }

    /**
     * Determines this check box's group.
     * @return     this check box's group, or <code>null</code>
     *               if the check box is not pbrt of b check box group.
     * @see        #setCheckboxGroup(CheckboxGroup)
     */
    public CheckboxGroup getCheckboxGroup() {
        return group;
    }

    /**
     * Sets this check box's group to the specified check box group.
     * If this check box is blrebdy in b different check box group,
     * it is first tbken out of thbt group.
     * <p>
     * If the stbte of this check box is <code>true</code> bnd the new
     * group blrebdy hbs b check box selected, this check box's stbte
     * is chbnged to <code>fblse</code>.  If the stbte of this check
     * box is <code>true</code> bnd the new group hbs no check box
     * selected, this check box becomes the selected checkbox for
     * the new group bnd its stbte is <code>true</code>.
     *
     * @pbrbm     g   the new check box group, or <code>null</code>
     *                to remove this check box from bny check box group
     * @see       #getCheckboxGroup
     */
    public void setCheckboxGroup(CheckboxGroup g) {
        CheckboxGroup oldGroup;
        boolebn oldStbte;

        /* Do nothing if this check box hbs blrebdy belonged
         * to the check box group g.
         */
        if (this.group == g) {
            return;
        }

        synchronized (this) {
            oldGroup = this.group;
            oldStbte = getStbte();

            this.group = g;
            CheckboxPeer peer = (CheckboxPeer)this.peer;
            if (peer != null) {
                peer.setCheckboxGroup(g);
            }
            if (this.group != null && getStbte()) {
                if (this.group.getSelectedCheckbox() != null) {
                    setStbte(fblse);
                } else {
                    this.group.setSelectedCheckbox(this);
                }
            }
        }

        /* Locking check box below could cbuse debdlock with
         * CheckboxGroup's setSelectedCheckbox method.
         *
         * Fix for 4726853 by kdm@spbrc.spb.su
         * Here we should check if this check box wbs selected
         * in the previous group bnd set selected check box to
         * null for thbt group if so.
         */
        if (oldGroup != null && oldStbte) {
            oldGroup.setSelectedCheckbox(null);
        }
    }

    /**
     * Adds the specified item listener to receive item events from
     * this check box.  Item events bre sent to listeners in response
     * to user input, but not in response to cblls to setStbte().
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l    the item listener
     * @see           #removeItemListener
     * @see           #getItemListeners
     * @see           #setStbte
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since         1.1
     */
    public synchronized void bddItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.bdd(itemListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified item listener so thbt the item listener
     * no longer receives item events from this check box.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         l    the item listener
     * @see           #bddItemListener
     * @see           #getItemListeners
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since         1.1
     */
    public synchronized void removeItemListener(ItemListener l) {
        if (l == null) {
            return;
        }
        itemListener = AWTEventMulticbster.remove(itemListener, l);
    }

    /**
     * Returns bn brrby of bll the item listeners
     * registered on this checkbox.
     *
     * @return bll of this checkbox's <code>ItemListener</code>s
     *         or bn empty brrby if no item
     *         listeners bre currently registered
     *
     * @see           #bddItemListener
     * @see           #removeItemListener
     * @see           jbvb.bwt.event.ItemEvent
     * @see           jbvb.bwt.event.ItemListener
     * @since 1.4
     */
    public synchronized ItemListener[] getItemListeners() {
        return getListeners(ItemListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>Checkbox</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>Checkbox</code> <code>c</code>
     * for its item listeners with the following code:
     *
     * <pre>ItemListener[] ils = (ItemListener[])(c.getListeners(ItemListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this checkbox,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getItemListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ItemListener.clbss) {
            l = itemListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == ItemEvent.ITEM_STATE_CHANGED) {
            if ((eventMbsk & AWTEvent.ITEM_EVENT_MASK) != 0 ||
                itemListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this check box.
     * If the event is bn instbnce of <code>ItemEvent</code>,
     * this method invokes the <code>processItemEvent</code> method.
     * Otherwise, it cblls its superclbss's <code>processEvent</code> method.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm         e the event
     * @see           jbvb.bwt.event.ItemEvent
     * @see           #processItemEvent
     * @since         1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ItemEvent) {
            processItemEvent((ItemEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes item events occurring on this check box by
     * dispbtching them to bny registered
     * <code>ItemListener</code> objects.
     * <p>
     * This method is not cblled unless item events bre
     * enbbled for this component. Item events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ItemListener</code> object is registered
     * vib <code>bddItemListener</code>.
     * <li>Item events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the item event
     * @see         jbvb.bwt.event.ItemEvent
     * @see         jbvb.bwt.event.ItemListener
     * @see         #bddItemListener
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processItemEvent(ItemEvent e) {
        ItemListener listener = itemListener;
        if (listener != null) {
            listener.itemStbteChbnged(e);
        }
    }

    /**
     * Returns b string representing the stbte of this <code>Checkbox</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return    the pbrbmeter string of this check box
     */
    protected String pbrbmString() {
        String str = super.pbrbmString();
        String lbbel = this.lbbel;
        if (lbbel != null) {
            str += ",lbbel=" + lbbel;
        }
        return str + ",stbte=" + stbte;
    }


    /* Seriblizbtion support.
     */

    /*
     * Seriblized dbtb version
     * @seribl
     */
    privbte int checkboxSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble <code>ItemListeners</code>
     * bs optionbl dbtb.  The non-seriblizbble
     * <code>ItemListeners</code> bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @pbrbm s the <code>ObjectOutputStrebm</code> to write
     * @seriblDbtb <code>null</code> terminbted sequence of 0
     *   or more pbirs; the pbir consists of b <code>String</code>
     *   bnd bn <code>Object</code>; the <code>String</code> indicbtes
     *   the type of object bnd is one of the following:
     *   <code>itemListenerK</code> indicbting bn
     *     <code>ItemListener</code> object
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#itemListenerK
     * @see #rebdObject(ObjectInputStrebm)
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws jbvb.io.IOException
    {
      s.defbultWriteObject();

      AWTEventMulticbster.sbve(s, itemListenerK, itemListener);
      s.writeObject(null);
    }

    /**
     * Rebds the <code>ObjectInputStrebm</code> bnd if it
     * isn't <code>null</code> bdds b listener to receive
     * item events fired by the <code>Checkbox</code>.
     * Unrecognized keys or vblues will be ignored.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @seribl
     * @see #removeItemListener(ItemListener)
     * @see #bddItemListener(ItemListener)
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #writeObject(ObjectOutputStrebm)
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
      GrbphicsEnvironment.checkHebdless();
      s.defbultRebdObject();

      Object keyOrNull;
      while(null != (keyOrNull = s.rebdObject())) {
        String key = ((String)keyOrNull).intern();

        if (itemListenerK == key)
          bddItemListener((ItemListener)(s.rebdObject()));

        else // skip vblue for unrecognized key
          s.rebdObject();
      }
    }

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this Checkbox.
     * For checkboxes, the AccessibleContext tbkes the form of bn
     * AccessibleAWTCheckbox.
     * A new AccessibleAWTCheckbox is crebted if necessbry.
     *
     * @return bn AccessibleAWTCheckbox thbt serves bs the
     *         AccessibleContext of this Checkbox
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTCheckbox();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Checkbox</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to checkbox user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTCheckbox extends AccessibleAWTComponent
        implements ItemListener, AccessibleAction, AccessibleVblue
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 7881579233144754107L;

        /**
         * Constructor for {@code AccessibleAWTCheckbox}
         */
        public AccessibleAWTCheckbox() {
            super();
            Checkbox.this.bddItemListener(this);
        }

        /**
         * Fire bccessible property chbnge events when the stbte of the
         * toggle button chbnges.
         */
        public void itemStbteChbnged(ItemEvent e) {
            Checkbox cb = (Checkbox) e.getSource();
            if (Checkbox.this.bccessibleContext != null) {
                if (cb.getStbte()) {
                    Checkbox.this.bccessibleContext.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            null, AccessibleStbte.CHECKED);
                } else {
                    Checkbox.this.bccessibleContext.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            AccessibleStbte.CHECKED, null);
                }
            }
        }

        /**
         * Get the AccessibleAction bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleAction interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Get the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleVblue interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Returns the number of Actions bvbilbble in this object.
         * If there is more thbn one, the first one is the "defbult"
         * bction.
         *
         * @return the number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 0;  //  To be fully implemented in b future relebse
        }

        /**
         * Return b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         */
        public String getAccessibleActionDescription(int i) {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the the bction wbs performed; else fblse.
         */
        public boolebn doAccessibleAction(int i) {
            return fblse;    //  To be fully implemented in b future relebse
        }

        /**
         * Get the vblue of this object bs b Number.  If the vblue hbs not been
         * set, the return vblue will be null.
         *
         * @return vblue of the object
         * @see #setCurrentAccessibleVblue
         */
        public Number getCurrentAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set; else Fblse
         * @see #getCurrentAccessibleVblue
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            return fblse;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return Minimum vblue of the object; null if this object does not
         * hbve b minimum vblue
         * @see #getMbximumAccessibleVblue
         */
        public Number getMinimumAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return Mbximum vblue of the object; null if this object does not
         * hbve b mbximum vblue
         * @see #getMinimumAccessibleVblue
         */
        public Number getMbximumAccessibleVblue() {
            return null;  //  To be fully implemented in b future relebse
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of
         * the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getStbte()) {
                stbtes.bdd(AccessibleStbte.CHECKED);
            }
            return stbtes;
        }


    } // inner clbss AccessibleAWTCheckbox

}
