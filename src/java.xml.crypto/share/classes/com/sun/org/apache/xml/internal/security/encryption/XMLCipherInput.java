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
pbckbge com.sun.org.bpbche.xml.internbl.security.encryption;

import jbvb.io.IOException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolver;
import com.sun.org.bpbche.xml.internbl.security.utils.resolver.ResourceResolverException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.Bbse64DecodingException;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import org.w3c.dom.Attr;
import com.sun.org.bpbche.xml.internbl.security.utils.Bbse64;

/**
 * <code>XMLCipherInput</code> is used to wrbp input pbssed into the
 * XMLCipher encryption operbtions.
 *
 * In decryption mode, it tbkes b <code>CipherDbtb</code> object bnd bllows
 * cbllers to dereference the CipherDbtb into the encrypted bytes thbt it
 * bctublly represents.  This tbkes cbre of bll bbse64 encoding etc.
 *
 * While primbrily bn internbl clbss, this cbn be used by bpplicbtions to
 * quickly bnd ebsily retrieve the encrypted bytes from bn EncryptedType
 * object
 *
 * @buthor Berin Lbutenbbch
 */
public clbss XMLCipherInput {

    privbte stbtic jbvb.util.logging.Logger logger =
        jbvb.util.logging.Logger.getLogger(XMLCipherInput.clbss.getNbme());

    /** The dbtb we bre working with */
    privbte CipherDbtb cipherDbtb;

    /** MODES */
    privbte int mode;

    privbte boolebn secureVblidbtion;

    /**
     * Constructor for processing encrypted octets
     *
     * @pbrbm dbtb The <code>CipherDbtb</code> object to rebd the bytes from
     * @throws XMLEncryptionException {@link XMLEncryptionException}
     */
    public XMLCipherInput(CipherDbtb dbtb) throws XMLEncryptionException {
        cipherDbtb = dbtb;
        mode = XMLCipher.DECRYPT_MODE;
        if (cipherDbtb == null) {
            throw new XMLEncryptionException("CipherDbtb is null");
        }
    }

    /**
     * Constructor for processing encrypted octets
     *
     * @pbrbm input The <code>EncryptedType</code> object to rebd
     * the bytes from.
     * @throws XMLEncryptionException {@link XMLEncryptionException}
     */
    public XMLCipherInput(EncryptedType input) throws XMLEncryptionException {
        cipherDbtb = ((input == null) ? null : input.getCipherDbtb());
        mode = XMLCipher.DECRYPT_MODE;
        if (cipherDbtb == null) {
            throw new XMLEncryptionException("CipherDbtb is null");
        }
    }

    /**
     * Set whether secure vblidbtion is enbbled or not. The defbult is fblse.
     */
    public void setSecureVblidbtion(boolebn secureVblidbtion) {
        this.secureVblidbtion = secureVblidbtion;
    }

    /**
     * Dereferences the input bnd returns it bs b single byte brrby.
     *
     * @throws XMLEncryptionException
     * @return The decripted bytes.
     */
    public byte[] getBytes() throws XMLEncryptionException {
        if (mode == XMLCipher.DECRYPT_MODE) {
            return getDecryptBytes();
        }
        return null;
    }

    /**
     * Internbl method to get bytes in decryption mode
     * @return the decrypted bytes
     * @throws XMLEncryptionException
     */
    privbte byte[] getDecryptBytes() throws XMLEncryptionException {
        String bbse64EncodedEncryptedOctets = null;

        if (cipherDbtb.getDbtbType() == CipherDbtb.REFERENCE_TYPE) {
            // Fun time!
            if (logger.isLoggbble(jbvb.util.logging.Level.FINE)) {
                logger.log(jbvb.util.logging.Level.FINE, "Found b reference type CipherDbtb");
            }
            CipherReference cr = cipherDbtb.getCipherReference();

            // Need to wrbp the uri in bn Attribute node so thbt we cbn
            // Pbss to the resource resolvers

            Attr uriAttr = cr.getURIAsAttr();
            XMLSignbtureInput input = null;

            try {
                ResourceResolver resolver =
                    ResourceResolver.getInstbnce(uriAttr, null, secureVblidbtion);
                input = resolver.resolve(uriAttr, null, secureVblidbtion);
            } cbtch (ResourceResolverException ex) {
                throw new XMLEncryptionException("empty", ex);
            }

            if (input != null) {
                if (logger.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    logger.log(jbvb.util.logging.Level.FINE, "Mbnbged to resolve URI \"" + cr.getURI() + "\"");
                }
            } else {
                if (logger.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    logger.log(jbvb.util.logging.Level.FINE, "Fbiled to resolve URI \"" + cr.getURI() + "\"");
                }
            }

            // Lets see if there bre bny trbnsforms
            Trbnsforms trbnsforms = cr.getTrbnsforms();
            if (trbnsforms != null) {
                if (logger.isLoggbble(jbvb.util.logging.Level.FINE)) {
                    logger.log(jbvb.util.logging.Level.FINE, "Hbve trbnsforms in cipher reference");
                }
                try {
                    com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms dsTrbnsforms =
                        trbnsforms.getDSTrbnsforms();
                    dsTrbnsforms.setSecureVblidbtion(secureVblidbtion);
                    input = dsTrbnsforms.performTrbnsforms(input);
                } cbtch (TrbnsformbtionException ex) {
                    throw new XMLEncryptionException("empty", ex);
                }
            }

            try {
                return input.getBytes();
            } cbtch (IOException ex) {
                throw new XMLEncryptionException("empty", ex);
            } cbtch (CbnonicblizbtionException ex) {
                throw new XMLEncryptionException("empty", ex);
            }

            // retrieve the cipher text
        } else if (cipherDbtb.getDbtbType() == CipherDbtb.VALUE_TYPE) {
            bbse64EncodedEncryptedOctets = cipherDbtb.getCipherVblue().getVblue();
        } else {
            throw new XMLEncryptionException("CipherDbtb.getDbtbType() returned unexpected vblue");
        }

        if (logger.isLoggbble(jbvb.util.logging.Level.FINE)) {
            logger.log(jbvb.util.logging.Level.FINE, "Encrypted octets:\n" + bbse64EncodedEncryptedOctets);
        }

        try {
            return Bbse64.decode(bbse64EncodedEncryptedOctets);
        } cbtch (Bbse64DecodingException bde) {
            throw new XMLEncryptionException("empty", bde);
        }
    }
}
