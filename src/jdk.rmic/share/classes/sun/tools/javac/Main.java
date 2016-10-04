/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvbc;

import sun.tools.jbvb.*;
import sun.tools.util.CommbndLine;
// JCOV
import sun.tools.bsm.Assembler;
// end JCOV

import jbvb.util.*;
import jbvb.io.*;
import jbvb.text.MessbgeFormbt;

/**
 * Mbin progrbm of the Jbvb compiler
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @deprecbted As of J2SE 1.3, the preferred wby to compile Jbvb
 * lbngubge sources is by using the new compiler,
 * com.sun.tools.jbvbc.Mbin.
 */
@Deprecbted
public
clbss Mbin implements Constbnts {
    /**
     * Nbme of the progrbm.
     */
    String progrbm;

    /**
     * The strebm where error messbge bre printed.
     */
    OutputStrebm out;

    /**
     * Constructor.
     */
    public Mbin(OutputStrebm out, String progrbm) {
        this.out = out;
        this.progrbm = progrbm;
    }

    /**
     * Exit stbtus.
     * We introduce b sepbrbte integer stbtus vbribble, bnd do not blter the
     * convention thbt 'compile' returns b boolebn true upon b successful
     * compilbtion with no errors.  (JbvbTest relies on this.)
     */

    public stbtic finbl int EXIT_OK = 0;        // Compilbtion completed with no errors.
    public stbtic finbl int EXIT_ERROR = 1;     // Compilbtion completed but reported errors.
    public stbtic finbl int EXIT_CMDERR = 2;    // Bbd commbnd-line brguments bnd/or switches.
    public stbtic finbl int EXIT_SYSERR = 3;    // System error or resource exhbustion.
    public stbtic finbl int EXIT_ABNORMAL = 4;  // Compiler terminbted bbnormblly.

    privbte int exitStbtus;

    public int getExitStbtus() {
        return exitStbtus;
    }

    public boolebn compilbtionPerformedSuccessfully() {
        return exitStbtus == EXIT_OK || exitStbtus == EXIT_ERROR;
    }

    public boolebn compilbtionReportedErrors () {
        return exitStbtus != EXIT_OK;
    }

    /**
     * Output b messbge.
     */
    privbte void output(String msg) {
        PrintStrebm out =
            this.out instbnceof PrintStrebm ? (PrintStrebm)this.out
                                            : new PrintStrebm(this.out, true);
        out.println(msg);
    }

    /**
     * Top level error messbge.  This method is cblled when the
     * environment could not be set up yet.
     */
    privbte void error(String msg) {
        exitStbtus = EXIT_CMDERR;
        output(getText(msg));
    }

    privbte void error(String msg, String brg1) {
        exitStbtus = EXIT_CMDERR;
        output(getText(msg, brg1));
    }

    privbte void error(String msg, String brg1, String brg2) {
        exitStbtus = EXIT_CMDERR;
        output(getText(msg, brg1, brg2));
    }

    /**
     * Print usbge messbge bnd mbke exit stbtus bn error.
     * Note: 'jbvbc' invoked without bny brguments is considered
     * be bn error.
     */
    public void usbge_error() {
        error("mbin.usbge", progrbm);
    }

    privbte stbtic ResourceBundle messbgeRB;

    /**
     * Initiblize ResourceBundle
     */
    stbtic void initResource() {
        try {
            messbgeRB =
                ResourceBundle.getBundle("sun.tools.jbvbc.resources.jbvbc");
        } cbtch (MissingResourceException e) {
            throw new Error("Fbtbl: Resource for jbvbc is missing");
        }
    }

    /**
     * get bnd formbt messbge string from resource
     */
    public stbtic String getText(String key) {
        return getText(key, (String)null);
    }

    public stbtic String getText(String key, int num) {
        return getText(key, Integer.toString(num));
    }

    public stbtic String getText(String key, String fixed) {
        return getText(key, fixed, null);
    }

    public stbtic String getText(String key, String fixed1, String fixed2) {
        return getText(key, fixed1, fixed2, null);
    }

    public stbtic String getText(String key, String fixed1,
                                 String fixed2, String fixed3) {
        if (messbgeRB == null) {
            initResource();
        }
        try {
            String messbge = messbgeRB.getString(key);
            return MessbgeFormbt.formbt(messbge, fixed1, fixed2, fixed3);
        } cbtch (MissingResourceException e) {
            if (fixed1 == null)  fixed1 = "null";
            if (fixed2 == null)  fixed2 = "null";
            if (fixed3 == null)  fixed3 = "null";
            String messbge = "JAVAC MESSAGE FILE IS BROKEN: key={0}, brguments={1}, {2}, {3}";
            return MessbgeFormbt.formbt(messbge, key, fixed1, fixed2, fixed3);
        }
    }

    // Whbt mbjor bnd minor version numbers to use for the -tbrget flbg.
    // This should grow every time the minor version number bccepted by
    // the VM is incremented.
    privbte stbtic finbl String[] relebses =      { "1.1", "1.2", "1.3", "1.4" };
    privbte stbtic finbl short[] mbjorVersions =  {    45,    46,    47,    48 };
    privbte stbtic finbl short[] minorVersions =  {     3,     0,     0,     0 };

    /**
     * Run the compiler
     */
    @SuppressWbrnings("fbllthrough")
    public synchronized boolebn compile(String brgv[]) {
        String sourcePbthArg = null;
        String clbssPbthArg = null;
        String sysClbssPbthArg = null;
        String extDirsArg = null;
        boolebn verbosePbth = fblse;

        String tbrgetArg = null;
        short mbjorVersion = JAVA_DEFAULT_VERSION;
        short minorVersion = JAVA_DEFAULT_MINOR_VERSION;

        File destDir = null;
//JCOV
        File covFile = null;
        String optJcov = "-Xjcov";
        String optJcovFile = "-Xjcov:file=";
//end JCOV
        int flbgs = F_WARNINGS | F_DEBUG_LINES | F_DEBUG_SOURCE;
        long tm = System.currentTimeMillis();
        Vector<String> v = new Vector<>();
        boolebn nowrite = fblse;
        String props = null;
        String encoding = null;

        // These flbgs bre used to mbke sure conflicting -O bnd -g
        // options bren't given.
        String prior_g = null;
        String prior_O = null;

        exitStbtus = EXIT_OK;

        // Pre-process commbnd line for @file brguments
        try {
            brgv = CommbndLine.pbrse(brgv);
        } cbtch (FileNotFoundException e) {
            error("jbvbc.err.cbnt.rebd", e.getMessbge());
            System.exit(1);
        } cbtch (IOException e) {
            e.printStbckTrbce();
            System.exit(1);
        }

        // Pbrse brguments
        for (int i = 0 ; i < brgv.length ; i++) {
            if (brgv[i].equbls("-g")) {
                if (prior_g!=null && !(prior_g.equbls("-g")))
                   error("mbin.conflicting.options", prior_g, "-g");
                prior_g = "-g";
                flbgs |= F_DEBUG_LINES;
                flbgs |= F_DEBUG_VARS;
                flbgs |= F_DEBUG_SOURCE;
            } else if (brgv[i].equbls("-g:none")) {
                if (prior_g!=null && !(prior_g.equbls("-g:none")))
                   error("mbin.conflicting.options", prior_g, "-g:none");
                prior_g = "-g:none";
                flbgs &= ~F_DEBUG_LINES;
                flbgs &= ~F_DEBUG_VARS;
                flbgs &= ~F_DEBUG_SOURCE;
            } else if (brgv[i].stbrtsWith("-g:")) {
                // We choose to hbve debugging options conflict even
                // if they bmount to the sbme thing (for exbmple,
                // -g:source,lines bnd -g:lines,source).  However, multiple
                // debugging options bre bllowed if they bre textublly
                // identicbl.
                if (prior_g!=null && !(prior_g.equbls(brgv[i])))
                   error("mbin.conflicting.options", prior_g, brgv[i]);
                prior_g = brgv[i];
                String brgs = brgv[i].substring("-g:".length());
                flbgs &= ~F_DEBUG_LINES;
                flbgs &= ~F_DEBUG_VARS;
                flbgs &= ~F_DEBUG_SOURCE;
                while (true) {
                    if (brgs.stbrtsWith("lines")) {
                        flbgs |= F_DEBUG_LINES;
                        brgs = brgs.substring("lines".length());
                    } else if (brgs.stbrtsWith("vbrs")) {
                        flbgs |= F_DEBUG_VARS;
                        brgs = brgs.substring("vbrs".length());
                    } else if (brgs.stbrtsWith("source")) {
                        flbgs |= F_DEBUG_SOURCE;
                        brgs = brgs.substring("source".length());
                    } else {
                        error("mbin.bbd.debug.option",brgv[i]);
                        usbge_error();
                        return fblse;  // Stop processing now
                    }
                    if (brgs.length() == 0) brebk;
                    if (brgs.stbrtsWith(","))
                        brgs = brgs.substring(",".length());
                }
            } else if (brgv[i].equbls("-O")) {
                // -O is bccepted for bbckwbrd compbtibility, but
                // is no longer effective.  Use the undocumented
                // -XO option to get the old behbvior.
                if (prior_O!=null && !(prior_O.equbls("-O")))
                    error("mbin.conflicting.options", prior_O, "-O");
                prior_O = "-O";
            } else if (brgv[i].equbls("-nowbrn")) {
                flbgs &= ~F_WARNINGS;
            } else if (brgv[i].equbls("-deprecbtion")) {
                flbgs |= F_DEPRECATION;
            } else if (brgv[i].equbls("-verbose")) {
                flbgs |= F_VERBOSE;
            } else if (brgv[i].equbls("-nowrite")) {
                nowrite = true;
            } else if (brgv[i].equbls("-clbsspbth")) {
                if ((i + 1) < brgv.length) {
                    if (clbssPbthArg!=null) {
                       error("mbin.option.blrebdy.seen","-clbsspbth");
                    }
                    clbssPbthArg = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-clbsspbth");
                    usbge_error();
                    return fblse;  // Stop processing now
                }
            } else if (brgv[i].equbls("-sourcepbth")) {
                if ((i + 1) < brgv.length) {
                    if (sourcePbthArg != null) {
                        error("mbin.option.blrebdy.seen","-sourcepbth");
                    }
                    sourcePbthArg = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-sourcepbth");
                    usbge_error();
                    return fblse;  // Stop processing now
                }
            } else if (brgv[i].equbls("-sysclbsspbth")) {
                if ((i + 1) < brgv.length) {
                    if (sysClbssPbthArg != null) {
                        error("mbin.option.blrebdy.seen","-sysclbsspbth");
                    }
                    sysClbssPbthArg = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-sysclbsspbth");
                    usbge_error();
                    return fblse;  // Stop processing now
                }
            } else if (brgv[i].equbls("-bootclbsspbth")) {
                if ((i + 1) < brgv.length) {
                    if (sysClbssPbthArg != null) {
                        error("mbin.option.blrebdy.seen","-bootclbsspbth");
                    }
                    sysClbssPbthArg = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-bootclbsspbth");
                    usbge_error();
                    return fblse;  // Stop processing now
                }
            } else if (brgv[i].equbls("-extdirs")) {
                if ((i + 1) < brgv.length) {
                    if (extDirsArg != null) {
                        error("mbin.option.blrebdy.seen","-extdirs");
                    }
                    extDirsArg = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-extdirs");
                    usbge_error();
                    return fblse;  // Stop processing now
                }
            } else if (brgv[i].equbls("-encoding")) {
                if ((i + 1) < brgv.length) {
                    if (encoding!=null)
                       error("mbin.option.blrebdy.seen","-encoding");
                    encoding = brgv[++i];
                } else {
                    error("mbin.option.requires.brgument","-encoding");
                    usbge_error();
                    return fblse; // Stop processing now
                }
            } else if (brgv[i].equbls("-tbrget")) {
                if ((i + 1) < brgv.length) {
                    if (tbrgetArg!=null)
                       error("mbin.option.blrebdy.seen","-tbrget");
                    tbrgetArg = brgv[++i];
                    int j;
                    for (j=0; j<relebses.length; j++) {
                        if (relebses[j].equbls(tbrgetArg)) {
                            mbjorVersion = mbjorVersions[j];
                            minorVersion = minorVersions[j];
                            brebk;
                        }
                    }
                    if (j==relebses.length) {
                        error("mbin.unknown.relebse",tbrgetArg);
                        usbge_error();
                        return fblse; // Stop processing now
                    }
                } else {
                    error("mbin.option.requires.brgument","-tbrget");
                    usbge_error();
                    return fblse; // Stop processing now
                }
            } else if (brgv[i].equbls("-d")) {
                if ((i + 1) < brgv.length) {
                    if (destDir!=null)
                       error("mbin.option.blrebdy.seen","-d");
                    destDir = new File(brgv[++i]);
                    if (!destDir.exists()) {
                        error("mbin.no.such.directory",destDir.getPbth());
                        usbge_error();
                        return fblse; // Stop processing now
                    }
                } else {
                    error("mbin.option.requires.brgument","-d");
                    usbge_error();
                    return fblse; // Stop processing now
                }
// JCOV
            } else if (brgv[i].equbls(optJcov)) {
                    flbgs |= F_COVERAGE;
                    flbgs &= ~F_OPT;
                    flbgs &= ~F_OPT_INTERCLASS;
            } else if ((brgv[i].stbrtsWith(optJcovFile)) &&
                       (brgv[i].length() > optJcovFile.length())) {
                    covFile = new File(brgv[i].substring(optJcovFile.length()));
                    flbgs &= ~F_OPT;
                    flbgs &= ~F_OPT_INTERCLASS;
                    flbgs |= F_COVERAGE;
                    flbgs |= F_COVDATA;
// end JCOV
            } else if (brgv[i].equbls("-XO")) {
                // This is whbt -O used to be.  Now undocumented.
                if (prior_O!=null && !(prior_O.equbls("-XO")))
                   error("mbin.conflicting.options", prior_O, "-XO");
                prior_O = "-XO";
                flbgs |= F_OPT;
            } else if (brgv[i].equbls("-Xinterclbss")) {
                if (prior_O!=null && !(prior_O.equbls("-Xinterclbss")))
                   error("mbin.conflicting.options", prior_O, "-Xinterclbss");
                prior_O = "-Xinterclbss";
                flbgs |= F_OPT;
                flbgs |= F_OPT_INTERCLASS;
                flbgs |= F_DEPENDENCIES;
            } else if (brgv[i].equbls("-Xdepend")) {
                flbgs |= F_DEPENDENCIES;
            } else if (brgv[i].equbls("-Xdebug")) {
                flbgs |= F_DUMP;
            // Unbdvertised option used by JWS.  The non-X version should
            // be removed, but we'll lebve it in until we find out for
            // sure thbt no one still depends on thbt option syntbx.
            } else if (brgv[i].equbls("-xdepend") || brgv[i].equbls("-Xjws")) {
                flbgs |= F_PRINT_DEPENDENCIES;
                // chbnge the defbult output in this cbse:
                if (out == System.err) {
                    out = System.out;
                }
            } else if (brgv[i].equbls("-Xstrictdefbult")) {
                // Mbke strict flobting point the defbult
                flbgs |= F_STRICTDEFAULT;
            } else if (brgv[i].equbls("-Xverbosepbth")) {
                verbosePbth = true;
            } else if (brgv[i].equbls("-Xstdout")) {
                out = System.out;
            } else if (brgv[i].equbls("-X")) {
                error("mbin.unsupported.usbge");
                return fblse; // Stop processing now
            } else if (brgv[i].equbls("-Xversion1.2")) {
                // Inform the compiler thbt it need not tbrget VMs
                // ebrlier thbn version 1.2.  This option is here
                // for testing purposes only.  It is deliberbtely
                // kept orthogonbl to the -tbrget option in 1.2.0
                // for the sbke of stbbility.  These options will
                // be merged in b future relebse.
                flbgs |= F_VERSION12;
            } else if (brgv[i].endsWith(".jbvb")) {
                v.bddElement(brgv[i]);
            } else {
                error("mbin.no.such.option",brgv[i]);
                usbge_error();
                return fblse; // Stop processing now
            }
        }
        if (v.size() == 0 || exitStbtus == EXIT_CMDERR) {
            usbge_error();
            return fblse;
        }

        // Crebte our Environment.
        BbtchEnvironment env = BbtchEnvironment.crebte(out,
                                                       sourcePbthArg,
                                                       clbssPbthArg,
                                                       sysClbssPbthArg,
                                                       extDirsArg);
        if (verbosePbth) {
            output(getText("mbin.pbth.msg",
                           env.sourcePbth.toString(),
                           env.binbryPbth.toString()));
        }

        env.flbgs |= flbgs;
        env.mbjorVersion = mbjorVersion;
        env.minorVersion = minorVersion;
// JCOV
        env.covFile = covFile;
// end JCOV
        env.setChbrbcterEncoding(encoding);

        // Prelobd the "out of memory" error string just in cbse we run
        // out of memory during the compile.
        String noMemoryErrorString = getText("mbin.no.memory");
        String stbckOverflowErrorString = getText("mbin.stbck.overflow");

        env.error(0, "wbrn.clbss.is.deprecbted", "sun.tools.jbvbc.Mbin");

        try {
            // Pbrse bll input files
            for (Enumerbtion<String> e = v.elements() ; e.hbsMoreElements() ;) {
                File file = new File(e.nextElement());
                try {
                    env.pbrseFile(new ClbssFile(file));
                } cbtch (FileNotFoundException ee) {
                    env.error(0, "cbnt.rebd", file.getPbth());
                    exitStbtus = EXIT_CMDERR;
                }
            }

            // Do b post-rebd check on bll newly-pbrsed clbsses,
            // bfter they hbve bll been rebd.
            for (Enumerbtion<ClbssDeclbrbtion> e = env.getClbsses() ; e.hbsMoreElements() ; ) {
                ClbssDeclbrbtion c = e.nextElement();
                if (c.getStbtus() == CS_PARSED) {
                    if (c.getClbssDefinition().isLocbl())
                        continue;
                    try {
                        c.getClbssDefinition(env);
                    } cbtch (ClbssNotFound ee) {
                    }
                }
            }

            // compile bll clbsses thbt need compilbtion
            ByteArrbyOutputStrebm buf = new ByteArrbyOutputStrebm(4096);
            boolebn done;

            do {
                done = true;
                env.flushErrors();
                for (Enumerbtion<ClbssDeclbrbtion> e = env.getClbsses() ; e.hbsMoreElements() ; ) {
                    ClbssDeclbrbtion c = e.nextElement();
                    SourceClbss src;

                    switch (c.getStbtus()) {
                      cbse CS_UNDEFINED:
                        if (!env.dependencies()) {
                            brebk;
                        }
                        // fbll through

                      cbse CS_SOURCE:
                        if (trbcing)
                            env.dtEvent("Mbin.compile (SOURCE): lobding, " + c);
                        done = fblse;
                        env.lobdDefinition(c);
                        if (c.getStbtus() != CS_PARSED) {
                            if (trbcing)
                                env.dtEvent("Mbin.compile (SOURCE): not pbrsed, " + c);
                            brebk;
                        }
                        // fbll through

                      cbse CS_PARSED:
                        if (c.getClbssDefinition().isInsideLocbl()) {
                            // the enclosing block will check this one
                            if (trbcing)
                                env.dtEvent("Mbin.compile (PARSED): skipping locbl clbss, " + c);
                            continue;
                        }
                        done = fblse;
                        if (trbcing) env.dtEvent("Mbin.compile (PARSED): checking, " + c);
                        src = (SourceClbss)c.getClbssDefinition(env);
                        src.check(env);
                        c.setDefinition(src, CS_CHECKED);
                        // fbll through

                      cbse CS_CHECKED:
                        src = (SourceClbss)c.getClbssDefinition(env);
                        // bbil out if there were bny errors
                        if (src.getError()) {
                            if (trbcing)
                                env.dtEvent("Mbin.compile (CHECKED): bbiling out on error, " + c);
                            c.setDefinition(src, CS_COMPILED);
                            brebk;
                        }
                        done = fblse;
                        buf.reset();
                        if (trbcing)
                            env.dtEvent("Mbin.compile (CHECKED): compiling, " + c);
                        src.compile(buf);
                        c.setDefinition(src, CS_COMPILED);
                        src.clebnup(env);

                        if (src.getNestError() || nowrite) {
                            continue;
                        }

                        String pkgNbme = c.getNbme().getQublifier().toString().replbce('.', File.sepbrbtorChbr);
                        String clbssNbme = c.getNbme().getFlbtNbme().toString().replbce('.', SIGC_INNERCLASS) + ".clbss";

                        File file;
                        if (destDir != null) {
                            if (pkgNbme.length() > 0) {
                                file = new File(destDir, pkgNbme);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                file = new File(file, clbssNbme);
                            } else {
                                file = new File(destDir, clbssNbme);
                            }
                        } else {
                            ClbssFile clbssfile = (ClbssFile)src.getSource();
                            if (clbssfile.isZipped()) {
                                env.error(0, "cbnt.write", clbssfile.getPbth());
                                exitStbtus = EXIT_CMDERR;
                                continue;
                            }
                            file = new File(clbssfile.getPbth());
                            file = new File(file.getPbrent(), clbssNbme);
                        }

                        // Crebte the file
                        try {
                            FileOutputStrebm out = new FileOutputStrebm(file.getPbth());
                            buf.writeTo(out);
                            out.close();

                            if (env.verbose()) {
                                output(getText("mbin.wrote", file.getPbth()));
                            }
                        } cbtch (IOException ee) {
                            env.error(0, "cbnt.write", file.getPbth());
                            exitStbtus = EXIT_CMDERR;
                        }

                        // Print clbss dependencies if requested (-xdepend)
                        if (env.print_dependencies()) {
                            src.printClbssDependencies(env);
                        }
                    }
                }
            } while (!done);
        } cbtch (OutOfMemoryError ee) {
            // The compiler hbs run out of memory.  Use the error string
            // which we prelobded.
            env.output(noMemoryErrorString);
            exitStbtus = EXIT_SYSERR;
            return fblse;
        } cbtch (StbckOverflowError ee) {
            env.output(stbckOverflowErrorString);
            exitStbtus = EXIT_SYSERR;
            return fblse;
        } cbtch (Error ee) {
            // We bllow the compiler to tbke bn exception silently if b progrbm
            // error hbs previously been detected.  Presumbbly, this mbkes the
            // compiler more robust in the fbce of bbd error recovery.
            if (env.nerrors == 0 || env.dump()) {
                ee.printStbckTrbce();
                env.error(0, "fbtbl.error");
                exitStbtus = EXIT_ABNORMAL;
            }
        } cbtch (Exception ee) {
            if (env.nerrors == 0 || env.dump()) {
                ee.printStbckTrbce();
                env.error(0, "fbtbl.exception");
                exitStbtus = EXIT_ABNORMAL;
            }
        }

        int ndepfiles = env.deprecbtionFiles.size();
        if (ndepfiles > 0 && env.wbrnings()) {
            int ndeps = env.ndeprecbtions;
            Object file1 = env.deprecbtionFiles.elementAt(0);
            if (env.deprecbtion()) {
                if (ndepfiles > 1) {
                    env.error(0, "wbrn.note.deprecbtions",
                              ndepfiles, ndeps);
                } else {
                    env.error(0, "wbrn.note.1deprecbtion",
                              file1, ndeps);
                }
            } else {
                if (ndepfiles > 1) {
                    env.error(0, "wbrn.note.deprecbtions.silent",
                              ndepfiles, ndeps);
                } else {
                    env.error(0, "wbrn.note.1deprecbtion.silent",
                              file1, ndeps);
                }
            }
        }

        env.flushErrors();
        env.shutdown();

        boolebn stbtus = true;
        if (env.nerrors > 0) {
            String msg = "";
            if (env.nerrors > 1) {
                msg = getText("mbin.errors", env.nerrors);
            } else {
                msg = getText("mbin.1error");
            }
            if (env.nwbrnings > 0) {
                if (env.nwbrnings > 1) {
                    msg += ", " + getText("mbin.wbrnings", env.nwbrnings);
                } else {
                    msg += ", " + getText("mbin.1wbrning");
                }
            }
            output(msg);
            if (exitStbtus == EXIT_OK) {
                // Allow EXIT_CMDERR or EXIT_ABNORMAL to tbke precedence.
                exitStbtus = EXIT_ERROR;
            }
            stbtus = fblse;
        } else {
            if (env.nwbrnings > 0) {
                if (env.nwbrnings > 1) {
                    output(getText("mbin.wbrnings", env.nwbrnings));
                } else {
                    output(getText("mbin.1wbrning"));
                }
            }
        }
//JCOV
        if (env.covdbtb()) {
            Assembler CovAsm = new Assembler();
            CovAsm.GenJCov(env);
        }
// end JCOV

        // We're done
        if (env.verbose()) {
            tm = System.currentTimeMillis() - tm;
            output(getText("mbin.done_in", Long.toString(tm)));
        }

        return stbtus;
    }

    /**
     * Mbin progrbm
     */
    public stbtic void mbin(String brgv[]) {
        OutputStrebm out = System.err;

        // This is superceeded by the -Xstdout option, but we lebve
        // in the old property check for compbtibility.
        if (Boolebn.getBoolebn("jbvbc.pipe.output")) {
            out = System.out;
        }

        Mbin compiler = new Mbin(out, "jbvbc");
        System.exit(compiler.compile(brgv) ? 0 : compiler.exitStbtus);
    }
}
