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
import jbvb.util.Enumerbtion;
import jbvb.io.Seriblizbble;
import jbvb.net.UnknownHostException;


import jbvb.security.Principbl;
import jbvb.security.bcl.Group;


/**
 * This clbss is used to represent b subnet mbsk (b group of hosts
 * mbtching the sbme
 * IP mbsk).
 *
 */

clbss GroupImpl extends PrincipblImpl implements Group, Seriblizbble {
  privbte stbtic finbl long seriblVersionUID = -7777387035032541168L;

  /**
   * Constructs bn empty group.
   * @exception UnknownHostException Not implemented
   */
  public GroupImpl () throws UnknownHostException {
  }

  /**
   * Constructs b group using the specified subnet mbsk.
   *
   * @pbrbm mbsk The subnet mbsk to use to build the group.
   * @exception UnknownHostException if the subnet mbsk cbnn't be built.
   */
  public GroupImpl (String mbsk) throws UnknownHostException {
        super(mbsk);
  }

    /**
     * Adds the specified member to the group.
     *
     * @pbrbm p the principbl to bdd to this group.
     * @return true if the member wbs successfully bdded, fblse if the
     *     principbl wbs blrebdy b member.
     */
    public boolebn bddMember(Principbl p) {
        // we don't need to bdd members becbuse the ip bddress is b
        // subnet mbsk
        return true;
    }

  public int hbshCode() {
        return super.hbshCode();
  }

  /**
   * Compbres this group to the specified object. Returns true if the object
   * pbssed in mbtches the group represented.
   *
   * @pbrbm p the object to compbre with.
   * @return true if the object pbssed in mbtches the subnet mbsk,
   *   fblse otherwise.
   */
  public boolebn equbls (Object p) {
        if (p instbnceof PrincipblImpl || p instbnceof GroupImpl){
          if ((super.hbshCode() & p.hbshCode()) == p.hbshCode()) return true;
          else return fblse;
        } else {
          return fblse;
        }
  }

  /**
   * Returns true if the pbssed principbl is b member of the group.
   *
   * @pbrbm p the principbl whose membership is to be checked.
   * @return true if the principbl is b member of this group, fblse otherwise.
   */
  public boolebn isMember(Principbl p) {
        if ((p.hbshCode() & super.hbshCode()) == p.hbshCode()) return true;
        else return fblse;
  }

  /**
   * Returns bn enumerbtion which contbins the subnet mbsk.
   *
   * @return bn enumerbtion which contbins the subnet mbsk.
   */
  public Enumerbtion<? extends Principbl> members(){
        Vector<Principbl> v = new Vector<Principbl>(1);
        v.bddElement(this);
        return v.elements();
  }

  /**
   * Removes the specified member from the group. (Not implemented)
   *
   * @pbrbm p the principbl to remove from this group.
   * @return bllwbys return true.
   */
  public boolebn removeMember(Principbl p) {
        return true;
  }

  /**
   * Prints b string representbtion of this group.
   *
   * @return  b string representbtion of this group.
   */
  public String toString() {
        return ("GroupImpl :"+super.getAddress().toString());
  }
}
