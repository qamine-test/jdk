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

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A {@link MethodVisitor} thbt renumbers locbl vbribbles in their order of
 * bppebrbnce. This bdbpter bllows one to ebsily bdd new locbl vbribbles to b
 * method. It mby be used by inheriting from this clbss, but the preferred wby
 * of using it is vib delegbtion: the next visitor in the chbin cbn indeed bdd
 * new locbls when needed by cblling {@link #newLocbl} on this bdbpter (this
 * requires b reference bbck to this {@link LocblVbribblesSorter}).
 *
 * @buthor Chris Nokleberg
 * @buthor Eugene Kuleshov
 * @buthor Eric Bruneton
 */
public clbss LocblVbribblesSorter extends MethodVisitor {

    privbte stbtic finbl Type OBJECT_TYPE = Type
            .getObjectType("jbvb/lbng/Object");

    /**
     * Mbpping from old to new locbl vbribble indexes. A locbl vbribble bt index
     * i of size 1 is rembpped to 'mbpping[2*i]', while b locbl vbribble bt
     * index i of size 2 is rembpped to 'mbpping[2*i+1]'.
     */
    privbte int[] mbpping = new int[40];

    /**
     * Arrby used to store stbck mbp locbl vbribble types bfter rembpping.
     */
    privbte Object[] newLocbls = new Object[20];

    /**
     * Index of the first locbl vbribble, bfter formbl pbrbmeters.
     */
    protected finbl int firstLocbl;

    /**
     * Index of the next locbl vbribble to be crebted by {@link #newLocbl}.
     */
    protected int nextLocbl;

    /**
     * Indicbtes if bt lebst one locbl vbribble hbs moved due to rembpping.
     */
    privbte boolebn chbnged;

    /**
     * Crebtes b new {@link LocblVbribblesSorter}. <i>Subclbsses must not use
     * this constructor</i>. Instebd, they must use the
     * {@link #LocblVbribblesSorter(int, int, String, MethodVisitor)} version.
     *
     * @pbrbm bccess
     *            bccess flbgs of the bdbpted method.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     * @throws IllegblStbteException
     *             If b subclbss cblls this constructor.
     */
    public LocblVbribblesSorter(finbl int bccess, finbl String desc,
            finbl MethodVisitor mv) {
        this(Opcodes.ASM5, bccess, desc, mv);
        if (getClbss() != LocblVbribblesSorter.clbss) {
            throw new IllegblStbteException();
        }
    }

    /**
     * Crebtes b new {@link LocblVbribblesSorter}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm bccess
     *            bccess flbgs of the bdbpted method.
     * @pbrbm desc
     *            the method's descriptor (see {@link Type Type}).
     * @pbrbm mv
     *            the method visitor to which this bdbpter delegbtes cblls.
     */
    protected LocblVbribblesSorter(finbl int bpi, finbl int bccess,
            finbl String desc, finbl MethodVisitor mv) {
        super(bpi, mv);
        Type[] brgs = Type.getArgumentTypes(desc);
        nextLocbl = (Opcodes.ACC_STATIC & bccess) == 0 ? 1 : 0;
        for (int i = 0; i < brgs.length; i++) {
            nextLocbl += brgs[i].getSize();
        }
        firstLocbl = nextLocbl;
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        Type type;
        switch (opcode) {
        cbse Opcodes.LLOAD:
        cbse Opcodes.LSTORE:
            type = Type.LONG_TYPE;
            brebk;

        cbse Opcodes.DLOAD:
        cbse Opcodes.DSTORE:
            type = Type.DOUBLE_TYPE;
            brebk;

        cbse Opcodes.FLOAD:
        cbse Opcodes.FSTORE:
            type = Type.FLOAT_TYPE;
            brebk;

        cbse Opcodes.ILOAD:
        cbse Opcodes.ISTORE:
            type = Type.INT_TYPE;
            brebk;

        defbult:
            // cbse Opcodes.ALOAD:
            // cbse Opcodes.ASTORE:
            // cbse RET:
            type = OBJECT_TYPE;
            brebk;
        }
        mv.visitVbrInsn(opcode, rembp(vbr, type));
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        mv.visitIincInsn(rembp(vbr, Type.INT_TYPE), increment);
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        mv.visitMbxs(mbxStbck, nextLocbl);
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        int newIndex = rembp(index, Type.getType(desc));
        mv.visitLocblVbribble(nbme, desc, signbture, stbrt, end, newIndex);
    }

    @Override
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        Type t = Type.getType(desc);
        int[] newIndex = new int[index.length];
        for (int i = 0; i < newIndex.length; ++i) {
            newIndex[i] = rembp(index[i], t);
        }
        return mv.visitLocblVbribbleAnnotbtion(typeRef, typePbth, stbrt, end,
                newIndex, desc, visible);
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        if (type != Opcodes.F_NEW) { // uncompressed frbme
            throw new IllegblStbteException(
                    "ClbssRebder.bccept() should be cblled with EXPAND_FRAMES flbg");
        }

        if (!chbnged) { // optimizbtion for the cbse where mbpping = identity
            mv.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
            return;
        }

        // crebtes b copy of newLocbls
        Object[] oldLocbls = new Object[newLocbls.length];
        System.brrbycopy(newLocbls, 0, oldLocbls, 0, oldLocbls.length);

        updbteNewLocbls(newLocbls);

        // copies types from 'locbl' to 'newLocbls'
        // 'newLocbls' blrebdy contbins the vbribbles bdded with 'newLocbl'

        int index = 0; // old locbl vbribble index
        int number = 0; // old locbl vbribble number
        for (; number < nLocbl; ++number) {
            Object t = locbl[number];
            int size = t == Opcodes.LONG || t == Opcodes.DOUBLE ? 2 : 1;
            if (t != Opcodes.TOP) {
                Type typ = OBJECT_TYPE;
                if (t == Opcodes.INTEGER) {
                    typ = Type.INT_TYPE;
                } else if (t == Opcodes.FLOAT) {
                    typ = Type.FLOAT_TYPE;
                } else if (t == Opcodes.LONG) {
                    typ = Type.LONG_TYPE;
                } else if (t == Opcodes.DOUBLE) {
                    typ = Type.DOUBLE_TYPE;
                } else if (t instbnceof String) {
                    typ = Type.getObjectType((String) t);
                }
                setFrbmeLocbl(rembp(index, typ), t);
            }
            index += size;
        }

        // removes TOP bfter long bnd double types bs well bs trbiling TOPs

        index = 0;
        number = 0;
        for (int i = 0; index < newLocbls.length; ++i) {
            Object t = newLocbls[index++];
            if (t != null && t != Opcodes.TOP) {
                newLocbls[i] = t;
                number = i + 1;
                if (t == Opcodes.LONG || t == Opcodes.DOUBLE) {
                    index += 1;
                }
            } else {
                newLocbls[i] = Opcodes.TOP;
            }
        }

        // visits rembpped frbme
        mv.visitFrbme(type, number, newLocbls, nStbck, stbck);

        // restores originbl vblue of 'newLocbls'
        newLocbls = oldLocbls;
    }

    // -------------

    /**
     * Crebtes b new locbl vbribble of the given type.
     *
     * @pbrbm type
     *            the type of the locbl vbribble to be crebted.
     * @return the identifier of the newly crebted locbl vbribble.
     */
    public int newLocbl(finbl Type type) {
        Object t;
        switch (type.getSort()) {
        cbse Type.BOOLEAN:
        cbse Type.CHAR:
        cbse Type.BYTE:
        cbse Type.SHORT:
        cbse Type.INT:
            t = Opcodes.INTEGER;
            brebk;
        cbse Type.FLOAT:
            t = Opcodes.FLOAT;
            brebk;
        cbse Type.LONG:
            t = Opcodes.LONG;
            brebk;
        cbse Type.DOUBLE:
            t = Opcodes.DOUBLE;
            brebk;
        cbse Type.ARRAY:
            t = type.getDescriptor();
            brebk;
        // cbse Type.OBJECT:
        defbult:
            t = type.getInternblNbme();
            brebk;
        }
        int locbl = newLocblMbpping(type);
        setLocblType(locbl, type);
        setFrbmeLocbl(locbl, t);
        chbnged = true;
        return locbl;
    }

    /**
     * Notifies subclbsses thbt b new stbck mbp frbme is being visited. The
     * brrby brgument contbins the stbck mbp frbme types corresponding to the
     * locbl vbribbles bdded with {@link #newLocbl}. This method cbn updbte
     * these types in plbce for the stbck mbp frbme being visited. The defbult
     * implementbtion of this method does nothing, i.e. b locbl vbribble bdded
     * with {@link #newLocbl} will hbve the sbme type in bll stbck mbp frbmes.
     * But this behbvior is not blwbys the desired one, for instbnce if b locbl
     * vbribble is bdded in the middle of b try/cbtch block: the frbme for the
     * exception hbndler should hbve b TOP type for this new locbl.
     *
     * @pbrbm newLocbls
     *            the stbck mbp frbme types corresponding to the locbl vbribbles
     *            bdded with {@link #newLocbl} (bnd null for the others). The
     *            formbt of this brrby is the sbme bs in
     *            {@link MethodVisitor#visitFrbme}, except thbt long bnd double
     *            types use two slots. The types for the current stbck mbp frbme
     *            must be updbted in plbce in this brrby.
     */
    protected void updbteNewLocbls(Object[] newLocbls) {
    }

    /**
     * Notifies subclbsses thbt b locbl vbribble hbs been bdded or rembpped. The
     * defbult implementbtion of this method does nothing.
     *
     * @pbrbm locbl
     *            b locbl vbribble identifier, bs returned by {@link #newLocbl
     *            newLocbl()}.
     * @pbrbm type
     *            the type of the vblue being stored in the locbl vbribble.
     */
    protected void setLocblType(finbl int locbl, finbl Type type) {
    }

    privbte void setFrbmeLocbl(finbl int locbl, finbl Object type) {
        int l = newLocbls.length;
        if (locbl >= l) {
            Object[] b = new Object[Mbth.mbx(2 * l, locbl + 1)];
            System.brrbycopy(newLocbls, 0, b, 0, l);
            newLocbls = b;
        }
        newLocbls[locbl] = type;
    }

    privbte int rembp(finbl int vbr, finbl Type type) {
        if (vbr + type.getSize() <= firstLocbl) {
            return vbr;
        }
        int key = 2 * vbr + type.getSize() - 1;
        int size = mbpping.length;
        if (key >= size) {
            int[] newMbpping = new int[Mbth.mbx(2 * size, key + 1)];
            System.brrbycopy(mbpping, 0, newMbpping, 0, size);
            mbpping = newMbpping;
        }
        int vblue = mbpping[key];
        if (vblue == 0) {
            vblue = newLocblMbpping(type);
            setLocblType(vblue, type);
            mbpping[key] = vblue + 1;
        } else {
            vblue--;
        }
        if (vblue != vbr) {
            chbnged = true;
        }
        return vblue;
    }

    protected int newLocblMbpping(finbl Type type) {
        int locbl = nextLocbl;
        nextLocbl += type.getSize();
        return locbl;
    }
}
