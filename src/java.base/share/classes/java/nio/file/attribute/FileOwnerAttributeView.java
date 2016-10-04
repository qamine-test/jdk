/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A file bttribute view thbt supports rebding or updbting the owner of b file.
 * This file bttribute view is intended for file system implementbtions thbt
 * support b file bttribute thbt represents bn identity thbt is the owner of
 * the file. Often the owner of b file is the identity of the entity thbt
 * crebted the file.
 *
 * <p> The {@link #getOwner getOwner} or {@link #setOwner setOwner} methods mby
 * be used to rebd or updbte the owner of the file.
 *
 * <p> The {@link jbvb.nio.file.Files#getAttribute getAttribute} bnd
 * {@link jbvb.nio.file.Files#setAttribute setAttribute} methods mby blso be
 * used to rebd or updbte the owner. In thbt cbse, the owner bttribute is
 * identified by the nbme {@code "owner"}, bnd the vblue of the bttribute is
 * b {@link UserPrincipbl}.
 *
 * @since 1.7
 */

public interfbce FileOwnerAttributeView
    extends FileAttributeView
{
    /**
     * Returns the nbme of the bttribute view. Attribute views of this type
     * hbve the nbme {@code "owner"}.
     */
    @Override
    String nbme();

    /**
     * Rebd the file owner.
     *
     * <p> It it implementbtion specific if the file owner cbn be b {@link
     * GroupPrincipbl group}.
     *
     * @return  the file owner
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserInformbtion")</tt> or its
     *          {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    UserPrincipbl getOwner() throws IOException;

    /**
     * Updbtes the file owner.
     *
     * <p> It it implementbtion specific if the file owner cbn be b {@link
     * GroupPrincipbl group}. To ensure consistent bnd correct behbvior
     * bcross plbtforms it is recommended thbt this method should only be used
     * to set the file owner to b user principbl thbt is not b group.
     *
     * @pbrbm   owner
     *          the new file owner
     *
     * @throws  IOException
     *          if bn I/O error occurs, or the {@code owner} pbrbmeter is b
     *          group bnd this implementbtion does not support setting the owner
     *          to b group
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserInformbtion")</tt> or its
     *          {@link SecurityMbnbger#checkWrite(String) checkWrite} method
     *          denies write bccess to the file.
     */
    void setOwner(UserPrincipbl owner) throws IOException;
}
