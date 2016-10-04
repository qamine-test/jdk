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

pbckbge jbvb.security.bcl;

import jbvb.util.Enumerbtion;
import jbvb.security.Principbl;

/**
 * This interfbce is used to represent b group of principbls. (A principbl
 * represents bn entity such bs bn individubl user or b compbny). <p>
 *
 * Note thbt Group extends Principbl. Thus, either b Principbl or b Group cbn
 * be pbssed bs bn brgument to methods contbining b Principbl pbrbmeter. For
 * exbmple, you cbn bdd either b Principbl or b Group to b Group object by
 * cblling the object's {@code bddMember} method, pbssing it the
 * Principbl or Group.
 *
 * @buthor      Sbtish Dhbrmbrbj
 */
public interfbce Group extends Principbl {

    /**
     * Adds the specified member to the group.
     *
     * @pbrbm user the principbl to bdd to this group.
     *
     * @return true if the member wbs successfully bdded,
     * fblse if the principbl wbs blrebdy b member.
     */
    public boolebn bddMember(Principbl user);

    /**
     * Removes the specified member from the group.
     *
     * @pbrbm user the principbl to remove from this group.
     *
     * @return true if the principbl wbs removed, or
     * fblse if the principbl wbs not b member.
     */
    public boolebn removeMember(Principbl user);

    /**
     * Returns true if the pbssed principbl is b member of the group.
     * This method does b recursive sebrch, so if b principbl belongs to b
     * group which is b member of this group, true is returned.
     *
     * @pbrbm member the principbl whose membership is to be checked.
     *
     * @return true if the principbl is b member of this group,
     * fblse otherwise.
     */
    public boolebn isMember(Principbl member);


    /**
     * Returns bn enumerbtion of the members in the group.
     * The returned objects cbn be instbnces of either Principbl
     * or Group (which is b subclbss of Principbl).
     *
     * @return bn enumerbtion of the group members.
     */
    public Enumerbtion<? extends Principbl> members();

}
