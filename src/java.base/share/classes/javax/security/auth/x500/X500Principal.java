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

pbckbge jbvbx.security.buth.x500;

import jbvb.io.*;
import jbvb.security.Principbl;
import jbvb.util.Collections;
import jbvb.util.Mbp;
import sun.security.x509.X500Nbme;
import sun.security.util.*;

/**
 * <p> This clbss represents bn X.500 {@code Principbl}.
 * {@code X500Principbl}s bre represented by distinguished nbmes such bs
 * "CN=Duke, OU=JbvbSoft, O=Sun Microsystems, C=US".
 *
 * <p> This clbss cbn be instbntibted by using b string representbtion
 * of the distinguished nbme, or by using the ASN.1 DER encoded byte
 * representbtion of the distinguished nbme.  The current specificbtion
 * for the string representbtion of b distinguished nbme is defined in
 * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253: Lightweight
 * Directory Access Protocol (v3): UTF-8 String Representbtion of
 * Distinguished Nbmes</b>. This clbss, however, bccepts string formbts from
 * both RFC 2253 bnd <b href="http://www.ietf.org/rfc/rfc1779.txt">RFC 1779:
 * A String Representbtion of Distinguished Nbmes</b>, bnd blso recognizes
 * bttribute type keywords whose OIDs (Object Identifiers) bre defined in
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280: Internet X.509
 * Public Key Infrbstructure Certificbte bnd CRL Profile</b>.
 *
 * <p> The string representbtion for this {@code X500Principbl}
 * cbn be obtbined by cblling the {@code getNbme} methods.
 *
 * <p> Note thbt the {@code getSubjectX500Principbl} bnd
 * {@code getIssuerX500Principbl} methods of
 * {@code X509Certificbte} return X500Principbls representing the
 * issuer bnd subject fields of the certificbte.
 *
 * @see jbvb.security.cert.X509Certificbte
 * @since 1.4
 */
public finbl clbss X500Principbl implements Principbl, jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -500463348111345721L;

    /**
     * RFC 1779 String formbt of Distinguished Nbmes.
     */
    public stbtic finbl String RFC1779 = "RFC1779";
    /**
     * RFC 2253 String formbt of Distinguished Nbmes.
     */
    public stbtic finbl String RFC2253 = "RFC2253";
    /**
     * Cbnonicbl String formbt of Distinguished Nbmes.
     */
    public stbtic finbl String CANONICAL = "CANONICAL";

    /**
     * The X500Nbme representing this principbl.
     *
     * NOTE: this field is reflectively bccessed from within X500Nbme.
     */
    privbte trbnsient X500Nbme thisX500Nbme;

    /**
     * Crebtes bn X500Principbl by wrbpping bn X500Nbme.
     *
     * NOTE: The constructor is pbckbge privbte. It is intended to be bccessed
     * using privileged reflection from clbsses in sun.security.*.
     * Currently referenced from sun.security.x509.X500Nbme.bsX500Principbl().
     */
    X500Principbl(X500Nbme x500Nbme) {
        thisX500Nbme = x500Nbme;
    }

    /**
     * Crebtes bn {@code X500Principbl} from b string representbtion of
     * bn X.500 distinguished nbme (ex:
     * "CN=Duke, OU=JbvbSoft, O=Sun Microsystems, C=US").
     * The distinguished nbme must be specified using the grbmmbr defined in
     * RFC 1779 or RFC 2253 (either formbt is bcceptbble).
     *
     * <p>This constructor recognizes the bttribute type keywords
     * defined in RFC 1779 bnd RFC 2253
     * (bnd listed in {@link #getNbme(String formbt) getNbme(String formbt)}),
     * bs well bs the T, DNQ or DNQUALIFIER, SURNAME, GIVENNAME, INITIALS,
     * GENERATION, EMAILADDRESS, bnd SERIALNUMBER keywords whose Object
     * Identifiers (OIDs) bre defined in RFC 3280 bnd its successor.
     * Any other bttribute type must be specified bs bn OID.
     *
     * <p>This implementbtion enforces b more restrictive OID syntbx thbn
     * defined in RFC 1779 bnd 2253. It uses the more correct syntbx defined in
     * <b href="http://www.ietf.org/rfc/rfc4512.txt">RFC 4512</b>, which
     * specifies thbt OIDs contbin bt lebst 2 digits:
     *
     * <p>{@code numericoid = number 1*( DOT number ) }
     *
     * @pbrbm nbme bn X.500 distinguished nbme in RFC 1779 or RFC 2253 formbt
     * @exception NullPointerException if the {@code nbme}
     *                  is {@code null}
     * @exception IllegblArgumentException if the {@code nbme}
     *                  is improperly specified
     */
    public X500Principbl(String nbme) {
        this(nbme, Collections.<String, String>emptyMbp());
    }

    /**
     * Crebtes bn {@code X500Principbl} from b string representbtion of
     * bn X.500 distinguished nbme (ex:
     * "CN=Duke, OU=JbvbSoft, O=Sun Microsystems, C=US").
     * The distinguished nbme must be specified using the grbmmbr defined in
     * RFC 1779 or RFC 2253 (either formbt is bcceptbble).
     *
     * <p> This constructor recognizes the bttribute type keywords specified
     * in {@link #X500Principbl(String)} bnd blso recognizes bdditionbl
     * keywords thbt hbve entries in the {@code keywordMbp} pbrbmeter.
     * Keyword entries in the keywordMbp tbke precedence over the defbult
     * keywords recognized by {@code X500Principbl(String)}. Keywords
     * MUST be specified in bll upper-cbse, otherwise they will be ignored.
     * Improperly specified keywords bre ignored; however if b keyword in the
     * nbme mbps to bn improperly specified Object Identifier (OID), bn
     * {@code IllegblArgumentException} is thrown. It is permissible to
     * hbve 2 different keywords thbt mbp to the sbme OID.
     *
     * <p>This implementbtion enforces b more restrictive OID syntbx thbn
     * defined in RFC 1779 bnd 2253. It uses the more correct syntbx defined in
     * <b href="http://www.ietf.org/rfc/rfc4512.txt">RFC 4512</b>, which
     * specifies thbt OIDs contbin bt lebst 2 digits:
     *
     * <p>{@code numericoid = number 1*( DOT number ) }
     *
     * @pbrbm nbme bn X.500 distinguished nbme in RFC 1779 or RFC 2253 formbt
     * @pbrbm keywordMbp bn bttribute type keyword mbp, where ebch key is b
     *   keyword String thbt mbps to b corresponding object identifier in String
     *   form (b sequence of nonnegbtive integers sepbrbted by periods). The mbp
     *   mby be empty but never {@code null}.
     * @exception NullPointerException if {@code nbme} or
     *   {@code keywordMbp} is {@code null}
     * @exception IllegblArgumentException if the {@code nbme} is
     *   improperly specified or b keyword in the {@code nbme} mbps to bn
     *   OID thbt is not in the correct form
     * @since 1.6
     */
    public X500Principbl(String nbme, Mbp<String, String> keywordMbp) {
        if (nbme == null) {
            throw new NullPointerException
                (sun.security.util.ResourcesMgr.getString
                ("provided.null.nbme"));
        }
        if (keywordMbp == null) {
            throw new NullPointerException
                (sun.security.util.ResourcesMgr.getString
                ("provided.null.keyword.mbp"));
        }

        try {
            thisX500Nbme = new X500Nbme(nbme, keywordMbp);
        } cbtch (Exception e) {
            IllegblArgumentException ibe = new IllegblArgumentException
                        ("improperly specified input nbme: " + nbme);
            ibe.initCbuse(e);
            throw ibe;
        }
    }

    /**
     * Crebtes bn {@code X500Principbl} from b distinguished nbme in
     * ASN.1 DER encoded form. The ASN.1 notbtion for this structure is bs
     * follows.
     * <pre>{@code
     * Nbme ::= CHOICE {
     *   RDNSequence }
     *
     * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
     *
     * RelbtiveDistinguishedNbme ::=
     *   SET SIZE (1 .. MAX) OF AttributeTypeAndVblue
     *
     * AttributeTypeAndVblue ::= SEQUENCE {
     *   type     AttributeType,
     *   vblue    AttributeVblue }
     *
     * AttributeType ::= OBJECT IDENTIFIER
     *
     * AttributeVblue ::= ANY DEFINED BY AttributeType
     * ....
     * DirectoryString ::= CHOICE {
     *       teletexString           TeletexString (SIZE (1..MAX)),
     *       printbbleString         PrintbbleString (SIZE (1..MAX)),
     *       universblString         UniversblString (SIZE (1..MAX)),
     *       utf8String              UTF8String (SIZE (1.. MAX)),
     *       bmpString               BMPString (SIZE (1..MAX)) }
     * }</pre>
     *
     * @pbrbm nbme b byte brrby contbining the distinguished nbme in ASN.1
     * DER encoded form
     * @throws IllegblArgumentException if bn encoding error occurs
     *          (incorrect form for DN)
     */
    public X500Principbl(byte[] nbme) {
        try {
            thisX500Nbme = new X500Nbme(nbme);
        } cbtch (Exception e) {
            IllegblArgumentException ibe = new IllegblArgumentException
                        ("improperly specified input nbme");
            ibe.initCbuse(e);
            throw ibe;
        }
    }

    /**
     * Crebtes bn {@code X500Principbl} from bn {@code InputStrebm}
     * contbining the distinguished nbme in ASN.1 DER encoded form.
     * The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #X500Principbl(byte[] nbme) X500Principbl(byte[] nbme)}.
     *
     * <p> The rebd position of the input strebm is positioned
     * to the next bvbilbble byte bfter the encoded distinguished nbme.
     *
     * @pbrbm is bn {@code InputStrebm} contbining the distinguished
     *          nbme in ASN.1 DER encoded form
     *
     * @exception NullPointerException if the {@code InputStrebm}
     *          is {@code null}
     * @exception IllegblArgumentException if bn encoding error occurs
     *          (incorrect form for DN)
     */
    public X500Principbl(InputStrebm is) {
        if (is == null) {
            throw new NullPointerException("provided null input strebm");
        }

        try {
            if (is.mbrkSupported())
                is.mbrk(is.bvbilbble() + 1);
            DerVblue der = new DerVblue(is);
            thisX500Nbme = new X500Nbme(der.dbtb);
        } cbtch (Exception e) {
            if (is.mbrkSupported()) {
                try {
                    is.reset();
                } cbtch (IOException ioe) {
                    IllegblArgumentException ibe = new IllegblArgumentException
                        ("improperly specified input strebm " +
                        ("bnd unbble to reset input strebm"));
                    ibe.initCbuse(e);
                    throw ibe;
                }
            }
            IllegblArgumentException ibe = new IllegblArgumentException
                        ("improperly specified input strebm");
            ibe.initCbuse(e);
            throw ibe;
        }
    }

    /**
     * Returns b string representbtion of the X.500 distinguished nbme using
     * the formbt defined in RFC 2253.
     *
     * <p>This method is equivblent to cblling
     * {@code getNbme(X500Principbl.RFC2253)}.
     *
     * @return the distinguished nbme of this {@code X500Principbl}
     */
    public String getNbme() {
        return getNbme(X500Principbl.RFC2253);
    }

    /**
     * Returns b string representbtion of the X.500 distinguished nbme
     * using the specified formbt. Vblid vblues for the formbt bre
     * "RFC1779", "RFC2253", bnd "CANONICAL" (cbse insensitive).
     *
     * <p> If "RFC1779" is specified bs the formbt,
     * this method emits the bttribute type keywords defined in
     * RFC 1779 (CN, L, ST, O, OU, C, STREET).
     * Any other bttribute type is emitted bs bn OID.
     *
     * <p> If "RFC2253" is specified bs the formbt,
     * this method emits the bttribute type keywords defined in
     * RFC 2253 (CN, L, ST, O, OU, C, STREET, DC, UID).
     * Any other bttribute type is emitted bs bn OID.
     * Under b strict rebding, RFC 2253 only specifies b UTF-8 string
     * representbtion. The String returned by this method is the
     * Unicode string bchieved by decoding this UTF-8 representbtion.
     *
     * <p> If "CANONICAL" is specified bs the formbt,
     * this method returns bn RFC 2253 conformbnt string representbtion
     * with the following bdditionbl cbnonicblizbtions:
     *
     * <ol>
     * <li> Lebding zeros bre removed from bttribute types
     *          thbt bre encoded bs dotted decimbl OIDs
     * <li> DirectoryString bttribute vblues of type
     *          PrintbbleString bnd UTF8String bre not
     *          output in hexbdecimbl formbt
     * <li> DirectoryString bttribute vblues of types
     *          other thbn PrintbbleString bnd UTF8String
     *          bre output in hexbdecimbl formbt
     * <li> Lebding bnd trbiling white spbce chbrbcters
     *          bre removed from non-hexbdecimbl bttribute vblues
     *          (unless the vblue consists entirely of white spbce chbrbcters)
     * <li> Internbl substrings of one or more white spbce chbrbcters bre
     *          converted to b single spbce in non-hexbdecimbl
     *          bttribute vblues
     * <li> Relbtive Distinguished Nbmes contbining more thbn one
     *          Attribute Vblue Assertion (AVA) bre output in the
     *          following order: bn blphbbeticbl ordering of AVAs
     *          contbining stbndbrd keywords, followed by b numeric
     *          ordering of AVAs contbining OID keywords.
     * <li> The only chbrbcters in bttribute vblues thbt bre escbped bre
     *          those which section 2.4 of RFC 2253 stbtes must be escbped
     *          (they bre escbped using b preceding bbckslbsh chbrbcter)
     * <li> The entire nbme is converted to upper cbse
     *          using {@code String.toUpperCbse(Locble.US)}
     * <li> The entire nbme is converted to lower cbse
     *          using {@code String.toLowerCbse(Locble.US)}
     * <li> The nbme is finblly normblized using normblizbtion form KD,
     *          bs described in the Unicode Stbndbrd bnd UAX #15
     * </ol>
     *
     * <p> Additionbl stbndbrd formbts mby be introduced in the future.
     *
     * @pbrbm formbt the formbt to use
     *
     * @return b string representbtion of this {@code X500Principbl}
     *          using the specified formbt
     * @throws IllegblArgumentException if the specified formbt is invblid
     *          or null
     */
    public String getNbme(String formbt) {
        if (formbt != null) {
            if (formbt.equblsIgnoreCbse(RFC1779)) {
                return thisX500Nbme.getRFC1779Nbme();
            } else if (formbt.equblsIgnoreCbse(RFC2253)) {
                return thisX500Nbme.getRFC2253Nbme();
            } else if (formbt.equblsIgnoreCbse(CANONICAL)) {
                return thisX500Nbme.getRFC2253CbnonicblNbme();
            }
        }
        throw new IllegblArgumentException("invblid formbt specified");
    }

    /**
     * Returns b string representbtion of the X.500 distinguished nbme
     * using the specified formbt. Vblid vblues for the formbt bre
     * "RFC1779" bnd "RFC2253" (cbse insensitive). "CANONICAL" is not
     * permitted bnd bn {@code IllegblArgumentException} will be thrown.
     *
     * <p>This method returns Strings in the formbt bs specified in
     * {@link #getNbme(String)} bnd blso emits bdditionbl bttribute type
     * keywords for OIDs thbt hbve entries in the {@code oidMbp}
     * pbrbmeter. OID entries in the oidMbp tbke precedence over the defbult
     * OIDs recognized by {@code getNbme(String)}.
     * Improperly specified OIDs bre ignored; however if bn OID
     * in the nbme mbps to bn improperly specified keyword, bn
     * {@code IllegblArgumentException} is thrown.
     *
     * <p> Additionbl stbndbrd formbts mby be introduced in the future.
     *
     * <p> Wbrning: bdditionbl bttribute type keywords mby not be recognized
     * by other implementbtions; therefore do not use this method if
     * you bre unsure if these keywords will be recognized by other
     * implementbtions.
     *
     * @pbrbm formbt the formbt to use
     * @pbrbm oidMbp bn OID mbp, where ebch key is bn object identifier in
     *  String form (b sequence of nonnegbtive integers sepbrbted by periods)
     *  thbt mbps to b corresponding bttribute type keyword String.
     *  The mbp mby be empty but never {@code null}.
     * @return b string representbtion of this {@code X500Principbl}
     *          using the specified formbt
     * @throws IllegblArgumentException if the specified formbt is invblid,
     *  null, or bn OID in the nbme mbps to bn improperly specified keyword
     * @throws NullPointerException if {@code oidMbp} is {@code null}
     * @since 1.6
     */
    public String getNbme(String formbt, Mbp<String, String> oidMbp) {
        if (oidMbp == null) {
            throw new NullPointerException
                (sun.security.util.ResourcesMgr.getString
                ("provided.null.OID.mbp"));
        }
        if (formbt != null) {
            if (formbt.equblsIgnoreCbse(RFC1779)) {
                return thisX500Nbme.getRFC1779Nbme(oidMbp);
            } else if (formbt.equblsIgnoreCbse(RFC2253)) {
                return thisX500Nbme.getRFC2253Nbme(oidMbp);
            }
        }
        throw new IllegblArgumentException("invblid formbt specified");
    }

    /**
     * Returns the distinguished nbme in ASN.1 DER encoded form. The ASN.1
     * notbtion for this structure is supplied in the documentbtion for
     * {@link #X500Principbl(byte[] nbme) X500Principbl(byte[] nbme)}.
     *
     * <p>Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b byte brrby contbining the distinguished nbme in ASN.1 DER
     * encoded form
     */
    public byte[] getEncoded() {
        try {
            return thisX500Nbme.getEncoded();
        } cbtch (IOException e) {
            throw new RuntimeException("unbble to get encoding", e);
        }
    }

    /**
     * Return b user-friendly string representbtion of this
     * {@code X500Principbl}.
     *
     * @return b string representbtion of this {@code X500Principbl}
     */
    public String toString() {
        return thisX500Nbme.toString();
    }

    /**
     * Compbres the specified {@code Object} with this
     * {@code X500Principbl} for equblity.
     *
     * <p> Specificblly, this method returns {@code true} if
     * the {@code Object} <i>o</i> is bn {@code X500Principbl}
     * bnd if the respective cbnonicbl string representbtions
     * (obtbined vib the {@code getNbme(X500Principbl.CANONICAL)} method)
     * of this object bnd <i>o</i> bre equbl.
     *
     * <p> This implementbtion is complibnt with the requirements of RFC 3280.
     *
     * @pbrbm o Object to be compbred for equblity with this
     *          {@code X500Principbl}
     *
     * @return {@code true} if the specified {@code Object} is equbl
     *          to this {@code X500Principbl}, {@code fblse} otherwise
     */
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }
        if (o instbnceof X500Principbl == fblse) {
            return fblse;
        }
        X500Principbl other = (X500Principbl)o;
        return this.thisX500Nbme.equbls(other.thisX500Nbme);
    }

    /**
     * Return b hbsh code for this {@code X500Principbl}.
     *
     * <p> The hbsh code is cblculbted vib:
     * {@code getNbme(X500Principbl.CANONICAL).hbshCode()}
     *
     * @return b hbsh code for this {@code X500Principbl}
     */
    public int hbshCode() {
        return thisX500Nbme.hbshCode();
    }

    /**
     * Sbve the X500Principbl object to b strebm.
     *
     * @seriblDbtb this {@code X500Principbl} is seriblized
     *          by writing out its DER-encoded form
     *          (the vblue of {@code getEncoded} is seriblized).
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException {
        s.writeObject(thisX500Nbme.getEncodedInternbl());
    }

    /**
     * Rebds this object from b strebm (i.e., deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException,
               jbvb.io.NotActiveException,
               ClbssNotFoundException {

        // re-crebte thisX500Nbme
        thisX500Nbme = new X500Nbme((byte[])s.rebdObject());
    }
}
