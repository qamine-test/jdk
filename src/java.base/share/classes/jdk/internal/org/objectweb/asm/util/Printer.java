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
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * An bbstrbct converter from visit events to text.
 *
 * @buthor Eric Bruneton
 */
public bbstrbct clbss Printer {

    /**
     * The nbmes of the Jbvb Virtubl Mbchine opcodes.
     */
    public stbtic finbl String[] OPCODES;

    /**
     * The nbmes of the for <code>operbnd</code> pbrbmeter vblues of the
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitIntInsn} method when
     * <code>opcode</code> is <code>NEWARRAY</code>.
     */
    public stbtic finbl String[] TYPES;

    /**
     * The nbmes of the <code>tbg</code> field vblues for
     * {@link jdk.internbl.org.objectweb.bsm.Hbndle}.
     */
    public stbtic finbl String[] HANDLE_TAG;

    stbtic {
        String s = "NOP,ACONST_NULL,ICONST_M1,ICONST_0,ICONST_1,ICONST_2,"
                + "ICONST_3,ICONST_4,ICONST_5,LCONST_0,LCONST_1,FCONST_0,"
                + "FCONST_1,FCONST_2,DCONST_0,DCONST_1,BIPUSH,SIPUSH,LDC,,,"
                + "ILOAD,LLOAD,FLOAD,DLOAD,ALOAD,,,,,,,,,,,,,,,,,,,,,IALOAD,"
                + "LALOAD,FALOAD,DALOAD,AALOAD,BALOAD,CALOAD,SALOAD,ISTORE,"
                + "LSTORE,FSTORE,DSTORE,ASTORE,,,,,,,,,,,,,,,,,,,,,IASTORE,"
                + "LASTORE,FASTORE,DASTORE,AASTORE,BASTORE,CASTORE,SASTORE,POP,"
                + "POP2,DUP,DUP_X1,DUP_X2,DUP2,DUP2_X1,DUP2_X2,SWAP,IADD,LADD,"
                + "FADD,DADD,ISUB,LSUB,FSUB,DSUB,IMUL,LMUL,FMUL,DMUL,IDIV,LDIV,"
                + "FDIV,DDIV,IREM,LREM,FREM,DREM,INEG,LNEG,FNEG,DNEG,ISHL,LSHL,"
                + "ISHR,LSHR,IUSHR,LUSHR,IAND,LAND,IOR,LOR,IXOR,LXOR,IINC,I2L,"
                + "I2F,I2D,L2I,L2F,L2D,F2I,F2L,F2D,D2I,D2L,D2F,I2B,I2C,I2S,LCMP,"
                + "FCMPL,FCMPG,DCMPL,DCMPG,IFEQ,IFNE,IFLT,IFGE,IFGT,IFLE,"
                + "IF_ICMPEQ,IF_ICMPNE,IF_ICMPLT,IF_ICMPGE,IF_ICMPGT,IF_ICMPLE,"
                + "IF_ACMPEQ,IF_ACMPNE,GOTO,JSR,RET,TABLESWITCH,LOOKUPSWITCH,"
                + "IRETURN,LRETURN,FRETURN,DRETURN,ARETURN,RETURN,GETSTATIC,"
                + "PUTSTATIC,GETFIELD,PUTFIELD,INVOKEVIRTUAL,INVOKESPECIAL,"
                + "INVOKESTATIC,INVOKEINTERFACE,INVOKEDYNAMIC,NEW,NEWARRAY,"
                + "ANEWARRAY,ARRAYLENGTH,ATHROW,CHECKCAST,INSTANCEOF,"
                + "MONITORENTER,MONITOREXIT,,MULTIANEWARRAY,IFNULL,IFNONNULL,";
        OPCODES = new String[200];
        int i = 0;
        int j = 0;
        int l;
        while ((l = s.indexOf(',', j)) > 0) {
            OPCODES[i++] = j + 1 == l ? null : s.substring(j, l);
            j = l + 1;
        }

        s = "T_BOOLEAN,T_CHAR,T_FLOAT,T_DOUBLE,T_BYTE,T_SHORT,T_INT,T_LONG,";
        TYPES = new String[12];
        j = 0;
        i = 4;
        while ((l = s.indexOf(',', j)) > 0) {
            TYPES[i++] = s.substring(j, l);
            j = l + 1;
        }

        s = "H_GETFIELD,H_GETSTATIC,H_PUTFIELD,H_PUTSTATIC,"
                + "H_INVOKEVIRTUAL,H_INVOKESTATIC,H_INVOKESPECIAL,"
                + "H_NEWINVOKESPECIAL,H_INVOKEINTERFACE,";
        HANDLE_TAG = new String[10];
        j = 0;
        i = 1;
        while ((l = s.indexOf(',', j)) > 0) {
            HANDLE_TAG[i++] = s.substring(j, l);
            j = l + 1;
        }
    }

    /**
     * The ASM API version implemented by this clbss. The vblue of this field
     * must be one of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     */
    protected finbl int bpi;

    /**
     * A buffer thbt cbn be used to crebte strings.
     */
    protected finbl StringBuffer buf;

    /**
     * The text to be printed. Since the code of methods is not necessbrily
     * visited in sequentibl order, one method bfter the other, but cbn be
     * interlbced (some instructions from method one, then some instructions
     * from method two, then some instructions from method one bgbin...), it is
     * not possible to print the visited instructions directly to b sequentibl
     * strebm. A clbss is therefore printed in b two steps process: b string
     * tree is constructed during the visit, bnd printed to b sequentibl strebm
     * bt the end of the visit. This string tree is stored in this field, bs b
     * string list thbt cbn contbin other string lists, which cbn themselves
     * contbin other string lists, bnd so on.
     */
    public finbl List<Object> text;

    /**
     * Constructs b new {@link Printer}.
     */
    protected Printer(finbl int bpi) {
        this.bpi = bpi;
        this.buf = new StringBuffer();
        this.text = new ArrbyList<Object>();
    }

    /**
     * Clbss hebder. See {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visit}.
     */
    public bbstrbct void visit(finbl int version, finbl int bccess,
            finbl String nbme, finbl String signbture, finbl String superNbme,
            finbl String[] interfbces);

    /**
     * Clbss source. See {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitSource}.
     */
    public bbstrbct void visitSource(finbl String file, finbl String debug);

    /**
     * Clbss outer clbss. See
     * {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitOuterClbss}.
     */
    public bbstrbct void visitOuterClbss(finbl String owner, finbl String nbme,
            finbl String desc);

    /**
     * Clbss bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitAnnotbtion}.
     */
    public bbstrbct Printer visitClbssAnnotbtion(finbl String desc,
            finbl boolebn visible);

    /**
     * Clbss type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitTypeAnnotbtion}.
     */
    public Printer visitClbssTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Clbss bttribute. See
     * {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitAttribute}.
     */
    public bbstrbct void visitClbssAttribute(finbl Attribute bttr);

    /**
     * Clbss inner nbme. See
     * {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitInnerClbss}.
     */
    public bbstrbct void visitInnerClbss(finbl String nbme,
            finbl String outerNbme, finbl String innerNbme, finbl int bccess);

    /**
     * Clbss field. See {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitField}.
     */
    public bbstrbct Printer visitField(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl Object vblue);

    /**
     * Clbss method. See {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitMethod}.
     */
    public bbstrbct Printer visitMethod(finbl int bccess, finbl String nbme,
            finbl String desc, finbl String signbture, finbl String[] exceptions);

    /**
     * Clbss end. See {@link jdk.internbl.org.objectweb.bsm.ClbssVisitor#visitEnd}.
     */
    public bbstrbct void visitClbssEnd();

    // ------------------------------------------------------------------------
    // Annotbtions
    // ------------------------------------------------------------------------

    /**
     * Annotbtion vblue. See {@link jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor#visit}.
     */
    public bbstrbct void visit(finbl String nbme, finbl Object vblue);

    /**
     * Annotbtion enum vblue. See
     * {@link jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor#visitEnum}.
     */
    public bbstrbct void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue);

    /**
     * Nested bnnotbtion vblue. See
     * {@link jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor#visitAnnotbtion}.
     */
    public bbstrbct Printer visitAnnotbtion(finbl String nbme, finbl String desc);

    /**
     * Annotbtion brrby vblue. See
     * {@link jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor#visitArrby}.
     */
    public bbstrbct Printer visitArrby(finbl String nbme);

    /**
     * Annotbtion end. See {@link jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor#visitEnd}.
     */
    public bbstrbct void visitAnnotbtionEnd();

    // ------------------------------------------------------------------------
    // Fields
    // ------------------------------------------------------------------------

    /**
     * Field bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.FieldVisitor#visitAnnotbtion}.
     */
    public bbstrbct Printer visitFieldAnnotbtion(finbl String desc,
            finbl boolebn visible);

    /**
     * Field type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.FieldVisitor#visitTypeAnnotbtion}.
     */
    public Printer visitFieldTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Field bttribute. See
     * {@link jdk.internbl.org.objectweb.bsm.FieldVisitor#visitAttribute}.
     */
    public bbstrbct void visitFieldAttribute(finbl Attribute bttr);

    /**
     * Field end. See {@link jdk.internbl.org.objectweb.bsm.FieldVisitor#visitEnd}.
     */
    public bbstrbct void visitFieldEnd();

    // ------------------------------------------------------------------------
    // Methods
    // ------------------------------------------------------------------------

    /**
     * Method pbrbmeter. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitPbrbmeter(String, int)}.
     */
    public void visitPbrbmeter(String nbme, int bccess) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method defbult bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitAnnotbtionDefbult}.
     */
    public bbstrbct Printer visitAnnotbtionDefbult();

    /**
     * Method bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitAnnotbtion}.
     */
    public bbstrbct Printer visitMethodAnnotbtion(finbl String desc,
            finbl boolebn visible);

    /**
     * Method type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTypeAnnotbtion}.
     */
    public Printer visitMethodTypeAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method pbrbmeter bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitPbrbmeterAnnotbtion}.
     */
    public bbstrbct Printer visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible);

    /**
     * Method bttribute. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitAttribute}.
     */
    public bbstrbct void visitMethodAttribute(finbl Attribute bttr);

    /**
     * Method stbrt. See {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitCode}.
     */
    public bbstrbct void visitCode();

    /**
     * Method stbck frbme. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitFrbme}.
     */
    public bbstrbct void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck);

    /**
     * Method instruction. See {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitInsn}
     * .
     */
    public bbstrbct void visitInsn(finbl int opcode);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitIntInsn}.
     */
    public bbstrbct void visitIntInsn(finbl int opcode, finbl int operbnd);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitVbrInsn}.
     */
    public bbstrbct void visitVbrInsn(finbl int opcode, finbl int vbr);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTypeInsn}.
     */
    public bbstrbct void visitTypeInsn(finbl int opcode, finbl String type);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitFieldInsn}.
     */
    public bbstrbct void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitMethodInsn}.
     */
    @Deprecbted
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            boolebn itf = opcode == Opcodes.INVOKEINTERFACE;
            visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitMethodInsn}.
     */
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            if (itf != (opcode == Opcodes.INVOKEINTERFACE)) {
                throw new IllegblArgumentException(
                        "INVOKESPECIAL/STATIC on interfbces require ASM 5");
            }
            visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitInvokeDynbmicInsn}.
     */
    public bbstrbct void visitInvokeDynbmicInsn(String nbme, String desc,
            Hbndle bsm, Object... bsmArgs);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitJumpInsn}.
     */
    public bbstrbct void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel);

    /**
     * Method lbbel. See {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitLbbel}.
     */
    public bbstrbct void visitLbbel(finbl Lbbel lbbel);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitLdcInsn}.
     */
    public bbstrbct void visitLdcInsn(finbl Object cst);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitIincInsn}.
     */
    public bbstrbct void visitIincInsn(finbl int vbr, finbl int increment);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTbbleSwitchInsn}.
     */
    public bbstrbct void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitLookupSwitchInsn}.
     */
    public bbstrbct void visitLookupSwitchInsn(finbl Lbbel dflt,
            finbl int[] keys, finbl Lbbel[] lbbels);

    /**
     * Method instruction. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitMultiANewArrbyInsn}.
     */
    public bbstrbct void visitMultiANewArrbyInsn(finbl String desc,
            finbl int dims);

    /**
     * Instruction type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitInsnAnnotbtion}.
     */
    public Printer visitInsnAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method exception hbndler. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTryCbtchBlock}.
     */
    public bbstrbct void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type);

    /**
     * Try cbtch block type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTryCbtchAnnotbtion}.
     */
    public Printer visitTryCbtchAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method debug info. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitLocblVbribble}.
     */
    public bbstrbct void visitLocblVbribble(finbl String nbme,
            finbl String desc, finbl String signbture, finbl Lbbel stbrt,
            finbl Lbbel end, finbl int index);

    /**
     * Locbl vbribble type bnnotbtion. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitTryCbtchAnnotbtion}.
     */
    public Printer visitLocblVbribbleAnnotbtion(finbl int typeRef,
            finbl TypePbth typePbth, finbl Lbbel[] stbrt, finbl Lbbel[] end,
            finbl int[] index, finbl String desc, finbl boolebn visible) {
        throw new RuntimeException("Must be overriden");
    }

    /**
     * Method debug info. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitLineNumber}.
     */
    public bbstrbct void visitLineNumber(finbl int line, finbl Lbbel stbrt);

    /**
     * Method mbx stbck bnd mbx locbls. See
     * {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitMbxs}.
     */
    public bbstrbct void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls);

    /**
     * Method end. See {@link jdk.internbl.org.objectweb.bsm.MethodVisitor#visitEnd}.
     */
    public bbstrbct void visitMethodEnd();

    /**
     * Returns the text constructed by this visitor.
     *
     * @return the text constructed by this visitor.
     */
    public List<Object> getText() {
        return text;
    }

    /**
     * Prints the text constructed by this visitor.
     *
     * @pbrbm pw
     *            the print writer to be used.
     */
    public void print(finbl PrintWriter pw) {
        printList(pw, text);
    }

    /**
     * Appends b quoted string to b given buffer.
     *
     * @pbrbm buf
     *            the buffer where the string must be bdded.
     * @pbrbm s
     *            the string to be bdded.
     */
    public stbtic void bppendString(finbl StringBuffer buf, finbl String s) {
        buf.bppend('\"');
        for (int i = 0; i < s.length(); ++i) {
            chbr c = s.chbrAt(i);
            if (c == '\n') {
                buf.bppend("\\n");
            } else if (c == '\r') {
                buf.bppend("\\r");
            } else if (c == '\\') {
                buf.bppend("\\\\");
            } else if (c == '"') {
                buf.bppend("\\\"");
            } else if (c < 0x20 || c > 0x7f) {
                buf.bppend("\\u");
                if (c < 0x10) {
                    buf.bppend("000");
                } else if (c < 0x100) {
                    buf.bppend("00");
                } else if (c < 0x1000) {
                    buf.bppend('0');
                }
                buf.bppend(Integer.toString(c, 16));
            } else {
                buf.bppend(c);
            }
        }
        buf.bppend('\"');
    }

    /**
     * Prints the given string tree.
     *
     * @pbrbm pw
     *            the writer to be used to print the tree.
     * @pbrbm l
     *            b string tree, i.e., b string list thbt cbn contbin other
     *            string lists, bnd so on recursively.
     */
    stbtic void printList(finbl PrintWriter pw, finbl List<?> l) {
        for (int i = 0; i < l.size(); ++i) {
            Object o = l.get(i);
            if (o instbnceof List) {
                printList(pw, (List<?>) o);
            } else {
                pw.print(o.toString());
            }
        }
    }
}
