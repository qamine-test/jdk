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
pbckbge jdk.internbl.org.objectweb.bsm.util;

import jbvb.io.PrintWriter;
import jbvb.io.StringWriter;
import jbvb.lbng.reflect.Field;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;
import jdk.internbl.org.objectweb.bsm.TypeReference;
import jdk.internbl.org.objectweb.bsm.tree.MethodNode;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.Anblyzer;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.BbsicVblue;
import jdk.internbl.org.objectweb.bsm.tree.bnblysis.BbsicVerifier;

/**
 * A {@link MethodVisitor} thbt checks thbt its methods bre properly used. More
 * precisely this method bdbpter checks ebch instruction individublly, i.e.,
 * ebch visit method checks some preconditions bbsed <i>only</i> on its
 * brguments - such bs the fbct thbt the given opcode is correct for b given
 * visit method. This bdbpter cbn blso perform some bbsic dbtb flow checks (more
 * precisely those thbt cbn be performed without the full clbss hierbrchy - see
 * {@link jdk.internbl.org.objectweb.bsm.tree.bnblysis.BbsicVerifier}). For instbnce in b
 * method whose signbture is <tt>void m ()</tt>, the invblid instruction
 * IRETURN, or the invblid sequence IADD L2I will be detected if the dbtb flow
 * checks bre enbbled. These checks bre enbbled by using the
 * {@link #CheckMethodAdbpter(int,String,String,MethodVisitor,Mbp)} constructor.
 * They bre not performed if bny other constructor is used.
 *
 * @buthor Eric Bruneton
 */
public clbss CheckMethodAdbpter extends MethodVisitor {

    /**
     * The clbss version number.
     */
    public int version;

    /**
     * The bccess flbgs of the method.
     */
    privbte int bccess;

    /**
     * <tt>true</tt> if the visitCode method hbs been cblled.
     */
    privbte boolebn stbrtCode;

    /**
     * <tt>true</tt> if the visitMbxs method hbs been cblled.
     */
    privbte boolebn endCode;

    /**
     * <tt>true</tt> if the visitEnd method hbs been cblled.
     */
    privbte boolebn endMethod;

    /**
     * Number of visited instructions.
     */
    privbte int insnCount;

    /**
     * The blrebdy visited lbbels. This mbp bssocibte Integer vblues to pseudo
     * code offsets.
     */
    privbte finbl Mbp<Lbbel, Integer> lbbels;

    /**
     * The lbbels used in this method. Every used lbbel must be visited with
     * visitLbbel before the end of the method (i.e. should be in #lbbels).
     */
    privbte Set<Lbbel> usedLbbels;

    /**
     * Number of visited frbmes in expbnded form.
     */
    privbte int expbndedFrbmes;

    /**
     * Number of visited frbmes in compressed form.
     */
    privbte int compressedFrbmes;

    /**
     * Number of instructions before the lbst visited frbme.
     */
    privbte int lbstFrbme = -1;

    /**
     * The exception hbndler rbnges. Ebch pbir of list element contbins the
     * stbrt bnd end lbbels of bn exception hbndler block.
     */
    privbte List<Lbbel> hbndlers;

    /**
     * Code of the visit method to be used for ebch opcode.
     */
    privbte stbtic finbl int[] TYPE;

    /**
     * The Lbbel.stbtus field.
     */
    privbte stbtic Field lbbelStbtusField;

    stbtic {
        String s = "BBBBBBBBBBBBBBBBCCIAADDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBDD"
                + "DDDAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
                + "BBBBBBBBBBBBBBBBBBBJBBBBBBBBBBBBBBBBBBBBHHHHHHHHHHHHHHHHD"
                + "KLBBBBBBFFFFGGGGAECEBBEEBBAMHHAA";
        TYPE = new int[s.length()];
        for (int i = 0; i < TYPE.length; ++i) {
            TYPE[i] = s.chbrAt(i) - 'A' - 1;
        }
    }

    // code to generbte the bbove string
    // public stbtic void mbin (String[] brgs) {
    // int[] TYPE = new int[] {
    // 0, //NOP
    // 0, //ACONST_NULL
    // 0, //ICONST_M1
    // 0, //ICONST_0
    // 0, //ICONST_1
    // 0, //ICONST_2
    // 0, //ICONST_3
    // 0, //ICONST_4
    // 0, //ICONST_5
    // 0, //LCONST_0
    // 0, //LCONST_1
    // 0, //FCONST_0
    // 0, //FCONST_1
    // 0, //FCONST_2
    // 0, //DCONST_0
    // 0, //DCONST_1
    // 1, //BIPUSH
    // 1, //SIPUSH
    // 7, //LDC
    // -1, //LDC_W
    // -1, //LDC2_W
    // 2, //ILOAD
    // 2, //LLOAD
    // 2, //FLOAD
    // 2, //DLOAD
    // 2, //ALOAD
    // -1, //ILOAD_0
    // -1, //ILOAD_1
    // -1, //ILOAD_2
    // -1, //ILOAD_3
    // -1, //LLOAD_0
    // -1, //LLOAD_1
    // -1, //LLOAD_2
    // -1, //LLOAD_3
    // -1, //FLOAD_0
    // -1, //FLOAD_1
    // -1, //FLOAD_2
    // -1, //FLOAD_3
    // -1, //DLOAD_0
    // -1, //DLOAD_1
    // -1, //DLOAD_2
    // -1, //DLOAD_3
    // -1, //ALOAD_0
    // -1, //ALOAD_1
    // -1, //ALOAD_2
    // -1, //ALOAD_3
    // 0, //IALOAD
    // 0, //LALOAD
    // 0, //FALOAD
    // 0, //DALOAD
    // 0, //AALOAD
    // 0, //BALOAD
    // 0, //CALOAD
    // 0, //SALOAD
    // 2, //ISTORE
    // 2, //LSTORE
    // 2, //FSTORE
    // 2, //DSTORE
    // 2, //ASTORE
    // -1, //ISTORE_0
    // -1, //ISTORE_1
    // -1, //ISTORE_2
    // -1, //ISTORE_3
    // -1, //LSTORE_0
    // -1, //LSTORE_1
    // -1, //LSTORE_2
    // -1, //LSTORE_3
    // -1, //FSTORE_0
    // -1, //FSTORE_1
    // -1, //FSTORE_2
    // -1, //FSTORE_3
    // -1, //DSTORE_0
    // -1, //DSTORE_1
    // -1, //DSTORE_2
    // -1, //DSTORE_3
    // -1, //ASTORE_0
    // -1, //ASTORE_1
    // -1, //ASTORE_2
    // -1, //ASTORE_3
    // 0, //IASTORE
    // 0, //LASTORE
    // 0, //FASTORE
    // 0, //DASTORE
    // 0, //AASTORE
    // 0, //BASTORE
    // 0, //CASTORE
    // 0, //SASTORE
    // 0, //POP
    // 0, //POP2
    // 0, //DUP
    // 0, //DUP_X1
    // 0, //DUP_X2
    // 0, //DUP2
    // 0, //DUP2_X1
    // 0, //DUP2_X2
    // 0, //SWAP
    // 0, //IADD
    // 0, //LADD
    // 0, //FADD
    // 0, //DADD
    // 0, //ISUB
    // 0, //LSUB
    // 0, //FSUB
    // 0, //DSUB
    // 0, //IMUL
    // 0, //LMUL
    // 0, //FMUL
    // 0, //DMUL
    // 0, //IDIV
    // 0, //LDIV
    // 0, //FDIV
    // 0, //DDIV
    // 0, //IREM
    // 0, //LREM
    // 0, //FREM
    // 0, //DREM
    // 0, //INEG
    // 0, //LNEG
    // 0, //FNEG
    // 0, //DNEG
    // 0, //ISHL
    // 0, //LSHL
    // 0, //ISHR
    // 0, //LSHR
    // 0, //IUSHR
    // 0, //LUSHR
    // 0, //IAND
    // 0, //LAND
    // 0, //IOR
    // 0, //LOR
    // 0, //IXOR
    // 0, //LXOR
    // 8, //IINC
    // 0, //I2L
    // 0, //I2F
    // 0, //I2D
    // 0, //L2I
    // 0, //L2F
    // 0, //L2D
    // 0, //F2I
    // 0, //F2L
    // 0, //F2D
    // 0, //D2I
    // 0, //D2L
    // 0, //D2F
    // 0, //I2B
    // 0, //I2C
    // 0, //I2S
    // 0, //LCMP
    // 0, //FCMPL
    // 0, //FCMPG
    // 0, //DCMPL
    // 0, //DCMPG
    // 6, //IFEQ
    // 6, //IFNE
    // 6, //IFLT
    // 6, //IFGE
    // 6, //IFGT
    // 6, //IFLE
    // 6, //IF_ICMPEQ
    // 6, //IF_ICMPNE
    // 6, //IF_ICMPLT
    // 6, //IF_ICMPGE
    // 6, //IF_ICMPGT
    // 6, //IF_ICMPLE
    // 6, //IF_ACMPEQ
    // 6, //IF_ACMPNE
    // 6, //GOTO
    // 6, //JSR
    // 2, //RET
    // 9, //TABLESWITCH
    // 10, //LOOKUPSWITCH
    // 0, //IRETURN
    // 0, //LRETURN
    // 0, //FRETURN
    // 0, //DRETURN
    // 0, //ARETURN
    // 0, //RETURN
    // 4, //GETSTATIC
    // 4, //PUTSTATIC
    // 4, //GETFIELD
    // 4, //PUTFIELD
    // 5, //INVOKEVIRTUAL
    // 5, //INVOKESPECIAL
    // 5, //INVOKESTATIC
    // 5, //INVOKEINTERFACE
    // -1, //INVOKEDYNAMIC
    // 3, //NEW
    // 1, //NEWARRAY
    // 3, //ANEWARRAY
    // 0, //ARRAYLENGTH
    // 0, //ATHROW
    // 3, //CHECKCAST
    // 3, //INSTANCEOF
    // 0, //MONITORENTER
    // 0, //MONITOREXIT
    // -1, //WIDE
    // 11, //MULTIANEWARRAY
    // 6, //IFNULL
    // 6, //IFNONNULL
    // -1, //GOTO_W
    // -1 //JSR_W
    // };
    // for (int i = 0; i < TYPE.length; ++i) {
    // System.out.print((chbr)(TYPE[i] + 1 + 'A'));
    // }
    // System.out.println();
    // }

    /**
     * Constructs b new {@link CheckMethodAdbpter} object. This method bdbpter
     * will not perform bny dbtb flow check (see
     * {@link #CheckMethodAdbpter(int,String,String,MethodVisitor,Mbp)}).
     * <i>Subclbsses must not use this constructor</i>. Instebd, they must use
     * the {@link #CheckMethodAdbpter(int, MethodVisitor, Mbp)} version.
     *
     * @pbrbm mv
     *            the method visitor to which this bdbpter must delegbte cblls.
     */
    public CheckMethodAdbpter(finbl MethodVisitor mv) {
        this(mv, new HbshMbp<Lbbel, Integer>());
    }

    /**
     * Constructs b new {@link CheckMethodAdbpter} object. This method bdbpter
     * will not perform bny dbtb flow check (see
     * {@link #CheckMethodAdbpter(int,String,String,MethodVisitor,Mbp)}).
     * <i>Subclbsses must not use this constructor</i>. Instebd, they must use
     * the {@link #CheckMethodAdbpter(int, MethodVisitor, Mbp)} version.
     *
     * @pbrbm mv
     *            the method visitor to which this bdbpter must delegbte cblls.
     * @pbrbm lbbels
     *            b mbp of blrebdy visited lbbels (in other methods).
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public CheckMethodAdbpter(finbl MethodVisitor mv,
            finbl Mbp<Lbbel, Integer> lbbels) {
        this(Opcodes.ASM5, mv, lbbels);
        if (getClbss() != CheckMethodAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Constructs b new {@link CheckMethodAdbpter} object. This method bdbpter
     * will not perform bny dbtb flow check (see
     * {@link #CheckMethodAdbpter(int,String,String,MethodVisitor,Mbp)}).
     *
     * @pbrbm mv
     *            the method visitor to which this bdbpter must delegbte cblls.
     * @pbrbm lbbels
     *            b mbp of blrebdy visited lbbels (in other methods).
     */
    protected CheckMethodAdbpter(finbl int bpi, finbl MethodVisitor mv,
            finbl Mbp<Lbbel, Integer> lbbels) {
        super(bpi, mv);
        this.lbbels = lbbels;
        this.usedLbbels = new HbshSet<Lbbel>();
        this.hbndlers = new ArrbyList<Lbbel>();
    }

    /**
     * Constructs b new {@link CheckMethodAdbpter} object. This method bdbpter
     * will perform bbsic dbtb flow checks. For instbnce in b method whose
     * signbture is <tt>void m ()</tt>, the invblid instruction IRETURN, or the
     * invblid sequence IADD L2I will be detected.
     *
     * @pbrbm bccess
     *            the method's bccess flbgs.
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm cmv
     *            the method visitor to which this bdbpter must delegbte cblls.
     * @pbrbm lbbels
     *            b mbp of blrebdy visited lbbels (in other methods).
     */
    public CheckMethodAdbpter(finbl int bccess, finbl String nbme,
            finbl String desc, finbl MethodVisitor cmv,
            finbl Mbp<Lbbel, Integer> lbbels) {
        this(new MethodNode(Opcodes.ASM5, bccess, nbme, desc, null, null) {
            @Override
            public void visitEnd() {
                Anblyzer<BbsicVblue> b = new Anblyzer<BbsicVblue>(
                        new BbsicVerifier());
                try {
                    b.bnblyze("dummy", this);
                } cbtch (Exception e) {
                    if (e instbnceof IndexOutOfBoundsException
                            && mbxLocbls == 0 && mbxStbck == 0) {
                        throw new RuntimeException(
                                "Dbtb flow checking option requires vblid, non zero mbxLocbls bnd mbxStbck vblues.");
                    }
                    e.printStbckTrbce();
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw, true);
                    CheckClbssAdbpter.printAnblyzerResult(this, b, pw);
                    pw.close();
                    throw new RuntimeException(e.getMessbge() + ' '
                            + sw.toString());
                }
                bccept(cmv);
            }
        }, lbbels);
        this.bccess = bccess;
    }

    @Override
    public void visitPbrbmeter(String nbme, int bccess) {
        if (nbme != null) {
            checkUnqublifiedNbme(version, nbme, "nbme");
        }
        CheckClbssAdbpter.checkAccess(bccess, Opcodes.ACC_FINAL
                + Opcodes.ACC_MANDATED + Opcodes.ACC_SYNTHETIC);
        super.visitPbrbmeter(nbme, bccess);
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        checkEndMethod();
        checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitAnnotbtion(desc, visible));
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        checkEndMethod();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.METHOD_TYPE_PARAMETER
                && sort != TypeReference.METHOD_TYPE_PARAMETER_BOUND
                && sort != TypeReference.METHOD_RETURN
                && sort != TypeReference.METHOD_RECEIVER
                && sort != TypeReference.METHOD_FORMAL_PARAMETER
                && sort != TypeReference.THROWS) {
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        CheckClbssAdbpter.checkTypeRefAndPbth(typeRef, typePbth);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitTypeAnnotbtion(typeRef,
                typePbth, desc, visible));
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtionDefbult() {
        checkEndMethod();
        return new CheckAnnotbtionAdbpter(super.visitAnnotbtionDefbult(), fblse);
    }

    @Override
    public AnnotbtionVisitor visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        checkEndMethod();
        checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitPbrbmeterAnnotbtion(
                pbrbmeter, desc, visible));
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        checkEndMethod();
        if (bttr == null) {
            throw new IllegblArgumentException(
                    "Invblid bttribute (must not be null)");
        }
        super.visitAttribute(bttr);
    }

    @Override
    public void visitCode() {
        if ((bccess & Opcodes.ACC_ABSTRACT) != 0) {
            throw new RuntimeException("Abstrbct methods cbnnot hbve code");
        }
        stbrtCode = true;
        super.visitCode();
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        if (insnCount == lbstFrbme) {
            throw new IllegblStbteException(
                    "At most one frbme cbn be visited bt b given code locbtion.");
        }
        lbstFrbme = insnCount;
        int mLocbl;
        int mStbck;
        switch (type) {
        cbse Opcodes.F_NEW:
        cbse Opcodes.F_FULL:
            mLocbl = Integer.MAX_VALUE;
            mStbck = Integer.MAX_VALUE;
            brebk;

        cbse Opcodes.F_SAME:
            mLocbl = 0;
            mStbck = 0;
            brebk;

        cbse Opcodes.F_SAME1:
            mLocbl = 0;
            mStbck = 1;
            brebk;

        cbse Opcodes.F_APPEND:
        cbse Opcodes.F_CHOP:
            mLocbl = 3;
            mStbck = 0;
            brebk;

        defbult:
            throw new IllegblArgumentException("Invblid frbme type " + type);
        }

        if (nLocbl > mLocbl) {
            throw new IllegblArgumentException("Invblid nLocbl=" + nLocbl
                    + " for frbme type " + type);
        }
        if (nStbck > mStbck) {
            throw new IllegblArgumentException("Invblid nStbck=" + nStbck
                    + " for frbme type " + type);
        }

        if (type != Opcodes.F_CHOP) {
            if (nLocbl > 0 && (locbl == null || locbl.length < nLocbl)) {
                throw new IllegblArgumentException(
                        "Arrby locbl[] is shorter thbn nLocbl");
            }
            for (int i = 0; i < nLocbl; ++i) {
                checkFrbmeVblue(locbl[i]);
            }
        }
        if (nStbck > 0 && (stbck == null || stbck.length < nStbck)) {
            throw new IllegblArgumentException(
                    "Arrby stbck[] is shorter thbn nStbck");
        }
        for (int i = 0; i < nStbck; ++i) {
            checkFrbmeVblue(stbck[i]);
        }
        if (type == Opcodes.F_NEW) {
            ++expbndedFrbmes;
        } else {
            ++compressedFrbmes;
        }
        if (expbndedFrbmes > 0 && compressedFrbmes > 0) {
            throw new RuntimeException(
                    "Expbnded bnd compressed frbmes must not be mixed.");
        }
        super.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
    }

    @Override
    public void visitInsn(finbl int opcode) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 0);
        super.visitInsn(opcode);
        ++insnCount;
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 1);
        switch (opcode) {
        cbse Opcodes.BIPUSH:
            checkSignedByte(operbnd, "Invblid operbnd");
            brebk;
        cbse Opcodes.SIPUSH:
            checkSignedShort(operbnd, "Invblid operbnd");
            brebk;
        // cbse Constbnts.NEWARRAY:
        defbult:
            if (operbnd < Opcodes.T_BOOLEAN || operbnd > Opcodes.T_LONG) {
                throw new IllegblArgumentException(
                        "Invblid operbnd (must be bn brrby type code T_...): "
                                + operbnd);
            }
        }
        super.visitIntInsn(opcode, operbnd);
        ++insnCount;
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 2);
        checkUnsignedShort(vbr, "Invblid vbribble index");
        super.visitVbrInsn(opcode, vbr);
        ++insnCount;
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 3);
        checkInternblNbme(type, "type");
        if (opcode == Opcodes.NEW && type.chbrAt(0) == '[') {
            throw new IllegblArgumentException(
                    "NEW cbnnot be used to crebte brrbys: " + type);
        }
        super.visitTypeInsn(opcode, type);
        ++insnCount;
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 4);
        checkInternblNbme(owner, "owner");
        checkUnqublifiedNbme(version, nbme, "nbme");
        checkDesc(desc, fblse);
        super.visitFieldInsn(opcode, owner, nbme, desc);
        ++insnCount;
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc,
                opcode == Opcodes.INVOKEINTERFACE);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc, boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc, itf);
    }

    privbte void doVisitMethodInsn(int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 5);
        if (opcode != Opcodes.INVOKESPECIAL || !"<init>".equbls(nbme)) {
            checkMethodIdentifier(version, nbme, "nbme");
        }
        checkInternblNbme(owner, "owner");
        checkMethodDesc(desc);
        if (opcode == Opcodes.INVOKEVIRTUAL && itf) {
            throw new IllegblArgumentException(
                    "INVOKEVIRTUAL cbn't be used with interfbces");
        }
        if (opcode == Opcodes.INVOKEINTERFACE && !itf) {
            throw new IllegblArgumentException(
                    "INVOKEINTERFACE cbn't be used with clbsses");
        }
        // Cblling super.visitMethodInsn requires to cbll the correct version
        // depending on this.bpi (otherwise infinite loops cbn occur). To
        // simplify bnd to mbke it ebsier to butombticblly remove the bbckwbrd
        // compbtibility code, we inline the code of the overridden method here.
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        }
        ++insnCount;
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        checkStbrtCode();
        checkEndCode();
        checkMethodIdentifier(version, nbme, "nbme");
        checkMethodDesc(desc);
        if (bsm.getTbg() != Opcodes.H_INVOKESTATIC
                && bsm.getTbg() != Opcodes.H_NEWINVOKESPECIAL) {
            throw new IllegblArgumentException("invblid hbndle tbg "
                    + bsm.getTbg());
        }
        for (int i = 0; i < bsmArgs.length; i++) {
            checkLDCConstbnt(bsmArgs[i]);
        }
        super.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        ++insnCount;
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        checkStbrtCode();
        checkEndCode();
        checkOpcode(opcode, 6);
        checkLbbel(lbbel, fblse, "lbbel");
        checkNonDebugLbbel(lbbel);
        super.visitJumpInsn(opcode, lbbel);
        usedLbbels.bdd(lbbel);
        ++insnCount;
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        checkStbrtCode();
        checkEndCode();
        checkLbbel(lbbel, fblse, "lbbel");
        if (lbbels.get(lbbel) != null) {
            throw new IllegblArgumentException("Alrebdy visited lbbel");
        }
        lbbels.put(lbbel, new Integer(insnCount));
        super.visitLbbel(lbbel);
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        checkStbrtCode();
        checkEndCode();
        checkLDCConstbnt(cst);
        super.visitLdcInsn(cst);
        ++insnCount;
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        checkStbrtCode();
        checkEndCode();
        checkUnsignedShort(vbr, "Invblid vbribble index");
        checkSignedShort(increment, "Invblid increment");
        super.visitIincInsn(vbr, increment);
        ++insnCount;
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        checkStbrtCode();
        checkEndCode();
        if (mbx < min) {
            throw new IllegblArgumentException("Mbx = " + mbx
                    + " must be grebter thbn or equbl to min = " + min);
        }
        checkLbbel(dflt, fblse, "defbult lbbel");
        checkNonDebugLbbel(dflt);
        if (lbbels == null || lbbels.length != mbx - min + 1) {
            throw new IllegblArgumentException(
                    "There must be mbx - min + 1 lbbels");
        }
        for (int i = 0; i < lbbels.length; ++i) {
            checkLbbel(lbbels[i], fblse, "lbbel bt index " + i);
            checkNonDebugLbbel(lbbels[i]);
        }
        super.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        for (int i = 0; i < lbbels.length; ++i) {
            usedLbbels.bdd(lbbels[i]);
        }
        ++insnCount;
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        checkEndCode();
        checkStbrtCode();
        checkLbbel(dflt, fblse, "defbult lbbel");
        checkNonDebugLbbel(dflt);
        if (keys == null || lbbels == null || keys.length != lbbels.length) {
            throw new IllegblArgumentException(
                    "There must be the sbme number of keys bnd lbbels");
        }
        for (int i = 0; i < lbbels.length; ++i) {
            checkLbbel(lbbels[i], fblse, "lbbel bt index " + i);
            checkNonDebugLbbel(lbbels[i]);
        }
        super.visitLookupSwitchInsn(dflt, keys, lbbels);
        usedLbbels.bdd(dflt);
        for (int i = 0; i < lbbels.length; ++i) {
            usedLbbels.bdd(lbbels[i]);
        }
        ++insnCount;
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        checkStbrtCode();
        checkEndCode();
        checkDesc(desc, fblse);
        if (desc.chbrAt(0) != '[') {
            throw new IllegblArgumentException(
                    "Invblid descriptor (must be bn brrby type descriptor): "
                            + desc);
        }
        if (dims < 1) {
            throw new IllegblArgumentException(
                    "Invblid dimensions (must be grebter thbn 0): " + dims);
        }
        if (dims > desc.lbstIndexOf('[') + 1) {
            throw new IllegblArgumentException(
                    "Invblid dimensions (must not be grebter thbn dims(desc)): "
                            + dims);
        }
        super.visitMultiANewArrbyInsn(desc, dims);
        ++insnCount;
    }

    @Override
    public AnnotbtionVisitor visitInsnAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        checkStbrtCode();
        checkEndCode();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.INSTANCEOF && sort != TypeReference.NEW
                && sort != TypeReference.CONSTRUCTOR_REFERENCE
                && sort != TypeReference.METHOD_REFERENCE
                && sort != TypeReference.CAST
                && sort != TypeReference.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
                && sort != TypeReference.METHOD_INVOCATION_TYPE_ARGUMENT
                && sort != TypeReference.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
                && sort != TypeReference.METHOD_REFERENCE_TYPE_ARGUMENT) {
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        CheckClbssAdbpter.checkTypeRefAndPbth(typeRef, typePbth);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitInsnAnnotbtion(typeRef,
                typePbth, desc, visible));
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        checkStbrtCode();
        checkEndCode();
        checkLbbel(stbrt, fblse, "stbrt lbbel");
        checkLbbel(end, fblse, "end lbbel");
        checkLbbel(hbndler, fblse, "hbndler lbbel");
        checkNonDebugLbbel(stbrt);
        checkNonDebugLbbel(end);
        checkNonDebugLbbel(hbndler);
        if (lbbels.get(stbrt) != null || lbbels.get(end) != null
                || lbbels.get(hbndler) != null) {
            throw new IllegblStbteException(
                    "Try cbtch blocks must be visited before their lbbels");
        }
        if (type != null) {
            checkInternblNbme(type, "type");
        }
        super.visitTryCbtchBlock(stbrt, end, hbndler, type);
        hbndlers.bdd(stbrt);
        hbndlers.bdd(end);
    }

    @Override
    public AnnotbtionVisitor visitTryCbtchAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        checkStbrtCode();
        checkEndCode();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.EXCEPTION_PARAMETER) {
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        CheckClbssAdbpter.checkTypeRefAndPbth(typeRef, typePbth);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(super.visitTryCbtchAnnotbtion(
                typeRef, typePbth, desc, visible));
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        checkStbrtCode();
        checkEndCode();
        checkUnqublifiedNbme(version, nbme, "nbme");
        checkDesc(desc, fblse);
        checkLbbel(stbrt, true, "stbrt lbbel");
        checkLbbel(end, true, "end lbbel");
        checkUnsignedShort(index, "Invblid vbribble index");
        int s = lbbels.get(stbrt).intVblue();
        int e = lbbels.get(end).intVblue();
        if (e < s) {
            throw new IllegblArgumentException(
                    "Invblid stbrt bnd end lbbels (end must be grebter thbn stbrt)");
        }
        super.visitLocblVbribble(nbme, desc, signbture, stbrt, end, index);
    }

    @Override
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        checkStbrtCode();
        checkEndCode();
        int sort = typeRef >>> 24;
        if (sort != TypeReference.LOCAL_VARIABLE
                && sort != TypeReference.RESOURCE_VARIABLE) {
            throw new IllegblArgumentException("Invblid type reference sort 0x"
                    + Integer.toHexString(sort));
        }
        CheckClbssAdbpter.checkTypeRefAndPbth(typeRef, typePbth);
        checkDesc(desc, fblse);
        if (stbrt == null || end == null || index == null
                || end.length != stbrt.length || index.length != stbrt.length) {
            throw new IllegblArgumentException(
                    "Invblid stbrt, end bnd index brrbys (must be non null bnd of identicbl length");
        }
        for (int i = 0; i < stbrt.length; ++i) {
            checkLbbel(stbrt[i], true, "stbrt lbbel");
            checkLbbel(end[i], true, "end lbbel");
            checkUnsignedShort(index[i], "Invblid vbribble index");
            int s = lbbels.get(stbrt[i]).intVblue();
            int e = lbbels.get(end[i]).intVblue();
            if (e < s) {
                throw new IllegblArgumentException(
                        "Invblid stbrt bnd end lbbels (end must be grebter thbn stbrt)");
            }
        }
        return super.visitLocblVbribbleAnnotbtion(typeRef, typePbth, stbrt,
                end, index, desc, visible);
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        checkStbrtCode();
        checkEndCode();
        checkUnsignedShort(line, "Invblid line number");
        checkLbbel(stbrt, true, "stbrt lbbel");
        super.visitLineNumber(line, stbrt);
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        checkStbrtCode();
        checkEndCode();
        endCode = true;
        for (Lbbel l : usedLbbels) {
            if (lbbels.get(l) == null) {
                throw new IllegblStbteException("Undefined lbbel used");
            }
        }
        for (int i = 0; i < hbndlers.size();) {
            Integer stbrt = lbbels.get(hbndlers.get(i++));
            Integer end = lbbels.get(hbndlers.get(i++));
            if (stbrt == null || end == null) {
                throw new IllegblStbteException(
                        "Undefined try cbtch block lbbels");
            }
            if (end.intVblue() <= stbrt.intVblue()) {
                throw new IllegblStbteException(
                        "Emty try cbtch block hbndler rbnge");
            }
        }
        checkUnsignedShort(mbxStbck, "Invblid mbx stbck");
        checkUnsignedShort(mbxLocbls, "Invblid mbx locbls");
        super.visitMbxs(mbxStbck, mbxLocbls);
    }

    @Override
    public void visitEnd() {
        checkEndMethod();
        endMethod = true;
        super.visitEnd();
    }

    // -------------------------------------------------------------------------

    /**
     * Checks thbt the visitCode method hbs been cblled.
     */
    void checkStbrtCode() {
        if (!stbrtCode) {
            throw new IllegblStbteException(
                    "Cbnnot visit instructions before visitCode hbs been cblled.");
        }
    }

    /**
     * Checks thbt the visitMbxs method hbs not been cblled.
     */
    void checkEndCode() {
        if (endCode) {
            throw new IllegblStbteException(
                    "Cbnnot visit instructions bfter visitMbxs hbs been cblled.");
        }
    }

    /**
     * Checks thbt the visitEnd method hbs not been cblled.
     */
    void checkEndMethod() {
        if (endMethod) {
            throw new IllegblStbteException(
                    "Cbnnot visit elements bfter visitEnd hbs been cblled.");
        }
    }

    /**
     * Checks b stbck frbme vblue.
     *
     * @pbrbm vblue
     *            the vblue to be checked.
     */
    void checkFrbmeVblue(finbl Object vblue) {
        if (vblue == Opcodes.TOP || vblue == Opcodes.INTEGER
                || vblue == Opcodes.FLOAT || vblue == Opcodes.LONG
                || vblue == Opcodes.DOUBLE || vblue == Opcodes.NULL
                || vblue == Opcodes.UNINITIALIZED_THIS) {
            return;
        }
        if (vblue instbnceof String) {
            checkInternblNbme((String) vblue, "Invblid stbck frbme vblue");
            return;
        }
        if (!(vblue instbnceof Lbbel)) {
            throw new IllegblArgumentException("Invblid stbck frbme vblue: "
                    + vblue);
        } else {
            usedLbbels.bdd((Lbbel) vblue);
        }
    }

    /**
     * Checks thbt the type of the given opcode is equbl to the given type.
     *
     * @pbrbm opcode
     *            the opcode to be checked.
     * @pbrbm type
     *            the expected opcode type.
     */
    stbtic void checkOpcode(finbl int opcode, finbl int type) {
        if (opcode < 0 || opcode > 199 || TYPE[opcode] != type) {
            throw new IllegblArgumentException("Invblid opcode: " + opcode);
        }
    }

    /**
     * Checks thbt the given vblue is b signed byte.
     *
     * @pbrbm vblue
     *            the vblue to be checked.
     * @pbrbm msg
     *            bn messbge to be used in cbse of error.
     */
    stbtic void checkSignedByte(finbl int vblue, finbl String msg) {
        if (vblue < Byte.MIN_VALUE || vblue > Byte.MAX_VALUE) {
            throw new IllegblArgumentException(msg
                    + " (must be b signed byte): " + vblue);
        }
    }

    /**
     * Checks thbt the given vblue is b signed short.
     *
     * @pbrbm vblue
     *            the vblue to be checked.
     * @pbrbm msg
     *            bn messbge to be used in cbse of error.
     */
    stbtic void checkSignedShort(finbl int vblue, finbl String msg) {
        if (vblue < Short.MIN_VALUE || vblue > Short.MAX_VALUE) {
            throw new IllegblArgumentException(msg
                    + " (must be b signed short): " + vblue);
        }
    }

    /**
     * Checks thbt the given vblue is bn unsigned short.
     *
     * @pbrbm vblue
     *            the vblue to be checked.
     * @pbrbm msg
     *            bn messbge to be used in cbse of error.
     */
    stbtic void checkUnsignedShort(finbl int vblue, finbl String msg) {
        if (vblue < 0 || vblue > 65535) {
            throw new IllegblArgumentException(msg
                    + " (must be bn unsigned short): " + vblue);
        }
    }

    /**
     * Checks thbt the given vblue is bn {@link Integer}, b{@link Flobt}, b
     * {@link Long}, b {@link Double} or b {@link String}.
     *
     * @pbrbm cst
     *            the vblue to be checked.
     */
    stbtic void checkConstbnt(finbl Object cst) {
        if (!(cst instbnceof Integer) && !(cst instbnceof Flobt)
                && !(cst instbnceof Long) && !(cst instbnceof Double)
                && !(cst instbnceof String)) {
            throw new IllegblArgumentException("Invblid constbnt: " + cst);
        }
    }

    void checkLDCConstbnt(finbl Object cst) {
        if (cst instbnceof Type) {
            int s = ((Type) cst).getSort();
            if (s != Type.OBJECT && s != Type.ARRAY && s != Type.METHOD) {
                throw new IllegblArgumentException("Illegbl LDC constbnt vblue");
            }
            if (s != Type.METHOD && (version & 0xFFFF) < Opcodes.V1_5) {
                throw new IllegblArgumentException(
                        "ldc of b constbnt clbss requires bt lebst version 1.5");
            }
            if (s == Type.METHOD && (version & 0xFFFF) < Opcodes.V1_7) {
                throw new IllegblArgumentException(
                        "ldc of b method type requires bt lebst version 1.7");
            }
        } else if (cst instbnceof Hbndle) {
            if ((version & 0xFFFF) < Opcodes.V1_7) {
                throw new IllegblArgumentException(
                        "ldc of b hbndle requires bt lebst version 1.7");
            }
            int tbg = ((Hbndle) cst).getTbg();
            if (tbg < Opcodes.H_GETFIELD || tbg > Opcodes.H_INVOKEINTERFACE) {
                throw new IllegblArgumentException("invblid hbndle tbg " + tbg);
            }
        } else {
            checkConstbnt(cst);
        }
    }

    /**
     * Checks thbt the given string is b vblid unqublified nbme.
     *
     * @pbrbm version
     *            the clbss version.
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkUnqublifiedNbme(int version, finbl String nbme,
            finbl String msg) {
        if ((version & 0xFFFF) < Opcodes.V1_5) {
            checkIdentifier(nbme, msg);
        } else {
            for (int i = 0; i < nbme.length(); ++i) {
                if (".;[/".indexOf(nbme.chbrAt(i)) != -1) {
                    throw new IllegblArgumentException("Invblid " + msg
                            + " (must be b vblid unqublified nbme): " + nbme);
                }
            }
        }
    }

    /**
     * Checks thbt the given string is b vblid Jbvb identifier.
     *
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkIdentifier(finbl String nbme, finbl String msg) {
        checkIdentifier(nbme, 0, -1, msg);
    }

    /**
     * Checks thbt the given substring is b vblid Jbvb identifier.
     *
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm stbrt
     *            index of the first chbrbcter of the identifier (inclusive).
     * @pbrbm end
     *            index of the lbst chbrbcter of the identifier (exclusive). -1
     *            is equivblent to <tt>nbme.length()</tt> if nbme is not
     *            <tt>null</tt>.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkIdentifier(finbl String nbme, finbl int stbrt,
            finbl int end, finbl String msg) {
        if (nbme == null || (end == -1 ? nbme.length() <= stbrt : end <= stbrt)) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must not be null or empty)");
        }
        if (!Chbrbcter.isJbvbIdentifierStbrt(nbme.chbrAt(stbrt))) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must be b vblid Jbvb identifier): " + nbme);
        }
        int mbx = end == -1 ? nbme.length() : end;
        for (int i = stbrt + 1; i < mbx; ++i) {
            if (!Chbrbcter.isJbvbIdentifierPbrt(nbme.chbrAt(i))) {
                throw new IllegblArgumentException("Invblid " + msg
                        + " (must be b vblid Jbvb identifier): " + nbme);
            }
        }
    }

    /**
     * Checks thbt the given string is b vblid Jbvb identifier.
     *
     * @pbrbm version
     *            the clbss version.
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkMethodIdentifier(int version, finbl String nbme,
            finbl String msg) {
        if (nbme == null || nbme.length() == 0) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must not be null or empty)");
        }
        if ((version & 0xFFFF) >= Opcodes.V1_5) {
            for (int i = 0; i < nbme.length(); ++i) {
                if (".;[/<>".indexOf(nbme.chbrAt(i)) != -1) {
                    throw new IllegblArgumentException("Invblid " + msg
                            + " (must be b vblid unqublified nbme): " + nbme);
                }
            }
            return;
        }
        if (!Chbrbcter.isJbvbIdentifierStbrt(nbme.chbrAt(0))) {
            throw new IllegblArgumentException(
                    "Invblid "
                            + msg
                            + " (must be b '<init>', '<clinit>' or b vblid Jbvb identifier): "
                            + nbme);
        }
        for (int i = 1; i < nbme.length(); ++i) {
            if (!Chbrbcter.isJbvbIdentifierPbrt(nbme.chbrAt(i))) {
                throw new IllegblArgumentException(
                        "Invblid "
                                + msg
                                + " (must be '<init>' or '<clinit>' or b vblid Jbvb identifier): "
                                + nbme);
            }
        }
    }

    /**
     * Checks thbt the given string is b vblid internbl clbss nbme.
     *
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkInternblNbme(finbl String nbme, finbl String msg) {
        if (nbme == null || nbme.length() == 0) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must not be null or empty)");
        }
        if (nbme.chbrAt(0) == '[') {
            checkDesc(nbme, fblse);
        } else {
            checkInternblNbme(nbme, 0, -1, msg);
        }
    }

    /**
     * Checks thbt the given substring is b vblid internbl clbss nbme.
     *
     * @pbrbm nbme
     *            the string to be checked.
     * @pbrbm stbrt
     *            index of the first chbrbcter of the identifier (inclusive).
     * @pbrbm end
     *            index of the lbst chbrbcter of the identifier (exclusive). -1
     *            is equivblent to <tt>nbme.length()</tt> if nbme is not
     *            <tt>null</tt>.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    stbtic void checkInternblNbme(finbl String nbme, finbl int stbrt,
            finbl int end, finbl String msg) {
        int mbx = end == -1 ? nbme.length() : end;
        try {
            int begin = stbrt;
            int slbsh;
            do {
                slbsh = nbme.indexOf('/', begin + 1);
                if (slbsh == -1 || slbsh > mbx) {
                    slbsh = mbx;
                }
                checkIdentifier(nbme, begin, slbsh, null);
                begin = slbsh + 1;
            } while (slbsh != mbx);
        } cbtch (IllegblArgumentException unused) {
            throw new IllegblArgumentException(
                    "Invblid "
                            + msg
                            + " (must be b fully qublified clbss nbme in internbl form): "
                            + nbme);
        }
    }

    /**
     * Checks thbt the given string is b vblid type descriptor.
     *
     * @pbrbm desc
     *            the string to be checked.
     * @pbrbm cbnBeVoid
     *            <tt>true</tt> if <tt>V</tt> cbn be considered vblid.
     */
    stbtic void checkDesc(finbl String desc, finbl boolebn cbnBeVoid) {
        int end = checkDesc(desc, 0, cbnBeVoid);
        if (end != desc.length()) {
            throw new IllegblArgumentException("Invblid descriptor: " + desc);
        }
    }

    /**
     * Checks thbt b the given substring is b vblid type descriptor.
     *
     * @pbrbm desc
     *            the string to be checked.
     * @pbrbm stbrt
     *            index of the first chbrbcter of the identifier (inclusive).
     * @pbrbm cbnBeVoid
     *            <tt>true</tt> if <tt>V</tt> cbn be considered vblid.
     * @return the index of the lbst chbrbcter of the type decriptor, plus one.
     */
    stbtic int checkDesc(finbl String desc, finbl int stbrt,
            finbl boolebn cbnBeVoid) {
        if (desc == null || stbrt >= desc.length()) {
            throw new IllegblArgumentException(
                    "Invblid type descriptor (must not be null or empty)");
        }
        int index;
        switch (desc.chbrAt(stbrt)) {
        cbse 'V':
            if (cbnBeVoid) {
                return stbrt + 1;
            } else {
                throw new IllegblArgumentException("Invblid descriptor: "
                        + desc);
            }
        cbse 'Z':
        cbse 'C':
        cbse 'B':
        cbse 'S':
        cbse 'I':
        cbse 'F':
        cbse 'J':
        cbse 'D':
            return stbrt + 1;
        cbse '[':
            index = stbrt + 1;
            while (index < desc.length() && desc.chbrAt(index) == '[') {
                ++index;
            }
            if (index < desc.length()) {
                return checkDesc(desc, index, fblse);
            } else {
                throw new IllegblArgumentException("Invblid descriptor: "
                        + desc);
            }
        cbse 'L':
            index = desc.indexOf(';', stbrt);
            if (index == -1 || index - stbrt < 2) {
                throw new IllegblArgumentException("Invblid descriptor: "
                        + desc);
            }
            try {
                checkInternblNbme(desc, stbrt + 1, index, null);
            } cbtch (IllegblArgumentException unused) {
                throw new IllegblArgumentException("Invblid descriptor: "
                        + desc);
            }
            return index + 1;
        defbult:
            throw new IllegblArgumentException("Invblid descriptor: " + desc);
        }
    }

    /**
     * Checks thbt the given string is b vblid method descriptor.
     *
     * @pbrbm desc
     *            the string to be checked.
     */
    stbtic void checkMethodDesc(finbl String desc) {
        if (desc == null || desc.length() == 0) {
            throw new IllegblArgumentException(
                    "Invblid method descriptor (must not be null or empty)");
        }
        if (desc.chbrAt(0) != '(' || desc.length() < 3) {
            throw new IllegblArgumentException("Invblid descriptor: " + desc);
        }
        int stbrt = 1;
        if (desc.chbrAt(stbrt) != ')') {
            do {
                if (desc.chbrAt(stbrt) == 'V') {
                    throw new IllegblArgumentException("Invblid descriptor: "
                            + desc);
                }
                stbrt = checkDesc(desc, stbrt, fblse);
            } while (stbrt < desc.length() && desc.chbrAt(stbrt) != ')');
        }
        stbrt = checkDesc(desc, stbrt + 1, true);
        if (stbrt != desc.length()) {
            throw new IllegblArgumentException("Invblid descriptor: " + desc);
        }
    }

    /**
     * Checks thbt the given lbbel is not null. This method cbn blso check thbt
     * the lbbel hbs been visited.
     *
     * @pbrbm lbbel
     *            the lbbel to be checked.
     * @pbrbm checkVisited
     *            <tt>true</tt> to check thbt the lbbel hbs been visited.
     * @pbrbm msg
     *            b messbge to be used in cbse of error.
     */
    void checkLbbel(finbl Lbbel lbbel, finbl boolebn checkVisited,
            finbl String msg) {
        if (lbbel == null) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must not be null)");
        }
        if (checkVisited && lbbels.get(lbbel) == null) {
            throw new IllegblArgumentException("Invblid " + msg
                    + " (must be visited first)");
        }
    }

    /**
     * Checks thbt the given lbbel is not b lbbel used only for debug purposes.
     *
     * @pbrbm lbbel
     *            the lbbel to be checked.
     */
    privbte stbtic void checkNonDebugLbbel(finbl Lbbel lbbel) {
        Field f = getLbbelStbtusField();
        int stbtus = 0;
        try {
            stbtus = f == null ? 0 : ((Integer) f.get(lbbel)).intVblue();
        } cbtch (IllegblAccessException e) {
            throw new Error("Internbl error");
        }
        if ((stbtus & 0x01) != 0) {
            throw new IllegblArgumentException(
                    "Lbbels used for debug info cbnnot be reused for control flow");
        }
    }

    /**
     * Returns the Field object corresponding to the Lbbel.stbtus field.
     *
     * @return the Field object corresponding to the Lbbel.stbtus field.
     */
    privbte stbtic Field getLbbelStbtusField() {
        if (lbbelStbtusField == null) {
            lbbelStbtusField = getLbbelField("b");
            if (lbbelStbtusField == null) {
                lbbelStbtusField = getLbbelField("stbtus");
            }
        }
        return lbbelStbtusField;
    }

    /**
     * Returns the field of the Lbbel clbss whose nbme is given.
     *
     * @pbrbm nbme
     *            b field nbme.
     * @return the field of the Lbbel clbss whose nbme is given, or null.
     */
    privbte stbtic Field getLbbelField(finbl String nbme) {
        try {
            Field f = Lbbel.clbss.getDeclbredField(nbme);
            f.setAccessible(true);
            return f;
        } cbtch (NoSuchFieldException e) {
            return null;
        }
    }
}
