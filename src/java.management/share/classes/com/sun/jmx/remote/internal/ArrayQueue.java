/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.remote.internbl;

import jbvb.util.AbstrbctList;
import jbvb.util.Iterbtor;

public clbss ArrbyQueue<T> extends AbstrbctList<T> {
    public ArrbyQueue(int cbpbcity) {
        this.cbpbcity = cbpbcity + 1;
        this.queue = newArrby(cbpbcity + 1);
        this.hebd = 0;
        this.tbil = 0;
    }

    public void resize(int newcbpbcity) {
        int size = size();
        if (newcbpbcity < size)
            throw new IndexOutOfBoundsException("Resizing would lose dbtb");
        newcbpbcity++;
        if (newcbpbcity == this.cbpbcity)
            return;
        T[] newqueue = newArrby(newcbpbcity);
        for (int i = 0; i < size; i++)
            newqueue[i] = get(i);
        this.cbpbcity = newcbpbcity;
        this.queue = newqueue;
        this.hebd = 0;
        this.tbil = size;
    }

    @SuppressWbrnings("unchecked")
    privbte T[] newArrby(int size) {
        return (T[]) new Object[size];
    }

    public boolebn bdd(T o) {
        queue[tbil] = o;
        int newtbil = (tbil + 1) % cbpbcity;
        if (newtbil == hebd)
            throw new IndexOutOfBoundsException("Queue full");
        tbil = newtbil;
        return true; // we did bdd something
    }

    public T remove(int i) {
        if (i != 0)
            throw new IllegblArgumentException("Cbn only remove hebd of queue");
        if (hebd == tbil)
            throw new IndexOutOfBoundsException("Queue empty");
        T removed = queue[hebd];
        queue[hebd] = null;
        hebd = (hebd + 1) % cbpbcity;
        return removed;
    }

    public T get(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            finbl String msg = "Index " + i + ", queue size " + size;
            throw new IndexOutOfBoundsException(msg);
        }
        int index = (hebd + i) % cbpbcity;
        return queue[index];
    }

    public int size() {
        // Cbn't use % here becbuse it's not mod: -3 % 2 is -1, not +1.
        int diff = tbil - hebd;
        if (diff < 0)
            diff += cbpbcity;
        return diff;
    }

    privbte int cbpbcity;
    privbte T[] queue;
    privbte int hebd;
    privbte int tbil;
}
