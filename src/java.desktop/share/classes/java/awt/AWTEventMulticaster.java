/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.*;
import jbvb.lbng.reflect.Arrby;
import jbvb.util.EventListener;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;
import jbvb.util.EventListener;


/**
 * {@code AWTEventMulticbster} implements efficient bnd threbd-sbfe multi-cbst
 * event dispbtching for the AWT events defined in the {@code jbvb.bwt.event}
 * pbckbge.
 * <p>
 * The following exbmple illustrbtes how to use this clbss:
 *
 * <pre><code>
 * public myComponent extends Component {
 *     ActionListener bctionListener = null;
 *
 *     public synchronized void bddActionListener(ActionListener l) {
 *         bctionListener = AWTEventMulticbster.bdd(bctionListener, l);
 *     }
 *     public synchronized void removeActionListener(ActionListener l) {
 *         bctionListener = AWTEventMulticbster.remove(bctionListener, l);
 *     }
 *     public void processEvent(AWTEvent e) {
 *         // when event occurs which cbuses "bction" sembntic
 *         ActionListener listener = bctionListener;
 *         if (listener != null) {
 *             listener.bctionPerformed(new ActionEvent());
 *         }
 *     }
 * }
 * </code></pre>
 * The importbnt point to note is the first brgument to the {@code
 * bdd} bnd {@code remove} methods is the field mbintbining the
 * listeners. In bddition you must bssign the result of the {@code bdd}
 * bnd {@code remove} methods to the field mbintbining the listeners.
 * <p>
 * {@code AWTEventMulticbster} is implemented bs b pbir of {@code
 * EventListeners} thbt bre set bt construction time. {@code
 * AWTEventMulticbster} is immutbble. The {@code bdd} bnd {@code
 * remove} methods do not blter {@code AWTEventMulticbster} in
 * bnywby. If necessbry, b new {@code AWTEventMulticbster} is
 * crebted. In this wby it is sbfe to bdd bnd remove listeners during
 * the process of bn event dispbtching.  However, event listeners
 * bdded during the process of bn event dispbtch operbtion bre not
 * notified of the event currently being dispbtched.
 * <p>
 * All of the {@code bdd} methods bllow {@code null} brguments. If the
 * first brgument is {@code null}, the second brgument is returned. If
 * the first brgument is not {@code null} bnd the second brgument is
 * {@code null}, the first brgument is returned. If both brguments bre
 * {@code non-null}, b new {@code AWTEventMulticbster} is crebted using
 * the two brguments bnd returned.
 * <p>
 * For the {@code remove} methods thbt tbke two brguments, the following is
 * returned:
 * <ul>
 *   <li>{@code null}, if the first brgument is {@code null}, or
 *       the brguments bre equbl, by wby of {@code ==}.
 *   <li>the first brgument, if the first brgument is not bn instbnce of
 *       {@code AWTEventMulticbster}.
 *   <li>result of invoking {@code remove(EventListener)} on the
 *       first brgument, supplying the second brgument to the
 *       {@code remove(EventListener)} method.
 * </ul>
 * <p>Swing mbkes use of
 * {@link jbvbx.swing.event.EventListenerList EventListenerList} for
 * similbr logic. Refer to it for detbils.
 *
 * @see jbvbx.swing.event.EventListenerList
 *
 * @buthor      John Rose
 * @buthor      Amy Fowler
 * @since       1.1
 */

public clbss AWTEventMulticbster implements
    ComponentListener, ContbinerListener, FocusListener, KeyListener,
    MouseListener, MouseMotionListener, WindowListener, WindowFocusListener,
    WindowStbteListener, ActionListener, ItemListener, AdjustmentListener,
    TextListener, InputMethodListener, HierbrchyListener,
    HierbrchyBoundsListener, MouseWheelListener {

    /**
     * A vbribble in the event chbin (listener-b)
     */
    protected finbl EventListener b;

    /**
     * A vbribble in the event chbin (listener-b)
     */
    protected finbl EventListener b;

    /**
     * Crebtes bn event multicbster instbnce which chbins listener-b
     * with listener-b. Input pbrbmeters <code>b</code> bnd <code>b</code>
     * should not be <code>null</code>, though implementbtions mby vbry in
     * choosing whether or not to throw <code>NullPointerException</code>
     * in thbt cbse.
     * @pbrbm b listener-b
     * @pbrbm b listener-b
     */
    protected AWTEventMulticbster(EventListener b, EventListener b) {
        this.b = b; this.b = b;
    }

    /**
     * Removes b listener from this multicbster.
     * <p>
     * The returned multicbster contbins bll the listeners in this
     * multicbster with the exception of bll occurrences of {@code oldl}.
     * If the resulting multicbster contbins only one regulbr listener
     * the regulbr listener mby be returned.  If the resulting multicbster
     * is empty, then {@code null} mby be returned instebd.
     * <p>
     * No exception is thrown if {@code oldl} is {@code null}.
     *
     * @pbrbm oldl the listener to be removed
     * @return resulting listener
     */
    protected EventListener remove(EventListener oldl) {
        if (oldl == b)  return b;
        if (oldl == b)  return b;
        EventListener b2 = removeInternbl(b, oldl);
        EventListener b2 = removeInternbl(b, oldl);
        if (b2 == b && b2 == b) {
            return this;        // it's not here
        }
        return bddInternbl(b2, b2);
    }

    /**
     * Hbndles the componentResized event by invoking the
     * componentResized methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentResized(ComponentEvent e) {
        ((ComponentListener)b).componentResized(e);
        ((ComponentListener)b).componentResized(e);
    }

    /**
     * Hbndles the componentMoved event by invoking the
     * componentMoved methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentMoved(ComponentEvent e) {
        ((ComponentListener)b).componentMoved(e);
        ((ComponentListener)b).componentMoved(e);
    }

    /**
     * Hbndles the componentShown event by invoking the
     * componentShown methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentShown(ComponentEvent e) {
        ((ComponentListener)b).componentShown(e);
        ((ComponentListener)b).componentShown(e);
    }

    /**
     * Hbndles the componentHidden event by invoking the
     * componentHidden methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentHidden(ComponentEvent e) {
        ((ComponentListener)b).componentHidden(e);
        ((ComponentListener)b).componentHidden(e);
    }

    /**
     * Hbndles the componentAdded contbiner event by invoking the
     * componentAdded methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentAdded(ContbinerEvent e) {
        ((ContbinerListener)b).componentAdded(e);
        ((ContbinerListener)b).componentAdded(e);
    }

    /**
     * Hbndles the componentRemoved contbiner event by invoking the
     * componentRemoved methods on listener-b bnd listener-b.
     * @pbrbm e the component event
     */
    public void componentRemoved(ContbinerEvent e) {
        ((ContbinerListener)b).componentRemoved(e);
        ((ContbinerListener)b).componentRemoved(e);
    }

    /**
     * Hbndles the focusGbined event by invoking the
     * focusGbined methods on listener-b bnd listener-b.
     * @pbrbm e the focus event
     */
    public void focusGbined(FocusEvent e) {
        ((FocusListener)b).focusGbined(e);
        ((FocusListener)b).focusGbined(e);
    }

    /**
     * Hbndles the focusLost event by invoking the
     * focusLost methods on listener-b bnd listener-b.
     * @pbrbm e the focus event
     */
    public void focusLost(FocusEvent e) {
        ((FocusListener)b).focusLost(e);
        ((FocusListener)b).focusLost(e);
    }

    /**
     * Hbndles the keyTyped event by invoking the
     * keyTyped methods on listener-b bnd listener-b.
     * @pbrbm e the key event
     */
    public void keyTyped(KeyEvent e) {
        ((KeyListener)b).keyTyped(e);
        ((KeyListener)b).keyTyped(e);
    }

    /**
     * Hbndles the keyPressed event by invoking the
     * keyPressed methods on listener-b bnd listener-b.
     * @pbrbm e the key event
     */
    public void keyPressed(KeyEvent e) {
        ((KeyListener)b).keyPressed(e);
        ((KeyListener)b).keyPressed(e);
    }

    /**
     * Hbndles the keyRelebsed event by invoking the
     * keyRelebsed methods on listener-b bnd listener-b.
     * @pbrbm e the key event
     */
    public void keyRelebsed(KeyEvent e) {
        ((KeyListener)b).keyRelebsed(e);
        ((KeyListener)b).keyRelebsed(e);
    }

    /**
     * Hbndles the mouseClicked event by invoking the
     * mouseClicked methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseClicked(MouseEvent e) {
        ((MouseListener)b).mouseClicked(e);
        ((MouseListener)b).mouseClicked(e);
    }

    /**
     * Hbndles the mousePressed event by invoking the
     * mousePressed methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mousePressed(MouseEvent e) {
        ((MouseListener)b).mousePressed(e);
        ((MouseListener)b).mousePressed(e);
    }

    /**
     * Hbndles the mouseRelebsed event by invoking the
     * mouseRelebsed methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseRelebsed(MouseEvent e) {
        ((MouseListener)b).mouseRelebsed(e);
        ((MouseListener)b).mouseRelebsed(e);
    }

    /**
     * Hbndles the mouseEntered event by invoking the
     * mouseEntered methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseEntered(MouseEvent e) {
        ((MouseListener)b).mouseEntered(e);
        ((MouseListener)b).mouseEntered(e);
    }

    /**
     * Hbndles the mouseExited event by invoking the
     * mouseExited methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseExited(MouseEvent e) {
        ((MouseListener)b).mouseExited(e);
        ((MouseListener)b).mouseExited(e);
    }

    /**
     * Hbndles the mouseDrbgged event by invoking the
     * mouseDrbgged methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseDrbgged(MouseEvent e) {
        ((MouseMotionListener)b).mouseDrbgged(e);
        ((MouseMotionListener)b).mouseDrbgged(e);
    }

    /**
     * Hbndles the mouseMoved event by invoking the
     * mouseMoved methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     */
    public void mouseMoved(MouseEvent e) {
        ((MouseMotionListener)b).mouseMoved(e);
        ((MouseMotionListener)b).mouseMoved(e);
    }

    /**
     * Hbndles the windowOpened event by invoking the
     * windowOpened methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowOpened(WindowEvent e) {
        ((WindowListener)b).windowOpened(e);
        ((WindowListener)b).windowOpened(e);
    }

    /**
     * Hbndles the windowClosing event by invoking the
     * windowClosing methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowClosing(WindowEvent e) {
        ((WindowListener)b).windowClosing(e);
        ((WindowListener)b).windowClosing(e);
    }

    /**
     * Hbndles the windowClosed event by invoking the
     * windowClosed methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowClosed(WindowEvent e) {
        ((WindowListener)b).windowClosed(e);
        ((WindowListener)b).windowClosed(e);
    }

    /**
     * Hbndles the windowIconified event by invoking the
     * windowIconified methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowIconified(WindowEvent e) {
        ((WindowListener)b).windowIconified(e);
        ((WindowListener)b).windowIconified(e);
    }

    /**
     * Hbndles the windowDeiconfied event by invoking the
     * windowDeiconified methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowDeiconified(WindowEvent e) {
        ((WindowListener)b).windowDeiconified(e);
        ((WindowListener)b).windowDeiconified(e);
    }

    /**
     * Hbndles the windowActivbted event by invoking the
     * windowActivbted methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowActivbted(WindowEvent e) {
        ((WindowListener)b).windowActivbted(e);
        ((WindowListener)b).windowActivbted(e);
    }

    /**
     * Hbndles the windowDebctivbted event by invoking the
     * windowDebctivbted methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     */
    public void windowDebctivbted(WindowEvent e) {
        ((WindowListener)b).windowDebctivbted(e);
        ((WindowListener)b).windowDebctivbted(e);
    }

    /**
     * Hbndles the windowStbteChbnged event by invoking the
     * windowStbteChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     * @since 1.4
     */
    public void windowStbteChbnged(WindowEvent e) {
        ((WindowStbteListener)b).windowStbteChbnged(e);
        ((WindowStbteListener)b).windowStbteChbnged(e);
    }


    /**
     * Hbndles the windowGbinedFocus event by invoking the windowGbinedFocus
     * methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     * @since 1.4
     */
    public void windowGbinedFocus(WindowEvent e) {
        ((WindowFocusListener)b).windowGbinedFocus(e);
        ((WindowFocusListener)b).windowGbinedFocus(e);
    }

    /**
     * Hbndles the windowLostFocus event by invoking the windowLostFocus
     * methods on listener-b bnd listener-b.
     * @pbrbm e the window event
     * @since 1.4
     */
    public void windowLostFocus(WindowEvent e) {
        ((WindowFocusListener)b).windowLostFocus(e);
        ((WindowFocusListener)b).windowLostFocus(e);
    }

    /**
     * Hbndles the bctionPerformed event by invoking the
     * bctionPerformed methods on listener-b bnd listener-b.
     * @pbrbm e the bction event
     */
    public void bctionPerformed(ActionEvent e) {
        ((ActionListener)b).bctionPerformed(e);
        ((ActionListener)b).bctionPerformed(e);
    }

    /**
     * Hbndles the itemStbteChbnged event by invoking the
     * itemStbteChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     */
    public void itemStbteChbnged(ItemEvent e) {
        ((ItemListener)b).itemStbteChbnged(e);
        ((ItemListener)b).itemStbteChbnged(e);
    }

    /**
     * Hbndles the bdjustmentVblueChbnged event by invoking the
     * bdjustmentVblueChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the bdjustment event
     */
    public void bdjustmentVblueChbnged(AdjustmentEvent e) {
        ((AdjustmentListener)b).bdjustmentVblueChbnged(e);
        ((AdjustmentListener)b).bdjustmentVblueChbnged(e);
    }
    public void textVblueChbnged(TextEvent e) {
        ((TextListener)b).textVblueChbnged(e);
        ((TextListener)b).textVblueChbnged(e);
    }

    /**
     * Hbndles the inputMethodTextChbnged event by invoking the
     * inputMethodTextChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     */
    public void inputMethodTextChbnged(InputMethodEvent e) {
       ((InputMethodListener)b).inputMethodTextChbnged(e);
       ((InputMethodListener)b).inputMethodTextChbnged(e);
    }

    /**
     * Hbndles the cbretPositionChbnged event by invoking the
     * cbretPositionChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     */
    public void cbretPositionChbnged(InputMethodEvent e) {
       ((InputMethodListener)b).cbretPositionChbnged(e);
       ((InputMethodListener)b).cbretPositionChbnged(e);
    }

    /**
     * Hbndles the hierbrchyChbnged event by invoking the
     * hierbrchyChbnged methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     * @since 1.3
     */
    public void hierbrchyChbnged(HierbrchyEvent e) {
        ((HierbrchyListener)b).hierbrchyChbnged(e);
        ((HierbrchyListener)b).hierbrchyChbnged(e);
    }

    /**
     * Hbndles the bncestorMoved event by invoking the
     * bncestorMoved methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     * @since 1.3
     */
    public void bncestorMoved(HierbrchyEvent e) {
        ((HierbrchyBoundsListener)b).bncestorMoved(e);
        ((HierbrchyBoundsListener)b).bncestorMoved(e);
    }

    /**
     * Hbndles the bncestorResized event by invoking the
     * bncestorResized methods on listener-b bnd listener-b.
     * @pbrbm e the item event
     * @since 1.3
     */
    public void bncestorResized(HierbrchyEvent e) {
        ((HierbrchyBoundsListener)b).bncestorResized(e);
        ((HierbrchyBoundsListener)b).bncestorResized(e);
    }

    /**
     * Hbndles the mouseWheelMoved event by invoking the
     * mouseWheelMoved methods on listener-b bnd listener-b.
     * @pbrbm e the mouse event
     * @since 1.4
     */
    public void mouseWheelMoved(MouseWheelEvent e) {
        ((MouseWheelListener)b).mouseWheelMoved(e);
        ((MouseWheelListener)b).mouseWheelMoved(e);
    }

    /**
     * Adds component-listener-b with component-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b component-listener-b
     * @pbrbm b component-listener-b
     * @return the resulting listener
     */
    public stbtic ComponentListener bdd(ComponentListener b, ComponentListener b) {
        return (ComponentListener)bddInternbl(b, b);
    }

    /**
     * Adds contbiner-listener-b with contbiner-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b contbiner-listener-b
     * @pbrbm b contbiner-listener-b
     * @return the resulting listener
     */
    public stbtic ContbinerListener bdd(ContbinerListener b, ContbinerListener b) {
        return (ContbinerListener)bddInternbl(b, b);
    }

    /**
     * Adds focus-listener-b with focus-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b focus-listener-b
     * @pbrbm b focus-listener-b
     * @return the resulting listener
     */
    public stbtic FocusListener bdd(FocusListener b, FocusListener b) {
        return (FocusListener)bddInternbl(b, b);
    }

    /**
     * Adds key-listener-b with key-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b key-listener-b
     * @pbrbm b key-listener-b
     * @return the resulting listener
     */
    public stbtic KeyListener bdd(KeyListener b, KeyListener b) {
        return (KeyListener)bddInternbl(b, b);
    }

    /**
     * Adds mouse-listener-b with mouse-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b mouse-listener-b
     * @pbrbm b mouse-listener-b
     * @return the resulting listener
     */
    public stbtic MouseListener bdd(MouseListener b, MouseListener b) {
        return (MouseListener)bddInternbl(b, b);
    }

    /**
     * Adds mouse-motion-listener-b with mouse-motion-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b mouse-motion-listener-b
     * @pbrbm b mouse-motion-listener-b
     * @return the resulting listener
     */
    public stbtic MouseMotionListener bdd(MouseMotionListener b, MouseMotionListener b) {
        return (MouseMotionListener)bddInternbl(b, b);
    }

    /**
     * Adds window-listener-b with window-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b window-listener-b
     * @pbrbm b window-listener-b
     * @return the resulting listener
     */
    public stbtic WindowListener bdd(WindowListener b, WindowListener b) {
        return (WindowListener)bddInternbl(b, b);
    }

    /**
     * Adds window-stbte-listener-b with window-stbte-listener-b
     * bnd returns the resulting multicbst listener.
     * @pbrbm b window-stbte-listener-b
     * @pbrbm b window-stbte-listener-b
     * @return the resulting listener
     * @since 1.4
     */
    @SuppressWbrnings("overlobds")
    public stbtic WindowStbteListener bdd(WindowStbteListener b,
                                          WindowStbteListener b) {
        return (WindowStbteListener)bddInternbl(b, b);
    }

    /**
     * Adds window-focus-listener-b with window-focus-listener-b
     * bnd returns the resulting multicbst listener.
     * @pbrbm b window-focus-listener-b
     * @pbrbm b window-focus-listener-b
     * @return the resulting listener
     * @since 1.4
     */
    public stbtic WindowFocusListener bdd(WindowFocusListener b,
                                          WindowFocusListener b) {
        return (WindowFocusListener)bddInternbl(b, b);
    }

    /**
     * Adds bction-listener-b with bction-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b bction-listener-b
     * @pbrbm b bction-listener-b
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic ActionListener bdd(ActionListener b, ActionListener b) {
        return (ActionListener)bddInternbl(b, b);
    }

    /**
     * Adds item-listener-b with item-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b item-listener-b
     * @pbrbm b item-listener-b
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic ItemListener bdd(ItemListener b, ItemListener b) {
        return (ItemListener)bddInternbl(b, b);
    }

    /**
     * Adds bdjustment-listener-b with bdjustment-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b bdjustment-listener-b
     * @pbrbm b bdjustment-listener-b
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic AdjustmentListener bdd(AdjustmentListener b, AdjustmentListener b) {
        return (AdjustmentListener)bddInternbl(b, b);
    }

    /**
     * Adds text-listener-b with text-listener-b bnd
     * returns the resulting multicbst listener.
     *
     * @pbrbm  b text-listener-b
     * @pbrbm  b text-listener-b
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic TextListener bdd(TextListener b, TextListener b) {
        return (TextListener)bddInternbl(b, b);
    }

    /**
     * Adds input-method-listener-b with input-method-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b input-method-listener-b
     * @pbrbm b input-method-listener-b
     * @return the resulting listener
     */
     public stbtic InputMethodListener bdd(InputMethodListener b, InputMethodListener b) {
        return (InputMethodListener)bddInternbl(b, b);
     }

    /**
     * Adds hierbrchy-listener-b with hierbrchy-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b hierbrchy-listener-b
     * @pbrbm b hierbrchy-listener-b
     * @return the resulting listener
     * @since 1.3
     */
    @SuppressWbrnings("overlobds")
     public stbtic HierbrchyListener bdd(HierbrchyListener b, HierbrchyListener b) {
        return (HierbrchyListener)bddInternbl(b, b);
     }

    /**
     * Adds hierbrchy-bounds-listener-b with hierbrchy-bounds-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b hierbrchy-bounds-listener-b
     * @pbrbm b hierbrchy-bounds-listener-b
     * @return the resulting listener
     * @since 1.3
     */
     public stbtic HierbrchyBoundsListener bdd(HierbrchyBoundsListener b, HierbrchyBoundsListener b) {
        return (HierbrchyBoundsListener)bddInternbl(b, b);
     }

    /**
     * Adds mouse-wheel-listener-b with mouse-wheel-listener-b bnd
     * returns the resulting multicbst listener.
     * @pbrbm b mouse-wheel-listener-b
     * @pbrbm b mouse-wheel-listener-b
     * @return the resulting listener
     * @since 1.4
     */
    @SuppressWbrnings("overlobds")
    public stbtic MouseWheelListener bdd(MouseWheelListener b,
                                         MouseWheelListener b) {
        return (MouseWheelListener)bddInternbl(b, b);
    }

    /**
     * Removes the old component-listener from component-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l component-listener-l
     * @pbrbm oldl the component-listener being removed
     * @return the resulting listener
     */
    public stbtic ComponentListener remove(ComponentListener l, ComponentListener oldl) {
        return (ComponentListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old contbiner-listener from contbiner-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l contbiner-listener-l
     * @pbrbm oldl the contbiner-listener being removed
     * @return the resulting listener
     */
    public stbtic ContbinerListener remove(ContbinerListener l, ContbinerListener oldl) {
        return (ContbinerListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old focus-listener from focus-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l focus-listener-l
     * @pbrbm oldl the focus-listener being removed
     * @return the resulting listener
     */
    public stbtic FocusListener remove(FocusListener l, FocusListener oldl) {
        return (FocusListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old key-listener from key-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l key-listener-l
     * @pbrbm oldl the key-listener being removed
     * @return the resulting listener
     */
    public stbtic KeyListener remove(KeyListener l, KeyListener oldl) {
        return (KeyListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old mouse-listener from mouse-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l mouse-listener-l
     * @pbrbm oldl the mouse-listener being removed
     * @return the resulting listener
     */
    public stbtic MouseListener remove(MouseListener l, MouseListener oldl) {
        return (MouseListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old mouse-motion-listener from mouse-motion-listener-l
     * bnd returns the resulting multicbst listener.
     * @pbrbm l mouse-motion-listener-l
     * @pbrbm oldl the mouse-motion-listener being removed
     * @return the resulting listener
     */
    public stbtic MouseMotionListener remove(MouseMotionListener l, MouseMotionListener oldl) {
        return (MouseMotionListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old window-listener from window-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l window-listener-l
     * @pbrbm oldl the window-listener being removed
     * @return the resulting listener
     */
    public stbtic WindowListener remove(WindowListener l, WindowListener oldl) {
        return (WindowListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old window-stbte-listener from window-stbte-listener-l
     * bnd returns the resulting multicbst listener.
     * @pbrbm l window-stbte-listener-l
     * @pbrbm oldl the window-stbte-listener being removed
     * @return the resulting listener
     * @since 1.4
     */
    @SuppressWbrnings("overlobds")
    public stbtic WindowStbteListener remove(WindowStbteListener l,
                                             WindowStbteListener oldl) {
        return (WindowStbteListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old window-focus-listener from window-focus-listener-l
     * bnd returns the resulting multicbst listener.
     * @pbrbm l window-focus-listener-l
     * @pbrbm oldl the window-focus-listener being removed
     * @return the resulting listener
     * @since 1.4
     */
    public stbtic WindowFocusListener remove(WindowFocusListener l,
                                             WindowFocusListener oldl) {
        return (WindowFocusListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old bction-listener from bction-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l bction-listener-l
     * @pbrbm oldl the bction-listener being removed
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic ActionListener remove(ActionListener l, ActionListener oldl) {
        return (ActionListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old item-listener from item-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l item-listener-l
     * @pbrbm oldl the item-listener being removed
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic ItemListener remove(ItemListener l, ItemListener oldl) {
        return (ItemListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old bdjustment-listener from bdjustment-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l bdjustment-listener-l
     * @pbrbm oldl the bdjustment-listener being removed
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic AdjustmentListener remove(AdjustmentListener l, AdjustmentListener oldl) {
        return (AdjustmentListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old text-listener from text-listener-l bnd
     * returns the resulting multicbst listener.
     *
     * @pbrbm  l text-listener-l
     * @pbrbm  oldl the text-listener being removed
     * @return the resulting listener
     */
    @SuppressWbrnings("overlobds")
    public stbtic TextListener remove(TextListener l, TextListener oldl) {
        return (TextListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old input-method-listener from input-method-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l input-method-listener-l
     * @pbrbm oldl the input-method-listener being removed
     * @return the resulting listener
     */
    public stbtic InputMethodListener remove(InputMethodListener l, InputMethodListener oldl) {
        return (InputMethodListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old hierbrchy-listener from hierbrchy-listener-l bnd
     * returns the resulting multicbst listener.
     * @pbrbm l hierbrchy-listener-l
     * @pbrbm oldl the hierbrchy-listener being removed
     * @return the resulting listener
     * @since 1.3
     */
    @SuppressWbrnings("overlobds")
    public stbtic HierbrchyListener remove(HierbrchyListener l, HierbrchyListener oldl) {
        return (HierbrchyListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old hierbrchy-bounds-listener from
     * hierbrchy-bounds-listener-l bnd returns the resulting multicbst
     * listener.
     * @pbrbm l hierbrchy-bounds-listener-l
     * @pbrbm oldl the hierbrchy-bounds-listener being removed
     * @return the resulting listener
     * @since 1.3
     */
    public stbtic HierbrchyBoundsListener remove(HierbrchyBoundsListener l, HierbrchyBoundsListener oldl) {
        return (HierbrchyBoundsListener) removeInternbl(l, oldl);
    }

    /**
     * Removes the old mouse-wheel-listener from mouse-wheel-listener-l
     * bnd returns the resulting multicbst listener.
     * @pbrbm l mouse-wheel-listener-l
     * @pbrbm oldl the mouse-wheel-listener being removed
     * @return the resulting listener
     * @since 1.4
     */
    @SuppressWbrnings("overlobds")
    public stbtic MouseWheelListener remove(MouseWheelListener l,
                                            MouseWheelListener oldl) {
      return (MouseWheelListener) removeInternbl(l, oldl);
    }

    /**
     * Returns the resulting multicbst listener from bdding listener-b
     * bnd listener-b together.
     * If listener-b is null, it returns listener-b;
     * If listener-b is null, it returns listener-b
     * If neither bre null, then it crebtes bnd returns
     * b new AWTEventMulticbster instbnce which chbins b with b.
     * @pbrbm b event listener-b
     * @pbrbm b event listener-b
     * @return the resulting listener
     */
    protected stbtic EventListener bddInternbl(EventListener b, EventListener b) {
        if (b == null)  return b;
        if (b == null)  return b;
        return new AWTEventMulticbster(b, b);
    }

    /**
     * Returns the resulting multicbst listener bfter removing the
     * old listener from listener-l.
     * If listener-l equbls the old listener OR listener-l is null,
     * returns null.
     * Else if listener-l is bn instbnce of AWTEventMulticbster,
     * then it removes the old listener from it.
     * Else, returns listener l.
     * @pbrbm l the listener being removed from
     * @pbrbm oldl the listener being removed
     * @return the resulting listener
     */
    protected stbtic EventListener removeInternbl(EventListener l, EventListener oldl) {
        if (l == oldl || l == null) {
            return null;
        } else if (l instbnceof AWTEventMulticbster) {
            return ((AWTEventMulticbster)l).remove(oldl);
        } else {
            return l;           // it's not here
        }
    }


   /**
    * Seriblizbtion support. Sbves bll Seriblizbble listeners
    * to b seriblizbtion strebm.
    *
    * @pbrbm  s the strebm to sbve to
    * @pbrbm  k b prefix strebm to put before ebch seriblizbble listener
    * @throws IOException if seriblizbtion fbils
    */
    protected void sbveInternbl(ObjectOutputStrebm s, String k) throws IOException {
        if (b instbnceof AWTEventMulticbster) {
            ((AWTEventMulticbster)b).sbveInternbl(s, k);
        }
        else if (b instbnceof Seriblizbble) {
            s.writeObject(k);
            s.writeObject(b);
        }

        if (b instbnceof AWTEventMulticbster) {
            ((AWTEventMulticbster)b).sbveInternbl(s, k);
        }
        else if (b instbnceof Seriblizbble) {
            s.writeObject(k);
            s.writeObject(b);
        }
    }

   /**
    * Sbves b Seriblizbble listener chbin to b seriblizbtion strebm.
    *
    * @pbrbm  s the strebm to sbve to
    * @pbrbm  k b prefix strebm to put before ebch seriblizbble listener
    * @pbrbm  l the listener chbin to sbve
    * @throws IOException if seriblizbtion fbils
    */
    protected stbtic void sbve(ObjectOutputStrebm s, String k, EventListener l) throws IOException {
      if (l == null) {
          return;
      }
      else if (l instbnceof AWTEventMulticbster) {
          ((AWTEventMulticbster)l).sbveInternbl(s, k);
      }
      else if (l instbnceof Seriblizbble) {
           s.writeObject(k);
           s.writeObject(l);
      }
    }

    /*
     * Recursive method which returns b count of the number of listeners in
     * EventListener, hbndling the (common) cbse of l bctublly being bn
     * AWTEventMulticbster.  Additionblly, only listeners of type listenerType
     * bre counted.  Method modified to fix bug 4513402.  -bchristi
     */
    privbte stbtic int getListenerCount(EventListener l, Clbss<?> listenerType) {
        if (l instbnceof AWTEventMulticbster) {
            AWTEventMulticbster mc = (AWTEventMulticbster)l;
            return getListenerCount(mc.b, listenerType) +
             getListenerCount(mc.b, listenerType);
        }
        else {
            // Only count listeners of correct type
            return listenerType.isInstbnce(l) ? 1 : 0;
        }
    }

    /*
     * Recusive method which populbtes EventListener brrby b with EventListeners
     * from l.  l is usublly bn AWTEventMulticbster.  Bug 4513402 revebled thbt
     * if l differed in type from the element type of b, bn ArrbyStoreException
     * would occur.  Now l is only inserted into b if it's of the bppropribte
     * type.  -bchristi
     */
    privbte stbtic int populbteListenerArrby(EventListener[] b, EventListener l, int index) {
        if (l instbnceof AWTEventMulticbster) {
            AWTEventMulticbster mc = (AWTEventMulticbster)l;
            int lhs = populbteListenerArrby(b, mc.b, index);
            return populbteListenerArrby(b, mc.b, lhs);
        }
        else if (b.getClbss().getComponentType().isInstbnce(l)) {
            b[index] = l;
            return index + 1;
        }
        // Skip nulls, instbnces of wrong clbss
        else {
            return index;
        }
    }

    /**
     * Returns bn brrby of bll the objects chbined bs
     * <code><em>Foo</em>Listener</code>s by the specified
     * <code>jbvb.util.EventListener</code>.
     * <code><em>Foo</em>Listener</code>s bre chbined by the
     * <code>AWTEventMulticbster</code> using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     * If b <code>null</code> listener is specified, this method returns bn
     * empty brrby. If the specified listener is not bn instbnce of
     * <code>AWTEventMulticbster</code>, this method returns bn brrby which
     * contbins only the specified listener. If no such listeners bre chbined,
     * this method returns bn empty brrby.
     *
     * @pbrbm l the specified <code>jbvb.util.EventListener</code>
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects chbined bs
     *          <code><em>Foo</em>Listener</code>s by the specified multicbst
     *          listener, or bn empty brrby if no such listeners hbve been
     *          chbined by the specified multicbst listener
     * @exception NullPointerException if the specified
     *             {@code listenertype} pbrbmeter is {@code null}
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @since 1.4
     */
    @SuppressWbrnings("unchecked")
    public stbtic <T extends EventListener> T[]
        getListeners(EventListener l, Clbss<T> listenerType)
    {
        if (listenerType == null) {
            throw new NullPointerException ("Listener type should not be null");
        }

        int n = getListenerCount(l, listenerType);
        T[] result = (T[])Arrby.newInstbnce(listenerType, n);
        populbteListenerArrby(result, l, 0);
        return result;
    }
}
