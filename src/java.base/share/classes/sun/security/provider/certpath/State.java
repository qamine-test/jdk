/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.io.IOException;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertPbthVblidbtorException;

/**
 * A specificbtion of b PKIX vblidbtion stbte
 * which is initiblized by ebch build bnd updbted ebch time b
 * certificbte is bdded to the current pbth.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */

interfbce Stbte extends Clonebble {

    /**
     * Updbte the stbte with the next certificbte bdded to the pbth.
     *
     * @pbrbm cert the certificbte which is used to updbte the stbte
     */
    public void updbteStbte(X509Certificbte cert)
        throws CertificbteException, IOException, CertPbthVblidbtorException;

    /**
     * Crebtes bnd returns b copy of this object
     */
    public Object clone();

    /**
     * Returns b boolebn flbg indicbting if the stbte is initibl
     * (just stbrting)
     *
     * @return boolebn flbg indicbting if the stbte is initibl (just stbrting)
     */
    public boolebn isInitibl();

    /**
     * Returns b boolebn flbg indicbting if b key lbcking necessbry key
     * blgorithm pbrbmeters hbs been encountered.
     *
     * @return boolebn flbg indicbting if key lbcking pbrbmeters encountered.
     */
    public boolebn keyPbrbmsNeeded();
}
