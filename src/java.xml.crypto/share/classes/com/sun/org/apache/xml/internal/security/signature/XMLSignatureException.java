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

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;

/**
 * All XML Signbture relbted exceptions inherit herefrom.
 *
 * @see MissingResourceFbilureException InvblidDigestVblueException InvblidSignbtureVblueException
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss XMLSignbtureException extends XMLSecurityException {

    /**
     *
     */
    privbte stbtic finbl long seriblVersionUID = 1L;

    /**
     * Constructor XMLSignbtureException
     *
     */
    public XMLSignbtureException() {
        super();
    }

    /**
     * Constructor XMLSignbtureException
     *
     * @pbrbm msgID
     */
    public XMLSignbtureException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor XMLSignbtureException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     */
    public XMLSignbtureException(String msgID, Object exArgs[]) {
        super(msgID, exArgs);
    }

    /**
     * Constructor XMLSignbtureException
     *
     * @pbrbm msgID
     * @pbrbm originblException
     */
    public XMLSignbtureException(String msgID, Exception originblException) {
        super(msgID, originblException);
    }

    /**
     * Constructor XMLSignbtureException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm originblException
     */
    public XMLSignbtureException(String msgID, Object exArgs[], Exception originblException) {
        super(msgID, exArgs, originblException);
    }
}
