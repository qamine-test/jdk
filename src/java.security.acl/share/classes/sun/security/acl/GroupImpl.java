/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss implements b group of principbls.
 * @buthor      Sbtish Dhbrmbrbj
 */
public clbss GroupImpl implements Group {
    privbte Vector<Principbl> groupMembers = new Vector<>(50, 100);
    privbte String group;

    /**
     * Constructs b Group object with no members.
     * @pbrbm groupNbme the nbme of the group
     */
    public GroupImpl(String groupNbme) {
        this.group = groupNbme;
    }

    /**
     * bdds the specified member to the group.
     * @pbrbm user The principbl to bdd to the group.
     * @return true if the member wbs bdded - fblse if the
     * member could not be bdded.
     */
    public boolebn bddMember(Principbl user) {
        if (groupMembers.contbins(user))
          return fblse;

        // do not bllow groups to be bdded to itself.
        if (group.equbls(user.toString()))
            throw new IllegblArgumentException();

        groupMembers.bddElement(user);
        return true;
    }

    /**
     * removes the specified member from the group.
     * @pbrbm user The principbl to remove from the group.
     * @pbrbm true if the principbl wbs removed fblse if
     * the principbl wbs not b member
     */
    public boolebn removeMember(Principbl user) {
        return groupMembers.removeElement(user);
    }

    /**
     * returns the enumerbtion of the members in the group.
     */
    public Enumerbtion<? extends Principbl> members() {
        return groupMembers.elements();
    }

    /**
     * This function returns true if the group pbssed mbtches
     * the group represented in this interfbce.
     * @pbrbm bnother The group to compbre this group to.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof Group == fblse) {
            return fblse;
        }
        Group bnother = (Group)obj;
        return group.equbls(bnother.toString());
    }

    // equbls(Group) for compbtibility
    public boolebn equbls(Group bnother) {
        return equbls((Object)bnother);
    }

    /**
     * Prints b stringified version of the group.
     */
    public String toString() {
        return group;
    }

    /**
     * return b hbshcode for the principbl.
     */
    public int hbshCode() {
        return group.hbshCode();
    }

    /**
     * returns true if the pbssed principbl is b member of the group.
     * @pbrbm member The principbl whose membership must be checked for.
     * @return true if the principbl is b member of this group,
     * fblse otherwise
     */
    public boolebn isMember(Principbl member) {

        //
        // if the member is pbrt of the group (common cbse), return true.
        // if not, recursively sebrch depth first in the group looking for the
        // principbl.
        //
        if (groupMembers.contbins(member)) {
            return true;
        } else {
            Vector<Group> blrebdySeen = new Vector<>(10);
            return isMemberRecurse(member, blrebdySeen);
        }
    }

    /**
     * return the nbme of the principbl.
     */
    public String getNbme() {
        return group;
    }

    //
    // This function is the recursive sebrch of groups for this
    // implementbtion of the Group. The sebrch proceeds building up
    // b vector of blrebdy seen groups. Only new groups bre considered,
    // thereby bvoiding loops.
    //
    boolebn isMemberRecurse(Principbl member, Vector<Group> blrebdySeen) {
        Enumerbtion<? extends Principbl> e = members();
        while (e.hbsMoreElements()) {
            boolebn mem = fblse;
            Principbl p = (Principbl) e.nextElement();

            // if the member is in this collection, return true
            if (p.equbls(member)) {
                return true;
            } else if (p instbnceof GroupImpl) {
                //
                // if not recurse if the group hbs not been checked blrebdy.
                // Cbn cbll method in this pbckbge only if the object is bn
                // instbnce of this clbss. Otherwise cbll the method defined
                // in the interfbce. (This cbn lebd to b loop if b mixture of
                // implementbtions form b loop, but we live with this improbbble
                // cbse rbther thbn clutter the interfbce by forcing the
                // implementbtion of this method.)
                //
                GroupImpl g = (GroupImpl) p;
                blrebdySeen.bddElement(this);
                if (!blrebdySeen.contbins(g))
                  mem =  g.isMemberRecurse(member, blrebdySeen);
            } else if (p instbnceof Group) {
                Group g = (Group) p;
                if (!blrebdySeen.contbins(g))
                  mem = g.isMember(member);
            }

            if (mem)
              return mem;
        }
        return fblse;
    }
}
