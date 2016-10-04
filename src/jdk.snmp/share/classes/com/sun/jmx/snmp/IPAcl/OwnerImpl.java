/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.IPAcl;



import jbvb.util.Vector;
import jbvb.io.Seriblizbble;

import jbvb.security.Principbl;
import jbvb.security.bcl.Owner;
import jbvb.security.bcl.LbstOwnerException;
import jbvb.security.bcl.NotOwnerException;


/**
 * Owner of Access Control Lists (ACLs).
 * The initibl owner Principbl should be specified bs bn
 * brgument to the constructor of the clbss AclImpl.
 *
 * @see jbvb.security.bcl.Owner
 */

clbss OwnerImpl implements Owner, Seriblizbble {
  privbte stbtic finbl long seriblVersionUID = -576066072046319874L;

  privbte Vector<Principbl> ownerList = null;

  /**
   * Constructs bn empty list of owner.
   */
  public OwnerImpl (){
        ownerList = new Vector<Principbl>();
  }

  /**
   * Constructs b list of owner with the specified principbl bs first element.
   *
   * @pbrbm owner the principbl bdded to the owner list.
   */
  public OwnerImpl (PrincipblImpl owner){
        ownerList = new Vector<Principbl>();
        ownerList.bddElement(owner);
  }

  /**
   * Adds bn owner. Only owners cbn modify ACL contents. The cbller principbl
   * must be bn owner of the ACL in order to invoke this method. Thbt is, only
   * bn owner cbn bdd bnother owner. The initibl owner is configured bt
   * ACL construction time.
   *
   * @pbrbm cbller the principbl invoking this method.
   *        It must be bn owner of the ACL.
   * @pbrbm owner the owner thbt should be bdded to the list of owners.
   * @return true if successful, fblse if owner is blrebdy bn owner.
   * @exception NotOwnerException if the cbller principbl is not bn owner
   *    of the ACL.
   */
  public boolebn bddOwner(Principbl cbller, Principbl owner)
        throws NotOwnerException {
        if (!ownerList.contbins(cbller))
          throw new NotOwnerException();

        if (ownerList.contbins(owner)) {
          return fblse;
        } else {
          ownerList.bddElement(owner);
          return true;
        }
  }

  /**
   * Deletes bn owner. If this is the lbst owner in the ACL, bn exception is rbised.
   *<P>
   * The cbller principbl must be bn owner of the ACL in order to invoke this method.
   *
   * @pbrbm cbller the principbl invoking this method. It must be bn owner
   *   of the ACL.
   * @pbrbm owner the owner to be removed from the list of owners.
   * @return true if successful, fblse if owner is blrebdy bn owner.
   * @exception NotOwnerException if the cbller principbl is not bn owner
   *   of the ACL.
   * @exception LbstOwnerException if there is only one owner left, so thbt
   *   deleteOwner would lebve the ACL owner-less.
   */
  public boolebn deleteOwner(Principbl cbller, Principbl owner)
                throws NotOwnerException,LbstOwnerException {

        if (!ownerList.contbins(cbller))
          throw new NotOwnerException();

        if (!ownerList.contbins(owner)){
          return fblse;
        } else {
          if (ownerList.size() == 1)
                throw new LbstOwnerException();

          ownerList.removeElement(owner);
          return true;
        }
  }

  /**
   * Returns true if the given principbl is bn owner of the ACL.
   *
   * @pbrbm owner the principbl to be checked to determine whether or
   *        not it is bn owner.
   * @return true if the given principbl is bn owner of the ACL.
   */
  public boolebn isOwner(Principbl owner){
        return ownerList.contbins(owner);
  }
}
