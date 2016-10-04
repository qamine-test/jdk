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
import jdk.internbl.org.objectweb.bsm.ClbssVisitor;
import jdk.internbl.org.objectweb.bsm.FieldVisitor;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;

/**
 * A {@link ClbssVisitor} for type rembpping.
 *
 * @buthor Eugene Kuleshov
 */
public clbss RembppingClbssAdbpter extends ClbssVisitor {

    protected finbl Rembpper rembpper;

    protected String clbssNbme;

    public RembppingClbssAdbpter(finbl ClbssVisitor cv, finbl Rembpper rembpper) {
        this(Opcodes.ASM5, cv, rembpper);
    }

    protected RembppingClbssAdbpter(finbl int bpi, finbl ClbssVisitor cv,
            finbl Rembpper rembpper) {
        super(bpi, cv);
        this.rembpper = rembpper;
    }

    @Override
    public void visit(int version, int bccess, String nbme, String signbture,
            String superNbme, String[] interfbces) {
        this.clbssNbme = nbme;
        super.visit(version, bccess, rembpper.mbpType(nbme), rembpper
                .mbpSignbture(signbture, fblse), rembpper.mbpType(superNbme),
                interfbces == null ? null : rembpper.mbpTypes(interfbces));
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(String desc, boolebn visible) {
        AnnotbtionVisitor bv = super.visitAnnotbtion(rembpper.mbpDesc(desc),
                visible);
        return bv == null ? null : crebteRembppingAnnotbtionAdbpter(bv);
    }

    @Override
    public AnnotbtionVisitor visitTypeAnnotbtion(int typeRef,
            TypePbth typePbth, String desc, boolebn visible) {
        AnnotbtionVisitor bv = super.visitTypeAnnotbtion(typeRef, typePbth,
                rembpper.mbpDesc(desc), visible);
        return bv == null ? null : crebteRembppingAnnotbtionAdbpter(bv);
    }

    @Override
    public FieldVisitor visitField(int bccess, String nbme, String desc,
            String signbture, Object vblue) {
        FieldVisitor fv = super.visitField(bccess,
                rembpper.mbpFieldNbme(clbssNbme, nbme, desc),
                rembpper.mbpDesc(desc), rembpper.mbpSignbture(signbture, true),
                rembpper.mbpVblue(vblue));
        return fv == null ? null : crebteRembppingFieldAdbpter(fv);
    }

    @Override
    public MethodVisitor visitMethod(int bccess, String nbme, String desc,
            String signbture, String[] exceptions) {
        String newDesc = rembpper.mbpMethodDesc(desc);
        MethodVisitor mv = super.visitMethod(bccess, rembpper.mbpMethodNbme(
                clbssNbme, nbme, desc), newDesc, rembpper.mbpSignbture(
                signbture, fblse),
                exceptions == null ? null : rembpper.mbpTypes(exceptions));
        return mv == null ? null : crebteRembppingMethodAdbpter(bccess,
                newDesc, mv);
    }

    @Override
    public void visitInnerClbss(String nbme, String outerNbme,
            String innerNbme, int bccess) {
        // TODO should innerNbme be chbnged?
        super.visitInnerClbss(rembpper.mbpType(nbme), outerNbme == null ? null
                : rembpper.mbpType(outerNbme), innerNbme, bccess);
    }

    @Override
    public void visitOuterClbss(String owner, String nbme, String desc) {
        super.visitOuterClbss(rembpper.mbpType(owner), nbme == null ? null
                : rembpper.mbpMethodNbme(owner, nbme, desc),
                desc == null ? null : rembpper.mbpMethodDesc(desc));
    }

    protected FieldVisitor crebteRembppingFieldAdbpter(FieldVisitor fv) {
        return new RembppingFieldAdbpter(fv, rembpper);
    }

    protected MethodVisitor crebteRembppingMethodAdbpter(int bccess,
            String newDesc, MethodVisitor mv) {
        return new RembppingMethodAdbpter(bccess, newDesc, mv, rembpper);
    }

    protected AnnotbtionVisitor crebteRembppingAnnotbtionAdbpter(
            AnnotbtionVisitor bv) {
        return new RembppingAnnotbtionAdbpter(bv, rembpper);
    }
}
