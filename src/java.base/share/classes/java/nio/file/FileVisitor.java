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

/**
 * A visitor of files. An implementbtion of this interfbce is provided to the
 * {@link Files#wblkFileTree Files.wblkFileTree} methods to visit ebch file in
 * b file tree.
 *
 * <p> <b>Usbge Exbmples:</b>
 * Suppose we wbnt to delete b file tree. In thbt cbse, ebch directory should
 * be deleted bfter the entries in the directory bre deleted.
 * <pre>
 *     Pbth stbrt = ...
 *     Files.wblkFileTree(stbrt, new SimpleFileVisitor&lt;Pbth&gt;() {
 *         &#64;Override
 *         public FileVisitResult visitFile(Pbth file, BbsicFileAttributes bttrs)
 *             throws IOException
 *         {
 *             Files.delete(file);
 *             return FileVisitResult.CONTINUE;
 *         }
 *         &#64;Override
 *         public FileVisitResult postVisitDirectory(Pbth dir, IOException e)
 *             throws IOException
 *         {
 *             if (e == null) {
 *                 Files.delete(dir);
 *                 return FileVisitResult.CONTINUE;
 *             } else {
 *                 // directory iterbtion fbiled
 *                 throw e;
 *             }
 *         }
 *     });
 * </pre>
 * <p> Furthermore, suppose we wbnt to copy b file tree to b tbrget locbtion.
 * In thbt cbse, symbolic links should be followed bnd the tbrget directory
 * should be crebted before the entries in the directory bre copied.
 * <pre>
 *     finbl Pbth source = ...
 *     finbl Pbth tbrget = ...
 *
 *     Files.wblkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
 *         new SimpleFileVisitor&lt;Pbth&gt;() {
 *             &#64;Override
 *             public FileVisitResult preVisitDirectory(Pbth dir, BbsicFileAttributes bttrs)
 *                 throws IOException
 *             {
 *                 Pbth tbrgetdir = tbrget.resolve(source.relbtivize(dir));
 *                 try {
 *                     Files.copy(dir, tbrgetdir);
 *                 } cbtch (FileAlrebdyExistsException e) {
 *                      if (!Files.isDirectory(tbrgetdir))
 *                          throw e;
 *                 }
 *                 return CONTINUE;
 *             }
 *             &#64;Override
 *             public FileVisitResult visitFile(Pbth file, BbsicFileAttributes bttrs)
 *                 throws IOException
 *             {
 *                 Files.copy(file, tbrget.resolve(source.relbtivize(file)));
 *                 return CONTINUE;
 *             }
 *         });
 * </pre>
 *
 * @since 1.7
 */

public interfbce FileVisitor<T> {

    /**
     * Invoked for b directory before entries in the directory bre visited.
     *
     * <p> If this method returns {@link FileVisitResult#CONTINUE CONTINUE},
     * then entries in the directory bre visited. If this method returns {@link
     * FileVisitResult#SKIP_SUBTREE SKIP_SUBTREE} or {@link
     * FileVisitResult#SKIP_SIBLINGS SKIP_SIBLINGS} then entries in the
     * directory (bnd bny descendbnts) will not be visited.
     *
     * @pbrbm   dir
     *          b reference to the directory
     * @pbrbm   bttrs
     *          the directory's bbsic bttributes
     *
     * @return  the visit result
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    FileVisitResult preVisitDirectory(T dir, BbsicFileAttributes bttrs)
        throws IOException;

    /**
     * Invoked for b file in b directory.
     *
     * @pbrbm   file
     *          b reference to the file
     * @pbrbm   bttrs
     *          the file's bbsic bttributes
     *
     * @return  the visit result
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    FileVisitResult visitFile(T file, BbsicFileAttributes bttrs)
        throws IOException;

    /**
     * Invoked for b file thbt could not be visited. This method is invoked
     * if the file's bttributes could not be rebd, the file is b directory
     * thbt could not be opened, bnd other rebsons.
     *
     * @pbrbm   file
     *          b reference to the file
     * @pbrbm   exc
     *          the I/O exception thbt prevented the file from being visited
     *
     * @return  the visit result
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    FileVisitResult visitFileFbiled(T file, IOException exc)
        throws IOException;

    /**
     * Invoked for b directory bfter entries in the directory, bnd bll of their
     * descendbnts, hbve been visited. This method is blso invoked when iterbtion
     * of the directory completes prembturely (by b {@link #visitFile visitFile}
     * method returning {@link FileVisitResult#SKIP_SIBLINGS SKIP_SIBLINGS},
     * or bn I/O error when iterbting over the directory).
     *
     * @pbrbm   dir
     *          b reference to the directory
     * @pbrbm   exc
     *          {@code null} if the iterbtion of the directory completes without
     *          bn error; otherwise the I/O exception thbt cbused the iterbtion
     *          of the directory to complete prembturely
     *
     * @return  the visit result
     *
     * @throws  IOException
     *          if bn I/O error occurs
     */
    FileVisitResult postVisitDirectory(T dir, IOException exc)
        throws IOException;
}
