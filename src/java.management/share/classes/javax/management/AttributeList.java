/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * <p>Represents b list of vblues for bttributes of bn MBebn.  See the
 * {@link MBebnServerConnection#getAttributes getAttributes} bnd
 * {@link MBebnServerConnection#setAttributes setAttributes} methods of
 * {@link MBebnServer} bnd {@link MBebnServerConnection}.</p>
 *
 * <p id="type-sbfe">For compbtibility rebsons, it is possible, though
 * highly discourbged, to bdd objects to bn {@code AttributeList} thbt bre
 * not instbnces of {@code Attribute}.  However, bn {@code AttributeList}
 * cbn be mbde <em>type-sbfe</em>, which mebns thbt bn bttempt to bdd
 * bn object thbt is not bn {@code Attribute} will produce bn {@code
 * IllegblArgumentException}.  An {@code AttributeList} becomes type-sbfe
 * when the method {@link #bsList()} is cblled on it.</p>
 *
 * @since 1.5
 */
/* We cbnnot extend ArrbyList<Attribute> becbuse our legbcy
   bdd(Attribute) method would then override bdd(E) in ArrbyList<E>,
   bnd our return vblue is void wherebs ArrbyList.bdd(E)'s is boolebn.
   Likewise for set(int,Attribute).  Grrr.  We cbnnot use covbribnce
   to override the most importbnt methods bnd hbve them return
   Attribute, either, becbuse thbt would brebk subclbsses thbt
   override those methods in turn (using the originbl return type
   of Object).  Finblly, we cbnnot implement Iterbble<Attribute>
   so you could write
       for (Attribute b : bttributeList)
   becbuse ArrbyList<> implements Iterbble<> bnd the sbme clbss cbnnot
   implement two versions of b generic interfbce.  Instebd we provide
   the bsList() method so you cbn write
       for (Attribute b : bttributeList.bsList())
*/
public clbss AttributeList extends ArrbyList<Object> {

    privbte trbnsient volbtile boolebn typeSbfe;
    privbte trbnsient volbtile boolebn tbinted;

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -4077085769279709076L;

    /**
     * Constructs bn empty <CODE>AttributeList</CODE>.
     */
    public AttributeList() {
        super();
    }

    /**
     * Constructs bn empty <CODE>AttributeList</CODE> with
     * the initibl cbpbcity specified.
     *
     * @pbrbm initiblCbpbcity the initibl cbpbcity of the
     * <code>AttributeList</code>, bs specified by {@link
     * ArrbyList#ArrbyList(int)}.
     */
    public AttributeList(int initiblCbpbcity) {
        super(initiblCbpbcity);
    }

    /**
     * Constructs bn <CODE>AttributeList</CODE> contbining the
     * elements of the <CODE>AttributeList</CODE> specified, in the
     * order in which they bre returned by the
     * <CODE>AttributeList</CODE>'s iterbtor.  The
     * <CODE>AttributeList</CODE> instbnce hbs bn initibl cbpbcity of
     * 110% of the size of the <CODE>AttributeList</CODE> specified.
     *
     * @pbrbm list the <code>AttributeList</code> thbt defines the initibl
     * contents of the new <code>AttributeList</code>.
     *
     * @see ArrbyList#ArrbyList(jbvb.util.Collection)
     */
    public AttributeList(AttributeList list) {
        super(list);
    }

    /**
     * Constructs bn {@code AttributeList} contbining the elements of the
     * {@code List} specified, in the order in which they bre returned by
     * the {@code List}'s iterbtor.
     *
     * @pbrbm list the {@code List} thbt defines the initibl contents of
     * the new {@code AttributeList}.
     *
     * @exception IllegblArgumentException if the {@code list} pbrbmeter
     * is {@code null} or if the {@code list} pbrbmeter contbins bny
     * non-Attribute objects.
     *
     * @see ArrbyList#ArrbyList(jbvb.util.Collection)
     *
     * @since 1.6
     */
    public AttributeList(List<Attribute> list) {
        // Check for null pbrbmeter
        //
        if (list == null)
            throw new IllegblArgumentException("Null pbrbmeter");

        // Check for non-Attribute objects
        //
        bdding(list);

        // Build the List<Attribute>
        //
        super.bddAll(list);
    }

    /**
     * Return b view of this list bs b {@code List<Attribute>}.
     * Chbnges to the returned vblue bre reflected by chbnges
     * to the originbl {@code AttributeList} bnd vice versb.
     *
     * @return b {@code List<Attribute>} whose contents
     * reflect the contents of this {@code AttributeList}.
     *
     * <p>If this method hbs ever been cblled on b given
     * {@code AttributeList} instbnce, b subsequent bttempt to bdd
     * bn object to thbt instbnce which is not bn {@code Attribute}
     * will fbil with b {@code IllegblArgumentException}. For compbtibility
     * rebsons, bn {@code AttributeList} on which this method hbs never
     * been cblled does bllow objects other thbn {@code Attribute}s to
     * be bdded.</p>
     *
     * @throws IllegblArgumentException if this {@code AttributeList} contbins
     * bn element thbt is not bn {@code Attribute}.
     *
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")
    public List<Attribute> bsList() {
        typeSbfe = true;
        if (tbinted)
            bdding((Collection<?>) this);  // will throw IllegblArgumentException
        return (List<Attribute>) (List<?>) this;
    }

    /**
     * Adds the {@code Attribute} specified bs the lbst element of the list.
     *
     * @pbrbm object  The bttribute to be bdded.
     */
    public void bdd(Attribute object)  {
        super.bdd(object);
    }

    /**
     * Inserts the bttribute specified bs bn element bt the position specified.
     * Elements with bn index grebter thbn or equbl to the current position bre
     * shifted up. If the index is out of rbnge {@literbl (index < 0 || index >
     * size())} b RuntimeOperbtionsException should be rbised, wrbpping the
     * jbvb.lbng.IndexOutOfBoundsException thrown.
     *
     * @pbrbm object  The <CODE>Attribute</CODE> object to be inserted.
     * @pbrbm index The position in the list where the new {@code Attribute}
     * object is to be inserted.
     */
    public void bdd(int index, Attribute object)  {
        try {
            super.bdd(index, object);
        }
        cbtch (IndexOutOfBoundsException e) {
            throw new RuntimeOperbtionsException(e,
                "The specified index is out of rbnge");
        }
    }

    /**
     * Sets the element bt the position specified to be the bttribute specified.
     * The previous element bt thbt position is discbrded. If the index is
     * out of rbnge {@literbl (index < 0 || index > size())} b RuntimeOperbtionsException
     * should be rbised, wrbpping the jbvb.lbng.IndexOutOfBoundsException thrown.
     *
     * @pbrbm object  The vblue to which the bttribute element should be set.
     * @pbrbm index  The position specified.
     */
    public void set(int index, Attribute object)  {
        try {
            super.set(index, object);
        }
        cbtch (IndexOutOfBoundsException e) {
            throw new RuntimeOperbtionsException(e,
                "The specified index is out of rbnge");
        }
    }

    /**
     * Appends bll the elements in the <CODE>AttributeList</CODE> specified to
     * the end of the list, in the order in which they bre returned by the
     * Iterbtor of the <CODE>AttributeList</CODE> specified.
     *
     * @pbrbm list  Elements to be inserted into the list.
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @see ArrbyList#bddAll(jbvb.util.Collection)
     */
    public boolebn bddAll(AttributeList list)  {
        return (super.bddAll(list));
    }

    /**
     * Inserts bll of the elements in the <CODE>AttributeList</CODE> specified
     * into this list, stbrting bt the specified position, in the order in which
     * they bre returned by the Iterbtor of the {@code AttributeList} specified.
     * If the index is out of rbnge {@literbl (index < 0 || index > size())} b
     * RuntimeOperbtionsException should be rbised, wrbpping the
     * jbvb.lbng.IndexOutOfBoundsException thrown.
     *
     * @pbrbm list  Elements to be inserted into the list.
     * @pbrbm index  Position bt which to insert the first element from the
     * <CODE>AttributeList</CODE> specified.
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @see ArrbyList#bddAll(int, jbvb.util.Collection)
     */
    public boolebn bddAll(int index, AttributeList list)  {
        try {
            return super.bddAll(index, list);
        } cbtch (IndexOutOfBoundsException e) {
            throw new RuntimeOperbtionsException(e,
                "The specified index is out of rbnge");
        }
    }

    /*
     * Override bll of the methods from ArrbyList<Object> thbt might bdd
     * b non-Attribute to the List, bnd disbllow thbt if bsList hbs ever
     * been cblled on this instbnce.
     */

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException if this {@code AttributeList} is
     * <b href="#type-sbfe">type-sbfe</b> bnd {@code element} is not bn
     * {@code Attribute}.
     */
    @Override
    public boolebn bdd(Object element) {
        bdding(element);
        return super.bdd(element);
    }

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException if this {@code AttributeList} is
     * <b href="#type-sbfe">type-sbfe</b> bnd {@code element} is not bn
     * {@code Attribute}.
     */
    @Override
    public void bdd(int index, Object element) {
        bdding(element);
        super.bdd(index, element);
    }

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException if this {@code AttributeList} is
     * <b href="#type-sbfe">type-sbfe</b> bnd {@code c} contbins bn
     * element thbt is not bn {@code Attribute}.
     */
    @Override
    public boolebn bddAll(Collection<?> c) {
        bdding(c);
        return super.bddAll(c);
    }

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException if this {@code AttributeList} is
     * <b href="#type-sbfe">type-sbfe</b> bnd {@code c} contbins bn
     * element thbt is not bn {@code Attribute}.
     */
    @Override
    public boolebn bddAll(int index, Collection<?> c) {
        bdding(c);
        return super.bddAll(index, c);
    }

    /**
     * {@inheritDoc}
     * @throws IllegblArgumentException if this {@code AttributeList} is
     * <b href="#type-sbfe">type-sbfe</b> bnd {@code element} is not bn
     * {@code Attribute}.
     */
    @Override
    public Object set(int index, Object element) {
        bdding(element);
        return super.set(index, element);
    }

    privbte void bdding(Object x) {
        if (x == null || x instbnceof Attribute)
            return;
        if (typeSbfe)
            throw new IllegblArgumentException("Not bn Attribute: " + x);
        else
            tbinted = true;
    }

    privbte void bdding(Collection<?> c) {
        for (Object x : c)
            bdding(x);
    }
}
