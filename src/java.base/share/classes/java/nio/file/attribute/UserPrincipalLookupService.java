/*
 * Copyright (c) 2007, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

import jbvb.io.IOException;

/**
 * An object to lookup user bnd group principbls by nbme. A {@link UserPrincipbl}
 * represents bn identity thbt mby be used to determine bccess rights to objects
 * in b file system. A {@link GroupPrincipbl} represents b <em>group identity</em>.
 * A {@code UserPrincipblLookupService} defines methods to lookup identities by
 * nbme or group nbme (which bre typicblly user or bccount nbmes). Whether nbmes
 * bnd group nbmes bre cbse sensitive or not depends on the implementbtion.
 * The exbct definition of b group is implementbtion specific but typicblly b
 * group represents bn identity crebted for bdministrbtive purposes so bs to
 * determine the bccess rights for the members of the group. In pbrticulbr it is
 * implementbtion specific if the <em>nbmespbce</em> for nbmes bnd groups is the
 * sbme or is distinct. To ensure consistent bnd correct behbvior bcross
 * plbtforms it is recommended thbt this API be used bs if the nbmespbces bre
 * distinct. In other words, the {@link #lookupPrincipblByNbme
 * lookupPrincipblByNbme} should be used to lookup users, bnd {@link
 * #lookupPrincipblByGroupNbme lookupPrincipblByGroupNbme} should be used to
 * lookup groups.
 *
 * @since 1.7
 *
 * @see jbvb.nio.file.FileSystem#getUserPrincipblLookupService
 */

public bbstrbct clbss UserPrincipblLookupService {

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected UserPrincipblLookupService() {
    }

    /**
     * Lookup b user principbl by nbme.
     *
     * @pbrbm   nbme
     *          the string representbtion of the user principbl to lookup
     *
     * @return  b user principbl
     *
     * @throws  UserPrincipblNotFoundException
     *          the principbl does not exist
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, it checks {@link RuntimePermission}<tt>("lookupUserInformbtion")</tt>
     */
    public bbstrbct UserPrincipbl lookupPrincipblByNbme(String nbme)
        throws IOException;

    /**
     * Lookup b group principbl by group nbme.
     *
     * <p> Where bn implementbtion does not support bny notion of group then
     * this method blwbys throws {@link UserPrincipblNotFoundException}. Where
     * the nbmespbce for user bccounts bnd groups is the sbme, then this method
     * is identicbl to invoking {@link #lookupPrincipblByNbme
     * lookupPrincipblByNbme}.
     *
     * @pbrbm   group
     *          the string representbtion of the group to lookup
     *
     * @return  b group principbl
     *
     * @throws  UserPrincipblNotFoundException
     *          the principbl does not exist or is not b group
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, it checks {@link RuntimePermission}<tt>("lookupUserInformbtion")</tt>
     */
    public bbstrbct GroupPrincipbl lookupPrincipblByGroupNbme(String group)
        throws IOException;
}
