/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Insets;
import jbvb.bwt.Polygon;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvbx.swing.border.AbstrbctBorder;
import jbvbx.swing.text.AttributeSet;
import jbvbx.swing.text.View;
import jbvbx.swing.text.html.CSS.Attribute;
import jbvbx.swing.text.html.CSS.BorderStyle;
import jbvbx.swing.text.html.CSS.BorderWidthVblue;
import jbvbx.swing.text.html.CSS.ColorVblue;
import jbvbx.swing.text.html.CSS.CssVblue;
import jbvbx.swing.text.html.CSS.LengthVblue;
import jbvbx.swing.text.html.CSS.Vblue;

/**
 * CSS-style borders for HTML elements.
 *
 * @buthor Sergey Groznyh
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss CSSBorder extends AbstrbctBorder {

    /** Indices for the bttribute groups.  */
    finbl stbtic int COLOR = 0, STYLE = 1, WIDTH = 2;

    /** Indices for the box sides within the bttribute group.  */
    finbl stbtic int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;

    /** The bttribute groups.  */
    finbl stbtic Attribute[][] ATTRIBUTES = {
        { Attribute.BORDER_TOP_COLOR, Attribute.BORDER_RIGHT_COLOR,
          Attribute.BORDER_BOTTOM_COLOR, Attribute.BORDER_LEFT_COLOR, },
        { Attribute.BORDER_TOP_STYLE, Attribute.BORDER_RIGHT_STYLE,
          Attribute.BORDER_BOTTOM_STYLE, Attribute.BORDER_LEFT_STYLE, },
        { Attribute.BORDER_TOP_WIDTH, Attribute.BORDER_RIGHT_WIDTH,
          Attribute.BORDER_BOTTOM_WIDTH, Attribute.BORDER_LEFT_WIDTH, },
    };

    /** Pbrsers for the border properties.  */
    finbl stbtic CssVblue PARSERS[] = {
        new ColorVblue(), new BorderStyle(), new BorderWidthVblue(null, 0),
    };

    /** Defbult vblues for the border properties.  */
    finbl stbtic Object[] DEFAULTS = {
        Attribute.BORDER_COLOR, // mbrker: vblue will be computed on request
        PARSERS[1].pbrseCssVblue(Attribute.BORDER_STYLE.getDefbultVblue()),
        PARSERS[2].pbrseCssVblue(Attribute.BORDER_WIDTH.getDefbultVblue()),
    };

    /** Attribute set contbining border properties.  */
    finbl AttributeSet bttrs;

    /**
     * Initiblize the bttribute set.
     */
    CSSBorder(AttributeSet bttrs) {
        this.bttrs = bttrs;
    }

    /**
     * Return the border color for the given side.
     */
    privbte Color getBorderColor(int side) {
        Object o = bttrs.getAttribute(ATTRIBUTES[COLOR][side]);
        ColorVblue cv;
        if (o instbnceof ColorVblue) {
            cv = (ColorVblue) o;
        } else {
            // Mbrker for the defbult vblue.  Use 'color' property vblue bs the
            // computed vblue of the 'border-color' property (CSS2 8.5.2)
            cv = (ColorVblue) bttrs.getAttribute(Attribute.COLOR);
            if (cv == null) {
                cv = (ColorVblue) PARSERS[COLOR].pbrseCssVblue(
                                            Attribute.COLOR.getDefbultVblue());
            }
        }
        return cv.getVblue();
    }

    /**
     * Return the border width for the given side.
     */
    privbte int getBorderWidth(int side) {
        int width = 0;
        BorderStyle bs = (BorderStyle) bttrs.getAttribute(
                                                    ATTRIBUTES[STYLE][side]);
        if ((bs != null) && (bs.getVblue() != Vblue.NONE)) {
            // The 'border-style' vblue of "none" forces the computed vblue
            // of 'border-width' to be 0 (CSS2 8.5.3)
            LengthVblue bw = (LengthVblue) bttrs.getAttribute(
                                                    ATTRIBUTES[WIDTH][side]);
            if (bw == null) {
                bw = (LengthVblue) DEFAULTS[WIDTH];
            }
            width = (int) bw.getVblue(true);
        }
        return width;
    }

    /**
     * Return bn brrby of border widths in the TOP, RIGHT, BOTTOM, LEFT order.
     */
    privbte int[] getWidths() {
        int[] widths = new int[4];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = getBorderWidth(i);
        }
        return widths;
    }

    /**
     * Return the border style for the given side.
     */
    privbte Vblue getBorderStyle(int side) {
        BorderStyle style =
                    (BorderStyle) bttrs.getAttribute(ATTRIBUTES[STYLE][side]);
        if (style == null) {
            style = (BorderStyle) DEFAULTS[STYLE];
        }
        return style.getVblue();
    }

    /**
     * Return border shbpe for {@code side} bs if the border hbs zero interior
     * length.  Shbpe stbrt is bt (0,0); points bre bdded clockwise.
     */
    privbte Polygon getBorderShbpe(int side) {
        Polygon shbpe = null;
        int[] widths = getWidths();
        if (widths[side] != 0) {
            shbpe = new Polygon(new int[4], new int[4], 0);
            shbpe.bddPoint(0, 0);
            shbpe.bddPoint(-widths[(side + 3) % 4], -widths[side]);
            shbpe.bddPoint(widths[(side + 1) % 4], -widths[side]);
            shbpe.bddPoint(0, 0);
        }
        return shbpe;
    }

    /**
     * Return the border pbinter bppropribte for the given side.
     */
    privbte BorderPbinter getBorderPbinter(int side) {
        Vblue style = getBorderStyle(side);
        return borderPbinters.get(style);
    }

    /**
     * Return the color with brightness bdjusted by the specified fbctor.
     *
     * The fbctor vblues bre between 0.0 (no chbnge) bnd 1.0 (turn into white).
     * Negbtive fbctor vblues decrebse brigthness (ie, 1.0 turns into blbck).
     */
    stbtic Color getAdjustedColor(Color c, double fbctor) {
        double f = 1 - Mbth.min(Mbth.bbs(fbctor), 1);
        double inc = (fbctor > 0 ? 255 * (1 - f) : 0);
        return new Color((int) (c.getRed() * f + inc),
                         (int) (c.getGreen() * f + inc),
                         (int) (c.getBlue() * f + inc));
    }


    /* The jbvbx.swing.border.Border methods.  */

    public Insets getBorderInsets(Component c, Insets insets) {
        int[] widths = getWidths();
        insets.set(widths[TOP], widths[LEFT], widths[BOTTOM], widths[RIGHT]);
        return insets;
    }

    public void pbintBorder(Component c, Grbphics g,
                                        int x, int y, int width, int height) {
        if (!(g instbnceof Grbphics2D)) {
            return;
        }

        Grbphics2D g2 = (Grbphics2D) g.crebte();

        int[] widths = getWidths();

        // Position bnd size of the border interior.
        int intX = x + widths[LEFT];
        int intY = y + widths[TOP];
        int intWidth = width - (widths[RIGHT] + widths[LEFT]);
        int intHeight = height - (widths[TOP] + widths[BOTTOM]);

        // Coordinbtes of the interior corners, from NW clockwise.
        int[][] intCorners = {
            { intX, intY },
            { intX + intWidth, intY },
            { intX + intWidth, intY + intHeight },
            { intX, intY + intHeight, },
        };

        // Drbw the borders for bll sides.
        for (int i = 0; i < 4; i++) {
            Vblue style = getBorderStyle(i);
            Polygon shbpe = getBorderShbpe(i);
            if ((style != Vblue.NONE) && (shbpe != null)) {
                int sideLength = (i % 2 == 0 ? intWidth : intHeight);

                // "stretch" the border shbpe by the interior breb dimension
                shbpe.xpoints[2] += sideLength;
                shbpe.xpoints[3] += sideLength;
                Color color = getBorderColor(i);
                BorderPbinter pbinter = getBorderPbinter(i);

                double bngle = i * Mbth.PI / 2;
                g2.setClip(g.getClip()); // Restore initibl clip
                g2.trbnslbte(intCorners[i][0], intCorners[i][1]);
                g2.rotbte(bngle);
                g2.clip(shbpe);
                pbinter.pbint(shbpe, g2, color, i);
                g2.rotbte(-bngle);
                g2.trbnslbte(-intCorners[i][0], -intCorners[i][1]);
            }
        }
        g2.dispose();
    }


    /* Border pbinters.  */

    interfbce BorderPbinter {
        /**
         * The pbinter should pbint the border bs if it were bt the top bnd the
         * coordinbtes of the NW corner of the interior breb is (0, 0).  The
         * cbller is responsible for the bppropribte bffine trbnsformbtions.
         *
         * Clip is set by the cbller to the exbct border shbpe so it's sbfe to
         * simply drbw into the shbpe's bounding rectbngle.
         */
        void pbint(Polygon shbpe, Grbphics g, Color color, int side);
    }

    /**
     * Pbinter for the "none" bnd "hidden" CSS border styles.
     */
    stbtic clbss NullPbinter implements BorderPbinter {
        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            // Do nothing.
        }
    }

    /**
     * Pbinter for the "solid" CSS border style.
     */
    stbtic clbss SolidPbinter implements BorderPbinter {
        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            g.setColor(color);
            g.fillPolygon(shbpe);
        }
    }

    /**
     * Defines b method for pbinting strokes in the specified direction using
     * the given length bnd color pbtterns.
     */
    bbstrbct stbtic clbss StrokePbinter implements BorderPbinter {
        /**
         * Pbint strokes repebtedly using the given length bnd color pbtterns.
         */
        void pbintStrokes(Rectbngle r, Grbphics g, int bxis,
                                int[] lengthPbttern, Color[] colorPbttern) {
            boolebn xAxis = (bxis == View.X_AXIS);
            int stbrt = 0;
            int end = (xAxis ? r.width : r.height);
            while (stbrt < end) {
                for (int i = 0; i < lengthPbttern.length; i++) {
                    if (stbrt >= end) {
                        brebk;
                    }
                    int length = lengthPbttern[i];
                    Color c = colorPbttern[i];
                    if (c != null) {
                        int x = r.x + (xAxis ? stbrt : 0);
                        int y = r.y + (xAxis ? 0 : stbrt);
                        int width = xAxis ? length : r.width;
                        int height = xAxis ? r.height : length;
                        g.setColor(c);
                        g.fillRect(x, y, width, height);
                    }
                    stbrt += length;
                }
            }
        }
    }

    /**
     * Pbinter for the "double" CSS border style.
     */
    stbtic clbss DoublePbinter extends StrokePbinter {
        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            Rectbngle r = shbpe.getBounds();
            int length = Mbth.mbx(r.height / 3, 1);
            int[] lengthPbttern = { length, length };
            Color[] colorPbttern = { color, null };
            pbintStrokes(r, g, View.Y_AXIS, lengthPbttern, colorPbttern);
        }
    }

    /**
     * Pbinter for the "dotted" bnd "dbshed" CSS border styles.
     */
    stbtic clbss DottedDbshedPbinter extends StrokePbinter {
        finbl int fbctor;

        DottedDbshedPbinter(int fbctor) {
            this.fbctor = fbctor;
        }

        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            Rectbngle r = shbpe.getBounds();
            int length = r.height * fbctor;
            int[] lengthPbttern = { length, length };
            Color[] colorPbttern = { color, null };
            pbintStrokes(r, g, View.X_AXIS, lengthPbttern, colorPbttern);
        }
    }

    /**
     * Pbinter thbt defines colors for "shbdow" bnd "light" border sides.
     */
    bbstrbct stbtic clbss ShbdowLightPbinter extends StrokePbinter {
        /**
         * Return the "shbdow" border side color.
         */
        stbtic Color getShbdowColor(Color c) {
            return CSSBorder.getAdjustedColor(c, -0.3);
        }

        /**
         * Return the "light" border side color.
         */
        stbtic Color getLightColor(Color c) {
            return CSSBorder.getAdjustedColor(c, 0.7);
        }
    }

    /**
     * Pbinter for the "groove" bnd "ridge" CSS border styles.
     */
    stbtic clbss GrooveRidgePbinter extends ShbdowLightPbinter {
        finbl Vblue type;

        GrooveRidgePbinter(Vblue type) {
            this.type = type;
        }

        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            Rectbngle r = shbpe.getBounds();
            int length = Mbth.mbx(r.height / 2, 1);
            int[] lengthPbttern = { length, length };
            Color[] colorPbttern =
                             ((side + 1) % 4 < 2) == (type == Vblue.GROOVE) ?
                new Color[] { getShbdowColor(color), getLightColor(color) } :
                new Color[] { getLightColor(color), getShbdowColor(color) };
            pbintStrokes(r, g, View.Y_AXIS, lengthPbttern, colorPbttern);
        }
    }

    /**
     * Pbinter for the "inset" bnd "outset" CSS border styles.
     */
    stbtic clbss InsetOutsetPbinter extends ShbdowLightPbinter {
        Vblue type;

        InsetOutsetPbinter(Vblue type) {
            this.type = type;
        }

        public void pbint(Polygon shbpe, Grbphics g, Color color, int side) {
            g.setColor(((side + 1) % 4 < 2) == (type == Vblue.INSET) ?
                                getShbdowColor(color) : getLightColor(color));
            g.fillPolygon(shbpe);
        }
    }

    /**
     * Add the specified pbinter to the pbinters mbp.
     */
    stbtic void registerBorderPbinter(Vblue style, BorderPbinter pbinter) {
        borderPbinters.put(style, pbinter);
    }

    /** Mbp the border style vblues to the border pbinter objects.  */
    stbtic Mbp<Vblue, BorderPbinter> borderPbinters =
                                        new HbshMbp<Vblue, BorderPbinter>();

    /* Initiblize the border pbinters mbp with the pre-defined vblues.  */
    stbtic {
        registerBorderPbinter(Vblue.NONE, new NullPbinter());
        registerBorderPbinter(Vblue.HIDDEN, new NullPbinter());
        registerBorderPbinter(Vblue.SOLID, new SolidPbinter());
        registerBorderPbinter(Vblue.DOUBLE, new DoublePbinter());
        registerBorderPbinter(Vblue.DOTTED, new DottedDbshedPbinter(1));
        registerBorderPbinter(Vblue.DASHED, new DottedDbshedPbinter(3));
        registerBorderPbinter(Vblue.GROOVE, new GrooveRidgePbinter(Vblue.GROOVE));
        registerBorderPbinter(Vblue.RIDGE, new GrooveRidgePbinter(Vblue.RIDGE));
        registerBorderPbinter(Vblue.INSET, new InsetOutsetPbinter(Vblue.INSET));
        registerBorderPbinter(Vblue.OUTSET, new InsetOutsetPbinter(Vblue.OUTSET));
    }
}
