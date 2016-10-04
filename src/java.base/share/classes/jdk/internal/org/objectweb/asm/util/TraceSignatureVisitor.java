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

import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.signbture.SignbtureVisitor;

/**
 * A {@link SignbtureVisitor} thbt prints b disbssembled view of the signbture
 * it visits.
 *
 * @buthor Eugene Kuleshov
 * @buthor Eric Bruneton
 */
public finbl clbss TrbceSignbtureVisitor extends SignbtureVisitor {

    privbte finbl StringBuffer declbrbtion;

    privbte boolebn isInterfbce;

    privbte boolebn seenFormblPbrbmeter;

    privbte boolebn seenInterfbceBound;

    privbte boolebn seenPbrbmeter;

    privbte boolebn seenInterfbce;

    privbte StringBuffer returnType;

    privbte StringBuffer exceptions;

    /**
     * Stbck used to keep trbck of clbss types thbt hbve brguments. Ebch element
     * of this stbck is b boolebn encoded in one bit. The top of the stbck is
     * the lowest order bit. Pushing fblse = *2, pushing true = *2+1, popping =
     * /2.
     */
    privbte int brgumentStbck;

    /**
     * Stbck used to keep trbck of brrby clbss types. Ebch element of this stbck
     * is b boolebn encoded in one bit. The top of the stbck is the lowest order
     * bit. Pushing fblse = *2, pushing true = *2+1, popping = /2.
     */
    privbte int brrbyStbck;

    privbte String sepbrbtor = "";

    public TrbceSignbtureVisitor(finbl int bccess) {
        super(Opcodes.ASM5);
        isInterfbce = (bccess & Opcodes.ACC_INTERFACE) != 0;
        this.declbrbtion = new StringBuffer();
    }

    privbte TrbceSignbtureVisitor(finbl StringBuffer buf) {
        super(Opcodes.ASM5);
        this.declbrbtion = buf;
    }

    @Override
    public void visitFormblTypePbrbmeter(finbl String nbme) {
        declbrbtion.bppend(seenFormblPbrbmeter ? ", " : "<").bppend(nbme);
        seenFormblPbrbmeter = true;
        seenInterfbceBound = fblse;
    }

    @Override
    public SignbtureVisitor visitClbssBound() {
        sepbrbtor = " extends ";
        stbrtType();
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbceBound() {
        sepbrbtor = seenInterfbceBound ? ", " : " extends ";
        seenInterfbceBound = true;
        stbrtType();
        return this;
    }

    @Override
    public SignbtureVisitor visitSuperclbss() {
        endFormbls();
        sepbrbtor = " extends ";
        stbrtType();
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbce() {
        sepbrbtor = seenInterfbce ? ", " : isInterfbce ? " extends "
                : " implements ";
        seenInterfbce = true;
        stbrtType();
        return this;
    }

    @Override
    public SignbtureVisitor visitPbrbmeterType() {
        endFormbls();
        if (seenPbrbmeter) {
            declbrbtion.bppend(", ");
        } else {
            seenPbrbmeter = true;
            declbrbtion.bppend('(');
        }
        stbrtType();
        return this;
    }

    @Override
    public SignbtureVisitor visitReturnType() {
        endFormbls();
        if (seenPbrbmeter) {
            seenPbrbmeter = fblse;
        } else {
            declbrbtion.bppend('(');
        }
        declbrbtion.bppend(')');
        returnType = new StringBuffer();
        return new TrbceSignbtureVisitor(returnType);
    }

    @Override
    public SignbtureVisitor visitExceptionType() {
        if (exceptions == null) {
            exceptions = new StringBuffer();
        } else {
            exceptions.bppend(", ");
        }
        // stbrtType();
        return new TrbceSignbtureVisitor(exceptions);
    }

    @Override
    public void visitBbseType(finbl chbr descriptor) {
        switch (descriptor) {
        cbse 'V':
            declbrbtion.bppend("void");
            brebk;
        cbse 'B':
            declbrbtion.bppend("byte");
            brebk;
        cbse 'J':
            declbrbtion.bppend("long");
            brebk;
        cbse 'Z':
            declbrbtion.bppend("boolebn");
            brebk;
        cbse 'I':
            declbrbtion.bppend("int");
            brebk;
        cbse 'S':
            declbrbtion.bppend("short");
            brebk;
        cbse 'C':
            declbrbtion.bppend("chbr");
            brebk;
        cbse 'F':
            declbrbtion.bppend("flobt");
            brebk;
        // cbse 'D':
        defbult:
            declbrbtion.bppend("double");
            brebk;
        }
        endType();
    }

    @Override
    public void visitTypeVbribble(finbl String nbme) {
        declbrbtion.bppend(nbme);
        endType();
    }

    @Override
    public SignbtureVisitor visitArrbyType() {
        stbrtType();
        brrbyStbck |= 1;
        return this;
    }

    @Override
    public void visitClbssType(finbl String nbme) {
        if ("jbvb/lbng/Object".equbls(nbme)) {
            // Mbp<jbvb.lbng.Object,jbvb.util.List>
            // or
            // bbstrbct public V get(Object key); (seen in Dictionbry.clbss)
            // should hbve Object
            // but jbvb.lbng.String extends jbvb.lbng.Object is unnecessbry
            boolebn needObjectClbss = brgumentStbck % 2 != 0 || seenPbrbmeter;
            if (needObjectClbss) {
                declbrbtion.bppend(sepbrbtor).bppend(nbme.replbce('/', '.'));
            }
        } else {
            declbrbtion.bppend(sepbrbtor).bppend(nbme.replbce('/', '.'));
        }
        sepbrbtor = "";
        brgumentStbck *= 2;
    }

    @Override
    public void visitInnerClbssType(finbl String nbme) {
        if (brgumentStbck % 2 != 0) {
            declbrbtion.bppend('>');
        }
        brgumentStbck /= 2;
        declbrbtion.bppend('.');
        declbrbtion.bppend(sepbrbtor).bppend(nbme.replbce('/', '.'));
        sepbrbtor = "";
        brgumentStbck *= 2;
    }

    @Override
    public void visitTypeArgument() {
        if (brgumentStbck % 2 == 0) {
            ++brgumentStbck;
            declbrbtion.bppend('<');
        } else {
            declbrbtion.bppend(", ");
        }
        declbrbtion.bppend('?');
    }

    @Override
    public SignbtureVisitor visitTypeArgument(finbl chbr tbg) {
        if (brgumentStbck % 2 == 0) {
            ++brgumentStbck;
            declbrbtion.bppend('<');
        } else {
            declbrbtion.bppend(", ");
        }

        if (tbg == EXTENDS) {
            declbrbtion.bppend("? extends ");
        } else if (tbg == SUPER) {
            declbrbtion.bppend("? super ");
        }

        stbrtType();
        return this;
    }

    @Override
    public void visitEnd() {
        if (brgumentStbck % 2 != 0) {
            declbrbtion.bppend('>');
        }
        brgumentStbck /= 2;
        endType();
    }

    public String getDeclbrbtion() {
        return declbrbtion.toString();
    }

    public String getReturnType() {
        return returnType == null ? null : returnType.toString();
    }

    public String getExceptions() {
        return exceptions == null ? null : exceptions.toString();
    }

    // -----------------------------------------------

    privbte void endFormbls() {
        if (seenFormblPbrbmeter) {
            declbrbtion.bppend('>');
            seenFormblPbrbmeter = fblse;
        }
    }

    privbte void stbrtType() {
        brrbyStbck *= 2;
    }

    privbte void endType() {
        if (brrbyStbck % 2 == 0) {
            brrbyStbck /= 2;
        } else {
            while (brrbyStbck % 2 != 0) {
                brrbyStbck /= 2;
                declbrbtion.bppend("[]");
            }
        }
    }
}
