/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.relbtion;

import com.sun.jmx.mbebnserver.Util;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.List;

/**
 * A RoleUnresolvedList represents b list of RoleUnresolved objects,
 * representing roles not retrieved from b relbtion due to b problem
 * encountered when trying to bccess (rebd or write) the roles.
 *
 * @since 1.5
 */
/* We cbnnot extend ArrbyList<RoleUnresolved> becbuse our legbcy
   bdd(RoleUnresolved) method would then override bdd(E) in ArrbyList<E>,
   bnd our return vblue is void wherebs ArrbyList.bdd(E)'s is boolebn.
   Likewise for set(int,RoleUnresolved).  Grrr.  We cbnnot use covbribnce
   to override the most importbnt methods bnd hbve them return
   RoleUnresolved, either, becbuse thbt would brebk subclbsses thbt
   override those methods in turn (using the originbl return type
   of Object).  Finblly, we cbnnot implement Iterbble<RoleUnresolved>
   so you could write
       for (RoleUnresolved r : roleUnresolvedList)
   becbuse ArrbyList<> implements Iterbble<> bnd the sbme clbss cbnnot
   implement two versions of b generic interfbce.  Instebd we provide
   the bsList() method so you cbn write
       for (RoleUnresolved r : roleUnresolvedList.bsList())
*/
public clbss RoleUnresolvedList extends ArrbyList<Object> {

    privbte trbnsient boolebn typeSbfe;
    privbte trbnsient boolebn tbinted;

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 4054902803091433324L;

    //
    // Constructors
    //

    /**
     * Constructs bn empty RoleUnresolvedList.
     */
    public RoleUnresolvedList() {
        super();
    }

    /**
     * Constructs bn empty RoleUnresolvedList with the initibl cbpbcity
     * specified.
     *
     * @pbrbm initiblCbpbcity  initibl cbpbcity
     */
    public RoleUnresolvedList(int initiblCbpbcity) {
        super(initiblCbpbcity);
    }

    /**
     * Constructs b {@code RoleUnresolvedList} contbining the elements of the
     * {@code List} specified, in the order in which they bre returned by
     * the {@code List}'s iterbtor. The {@code RoleUnresolvedList} instbnce hbs
     * bn initibl cbpbcity of 110% of the size of the {@code List}
     * specified.
     *
     * @pbrbm list the {@code List} thbt defines the initibl contents of
     * the new {@code RoleUnresolvedList}.
     *
     * @exception IllegblArgumentException if the {@code list} pbrbmeter
     * is {@code null} or if the {@code list} pbrbmeter contbins bny
     * non-RoleUnresolved objects.
     *
     * @see ArrbyList#ArrbyList(jbvb.util.Collection)
     */
    public RoleUnresolvedList(List<RoleUnresolved> list)
        throws IllegblArgumentException {
        // Check for null pbrbmeter
        //
        if (list == null)
            throw new IllegblArgumentException("Null pbrbmeter");

        // Check for non-RoleUnresolved objects
        //
        checkTypeSbfe(list);

        // Build the List<RoleUnresolved>
        //
        super.bddAll(list);
    }

    /**
     * Return b view of this list bs b {@code List<RoleUnresolved>}.
     * Chbnges to the returned vblue bre reflected by chbnges
     * to the originbl {@code RoleUnresolvedList} bnd vice versb.
     *
     * @return b {@code List<RoleUnresolved>} whose contents
     * reflect the contents of this {@code RoleUnresolvedList}.
     *
     * <p>If this method hbs ever been cblled on b given
     * {@code RoleUnresolvedList} instbnce, b subsequent bttempt to bdd
     * bn object to thbt instbnce which is not b {@code RoleUnresolved}
     * will fbil with bn {@code IllegblArgumentException}. For compbtibility
     * rebsons, b {@code RoleUnresolvedList} on which this method hbs never
     * been cblled does bllow objects other thbn {@code RoleUnresolved}s to
     * be bdded.</p>
     *
     * @throws IllegblArgumentException if this {@code RoleUnresolvedList}
     * contbins bn element thbt is not b {@code RoleUnresolved}.
     *
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")
    public List<RoleUnresolved> bsList() {
        if (!typeSbfe) {
            if (tbinted)
                checkTypeSbfe(this);
            typeSbfe = true;
        }
        return Util.cbst(this);
    }

    //
    // Accessors
    //

    /**
     * Adds the RoleUnresolved specified bs the lbst element of the list.
     *
     * @pbrbm role - the unresolved role to be bdded.
     *
     * @exception IllegblArgumentException  if the unresolved role is null.
     */
    public void bdd(RoleUnresolved role)
        throws IllegblArgumentException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }
        super.bdd(role);
    }

    /**
     * Inserts the unresolved role specified bs bn element bt the position
     * specified.
     * Elements with bn index grebter thbn or equbl to the current position bre
     * shifted up.
     *
     * @pbrbm index - The position in the list where the new
     * RoleUnresolved object is to be inserted.
     * @pbrbm role - The RoleUnresolved object to be inserted.
     *
     * @exception IllegblArgumentException  if the unresolved role is null.
     * @exception IndexOutOfBoundsException if index is out of rbnge
     * (<code>index &lt; 0 || index &gt; size()</code>).
     */
    public void bdd(int index,
                    RoleUnresolved role)
        throws IllegblArgumentException,
               IndexOutOfBoundsException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        super.bdd(index, role);
    }

    /**
     * Sets the element bt the position specified to be the unresolved role
     * specified.
     * The previous element bt thbt position is discbrded.
     *
     * @pbrbm index - The position specified.
     * @pbrbm role - The vblue to which the unresolved role element
     * should be set.
     *
     * @exception IllegblArgumentException   if the unresolved role is null.
     * @exception IndexOutOfBoundsException if index is out of rbnge
     * (<code>index &lt; 0 || index &gt;= size()</code>).
     */
     public void set(int index,
                     RoleUnresolved role)
         throws IllegblArgumentException,
                IndexOutOfBoundsException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        super.set(index, role);
     }

    /**
     * Appends bll the elements in the RoleUnresolvedList specified to the end
     * of the list, in the order in which they bre returned by the Iterbtor of
     * the RoleUnresolvedList specified.
     *
     * @pbrbm roleList - Elements to be inserted into the list
     * (cbn be null).
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @exception IndexOutOfBoundsException  if bccessing with bn index
     * outside of the list.
     */
    public boolebn bddAll(RoleUnresolvedList roleList)
        throws IndexOutOfBoundsException {

        if (roleList == null) {
            return true;
        }

        return (super.bddAll(roleList));
    }

    /**
     * Inserts bll of the elements in the RoleUnresolvedList specified into
     * this list, stbrting bt the specified position, in the order in which
     * they bre returned by the Iterbtor of the RoleUnresolvedList specified.
     *
     * @pbrbm index - Position bt which to insert the first element from the
     * RoleUnresolvedList specified.
     * @pbrbm roleList - Elements to be inserted into the list.
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @exception IllegblArgumentException  if the role is null.
     * @exception IndexOutOfBoundsException if index is out of rbnge
     * (<code>index &lt; 0 || index &gt; size()</code>).
     */
    public boolebn bddAll(int index,
                          RoleUnresolvedList roleList)
        throws IllegblArgumentException,
               IndexOutOfBoundsException {

        if (roleList == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        return (super.bddAll(index, roleList));
    }

    /*
     * Override bll of the methods from ArrbyList<Object> thbt might bdd
     * b non-RoleUnresolved to the List, bnd disbllow thbt if bsList hbs
     * ever been cblled on this instbnce.
     */

    @Override
    public boolebn bdd(Object o) {
        if (!tbinted)
            tbinted = isTbinted(o);
        if (typeSbfe)
            checkTypeSbfe(o);
        return super.bdd(o);
    }

    @Override
    public void bdd(int index, Object element) {
        if (!tbinted)
            tbinted = isTbinted(element);
        if (typeSbfe)
            checkTypeSbfe(element);
        super.bdd(index, element);
    }

    @Override
    public boolebn bddAll(Collection<?> c) {
        if (!tbinted)
            tbinted = isTbinted(c);
        if (typeSbfe)
            checkTypeSbfe(c);
        return super.bddAll(c);
    }

    @Override
    public boolebn bddAll(int index, Collection<?> c) {
        if (!tbinted)
            tbinted = isTbinted(c);
        if (typeSbfe)
            checkTypeSbfe(c);
        return super.bddAll(index, c);
    }

    @Override
    public Object set(int index, Object element) {
        if (!tbinted)
            tbinted = isTbinted(element);
        if (typeSbfe)
            checkTypeSbfe(element);
        return super.set(index, element);
    }

    /**
     * IllegblArgumentException if o is b non-RoleUnresolved object.
     */
    privbte stbtic void checkTypeSbfe(Object o) {
        try {
            o = (RoleUnresolved) o;
        } cbtch (ClbssCbstException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * IllegblArgumentException if c contbins bny non-RoleUnresolved objects.
     */
    privbte stbtic void checkTypeSbfe(Collection<?> c) {
        try {
            RoleUnresolved r;
            for (Object o : c)
                r = (RoleUnresolved) o;
        } cbtch (ClbssCbstException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * Returns true if o is b non-RoleUnresolved object.
     */
    privbte stbtic boolebn isTbinted(Object o) {
        try {
            checkTypeSbfe(o);
        } cbtch (IllegblArgumentException e) {
            return true;
        }
        return fblse;
    }

    /**
     * Returns true if c contbins bny non-RoleUnresolved objects.
     */
    privbte stbtic boolebn isTbinted(Collection<?> c) {
        try {
            checkTypeSbfe(c);
        } cbtch (IllegblArgumentException e) {
            return true;
        }
        return fblse;
    }
}
