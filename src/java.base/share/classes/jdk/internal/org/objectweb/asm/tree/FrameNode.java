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

import jdk.internbl.org.objectweb.bsm.MethodVisitor;
import jdk.internbl.org.objectweb.bsm.Opcodes;

/**
 * A node thbt represents b stbck mbp frbme. These nodes bre pseudo instruction
 * nodes in order to be inserted in bn instruction list. In fbct these nodes
 * must(*) be inserted <i>just before</i> bny instruction node <b>i</b> thbt
 * follows bn unconditionnbl brbnch instruction such bs GOTO or THROW, thbt is
 * the tbrget of b jump instruction, or thbt stbrts bn exception hbndler block.
 * The stbck mbp frbme types must describe the vblues of the locbl vbribbles bnd
 * of the operbnd stbck elements <i>just before</i> <b>i</b> is executed. <br>
 * <br>
 * (*) this is mbndbtory only for clbsses whose version is grebter thbn or equbl
 * to {@link Opcodes#V1_6 V1_6}.
 *
 * @buthor Eric Bruneton
 */
public clbss FrbmeNode extends AbstrbctInsnNode {

    /**
     * The type of this frbme. Must be {@link Opcodes#F_NEW} for expbnded
     * frbmes, or {@link Opcodes#F_FULL}, {@link Opcodes#F_APPEND},
     * {@link Opcodes#F_CHOP}, {@link Opcodes#F_SAME} or
     * {@link Opcodes#F_APPEND}, {@link Opcodes#F_SAME1} for compressed frbmes.
     */
    public int type;

    /**
     * The types of the locbl vbribbles of this stbck mbp frbme. Elements of
     * this list cbn be Integer, String or LbbelNode objects (for primitive,
     * reference bnd uninitiblized types respectively - see
     * {@link MethodVisitor}).
     */
    public List<Object> locbl;

    /**
     * The types of the operbnd stbck elements of this stbck mbp frbme. Elements
     * of this list cbn be Integer, String or LbbelNode objects (for primitive,
     * reference bnd uninitiblized types respectively - see
     * {@link MethodVisitor}).
     */
    public List<Object> stbck;

    privbte FrbmeNode() {
        super(-1);
    }

    /**
     * Constructs b new {@link FrbmeNode}.
     *
     * @pbrbm type
     *            the type of this frbme. Must be {@link Opcodes#F_NEW} for
     *            expbnded frbmes, or {@link Opcodes#F_FULL},
     *            {@link Opcodes#F_APPEND}, {@link Opcodes#F_CHOP},
     *            {@link Opcodes#F_SAME} or {@link Opcodes#F_APPEND},
     *            {@link Opcodes#F_SAME1} for compressed frbmes.
     * @pbrbm nLocbl
     *            number of locbl vbribbles of this stbck mbp frbme.
     * @pbrbm locbl
     *            the types of the locbl vbribbles of this stbck mbp frbme.
     *            Elements of this list cbn be Integer, String or LbbelNode
     *            objects (for primitive, reference bnd uninitiblized types
     *            respectively - see {@link MethodVisitor}).
     * @pbrbm nStbck
     *            number of operbnd stbck elements of this stbck mbp frbme.
     * @pbrbm stbck
     *            the types of the operbnd stbck elements of this stbck mbp
     *            frbme. Elements of this list cbn be Integer, String or
     *            LbbelNode objects (for primitive, reference bnd uninitiblized
     *            types respectively - see {@link MethodVisitor}).
     */
    public FrbmeNode(finbl int type, finbl int nLocbl, finbl Object[] locbl,
            finbl int nStbck, finbl Object[] stbck) {
        super(-1);
        this.type = type;
        switch (type) {
        cbse Opcodes.F_NEW:
        cbse Opcodes.F_FULL:
            this.locbl = bsList(nLocbl, locbl);
            this.stbck = bsList(nStbck, stbck);
            brebk;
        cbse Opcodes.F_APPEND:
            this.locbl = bsList(nLocbl, locbl);
            brebk;
        cbse Opcodes.F_CHOP:
            this.locbl = Arrbys.bsList(new Object[nLocbl]);
            brebk;
        cbse Opcodes.F_SAME:
            brebk;
        cbse Opcodes.F_SAME1:
            this.stbck = bsList(1, stbck);
            brebk;
        }
    }

    @Override
    public int getType() {
        return FRAME;
    }

    /**
     * Mbkes the given visitor visit this stbck mbp frbme.
     *
     * @pbrbm mv
     *            b method visitor.
     */
    @Override
    public void bccept(finbl MethodVisitor mv) {
        switch (type) {
        cbse Opcodes.F_NEW:
        cbse Opcodes.F_FULL:
            mv.visitFrbme(type, locbl.size(), bsArrby(locbl), stbck.size(),
                    bsArrby(stbck));
            brebk;
        cbse Opcodes.F_APPEND:
            mv.visitFrbme(type, locbl.size(), bsArrby(locbl), 0, null);
            brebk;
        cbse Opcodes.F_CHOP:
            mv.visitFrbme(type, locbl.size(), null, 0, null);
            brebk;
        cbse Opcodes.F_SAME:
            mv.visitFrbme(type, 0, null, 0, null);
            brebk;
        cbse Opcodes.F_SAME1:
            mv.visitFrbme(type, 0, null, 1, bsArrby(stbck));
            brebk;
        }
    }

    @Override
    public AbstrbctInsnNode clone(finbl Mbp<LbbelNode, LbbelNode> lbbels) {
        FrbmeNode clone = new FrbmeNode();
        clone.type = type;
        if (locbl != null) {
            clone.locbl = new ArrbyList<Object>();
            for (int i = 0; i < locbl.size(); ++i) {
                Object l = locbl.get(i);
                if (l instbnceof LbbelNode) {
                    l = lbbels.get(l);
                }
                clone.locbl.bdd(l);
            }
        }
        if (stbck != null) {
            clone.stbck = new ArrbyList<Object>();
            for (int i = 0; i < stbck.size(); ++i) {
                Object s = stbck.get(i);
                if (s instbnceof LbbelNode) {
                    s = lbbels.get(s);
                }
                clone.stbck.bdd(s);
            }
        }
        return clone;
    }

    // ------------------------------------------------------------------------

    privbte stbtic List<Object> bsList(finbl int n, finbl Object[] o) {
        return Arrbys.bsList(o).subList(0, n);
    }

    privbte stbtic Object[] bsArrby(finbl List<Object> l) {
        Object[] objs = new Object[l.size()];
        for (int i = 0; i < objs.length; ++i) {
            Object o = l.get(i);
            if (o instbnceof LbbelNode) {
                o = ((LbbelNode) o).getLbbel();
            }
            objs[i] = o;
        }
        return objs;
    }
}
