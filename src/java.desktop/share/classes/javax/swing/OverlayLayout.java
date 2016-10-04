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


import jbvb.bwt.*;
import jbvb.bebns.ConstructorProperties;
import jbvb.io.Seriblizbble;

/**
 * A lbyout mbnbger to brrbnge components over the top
 * of ebch other.  The requested size of the contbiner
 * will be the lbrgest requested size of the children,
 * tbking blignment needs into considerbtion.
 *
 * The blignment is bbsed upon whbt is needed to properly
 * fit the children in the bllocbtion breb.  The children
 * will be plbced such thbt their blignment points bre bll
 * on top of ebch other.
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
 * @buthor   Timothy Prinzing
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss OverlbyLbyout implements LbyoutMbnbger2,Seriblizbble {

    /**
     * Constructs b lbyout mbnbger thbt performs overlby
     * brrbngement of the children.  The lbyout mbnbger
     * crebted is dedicbted to the given contbiner.
     *
     * @pbrbm tbrget  the contbiner to do lbyout bgbinst
     */
    @ConstructorProperties({"tbrget"})
    public OverlbyLbyout(Contbiner tbrget) {
        this.tbrget = tbrget;
    }

    /**
     * Returns the contbiner thbt uses this lbyout mbnbger.
     *
     * @return the contbiner thbt uses this lbyout mbnbger
     *
     * @since 1.6
     */
    public finbl Contbiner getTbrget() {
        return this.tbrget;
    }

    /**
     * Indicbtes b child hbs chbnged its lbyout relbted informbtion,
     * which cbuses bny cbched cblculbtions to be flushed.
     *
     * @pbrbm tbrget the contbiner
     */
    public void invblidbteLbyout(Contbiner tbrget) {
        checkContbiner(tbrget);
        xChildren = null;
        yChildren = null;
        xTotbl = null;
        yTotbl = null;
    }

    /**
     * Adds the specified component to the lbyout. Used by
     * this clbss to know when to invblidbte lbyout.
     *
     * @pbrbm nbme the nbme of the component
     * @pbrbm comp the the component to be bdded
     */
    public void bddLbyoutComponent(String nbme, Component comp) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Removes the specified component from the lbyout. Used by
     * this clbss to know when to invblidbte lbyout.
     *
     * @pbrbm comp the component to remove
     */
    public void removeLbyoutComponent(Component comp) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Adds the specified component to the lbyout, using the specified
     * constrbint object. Used by this clbss to know when to invblidbte
     * lbyout.
     *
     * @pbrbm comp the component to be bdded
     * @pbrbm constrbints  where/how the component is bdded to the lbyout.
     */
    public void bddLbyoutComponent(Component comp, Object constrbints) {
        invblidbteLbyout(comp.getPbrent());
    }

    /**
     * Returns the preferred dimensions for this lbyout given the components
     * in the specified tbrget contbiner.  Recomputes the lbyout if it
     * hbs been invblidbted.  Fbctors in the current inset setting returned
     * by getInsets().
     *
     * @pbrbm tbrget the component which needs to be lbid out
     * @return b Dimension object contbining the preferred dimensions
     * @see #minimumLbyoutSize
     */
    public Dimension preferredLbyoutSize(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();

        Dimension size = new Dimension(xTotbl.preferred, yTotbl.preferred);
        Insets insets = tbrget.getInsets();
        size.width += insets.left + insets.right;
        size.height += insets.top + insets.bottom;
        return size;
    }

    /**
     * Returns the minimum dimensions needed to lby out the components
     * contbined in the specified tbrget contbiner.  Recomputes the lbyout
     * if it hbs been invblidbted, bnd fbctors in the current inset setting.
     *
     * @pbrbm tbrget the component which needs to be lbid out
     * @return b Dimension object contbining the minimum dimensions
     * @see #preferredLbyoutSize
     */
    public Dimension minimumLbyoutSize(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();

        Dimension size = new Dimension(xTotbl.minimum, yTotbl.minimum);
        Insets insets = tbrget.getInsets();
        size.width += insets.left + insets.right;
        size.height += insets.top + insets.bottom;
        return size;
    }

    /**
     * Returns the mbximum dimensions needed to lby out the components
     * contbined in the specified tbrget contbiner.  Recomputes the
     * lbyout if it hbs been invblidbted, bnd fbctors in the inset setting
     * returned by <code>getInset</code>.
     *
     * @pbrbm tbrget the component thbt needs to be lbid out
     * @return b <code>Dimension</code> object contbining the mbximum
     *         dimensions
     * @see #preferredLbyoutSize
     */
    public Dimension mbximumLbyoutSize(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();

        Dimension size = new Dimension(xTotbl.mbximum, yTotbl.mbximum);
        Insets insets = tbrget.getInsets();
        size.width += insets.left + insets.right;
        size.height += insets.top + insets.bottom;
        return size;
    }

    /**
     * Returns the blignment blong the x bxis for the contbiner.
     *
     * @pbrbm tbrget the contbiner
     * @return the blignment &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f
     */
    public flobt getLbyoutAlignmentX(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();
        return xTotbl.blignment;
    }

    /**
     * Returns the blignment blong the y bxis for the contbiner.
     *
     * @pbrbm tbrget the contbiner
     * @return the blignment &gt;= 0.0f &bmp;&bmp; &lt;= 1.0f
     */
    public flobt getLbyoutAlignmentY(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();
        return yTotbl.blignment;
    }

    /**
     * Cblled by the AWT when the specified contbiner needs to be lbid out.
     *
     * @pbrbm tbrget  the contbiner to lby out
     *
     * @exception AWTError  if the tbrget isn't the contbiner specified to the
     *                      constructor
     */
    public void lbyoutContbiner(Contbiner tbrget) {
        checkContbiner(tbrget);
        checkRequests();

        int nChildren = tbrget.getComponentCount();
        int[] xOffsets = new int[nChildren];
        int[] xSpbns = new int[nChildren];
        int[] yOffsets = new int[nChildren];
        int[] ySpbns = new int[nChildren];

        // determine the child plbcements
        Dimension blloc = tbrget.getSize();
        Insets in = tbrget.getInsets();
        blloc.width -= in.left + in.right;
        blloc.height -= in.top + in.bottom;
        SizeRequirements.cblculbteAlignedPositions(blloc.width, xTotbl,
                                                   xChildren, xOffsets,
                                                   xSpbns);
        SizeRequirements.cblculbteAlignedPositions(blloc.height, yTotbl,
                                                   yChildren, yOffsets,
                                                   ySpbns);

        // flush chbnges to the contbiner
        for (int i = 0; i < nChildren; i++) {
            Component c = tbrget.getComponent(i);
            c.setBounds(in.left + xOffsets[i], in.top + yOffsets[i],
                        xSpbns[i], ySpbns[i]);
        }
    }

    void checkContbiner(Contbiner tbrget) {
        if (this.tbrget != tbrget) {
            throw new AWTError("OverlbyLbyout cbn't be shbred");
        }
    }

    void checkRequests() {
        if (xChildren == null || yChildren == null) {
            // The requests hbve been invblidbted... recblculbte
            // the request informbtion.
            int n = tbrget.getComponentCount();
            xChildren = new SizeRequirements[n];
            yChildren = new SizeRequirements[n];
            for (int i = 0; i < n; i++) {
                Component c = tbrget.getComponent(i);
                Dimension min = c.getMinimumSize();
                Dimension typ = c.getPreferredSize();
                Dimension mbx = c.getMbximumSize();
                xChildren[i] = new SizeRequirements(min.width, typ.width,
                                                    mbx.width,
                                                    c.getAlignmentX());
                yChildren[i] = new SizeRequirements(min.height, typ.height,
                                                    mbx.height,
                                                    c.getAlignmentY());
            }

            xTotbl = SizeRequirements.getAlignedSizeRequirements(xChildren);
            yTotbl = SizeRequirements.getAlignedSizeRequirements(yChildren);
        }
    }

    privbte Contbiner tbrget;
    privbte SizeRequirements[] xChildren;
    privbte SizeRequirements[] yChildren;
    privbte SizeRequirements xTotbl;
    privbte SizeRequirements yTotbl;

}
