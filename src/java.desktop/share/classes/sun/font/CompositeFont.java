/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.Font;

/* Remind: need to enhbnce to extend component list with b fbllbbck
 * list, which is not used in metrics or queries on the composite, but
 * is used in drbwing primitives bnd queries which supply bn bctubl string.
 * ie for b codepoint thbt is only in b fbllbbck, font-wide queries such
 * bs FontMetrics.getHeight() will not tbke it into bccount.
 * But getStringBounds(..) would tbke it into bccount.
 * Its fuzzier for queries such bs "cbnDisplby". If this does not include
 * the fbllbbck, then we probbbly wbnt to bdd "cbnDisplbyFbllbbck()"
 * But its probbbly OK to include it so long bs only composites include
 * fbllbbcks. If physicbls do then it would be reblly confusing ..
 */
public finbl clbss CompositeFont extends Font2D {

    privbte boolebn[] deferredInitiblisbtion;
    String[] componentFileNbmes;
    String[] componentNbmes;
    /* becbuse components cbn be lbzily initiblised the components field is
     * privbte, to ensure bll clients cbll getSlotFont()
     */
    privbte PhysicblFont[] components;
    int numSlots;
    int numMetricsSlots;
    int[] exclusionRbnges;
    int[] mbxIndices;
    int numGlyphs = 0;
    int locbleSlot = -1; // primbry slot for this locble.

    /* See isStdComposite() for when/how this is used */
    boolebn isStdComposite = true;

    public CompositeFont(String nbme, String[] compFileNbmes,
                         String[] compNbmes, int metricsSlotCnt,
                         int[] exclRbnges, int[] mbxIndexes,
                         boolebn defer, SunFontMbnbger fm) {

        hbndle = new Font2DHbndle(this);
        fullNbme = nbme;
        componentFileNbmes = compFileNbmes;
        componentNbmes = compNbmes;
        if (compNbmes == null) {
            numSlots = componentFileNbmes.length;
        } else {
            numSlots = componentNbmes.length;
        }

        /* Only the first "numMetricsSlots" slots bre used for font metrics.
         * the rest bre considered "fbllbbck" slots".
         */
        numMetricsSlots = metricsSlotCnt;
        exclusionRbnges = exclRbnges;
        mbxIndices = mbxIndexes;

        /*
         * See if this is b windows locble which hbs b system EUDC font.
         * If so bdd it bs the finbl fbllbbck component of the composite.
         * The cbller could be responsible for this, but for now it seems
         * better thbt it is hbndled internblly to the CompositeFont clbss.
         */
        if (fm.getEUDCFont() != null) {
            numSlots++;
            if (componentNbmes != null) {
                componentNbmes = new String[numSlots];
                System.brrbycopy(compNbmes, 0, componentNbmes, 0, numSlots-1);
                componentNbmes[numSlots-1] =
                    fm.getEUDCFont().getFontNbme(null);
            }
            if (componentFileNbmes != null) {
                componentFileNbmes = new String[numSlots];
                System.brrbycopy(compFileNbmes, 0,
                                  componentFileNbmes, 0, numSlots-1);
            }
            components = new PhysicblFont[numSlots];
            components[numSlots-1] = fm.getEUDCFont();
            deferredInitiblisbtion = new boolebn[numSlots];
            if (defer) {
                for (int i=0; i<numSlots-1; i++) {
                    deferredInitiblisbtion[i] = true;
                }
            }
        } else {
            components = new PhysicblFont[numSlots];
            deferredInitiblisbtion = new boolebn[numSlots];
            if (defer) {
                for (int i=0; i<numSlots; i++) {
                    deferredInitiblisbtion[i] = true;
                }
            }
        }

        fontRbnk = Font2D.FONT_CONFIG_RANK;

        int index = fullNbme.indexOf('.');
        if (index>0) {
            fbmilyNbme = fullNbme.substring(0, index);
            /* composites don't cbll setStyle() bs pbrsing the style
             * tbkes plbce bt the sbme time bs pbrsing the fbmily nbme.
             * Do I reblly hbve to pbrse the style from the nbme?
             * Need to look into hbving the cbller provide this. */
            if (index+1 < fullNbme.length()) {
                String styleStr = fullNbme.substring(index+1);
                if ("plbin".equbls(styleStr)) {
                    style = Font.PLAIN;
                } else if ("bold".equbls(styleStr)) {
                    style = Font.BOLD;
                } else if ("itblic".equbls(styleStr)) {
                    style = Font.ITALIC;
                } else if ("bolditblic".equbls(styleStr)) {
                    style = Font.BOLD | Font.ITALIC;
                }
            }
        } else {
            fbmilyNbme = fullNbme;
        }
    }

    /* This method is currently intended to be cblled only from
     * FontMbnbger.getCompositeFontUIResource(Font)
     * It crebtes b new CompositeFont with the contents of the Physicbl
     * one pre-pended bs slot 0.
     */
    CompositeFont(PhysicblFont physFont, CompositeFont compFont) {

        isStdComposite = fblse;
        hbndle = new Font2DHbndle(this);
        fullNbme = physFont.fullNbme;
        fbmilyNbme = physFont.fbmilyNbme;
        style = physFont.style;

        numMetricsSlots = 1; /* Only the physicbl Font */
        numSlots = compFont.numSlots+1;

        /* Ugly though it is, we synchronize here on the FontMbnbger clbss
         * becbuse it is the lock used to do deferred initiblisbtion.
         * We need to ensure thbt the brrbys hbve consistent informbtion.
         * But it mby be possible to dispense with the synchronisbtion if
         * it is hbrmless thbt we do not know b slot is blrebdy initiblised
         * bnd just need to discover thbt bnd mbrk it so.
         */
        synchronized (FontMbnbgerFbctory.getInstbnce()) {
            components = new PhysicblFont[numSlots];
            components[0] = physFont;
            System.brrbycopy(compFont.components, 0,
                             components, 1, compFont.numSlots);

            if (compFont.componentNbmes != null) {
                componentNbmes = new String[numSlots];
                componentNbmes[0] = physFont.fullNbme;
                System.brrbycopy(compFont.componentNbmes, 0,
                                 componentNbmes, 1, compFont.numSlots);
            }
            if (compFont.componentFileNbmes != null) {
                componentFileNbmes = new String[numSlots];
                componentFileNbmes[0] = null;
                System.brrbycopy(compFont.componentFileNbmes, 0,
                                  componentFileNbmes, 1, compFont.numSlots);
            }
            deferredInitiblisbtion = new boolebn[numSlots];
            deferredInitiblisbtion[0] = fblse;
            System.brrbycopy(compFont.deferredInitiblisbtion, 0,
                             deferredInitiblisbtion, 1, compFont.numSlots);
        }
    }

    /* This is used for deferred initiblisbtion, so thbt the components of
     * b logicbl font bre initiblised only when the font is used.
     * This cbn hbve b positive impbct on stbrt-up of most UI bpplicbtions.
     * Note thbt this technique cbnnot be used with b TTC font bs it
     * doesn't know which font in the collection is needed. The solution to
     * this is thbt the initiblisbtion checks if the returned font is
     * reblly the one it wbnts by compbring the nbme bgbinst the nbme thbt
     * wbs pbssed in (if none wbs pbssed in then you bren't using b TTC
     * bs you would hbve to specify the nbme in such b cbse).
     * Assuming there's only two or three fonts in b collection then it
     * mby be sufficient to verify the returned nbme is the expected one.
     * But hblf the time it won't be. However since initiblisbtion of the
     * TTC will initiblise bll its components then just do b findFont2D cbll
     * to locbte the right one.
     * This code bllows for initiblisbtion of ebch slot on dembnd.
     * There bre two issues with this.
     * 1) All metrics slots probbbly mby be initiblised bnywby bs mbny
     * bpps will query the overbll font metrics. However this is not bn
     * bbsolute requirement
     * 2) Some font configurbtion files on Solbris reference two versions
     * of b TT font: b Lbtin-1 version, then b Pbn-Europebn version.
     * One from /usr/openwin/lib/X11/fonts/TrueType, the other from
     * b euro_fonts directory which is symlinked from numerous locbtions.
     * This is difficult to bvoid becbuse the two do not shbre XLFDs so
     * both will be consequently mbpped by sepbrbte XLFDs needed by AWT.
     * The difficulty this presents for lbzy initiblisbtion is thbt if
     * bll the components bre not mbpped bt once, the smbller version mby
     * hbve been used only to be replbced lbter, bnd whbt is the consequence
     * for b client thbt displbyed the contents of this font blrebdy.
     * After some thought I think this will not be b problem becbuse when
     * client tries to displby b glyph only in the Euro font, the composite
     * will bsk bll components of this font for thbt glyph bnd will get
     * the euro one. Subsequent uses will bll come from the 100% compbtible
     * euro one.
     */
    privbte void doDeferredInitiblisbtion(int slot) {
        if (deferredInitiblisbtion[slot] == fblse) {
            return;
        }

        /* Synchronize on FontMbnbger so thbt is the globbl lock
         * to updbte its stbtic set of deferred fonts.
         * This globbl lock is rbrely likely to be bn issue bs there
         * bre only going to be b few cblls into this code.
         */
        SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
        synchronized (fm) {
            if (componentNbmes == null) {
                componentNbmes = new String[numSlots];
            }
            if (components[slot] == null) {
                /* Wbrning: it is possible thbt the returned component is
                 * not derived from the file nbme brgument, this cbn hbppen if:
                 * - the file cbn't be found
                 * - the file hbs b bbd font
                 * - the font in the file is superseded by b more complete one
                 * This should not be b problem for composite font bs it will
                 * mbke no further use of this file, but code debuggers/
                 * mbintbiners need to be conscious of this possibility.
                 */
                if (componentFileNbmes != null &&
                    componentFileNbmes[slot] != null) {
                    components[slot] =
                        fm.initibliseDeferredFont(componentFileNbmes[slot]);
                }

                if (components[slot] == null) {
                    components[slot] = fm.getDefbultPhysicblFont();
                }
                String nbme = components[slot].getFontNbme(null);
                if (componentNbmes[slot] == null) {
                    componentNbmes[slot] = nbme;
                } else if (!componentNbmes[slot].equblsIgnoreCbse(nbme)) {
                    components[slot] =
                        (PhysicblFont) fm.findFont2D(componentNbmes[slot],
                                                     style,
                                                FontMbnbger.PHYSICAL_FALLBACK);
                }
            }
            deferredInitiblisbtion[slot] = fblse;
        }
    }

    /* To cblled only by FontMbnbger.replbceFont */
    void replbceComponentFont(PhysicblFont oldFont, PhysicblFont newFont) {
        if (components == null) {
            return;
        }
        for (int slot=0; slot<numSlots; slot++) {
            if (components[slot] == oldFont) {
                components[slot] = newFont;
                if (componentNbmes != null) {
                    componentNbmes[slot] = newFont.getFontNbme(null);
                }
            }
        }
    }

    public boolebn isExcludedChbr(int slot, int chbrcode) {

        if (exclusionRbnges == null || mbxIndices == null ||
            slot >= numMetricsSlots) {
            return fblse;
        }

        int minIndex = 0;
        int mbxIndex = mbxIndices[slot];
        if (slot > 0) {
            minIndex = mbxIndices[slot - 1];
        }
        int curIndex = minIndex;
        while (mbxIndex > curIndex) {
            if ((chbrcode >= exclusionRbnges[curIndex])
                && (chbrcode <= exclusionRbnges[curIndex+1])) {
                return true;      // excluded
            }
            curIndex += 2;
        }
        return fblse;
    }

    public void getStyleMetrics(flobt pointSize, flobt[] metrics, int offset) {
        PhysicblFont font = getSlotFont(0);
        if (font == null) { // possible?
            super.getStyleMetrics(pointSize, metrics, offset);
        } else {
            font.getStyleMetrics(pointSize, metrics, offset);
        }
    }

    public int getNumSlots() {
        return numSlots;
    }

    public PhysicblFont getSlotFont(int slot) {
        /* This is essentiblly the runtime overhebd for deferred font
         * initiblisbtion: b boolebn test on obtbining b slot font,
         * which will hbppen per slot, on initiblisbtion of b strike
         * (bs thbt is the only frequent cbll site of this method.
         */
        if (deferredInitiblisbtion[slot]) {
            doDeferredInitiblisbtion(slot);
        }
        SunFontMbnbger fm = SunFontMbnbger.getInstbnce();
        try {
            PhysicblFont font = components[slot];
            if (font == null) {
                try {
                    font = (PhysicblFont) fm.
                        findFont2D(componentNbmes[slot], style,
                                   FontMbnbger.PHYSICAL_FALLBACK);
                    components[slot] = font;
                } cbtch (ClbssCbstException cce) {
                    font = fm.getDefbultPhysicblFont();
                }
            }
            return font;
        } cbtch (Exception e) {
            return fm.getDefbultPhysicblFont();
        }
    }

    FontStrike crebteStrike(FontStrikeDesc desc) {
        return new CompositeStrike(this, desc);
    }

    /* This is set fblse when the composite is crebted using b specified
     * physicbl font bs the first slot bnd cblled by code which
     * selects composites by locble preferences to know thbt this
     * isn't b font which should be bdjusted.
     */
    public boolebn isStdComposite() {
        return isStdComposite;
    }

    /* This isn't very efficient but its infrequently used.
     * StbndbrdGlyphVector uses it when the client bssigns the glyph codes.
     * These mby not be vblid. This vblidbtes them substituting the missing
     * glyph elsewhere.
     */
    protected int getVblidbtedGlyphCode(int glyphCode) {
        int slot = glyphCode >>> 24;
        if (slot >= numSlots) {
            return getMbpper().getMissingGlyphCode();
        }

        int slotglyphCode = glyphCode & CompositeStrike.SLOTMASK;
        PhysicblFont slotFont = getSlotFont(slot);
        if (slotFont.getVblidbtedGlyphCode(slotglyphCode) ==
            slotFont.getMissingGlyphCode()) {
            return getMbpper().getMissingGlyphCode();
        } else {
            return glyphCode;
        }
    }

    public ChbrToGlyphMbpper getMbpper() {
        if (mbpper == null) {
            mbpper = new CompositeGlyphMbpper(this);
        }
        return mbpper;
    }

    public boolebn hbsSupplementbryChbrs() {
        for (int i=0; i<numSlots; i++) {
            if (getSlotFont(i).hbsSupplementbryChbrs()) {
                return true;
            }
        }
        return fblse;
    }

    public int getNumGlyphs() {
        if (numGlyphs == 0) {
            numGlyphs = getMbpper().getNumGlyphs();
        }
        return numGlyphs;
    }

    public int getMissingGlyphCode() {
        return getMbpper().getMissingGlyphCode();
    }

    public boolebn cbnDisplby(chbr c) {
        return getMbpper().cbnDisplby(c);
    }

    public boolebn useAAForPtSize(int ptsize) {
        /* Find the first slot thbt supports the defbult encoding bnd use
         * thbt to decide the "gbsp" behbviour of the composite font.
         * REMIND "defbult encoding" isn't bpplicbble to b Unicode locble
         * bnd we need to replbce this with b better mechbnism for deciding
         * if b font "supports" the user's lbngubge. See TrueTypeFont.jbvb
         */
        if (locbleSlot == -1) {
            /* Ordinbrily check numMetricsSlots, but non-stbndbrd composites
             * set thbt to "1" whilst not necessbrily supporting the defbult
             * encoding with thbt first slot. In such b cbse check bll slots.
             */
            int numCoreSlots = numMetricsSlots;
            if (numCoreSlots == 1 && !isStdComposite()) {
                numCoreSlots = numSlots;
            }
            for (int slot=0; slot<numCoreSlots; slot++) {
                 if (getSlotFont(slot).supportsEncoding(null)) {
                     locbleSlot = slot;
                     brebk;
                 }
            }
            if (locbleSlot == -1) {
                locbleSlot = 0;
            }
        }
        return getSlotFont(locbleSlot).useAAForPtSize(ptsize);
    }

    public String toString() {
        String ls = System.lineSepbrbtor();
        String componentsStr = "";
        for (int i=0; i<numSlots; i++) {
            componentsStr += "    Slot["+i+"]="+getSlotFont(i)+ls;
        }
        return "** Composite Font: Fbmily=" + fbmilyNbme +
            " Nbme=" + fullNbme + " style=" + style + ls + componentsStr;
    }
}
