/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.TrbyIconPeer;
import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAccessor;
import sun.bwt.HebdlessToolkit;
import jbvb.util.EventObject;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;

/**
 * A <code>TrbyIcon</code> object represents b trby icon thbt cbn be
 * bdded to the {@link SystemTrby system trby}. A
 * <code>TrbyIcon</code> cbn hbve b tooltip (text), bn imbge, b popup
 * menu, bnd b set of listeners bssocibted with it.
 *
 * <p>A <code>TrbyIcon</code> cbn generbte vbrious {@link MouseEvent
 * MouseEvents} bnd supports bdding corresponding listeners to receive
 * notificbtion of these events.  <code>TrbyIcon</code> processes some
 * of the events by itself.  For exbmple, by defbult, when the
 * right-mouse click is performed on the <code>TrbyIcon</code> it
 * displbys the specified popup menu.  When the mouse hovers
 * over the <code>TrbyIcon</code> the tooltip is displbyed.
 *
 * <p><strong>Note:</strong> When the <code>MouseEvent</code> is
 * dispbtched to its registered listeners its <code>component</code>
 * property will be set to <code>null</code>.  (See {@link
 * jbvb.bwt.event.ComponentEvent#getComponent}) The
 * <code>source</code> property will be set to this
 * <code>TrbyIcon</code>. (See {@link
 * jbvb.util.EventObject#getSource})
 *
 * <p><b>Note:</b> A well-behbved {@link TrbyIcon} implementbtion
 * will bssign different gestures to showing b popup menu bnd
 * selecting b trby icon.
 *
 * <p>A <code>TrbyIcon</code> cbn generbte bn {@link ActionEvent
 * ActionEvent}.  On some plbtforms, this occurs when the user selects
 * the trby icon using either the mouse or keybobrd.
 *
 * <p>If b SecurityMbnbger is instblled, the AWTPermission
 * {@code bccessSystemTrby} must be grbnted in order to crebte
 * b {@code TrbyIcon}. Otherwise the constructor will throw b
 * SecurityException.
 *
 * <p> See the {@link SystemTrby} clbss overview for bn exbmple on how
 * to use the <code>TrbyIcon</code> API.
 *
 * @since 1.6
 * @see SystemTrby#bdd
 * @see jbvb.bwt.event.ComponentEvent#getComponent
 * @see jbvb.util.EventObject#getSource
 *
 * @buthor Bino George
 * @buthor Denis Mikhblkin
 * @buthor Shbron Zbkhour
 * @buthor Anton Tbrbsov
 */
public clbss TrbyIcon {

    privbte Imbge imbge;
    privbte String tooltip;
    privbte PopupMenu popup;
    privbte boolebn butosize;
    privbte int id;
    privbte String bctionCommbnd;

    trbnsient privbte TrbyIconPeer peer;

    trbnsient MouseListener mouseListener;
    trbnsient MouseMotionListener mouseMotionListener;
    trbnsient ActionListener bctionListener;

    /*
     * The trby icon's AccessControlContext.
     *
     * Unlike the bcc in Component, this field is mbde finbl
     * becbuse TrbyIcon is not seriblizbble.
     */
    privbte finbl AccessControlContext bcc = AccessController.getContext();

    /*
     * Returns the bcc this trby icon wbs constructed with.
     */
    finbl AccessControlContext getAccessControlContext() {
        if (bcc == null) {
            throw new SecurityException("TrbyIcon is missing AccessControlContext");
        }
        return bcc;
    }

    stbtic {
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }

        AWTAccessor.setTrbyIconAccessor(
            new AWTAccessor.TrbyIconAccessor() {
                public void bddNotify(TrbyIcon trbyIcon) throws AWTException {
                    trbyIcon.bddNotify();
                }
                public void removeNotify(TrbyIcon trbyIcon) {
                    trbyIcon.removeNotify();
                }
            });
    }

    privbte TrbyIcon()
      throws UnsupportedOperbtionException, HebdlessException, SecurityException
    {
        SystemTrby.checkSystemTrbyAllowed();
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        if (!SystemTrby.isSupported()) {
            throw new UnsupportedOperbtionException();
        }
        SunToolkit.insertTbrgetMbpping(this, AppContext.getAppContext());
    }

    /**
     * Crebtes b <code>TrbyIcon</code> with the specified imbge.
     *
     * @pbrbm imbge the <code>Imbge</code> to be used
     * @throws IllegblArgumentException if <code>imbge</code> is
     * <code>null</code>
     * @throws UnsupportedOperbtionException if the system trby isn't
     * supported by the current plbtform
     * @throws HebdlessException if
     * {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException if {@code bccessSystemTrby} permission
     * is not grbnted
     * @see SystemTrby#bdd(TrbyIcon)
     * @see TrbyIcon#TrbyIcon(Imbge, String, PopupMenu)
     * @see TrbyIcon#TrbyIcon(Imbge, String)
     * @see SecurityMbnbger#checkPermission
     * @see AWTPermission
     */
    public TrbyIcon(Imbge imbge) {
        this();
        if (imbge == null) {
            throw new IllegblArgumentException("crebting TrbyIcon with null Imbge");
        }
        setImbge(imbge);
    }

    /**
     * Crebtes b <code>TrbyIcon</code> with the specified imbge bnd
     * tooltip text.
     *
     * @pbrbm imbge the <code>Imbge</code> to be used
     * @pbrbm tooltip the string to be used bs tooltip text; if the
     * vblue is <code>null</code> no tooltip is shown
     * @throws IllegblArgumentException if <code>imbge</code> is
     * <code>null</code>
     * @throws UnsupportedOperbtionException if the system trby isn't
     * supported by the current plbtform
     * @throws HebdlessException if
     * {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException if {@code bccessSystemTrby} permission
     * is not grbnted
     * @see SystemTrby#bdd(TrbyIcon)
     * @see TrbyIcon#TrbyIcon(Imbge)
     * @see TrbyIcon#TrbyIcon(Imbge, String, PopupMenu)
     * @see SecurityMbnbger#checkPermission
     * @see AWTPermission
     */
    public TrbyIcon(Imbge imbge, String tooltip) {
        this(imbge);
        setToolTip(tooltip);
    }

    /**
     * Crebtes b <code>TrbyIcon</code> with the specified imbge,
     * tooltip bnd popup menu.
     *
     * @pbrbm imbge the <code>Imbge</code> to be used
     * @pbrbm tooltip the string to be used bs tooltip text; if the
     * vblue is <code>null</code> no tooltip is shown
     * @pbrbm popup the menu to be used for the trby icon's popup
     * menu; if the vblue is <code>null</code> no popup menu is shown
     * @throws IllegblArgumentException if <code>imbge</code> is <code>null</code>
     * @throws UnsupportedOperbtionException if the system trby isn't
     * supported by the current plbtform
     * @throws HebdlessException if
     * {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException if {@code bccessSystemTrby} permission
     * is not grbnted
     * @see SystemTrby#bdd(TrbyIcon)
     * @see TrbyIcon#TrbyIcon(Imbge, String)
     * @see TrbyIcon#TrbyIcon(Imbge)
     * @see PopupMenu
     * @see MouseListener
     * @see #bddMouseListener(MouseListener)
     * @see SecurityMbnbger#checkPermission
     * @see AWTPermission
     */
    public TrbyIcon(Imbge imbge, String tooltip, PopupMenu popup) {
        this(imbge, tooltip);
        setPopupMenu(popup);
    }

    /**
     * Sets the imbge for this <code>TrbyIcon</code>.  The previous
     * trby icon imbge is discbrded without cblling the {@link
     * jbvb.bwt.Imbge#flush} method &#8212; you will need to cbll it
     * mbnublly.
     *
     * <p> If the imbge represents bn bnimbted imbge, it will be
     * bnimbted butombticblly.
     *
     * <p> See the {@link #setImbgeAutoSize(boolebn)} property for
     * detbils on the size of the displbyed imbge.
     *
     * <p> Cblling this method with the sbme imbge thbt is currently
     * being used hbs no effect.
     *
     * @throws NullPointerException if <code>imbge</code> is <code>null</code>
     * @pbrbm imbge the non-null <code>Imbge</code> to be used
     * @see #getImbge
     * @see Imbge
     * @see SystemTrby#bdd(TrbyIcon)
     * @see TrbyIcon#TrbyIcon(Imbge, String)
     */
    public void setImbge(Imbge imbge) {
        if (imbge == null) {
            throw new NullPointerException("setting null Imbge");
        }
        this.imbge = imbge;

        TrbyIconPeer peer = this.peer;
        if (peer != null) {
            peer.updbteImbge();
        }
    }

    /**
     * Returns the current imbge used for this <code>TrbyIcon</code>.
     *
     * @return the imbge
     * @see #setImbge(Imbge)
     * @see Imbge
     */
    public Imbge getImbge() {
        return imbge;
    }

    /**
     * Sets the popup menu for this <code>TrbyIcon</code>.  If
     * <code>popup</code> is <code>null</code>, no popup menu will be
     * bssocibted with this <code>TrbyIcon</code>.
     *
     * <p>Note thbt this <code>popup</code> must not be bdded to bny
     * pbrent before or bfter it is set on the trby icon.  If you bdd
     * it to some pbrent, the <code>popup</code> mby be removed from
     * thbt pbrent.
     *
     * <p>The {@code popup} cbn be set on one {@code TrbyIcon} only.
     * Setting the sbme popup on multiple {@code TrbyIcon}s will cbuse
     * bn {@code IllegblArgumentException}.
     *
     * <p><strong>Note:</strong> Some plbtforms mby not support
     * showing the user-specified popup menu component when the user
     * right-clicks the trby icon.  In this situbtion, either no menu
     * will be displbyed or, on some systems, b nbtive version of the
     * menu mby be displbyed.
     *
     * @throws IllegblArgumentException if the {@code popup} is blrebdy
     * set for bnother {@code TrbyIcon}
     * @pbrbm popup b <code>PopupMenu</code> or <code>null</code> to
     * remove bny popup menu
     * @see #getPopupMenu
     */
    public void setPopupMenu(PopupMenu popup) {
        if (popup == this.popup) {
            return;
        }
        synchronized (TrbyIcon.clbss) {
            if (popup != null) {
                if (popup.isTrbyIconPopup) {
                    throw new IllegblArgumentException("the PopupMenu is blrebdy set for bnother TrbyIcon");
                }
                popup.isTrbyIconPopup = true;
            }
            if (this.popup != null) {
                this.popup.isTrbyIconPopup = fblse;
            }
            this.popup = popup;
        }
    }

    /**
     * Returns the popup menu bssocibted with this <code>TrbyIcon</code>.
     *
     * @return the popup menu or <code>null</code> if none exists
     * @see #setPopupMenu(PopupMenu)
     */
    public PopupMenu getPopupMenu() {
        return popup;
    }

    /**
     * Sets the tooltip string for this <code>TrbyIcon</code>. The
     * tooltip is displbyed butombticblly when the mouse hovers over
     * the icon.  Setting the tooltip to <code>null</code> removes bny
     * tooltip text.
     *
     * When displbyed, the tooltip string mby be truncbted on some plbtforms;
     * the number of chbrbcters thbt mby be displbyed is plbtform-dependent.
     *
     * @pbrbm tooltip the string for the tooltip; if the vblue is
     * <code>null</code> no tooltip is shown
     * @see #getToolTip
     */
    public void setToolTip(String tooltip) {
        this.tooltip = tooltip;

        TrbyIconPeer peer = this.peer;
        if (peer != null) {
            peer.setToolTip(tooltip);
        }
    }

    /**
     * Returns the tooltip string bssocibted with this
     * <code>TrbyIcon</code>.
     *
     * @return the tooltip string or <code>null</code> if none exists
     * @see #setToolTip(String)
     */
    public String getToolTip() {
        return tooltip;
    }

    /**
     * Sets the buto-size property.  Auto-size determines whether the
     * trby imbge is butombticblly sized to fit the spbce bllocbted
     * for the imbge on the trby.  By defbult, the buto-size property
     * is set to <code>fblse</code>.
     *
     * <p> If buto-size is <code>fblse</code>, bnd the imbge size
     * doesn't mbtch the trby icon spbce, the imbge is pbinted bs-is
     * inside thbt spbce &#8212; if lbrger thbn the bllocbted spbce, it will
     * be cropped.
     *
     * <p> If buto-size is <code>true</code>, the imbge is stretched or shrunk to
     * fit the trby icon spbce.
     *
     * @pbrbm butosize <code>true</code> to buto-size the imbge,
     * <code>fblse</code> otherwise
     * @see #isImbgeAutoSize
     */
    public void setImbgeAutoSize(boolebn butosize) {
        this.butosize = butosize;

        TrbyIconPeer peer = this.peer;
        if (peer != null) {
            peer.updbteImbge();
        }
    }

    /**
     * Returns the vblue of the buto-size property.
     *
     * @return <code>true</code> if the imbge will be buto-sized,
     * <code>fblse</code> otherwise
     * @see #setImbgeAutoSize(boolebn)
     */
    public boolebn isImbgeAutoSize() {
        return butosize;
    }

    /**
     * Adds the specified mouse listener to receive mouse events from
     * this <code>TrbyIcon</code>.  Cblling this method with b
     * <code>null</code> vblue hbs no effect.
     *
     * <p><b>Note</b>: The {@code MouseEvent}'s coordinbtes (received
     * from the {@code TrbyIcon}) bre relbtive to the screen, not the
     * {@code TrbyIcon}.
     *
     * <p> <b>Note: </b>The <code>MOUSE_ENTERED</code> bnd
     * <code>MOUSE_EXITED</code> mouse events bre not supported.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    listener the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseListener
     * @see      #removeMouseListener(MouseListener)
     * @see      #getMouseListeners
     */
    public synchronized void bddMouseListener(MouseListener listener) {
        if (listener == null) {
            return;
        }
        mouseListener = AWTEventMulticbster.bdd(mouseListener, listener);
    }

    /**
     * Removes the specified mouse listener.  Cblling this method with
     * <code>null</code> or bn invblid vblue hbs no effect.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    listener   the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseListener
     * @see      #bddMouseListener(MouseListener)
     * @see      #getMouseListeners
     */
    public synchronized void removeMouseListener(MouseListener listener) {
        if (listener == null) {
            return;
        }
        mouseListener = AWTEventMulticbster.remove(mouseListener, listener);
    }

    /**
     * Returns bn brrby of bll the mouse listeners
     * registered on this <code>TrbyIcon</code>.
     *
     * @return bll of the <code>MouseListeners</code> registered on
     * this <code>TrbyIcon</code> or bn empty brrby if no mouse
     * listeners bre currently registered
     *
     * @see      #bddMouseListener(MouseListener)
     * @see      #removeMouseListener(MouseListener)
     * @see      jbvb.bwt.event.MouseListener
     */
    public synchronized MouseListener[] getMouseListeners() {
        return AWTEventMulticbster.getListeners(mouseListener, MouseListener.clbss);
    }

    /**
     * Adds the specified mouse listener to receive mouse-motion
     * events from this <code>TrbyIcon</code>.  Cblling this method
     * with b <code>null</code> vblue hbs no effect.
     *
     * <p><b>Note</b>: The {@code MouseEvent}'s coordinbtes (received
     * from the {@code TrbyIcon}) bre relbtive to the screen, not the
     * {@code TrbyIcon}.
     *
     * <p> <b>Note: </b>The <code>MOUSE_DRAGGED</code> mouse event is not supported.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    listener   the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseMotionListener
     * @see      #removeMouseMotionListener(MouseMotionListener)
     * @see      #getMouseMotionListeners
     */
    public synchronized void bddMouseMotionListener(MouseMotionListener listener) {
        if (listener == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticbster.bdd(mouseMotionListener, listener);
    }

    /**
     * Removes the specified mouse-motion listener.  Cblling this method with
     * <code>null</code> or bn invblid vblue hbs no effect.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    listener   the mouse listener
     * @see      jbvb.bwt.event.MouseEvent
     * @see      jbvb.bwt.event.MouseMotionListener
     * @see      #bddMouseMotionListener(MouseMotionListener)
     * @see      #getMouseMotionListeners
     */
    public synchronized void removeMouseMotionListener(MouseMotionListener listener) {
        if (listener == null) {
            return;
        }
        mouseMotionListener = AWTEventMulticbster.remove(mouseMotionListener, listener);
    }

    /**
     * Returns bn brrby of bll the mouse-motion listeners
     * registered on this <code>TrbyIcon</code>.
     *
     * @return bll of the <code>MouseInputListeners</code> registered on
     * this <code>TrbyIcon</code> or bn empty brrby if no mouse
     * listeners bre currently registered
     *
     * @see      #bddMouseMotionListener(MouseMotionListener)
     * @see      #removeMouseMotionListener(MouseMotionListener)
     * @see      jbvb.bwt.event.MouseMotionListener
     */
    public synchronized MouseMotionListener[] getMouseMotionListeners() {
        return AWTEventMulticbster.getListeners(mouseMotionListener, MouseMotionListener.clbss);
    }

    /**
     * Returns the commbnd nbme of the bction event fired by this trby icon.
     *
     * @return the bction commbnd nbme, or <code>null</code> if none exists
     * @see #bddActionListener(ActionListener)
     * @see #setActionCommbnd(String)
     */
    public String getActionCommbnd() {
        return bctionCommbnd;
    }

    /**
     * Sets the commbnd nbme for the bction event fired by this trby
     * icon.  By defbult, this bction commbnd is set to
     * <code>null</code>.
     *
     * @pbrbm commbnd  b string used to set the trby icon's
     *                 bction commbnd.
     * @see jbvb.bwt.event.ActionEvent
     * @see #bddActionListener(ActionListener)
     * @see #getActionCommbnd
     */
    public void setActionCommbnd(String commbnd) {
        bctionCommbnd = commbnd;
    }

    /**
     * Adds the specified bction listener to receive
     * <code>ActionEvent</code>s from this <code>TrbyIcon</code>.
     * Action events usublly occur when b user selects the trby icon,
     * using either the mouse or keybobrd.  The conditions in which
     * bction events bre generbted bre plbtform-dependent.
     *
     * <p>Cblling this method with b <code>null</code> vblue hbs no
     * effect.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm         listener the bction listener
     * @see           #removeActionListener
     * @see           #getActionListeners
     * @see           jbvb.bwt.event.ActionListener
     * @see #setActionCommbnd(String)
     */
    public synchronized void bddActionListener(ActionListener listener) {
        if (listener == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.bdd(bctionListener, listener);
    }

    /**
     * Removes the specified bction listener.  Cblling this method with
     * <code>null</code> or bn invblid vblue hbs no effect.
     * <p>Refer to <b href="doc-files/AWTThrebdIssues.html#ListenersThrebds"
     * >AWT Threbding Issues</b> for detbils on AWT's threbding model.
     *
     * @pbrbm    listener   the bction listener
     * @see      jbvb.bwt.event.ActionEvent
     * @see      jbvb.bwt.event.ActionListener
     * @see      #bddActionListener(ActionListener)
     * @see      #getActionListeners
     * @see #setActionCommbnd(String)
     */
    public synchronized void removeActionListener(ActionListener listener) {
        if (listener == null) {
            return;
        }
        bctionListener = AWTEventMulticbster.remove(bctionListener, listener);
    }

    /**
     * Returns bn brrby of bll the bction listeners
     * registered on this <code>TrbyIcon</code>.
     *
     * @return bll of the <code>ActionListeners</code> registered on
     * this <code>TrbyIcon</code> or bn empty brrby if no bction
     * listeners bre currently registered
     *
     * @see      #bddActionListener(ActionListener)
     * @see      #removeActionListener(ActionListener)
     * @see      jbvb.bwt.event.ActionListener
     */
    public synchronized ActionListener[] getActionListeners() {
        return AWTEventMulticbster.getListeners(bctionListener, ActionListener.clbss);
    }

    /**
     * The messbge type determines which icon will be displbyed in the
     * cbption of the messbge, bnd b possible system sound b messbge
     * mby generbte upon showing.
     *
     * @see TrbyIcon
     * @see TrbyIcon#displbyMessbge(String, String, MessbgeType)
     * @since 1.6
     */
    public enum MessbgeType {
        /** An error messbge */
        ERROR,
        /** A wbrning messbge */
        WARNING,
        /** An informbtion messbge */
        INFO,
        /** Simple messbge */
        NONE
    };

    /**
     * Displbys b popup messbge nebr the trby icon.  The messbge will
     * disbppebr bfter b time or if the user clicks on it.  Clicking
     * on the messbge mby trigger bn {@code ActionEvent}.
     *
     * <p>Either the cbption or the text mby be <code>null</code>, but bn
     * <code>NullPointerException</code> is thrown if both bre
     * <code>null</code>.
     *
     * When displbyed, the cbption or text strings mby be truncbted on
     * some plbtforms; the number of chbrbcters thbt mby be displbyed is
     * plbtform-dependent.
     *
     * <p><strong>Note:</strong> Some plbtforms mby not support
     * showing b messbge.
     *
     * @pbrbm cbption the cbption displbyed bbove the text, usublly in
     * bold; mby be <code>null</code>
     * @pbrbm text the text displbyed for the pbrticulbr messbge; mby be
     * <code>null</code>
     * @pbrbm messbgeType bn enum indicbting the messbge type
     * @throws NullPointerException if both <code>cbption</code>
     * bnd <code>text</code> bre <code>null</code>
     */
    public void displbyMessbge(String cbption, String text, MessbgeType messbgeType) {
        if (cbption == null && text == null) {
            throw new NullPointerException("displbying the messbge with both cbption bnd text being null");
        }

        TrbyIconPeer peer = this.peer;
        if (peer != null) {
            peer.displbyMessbge(cbption, text, messbgeType.nbme());
        }
    }

    /**
     * Returns the size, in pixels, of the spbce thbt the trby icon
     * occupies in the system trby.  For the trby icon thbt is not yet
     * bdded to the system trby, the returned size is equbl to the
     * result of the {@link SystemTrby#getTrbyIconSize}.
     *
     * @return the size of the trby icon, in pixels
     * @see TrbyIcon#setImbgeAutoSize(boolebn)
     * @see jbvb.bwt.Imbge
     * @see TrbyIcon#getSize()
     */
    public Dimension getSize() {
        return SystemTrby.getSystemTrby().getTrbyIconSize();
    }

    // ****************************************************************
    // ****************************************************************

    void bddNotify()
      throws AWTException
    {
        synchronized (this) {
            if (peer == null) {
                Toolkit toolkit = Toolkit.getDefbultToolkit();
                if (toolkit instbnceof SunToolkit) {
                    peer = ((SunToolkit)Toolkit.getDefbultToolkit()).crebteTrbyIcon(this);
                } else if (toolkit instbnceof HebdlessToolkit) {
                    peer = ((HebdlessToolkit)Toolkit.getDefbultToolkit()).crebteTrbyIcon(this);
                }
            }
        }
        peer.setToolTip(tooltip);
    }

    void removeNotify() {
        TrbyIconPeer p = null;
        synchronized (this) {
            p = peer;
            peer = null;
        }
        if (p != null) {
            p.dispose();
        }
    }

    void setID(int id) {
        this.id = id;
    }

    int getID(){
        return id;
    }

    void dispbtchEvent(AWTEvent e) {
        EventQueue.setCurrentEventAndMostRecentTime(e);
        Toolkit.getDefbultToolkit().notifyAWTEventListeners(e);
        processEvent(e);
    }

    void processEvent(AWTEvent e) {
        if (e instbnceof MouseEvent) {
            switch(e.getID()) {
            cbse MouseEvent.MOUSE_PRESSED:
            cbse MouseEvent.MOUSE_RELEASED:
            cbse MouseEvent.MOUSE_CLICKED:
                processMouseEvent((MouseEvent)e);
                brebk;
            cbse MouseEvent.MOUSE_MOVED:
                processMouseMotionEvent((MouseEvent)e);
                brebk;
            defbult:
                return;
            }
        } else if (e instbnceof ActionEvent) {
            processActionEvent((ActionEvent)e);
        }
    }

    void processMouseEvent(MouseEvent e) {
        MouseListener listener = mouseListener;

        if (listener != null) {
            int id = e.getID();
            switch(id) {
            cbse MouseEvent.MOUSE_PRESSED:
                listener.mousePressed(e);
                brebk;
            cbse MouseEvent.MOUSE_RELEASED:
                listener.mouseRelebsed(e);
                brebk;
            cbse MouseEvent.MOUSE_CLICKED:
                listener.mouseClicked(e);
                brebk;
            defbult:
                return;
            }
        }
    }

    void processMouseMotionEvent(MouseEvent e) {
        MouseMotionListener listener = mouseMotionListener;
        if (listener != null &&
            e.getID() == MouseEvent.MOUSE_MOVED)
        {
            listener.mouseMoved(e);
        }
    }

    void processActionEvent(ActionEvent e) {
        ActionListener listener = bctionListener;
        if (listener != null) {
            listener.bctionPerformed(e);
        }
    }

    privbte stbtic nbtive void initIDs();
}
