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

import jdk.internbl.org.objectweb.bsm.AnnotbtionVisitor;
import jdk.internbl.org.objectweb.bsm.Attribute;
import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A {@link MethodVisitor} thbt prints the methods it visits with b
 * {@link Printer}.
 *
 * @buthor Eric Bruneton
 */
public finbl clbss TrbceMethodVisitor extends MethodVisitor {

    public finbl Printer p;

    public TrbceMethodVisitor(finbl Printer p) {
        this(null, p);
    }

    public TrbceMethodVisitor(finbl MethodVisitor mv, finbl Printer p) {
        super(Opcodes.ASM5, mv);
        this.p = p;
    }

    @Override
    public void visitPbrbmeter(String nbme, int bccess) {
        p.visitPbrbmeter(nbme, bccess);
        super.visitPbrbmeter(nbme, bccess);
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String desc,
            finbl boolebn visible) {
        Printer p = this.p.visitMethodAnnotbtion(desc, visible);
        AnnotbtionVisitor bv = mv == null ? null : mv.visitAnnotbtion(desc,
                visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        Printer p = this.p.visitMethodTypeAnnotbtion(typeRef, typePbth, desc,
                visible);
        AnnotbtionVisitor bv = mv == null ? null : mv.visitTypeAnnotbtion(
                typeRef, typePbth, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitAttribute(finbl Attribute bttr) {
        p.visitMethodAttribute(bttr);
        super.visitAttribute(bttr);
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtionDefbult() {
        Printer p = this.p.visitAnnotbtionDefbult();
        AnnotbtionVisitor bv = mv == null ? null : mv.visitAnnotbtionDefbult();
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public AnnotbtionVisitor visitPbrbmeterAnnotbtion(finbl int pbrbmeter,
            finbl String desc, finbl boolebn visible) {
        Printer p = this.p.visitPbrbmeterAnnotbtion(pbrbmeter, desc, visible);
        AnnotbtionVisitor bv = mv == null ? null : mv.visitPbrbmeterAnnotbtion(
                pbrbmeter, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitCode() {
        p.visitCode();
        super.visitCode();
    }

    @Override
    public void visitFrbme(finbl int type, finbl int nLocbl,
            finbl Object[] locbl, finbl int nStbck, finbl Object[] stbck) {
        p.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
        super.visitFrbme(type, nLocbl, locbl, nStbck, stbck);
    }

    @Override
    public void visitInsn(finbl int opcode) {
        p.visitInsn(opcode);
        super.visitInsn(opcode);
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        p.visitIntInsn(opcode, operbnd);
        super.visitIntInsn(opcode, operbnd);
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        p.visitVbrInsn(opcode, vbr);
        super.visitVbrInsn(opcode, vbr);
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        p.visitTypeInsn(opcode, type);
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        p.visitFieldInsn(opcode, owner, nbme, desc);
        super.visitFieldInsn(opcode, owner, nbme, desc);
    }

    @Deprecbted
    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc) {
        if (bpi >= Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc);
            return;
        }
        p.visitMethodInsn(opcode, owner, nbme, desc);
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String nbme,
            String desc, boolebn itf) {
        if (bpi < Opcodes.ASM5) {
            super.visitMethodInsn(opcode, owner, nbme, desc, itf);
            return;
        }
        p.visitMethodInsn(opcode, owner, nbme, desc, itf);
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        }
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        p.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        super.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        p.visitJumpInsn(opcode, lbbel);
        super.visitJumpInsn(opcode, lbbel);
    }

    @Override
    public void visitLbbel(finbl Lbbel lbbel) {
        p.visitLbbel(lbbel);
        super.visitLbbel(lbbel);
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        p.visitLdcInsn(cst);
        super.visitLdcInsn(cst);
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        p.visitIincInsn(vbr, increment);
        super.visitIincInsn(vbr, increment);
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        p.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        super.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        p.visitLookupSwitchInsn(dflt, keys, lbbels);
        super.visitLookupSwitchInsn(dflt, keys, lbbels);
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        p.visitMultiANewArrbyInsn(desc, dims);
        super.visitMultiANewArrbyInsn(desc, dims);
    }

    @Override
    public AnnotbtionVisitor visitInsnAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        Printer p = this.p
                .visitInsnAnnotbtion(typeRef, typePbth, desc, visible);
        AnnotbtionVisitor bv = mv == null ? null : mv.visitInsnAnnotbtion(
                typeRef, typePbth, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitTryCbtchBlock(finbl Lbbel stbrt, finbl Lbbel end,
            finbl Lbbel hbndler, finbl String type) {
        p.visitTryCbtchBlock(stbrt, end, hbndler, type);
        super.visitTryCbtchBlock(stbrt, end, hbndler, type);
    }

    @Override
    public AnnotbtionVisitor visitTryCbtchAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        Printer p = this.p.visitTryCbtchAnnotbtion(typeRef, typePbth, desc,
                visible);
        AnnotbtionVisitor bv = mv == null ? null : mv.visitTryCbtchAnnotbtion(
                typeRef, typePbth, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitLocblVbribble(finbl String nbme, finbl String desc,
            finbl String signbture, finbl Lbbel stbrt, finbl Lbbel end,
            finbl int index) {
        p.visitLocblVbribble(nbme, desc, signbture, stbrt, end, index);
        super.visitLocblVbribble(nbme, desc, signbture, stbrt, end, index);
    }

    @Override
    public AnnotbtionVisitor visitLocblVbribbleAnnotbtion(int typeRef,
            TypePbth typePbth, Lbbel[] stbrt, Lbbel[] end, int[] index,
            String desc, boolebn visible) {
        Printer p = this.p.visitLocblVbribbleAnnotbtion(typeRef, typePbth,
                stbrt, end, index, desc, visible);
        AnnotbtionVisitor bv = mv == null ? null : mv
                .visitLocblVbribbleAnnotbtion(typeRef, typePbth, stbrt, end,
                        index, desc, visible);
        return new TrbceAnnotbtionVisitor(bv, p);
    }

    @Override
    public void visitLineNumber(finbl int line, finbl Lbbel stbrt) {
        p.visitLineNumber(line, stbrt);
        super.visitLineNumber(line, stbrt);
    }

    @Override
    public void visitMbxs(finbl int mbxStbck, finbl int mbxLocbls) {
        p.visitMbxs(mbxStbck, mbxLocbls);
        super.visitMbxs(mbxStbck, mbxLocbls);
    }

    @Override
    public void visitEnd() {
        p.visitMethodEnd();
        super.visitEnd();
    }
}
