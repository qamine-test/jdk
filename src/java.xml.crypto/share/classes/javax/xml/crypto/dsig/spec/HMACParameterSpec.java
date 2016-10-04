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
 * $Id: HMACPbrbmeterSpec.jbvb,v 1.4 2005/05/10 16:40:17 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.spec;

import jbvbx.xml.crypto.dsig.SignbtureMethod;

/**
 * Pbrbmeters for the <b href="http://www.w3.org/TR/xmldsig-core/#sec-MACs">
 * XML Signbture HMAC Algorithm</b>. The pbrbmeters include bn optionbl output
 * length which specifies the MAC truncbtion length in bits. The resulting
 * HMAC will be truncbted to the specified number of bits. If the pbrbmeter is
 * not specified, then this implies thbt bll the bits of the hbsh bre to be
 * output. The XML Schemb Definition of the <code>HMACOutputLength</code>
 * element is defined bs:
 * <pre><code>
 * &lt;element nbme="HMACOutputLength" minOccurs="0" type="ds:HMACOutputLengthType"/&gt;
 * &lt;simpleType nbme="HMACOutputLengthType"&gt;
 *   &lt;restriction bbse="integer"/&gt;
 * &lt;/simpleType&gt;
 * </code></pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see SignbtureMethod
 * @see <b href="http://www.ietf.org/rfc/rfc2104.txt">RFC 2104</b>
 */
public finbl clbss HMACPbrbmeterSpec implements SignbtureMethodPbrbmeterSpec {

    privbte int outputLength;

    /**
     * Crebtes bn <code>HMACPbrbmeterSpec</code> with the specified truncbtion
     * length.
     *
     * @pbrbm outputLength the truncbtion length in number of bits
     */
    public HMACPbrbmeterSpec(int outputLength) {
        this.outputLength = outputLength;
    }

    /**
     * Returns the truncbtion length.
     *
     * @return the truncbtion length in number of bits
     */
    public int getOutputLength() {
        return outputLength;
    }
}
