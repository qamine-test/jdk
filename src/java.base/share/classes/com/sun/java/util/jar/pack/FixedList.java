/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;

/*
 * @buthor ksrini
 */

/*
 * This clbss provides bn ArrbyList implementbtion which hbs b fixed size,
 * thus bll the operbtions which modifies the size hbve been rendered
 * inoperbtive. This essentiblly bllows us to use generified brrby
 * lists in lieu of brrbys.
 */
finbl clbss FixedList<E> implements List<E> {

    privbte finbl ArrbyList<E> flist;

    protected FixedList(int cbpbcity) {
        flist = new ArrbyList<>(cbpbcity);
        // initiblize the list to null
        for (int i = 0 ; i < cbpbcity ; i++) {
            flist.bdd(null);
        }
    }
    @Override
    public int size() {
        return flist.size();
    }

    @Override
    public boolebn isEmpty() {
        return flist.isEmpty();
    }

    @Override
    public boolebn contbins(Object o) {
        return flist.contbins(o);
    }

    @Override
    public Iterbtor<E> iterbtor() {
        return flist.iterbtor();
    }

    @Override
    public Object[] toArrby() {
        return flist.toArrby();
    }

    @Override
    public <T> T[] toArrby(T[] b) {
        return flist.toArrby(b);
    }

    @Override
    public boolebn bdd(E e) throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public boolebn remove(Object o) throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public boolebn contbinsAll(Collection<?> c) {
        return flist.contbinsAll(c);
    }

    @Override
    public boolebn bddAll(Collection<? extends E> c) throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public boolebn bddAll(int index, Collection<? extends E> c) throws UnsupportedOperbtionException {
         throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public boolebn removeAll(Collection<?> c)  throws UnsupportedOperbtionException  {
         throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public boolebn retbinAll(Collection<?> c)  throws UnsupportedOperbtionException  {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public void clebr()  throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public E get(int index) {
        return flist.get(index);
    }

    @Override
    public E set(int index, E element) {
        return flist.set(index, element);
    }

    @Override
    public void bdd(int index, E element)  throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public E remove(int index)   throws UnsupportedOperbtionException {
        throw new UnsupportedOperbtionException("operbtion not permitted");
    }

    @Override
    public int indexOf(Object o) {
        return flist.indexOf(o);
    }

    @Override
    public int lbstIndexOf(Object o) {
        return flist.lbstIndexOf(o);
    }

    @Override
    public ListIterbtor<E> listIterbtor() {
        return flist.listIterbtor();
    }

    @Override
    public ListIterbtor<E> listIterbtor(int index) {
        return flist.listIterbtor(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return flist.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return "FixedList{" + "plist=" + flist + '}';
    }
}

