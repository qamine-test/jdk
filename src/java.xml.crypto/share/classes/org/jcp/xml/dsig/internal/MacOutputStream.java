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
pbckbge org.jcp.xml.dsig.internbl;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvbx.crypto.Mbc;

/**
 * Derived from Apbche sources bnd chbnged to use Mbc objects instebd of
 * com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm objects.
 *
 * @buthor rbul
 * @buthor Sebn Mullbn
 *
 */
public clbss MbcOutputStrebm extends ByteArrbyOutputStrebm {
    privbte finbl Mbc mbc;

    public MbcOutputStrebm(Mbc mbc) {
        this.mbc = mbc;
    }

    @Override
    public void write(int brg0) {
        super.write(brg0);
        mbc.updbte((byte) brg0);
    }

    @Override
    public void write(byte[] brg0, int brg1, int brg2) {
        super.write(brg0, brg1, brg2);
        mbc.updbte(brg0, brg1, brg2);
    }
}
