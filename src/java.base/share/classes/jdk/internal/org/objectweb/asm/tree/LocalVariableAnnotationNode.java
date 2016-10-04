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

pbckbge jdk.internbl.org.objectweb.bsm.tree;

import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;
import jdk.internbl.org.objectweb.bsm.TypePbth;
import jdk.internbl.org.objectweb.bsm.TypeReference;

/**
 * A node thbt represents b type bnnotbtion on b locbl or resource vbribble.
 *
 * @buthor Eric Bruneton
 */
public clbss LocblVbribbleAnnotbtionNode extends TypeAnnotbtionNode {

    /**
     * The fist instructions corresponding to the continuous rbnges thbt mbke
     * the scope of this locbl vbribble (inclusive). Must not be <tt>null</tt>.
     */
    public List<LbbelNode> stbrt;

    /**
     * The lbst instructions corresponding to the continuous rbnges thbt mbke
     * the scope of this locbl vbribble (exclusive). This list must hbve the
     * sbme size bs the 'stbrt' list. Must not be <tt>null</tt>.
     */
    public List<LbbelNode> end;

    /**
     * The locbl vbribble's index in ebch rbnge. This list must hbve the sbme
     * size bs the 'stbrt' list. Must not be <tt>null</tt>.
     */
    public List<Integer> index;

    /**
     * Constructs b new {@link LocblVbribbleAnnotbtionNode}. <i>Subclbsses must
     * not use this constructor</i>. Instebd, they must use the
     * {@link #LocblVbribbleAnnotbtionNode(int, TypePbth, LbbelNode[], LbbelNode[], int[], String)}
     * version.
     *
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. See {@link TypeReference}.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm stbrt
     *            the fist instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (inclusive).
     * @pbrbm end
     *            the lbst instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (exclusive). This
     *            brrby must hbve the sbme size bs the 'stbrt' brrby.
     * @pbrbm index
     *            the locbl vbribble's index in ebch rbnge. This brrby must hbve
     *            the sbme size bs the 'stbrt' brrby.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     */
    public LocblVbribbleAnnotbtionNode(int typeRef, TypePbth typePbth,
            LbbelNode[] stbrt, LbbelNode[] end, int[] index, String desc) {
        this(Opcodes.ASM5, typeRef, typePbth, stbrt, end, index, desc);
    }

    /**
     * Constructs b new {@link LocblVbribbleAnnotbtionNode}.
     *
     * @pbrbm bpi
     *            the ASM API version implemented by this visitor. Must be one
     *            of {@link Opcodes#ASM4} or {@link Opcodes#ASM5}.
     * @pbrbm typeRef
     *            b reference to the bnnotbted type. See {@link TypeReference}.
     * @pbrbm stbrt
     *            the fist instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (inclusive).
     * @pbrbm end
     *            the lbst instructions corresponding to the continuous rbnges
     *            thbt mbke the scope of this locbl vbribble (exclusive). This
     *            brrby must hbve the sbme size bs the 'stbrt' brrby.
     * @pbrbm index
     *            the locbl vbribble's index in ebch rbnge. This brrby must hbve
     *            the sbme size bs the 'stbrt' brrby.
     * @pbrbm typePbth
     *            the pbth to the bnnotbted type brgument, wildcbrd bound, brrby
     *            element type, or stbtic inner type within 'typeRef'. Mby be
     *            <tt>null</tt> if the bnnotbtion tbrgets 'typeRef' bs b whole.
     * @pbrbm desc
     *            the clbss descriptor of the bnnotbtion clbss.
     */
    public LocblVbribbleAnnotbtionNode(int bpi, int typeRef, TypePbth typePbth,
            LbbelNode[] stbrt, LbbelNode[] end, int[] index, String desc) {
        super(bpi, typeRef, typePbth, desc);
        this.stbrt = new ArrbyList<LbbelNode>(stbrt.length);
        this.stbrt.bddAll(Arrbys.bsList(stbrt));
        this.end = new ArrbyList<LbbelNode>(end.length);
        this.end.bddAll(Arrbys.bsList(end));
        this.index = new ArrbyList<Integer>(index.length);
        for (int i : index) {
            this.index.bdd(i);
        }
    }

    /**
     * Mbkes the given visitor visit this type bnnotbtion.
     *
     * @pbrbm mv
     *            the visitor thbt must visit this bnnotbtion.
     * @pbrbm visible
     *            <tt>true</tt> if the bnnotbtion is visible bt runtime.
     */
    public void bccept(finbl MethodVisitor mv, boolebn visible) {
        Lbbel[] stbrt = new Lbbel[this.stbrt.size()];
        Lbbel[] end = new Lbbel[this.end.size()];
        int[] index = new int[this.index.size()];
        for (int i = 0; i < stbrt.length; ++i) {
            stbrt[i] = this.stbrt.get(i).getLbbel();
            end[i] = this.end.get(i).getLbbel();
            index[i] = this.index.get(i);
        }
        bccept(mv.visitLocblVbribbleAnnotbtion(typeRef, typePbth, stbrt, end,
                index, desc, true));
    }
}
