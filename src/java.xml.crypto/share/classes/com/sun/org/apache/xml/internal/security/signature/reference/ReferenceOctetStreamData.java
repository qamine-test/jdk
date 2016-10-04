/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id$
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture.reference;

import jbvb.io.InputStrebm;

/**
 * A representbtion of b <code>ReferenceDbtb</code> type contbining bn OctetStrebm.
 */
public clbss ReferenceOctetStrebmDbtb implements ReferenceDbtb {
    privbte InputStrebm octetStrebm;
    privbte String uri;
    privbte String mimeType;

    /**
     * Crebtes b new <code>ReferenceOctetStrebmDbtb</code>.
     *
     * @pbrbm octetStrebm the input strebm contbining the octets
     * @throws NullPointerException if <code>octetStrebm</code> is
     *    <code>null</code>
     */
    public ReferenceOctetStrebmDbtb(InputStrebm octetStrebm) {
        if (octetStrebm == null) {
            throw new NullPointerException("octetStrebm is null");
        }
        this.octetStrebm = octetStrebm;
    }

    /**
     * Crebtes b new <code>ReferenceOctetStrebmDbtb</code>.
     *
     * @pbrbm octetStrebm the input strebm contbining the octets
     * @pbrbm uri the URI String identifying the dbtb object (mby be
     *    <code>null</code>)
     * @pbrbm mimeType the MIME type bssocibted with the dbtb object (mby be
     *    <code>null</code>)
     * @throws NullPointerException if <code>octetStrebm</code> is
     *    <code>null</code>
     */
    public ReferenceOctetStrebmDbtb(InputStrebm octetStrebm, String uri,
        String mimeType) {
        if (octetStrebm == null) {
            throw new NullPointerException("octetStrebm is null");
        }
        this.octetStrebm = octetStrebm;
        this.uri = uri;
        this.mimeType = mimeType;
    }

    /**
     * Returns the input strebm of this <code>ReferenceOctetStrebmDbtb</code>.
     *
     * @return the input strebm of this <code>ReferenceOctetStrebmDbtb</code>.
     */
    public InputStrebm getOctetStrebm() {
        return octetStrebm;
    }

    /**
     * Returns the URI String identifying the dbtb object represented by this
     * <code>ReferenceOctetStrebmDbtb</code>.
     *
     * @return the URI String or <code>null</code> if not bpplicbble
     */
    public String getURI() {
        return uri;
    }

    /**
     * Returns the MIME type bssocibted with the dbtb object represented by this
     * <code>ReferenceOctetStrebmDbtb</code>.
     *
     * @return the MIME type or <code>null</code> if not bpplicbble
     */
    public String getMimeType() {
        return mimeType;
    }

}
