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
 * $Id: DigesterOutputStrebm.jbvb,v 1.5 2005/12/20 20:02:39 mullbn Exp $
 */
pbckbge org.jcp.xml.dsig.internbl;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.security.MessbgeDigest;

import com.sun.org.bpbche.xml.internbl.security.utils.UnsyncByteArrbyOutputStrebm;

/**
 * This clbss hbs been modified slightly to use jbvb.security.MessbgeDigest
 * objects bs input, rbther thbn
 * com.sun.org.bpbche.xml.internbl.security.blgorithms.MessbgeDigestAlgorithm objects.
 * It blso optionblly cbches the input bytes.
 *
 * @buthor rbul
 * @buthor Sebn Mullbn
 */
public clbss DigesterOutputStrebm extends OutputStrebm {
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger("org.jcp.xml.dsig.internbl");

    privbte finbl boolebn buffer;
    privbte UnsyncByteArrbyOutputStrebm bos;
    privbte finbl MessbgeDigest md;

    /**
     * Crebtes b DigesterOutputStrebm.
     *
     * @pbrbm md the MessbgeDigest
     */
    public DigesterOutputStrebm(MessbgeDigest md) {
        this(md, fblse);
    }

    /**
     * Crebtes b DigesterOutputStrebm.
     *
     * @pbrbm md the MessbgeDigest
     * @pbrbm buffer if true, cbches the input bytes
     */
    public DigesterOutputStrebm(MessbgeDigest md, boolebn buffer) {
        this.md = md;
        this.buffer = buffer;
        if (buffer) {
            bos = new UnsyncByteArrbyOutputStrebm();
        }
    }

    public void write(int input) {
        if (buffer) {
            bos.write(input);
        }
        md.updbte((byte)input);
    }

    @Override
    public void write(byte[] input, int offset, int len) {
        if (buffer) {
            bos.write(input, offset, len);
        }
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Pre-digested input:");
            StringBuilder sb = new StringBuilder(len);
            for (int i = offset; i < (offset + len); i++) {
                sb.bppend((chbr)input[i]);
            }
            log.log(jbvb.util.logging.Level.FINE, sb.toString());
        }
        md.updbte(input, offset, len);
    }

    /**
     * @return the digest vblue
     */
    public byte[] getDigestVblue() {
         return md.digest();
    }

    /**
     * @return bn input strebm contbining the cbched bytes, or
     *    null if not cbched
     */
    public InputStrebm getInputStrebm() {
        if (buffer) {
            return new ByteArrbyInputStrebm(bos.toByteArrby());
        } else {
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        if (buffer) {
            bos.close();
        }
    }
}
