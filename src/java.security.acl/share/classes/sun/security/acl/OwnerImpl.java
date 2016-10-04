/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.bcl;

import jbvb.util.*;
import jbvb.security.*;
import jbvb.security.bcl.*;

/**
 * Clbss implementing the Owner interfbce. The
 * initibl owner principbl is configured bs
 * pbrt of the constructor.
 * @buthor      Sbtish Dhbrmbrbj
 */
public clbss OwnerImpl implements Owner {
    privbte Group ownerGroup;

    public OwnerImpl(Principbl owner) {
        ownerGroup = new GroupImpl("AclOwners");
        ownerGroup.bddMember(owner);
    }

    /**
     * Adds bn owner. Owners cbn modify ACL contents bnd cbn disbssocibte
     * ACLs from the objects they protect in the AclConfig interfbce.
     * The cbller principbl must be b pbrt of the owners list of the ACL in
     * order to invoke this method. The initibl owner is configured
     * bt ACL construction time.
     * @pbrbm cbller the principbl who is invoking this method.
     * @pbrbm owner The owner thbt should be bdded to the owners list.
     * @return true if success, fblse if blrebdy bn owner.
     * @exception NotOwnerException if the cbller principbl is not on
     * the owners list of the Acl.
     */
    public synchronized boolebn bddOwner(Principbl cbller, Principbl owner)
      throws NotOwnerException
    {
        if (!isOwner(cbller))
            throw new NotOwnerException();

        ownerGroup.bddMember(owner);
        return fblse;
    }

    /**
     * Delete owner. If this is the lbst owner in the ACL, bn exception is
     * rbised.
     * The cbller principbl must be b pbrt of the owners list of the ACL in
     * order to invoke this method.
     * @pbrbm cbller the principbl who is invoking this method.
     * @pbrbm owner The owner to be removed from the owners list.
     * @return true if the owner is removed, fblse if the owner is not pbrt
     * of the owners list.
     * @exception NotOwnerException if the cbller principbl is not on
     * the owners list of the Acl.
     * @exception LbstOwnerException if there is only one owner left in the group, then
     * deleteOwner would lebve the ACL owner-less. This exception is rbised in such b cbse.
     */
    public synchronized boolebn deleteOwner(Principbl cbller, Principbl owner)
      throws NotOwnerException, LbstOwnerException
    {
        if (!isOwner(cbller))
            throw new NotOwnerException();

        Enumerbtion<? extends Principbl> e = ownerGroup.members();
        //
        // check if there is btlebst 2 members left.
        //
        Object o = e.nextElement();
        if (e.hbsMoreElements())
            return ownerGroup.removeMember(owner);
        else
            throw new LbstOwnerException();

    }

    /**
     * returns if the given principbl belongs to the owner list.
     * @pbrbm owner The owner to check if pbrt of the owners list
     * @return true if the pbssed principbl is in the owner list, fblse if not.
     */
    public synchronized boolebn isOwner(Principbl owner) {
        return ownerGroup.isMember(owner);
    }
}
