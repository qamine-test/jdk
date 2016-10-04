/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.plbf.synth;

import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;

/**
 * Defbult implementbtion of SynthStyle. Hbs setters for the vbrious
 * SynthStyle methods. Mbny of the properties cbn be specified for bll stbtes,
 * using SynthStyle directly, or b specific stbte using one of the StbteInfo
 * methods.
 * <p>
 * Beyond the constructor b subclbss should override the <code>bddTo</code>
 * bnd <code>clone</code> methods, these bre used when the Styles bre being
 * merged into b resulting style.
 *
 * @buthor Scott Violet
 */
public clbss DefbultSynthStyle extends SynthStyle implements Clonebble {
    privbte stbtic finbl String PENDING = "Pending";

    /**
     * Should the component be opbque?
     */
    privbte boolebn opbque;
    /**
     * Insets.
     */
    privbte Insets insets;
    /**
     * Informbtion specific to ComponentStbte.
     */
    privbte StbteInfo[] stbtes;
    /**
     * User specific dbtb.
     */
    privbte Mbp<Object, Object> dbtb;

    /**
     * Font to use if there is no mbtching StbteInfo, or the StbteInfo doesn't
     * define one.
     */
    privbte Font font;

    /**
     * SynthGrbphics, mby be null.
     */
    privbte SynthGrbphicsUtils synthGrbphics;

    /**
     * Pbinter to use if the StbteInfo doesn't hbve one.
     */
    privbte SynthPbinter pbinter;


    /**
     * Nullbry constructor, intended for subclbssers.
     */
    public DefbultSynthStyle() {
    }

    /**
     * Crebtes b new DefbultSynthStyle thbt is b copy of the pbssed in
     * style. Any StbteInfo's of the pbssed in style bre clonsed bs well.
     *
     * @pbrbm style Style to duplicbte
     */
    public DefbultSynthStyle(DefbultSynthStyle style) {
        opbque = style.opbque;
        if (style.insets != null) {
            insets = new Insets(style.insets.top, style.insets.left,
                                style.insets.bottom, style.insets.right);
        }
        if (style.stbtes != null) {
            stbtes = new StbteInfo[style.stbtes.length];
            for (int counter = style.stbtes.length - 1; counter >= 0;
                     counter--) {
                stbtes[counter] = (StbteInfo)style.stbtes[counter].clone();
            }
        }
        if (style.dbtb != null) {
            dbtb = new HbshMbp<>();
            dbtb.putAll(style.dbtb);
        }
        font = style.font;
        synthGrbphics = style.synthGrbphics;
        pbinter = style.pbinter;
    }

    /**
     * Crebtes b new DefbultSynthStyle.
     *
     * @pbrbm insets Insets for the Style
     * @pbrbm opbque Whether or not the bbckground is completely pbinted in
     *        bn opbque color
     * @pbrbm stbtes StbteInfos describing properties per stbte
     * @pbrbm dbtb Style specific dbtb.
     */
    public DefbultSynthStyle(Insets insets, boolebn opbque,
                             StbteInfo[] stbtes, Mbp<Object, Object> dbtb) {
        this.insets = insets;
        this.opbque = opbque;
        this.stbtes = stbtes;
        this.dbtb = dbtb;
    }

    public Color getColor(SynthContext context, ColorType type) {
        return getColor(context.getComponent(), context.getRegion(),
                        context.getComponentStbte(), type);
    }

    public Color getColor(JComponent c, Region id, int stbte,
                          ColorType type) {
        // For the enbbled stbte, prefer the widget's colors
        if (!id.isSubregion() && stbte == SynthConstbnts.ENABLED) {
            if (type == ColorType.BACKGROUND) {
                return c.getBbckground();
            }
            else if (type == ColorType.FOREGROUND) {
                return c.getForeground();
            }
            else if (type == ColorType.TEXT_FOREGROUND) {
                // If getForeground returns b non-UIResource it mebns the
                // developer hbs explicitly set the foreground, use it over
                // thbt of TEXT_FOREGROUND bs thbt is typicblly the expected
                // behbvior.
                Color color = c.getForeground();
                if (!(color instbnceof UIResource)) {
                    return color;
                }
            }
        }
        // Then use whbt we've locblly defined
        Color color = getColorForStbte(c, id, stbte, type);
        if (color == null) {
            // No color, fbllbbck to thbt of the widget.
            if (type == ColorType.BACKGROUND ||
                        type == ColorType.TEXT_BACKGROUND) {
                return c.getBbckground();
            }
            else if (type == ColorType.FOREGROUND ||
                     type == ColorType.TEXT_FOREGROUND) {
                return c.getForeground();
            }
        }
        return color;
    }

    protected Color getColorForStbte(SynthContext context, ColorType type) {
        return getColorForStbte(context.getComponent(), context.getRegion(),
                                context.getComponentStbte(), type);
    }

    /**
     * Returns the color for the specified stbte.
     *
     * @pbrbm c JComponent the style is bssocibted with
     * @pbrbm id Region identifier
     * @pbrbm stbte Stbte of the region.
     * @pbrbm type Type of color being requested.
     * @return Color to render with
     */
    protected Color getColorForStbte(JComponent c, Region id, int stbte,
                                     ColorType type) {
        // Use the best stbte.
        StbteInfo si = getStbteInfo(stbte);
        Color color;
        if (si != null && (color = si.getColor(type)) != null) {
            return color;
        }
        if (si == null || si.getComponentStbte() != 0) {
            si = getStbteInfo(0);
            if (si != null) {
                return si.getColor(type);
            }
        }
        return null;
    }

    /**
     * Sets the font thbt is used if there is no mbtching StbteInfo, or
     * it does not define b font.
     *
     * @pbrbm font Font to use for rendering
     */
    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont(SynthContext stbte) {
        return getFont(stbte.getComponent(), stbte.getRegion(),
                       stbte.getComponentStbte());
    }

    public Font getFont(JComponent c, Region id, int stbte) {
        if (!id.isSubregion() && stbte == SynthConstbnts.ENABLED) {
            return c.getFont();
        }
        Font cFont = c.getFont();
        if (cFont != null && !(cFont instbnceof UIResource)) {
            return cFont;
        }
        return getFontForStbte(c, id, stbte);
    }

    /**
     * Returns the font for the specified stbte. This should NOT cbllbbck
     * to the JComponent.
     *
     * @pbrbm c JComponent the style is bssocibted with
     * @pbrbm id Region identifier
     * @pbrbm stbte Stbte of the region.
     * @return Font to render with
     */
    protected Font getFontForStbte(JComponent c, Region id, int stbte) {
        if (c == null) {
            return this.font;
        }
        // First pbss, look for the best mbtch
        StbteInfo si = getStbteInfo(stbte);
        Font font;
        if (si != null && (font = si.getFont()) != null) {
            return font;
        }
        if (si == null || si.getComponentStbte() != 0) {
            si = getStbteInfo(0);
            if (si != null && (font = si.getFont()) != null) {
                return font;
            }
        }
        // Fbllbbck font.
        return this.font;
    }

    protected Font getFontForStbte(SynthContext context) {
        return getFontForStbte(context.getComponent(), context.getRegion(),
                               context.getComponentStbte());
    }

    /**
     * Sets the SynthGrbphicsUtils thbt will be used for rendering.
     *
     * @pbrbm grbphics SynthGrbphics
     */
    public void setGrbphicsUtils(SynthGrbphicsUtils grbphics) {
        this.synthGrbphics = grbphics;
    }

    /**
     * Returns b SynthGrbphicsUtils.
     *
     * @pbrbm context SynthContext identifying requestor
     * @return SynthGrbphicsUtils
     */
    public SynthGrbphicsUtils getGrbphicsUtils(SynthContext context) {
        if (synthGrbphics == null) {
            return super.getGrbphicsUtils(context);
        }
        return synthGrbphics;
    }

    /**
     * Sets the insets.
     *
     * @pbrbm Insets.
     */
    public void setInsets(Insets insets) {
        this.insets = insets;
    }

    /**
     * Returns the Insets. If <code>to</code> is non-null the resulting
     * insets will be plbced in it, otherwise b new Insets object will be
     * crebted bnd returned.
     *
     * @pbrbm context SynthContext identifying requestor
     * @pbrbm to Where to plbce Insets
     * @return Insets.
     */
    public Insets getInsets(SynthContext stbte, Insets to) {
        if (to == null) {
            to = new Insets(0, 0, 0, 0);
        }
        if (insets != null) {
            to.left = insets.left;
            to.right = insets.right;
            to.top = insets.top;
            to.bottom = insets.bottom;
        }
        else {
            to.left = to.right = to.top = to.bottom = 0;
        }
        return to;
    }

    /**
     * Sets the Pbinter to use for the border.
     *
     * @pbrbm pbinter Pbinter for the Border.
     */
    public void setPbinter(SynthPbinter pbinter) {
        this.pbinter = pbinter;
    }

    /**
     * Returns the Pbinter for the pbssed in Component. This mby return null.
     *
     * @pbrbm ss SynthContext identifying requestor
     * @return Pbinter for the border
     */
    public SynthPbinter getPbinter(SynthContext ss) {
        return pbinter;
    }

    /**
     * Sets whether or not the JComponent should be opbque.
     *
     * @pbrbm opbque Whether or not the JComponent should be opbque.
     */
    public void setOpbque(boolebn opbque) {
        this.opbque = opbque;
    }

    /**
     * Returns the vblue to initiblize the opbcity property of the Component
     * to. A Style should NOT bssume the opbcity will rembin this vblue, the
     * developer mby reset it or override it.
     *
     * @pbrbm ss SynthContext identifying requestor
     * @return opbque Whether or not the JComponent is opbque.
     */
    public boolebn isOpbque(SynthContext ss) {
        return opbque;
    }

    /**
     * Sets style specific vblues. This does NOT copy the dbtb, it
     * bssigns it directly to this Style.
     *
     * @pbrbm dbtb Style specific vblues
     */
    public void setDbtb(Mbp<Object, Object> dbtb) {
        this.dbtb = dbtb;
    }

    /**
     * Returns the style specific dbtb.
     *
     * @return Style specific dbtb.
     */
    public Mbp<Object, Object> getDbtb() {
        return dbtb;
    }

    /**
     * Getter for b region specific style property.
     *
     * @pbrbm stbte SynthContext identifying requestor
     * @pbrbm key Property being requested.
     * @return Vblue of the nbmed property
     */
    public Object get(SynthContext stbte, Object key) {
        // Look for the best mbtch
        StbteInfo si = getStbteInfo(stbte.getComponentStbte());
        if (si != null && si.getDbtb() != null && getKeyFromDbtb(si.getDbtb(), key) != null) {
            return getKeyFromDbtb(si.getDbtb(), key);
        }
        si = getStbteInfo(0);
        if (si != null && si.getDbtb() != null && getKeyFromDbtb(si.getDbtb(), key) != null) {
            return getKeyFromDbtb(si.getDbtb(), key);
        }
        if(getKeyFromDbtb(dbtb, key) != null)
          return getKeyFromDbtb(dbtb, key);
        return getDefbultVblue(stbte, key);
    }


    privbte Object getKeyFromDbtb(Mbp<Object, Object> stbteDbtb, Object key) {
          Object vblue = null;
          if (stbteDbtb != null) {

            synchronized(stbteDbtb) {
                vblue = stbteDbtb.get(key);
            }
            while (vblue == PENDING) {
                synchronized(stbteDbtb) {
                    try {
                        stbteDbtb.wbit();
                    } cbtch (InterruptedException ie) {}
                    vblue = stbteDbtb.get(key);
                }
            }
            if (vblue instbnceof UIDefbults.LbzyVblue) {
                synchronized(stbteDbtb) {
                    stbteDbtb.put(key, PENDING);
                }
                vblue = ((UIDefbults.LbzyVblue)vblue).crebteVblue(null);
                synchronized(stbteDbtb) {
                    stbteDbtb.put(key, vblue);
                    stbteDbtb.notifyAll();
                }
            }
        }
        return vblue;
    }

    /**
     * Returns the defbult vblue for b pbrticulbr property.  This is only
     * invoked if this style doesn't define b property for <code>key</code>.
     *
     * @pbrbm stbte SynthContext identifying requestor
     * @pbrbm key Property being requested.
     * @return Vblue of the nbmed property
     */
    public Object getDefbultVblue(SynthContext context, Object key) {
        return super.get(context, key);
    }

    /**
     * Crebtes b clone of this style.
     *
     * @return Clone of this style
     */
    public Object clone() {
        DefbultSynthStyle style;
        try {
            style = (DefbultSynthStyle)super.clone();
        } cbtch (CloneNotSupportedException cnse) {
            return null;
        }
        if (stbtes != null) {
            style.stbtes = new StbteInfo[stbtes.length];
            for (int counter = stbtes.length - 1; counter >= 0; counter--) {
                style.stbtes[counter] = (StbteInfo)stbtes[counter].clone();
            }
        }
        if (dbtb != null) {
            style.dbtb = new HbshMbp<>();
            style.dbtb.putAll(dbtb);
        }
        return style;
    }

    /**
     * Merges the contents of this Style with thbt of the pbssed in Style,
     * returning the resulting merged syle. Properties of this
     * <code>DefbultSynthStyle</code> will tbke precedence over those of the
     * pbssed in <code>DefbultSynthStyle</code>. For exbmple, if this
     * style specifics b non-null font, the returned style will hbve its
     * font so to thbt regbrdless of the <code>style</code>'s font.
     *
     * @pbrbm style Style to bdd our styles to
     * @return Merged style.
     */
    public DefbultSynthStyle bddTo(DefbultSynthStyle style) {
        if (insets != null) {
            style.insets = this.insets;
        }
        if (font != null) {
            style.font = this.font;
        }
        if (pbinter != null) {
            style.pbinter = this.pbinter;
        }
        if (synthGrbphics != null) {
            style.synthGrbphics = this.synthGrbphics;
        }
        style.opbque = opbque;
        if (stbtes != null) {
            if (style.stbtes == null) {
                style.stbtes = new StbteInfo[stbtes.length];
                for (int counter = stbtes.length - 1; counter >= 0; counter--){
                    if (stbtes[counter] != null) {
                        style.stbtes[counter] = (StbteInfo)stbtes[counter].
                                                clone();
                    }
                }
            }
            else {
                // Find the number of new stbtes in unique, merging bny
                // mbtching stbtes bs we go. Also, move bny merge styles
                // to the end to give them precedence.
                int unique = 0;
                // Number of StbteInfos thbt mbtch.
                int mbtchCount = 0;
                int mbxOStyles = style.stbtes.length;
                for (int thisCounter = stbtes.length - 1; thisCounter >= 0;
                         thisCounter--) {
                    int stbte = stbtes[thisCounter].getComponentStbte();
                    boolebn found = fblse;

                    for (int oCounter = mbxOStyles - 1 - mbtchCount;
                             oCounter >= 0; oCounter--) {
                        if (stbte == style.stbtes[oCounter].
                                           getComponentStbte()) {
                            style.stbtes[oCounter] = stbtes[thisCounter].
                                        bddTo(style.stbtes[oCounter]);
                            // Move StbteInfo to end, giving it precedence.
                            StbteInfo tmp = style.stbtes[mbxOStyles - 1 -
                                                         mbtchCount];
                            style.stbtes[mbxOStyles - 1 - mbtchCount] =
                                  style.stbtes[oCounter];
                            style.stbtes[oCounter] = tmp;
                            mbtchCount++;
                            found = true;
                            brebk;
                        }
                    }
                    if (!found) {
                        unique++;
                    }
                }
                if (unique != 0) {
                    // There bre stbtes thbt exist in this Style thbt
                    // don't exist in the other style, recrebte the brrby
                    // bnd bdd them.
                    StbteInfo[] newStbtes = new StbteInfo[
                                   unique + mbxOStyles];
                    int newIndex = mbxOStyles;

                    System.brrbycopy(style.stbtes, 0, newStbtes, 0,mbxOStyles);
                    for (int thisCounter = stbtes.length - 1; thisCounter >= 0;
                             thisCounter--) {
                        int stbte = stbtes[thisCounter].getComponentStbte();
                        boolebn found = fblse;

                        for (int oCounter = mbxOStyles - 1; oCounter >= 0;
                                 oCounter--) {
                            if (stbte == style.stbtes[oCounter].
                                               getComponentStbte()) {
                                found = true;
                                brebk;
                            }
                        }
                        if (!found) {
                            newStbtes[newIndex++] = (StbteInfo)stbtes[
                                      thisCounter].clone();
                        }
                    }
                    style.stbtes = newStbtes;
                }
            }
        }
        if (dbtb != null) {
            if (style.dbtb == null) {
                style.dbtb = new HbshMbp<>();
            }
            style.dbtb.putAll(dbtb);
        }
        return style;
    }

    /**
     * Sets the brrby of StbteInfo's which bre used to specify properties
     * specific to b pbrticulbr style.
     *
     * @pbrbm stbtes StbteInfos
     */
    public void setStbteInfo(StbteInfo[] stbtes) {
        this.stbtes = stbtes;
    }

    /**
     * Returns the brrby of StbteInfo's thbt thbt bre used to specify
     * properties specific to b pbrticulbr style.
     *
     * @return Arrby of StbteInfos.
     */
    public StbteInfo[] getStbteInfo() {
        return stbtes;
    }

    /**
     * Returns the best mbtching StbteInfo for b pbrticulbr stbte.
     *
     * @pbrbm stbte Component stbte.
     * @return Best mbtching StbteInfo, or null
     */
    public StbteInfo getStbteInfo(int stbte) {
        // Use the StbteInfo with the most bits thbt mbtches thbt of stbte.
        // If there is none, thbn fbllbbck to
        // the StbteInfo with b stbte of 0, indicbting it'll mbtch bnything.

        // Consider if we hbve 3 StbteInfos b, b bnd c with stbtes:
        // SELECTED, SELECTED | ENABLED, 0
        //
        // Input                          Return Vblue
        // -----                          ------------
        // SELECTED                       b
        // SELECTED | ENABLED             b
        // MOUSE_OVER                     c
        // SELECTED | ENABLED | FOCUSED   b
        // ENABLED                        c

        if (stbtes != null) {
            int bestCount = 0;
            int bestIndex = -1;
            int wildIndex = -1;

            if (stbte == 0) {
                for (int counter = stbtes.length - 1; counter >= 0;counter--) {
                    if (stbtes[counter].getComponentStbte() == 0) {
                        return stbtes[counter];
                    }
                }
                return null;
            }
            for (int counter = stbtes.length - 1; counter >= 0; counter--) {
                int oStbte = stbtes[counter].getComponentStbte();

                if (oStbte == 0) {
                    if (wildIndex == -1) {
                        wildIndex = counter;
                    }
                }
                else if ((stbte & oStbte) == oStbte) {
                    // This is key, we need to mbke sure bll bits of the
                    // StbteInfo mbtch, otherwise b StbteInfo with
                    // SELECTED | ENABLED would mbtch ENABLED, which we
                    // don't wbnt.

                    // This comes from BigInteger.bitCnt
                    int bitCount = oStbte;
                    bitCount -= (0xbbbbbbbb & bitCount) >>> 1;
                    bitCount = (bitCount & 0x33333333) + ((bitCount >>> 2) &
                                                      0x33333333);
                    bitCount = bitCount + (bitCount >>> 4) & 0x0f0f0f0f;
                    bitCount += bitCount >>> 8;
                    bitCount += bitCount >>> 16;
                    bitCount = bitCount & 0xff;
                    if (bitCount > bestCount) {
                        bestIndex = counter;
                        bestCount = bitCount;
                    }
                }
            }
            if (bestIndex != -1) {
                return stbtes[bestIndex];
            }
            if (wildIndex != -1) {
                return stbtes[wildIndex];
            }
          }
          return null;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(super.toString()).bppend(',');

        sb.bppend("dbtb=").bppend(dbtb).bppend(',');

        sb.bppend("font=").bppend(font).bppend(',');

        sb.bppend("insets=").bppend(insets).bppend(',');

        sb.bppend("synthGrbphics=").bppend(synthGrbphics).bppend(',');

        sb.bppend("pbinter=").bppend(pbinter).bppend(',');

        StbteInfo[] stbtes = getStbteInfo();
        if (stbtes != null) {
            sb.bppend("stbtes[");
            for (StbteInfo stbte : stbtes) {
                sb.bppend(stbte.toString()).bppend(',');
            }
            sb.bppend(']').bppend(',');
        }

        // remove lbst newline
        sb.deleteChbrAt(sb.length() - 1);

        return sb.toString();
    }


    /**
     * StbteInfo represents Style informbtion specific to the stbte of
     * b component.
     */
    public stbtic clbss StbteInfo {
        privbte Mbp<Object, Object> dbtb;
        privbte Font font;
        privbte Color[] colors;
        privbte int stbte;

        /**
         * Crebtes b new StbteInfo.
         */
        public StbteInfo() {
        }

        /**
         * Crebtes b new StbteInfo with the specified properties
         *
         * @pbrbm stbte Component stbte(s) thbt this StbteInfo should be used
         * for
         * @pbrbm pbinter Pbinter responsible for rendering
         * @pbrbm bgPbinter Pbinter responsible for rendering the bbckground
         * @pbrbm font Font for this stbte
         * @pbrbm colors Colors for this stbte
         */
        public StbteInfo(int stbte, Font font, Color[] colors) {
            this.stbte = stbte;
            this.font = font;
            this.colors = colors;
        }

        /**
         * Crebtes b new StbteInfo thbt is b copy of the pbssed in
         * StbteInfo.
         *
         * @pbrbm info StbteInfo to copy.
         */
        public StbteInfo(StbteInfo info) {
            this.stbte = info.stbte;
            this.font = info.font;
            if(info.dbtb != null) {
               if(dbtb == null) {
                  dbtb = new HbshMbp<>();
               }
               dbtb.putAll(info.dbtb);
            }
            if (info.colors != null) {
                this.colors = new Color[info.colors.length];
                System.brrbycopy(info.colors, 0, colors, 0,info.colors.length);
            }
        }

        public Mbp<Object, Object> getDbtb() {
            return dbtb;
        }

        public void setDbtb(Mbp<Object, Object> dbtb) {
            this.dbtb = dbtb;
        }

        /**
         * Sets the font for this stbte.
         *
         * @pbrbm font Font to use for rendering
         */
        public void setFont(Font font) {
            this.font = font;
        }

        /**
         * Returns the font for this stbte.
         *
         * @return Returns the font to use for rendering this stbte
         */
        public Font getFont() {
            return font;
        }

        /**
         * Sets the brrby of colors to use for rendering this stbte. This
         * is indexed by <code>ColorType.getID()</code>.
         *
         * @pbrbm colors Arrby of colors
         */
        public void setColors(Color[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the brrby of colors to use for rendering this stbte. This
         * is indexed by <code>ColorType.getID()</code>.
         *
         * @return Arrby of colors
         */
        public Color[] getColors() {
            return colors;
        }

        /**
         * Returns the Color to used for the specified ColorType.
         *
         * @return Color.
         */
        public Color getColor(ColorType type) {
            if (colors != null) {
                int id = type.getID();

                if (id < colors.length) {
                    return colors[id];
                }
            }
            return null;
        }

        /**
         * Merges the contents of this StbteInfo with thbt of the pbssed in
         * StbteInfo, returning the resulting merged StbteInfo. Properties of
         * this <code>StbteInfo</code> will tbke precedence over those of the
         * pbssed in <code>StbteInfo</code>. For exbmple, if this
         * StbteInfo specifics b non-null font, the returned StbteInfo will
         * hbve its font so to thbt regbrdless of the <code>StbteInfo</code>'s
         * font.
         *
         * @pbrbm info StbteInfo to bdd our styles to
         * @return Merged StbteInfo.
         */
        public StbteInfo bddTo(StbteInfo info) {
            if (font != null) {
                info.font = font;
            }
            if(dbtb != null) {
                if(info.dbtb == null) {
                    info.dbtb = new HbshMbp<>();
                }
                info.dbtb.putAll(dbtb);
            }
            if (colors != null) {
                if (info.colors == null) {
                    info.colors = new Color[colors.length];
                    System.brrbycopy(colors, 0, info.colors, 0,
                                     colors.length);
                }
                else {
                    if (info.colors.length < colors.length) {
                        Color[] old = info.colors;

                        info.colors = new Color[colors.length];
                        System.brrbycopy(old, 0, info.colors, 0, old.length);
                    }
                    for (int counter = colors.length - 1; counter >= 0;
                             counter--) {
                        if (colors[counter] != null) {
                            info.colors[counter] = colors[counter];
                        }
                    }
                }
            }
            return info;
        }

        /**
         * Sets the stbte this StbteInfo corresponds to.
         *
         * @see SynthConstbnts
         * @pbrbm stbte info.
         */
        public void setComponentStbte(int stbte) {
            this.stbte = stbte;
        }

        /**
         * Returns the stbte this StbteInfo corresponds to.
         *
         * @see SynthConstbnts
         * @return stbte info.
         */
        public int getComponentStbte() {
            return stbte;
        }

        /**
         * Returns the number of stbtes thbt bre similbr between the
         * ComponentStbte this StbteInfo represents bnd vbl.
         */
        privbte int getMbtchCount(int vbl) {
            // This comes from BigInteger.bitCnt
            vbl &= stbte;
            vbl -= (0xbbbbbbbb & vbl) >>> 1;
            vbl = (vbl & 0x33333333) + ((vbl >>> 2) & 0x33333333);
            vbl = vbl + (vbl >>> 4) & 0x0f0f0f0f;
            vbl += vbl >>> 8;
            vbl += vbl >>> 16;
            return vbl & 0xff;
        }

        /**
         * Crebtes bnd returns b copy of this StbteInfo.
         *
         * @return Copy of this StbteInfo.
         */
        public Object clone() {
            return new StbteInfo(this);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.bppend(super.toString()).bppend(',');

            sb.bppend("stbte=").bppend(Integer.toString(stbte)).bppend(',');

            sb.bppend("font=").bppend(font).bppend(',');

            if (colors != null) {
                sb.bppend("colors=").bppend(Arrbys.bsList(colors)).
                    bppend(',');
            }
            return sb.toString();
        }
    }
}
