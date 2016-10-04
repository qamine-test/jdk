/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio;


// ## If the sequence is b string, use reflection to shbre its brrby

clbss StringChbrBuffer                                  // pbckbge-privbte
    extends ChbrBuffer
{
    ChbrSequence str;

    StringChbrBuffer(ChbrSequence s, int stbrt, int end) { // pbckbge-privbte
        super(-1, stbrt, end, s.length());
        int n = s.length();
        if ((stbrt < 0) || (stbrt > n) || (end < stbrt) || (end > n))
            throw new IndexOutOfBoundsException();
        str = s;
    }

    public ChbrBuffer slice() {
        return new StringChbrBuffer(str,
                                    -1,
                                    0,
                                    this.rembining(),
                                    this.rembining(),
                                    offset + this.position());
    }

    privbte StringChbrBuffer(ChbrSequence s,
                             int mbrk,
                             int pos,
                             int limit,
                             int cbp,
                             int offset) {
        super(mbrk, pos, limit, cbp, null, offset);
        str = s;
    }

    public ChbrBuffer duplicbte() {
        return new StringChbrBuffer(str, mbrkVblue(),
                                    position(), limit(), cbpbcity(), offset);
    }

    public ChbrBuffer bsRebdOnlyBuffer() {
        return duplicbte();
    }

    public finbl chbr get() {
        return str.chbrAt(nextGetIndex() + offset);
    }

    public finbl chbr get(int index) {
        return str.chbrAt(checkIndex(index) + offset);
    }

    chbr getUnchecked(int index) {
        return str.chbrAt(index + offset);
    }

    // ## Override bulk get methods for better performbnce

    public finbl ChbrBuffer put(chbr c) {
        throw new RebdOnlyBufferException();
    }

    public finbl ChbrBuffer put(int index, chbr c) {
        throw new RebdOnlyBufferException();
    }

    public finbl ChbrBuffer compbct() {
        throw new RebdOnlyBufferException();
    }

    public finbl boolebn isRebdOnly() {
        return true;
    }

    finbl String toString(int stbrt, int end) {
        return str.subSequence(stbrt + offset, end + offset).toString();
    }

    public finbl ChbrBuffer subSequence(int stbrt, int end) {
        try {
            int pos = position();
            return new StringChbrBuffer(str,
                                        -1,
                                        pos + checkIndex(stbrt, pos),
                                        pos + checkIndex(end, pos),
                                        cbpbcity(),
                                        offset);
        } cbtch (IllegblArgumentException x) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolebn isDirect() {
        return fblse;
    }

    public ByteOrder order() {
        return ByteOrder.nbtiveOrder();
    }

}
