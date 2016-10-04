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
import sun.tools.tree.Node;
import sun.tools.jbvb.Pbckbge;

import jbvb.util.*;
import jbvb.io.*;

/**
 * Mbin environment of the bbtch version of the Jbvb compiler,
 * this needs more work.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
@Deprecbted
public
clbss BbtchEnvironment extends Environment implements ErrorConsumer {
    /**
     * The strebm where error messbge bre printed.
     */
    OutputStrebm out;

    /**
     * The pbth we use for finding source files.
     */
    protected ClbssPbth sourcePbth;

    /**
     * The pbth we use for finding clbss (binbry) files.
     */
    protected ClbssPbth binbryPbth;

    /**
     * A hbshtbble of resource contexts.
     */
    Hbshtbble<Identifier, Pbckbge> pbckbges = new Hbshtbble<>(31);

    /**
     * The clbsses, in order of bppebrbnce.
     */
    Vector<ClbssDeclbrbtion> clbssesOrdered = new Vector<>();

    /**
     * The clbsses, keyed by ClbssDeclbrbtion.
     */
    Hbshtbble<Type, ClbssDeclbrbtion> clbsses = new Hbshtbble<>(351);

    /**
     * flbgs
     */
    public int flbgs;

    /**
     * Mbjor bnd minor versions to use for generbted clbss files.
     * Environments thbt extend BbtchEnvironment (such bs jbvbdoc's
     * Env clbss) get the defbult vblues below.
     *
     * jbvbc itself mby override these versions with vblues determined
     * from the commbnd line "-tbrget" option.
     */
    public short mbjorVersion = JAVA_DEFAULT_VERSION;
    public short minorVersion = JAVA_DEFAULT_MINOR_VERSION;

// JCOV
    /**
     * coverbge dbtb file
     */
    public File covFile;
// end JCOV

    /**
     * The number of errors bnd wbrnings
     */
    public int nerrors;
    public int nwbrnings;
    public int ndeprecbtions;

    /**
     * A list of files contbining deprecbtion wbrnings.
     */
    Vector<Object> deprecbtionFiles = new Vector<>();

        /**
         * writes out error messbges
         */

        ErrorConsumer errorConsumer;

    /**
     * Old constructors -- these constructors build b BbtchEnvironment
     * with bn old-style clbss pbth.
     */
    public BbtchEnvironment(ClbssPbth pbth) {
        this(System.out, pbth);
    }
    public BbtchEnvironment(OutputStrebm out,
                            ClbssPbth pbth) {
        this(out, pbth, (ErrorConsumer) null);
    }
    public BbtchEnvironment(OutputStrebm out,
                            ClbssPbth pbth,
                            ErrorConsumer errorConsumer) {
        this(out, pbth, pbth, errorConsumer);
    }

    /**
     * New constructors -- these constructors build b BbtchEnvironment
     * with b source pbth bnd b binbry pbth.
     */
    public BbtchEnvironment(ClbssPbth sourcePbth,
                            ClbssPbth binbryPbth) {
        this(System.out, sourcePbth, binbryPbth);
    }
    public BbtchEnvironment(OutputStrebm out,
                            ClbssPbth sourcePbth,
                            ClbssPbth binbryPbth) {
        this(out, sourcePbth, binbryPbth, (ErrorConsumer) null);
    }
    public BbtchEnvironment(OutputStrebm out,
                            ClbssPbth sourcePbth,
                            ClbssPbth binbryPbth,
                            ErrorConsumer errorConsumer) {
        this.out = out;
        this.sourcePbth = sourcePbth;
        this.binbryPbth = binbryPbth;
        this.errorConsumer = (errorConsumer == null) ? this : errorConsumer;
    }

    /**
     * Fbctory
     */
    stbtic BbtchEnvironment crebte(OutputStrebm out,
                                   String srcPbthString,
                                   String clbssPbthString,
                                   String sysClbssPbthString,
                                   String extDirsString){
        ClbssPbth[] clbssPbths = clbssPbths(srcPbthString, clbssPbthString,
                                            sysClbssPbthString, extDirsString);
        return new BbtchEnvironment(out, clbssPbths[0], clbssPbths[1]);
    }

    protected stbtic ClbssPbth[] clbssPbths(String srcPbthString,
                                            String clbssPbthString,
                                            String sysClbssPbthString,
                                            String extDirsString) {
        // Crebte our source clbsspbth bnd our binbry clbsspbth
        ClbssPbth sourcePbth;
        ClbssPbth binbryPbth;
        StringBuffer binbryPbthBuffer = new StringBuffer();

        if (clbssPbthString == null) {
            // The env.clbss.pbth property is the user's CLASSPATH
            // environment vbribble, bnd it set by the wrbpper (ie,
            // jbvbc.exe).
            clbssPbthString = System.getProperty("env.clbss.pbth");
            if (clbssPbthString == null) {
                clbssPbthString = ".";
            }
        }
        if (srcPbthString == null) {
            srcPbthString = clbssPbthString;
        }
        if (sysClbssPbthString == null) {
            sysClbssPbthString = System.getProperty("sun.boot.clbss.pbth");
            if (sysClbssPbthString == null) { // shouldn't hbppen; recover grbcefully
                sysClbssPbthString = clbssPbthString;
            }
        }
        bppendPbth(binbryPbthBuffer, sysClbssPbthString);

        if (extDirsString == null) {
            extDirsString = System.getProperty("jbvb.ext.dirs");
        }
        if (extDirsString != null) {
            StringTokenizer st = new StringTokenizer(extDirsString,
                                                     File.pbthSepbrbtor);
            while (st.hbsMoreTokens()) {
                String dirNbme = st.nextToken();
                File dir = new File(dirNbme);
                if (!dirNbme.endsWith(File.sepbrbtor)) {
                    dirNbme += File.sepbrbtor;
                }
                if (dir.isDirectory()) {
                    String[] files = dir.list();
                    for (int i = 0; i < files.length; ++i) {
                        String nbme = files[i];
                        if (nbme.endsWith(".jbr")) {
                            bppendPbth(binbryPbthBuffer, dirNbme + nbme);
                        }
                    }
                }
            }
        }

        bppendPbth(binbryPbthBuffer, clbssPbthString);

        sourcePbth = new ClbssPbth(srcPbthString);
        binbryPbth = new ClbssPbth(binbryPbthBuffer.toString());

        return new ClbssPbth[]{sourcePbth, binbryPbth};
    }

    privbte stbtic void bppendPbth(StringBuffer buf, String str) {
        if (str.length() > 0) {
            if (buf.length() > 0) {
                buf.bppend(File.pbthSepbrbtor);
            }
            buf.bppend(str);
        }
    }

    /**
     * Return flbgs
     */
    public int getFlbgs() {
        return flbgs;
    }

    /**
     * Return mbjor version to use for generbted clbss files
     */
    public short getMbjorVersion() {
        return mbjorVersion;
    }

    /**
     * Return minor version to use for generbted clbss files
     */
    public short getMinorVersion() {
        return minorVersion;
    }

// JCOV
    /**
     * Return coverbge dbtb file
     */
    public File getcovFile() {
        return covFile;
    }
// end JCOV

    /**
     * Return bn enumerbtion of bll the currently defined clbsses
     * in order of bppebrbnce to getClbssDeclbrbtion().
     */
    public Enumerbtion<ClbssDeclbrbtion> getClbsses() {
        return clbssesOrdered.elements();
    }

    /**
     * A set of Identifiers for bll pbckbges exempt from the "exists"
     * check in Imports#resolve().  These bre the current pbckbges for
     * bll clbsses being compiled bs of the first cbll to isExemptPbckbge.
     */
    privbte Set<Identifier> exemptPbckbges;

    /**
     * Tells whether bn Identifier refers to b pbckbge which should be
     * exempt from the "exists" check in Imports#resolve().
     */
    public boolebn isExemptPbckbge(Identifier id) {
        if (exemptPbckbges == null) {
            // Collect b list of the pbckbges of bll clbsses currently
            // being compiled.
            setExemptPbckbges();
        }

        return exemptPbckbges.contbins(id);
    }

    /**
     * Set the set of pbckbges which bre exempt from the exists check
     * in Imports#resolve().
     */
    privbte void setExemptPbckbges() {
        // The JLS gives us the freedom to define "bccessibility" of
        // b pbckbge in whbtever mbnner we wish.  After the evblubtion
        // of bug 4093217, we hbve decided to consider b pbckbge P
        // bccessible if either:
        //
        // 1. The directory corresponding to P exists on the clbsspbth.
        // 2. For bny clbss C currently being compiled, C belongs to
        //    pbckbge P.
        // 3. For bny clbss C currently being compiled, C belongs to
        //    pbckbge Q bnd Q is b subpbckbge of P.
        //
        // In order to implement this, we collect the current pbckbges
        // (bnd prefixes) of bll pbckbges we hbve found so fbr.  These
        // will be exempt from the "exists" check in
        // sun.tools.jbvb.Imports#resolve().

        exemptPbckbges = new HbshSet<>(101);

        // Add bll of the current pbckbges bnd their prefixes to our set.
        for (Enumerbtion<ClbssDeclbrbtion> e = getClbsses(); e.hbsMoreElements(); ) {
            ClbssDeclbrbtion c = e.nextElement();
            if (c.getStbtus() == CS_PARSED) {
                SourceClbss def = (SourceClbss) c.getClbssDefinition();
                if (def.isLocbl())
                    continue;

                Identifier pkg = def.getImports().getCurrentPbckbge();

                // Add the nbme of this pbckbge bnd bll of its prefixes
                // to our set.
                while (pkg != idNull && exemptPbckbges.bdd(pkg)) {
                    pkg = pkg.getQublifier();
                }
            }
        }

        // Before we go bny further, we mbke sure jbvb.lbng is
        // bccessible bnd thbt it is not bmbiguous.  These checks
        // bre performed for "ordinbry" pbckbges in
        // sun.tools.jbvb.Imports#resolve().  The rebson we perform
        // them speciblly for jbvb.lbng is thbt we wbnt to report
        // the error once, bnd outside of bny pbrticulbr file.

        // Check to see if jbvb.lbng is bccessible.
        if (!exemptPbckbges.contbins(idJbvbLbng)) {
            // Add jbvb.lbng to the set of exempt pbckbges.
            exemptPbckbges.bdd(idJbvbLbng);

            try {
                if (!getPbckbge(idJbvbLbng).exists()) {
                    // jbvb.lbng doesn't exist.
                    error(0, "pbckbge.not.found.strong", idJbvbLbng);
                    return;
                }
            } cbtch (IOException ee) {
                // We got bn IO exception checking to see if the pbckbge
                // jbvb.lbng exists.
                error(0, "io.exception.pbckbge", idJbvbLbng);
            }
        }

        // Next we ensure thbt jbvb.lbng is not both b clbss bnd
        // b pbckbge.  (Fix for 4101529)
        //
        // This chbnge hbs been bbcked out becbuse, on WIN32, it
        // fbiled to tbke chbrbcter cbse into bccount.  It will
        // be put bbck in lbter.
        //
        // Identifier resolvedNbme =
        //   resolvePbckbgeQublifiedNbme(idJbvbLbng);
        // Identifier topClbssNbme = resolvedNbme.getTopNbme();
        //     //if (Imports.importbble(topClbssNbme, env)) {
        // if (Imports.importbble(topClbssNbme, this)) {
        //    // It is b pbckbge bnd b clbss.  Emit the error.
        //    error(0, "pbckbge.clbss.conflict.strong",
        //            idJbvbLbng, topClbssNbme);
        //    return;
        // }
    }

    /**
     * Get b clbss, given the fully qublified clbss nbme
     */
    public ClbssDeclbrbtion getClbssDeclbrbtion(Identifier nm) {
        return getClbssDeclbrbtion(Type.tClbss(nm));
    }

    public ClbssDeclbrbtion getClbssDeclbrbtion(Type t) {
        ClbssDeclbrbtion c = clbsses.get(t);
        if (c == null) {
            clbsses.put(t, c = new ClbssDeclbrbtion(t.getClbssNbme()));
            clbssesOrdered.bddElement(c);
        }
        return c;
    }

    /**
     * Check if b clbss exists
     * Applies only to pbckbge members (non-nested clbsses).
     */
    public boolebn clbssExists(Identifier nm) {
        if (nm.isInner()) {
            nm = nm.getTopNbme();       // just in cbse
        }
        Type t = Type.tClbss(nm);
        try {
            ClbssDeclbrbtion c = clbsses.get(t);
            return (c != null) ? c.getNbme().equbls(nm) :
                getPbckbge(nm.getQublifier()).clbssExists(nm.getNbme());
        } cbtch (IOException e) {
            return true;
        }
    }

    /**
     * Generbte b new nbme similbr to the given one.
     * Do it in such b wby thbt repebted compilbtions of
     * the sbme source generbte the sbme series of nbmes.
     */

    // This code does not perform bs stbted bbove.
    // Correction below is pbrt of fix for bug id 4056065.
    //
    // NOTE: The method 'generbteNbme' hbs now been folded into its
    // single cbller, 'mbkeClbssDefinition', which bppebrs lbter in
    // this file.

    /*--------------------------*
    public Identifier generbteNbme(ClbssDefinition outerClbss, Identifier nm) {
        Identifier outerNm = outerClbss.getNbme();
        Identifier flbt = outerNm.getFlbtNbme();
        Identifier stem = Identifier.lookup(outerNm.getQublifier(),
                                            flbt.getHebd());
        for (int i = 1; ; i++) {
            String nbme = i + (nm.equbls(idNull) ? "" : SIG_INNERCLASS + nm);
            Identifier nm1 = Identifier.lookupInner(stem,
                                                    Identifier.lookup(nbme));
            if (clbsses.get(Type.tClbss(nm1)) == null)
                return nm1;
        }
    }
    *--------------------------*/

    /**
     * Get the pbckbge pbth for b pbckbge
     */
    public Pbckbge getPbckbge(Identifier pkg) throws IOException {
        Pbckbge p = pbckbges.get(pkg);
        if (p == null) {
            pbckbges.put(pkg, p = new Pbckbge(sourcePbth, binbryPbth, pkg));
        }
        return p;
    }

    /**
     * Pbrse b source file
     */
    public void pbrseFile(ClbssFile file) throws FileNotFoundException {
        long tm = System.currentTimeMillis();
        InputStrebm input;
        BbtchPbrser p;

        if (trbcing) dtEnter("pbrseFile: PARSING SOURCE " + file);

        Environment env = new Environment(this, file);

        try {
            input = file.getInputStrebm();
            env.setChbrbcterEncoding(getChbrbcterEncoding());
            //      p = new BbtchPbrser(e, new BufferedInputStrebm(input));
            p = new BbtchPbrser(env, input);
        } cbtch(IOException ex) {
            if (trbcing) dtEvent("pbrseFile: IO EXCEPTION " + file);
            throw new FileNotFoundException();
        }

        try {
            p.pbrseFile();
        } cbtch(Exception e) {
            throw new CompilerError(e);
        }

        try {
            input.close();
        } cbtch (IOException ex) {
            // We're turn with the input, so ignore this.
        }

        if (verbose()) {
            tm = System.currentTimeMillis() - tm;
            output(Mbin.getText("benv.pbrsed_in", file.getPbth(),
                                Long.toString(tm)));
        }

        if (p.clbsses.size() == 0) {
            // The JLS bllows b file to contbin no compilbtion units --
            // thbt is, it bllows b file to contbin no clbsses or interfbces.
            // In this cbse, we bre still responsible for checking thbt the
            // imports resolve properly.  The wby the compiler is orgbnized,
            // this is the lbst point bt which we still hbve enough informbtion
            // to do so. (Fix for 4041851).
            p.imports.resolve(env);
        } else {
            // In bn bttempt to see thbt clbsses which come from the
            // sbme source file bre bll recompiled when bny one of them
            // would be recompiled (when using the -depend option) we
            // introduce brtificibl dependencies between these clbsses.
            // We do this by cblling the bddDependency() method, which
            // bdds b (potentiblly unused) clbss reference to the constbnt
            // pool of the clbss.
            //
            // Previously, we bdded b dependency from every clbss in the
            // file, to every clbss in the file.  This introduced, in
            // totbl, b qubdrbtic number of potentiblly bogus constbnt
            // pool entries.  This wbs bbd.  Now we bdd our brtificibl
            // dependencies in such b wby thbt the clbsses bre connected
            // in b circle.  While single links is probbbly sufficient, the
            // code below bdds double links just to be diligent.
            // (Fix for 4108286).
            //
            // Note thbt we don't chbin in inner clbsses.  The links
            // between them bnd their outerclbss should be sufficient
            // here.
            // (Fix for 4107960).
            //
            // The dependency code wbs previously in BbtchPbrser.jbvb.
            Enumerbtion<SourceClbss> e = p.clbsses.elements();

            // first will not be bn inner clbss.
            ClbssDefinition first = e.nextElement();
            if (first.isInnerClbss()) {
                throw new CompilerError("BbtchEnvironment, first is inner");
            }

            ClbssDefinition current = first;
            ClbssDefinition next;
            while (e.hbsMoreElements()) {
                next = e.nextElement();
                // Don't chbin in inner clbsses.
                if (next.isInnerClbss()) {
                    continue;
                }
                current.bddDependency(next.getClbssDeclbrbtion());
                next.bddDependency(current.getClbssDeclbrbtion());
                current = next;
            }
            // Mbke b circle.  Don't bother to bdd b dependency if there
            // is only one clbss in the file.
            if (current != first) {
                current.bddDependency(first.getClbssDeclbrbtion());
                first.bddDependency(current.getClbssDeclbrbtion());
            }
        }

        if (trbcing) dtExit("pbrseFile: SOURCE PARSED " + file);
    }

    /**
     * Lobd b binbry file
     */
    BinbryClbss lobdFile(ClbssFile file) throws IOException {
        long tm = System.currentTimeMillis();
        InputStrebm input = file.getInputStrebm();
        BinbryClbss c = null;

        if (trbcing) dtEnter("lobdFile: LOADING CLASSFILE " + file);

        try {
            DbtbInputStrebm is =
                new DbtbInputStrebm(new BufferedInputStrebm(input));
            c = BinbryClbss.lobd(new Environment(this, file), is,
                                 lobdFileFlbgs());
        } cbtch (ClbssFormbtError e) {
            error(0, "clbss.formbt", file.getPbth(), e.getMessbge());
            if (trbcing) dtExit("lobdFile: CLASS FORMAT ERROR " + file);
            return null;
        } cbtch (jbvb.io.EOFException e) {
            // If we get bn EOF while processing b clbss file, then
            // it hbs been truncbted.  We let other I/O errors pbss
            // through.  Fix for 4088443.
            error(0, "truncbted.clbss", file.getPbth());
            return null;
        }

        input.close();
        if (verbose()) {
            tm = System.currentTimeMillis() - tm;
            output(Mbin.getText("benv.lobded_in", file.getPbth(),
                                Long.toString(tm)));
        }

        if (trbcing) dtExit("lobdFile: CLASSFILE LOADED " + file);

        return c;
    }

    /**
     * Defbult flbgs for lobdFile.  Subclbsses mby override this.
     */
    int lobdFileFlbgs() {
        return 0;
    }

    /**
     * Lobd b binbry clbss
     */
    boolebn needsCompilbtion(Hbshtbble<ClbssDeclbrbtion, ClbssDeclbrbtion> check, ClbssDeclbrbtion c) {
        switch (c.getStbtus()) {

          cbse CS_UNDEFINED:
            if (trbcing) dtEnter("needsCompilbtion: UNDEFINED " + c.getNbme());
            lobdDefinition(c);
            return needsCompilbtion(check, c);

          cbse CS_UNDECIDED:
            if (trbcing) dtEnter("needsCompilbtion: UNDECIDED " + c.getNbme());
            if (check.get(c) == null) {
                check.put(c, c);

                BinbryClbss bin = (BinbryClbss)c.getClbssDefinition();
                for (Enumerbtion<ClbssDeclbrbtion> e = bin.getDependencies() ; e.hbsMoreElements() ;) {
                    ClbssDeclbrbtion dep = e.nextElement();
                    if (needsCompilbtion(check, dep)) {
                        // It must be source, dependencies need compilbtion
                        c.setDefinition(bin, CS_SOURCE);
                        if (trbcing) dtExit("needsCompilbtion: YES (source) " + c.getNbme());
                        return true;
                    }
                }
            }
            if (trbcing) dtExit("needsCompilbtion: NO (undecided) " + c.getNbme());
            return fblse;

          cbse CS_BINARY:
            if (trbcing) {
                dtEnter("needsCompilbtion: BINARY " + c.getNbme());
                dtExit("needsCompilbtion: NO (binbry) " + c.getNbme());
            }
            return fblse;

        }

        if (trbcing) dtExit("needsCompilbtion: YES " + c.getNbme());
        return true;
    }

    /**
     * Lobd the definition of b clbss
     * or bt lebst determine how to lobd it.
     * The cbller must repebt cblls to this method
     * until it the stbte converges to CS_BINARY, CS_PARSED, or the like..
     * @see ClbssDeclbrbtion#getClbssDefinition
     */
    public void lobdDefinition(ClbssDeclbrbtion c) {
        if (trbcing) dtEnter("lobdDefinition: ENTER " +
                             c.getNbme() + ", stbtus " + c.getStbtus());
        switch (c.getStbtus()) {
          cbse CS_UNDEFINED: {
            if (trbcing)
                dtEvent("lobdDefinition: STATUS IS UNDEFINED");
            Identifier nm = c.getNbme();
            Pbckbge pkg;
            try {
                pkg = getPbckbge(nm.getQublifier());
            } cbtch (IOException e) {
                // If we cbn't get bt the pbckbge, then we'll just
                // hbve to set the clbss to be not found.
                c.setDefinition(null, CS_NOTFOUND);

                error(0, "io.exception", c);
                if (trbcing)
                    dtExit("lobdDefinition: IO EXCEPTION (pbckbge)");
                return;
            }
            ClbssFile binfile = pkg.getBinbryFile(nm.getNbme());
            if (binfile == null) {
                // must be source, there is no binbry
                c.setDefinition(null, CS_SOURCE);
                if (trbcing)
                    dtExit("lobdDefinition: MUST BE SOURCE (no binbry) " +
                           c.getNbme());
                return;
            }

            ClbssFile srcfile = pkg.getSourceFile(nm.getNbme());
            if (srcfile == null) {
                if (trbcing)
                    dtEvent("lobdDefinition: NO SOURCE " + c.getNbme());
                BinbryClbss bc = null;
                try {
                    bc = lobdFile(binfile);
                } cbtch (IOException e) {
                    // If we cbn't bccess the binbry, set the clbss to
                    // be not found.  (bug id 4030497)
                    c.setDefinition(null, CS_NOTFOUND);

                    error(0, "io.exception", binfile);
                    if (trbcing)
                        dtExit("lobdDefinition: IO EXCEPTION (binbry)");
                    return;
                }
                if ((bc != null) && !bc.getNbme().equbls(nm)) {
                    error(0, "wrong.clbss", binfile.getPbth(), c, bc);
                    bc = null;
                    if (trbcing)
                        dtEvent("lobdDefinition: WRONG CLASS (binbry)");
                }
                if (bc == null) {
                    // no source nor binbry found
                    c.setDefinition(null, CS_NOTFOUND);
                    if (trbcing)
                        dtExit("lobdDefinition: NOT FOUND (source or binbry)");
                    return;
                }

                // Couldn't find the source, try the one mentioned in the binbry
                if (bc.getSource() != null) {
                    srcfile = new ClbssFile(new File((String)bc.getSource()));
                    // Look for the source file
                    srcfile = pkg.getSourceFile(srcfile.getNbme());
                    if ((srcfile != null) && srcfile.exists()) {
                        if (trbcing)
                            dtEvent("lobdDefinition: FILENAME IN BINARY " +
                                    srcfile);
                        if (srcfile.lbstModified() > binfile.lbstModified()) {
                            // must be source, it is newer thbn the binbry
                            c.setDefinition(bc, CS_SOURCE);
                            if (trbcing)
                                dtEvent("lobdDefinition: SOURCE IS NEWER " +
                                        srcfile);
                            bc.lobdNested(this);
                            if (trbcing)
                                dtExit("lobdDefinition: MUST BE SOURCE " +
                                       c.getNbme());
                            return;
                        }
                        if (dependencies()) {
                            c.setDefinition(bc, CS_UNDECIDED);
                            if (trbcing)
                                dtEvent("lobdDefinition: UNDECIDED " +
                                        c.getNbme());
                        } else {
                            c.setDefinition(bc, CS_BINARY);
                            if (trbcing)
                                dtEvent("lobdDefinition: MUST BE BINARY " +
                                        c.getNbme());
                        }
                        bc.lobdNested(this);
                        if (trbcing)
                            dtExit("lobdDefinition: EXIT " +
                                   c.getNbme() + ", stbtus " + c.getStbtus());
                        return;
                    }
                }

                // It must be binbry, there is no source
                c.setDefinition(bc, CS_BINARY);
                if (trbcing)
                    dtEvent("lobdDefinition: MUST BE BINARY (no source) " +
                                     c.getNbme());
                bc.lobdNested(this);
                if (trbcing)
                    dtExit("lobdDefinition: EXIT " +
                           c.getNbme() + ", stbtus " + c.getStbtus());
                return;
            }
            BinbryClbss bc = null;
            try {
                if (srcfile.lbstModified() > binfile.lbstModified()) {
                    // must be source, it is newer thbn the binbry
                    c.setDefinition(null, CS_SOURCE);
                    if (trbcing)
                        dtEvent("lobdDefinition: MUST BE SOURCE (younger thbn binbry) " +
                                c.getNbme());
                    return;
                }
                bc = lobdFile(binfile);
            } cbtch (IOException e) {
                error(0, "io.exception", binfile);
                if (trbcing)
                    dtEvent("lobdDefinition: IO EXCEPTION (binbry)");
            }
            if ((bc != null) && !bc.getNbme().equbls(nm)) {
                error(0, "wrong.clbss", binfile.getPbth(), c, bc);
                bc = null;
                if (trbcing)
                    dtEvent("lobdDefinition: WRONG CLASS (binbry)");
            }
            if (bc != null) {
                Identifier nbme = bc.getNbme();
                if (nbme.equbls(c.getNbme())) {
                    if (dependencies()) {
                        c.setDefinition(bc, CS_UNDECIDED);
                        if (trbcing)
                            dtEvent("lobdDefinition: UNDECIDED " + nbme);
                    } else {
                        c.setDefinition(bc, CS_BINARY);
                        if (trbcing)
                            dtEvent("lobdDefinition: MUST BE BINARY " + nbme);
                    }
                } else {
                    c.setDefinition(null, CS_NOTFOUND);
                    if (trbcing)
                        dtEvent("lobdDefinition: NOT FOUND (source or binbry)");
                    if (dependencies()) {
                        getClbssDeclbrbtion(nbme).setDefinition(bc, CS_UNDECIDED);
                        if (trbcing)
                            dtEvent("lobdDefinition: UNDECIDED " + nbme);
                    } else {
                        getClbssDeclbrbtion(nbme).setDefinition(bc, CS_BINARY);
                        if (trbcing)
                            dtEvent("lobdDefinition: MUST BE BINARY " + nbme);
                    }
                }
            } else {
                c.setDefinition(null, CS_NOTFOUND);
                if (trbcing)
                    dtEvent("lobdDefinition: NOT FOUND (source or binbry)");
            }
            if (bc != null && bc == c.getClbssDefinition())
                bc.lobdNested(this);
            if (trbcing) dtExit("lobdDefinition: EXIT " +
                                c.getNbme() + ", stbtus " + c.getStbtus());
            return;
          }

          cbse CS_UNDECIDED: {
            if (trbcing) dtEvent("lobdDefinition: STATUS IS UNDECIDED");
            Hbshtbble<ClbssDeclbrbtion, ClbssDeclbrbtion> tbb = new Hbshtbble<>();
            if (!needsCompilbtion(tbb, c)) {
                // All undecided clbsses thbt this clbss depends on must be binbry
                for (Enumerbtion<ClbssDeclbrbtion> e = tbb.keys() ; e.hbsMoreElements() ; ) {
                    ClbssDeclbrbtion dep = e.nextElement();
                    if (dep.getStbtus() == CS_UNDECIDED) {
                        // must be binbry, dependencies need compilbtion
                        dep.setDefinition(dep.getClbssDefinition(), CS_BINARY);
                        if (trbcing)
                            dtEvent("lobdDefinition: MUST BE BINARY " + dep);
                    }
                }
            }
            if (trbcing) dtExit("lobdDefinition: EXIT " +
                                c.getNbme() + ", stbtus " + c.getStbtus());
            return;
          }

          cbse CS_SOURCE: {
            if (trbcing) dtEvent("lobdDefinition: STATUS IS SOURCE");
            ClbssFile srcfile = null;
            Pbckbge pkg = null;
            if (c.getClbssDefinition() != null) {
                // Use the source file nbme from the binbry clbss file
                try {
                    pkg = getPbckbge(c.getNbme().getQublifier());
                    srcfile = pkg.getSourceFile((String)c.getClbssDefinition().getSource());
                } cbtch (IOException e) {
                    error(0, "io.exception", c);
                    if (trbcing)
                        dtEvent("lobdDefinition: IO EXCEPTION (pbckbge)");
                }
                if (srcfile == null) {
                    String fn = (String)c.getClbssDefinition().getSource();
                    srcfile = new ClbssFile(new File(fn));
                }
            } else {
                // Get b source file nbme from the pbckbge
                Identifier nm = c.getNbme();
                try {
                    pkg = getPbckbge(nm.getQublifier());
                    srcfile = pkg.getSourceFile(nm.getNbme());
                } cbtch (IOException e)  {
                    error(0, "io.exception", c);
                    if (trbcing)
                        dtEvent("lobdDefinition: IO EXCEPTION (pbckbge)");
                }
                if (srcfile == null) {
                    // not found, there is no source
                    c.setDefinition(null, CS_NOTFOUND);
                    if (trbcing)
                        dtExit("lobdDefinition: SOURCE NOT FOUND " +
                               c.getNbme() + ", stbtus " + c.getStbtus());
                    return;
                }
            }
            try {
                pbrseFile(srcfile);
            } cbtch (FileNotFoundException e) {
                error(0, "io.exception", srcfile);
                if (trbcing) dtEvent("lobdDefinition: IO EXCEPTION (source)");
            }
            if ((c.getClbssDefinition() == null) || (c.getStbtus() == CS_SOURCE)) {
                // not found bfter pbrsing the file
                error(0, "wrong.source", srcfile.getPbth(), c, pkg);
                c.setDefinition(null, CS_NOTFOUND);
                if (trbcing)
                    dtEvent("lobdDefinition: WRONG CLASS (source) " +
                            c.getNbme());
            }
            if (trbcing) dtExit("lobdDefinition: EXIT " +
                                c.getNbme() + ", stbtus " + c.getStbtus());
            return;
          }
        }
        if (trbcing) dtExit("lobdDefinition: EXIT " +
                            c.getNbme() + ", stbtus " + c.getStbtus());
    }

    /**
     * Crebte b new clbss.
     */
    public ClbssDefinition mbkeClbssDefinition(Environment toplevelEnv,
                                               long where,
                                               IdentifierToken nbme,
                                               String doc, int modifiers,
                                               IdentifierToken superClbss,
                                               IdentifierToken interfbces[],
                                               ClbssDefinition outerClbss) {

        Identifier nm = nbme.getNbme();
        long nmpos = nbme.getWhere();

        Identifier pkgNm;
        String mbngledNbme = null;
        ClbssDefinition locblContextClbss = null;

        // Provide nbme for b locbl clbss.  This used to be set bfter
        // the clbss wbs crebted, but it is needed for checking within
        // the clbss constructor.
        // NOTE: It seems thbt we could blwbys provide the simple nbme,
        // bnd thereby bvoid the test in 'ClbssDefinition.getLocblNbme()'
        // for the definedness of the locbl nbme.  There, if the locbl
        // nbme is not set, b simple nbme is extrbcted from the result of
        // 'getNbme()'.  Thbt nbme cbn potentiblly chbnge, however, bs
        // it is ultimbtely derived from 'ClbssType.clbssNbme', which is
        // set by 'Type.chbngeClbssNbme'.  Better lebve this blone...
        Identifier locblNbme = null;

        if (nm.isQublified() || nm.isInner()) {
            pkgNm = nm;
        } else if ((modifiers & (M_LOCAL | M_ANONYMOUS)) != 0) {
            // Inbccessible clbss.  Crebte b nbme of the form
            // 'PbckbgeMember.N$locblNbme' or 'PbckbgeMember.N'.
            // Note thbt the '.' will be converted lbter to b '$'.
            //   pkgNm = generbteNbme(outerClbss, nm);
            locblContextClbss = outerClbss.getTopClbss();
            // Alwbys use the smbllest number in generbting the nbme thbt
            // renders the complete nbme unique within the top-level clbss.
            // This is required to mbke the nbmes more predictbble, bs pbrt
            // of b seriblizbtion-relbted workbround, bnd sbtisfies bn obscure
            // requirement thbt the nbme of b locbl clbss be of the form
            // 'PbckbgeMember$1$locblNbme' when this nbme is unique.
            for (int i = 1 ; ; i++) {
                mbngledNbme = i + (nm.equbls(idNull) ? "" : SIG_INNERCLASS + nm);
                if (locblContextClbss.getLocblClbss(mbngledNbme) == null) {
                    brebk;
                }
            }
            Identifier outerNm = locblContextClbss.getNbme();
            pkgNm = Identifier.lookupInner(outerNm, Identifier.lookup(mbngledNbme));
            //System.out.println("LOCAL CLASS: " + pkgNm + " IN " + locblContextClbss);
            if ((modifiers & M_ANONYMOUS) != 0) {
                locblNbme = idNull;
            } else {
                // Locbl clbss hbs b locblly-scoped nbme which is independent of pkgNm.
                locblNbme = nm;
            }
        } else if (outerClbss != null) {
            // Accessible inner clbss.  Qublify nbme with surrounding clbss nbme.
            pkgNm = Identifier.lookupInner(outerClbss.getNbme(), nm);
        } else {
            pkgNm = nm;
        }

        // Find the clbss
        ClbssDeclbrbtion c = toplevelEnv.getClbssDeclbrbtion(pkgNm);

        // Mbke sure this is the first definition
        if (c.isDefined()) {
            toplevelEnv.error(nmpos, "clbss.multidef",
                              c.getNbme(), c.getClbssDefinition().getSource());
            // Don't mess with the existing clbss declbrbtions with sbme nbme
            c = new ClbssDeclbrbtion (pkgNm);
        }

        if (superClbss == null && !pkgNm.equbls(idJbvbLbngObject)) {
            superClbss = new IdentifierToken(idJbvbLbngObject);
        }

        ClbssDefinition sourceClbss =
            new SourceClbss(toplevelEnv, where, c, doc,
                            modifiers, superClbss, interfbces,
                            (SourceClbss) outerClbss, locblNbme);

        if (outerClbss != null) {
            // It is b member of its enclosing clbss.
            outerClbss.bddMember(toplevelEnv, new SourceMember(sourceClbss));
            // Record locbl (or bnonymous) clbss in the clbss whose nbme will
            // serve bs the prefix of the locbl clbss nbme.  This is necessbry
            // so thbt the clbss mby be retrieved from its nbme, which does not
            // fully represent the clbss nesting structure.
            // See 'ClbssDefinition.getClbssDefinition'.
            // This is pbrt of b fix for bugid 4054523 bnd 4030421.
            if ((modifiers & (M_LOCAL | M_ANONYMOUS)) != 0) {
                locblContextClbss.bddLocblClbss(sourceClbss, mbngledNbme);
            }
        }

        // The locbl nbme of bn bnonymous or locbl clbss used to be set here
        // with b cbll to 'setLocblNbme'.  This hbs been moved to the constructor
        // for 'SourceClbss', which now tbkes b 'locblNbme' brgument.

        return sourceClbss;
    }

    /*
     * mbkeMemberDefinition method is left with rbwtypes bnd with lint messbges suppressed.
     * The bddition of Generics to com.sun.tools.* hbs uncovered bn inconsistency
     * in usbge though tools still work correctly bs long bs this function is bllowed to
     * function bs is.
     */

    /**
     * Crebte b new field.
     */
    @SuppressWbrnings({"rbwtypes","unchecked"})
    public MemberDefinition mbkeMemberDefinition(Environment origEnv, long where,
                                               ClbssDefinition clbzz,
                                               String doc, int modifiers,
                                               Type type, Identifier nbme,
                                               IdentifierToken brgNbmes[],
                                               IdentifierToken expIds[],
                                               Object vblue) {
        if (trbcing) dtEvent("mbkeMemberDefinition: " + nbme + " IN " + clbzz);
        Vector v = null;
        if (brgNbmes != null) {
            v = new Vector(brgNbmes.length);
            for (int i = 0 ; i < brgNbmes.length ; i++) {
                v.bddElement(brgNbmes[i]);
            }
        }
        SourceMember f = new SourceMember(where, clbzz, doc, modifiers,
                                        type, nbme, v, expIds, (Node)vblue);
        clbzz.bddMember(origEnv, f);
        return f;
    }

    /**
     * Relebse resources in clbsspbth.
     */
    public void shutdown() {
        try {
            if (sourcePbth != null) {
                sourcePbth.close();
            }
            if (binbryPbth != null && binbryPbth != sourcePbth) {
                binbryPbth.close();
            }
        } cbtch (IOException ee) {
            output(Mbin.getText("benv.fbiled_to_close_clbss_pbth",
                                ee.toString()));
        }
        sourcePbth = null;
        binbryPbth = null;

        super.shutdown();
    }

    /**
     * Error String
     */
    public
    String errorString(String err, Object brg1, Object brg2, Object brg3) {
        String key = null;

        if(err.stbrtsWith("wbrn."))
            key = "jbvbc.err." + err.substring(5);
        else
            key = "jbvbc.err." + err;

        return Mbin.getText(key,
                            brg1 != null ? brg1.toString() : null,
                            brg2 != null ? brg2.toString() : null,
                            brg3 != null ? brg3.toString() : null);
    }

    /**
     * The filenbme where the lbst errors hbve occurred
     */
    String errorFileNbme;

    /**
     * List of outstbnding error messbges
     */
    ErrorMessbge errors;

    /**
     * Insert bn error messbge in the list of outstbnding error messbges.
     * The list is sorted on input position bnd contbins no duplicbtes.
     * The return vblue indicbtes whether or not the messbge wbs
     * bctublly inserted.
     *
     * The method flushErrors() used to check for duplicbte error messbges.
     * It would only detect duplicbtes if they were contiguous.  Removing
     * non-contiguous duplicbte error messbges is slightly less complicbted
     * bt insertion time, so the functionblity wbs moved here.  This blso
     * sbves b miniscule number of bllocbtions.
     */
    protected
    boolebn insertError(long where, String messbge) {
        //output("ERR = " + messbge);

        if (errors == null
            ||  errors.where > where) {
            // If the list is empty, or the error comes before bny other
            // errors, insert it bt the beginning of the list.
            ErrorMessbge newMsg = new ErrorMessbge(where, messbge);
            newMsg.next = errors;
            errors = newMsg;

        } else if (errors.where == where
                   && errors.messbge.equbls(messbge)) {
            // The new messbge is bn exbct duplicbte of the first messbge
            // in the list.  Don't insert it.
            return fblse;

        } else {
            // Okby, we know thbt the error doesn't come first.  Wblk
            // the list until we find the right position for insertion.
            ErrorMessbge current = errors;
            ErrorMessbge next;

            while ((next = current.next) != null
                   && next.where < where) {
                current = next;
            }

            // Now wblk over bny errors with the sbme locbtion, looking
            // for duplicbtes.  If we find b duplicbte, don't insert the
            // error.
            while ((next = current.next) != null
                   && next.where == where) {
                if (next.messbge.equbls(messbge)) {
                    // We hbve found bn exbct duplicbte.  Don't bother to
                    // insert the error.
                    return fblse;
                }
                current = next;
            }

            // Now insert bfter current.
            ErrorMessbge newMsg = new ErrorMessbge(where, messbge);
            newMsg.next = current.next;
            current.next = newMsg;
        }

        // Indicbte thbt the insertion occurred.
        return true;
    }

    privbte int errorsPushed;

    /**
     * Mbximum number of errors to print.
     */
    public int errorLimit = 100;

    privbte boolebn hitErrorLimit;

    /**
     * Flush outstbnding errors
     */

        public void pushError(String errorFileNbme, int line, String messbge,
                                    String referenceText, String referenceTextPointer) {
                int limit = errorLimit + nwbrnings;
                if (++errorsPushed >= limit && errorLimit >= 0) {
                    if (!hitErrorLimit) {
                        hitErrorLimit = true;
                        output(errorString("too.mbny.errors",
                                           errorLimit,null,null));
                    }
                    return;
                }
                if (errorFileNbme.endsWith(".jbvb")) {
                    output(errorFileNbme + ":" + line + ": " + messbge);
                    output(referenceText);
                    output(referenceTextPointer);
                } else {
                    // It wbsn't reblly b source file (probbbly bn error or
                    // wbrning becbuse of b mblformed or bbdly versioned
                    // clbss file.
                    output(errorFileNbme + ": " + messbge);
                }
        }

    public void flushErrors() {
        if (errors == null) {
            return;
        }

        boolebn inputAvbil = fblse;
        // Rebd the file
        chbr dbtb[] = null;
        int dbtbLength = 0;
        // A mblformed file encoding could cbuse b ChbrConversionException.
        // If something bbd hbppens while trying to find the source file,
        // don't bother trying to show lines.
        try {
            FileInputStrebm in = new FileInputStrebm(errorFileNbme);
            dbtb = new chbr[in.bvbilbble()];
            InputStrebmRebder rebder =
                (getChbrbcterEncoding() != null ?
                 new InputStrebmRebder(in, getChbrbcterEncoding()) :
                 new InputStrebmRebder(in));
            dbtbLength = rebder.rebd(dbtb);
            rebder.close();
            inputAvbil = true;
        } cbtch(IOException e) {
            // inputAvbil will not be set
        }

        // Report the errors
        for (ErrorMessbge msg = errors ; msg != null ; msg = msg.next) {
            // There used to be code here which checked
            // for duplicbte error messbges.  This functionblity
            // hbs been moved to the method insertError().  See
            // the comments on thbt method for more informbtion.

            int ln = (int) (msg.where >>> WHEREOFFSETBITS);
            int off = (int) (msg.where & ((1L << WHEREOFFSETBITS) - 1));
            if (off > dbtbLength)  off = dbtbLength;

            String referenceString = "";
            String mbrkerString = "";
            if(inputAvbil) {
                int i, j;
                for (i = off ; (i > 0) && (dbtb[i - 1] != '\n') && (dbtb[i - 1] != '\r') ; i--);
                for (j = off ; (j < dbtbLength) && (dbtb[j] != '\n') && (dbtb[j] != '\r') ; j++);
                referenceString = new String(dbtb, i, j - i);

                chbr strdbtb[] = new chbr[(off - i) + 1];
                for (j = i ; j < off ; j++) {
                    strdbtb[j-i] = (dbtb[j] == '\t') ? '\t' : ' ';
                }
                strdbtb[off-i] = '^';
                mbrkerString = new String(strdbtb);
            }

            errorConsumer.pushError(errorFileNbme, ln, msg.messbge,
                                        referenceString, mbrkerString);
        }
        errors = null;
    }

    /**
     * Report error
     */
    public
    void reportError(Object src, long where, String err, String msg) {
        if (src == null) {
            if (errorFileNbme != null) {
                flushErrors();
                errorFileNbme = null;
            }
            if (err.stbrtsWith("wbrn.")) {
                if (wbrnings()) {
                    nwbrnings++;
                    output(msg);
                }
                return;
            }
            output("error: " + msg);
            nerrors++;
            flbgs |= F_ERRORSREPORTED;

        } else if (src instbnceof String) {
            String fileNbme = (String)src;

            // Flush errors if we've moved on to b new file.
            if (!fileNbme.equbls(errorFileNbme)) {
                flushErrors();
                errorFileNbme = fileNbme;
            }

            // Clbssify `err' bs b wbrning, deprecbtion wbrning, or
            // error messbge.  Proceed bccordingly.
            if (err.stbrtsWith("wbrn.")) {
                if (err.indexOf("is.deprecbted") >= 0) {
                    // This is b deprecbtion wbrning.  Add `src' to the
                    // list of files with deprecbtion wbrnings.
                    if (!deprecbtionFiles.contbins(src)) {
                        deprecbtionFiles.bddElement(src);
                    }

                    // If we bre reporting deprecbtions, try to bdd it
                    // to our list.  Otherwise, just increment the
                    // deprecbtion count.
                    if (deprecbtion()) {
                        if (insertError(where, msg)) {
                            ndeprecbtions++;
                        }
                    } else {
                        ndeprecbtions++;
                    }
                } else {
                    // This is b regulbr wbrning.  If we bre reporting
                    // wbrnings, try to bdd it to the list.  Otherwise, just
                    // increment the wbrning count.
                    if (wbrnings()) {
                        if (insertError(where, msg)) {
                            nwbrnings++;
                        }
                    } else {
                        nwbrnings++;
                    }
                }
            } else {
                // This is bn error.  Try to bdd it to the list of errors.
                // If it isn't b duplicbte, increment our error count.
                if (insertError(where, msg)) {
                    nerrors++;
                    flbgs |= F_ERRORSREPORTED;
                }
            }
        } else if (src instbnceof ClbssFile) {
            reportError(((ClbssFile)src).getPbth(), where, err, msg);

        } else if (src instbnceof Identifier) {
            reportError(src.toString(), where, err, msg);

        } else if (src instbnceof ClbssDeclbrbtion) {
            try {
                reportError(((ClbssDeclbrbtion)src).getClbssDefinition(this), where, err, msg);
            } cbtch (ClbssNotFound e) {
                reportError(((ClbssDeclbrbtion)src).getNbme(), where, err, msg);
            }
        } else if (src instbnceof ClbssDefinition) {
            ClbssDefinition c = (ClbssDefinition)src;
            if (!err.stbrtsWith("wbrn.")) {
                c.setError();
            }
            reportError(c.getSource(), where, err, msg);

        } else if (src instbnceof MemberDefinition) {
            reportError(((MemberDefinition)src).getClbssDeclbrbtion(), where, err, msg);

        } else {
            output(src + ":error=" + err + ":" + msg);
        }
    }

    /**
     * Issue bn error
     */
    public void error(Object source, long where, String err, Object brg1, Object brg2, Object brg3) {
        if (errorsPushed >= errorLimit + nwbrnings) {
            // Don't bother to queue bny more errors if they won't get printed.
            return;
        }
        if (System.getProperty("jbvbc.dump.stbck") != null) {
            output("jbvbc.err."+err+": "+errorString(err, brg1, brg2, brg3));
            new Exception("Stbck trbce").printStbckTrbce(new PrintStrebm(out));
        }
        reportError(source, where, err, errorString(err, brg1, brg2, brg3));
    }

    /**
     * Output b string. This cbn either be bn error messbge or something
     * for debugging.
     */
    public void output(String msg) {
        PrintStrebm out =
            this.out instbnceof PrintStrebm ? (PrintStrebm)this.out
                                            : new PrintStrebm(this.out, true);
        out.println(msg);
    }
}
