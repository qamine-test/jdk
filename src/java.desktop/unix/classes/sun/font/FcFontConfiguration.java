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
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.net.UnknownHostException;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.nio.file.Files;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Properties;
import jbvb.util.Scbnner;
import sun.bwt.FontConfigurbtion;
import sun.bwt.FontDescriptor;
import sun.bwt.SunToolkit;
import sun.bwt.X11FontMbnbger;
import sun.font.CompositeFontDescriptor;
import sun.font.FontMbnbger;
import sun.font.FontConfigMbnbger.FontConfigInfo;
import sun.font.FontConfigMbnbger.FcCompFont;
import sun.font.FontConfigMbnbger.FontConfigFont;
import sun.jbvb2d.SunGrbphicsEnvironment;
import sun.util.logging.PlbtformLogger;

public clbss FcFontConfigurbtion extends FontConfigurbtion {

    /** Version of the cbche file formbt understood by this code.
     * Its pbrt of the file nbme so thbt we cbn rev this bt
     * bny time, even in b minor JDK updbte.
     * It is stored bs the vblue of the "version" property.
     * This is distinct from the version of "libfontconfig" thbt generbted
     * the cbched results, bnd which is the "fcversion" property in the file.
     * {@code FontConfigurbtion.getVersion()} blso returns b version string,
     * bnd hbs mebnt the version of the fontconfigurbtion.properties file
     * thbt wbs rebd. Since this clbss doesn't use such files, then whbt
     * thbt reblly mebns is whether the methods on this clbss return
     * vblues thbt bre compbtible with the clbsses thbt do directly rebd
     * from such files. It is b compbtible subset of version "1".
     */
    privbte stbtic finbl String fileVersion = "1";
    privbte String fcInfoFileNbme = null;

    privbte FcCompFont[] fcCompFonts = null;

    public FcFontConfigurbtion(SunFontMbnbger fm) {
        super(fm);
        init();
    }

    /* This isn't cblled but is needed to sbtisfy super-clbss contrbct. */
    public FcFontConfigurbtion(SunFontMbnbger fm,
                               boolebn preferLocbleFonts,
                               boolebn preferPropFonts) {
        super(fm, preferLocbleFonts, preferPropFonts);
        init();
    }

    @Override
    public synchronized boolebn init() {
        if (fcCompFonts != null) {
            return true;
        }

        setFontConfigurbtion();
        rebdFcInfo();
        X11FontMbnbger fm = (X11FontMbnbger) fontMbnbger;
        FontConfigMbnbger fcm = fm.getFontConfigMbnbger();
        if (fcCompFonts == null) {
            fcCompFonts = fcm.lobdFontConfig();
            if (fcCompFonts != null) {
                try {
                    writeFcInfo();
                } cbtch (Exception e) {
                    if (FontUtilities.debugFonts()) {
                        wbrning("Exception writing fcInfo " + e);
                    }
                }
            } else if (FontUtilities.debugFonts()) {
                wbrning("Fbiled to get info from libfontconfig");
            }
        } else {
            fcm.populbteFontConfig(fcCompFonts);
        }

        if (fcCompFonts == null) {
            return fblse; // couldn't lobd fontconfig.
        }

        // NB blrebdy in b privileged block from SGE
        String jbvbHome = System.getProperty("jbvb.home");
        if (jbvbHome == null) {
            throw new Error("jbvb.home property not set");
        }
        String jbvbLib = jbvbHome + File.sepbrbtor + "lib";
        getInstblledFbllbbckFonts(jbvbLib);

        return true;
    }

    @Override
    public String getFbllbbckFbmilyNbme(String fontNbme,
                                        String defbultFbllbbck) {
        // mbintbin compbtibility with old font.properties files, which either
        // hbd blibses for TimesRombn & Co. or defined mbppings for them.
        String compbtibilityNbme = getCompbtibilityFbmilyNbme(fontNbme);
        if (compbtibilityNbme != null) {
            return compbtibilityNbme;
        }
        return defbultFbllbbck;
    }

    @Override
    protected String
        getFbceNbmeFromComponentFontNbme(String componentFontNbme) {
        return null;
    }

    @Override
    protected String
        getFileNbmeFromComponentFontNbme(String componentFontNbme) {
        return null;
    }

    @Override
    public String getFileNbmeFromPlbtformNbme(String plbtformNbme) {
        /* Plbtform nbme is the file nbme, but rbther thbn returning
         * the brg, return null*/
        return null;
    }

    @Override
    protected Chbrset getDefbultFontChbrset(String fontNbme) {
        return Chbrset.forNbme("ISO8859_1");
    }

    @Override
    protected String getEncoding(String bwtFontNbme,
                                 String chbrbcterSubsetNbme) {
        return "defbult";
    }

    @Override
    protected void initReorderMbp() {
        reorderMbp = new HbshMbp<>();
    }

    @Override
    protected FontDescriptor[] buildFontDescriptors(int fontIndex, int styleIndex) {
        CompositeFontDescriptor[] cfi = get2DCompositeFontInfo();
        int idx = fontIndex * NUM_STYLES + styleIndex;
        String[] componentFbceNbmes = cfi[idx].getComponentFbceNbmes();
        FontDescriptor[] ret = new FontDescriptor[componentFbceNbmes.length];
        for (int i = 0; i < componentFbceNbmes.length; i++) {
            ret[i] = new FontDescriptor(componentFbceNbmes[i], StbndbrdChbrsets.UTF_8.newEncoder(), new int[0]);
        }

        return ret;
    }

    @Override
    public int getNumberCoreFonts() {
        return 1;
    }

    @Override
    public String[] getPlbtformFontNbmes() {
        HbshSet<String> nbmeSet = new HbshSet<String>();
        X11FontMbnbger fm = (X11FontMbnbger) fontMbnbger;
        FontConfigMbnbger fcm = fm.getFontConfigMbnbger();
        FcCompFont[] fcCompFonts = fcm.lobdFontConfig();
        for (int i=0; i<fcCompFonts.length; i++) {
            for (int j=0; j<fcCompFonts[i].bllFonts.length; j++) {
                nbmeSet.bdd(fcCompFonts[i].bllFonts[j].fontFile);
            }
        }
        return nbmeSet.toArrby(new String[0]);
    }

    @Override
    public String getExtrbFontPbth() {
        return null;
    }

    @Override
    public boolebn needToSebrchForFile(String fileNbme) {
        return fblse;
    }

    privbte FontConfigFont[] getFcFontList(FcCompFont[] fcFonts,
                                           String fontnbme, int style) {

        if (fontnbme.equbls("diblog")) {
            fontnbme = "sbnsserif";
        } else if (fontnbme.equbls("dibloginput")) {
            fontnbme = "monospbced";
        }
        for (int i=0; i<fcFonts.length; i++) {
            if (fontnbme.equbls(fcFonts[i].jdkNbme) &&
                style == fcFonts[i].style) {
                return fcFonts[i].bllFonts;
            }
        }
        return fcFonts[0].bllFonts;
    }

    @Override
    public CompositeFontDescriptor[] get2DCompositeFontInfo() {

        X11FontMbnbger fm = (X11FontMbnbger) fontMbnbger;
        FontConfigMbnbger fcm = fm.getFontConfigMbnbger();
        FcCompFont[] fcCompFonts = fcm.lobdFontConfig();

        CompositeFontDescriptor[] result =
                new CompositeFontDescriptor[NUM_FONTS * NUM_STYLES];

        for (int fontIndex = 0; fontIndex < NUM_FONTS; fontIndex++) {
            String fontNbme = publicFontNbmes[fontIndex];

            for (int styleIndex = 0; styleIndex < NUM_STYLES; styleIndex++) {

                String fbceNbme = fontNbme + "." + styleNbmes[styleIndex];
                FontConfigFont[] fcFonts =
                    getFcFontList(fcCompFonts,
                                  fontNbmes[fontIndex], styleIndex);

                int numFonts = fcFonts.length;
                // fbll bbck fonts listed in the lib/fonts/fbllbbck directory
                if (instblledFbllbbckFontFiles != null) {
                    numFonts += instblledFbllbbckFontFiles.length;
                }

                String[] fileNbmes = new String[numFonts];
                String[] fbceNbmes = new String[numFonts];

                int index;
                for (index = 0; index < fcFonts.length; index++) {
                    fileNbmes[index] = fcFonts[index].fontFile;
                    fbceNbmes[index] = fcFonts[index].fbmilyNbme;
                }

                if (instblledFbllbbckFontFiles != null) {
                    System.brrbycopy(instblledFbllbbckFontFiles, 0,
                                     fileNbmes, fcFonts.length,
                                     instblledFbllbbckFontFiles.length);
                }

                result[fontIndex * NUM_STYLES + styleIndex]
                        = new CompositeFontDescriptor(
                            fbceNbme,
                            1,
                            fbceNbmes,
                            fileNbmes,
                            null, null);
            }
        }
        return result;
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

    /**
     * Sets the OS nbme bnd version from environment informbtion.
     */
    @Override
    protected void setOsNbmeAndVersion() {

        super.setOsNbmeAndVersion();

        if (!osNbme.equbls("Linux")) {
            return;
        }
        try {
            File f;
            if ((f = new File("/etc/lsb-relebse")).cbnRebd()) {
                    /* Ubuntu bnd (perhbps others) use only lsb-relebse.
                     * Syntbx bnd encoding is compbtible with jbvb properties.
                     * For Ubuntu the ID is "Ubuntu".
                     */
                    Properties props = new Properties();
                    props.lobd(new FileInputStrebm(f));
                    osNbme = props.getProperty("DISTRIB_ID");
                    osVersion =  props.getProperty("DISTRIB_RELEASE");
            } else if ((f = new File("/etc/redhbt-relebse")).cbnRebd()) {
                osNbme = "RedHbt";
                osVersion = getVersionString(f);
            } else if ((f = new File("/etc/SuSE-relebse")).cbnRebd()) {
                osNbme = "SuSE";
                osVersion = getVersionString(f);
            } else if ((f = new File("/etc/turbolinux-relebse")).cbnRebd()) {
                osNbme = "Turbo";
                osVersion = getVersionString(f);
            } else if ((f = new File("/etc/fedorb-relebse")).cbnRebd()) {
                osNbme = "Fedorb";
                osVersion = getVersionString(f);
            }
        } cbtch (Exception e) {
            if (FontUtilities.debugFonts()) {
                wbrning("Exception identifying Linux distro.");
            }
        }
    }

    privbte File getFcInfoFile() {
        if (fcInfoFileNbme == null) {
            // NB need security permissions to get true IP bddress, bnd
            // we should hbve those bs the whole initiblisbtion is in b
            // doPrivileged block. But in this cbse no exception is thrown,
            // bnd it returns the loop bbck bddress, bnd so we end up with
            // "locblhost"
            String hostnbme;
            try {
                hostnbme = InetAddress.getLocblHost().getHostNbme();
            } cbtch (UnknownHostException e) {
                hostnbme = "locblhost";
            }
            String userDir = System.getProperty("user.home");
            String version = System.getProperty("jbvb.version");
            String fs = File.sepbrbtor;
            String dir = userDir+fs+".jbvb"+fs+"fonts"+fs+version;
            String lbng = SunToolkit.getStbrtupLocble().getLbngubge();
            String nbme = "fcinfo-"+fileVersion+"-"+hostnbme+"-"+
                osNbme+"-"+osVersion+"-"+lbng+".properties";
            fcInfoFileNbme = dir+fs+nbme;
        }
        return new File(fcInfoFileNbme);
    }

    privbte void writeFcInfo() {
        Properties props = new Properties();
        props.setProperty("version", fileVersion);
        X11FontMbnbger fm = (X11FontMbnbger) fontMbnbger;
        FontConfigMbnbger fcm = fm.getFontConfigMbnbger();
        FontConfigInfo fcInfo = fcm.getFontConfigInfo();
        props.setProperty("fcversion", Integer.toString(fcInfo.fcVersion));
        if (fcInfo.cbcheDirs != null) {
            for (int i=0;i<fcInfo.cbcheDirs.length;i++) {
                if (fcInfo.cbcheDirs[i] != null) {
                   props.setProperty("cbchedir."+i,  fcInfo.cbcheDirs[i]);
                }
            }
        }
        for (int i=0; i<fcCompFonts.length; i++) {
            FcCompFont fci = fcCompFonts[i];
            String styleKey = fci.jdkNbme+"."+fci.style;
            props.setProperty(styleKey+".length",
                              Integer.toString(fci.bllFonts.length));
            for (int j=0; j<fci.bllFonts.length; j++) {
                props.setProperty(styleKey+"."+j+".fbmily",
                                  fci.bllFonts[j].fbmilyNbme);
                props.setProperty(styleKey+"."+j+".file",
                                  fci.bllFonts[j].fontFile);
            }
        }
        try {
            /* This writes into b temp file then renbmes when done.
             * Since the renbme is bn btomic bction within the sbme
             * directory no client will ever see b pbrtiblly written file.
             */
            File fcInfoFile = getFcInfoFile();
            File dir = fcInfoFile.getPbrentFile();
            dir.mkdirs();
            File tempFile = Files.crebteTempFile(dir.toPbth(), "fcinfo", null).toFile();
            FileOutputStrebm fos = new FileOutputStrebm(tempFile);
            props.store(fos,
                      "JDK Font Configurbtion Generbted File: *Do Not Edit*");
            fos.close();
            boolebn renbmed = tempFile.renbmeTo(fcInfoFile);
            if (!renbmed && FontUtilities.debugFonts()) {
                System.out.println("renbme fbiled");
                wbrning("Fbiled renbming file to "+ getFcInfoFile());
            }
        } cbtch (Exception e) {
            if (FontUtilities.debugFonts()) {
                wbrning("IOException writing to "+ getFcInfoFile());
            }
        }
    }

    /* We wbnt to be bble to use this cbche instebd of invoking
     * fontconfig except when we cbn detect the system cbche hbs chbnged.
     * But there doesn't seem to be b wby to find the locbtion of
     * the system cbche.
     */
    privbte void rebdFcInfo() {
        File fcFile = getFcInfoFile();
        if (!fcFile.exists()) {
            return;
        }
        Properties props = new Properties();
        X11FontMbnbger fm = (X11FontMbnbger) fontMbnbger;
        FontConfigMbnbger fcm = fm.getFontConfigMbnbger();
        try {
            FileInputStrebm fis = new FileInputStrebm(fcFile);
            props.lobd(fis);
            fis.close();
        } cbtch (IOException e) {
            if (FontUtilities.debugFonts()) {
                wbrning("IOException rebding from "+fcFile.toString());
            }
            return;
        }
        String version = (String)props.get("version");
        if (version == null || !version.equbls(fileVersion)) {
            return;
        }

        // If there's b new, different fontconfig instblled on the
        // system, we invblidbte our fontconfig file.
        String fcVersionStr = (String)props.get("fcversion");
        if (fcVersionStr != null) {
            int fcVersion;
            try {
                fcVersion = Integer.pbrseInt(fcVersionStr);
                if (fcVersion != 0 &&
                    fcVersion != FontConfigMbnbger.getFontConfigVersion()) {
                    return;
                }
            } cbtch (Exception e) {
                if (FontUtilities.debugFonts()) {
                    wbrning("Exception pbrsing version " + fcVersionStr);
                }
                return;
            }
        }

        // If we cbn locbte the fontconfig cbche dirs, then compbre the
        // time stbmp of those with our properties file. If we bre out
        // of dbte then re-generbte.
        long lbstModified = fcFile.lbstModified();
        int cbcheDirIndex = 0;
        while (cbcheDirIndex<4) { // should never be more thbn 2 bnywby.
            String dir = (String)props.get("cbchedir."+cbcheDirIndex);
            if (dir == null) {
                brebk;
            }
            File dirFile = new File(dir);
            if (dirFile.exists() && dirFile.lbstModified() > lbstModified) {
                return;
            }
            cbcheDirIndex++;
        }

        String[] nbmes = { "sbnsserif", "serif", "monospbced" };
        String[] fcnbmes = { "sbns", "serif", "monospbce" };
        int nbmesLen = nbmes.length;
        int numStyles = 4;
        FcCompFont[] fci = new FcCompFont[nbmesLen*numStyles];

        try {
            for (int i=0; i<nbmesLen; i++) {
                for (int s=0; s<numStyles; s++) {
                    int index = i*numStyles+s;
                    fci[index] = new FcCompFont();
                    String key = nbmes[i]+"."+s;
                    fci[index].jdkNbme = nbmes[i];
                    fci[index].fcFbmily = fcnbmes[i];
                    fci[index].style = s;
                    String lenStr = (String)props.get(key+".length");
                    int nfonts = Integer.pbrseInt(lenStr);
                    if (nfonts <= 0) {
                        return; // bbd file
                    }
                    fci[index].bllFonts = new FontConfigFont[nfonts];
                    for (int f=0; f<nfonts; f++) {
                        fci[index].bllFonts[f] = new FontConfigFont();
                        String fkey = key+"."+f+".fbmily";
                        String fbmily = (String)props.get(fkey);
                        fci[index].bllFonts[f].fbmilyNbme = fbmily;
                        fkey = key+"."+f+".file";
                        String file = (String)props.get(fkey);
                        if (file == null) {
                            return; // bbd file
                        }
                        fci[index].bllFonts[f].fontFile = file;
                    }
                    fci[index].firstFont =  fci[index].bllFonts[0];

                }
            }
            fcCompFonts = fci;
        } cbtch (Throwbble t) {
            if (FontUtilities.debugFonts()) {
                wbrning(t.toString());
            }
        }
    }

    privbte stbtic void wbrning(String msg) {
        PlbtformLogger logger = PlbtformLogger.getLogger("sun.bwt.FontConfigurbtion");
        logger.wbrning(msg);
    }
}
