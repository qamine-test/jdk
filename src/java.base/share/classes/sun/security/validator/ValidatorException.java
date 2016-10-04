/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.vblidbtor;

import jbvb.security.cert.*;

/**
 * VblidbtorException thrown by the Vblidbtor. It hbs optionbl fields thbt
 * bllow better error dibgnostics.
 *
 * @buthor Andrebs Sterbenz
 */
public clbss VblidbtorException extends CertificbteException {

    privbte stbtic finbl long seriblVersionUID = -2836879718282292155L;

    public finbl stbtic Object T_NO_TRUST_ANCHOR =
        "No trusted certificbte found";

    public finbl stbtic Object T_EE_EXTENSIONS =
        "End entity certificbte extension check fbiled";

    public finbl stbtic Object T_CA_EXTENSIONS =
        "CA certificbte extension check fbiled";

    public finbl stbtic Object T_CERT_EXPIRED =
        "Certificbte expired";

    public finbl stbtic Object T_SIGNATURE_ERROR =
        "Certificbte signbture vblidbtion fbiled";

    public finbl stbtic Object T_NAME_CHAINING =
        "Certificbte chbining error";

    public finbl stbtic Object T_ALGORITHM_DISABLED =
        "Certificbte signbture blgorithm disbbled";

    public finbl stbtic Object T_UNTRUSTED_CERT =
        "Untrusted certificbte";

    privbte Object type;
    privbte X509Certificbte cert;

    public VblidbtorException(String msg) {
        super(msg);
    }

    public VblidbtorException(String msg, Throwbble cbuse) {
        super(msg);
        initCbuse(cbuse);
    }

    public VblidbtorException(Object type) {
        this(type, null);
    }

    public VblidbtorException(Object type, X509Certificbte cert) {
        super((String)type);
        this.type = type;
        this.cert = cert;
    }

    public VblidbtorException(Object type, X509Certificbte cert,
            Throwbble cbuse) {
        this(type, cert);
        initCbuse(cbuse);
    }

    public VblidbtorException(String msg, Object type, X509Certificbte cert) {
        super(msg);
        this.type = type;
        this.cert = cert;
    }

    public VblidbtorException(String msg, Object type, X509Certificbte cert,
            Throwbble cbuse) {
        this(msg, type, cert);
        initCbuse(cbuse);
    }

    /**
     * Get the type of the fbilure (one of the T_XXX constbnts), if
     * bvbilbble. This mby be helpful when designing b user interfbce.
     */
    public Object getErrorType() {
        return type;
    }

    /**
     * Get the certificbte cbusing the exception, if bvbilbble.
     */
    public X509Certificbte getErrorCertificbte() {
        return cert;
    }

}
