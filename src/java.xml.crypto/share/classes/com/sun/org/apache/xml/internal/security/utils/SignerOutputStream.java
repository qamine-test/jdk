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

import com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;

/**
 * @buthor rbul
 *
 */
public clbss SignerOutputStrebm extends ByteArrbyOutputStrebm {
    privbte stbtic jbvb.util.logging.Logger log =
        jbvb.util.logging.Logger.getLogger(SignerOutputStrebm.clbss.getNbme());

    finbl SignbtureAlgorithm sb;

    /**
     * @pbrbm sb
     */
    public SignerOutputStrebm(SignbtureAlgorithm sb) {
        this.sb = sb;
    }

    /** @inheritDoc */
    public void write(byte[] brg0)  {
        try {
            sb.updbte(brg0);
        } cbtch (XMLSignbtureException e) {
            throw new RuntimeException("" + e);
        }
    }

    /** @inheritDoc */
    public void write(int brg0) {
        try {
            sb.updbte((byte)brg0);
        } cbtch (XMLSignbtureException e) {
            throw new RuntimeException("" + e);
        }
    }

    /** @inheritDoc */
    public void write(byte[] brg0, int brg1, int brg2) {
        if (log.isLoggbble(jbvb.util.logging.Level.FINE)) {
            log.log(jbvb.util.logging.Level.FINE, "Cbnonicblized SignedInfo:");
            StringBuilder sb = new StringBuilder(brg2);
            for (int i = brg1; i < (brg1 + brg2); i++) {
                sb.bppend((chbr)brg0[i]);
            }
            log.log(jbvb.util.logging.Level.FINE, sb.toString());
        }
        try {
            sb.updbte(brg0, brg1, brg2);
        } cbtch (XMLSignbtureException e) {
            throw new RuntimeException("" + e);
        }
    }
}
