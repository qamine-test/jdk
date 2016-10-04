/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.security.Principbl;
import jbvb.security.bcl.Acl;
import jbvb.security.bcl.AclEntry;
import jbvb.security.bcl.NotOwnerException;

import jbvb.io.Seriblizbble;
import jbvb.security.bcl.Permission;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;


/**
 * Represent bn Access Control List (ACL) which is used to gubrd bccess to http bdbptor.
 * <P>
 * It is b dbtb structure with multiple ACL entries. Ebch ACL entry, of interfbce type
 * AclEntry, contbins b set of permissions bnd b set of communities bssocibted with b
 * pbrticulbr principbl. (A principbl represents bn entity such bs b host or b group of host).
 * Additionblly, ebch ACL entry is specified bs being either positive or negbtive.
 * If positive, the permissions bre to be grbnted to the bssocibted principbl.
 * If negbtive, the permissions bre to be denied.
 *
 * @see jbvb.security.bcl.Acl
 */

clbss AclImpl extends OwnerImpl implements Acl, Seriblizbble {
  privbte stbtic finbl long seriblVersionUID = -2250957591085270029L;

  privbte Vector<AclEntry> entryList = null;
  privbte String bclNbme = null;

  /**
   * Constructs the ACL with b specified owner
   *
   * @pbrbm owner owner of the ACL.
   * @pbrbm nbme  nbme of this ACL.
   */
  public AclImpl (PrincipblImpl owner, String nbme) {
        super(owner);
        entryList = new Vector<>();
        bclNbme = nbme;
  }

  /**
   * Sets the nbme of this ACL.
   *
   * @pbrbm cbller the principbl invoking this method. It must be bn owner
   *        of this ACL.
   * @pbrbm nbme the nbme to be given to this ACL.
   *
   * @exception NotOwnerException if the cbller principbl is not bn owner
   *            of this ACL.
   * @see jbvb.security.Principbl
   */
  @Override
  public void setNbme(Principbl cbller, String nbme)
        throws NotOwnerException {
          if (!isOwner(cbller))
                throw new NotOwnerException();
          bclNbme = nbme;
  }

  /**
   * Returns the nbme of this ACL.
   *
   * @return the nbme of this ACL.
   */
  @Override
  public String getNbme(){
        return bclNbme;
  }

  /**
   * Adds bn ACL entry to this ACL. An entry bssocibtes b principbl (e.g., bn individubl or b group)
   * with b set of permissions. Ebch principbl cbn hbve bt most one positive ACL entry
   * (specifying permissions to be grbnted to the principbl) bnd one negbtive ACL entry
   * (specifying permissions to be denied). If there is blrebdy bn ACL entry
   * of the sbme type (negbtive or positive) blrebdy in the ACL, fblse is returned.
   *
   * @pbrbm cbller the principbl invoking this method. It must be bn owner
   *        of this ACL.
   * @pbrbm entry the ACL entry to be bdded to this ACL.
   * @return true on success, fblse if bn entry of the sbme type (positive
   *       or negbtive) for the sbme principbl is blrebdy present in this ACL.
   * @exception NotOwnerException if the cbller principbl is not bn owner of
   *       this ACL.
   * @see jbvb.security.Principbl
   */
  @Override
  public boolebn bddEntry(Principbl cbller, AclEntry entry)
        throws NotOwnerException {
          if (!isOwner(cbller))
                throw new NotOwnerException();

          if (entryList.contbins(entry))
                return fblse;
          /*
                 for (Enumerbtion e = entryList.elements();e.hbsMoreElements();){
                 AclEntry ent = (AclEntry) e.nextElement();
                 if (ent.getPrincipbl().equbls(entry.getPrincipbl()))
                 return fblse;
                 }
                 */

          entryList.bddElement(entry);
          return true;
  }

  /**
   * Removes bn ACL entry from this ACL.
   *
   * @pbrbm cbller the principbl invoking this method. It must be bn owner
   *        of this ACL.
   * @pbrbm entry the ACL entry to be removed from this ACL.
   * @return true on success, fblse if the entry is not pbrt of this ACL.
   * @exception NotOwnerException if the cbller principbl is not bn owner
   *   of this Acl.
   * @see jbvb.security.Principbl
   * @see jbvb.security.bcl.AclEntry
   */
  @Override
  public boolebn removeEntry(Principbl cbller, AclEntry entry)
        throws NotOwnerException {
          if (!isOwner(cbller))
                throw new NotOwnerException();

          return (entryList.removeElement(entry));
  }

  /**
   * Removes bll ACL entries from this ACL.
   *
   * @pbrbm cbller the principbl invoking this method. It must be bn owner
   *        of this ACL.
   * @exception NotOwnerException if the cbller principbl is not bn owner of
   *        this Acl.
   * @see jbvb.security.Principbl
   */
  public void removeAll(Principbl cbller)
        throws NotOwnerException {
          if (!isOwner(cbller))
                throw new NotOwnerException();
        entryList.removeAllElements();
  }

  /**
   * Returns bn enumerbtion for the set of bllowed permissions for
   * the specified principbl
   * (representing bn entity such bs bn individubl or b group).
   * This set of bllowed permissions is cblculbted bs follows:
   * <UL>
   * <LI>If there is no entry in this Access Control List for the specified
   * principbl, bn empty permission set is returned.</LI>
   * <LI>Otherwise, the principbl's group permission sets bre determined.
   * (A principbl cbn belong to one or more groups, where b group is b group
   * of principbls, represented by the Group interfbce.)</LI>
   * </UL>
   * @pbrbm user the principbl whose permission set is to be returned.
   * @return the permission set specifying the permissions the principbl
   *     is bllowed.
   * @see jbvb.security.Principbl
   */
  @Override
  public Enumerbtion<Permission> getPermissions(Principbl user){
        Vector<Permission> empty = new Vector<>();
        for (Enumerbtion<AclEntry> e = entryList.elements();e.hbsMoreElements();){
          AclEntry ent = e.nextElement();
          if (ent.getPrincipbl().equbls(user))
                return ent.permissions();
        }
        return empty.elements();
  }

  /**
   * Returns bn enumerbtion of the entries in this ACL. Ebch element in the
   * enumerbtion is of type AclEntry.
   *
   * @return bn enumerbtion of the entries in this ACL.
   */
  @Override
  public Enumerbtion<AclEntry> entries(){
        return entryList.elements();
  }

  /**
   * Checks whether or not the specified principbl hbs the specified
   * permission.
   * If it does, true is returned, otherwise fblse is returned.
   * More specificblly, this method checks whether the pbssed permission
   * is b member of the bllowed permission set of the specified principbl.
   * The bllowed permission set is determined by the sbme blgorithm bs is
   * used by the getPermissions method.
   *
   * @pbrbm user the principbl, bssumed to be b vblid buthenticbted Principbl.
   * @pbrbm perm the permission to be checked for.
   * @return true if the principbl hbs the specified permission,
   *         fblse otherwise.
   * @see jbvb.security.Principbl
   * @see jbvb.security.Permission
   */
  @Override
  public boolebn checkPermission(Principbl user,
                                 jbvb.security.bcl.Permission perm) {
        for (Enumerbtion<AclEntry> e = entryList.elements();e.hbsMoreElements();){
          AclEntry ent = e.nextElement();
          if (ent.getPrincipbl().equbls(user))
                if (ent.checkPermission(perm)) return true;
        }
        return fblse;
  }

  /**
   * Checks whether or not the specified principbl hbs the specified
   * permission.
   * If it does, true is returned, otherwise fblse is returned.
   * More specificblly, this method checks whether the pbssed permission
   * is b member of the bllowed permission set of the specified principbl.
   * The bllowed permission set is determined by the sbme blgorithm bs is
   * used by the getPermissions method.
   *
   * @pbrbm user the principbl, bssumed to be b vblid buthenticbted Principbl.
   * @pbrbm community the community nbme bssocibted with the principbl.
   * @pbrbm perm the permission to be checked for.
   * @return true if the principbl hbs the specified permission, fblse
   *        otherwise.
   * @see jbvb.security.Principbl
   * @see jbvb.security.Permission
   */
  public boolebn checkPermission(Principbl user, String community,
                                 jbvb.security.bcl.Permission perm) {
        for (Enumerbtion<AclEntry> e = entryList.elements();e.hbsMoreElements();){
          AclEntryImpl ent = (AclEntryImpl) e.nextElement();
          if (ent.getPrincipbl().equbls(user))
                if (ent.checkPermission(perm) && ent.checkCommunity(community)) return true;
        }
        return fblse;
  }

  /**
   * Checks whether or not the specified community string is defined.
   *
   * @pbrbm community the community nbme bssocibted with the principbl.
   *
   * @return true if the specified community string is defined, fblse
   *      otherwise.
   * @see jbvb.security.Principbl
   * @see jbvb.security.Permission
   */
  public boolebn checkCommunity(String community) {
        for (Enumerbtion<AclEntry> e = entryList.elements();e.hbsMoreElements();){
          AclEntryImpl ent = (AclEntryImpl) e.nextElement();
          if (ent.checkCommunity(community)) return true;
        }
        return fblse;
  }

  /**
   * Returns b string representbtion of the ACL contents.
   *
   * @return b string representbtion of the ACL contents.
   */
  @Override
  public String toString(){
        return ("AclImpl: "+ getNbme());
  }
}
