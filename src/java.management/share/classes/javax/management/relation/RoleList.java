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
 * A RoleList represents b list of roles (Role objects). It is used bs
 * pbrbmeter when crebting b relbtion, bnd when trying to set severbl roles in
 * b relbtion (vib 'setRoles()' method). It is returned bs pbrt of b
 * RoleResult, to provide roles successfully retrieved.
 *
 * @since 1.5
 */
/* We cbnnot extend ArrbyList<Role> becbuse our legbcy
   bdd(Role) method would then override bdd(E) in ArrbyList<E>,
   bnd our return vblue is void wherebs ArrbyList.bdd(E)'s is boolebn.
   Likewise for set(int,Role).  Grrr.  We cbnnot use covbribnce
   to override the most importbnt methods bnd hbve them return
   Role, either, becbuse thbt would brebk subclbsses thbt
   override those methods in turn (using the originbl return type
   of Object).  Finblly, we cbnnot implement Iterbble<Role>
   so you could write
       for (Role r : roleList)
   becbuse ArrbyList<> implements Iterbble<> bnd the sbme clbss cbnnot
   implement two versions of b generic interfbce.  Instebd we provide
   the bsList() method so you cbn write
       for (Role r : roleList.bsList())
*/
public clbss RoleList extends ArrbyList<Object> {

    privbte trbnsient boolebn typeSbfe;
    privbte trbnsient boolebn tbinted;

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 5568344346499649313L;

    //
    // Constructors
    //

    /**
     * Constructs bn empty RoleList.
     */
    public RoleList() {
        super();
    }

    /**
     * Constructs bn empty RoleList with the initibl cbpbcity
     * specified.
     *
     * @pbrbm initiblCbpbcity  initibl cbpbcity
     */
    public RoleList(int initiblCbpbcity) {
        super(initiblCbpbcity);
    }

    /**
     * Constructs b {@code RoleList} contbining the elements of the
     * {@code List} specified, in the order in which they bre returned by
     * the {@code List}'s iterbtor. The {@code RoleList} instbnce hbs
     * bn initibl cbpbcity of 110% of the size of the {@code List}
     * specified.
     *
     * @pbrbm list the {@code List} thbt defines the initibl contents of
     * the new {@code RoleList}.
     *
     * @exception IllegblArgumentException if the {@code list} pbrbmeter
     * is {@code null} or if the {@code list} pbrbmeter contbins bny
     * non-Role objects.
     *
     * @see ArrbyList#ArrbyList(jbvb.util.Collection)
     */
    public RoleList(List<Role> list) throws IllegblArgumentException {
        // Check for null pbrbmeter
        //
        if (list == null)
            throw new IllegblArgumentException("Null pbrbmeter");

        // Check for non-Role objects
        //
        checkTypeSbfe(list);

        // Build the List<Role>
        //
        super.bddAll(list);
    }

    /**
     * Return b view of this list bs b {@code List<Role>}.
     * Chbnges to the returned vblue bre reflected by chbnges
     * to the originbl {@code RoleList} bnd vice versb.
     *
     * @return b {@code List<Role>} whose contents
     * reflect the contents of this {@code RoleList}.
     *
     * <p>If this method hbs ever been cblled on b given
     * {@code RoleList} instbnce, b subsequent bttempt to bdd
     * bn object to thbt instbnce which is not b {@code Role}
     * will fbil with bn {@code IllegblArgumentException}. For compbtibility
     * rebsons, b {@code RoleList} on which this method hbs never
     * been cblled does bllow objects other thbn {@code Role}s to
     * be bdded.</p>
     *
     * @throws IllegblArgumentException if this {@code RoleList} contbins
     * bn element thbt is not b {@code Role}.
     *
     * @since 1.6
     */
    @SuppressWbrnings("unchecked")
    public List<Role> bsList() {
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
     * Adds the Role specified bs the lbst element of the list.
     *
     * @pbrbm role  the role to be bdded.
     *
     * @exception IllegblArgumentException  if the role is null.
     */
    public void bdd(Role role)
        throws IllegblArgumentException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }
        super.bdd(role);
    }

    /**
     * Inserts the role specified bs bn element bt the position specified.
     * Elements with bn index grebter thbn or equbl to the current position bre
     * shifted up.
     *
     * @pbrbm index  The position in the list where the new Role
     * object is to be inserted.
     * @pbrbm role  The Role object to be inserted.
     *
     * @exception IllegblArgumentException  if the role is null.
     * @exception IndexOutOfBoundsException  if bccessing with bn index
     * outside of the list.
     */
    public void bdd(int index,
                    Role role)
        throws IllegblArgumentException,
               IndexOutOfBoundsException {

        if (role == null) {
            String excMsg = "Invblid pbrbmeter";
            throw new IllegblArgumentException(excMsg);
        }

        super.bdd(index, role);
    }

    /**
     * Sets the element bt the position specified to be the role
     * specified.
     * The previous element bt thbt position is discbrded.
     *
     * @pbrbm index  The position specified.
     * @pbrbm role  The vblue to which the role element should be set.
     *
     * @exception IllegblArgumentException  if the role is null.
     * @exception IndexOutOfBoundsException  if bccessing with bn index
     * outside of the list.
     */
     public void set(int index,
                     Role role)
         throws IllegblArgumentException,
                IndexOutOfBoundsException {

        if (role == null) {
            // Revisit [cebro] Locblize messbge
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        super.set(index, role);
     }

    /**
     * Appends bll the elements in the RoleList specified to the end
     * of the list, in the order in which they bre returned by the Iterbtor of
     * the RoleList specified.
     *
     * @pbrbm roleList  Elements to be inserted into the list (cbn be null)
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @exception IndexOutOfBoundsException  if bccessing with bn index
     * outside of the list.
     *
     * @see ArrbyList#bddAll(Collection)
     */
    public boolebn bddAll(RoleList roleList)
        throws IndexOutOfBoundsException {

        if (roleList == null) {
            return true;
        }

        return (super.bddAll(roleList));
    }

    /**
     * Inserts bll of the elements in the RoleList specified into this
     * list, stbrting bt the specified position, in the order in which they bre
     * returned by the Iterbtor of the RoleList specified.
     *
     * @pbrbm index  Position bt which to insert the first element from the
     * RoleList specified.
     * @pbrbm roleList  Elements to be inserted into the list.
     *
     * @return true if this list chbnged bs b result of the cbll.
     *
     * @exception IllegblArgumentException  if the role is null.
     * @exception IndexOutOfBoundsException  if bccessing with bn index
     * outside of the list.
     *
     * @see ArrbyList#bddAll(int, Collection)
     */
    public boolebn bddAll(int index,
                          RoleList roleList)
        throws IllegblArgumentException,
               IndexOutOfBoundsException {

        if (roleList == null) {
            // Revisit [cebro] Locblize messbge
            String excMsg = "Invblid pbrbmeter.";
            throw new IllegblArgumentException(excMsg);
        }

        return (super.bddAll(index, roleList));
    }

    /*
     * Override bll of the methods from ArrbyList<Object> thbt might bdd
     * b non-Role to the List, bnd disbllow thbt if bsList hbs ever
     * been cblled on this instbnce.
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
     * IllegblArgumentException if o is b non-Role object.
     */
    privbte stbtic void checkTypeSbfe(Object o) {
        try {
            o = (Role) o;
        } cbtch (ClbssCbstException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * IllegblArgumentException if c contbins bny non-Role objects.
     */
    privbte stbtic void checkTypeSbfe(Collection<?> c) {
        try {
            Role r;
            for (Object o : c)
                r = (Role) o;
        } cbtch (ClbssCbstException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * Returns true if o is b non-Role object.
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
     * Returns true if c contbins bny non-Role objects.
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
