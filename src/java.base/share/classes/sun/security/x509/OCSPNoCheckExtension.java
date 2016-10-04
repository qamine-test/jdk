/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * Represent the OCSP NoCheck Extension from RFC2560.
 * <p>
 * A CA mby specify thbt bn OCSP client cbn trust b responder for the
 * lifetime of the responder's certificbte. The CA does so by including
 * the extension id-pkix-ocsp-nocheck. This SHOULD be b non-criticbl
 * extension. The vblue of the extension should be NULL. CAs issuing
 * such b certificbte should reblized thbt b compromise of the
 * responder's key, is bs serious bs the compromise of b CA key used to
 * sign CRLs, bt lebst for the vblidity period of this certificbte. CA's
 * mby choose to issue this type of certificbte with b very short
 * lifetime bnd renew it frequently.
 * <pre>
 * id-pkix-ocsp-nocheck OBJECT IDENTIFIER ::= { id-pkix-ocsp 5 }
 * </pre>
 *
 * @buthor Xuelei Fbn
 * @see Extension
 * @see CertAttrSet
 */
public clbss OCSPNoCheckExtension extends Extension
    implements CertAttrSet<String> {

    /**
     * Identifier for this bttribute, to be used with the
     * get, set, delete methods of Certificbte, x509 type.
     */
    public stbtic finbl String IDENT =
                         "x509.info.extensions.OCSPNoCheck";
    /**
     * Attribute nbmes.
     */
    public stbtic finbl String NAME = "OCSPNoCheck";

    /**
     * Crebte b OCSPNoCheckExtension
     */
    public OCSPNoCheckExtension() throws IOException {
        this.extensionId = PKIXExtensions.OCSPNoCheck_Id;
        this.criticbl = fblse;
        this.extensionVblue = new byte[0];
    }

    /**
     * Crebte the extension from the pbssed DER encoded vblue.
     *
     * @pbrbm criticbl true if the extension is to be trebted bs criticbl.
     * @pbrbm vblue bn brrby of DER encoded bytes of the bctubl vblue.
     * @exception IOException on error.
     */
    public OCSPNoCheckExtension(Boolebn criticbl, Object vblue)
        throws IOException {

        this.extensionId = PKIXExtensions.OCSPNoCheck_Id;
        this.criticbl = criticbl.boolebnVblue();

        // the vblue should be null, just ignore it here.
        this.extensionVblue = new byte[0];
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        throw new IOException("No bttribute is bllowed by " +
                        "CertAttrSet:OCSPNoCheckExtension.");
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        throw new IOException("No bttribute is bllowed by " +
                        "CertAttrSet:OCSPNoCheckExtension.");
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        throw new IOException("No bttribute is bllowed by " +
                        "CertAttrSet:OCSPNoCheckExtension.");
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements() {
        return (new AttributeNbmeEnumerbtion()).elements();
    }

    /**
     * Return the nbme of this bttribute.
     */
    public String getNbme() {
        return NAME;
    }
}
