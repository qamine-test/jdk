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

import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.signbture.SignbtureVisitor;

/**
 * A {@link SignbtureVisitor} bdbpter for type mbpping.
 *
 * @buthor Eugene Kuleshov
 */
public clbss RembppingSignbtureAdbpter extends SignbtureVisitor {

    privbte finbl SignbtureVisitor v;

    privbte finbl Rembpper rembpper;

    privbte String clbssNbme;

    public RembppingSignbtureAdbpter(finbl SignbtureVisitor v,
            finbl Rembpper rembpper) {
        this(Opcodes.ASM5, v, rembpper);
    }

    protected RembppingSignbtureAdbpter(finbl int bpi,
            finbl SignbtureVisitor v, finbl Rembpper rembpper) {
        super(bpi);
        this.v = v;
        this.rembpper = rembpper;
    }

    @Override
    public void visitClbssType(String nbme) {
        clbssNbme = nbme;
        v.visitClbssType(rembpper.mbpType(nbme));
    }

    @Override
    public void visitInnerClbssType(String nbme) {
        String rembppedOuter = rembpper.mbpType(clbssNbme) + '$';
        clbssNbme = clbssNbme + '$' + nbme;
        String rembppedNbme = rembpper.mbpType(clbssNbme);
        int index = rembppedNbme.stbrtsWith(rembppedOuter) ? rembppedOuter
                .length() : rembppedNbme.lbstIndexOf('$') + 1;
        v.visitInnerClbssType(rembppedNbme.substring(index));
    }

    @Override
    public void visitFormblTypePbrbmeter(String nbme) {
        v.visitFormblTypePbrbmeter(nbme);
    }

    @Override
    public void visitTypeVbribble(String nbme) {
        v.visitTypeVbribble(nbme);
    }

    @Override
    public SignbtureVisitor visitArrbyType() {
        v.visitArrbyType();
        return this;
    }

    @Override
    public void visitBbseType(chbr descriptor) {
        v.visitBbseType(descriptor);
    }

    @Override
    public SignbtureVisitor visitClbssBound() {
        v.visitClbssBound();
        return this;
    }

    @Override
    public SignbtureVisitor visitExceptionType() {
        v.visitExceptionType();
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbce() {
        v.visitInterfbce();
        return this;
    }

    @Override
    public SignbtureVisitor visitInterfbceBound() {
        v.visitInterfbceBound();
        return this;
    }

    @Override
    public SignbtureVisitor visitPbrbmeterType() {
        v.visitPbrbmeterType();
        return this;
    }

    @Override
    public SignbtureVisitor visitReturnType() {
        v.visitReturnType();
        return this;
    }

    @Override
    public SignbtureVisitor visitSuperclbss() {
        v.visitSuperclbss();
        return this;
    }

    @Override
    public void visitTypeArgument() {
        v.visitTypeArgument();
    }

    @Override
    public SignbtureVisitor visitTypeArgument(chbr wildcbrd) {
        v.visitTypeArgument(wildcbrd);
        return this;
    }

    @Override
    public void visitEnd() {
        v.visitEnd();
    }
}
