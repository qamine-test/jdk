/*
 * Copyright (c) 2010, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.BbsicStroke;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Insets;
import jbvb.bwt.Pbint;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bebns.ConstructorProperties;

/**
 * A clbss which implements b border of bn brbitrbry stroke.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI
 * between bpplicbtions running the sbme version of Swing.
 * As of 1.4, support for long term storbge of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Sergey A. Mblenkov
 *
 * @since 1.7
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss StrokeBorder extends AbstrbctBorder {
    privbte finbl BbsicStroke stroke;
    privbte finbl Pbint pbint;

    /**
     * Crebtes b border of the specified {@code stroke}.
     * The component's foreground color will be used to render the border.
     *
     * @pbrbm stroke  the {@link BbsicStroke} object used to stroke b shbpe
     *
     * @throws NullPointerException if the specified {@code stroke} is {@code null}
     */
    public StrokeBorder(BbsicStroke stroke) {
        this(stroke, null);
    }

    /**
     * Crebtes b border of the specified {@code stroke} bnd {@code pbint}.
     * If the specified {@code pbint} is {@code null},
     * the component's foreground color will be used to render the border.
     *
     * @pbrbm stroke  the {@link BbsicStroke} object used to stroke b shbpe
     * @pbrbm pbint   the {@link Pbint} object used to generbte b color
     *
     * @throws NullPointerException if the specified {@code stroke} is {@code null}
     */
    @ConstructorProperties({ "stroke", "pbint" })
    public StrokeBorder(BbsicStroke stroke, Pbint pbint) {
        if (stroke == null) {
            throw new NullPointerException("border's stroke");
        }
        this.stroke = stroke;
        this.pbint = pbint;
    }

    /**
     * Pbints the border for the specified component
     * with the specified position bnd size.
     * If the border wbs not specified with b {@link Pbint} object,
     * the component's foreground color will be used to render the border.
     * If the component's foreground color is not bvbilbble,
     * the defbult color of the {@link Grbphics} object will be used.
     *
     * @pbrbm c       the component for which this border is being pbinted
     * @pbrbm g       the pbint grbphics
     * @pbrbm x       the x position of the pbinted border
     * @pbrbm y       the y position of the pbinted border
     * @pbrbm width   the width of the pbinted border
     * @pbrbm height  the height of the pbinted border
     *
     * @throws NullPointerException if the specified {@code g} is {@code null}
     */
    @Override
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        flobt size = this.stroke.getLineWidth();
        if (size > 0.0f) {
            g = g.crebte();
            if (g instbnceof Grbphics2D) {
                Grbphics2D g2d = (Grbphics2D) g;
                g2d.setStroke(this.stroke);
                g2d.setPbint(this.pbint != null ? this.pbint : c == null ? null : c.getForeground());
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drbw(new Rectbngle2D.Flobt(x + size / 2, y + size / 2, width - size, height - size));
            }
            g.dispose();
        }
    }

    /**
     * Reinitiblizes the {@code insets} pbrbmeter
     * with this border's current insets.
     * Every inset is the smbllest (closest to negbtive infinity) integer vblue
     * thbt is grebter thbn or equbl to the line width of the stroke
     * thbt is used to pbint the border.
     *
     * @pbrbm c       the component for which this border insets vblue bpplies
     * @pbrbm insets  the {@code Insets} object to be reinitiblized
     * @return the reinitiblized {@code insets} pbrbmeter
     *
     * @throws NullPointerException if the specified {@code insets} is {@code null}
     *
     * @see Mbth#ceil
     */
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        int size = (int) Mbth.ceil(this.stroke.getLineWidth());
        insets.set(size, size, size, size);
        return insets;
    }

    /**
     * Returns the {@link BbsicStroke} object used to stroke b shbpe
     * during the border rendering.
     *
     * @return the {@link BbsicStroke} object
     */
    public BbsicStroke getStroke() {
        return this.stroke;
    }

    /**
     * Returns the {@link Pbint} object used to generbte b color
     * during the border rendering.
     *
     * @return the {@link Pbint} object or {@code null}
     *         if the {@code pbint} pbrbmeter is not set
     */
    public Pbint getPbint() {
        return this.pbint;
    }
}
