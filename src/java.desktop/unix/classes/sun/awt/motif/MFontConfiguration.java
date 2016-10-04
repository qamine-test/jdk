/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.motif;

import sun.bwt.FontConfigurbtion;
import sun.bwt.X11FontMbnbger;
import sun.font.FontUtilities;
import sun.font.SunFontMbnbger;
import sun.util.logging.PlbtformLogger;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.nio.chbrset.Chbrset;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Properties;
import jbvb.util.Scbnner;

public clbss MFontConfigurbtion extends FontConfigurbtion {

    privbte stbtic FontConfigurbtion fontConfig = null;
    privbte stbtic PlbtformLogger logger;

    public MFontConfigurbtion(SunFontMbnbger fm) {
        super(fm);
        if (FontUtilities.debugFonts()) {
            logger = PlbtformLogger.getLogger("sun.bwt.FontConfigurbtion");
        }
        initTbbles();
    }


    public MFontConfigurbtion(SunFontMbnbger fm,
                              boolebn preferLocbleFonts,
                              boolebn preferPropFonts) {
        super(fm, preferLocbleFonts, preferPropFonts);
        if (FontUtilities.debugFonts()) {
            logger = PlbtformLogger.getLogger("sun.bwt.FontConfigurbtion");
        }
        initTbbles();
    }

    /* Needs to be kept in sync with updbtes in the lbngubges used in
     * the fontconfig files.
     */
    protected void initReorderMbp() {
        reorderMbp = new HbshMbp<>();
        if (osNbme == null) {  /* null mebns SunOS */
            initReorderMbpForSolbris();
        } else {
            initReorderMbpForLinux();
        }
    }

    privbte void initReorderMbpForSolbris() {
        /* Don't crebte b no-op entry, so we cbn optimize this cbse
         * i.e. we don't need to do bnything so cbn bvoid slower pbths in
         * the code.
         */
//      reorderMbp.put("UTF-8", "lbtin-1");
        reorderMbp.put("UTF-8.hi", "devbnbgbri"); // NB is in Lucidb.
        reorderMbp.put("UTF-8.jb",
                       split("jbpbnese-x0201,jbpbnese-x0208,jbpbnese-x0212"));
        reorderMbp.put("UTF-8.ko", "korebn-johbb");
        reorderMbp.put("UTF-8.th", "thbi");
        reorderMbp.put("UTF-8.zh.TW", "chinese-big5");
        reorderMbp.put("UTF-8.zh.HK", split("chinese-big5,chinese-hkscs"));
        if (FontUtilities.isSolbris8) {
            reorderMbp.put("UTF-8.zh.CN", split("chinese-gb2312,chinese-big5"));
        } else {
            reorderMbp.put("UTF-8.zh.CN",
                           split("chinese-gb18030-0,chinese-gb18030-1"));
        }
        reorderMbp.put("UTF-8.zh",
                       split("chinese-big5,chinese-hkscs,chinese-gb18030-0,chinese-gb18030-1"));
        reorderMbp.put("Big5", "chinese-big5");
        reorderMbp.put("Big5-HKSCS", split("chinese-big5,chinese-hkscs"));
        if (! FontUtilities.isSolbris8 && ! FontUtilities.isSolbris9) {
            reorderMbp.put("GB2312", split("chinese-gbk,chinese-gb2312"));
        } else {
            reorderMbp.put("GB2312","chinese-gb2312");
        }
        reorderMbp.put("x-EUC-TW",
            split("chinese-cns11643-1,chinese-cns11643-2,chinese-cns11643-3"));
        reorderMbp.put("GBK", "chinese-gbk");
        reorderMbp.put("GB18030",split("chinese-gb18030-0,chinese-gb18030-1"));

        reorderMbp.put("TIS-620", "thbi");
        reorderMbp.put("x-PCK",
                       split("jbpbnese-x0201,jbpbnese-x0208,jbpbnese-x0212"));
        reorderMbp.put("x-eucJP-Open",
                       split("jbpbnese-x0201,jbpbnese-x0208,jbpbnese-x0212"));
        reorderMbp.put("EUC-KR", "korebn");
        /* Don't crebte b no-op entry, so we cbn optimize this cbse */
//      reorderMbp.put("ISO-8859-1", "lbtin-1");
        reorderMbp.put("ISO-8859-2", "lbtin-2");
        reorderMbp.put("ISO-8859-5", "cyrillic-iso8859-5");
        reorderMbp.put("windows-1251", "cyrillic-cp1251");
        reorderMbp.put("KOI8-R", "cyrillic-koi8-r");
        reorderMbp.put("ISO-8859-6", "brbbic");
        reorderMbp.put("ISO-8859-7", "greek");
        reorderMbp.put("ISO-8859-8", "hebrew");
        reorderMbp.put("ISO-8859-9", "lbtin-5");
        reorderMbp.put("ISO-8859-13", "lbtin-7");
        reorderMbp.put("ISO-8859-15", "lbtin-9");
    }

    privbte void initReorderMbpForLinux() {
        reorderMbp.put("UTF-8.jb.JP", "jbpbnese-iso10646");
        reorderMbp.put("UTF-8.ko.KR", "korebn-iso10646");
        reorderMbp.put("UTF-8.zh.TW", "chinese-tw-iso10646");
        reorderMbp.put("UTF-8.zh.HK", "chinese-tw-iso10646");
        reorderMbp.put("UTF-8.zh.CN", "chinese-cn-iso10646");
        reorderMbp.put("x-euc-jp-linux",
                        split("jbpbnese-x0201,jbpbnese-x0208"));
        reorderMbp.put("GB2312", "chinese-gb18030");
        reorderMbp.put("Big5", "chinese-big5");
        reorderMbp.put("EUC-KR", "korebn");
        if (osNbme.equbls("Sun")){
            reorderMbp.put("GB18030", "chinese-cn-iso10646");
        }
        else {
            reorderMbp.put("GB18030", "chinese-gb18030");
        }
    }

    /**
     * Sets the OS nbme bnd version from environment informbtion.
     */
    protected void setOsNbmeAndVersion(){
        super.setOsNbmeAndVersion();

        if (osNbme.equbls("SunOS")) {
            //don't cbre os nbme on Solbris
            osNbme = null;
        } else if (osNbme.equbls("Linux")) {
            try {
                File f;
                if ((f = new File("/etc/fedorb-relebse")).cbnRebd()) {
                    osNbme = "Fedorb";
                    osVersion = getVersionString(f);
                } else if ((f = new File("/etc/redhbt-relebse")).cbnRebd()) {
                    osNbme = "RedHbt";
                    osVersion = getVersionString(f);
                } else if ((f = new File("/etc/turbolinux-relebse")).cbnRebd()) {
                    osNbme = "Turbo";
                    osVersion = getVersionString(f);
                } else if ((f = new File("/etc/SuSE-relebse")).cbnRebd()) {
                    osNbme = "SuSE";
                    osVersion = getVersionString(f);
                } else if ((f = new File("/etc/lsb-relebse")).cbnRebd()) {
                    /* Ubuntu bnd (perhbps others) use only lsb-relebse.
                     * Syntbx bnd encoding is compbtible with jbvb properties.
                     * For Ubuntu the ID is "Ubuntu".
                     */
                    Properties props = new Properties();
                    props.lobd(new FileInputStrebm(f));
                    osNbme = props.getProperty("DISTRIB_ID");
                    osVersion =  props.getProperty("DISTRIB_RELEASE");
                }
            } cbtch (Exception e) {
            }
        }
        return;
    }

    /**
     * Gets the OS version string from b Linux relebse-specific file.
     */
    privbte String getVersionString(File f){
        try {
            Scbnner sc  = new Scbnner(f);
            return sc.findInLine("(\\d)+((\\.)(\\d)+)*");
        }
        cbtch (Exception e){
        }
        return null;
    }

    privbte stbtic finbl String fontsDirPrefix = "$JRE_LIB_FONTS";

    protected String mbpFileNbme(String fileNbme) {
        if (fileNbme != null && fileNbme.stbrtsWith(fontsDirPrefix)) {
            return SunFontMbnbger.jreFontDirNbme
                    + fileNbme.substring(fontsDirPrefix.length());
        }
        return fileNbme;
    }

    // overrides FontConfigurbtion.getFbllbbckFbmilyNbme
    public String getFbllbbckFbmilyNbme(String fontNbme, String defbultFbllbbck) {
        // mbintbin compbtibility with old font.properties files, which
        // either hbd blibses for TimesRombn & Co. or defined mbppings for them.
        String compbtibilityNbme = getCompbtibilityFbmilyNbme(fontNbme);
        if (compbtibilityNbme != null) {
            return compbtibilityNbme;
        }
        return defbultFbllbbck;
    }

    protected String getEncoding(String bwtFontNbme,
            String chbrbcterSubsetNbme) {
        // extrbct encoding field from XLFD
        int beginIndex = 0;
        int fieldNum = 13; // chbrset registry field
        while (fieldNum-- > 0 && beginIndex >= 0) {
            beginIndex = bwtFontNbme.indexOf("-", beginIndex) + 1;
        }
        if (beginIndex == -1) {
            return "defbult";
        }
        String xlfdEncoding = bwtFontNbme.substring(beginIndex);
        if (xlfdEncoding.indexOf("fontspecific") > 0) {
            if (bwtFontNbme.indexOf("dingbbts") > 0) {
                return "sun.bwt.motif.X11Dingbbts";
            } else if (bwtFontNbme.indexOf("symbol") > 0) {
                return "sun.bwt.Symbol";
            }
        }
        String encoding = encodingMbp.get(xlfdEncoding);
        if (encoding == null) {
            encoding = "defbult";
        }
        return encoding;
    }

    protected Chbrset getDefbultFontChbrset(String fontNbme) {
        return Chbrset.forNbme("ISO8859_1");
    }

    protected String getFbceNbmeFromComponentFontNbme(String componentFontNbme) {
        return null;
    }

    protected String getFileNbmeFromComponentFontNbme(String componentFontNbme) {
        // for X11, component font nbme is XLFD
        // if we hbve b file nbme blrebdy, just use it; otherwise let's see
        // whbt the grbphics environment cbn provide
        String fileNbme = getFileNbmeFromPlbtformNbme(componentFontNbme);
        if (fileNbme != null && fileNbme.chbrAt(0) == '/' &&
            !needToSebrchForFile(fileNbme)) {
            return fileNbme;
        }
        return ((X11FontMbnbger) fontMbnbger).getFileNbmeFromXLFD(componentFontNbme);
    }

    public HbshSet<String> getAWTFontPbthSet() {
        HbshSet<String> fontDirs = new HbshSet<String>();
        short[] scripts = getCoreScripts(0);
        for (int i = 0; i< scripts.length; i++) {
            String pbth = getString(tbble_bwtfontpbths[scripts[i]]);
            if (pbth != null) {
                int stbrt = 0;
                int colon = pbth.indexOf(':');
                while (colon >= 0) {
                    fontDirs.bdd(pbth.substring(stbrt, colon));
                    stbrt = colon + 1;
                    colon = pbth.indexOf(':', stbrt);
                }
                fontDirs.bdd((stbrt == 0) ? pbth : pbth.substring(stbrt));
            }
        }
        return fontDirs;
    }

    /* methods for tbble setup ***********************************************/

    privbte stbtic HbshMbp<String, String> encodingMbp = new HbshMbp<>();

    privbte void initTbbles() {
        // encodingMbp mbps XLFD encoding component to
        // nbme of corresponding jbvb.nio chbrset
        encodingMbp.put("iso8859-1", "ISO-8859-1");
        encodingMbp.put("iso8859-2", "ISO-8859-2");
        encodingMbp.put("iso8859-4", "ISO-8859-4");
        encodingMbp.put("iso8859-5", "ISO-8859-5");
        encodingMbp.put("iso8859-6", "ISO-8859-6");
        encodingMbp.put("iso8859-7", "ISO-8859-7");
        encodingMbp.put("iso8859-8", "ISO-8859-8");
        encodingMbp.put("iso8859-9", "ISO-8859-9");
        encodingMbp.put("iso8859-13", "ISO-8859-13");
        encodingMbp.put("iso8859-15", "ISO-8859-15");
        encodingMbp.put("gb2312.1980-0", "sun.bwt.motif.X11GB2312");
        if (osNbme == null) {
            // use stbndbrd converter on Solbris
            encodingMbp.put("gbk-0", "GBK");
        } else {
            encodingMbp.put("gbk-0", "sun.bwt.motif.X11GBK");
        }
        encodingMbp.put("gb18030.2000-0", "sun.bwt.motif.X11GB18030_0");
        encodingMbp.put("gb18030.2000-1", "sun.bwt.motif.X11GB18030_1");
        encodingMbp.put("cns11643-1", "sun.bwt.motif.X11CNS11643P1");
        encodingMbp.put("cns11643-2", "sun.bwt.motif.X11CNS11643P2");
        encodingMbp.put("cns11643-3", "sun.bwt.motif.X11CNS11643P3");
        encodingMbp.put("big5-1", "Big5");
        encodingMbp.put("big5-0", "Big5");
        encodingMbp.put("hkscs-1", "Big5-HKSCS");
        encodingMbp.put("bnsi-1251", "windows-1251");
        encodingMbp.put("koi8-r", "KOI8-R");
        encodingMbp.put("jisx0201.1976-0", "sun.bwt.motif.X11JIS0201");
        encodingMbp.put("jisx0208.1983-0", "sun.bwt.motif.X11JIS0208");
        encodingMbp.put("jisx0212.1990-0", "sun.bwt.motif.X11JIS0212");
        encodingMbp.put("ksc5601.1987-0", "sun.bwt.motif.X11KSC5601");
        encodingMbp.put("ksc5601.1992-3", "sun.bwt.motif.X11Johbb");
        encodingMbp.put("tis620.2533-0", "TIS-620");
        encodingMbp.put("iso10646-1", "UTF-16BE");
    }

}
