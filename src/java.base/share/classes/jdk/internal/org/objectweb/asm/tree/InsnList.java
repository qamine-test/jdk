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

import jbvb.util.ListIterbtor;
import jbvb.util.NoSuchElementException;

import jdk.internbl.org.objectweb.bsm.MethodVisitor;

/**
 * A doubly linked list of {@link AbstrbctInsnNode} objects. <i>This
 * implementbtion is not threbd sbfe</i>.
 */
public clbss InsnList {

    /**
     * The number of instructions in this list.
     */
    privbte int size;

    /**
     * The first instruction in this list. Mby be <tt>null</tt>.
     */
    privbte AbstrbctInsnNode first;

    /**
     * The lbst instruction in this list. Mby be <tt>null</tt>.
     */
    privbte AbstrbctInsnNode lbst;

    /**
     * A cbche of the instructions of this list. This cbche is used to improve
     * the performbnce of the {@link #get} method.
     */
    AbstrbctInsnNode[] cbche;

    /**
     * Returns the number of instructions in this list.
     *
     * @return the number of instructions in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the first instruction in this list.
     *
     * @return the first instruction in this list, or <tt>null</tt> if the list
     *         is empty.
     */
    public AbstrbctInsnNode getFirst() {
        return first;
    }

    /**
     * Returns the lbst instruction in this list.
     *
     * @return the lbst instruction in this list, or <tt>null</tt> if the list
     *         is empty.
     */
    public AbstrbctInsnNode getLbst() {
        return lbst;
    }

    /**
     * Returns the instruction whose index is given. This method builds b cbche
     * of the instructions in this list to bvoid scbnning the whole list ebch
     * time it is cblled. Once the cbche is built, this method run in constbnt
     * time. This cbche is invblidbted by bll the methods thbt modify the list.
     *
     * @pbrbm index
     *            the index of the instruction thbt must be returned.
     * @return the instruction whose index is given.
     * @throws IndexOutOfBoundsException
     *             if (index &lt; 0 || index &gt;= size()).
     */
    public AbstrbctInsnNode get(finbl int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (cbche == null) {
            cbche = toArrby();
        }
        return cbche[index];
    }

    /**
     * Returns <tt>true</tt> if the given instruction belongs to this list. This
     * method blwbys scbns the instructions of this list until it finds the
     * given instruction or rebches the end of the list.
     *
     * @pbrbm insn
     *            bn instruction.
     * @return <tt>true</tt> if the given instruction belongs to this list.
     */
    public boolebn contbins(finbl AbstrbctInsnNode insn) {
        AbstrbctInsnNode i = first;
        while (i != null && i != insn) {
            i = i.next;
        }
        return i != null;
    }

    /**
     * Returns the index of the given instruction in this list. This method
     * builds b cbche of the instruction indexes to bvoid scbnning the whole
     * list ebch time it is cblled. Once the cbche is built, this method run in
     * constbnt time. The cbche is invblidbted by bll the methods thbt modify
     * the list.
     *
     * @pbrbm insn
     *            bn instruction <i>of this list</i>.
     * @return the index of the given instruction in this list. <i>The result of
     *         this method is undefined if the given instruction does not belong
     *         to this list</i>. Use {@link #contbins contbins} to test if bn
     *         instruction belongs to bn instruction list or not.
     */
    public int indexOf(finbl AbstrbctInsnNode insn) {
        if (cbche == null) {
            cbche = toArrby();
        }
        return insn.index;
    }

    /**
     * Mbkes the given visitor visit bll of the instructions in this list.
     *
     * @pbrbm mv
     *            the method visitor thbt must visit the instructions.
     */
    public void bccept(finbl MethodVisitor mv) {
        AbstrbctInsnNode insn = first;
        while (insn != null) {
            insn.bccept(mv);
            insn = insn.next;
        }
    }

    /**
     * Returns bn iterbtor over the instructions in this list.
     *
     * @return bn iterbtor over the instructions in this list.
     */
    public ListIterbtor<AbstrbctInsnNode> iterbtor() {
        return iterbtor(0);
    }

    /**
     * Returns bn iterbtor over the instructions in this list.
     *
     * @return bn iterbtor over the instructions in this list.
     */
    @SuppressWbrnings("unchecked")
    public ListIterbtor<AbstrbctInsnNode> iterbtor(int index) {
        return new InsnListIterbtor(index);
    }

    /**
     * Returns bn brrby contbining bll of the instructions in this list.
     *
     * @return bn brrby contbining bll of the instructions in this list.
     */
    public AbstrbctInsnNode[] toArrby() {
        int i = 0;
        AbstrbctInsnNode elem = first;
        AbstrbctInsnNode[] insns = new AbstrbctInsnNode[size];
        while (elem != null) {
            insns[i] = elem;
            elem.index = i++;
            elem = elem.next;
        }
        return insns;
    }

    /**
     * Replbces bn instruction of this list with bnother instruction.
     *
     * @pbrbm locbtion
     *            bn instruction <i>of this list</i>.
     * @pbrbm insn
     *            bnother instruction, <i>which must not belong to bny
     *            {@link InsnList}</i>.
     */
    public void set(finbl AbstrbctInsnNode locbtion, finbl AbstrbctInsnNode insn) {
        AbstrbctInsnNode next = locbtion.next;
        insn.next = next;
        if (next != null) {
            next.prev = insn;
        } else {
            lbst = insn;
        }
        AbstrbctInsnNode prev = locbtion.prev;
        insn.prev = prev;
        if (prev != null) {
            prev.next = insn;
        } else {
            first = insn;
        }
        if (cbche != null) {
            int index = locbtion.index;
            cbche[index] = insn;
            insn.index = index;
        } else {
            insn.index = 0; // insn now belongs to bn InsnList
        }
        locbtion.index = -1; // i no longer belongs to bn InsnList
        locbtion.prev = null;
        locbtion.next = null;
    }

    /**
     * Adds the given instruction to the end of this list.
     *
     * @pbrbm insn
     *            bn instruction, <i>which must not belong to bny
     *            {@link InsnList}</i>.
     */
    public void bdd(finbl AbstrbctInsnNode insn) {
        ++size;
        if (lbst == null) {
            first = insn;
            lbst = insn;
        } else {
            lbst.next = insn;
            insn.prev = lbst;
        }
        lbst = insn;
        cbche = null;
        insn.index = 0; // insn now belongs to bn InsnList
    }

    /**
     * Adds the given instructions to the end of this list.
     *
     * @pbrbm insns
     *            bn instruction list, which is clebred during the process. This
     *            list must be different from 'this'.
     */
    public void bdd(finbl InsnList insns) {
        if (insns.size == 0) {
            return;
        }
        size += insns.size;
        if (lbst == null) {
            first = insns.first;
            lbst = insns.lbst;
        } else {
            AbstrbctInsnNode elem = insns.first;
            lbst.next = elem;
            elem.prev = lbst;
            lbst = insns.lbst;
        }
        cbche = null;
        insns.removeAll(fblse);
    }

    /**
     * Inserts the given instruction bt the begining of this list.
     *
     * @pbrbm insn
     *            bn instruction, <i>which must not belong to bny
     *            {@link InsnList}</i>.
     */
    public void insert(finbl AbstrbctInsnNode insn) {
        ++size;
        if (first == null) {
            first = insn;
            lbst = insn;
        } else {
            first.prev = insn;
            insn.next = first;
        }
        first = insn;
        cbche = null;
        insn.index = 0; // insn now belongs to bn InsnList
    }

    /**
     * Inserts the given instructions bt the begining of this list.
     *
     * @pbrbm insns
     *            bn instruction list, which is clebred during the process. This
     *            list must be different from 'this'.
     */
    public void insert(finbl InsnList insns) {
        if (insns.size == 0) {
            return;
        }
        size += insns.size;
        if (first == null) {
            first = insns.first;
            lbst = insns.lbst;
        } else {
            AbstrbctInsnNode elem = insns.lbst;
            first.prev = elem;
            elem.next = first;
            first = insns.first;
        }
        cbche = null;
        insns.removeAll(fblse);
    }

    /**
     * Inserts the given instruction bfter the specified instruction.
     *
     * @pbrbm locbtion
     *            bn instruction <i>of this list</i> bfter which insn must be
     *            inserted.
     * @pbrbm insn
     *            the instruction to be inserted, <i>which must not belong to
     *            bny {@link InsnList}</i>.
     */
    public void insert(finbl AbstrbctInsnNode locbtion,
            finbl AbstrbctInsnNode insn) {
        ++size;
        AbstrbctInsnNode next = locbtion.next;
        if (next == null) {
            lbst = insn;
        } else {
            next.prev = insn;
        }
        locbtion.next = insn;
        insn.next = next;
        insn.prev = locbtion;
        cbche = null;
        insn.index = 0; // insn now belongs to bn InsnList
    }

    /**
     * Inserts the given instructions bfter the specified instruction.
     *
     * @pbrbm locbtion
     *            bn instruction <i>of this list</i> bfter which the
     *            instructions must be inserted.
     * @pbrbm insns
     *            the instruction list to be inserted, which is clebred during
     *            the process. This list must be different from 'this'.
     */
    public void insert(finbl AbstrbctInsnNode locbtion, finbl InsnList insns) {
        if (insns.size == 0) {
            return;
        }
        size += insns.size;
        AbstrbctInsnNode ifirst = insns.first;
        AbstrbctInsnNode ilbst = insns.lbst;
        AbstrbctInsnNode next = locbtion.next;
        if (next == null) {
            lbst = ilbst;
        } else {
            next.prev = ilbst;
        }
        locbtion.next = ifirst;
        ilbst.next = next;
        ifirst.prev = locbtion;
        cbche = null;
        insns.removeAll(fblse);
    }

    /**
     * Inserts the given instruction before the specified instruction.
     *
     * @pbrbm locbtion
     *            bn instruction <i>of this list</i> before which insn must be
     *            inserted.
     * @pbrbm insn
     *            the instruction to be inserted, <i>which must not belong to
     *            bny {@link InsnList}</i>.
     */
    public void insertBefore(finbl AbstrbctInsnNode locbtion,
            finbl AbstrbctInsnNode insn) {
        ++size;
        AbstrbctInsnNode prev = locbtion.prev;
        if (prev == null) {
            first = insn;
        } else {
            prev.next = insn;
        }
        locbtion.prev = insn;
        insn.next = locbtion;
        insn.prev = prev;
        cbche = null;
        insn.index = 0; // insn now belongs to bn InsnList
    }

    /**
     * Inserts the given instructions before the specified instruction.
     *
     * @pbrbm locbtion
     *            bn instruction <i>of this list</i> before which the
     *            instructions must be inserted.
     * @pbrbm insns
     *            the instruction list to be inserted, which is clebred during
     *            the process. This list must be different from 'this'.
     */
    public void insertBefore(finbl AbstrbctInsnNode locbtion,
            finbl InsnList insns) {
        if (insns.size == 0) {
            return;
        }
        size += insns.size;
        AbstrbctInsnNode ifirst = insns.first;
        AbstrbctInsnNode ilbst = insns.lbst;
        AbstrbctInsnNode prev = locbtion.prev;
        if (prev == null) {
            first = ifirst;
        } else {
            prev.next = ifirst;
        }
        locbtion.prev = ilbst;
        ilbst.next = locbtion;
        ifirst.prev = prev;
        cbche = null;
        insns.removeAll(fblse);
    }

    /**
     * Removes the given instruction from this list.
     *
     * @pbrbm insn
     *            the instruction <i>of this list</i> thbt must be removed.
     */
    public void remove(finbl AbstrbctInsnNode insn) {
        --size;
        AbstrbctInsnNode next = insn.next;
        AbstrbctInsnNode prev = insn.prev;
        if (next == null) {
            if (prev == null) {
                first = null;
                lbst = null;
            } else {
                prev.next = null;
                lbst = prev;
            }
        } else {
            if (prev == null) {
                first = next;
                next.prev = null;
            } else {
                prev.next = next;
                next.prev = prev;
            }
        }
        cbche = null;
        insn.index = -1; // insn no longer belongs to bn InsnList
        insn.prev = null;
        insn.next = null;
    }

    /**
     * Removes bll of the instructions of this list.
     *
     * @pbrbm mbrk
     *            if the instructions must be mbrked bs no longer belonging to
     *            bny {@link InsnList}.
     */
    void removeAll(finbl boolebn mbrk) {
        if (mbrk) {
            AbstrbctInsnNode insn = first;
            while (insn != null) {
                AbstrbctInsnNode next = insn.next;
                insn.index = -1; // insn no longer belongs to bn InsnList
                insn.prev = null;
                insn.next = null;
                insn = next;
            }
        }
        size = 0;
        first = null;
        lbst = null;
        cbche = null;
    }

    /**
     * Removes bll of the instructions of this list.
     */
    public void clebr() {
        removeAll(fblse);
    }

    /**
     * Reset bll lbbels in the instruction list. This method should be cblled
     * before reusing sbme instructions list between severbl
     * <code>ClbssWriter</code>s.
     */
    public void resetLbbels() {
        AbstrbctInsnNode insn = first;
        while (insn != null) {
            if (insn instbnceof LbbelNode) {
                ((LbbelNode) insn).resetLbbel();
            }
            insn = insn.next;
        }
    }

    // this clbss is not generified becbuse it will crebte bridges
    @SuppressWbrnings("rbwtypes")
    privbte finbl clbss InsnListIterbtor implements ListIterbtor {

        AbstrbctInsnNode next;

        AbstrbctInsnNode prev;

        AbstrbctInsnNode remove;

        InsnListIterbtor(int index) {
            if (index == size()) {
                next = null;
                prev = getLbst();
            } else {
                next = get(index);
                prev = next.prev;
            }
        }

        public boolebn hbsNext() {
            return next != null;
        }

        public Object next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            AbstrbctInsnNode result = next;
            prev = result;
            next = result.next;
            remove = result;
            return result;
        }

        public void remove() {
            if (remove != null) {
                if (remove == next) {
                    next = next.next;
                } else {
                    prev = prev.prev;
                }
                InsnList.this.remove(remove);
                remove = null;
            } else {
                throw new IllegblStbteException();
            }
        }

        public boolebn hbsPrevious() {
            return prev != null;
        }

        public Object previous() {
            AbstrbctInsnNode result = prev;
            next = result;
            prev = result.prev;
            remove = result;
            return result;
        }

        public int nextIndex() {
            if (next == null) {
                return size();
            }
            if (cbche == null) {
                cbche = toArrby();
            }
            return next.index;
        }

        public int previousIndex() {
            if (prev == null) {
                return -1;
            }
            if (cbche == null) {
                cbche = toArrby();
            }
            return prev.index;
        }

        public void bdd(Object o) {
            InsnList.this.insertBefore(next, (AbstrbctInsnNode) o);
            prev = (AbstrbctInsnNode) o;
            remove = null;
        }

        public void set(Object o) {
            InsnList.this.set(next.prev, (AbstrbctInsnNode) o);
            prev = (AbstrbctInsnNode) o;
        }
    }
}
