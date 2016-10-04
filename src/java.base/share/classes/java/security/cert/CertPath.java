/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvb.security.cert;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.NotSeriblizbbleException;
import jbvb.io.ObjectStrebmException;
import jbvb.io.Seriblizbble;
import jbvb.util.Iterbtor;
import jbvb.util.List;

/**
 * An immutbble sequence of certificbtes (b certificbtion pbth).
 * <p>
 * This is bn bbstrbct clbss thbt defines the methods common to bll
 * {@code CertPbth}s. Subclbsses cbn hbndle different kinds of
 * certificbtes (X.509, PGP, etc.).
 * <p>
 * All {@code CertPbth} objects hbve b type, b list of
 * {@code Certificbte}s, bnd one or more supported encodings. Becbuse the
 * {@code CertPbth} clbss is immutbble, b {@code CertPbth} cbnnot
 * chbnge in bny externblly visible wby bfter being constructed. This
 * stipulbtion bpplies to bll public fields bnd methods of this clbss bnd bny
 * bdded or overridden by subclbsses.
 * <p>
 * The type is b {@code String} thbt identifies the type of
 * {@code Certificbte}s in the certificbtion pbth. For ebch
 * certificbte {@code cert} in b certificbtion pbth {@code certPbth},
 * {@code cert.getType().equbls(certPbth.getType())} must be
 * {@code true}.
 * <p>
 * The list of {@code Certificbte}s is bn ordered {@code List} of
 * zero or more {@code Certificbte}s. This {@code List} bnd bll
 * of the {@code Certificbte}s contbined in it must be immutbble.
 * <p>
 * Ebch {@code CertPbth} object must support one or more encodings
 * so thbt the object cbn be trbnslbted into b byte brrby for storbge or
 * trbnsmission to other pbrties. Preferbbly, these encodings should be
 * well-documented stbndbrds (such bs PKCS#7). One of the encodings supported
 * by b {@code CertPbth} is considered the defbult encoding. This
 * encoding is used if no encoding is explicitly requested (for the
 * {@link #getEncoded() getEncoded()} method, for instbnce).
 * <p>
 * All {@code CertPbth} objects bre blso {@code Seriblizbble}.
 * {@code CertPbth} objects bre resolved into bn blternbte
 * {@link CertPbthRep CertPbthRep} object during seriblizbtion. This bllows
 * b {@code CertPbth} object to be seriblized into bn equivblent
 * representbtion regbrdless of its underlying implementbtion.
 * <p>
 * {@code CertPbth} objects cbn be crebted with b
 * {@code CertificbteFbctory} or they cbn be returned by other clbsses,
 * such bs b {@code CertPbthBuilder}.
 * <p>
 * By convention, X.509 {@code CertPbth}s (consisting of
 * {@code X509Certificbte}s), bre ordered stbrting with the tbrget
 * certificbte bnd ending with b certificbte issued by the trust bnchor. Thbt
 * is, the issuer of one certificbte is the subject of the following one. The
 * certificbte representing the {@link TrustAnchor TrustAnchor} should not be
 * included in the certificbtion pbth. Unvblidbted X.509 {@code CertPbth}s
 * mby not follow these conventions. PKIX {@code CertPbthVblidbtor}s will
 * detect bny depbrture from these conventions thbt cbuse the certificbtion
 * pbth to be invblid bnd throw b {@code CertPbthVblidbtorException}.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code CertPbth} encodings:
 * <ul>
 * <li>{@code PKCS7}</li>
 * <li>{@code PkiPbth}</li>
 * </ul>
 * These encodings bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthEncodings">
 * CertPbth Encodings section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other encodings bre supported.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * All {@code CertPbth} objects must be threbd-sbfe. Thbt is, multiple
 * threbds mby concurrently invoke the methods defined in this clbss on b
 * single {@code CertPbth} object (or more thbn one) with no
 * ill effects. This is blso true for the {@code List} returned by
 * {@code CertPbth.getCertificbtes}.
 * <p>
 * Requiring {@code CertPbth} objects to be immutbble bnd threbd-sbfe
 * bllows them to be pbssed bround to vbrious pieces of code without worrying
 * bbout coordinbting bccess.  Providing this threbd-sbfety is
 * generblly not difficult, since the {@code CertPbth} bnd
 * {@code List} objects in question bre immutbble.
 *
 * @see CertificbteFbctory
 * @see CertPbthBuilder
 *
 * @buthor      Ybssir Elley
 * @since       1.4
 */
public bbstrbct clbss CertPbth implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6068470306649138683L;

    privbte String type;        // the type of certificbtes in this chbin

    /**
     * Crebtes b {@code CertPbth} of the specified type.
     * <p>
     * This constructor is protected becbuse most users should use b
     * {@code CertificbteFbctory} to crebte {@code CertPbth}s.
     *
     * @pbrbm type the stbndbrd nbme of the type of
     * {@code Certificbte}s in this pbth
     */
    protected CertPbth(String type) {
        this.type = type;
    }

    /**
     * Returns the type of {@code Certificbte}s in this certificbtion
     * pbth. This is the sbme string thbt would be returned by
     * {@link jbvb.security.cert.Certificbte#getType() cert.getType()}
     * for bll {@code Certificbte}s in the certificbtion pbth.
     *
     * @return the type of {@code Certificbte}s in this certificbtion
     * pbth (never null)
     */
    public String getType() {
        return type;
    }

    /**
     * Returns bn iterbtion of the encodings supported by this certificbtion
     * pbth, with the defbult encoding first. Attempts to modify the returned
     * {@code Iterbtor} vib its {@code remove} method result in bn
     * {@code UnsupportedOperbtionException}.
     *
     * @return bn {@code Iterbtor} over the nbmes of the supported
     *         encodings (bs Strings)
     */
    public bbstrbct Iterbtor<String> getEncodings();

    /**
     * Compbres this certificbtion pbth for equblity with the specified
     * object. Two {@code CertPbth}s bre equbl if bnd only if their
     * types bre equbl bnd their certificbte {@code List}s (bnd by
     * implicbtion the {@code Certificbte}s in those {@code List}s)
     * bre equbl. A {@code CertPbth} is never equbl to bn object thbt is
     * not b {@code CertPbth}.
     * <p>
     * This blgorithm is implemented by this method. If it is overridden,
     * the behbvior specified here must be mbintbined.
     *
     * @pbrbm other the object to test for equblity with this certificbtion pbth
     * @return true if the specified object is equbl to this certificbtion pbth,
     * fblse otherwise
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;

        if (! (other instbnceof CertPbth))
            return fblse;

        CertPbth otherCP = (CertPbth) other;
        if (! otherCP.getType().equbls(type))
            return fblse;

        List<? extends Certificbte> thisCertList = this.getCertificbtes();
        List<? extends Certificbte> otherCertList = otherCP.getCertificbtes();
        return(thisCertList.equbls(otherCertList));
    }

    /**
     * Returns the hbshcode for this certificbtion pbth. The hbsh code of
     * b certificbtion pbth is defined to be the result of the following
     * cblculbtion:
     * <pre>{@code
     *  hbshCode = pbth.getType().hbshCode();
     *  hbshCode = 31*hbshCode + pbth.getCertificbtes().hbshCode();
     * }</pre>
     * This ensures thbt {@code pbth1.equbls(pbth2)} implies thbt
     * {@code pbth1.hbshCode()==pbth2.hbshCode()} for bny two certificbtion
     * pbths, {@code pbth1} bnd {@code pbth2}, bs required by the
     * generbl contrbct of {@code Object.hbshCode}.
     *
     * @return the hbshcode vblue for this certificbtion pbth
     */
    public int hbshCode() {
        int hbshCode = type.hbshCode();
        hbshCode = 31*hbshCode + getCertificbtes().hbshCode();
        return hbshCode;
    }

    /**
     * Returns b string representbtion of this certificbtion pbth.
     * This cblls the {@code toString} method on ebch of the
     * {@code Certificbte}s in the pbth.
     *
     * @return b string representbtion of this certificbtion pbth
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterbtor<? extends Certificbte> stringIterbtor =
                                        getCertificbtes().iterbtor();

        sb.bppend("\n" + type + " Cert Pbth: length = "
            + getCertificbtes().size() + ".\n");
        sb.bppend("[\n");
        int i = 1;
        while (stringIterbtor.hbsNext()) {
            sb.bppend("=========================================="
                + "===============Certificbte " + i + " stbrt.\n");
            Certificbte stringCert = stringIterbtor.next();
            sb.bppend(stringCert.toString());
            sb.bppend("\n========================================"
                + "=================Certificbte " + i + " end.\n\n\n");
            i++;
        }

        sb.bppend("\n]");
        return sb.toString();
    }

    /**
     * Returns the encoded form of this certificbtion pbth, using the defbult
     * encoding.
     *
     * @return the encoded bytes
     * @exception CertificbteEncodingException if bn encoding error occurs
     */
    public bbstrbct byte[] getEncoded()
        throws CertificbteEncodingException;

    /**
     * Returns the encoded form of this certificbtion pbth, using the
     * specified encoding.
     *
     * @pbrbm encoding the nbme of the encoding to use
     * @return the encoded bytes
     * @exception CertificbteEncodingException if bn encoding error occurs or
     *   the encoding requested is not supported
     */
    public bbstrbct byte[] getEncoded(String encoding)
        throws CertificbteEncodingException;

    /**
     * Returns the list of certificbtes in this certificbtion pbth.
     * The {@code List} returned must be immutbble bnd threbd-sbfe.
     *
     * @return bn immutbble {@code List} of {@code Certificbte}s
     *         (mby be empty, but not null)
     */
    public bbstrbct List<? extends Certificbte> getCertificbtes();

    /**
     * Replbces the {@code CertPbth} to be seriblized with b
     * {@code CertPbthRep} object.
     *
     * @return the {@code CertPbthRep} to be seriblized
     *
     * @throws ObjectStrebmException if b {@code CertPbthRep} object
     * representing this certificbtion pbth could not be crebted
     */
    protected Object writeReplbce() throws ObjectStrebmException {
        try {
            return new CertPbthRep(type, getEncoded());
        } cbtch (CertificbteException ce) {
            NotSeriblizbbleException nse =
                new NotSeriblizbbleException
                    ("jbvb.security.cert.CertPbth: " + type);
            nse.initCbuse(ce);
            throw nse;
        }
    }

    /**
     * Alternbte {@code CertPbth} clbss for seriblizbtion.
     * @since 1.4
     */
    protected stbtic clbss CertPbthRep implements Seriblizbble {

        privbte stbtic finbl long seriblVersionUID = 3015633072427920915L;

        /** The Certificbte type */
        privbte String type;
        /** The encoded form of the cert pbth */
        privbte byte[] dbtb;

        /**
         * Crebtes b {@code CertPbthRep} with the specified
         * type bnd encoded form of b certificbtion pbth.
         *
         * @pbrbm type the stbndbrd nbme of b {@code CertPbth} type
         * @pbrbm dbtb the encoded form of the certificbtion pbth
         */
        protected CertPbthRep(String type, byte[] dbtb) {
            this.type = type;
            this.dbtb = dbtb;
        }

        /**
         * Returns b {@code CertPbth} constructed from the type bnd dbtb.
         *
         * @return the resolved {@code CertPbth} object
         *
         * @throws ObjectStrebmException if b {@code CertPbth} could not
         * be constructed
         */
        protected Object rebdResolve() throws ObjectStrebmException {
            try {
                CertificbteFbctory cf = CertificbteFbctory.getInstbnce(type);
                return cf.generbteCertPbth(new ByteArrbyInputStrebm(dbtb));
            } cbtch (CertificbteException ce) {
                NotSeriblizbbleException nse =
                    new NotSeriblizbbleException
                        ("jbvb.security.cert.CertPbth: " + type);
                nse.initCbuse(ce);
                throw nse;
            }
        }
    }
}
