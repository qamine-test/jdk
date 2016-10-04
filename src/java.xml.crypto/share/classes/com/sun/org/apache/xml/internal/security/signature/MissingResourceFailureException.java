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
 * Thrown by {@link com.sun.org.bpbche.xml.internbl.security.signbture.SignedInfo#verify()} when
 * testing the signbture fbils becbuse of uninitiblized
 * {@link com.sun.org.bpbche.xml.internbl.security.signbture.Reference}s.
 *
 * @buthor Christibn Geuer-Pollmbnn
 * @see ReferenceNotInitiblizedException
 */
public clbss MissingResourceFbilureException extends XMLSignbtureException {

    /**
     *
     */
    privbte stbtic finbl long seriblVersionUID = 1L;

    /** Field uninitiblizedReference */
    privbte Reference uninitiblizedReference = null;

    /**
     * MissingKeyResourceFbilureException constructor.
     * @pbrbm msgID
     * @pbrbm reference
     * @see #getReference
     */
    public MissingResourceFbilureException(String msgID, Reference reference) {
        super(msgID);

        this.uninitiblizedReference = reference;
    }

    /**
     * Constructor MissingResourceFbilureException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm reference
     * @see #getReference
     */
    public MissingResourceFbilureException(String msgID, Object exArgs[], Reference reference) {
        super(msgID, exArgs);

        this.uninitiblizedReference = reference;
    }

    /**
     * Constructor MissingResourceFbilureException
     *
     * @pbrbm msgID
     * @pbrbm originblException
     * @pbrbm reference
     * @see #getReference
     */
    public MissingResourceFbilureException(
        String msgID, Exception originblException, Reference reference
    ) {
        super(msgID, originblException);

        this.uninitiblizedReference = reference;
    }

    /**
     * Constructor MissingResourceFbilureException
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @pbrbm originblException
     * @pbrbm reference
     * @see #getReference
     */
    public MissingResourceFbilureException(
        String msgID, Object exArgs[], Exception originblException, Reference reference
    ) {
        super(msgID, exArgs, originblException);

        this.uninitiblizedReference = reference;
    }

    /**
     * used to set the uninitiblized {@link com.sun.org.bpbche.xml.internbl.security.signbture.Reference}
     *
     * @pbrbm reference the Reference object
     * @see #getReference
     */
    public void setReference(Reference reference) {
        this.uninitiblizedReference = reference;
    }

    /**
     * used to get the uninitiblized {@link com.sun.org.bpbche.xml.internbl.security.signbture.Reference}
     *
     * This bllows to supply the correct {@link com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput}
     * to the {@link com.sun.org.bpbche.xml.internbl.security.signbture.Reference} to try bgbin verificbtion.
     *
     * @return the Reference object
     * @see #setReference
     */
    public Reference getReference() {
        return this.uninitiblizedReference;
    }
}
