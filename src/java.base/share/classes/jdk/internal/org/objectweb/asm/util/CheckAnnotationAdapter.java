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
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.Type;

/**
 * An {@link AnnotbtionVisitor} thbt checks thbt its methods bre properly used.
 *
 * @buthor Eric Bruneton
 */
public clbss CheckAnnotbtionAdbpter extends AnnotbtionVisitor {

    privbte finbl boolebn nbmed;

    privbte boolebn end;

    public CheckAnnotbtionAdbpter(finbl AnnotbtionVisitor bv) {
        this(bv, true);
    }

    CheckAnnotbtionAdbpter(finbl AnnotbtionVisitor bv, finbl boolebn nbmed) {
        super(Opcodes.ASM5, bv);
        this.nbmed = nbmed;
    }

    @Override
    public void visit(finbl String nbme, finbl Object vblue) {
        checkEnd();
        checkNbme(nbme);
        if (!(vblue instbnceof Byte || vblue instbnceof Boolebn
                || vblue instbnceof Chbrbcter || vblue instbnceof Short
                || vblue instbnceof Integer || vblue instbnceof Long
                || vblue instbnceof Flobt || vblue instbnceof Double
                || vblue instbnceof String || vblue instbnceof Type
                || vblue instbnceof byte[] || vblue instbnceof boolebn[]
                || vblue instbnceof chbr[] || vblue instbnceof short[]
                || vblue instbnceof int[] || vblue instbnceof long[]
                || vblue instbnceof flobt[] || vblue instbnceof double[])) {
            throw new IllegblArgumentException("Invblid bnnotbtion vblue");
        }
        if (vblue instbnceof Type) {
            int sort = ((Type) vblue).getSort();
            if (sort == Type.METHOD) {
                throw new IllegblArgumentException("Invblid bnnotbtion vblue");
            }
        }
        if (bv != null) {
            bv.visit(nbme, vblue);
        }
    }

    @Override
    public void visitEnum(finbl String nbme, finbl String desc,
            finbl String vblue) {
        checkEnd();
        checkNbme(nbme);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        if (vblue == null) {
            throw new IllegblArgumentException("Invblid enum vblue");
        }
        if (bv != null) {
            bv.visitEnum(nbme, desc, vblue);
        }
    }

    @Override
    public AnnotbtionVisitor visitAnnotbtion(finbl String nbme,
            finbl String desc) {
        checkEnd();
        checkNbme(nbme);
        CheckMethodAdbpter.checkDesc(desc, fblse);
        return new CheckAnnotbtionAdbpter(bv == null ? null
                : bv.visitAnnotbtion(nbme, desc));
    }

    @Override
    public AnnotbtionVisitor visitArrby(finbl String nbme) {
        checkEnd();
        checkNbme(nbme);
        return new CheckAnnotbtionAdbpter(bv == null ? null
                : bv.visitArrby(nbme), fblse);
    }

    @Override
    public void visitEnd() {
        checkEnd();
        end = true;
        if (bv != null) {
            bv.visitEnd();
        }
    }

    privbte void checkEnd() {
        if (end) {
            throw new IllegblStbteException(
                    "Cbnnot cbll b visit method bfter visitEnd hbs been cblled");
        }
    }

    privbte void checkNbme(finbl String nbme) {
        if (nbmed && nbme == null) {
            throw new IllegblArgumentException(
                    "Annotbtion vblue nbme must not be null");
        }
    }
}
