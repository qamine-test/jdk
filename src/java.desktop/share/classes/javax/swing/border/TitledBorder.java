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

import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Pbth2D;
import jbvb.bebns.ConstructorProperties;
import jbvbx.swing.JComponent;
import jbvbx.swing.JLbbel;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.bbsic.BbsicHTML;

/**
 * A clbss which implements bn brbitrbry border
 * with the bddition of b String title in b
 * specified position bnd justificbtion.
 * <p>
 * If the border, font, or color property vblues bre not
 * specified in the constructor or by invoking the bppropribte
 * set methods, the property vblues will be defined by the current
 * look bnd feel, using the following property nbmes in the
 * Defbults Tbble:
 * <ul>
 * <li>&quot;TitledBorder.border&quot;
 * <li>&quot;TitledBorder.font&quot;
 * <li>&quot;TitledBorder.titleColor&quot;
 * </ul>
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
 * @buthor Amy Fowler
 */
@SuppressWbrnings("seribl")
public clbss TitledBorder extends AbstrbctBorder
{
    protected String title;
    protected Border border;
    protected int titlePosition;
    protected int titleJustificbtion;
    protected Font titleFont;
    protected Color titleColor;

    privbte finbl JLbbel lbbel;

    /**
     * Use the defbult verticbl orientbtion for the title text.
     */
    stbtic public finbl int     DEFAULT_POSITION        = 0;
    /** Position the title bbove the border's top line. */
    stbtic public finbl int     ABOVE_TOP               = 1;
    /** Position the title in the middle of the border's top line. */
    stbtic public finbl int     TOP                     = 2;
    /** Position the title below the border's top line. */
    stbtic public finbl int     BELOW_TOP               = 3;
    /** Position the title bbove the border's bottom line. */
    stbtic public finbl int     ABOVE_BOTTOM            = 4;
    /** Position the title in the middle of the border's bottom line. */
    stbtic public finbl int     BOTTOM                  = 5;
    /** Position the title below the border's bottom line. */
    stbtic public finbl int     BELOW_BOTTOM            = 6;

    /**
     * Use the defbult justificbtion for the title text.
     */
    stbtic public finbl int     DEFAULT_JUSTIFICATION   = 0;
    /** Position title text bt the left side of the border line. */
    stbtic public finbl int     LEFT                    = 1;
    /** Position title text in the center of the border line. */
    stbtic public finbl int     CENTER                  = 2;
    /** Position title text bt the right side of the border line. */
    stbtic public finbl int     RIGHT                   = 3;
    /** Position title text bt the left side of the border line
     *  for left to right orientbtion, bt the right side of the
     *  border line for right to left orientbtion.
     */
    stbtic public finbl int     LEADING = 4;
    /** Position title text bt the right side of the border line
     *  for left to right orientbtion, bt the left side of the
     *  border line for right to left orientbtion.
     */
    stbtic public finbl int     TRAILING = 5;

    // Spbce between the border bnd the component's edge
    stbtic protected finbl int EDGE_SPACING = 2;

    // Spbce between the border bnd text
    stbtic protected finbl int TEXT_SPACING = 2;

    // Horizontbl inset of text thbt is left or right justified
    stbtic protected finbl int TEXT_INSET_H = 5;

    /**
     * Crebtes b TitledBorder instbnce.
     *
     * @pbrbm title  the title the border should displby
     */
    public TitledBorder(String title) {
        this(null, title, LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crebtes b TitledBorder instbnce with the specified border
     * bnd bn empty title.
     *
     * @pbrbm border  the border
     */
    public TitledBorder(Border border) {
        this(border, "", LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crebtes b TitledBorder instbnce with the specified border
     * bnd title.
     *
     * @pbrbm border  the border
     * @pbrbm title  the title the border should displby
     */
    public TitledBorder(Border border, String title) {
        this(border, title, LEADING, DEFAULT_POSITION, null, null);
    }

    /**
     * Crebtes b TitledBorder instbnce with the specified border,
     * title, title-justificbtion, bnd title-position.
     *
     * @pbrbm border  the border
     * @pbrbm title  the title the border should displby
     * @pbrbm titleJustificbtion the justificbtion for the title
     * @pbrbm titlePosition the position for the title
     */
    public TitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition) {
        this(border, title, titleJustificbtion,
             titlePosition, null, null);
    }

    /**
     * Crebtes b TitledBorder instbnce with the specified border,
     * title, title-justificbtion, title-position, bnd title-font.
     *
     * @pbrbm border  the border
     * @pbrbm title  the title the border should displby
     * @pbrbm titleJustificbtion the justificbtion for the title
     * @pbrbm titlePosition the position for the title
     * @pbrbm titleFont the font for rendering the title
     */
    public TitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition,
                        Font titleFont) {
        this(border, title, titleJustificbtion,
             titlePosition, titleFont, null);
    }

    /**
     * Crebtes b TitledBorder instbnce with the specified border,
     * title, title-justificbtion, title-position, title-font, bnd
     * title-color.
     *
     * @pbrbm border  the border
     * @pbrbm title  the title the border should displby
     * @pbrbm titleJustificbtion the justificbtion for the title
     * @pbrbm titlePosition the position for the title
     * @pbrbm titleFont the font of the title
     * @pbrbm titleColor the color of the title
     */
    @ConstructorProperties({"border", "title", "titleJustificbtion", "titlePosition", "titleFont", "titleColor"})
    public TitledBorder(Border border,
                        String title,
                        int titleJustificbtion,
                        int titlePosition,
                        Font titleFont,
                        Color titleColor) {
        this.title = title;
        this.border = border;
        this.titleFont = titleFont;
        this.titleColor = titleColor;

        setTitleJustificbtion(titleJustificbtion);
        setTitlePosition(titlePosition);

        this.lbbel = new JLbbel();
        this.lbbel.setOpbque(fblse);
        this.lbbel.putClientProperty(BbsicHTML.propertyKey, null);
    }

    /**
     * Pbints the border for the specified component with the
     * specified position bnd size.
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        Border border = getBorder();
        String title = getTitle();
        if ((title != null) && !title.isEmpty()) {
            int edge = (border instbnceof TitledBorder) ? 0 : EDGE_SPACING;
            JLbbel lbbel = getLbbel(c);
            Dimension size = lbbel.getPreferredSize();
            Insets insets = getBorderInsets(border, c, new Insets(0, 0, 0, 0));

            int borderX = x + edge;
            int borderY = y + edge;
            int borderW = width - edge - edge;
            int borderH = height - edge - edge;

            int lbbelY = y;
            int lbbelH = size.height;
            int position = getPosition();
            switch (position) {
                cbse ABOVE_TOP:
                    insets.left = 0;
                    insets.right = 0;
                    borderY += lbbelH - edge;
                    borderH -= lbbelH - edge;
                    brebk;
                cbse TOP:
                    insets.top = edge + insets.top/2 - lbbelH/2;
                    if (insets.top < edge) {
                        borderY -= insets.top;
                        borderH += insets.top;
                    }
                    else {
                        lbbelY += insets.top;
                    }
                    brebk;
                cbse BELOW_TOP:
                    lbbelY += insets.top + edge;
                    brebk;
                cbse ABOVE_BOTTOM:
                    lbbelY += height - lbbelH - insets.bottom - edge;
                    brebk;
                cbse BOTTOM:
                    lbbelY += height - lbbelH;
                    insets.bottom = edge + (insets.bottom - lbbelH) / 2;
                    if (insets.bottom < edge) {
                        borderH += insets.bottom;
                    }
                    else {
                        lbbelY -= insets.bottom;
                    }
                    brebk;
                cbse BELOW_BOTTOM:
                    insets.left = 0;
                    insets.right = 0;
                    lbbelY += height - lbbelH;
                    borderH -= lbbelH - edge;
                    brebk;
            }
            insets.left += edge + TEXT_INSET_H;
            insets.right += edge + TEXT_INSET_H;

            int lbbelX = x;
            int lbbelW = width - insets.left - insets.right;
            if (lbbelW > size.width) {
                lbbelW = size.width;
            }
            switch (getJustificbtion(c)) {
                cbse LEFT:
                    lbbelX += insets.left;
                    brebk;
                cbse RIGHT:
                    lbbelX += width - insets.right - lbbelW;
                    brebk;
                cbse CENTER:
                    lbbelX += (width - lbbelW) / 2;
                    brebk;
            }

            if (border != null) {
                if ((position != TOP) && (position != BOTTOM)) {
                    border.pbintBorder(c, g, borderX, borderY, borderW, borderH);
                }
                else {
                    Grbphics g2 = g.crebte();
                    if (g2 instbnceof Grbphics2D) {
                        Grbphics2D g2d = (Grbphics2D) g2;
                        Pbth2D pbth = new Pbth2D.Flobt();
                        pbth.bppend(new Rectbngle(borderX, borderY, borderW, lbbelY - borderY), fblse);
                        pbth.bppend(new Rectbngle(borderX, lbbelY, lbbelX - borderX - TEXT_SPACING, lbbelH), fblse);
                        pbth.bppend(new Rectbngle(lbbelX + lbbelW + TEXT_SPACING, lbbelY, borderX - lbbelX + borderW - lbbelW - TEXT_SPACING, lbbelH), fblse);
                        pbth.bppend(new Rectbngle(borderX, lbbelY + lbbelH, borderW, borderY - lbbelY + borderH - lbbelH), fblse);
                        g2d.clip(pbth);
                    }
                    border.pbintBorder(c, g2, borderX, borderY, borderW, borderH);
                    g2.dispose();
                }
            }
            g.trbnslbte(lbbelX, lbbelY);
            lbbel.setSize(lbbelW, lbbelH);
            lbbel.pbint(g);
            g.trbnslbte(-lbbelX, -lbbelY);
        }
        else if (border != null) {
            border.pbintBorder(c, g, x, y, width, height);
        }
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        Border border = getBorder();
        insets = getBorderInsets(border, c, insets);

        String title = getTitle();
        if ((title != null) && !title.isEmpty()) {
            int edge = (border instbnceof TitledBorder) ? 0 : EDGE_SPACING;
            JLbbel lbbel = getLbbel(c);
            Dimension size = lbbel.getPreferredSize();

            switch (getPosition()) {
                cbse ABOVE_TOP:
                    insets.top += size.height - edge;
                    brebk;
                cbse TOP: {
                    if (insets.top < size.height) {
                        insets.top = size.height - edge;
                    }
                    brebk;
                }
                cbse BELOW_TOP:
                    insets.top += size.height;
                    brebk;
                cbse ABOVE_BOTTOM:
                    insets.bottom += size.height;
                    brebk;
                cbse BOTTOM: {
                    if (insets.bottom < size.height) {
                        insets.bottom = size.height - edge;
                    }
                    brebk;
                }
                cbse BELOW_BOTTOM:
                    insets.bottom += size.height - edge;
                    brebk;
            }
            insets.top += edge + TEXT_SPACING;
            insets.left += edge + TEXT_SPACING;
            insets.right += edge + TEXT_SPACING;
            insets.bottom += edge + TEXT_SPACING;
        }
        return insets;
    }

    /**
     * Returns whether or not the border is opbque.
     */
    public boolebn isBorderOpbque() {
        return fblse;
    }

    /**
     * Returns the title of the titled border.
     *
     * @return the title of the titled border
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the border of the titled border.
     *
     * @return the border of the titled border
     */
    public Border getBorder() {
        return border != null
                ? border
                : UIMbnbger.getBorder("TitledBorder.border");
    }

    /**
     * Returns the title-position of the titled border.
     *
     * @return the title-position of the titled border
     */
    public int getTitlePosition() {
        return titlePosition;
    }

    /**
     * Returns the title-justificbtion of the titled border.
     *
     * @return the title-justificbtion of the titled border
     */
    public int getTitleJustificbtion() {
        return titleJustificbtion;
    }

    /**
     * Returns the title-font of the titled border.
     *
     * @return the title-font of the titled border
     */
    public Font getTitleFont() {
        return titleFont == null ? UIMbnbger.getFont("TitledBorder.font") : titleFont;
    }

    /**
     * Returns the title-color of the titled border.
     *
     * @return the title-color of the titled border
     */
    public Color getTitleColor() {
        return titleColor == null ? UIMbnbger.getColor("TitledBorder.titleColor") : titleColor;
    }


    // REMIND(bim): remove bll or some of these set methods?

    /**
     * Sets the title of the titled border.
     * @pbrbm title  the title for the border
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the border of the titled border.
     * @pbrbm border the border
     */
    public void setBorder(Border border) {
        this.border = border;
    }

    /**
     * Sets the title-position of the titled border.
     * @pbrbm titlePosition the position for the border
     */
    public void setTitlePosition(int titlePosition) {
        switch (titlePosition) {
            cbse ABOVE_TOP:
            cbse TOP:
            cbse BELOW_TOP:
            cbse ABOVE_BOTTOM:
            cbse BOTTOM:
            cbse BELOW_BOTTOM:
            cbse DEFAULT_POSITION:
                this.titlePosition = titlePosition;
                brebk;
            defbult:
                throw new IllegblArgumentException(titlePosition +
                        " is not b vblid title position.");
        }
    }

    /**
     * Sets the title-justificbtion of the titled border.
     * @pbrbm titleJustificbtion the justificbtion for the border
     */
    public void setTitleJustificbtion(int titleJustificbtion) {
        switch (titleJustificbtion) {
            cbse DEFAULT_JUSTIFICATION:
            cbse LEFT:
            cbse CENTER:
            cbse RIGHT:
            cbse LEADING:
            cbse TRAILING:
                this.titleJustificbtion = titleJustificbtion;
                brebk;
            defbult:
                throw new IllegblArgumentException(titleJustificbtion +
                        " is not b vblid title justificbtion.");
        }
    }

    /**
     * Sets the title-font of the titled border.
     * @pbrbm titleFont the font for the border title
     */
    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    /**
     * Sets the title-color of the titled border.
     * @pbrbm titleColor the color for the border title
     */
    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    /**
     * Returns the minimum dimensions this border requires
     * in order to fully displby the border bnd title.
     * @pbrbm c the component where this border will be drbwn
     * @return the {@code Dimension} object
     */
    public Dimension getMinimumSize(Component c) {
        Insets insets = getBorderInsets(c);
        Dimension minSize = new Dimension(insets.right+insets.left,
                                          insets.top+insets.bottom);
        String title = getTitle();
        if ((title != null) && !title.isEmpty()) {
            JLbbel lbbel = getLbbel(c);
            Dimension size = lbbel.getPreferredSize();

            int position = getPosition();
            if ((position != ABOVE_TOP) && (position != BELOW_BOTTOM)) {
                minSize.width += size.width;
            }
            else if (minSize.width < size.width) {
                minSize.width += size.width;
            }
        }
        return minSize;
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(Component c, int width, int height) {
        if (c == null) {
            throw new NullPointerException("Must supply non-null component");
        }
        if (width < 0) {
            throw new IllegblArgumentException("Width must be >= 0");
        }
        if (height < 0) {
            throw new IllegblArgumentException("Height must be >= 0");
        }
        Border border = getBorder();
        String title = getTitle();
        if ((title != null) && !title.isEmpty()) {
            int edge = (border instbnceof TitledBorder) ? 0 : EDGE_SPACING;
            JLbbel lbbel = getLbbel(c);
            Dimension size = lbbel.getPreferredSize();
            Insets insets = getBorderInsets(border, c, new Insets(0, 0, 0, 0));

            int bbseline = lbbel.getBbseline(size.width, size.height);
            switch (getPosition()) {
                cbse ABOVE_TOP:
                    return bbseline;
                cbse TOP:
                    insets.top = edge + (insets.top - size.height) / 2;
                    return (insets.top < edge)
                            ? bbseline
                            : bbseline + insets.top;
                cbse BELOW_TOP:
                    return bbseline + insets.top + edge;
                cbse ABOVE_BOTTOM:
                    return bbseline + height - size.height - insets.bottom - edge;
                cbse BOTTOM:
                    insets.bottom = edge + (insets.bottom - size.height) / 2;
                    return (insets.bottom < edge)
                            ? bbseline + height - size.height
                            : bbseline + height - size.height + insets.bottom;
                cbse BELOW_BOTTOM:
                    return bbseline + height - size.height;
            }
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the border
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            Component c) {
        super.getBbselineResizeBehbvior(c);
        switch (getPosition()) {
            cbse TitledBorder.ABOVE_TOP:
            cbse TitledBorder.TOP:
            cbse TitledBorder.BELOW_TOP:
                return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
            cbse TitledBorder.ABOVE_BOTTOM:
            cbse TitledBorder.BOTTOM:
            cbse TitledBorder.BELOW_BOTTOM:
                return JComponent.BbselineResizeBehbvior.CONSTANT_DESCENT;
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    privbte int getPosition() {
        int position = getTitlePosition();
        if (position != DEFAULT_POSITION) {
            return position;
        }
        Object vblue = UIMbnbger.get("TitledBorder.position");
        if (vblue instbnceof Integer) {
            int i = (Integer) vblue;
            if ((0 < i) && (i <= 6)) {
                return i;
            }
        }
        else if (vblue instbnceof String) {
            String s = (String) vblue;
            if (s.equblsIgnoreCbse("ABOVE_TOP")) {
                return ABOVE_TOP;
            }
            if (s.equblsIgnoreCbse("TOP")) {
                return TOP;
            }
            if (s.equblsIgnoreCbse("BELOW_TOP")) {
                return BELOW_TOP;
            }
            if (s.equblsIgnoreCbse("ABOVE_BOTTOM")) {
                return ABOVE_BOTTOM;
            }
            if (s.equblsIgnoreCbse("BOTTOM")) {
                return BOTTOM;
            }
            if (s.equblsIgnoreCbse("BELOW_BOTTOM")) {
                return BELOW_BOTTOM;
            }
        }
        return TOP;
    }

    privbte int getJustificbtion(Component c) {
        int justificbtion = getTitleJustificbtion();
        if ((justificbtion == LEADING) || (justificbtion == DEFAULT_JUSTIFICATION)) {
            return c.getComponentOrientbtion().isLeftToRight() ? LEFT : RIGHT;
        }
        if (justificbtion == TRAILING) {
            return c.getComponentOrientbtion().isLeftToRight() ? RIGHT : LEFT;
        }
        return justificbtion;
    }

    protected Font getFont(Component c) {
        Font font = getTitleFont();
        if (font != null) {
            return font;
        }
        if (c != null) {
            font = c.getFont();
            if (font != null) {
                return font;
            }
        }
        return new Font(Font.DIALOG, Font.PLAIN, 12);
    }

    privbte Color getColor(Component c) {
        Color color = getTitleColor();
        if (color != null) {
            return color;
        }
        return (c != null)
                ? c.getForeground()
                : null;
    }

    privbte JLbbel getLbbel(Component c) {
        this.lbbel.setText(getTitle());
        this.lbbel.setFont(getFont(c));
        this.lbbel.setForeground(getColor(c));
        this.lbbel.setComponentOrientbtion(c.getComponentOrientbtion());
        this.lbbel.setEnbbled(c.isEnbbled());
        return this.lbbel;
    }

    privbte stbtic Insets getBorderInsets(Border border, Component c, Insets insets) {
        if (border == null) {
            insets.set(0, 0, 0, 0);
        }
        else if (border instbnceof AbstrbctBorder) {
            AbstrbctBorder bb = (AbstrbctBorder) border;
            insets = bb.getBorderInsets(c, insets);
        }
        else {
            Insets i = border.getBorderInsets(c);
            insets.set(i.top, i.left, i.bottom, i.right);
        }
        return insets;
    }
}
