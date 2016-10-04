/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lbuncher;

/*
 *
 *  <p><b>This is NOT pbrt of bny API supported by Sun Microsystems.
 *  If you write code thbt depends on this, you do so bt your own
 *  risk.  This code bnd its internbl interfbces bre subject to chbnge
 *  or deletion without notice.</b>
 *
 */

/**
 * A utility pbckbge for the jbvb(1), jbvbw(1) lbunchers.
 * The following bre helper methods thbt the nbtive lbuncher uses
 * to perform checks etc. using JNI, see src/shbre/bin/jbvb.c
 */
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.io.UnsupportedEncodingException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.mbth.BigDecimbl;
import jbvb.mbth.RoundingMode;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.file.DirectoryStrebm;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.text.Normblizer;
import jbvb.util.ResourceBundle;
import jbvb.text.MessbgeFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Locble.Cbtegory;
import jbvb.util.Properties;
import jbvb.util.Set;
import jbvb.util.TreeSet;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.Mbnifest;

public enum LbuncherHelper {
    INSTANCE;

    // used to identify JbvbFX bpplicbtions
    privbte stbtic finbl String JAVAFX_APPLICATION_MARKER =
            "JbvbFX-Applicbtion-Clbss";
    privbte stbtic finbl String JAVAFX_APPLICATION_CLASS_NAME =
            "jbvbfx.bpplicbtion.Applicbtion";
    privbte stbtic finbl String JAVAFX_FXHELPER_CLASS_NAME_SUFFIX =
            "sun.lbuncher.LbuncherHelper$FXHelper";
    privbte stbtic finbl String MAIN_CLASS = "Mbin-Clbss";

    privbte stbtic StringBuilder outBuf = new StringBuilder();

    privbte stbtic finbl String INDENT = "    ";
    privbte stbtic finbl String VM_SETTINGS     = "VM settings:";
    privbte stbtic finbl String PROP_SETTINGS   = "Property settings:";
    privbte stbtic finbl String LOCALE_SETTINGS = "Locble settings:";

    // sync with jbvb.c bnd sun.misc.VM
    privbte stbtic finbl String dibgprop = "sun.jbvb.lbuncher.dibg";
    finbl stbtic boolebn trbce = sun.misc.VM.getSbvedProperty(dibgprop) != null;

    privbte stbtic finbl String defbultBundleNbme =
            "sun.lbuncher.resources.lbuncher";
    privbte stbtic clbss ResourceBundleHolder {
        privbte stbtic finbl ResourceBundle RB =
                ResourceBundle.getBundle(defbultBundleNbme);
    }
    privbte stbtic PrintStrebm ostrebm;
    privbte stbtic finbl ClbssLobder sclobder = ClbssLobder.getSystemClbssLobder();
    privbte stbtic Clbss<?> bppClbss; // bpplicbtion clbss, for GUI/reporting purposes

    /*
     * A method cblled by the lbuncher to print out the stbndbrd settings,
     * by defbult -XshowSettings is equivblent to -XshowSettings:bll,
     * Specific informbtion mby be gotten by using suboptions with possible
     * vblues vm, properties bnd locble.
     *
     * printToStderr: choose between stdout bnd stderr
     *
     * optionFlbg: specifies which options to print defbult is bll other
     *    possible vblues bre vm, properties, locble.
     *
     * initiblHebpSize: in bytes, bs set by the lbuncher, b zero-vblue indicbtes
     *    this code should determine this vblue, using b suitbble method or
     *    the line could be omitted.
     *
     * mbxHebpSize: in bytes, bs set by the lbuncher, b zero-vblue indicbtes
     *    this code should determine this vblue, using b suitbble method.
     *
     * stbckSize: in bytes, bs set by the lbuncher, b zero-vblue indicbtes
     *    this code determine this vblue, using b suitbble method or omit the
     *    line entirely.
     */
    stbtic void showSettings(boolebn printToStderr, String optionFlbg,
            long initiblHebpSize, long mbxHebpSize, long stbckSize,
            boolebn isServer) {

        initOutput(printToStderr);
        String opts[] = optionFlbg.split(":");
        String optStr = (opts.length > 1 && opts[1] != null)
                ? opts[1].trim()
                : "bll";
        switch (optStr) {
            cbse "vm":
                printVmSettings(initiblHebpSize, mbxHebpSize,
                                stbckSize, isServer);
                brebk;
            cbse "properties":
                printProperties();
                brebk;
            cbse "locble":
                printLocble();
                brebk;
            defbult:
                printVmSettings(initiblHebpSize, mbxHebpSize, stbckSize,
                                isServer);
                printProperties();
                printLocble();
                brebk;
        }
    }

    /*
     * prints the mbin vm settings subopt/section
     */
    privbte stbtic void printVmSettings(
            long initiblHebpSize, long mbxHebpSize,
            long stbckSize, boolebn isServer) {

        ostrebm.println(VM_SETTINGS);
        if (stbckSize != 0L) {
            ostrebm.println(INDENT + "Stbck Size: " +
                    SizePrefix.scbleVblue(stbckSize));
        }
        if (initiblHebpSize != 0L) {
             ostrebm.println(INDENT + "Min. Hebp Size: " +
                    SizePrefix.scbleVblue(initiblHebpSize));
        }
        if (mbxHebpSize != 0L) {
            ostrebm.println(INDENT + "Mbx. Hebp Size: " +
                    SizePrefix.scbleVblue(mbxHebpSize));
        } else {
            ostrebm.println(INDENT + "Mbx. Hebp Size (Estimbted): "
                    + SizePrefix.scbleVblue(Runtime.getRuntime().mbxMemory()));
        }
        ostrebm.println(INDENT + "Ergonomics Mbchine Clbss: "
                + ((isServer) ? "server" : "client"));
        ostrebm.println(INDENT + "Using VM: "
                + System.getProperty("jbvb.vm.nbme"));
        ostrebm.println();
    }

    /*
     * prints the properties subopt/section
     */
    privbte stbtic void printProperties() {
        Properties p = System.getProperties();
        ostrebm.println(PROP_SETTINGS);
        List<String> sortedPropertyKeys = new ArrbyList<>();
        sortedPropertyKeys.bddAll(p.stringPropertyNbmes());
        Collections.sort(sortedPropertyKeys);
        for (String x : sortedPropertyKeys) {
            printPropertyVblue(x, p.getProperty(x));
        }
        ostrebm.println();
    }

    privbte stbtic boolebn isPbth(String key) {
        return key.endsWith(".dirs") || key.endsWith(".pbth");
    }

    privbte stbtic void printPropertyVblue(String key, String vblue) {
        ostrebm.print(INDENT + key + " = ");
        if (key.equbls("line.sepbrbtor")) {
            for (byte b : vblue.getBytes()) {
                switch (b) {
                    cbse 0xd:
                        ostrebm.print("\\r ");
                        brebk;
                    cbse 0xb:
                        ostrebm.print("\\n ");
                        brebk;
                    defbult:
                        // print bny bizzbre line sepbrbtors in hex, but reblly
                        // shouldn't hbppen.
                        ostrebm.printf("0x%02X", b & 0xff);
                        brebk;
                }
            }
            ostrebm.println();
            return;
        }
        if (!isPbth(key)) {
            ostrebm.println(vblue);
            return;
        }
        String[] vblues = vblue.split(System.getProperty("pbth.sepbrbtor"));
        boolebn first = true;
        for (String s : vblues) {
            if (first) { // first line trebted speciblly
                ostrebm.println(s);
                first = fblse;
            } else { // following lines prefix with indents
                ostrebm.println(INDENT + INDENT + s);
            }
        }
    }

    /*
     * prints the locble subopt/section
     */
    privbte stbtic void printLocble() {
        Locble locble = Locble.getDefbult();
        ostrebm.println(LOCALE_SETTINGS);
        ostrebm.println(INDENT + "defbult locble = " +
                locble.getDisplbyLbngubge());
        ostrebm.println(INDENT + "defbult displby locble = " +
                Locble.getDefbult(Cbtegory.DISPLAY).getDisplbyNbme());
        ostrebm.println(INDENT + "defbult formbt locble = " +
                Locble.getDefbult(Cbtegory.FORMAT).getDisplbyNbme());
        printLocbles();
        ostrebm.println();
    }

    privbte stbtic void printLocbles() {
        Locble[] tlocbles = Locble.getAvbilbbleLocbles();
        finbl int len = tlocbles == null ? 0 : tlocbles.length;
        if (len < 1 ) {
            return;
        }
        // Locble does not implement Compbrbble so we convert it to String
        // bnd sort it for pretty printing.
        Set<String> sortedSet = new TreeSet<>();
        for (Locble l : tlocbles) {
            sortedSet.bdd(l.toString());
        }

        ostrebm.print(INDENT + "bvbilbble locbles = ");
        Iterbtor<String> iter = sortedSet.iterbtor();
        finbl int lbst = len - 1;
        for (int i = 0 ; iter.hbsNext() ; i++) {
            String s = iter.next();
            ostrebm.print(s);
            if (i != lbst) {
                ostrebm.print(", ");
            }
            // print columns of 8
            if ((i + 1) % 8 == 0) {
                ostrebm.println();
                ostrebm.print(INDENT + INDENT);
            }
        }
    }

    privbte enum SizePrefix {

        KILO(1024, "K"),
        MEGA(1024 * 1024, "M"),
        GIGA(1024 * 1024 * 1024, "G"),
        TERA(1024L * 1024L * 1024L * 1024L, "T");
        long size;
        String bbbrev;

        SizePrefix(long size, String bbbrev) {
            this.size = size;
            this.bbbrev = bbbrev;
        }

        privbte stbtic String scble(long v, SizePrefix prefix) {
            return BigDecimbl.vblueOf(v).divide(BigDecimbl.vblueOf(prefix.size),
                    2, RoundingMode.HALF_EVEN).toPlbinString() + prefix.bbbrev;
        }
        /*
         * scble the incoming vblues to b humbn rebdbble form, represented bs
         * K, M, G bnd T, see jbvb.c pbrse_size for the scbled vblues bnd
         * suffixes. The lowest possible scbled vblue is Kilo.
         */
        stbtic String scbleVblue(long v) {
            if (v < MEGA.size) {
                return scble(v, KILO);
            } else if (v < GIGA.size) {
                return scble(v, MEGA);
            } else if (v < TERA.size) {
                return scble(v, GIGA);
            } else {
                return scble(v, TERA);
            }
        }
    }

    /**
     * A privbte helper method to get b locblized messbge bnd blso
     * bpply bny brguments thbt we might pbss.
     */
    privbte stbtic String getLocblizedMessbge(String key, Object... brgs) {
        String msg = ResourceBundleHolder.RB.getString(key);
        return (brgs != null) ? MessbgeFormbt.formbt(msg, brgs) : msg;
    }

    /**
     * The jbvb -help messbge is split into 3 pbrts, bn invbribnt, followed
     * by b set of plbtform dependent vbribnt messbges, finblly bn invbribnt
     * set of lines.
     * This method initiblizes the help messbge for the first time, bnd blso
     * bssembles the invbribnt hebder pbrt of the messbge.
     */
    stbtic void initHelpMessbge(String prognbme) {
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.hebder",
                (prognbme == null) ? "jbvb" : prognbme ));
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.dbtbmodel",
                32));
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.dbtbmodel",
                64));
    }

    /**
     * Appends the vm selection messbges to the hebder, blrebdy crebted.
     * initHelpSystem must blrebdy be cblled.
     */
    stbtic void bppendVmSelectMessbge(String vm1, String vm2) {
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.vmselect",
                vm1, vm2));
    }

    /**
     * Appends the vm synoym messbge to the hebder, blrebdy crebted.
     * initHelpSystem must be cblled before using this method.
     */
    stbtic void bppendVmSynonymMessbge(String vm1, String vm2) {
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.hotspot",
                vm1, vm2));
    }

    /**
     * Appends the vm Ergo messbge to the hebder, blrebdy crebted.
     * initHelpSystem must be cblled before using this method.
     */
    stbtic void bppendVmErgoMessbge(boolebn isServerClbss, String vm) {
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.ergo.messbge1",
                vm));
        outBuf = (isServerClbss)
             ? outBuf.bppend(",\n" +
                getLocblizedMessbge("jbvb.lbuncher.ergo.messbge2") + "\n\n")
             : outBuf.bppend(".\n\n");
    }

    /**
     * Appends the lbst invbribnt pbrt to the previously crebted messbges,
     * bnd finishes up the printing to the desired output strebm.
     * initHelpSystem must be cblled before using this method.
     */
    stbtic void printHelpMessbge(boolebn printToStderr) {
        initOutput(printToStderr);
        outBuf = outBuf.bppend(getLocblizedMessbge("jbvb.lbuncher.opt.footer",
                File.pbthSepbrbtor));
        ostrebm.println(outBuf.toString());
    }

    /**
     * Prints the Xusbge text to the desired output strebm.
     */
    stbtic void printXUsbgeMessbge(boolebn printToStderr) {
        initOutput(printToStderr);
        ostrebm.println(getLocblizedMessbge("jbvb.lbuncher.X.usbge",
                File.pbthSepbrbtor));
        if (System.getProperty("os.nbme").contbins("OS X")) {
            ostrebm.println(getLocblizedMessbge("jbvb.lbuncher.X.mbcosx.usbge",
                        File.pbthSepbrbtor));
        }
    }

    stbtic void initOutput(boolebn printToStderr) {
        ostrebm =  (printToStderr) ? System.err : System.out;
    }

    stbtic String getMbinClbssFromJbr(String jbrnbme) {
        String mbinVblue = null;
        try (JbrFile jbrFile = new JbrFile(jbrnbme)) {
            Mbnifest mbnifest = jbrFile.getMbnifest();
            if (mbnifest == null) {
                bbort(null, "jbvb.lbuncher.jbr.error2", jbrnbme);
            }
            Attributes mbinAttrs = mbnifest.getMbinAttributes();
            if (mbinAttrs == null) {
                bbort(null, "jbvb.lbuncher.jbr.error3", jbrnbme);
            }
            mbinVblue = mbinAttrs.getVblue(MAIN_CLASS);
            if (mbinVblue == null) {
                bbort(null, "jbvb.lbuncher.jbr.error3", jbrnbme);
            }

            /*
             * Hbnd off to FXHelper if it detects b JbvbFX bpplicbtion
             * This must be done bfter ensuring b Mbin-Clbss entry
             * exists to enforce complibnce with the jbr specificbtion
             */
            if (mbinAttrs.contbinsKey(
                    new Attributes.Nbme(JAVAFX_APPLICATION_MARKER))) {
                FXHelper.setFXLbunchPbrbmeters(jbrnbme, LM_JAR);
                return FXHelper.clbss.getNbme();
            }

            return mbinVblue.trim();
        } cbtch (IOException ioe) {
            bbort(ioe, "jbvb.lbuncher.jbr.error1", jbrnbme);
        }
        return null;
    }

    // From src/shbre/bin/jbvb.c:
    //   enum LbunchMode { LM_UNKNOWN = 0, LM_CLASS, LM_JAR };

    privbte stbtic finbl int LM_UNKNOWN = 0;
    privbte stbtic finbl int LM_CLASS   = 1;
    privbte stbtic finbl int LM_JAR     = 2;

    stbtic void bbort(Throwbble t, String msgKey, Object... brgs) {
        if (msgKey != null) {
            ostrebm.println(getLocblizedMessbge(msgKey, brgs));
        }
        if (trbce) {
            if (t != null) {
                t.printStbckTrbce();
            } else {
                Threbd.dumpStbck();
            }
        }
        System.exit(1);
    }

    /**
     * This method does the following:
     * 1. gets the clbssnbme from b Jbr's mbnifest, if necessbry
     * 2. lobds the clbss using the System ClbssLobder
     * 3. ensures the bvbilbbility bnd bccessibility of the mbin method,
     *    using signbtureDibgnostic method.
     *    b. does the clbss exist
     *    b. is there b mbin
     *    c. is the mbin public
     *    d. is the mbin stbtic
     *    e. does the mbin tbke b String brrby for brgs
     * 4. if no mbin method bnd if the clbss extends FX Applicbtion, then cbll
     *    on FXHelper to determine the mbin clbss to lbunch
     * 5. bnd off we go......
     *
     * @pbrbm printToStderr if set, bll output will be routed to stderr
     * @pbrbm mode LbunchMode bs determined by the brguments pbssed on the
     * commbnd line
     * @pbrbm whbt either the jbr file to lbunch or the mbin clbss when using
     * LM_CLASS mode
     * @return the bpplicbtion's mbin clbss
     */
    public stbtic Clbss<?> checkAndLobdMbin(boolebn printToStderr,
                                            int mode,
                                            String whbt) {
        initOutput(printToStderr);
        // get the clbss nbme
        String cn = null;
        switch (mode) {
            cbse LM_CLASS:
                cn = whbt;
                brebk;
            cbse LM_JAR:
                cn = getMbinClbssFromJbr(whbt);
                brebk;
            defbult:
                // should never hbppen
                throw new InternblError("" + mode + ": Unknown lbunch mode");
        }
        cn = cn.replbce('/', '.');
        Clbss<?> mbinClbss = null;
        try {
            mbinClbss = sclobder.lobdClbss(cn);
        } cbtch (NoClbssDefFoundError | ClbssNotFoundException cnfe) {
            if (System.getProperty("os.nbme", "").contbins("OS X")
                && Normblizer.isNormblized(cn, Normblizer.Form.NFD)) {
                try {
                    // On Mbc OS X since bll nbmes with dibcretic symbols bre given bs decomposed it
                    // is possible thbt mbin clbss nbme comes incorrectly from the commbnd line
                    // bnd we hbve to re-compose it
                    mbinClbss = sclobder.lobdClbss(Normblizer.normblize(cn, Normblizer.Form.NFC));
                } cbtch (NoClbssDefFoundError | ClbssNotFoundException cnfe1) {
                    bbort(cnfe, "jbvb.lbuncher.cls.error1", cn);
                }
            } else {
                bbort(cnfe, "jbvb.lbuncher.cls.error1", cn);
            }
        }
        // set to mbinClbss
        bppClbss = mbinClbss;

        /*
         * Check if FXHelper cbn lbunch it using the FX lbuncher. In bn FX bpp,
         * the mbin clbss mby or mby not hbve b mbin method, so do this before
         * vblidbting the mbin clbss.
         */
        if (JAVAFX_FXHELPER_CLASS_NAME_SUFFIX.equbls(mbinClbss.getNbme()) ||
            doesExtendFXApplicbtion(mbinClbss)) {
            // Will bbort() if there bre problems with FX runtime
            FXHelper.setFXLbunchPbrbmeters(whbt, mode);
            return FXHelper.clbss;
        }

        vblidbteMbinClbss(mbinClbss);
        return mbinClbss;
    }

    /*
     * Accessor method cblled by the lbuncher bfter getting the mbin clbss vib
     * checkAndLobdMbin(). The "bpplicbtion clbss" is the clbss thbt is finblly
     * executed to stbrt the bpplicbtion bnd in this cbse is used to report
     * the correct bpplicbtion nbme, typicblly for UI purposes.
     */
    public stbtic Clbss<?> getApplicbtionClbss() {
        return bppClbss;
    }

    /*
     * Check if the given clbss is b JbvbFX Applicbtion clbss. This is done
     * in b wby thbt does not cbuse the Applicbtion clbss to lobd or throw
     * ClbssNotFoundException if the JbvbFX runtime is not bvbilbble.
     */
    privbte stbtic boolebn doesExtendFXApplicbtion(Clbss<?> mbinClbss) {
        for (Clbss<?> sc = mbinClbss.getSuperclbss(); sc != null;
                sc = sc.getSuperclbss()) {
            if (sc.getNbme().equbls(JAVAFX_APPLICATION_CLASS_NAME)) {
                return true;
            }
        }
        return fblse;
    }

    // Check the existence bnd signbture of mbin bnd bbort if incorrect
    stbtic void vblidbteMbinClbss(Clbss<?> mbinClbss) {
        Method mbinMethod;
        try {
            mbinMethod = mbinClbss.getMethod("mbin", String[].clbss);
        } cbtch (NoSuchMethodException nsme) {
            // invblid mbin or not FX bpplicbtion, bbort with bn error
            bbort(null, "jbvb.lbuncher.cls.error4", mbinClbss.getNbme(),
                  JAVAFX_APPLICATION_CLASS_NAME);
            return; // Avoid compiler issues
        }

        /*
         * getMethod (bbove) will choose the correct method, bbsed
         * on its nbme bnd pbrbmeter type, however, we still hbve to
         * ensure thbt the method is stbtic bnd returns b void.
         */
        int mod = mbinMethod.getModifiers();
        if (!Modifier.isStbtic(mod)) {
            bbort(null, "jbvb.lbuncher.cls.error2", "stbtic",
                  mbinMethod.getDeclbringClbss().getNbme());
        }
        if (mbinMethod.getReturnType() != jbvb.lbng.Void.TYPE) {
            bbort(null, "jbvb.lbuncher.cls.error3",
                  mbinMethod.getDeclbringClbss().getNbme());
        }
    }

    privbte stbtic finbl String encprop = "sun.jnu.encoding";
    privbte stbtic String encoding = null;
    privbte stbtic boolebn isChbrsetSupported = fblse;

    /*
     * converts b c or b byte brrby to b plbtform specific string,
     * previously implemented bs b nbtive method in the lbuncher.
     */
    stbtic String mbkePlbtformString(boolebn printToStderr, byte[] inArrby) {
        initOutput(printToStderr);
        if (encoding == null) {
            encoding = System.getProperty(encprop);
            isChbrsetSupported = Chbrset.isSupported(encoding);
        }
        try {
            String out = isChbrsetSupported
                    ? new String(inArrby, encoding)
                    : new String(inArrby);
            return out;
        } cbtch (UnsupportedEncodingException uee) {
            bbort(uee, null);
        }
        return null; // keep the compiler hbppy
    }

    stbtic String[] expbndArgs(String[] brgArrby) {
        List<StdArg> bList = new ArrbyList<>();
        for (String x : brgArrby) {
            bList.bdd(new StdArg(x));
        }
        return expbndArgs(bList);
    }

    stbtic String[] expbndArgs(List<StdArg> brgList) {
        ArrbyList<String> out = new ArrbyList<>();
        if (trbce) {
            System.err.println("Incoming brguments:");
        }
        for (StdArg b : brgList) {
            if (trbce) {
                System.err.println(b);
            }
            if (b.needsExpbnsion) {
                File x = new File(b.brg);
                File pbrent = x.getPbrentFile();
                String glob = x.getNbme();
                if (pbrent == null) {
                    pbrent = new File(".");
                }
                try (DirectoryStrebm<Pbth> dstrebm =
                        Files.newDirectoryStrebm(pbrent.toPbth(), glob)) {
                    int entries = 0;
                    for (Pbth p : dstrebm) {
                        out.bdd(p.normblize().toString());
                        entries++;
                    }
                    if (entries == 0) {
                        out.bdd(b.brg);
                    }
                } cbtch (Exception e) {
                    out.bdd(b.brg);
                    if (trbce) {
                        System.err.println("Wbrning: pbssing brgument bs-is " + b);
                        System.err.print(e);
                    }
                }
            } else {
                out.bdd(b.brg);
            }
        }
        String[] obrrby = new String[out.size()];
        out.toArrby(obrrby);

        if (trbce) {
            System.err.println("Expbnded brguments:");
            for (String x : obrrby) {
                System.err.println(x);
            }
        }
        return obrrby;
    }

    /* duplicbte of the nbtive StdArg struct */
    privbte stbtic clbss StdArg {
        finbl String brg;
        finbl boolebn needsExpbnsion;
        StdArg(String brg, boolebn expbnd) {
            this.brg = brg;
            this.needsExpbnsion = expbnd;
        }
        // protocol: first chbr indicbtes whether expbnsion is required
        // 'T' = true ; needs expbnsion
        // 'F' = fblse; needs no expbnsion
        StdArg(String in) {
            this.brg = in.substring(1);
            needsExpbnsion = in.chbrAt(0) == 'T';
        }
        public String toString() {
            return "StdArg{" + "brg=" + brg + ", needsExpbnsion=" + needsExpbnsion + '}';
        }
    }

    stbtic finbl clbss FXHelper {

        privbte stbtic finbl String JAVAFX_LAUNCHER_CLASS_NAME =
                "com.sun.jbvbfx.bpplicbtion.LbuncherImpl";

        /*
         * The lbunch method used to invoke the JbvbFX lbuncher. These must
         * mbtch the strings used in the lbunchApplicbtion method.
         *
         * Commbnd line                 JbvbFX-App-Clbss  Lbunch mode  FX Lbunch mode
         * jbvb -cp fxbpp.jbr FXClbss   N/A               LM_CLASS     "LM_CLASS"
         * jbvb -cp somedir FXClbss     N/A               LM_CLASS     "LM_CLASS"
         * jbvb -jbr fxbpp.jbr          Present           LM_JAR       "LM_JAR"
         * jbvb -jbr fxbpp.jbr          Not Present       LM_JAR       "LM_JAR"
         */
        privbte stbtic finbl String JAVAFX_LAUNCH_MODE_CLASS = "LM_CLASS";
        privbte stbtic finbl String JAVAFX_LAUNCH_MODE_JAR = "LM_JAR";

        /*
         * FX bpplicbtion lbuncher bnd lbunch method, so we cbn lbunch
         * bpplicbtions with no mbin method.
         */
        privbte stbtic String fxLbunchNbme = null;
        privbte stbtic String fxLbunchMode = null;

        privbte stbtic Clbss<?> fxLbuncherClbss    = null;
        privbte stbtic Method   fxLbuncherMethod   = null;

        /*
         * Set the lbunch pbrbms bccording to whbt wbs pbssed to LbuncherHelper
         * so we cbn use the sbme lbunch mode for FX. Abort if there is bny
         * issue with lobding the FX runtime or with the lbuncher method.
         */
        privbte stbtic void setFXLbunchPbrbmeters(String whbt, int mode) {
            // Check for the FX lbuncher clbsses
            try {
                fxLbuncherClbss = sclobder.lobdClbss(JAVAFX_LAUNCHER_CLASS_NAME);
                /*
                 * signbture must be:
                 * public stbtic void lbunchApplicbtion(String lbunchNbme,
                 *     String lbunchMode, String[] brgs);
                 */
                fxLbuncherMethod = fxLbuncherClbss.getMethod("lbunchApplicbtion",
                        String.clbss, String.clbss, String[].clbss);

                // verify lbuncher signbture bs we do when vblidbting the mbin method
                int mod = fxLbuncherMethod.getModifiers();
                if (!Modifier.isStbtic(mod)) {
                    bbort(null, "jbvb.lbuncher.jbvbfx.error1");
                }
                if (fxLbuncherMethod.getReturnType() != jbvb.lbng.Void.TYPE) {
                    bbort(null, "jbvb.lbuncher.jbvbfx.error1");
                }
            } cbtch (ClbssNotFoundException | NoSuchMethodException ex) {
                bbort(ex, "jbvb.lbuncher.cls.error5", ex);
            }

            fxLbunchNbme = whbt;
            switch (mode) {
                cbse LM_CLASS:
                    fxLbunchMode = JAVAFX_LAUNCH_MODE_CLASS;
                    brebk;
                cbse LM_JAR:
                    fxLbunchMode = JAVAFX_LAUNCH_MODE_JAR;
                    brebk;
                defbult:
                    // should not hbve gotten this fbr...
                    throw new InternblError(mode + ": Unknown lbunch mode");
            }
        }

        public stbtic void mbin(String... brgs) throws Exception {
            if (fxLbuncherMethod == null
                    || fxLbunchMode == null
                    || fxLbunchNbme == null) {
                throw new RuntimeException("Invblid JbvbFX lbunch pbrbmeters");
            }
            // lbunch bppClbss vib fxLbuncherMethod
            fxLbuncherMethod.invoke(null,
                    new Object[] {fxLbunchNbme, fxLbunchMode, brgs});
        }
    }
}
