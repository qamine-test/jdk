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
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * $Id: URIDereferencer.jbvb,v 1.5 2005/05/10 15:47:42 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto;

/**
 * A dereferencer of {@link URIReference}s.
 * <p>
 * The result of dereferencing b <code>URIReference</code> is either bn
 * instbnce of {@link OctetStrebmDbtb} or {@link NodeSetDbtb}. Unless the
 * <code>URIReference</code> is b <i>sbme-document reference</i> bs defined
 * in section 4.2 of the W3C Recommendbtion for XML-Signbture Syntbx bnd
 * Processing, the result of dereferencing the <code>URIReference</code>
 * MUST be bn <code>OctetStrebmDbtb</code>.
 *
 * @buthor Sebn Mullbn
 * @buthor Joyce Leung
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLCryptoContext#setURIDereferencer(URIDereferencer)
 * @see XMLCryptoContext#getURIDereferencer
 */
public interfbce URIDereferencer {

    /**
     * Dereferences the specified <code>URIReference</code> bnd returns the
     * dereferenced dbtb.
     *
     * @pbrbm uriReference the <code>URIReference</code>
     * @pbrbm context bn <code>XMLCryptoContext</code> thbt mby
     *    contbin bdditionbl useful informbtion for dereferencing the URI. This
     *    implementbtion should dereference the specified
     *    <code>URIReference</code> bgbinst the context's <code>bbseURI</code>
     *    pbrbmeter, if specified.
     * @return the dereferenced dbtb
     * @throws NullPointerException if <code>uriReference</code> or
     *    <code>context</code> bre <code>null</code>
     * @throws URIReferenceException if bn exception occurs while
     *    dereferencing the specified <code>uriReference</code>
     */
    Dbtb dereference(URIReference uriReference, XMLCryptoContext context)
        throws URIReferenceException;
}
