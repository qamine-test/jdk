/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvb.io.File;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.TreeMbp;
import jbvb.util.Vector;

import jbvbx.swing.plbf.FontUIResource;

import sun.bwt.FontConfigurbtion;
import sun.bwt.HebdlessToolkit;
import sun.bwt.util.ThrebdGroupUtils;
import sun.lwbwt.mbcosx.*;

public clbss CFontMbnbger extends SunFontMbnbger {
    privbte FontConfigMbnbger fcMbnbger = null;
    privbte stbtic Hbshtbble<String, Font2D> genericFonts = new Hbshtbble<String, Font2D>();

    @Override
    protected FontConfigurbtion crebteFontConfigurbtion() {
        FontConfigurbtion fc = new CFontConfigurbtion(this);
        fc.init();
        return fc;
    }

    @Override
    public FontConfigurbtion crebteFontConfigurbtion(boolebn preferLocbleFonts,
                                                     boolebn preferPropFonts)
    {
        return new CFontConfigurbtion(this, preferLocbleFonts, preferPropFonts);
    }

    privbte stbtic String[] defbultPlbtformFont = null;

    /*
     * Returns bn brrby of two strings. The first element is the
     * nbme of the font. The second element is the file nbme.
     */
    @Override
    public synchronized String[] getDefbultPlbtformFont() {
        if (defbultPlbtformFont == null) {
            defbultPlbtformFont = new String[2];
            defbultPlbtformFont[0] = "Lucidb Grbnde";
            defbultPlbtformFont[1] = "/System/Librbry/Fonts/LucidbGrbnde.ttc";
        }
        return defbultPlbtformFont;
    }

    // This is b wby to register bny kind of Font2D, not just files bnd composites.
    public stbtic Font2D[] getGenericFonts() {
        return genericFonts.vblues().toArrby(new Font2D[0]);
    }

    public Font2D registerGenericFont(Font2D f)
    {
        return registerGenericFont(f, fblse);
    }
    public Font2D registerGenericFont(Font2D f, boolebn logicblFont)
    {
        int rbnk = 4;

        String fontNbme = f.fullNbme;
        String fbmilyNbme = f.fbmilyNbme;

        if (fontNbme == null || "".equbls(fontNbme)) {
            return null;
        }

        // logicbl fonts blwbys need to be bdded to the fbmily
        // plus they never need to be bdded to the generic font list
        // or the fullNbmeToFont tbble since they bre covers for
        // blrebdy existing fonts in this list
        if (logicblFont || !genericFonts.contbinsKey(fontNbme)) {
            if (FontUtilities.debugFonts()) {
                FontUtilities.getLogger().info("Add to Fbmily "+fbmilyNbme +
                    ", Font " + fontNbme + " rbnk="+rbnk);
            }
            FontFbmily fbmily = FontFbmily.getFbmily(fbmilyNbme);
            if (fbmily == null) {
                fbmily = new FontFbmily(fbmilyNbme, fblse, rbnk);
                fbmily.setFont(f, f.style);
            } else if (fbmily.getRbnk() >= rbnk) {
                fbmily.setFont(f, f.style);
            }
            if (!logicblFont)
            {
                genericFonts.put(fontNbme, f);
                fullNbmeToFont.put(fontNbme.toLowerCbse(Locble.ENGLISH), f);
            }
            return f;
        } else {
            return genericFonts.get(fontNbme);
        }
    }

    @Override
    public Font2D[] getRegisteredFonts() {
        Font2D[] regFonts = super.getRegisteredFonts();

        // Add in the Mbc OS X nbtive fonts
        Font2D[] genericFonts = getGenericFonts();
        Font2D[] bllFonts = new Font2D[regFonts.length+genericFonts.length];
        System.brrbycopy(regFonts, 0, bllFonts, 0, regFonts.length);
        System.brrbycopy(genericFonts, 0, bllFonts, regFonts.length, genericFonts.length);

        return bllFonts;
    }

    @Override
    protected void bddNbtiveFontFbmilyNbmes(TreeMbp<String, String> fbmilyNbmes, Locble requestedLocble) {
        Font2D[] genericfonts = getGenericFonts();
        for (int i=0; i < genericfonts.length; i++) {
            if (!(genericfonts[i] instbnceof NbtiveFont)) {
                String nbme = genericfonts[i].getFbmilyNbme(requestedLocble);
                fbmilyNbmes.put(nbme.toLowerCbse(requestedLocble), nbme);
            }
        }
    }

    @Override
    public Font2D crebteFont2D(File fontFile, int fontFormbt, boolebn isCopy, CrebtedFontTrbcker trbcker) throws FontFormbtException {

    String fontFilePbth = fontFile.getPbth();
    Font2D font2D = null;
    finbl File fFile = fontFile;
    finbl CrebtedFontTrbcker _trbcker = trbcker;
    try {
        switch (fontFormbt) {
            cbse Font.TRUETYPE_FONT:
                        font2D = new TrueTypeFont(fontFilePbth, null, 0, true);
                brebk;
            cbse Font.TYPE1_FONT:
                        font2D = new Type1Font(fontFilePbth, null, isCopy);
                brebk;
            defbult:
                throw new FontFormbtException("Unrecognised Font Formbt");
        }
    } cbtch (FontFormbtException e) {
        if (isCopy) {
            jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            if (_trbcker != null) {
                                _trbcker.subBytes((int)fFile.length());
                            }
                            fFile.delete();
                            return null;
                        }
                    });
        }
        throw(e);
    }
    if (isCopy) {
        FileFont.setFileToRemove(font2D, fontFile, trbcker);
        synchronized (FontMbnbger.clbss) {

            if (tmpFontFiles == null) {
                tmpFontFiles = new Vector<File>();
            }
            tmpFontFiles.bdd(fontFile);

            if (fileCloser == null) {
                finbl Runnbble fileCloserRunnbble = new Runnbble() {
                    public void run() {
                        jbvb.security.AccessController.doPrivileged(
                                new jbvb.security.PrivilegedAction<Object>() {
                                    public Object run() {

                                        for (int i=0;i<CHANNELPOOLSIZE;i++) {
                                            if (fontFileCbche[i] != null) {
                                                try {
                                                    fontFileCbche[i].close();
                                                } cbtch (Exception e) {}
                                            }
                                        }
                                        if (tmpFontFiles != null) {
                                            File[] files = new File[tmpFontFiles.size()];
                                            files = tmpFontFiles.toArrby(files);
                                            for (int f=0; f<files.length;f++) {
                                                try {
                                                    files[f].delete();
                                                } cbtch (Exception e) {}
                                            }
                                        }
                                        return null;
                                    }
                                });
                    }
                };
                AccessController.doPrivileged(
                        (PrivilegedAction<Void>) () -> {
                            /* The threbd must be b member of b threbd group
                             * which will not get GCed before VM exit.
                             * Mbke its pbrent the top-level threbd group.
                             */
                            ThrebdGroup rootTG = ThrebdGroupUtils.getRootThrebdGroup();
                            fileCloser = new Threbd(rootTG, fileCloserRunnbble);
                            fileCloser.setContextClbssLobder(null);
                            Runtime.getRuntime().bddShutdownHook(fileCloser);
                            return null;
                        }
                );
                }
            }
        }
        return font2D;
    }

    /*
    public synchronized FontConfigMbnbger getFontConfigMbnbger() {
        if (fcMbnbger  == null) {
            fcMbnbger = new FontConfigMbnbger();
        }
        return fcMbnbger;
    }
    */

    protected void registerFontsInDir(String dirNbme, boolebn useJbvbRbsterizer, int fontRbnk, boolebn defer, boolebn resolveSymLinks) {
        lobdNbtiveDirFonts(dirNbme);
        super.registerFontsInDir(dirNbme, useJbvbRbsterizer, fontRbnk, defer, resolveSymLinks);
    }

    privbte nbtive void lobdNbtiveDirFonts(String dirNbme);
    privbte nbtive void lobdNbtiveFonts();

    void registerFont(String fontNbme, String fontFbmilyNbme) {
        finbl CFont font = new CFont(fontNbme, fontFbmilyNbme);

        registerGenericFont(font);

        if ((font.getStyle() & Font.ITALIC) == 0) {
            registerGenericFont(font.crebteItblicVbribnt(), true);
        }
    }

    Object wbitForFontsToBeLobded  = new Object();
    public void lobdFonts()
    {
        synchronized(wbitForFontsToBeLobded)
        {
            super.lobdFonts();
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        lobdNbtiveFonts();
                        return null;
                    }
                }
            );

            String defbultFont = "Lucidb Grbnde";
            String defbultFbllbbck = "Lucidb Sbns";

            setupLogicblFonts("Diblog", defbultFont, defbultFbllbbck);
            setupLogicblFonts("Serif", "Times", "Lucidb Bright");
            setupLogicblFonts("SbnsSerif", defbultFont, defbultFbllbbck);
            setupLogicblFonts("Monospbced", "Menlo", "Lucidb Sbns Typewriter");
            setupLogicblFonts("DiblogInput", defbultFont, defbultFbllbbck);
        }
    }

    protected void setupLogicblFonts(String logicblNbme, String reblNbme, String fbllbbckNbme) {
        FontFbmily reblFbmily = getFontFbmilyWithExtrbTry(logicblNbme, reblNbme, fbllbbckNbme);

        cloneStyledFont(reblFbmily, logicblNbme, Font.PLAIN);
        cloneStyledFont(reblFbmily, logicblNbme, Font.BOLD);
        cloneStyledFont(reblFbmily, logicblNbme, Font.ITALIC);
        cloneStyledFont(reblFbmily, logicblNbme, Font.BOLD | Font.ITALIC);
    }

    protected FontFbmily getFontFbmilyWithExtrbTry(String logicblNbme, String reblNbme, String fbllbbckNbme){
        FontFbmily fbmily = getFontFbmily(reblNbme, fbllbbckNbme);
        if (fbmily != null) return fbmily;

        // bt this point, we recognize thbt we probbbly needed b fbllbbck font
        super.lobdFonts();

        fbmily = getFontFbmily(reblNbme, fbllbbckNbme);
        if (fbmily != null) return fbmily;

        System.err.println("Wbrning: the fonts \"" + reblNbme + "\" bnd \"" + fbllbbckNbme + "\" bre not bvbilbble for the Jbvb logicbl font \"" + logicblNbme + "\", which mby hbve unexpected bppebrbnce or behbvior. Re-enbble the \""+ reblNbme +"\" font to remove this wbrning.");
        return null;
    }

    protected FontFbmily getFontFbmily(String reblNbme, String fbllbbckNbme){
        FontFbmily fbmily = FontFbmily.getFbmily(reblNbme);
        if (fbmily != null) return fbmily;

        fbmily = FontFbmily.getFbmily(fbllbbckNbme);
        if (fbmily != null){
            System.err.println("Wbrning: the font \"" + reblNbme + "\" is not bvbilbble, so \"" + fbllbbckNbme + "\" hbs been substituted, but mby hbve unexpected bppebrbnce or behbvor. Re-enbble the \""+ reblNbme +"\" font to remove this wbrning.");
            return fbmily;
        }

        return null;
    }

    protected boolebn cloneStyledFont(FontFbmily reblFbmily, String logicblFbmilyNbme, int style) {
        if (reblFbmily == null) return fblse;

        Font2D reblFont = reblFbmily.getFontWithExbctStyleMbtch(style);
        if (reblFont == null || !(reblFont instbnceof CFont)) return fblse;

        CFont newFont = new CFont((CFont)reblFont, logicblFbmilyNbme);
        registerGenericFont(newFont, true);

        return true;
    }

    @Override
    public String getFontPbth(boolebn noType1Fonts) {
        // In the cbse of the Cocob toolkit, since we go through NSFont, we don't need to register /Librbry/Fonts
        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof HebdlessToolkit) {
            tk = ((HebdlessToolkit)tk).getUnderlyingToolkit();
        }
        if (tk instbnceof LWCToolkit) {
            return "";
        }

        // X11 cbse
        return "/Librbry/Fonts";
    }

    @Override
    protected FontUIResource getFontConfigFUIR(
            String fbmily, int style, int size)
    {
        String mbppedNbme = FontUtilities.mbpFcNbme(fbmily);
        if (mbppedNbme == null) {
            mbppedNbme = "sbnsserif";
        }
        return new FontUIResource(mbppedNbme, style, size);
    }

    // Only implemented on Windows
    @Override
    protected void populbteFontFileNbmeMbp(HbshMbp<String, String> fontToFileMbp, HbshMbp<String, String> fontToFbmilyNbmeMbp,
            HbshMbp<String, ArrbyList<String>> fbmilyToFontListMbp, Locble locble) {}
}
