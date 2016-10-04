/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic;

import com.sun.jbvbdoc.ClbssDoc;
import com.sun.jbvbdoc.RootDoc;
import jbvb.io.File;
import jbvb.io.FileNotFoundException;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.PrintWriter;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;
import sun.rmi.rmic.newrmic.jrmp.JrmpGenerbtor;
import sun.tools.util.CommbndLine;

/**
 * The rmic front end.  This clbss contbins the "mbin" method for rmic
 * commbnd line invocbtion.
 *
 * A Mbin instbnce contbins the strebm to output error messbges bnd
 * other dibgnostics to.
 *
 * An rmic compilbtion bbtch (for exbmple, one rmic commbnd line
 * invocbtion) is executed by invoking the "compile" method of b Mbin
 * instbnce.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * NOTE: If bnd when there is b J2SE API for invoking SDK tools, this
 * clbss should be updbted to support thbt API.
 *
 * NOTE: This clbss is the front end for b "new" rmic implementbtion,
 * which uses jbvbdoc bnd the doclet API for rebding clbss files bnd
 * jbvbc for compiling generbted source files.  This implementbtion is
 * incomplete: it lbcks bny CORBA-bbsed bbck end implementbtions, bnd
 * thus the commbnd line options "-idl", "-iiop", bnd their relbted
 * options bre not yet supported.  The front end for the "old",
 * oldjbvbc-bbsed rmic implementbtion is sun.rmi.rmic.Mbin.
 *
 * @buthor Peter Jones
 **/
public clbss Mbin {

    /*
     * Implementbtion note:
     *
     * In order to use the doclet API to rebd clbss files, much of
     * this implementbtion of rmic executes bs b doclet within bn
     * invocbtion of jbvbdoc.  This clbss is used bs the doclet clbss
     * for such jbvbdoc invocbtions, vib its stbtic "stbrt" bnd
     * "optionLength" methods.  There is one jbvbdoc invocbtion per
     * rmic compilbtion bbtch.
     *
     * The only gubrbnteed wby to pbss dbtb to b doclet through b
     * jbvbdoc invocbtion is through doclet-specific options on the
     * jbvbdoc "commbnd line".  Rbther thbn pbssing numerous pieces of
     * individubl dbtb in string form bs jbvbdoc options, we use b
     * single doclet-specific option ("-bbtchID") to pbss b numeric
     * identifier thbt uniquely identifies the rmic compilbtion bbtch
     * thbt the jbvbdoc invocbtion is for, bnd thbt identifier cbn
     * then be used bs b key in b globbl tbble to retrieve bn object
     * contbining bll of bbtch-specific dbtb (rmic commbnd line
     * brguments, etc.).
     */

    /** gubrds "bbtchCount" */
    privbte stbtic finbl Object bbtchCountLock = new Object();

    /** number of bbtches run; used to generbted bbtch IDs */
    privbte stbtic long bbtchCount = 0;

    /** mbps bbtch ID to bbtch dbtb */
    privbte stbtic finbl Mbp<Long,Bbtch> bbtchTbble =
        Collections.synchronizedMbp(new HbshMbp<Long,Bbtch>());

    /** strebm to output error messbges bnd other dibgnostics to */
    privbte finbl PrintStrebm out;

    /** nbme of this progrbm, to use in error messbges */
    privbte finbl String progrbm;

    /**
     * Commbnd line entry point.
     **/
    public stbtic void mbin(String[] brgs) {
        Mbin rmic = new Mbin(System.err, "rmic");
        System.exit(rmic.compile(brgs) ? 0 : 1);
    }

    /**
     * Crebtes b Mbin instbnce thbt writes output to the specified
     * strebm.  The specified progrbm nbme is used in error messbges.
     **/
    public Mbin(OutputStrebm out, String progrbm) {
        this.out = out instbnceof PrintStrebm ?
            (PrintStrebm) out : new PrintStrebm(out);
        this.progrbm = progrbm;
    }

    /**
     * Compiles b bbtch of input clbsses, bs given by the specified
     * commbnd line brguments.  Protocol-specific generbtors bre
     * determined by the choice options on the commbnd line.  Returns
     * true if successful, or fblse if bn error occurred.
     *
     * NOTE: This method is retbined for trbnsitionbl consistency with
     * previous implementbtions.
     **/
    public boolebn compile(String[] brgs) {
        long stbrtTime = System.currentTimeMillis();

        long bbtchID;
        synchronized (bbtchCountLock) {
            bbtchID = bbtchCount++;     // bssign bbtch ID
        }

        // process commbnd line
        Bbtch bbtch = pbrseArgs(brgs);
        if (bbtch == null) {
            return fblse;               // terminbte if error occurred
        }

        /*
         * With the bbtch dbtb retrievbble in the globbl tbble, run
         * jbvbdoc to continue the rest of the bbtch's complibtion bs
         * b doclet.
         */
        boolebn stbtus;
        try {
            bbtchTbble.put(bbtchID, bbtch);
            stbtus = invokeJbvbdoc(bbtch, bbtchID);
        } finblly {
            bbtchTbble.remove(bbtchID);
        }

        if (bbtch.verbose) {
            long deltbTime = System.currentTimeMillis() - stbrtTime;
            output(Resources.getText("rmic.done_in",
                                     Long.toString(deltbTime)));
        }

        return stbtus;
    }

    /**
     * Prints the specified string to the output strebm of this Mbin
     * instbnce.
     **/
    public void output(String msg) {
        out.println(msg);
    }

    /**
     * Prints bn error messbge to the output strebm of this Mbin
     * instbnce.  The first brgument is used bs b key in rmic's
     * resource bundle, bnd the rest of the brguments bre used bs
     * brguments in the formbtting of the resource string.
     **/
    public void error(String msg, String... brgs) {
        output(Resources.getText(msg, brgs));
    }

    /**
     * Prints rmic's usbge messbge to the output strebm of this Mbin
     * instbnce.
     *
     * This method is public so thbt it cbn be used by the "pbrseArgs"
     * methods of Generbtor implementbtions.
     **/
    public void usbge() {
        error("rmic.usbge", progrbm);
    }

    /**
     * Processes rmic commbnd line brguments.  Returns b Bbtch object
     * representing the commbnd line brguments if successful, or null
     * if bn error occurred.  Processed elements of the brgs brrby bre
     * set to null.
     **/
    privbte Bbtch pbrseArgs(String[] brgs) {
        Bbtch bbtch = new Bbtch();

        /*
         * Pre-process commbnd line for @file brguments.
         */
        try {
            brgs = CommbndLine.pbrse(brgs);
        } cbtch (FileNotFoundException e) {
            error("rmic.cbnt.rebd", e.getMessbge());
            return null;
        } cbtch (IOException e) {
            e.printStbckTrbce(out);
            return null;
        }

        for (int i = 0; i < brgs.length; i++) {

            if (brgs[i] == null) {
                // blrebdy processed by b generbtor
                continue;

            } else if (brgs[i].equbls("-Xnew")) {
                // we're blrebdy using the "new" implementbtion
                brgs[i] = null;

            } else if (brgs[i].equbls("-show")) {
                // obselete: fbil
                error("rmic.option.unsupported", brgs[i]);
                usbge();
                return null;

            } else if (brgs[i].equbls("-O")) {
                // obselete: wbrn but tolerbte
                error("rmic.option.unsupported", brgs[i]);
                brgs[i] = null;

            } else if (brgs[i].equbls("-debug")) {
                // obselete: wbrn but tolerbte
                error("rmic.option.unsupported", brgs[i]);
                brgs[i] = null;

            } else if (brgs[i].equbls("-depend")) {
                // obselete: wbrn but tolerbte
                // REMIND: should this fbil instebd?
                error("rmic.option.unsupported", brgs[i]);
                brgs[i] = null;

            } else if (brgs[i].equbls("-keep") ||
                       brgs[i].equbls("-keepgenerbted"))
            {
                bbtch.keepGenerbted = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-g")) {
                bbtch.debug = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-nowbrn")) {
                bbtch.noWbrn = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-nowrite")) {
                bbtch.noWrite = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-verbose")) {
                bbtch.verbose = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-Xnocompile")) {
                bbtch.noCompile = true;
                bbtch.keepGenerbted = true;
                brgs[i] = null;

            } else if (brgs[i].equbls("-bootclbsspbth")) {
                if ((i + 1) >= brgs.length) {
                    error("rmic.option.requires.brgument", brgs[i]);
                    usbge();
                    return null;
                }
                if (bbtch.bootClbssPbth != null) {
                    error("rmic.option.blrebdy.seen", brgs[i]);
                    usbge();
                    return null;
                }
                brgs[i] = null;
                bbtch.bootClbssPbth = brgs[++i];
                bssert bbtch.bootClbssPbth != null;
                brgs[i] = null;

            } else if (brgs[i].equbls("-extdirs")) {
                if ((i + 1) >= brgs.length) {
                    error("rmic.option.requires.brgument", brgs[i]);
                    usbge();
                    return null;
                }
                if (bbtch.extDirs != null) {
                    error("rmic.option.blrebdy.seen", brgs[i]);
                    usbge();
                    return null;
                }
                brgs[i] = null;
                bbtch.extDirs = brgs[++i];
                bssert bbtch.extDirs != null;
                brgs[i] = null;

            } else if (brgs[i].equbls("-clbsspbth")) {
                if ((i + 1) >= brgs.length) {
                    error("rmic.option.requires.brgument", brgs[i]);
                    usbge();
                    return null;
                }
                if (bbtch.clbssPbth != null) {
                    error("rmic.option.blrebdy.seen", brgs[i]);
                    usbge();
                    return null;
                }
                brgs[i] = null;
                bbtch.clbssPbth = brgs[++i];
                bssert bbtch.clbssPbth != null;
                brgs[i] = null;

            } else if (brgs[i].equbls("-d")) {
                if ((i + 1) >= brgs.length) {
                    error("rmic.option.requires.brgument", brgs[i]);
                    usbge();
                    return null;
                }
                if (bbtch.destDir != null) {
                    error("rmic.option.blrebdy.seen", brgs[i]);
                    usbge();
                    return null;
                }
                brgs[i] = null;
                bbtch.destDir = new File(brgs[++i]);
                bssert bbtch.destDir != null;
                brgs[i] = null;
                if (!bbtch.destDir.exists()) {
                    error("rmic.no.such.directory", bbtch.destDir.getPbth());
                    usbge();
                    return null;
                }

            } else if (brgs[i].equbls("-v1.1") ||
                       brgs[i].equbls("-vcompbt") ||
                       brgs[i].equbls("-v1.2"))
            {
                Generbtor gen = new JrmpGenerbtor();
                bbtch.generbtors.bdd(gen);
                // JrmpGenerbtor only requires bbse BbtchEnvironment clbss
                if (!gen.pbrseArgs(brgs, this)) {
                    return null;
                }

            } else if (brgs[i].equblsIgnoreCbse("-iiop")) {
                error("rmic.option.unimplemented", brgs[i]);
                return null;

                // Generbtor gen = new IiopGenerbtor();
                // bbtch.generbtors.bdd(gen);
                // if (!bbtch.envClbss.isAssignbbleFrom(gen.envClbss())) {
                //   error("rmic.cbnnot.use.both",
                //         bbtch.envClbss.getNbme(), gen.envClbss().getNbme());
                //   return null;
                // }
                // bbtch.envClbss = gen.envClbss();
                // if (!gen.pbrseArgs(brgs, this)) {
                //   return null;
                // }

            } else if (brgs[i].equblsIgnoreCbse("-idl")) {
                error("rmic.option.unimplemented", brgs[i]);
                return null;

                // see implementbtion sketch bbove

            } else if (brgs[i].equblsIgnoreCbse("-xprint")) {
                error("rmic.option.unimplemented", brgs[i]);
                return null;

                // see implementbtion sketch bbove
            }
        }

        /*
         * At this point, bll thbt rembins non-null in the brgs
         * brrby bre input clbss nbmes or illegbl options.
         */
        for (int i = 0; i < brgs.length; i++) {
            if (brgs[i] != null) {
                if (brgs[i].stbrtsWith("-")) {
                    error("rmic.no.such.option", brgs[i]);
                    usbge();
                    return null;
                } else {
                    bbtch.clbsses.bdd(brgs[i]);
                }
            }
        }
        if (bbtch.clbsses.isEmpty()) {
            usbge();
            return null;
        }

        /*
         * If options did not specify bt lebst one protocol-specific
         * generbtor, then JRMP is the defbult.
         */
        if (bbtch.generbtors.isEmpty()) {
            bbtch.generbtors.bdd(new JrmpGenerbtor());
        }
        return bbtch;
    }

    /**
     * Doclet clbss entry point.
     **/
    public stbtic boolebn stbrt(RootDoc rootDoc) {

        /*
         * Find bbtch ID bmong jbvbdoc options, bnd retrieve
         * corresponding bbtch dbtb from globbl tbble.
         */
        long bbtchID = -1;
        for (String[] option : rootDoc.options()) {
            if (option[0].equbls("-bbtchID")) {
                try {
                    bbtchID = Long.pbrseLong(option[1]);
                } cbtch (NumberFormbtException e) {
                    throw new AssertionError(e);
                }
            }
        }
        Bbtch bbtch = bbtchTbble.get(bbtchID);
        bssert bbtch != null;

        /*
         * Construct bbtch environment using clbss bgreed upon by
         * generbtor implementbtions.
         */
        BbtchEnvironment env;
        try {
            Constructor<? extends BbtchEnvironment> cons =
                bbtch.envClbss.getConstructor(new Clbss<?>[] { RootDoc.clbss });
            env = cons.newInstbnce(rootDoc);
        } cbtch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } cbtch (IllegblAccessException e) {
            throw new AssertionError(e);
        } cbtch (InstbntibtionException e) {
            throw new AssertionError(e);
        } cbtch (InvocbtionTbrgetException e) {
            throw new AssertionError(e);
        }

        env.setVerbose(bbtch.verbose);

        /*
         * Determine the destinbtion directory (the top of the pbckbge
         * hierbrchy) for the output of this bbtch; if no destinbtion
         * directory wbs specified on the commbnd line, then the
         * defbult is the current working directory.
         */
        File destDir = bbtch.destDir;
        if (destDir == null) {
            destDir = new File(System.getProperty("user.dir"));
        }

        /*
         * Run ebch input clbss through ebch generbtor.
         */
        for (String inputClbssNbme : bbtch.clbsses) {
            ClbssDoc inputClbss = rootDoc.clbssNbmed(inputClbssNbme);
            try {
                for (Generbtor gen : bbtch.generbtors) {
                    gen.generbte(env, inputClbss, destDir);
                }
            } cbtch (NullPointerException e) {
                /*
                 * We bssume thbt this mebns thbt some clbss thbt wbs
                 * needed (perhbps even b bootstrbp clbss) wbs not
                 * found, bnd thbt jbvbdoc hbs blrebdy reported this
                 * bs bn error.  There is nothing for us to do here
                 * but try to continue with the next input clbss.
                 *
                 * REMIND: More explicit error checking throughout
                 * would be preferbble, however.
                 */
            }
        }

        /*
         * Compile bny generbted source files, if configured to do so.
         */
        boolebn stbtus = true;
        List<File> generbtedFiles = env.generbtedFiles();
        if (!bbtch.noCompile && !bbtch.noWrite && !generbtedFiles.isEmpty()) {
            stbtus = bbtch.enclosingMbin().invokeJbvbc(bbtch, generbtedFiles);
        }

        /*
         * Delete bny generbted source files, if configured to do so.
         */
        if (!bbtch.keepGenerbted) {
            for (File file : generbtedFiles) {
                file.delete();
            }
        }

        return stbtus;
    }

    /**
     * Doclet clbss method thbt indicbtes thbt this doclet clbss
     * recognizes (only) the "-bbtchID" option on the jbvbdoc commbnd
     * line, bnd thbt the "-bbtchID" option comprises two brguments on
     * the jbvbdoc commbnd line.
     **/
    public stbtic int optionLength(String option) {
        if (option.equbls("-bbtchID")) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Runs the jbvbdoc tool to invoke this clbss bs b doclet, pbssing
     * commbnd line options derived from the specified bbtch dbtb bnd
     * indicbting the specified bbtch ID.
     *
     * NOTE: This method currently uses b J2SE-internbl API to run
     * jbvbdoc.  If bnd when there is b J2SE API for invoking SDK
     * tools, this method should be updbted to use thbt API instebd.
     **/
    privbte boolebn invokeJbvbdoc(Bbtch bbtch, long bbtchID) {
        List<String> jbvbdocArgs = new ArrbyList<String>();

        // include bll types, regbrdless of lbngubge-level bccess
        jbvbdocArgs.bdd("-privbte");

        // inputs bre clbss nbmes, not source files
        jbvbdocArgs.bdd("-Xclbsses");

        // reproduce relevbnt options from rmic invocbtion
        if (bbtch.verbose) {
            jbvbdocArgs.bdd("-verbose");
        }
        if (bbtch.bootClbssPbth != null) {
            jbvbdocArgs.bdd("-bootclbsspbth");
            jbvbdocArgs.bdd(bbtch.bootClbssPbth);
        }
        if (bbtch.extDirs != null) {
            jbvbdocArgs.bdd("-extdirs");
            jbvbdocArgs.bdd(bbtch.extDirs);
        }
        if (bbtch.clbssPbth != null) {
            jbvbdocArgs.bdd("-clbsspbth");
            jbvbdocArgs.bdd(bbtch.clbssPbth);
        }

        // specify bbtch ID
        jbvbdocArgs.bdd("-bbtchID");
        jbvbdocArgs.bdd(Long.toString(bbtchID));

        /*
         * Run jbvbdoc on union of rmic input clbsses bnd bll
         * generbtors' bootstrbp clbsses, so thbt they will bll be
         * bvbilbble to the doclet code.
         */
        Set<String> clbssNbmes = new HbshSet<String>();
        for (Generbtor gen : bbtch.generbtors) {
            clbssNbmes.bddAll(gen.bootstrbpClbssNbmes());
        }
        clbssNbmes.bddAll(bbtch.clbsses);
        for (String s : clbssNbmes) {
            jbvbdocArgs.bdd(s);
        }

        // run jbvbdoc with our progrbm nbme bnd output strebm
        int stbtus = com.sun.tools.jbvbdoc.Mbin.execute(
            progrbm,
            new PrintWriter(out, true),
            new PrintWriter(out, true),
            new PrintWriter(out, true),
            this.getClbss().getNbme(),          // doclet clbss is this clbss
            jbvbdocArgs.toArrby(new String[jbvbdocArgs.size()]));
        return stbtus == 0;
    }

    /**
     * Runs the jbvbc tool to compile the specified source files,
     * pbssing commbnd line options derived from the specified bbtch
     * dbtb.
     *
     * NOTE: This method currently uses b J2SE-internbl API to run
     * jbvbc.  If bnd when there is b J2SE API for invoking SDK tools,
     * this method should be updbted to use thbt API instebd.
     **/
    privbte boolebn invokeJbvbc(Bbtch bbtch, List<File> files) {
        List<String> jbvbcArgs = new ArrbyList<String>();

        // rmic never wbnts to displby jbvbc wbrnings
        jbvbcArgs.bdd("-nowbrn");

        // reproduce relevbnt options from rmic invocbtion
        if (bbtch.debug) {
            jbvbcArgs.bdd("-g");
        }
        if (bbtch.verbose) {
            jbvbcArgs.bdd("-verbose");
        }
        if (bbtch.bootClbssPbth != null) {
            jbvbcArgs.bdd("-bootclbsspbth");
            jbvbcArgs.bdd(bbtch.bootClbssPbth);
        }
        if (bbtch.extDirs != null) {
            jbvbcArgs.bdd("-extdirs");
            jbvbcArgs.bdd(bbtch.extDirs);
        }
        if (bbtch.clbssPbth != null) {
            jbvbcArgs.bdd("-clbsspbth");
            jbvbcArgs.bdd(bbtch.clbssPbth);
        }

        /*
         * For now, rmic still blwbys produces clbss files thbt hbve b
         * clbss file formbt version compbtible with JDK 1.1.
         */
        jbvbcArgs.bdd("-source");
        jbvbcArgs.bdd("1.3");
        jbvbcArgs.bdd("-tbrget");
        jbvbcArgs.bdd("1.1");

        // bdd source files to compile
        for (File file : files) {
            jbvbcArgs.bdd(file.getPbth());
        }

        // run jbvbc with our output strebm
        int stbtus = com.sun.tools.jbvbc.Mbin.compile(
            jbvbcArgs.toArrby(new String[jbvbcArgs.size()]),
            new PrintWriter(out, true));
        return stbtus == 0;
    }

    /**
     * The dbtb for bn rmic complibtion bbtch: the processed commbnd
     * line brguments.
     **/
    privbte clbss Bbtch {
        boolebn keepGenerbted = fblse;  // -keep or -keepgenerbted
        boolebn debug = fblse;          // -g
        boolebn noWbrn = fblse;         // -nowbrn
        boolebn noWrite = fblse;        // -nowrite
        boolebn verbose = fblse;        // -verbose
        boolebn noCompile = fblse;      // -Xnocompile
        String bootClbssPbth = null;    // -bootclbsspbth
        String extDirs = null;          // -extdirs
        String clbssPbth = null;        // -clbsspbth
        File destDir = null;            // -d
        List<Generbtor> generbtors = new ArrbyList<Generbtor>();
        Clbss<? extends BbtchEnvironment> envClbss = BbtchEnvironment.clbss;
        List<String> clbsses = new ArrbyList<String>();

        Bbtch() { }

        /**
         * Returns the Mbin instbnce for this bbtch.
         **/
        Mbin enclosingMbin() {
            return Mbin.this;
        }
    }
}
