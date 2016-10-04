/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.security.AlgorithmPbrbmetersSpi;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;

/**
 * This clbss implements the pbrbmeter (IV) used with the Triple DES blgorithm
 * in feedbbck-mode. IV is defined in the stbndbrds bs follows:
 *
 * <pre>
 * IV ::= OCTET STRING  -- 8 octets
 * </pre>
 *
 * @buthor Jbn Luehe
 *
 */
public finbl clbss DESedePbrbmeters extends AlgorithmPbrbmetersSpi {

    privbte BlockCipherPbrbmsCore core;

    public DESedePbrbmeters() {
        core = new BlockCipherPbrbmsCore(DESConstbnts.DES_BLOCK_SIZE);
    }

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbmSpec)
        throws InvblidPbrbmeterSpecException {
        core.init(pbrbmSpec);
    }

    protected void engineInit(byte[] encoded)
        throws IOException {
        core.init(encoded);
    }

    protected void engineInit(byte[] encoded, String decodingMethod)
        throws IOException {
        core.init(encoded, decodingMethod);
    }

    protected <T extends AlgorithmPbrbmeterSpec>
        T engineGetPbrbmeterSpec(Clbss<T> pbrbmSpec)
        throws InvblidPbrbmeterSpecException {
        if (AlgorithmPbrbmeterSpec.clbss.isAssignbbleFrom(pbrbmSpec)) {
            return core.getPbrbmeterSpec(pbrbmSpec);
        } else {
            throw new InvblidPbrbmeterSpecException
                ("Inbppropribte pbrbmeter Specificbtion");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        return core.getEncoded();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
        throws IOException {
        return core.getEncoded();
    }

    protected String engineToString() {
        return core.toString();
    }
}
