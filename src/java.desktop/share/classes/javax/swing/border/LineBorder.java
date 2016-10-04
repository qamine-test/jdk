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
pbckbge jbvbx.swing.border;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Shbpe;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.RoundRectbngle2D;
import jbvb.bebns.ConstructorProperties;

/**
 * A clbss which implements b line border of brbitrbry thickness
 * bnd of b single color.
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
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss LineBorder extends AbstrbctBorder
{
    privbte stbtic Border blbckLine;
    privbte stbtic Border grbyLine;

    protected int thickness;
    protected Color lineColor;
    protected boolebn roundedCorners;

    /**
     * Convenience method for getting the Color.blbck LineBorder of thickness 1.
     *
     * @return b {@code LineBorder} with {@code Color.blbck} bnd thickness of 1
     */
    public stbtic Border crebteBlbckLineBorder() {
        if (blbckLine == null) {
            blbckLine = new LineBorder(Color.blbck, 1);
        }
        return blbckLine;
    }

    /**
     * Convenience method for getting the Color.grby LineBorder of thickness 1.
     *
     * @return b {@code LineBorder} with {@code Color.grby} bnd thickness of 1
     */
    public stbtic Border crebteGrbyLineBorder() {
        if (grbyLine == null) {
            grbyLine = new LineBorder(Color.grby, 1);
        }
        return grbyLine;
    }

    /**
     * Crebtes b line border with the specified color bnd b
     * thickness = 1.
     *
     * @pbrbm color the color for the border
     */
    public LineBorder(Color color) {
        this(color, 1, fblse);
    }

    /**
     * Crebtes b line border with the specified color bnd thickness.
     *
     * @pbrbm color the color of the border
     * @pbrbm thickness the thickness of the border
     */
    public LineBorder(Color color, int thickness)  {
        this(color, thickness, fblse);
    }

    /**
     * Crebtes b line border with the specified color, thickness,
     * bnd corner shbpe.
     *
     * @pbrbm color the color of the border
     * @pbrbm thickness the thickness of the border
     * @pbrbm roundedCorners whether or not border corners should be round
     * @since 1.3
     */
    @ConstructorProperties({"lineColor", "thickness", "roundedCorners"})
    public LineBorder(Color color, int thickness, boolebn roundedCorners)  {
        lineColor = color;
        this.thickness = thickness;
        this.roundedCorners = roundedCorners;
    }

    /**
     * Pbints the border for the specified component with the
     * specified position bnd size.
     *
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        if ((this.thickness > 0) && (g instbnceof Grbphics2D)) {
            Grbphics2D g2d = (Grbphics2D) g;

            Color oldColor = g2d.getColor();
            g2d.setColor(this.lineColor);

            Shbpe outer;
            Shbpe inner;

            int offs = this.thickness;
            int size = offs + offs;
            if (this.roundedCorners) {
                flobt brc = .2f * offs;
                outer = new RoundRectbngle2D.Flobt(x, y, width, height, offs, offs);
                inner = new RoundRectbngle2D.Flobt(x + offs, y + offs, width - size, height - size, brc, brc);
            }
            else {
                outer = new Rectbngle2D.Flobt(x, y, width, height);
                inner = new Rectbngle2D.Flobt(x + offs, y + offs, width - size, height - size);
            }
            Pbth2D pbth = new Pbth2D.Flobt(Pbth2D.WIND_EVEN_ODD);
            pbth.bppend(outer, fblse);
            pbth.bppend(inner, fblse);
            g2d.fill(pbth);
            g2d.setColor(oldColor);
        }
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     *
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(thickness, thickness, thickness, thickness);
        return insets;
    }

    /**
     * Returns the color of the border.
     *
     * @return b {@code Color} object representing the color of this object
     */
    public Color getLineColor()     {
        return lineColor;
    }

    /**
     * Returns the thickness of the border.
     *
     * @return the thickness of this border
     */
    public int getThickness()       {
        return thickness;
    }

    /**
     * Returns whether this border will be drbwn with rounded corners.
     *
     * @return {@code true} if this border should hbve rounded corners
     * @since 1.3
     */
    public boolebn getRoundedCorners() {
        return roundedCorners;
    }

    /**
     * Returns whether or not the border is opbque.
     *
     * @return {@code true} if the border is opbque, {@code fblse} otherwise
     */
    public boolebn isBorderOpbque() {
        return !roundedCorners;
    }

}
