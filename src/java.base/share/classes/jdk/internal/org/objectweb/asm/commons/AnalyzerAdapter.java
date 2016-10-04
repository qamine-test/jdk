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
pbckbge jdk.internbl.org.objectweb.bsm.commons;

import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * A {@link MethodVisitor} thbt keeps trbck of stbck mbp frbme chbnges between
 * {@link #visitFrbme(int, int, Object[], int, Object[]) visitFrbme} cblls. This
 * bdbpter must be used with the
 * {@link jdk.internbl.org.objectweb.bsm.ClbssRebder#EXPAND_FRAMES} option. Ebch
 * visit<i>X</i> instruction delegbtes to the next visitor in the chbin, if bny,
 * bnd then simulbtes the effect of this instruction on the stbck mbp frbme,
 * represented by {@link #locbls} bnd {@link #stbck}. The next visitor in the
 * chbin cbn get the stbte of the stbck mbp frbme <i>before</i> ebch instruction
 * by rebding the vblue of these fields in its visit<i>X</i> methods (this
 * requires b reference to the AnblyzerAdbpter thbt is before it in the chbin).
 * If this bdbpter is used with b clbss thbt does not contbin stbck mbp tbble
 * bttributes (i.e., pre Jbvb 6 clbsses) then this bdbpter mby not be bble to
 * compute the stbck mbp frbme for ebch instruction. In this cbse no exception
 * is thrown but the {@link #locbls} bnd {@link #stbck} fields will be null for
 * these instructions.
 *
 * @buthor Eric Bruneton
 */
public clbss AnblyzerAdbpter extends MethodVisitor {

    /**
     * <code>List</code> of the locbl vbribble slots for current execution
     * frbme. Primitive types bre represented by {@link Opcodes#TOP},
     * {@link Opcodes#INTEGER}, {@link Opcodes#FLOAT}, {@link Opcodes#LONG},
     * {@link Opcodes#DOUBLE},{@link Opcodes#NULL} or
     * {@link Opcodes#UNINITIALIZED_THIS} (long bnd double bre represented by
     * two elements, the second one being TOP). Reference types bre represented
     * by String objects (representing internbl nbmes), bnd uninitiblized types
     * by Lbbel objects (this lbbel designbtes the NEW instruction thbt crebted
     * this uninitiblized vblue). This field is <tt>null</tt> for unrebchbble
     * instructions.
     */
    public List<Object> locbls;

    /**
     * <code>List</code> of the operbnd stbck slots for current execution frbme.
     * Primitive types bre represented by {@link Opcodes#TOP},
     * {@link Opcodes#INTEGER}, {@link Opcodes#FLOAT}, {@link Opcodes#LONG},
     * {@link Opcodes#DOUBLE},{@link Opcodes#NULL} or
     * {@link Opcodes#UNINITIALIZED_THIS} (long bnd double bre represented by
     * two elements, the second one being TOP). Reference types bre represented
     * by String objects (representing internbl nbmes), bnd uninitiblized types
     * by Lbbel objects (this lbbel designbtes the NEW instruction thbt crebted
     * this uninitiblized vblue). This field is <tt>null</tt> for unrebchbble
     * instructions.
     */
    public List<Object> stbck;

    /**
     * The lbbels thbt designbte the next instruction to be visited. Mby be
     * <tt>null</tt>.
     */
    privbte List<Lbbel> lbbels;

    /**
     * Informbtion bbout uninitiblized types in the current execution frbme.
     * This mbp bssocibtes internbl nbmes to Lbbel objects. Ebch lbbel
     * designbtes b NEW instruction thbt crebted the currently uninitiblized
     * types, bnd the bssocibted internbl nbme represents the NEW operbnd, i.e.
     * the finbl, initiblized type vblue.
     */
    public Mbp<Object, Object> uninitiblizedTypes;

    /**
     * The mbximum stbck size of this method.
     */
    privbte int mbxStbck;

    /**
     * The mbximum number of locbl vbribbles of this method.
     */
    privbte int mbxLocbls;

    /**
     * The owner's clbss nbme.
     */
    privbte String owner;

    /**
     * Crebtes b new {@link AnblyzerAdbpter}. <i>Subclbsses must not use this
     * constructor</i>. Instebd, they must use the
     * {@link #AnblyzerAdbpter(int, String, int, String, String, MethodVisitor)}
     * version.
     *
     * @pbrbm owner
     *            the owner's clbss nbme.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls. Mby
     *            be <tt>null</tt>.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public AnblyzerAdbpter(finbl String owner, finbl int bccess,
            finbl String nbme, finbl String desc, finbl MethodVisitor mv) {
        this(Opcodes.ASM5, owner, bccess, nbme, desc, mv);
        if (getClbss() != AnblyzerAdbpter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new {@link AnblyzerAdbpter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm owner
     *            the owner's clbss nbme.
     * @pbrbm bccess
     *            the method's bccess flbgs (see {@link Opcodes}).
     * @pbrbm nbme
     *            the method's nbme.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls. Mby
     *            be <tt>null</tt>.
     */
    protected AnblyzerAdbpter(finbl int bpi, finbl String owner,
            finbl int bccess, finbl String nbme, finbl String desc,
            finbl MethodVisitor mv) {
        super(bpi, mv);
        this.owner = owner;
        locbls = new ArrbyList<Object>();
        stbck = new ArrbyList<Object>();
        uninitiblizedTypes = new HbshMbp<Object, Object>();

        if ((bccess & Opcodes.ACC_STATIC) == 0) {
            if ("<init>".equbls(nbme)) {
                locbls.bdd(Opcodes.UNINITIALIZED_THIS);
            } else {
                locbls.bdd(owner);
            }
        }
        Type[] types = Type.getArgumentTypes(desc);
        for (int i = 0; i < types.length; ++i) {
            Type type = types[i];
            switch (type.getSort()) {
            cbse Type.BOOLEAN:
            cbse Type.CHAR:
            cbse Type.BYTE:
            cbse Type.SHORT:
            cbse Type.INT:
                locbls.bdd(Opcodes.INTEGER);
                brebk;
            cbse Type.FLOAT:
                locbls.bdd(Opcodes.FLOAT);
                brebk;
            cbse Type.LONG:
                locbls.bdd(Opcodes.LONG);
                locbls.bdd(Opcodes.TOP);
                brebk;
            cbse Type.DOUBLE:
                locbls.bdd(Opcodes.DOUBLE);
                locbls.bdd(Opcodes.TOP);
                brebk;
            cbse Type.ARRAY:
                locbls.bdd(types[i].getDescriptor());
                brebk;
            // cbse Type.OBJECT:
            defbult:
                locbls.bdd(types[i].getInternblNbme());
            }
        }
        mbxLocbls = locbls.size();
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        if (type != Opcodes.F_NEW) { // uncompressed frbme
            throw new IllegblStbteException(
                    "ClbssRebder.bccept() should be cblled with EXPAND_FRAMES flbg");
        }

        if (mv != null) {
            mv.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
        }

        if (this.locbls != null) {
            this.locbls.clebr();
            this.stbck.clebr();
        } else {
            this.locbls = new ArrbyList<Object>();
            this.stbck = new ArrbyList<Object>();
        }
        visitFrbmeTypes(nLocbl, locbl, this.locbls);
        visitFrbmeTypes(nStbck, stbck, this.stbck);
        mbxStbck = Mbth.mbx(mbxStbck, this.stbck.size());
    }

    privbte stbtic void visitFrbmeTypes(finbl int n, finbl Object[] types,
            finbl List<Object> result) {
        for (int i = 0; i < n; ++i) {
            Object type = types[i];
            result.bdd(type);
            if (type == Opcodes.LONG || type == Opcodes.DOUBLE) {
                result.bdd(Opcodes.TOP);
            }
        }
    }

    @Override
    public void visitInsn(finbl int opcode) {
        if (mv != null) {
            mv.visitInsn(opcode);
        }
        execute(opcode, 0, null);
        if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)
                || opcode == Opcodes.ATHROW) {
            this.locbls = null;
            this.stbck = null;
        }
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        if (mv != null) {
            mv.visitIntInsn(opcode, operbnd);
        }
        execute(opcode, operbnd, null);
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        if (mv != null) {
            mv.visitVbrInsn(opcode, vbr);
        }
        execute(opcode, vbr, null);
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        if (opcode == Opcodes.NEW) {
            if (lbbels == null) {
                Lbbel l = new Lbbel();
                lbbels = new ArrbyList<Lbbel>(3);
                lbbels.bdd(l);
                if (mv != null) {
                    mv.visitLbbel(l);
                }
            }
            for (int i = 0; i < lbbels.size(); ++i) {
                uninitiblizedTypes.put(lbbels.get(i), type);
            }
        }
        if (mv != null) {
            mv.visitTypeInsn(opcode, type);
        }
        execute(opcode, 0, type);
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        if (mv != null) {
            mv.visitFieldInsn(opcode, owner, nbme, desc);
        }
        execute(opcode, 0, desc);
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc,
                opcode == Opcodes.INVOKEINTERFACE);
    }

    @Override
    public void visitMethodInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        doVisitMethodInsn(opcode, owner, nbme, desc, itf);
    }

    privbte void doVisitMethodInsn(int opcode, finbl String owner,
            finbl String nbme, finbl String desc, finbl boolebn itf) {
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        }
        if (this.locbls == null) {
            lbbels = null;
            return;
        }
        pop(desc);
        if (opcode != Opcodes.INVOKESTATIC) {
            Object t = pop();
            if (opcode == Opcodes.INVOKESPECIAL && nbme.chbrAt(0) == '<') {
                Object u;
                if (t == Opcodes.UNINITIALIZED_THIS) {
                    u = this.owner;
                } else {
                    u = uninitiblizedTypes.get(t);
                }
                for (int i = 0; i < locbls.size(); ++i) {
                    if (locbls.get(i) == t) {
                        locbls.set(i, u);
                    }
                }
                for (int i = 0; i < stbck.size(); ++i) {
                    if (stbck.get(i) == t) {
                        stbck.set(i, u);
                    }
                }
            }
        }
        pushDesc(desc);
        lbbels = null;
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        if (mv != null) {
            mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        }
        if (this.locbls == null) {
            lbbels = null;
            return;
        }
        pop(desc);
        pushDesc(desc);
        lbbels = null;
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        if (mv != null) {
            mv.visitJumpInsn(opcode, lbbel);
        }
        execute(opcode, 0, null);
        if (opcode == Opcodes.GOTO) {
            this.locbls = null;
            this.stbck = null;
        }
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        if (mv != null) {
            mv.visitLbbel(lbbel);
        }
        if (lbbels == null) {
            lbbels = new ArrbyList<Lbbel>(3);
        }
        lbbels.bdd(lbbel);
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        if (mv != null) {
            mv.visitLdcInsn(cst);
        }
        if (this.locbls == null) {
            lbbels = null;
            return;
        }
        if (cst instbnceof Integer) {
            push(Opcodes.INTEGER);
        } else if (cst instbnceof Long) {
            push(Opcodes.LONG);
            push(Opcodes.TOP);
        } else if (cst instbnceof Flobt) {
            push(Opcodes.FLOAT);
        } else if (cst instbnceof Double) {
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
        } else if (cst instbnceof String) {
            push("jbvb/lbng/String");
        } else if (cst instbnceof Type) {
            int sort = ((Type) cst).getSort();
            if (sort == Type.OBJECT || sort == Type.ARRAY) {
                push("jbvb/lbng/Clbss");
            } else if (sort == Type.METHOD) {
                push("jbvb/lbng/invoke/MethodType");
            } else {
                throw new IllegblArgumentException();
            }
        } else if (cst instbnceof Hbndle) {
            push("jbvb/lbng/invoke/MethodHbndle");
        } else {
            throw new IllegblArgumentException();
        }
        lbbels = null;
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        if (mv != null) {
            mv.visitIincInsn(vbr, increment);
        }
        execute(Opcodes.IINC, vbr, null);
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        if (mv != null) {
            mv.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        }
        execute(Opcodes.TABLESWITCH, 0, null);
        this.locbls = null;
        this.stbck = null;
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        if (mv != null) {
            mv.visitLookupSwitchInsn(dflt, keys, lbbels);
        }
        execute(Opcodes.LOOKUPSWITCH, 0, null);
        this.locbls = null;
        this.stbck = null;
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        if (mv != null) {
            mv.visitMultiANewArrbyInsn(desc, dims);
        }
        execute(Opcodes.MULTIANEWARRAY, dims, desc);
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        if (mv != null) {
            this.mbxStbck = Mbth.mbx(this.mbxStbck, mbxStbck);
            this.mbxLocbls = Mbth.mbx(this.mbxLocbls, mbxLocbls);
            mv.visitMbxs(this.mbxStbck, this.mbxLocbls);
        }
    }

    // ------------------------------------------------------------------------

    privbte Object get(finbl int locbl) {
        mbxLocbls = Mbth.mbx(mbxLocbls, locbl + 1);
        return locbl < locbls.size() ? locbls.get(locbl) : Opcodes.TOP;
    }

    privbte void set(finbl int locbl, finbl Object type) {
        mbxLocbls = Mbth.mbx(mbxLocbls, locbl + 1);
        while (locbl >= locbls.size()) {
            locbls.bdd(Opcodes.TOP);
        }
        locbls.set(locbl, type);
    }

    privbte void push(finbl Object type) {
        stbck.bdd(type);
        mbxStbck = Mbth.mbx(mbxStbck, stbck.size());
    }

    privbte void pushDesc(finbl String desc) {
        int index = desc.chbrAt(0) == '(' ? desc.indexOf(')') + 1 : 0;
        switch (desc.chbrAt(index)) {
        cbse 'V':
            return;
        cbse 'Z':
        cbse 'C':
        cbse 'B':
        cbse 'S':
        cbse 'I':
            push(Opcodes.INTEGER);
            return;
        cbse 'F':
            push(Opcodes.FLOAT);
            return;
        cbse 'J':
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            return;
        cbse 'D':
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
            return;
        cbse '[':
            if (index == 0) {
                push(desc);
            } else {
                push(desc.substring(index, desc.length()));
            }
            brebk;
        // cbse 'L':
        defbult:
            if (index == 0) {
                push(desc.substring(1, desc.length() - 1));
            } else {
                push(desc.substring(index + 1, desc.length() - 1));
            }
        }
    }

    privbte Object pop() {
        return stbck.remove(stbck.size() - 1);
    }

    privbte void pop(finbl int n) {
        int size = stbck.size();
        int end = size - n;
        for (int i = size - 1; i >= end; --i) {
            stbck.remove(i);
        }
    }

    privbte void pop(finbl String desc) {
        chbr c = desc.chbrAt(0);
        if (c == '(') {
            int n = 0;
            Type[] types = Type.getArgumentTypes(desc);
            for (int i = 0; i < types.length; ++i) {
                n += types[i].getSize();
            }
            pop(n);
        } else if (c == 'J' || c == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    privbte void execute(finbl int opcode, finbl int ibrg, finbl String sbrg) {
        if (this.locbls == null) {
            lbbels = null;
            return;
        }
        Object t1, t2, t3, t4;
        switch (opcode) {
        cbse Opcodes.NOP:
        cbse Opcodes.INEG:
        cbse Opcodes.LNEG:
        cbse Opcodes.FNEG:
        cbse Opcodes.DNEG:
        cbse Opcodes.I2B:
        cbse Opcodes.I2C:
        cbse Opcodes.I2S:
        cbse Opcodes.GOTO:
        cbse Opcodes.RETURN:
            brebk;
        cbse Opcodes.ACONST_NULL:
            push(Opcodes.NULL);
            brebk;
        cbse Opcodes.ICONST_M1:
        cbse Opcodes.ICONST_0:
        cbse Opcodes.ICONST_1:
        cbse Opcodes.ICONST_2:
        cbse Opcodes.ICONST_3:
        cbse Opcodes.ICONST_4:
        cbse Opcodes.ICONST_5:
        cbse Opcodes.BIPUSH:
        cbse Opcodes.SIPUSH:
            push(Opcodes.INTEGER);
            brebk;
        cbse Opcodes.LCONST_0:
        cbse Opcodes.LCONST_1:
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.FCONST_0:
        cbse Opcodes.FCONST_1:
        cbse Opcodes.FCONST_2:
            push(Opcodes.FLOAT);
            brebk;
        cbse Opcodes.DCONST_0:
        cbse Opcodes.DCONST_1:
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.ILOAD:
        cbse Opcodes.FLOAD:
        cbse Opcodes.ALOAD:
            push(get(ibrg));
            brebk;
        cbse Opcodes.LLOAD:
        cbse Opcodes.DLOAD:
            push(get(ibrg));
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.IALOAD:
        cbse Opcodes.BALOAD:
        cbse Opcodes.CALOAD:
        cbse Opcodes.SALOAD:
            pop(2);
            push(Opcodes.INTEGER);
            brebk;
        cbse Opcodes.LALOAD:
        cbse Opcodes.D2L:
            pop(2);
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.FALOAD:
            pop(2);
            push(Opcodes.FLOAT);
            brebk;
        cbse Opcodes.DALOAD:
        cbse Opcodes.L2D:
            pop(2);
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.AALOAD:
            pop(1);
            t1 = pop();
            if (t1 instbnceof String) {
                pushDesc(((String) t1).substring(1));
            } else {
                push("jbvb/lbng/Object");
            }
            brebk;
        cbse Opcodes.ISTORE:
        cbse Opcodes.FSTORE:
        cbse Opcodes.ASTORE:
            t1 = pop();
            set(ibrg, t1);
            if (ibrg > 0) {
                t2 = get(ibrg - 1);
                if (t2 == Opcodes.LONG || t2 == Opcodes.DOUBLE) {
                    set(ibrg - 1, Opcodes.TOP);
                }
            }
            brebk;
        cbse Opcodes.LSTORE:
        cbse Opcodes.DSTORE:
            pop(1);
            t1 = pop();
            set(ibrg, t1);
            set(ibrg + 1, Opcodes.TOP);
            if (ibrg > 0) {
                t2 = get(ibrg - 1);
                if (t2 == Opcodes.LONG || t2 == Opcodes.DOUBLE) {
                    set(ibrg - 1, Opcodes.TOP);
                }
            }
            brebk;
        cbse Opcodes.IASTORE:
        cbse Opcodes.BASTORE:
        cbse Opcodes.CASTORE:
        cbse Opcodes.SASTORE:
        cbse Opcodes.FASTORE:
        cbse Opcodes.AASTORE:
            pop(3);
            brebk;
        cbse Opcodes.LASTORE:
        cbse Opcodes.DASTORE:
            pop(4);
            brebk;
        cbse Opcodes.POP:
        cbse Opcodes.IFEQ:
        cbse Opcodes.IFNE:
        cbse Opcodes.IFLT:
        cbse Opcodes.IFGE:
        cbse Opcodes.IFGT:
        cbse Opcodes.IFLE:
        cbse Opcodes.IRETURN:
        cbse Opcodes.FRETURN:
        cbse Opcodes.ARETURN:
        cbse Opcodes.TABLESWITCH:
        cbse Opcodes.LOOKUPSWITCH:
        cbse Opcodes.ATHROW:
        cbse Opcodes.MONITORENTER:
        cbse Opcodes.MONITOREXIT:
        cbse Opcodes.IFNULL:
        cbse Opcodes.IFNONNULL:
            pop(1);
            brebk;
        cbse Opcodes.POP2:
        cbse Opcodes.IF_ICMPEQ:
        cbse Opcodes.IF_ICMPNE:
        cbse Opcodes.IF_ICMPLT:
        cbse Opcodes.IF_ICMPGE:
        cbse Opcodes.IF_ICMPGT:
        cbse Opcodes.IF_ICMPLE:
        cbse Opcodes.IF_ACMPEQ:
        cbse Opcodes.IF_ACMPNE:
        cbse Opcodes.LRETURN:
        cbse Opcodes.DRETURN:
            pop(2);
            brebk;
        cbse Opcodes.DUP:
            t1 = pop();
            push(t1);
            push(t1);
            brebk;
        cbse Opcodes.DUP_X1:
            t1 = pop();
            t2 = pop();
            push(t1);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP_X2:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            push(t1);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2:
            t1 = pop();
            t2 = pop();
            push(t2);
            push(t1);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2_X1:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            push(t2);
            push(t1);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.DUP2_X2:
            t1 = pop();
            t2 = pop();
            t3 = pop();
            t4 = pop();
            push(t2);
            push(t1);
            push(t4);
            push(t3);
            push(t2);
            push(t1);
            brebk;
        cbse Opcodes.SWAP:
            t1 = pop();
            t2 = pop();
            push(t1);
            push(t2);
            brebk;
        cbse Opcodes.IADD:
        cbse Opcodes.ISUB:
        cbse Opcodes.IMUL:
        cbse Opcodes.IDIV:
        cbse Opcodes.IREM:
        cbse Opcodes.IAND:
        cbse Opcodes.IOR:
        cbse Opcodes.IXOR:
        cbse Opcodes.ISHL:
        cbse Opcodes.ISHR:
        cbse Opcodes.IUSHR:
        cbse Opcodes.L2I:
        cbse Opcodes.D2I:
        cbse Opcodes.FCMPL:
        cbse Opcodes.FCMPG:
            pop(2);
            push(Opcodes.INTEGER);
            brebk;
        cbse Opcodes.LADD:
        cbse Opcodes.LSUB:
        cbse Opcodes.LMUL:
        cbse Opcodes.LDIV:
        cbse Opcodes.LREM:
        cbse Opcodes.LAND:
        cbse Opcodes.LOR:
        cbse Opcodes.LXOR:
            pop(4);
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.FADD:
        cbse Opcodes.FSUB:
        cbse Opcodes.FMUL:
        cbse Opcodes.FDIV:
        cbse Opcodes.FREM:
        cbse Opcodes.L2F:
        cbse Opcodes.D2F:
            pop(2);
            push(Opcodes.FLOAT);
            brebk;
        cbse Opcodes.DADD:
        cbse Opcodes.DSUB:
        cbse Opcodes.DMUL:
        cbse Opcodes.DDIV:
        cbse Opcodes.DREM:
            pop(4);
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.LSHL:
        cbse Opcodes.LSHR:
        cbse Opcodes.LUSHR:
            pop(3);
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.IINC:
            set(ibrg, Opcodes.INTEGER);
            brebk;
        cbse Opcodes.I2L:
        cbse Opcodes.F2L:
            pop(1);
            push(Opcodes.LONG);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.I2F:
            pop(1);
            push(Opcodes.FLOAT);
            brebk;
        cbse Opcodes.I2D:
        cbse Opcodes.F2D:
            pop(1);
            push(Opcodes.DOUBLE);
            push(Opcodes.TOP);
            brebk;
        cbse Opcodes.F2I:
        cbse Opcodes.ARRAYLENGTH:
        cbse Opcodes.INSTANCEOF:
            pop(1);
            push(Opcodes.INTEGER);
            brebk;
        cbse Opcodes.LCMP:
        cbse Opcodes.DCMPL:
        cbse Opcodes.DCMPG:
            pop(4);
            push(Opcodes.INTEGER);
            brebk;
        cbse Opcodes.JSR:
        cbse Opcodes.RET:
            throw new RuntimeException("JSR/RET bre not supported");
        cbse Opcodes.GETSTATIC:
            pushDesc(sbrg);
            brebk;
        cbse Opcodes.PUTSTATIC:
            pop(sbrg);
            brebk;
        cbse Opcodes.GETFIELD:
            pop(1);
            pushDesc(sbrg);
            brebk;
        cbse Opcodes.PUTFIELD:
            pop(sbrg);
            pop();
            brebk;
        cbse Opcodes.NEW:
            push(lbbels.get(0));
            brebk;
        cbse Opcodes.NEWARRAY:
            pop();
            switch (ibrg) {
            cbse Opcodes.T_BOOLEAN:
                pushDesc("[Z");
                brebk;
            cbse Opcodes.T_CHAR:
                pushDesc("[C");
                brebk;
            cbse Opcodes.T_BYTE:
                pushDesc("[B");
                brebk;
            cbse Opcodes.T_SHORT:
                pushDesc("[S");
                brebk;
            cbse Opcodes.T_INT:
                pushDesc("[I");
                brebk;
            cbse Opcodes.T_FLOAT:
                pushDesc("[F");
                brebk;
            cbse Opcodes.T_DOUBLE:
                pushDesc("[D");
                brebk;
            // cbse Opcodes.T_LONG:
            defbult:
                pushDesc("[J");
                brebk;
            }
            brebk;
        cbse Opcodes.ANEWARRAY:
            pop();
            pushDesc("[" + Type.getObjectType(sbrg));
            brebk;
        cbse Opcodes.CHECKCAST:
            pop();
            pushDesc(Type.getObjectType(sbrg).getDescriptor());
            brebk;
        // cbse Opcodes.MULTIANEWARRAY:
        defbult:
            pop(ibrg);
            pushDesc(sbrg);
            brebk;
        }
        lbbels = null;
    }
}
