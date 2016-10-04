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

import jbvb.bwt.peer.TextComponentPeer;
import jbvb.bwt.event.*;
import jbvb.util.EventListener;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.text.BrebkIterbtor;
import jbvbx.swing.text.AttributeSet;
import jbvbx.bccessibility.*;
import jbvb.bwt.im.InputMethodRequests;
import sun.bwt.AWTPermissions;
import sun.bwt.InputMethodSupport;

/**
 * The <code>TextComponent</code> clbss is the superclbss of
 * bny component thbt bllows the editing of some text.
 * <p>
 * A text component embodies b string of text.  The
 * <code>TextComponent</code> clbss defines b set of methods
 * thbt determine whether or not this text is editbble. If the
 * component is editbble, it defines bnother set of methods
 * thbt supports b text insertion cbret.
 * <p>
 * In bddition, the clbss defines methods thbt bre used
 * to mbintbin b current <em>selection</em> from the text.
 * The text selection, b substring of the component's text,
 * is the tbrget of editing operbtions. It is blso referred
 * to bs the <em>selected text</em>.
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public clbss TextComponent extends Component implements Accessible {

    /**
     * The vblue of the text.
     * A <code>null</code> vblue is the sbme bs "".
     *
     * @seribl
     * @see #setText(String)
     * @see #getText()
     */
    String text;

    /**
     * A boolebn indicbting whether or not this
     * <code>TextComponent</code> is editbble.
     * It will be <code>true</code> if the text component
     * is editbble bnd <code>fblse</code> if not.
     *
     * @seribl
     * @see #isEditbble()
     */
    boolebn editbble = true;

    /**
     * The selection refers to the selected text, bnd the
     * <code>selectionStbrt</code> is the stbrt position
     * of the selected text.
     *
     * @seribl
     * @see #getSelectionStbrt()
     * @see #setSelectionStbrt(int)
     */
    int selectionStbrt;

    /**
     * The selection refers to the selected text, bnd the
     * <code>selectionEnd</code>
     * is the end position of the selected text.
     *
     * @seribl
     * @see #getSelectionEnd()
     * @see #setSelectionEnd(int)
     */
    int selectionEnd;

    // A flbg used to tell whether the bbckground hbs been set by
    // developer code (bs opposed to AWT code).  Used to determine
    // the bbckground color of non-editbble TextComponents.
    boolebn bbckgroundSetByClientCode = fblse;

    /**
     * A list of listeners thbt will receive events from this object.
     */
    trbnsient protected TextListener textListener;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -2214773872412987419L;

    /**
     * Constructs b new text component initiblized with the
     * specified text. Sets the vblue of the cursor to
     * <code>Cursor.TEXT_CURSOR</code>.
     * @pbrbm      text       the text to be displbyed; if
     *             <code>text</code> is <code>null</code>, the empty
     *             string <code>""</code> will be displbyed
     * @exception  HebdlessException if
     *             <code>GrbphicsEnvironment.isHebdless</code>
     *             returns true
     * @see        jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see        jbvb.bwt.Cursor
     */
    TextComponent(String text) throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        this.text = (text != null) ? text : "";
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    }

    privbte void enbbleInputMethodsIfNecessbry() {
        if (checkForEnbbleIM) {
            checkForEnbbleIM = fblse;
            try {
                Toolkit toolkit = Toolkit.getDefbultToolkit();
                boolebn shouldEnbble = fblse;
                if (toolkit instbnceof InputMethodSupport) {
                    shouldEnbble = ((InputMethodSupport)toolkit)
                      .enbbleInputMethodsForTextComponent();
                }
                enbbleInputMethods(shouldEnbble);
            } cbtch (Exception e) {
                // if something bbd hbppens, just don't enbble input methods
            }
        }
    }

    /**
     * Enbbles or disbbles input method support for this text component. If input
     * method support is enbbled bnd the text component blso processes key events,
     * incoming events bre offered to the current input method bnd will only be
     * processed by the component or dispbtched to its listeners if the input method
     * does not consume them. Whether bnd how input method support for this text
     * component is enbbled or disbbled by defbult is implementbtion dependent.
     *
     * @pbrbm enbble true to enbble, fblse to disbble
     * @see #processKeyEvent
     * @since 1.2
     */
    public void enbbleInputMethods(boolebn enbble) {
        checkForEnbbleIM = fblse;
        super.enbbleInputMethods(enbble);
    }

    boolebn breInputMethodsEnbbled() {
        // moved from the constructor bbove to here bnd bddNotify below,
        // this cbll will initiblize the toolkit if not blrebdy initiblized.
        if (checkForEnbbleIM) {
            enbbleInputMethodsIfNecessbry();
        }

        // TextComponent hbndles key events without touching the eventMbsk or
        // hbving b key listener, so just check whether the flbg is set
        return (eventMbsk & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0;
    }

    public InputMethodRequests getInputMethodRequests() {
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) return peer.getInputMethodRequests();
        else return null;
    }



    /**
     * Mbkes this Component displbybble by connecting it to b
     * nbtive screen resource.
     * This method is cblled internblly by the toolkit bnd should
     * not be cblled directly by progrbms.
     * @see       jbvb.bwt.TextComponent#removeNotify
     */
    public void bddNotify() {
        super.bddNotify();
        enbbleInputMethodsIfNecessbry();
    }

    /**
     * Removes the <code>TextComponent</code>'s peer.
     * The peer bllows us to modify the bppebrbnce of the
     * <code>TextComponent</code> without chbnging its
     * functionblity.
     */
    public void removeNotify() {
        synchronized (getTreeLock()) {
            TextComponentPeer peer = (TextComponentPeer)this.peer;
            if (peer != null) {
                text = peer.getText();
                selectionStbrt = peer.getSelectionStbrt();
                selectionEnd = peer.getSelectionEnd();
            }
            super.removeNotify();
        }
    }

    /**
     * Sets the text thbt is presented by this
     * text component to be the specified text.
     * @pbrbm       t   the new text;
     *                  if this pbrbmeter is <code>null</code> then
     *                  the text is set to the empty string ""
     * @see         jbvb.bwt.TextComponent#getText
     */
    public synchronized void setText(String t) {
        boolebn skipTextEvent = (text == null || text.isEmpty())
                && (t == null || t.isEmpty());
        text = (t != null) ? t : "";
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        // Plebse note thbt we do not wbnt to post bn event
        // if TextAreb.setText() or TextField.setText() replbces bn empty text
        // by bn empty text, thbt is, if component's text rembins unchbnged.
        if (peer != null && !skipTextEvent) {
            peer.setText(text);
        }
    }

    /**
     * Returns the text thbt is presented by this text component.
     * By defbult, this is bn empty string.
     *
     * @return the vblue of this <code>TextComponent</code>
     * @see     jbvb.bwt.TextComponent#setText
     */
    public synchronized String getText() {
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            text = peer.getText();
        }
        return text;
    }

    /**
     * Returns the selected text from the text thbt is
     * presented by this text component.
     * @return      the selected text of this text component
     * @see         jbvb.bwt.TextComponent#select
     */
    public synchronized String getSelectedText() {
        return getText().substring(getSelectionStbrt(), getSelectionEnd());
    }

    /**
     * Indicbtes whether or not this text component is editbble.
     * @return     <code>true</code> if this text component is
     *                  editbble; <code>fblse</code> otherwise.
     * @see        jbvb.bwt.TextComponent#setEditbble
     * @since      1.0
     */
    public boolebn isEditbble() {
        return editbble;
    }

    /**
     * Sets the flbg thbt determines whether or not this
     * text component is editbble.
     * <p>
     * If the flbg is set to <code>true</code>, this text component
     * becomes user editbble. If the flbg is set to <code>fblse</code>,
     * the user cbnnot chbnge the text of this text component.
     * By defbult, non-editbble text components hbve b bbckground color
     * of SystemColor.control.  This defbult cbn be overridden by
     * cblling setBbckground.
     *
     * @pbrbm     b   b flbg indicbting whether this text component
     *                      is user editbble.
     * @see       jbvb.bwt.TextComponent#isEditbble
     * @since     1.0
     */
    public synchronized void setEditbble(boolebn b) {
        if (editbble == b) {
            return;
        }

        editbble = b;
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            peer.setEditbble(b);
        }
    }

    /**
     * Gets the bbckground color of this text component.
     *
     * By defbult, non-editbble text components hbve b bbckground color
     * of SystemColor.control.  This defbult cbn be overridden by
     * cblling setBbckground.
     *
     * @return This text component's bbckground color.
     *         If this text component does not hbve b bbckground color,
     *         the bbckground color of its pbrent is returned.
     * @see #setBbckground(Color)
     * @since 1.0
     */
    public Color getBbckground() {
        if (!editbble && !bbckgroundSetByClientCode) {
            return SystemColor.control;
        }

        return super.getBbckground();
    }

    /**
     * Sets the bbckground color of this text component.
     *
     * @pbrbm c The color to become this text component's color.
     *        If this pbrbmeter is null then this text component
     *        will inherit the bbckground color of its pbrent.
     * @see #getBbckground()
     * @since 1.0
     */
    public void setBbckground(Color c) {
        bbckgroundSetByClientCode = true;
        super.setBbckground(c);
    }

    /**
     * Gets the stbrt position of the selected text in
     * this text component.
     * @return      the stbrt position of the selected text
     * @see         jbvb.bwt.TextComponent#setSelectionStbrt
     * @see         jbvb.bwt.TextComponent#getSelectionEnd
     */
    public synchronized int getSelectionStbrt() {
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            selectionStbrt = peer.getSelectionStbrt();
        }
        return selectionStbrt;
    }

    /**
     * Sets the selection stbrt for this text component to
     * the specified position. The new stbrt point is constrbined
     * to be bt or before the current selection end. It blso
     * cbnnot be set to less thbn zero, the beginning of the
     * component's text.
     * If the cbller supplies b vblue for <code>selectionStbrt</code>
     * thbt is out of bounds, the method enforces these constrbints
     * silently, bnd without fbilure.
     * @pbrbm       selectionStbrt   the stbrt position of the
     *                        selected text
     * @see         jbvb.bwt.TextComponent#getSelectionStbrt
     * @see         jbvb.bwt.TextComponent#setSelectionEnd
     * @since       1.1
     */
    public synchronized void setSelectionStbrt(int selectionStbrt) {
        /* Route through select method to enforce consistent policy
         * between selectionStbrt bnd selectionEnd.
         */
        select(selectionStbrt, getSelectionEnd());
    }

    /**
     * Gets the end position of the selected text in
     * this text component.
     * @return      the end position of the selected text
     * @see         jbvb.bwt.TextComponent#setSelectionEnd
     * @see         jbvb.bwt.TextComponent#getSelectionStbrt
     */
    public synchronized int getSelectionEnd() {
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            selectionEnd = peer.getSelectionEnd();
        }
        return selectionEnd;
    }

    /**
     * Sets the selection end for this text component to
     * the specified position. The new end point is constrbined
     * to be bt or bfter the current selection stbrt. It blso
     * cbnnot be set beyond the end of the component's text.
     * If the cbller supplies b vblue for <code>selectionEnd</code>
     * thbt is out of bounds, the method enforces these constrbints
     * silently, bnd without fbilure.
     * @pbrbm       selectionEnd   the end position of the
     *                        selected text
     * @see         jbvb.bwt.TextComponent#getSelectionEnd
     * @see         jbvb.bwt.TextComponent#setSelectionStbrt
     * @since       1.1
     */
    public synchronized void setSelectionEnd(int selectionEnd) {
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
     * equbl to the length of the text component's text.  The
     * chbrbcter positions bre indexed stbrting with zero.
     * The length of the selection is
     * <code>endPosition</code> - <code>stbrtPosition</code>, so the
     * chbrbcter bt <code>endPosition</code> is not selected.
     * If the stbrt bnd end positions of the selected text bre equbl,
     * bll text is deselected.
     * <p>
     * If the cbller supplies vblues thbt bre inconsistent or out of
     * bounds, the method enforces these constrbints silently, bnd
     * without fbilure. Specificblly, if the stbrt position or end
     * position is grebter thbn the length of the text, it is reset to
     * equbl the text length. If the stbrt position is less thbn zero,
     * it is reset to zero, bnd if the end position is less thbn the
     * stbrt position, it is reset to the stbrt position.
     *
     * @pbrbm        selectionStbrt the zero-bbsed index of the first
                       chbrbcter (<code>chbr</code> vblue) to be selected
     * @pbrbm        selectionEnd the zero-bbsed end position of the
                       text to be selected; the chbrbcter (<code>chbr</code> vblue) bt
                       <code>selectionEnd</code> is not selected
     * @see          jbvb.bwt.TextComponent#setSelectionStbrt
     * @see          jbvb.bwt.TextComponent#setSelectionEnd
     * @see          jbvb.bwt.TextComponent#selectAll
     */
    public synchronized void select(int selectionStbrt, int selectionEnd) {
        String text = getText();
        if (selectionStbrt < 0) {
            selectionStbrt = 0;
        }
        if (selectionStbrt > text.length()) {
            selectionStbrt = text.length();
        }
        if (selectionEnd > text.length()) {
            selectionEnd = text.length();
        }
        if (selectionEnd < selectionStbrt) {
            selectionEnd = selectionStbrt;
        }

        this.selectionStbrt = selectionStbrt;
        this.selectionEnd = selectionEnd;

        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            peer.select(selectionStbrt, selectionEnd);
        }
    }

    /**
     * Selects bll the text in this text component.
     * @see        jbvb.bwt.TextComponent#select
     */
    public synchronized void selectAll() {
        this.selectionStbrt = 0;
        this.selectionEnd = getText().length();

        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            peer.select(selectionStbrt, selectionEnd);
        }
    }

    /**
     * Sets the position of the text insertion cbret.
     * The cbret position is constrbined to be between 0
     * bnd the lbst chbrbcter of the text, inclusive.
     * If the pbssed-in vblue is grebter thbn this rbnge,
     * the vblue is set to the lbst chbrbcter (or 0 if
     * the <code>TextComponent</code> contbins no text)
     * bnd no error is returned.  If the pbssed-in vblue is
     * less thbn 0, bn <code>IllegblArgumentException</code>
     * is thrown.
     *
     * @pbrbm        position the position of the text insertion cbret
     * @exception    IllegblArgumentException if <code>position</code>
     *               is less thbn zero
     * @since        1.1
     */
    public synchronized void setCbretPosition(int position) {
        if (position < 0) {
            throw new IllegblArgumentException("position less thbn zero.");
        }

        int mbxposition = getText().length();
        if (position > mbxposition) {
            position = mbxposition;
        }

        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            peer.setCbretPosition(position);
        } else {
            select(position, position);
        }
    }

    /**
     * Returns the position of the text insertion cbret.
     * The cbret position is constrbined to be between 0
     * bnd the lbst chbrbcter of the text, inclusive.
     * If the text or cbret hbve not been set, the defbult
     * cbret position is 0.
     *
     * @return       the position of the text insertion cbret
     * @see #setCbretPosition(int)
     * @since        1.1
     */
    public synchronized int getCbretPosition() {
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        int position = 0;

        if (peer != null) {
            position = peer.getCbretPosition();
        } else {
            position = selectionStbrt;
        }
        int mbxposition = getText().length();
        if (position > mbxposition) {
            position = mbxposition;
        }
        return position;
    }

    /**
     * Adds the specified text event listener to receive text events
     * from this text component.
     * If <code>l</code> is <code>null</code>, no exception is
     * thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm l the text event listener
     * @see             #removeTextListener
     * @see             #getTextListeners
     * @see             jbvb.bwt.event.TextListener
     */
    public synchronized void bddTextListener(TextListener l) {
        if (l == null) {
            return;
        }
        textListener = AWTEventMulticbster.bdd(textListener, l);
        newEventsOnly = true;
    }

    /**
     * Removes the specified text event listener so thbt it no longer
     * receives text events from this text component
     * If <code>l</code> is <code>null</code>, no exception is
     * thrown bnd no bction is performed.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm           l     the text listener
     * @see             #bddTextListener
     * @see             #getTextListeners
     * @see             jbvb.bwt.event.TextListener
     * @since           1.1
     */
    public synchronized void removeTextListener(TextListener l) {
        if (l == null) {
            return;
        }
        textListener = AWTEventMulticbster.remove(textListener, l);
    }

    /**
     * Returns bn brrby of bll the text listeners
     * registered on this text component.
     *
     * @return bll of this text component's <code>TextListener</code>s
     *         or bn empty brrby if no text
     *         listeners bre currently registered
     *
     *
     * @see #bddTextListener
     * @see #removeTextListener
     * @since 1.4
     */
    public synchronized TextListener[] getTextListeners() {
        return getListeners(TextListener.clbss);
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this <code>TextComponent</code>.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>TextComponent</code> <code>t</code>
     * for its text listeners with the following code:
     *
     * <pre>TextListener[] tls = (TextListener[])(t.getListeners(TextListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this text component,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getTextListeners
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        EventListener l = null;
        if  (listenerType == TextListener.clbss) {
            l = textListener;
        } else {
            return super.getListeners(listenerType);
        }
        return AWTEventMulticbster.getListeners(l, listenerType);
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        if (e.id == TextEvent.TEXT_VALUE_CHANGED) {
            if ((eventMbsk & AWTEvent.TEXT_EVENT_MASK) != 0 ||
                textListener != null) {
                return true;
            }
            return fblse;
        }
        return super.eventEnbbled(e);
    }

    /**
     * Processes events on this text component. If the event is b
     * <code>TextEvent</code>, it invokes the <code>processTextEvent</code>
     * method else it invokes its superclbss's <code>processEvent</code>.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the event
     */
    protected void processEvent(AWTEvent e) {
        if (e instbnceof TextEvent) {
            processTextEvent((TextEvent)e);
            return;
        }
        super.processEvent(e);
    }

    /**
     * Processes text events occurring on this text component by
     * dispbtching them to bny registered <code>TextListener</code> objects.
     * <p>
     * NOTE: This method will not be cblled unless text events
     * bre enbbled for this component. This hbppens when one of the
     * following occurs:
     * <ul>
     * <li>A <code>TextListener</code> object is registered
     * vib <code>bddTextListener</code>
     * <li>Text events bre enbbled vib <code>enbbleEvents</code>
     * </ul>
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the text event
     * @see Component#enbbleEvents
     */
    protected void processTextEvent(TextEvent e) {
        TextListener listener = textListener;
        if (listener != null) {
            int id = e.getID();
            switch (id) {
            cbse TextEvent.TEXT_VALUE_CHANGED:
                listener.textVblueChbnged(e);
                brebk;
            }
        }
    }

    /**
     * Returns b string representing the stbte of this
     * <code>TextComponent</code>. This
     * method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return      the pbrbmeter string of this text component
     */
    protected String pbrbmString() {
        String str = super.pbrbmString() + ",text=" + getText();
        if (editbble) {
            str += ",editbble";
        }
        return str + ",selection=" + getSelectionStbrt() + "-" + getSelectionEnd();
    }

    /**
     * Assigns b vblid vblue to the cbnAccessClipbobrd instbnce vbribble.
     */
    privbte boolebn cbnAccessClipbobrd() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null) return true;
        try {
            sm.checkPermission(AWTPermissions.ACCESS_CLIPBOARD_PERMISSION);
            return true;
        } cbtch (SecurityException e) {}
        return fblse;
    }

    /*
     * Seriblizbtion support.
     */
    /**
     * The textComponent SeriblizedDbtbVersion.
     *
     * @seribl
     */
    privbte int textComponentSeriblizedDbtbVersion = 1;

    /**
     * Writes defbult seriblizbble fields to strebm.  Writes
     * b list of seriblizbble TextListener(s) bs optionbl dbtb.
     * The non-seriblizbble TextListener(s) bre detected bnd
     * no bttempt is mbde to seriblize them.
     *
     * @seriblDbtb Null terminbted sequence of zero or more pbirs.
     *             A pbir consists of b String bnd Object.
     *             The String indicbtes the type of object bnd
     *             is one of the following :
     *             textListenerK indicbting bnd TextListener object.
     *
     * @see AWTEventMulticbster#sbve(ObjectOutputStrebm, String, EventListener)
     * @see jbvb.bwt.Component#textListenerK
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
      throws IOException
    {
        // Seriblizbtion support.  Since the vblue of the fields
        // selectionStbrt, selectionEnd, bnd text bren't necessbrily
        // up to dbte, we sync them up with the peer before seriblizing.
        TextComponentPeer peer = (TextComponentPeer)this.peer;
        if (peer != null) {
            text = peer.getText();
            selectionStbrt = peer.getSelectionStbrt();
            selectionEnd = peer.getSelectionEnd();
        }

        s.defbultWriteObject();

        AWTEventMulticbster.sbve(s, textListenerK, textListener);
        s.writeObject(null);
    }

    /**
     * Rebd the ObjectInputStrebm, bnd if it isn't null,
     * bdd b listener to receive text events fired by the
     * TextComponent.  Unrecognized keys or vblues will be
     * ignored.
     *
     * @exception HebdlessException if
     * <code>GrbphicsEnvironment.isHebdless()</code> returns
     * <code>true</code>
     * @see #removeTextListener
     * @see #bddTextListener
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException
    {
        GrbphicsEnvironment.checkHebdless();
        s.defbultRebdObject();

        // Mbke sure the stbte we just rebd in for text,
        // selectionStbrt bnd selectionEnd hbs legbl vblues
        this.text = (text != null) ? text : "";
        select(selectionStbrt, selectionEnd);

        Object keyOrNull;
        while(null != (keyOrNull = s.rebdObject())) {
            String key = ((String)keyOrNull).intern();

            if (textListenerK == key) {
                bddTextListener((TextListener)(s.rebdObject()));
            } else {
                // skip vblue for unrecognized key
                s.rebdObject();
            }
        }
        enbbleInputMethodsIfNecessbry();
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this TextComponent.
     * For text components, the AccessibleContext tbkes the form of bn
     * AccessibleAWTTextComponent.
     * A new AccessibleAWTTextComponent instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTTextComponent thbt serves bs the
     *         AccessibleContext of this TextComponent
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTTextComponent();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>TextComponent</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to text component user-interfbce
     * elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTTextComponent extends AccessibleAWTComponent
        implements AccessibleText, TextListener
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 3631432373506317811L;

        /**
         * Constructs bn AccessibleAWTTextComponent.  Adds b listener to trbck
         * cbret chbnge.
         */
        public AccessibleAWTTextComponent() {
            TextComponent.this.bddTextListener(this);
        }

        /**
         * TextListener notificbtion of b text vblue chbnge.
         */
        public void textVblueChbnged(TextEvent textEvent)  {
            Integer cpos = Integer.vblueOf(TextComponent.this.getCbretPosition());
            firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY, null, cpos);
        }

        /**
         * Gets the stbte set of the TextComponent.
         * The AccessibleStbteSet of bn object is composed of b set of
         * unique AccessibleStbtes.  A chbnge in the AccessibleStbteSet
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
            if (TextComponent.this.isEditbble()) {
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
            return -1;
        }

        /**
         * Determines the bounding box of the chbrbcter bt the given
         * index into the string.  The bounds bre returned in locbl
         * coordinbtes.  If the index is invblid b null rectbngle
         * is returned.
         *
         * @pbrbm i the index into the String &gt;= 0
         * @return the screen coordinbtes of the chbrbcter's bounding box
         */
        public Rectbngle getChbrbcterBounds(int i) {
            return null;
        }

        /**
         * Returns the number of chbrbcters (vblid indicies)
         *
         * @return the number of chbrbcters &gt;= 0
         */
        public int getChbrCount() {
            return TextComponent.this.getText().length();
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
            return TextComponent.this.getCbretPosition();
        }

        /**
         * Returns the AttributeSet for b given chbrbcter (bt b given index).
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            return null; // No bttributes in TextComponent
        }

        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         * Return 0 if the text is empty, or the cbret position
         * if no selection.
         *
         * @return the index into the text of the stbrt of the selection &gt;= 0
         */
        public int getSelectionStbrt() {
            return TextComponent.this.getSelectionStbrt();
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         * Return 0 if the text is empty, or the cbret position
         * if no selection.
         *
         * @return the index into the text of the end of the selection &gt;= 0
         */
        public int getSelectionEnd() {
            return TextComponent.this.getSelectionEnd();
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the text, null if no selection
         */
        public String getSelectedText() {
            String selText = TextComponent.this.getSelectedText();
            // Fix for 4256662
            if (selText == null || selText.equbls("")) {
                return null;
            }
            return selText;
        }

        /**
         * Returns the String bt b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence,
         *   null for bn invblid index or pbrt
         */
        public String getAtIndex(int pbrt, int index) {
            if (index < 0 || index >= TextComponent.this.getText().length()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                return TextComponent.this.getText().substring(index, index+1);
            cbse AccessibleText.WORD:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce();
                    words.setText(s);
                    int end = words.following(index);
                    return s.substring(words.previous(), end);
                }
            cbse AccessibleText.SENTENCE:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor sentence = BrebkIterbtor.getSentenceInstbnce();
                    sentence.setText(s);
                    int end = sentence.following(index);
                    return s.substring(sentence.previous(), end);
                }
            defbult:
                return null;
            }
        }

        privbte stbtic finbl boolebn NEXT = true;
        privbte stbtic finbl boolebn PREVIOUS = fblse;

        /**
         * Needed to unify forwbrd bnd bbckwbrd sebrching.
         * The method bssumes thbt s is the text bssigned to words.
         */
        privbte int findWordLimit(int index, BrebkIterbtor words, boolebn direction,
                                         String s) {
            // Fix for 4256660 bnd 4256661.
            // Words iterbtor is different from chbrbcter bnd sentence iterbtors
            // in thbt end of one word is not necessbrily stbrt of bnother word.
            // Plebse see jbvb.text.BrebkIterbtor JbvbDoc. The code below is
            // bbsed on nextWordStbrtAfter exbmple from BrebkIterbtor.jbvb.
            int lbst = (direction == NEXT) ? words.following(index)
                                           : words.preceding(index);
            int current = (direction == NEXT) ? words.next()
                                              : words.previous();
            while (current != BrebkIterbtor.DONE) {
                for (int p = Mbth.min(lbst, current); p < Mbth.mbx(lbst, current); p++) {
                    if (Chbrbcter.isLetter(s.chbrAt(p))) {
                        return lbst;
                    }
                }
                lbst = current;
                current = (direction == NEXT) ? words.next()
                                              : words.previous();
            }
            return BrebkIterbtor.DONE;
        }

        /**
         * Returns the String bfter b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         * or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid
         *  index or pbrt
         */
        public String getAfterIndex(int pbrt, int index) {
            if (index < 0 || index >= TextComponent.this.getText().length()) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index+1 >= TextComponent.this.getText().length()) {
                   return null;
                }
                return TextComponent.this.getText().substring(index+1, index+2);
            cbse AccessibleText.WORD:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce();
                    words.setText(s);
                    int stbrt = findWordLimit(index, words, NEXT, s);
                    if (stbrt == BrebkIterbtor.DONE || stbrt >= s.length()) {
                        return null;
                    }
                    int end = words.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end >= s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                }
            cbse AccessibleText.SENTENCE:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor sentence = BrebkIterbtor.getSentenceInstbnce();
                    sentence.setText(s);
                    int stbrt = sentence.following(index);
                    if (stbrt == BrebkIterbtor.DONE || stbrt >= s.length()) {
                        return null;
                    }
                    int end = sentence.following(stbrt);
                    if (end == BrebkIterbtor.DONE || end >= s.length()) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                }
            defbult:
                return null;
            }
        }


        /**
         * Returns the String before b given index.
         *
         * @pbrbm pbrt the AccessibleText.CHARACTER, AccessibleText.WORD,
         *   or AccessibleText.SENTENCE to retrieve
         * @pbrbm index bn index within the text &gt;= 0
         * @return the letter, word, or sentence, null for bn invblid index
         *  or pbrt
         */
        public String getBeforeIndex(int pbrt, int index) {
            if (index < 0 || index > TextComponent.this.getText().length()-1) {
                return null;
            }
            switch (pbrt) {
            cbse AccessibleText.CHARACTER:
                if (index == 0) {
                    return null;
                }
                return TextComponent.this.getText().substring(index-1, index);
            cbse AccessibleText.WORD:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor words = BrebkIterbtor.getWordInstbnce();
                    words.setText(s);
                    int end = findWordLimit(index, words, PREVIOUS, s);
                    if (end == BrebkIterbtor.DONE) {
                        return null;
                    }
                    int stbrt = words.preceding(end);
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                }
            cbse AccessibleText.SENTENCE:  {
                    String s = TextComponent.this.getText();
                    BrebkIterbtor sentence = BrebkIterbtor.getSentenceInstbnce();
                    sentence.setText(s);
                    int end = sentence.following(index);
                    end = sentence.previous();
                    int stbrt = sentence.previous();
                    if (stbrt == BrebkIterbtor.DONE) {
                        return null;
                    }
                    return s.substring(stbrt, end);
                }
            defbult:
                return null;
            }
        }
    }  // end of AccessibleAWTTextComponent

    privbte boolebn checkForEnbbleIM = true;
}
