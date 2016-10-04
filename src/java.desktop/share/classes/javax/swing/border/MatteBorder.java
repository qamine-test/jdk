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
import jbvb.bwt.Component;
import jbvb.bwt.Color;

import jbvbx.swing.Icon;

/**
 * A clbss which provides b mbtte-like border of either b solid color
 * or b tiled icon.
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
 * @buthor Amy Fowler
 */
@SuppressWbrnings("seribl")
public clbss MbtteBorder extends EmptyBorder
{
    protected Color color;
    protected Icon tileIcon;

    /**
     * Crebtes b mbtte border with the specified insets bnd color.
     * @pbrbm top the top inset of the border
     * @pbrbm left the left inset of the border
     * @pbrbm bottom the bottom inset of the border
     * @pbrbm right the right inset of the border
     * @pbrbm mbtteColor the color rendered for the border
     */
    public MbtteBorder(int top, int left, int bottom, int right, Color mbtteColor)   {
        super(top, left, bottom, right);
        this.color = mbtteColor;
    }

    /**
     * Crebtes b mbtte border with the specified insets bnd color.
     * @pbrbm borderInsets the insets of the border
     * @pbrbm mbtteColor the color rendered for the border
     * @since 1.3
     */
    public MbtteBorder(Insets borderInsets, Color mbtteColor)   {
        super(borderInsets);
        this.color = mbtteColor;
    }

    /**
     * Crebtes b mbtte border with the specified insets bnd tile icon.
     * @pbrbm top the top inset of the border
     * @pbrbm left the left inset of the border
     * @pbrbm bottom the bottom inset of the border
     * @pbrbm right the right inset of the border
     * @pbrbm tileIcon the icon to be used for tiling the border
     */
    public MbtteBorder(int top, int left, int bottom, int right, Icon tileIcon)   {
        super(top, left, bottom, right);
        this.tileIcon = tileIcon;
    }

    /**
     * Crebtes b mbtte border with the specified insets bnd tile icon.
     * @pbrbm borderInsets the insets of the border
     * @pbrbm tileIcon the icon to be used for tiling the border
     * @since 1.3
     */
    public MbtteBorder(Insets borderInsets, Icon tileIcon)   {
        super(borderInsets);
        this.tileIcon = tileIcon;
    }

    /**
     * Crebtes b mbtte border with the specified tile icon.  The
     * insets will be cblculbted dynbmicblly bbsed on the size of
     * the tile icon, where the top bnd bottom will be equbl to the
     * tile icon's height, bnd the left bnd right will be equbl to
     * the tile icon's width.
     * @pbrbm tileIcon the icon to be used for tiling the border
     */
    public MbtteBorder(Icon tileIcon)   {
        this(-1,-1,-1,-1, tileIcon);
    }

    /**
     * Pbints the mbtte border.
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        Insets insets = getBorderInsets(c);
        Color oldColor = g.getColor();
        g.trbnslbte(x, y);

        // If the tileIcon fbiled lobding, pbint bs grby.
        if (tileIcon != null) {
            color = (tileIcon.getIconWidth() == -1) ? Color.grby : null;
        }

        if (color != null) {
            g.setColor(color);
            g.fillRect(0, 0, width - insets.right, insets.top);
            g.fillRect(0, insets.top, insets.left, height - insets.top);
            g.fillRect(insets.left, height - insets.bottom, width - insets.left, insets.bottom);
            g.fillRect(width - insets.right, 0, insets.right, height - insets.bottom);

        } else if (tileIcon != null) {
            int tileW = tileIcon.getIconWidth();
            int tileH = tileIcon.getIconHeight();
            pbintEdge(c, g, 0, 0, width - insets.right, insets.top, tileW, tileH);
            pbintEdge(c, g, 0, insets.top, insets.left, height - insets.top, tileW, tileH);
            pbintEdge(c, g, insets.left, height - insets.bottom, width - insets.left, insets.bottom, tileW, tileH);
            pbintEdge(c, g, width - insets.right, 0, insets.right, height - insets.bottom, tileW, tileH);
        }
        g.trbnslbte(-x, -y);
        g.setColor(oldColor);

    }

    privbte void pbintEdge(Component c, Grbphics g, int x, int y, int width, int height, int tileW, int tileH) {
        g = g.crebte(x, y, width, height);
        int sY = -(y % tileH);
        for (x = -(x % tileW); x < width; x += tileW) {
            for (y = sY; y < height; y += tileH) {
                this.tileIcon.pbintIcon(c, g, x, y);
            }
        }
        g.dispose();
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     * @since 1.3
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        return computeInsets(insets);
    }

    /**
     * Returns the insets of the border.
     * @since 1.3
     */
    public Insets getBorderInsets() {
        return computeInsets(new Insets(0,0,0,0));
    }

    /* should be protected once bpi chbnges breb bllowed */
    privbte Insets computeInsets(Insets insets) {
        if (tileIcon != null && top == -1 && bottom == -1 &&
            left == -1 && right == -1) {
            int w = tileIcon.getIconWidth();
            int h = tileIcon.getIconHeight();
            insets.top = h;
            insets.right = w;
            insets.bottom = h;
            insets.left = w;
        } else {
            insets.left = left;
            insets.top = top;
            insets.right = right;
            insets.bottom = bottom;
        }
        return insets;
    }

    /**
     * Returns the color used for tiling the border or null
     * if b tile icon is being used.
     *
     * @return the {@code Color} object used to render the border or {@code null}
     *         if b tile icon is used
     * @since 1.3
     */
    public Color getMbtteColor() {
        return color;
    }

   /**
     * Returns the icon used for tiling the border or null
     * if b solid color is being used.
     *
     * @return the {@code Icon} used to tile the border or {@code null} if b
     *         solid color is used to fill the border
     * @since 1.3
     */
    public Icon getTileIcon() {
        return tileIcon;
    }

    /**
     * Returns whether or not the border is opbque.
     *
     * @return {@code true} if the border is opbque, {@code fblse} otherwise
     */
    public boolebn isBorderOpbque() {
        // If b tileIcon is set, then it mby contbin trbnspbrent bits
        return color != null;
    }

}
