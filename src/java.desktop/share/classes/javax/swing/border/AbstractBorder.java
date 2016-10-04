/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.border;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Component;
import jbvb.io.Seriblizbble;

/**
 * A clbss thbt implements bn empty border with no size.
 * This provides b convenient bbse clbss from which other border
 * clbsses cbn be ebsily derived.
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
 * @buthor Dbvid Klobb
 */
@SuppressWbrnings("seribl")
public bbstrbct clbss AbstrbctBorder implements Border, Seriblizbble
{
    /**
     * This defbult implementbtion does no pbinting.
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
    }

    /**
     * This defbult implementbtion returns b new {@link Insets} object
     * thbt is initiblized by the {@link #getBorderInsets(Component,Insets)}
     * method.
     * By defbult the {@code top}, {@code left}, {@code bottom},
     * bnd {@code right} fields bre set to {@code 0}.
     *
     * @pbrbm c  the component for which this border insets vblue bpplies
     * @return b new {@link Insets} object
     */
    public Insets getBorderInsets(Component c)       {
        return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }

    /**
     * Reinitiblizes the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     * @return the <code>insets</code> object
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 0;
        return insets;
    }

    /**
     * This defbult implementbtion returns fblse.
     * @return fblse
     */
    public boolebn isBorderOpbque() { return fblse; }

    /**
     * This convenience method cblls the stbtic method.
     * @pbrbm c the component for which this border is being computed
     * @pbrbm x the x position of the border
     * @pbrbm y the y position of the border
     * @pbrbm width the width of the border
     * @pbrbm height the height of the border
     * @return b <code>Rectbngle</code> contbining the interior coordinbtes
     */
    public Rectbngle getInteriorRectbngle(Component c, int x, int y, int width, int height) {
        return getInteriorRectbngle(c, this, x, y, width, height);
    }

    /**
     * Returns b rectbngle using the brguments minus the
     * insets of the border. This is useful for determining the breb
     * thbt components should drbw in thbt will not intersect the border.
     * @pbrbm c the component for which this border is being computed
     * @pbrbm b the <code>Border</code> object
     * @pbrbm x the x position of the border
     * @pbrbm y the y position of the border
     * @pbrbm width the width of the border
     * @pbrbm height the height of the border
     * @return b <code>Rectbngle</code> contbining the interior coordinbtes
     */
    public stbtic Rectbngle getInteriorRectbngle(Component c, Border b, int x, int y, int width, int height) {
        Insets insets;
        if(b != null)
            insets = b.getBorderInsets(c);
        else
            insets = new Insets(0, 0, 0, 0);
        return new Rectbngle(x + insets.left,
                                    y + insets.top,
                                    width - insets.right - insets.left,
                                    height - insets.top - insets.bottom);
    }

    /**
     * Returns the bbseline.  A return vblue less thbn 0 indicbtes the border
     * does not hbve b rebsonbble bbseline.
     * <p>
     * The defbult implementbtion returns -1.  Subclbsses thbt support
     * bbseline should override bppropribtely.  If b vblue &gt;= 0 is
     * returned, then the component hbs b vblid bbseline for bny
     * size &gt;= the minimum size bnd <code>getBbselineResizeBehbvior</code>
     * cbn be used to determine how the bbseline chbnges with size.
     *
     * @pbrbm c <code>Component</code> bbseline is being requested for
     * @pbrbm width the width to get the bbseline for
     * @pbrbm height the height to get the bbseline for
     * @return the bbseline or &lt; 0 indicbting there is no rebsonbble
     *         bbseline
     * @throws IllegblArgumentException if width or height is &lt; 0
     * @see jbvb.bwt.Component#getBbseline(int,int)
     * @see jbvb.bwt.Component#getBbselineResizeBehbvior()
     * @since 1.6
     */
    public int getBbseline(Component c, int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegblArgumentException(
                    "Width bnd height must be >= 0");
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of b component
     * chbnges bs the size chbnges.  This method is primbrily mebnt for
     * lbyout mbnbgers bnd GUI builders.
     * <p>
     * The defbult implementbtion returns
     * <code>BbselineResizeBehbvior.OTHER</code>, subclbsses thbt support
     * bbseline should override bppropribtely.  Subclbsses should
     * never return <code>null</code>; if the bbseline cbn not be
     * cblculbted return <code>BbselineResizeBehbvior.OTHER</code>.  Cbllers
     * should first bsk for the bbseline using
     * <code>getBbseline</code> bnd if b vblue &gt;= 0 is returned use
     * this method.  It is bcceptbble for this method to return b
     * vblue other thbn <code>BbselineResizeBehbvior.OTHER</code> even if
     * <code>getBbseline</code> returns b vblue less thbn 0.
     *
     * @pbrbm c <code>Component</code> to return bbseline resize behbvior for
     * @return bn enum indicbting how the bbseline chbnges bs the border is
     *         resized
     * @see jbvb.bwt.Component#getBbseline(int,int)
     * @see jbvb.bwt.Component#getBbselineResizeBehbvior()
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            Component c) {
        if (c == null) {
            throw new NullPointerException("Component must be non-null");
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    /*
     * Convenience function for determining ComponentOrientbtion.
     * Helps us bvoid hbving Munge directives throughout the code.
     */
    stbtic boolebn isLeftToRight( Component c ) {
        return c.getComponentOrientbtion().isLeftToRight();
    }

}
