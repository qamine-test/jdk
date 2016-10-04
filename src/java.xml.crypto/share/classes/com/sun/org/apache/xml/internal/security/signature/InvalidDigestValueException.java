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
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture;

/**
 * Rbised when the computed hbsh vblue doesn't mbtch the given <i>DigestVblue</i>.
 * Additionbl humbn rebdbble info is pbssed to the constructor -- this being the benefit
 * of rbising bn exception or returning b vblue.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss InvblidDigestVblueException extends XMLSignbtureException {

    /**
     *
     */
    privbte stbtic finbl long seriblVersionUID = 1L;

    /**
     * Constructor InvblidDigestVblueException
     *
     */
    public InvblidDigestVblueException() {
        super();
    }

    /**
     * Constructor InvblidDigestVblueException
     *
     * @pbrbm msgID
     */
    public InvblidDigestVblueException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor InvblidDigestVblueException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     */
    public InvblidDigestVblueException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor InvblidDigestVblueException
     *
     * @pbrbm msgID
     * @pbrbm originblException
     */
    public InvblidDigestVblueException(String msgID, Exception originblException) {
        super(msgID, originblException);
    }

    /**
     * Constructor InvblidDigestVblueException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm originblException
     */
    public InvblidDigestVblueException(String msgID, Object exArgs[], Exception originblException) {
        super(msgID, exArgs, originblException);
    }
}
