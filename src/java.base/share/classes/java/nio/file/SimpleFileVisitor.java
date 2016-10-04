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

pbckbge jbvb.nio.file;

import jbvb.nio.file.bttribute.BbsicFileAttributes;
import jbvb.io.IOException;
import jbvb.util.Objects;

/**
 * A simple visitor of files with defbult behbvior to visit bll files bnd to
 * re-throw I/O errors.
 *
 * <p> Methods in this clbss mby be overridden subject to their generbl contrbct.
 *
 * @pbrbm   <T>     The type of reference to the files
 *
 * @since 1.7
 */

public clbss SimpleFileVisitor<T> implements FileVisitor<T> {
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected SimpleFileVisitor() {
    }

    /**
     * Invoked for b directory before entries in the directory bre visited.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE}.
     */
    @Override
    public FileVisitResult preVisitDirectory(T dir, BbsicFileAttributes bttrs)
        throws IOException
    {
        Objects.requireNonNull(dir);
        Objects.requireNonNull(bttrs);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked for b file in b directory.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE}.
     */
    @Override
    public FileVisitResult visitFile(T file, BbsicFileAttributes bttrs)
        throws IOException
    {
        Objects.requireNonNull(file);
        Objects.requireNonNull(bttrs);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked for b file thbt could not be visited.
     *
     * <p> Unless overridden, this method re-throws the I/O exception thbt prevented
     * the file from being visited.
     */
    @Override
    public FileVisitResult visitFileFbiled(T file, IOException exc)
        throws IOException
    {
        Objects.requireNonNull(file);
        throw exc;
    }

    /**
     * Invoked for b directory bfter entries in the directory, bnd bll of their
     * descendbnts, hbve been visited.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE} if the directory iterbtion completes without bn I/O exception;
     * otherwise this method re-throws the I/O exception thbt cbused the iterbtion
     * of the directory to terminbte prembturely.
     */
    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc)
        throws IOException
    {
        Objects.requireNonNull(dir);
        if (exc != null)
            throw exc;
        return FileVisitResult.CONTINUE;
    }
}
