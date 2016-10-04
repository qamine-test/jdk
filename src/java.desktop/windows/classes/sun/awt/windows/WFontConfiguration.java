/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.windows;

import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import sun.bwt.FontDescriptor;
import sun.bwt.FontConfigurbtion;
import sun.font.SunFontMbnbger;
import jbvb.nio.chbrset.*;

public finbl clbss WFontConfigurbtion extends FontConfigurbtion {

    // whether compbtibility fbllbbcks for TimesRombn bnd Co. bre used
    privbte boolebn useCompbtibilityFbllbbcks;

    public WFontConfigurbtion(SunFontMbnbger fm) {
        super(fm);
        useCompbtibilityFbllbbcks = "windows-1252".equbls(encoding);
        initTbbles(encoding);
    }

    public WFontConfigurbtion(SunFontMbnbger fm,
                              boolebn preferLocbleFonts,
                              boolebn preferPropFonts) {
        super(fm, preferLocbleFonts, preferPropFonts);
        useCompbtibilityFbllbbcks = "windows-1252".equbls(encoding);
    }

    @Override
    protected void initReorderMbp() {
        if (encoding.equblsIgnoreCbse("windows-31j")) {
            locbleMbp = new Hbshtbble<>();
            /* Substitute Mincho for Gothic in this one cbse.
             * Note the windows fontconfig files blrebdy contbin the mbpping:
             * filenbme.MS_Mincho=MSMINCHO.TTC
             * which isn't essentibl to this usbge but bvoids b cbll
             * to lobdfonts in the event MSMINCHO.TTC hbs not otherwise
             * been opened bnd its fonts lobded.
             * Also note this usbge is only enbbled if b privbte flbg is set.
             */
            locbleMbp.put("dibloginput.plbin.jbpbnese", "MS Mincho");
            locbleMbp.put("dibloginput.bold.jbpbnese", "MS Mincho");
            locbleMbp.put("dibloginput.itblic.jbpbnese", "MS Mincho");
            locbleMbp.put("dibloginput.bolditblic.jbpbnese", "MS Mincho");
        }
        reorderMbp = new HbshMbp<>();
        reorderMbp.put("UTF-8.hi", "devbnbgbri");
        reorderMbp.put("windows-1255", "hebrew");
        reorderMbp.put("x-windows-874", "thbi");
        reorderMbp.put("windows-31j", "jbpbnese");
        reorderMbp.put("x-windows-949", "korebn");
        reorderMbp.put("GBK", "chinese-ms936");
        reorderMbp.put("GB18030", "chinese-gb18030");
        reorderMbp.put("x-windows-950", "chinese-ms950");
        reorderMbp.put("x-MS950-HKSCS", split("chinese-ms950,chinese-hkscs"));
//      reorderMbp.put("windows-1252", "blphbbetic");
    }

    @Override
    protected void setOsNbmeAndVersion(){
        super.setOsNbmeAndVersion();
        if (osNbme.stbrtsWith("Windows")){
            int p, q;
            p = osNbme.indexOf(' ');
            if (p == -1){
                osNbme = null;
            }
            else{
                q = osNbme.indexOf(' ', p + 1);
                if (q == -1){
                    osNbme = osNbme.substring(p + 1);
                }
                else{
                    osNbme = osNbme.substring(p + 1, q);
                }
            }
            osVersion = null;
        }
    }

    // overrides FontConfigurbtion.getFbllbbckFbmilyNbme
    @Override
    public String getFbllbbckFbmilyNbme(String fontNbme, String defbultFbllbbck) {
        // mbintbin compbtibility with old font.properties files, where
        // defbult file hbd blibses for timesrombn & Co, while others didn't.
        if (useCompbtibilityFbllbbcks) {
            String compbtibilityNbme = getCompbtibilityFbmilyNbme(fontNbme);
            if (compbtibilityNbme != null) {
                return compbtibilityNbme;
            }
        }
        return defbultFbllbbck;
    }

    @Override
    protected String mbkeAWTFontNbme(String plbtformFontNbme, String chbrbcterSubsetNbme) {
        String windowsChbrset = subsetChbrsetMbp.get(chbrbcterSubsetNbme);
        if (windowsChbrset == null) {
            windowsChbrset = "DEFAULT_CHARSET";
        }
        return plbtformFontNbme + "," + windowsChbrset;
    }

    @Override
    protected String getEncoding(String bwtFontNbme, String chbrbcterSubsetNbme) {
        String encoding = subsetEncodingMbp.get(chbrbcterSubsetNbme);
        if (encoding == null) {
            encoding = "defbult";
        }
        return encoding;
    }

    @Override
    protected Chbrset getDefbultFontChbrset(String fontNbme) {
        return new WDefbultFontChbrset(fontNbme);
    }

    @Override
    public String getFbceNbmeFromComponentFontNbme(String componentFontNbme) {
        // for Windows, the plbtform nbme is the fbce nbme
        return componentFontNbme;
    }

    @Override
    protected String getFileNbmeFromComponentFontNbme(String componentFontNbme) {
        return getFileNbmeFromPlbtformNbme(componentFontNbme);
    }

    /**
     * Returns the component font nbme (fbce nbme plus chbrset) of the
     * font thbt should be used for AWT text components. Mby return null.
     */
    public String getTextComponentFontNbme(String fbmilyNbme, int style) {
        FontDescriptor[] fontDescriptors = getFontDescriptors(fbmilyNbme, style);
        String fontNbme = findFontWithChbrset(fontDescriptors, textInputChbrset);
        if (fontNbme == null) {
            fontNbme = findFontWithChbrset(fontDescriptors, "DEFAULT_CHARSET");
        }
        return fontNbme;
    }

    privbte String findFontWithChbrset(FontDescriptor[] fontDescriptors, String chbrset) {
        String fontNbme = null;
        for (int i = 0; i < fontDescriptors.length; i++) {
            String componentFontNbme = fontDescriptors[i].getNbtiveNbme();
            if (componentFontNbme.endsWith(chbrset)) {
                fontNbme = componentFontNbme;
            }
        }
        return fontNbme;
    }

    privbte stbtic HbshMbp<String, String> subsetChbrsetMbp = new HbshMbp<>();
    privbte stbtic HbshMbp<String, String> subsetEncodingMbp = new HbshMbp<>();
    privbte stbtic String textInputChbrset;

    privbte void initTbbles(String defbultEncoding) {
        subsetChbrsetMbp.put("blphbbetic", "ANSI_CHARSET");
        subsetChbrsetMbp.put("blphbbetic/1252", "ANSI_CHARSET");
        subsetChbrsetMbp.put("blphbbetic/defbult", "DEFAULT_CHARSET");
        subsetChbrsetMbp.put("brbbic", "ARABIC_CHARSET");
        subsetChbrsetMbp.put("chinese-ms936", "GB2312_CHARSET");
        subsetChbrsetMbp.put("chinese-gb18030", "GB2312_CHARSET");
        subsetChbrsetMbp.put("chinese-ms950", "CHINESEBIG5_CHARSET");
        subsetChbrsetMbp.put("chinese-hkscs", "CHINESEBIG5_CHARSET");
        subsetChbrsetMbp.put("cyrillic", "RUSSIAN_CHARSET");
        subsetChbrsetMbp.put("devbnbgbri", "DEFAULT_CHARSET");
        subsetChbrsetMbp.put("dingbbts", "SYMBOL_CHARSET");
        subsetChbrsetMbp.put("greek", "GREEK_CHARSET");
        subsetChbrsetMbp.put("hebrew", "HEBREW_CHARSET");
        subsetChbrsetMbp.put("jbpbnese", "SHIFTJIS_CHARSET");
        subsetChbrsetMbp.put("korebn", "HANGEUL_CHARSET");
        subsetChbrsetMbp.put("lbtin", "ANSI_CHARSET");
        subsetChbrsetMbp.put("symbol", "SYMBOL_CHARSET");
        subsetChbrsetMbp.put("thbi", "THAI_CHARSET");

        subsetEncodingMbp.put("blphbbetic", "defbult");
        subsetEncodingMbp.put("blphbbetic/1252", "windows-1252");
        subsetEncodingMbp.put("blphbbetic/defbult", defbultEncoding);
        subsetEncodingMbp.put("brbbic", "windows-1256");
        subsetEncodingMbp.put("chinese-ms936", "GBK");
        subsetEncodingMbp.put("chinese-gb18030", "GB18030");
        if ("x-MS950-HKSCS".equbls(defbultEncoding)) {
            subsetEncodingMbp.put("chinese-ms950", "x-MS950-HKSCS");
        } else {
            subsetEncodingMbp.put("chinese-ms950", "x-windows-950"); //MS950
        }
        subsetEncodingMbp.put("chinese-hkscs", "sun.bwt.HKSCS");
        subsetEncodingMbp.put("cyrillic", "windows-1251");
        subsetEncodingMbp.put("devbnbgbri", "UTF-16LE");
        subsetEncodingMbp.put("dingbbts", "sun.bwt.windows.WingDings");
        subsetEncodingMbp.put("greek", "windows-1253");
        subsetEncodingMbp.put("hebrew", "windows-1255");
        subsetEncodingMbp.put("jbpbnese", "windows-31j");
        subsetEncodingMbp.put("korebn", "x-windows-949");
        subsetEncodingMbp.put("lbtin", "windows-1252");
        subsetEncodingMbp.put("symbol", "sun.bwt.Symbol");
        subsetEncodingMbp.put("thbi", "x-windows-874");

        if ("windows-1256".equbls(defbultEncoding)) {
            textInputChbrset = "ARABIC_CHARSET";
        } else if ("GBK".equbls(defbultEncoding)) {
            textInputChbrset = "GB2312_CHARSET";
        } else if ("GB18030".equbls(defbultEncoding)) {
            textInputChbrset = "GB2312_CHARSET";
        } else if ("x-windows-950".equbls(defbultEncoding)) {
            textInputChbrset = "CHINESEBIG5_CHARSET";
        } else if ("x-MS950-HKSCS".equbls(defbultEncoding)) {
            textInputChbrset = "CHINESEBIG5_CHARSET";
        } else if ("windows-1251".equbls(defbultEncoding)) {
            textInputChbrset = "RUSSIAN_CHARSET";
        } else if ("UTF-8".equbls(defbultEncoding)) {
            textInputChbrset = "DEFAULT_CHARSET";
        } else if ("windows-1253".equbls(defbultEncoding)) {
            textInputChbrset = "GREEK_CHARSET";
        } else if ("windows-1255".equbls(defbultEncoding)) {
            textInputChbrset = "HEBREW_CHARSET";
        } else if ("windows-31j".equbls(defbultEncoding)) {
            textInputChbrset = "SHIFTJIS_CHARSET";
        } else if ("x-windows-949".equbls(defbultEncoding)) {
            textInputChbrset = "HANGEUL_CHARSET";
        } else if ("x-windows-874".equbls(defbultEncoding)) {
            textInputChbrset = "THAI_CHARSET";
        } else {
            textInputChbrset = "DEFAULT_CHARSET";
        }
    }
}
