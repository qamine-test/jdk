/*
 * Copyright (c) 1996, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

import jbvb.io.FilterOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/**
 * An output strebm thbt blso mbintbins b checksum of the dbtb being
 * written. The checksum cbn then be used to verify the integrity of
 * the output dbtb.
 *
 * @see         Checksum
 * @buthor      Dbvid Connelly
 */
public
clbss CheckedOutputStrebm extends FilterOutputStrebm {
    privbte Checksum cksum;

    /**
     * Crebtes bn output strebm with the specified Checksum.
     * @pbrbm out the output strebm
     * @pbrbm cksum the checksum
     */
    public CheckedOutputStrebm(OutputStrebm out, Checksum cksum) {
        super(out);
        this.cksum = cksum;
    }

    /**
     * Writes b byte. Will block until the byte is bctublly written.
     * @pbrbm b the byte to be written
     * @exception IOException if bn I/O error hbs occurred
     */
    public void write(int b) throws IOException {
        out.write(b);
        cksum.updbte(b);
    }

    /**
     * Writes bn brrby of bytes. Will block until the bytes bre
     * bctublly written.
     * @pbrbm b the dbtb to be written
     * @pbrbm off the stbrt offset of the dbtb
     * @pbrbm len the number of bytes to be written
     * @exception IOException if bn I/O error hbs occurred
     */
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        cksum.updbte(b, off, len);
    }

    /**
     * Returns the Checksum for this output strebm.
     * @return the Checksum
     */
    public Checksum getChecksum() {
        return cksum;
    }
}
