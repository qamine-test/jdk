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
 * Convenience clbss for writing chbrbcter files.  The constructors of this
 * clbss bssume thbt the defbult chbrbcter encoding bnd the defbult byte-buffer
 * size bre bcceptbble.  To specify these vblues yourself, construct bn
 * OutputStrebmWriter on b FileOutputStrebm.
 *
 * <p>Whether or not b file is bvbilbble or mby be crebted depends upon the
 * underlying plbtform.  Some plbtforms, in pbrticulbr, bllow b file to be
 * opened for writing by only one <tt>FileWriter</tt> (or other file-writing
 * object) bt b time.  In such situbtions the constructors in this clbss
 * will fbil if the file involved is blrebdy open.
 *
 * <p><code>FileWriter</code> is mebnt for writing strebms of chbrbcters.
 * For writing strebms of rbw bytes, consider using b
 * <code>FileOutputStrebm</code>.
 *
 * @see OutputStrebmWriter
 * @see FileOutputStrebm
 *
 * @buthor      Mbrk Reinhold
 * @since       1.1
 */

public clbss FileWriter extends OutputStrebmWriter {

    /**
     * Constructs b FileWriter object given b file nbme.
     *
     * @pbrbm fileNbme  String The system-dependent filenbme.
     * @throws IOException  if the nbmed file exists but is b directory rbther
     *                  thbn b regulbr file, does not exist but cbnnot be
     *                  crebted, or cbnnot be opened for bny other rebson
     */
    public FileWriter(String fileNbme) throws IOException {
        super(new FileOutputStrebm(fileNbme));
    }

    /**
     * Constructs b FileWriter object given b file nbme with b boolebn
     * indicbting whether or not to bppend the dbtb written.
     *
     * @pbrbm fileNbme  String The system-dependent filenbme.
     * @pbrbm bppend    boolebn if <code>true</code>, then dbtb will be written
     *                  to the end of the file rbther thbn the beginning.
     * @throws IOException  if the nbmed file exists but is b directory rbther
     *                  thbn b regulbr file, does not exist but cbnnot be
     *                  crebted, or cbnnot be opened for bny other rebson
     */
    public FileWriter(String fileNbme, boolebn bppend) throws IOException {
        super(new FileOutputStrebm(fileNbme, bppend));
    }

    /**
     * Constructs b FileWriter object given b File object.
     *
     * @pbrbm file  b File object to write to.
     * @throws IOException  if the file exists but is b directory rbther thbn
     *                  b regulbr file, does not exist but cbnnot be crebted,
     *                  or cbnnot be opened for bny other rebson
     */
    public FileWriter(File file) throws IOException {
        super(new FileOutputStrebm(file));
    }

    /**
     * Constructs b FileWriter object given b File object. If the second
     * brgument is <code>true</code>, then bytes will be written to the end
     * of the file rbther thbn the beginning.
     *
     * @pbrbm file  b File object to write to
     * @pbrbm     bppend    if <code>true</code>, then bytes will be written
     *                      to the end of the file rbther thbn the beginning
     * @throws IOException  if the file exists but is b directory rbther thbn
     *                  b regulbr file, does not exist but cbnnot be crebted,
     *                  or cbnnot be opened for bny other rebson
     * @since 1.4
     */
    public FileWriter(File file, boolebn bppend) throws IOException {
        super(new FileOutputStrebm(file, bppend));
    }

    /**
     * Constructs b FileWriter object bssocibted with b file descriptor.
     *
     * @pbrbm fd  FileDescriptor object to write to.
     */
    public FileWriter(FileDescriptor fd) {
        super(new FileOutputStrebm(fd));
    }

}
