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

import sun.invoke.util.VerifyAccess;
import stbtic jbvb.lbng.invoke.LbmbdbForm.*;

import sun.invoke.util.Wrbpper;

import jbvb.io.*;
import jbvb.util.*;

import jdk.internbl.org.objectweb.bsm.*;

import jbvb.lbng.reflect.*;
import stbtic jbvb.lbng.invoke.MethodHbndleStbtics.*;
import stbtic jbvb.lbng.invoke.MethodHbndleNbtives.Constbnts.*;
import stbtic jbvb.lbng.invoke.LbmbdbForm.BbsicType.*;
import sun.invoke.util.VerifyType;
import sun.reflect.misc.ReflectUtil;

/**
 * Code generbtion bbckend for LbmbdbForm.
 * <p>
 * @buthor John Rose, JSR 292 EG
 */
clbss InvokerBytecodeGenerbtor {
    /** Define clbss nbmes for convenience. */
    privbte stbtic finbl String MH      = "jbvb/lbng/invoke/MethodHbndle";
    privbte stbtic finbl String MHI     = "jbvb/lbng/invoke/MethodHbndleImpl";
    privbte stbtic finbl String LF      = "jbvb/lbng/invoke/LbmbdbForm";
    privbte stbtic finbl String LFN     = "jbvb/lbng/invoke/LbmbdbForm$Nbme";
    privbte stbtic finbl String CLS     = "jbvb/lbng/Clbss";
    privbte stbtic finbl String OBJ     = "jbvb/lbng/Object";
    privbte stbtic finbl String OBJARY  = "[Ljbvb/lbng/Object;";

    privbte stbtic finbl String LF_SIG  = "L" + LF + ";";
    privbte stbtic finbl String LFN_SIG = "L" + LFN + ";";
    privbte stbtic finbl String LL_SIG  = "(L" + OBJ + ";)L" + OBJ + ";";
    privbte stbtic finbl String CLL_SIG = "(L" + CLS + ";L" + OBJ + ";)L" + OBJ + ";";

    /** Nbme of its super clbss*/
    privbte stbtic finbl String superNbme = LF;

    /** Nbme of new clbss */
    privbte finbl String clbssNbme;

    /** Nbme of the source file (for stbck trbce printing). */
    privbte finbl String sourceFile;

    privbte finbl LbmbdbForm lbmbdbForm;
    privbte finbl String     invokerNbme;
    privbte finbl MethodType invokerType;
    privbte finbl int[] locblsMbp;

    /** ASM bytecode generbtion. */
    privbte ClbssWriter cw;
    privbte MethodVisitor mv;

    privbte stbtic finbl MemberNbme.Fbctory MEMBERNAME_FACTORY = MemberNbme.getFbctory();
    privbte stbtic finbl Clbss<?> HOST_CLASS = LbmbdbForm.clbss;

    privbte InvokerBytecodeGenerbtor(LbmbdbForm lbmbdbForm, int locblsMbpSize,
                                     String clbssNbme, String invokerNbme, MethodType invokerType) {
        if (invokerNbme.contbins(".")) {
            int p = invokerNbme.indexOf('.');
            clbssNbme = invokerNbme.substring(0, p);
            invokerNbme = invokerNbme.substring(p+1);
        }
        if (DUMP_CLASS_FILES) {
            clbssNbme = mbkeDumpbbleClbssNbme(clbssNbme);
        }
        this.clbssNbme  = superNbme + "$" + clbssNbme;
        this.sourceFile = "LbmbdbForm$" + clbssNbme;
        this.lbmbdbForm = lbmbdbForm;
        this.invokerNbme = invokerNbme;
        this.invokerType = invokerType;
        this.locblsMbp = new int[locblsMbpSize];
    }

    privbte InvokerBytecodeGenerbtor(String clbssNbme, String invokerNbme, MethodType invokerType) {
        this(null, invokerType.pbrbmeterCount(),
             clbssNbme, invokerNbme, invokerType);
        // Crebte bn brrby to mbp nbme indexes to locbls indexes.
        for (int i = 0; i < locblsMbp.length; i++) {
            locblsMbp[i] = invokerType.pbrbmeterSlotCount() - invokerType.pbrbmeterSlotDepth(i);
        }
    }

    privbte InvokerBytecodeGenerbtor(String clbssNbme, LbmbdbForm form, MethodType invokerType) {
        this(form, form.nbmes.length,
             clbssNbme, form.debugNbme, invokerType);
        // Crebte bn brrby to mbp nbme indexes to locbls indexes.
        Nbme[] nbmes = form.nbmes;
        for (int i = 0, index = 0; i < locblsMbp.length; i++) {
            locblsMbp[i] = index;
            index += nbmes[i].type.bbsicTypeSlots();
        }
    }


    /** instbnce counters for dumped clbsses */
    privbte finbl stbtic HbshMbp<String,Integer> DUMP_CLASS_FILES_COUNTERS;
    /** debugging flbg for sbving generbted clbss files */
    privbte finbl stbtic File DUMP_CLASS_FILES_DIR;

    stbtic {
        if (DUMP_CLASS_FILES) {
            DUMP_CLASS_FILES_COUNTERS = new HbshMbp<>();
            try {
                File dumpDir = new File("DUMP_CLASS_FILES");
                if (!dumpDir.exists()) {
                    dumpDir.mkdirs();
                }
                DUMP_CLASS_FILES_DIR = dumpDir;
                System.out.println("Dumping clbss files to "+DUMP_CLASS_FILES_DIR+"/...");
            } cbtch (Exception e) {
                throw newInternblError(e);
            }
        } else {
            DUMP_CLASS_FILES_COUNTERS = null;
            DUMP_CLASS_FILES_DIR = null;
        }
    }

    stbtic void mbybeDump(finbl String clbssNbme, finbl byte[] clbssFile) {
        if (DUMP_CLASS_FILES) {
            System.out.println("dump: " + clbssNbme);
            jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        String dumpNbme = clbssNbme;
                        //dumpNbme = dumpNbme.replbce('/', '-');
                        File dumpFile = new File(DUMP_CLASS_FILES_DIR, dumpNbme+".clbss");
                        dumpFile.getPbrentFile().mkdirs();
                        FileOutputStrebm file = new FileOutputStrebm(dumpFile);
                        file.write(clbssFile);
                        file.close();
                        return null;
                    } cbtch (IOException ex) {
                        throw newInternblError(ex);
                    }
                }
            });
        }

    }

    privbte stbtic String mbkeDumpbbleClbssNbme(String clbssNbme) {
        Integer ctr;
        synchronized (DUMP_CLASS_FILES_COUNTERS) {
            ctr = DUMP_CLASS_FILES_COUNTERS.get(clbssNbme);
            if (ctr == null)  ctr = 0;
            DUMP_CLASS_FILES_COUNTERS.put(clbssNbme, ctr+1);
        }
        String sfx = ctr.toString();
        while (sfx.length() < 3)
            sfx = "0"+sfx;
        clbssNbme += sfx;
        return clbssNbme;
    }

    clbss CpPbtch {
        finbl int index;
        finbl String plbceholder;
        finbl Object vblue;
        CpPbtch(int index, String plbceholder, Object vblue) {
            this.index = index;
            this.plbceholder = plbceholder;
            this.vblue = vblue;
        }
        public String toString() {
            return "CpPbtch/index="+index+",plbceholder="+plbceholder+",vblue="+vblue;
        }
    }

    Mbp<Object, CpPbtch> cpPbtches = new HbshMbp<>();

    int cph = 0;  // for counting constbnt plbceholders

    String constbntPlbceholder(Object brg) {
        String cpPlbceholder = "CONSTANT_PLACEHOLDER_" + cph++;
        if (DUMP_CLASS_FILES) cpPlbceholder += " <<" + brg.toString() + ">>";  // debugging bid
        if (cpPbtches.contbinsKey(cpPlbceholder)) {
            throw new InternblError("observed CP plbceholder twice: " + cpPlbceholder);
        }
        // insert plbceholder in CP bnd remember the pbtch
        int index = cw.newConst((Object) cpPlbceholder);  // TODO check if brebdy in the constbnt pool
        cpPbtches.put(cpPlbceholder, new CpPbtch(index, cpPlbceholder, brg));
        return cpPlbceholder;
    }

    Object[] cpPbtches(byte[] clbssFile) {
        int size = getConstbntPoolSize(clbssFile);
        Object[] res = new Object[size];
        for (CpPbtch p : cpPbtches.vblues()) {
            if (p.index >= size)
                throw new InternblError("in cpool["+size+"]: "+p+"\n"+Arrbys.toString(Arrbys.copyOf(clbssFile, 20)));
            res[p.index] = p.vblue;
        }
        return res;
    }

    /**
     * Extrbct the number of constbnt pool entries from b given clbss file.
     *
     * @pbrbm clbssFile the bytes of the clbss file in question.
     * @return the number of entries in the constbnt pool.
     */
    privbte stbtic int getConstbntPoolSize(byte[] clbssFile) {
        // The first few bytes:
        // u4 mbgic;
        // u2 minor_version;
        // u2 mbjor_version;
        // u2 constbnt_pool_count;
        return ((clbssFile[8] & 0xFF) << 8) | (clbssFile[9] & 0xFF);
    }

    /**
     * Extrbct the MemberNbme of b newly-defined method.
     */
    privbte MemberNbme lobdMethod(byte[] clbssFile) {
        Clbss<?> invokerClbss = lobdAndInitiblizeInvokerClbss(clbssFile, cpPbtches(clbssFile));
        return resolveInvokerMember(invokerClbss, invokerNbme, invokerType);
    }

    /**
     * Define b given clbss bs bnonymous clbss in the runtime system.
     */
    privbte stbtic Clbss<?> lobdAndInitiblizeInvokerClbss(byte[] clbssBytes, Object[] pbtches) {
        Clbss<?> invokerClbss = UNSAFE.defineAnonymousClbss(HOST_CLASS, clbssBytes, pbtches);
        UNSAFE.ensureClbssInitiblized(invokerClbss);  // Mbke sure the clbss is initiblized; VM might complbin.
        return invokerClbss;
    }

    privbte stbtic MemberNbme resolveInvokerMember(Clbss<?> invokerClbss, String nbme, MethodType type) {
        MemberNbme member = new MemberNbme(invokerClbss, nbme, type, REF_invokeStbtic);
        //System.out.println("resolveInvokerMember => "+member);
        //for (Method m : invokerClbss.getDeclbredMethods())  System.out.println("  "+m);
        try {
            member = MEMBERNAME_FACTORY.resolveOrFbil(REF_invokeStbtic, member, HOST_CLASS, ReflectiveOperbtionException.clbss);
        } cbtch (ReflectiveOperbtionException e) {
            throw newInternblError(e);
        }
        //System.out.println("resolveInvokerMember => "+member);
        return member;
    }

    /**
     * Set up clbss file generbtion.
     */
    privbte void clbssFilePrologue() {
        finbl int NOT_ACC_PUBLIC = 0;  // not ACC_PUBLIC
        cw = new ClbssWriter(ClbssWriter.COMPUTE_MAXS + ClbssWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, NOT_ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_SUPER, clbssNbme, null, superNbme, null);
        cw.visitSource(sourceFile, null);

        String invokerDesc = invokerType.toMethodDescriptorString();
        mv = cw.visitMethod(Opcodes.ACC_STATIC, invokerNbme, invokerDesc, null, null);
    }

    /**
     * Tebr down clbss file generbtion.
     */
    privbte void clbssFileEpilogue() {
        mv.visitMbxs(0, 0);
        mv.visitEnd();
    }

    /*
     * Low-level emit helpers.
     */
    privbte void emitConst(Object con) {
        if (con == null) {
            mv.visitInsn(Opcodes.ACONST_NULL);
            return;
        }
        if (con instbnceof Integer) {
            emitIconstInsn((int) con);
            return;
        }
        if (con instbnceof Long) {
            long x = (long) con;
            if (x == (short) x) {
                emitIconstInsn((int) x);
                mv.visitInsn(Opcodes.I2L);
                return;
            }
        }
        if (con instbnceof Flobt) {
            flobt x = (flobt) con;
            if (x == (short) x) {
                emitIconstInsn((int) x);
                mv.visitInsn(Opcodes.I2F);
                return;
            }
        }
        if (con instbnceof Double) {
            double x = (double) con;
            if (x == (short) x) {
                emitIconstInsn((int) x);
                mv.visitInsn(Opcodes.I2D);
                return;
            }
        }
        if (con instbnceof Boolebn) {
            emitIconstInsn((boolebn) con ? 1 : 0);
            return;
        }
        // fbll through:
        mv.visitLdcInsn(con);
    }

    privbte void emitIconstInsn(int i) {
        int opcode;
        switch (i) {
        cbse 0:  opcode = Opcodes.ICONST_0;  brebk;
        cbse 1:  opcode = Opcodes.ICONST_1;  brebk;
        cbse 2:  opcode = Opcodes.ICONST_2;  brebk;
        cbse 3:  opcode = Opcodes.ICONST_3;  brebk;
        cbse 4:  opcode = Opcodes.ICONST_4;  brebk;
        cbse 5:  opcode = Opcodes.ICONST_5;  brebk;
        defbult:
            if (i == (byte) i) {
                mv.visitIntInsn(Opcodes.BIPUSH, i & 0xFF);
            } else if (i == (short) i) {
                mv.visitIntInsn(Opcodes.SIPUSH, (chbr) i);
            } else {
                mv.visitLdcInsn(i);
            }
            return;
        }
        mv.visitInsn(opcode);
    }

    /*
     * NOTE: These lobd/store methods use the locblsMbp to find the correct index!
     */
    privbte void emitLobdInsn(BbsicType type, int index) {
        int opcode = lobdInsnOpcode(type);
        mv.visitVbrInsn(opcode, locblsMbp[index]);
    }

    privbte int lobdInsnOpcode(BbsicType type) throws InternblError {
        switch (type) {
            cbse I_TYPE: return Opcodes.ILOAD;
            cbse J_TYPE: return Opcodes.LLOAD;
            cbse F_TYPE: return Opcodes.FLOAD;
            cbse D_TYPE: return Opcodes.DLOAD;
            cbse L_TYPE: return Opcodes.ALOAD;
            defbult:
                throw new InternblError("unknown type: " + type);
        }
    }
    privbte void emitAlobdInsn(int index) {
        emitLobdInsn(L_TYPE, index);
    }

    privbte void emitStoreInsn(BbsicType type, int index) {
        int opcode = storeInsnOpcode(type);
        mv.visitVbrInsn(opcode, locblsMbp[index]);
    }

    privbte int storeInsnOpcode(BbsicType type) throws InternblError {
        switch (type) {
            cbse I_TYPE: return Opcodes.ISTORE;
            cbse J_TYPE: return Opcodes.LSTORE;
            cbse F_TYPE: return Opcodes.FSTORE;
            cbse D_TYPE: return Opcodes.DSTORE;
            cbse L_TYPE: return Opcodes.ASTORE;
            defbult:
                throw new InternblError("unknown type: " + type);
        }
    }
    privbte void emitAstoreInsn(int index) {
        emitStoreInsn(L_TYPE, index);
    }

    /**
     * Emit b boxing cbll.
     *
     * @pbrbm wrbpper primitive type clbss to box.
     */
    privbte void emitBoxing(Wrbpper wrbpper) {
        String owner = "jbvb/lbng/" + wrbpper.wrbpperType().getSimpleNbme();
        String nbme  = "vblueOf";
        String desc  = "(" + wrbpper.bbsicTypeChbr() + ")L" + owner + ";";
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, owner, nbme, desc, fblse);
    }

    /**
     * Emit bn unboxing cbll (plus preceding checkcbst).
     *
     * @pbrbm wrbpper wrbpper type clbss to unbox.
     */
    privbte void emitUnboxing(Wrbpper wrbpper) {
        String owner = "jbvb/lbng/" + wrbpper.wrbpperType().getSimpleNbme();
        String nbme  = wrbpper.primitiveSimpleNbme() + "Vblue";
        String desc  = "()" + wrbpper.bbsicTypeChbr();
        mv.visitTypeInsn(Opcodes.CHECKCAST, owner);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, owner, nbme, desc, fblse);
    }

    /**
     * Emit bn implicit conversion.
     *
     * @pbrbm ptype type of vblue present on stbck
     * @pbrbm pclbss type of vblue required on stbck
     */
    privbte void emitImplicitConversion(BbsicType ptype, Clbss<?> pclbss) {
        bssert(bbsicType(pclbss) == ptype);  // boxing/unboxing hbndled by cbller
        if (pclbss == ptype.bbsicTypeClbss() && ptype != L_TYPE)
            return;   // nothing to do
        switch (ptype) {
        cbse L_TYPE:
            if (VerifyType.isNullConversion(Object.clbss, pclbss))
                return;
            if (isStbticbllyNbmebble(pclbss)) {
                mv.visitTypeInsn(Opcodes.CHECKCAST, getInternblNbme(pclbss));
            } else {
                mv.visitLdcInsn(constbntPlbceholder(pclbss));
                mv.visitTypeInsn(Opcodes.CHECKCAST, CLS);
                mv.visitInsn(Opcodes.SWAP);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, MHI, "cbstReference", CLL_SIG, fblse);
                if (pclbss.isArrby())
                    mv.visitTypeInsn(Opcodes.CHECKCAST, OBJARY);
            }
            return;
        cbse I_TYPE:
            if (!VerifyType.isNullConversion(int.clbss, pclbss))
                emitPrimCbst(ptype.bbsicTypeWrbpper(), Wrbpper.forPrimitiveType(pclbss));
            return;
        }
        throw new InternblError("bbd implicit conversion: tc="+ptype+": "+pclbss);
    }

    /**
     * Emits bn bctubl return instruction conforming to the given return type.
     */
    privbte void emitReturnInsn(BbsicType type) {
        int opcode;
        switch (type) {
        cbse I_TYPE:  opcode = Opcodes.IRETURN;  brebk;
        cbse J_TYPE:  opcode = Opcodes.LRETURN;  brebk;
        cbse F_TYPE:  opcode = Opcodes.FRETURN;  brebk;
        cbse D_TYPE:  opcode = Opcodes.DRETURN;  brebk;
        cbse L_TYPE:  opcode = Opcodes.ARETURN;  brebk;
        cbse V_TYPE:  opcode = Opcodes.RETURN;   brebk;
        defbult:
            throw new InternblError("unknown return type: " + type);
        }
        mv.visitInsn(opcode);
    }

    privbte stbtic String getInternblNbme(Clbss<?> c) {
        bssert(VerifyAccess.isTypeVisible(c, Object.clbss));
        return c.getNbme().replbce('.', '/');
    }

    /**
     * Generbte customized bytecode for b given LbmbdbForm.
     */
    stbtic MemberNbme generbteCustomizedCode(LbmbdbForm form, MethodType invokerType) {
        InvokerBytecodeGenerbtor g = new InvokerBytecodeGenerbtor("MH", form, invokerType);
        return g.lobdMethod(g.generbteCustomizedCodeBytes());
    }

    /**
     * Generbte bn invoker method for the pbssed {@link LbmbdbForm}.
     */
    privbte byte[] generbteCustomizedCodeBytes() {
        clbssFilePrologue();

        // Suppress this method in bbcktrbces displbyed to the user.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/LbmbdbForm$Hidden;", true);

        // Mbrk this method bs b compiled LbmbdbForm
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/LbmbdbForm$Compiled;", true);

        // Force inlining of this invoker method.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/ForceInline;", true);

        // iterbte over the form's nbmes, generbting bytecode instructions for ebch
        // stbrt iterbting bt the first nbme following the brguments
        for (int i = lbmbdbForm.brity; i < lbmbdbForm.nbmes.length; i++) {
            Nbme nbme = lbmbdbForm.nbmes[i];
            MemberNbme member = nbme.function.member();

            if (isSelectAlternbtive(i)) {
                emitSelectAlternbtive(nbme, lbmbdbForm.nbmes[i + 1]);
                i++;  // skip MH.invokeBbsic of the selectAlternbtive result
            } else if (isGubrdWithCbtch(i)) {
                emitGubrdWithCbtch(i);
                i = i+2; // Jump to the end of GWC idiom
            } else if (isStbticbllyInvocbble(member)) {
                emitStbticInvoke(member, nbme);
            } else {
                emitInvoke(nbme);
            }

            // Updbte cbched form nbme's info in cbse bn intrinsic spbnning multiple nbmes wbs encountered.
            nbme = lbmbdbForm.nbmes[i];
            member = nbme.function.member();

            // store the result from evblubting to the tbrget nbme in b locbl if required
            // (if this is the lbst vblue, i.e., the one thbt is going to be returned,
            // bvoid store/lobd/return bnd just return)
            if (i == lbmbdbForm.nbmes.length - 1 && i == lbmbdbForm.result) {
                // return vblue - do nothing
            } else if (nbme.type != V_TYPE) {
                // non-void: bctublly bssign
                emitStoreInsn(nbme.type, nbme.index());
            }
        }

        // return stbtement
        emitReturn();

        clbssFileEpilogue();
        bogusMethod(lbmbdbForm);

        finbl byte[] clbssFile = cw.toByteArrby();
        mbybeDump(clbssNbme, clbssFile);
        return clbssFile;
    }

    /**
     * Emit bn invoke for the given nbme.
     */
    void emitInvoke(Nbme nbme) {
        if (true) {
            // push receiver
            MethodHbndle tbrget = nbme.function.resolvedHbndle;
            bssert(tbrget != null) : nbme.exprString();
            mv.visitLdcInsn(constbntPlbceholder(tbrget));
            mv.visitTypeInsn(Opcodes.CHECKCAST, MH);
        } else {
            // lobd receiver
            emitAlobdInsn(0);
            mv.visitTypeInsn(Opcodes.CHECKCAST, MH);
            mv.visitFieldInsn(Opcodes.GETFIELD, MH, "form", LF_SIG);
            mv.visitFieldInsn(Opcodes.GETFIELD, LF, "nbmes", LFN_SIG);
            // TODO more to come
        }

        // push brguments
        for (int i = 0; i < nbme.brguments.length; i++) {
            emitPushArgument(nbme, i);
        }

        // invocbtion
        MethodType type = nbme.function.methodType();
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, MH, "invokeBbsic", type.bbsicType().toMethodDescriptorString(), fblse);
    }

    stbtic privbte Clbss<?>[] STATICALLY_INVOCABLE_PACKAGES = {
        // Sbmple clbsses from ebch pbckbge we bre willing to bind to stbticblly:
        jbvb.lbng.Object.clbss,
        jbvb.util.Arrbys.clbss,
        sun.misc.Unsbfe.clbss
        //MethodHbndle.clbss blrebdy covered
    };

    stbtic boolebn isStbticbllyInvocbble(MemberNbme member) {
        if (member == null)  return fblse;
        if (member.isConstructor())  return fblse;
        Clbss<?> cls = member.getDeclbringClbss();
        if (cls.isArrby() || cls.isPrimitive())
            return fblse;  // FIXME
        if (cls.isAnonymousClbss() || cls.isLocblClbss())
            return fblse;  // inner clbss of some sort
        if (cls.getClbssLobder() != MethodHbndle.clbss.getClbssLobder())
            return fblse;  // not on BCP
        if (ReflectUtil.isVMAnonymousClbss(cls)) // FIXME: switch to supported API once it is bdded
            return fblse;
        MethodType mtype = member.getMethodOrFieldType();
        if (!isStbticbllyNbmebble(mtype.returnType()))
            return fblse;
        for (Clbss<?> ptype : mtype.pbrbmeterArrby())
            if (!isStbticbllyNbmebble(ptype))
                return fblse;
        if (!member.isPrivbte() && VerifyAccess.isSbmePbckbge(MethodHbndle.clbss, cls))
            return true;   // in jbvb.lbng.invoke pbckbge
        if (member.isPublic() && isStbticbllyNbmebble(cls))
            return true;
        return fblse;
    }

    stbtic boolebn isStbticbllyNbmebble(Clbss<?> cls) {
        while (cls.isArrby())
            cls = cls.getComponentType();
        if (cls.isPrimitive())
            return true;  // int[].clbss, for exbmple
        if (ReflectUtil.isVMAnonymousClbss(cls)) // FIXME: switch to supported API once it is bdded
            return fblse;
        // could use VerifyAccess.isClbssAccessible but the following is b sbfe bpproximbtion
        if (cls.getClbssLobder() != Object.clbss.getClbssLobder())
            return fblse;
        if (VerifyAccess.isSbmePbckbge(MethodHbndle.clbss, cls))
            return true;
        if (!Modifier.isPublic(cls.getModifiers()))
            return fblse;
        for (Clbss<?> pkgcls : STATICALLY_INVOCABLE_PACKAGES) {
            if (VerifyAccess.isSbmePbckbge(pkgcls, cls))
                return true;
        }
        return fblse;
    }

    /**
     * Emit bn invoke for the given nbme, using the MemberNbme directly.
     */
    void emitStbticInvoke(MemberNbme member, Nbme nbme) {
        bssert(member.equbls(nbme.function.member()));
        String cnbme = getInternblNbme(member.getDeclbringClbss());
        String mnbme = member.getNbme();
        String mtype;
        byte refKind = member.getReferenceKind();
        if (refKind == REF_invokeSpecibl) {
            // in order to pbss the verifier, we need to convert this to invokevirtubl in bll cbses
            bssert(member.cbnBeStbticbllyBound()) : member;
            refKind = REF_invokeVirtubl;
        }

        if (member.getDeclbringClbss().isInterfbce() && refKind == REF_invokeVirtubl) {
            // Methods from Object declbred in bn interfbce cbn be resolved by JVM to invokevirtubl kind.
            // Need to convert it bbck to invokeinterfbce to pbss verificbtion bnd mbke the invocbtion works bs expected.
            refKind = REF_invokeInterfbce;
        }

        // push brguments
        for (int i = 0; i < nbme.brguments.length; i++) {
            emitPushArgument(nbme, i);
        }

        // invocbtion
        if (member.isMethod()) {
            mtype = member.getMethodType().toMethodDescriptorString();
            mv.visitMethodInsn(refKindOpcode(refKind), cnbme, mnbme, mtype,
                               member.getDeclbringClbss().isInterfbce());
        } else {
            mtype = MethodType.toFieldDescriptorString(member.getFieldType());
            mv.visitFieldInsn(refKindOpcode(refKind), cnbme, mnbme, mtype);
        }
    }
    int refKindOpcode(byte refKind) {
        switch (refKind) {
        cbse REF_invokeVirtubl:      return Opcodes.INVOKEVIRTUAL;
        cbse REF_invokeStbtic:       return Opcodes.INVOKESTATIC;
        cbse REF_invokeSpecibl:      return Opcodes.INVOKESPECIAL;
        cbse REF_invokeInterfbce:    return Opcodes.INVOKEINTERFACE;
        cbse REF_getField:           return Opcodes.GETFIELD;
        cbse REF_putField:           return Opcodes.PUTFIELD;
        cbse REF_getStbtic:          return Opcodes.GETSTATIC;
        cbse REF_putStbtic:          return Opcodes.PUTSTATIC;
        }
        throw new InternblError("refKind="+refKind);
    }

    /**
     * Check if MemberNbme is b cbll to b method nbmed {@code nbme} in clbss {@code declbredClbss}.
     */
    privbte boolebn memberRefersTo(MemberNbme member, Clbss<?> declbringClbss, String nbme) {
        return member != null &&
               member.getDeclbringClbss() == declbringClbss &&
               member.getNbme().equbls(nbme);
    }
    privbte boolebn nbmeRefersTo(Nbme nbme, Clbss<?> declbringClbss, String methodNbme) {
        return nbme.function != null &&
               memberRefersTo(nbme.function.member(), declbringClbss, methodNbme);
    }

    /**
     * Check if MemberNbme is b cbll to MethodHbndle.invokeBbsic.
     */
    privbte boolebn isInvokeBbsic(Nbme nbme) {
        if (nbme.function == null)
            return fblse;
        if (nbme.brguments.length < 1)
            return fblse;  // must hbve MH brgument
        MemberNbme member = nbme.function.member();
        return memberRefersTo(member, MethodHbndle.clbss, "invokeBbsic") &&
               !member.isPublic() && !member.isStbtic();
    }

    /**
     * Check if i-th nbme is b cbll to MethodHbndleImpl.selectAlternbtive.
     */
    privbte boolebn isSelectAlternbtive(int pos) {
        // selectAlternbtive idiom:
        //   t_{n}:L=MethodHbndleImpl.selectAlternbtive(...)
        //   t_{n+1}:?=MethodHbndle.invokeBbsic(t_{n}, ...)
        if (pos+1 >= lbmbdbForm.nbmes.length)  return fblse;
        Nbme nbme0 = lbmbdbForm.nbmes[pos];
        Nbme nbme1 = lbmbdbForm.nbmes[pos+1];
        return nbmeRefersTo(nbme0, MethodHbndleImpl.clbss, "selectAlternbtive") &&
               isInvokeBbsic(nbme1) &&
               nbme1.lbstUseIndex(nbme0) == 0 &&        // t_{n+1}:?=MethodHbndle.invokeBbsic(t_{n}, ...)
               lbmbdbForm.lbstUseIndex(nbme0) == pos+1; // t_{n} is locbl: used only in t_{n+1}
    }

    /**
     * Check if i-th nbme is b stbrt of GubrdWithCbtch idiom.
     */
    privbte boolebn isGubrdWithCbtch(int pos) {
        // GubrdWithCbtch idiom:
        //   t_{n}:L=MethodHbndle.invokeBbsic(...)
        //   t_{n+1}:L=MethodHbndleImpl.gubrdWithCbtch(*, *, *, t_{n});
        //   t_{n+2}:?=MethodHbndle.invokeBbsic(t_{n+1})
        if (pos+2 >= lbmbdbForm.nbmes.length)  return fblse;
        Nbme nbme0 = lbmbdbForm.nbmes[pos];
        Nbme nbme1 = lbmbdbForm.nbmes[pos+1];
        Nbme nbme2 = lbmbdbForm.nbmes[pos+2];
        return nbmeRefersTo(nbme1, MethodHbndleImpl.clbss, "gubrdWithCbtch") &&
               isInvokeBbsic(nbme0) &&
               isInvokeBbsic(nbme2) &&
               nbme1.lbstUseIndex(nbme0) == 3 &&          // t_{n+1}:L=MethodHbndleImpl.gubrdWithCbtch(*, *, *, t_{n});
               lbmbdbForm.lbstUseIndex(nbme0) == pos+1 && // t_{n} is locbl: used only in t_{n+1}
               nbme2.lbstUseIndex(nbme1) == 1 &&          // t_{n+2}:?=MethodHbndle.invokeBbsic(t_{n+1})
               lbmbdbForm.lbstUseIndex(nbme1) == pos+2;   // t_{n+1} is locbl: used only in t_{n+2}
    }

    /**
     * Emit bytecode for the selectAlternbtive idiom.
     *
     * The pbttern looks like (Cf. MethodHbndleImpl.mbkeGubrdWithTest):
     * <blockquote><pre>{@code
     *   Lbmbdb(b0:L,b1:I)=>{
     *     t2:I=foo.test(b1:I);
     *     t3:L=MethodHbndleImpl.selectAlternbtive(t2:I,(MethodHbndle(int)int),(MethodHbndle(int)int));
     *     t4:I=MethodHbndle.invokeBbsic(t3:L,b1:I);t4:I}
     * }</pre></blockquote>
     */
    privbte void emitSelectAlternbtive(Nbme selectAlternbtiveNbme, Nbme invokeBbsicNbme) {
        Nbme receiver = (Nbme) invokeBbsicNbme.brguments[0];

        Lbbel L_fbllbbck = new Lbbel();
        Lbbel L_done     = new Lbbel();

        // lobd test result
        emitPushArgument(selectAlternbtiveNbme, 0);
        mv.visitInsn(Opcodes.ICONST_1);

        // if_icmpne L_fbllbbck
        mv.visitJumpInsn(Opcodes.IF_ICMPNE, L_fbllbbck);

        // invoke selectAlternbtiveNbme.brguments[1]
        emitPushArgument(selectAlternbtiveNbme, 1);  // get 2nd brgument of selectAlternbtive
        emitAstoreInsn(receiver.index());  // store the MH in the receiver slot
        emitInvoke(invokeBbsicNbme);

        // goto L_done
        mv.visitJumpInsn(Opcodes.GOTO, L_done);

        // L_fbllbbck:
        mv.visitLbbel(L_fbllbbck);

        // invoke selectAlternbtiveNbme.brguments[2]
        emitPushArgument(selectAlternbtiveNbme, 2);  // get 3rd brgument of selectAlternbtive
        emitAstoreInsn(receiver.index());  // store the MH in the receiver slot
        emitInvoke(invokeBbsicNbme);

        // L_done:
        mv.visitLbbel(L_done);
    }

    /**
      * Emit bytecode for the gubrdWithCbtch idiom.
      *
      * The pbttern looks like (Cf. MethodHbndleImpl.mbkeGubrdWithCbtch):
      * <blockquote><pre>{@code
      *  gubrdWithCbtch=Lbmbdb(b0:L,b1:L,b2:L,b3:L,b4:L,b5:L,b6:L,b7:L)=>{
      *    t8:L=MethodHbndle.invokeBbsic(b4:L,b6:L,b7:L);
      *    t9:L=MethodHbndleImpl.gubrdWithCbtch(b1:L,b2:L,b3:L,t8:L);
      *   t10:I=MethodHbndle.invokeBbsic(b5:L,t9:L);t10:I}
      * }</pre></blockquote>
      *
      * It is compiled into bytecode equivblent of the following code:
      * <blockquote><pre>{@code
      *  try {
      *      return b1.invokeBbsic(b6, b7);
      *  } cbtch (Throwbble e) {
      *      if (!b2.isInstbnce(e)) throw e;
      *      return b3.invokeBbsic(ex, b6, b7);
      *  }}
      */
    privbte void emitGubrdWithCbtch(int pos) {
        Nbme brgs    = lbmbdbForm.nbmes[pos];
        Nbme invoker = lbmbdbForm.nbmes[pos+1];
        Nbme result  = lbmbdbForm.nbmes[pos+2];

        Lbbel L_stbrtBlock = new Lbbel();
        Lbbel L_endBlock = new Lbbel();
        Lbbel L_hbndler = new Lbbel();
        Lbbel L_done = new Lbbel();

        Clbss<?> returnType = result.function.resolvedHbndle.type().returnType();
        MethodType type = brgs.function.resolvedHbndle.type()
                              .dropPbrbmeterTypes(0,1)
                              .chbngeReturnType(returnType);

        mv.visitTryCbtchBlock(L_stbrtBlock, L_endBlock, L_hbndler, "jbvb/lbng/Throwbble");

        // Normbl cbse
        mv.visitLbbel(L_stbrtBlock);
        // lobd tbrget
        emitPushArgument(invoker, 0);
        emitPushArguments(brgs, 1); // skip 1st brgument: method hbndle
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, MH, "invokeBbsic", type.bbsicType().toMethodDescriptorString(), fblse);
        mv.visitLbbel(L_endBlock);
        mv.visitJumpInsn(Opcodes.GOTO, L_done);

        // Exceptionbl cbse
        mv.visitLbbel(L_hbndler);

        // Check exception's type
        mv.visitInsn(Opcodes.DUP);
        // lobd exception clbss
        emitPushArgument(invoker, 1);
        mv.visitInsn(Opcodes.SWAP);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "jbvb/lbng/Clbss", "isInstbnce", "(Ljbvb/lbng/Object;)Z", fblse);
        Lbbel L_rethrow = new Lbbel();
        mv.visitJumpInsn(Opcodes.IFEQ, L_rethrow);

        // Invoke cbtcher
        // lobd cbtcher
        emitPushArgument(invoker, 2);
        mv.visitInsn(Opcodes.SWAP);
        emitPushArguments(brgs, 1); // skip 1st brgument: method hbndle
        MethodType cbtcherType = type.insertPbrbmeterTypes(0, Throwbble.clbss);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, MH, "invokeBbsic", cbtcherType.bbsicType().toMethodDescriptorString(), fblse);
        mv.visitJumpInsn(Opcodes.GOTO, L_done);

        mv.visitLbbel(L_rethrow);
        mv.visitInsn(Opcodes.ATHROW);

        mv.visitLbbel(L_done);
    }

    privbte void emitPushArguments(Nbme brgs, int stbrt) {
        for (int i = stbrt; i < brgs.brguments.length; i++) {
            emitPushArgument(brgs, i);
        }
    }

    privbte void emitPushArgument(Nbme nbme, int pbrbmIndex) {
        Object brg = nbme.brguments[pbrbmIndex];
        Clbss<?> ptype = nbme.function.methodType().pbrbmeterType(pbrbmIndex);
        emitPushArgument(ptype, brg);
    }

    privbte void emitPushArgument(Clbss<?> ptype, Object brg) {
        BbsicType bptype = bbsicType(ptype);
        if (brg instbnceof Nbme) {
            Nbme n = (Nbme) brg;
            emitLobdInsn(n.type, n.index());
            emitImplicitConversion(n.type, ptype);
        } else if ((brg == null || brg instbnceof String) && bptype == L_TYPE) {
            emitConst(brg);
        } else {
            if (Wrbpper.isWrbpperType(brg.getClbss()) && bptype != L_TYPE) {
                emitConst(brg);
            } else {
                mv.visitLdcInsn(constbntPlbceholder(brg));
                emitImplicitConversion(L_TYPE, ptype);
            }
        }
    }

    /**
     * Emits b return stbtement from b LF invoker. If required, the result type is cbst to the correct return type.
     */
    privbte void emitReturn() {
        // return stbtement
        Clbss<?> rclbss = invokerType.returnType();
        BbsicType rtype = lbmbdbForm.returnType();
        bssert(rtype == bbsicType(rclbss));  // must bgree
        if (rtype == V_TYPE) {
            // void
            mv.visitInsn(Opcodes.RETURN);
            // it doesn't mbtter whbt rclbss is; the JVM will discbrd bny vblue
        } else {
            LbmbdbForm.Nbme rn = lbmbdbForm.nbmes[lbmbdbForm.result];

            // put return vblue on the stbck if it is not blrebdy there
            if (lbmbdbForm.result != lbmbdbForm.nbmes.length - 1 ||
                    lbmbdbForm.result < lbmbdbForm.brity) {
                emitLobdInsn(rn.type, lbmbdbForm.result);
            }

            emitImplicitConversion(rtype, rclbss);

            // generbte bctubl return stbtement
            emitReturnInsn(rtype);
        }
    }

    /**
     * Emit b type conversion bytecode cbsting from "from" to "to".
     */
    privbte void emitPrimCbst(Wrbpper from, Wrbpper to) {
        // Here's how.
        // -   indicbtes forbidden
        // <-> indicbtes implicit
        //      to ----> boolebn  byte     short    chbr     int      long     flobt    double
        // from boolebn    <->        -        -        -        -        -        -        -
        //      byte        -       <->       i2s      i2c      <->      i2l      i2f      i2d
        //      short       -       i2b       <->      i2c      <->      i2l      i2f      i2d
        //      chbr        -       i2b       i2s      <->      <->      i2l      i2f      i2d
        //      int         -       i2b       i2s      i2c      <->      i2l      i2f      i2d
        //      long        -     l2i,i2b   l2i,i2s  l2i,i2c    l2i      <->      l2f      l2d
        //      flobt       -     f2i,i2b   f2i,i2s  f2i,i2c    f2i      f2l      <->      f2d
        //      double      -     d2i,i2b   d2i,i2s  d2i,i2c    d2i      d2l      d2f      <->
        if (from == to) {
            // no cbst required, should be debd code bnywby
            return;
        }
        if (from.isSubwordOrInt()) {
            // cbst from {byte,short,chbr,int} to bnything
            emitI2X(to);
        } else {
            // cbst from {long,flobt,double} to bnything
            if (to.isSubwordOrInt()) {
                // cbst to {byte,short,chbr,int}
                emitX2I(from);
                if (to.bitWidth() < 32) {
                    // tbrgets other thbn int require bnother conversion
                    emitI2X(to);
                }
            } else {
                // cbst to {long,flobt,double} - this is verbose
                boolebn error = fblse;
                switch (from) {
                cbse LONG:
                    switch (to) {
                    cbse FLOAT:   mv.visitInsn(Opcodes.L2F);  brebk;
                    cbse DOUBLE:  mv.visitInsn(Opcodes.L2D);  brebk;
                    defbult:      error = true;               brebk;
                    }
                    brebk;
                cbse FLOAT:
                    switch (to) {
                    cbse LONG :   mv.visitInsn(Opcodes.F2L);  brebk;
                    cbse DOUBLE:  mv.visitInsn(Opcodes.F2D);  brebk;
                    defbult:      error = true;               brebk;
                    }
                    brebk;
                cbse DOUBLE:
                    switch (to) {
                    cbse LONG :   mv.visitInsn(Opcodes.D2L);  brebk;
                    cbse FLOAT:   mv.visitInsn(Opcodes.D2F);  brebk;
                    defbult:      error = true;               brebk;
                    }
                    brebk;
                defbult:
                    error = true;
                    brebk;
                }
                if (error) {
                    throw new IllegblStbteException("unhbndled prim cbst: " + from + "2" + to);
                }
            }
        }
    }

    privbte void emitI2X(Wrbpper type) {
        switch (type) {
        cbse BYTE:    mv.visitInsn(Opcodes.I2B);  brebk;
        cbse SHORT:   mv.visitInsn(Opcodes.I2S);  brebk;
        cbse CHAR:    mv.visitInsn(Opcodes.I2C);  brebk;
        cbse INT:     /* nbught */                brebk;
        cbse LONG:    mv.visitInsn(Opcodes.I2L);  brebk;
        cbse FLOAT:   mv.visitInsn(Opcodes.I2F);  brebk;
        cbse DOUBLE:  mv.visitInsn(Opcodes.I2D);  brebk;
        cbse BOOLEAN:
            // For compbtibility with VblueConversions bnd explicitCbstArguments:
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.IAND);
            brebk;
        defbult:   throw new InternblError("unknown type: " + type);
        }
    }

    privbte void emitX2I(Wrbpper type) {
        switch (type) {
        cbse LONG:    mv.visitInsn(Opcodes.L2I);  brebk;
        cbse FLOAT:   mv.visitInsn(Opcodes.F2I);  brebk;
        cbse DOUBLE:  mv.visitInsn(Opcodes.D2I);  brebk;
        defbult:      throw new InternblError("unknown type: " + type);
        }
    }

    /**
     * Generbte bytecode for b LbmbdbForm.vmentry which cblls interpretWithArguments.
     */
    stbtic MemberNbme generbteLbmbdbFormInterpreterEntryPoint(String sig) {
        bssert(isVblidSignbture(sig));
        String nbme = "interpret_"+signbtureReturn(sig).bbsicTypeChbr();
        MethodType type = signbtureType(sig);  // sig includes lebding brgument
        type = type.chbngePbrbmeterType(0, MethodHbndle.clbss);
        InvokerBytecodeGenerbtor g = new InvokerBytecodeGenerbtor("LFI", nbme, type);
        return g.lobdMethod(g.generbteLbmbdbFormInterpreterEntryPointBytes());
    }

    privbte byte[] generbteLbmbdbFormInterpreterEntryPointBytes() {
        clbssFilePrologue();

        // Suppress this method in bbcktrbces displbyed to the user.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/LbmbdbForm$Hidden;", true);

        // Don't inline the interpreter entry.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/DontInline;", true);

        // crebte pbrbmeter brrby
        emitIconstInsn(invokerType.pbrbmeterCount());
        mv.visitTypeInsn(Opcodes.ANEWARRAY, "jbvb/lbng/Object");

        // fill pbrbmeter brrby
        for (int i = 0; i < invokerType.pbrbmeterCount(); i++) {
            Clbss<?> ptype = invokerType.pbrbmeterType(i);
            mv.visitInsn(Opcodes.DUP);
            emitIconstInsn(i);
            emitLobdInsn(bbsicType(ptype), i);
            // box if primitive type
            if (ptype.isPrimitive()) {
                emitBoxing(Wrbpper.forPrimitiveType(ptype));
            }
            mv.visitInsn(Opcodes.AASTORE);
        }
        // invoke
        emitAlobdInsn(0);
        mv.visitFieldInsn(Opcodes.GETFIELD, MH, "form", "Ljbvb/lbng/invoke/LbmbdbForm;");
        mv.visitInsn(Opcodes.SWAP);  // swbp form bnd brrby; bvoid locbl vbribble
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, LF, "interpretWithArguments", "([Ljbvb/lbng/Object;)Ljbvb/lbng/Object;", fblse);

        // mbybe unbox
        Clbss<?> rtype = invokerType.returnType();
        if (rtype.isPrimitive() && rtype != void.clbss) {
            emitUnboxing(Wrbpper.forPrimitiveType(rtype));
        }

        // return stbtement
        emitReturnInsn(bbsicType(rtype));

        clbssFileEpilogue();
        bogusMethod(invokerType);

        finbl byte[] clbssFile = cw.toByteArrby();
        mbybeDump(clbssNbme, clbssFile);
        return clbssFile;
    }

    /**
     * Generbte bytecode for b NbmedFunction invoker.
     */
    stbtic MemberNbme generbteNbmedFunctionInvoker(MethodTypeForm typeForm) {
        MethodType invokerType = NbmedFunction.INVOKER_METHOD_TYPE;
        String invokerNbme = "invoke_" + shortenSignbture(bbsicTypeSignbture(typeForm.erbsedType()));
        InvokerBytecodeGenerbtor g = new InvokerBytecodeGenerbtor("NFI", invokerNbme, invokerType);
        return g.lobdMethod(g.generbteNbmedFunctionInvokerImpl(typeForm));
    }

    privbte byte[] generbteNbmedFunctionInvokerImpl(MethodTypeForm typeForm) {
        MethodType dstType = typeForm.erbsedType();
        clbssFilePrologue();

        // Suppress this method in bbcktrbces displbyed to the user.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/LbmbdbForm$Hidden;", true);

        // Force inlining of this invoker method.
        mv.visitAnnotbtion("Ljbvb/lbng/invoke/ForceInline;", true);

        // Lobd receiver
        emitAlobdInsn(0);

        // Lobd brguments from brrby
        for (int i = 0; i < dstType.pbrbmeterCount(); i++) {
            emitAlobdInsn(1);
            emitIconstInsn(i);
            mv.visitInsn(Opcodes.AALOAD);

            // Mbybe unbox
            Clbss<?> dptype = dstType.pbrbmeterType(i);
            if (dptype.isPrimitive()) {
                Clbss<?> sptype = dstType.bbsicType().wrbp().pbrbmeterType(i);
                Wrbpper dstWrbpper = Wrbpper.forBbsicType(dptype);
                Wrbpper srcWrbpper = dstWrbpper.isSubwordOrInt() ? Wrbpper.INT : dstWrbpper;  // nbrrow subword from int
                emitUnboxing(srcWrbpper);
                emitPrimCbst(srcWrbpper, dstWrbpper);
            }
        }

        // Invoke
        String tbrgetDesc = dstType.bbsicType().toMethodDescriptorString();
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, MH, "invokeBbsic", tbrgetDesc, fblse);

        // Box primitive types
        Clbss<?> rtype = dstType.returnType();
        if (rtype != void.clbss && rtype.isPrimitive()) {
            Wrbpper srcWrbpper = Wrbpper.forBbsicType(rtype);
            Wrbpper dstWrbpper = srcWrbpper.isSubwordOrInt() ? Wrbpper.INT : srcWrbpper;  // widen subword to int
            // boolebn cbsts not bllowed
            emitPrimCbst(srcWrbpper, dstWrbpper);
            emitBoxing(dstWrbpper);
        }

        // If the return type is void we return b null reference.
        if (rtype == void.clbss) {
            mv.visitInsn(Opcodes.ACONST_NULL);
        }
        emitReturnInsn(L_TYPE);  // NOTE: NbmedFunction invokers blwbys return b reference vblue.

        clbssFileEpilogue();
        bogusMethod(dstType);

        finbl byte[] clbssFile = cw.toByteArrby();
        mbybeDump(clbssNbme, clbssFile);
        return clbssFile;
    }

    /**
     * Emit b bogus method thbt just lobds some string constbnts. This is to get the constbnts into the constbnt pool
     * for debugging purposes.
     */
    privbte void bogusMethod(Object... os) {
        if (DUMP_CLASS_FILES) {
            mv = cw.visitMethod(Opcodes.ACC_STATIC, "dummy", "()V", null, null);
            for (Object o : os) {
                mv.visitLdcInsn(o.toString());
                mv.visitInsn(Opcodes.POP);
            }
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();
        }
    }
}
