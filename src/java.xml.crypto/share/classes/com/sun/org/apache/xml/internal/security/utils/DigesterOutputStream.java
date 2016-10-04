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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import jbvb.io.ByteArrbyOutputStrebm;

import com.sun.org.bpbche.xml.internbl.security.blgorithms.MessbgeDigestAlgorithm;

/**
 * @buthor rbul
 *
 */
public clbss DigesterOutputStrebm extends ByteArrbyOutputStrebm {
    privbte stbtic finbl jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(DigesterOutputStrebm.clbss.getNbme());

    finbl MessbgeDigestAlgorithm mdb;

    /**
     * @pbrbm mdb
     */
    public DigesterOutputStrebm(MessbgeDigestAlgorithm mdb) {
        this.mdb = mdb;
    }

    /** @inheritDoc */
    public void write(byte[] brg0) {
        write(brg0, 0, brg0.length);
    }

    /** @inheritDoc */
    public void write(int brg0) {
        mdb.updbte((byte)brg0);
    }

    /** @inheritDoc */
    public void write(byte[] brg0, int brg1, int brg2) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Pre-digested input:");
            StringBuilder sb = new StringBuilder(brg2);
            for (int i = brg1; i < (brg1 + brg2); i++) {
                sb.bppend((chbr)brg0[i]);
            }
            log.log(jbvb.util.logging.Level.FINE, sb.toString());
        }
        mdb.updbte(brg0, brg1, brg2);
    }

    /**
     * @return the digest vblue
     */
    public byte[] getDigestVblue() {
        return mdb.digest();
    }
}
