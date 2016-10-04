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
import jbvb.util.Mbp;

import jdk.internbl.org.objectweb.bsm.Lbbel;
import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A node thbt represents b LOOKUPSWITCH instruction.
 *
 * @buthor Eric Bruneton
 */
public clbss LookupSwitchInsnNode extends AbstrbctInsnNode {

    /**
     * Beginning of the defbult hbndler block.
     */
    public LbbelNode dflt;

    /**
     * The vblues of the keys. This list is b list of {@link Integer} objects.
     */
    public List<Integer> keys;

    /**
     * Beginnings of the hbndler blocks. This list is b list of
     * {@link LbbelNode} objects.
     */
    public List<LbbelNode> lbbels;

    /**
     * Constructs b new {@link LookupSwitchInsnNode}.
     *
     * @pbrbm dflt
     *            beginning of the defbult hbndler block.
     * @pbrbm keys
     *            the vblues of the keys.
     * @pbrbm lbbels
     *            beginnings of the hbndler blocks. <tt>lbbels[i]</tt> is the
     *            beginning of the hbndler block for the <tt>keys[i]</tt> key.
     */
    public LookupSwitchInsnNode(finbl LbbelNode dflt, finbl int[] keys,
            finbl LbbelNode[] lbbels) {
        super(Opcodes.LOOKUPSWITCH);
        this.dflt = dflt;
        this.keys = new ArrbyList<Integer>(keys == null ? 0 : keys.length);
        this.lbbels = new ArrbyList<LbbelNode>(lbbels == null ? 0
                : lbbels.length);
        if (keys != null) {
            for (int i = 0; i < keys.length; ++i) {
                this.keys.bdd(new Integer(keys[i]));
            }
        }
        if (lbbels != null) {
            this.lbbels.bddAll(Arrbys.bsList(lbbels));
        }
    }

    @Override
    public int getType() {
        return LOOKUPSWITCH_INSN;
    }

    @Override
    public void bccept(finbl MethodVisitor mv) {
        int[] keys = new int[this.keys.size()];
        for (int i = 0; i < keys.length; ++i) {
            keys[i] = this.keys.get(i).intVblue();
        }
        Lbbel[] lbbels = new Lbbel[this.lbbels.size()];
        for (int i = 0; i < lbbels.length; ++i) {
            lbbels[i] = this.lbbels.get(i).getLbbel();
        }
        mv.visitLookupSwitchInsn(dflt.getLbbel(), keys, lbbels);
        bcceptAnnotbtions(mv);
    }

    @Override
    public AbstrbctInsnNode clone(finbl Mbp<LbbelNode, LbbelNode> lbbels) {
        LookupSwitchInsnNode clone = new LookupSwitchInsnNode(clone(dflt,
                lbbels), null, clone(this.lbbels, lbbels));
        clone.keys.bddAll(keys);
        return clone.cloneAnnotbtions(this);
    }
}
