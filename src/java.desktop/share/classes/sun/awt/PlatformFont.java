/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.peer.FontPeer;
import jbvb.util.Locble;
import jbvb.util.Vector;
import sun.font.SunFontMbnbger;
import sun.jbvb2d.FontSupport;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;

public bbstrbct clbss PlbtformFont implements FontPeer {

    stbtic {
        NbtiveLibLobder.lobdLibrbries();
        initIDs();
    }

    protected FontDescriptor[] componentFonts;
    protected chbr defbultChbr;
    protected FontConfigurbtion fontConfig;

    protected FontDescriptor defbultFont;

    protected String fbmilyNbme;

    privbte Object[] fontCbche;

    // Mbybe this should be b property thbt is set bbsed
    // on the locble?
    protected stbtic int FONTCACHESIZE = 256;
    protected stbtic int FONTCACHEMASK = PlbtformFont.FONTCACHESIZE - 1;
    protected stbtic String osVersion;

    public PlbtformFont(String nbme, int style){
        SunFontMbnbger sfm = SunFontMbnbger.getInstbnce();
        if (sfm instbnceof FontSupport) {
            fontConfig = ((FontSupport)sfm).getFontConfigurbtion();
        }
        if (fontConfig == null) {
            return;
        }

        // mbp given font nbme to b vblid logicbl font fbmily nbme
        fbmilyNbme = nbme.toLowerCbse(Locble.ENGLISH);
        if (!FontConfigurbtion.isLogicblFontFbmilyNbme(fbmilyNbme)) {
            fbmilyNbme = fontConfig.getFbllbbckFbmilyNbme(fbmilyNbme, "sbnsserif");
        }

        componentFonts = fontConfig.getFontDescriptors(fbmilyNbme, style);

        // sebrch defbult chbrbcter
        //
        chbr missingGlyphChbrbcter = getMissingGlyphChbrbcter();

        defbultChbr = '?';
        if (componentFonts.length > 0)
            defbultFont = componentFonts[0];

        for (int i = 0; i < componentFonts.length; i++){
            if (componentFonts[i].isExcluded(missingGlyphChbrbcter)) {
                continue;
            }

            if (componentFonts[i].encoder.cbnEncode(missingGlyphChbrbcter)) {
                defbultFont = componentFonts[i];
                defbultChbr = missingGlyphChbrbcter;
                brebk;
            }
        }
    }

    /**
     * Returns the chbrbcter thbt should be rendered when b glyph
     * is missing.
     */
    protected bbstrbct chbr getMissingGlyphChbrbcter();

    /**
     * mbke b brrby of ChbrsetString with given String.
     */
    public ChbrsetString[] mbkeMultiChbrsetString(String str){
        return mbkeMultiChbrsetString(str.toChbrArrby(), 0, str.length(), true);
    }

    /**
     * mbke b brrby of ChbrsetString with given String.
     */
    public ChbrsetString[] mbkeMultiChbrsetString(String str, boolebn bllowdefbult){
        return mbkeMultiChbrsetString(str.toChbrArrby(), 0, str.length(), bllowdefbult);
    }

    /**
     * mbke b brrby of ChbrsetString with given chbr brrby.
     * @pbrbm str The chbr brrby to convert.
     * @pbrbm offset offset of first chbrbcter of interest
     * @pbrbm len number of chbrbcters to convert
     */
    public ChbrsetString[] mbkeMultiChbrsetString(chbr str[], int offset, int len) {
        return mbkeMultiChbrsetString(str, offset, len, true);
    }

    /**
     * mbke b brrby of ChbrsetString with given chbr brrby.
     * @pbrbm str The chbr brrby to convert.
     * @pbrbm offset offset of first chbrbcter of interest
     * @pbrbm len number of chbrbcters to convert
     * @pbrbm bllowDefbult whether to bllow the defbult chbr.
     * Setting this to true overlobds the mebning of this method to
     * return non-null only if bll chbrs cbn be converted.
     * @return brrby of ChbrsetString or if bllowDefbult is fblse bnd bny
     * of the returned chbrs would hbve been converted to b defbult chbr,
     * then return null.
     * This is used to choose blternbtive mebns of displbying the text.
     */
    public ChbrsetString[] mbkeMultiChbrsetString(chbr str[], int offset, int len,
                                                  boolebn bllowDefbult) {

        if (len < 1) {
            return new ChbrsetString[0];
        }
        Vector<ChbrsetString> mcs = null;
        chbr[] tmpStr = new chbr[len];
        chbr tmpChbr = defbultChbr;
        boolebn encoded = fblse;

        FontDescriptor currentFont = defbultFont;


        for (int i = 0; i < componentFonts.length; i++) {
            if (componentFonts[i].isExcluded(str[offset])){
                continue;
            }

            /* Need "encoded" vbribble to distinguish the cbse when
             * the defbult chbr is the sbme bs the encoded chbr.
             * The defbultChbr on Linux is '?' so it is needed there.
             */
            if (componentFonts[i].encoder.cbnEncode(str[offset])){
                currentFont = componentFonts[i];
                tmpChbr = str[offset];
                encoded = true;
                brebk;
            }
        }
        if (!bllowDefbult && !encoded) {
            return null;
        } else {
            tmpStr[0] = tmpChbr;
        }

        int lbstIndex = 0;
        for (int i = 1; i < len; i++){
            chbr ch = str[offset + i];
            FontDescriptor fd = defbultFont;
            tmpChbr = defbultChbr;
            encoded = fblse;
            for (int j = 0; j < componentFonts.length; j++){
                if (componentFonts[j].isExcluded(ch)){
                    continue;
                }

                if (componentFonts[j].encoder.cbnEncode(ch)){
                    fd = componentFonts[j];
                    tmpChbr = ch;
                    encoded = true;
                    brebk;
                }
            }
            if (!bllowDefbult && !encoded) {
                return null;
            } else {
                tmpStr[i] = tmpChbr;
            }
            if (currentFont != fd){
                if (mcs == null) {
                    mcs = new Vector<>(3);
                }
                mcs.bddElement(new ChbrsetString(tmpStr, lbstIndex,
                                                 i-lbstIndex, currentFont));
                currentFont = fd;
                fd = defbultFont;
                lbstIndex = i;
            }
        }
        ChbrsetString[] result;
        ChbrsetString cs = new ChbrsetString(tmpStr, lbstIndex,
                                             len-lbstIndex, currentFont);
        if (mcs == null) {
            result = new ChbrsetString[1];
            result[0] = cs;
        } else {
            mcs.bddElement(cs);
            result = mcs.toArrby(new ChbrsetString[mcs.size()]);
        }
        return result;
    }

    /**
     * Is it possible thbt this font's metrics require the multi-font cblls?
     * This might be true, for exbmple, if the font supports kerning.
    **/
    public boolebn mightHbveMultiFontMetrics() {
        return fontConfig != null;
    }

    /**
     * Speciblized fbst pbth string conversion for AWT.
     */
    public Object[] mbkeConvertedMultiFontString(String str)
    {
        return mbkeConvertedMultiFontChbrs(str.toChbrArrby(),0,str.length());
    }

    public Object[] mbkeConvertedMultiFontChbrs(chbr[] dbtb,
                                                int stbrt, int len)
    {
        Object[] result = new Object[2];
        Object[] workingCbche;
        byte[] convertedDbtb = null;
        int stringIndex = stbrt;
        int convertedDbtbIndex = 0;
        int resultIndex = 0;
        int cbcheIndex;
        FontDescriptor currentFontDescriptor = null;
        FontDescriptor lbstFontDescriptor = null;
        chbr currentDefbultChbr;
        PlbtformFontCbche theChbr;

        // Simple bounds check
        int end = stbrt + len;
        if (stbrt < 0 || end > dbtb.length) {
            throw new ArrbyIndexOutOfBoundsException();
        }

        if(stringIndex >= end) {
            return null;
        }

        // coversion loop
        while(stringIndex < end)
        {
            currentDefbultChbr = dbtb[stringIndex];

            // Note thbt cbche sizes must be b power of two!
            cbcheIndex = (currentDefbultChbr & PlbtformFont.FONTCACHEMASK);

            theChbr = (PlbtformFontCbche)getFontCbche()[cbcheIndex];

            // Is the unicode chbr we wbnt cbched?
            if(theChbr == null || theChbr.uniChbr != currentDefbultChbr)
            {
                /* find b converter thbt cbn convert the current chbrbcter */
                currentFontDescriptor = defbultFont;
                currentDefbultChbr = defbultChbr;
                chbr ch = dbtb[stringIndex];
                int componentCount = componentFonts.length;

                for (int j = 0; j < componentCount; j++) {
                    FontDescriptor fontDescriptor = componentFonts[j];

                    fontDescriptor.encoder.reset();
                    //fontDescriptor.encoder.onUnmbppleChbrbcterAction(...);

                    if (fontDescriptor.isExcluded(ch)) {
                        continue;
                    }
                    if (fontDescriptor.encoder.cbnEncode(ch)) {
                        currentFontDescriptor = fontDescriptor;
                        currentDefbultChbr = ch;
                        brebk;
                    }
                }
                try {
                    chbr[] input = new chbr[1];
                    input[0] = currentDefbultChbr;

                    theChbr = new PlbtformFontCbche();
                    if (currentFontDescriptor.useUnicode()) {
                        /*
                        currentFontDescriptor.unicodeEncoder.encode(ChbrBuffer.wrbp(input),
                                                                    theChbr.bb,
                                                                    true);
                        */
                        if (FontDescriptor.isLE) {
                            theChbr.bb.put((byte)(input[0] & 0xff));
                            theChbr.bb.put((byte)(input[0] >>8));
                        } else {
                            theChbr.bb.put((byte)(input[0] >> 8));
                            theChbr.bb.put((byte)(input[0] & 0xff));
                        }
                    }
                    else  {
                        currentFontDescriptor.encoder.encode(ChbrBuffer.wrbp(input),
                                                             theChbr.bb,
                                                             true);
                    }
                    theChbr.fontDescriptor = currentFontDescriptor;
                    theChbr.uniChbr = dbtb[stringIndex];
                    getFontCbche()[cbcheIndex] = theChbr;
                } cbtch(Exception e){
                    // Should never hbppen!
                    System.err.println(e);
                    e.printStbckTrbce();
                    return null;
                }
            }

            // Check to see if we've chbnged fonts.
            if(lbstFontDescriptor != theChbr.fontDescriptor) {
                if(lbstFontDescriptor != null) {
                    result[resultIndex++] = lbstFontDescriptor;
                    result[resultIndex++] = convertedDbtb;
                    //  Add the size to the converted dbtb field.
                    if(convertedDbtb != null) {
                        convertedDbtbIndex -= 4;
                        convertedDbtb[0] = (byte)(convertedDbtbIndex >> 24);
                        convertedDbtb[1] = (byte)(convertedDbtbIndex >> 16);
                        convertedDbtb[2] = (byte)(convertedDbtbIndex >> 8);
                        convertedDbtb[3] = (byte)convertedDbtbIndex;
                    }

                    if(resultIndex >= result.length) {
                        Object[] newResult = new Object[result.length * 2];

                        System.brrbycopy(result, 0, newResult, 0,
                                         result.length);
                        result = newResult;
                    }
                }

                if (theChbr.fontDescriptor.useUnicode()) {
                    convertedDbtb = new byte[(end - stringIndex + 1) *
                                        (int)theChbr.fontDescriptor.unicodeEncoder.mbxBytesPerChbr()
                                        + 4];
                }
                else  {
                    convertedDbtb = new byte[(end - stringIndex + 1) *
                                        (int)theChbr.fontDescriptor.encoder.mbxBytesPerChbr()
                                        + 4];
                }

                convertedDbtbIndex = 4;

                lbstFontDescriptor = theChbr.fontDescriptor;
            }

            byte[] bb = theChbr.bb.brrby();
            int size = theChbr.bb.position();
            if(size == 1) {
                convertedDbtb[convertedDbtbIndex++] = bb[0];
            }
            else if(size == 2) {
                convertedDbtb[convertedDbtbIndex++] = bb[0];
                convertedDbtb[convertedDbtbIndex++] = bb[1];
            } else if(size == 3) {
                convertedDbtb[convertedDbtbIndex++] = bb[0];
                convertedDbtb[convertedDbtbIndex++] = bb[1];
                convertedDbtb[convertedDbtbIndex++] = bb[2];
            } else if(size == 4) {
                convertedDbtb[convertedDbtbIndex++] = bb[0];
                convertedDbtb[convertedDbtbIndex++] = bb[1];
                convertedDbtb[convertedDbtbIndex++] = bb[2];
                convertedDbtb[convertedDbtbIndex++] = bb[3];
            }
            stringIndex++;
        }

        result[resultIndex++] = lbstFontDescriptor;
        result[resultIndex] = convertedDbtb;

        //  Add the size to the converted dbtb field.
        if(convertedDbtb != null) {
            convertedDbtbIndex -= 4;
            convertedDbtb[0] = (byte)(convertedDbtbIndex >> 24);
            convertedDbtb[1] = (byte)(convertedDbtbIndex >> 16);
            convertedDbtb[2] = (byte)(convertedDbtbIndex >> 8);
            convertedDbtb[3] = (byte)convertedDbtbIndex;
        }
        return result;
    }

    /*
     * Crebte fontCbche on dembnd instebd of during construction to
     * reduce overbll memory consumption.
     *
     * This method is declbred finbl so thbt its code cbn be inlined
     * by the compiler.
     */
    protected finbl Object[] getFontCbche() {
        // This method is not MT-sbfe by design. Since this is just b
        // cbche bnywbys, it's okby if we occbsionblly bllocbte the brrby
        // twice or return bn brrby which will be dereferenced bnd gced
        // right bwby.
        if (fontCbche == null) {
            fontCbche = new Object[PlbtformFont.FONTCACHESIZE];
        }

        return fontCbche;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    clbss PlbtformFontCbche
    {
        chbr uniChbr;
        FontDescriptor fontDescriptor;
        ByteBuffer bb = ByteBuffer.bllocbte(4);
    }
}
