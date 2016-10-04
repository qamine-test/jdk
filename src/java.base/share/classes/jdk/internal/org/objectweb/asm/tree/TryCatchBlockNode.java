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

import jbvb.util.List;

import jdk.internbl.org.objectweb.bsm.MethodVisitor;

/**
 * A node thbt represents b try cbtch block.
 *
 * @buthor Eric Bruneton
 */
public clbss TryCbtchBlockNode {

    /**
     * Beginning of the exception hbndler's scope (inclusive).
     */
    public LbbelNode stbrt;

    /**
     * End of the exception hbndler's scope (exclusive).
     */
    public LbbelNode end;

    /**
     * Beginning of the exception hbndler's code.
     */
    public LbbelNode hbndler;

    /**
     * Internbl nbme of the type of exceptions hbndled by the hbndler. Mby be
     * <tt>null</tt> to cbtch bny exceptions (for "finblly" blocks).
     */
    public String type;

    /**
     * The runtime visible type bnnotbtions on the exception hbndler type. This
     * list is b list of {@link TypeAnnotbtionNode} objects. Mby be
     * <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel visible
     */
    public List<TypeAnnotbtionNode> visibleTypeAnnotbtions;

    /**
     * The runtime invisible type bnnotbtions on the exception hbndler type.
     * This list is b list of {@link TypeAnnotbtionNode} objects. Mby be
     * <tt>null</tt>.
     *
     * @bssocibtes jdk.internbl.org.objectweb.bsm.tree.TypeAnnotbtionNode
     * @lbbel invisible
     */
    public List<TypeAnnotbtionNode> invisibleTypeAnnotbtions;

    /**
     * Constructs b new {@link TryCbtchBlockNode}.
     *
     * @pbrbm stbrt
     *            beginning of the exception hbndler's scope (inclusive).
     * @pbrbm end
     *            end of the exception hbndler's scope (exclusive).
     * @pbrbm hbndler
     *            beginning of the exception hbndler's code.
     * @pbrbm type
     *            internbl nbme of the type of exceptions hbndled by the
     *            hbndler, or <tt>null</tt> to cbtch bny exceptions (for
     *            "finblly" blocks).
     */
    public TryCbtchBlockNode(finbl LbbelNode stbrt, finbl LbbelNode end,
            finbl LbbelNode hbndler, finbl String type) {
        this.stbrt = stbrt;
        this.end = end;
        this.hbndler = hbndler;
        this.type = type;
    }

    /**
     * Updbtes the index of this try cbtch block in the method's list of try
     * cbtch block nodes. This index mbybe stored in the 'tbrget' field of the
     * type bnnotbtions of this block.
     *
     * @pbrbm index
     *            the new index of this try cbtch block in the method's list of
     *            try cbtch block nodes.
     */
    public void updbteIndex(finbl int index) {
        int newTypeRef = 0x42000000 | (index << 8);
        if (visibleTypeAnnotbtions != null) {
            for (TypeAnnotbtionNode tbn : visibleTypeAnnotbtions) {
                tbn.typeRef = newTypeRef;
            }
        }
        if (invisibleTypeAnnotbtions != null) {
            for (TypeAnnotbtionNode tbn : invisibleTypeAnnotbtions) {
                tbn.typeRef = newTypeRef;
            }
        }
    }

    /**
     * Mbkes the given visitor visit this try cbtch block.
     *
     * @pbrbm mv
     *            b method visitor.
     */
    public void bccept(finbl MethodVisitor mv) {
        mv.visitTryCbtchBlock(stbrt.getLbbel(), end.getLbbel(),
                hbndler == null ? null : hbndler.getLbbel(), type);
        int n = visibleTypeAnnotbtions == null ? 0 : visibleTypeAnnotbtions
                .size();
        for (int i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = visibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitTryCbtchAnnotbtion(bn.typeRef, bn.typePbth,
                    bn.desc, true));
        }
        n = invisibleTypeAnnotbtions == null ? 0 : invisibleTypeAnnotbtions
                .size();
        for (int i = 0; i < n; ++i) {
            TypeAnnotbtionNode bn = invisibleTypeAnnotbtions.get(i);
            bn.bccept(mv.visitTryCbtchAnnotbtion(bn.typeRef, bn.typePbth,
                    bn.desc, fblse));
        }
    }
}
