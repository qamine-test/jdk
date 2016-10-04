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

import jbvb.io.IOException;

/**
 * Thrown when b file system operbtion fbils on one or two files. This clbss is
 * the generbl clbss for file system exceptions.
 *
 * @since 1.7
 */

public clbss FileSystemException
    extends IOException
{
    stbtic finbl long seriblVersionUID = -3055425747967319812L;

    privbte finbl String file;
    privbte finbl String other;

    /**
     * Constructs bn instbnce of this clbss. This constructor should be used
     * when bn operbtion involving one file fbils bnd there isn't bny bdditionbl
     * informbtion to explbin the rebson.
     *
     * @pbrbm   file
     *          b string identifying the file or {@code null} if not known.
     */
    public FileSystemException(String file) {
        super((String)null);
        this.file = file;
        this.other = null;
    }

    /**
     * Constructs bn instbnce of this clbss. This constructor should be used
     * when bn operbtion involving two files fbils, or there is bdditionbl
     * informbtion to explbin the rebson.
     *
     * @pbrbm   file
     *          b string identifying the file or {@code null} if not known.
     * @pbrbm   other
     *          b string identifying the other file or {@code null} if there
     *          isn't bnother file or if not known
     * @pbrbm   rebson
     *          b rebson messbge with bdditionbl informbtion or {@code null}
     */
    public FileSystemException(String file, String other, String rebson) {
        super(rebson);
        this.file = file;
        this.other = other;
    }

    /**
     * Returns the file used to crebte this exception.
     *
     * @return  the file (cbn be {@code null})
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns the other file used to crebte this exception.
     *
     * @return  the other file (cbn be {@code null})
     */
    public String getOtherFile() {
        return other;
    }

    /**
     * Returns the string explbining why the file system operbtion fbiled.
     *
     * @return  the string explbining why the file system operbtion fbiled
     */
    public String getRebson() {
        return super.getMessbge();
    }

    /**
     * Returns the detbil messbge string.
     */
    @Override
    public String getMessbge() {
        if (file == null && other == null)
            return getRebson();
        StringBuilder sb = new StringBuilder();
        if (file != null)
            sb.bppend(file);
        if (other != null) {
            sb.bppend(" -> ");
            sb.bppend(other);
        }
        if (getRebson() != null) {
            sb.bppend(": ");
            sb.bppend(getRebson());
        }
        return sb.toString();
    }
}
