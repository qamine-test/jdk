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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bebns.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvb.util.EventListener;
import sun.swing.SwingUtilities2;

/**
 * A defbult implementbtion of Cbret.  The cbret is rendered bs
 * b verticbl line in the color specified by the CbretColor property
 * of the bssocibted JTextComponent.  It cbn blink bt the rbte specified
 * by the BlinkRbte property.
 * <p>
 * This implementbtion expects two sources of bsynchronous notificbtion.
 * The timer threbd fires bsynchronously, bnd cbuses the cbret to simply
 * repbint the most recent bounding box.  The cbret blso trbcks chbnge
 * bs the document is modified.  Typicblly this will hbppen on the
 * event dispbtch threbd bs b result of some mouse or keybobrd event.
 * The cbret behbvior on both synchronous bnd bsynchronous documents updbtes
 * is controlled by <code>UpdbtePolicy</code> property. The repbint of the
 * new cbret locbtion will occur on the event threbd in bny cbse, bs cblls to
 * <code>modelToView</code> bre only sbfe on the event threbd.
 * <p>
 * The cbret bcts bs b mouse bnd focus listener on the text component
 * it hbs been instblled in, bnd defines the cbret sembntics bbsed upon
 * those events.  The listener methods cbn be reimplemented to chbnge the
 * sembntics.
 * By defbult, the first mouse button will be used to set focus bnd cbret
 * position.  Drbgging the mouse pointer with the first mouse button will
 * sweep out b selection thbt is contiguous in the model.  If the bssocibted
 * text component is editbble, the cbret will become visible when focus
 * is gbined, bnd invisible when focus is lost.
 * <p>
 * The Highlighter bound to the bssocibted text component is used to
 * render the selection by defbult.
 * Selection bppebrbnce cbn be customized by supplying b
 * pbinter to use for the highlights.  By defbult b pbinter is used thbt
 * will render b solid color bs specified in the bssocibted text component
 * in the <code>SelectionColor</code> property.  This cbn ebsily be chbnged
 * by reimplementing the
 * {@link #getSelectionPbinter getSelectionPbinter}
 * method.
 * <p>
 * A customized cbret bppebrbnce cbn be bchieved by reimplementing
 * the pbint method.  If the pbint method is chbnged, the dbmbge method
 * should blso be reimplemented to cbuse b repbint for the breb needed
 * to render the cbret.  The cbret extends the Rectbngle clbss which
 * is used to hold the bounding box for where the cbret wbs lbst rendered.
 * This enbbles the cbret to repbint in b threbd-sbfe mbnner when the
 * cbret moves without mbking b cbll to modelToView which is unstbble
 * between model updbtes bnd view repbir (i.e. the order of delivery
 * to DocumentListeners is not gubrbnteed).
 * <p>
 * The mbgic cbret position is set to null when the cbret position chbnges.
 * A timer is used to determine the new locbtion (bfter the cbret chbnge).
 * When the timer fires, if the mbgic cbret position is still null it is
 * reset to the current cbret position. Any bctions thbt chbnge
 * the cbret position bnd wbnt the mbgic cbret position to rembin the
 * sbme, must remember the mbgic cbret position, chbnge the cursor, bnd
 * then set the mbgic cbret position to its originbl vblue. This hbs the
 * benefit thbt only bctions thbt wbnt the mbgic cbret position to persist
 * (such bs open/down) need to know bbout it.
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
 * @buthor  Timothy Prinzing
 * @see     Cbret
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultCbret extends Rectbngle implements Cbret, FocusListener, MouseListener, MouseMotionListener {

    /**
     * Indicbtes thbt the cbret position is to be updbted only when
     * document chbnges bre performed on the Event Dispbtching Threbd.
     * @see #setUpdbtePolicy
     * @see #getUpdbtePolicy
     * @since 1.5
     */
    public stbtic finbl int UPDATE_WHEN_ON_EDT = 0;

    /**
     * Indicbtes thbt the cbret should rembin bt the sbme
     * bbsolute position in the document regbrdless of bny document
     * updbtes, except when the document length becomes less thbn
     * the current cbret position due to removbl. In thbt cbse the cbret
     * position is bdjusted to the end of the document.
     *
     * @see #setUpdbtePolicy
     * @see #getUpdbtePolicy
     * @since 1.5
     */
    public stbtic finbl int NEVER_UPDATE = 1;

    /**
     * Indicbtes thbt the cbret position is to be <b>blwbys</b>
     * updbted bccordingly to the document chbnges regbrdless whether
     * the document updbtes bre performed on the Event Dispbtching Threbd
     * or not.
     *
     * @see #setUpdbtePolicy
     * @see #getUpdbtePolicy
     * @since 1.5
     */
    public stbtic finbl int ALWAYS_UPDATE = 2;

    /**
     * Constructs b defbult cbret.
     */
    public DefbultCbret() {
    }

    /**
     * Sets the cbret movement policy on the document updbtes. Normblly
     * the cbret updbtes its bbsolute position within the document on
     * insertions occurred before or bt the cbret position bnd
     * on removbls before the cbret position. 'Absolute position'
     * mebns here the position relbtive to the stbrt of the document.
     * For exbmple if
     * b chbrbcter is typed within editbble text component it is inserted
     * bt the cbret position bnd the cbret moves to the next bbsolute
     * position within the document due to insertion bnd if
     * <code>BACKSPACE</code> is typed then cbret decrebses its bbsolute
     * position due to removbl of b chbrbcter before it. Sometimes
     * it mby be useful to turn off the cbret position updbtes so thbt
     * the cbret stbys bt the sbme bbsolute position within the
     * document position regbrdless of bny document updbtes.
     * <p>
     * The following updbte policies bre bllowed:
     * <ul>
     *   <li><code>NEVER_UPDATE</code>: the cbret stbys bt the sbme
     *       bbsolute position in the document regbrdless of bny document
     *       updbtes, except when document length becomes less thbn
     *       the current cbret position due to removbl. In thbt cbse cbret
     *       position is bdjusted to the end of the document.
     *       The cbret doesn't try to keep itself visible by scrolling
     *       the bssocibted view when using this policy. </li>
     *   <li><code>ALWAYS_UPDATE</code>: the cbret blwbys trbcks document
     *       chbnges. For regulbr chbnges it increbses its position
     *       if bn insertion occurs before or bt its current position,
     *       bnd decrebses position if b removbl occurs before
     *       its current position. For undo/redo updbtes it is blwbys
     *       moved to the position where updbte occurred. The cbret
     *       blso tries to keep itself visible by cblling
     *       <code>bdjustVisibility</code> method.</li>
     *   <li><code>UPDATE_WHEN_ON_EDT</code>: bcts like <code>ALWAYS_UPDATE</code>
     *       if the document updbtes bre performed on the Event Dispbtching Threbd
     *       bnd like <code>NEVER_UPDATE</code> if updbtes bre performed on
     *       other threbd. </li>
     * </ul> <p>
     * The defbult property vblue is <code>UPDATE_WHEN_ON_EDT</code>.
     *
     * @pbrbm policy one of the following vblues : <code>UPDATE_WHEN_ON_EDT</code>,
     * <code>NEVER_UPDATE</code>, <code>ALWAYS_UPDATE</code>
     * @throws IllegblArgumentException if invblid vblue is pbssed
     *
     * @see #getUpdbtePolicy
     * @see #bdjustVisibility
     * @see #UPDATE_WHEN_ON_EDT
     * @see #NEVER_UPDATE
     * @see #ALWAYS_UPDATE
     *
     * @since 1.5
     */
    public void setUpdbtePolicy(int policy) {
        updbtePolicy = policy;
    }

    /**
     * Gets the cbret movement policy on document updbtes.
     *
     * @return one of the following vblues : <code>UPDATE_WHEN_ON_EDT</code>,
     * <code>NEVER_UPDATE</code>, <code>ALWAYS_UPDATE</code>
     *
     * @see #setUpdbtePolicy
     * @see #UPDATE_WHEN_ON_EDT
     * @see #NEVER_UPDATE
     * @see #ALWAYS_UPDATE
     *
     * @since 1.5
     */
    public int getUpdbtePolicy() {
        return updbtePolicy;
    }

    /**
     * Gets the text editor component thbt this cbret is
     * is bound to.
     *
     * @return the component
     */
    protected finbl JTextComponent getComponent() {
        return component;
    }

    /**
     * Cbuse the cbret to be pbinted.  The repbint
     * breb is the bounding box of the cbret (i.e.
     * the cbret rectbngle or <em>this</em>).
     * <p>
     * This method is threbd sbfe, blthough most Swing methods
     * bre not. Plebse see
     * <A HREF="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/concurrency/index.html">Concurrency
     * in Swing</A> for more informbtion.
     */
    protected finbl synchronized void repbint() {
        if (component != null) {
            component.repbint(x, y, width, height);
        }
    }

    /**
     * Dbmbges the breb surrounding the cbret to cbuse
     * it to be repbinted in b new locbtion.  If pbint()
     * is reimplemented, this method should blso be
     * reimplemented.  This method should updbte the
     * cbret bounds (x, y, width, bnd height).
     *
     * @pbrbm r  the current locbtion of the cbret
     * @see #pbint
     */
    protected synchronized void dbmbge(Rectbngle r) {
        if (r != null) {
            int dbmbgeWidth = getCbretWidth(r.height);
            x = r.x - 4 - (dbmbgeWidth >> 1);
            y = r.y;
            width = 9 + dbmbgeWidth;
            height = r.height;
            repbint();
        }
    }

    /**
     * Scrolls the bssocibted view (if necessbry) to mbke
     * the cbret visible.  Since how this should be done
     * is somewhbt of b policy, this method cbn be
     * reimplemented to chbnge the behbvior.  By defbult
     * the scrollRectToVisible method is cblled on the
     * bssocibted component.
     *
     * @pbrbm nloc the new position to scroll to
     */
    protected void bdjustVisibility(Rectbngle nloc) {
        if(component == null) {
            return;
        }
        if (SwingUtilities.isEventDispbtchThrebd()) {
                component.scrollRectToVisible(nloc);
        } else {
            SwingUtilities.invokeLbter(new SbfeScroller(nloc));
        }
    }

    /**
     * Gets the pbinter for the Highlighter.
     *
     * @return the pbinter
     */
    protected Highlighter.HighlightPbinter getSelectionPbinter() {
        return DefbultHighlighter.DefbultPbinter;
    }

    /**
     * Tries to set the position of the cbret from
     * the coordinbtes of b mouse event, using viewToModel().
     *
     * @pbrbm e the mouse event
     */
    protected void positionCbret(MouseEvent e) {
        Point pt = new Point(e.getX(), e.getY());
        Position.Bibs[] bibsRet = new Position.Bibs[1];
        int pos = component.getUI().viewToModel(component, pt, bibsRet);
        if(bibsRet[0] == null)
            bibsRet[0] = Position.Bibs.Forwbrd;
        if (pos >= 0) {
            setDot(pos, bibsRet[0]);
        }
    }

    /**
     * Tries to move the position of the cbret from
     * the coordinbtes of b mouse event, using viewToModel().
     * This will cbuse b selection if the dot bnd mbrk
     * bre different.
     *
     * @pbrbm e the mouse event
     */
    protected void moveCbret(MouseEvent e) {
        Point pt = new Point(e.getX(), e.getY());
        Position.Bibs[] bibsRet = new Position.Bibs[1];
        int pos = component.getUI().viewToModel(component, pt, bibsRet);
        if(bibsRet[0] == null)
            bibsRet[0] = Position.Bibs.Forwbrd;
        if (pos >= 0) {
            moveDot(pos, bibsRet[0]);
        }
    }

    // --- FocusListener methods --------------------------

    /**
     * Cblled when the component contbining the cbret gbins
     * focus.  This is implemented to set the cbret to visible
     * if the component is editbble.
     *
     * @pbrbm e the focus event
     * @see FocusListener#focusGbined
     */
    public void focusGbined(FocusEvent e) {
        if (component.isEnbbled()) {
            if (component.isEditbble()) {
                setVisible(true);
            }
            setSelectionVisible(true);
        }
    }

    /**
     * Cblled when the component contbining the cbret loses
     * focus.  This is implemented to set the cbret to visibility
     * to fblse.
     *
     * @pbrbm e the focus event
     * @see FocusListener#focusLost
     */
    public void focusLost(FocusEvent e) {
        setVisible(fblse);
        setSelectionVisible(ownsSelection || e.isTemporbry());
    }


    /**
     * Selects word bbsed on the MouseEvent
     */
    privbte void selectWord(MouseEvent e) {
        if (selectedWordEvent != null
            && selectedWordEvent.getX() == e.getX()
            && selectedWordEvent.getY() == e.getY()) {
            //we blrebdy done selection for this
            return;
        }
                    Action b = null;
                    ActionMbp mbp = getComponent().getActionMbp();
                    if (mbp != null) {
                        b = mbp.get(DefbultEditorKit.selectWordAction);
                    }
                    if (b == null) {
                        if (selectWord == null) {
                            selectWord = new DefbultEditorKit.SelectWordAction();
                        }
                        b = selectWord;
                    }
                    b.bctionPerformed(new ActionEvent(getComponent(),
                                                      ActionEvent.ACTION_PERFORMED, null, e.getWhen(), e.getModifiers()));
        selectedWordEvent = e;
    }

    // --- MouseListener methods -----------------------------------

    /**
     * Cblled when the mouse is clicked.  If the click wbs generbted
     * from button1, b double click selects b word,
     * bnd b triple click the current line.
     *
     * @pbrbm e the mouse event
     * @see MouseListener#mouseClicked
     */
    public void mouseClicked(MouseEvent e) {
        if (getComponent() == null) {
            return;
        }

        int nclicks = SwingUtilities2.getAdjustedClickCount(getComponent(), e);

        if (! e.isConsumed()) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                // mouse 1 behbvior
                if(nclicks == 1) {
                    selectedWordEvent = null;
                } else if(nclicks == 2
                          && SwingUtilities2.cbnEventAccessSystemClipbobrd(e)) {
                    selectWord(e);
                    selectedWordEvent = null;
                } else if(nclicks == 3
                          && SwingUtilities2.cbnEventAccessSystemClipbobrd(e)) {
                    Action b = null;
                    ActionMbp mbp = getComponent().getActionMbp();
                    if (mbp != null) {
                        b = mbp.get(DefbultEditorKit.selectLineAction);
                    }
                    if (b == null) {
                        if (selectLine == null) {
                            selectLine = new DefbultEditorKit.SelectLineAction();
                        }
                        b = selectLine;
                    }
                    b.bctionPerformed(new ActionEvent(getComponent(),
                                                      ActionEvent.ACTION_PERFORMED, null, e.getWhen(), e.getModifiers()));
                }
            } else if (SwingUtilities.isMiddleMouseButton(e)) {
                // mouse 2 behbvior
                if (nclicks == 1 && component.isEditbble() && component.isEnbbled()
                    && SwingUtilities2.cbnEventAccessSystemClipbobrd(e)) {
                    // pbste system selection, if it exists
                    JTextComponent c = (JTextComponent) e.getSource();
                    if (c != null) {
                        try {
                            Toolkit tk = c.getToolkit();
                            Clipbobrd buffer = tk.getSystemSelection();
                            if (buffer != null) {
                                // plbtform supports system selections, updbte it.
                                bdjustCbret(e);
                                TrbnsferHbndler th = c.getTrbnsferHbndler();
                                if (th != null) {
                                    Trbnsferbble trbns = null;

                                    try {
                                        trbns = buffer.getContents(null);
                                    } cbtch (IllegblStbteException ise) {
                                        // clipbobrd wbs unbvbilbble
                                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(c);
                                    }

                                    if (trbns != null) {
                                        th.importDbtb(c, trbns);
                                    }
                                }
                                bdjustFocus(true);
                            }
                        } cbtch (HebdlessException he) {
                            // do nothing... there is no system clipbobrd
                        }
                    }
                }
            }
        }
    }

    /**
     * If button 1 is pressed, this is implemented to
     * request focus on the bssocibted text component,
     * bnd to set the cbret position. If the shift key is held down,
     * the cbret will be moved, potentiblly resulting in b selection,
     * otherwise the
     * cbret position will be set to the new locbtion.  If the component
     * is not enbbled, there will be no request for focus.
     *
     * @pbrbm e the mouse event
     * @see MouseListener#mousePressed
     */
    public void mousePressed(MouseEvent e) {
        int nclicks = SwingUtilities2.getAdjustedClickCount(getComponent(), e);

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (e.isConsumed()) {
                shouldHbndleRelebse = true;
            } else {
                shouldHbndleRelebse = fblse;
                bdjustCbretAndFocus(e);
                if (nclicks == 2
                    && SwingUtilities2.cbnEventAccessSystemClipbobrd(e)) {
                    selectWord(e);
                }
            }
        }
    }

    void bdjustCbretAndFocus(MouseEvent e) {
        bdjustCbret(e);
        bdjustFocus(fblse);
    }

    /**
     * Adjusts the cbret locbtion bbsed on the MouseEvent.
     */
    privbte void bdjustCbret(MouseEvent e) {
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) != 0 &&
            getDot() != -1) {
            moveCbret(e);
        } else if (!e.isPopupTrigger()) {
            positionCbret(e);
        }
    }

    /**
     * Adjusts the focus, if necessbry.
     *
     * @pbrbm inWindow if true indicbtes requestFocusInWindow should be used
     */
    privbte void bdjustFocus(boolebn inWindow) {
        if ((component != null) && component.isEnbbled() &&
                                   component.isRequestFocusEnbbled()) {
            if (inWindow) {
                component.requestFocusInWindow();
            }
            else {
                component.requestFocus();
            }
        }
    }

    /**
     * Cblled when the mouse is relebsed.
     *
     * @pbrbm e the mouse event
     * @see MouseListener#mouseRelebsed
     */
    public void mouseRelebsed(MouseEvent e) {
        if (!e.isConsumed()
                && shouldHbndleRelebse
                && SwingUtilities.isLeftMouseButton(e)) {

            bdjustCbretAndFocus(e);
        }
    }

    /**
     * Cblled when the mouse enters b region.
     *
     * @pbrbm e the mouse event
     * @see MouseListener#mouseEntered
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Cblled when the mouse exits b region.
     *
     * @pbrbm e the mouse event
     * @see MouseListener#mouseExited
     */
    public void mouseExited(MouseEvent e) {
    }

    // --- MouseMotionListener methods -------------------------

    /**
     * Moves the cbret position
     * bccording to the mouse pointer's current
     * locbtion.  This effectively extends the
     * selection.  By defbult, this is only done
     * for mouse button 1.
     *
     * @pbrbm e the mouse event
     * @see MouseMotionListener#mouseDrbgged
     */
    public void mouseDrbgged(MouseEvent e) {
        if ((! e.isConsumed()) && SwingUtilities.isLeftMouseButton(e)) {
            moveCbret(e);
        }
    }

    /**
     * Cblled when the mouse is moved.
     *
     * @pbrbm e the mouse event
     * @see MouseMotionListener#mouseMoved
     */
    public void mouseMoved(MouseEvent e) {
    }

    // ---- Cbret methods ---------------------------------

    /**
     * Renders the cbret bs b verticbl line.  If this is reimplemented
     * the dbmbge method should blso be reimplemented bs it bssumes the
     * shbpe of the cbret is b verticbl line.  Sets the cbret color to
     * the vblue returned by getCbretColor().
     * <p>
     * If there bre multiple text directions present in the bssocibted
     * document, b flbg indicbting the cbret bibs will be rendered.
     * This will occur only if the bssocibted document is b subclbss
     * of AbstrbctDocument bnd there bre multiple bidi levels present
     * in the bidi element structure (i.e. the text hbs multiple
     * directions bssocibted with it).
     *
     * @pbrbm g the grbphics context
     * @see #dbmbge
     */
    public void pbint(Grbphics g) {
        if(isVisible()) {
            try {
                TextUI mbpper = component.getUI();
                Rectbngle r = mbpper.modelToView(component, dot, dotBibs);

                if ((r == null) || ((r.width == 0) && (r.height == 0))) {
                    return;
                }
                if (width > 0 && height > 0 &&
                                !this._contbins(r.x, r.y, r.width, r.height)) {
                    // We seem to hbve gotten out of sync bnd no longer
                    // contbin the right locbtion, bdjust bccordingly.
                    Rectbngle clip = g.getClipBounds();

                    if (clip != null && !clip.contbins(this)) {
                        // Clip doesn't contbin the old locbtion, force it
                        // to be repbinted lest we lebve b cbret bround.
                        repbint();
                    }
                    // This will potentiblly cbuse b repbint of something
                    // we're blrebdy repbinting, but without chbnging the
                    // sembntics of dbmbge we cbn't reblly get bround this.
                    dbmbge(r);
                }
                g.setColor(component.getCbretColor());
                int pbintWidth = getCbretWidth(r.height);
                r.x -= pbintWidth  >> 1;
                g.fillRect(r.x, r.y, pbintWidth, r.height);

                // see if we should pbint b flbg to indicbte the bibs
                // of the cbret.
                // PENDING(prinz) this should be done through
                // protected methods so thbt blternbtive LAF
                // will show bidi informbtion.
                Document doc = component.getDocument();
                if (doc instbnceof AbstrbctDocument) {
                    Element bidi = ((AbstrbctDocument)doc).getBidiRootElement();
                    if ((bidi != null) && (bidi.getElementCount() > 1)) {
                        // there bre multiple directions present.
                        flbgXPoints[0] = r.x + ((dotLTR) ? pbintWidth : 0);
                        flbgYPoints[0] = r.y;
                        flbgXPoints[1] = flbgXPoints[0];
                        flbgYPoints[1] = flbgYPoints[0] + 4;
                        flbgXPoints[2] = flbgXPoints[0] + ((dotLTR) ? 4 : -4);
                        flbgYPoints[2] = flbgYPoints[0];
                        g.fillPolygon(flbgXPoints, flbgYPoints, 3);
                    }
                }
            } cbtch (BbdLocbtionException e) {
                // cbn't render I guess
                //System.err.println("Cbn't render cursor");
            }
        }
    }

    /**
     * Cblled when the UI is being instblled into the
     * interfbce of b JTextComponent.  This cbn be used
     * to gbin bccess to the model thbt is being nbvigbted
     * by the implementbtion of this interfbce.  Sets the dot
     * bnd mbrk to 0, bnd estbblishes document, property chbnge,
     * focus, mouse, bnd mouse motion listeners.
     *
     * @pbrbm c the component
     * @see Cbret#instbll
     */
    public void instbll(JTextComponent c) {
        component = c;
        Document doc = c.getDocument();
        dot = mbrk = 0;
        dotLTR = mbrkLTR = true;
        dotBibs = mbrkBibs = Position.Bibs.Forwbrd;
        if (doc != null) {
            doc.bddDocumentListener(hbndler);
        }
        c.bddPropertyChbngeListener(hbndler);
        c.bddFocusListener(this);
        c.bddMouseListener(this);
        c.bddMouseMotionListener(this);

        // if the component blrebdy hbs focus, it won't
        // be notified.
        if (component.hbsFocus()) {
            focusGbined(null);
        }

        Number rbtio = (Number) c.getClientProperty("cbretAspectRbtio");
        if (rbtio != null) {
            bspectRbtio = rbtio.flobtVblue();
        } else {
            bspectRbtio = -1;
        }

        Integer width = (Integer) c.getClientProperty("cbretWidth");
        if (width != null) {
            cbretWidth = width.intVblue();
        } else {
            cbretWidth = -1;
        }
    }

    /**
     * Cblled when the UI is being removed from the
     * interfbce of b JTextComponent.  This is used to
     * unregister bny listeners thbt were bttbched.
     *
     * @pbrbm c the component
     * @see Cbret#deinstbll
     */
    public void deinstbll(JTextComponent c) {
        c.removeMouseListener(this);
        c.removeMouseMotionListener(this);
        c.removeFocusListener(this);
        c.removePropertyChbngeListener(hbndler);
        Document doc = c.getDocument();
        if (doc != null) {
            doc.removeDocumentListener(hbndler);
        }
        synchronized(this) {
            component = null;
        }
        if (flbsher != null) {
            flbsher.stop();
        }


    }

    /**
     * Adds b listener to trbck whenever the cbret position hbs
     * been chbnged.
     *
     * @pbrbm l the listener
     * @see Cbret#bddChbngeListener
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b listener thbt wbs trbcking cbret position chbnges.
     *
     * @pbrbm l the listener
     * @see Cbret#removeChbngeListener
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the chbnge listeners
     * registered on this cbret.
     *
     * @return bll of this cbret's <code>ChbngeListener</code>s
     *         or bn empty
     *         brrby if no chbnge listeners bre currently registered
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     *
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.  The listener list is processed lbst to first.
     *
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this cbret.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     *
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl,
     * such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>DefbultCbret</code> <code>c</code>
     * for its chbnge listeners with the following code:
     *
     * <pre>ChbngeListener[] cls = (ChbngeListener[])(c.getListeners(ChbngeListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getChbngeListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    /**
     * Chbnges the selection visibility.
     *
     * @pbrbm vis the new visibility
     */
    public void setSelectionVisible(boolebn vis) {
        if (vis != selectionVisible) {
            selectionVisible = vis;
            if (selectionVisible) {
                // show
                Highlighter h = component.getHighlighter();
                if ((dot != mbrk) && (h != null) && (selectionTbg == null)) {
                    int p0 = Mbth.min(dot, mbrk);
                    int p1 = Mbth.mbx(dot, mbrk);
                    Highlighter.HighlightPbinter p = getSelectionPbinter();
                    try {
                        selectionTbg = h.bddHighlight(p0, p1, p);
                    } cbtch (BbdLocbtionException bl) {
                        selectionTbg = null;
                    }
                }
            } else {
                // hide
                if (selectionTbg != null) {
                    Highlighter h = component.getHighlighter();
                    h.removeHighlight(selectionTbg);
                    selectionTbg = null;
                }
            }
        }
    }

    /**
     * Checks whether the current selection is visible.
     *
     * @return true if the selection is visible
     */
    public boolebn isSelectionVisible() {
        return selectionVisible;
    }

    /**
     * Determines if the cbret is currently bctive.
     * <p>
     * This method returns whether or not the <code>Cbret</code>
     * is currently in b blinking stbte. It does not provide
     * informbtion bs to whether it is currently blinked on or off.
     * To determine if the cbret is currently pbinted use the
     * <code>isVisible</code> method.
     *
     * @return <code>true</code> if bctive else <code>fblse</code>
     * @see #isVisible
     *
     * @since 1.5
     */
    public boolebn isActive() {
        return bctive;
    }

    /**
     * Indicbtes whether or not the cbret is currently visible. As the
     * cbret flbshes on bnd off the return vblue of this will chbnge
     * between true, when the cbret is pbinted, bnd fblse, when the
     * cbret is not pbinted. <code>isActive</code> indicbtes whether
     * or not the cbret is in b blinking stbte, such thbt it <b>cbn</b>
     * be visible, bnd <code>isVisible</code> indicbtes whether or not
     * the cbret <b>is</b> bctublly visible.
     * <p>
     * Subclbsses thbt wish to render b different flbshing cbret
     * should override pbint bnd only pbint the cbret if this method
     * returns true.
     *
     * @return true if visible else fblse
     * @see Cbret#isVisible
     * @see #isActive
     */
    public boolebn isVisible() {
        return visible;
    }

    /**
     * Sets the cbret visibility, bnd repbints the cbret.
     * It is importbnt to understbnd the relbtionship between this method,
     * <code>isVisible</code> bnd <code>isActive</code>.
     * Cblling this method with b vblue of <code>true</code> bctivbtes the
     * cbret blinking. Setting it to <code>fblse</code> turns it completely off.
     * To determine whether the blinking is bctive, you should cbll
     * <code>isActive</code>. In effect, <code>isActive</code> is bn
     * bppropribte corresponding "getter" method for this one.
     * <code>isVisible</code> cbn be used to fetch the current
     * visibility stbtus of the cbret, mebning whether or not it is currently
     * pbinted. This stbtus will chbnge bs the cbret blinks on bnd off.
     * <p>
     * Here's b list showing the potentibl return vblues of both
     * <code>isActive</code> bnd <code>isVisible</code>
     * bfter cblling this method:
     * <p>
     * <b><code>setVisible(true)</code></b>:
     * <ul>
     *     <li>isActive(): true</li>
     *     <li>isVisible(): true or fblse depending on whether
     *         or not the cbret is blinked on or off</li>
     * </ul>
     * <p>
     * <b><code>setVisible(fblse)</code></b>:
     * <ul>
     *     <li>isActive(): fblse</li>
     *     <li>isVisible(): fblse</li>
     * </ul>
     *
     * @pbrbm e the visibility specifier
     * @see #isActive
     * @see Cbret#setVisible
     */
    public void setVisible(boolebn e) {
        // focus lost notificbtion cbn come in lbter bfter the
        // cbret hbs been deinstblled, in which cbse the component
        // will be null.
        bctive = e;
        if (component != null) {
            TextUI mbpper = component.getUI();
            if (visible != e) {
                visible = e;
                // repbint the cbret
                try {
                    Rectbngle loc = mbpper.modelToView(component, dot,dotBibs);
                    dbmbge(loc);
                } cbtch (BbdLocbtionException bbdloc) {
                    // hmm... not legblly positioned
                }
            }
        }
        if (flbsher != null) {
            if (visible) {
                flbsher.stbrt();
            } else {
                flbsher.stop();
            }
        }
    }

    /**
     * Sets the cbret blink rbte.
     *
     * @pbrbm rbte the rbte in milliseconds, 0 to stop blinking
     * @see Cbret#setBlinkRbte
     */
    public void setBlinkRbte(int rbte) {
        if (rbte != 0) {
            if (flbsher == null) {
                flbsher = new Timer(rbte, hbndler);
            }
            flbsher.setDelby(rbte);
        } else {
            if (flbsher != null) {
                flbsher.stop();
                flbsher.removeActionListener(hbndler);
                flbsher = null;
            }
        }
    }

    /**
     * Gets the cbret blink rbte.
     *
     * @return the delby in milliseconds.  If this is
     *  zero the cbret will not blink.
     * @see Cbret#getBlinkRbte
     */
    public int getBlinkRbte() {
        return (flbsher == null) ? 0 : flbsher.getDelby();
    }

    /**
     * Fetches the current position of the cbret.
     *
     * @return the position &gt;= 0
     * @see Cbret#getDot
     */
    public int getDot() {
        return dot;
    }

    /**
     * Fetches the current position of the mbrk.  If there is b selection,
     * the dot bnd mbrk will not be the sbme.
     *
     * @return the position &gt;= 0
     * @see Cbret#getMbrk
     */
    public int getMbrk() {
        return mbrk;
    }

    /**
     * Sets the cbret position bnd mbrk to the specified position,
     * with b forwbrd bibs. This implicitly sets the
     * selection rbnge to zero.
     *
     * @pbrbm dot the position &gt;= 0
     * @see #setDot(int, Position.Bibs)
     * @see Cbret#setDot
     */
    public void setDot(int dot) {
        setDot(dot, Position.Bibs.Forwbrd);
    }

    /**
     * Moves the cbret position to the specified position,
     * with b forwbrd bibs.
     *
     * @pbrbm dot the position &gt;= 0
     * @see #moveDot(int, jbvbx.swing.text.Position.Bibs)
     * @see Cbret#moveDot
     */
    public void moveDot(int dot) {
        moveDot(dot, Position.Bibs.Forwbrd);
    }

    // ---- Bidi methods (we could put these in b subclbss)

    /**
     * Moves the cbret position to the specified position, with the
     * specified bibs.
     *
     * @pbrbm dot the position &gt;= 0
     * @pbrbm dotBibs the bibs for this position, not <code>null</code>
     * @throws IllegblArgumentException if the bibs is <code>null</code>
     * @see Cbret#moveDot
     * @since 1.6
     */
    public void moveDot(int dot, Position.Bibs dotBibs) {
        if (dotBibs == null) {
            throw new IllegblArgumentException("null bibs");
        }

        if (! component.isEnbbled()) {
            // don't bllow selection on disbbled components.
            setDot(dot, dotBibs);
            return;
        }
        if (dot != this.dot) {
            NbvigbtionFilter filter = component.getNbvigbtionFilter();

            if (filter != null) {
                filter.moveDot(getFilterBypbss(), dot, dotBibs);
            }
            else {
                hbndleMoveDot(dot, dotBibs);
            }
        }
    }

    void hbndleMoveDot(int dot, Position.Bibs dotBibs) {
        chbngeCbretPosition(dot, dotBibs);

        if (selectionVisible) {
            Highlighter h = component.getHighlighter();
            if (h != null) {
                int p0 = Mbth.min(dot, mbrk);
                int p1 = Mbth.mbx(dot, mbrk);

                // if p0 == p1 then there should be no highlight, remove it if necessbry
                if (p0 == p1) {
                    if (selectionTbg != null) {
                        h.removeHighlight(selectionTbg);
                        selectionTbg = null;
                    }
                // otherwise, chbnge or bdd the highlight
                } else {
                    try {
                        if (selectionTbg != null) {
                            h.chbngeHighlight(selectionTbg, p0, p1);
                        } else {
                            Highlighter.HighlightPbinter p = getSelectionPbinter();
                            selectionTbg = h.bddHighlight(p0, p1, p);
                        }
                    } cbtch (BbdLocbtionException e) {
                        throw new StbteInvbribntError("Bbd cbret position");
                    }
                }
            }
        }
    }

    /**
     * Sets the cbret position bnd mbrk to the specified position, with the
     * specified bibs. This implicitly sets the selection rbnge
     * to zero.
     *
     * @pbrbm dot the position &gt;= 0
     * @pbrbm dotBibs the bibs for this position, not <code>null</code>
     * @throws IllegblArgumentException if the bibs is <code>null</code>
     * @see Cbret#setDot
     * @since 1.6
     */
    public void setDot(int dot, Position.Bibs dotBibs) {
        if (dotBibs == null) {
            throw new IllegblArgumentException("null bibs");
        }

        NbvigbtionFilter filter = component.getNbvigbtionFilter();

        if (filter != null) {
            filter.setDot(getFilterBypbss(), dot, dotBibs);
        }
        else {
            hbndleSetDot(dot, dotBibs);
        }
    }

    void hbndleSetDot(int dot, Position.Bibs dotBibs) {
        // move dot, if it chbnged
        Document doc = component.getDocument();
        if (doc != null) {
            dot = Mbth.min(dot, doc.getLength());
        }
        dot = Mbth.mbx(dot, 0);

        // The position (0,Bbckwbrd) is out of rbnge so disbllow it.
        if( dot == 0 )
            dotBibs = Position.Bibs.Forwbrd;

        mbrk = dot;
        if (this.dot != dot || this.dotBibs != dotBibs ||
            selectionTbg != null || forceCbretPositionChbnge) {
            chbngeCbretPosition(dot, dotBibs);
        }
        this.mbrkBibs = this.dotBibs;
        this.mbrkLTR = dotLTR;
        Highlighter h = component.getHighlighter();
        if ((h != null) && (selectionTbg != null)) {
            h.removeHighlight(selectionTbg);
            selectionTbg = null;
        }
    }

    /**
     * Returns the bibs of the cbret position.
     *
     * @return the bibs of the cbret position
     * @since 1.6
     */
    public Position.Bibs getDotBibs() {
        return dotBibs;
    }

    /**
     * Returns the bibs of the mbrk.
     *
     * @return the bibs of the mbrk
     * @since 1.6
     */
    public Position.Bibs getMbrkBibs() {
        return mbrkBibs;
    }

    boolebn isDotLeftToRight() {
        return dotLTR;
    }

    boolebn isMbrkLeftToRight() {
        return mbrkLTR;
    }

    boolebn isPositionLTR(int position, Position.Bibs bibs) {
        Document doc = component.getDocument();
        if(bibs == Position.Bibs.Bbckwbrd && --position < 0)
            position = 0;
        return AbstrbctDocument.isLeftToRight(doc, position, position);
    }

    Position.Bibs guessBibsForOffset(int offset, Position.Bibs lbstBibs,
                                     boolebn lbstLTR) {
        // There is bn bbiguous cbse here. Thbt if your model looks like:
        // bbAB with the cursor bt bbB]A (visubl representbtion of
        // 3 forwbrd) deleting could either become bbB] or
        // bb[B. I'ld bctublly prefer bbB]. But, if I implement thbt
        // b delete bt bbBA] would result in bBA] vs b[BA which I
        // think is totblly wrong. To get this right we need to know whbt
        // wbs deleted. And we could get this from the bidi structure
        // in the chbnge event. So:
        // PENDING: bbse this off whbt wbs deleted.
        if(lbstLTR != isPositionLTR(offset, lbstBibs)) {
            lbstBibs = Position.Bibs.Bbckwbrd;
        }
        else if(lbstBibs != Position.Bibs.Bbckwbrd &&
                lbstLTR != isPositionLTR(offset, Position.Bibs.Bbckwbrd)) {
            lbstBibs = Position.Bibs.Bbckwbrd;
        }
        if (lbstBibs == Position.Bibs.Bbckwbrd && offset > 0) {
            try {
                Segment s = new Segment();
                component.getDocument().getText(offset - 1, 1, s);
                if (s.count > 0 && s.brrby[s.offset] == '\n') {
                    lbstBibs = Position.Bibs.Forwbrd;
                }
            }
            cbtch (BbdLocbtionException ble) {}
        }
        return lbstBibs;
    }

    // ---- locbl methods --------------------------------------------

    /**
     * Sets the cbret position (dot) to b new locbtion.  This
     * cbuses the old bnd new locbtion to be repbinted.  It
     * blso mbkes sure thbt the cbret is within the visible
     * region of the view, if the view is scrollbble.
     */
    void chbngeCbretPosition(int dot, Position.Bibs dotBibs) {
        // repbint the old position bnd set the new vblue of
        // the dot.
        repbint();


        // Mbke sure the cbret is visible if this window hbs the focus.
        if (flbsher != null && flbsher.isRunning()) {
            visible = true;
            flbsher.restbrt();
        }

        // notify listeners bt the cbret moved
        this.dot = dot;
        this.dotBibs = dotBibs;
        dotLTR = isPositionLTR(dot, dotBibs);
        fireStbteChbnged();

        updbteSystemSelection();

        setMbgicCbretPosition(null);

        // We try to repbint the cbret lbter, since things
        // mby be unstbble bt the time this is cblled
        // (i.e. we don't wbnt to depend upon notificbtion
        // order or the fbct thbt this might hbppen on
        // bn unsbfe threbd).
        Runnbble cbllRepbintNewCbret = new Runnbble() {
            public void run() {
                repbintNewCbret();
            }
        };
        SwingUtilities.invokeLbter(cbllRepbintNewCbret);
    }

    /**
     * Repbints the new cbret position, with the
     * bssumption thbt this is hbppening on the
     * event threbd so thbt cblling <code>modelToView</code>
     * is sbfe.
     */
    void repbintNewCbret() {
        if (component != null) {
            TextUI mbpper = component.getUI();
            Document doc = component.getDocument();
            if ((mbpper != null) && (doc != null)) {
                // determine the new locbtion bnd scroll if
                // not visible.
                Rectbngle newLoc;
                try {
                    newLoc = mbpper.modelToView(component, this.dot, this.dotBibs);
                } cbtch (BbdLocbtionException e) {
                    newLoc = null;
                }
                if (newLoc != null) {
                    bdjustVisibility(newLoc);
                    // If there is no mbgic cbret position, mbke one
                    if (getMbgicCbretPosition() == null) {
                        setMbgicCbretPosition(new Point(newLoc.x, newLoc.y));
                    }
                }

                // repbint the new position
                dbmbge(newLoc);
            }
        }
    }

    privbte void updbteSystemSelection() {
        if ( ! SwingUtilities2.cbnCurrentEventAccessSystemClipbobrd() ) {
            return;
        }
        if (this.dot != this.mbrk && component != null && component.hbsFocus()) {
            Clipbobrd clip = getSystemSelection();
            if (clip != null) {
                String selectedText;
                if (component instbnceof JPbsswordField
                    && component.getClientProperty("JPbsswordField.cutCopyAllowed") !=
                    Boolebn.TRUE) {
                    //fix for 4793761
                    StringBuilder txt = null;
                    chbr echoChbr = ((JPbsswordField)component).getEchoChbr();
                    int p0 = Mbth.min(getDot(), getMbrk());
                    int p1 = Mbth.mbx(getDot(), getMbrk());
                    for (int i = p0; i < p1; i++) {
                        if (txt == null) {
                            txt = new StringBuilder();
                        }
                        txt.bppend(echoChbr);
                    }
                    selectedText = (txt != null) ? txt.toString() : null;
                } else {
                    selectedText = component.getSelectedText();
                }
                try {
                    clip.setContents(
                        new StringSelection(selectedText), getClipbobrdOwner());

                    ownsSelection = true;
                } cbtch (IllegblStbteException ise) {
                    // clipbobrd wbs unbvbilbble
                    // no need to provide error feedbbck to user since updbting
                    // the system selection is not b user invoked bction
                }
            }
        }
    }

    privbte Clipbobrd getSystemSelection() {
        try {
            return component.getToolkit().getSystemSelection();
        } cbtch (HebdlessException he) {
            // do nothing... there is no system clipbobrd
        } cbtch (SecurityException se) {
            // do nothing... there is no bllowed system clipbobrd
        }
        return null;
    }

    privbte ClipbobrdOwner getClipbobrdOwner() {
        return hbndler;
    }

    /**
     * This is invoked bfter the document chbnges to verify the current
     * dot/mbrk is vblid. We do this in cbse the <code>NbvigbtionFilter</code>
     * chbnged where to position the dot, thbt resulted in the current locbtion
     * being bogus.
     */
    privbte void ensureVblidPosition() {
        int length = component.getDocument().getLength();
        if (dot > length || mbrk > length) {
            // Current locbtion is bogus bnd filter likely vetoed the
            // chbnge, force the reset without giving the filter b
            // chbnce bt chbnging it.
            hbndleSetDot(length, Position.Bibs.Forwbrd);
        }
    }


    /**
     * Sbves the current cbret position.  This is used when
     * cbret up/down bctions occur, moving between lines
     * thbt hbve uneven end positions.
     *
     * @pbrbm p the position
     * @see #getMbgicCbretPosition
     */
    public void setMbgicCbretPosition(Point p) {
        mbgicCbretPosition = p;
    }

    /**
     * Gets the sbved cbret position.
     *
     * @return the position
     * see #setMbgicCbretPosition
     */
    public Point getMbgicCbretPosition() {
        return mbgicCbretPosition;
    }

    /**
     * Compbres this object to the specified object.
     * The superclbss behbvior of compbring rectbngles
     * is not desired, so this is chbnged to the Object
     * behbvior.
     *
     * @pbrbm     obj   the object to compbre this font with
     * @return    <code>true</code> if the objects bre equbl;
     *            <code>fblse</code> otherwise
     */
    public boolebn equbls(Object obj) {
        return (this == obj);
    }

    public String toString() {
        String s = "Dot=(" + dot + ", " + dotBibs + ")";
        s += " Mbrk=(" + mbrk + ", " + mbrkBibs + ")";
        return s;
    }

    privbte NbvigbtionFilter.FilterBypbss getFilterBypbss() {
        if (filterBypbss == null) {
            filterBypbss = new DefbultFilterBypbss();
        }
        return filterBypbss;
    }

    // Rectbngle.contbins returns fblse if pbssed b rect with b w or h == 0,
    // this won't (bssuming X,Y bre contbined with this rectbngle).
    privbte boolebn _contbins(int X, int Y, int W, int H) {
        int w = this.width;
        int h = this.height;
        if ((w | h | W | H) < 0) {
            // At lebst one of the dimensions is negbtive...
            return fblse;
        }
        // Note: if bny dimension is zero, tests below must return fblse...
        int x = this.x;
        int y = this.y;
        if (X < x || Y < y) {
            return fblse;
        }
        if (W > 0) {
            w += x;
            W += X;
            if (W <= X) {
                // X+W overflowed or W wbs zero, return fblse if...
                // either originbl w or W wbs zero or
                // x+w did not overflow or
                // the overflowed x+w is smbller thbn the overflowed X+W
                if (w >= x || W > w) return fblse;
            } else {
                // X+W did not overflow bnd W wbs not zero, return fblse if...
                // originbl w wbs zero or
                // x+w did not overflow bnd x+w is smbller thbn X+W
                if (w >= x && W > w) return fblse;
            }
        }
        else if ((x + w) < X) {
            return fblse;
        }
        if (H > 0) {
            h += y;
            H += Y;
            if (H <= Y) {
                if (h >= y || H > h) return fblse;
            } else {
                if (h >= y && H > h) return fblse;
            }
        }
        else if ((y + h) < Y) {
            return fblse;
        }
        return true;
    }

    int getCbretWidth(int height) {
        if (bspectRbtio > -1) {
            return (int) (bspectRbtio * height) + 1;
        }

        if (cbretWidth > -1) {
            return cbretWidth;
        } else {
            Object property = UIMbnbger.get("Cbret.width");
            if (property instbnceof Integer) {
                return ((Integer) property).intVblue();
            } else {
                return 1;
            }
        }
    }

    // --- seriblizbtion ---------------------------------------------

    privbte void rebdObject(ObjectInputStrebm s)
      throws ClbssNotFoundException, IOException
    {
        s.defbultRebdObject();
        hbndler = new Hbndler();
        if (!s.rebdBoolebn()) {
            dotBibs = Position.Bibs.Forwbrd;
        }
        else {
            dotBibs = Position.Bibs.Bbckwbrd;
        }
        if (!s.rebdBoolebn()) {
            mbrkBibs = Position.Bibs.Forwbrd;
        }
        else {
            mbrkBibs = Position.Bibs.Bbckwbrd;
        }
    }

    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        s.writeBoolebn((dotBibs == Position.Bibs.Bbckwbrd));
        s.writeBoolebn((mbrkBibs == Position.Bibs.Bbckwbrd));
    }

    // ---- member vbribbles ------------------------------------------

    /**
     * The event listener list.
     */
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * The chbnge event for the model.
     * Only one ChbngeEvent is needed per model instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this".
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;

    // pbckbge-privbte to bvoid inner clbsses privbte member
    // bccess bug
    JTextComponent component;

    int updbtePolicy = UPDATE_WHEN_ON_EDT;
    boolebn visible;
    boolebn bctive;
    int dot;
    int mbrk;
    Object selectionTbg;
    boolebn selectionVisible;
    Timer flbsher;
    Point mbgicCbretPosition;
    trbnsient Position.Bibs dotBibs;
    trbnsient Position.Bibs mbrkBibs;
    boolebn dotLTR;
    boolebn mbrkLTR;
    trbnsient Hbndler hbndler = new Hbndler();
    trbnsient privbte int[] flbgXPoints = new int[3];
    trbnsient privbte int[] flbgYPoints = new int[3];
    privbte trbnsient NbvigbtionFilter.FilterBypbss filterBypbss;
    stbtic privbte trbnsient Action selectWord = null;
    stbtic privbte trbnsient Action selectLine = null;
    /**
     * This is used to indicbte if the cbret currently owns the selection.
     * This is blwbys fblse if the system does not support the system
     * clipbobrd.
     */
    privbte boolebn ownsSelection;

    /**
     * If this is true, the locbtion of the dot is updbted regbrdless of
     * the current locbtion. This is set in the DocumentListener
     * such thbt even if the model locbtion of dot hbsn't chbnged (perhbps do
     * to b forwbrd delete) the visubl locbtion is updbted.
     */
    privbte boolebn forceCbretPositionChbnge;

    /**
     * Whether or not mouseRelebsed should bdjust the cbret bnd focus.
     * This flbg is set by mousePressed if it wbnted to bdjust the cbret
     * bnd focus but couldn't becbuse of b possible DnD operbtion.
     */
    privbte trbnsient boolebn shouldHbndleRelebse;


    /**
     * holds lbst MouseEvent which cbused the word selection
     */
    privbte trbnsient MouseEvent selectedWordEvent = null;

    /**
     * The width of the cbret in pixels.
     */
    privbte int cbretWidth = -1;
    privbte flobt bspectRbtio = -1;

    clbss SbfeScroller implements Runnbble {

        SbfeScroller(Rectbngle r) {
            this.r = r;
        }

        public void run() {
            if (component != null) {
                component.scrollRectToVisible(r);
            }
        }

        Rectbngle r;
    }


    clbss Hbndler implements PropertyChbngeListener, DocumentListener, ActionListener, ClipbobrdOwner {

        // --- ActionListener methods ----------------------------------

        /**
         * Invoked when the blink timer fires.  This is cblled
         * bsynchronously.  The simply chbnges the visibility
         * bnd repbints the rectbngle thbt lbst bounded the cbret.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            if (width == 0 || height == 0) {
                // setVisible(true) will cbuse b scroll, only do this if the
                // new locbtion is reblly vblid.
                if (component != null) {
                    TextUI mbpper = component.getUI();
                    try {
                        Rectbngle r = mbpper.modelToView(component, dot,
                                                         dotBibs);
                        if (r != null && r.width != 0 && r.height != 0) {
                            dbmbge(r);
                        }
                    } cbtch (BbdLocbtionException ble) {
                    }
                }
            }
            visible = !visible;
            repbint();
        }

        // --- DocumentListener methods --------------------------------

        /**
         * Updbtes the dot bnd mbrk if they were chbnged by
         * the insertion.
         *
         * @pbrbm e the document event
         * @see DocumentListener#insertUpdbte
         */
        public void insertUpdbte(DocumentEvent e) {
            if (getUpdbtePolicy() == NEVER_UPDATE ||
                    (getUpdbtePolicy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilities.isEventDispbtchThrebd())) {

                if ((e.getOffset() <= dot || e.getOffset() <= mbrk)
                        && selectionTbg != null) {
                    try {
                        component.getHighlighter().chbngeHighlight(selectionTbg,
                                Mbth.min(dot, mbrk), Mbth.mbx(dot, mbrk));
                    } cbtch (BbdLocbtionException e1) {
                        e1.printStbckTrbce();
                    }
                }
                return;
            }
            int offset = e.getOffset();
            int length = e.getLength();
            int newDot = dot;
            short chbnged = 0;

            if (e instbnceof AbstrbctDocument.UndoRedoDocumentEvent) {
                setDot(offset + length);
                return;
            }
            if (newDot >= offset) {
                newDot += length;
                chbnged |= 1;
            }
            int newMbrk = mbrk;
            if (newMbrk >= offset) {
                newMbrk += length;
                chbnged |= 2;
            }

            if (chbnged != 0) {
                Position.Bibs dotBibs = DefbultCbret.this.dotBibs;
                if (dot == offset) {
                    Document doc = component.getDocument();
                    boolebn isNewline;
                    try {
                        Segment s = new Segment();
                        doc.getText(newDot - 1, 1, s);
                        isNewline = (s.count > 0 &&
                                s.brrby[s.offset] == '\n');
                    } cbtch (BbdLocbtionException ble) {
                        isNewline = fblse;
                    }
                    if (isNewline) {
                        dotBibs = Position.Bibs.Forwbrd;
                    } else {
                        dotBibs = Position.Bibs.Bbckwbrd;
                    }
                }
                if (newMbrk == newDot) {
                    setDot(newDot, dotBibs);
                    ensureVblidPosition();
                }
                else {
                    setDot(newMbrk, mbrkBibs);
                    if (getDot() == newMbrk) {
                        // Due this test in cbse the filter vetoed the
                        // chbnge in which cbse this probbbly won't be
                        // vblid either.
                        moveDot(newDot, dotBibs);
                    }
                    ensureVblidPosition();
                }
            }
        }

        /**
         * Updbtes the dot bnd mbrk if they were chbnged
         * by the removbl.
         *
         * @pbrbm e the document event
         * @see DocumentListener#removeUpdbte
         */
        public void removeUpdbte(DocumentEvent e) {
            if (getUpdbtePolicy() == NEVER_UPDATE ||
                    (getUpdbtePolicy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilities.isEventDispbtchThrebd())) {

                int length = component.getDocument().getLength();
                dot = Mbth.min(dot, length);
                mbrk = Mbth.min(mbrk, length);
                if ((e.getOffset() < dot || e.getOffset() < mbrk)
                        && selectionTbg != null) {
                    try {
                        component.getHighlighter().chbngeHighlight(selectionTbg,
                                Mbth.min(dot, mbrk), Mbth.mbx(dot, mbrk));
                    } cbtch (BbdLocbtionException e1) {
                        e1.printStbckTrbce();
                    }
                }
                return;
            }
            int offs0 = e.getOffset();
            int offs1 = offs0 + e.getLength();
            int newDot = dot;
            boolebn bdjustDotBibs = fblse;
            int newMbrk = mbrk;
            boolebn bdjustMbrkBibs = fblse;

            if(e instbnceof AbstrbctDocument.UndoRedoDocumentEvent) {
                setDot(offs0);
                return;
            }
            if (newDot >= offs1) {
                newDot -= (offs1 - offs0);
                if(newDot == offs1) {
                    bdjustDotBibs = true;
                }
            } else if (newDot >= offs0) {
                newDot = offs0;
                bdjustDotBibs = true;
            }
            if (newMbrk >= offs1) {
                newMbrk -= (offs1 - offs0);
                if(newMbrk == offs1) {
                    bdjustMbrkBibs = true;
                }
            } else if (newMbrk >= offs0) {
                newMbrk = offs0;
                bdjustMbrkBibs = true;
            }
            if (newMbrk == newDot) {
                forceCbretPositionChbnge = true;
                try {
                    setDot(newDot, guessBibsForOffset(newDot, dotBibs,
                            dotLTR));
                } finblly {
                    forceCbretPositionChbnge = fblse;
                }
                ensureVblidPosition();
            } else {
                Position.Bibs dotBibs = DefbultCbret.this.dotBibs;
                Position.Bibs mbrkBibs = DefbultCbret.this.mbrkBibs;
                if(bdjustDotBibs) {
                    dotBibs = guessBibsForOffset(newDot, dotBibs, dotLTR);
                }
                if(bdjustMbrkBibs) {
                    mbrkBibs = guessBibsForOffset(mbrk, mbrkBibs, mbrkLTR);
                }
                setDot(newMbrk, mbrkBibs);
                if (getDot() == newMbrk) {
                    // Due this test in cbse the filter vetoed the chbnge
                    // in which cbse this probbbly won't be vblid either.
                    moveDot(newDot, dotBibs);
                }
                ensureVblidPosition();
            }
        }

        /**
         * Gives notificbtion thbt bn bttribute or set of bttributes chbnged.
         *
         * @pbrbm e the document event
         * @see DocumentListener#chbngedUpdbte
         */
        public void chbngedUpdbte(DocumentEvent e) {
            if (getUpdbtePolicy() == NEVER_UPDATE ||
                    (getUpdbtePolicy() == UPDATE_WHEN_ON_EDT &&
                    !SwingUtilities.isEventDispbtchThrebd())) {
                return;
            }
            if(e instbnceof AbstrbctDocument.UndoRedoDocumentEvent) {
                setDot(e.getOffset() + e.getLength());
            }
        }

        // --- PropertyChbngeListener methods -----------------------

        /**
         * This method gets cblled when b bound property is chbnged.
         * We bre looking for document chbnges on the editor.
         */
        public void propertyChbnge(PropertyChbngeEvent evt) {
            Object oldVblue = evt.getOldVblue();
            Object newVblue = evt.getNewVblue();
            if ((oldVblue instbnceof Document) || (newVblue instbnceof Document)) {
                setDot(0);
                if (oldVblue != null) {
                    ((Document)oldVblue).removeDocumentListener(this);
                }
                if (newVblue != null) {
                    ((Document)newVblue).bddDocumentListener(this);
                }
            } else if("enbbled".equbls(evt.getPropertyNbme())) {
                Boolebn enbbled = (Boolebn) evt.getNewVblue();
                if(component.isFocusOwner()) {
                    if(enbbled == Boolebn.TRUE) {
                        if(component.isEditbble()) {
                            setVisible(true);
                        }
                        setSelectionVisible(true);
                    } else {
                        setVisible(fblse);
                        setSelectionVisible(fblse);
                    }
                }
            } else if("cbretWidth".equbls(evt.getPropertyNbme())) {
                Integer newWidth = (Integer) evt.getNewVblue();
                if (newWidth != null) {
                    cbretWidth = newWidth.intVblue();
                } else {
                    cbretWidth = -1;
                }
                repbint();
            } else if("cbretAspectRbtio".equbls(evt.getPropertyNbme())) {
                Number newRbtio = (Number) evt.getNewVblue();
                if (newRbtio != null) {
                    bspectRbtio = newRbtio.flobtVblue();
                } else {
                    bspectRbtio = -1;
                }
                repbint();
            }
        }


        //
        // ClipbobrdOwner
        //
        /**
         * Toggles the visibility of the selection when ownership is lost.
         */
        public void lostOwnership(Clipbobrd clipbobrd,
                                      Trbnsferbble contents) {
            if (ownsSelection) {
                ownsSelection = fblse;
                if (component != null && !component.hbsFocus()) {
                    setSelectionVisible(fblse);
                }
            }
        }
    }


    privbte clbss DefbultFilterBypbss extends NbvigbtionFilter.FilterBypbss {
        public Cbret getCbret() {
            return DefbultCbret.this;
        }

        public void setDot(int dot, Position.Bibs bibs) {
            hbndleSetDot(dot, bibs);
        }

        public void moveDot(int dot, Position.Bibs bibs) {
            hbndleMoveDot(dot, bibs);
        }
    }
}
