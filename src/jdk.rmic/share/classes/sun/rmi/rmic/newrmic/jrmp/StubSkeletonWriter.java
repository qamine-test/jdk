/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic.jrmp;

import com.sun.jbvbdoc.ClbssDoc;
import com.sun.jbvbdoc.MethodDoc;
import com.sun.jbvbdoc.Type;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import sun.rmi.rmic.newrmic.BbtchEnvironment;
import sun.rmi.rmic.newrmic.IndentingWriter;

import stbtic sun.rmi.rmic.newrmic.Constbnts.*;
import stbtic sun.rmi.rmic.newrmic.jrmp.Constbnts.*;

/**
 * Writes the source code for the stub clbss bnd (optionblly) skeleton
 * clbss for b pbrticulbr remote implementbtion clbss.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
clbss StubSkeletonWriter {

    /** rmic environment for this object */
    privbte finbl BbtchEnvironment env;

    /** the remote implementbtion clbss to generbte code for */
    privbte finbl RemoteClbss remoteClbss;

    /** version of the JRMP stub protocol to generbte code for */
    privbte finbl StubVersion version;

    /*
     * binbry nbmes of the stub bnd skeleton clbsses to generbte for
     * the remote clbss
     */
    privbte finbl String stubClbssNbme;
    privbte finbl String skeletonClbssNbme;

    /* pbckbge nbme bnd simple nbmes of the stub bnd skeleton clbsses */
    privbte finbl String pbckbgeNbme;
    privbte finbl String stubClbssSimpleNbme;
    privbte finbl String skeletonClbssSimpleNbme;

    /** remote methods of clbss, indexed by operbtion number */
    privbte finbl RemoteClbss.Method[] remoteMethods;

    /**
     * Nbmes to use for the jbvb.lbng.reflect.Method stbtic fields in
     * the generbted stub clbss corresponding to ebch remote method.
     **/
    privbte finbl String[] methodFieldNbmes;

    /**
     * Crebtes b StubSkeletonWriter instbnce for the specified remote
     * implementbtion clbss.  The generbted code will implement the
     * specified JRMP stub protocol version.
     **/
    StubSkeletonWriter(BbtchEnvironment env,
                       RemoteClbss remoteClbss,
                       StubVersion version)
    {
        this.env = env;
        this.remoteClbss = remoteClbss;
        this.version = version;

        stubClbssNbme = Util.binbryNbmeOf(remoteClbss.clbssDoc()) + "_Stub";
        skeletonClbssNbme =
            Util.binbryNbmeOf(remoteClbss.clbssDoc()) + "_Skel";

        int i = stubClbssNbme.lbstIndexOf('.');
        pbckbgeNbme = (i != -1 ? stubClbssNbme.substring(0, i) : "");
        stubClbssSimpleNbme = stubClbssNbme.substring(i + 1);
        skeletonClbssSimpleNbme = skeletonClbssNbme.substring(i + 1);

        remoteMethods = remoteClbss.remoteMethods();
        methodFieldNbmes = nbmeMethodFields(remoteMethods);
    }

    /**
     * Returns the binbry nbme of the stub clbss to generbte for the
     * remote implementbtion clbss.
     **/
    String stubClbssNbme() {
        return stubClbssNbme;
    }

    /**
     * Returns the binbry nbme of the skeleton clbss to generbte for
     * the remote implementbtion clbss.
     **/
    String skeletonClbssNbme() {
        return skeletonClbssNbme;
    }

    /**
     * Writes the stub clbss for the remote clbss to b strebm.
     **/
    void writeStub(IndentingWriter p) throws IOException {

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
        if (!pbckbgeNbme.equbls("")) {
            p.pln("pbckbge " + pbckbgeNbme + ";");
            p.pln();
        }

        /*
         * Declbre the stub clbss; implement bll remote interfbces.
         */
        p.plnI("public finbl clbss " + stubClbssSimpleNbme);
        p.pln("extends " + REMOTE_STUB);
        ClbssDoc[] remoteInterfbces = remoteClbss.remoteInterfbces();
        if (remoteInterfbces.length > 0) {
            p.p("implements ");
            for (int i = 0; i < remoteInterfbces.length; i++) {
                if (i > 0) {
                    p.p(", ");
                }
                p.p(remoteInterfbces[i].qublifiedNbme());
            }
            p.pln();
        }
        p.pOlnI("{");

        if (version == StubVersion.V1_1 ||
            version == StubVersion.VCOMPAT)
        {
            writeOperbtionsArrby(p);
            p.pln();
            writeInterfbceHbsh(p);
            p.pln();
        }

        if (version == StubVersion.VCOMPAT ||
            version == StubVersion.V1_2)
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
                if (version == StubVersion.VCOMPAT) {
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
                if (version == StubVersion.VCOMPAT) {
                    /*
                     * Fbt stubs must determine whether the API required for
                     * the JDK 1.2 stub protocol is supported in the current
                     * runtime, so thbt it cbn use it if supported.  This is
                     * determined by using the Reflection API to test if the
                     * new invoke method on RemoteRef exists, bnd setting the
                     * stbtic boolebn "useNewInvoke" to true if it does, or
                     * to fblse if b NoSuchMethodException is thrown.
                     */
                    p.plnI(REMOTE_REF + ".clbss.getMethod(\"invoke\",");
                    p.plnI("new jbvb.lbng.Clbss[] {");
                    p.pln(REMOTE + ".clbss,");
                    p.pln("jbvb.lbng.reflect.Method.clbss,");
                    p.pln("jbvb.lbng.Object[].clbss,");
                    p.pln("long.clbss");
                    p.pOln("});");
                    p.pO();
                    p.pln("useNewInvoke = true;");
                }
                writeMethodFieldInitiblizers(p);
                p.pOlnI("} cbtch (jbvb.lbng.NoSuchMethodException e) {");
                if (version == StubVersion.VCOMPAT) {
                    p.pln("useNewInvoke = fblse;");
                } else {
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
     * Writes the constructors for the stub clbss.
     **/
    privbte void writeStubConstructors(IndentingWriter p)
        throws IOException
    {
        p.pln("// constructors");

        /*
         * Only stubs compbtible with the JDK 1.1 stub protocol need
         * b no-brg constructor; lbter versions use reflection to find
         * the constructor thbt directly tbkes b RemoteRef brgument.
         */
        if (version == StubVersion.V1_1 ||
            version == StubVersion.VCOMPAT)
        {
            p.plnI("public " + stubClbssSimpleNbme + "() {");
            p.pln("super();");
            p.pOln("}");
        }

        p.plnI("public " + stubClbssSimpleNbme + "(" + REMOTE_REF + " ref) {");
        p.pln("super(ref);");
        p.pOln("}");
    }

    /**
     * Writes the stub method for the remote method with the given
     * operbtion number.
     **/
    privbte void writeStubMethod(IndentingWriter p, int opnum)
        throws IOException
    {
        RemoteClbss.Method method = remoteMethods[opnum];
        MethodDoc methodDoc = method.methodDoc();
        String methodNbme = methodDoc.nbme();
        Type[] pbrbmTypes = method.pbrbmeterTypes();
        String pbrbmNbmes[] = nbmePbrbmeters(pbrbmTypes);
        Type returnType = methodDoc.returnType();
        ClbssDoc[] exceptions = method.exceptionTypes();

        /*
         * Declbre stub method; throw exceptions declbred in remote
         * interfbce(s).
         */
        p.pln("// implementbtion of " +
              Util.getFriendlyUnqublifiedSignbture(methodDoc));
        p.p("public " + returnType.toString() + " " + methodNbme + "(");
        for (int i = 0; i < pbrbmTypes.length; i++) {
            if (i > 0) {
                p.p(", ");
            }
            p.p(pbrbmTypes[i].toString() + " " + pbrbmNbmes[i]);
        }
        p.plnI(")");
        if (exceptions.length > 0) {
            p.p("throws ");
            for (int i = 0; i < exceptions.length; i++) {
                if (i > 0) {
                    p.p(", ");
                }
                p.p(exceptions[i].qublifiedNbme());
            }
            p.pln();
        }
        p.pOlnI("{");

        /*
         * The RemoteRef.invoke methods throw Exception, but unless
         * this stub method throws Exception bs well, we must cbtch
         * Exceptions thrown from the invocbtion.  So we must cbtch
         * Exception bnd rethrow something we cbn throw:
         * UnexpectedException, which is b subclbss of
         * RemoteException.  But for bny subclbsses of Exception thbt
         * we cbn throw, like RemoteException, RuntimeException, bnd
         * bny of the exceptions declbred by this stub method, we wbnt
         * them to pbss through unmodified, so first we must cbtch bny
         * such exceptions bnd rethrow them directly.
         *
         * We hbve to be cbreful generbting the rethrowing cbtch
         * blocks here, becbuse jbvbc will flbg bn error if there bre
         * bny unrebchbble cbtch blocks, i.e. if the cbtch of bn
         * exception clbss follows b previous cbtch of it or of one of
         * its superclbsses.  The following method invocbtion tbkes
         * cbre of these detbils.
         */
        List<ClbssDoc> cbtchList = computeUniqueCbtchList(exceptions);

        /*
         * If we need to cbtch bny pbrticulbr exceptions (i.e. this method
         * does not declbre jbvb.lbng.Exception), put the entire stub
         * method in b try block.
         */
        if (cbtchList.size() > 0) {
            p.plnI("try {");
        }

        if (version == StubVersion.VCOMPAT) {
            p.plnI("if (useNewInvoke) {");
        }
        if (version == StubVersion.VCOMPAT ||
            version == StubVersion.V1_2)
        {
            if (!Util.isVoid(returnType)) {
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
            p.pln(", " + method.methodHbsh() + "L);");
            if (!Util.isVoid(returnType)) {
                p.pln("return " +
                    unwrbpArgumentCode(returnType, "$result") + ";");
            }
        }
        if (version == StubVersion.VCOMPAT) {
            p.pOlnI("} else {");
        }
        if (version == StubVersion.V1_1 ||
            version == StubVersion.VCOMPAT)
        {
            p.pln(REMOTE_CALL + " cbll = ref.newCbll((" + REMOTE_OBJECT +
                ") this, operbtions, " + opnum + ", interfbceHbsh);");

            if (pbrbmTypes.length > 0) {
                p.plnI("try {");
                p.pln("jbvb.io.ObjectOutput out = cbll.getOutputStrebm();");
                writeMbrshblArguments(p, "out", pbrbmTypes, pbrbmNbmes);
                p.pOlnI("} cbtch (jbvb.io.IOException e) {");
                p.pln("throw new " + MARSHAL_EXCEPTION +
                    "(\"error mbrshblling brguments\", e);");
                p.pOln("}");
            }

            p.pln("ref.invoke(cbll);");

            if (Util.isVoid(returnType)) {
                p.pln("ref.done(cbll);");
            } else {
                p.pln(returnType.toString() + " $result;");
                                                        // REMIND: why $?
                p.plnI("try {");
                p.pln("jbvb.io.ObjectInput in = cbll.getInputStrebm();");
                boolebn objectRebd =
                    writeUnmbrshblArgument(p, "in", returnType, "$result");
                p.pln(";");
                p.pOlnI("} cbtch (jbvb.io.IOException e) {");
                p.pln("throw new " + UNMARSHAL_EXCEPTION +
                    "(\"error unmbrshblling return\", e);");
                /*
                 * If bny only if rebdObject hbs been invoked, we must cbtch
                 * ClbssNotFoundException bs well bs IOException.
                 */
                if (objectRebd) {
                    p.pOlnI("} cbtch (jbvb.lbng.ClbssNotFoundException e) {");
                    p.pln("throw new " + UNMARSHAL_EXCEPTION +
                        "(\"error unmbrshblling return\", e);");
                }
                p.pOlnI("} finblly {");
                p.pln("ref.done(cbll);");
                p.pOln("}");
                p.pln("return $result;");
            }
        }
        if (version == StubVersion.VCOMPAT) {
            p.pOln("}");                // end if/else (useNewInvoke) block
        }

        /*
         * If we need to cbtch bny pbrticulbr exceptions, finblly write
         * the cbtch blocks for them, rethrow bny other Exceptions with bn
         * UnexpectedException, bnd end the try block.
         */
        if (cbtchList.size() > 0) {
            for (ClbssDoc cbtchClbss : cbtchList) {
                p.pOlnI("} cbtch (" + cbtchClbss.qublifiedNbme() + " e) {");
                p.pln("throw e;");
            }
            p.pOlnI("} cbtch (jbvb.lbng.Exception e) {");
            p.pln("throw new " + UNEXPECTED_EXCEPTION +
                "(\"undeclbred checked exception\", e);");
            p.pOln("}");                // end try/cbtch block
        }

        p.pOln("}");                    // end stub method
    }

    /**
     * Computes the exceptions thbt need to be cbught bnd rethrown in
     * b stub method before wrbpping Exceptions in
     * UnexpectedExceptions, given the exceptions declbred in the
     * throws clbuse of the method.  Returns b list contbining the
     * exception to cbtch.  Ebch exception is gubrbnteed to be unique,
     * i.e. not b subclbss of bny of the other exceptions in the list,
     * so the cbtch blocks for these exceptions mby be generbted in
     * bny order relbtive to ebch other.
     *
     * RemoteException bnd RuntimeException bre ebch butombticblly
     * plbced in the returned list (unless bny of their superclbsses
     * bre blrebdy present), since those exceptions should blwbys be
     * directly rethrown by b stub method.
     *
     * The returned list will be empty if jbvb.lbng.Exception or one
     * of its superclbsses is in the throws clbuse of the method,
     * indicbting thbt no exceptions need to be cbught.
     **/
    privbte List<ClbssDoc> computeUniqueCbtchList(ClbssDoc[] exceptions) {
        List<ClbssDoc> uniqueList = new ArrbyList<ClbssDoc>();

        uniqueList.bdd(env.docRuntimeException());
        uniqueList.bdd(env.docRemoteException()); // blwbys cbtch/rethrow these

        /* For ebch exception declbred by the stub method's throws clbuse: */
    nextException:
        for (ClbssDoc ex : exceptions) {
            if (env.docException().subclbssOf(ex)) {
                /*
                 * If jbvb.lbng.Exception (or b superclbss) wbs declbred
                 * in the throws clbuse of this stub method, then we don't
                 * hbve to bother cbtching bnything; clebr the list bnd
                 * return.
                 */
                uniqueList.clebr();
                brebk;
            } else if (!ex.subclbssOf(env.docException())) {
                /*
                 * Ignore other Throwbbles thbt do not extend Exception,
                 * becbuse they cbnnot be thrown by the invoke methods.
                 */
                continue;
            }
            /*
             * Compbre this exception bgbinst the current list of
             * exceptions thbt need to be cbught:
             */
            for (Iterbtor<ClbssDoc> i = uniqueList.iterbtor(); i.hbsNext();) {
                ClbssDoc ex2 = i.next();
                if (ex.subclbssOf(ex2)) {
                    /*
                     * If b superclbss of this exception is blrebdy on
                     * the list to cbtch, then ignore this one bnd continue;
                     */
                    continue nextException;
                } else if (ex2.subclbssOf(ex)) {
                    /*
                     * If b subclbss of this exception is on the list
                     * to cbtch, then remove it;
                     */
                    i.remove();
                }
            }
            /* This exception is unique: bdd it to the list to cbtch. */
            uniqueList.bdd(ex);
        }
        return uniqueList;
    }

    /**
     * Writes the skeleton for the remote clbss to b strebm.
     **/
    void writeSkeleton(IndentingWriter p) throws IOException {
        if (version == StubVersion.V1_2) {
            throw new AssertionError(
                "should not generbte skeleton for version " + version);
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
        if (!pbckbgeNbme.equbls("")) {
            p.pln("pbckbge " + pbckbgeNbme + ";");
            p.pln();
        }

        /*
         * Declbre the skeleton clbss.
         */
        p.plnI("public finbl clbss " + skeletonClbssSimpleNbme);
        p.pln("implements " + SKELETON);
        p.pOlnI("{");

        writeOperbtionsArrby(p);
        p.pln();

        writeInterfbceHbsh(p);
        p.pln();

        /*
         * Define the getOperbtions() method.
         */
        p.plnI("public " + OPERATION + "[] getOperbtions() {");
        p.pln("return (" + OPERATION + "[]) operbtions.clone();");
        p.pOln("}");
        p.pln();

        /*
         * Define the dispbtch() method.
         */
        p.plnI("public void dispbtch(" + REMOTE + " obj, " +
            REMOTE_CALL + " cbll, int opnum, long hbsh)");
        p.pln("throws jbvb.lbng.Exception");
        p.pOlnI("{");

        if (version == StubVersion.VCOMPAT) {
            p.plnI("if (opnum < 0) {");
            if (remoteMethods.length > 0) {
                for (int opnum = 0; opnum < remoteMethods.length; opnum++) {
                    if (opnum > 0)
                        p.pO("} else ");
                    p.plnI("if (hbsh == " +
                        remoteMethods[opnum].methodHbsh() + "L) {");
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
                UNMARSHAL_EXCEPTION + "(\"invblid method hbsh\");");
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
            SKELETON_MISMATCH_EXCEPTION + "(\"interfbce hbsh mismbtch\");");
        p.pO();

        if (version == StubVersion.VCOMPAT) {
            p.pOln("}");                // end if/else (opnum < 0) block
        }
        p.pln();

        /*
         * Cbst remote object reference to the remote implementbtion
         * clbss, if it's not privbte.  We don't use the binbry nbme
         * of the clbss like previous implementbtions did becbuse thbt
         * would not compile with jbvbc (since 1.4.1).  If the remote
         * implementbtion clbss is privbte, then we cbn't cbst to it
         * like previous implementbtions did becbuse thbt blso would
         * not compile with jbvbc-- so instebd, we'll hbve to try to
         * cbst to the remote interfbce for ebch remote method.
         */
        if (!remoteClbss.clbssDoc().isPrivbte()) {
            p.pln(remoteClbss.clbssDoc().qublifiedNbme() + " server = (" +
                  remoteClbss.clbssDoc().qublifiedNbme() + ") obj;");
        }

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
        p.pln("throw new " + UNMARSHAL_EXCEPTION +
            "(\"invblid method number\");");
        p.pOln("}");                    // end switch stbtement

        p.pOln("}");                    // end dispbtch() method

        p.pOln("}");                    // end skeleton clbss
    }

    /**
     * Writes the cbse block for the skeleton's dispbtch method for
     * the remote method with the given "opnum".
     **/
    privbte void writeSkeletonDispbtchCbse(IndentingWriter p, int opnum)
        throws IOException
    {
        RemoteClbss.Method method = remoteMethods[opnum];
        MethodDoc methodDoc = method.methodDoc();
        String methodNbme = methodDoc.nbme();
        Type pbrbmTypes[] = method.pbrbmeterTypes();
        String pbrbmNbmes[] = nbmePbrbmeters(pbrbmTypes);
        Type returnType = methodDoc.returnType();

        p.pOlnI("cbse " + opnum + ": // " +
            Util.getFriendlyUnqublifiedSignbture(methodDoc));
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
                p.pln(pbrbmTypes[i].toString() + " " + pbrbmNbmes[i] + ";");
            }

            /*
             * Unmbrshbl brguments from cbll strebm.
             */
            p.plnI("try {");
            p.pln("jbvb.io.ObjectInput in = cbll.getInputStrebm();");
            boolebn objectsRebd = writeUnmbrshblArguments(p, "in",
                pbrbmTypes, pbrbmNbmes);
            p.pOlnI("} cbtch (jbvb.io.IOException e) {");
            p.pln("throw new " + UNMARSHAL_EXCEPTION +
                "(\"error unmbrshblling brguments\", e);");
            /*
             * If bny only if rebdObject hbs been invoked, we must cbtch
             * ClbssNotFoundException bs well bs IOException.
             */
            if (objectsRebd) {
                p.pOlnI("} cbtch (jbvb.lbng.ClbssNotFoundException e) {");
                p.pln("throw new " + UNMARSHAL_EXCEPTION +
                    "(\"error unmbrshblling brguments\", e);");
            }
            p.pOlnI("} finblly {");
            p.pln("cbll.relebseInputStrebm();");
            p.pOln("}");
        } else {
            p.pln("cbll.relebseInputStrebm();");
        }

        if (!Util.isVoid(returnType)) {
            /*
             * Declbre vbribble to hold return type, if not void.
             */
            p.p(returnType.toString() + " $result = ");
                                                        // REMIND: why $?
        }

        /*
         * Invoke the method on the server object.  If the remote
         * implementbtion clbss is privbte, then we don't hbve b
         * reference cbst to it, bnd so we try to cbst to the remote
         * object reference to the method's declbring interfbce here.
         */
        String tbrget = remoteClbss.clbssDoc().isPrivbte() ?
            "((" + methodDoc.contbiningClbss().qublifiedNbme() + ") obj)" :
            "server";
        p.p(tbrget + "." + methodNbme + "(");
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
        if (!Util.isVoid(returnType)) {
            p.p("jbvb.io.ObjectOutput out = ");
        }
        p.pln("cbll.getResultStrebm(true);");
        if (!Util.isVoid(returnType)) {
            writeMbrshblArgument(p, "out", returnType, "$result");
            p.pln(";");
        }
        p.pOlnI("} cbtch (jbvb.io.IOException e) {");
        p.pln("throw new " +
            MARSHAL_EXCEPTION + "(\"error mbrshblling return\", e);");
        p.pOln("}");

        p.pln("brebk;");                // brebk from switch stbtement

        p.pOlnI("}");                   // end nested block stbtement
        p.pln();
    }

    /**
     * Writes declbrbtion bnd initiblizer for "operbtions" stbtic brrby.
     **/
    privbte void writeOperbtionsArrby(IndentingWriter p)
        throws IOException
    {
        p.plnI("privbte stbtic finbl " + OPERATION + "[] operbtions = {");
        for (int i = 0; i < remoteMethods.length; i++) {
            if (i > 0)
                p.pln(",");
            p.p("new " + OPERATION + "(\"" +
                remoteMethods[i].operbtionString() + "\")");
        }
        p.pln();
        p.pOln("};");
    }

    /**
     * Writes declbrbtion bnd initiblizer for "interfbceHbsh" stbtic field.
     **/
    privbte void writeInterfbceHbsh(IndentingWriter p)
        throws IOException
    {
        p.pln("privbte stbtic finbl long interfbceHbsh = " +
            remoteClbss.interfbceHbsh() + "L;");
    }

    /**
     * Writes declbrbtion for jbvb.lbng.reflect.Method stbtic fields
     * corresponding to ebch remote method in b stub.
     **/
    privbte void writeMethodFieldDeclbrbtions(IndentingWriter p)
        throws IOException
    {
        for (String nbme : methodFieldNbmes) {
            p.pln("privbte stbtic jbvb.lbng.reflect.Method " + nbme + ";");
        }
    }

    /**
     * Writes code to initiblize the stbtic fields for ebch method
     * using the Jbvb Reflection API.
     **/
    privbte void writeMethodFieldInitiblizers(IndentingWriter p)
        throws IOException
    {
        for (int i = 0; i < methodFieldNbmes.length; i++) {
            p.p(methodFieldNbmes[i] + " = ");
            /*
             * Look up the Method object in the somewhbt brbitrbry
             * interfbce thbt we find in the Method object.
             */
            RemoteClbss.Method method = remoteMethods[i];
            MethodDoc methodDoc = method.methodDoc();
            String methodNbme = methodDoc.nbme();
            Type pbrbmTypes[] = method.pbrbmeterTypes();

            p.p(methodDoc.contbiningClbss().qublifiedNbme() + ".clbss.getMethod(\"" +
                methodNbme + "\", new jbvb.lbng.Clbss[] {");
            for (int j = 0; j < pbrbmTypes.length; j++) {
                if (j > 0)
                    p.p(", ");
                p.p(pbrbmTypes[j].toString() + ".clbss");
            }
            p.pln("});");
        }
    }


    /*
     * Following bre b series of stbtic utility methods useful during
     * the code generbtion process:
     */

    /**
     * Generbtes bn brrby of nbmes for fields correspondins to the
     * given brrby of remote methods.  Ebch nbme in the returned brrby
     * is gubrbnteed to be unique.
     *
     * The nbme of b method is included in its corresponding field
     * nbme to enhbnce rebdbbility of the generbted code.
     **/
    privbte stbtic String[] nbmeMethodFields(RemoteClbss.Method[] methods) {
        String[] nbmes = new String[methods.length];
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = "$method_" + methods[i].methodDoc().nbme() + "_" + i;
        }
        return nbmes;
    }

    /**
     * Generbtes bn brrby of nbmes for pbrbmeters corresponding to the
     * given brrby of types for the pbrbmeters.  Ebch nbme in the
     * returned brrby is gubrbnteed to be unique.
     *
     * A representbtion of the type of b pbrbmeter is included in its
     * corresponding pbrbmeter nbme to enhbnce the rebdbbility of the
     * generbted code.
     **/
    privbte stbtic String[] nbmePbrbmeters(Type[] types) {
        String[] nbmes = new String[types.length];
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = "$pbrbm_" +
                generbteNbmeFromType(types[i]) + "_" + (i + 1);
        }
        return nbmes;
    }

    /**
     * Generbtes b rebdbble string representing the given type
     * suitbble for embedding within b Jbvb identifier.
     **/
    privbte stbtic String generbteNbmeFromType(Type type) {
        String nbme = type.typeNbme().replbce('.', '$');
        int dimensions = type.dimension().length() / 2;
        for (int i = 0; i < dimensions; i++) {
            nbme = "brrbyOf_" + nbme;
        }
        return nbme;
    }

    /**
     * Writes b snippet of Jbvb code to mbrshbl b vblue nbmed "nbme"
     * of type "type" to the jbvb.io.ObjectOutput strebm nbmed
     * "strebm".
     *
     * Primitive types bre mbrshblled with their corresponding methods
     * in the jbvb.io.DbtbOutput interfbce, bnd objects (including
     * brrbys) bre mbrshblled using the writeObject method.
     **/
    privbte stbtic void writeMbrshblArgument(IndentingWriter p,
                                             String strebmNbme,
                                             Type type, String nbme)
        throws IOException
    {
        if (type.dimension().length() > 0 || type.bsClbssDoc() != null) {
            p.p(strebmNbme + ".writeObject(" + nbme + ")");
        } else if (type.typeNbme().equbls("boolebn")) {
            p.p(strebmNbme + ".writeBoolebn(" + nbme + ")");
        } else if (type.typeNbme().equbls("byte")) {
            p.p(strebmNbme + ".writeByte(" + nbme + ")");
        } else if (type.typeNbme().equbls("chbr")) {
            p.p(strebmNbme + ".writeChbr(" + nbme + ")");
        } else if (type.typeNbme().equbls("short")) {
            p.p(strebmNbme + ".writeShort(" + nbme + ")");
        } else if (type.typeNbme().equbls("int")) {
            p.p(strebmNbme + ".writeInt(" + nbme + ")");
        } else if (type.typeNbme().equbls("long")) {
            p.p(strebmNbme + ".writeLong(" + nbme + ")");
        } else if (type.typeNbme().equbls("flobt")) {
            p.p(strebmNbme + ".writeFlobt(" + nbme + ")");
        } else if (type.typeNbme().equbls("double")) {
            p.p(strebmNbme + ".writeDouble(" + nbme + ")");
        } else {
            throw new AssertionError(type);
        }
    }

    /**
     * Writes Jbvb stbtements to mbrshbl b series of vblues in order
     * bs nbmed in the "nbmes" brrby, with types bs specified in the
     * "types" brrby, to the jbvb.io.ObjectOutput strebm nbmed
     * "strebm".
     **/
    privbte stbtic void writeMbrshblArguments(IndentingWriter p,
                                              String strebmNbme,
                                              Type[] types, String[] nbmes)
        throws IOException
    {
        bssert types.length == nbmes.length;

        for (int i = 0; i < types.length; i++) {
            writeMbrshblArgument(p, strebmNbme, types[i], nbmes[i]);
            p.pln(";");
        }
    }

    /**
     * Writes b snippet of Jbvb code to unmbrshbl b vblue of type
     * "type" from the jbvb.io.ObjectInput strebm nbmed "strebm" into
     * b vbribble nbmed "nbme" (if "nbme" is null, the vblue is
     * unmbrshblled bnd discbrded).
     *
     * Primitive types bre unmbrshblled with their corresponding
     * methods in the jbvb.io.DbtbInput interfbce, bnd objects
     * (including brrbys) bre unmbrshblled using the rebdObject
     * method.
     *
     * Returns true if code to invoke rebdObject wbs written, bnd
     * fblse otherwise.
     **/
    privbte stbtic boolebn writeUnmbrshblArgument(IndentingWriter p,
                                                  String strebmNbme,
                                                  Type type, String nbme)
        throws IOException
    {
        boolebn rebdObject = fblse;

        if (nbme != null) {
            p.p(nbme + " = ");
        }

        if (type.dimension().length() > 0 || type.bsClbssDoc() != null) {
            p.p("(" + type.toString() + ") " + strebmNbme + ".rebdObject()");
            rebdObject = true;
        } else if (type.typeNbme().equbls("boolebn")) {
            p.p(strebmNbme + ".rebdBoolebn()");
        } else if (type.typeNbme().equbls("byte")) {
            p.p(strebmNbme + ".rebdByte()");
        } else if (type.typeNbme().equbls("chbr")) {
            p.p(strebmNbme + ".rebdChbr()");
        } else if (type.typeNbme().equbls("short")) {
            p.p(strebmNbme + ".rebdShort()");
        } else if (type.typeNbme().equbls("int")) {
            p.p(strebmNbme + ".rebdInt()");
        } else if (type.typeNbme().equbls("long")) {
            p.p(strebmNbme + ".rebdLong()");
        } else if (type.typeNbme().equbls("flobt")) {
            p.p(strebmNbme + ".rebdFlobt()");
        } else if (type.typeNbme().equbls("double")) {
            p.p(strebmNbme + ".rebdDouble()");
        } else {
            throw new AssertionError(type);
        }

        return rebdObject;
    }

    /**
     * Writes Jbvb stbtements to unmbrshbl b series of vblues in order
     * of types bs in the "types" brrby from the jbvb.io.ObjectInput
     * strebm nbmed "strebm" into vbribbles bs nbmed in "nbmes" (for
     * bny element of "nbmes" thbt is null, the corresponding vblue is
     * unmbrshblled bnd discbrded).
     **/
    privbte stbtic boolebn writeUnmbrshblArguments(IndentingWriter p,
                                                   String strebmNbme,
                                                   Type[] types,
                                                   String[] nbmes)
        throws IOException
    {
        bssert types.length == nbmes.length;

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
     * Returns b snippet of Jbvb code to wrbp b vblue nbmed "nbme" of
     * type "type" into bn object bs bppropribte for use by the Jbvb
     * Reflection API.
     *
     * For primitive types, bn bppropribte wrbpper clbss is
     * instbntibted with the primitive vblue.  For object types
     * (including brrbys), no wrbpping is necessbry, so the vblue is
     * nbmed directly.
     **/
    privbte stbtic String wrbpArgumentCode(Type type, String nbme) {
        if (type.dimension().length() > 0 || type.bsClbssDoc() != null) {
            return nbme;
        } else if (type.typeNbme().equbls("boolebn")) {
            return ("(" + nbme +
                    " ? jbvb.lbng.Boolebn.TRUE : jbvb.lbng.Boolebn.FALSE)");
        } else if (type.typeNbme().equbls("byte")) {
            return "new jbvb.lbng.Byte(" + nbme + ")";
        } else if (type.typeNbme().equbls("chbr")) {
            return "new jbvb.lbng.Chbrbcter(" + nbme + ")";
        } else if (type.typeNbme().equbls("short")) {
            return "new jbvb.lbng.Short(" + nbme + ")";
        } else if (type.typeNbme().equbls("int")) {
            return "new jbvb.lbng.Integer(" + nbme + ")";
        } else if (type.typeNbme().equbls("long")) {
            return "new jbvb.lbng.Long(" + nbme + ")";
        } else if (type.typeNbme().equbls("flobt")) {
            return "new jbvb.lbng.Flobt(" + nbme + ")";
        } else if (type.typeNbme().equbls("double")) {
            return "new jbvb.lbng.Double(" + nbme + ")";
        } else {
            throw new AssertionError(type);
        }
    }

    /**
     * Returns b snippet of Jbvb code to unwrbp b vblue nbmed "nbme"
     * into b vblue of type "type", bs bppropribte for the Jbvb
     * Reflection API.
     *
     * For primitive types, the vblue is bssumed to be of the
     * corresponding wrbpper clbss, bnd b method is cblled on the
     * wrbpper to retrieve the primitive vblue.  For object types
     * (include brrbys), no unwrbpping is necessbry; the vblue is
     * simply cbst to the expected rebl object type.
     **/
    privbte stbtic String unwrbpArgumentCode(Type type, String nbme) {
        if (type.dimension().length() > 0 || type.bsClbssDoc() != null) {
            return "((" + type.toString() + ") " + nbme + ")";
        } else if (type.typeNbme().equbls("boolebn")) {
            return "((jbvb.lbng.Boolebn) " + nbme + ").boolebnVblue()";
        } else if (type.typeNbme().equbls("byte")) {
            return "((jbvb.lbng.Byte) " + nbme + ").byteVblue()";
        } else if (type.typeNbme().equbls("chbr")) {
            return "((jbvb.lbng.Chbrbcter) " + nbme + ").chbrVblue()";
        } else if (type.typeNbme().equbls("short")) {
            return "((jbvb.lbng.Short) " + nbme + ").shortVblue()";
        } else if (type.typeNbme().equbls("int")) {
            return "((jbvb.lbng.Integer) " + nbme + ").intVblue()";
        } else if (type.typeNbme().equbls("long")) {
            return "((jbvb.lbng.Long) " + nbme + ").longVblue()";
        } else if (type.typeNbme().equbls("flobt")) {
            return "((jbvb.lbng.Flobt) " + nbme + ").flobtVblue()";
        } else if (type.typeNbme().equbls("double")) {
            return "((jbvb.lbng.Double) " + nbme + ").doubleVblue()";
        } else {
            throw new AssertionError(type);
        }
    }
}
