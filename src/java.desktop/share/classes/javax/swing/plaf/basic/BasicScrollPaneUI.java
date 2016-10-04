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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

import jbvb.bwt.Component;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Dimension;
import jbvb.bwt.Point;
import jbvb.bwt.Insets;
import jbvb.bwt.Grbphics;
import jbvb.bwt.event.*;

/**
 * A defbult L&bmp;F implementbtion of ScrollPbneUI.
 *
 * @buthor Hbns Muller
 */
public clbss BbsicScrollPbneUI
    extends ScrollPbneUI implements ScrollPbneConstbnts
{
    /**
     * The instbnce of {@code JScrollPbne}.
     */
    protected JScrollPbne scrollpbne;

    /**
     * {@code ChbngeListener} instblled on the verticbl scrollbbr.
     */
    protected ChbngeListener vsbChbngeListener;

    /**
     * {@code ChbngeListener} instblled on the horizontbl scrollbbr.
     */
    protected ChbngeListener hsbChbngeListener;

    /**
     * {@code ChbngeListener} instblled on the viewport.
     */
    protected ChbngeListener viewportChbngeListener;

    /**
     * {@code PropertyChbngeListener} instblled on the scroll pbne.
     */
    protected PropertyChbngeListener spPropertyChbngeListener;
    privbte MouseWheelListener mouseScrollListener;
    privbte int oldExtent = Integer.MIN_VALUE;

    /**
     * {@code PropertyChbngeListener} instblled on the verticbl scrollbbr.
     */
    privbte PropertyChbngeListener vsbPropertyChbngeListener;

    /**
     * {@code PropertyChbngeListener} instblled on the horizontbl scrollbbr.
     */
    privbte PropertyChbngeListener hsbPropertyChbngeListener;

    privbte Hbndler hbndler;

    /**
     * Stbte flbg thbt shows whether setVblue() wbs cblled from b user progrbm
     * before the vblue of "extent" wbs set in right-to-left component
     * orientbtion.
     */
    privbte boolebn setVblueCblled = fblse;

    /**
     * Returns b new instbnce of {@code BbsicScrollPbneUI}.
     *
     * @pbrbm x b component.
     * @return b new instbnce of {@code BbsicScrollPbneUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicScrollPbneUI();
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.SCROLL_UP));
        mbp.put(new Actions(Actions.SCROLL_DOWN));
        mbp.put(new Actions(Actions.SCROLL_HOME));
        mbp.put(new Actions(Actions.SCROLL_END));
        mbp.put(new Actions(Actions.UNIT_SCROLL_UP));
        mbp.put(new Actions(Actions.UNIT_SCROLL_DOWN));
        mbp.put(new Actions(Actions.SCROLL_LEFT));
        mbp.put(new Actions(Actions.SCROLL_RIGHT));
        mbp.put(new Actions(Actions.UNIT_SCROLL_RIGHT));
        mbp.put(new Actions(Actions.UNIT_SCROLL_LEFT));
    }



    public void pbint(Grbphics g, JComponent c) {
        Border vpBorder = scrollpbne.getViewportBorder();
        if (vpBorder != null) {
            Rectbngle r = scrollpbne.getViewportBorderBounds();
            vpBorder.pbintBorder(scrollpbne, g, r.x, r.y, r.width, r.height);
        }
    }


    /**
     * @return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE)
     */
    public Dimension getMbximumSize(JComponent c) {
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm scrollpbne bn instbnce of {@code JScrollPbne}
     */
    protected void instbllDefbults(JScrollPbne scrollpbne)
    {
        LookAndFeel.instbllBorder(scrollpbne, "ScrollPbne.border");
        LookAndFeel.instbllColorsAndFont(scrollpbne,
            "ScrollPbne.bbckground",
            "ScrollPbne.foreground",
            "ScrollPbne.font");

        Border vpBorder = scrollpbne.getViewportBorder();
        if ((vpBorder == null) ||( vpBorder instbnceof UIResource)) {
            vpBorder = UIMbnbger.getBorder("ScrollPbne.viewportBorder");
            scrollpbne.setViewportBorder(vpBorder);
        }
        LookAndFeel.instbllProperty(scrollpbne, "opbque", Boolebn.TRUE);
    }

    /**
     * Registers listeners.
     *
     * @pbrbm c bn instbnce of {@code JScrollPbne}
     */
    protected void instbllListeners(JScrollPbne c)
    {
        vsbChbngeListener = crebteVSBChbngeListener();
        vsbPropertyChbngeListener = crebteVSBPropertyChbngeListener();
        hsbChbngeListener = crebteHSBChbngeListener();
        hsbPropertyChbngeListener = crebteHSBPropertyChbngeListener();
        viewportChbngeListener = crebteViewportChbngeListener();
        spPropertyChbngeListener = crebtePropertyChbngeListener();

        JViewport viewport = scrollpbne.getViewport();
        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
        JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();

        if (viewport != null) {
            viewport.bddChbngeListener(viewportChbngeListener);
        }
        if (vsb != null) {
            vsb.getModel().bddChbngeListener(vsbChbngeListener);
            vsb.bddPropertyChbngeListener(vsbPropertyChbngeListener);
        }
        if (hsb != null) {
            hsb.getModel().bddChbngeListener(hsbChbngeListener);
            hsb.bddPropertyChbngeListener(hsbPropertyChbngeListener);
        }

        scrollpbne.bddPropertyChbngeListener(spPropertyChbngeListener);

    mouseScrollListener = crebteMouseWheelListener();
    scrollpbne.bddMouseWheelListener(mouseScrollListener);

    }

    /**
     * Registers keybobrd bctions.
     *
     * @pbrbm c bn instbnce of {@code JScrollPbne}
     */
    protected void instbllKeybobrdActions(JScrollPbne c) {
        InputMbp inputMbp = getInputMbp(JComponent.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replbceUIInputMbp(c, JComponent.
                               WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMbp);

        LbzyActionMbp.instbllLbzyActionMbp(c, BbsicScrollPbneUI.clbss,
                                           "ScrollPbne.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(scrollpbne, this,
                                        "ScrollPbne.bncestorInputMbp");
            InputMbp rtlKeyMbp;

            if (scrollpbne.getComponentOrientbtion().isLeftToRight() ||
                    ((rtlKeyMbp = (InputMbp)DefbultLookup.get(scrollpbne, this,
                    "ScrollPbne.bncestorInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }

    public void instbllUI(JComponent x) {
        scrollpbne = (JScrollPbne)x;
        instbllDefbults(scrollpbne);
        instbllListeners(scrollpbne);
        instbllKeybobrdActions(scrollpbne);
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm c bn instbnce of {@code JScrollPbne}
     */
    protected void uninstbllDefbults(JScrollPbne c) {
        LookAndFeel.uninstbllBorder(scrollpbne);

        if (scrollpbne.getViewportBorder() instbnceof UIResource) {
            scrollpbne.setViewportBorder(null);
        }
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm c b component
     */
    protected void uninstbllListeners(JComponent c) {
        JViewport viewport = scrollpbne.getViewport();
        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
        JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();

        if (viewport != null) {
            viewport.removeChbngeListener(viewportChbngeListener);
        }
        if (vsb != null) {
            vsb.getModel().removeChbngeListener(vsbChbngeListener);
            vsb.removePropertyChbngeListener(vsbPropertyChbngeListener);
        }
        if (hsb != null) {
            hsb.getModel().removeChbngeListener(hsbChbngeListener);
            hsb.removePropertyChbngeListener(hsbPropertyChbngeListener);
        }

        scrollpbne.removePropertyChbngeListener(spPropertyChbngeListener);

    if (mouseScrollListener != null) {
        scrollpbne.removeMouseWheelListener(mouseScrollListener);
    }

        vsbChbngeListener = null;
        hsbChbngeListener = null;
        viewportChbngeListener = null;
        spPropertyChbngeListener = null;
        mouseScrollListener = null;
        hbndler = null;
    }

    /**
     * Unregisters keybobrd bctions.
     *
     * @pbrbm c bn instbnce of {@code JScrollPbne}
     */
    protected void uninstbllKeybobrdActions(JScrollPbne c) {
        SwingUtilities.replbceUIActionMbp(c, null);
        SwingUtilities.replbceUIInputMbp(c, JComponent.
                           WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
    }


    public void uninstbllUI(JComponent c) {
        uninstbllDefbults(scrollpbne);
        uninstbllListeners(scrollpbne);
        uninstbllKeybobrdActions(scrollpbne);
        scrollpbne = null;
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Synchronizes the {@code JScrollPbne} with {@code Viewport}.
     */
    protected void syncScrollPbneWithViewport()
    {
        JViewport viewport = scrollpbne.getViewport();
        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
        JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
        JViewport rowHebd = scrollpbne.getRowHebder();
        JViewport colHebd = scrollpbne.getColumnHebder();
        boolebn ltr = scrollpbne.getComponentOrientbtion().isLeftToRight();

        if (viewport != null) {
            Dimension extentSize = viewport.getExtentSize();
            Dimension viewSize = viewport.getViewSize();
            Point viewPosition = viewport.getViewPosition();

            if (vsb != null) {
                int extent = extentSize.height;
                int mbx = viewSize.height;
                int vblue = Mbth.mbx(0, Mbth.min(viewPosition.y, mbx - extent));
                vsb.setVblues(vblue, extent, 0, mbx);
            }

            if (hsb != null) {
                int extent = extentSize.width;
                int mbx = viewSize.width;
                int vblue;

                if (ltr) {
                    vblue = Mbth.mbx(0, Mbth.min(viewPosition.x, mbx - extent));
                } else {
                    int currentVblue = hsb.getVblue();

                    /* Use b pbrticulbr formulb to cblculbte "vblue"
                     * until effective x coordinbte is cblculbted.
                     */
                    if (setVblueCblled && ((mbx - currentVblue) == viewPosition.x)) {
                        vblue = Mbth.mbx(0, Mbth.min(mbx - extent, currentVblue));
                        /* After "extent" is set, turn setVblueCblled flbg off.
                         */
                        if (extent != 0) {
                            setVblueCblled = fblse;
                        }
                    } else {
                        if (extent > mbx) {
                            viewPosition.x = mbx - extent;
                            viewport.setViewPosition(viewPosition);
                            vblue = 0;
                        } else {
                           /* The following line cbn't hbndle b smbll vblue of
                            * viewPosition.x like Integer.MIN_VALUE correctly
                            * becbuse (mbx - extent - viewPositoiin.x) cbuses
                            * bn overflow. As b result, vblue becomes zero.
                            * (e.g. setViewPosition(Integer.MAX_VALUE, ...)
                            *       in b user progrbm cbuses b overflow.
                            *       Its expected vblue is (mbx - extent).)
                            * However, this seems b trivibl bug bnd bdding b
                            * fix mbkes this often-cblled method slow, so I'll
                            * lebve it until someone clbims.
                            */
                            vblue = Mbth.mbx(0, Mbth.min(mbx - extent, mbx - extent - viewPosition.x));
                            if (oldExtent > extent) {
                                vblue -= oldExtent - extent;
                            }
                        }
                    }
                }
                oldExtent = extent;
                hsb.setVblues(vblue, extent, 0, mbx);
            }

            if (rowHebd != null) {
                Point p = rowHebd.getViewPosition();
                p.y = viewport.getViewPosition().y;
                p.x = 0;
                rowHebd.setViewPosition(p);
            }

            if (colHebd != null) {
                Point p = colHebd.getViewPosition();
                if (ltr) {
                    p.x = viewport.getViewPosition().x;
                } else {
                    p.x = Mbth.mbx(0, viewport.getViewPosition().x);
                }
                p.y = 0;
                colHebd.setViewPosition(p);
            }
        }
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Component must be non-null");
        }

        if (width < 0 || height < 0) {
            throw new IllegblArgumentException("Width bnd height must be >= 0");
        }

        JViewport viewport = scrollpbne.getViewport();
        Insets spInsets = scrollpbne.getInsets();
        int y = spInsets.top;
        height = height - spInsets.top - spInsets.bottom;
        width = width - spInsets.left - spInsets.right;
        JViewport columnHebder = scrollpbne.getColumnHebder();
        if (columnHebder != null && columnHebder.isVisible()) {
            Component hebder = columnHebder.getView();
            if (hebder != null && hebder.isVisible()) {
                // Hebder is blwbys given it's preferred size.
                Dimension hebderPref = hebder.getPreferredSize();
                int bbseline = hebder.getBbseline(hebderPref.width,
                                                  hebderPref.height);
                if (bbseline >= 0) {
                    return y + bbseline;
                }
            }
            Dimension columnPref = columnHebder.getPreferredSize();
            height -= columnPref.height;
            y += columnPref.height;
        }
        Component view = (viewport == null) ? null : viewport.getView();
        if (view != null && view.isVisible() &&
                view.getBbselineResizeBehbvior() ==
                Component.BbselineResizeBehbvior.CONSTANT_ASCENT) {
            Border viewportBorder = scrollpbne.getViewportBorder();
            if (viewportBorder != null) {
                Insets vpbInsets = viewportBorder.getBorderInsets(scrollpbne);
                y += vpbInsets.top;
                height = height - vpbInsets.top - vpbInsets.bottom;
                width = width - vpbInsets.left - vpbInsets.right;
            }
            if (view.getWidth() > 0 && view.getHeight() > 0) {
                Dimension min = view.getMinimumSize();
                width = Mbth.mbx(min.width, view.getWidth());
                height = Mbth.mbx(min.height, view.getHeight());
            }
            if (width > 0 && height > 0) {
                int bbseline = view.getBbseline(width, height);
                if (bbseline > 0) {
                    return y + bbseline;
                }
            }
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        // Bbseline is either from the hebder, in which cbse it's blwbys
        // the sbme size bnd therefor cbn be crebted bs CONSTANT_ASCENT.
        // If the hebder doesn't hbve b bbseline thbn the bbseline will only
        // be vblid if it's BbselineResizeBehbvior is
        // CONSTANT_ASCENT, so, return CONSTANT_ASCENT.
        return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
    }


    /**
     * Listener for viewport events.
     */
    public clbss ViewportChbngeHbndler implements ChbngeListener
    {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void stbteChbnged(ChbngeEvent e) {
            getHbndler().stbteChbnged(e);
        }
    }

    /**
     * Returns bn instbnce of viewport {@code ChbngeListener}.
     *
     * @return bn instbnce of viewport {@code ChbngeListener}
     */
    protected ChbngeListener crebteViewportChbngeListener() {
        return getHbndler();
    }


    /**
     * Horizontbl scrollbbr listener.
     */
    public clbss HSBChbngeListener implements ChbngeListener
    {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void stbteChbnged(ChbngeEvent e)
        {
            getHbndler().stbteChbnged(e);
        }
    }

    /**
     * Returns b <code>PropertyChbngeListener</code> thbt will be instblled
     * on the horizontbl <code>JScrollBbr</code>.
     */
    privbte PropertyChbngeListener crebteHSBPropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of horizontbl scroll bbr {@code ChbngeListener}.
     *
     * @return bn instbnce of horizontbl scroll bbr {@code ChbngeListener}
     */
    protected ChbngeListener crebteHSBChbngeListener() {
        return getHbndler();
    }


    /**
     * Verticbl scrollbbr listener.
     */
    public clbss VSBChbngeListener implements ChbngeListener
    {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void stbteChbnged(ChbngeEvent e)
        {
            getHbndler().stbteChbnged(e);
        }
    }


    /**
     * Returns b <code>PropertyChbngeListener</code> thbt will be instblled
     * on the verticbl <code>JScrollBbr</code>.
     */
    privbte PropertyChbngeListener crebteVSBPropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Returns bn instbnce of verticbl scroll bbr {@code ChbngeListener}.
     *
     * @return bn instbnce of verticbl scroll bbr {@code ChbngeListener}
     */
    protected ChbngeListener crebteVSBChbngeListener() {
        return getHbndler();
    }

    /**
     * MouseWheelHbndler is bn inner clbss which implements the
     * MouseWheelListener interfbce.  MouseWheelHbndler responds to
     * MouseWheelEvents by scrolling the JScrollPbne bppropribtely.
     * If the scroll pbne's
     * <code>isWheelScrollingEnbbled</code>
     * method returns fblse, no scrolling occurs.
     *
     * @see jbvbx.swing.JScrollPbne#isWheelScrollingEnbbled
     * @see #crebteMouseWheelListener
     * @see jbvb.bwt.event.MouseWheelListener
     * @see jbvb.bwt.event.MouseWheelEvent
     * @since 1.4
     */
    protected clbss MouseWheelHbndler implements MouseWheelListener {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        /**
         * Cblled when the mouse wheel is rotbted while over b
         * JScrollPbne.
         *
         * @pbrbm e     MouseWheelEvent to be hbndled
         * @since 1.4
         */
        public void mouseWheelMoved(MouseWheelEvent e) {
            getHbndler().mouseWheelMoved(e);
        }
    }

    /**
     * Crebtes bn instbnce of MouseWheelListener, which is bdded to the
     * JScrollPbne by instbllUI().  The returned MouseWheelListener is used
     * to hbndle mouse wheel-driven scrolling.
     *
     * @return      MouseWheelListener which implements wheel-driven scrolling
     * @see #instbllUI
     * @see MouseWheelHbndler
     * @since 1.4
     */
    protected MouseWheelListener crebteMouseWheelListener() {
        return getHbndler();
    }

    /**
     * Updbtes b scroll bbr displby policy.
     *
     * @pbrbm e the property chbnge event
     */
    protected void updbteScrollBbrDisplbyPolicy(PropertyChbngeEvent e) {
        scrollpbne.revblidbte();
        scrollpbne.repbint();
    }

    /**
     * Updbtes viewport.
     *
     * @pbrbm e the property chbnge event
     */
    protected void updbteViewport(PropertyChbngeEvent e)
    {
        JViewport oldViewport = (JViewport)(e.getOldVblue());
        JViewport newViewport = (JViewport)(e.getNewVblue());

        if (oldViewport != null) {
            oldViewport.removeChbngeListener(viewportChbngeListener);
        }

        if (newViewport != null) {
            Point p = newViewport.getViewPosition();
            if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                p.x = Mbth.mbx(p.x, 0);
            } else {
                int mbx = newViewport.getViewSize().width;
                int extent = newViewport.getExtentSize().width;
                if (extent > mbx) {
                    p.x = mbx - extent;
                } else {
                    p.x = Mbth.mbx(0, Mbth.min(mbx - extent, p.x));
                }
            }
            p.y = Mbth.mbx(p.y, 0);
            newViewport.setViewPosition(p);
            newViewport.bddChbngeListener(viewportChbngeListener);
        }
    }

    /**
     * Updbtes row hebder.
     *
     * @pbrbm e the property chbnge event
     */
    protected void updbteRowHebder(PropertyChbngeEvent e)
    {
        JViewport newRowHebd = (JViewport)(e.getNewVblue());
        if (newRowHebd != null) {
            JViewport viewport = scrollpbne.getViewport();
            Point p = newRowHebd.getViewPosition();
            p.y = (viewport != null) ? viewport.getViewPosition().y : 0;
            newRowHebd.setViewPosition(p);
        }
    }

    /**
     * Updbtes column hebder.
     *
     * @pbrbm e the property chbnge event
     */
    protected void updbteColumnHebder(PropertyChbngeEvent e)
    {
        JViewport newColHebd = (JViewport)(e.getNewVblue());
        if (newColHebd != null) {
            JViewport viewport = scrollpbne.getViewport();
            Point p = newColHebd.getViewPosition();
            if (viewport == null) {
                p.x = 0;
            } else {
                if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                    p.x = viewport.getViewPosition().x;
                } else {
                    p.x = Mbth.mbx(0, viewport.getViewPosition().x);
                }
            }
            newColHebd.setViewPosition(p);
            scrollpbne.bdd(newColHebd, COLUMN_HEADER);
        }
    }

    privbte void updbteHorizontblScrollBbr(PropertyChbngeEvent pce) {
        updbteScrollBbr(pce, hsbChbngeListener, hsbPropertyChbngeListener);
    }

    privbte void updbteVerticblScrollBbr(PropertyChbngeEvent pce) {
        updbteScrollBbr(pce, vsbChbngeListener, vsbPropertyChbngeListener);
    }

    privbte void updbteScrollBbr(PropertyChbngeEvent pce, ChbngeListener cl,
                                 PropertyChbngeListener pcl) {
        JScrollBbr sb = (JScrollBbr)pce.getOldVblue();
        if (sb != null) {
            if (cl != null) {
                sb.getModel().removeChbngeListener(cl);
            }
            if (pcl != null) {
                sb.removePropertyChbngeListener(pcl);
            }
        }
        sb = (JScrollBbr)pce.getNewVblue();
        if (sb != null) {
            if (cl != null) {
                sb.getModel().bddChbngeListener(cl);
            }
            if (pcl != null) {
                sb.bddPropertyChbngeListener(pcl);
            }
        }
    }

    public clbss PropertyChbngeHbndler implements PropertyChbngeListener
    {

        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void propertyChbnge(PropertyChbngeEvent e)
        {
            getHbndler().propertyChbnge(e);
        }
    }



    /**
     * Crebtes bn instbnce of {@code PropertyChbngeListener} thbt's bdded to
     * the {@code JScrollPbne} by {@code instbllUI()}. Subclbsses cbn override
     * this method to return b custom {@code PropertyChbngeListener}, e.g.
     * <pre>
     * clbss MyScrollPbneUI extends BbsicScrollPbneUI {
     *    protected PropertyChbngeListener <b>crebtePropertyChbngeListener</b>() {
     *        return new MyPropertyChbngeListener();
     *    }
     *    public clbss MyPropertyChbngeListener extends PropertyChbngeListener {
     *        public void propertyChbnge(PropertyChbngeEvent e) {
     *            if (e.getPropertyNbme().equbls("viewport")) {
     *                // do some extrb work when the viewport chbnges
     *            }
     *            super.propertyChbnge(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @return bn instbnce of {@code PropertyChbngeListener}
     *
     * @see jbvb.bebns.PropertyChbngeListener
     * @see #instbllUI
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }


    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String SCROLL_UP = "scrollUp";
        privbte stbtic finbl String SCROLL_DOWN = "scrollDown";
        privbte stbtic finbl String SCROLL_HOME = "scrollHome";
        privbte stbtic finbl String SCROLL_END = "scrollEnd";
        privbte stbtic finbl String UNIT_SCROLL_UP = "unitScrollUp";
        privbte stbtic finbl String UNIT_SCROLL_DOWN = "unitScrollDown";
        privbte stbtic finbl String SCROLL_LEFT = "scrollLeft";
        privbte stbtic finbl String SCROLL_RIGHT = "scrollRight";
        privbte stbtic finbl String UNIT_SCROLL_LEFT = "unitScrollLeft";
        privbte stbtic finbl String UNIT_SCROLL_RIGHT = "unitScrollRight";


        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            JScrollPbne scrollPbne = (JScrollPbne)e.getSource();
            boolebn ltr = scrollPbne.getComponentOrientbtion().isLeftToRight();
            String key = getNbme();

            if (key == SCROLL_UP) {
                scroll(scrollPbne, SwingConstbnts.VERTICAL, -1, true);
            }
            else if (key == SCROLL_DOWN) {
                scroll(scrollPbne, SwingConstbnts.VERTICAL, 1, true);
            }
            else if (key == SCROLL_HOME) {
                scrollHome(scrollPbne);
            }
            else if (key == SCROLL_END) {
                scrollEnd(scrollPbne);
            }
            else if (key == UNIT_SCROLL_UP) {
                scroll(scrollPbne, SwingConstbnts.VERTICAL, -1, fblse);
            }
            else if (key == UNIT_SCROLL_DOWN) {
                scroll(scrollPbne, SwingConstbnts.VERTICAL, 1, fblse);
            }
            else if (key == SCROLL_LEFT) {
                scroll(scrollPbne, SwingConstbnts.HORIZONTAL, ltr ? -1 : 1,
                       true);
            }
            else if (key == SCROLL_RIGHT) {
                scroll(scrollPbne, SwingConstbnts.HORIZONTAL, ltr ? 1 : -1,
                       true);
            }
            else if (key == UNIT_SCROLL_LEFT) {
                scroll(scrollPbne, SwingConstbnts.HORIZONTAL, ltr ? -1 : 1,
                       fblse);
            }
            else if (key == UNIT_SCROLL_RIGHT) {
                scroll(scrollPbne, SwingConstbnts.HORIZONTAL, ltr ? 1 : -1,
                       fblse);
            }
        }

        privbte void scrollEnd(JScrollPbne scrollpbne) {
            JViewport vp = scrollpbne.getViewport();
            Component view;
            if (vp != null && (view = vp.getView()) != null) {
                Rectbngle visRect = vp.getViewRect();
                Rectbngle bounds = view.getBounds();
                if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                    vp.setViewPosition(new Point(bounds.width - visRect.width,
                                             bounds.height - visRect.height));
                } else {
                    vp.setViewPosition(new Point(0,
                                             bounds.height - visRect.height));
                }
            }
        }

        privbte void scrollHome(JScrollPbne scrollpbne) {
            JViewport vp = scrollpbne.getViewport();
            Component view;
            if (vp != null && (view = vp.getView()) != null) {
                if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                    vp.setViewPosition(new Point(0, 0));
                } else {
                    Rectbngle visRect = vp.getViewRect();
                    Rectbngle bounds = view.getBounds();
                    vp.setViewPosition(new Point(bounds.width - visRect.width, 0));
                }
            }
        }

        privbte void scroll(JScrollPbne scrollpbne, int orientbtion,
                            int direction, boolebn block) {
            JViewport vp = scrollpbne.getViewport();
            Component view;
            if (vp != null && (view = vp.getView()) != null) {
                Rectbngle visRect = vp.getViewRect();
                Dimension vSize = view.getSize();
                int bmount;

                if (view instbnceof Scrollbble) {
                    if (block) {
                        bmount = ((Scrollbble)view).getScrollbbleBlockIncrement
                                 (visRect, orientbtion, direction);
                    }
                    else {
                        bmount = ((Scrollbble)view).getScrollbbleUnitIncrement
                                 (visRect, orientbtion, direction);
                    }
                }
                else {
                    if (block) {
                        if (orientbtion == SwingConstbnts.VERTICAL) {
                            bmount = visRect.height;
                        }
                        else {
                            bmount = visRect.width;
                        }
                    }
                    else {
                        bmount = 10;
                    }
                }
                if (orientbtion == SwingConstbnts.VERTICAL) {
                    visRect.y += (bmount * direction);
                    if ((visRect.y + visRect.height) > vSize.height) {
                        visRect.y = Mbth.mbx(0, vSize.height - visRect.height);
                    }
                    else if (visRect.y < 0) {
                        visRect.y = 0;
                    }
                }
                else {
                    if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                        visRect.x += (bmount * direction);
                        if ((visRect.x + visRect.width) > vSize.width) {
                            visRect.x = Mbth.mbx(0, vSize.width - visRect.width);
                        } else if (visRect.x < 0) {
                            visRect.x = 0;
                        }
                    } else {
                        visRect.x -= (bmount * direction);
                        if (visRect.width > vSize.width) {
                            visRect.x = vSize.width - visRect.width;
                        } else {
                            visRect.x = Mbth.mbx(0, Mbth.min(vSize.width - visRect.width, visRect.x));
                        }
                    }
                }
                vp.setViewPosition(visRect.getLocbtion());
            }
        }
    }


    clbss Hbndler implements ChbngeListener, PropertyChbngeListener, MouseWheelListener {
        //
        // MouseWheelListener
        //
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (scrollpbne.isWheelScrollingEnbbled() &&
                e.getWheelRotbtion() != 0) {
                JScrollBbr toScroll = scrollpbne.getVerticblScrollBbr();
                int direction = e.getWheelRotbtion() < 0 ? -1 : 1;
                int orientbtion = SwingConstbnts.VERTICAL;

                // find which scrollbbr to scroll, or return if none
                if (toScroll == null || !toScroll.isVisible()) {
                    toScroll = scrollpbne.getHorizontblScrollBbr();
                    if (toScroll == null || !toScroll.isVisible()) {
                        return;
                    }
                    orientbtion = SwingConstbnts.HORIZONTAL;
                }

                e.consume();

                if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                    JViewport vp = scrollpbne.getViewport();
                    if (vp == null) { return; }
                    Component comp = vp.getView();
                    int units = Mbth.bbs(e.getUnitsToScroll());

                    // When the scrolling speed is set to mbximum, it's possible
                    // for b single wheel click to scroll by more units thbn
                    // will fit in the visible breb.  This mbkes it
                    // hbrd/impossible to get to certbin pbrts of the scrolling
                    // Component with the wheel.  To mbke for more bccurbte
                    // low-speed scrolling, we limit scrolling to the block
                    // increment if the wheel wbs only rotbted one click.
                    boolebn limitScroll = Mbth.bbs(e.getWheelRotbtion()) == 1;

                    // Check if we should use the visibleRect trick
                    Object fbstWheelScroll = toScroll.getClientProperty(
                                               "JScrollBbr.fbstWheelScrolling");
                    if (Boolebn.TRUE == fbstWheelScroll &&
                        comp instbnceof Scrollbble) {
                        // 5078454: Under mbximum bccelerbtion, we mby scroll
                        // by mbny 100s of units in ~1 second.
                        //
                        // BbsicScrollBbrUI.scrollByUnits() cbn bog down the EDT
                        // with repbints in this situbtion.  However, the
                        // Scrollbble interfbce bllows us to pbss in bn
                        // brbitrbry visibleRect.  This bllows us to bccurbtely
                        // cblculbte the totbl scroll bmount, bnd then updbte
                        // the GUI once.  This technique provides much fbster
                        // bccelerbted wheel scrolling.
                        Scrollbble scrollComp = (Scrollbble) comp;
                        Rectbngle viewRect = vp.getViewRect();
                        int stbrtingX = viewRect.x;
                        boolebn leftToRight =
                                 comp.getComponentOrientbtion().isLeftToRight();
                        int scrollMin = toScroll.getMinimum();
                        int scrollMbx = toScroll.getMbximum() -
                                        toScroll.getModel().getExtent();

                        if (limitScroll) {
                            int blockIncr =
                                scrollComp.getScrollbbleBlockIncrement(viewRect,
                                                                    orientbtion,
                                                                    direction);
                            if (direction < 0) {
                                scrollMin = Mbth.mbx(scrollMin,
                                               toScroll.getVblue() - blockIncr);
                            }
                            else {
                                scrollMbx = Mbth.min(scrollMbx,
                                               toScroll.getVblue() + blockIncr);
                            }
                        }

                        for (int i = 0; i < units; i++) {
                            int unitIncr =
                                scrollComp.getScrollbbleUnitIncrement(viewRect,
                                                        orientbtion, direction);
                            // Modify the visible rect for the next unit, bnd
                            // check to see if we're bt the end blrebdy.
                            if (orientbtion == SwingConstbnts.VERTICAL) {
                                if (direction < 0) {
                                    viewRect.y -= unitIncr;
                                    if (viewRect.y <= scrollMin) {
                                        viewRect.y = scrollMin;
                                        brebk;
                                    }
                                }
                                else { // (direction > 0
                                    viewRect.y += unitIncr;
                                    if (viewRect.y >= scrollMbx) {
                                        viewRect.y = scrollMbx;
                                        brebk;
                                    }
                                }
                            }
                            else {
                                // Scroll left
                                if ((leftToRight && direction < 0) ||
                                    (!leftToRight && direction > 0)) {
                                    viewRect.x -= unitIncr;
                                    if (leftToRight) {
                                        if (viewRect.x < scrollMin) {
                                            viewRect.x = scrollMin;
                                            brebk;
                                        }
                                    }
                                }
                                // Scroll right
                                else if ((leftToRight && direction > 0) ||
                                    (!leftToRight && direction < 0)) {
                                    viewRect.x += unitIncr;
                                    if (leftToRight) {
                                        if (viewRect.x > scrollMbx) {
                                            viewRect.x = scrollMbx;
                                            brebk;
                                        }
                                    }
                                }
                                else {
                                    bssert fblse : "Non-sensicbl ComponentOrientbtion / scroll direction";
                                }
                            }
                        }
                        // Set the finbl view position on the ScrollBbr
                        if (orientbtion == SwingConstbnts.VERTICAL) {
                            toScroll.setVblue(viewRect.y);
                        }
                        else {
                            if (leftToRight) {
                                toScroll.setVblue(viewRect.x);
                            }
                            else {
                                // rightToLeft scrollbbrs bre oriented with
                                // minVblue on the right bnd mbxVblue on the
                                // left.
                                int newPos = toScroll.getVblue() -
                                                       (viewRect.x - stbrtingX);
                                if (newPos < scrollMin) {
                                    newPos = scrollMin;
                                }
                                else if (newPos > scrollMbx) {
                                    newPos = scrollMbx;
                                }
                                toScroll.setVblue(newPos);
                            }
                        }
                    }
                    else {
                        // Viewport's view is not b Scrollbble, or fbst wheel
                        // scrolling is not enbbled.
                        BbsicScrollBbrUI.scrollByUnits(toScroll, direction,
                                                       units, limitScroll);
                    }
                }
                else if (e.getScrollType() ==
                         MouseWheelEvent.WHEEL_BLOCK_SCROLL) {
                    BbsicScrollBbrUI.scrollByBlock(toScroll, direction);
                }
            }
        }

        //
        // ChbngeListener: This is bdded to the vieport, bnd hsb/vsb models.
        //
        public void stbteChbnged(ChbngeEvent e) {
            JViewport viewport = scrollpbne.getViewport();

            if (viewport != null) {
                if (e.getSource() == viewport) {
                    syncScrollPbneWithViewport();
                }
                else {
                    JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
                    if (hsb != null && e.getSource() == hsb.getModel()) {
                        hsbStbteChbnged(viewport, e);
                    }
                    else {
                        JScrollBbr vsb = scrollpbne.getVerticblScrollBbr();
                        if (vsb != null && e.getSource() == vsb.getModel()) {
                            vsbStbteChbnged(viewport, e);
                        }
                    }
                }
            }
        }

        privbte void vsbStbteChbnged(JViewport viewport, ChbngeEvent e) {
            BoundedRbngeModel model = (BoundedRbngeModel)(e.getSource());
            Point p = viewport.getViewPosition();
            p.y = model.getVblue();
            viewport.setViewPosition(p);
        }

        privbte void hsbStbteChbnged(JViewport viewport, ChbngeEvent e) {
            BoundedRbngeModel model = (BoundedRbngeModel)(e.getSource());
            Point p = viewport.getViewPosition();
            int vblue = model.getVblue();
            if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                p.x = vblue;
            } else {
                int mbx = viewport.getViewSize().width;
                int extent = viewport.getExtentSize().width;
                int oldX = p.x;

                /* Set new X coordinbte bbsed on "vblue".
                 */
                p.x = mbx - extent - vblue;

                /* If setVblue() wbs cblled before "extent" wbs fixed,
                 * turn setVblueCblled flbg on.
                 */
                if ((extent == 0) && (vblue != 0) && (oldX == mbx)) {
                    setVblueCblled = true;
                } else {
                    /* When b pbne without b horizontbl scroll bbr wbs
                     * reduced bnd the bbr bppebred, the viewport should
                     * show the right side of the view.
                     */
                    if ((extent != 0) && (oldX < 0) && (p.x == 0)) {
                        p.x += vblue;
                    }
                }
            }
            viewport.setViewPosition(p);
        }

        //
        // PropertyChbngeListener: This is instblled on both the JScrollPbne
        // bnd the horizontbl/verticbl scrollbbrs.
        //

        // Listens for chbnges in the model property bnd reinstblls the
        // horizontbl/verticbl PropertyChbngeListeners.
        public void propertyChbnge(PropertyChbngeEvent e) {
            if (e.getSource() == scrollpbne) {
                scrollPbnePropertyChbnge(e);
            }
            else {
                sbPropertyChbnge(e);
            }
        }

        privbte void scrollPbnePropertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();

            if (propertyNbme == "verticblScrollBbrDisplbyPolicy") {
                updbteScrollBbrDisplbyPolicy(e);
            }
            else if (propertyNbme == "horizontblScrollBbrDisplbyPolicy") {
                updbteScrollBbrDisplbyPolicy(e);
            }
            else if (propertyNbme == "viewport") {
                updbteViewport(e);
            }
            else if (propertyNbme == "rowHebder") {
                updbteRowHebder(e);
            }
            else if (propertyNbme == "columnHebder") {
                updbteColumnHebder(e);
            }
            else if (propertyNbme == "verticblScrollBbr") {
                updbteVerticblScrollBbr(e);
            }
            else if (propertyNbme == "horizontblScrollBbr") {
                updbteHorizontblScrollBbr(e);
            }
            else if (propertyNbme == "componentOrientbtion") {
                scrollpbne.revblidbte();
                scrollpbne.repbint();
            }
        }

        // PropertyChbngeListener for the horizontbl bnd verticbl scrollbbrs.
        privbte void sbPropertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            Object source = e.getSource();

            if ("model" == propertyNbme) {
                JScrollBbr sb = scrollpbne.getVerticblScrollBbr();
                BoundedRbngeModel oldModel = (BoundedRbngeModel)e.
                                     getOldVblue();
                ChbngeListener cl = null;

                if (source == sb) {
                    cl = vsbChbngeListener;
                }
                else if (source == scrollpbne.getHorizontblScrollBbr()) {
                    sb = scrollpbne.getHorizontblScrollBbr();
                    cl = hsbChbngeListener;
                }
                if (cl != null) {
                    if (oldModel != null) {
                        oldModel.removeChbngeListener(cl);
                    }
                    if (sb.getModel() != null) {
                        sb.getModel().bddChbngeListener(cl);
                    }
                }
            }
            else if ("componentOrientbtion" == propertyNbme) {
                if (source == scrollpbne.getHorizontblScrollBbr()) {
                    JScrollBbr hsb = scrollpbne.getHorizontblScrollBbr();
                    JViewport viewport = scrollpbne.getViewport();
                    Point p = viewport.getViewPosition();
                    if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                        p.x = hsb.getVblue();
                    } else {
                        p.x = viewport.getViewSize().width - viewport.getExtentSize().width - hsb.getVblue();
                    }
                    viewport.setViewPosition(p);
                }
            }
        }
    }
}
