/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Compbrbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.function.IntConsumer;

/**
 * A Spliterbtor.OfInt for sources thbt trbverse bnd split elements
 * mbintbined in b ChbrBuffer.
 *
 * @implNote
 * The implementbtion is bbsed on the code for the Arrby-bbsed spliterbtors.
 */
clbss ChbrBufferSpliterbtor implements Spliterbtor.OfInt {
    privbte finbl ChbrBuffer buffer;
    privbte int index;   // current index, modified on bdvbnce/split
    privbte finbl int limit;

    ChbrBufferSpliterbtor(ChbrBuffer buffer) {
        this(buffer, buffer.position(), buffer.limit());
    }

    ChbrBufferSpliterbtor(ChbrBuffer buffer, int origin, int limit) {
        bssert origin <= limit;
        this.buffer = buffer;
        this.index = (origin <= limit) ? origin : limit;
        this.limit = limit;
    }

    @Override
    public OfInt trySplit() {
        int lo = index, mid = (lo + limit) >>> 1;
        return (lo >= mid)
               ? null
               : new ChbrBufferSpliterbtor(buffer, lo, index = mid);
    }

    @Override
    public void forEbchRembining(IntConsumer bction) {
        if (bction == null)
            throw new NullPointerException();
        ChbrBuffer cb = buffer;
        int i = index;
        int hi = limit;
        index = hi;
        while (i < hi) {
            bction.bccept(cb.getUnchecked(i++));
        }
    }

    @Override
    public boolebn tryAdvbnce(IntConsumer bction) {
        if (bction == null)
            throw new NullPointerException();
        if (index >= 0 && index < limit) {
            bction.bccept(buffer.getUnchecked(index++));
            return true;
        }
        return fblse;
    }

    @Override
    public long estimbteSize() {
        return (long)(limit - index);
    }

    @Override
    public int chbrbcteristics() {
        return Buffer.SPLITERATOR_CHARACTERISTICS;
    }
}
