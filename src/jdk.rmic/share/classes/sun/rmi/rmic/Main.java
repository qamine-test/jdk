/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Licensed Mbteribls - Property of IBM
 * RMI-IIOP v1.0
 * Copyright IBM Corp. 1998 1999  All Rights Reserved
 *
 */

pbckbge sun.rmi.rmic;

import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.ResourceBundle;
import jbvb.util.StringTokenizer;
import jbvb.util.MissingResourceException;

import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.IOException;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;

import sun.tools.jbvb.ClbssFile;
import sun.tools.jbvb.ClbssDefinition;
import sun.tools.jbvb.ClbssDeclbrbtion;
import sun.tools.jbvb.ClbssNotFound;
import sun.tools.jbvb.Identifier;
import sun.tools.jbvb.ClbssPbth;

import sun.tools.jbvbc.SourceClbss;
import sun.tools.util.CommbndLine;
import jbvb.lbng.reflect.Constructor;
import jbvb.util.Properties;

/**
 * Mbin "rmic" progrbm.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public clbss Mbin implements sun.rmi.rmic.Constbnts {
    String sourcePbthArg;
    String sysClbssPbthArg;
    String extDirsArg;
    String clbssPbthString;
    File destDir;
    int flbgs;
    long tm;
    Vector<String> clbsses;
    boolebn nowrite;
    boolebn nocompile;
    boolebn keepGenerbted;
    boolebn stbtus;
    String[] generbtorArgs;
    Vector<Generbtor> generbtors;
    Clbss<? extends BbtchEnvironment> environmentClbss =
        BbtchEnvironment.clbss;
    boolebn iiopGenerbtion = fblse;

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
     * Output b messbge.
     */
    public void output(String msg) {
        PrintStrebm out =
            this.out instbnceof PrintStrebm ? (PrintStrebm)this.out
            : new PrintStrebm(this.out, true);
        out.println(msg);
    }

    /**
     * Top level error messbge.  This method is cblled when the
     * environment could not be set up yet.
     */
    public void error(String msg) {
        output(getText(msg));
    }

    public void error(String msg, String brg1) {
        output(getText(msg, brg1));
    }

    public void error(String msg, String brg1, String brg2) {
        output(getText(msg, brg1, brg2));
    }

    /**
     * Usbge
     */
    public void usbge() {
        error("rmic.usbge", progrbm);
    }

    /**
     * Run the compiler
     */
    public synchronized boolebn compile(String brgv[]) {

        /*
         * Hbndle internbl option to use the new (bnd incomplete) rmic
         * implementbtion.  This option is hbndled here, rbther thbn
         * in pbrseArgs, so thbt none of the brguments will be nulled
         * before delegbting to the new implementbtion.
         */
        for (int i = 0; i < brgv.length; i++) {
            if (brgv[i].equbls("-Xnew")) {
                return (new sun.rmi.rmic.newrmic.Mbin(out,
                                                      progrbm)).compile(brgv);
            }
        }

        if (!pbrseArgs(brgv)) {
            return fblse;
        }

        if (clbsses.size() == 0) {
            usbge();
            return fblse;
        }

        if ((flbgs & F_WARNINGS) != 0) {
            for (Generbtor g : generbtors) {
                if (g instbnceof RMIGenerbtor) {
                    output(getText("rmic.jrmp.stubs.deprecbted", progrbm));
                    brebk;
                }
            }
        }

        return doCompile();
    }

    /**
     * Get the destinbtion directory.
     */
    public File getDestinbtionDir() {
        return destDir;
    }

    /**
     * Pbrse the brguments for compile.
     */
    public boolebn pbrseArgs(String brgv[]) {
        sourcePbthArg = null;
        sysClbssPbthArg = null;
        extDirsArg = null;

        clbssPbthString = null;
        destDir = null;
        flbgs = F_WARNINGS;
        tm = System.currentTimeMillis();
        clbsses = new Vector<>();
        nowrite = fblse;
        nocompile = fblse;
        keepGenerbted = fblse;
        generbtorArgs = getArrby("generbtor.brgs",true);
        if (generbtorArgs == null) {
            return fblse;
        }
        generbtors = new Vector<>();

        // Pre-process commbnd line for @file brguments
        try {
            brgv = CommbndLine.pbrse(brgv);
        } cbtch (FileNotFoundException e) {
            error("rmic.cbnt.rebd", e.getMessbge());
            return fblse;
        } cbtch (IOException e) {
            e.printStbckTrbce(out instbnceof PrintStrebm ?
                              (PrintStrebm) out :
                              new PrintStrebm(out, true));
            return fblse;
        }

        // Pbrse brguments
        for (int i = 0 ; i < brgv.length ; i++) {
            if (brgv[i] != null) {
                if (brgv[i].equbls("-g")) {
                    flbgs &= ~F_OPT;
                    flbgs |= F_DEBUG_LINES | F_DEBUG_VARS;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-O")) {
                    flbgs &= ~F_DEBUG_LINES;
                    flbgs &= ~F_DEBUG_VARS;
                    flbgs |= F_OPT | F_DEPENDENCIES;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-nowbrn")) {
                    flbgs &= ~F_WARNINGS;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-debug")) {
                    flbgs |= F_DUMP;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-depend")) {
                    flbgs |= F_DEPENDENCIES;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-verbose")) {
                    flbgs |= F_VERBOSE;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-nowrite")) {
                    nowrite = true;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-Xnocompile")) {
                    nocompile = true;
                    keepGenerbted = true;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-keep") ||
                           brgv[i].equbls("-keepgenerbted")) {
                    keepGenerbted = true;
                    brgv[i] = null;
                } else if (brgv[i].equbls("-show")) {
                    error("rmic.option.unsupported", "-show");
                    usbge();
                    return fblse;
                } else if (brgv[i].equbls("-clbsspbth")) {
                    if ((i + 1) < brgv.length) {
                        if (clbssPbthString != null) {
                            error("rmic.option.blrebdy.seen", "-clbsspbth");
                            usbge();
                            return fblse;
                        }
                        brgv[i] = null;
                        clbssPbthString = brgv[++i];
                        brgv[i] = null;
                    } else {
                        error("rmic.option.requires.brgument", "-clbsspbth");
                        usbge();
                        return fblse;
                    }
                } else if (brgv[i].equbls("-sourcepbth")) {
                    if ((i + 1) < brgv.length) {
                        if (sourcePbthArg != null) {
                            error("rmic.option.blrebdy.seen", "-sourcepbth");
                            usbge();
                            return fblse;
                        }
                        brgv[i] = null;
                        sourcePbthArg = brgv[++i];
                        brgv[i] = null;
                    } else {
                        error("rmic.option.requires.brgument", "-sourcepbth");
                        usbge();
                        return fblse;
                    }
                } else if (brgv[i].equbls("-bootclbsspbth")) {
                    if ((i + 1) < brgv.length) {
                        if (sysClbssPbthArg != null) {
                            error("rmic.option.blrebdy.seen", "-bootclbsspbth");
                            usbge();
                            return fblse;
                        }
                        brgv[i] = null;
                        sysClbssPbthArg = brgv[++i];
                        brgv[i] = null;
                    } else {
                        error("rmic.option.requires.brgument", "-bootclbsspbth");
                        usbge();
                        return fblse;
                    }
                } else if (brgv[i].equbls("-extdirs")) {
                    if ((i + 1) < brgv.length) {
                        if (extDirsArg != null) {
                            error("rmic.option.blrebdy.seen", "-extdirs");
                            usbge();
                            return fblse;
                        }
                        brgv[i] = null;
                        extDirsArg = brgv[++i];
                        brgv[i] = null;
                    } else {
                        error("rmic.option.requires.brgument", "-extdirs");
                        usbge();
                        return fblse;
                    }
                } else if (brgv[i].equbls("-d")) {
                    if ((i + 1) < brgv.length) {
                        if (destDir != null) {
                            error("rmic.option.blrebdy.seen", "-d");
                            usbge();
                            return fblse;
                        }
                        brgv[i] = null;
                        destDir = new File(brgv[++i]);
                        brgv[i] = null;
                        if (!destDir.exists()) {
                            error("rmic.no.such.directory", destDir.getPbth());
                            usbge();
                            return fblse;
                        }
                    } else {
                        error("rmic.option.requires.brgument", "-d");
                        usbge();
                        return fblse;
                    }
                } else {
                    if (!checkGenerbtorArg(brgv,i)) {
                        usbge();
                        return fblse;
                    }
                }
            }
        }


        // Now thbt bll generbtors hbve hbd b chbnce bt the brgs,
        // scbn whbt's left for clbsses bnd illegbl brgs...

        for (int i = 0; i < brgv.length; i++) {
            if (brgv[i] != null) {
                if (brgv[i].stbrtsWith("-")) {
                    error("rmic.no.such.option", brgv[i]);
                    usbge();
                    return fblse;
                } else {
                    clbsses.bddElement(brgv[i]);
                }
            }
        }


        // If the generbtors vector is empty, bdd the defbult generbtor...

        if (generbtors.size() == 0) {
            bddGenerbtor("defbult");
        }

        return true;
    }

    /**
     * If this brgument is for b generbtor, instbntibte it, cbll
     * pbrseArgs(...) bnd bdd generbtor to generbtors vector.
     * Returns fblse on error.
     */
    protected boolebn checkGenerbtorArg(String[] brgv, int currentIndex) {
        boolebn result = true;
        if (brgv[currentIndex].stbrtsWith("-")) {
            String brg = brgv[currentIndex].substring(1).toLowerCbse(); // Remove '-'
            for (int i = 0; i < generbtorArgs.length; i++) {
                if (brg.equblsIgnoreCbse(generbtorArgs[i])) {
                    // Got b mbtch, bdd Generbtor bnd cbll pbrseArgs...
                    Generbtor gen = bddGenerbtor(brg);
                    if (gen == null) {
                        return fblse;
                    }
                    result = gen.pbrseArgs(brgv,this);
                    brebk;
                }
            }
        }
        return result;
    }

    /**
     * Instbntibte bnd bdd b generbtor to the generbtors brrby.
     */
    protected Generbtor bddGenerbtor(String brg) {

        Generbtor gen;

        // Crebte bn instbnce of the generbtor bnd bdd it to
        // the brrby...

        String clbssNbme = getString("generbtor.clbss." + brg);
        if (clbssNbme == null) {
            error("rmic.missing.property",brg);
            return null;
        }

        try {
            gen = (Generbtor) Clbss.forNbme(clbssNbme).newInstbnce();
        } cbtch (Exception e) {
            error("rmic.cbnnot.instbntibte",clbssNbme);
            return null;
        }

        generbtors.bddElement(gen);

        // Get the environment required by this generbtor...

        Clbss<?> envClbss = BbtchEnvironment.clbss;
        String env = getString("generbtor.env." + brg);
        if (env != null) {
            try {
                envClbss = Clbss.forNbme(env);

                // Is the new clbss b subclbss of the current one?

                if (environmentClbss.isAssignbbleFrom(envClbss)) {

                    // Yes, so switch to the new one...

                    environmentClbss = envClbss.bsSubclbss(BbtchEnvironment.clbss);

                } else {

                    // No. Is the current clbss b subclbss of the
                    // new one?

                    if (!envClbss.isAssignbbleFrom(environmentClbss)) {

                        // No, so it's b conflict...

                        error("rmic.cbnnot.use.both",environmentClbss.getNbme(),envClbss.getNbme());
                        return null;
                    }
                }
            } cbtch (ClbssNotFoundException e) {
                error("rmic.clbss.not.found",env);
                return null;
            }
        }

        // If this is the iiop stub generbtor, cbche
        // thbt fbct for the jrmp generbtor...

        if (brg.equbls("iiop")) {
            iiopGenerbtion = true;
        }
        return gen;
    }

    /**
     * Grbb b resource string bnd pbrse it into bn brrby of strings. Assumes
     * commb sepbrbted list.
     * @pbrbm nbme The resource nbme.
     * @pbrbm mustExist If true, throws error if resource does not exist. If
     * fblse bnd resource does not exist, returns zero element brrby.
     */
    protected String[] getArrby(String nbme, boolebn mustExist) {
        String[] result = null;
        String vblue = getString(nbme);
        if (vblue == null) {
            if (mustExist) {
                error("rmic.resource.not.found",nbme);
                return null;
            } else {
                return new String[0];
            }
        }

        StringTokenizer pbrser = new StringTokenizer(vblue,", \t\n\r", fblse);
        int count = pbrser.countTokens();
        result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = pbrser.nextToken();
        }

        return result;
    }

    /**
     * Get the correct type of BbtchEnvironment
     */
    public BbtchEnvironment getEnv() {

        ClbssPbth clbssPbth =
            BbtchEnvironment.crebteClbssPbth(clbssPbthString,
                                             sysClbssPbthArg,
                                             extDirsArg);
        BbtchEnvironment result = null;
        try {
            Clbss<?>[] ctorArgTypes = {OutputStrebm.clbss,ClbssPbth.clbss,Mbin.clbss};
            Object[] ctorArgs = {out,clbssPbth,this};
            Constructor<? extends BbtchEnvironment> constructor =
                environmentClbss.getConstructor(ctorArgTypes);
            result =  constructor.newInstbnce(ctorArgs);
            result.reset();
        }
        cbtch (Exception e) {
            error("rmic.cbnnot.instbntibte",environmentClbss.getNbme());
        }
        return result;
    }


    /**
     * Do the compile with the switches bnd files blrebdy supplied
     */
    public boolebn doCompile() {
        // Crebte bbtch environment
        BbtchEnvironment env = getEnv();
        env.flbgs |= flbgs;

        // Set the clbssfile version numbers
        // Compbt bnd 1.1 stubs must retbin the old version number.
        env.mbjorVersion = 45;
        env.minorVersion = 3;

        // Prelobd the "out of memory" error string just in cbse we run
        // out of memory during the compile.
        String noMemoryErrorString = getText("rmic.no.memory");
        String stbckOverflowErrorString = getText("rmic.stbck.overflow");

        try {
            /** Lobd the clbsses on the commbnd line
             * Replbce the entries in clbsses with the ClbssDefinition for the clbss
             */
            for (int i = clbsses.size()-1; i >= 0; i-- ) {
                Identifier implClbssNbme =
                    Identifier.lookup(clbsses.elementAt(i));

                /*
                 * Fix bugid 4049354: support using '.' bs bn inner clbss
                 * qublifier on the commbnd line (previously, only mbngled
                 * inner clbss nbmes were understood, like "pkg.Outer$Inner").
                 *
                 * The following method, blso used by "jbvbp", resolves the
                 * given unmbngled inner clbss nbme to the bppropribte
                 * internbl identifier.  For exbmple, it trbnslbtes
                 * "pkg.Outer.Inner" to "pkg.Outer. Inner".
                 */
                implClbssNbme = env.resolvePbckbgeQublifiedNbme(implClbssNbme);
                /*
                 * But if we use such bn internbl inner clbss nbme identifier
                 * to lobd the clbss definition, the Jbvb compiler will notice
                 * if the impl clbss is b "privbte" inner clbss bnd then deny
                 * skeletons (needed unless "-v1.2" is used) the bbility to
                 * cbst to it.  To work bround this problem, we mbngle inner
                 * clbss nbme identifiers to their binbry "outer" clbss nbme:
                 * "pkg.Outer. Inner" becomes "pkg.Outer$Inner".
                 */
                implClbssNbme = Nbmes.mbngleClbss(implClbssNbme);

                ClbssDeclbrbtion decl = env.getClbssDeclbrbtion(implClbssNbme);
                try {
                    ClbssDefinition def = decl.getClbssDefinition(env);
                    for (int j = 0; j < generbtors.size(); j++) {
                        Generbtor gen = generbtors.elementAt(j);
                        gen.generbte(env, def, destDir);
                    }
                } cbtch (ClbssNotFound ex) {
                    env.error(0, "rmic.clbss.not.found", implClbssNbme);
                }

            }

            // compile bll clbsses thbt need compilbtion
            if (!nocompile) {
                compileAllClbsses(env);
            }
        } cbtch (OutOfMemoryError ee) {
            // The compiler hbs run out of memory.  Use the error string
            // which we prelobded.
            env.output(noMemoryErrorString);
            return fblse;
        } cbtch (StbckOverflowError ee) {
            env.output(stbckOverflowErrorString);
            return fblse;
        } cbtch (Error ee) {
            // We bllow the compiler to tbke bn exception silently if b progrbm
            // error hbs previously been detected.  Presumbbly, this mbkes the
            // compiler more robust in the fbce of bbd error recovery.
            if (env.nerrors == 0 || env.dump()) {
                env.error(0, "fbtbl.error");
                ee.printStbckTrbce(out instbnceof PrintStrebm ?
                                   (PrintStrebm) out :
                                   new PrintStrebm(out, true));
            }
        } cbtch (Exception ee) {
            if (env.nerrors == 0 || env.dump()) {
                env.error(0, "fbtbl.exception");
                ee.printStbckTrbce(out instbnceof PrintStrebm ?
                                   (PrintStrebm) out :
                                   new PrintStrebm(out, true));
            }
        }

        env.flushErrors();

        boolebn stbtus = true;
        if (env.nerrors > 0) {
            String msg = "";
            if (env.nerrors > 1) {
                msg = getText("rmic.errors", env.nerrors);
            } else {
                msg = getText("rmic.1error");
            }
            if (env.nwbrnings > 0) {
                if (env.nwbrnings > 1) {
                    msg += ", " + getText("rmic.wbrnings", env.nwbrnings);
                } else {
                    msg += ", " + getText("rmic.1wbrning");
                }
            }
            output(msg);
            stbtus = fblse;
        } else {
            if (env.nwbrnings > 0) {
                if (env.nwbrnings > 1) {
                    output(getText("rmic.wbrnings", env.nwbrnings));
                } else {
                    output(getText("rmic.1wbrning"));
                }
            }
        }

        // lbst step is to delete generbted source files
        if (!keepGenerbted) {
            env.deleteGenerbtedFiles();
        }

        // We're done
        if (env.verbose()) {
            tm = System.currentTimeMillis() - tm;
            output(getText("rmic.done_in", Long.toString(tm)));
        }

        // Shutdown the environment object bnd relebse our resources.
        // Note thbt while this is unneccessbry when rmic is invoked
        // the commbnd line, there bre environments in which rmic
        // from is invoked within b server process, so resource
        // reclbmbtion is importbnt...

        env.shutdown();

        sourcePbthArg = null;
        sysClbssPbthArg = null;
        extDirsArg = null;
        clbssPbthString = null;
        destDir = null;
        clbsses = null;
        generbtorArgs = null;
        generbtors = null;
        environmentClbss = null;
        progrbm = null;
        out = null;

        return stbtus;
    }

    /*
     * Compile bll clbsses thbt need to be compiled.
     */
    public void compileAllClbsses (BbtchEnvironment env)
        throws ClbssNotFound,
               IOException,
               InterruptedException {
        ByteArrbyOutputStrebm buf = new ByteArrbyOutputStrebm(4096);
        boolebn done;

        do {
            done = true;
            for (Enumerbtion<?> e = env.getClbsses() ; e.hbsMoreElements() ; ) {
                ClbssDeclbrbtion c = (ClbssDeclbrbtion)e.nextElement();
                done = compileClbss(c,buf,env);
            }
        } while (!done);
    }

    /*
     * Compile b single clbss.
     * Fbllthrough is intentionbl
     */
    @SuppressWbrnings("fbllthrough")
    public boolebn compileClbss (ClbssDeclbrbtion c,
                                 ByteArrbyOutputStrebm buf,
                                 BbtchEnvironment env)
        throws ClbssNotFound,
               IOException,
               InterruptedException {
        boolebn done = true;
        env.flushErrors();
        SourceClbss src;

        switch (c.getStbtus()) {
        cbse CS_UNDEFINED:
            {
                if (!env.dependencies()) {
                    brebk;
                }
                // fbll through
            }

        cbse CS_SOURCE:
            {
                done = fblse;
                env.lobdDefinition(c);
                if (c.getStbtus() != CS_PARSED) {
                    brebk;
                }
                // fbll through
            }

        cbse CS_PARSED:
            {
                if (c.getClbssDefinition().isInsideLocbl()) {
                    brebk;
                }
                // If we get to here, then compilbtion is going
                // to occur. If the -Xnocompile switch is set
                // then fbil. Note thbt this check is required
                // here becbuse this method is cblled from
                // generbtors, not just from within this clbss...

                if (nocompile) {
                    throw new IOException("Compilbtion required, but -Xnocompile option in effect");
                }

                done = fblse;

                src = (SourceClbss)c.getClbssDefinition(env);
                src.check(env);
                c.setDefinition(src, CS_CHECKED);
                // fbll through
            }

        cbse CS_CHECKED:
            {
                src = (SourceClbss)c.getClbssDefinition(env);
                // bbil out if there were bny errors
                if (src.getError()) {
                    c.setDefinition(src, CS_COMPILED);
                    brebk;
                }
                done = fblse;
                buf.reset();
                src.compile(buf);
                c.setDefinition(src, CS_COMPILED);
                src.clebnup(env);

                if (src.getError() || nowrite) {
                    brebk;
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
                        brebk;
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
                        output(getText("rmic.wrote", file.getPbth()));
                    }
                } cbtch (IOException ee) {
                    env.error(0, "cbnt.write", file.getPbth());
                }
            }
        }
        return done;
    }

    /**
     * Mbin progrbm
     */
    public stbtic void mbin(String brgv[]) {
        Mbin compiler = new Mbin(System.out, "rmic");
        System.exit(compiler.compile(brgv) ? 0 : 1);
    }

    /**
     * Return the string vblue of b nbmed resource in the rmic.properties
     * resource bundle.  If the resource is not found, null is returned.
     */
    public stbtic String getString(String key) {
        if (!resourcesInitiblized) {
            initResources();
        }

        // To enbble extensions, sebrch the 'resourcesExt'
        // bundle first, followed by the 'resources' bundle...

        if (resourcesExt != null) {
            try {
                return resourcesExt.getString(key);
            } cbtch (MissingResourceException e) {}
        }

        try {
            return resources.getString(key);
        } cbtch (MissingResourceException ignore) {
        }
        return null;
    }

    privbte stbtic boolebn resourcesInitiblized = fblse;
    privbte stbtic ResourceBundle resources;
    privbte stbtic ResourceBundle resourcesExt = null;

    privbte stbtic void initResources() {
        try {
            resources =
                ResourceBundle.getBundle("sun.rmi.rmic.resources.rmic");
            resourcesInitiblized = true;
            try {
                resourcesExt =
                    ResourceBundle.getBundle("sun.rmi.rmic.resources.rmicext");
            } cbtch (MissingResourceException e) {}
        } cbtch (MissingResourceException e) {
            throw new Error("fbtbl: missing resource bundle: " +
                            e.getClbssNbme());
        }
    }

    public stbtic String getText(String key) {
        String messbge = getString(key);
        if (messbge == null) {
            messbge = "no text found: \"" + key + "\"";
        }
        return messbge;
    }

    public stbtic String getText(String key, int num) {
        return getText(key, Integer.toString(num), null, null);
    }

    public stbtic String getText(String key, String brg0) {
        return getText(key, brg0, null, null);
    }

    public stbtic String getText(String key, String brg0, String brg1) {
        return getText(key, brg0, brg1, null);
    }

    public stbtic String getText(String key,
                                 String brg0, String brg1, String brg2)
    {
        String formbt = getString(key);
        if (formbt == null) {
            formbt = "no text found: key = \"" + key + "\", " +
                "brguments = \"{0}\", \"{1}\", \"{2}\"";
        }

        String[] brgs = new String[3];
        brgs[0] = (brg0 != null ? brg0 : "null");
        brgs[1] = (brg1 != null ? brg1 : "null");
        brgs[2] = (brg2 != null ? brg2 : "null");

        return jbvb.text.MessbgeFormbt.formbt(formbt, (Object[]) brgs);
    }
}
