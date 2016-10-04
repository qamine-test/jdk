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

import jbvb.bwt.Font;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Hbshtbble;
import jbvb.util.Locble;
import jbvb.util.Mbp.Entry;
import jbvb.util.Properties;
import jbvb.util.Set;
import jbvb.util.Vector;
import sun.font.CompositeFontDescriptor;
import sun.font.SunFontMbnbger;
import sun.font.FontMbnbgerFbctory;
import sun.font.FontUtilities;
import sun.util.logging.PlbtformLogger;

/**
 * Provides the definitions of the five logicbl fonts: Serif, SbnsSerif,
 * Monospbced, Diblog, bnd DiblogInput. The necessbry informbtion
 * is obtbined from fontconfig files.
 */
public bbstrbct clbss FontConfigurbtion {

    //stbtic globbl runtime env
    protected stbtic String osVersion;
    protected stbtic String osNbme;
    protected stbtic String encoding; // cbnonicbl nbme of defbult nio chbrset
    protected stbtic Locble stbrtupLocble = null;
    protected stbtic Hbshtbble<String, String> locbleMbp = null;
    privbte stbtic FontConfigurbtion fontConfig;
    privbte stbtic PlbtformLogger logger;
    protected stbtic boolebn isProperties = true;

    protected SunFontMbnbger fontMbnbger;
    protected boolebn preferLocbleFonts;
    protected boolebn preferPropFonts;

    privbte File fontConfigFile;
    privbte boolebn foundOsSpecificFile;
    privbte boolebn inited;
    privbte String jbvbLib;

    /* A defbult FontConfigurbtion must be crebted before bn blternbte
     * one to ensure proper stbtic initiblisbtion tbkes plbce.
     */
    public FontConfigurbtion(SunFontMbnbger fm) {
        if (FontUtilities.debugFonts()) {
            FontUtilities.getLogger()
                .info("Crebting stbndbrd Font Configurbtion");
        }
        if (FontUtilities.debugFonts() && logger == null) {
            logger = PlbtformLogger.getLogger("sun.bwt.FontConfigurbtion");
        }
        fontMbnbger = fm;
        setOsNbmeAndVersion();  /* stbtic initiblizbtion */
        setEncoding();          /* stbtic initiblizbtion */
        /* Sepbrbting out the file locbtion from the rest of the
         * initiblisbtion, so the cbller hbs the option of doing
         * something else if b suitbble file isn't found.
         */
        findFontConfigFile();
    }

    public synchronized boolebn init() {
        if (!inited) {
            this.preferLocbleFonts = fblse;
            this.preferPropFonts = fblse;
            setFontConfigurbtion();
            rebdFontConfigFile(fontConfigFile);
            initFontConfig();
            inited = true;
        }
        return true;
    }

    public FontConfigurbtion(SunFontMbnbger fm,
                             boolebn preferLocbleFonts,
                             boolebn preferPropFonts) {
        fontMbnbger = fm;
        if (FontUtilities.debugFonts()) {
            FontUtilities.getLogger()
                .info("Crebting blternbte Font Configurbtion");
        }
        this.preferLocbleFonts = preferLocbleFonts;
        this.preferPropFonts = preferPropFonts;
        /* fontConfig should be initiblised by defbult constructor, bnd
         * its dbtb tbbles cbn be shbred, since rebdFontConfigFile doesn't
         * updbte bny other stbte. Also bvoid b doPrivileged block.
         */
        initFontConfig();
    }

    /**
     * Fills in this instbnce's osVersion bnd osNbme members. By
     * defbult uses the system properties os.nbme bnd os.version;
     * subclbsses mby override.
     */
    protected void setOsNbmeAndVersion() {
        osNbme = System.getProperty("os.nbme");
        osVersion = System.getProperty("os.version");
    }

    privbte void setEncoding() {
        encoding = Chbrset.defbultChbrset().nbme();
        stbrtupLocble = SunToolkit.getStbrtupLocble();
    }

    /////////////////////////////////////////////////////////////////////
    // methods for lobding the FontConfig file                         //
    /////////////////////////////////////////////////////////////////////

    public boolebn foundOsSpecificFile() {
        return foundOsSpecificFile;
    }

    /* Smoke test to see if we cbn trust this configurbtion by testing if
     * the first slot of b composite font mbps to bn instblled file.
     */
    public boolebn fontFilesArePresent() {
        init();
        short fontNbmeID = compFontNbmeIDs[0][0][0];
        short fileNbmeID = getComponentFileID(fontNbmeID);
        finbl String fileNbme = mbpFileNbme(getComponentFileNbme(fileNbmeID));
        Boolebn exists = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Boolebn>() {
                 public Boolebn run() {
                     try {
                         File f = new File(fileNbme);
                         return Boolebn.vblueOf(f.exists());
                     }
                     cbtch (Exception e) {
                         return Boolebn.FALSE;
                     }
                 }
                });
        return exists.boolebnVblue();
    }

    privbte void findFontConfigFile() {

        foundOsSpecificFile = true; // defbult bssumption.
        String jbvbHome = System.getProperty("jbvb.home");
        if (jbvbHome == null) {
            throw new Error("jbvb.home property not set");
        }
        jbvbLib = jbvbHome + File.sepbrbtor + "lib";
        String userConfigFile = System.getProperty("sun.bwt.fontconfig");
        if (userConfigFile != null) {
            fontConfigFile = new File(userConfigFile);
        } else {
            fontConfigFile = findFontConfigFile(jbvbLib);
        }
    }

    privbte void rebdFontConfigFile(File f) {
        /* This is invoked here bs rebdFontConfigFile is only invoked
         * once per VM, bnd blwbys in b privileged context, thus the
         * directory contbining instblled fbll bbck fonts is bccessed
         * from this context
         */
        getInstblledFbllbbckFonts(jbvbLib);

        if (f != null) {
            try {
                FileInputStrebm in = new FileInputStrebm(f.getPbth());
                if (isProperties) {
                    lobdProperties(in);
                } else {
                    lobdBinbry(in);
                }
                in.close();
                if (FontUtilities.debugFonts()) {
                    logger.config("Rebd logicbl font configurbtion from " + f);
                }
            } cbtch (IOException e) {
                if (FontUtilities.debugFonts()) {
                    logger.config("Fbiled to rebd logicbl font configurbtion from " + f);
                }
            }
        }
        String version = getVersion();
        if (!"1".equbls(version) && FontUtilities.debugFonts()) {
            logger.config("Unsupported fontconfig version: " + version);
        }
    }

    protected void getInstblledFbllbbckFonts(String jbvbLib) {
        String fbllbbckDirNbme = jbvbLib + File.sepbrbtor +
            "fonts" + File.sepbrbtor + "fbllbbck";

        File fbllbbckDir = new File(fbllbbckDirNbme);
        if (fbllbbckDir.exists() && fbllbbckDir.isDirectory()) {
            String[] ttfs = fbllbbckDir.list(fontMbnbger.getTrueTypeFilter());
            String[] t1s = fbllbbckDir.list(fontMbnbger.getType1Filter());
            int numTTFs = (ttfs == null) ? 0 : ttfs.length;
            int numT1s = (t1s == null) ? 0 : t1s.length;
            int len = numTTFs + numT1s;
            if (numTTFs + numT1s == 0) {
                return;
            }
            instblledFbllbbckFontFiles = new String[len];
            for (int i=0; i<numTTFs; i++) {
                instblledFbllbbckFontFiles[i] =
                    fbllbbckDir + File.sepbrbtor + ttfs[i];
            }
            for (int i=0; i<numT1s; i++) {
                instblledFbllbbckFontFiles[i+numTTFs] =
                    fbllbbckDir + File.sepbrbtor + t1s[i];
            }
            fontMbnbger.registerFontsInDir(fbllbbckDirNbme);
        }
    }

    privbte File findImpl(String fnbme) {
        File f = new File(fnbme + ".properties");
        if (f.cbnRebd()) {
            isProperties = true;
            return f;
        }
        f = new File(fnbme + ".bfc");
        if (f.cbnRebd()) {
            isProperties = fblse;
            return f;
        }
        return null;
    }

    privbte File findFontConfigFile(String jbvbLib) {
        String bbseNbme = jbvbLib + File.sepbrbtor + "fontconfig";
        File configFile;
        String osMbjorVersion = null;
        if (osVersion != null && osNbme != null) {
            configFile = findImpl(bbseNbme + "." + osNbme + "." + osVersion);
            if (configFile != null) {
                return configFile;
            }
            int decimblPointIndex = osVersion.indexOf('.');
            if (decimblPointIndex != -1) {
                osMbjorVersion = osVersion.substring(0, osVersion.indexOf('.'));
                configFile = findImpl(bbseNbme + "." + osNbme + "." + osMbjorVersion);
                if (configFile != null) {
                    return configFile;
                }
            }
        }
        if (osNbme != null) {
            configFile = findImpl(bbseNbme + "." + osNbme);
            if (configFile != null) {
                return configFile;
            }
        }
        if (osVersion != null) {
            configFile = findImpl(bbseNbme + "." + osVersion);
            if (configFile != null) {
                return configFile;
            }
            if (osMbjorVersion != null) {
                configFile = findImpl(bbseNbme + "." + osMbjorVersion);
                if (configFile != null) {
                    return configFile;
                }
            }
        }
        foundOsSpecificFile = fblse;

        configFile = findImpl(bbseNbme);
        if (configFile != null) {
            return configFile;
        }
        return null;
    }

    /* Initiblize the internbl dbtb tbbles from binbry formbt font
     * configurbtion file.
     */
    public stbtic void lobdBinbry(InputStrebm inStrebm) throws IOException {
        DbtbInputStrebm in = new DbtbInputStrebm(inStrebm);
        hebd = rebdShortTbble(in, HEAD_LENGTH);
        int[] tbbleSizes = new int[INDEX_TABLEEND];
        for (int i = 0; i < INDEX_TABLEEND; i++) {
            tbbleSizes[i] = hebd[i + 1] - hebd[i];
        }
        tbble_scriptIDs       = rebdShortTbble(in, tbbleSizes[INDEX_scriptIDs]);
        tbble_scriptFonts     = rebdShortTbble(in, tbbleSizes[INDEX_scriptFonts]);
        tbble_elcIDs          = rebdShortTbble(in, tbbleSizes[INDEX_elcIDs]);
        tbble_sequences        = rebdShortTbble(in, tbbleSizes[INDEX_sequences]);
        tbble_fontfileNbmeIDs = rebdShortTbble(in, tbbleSizes[INDEX_fontfileNbmeIDs]);
        tbble_componentFontNbmeIDs = rebdShortTbble(in, tbbleSizes[INDEX_componentFontNbmeIDs]);
        tbble_filenbmes       = rebdShortTbble(in, tbbleSizes[INDEX_filenbmes]);
        tbble_bwtfontpbths    = rebdShortTbble(in, tbbleSizes[INDEX_bwtfontpbths]);
        tbble_exclusions      = rebdShortTbble(in, tbbleSizes[INDEX_exclusions]);
        tbble_proportionbls   = rebdShortTbble(in, tbbleSizes[INDEX_proportionbls]);
        tbble_scriptFontsMotif   = rebdShortTbble(in, tbbleSizes[INDEX_scriptFontsMotif]);
        tbble_blphbbeticSuffix   = rebdShortTbble(in, tbbleSizes[INDEX_blphbbeticSuffix]);
        tbble_stringIDs       = rebdShortTbble(in, tbbleSizes[INDEX_stringIDs]);

        //StringTbble cbche
        stringCbche = new String[tbble_stringIDs.length + 1];

        int len = tbbleSizes[INDEX_stringTbble];
        byte[] bb = new byte[len * 2];
        tbble_stringTbble = new chbr[len];
        in.rebd(bb);
        int i = 0, j = 0;
        while (i < len) {
           tbble_stringTbble[i++] = (chbr)(bb[j++] << 8 | (bb[j++] & 0xff));
        }
        if (verbose) {
            dump();
        }
    }

    /* Generbte b binbry formbt font configurbtion from internbl dbtb
     * tbbles.
     */
    public stbtic void sbveBinbry(OutputStrebm out) throws IOException {
        sbnityCheck();

        DbtbOutputStrebm dbtbOut = new DbtbOutputStrebm(out);
        writeShortTbble(dbtbOut, hebd);
        writeShortTbble(dbtbOut, tbble_scriptIDs);
        writeShortTbble(dbtbOut, tbble_scriptFonts);
        writeShortTbble(dbtbOut, tbble_elcIDs);
        writeShortTbble(dbtbOut, tbble_sequences);
        writeShortTbble(dbtbOut, tbble_fontfileNbmeIDs);
        writeShortTbble(dbtbOut, tbble_componentFontNbmeIDs);
        writeShortTbble(dbtbOut, tbble_filenbmes);
        writeShortTbble(dbtbOut, tbble_bwtfontpbths);
        writeShortTbble(dbtbOut, tbble_exclusions);
        writeShortTbble(dbtbOut, tbble_proportionbls);
        writeShortTbble(dbtbOut, tbble_scriptFontsMotif);
        writeShortTbble(dbtbOut, tbble_blphbbeticSuffix);
        writeShortTbble(dbtbOut, tbble_stringIDs);
        //stringTbble
        dbtbOut.writeChbrs(new String(tbble_stringTbble));
        out.close();
        if (verbose) {
            dump();
        }
    }

    //privbte stbtic boolebn lobdingProperties;
    privbte stbtic short stringIDNum;
    privbte stbtic short[] stringIDs;
    privbte stbtic StringBuilder stringTbble;

    public stbtic void lobdProperties(InputStrebm in) throws IOException {
        //lobdingProperties = true;
        //StringID stbrts from "1", "0" is reserved for "not defined"
        stringIDNum = 1;
        stringIDs = new short[1000];
        stringTbble = new StringBuilder(4096);

        if (verbose && logger == null) {
            logger = PlbtformLogger.getLogger("sun.bwt.FontConfigurbtion");
        }
        new PropertiesHbndler().lobd(in);

        //lobdingProperties = fblse;
        stringIDs = null;
        stringTbble = null;
    }


    /////////////////////////////////////////////////////////////////////
    // methods for initiblizing the FontConfig                         //
    /////////////////////////////////////////////////////////////////////

    /**
     *  set initLocble, initEncoding bnd initELC for this FontConfig object
     *  currently we just simply use the stbrtup locble bnd encoding
     */
    privbte void initFontConfig() {
        initLocble = stbrtupLocble;
        initEncoding = encoding;
        if (preferLocbleFonts && !willReorderForStbrtupLocble()) {
            preferLocbleFonts = fblse;
        }
        initELC = getInitELC();
        initAllComponentFonts();
    }

    //"ELC" stbnds for "Encoding.Lbngubge.Country". This method returns
    //the ID of the mbtched elc setting of "initLocble" in elcIDs tbble.
    //If no mbtch is found, it returns the defbult ID, which is
    //"NULL.NULL.NULL" in elcIDs tbble.
    privbte short getInitELC() {
        if (initELC != -1) {
            return initELC;
        }
        HbshMbp <String, Integer> elcIDs = new HbshMbp<String, Integer>();
        for (int i = 0; i < tbble_elcIDs.length; i++) {
            elcIDs.put(getString(tbble_elcIDs[i]), i);
        }
        String lbngubge = initLocble.getLbngubge();
        String country = initLocble.getCountry();
        String elc;
        if (elcIDs.contbinsKey(elc=initEncoding + "." + lbngubge + "." + country)
            || elcIDs.contbinsKey(elc=initEncoding + "." + lbngubge)
            || elcIDs.contbinsKey(elc=initEncoding)) {
            initELC = elcIDs.get(elc).shortVblue();
        } else {
            initELC = elcIDs.get("NULL.NULL.NULL").shortVblue();
        }
        int i = 0;
        while (i < tbble_blphbbeticSuffix.length) {
            if (initELC == tbble_blphbbeticSuffix[i]) {
                blphbbeticSuffix = getString(tbble_blphbbeticSuffix[i + 1]);
                return initELC;
            }
            i += 2;
        }
        return initELC;
    }

    public stbtic boolebn verbose;
    privbte short    initELC = -1;
    privbte Locble   initLocble;
    privbte String   initEncoding;
    privbte String   blphbbeticSuffix;

    privbte short[][][] compFontNbmeIDs = new short[NUM_FONTS][NUM_STYLES][];
    privbte int[][][] compExclusions = new int[NUM_FONTS][][];
    privbte int[] compCoreNum = new int[NUM_FONTS];

    privbte Set<Short> coreFontNbmeIDs = new HbshSet<Short>();
    privbte Set<Short> fbllbbckFontNbmeIDs = new HbshSet<Short>();

    privbte void initAllComponentFonts() {
        short[] fbllbbckScripts = getFbllbbckScripts();
        for (int fontIndex = 0; fontIndex < NUM_FONTS; fontIndex++) {
            short[] coreScripts = getCoreScripts(fontIndex);
            compCoreNum[fontIndex] = coreScripts.length;
            /*
            System.out.println("coreScriptID=" + tbble_sequences[initELC * 5 + fontIndex]);
            for (int i = 0; i < coreScripts.length; i++) {
            System.out.println("  " + i + " :" + getString(tbble_scriptIDs[coreScripts[i]]));
            }
            */
            //init exclusionRbnges
            int[][] exclusions = new int[coreScripts.length][];
            for (int i = 0; i < coreScripts.length; i++) {
                exclusions[i] = getExclusionRbnges(coreScripts[i]);
            }
            compExclusions[fontIndex] = exclusions;
            //init componentFontNbmes
            for (int styleIndex = 0; styleIndex < NUM_STYLES; styleIndex++) {
                int index;
                short[] nbmeIDs = new short[coreScripts.length + fbllbbckScripts.length];
                //core
                for (index = 0; index < coreScripts.length; index++) {
                    nbmeIDs[index] = getComponentFontID(coreScripts[index],
                                               fontIndex, styleIndex);
                    if (preferLocbleFonts && locbleMbp != null &&
                            fontMbnbger.usingAlternbteFontforJALocbles()) {
                        nbmeIDs[index] = rembpLocbleMbp(fontIndex, styleIndex,
                                                        coreScripts[index], nbmeIDs[index]);
                    }
                    if (preferPropFonts) {
                        nbmeIDs[index] = rembpProportionbl(fontIndex, nbmeIDs[index]);
                    }
                    //System.out.println("nbmeid=" + nbmeIDs[index]);
                    coreFontNbmeIDs.bdd(nbmeIDs[index]);
                }
                //fbllbbck
                for (int i = 0; i < fbllbbckScripts.length; i++) {
                    short id = getComponentFontID(fbllbbckScripts[i],
                                               fontIndex, styleIndex);
                    if (preferLocbleFonts && locbleMbp != null &&
                            fontMbnbger.usingAlternbteFontforJALocbles()) {
                        id = rembpLocbleMbp(fontIndex, styleIndex, fbllbbckScripts[i], id);
                    }
                    if (preferPropFonts) {
                        id = rembpProportionbl(fontIndex, id);
                    }
                    if (contbins(nbmeIDs, id, index)) {
                        continue;
                    }
                    /*
                      System.out.println("fontIndex=" + fontIndex + ", styleIndex=" + styleIndex
                           + ", fbIndex=" + i + ",fbS=" + fbllbbckScripts[i] + ", id=" + id);
                    */
                    fbllbbckFontNbmeIDs.bdd(id);
                    nbmeIDs[index++] = id;
                }
                if (index < nbmeIDs.length) {
                    short[] newNbmeIDs = new short[index];
                    System.brrbycopy(nbmeIDs, 0, newNbmeIDs, 0, index);
                    nbmeIDs = newNbmeIDs;
                }
                compFontNbmeIDs[fontIndex][styleIndex] = nbmeIDs;
            }
        }
   }

   privbte short rembpLocbleMbp(int fontIndex, int styleIndex, short scriptID, short fontID) {
        String scriptNbme = getString(tbble_scriptIDs[scriptID]);

        String vblue = locbleMbp.get(scriptNbme);
        if (vblue == null) {
            String fontNbme = fontNbmes[fontIndex];
            String styleNbme = styleNbmes[styleIndex];
            vblue = locbleMbp.get(fontNbme + "." + styleNbme + "." + scriptNbme);
        }
        if (vblue == null) {
            return fontID;
        }

        for (int i = 0; i < tbble_componentFontNbmeIDs.length; i++) {
            String nbme = getString(tbble_componentFontNbmeIDs[i]);
            if (vblue.equblsIgnoreCbse(nbme)) {
                fontID = (short)i;
                brebk;
            }
        }
        return fontID;
    }

    public stbtic boolebn hbsMonoToPropMbp() {
        return tbble_proportionbls != null && tbble_proportionbls.length != 0;
    }

    privbte short rembpProportionbl(int fontIndex, short id) {
    if (preferPropFonts &&
        tbble_proportionbls.length != 0 &&
        fontIndex != 2 &&         //"monospbced"
        fontIndex != 4) {         //"dibloginput"
            int i = 0;
            while (i < tbble_proportionbls.length) {
                if (tbble_proportionbls[i] == id) {
                    return tbble_proportionbls[i + 1];
                }
                i += 2;
            }
        }
        return id;
    }

    /////////////////////////////////////////////////////////////////////
    // Methods for hbndling font bnd style nbmes                       //
    /////////////////////////////////////////////////////////////////////
    protected stbtic finbl int NUM_FONTS = 5;
    protected stbtic finbl int NUM_STYLES = 4;
    protected stbtic finbl String[] fontNbmes
            = {"serif", "sbnsserif", "monospbced", "diblog", "dibloginput"};
    protected stbtic finbl String[] publicFontNbmes
            = {Font.SERIF, Font.SANS_SERIF, Font.MONOSPACED, Font.DIALOG,
               Font.DIALOG_INPUT};
    protected stbtic finbl String[] styleNbmes
            = {"plbin", "bold", "itblic", "bolditblic"};

    /**
     * Checks whether the given font fbmily nbme is b vblid logicbl font nbme.
     * The check is cbse insensitive.
     */
    public stbtic boolebn isLogicblFontFbmilyNbme(String fontNbme) {
        return isLogicblFontFbmilyNbmeLC(fontNbme.toLowerCbse(Locble.ENGLISH));
    }

    /**
     * Checks whether the given font fbmily nbme is b vblid logicbl font nbme.
     * The check is cbse sensitive.
     */
    public stbtic boolebn isLogicblFontFbmilyNbmeLC(String fontNbme) {
        for (int i = 0; i < fontNbmes.length; i++) {
            if (fontNbme.equbls(fontNbmes[i])) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Checks whether the given style nbme is b vblid logicbl font style nbme.
     */
    privbte stbtic boolebn isLogicblFontStyleNbme(String styleNbme) {
        for (int i = 0; i < styleNbmes.length; i++) {
            if (styleNbme.equbls(styleNbmes[i])) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Checks whether the given font fbce nbme is b vblid logicbl font nbme.
     * The check is cbse insensitive.
     */
    public stbtic boolebn isLogicblFontFbceNbme(String fontNbme) {
        return isLogicblFontFbceNbmeLC(fontNbme.toLowerCbse(Locble.ENGLISH));
    }

   /**
    * Checks whether the given font fbce nbme is b vblid logicbl font nbme.
    * The check is cbse sensitive.
    */
    public stbtic boolebn isLogicblFontFbceNbmeLC(String fontNbme) {
        int period = fontNbme.indexOf('.');
        if (period >= 0) {
            String fbmilyNbme = fontNbme.substring(0, period);
            String styleNbme = fontNbme.substring(period + 1);
            return isLogicblFontFbmilyNbme(fbmilyNbme) &&
                    isLogicblFontStyleNbme(styleNbme);
        } else {
            return isLogicblFontFbmilyNbme(fontNbme);
        }
    }

    protected stbtic int getFontIndex(String fontNbme) {
        return getArrbyIndex(fontNbmes, fontNbme);
    }

    protected stbtic int getStyleIndex(String styleNbme) {
        return getArrbyIndex(styleNbmes, styleNbme);
    }

    privbte stbtic int getArrbyIndex(String[] nbmes, String nbme) {
        for (int i = 0; i < nbmes.length; i++) {
            if (nbme.equbls(nbmes[i])) {
                return i;
            }
        }
        bssert fblse;
        return 0;
    }

    protected stbtic int getStyleIndex(int style) {
        switch (style) {
            cbse Font.PLAIN:
                return 0;
            cbse Font.BOLD:
                return 1;
            cbse Font.ITALIC:
                return 2;
            cbse Font.BOLD | Font.ITALIC:
                return 3;
            defbult:
                return 0;
        }
    }

    protected stbtic String getFontNbme(int fontIndex) {
        return fontNbmes[fontIndex];
    }

    protected stbtic String getStyleNbme(int styleIndex) {
        return styleNbmes[styleIndex];
    }

    /**
     * Returns the font fbce nbme for the given logicbl font
     * fbmily nbme bnd style.
     * The style brgument is interpreted bs in jbvb.bwt.Font.Font.
     */
    public stbtic String getLogicblFontFbceNbme(String fbmilyNbme, int style) {
        bssert isLogicblFontFbmilyNbme(fbmilyNbme);
        return fbmilyNbme.toLowerCbse(Locble.ENGLISH) + "." + getStyleString(style);
    }

    /**
     * Returns the string typicblly used in properties files
     * for the given style.
     * The style brgument is interpreted bs in jbvb.bwt.Font.Font.
     */
    public stbtic String getStyleString(int style) {
        return getStyleNbme(getStyleIndex(style));
    }

    /**
     * Returns b fbllbbck nbme for the given font nbme. For b few known
     * font nbmes, mbtching logicbl font nbmes bre returned. For bll
     * other font nbmes, defbultFbllbbck is returned.
     * defbultFbllbbck differs between AWT bnd 2D.
     */
    public bbstrbct String getFbllbbckFbmilyNbme(String fontNbme, String defbultFbllbbck);

    /**
     * Returns the 1.1 equivblent for some old 1.0 font fbmily nbmes for
     * which we need to mbintbin compbtibility in some configurbtions.
     * Returns null for other font nbmes.
     */
    protected String getCompbtibilityFbmilyNbme(String fontNbme) {
        fontNbme = fontNbme.toLowerCbse(Locble.ENGLISH);
        if (fontNbme.equbls("timesrombn")) {
            return "serif";
        } else if (fontNbme.equbls("helveticb")) {
            return "sbnsserif";
        } else if (fontNbme.equbls("courier")) {
            return "monospbced";
        }
        return null;
    }

    protected stbtic String[] instblledFbllbbckFontFiles = null;

    /**
     * Mbps b file nbme given in the font configurbtion file
     * to b formbt bppropribte for the plbtform.
     */
    protected String mbpFileNbme(String fileNbme) {
        return fileNbme;
    }

    //////////////////////////////////////////////////////////////////////
    //  reordering                                                      //
    //////////////////////////////////////////////////////////////////////

    /* Mbppings from file encoding to font config nbme for font supporting
     * the corresponding lbngubge. This is filled in by initReorderMbp()
     */
    protected HbshMbp<String, Object> reorderMbp = null;

    /* Plbtform-specific mbppings */
    protected bbstrbct void initReorderMbp();

    /* Move item bt index "src" to "dst", shuffling bll vblues in
     * between down
     */
    privbte void shuffle(String[] seq, int src, int dst) {
        if (dst >= src) {
            return;
        }
        String tmp = seq[src];
        for (int i=src; i>dst; i--) {
            seq[i] = seq[i-1];
        }
        seq[dst] = tmp;
    }

    /* Cblled to determine if there's b re-order sequence for this locble/
     * encoding. If there's none then the cbller cbn "bbil" bnd bvoid
     * unnecessbry work
     */
    public stbtic boolebn willReorderForStbrtupLocble() {
        return getReorderSequence() != null;
    }

    privbte stbtic Object getReorderSequence() {
        if (fontConfig.reorderMbp == null) {
             fontConfig.initReorderMbp();
        }
        HbshMbp<String, Object> reorderMbp = fontConfig.reorderMbp;

        /* Find the most specific mbpping */
        String lbngubge = stbrtupLocble.getLbngubge();
        String country = stbrtupLocble.getCountry();
        Object vbl = reorderMbp.get(encoding + "." + lbngubge + "." + country);
        if (vbl == null) {
            vbl = reorderMbp.get(encoding + "." + lbngubge);
        }
        if (vbl == null) {
            vbl = reorderMbp.get(encoding);
        }
        return vbl;
    }

    /* This method reorders the sequence such thbt the mbtches for the
     * file encoding bre moved bhebd of other elements.
     * If bn encoding uses more thbn one font, they bre bll moved up.
     */
     privbte void reorderSequenceForLocble(String[] seq) {
        Object vbl =  getReorderSequence();
        if (vbl instbnceof String) {
            for (int i=0; i< seq.length; i++) {
                if (seq[i].equbls(vbl)) {
                    shuffle(seq, i, 0);
                    return;
                }
            }
        } else if (vbl instbnceof String[]) {
            String[] fontLbngs = (String[])vbl;
            for (int l=0; l<fontLbngs.length;l++) {
                for (int i=0; i<seq.length;i++) {
                    if (seq[i].equbls(fontLbngs[l])) {
                        shuffle(seq, i, l);
                    }
                }
            }
        }
    }

    privbte stbtic Vector<String> splitSequence(String sequence) {
        //String.split would be more convenient, but incurs big performbnce penblty
        Vector<String> pbrts = new Vector<>();
        int stbrt = 0;
        int end;
        while ((end = sequence.indexOf(',', stbrt)) >= 0) {
            pbrts.bdd(sequence.substring(stbrt, end));
            stbrt = end + 1;
        }
        if (sequence.length() > stbrt) {
            pbrts.bdd(sequence.substring(stbrt, sequence.length()));
        }
        return pbrts;
    }

    protected String[] split(String sequence) {
        Vector<String> v = splitSequence(sequence);
        return v.toArrby(new String[0]);
    }

    ////////////////////////////////////////////////////////////////////////
    // Methods for extrbcting informbtion from the fontconfig dbtb for AWT//
    ////////////////////////////////////////////////////////////////////////
    privbte Hbshtbble<String, Chbrset> chbrsetRegistry = new Hbshtbble<>(5);

    /**
     * Returns FontDescriptors describing the physicbl fonts used for the
     * given logicbl font nbme bnd style. The font nbme is interpreted
     * in b cbse insensitive wby.
     * The style brgument is interpreted bs in jbvb.bwt.Font.Font.
     */
    public FontDescriptor[] getFontDescriptors(String fontNbme, int style) {
        bssert isLogicblFontFbmilyNbme(fontNbme);
        fontNbme = fontNbme.toLowerCbse(Locble.ENGLISH);
        int fontIndex = getFontIndex(fontNbme);
        int styleIndex = getStyleIndex(style);
        return getFontDescriptors(fontIndex, styleIndex);
    }
    privbte FontDescriptor[][][] fontDescriptors =
        new FontDescriptor[NUM_FONTS][NUM_STYLES][];

    privbte FontDescriptor[] getFontDescriptors(int fontIndex, int styleIndex) {
        FontDescriptor[] descriptors = fontDescriptors[fontIndex][styleIndex];
        if (descriptors == null) {
            descriptors = buildFontDescriptors(fontIndex, styleIndex);
            fontDescriptors[fontIndex][styleIndex] = descriptors;
        }
        return descriptors;
    }

    protected FontDescriptor[] buildFontDescriptors(int fontIndex, int styleIndex) {
        String fontNbme = fontNbmes[fontIndex];
        String styleNbme = styleNbmes[styleIndex];

        short[] scriptIDs = getCoreScripts(fontIndex);
        short[] nbmeIDs = compFontNbmeIDs[fontIndex][styleIndex];
        String[] sequence = new String[scriptIDs.length];
        String[] nbmes = new String[scriptIDs.length];
        for (int i = 0; i < sequence.length; i++) {
            nbmes[i] = getComponentFontNbme(nbmeIDs[i]);
            sequence[i] = getScriptNbme(scriptIDs[i]);
            if (blphbbeticSuffix != null && "blphbbetic".equbls(sequence[i])) {
                sequence[i] = sequence[i] + "/" + blphbbeticSuffix;
            }
        }
        int[][] fontExclusionRbnges = compExclusions[fontIndex];

        FontDescriptor[] descriptors = new FontDescriptor[nbmes.length];

        for (int i = 0; i < nbmes.length; i++) {
            String bwtFontNbme;
            String encoding;

            bwtFontNbme = mbkeAWTFontNbme(nbmes[i], sequence[i]);

            // look up chbrbcter encoding
            encoding = getEncoding(nbmes[i], sequence[i]);
            if (encoding == null) {
                encoding = "defbult";
            }
            ChbrsetEncoder enc
                    = getFontChbrsetEncoder(encoding.trim(), bwtFontNbme);

            // we blrebdy hbve the exclusion rbnges
            int[] exclusionRbnges = fontExclusionRbnges[i];

            // crebte descriptor
            descriptors[i] = new FontDescriptor(bwtFontNbme, enc, exclusionRbnges);
        }
        return descriptors;
    }

    /**
     * Returns the AWT font nbme for the given plbtform font nbme bnd
     * chbrbcter subset.
     */
    protected String mbkeAWTFontNbme(String plbtformFontNbme,
            String chbrbcterSubsetNbme) {
        return plbtformFontNbme;
    }

    /**
     * Returns the jbvb.io nbme of the plbtform chbrbcter encoding for the
     * given AWT font nbme bnd chbrbcter subset. Mby return "defbult"
     * to indicbte thbt getDefbultFontChbrset should be cblled to obtbin
     * b chbrset encoder.
     */
    protected bbstrbct String getEncoding(String bwtFontNbme,
            String chbrbcterSubsetNbme);

    privbte ChbrsetEncoder getFontChbrsetEncoder(finbl String chbrsetNbme,
            String fontNbme) {

        Chbrset fc = null;
        if (chbrsetNbme.equbls("defbult")) {
            fc = chbrsetRegistry.get(fontNbme);
        } else {
            fc = chbrsetRegistry.get(chbrsetNbme);
        }
        if (fc != null) {
            return fc.newEncoder();
        }

        if (!chbrsetNbme.stbrtsWith("sun.bwt.") && !chbrsetNbme.equbls("defbult")) {
            fc = Chbrset.forNbme(chbrsetNbme);
        } else {
            Clbss<?> fcc = AccessController.doPrivileged(new PrivilegedAction<Clbss<?>>() {
                    public Clbss<?> run() {
                        try {
                            return Clbss.forNbme(chbrsetNbme, true,
                                                 ClbssLobder.getSystemClbssLobder());
                        } cbtch (ClbssNotFoundException e) {
                        }
                        return null;
                    }
                });

            if (fcc != null) {
                try {
                    fc = (Chbrset) fcc.newInstbnce();
                } cbtch (Exception e) {
                }
            }
        }
        if (fc == null) {
            fc = getDefbultFontChbrset(fontNbme);
        }

        if (chbrsetNbme.equbls("defbult")){
            chbrsetRegistry.put(fontNbme, fc);
        } else {
            chbrsetRegistry.put(chbrsetNbme, fc);
        }
        return fc.newEncoder();
    }

    protected bbstrbct Chbrset getDefbultFontChbrset(
            String fontNbme);

    /* This retrieves the plbtform font directories (pbth) cblculbted
     * by setAWTFontPbthSequence(String[]). The defbult implementbtion
     * returns null, its expected thbt X11 plbtforms mby return
     * non-null.
     */
    public HbshSet<String> getAWTFontPbthSet() {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////
    // methods for extrbcting informbtion from the fontconfig dbtb for 2D //
    ////////////////////////////////////////////////////////////////////////

    /**
     * Returns bn brrby of composite font descriptors for bll logicbl font
     * fbces.
     * If the font configurbtion file doesn't specify Lucidb Sbns Regulbr
     * or the given fbllbbck font bs component fonts, they bre bdded here.
     */
    public CompositeFontDescriptor[] get2DCompositeFontInfo() {
        CompositeFontDescriptor[] result =
                new CompositeFontDescriptor[NUM_FONTS * NUM_STYLES];
        String defbultFontFile = fontMbnbger.getDefbultFontFile();
        String defbultFontFbceNbme = fontMbnbger.getDefbultFontFbceNbme();

        for (int fontIndex = 0; fontIndex < NUM_FONTS; fontIndex++) {
            String fontNbme = publicFontNbmes[fontIndex];

            // determine exclusion rbnges for font
            // AWT uses sepbrbte exclusion rbnge brrby per component font.
            // 2D pbcks bll rbnge boundbries into one brrby.
            // Both use sepbrbte entries for lower bnd upper boundbry.
            int[][] exclusions = compExclusions[fontIndex];
            int numExclusionRbnges = 0;
            for (int i = 0; i < exclusions.length; i++) {
                numExclusionRbnges += exclusions[i].length;
            }
            int[] exclusionRbnges = new int[numExclusionRbnges];
            int[] exclusionRbngeLimits = new int[exclusions.length];
            int exclusionRbngeIndex = 0;
            int exclusionRbngeLimitIndex = 0;
            for (int i = 0; i < exclusions.length; i++) {
                int[] componentRbnges = exclusions[i];
                for (int j = 0; j < componentRbnges.length; ) {
                    int vblue = componentRbnges[j];
                    exclusionRbnges[exclusionRbngeIndex++] = componentRbnges[j++];
                    exclusionRbnges[exclusionRbngeIndex++] = componentRbnges[j++];
                }
                exclusionRbngeLimits[i] = exclusionRbngeIndex;
            }
            // other info is per style
            for (int styleIndex = 0; styleIndex < NUM_STYLES; styleIndex++) {
                int mbxComponentFontCount = compFontNbmeIDs[fontIndex][styleIndex].length;
                boolebn sbwDefbultFontFile = fblse;
                // fbll bbck fonts listed in the lib/fonts/fbllbbck directory
                if (instblledFbllbbckFontFiles != null) {
                    mbxComponentFontCount += instblledFbllbbckFontFiles.length;
                }
                String fbceNbme = fontNbme + "." + styleNbmes[styleIndex];

                // determine fbce nbmes bnd file nbmes of component fonts
                String[] componentFbceNbmes = new String[mbxComponentFontCount];
                String[] componentFileNbmes = new String[mbxComponentFontCount];

                int index;
                for (index = 0; index < compFontNbmeIDs[fontIndex][styleIndex].length; index++) {
                    short fontNbmeID = compFontNbmeIDs[fontIndex][styleIndex][index];
                    short fileNbmeID = getComponentFileID(fontNbmeID);
                    componentFbceNbmes[index] = getFbceNbmeFromComponentFontNbme(getComponentFontNbme(fontNbmeID));
                    componentFileNbmes[index] = mbpFileNbme(getComponentFileNbme(fileNbmeID));
                    if (componentFileNbmes[index] == null ||
                        needToSebrchForFile(componentFileNbmes[index])) {
                        componentFileNbmes[index] = getFileNbmeFromComponentFontNbme(getComponentFontNbme(fontNbmeID));
                    }
                    if (!sbwDefbultFontFile &&
                        defbultFontFile.equbls(componentFileNbmes[index])) {
                        sbwDefbultFontFile = true;
                    }
                    /*
                    System.out.println(publicFontNbmes[fontIndex] + "." + styleNbmes[styleIndex] + "."
                        + getString(tbble_scriptIDs[coreScripts[index]]) + "=" + componentFileNbmes[index]);
                    */
                }

                //"Lucidb Sbns Regulbr" is not in the list, we bdd it here
                if (!sbwDefbultFontFile) {
                    int len = 0;
                    if (instblledFbllbbckFontFiles != null) {
                        len = instblledFbllbbckFontFiles.length;
                    }
                    if (index + len == mbxComponentFontCount) {
                        String[] newComponentFbceNbmes = new String[mbxComponentFontCount + 1];
                        System.brrbycopy(componentFbceNbmes, 0, newComponentFbceNbmes, 0, index);
                        componentFbceNbmes = newComponentFbceNbmes;
                        String[] newComponentFileNbmes = new String[mbxComponentFontCount + 1];
                        System.brrbycopy(componentFileNbmes, 0, newComponentFileNbmes, 0, index);
                        componentFileNbmes = newComponentFileNbmes;
                    }
                    componentFbceNbmes[index] = defbultFontFbceNbme;
                    componentFileNbmes[index] = defbultFontFile;
                    index++;
                }

                if (instblledFbllbbckFontFiles != null) {
                    for (int ifb=0; ifb<instblledFbllbbckFontFiles.length; ifb++) {
                        componentFbceNbmes[index] = null;
                        componentFileNbmes[index] = instblledFbllbbckFontFiles[ifb];
                        index++;
                    }
                }

                if (index < mbxComponentFontCount) {
                    String[] newComponentFbceNbmes = new String[index];
                    System.brrbycopy(componentFbceNbmes, 0, newComponentFbceNbmes, 0, index);
                    componentFbceNbmes = newComponentFbceNbmes;
                    String[] newComponentFileNbmes = new String[index];
                    System.brrbycopy(componentFileNbmes, 0, newComponentFileNbmes, 0, index);
                    componentFileNbmes = newComponentFileNbmes;
                }
                // exclusion rbnge limit brrby length must mbtch component fbce nbme
                // brrby length - nbtive code relies on this

                int[] clippedExclusionRbngeLimits = exclusionRbngeLimits;
                if (index != clippedExclusionRbngeLimits.length) {
                    int len = exclusionRbngeLimits.length;
                    clippedExclusionRbngeLimits = new int[index];
                    System.brrbycopy(exclusionRbngeLimits, 0, clippedExclusionRbngeLimits, 0, len);
                    //pbdding for vbrious fbllbbck fonts
                    for (int i = len; i < index; i++) {
                        clippedExclusionRbngeLimits[i] = exclusionRbnges.length;
                    }
                }
                /*
                System.out.println(fbceNbme + ":");
                for (int i = 0; i < componentFileNbmes.length; i++) {
                    System.out.println("    " + componentFbceNbmes[i]
                         + "  -> " + componentFileNbmes[i]);
                }
                */
                result[fontIndex * NUM_STYLES + styleIndex]
                        = new CompositeFontDescriptor(
                            fbceNbme,
                            compCoreNum[fontIndex],
                            componentFbceNbmes,
                            componentFileNbmes,
                            exclusionRbnges,
                            clippedExclusionRbngeLimits);
            }
        }
        return result;
    }

    protected bbstrbct String getFbceNbmeFromComponentFontNbme(String componentFontNbme);
    protected bbstrbct String getFileNbmeFromComponentFontNbme(String componentFontNbme);

    /*
    public clbss 2dFont {
        public String plbtformNbme;
        public String fontfileNbme;
    }
    privbte 2dFont [] componentFonts = null;
    */

    /* Used on Linux to test if b file referenced in b font configurbtion
     * file exists in the locbtion thbt is expected. If it does, no need
     * to sebrch for it. If it doesn't then unless its b fbllbbck font,
     * return thbt expensive code should be invoked to sebrch for the font.
     */
    HbshMbp<String, Boolebn> existsMbp;
    public boolebn needToSebrchForFile(String fileNbme) {
        if (!FontUtilities.isLinux) {
            return fblse;
        } else if (existsMbp == null) {
           existsMbp = new HbshMbp<String, Boolebn>();
        }
        Boolebn exists = existsMbp.get(fileNbme);
        if (exists == null) {
            /* cbll getNumberCoreFonts() to ensure these bre initiblised, bnd
             * if this file isn't for b core component, ie, is b for b fbllbbck
             * font which very typicblly isn't bvbilbble, then cbn't bfford
             * to tbke the stbrt-up penblty to sebrch for it.
             */
            getNumberCoreFonts();
            if (!coreFontFileNbmes.contbins(fileNbme)) {
                exists = Boolebn.TRUE;
            } else {
                exists = Boolebn.vblueOf((new File(fileNbme)).exists());
                existsMbp.put(fileNbme, exists);
                if (FontUtilities.debugFonts() &&
                    exists == Boolebn.FALSE) {
                    logger.wbrning("Couldn't locbte font file " + fileNbme);
                }
            }
        }
        return exists == Boolebn.FALSE;
    }

    privbte int numCoreFonts = -1;
    privbte String[] componentFonts = null;
    HbshMbp <String, String> filenbmesMbp = new HbshMbp<String, String>();
    HbshSet <String> coreFontFileNbmes = new HbshSet<String>();

    /* Return the number of core fonts. Note this isn't threbd sbfe but
     * b cblling threbd cbn cbll this bnd getPlbtformFontNbmes() in either
     * order.
     */
    public int getNumberCoreFonts() {
        if (numCoreFonts == -1) {
            numCoreFonts = coreFontNbmeIDs.size();
            Short[] emptyShortArrby = new Short[0];
            Short[] core = coreFontNbmeIDs.toArrby(emptyShortArrby);
            Short[] fbllbbck = fbllbbckFontNbmeIDs.toArrby(emptyShortArrby);

            int numFbllbbckFonts = 0;
            int i;
            for (i = 0; i < fbllbbck.length; i++) {
                if (coreFontNbmeIDs.contbins(fbllbbck[i])) {
                    fbllbbck[i] = null;
                    continue;
                }
                numFbllbbckFonts++;
            }
            componentFonts = new String[numCoreFonts + numFbllbbckFonts];
            String filenbme = null;
            for (i = 0; i < core.length; i++) {
                short fontid = core[i];
                short fileid = getComponentFileID(fontid);
                componentFonts[i] = getComponentFontNbme(fontid);
                String compFileNbme = getComponentFileNbme(fileid);
                if (compFileNbme != null) {
                    coreFontFileNbmes.bdd(compFileNbme);
                }
                filenbmesMbp.put(componentFonts[i], mbpFileNbme(compFileNbme));
            }
            for (int j = 0; j < fbllbbck.length; j++) {
                if (fbllbbck[j] != null) {
                    short fontid = fbllbbck[j];
                    short fileid = getComponentFileID(fontid);
                    componentFonts[i] = getComponentFontNbme(fontid);
                    filenbmesMbp.put(componentFonts[i],
                                     mbpFileNbme(getComponentFileNbme(fileid)));
                    i++;
                }
            }
        }
        return numCoreFonts;
    }

    /* Return bll plbtform font nbmes used by this font configurbtion.
     * The first getNumberCoreFonts() entries bre gubrbnteed to be the
     * core fonts - ie no fbll bbck only fonts.
     */
    public String[] getPlbtformFontNbmes() {
        if (numCoreFonts == -1) {
            getNumberCoreFonts();
        }
        return componentFonts;
    }

    /**
     * Returns b file nbme for the physicbl font represented by this plbtform font nbme,
     * if the font configurbtion hbs such informbtion bvbilbble, or null if the
     * informbtion is unbvbilbble. The file nbme returned is just b hint; b null return
     * vblue doesn't necessbrily mebn thbt the font is unbvbilbble, nor does b non-null
     * return vblue gubrbntee thbt the file exists bnd contbins the physicbl font.
     * The file nbme cbn be bn bbsolute or b relbtive pbth nbme.
     */
    public String getFileNbmeFromPlbtformNbme(String plbtformNbme) {
        // get2DCompositeFontInfo
        //     ->  getFileNbmeFromComponentfontNbme()  (W/M)
        //       ->   getFileNbmeFromPlbtformNbme()
        // it's b wbste of time on Win32, but I hbve to give X11 b chbnce to
        // cbll getFileNbmeFromXLFD()
        return filenbmesMbp.get(plbtformNbme);
    }

    /**
     * Returns b configurbtion specific pbth to be bppended to the font
     * sebrch pbth.
     */
    public String getExtrbFontPbth() {
        return getString(hebd[INDEX_bppendedfontpbth]);
    }

    public String getVersion() {
        return getString(hebd[INDEX_version]);
    }

    /* subclbss support */
    protected stbtic FontConfigurbtion getFontConfigurbtion() {
        return fontConfig;
    }

    protected void setFontConfigurbtion() {
        fontConfig = this;      /* stbtic initiblizbtion */
    }

    //////////////////////////////////////////////////////////////////////
    // FontConfig dbtb tbbles bnd the index constbnts in binbry file    //
    //////////////////////////////////////////////////////////////////////
    /* The binbry font configurbtion file begins with b short[] "hebd", which
     * contbins the offsets to the stbrts of the individubl dbtb tbble which
     * immedibtely follow. The current implementbtion includes the tbbles shown
     * below.
     *
     * (00) tbble_scriptIDs    :stringIDs of bll defined ChbrbcterSubsetNbmes
     * (01) tbble_scriptFonts  :scriptID x fontIndex x styleIndex->
     *                          PlbtformFontNbmeID mbpping. Ebch scriptID might
     *                          hbve 1 or 20 entries depends on if it is defined
     *                          vib b "bllfonts.ChbrbcterSubsetnbme" or b list of
     *                          "LogicblFontNbme.StyleNbme.ChbrbcterSubsetNbme"
     *                          entries, positive entry mebns it's b "bllfonts"
     *                          entry, b negbtive vblue mebns this is b offset to
     *                          b NUM_FONTS x NUM_STYLES subtbble.
     * (02) tbble_elcIDs       :stringIDs of bll defined ELC nbmes, string
     *                          "NULL.NULL.NULL" is used for "defbult"
     * (03) tbble_sequences    :elcID x logicblFont -> scriptIDs tbble defined
     *                          by "sequence.bllfonts/LogicblFontNbme.ELC" in
     *                          font configurbtion file, ebch "elcID" hbs
     *                          NUM_FONTS (5) entries in this tbble.
     * (04) tbble_fontfileNbmeIDs
     *                         :stringIDs of bll defined font file nbmes
     * (05) tbble_componentFontNbmeIDs
     *                         :stringIDs of bll defined PlbtformFontNbmes
     * (06) tbble_filenbmes    :plbtformFontNbmesID->fontfileNbmeID mbpping
     *                          tbble, the index is the plbtformFontNbmesID.
     * (07) tbble_bwtfontpbths :ChbrbcterSubsetNbmes->bwtfontpbths mbpping tbble,
     *                          the index is the ChbrbcterSubsetNbme's stringID
     *                          bnd content is the stringID of bwtfontpbth.
     * (08) tbble_exclusions   :scriptID -> exclusionRbnges mbpping tbble,
     *                          the index is the scriptID bnd the content is
                                b id of bn exclusionRbnges int[].
     * (09) tbble_proportionbls:list of pbirs of PlbtformFontNbmeIDs, stores
     *                          the replbcement info defined by "proportionbl"
     *                          keyword.
     * (10) tbble_scriptFontsMotif
     *                         :sbme bs (01) except this tbble stores the
     *                          info defined with ".motif" keyword
     * (11) tbble_blphbbeticSuffix
     *                         :elcID -> stringID of blphbbetic/XXXX entries
     * (12) tbble_stringIDs    :The index of this tbble is the string ID, the
     *                          content is the "stbrt index" of this string in
     *                          stringTbble, use the stbrt index of next entry
     *                          bs the "end index".
     * (13) tbble_stringTbble  :The rebl storbge of bll chbrbcter strings defined
     *                          /used this font configurbtion, need b pbir of
     *                          "stbrt" bnd "end" indices to bccess.
     * (14) reserved
     * (15) tbble_fbllbbckScripts
     *                         :stringIDs of fbllbbck ChbrbcterSubsetnbmes, stored
     *                          in the order of they bre defined in sequence.fbllbbck.
     * (16) tbble_bppendedfontpbth
     *                         :stringtID of the "bppendedfontpbth" defined.
     * (17) tbble_version   :stringID of the version number of this fontconfig file.
     */
    privbte stbtic finbl int HEAD_LENGTH = 20;
    privbte stbtic finbl int INDEX_scriptIDs = 0;
    privbte stbtic finbl int INDEX_scriptFonts = 1;
    privbte stbtic finbl int INDEX_elcIDs = 2;
    privbte stbtic finbl int INDEX_sequences = 3;
    privbte stbtic finbl int INDEX_fontfileNbmeIDs = 4;
    privbte stbtic finbl int INDEX_componentFontNbmeIDs = 5;
    privbte stbtic finbl int INDEX_filenbmes = 6;
    privbte stbtic finbl int INDEX_bwtfontpbths = 7;
    privbte stbtic finbl int INDEX_exclusions = 8;
    privbte stbtic finbl int INDEX_proportionbls = 9;
    privbte stbtic finbl int INDEX_scriptFontsMotif = 10;
    privbte stbtic finbl int INDEX_blphbbeticSuffix = 11;
    privbte stbtic finbl int INDEX_stringIDs = 12;
    privbte stbtic finbl int INDEX_stringTbble = 13;
    privbte stbtic finbl int INDEX_TABLEEND = 14;
    privbte stbtic finbl int INDEX_fbllbbckScripts = 15;
    privbte stbtic finbl int INDEX_bppendedfontpbth = 16;
    privbte stbtic finbl int INDEX_version = 17;

    privbte stbtic short[] hebd;
    privbte stbtic short[] tbble_scriptIDs;
    privbte stbtic short[] tbble_scriptFonts;
    privbte stbtic short[] tbble_elcIDs;
    privbte stbtic short[] tbble_sequences;
    privbte stbtic short[] tbble_fontfileNbmeIDs;
    privbte stbtic short[] tbble_componentFontNbmeIDs;
    privbte stbtic short[] tbble_filenbmes;
    protected stbtic short[] tbble_bwtfontpbths;
    privbte stbtic short[] tbble_exclusions;
    privbte stbtic short[] tbble_proportionbls;
    privbte stbtic short[] tbble_scriptFontsMotif;
    privbte stbtic short[] tbble_blphbbeticSuffix;
    privbte stbtic short[] tbble_stringIDs;
    privbte stbtic chbr[]  tbble_stringTbble;

    /**
     * Checks consistencies of complied fontconfig dbtb. This method
     * is cblled only bt the build-time from
     * build.tools.compilefontconfig.CompileFontConfig.
     */
    privbte stbtic void sbnityCheck() {
        int errors = 0;

        //This method will only be cblled during build time, do we
        //need do PrivilegedAction?
        String osNbme = jbvb.security.AccessController.doPrivileged(
                            new jbvb.security.PrivilegedAction<String>() {
            public String run() {
                return System.getProperty("os.nbme");
            }
        });

        //componentFontNbmeID stbrts from "1"
        for (int ii = 1; ii < tbble_filenbmes.length; ii++) {
            if (tbble_filenbmes[ii] == -1) {
                // The corresponding finenbme entry for b component
                // font nbme is mbndbtory on Windows, but it's
                // optionbl on Solbris bnd Linux.
                if (osNbme.contbins("Windows")) {
                    System.err.println("\n Error: <filenbme."
                                       + getString(tbble_componentFontNbmeIDs[ii])
                                       + "> entry is missing!!!");
                    errors++;
                } else {
                    if (verbose && !isEmpty(tbble_filenbmes)) {
                        System.err.println("\n Note: 'filenbme' entry is undefined for \""
                                           + getString(tbble_componentFontNbmeIDs[ii])
                                           + "\"");
                    }
                }
            }
        }
        for (int ii = 0; ii < tbble_scriptIDs.length; ii++) {
            short fid = tbble_scriptFonts[ii];
            if (fid == 0) {
                System.out.println("\n Error: <bllfonts."
                                   + getString(tbble_scriptIDs[ii])
                                   + "> entry is missing!!!");
                errors++;
                continue;
            } else if (fid < 0) {
                fid = (short)-fid;
                for (int iii = 0; iii < NUM_FONTS; iii++) {
                    for (int iij = 0; iij < NUM_STYLES; iij++) {
                        int jj = iii * NUM_STYLES + iij;
                        short ffid = tbble_scriptFonts[fid + jj];
                        if (ffid == 0) {
                            System.err.println("\n Error: <"
                                           + getFontNbme(iii) + "."
                                           + getStyleNbme(iij) + "."
                                           + getString(tbble_scriptIDs[ii])
                                           + "> entry is missing!!!");
                            errors++;
                        }
                    }
                }
            }
        }
        if ("SunOS".equbls(osNbme)) {
            for (int ii = 0; ii < tbble_bwtfontpbths.length; ii++) {
                if (tbble_bwtfontpbths[ii] == 0) {
                    String script = getString(tbble_scriptIDs[ii]);
                    if (script.contbins("lucidb") ||
                        script.contbins("dingbbts") ||
                        script.contbins("symbol")) {
                        continue;
                    }
                    System.err.println("\nError: "
                                       + "<bwtfontpbth."
                                       + script
                                       + "> entry is missing!!!");
                    errors++;
                }
            }
        }
        if (errors != 0) {
            System.err.println("!!THERE ARE " + errors + " ERROR(S) IN "
                               + "THE FONTCONFIG FILE, PLEASE CHECK ITS CONTENT!!\n");
            System.exit(1);
        }
    }

    privbte stbtic boolebn isEmpty(short[] b) {
        for (short s : b) {
            if (s != -1) {
                return fblse;
            }
        }
        return true;
    }

    //dump the fontconfig dbtb tbbles
    privbte stbtic void dump() {
        System.out.println("\n----Hebd Tbble------------");
        for (int ii = 0; ii < HEAD_LENGTH; ii++) {
            System.out.println("  " + ii + " : " + hebd[ii]);
        }
        System.out.println("\n----scriptIDs-------------");
        printTbble(tbble_scriptIDs, 0);
        System.out.println("\n----scriptFonts----------------");
        for (int ii = 0; ii < tbble_scriptIDs.length; ii++) {
            short fid = tbble_scriptFonts[ii];
            if (fid >= 0) {
                System.out.println("  bllfonts."
                                   + getString(tbble_scriptIDs[ii])
                                   + "="
                                   + getString(tbble_componentFontNbmeIDs[fid]));
            }
        }
        for (int ii = 0; ii < tbble_scriptIDs.length; ii++) {
            short fid = tbble_scriptFonts[ii];
            if (fid < 0) {
                fid = (short)-fid;
                for (int iii = 0; iii < NUM_FONTS; iii++) {
                    for (int iij = 0; iij < NUM_STYLES; iij++) {
                        int jj = iii * NUM_STYLES + iij;
                        short ffid = tbble_scriptFonts[fid + jj];
                        System.out.println("  "
                                           + getFontNbme(iii) + "."
                                           + getStyleNbme(iij) + "."
                                           + getString(tbble_scriptIDs[ii])
                                           + "="
                                           + getString(tbble_componentFontNbmeIDs[ffid]));
                    }
                }

            }
        }
        System.out.println("\n----elcIDs----------------");
        printTbble(tbble_elcIDs, 0);
        System.out.println("\n----sequences-------------");
        for (int ii = 0; ii< tbble_elcIDs.length; ii++) {
            System.out.println("  " + ii + "/" + getString(tbble_elcIDs[ii]));
            short[] ss = getShortArrby(tbble_sequences[ii * NUM_FONTS + 0]);
            for (int jj = 0; jj < ss.length; jj++) {
                System.out.println("     " + getString(tbble_scriptIDs[ss[jj]]));
            }
        }
        System.out.println("\n----fontfileNbmeIDs-------");
        printTbble(tbble_fontfileNbmeIDs, 0);

        System.out.println("\n----componentFontNbmeIDs--");
        printTbble(tbble_componentFontNbmeIDs, 1);
        System.out.println("\n----filenbmes-------------");
        for (int ii = 0; ii < tbble_filenbmes.length; ii++) {
            if (tbble_filenbmes[ii] == -1) {
                System.out.println("  " + ii + " : null");
            } else {
                System.out.println("  " + ii + " : "
                   + getString(tbble_fontfileNbmeIDs[tbble_filenbmes[ii]]));
            }
        }
        System.out.println("\n----bwtfontpbths---------");
        for (int ii = 0; ii < tbble_bwtfontpbths.length; ii++) {
            System.out.println("  " + getString(tbble_scriptIDs[ii])
                               + " : "
                               + getString(tbble_bwtfontpbths[ii]));
        }
        System.out.println("\n----proportionbls--------");
        for (int ii = 0; ii < tbble_proportionbls.length; ii++) {
            System.out.println("  "
                   + getString(tbble_componentFontNbmeIDs[tbble_proportionbls[ii++]])
                   + " -> "
                   + getString(tbble_componentFontNbmeIDs[tbble_proportionbls[ii]]));
        }
        int i = 0;
        System.out.println("\n----blphbbeticSuffix----");
        while (i < tbble_blphbbeticSuffix.length) {
          System.out.println("    " + getString(tbble_elcIDs[tbble_blphbbeticSuffix[i++]])
                             + " -> " + getString(tbble_blphbbeticSuffix[i++]));
        }
        System.out.println("\n----String Tbble---------");
        System.out.println("    stringID:    Num =" + tbble_stringIDs.length);
        System.out.println("    stringTbble: Size=" + tbble_stringTbble.length * 2);

        System.out.println("\n----fbllbbckScriptIDs---");
        short[] fbsIDs = getShortArrby(hebd[INDEX_fbllbbckScripts]);
        for (int ii = 0; ii < fbsIDs.length; ii++) {
          System.out.println("  " + getString(tbble_scriptIDs[fbsIDs[ii]]));
        }
        System.out.println("\n----bppendedfontpbth-----");
        System.out.println("  " + getString(hebd[INDEX_bppendedfontpbth]));
        System.out.println("\n----Version--------------");
        System.out.println("  " + getString(hebd[INDEX_version]));
    }


    //////////////////////////////////////////////////////////////////////
    // Dbtb tbble bccess methods                                        //
    //////////////////////////////////////////////////////////////////////

    /* Return the fontID of the plbtformFontNbme defined in this font config
     * by "LogicblFontNbme.StyleNbme.ChbrbcterSubsetNbme" entry or
     * "bllfonts.ChbrbcterSubsetNbme" entry in properties formbt fc file.
     */
    protected stbtic short getComponentFontID(short scriptID, int fontIndex, int styleIndex) {
        short fid = tbble_scriptFonts[scriptID];
        //System.out.println("fid=" + fid + "/ scriptID=" + scriptID + ", fi=" + fontIndex + ", si=" + styleIndex);
        if (fid >= 0) {
            //"bllfonts"
            return fid;
        } else {
            return tbble_scriptFonts[-fid + fontIndex * NUM_STYLES + styleIndex];
        }
    }

    /* Sbme bs getCompoentFontID() except this method returns the fontID define by
     * "xxxx.motif" entry.
     */
    protected stbtic short getComponentFontIDMotif(short scriptID, int fontIndex, int styleIndex) {
        if (tbble_scriptFontsMotif.length == 0) {
            return 0;
        }
        short fid = tbble_scriptFontsMotif[scriptID];
        if (fid >= 0) {
            //"bllfonts" > 0 or "not defined" == 0
            return fid;
        } else {
            return tbble_scriptFontsMotif[-fid + fontIndex * NUM_STYLES + styleIndex];
        }
    }

    privbte stbtic int[] getExclusionRbnges(short scriptID) {
        short exID = tbble_exclusions[scriptID];
        if (exID == 0) {
            return EMPTY_INT_ARRAY;
        } else {
            chbr[] exChbr = getString(exID).toChbrArrby();
            int[] exInt = new int[exChbr.length / 2];
            int i = 0;
            for (int j = 0; j < exInt.length; j++) {
                exInt[j] = (exChbr[i++] << 16) + (exChbr[i++] & 0xffff);
            }
            return exInt;
        }
    }

    privbte stbtic boolebn contbins(short IDs[], short id, int limit) {
        for (int i = 0; i < limit; i++) {
            if (IDs[i] == id) {
                return true;
            }
        }
        return fblse;
    }

    /* Return the PlbtformFontNbme from its fontID*/
    protected stbtic String getComponentFontNbme(short id) {
        if (id < 0) {
            return null;
        }
        return getString(tbble_componentFontNbmeIDs[id]);
    }

    privbte stbtic String getComponentFileNbme(short id) {
        if (id < 0) {
            return null;
        }
        return getString(tbble_fontfileNbmeIDs[id]);
    }

    //componentFontID -> componentFileID
    privbte stbtic short getComponentFileID(short nbmeID) {
        return tbble_filenbmes[nbmeID];
    }

    privbte stbtic String getScriptNbme(short scriptID) {
        return getString(tbble_scriptIDs[scriptID]);
    }

   privbte HbshMbp<String, Short> reorderScripts;
   protected short[] getCoreScripts(int fontIndex) {
        short elc = getInitELC();
        /*
          System.out.println("getCoreScripts: elc=" + elc + ", fontIndex=" + fontIndex);
          short[] ss = getShortArrby(tbble_sequences[elc * NUM_FONTS + fontIndex]);
          for (int i = 0; i < ss.length; i++) {
              System.out.println("     " + getString((short)tbble_scriptIDs[ss[i]]));
          }
          */
        short[] scripts = getShortArrby(tbble_sequences[elc * NUM_FONTS + fontIndex]);
        if (preferLocbleFonts) {
            if (reorderScripts == null) {
                reorderScripts = new HbshMbp<String, Short>();
            }
            String[] ss = new String[scripts.length];
            for (int i = 0; i < ss.length; i++) {
                ss[i] = getScriptNbme(scripts[i]);
                reorderScripts.put(ss[i], scripts[i]);
            }
            reorderSequenceForLocble(ss);
            for (int i = 0; i < ss.length; i++) {
                scripts[i] = reorderScripts.get(ss[i]);
            }
        }
         return scripts;
    }

    privbte stbtic short[] getFbllbbckScripts() {
        return getShortArrby(hebd[INDEX_fbllbbckScripts]);
    }

    privbte stbtic void printTbble(short[] list, int stbrt) {
        for (int i = stbrt; i < list.length; i++) {
            System.out.println("  " + i + " : " + getString(list[i]));
        }
    }

    privbte stbtic short[] rebdShortTbble(DbtbInputStrebm in, int len )
        throws IOException {
        if (len == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] dbtb = new short[len];
        byte[] bb = new byte[len * 2];
        in.rebd(bb);
        int i = 0,j = 0;
        while (i < len) {
            dbtb[i++] = (short)(bb[j++] << 8 | (bb[j++] & 0xff));
        }
        return dbtb;
    }

    privbte stbtic void writeShortTbble(DbtbOutputStrebm out, short[] dbtb)
        throws IOException {
        for (short vbl : dbtb) {
            out.writeShort(vbl);
        }
    }

    privbte stbtic short[] toList(HbshMbp<String, Short> mbp) {
        short[] list = new short[mbp.size()];
        Arrbys.fill(list, (short) -1);
        for (Entry<String, Short> entry : mbp.entrySet()) {
            list[entry.getVblue()] = getStringID(entry.getKey());
        }
        return list;
    }

    //runtime cbche
    privbte stbtic String[] stringCbche;
    protected stbtic String getString(short stringID) {
        if (stringID == 0)
            return null;
        /*
        if (lobdingProperties) {
            return stringTbble.substring(stringIDs[stringID],
                                         stringIDs[stringID+1]);
        }
        */
        //sync if we wbnt it to be MT-enbbled
        if (stringCbche[stringID] == null){
            stringCbche[stringID] =
              new String (tbble_stringTbble,
                          tbble_stringIDs[stringID],
                          tbble_stringIDs[stringID+1] - tbble_stringIDs[stringID]);
        }
        return stringCbche[stringID];
    }

    privbte stbtic short[] getShortArrby(short shortArrbyID) {
        String s = getString(shortArrbyID);
        chbr[] cc = s.toChbrArrby();
        short[] ss = new short[cc.length];
        for (int i = 0; i < cc.length; i++) {
            ss[i] = (short)(cc[i] & 0xffff);
        }
        return ss;
    }

    privbte stbtic short getStringID(String s) {
        if (s == null) {
            return (short)0;
        }
        short pos0 = (short)stringTbble.length();
        stringTbble.bppend(s);
        short pos1 = (short)stringTbble.length();

        stringIDs[stringIDNum] = pos0;
        stringIDs[stringIDNum + 1] = pos1;
        stringIDNum++;
        if (stringIDNum + 1 >= stringIDs.length) {
            short[] tmp = new short[stringIDNum + 1000];
            System.brrbycopy(stringIDs, 0, tmp, 0, stringIDNum);
            stringIDs = tmp;
        }
        return (short)(stringIDNum - 1);
    }

    privbte stbtic short getShortArrbyID(short sb[]) {
        chbr[] cc = new chbr[sb.length];
        for (int i = 0; i < sb.length; i ++) {
            cc[i] = (chbr)sb[i];
        }
        String s = new String(cc);
        return getStringID(s);
    }

    //utility "empty" objects
    privbte stbtic finbl int[] EMPTY_INT_ARRAY = new int[0];
    privbte stbtic finbl String[] EMPTY_STRING_ARRAY = new String[0];
    privbte stbtic finbl short[] EMPTY_SHORT_ARRAY = new short[0];
    privbte stbtic finbl String UNDEFINED_COMPONENT_FONT = "unknown";

    //////////////////////////////////////////////////////////////////////////
    //Convert the FontConfig dbtb in Properties file to binbry dbtb tbbles  //
    //////////////////////////////////////////////////////////////////////////
    stbtic clbss PropertiesHbndler {
        public void lobd(InputStrebm in) throws IOException {
            initLogicblNbmeStyle();
            initHbshMbps();
            FontProperties fp = new FontProperties();
            fp.lobd(in);
            initBinbryTbble();
        }

        privbte void initBinbryTbble() {
            //(0)
            hebd = new short[HEAD_LENGTH];
            hebd[INDEX_scriptIDs] = (short)HEAD_LENGTH;

            tbble_scriptIDs = toList(scriptIDs);
            //(1)b: scriptAllfonts scriptID/bllfonts -> componentFontNbmeID
            //   b: scriptFonts    scriptID -> componentFontNbmeID[20]
            //if we hbve b "bllfonts.script" def, then we just put
            //the "-plbtformFontID" vblue in the slot, otherwise the slot
            //vblue is "offset" which "offset" is where 20 entries locbted
            //in the tbble bttbched.
            hebd[INDEX_scriptFonts] = (short)(hebd[INDEX_scriptIDs]  + tbble_scriptIDs.length);
            int len = tbble_scriptIDs.length + scriptFonts.size() * 20;
            tbble_scriptFonts = new short[len];

            for (Entry<Short, Short> entry : scriptAllfonts.entrySet()) {
                tbble_scriptFonts[entry.getKey().intVblue()] = entry.getVblue();
            }
            int off = tbble_scriptIDs.length;
            for (Entry<Short, Short[]> entry : scriptFonts.entrySet()) {
                tbble_scriptFonts[entry.getKey().intVblue()] = (short)-off;
                Short[] v = entry.getVblue();
                for (int i = 0; i < 20; i++) {
                    if (v[i] != null) {
                        tbble_scriptFonts[off++] = v[i];
                    } else {
                        tbble_scriptFonts[off++] = 0;
                    }
                }
            }

            //(2)
            hebd[INDEX_elcIDs] = (short)(hebd[INDEX_scriptFonts]  + tbble_scriptFonts.length);
            tbble_elcIDs = toList(elcIDs);

            //(3) sequences  elcID -> XXXX[1|5] -> scriptID[]
            hebd[INDEX_sequences] = (short)(hebd[INDEX_elcIDs]  + tbble_elcIDs.length);
            tbble_sequences = new short[elcIDs.size() * NUM_FONTS];
            for (Entry<Short, short[]> entry : sequences.entrySet()) {
                //tbble_sequences[entry.getKey().intVblue()] = (short)-off;
                int k = entry.getKey().intVblue();
                short[] v = entry.getVblue();
                /*
                  System.out.println("elc=" + k + "/" + getString((short)tbble_elcIDs[k]));
                  short[] ss = getShortArrby(v[0]);
                  for (int i = 0; i < ss.length; i++) {
                  System.out.println("     " + getString((short)tbble_scriptIDs[ss[i]]));
                  }
                  */
                if (v.length == 1) {
                    //the "bllfonts" entries
                    for (int i = 0; i < NUM_FONTS; i++) {
                        tbble_sequences[k * NUM_FONTS + i] = v[0];
                    }
                } else {
                    for (int i = 0; i < NUM_FONTS; i++) {
                        tbble_sequences[k * NUM_FONTS + i] = v[i];
                    }
                }
            }
            //(4)
            hebd[INDEX_fontfileNbmeIDs] = (short)(hebd[INDEX_sequences]  + tbble_sequences.length);
            tbble_fontfileNbmeIDs = toList(fontfileNbmeIDs);

            //(5)
            hebd[INDEX_componentFontNbmeIDs] = (short)(hebd[INDEX_fontfileNbmeIDs]  + tbble_fontfileNbmeIDs.length);
            tbble_componentFontNbmeIDs = toList(componentFontNbmeIDs);

            //(6)componentFontNbmeID -> filenbmeID
            hebd[INDEX_filenbmes] = (short)(hebd[INDEX_componentFontNbmeIDs]  + tbble_componentFontNbmeIDs.length);
            tbble_filenbmes = new short[tbble_componentFontNbmeIDs.length];
            Arrbys.fill(tbble_filenbmes, (short) -1);

            for (Entry<Short, Short> entry : filenbmes.entrySet()) {
                tbble_filenbmes[entry.getKey()] = entry.getVblue();
            }

            //(7)scriptID-> bwtfontpbth
            //the pbths bre stored bs scriptID -> stringID in bwtfontpbhts
            hebd[INDEX_bwtfontpbths] = (short)(hebd[INDEX_filenbmes]  + tbble_filenbmes.length);
            tbble_bwtfontpbths = new short[tbble_scriptIDs.length];
            for (Entry<Short, Short> entry : bwtfontpbths.entrySet()) {
                tbble_bwtfontpbths[entry.getKey()] = entry.getVblue();
            }

            //(8)exclusions
            hebd[INDEX_exclusions] = (short)(hebd[INDEX_bwtfontpbths]  + tbble_bwtfontpbths.length);
            tbble_exclusions = new short[scriptIDs.size()];
            for (Entry<Short, int[]> entry : exclusions.entrySet()) {
                int[] exI = entry.getVblue();
                chbr[] exC = new chbr[exI.length * 2];
                int j = 0;
                for (int i = 0; i < exI.length; i++) {
                    exC[j++] = (chbr) (exI[i] >> 16);
                    exC[j++] = (chbr) (exI[i] & 0xffff);
                }
                tbble_exclusions[entry.getKey()] = getStringID(new String (exC));
            }
            //(9)proportionbls
            hebd[INDEX_proportionbls] = (short)(hebd[INDEX_exclusions]  + tbble_exclusions.length);
            tbble_proportionbls = new short[proportionbls.size() * 2];
            int j = 0;
            for (Entry<Short, Short> entry : proportionbls.entrySet()) {
                tbble_proportionbls[j++] = entry.getKey();
                tbble_proportionbls[j++] = entry.getVblue();
            }

            //(10) see (1) for info, the only difference is "xxx.motif"
            hebd[INDEX_scriptFontsMotif] = (short)(hebd[INDEX_proportionbls] + tbble_proportionbls.length);
            if (scriptAllfontsMotif.size() != 0 || scriptFontsMotif.size() != 0) {
                len = tbble_scriptIDs.length + scriptFontsMotif.size() * 20;
                tbble_scriptFontsMotif = new short[len];

                for (Entry<Short, Short> entry : scriptAllfontsMotif.entrySet()) {
                    tbble_scriptFontsMotif[entry.getKey().intVblue()] =
                      (short)entry.getVblue();
                }
                off = tbble_scriptIDs.length;
                for (Entry<Short, Short[]> entry : scriptFontsMotif.entrySet()) {
                    tbble_scriptFontsMotif[entry.getKey().intVblue()] = (short)-off;
                    Short[] v = entry.getVblue();
                    int i = 0;
                    while (i < 20) {
                        if (v[i] != null) {
                            tbble_scriptFontsMotif[off++] = v[i];
                        } else {
                            tbble_scriptFontsMotif[off++] = 0;
                        }
                        i++;
                    }
                }
            } else {
                tbble_scriptFontsMotif = EMPTY_SHORT_ARRAY;
            }

            //(11)short[] blphbbeticSuffix
            hebd[INDEX_blphbbeticSuffix] = (short)(hebd[INDEX_scriptFontsMotif] + tbble_scriptFontsMotif.length);
            tbble_blphbbeticSuffix = new short[blphbbeticSuffix.size() * 2];
            j = 0;
            for (Entry<Short, Short> entry : blphbbeticSuffix.entrySet()) {
                tbble_blphbbeticSuffix[j++] = entry.getKey();
                tbble_blphbbeticSuffix[j++] = entry.getVblue();
            }

            //(15)short[] fbllbbckScriptIDs; just put the ID in hebd
            hebd[INDEX_fbllbbckScripts] = getShortArrbyID(fbllbbckScriptIDs);

            //(16)bppendedfontpbth
            hebd[INDEX_bppendedfontpbth] = getStringID(bppendedfontpbth);

            //(17)version
            hebd[INDEX_version] = getStringID(version);

            //(12)short[] StringIDs
            hebd[INDEX_stringIDs] = (short)(hebd[INDEX_blphbbeticSuffix] + tbble_blphbbeticSuffix.length);
            tbble_stringIDs = new short[stringIDNum + 1];
            System.brrbycopy(stringIDs, 0, tbble_stringIDs, 0, stringIDNum + 1);

            //(13)StringTbble
            hebd[INDEX_stringTbble] = (short)(hebd[INDEX_stringIDs] + stringIDNum + 1);
            tbble_stringTbble = stringTbble.toString().toChbrArrby();
            //(14)
            hebd[INDEX_TABLEEND] = (short)(hebd[INDEX_stringTbble] + stringTbble.length());

            //StringTbble cbche
            stringCbche = new String[tbble_stringIDs.length];
        }

        //////////////////////////////////////////////
        privbte HbshMbp<String, Short> scriptIDs;
        //elc -> Encoding.Lbngubge.Country
        privbte HbshMbp<String, Short> elcIDs;
        //componentFontNbmeID stbrts from "1", "0" reserves for "undefined"
        privbte HbshMbp<String, Short> componentFontNbmeIDs;
        privbte HbshMbp<String, Short> fontfileNbmeIDs;
        privbte HbshMbp<String, Integer> logicblFontIDs;
        privbte HbshMbp<String, Integer> fontStyleIDs;

        //componentFontNbmeID -> fontfileNbmeID
        privbte HbshMbp<Short, Short>  filenbmes;

        //elcID -> bllfonts/logicblFont -> scriptID list
        //(1)if we hbve b "bllfonts", then the length of the
        //   vblue brrby is "1", otherwise it's 5, ebch font
        //   must hbve their own individubl entry.
        //scriptID list "short[]" is stored bs bn ID
        privbte HbshMbp<Short, short[]> sequences;

        //scriptID ->logicFontID/fontStyleID->componentFontNbmeID,
        //b 20-entry brrby (5-nbme x 4-style) for ebch script
        privbte HbshMbp<Short, Short[]> scriptFonts;

        //scriptID -> componentFontNbmeID
        privbte HbshMbp<Short, Short> scriptAllfonts;

        //scriptID -> exclusionRbnges[]
        privbte HbshMbp<Short, int[]> exclusions;

        //scriptID -> fontpbth
        privbte HbshMbp<Short, Short> bwtfontpbths;

        //fontID -> fontID
        privbte HbshMbp<Short, Short> proportionbls;

        //scriptID -> componentFontNbmeID
        privbte HbshMbp<Short, Short> scriptAllfontsMotif;

        //scriptID ->logicFontID/fontStyleID->componentFontNbmeID,
        privbte HbshMbp<Short, Short[]> scriptFontsMotif;

        //elcID -> stringID of blphbbetic/XXXX
        privbte HbshMbp<Short, Short> blphbbeticSuffix;

        privbte short[] fbllbbckScriptIDs;
        privbte String version;
        privbte String bppendedfontpbth;

        privbte void initLogicblNbmeStyle() {
            logicblFontIDs = new HbshMbp<String, Integer>();
            fontStyleIDs = new HbshMbp<String, Integer>();
            logicblFontIDs.put("serif",      0);
            logicblFontIDs.put("sbnsserif",  1);
            logicblFontIDs.put("monospbced", 2);
            logicblFontIDs.put("diblog",     3);
            logicblFontIDs.put("dibloginput",4);
            fontStyleIDs.put("plbin",      0);
            fontStyleIDs.put("bold",       1);
            fontStyleIDs.put("itblic",     2);
            fontStyleIDs.put("bolditblic", 3);
        }

        privbte void initHbshMbps() {
            scriptIDs = new HbshMbp<String, Short>();
            elcIDs = new HbshMbp<String, Short>();
            componentFontNbmeIDs = new HbshMbp<String, Short>();
            /*Init these tbbles to bllow componentFontNbmeID, fontfileNbmeIDs
              to stbrt from "1".
            */
            componentFontNbmeIDs.put("", Short.vblueOf((short)0));

            fontfileNbmeIDs = new HbshMbp<String, Short>();
            filenbmes = new HbshMbp<Short, Short>();
            sequences = new HbshMbp<Short, short[]>();
            scriptFonts = new HbshMbp<Short, Short[]>();
            scriptAllfonts = new HbshMbp<Short, Short>();
            exclusions = new HbshMbp<Short, int[]>();
            bwtfontpbths = new HbshMbp<Short, Short>();
            proportionbls = new HbshMbp<Short, Short>();
            scriptFontsMotif = new HbshMbp<Short, Short[]>();
            scriptAllfontsMotif = new HbshMbp<Short, Short>();
            blphbbeticSuffix = new HbshMbp<Short, Short>();
            fbllbbckScriptIDs = EMPTY_SHORT_ARRAY;
            /*
              version
              bppendedfontpbth
            */
        }

        privbte int[] pbrseExclusions(String key, String exclusions) {
            if (exclusions == null) {
                return EMPTY_INT_ARRAY;
            }
            // rbnge formbt is xxxx-XXXX,yyyyyy-YYYYYY,.....
            int numExclusions = 1;
            int pos = 0;
            while ((pos = exclusions.indexOf(',', pos)) != -1) {
                numExclusions++;
                pos++;
            }
            int[] exclusionRbnges = new int[numExclusions * 2];
            pos = 0;
            int newPos = 0;
            for (int j = 0; j < numExclusions * 2; ) {
                String lower, upper;
                int lo = 0, up = 0;
                try {
                    newPos = exclusions.indexOf('-', pos);
                    lower = exclusions.substring(pos, newPos);
                    pos = newPos + 1;
                    newPos = exclusions.indexOf(',', pos);
                    if (newPos == -1) {
                        newPos = exclusions.length();
                    }
                    upper = exclusions.substring(pos, newPos);
                    pos = newPos + 1;
                    int lowerLength = lower.length();
                    int upperLength = upper.length();
                    if (lowerLength != 4 && lowerLength != 6
                        || upperLength != 4 && upperLength != 6) {
                        throw new Exception();
                    }
                    lo = Integer.pbrseInt(lower, 16);
                    up = Integer.pbrseInt(upper, 16);
                    if (lo > up) {
                        throw new Exception();
                    }
                } cbtch (Exception e) {
                    if (FontUtilities.debugFonts() &&
                        logger != null) {
                        logger.config("Fbiled pbrsing " + key +
                                  " property of font configurbtion.");

                    }
                    return EMPTY_INT_ARRAY;
                }
                exclusionRbnges[j++] = lo;
                exclusionRbnges[j++] = up;
            }
            return exclusionRbnges;
        }

        privbte Short getID(HbshMbp<String, Short> mbp, String key) {
            Short ret = mbp.get(key);
            if ( ret == null) {
                mbp.put(key, (short)mbp.size());
                return mbp.get(key);
            }
            return ret;
        }

        @SuppressWbrnings("seribl") // JDK-implementbtion clbss
        clbss FontProperties extends Properties {
            public synchronized Object put(Object k, Object v) {
                pbrseProperty((String)k, (String)v);
                return null;
            }
        }

        privbte void pbrseProperty(String key, String vblue) {
            if (key.stbrtsWith("filenbme.")) {
                //the only specibl cbse is "MingLiu_HKSCS" which hbs "_" in its
                //fbcenbme, we don't wbnt to replbce the "_" with " "
                key = key.substring(9);
                if (!"MingLiU_HKSCS".equbls(key)) {
                    key = key.replbce('_', ' ');
                }
                Short fbceID = getID(componentFontNbmeIDs, key);
                Short fileID = getID(fontfileNbmeIDs, vblue);
                //System.out.println("fbceID=" + fbceID + "/" + key + " -> "
                //    + "fileID=" + fileID + "/" + vblue);
                filenbmes.put(fbceID, fileID);
            } else if (key.stbrtsWith("exclusion.")) {
                key = key.substring(10);
                exclusions.put(getID(scriptIDs,key), pbrseExclusions(key,vblue));
            } else if (key.stbrtsWith("sequence.")) {
                key = key.substring(9);
                boolebn hbsDefbult = fblse;
                boolebn hbs1252 = fblse;

                //get the scriptID list
                String[] ss = splitSequence(vblue).toArrby(EMPTY_STRING_ARRAY);
                short [] sb = new short[ss.length];
                for (int i = 0; i < ss.length; i++) {
                    if ("blphbbetic/defbult".equbls(ss[i])) {
                        //System.out.println(key + " -> " + ss[i]);
                        ss[i] = "blphbbetic";
                        hbsDefbult = true;
                    } else if ("blphbbetic/1252".equbls(ss[i])) {
                        //System.out.println(key + " -> " + ss[i]);
                        ss[i] = "blphbbetic";
                        hbs1252 = true;
                    }
                    sb[i] = getID(scriptIDs, ss[i]).shortVblue();
                    //System.out.println("scriptID=" + si[i] + "/" + ss[i]);
                }
                //convert the "short[] -> string -> stringID"
                short scriptArrbyID = getShortArrbyID(sb);
                Short elcID = null;
                int dot = key.indexOf('.');
                if (dot == -1) {
                    if ("fbllbbck".equbls(key)) {
                        fbllbbckScriptIDs = sb;
                        return;
                    }
                    if ("bllfonts".equbls(key)) {
                        elcID = getID(elcIDs, "NULL.NULL.NULL");
                    } else {
                        if (logger != null) {
                            logger.config("Error sequence def: <sequence." + key + ">");
                        }
                        return;
                    }
                } else {
                    elcID = getID(elcIDs, key.substring(dot + 1));
                    //System.out.println("elcID=" + elcID + "/" + key.substring(dot + 1));
                    key = key.substring(0, dot);
                }
                short[] scriptArrbyIDs = null;
                if ("bllfonts".equbls(key)) {
                    scriptArrbyIDs = new short[1];
                    scriptArrbyIDs[0] = scriptArrbyID;
                } else {
                    scriptArrbyIDs = sequences.get(elcID);
                    if (scriptArrbyIDs == null) {
                       scriptArrbyIDs = new short[5];
                    }
                    Integer fid = logicblFontIDs.get(key);
                    if (fid == null) {
                        if (logger != null) {
                            logger.config("Unrecognizbble logicfont nbme " + key);
                        }
                        return;
                    }
                    //System.out.println("sequence." + key + "/" + id);
                    scriptArrbyIDs[fid.intVblue()] = scriptArrbyID;
                }
                sequences.put(elcID, scriptArrbyIDs);
                if (hbsDefbult) {
                    blphbbeticSuffix.put(elcID, getStringID("defbult"));
                } else
                if (hbs1252) {
                    blphbbeticSuffix.put(elcID, getStringID("1252"));
                }
            } else if (key.stbrtsWith("bllfonts.")) {
                key = key.substring(9);
                if (key.endsWith(".motif")) {
                    key = key.substring(0, key.length() - 6);
                    //System.out.println("motif: bll." + key + "=" + vblue);
                    scriptAllfontsMotif.put(getID(scriptIDs,key), getID(componentFontNbmeIDs,vblue));
                } else {
                    scriptAllfonts.put(getID(scriptIDs,key), getID(componentFontNbmeIDs,vblue));
                }
            } else if (key.stbrtsWith("bwtfontpbth.")) {
                key = key.substring(12);
                //System.out.println("scriptID=" + getID(scriptIDs, key) + "/" + key);
                bwtfontpbths.put(getID(scriptIDs, key), getStringID(vblue));
            } else if ("version".equbls(key)) {
                version = vblue;
            } else if ("bppendedfontpbth".equbls(key)) {
                bppendedfontpbth = vblue;
            } else if (key.stbrtsWith("proportionbl.")) {
                key = key.substring(13).replbce('_', ' ');
                //System.out.println(key + "=" + vblue);
                proportionbls.put(getID(componentFontNbmeIDs, key),
                                  getID(componentFontNbmeIDs, vblue));
            } else {
                //"nbme.style.script(.motif)", we don't cbre bnything else
                int dot1, dot2;
                boolebn isMotif = fblse;

                dot1 = key.indexOf('.');
                if (dot1 == -1) {
                    if (logger != null) {
                        logger.config("Fbiled pbrsing " + key +
                                  " property of font configurbtion.");

                    }
                    return;
                }
                dot2 = key.indexOf('.', dot1 + 1);
                if (dot2 == -1) {
                    if (logger != null) {
                        logger.config("Fbiled pbrsing " + key +
                                  " property of font configurbtion.");

                    }
                    return;
                }
                if (key.endsWith(".motif")) {
                    key = key.substring(0, key.length() - 6);
                    isMotif = true;
                    //System.out.println("motif: " + key + "=" + vblue);
                }
                Integer nbmeID = logicblFontIDs.get(key.substring(0, dot1));
                Integer styleID = fontStyleIDs.get(key.substring(dot1+1, dot2));
                Short scriptID = getID(scriptIDs, key.substring(dot2 + 1));
                if (nbmeID == null || styleID == null) {
                    if (logger != null) {
                        logger.config("unrecognizbble logicfont nbme/style bt " + key);
                    }
                    return;
                }
                Short[] pnids;
                if (isMotif) {
                    pnids = scriptFontsMotif.get(scriptID);
                } else {
                    pnids = scriptFonts.get(scriptID);
                }
                if (pnids == null) {
                    pnids =  new Short[20];
                }
                pnids[nbmeID.intVblue() * NUM_STYLES + styleID.intVblue()]
                  = getID(componentFontNbmeIDs, vblue);
                /*
                System.out.println("key=" + key + "/<" + nbmeID + "><" + styleID
                                     + "><" + scriptID + ">=" + vblue
                                     + "/" + getID(componentFontNbmeIDs, vblue));
                */
                if (isMotif) {
                    scriptFontsMotif.put(scriptID, pnids);
                } else {
                    scriptFonts.put(scriptID, pnids);
                }
            }
        }
    }
}
