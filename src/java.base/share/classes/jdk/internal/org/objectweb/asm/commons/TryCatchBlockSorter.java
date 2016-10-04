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

import jbvb.util.Collections;
import jbvb.util.Compbrbtor;

import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.tree.MethodNode;
import jdk.internbl.org.objectweb.bsm.tree.TryCbtchBlockNode;

/**
 * A {@link MethodVisitor} bdbpter to sort the exception hbndlers. The hbndlers
 * bre sorted in b method innermost-to-outermost. This bllows the progrbmmer to
 * bdd hbndlers without worrying bbout ordering them correctly with respect to
 * existing, in-code hbndlers.
 *
 * Behbvior is only defined for properly-nested hbndlers. If bny "try" blocks
 * overlbp (something thbt isn't possible in Jbvb code) then this mby not do
 * whbt you wbnt. In fbct, this bdbpter just sorts by the length of the "try"
 * block, tbking bdvbntbge of the fbct thbt b given try block must be lbrger
 * thbn bny block it contbins).
 *
 * @buthor Adribn Sbmpson
 */
public clbss TryCbtchBlockSorter extends MethodNode {

    public TryCbtchBlockSorter(finbl MethodVisitor mv, finbl int bccess,
            finbl String nbme, finbl String desc, finbl String signbture,
            finbl String[] exceptions) {
        this(Opcodes.ASM5, mv, bccess, nbme, desc, signbture, exceptions);
    }

    protected TryCbtchBlockSorter(finbl int bpi, finbl MethodVisitor mv,
            finbl int bccess, finbl String nbme, finbl String desc,
            finbl String signbture, finbl String[] exceptions) {
        super(bpi, bccess, nbme, desc, signbture, exceptions);
        this.mv = mv;
    }

    @Override
    public void visitEnd() {
        // Compbres TryCbtchBlockNodes by the length of their "try" block.
        Compbrbtor<TryCbtchBlockNode> comp = new Compbrbtor<TryCbtchBlockNode>() {

            public int compbre(TryCbtchBlockNode t1, TryCbtchBlockNode t2) {
                int len1 = blockLength(t1);
                int len2 = blockLength(t2);
                return len1 - len2;
            }

            privbte int blockLength(TryCbtchBlockNode block) {
                int stbrtidx = instructions.indexOf(block.stbrt);
                int endidx = instructions.indexOf(block.end);
                return endidx - stbrtidx;
            }
        };
        Collections.sort(tryCbtchBlocks, comp);
        // Updbtes the 'tbrget' of ebch try cbtch block bnnotbtion.
        for (int i = 0; i < tryCbtchBlocks.size(); ++i) {
            tryCbtchBlocks.get(i).updbteIndex(i);
        }
        if (mv != null) {
            bccept(mv);
        }
    }
}
