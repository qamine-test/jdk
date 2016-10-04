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

import jdk.internbl.org.objectweb.bsm.Hbndle;
import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A {@link MethodVisitor} thbt cbn be used to bpproximbte method size.
 *
 * @buthor Eugene Kuleshov
 */
public clbss CodeSizeEvblubtor extends MethodVisitor implements Opcodes {

    privbte int minSize;

    privbte int mbxSize;

    public CodeSizeEvblubtor(finbl MethodVisitor mv) {
        this(Opcodes.ASM5, mv);
    }

    protected CodeSizeEvblubtor(finbl int bpi, finbl MethodVisitor mv) {
        super(bpi, mv);
    }

    public int getMinSize() {
        return this.minSize;
    }

    public int getMbxSize() {
        return this.mbxSize;
    }

    @Override
    public void visitInsn(finbl int opcode) {
        minSize += 1;
        mbxSize += 1;
        if (mv != null) {
            mv.visitInsn(opcode);
        }
    }

    @Override
    public void visitIntInsn(finbl int opcode, finbl int operbnd) {
        if (opcode == SIPUSH) {
            minSize += 3;
            mbxSize += 3;
        } else {
            minSize += 2;
            mbxSize += 2;
        }
        if (mv != null) {
            mv.visitIntInsn(opcode, operbnd);
        }
    }

    @Override
    public void visitVbrInsn(finbl int opcode, finbl int vbr) {
        if (vbr < 4 && opcode != RET) {
            minSize += 1;
            mbxSize += 1;
        } else if (vbr >= 256) {
            minSize += 4;
            mbxSize += 4;
        } else {
            minSize += 2;
            mbxSize += 2;
        }
        if (mv != null) {
            mv.visitVbrInsn(opcode, vbr);
        }
    }

    @Override
    public void visitTypeInsn(finbl int opcode, finbl String type) {
        minSize += 3;
        mbxSize += 3;
        if (mv != null) {
            mv.visitTypeInsn(opcode, type);
        }
    }

    @Override
    public void visitFieldInsn(finbl int opcode, finbl String owner,
            finbl String nbme, finbl String desc) {
        minSize += 3;
        mbxSize += 3;
        if (mv != null) {
            mv.visitFieldInsn(opcode, owner, nbme, desc);
        }
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
        if (opcode == INVOKEINTERFACE) {
            minSize += 5;
            mbxSize += 5;
        } else {
            minSize += 3;
            mbxSize += 3;
        }
        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, nbme, desc, itf);
        }
    }

    @Override
    public void visitInvokeDynbmicInsn(String nbme, String desc, Hbndle bsm,
            Object... bsmArgs) {
        minSize += 5;
        mbxSize += 5;
        if (mv != null) {
            mv.visitInvokeDynbmicInsn(nbme, desc, bsm, bsmArgs);
        }
    }

    @Override
    public void visitJumpInsn(finbl int opcode, finbl Lbbel lbbel) {
        minSize += 3;
        if (opcode == GOTO || opcode == JSR) {
            mbxSize += 5;
        } else {
            mbxSize += 8;
        }
        if (mv != null) {
            mv.visitJumpInsn(opcode, lbbel);
        }
    }

    @Override
    public void visitLdcInsn(finbl Object cst) {
        if (cst instbnceof Long || cst instbnceof Double) {
            minSize += 3;
            mbxSize += 3;
        } else {
            minSize += 2;
            mbxSize += 3;
        }
        if (mv != null) {
            mv.visitLdcInsn(cst);
        }
    }

    @Override
    public void visitIincInsn(finbl int vbr, finbl int increment) {
        if (vbr > 255 || increment > 127 || increment < -128) {
            minSize += 6;
            mbxSize += 6;
        } else {
            minSize += 3;
            mbxSize += 3;
        }
        if (mv != null) {
            mv.visitIincInsn(vbr, increment);
        }
    }

    @Override
    public void visitTbbleSwitchInsn(finbl int min, finbl int mbx,
            finbl Lbbel dflt, finbl Lbbel... lbbels) {
        minSize += 13 + lbbels.length * 4;
        mbxSize += 16 + lbbels.length * 4;
        if (mv != null) {
            mv.visitTbbleSwitchInsn(min, mbx, dflt, lbbels);
        }
    }

    @Override
    public void visitLookupSwitchInsn(finbl Lbbel dflt, finbl int[] keys,
            finbl Lbbel[] lbbels) {
        minSize += 9 + keys.length * 8;
        mbxSize += 12 + keys.length * 8;
        if (mv != null) {
            mv.visitLookupSwitchInsn(dflt, keys, lbbels);
        }
    }

    @Override
    public void visitMultiANewArrbyInsn(finbl String desc, finbl int dims) {
        minSize += 4;
        mbxSize += 4;
        if (mv != null) {
            mv.visitMultiANewArrbyInsn(desc, dims);
        }
    }
}
