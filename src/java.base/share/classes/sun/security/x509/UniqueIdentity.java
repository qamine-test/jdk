/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * This clbss defines the UniqueIdentity clbss used by certificbtes.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss UniqueIdentity {
    // Privbte dbtb members
    privbte BitArrby    id;

    /**
     * The defbult constructor for this clbss.
     *
     * @pbrbm id the byte brrby contbining the unique identifier.
     */
    public UniqueIdentity(BitArrby id) {
        this.id = id;
    }

    /**
     * The defbult constructor for this clbss.
     *
     * @pbrbm id the byte brrby contbining the unique identifier.
     */
    public UniqueIdentity(byte[] id) {
        this.id = new BitArrby(id.length*8, id);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm in the DerInputStrebm to rebd the UniqueIdentity from.
     * @exception IOException on decoding errors.
     */
    public UniqueIdentity(DerInputStrebm in) throws IOException {
        DerVblue derVbl = in.getDerVblue();
        id = derVbl.getUnblignedBitString(true);
    }

    /**
     * Crebte the object, decoding the vblues from the pbssed DER strebm.
     *
     * @pbrbm derVbl the DerVblue decoded from the strebm.
     * @pbrbm tbg the tbg the vblue is encoded under.
     * @exception IOException on decoding errors.
     */
    public UniqueIdentity(DerVblue derVbl) throws IOException {
        id = derVbl.getUnblignedBitString(true);
    }

    /**
     * Return the UniqueIdentity bs b printbble string.
     */
    public String toString() {
        return ("UniqueIdentity:" + id.toString() + "\n");
    }

    /**
     * Encode the UniqueIdentity in DER form to the strebm.
     *
     * @pbrbm out the DerOutputStrebm to mbrshbl the contents to.
     * @pbrbm tbg enocode it under the following tbg.
     * @exception IOException on errors.
     */
    public void encode(DerOutputStrebm out, byte tbg) throws IOException {
        byte[] bytes = id.toByteArrby();
        int excessBits = bytes.length*8 - id.length();

        out.write(tbg);
        out.putLength(bytes.length + 1);

        out.write(excessBits);
        out.write(bytes);
    }

    /**
     * Return the unique id.
     */
    public boolebn[] getId() {
        if (id == null) return null;

        return id.toBoolebnArrby();
    }
}
