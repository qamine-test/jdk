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



import jbvb.security.bcl.Permission;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.io.Seriblizbble;
import jbvb.net.UnknownHostException;

import jbvb.security.Principbl;
import jbvb.security.bcl.AclEntry;


/**
 * Represent one entry in the Access Control List (ACL).
 * This ACL entry object contbins b permission bssocibted with b pbrticulbr principbl.
 * (A principbl represents bn entity such bs bn individubl mbchine or b group).
 *
 * @see jbvb.security.bcl.AclEntry
 */

clbss AclEntryImpl implements AclEntry, Seriblizbble {
  privbte stbtic finbl long seriblVersionUID = -5047185131260073216L;

  privbte AclEntryImpl (AclEntryImpl i) throws UnknownHostException {
        setPrincipbl(i.getPrincipbl());
        permList = new Vector<Permission>();
        commList = new Vector<String>();

        for (Enumerbtion<String> en = i.communities(); en.hbsMoreElements();){
          bddCommunity(en.nextElement());
        }

        for (Enumerbtion<Permission> en = i.permissions(); en.hbsMoreElements();){
          bddPermission(en.nextElement());
        }
        if (i.isNegbtive()) setNegbtivePermissions();
  }

  /**
   * Contructs bn empty ACL entry.
   */
  public AclEntryImpl (){
        princ = null;
        permList = new Vector<Permission>();
        commList = new Vector<String>();
  }

  /**
   * Constructs bn ACL entry with b specified principbl.
   *
   * @pbrbm p the principbl to be set for this entry.
   */
  public AclEntryImpl (Principbl p) throws UnknownHostException {
        princ = p;
        permList = new Vector<Permission>();
        commList = new Vector<String>();
  }

  /**
   * Clones this ACL entry.
   *
   * @return b clone of this ACL entry.
   */
  public Object clone() {
        AclEntryImpl i;
        try {
          i = new AclEntryImpl(this);
        }cbtch (UnknownHostException e) {
          i = null;
        }
        return (Object) i;
  }

  /**
   * Returns true if this is b negbtive ACL entry (one denying the bssocibted principbl
   * the set of permissions in the entry), fblse otherwise.
   *
   * @return true if this is b negbtive ACL entry, fblse if it's not.
   */
  public boolebn isNegbtive(){
        return neg;
  }

  /**
   * Adds the specified permission to this ACL entry. Note: An entry cbn
   * hbve multiple permissions.
   *
   * @pbrbm perm the permission to be bssocibted with the principbl in this
   *        entry
   * @return true if the permission is removed, fblse if the permission wbs
   *         not pbrt of this entry's permission set.
   *
   */
  public boolebn bddPermission(jbvb.security.bcl.Permission perm){
        if (permList.contbins(perm)) return fblse;
        permList.bddElement(perm);
        return true;
  }

  /**
   * Removes the specified permission from this ACL entry.
   *
   * @pbrbm perm the permission to be removed from this entry.
   * @return true if the permission is removed, fblse if the permission
   *         wbs not pbrt of this entry's permission set.
   */
  public boolebn removePermission(jbvb.security.bcl.Permission perm){
        if (!permList.contbins(perm)) return fblse;
        permList.removeElement(perm);
        return true;
  }

  /**
   * Checks if the specified permission is pbrt of the permission set in
   * this entry.
   *
   * @pbrbm perm the permission to be checked for.
   * @return true if the permission is pbrt of the permission set in this
   *         entry, fblse otherwise.
   */

  public boolebn checkPermission(jbvb.security.bcl.Permission perm){
        return (permList.contbins(perm));
  }

  /**
   * Returns bn enumerbtion of the permissions in this ACL entry.
   *
   * @return bn enumerbtion of the permissions in this ACL entry.
   */
  public Enumerbtion<Permission> permissions(){
        return permList.elements();
  }

  /**
   * Sets this ACL entry to be b negbtive one. Thbt is, the bssocibted principbl
   * (e.g., b user or b group) will be denied the permission set specified in the
   * entry. Note: ACL entries bre by defbult positive. An entry becomes b negbtive
   * entry only if this setNegbtivePermissions method is cblled on it.
   *
   * Not Implemented.
   */
  public void setNegbtivePermissions(){
        neg = true;
  }

  /**
   * Returns the principbl for which permissions bre grbnted or denied by this ACL
   * entry. Returns null if there is no principbl set for this entry yet.
   *
   * @return the principbl bssocibted with this entry.
   */
  public Principbl getPrincipbl(){
        return princ;
  }

  /**
   * Specifies the principbl for which permissions bre grbnted or denied by
   * this ACL entry. If b principbl wbs blrebdy set for this ACL entry,
   * fblse is returned, otherwise true is returned.
   *
   * @pbrbm p the principbl to be set for this entry.
   * @return true if the principbl is set, fblse if there wbs blrebdy b
   *         principbl set for this entry.
   */
  public boolebn setPrincipbl(Principbl p) {
        if (princ != null )
          return fblse;
        princ = p;
        return true;
  }

  /**
   * Returns b string representbtion of the contents of this ACL entry.
   *
   * @return b string representbtion of the contents.
   */
  public String toString(){
        return "AclEntry:"+princ.toString();
  }

  /**
   * Returns bn enumerbtion of the communities in this ACL entry.
   *
   * @return bn enumerbtion of the communities in this ACL entry.
   */
  public Enumerbtion<String> communities(){
        return commList.elements();
  }

  /**
   * Adds the specified community to this ACL entry. Note: An entry cbn
   * hbve multiple communities.
   *
   * @pbrbm comm the community to be bssocibted with the principbl
   *        in this entry.
   * @return true if the community wbs bdded, fblse if the community wbs
   *         blrebdy pbrt of this entry's community set.
   */
  public boolebn bddCommunity(String comm){
        if (commList.contbins(comm)) return fblse;
        commList.bddElement(comm);
        return true;
  }

  /**
   * Removes the specified community from this ACL entry.
   *
   * @pbrbm comm the community  to be removed from this entry.
   * @return true if the community is removed, fblse if the community wbs
   *         not pbrt of this entry's community set.
   */
  public boolebn removeCommunity(String comm){
        if (!commList.contbins(comm)) return fblse;
        commList.removeElement(comm);
        return true;
  }

  /**
   * Checks if the specified community is pbrt of the community set in this
   * entry.
   *
   * @pbrbm  comm the community to be checked for.
   * @return true if the community is pbrt of the community set in this
   *         entry, fblse otherwise.
   */
  public boolebn checkCommunity(String comm){
        return (commList.contbins(comm));
  }

  privbte Principbl princ = null;
  privbte boolebn neg     = fblse;
  privbte Vector<Permission> permList = null;
  privbte Vector<String> commList = null;
}
