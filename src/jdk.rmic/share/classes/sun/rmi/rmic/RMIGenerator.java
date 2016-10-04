/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*****************************************************************************/
/*                    Copyright (c) IBM Corporbtion 1998                     */
/*                                                                           */
/* (C) Copyright IBM Corp. 1998                                              */
/*                                                                           */
/*****************************************************************************/

pbckbge sun.rmi.rmic;

import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.IOException;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import sun.tools.jbvb.Type;
import sun.tools.jbvb.Identifier;
import sun.tools.jbvb.ClbssDefinition;
import sun.tools.jbvb.ClbssDeclbrbtion;
import sun.tools.jbvb.ClbssNotFound;
import sun.tools.jbvb.ClbssFile;
import sun.tools.jbvb.MemberDefinition;
import com.sun.corbb.se.impl.util.Utility;

/**
 * A Generbtor object will generbte the Jbvb source code of the stub
 * bnd skeleton clbsses for bn RMI remote implementbtion clbss, using
 * b pbrticulbr stub protocol version.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Peter Jones,  Brybn Atsbtt
 */
public clbss RMIGenerbtor implements RMIConstbnts, Generbtor {

    privbte stbtic finbl Hbshtbble<String, Integer> versionOptions = new Hbshtbble<>();
    stbtic {
        versionOptions.put("-v1.1", STUB_VERSION_1_1);
        versionOptions.put("-vcompbt", STUB_VERSION_FAT);
        versionOptions.put("-v1.2", STUB_VERSION_1_2);
    }

    /**
     * Defbult constructor for Mbin to use.
     */
    public RMIGenerbtor() {
        version = STUB_VERSION_1_2;     // defbult is -v1.2 (see 4638155)
    }

    /**
     * Exbmine bnd consume commbnd line brguments.
     * @pbrbm brgv The commbnd line brguments. Ignore null
     * bnd unknown brguments. Set ebch consumed brgument to null.
     * @pbrbm error Report bny errors using the mbin.error() methods.
     * @return true if no errors, fblse otherwise.
     */
    public boolebn pbrseArgs(String brgv[], Mbin mbin) {
        String explicitVersion = null;
        for (int i = 0; i < brgv.length; i++) {
            if (brgv[i] != null) {
                String brg = brgv[i].toLowerCbse();
                if (versionOptions.contbinsKey(brg)) {
                    if (explicitVersion != null &&
                        !explicitVersion.equbls(brg))
                    {
                        mbin.error("rmic.cbnnot.use.both",
                                   explicitVersion, brg);
                        return fblse;
                    }
                    explicitVersion = brg;
                    version = versionOptions.get(brg);
                    brgv[i] = null;
                }
            }
        }
        return true;
    }

    /**
     * Generbte the source files for the stub bnd/or skeleton clbsses
     * needed by RMI for the given remote implementbtion clbss.
     *
     * @pbrbm env       compiler environment
     * @pbrbm cdef      definition of remote implementbtion clbss
     *                  to generbte stubs bnd/or skeletons for
     * @pbrbm destDir   directory for the root of the pbckbge hierbrchy
     *                  for generbted files
     */
    public void generbte(BbtchEnvironment env, ClbssDefinition cdef, File destDir) {
        RemoteClbss remoteClbss = RemoteClbss.forClbss(env, cdef);
        if (remoteClbss == null)        // exit if bn error occurred
            return;

        RMIGenerbtor gen;
        try {
            gen = new RMIGenerbtor(env, cdef, destDir, remoteClbss, version);
        } cbtch (ClbssNotFound e) {
            env.error(0, "rmic.clbss.not.found", e.nbme);
            return;
        }
        gen.generbte();
    }

    privbte void generbte() {
        env.bddGenerbtedFile(stubFile);

        try {
            IndentingWriter out = new IndentingWriter(
                new OutputStrebmWriter(new FileOutputStrebm(stubFile)));
            writeStub(out);
            out.close();
            if (env.verbose()) {
                env.output(Mbin.getText("rmic.wrote", stubFile.getPbth()));
            }
            env.pbrseFile(new ClbssFile(stubFile));
        } cbtch (IOException e) {
            env.error(0, "cbnt.write", stubFile.toString());
            return;
        }

        if (version == STUB_VERSION_1_1 ||
            version == STUB_VERSION_FAT)
        {
            env.bddGenerbtedFile(skeletonFile);

            try {
                IndentingWriter out = new IndentingWriter(
                    new OutputStrebmWriter(
                        new FileOutputStrebm(skeletonFile)));
                writeSkeleton(out);
                out.close();
                if (env.verbose()) {
                    env.output(Mbin.getText("rmic.wrote",
                        skeletonFile.getPbth()));
                }
                env.pbrseFile(new ClbssFile(skeletonFile));
            } cbtch (IOException e) {
                env.error(0, "cbnt.write", stubFile.toString());
                return;
            }
        } else {
            /*
             * For bugid 4135136: if skeleton files bre not being generbted
             * for this compilbtion run, delete old skeleton source or clbss
             * files for this remote implementbtion clbss thbt were
             * (presumbbly) left over from previous runs, to bvoid user
             * confusion from extrbneous or inconsistent generbted files.
             */

            File outputDir = Util.getOutputDirectoryFor(remoteClbssNbme,destDir,env);
            File skeletonClbssFile = new File(outputDir,skeletonClbssNbme.getNbme().toString() + ".clbss");

            skeletonFile.delete();      // ignore fbilures (no big debl)
            skeletonClbssFile.delete();
        }
    }

    /**
     * Return the File object thbt should be used bs the source file
     * for the given Jbvb clbss, using the supplied destinbtion
     * directory for the top of the pbckbge hierbrchy.
     */
    protected stbtic File sourceFileForClbss(Identifier clbssNbme,
                                             Identifier outputClbssNbme,
                                             File destDir,
                                             BbtchEnvironment env)
    {
        File pbckbgeDir = Util.getOutputDirectoryFor(clbssNbme,destDir,env);
        String outputNbme = Nbmes.mbngleClbss(outputClbssNbme).getNbme().toString();

        // Is there bny existing _Tie equivblent leftover from b
        // previous invocbtion of rmic -iiop? Only do this once per
        // clbss by looking for skeleton generbtion...

        if (outputNbme.endsWith("_Skel")) {
            String clbssNbmeStr = clbssNbme.getNbme().toString();
            File temp = new File(pbckbgeDir, Utility.tieNbme(clbssNbmeStr) + ".clbss");
            if (temp.exists()) {

                // Found b tie. Is IIOP generbtion blso being done?

                if (!env.getMbin().iiopGenerbtion) {

                    // No, so write b wbrning...

                    env.error(0,"wbrn.rmic.tie.found",
                              clbssNbmeStr,
                              temp.getAbsolutePbth());
                }
            }
        }

        String outputFileNbme = outputNbme + ".jbvb";
        return new File(pbckbgeDir, outputFileNbme);
    }


    /** rmic environment for this object */
    privbte BbtchEnvironment env;

    /** the remote clbss thbt this instbnce is generbting code for */
    privbte RemoteClbss remoteClbss;

    /** version of the stub protocol to use in code generbtion */
    privbte int version;

    /** remote methods for remote clbss, indexed by operbtion number */
    privbte RemoteClbss.Method[] remoteMethods;

    /**
     * Nbmes for the remote clbss bnd the stub bnd skeleton clbsses
     * to be generbted for it.
     */
    privbte Identifier remoteClbssNbme;
    privbte Identifier stubClbssNbme;
    privbte Identifier skeletonClbssNbme;

    privbte ClbssDefinition cdef;
    privbte File destDir;
    privbte File stubFile;
    privbte File skeletonFile;

    /**
     * Nbmes to use for the jbvb.lbng.reflect.Method stbtic fields
     * corresponding to ebch remote method.
     */
    privbte String[] methodFieldNbmes;

    /** cbched definition for certbin exception clbsses in this environment */
    privbte ClbssDefinition defException;
    privbte ClbssDefinition defRemoteException;
    privbte ClbssDefinition defRuntimeException;

    /**
     * Crebte b new stub/skeleton Generbtor object for the given
     * remote implementbtion clbss to generbte code bccording to
     * the given stub protocol version.
     */
    privbte RMIGenerbtor(BbtchEnvironment env, ClbssDefinition cdef,
                           File destDir, RemoteClbss remoteClbss, int version)
        throws ClbssNotFound
    {
        this.destDir     = destDir;
        this.cdef        = cdef;
        this.env         = env;
        this.remoteClbss = remoteClbss;
        this.version     = version;

        remoteMethods = remoteClbss.getRemoteMethods();

        remoteClbssNbme = remoteClbss.getNbme();
        stubClbssNbme = Nbmes.stubFor(remoteClbssNbme);
        skeletonClbssNbme = Nbmes.skeletonFor(remoteClbssNbme);

        methodFieldNbmes = nbmeMethodFields(remoteMethods);

        stubFile = sourceFileForClbss(remoteClbssNbme,stubClbssNbme, destDir , env);
        skeletonFile = sourceFileForClbss(remoteClbssNbme,skeletonClbssNbme, destDir, env);

        /*
         * Initiblize cbched definitions for exception clbsses used
         * in the generbtion process.
         */
        defException =
            env.getClbssDeclbrbtion(idJbvbLbngException).
                getClbssDefinition(env);
        defRemoteException =
            env.getClbssDeclbrbtion(idRemoteException).
                getClbssDefinition(env);
        defRuntimeException =
            env.getClbssDeclbrbtion(idJbvbLbngRuntimeException).
                getClbssDefinition(env);
    }

    /**
     * Write the stub for the remote clbss to b strebm.
     */
    privbte void writeStub(IndentingWriter p) throws IOException {

        /*
         * Write boiler plbte comment.
         */
        p.pln("// Stub clbss generbted by rmic, do not edit.");
        p.pln("// Contents subject to chbnge without notice.");
        p.pln();

        /*
         * If remote implementbtion clbss wbs in b pbrticulbr pbckbge,
         * declbre the stub clbss to be in the sbme pbckbge.
         */
        if (remoteClbssNbme.isQublified()) {
            p.pln("pbckbge " + remoteClbssNbme.getQublifier() + ";");
            p.pln();
        }

        /*
         * Declbre the stub clbss; implement bll remote interfbces.
         */
        p.plnI("public finbl clbss " +
            Nbmes.mbngleClbss(stubClbssNbme.getNbme()));
        p.pln("extends " + idRemoteStub);
        ClbssDefinition[] remoteInterfbces = remoteClbss.getRemoteInterfbces();
        if (remoteInterfbces.length > 0) {
            p.p("implements ");
            for (int i = 0; i < remoteInterfbces.length; i++) {
                if (i > 0)
                    p.p(", ");
                p.p(remoteInterfbces[i].getNbme().toString());
            }
            p.pln();
        }
        p.pOlnI("{");

        if (version == STUB_VERSION_1_1 ||
            version == STUB_VERSION_FAT)
        {
            writeOperbtionsArrby(p);
            p.pln();
            writeInterfbceHbsh(p);
            p.pln();
        }

        if (version == STUB_VERSION_FAT ||
            version == STUB_VERSION_1_2)
        {
            p.pln("privbte stbtic finbl long seriblVersionUID = " +
                STUB_SERIAL_VERSION_UID + ";");
            p.pln();

            /*
             * We only need to declbre bnd initiblize the stbtic fields of
             * Method objects for ebch remote method if there bre bny remote
             * methods; otherwise, skip this code entirely, to bvoid generbting
             * b try/cbtch block for b checked exception thbt cbnnot occur
             * (see bugid 4125181).
             */
            if (methodFieldNbmes.length > 0) {
                if (version == STUB_VERSION_FAT) {
                    p.pln("privbte stbtic boolebn useNewInvoke;");
                }
                writeMethodFieldDeclbrbtions(p);
                p.pln();

                /*
                 * Initiblize jbvb.lbng.reflect.Method fields for ebch remote
                 * method in b stbtic initiblizer.
                 */
                p.plnI("stbtic {");
                p.plnI("try {");
                if (version == STUB_VERSION_FAT) {
                    /*
                     * Fbt stubs must determine whether the API required for
                     * the JDK 1.2 stub protocol is supported in the current
                     * runtime, so thbt it cbn use it if supported.  This is
                     * determined by using the Reflection API to test if the
                     * new invoke method on RemoteRef exists, bnd setting the
                     * stbtic boolebn "useNewInvoke" to true if it does, or
                     * to fblse if b NoSuchMethodException is thrown.
                     */
                    p.plnI(idRemoteRef + ".clbss.getMethod(\"invoke\",");
                    p.plnI("new jbvb.lbng.Clbss[] {");
                    p.pln(idRemote + ".clbss,");
                    p.pln("jbvb.lbng.reflect.Method.clbss,");
                    p.pln("jbvb.lbng.Object[].clbss,");
                    p.pln("long.clbss");
                    p.pOln("});");
                    p.pO();
                    p.pln("useNewInvoke = true;");
                }
                writeMethodFieldInitiblizers(p);
                p.pOlnI("} cbtch (jbvb.lbng.NoSuchMethodException e) {");
                if (version == STUB_VERSION_FAT) {
                    p.pln("useNewInvoke = fblse;");
                } else {
                    /*
                     * REMIND: By throwing bn Error here, the bpplicbtion will
                     * get the NoSuchMethodError directly when the stub clbss
                     * is initiblized.  If we throw b RuntimeException
                     * instebd, the bpplicbtion would get bn
                     * ExceptionInInitiblizerError.  Would thbt be more
                     * bppropribte, bnd if so, which RuntimeException should
                     * be thrown?
                     */
                    p.plnI("throw new jbvb.lbng.NoSuchMethodError(");
                    p.pln("\"stub clbss initiblizbtion fbiled\");");
                    p.pO();
                }
                p.pOln("}");            // end try/cbtch block
                p.pOln("}");            // end stbtic initiblizer
                p.pln();
            }
        }

        writeStubConstructors(p);
        p.pln();

        /*
         * Write ebch stub method.
         */
        if (remoteMethods.length > 0) {
            p.pln("// methods from remote interfbces");
            for (int i = 0; i < remoteMethods.length; ++i) {
                p.pln();
                writeStubMethod(p, i);
            }
        }

        p.pOln("}");                    // end stub clbss
    }

    /**
     * Write the constructors for the stub clbss.
     */
    privbte void writeStubConstructors(IndentingWriter p)
        throws IOException
    {
        p.pln("// constructors");

        /*
         * Only stubs compbtible with the JDK 1.1 stub protocol need
         * b no-brg constructor; lbter versions use reflection to find
         * the constructor thbt directly tbkes b RemoteRef brgument.
         */
        if (version == STUB_VERSION_1_1 ||
            version == STUB_VERSION_FAT)
        {
            p.plnI("public " + Nbmes.mbngleClbss(stubClbssNbme.getNbme()) +
                "() {");
            p.pln("super();");
            p.pOln("}");
        }

        p.plnI("public " + Nbmes.mbngleClbss(stubClbssNbme.getNbme()) +
            "(" + idRemoteRef + " ref) {");
        p.pln("super(ref);");
        p.pOln("}");
    }

    /**
     * Write the stub method for the remote method with the given "opnum".
     */
    privbte void writeStubMethod(IndentingWriter p, int opnum)
        throws IOException
    {
        RemoteClbss.Method method = remoteMethods[opnum];
        Identifier methodNbme = method.getNbme();
        Type methodType = method.getType();
        Type pbrbmTypes[] = methodType.getArgumentTypes();
        String pbrbmNbmes[] = nbmePbrbmeters(pbrbmTypes);
        Type returnType = methodType.getReturnType();
        ClbssDeclbrbtion[] exceptions = method.getExceptions();

        /*
         * Declbre stub method; throw exceptions declbred in remote
         * interfbce(s).
         */
        p.pln("// implementbtion of " +
            methodType.typeString(methodNbme.toString(), true, fblse));
        p.p("public " + returnType + " " + methodNbme + "(");
        for (int i = 0; i < pbrbmTypes.length; i++) {
            if (i > 0)
                p.p(", ");
            p.p(pbrbmTypes[i] + " " + pbrbmNbmes[i]);
        }
        p.plnI(")");
        if (exceptions.length > 0) {
            p.p("throws ");
            for (int i = 0; i < exceptions.length; i++) {
                if (i > 0)
                    p.p(", ");
                p.p(exceptions[i].getNbme().toString());
            }
            p.pln();
        }
        p.pOlnI("{");

        /*
         * The RemoteRef.invoke methods throw Exception, but unless this
         * stub method throws Exception bs well, we must cbtch Exceptions
         * thrown from the invocbtion.  So we must cbtch Exception bnd
         * rethrow something we cbn throw: UnexpectedException, which is b
         * subclbss of RemoteException.  But for bny subclbsses of Exception
         * thbt we cbn throw, like RemoteException, RuntimeException, bnd
         * bny of the exceptions declbred by this stub method, we wbnt them
         * to pbss through unhbrmed, so first we must cbtch bny such
         * exceptions bnd rethrow it directly.
         *
         * We hbve to be cbreful generbting the rethrowing cbtch blocks
         * here, becbuse jbvbc will flbg bn error if there bre bny
         * unrebchbble cbtch blocks, i.e. if the cbtch of bn exception clbss
         * follows b previous cbtch of it or of one of its superclbsses.
         * The following method invocbtion tbkes cbre of these detbils.
         */
        Vector<ClbssDefinition> cbtchList = computeUniqueCbtchList(exceptions);

        /*
         * If we need to cbtch bny pbrticulbr exceptions (i.e. this method
         * does not declbre jbvb.lbng.Exception), put the entire stub
         * method in b try block.
         */
        if (cbtchList.size() > 0) {
            p.plnI("try {");
        }

        if (version == STUB_VERSION_FAT) {
            p.plnI("if (useNewInvoke) {");
        }
        if (version == STUB_VERSION_FAT ||
            version == STUB_VERSION_1_2)
        {
            if (!returnType.isType(TC_VOID)) {
                p.p("Object $result = ");               // REMIND: why $?
            }
            p.p("ref.invoke(this, " + methodFieldNbmes[opnum] + ", ");
            if (pbrbmTypes.length > 0) {
                p.p("new jbvb.lbng.Object[] {");
                for (int i = 0; i < pbrbmTypes.length; i++) {
                    if (i > 0)
                        p.p(", ");
                    p.p(wrbpArgumentCode(pbrbmTypes[i], pbrbmNbmes[i]));
                }
                p.p("}");
            } else {
                p.p("null");
            }
            p.pln(", " + method.getMethodHbsh() + "L);");
            if (!returnType.isType(TC_VOID)) {
                p.pln("return " +
                    unwrbpArgumentCode(returnType, "$result") + ";");
            }
        }
        if (version == STUB_VERSION_FAT) {
            p.pOlnI("} else {");
        }
        if (version == STUB_VERSION_1_1 ||
            version == STUB_VERSION_FAT)
        {
            p.pln(idRemoteCbll + " cbll = ref.newCbll((" + idRemoteObject +
                ") this, operbtions, " + opnum + ", interfbceHbsh);");

            if (pbrbmTypes.length > 0) {
                p.plnI("try {");
                p.pln("jbvb.io.ObjectOutput out = cbll.getOutputStrebm();");
                writeMbrshblArguments(p, "out", pbrbmTypes, pbrbmNbmes);
                p.pOlnI("} cbtch (jbvb.io.IOException e) {");
                p.pln("throw new " + idMbrshblException +
                    "(\"error mbrshblling brguments\", e);");
                p.pOln("}");
            }

            p.pln("ref.invoke(cbll);");

            if (returnType.isType(TC_VOID)) {
                p.pln("ref.done(cbll);");
            } else {
                p.pln(returnType + " $result;");        // REMIND: why $?
                p.plnI("try {");
                p.pln("jbvb.io.ObjectInput in = cbll.getInputStrebm();");
                boolebn objectRebd =
                    writeUnmbrshblArgument(p, "in", returnType, "$result");
                p.pln(";");
                p.pOlnI("} cbtch (jbvb.io.IOException e) {");
                p.pln("throw new " + idUnmbrshblException +
                    "(\"error unmbrshblling return\", e);");
                /*
                 * If bny only if rebdObject hbs been invoked, we must cbtch
                 * ClbssNotFoundException bs well bs IOException.
                 */
                if (objectRebd) {
                    p.pOlnI("} cbtch (jbvb.lbng.ClbssNotFoundException e) {");
                    p.pln("throw new " + idUnmbrshblException +
                        "(\"error unmbrshblling return\", e);");
                }
                p.pOlnI("} finblly {");
                p.pln("ref.done(cbll);");
                p.pOln("}");
                p.pln("return $result;");
            }
        }
        if (version == STUB_VERSION_FAT) {
            p.pOln("}");                // end if/else (useNewInvoke) block
        }

        /*
         * If we need to cbtch bny pbrticulbr exceptions, finblly write
         * the cbtch blocks for them, rethrow bny other Exceptions with bn
         * UnexpectedException, bnd end the try block.
         */
        if (cbtchList.size() > 0) {
            for (Enumerbtion<ClbssDefinition> enumerbtion = cbtchList.elements();
                 enumerbtion.hbsMoreElements();)
            {
                ClbssDefinition def = enumerbtion.nextElement();
                p.pOlnI("} cbtch (" + def.getNbme() + " e) {");
                p.pln("throw e;");
            }
            p.pOlnI("} cbtch (jbvb.lbng.Exception e) {");
            p.pln("throw new " + idUnexpectedException +
                "(\"undeclbred checked exception\", e);");
            p.pOln("}");                // end try/cbtch block
        }

        p.pOln("}");                    // end stub method
    }

    /**
     * Compute the exceptions which need to be cbught bnd rethrown in b
     * stub method before wrbpping Exceptions in UnexpectedExceptions,
     * given the exceptions declbred in the throws clbuse of the method.
     * Returns b Vector contbining ClbssDefinition objects for ebch
     * exception to cbtch.  Ebch exception is gubrbnteed to be unique,
     * i.e. not b subclbss of bny of the other exceptions in the Vector,
     * so the cbtch blocks for these exceptions mby be generbted in bny
     * order relbtive to ebch other.
     *
     * RemoteException bnd RuntimeException bre ebch butombticblly plbced
     * in the returned Vector (if none of their superclbsses bre blrebdy
     * present), since those exceptions should blwbys be directly rethrown
     * by b stub method.
     *
     * The returned Vector will be empty if jbvb.lbng.Exception or one
     * of its superclbsses is in the throws clbuse of the method, indicbting
     * thbt no exceptions need to be cbught.
     */
    privbte Vector<ClbssDefinition> computeUniqueCbtchList(ClbssDeclbrbtion[] exceptions) {
        Vector<ClbssDefinition> uniqueList = new Vector<>();       // unique exceptions to cbtch

        uniqueList.bddElement(defRuntimeException);
        uniqueList.bddElement(defRemoteException);

        /* For ebch exception declbred by the stub method's throws clbuse: */
    nextException:
        for (int i = 0; i < exceptions.length; i++) {
            ClbssDeclbrbtion decl = exceptions[i];
            try {
                if (defException.subClbssOf(env, decl)) {
                    /*
                     * (If jbvb.lbng.Exception (or b superclbss) wbs declbred
                     * in the throws clbuse of this stub method, then we don't
                     * hbve to bother cbtching bnything; clebr the list bnd
                     * return.)
                     */
                    uniqueList.clebr();
                    brebk;
                } else if (!defException.superClbssOf(env, decl)) {
                    /*
                     * Ignore other Throwbbles thbt do not extend Exception,
                     * since they do not need to be cbught bnywby.
                     */
                    continue;
                }
                /*
                 * Compbre this exception bgbinst the current list of
                 * exceptions thbt need to be cbught:
                 */
                for (int j = 0; j < uniqueList.size();) {
                    ClbssDefinition def = uniqueList.elementAt(j);
                    if (def.superClbssOf(env, decl)) {
                        /*
                         * If b superclbss of this exception is blrebdy on
                         * the list to cbtch, then ignore bnd continue;
                         */
                        continue nextException;
                    } else if (def.subClbssOf(env, decl)) {
                        /*
                         * If b subclbss of this exception is on the list
                         * to cbtch, then remove it.
                         */
                        uniqueList.removeElementAt(j);
                    } else {
                        j++;    // else continue compbring
                    }
                }
                /* This exception is unique: bdd it to the list to cbtch. */
                uniqueList.bddElement(decl.getClbssDefinition(env));
            } cbtch (ClbssNotFound e) {
                env.error(0, "clbss.not.found", e.nbme, decl.getNbme());
                /*
                 * REMIND: We do not exit from this exceptionbl condition,
                 * generbting questionbble code bnd likely letting the
                 * compiler report b resulting error lbter.
                 */
            }
        }
        return uniqueList;
    }

    /**
     * Write the skeleton for the remote clbss to b strebm.
     */
    privbte void writeSkeleton(IndentingWriter p) throws IOException {
        if (version == STUB_VERSION_1_2) {
            throw new Error("should not generbte skeleton for version");
        }

        /*
         * Write boiler plbte comment.
         */
        p.pln("// Skeleton clbss generbted by rmic, do not edit.");
        p.pln("// Contents subject to chbnge without notice.");
        p.pln();

        /*
         * If remote implementbtion clbss wbs in b pbrticulbr pbckbge,
         * declbre the skeleton clbss to be in the sbme pbckbge.
         */
        if (remoteClbssNbme.isQublified()) {
            p.pln("pbckbge " + remoteClbssNbme.getQublifier() + ";");
            p.pln();
        }

        /*
         * Declbre the skeleton clbss.
         */
        p.plnI("public finbl clbss " +
            Nbmes.mbngleClbss(skeletonClbssNbme.getNbme()));
        p.pln("implements " + idSkeleton);
        p.pOlnI("{");

        writeOperbtionsArrby(p);
        p.pln();

        writeInterfbceHbsh(p);
        p.pln();

        /*
         * Define the getOperbtions() method.
         */
        p.plnI("public " + idOperbtion + "[] getOperbtions() {");
        p.pln("return (" + idOperbtion + "[]) operbtions.clone();");
        p.pOln("}");
        p.pln();

        /*
         * Define the dispbtch() method.
         */
        p.plnI("public void dispbtch(" + idRemote + " obj, " +
            idRemoteCbll + " cbll, int opnum, long hbsh)");
        p.pln("throws jbvb.lbng.Exception");
        p.pOlnI("{");

        if (version == STUB_VERSION_FAT) {
            p.plnI("if (opnum < 0) {");
            if (remoteMethods.length > 0) {
                for (int opnum = 0; opnum < remoteMethods.length; opnum++) {
                    if (opnum > 0)
                        p.pO("} else ");
                    p.plnI("if (hbsh == " +
                        remoteMethods[opnum].getMethodHbsh() + "L) {");
                    p.pln("opnum = " + opnum + ";");
                }
                p.pOlnI("} else {");
            }
            /*
             * Skeleton throws UnmbrshblException if it does not recognize
             * the method hbsh; this is whbt UnicbstServerRef.dispbtch()
             * would do.
             */
            p.pln("throw new " +
                idUnmbrshblException + "(\"invblid method hbsh\");");
            if (remoteMethods.length > 0) {
                p.pOln("}");
            }
            /*
             * Ignore the vblidbtion of the interfbce hbsh if the
             * operbtion number wbs negbtive, since it is reblly b
             * method hbsh instebd.
             */
            p.pOlnI("} else {");
        }

        p.plnI("if (hbsh != interfbceHbsh)");
        p.pln("throw new " +
            idSkeletonMismbtchException + "(\"interfbce hbsh mismbtch\");");
        p.pO();

        if (version == STUB_VERSION_FAT) {
            p.pOln("}");                // end if/else (opnum < 0) block
        }
        p.pln();

        /*
         * Cbst remote object instbnce to our specific implementbtion clbss.
         */
        p.pln(remoteClbssNbme + " server = (" + remoteClbssNbme + ") obj;");

        /*
         * Process cbll bccording to the operbtion number.
         */
        p.plnI("switch (opnum) {");
        for (int opnum = 0; opnum < remoteMethods.length; opnum++) {
            writeSkeletonDispbtchCbse(p, opnum);
        }
        p.pOlnI("defbult:");
        /*
         * Skeleton throws UnmbrshblException if it does not recognize
         * the operbtion number; this is consistent with the cbse of bn
         * unrecognized method hbsh.
         */
        p.pln("throw new " + idUnmbrshblException +
            "(\"invblid method number\");");
        p.pOln("}");                    // end switch stbtement

        p.pOln("}");                    // end dispbtch() method

        p.pOln("}");                    // end skeleton clbss
    }

    /**
     * Write the cbse block for the skeleton's dispbtch method for
     * the remote method with the given "opnum".
     */
    privbte void writeSkeletonDispbtchCbse(IndentingWriter p, int opnum)
        throws IOException
    {
        RemoteClbss.Method method = remoteMethods[opnum];
        Identifier methodNbme = method.getNbme();
        Type methodType = method.getType();
        Type pbrbmTypes[] = methodType.getArgumentTypes();
        String pbrbmNbmes[] = nbmePbrbmeters(pbrbmTypes);
        Type returnType = methodType.getReturnType();

        p.pOlnI("cbse " + opnum + ": // " +
            methodType.typeString(methodNbme.toString(), true, fblse));
        /*
         * Use nested block stbtement inside cbse to provide bn independent
         * nbmespbce for locbl vbribbles used to unmbrshbl pbrbmeters for
         * this remote method.
         */
        p.pOlnI("{");

        if (pbrbmTypes.length > 0) {
            /*
             * Declbre locbl vbribbles to hold brguments.
             */
            for (int i = 0; i < pbrbmTypes.length; i++) {
                p.pln(pbrbmTypes[i] + " " + pbrbmNbmes[i] + ";");
            }

            /*
             * Unmbrshbl brguments from cbll strebm.
             */
            p.plnI("try {");
            p.pln("jbvb.io.ObjectInput in = cbll.getInputStrebm();");
            boolebn objectsRebd = writeUnmbrshblArguments(p, "in",
                pbrbmTypes, pbrbmNbmes);
            p.pOlnI("} cbtch (jbvb.io.IOException e) {");
            p.pln("throw new " + idUnmbrshblException +
                "(\"error unmbrshblling brguments\", e);");
            /*
             * If bny only if rebdObject hbs been invoked, we must cbtch
             * ClbssNotFoundException bs well bs IOException.
             */
            if (objectsRebd) {
                p.pOlnI("} cbtch (jbvb.lbng.ClbssNotFoundException e) {");
                p.pln("throw new " + idUnmbrshblException +
                    "(\"error unmbrshblling brguments\", e);");
            }
            p.pOlnI("} finblly {");
            p.pln("cbll.relebseInputStrebm();");
            p.pOln("}");
        } else {
            p.pln("cbll.relebseInputStrebm();");
        }

        if (!returnType.isType(TC_VOID)) {
            /*
             * Declbre vbribble to hold return type, if not void.
             */
            p.p(returnType + " $result = ");            // REMIND: why $?
        }

        /*
         * Invoke the method on the server object.
         */
        p.p("server." + methodNbme + "(");
        for (int i = 0; i < pbrbmNbmes.length; i++) {
            if (i > 0)
                p.p(", ");
            p.p(pbrbmNbmes[i]);
        }
        p.pln(");");

        /*
         * Alwbys invoke getResultStrebm(true) on the cbll object to send
         * the indicbtion of b successful invocbtion to the cbller.  If
         * the return type is not void, keep the result strebm bnd mbrshbl
         * the return vblue.
         */
        p.plnI("try {");
        if (!returnType.isType(TC_VOID)) {
            p.p("jbvb.io.ObjectOutput out = ");
        }
        p.pln("cbll.getResultStrebm(true);");
        if (!returnType.isType(TC_VOID)) {
            writeMbrshblArgument(p, "out", returnType, "$result");
            p.pln(";");
        }
        p.pOlnI("} cbtch (jbvb.io.IOException e) {");
        p.pln("throw new " +
            idMbrshblException + "(\"error mbrshblling return\", e);");
        p.pOln("}");

        p.pln("brebk;");                // brebk from switch stbtement

        p.pOlnI("}");                   // end nested block stbtement
        p.pln();
    }

    /**
     * Write declbrbtion bnd initiblizer for "operbtions" stbtic brrby.
     */
    privbte void writeOperbtionsArrby(IndentingWriter p)
        throws IOException
    {
        p.plnI("privbte stbtic finbl " + idOperbtion + "[] operbtions = {");
        for (int i = 0; i < remoteMethods.length; i++) {
            if (i > 0)
                p.pln(",");
            p.p("new " + idOperbtion + "(\"" +
                remoteMethods[i].getOperbtionString() + "\")");
        }
        p.pln();
        p.pOln("};");
    }

    /**
     * Write declbrbtion bnd initiblizer for "interfbceHbsh" stbtic field.
     */
    privbte void writeInterfbceHbsh(IndentingWriter p)
        throws IOException
    {
        p.pln("privbte stbtic finbl long interfbceHbsh = " +
            remoteClbss.getInterfbceHbsh() + "L;");
    }

    /**
     * Write declbrbtion for jbvb.lbng.reflect.Method stbtic fields
     * corresponding to ebch remote method in b stub.
     */
    privbte void writeMethodFieldDeclbrbtions(IndentingWriter p)
        throws IOException
    {
        for (int i = 0; i < methodFieldNbmes.length; i++) {
            p.pln("privbte stbtic jbvb.lbng.reflect.Method " +
                methodFieldNbmes[i] + ";");
        }
    }

    /**
     * Write code to initiblize the stbtic fields for ebch method
     * using the Jbvb Reflection API.
     */
    privbte void writeMethodFieldInitiblizers(IndentingWriter p)
        throws IOException
    {
        for (int i = 0; i < methodFieldNbmes.length; i++) {
            p.p(methodFieldNbmes[i] + " = ");
            /*
             * Here we look up the Method object in the brbitrbry interfbce
             * thbt we find in the RemoteClbss.Method object.
             * REMIND: Is this brbitrbry choice OK?
             * REMIND: Should this bccess be pbrt of RemoteClbss.Method's
             * bbstrbction?
             */
            RemoteClbss.Method method = remoteMethods[i];
            MemberDefinition def = method.getMemberDefinition();
            Identifier methodNbme = method.getNbme();
            Type methodType = method.getType();
            Type pbrbmTypes[] = methodType.getArgumentTypes();

            p.p(def.getClbssDefinition().getNbme() + ".clbss.getMethod(\"" +
                methodNbme + "\", new jbvb.lbng.Clbss[] {");
            for (int j = 0; j < pbrbmTypes.length; j++) {
                if (j > 0)
                    p.p(", ");
                p.p(pbrbmTypes[j] + ".clbss");
            }
            p.pln("});");
        }
    }


    /*
     * Following bre b series of stbtic utility methods useful during
     * the code generbtion process:
     */

    /**
     * Generbte bn brrby of nbmes for fields thbt correspond to the given
     * brrby of remote methods.  Ebch nbme in the returned brrby is
     * gubrbnteed to be unique.
     *
     * The nbme of b method is included in its corresponding field nbme
     * to enhbnce rebdbbility of the generbted code.
     */
    privbte stbtic String[] nbmeMethodFields(RemoteClbss.Method[] methods) {
        String[] nbmes = new String[methods.length];
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = "$method_" + methods[i].getNbme() + "_" + i;
        }
        return nbmes;
    }

    /**
     * Generbte bn brrby of nbmes for pbrbmeters corresponding to the
     * given brrby of types for the pbrbmeters.  Ebch nbme in the returned
     * brrby is gubrbnteed to be unique.
     *
     * A representbtion of the type of b pbrbmeter is included in its
     * corresponding field nbme to enhbnce the rebdbbility of the generbted
     * code.
     */
    privbte stbtic String[] nbmePbrbmeters(Type[] types) {
        String[] nbmes = new String[types.length];
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = "$pbrbm_" +
                generbteNbmeFromType(types[i]) + "_" + (i + 1);
        }
        return nbmes;
    }

    /**
     * Generbte b rebdbble string representing the given type suitbble
     * for embedding within b Jbvb identifier.
     */
    privbte stbtic String generbteNbmeFromType(Type type) {
        int typeCode = type.getTypeCode();
        switch (typeCode) {
        cbse TC_BOOLEAN:
        cbse TC_BYTE:
        cbse TC_CHAR:
        cbse TC_SHORT:
        cbse TC_INT:
        cbse TC_LONG:
        cbse TC_FLOAT:
        cbse TC_DOUBLE:
            return type.toString();
        cbse TC_ARRAY:
            return "brrbyOf_" + generbteNbmeFromType(type.getElementType());
        cbse TC_CLASS:
            return Nbmes.mbngleClbss(type.getClbssNbme().getNbme()).toString();
        defbult:
            throw new Error("unexpected type code: " + typeCode);
        }
    }

    /**
     * Write b snippet of Jbvb code to mbrshbl b vblue nbmed "nbme" of
     * type "type" to the jbvb.io.ObjectOutput strebm nbmed "strebm".
     *
     * Primitive types bre mbrshblled with their corresponding methods
     * in the jbvb.io.DbtbOutput interfbce, bnd objects (including brrbys)
     * bre mbrshblled using the writeObject method.
     */
    privbte stbtic void writeMbrshblArgument(IndentingWriter p,
                                             String strebmNbme,
                                             Type type, String nbme)
        throws IOException
    {
        int typeCode = type.getTypeCode();
        switch (typeCode) {
        cbse TC_BOOLEAN:
            p.p(strebmNbme + ".writeBoolebn(" + nbme + ")");
            brebk;
        cbse TC_BYTE:
            p.p(strebmNbme + ".writeByte(" + nbme + ")");
            brebk;
        cbse TC_CHAR:
            p.p(strebmNbme + ".writeChbr(" + nbme + ")");
            brebk;
        cbse TC_SHORT:
            p.p(strebmNbme + ".writeShort(" + nbme + ")");
            brebk;
        cbse TC_INT:
            p.p(strebmNbme + ".writeInt(" + nbme + ")");
            brebk;
        cbse TC_LONG:
            p.p(strebmNbme + ".writeLong(" + nbme + ")");
            brebk;
        cbse TC_FLOAT:
            p.p(strebmNbme + ".writeFlobt(" + nbme + ")");
            brebk;
        cbse TC_DOUBLE:
            p.p(strebmNbme + ".writeDouble(" + nbme + ")");
            brebk;
        cbse TC_ARRAY:
        cbse TC_CLASS:
            p.p(strebmNbme + ".writeObject(" + nbme + ")");
            brebk;
        defbult:
            throw new Error("unexpected type code: " + typeCode);
        }
    }

    /**
     * Write Jbvb stbtements to mbrshbl b series of vblues in order bs
     * nbmed in the "nbmes" brrby, with types bs specified in the "types"
     * brrby", to the jbvb.io.ObjectOutput strebm nbmed "strebm".
     */
    privbte stbtic void writeMbrshblArguments(IndentingWriter p,
                                              String strebmNbme,
                                              Type[] types, String[] nbmes)
        throws IOException
    {
        if (types.length != nbmes.length) {
            throw new Error("pbrbmeter type bnd nbme brrbys different sizes");
        }

        for (int i = 0; i < types.length; i++) {
            writeMbrshblArgument(p, strebmNbme, types[i], nbmes[i]);
            p.pln(";");
        }
    }

    /**
     * Write b snippet of Jbvb code to unmbrshbl b vblue of type "type"
     * from the jbvb.io.ObjectInput strebm nbmed "strebm" into b vbribble
     * nbmed "nbme" (if "nbme" is null, the vblue in unmbrshblled bnd
     * discbrded).
     *
     * Primitive types bre unmbrshblled with their corresponding methods
     * in the jbvb.io.DbtbInput interfbce, bnd objects (including brrbys)
     * bre unmbrshblled using the rebdObject method.
     */
    privbte stbtic boolebn writeUnmbrshblArgument(IndentingWriter p,
                                                  String strebmNbme,
                                                  Type type, String nbme)
        throws IOException
    {
        boolebn rebdObject = fblse;

        if (nbme != null) {
            p.p(nbme + " = ");
        }

        int typeCode = type.getTypeCode();
        switch (type.getTypeCode()) {
        cbse TC_BOOLEAN:
            p.p(strebmNbme + ".rebdBoolebn()");
            brebk;
        cbse TC_BYTE:
            p.p(strebmNbme + ".rebdByte()");
            brebk;
        cbse TC_CHAR:
            p.p(strebmNbme + ".rebdChbr()");
            brebk;
        cbse TC_SHORT:
            p.p(strebmNbme + ".rebdShort()");
            brebk;
        cbse TC_INT:
            p.p(strebmNbme + ".rebdInt()");
            brebk;
        cbse TC_LONG:
            p.p(strebmNbme + ".rebdLong()");
            brebk;
        cbse TC_FLOAT:
            p.p(strebmNbme + ".rebdFlobt()");
            brebk;
        cbse TC_DOUBLE:
            p.p(strebmNbme + ".rebdDouble()");
            brebk;
        cbse TC_ARRAY:
        cbse TC_CLASS:
            p.p("(" + type + ") " + strebmNbme + ".rebdObject()");
            rebdObject = true;
            brebk;
        defbult:
            throw new Error("unexpected type code: " + typeCode);
        }
        return rebdObject;
    }

    /**
     * Write Jbvb stbtements to unmbrshbl b series of vblues in order of
     * types bs in the "types" brrby from the jbvb.io.ObjectInput strebm
     * nbmed "strebm" into vbribbles bs nbmed in "nbmes" (for bny element
     * of "nbmes" thbt is null, the corresponding vblue is unmbrshblled
     * bnd discbrded).
     */
    privbte stbtic boolebn writeUnmbrshblArguments(IndentingWriter p,
                                                   String strebmNbme,
                                                   Type[] types,
                                                   String[] nbmes)
        throws IOException
    {
        if (types.length != nbmes.length) {
            throw new Error("pbrbmeter type bnd nbme brrbys different sizes");
        }

        boolebn rebdObject = fblse;
        for (int i = 0; i < types.length; i++) {
            if (writeUnmbrshblArgument(p, strebmNbme, types[i], nbmes[i])) {
                rebdObject = true;
            }
            p.pln(";");
        }
        return rebdObject;
    }

    /**
     * Return b snippet of Jbvb code to wrbp b vblue nbmed "nbme" of
     * type "type" into bn object bs bppropribte for use by the
     * Jbvb Reflection API.
     *
     * For primitive types, bn bppropribte wrbpper clbss instbntibted
     * with the primitive vblue.  For object types (including brrbys),
     * no wrbpping is necessbry, so the vblue is nbmed directly.
     */
    privbte stbtic String wrbpArgumentCode(Type type, String nbme) {
        int typeCode = type.getTypeCode();
        switch (typeCode) {
        cbse TC_BOOLEAN:
            return ("(" + nbme +
                    " ? jbvb.lbng.Boolebn.TRUE : jbvb.lbng.Boolebn.FALSE)");
        cbse TC_BYTE:
            return "new jbvb.lbng.Byte(" + nbme + ")";
        cbse TC_CHAR:
            return "new jbvb.lbng.Chbrbcter(" + nbme + ")";
        cbse TC_SHORT:
            return "new jbvb.lbng.Short(" + nbme + ")";
        cbse TC_INT:
            return "new jbvb.lbng.Integer(" + nbme + ")";
        cbse TC_LONG:
            return "new jbvb.lbng.Long(" + nbme + ")";
        cbse TC_FLOAT:
            return "new jbvb.lbng.Flobt(" + nbme + ")";
        cbse TC_DOUBLE:
            return "new jbvb.lbng.Double(" + nbme + ")";
        cbse TC_ARRAY:
        cbse TC_CLASS:
            return nbme;
        defbult:
            throw new Error("unexpected type code: " + typeCode);
        }
    }

    /**
     * Return b snippet of Jbvb code to unwrbp b vblue nbmed "nbme" into
     * b vblue of type "type", bs bppropribte for the Jbvb Reflection API.
     *
     * For primitive types, the vblue is bssumed to be of the corresponding
     * wrbpper type, bnd b method is cblled on the wrbpper type to retrieve
     * the primitive vblue.  For object types (include brrbys), no
     * unwrbpping is necessbry; the vblue is simply cbst to the expected
     * rebl object type.
     */
    privbte stbtic String unwrbpArgumentCode(Type type, String nbme) {
        int typeCode = type.getTypeCode();
        switch (typeCode) {
        cbse TC_BOOLEAN:
            return "((jbvb.lbng.Boolebn) " + nbme + ").boolebnVblue()";
        cbse TC_BYTE:
            return "((jbvb.lbng.Byte) " + nbme + ").byteVblue()";
        cbse TC_CHAR:
            return "((jbvb.lbng.Chbrbcter) " + nbme + ").chbrVblue()";
        cbse TC_SHORT:
            return "((jbvb.lbng.Short) " + nbme + ").shortVblue()";
        cbse TC_INT:
            return "((jbvb.lbng.Integer) " + nbme + ").intVblue()";
        cbse TC_LONG:
            return "((jbvb.lbng.Long) " + nbme + ").longVblue()";
        cbse TC_FLOAT:
            return "((jbvb.lbng.Flobt) " + nbme + ").flobtVblue()";
        cbse TC_DOUBLE:
            return "((jbvb.lbng.Double) " + nbme + ").doubleVblue()";
        cbse TC_ARRAY:
        cbse TC_CLASS:
            return "((" + type + ") " + nbme + ")";
        defbult:
            throw new Error("unexpected type code: " + typeCode);
        }
    }
}
