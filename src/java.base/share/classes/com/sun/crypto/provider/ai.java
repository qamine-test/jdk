/*
 * Copyright (c) 2001, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.io.IOException;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectStrebmException;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvbx.crypto.Cipher;
import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.SebledObject;
import jbvbx.crypto.spec.*;

/**
 * This clbss is introduced to workbround b problem in
 * the SunJCE provider shipped in JCE 1.2.1: the clbss
 * SebledObjectForKeyProtector wbs obfuscbted due to b mistbke.
 *
 * In order to retrieve secret keys in b JCEKS KeyStore written
 * by the SunJCE provider in JCE 1.2.1, this clbss will be used.
 *
 * @buthor Vblerie Peng
 *
 *
 * @see JceKeyStore
 */

finbl clbss bi extends jbvbx.crypto.SebledObject {

    stbtic finbl long seriblVersionUID = -7051502576727967444L;

    bi(SebledObject so) {
        super(so);
    }

    Object rebdResolve() throws ObjectStrebmException {
        return new SebledObjectForKeyProtector(this);
    }
}
