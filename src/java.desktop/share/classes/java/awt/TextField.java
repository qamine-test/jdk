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

import jbvb.bwt.peer.TextFieldPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvbx.bccessibility.*;


/**
 * A <code>TextField</code> object is b text component
 * thbt bllows for the editing of b single line of text.
 * <p>
 * For exbmple, the following imbge depicts b frbme with four
 * text fields of vbrying widths. Two of these text fields
 * displby the predefined text <code>"Hello"</code>.
 * <p>
 * <img src="doc-files/TextField-1.gif" blt="The preceding text describes this imbge."
 * style="flobt:center; mbrgin: 7px 10px;">
 * <p>
 * Here is the code thbt produces these four text fields:
 *
 * <hr><blockquote><pre>
 * TextField tf1, tf2, tf3, tf4;
 * // b blbnk text field
 * tf1 = new TextField();
 * // blbnk field of 20 columns
 * tf2 = new TextField("", 20);
 * // predefined text displbyed
 * tf3 = new TextField("Hello!");
 * // predefined text in 30 columns
 * tf4 = new TextField("Hello", 30);
 * </pre></blockquote><hr>
 * <p>
 * Every time the user types b key in the text field, one or
 * more key events bre sent to the text field.  A <code>KeyEvent</code>
 * mby be one of three types: keyPressed, keyRelebsed, or keyTyped.
 * The properties of b key event indicbte which of these types
 * it is, bs well bs bdditionbl informbtion bbout the event,
 * such bs whbt modifiers bre bpplied to the key event bnd the
 * time bt which the event occurred.
 * <p>
 * The key event is pbssed to every <code>KeyListener</code>
 * or <code>KeyAdbpter</code> object which registered to receive such
 * events using the component's <code>bddKeyListener</code> method.
 * (<code>KeyAdbpter</code> objects implement the
 * <code>KeyListener</code> interfbce.)
 * <p>
 * It is blso possible to fire bn <code>ActionEvent</code>.
 * If bction events bre enbbled for the text field, they mby
 * be fired by pressing the <code>Return</code> key.
 * <p>
 * The <code>TextField</code> clbss's <code>processEvent</code>
 * method exbmines the bction event bnd pbsses it blong to
 * <code>processActionEvent</code>. The lbtter method redirects the
 * event to bny <code>ActionListener</code> objects thbt hbve
 * registered to receive bction events generbted by this
 * text field.
 *
 * @buthor      Sbmi Shbio
 * @see         jbvb.bwt.event.KeyEvent
 * @see         jbvb.bwt.event.KeyAdbpter
 * @see         jbvb.bwt.event.KeyListener
 * @see         jbvb.bwt.event.ActionEvent
 * @see         jbvb.bwt.Component#bddKeyListener
 * @see         jbvb.bwt.TextField#processEvent
 * @see         jbvb.bwt.TextField#processActionEvent
 * @see         jbvb.bwt.TextField#bddActionListener
 * @since       1.0
 */
public clbss TextField extends TextComponent {

    /**
     * The number of columns in the text field.
     * A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.
     * Gubrbnteed to be non-negbtive.
     *
     * @seribl
     * @see #setColumns(int)
     * @see #getColumns()
     */
    int columns;

    /**
     * The echo chbrbcter, which is used when
     * the user wishes to disguise the chbrbcters
     * typed into the text field.
     * The disguises bre removed if echoChbr = <code>0</code>.
     *
     * @seribl
     * @see #getEchoChbr()
     * @see #setEchoChbr(chbr)
     * @see #echoChbrIsSet()
     */
    chbr echoChbr;

    trbnsient ActionListener bctionListener;

    privbte stbtic finbl String bbse = "textfield";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -2966288784432217853L;

    /**
     * Initiblize JNI field bnd method ids
     */
    privbte stbtic nbtive void initIDs();

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * Constructs b new text field.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public TextField() throws HebdlessException {
        this("", 0);
    }

    /**
     * Constructs b new text field initiblized with the specified text.
     * @pbrbm      text       the text to be displbyed. If
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public TextField(String text) throws HebdlessException {
        this(text, (text != null) ? text.length() : 0);
    }

    /**
     * Constructs b new empty text field with the specified number
     * of columns.  A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.
     * @pbrbm      columns     the number of columns.  If
     *             <code>columns</code> is less thbn <code>0</code>,
     *             <code>columns</code> is set to <code>0</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public TextField(int columns) throws HebdlessException {
        this("", columns);
    }

    /**
     * Constructs b new text field initiblized with the specified text
     * to be displbyed, bnd wide enough to hold the specified
     * number of columns. A column is bn bpproximbte bverbge chbrbcter
     * width thbt is plbtform-dependent.
     * @pbrbm      text       the text to be displbyed. If
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed.
     * @pbrbm      columns     the number of columns.  If
     *             <code>columns</code> is less thbn <code>0</code>,
     *             <code>columns</code> is set to <code>0</code>.
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public TextField(String text, int columns) throws HebdlessException {
        super(text);
        this.columns = (columns >= 0) ? columns : 0;
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is null.
     */
    String constructComponentNbme() {
        synchronized (TextField.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Crebtes the TextField's peer.  The peer bllows us to modify the
     * bppebrbnce of the TextField without chbnging its functionblity.
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (peer == null)
                peer = getToolkit().crebteTextField(this);
            super.bddNotify();
        }
    }

    /**
     * Gets the chbrbcter thbt is to be used for echoing.
     * <p>
     * An echo chbrbcter is useful for text fields where
     * user input should not be echoed to the screen, bs in
     * the cbse of b text field for entering b pbssword.
     * If <code>echoChbr</code> = <code>0</code>, user
     * input is echoed to the screen unchbnged.
     * <p>
     * A Jbvb plbtform implementbtion mby support only b limited,
     * non-empty set of echo chbrbcters. This function returns the
     * echo chbrbcter originblly requested vib setEchoChbr(). The echo
     * chbrbcter bctublly used by the TextField implementbtion might be
     * different.
     * @return      the echo chbrbcter for this text field.
     * @see         jbvb.bwt.TextField#echoChbrIsSet
     * @see         jbvb.bwt.TextField#setEchoChbr
     */
    public chbr getEchoChbr() {
        return echoChbr;
    }

    /**
     * Sets the echo chbrbcter for this text field.
     * <p>
     * An echo chbrbcter is useful for text fields where
     * user input should not be echoed to the screen, bs in
     * the cbse of b text field for entering b pbssword.
     * Setting <code>echoChbr</code> = <code>0</code> bllows
     * user input to be echoed to the screen bgbin.
     * <p>
     * A Jbvb plbtform implementbtion mby support only b limited,
     * non-empty set of echo chbrbcters. Attempts to set bn
     * unsupported echo chbrbcter will cbuse the defbult echo
     * chbrbcter to be used instebd. Subsequent cblls to getEchoChbr()
     * will return the echo chbrbcter originblly requested. This might
     * or might not be identicbl to the echo chbrbcter bctublly
     * used by the TextField implementbtion.
     * @pbrbm       c   the echo chbrbcter for this text field.
     * @see         jbvb.bwt.TextField#echoChbrIsSet
     * @see         jbvb.bwt.TextField#getEchoChbr
     * @since       1.1
     */
    public void setEchoChbr(chbr c) {
        setEchoChbrbcter(c);
    }

    /**
     * Sets the chbrbcter to be echoed when protected input is displbyed.
     *
     *  @pbrbm  c the echo chbrbcter for this text field
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>setEchoChbr(chbr)</code>.
     */
    @Deprecbted
    public synchronized void setEchoChbrbcter(chbr c) {
        if (echoChbr != c) {
            echoChbr = c;
            TextFieldPeer peer = (TextFieldPeer)this.peer;
            if (peer != null) {
                peer.setEchoChbr(c);
            }
        }
    }

    /**
     * Sets the text thbt is presented by this
     * text component to be the specified text.
     * @pbrbm       t   the new text.
     * @see         jbvb.bwt.TextComponent#getText
     */
    public void setText(String t) {
        super.setText(t);

        // This could chbnge the preferred size of the Component.
        invblidbteIfVblid();
    }

    /**
     * Indicbtes whether or not this text field hbs b
     * chbrbcter set for echoing.
     * <p>
     * An echo chbrbcter is useful for text fields where
     * user input should not be echoed to the screen, bs in
     * the cbse of b text field for entering b pbssword.
     * @return     <code>true</code> if this text field hbs
     *                 b chbrbcter set for echoing;
     *                 <code>fblse</code> otherwise.
     * @see        jbvb.bwt.TextField#setEchoChbr
     * @see        jbvb.bwt.TextField#getEchoChbr
     */
    public boolebn echoChbrIsSet() {
        return echoChbr != 0;
    }

    /**
     * Gets the number of columns in this text field. A column is bn
     * bpproximbte bverbge chbrbcter width thbt is plbtform-dependent.
     * @return     the number of columns.
     * @see        jbvb.bwt.TextField#setColumns
     * @since      1.1
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of columns in this text field. A column is bn
     * bpproximbte bverbge chbrbcter width thbt is plbtform-dependent.
     * @pbrbm      columns   the number of columns.
     * @see        jbvb.bwt.TextField#getColumns
     * @exception  IllegblArgumentException   if the vblue
     *                 supplied for <code>columns</code>
     *                 is less thbn <code>0</code>.
     * @since      1.1
     */
    public void setColumns(int columns) {
        int oldVbl;
        synchronized (this) {
            oldVbl = this.columns;
            if (columns < 0) {
                throw new IllegblArgumentException("columns less thbn zero.");
            }
            if (columns != oldVbl) {
                this.columns = columns;
            }
        }

        if (columns != oldVbl) {
            invblidbte();
        }
    }

    /**
     * Gets the preferred size of this text field
     * with the specified number of columns.
     * @pbrbm     columns the number of columns
     *                 in this text field.
     * @return    the preferred dimensions for
     *                 displbying this text field.
     * @since     1.1
     */
    public Dimension getPreferredSize(int columns) {
        return preferredSize(columns);
    }

    /**
     * Returns the preferred size for this text field
     * with the specified number of columns.
     *
     * @pbrbm  columns the number of columns
     * @return the preferred size for the text field
     *
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize(int)</code>.
     */
    @Deprecbted
    public Dimension preferredSize(int columns) {
        synchronized (getTreeLock()) {
            TextFieldPeer peer = (TextFieldPeer)this.peer;
            return (peer != null) ?
                       peer.getPreferredSize(columns) :
                       super.preferredSize();
        }
    }

    /**
     * Gets the preferred size of this text field.
     * @return     the preferred dimensions for
     *                         displbying this text field.
     * @since      1.1
     */
    public Dimension getPreferredSize() {
        return preferredSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getPreferredSize()</code>.
     */
    @Deprecbted
    public Dimension preferredSize() {
        synchronized (getTreeLock()) {
            return (columns > 0) ?
                       preferredSize(columns) :
                       super.preferredSize();
        }
    }

    /**
     * Gets the minimum dimensions for b text field with
     * the specified number of columns.
     * @pbrbm  columns the number of columns in
     *         this text field.
     * @return the minimum size for this text field
     * @since    1.1
     */
    public Dimension getMinimumSize(int columns) {
        return minimumSize(columns);
    }

    /**
     * Returns the minimum dimensions for b text field with
     * the specified number of columns.
     *
     * @pbrbm  columns the number of columns
     * @return the minimum size for this text field
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize(int)</code>.
     */
    @Deprecbted
    public Dimension minimumSize(int columns) {
        synchronized (getTreeLock()) {
            TextFieldPeer peer = (TextFieldPeer)this.peer;
            return (peer != null) ?
                       peer.getMinimumSize(columns) :
                       super.minimumSize();
        }
    }

    /**
     * Gets the minimum dimensions for this text field.
     * @return     the minimum dimensions for
     *                  displbying this text field.
     * @since      1.1
     */
    public Dimension getMinimumSize() {
        return minimumSize();
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * replbced by <code>getMinimumSize()</code>.
     */
    @Deprecbted
    public Dimension minimumSize() {
        synchronized (getTreeLock()) {
            return (columns > 0) ?
                       minimumSize(columns) :
                       super.minimumSize();
        }
    }

    /**
     * Adds the specified bction listener to receive
     * bction events from this text field.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm      l the bction listener.
     * @see        #removeActionListener
     * @see        #getActionListeners
     * @see        jbvb.bwt.event.ActionListener
     * @since      1.1
     */
    public synchronized void bddActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.bdd(bctionListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified bction listener so thbt it no longer
     * receives bction events from this text field.
     * If l is null, no exception is thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm           l the bction listener.
     * @see             #bddActionListener
     * @see             #getActionListeners
     * @see             jbvb.bwt.event.ActionListener
     * @since           1.1
     */
    public synchronized void removeActionListener(ActionListener l) {
        if (l == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.remove(bctionListener, l);
    }

    /**
     * Returns bn brrby of bll the bction listeners
     * registered on this textfield.
     *
     * @return bll of this textfield's <code>ActionListener</code>s
     *         or bn empty brrby if no bction
     *         listeners bre currently registered
     *
     * @see #bddActionListener
     * @see #removeActionListener
     * @see jbvb.bwt.event.ActionListener
     * @since 1.4
     */
    public synchronized ActionListener[] getActionListeners() {
        return getListeners(ActionListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>TextField</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>TextField</code> <code>t</code>
     * for its bction listeners with the following code:
     *
     * <pre>ActionListener[] bls = (ActionListener[])(t.getListeners(ActionListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this textfield,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getActionListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == ActionListener.clbss) {
            l = bctionListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == ActionEvent.ACTION_PERFORMED) {
            if ((eventMbsk & AWTEvent.ACTION_EVENT_MASK) != 0 ||
                bctionListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this text field. If the event
     * is bn instbnce of <code>ActionEvent</code>,
     * it invokes the <code>processActionEvent</code>
     * method. Otherwise, it invokes <code>processEvent</code>
     * on the superclbss.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm      e the event
     * @see        jbvb.bwt.event.ActionEvent
     * @see        jbvb.bwt.TextField#processActionEvent
     * @since      1.1
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof ActionEvent) {
            processActionEvent((ActionEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes bction events occurring on this text field by
     * dispbtching them to bny registered
     * <code>ActionListener</code> objects.
     * <p>
     * This method is not cblled unless bction events bre
     * enbbled for this component. Action events bre enbbled
     * when one of the following occurs:
     * <ul>
     * <li>An <code>ActionListener</code> object is registered
     * vib <code>bddActionListener</code>.
     * <li>Action events bre enbbled vib <code>enbbleEvents</code>.
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm       e the bction event
     * @see         jbvb.bwt.event.ActionListener
     * @see         jbvb.bwt.TextField#bddActionListener
     * @see         jbvb.bwt.Component#enbbleEvents
     * @since       1.1
     */
    protected void processActionEvent(ActionEvent e) {
        ActionListener listener = bctionListener;
        if (listener != null) {
            listener.bctionPerformed(e);
        }
    }

    /**
     * Returns b string representing the stbte of this <code>TextField</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return      the pbrbmeter string of this text field
     */
    protected String pbrbmString() {
        String str = super.pbrbmString();
        if (echoChbr != 0) {
            str += ",echo=" + echoChbr;
        }
        return str;
    }


    /*
     * Seriblizbtion support.
     */
    /**
     * The textField Seriblized Dbtb Version.
     *
     * @seribl
     */
    privbte int textFieldSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble ActionListener(s) bs optionbl dbtb.
     * The non-seriblizbble ActionListener(s) bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @seriblDbtb Null terminbted sequence of zero or more pbirs.
     *             A pbir consists of b String bnd Object.
     *             The String indicbtes the type of object bnd
     *             is one of the following :
     *             ActionListenerK indicbting bnd ActionListener object.
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#bctionListenerK
     */
    privbte void writeObject(ObjectOutputStrebm s)
      throws IOException
    {
        s.defbultWriteObject();

        AWTEventMulticbster.sbve(s, bctionListenerK, bctionListener);
        s.writeObject(null);
    }

    /**
     * Rebd the ObjectInputStrebm bnd if it isn't null,
     * bdd b listener to receive bction events fired by the
     * TextField.  Unrecognized keys or vblues will be
     * ignored.
     *
     * @exception HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns
     * <code>true</code>
     * @see #removeActionListener(ActionListener)
     * @see #bddActionListener(ActionListener)
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException, HebdlessException
    {
        // HebdlessException will be thrown by TextComponent's rebdObject
        s.defbultRebdObject();

        // Mbke sure the stbte we just rebd in for columns hbs legbl vblues
        if (columns < 0) {
            columns = 0;
        }

        // Rebd in listeners, if bny
        Object keyOrNull;
        while(null != (keyOrNull = s.rebdObject())) {
            String key = ((String)keyOrNull).intern();

            if (bctionListenerK == key) {
                bddActionListener((ActionListener)(s.rebdObject()));
            } else {
                // skip vblue for unrecognized key
                s.rebdObject();
            }
        }
    }


/////////////////
// Accessibility support
////////////////


    /**
     * Gets the AccessibleContext bssocibted with this TextField.
     * For text fields, the AccessibleContext tbkes the form of bn
     * AccessibleAWTTextField.
     * A new AccessibleAWTTextField instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTTextField thbt serves bs the
     *         AccessibleContext of this TextField
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTTextField();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>TextField</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to text field user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTTextField extends AccessibleAWTTextComponent
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 6219164359235943158L;

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbteSet describing the stbtes
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            stbtes.bdd(AccessibleStbte.SINGLE_LINE);
            return stbtes;
        }
    }

}
