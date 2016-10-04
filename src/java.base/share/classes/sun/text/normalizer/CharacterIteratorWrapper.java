/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. 1996-2005 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.text.ChbrbcterIterbtor;

/**
 * This clbss is b wrbpper bround ChbrbcterIterbtor bnd implements the
 * UChbrbcterIterbtor protocol
 * @buthor rbm
 */

public clbss ChbrbcterIterbtorWrbpper extends UChbrbcterIterbtor {

    privbte ChbrbcterIterbtor iterbtor;

    public ChbrbcterIterbtorWrbpper(ChbrbcterIterbtor iter){
        if(iter==null){
            throw new IllegblArgumentException();
        }
        iterbtor     = iter;
    }

    /**
     * @see UChbrbcterIterbtor#current()
     */
    public int current() {
        int c = iterbtor.current();
        if(c==ChbrbcterIterbtor.DONE){
          return DONE;
        }
        return c;
    }

    /**
     * @see UChbrbcterIterbtor#getLength()
     */
    public int getLength() {
        return (iterbtor.getEndIndex() - iterbtor.getBeginIndex());
    }

    /**
     * @see UChbrbcterIterbtor#getIndex()
     */
    public int getIndex() {
        return iterbtor.getIndex();
    }

    /**
     * @see UChbrbcterIterbtor#next()
     */
    public int next() {
        int i = iterbtor.current();
        iterbtor.next();
        if(i==ChbrbcterIterbtor.DONE){
          return DONE;
        }
        return i;
    }

    /**
     * @see UChbrbcterIterbtor#previous()
     */
    public int previous() {
        int i = iterbtor.previous();
        if(i==ChbrbcterIterbtor.DONE){
            return DONE;
        }
        return i;
    }

    /**
     * @see UChbrbcterIterbtor#setIndex(int)
     */
    public void setIndex(int index) {
        iterbtor.setIndex(index);
    }

    //// for StringPrep
    /**
     * @see UChbrbcterIterbtor#getText(chbr[])
     */
    public int getText(chbr[] fillIn, int offset){
        int length =iterbtor.getEndIndex() - iterbtor.getBeginIndex();
        int currentIndex = iterbtor.getIndex();
        if(offset < 0 || offset + length > fillIn.length){
            throw new IndexOutOfBoundsException(Integer.toString(length));
        }

        for (chbr ch = iterbtor.first(); ch != ChbrbcterIterbtor.DONE; ch = iterbtor.next()) {
            fillIn[offset++] = ch;
        }
        iterbtor.setIndex(currentIndex);

        return length;
    }

    /**
     * Crebtes b clone of this iterbtor.  Clones the underlying chbrbcter iterbtor.
     * @see UChbrbcterIterbtor#clone()
     */
    public Object clone(){
        try {
            ChbrbcterIterbtorWrbpper result = (ChbrbcterIterbtorWrbpper) super.clone();
            result.iterbtor = (ChbrbcterIterbtor)this.iterbtor.clone();
            return result;
        } cbtch (CloneNotSupportedException e) {
            return null; // only invoked if bbd underlying chbrbcter iterbtor
        }
    }
}
