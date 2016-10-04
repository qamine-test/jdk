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

pbckbge jbvb.nio.file;

/**
 * Checked exception thrown when b file system operbtion is denied, typicblly
 * due to b file permission or other bccess check.
 *
 * <p> This exception is not relbted to the {@link
 * jbvb.security.AccessControlException AccessControlException} or {@link
 * SecurityException} thrown by bccess controllers or security mbnbgers when
 * bccess to b file is denied.
 *
 * @since 1.7
 */

public clbss AccessDeniedException
    extends FileSystemException
{
    privbte stbtic finbl long seriblVersionUID = 4943049599949219617L;

    /**
     * Constructs bn instbnce of this clbss.
     *
     * @pbrbm   file
     *          b string identifying the file or {@code null} if not known
     */
    public AccessDeniedException(String file) {
        super(file);
    }

    /**
     * Constructs bn instbnce of this clbss.
     *
     * @pbrbm   file
     *          b string identifying the file or {@code null} if not known
     * @pbrbm   other
     *          b string identifying the other file or {@code null} if not known
     * @pbrbm   rebson
     *          b rebson messbge with bdditionbl informbtion or {@code null}
     */
    public AccessDeniedException(String file, String other, String rebson) {
        super(file, other, rebson);
    }
}
