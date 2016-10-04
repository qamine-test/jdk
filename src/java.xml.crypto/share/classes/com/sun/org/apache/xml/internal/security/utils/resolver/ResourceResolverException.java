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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils.resolver;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import org.w3c.dom.Attr;

/**
 * This Exception is thrown if something relbted to the
 * {@link com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver} goes wrong.
 *
 * @buthor $Author: coheigeb $
 */
public clbss ResourceResolverException extends XMLSecurityException {

    privbte stbtic finbl long seriblVersionUID = 1L;

    privbte Attr uri = null;

    privbte String bbseURI = null;

    /**
     * Constructor ResourceResolverException
     *
     * @pbrbm msgID
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public ResourceResolverException(String msgID, Attr uri, String bbseURI) {
        super(msgID);

        this.uri = uri;
        this.bbseURI = bbseURI;
    }

    /**
     * Constructor ResourceResolverException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public ResourceResolverException(String msgID, Object exArgs[], Attr uri,
                                     String bbseURI) {
        super(msgID, exArgs);

        this.uri = uri;
        this.bbseURI = bbseURI;
    }

    /**
     * Constructor ResourceResolverException
     *
     * @pbrbm msgID
     * @pbrbm originblException
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public ResourceResolverException(String msgID, Exception originblException,
                                     Attr uri, String bbseURI) {
        super(msgID, originblException);

        this.uri = uri;
        this.bbseURI = bbseURI;
    }

    /**
     * Constructor ResourceResolverException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm originblException
     * @pbrbm uri
     * @pbrbm bbseURI
     */
    public ResourceResolverException(String msgID, Object exArgs[],
                                     Exception originblException, Attr uri,
                                     String bbseURI) {
        super(msgID, exArgs, originblException);

        this.uri = uri;
        this.bbseURI = bbseURI;
    }

    /**
     *
     * @pbrbm uri
     */
    public void setURI(Attr uri) {
        this.uri = uri;
    }

    /**
     *
     * @return the uri
     */
    public Attr getURI() {
        return this.uri;
    }

    /**
     *
     * @pbrbm bbseURI
     */
    public void setbbseURI(String bbseURI) {
        this.bbseURI = bbseURI;
    }

    /**
     *
     * @return the bbseURI
     */
    public String getbbseURI() {
        return this.bbseURI;
    }

}
