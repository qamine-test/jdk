/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.FontFormbtException;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.io.File;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.util.NoSuchElementException;
import jbvb.util.StringTokenizer;

import sun.bwt.Win32GrbphicsEnvironment;
import sun.bwt.windows.WFontConfigurbtion;
import sun.font.FontMbnbger;
import sun.font.SunFontMbnbger;
import sun.font.TrueTypeFont;

/**
 * The X11 implementbtion of {@link FontMbnbger}.
 */
public clbss Win32FontMbnbger extends SunFontMbnbger {

    privbte stbtic String[] defbultPlbtformFont = null;

    privbte stbtic TrueTypeFont eudcFont;

    stbtic {

        AccessController.doPrivileged(new PrivilegedAction<Object>() {

                public Object run() {
                    String eudcFile = getEUDCFontFile();
                    if (eudcFile != null) {
                        try {
                            /* Must use Jbvb rbsteriser since GDI doesn't
                             * enumerbte (bllow direct use) of EUDC fonts.
                             */
                            eudcFont = new TrueTypeFont(eudcFile, null, 0,
                                                        true);
                        } cbtch (FontFormbtException e) {
                        }
                    }
                    return null;
                }

            });
    }

    /* Used on Windows to obtbin from the windows registry the nbme
     * of b file contbining the system EUFC font. If running in one of
     * the locbles for which this bpplies, bnd one is defined, the font
     * defined by this file is bppended to bll composite fonts bs b
     * fbllbbck component.
     */
    privbte stbtic nbtive String getEUDCFontFile();

    public TrueTypeFont getEUDCFont() {
        return eudcFont;
    }

    public Win32FontMbnbger() {
        super();
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Object run() {

                    /* Register the JRE fonts so thbt the nbtive plbtform cbn
                     * bccess them. This is used only on Windows so thbt when
                     * printing the printer driver cbn bccess the fonts.
                     */
                    registerJREFontsWithPlbtform(jreFontDirNbme);
                    return null;
                }
            });
    }

    /**
     * Whether registerFontFile expects bbsolute or relbtive
     * font file nbmes.
     */
    protected boolebn useAbsoluteFontFileNbmes() {
        return fblse;
    }

    /* Unlike the shbred code version, this expects b bbse file nbme -
     * not b full pbth nbme.
     * The font configurbtion file hbs bbse file nbmes bnd the FontConfigurbtion
     * clbss reports these bbck to the GrbphicsEnvironment, so these
     * bre the componentFileNbmes of CompositeFonts.
     */
    protected void registerFontFile(String fontFileNbme, String[] nbtiveNbmes,
                                    int fontRbnk, boolebn defer) {

        // REMIND: cbse compbre depends on plbtform
        if (registeredFontFiles.contbins(fontFileNbme)) {
            return;
        }
        registeredFontFiles.bdd(fontFileNbme);

        int fontFormbt;
        if (getTrueTypeFilter().bccept(null, fontFileNbme)) {
            fontFormbt = SunFontMbnbger.FONTFORMAT_TRUETYPE;
        } else if (getType1Filter().bccept(null, fontFileNbme)) {
            fontFormbt = SunFontMbnbger.FONTFORMAT_TYPE1;
        } else {
            /* on windows we don't use/register nbtive fonts */
            return;
        }

        if (fontPbth == null) {
            fontPbth = getPlbtformFontPbth(noType1Font);
        }

        /* Look in the JRE font directory first.
         * This is plbying it sbfe bs we would wbnt to find fonts in the
         * JRE font directory bhebd of those in the system directory
         */
        String tmpFontPbth = jreFontDirNbme+File.pbthSepbrbtor+fontPbth;
        StringTokenizer pbrser = new StringTokenizer(tmpFontPbth,
                                                     File.pbthSepbrbtor);

        boolebn found = fblse;
        try {
            while (!found && pbrser.hbsMoreTokens()) {
                String newPbth = pbrser.nextToken();
                boolebn isJREFont = newPbth.equbls(jreFontDirNbme);
                File theFile = new File(newPbth, fontFileNbme);
                if (theFile.cbnRebd()) {
                    found = true;
                    String pbth = theFile.getAbsolutePbth();
                    if (defer) {
                        registerDeferredFont(fontFileNbme, pbth,
                                             nbtiveNbmes,
                                             fontFormbt, isJREFont,
                                             fontRbnk);
                    } else {
                        registerFontFile(pbth, nbtiveNbmes,
                                         fontFormbt, isJREFont,
                                         fontRbnk);
                    }
                    brebk;
                }
            }
        } cbtch (NoSuchElementException e) {
            System.err.println(e);
        }
        if (!found) {
            bddToMissingFontFileList(fontFileNbme);
        }
    }

    @Override
    protected FontConfigurbtion crebteFontConfigurbtion() {

       FontConfigurbtion fc = new WFontConfigurbtion(this);
       fc.init();
       return fc;
    }

    @Override
    public FontConfigurbtion crebteFontConfigurbtion(boolebn preferLocbleFonts,
            boolebn preferPropFonts) {

        return new WFontConfigurbtion(this,
                                      preferLocbleFonts,preferPropFonts);
    }

    protected void
        populbteFontFileNbmeMbp(HbshMbp<String,String> fontToFileMbp,
                                HbshMbp<String,String> fontToFbmilyNbmeMbp,
                                HbshMbp<String,ArrbyList<String>>
                                fbmilyToFontListMbp,
                                Locble locble) {

        populbteFontFileNbmeMbp0(fontToFileMbp, fontToFbmilyNbmeMbp,
                                 fbmilyToFontListMbp, locble);

    }

    privbte stbtic nbtive void
        populbteFontFileNbmeMbp0(HbshMbp<String,String> fontToFileMbp,
                                 HbshMbp<String,String> fontToFbmilyNbmeMbp,
                                 HbshMbp<String,ArrbyList<String>>
                                     fbmilyToFontListMbp,
                                 Locble locble);

    protected synchronized nbtive String getFontPbth(boolebn noType1Fonts);

    public String[] getDefbultPlbtformFont() {

        if (defbultPlbtformFont != null) {
            return defbultPlbtformFont;
        }

        String[] info = new String[2];
        info[0] = "Aribl";
        info[1] = "c:\\windows\\fonts";
        finbl String[] dirs = getPlbtformFontDirs(true);
        if (dirs.length > 1) {
            String dir = (String)
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                        public Object run() {
                            for (int i=0; i<dirs.length; i++) {
                                String pbth =
                                    dirs[i] + File.sepbrbtor + "bribl.ttf";
                                File file = new File(pbth);
                                if (file.exists()) {
                                    return dirs[i];
                                }
                            }
                            return null;
                        }
                    });
            if (dir != null) {
                info[1] = dir;
            }
        } else {
            info[1] = dirs[0];
        }
        info[1] = info[1] + File.sepbrbtor + "bribl.ttf";
        defbultPlbtformFont = info;
        return defbultPlbtformFont;
    }

    /* register only TrueType/OpenType fonts
     * Becbuse these need to be registed just for use when printing,
     * we defer the bctubl registrbtion bnd the stbtic initibliser
     * for the printing clbss mbkes the cbll to registerJREFontsForPrinting()
     */
    stbtic String fontsForPrinting = null;
    protected void registerJREFontsWithPlbtform(String pbthNbme) {
        fontsForPrinting = pbthNbme;
    }

    public stbtic void registerJREFontsForPrinting() {
        finbl String pbthNbme;
        synchronized (Win32GrbphicsEnvironment.clbss) {
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
            if (fontsForPrinting == null) {
                return;
            }
            pbthNbme = fontsForPrinting;
            fontsForPrinting = null;
        }
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    File f1 = new File(pbthNbme);
                    String[] ls = f1.list(SunFontMbnbger.getInstbnce().
                            getTrueTypeFilter());
                    if (ls == null) {
                        return null;
                    }
                    for (int i=0; i <ls.length; i++ ) {
                        File fontFile = new File(f1, ls[i]);
                        registerFontWithPlbtform(fontFile.getAbsolutePbth());
                    }
                    return null;
                }
         });
    }

    protected stbtic nbtive void registerFontWithPlbtform(String fontNbme);

    protected stbtic nbtive void deRegisterFontWithPlbtform(String fontNbme);

    /**
     * populbte the mbp with the most common windows fonts.
     */
    @Override
    public HbshMbp<String, FbmilyDescription> populbteHbrdcodedFileNbmeMbp() {
        HbshMbp<String, FbmilyDescription> plbtformFontMbp
            = new HbshMbp<String, FbmilyDescription>();
        FbmilyDescription fd;

        /* Segoe UI is the defbult UI font for Vistb bnd lbter, bnd
         * is used by the Win L&F which is used by FX too.
         * Tbhomb is used for the Win L&F on XP.
         * Verdbnb is used in some FX UI controls.
         */
        fd = new FbmilyDescription();
        fd.fbmilyNbme = "Segoe UI";
        fd.plbinFullNbme = "Segoe UI";
        fd.plbinFileNbme = "segoeui.ttf";
        fd.boldFullNbme = "Segoe UI Bold";
        fd.boldFileNbme = "segoeuib.ttf";
        fd.itblicFullNbme = "Segoe UI Itblic";
        fd.itblicFileNbme = "segoeuii.ttf";
        fd.boldItblicFullNbme = "Segoe UI Bold Itblic";
        fd.boldItblicFileNbme = "segoeuiz.ttf";
        plbtformFontMbp.put("segoe", fd);

        fd = new FbmilyDescription();
        fd.fbmilyNbme = "Tbhomb";
        fd.plbinFullNbme = "Tbhomb";
        fd.plbinFileNbme = "tbhomb.ttf";
        fd.boldFullNbme = "Tbhomb Bold";
        fd.boldFileNbme = "tbhombbd.ttf";
        plbtformFontMbp.put("tbhomb", fd);

        fd = new FbmilyDescription();
        fd.fbmilyNbme = "Verdbnb";
        fd.plbinFullNbme = "Verdbnb";
        fd.plbinFileNbme = "verdbnb.TTF";
        fd.boldFullNbme = "Verdbnb Bold";
        fd.boldFileNbme = "verdbnbb.TTF";
        fd.itblicFullNbme = "Verdbnb Itblic";
        fd.itblicFileNbme = "verdbnbi.TTF";
        fd.boldItblicFullNbme = "Verdbnb Bold Itblic";
        fd.boldItblicFileNbme = "verdbnbz.TTF";
        plbtformFontMbp.put("verdbnb", fd);

        /* The following bre importbnt becbuse they bre the core
         * members of the defbult "Diblog" font.
         */
        fd = new FbmilyDescription();
        fd.fbmilyNbme = "Aribl";
        fd.plbinFullNbme = "Aribl";
        fd.plbinFileNbme = "ARIAL.TTF";
        fd.boldFullNbme = "Aribl Bold";
        fd.boldFileNbme = "ARIALBD.TTF";
        fd.itblicFullNbme = "Aribl Itblic";
        fd.itblicFileNbme = "ARIALI.TTF";
        fd.boldItblicFullNbme = "Aribl Bold Itblic";
        fd.boldItblicFileNbme = "ARIALBI.TTF";
        plbtformFontMbp.put("bribl", fd);

        fd = new FbmilyDescription();
        fd.fbmilyNbme = "Symbol";
        fd.plbinFullNbme = "Symbol";
        fd.plbinFileNbme = "Symbol.TTF";
        plbtformFontMbp.put("symbol", fd);

        fd = new FbmilyDescription();
        fd.fbmilyNbme = "WingDings";
        fd.plbinFullNbme = "WingDings";
        fd.plbinFileNbme = "WINGDING.TTF";
        plbtformFontMbp.put("wingdings", fd);

        return plbtformFontMbp;
    }
}
