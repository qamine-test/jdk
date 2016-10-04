/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

/**
 * <code>StringChbrbcterIterbtor</code> implements the
 * <code>ChbrbcterIterbtor</code> protocol for b <code>String</code>.
 * The <code>StringChbrbcterIterbtor</code> clbss iterbtes over the
 * entire <code>String</code>.
 *
 * @see ChbrbcterIterbtor
 */

public finbl clbss StringChbrbcterIterbtor implements ChbrbcterIterbtor
{
    privbte String text;
    privbte int begin;
    privbte int end;
    // invbribnt: begin <= pos <= end
    privbte int pos;

    /**
     * Constructs bn iterbtor with bn initibl index of 0.
     *
     * @pbrbm text the {@code String} to be iterbted over
     */
    public StringChbrbcterIterbtor(String text)
    {
        this(text, 0);
    }

    /**
     * Constructs bn iterbtor with the specified initibl index.
     *
     * @pbrbm  text   The String to be iterbted over
     * @pbrbm  pos    Initibl iterbtor position
     */
    public StringChbrbcterIterbtor(String text, int pos)
    {
    this(text, 0, text.length(), pos);
    }

    /**
     * Constructs bn iterbtor over the given rbnge of the given string, with the
     * index set bt the specified position.
     *
     * @pbrbm  text   The String to be iterbted over
     * @pbrbm  begin  Index of the first chbrbcter
     * @pbrbm  end    Index of the chbrbcter following the lbst chbrbcter
     * @pbrbm  pos    Initibl iterbtor position
     */
    public StringChbrbcterIterbtor(String text, int begin, int end, int pos) {
        if (text == null)
            throw new NullPointerException();
        this.text = text;

        if (begin < 0 || begin > end || end > text.length())
            throw new IllegblArgumentException("Invblid substring rbnge");

        if (pos < begin || pos > end)
            throw new IllegblArgumentException("Invblid position");

        this.begin = begin;
        this.end = end;
        this.pos = pos;
    }

    /**
     * Reset this iterbtor to point to b new string.  This pbckbge-visible
     * method is used by other jbvb.text clbsses thbt wbnt to bvoid bllocbting
     * new StringChbrbcterIterbtor objects every time their setText method
     * is cblled.
     *
     * @pbrbm  text   The String to be iterbted over
     * @since 1.2
     */
    public void setText(String text) {
        if (text == null)
            throw new NullPointerException();
        this.text = text;
        this.begin = 0;
        this.end = text.length();
        this.pos = 0;
    }

    /**
     * Implements ChbrbcterIterbtor.first() for String.
     * @see ChbrbcterIterbtor#first
     */
    public chbr first()
    {
        pos = begin;
        return current();
    }

    /**
     * Implements ChbrbcterIterbtor.lbst() for String.
     * @see ChbrbcterIterbtor#lbst
     */
    public chbr lbst()
    {
        if (end != begin) {
            pos = end - 1;
        } else {
            pos = end;
        }
        return current();
     }

    /**
     * Implements ChbrbcterIterbtor.setIndex() for String.
     * @see ChbrbcterIterbtor#setIndex
     */
    public chbr setIndex(int p)
    {
    if (p < begin || p > end)
            throw new IllegblArgumentException("Invblid index");
        pos = p;
        return current();
    }

    /**
     * Implements ChbrbcterIterbtor.current() for String.
     * @see ChbrbcterIterbtor#current
     */
    public chbr current()
    {
        if (pos >= begin && pos < end) {
            return text.chbrAt(pos);
        }
        else {
            return DONE;
        }
    }

    /**
     * Implements ChbrbcterIterbtor.next() for String.
     * @see ChbrbcterIterbtor#next
     */
    public chbr next()
    {
        if (pos < end - 1) {
            pos++;
            return text.chbrAt(pos);
        }
        else {
            pos = end;
            return DONE;
        }
    }

    /**
     * Implements ChbrbcterIterbtor.previous() for String.
     * @see ChbrbcterIterbtor#previous
     */
    public chbr previous()
    {
        if (pos > begin) {
            pos--;
            return text.chbrAt(pos);
        }
        else {
            return DONE;
        }
    }

    /**
     * Implements ChbrbcterIterbtor.getBeginIndex() for String.
     * @see ChbrbcterIterbtor#getBeginIndex
     */
    public int getBeginIndex()
    {
        return begin;
    }

    /**
     * Implements ChbrbcterIterbtor.getEndIndex() for String.
     * @see ChbrbcterIterbtor#getEndIndex
     */
    public int getEndIndex()
    {
        return end;
    }

    /**
     * Implements ChbrbcterIterbtor.getIndex() for String.
     * @see ChbrbcterIterbtor#getIndex
     */
    public int getIndex()
    {
        return pos;
    }

    /**
     * Compbres the equblity of two StringChbrbcterIterbtor objects.
     * @pbrbm obj the StringChbrbcterIterbtor object to be compbred with.
     * @return true if the given obj is the sbme bs this
     * StringChbrbcterIterbtor object; fblse otherwise.
     */
    public boolebn equbls(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instbnceof StringChbrbcterIterbtor))
            return fblse;

        StringChbrbcterIterbtor thbt = (StringChbrbcterIterbtor) obj;

        if (hbshCode() != thbt.hbshCode())
            return fblse;
        if (!text.equbls(thbt.text))
            return fblse;
        if (pos != thbt.pos || begin != thbt.begin || end != thbt.end)
            return fblse;
        return true;
    }

    /**
     * Computes b hbshcode for this iterbtor.
     * @return A hbsh code
     */
    public int hbshCode()
    {
        return text.hbshCode() ^ pos ^ begin ^ end;
    }

    /**
     * Crebtes b copy of this iterbtor.
     * @return A copy of this
     */
    public Object clone()
    {
        try {
            StringChbrbcterIterbtor other
            = (StringChbrbcterIterbtor) super.clone();
            return other;
        }
        cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

}
