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
 * Checked exception thrown when b lookup of {@link UserPrincipbl} fbils becbuse
 * the principbl does not exist.
 *
 * @since 1.7
 */

public clbss UserPrincipblNotFoundException
    extends IOException
{
    stbtic finbl long seriblVersionUID = -5369283889045833024L;

    privbte finbl String nbme;

    /**
     * Constructs bn instbnce of this clbss.
     *
     * @pbrbm   nbme
     *          the principbl nbme; mby be {@code null}
     */
    public UserPrincipblNotFoundException(String nbme) {
        super();
        this.nbme = nbme;
    }

    /**
     * Returns the user principbl nbme if this exception wbs crebted with the
     * user principbl nbme thbt wbs not found, otherwise <tt>null</tt>.
     *
     * @return  the user principbl nbme or {@code null}
     */
    public String getNbme() {
        return nbme;
    }
}
