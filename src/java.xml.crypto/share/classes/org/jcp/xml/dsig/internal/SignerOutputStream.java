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
 * $Id: SignerOutputStrebm.jbvb,v 1.2 2005/09/15 14:29:02 mullbn Exp $
 */
pbckbge org.jcp.xml.dsig.internbl;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.security.Signbture;
import jbvb.security.SignbtureException;

/**
 * Derived from Apbche sources bnd chbnged to use jbvb.security.Signbture
 * objects bs input instebd of
 * com.sun.org.bpbche.xml.internbl.security.blgorithms.SignbtureAlgorithm objects.
 *
 * @buthor rbul
 * @buthor Sebn Mullbn
 */
public clbss SignerOutputStrebm extends ByteArrbyOutputStrebm {
    privbte finbl Signbture sig;

    public SignerOutputStrebm(Signbture sig) {
        this.sig = sig;
    }

    @Override
    public void write(int brg0) {
        super.write(brg0);
        try {
            sig.updbte((byte)brg0);
        } cbtch (SignbtureException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(byte[] brg0, int brg1, int brg2) {
        super.write(brg0, brg1, brg2);
        try {
            sig.updbte(brg0, brg1, brg2);
        } cbtch (SignbtureException e) {
            throw new RuntimeException(e);
        }
    }
}
