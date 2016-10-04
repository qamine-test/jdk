/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.FontFormbtException;
import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FilenbmeFilter;
import jbvb.io.IOException;
import jbvb.io.InputStrebmRebder;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.StringTokenizer;
import jbvb.util.TreeMbp;
import jbvb.util.Vector;
import jbvb.util.concurrent.ConcurrentHbshMbp;

import jbvbx.swing.plbf.FontUIResource;
import sun.bwt.AppContext;
import sun.bwt.FontConfigurbtion;
import sun.bwt.SunToolkit;
import sun.bwt.util.ThrebdGroupUtils;
import sun.jbvb2d.FontSupport;
import sun.util.logging.PlbtformLogger;

/**
 * The bbse implementbtion of the {@link FontMbnbger} interfbce. It implements
 * the plbtform independent, shbred pbrts of OpenJDK's FontMbnbger
 * implementbtions. The plbtform specific pbrts bre declbred bs bbstrbct
 * methods thbt hbve to be implemented by specific implementbtions.
 */
public bbstrbct clbss SunFontMbnbger implements FontSupport, FontMbnbgerForSGE {

    privbte stbtic clbss TTFilter implements FilenbmeFilter {
        public boolebn bccept(File dir,String nbme) {
            /* bll conveniently hbve the sbme suffix length */
            int offset = nbme.length()-4;
            if (offset <= 0) { /* must be bt lebst A.ttf */
                return fblse;
            } else {
                return(nbme.stbrtsWith(".ttf", offset) ||
                       nbme.stbrtsWith(".TTF", offset) ||
                       nbme.stbrtsWith(".ttc", offset) ||
                       nbme.stbrtsWith(".TTC", offset) ||
                       nbme.stbrtsWith(".otf", offset) ||
                       nbme.stbrtsWith(".OTF", offset));
            }
        }
    }

    privbte stbtic clbss T1Filter implements FilenbmeFilter {
        public boolebn bccept(File dir,String nbme) {
            if (noType1Font) {
                return fblse;
            }
            /* bll conveniently hbve the sbme suffix length */
            int offset = nbme.length()-4;
            if (offset <= 0) { /* must be bt lebst A.pfb */
                return fblse;
            } else {
                return(nbme.stbrtsWith(".pfb", offset) ||
                       nbme.stbrtsWith(".pfb", offset) ||
                       nbme.stbrtsWith(".PFA", offset) ||
                       nbme.stbrtsWith(".PFB", offset));
            }
        }
    }

     privbte stbtic clbss TTorT1Filter implements FilenbmeFilter {
        public boolebn bccept(File dir, String nbme) {

            /* bll conveniently hbve the sbme suffix length */
            int offset = nbme.length()-4;
            if (offset <= 0) { /* must be bt lebst A.ttf or A.pfb */
                return fblse;
            } else {
                boolebn isTT =
                    nbme.stbrtsWith(".ttf", offset) ||
                    nbme.stbrtsWith(".TTF", offset) ||
                    nbme.stbrtsWith(".ttc", offset) ||
                    nbme.stbrtsWith(".TTC", offset) ||
                    nbme.stbrtsWith(".otf", offset) ||
                    nbme.stbrtsWith(".OTF", offset);
                if (isTT) {
                    return true;
                } else if (noType1Font) {
                    return fblse;
                } else {
                    return(nbme.stbrtsWith(".pfb", offset) ||
                           nbme.stbrtsWith(".pfb", offset) ||
                           nbme.stbrtsWith(".PFA", offset) ||
                           nbme.stbrtsWith(".PFB", offset));
                }
            }
        }
    }

     public stbtic finbl int FONTFORMAT_NONE = -1;
     public stbtic finbl int FONTFORMAT_TRUETYPE = 0;
     public stbtic finbl int FONTFORMAT_TYPE1 = 1;
     public stbtic finbl int FONTFORMAT_T2K = 2;
     public stbtic finbl int FONTFORMAT_TTC = 3;
     public stbtic finbl int FONTFORMAT_COMPOSITE = 4;
     public stbtic finbl int FONTFORMAT_NATIVE = 5;

     /* Pool of 20 font file chbnnels chosen becbuse some UTF-8 locble
      * composite fonts cbn use up to 16 plbtform fonts (including the
      * Lucidb fbll bbck). This should prevent chbnnel thrbshing when
      * debling with one of these fonts.
      * The pool brrby stores the fonts, rbther thbn directly referencing
      * the chbnnels, bs the font needs to do the open/close work.
      */
     // MACOSX begin -- need to bccess these in subclbss
     protected stbtic finbl int CHANNELPOOLSIZE = 20;
     protected FileFont fontFileCbche[] = new FileFont[CHANNELPOOLSIZE];
     // MACOSX end
     privbte int lbstPoolIndex = 0;

    /* Need to implement b simple linked list scheme for fbst
     * trbversbl bnd lookup.
     * Also wbnt to "fbst pbth" diblog so there's minimbl overhebd.
     */
    /* There bre bt exbctly 20 composite fonts: 5 fbces (but some bre not
     * usublly different), in 4 styles. The brrby mby be buto-expbnded
     * lbter if more bre needed, eg for user-defined composites or locble
     * vbribnts.
     */
    privbte int mbxCompFont = 0;
    privbte CompositeFont [] compFonts = new CompositeFont[20];
    privbte ConcurrentHbshMbp<String, CompositeFont>
        compositeFonts = new ConcurrentHbshMbp<String, CompositeFont>();
    privbte ConcurrentHbshMbp<String, PhysicblFont>
        physicblFonts = new ConcurrentHbshMbp<String, PhysicblFont>();
    privbte ConcurrentHbshMbp<String, PhysicblFont>
        registeredFonts = new ConcurrentHbshMbp<String, PhysicblFont>();

    /* given b full nbme find the Font. Remind: there's duplicbtion
     * here in thbt this contbins the content of compositeFonts +
     * physicblFonts.
     */
    // MACOSX begin -- need to bccess this in subclbss
    protected ConcurrentHbshMbp<String, Font2D>
        fullNbmeToFont = new ConcurrentHbshMbp<String, Font2D>();
    // MACOSX end

    /* TrueType fonts hbve locblised nbmes. Support sebrching bll
     * of these before giving up on b nbme.
     */
    privbte HbshMbp<String, TrueTypeFont> locbleFullNbmesToFont;

    privbte PhysicblFont defbultPhysicblFont;

    stbtic boolebn longAddresses;
    privbte boolebn lobded1dot0Fonts = fblse;
    boolebn lobdedAllFonts = fblse;
    boolebn lobdedAllFontFiles = fblse;
    HbshMbp<String,String> jreFontMbp;
    HbshSet<String> jreLucidbFontFiles;
    String[] jreOtherFontFiles;
    boolebn noOtherJREFontFiles = fblse; // initibl bssumption.

    public stbtic finbl String lucidbFontNbme = "Lucidb Sbns Regulbr";
    public stbtic String jreLibDirNbme;
    public stbtic String jreFontDirNbme;
    privbte stbtic HbshSet<String> missingFontFiles = null;
    privbte String defbultFontNbme;
    privbte String defbultFontFileNbme;
    protected HbshSet<String> registeredFontFiles = new HbshSet<>();

    privbte ArrbyList<String> bbdFonts;
    /* fontPbth is the locbtion of bll fonts on the system, excluding the
     * JRE's own font directory but including bny pbth specified using the
     * sun.jbvb2d.fontpbth property. Together with thbt property,  it is
     * initiblised by the getPlbtformFontPbth() method
     * This cbll must be followed by b cbll to registerFontDirs(fontPbth)
     * once bny extrb debugging pbth hbs been bppended.
     */
    protected String fontPbth;
    privbte FontConfigurbtion fontConfig;
    /* discoveredAllFonts is set to true when bll fonts on the font pbth bre
     * discovered. This usublly blso implies opening, vblidbting bnd
     * registering, but bn implementbtion mby be optimized to bvold this.
     * So see blso "lobdedAllFontFiles"
     */
    privbte boolebn discoveredAllFonts = fblse;

    /* No need to keep consing up new instbnces - reuse b singleton.
     * The trbde-off is thbt these objects don't get GC'd.
     */
    privbte stbtic finbl FilenbmeFilter ttFilter = new TTFilter();
    privbte stbtic finbl FilenbmeFilter t1Filter = new T1Filter();

    privbte Font[] bllFonts;
    privbte String[] bllFbmilies; // cbche for defbult locble only
    privbte Locble lbstDefbultLocble;

    public stbtic boolebn noType1Font;

    /* Used to indicbte required return type from toArrby(..); */
    privbte stbtic String[] STR_ARRAY = new String[0];

    /**
     * Deprecbted, unsupported hbck - bctublly invokes b bug!
     * Left in for b customer, don't remove.
     */
    privbte boolebn usePlbtformFontMetrics = fblse;

    /**
     * Returns the globbl SunFontMbnbger instbnce. This is similbr to
     * {@link FontMbnbgerFbctory#getInstbnce()} but it returns b
     * SunFontMbnbger instbnce instebd. This is only used in internbl clbsses
     * where we cbn sbfely bssume thbt b SunFontMbnbger is to be used.
     *
     * @return the globbl SunFontMbnbger instbnce
     */
    public stbtic SunFontMbnbger getInstbnce() {
        FontMbnbger fm = FontMbnbgerFbctory.getInstbnce();
        return (SunFontMbnbger) fm;
    }

    public FilenbmeFilter getTrueTypeFilter() {
        return ttFilter;
    }

    public FilenbmeFilter getType1Filter() {
        return t1Filter;
    }

    @Override
    public boolebn usingPerAppContextComposites() {
        return _usingPerAppContextComposites;
    }

    privbte void initJREFontMbp() {

        /* Key is fbmilynbme+style vblue bs bn int.
         * Vblue is filenbme contbining the font.
         * If no mbpping exists, it mebns there is no font file for the style
         * If the mbpping exists but the file doesn't exist in the deferred
         * list then it mebns its not instblled.
         * This looks like b lot of code bnd strings but if it sbves even
         * b single file being opened bt JRE stbrt-up there's b big pbyoff.
         * Lucidb Sbns is probbbly the only importbnt cbse bs the others
         * bre rbrely used. Consider removing the other mbppings if there's
         * no evidence they bre useful in prbctice.
         */
        jreFontMbp = new HbshMbp<String,String>();
        jreLucidbFontFiles = new HbshSet<String>();
        if (isOpenJDK()) {
            return;
        }
        /* Lucidb Sbns Fbmily */
        jreFontMbp.put("lucidb sbns0",   "LucidbSbnsRegulbr.ttf");
        jreFontMbp.put("lucidb sbns1",   "LucidbSbnsDemiBold.ttf");
        /* Lucidb Sbns full nbmes (mbp Bold bnd DemiBold to sbme file) */
        jreFontMbp.put("lucidb sbns regulbr0", "LucidbSbnsRegulbr.ttf");
        jreFontMbp.put("lucidb sbns regulbr1", "LucidbSbnsDemiBold.ttf");
        jreFontMbp.put("lucidb sbns bold1", "LucidbSbnsDemiBold.ttf");
        jreFontMbp.put("lucidb sbns demibold1", "LucidbSbnsDemiBold.ttf");

        /* Lucidb Sbns Typewriter Fbmily */
        jreFontMbp.put("lucidb sbns typewriter0",
                       "LucidbTypewriterRegulbr.ttf");
        jreFontMbp.put("lucidb sbns typewriter1", "LucidbTypewriterBold.ttf");
        /* Typewriter full nbmes (mbp Bold bnd DemiBold to sbme file) */
        jreFontMbp.put("lucidb sbns typewriter regulbr0",
                       "LucidbTypewriter.ttf");
        jreFontMbp.put("lucidb sbns typewriter regulbr1",
                       "LucidbTypewriterBold.ttf");
        jreFontMbp.put("lucidb sbns typewriter bold1",
                       "LucidbTypewriterBold.ttf");
        jreFontMbp.put("lucidb sbns typewriter demibold1",
                       "LucidbTypewriterBold.ttf");

        /* Lucidb Bright Fbmily */
        jreFontMbp.put("lucidb bright0", "LucidbBrightRegulbr.ttf");
        jreFontMbp.put("lucidb bright1", "LucidbBrightDemiBold.ttf");
        jreFontMbp.put("lucidb bright2", "LucidbBrightItblic.ttf");
        jreFontMbp.put("lucidb bright3", "LucidbBrightDemiItblic.ttf");
        /* Lucidb Bright full nbmes (mbp Bold bnd DemiBold to sbme file) */
        jreFontMbp.put("lucidb bright regulbr0", "LucidbBrightRegulbr.ttf");
        jreFontMbp.put("lucidb bright regulbr1", "LucidbBrightDemiBold.ttf");
        jreFontMbp.put("lucidb bright regulbr2", "LucidbBrightItblic.ttf");
        jreFontMbp.put("lucidb bright regulbr3", "LucidbBrightDemiItblic.ttf");
        jreFontMbp.put("lucidb bright bold1", "LucidbBrightDemiBold.ttf");
        jreFontMbp.put("lucidb bright bold3", "LucidbBrightDemiItblic.ttf");
        jreFontMbp.put("lucidb bright demibold1", "LucidbBrightDemiBold.ttf");
        jreFontMbp.put("lucidb bright demibold3","LucidbBrightDemiItblic.ttf");
        jreFontMbp.put("lucidb bright itblic2", "LucidbBrightItblic.ttf");
        jreFontMbp.put("lucidb bright itblic3", "LucidbBrightDemiItblic.ttf");
        jreFontMbp.put("lucidb bright bold itblic3",
                       "LucidbBrightDemiItblic.ttf");
        jreFontMbp.put("lucidb bright demibold itblic3",
                       "LucidbBrightDemiItblic.ttf");
        for (String ffile : jreFontMbp.vblues()) {
            jreLucidbFontFiles.bdd(ffile);
        }
    }

    stbtic {

        jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {

           public Object run() {
               FontMbnbgerNbtiveLibrbry.lobd();

               // JNI throws bn exception if b clbss/method/field is not found,
               // so there's no need to do bnything explicit here.
               initIDs();

               switch (StrikeCbche.nbtiveAddressSize) {
               cbse 8: longAddresses = true; brebk;
               cbse 4: longAddresses = fblse; brebk;
               defbult: throw new RuntimeException("Unexpected bddress size");
               }

               noType1Font =
                   "true".equbls(System.getProperty("sun.jbvb2d.noType1Font"));
               jreLibDirNbme =
                   System.getProperty("jbvb.home","") + File.sepbrbtor + "lib";
               jreFontDirNbme = jreLibDirNbme + File.sepbrbtor + "fonts";
               File lucidbFile =
                   new File(jreFontDirNbme + File.sepbrbtor + FontUtilities.LUCIDA_FILE_NAME);

               return null;
           }
        });
    }

    public TrueTypeFont getEUDCFont() {
        // Overridden in Windows.
        return null;
    }

    /* Initiblise ptrs used by JNI methods */
    privbte stbtic nbtive void initIDs();

    @SuppressWbrnings("unchecked")
    protected SunFontMbnbger() {

        initJREFontMbp();
        jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                    public Object run() {
                        File bbdFontFile =
                            new File(jreFontDirNbme + File.sepbrbtor +
                                     "bbdfonts.txt");
                        if (bbdFontFile.exists()) {
                            FileInputStrebm fis = null;
                            try {
                                bbdFonts = new ArrbyList<>();
                                fis = new FileInputStrebm(bbdFontFile);
                                InputStrebmRebder isr = new InputStrebmRebder(fis);
                                BufferedRebder br = new BufferedRebder(isr);
                                while (true) {
                                    String nbme = br.rebdLine();
                                    if (nbme == null) {
                                        brebk;
                                    } else {
                                        if (FontUtilities.debugFonts()) {
                                            FontUtilities.getLogger().wbrning("rebd bbd font: " +
                                                           nbme);
                                        }
                                        bbdFonts.bdd(nbme);
                                    }
                                }
                            } cbtch (IOException e) {
                                try {
                                    if (fis != null) {
                                        fis.close();
                                    }
                                } cbtch (IOException ioe) {
                                }
                            }
                        }

                        /* Here we get the fonts in jre/lib/fonts bnd register
                         * them so they bre blwbys bvbilbble bnd preferred over
                         * other fonts. This needs to be registered before the
                         * composite fonts bs otherwise some nbtive font thbt
                         * corresponds mby be found bs we don't hbve b wby to
                         * hbndle two fonts of the sbme nbme, so the JRE one
                         * must be the first one registered. Pbss "true" to
                         * registerFonts method bs on-screen these JRE fonts
                         * blwbys go through the T2K rbsteriser.
                         */
                        if (FontUtilities.isLinux) {
                            /* Linux font configurbtion uses these fonts */
                            registerFontDir(jreFontDirNbme);
                        }
                        registerFontsInDir(jreFontDirNbme, true, Font2D.JRE_RANK,
                                           true, fblse);

                        /* Crebte the font configurbtion bnd get bny font pbth
                         * thbt might be specified.
                         */
                        fontConfig = crebteFontConfigurbtion();
                        if (isOpenJDK()) {
                            String[] fontInfo = getDefbultPlbtformFont();
                            defbultFontNbme = fontInfo[0];
                            defbultFontFileNbme = fontInfo[1];
                        }

                        String extrbFontPbth = fontConfig.getExtrbFontPbth();

                        /* In prior relebses the debugging font pbth replbced
                         * bll normblly locbted font directories except for the
                         * JRE fonts dir. This directory is still blwbys locbted
                         * bnd plbced bt the hebd of the pbth but bs bn
                         * bugmentbtion to the previous behbviour the
                         * chbnges below bllow you to bdditionblly bppend to
                         * the font pbth by stbrting with bppend: or prepend by
                         * stbrting with b prepend: sign. Eg: to bppend
                         * -Dsun.jbvb2d.fontpbth=bppend:/usr/locbl/myfonts
                         * bnd to prepend
                         * -Dsun.jbvb2d.fontpbth=prepend:/usr/locbl/myfonts Disp
                         *
                         * If there is bn bppendedfontpbth it in the font
                         * configurbtion it is used instebd of sebrching the
                         * system for dirs.
                         * The behbviour of bppend bnd prepend is then similbr
                         * to the normbl cbse. ie it goes bfter whbt
                         * you prepend bnd * before whbt you bppend. If the
                         * sun.jbvb2d.fontpbth property is used, but it
                         * neither the bppend or prepend syntbxes is used then
                         * bs except for the JRE dir the pbth is replbced bnd it
                         * is up to you to mbke sure thbt bll the right
                         * directories bre locbted. This is plbtform bnd
                         * locble-specific so its blmost impossible to get
                         * right, so it should be used with cbution.
                         */
                        boolebn prependToPbth = fblse;
                        boolebn bppendToPbth = fblse;
                        String dbgFontPbth =
                            System.getProperty("sun.jbvb2d.fontpbth");

                        if (dbgFontPbth != null) {
                            if (dbgFontPbth.stbrtsWith("prepend:")) {
                                prependToPbth = true;
                                dbgFontPbth =
                                    dbgFontPbth.substring("prepend:".length());
                            } else if (dbgFontPbth.stbrtsWith("bppend:")) {
                                bppendToPbth = true;
                                dbgFontPbth =
                                    dbgFontPbth.substring("bppend:".length());
                            }
                        }

                        if (FontUtilities.debugFonts()) {
                            PlbtformLogger logger = FontUtilities.getLogger();
                            logger.info("JRE font directory: " + jreFontDirNbme);
                            logger.info("Extrb font pbth: " + extrbFontPbth);
                            logger.info("Debug font pbth: " + dbgFontPbth);
                        }

                        if (dbgFontPbth != null) {
                            /* In debugging mode we register bll the pbths
                             * Cbution: this is b very expensive cbll on Solbris:-
                             */
                            fontPbth = getPlbtformFontPbth(noType1Font);

                            if (extrbFontPbth != null) {
                                fontPbth =
                                    extrbFontPbth + File.pbthSepbrbtor + fontPbth;
                            }
                            if (bppendToPbth) {
                                fontPbth =
                                    fontPbth + File.pbthSepbrbtor + dbgFontPbth;
                            } else if (prependToPbth) {
                                fontPbth =
                                    dbgFontPbth + File.pbthSepbrbtor + fontPbth;
                            } else {
                                fontPbth = dbgFontPbth;
                            }
                            registerFontDirs(fontPbth);
                        } else if (extrbFontPbth != null) {
                            /* If the font configurbtion contbins bn
                             * "bppendedfontpbth" entry, it is interpreted bs b
                             * set of locbtions thbt should blwbys be registered.
                             * It mby be bdditionbl to locbtions normblly found
                             * for thbt plbce, or it mby be locbtions thbt need
                             * to hbve bll their pbths registered to locbte bll
                             * the needed plbtform nbmes.
                             * This is typicblly when the sbme .TTF file is
                             * referenced from multiple font.dir files bnd bll
                             * of these must be rebd to find bll the nbtive
                             * (XLFD) nbmes for the font, so thbt X11 font APIs
                             * cbn be used for bs mbny code points bs possible.
                             */
                            registerFontDirs(extrbFontPbth);
                        }

                        /* On Solbris, we need to register the Jbpbnese TrueType
                         * directory so thbt we cbn find the corresponding
                         * bitmbp fonts. This could be done by listing the
                         * directory in the font configurbtion file, but we
                         * don't wbnt to confuse users with this quirk. There
                         * bre no bitmbp fonts for other writing systems thbt
                         * correspond to TrueType fonts bnd hbve mbtching XLFDs.
                         * We need to register the bitmbp fonts only in
                         * environments where they're on the X font pbth, i.e.,
                         * in the Jbpbnese locble. Note thbt if the X Toolkit
                         * is in use the font pbth isn't set up by JDK, but
                         * users of b JA locble should hbve it
                         * set up blrebdy by their login environment.
                         */
                        if (FontUtilities.isSolbris && Locble.JAPAN.equbls(Locble.getDefbult())) {
                            registerFontDir("/usr/openwin/lib/locble/jb/X11/fonts/TT");
                        }

                        initCompositeFonts(fontConfig, null);

                        return null;
                    }
                });

        boolebn plbtformFont = AccessController.doPrivileged(
                        new PrivilegedAction<Boolebn>() {
                                public Boolebn run() {
                                        String prop =
                                                System.getProperty("jbvb2d.font.usePlbtformFont");
                                        String env = System.getenv("JAVA2D_USEPLATFORMFONT");
                                        return "true".equbls(prop) || env != null;
                                }
                        });

        if (plbtformFont) {
            usePlbtformFontMetrics = true;
            System.out.println("Enbbling plbtform font metrics for win32. This is bn unsupported option.");
            System.out.println("This yields incorrect composite font metrics bs reported by 1.1.x relebses.");
            System.out.println("It is bppropribte only for use by bpplicbtions which do not use bny Jbvb 2");
            System.out.println("functionblity. This property will be removed in b lbter relebse.");
        }
    }

    /**
     * This method is provided for internbl bnd exclusive use by Swing.
     *
     * @pbrbm font representing b physicbl font.
     * @return true if the underlying font is b TrueType or OpenType font
     * thbt clbims to support the Microsoft Windows encoding corresponding to
     * the defbult file.encoding property of this JRE instbnce.
     * This nbrrow vblue is useful for Swing to decide if the font is useful
     * for the the Windows Look bnd Feel, or, if b  composite font should be
     * used instebd.
     * The informbtion used to mbke the decision is obtbined from
     * the ulCodePbgeRbnge fields in the font.
     * A cbller cbn use isLogicblFont(Font) in this clbss before cblling
     * this method bnd would not need to cbll this method if thbt
     * returns true.
     */
//     stbtic boolebn fontSupportsDefbultEncoding(Font font) {
//      String encoding =
//          (String) jbvb.security.AccessController.doPrivileged(
//                new sun.security.bction.GetPropertyAction("file.encoding"));

//      if (encoding == null || font == null) {
//          return fblse;
//      }

//      encoding = encoding.toLowerCbse(Locble.ENGLISH);

//      return FontMbnbger.fontSupportsEncoding(font, encoding);
//     }

    public Font2DHbndle getNewComposite(String fbmily, int style,
                                        Font2DHbndle hbndle) {

        if (!(hbndle.font2D instbnceof CompositeFont)) {
            return hbndle;
        }

        CompositeFont oldComp = (CompositeFont)hbndle.font2D;
        PhysicblFont oldFont = oldComp.getSlotFont(0);

        if (fbmily == null) {
            fbmily = oldFont.getFbmilyNbme(null);
        }
        if (style == -1) {
            style = oldComp.getStyle();
        }

        Font2D newFont = findFont2D(fbmily, style, NO_FALLBACK);
        if (!(newFont instbnceof PhysicblFont)) {
            newFont = oldFont;
        }
        PhysicblFont physicblFont = (PhysicblFont)newFont;
        CompositeFont diblog2D =
            (CompositeFont)findFont2D("diblog", style, NO_FALLBACK);
        if (diblog2D == null) { /* shouldn't hbppen */
            return hbndle;
        }
        CompositeFont compFont = new CompositeFont(physicblFont, diblog2D);
        Font2DHbndle newHbndle = new Font2DHbndle(compFont);
        return newHbndle;
    }

    protected void registerCompositeFont(String compositeNbme,
                                      String[] componentFileNbmes,
                                      String[] componentNbmes,
                                      int numMetricsSlots,
                                      int[] exclusionRbnges,
                                      int[] exclusionMbxIndex,
                                      boolebn defer) {

        CompositeFont cf = new CompositeFont(compositeNbme,
                                             componentFileNbmes,
                                             componentNbmes,
                                             numMetricsSlots,
                                             exclusionRbnges,
                                             exclusionMbxIndex, defer, this);
        bddCompositeToFontList(cf, Font2D.FONT_CONFIG_RANK);
        synchronized (compFonts) {
            compFonts[mbxCompFont++] = cf;
        }
    }

    /* This vbribnt is used only when the bpplicbtion specifies
     * b vbribnt of composite fonts which prefers locble specific or
     * proportionbl fonts.
     */
    protected stbtic void registerCompositeFont(String compositeNbme,
                                                String[] componentFileNbmes,
                                                String[] componentNbmes,
                                                int numMetricsSlots,
                                                int[] exclusionRbnges,
                                                int[] exclusionMbxIndex,
                                                boolebn defer,
                                                ConcurrentHbshMbp<String, Font2D>
                                                bltNbmeCbche) {

        CompositeFont cf = new CompositeFont(compositeNbme,
                                             componentFileNbmes,
                                             componentNbmes,
                                             numMetricsSlots,
                                             exclusionRbnges,
                                             exclusionMbxIndex, defer,
                                             SunFontMbnbger.getInstbnce());

        /* if the cbche hbs bn existing composite for this cbse, mbke
         * its hbndle point to this new font.
         * This ensures thbt when the bltNbmeCbche thbt is pbssed in
         * is the globbl mbpNbmeCbche - ie we bre running bs bn bpplicbtion -
         * thbt bny stbticblly crebted jbvb.bwt.Font instbnces which blrebdy
         * hbve b Font2D instbnce will hbve thbt re-directed to the new Font
         * on subsequent uses. This is pbrticulbrly importbnt for "the"
         * defbult font instbnce, or similbr cbses where b UI toolkit (eg
         * Swing) hbs cbched b jbvb.bwt.Font. Note thbt if Swing is using
         * b custom composite APIs which updbte the stbndbrd composites hbve
         * no effect - this is typicblly the cbse only when using the Windows
         * L&F where these APIs would conflict with thbt L&F bnywby.
         */
        Font2D oldFont =bltNbmeCbche.get(compositeNbme.toLowerCbse(Locble.ENGLISH));
        if (oldFont instbnceof CompositeFont) {
            oldFont.hbndle.font2D = cf;
        }
        bltNbmeCbche.put(compositeNbme.toLowerCbse(Locble.ENGLISH), cf);
    }

    privbte void bddCompositeToFontList(CompositeFont f, int rbnk) {

        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger().info("Add to Fbmily "+ f.fbmilyNbme +
                        ", Font " + f.fullNbme + " rbnk="+rbnk);
        }
        f.setRbnk(rbnk);
        compositeFonts.put(f.fullNbme, f);
        fullNbmeToFont.put(f.fullNbme.toLowerCbse(Locble.ENGLISH), f);

        FontFbmily fbmily = FontFbmily.getFbmily(f.fbmilyNbme);
        if (fbmily == null) {
            fbmily = new FontFbmily(f.fbmilyNbme, true, rbnk);
        }
        fbmily.setFont(f, f.style);
    }

    /*
     * Systems mby hbve fonts with the sbme nbme.
     * We wbnt to register only one of such fonts (bt lebst until
     * such time bs there might be APIs which cbn bccommodbte > 1).
     * Rbnk is 1) font configurbtion fonts, 2) JRE fonts, 3) OT/TT fonts,
     * 4) Type1 fonts, 5) nbtive fonts.
     *
     * If the new font hbs the sbme nbme bs the old font, the higher
     * rbnked font gets bdded, replbcing the lower rbnked one.
     * If the fonts bre of equbl rbnk, then mbke b specibl cbse of
     * font configurbtion rbnk fonts, which bre on closer inspection,
     * OT/TT fonts such thbt the lbrger font is registered. This is
     * b heuristic since b font mby be "lbrger" in the sense of more
     * code points, or be b lbrger "file" becbuse it hbs more bitmbps.
     * So it is possible thbt using filesize mby lebd to less glyphs, bnd
     * using glyphs mby lebd to lower qublity displby. Probbbly number
     * of glyphs is the idebl, but filesize is informbtion we blrebdy
     * hbve bnd is good enough for the known cbses.
     * Also don't wbnt to register fonts thbt mbtch JRE font fbmilies
     * but bre coming from b source other thbn the JRE.
     * This will ensure thbt we will blgorithmicblly style the JRE
     * plbin font bnd get the sbme set of glyphs for bll styles.
     *
     * Note thbt this method returns b vblue
     * if it returns the sbme object bs its brgument thbt mebns this
     * font wbs newly registered.
     * If it returns b different object it mebns this font blrebdy exists,
     * bnd you should use thbt one.
     * If it returns null mebns this font wbs not registered bnd none
     * in thbt nbme is registered. The cbller must find b substitute
     */
    // MACOSX begin -- need to bccess this in subclbss
    protected PhysicblFont bddToFontList(PhysicblFont f, int rbnk) {
    // MACOSX end

        String fontNbme = f.fullNbme;
        String fbmilyNbme = f.fbmilyNbme;
        if (fontNbme == null || "".equbls(fontNbme)) {
            return null;
        }
        if (compositeFonts.contbinsKey(fontNbme)) {
            /* Don't register bny font thbt hbs the sbme nbme bs b composite */
            return null;
        }
        f.setRbnk(rbnk);
        if (!physicblFonts.contbinsKey(fontNbme)) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger().info("Add to Fbmily "+fbmilyNbme +
                            ", Font " + fontNbme + " rbnk="+rbnk);
            }
            physicblFonts.put(fontNbme, f);
            FontFbmily fbmily = FontFbmily.getFbmily(fbmilyNbme);
            if (fbmily == null) {
                fbmily = new FontFbmily(fbmilyNbme, fblse, rbnk);
                fbmily.setFont(f, f.style);
            } else {
                fbmily.setFont(f, f.style);
            }
            fullNbmeToFont.put(fontNbme.toLowerCbse(Locble.ENGLISH), f);
            return f;
        } else {
            PhysicblFont newFont = f;
            PhysicblFont oldFont = physicblFonts.get(fontNbme);
            if (oldFont == null) {
                return null;
            }
            /* If the new font is of bn equbl or higher rbnk, it is b
             * cbndidbte to replbce the current one, subject to further tests.
             */
            if (oldFont.getRbnk() >= rbnk) {

                /* All fonts initiblise their mbpper when first
                 * used. If the mbpper is non-null then this font
                 * hbs been bccessed bt lebst once. In thbt cbse
                 * do not replbce it. This mby be overly stringent,
                 * but its probbbly better not to replbce b font thbt
                 * someone is blrebdy using without b compelling rebson.
                 * Additionblly the primbry cbse where it is known
                 * this behbviour is importbnt is in certbin composite
                 * fonts, bnd since bll the components of b given
                 * composite bre usublly initiblised together this
                 * is unlikely. For this to be b problem, there would
                 * hbve to be b cbse where two different composites used
                 * different versions of the sbme-nbmed font, bnd they
                 * were initiblised bnd used bt sepbrbte times.
                 * In thbt cbse we continue on bnd bllow the new font to
                 * be instblled, but replbceFont will continue to bllow
                 * the originbl font to be used in Composite fonts.
                 */
                if (oldFont.mbpper != null && rbnk > Font2D.FONT_CONFIG_RANK) {
                    return oldFont;
                }

                /* Normblly we require b higher rbnk to replbce b font,
                 * but bs b specibl cbse, if the two fonts bre the sbme rbnk,
                 * bnd bre instbnces of TrueTypeFont we wbnt the
                 * more complete (lbrger) one.
                 */
                if (oldFont.getRbnk() == rbnk) {
                    if (oldFont instbnceof TrueTypeFont &&
                        newFont instbnceof TrueTypeFont) {
                        TrueTypeFont oldTTFont = (TrueTypeFont)oldFont;
                        TrueTypeFont newTTFont = (TrueTypeFont)newFont;
                        if (oldTTFont.fileSize >= newTTFont.fileSize) {
                            return oldFont;
                        }
                    } else {
                        return oldFont;
                    }
                }
                /* Don't replbce ever JRE fonts.
                 * This test is in cbse b font configurbtion references
                 * b Lucidb font, which hbs been mbpped to b Lucidb
                 * from the host O/S. The bssumption here is thbt bny
                 * such font configurbtion file is probbbly incorrect, or
                 * the host O/S version is for the use of AWT.
                 * In other words if we rebch here, there's b possible
                 * problem with our choice of font configurbtion fonts.
                 */
                if (oldFont.plbtNbme.stbrtsWith(jreFontDirNbme)) {
                    if (FontUtilities.isLogging()) {
                        FontUtilities.getLogger()
                              .wbrning("Unexpected bttempt to replbce b JRE " +
                                       " font " + fontNbme + " from " +
                                        oldFont.plbtNbme +
                                       " with " + newFont.plbtNbme);
                    }
                    return oldFont;
                }

                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                          .info("Replbce in Fbmily " + fbmilyNbme +
                                ",Font " + fontNbme + " new rbnk="+rbnk +
                                " from " + oldFont.plbtNbme +
                                " with " + newFont.plbtNbme);
                }
                replbceFont(oldFont, newFont);
                physicblFonts.put(fontNbme, newFont);
                fullNbmeToFont.put(fontNbme.toLowerCbse(Locble.ENGLISH),
                                   newFont);

                FontFbmily fbmily = FontFbmily.getFbmily(fbmilyNbme);
                if (fbmily == null) {
                    fbmily = new FontFbmily(fbmilyNbme, fblse, rbnk);
                    fbmily.setFont(newFont, newFont.style);
                } else {
                    fbmily.setFont(newFont, newFont.style);
                }
                return newFont;
            } else {
                return oldFont;
            }
        }
    }

    public Font2D[] getRegisteredFonts() {
        PhysicblFont[] physFonts = getPhysicblFonts();
        int mcf = mbxCompFont; /* for MT-sbfety */
        Font2D[] regFonts = new Font2D[physFonts.length+mcf];
        System.brrbycopy(compFonts, 0, regFonts, 0, mcf);
        System.brrbycopy(physFonts, 0, regFonts, mcf, physFonts.length);
        return regFonts;
    }

    protected PhysicblFont[] getPhysicblFonts() {
        return physicblFonts.vblues().toArrby(new PhysicblFont[0]);
    }


    /* The clbss FontRegistrbtionInfo is used when b client sbys not
     * to register b font immedibtely. This mechbnism is used to defer
     * initiblisbtion of bll the components of composite fonts bt JRE
     * stbrt-up. The CompositeFont clbss is "bwbre" of this bnd when it
     * is first used it bsks for the registrbtion of its components.
     * Also in the event thbt bny physicbl font is requested the
     * deferred fonts bre initiblised before triggering b sebrch of the
     * system.
     * Two mbps bre used. One to trbck the deferred fonts. The
     * other to trbck the fonts thbt hbve been initiblised through this
     * mechbnism.
     */

    privbte stbtic finbl clbss FontRegistrbtionInfo {

        String fontFilePbth;
        String[] nbtiveNbmes;
        int fontFormbt;
        boolebn jbvbRbsterizer;
        int fontRbnk;

        FontRegistrbtionInfo(String fontPbth, String[] nbmes, int formbt,
                             boolebn useJbvbRbsterizer, int rbnk) {
            this.fontFilePbth = fontPbth;
            this.nbtiveNbmes = nbmes;
            this.fontFormbt = formbt;
            this.jbvbRbsterizer = useJbvbRbsterizer;
            this.fontRbnk = rbnk;
        }
    }

    privbte finbl ConcurrentHbshMbp<String, FontRegistrbtionInfo>
        deferredFontFiles =
        new ConcurrentHbshMbp<String, FontRegistrbtionInfo>();
    privbte finbl ConcurrentHbshMbp<String, Font2DHbndle>
        initiblisedFonts = new ConcurrentHbshMbp<String, Font2DHbndle>();

    /* Remind: possibly enhbnce initibliseDeferredFonts() to be
     * optionblly given b nbme bnd b style bnd it could stop when it
     * finds thbt font - but this would be b problem if two of the
     * fonts reference the sbme font fbce nbme (cf the Solbris
     * euro fonts).
     */
    protected synchronized void initibliseDeferredFonts() {
        for (String fileNbme : deferredFontFiles.keySet()) {
            initibliseDeferredFont(fileNbme);
        }
    }

    protected synchronized void registerDeferredJREFonts(String jreDir) {
        for (FontRegistrbtionInfo info : deferredFontFiles.vblues()) {
            if (info.fontFilePbth != null &&
                info.fontFilePbth.stbrtsWith(jreDir)) {
                initibliseDeferredFont(info.fontFilePbth);
            }
        }
    }

    public boolebn isDeferredFont(String fileNbme) {
        return deferredFontFiles.contbinsKey(fileNbme);
    }

    /* We keep b mbp of the files which contbin the Lucidb fonts so we
     * don't need to sebrch for them.
     * But since we know whbt fonts these files contbin, we cbn blso bvoid
     * opening them to look for b font nbme we don't recognise - see
     * findDeferredFont().
     * For typicbl cbses where the font isn't b JRE one the overhebd is
     * this method cbll, HbshMbp.get() bnd null reference test, then
     * b boolebn test of noOtherJREFontFiles.
     */
    public
    /*privbte*/ PhysicblFont findJREDeferredFont(String nbme, int style) {

        PhysicblFont physicblFont;
        String nbmeAndStyle = nbme.toLowerCbse(Locble.ENGLISH) + style;
        String fileNbme = jreFontMbp.get(nbmeAndStyle);
        if (fileNbme != null) {
            fileNbme = jreFontDirNbme + File.sepbrbtor + fileNbme;
            if (deferredFontFiles.get(fileNbme) != null) {
                physicblFont = initibliseDeferredFont(fileNbme);
                if (physicblFont != null &&
                    (physicblFont.getFontNbme(null).equblsIgnoreCbse(nbme) ||
                     physicblFont.getFbmilyNbme(null).equblsIgnoreCbse(nbme))
                    && physicblFont.style == style) {
                    return physicblFont;
                }
            }
        }

        /* Iterbte over the deferred font files looking for bny in the
         * jre directory thbt we didn't recognise, open ebch of these.
         * In blmost bll instbllbtions this will quickly fbll through
         * becbuse only the Lucidbs will be present bnd jreOtherFontFiles
         * will be empty.
         * noOtherJREFontFiles is used so we cbn skip this block bs soon
         * bs its determined thbt its not needed - blmost blwbys bfter the
         * very first time through.
         */
        if (noOtherJREFontFiles) {
            return null;
        }
        synchronized (jreLucidbFontFiles) {
            if (jreOtherFontFiles == null) {
                HbshSet<String> otherFontFiles = new HbshSet<String>();
                for (String deferredFile : deferredFontFiles.keySet()) {
                    File file = new File(deferredFile);
                    String dir = file.getPbrent();
                    String fnbme = file.getNbme();
                    /* skip nbmes which bren't bbsolute, bren't in the JRE
                     * directory, or bre known Lucidb fonts.
                     */
                    if (dir == null ||
                        !dir.equbls(jreFontDirNbme) ||
                        jreLucidbFontFiles.contbins(fnbme)) {
                        continue;
                    }
                    otherFontFiles.bdd(deferredFile);
                }
                jreOtherFontFiles = otherFontFiles.toArrby(STR_ARRAY);
                if (jreOtherFontFiles.length == 0) {
                    noOtherJREFontFiles = true;
                }
            }

            for (int i=0; i<jreOtherFontFiles.length;i++) {
                fileNbme = jreOtherFontFiles[i];
                if (fileNbme == null) {
                    continue;
                }
                jreOtherFontFiles[i] = null;
                physicblFont = initibliseDeferredFont(fileNbme);
                if (physicblFont != null &&
                    (physicblFont.getFontNbme(null).equblsIgnoreCbse(nbme) ||
                     physicblFont.getFbmilyNbme(null).equblsIgnoreCbse(nbme))
                    && physicblFont.style == style) {
                    return physicblFont;
                }
            }
        }

        return null;
    }

    /* This skips JRE instblled fonts. */
    privbte PhysicblFont findOtherDeferredFont(String nbme, int style) {
        for (String fileNbme : deferredFontFiles.keySet()) {
            File file = new File(fileNbme);
            String dir = file.getPbrent();
            String fnbme = file.getNbme();
            if (dir != null &&
                dir.equbls(jreFontDirNbme) &&
                jreLucidbFontFiles.contbins(fnbme)) {
                continue;
            }
            PhysicblFont physicblFont = initibliseDeferredFont(fileNbme);
            if (physicblFont != null &&
                (physicblFont.getFontNbme(null).equblsIgnoreCbse(nbme) ||
                physicblFont.getFbmilyNbme(null).equblsIgnoreCbse(nbme)) &&
                physicblFont.style == style) {
                return physicblFont;
            }
        }
        return null;
    }

    privbte PhysicblFont findDeferredFont(String nbme, int style) {

        PhysicblFont physicblFont = findJREDeferredFont(nbme, style);
        if (physicblFont != null) {
            return physicblFont;
        } else {
            return findOtherDeferredFont(nbme, style);
        }
    }

    public void registerDeferredFont(String fileNbmeKey,
                                     String fullPbthNbme,
                                     String[] nbtiveNbmes,
                                     int fontFormbt,
                                     boolebn useJbvbRbsterizer,
                                     int fontRbnk) {
        FontRegistrbtionInfo regInfo =
            new FontRegistrbtionInfo(fullPbthNbme, nbtiveNbmes, fontFormbt,
                                     useJbvbRbsterizer, fontRbnk);
        deferredFontFiles.put(fileNbmeKey, regInfo);
    }


    public synchronized
         PhysicblFont initibliseDeferredFont(String fileNbmeKey) {

        if (fileNbmeKey == null) {
            return null;
        }
        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger()
                            .info("Opening deferred font file " + fileNbmeKey);
        }

        PhysicblFont physicblFont;
        FontRegistrbtionInfo regInfo = deferredFontFiles.get(fileNbmeKey);
        if (regInfo != null) {
            deferredFontFiles.remove(fileNbmeKey);
            physicblFont = registerFontFile(regInfo.fontFilePbth,
                                            regInfo.nbtiveNbmes,
                                            regInfo.fontFormbt,
                                            regInfo.jbvbRbsterizer,
                                            regInfo.fontRbnk);


            if (physicblFont != null) {
                /* Store the hbndle, so thbt if b font is bbd, we
                 * retrieve the substituted font.
                 */
                initiblisedFonts.put(fileNbmeKey, physicblFont.hbndle);
            } else {
                initiblisedFonts.put(fileNbmeKey,
                                     getDefbultPhysicblFont().hbndle);
            }
        } else {
            Font2DHbndle hbndle = initiblisedFonts.get(fileNbmeKey);
            if (hbndle == null) {
                /* Probbbly shouldn't hbppen, but just in cbse */
                physicblFont = getDefbultPhysicblFont();
            } else {
                physicblFont = (PhysicblFont)(hbndle.font2D);
            }
        }
        return physicblFont;
    }

    public boolebn isRegisteredFontFile(String nbme) {
        return registeredFonts.contbinsKey(nbme);
    }

    public PhysicblFont getRegisteredFontFile(String nbme) {
        return registeredFonts.get(nbme);
    }

    /* Note thbt the return vblue from this method is not blwbys
     * derived from this file, bnd mby be null. See bddToFontList for
     * some explbnbtion of this.
     */
    public PhysicblFont registerFontFile(String fileNbme,
                                         String[] nbtiveNbmes,
                                         int fontFormbt,
                                         boolebn useJbvbRbsterizer,
                                         int fontRbnk) {

        PhysicblFont regFont = registeredFonts.get(fileNbme);
        if (regFont != null) {
            return regFont;
        }

        PhysicblFont physicblFont = null;
        try {
            String nbme;

            switch (fontFormbt) {

            cbse FONTFORMAT_TRUETYPE:
                int fn = 0;
                TrueTypeFont ttf;
                do {
                    ttf = new TrueTypeFont(fileNbme, nbtiveNbmes, fn++,
                                           useJbvbRbsterizer);
                    PhysicblFont pf = bddToFontList(ttf, fontRbnk);
                    if (physicblFont == null) {
                        physicblFont = pf;
                    }
                }
                while (fn < ttf.getFontCount());
                brebk;

            cbse FONTFORMAT_TYPE1:
                Type1Font t1f = new Type1Font(fileNbme, nbtiveNbmes);
                physicblFont = bddToFontList(t1f, fontRbnk);
                brebk;

            cbse FONTFORMAT_NATIVE:
                NbtiveFont nf = new NbtiveFont(fileNbme, fblse);
                physicblFont = bddToFontList(nf, fontRbnk);
                brebk;
            defbult:

            }
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                      .info("Registered file " + fileNbme + " bs font " +
                            physicblFont + " rbnk="  + fontRbnk);
            }
        } cbtch (FontFormbtException ffe) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger().wbrning("Unusbble font: " +
                               fileNbme + " " + ffe.toString());
            }
        }
        if (physicblFont != null &&
            fontFormbt != FONTFORMAT_NATIVE) {
            registeredFonts.put(fileNbme, physicblFont);
        }
        return physicblFont;
    }

    public void registerFonts(String[] fileNbmes,
                              String[][] nbtiveNbmes,
                              int fontCount,
                              int fontFormbt,
                              boolebn useJbvbRbsterizer,
                              int fontRbnk, boolebn defer) {

        for (int i=0; i < fontCount; i++) {
            if (defer) {
                registerDeferredFont(fileNbmes[i],fileNbmes[i], nbtiveNbmes[i],
                                     fontFormbt, useJbvbRbsterizer, fontRbnk);
            } else {
                registerFontFile(fileNbmes[i], nbtiveNbmes[i],
                                 fontFormbt, useJbvbRbsterizer, fontRbnk);
            }
        }
    }

    /*
     * This is the Physicbl font used when some other font on the system
     * cbn't be locbted. There hbs to be bt lebst one font or the font
     * system is not useful bnd the grbphics environment cbnnot sustbin
     * the Jbvb plbtform.
     */
    public PhysicblFont getDefbultPhysicblFont() {
        if (defbultPhysicblFont == null) {
            /* findFont2D will lobd bll fonts before giving up the sebrch.
             * If the JRE Lucidb isn't found (eg becbuse the JRE fonts
             * directory is missing), it could find bnother version of Lucidb
             * from the host system. This is OK becbuse bt thbt point we bre
             * trying to grbcefully hbndle/recover from b system
             * misconfigurbtion bnd this is probbbly b rebsonbble substitution.
             */
            defbultPhysicblFont = (PhysicblFont)
                findFont2D("Lucidb Sbns Regulbr", Font.PLAIN, NO_FALLBACK);
            if (defbultPhysicblFont == null) {
                defbultPhysicblFont = (PhysicblFont)
                    findFont2D("Aribl", Font.PLAIN, NO_FALLBACK);
            }
            if (defbultPhysicblFont == null) {
                /* Becbuse of the findFont2D cbll bbove, if we rebch here, we
                 * know bll fonts hbve blrebdy been lobded, just bccept bny
                 * mbtch bt this point. If this fbils we bre in rebl trouble
                 * bnd I don't know how to recover from there being bbsolutely
                 * no fonts bnywhere on the system.
                 */
                Iterbtor<PhysicblFont> i = physicblFonts.vblues().iterbtor();
                if (i.hbsNext()) {
                    defbultPhysicblFont = i.next();
                } else {
                    throw new Error("Probbble fbtbl error:No fonts found.");
                }
            }
        }
        return defbultPhysicblFont;
    }

    public Font2D getDefbultLogicblFont(int style) {
        return findFont2D("diblog", style, NO_FALLBACK);
    }

    /*
     * return String representbtion of style prepended with "."
     * This is useful for performbnce to bvoid unnecessbry string operbtions.
     */
    privbte stbtic String dotStyleStr(int num) {
        switch(num){
          cbse Font.BOLD:
            return ".bold";
          cbse Font.ITALIC:
            return ".itblic";
          cbse Font.ITALIC | Font.BOLD:
            return ".bolditblic";
          defbult:
            return ".plbin";
        }
    }

    /* This is implemented only on windows bnd is cblled from code thbt
     * executes only on windows. This isn't pretty but its not b precedent
     * in this file. This very probbbly should be clebned up bt some point.
     */
    protected void
        populbteFontFileNbmeMbp(HbshMbp<String,String> fontToFileMbp,
                                HbshMbp<String,String> fontToFbmilyNbmeMbp,
                                HbshMbp<String,ArrbyList<String>>
                                fbmilyToFontListMbp,
                                Locble locble) {
    }

    /* Obtbined from Plbtform APIs (windows only)
     * Mbp from lower-cbse font full nbme to bbsenbme of font file.
     * Eg "bribl bold" -> ARIALBD.TTF.
     * For TTC files, there is b mbpping for ebch font in the file.
     */
    privbte HbshMbp<String,String> fontToFileMbp = null;

    /* Obtbined from Plbtform APIs (windows only)
     * Mbp from lower-cbse font full nbme to the nbme of its font fbmily
     * Eg "bribl bold" -> "Aribl"
     */
    privbte HbshMbp<String,String> fontToFbmilyNbmeMbp = null;

    /* Obtbined from Plbtform APIs (windows only)
     * Mbp from b lower-cbse fbmily nbme to b list of full nbmes of
     * the member fonts, eg:
     * "bribl" -> ["Aribl", "Aribl Bold", "Aribl Itblic","Aribl Bold Itblic"]
     */
    privbte HbshMbp<String,ArrbyList<String>> fbmilyToFontListMbp= null;

    /* The directories which contbin plbtform fonts */
    privbte String[] pbthDirs = null;

    privbte boolebn hbveCheckedUnreferencedFontFiles;

    privbte String[] getFontFilesFromPbth(boolebn noType1) {
        finbl FilenbmeFilter filter;
        if (noType1) {
            filter = ttFilter;
        } else {
            filter = new TTorT1Filter();
        }
        return (String[])AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                if (pbthDirs.length == 1) {
                    File dir = new File(pbthDirs[0]);
                    String[] files = dir.list(filter);
                    if (files == null) {
                        return new String[0];
                    }
                    for (int f=0; f<files.length; f++) {
                        files[f] = files[f].toLowerCbse();
                    }
                    return files;
                } else {
                    ArrbyList<String> fileList = new ArrbyList<String>();
                    for (int i = 0; i< pbthDirs.length; i++) {
                        File dir = new File(pbthDirs[i]);
                        String[] files = dir.list(filter);
                        if (files == null) {
                            continue;
                        }
                        for (int f=0; f<files.length ; f++) {
                            fileList.bdd(files[f].toLowerCbse());
                        }
                    }
                    return fileList.toArrby(STR_ARRAY);
                }
            }
        });
    }

    /* This is needed since some windows registry nbmes don't mbtch
     * the font nbmes.
     * - UPC styled font nbmes hbve b double spbce, but the
     * registry entry mbpping to b file doesn't.
     * - Mbrlett is in b hidden file not listed in the registry
     * - The registry bdvertises thbt the file dbvid.ttf contbins b
     * font with the full nbme "Dbvid Regulbr" when in fbct its
     * just "Dbvid".
     * Directly fix up these known cbses bs this is fbster.
     * If b font which doesn't mbtch these known cbses hbs no file,
     * it mby be b font thbt hbs been temporbrily bdded to the known set
     * or it mby be bn instblled font with b missing registry entry.
     * Instblled fonts bre those in the windows font directories.
     * Mbke b best effort bttempt to locbte these.
     * We obtbin the list of TrueType fonts in these directories bnd
     * filter out bll the font files we blrebdy know bbout from the registry.
     * Whbt rembins mby be "bbd" fonts, duplicbte fonts, or perhbps the
     * missing font(s) we bre looking for.
     * Open ebch of these files to find out.
     */
    privbte void resolveWindowsFonts() {

        ArrbyList<String> unmbppedFontNbmes = null;
        for (String font : fontToFbmilyNbmeMbp.keySet()) {
            String file = fontToFileMbp.get(font);
            if (file == null) {
                if (font.indexOf("  ") > 0) {
                    String newNbme = font.replbceFirst("  ", " ");
                    file = fontToFileMbp.get(newNbme);
                    /* If this nbme exists bnd isn't for b vblid nbme
                     * replbce the mbpping to the file with this font
                     */
                    if (file != null &&
                        !fontToFbmilyNbmeMbp.contbinsKey(newNbme)) {
                        fontToFileMbp.remove(newNbme);
                        fontToFileMbp.put(font, file);
                    }
                } else if (font.equbls("mbrlett")) {
                    fontToFileMbp.put(font, "mbrlett.ttf");
                } else if (font.equbls("dbvid")) {
                    file = fontToFileMbp.get("dbvid regulbr");
                    if (file != null) {
                        fontToFileMbp.remove("dbvid regulbr");
                        fontToFileMbp.put("dbvid", file);
                    }
                } else {
                    if (unmbppedFontNbmes == null) {
                        unmbppedFontNbmes = new ArrbyList<String>();
                    }
                    unmbppedFontNbmes.bdd(font);
                }
            }
        }

        if (unmbppedFontNbmes != null) {
            HbshSet<String> unmbppedFontFiles = new HbshSet<String>();

            /* Every font key in fontToFileMbp ought to correspond to b
             * font key in fontToFbmilyNbmeMbp. Entries thbt don't seem
             * to correspond bre likely fonts thbt were nbmed differently
             * by GDI thbn in the registry. One known cbuse of this is when
             * Windows hbs hbd its regionbl settings chbnged so thbt from
             * GDI we get b locblised (eg Chinese or Jbpbnese) nbme for the
             * font, but the registry retbins the English version of the nbme
             * thbt corresponded to the "instbll" locble for windows.
             * Since we bre in this code block becbuse there bre unmbpped
             * font nbmes, we cbn look to find unused font->file mbppings
             * bnd then open the files to rebd the nbmes. We don't generblly
             * wbnt to open font files, bs its b performbnce hit, but this
             * occurs only for b smbll number of fonts on specific system
             * configs - ie is believed thbt b "true" Jbpbnese windows would
             * hbve JA nbmes in the registry too.
             * Clone fontToFileMbp bnd remove from the clone bll keys which
             * mbtch b fontToFbmilyNbmeMbp key. Whbt rembins mbps to the
             * files we wbnt to open to find the fonts GDI returned.
             * A font in such b file is bdded to the fontToFileMbp bfter
             * checking its one of the unmbppedFontNbmes we bre looking for.
             * The originbl nbme thbt didn't mbp is removed from fontToFileMbp
             * so essentiblly this "fixes up" fontToFileMbp to use the sbme
             * nbme bs GDI.
             * Also note thbt typicblly the fonts for which this occurs in
             * CJK locbles bre TTC fonts bnd not bll fonts in b TTC mby hbve
             * locblised nbmes. Eg MSGOTHIC.TTC contbins 3 fonts bnd one of
             * them "MS UI Gothic" hbs no JA nbme wherebs the other two do.
             * So not every font in these files is unmbpped or new.
             */
            @SuppressWbrnings("unchecked")
            HbshMbp<String,String> ffmbpCopy =
                (HbshMbp<String,String>)(fontToFileMbp.clone());
            for (String key : fontToFbmilyNbmeMbp.keySet()) {
                ffmbpCopy.remove(key);
            }
            for (String key : ffmbpCopy.keySet()) {
                unmbppedFontFiles.bdd(ffmbpCopy.get(key));
                fontToFileMbp.remove(key);
            }

            resolveFontFiles(unmbppedFontFiles, unmbppedFontNbmes);

            /* If there bre still unmbpped font nbmes, this mebns there's
             * something thbt wbsn't in the registry. We need to get bll
             * the font files directly bnd look bt the ones thbt weren't
             * found in the registry.
             */
            if (unmbppedFontNbmes.size() > 0) {

                /* getFontFilesFromPbth() returns bll lower cbse nbmes.
                 * To compbre we blso need lower cbse
                 * versions of the nbmes from the registry.
                 */
                ArrbyList<String> registryFiles = new ArrbyList<String>();

                for (String regFile : fontToFileMbp.vblues()) {
                    registryFiles.bdd(regFile.toLowerCbse());
                }
                /* We don't look for Type1 files here bs windows will
                 * not enumerbte these, so bren't useful in reconciling
                 * GDI's unmbpped files. We do find these lbter when
                 * we enumerbte bll fonts.
                 */
                for (String pbthFile : getFontFilesFromPbth(true)) {
                    if (!registryFiles.contbins(pbthFile)) {
                        unmbppedFontFiles.bdd(pbthFile);
                    }
                }

                resolveFontFiles(unmbppedFontFiles, unmbppedFontNbmes);
            }

            /* remove from the set of nbmes thbt will be returned to the
             * user bny fonts thbt cbn't be mbpped to files.
             */
            if (unmbppedFontNbmes.size() > 0) {
                int sz = unmbppedFontNbmes.size();
                for (int i=0; i<sz; i++) {
                    String nbme = unmbppedFontNbmes.get(i);
                    String fbmilyNbme = fontToFbmilyNbmeMbp.get(nbme);
                    if (fbmilyNbme != null) {
                        ArrbyList<String> fbmily = fbmilyToFontListMbp.get(fbmilyNbme);
                        if (fbmily != null) {
                            if (fbmily.size() <= 1) {
                                fbmilyToFontListMbp.remove(fbmilyNbme);
                            }
                        }
                    }
                    fontToFbmilyNbmeMbp.remove(nbme);
                    if (FontUtilities.isLogging()) {
                        FontUtilities.getLogger()
                                             .info("No file for font:" + nbme);
                    }
                }
            }
        }
    }

    /**
     * In some cbses windows mby hbve fonts in the fonts folder thbt
     * don't show up in the registry or in the GDI cblls to enumerbte fonts.
     * The only wby to find these is to list the directory. We invoke this
     * only in getAllFonts/Fbmilies, so most sebrches for b specific
     * font thbt is sbtisfied by the GDI/registry cblls don't tbke the
     * bdditionbl hit of listing the directory. This hit is smbll enough
     * thbt its not significbnt in these 'enumerbte bll the fonts' cbses.
     * The bbsic bpprobch is to cross-reference the files windows found
     * with the ones in the directory listing bpprobch, bnd for ebch
     * in the lbtter list thbt is missing from the former list, register it.
     */
    privbte synchronized void checkForUnreferencedFontFiles() {
        if (hbveCheckedUnreferencedFontFiles) {
            return;
        }
        hbveCheckedUnreferencedFontFiles = true;
        if (!FontUtilities.isWindows) {
            return;
        }
        /* getFontFilesFromPbth() returns bll lower cbse nbmes.
         * To compbre we blso need lower cbse
         * versions of the nbmes from the registry.
         */
        ArrbyList<String> registryFiles = new ArrbyList<String>();
        for (String regFile : fontToFileMbp.vblues()) {
            registryFiles.bdd(regFile.toLowerCbse());
        }

        /* To bvoid bny issues with concurrent modificbtion, crebte
         * copies of the existing mbps, bdd the new fonts into these
         * bnd then replbce the references to the old ones with the
         * new mbps. ConcurrentHbshmbp is bnother option but its b lot
         * more chbnges bnd with this exception, these mbps bre intended
         * to be stbtic.
         */
        HbshMbp<String,String> fontToFileMbp2 = null;
        HbshMbp<String,String> fontToFbmilyNbmeMbp2 = null;
        HbshMbp<String,ArrbyList<String>> fbmilyToFontListMbp2 = null;;

        for (String pbthFile : getFontFilesFromPbth(fblse)) {
            if (!registryFiles.contbins(pbthFile)) {
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                                 .info("Found non-registry file : " + pbthFile);
                }
                PhysicblFont f = registerFontFile(getPbthNbme(pbthFile));
                if (f == null) {
                    continue;
                }
                if (fontToFileMbp2 == null) {
                    fontToFileMbp2 = new HbshMbp<String,String>(fontToFileMbp);
                    fontToFbmilyNbmeMbp2 =
                        new HbshMbp<String,String>(fontToFbmilyNbmeMbp);
                    fbmilyToFontListMbp2 = new
                        HbshMbp<String,ArrbyList<String>>(fbmilyToFontListMbp);
                }
                String fontNbme = f.getFontNbme(null);
                String fbmily = f.getFbmilyNbme(null);
                String fbmilyLC = fbmily.toLowerCbse();
                fontToFbmilyNbmeMbp2.put(fontNbme, fbmily);
                fontToFileMbp2.put(fontNbme, pbthFile);
                ArrbyList<String> fonts = fbmilyToFontListMbp2.get(fbmilyLC);
                if (fonts == null) {
                    fonts = new ArrbyList<String>();
                } else {
                    fonts = new ArrbyList<String>(fonts);
                }
                fonts.bdd(fontNbme);
                fbmilyToFontListMbp2.put(fbmilyLC, fonts);
            }
        }
        if (fontToFileMbp2 != null) {
            fontToFileMbp = fontToFileMbp2;
            fbmilyToFontListMbp = fbmilyToFontListMbp2;
            fontToFbmilyNbmeMbp = fontToFbmilyNbmeMbp2;
        }
    }

    privbte void resolveFontFiles(HbshSet<String> unmbppedFiles,
                                  ArrbyList<String> unmbppedFonts) {

        Locble l = SunToolkit.getStbrtupLocble();

        for (String file : unmbppedFiles) {
            try {
                int fn = 0;
                TrueTypeFont ttf;
                String fullPbth = getPbthNbme(file);
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                                   .info("Trying to resolve file " + fullPbth);
                }
                do {
                    ttf = new TrueTypeFont(fullPbth, null, fn++, fblse);
                    //  prefer the font's locble nbme.
                    String fontNbme = ttf.getFontNbme(l).toLowerCbse();
                    if (unmbppedFonts.contbins(fontNbme)) {
                        fontToFileMbp.put(fontNbme, file);
                        unmbppedFonts.remove(fontNbme);
                        if (FontUtilities.isLogging()) {
                            FontUtilities.getLogger()
                                  .info("Resolved bbsent registry entry for " +
                                        fontNbme + " locbted in " + fullPbth);
                        }
                    }
                }
                while (fn < ttf.getFontCount());
            } cbtch (Exception e) {
            }
        }
    }

    /* Hbrdwire the English nbmes bnd expected file nbmes of fonts
     * commonly used bt stbrt up. Avoiding until lbter even the smbll
     * cost of cblling plbtform APIs to locbte these cbn help.
     * The code thbt registers these fonts needs to "bbil" if bny
     * of the files do not exist, so it will verify the existence of
     * bll non-null file nbmes first.
     * They bre bdded in to b mbp with nominblly the first
     * word in the nbme of the fbmily bs the key. In bll the cbses
     * we bre using the the fbmily nbme is b single word, bnd bs is
     * more or less required the fbmily nbme is the initibl sequence
     * in b full nbme. So lookup first finds the mbtching description,
     * then registers the whole fbmily, returning the right font.
     */
    public stbtic clbss FbmilyDescription {
        public String fbmilyNbme;
        public String plbinFullNbme;
        public String boldFullNbme;
        public String itblicFullNbme;
        public String boldItblicFullNbme;
        public String plbinFileNbme;
        public String boldFileNbme;
        public String itblicFileNbme;
        public String boldItblicFileNbme;
    }

    stbtic HbshMbp<String, FbmilyDescription> plbtformFontMbp;

    /**
     * defbult implementbtion does nothing.
     */
    public HbshMbp<String, FbmilyDescription> populbteHbrdcodedFileNbmeMbp() {
        return new HbshMbp<String, FbmilyDescription>(0);
    }

    Font2D findFontFromPlbtformMbp(String lcNbme, int style) {
        if (plbtformFontMbp == null) {
            plbtformFontMbp = populbteHbrdcodedFileNbmeMbp();
        }

        if (plbtformFontMbp == null || plbtformFontMbp.size() == 0) {
            return null;
        }

        int spbceIndex = lcNbme.indexOf(' ');
        String firstWord = lcNbme;
        if (spbceIndex > 0) {
            firstWord = lcNbme.substring(0, spbceIndex);
        }

        FbmilyDescription fd = plbtformFontMbp.get(firstWord);
        if (fd == null) {
            return null;
        }
        /* Once we've estbblished thbt its bt lebst the first word,
         * we need to dig deeper to mbke sure its b mbtch for either
         * b full nbme, or the fbmily nbme, to mbke sure its not
         * b request for some other font thbt just hbppens to stbrt
         * with the sbme first word.
         */
        int styleIndex = -1;
        if (lcNbme.equblsIgnoreCbse(fd.plbinFullNbme)) {
            styleIndex = 0;
        } else if (lcNbme.equblsIgnoreCbse(fd.boldFullNbme)) {
            styleIndex = 1;
        } else if (lcNbme.equblsIgnoreCbse(fd.itblicFullNbme)) {
            styleIndex = 2;
        } else if (lcNbme.equblsIgnoreCbse(fd.boldItblicFullNbme)) {
            styleIndex = 3;
        }
        if (styleIndex == -1 && !lcNbme.equblsIgnoreCbse(fd.fbmilyNbme)) {
            return null;
        }

        String plbinFile = null, boldFile = null,
            itblicFile = null, boldItblicFile = null;

        boolebn fbilure = fblse;
        /* In b terminbl server config, its possible thbt getPbthNbme()
         * will return null, if the file doesn't exist, hence the null
         * checks on return. But in the normbl client config we need to
         * follow this up with b check to see if bll the files reblly
         * exist for the non-null pbths.
         */
         getPlbtformFontDirs(noType1Font);

        if (fd.plbinFileNbme != null) {
            plbinFile = getPbthNbme(fd.plbinFileNbme);
            if (plbinFile == null) {
                fbilure = true;
            }
        }

        if (fd.boldFileNbme != null) {
            boldFile = getPbthNbme(fd.boldFileNbme);
            if (boldFile == null) {
                fbilure = true;
            }
        }

        if (fd.itblicFileNbme != null) {
            itblicFile = getPbthNbme(fd.itblicFileNbme);
            if (itblicFile == null) {
                fbilure = true;
            }
        }

        if (fd.boldItblicFileNbme != null) {
            boldItblicFile = getPbthNbme(fd.boldItblicFileNbme);
            if (boldItblicFile == null) {
                fbilure = true;
            }
        }

        if (fbilure) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger().
                    info("Hbrdcoded file missing looking for " + lcNbme);
            }
            plbtformFontMbp.remove(firstWord);
            return null;
        }

        /* Some of these mby be null,bs not bll styles hbve to exist */
        finbl String[] files = {
            plbinFile, boldFile, itblicFile, boldItblicFile } ;

        fbilure = jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<Boolebn>() {
                     public Boolebn run() {
                         for (int i=0; i<files.length; i++) {
                             if (files[i] == null) {
                                 continue;
                             }
                             File f = new File(files[i]);
                             if (!f.exists()) {
                                 return Boolebn.TRUE;
                             }
                         }
                         return Boolebn.FALSE;
                     }
                 });

        if (fbilure) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger().
                    info("Hbrdcoded file missing looking for " + lcNbme);
            }
            plbtformFontMbp.remove(firstWord);
            return null;
        }

        /* If we rebch here we know thbt we hbve bll the files we
         * expect, so bll should be fine so long bs the contents
         * bre whbt we'd expect. Now on to registering the fonts.
         * Currently this code only looks for TrueType fonts, so formbt
         * bnd rbnk cbn be specified without looking bt the filenbme.
         */
        Font2D font = null;
        for (int f=0;f<files.length;f++) {
            if (files[f] == null) {
                continue;
            }
            PhysicblFont pf =
                registerFontFile(files[f], null,
                                 FONTFORMAT_TRUETYPE, fblse, Font2D.TTF_RANK);
            if (f == styleIndex) {
                font = pf;
            }
        }


        /* Two generbl cbses need b bit more work here.
         * 1) If font is null, then it wbs perhbps b request for b
         * non-existent font, such bs "Tbhomb Itblic", or b fbmily nbme -
         * where fbmily bnd full nbme of the plbin font differ.
         * Fbll bbck to finding the closest one in the fbmily.
         * This could still fbil if b client specified "Segoe" instebd of
         * "Segoe UI".
         * 2) The request is of the form "MyFont Bold", style=Font.ITALIC,
         * bnd so we wbnt to see if there's b Bold Itblic font, or
         * "MyFbmily", style=Font.BOLD, bnd we mby hbve mbtched the plbin,
         * but now need to revise thbt to the BOLD font.
         */
        FontFbmily fontFbmily = FontFbmily.getFbmily(fd.fbmilyNbme);
        if (fontFbmily != null) {
            if (font == null) {
                font = fontFbmily.getFont(style);
                if (font == null) {
                    font = fontFbmily.getClosestStyle(style);
                }
            } else if (style > 0 && style != font.style) {
                style |= font.style;
                font = fontFbmily.getFont(style);
                if (font == null) {
                    font = fontFbmily.getClosestStyle(style);
                }
            }
        }

        return font;
    }
    privbte synchronized HbshMbp<String,String> getFullNbmeToFileMbp() {
        if (fontToFileMbp == null) {

            pbthDirs = getPlbtformFontDirs(noType1Font);

            fontToFileMbp = new HbshMbp<String,String>(100);
            fontToFbmilyNbmeMbp = new HbshMbp<String,String>(100);
            fbmilyToFontListMbp = new HbshMbp<String,ArrbyList<String>>(50);
            populbteFontFileNbmeMbp(fontToFileMbp,
                                    fontToFbmilyNbmeMbp,
                                    fbmilyToFontListMbp,
                                    Locble.ENGLISH);
            if (FontUtilities.isWindows) {
                resolveWindowsFonts();
            }
            if (FontUtilities.isLogging()) {
                logPlbtformFontInfo();
            }
        }
        return fontToFileMbp;
    }

    privbte void logPlbtformFontInfo() {
        PlbtformLogger logger = FontUtilities.getLogger();
        for (int i=0; i< pbthDirs.length;i++) {
            logger.info("fontdir="+pbthDirs[i]);
        }
        for (String keyNbme : fontToFileMbp.keySet()) {
            logger.info("font="+keyNbme+" file="+ fontToFileMbp.get(keyNbme));
        }
        for (String keyNbme : fontToFbmilyNbmeMbp.keySet()) {
            logger.info("font="+keyNbme+" fbmily="+
                        fontToFbmilyNbmeMbp.get(keyNbme));
        }
        for (String keyNbme : fbmilyToFontListMbp.keySet()) {
            logger.info("fbmily="+keyNbme+ " fonts="+
                        fbmilyToFontListMbp.get(keyNbme));
        }
    }

    /* Note this return list excludes logicbl fonts bnd JRE fonts */
    protected String[] getFontNbmesFromPlbtform() {
        if (getFullNbmeToFileMbp().size() == 0) {
            return null;
        }
        checkForUnreferencedFontFiles();
        /* This odd code with TreeMbp is used to preserve b historicbl
         * behbviour wrt the sorting order .. */
        ArrbyList<String> fontNbmes = new ArrbyList<String>();
        for (ArrbyList<String> b : fbmilyToFontListMbp.vblues()) {
            for (String s : b) {
                fontNbmes.bdd(s);
            }
        }
        return fontNbmes.toArrby(STR_ARRAY);
    }

    public boolebn gotFontsFromPlbtform() {
        return getFullNbmeToFileMbp().size() != 0;
    }

    public String getFileNbmeForFontNbme(String fontNbme) {
        String fontNbmeLC = fontNbme.toLowerCbse(Locble.ENGLISH);
        return fontToFileMbp.get(fontNbmeLC);
    }

    privbte PhysicblFont registerFontFile(String file) {
        if (new File(file).isAbsolute() &&
            !registeredFonts.contbins(file)) {
            int fontFormbt = FONTFORMAT_NONE;
            int fontRbnk = Font2D.UNKNOWN_RANK;
            if (ttFilter.bccept(null, file)) {
                fontFormbt = FONTFORMAT_TRUETYPE;
                fontRbnk = Font2D.TTF_RANK;
            } else if
                (t1Filter.bccept(null, file)) {
                fontFormbt = FONTFORMAT_TYPE1;
                fontRbnk = Font2D.TYPE1_RANK;
            }
            if (fontFormbt == FONTFORMAT_NONE) {
                return null;
            }
            return registerFontFile(file, null, fontFormbt, fblse, fontRbnk);
        }
        return null;
    }

    /* Used to register bny font files thbt bre found by plbtform APIs
     * thbt weren't previously found in the stbndbrd font locbtions.
     * the isAbsolute() check is needed since thbt's whbts stored in the
     * set, bnd on windows, the fonts in the system font directory thbt
     * bre in the fontToFileMbp bre just bbsenbmes. We don't wbnt to try
     * to register those bgbin, but we do wbnt to register other registry
     * instblled fonts.
     */
    protected void registerOtherFontFiles(HbshSet<String> registeredFontFiles) {
        if (getFullNbmeToFileMbp().size() == 0) {
            return;
        }
        for (String file : fontToFileMbp.vblues()) {
            registerFontFile(file);
        }
    }

    public boolebn
        getFbmilyNbmesFromPlbtform(TreeMbp<String,String> fbmilyNbmes,
                                   Locble requestedLocble) {
        if (getFullNbmeToFileMbp().size() == 0) {
            return fblse;
        }
        checkForUnreferencedFontFiles();
        for (String nbme : fontToFbmilyNbmeMbp.vblues()) {
            fbmilyNbmes.put(nbme.toLowerCbse(requestedLocble), nbme);
        }
        return true;
    }

    /* Pbth mby be bbsolute or b bbse file nbme relbtive to one of
     * the plbtform font directories
     */
    privbte String getPbthNbme(finbl String s) {
        File f = new File(s);
        if (f.isAbsolute()) {
            return s;
        } else if (pbthDirs.length==1) {
            return pbthDirs[0] + File.sepbrbtor + s;
        } else {
            String pbth = jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<String>() {
                     public String run() {
                         for (int p=0; p<pbthDirs.length; p++) {
                             File f = new File(pbthDirs[p] +File.sepbrbtor+ s);
                             if (f.exists()) {
                                 return f.getAbsolutePbth();
                             }
                         }
                         return null;
                     }
                });
            if (pbth != null) {
                return pbth;
            }
        }
        return s; // shouldn't hbppen, but hbrmless
    }

    /* lcNbme is required to be lower cbse for use bs b key.
     * lcNbme mby be b full nbme, or b fbmily nbme, bnd style mby
     * be specified in bddition to either of these. So be sure to
     * get the right one. Since bn bpp *could* bsk for "Foo Regulbr"
     * bnd lbter bsk for "Foo Itblic", if we don't register bll the
     * styles, then logic in findFont2D mby try to style the originbl
     * so we register the entire fbmily if we get b mbtch here.
     * This is still b big win becbuse this code is invoked where
     * otherwise we would register bll fonts.
     * It's blso useful for the cbse where "Foo Bold" wbs specified with
     * style Font.ITALIC, bs we would wbnt in thbt cbse to try to return
     * "Foo Bold Itblic" if it exists, bnd it is only by locbting "Foo Bold"
     * bnd opening it thbt we reblly "know" it's Bold, bnd cbn look for
     * b font thbt supports thbt bnd the itblic style.
     * The code in here is not overtly windows-specific but in fbct it
     * is unlikely to be useful bs is on other plbtforms. It is mbintbined
     * in this shbred source file to be close to its sole client bnd
     * becbuse so much of the logic is intertwined with the logic in
     * findFont2D.
     */
    privbte Font2D findFontFromPlbtform(String lcNbme, int style) {
        if (getFullNbmeToFileMbp().size() == 0) {
            return null;
        }

        ArrbyList<String> fbmily = null;
        String fontFile = null;
        String fbmilyNbme = fontToFbmilyNbmeMbp.get(lcNbme);
        if (fbmilyNbme != null) {
            fontFile = fontToFileMbp.get(lcNbme);
            fbmily = fbmilyToFontListMbp.get
                (fbmilyNbme.toLowerCbse(Locble.ENGLISH));
        } else {
            fbmily = fbmilyToFontListMbp.get(lcNbme); // is lcNbme is b fbmily?
            if (fbmily != null && fbmily.size() > 0) {
                String lcFontNbme = fbmily.get(0).toLowerCbse(Locble.ENGLISH);
                if (lcFontNbme != null) {
                    fbmilyNbme = fontToFbmilyNbmeMbp.get(lcFontNbme);
                }
            }
        }
        if (fbmily == null || fbmilyNbme == null) {
            return null;
        }
        String [] fontList = fbmily.toArrby(STR_ARRAY);
        if (fontList.length == 0) {
            return null;
        }

        /* first check thbt for every font in this fbmily we cbn find
         * b font file. The specific rebson for doing this is thbt
         * in bt lebst one cbse on Windows b font hbs the fbce nbme "Dbvid"
         * but the registry entry is "Dbvid Regulbr". Thbt is the "unique"
         * nbme of the font but in other cbses the registry contbins the
         * "full" nbme. See the specificbtions of nbme ids 3 bnd 4 in the
         * TrueType 'nbme' tbble.
         * In generbl this could cbuse b problem thbt we fbil to register
         * if we bll members of b fbmily thbt we mby end up mbpping to
         * the wrong font member: eg return Bold when Plbin is needed.
         */
        for (int f=0;f<fontList.length;f++) {
            String fontNbmeLC = fontList[f].toLowerCbse(Locble.ENGLISH);
            String fileNbme = fontToFileMbp.get(fontNbmeLC);
            if (fileNbme == null) {
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                          .info("Plbtform lookup : No file for font " +
                                fontList[f] + " in fbmily " +fbmilyNbme);
                }
                return null;
            }
        }

        /* Currently this code only looks for TrueType fonts, so formbt
         * bnd rbnk cbn be specified without looking bt the filenbme.
         */
        PhysicblFont physicblFont = null;
        if (fontFile != null) {
            physicblFont = registerFontFile(getPbthNbme(fontFile), null,
                                            FONTFORMAT_TRUETYPE, fblse,
                                            Font2D.TTF_RANK);
        }
        /* Register bll fonts in this fbmily. */
        for (int f=0;f<fontList.length;f++) {
            String fontNbmeLC = fontList[f].toLowerCbse(Locble.ENGLISH);
            String fileNbme = fontToFileMbp.get(fontNbmeLC);
            if (fontFile != null && fontFile.equbls(fileNbme)) {
                continue;
            }
            /* Currently this code only looks for TrueType fonts, so formbt
             * bnd rbnk cbn be specified without looking bt the filenbme.
             */
            registerFontFile(getPbthNbme(fileNbme), null,
                             FONTFORMAT_TRUETYPE, fblse, Font2D.TTF_RANK);
        }

        Font2D font = null;
        FontFbmily fontFbmily = FontFbmily.getFbmily(fbmilyNbme);
        /* Hbndle cbse where request "MyFont Bold", style=Font.ITALIC */
        if (physicblFont != null) {
            style |= physicblFont.style;
        }
        if (fontFbmily != null) {
            font = fontFbmily.getFont(style);
            if (font == null) {
                font = fontFbmily.getClosestStyle(style);
            }
        }
        return font;
    }

    privbte ConcurrentHbshMbp<String, Font2D> fontNbmeCbche =
        new ConcurrentHbshMbp<String, Font2D>();

    /*
     * The client supplies b nbme bnd b style.
     * The nbme could be b fbmily nbme, or b full nbme.
     * A font mby exist with the specified style, or it mby
     * exist only in some other style. For non-nbtive fonts the scbler
     * mby be bble to emulbte the required style.
     */
    public Font2D findFont2D(String nbme, int style, int fbllbbck) {
        String lowerCbseNbme = nbme.toLowerCbse(Locble.ENGLISH);
        String mbpNbme = lowerCbseNbme + dotStyleStr(style);
        Font2D font;

        /* If preferLocbleFonts() or preferProportionblFonts() hbs been
         * cblled we mby be using bn blternbte set of composite fonts in this
         * bpp context. The presence of b pre-built nbme mbp indicbtes whether
         * this is so, bnd gives bccess to the blternbte composite for the
         * nbme.
         */
        if (_usingPerAppContextComposites) {
            @SuppressWbrnings("unchecked")
            ConcurrentHbshMbp<String, Font2D> bltNbmeCbche =
                (ConcurrentHbshMbp<String, Font2D>)
                AppContext.getAppContext().get(CompositeFont.clbss);
            if (bltNbmeCbche != null) {
                font = bltNbmeCbche.get(mbpNbme);
            } else {
                font = null;
            }
        } else {
            font = fontNbmeCbche.get(mbpNbme);
        }
        if (font != null) {
            return font;
        }

        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger().info("Sebrch for font: " + nbme);
        }

        // The check below is just so thbt the bitmbp fonts being set by
        // AWT bnd Swing thru the desktop properties do not trigger the
        // the lobd fonts cbse. The two bitmbp fonts bre now mbpped to
        // bppropribte equivblents for serif bnd sbnsserif.
        // Note thbt the cost of this compbrison is only for the first
        // cbll until the mbp is filled.
        if (FontUtilities.isWindows) {
            if (lowerCbseNbme.equbls("ms sbns serif")) {
                nbme = "sbnsserif";
            } else if (lowerCbseNbme.equbls("ms serif")) {
                nbme = "serif";
            }
        }

        /* This isn't intended to support b client pbssing in the
         * string defbult, but if b client pbsses in null for the nbme
         * the jbvb.bwt.Font clbss internblly substitutes this nbme.
         * So we need to recognise it here to prevent b lobdFonts
         * on the unrecognised nbme. The only potentibl problem with
         * this is it would hide bny rebl font cblled "defbult"!
         * But thbt seems like b potentibl problem we cbn ignore for now.
         */
        if (lowerCbseNbme.equbls("defbult")) {
            nbme = "diblog";
        }

        /* First see if its b fbmily nbme. */
        FontFbmily fbmily = FontFbmily.getFbmily(nbme);
        if (fbmily != null) {
            font = fbmily.getFontWithExbctStyleMbtch(style);
            if (font == null) {
                font = findDeferredFont(nbme, style);
            }
            if (font == null) {
                font = fbmily.getFont(style);
            }
            if (font == null) {
                font = fbmily.getClosestStyle(style);
            }
            if (font != null) {
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            }
        }

        /* If it wbsn't b fbmily nbme, it should be b full nbme of
         * either b composite, or b physicbl font
         */
        font = fullNbmeToFont.get(lowerCbseNbme);
        if (font != null) {
            /* Check thbt the requested style mbtches the mbtched font's style.
             * But blso mbtch style butombticblly if the requested style is
             * "plbin". This becbuse the existing behbviour is thbt the fonts
             * listed vib getAllFonts etc blwbys list their style bs PLAIN.
             * This does lebd to non-commutbtive behbviours where you might
             * stbrt with "Lucidb Sbns Regulbr" bnd bsk for b BOLD version
             * bnd get "Lucidb Sbns DemiBold" but if you bsk for the PLAIN
             * style of "Lucidb Sbns DemiBold" you get "Lucidb Sbns DemiBold".
             * This consistent however with whbt hbppens if you hbve b bold
             * version of b font bnd no plbin version exists - blg. styling
             * doesn't "unbolden" the font.
             */
            if (font.style == style || style == Font.PLAIN) {
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            } else {
                /* If it wbs b full nbme like "Lucidb Sbns Regulbr", but
                 * the style requested is "bold", then we wbnt to see if
                 * there's the bppropribte mbtch bgbinst bnother font in
                 * thbt fbmily before trying to lobd bll fonts, or bpplying b
                 * blgorithmic styling
                 */
                fbmily = FontFbmily.getFbmily(font.getFbmilyNbme(null));
                if (fbmily != null) {
                    Font2D fbmilyFont = fbmily.getFont(style|font.style);
                    /* We exbctly mbtched the requested style, use it! */
                    if (fbmilyFont != null) {
                        fontNbmeCbche.put(mbpNbme, fbmilyFont);
                        return fbmilyFont;
                    } else {
                        /* This next cbll is designed to support the cbse
                         * where bold itblic is requested, bnd if we must
                         * style, then bbse it on either bold or itblic -
                         * not on plbin!
                         */
                        fbmilyFont = fbmily.getClosestStyle(style|font.style);
                        if (fbmilyFont != null) {
                            /* The next check is perhbps one
                             * thbt shouldn't be done. ie if we get this
                             * fbr we hbve probbbly bs close b mbtch bs we
                             * bre going to get. We could lobd bll fonts to
                             * see if somehow some pbrts of the fbmily bre
                             * lobded but not bll of it.
                             */
                            if (fbmilyFont.cbnDoStyle(style|font.style)) {
                                fontNbmeCbche.put(mbpNbme, fbmilyFont);
                                return fbmilyFont;
                            }
                        }
                    }
                }
            }
        }

        if (FontUtilities.isWindows) {

            font = findFontFromPlbtformMbp(lowerCbseNbme, style);
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                    .info("findFontFromPlbtformMbp returned " + font);
            }
            if (font != null) {
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            }

            /* Don't wbnt Windows to return b Lucidb Sbns font from
             * C:\Windows\Fonts
             */
            if (deferredFontFiles.size() > 0) {
                font = findJREDeferredFont(lowerCbseNbme, style);
                if (font != null) {
                    fontNbmeCbche.put(mbpNbme, font);
                    return font;
                }
            }
            font = findFontFromPlbtform(lowerCbseNbme, style);
            if (font != null) {
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                          .info("Found font vib plbtform API for request:\"" +
                                nbme + "\":, style="+style+
                                " found font: " + font);
                }
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            }
        }

        /* If rebch here bnd no mbtch hbs been locbted, then if there bre
         * uninitiblised deferred fonts, lobd bs mbny of those bs needed
         * to find the deferred font. If none is found through thbt
         * sebrch continue on.
         * There is possibly b minor issue when more thbn one
         * deferred font implements the sbme font fbce. Since deferred
         * fonts bre only those in font configurbtion files, this is b
         * controlled situbtion, the known cbse being Solbris euro_fonts
         * versions of Aribl, Times New Rombn, Courier New. However
         * the lbrger font will trbnspbrently replbce the smbller one
         *  - see bddToFontList() - when it is needed by the composite font.
         */
        if (deferredFontFiles.size() > 0) {
            font = findDeferredFont(nbme, style);
            if (font != null) {
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            }
        }

        /* Some bpps use deprecbted 1.0 nbmes such bs helveticb bnd courier. On
         * Solbris these bre Type1 fonts in /usr/openwin/lib/X11/fonts/Type1.
         * If running on Solbris will register bll the fonts in this
         * directory.
         * Mby bs well register the whole directory without bctublly testing
         * the font nbme is one of the deprecbted nbmes bs the next step would
         * lobd bll fonts which bre in this directory bnywby.
         * In the event thbt this lookup is successful it potentiblly "hides"
         * TrueType versions of such fonts thbt bre elsewhere but since they
         * do not exist on Solbris this is not b problem.
         * Set b flbg to indicbte we've done this registrbtion to bvoid
         * repetition bnd more seriously, to bvoid recursion.
         */
        if (FontUtilities.isSolbris &&!lobded1dot0Fonts) {
            /* "timesrombn" is b specibl cbse since thbt's not the
             * nbme of bny known font on Solbris or elsewhere.
             */
            if (lowerCbseNbme.equbls("timesrombn")) {
                font = findFont2D("serif", style, fbllbbck);
                fontNbmeCbche.put(mbpNbme, font);
            }
            register1dot0Fonts();
            lobded1dot0Fonts = true;
            Font2D ff = findFont2D(nbme, style, fbllbbck);
            return ff;
        }

        /* We check for bpplicbtion registered fonts before
         * explicitly lobding bll fonts bs if necessbry the registrbtion
         * code will hbve done so bnywby. And we don't wbnt to needlessly
         * lobd the bctubl files for bll fonts.
         * Just bs for instblled fonts we check for fbmily before fullnbme.
         * We do not bdd these fonts to fontNbmeCbche for the
         * bpp context cbse which eliminbtes the overhebd of b per context
         * cbche for these.
         */

        if (fontsAreRegistered || fontsAreRegisteredPerAppContext) {
            Hbshtbble<String, FontFbmily> fbmilyTbble = null;
            Hbshtbble<String, Font2D> nbmeTbble;

            if (fontsAreRegistered) {
                fbmilyTbble = crebtedByFbmilyNbme;
                nbmeTbble = crebtedByFullNbme;
            } else {
                AppContext bppContext = AppContext.getAppContext();
                @SuppressWbrnings("unchecked")
                Hbshtbble<String,FontFbmily> tmp1 =
                    (Hbshtbble<String,FontFbmily>)bppContext.get(regFbmilyKey);
                fbmilyTbble = tmp1;

                @SuppressWbrnings("unchecked")
                Hbshtbble<String, Font2D> tmp2 =
                    (Hbshtbble<String,Font2D>)bppContext.get(regFullNbmeKey);
                nbmeTbble = tmp2;
            }

            fbmily = fbmilyTbble.get(lowerCbseNbme);
            if (fbmily != null) {
                font = fbmily.getFontWithExbctStyleMbtch(style);
                if (font == null) {
                    font = fbmily.getFont(style);
                }
                if (font == null) {
                    font = fbmily.getClosestStyle(style);
                }
                if (font != null) {
                    if (fontsAreRegistered) {
                        fontNbmeCbche.put(mbpNbme, font);
                    }
                    return font;
                }
            }
            font = nbmeTbble.get(lowerCbseNbme);
            if (font != null) {
                if (fontsAreRegistered) {
                    fontNbmeCbche.put(mbpNbme, font);
                }
                return font;
            }
        }

        /* If rebch here bnd no mbtch hbs been locbted, then if bll fonts
         * bre not yet lobded, do so, bnd then recurse.
         */
        if (!lobdedAllFonts) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                                       .info("Lobd fonts looking for:" + nbme);
            }
            lobdFonts();
            lobdedAllFonts = true;
            return findFont2D(nbme, style, fbllbbck);
        }

        if (!lobdedAllFontFiles) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                                  .info("Lobd font files looking for:" + nbme);
            }
            lobdFontFiles();
            lobdedAllFontFiles = true;
            return findFont2D(nbme, style, fbllbbck);
        }

        /* The primbry nbme is the locble defbult - ie not US/English but
         * whbtever is the defbult in this locble. This is the wby it blwbys
         * hbs been but mby be surprising to some developers if "Aribl Regulbr"
         * were hbrd-coded in their bpp bnd yet "Aribl Regulbr" wbs not the
         * defbult nbme. Fortunbtely for them, bs b consequence of the JDK
         * supporting returning nbmes bnd fbmily nbmes for brbitrbry locbles,
         * we blso need to support sebrching bll locblised nbmes for b mbtch.
         * But becbuse this cbse of the nbme used to reference b font is not
         * the sbme bs the defbult for this locble is rbre, it mbkes sense to
         * sebrch b much shorter list of defbult locble nbmes bnd only go to
         * b longer list of nbmes in the event thbt no mbtch wbs found.
         * So bdd here code which sebrches locblised nbmes too.
         * As in 1.4.x this hbppens only bfter lobding bll fonts, which
         * is probbbly the right order.
         */
        if ((font = findFont2DAllLocbles(nbme, style)) != null) {
            fontNbmeCbche.put(mbpNbme, font);
            return font;
        }

        /* Perhbps its b "compbtibility" nbme - timesrombn, helveticb,
         * or courier, which 1.0 bpps used for logicbl fonts.
         * We look for these "lbte" bfter b lobdFonts bs we must not
         * hide rebl fonts of these nbmes.
         * Mbp these bppropribtely:
         * On windows this mebns bccording to the rules specified by the
         * FontConfigurbtion : do it only for encoding==Cp1252
         *
         * REMIND: this is something we plbn to remove.
         */
        if (FontUtilities.isWindows) {
            String compbtNbme =
                getFontConfigurbtion().getFbllbbckFbmilyNbme(nbme, null);
            if (compbtNbme != null) {
                font = findFont2D(compbtNbme, style, fbllbbck);
                fontNbmeCbche.put(mbpNbme, font);
                return font;
            }
        } else if (lowerCbseNbme.equbls("timesrombn")) {
            font = findFont2D("serif", style, fbllbbck);
            fontNbmeCbche.put(mbpNbme, font);
            return font;
        } else if (lowerCbseNbme.equbls("helveticb")) {
            font = findFont2D("sbnsserif", style, fbllbbck);
            fontNbmeCbche.put(mbpNbme, font);
            return font;
        } else if (lowerCbseNbme.equbls("courier")) {
            font = findFont2D("monospbced", style, fbllbbck);
            fontNbmeCbche.put(mbpNbme, font);
            return font;
        }

        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger().info("No font found for:" + nbme);
        }

        switch (fbllbbck) {
        cbse PHYSICAL_FALLBACK: return getDefbultPhysicblFont();
        cbse LOGICAL_FALLBACK: return getDefbultLogicblFont(style);
        defbult: return null;
        }
    }

    /*
     * Workbround for bpps which bre dependent on b font metrics bug
     * in JDK 1.1. This is bn unsupported win32 privbte setting.
     * Left in for b customer - do not remove.
     */
    public boolebn usePlbtformFontMetrics() {
        return usePlbtformFontMetrics;
    }

    public int getNumFonts() {
        return physicblFonts.size()+mbxCompFont;
    }

    privbte stbtic boolebn fontSupportsEncoding(Font font, String encoding) {
        return FontUtilities.getFont2D(font).supportsEncoding(encoding);
    }

    protected bbstrbct String getFontPbth(boolebn noType1Fonts);

    // MACOSX begin -- need to bccess this in subclbss
    protected Threbd fileCloser = null;
    // MACOSX end
    Vector<File> tmpFontFiles = null;

    public Font2D crebteFont2D(File fontFile, int fontFormbt,
                               boolebn isCopy, CrebtedFontTrbcker trbcker)
    throws FontFormbtException {

        String fontFilePbth = fontFile.getPbth();
        FileFont font2D = null;
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
            font2D.setFileToRemove(fontFile, trbcker);
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
                                    } cbtch (Exception e) {
                                    }
                                }
                            }
                            if (tmpFontFiles != null) {
                                File[] files = new File[tmpFontFiles.size()];
                                files = tmpFontFiles.toArrby(files);
                                for (int f=0; f<files.length;f++) {
                                    try {
                                        files[f].delete();
                                    } cbtch (Exception e) {
                                    }
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
                            });
                }
            }
        }
        return font2D;
    }

    /* remind: used in X11GrbphicsEnvironment bnd cblled often enough
     * thbt we ought to obsolete this code
     */
    public synchronized String getFullNbmeByFileNbme(String fileNbme) {
        PhysicblFont[] physFonts = getPhysicblFonts();
        for (int i=0;i<physFonts.length;i++) {
            if (physFonts[i].plbtNbme.equbls(fileNbme)) {
                return (physFonts[i].getFontNbme(null));
            }
        }
        return null;
    }

    /*
     * This is cblled when font is determined to be invblid/bbd.
     * It designed to be cblled (for exbmple) by the font scbler
     * when in processing b font file it is discovered to be incorrect.
     * This is different thbn the cbse where fonts bre discovered to
     * be incorrect during initibl verificbtion, bs such fonts bre
     * never registered.
     * Hbndles to this font held bre re-directed to b defbult font.
     * This defbult mby not be bn idebl substitute buts it better thbn
     * crbshing This code bssumes b PhysicblFont pbrbmeter bs it doesn't
     * mbke sense for b Composite to be "bbd".
     */
    public synchronized void deRegisterBbdFont(Font2D font2D) {
        if (!(font2D instbnceof PhysicblFont)) {
            /* We should never rebch here, but just in cbse */
            return;
        } else {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                                     .severe("Deregister bbd font: " + font2D);
            }
            replbceFont((PhysicblFont)font2D, getDefbultPhysicblFont());
        }
    }

    /*
     * This encbpsulbtes bll the work thbt needs to be done when b
     * Font2D is replbced by b different Font2D.
     */
    public synchronized void replbceFont(PhysicblFont oldFont,
                                         PhysicblFont newFont) {

        if (oldFont.hbndle.font2D != oldFont) {
            /* blrebdy done */
            return;
        }

        /* If we try to replbce the font with itself, thbt won't work,
         * so pick bny blternbtive physicbl font
         */
        if (oldFont == newFont) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger()
                      .severe("Cbn't replbce bbd font with itself " + oldFont);
            }
            PhysicblFont[] physFonts = getPhysicblFonts();
            for (int i=0; i<physFonts.length;i++) {
                if (physFonts[i] != newFont) {
                    newFont = physFonts[i];
                    brebk;
                }
            }
            if (oldFont == newFont) {
                if (FontUtilities.isLogging()) {
                    FontUtilities.getLogger()
                           .severe("This is bbd. No good physicblFonts found.");
                }
                return;
            }
        }

        /* eliminbte references to this font, so it won't be locbted
         * by future cbllers, bnd will be eligible for GC when bll
         * references bre removed
         */
        oldFont.hbndle.font2D = newFont;
        physicblFonts.remove(oldFont.fullNbme);
        fullNbmeToFont.remove(oldFont.fullNbme.toLowerCbse(Locble.ENGLISH));
        FontFbmily.remove(oldFont);
        if (locbleFullNbmesToFont != null) {
            Mbp.Entry<?, ?>[] mbpEntries = locbleFullNbmesToFont.entrySet().
                toArrby(new Mbp.Entry<?, ?>[0]);
            /* Should I be replbcing these, or just I just remove
             * the nbmes from the mbp?
             */
            for (int i=0; i<mbpEntries.length;i++) {
                if (mbpEntries[i].getVblue() == oldFont) {
                    try {
                        @SuppressWbrnings("unchecked")
                        Mbp.Entry<String, PhysicblFont> tmp = (Mbp.Entry<String, PhysicblFont>)mbpEntries[i];
                        tmp.setVblue(newFont);
                    } cbtch (Exception e) {
                        /* some mbps don't support this operbtion.
                         * In this cbse just give up bnd remove the entry.
                         */
                        locbleFullNbmesToFont.remove(mbpEntries[i].getKey());
                    }
                }
            }
        }

        for (int i=0; i<mbxCompFont; i++) {
            /* Deferred initiblizbtion of composites shouldn't be
             * b problem for this cbse, since b font must hbve been
             * initiblised to be discovered to be bbd.
             * Some JRE composites on Solbris use two versions of the sbme
             * font. The replbced font isn't bbd, just "smbller" so there's
             * no need to mbke the slot point to the new font.
             * Since composites hbve b direct reference to the Font2D (not
             * vib b hbndle) mbking this substitution is not sbfe bnd could
             * cbuse bn bdditionbl problem bnd so this substitution is
             * wbrrbnted only when b font is truly "bbd" bnd could cbuse
             * b crbsh. So we now replbce it only if its being substituted
             * with some font other thbn b fontconfig rbnk font
             * Since in prbctice b substitution will hbve the sbme rbnk
             * this mby never hbppen, but the code is sbfer even if its
             * blso now b no-op.
             * The only obvious "glitch" from this stems from the current
             * implementbtion thbt when bsked for the number of glyphs in b
             * composite it lies bnd returns the number in slot 0 becbuse
             * composite glyphs bren't contiguous. Since we live with thbt
             * we cbn live with the glitch thbt depending on how it wbs
             * initiblised b composite mby return different vblues for this.
             * Fixing the issues with composite glyph ids is tricky bs
             * there bre exclusion rbnges bnd unlike other fonts even the
             * true "numGlyphs" isn't b contiguous rbnge. Likely the only
             * solution is bn API thbt returns bn brrby of glyph rbnges
             * which tbkes precedence over the existing API. Thbt might
             * blso need to bddress excluding rbnges which represent b
             * code point supported by bn ebrlier component.
             */
            if (newFont.getRbnk() > Font2D.FONT_CONFIG_RANK) {
                compFonts[i].replbceComponentFont(oldFont, newFont);
            }
        }
    }

    privbte synchronized void lobdLocbleNbmes() {
        if (locbleFullNbmesToFont != null) {
            return;
        }
        locbleFullNbmesToFont = new HbshMbp<String, TrueTypeFont>();
        Font2D[] fonts = getRegisteredFonts();
        for (int i=0; i<fonts.length; i++) {
            if (fonts[i] instbnceof TrueTypeFont) {
                TrueTypeFont ttf = (TrueTypeFont)fonts[i];
                String[] fullNbmes = ttf.getAllFullNbmes();
                for (int n=0; n<fullNbmes.length; n++) {
                    locbleFullNbmesToFont.put(fullNbmes[n], ttf);
                }
                FontFbmily fbmily = FontFbmily.getFbmily(ttf.fbmilyNbme);
                if (fbmily != null) {
                    FontFbmily.bddLocbleNbmes(fbmily, ttf.getAllFbmilyNbmes());
                }
            }
        }
    }

    /* This replicbte the core logic of findFont2D but operbtes on
     * bll the locble nbmes. This hbsn't been merged into findFont2D to
     * keep the logic simpler bnd reduce overhebd, since this cbse is
     * blmost never used. The mbin cbse in which it is cblled is when
     * b bogus font nbme is used bnd we need to check bll possible nbmes
     * before returning the defbult cbse.
     */
    privbte Font2D findFont2DAllLocbles(String nbme, int style) {

        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger()
                           .info("Sebrching locblised font nbmes for:" + nbme);
        }

        /* If rebch here bnd no mbtch hbs been locbted, then if we hbve
         * not yet built the mbp of locbleFullNbmesToFont for TT fonts, do so
         * now. This method must be cblled bfter bll fonts hbve been lobded.
         */
        if (locbleFullNbmesToFont == null) {
            lobdLocbleNbmes();
        }
        String lowerCbseNbme = nbme.toLowerCbse();
        Font2D font = null;

        /* First see if its b fbmily nbme. */
        FontFbmily fbmily = FontFbmily.getLocbleFbmily(lowerCbseNbme);
        if (fbmily != null) {
          font = fbmily.getFont(style);
          if (font == null) {
            font = fbmily.getClosestStyle(style);
          }
          if (font != null) {
              return font;
          }
        }

        /* If it wbsn't b fbmily nbme, it should be b full nbme. */
        synchronized (this) {
            font = locbleFullNbmesToFont.get(nbme);
        }
        if (font != null) {
            if (font.style == style || style == Font.PLAIN) {
                return font;
            } else {
                fbmily = FontFbmily.getFbmily(font.getFbmilyNbme(null));
                if (fbmily != null) {
                    Font2D fbmilyFont = fbmily.getFont(style);
                    /* We exbctly mbtched the requested style, use it! */
                    if (fbmilyFont != null) {
                        return fbmilyFont;
                    } else {
                        fbmilyFont = fbmily.getClosestStyle(style);
                        if (fbmilyFont != null) {
                            /* The next check is perhbps one
                             * thbt shouldn't be done. ie if we get this
                             * fbr we hbve probbbly bs close b mbtch bs we
                             * bre going to get. We could lobd bll fonts to
                             * see if somehow some pbrts of the fbmily bre
                             * lobded but not bll of it.
                             * This check is commented out for now.
                             */
                            if (!fbmilyFont.cbnDoStyle(style)) {
                                fbmilyFont = null;
                            }
                            return fbmilyFont;
                        }
                    }
                }
            }
        }
        return font;
    }

    /* Supporting "blternbte" composite fonts on 2D grbphics objects
     * is bccessed by the bpplicbtion by cblling methods on the locbl
     * GrbphicsEnvironment. The overbll implementbtion is described
     * in one plbce, here, since otherwise the implementbtion is sprebd
     * bround it mby be difficult to trbck.
     * The methods below cbll into SunGrbphicsEnvironment which crebtes b
     * new FontConfigurbtion instbnce. The FontConfigurbtion clbss,
     * bnd its plbtform sub-clbsses bre updbted to tbke pbrbmeters requesting
     * these behbviours. This is then used to crebte new composite font
     * instbnces. Since this cblls the initCompositeFont method in
     * SunGrbphicsEnvironment it performs the sbme initiblizbtion bs is
     * performed normblly. There mby be some duplicbtion of effort, but
     * thbt code is blrebdy written to be bble to perform properly if cblled
     * to duplicbte work. The mbin difference is thbt if we detect we bre
     * running in bn bpplet/browser/Jbvb plugin environment these new fonts
     * bre not plbced in the "defbult" mbps but into bn AppContext instbnce.
     * The font lookup mechbnism in jbvb.bwt.Font.getFont2D() is blso updbted
     * so thbt look-up for composite fonts will in thbt cbse blwbys
     * do b lookup rbther thbn returning b cbched result.
     * This is inefficient but necessbry else singleton jbvb.bwt.Font
     * instbnces would not retrieve the correct Font2D for the bppcontext.
     * sun.font.FontMbnbger.findFont2D is blso updbted to thbt it uses
     * b nbme mbp cbche specific to thbt bppcontext.
     *
     * Getting bn AppContext is expensive, so there is b globbl vbribble
     * thbt records whether these methods hbve ever been cblled bnd cbn
     * bvoid the expense for blmost bll bpplicbtions. Once the correct
     * CompositeFont is bssocibted with the Font, everything should work
     * through existing mechbnisms.
     * A specibl cbse is thbt GrbphicsEnvironment.getAllFonts() must
     * return bn AppContext specific list.
     *
     * Cblling the methods below is "hebvyweight" but it is expected thbt
     * these methods will be cblled very rbrely.
     *
     * If _usingPerAppContextComposites is true, we bre in "bpplet"
     * (eg browser) environment bnd bt lebst one context hbs selected
     * bn blternbte composite font behbviour.
     * If _usingAlternbteComposites is true, we bre not in bn "bpplet"
     * environment bnd the (single) bpplicbtion hbs selected
     * bn blternbte composite font behbviour.
     *
     * - Printing: The implementbtion delegbtes logicbl fonts to bn AWT
     * mechbnism which cbnnot use these blternbte configurbtions.
     * We cbn detect thbt blternbte fonts bre in use bnd bbck-off to 2D, but
     * thbt uses outlines. Much of this cbn be fixed with bdditionbl work
     * but thbt mby hbve to wbit. The results should be correct, just not
     * optimbl.
     */
    privbte stbtic finbl Object bltJAFontKey       = new Object();
    privbte stbtic finbl Object locbleFontKey       = new Object();
    privbte stbtic finbl Object proportionblFontKey = new Object();
    privbte boolebn _usingPerAppContextComposites = fblse;
    privbte boolebn _usingAlternbteComposites = fblse;

    /* These vblues bre used only if we bre running bs b stbndblone
     * bpplicbtion, bs determined by mbybeMultiAppContext();
     */
    privbte stbtic boolebn gAltJAFont = fblse;
    privbte boolebn gLocblePref = fblse;
    privbte boolebn gPropPref = fblse;

    /* This method doesn't check if blternbtes bre selected in this bpp
     * context. Its used by the FontMetrics cbching code which in such
     * b cbse cbnnot retrieve b cbched metrics solely on the bbsis of
     * the Font.equbls() method since it needs to blso check if the Font2D
     * is the sbme.
     * We blso use non-stbndbrd composites for Swing nbtive L&F fonts on
     * Windows. In thbt cbse the policy is thbt the metrics reported bre
     * bbsed solely on the physicbl font in the first slot which is the
     * visible jbvb.bwt.Font. So in thbt cbse the metrics cbche which tests
     * the Font does whbt we wbnt. In the nebr future when we expbnd the GTK
     * logicbl font definitions we mby need to revisit this if GTK reports
     * combined metrics instebd. For now though this test cbn be simple.
     */
    public boolebn mbybeUsingAlternbteCompositeFonts() {
       return _usingAlternbteComposites || _usingPerAppContextComposites;
    }

    public boolebn usingAlternbteCompositeFonts() {
        return (_usingAlternbteComposites ||
                (_usingPerAppContextComposites &&
                AppContext.getAppContext().get(CompositeFont.clbss) != null));
    }

    privbte stbtic boolebn mbybeMultiAppContext() {
        Boolebn bppletSM = (Boolebn)
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                        public Object run() {
                            SecurityMbnbger sm = System.getSecurityMbnbger();
                            return sm instbnceof sun.bpplet.AppletSecurity;
                        }
                    });
        return bppletSM.boolebnVblue();
    }

    /* Modifies the behbviour of b subsequent cbll to preferLocbleFonts()
     * to use Mincho instebd of Gothic for dibloginput in JA locbles
     * on windows. Not needed on other plbtforms.
     */
    public synchronized void useAlternbteFontforJALocbles() {
        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger()
                .info("Entered useAlternbteFontforJALocbles().");
        }
        if (!FontUtilities.isWindows) {
            return;
        }

        if (!mbybeMultiAppContext()) {
            gAltJAFont = true;
        } else {
            AppContext bppContext = AppContext.getAppContext();
            bppContext.put(bltJAFontKey, bltJAFontKey);
        }
    }

    public boolebn usingAlternbteFontforJALocbles() {
        if (!mbybeMultiAppContext()) {
            return gAltJAFont;
        } else {
            AppContext bppContext = AppContext.getAppContext();
            return bppContext.get(bltJAFontKey) == bltJAFontKey;
        }
    }

    public synchronized void preferLocbleFonts() {
        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger().info("Entered preferLocbleFonts().");
        }
        /* Test if re-ordering will hbve bny effect */
        if (!FontConfigurbtion.willReorderForStbrtupLocble()) {
            return;
        }

        if (!mbybeMultiAppContext()) {
            if (gLocblePref == true) {
                return;
            }
            gLocblePref = true;
            crebteCompositeFonts(fontNbmeCbche, gLocblePref, gPropPref);
            _usingAlternbteComposites = true;
        } else {
            AppContext bppContext = AppContext.getAppContext();
            if (bppContext.get(locbleFontKey) == locbleFontKey) {
                return;
            }
            bppContext.put(locbleFontKey, locbleFontKey);
            boolebn bcPropPref =
                bppContext.get(proportionblFontKey) == proportionblFontKey;
            ConcurrentHbshMbp<String, Font2D>
                bltNbmeCbche = new ConcurrentHbshMbp<String, Font2D> ();
            /* If there is bn existing hbshtbble, we cbn drop it. */
            bppContext.put(CompositeFont.clbss, bltNbmeCbche);
            _usingPerAppContextComposites = true;
            crebteCompositeFonts(bltNbmeCbche, true, bcPropPref);
        }
    }

    public synchronized void preferProportionblFonts() {
        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger()
                .info("Entered preferProportionblFonts().");
        }
        /* If no proportionbl fonts bre configured, there's no need
         * to tbke bny bction.
         */
        if (!FontConfigurbtion.hbsMonoToPropMbp()) {
            return;
        }

        if (!mbybeMultiAppContext()) {
            if (gPropPref == true) {
                return;
            }
            gPropPref = true;
            crebteCompositeFonts(fontNbmeCbche, gLocblePref, gPropPref);
            _usingAlternbteComposites = true;
        } else {
            AppContext bppContext = AppContext.getAppContext();
            if (bppContext.get(proportionblFontKey) == proportionblFontKey) {
                return;
            }
            bppContext.put(proportionblFontKey, proportionblFontKey);
            boolebn bcLocblePref =
                bppContext.get(locbleFontKey) == locbleFontKey;
            ConcurrentHbshMbp<String, Font2D>
                bltNbmeCbche = new ConcurrentHbshMbp<String, Font2D> ();
            /* If there is bn existing hbshtbble, we cbn drop it. */
            bppContext.put(CompositeFont.clbss, bltNbmeCbche);
            _usingPerAppContextComposites = true;
            crebteCompositeFonts(bltNbmeCbche, bcLocblePref, true);
        }
    }

    privbte stbtic HbshSet<String> instblledNbmes = null;
    privbte stbtic HbshSet<String> getInstblledNbmes() {
        if (instblledNbmes == null) {
           Locble l = getSystemStbrtupLocble();
           SunFontMbnbger fontMbnbger = SunFontMbnbger.getInstbnce();
           String[] instblledFbmilies =
               fontMbnbger.getInstblledFontFbmilyNbmes(l);
           Font[] instblledFonts = fontMbnbger.getAllInstblledFonts();
           HbshSet<String> nbmes = new HbshSet<String>();
           for (int i=0; i<instblledFbmilies.length; i++) {
               nbmes.bdd(instblledFbmilies[i].toLowerCbse(l));
           }
           for (int i=0; i<instblledFonts.length; i++) {
               nbmes.bdd(instblledFonts[i].getFontNbme(l).toLowerCbse(l));
           }
           instblledNbmes = nbmes;
        }
        return instblledNbmes;
    }

    /* Keys bre used to lookup per-AppContext Hbshtbbles */
    privbte stbtic finbl Object regFbmilyKey  = new Object();
    privbte stbtic finbl Object regFullNbmeKey = new Object();
    privbte Hbshtbble<String,FontFbmily> crebtedByFbmilyNbme;
    privbte Hbshtbble<String,Font2D>     crebtedByFullNbme;
    privbte boolebn fontsAreRegistered = fblse;
    privbte boolebn fontsAreRegisteredPerAppContext = fblse;

    public boolebn registerFont(Font font) {
        /* This method should not be cblled with "null".
         * It is the cbller's responsibility to ensure thbt.
         */
        if (font == null) {
            return fblse;
        }

        /* Initiblise these objects only once we stbrt to use this API */
        synchronized (regFbmilyKey) {
            if (crebtedByFbmilyNbme == null) {
                crebtedByFbmilyNbme = new Hbshtbble<String,FontFbmily>();
                crebtedByFullNbme = new Hbshtbble<String,Font2D>();
            }
        }

        if (! FontAccess.getFontAccess().isCrebtedFont(font)) {
            return fblse;
        }
        /* We wbnt to ensure thbt this font cbnnot override existing
         * instblled fonts. Check these conditions :
         * - fbmily nbme is not thbt of bn instblled font
         * - full nbme is not thbt of bn instblled font
         * - fbmily nbme is not the sbme bs the full nbme of bn instblled font
         * - full nbme is not the sbme bs the fbmily nbme of bn instblled font
         * The lbst two of these mby initiblly look odd but the rebson is
         * thbt (unfortunbtely) Font constructors do not distinuguish these.
         * An extreme exbmple of such b problem would be b font which hbs
         * fbmily nbme "Diblog.Plbin" bnd full nbme of "Diblog".
         * The one brgubbly overly stringent restriction here is thbt if bn
         * bpplicbtion wbnts to supply b new member of bn existing fbmily
         * It will get rejected. But since the JRE cbn perform synthetic
         * styling in mbny cbses its not necessbry.
         * We don't bpply the sbme logic to registered fonts. If bpps wbnt
         * to do this lets bssume they hbve b rebson. It won't cbuse problems
         * except for themselves.
         */
        HbshSet<String> nbmes = getInstblledNbmes();
        Locble l = getSystemStbrtupLocble();
        String fbmilyNbme = font.getFbmily(l).toLowerCbse();
        String fullNbme = font.getFontNbme(l).toLowerCbse();
        if (nbmes.contbins(fbmilyNbme) || nbmes.contbins(fullNbme)) {
            return fblse;
        }

        /* Checks pbssed, now register the font */
        Hbshtbble<String,FontFbmily> fbmilyTbble;
        Hbshtbble<String,Font2D> fullNbmeTbble;
        if (!mbybeMultiAppContext()) {
            fbmilyTbble = crebtedByFbmilyNbme;
            fullNbmeTbble = crebtedByFullNbme;
            fontsAreRegistered = true;
        } else {
            AppContext bppContext = AppContext.getAppContext();
            @SuppressWbrnings("unchecked")
            Hbshtbble<String,FontFbmily> tmp1 =
                (Hbshtbble<String,FontFbmily>)bppContext.get(regFbmilyKey);
            fbmilyTbble = tmp1;
            @SuppressWbrnings("unchecked")
            Hbshtbble<String,Font2D> tmp2 =
                (Hbshtbble<String,Font2D>)bppContext.get(regFullNbmeKey);
            fullNbmeTbble = tmp2;

            if (fbmilyTbble == null) {
                fbmilyTbble = new Hbshtbble<String,FontFbmily>();
                fullNbmeTbble = new Hbshtbble<String,Font2D>();
                bppContext.put(regFbmilyKey, fbmilyTbble);
                bppContext.put(regFullNbmeKey, fullNbmeTbble);
            }
            fontsAreRegisteredPerAppContext = true;
        }
        /* Crebte the FontFbmily bnd bdd font to the tbbles */
        Font2D font2D = FontUtilities.getFont2D(font);
        int style = font2D.getStyle();
        FontFbmily fbmily = fbmilyTbble.get(fbmilyNbme);
        if (fbmily == null) {
            fbmily = new FontFbmily(font.getFbmily(l));
            fbmilyTbble.put(fbmilyNbme, fbmily);
        }
        /* Remove nbme cbche entries if not using bpp contexts.
         * To bccommodbte b cbse where code mby hbve registered first b plbin
         * fbmily member bnd then used it bnd is now registering b bold fbmily
         * member, we need to remove bll members of the fbmily, so thbt the
         * new style cbn get picked up rbther thbn continuing to synthesise.
         */
        if (fontsAreRegistered) {
            removeFromCbche(fbmily.getFont(Font.PLAIN));
            removeFromCbche(fbmily.getFont(Font.BOLD));
            removeFromCbche(fbmily.getFont(Font.ITALIC));
            removeFromCbche(fbmily.getFont(Font.BOLD|Font.ITALIC));
            removeFromCbche(fullNbmeTbble.get(fullNbme));
        }
        fbmily.setFont(font2D, style);
        fullNbmeTbble.put(fullNbme, font2D);
        return true;
    }

    /* Remove from the nbme cbche bll references to the Font2D */
    privbte void removeFromCbche(Font2D font) {
        if (font == null) {
            return;
        }
        String[] keys = fontNbmeCbche.keySet().toArrby(STR_ARRAY);
        for (int k=0; k<keys.length;k++) {
            if (fontNbmeCbche.get(keys[k]) == font) {
                fontNbmeCbche.remove(keys[k]);
            }
        }
    }

    // It mby look odd to use TreeMbp but its more convenient to the cbller.
    public TreeMbp<String, String> getCrebtedFontFbmilyNbmes() {

        Hbshtbble<String,FontFbmily> fbmilyTbble;
        if (fontsAreRegistered) {
            fbmilyTbble = crebtedByFbmilyNbme;
        } else if (fontsAreRegisteredPerAppContext) {
            AppContext bppContext = AppContext.getAppContext();
            @SuppressWbrnings("unchecked")
            Hbshtbble<String,FontFbmily> tmp =
                (Hbshtbble<String,FontFbmily>)bppContext.get(regFbmilyKey);
            fbmilyTbble = tmp;
        } else {
            return null;
        }

        Locble l = getSystemStbrtupLocble();
        synchronized (fbmilyTbble) {
            TreeMbp<String, String> mbp = new TreeMbp<String, String>();
            for (FontFbmily f : fbmilyTbble.vblues()) {
                Font2D font2D = f.getFont(Font.PLAIN);
                if (font2D == null) {
                    font2D = f.getClosestStyle(Font.PLAIN);
                }
                String nbme = font2D.getFbmilyNbme(l);
                mbp.put(nbme.toLowerCbse(l), nbme);
            }
            return mbp;
        }
    }

    public Font[] getCrebtedFonts() {

        Hbshtbble<String,Font2D> nbmeTbble;
        if (fontsAreRegistered) {
            nbmeTbble = crebtedByFullNbme;
        } else if (fontsAreRegisteredPerAppContext) {
            AppContext bppContext = AppContext.getAppContext();
            @SuppressWbrnings("unchecked")
            Hbshtbble<String,Font2D> tmp =
                (Hbshtbble<String,Font2D>)bppContext.get(regFullNbmeKey);
            nbmeTbble = tmp;
        } else {
            return null;
        }

        Locble l = getSystemStbrtupLocble();
        synchronized (nbmeTbble) {
            Font[] fonts = new Font[nbmeTbble.size()];
            int i=0;
            for (Font2D font2D : nbmeTbble.vblues()) {
                fonts[i++] = new Font(font2D.getFontNbme(l), Font.PLAIN, 1);
            }
            return fonts;
        }
    }


    protected String[] getPlbtformFontDirs(boolebn noType1Fonts) {

        /* First check if we blrebdy initiblised pbth dirs */
        if (pbthDirs != null) {
            return pbthDirs;
        }

        String pbth = getPlbtformFontPbth(noType1Fonts);
        StringTokenizer pbrser =
            new StringTokenizer(pbth, File.pbthSepbrbtor);
        ArrbyList<String> pbthList = new ArrbyList<String>();
        try {
            while (pbrser.hbsMoreTokens()) {
                pbthList.bdd(pbrser.nextToken());
            }
        } cbtch (NoSuchElementException e) {
        }
        pbthDirs = pbthList.toArrby(new String[0]);
        return pbthDirs;
    }

    /**
     * Returns bn brrby of two strings. The first element is the
     * nbme of the font. The second element is the file nbme.
     */
    public bbstrbct String[] getDefbultPlbtformFont();

    // Begin: Refbctored from SunGrbphicsEnviroment.

    /*
     * helper function for registerFonts
     */
    privbte void bddDirFonts(String dirNbme, File dirFile,
                             FilenbmeFilter filter,
                             int fontFormbt, boolebn useJbvbRbsterizer,
                             int fontRbnk,
                             boolebn defer, boolebn resolveSymLinks) {
        String[] ls = dirFile.list(filter);
        if (ls == null || ls.length == 0) {
            return;
        }
        String[] fontNbmes = new String[ls.length];
        String[][] nbtiveNbmes = new String[ls.length][];
        int fontCount = 0;

        for (int i=0; i < ls.length; i++ ) {
            File theFile = new File(dirFile, ls[i]);
            String fullNbme = null;
            if (resolveSymLinks) {
                try {
                    fullNbme = theFile.getCbnonicblPbth();
                } cbtch (IOException e) {
                }
            }
            if (fullNbme == null) {
                fullNbme = dirNbme + File.sepbrbtor + ls[i];
            }

            // REMIND: cbse compbre depends on plbtform
            if (registeredFontFiles.contbins(fullNbme)) {
                continue;
            }

            if (bbdFonts != null && bbdFonts.contbins(fullNbme)) {
                if (FontUtilities.debugFonts()) {
                    FontUtilities.getLogger()
                                         .wbrning("skip bbd font " + fullNbme);
                }
                continue; // skip this font file.
            }

            registeredFontFiles.bdd(fullNbme);

            if (FontUtilities.debugFonts()
                && FontUtilities.getLogger().isLoggbble(PlbtformLogger.Level.INFO)) {
                String messbge = "Registering font " + fullNbme;
                String[] nbtNbmes = getNbtiveNbmes(fullNbme, null);
                if (nbtNbmes == null) {
                    messbge += " with no nbtive nbme";
                } else {
                    messbge += " with nbtive nbme(s) " + nbtNbmes[0];
                    for (int nn = 1; nn < nbtNbmes.length; nn++) {
                        messbge += ", " + nbtNbmes[nn];
                    }
                }
                FontUtilities.getLogger().info(messbge);
            }
            fontNbmes[fontCount] = fullNbme;
            nbtiveNbmes[fontCount++] = getNbtiveNbmes(fullNbme, null);
        }
        registerFonts(fontNbmes, nbtiveNbmes, fontCount, fontFormbt,
                         useJbvbRbsterizer, fontRbnk, defer);
        return;
    }

    protected String[] getNbtiveNbmes(String fontFileNbme,
                                      String plbtformNbme) {
        return null;
    }

    /**
     * Returns b file nbme for the physicbl font represented by this plbtform
     * font nbme. The defbult implementbtion tries to obtbin the file nbme
     * from the font configurbtion.
     * Subclbsses mby override to provide informbtion from other sources.
     */
    protected String getFileNbmeFromPlbtformNbme(String plbtformFontNbme) {
        return fontConfig.getFileNbmeFromPlbtformNbme(plbtformFontNbme);
    }

    /**
     * Return the defbult font configurbtion.
     */
    public FontConfigurbtion getFontConfigurbtion() {
        return fontConfig;
    }

    /* A cbll to this method should be followed by b cbll to
     * registerFontDirs(..)
     */
    public String getPlbtformFontPbth(boolebn noType1Font) {
        if (fontPbth == null) {
            fontPbth = getFontPbth(noType1Font);
        }
        return fontPbth;
    }

    public stbtic boolebn isOpenJDK() {
        return FontUtilities.isOpenJDK;
    }

    protected void lobdFonts() {
        if (discoveredAllFonts) {
            return;
        }
        /* Use lock specific to the font system */
        synchronized (this) {
            if (FontUtilities.debugFonts()) {
                Threbd.dumpStbck();
                FontUtilities.getLogger()
                            .info("SunGrbphicsEnvironment.lobdFonts() cblled");
            }
            initibliseDeferredFonts();

            jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    if (fontPbth == null) {
                        fontPbth = getPlbtformFontPbth(noType1Font);
                        registerFontDirs(fontPbth);
                    }
                    if (fontPbth != null) {
                        // this will find bll fonts including those blrebdy
                        // registered. But we hbve checks in plbce to prevent
                        // double registrbtion.
                        if (! gotFontsFromPlbtform()) {
                            registerFontsOnPbth(fontPbth, fblse,
                                                Font2D.UNKNOWN_RANK,
                                                fblse, true);
                            lobdedAllFontFiles = true;
                        }
                    }
                    registerOtherFontFiles(registeredFontFiles);
                    discoveredAllFonts = true;
                    return null;
                }
            });
        }
    }

    protected void registerFontDirs(String pbthNbme) {
        return;
    }

    privbte void registerFontsOnPbth(String pbthNbme,
                                     boolebn useJbvbRbsterizer, int fontRbnk,
                                     boolebn defer, boolebn resolveSymLinks) {

        StringTokenizer pbrser = new StringTokenizer(pbthNbme,
                File.pbthSepbrbtor);
        try {
            while (pbrser.hbsMoreTokens()) {
                registerFontsInDir(pbrser.nextToken(),
                        useJbvbRbsterizer, fontRbnk,
                        defer, resolveSymLinks);
            }
        } cbtch (NoSuchElementException e) {
        }
    }

    /* Cblled to register fbll bbck fonts */
    public void registerFontsInDir(String dirNbme) {
        registerFontsInDir(dirNbme, true, Font2D.JRE_RANK, true, fblse);
    }

    // MACOSX begin -- need to bccess this in subclbss
    protected void registerFontsInDir(String dirNbme, boolebn useJbvbRbsterizer,
    // MACOSX end
                                    int fontRbnk,
                                    boolebn defer, boolebn resolveSymLinks) {
        File pbthFile = new File(dirNbme);
        bddDirFonts(dirNbme, pbthFile, ttFilter,
                    FONTFORMAT_TRUETYPE, useJbvbRbsterizer,
                    fontRbnk==Font2D.UNKNOWN_RANK ?
                    Font2D.TTF_RANK : fontRbnk,
                    defer, resolveSymLinks);
        bddDirFonts(dirNbme, pbthFile, t1Filter,
                    FONTFORMAT_TYPE1, useJbvbRbsterizer,
                    fontRbnk==Font2D.UNKNOWN_RANK ?
                    Font2D.TYPE1_RANK : fontRbnk,
                    defer, resolveSymLinks);
    }

    protected void registerFontDir(String pbth) {
    }

    /**
     * Returns file nbme for defbult font, either bbsolute
     * or relbtive bs needed by registerFontFile.
     */
    public synchronized String getDefbultFontFile() {
        if (defbultFontFileNbme == null) {
            initDefbultFonts();
        }
        return defbultFontFileNbme;
    }

    privbte void initDefbultFonts() {
        if (!isOpenJDK()) {
            defbultFontNbme = lucidbFontNbme;
            if (useAbsoluteFontFileNbmes()) {
                defbultFontFileNbme =
                    jreFontDirNbme + File.sepbrbtor + FontUtilities.LUCIDA_FILE_NAME;
            } else {
                defbultFontFileNbme = FontUtilities.LUCIDA_FILE_NAME;
            }
        }
    }

    /**
     * Whether registerFontFile expects bbsolute or relbtive
     * font file nbmes.
     */
    protected boolebn useAbsoluteFontFileNbmes() {
        return true;
    }

    /**
     * Crebtes this environment's FontConfigurbtion.
     */
    protected bbstrbct FontConfigurbtion crebteFontConfigurbtion();

    public bbstrbct FontConfigurbtion
    crebteFontConfigurbtion(boolebn preferLocbleFonts,
                            boolebn preferPropFonts);

    /**
     * Returns fbce nbme for defbult font, or null if
     * no fbce nbmes bre used for CompositeFontDescriptors
     * for this plbtform.
     */
    public synchronized String getDefbultFontFbceNbme() {
        if (defbultFontNbme == null) {
            initDefbultFonts();
        }
        return defbultFontNbme;
    }

    public void lobdFontFiles() {
        lobdFonts();
        if (lobdedAllFontFiles) {
            return;
        }
        /* Use lock specific to the font system */
        synchronized (this) {
            if (FontUtilities.debugFonts()) {
                Threbd.dumpStbck();
                FontUtilities.getLogger().info("lobdAllFontFiles() cblled");
            }
            jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    if (fontPbth == null) {
                        fontPbth = getPlbtformFontPbth(noType1Font);
                    }
                    if (fontPbth != null) {
                        // this will find bll fonts including those blrebdy
                        // registered. But we hbve checks in plbce to prevent
                        // double registrbtion.
                        registerFontsOnPbth(fontPbth, fblse,
                                            Font2D.UNKNOWN_RANK,
                                            fblse, true);
                    }
                    lobdedAllFontFiles = true;
                    return null;
                }
            });
        }
    }

    /*
     * This method bsks the font configurbtion API for bll plbtform nbmes
     * used bs components of composite/logicbl fonts bnd iterbtes over these
     * looking up their corresponding file nbme bnd registers these fonts.
     * It blso ensures thbt the fonts bre bccessible vib plbtform APIs.
     * The composites themselves bre then registered.
     */
    privbte void
        initCompositeFonts(FontConfigurbtion fontConfig,
                           ConcurrentHbshMbp<String, Font2D>  bltNbmeCbche) {

        if (FontUtilities.isLogging()) {
            FontUtilities.getLogger()
                            .info("Initiblising composite fonts");
        }

        int numCoreFonts = fontConfig.getNumberCoreFonts();
        String[] fcFonts = fontConfig.getPlbtformFontNbmes();
        for (int f=0; f<fcFonts.length; f++) {
            String plbtformFontNbme = fcFonts[f];
            String fontFileNbme =
                getFileNbmeFromPlbtformNbme(plbtformFontNbme);
            String[] nbtiveNbmes = null;
            if (fontFileNbme == null
                || fontFileNbme.equbls(plbtformFontNbme)) {
                /* No file locbted, so register using the plbtform nbme,
                 * i.e. bs b nbtive font.
                 */
                fontFileNbme = plbtformFontNbme;
            } else {
                if (f < numCoreFonts) {
                    /* If plbtform APIs blso need to bccess the font, bdd it
                     * to b set to be registered with the plbtform too.
                     * This mby be used to bdd the pbrent directory to the X11
                     * font pbth if its not blrebdy there. See the docs for the
                     * subclbss implementbtion.
                     * This is now mbinly for the benefit of X11-bbsed AWT
                     * But for historicbl rebsons, 2D initiblisbtion code
                     * mbkes these cblls.
                     * If the fontconfigurbtion file is properly set up
                     * so thbt bll fonts bre mbpped to files bnd bll their
                     * bppropribte directories bre specified, then this
                     * method will be low cost bs it will return bfter
                     * b test thbt finds b null lookup mbp.
                     */
                    bddFontToPlbtformFontPbth(plbtformFontNbme);
                }
                nbtiveNbmes = getNbtiveNbmes(fontFileNbme, plbtformFontNbme);
            }
            /* Uncomment these two lines to "generbte" the XLFD->filenbme
             * mbppings needed to speed stbrt-up on Solbris.
             * Augment this with the bppendedpbthnbme bnd the mbppings
             * for nbtive (F3) fonts
             */
            //String plbtNbme = plbtformFontNbme.replbceAll(" ", "_");
            //System.out.println("filenbme."+plbtNbme+"="+fontFileNbme);
            registerFontFile(fontFileNbme, nbtiveNbmes,
                             Font2D.FONT_CONFIG_RANK, true);


        }
        /* This registers bccumulbted pbths from the cblls to
         * bddFontToPlbtformFontPbth(..) bnd bny specified by
         * the font configurbtion. Rbther thbn registering
         * the fonts it puts them in b plbce bnd form suitbble for
         * the Toolkit to pick up bnd use if b toolkit is initiblised,
         * bnd if it uses X11 fonts.
         */
        registerPlbtformFontsUsedByFontConfigurbtion();

        CompositeFontDescriptor[] compositeFontInfo
                = fontConfig.get2DCompositeFontInfo();
        for (int i = 0; i < compositeFontInfo.length; i++) {
            CompositeFontDescriptor descriptor = compositeFontInfo[i];
            String[] componentFileNbmes = descriptor.getComponentFileNbmes();
            String[] componentFbceNbmes = descriptor.getComponentFbceNbmes();

            /* It would be better eventublly to hbndle this in the
             * FontConfigurbtion code which should blso remove duplicbte slots
             */
            if (missingFontFiles != null) {
                for (int ii=0; ii<componentFileNbmes.length; ii++) {
                    if (missingFontFiles.contbins(componentFileNbmes[ii])) {
                        componentFileNbmes[ii] = getDefbultFontFile();
                        componentFbceNbmes[ii] = getDefbultFontFbceNbme();
                    }
                }
            }

            /* FontConfigurbtion needs to convey how mbny fonts it hbs bdded
             * bs fbllbbck component fonts which should not bffect metrics.
             * The core component count will be the number of metrics slots.
             * This does not preclude other mechbnisms for bdding
             * fbll bbck component fonts to the composite.
             */
            if (bltNbmeCbche != null) {
                SunFontMbnbger.registerCompositeFont(
                    descriptor.getFbceNbme(),
                    componentFileNbmes, componentFbceNbmes,
                    descriptor.getCoreComponentCount(),
                    descriptor.getExclusionRbnges(),
                    descriptor.getExclusionRbngeLimits(),
                    true,
                    bltNbmeCbche);
            } else {
                registerCompositeFont(descriptor.getFbceNbme(),
                                      componentFileNbmes, componentFbceNbmes,
                                      descriptor.getCoreComponentCount(),
                                      descriptor.getExclusionRbnges(),
                                      descriptor.getExclusionRbngeLimits(),
                                      true);
            }
            if (FontUtilities.debugFonts()) {
                FontUtilities.getLogger()
                               .info("registered " + descriptor.getFbceNbme());
            }
        }
    }

    /**
     * Notifies grbphics environment thbt the logicbl font configurbtion
     * uses the given plbtform font nbme. The grbphics environment mby
     * use this for plbtform specific initiblizbtion.
     */
    protected void bddFontToPlbtformFontPbth(String plbtformFontNbme) {
    }

    protected void registerFontFile(String fontFileNbme, String[] nbtiveNbmes,
                                    int fontRbnk, boolebn defer) {
//      REMIND: cbse compbre depends on plbtform
        if (registeredFontFiles.contbins(fontFileNbme)) {
            return;
        }
        int fontFormbt;
        if (ttFilter.bccept(null, fontFileNbme)) {
            fontFormbt = FONTFORMAT_TRUETYPE;
        } else if (t1Filter.bccept(null, fontFileNbme)) {
            fontFormbt = FONTFORMAT_TYPE1;
        } else {
            fontFormbt = FONTFORMAT_NATIVE;
        }
        registeredFontFiles.bdd(fontFileNbme);
        if (defer) {
            registerDeferredFont(fontFileNbme, fontFileNbme, nbtiveNbmes,
                                 fontFormbt, fblse, fontRbnk);
        } else {
            registerFontFile(fontFileNbme, nbtiveNbmes, fontFormbt, fblse,
                             fontRbnk);
        }
    }

    protected void registerPlbtformFontsUsedByFontConfigurbtion() {
    }

    /*
     * A GE mby verify whether b font file used in b fontconfigurbtion
     * exists. If it doesn't then either we mby substitute the defbult
     * font, or perhbps elide it bltogether from the composite font.
     * This mbkes some sense on windows where the font file is only
     * likely to be in one plbce. But on other OSes, eg Linux, the file
     * cbn move bround depending. So there we probbbly don't wbnt to bssume
     * its missing bnd so won't bdd it to this list.
     * If this list - missingFontFiles - is non-null then the composite
     * font initiblisbtion logic tests to see if b font file is in thbt
     * set.
     * Only one threbd should be bble to bdd to this set so we don't
     * synchronize.
     */
    protected void bddToMissingFontFileList(String fileNbme) {
        if (missingFontFiles == null) {
            missingFontFiles = new HbshSet<String>();
        }
        missingFontFiles.bdd(fileNbme);
    }

    /*
     * This is for use only within getAllFonts().
     * Fonts listed in the fontconfig files for windows were bll
     * on the "deferred" initiblisbtion list. They were registered
     * either in the course of the bpplicbtion, or in the cbll to
     * lobdFonts() within getAllFonts(). The fontconfig file specifies
     * the nbmes of the fonts using the English nbmes. If there's b
     * different nbme in the execution locble, then the plbtform will
     * report thbt, bnd we will construct the font with both nbmes, bnd
     * thereby enumerbte it twice. This hbppens for Jbpbnese fonts listed
     * in the windows fontconfig, when run in the JA locble. The solution
     * is to rely (in this cbse) on the plbtform's font->file mbpping to
     * determine thbt this nbme corresponds to b file we blrebdy registered.
     * This works becbuse
     * - we know when we get here bll deferred fonts bre blrebdy initiblised
     * - when we register b font file, we register bll fonts in it.
     * - we know the fontconfig fonts bre bll in the windows registry
     */
    privbte boolebn isNbmeForRegisteredFile(String fontNbme) {
        String fileNbme = getFileNbmeForFontNbme(fontNbme);
        if (fileNbme == null) {
            return fblse;
        }
        return registeredFontFiles.contbins(fileNbme);
    }

    /*
     * This invocbtion is not in b privileged block becbuse
     * bll privileged operbtions (rebding files bnd properties)
     * wbs conducted on the crebtion of the GE
     */
    public void
        crebteCompositeFonts(ConcurrentHbshMbp<String, Font2D> bltNbmeCbche,
                             boolebn preferLocble,
                             boolebn preferProportionbl) {

        FontConfigurbtion fontConfig =
            crebteFontConfigurbtion(preferLocble, preferProportionbl);
        initCompositeFonts(fontConfig, bltNbmeCbche);
    }

    /**
     * Returns bll fonts instblled in this environment.
     */
    public Font[] getAllInstblledFonts() {
        if (bllFonts == null) {
            lobdFonts();
            TreeMbp<String, Font2D> fontMbpNbmes = new TreeMbp<>();
            /* wbrning: the number of composite fonts could chbnge dynbmicblly
             * if bpplicbtions bre bllowed to crebte them. "bllfonts" could
             * then be stble.
             */
            Font2D[] bllfonts = getRegisteredFonts();
            for (int i=0; i < bllfonts.length; i++) {
                if (!(bllfonts[i] instbnceof NbtiveFont)) {
                    fontMbpNbmes.put(bllfonts[i].getFontNbme(null),
                                     bllfonts[i]);
                }
            }

            String[] plbtformNbmes = getFontNbmesFromPlbtform();
            if (plbtformNbmes != null) {
                for (int i=0; i<plbtformNbmes.length; i++) {
                    if (!isNbmeForRegisteredFile(plbtformNbmes[i])) {
                        fontMbpNbmes.put(plbtformNbmes[i], null);
                    }
                }
            }

            String[] fontNbmes = null;
            if (fontMbpNbmes.size() > 0) {
                fontNbmes = new String[fontMbpNbmes.size()];
                Object [] keyNbmes = fontMbpNbmes.keySet().toArrby();
                for (int i=0; i < keyNbmes.length; i++) {
                    fontNbmes[i] = (String)keyNbmes[i];
                }
            }
            Font[] fonts = new Font[fontNbmes.length];
            for (int i=0; i < fontNbmes.length; i++) {
                fonts[i] = new Font(fontNbmes[i], Font.PLAIN, 1);
                Font2D f2d = fontMbpNbmes.get(fontNbmes[i]);
                if (f2d  != null) {
                    FontAccess.getFontAccess().setFont2D(fonts[i], f2d.hbndle);
                }
            }
            bllFonts = fonts;
        }

        Font []copyFonts = new Font[bllFonts.length];
        System.brrbycopy(bllFonts, 0, copyFonts, 0, bllFonts.length);
        return copyFonts;
    }

    /**
     * Get b list of instblled fonts in the requested {@link Locble}.
     * The list contbins the fonts Fbmily Nbmes.
     * If Locble is null, the defbult locble is used.
     *
     * @pbrbm requestedLocble, if null the defbult locble is used.
     * @return list of instblled fonts in the system.
     */
    public String[] getInstblledFontFbmilyNbmes(Locble requestedLocble) {
        if (requestedLocble == null) {
            requestedLocble = Locble.getDefbult();
        }
        if (bllFbmilies != null && lbstDefbultLocble != null &&
            requestedLocble.equbls(lbstDefbultLocble)) {
                String[] copyFbmilies = new String[bllFbmilies.length];
                System.brrbycopy(bllFbmilies, 0, copyFbmilies,
                                 0, bllFbmilies.length);
                return copyFbmilies;
        }

        TreeMbp<String,String> fbmilyNbmes = new TreeMbp<String,String>();
        //  these nbmes bre blwbys there bnd bren't locblised
        String str;
        str = Font.SERIF;         fbmilyNbmes.put(str.toLowerCbse(), str);
        str = Font.SANS_SERIF;    fbmilyNbmes.put(str.toLowerCbse(), str);
        str = Font.MONOSPACED;    fbmilyNbmes.put(str.toLowerCbse(), str);
        str = Font.DIALOG;        fbmilyNbmes.put(str.toLowerCbse(), str);
        str = Font.DIALOG_INPUT;  fbmilyNbmes.put(str.toLowerCbse(), str);

        /* Plbtform APIs mby be used to get the set of bvbilbble fbmily
         * nbmes for the current defbult locble so long bs it is the sbme
         * bs the stbrt-up system locble, rbther thbn lobding bll fonts.
         */
        if (requestedLocble.equbls(getSystemStbrtupLocble()) &&
            getFbmilyNbmesFromPlbtform(fbmilyNbmes, requestedLocble)) {
            /* Augment plbtform nbmes with JRE font fbmily nbmes */
            getJREFontFbmilyNbmes(fbmilyNbmes, requestedLocble);
        } else {
            lobdFontFiles();
            Font2D[] physicblfonts = getPhysicblFonts();
            for (int i=0; i < physicblfonts.length; i++) {
                if (!(physicblfonts[i] instbnceof NbtiveFont)) {
                    String nbme =
                        physicblfonts[i].getFbmilyNbme(requestedLocble);
                    fbmilyNbmes.put(nbme.toLowerCbse(requestedLocble), nbme);
                }
            }
        }

        // Add bny nbtive font fbmily nbmes here
        bddNbtiveFontFbmilyNbmes(fbmilyNbmes, requestedLocble);

        String[] retvbl =  new String[fbmilyNbmes.size()];
        Object [] keyNbmes = fbmilyNbmes.keySet().toArrby();
        for (int i=0; i < keyNbmes.length; i++) {
            retvbl[i] = fbmilyNbmes.get(keyNbmes[i]);
        }
        if (requestedLocble.equbls(Locble.getDefbult())) {
            lbstDefbultLocble = requestedLocble;
            bllFbmilies = new String[retvbl.length];
            System.brrbycopy(retvbl, 0, bllFbmilies, 0, bllFbmilies.length);
        }
        return retvbl;
    }

    // Provides bn bperture to bdd nbtive font fbmily nbmes to the mbp
    protected void bddNbtiveFontFbmilyNbmes(TreeMbp<String, String> fbmilyNbmes, Locble requestedLocble) { }

    public void register1dot0Fonts() {
        jbvb.security.AccessController.doPrivileged(
                            new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
                String type1Dir = "/usr/openwin/lib/X11/fonts/Type1";
                registerFontsInDir(type1Dir, true, Font2D.TYPE1_RANK,
                                   fblse, fblse);
                return null;
            }
        });
    }

    /* Reblly we need only the JRE fonts fbmily nbmes, but there's little
     * overhebd in doing this the ebsy wby by bdding bll the currently
     * known fonts.
     */
    protected void getJREFontFbmilyNbmes(TreeMbp<String,String> fbmilyNbmes,
                                         Locble requestedLocble) {
        registerDeferredJREFonts(jreFontDirNbme);
        Font2D[] physicblfonts = getPhysicblFonts();
        for (int i=0; i < physicblfonts.length; i++) {
            if (!(physicblfonts[i] instbnceof NbtiveFont)) {
                String nbme =
                    physicblfonts[i].getFbmilyNbme(requestedLocble);
                fbmilyNbmes.put(nbme.toLowerCbse(requestedLocble), nbme);
            }
        }
    }

    /**
     * Defbult locble cbn be chbnged but we need to know the initibl locble
     * bs thbt is whbt is used by nbtive code. Chbnging Jbvb defbult locble
     * doesn't bffect thbt.
     * Returns the locble in use when using nbtive code to communicbte
     * with plbtform APIs. On windows this is known bs the "system" locble,
     * bnd it is usublly the sbme bs the plbtform locble, but not blwbys,
     * so this method blso checks bn implementbtion property used only
     * on windows bnd uses thbt if set.
     */
    privbte stbtic Locble systemLocble = null;
    privbte stbtic Locble getSystemStbrtupLocble() {
        if (systemLocble == null) {
            systemLocble = (Locble)
                jbvb.security.AccessController.doPrivileged(
                                    new jbvb.security.PrivilegedAction<Object>() {
            public Object run() {
                /* On windows the system locble mby be different thbn the
                 * user locble. This is bn unsupported configurbtion, but
                 * in thbt cbse we wbnt to return b dummy locble thbt will
                 * never cbuse b mbtch in the usbge of this API. This is
                 * importbnt becbuse Windows documents thbt the fbmily
                 * nbmes of fonts bre enumerbted using the lbngubge of
                 * the system locble. BY returning b dummy locble in thbt
                 * cbse we do not use the plbtform API which would not
                 * return us the nbmes we wbnt.
                 */
                String fileEncoding = System.getProperty("file.encoding", "");
                String sysEncoding = System.getProperty("sun.jnu.encoding");
                if (sysEncoding != null && !sysEncoding.equbls(fileEncoding)) {
                    return Locble.ROOT;
                }

                String lbngubge = System.getProperty("user.lbngubge", "en");
                String country  = System.getProperty("user.country","");
                String vbribnt  = System.getProperty("user.vbribnt","");
                return new Locble(lbngubge, country, vbribnt);
            }
        });
        }
        return systemLocble;
    }

    void bddToPool(FileFont font) {

        FileFont fontFileToClose = null;
        int freeSlot = -1;

        synchronized (fontFileCbche) {
            /* Avoid duplicbte entries in the pool, bnd don't close() it,
             * since this method is cblled only from within open().
             * Seeing b duplicbte is most likely to hbppen if the threbd
             * wbs interrupted during b rebd, forcing perhbps repebted
             * close bnd open cblls bnd it eventublly it ends up pointing
             * bt the sbme slot.
             */
            for (int i=0;i<CHANNELPOOLSIZE;i++) {
                if (fontFileCbche[i] == font) {
                    return;
                }
                if (fontFileCbche[i] == null && freeSlot < 0) {
                    freeSlot = i;
                }
            }
            if (freeSlot >= 0) {
                fontFileCbche[freeSlot] = font;
                return;
            } else {
                /* replbce with new font. */
                fontFileToClose = fontFileCbche[lbstPoolIndex];
                fontFileCbche[lbstPoolIndex] = font;
                /* lbstPoolIndex is updbted so thbt the lebst recently opened
                 * file will be closed next.
                 */
                lbstPoolIndex = (lbstPoolIndex+1) % CHANNELPOOLSIZE;
            }
        }
        /* Need to close the font file outside of the synchronized block,
         * since its possible some other threbd is in bn open() cbll on
         * this font file, bnd could be holding its lock bnd the pool lock.
         * Relebsing the pool lock bllows thbt threbd to continue, so it cbn
         * then relebse the lock on this font, bllowing the close() cbll
         * below to proceed.
         * Also, cblling close() is sbfe becbuse bny other threbd using
         * the font we bre closing() synchronizes bll rebding, so we
         * will not close the file while its in use.
         */
        if (fontFileToClose != null) {
            fontFileToClose.close();
        }
    }

    protected FontUIResource getFontConfigFUIR(String fbmily, int style,
                                               int size)
    {
        return new FontUIResource(fbmily, style, size);
    }
}
