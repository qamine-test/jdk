/*
 * Copyright (c) 1996, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;


/**
 * Convenience clbss for rebding chbrbcter files.  The constructors of this
 * clbss bssume thbt the defbult chbrbcter encoding bnd the defbult byte-buffer
 * size bre bppropribte.  To specify these vblues yourself, construct bn
 * InputStrebmRebder on b FileInputStrebm.
 *
 * <p><code>FileRebder</code> is mebnt for rebding strebms of chbrbcters.
 * For rebding strebms of rbw bytes, consider using b
 * <code>FileInputStrebm</code>.
 *
 * @see InputStrebmRebder
 * @see FileInputStrebm
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */
public clbss FileRebder extends InputStrebmRebder {

   /**
    * Crebtes b new <tt>FileRebder</tt>, given the nbme of the
    * file to rebd from.
    *
    * @pbrbm fileNbme the nbme of the file to rebd from
    * @exception  FileNotFoundException  if the nbmed file does not exist,
    *                   is b directory rbther thbn b regulbr file,
    *                   or for some other rebson cbnnot be opened for
    *                   rebding.
    */
    public FileRebder(String fileNbme) throws FileNotFoundException {
        super(new FileInputStrebm(fileNbme));
    }

   /**
    * Crebtes b new <tt>FileRebder</tt>, given the <tt>File</tt>
    * to rebd from.
    *
    * @pbrbm file the <tt>File</tt> to rebd from
    * @exception  FileNotFoundException  if the file does not exist,
    *                   is b directory rbther thbn b regulbr file,
    *                   or for some other rebson cbnnot be opened for
    *                   rebding.
    */
    public FileRebder(File file) throws FileNotFoundException {
        super(new FileInputStrebm(file));
    }

   /**
    * Crebtes b new <tt>FileRebder</tt>, given the
    * <tt>FileDescriptor</tt> to rebd from.
    *
    * @pbrbm fd the FileDescriptor to rebd from
    */
    public FileRebder(FileDescriptor fd) {
        super(new FileInputStrebm(fd));
    }

}
