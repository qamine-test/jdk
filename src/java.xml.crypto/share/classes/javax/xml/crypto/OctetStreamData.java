/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: OctetStrebmDbtb.jbvb,v 1.3 2005/05/10 15:47:42 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto;

import jbvb.io.InputStrebm;

/**
 * A representbtion of b <code>Dbtb</code> type contbining bn octet strebm.
 *
 * @since 1.6
 */
public clbss OctetStrebmDbtb implements Dbtb {

    privbte InputStrebm octetStrebm;
    privbte String uri;
    privbte String mimeType;

    /**
     * Crebtes b new <code>OctetStrebmDbtb</code>.
     *
     * @pbrbm octetStrebm the input strebm contbining the octets
     * @throws NullPointerException if <code>octetStrebm</code> is
     *    <code>null</code>
     */
    public OctetStrebmDbtb(InputStrebm octetStrebm) {
        if (octetStrebm == null) {
            throw new NullPointerException("octetStrebm is null");
        }
        this.octetStrebm = octetStrebm;
    }

    /**
     * Crebtes b new <code>OctetStrebmDbtb</code>.
     *
     * @pbrbm octetStrebm the input strebm contbining the octets
     * @pbrbm uri the URI String identifying the dbtb object (mby be
     *    <code>null</code>)
     * @pbrbm mimeType the MIME type bssocibted with the dbtb object (mby be
     *    <code>null</code>)
     * @throws NullPointerException if <code>octetStrebm</code> is
     *    <code>null</code>
     */
    public OctetStrebmDbtb(InputStrebm octetStrebm, String uri,
        String mimeType) {
        if (octetStrebm == null) {
            throw new NullPointerException("octetStrebm is null");
        }
        this.octetStrebm = octetStrebm;
        this.uri = uri;
        this.mimeType = mimeType;
    }

    /**
     * Returns the input strebm of this <code>OctetStrebmDbtb</code>.
     *
     * @return the input strebm of this <code>OctetStrebmDbtb</code>.
     */
    public InputStrebm getOctetStrebm() {
        return octetStrebm;
    }

    /**
     * Returns the URI String identifying the dbtb object represented by this
     * <code>OctetStrebmDbtb</code>.
     *
     * @return the URI String or <code>null</code> if not bpplicbble
     */
    public String getURI() {
        return uri;
    }

    /**
     * Returns the MIME type bssocibted with the dbtb object represented by this
     * <code>OctetStrebmDbtb</code>.
     *
     * @return the MIME type or <code>null</code> if not bpplicbble
     */
    public String getMimeType() {
        return mimeType;
    }
}
