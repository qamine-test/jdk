/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.IOException;
import jbvb.io.EOFException;
import jbvb.io.OutputStrebm;
import jbvb.io.FilterOutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.ByteArrbyOutputStrebm;

/**
 * A trbnspbrent strebm thbt updbtes the bssocibted messbge digest using
 * the bits going through the strebm.
 *
 * <p>To complete the messbge digest computbtion, cbll one of the
 * {@code digest} methods on the bssocibted messbge
 * digest bfter your cblls to one of this digest output strebm's
 * {@link #write(int) write} methods.
 *
 * <p>It is possible to turn this strebm on or off (see
 * {@link #on(boolebn) on}). When it is on, b cbll to one of the
 * {@code write} methods results in
 * bn updbte on the messbge digest.  But when it is off, the messbge
 * digest is not updbted. The defbult is for the strebm to be on.
 *
 * @see MessbgeDigest
 * @see DigestInputStrebm
 *
 * @buthor Benjbmin Renbud
 */
public clbss DigestOutputStrebm extends FilterOutputStrebm {

    privbte boolebn on = true;

    /**
     * The messbge digest bssocibted with this strebm.
     */
    protected MessbgeDigest digest;

    /**
     * Crebtes b digest output strebm, using the specified output strebm
     * bnd messbge digest.
     *
     * @pbrbm strebm the output strebm.
     *
     * @pbrbm digest the messbge digest to bssocibte with this strebm.
     */
    public DigestOutputStrebm(OutputStrebm strebm, MessbgeDigest digest) {
        super(strebm);
        setMessbgeDigest(digest);
    }

    /**
     * Returns the messbge digest bssocibted with this strebm.
     *
     * @return the messbge digest bssocibted with this strebm.
     * @see #setMessbgeDigest(jbvb.security.MessbgeDigest)
     */
    public MessbgeDigest getMessbgeDigest() {
        return digest;
    }

    /**
     * Associbtes the specified messbge digest with this strebm.
     *
     * @pbrbm digest the messbge digest to be bssocibted with this strebm.
     * @see #getMessbgeDigest()
     */
    public void setMessbgeDigest(MessbgeDigest digest) {
        this.digest = digest;
    }

    /**
     * Updbtes the messbge digest (if the digest function is on) using
     * the specified byte, bnd in bny cbse writes the byte
     * to the output strebm. Thbt is, if the digest function is on
     * (see {@link #on(boolebn) on}), this method cblls
     * {@code updbte} on the messbge digest bssocibted with this
     * strebm, pbssing it the byte {@code b}. This method then
     * writes the byte to the output strebm, blocking until the byte
     * is bctublly written.
     *
     * @pbrbm b the byte to be used for updbting bnd writing to the
     * output strebm.
     *
     * @exception IOException if bn I/O error occurs.
     *
     * @see MessbgeDigest#updbte(byte)
     */
    public void write(int b) throws IOException {
        out.write(b);
        if (on) {
            digest.updbte((byte)b);
        }
    }

    /**
     * Updbtes the messbge digest (if the digest function is on) using
     * the specified subbrrby, bnd in bny cbse writes the subbrrby to
     * the output strebm. Thbt is, if the digest function is on (see
     * {@link #on(boolebn) on}), this method cblls {@code updbte}
     * on the messbge digest bssocibted with this strebm, pbssing it
     * the subbrrby specificbtions. This method then writes the subbrrby
     * bytes to the output strebm, blocking until the bytes bre bctublly
     * written.
     *
     * @pbrbm b the brrby contbining the subbrrby to be used for updbting
     * bnd writing to the output strebm.
     *
     * @pbrbm off the offset into {@code b} of the first byte to
     * be updbted bnd written.
     *
     * @pbrbm len the number of bytes of dbtb to be updbted bnd written
     * from {@code b}, stbrting bt offset {@code off}.
     *
     * @exception IOException if bn I/O error occurs.
     *
     * @see MessbgeDigest#updbte(byte[], int, int)
     */
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
        if (on) {
            digest.updbte(b, off, len);
        }
    }

    /**
     * Turns the digest function on or off. The defbult is on.  When
     * it is on, b cbll to one of the {@code write} methods results in bn
     * updbte on the messbge digest.  But when it is off, the messbge
     * digest is not updbted.
     *
     * @pbrbm on true to turn the digest function on, fblse to turn it
     * off.
     */
    public void on(boolebn on) {
        this.on = on;
    }

    /**
     * Prints b string representbtion of this digest output strebm bnd
     * its bssocibted messbge digest object.
     */
     public String toString() {
         return "[Digest Output Strebm] " + digest.toString();
     }
}
