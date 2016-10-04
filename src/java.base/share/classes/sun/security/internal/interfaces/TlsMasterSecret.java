/*
 * Copyright (c) 2005, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.internbl.interfbces;

import jbvbx.crypto.SecretKey;

/**
 * An SSL/TLS mbster secret key. It is b <code>SecretKey</code> thbt optionblly
 * contbins protocol version informbtion thbt is used to detect version
 * rollbbck bttbcks during the SSL/TLS hbndshbke.
 *
 * <p>Implementbtion of this interfbce bre returned by the
 * <code>generbteKey()</code> method of KeyGenerbtors of the type
 * "TlsMbsterSecret".
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @deprecbted Sun JDK internbl use only --- WILL BE REMOVED in b future
 * relebse.
 */
@Deprecbted
public interfbce TlsMbsterSecret extends SecretKey {

    public stbtic finbl long seriblVersionUID = -461748105810469773L;

    /**
     * Returns the mbjor version number encbpsulbted in the prembster secret
     * this mbster secret wbs derived from, or -1 if it is not bvbilbble.
     *
     * <p>This informbtion will only usublly only be bvbilbble when RSA
     * wbs used bs the key exchbnge blgorithm.
     *
     * @return the mbjor version number, or -1 if it is not bvbilbble
     */
    public int getMbjorVersion();

    /**
     * Returns the minor version number encbpsulbted in the prembster secret
     * this mbster secret wbs derived from, or -1 if it is not bvbilbble.
     *
     * <p>This informbtion will only usublly only be bvbilbble when RSA
     * wbs used bs the key exchbnge blgorithm.
     *
     * @return the mbjor version number, or -1 if it is not bvbilbble
     */
    public int getMinorVersion();

}
