/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.invoke;

import jdk.internbl.org.objectweb.bsm.*;
import sun.invoke.util.BytecodeDescriptor;
import sun.misc.Unsbfe;
import sun.security.bction.GetPropertyAction;

import jbvb.io.FilePermission;
import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.Constructor;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.LinkedHbshSet;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.PropertyPermission;
import jbvb.util.Set;

import stbtic jdk.internbl.org.objectweb.bsm.Opcodes.*;

/**
 * Lbmbdb metbfbctory implementbtion which dynbmicblly crebtes bn
 * inner-clbss-like clbss per lbmbdb cbllsite.
 *
 * @see LbmbdbMetbfbctory
 */
/* pbckbge */ finbl clbss InnerClbssLbmbdbMetbfbctory extends AbstrbctVblidbtingLbmbdbMetbfbctory {
    privbte stbtic finbl Unsbfe UNSAFE = Unsbfe.getUnsbfe();

    privbte stbtic finbl int CLASSFILE_VERSION = 52;
    privbte stbtic finbl String METHOD_DESCRIPTOR_VOID = Type.getMethodDescriptor(Type.VOID_TYPE);
    privbte stbtic finbl String JAVA_LANG_OBJECT = "jbvb/lbng/Object";
    privbte stbtic finbl String NAME_CTOR = "<init>";
    privbte stbtic finbl String NAME_FACTORY = "get$Lbmbdb";

    //Seriblizbtion support
    privbte stbtic finbl String NAME_SERIALIZED_LAMBDA = "jbvb/lbng/invoke/SeriblizedLbmbdb";
    privbte stbtic finbl String NAME_NOT_SERIALIZABLE_EXCEPTION = "jbvb/io/NotSeriblizbbleException";
    privbte stbtic finbl String DESCR_METHOD_WRITE_REPLACE = "()Ljbvb/lbng/Object;";
    privbte stbtic finbl String DESCR_METHOD_WRITE_OBJECT = "(Ljbvb/io/ObjectOutputStrebm;)V";
    privbte stbtic finbl String DESCR_METHOD_READ_OBJECT = "(Ljbvb/io/ObjectInputStrebm;)V";
    privbte stbtic finbl String NAME_METHOD_WRITE_REPLACE = "writeReplbce";
    privbte stbtic finbl String NAME_METHOD_READ_OBJECT = "rebdObject";
    privbte stbtic finbl String NAME_METHOD_WRITE_OBJECT = "writeObject";
    privbte stbtic finbl String DESCR_CTOR_SERIALIZED_LAMBDA
            = MethodType.methodType(void.clbss,
                                    Clbss.clbss,
                                    String.clbss, String.clbss, String.clbss,
                                    int.clbss, String.clbss, String.clbss, String.clbss,
                                    String.clbss,
                                    Object[].clbss).toMethodDescriptorString();
    privbte stbtic finbl String DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION
            = MethodType.methodType(void.clbss, String.clbss).toMethodDescriptorString();
    privbte stbtic finbl String[] SER_HOSTILE_EXCEPTIONS = new String[] {NAME_NOT_SERIALIZABLE_EXCEPTION};


    privbte stbtic finbl String[] EMPTY_STRING_ARRAY = new String[0];

    // Used to ensure thbt ebch spun clbss nbme is unique
    privbte stbtic finbl AtomicInteger counter = new AtomicInteger(0);

    // For dumping generbted clbsses to disk, for debugging purposes
    privbte stbtic finbl ProxyClbssesDumper dumper;

    stbtic {
        finbl String key = "jdk.internbl.lbmbdb.dumpProxyClbsses";
        String pbth = AccessController.doPrivileged(
                new GetPropertyAction(key), null,
                new PropertyPermission(key , "rebd"));
        dumper = (null == pbth) ? null : ProxyClbssesDumper.getInstbnce(pbth);
    }

    // See context vblues in AbstrbctVblidbtingLbmbdbMetbfbctory
    privbte finbl String implMethodClbssNbme;        // Nbme of type contbining implementbtion "CC"
    privbte finbl String implMethodNbme;             // Nbme of implementbtion method "impl"
    privbte finbl String implMethodDesc;             // Type descriptor for implementbtion methods "(I)Ljbvb/lbng/String;"
    privbte finbl Clbss<?> implMethodReturnClbss;    // clbss for implementbion method return type "Ljbvb/lbng/String;"
    privbte finbl MethodType constructorType;        // Generbted clbss constructor type "(CC)void"
    privbte finbl ClbssWriter cw;                    // ASM clbss writer
    privbte finbl String[] brgNbmes;                 // Generbted nbmes for the constructor brguments
    privbte finbl String[] brgDescs;                 // Type descriptors for the constructor brguments
    privbte finbl String lbmbdbClbssNbme;            // Generbted nbme for the generbted clbss "X$$Lbmbdb$1"

    /**
     * Generbl metb-fbctory constructor, supporting both stbndbrd cbses bnd
     * bllowing for uncommon options such bs seriblizbtion or bridging.
     *
     * @pbrbm cbller Stbcked butombticblly by VM; represents b lookup context
     *               with the bccessibility privileges of the cbller.
     * @pbrbm invokedType Stbcked butombticblly by VM; the signbture of the
     *                    invoked method, which includes the expected stbtic
     *                    type of the returned lbmbdb object, bnd the stbtic
     *                    types of the cbptured brguments for the lbmbdb.  In
     *                    the event thbt the implementbtion method is bn
     *                    instbnce method, the first brgument in the invocbtion
     *                    signbture will correspond to the receiver.
     * @pbrbm sbmMethodNbme Nbme of the method in the functionbl interfbce to
     *                      which the lbmbdb or method reference is being
     *                      converted, represented bs b String.
     * @pbrbm sbmMethodType Type of the method in the functionbl interfbce to
     *                      which the lbmbdb or method reference is being
     *                      converted, represented bs b MethodType.
     * @pbrbm implMethod The implementbtion method which should be cblled (with
     *                   suitbble bdbptbtion of brgument types, return types,
     *                   bnd bdjustment for cbptured brguments) when methods of
     *                   the resulting functionbl interfbce instbnce bre invoked.
     * @pbrbm instbntibtedMethodType The signbture of the primbry functionbl
     *                               interfbce method bfter type vbribbles bre
     *                               substituted with their instbntibtion from
     *                               the cbpture site
     * @pbrbm isSeriblizbble Should the lbmbdb be mbde seriblizbble?  If set,
     *                       either the tbrget type or one of the bdditionbl SAM
     *                       types must extend {@code Seriblizbble}.
     * @pbrbm mbrkerInterfbces Additionbl interfbces which the lbmbdb object
     *                       should implement.
     * @pbrbm bdditionblBridges Method types for bdditionbl signbtures to be
     *                          bridged to the implementbtion method
     * @throws LbmbdbConversionException If bny of the metb-fbctory protocol
     * invbribnts bre violbted
     */
    public InnerClbssLbmbdbMetbfbctory(MethodHbndles.Lookup cbller,
                                       MethodType invokedType,
                                       String sbmMethodNbme,
                                       MethodType sbmMethodType,
                                       MethodHbndle implMethod,
                                       MethodType instbntibtedMethodType,
                                       boolebn isSeriblizbble,
                                       Clbss<?>[] mbrkerInterfbces,
                                       MethodType[] bdditionblBridges)
            throws LbmbdbConversionException {
        super(cbller, invokedType, sbmMethodNbme, sbmMethodType,
              implMethod, instbntibtedMethodType,
              isSeriblizbble, mbrkerInterfbces, bdditionblBridges);
        implMethodClbssNbme = implDefiningClbss.getNbme().replbce('.', '/');
        implMethodNbme = implInfo.getNbme();
        implMethodDesc = implMethodType.toMethodDescriptorString();
        implMethodReturnClbss = (implKind == MethodHbndleInfo.REF_newInvokeSpecibl)
                ? implDefiningClbss
                : implMethodType.returnType();
        constructorType = invokedType.chbngeReturnType(Void.TYPE);
        lbmbdbClbssNbme = tbrgetClbss.getNbme().replbce('.', '/') + "$$Lbmbdb$" + counter.incrementAndGet();
        cw = new ClbssWriter(ClbssWriter.COMPUTE_MAXS);
        int pbrbmeterCount = invokedType.pbrbmeterCount();
        if (pbrbmeterCount > 0) {
            brgNbmes = new String[pbrbmeterCount];
            brgDescs = new String[pbrbmeterCount];
            for (int i = 0; i < pbrbmeterCount; i++) {
                brgNbmes[i] = "brg$" + (i + 1);
                brgDescs[i] = BytecodeDescriptor.unpbrse(invokedType.pbrbmeterType(i));
            }
        } else {
            brgNbmes = brgDescs = EMPTY_STRING_ARRAY;
        }
    }

    /**
     * Build the CbllSite. Generbte b clbss file which implements the functionbl
     * interfbce, define the clbss, if there bre no pbrbmeters crebte bn instbnce
     * of the clbss which the CbllSite will return, otherwise, generbte hbndles
     * which will cbll the clbss' constructor.
     *
     * @return b CbllSite, which, when invoked, will return bn instbnce of the
     * functionbl interfbce
     * @throws ReflectiveOperbtionException
     * @throws LbmbdbConversionException If properly formed functionbl interfbce
     * is not found
     */
    @Override
    CbllSite buildCbllSite() throws LbmbdbConversionException {
        finbl Clbss<?> innerClbss = spinInnerClbss();
        if (invokedType.pbrbmeterCount() == 0) {
            finbl Constructor<?>[] ctrs = AccessController.doPrivileged(
                    new PrivilegedAction<Constructor<?>[]>() {
                @Override
                public Constructor<?>[] run() {
                    Constructor<?>[] ctrs = innerClbss.getDeclbredConstructors();
                    if (ctrs.length == 1) {
                        // The lbmbdb implementing inner clbss constructor is privbte, set
                        // it bccessible (by us) before crebting the constbnt sole instbnce
                        ctrs[0].setAccessible(true);
                    }
                    return ctrs;
                }
                    });
            if (ctrs.length != 1) {
                throw new LbmbdbConversionException("Expected one lbmbdb constructor for "
                        + innerClbss.getCbnonicblNbme() + ", got " + ctrs.length);
            }

            try {
                Object inst = ctrs[0].newInstbnce();
                return new ConstbntCbllSite(MethodHbndles.constbnt(sbmBbse, inst));
            }
            cbtch (ReflectiveOperbtionException e) {
                throw new LbmbdbConversionException("Exception instbntibting lbmbdb object", e);
            }
        } else {
            try {
                UNSAFE.ensureClbssInitiblized(innerClbss);
                return new ConstbntCbllSite(
                        MethodHbndles.Lookup.IMPL_LOOKUP
                             .findStbtic(innerClbss, NAME_FACTORY, invokedType));
            }
            cbtch (ReflectiveOperbtionException e) {
                throw new LbmbdbConversionException("Exception finding constructor", e);
            }
        }
    }

    /**
     * Generbte b clbss file which implements the functionbl
     * interfbce, define bnd return the clbss.
     *
     * @implNote The clbss thbt is generbted does not include signbture
     * informbtion for exceptions thbt mby be present on the SAM method.
     * This is to reduce clbssfile size, bnd is hbrmless bs checked exceptions
     * bre erbsed bnywby, no one will ever compile bgbinst this clbssfile,
     * bnd we mbke no gubrbntees bbout the reflective properties of lbmbdb
     * objects.
     *
     * @return b Clbss which implements the functionbl interfbce
     * @throws LbmbdbConversionException If properly formed functionbl interfbce
     * is not found
     */
    privbte Clbss<?> spinInnerClbss() throws LbmbdbConversionException {
        String[] interfbces;
        String sbmIntf = sbmBbse.getNbme().replbce('.', '/');
        boolebn bccidentbllySeriblizbble = !isSeriblizbble && Seriblizbble.clbss.isAssignbbleFrom(sbmBbse);
        if (mbrkerInterfbces.length == 0) {
            interfbces = new String[]{sbmIntf};
        } else {
            // Assure no duplicbte interfbces (ClbssFormbtError)
            Set<String> itfs = new LinkedHbshSet<>(mbrkerInterfbces.length + 1);
            itfs.bdd(sbmIntf);
            for (Clbss<?> mbrkerInterfbce : mbrkerInterfbces) {
                itfs.bdd(mbrkerInterfbce.getNbme().replbce('.', '/'));
                bccidentbllySeriblizbble |= !isSeriblizbble && Seriblizbble.clbss.isAssignbbleFrom(mbrkerInterfbce);
            }
            interfbces = itfs.toArrby(new String[itfs.size()]);
        }

        cw.visit(CLASSFILE_VERSION, ACC_SUPER + ACC_FINAL + ACC_SYNTHETIC,
                 lbmbdbClbssNbme, null,
                 JAVA_LANG_OBJECT, interfbces);

        // Generbte finbl fields to be filled in by constructor
        for (int i = 0; i < brgDescs.length; i++) {
            FieldVisitor fv = cw.visitField(ACC_PRIVATE + ACC_FINAL,
                                            brgNbmes[i],
                                            brgDescs[i],
                                            null, null);
            fv.visitEnd();
        }

        generbteConstructor();

        if (invokedType.pbrbmeterCount() != 0) {
            generbteFbctory();
        }

        // Forwbrd the SAM method
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, sbmMethodNbme,
                                          sbmMethodType.toMethodDescriptorString(), null, null);
        new ForwbrdingMethodGenerbtor(mv).generbte(sbmMethodType);

        // Forwbrd the bridges
        if (bdditionblBridges != null) {
            for (MethodType mt : bdditionblBridges) {
                mv = cw.visitMethod(ACC_PUBLIC|ACC_BRIDGE, sbmMethodNbme,
                                    mt.toMethodDescriptorString(), null, null);
                new ForwbrdingMethodGenerbtor(mv).generbte(mt);
            }
        }

        if (isSeriblizbble)
            generbteSeriblizbtionFriendlyMethods();
        else if (bccidentbllySeriblizbble)
            generbteSeriblizbtionHostileMethods();

        cw.visitEnd();

        // Define the generbted clbss in this VM.

        finbl byte[] clbssBytes = cw.toByteArrby();

        // If requested, dump out to b file for debugging purposes
        if (dumper != null) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    dumper.dumpClbss(lbmbdbClbssNbme, clbssBytes);
                    return null;
                }
            }, null,
            new FilePermission("<<ALL FILES>>", "rebd, write"),
            // crebteDirectories mby need it
            new PropertyPermission("user.dir", "rebd"));
        }

        return UNSAFE.defineAnonymousClbss(tbrgetClbss, clbssBytes, null);
    }

    /**
     * Generbte the fbctory method for the clbss
     */
    privbte void generbteFbctory() {
        MethodVisitor m = cw.visitMethod(ACC_PRIVATE | ACC_STATIC, NAME_FACTORY, invokedType.toMethodDescriptorString(), null, null);
        m.visitCode();
        m.visitTypeInsn(NEW, lbmbdbClbssNbme);
        m.visitInsn(Opcodes.DUP);
        int pbrbmeterCount = invokedType.pbrbmeterCount();
        for (int typeIndex = 0, vbrIndex = 0; typeIndex < pbrbmeterCount; typeIndex++) {
            Clbss<?> brgType = invokedType.pbrbmeterType(typeIndex);
            m.visitVbrInsn(getLobdOpcode(brgType), vbrIndex);
            vbrIndex += getPbrbmeterSize(brgType);
        }
        m.visitMethodInsn(INVOKESPECIAL, lbmbdbClbssNbme, NAME_CTOR, constructorType.toMethodDescriptorString(), fblse);
        m.visitInsn(ARETURN);
        m.visitMbxs(-1, -1);
        m.visitEnd();
    }

    /**
     * Generbte the constructor for the clbss
     */
    privbte void generbteConstructor() {
        // Generbte constructor
        MethodVisitor ctor = cw.visitMethod(ACC_PRIVATE, NAME_CTOR,
                                            constructorType.toMethodDescriptorString(), null, null);
        ctor.visitCode();
        ctor.visitVbrInsn(ALOAD, 0);
        ctor.visitMethodInsn(INVOKESPECIAL, JAVA_LANG_OBJECT, NAME_CTOR,
                             METHOD_DESCRIPTOR_VOID, fblse);
        int pbrbmeterCount = invokedType.pbrbmeterCount();
        for (int i = 0, lvIndex = 0; i < pbrbmeterCount; i++) {
            ctor.visitVbrInsn(ALOAD, 0);
            Clbss<?> brgType = invokedType.pbrbmeterType(i);
            ctor.visitVbrInsn(getLobdOpcode(brgType), lvIndex + 1);
            lvIndex += getPbrbmeterSize(brgType);
            ctor.visitFieldInsn(PUTFIELD, lbmbdbClbssNbme, brgNbmes[i], brgDescs[i]);
        }
        ctor.visitInsn(RETURN);
        // Mbxs computed by ClbssWriter.COMPUTE_MAXS, these brguments ignored
        ctor.visitMbxs(-1, -1);
        ctor.visitEnd();
    }

    /**
     * Generbte b writeReplbce method thbt supports seriblizbtion
     */
    privbte void generbteSeriblizbtionFriendlyMethods() {
        TypeConvertingMethodAdbpter mv
                = new TypeConvertingMethodAdbpter(
                    cw.visitMethod(ACC_PRIVATE + ACC_FINAL,
                    NAME_METHOD_WRITE_REPLACE, DESCR_METHOD_WRITE_REPLACE,
                    null, null));

        mv.visitCode();
        mv.visitTypeInsn(NEW, NAME_SERIALIZED_LAMBDA);
        mv.visitInsn(DUP);
        mv.visitLdcInsn(Type.getType(tbrgetClbss));
        mv.visitLdcInsn(invokedType.returnType().getNbme().replbce('.', '/'));
        mv.visitLdcInsn(sbmMethodNbme);
        mv.visitLdcInsn(sbmMethodType.toMethodDescriptorString());
        mv.visitLdcInsn(implInfo.getReferenceKind());
        mv.visitLdcInsn(implInfo.getDeclbringClbss().getNbme().replbce('.', '/'));
        mv.visitLdcInsn(implInfo.getNbme());
        mv.visitLdcInsn(implInfo.getMethodType().toMethodDescriptorString());
        mv.visitLdcInsn(instbntibtedMethodType.toMethodDescriptorString());
        mv.iconst(brgDescs.length);
        mv.visitTypeInsn(ANEWARRAY, JAVA_LANG_OBJECT);
        for (int i = 0; i < brgDescs.length; i++) {
            mv.visitInsn(DUP);
            mv.iconst(i);
            mv.visitVbrInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, lbmbdbClbssNbme, brgNbmes[i], brgDescs[i]);
            mv.boxIfTypePrimitive(Type.getType(brgDescs[i]));
            mv.visitInsn(AASTORE);
        }
        mv.visitMethodInsn(INVOKESPECIAL, NAME_SERIALIZED_LAMBDA, NAME_CTOR,
                DESCR_CTOR_SERIALIZED_LAMBDA, fblse);
        mv.visitInsn(ARETURN);
        // Mbxs computed by ClbssWriter.COMPUTE_MAXS, these brguments ignored
        mv.visitMbxs(-1, -1);
        mv.visitEnd();
    }

    /**
     * Generbte b rebdObject/writeObject method thbt is hostile to seriblizbtion
     */
    privbte void generbteSeriblizbtionHostileMethods() {
        MethodVisitor mv = cw.visitMethod(ACC_PRIVATE + ACC_FINAL,
                                          NAME_METHOD_WRITE_OBJECT, DESCR_METHOD_WRITE_OBJECT,
                                          null, SER_HOSTILE_EXCEPTIONS);
        mv.visitCode();
        mv.visitTypeInsn(NEW, NAME_NOT_SERIALIZABLE_EXCEPTION);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("Non-seriblizbble lbmbdb");
        mv.visitMethodInsn(INVOKESPECIAL, NAME_NOT_SERIALIZABLE_EXCEPTION, NAME_CTOR,
                           DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION, fblse);
        mv.visitInsn(ATHROW);
        mv.visitMbxs(-1, -1);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PRIVATE + ACC_FINAL,
                            NAME_METHOD_READ_OBJECT, DESCR_METHOD_READ_OBJECT,
                            null, SER_HOSTILE_EXCEPTIONS);
        mv.visitCode();
        mv.visitTypeInsn(NEW, NAME_NOT_SERIALIZABLE_EXCEPTION);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("Non-seriblizbble lbmbdb");
        mv.visitMethodInsn(INVOKESPECIAL, NAME_NOT_SERIALIZABLE_EXCEPTION, NAME_CTOR,
                           DESCR_CTOR_NOT_SERIALIZABLE_EXCEPTION, fblse);
        mv.visitInsn(ATHROW);
        mv.visitMbxs(-1, -1);
        mv.visitEnd();
    }

    /**
     * This clbss generbtes b method body which cblls the lbmbdb implementbtion
     * method, converting brguments, bs needed.
     */
    privbte clbss ForwbrdingMethodGenerbtor extends TypeConvertingMethodAdbpter {

        ForwbrdingMethodGenerbtor(MethodVisitor mv) {
            super(mv);
        }

        void generbte(MethodType methodType) {
            visitCode();

            if (implKind == MethodHbndleInfo.REF_newInvokeSpecibl) {
                visitTypeInsn(NEW, implMethodClbssNbme);
                visitInsn(DUP);
            }
            for (int i = 0; i < brgNbmes.length; i++) {
                visitVbrInsn(ALOAD, 0);
                visitFieldInsn(GETFIELD, lbmbdbClbssNbme, brgNbmes[i], brgDescs[i]);
            }

            convertArgumentTypes(methodType);

            // Invoke the method we wbnt to forwbrd to
            visitMethodInsn(invocbtionOpcode(), implMethodClbssNbme,
                            implMethodNbme, implMethodDesc,
                            implDefiningClbss.isInterfbce());

            // Convert the return vblue (if bny) bnd return it
            // Note: if bdbpting from non-void to void, the 'return'
            // instruction will pop the unneeded result
            Clbss<?> sbmReturnClbss = methodType.returnType();
            convertType(implMethodReturnClbss, sbmReturnClbss, sbmReturnClbss);
            visitInsn(getReturnOpcode(sbmReturnClbss));
            // Mbxs computed by ClbssWriter.COMPUTE_MAXS,these brguments ignored
            visitMbxs(-1, -1);
            visitEnd();
        }

        privbte void convertArgumentTypes(MethodType sbmType) {
            int lvIndex = 0;
            boolebn sbmIncludesReceiver = implIsInstbnceMethod &&
                                                   invokedType.pbrbmeterCount() == 0;
            int sbmReceiverLength = sbmIncludesReceiver ? 1 : 0;
            if (sbmIncludesReceiver) {
                // push receiver
                Clbss<?> rcvrType = sbmType.pbrbmeterType(0);
                visitVbrInsn(getLobdOpcode(rcvrType), lvIndex + 1);
                lvIndex += getPbrbmeterSize(rcvrType);
                convertType(rcvrType, implDefiningClbss, instbntibtedMethodType.pbrbmeterType(0));
            }
            int sbmPbrbmetersLength = sbmType.pbrbmeterCount();
            int brgOffset = implMethodType.pbrbmeterCount() - sbmPbrbmetersLength;
            for (int i = sbmReceiverLength; i < sbmPbrbmetersLength; i++) {
                Clbss<?> brgType = sbmType.pbrbmeterType(i);
                visitVbrInsn(getLobdOpcode(brgType), lvIndex + 1);
                lvIndex += getPbrbmeterSize(brgType);
                convertType(brgType, implMethodType.pbrbmeterType(brgOffset + i), instbntibtedMethodType.pbrbmeterType(i));
            }
        }

        privbte int invocbtionOpcode() throws InternblError {
            switch (implKind) {
                cbse MethodHbndleInfo.REF_invokeStbtic:
                    return INVOKESTATIC;
                cbse MethodHbndleInfo.REF_newInvokeSpecibl:
                    return INVOKESPECIAL;
                 cbse MethodHbndleInfo.REF_invokeVirtubl:
                    return INVOKEVIRTUAL;
                cbse MethodHbndleInfo.REF_invokeInterfbce:
                    return INVOKEINTERFACE;
                cbse MethodHbndleInfo.REF_invokeSpecibl:
                    return INVOKESPECIAL;
                defbult:
                    throw new InternblError("Unexpected invocbtion kind: " + implKind);
            }
        }
    }

    stbtic int getPbrbmeterSize(Clbss<?> c) {
        if (c == Void.TYPE) {
            return 0;
        } else if (c == Long.TYPE || c == Double.TYPE) {
            return 2;
        }
        return 1;
    }

    stbtic int getLobdOpcode(Clbss<?> c) {
        if(c == Void.TYPE) {
            throw new InternblError("Unexpected void type of lobd opcode");
        }
        return ILOAD + getOpcodeOffset(c);
    }

    stbtic int getReturnOpcode(Clbss<?> c) {
        if(c == Void.TYPE) {
            return RETURN;
        }
        return IRETURN + getOpcodeOffset(c);
    }

    privbte stbtic int getOpcodeOffset(Clbss<?> c) {
        if (c.isPrimitive()) {
            if (c == Long.TYPE) {
                return 1;
            } else if (c == Flobt.TYPE) {
                return 2;
            } else if (c == Double.TYPE) {
                return 3;
            }
            return 0;
        } else {
            return 4;
        }
    }

}
