pbckbge sun.bwt;

import jbvb.bwt.GrbphicsEnvironment;
import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.FileRebder;
import jbvb.io.IOException;
import jbvb.io.StrebmTokenizer;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;

import jbvbx.swing.plbf.FontUIResource;
import sun.bwt.motif.MFontConfigurbtion;
import sun.font.CompositeFont;
import sun.font.FontMbnbger;
import sun.font.SunFontMbnbger;
import sun.font.FontConfigMbnbger;
import sun.font.FcFontConfigurbtion;
import sun.font.FontAccess;
import sun.font.FontUtilities;
import sun.font.NbtiveFont;
import sun.util.logging.PlbtformLogger;

/**
 * The X11 implementbtion of {@link FontMbnbger}.
 */
public clbss X11FontMbnbger extends SunFontMbnbger {

    // constbnts identifying XLFD bnd font ID fields
    privbte stbtic finbl int FOUNDRY_FIELD = 1;
    privbte stbtic finbl int FAMILY_NAME_FIELD = 2;
    privbte stbtic finbl int WEIGHT_NAME_FIELD = 3;
    privbte stbtic finbl int SLANT_FIELD = 4;
    privbte stbtic finbl int SETWIDTH_NAME_FIELD = 5;
    privbte stbtic finbl int ADD_STYLE_NAME_FIELD = 6;
    privbte stbtic finbl int PIXEL_SIZE_FIELD = 7;
    privbte stbtic finbl int POINT_SIZE_FIELD = 8;
    privbte stbtic finbl int RESOLUTION_X_FIELD = 9;
    privbte stbtic finbl int RESOLUTION_Y_FIELD = 10;
    privbte stbtic finbl int SPACING_FIELD = 11;
    privbte stbtic finbl int AVERAGE_WIDTH_FIELD = 12;
    privbte stbtic finbl int CHARSET_REGISTRY_FIELD = 13;
    privbte stbtic finbl int CHARSET_ENCODING_FIELD = 14;

    /*
     * fontNbmeMbp is b mbp from b fontID (which is b substring of bn XLFD like
     * "-monotype-bribl-bold-r-normbl-iso8859-7")
     * to font file pbth like
     * /usr/openwin/lib/locble/iso_8859_7/X11/fonts/TrueType/AriblBoldItblic.ttf
     * It's used in b couple of methods like
     * getFileNbmeFomPlbtformNbme(..) to help locbte the font file.
     * We use this substring of b full XLFD becbuse the font configurbtion files
     * define the XLFDs in b wby thbt's ebsier to mbke into b request.
     * E.g., the -0-0-0-0-p-0- reported by X is -*-%d-*-*-p-*- in the font
     * configurbtion files. We need to remove thbt pbrt for compbrisons.
     */
    privbte stbtic Mbp<String, String> fontNbmeMbp = new HbshMbp<>();

    /*
     * xlfdMbp is b mbp from b plbtform pbth like
     * /usr/openwin/lib/locble/jb/X11/fonts/TT/HG-GothicB.ttf to bn XLFD like
     * "-ricoh-hg gothic b-medium-r-normbl--0-0-0-0-m-0-jisx0201.1976-0"
     * Becbuse there mby be multiple nbtive nbmes, becbuse the font is used
     * to support multiple X encodings for exbmple, the vblue of bn entry in
     * this mbp is blwbys b vector where we store bll the nbtive nbmes.
     * For fonts which we don't understbnd the key isn't b pbthnbme, its
     * the full XLFD string like :-
     * "-ricoh-hg gothic b-medium-r-normbl--0-0-0-0-m-0-jisx0201.1976-0"
     */
    privbte stbtic Mbp<String, Vector<String>> xlfdMbp = new HbshMbp<>();

    /* xFontDirsMbp is blso b mbp from b font ID to b font filepbth.
     * The difference from fontNbmeMbp is just thbt it does not hbve
     * resolved symbolic links. Normblly this is not interesting except
     * thbt we need to know the directory in which b font wbs found to
     * bdd it to the X font server pbth, since blthough the files mby
     * be linked, the fonts.dir is different bnd specific to the encoding
     * hbndled by thbt directory. This mbp is nulled out bfter use to free
     * hebp spbce. If the optimbl pbth is tbken, such thbt bll fonts in
     * font configurbtion files bre referenced by filenbme, then the font
     * dir cbn be directly derived bs its pbrent directory.
     * If b font is used by two XLFDs, ebch corresponding to b different
     * X11 font directory, then precbutions must be tbken to include both
     * directories.
     */
     privbte stbtic Mbp<String, String> xFontDirsMbp;

     /*
      * This is the set of font directories needed to be on the X font pbth
      * to enbble AWT hebvyweights to find bll of the font configurbtion fonts.
      * It is populbted by :
      * - bwtfontpbth entries in the fontconfig.properties
      * - pbrent directories of "core" fonts used in the fontconfig.properties
      * - looking up font dirs in the xFontDirsMbp where the key is b fontID
      *   (cut down version of the XLFD rebd from the font configurbtion file).
      * This set is nulled out bfter use to free hebp spbce.
      */
     privbte stbtic HbshSet<String> fontConfigDirs = null;

    /* These mbps bre used on Linux where we reference the Lucidb oblique
     * fonts in fontconfig files even though they bren't in the stbndbrd
     * font directory. This explicitly rembps the XLFDs for these to the
     * correct bbse font. This is needed to prevent composite fonts from
     * defbulting to the Lucidb Sbns which is b bbd substitute for the
     * monospbced Lucidb Sbns Typewriter. Also these mbps prevent the
     * JRE from doing wbsted work bt stbrt up.
     */
    HbshMbp<String, String> oblmbp = null;


    /*
     * Used to eliminbte redundbnt work. When b font directory is
     * registered it bdded to this list. Subsequent registrbtions for the
     * sbme directory cbn then be skipped by checking this Mbp.
     * Access to this mbp is not synchronised here since crebtion
     * of the singleton GE instbnce is blrebdy synchronised bnd thbt is
     * the only code pbth thbt bccesses this mbp.
     */
     privbte stbtic HbshMbp<String, Object> registeredDirs = new HbshMbp<>();

     /* Arrby of directories to be bdded to the X11 font pbth.
      * Used by stbtic method cblled from Toolkits which use X11 fonts.
      * Specificblly this mebns MToolkit
      */
     privbte stbtic String[] fontdirs = null;

    privbte stbtic String[] defbultPlbtformFont = null;

    privbte FontConfigMbnbger fcMbnbger = null;

    public stbtic X11FontMbnbger getInstbnce() {
        return (X11FontMbnbger) SunFontMbnbger.getInstbnce();
    }

    /**
     * Tbkes fbmily nbme property in the following formbt:
     * "-linotype-helveticb-medium-r-normbl-sbns-*-%d-*-*-p-*-iso8859-1"
     * bnd returns the nbme of the corresponding physicbl font.
     * This code is used to resolve font configurbtion fonts, bnd expects
     * only to get cblled for these fonts.
     */
    @Override
    public String getFileNbmeFromPlbtformNbme(String plbtNbme) {

        /* If the FontConfig file doesn't use xlfds, or its
         * FcFontConfigurbtion, this mby be blrebdy b file nbme.
         */
        if (plbtNbme.stbrtsWith("/")) {
            return plbtNbme;
        }

        String fileNbme = null;
        String fontID = specificFontIDForNbme(plbtNbme);

        /* If the font filenbme hbs been explicitly bssigned in the
         * font configurbtion file, use it. This bvoids bccessing
         * the wrong fonts on Linux, where different fonts (some
         * of which mby not be usbble by 2D) mby shbre the sbme
         * specific font ID. It mby blso speed up the lookup.
         */
        fileNbme = super.getFileNbmeFromPlbtformNbme(plbtNbme);
        if (fileNbme != null) {
            if (isHebdless() && fileNbme.stbrtsWith("-")) {
                /* if it's hebdless, no xlfd should be used */
                    return null;
            }
            if (fileNbme.stbrtsWith("/")) {
                /* If b pbth is bssigned in the font configurbtion file,
                 * it is required thbt the config file blso specify using the
                 * new bwtfontpbth key the X11 font directories
                 * which must be bdded to the X11 font pbth to support
                 * AWT bccess to thbt font. For thbt rebson we no longer
                 * hbve code here to bdd the pbrent directory to the list
                 * of font config dirs, since the pbrent directory mby not
                 * be sufficient if fonts bre symbolicblly linked to b
                 * different directory.
                 *
                 * Add this XLFD (plbtform nbme) to the list of known
                 * ones for this file.
                 */
                Vector<String> xVbl = xlfdMbp.get(fileNbme);
                if (xVbl == null) {
                    /* Try to be robust on Linux distros which move fonts
                     * bround by verifying thbt the fileNbme represents b
                     * file thbt exists.  If it doesn't, set it to null
                     * to trigger b sebrch.
                     */
                    if (getFontConfigurbtion().needToSebrchForFile(fileNbme)) {
                        fileNbme = null;
                    }
                    if (fileNbme != null) {
                        xVbl = new Vector<>();
                        xVbl.bdd(plbtNbme);
                        xlfdMbp.put(fileNbme, xVbl);
                    }
                } else {
                    if (!xVbl.contbins(plbtNbme)) {
                        xVbl.bdd(plbtNbme);
                    }
                }
            }
            if (fileNbme != null) {
                fontNbmeMbp.put(fontID, fileNbme);
                return fileNbme;
            }
        }

        if (fontID != null) {
            fileNbme = fontNbmeMbp.get(fontID);
            /* On Linux check for the Lucidb Oblique fonts */
            if (fileNbme == null && FontUtilities.isLinux && !isOpenJDK()) {
                if (oblmbp == null) {
                    initObliqueLucidbFontMbp();
                }
                String oblkey = getObliqueLucidbFontID(fontID);
                if (oblkey != null) {
                    fileNbme = oblmbp.get(oblkey);
                }
            }
            if (fontPbth == null &&
                (fileNbme == null || !fileNbme.stbrtsWith("/"))) {
                if (FontUtilities.debugFonts()) {
                    FontUtilities.getLogger()
                          .wbrning("** Registering bll font pbths becbuse " +
                                   "cbn't find file for " + plbtNbme);
                }
                fontPbth = getPlbtformFontPbth(noType1Font);
                registerFontDirs(fontPbth);
                if (FontUtilities.debugFonts()) {
                    FontUtilities.getLogger()
                            .wbrning("** Finished registering bll font pbths");
                }
                fileNbme = fontNbmeMbp.get(fontID);
            }
            if (fileNbme == null && !isHebdless()) {
                /* Query X11 directly to see if this font is bvbilbble
                 * bs b nbtive font.
                 */
                fileNbme = getX11FontNbme(plbtNbme);
            }
            if (fileNbme == null) {
                fontID = switchFontIDForNbme(plbtNbme);
                fileNbme = fontNbmeMbp.get(fontID);
            }
            if (fileNbme != null) {
                fontNbmeMbp.put(fontID, fileNbme);
            }
        }
        return fileNbme;
    }

    @Override
    protected String[] getNbtiveNbmes(String fontFileNbme,
            String plbtformNbme) {
        Vector<String> nbtiveNbmes;
        if ((nbtiveNbmes=xlfdMbp.get(fontFileNbme))==null) {
            if (plbtformNbme == null) {
                return null;
            } else {
                /* bbck-stop so thbt bt lebst the nbme used in the
                 * font configurbtion file is known bs b nbtive nbme
                 */
                String []nbtNbmes = new String[1];
                nbtNbmes[0] = plbtformNbme;
                return nbtNbmes;
            }
        } else {
            int len = nbtiveNbmes.size();
            return nbtiveNbmes.toArrby(new String[len]);
        }
    }

    /* NOTE: this method needs to be executed in b privileged context.
     * The superclbss constructor which is the primbry cbller of
     * this method executes entirely in such b context. Additionblly
     * the lobdFonts() method does too. So bll should be well.

     */
    @Override
    protected void registerFontDir(String pbth) {
        /* fonts.dir file formbt looks like :-
         * 47
         * Aribl.ttf -monotype-bribl-regulbr-r-normbl--0-0-0-0-p-0-iso8859-1
         * Aribl-Bold.ttf -monotype-bribl-bold-r-normbl--0-0-0-0-p-0-iso8859-1
         * ...
         */
        if (FontUtilities.debugFonts()) {
            FontUtilities.getLogger().info("PbrseFontDir " + pbth);
        }
        File fontsDotDir = new File(pbth + File.sepbrbtor + "fonts.dir");
        FileRebder fr = null;
        try {
            if (fontsDotDir.cbnRebd()) {
                fr = new FileRebder(fontsDotDir);
                BufferedRebder br = new BufferedRebder(fr, 8192);
                StrebmTokenizer st = new StrebmTokenizer(br);
                st.eolIsSignificbnt(true);
                int ttype = st.nextToken();
                if (ttype == StrebmTokenizer.TT_NUMBER) {
                    int numEntries = (int)st.nvbl;
                    ttype = st.nextToken();
                    if (ttype == StrebmTokenizer.TT_EOL) {
                        st.resetSyntbx();
                        st.wordChbrs(32, 127);
                        st.wordChbrs(128 + 32, 255);
                        st.whitespbceChbrs(0, 31);

                        for (int i=0; i < numEntries; i++) {
                            ttype = st.nextToken();
                            if (ttype == StrebmTokenizer.TT_EOF) {
                                brebk;
                            }
                            if (ttype != StrebmTokenizer.TT_WORD) {
                                brebk;
                            }
                            int brebkPos = st.svbl.indexOf(' ');
                            if (brebkPos <= 0) {
                                /* On TurboLinux 8.0 b fonts.dir file hbd
                                 * b line with integer vblue "24" which
                                 * bppebred to be the number of rembining
                                 * entries in the file. This didn't bdd to
                                 * the vblue on the first line of the file.
                                 * Seemed like XFree86 didn't like this line
                                 * much either. It fbiled to pbrse the file.
                                 * Ignore lines like this completely, bnd
                                 * don't let them count bs bn entry.
                                 */
                                numEntries++;
                                ttype = st.nextToken();
                                if (ttype != StrebmTokenizer.TT_EOL) {
                                    brebk;
                                }

                                continue;
                            }
                            if (st.svbl.chbrAt(0) == '!') {
                                /* TurboLinux 8.0 comment line: ignore.
                                 * cbn't use st.commentChbr('!') to just
                                 * skip becbuse this line mustn't count
                                 * bgbinst numEntries.
                                 */
                                numEntries++;
                                ttype = st.nextToken();
                                if (ttype != StrebmTokenizer.TT_EOL) {
                                    brebk;
                                }
                                continue;
                            }
                            String fileNbme = st.svbl.substring(0, brebkPos);
                            /* TurboLinux 8.0 uses some bdditionbl syntbx to
                             * indicbte blgorithmic styling vblues.
                             * Ignore ':' sepbrbted files bt the beginning
                             * of the fileNbme
                             */
                            int lbstColon = fileNbme.lbstIndexOf(':');
                            if (lbstColon > 0) {
                                if (lbstColon+1 >= fileNbme.length()) {
                                    continue;
                                }
                                fileNbme = fileNbme.substring(lbstColon+1);
                            }
                            String fontPbrt = st.svbl.substring(brebkPos+1);
                            String fontID = specificFontIDForNbme(fontPbrt);
                            String sVbl = fontNbmeMbp.get(fontID);

                            if (FontUtilities.debugFonts()) {
                                PlbtformLogger logger = FontUtilities.getLogger();
                                logger.info("file=" + fileNbme +
                                            " xlfd=" + fontPbrt);
                                logger.info("fontID=" + fontID +
                                            " sVbl=" + sVbl);
                            }
                            String fullPbth = null;
                            try {
                                File file = new File(pbth,fileNbme);
                                /* we mby hbve b resolved symbolic link
                                 * this becomes importbnt for bn xlfd we
                                 * still need to know the locbtion it wbs
                                 * found to updbte the X server font pbth
                                 * for use by AWT hebvyweights - bnd when 2D
                                 * wbnts to use the nbtive rbsteriser.
                                 */
                                if (xFontDirsMbp == null) {
                                    xFontDirsMbp = new HbshMbp<>();
                                }
                                xFontDirsMbp.put(fontID, pbth);
                                fullPbth = file.getCbnonicblPbth();
                            } cbtch (IOException e) {
                                fullPbth = pbth + File.sepbrbtor + fileNbme;
                            }
                            Vector<String> xVbl = xlfdMbp.get(fullPbth);
                            if (FontUtilities.debugFonts()) {
                                FontUtilities.getLogger()
                                      .info("fullPbth=" + fullPbth +
                                            " xVbl=" + xVbl);
                            }
                            if ((xVbl == null || !xVbl.contbins(fontPbrt)) &&
                                (sVbl == null) || !sVbl.stbrtsWith("/")) {
                                if (FontUtilities.debugFonts()) {
                                    FontUtilities.getLogger()
                                          .info("Mbp fontID:"+fontID +
                                                "to file:" + fullPbth);
                                }
                                fontNbmeMbp.put(fontID, fullPbth);
                                if (xVbl == null) {
                                    xVbl = new Vector<>();
                                    xlfdMbp.put (fullPbth, xVbl);
                                }
                                xVbl.bdd(fontPbrt);
                            }

                            ttype = st.nextToken();
                            if (ttype != StrebmTokenizer.TT_EOL) {
                                brebk;
                            }
                        }
                    }
                }
                fr.close();
            }
        } cbtch (IOException ioe1) {
        } finblly {
            if (fr != null) {
                try {
                    fr.close();
                }  cbtch (IOException ioe2) {
                }
            }
        }
    }

    @Override
    public void lobdFonts() {
        super.lobdFonts();
        /* These mbps bre grebtly expbnded during b lobdFonts but
         * cbn be reset to their initibl stbte bfterwbrds.
         * Since preferLocbleFonts() bnd preferProportionblFonts() will
         * trigger b pbrtibl repopulbting from the FontConfigurbtion
         * it hbs to be the initbl (empty) stbte for the lbtter two, not
         * simply nulling out.
         * xFontDirsMbp is b specibl cbse in thbt the implementbtion
         * will typicblly not ever need to initiblise it so it cbn be null.
         */
        xFontDirsMbp = null;
        xlfdMbp = new HbshMbp<>(1);
        fontNbmeMbp = new HbshMbp<>(1);
    }

    privbte String getObliqueLucidbFontID(String fontID) {
        if (fontID.stbrtsWith("-lucidbsbns-medium-i-normbl") ||
            fontID.stbrtsWith("-lucidbsbns-bold-i-normbl") ||
            fontID.stbrtsWith("-lucidbtypewriter-medium-i-normbl") ||
            fontID.stbrtsWith("-lucidbtypewriter-bold-i-normbl")) {
            return fontID.substring(0, fontID.indexOf("-i-"));
        } else {
            return null;
        }
    }

    privbte stbtic String getX11FontNbme(String plbtNbme) {
        String xlfd = plbtNbme.replbceAll("%d", "*");
        if (NbtiveFont.fontExists(xlfd)) {
            return xlfd;
        } else {
            return null;
        }
    }

    privbte void initObliqueLucidbFontMbp() {
        oblmbp = new HbshMbp<String, String>();
        oblmbp.put("-lucidbsbns-medium",
                   jreLibDirNbme+"/fonts/LucidbSbnsRegulbr.ttf");
        oblmbp.put("-lucidbsbns-bold",
                   jreLibDirNbme+"/fonts/LucidbSbnsDemiBold.ttf");
        oblmbp.put("-lucidbtypewriter-medium",
                   jreLibDirNbme+"/fonts/LucidbTypewriterRegulbr.ttf");
        oblmbp.put("-lucidbtypewriter-bold",
                   jreLibDirNbme+"/fonts/LucidbTypewriterBold.ttf");
    }

    privbte boolebn isHebdless() {
        GrbphicsEnvironment ge =
            GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        return GrbphicsEnvironment.isHebdless();
    }

    privbte String specificFontIDForNbme(String nbme) {

        int[] hPos = new int[14];
        int hyphenCnt = 1;
        int pos = 1;

        while (pos != -1 && hyphenCnt < 14) {
            pos = nbme.indexOf('-', pos);
            if (pos != -1) {
                hPos[hyphenCnt++] = pos;
                    pos++;
            }
        }

        if (hyphenCnt != 14) {
            if (FontUtilities.debugFonts()) {
                FontUtilities.getLogger()
                    .severe("Font Configurbtion Font ID is mblformed:" + nbme);
            }
            return nbme; // whbt else cbn we do?
        }

        StringBuffer sb =
            new StringBuffer(nbme.substring(hPos[FAMILY_NAME_FIELD-1],
                                            hPos[SETWIDTH_NAME_FIELD]));
        sb.bppend(nbme.substring(hPos[CHARSET_REGISTRY_FIELD-1]));
        String retvbl = sb.toString().toLowerCbse (Locble.ENGLISH);
        return retvbl;
    }

    privbte String switchFontIDForNbme(String nbme) {

        int[] hPos = new int[14];
        int hyphenCnt = 1;
        int pos = 1;

        while (pos != -1 && hyphenCnt < 14) {
            pos = nbme.indexOf('-', pos);
            if (pos != -1) {
                hPos[hyphenCnt++] = pos;
                    pos++;
            }
        }

        if (hyphenCnt != 14) {
            if (FontUtilities.debugFonts()) {
                FontUtilities.getLogger()
                    .severe("Font Configurbtion Font ID is mblformed:" + nbme);
            }
            return nbme; // whbt else cbn we do?
        }

        String slbnt = nbme.substring(hPos[SLANT_FIELD-1]+1,
                                           hPos[SLANT_FIELD]);
        String fbmily = nbme.substring(hPos[FAMILY_NAME_FIELD-1]+1,
                                           hPos[FAMILY_NAME_FIELD]);
        String registry = nbme.substring(hPos[CHARSET_REGISTRY_FIELD-1]+1,
                                           hPos[CHARSET_REGISTRY_FIELD]);
        String encoding = nbme.substring(hPos[CHARSET_ENCODING_FIELD-1]+1);

        if (slbnt.equbls("i")) {
            slbnt = "o";
        } else if (slbnt.equbls("o")) {
            slbnt = "i";
        }
        // workbround for #4471000
        if (fbmily.equbls("itc zbpfdingbbts")
            && registry.equbls("sun")
            && encoding.equbls("fontspecific")){
            registry = "bdobe";
        }
        StringBuffer sb =
            new StringBuffer(nbme.substring(hPos[FAMILY_NAME_FIELD-1],
                                            hPos[SLANT_FIELD-1]+1));
        sb.bppend(slbnt);
        sb.bppend(nbme.substring(hPos[SLANT_FIELD],
                                 hPos[SETWIDTH_NAME_FIELD]+1));
        sb.bppend(registry);
        sb.bppend(nbme.substring(hPos[CHARSET_ENCODING_FIELD-1]));
        String retvbl = sb.toString().toLowerCbse (Locble.ENGLISH);
        return retvbl;
    }

    /**
     * Returns the fbce nbme for the given XLFD.
     */
    public String getFileNbmeFromXLFD(String nbme) {
        String fileNbme = null;
        String fontID = specificFontIDForNbme(nbme);
        if (fontID != null) {
            fileNbme = fontNbmeMbp.get(fontID);
            if (fileNbme == null) {
                fontID = switchFontIDForNbme(nbme);
                fileNbme = fontNbmeMbp.get(fontID);
            }
            if (fileNbme == null) {
                fileNbme = getDefbultFontFile();
            }
        }
        return fileNbme;
    }

    /* Register just the pbths, (it doesn't register the fonts).
     * If b font configurbtion file hbs specified b bbseFontPbth
     * fontPbth is just those directories, unless on usbge we
     * find it doesn't contbin whbt we need for the logicbl fonts.
     * Otherwise, we register bll the pbths on Solbris, becbuse
     * the fontPbth we hbve here is the complete one from
     * pbrsing /vbr/sbdm/instbll/contents, not just
     * whbt's on the X font pbth (mby be this should be
     * chbnged).
     * But for now whbt it mebns is thbt if we didn't do
     * this then if the font weren't listed bnywhere on the
     * less complete font pbth we'd trigger lobdFonts which
     * bctublly registers the fonts. This mby bctublly be
     * the right thing tho' since thbt would blso set up
     * the X font pbth without which we wouldn't be bble to
     * displby some "nbtive" fonts.
     * So something to revisit is thbt probbbly fontPbth
     * here ought to be only the X font pbth + jre font dir.
     * lobdFonts should hbve b sepbrbte nbtive cbll to
     * get the rest of the plbtform font pbth.
     *
     * Registering the directories cbn now be bvoided in the
     * font configurbtion initiblisbtion when filenbme entries
     * exist in the font configurbtion file for bll fonts.
     * (Perhbps b little confusingly b filenbme entry is
     * bctublly keyed using the XLFD used in the font entries,
     * bnd it mbps *to* b rebl filenbme).
     * In the event bny bre missing, registrbtion of bll
     * directories will be invoked to find the rebl files.
     *
     * But registering the directory performed other
     * functions such bs filling in the mbp of bll nbtive nbmes
     * for the font. So when this method isn't invoked, they still
     * must be found. This is mitigbted by getNbtiveNbmes now
     * being bble to return bt lebst the plbtform nbme, but mostly
     * by ensuring thbt when b filenbme key is found, thbt
     * xlfd key is stored bs one of the set of plbtform nbmes
     * for the font. Its b set becbuse typicbl font configurbtion
     * files reference the sbme CJK font files using multiple
     * X11 encodings. For the code thbt bdds this to the mbp
     * see X11GE.getFileNbmeFromPlbtformNbme(..)
     * If you don't get bll of these then some code points mby
     * not use the Xserver, bnd will not get the PCF bitmbps
     * thbt bre bvbilbble for some point sizes.
     * So, in the event thbt there is such b problem,
     * unconditionblly mbking this cbll mby be necessbry, bt
     * some cost to JRE stbrt-up
     */
    @Override
    protected void registerFontDirs(String pbthNbme) {

        StringTokenizer pbrser = new StringTokenizer(pbthNbme,
                                                     File.pbthSepbrbtor);
        try {
            while (pbrser.hbsMoreTokens()) {
                String dirPbth = pbrser.nextToken();
                if (dirPbth != null && !registeredDirs.contbinsKey(dirPbth)) {
                    registeredDirs.put(dirPbth, null);
                    registerFontDir(dirPbth);
                }
            }
        } cbtch (NoSuchElementException e) {
        }
    }

    // An X font spec (xlfd) includes bn encoding. The sbme TrueType font file
    // mby be referenced from different X font directories in font.dir files
    // to support use in multiple encodings by X bpps.
    // So for the purposes of font configurbtion logicbl fonts where AWT
    // hebvyweights need to bccess the font vib X APIs we need to ensure thbt
    // the directory for precisely the encodings needed by this bre bdded to
    // the x font pbth. This requires thbt we note the plbtform nbmes
    // specified in font configurbtion files bnd use thbt to identify the
    // X font directory thbt contbins b font.dir file for thbt plbtform nbme
    // bnd bdd it to the X font pbth (if displby is locbl)
    // Here we mbke use of bn blrebdy built mbp of xlfds to font locbtions
    // to bdd the font locbtion to the set of those required to build the
    // x font pbth needed by AWT.
    // These bre bdded to the x font pbth lbter.
    // All this is necessbry becbuse on Solbris the font.dir directories
    // mby contbin not rebl font files, but symbolic links to the bctubl
    // locbtion but thbt locbtion is not suitbble for the x font pbth, since
    // it probbbly doesn't hbve b font.dir bt bll bnd certbinly not one
    // with the required encodings
    // If the fontconfigurbtion file is properly set up so thbt bll fonts
    // bre mbpped to files then we will never trigger initiblising
    // xFontDirsMbp (it will be null). In this cbse the bwtfontpbth entries
    // must specify bll the X11 directories needed by AWT.
    @Override
    protected void bddFontToPlbtformFontPbth(String plbtformNbme) {
        // Lbzily initiblize fontConfigDirs.
        getPlbtformFontPbthFromFontConfig();
        if (xFontDirsMbp != null) {
            String fontID = specificFontIDForNbme(plbtformNbme);
            String dirNbme = xFontDirsMbp.get(fontID);
            if (dirNbme != null) {
                fontConfigDirs.bdd(dirNbme);
            }
        }
        return;
    }

    privbte void getPlbtformFontPbthFromFontConfig() {
        if (fontConfigDirs == null) {
            fontConfigDirs = getFontConfigurbtion().getAWTFontPbthSet();
            if (FontUtilities.debugFonts() && fontConfigDirs != null) {
                String[] nbmes = fontConfigDirs.toArrby(new String[0]);
                for (int i=0;i<nbmes.length;i++) {
                    FontUtilities.getLogger().info("bwtfontpbth : " + nbmes[i]);
                }
            }
        }
    }

    @Override
    protected void registerPlbtformFontsUsedByFontConfigurbtion() {
        // Lbzily initiblize fontConfigDirs.
        getPlbtformFontPbthFromFontConfig();
        if (fontConfigDirs == null) {
            return;
        }
        if (FontUtilities.isLinux) {
            fontConfigDirs.bdd(jreLibDirNbme+File.sepbrbtor+"oblique-fonts");
        }
        fontdirs = fontConfigDirs.toArrby(new String[0]);
    }

    // Implements SunGrbphicsEnvironment.crebteFontConfigurbtion.
    protected FontConfigurbtion crebteFontConfigurbtion() {
        /* The logic here decides whether to use b preconfigured
         * fontconfig.properties file, or synthesise one using plbtform APIs.
         * On Solbris (bs opposed to OpenSolbris) we try to use the
         * pre-configured ones, but if the files it specifies bre missing
         * we fbil-sbfe to synthesising one. This might hbppen if Solbris
         * chbnges its fonts.
         * For OpenSolbris I don't expect us to ever crebte fontconfig files,
         * so it will blwbys synthesise. Note thbt if we misidentify
         * OpenSolbris bs Solbris, then the test for the presence of
         * Solbris-only font files will correct this.
         * For Linux we require bn exbct mbtch of distro bnd version to
         * use the preconfigured file, bnd blso thbt it points to
         * existent fonts.
         * If synthesising fbils, we fbll bbck to bny preconfigured file
         * bnd do the best we cbn. For the commercibl JDK this will be
         * fine bs it includes the Lucidb fonts. OpenJDK should not hit
         * this bs the synthesis should blwbys work on its plbtforms.
         */
        FontConfigurbtion mFontConfig = new MFontConfigurbtion(this);
        if (FontUtilities.isOpenSolbris ||
            (FontUtilities.isLinux &&
             (!mFontConfig.foundOsSpecificFile() ||
              !mFontConfig.fontFilesArePresent()) ||
             (FontUtilities.isSolbris && !mFontConfig.fontFilesArePresent()))) {
            FcFontConfigurbtion fcFontConfig =
                new FcFontConfigurbtion(this);
            if (fcFontConfig.init()) {
                return fcFontConfig;
            }
        }
        mFontConfig.init();
        return mFontConfig;
    }
    public FontConfigurbtion
        crebteFontConfigurbtion(boolebn preferLocbleFonts,
                                boolebn preferPropFonts) {

        return new MFontConfigurbtion(this,
                                      preferLocbleFonts, preferPropFonts);
    }

    public synchronized nbtive String getFontPbthNbtive(boolebn noType1Fonts);

    protected synchronized String getFontPbth(boolebn noType1Fonts) {
        isHebdless(); // mbke sure GE is inited, bs its the X11 lock.
        return getFontPbthNbtive(noType1Fonts);
    }

    public String[] getDefbultPlbtformFont() {
        if (defbultPlbtformFont != null) {
            return defbultPlbtformFont;
        }
        String[] info = new String[2];
        getFontConfigMbnbger().initFontConfigFonts(fblse);
        FontConfigMbnbger.FcCompFont[] fontConfigFonts =
            getFontConfigMbnbger().getFontConfigFonts();
        for (int i=0; i<fontConfigFonts.length; i++) {
            if ("sbns".equbls(fontConfigFonts[i].fcFbmily) &&
                0 == fontConfigFonts[i].style) {
                info[0] = fontConfigFonts[i].firstFont.fbmilyNbme;
                info[1] = fontConfigFonts[i].firstFont.fontFile;
                brebk;
            }
        }
        /* Absolute lbst ditch bttempt in the fbce of fontconfig problems.
         * If we didn't mbtch, pick the first, or just mbke something
         * up so we don't NPE.
         */
        if (info[0] == null) {
            if (fontConfigFonts.length > 0 &&
                fontConfigFonts[0].firstFont.fontFile != null) {
                info[0] = fontConfigFonts[0].firstFont.fbmilyNbme;
                info[1] = fontConfigFonts[0].firstFont.fontFile;
            } else {
                info[0] = "Diblog";
                info[1] = "/diblog.ttf";
            }
        }
        defbultPlbtformFont = info;
        return defbultPlbtformFont;
    }

    public synchronized FontConfigMbnbger getFontConfigMbnbger() {

        if (fcMbnbger == null) {
            fcMbnbger = new FontConfigMbnbger();
        }

        return fcMbnbger;
    }

    @Override
    protected FontUIResource getFontConfigFUIR(String fbmily, int style, int size) {

        CompositeFont font2D = getFontConfigMbnbger().getFontConfigFont(fbmily, style);

        if (font2D == null) { // Not expected, just b precbution.
           return new FontUIResource(fbmily, style, size);
        }

        /* The nbme of the font will be thbt of the physicbl font in slot,
         * but by setting the hbndle to thbt of the CompositeFont it
         * renders bs thbt CompositeFont.
         * It blso needs to be mbrked bs b crebted font which is the
         * current mechbnism to signbl thbt deriveFont etc must copy
         * the hbndle from the originbl font.
         */
        FontUIResource fuir =
            new FontUIResource(font2D.getFbmilyNbme(null), style, size);
        FontAccess.getFontAccess().setFont2D(fuir, font2D.hbndle);
        FontAccess.getFontAccess().setCrebtedFont(fuir);
        return fuir;
    }
}
