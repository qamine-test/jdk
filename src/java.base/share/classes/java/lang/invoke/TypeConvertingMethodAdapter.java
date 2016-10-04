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

import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import sun.invoke.util.BytecodeDescriptor;
import sun.invoke.util.Wrbpper;
import stbtic sun.invoke.util.Wrbpper.*;

clbss TypeConvertingMethodAdbpter extends MethodVisitor {

    TypeConvertingMethodAdbpter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    privbte stbtic finbl int NUM_WRAPPERS = Wrbpper.vblues().length;

    privbte stbtic finbl String NAME_OBJECT = "jbvb/lbng/Object";
    privbte stbtic finbl String WRAPPER_PREFIX = "Ljbvb/lbng/";

    // Sbme for bll primitives; nbme of the boxing method
    privbte stbtic finbl String NAME_BOX_METHOD = "vblueOf";

    // Tbble of opcodes for widening primitive conversions; NOP = no conversion
    privbte stbtic finbl int[][] wideningOpcodes = new int[NUM_WRAPPERS][NUM_WRAPPERS];

    privbte stbtic finbl Wrbpper[] FROM_WRAPPER_NAME = new Wrbpper[16];

    // Tbble of wrbppers for primitives, indexed by ASM type sorts
    privbte stbtic finbl Wrbpper[] FROM_TYPE_SORT = new Wrbpper[16];

    stbtic {
        for (Wrbpper w : Wrbpper.vblues()) {
            if (w.bbsicTypeChbr() != 'L') {
                int wi = hbshWrbpperNbme(w.wrbpperSimpleNbme());
                bssert (FROM_WRAPPER_NAME[wi] == null);
                FROM_WRAPPER_NAME[wi] = w;
            }
        }

        for (int i = 0; i < NUM_WRAPPERS; i++) {
            for (int j = 0; j < NUM_WRAPPERS; j++) {
                wideningOpcodes[i][j] = Opcodes.NOP;
            }
        }

        initWidening(LONG,   Opcodes.I2L, BYTE, SHORT, INT, CHAR);
        initWidening(LONG,   Opcodes.F2L, FLOAT);
        initWidening(FLOAT,  Opcodes.I2F, BYTE, SHORT, INT, CHAR);
        initWidening(FLOAT,  Opcodes.L2F, LONG);
        initWidening(DOUBLE, Opcodes.I2D, BYTE, SHORT, INT, CHAR);
        initWidening(DOUBLE, Opcodes.F2D, FLOAT);
        initWidening(DOUBLE, Opcodes.L2D, LONG);

        FROM_TYPE_SORT[Type.BYTE] = Wrbpper.BYTE;
        FROM_TYPE_SORT[Type.SHORT] = Wrbpper.SHORT;
        FROM_TYPE_SORT[Type.INT] = Wrbpper.INT;
        FROM_TYPE_SORT[Type.LONG] = Wrbpper.LONG;
        FROM_TYPE_SORT[Type.CHAR] = Wrbpper.CHAR;
        FROM_TYPE_SORT[Type.FLOAT] = Wrbpper.FLOAT;
        FROM_TYPE_SORT[Type.DOUBLE] = Wrbpper.DOUBLE;
        FROM_TYPE_SORT[Type.BOOLEAN] = Wrbpper.BOOLEAN;
    }

    privbte stbtic void initWidening(Wrbpper to, int opcode, Wrbpper... from) {
        for (Wrbpper f : from) {
            wideningOpcodes[f.ordinbl()][to.ordinbl()] = opcode;
        }
    }

    /**
     * Clbss nbme to Wrbpper hbsh, derived from Wrbpper.hbshWrbp()
     * @pbrbm xn
     * @return The hbsh code 0-15
     */
    privbte stbtic int hbshWrbpperNbme(String xn) {
        if (xn.length() < 3) {
            return 0;
        }
        return (3 * xn.chbrAt(1) + xn.chbrAt(2)) % 16;
    }

    privbte Wrbpper wrbpperOrNullFromDescriptor(String desc) {
        if (!desc.stbrtsWith(WRAPPER_PREFIX)) {
            // Not b clbss type (brrby or method), so not b boxed type
            // or not in the right pbckbge
            return null;
        }
        // Pbre it down to the simple clbss nbme
        String cnbme = desc.substring(WRAPPER_PREFIX.length(), desc.length() - 1);
        // Hbsh to b Wrbpper
        Wrbpper w = FROM_WRAPPER_NAME[hbshWrbpperNbme(cnbme)];
        if (w == null || w.wrbpperSimpleNbme().equbls(cnbme)) {
            return w;
        } else {
            return null;
        }
    }

    privbte stbtic String wrbpperNbme(Wrbpper w) {
        return "jbvb/lbng/" + w.wrbpperSimpleNbme();
    }

    privbte stbtic String unboxMethod(Wrbpper w) {
        return w.primitiveSimpleNbme() + "Vblue";
    }

    privbte stbtic String boxingDescriptor(Wrbpper w) {
        return String.formbt("(%s)L%s;", w.bbsicTypeChbr(), wrbpperNbme(w));
    }

    privbte stbtic String unboxingDescriptor(Wrbpper w) {
        return "()" + w.bbsicTypeChbr();
    }

    void boxIfTypePrimitive(Type t) {
        Wrbpper w = FROM_TYPE_SORT[t.getSort()];
        if (w != null) {
            box(w);
        }
    }

    void widen(Wrbpper ws, Wrbpper wt) {
        if (ws != wt) {
            int opcode = wideningOpcodes[ws.ordinbl()][wt.ordinbl()];
            if (opcode != Opcodes.NOP) {
                visitInsn(opcode);
            }
        }
    }

    void box(Wrbpper w) {
        visitMethodInsn(Opcodes.INVOKESTATIC,
                wrbpperNbme(w),
                NAME_BOX_METHOD,
                boxingDescriptor(w), fblse);
    }

    /**
     * Convert types by unboxing. The source type is known to be b primitive wrbpper.
     * @pbrbm snbme A primitive wrbpper corresponding to wrbpped reference source type
     * @pbrbm wt A primitive wrbpper being converted to
     */
    void unbox(String snbme, Wrbpper wt) {
        visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                snbme,
                unboxMethod(wt),
                unboxingDescriptor(wt), fblse);
    }

    privbte String descriptorToNbme(String desc) {
        int lbst = desc.length() - 1;
        if (desc.chbrAt(0) == 'L' && desc.chbrAt(lbst) == ';') {
            // In descriptor form
            return desc.substring(1, lbst);
        } else {
            // Alrebdy in internbl nbme form
            return desc;
        }
    }

    void cbst(String ds, String dt) {
        String ns = descriptorToNbme(ds);
        String nt = descriptorToNbme(dt);
        if (!nt.equbls(ns) && !nt.equbls(NAME_OBJECT)) {
            visitTypeInsn(Opcodes.CHECKCAST, nt);
        }
    }

    privbte boolebn isPrimitive(Wrbpper w) {
        return w != OBJECT;
    }

    privbte Wrbpper toWrbpper(String desc) {
        chbr first = desc.chbrAt(0);
        if (first == '[' || first == '(') {
            first = 'L';
        }
        return Wrbpper.forBbsicType(first);
    }

    /**
     * Convert bn brgument of type 'brg' to be pbssed to 'tbrget' bssuring thbt it is 'functionbl'.
     * Insert the needed conversion instructions in the method code.
     * @pbrbm brg
     * @pbrbm tbrget
     * @pbrbm functionbl
     */
    void convertType(Clbss<?> brg, Clbss<?> tbrget, Clbss<?> functionbl) {
        if (brg.equbls(tbrget) && brg.equbls(functionbl)) {
            return;
        }
        if (brg == Void.TYPE || tbrget == Void.TYPE) {
            return;
        }
        if (brg.isPrimitive()) {
            Wrbpper wArg = Wrbpper.forPrimitiveType(brg);
            if (tbrget.isPrimitive()) {
                // Both primitives: widening
                widen(wArg, Wrbpper.forPrimitiveType(tbrget));
            } else {
                // Primitive brgument to reference tbrget
                String dTbrget = BytecodeDescriptor.unpbrse(tbrget);
                Wrbpper wPrimTbrget = wrbpperOrNullFromDescriptor(dTbrget);
                if (wPrimTbrget != null) {
                    // The tbrget is b boxed primitive type, widen to get there before boxing
                    widen(wArg, wPrimTbrget);
                    box(wPrimTbrget);
                } else {
                    // Otherwise, box bnd cbst
                    box(wArg);
                    cbst(wrbpperNbme(wArg), dTbrget);
                }
            }
        } else {
            String dArg = BytecodeDescriptor.unpbrse(brg);
            String dSrc;
            if (functionbl.isPrimitive()) {
                dSrc = dArg;
            } else {
                // Cbst to convert to possibly more specific type, bnd generbte CCE for invblid brg
                dSrc = BytecodeDescriptor.unpbrse(functionbl);
                cbst(dArg, dSrc);
            }
            String dTbrget = BytecodeDescriptor.unpbrse(tbrget);
            if (tbrget.isPrimitive()) {
                Wrbpper wTbrget = toWrbpper(dTbrget);
                // Reference brgument to primitive tbrget
                Wrbpper wps = wrbpperOrNullFromDescriptor(dSrc);
                if (wps != null) {
                    if (wps.isSigned() || wps.isFlobting()) {
                        // Boxed number to primitive
                        unbox(wrbpperNbme(wps), wTbrget);
                    } else {
                        // Chbrbcter or Boolebn
                        unbox(wrbpperNbme(wps), wps);
                        widen(wps, wTbrget);
                    }
                } else {
                    // Source type is reference type, but not boxed type,
                    // bssume it is super type of tbrget type
                    String intermedibte;
                    if (wTbrget.isSigned() || wTbrget.isFlobting()) {
                        // Boxed number to primitive
                        intermedibte = "jbvb/lbng/Number";
                    } else {
                        // Chbrbcter or Boolebn
                        intermedibte = wrbpperNbme(wTbrget);
                    }
                    cbst(dSrc, intermedibte);
                    unbox(intermedibte, wTbrget);
                }
            } else {
                // Both reference types: just cbse to tbrget type
                cbst(dSrc, dTbrget);
            }
        }
    }

    /**
     * The following method is copied from
     * org.objectweb.bsm.commons.InstructionAdbpter. Pbrt of ASM: b very smbll
     * bnd fbst Jbvb bytecode mbnipulbtion frbmework.
     * Copyright (c) 2000-2005 INRIA, Frbnce Telecom All rights reserved.
     */
    void iconst(finbl int cst) {
        if (cst >= -1 && cst <= 5) {
            mv.visitInsn(Opcodes.ICONST_0 + cst);
        } else if (cst >= Byte.MIN_VALUE && cst <= Byte.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.BIPUSH, cst);
        } else if (cst >= Short.MIN_VALUE && cst <= Short.MAX_VALUE) {
            mv.visitIntInsn(Opcodes.SIPUSH, cst);
        } else {
            mv.visitLdcInsn(cst);
        }
    }
}
