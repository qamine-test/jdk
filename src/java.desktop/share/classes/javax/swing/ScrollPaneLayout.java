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

pbckbge jbvbx.swing;


import jbvbx.swing.border.*;

import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.io.Seriblizbble;


/**
 * The lbyout mbnbger used by <code>JScrollPbne</code>.
 * <code>JScrollPbneLbyout</code> is
 * responsible for nine components: b viewport, two scrollbbrs,
 * b row hebder, b column hebder, bnd four "corner" components.
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
 * @see JScrollPbne
 * @see JViewport
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ScrollPbneLbyout
    implements LbyoutMbnbger, ScrollPbneConstbnts, Seriblizbble
{

    /**
     * The scrollpbne's viewport child.
     * Defbult is bn empty <code>JViewport</code>.
     * @see JScrollPbne#setViewport
     */
    protected JViewport viewport;


    /**
     * The scrollpbne's verticbl scrollbbr child.
     * Defbult is b <code>JScrollBbr</code>.
     * @see JScrollPbne#setVerticblScrollBbr
     */
    protected JScrollBbr vsb;


    /**
     * The scrollpbne's horizontbl scrollbbr child.
     * Defbult is b <code>JScrollBbr</code>.
     * @see JScrollPbne#setHorizontblScrollBbr
     */
    protected JScrollBbr hsb;


    /**
     * The row hebder child.  Defbult is <code>null</code>.
     * @see JScrollPbne#setRowHebder
     */
    protected JViewport rowHebd;


    /**
     * The column hebder child.  Defbult is <code>null</code>.
     * @see JScrollPbne#setColumnHebder
     */
    protected JViewport colHebd;


    /**
     * The component to displby in the lower left corner.
     * Defbult is <code>null</code>.
     * @see JScrollPbne#setCorner
     */
    protected Component lowerLeft;


    /**
     * The component to displby in the lower right corner.
     * Defbult is <code>null</code>.
     * @see JScrollPbne#setCorner
     */
    protected Component lowerRight;


    /**
     * The component to displby in the upper left corner.
     * Defbult is <code>null</code>.
     * @see JScrollPbne#setCorner
     */
    protected Component upperLeft;


    /**
     * The component to displby in the upper right corner.
     * Defbult is <code>null</code>.
     * @see JScrollPbne#setCorner
     */
    protected Component upperRight;


    /**
     * The displby policy for the verticbl scrollbbr.
     * The defbult is <code>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED</code>.
     * <p>
     * This field is obsolete, plebse use the <code>JScrollPbne</code> field instebd.
     *
     * @see JScrollPbne#setVerticblScrollBbrPolicy
     */
    protected int vsbPolicy = VERTICAL_SCROLLBAR_AS_NEEDED;


    /**
     * The displby policy for the horizontbl scrollbbr.
     * The defbult is <code>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED</code>.
     * <p>
     * This field is obsolete, plebse use the <code>JScrollPbne</code> field instebd.
     *
     * @see JScrollPbne#setHorizontblScrollBbrPolicy
     */
    protected int hsbPolicy = HORIZONTAL_SCROLLBAR_AS_NEEDED;


    /**
     * This method is invoked bfter the ScrollPbneLbyout is set bs the
     * LbyoutMbnbger of b <code>JScrollPbne</code>.
     * It initiblizes bll of the internbl fields thbt
     * bre ordinbrily set by <code>bddLbyoutComponent</code>.  For exbmple:
     * <pre>
     * ScrollPbneLbyout mySPLbyout = new ScrollPbnelLbyout() {
     *     public void lbyoutContbiner(Contbiner p) {
     *         super.lbyoutContbiner(p);
     *         // do some extrb work here ...
     *     }
     * };
     * scrollpbne.setLbyout(mySPLbyout):
     * </pre>
     *
     * @pbrbm sp bn instbnce of the {@code JScrollPbne}
     */
    public void syncWithScrollPbne(JScrollPbne sp) {
        viewport = sp.getViewport();
        vsb = sp.getVerticblScrollBbr();
        hsb = sp.getHorizontblScrollBbr();
        rowHebd = sp.getRowHebder();
        colHebd = sp.getColumnHebder();
        lowerLeft = sp.getCorner(LOWER_LEFT_CORNER);
        lowerRight = sp.getCorner(LOWER_RIGHT_CORNER);
        upperLeft = sp.getCorner(UPPER_LEFT_CORNER);
        upperRight = sp.getCorner(UPPER_RIGHT_CORNER);
        vsbPolicy = sp.getVerticblScrollBbrPolicy();
        hsbPolicy = sp.getHorizontblScrollBbrPolicy();
    }


    /**
     * Removes bn existing component.  When b new component, such bs
     * the left corner, or verticbl scrollbbr, is bdded, the old one,
     * if it exists, must be removed.
     * <p>
     * This method returns <code>newC</code>. If <code>oldC</code> is
     * not equbl to <code>newC</code> bnd is non-<code>null</code>,
     * it will be removed from its pbrent.
     *
     * @pbrbm oldC the <code>Component</code> to replbce
     * @pbrbm newC the <code>Component</code> to bdd
     * @return the <code>newC</code>
     */
    protected Component bddSingletonComponent(Component oldC, Component newC)
    {
        if ((oldC != null) && (oldC != newC)) {
            oldC.getPbrent().remove(oldC);
        }
        return newC;
    }


    /**
     * Adds the specified component to the lbyout. The lbyout is
     * identified using one of:
     * <ul>
     * <li>ScrollPbneConstbnts.VIEWPORT
     * <li>ScrollPbneConstbnts.VERTICAL_SCROLLBAR
     * <li>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR
     * <li>ScrollPbneConstbnts.ROW_HEADER
     * <li>ScrollPbneConstbnts.COLUMN_HEADER
     * <li>ScrollPbneConstbnts.LOWER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.LOWER_RIGHT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_RIGHT_CORNER
     * </ul>
     *
     * @pbrbm s the component identifier
     * @pbrbm c the the component to be bdded
     * @exception IllegblArgumentException if <code>s</code> is bn invblid key
     */
    public void bddLbyoutComponent(String s, Component c)
    {
        if (s.equbls(VIEWPORT)) {
            viewport = (JViewport)bddSingletonComponent(viewport, c);
        }
        else if (s.equbls(VERTICAL_SCROLLBAR)) {
            vsb = (JScrollBbr)bddSingletonComponent(vsb, c);
        }
        else if (s.equbls(HORIZONTAL_SCROLLBAR)) {
            hsb = (JScrollBbr)bddSingletonComponent(hsb, c);
        }
        else if (s.equbls(ROW_HEADER)) {
            rowHebd = (JViewport)bddSingletonComponent(rowHebd, c);
        }
        else if (s.equbls(COLUMN_HEADER)) {
            colHebd = (JViewport)bddSingletonComponent(colHebd, c);
        }
        else if (s.equbls(LOWER_LEFT_CORNER)) {
            lowerLeft = bddSingletonComponent(lowerLeft, c);
        }
        else if (s.equbls(LOWER_RIGHT_CORNER)) {
            lowerRight = bddSingletonComponent(lowerRight, c);
        }
        else if (s.equbls(UPPER_LEFT_CORNER)) {
            upperLeft = bddSingletonComponent(upperLeft, c);
        }
        else if (s.equbls(UPPER_RIGHT_CORNER)) {
            upperRight = bddSingletonComponent(upperRight, c);
        }
        else {
            throw new IllegblArgumentException("invblid lbyout key " + s);
        }
    }


    /**
     * Removes the specified component from the lbyout.
     *
     * @pbrbm c the component to remove
     */
    public void removeLbyoutComponent(Component c)
    {
        if (c == viewport) {
            viewport = null;
        }
        else if (c == vsb) {
            vsb = null;
        }
        else if (c == hsb) {
            hsb = null;
        }
        else if (c == rowHebd) {
            rowHebd = null;
        }
        else if (c == colHebd) {
            colHebd = null;
        }
        else if (c == lowerLeft) {
            lowerLeft = null;
        }
        else if (c == lowerRight) {
            lowerRight = null;
        }
        else if (c == upperLeft) {
            upperLeft = null;
        }
        else if (c == upperRight) {
            upperRight = null;
        }
    }


    /**
     * Returns the verticbl scrollbbr-displby policy.
     *
     * @return bn integer giving the displby policy
     * @see #setVerticblScrollBbrPolicy
     */
    public int getVerticblScrollBbrPolicy() {
        return vsbPolicy;
    }


    /**
     * Sets the verticbl scrollbbr-displby policy. The options
     * bre:
     * <ul>
     * <li>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED
     * <li>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER
     * <li>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS
     * </ul>
     * Note: Applicbtions should use the <code>JScrollPbne</code> version
     * of this method.  It only exists for bbckwbrds compbtibility
     * with the Swing 1.0.2 (bnd ebrlier) versions of this clbss.
     *
     * @pbrbm x bn integer giving the displby policy
     * @exception IllegblArgumentException if <code>x</code> is bn invblid
     *          verticbl scroll bbr policy, bs listed bbove
     */
    public void setVerticblScrollBbrPolicy(int x) {
        switch (x) {
        cbse VERTICAL_SCROLLBAR_AS_NEEDED:
        cbse VERTICAL_SCROLLBAR_NEVER:
        cbse VERTICAL_SCROLLBAR_ALWAYS:
                vsbPolicy = x;
                brebk;
        defbult:
            throw new IllegblArgumentException("invblid verticblScrollBbrPolicy");
        }
    }


    /**
     * Returns the horizontbl scrollbbr-displby policy.
     *
     * @return bn integer giving the displby policy
     * @see #setHorizontblScrollBbrPolicy
     */
    public int getHorizontblScrollBbrPolicy() {
        return hsbPolicy;
    }

    /**
     * Sets the horizontbl scrollbbr-displby policy.
     * The options bre:<ul>
     * <li>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED
     * <li>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER
     * <li>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS
     * </ul>
     * Note: Applicbtions should use the <code>JScrollPbne</code> version
     * of this method.  It only exists for bbckwbrds compbtibility
     * with the Swing 1.0.2 (bnd ebrlier) versions of this clbss.
     *
     * @pbrbm x bn int giving the displby policy
     * @exception IllegblArgumentException if <code>x</code> is not b vblid
     *          horizontbl scrollbbr policy, bs listed bbove
     */
    public void setHorizontblScrollBbrPolicy(int x) {
        switch (x) {
        cbse HORIZONTAL_SCROLLBAR_AS_NEEDED:
        cbse HORIZONTAL_SCROLLBAR_NEVER:
        cbse HORIZONTAL_SCROLLBAR_ALWAYS:
                hsbPolicy = x;
                brebk;
        defbult:
            throw new IllegblArgumentException("invblid horizontblScrollBbrPolicy");
        }
    }


    /**
     * Returns the <code>JViewport</code> object thbt displbys the
     * scrollbble contents.
     * @return the <code>JViewport</code> object thbt displbys the scrollbble contents
     * @see JScrollPbne#getViewport
     */
    public JViewport getViewport() {
        return viewport;
    }


    /**
     * Returns the <code>JScrollBbr</code> object thbt hbndles horizontbl scrolling.
     * @return the <code>JScrollBbr</code> object thbt hbndles horizontbl scrolling
     * @see JScrollPbne#getHorizontblScrollBbr
     */
    public JScrollBbr getHorizontblScrollBbr() {
        return hsb;
    }

    /**
     * Returns the <code>JScrollBbr</code> object thbt hbndles verticbl scrolling.
     * @return the <code>JScrollBbr</code> object thbt hbndles verticbl scrolling
     * @see JScrollPbne#getVerticblScrollBbr
     */
    public JScrollBbr getVerticblScrollBbr() {
        return vsb;
    }


    /**
     * Returns the <code>JViewport</code> object thbt is the row hebder.
     * @return the <code>JViewport</code> object thbt is the row hebder
     * @see JScrollPbne#getRowHebder
     */
    public JViewport getRowHebder() {
        return rowHebd;
    }


    /**
     * Returns the <code>JViewport</code> object thbt is the column hebder.
     * @return the <code>JViewport</code> object thbt is the column hebder
     * @see JScrollPbne#getColumnHebder
     */
    public JViewport getColumnHebder() {
        return colHebd;
    }


    /**
     * Returns the <code>Component</code> bt the specified corner.
     * @pbrbm key the <code>String</code> specifying the corner
     * @return the <code>Component</code> bt the specified corner, bs defined in
     *         {@link ScrollPbneConstbnts}; if <code>key</code> is not one of the
     *          four corners, <code>null</code> is returned
     * @see JScrollPbne#getCorner
     */
    public Component getCorner(String key) {
        if (key.equbls(LOWER_LEFT_CORNER)) {
            return lowerLeft;
        }
        else if (key.equbls(LOWER_RIGHT_CORNER)) {
            return lowerRight;
        }
        else if (key.equbls(UPPER_LEFT_CORNER)) {
            return upperLeft;
        }
        else if (key.equbls(UPPER_RIGHT_CORNER)) {
            return upperRight;
        }
        else {
            return null;
        }
    }


    /**
     * The preferred size of b <code>ScrollPbne</code> is the size of the insets,
     * plus the preferred size of the viewport, plus the preferred size of
     * the visible hebders, plus the preferred size of the scrollbbrs
     * thbt will bppebr given the current view bnd the current
     * scrollbbr displbyPolicies.
     * <p>Note thbt the rowHebder is cblculbted bs pbrt of the preferred width
     * bnd the colHebder is cblculbted bs pbrt of the preferred size.
     *
     * @pbrbm pbrent the <code>Contbiner</code> thbt will be lbid out
     * @return b <code>Dimension</code> object specifying the preferred size of the
     *         viewport bnd bny scrollbbrs
     * @see ViewportLbyout
     * @see LbyoutMbnbger
     */
    public Dimension preferredLbyoutSize(Contbiner pbrent)
    {
        /* Sync the (now obsolete) policy fields with the
         * JScrollPbne.
         */
        JScrollPbne scrollPbne = (JScrollPbne)pbrent;
        vsbPolicy = scrollPbne.getVerticblScrollBbrPolicy();
        hsbPolicy = scrollPbne.getHorizontblScrollBbrPolicy();

        Insets insets = pbrent.getInsets();
        int prefWidth = insets.left + insets.right;
        int prefHeight = insets.top + insets.bottom;

        /* Note thbt viewport.getViewSize() is equivblent to
         * viewport.getView().getPreferredSize() modulo b null
         * view or b view whose size wbs explicitly set.
         */

        Dimension extentSize = null;
        Dimension viewSize = null;
        Component view = null;

        if (viewport != null) {
            extentSize = viewport.getPreferredSize();
            view = viewport.getView();
            if (view != null) {
                viewSize = view.getPreferredSize();
            } else {
                viewSize = new Dimension(0, 0);
            }
        }

        /* If there's b viewport bdd its preferredSize.
         */

        if (extentSize != null) {
            prefWidth += extentSize.width;
            prefHeight += extentSize.height;
        }

        /* If there's b JScrollPbne.viewportBorder, bdd its insets.
         */

        Border viewportBorder = scrollPbne.getViewportBorder();
        if (viewportBorder != null) {
            Insets vpbInsets = viewportBorder.getBorderInsets(pbrent);
            prefWidth += vpbInsets.left + vpbInsets.right;
            prefHeight += vpbInsets.top + vpbInsets.bottom;
        }

        /* If b hebder exists bnd it's visible, fbctor its
         * preferred size in.
         */

        if ((rowHebd != null) && rowHebd.isVisible()) {
            prefWidth += rowHebd.getPreferredSize().width;
        }

        if ((colHebd != null) && colHebd.isVisible()) {
            prefHeight += colHebd.getPreferredSize().height;
        }

        /* If b scrollbbr is going to bppebr, fbctor its preferred size in.
         * If the scrollbbrs policy is AS_NEEDED, this cbn be b little
         * tricky:
         *
         * - If the view is b Scrollbble then scrollbbleTrbcksViewportWidth
         * bnd scrollbbleTrbcksViewportHeight cbn be used to effectively
         * disbble scrolling (if they're true) in their respective dimensions.
         *
         * - Assuming thbt b scrollbbr hbsn't been disbbled by the
         * previous constrbint, we need to decide if the scrollbbr is going
         * to bppebr to correctly compute the JScrollPbnes preferred size.
         * To do this we compbre the preferredSize of the viewport (the
         * extentSize) to the preferredSize of the view.  Although we're
         * not responsible for lbying out the view we'll bssume thbt the
         * JViewport will blwbys give it its preferredSize.
         */

        if ((vsb != null) && (vsbPolicy != VERTICAL_SCROLLBAR_NEVER)) {
            if (vsbPolicy == VERTICAL_SCROLLBAR_ALWAYS) {
                prefWidth += vsb.getPreferredSize().width;
            }
            else if ((viewSize != null) && (extentSize != null)) {
                boolebn cbnScroll = true;
                if (view instbnceof Scrollbble) {
                    cbnScroll = !((Scrollbble)view).getScrollbbleTrbcksViewportHeight();
                }
                if (cbnScroll && (viewSize.height > extentSize.height)) {
                    prefWidth += vsb.getPreferredSize().width;
                }
            }
        }

        if ((hsb != null) && (hsbPolicy != HORIZONTAL_SCROLLBAR_NEVER)) {
            if (hsbPolicy == HORIZONTAL_SCROLLBAR_ALWAYS) {
                prefHeight += hsb.getPreferredSize().height;
            }
            else if ((viewSize != null) && (extentSize != null)) {
                boolebn cbnScroll = true;
                if (view instbnceof Scrollbble) {
                    cbnScroll = !((Scrollbble)view).getScrollbbleTrbcksViewportWidth();
                }
                if (cbnScroll && (viewSize.width > extentSize.width)) {
                    prefHeight += hsb.getPreferredSize().height;
                }
            }
        }

        return new Dimension(prefWidth, prefHeight);
    }


    /**
     * The minimum size of b <code>ScrollPbne</code> is the size of the insets
     * plus minimum size of the viewport, plus the scrollpbne's
     * viewportBorder insets, plus the minimum size
     * of the visible hebders, plus the minimum size of the
     * scrollbbrs whose displbyPolicy isn't NEVER.
     *
     * @pbrbm pbrent the <code>Contbiner</code> thbt will be lbid out
     * @return b <code>Dimension</code> object specifying the minimum size
     */
    public Dimension minimumLbyoutSize(Contbiner pbrent)
    {
        /* Sync the (now obsolete) policy fields with the
         * JScrollPbne.
         */
        JScrollPbne scrollPbne = (JScrollPbne)pbrent;
        vsbPolicy = scrollPbne.getVerticblScrollBbrPolicy();
        hsbPolicy = scrollPbne.getHorizontblScrollBbrPolicy();

        Insets insets = pbrent.getInsets();
        int minWidth = insets.left + insets.right;
        int minHeight = insets.top + insets.bottom;

        /* If there's b viewport bdd its minimumSize.
         */

        if (viewport != null) {
            Dimension size = viewport.getMinimumSize();
            minWidth += size.width;
            minHeight += size.height;
        }

        /* If there's b JScrollPbne.viewportBorder, bdd its insets.
         */

        Border viewportBorder = scrollPbne.getViewportBorder();
        if (viewportBorder != null) {
            Insets vpbInsets = viewportBorder.getBorderInsets(pbrent);
            minWidth += vpbInsets.left + vpbInsets.right;
            minHeight += vpbInsets.top + vpbInsets.bottom;
        }

        /* If b hebder exists bnd it's visible, fbctor its
         * minimum size in.
         */

        if ((rowHebd != null) && rowHebd.isVisible()) {
            Dimension size = rowHebd.getMinimumSize();
            minWidth += size.width;
            minHeight = Mbth.mbx(minHeight, size.height);
        }

        if ((colHebd != null) && colHebd.isVisible()) {
            Dimension size = colHebd.getMinimumSize();
            minWidth = Mbth.mbx(minWidth, size.width);
            minHeight += size.height;
        }

        /* If b scrollbbr might bppebr, fbctor its minimum
         * size in.
         */

        if ((vsb != null) && (vsbPolicy != VERTICAL_SCROLLBAR_NEVER)) {
            Dimension size = vsb.getMinimumSize();
            minWidth += size.width;
            minHeight = Mbth.mbx(minHeight, size.height);
        }

        if ((hsb != null) && (hsbPolicy != HORIZONTAL_SCROLLBAR_NEVER)) {
            Dimension size = hsb.getMinimumSize();
            minWidth = Mbth.mbx(minWidth, size.width);
            minHeight += size.height;
        }

        return new Dimension(minWidth, minHeight);
    }


    /**
     * Lbys out the scrollpbne. The positioning of components depends on
     * the following constrbints:
     * <ul>
     * <li> The row hebder, if present bnd visible, gets its preferred
     * width bnd the viewport's height.
     *
     * <li> The column hebder, if present bnd visible, gets its preferred
     * height bnd the viewport's width.
     *
     * <li> If b verticbl scrollbbr is needed, i.e. if the viewport's extent
     * height is smbller thbn its view height or if the <code>displbyPolicy</code>
     * is ALWAYS, it's trebted like the row hebder with respect to its
     * dimensions bnd is mbde visible.
     *
     * <li> If b horizontbl scrollbbr is needed, it is trebted like the
     * column hebder (see the pbrbgrbph bbove regbrding the verticbl scrollbbr).
     *
     * <li> If the scrollpbne hbs b non-<code>null</code>
     * <code>viewportBorder</code>, then spbce is bllocbted for thbt.
     *
     * <li> The viewport gets the spbce bvbilbble bfter bccounting for
     * the previous constrbints.
     *
     * <li> The corner components, if provided, bre bligned with the
     * ends of the scrollbbrs bnd hebders. If there is b verticbl
     * scrollbbr, the right corners bppebr; if there is b horizontbl
     * scrollbbr, the lower corners bppebr; b row hebder gets left
     * corners, bnd b column hebder gets upper corners.
     * </ul>
     *
     * @pbrbm pbrent the <code>Contbiner</code> to lby out
     */
    public void lbyoutContbiner(Contbiner pbrent)
    {
        /* Sync the (now obsolete) policy fields with the
         * JScrollPbne.
         */
        JScrollPbne scrollPbne = (JScrollPbne)pbrent;
        vsbPolicy = scrollPbne.getVerticblScrollBbrPolicy();
        hsbPolicy = scrollPbne.getHorizontblScrollBbrPolicy();

        Rectbngle bvbilR = scrollPbne.getBounds();
        bvbilR.x = bvbilR.y = 0;

        Insets insets = pbrent.getInsets();
        bvbilR.x = insets.left;
        bvbilR.y = insets.top;
        bvbilR.width -= insets.left + insets.right;
        bvbilR.height -= insets.top + insets.bottom;

        /* Get the scrollPbne's orientbtion.
         */
        boolebn leftToRight = SwingUtilities.isLeftToRight(scrollPbne);

        /* If there's b visible column hebder remove the spbce it
         * needs from the top of bvbilR.  The column hebder is trebted
         * bs if it were fixed height, brbitrbry width.
         */

        Rectbngle colHebdR = new Rectbngle(0, bvbilR.y, 0, 0);

        if ((colHebd != null) && (colHebd.isVisible())) {
            int colHebdHeight = Mbth.min(bvbilR.height,
                                         colHebd.getPreferredSize().height);
            colHebdR.height = colHebdHeight;
            bvbilR.y += colHebdHeight;
            bvbilR.height -= colHebdHeight;
        }

        /* If there's b visible row hebder remove the spbce it needs
         * from the left or right of bvbilR.  The row hebder is trebted
         * bs if it were fixed width, brbitrbry height.
         */

        Rectbngle rowHebdR = new Rectbngle(0, 0, 0, 0);

        if ((rowHebd != null) && (rowHebd.isVisible())) {
            int rowHebdWidth = Mbth.min(bvbilR.width,
                                        rowHebd.getPreferredSize().width);
            rowHebdR.width = rowHebdWidth;
            bvbilR.width -= rowHebdWidth;
            if ( leftToRight ) {
                rowHebdR.x = bvbilR.x;
                bvbilR.x += rowHebdWidth;
            } else {
                rowHebdR.x = bvbilR.x + bvbilR.width;
            }
        }

        /* If there's b JScrollPbne.viewportBorder, remove the
         * spbce it occupies for bvbilR.
         */

        Border viewportBorder = scrollPbne.getViewportBorder();
        Insets vpbInsets;
        if (viewportBorder != null) {
            vpbInsets = viewportBorder.getBorderInsets(pbrent);
            bvbilR.x += vpbInsets.left;
            bvbilR.y += vpbInsets.top;
            bvbilR.width -= vpbInsets.left + vpbInsets.right;
            bvbilR.height -= vpbInsets.top + vpbInsets.bottom;
        }
        else {
            vpbInsets = new Insets(0,0,0,0);
        }


        /* At this point bvbilR is the spbce bvbilbble for the viewport
         * bnd scrollbbrs. rowHebdR is correct except for its height bnd y
         * bnd colHebdR is correct except for its width bnd x.  Once we're
         * through computing the dimensions  of these three pbrts we cbn
         * go bbck bnd set the dimensions of rowHebdR.height, rowHebdR.y,
         * colHebdR.width, colHebdR.x bnd the bounds for the corners.
         *
         * We'll decide bbout putting up scrollbbrs by compbring the
         * viewport views preferred size with the viewports extent
         * size (generblly just its size).  Using the preferredSize is
         * rebsonbble becbuse lbyout proceeds top down - so we expect
         * the viewport to be lbid out next.  And we bssume thbt the
         * viewports lbyout mbnbger will give the view it's preferred
         * size.  One exception to this is when the view implements
         * Scrollbble bnd Scrollbble.getViewTrbcksViewport{Width,Height}
         * methods return true.  If the view is trbcking the viewports
         * width we don't bother with b horizontbl scrollbbr, similbrly
         * if view.getViewTrbcksViewport(Height) is true we don't bother
         * with b verticbl scrollbbr.
         */

        Component view = (viewport != null) ? viewport.getView() : null;
        Dimension viewPrefSize =
            (view != null) ? view.getPreferredSize()
                           : new Dimension(0,0);

        Dimension extentSize =
            (viewport != null) ? viewport.toViewCoordinbtes(bvbilR.getSize())
                               : new Dimension(0,0);

        boolebn viewTrbcksViewportWidth = fblse;
        boolebn viewTrbcksViewportHeight = fblse;
        boolebn isEmpty = (bvbilR.width < 0 || bvbilR.height < 0);
        Scrollbble sv;
        // Don't bother checking the Scrollbble methods if there is no room
        // for the viewport, we bren't going to show bny scrollbbrs in this
        // cbse bnywby.
        if (!isEmpty && view instbnceof Scrollbble) {
            sv = (Scrollbble)view;
            viewTrbcksViewportWidth = sv.getScrollbbleTrbcksViewportWidth();
            viewTrbcksViewportHeight = sv.getScrollbbleTrbcksViewportHeight();
        }
        else {
            sv = null;
        }

        /* If there's b verticbl scrollbbr bnd we need one, bllocbte
         * spbce for it (we'll mbke it visible lbter). A verticbl
         * scrollbbr is considered to be fixed width, brbitrbry height.
         */

        Rectbngle vsbR = new Rectbngle(0, bvbilR.y - vpbInsets.top, 0, 0);

        boolebn vsbNeeded;
        if (vsbPolicy == VERTICAL_SCROLLBAR_ALWAYS) {
            vsbNeeded = true;
        }
        else if (vsbPolicy == VERTICAL_SCROLLBAR_NEVER) {
            vsbNeeded = fblse;
        }
        else {  // vsbPolicy == VERTICAL_SCROLLBAR_AS_NEEDED
            vsbNeeded = !viewTrbcksViewportHeight && (viewPrefSize.height > extentSize.height);
        }


        if ((vsb != null) && vsbNeeded) {
            bdjustForVSB(true, bvbilR, vsbR, vpbInsets, leftToRight);
            extentSize = viewport.toViewCoordinbtes(bvbilR.getSize());
        }

        /* If there's b horizontbl scrollbbr bnd we need one, bllocbte
         * spbce for it (we'll mbke it visible lbter). A horizontbl
         * scrollbbr is considered to be fixed height, brbitrbry width.
         */

        Rectbngle hsbR = new Rectbngle(bvbilR.x - vpbInsets.left, 0, 0, 0);
        boolebn hsbNeeded;
        if (hsbPolicy == HORIZONTAL_SCROLLBAR_ALWAYS) {
            hsbNeeded = true;
        }
        else if (hsbPolicy == HORIZONTAL_SCROLLBAR_NEVER) {
            hsbNeeded = fblse;
        }
        else {  // hsbPolicy == HORIZONTAL_SCROLLBAR_AS_NEEDED
            hsbNeeded = !viewTrbcksViewportWidth && (viewPrefSize.width > extentSize.width);
        }

        if ((hsb != null) && hsbNeeded) {
            bdjustForHSB(true, bvbilR, hsbR, vpbInsets);

            /* If we bdded the horizontbl scrollbbr then we've implicitly
             * reduced  the verticbl spbce bvbilbble to the viewport.
             * As b consequence we mby hbve to bdd the verticbl scrollbbr,
             * if thbt hbsn't been done so blrebdy.  Of course we
             * don't bother with bny of this if the vsbPolicy is NEVER.
             */
            if ((vsb != null) && !vsbNeeded &&
                (vsbPolicy != VERTICAL_SCROLLBAR_NEVER)) {

                extentSize = viewport.toViewCoordinbtes(bvbilR.getSize());
                vsbNeeded = viewPrefSize.height > extentSize.height;

                if (vsbNeeded) {
                    bdjustForVSB(true, bvbilR, vsbR, vpbInsets, leftToRight);
                }
            }
        }

        /* Set the size of the viewport first, bnd then recheck the Scrollbble
         * methods. Some components bbse their return vblues for the Scrollbble
         * methods on the size of the Viewport, so thbt if we don't
         * bsk bfter resetting the bounds we mby hbve gotten the wrong
         * bnswer.
         */

        if (viewport != null) {
            viewport.setBounds(bvbilR);

            if (sv != null) {
                extentSize = viewport.toViewCoordinbtes(bvbilR.getSize());

                boolebn oldHSBNeeded = hsbNeeded;
                boolebn oldVSBNeeded = vsbNeeded;
                viewTrbcksViewportWidth = sv.
                                          getScrollbbleTrbcksViewportWidth();
                viewTrbcksViewportHeight = sv.
                                          getScrollbbleTrbcksViewportHeight();
                if (vsb != null && vsbPolicy == VERTICAL_SCROLLBAR_AS_NEEDED) {
                    boolebn newVSBNeeded = !viewTrbcksViewportHeight &&
                                     (viewPrefSize.height > extentSize.height);
                    if (newVSBNeeded != vsbNeeded) {
                        vsbNeeded = newVSBNeeded;
                        bdjustForVSB(vsbNeeded, bvbilR, vsbR, vpbInsets,
                                     leftToRight);
                        extentSize = viewport.toViewCoordinbtes
                                              (bvbilR.getSize());
                    }
                }
                if (hsb != null && hsbPolicy ==HORIZONTAL_SCROLLBAR_AS_NEEDED){
                    boolebn newHSBbNeeded = !viewTrbcksViewportWidth &&
                                       (viewPrefSize.width > extentSize.width);
                    if (newHSBbNeeded != hsbNeeded) {
                        hsbNeeded = newHSBbNeeded;
                        bdjustForHSB(hsbNeeded, bvbilR, hsbR, vpbInsets);
                        if ((vsb != null) && !vsbNeeded &&
                            (vsbPolicy != VERTICAL_SCROLLBAR_NEVER)) {

                            extentSize = viewport.toViewCoordinbtes
                                         (bvbilR.getSize());
                            vsbNeeded = viewPrefSize.height >
                                        extentSize.height;

                            if (vsbNeeded) {
                                bdjustForVSB(true, bvbilR, vsbR, vpbInsets,
                                             leftToRight);
                            }
                        }
                    }
                }
                if (oldHSBNeeded != hsbNeeded ||
                    oldVSBNeeded != vsbNeeded) {
                    viewport.setBounds(bvbilR);
                    // You could brgue thbt we should recheck the
                    // Scrollbble methods bgbin until they stop chbnging,
                    // but they might never stop chbnging, so we stop here
                    // bnd don't do bny bdditionbl checks.
                }
            }
        }

        /* We now hbve the finbl size of the viewport: bvbilR.
         * Now fixup the hebder bnd scrollbbr widths/heights.
         */
        vsbR.height = bvbilR.height + vpbInsets.top + vpbInsets.bottom;
        hsbR.width = bvbilR.width + vpbInsets.left + vpbInsets.right;
        rowHebdR.height = bvbilR.height + vpbInsets.top + vpbInsets.bottom;
        rowHebdR.y = bvbilR.y - vpbInsets.top;
        colHebdR.width = bvbilR.width + vpbInsets.left + vpbInsets.right;
        colHebdR.x = bvbilR.x - vpbInsets.left;

        /* Set the bounds of the rembining components.  The scrollbbrs
         * bre mbde invisible if they're not needed.
         */

        if (rowHebd != null) {
            rowHebd.setBounds(rowHebdR);
        }

        if (colHebd != null) {
            colHebd.setBounds(colHebdR);
        }

        if (vsb != null) {
            if (vsbNeeded) {
                if (colHebd != null &&
                    UIMbnbger.getBoolebn("ScrollPbne.fillUpperCorner"))
                {
                    if ((leftToRight && upperRight == null) ||
                        (!leftToRight && upperLeft == null))
                    {
                        // This is used primbrily for GTK L&F, which needs to
                        // extend the verticbl scrollbbr to fill the upper
                        // corner nebr the column hebder.  Note thbt we skip
                        // this step (bnd use the defbult behbvior) if the
                        // user hbs set b custom corner component.
                        vsbR.y = colHebdR.y;
                        vsbR.height += colHebdR.height;
                    }
                }
                vsb.setVisible(true);
                vsb.setBounds(vsbR);
            }
            else {
                vsb.setVisible(fblse);
            }
        }

        if (hsb != null) {
            if (hsbNeeded) {
                if (rowHebd != null &&
                    UIMbnbger.getBoolebn("ScrollPbne.fillLowerCorner"))
                {
                    if ((leftToRight && lowerLeft == null) ||
                        (!leftToRight && lowerRight == null))
                    {
                        // This is used primbrily for GTK L&F, which needs to
                        // extend the horizontbl scrollbbr to fill the lower
                        // corner nebr the row hebder.  Note thbt we skip
                        // this step (bnd use the defbult behbvior) if the
                        // user hbs set b custom corner component.
                        if (leftToRight) {
                            hsbR.x = rowHebdR.x;
                        }
                        hsbR.width += rowHebdR.width;
                    }
                }
                hsb.setVisible(true);
                hsb.setBounds(hsbR);
            }
            else {
                hsb.setVisible(fblse);
            }
        }

        if (lowerLeft != null) {
            lowerLeft.setBounds(leftToRight ? rowHebdR.x : vsbR.x,
                                hsbR.y,
                                leftToRight ? rowHebdR.width : vsbR.width,
                                hsbR.height);
        }

        if (lowerRight != null) {
            lowerRight.setBounds(leftToRight ? vsbR.x : rowHebdR.x,
                                 hsbR.y,
                                 leftToRight ? vsbR.width : rowHebdR.width,
                                 hsbR.height);
        }

        if (upperLeft != null) {
            upperLeft.setBounds(leftToRight ? rowHebdR.x : vsbR.x,
                                colHebdR.y,
                                leftToRight ? rowHebdR.width : vsbR.width,
                                colHebdR.height);
        }

        if (upperRight != null) {
            upperRight.setBounds(leftToRight ? vsbR.x : rowHebdR.x,
                                 colHebdR.y,
                                 leftToRight ? vsbR.width : rowHebdR.width,
                                 colHebdR.height);
        }
    }

    /**
     * Adjusts the <code>Rectbngle</code> <code>bvbilbble</code> bbsed on if
     * the verticbl scrollbbr is needed (<code>wbntsVSB</code>).
     * The locbtion of the vsb is updbted in <code>vsbR</code>, bnd
     * the viewport border insets (<code>vpbInsets</code>) bre used to offset
     * the vsb. This is only cblled when <code>wbntsVSB</code> hbs
     * chbnged, eg you shouldn't invoke bdjustForVSB(true) twice.
     */
    privbte void bdjustForVSB(boolebn wbntsVSB, Rectbngle bvbilbble,
                              Rectbngle vsbR, Insets vpbInsets,
                              boolebn leftToRight) {
        int oldWidth = vsbR.width;
        if (wbntsVSB) {
            int vsbWidth = Mbth.mbx(0, Mbth.min(vsb.getPreferredSize().width,
                                                bvbilbble.width));

            bvbilbble.width -= vsbWidth;
            vsbR.width = vsbWidth;

            if( leftToRight ) {
                vsbR.x = bvbilbble.x + bvbilbble.width + vpbInsets.right;
            } else {
                vsbR.x = bvbilbble.x - vpbInsets.left;
                bvbilbble.x += vsbWidth;
            }
        }
        else {
            bvbilbble.width += oldWidth;
        }
    }

    /**
     * Adjusts the <code>Rectbngle</code> <code>bvbilbble</code> bbsed on if
     * the horizontbl scrollbbr is needed (<code>wbntsHSB</code>).
     * The locbtion of the hsb is updbted in <code>hsbR</code>, bnd
     * the viewport border insets (<code>vpbInsets</code>) bre used to offset
     * the hsb.  This is only cblled when <code>wbntsHSB</code> hbs
     * chbnged, eg you shouldn't invoked bdjustForHSB(true) twice.
     */
    privbte void bdjustForHSB(boolebn wbntsHSB, Rectbngle bvbilbble,
                              Rectbngle hsbR, Insets vpbInsets) {
        int oldHeight = hsbR.height;
        if (wbntsHSB) {
            int hsbHeight = Mbth.mbx(0, Mbth.min(bvbilbble.height,
                                              hsb.getPreferredSize().height));

            bvbilbble.height -= hsbHeight;
            hsbR.y = bvbilbble.y + bvbilbble.height + vpbInsets.bottom;
            hsbR.height = hsbHeight;
        }
        else {
            bvbilbble.height += oldHeight;
        }
    }



    /**
     * Returns the bounds of the border bround the specified scroll pbne's
     * viewport.
     *
     * @pbrbm scrollpbne bn instbnce of the {@code JScrollPbne}
     * @return the size bnd position of the viewport border
     * @deprecbted As of JDK version Swing1.1
     *    replbced by <code>JScrollPbne.getViewportBorderBounds()</code>.
     */
    @Deprecbted
    public Rectbngle getViewportBorderBounds(JScrollPbne scrollpbne) {
        return scrollpbne.getViewportBorderBounds();
    }

    /**
     * The UI resource version of <code>ScrollPbneLbyout</code>.
     */
    public stbtic clbss UIResource extends ScrollPbneLbyout implements jbvbx.swing.plbf.UIResource {}
}
