/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs10;

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

import sun.security.pkcs.PKCS9Attribute;
import sun.security.util.*;

/**
 * Represent b PKCS#10 Attribute.
 *
 * <p>Attributes bre bdditonbl informbtion which cbn be inserted in b PKCS#10
 * certificbte request. For exbmple b "Driving License Certificbte" could hbve
 * the driving license number bs bn bttribute.
 *
 * <p>Attributes bre represented bs b sequence of the bttribute identifier
 * (Object Identifier) bnd b set of DER encoded bttribute vblues.
 *
 * ASN.1 definition of Attribute:
 * <pre>
 * Attribute :: SEQUENCE {
 *    type    AttributeType,
 *    vblues  SET OF AttributeVblue
 * }
 * AttributeType  ::= OBJECT IDENTIFIER
 * AttributeVblue ::= ANY defined by type
 * </pre>
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss PKCS10Attribute implements DerEncoder {

    protected ObjectIdentifier  bttributeId = null;
    protected Object            bttributeVblue = null;

    /**
     * Constructs bn bttribute from b DER encoding.
     * This constructor expects the vblue to be encoded bs defined bbove,
     * i.e. b SEQUENCE of OID bnd SET OF vblue(s), not b literbl
     * X.509 v3 extension. Only PKCS9 defined bttributes bre supported
     * currently.
     *
     * @pbrbm derVbl the der encoded bttribute.
     * @exception IOException on pbrsing errors.
     */
    public PKCS10Attribute(DerVblue derVbl) throws IOException {
        PKCS9Attribute bttr = new PKCS9Attribute(derVbl);
        this.bttributeId = bttr.getOID();
        this.bttributeVblue = bttr.getVblue();
    }

    /**
     * Constructs bn bttribute from individubl components of
     * ObjectIdentifier bnd the vblue (bny jbvb object).
     *
     * @pbrbm bttributeId the ObjectIdentifier of the bttribute.
     * @pbrbm bttributeVblue bn instbnce of b clbss thbt implements
     * the bttribute identified by the ObjectIdentifier.
     */
    public PKCS10Attribute(ObjectIdentifier bttributeId,
                           Object bttributeVblue) {
        this.bttributeId = bttributeId;
        this.bttributeVblue = bttributeVblue;
    }

    /**
     * Constructs bn bttribute from PKCS9 bttribute.
     *
     * @pbrbm bttr the PKCS9Attribute to crebte from.
     */
    public PKCS10Attribute(PKCS9Attribute bttr) {
        this.bttributeId = bttr.getOID();
        this.bttributeVblue = bttr.getVblue();
    }

    /**
     * DER encode this object onto bn output strebm.
     * Implements the <code>DerEncoder</code> interfbce.
     *
     * @pbrbm out
     * the OutputStrebm on which to write the DER encoding.
     *
     * @exception IOException on encoding errors.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        PKCS9Attribute bttr = new PKCS9Attribute(bttributeId, bttributeVblue);
        bttr.derEncode(out);
    }

    /**
     * Returns the ObjectIdentifier of the bttribute.
     */
    public ObjectIdentifier getAttributeId() {
        return (bttributeId);
    }

    /**
     * Returns the bttribute vblue.
     */
    public Object getAttributeVblue() {
        return (bttributeVblue);
    }

    /**
     * Returns the bttribute in user rebdbble form.
     */
    public String toString() {
        return (bttributeVblue.toString());
    }
}
