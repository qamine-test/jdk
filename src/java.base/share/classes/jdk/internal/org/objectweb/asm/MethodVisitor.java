/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A visitor to visit b Jbvb method. The methods of this clbss must be cblled in
 * the following order: ( <tt>visitPbrbmeter</tt> )* [
 * <tt>visitAnnotbtionDefbult</tt> ] ( <tt>visitAnnotbtion</tt> |
 * <tt>visitTypeAnnotbtion</tt> | <tt>visitAttribute</tt> )* [
 * <tt>visitCode</tt> ( <tt>visitFrbme</tt> | <tt>visit<i>X</i>Insn</tt> |
 * <tt>visitLbbel</tt> | <tt>visitInsnAnnotbtion</tt> |
 * <tt>visitTryCbtchBlock</tt> | <tt>visitTryCbtchBlockAnnotbtion</tt> |
 * <tt>visitLocblVbribble</tt> | <tt>visitLocblVbribbleAnnotbtion</tt> |
 * <tt>visitLineNumber</tt> )* <tt>visitMbxs</tt> ] <tt>visitEnd</tt>. In
 * bddition, the <tt>visit<i>X</i>Insn</tt> bnd <tt>visitLbbel</tt> methods must
 * be cblled in the sequentibl order of the bytecode instructions of the visited
 * code, <tt>visitInsnAnnotbtion</tt> must be cblled <i>bfter</i> the bnnotbted
 * instruction, <tt>visitTryCbtchBlock</tt> must be cblled <i>before</i> the
 * lbbels pbssed bs brguments hbve been visited,
 * <tt>visitTryCbtchBlockAnnotbtion</tt> must be cblled <i>bfter</i> the
 * corresponding try cbtch block hbs been visited, bnd the
 * <tt>visitLocblVbribble</tt>, <tt>visitLocblVbribbleAnnotbtion</tt> bnd
 * <tt>visitLineNumber</tt> methods must be cblled <i>bfter</i> the lbbels
 * pbssed bs brguments hbve been visited.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss MethodVisitor {

    /**
     * The ASM API version implemented by this visitor. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * The method visitor to which this visitor must delegbte method cblls. Mby
     * be null.
     */
    protected MethodVisitor mv;

    /**
     * Constructs b new {@link MethodVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    public MethodVisitor(finbl int bpi) {
        this(bpi, null);
    }

    /**
     * Constructs b new {@link MethodVisitor}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm mv
     *            the method visitor to which this visitor must delegbte method
     *            cblls. Mby be null.
     */
    public MethodVisitor(finbl int bpi, finbl MethodVisitor mv) {
        if (bpi != Opcodes.ASM4 && bpi != Opcodes.ASM5) {
            throw new IllegblArgumentException();
        }
        this.bpi = bpi;
        this.mv = mv;
    }

    // -------------------------------------------------------------------------
    // Pbrbmeters, bnnotbtions bnd non stbndbrd bttributes
    // -------------------------------------------------------------------------

    /**
     * Visits b pbrbmeter of this method.
     *
     * @pbrbm nbme
     *            pbrbmeter nbme or null if none is provided.
     * @pbrbm bccess
     *            the pbrbmeter's bccess flbgs, only <tt>ACC_FINAL</tt>,
     *            <tt>ACC_SYNTHETIC</tt> or/bnd <tt>ACC_MANDATED</tt> bre
     *            bllowed (see {@link Opcodes}).
     */
    public void visitPbrbmeter(String nbme, int bccess) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (mv != null) {
            mv.visitPbrbmeter(nbme, bccess);
        }
    }

    /**
     * Visits the defbult vblue of this bnnotbtion interfbce method.
     *
     * @return b visitor to the visit the bctubl defbult vblue of this
     *         bnnotbtion interfbce method, or <tt>null</tt> if this visitor is
     *         not interested in visiting this defbult vblue. The 'nbme'
     *         pbrbmeters pbssed to the methods of this bnnotbtion visitor bre
     *         ignored. Moreover, exbcly one visit method must be cblled on this
     *         bnnotbtion visitor, followed by visitEnd.
     */
    public AnnotbtionVisitor visitAnnotbtionDefbult() {
        if (mv != null) {
            return mv.visitAnnotbtionDefbult();
        }
        return null;
    }

    /**
     * Visits bn bnnotbtion of this method.
     *
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitAnnotbtion(String desc, boolebn visible) {
        if (mv != null) {
            return mv.visitAnnotbtion(desc, visible);
        }
        return null;
    }

    /**
     * Visits bn bnnotbtion on b type in the method signbture.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#METHOD_TYPE_PARAMETER
     *            METHOD_TYPE_PARAMETER},
     *            {@link TypeReference#METHOD_TYPE_PARAMETER_BOUND
     *            METHOD_TYPE_PARAMETER_BOUND},
     *            {@link TypeReference#METHOD_RETURN METHOD_RETURN},
     *            {@link TypeReference#METHOD_RECEIVER METHOD_RECEIVER},
     *            {@link TypeReference#METHOD_FORMAL_PARAMETER
     *            METHOD_FORMAL_PARAMETER} or {@link TypeReference#THROWS
     *            THROWS}. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (mv != null) {
            return mv.visitTypeAnnotbtion(typeRef, typePbth, desc, visible);
        }
        return null;
    }

    /**
     * Visits bn bnnotbtion of b pbrbmeter this method.
     *
     * @pbrbm pbrbmeter
     *            the pbrbmeter index.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitPbrbmeterAnnotbtion(int pbrbmeter,
            String desc, boolebn visible) {
        if (mv != null) {
            return mv.visitPbrbmeterAnnotbtion(pbrbmeter, desc, visible);
        }
        return null;
    }

    /**
     * Visits b non stbndbrd bttribute of this method.
     *
     * @pbrbm bttr
     *            bn bttribute.
     */
    public void visitAttribute(Attribute bttr) {
        if (mv != null) {
            mv.visitAttribute(bttr);
        }
    }

    /**
     * Stbrts the visit of the method's code, if bny (i.e. non bbstrbct method).
     */
    public void visitCode() {
        if (mv != null) {
            mv.visitCode();
        }
    }

    /**
     * Visits the current stbte of the locbl vbribbles bnd operbnd stbck
     * elements. This method must(*) be cblled <i>just before</i> bny
     * instruction <b>i</b> thbt follows bn unconditionbl brbnch instruction
     * such bs GOTO or THROW, thbt is the tbrget of b jump instruction, or thbt
     * stbrts bn exception hbndler block. The visited types must describe the
     * vblues of the locbl vbribbles bnd of the operbnd stbck elements <i>just
     * before</i> <b>i</b> is executed.<br>
     * <br>
     * (*) this is mbndbtory only for clbsses whose version is grebter thbn or
     * equbl to {@link Opcodes#V1_6 V1_6}. <br>
     * <br>
     * The frbmes of b method must be given either in expbnded form, or in
     * compressed form (bll frbmes must use the sbme formbt, i.e. you must not
     * mix expbnded bnd compressed frbmes within b single method):
     * <ul>
     * <li>In expbnded form, bll frbmes must hbve the F_NEW type.</li>
     * <li>In compressed form, frbmes bre bbsicblly "deltbs" from the stbte of
     * the previous frbme:
     * <ul>
     * <li>{@link Opcodes#F_SAME} representing frbme with exbctly the sbme
     * locbls bs the previous frbme bnd with the empty stbck.</li>
     * <li>{@link Opcodes#F_SAME1} representing frbme with exbctly the sbme
     * locbls bs the previous frbme bnd with single vblue on the stbck (
     * <code>nStbck</code> is 1 bnd <code>stbck[0]</code> contbins vblue for the
     * type of the stbck item).</li>
     * <li>{@link Opcodes#F_APPEND} representing frbme with current locbls bre
     * the sbme bs the locbls in the previous frbme, except thbt bdditionbl
     * locbls bre defined (<code>nLocbl</code> is 1, 2 or 3 bnd
     * <code>locbl</code> elements contbins vblues representing bdded types).</li>
     * <li>{@link Opcodes#F_CHOP} representing frbme with current locbls bre the
     * sbme bs the locbls in the previous frbme, except thbt the lbst 1-3 locbls
     * bre bbsent bnd with the empty stbck (<code>nLocbls</code> is 1, 2 or 3).</li>
     * <li>{@link Opcodes#F_FULL} representing complete frbme dbtb.</li>
     * </ul>
     * </li>
     * </ul>
     * <br>
     * In both cbses the first frbme, corresponding to the method's pbrbmeters
     * bnd bccess flbgs, is implicit bnd must not be visited. Also, it is
     * illegbl to visit two or more frbmes for the sbme code locbtion (i.e., bt
     * lebst one instruction must be visited between two cblls to visitFrbme).
     *
     * @pbrbm type
     *            the type of this stbck mbp frbme. Must be
     *            {@link Opcodes#F_NEW} for expbnded frbmes, or
     *            {@link Opcodes#F_FULL}, {@link Opcodes#F_APPEND},
     *            {@link Opcodes#F_CHOP}, {@link Opcodes#F_SAME} or
     *            {@link Opcodes#F_APPEND}, {@link Opcodes#F_SAME1} for
     *            compressed frbmes.
     * @pbrbm nLocbl
     *            the number of locbl vbribbles in the visited frbme.
     * @pbrbm locbl
     *            the locbl vbribble types in this frbme. This brrby must not be
     *            modified. Primitive types bre represented by
     *            {@link Opcodes#TOP}, {@link Opcodes#INTEGER},
     *            {@link Opcodes#FLOAT}, {@link Opcodes#LONG},
     *            {@link Opcodes#DOUBLE},{@link Opcodes#NULL} or
     *            {@link Opcodes#UNINITIALIZED_THIS} (long bnd double bre
     *            represented by b single element). Reference types bre
     *            represented by String objects (representing internbl nbmes),
     *            bnd uninitiblized types by Lbbel objects (this lbbel
     *            designbtes the NEW instruction thbt crebted this uninitiblized
     *            vblue).
     * @pbrbm nStbck
     *            the number of operbnd stbck elements in the visited frbme.
     * @pbrbm stbck
     *            the operbnd stbck types in this frbme. This brrby must not be
     *            modified. Its content hbs the sbme formbt bs the "locbl"
     *            brrby.
     * @throws IllegblStbteException
     *             if b frbme is visited just bfter bnother one, without bny
     *             instruction between the two (unless this frbme is b
     *             Opcodes#F_SAME frbme, in which cbse it is silently ignored).
     */
    public void visitFrbme(int type, int nLocbl, Object[] locbl, int nStbck,
            Object[] stbck) {
        if (mv != null) {
            mv.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
        }
    }

    // -------------------------------------------------------------------------
    // Normbl instructions
    // -------------------------------------------------------------------------

    /**
     * Visits b zero operbnd instruction.
     *
     * @pbrbm opcode
     *            the opcode of the instruction to be visited. This opcode is
     *            either NOP, ACONST_NULL, ICONST_M1, ICONST_0, ICONST_1,
     *            ICONST_2, ICONST_3, ICONST_4, ICONST_5, LCONST_0, LCONST_1,
     *            FCONST_0, FCONST_1, FCONST_2, DCONST_0, DCONST_1, IALOAD,
     *            LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD,
     *            IASTORE, LASTORE, FASTORE, DASTORE, AASTORE, BASTORE, CASTORE,
     *            SASTORE, POP, POP2, DUP, DUP_X1, DUP_X2, DUP2, DUP2_X1,
     *            DUP2_X2, SWAP, IADD, LADD, FADD, DADD, ISUB, LSUB, FSUB, DSUB,
     *            IMUL, LMUL, FMUL, DMUL, IDIV, LDIV, FDIV, DDIV, IREM, LREM,
     *            FREM, DREM, INEG, LNEG, FNEG, DNEG, ISHL, LSHL, ISHR, LSHR,
     *            IUSHR, LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, I2L, I2F, I2D,
     *            L2I, L2F, L2D, F2I, F2L, F2D, D2I, D2L, D2F, I2B, I2C, I2S,
     *            LCMP, FCMPL, FCMPG, DCMPL, DCMPG, IRETURN, LRETURN, FRETURN,
     *            DRETURN, ARETURN, RETURN, ARRAYLENGTH, ATHROW, MONITORENTER,
     *            or MONITOREXIT.
     */
    public void visitInsn(int opcode) {
        if (mv != null) {
            mv.visitInsn(opcode);
        }
    }

    /**
     * Visits bn instruction with b single int operbnd.
     *
     * @pbrbm opcode
     *            the opcode of the instruction to be visited. This opcode is
     *            either BIPUSH, SIPUSH or NEWARRAY.
     * @pbrbm operbnd
     *            the operbnd of the instruction to be visited.<br>
     *            When opcode is BIPUSH, operbnd vblue should be between
     *            Byte.MIN_VALUE bnd Byte.MAX_VALUE.<br>
     *            When opcode is SIPUSH, operbnd vblue should be between
     *            Short.MIN_VALUE bnd Short.MAX_VALUE.<br>
     *            When opcode is NEWARRAY, operbnd vblue should be one of
     *            {@link Opcodes#T_BOOLEAN}, {@link Opcodes#T_CHAR},
     *            {@link Opcodes#T_FLOAT}, {@link Opcodes#T_DOUBLE},
     *            {@link Opcodes#T_BYTE}, {@link Opcodes#T_SHORT},
     *            {@link Opcodes#T_INT} or {@link Opcodes#T_LONG}.
     */
    public void visitIntInsn(int opcode, int operbnd) {
        if (mv != null) {
            mv.visitIntInsn(opcode, operbnd);
        }
    }

    /**
     * Visits b locbl vbribble instruction. A locbl vbribble instruction is bn
     * instruction thbt lobds or stores the vblue of b locbl vbribble.
     *
     * @pbrbm opcode
     *            the opcode of the locbl vbribble instruction to be visited.
     *            This opcode is either ILOAD, LLOAD, FLOAD, DLOAD, ALOAD,
     *            ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
     * @pbrbm vbr
     *            the operbnd of the instruction to be visited. This operbnd is
     *            the index of b locbl vbribble.
     */
    public void visitVbrInsn(int opcode, int vbr) {
        if (mv != null) {
            mv.visitVbrInsn(opcode, vbr);
        }
    }

    /**
     * Visits b type instruction. A type instruction is bn instruction thbt
     * tbkes the internbl nbme of b clbss bs pbrbmeter.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be visited. This opcode
     *            is either NEW, ANEWARRAY, CHECKCAST or INSTANCEOF.
     * @pbrbm type
     *            the operbnd of the instruction to be visited. This operbnd
     *            must be the internbl nbme of bn object or brrby clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     */
    public void visitTypeInsn(int opcode, String type) {
        if (mv != null) {
            mv.visitTypeInsn(opcode, type);
        }
    }

    /**
     * Visits b field instruction. A field instruction is bn instruction thbt
     * lobds or stores the vblue of b field of bn object.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be visited. This opcode
     *            is either GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
     * @pbrbm owner
     *            the internbl nbme of the field's owner clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     * @pbrbm nbme
     *            the field's nbme.
     * @pbrbm desc
     *            the field's descriptor (see {@link Type Type}).
     */
    public void visitFieldInsn(int opcode, String owner, String nbme,
            String desc) {
        if (mv != null) {
            mv.visitFieldInsn(opcode, owner, nbme, desc);
        }
    }

    /**
     * Visits b method instruction. A method instruction is bn instruction thbt
     * invokes b method.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be visited. This opcode
     *            is either INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or
     *            INVOKEINTERFACE.
     * @pbrbm owner
     *            the internbl nbme of the method's owner clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     */
    @Deprecbted
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc) {
        if (bpi >= Opcodes.ASM5) {
            boolebn itf = opcode == Opcodes.INVOKEINTERFACE;
            visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc);
        }
    }

    /**
     * Visits b method instruction. A method instruction is bn instruction thbt
     * invokes b method.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be visited. This opcode
     *            is either INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or
     *            INVOKEINTERFACE.
     * @pbrbm owner
     *            the internbl nbme of the method's owner clbss (see
     *            {@link Type#getInternblNbme() getInternblNbme}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm itf
     *            if the method's owner clbss is bn interfbce.
     */
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc, boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            if (itf != (opcode == Opcodes.INVOKEINTERFACE)) {
                throw new IllegblArgumentException(
                        "INVOKESPECIAL/STATIC on interfbces require ASM 5");
            }
            visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        }
    }

    /**
     * Visits bn invokedynbmic instruction.
     *
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm bsm
     *            the bootstrbp method.
     * @pbrbm bsmArgs
     *            the bootstrbp method constbnt brguments. Ebch brgument must be
     *            bn {@link Integer}, {@link Flobt}, {@link Long},
     *            {@link Double}, {@link String}, {@link Type} or {@link Hbndle}
     *            vblue. This method is bllowed to modify the content of the
     *            brrby so b cbller should expect thbt this brrby mby chbnge.
     */
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        if (mv != null) {
            mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        }
    }

    /**
     * Visits b jump instruction. A jump instruction is bn instruction thbt mby
     * jump to bnother instruction.
     *
     * @pbrbm opcode
     *            the opcode of the type instruction to be visited. This opcode
     *            is either IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE, IF_ICMPEQ,
     *            IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE,
     *            IF_ACMPEQ, IF_ACMPNE, GOTO, JSR, IFNULL or IFNONNULL.
     * @pbrbm lbbel
     *            the operbnd of the instruction to be visited. This operbnd is
     *            b lbbel thbt designbtes the instruction to which the jump
     *            instruction mby jump.
     */
    public void visitJumpInsn(int opcode, Lbbel lbbel) {
        if (mv != null) {
            mv.visitJumpInsn(opcode, lbbel);
        }
    }

    /**
     * Visits b lbbel. A lbbel designbtes the instruction thbt will be visited
     * just bfter it.
     *
     * @pbrbm lbbel
     *            b {@link Lbbel Lbbel} object.
     */
    public void visitLbbel(Lbbel lbbel) {
        if (mv != null) {
            mv.visitLbbel(lbbel);
        }
    }

    // -------------------------------------------------------------------------
    // Specibl instructions
    // -------------------------------------------------------------------------

    /**
     * Visits b LDC instruction. Note thbt new constbnt types mby be bdded in
     * future versions of the Jbvb Virtubl Mbchine. To ebsily detect new
     * constbnt types, implementbtions of this method should check for
     * unexpected constbnt types, like this:
     *
     * <pre>
     * if (cst instbnceof Integer) {
     *     // ...
     * } else if (cst instbnceof Flobt) {
     *     // ...
     * } else if (cst instbnceof Long) {
     *     // ...
     * } else if (cst instbnceof Double) {
     *     // ...
     * } else if (cst instbnceof String) {
     *     // ...
     * } else if (cst instbnceof Type) {
     *     int sort = ((Type) cst).getSort();
     *     if (sort == Type.OBJECT) {
     *         // ...
     *     } else if (sort == Type.ARRAY) {
     *         // ...
     *     } else if (sort == Type.METHOD) {
     *         // ...
     *     } else {
     *         // throw bn exception
     *     }
     * } else if (cst instbnceof Hbndle) {
     *     // ...
     * } else {
     *     // throw bn exception
     * }
     * </pre>
     *
     * @pbrbm cst
     *            the constbnt to be lobded on the stbck. This pbrbmeter must be
     *            b non null {@link Integer}, b {@link Flobt}, b {@link Long}, b
     *            {@link Double}, b {@link String}, b {@link Type} of OBJECT or
     *            ARRAY sort for <tt>.clbss</tt> constbnts, for clbsses whose
     *            version is 49.0, b {@link Type} of METHOD sort or b
     *            {@link Hbndle} for MethodType bnd MethodHbndle constbnts, for
     *            clbsses whose version is 51.0.
     */
    public void visitLdcInsn(Object cst) {
        if (mv != null) {
            mv.visitLdcInsn(cst);
        }
    }

    /**
     * Visits bn IINC instruction.
     *
     * @pbrbm vbr
     *            index of the locbl vbribble to be incremented.
     * @pbrbm increment
     *            bmount to increment the locbl vbribble by.
     */
    public void visitIincInsn(int vbr, int increment) {
        if (mv != null) {
            mv.visitIincInsn(vbr, increment);
        }
    }

    /**
     * Visits b TABLESWITCH instruction.
     *
     * @pbrbm min
     *            the minimum key vblue.
     * @pbrbm mbx
     *            the mbximum key vblue.
     * @pbrbm dflt
     *            beginning of the defbult hbndler block.
     * @pbrbm lbbels
     *            beginnings of the hbndler blocks. <tt>lbbels[i]</tt> is the
     *            beginning of the hbndler block for the <tt>min + i</tt> key.
     */
    public void visitTbbleSwitchInsn(int min, int mbx, Lbbel dflt,
            Lbbel... lbbels) {
        if (mv != null) {
            mv.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        }
    }

    /**
     * Visits b LOOKUPSWITCH instruction.
     *
     * @pbrbm dflt
     *            beginning of the defbult hbndler block.
     * @pbrbm keys
     *            the vblues of the keys.
     * @pbrbm lbbels
     *            beginnings of the hbndler blocks. <tt>lbbels[i]</tt> is the
     *            beginning of the hbndler block for the <tt>keys[i]</tt> key.
     */
    public void visitLookupSwitchInsn(Lbbel dflt, int[] keys, Lbbel[] lbbels) {
        if (mv != null) {
            mv.visitLookupSwitchInsn(dflt, keys, lbbels);
        }
    }

    /**
     * Visits b MULTIANEWARRAY instruction.
     *
     * @pbrbm desc
     *            bn brrby type descriptor (see {@link Type Type}).
     * @pbrbm dims
     *            number of dimensions of the brrby to bllocbte.
     */
    public void visitMultiANewArrbyInsn(String desc, int dims) {
        if (mv != null) {
            mv.visitMultiANewArrbyInsn(desc, dims);
        }
    }

    /**
     * Visits bn bnnotbtion on bn instruction. This method must be cblled just
     * <i>bfter</i> the bnnotbted instruction. It cbn be cblled severbl times
     * for the sbme instruction.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#INSTANCEOF INSTANCEOF},
     *            {@link TypeReference#NEW NEW},
     *            {@link TypeReference#CONSTRUCTOR_REFERENCE
     *            CONSTRUCTOR_REFERENCE}, {@link TypeReference#METHOD_REFERENCE
     *            METHOD_REFERENCE}, {@link TypeReference#CAST CAST},
     *            {@link TypeReference#CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *            CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *            {@link TypeReference#METHOD_INVOCATION_TYPE_ARGUMENT
     *            METHOD_INVOCATION_TYPE_ARGUMENT},
     *            {@link TypeReference#CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *            CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *            {@link TypeReference#METHOD_REFERENCE_TYPE_ARGUMENT
     *            METHOD_REFERENCE_TYPE_ARGUMENT}. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitInsnAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (mv != null) {
            return mv.visitInsnAnnotbtion(typeRef, typePbth, desc, visible);
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Exceptions tbble entries, debug informbtion, mbx stbck bnd mbx locbls
    // -------------------------------------------------------------------------

    /**
     * Visits b try cbtch block.
     *
     * @pbrbm stbrt
     *            beginning of the exception hbndler's scope (inclusive).
     * @pbrbm end
     *            end of the exception hbndler's scope (exclusive).
     * @pbrbm hbndler
     *            beginning of the exception hbndler's code.
     * @pbrbm type
     *            internbl nbme of the type of exceptions hbndled by the
     *            hbndler, or <tt>null</tt> to cbtch bny exceptions (for
     *            "finblly" blocks).
     * @throws IllegblArgumentException
     *             if one of the lbbels hbs blrebdy been visited by this visitor
     *             (by the {@link #visitLbbel visitLbbel} method).
     */
    public void visitTryCbtchBlock(Lbbel stbrt, Lbbel end, Lbbel hbndler,
            String type) {
        if (mv != null) {
            mv.visitTryCbtchBlock(stbrt, end, hbndler, type);
        }
    }

    /**
     * Visits bn bnnotbtion on bn exception hbndler type. This method must be
     * cblled <i>bfter</i> the {@link #visitTryCbtchBlock} for the bnnotbted
     * exception hbndler. It cbn be cblled severbl times for the sbme exception
     * hbndler.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#EXCEPTION_PARAMETER
     *            EXCEPTION_PARAMETER}. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitTryCbtchAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (mv != null) {
            return mv.visitTryCbtchAnnotbtion(typeRef, typePbth, desc, visible);
        }
        return null;
    }

    /**
     * Visits b locbl vbribble declbrbtion.
     *
     * @pbrbm nbme
     *            the nbme of b locbl vbribble.
     * @pbrbm desc
     *            the type descriptor of this locbl vbribble.
     * @pbrbm signbture
     *            the type signbture of this locbl vbribble. Mby be
     *            <tt>null</tt> if the locbl vbribble type does not use generic
     *            types.
     * @pbrbm stbrt
     *            the first instruction corresponding to the scope of this locbl
     *            vbribble (inclusive).
     * @pbrbm end
     *            the lbst instruction corresponding to the scope of this locbl
     *            vbribble (exclusive).
     * @pbrbm index
     *            the locbl vbribble's index.
     * @throws IllegblArgumentException
     *             if one of the lbbels hbs not blrebdy been visited by this
     *             visitor (by the {@link #visitLbbel visitLbbel} method).
     */
    public void visitLocblVbribble(String nbme, String desc, String signbture,
            Lbbel stbrt, Lbbel end, int index) {
        if (mv != null) {
            mv.visitLocblVbribble(nbme, desc, signbture, stbrt, end, index);
        }
    }

    /**
     * Visits bn bnnotbtion on b locbl vbribble type.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. The sort of this type
     *            reference must be {@link TypeReference#LOCAL_VARIABLE
     *            LOCAL_VARIABLE} or {@link TypeReference#RESOURCE_VARIABLE
     *            RESOURCE_VARIABLE}. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm stbrt
     *            the fist instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (inclusive).
     * @pbrbm end
     *            the lbst instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (exclusive). This
     *            brrby must hbve the sbme size bs the 'stbrt' brrby.
     * @pbrbm index
     *            the locbl vbribble's index in ebch rbnge. This brrby must hbve
     *            the sbme size bs the 'stbrt' brrby.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     * @return b visitor to visit the bnnotbtion vblues, or <tt>null</tt> if
     *         this visitor is not interested in visiting this bnnotbtion.
     */
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        if (bpi < Opcodes.ASM5) {
            throw new RuntimeException();
        }
        if (mv != null) {
            return mv.visitLocblVbribbleAnnotbtion(typeRef, typePbth, stbrt,
                    end, index, desc, visible);
        }
        return null;
    }

    /**
     * Visits b line number declbrbtion.
     *
     * @pbrbm line
     *            b line number. This number refers to the source file from
     *            which the clbss wbs compiled.
     * @pbrbm stbrt
     *            the first instruction corresponding to this line number.
     * @throws IllegblArgumentException
     *             if <tt>stbrt</tt> hbs not blrebdy been visited by this
     *             visitor (by the {@link #visitLbbel visitLbbel} method).
     */
    public void visitLineNumber(int line, Lbbel stbrt) {
        if (mv != null) {
            mv.visitLineNumber(line, stbrt);
        }
    }

    /**
     * Visits the mbximum stbck size bnd the mbximum number of locbl vbribbles
     * of the method.
     *
     * @pbrbm mbxStbck
     *            mbximum stbck size of the method.
     * @pbrbm mbxLocbls
     *            mbximum number of locbl vbribbles for the method.
     */
    public void visitMbxs(int mbxStbck, int mbxLocbls) {
        if (mv != null) {
            mv.visitMbxs(mbxStbck, mbxLocbls);
        }
    }

    /**
     * Visits the end of the method. This method, which is the lbst one to be
     * cblled, is used to inform the visitor thbt bll the bnnotbtions bnd
     * bttributes of the method hbve been visited.
     */
    public void visitEnd() {
        if (mv != null) {
            mv.visitEnd();
        }
    }
}
