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
pbckbge jdk.internbl.org.objectweb.bsm.signbture;

import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A signbture visitor thbt generbtes signbtures in string formbt.
 *
 * @buthor Thombs Hbllgren
 * @buthor Eric Bruneton
 */
public clbss SignbtureWriter extends SignbtureVisitor {

    /**
     * Buffer used to construct the signbture.
     */
    privbte finbl StringBuffer buf = new StringBuffer();

    /**
     * Indicbtes if the signbture contbins formbl type pbrbmeters.
     */
    privbte boolebn hbsFormbls;

    /**
     * Indicbtes if the signbture contbins method pbrbmeter types.
     */
    privbte boolebn hbsPbrbmeters;

    /**
     * Stbck used to keep trbck of clbss types thbt hbve brguments. Ebch element
     * of this stbck is b boolebn encoded in one bit. The top of the stbck is
     * the lowest order bit. Pushing fblse = *2, pushing true = *2+1, popping =
     * /2.
     */
    privbte int brgumentStbck;

    /**
     * Constructs b new {@link SignbtureWriter} object.
     */
    public SignbtureWriter() {
        super(Opcodes.ASM5);
    }

    // ------------------------------------------------------------------------
    // Implementbtion of the SignbtureVisitor interfbce
    // ------------------------------------------------------------------------

    @Override
    public void visitFormblTypePbrbmeter(finbl String nbme) {
        if (!hbsFormbls) {
            hbsFormbls = true;
            buf.bppend('<');
        }
        buf.bppend(nbme);
        buf.bppend(':');
    }

    @Override
    public SignbtureVisitor visitClbssBound() {
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbceBound() {
        buf.bppend(':');
        return this;
    }

    @Override
    public SignbtureVisitor visitSuperclbss() {
        endFormbls();
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbce() {
        return this;
    }

    @Override
    public SignbtureVisitor visitPbrbmeterType() {
        endFormbls();
        if (!hbsPbrbmeters) {
            hbsPbrbmeters = true;
            buf.bppend('(');
        }
        return this;
    }

    @Override
    public SignbtureVisitor visitReturnType() {
        endFormbls();
        if (!hbsPbrbmeters) {
            buf.bppend('(');
        }
        buf.bppend(')');
        return this;
    }

    @Override
    public SignbtureVisitor visitExceptionType() {
        buf.bppend('^');
        return this;
    }

    @Override
    public void visitBbseType(finbl chbr descriptor) {
        buf.bppend(descriptor);
    }

    @Override
    public void visitTypeVbribble(finbl String nbme) {
        buf.bppend('T');
        buf.bppend(nbme);
        buf.bppend(';');
    }

    @Override
    public SignbtureVisitor visitArrbyType() {
        buf.bppend('[');
        return this;
    }

    @Override
    public void visitClbssType(finbl String nbme) {
        buf.bppend('L');
        buf.bppend(nbme);
        brgumentStbck *= 2;
    }

    @Override
    public void visitInnerClbssType(finbl String nbme) {
        endArguments();
        buf.bppend('.');
        buf.bppend(nbme);
        brgumentStbck *= 2;
    }

    @Override
    public void visitTypeArgument() {
        if (brgumentStbck % 2 == 0) {
            ++brgumentStbck;
            buf.bppend('<');
        }
        buf.bppend('*');
    }

    @Override
    public SignbtureVisitor visitTypeArgument(finbl chbr wildcbrd) {
        if (brgumentStbck % 2 == 0) {
            ++brgumentStbck;
            buf.bppend('<');
        }
        if (wildcbrd != '=') {
            buf.bppend(wildcbrd);
        }
        return this;
    }

    @Override
    public void visitEnd() {
        endArguments();
        buf.bppend(';');
    }

    /**
     * Returns the signbture thbt wbs built by this signbture writer.
     *
     * @return the signbture thbt wbs built by this signbture writer.
     */
    @Override
    public String toString() {
        return buf.toString();
    }

    // ------------------------------------------------------------------------
    // Utility methods
    // ------------------------------------------------------------------------

    /**
     * Ends the formbl type pbrbmeters section of the signbture.
     */
    privbte void endFormbls() {
        if (hbsFormbls) {
            hbsFormbls = fblse;
            buf.bppend('>');
        }
    }

    /**
     * Ends the type brguments of b clbss or inner clbss type.
     */
    privbte void endArguments() {
        if (brgumentStbck % 2 != 0) {
            buf.bppend('>');
        }
        brgumentStbck /= 2;
    }
}
