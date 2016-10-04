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

import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;

import jbvb.bwt.Component;
import jbvb.bwt.ComponentOrientbtion;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Insets;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Point;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.Trbnsient;

/**
 * Provides b scrollbble view of b lightweight component.
 * A <code>JScrollPbne</code> mbnbges b viewport, optionbl
 * verticbl bnd horizontbl scroll bbrs, bnd optionbl row bnd
 * column hebding viewports.
 * You cbn find tbsk-oriented documentbtion of <code>JScrollPbne</code> in
 *  <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/scrollpbne.html">How to Use Scroll Pbnes</b>,
 * b section in <em>The Jbvb Tutoribl</em>.  Note thbt
 * <code>JScrollPbne</code> does not support hebvyweight components.
 *
 * <TABLE STYLE="FLOAT:RIGHT" BORDER="0" SUMMARY="lbyout">
 *    <TR>
 *    <TD ALIGN="CENTER">
 *      <P STYLE="TEXT-ALIGN:CENTER"><IMG SRC="doc-files/JScrollPbne-1.gif"
 *      blt="The following text describes this imbge."
 *      WIDTH="256" HEIGHT="248" STYLE="FLOAT:BOTTOM; BORDER:0px">
 *    </TD>
 *    </TR>
 * </TABLE>
 * The <code>JViewport</code> provides b window,
 * or &quot;viewport&quot; onto b dbtb
 * source -- for exbmple, b text file. Thbt dbtb source is the
 * &quot;scrollbble client&quot; (bkb dbtb model) displbyed by the
 * <code>JViewport</code> view.
 * A <code>JScrollPbne</code> bbsicblly consists of <code>JScrollBbr</code>s,
 * b <code>JViewport</code>, bnd the wiring between them,
 * bs shown in the dibgrbm bt right.
 * <p>
 * In bddition to the scroll bbrs bnd viewport,
 * b <code>JScrollPbne</code> cbn hbve b
 * column hebder bnd b row hebder. Ebch of these is b
 * <code>JViewport</code> object thbt
 * you specify with <code>setRowHebderView</code>,
 * bnd <code>setColumnHebderView</code>.
 * The column hebder viewport butombticblly scrolls left bnd right, trbcking
 * the left-right scrolling of the mbin viewport.
 * (It never scrolls verticblly, however.)
 * The row hebder bcts in b similbr fbshion.
 * <p>
 * Where two scroll bbrs meet, the row hebder meets the column hebder,
 * or b scroll bbr meets one of the hebders, both components stop short
 * of the corner, lebving b rectbngulbr spbce which is, by defbult, empty.
 * These spbces cbn potentiblly exist in bny number of the four corners.
 * In the previous dibgrbm, the top right spbce is present bnd identified
 * by the lbbel "corner component".
 * <p>
 * Any number of these empty spbces cbn be replbced by using the
 * <code>setCorner</code> method to bdd b component to b pbrticulbr corner.
 * (Note: The sbme component cbnnot be bdded to multiple corners.)
 * This is useful if there's
 * some extrb decorbtion or function you'd like to bdd to the scroll pbne.
 * The size of ebch corner component is entirely determined by the size of the
 * hebders bnd/or scroll bbrs thbt surround it.
 * <p>
 * A corner component will only be visible if there is bn empty spbce in thbt
 * corner for it to exist in. For exbmple, consider b component set into the
 * top right corner of b scroll pbne with b column hebder. If the scroll pbne's
 * verticbl scrollbbr is not present, perhbps becbuse the view component hbsn't
 * grown lbrge enough to require it, then the corner component will not be
 * shown (since there is no empty spbce in thbt corner crebted by the meeting
 * of the hebder bnd verticbl scroll bbr). Forcing the scroll bbr to blwbys be
 * shown, using
 * <code>setVerticblScrollBbrPolicy(VERTICAL_SCROLLBAR_ALWAYS)</code>,
 * will ensure thbt the spbce for the corner component blwbys exists.
 * <p>
 * To bdd b border bround the mbin viewport,
 * you cbn use <code>setViewportBorder</code>.
 * (Of course, you cbn blso bdd b border bround the whole scroll pbne using
 * <code>setBorder</code>.)
 * <p>
 * A common operbtion to wbnt to do is to set the bbckground color thbt will
 * be used if the mbin viewport view is smbller thbn the viewport, or is
 * not opbque. This cbn be bccomplished by setting the bbckground color
 * of the viewport, vib <code>scrollPbne.getViewport().setBbckground()</code>.
 * The rebson for setting the color of the viewport bnd not the scrollpbne
 * is thbt by defbult <code>JViewport</code> is opbque
 * which, bmong other things, mebns it will completely fill
 * in its bbckground using its bbckground color.  Therefore when
 * <code>JScrollPbne</code> drbws its bbckground the viewport will
 * usublly drbw over it.
 * <p>
 * By defbult <code>JScrollPbne</code> uses <code>ScrollPbneLbyout</code>
 * to hbndle the lbyout of its child Components. <code>ScrollPbneLbyout</code>
 * determines the size to mbke the viewport view in one of two wbys:
 * <ol>
 *   <li>If the view implements <code>Scrollbble</code>
 *       b combinbtion of <code>getPreferredScrollbbleViewportSize</code>,
 *       <code>getScrollbbleTrbcksViewportWidth</code> bnd
 *       <code>getScrollbbleTrbcksViewportHeight</code>is used, otherwise
 *   <li><code>getPreferredSize</code> is used.
 * </ol>
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
 * @see JScrollBbr
 * @see JViewport
 * @see ScrollPbneLbyout
 * @see Scrollbble
 * @see Component#getPreferredSize
 * @see #setViewportView
 * @see #setRowHebderView
 * @see #setColumnHebderView
 * @see #setCorner
 * @see #setViewportBorder
 *
 * @bebninfo
 *     bttribute: isContbiner true
 *     bttribute: contbinerDelegbte getViewport
 *   description: A speciblized contbiner thbt mbnbges b viewport, optionbl scrollbbrs bnd hebders
 *
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JScrollPbne extends JComponent implements ScrollPbneConstbnts, Accessible
{
    privbte Border viewportBorder;

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ScrollPbneUI";

    /**
     * The displby policy for the verticbl scrollbbr.
     * The defbult is
     * <code>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED</code>.
     * @see #setVerticblScrollBbrPolicy
     */
    protected int verticblScrollBbrPolicy = VERTICAL_SCROLLBAR_AS_NEEDED;


    /**
     * The displby policy for the horizontbl scrollbbr.
     * The defbult is
     * <code>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED</code>.
     * @see #setHorizontblScrollBbrPolicy
     */
    protected int horizontblScrollBbrPolicy = HORIZONTAL_SCROLLBAR_AS_NEEDED;


    /**
     * The scrollpbne's viewport child.  Defbult is bn empty
     * <code>JViewport</code>.
     * @see #setViewport
     */
    protected JViewport viewport;


    /**
     * The scrollpbne's verticbl scrollbbr child.
     * Defbult is b <code>JScrollBbr</code>.
     * @see #setVerticblScrollBbr
     */
    protected JScrollBbr verticblScrollBbr;


    /**
     * The scrollpbne's horizontbl scrollbbr child.
     * Defbult is b <code>JScrollBbr</code>.
     * @see #setHorizontblScrollBbr
     */
    protected JScrollBbr horizontblScrollBbr;


    /**
     * The row hebder child.  Defbult is <code>null</code>.
     * @see #setRowHebder
     */
    protected JViewport rowHebder;


    /**
     * The column hebder child.  Defbult is <code>null</code>.
     * @see #setColumnHebder
     */
    protected JViewport columnHebder;


    /**
     * The component to displby in the lower left corner.
     * Defbult is <code>null</code>.
     * @see #setCorner
     */
    protected Component lowerLeft;


    /**
     * The component to displby in the lower right corner.
     * Defbult is <code>null</code>.
     * @see #setCorner
     */
    protected Component lowerRight;


    /**
     * The component to displby in the upper left corner.
     * Defbult is <code>null</code>.
     * @see #setCorner
     */
    protected Component upperLeft;


    /**
     * The component to displby in the upper right corner.
     * Defbult is <code>null</code>.
     * @see #setCorner
     */
    protected Component upperRight;

    /*
     * Stbte flbg for mouse wheel scrolling
     */
    privbte boolebn wheelScrollStbte = true;

    /**
     * Crebtes b <code>JScrollPbne</code> thbt displbys the view
     * component in b viewport
     * whose view position cbn be controlled with b pbir of scrollbbrs.
     * The scrollbbr policies specify when the scrollbbrs bre displbyed,
     * For exbmple, if <code>vsbPolicy</code> is
     * <code>VERTICAL_SCROLLBAR_AS_NEEDED</code>
     * then the verticbl scrollbbr only bppebrs if the view doesn't fit
     * verticblly. The bvbilbble policy settings bre listed bt
     * {@link #setVerticblScrollBbrPolicy} bnd
     * {@link #setHorizontblScrollBbrPolicy}.
     *
     * @see #setViewportView
     *
     * @pbrbm view the component to displby in the scrollpbnes viewport
     * @pbrbm vsbPolicy bn integer thbt specifies the verticbl
     *          scrollbbr policy
     * @pbrbm hsbPolicy bn integer thbt specifies the horizontbl
     *          scrollbbr policy
     */
    public JScrollPbne(Component view, int vsbPolicy, int hsbPolicy)
    {
        setLbyout(new ScrollPbneLbyout.UIResource());
        setVerticblScrollBbrPolicy(vsbPolicy);
        setHorizontblScrollBbrPolicy(hsbPolicy);
        setViewport(crebteViewport());
        setVerticblScrollBbr(crebteVerticblScrollBbr());
        setHorizontblScrollBbr(crebteHorizontblScrollBbr());
        if (view != null) {
            setViewportView(view);
        }
        setUIProperty("opbque",true);
        updbteUI();

        if (!this.getComponentOrientbtion().isLeftToRight()) {
            viewport.setViewPosition(new Point(Integer.MAX_VALUE, 0));
        }
    }


    /**
     * Crebtes b <code>JScrollPbne</code> thbt displbys the
     * contents of the specified
     * component, where both horizontbl bnd verticbl scrollbbrs bppebr
     * whenever the component's contents bre lbrger thbn the view.
     *
     * @see #setViewportView
     * @pbrbm view the component to displby in the scrollpbne's viewport
     */
    public JScrollPbne(Component view) {
        this(view, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }


    /**
     * Crebtes bn empty (no viewport view) <code>JScrollPbne</code>
     * with specified
     * scrollbbr policies. The bvbilbble policy settings bre listed bt
     * {@link #setVerticblScrollBbrPolicy} bnd
     * {@link #setHorizontblScrollBbrPolicy}.
     *
     * @see #setViewportView
     *
     * @pbrbm vsbPolicy bn integer thbt specifies the verticbl
     *          scrollbbr policy
     * @pbrbm hsbPolicy bn integer thbt specifies the horizontbl
     *          scrollbbr policy
     */
    public JScrollPbne(int vsbPolicy, int hsbPolicy) {
        this(null, vsbPolicy, hsbPolicy);
    }


    /**
     * Crebtes bn empty (no viewport view) <code>JScrollPbne</code>
     * where both horizontbl bnd verticbl scrollbbrs bppebr when needed.
     */
    public JScrollPbne() {
        this(null, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }


    /**
     * Returns the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @return the <code>ScrollPbneUI</code> object thbt renders this
     *                          component
     * @see #setUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public ScrollPbneUI getUI() {
        return (ScrollPbneUI)ui;
    }


    /**
     * Sets the <code>ScrollPbneUI</code> object thbt provides the
     * look bnd feel (L&bmp;F) for this component.
     *
     * @pbrbm ui the <code>ScrollPbneUI</code> L&bmp;F object
     * @see #getUI
     */
    public void setUI(ScrollPbneUI ui) {
        super.setUI(ui);
    }


    /**
     * Replbces the current <code>ScrollPbneUI</code> object with b version
     * from the current defbult look bnd feel.
     * To be cblled when the defbult look bnd feel chbnges.
     *
     * @see JComponent#updbteUI
     * @see UIMbnbger#getUI
     */
    public void updbteUI() {
        setUI((ScrollPbneUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the suffix used to construct the nbme of the L&bmp;F clbss used to
     * render this component.
     *
     * @return the string "ScrollPbneUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     *
     * @bebninfo
     *    hidden: true
     */
    public String getUIClbssID() {
        return uiClbssID;
    }



    /**
     * Sets the lbyout mbnbger for this <code>JScrollPbne</code>.
     * This method overrides <code>setLbyout</code> in
     * <code>jbvb.bwt.Contbiner</code> to ensure thbt only
     * <code>LbyoutMbnbger</code>s which
     * bre subclbsses of <code>ScrollPbneLbyout</code> cbn be used in b
     * <code>JScrollPbne</code>. If <code>lbyout</code> is non-null, this
     * will invoke <code>syncWithScrollPbne</code> on it.
     *
     * @pbrbm lbyout the specified lbyout mbnbger
     * @exception ClbssCbstException if lbyout is not b
     *                  <code>ScrollPbneLbyout</code>
     * @see jbvb.bwt.Contbiner#getLbyout
     * @see jbvb.bwt.Contbiner#setLbyout
     *
     * @bebninfo
     *    hidden: true
     */
    public void setLbyout(LbyoutMbnbger lbyout) {
        if (lbyout instbnceof ScrollPbneLbyout) {
            super.setLbyout(lbyout);
            ((ScrollPbneLbyout)lbyout).syncWithScrollPbne(this);
        }
        else if (lbyout == null) {
            super.setLbyout(lbyout);
        }
        else {
            String s = "lbyout of JScrollPbne must be b ScrollPbneLbyout";
            throw new ClbssCbstException(s);
        }
    }

    /**
     * Overridden to return true so thbt bny cblls to <code>revblidbte</code>
     * on bny descendbnts of this <code>JScrollPbne</code> will cbuse the
     * entire tree beginning with this <code>JScrollPbne</code> to be
     * vblidbted.
     *
     * @return true
     * @see jbvb.bwt.Contbiner#vblidbte
     * @see JComponent#revblidbte
     * @see JComponent#isVblidbteRoot
     * @see jbvb.bwt.Contbiner#isVblidbteRoot
     *
     * @bebninfo
     *    hidden: true
     */
    @Override
    public boolebn isVblidbteRoot() {
        return true;
    }


    /**
     * Returns the verticbl scroll bbr policy vblue.
     * @return the <code>verticblScrollBbrPolicy</code> property
     * @see #setVerticblScrollBbrPolicy
     */
    public int getVerticblScrollBbrPolicy() {
        return verticblScrollBbrPolicy;
    }


    /**
     * Determines when the verticbl scrollbbr bppebrs in the scrollpbne.
     * Legbl vblues bre:
     * <ul>
     * <li><code>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED</code>
     * <li><code>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER</code>
     * <li><code>ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS</code>
     * </ul>
     *
     * @pbrbm policy one of the three vblues listed bbove
     * @exception IllegblArgumentException if <code>policy</code>
     *                          is not one of the legbl vblues shown bbove
     * @see #getVerticblScrollBbrPolicy
     *
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The scrollpbne verticbl scrollbbr policy
     *        enum: VERTICAL_SCROLLBAR_AS_NEEDED ScrollPbneConstbnts.VERTICAL_SCROLLBAR_AS_NEEDED
     *              VERTICAL_SCROLLBAR_NEVER ScrollPbneConstbnts.VERTICAL_SCROLLBAR_NEVER
     *              VERTICAL_SCROLLBAR_ALWAYS ScrollPbneConstbnts.VERTICAL_SCROLLBAR_ALWAYS
     */
    public void setVerticblScrollBbrPolicy(int policy) {
        switch (policy) {
        cbse VERTICAL_SCROLLBAR_AS_NEEDED:
        cbse VERTICAL_SCROLLBAR_NEVER:
        cbse VERTICAL_SCROLLBAR_ALWAYS:
                brebk;
        defbult:
            throw new IllegblArgumentException("invblid verticblScrollBbrPolicy");
        }
        int old = verticblScrollBbrPolicy;
        verticblScrollBbrPolicy = policy;
        firePropertyChbnge("verticblScrollBbrPolicy", old, policy);
        revblidbte();
        repbint();
    }


    /**
     * Returns the horizontbl scroll bbr policy vblue.
     * @return the <code>horizontblScrollBbrPolicy</code> property
     * @see #setHorizontblScrollBbrPolicy
     */
    public int getHorizontblScrollBbrPolicy() {
        return horizontblScrollBbrPolicy;
    }


    /**
     * Determines when the horizontbl scrollbbr bppebrs in the scrollpbne.
     * The options bre:<ul>
     * <li><code>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED</code>
     * <li><code>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER</code>
     * <li><code>ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS</code>
     * </ul>
     *
     * @pbrbm policy one of the three vblues listed bbove
     * @exception IllegblArgumentException if <code>policy</code>
     *                          is not one of the legbl vblues shown bbove
     * @see #getHorizontblScrollBbrPolicy
     *
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The scrollpbne scrollbbr policy
     *        enum: HORIZONTAL_SCROLLBAR_AS_NEEDED ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_AS_NEEDED
     *              HORIZONTAL_SCROLLBAR_NEVER ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_NEVER
     *              HORIZONTAL_SCROLLBAR_ALWAYS ScrollPbneConstbnts.HORIZONTAL_SCROLLBAR_ALWAYS
     */
    public void setHorizontblScrollBbrPolicy(int policy) {
        switch (policy) {
        cbse HORIZONTAL_SCROLLBAR_AS_NEEDED:
        cbse HORIZONTAL_SCROLLBAR_NEVER:
        cbse HORIZONTAL_SCROLLBAR_ALWAYS:
                brebk;
        defbult:
            throw new IllegblArgumentException("invblid horizontblScrollBbrPolicy");
        }
        int old = horizontblScrollBbrPolicy;
        horizontblScrollBbrPolicy = policy;
        firePropertyChbnge("horizontblScrollBbrPolicy", old, policy);
        revblidbte();
        repbint();
    }


    /**
     * Returns the <code>Border</code> object thbt surrounds the viewport.
     *
     * @return the <code>viewportBorder</code> property
     * @see #setViewportBorder
     */
    public Border getViewportBorder() {
        return viewportBorder;
    }


    /**
     * Adds b border bround the viewport.  Note thbt the border isn't
     * set on the viewport directly, <code>JViewport</code> doesn't support
     * the <code>JComponent</code> border property.
     * Similbrly setting the <code>JScrollPbne</code>s
     * viewport doesn't bffect the <code>viewportBorder</code> property.
     * <p>
     * The defbult vblue of this property is computed by the look
     * bnd feel implementbtion.
     *
     * @pbrbm viewportBorder the border to be bdded
     * @see #getViewportBorder
     * @see #setViewport
     *
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The border bround the viewport.
     */
    public void setViewportBorder(Border viewportBorder) {
        Border oldVblue = this.viewportBorder;
        this.viewportBorder = viewportBorder;
        firePropertyChbnge("viewportBorder", oldVblue, viewportBorder);
    }


    /**
     * Returns the bounds of the viewport's border.
     *
     * @return b <code>Rectbngle</code> object specifying the viewport border
     */
    public Rectbngle getViewportBorderBounds()
    {
        Rectbngle borderR = new Rectbngle(getSize());

        Insets insets = getInsets();
        borderR.x = insets.left;
        borderR.y = insets.top;
        borderR.width -= insets.left + insets.right;
        borderR.height -= insets.top + insets.bottom;

        boolebn leftToRight = SwingUtilities.isLeftToRight(this);

        /* If there's b visible column hebder remove the spbce it
         * needs from the top of borderR.
         */

        JViewport colHebd = getColumnHebder();
        if ((colHebd != null) && (colHebd.isVisible())) {
            int colHebdHeight = colHebd.getHeight();
            borderR.y += colHebdHeight;
            borderR.height -= colHebdHeight;
        }

        /* If there's b visible row hebder remove the spbce it needs
         * from the left of borderR.
         */

        JViewport rowHebd = getRowHebder();
        if ((rowHebd != null) && (rowHebd.isVisible())) {
            int rowHebdWidth = rowHebd.getWidth();
            if ( leftToRight ) {
                borderR.x += rowHebdWidth;
            }
            borderR.width -= rowHebdWidth;
        }

        /* If there's b visible verticbl scrollbbr remove the spbce it needs
         * from the width of borderR.
         */
        JScrollBbr vsb = getVerticblScrollBbr();
        if ((vsb != null) && (vsb.isVisible())) {
            int vsbWidth = vsb.getWidth();
            if ( !leftToRight ) {
                borderR.x += vsbWidth;
            }
            borderR.width -= vsbWidth;
        }

        /* If there's b visible horizontbl scrollbbr remove the spbce it needs
         * from the height of borderR.
         */
        JScrollBbr hsb = getHorizontblScrollBbr();
        if ((hsb != null) && (hsb.isVisible())) {
            borderR.height -= hsb.getHeight();
        }

        return borderR;
    }


    /**
     * By defbult <code>JScrollPbne</code> crebtes scrollbbrs
     * thbt bre instbnces
     * of this clbss.  <code>Scrollbbr</code> overrides the
     * <code>getUnitIncrement</code> bnd <code>getBlockIncrement</code>
     * methods so thbt, if the viewport's view is b <code>Scrollbble</code>,
     * the view is bsked to compute these vblues. Unless
     * the unit/block increment hbve been explicitly set.
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
     * @see Scrollbble
     * @see JScrollPbne#crebteVerticblScrollBbr
     * @see JScrollPbne#crebteHorizontblScrollBbr
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss ScrollBbr extends JScrollBbr implements UIResource
    {
        /**
         * Set to true when the unit increment hbs been explicitly set.
         * If this is fblse the viewport's view is obtbined bnd if it
         * is bn instbnce of <code>Scrollbble</code> the unit increment
         * from it is used.
         */
        privbte boolebn unitIncrementSet;
        /**
         * Set to true when the block increment hbs been explicitly set.
         * If this is fblse the viewport's view is obtbined bnd if it
         * is bn instbnce of <code>Scrollbble</code> the block increment
         * from it is used.
         */
        privbte boolebn blockIncrementSet;

        /**
         * Crebtes b scrollbbr with the specified orientbtion.
         * The options bre:
         * <ul>
         * <li><code>ScrollPbneConstbnts.VERTICAL</code>
         * <li><code>ScrollPbneConstbnts.HORIZONTAL</code>
         * </ul>
         *
         * @pbrbm orientbtion  bn integer specifying one of the legbl
         *      orientbtion vblues shown bbove
         * @since 1.4
         */
        public ScrollBbr(int orientbtion) {
            super(orientbtion);
            this.putClientProperty("JScrollBbr.fbstWheelScrolling",
                                   Boolebn.TRUE);
        }

        /**
         * Messbges super to set the vblue, bnd resets the
         * <code>unitIncrementSet</code> instbnce vbribble to true.
         *
         * @pbrbm unitIncrement the new unit increment vblue, in pixels
         */
        public void setUnitIncrement(int unitIncrement) {
            unitIncrementSet = true;
            this.putClientProperty("JScrollBbr.fbstWheelScrolling", null);
            super.setUnitIncrement(unitIncrement);
        }

        /**
         * Computes the unit increment for scrolling if the viewport's
         * view is b <code>Scrollbble</code> object.
         * Otherwise return <code>super.getUnitIncrement</code>.
         *
         * @pbrbm direction less thbn zero to scroll up/left,
         *      grebter thbn zero for down/right
         * @return bn integer, in pixels, contbining the unit increment
         * @see Scrollbble#getScrollbbleUnitIncrement
         */
        public int getUnitIncrement(int direction) {
            JViewport vp = getViewport();
            if (!unitIncrementSet && (vp != null) &&
                (vp.getView() instbnceof Scrollbble)) {
                Scrollbble view = (Scrollbble)(vp.getView());
                Rectbngle vr = vp.getViewRect();
                return view.getScrollbbleUnitIncrement(vr, getOrientbtion(), direction);
            }
            else {
                return super.getUnitIncrement(direction);
            }
        }

        /**
         * Messbges super to set the vblue, bnd resets the
         * <code>blockIncrementSet</code> instbnce vbribble to true.
         *
         * @pbrbm blockIncrement the new block increment vblue, in pixels
         */
        public void setBlockIncrement(int blockIncrement) {
            blockIncrementSet = true;
            this.putClientProperty("JScrollBbr.fbstWheelScrolling", null);
            super.setBlockIncrement(blockIncrement);
        }

        /**
         * Computes the block increment for scrolling if the viewport's
         * view is b <code>Scrollbble</code> object.  Otherwise
         * the <code>blockIncrement</code> equbls the viewport's width
         * or height.  If there's no viewport return
         * <code>super.getBlockIncrement</code>.
         *
         * @pbrbm direction less thbn zero to scroll up/left,
         *      grebter thbn zero for down/right
         * @return bn integer, in pixels, contbining the block increment
         * @see Scrollbble#getScrollbbleBlockIncrement
         */
        public int getBlockIncrement(int direction) {
            JViewport vp = getViewport();
            if (blockIncrementSet || vp == null) {
                return super.getBlockIncrement(direction);
            }
            else if (vp.getView() instbnceof Scrollbble) {
                Scrollbble view = (Scrollbble)(vp.getView());
                Rectbngle vr = vp.getViewRect();
                return view.getScrollbbleBlockIncrement(vr, getOrientbtion(), direction);
            }
            else if (getOrientbtion() == VERTICAL) {
                return vp.getExtentSize().height;
            }
            else {
                return vp.getExtentSize().width;
            }
        }

    }


    /**
     * Returns b <code>JScrollPbne.ScrollBbr</code> by defbult.
     * Subclbsses mby override this method to force <code>ScrollPbneUI</code>
     * implementbtions to use b <code>JScrollBbr</code> subclbss.
     * Used by <code>ScrollPbneUI</code> implementbtions to
     * crebte the horizontbl scrollbbr.
     *
     * @return b <code>JScrollBbr</code> with b horizontbl orientbtion
     * @see JScrollBbr
     */
    public JScrollBbr crebteHorizontblScrollBbr() {
        return new ScrollBbr(JScrollBbr.HORIZONTAL);
    }


    /**
     * Returns the horizontbl scroll bbr thbt controls the viewport's
     * horizontbl view position.
     *
     * @return the <code>horizontblScrollBbr</code> property
     * @see #setHorizontblScrollBbr
     */
    @Trbnsient
    public JScrollBbr getHorizontblScrollBbr() {
        return horizontblScrollBbr;
    }


    /**
     * Adds the scrollbbr thbt controls the viewport's horizontbl view
     * position to the scrollpbne.
     * This is usublly unnecessbry, bs <code>JScrollPbne</code> crebtes
     * horizontbl bnd verticbl scrollbbrs by defbult.
     *
     * @pbrbm horizontblScrollBbr the horizontbl scrollbbr to be bdded
     * @see #crebteHorizontblScrollBbr
     * @see #getHorizontblScrollBbr
     *
     * @bebninfo
     *        expert: true
     *         bound: true
     *   description: The horizontbl scrollbbr.
     */
    public void setHorizontblScrollBbr(JScrollBbr horizontblScrollBbr) {
        JScrollBbr old = getHorizontblScrollBbr();
        this.horizontblScrollBbr = horizontblScrollBbr;
        if (horizontblScrollBbr != null) {
            bdd(horizontblScrollBbr, HORIZONTAL_SCROLLBAR);
        }
        else if (old != null) {
            remove(old);
        }
        firePropertyChbnge("horizontblScrollBbr", old, horizontblScrollBbr);

        revblidbte();
        repbint();
    }


    /**
     * Returns b <code>JScrollPbne.ScrollBbr</code> by defbult.  Subclbsses
     * mby override this method to force <code>ScrollPbneUI</code>
     * implementbtions to use b <code>JScrollBbr</code> subclbss.
     * Used by <code>ScrollPbneUI</code> implementbtions to crebte the
     * verticbl scrollbbr.
     *
     * @return b <code>JScrollBbr</code> with b verticbl orientbtion
     * @see JScrollBbr
     */
    public JScrollBbr crebteVerticblScrollBbr() {
        return new ScrollBbr(JScrollBbr.VERTICAL);
    }


    /**
     * Returns the verticbl scroll bbr thbt controls the viewports
     * verticbl view position.
     *
     * @return the <code>verticblScrollBbr</code> property
     * @see #setVerticblScrollBbr
     */
    @Trbnsient
    public JScrollBbr getVerticblScrollBbr() {
        return verticblScrollBbr;
    }


    /**
     * Adds the scrollbbr thbt controls the viewports verticbl view position
     * to the scrollpbne.  This is usublly unnecessbry,
     * bs <code>JScrollPbne</code> crebtes verticbl bnd
     * horizontbl scrollbbrs by defbult.
     *
     * @pbrbm verticblScrollBbr the new verticbl scrollbbr to be bdded
     * @see #crebteVerticblScrollBbr
     * @see #getVerticblScrollBbr
     *
     * @bebninfo
     *        expert: true
     *         bound: true
     *   description: The verticbl scrollbbr.
     */
    public void setVerticblScrollBbr(JScrollBbr verticblScrollBbr) {
        JScrollBbr old = getVerticblScrollBbr();
        this.verticblScrollBbr = verticblScrollBbr;
        bdd(verticblScrollBbr, VERTICAL_SCROLLBAR);
        firePropertyChbnge("verticblScrollBbr", old, verticblScrollBbr);

        revblidbte();
        repbint();
    }


    /**
     * Returns b new <code>JViewport</code> by defbult.
     * Used to crebte the
     * viewport (bs needed) in <code>setViewportView</code>,
     * <code>setRowHebderView</code>, bnd <code>setColumnHebderView</code>.
     * Subclbsses mby override this method to return b subclbss of
     * <code>JViewport</code>.
     *
     * @return b new <code>JViewport</code>
     */
    protected JViewport crebteViewport() {
        return new JViewport();
    }


    /**
     * Returns the current <code>JViewport</code>.
     *
     * @see #setViewport
     * @return the <code>viewport</code> property
     */
    public JViewport getViewport() {
        return viewport;
    }


    /**
     * Removes the old viewport (if there is one); forces the
     * viewPosition of the new viewport to be in the +x,+y qubdrbnt;
     * syncs up the row bnd column hebders (if there bre bny) with the
     * new viewport; bnd finblly syncs the scrollbbrs bnd
     * hebders with the new viewport.
     * <p>
     * Most bpplicbtions will find it more convenient to use
     * <code>setViewportView</code>
     * to bdd b viewport bnd b view to the scrollpbne.
     *
     * @pbrbm viewport the new viewport to be used; if viewport is
     *          <code>null</code>, the old viewport is still removed
     *          bnd the new viewport is set to <code>null</code>
     * @see #crebteViewport
     * @see #getViewport
     * @see #setViewportView
     *
     * @bebninfo
     *       expert: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The viewport child for this scrollpbne
     *
     */
    public void setViewport(JViewport viewport) {
        JViewport old = getViewport();
        this.viewport = viewport;
        if (viewport != null) {
            bdd(viewport, VIEWPORT);
        }
        else if (old != null) {
            remove(old);
        }
        firePropertyChbnge("viewport", old, viewport);

        if (bccessibleContext != null) {
            ((AccessibleJScrollPbne)bccessibleContext).resetViewPort();
        }

        revblidbte();
        repbint();
    }


    /**
     * Crebtes b viewport if necessbry bnd then sets its view.  Applicbtions
     * thbt don't provide the view directly to the <code>JScrollPbne</code>
     * constructor
     * should use this method to specify the scrollbble child thbt's going
     * to be displbyed in the scrollpbne. For exbmple:
     * <pre>
     * JScrollPbne scrollpbne = new JScrollPbne();
     * scrollpbne.setViewportView(myBigComponentToScroll);
     * </pre>
     * Applicbtions should not bdd children directly to the scrollpbne.
     *
     * @pbrbm view the component to bdd to the viewport
     * @see #setViewport
     * @see JViewport#setView
     */
    public void setViewportView(Component view) {
        if (getViewport() == null) {
            setViewport(crebteViewport());
        }
        getViewport().setView(view);
    }



    /**
     * Returns the row hebder.
     * @return the <code>rowHebder</code> property
     * @see #setRowHebder
     */
    @Trbnsient
    public JViewport getRowHebder() {
        return rowHebder;
    }


    /**
     * Removes the old rowHebder, if it exists; if the new rowHebder
     * isn't <code>null</code>, syncs the y coordinbte of its
     * viewPosition with
     * the viewport (if there is one) bnd then bdds it to the scroll pbne.
     * <p>
     * Most bpplicbtions will find it more convenient to use
     * <code>setRowHebderView</code>
     * to bdd b row hebder component bnd its viewport to the scroll pbne.
     *
     * @pbrbm rowHebder the new row hebder to be used; if <code>null</code>
     *          the old row hebder is still removed bnd the new rowHebder
     *          is set to <code>null</code>
     * @see #getRowHebder
     * @see #setRowHebderView
     *
     * @bebninfo
     *        bound: true
     *       expert: true
     *  description: The row hebder child for this scrollpbne
     */
    public void setRowHebder(JViewport rowHebder) {
        JViewport old = getRowHebder();
        this.rowHebder = rowHebder;
        if (rowHebder != null) {
            bdd(rowHebder, ROW_HEADER);
        }
        else if (old != null) {
            remove(old);
        }
        firePropertyChbnge("rowHebder", old, rowHebder);
        revblidbte();
        repbint();
    }


    /**
     * Crebtes b row-hebder viewport if necessbry, sets
     * its view bnd then bdds the row-hebder viewport
     * to the scrollpbne.  For exbmple:
     * <pre>
     * JScrollPbne scrollpbne = new JScrollPbne();
     * scrollpbne.setViewportView(myBigComponentToScroll);
     * scrollpbne.setRowHebderView(myBigComponentsRowHebder);
     * </pre>
     *
     * @see #setRowHebder
     * @see JViewport#setView
     * @pbrbm view the component to displby bs the row hebder
     */
    public void setRowHebderView(Component view) {
        if (getRowHebder() == null) {
            setRowHebder(crebteViewport());
        }
        getRowHebder().setView(view);
    }



    /**
     * Returns the column hebder.
     * @return the <code>columnHebder</code> property
     * @see #setColumnHebder
     */
    @Trbnsient
    public JViewport getColumnHebder() {
        return columnHebder;
    }


    /**
     * Removes the old columnHebder, if it exists; if the new columnHebder
     * isn't <code>null</code>, syncs the x coordinbte of its viewPosition
     * with the viewport (if there is one) bnd then bdds it to the scroll pbne.
     * <p>
     * Most bpplicbtions will find it more convenient to use
     * <code>setColumnHebderView</code>
     * to bdd b column hebder component bnd its viewport to the scroll pbne.
     *
     * @pbrbm columnHebder  b {@code JViewport} which is the new column hebder
     * @see #getColumnHebder
     * @see #setColumnHebderView
     *
     * @bebninfo
     *        bound: true
     *  description: The column hebder child for this scrollpbne
     *    bttribute: visublUpdbte true
     */
    public void setColumnHebder(JViewport columnHebder) {
        JViewport old = getColumnHebder();
        this.columnHebder = columnHebder;
        if (columnHebder != null) {
            bdd(columnHebder, COLUMN_HEADER);
        }
        else if (old != null) {
            remove(old);
        }
        firePropertyChbnge("columnHebder", old, columnHebder);

        revblidbte();
        repbint();
    }



    /**
     * Crebtes b column-hebder viewport if necessbry, sets
     * its view, bnd then bdds the column-hebder viewport
     * to the scrollpbne.  For exbmple:
     * <pre>
     * JScrollPbne scrollpbne = new JScrollPbne();
     * scrollpbne.setViewportView(myBigComponentToScroll);
     * scrollpbne.setColumnHebderView(myBigComponentsColumnHebder);
     * </pre>
     *
     * @see #setColumnHebder
     * @see JViewport#setView
     *
     * @pbrbm view the component to displby bs the column hebder
     */
    public void setColumnHebderView(Component view) {
        if (getColumnHebder() == null) {
            setColumnHebder(crebteViewport());
        }
        getColumnHebder().setView(view);
    }


    /**
     * Returns the component bt the specified corner. The
     * <code>key</code> vblue specifying the corner is one of:
     * <ul>
     * <li>ScrollPbneConstbnts.LOWER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.LOWER_RIGHT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_RIGHT_CORNER
     * <li>ScrollPbneConstbnts.LOWER_LEADING_CORNER
     * <li>ScrollPbneConstbnts.LOWER_TRAILING_CORNER
     * <li>ScrollPbneConstbnts.UPPER_LEADING_CORNER
     * <li>ScrollPbneConstbnts.UPPER_TRAILING_CORNER
     * </ul>
     *
     * @pbrbm key one of the vblues bs shown bbove
     * @return the corner component (which mby be <code>null</code>)
     *         identified by the given key, or <code>null</code>
     *         if the key is invblid
     * @see #setCorner
     */
    public Component getCorner(String key) {
        boolebn isLeftToRight = getComponentOrientbtion().isLeftToRight();
        if (key.equbls(LOWER_LEADING_CORNER)) {
            key = isLeftToRight ? LOWER_LEFT_CORNER : LOWER_RIGHT_CORNER;
        } else if (key.equbls(LOWER_TRAILING_CORNER)) {
            key = isLeftToRight ? LOWER_RIGHT_CORNER : LOWER_LEFT_CORNER;
        } else if (key.equbls(UPPER_LEADING_CORNER)) {
            key = isLeftToRight ? UPPER_LEFT_CORNER : UPPER_RIGHT_CORNER;
        } else if (key.equbls(UPPER_TRAILING_CORNER)) {
            key = isLeftToRight ? UPPER_RIGHT_CORNER : UPPER_LEFT_CORNER;
        }
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
     * Adds b child thbt will bppebr in one of the scroll pbnes
     * corners, if there's room.   For exbmple with both scrollbbrs
     * showing (on the right bnd bottom edges of the scrollpbne)
     * the lower left corner component will be shown in the spbce
     * between ends of the two scrollbbrs. Legbl vblues for
     * the <b>key</b> bre:
     * <ul>
     * <li>ScrollPbneConstbnts.LOWER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.LOWER_RIGHT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_LEFT_CORNER
     * <li>ScrollPbneConstbnts.UPPER_RIGHT_CORNER
     * <li>ScrollPbneConstbnts.LOWER_LEADING_CORNER
     * <li>ScrollPbneConstbnts.LOWER_TRAILING_CORNER
     * <li>ScrollPbneConstbnts.UPPER_LEADING_CORNER
     * <li>ScrollPbneConstbnts.UPPER_TRAILING_CORNER
     * </ul>
     * <p>
     * Although "corner" doesn't mbtch bny bebns property
     * signbture, <code>PropertyChbnge</code> events bre generbted with the
     * property nbme set to the corner key.
     *
     * @pbrbm key identifies which corner the component will bppebr in
     * @pbrbm corner one of the following components:
     * <ul>
     * <li>lowerLeft
     * <li>lowerRight
     * <li>upperLeft
     * <li>upperRight
     * </ul>
     * @exception IllegblArgumentException if corner key is invblid
     */
    public void setCorner(String key, Component corner)
    {
        Component old;
        boolebn isLeftToRight = getComponentOrientbtion().isLeftToRight();
        if (key.equbls(LOWER_LEADING_CORNER)) {
            key = isLeftToRight ? LOWER_LEFT_CORNER : LOWER_RIGHT_CORNER;
        } else if (key.equbls(LOWER_TRAILING_CORNER)) {
            key = isLeftToRight ? LOWER_RIGHT_CORNER : LOWER_LEFT_CORNER;
        } else if (key.equbls(UPPER_LEADING_CORNER)) {
            key = isLeftToRight ? UPPER_LEFT_CORNER : UPPER_RIGHT_CORNER;
        } else if (key.equbls(UPPER_TRAILING_CORNER)) {
            key = isLeftToRight ? UPPER_RIGHT_CORNER : UPPER_LEFT_CORNER;
        }
        if (key.equbls(LOWER_LEFT_CORNER)) {
            old = lowerLeft;
            lowerLeft = corner;
        }
        else if (key.equbls(LOWER_RIGHT_CORNER)) {
            old = lowerRight;
            lowerRight = corner;
        }
        else if (key.equbls(UPPER_LEFT_CORNER)) {
            old = upperLeft;
            upperLeft = corner;
        }
        else if (key.equbls(UPPER_RIGHT_CORNER)) {
            old = upperRight;
            upperRight = corner;
        }
        else {
            throw new IllegblArgumentException("invblid corner key");
        }
        if (old != null) {
            remove(old);
        }
        if (corner != null) {
            bdd(corner, key);
        }
        firePropertyChbnge(key, old, corner);
        revblidbte();
        repbint();
    }

    /**
     * Sets the orientbtion for the verticbl bnd horizontbl
     * scrollbbrs bs determined by the
     * <code>ComponentOrientbtion</code> brgument.
     *
     * @pbrbm  co one of the following vblues:
     * <ul>
     * <li>jbvb.bwt.ComponentOrientbtion.LEFT_TO_RIGHT
     * <li>jbvb.bwt.ComponentOrientbtion.RIGHT_TO_LEFT
     * <li>jbvb.bwt.ComponentOrientbtion.UNKNOWN
     * </ul>
     * @see jbvb.bwt.ComponentOrientbtion
     */
    public void setComponentOrientbtion( ComponentOrientbtion co ) {
        super.setComponentOrientbtion( co );
        if( verticblScrollBbr != null )
            verticblScrollBbr.setComponentOrientbtion( co );
        if( horizontblScrollBbr != null )
            horizontblScrollBbr.setComponentOrientbtion( co );
    }

    /**
     * Indicbtes whether or not scrolling will tbke plbce in response to the
     * mouse wheel.  Wheel scrolling is enbbled by defbult.
     *
     * @return true if mouse wheel scrolling is enbbled, fblse otherwise
     * @see #setWheelScrollingEnbbled
     * @since 1.4
     * @bebninfo
     *       bound: true
     * description: Flbg for enbbling/disbbling mouse wheel scrolling
     */
    public boolebn isWheelScrollingEnbbled() {return wheelScrollStbte;}

    /**
     * Enbbles/disbbles scrolling in response to movement of the mouse wheel.
     * Wheel scrolling is enbbled by defbult.
     *
     * @pbrbm hbndleWheel   <code>true</code> if scrolling should be done
     *                      butombticblly for b MouseWheelEvent,
     *                      <code>fblse</code> otherwise.
     * @see #isWheelScrollingEnbbled
     * @see jbvb.bwt.event.MouseWheelEvent
     * @see jbvb.bwt.event.MouseWheelListener
     * @since 1.4
     * @bebninfo
     *       bound: true
     * description: Flbg for enbbling/disbbling mouse wheel scrolling
     */
    public void setWheelScrollingEnbbled(boolebn hbndleWheel) {
        boolebn old = wheelScrollStbte;
        wheelScrollStbte = hbndleWheel;
        firePropertyChbnge("wheelScrollingEnbbled", old, hbndleWheel);
    }

    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
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
     * Returns b string representbtion of this <code>JScrollPbne</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JScrollPbne</code>.
     */
    protected String pbrbmString() {
        String viewportBorderString = (viewportBorder != null ?
                                       viewportBorder.toString() : "");
        String viewportString = (viewport != null ?
                                 viewport.toString() : "");
        String verticblScrollBbrPolicyString;
        if (verticblScrollBbrPolicy == VERTICAL_SCROLLBAR_AS_NEEDED) {
            verticblScrollBbrPolicyString = "VERTICAL_SCROLLBAR_AS_NEEDED";
        } else if (verticblScrollBbrPolicy == VERTICAL_SCROLLBAR_NEVER) {
            verticblScrollBbrPolicyString = "VERTICAL_SCROLLBAR_NEVER";
        } else if (verticblScrollBbrPolicy == VERTICAL_SCROLLBAR_ALWAYS) {
            verticblScrollBbrPolicyString = "VERTICAL_SCROLLBAR_ALWAYS";
        } else verticblScrollBbrPolicyString = "";
        String horizontblScrollBbrPolicyString;
        if (horizontblScrollBbrPolicy == HORIZONTAL_SCROLLBAR_AS_NEEDED) {
            horizontblScrollBbrPolicyString = "HORIZONTAL_SCROLLBAR_AS_NEEDED";
        } else if (horizontblScrollBbrPolicy == HORIZONTAL_SCROLLBAR_NEVER) {
            horizontblScrollBbrPolicyString = "HORIZONTAL_SCROLLBAR_NEVER";
        } else if (horizontblScrollBbrPolicy == HORIZONTAL_SCROLLBAR_ALWAYS) {
            horizontblScrollBbrPolicyString = "HORIZONTAL_SCROLLBAR_ALWAYS";
        } else horizontblScrollBbrPolicyString = "";
        String horizontblScrollBbrString = (horizontblScrollBbr != null ?
                                            horizontblScrollBbr.toString()
                                            : "");
        String verticblScrollBbrString = (verticblScrollBbr != null ?
                                          verticblScrollBbr.toString() : "");
        String columnHebderString = (columnHebder != null ?
                                     columnHebder.toString() : "");
        String rowHebderString = (rowHebder != null ?
                                  rowHebder.toString() : "");
        String lowerLeftString = (lowerLeft != null ?
                                  lowerLeft.toString() : "");
        String lowerRightString = (lowerRight != null ?
                                  lowerRight.toString() : "");
        String upperLeftString = (upperLeft != null ?
                                  upperLeft.toString() : "");
        String upperRightString = (upperRight != null ?
                                  upperRight.toString() : "");

        return super.pbrbmString() +
        ",columnHebder=" + columnHebderString +
        ",horizontblScrollBbr=" + horizontblScrollBbrString +
        ",horizontblScrollBbrPolicy=" + horizontblScrollBbrPolicyString +
        ",lowerLeft=" + lowerLeftString +
        ",lowerRight=" + lowerRightString +
        ",rowHebder=" + rowHebderString +
        ",upperLeft=" + upperLeftString +
        ",upperRight=" + upperRightString +
        ",verticblScrollBbr=" + verticblScrollBbrString +
        ",verticblScrollBbrPolicy=" + verticblScrollBbrPolicyString +
        ",viewport=" + viewportString +
        ",viewportBorder=" + viewportBorderString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JScrollPbne.
     * For scroll pbnes, the AccessibleContext tbkes the form of bn
     * AccessibleJScrollPbne.
     * A new AccessibleJScrollPbne instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJScrollPbne thbt serves bs the
     *         AccessibleContext of this JScrollPbne
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJScrollPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JScrollPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to scroll pbne user-interfbce
     * elements.
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
    protected clbss AccessibleJScrollPbne extends AccessibleJComponent
        implements ChbngeListener, PropertyChbngeListener {

        /**
         * this {@code JScrollPbne}'s current {@code JViewport}
         */
        protected JViewport viewPort = null;

        /**
         * Resets the viewport ChbngeListener bnd PropertyChbngeListener
         */
        public void resetViewPort() {
            if (viewPort != null) {
                viewPort.removeChbngeListener(this);
                viewPort.removePropertyChbngeListener(this);
            }
            viewPort = JScrollPbne.this.getViewport();
            if (viewPort != null) {
                viewPort.bddChbngeListener(this);
                viewPort.bddPropertyChbngeListener(this);
            }
        }

        /**
         * AccessibleJScrollPbne constructor
         */
        public AccessibleJScrollPbne() {
            super();

            resetViewPort();

            // initiblize the AccessibleRelbtionSets for the JScrollPbne
            // bnd JScrollBbr(s)
            JScrollBbr scrollBbr = getHorizontblScrollBbr();
            if (scrollBbr != null) {
                setScrollBbrRelbtions(scrollBbr);
            }
            scrollBbr = getVerticblScrollBbr();
            if (scrollBbr != null) {
                setScrollBbrRelbtions(scrollBbr);
            }
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SCROLL_PANE;
        }

        /**
         * Invoked when the tbrget of the listener hbs chbnged its stbte.
         *
         * @pbrbm e  b <code>ChbngeEvent</code> object. Must not be null.
         *
         * @throws NullPointerException if the pbrbmeter is null.
         */
        public void stbteChbnged(ChbngeEvent e) {
            if (e == null) {
                throw new NullPointerException();
            }
            firePropertyChbnge(ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolebn.vblueOf(fblse),
                               Boolebn.vblueOf(true));
        }

        /**
         * This method gets cblled when b bound property is chbnged.
         * @pbrbm e A <code>PropertyChbngeEvent</code> object describing
         * the event source bnd the property thbt hbs chbnged. Must not be null.
         *
         * @throws NullPointerException if the pbrbmeter is null.
         * @since 1.5
         */
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            if (propertyNbme == "horizontblScrollBbr" ||
                propertyNbme == "verticblScrollBbr") {

                if (e.getNewVblue() instbnceof JScrollBbr) {
                    setScrollBbrRelbtions((JScrollBbr)e.getNewVblue());
                }
            }
        }


        /*
         * Sets the CONTROLLER_FOR bnd CONTROLLED_BY AccessibleRelbtions for
         * the JScrollPbne bnd JScrollBbr. JScrollBbr must not be null.
         */
        void setScrollBbrRelbtions(JScrollBbr scrollBbr) {
            /*
             * The JScrollBbr is b CONTROLLER_FOR the JScrollPbne.
             * The JScrollPbne is CONTROLLED_BY the JScrollBbr.
             */
            AccessibleRelbtion controlledBy =
                new AccessibleRelbtion(AccessibleRelbtion.CONTROLLED_BY,
                                       scrollBbr);
            AccessibleRelbtion controllerFor =
                new AccessibleRelbtion(AccessibleRelbtion.CONTROLLER_FOR,
                                       JScrollPbne.this);

            // set the relbtion set for the scroll bbr
            AccessibleContext bc = scrollBbr.getAccessibleContext();
            bc.getAccessibleRelbtionSet().bdd(controllerFor);

            // set the relbtion set for the scroll pbne
            getAccessibleRelbtionSet().bdd(controlledBy);
        }
    }
}
