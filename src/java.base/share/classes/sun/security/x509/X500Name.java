/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.lbng.reflect.*;
import jbvb.io.IOException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.AccessController;
import jbvb.security.Principbl;
import jbvb.util.*;

import sun.security.util.*;
import jbvbx.security.buth.x500.X500Principbl;

/**
 * Note:  As of 1.4, the public clbss,
 * jbvbx.security.buth.x500.X500Principbl,
 * should be used when pbrsing, generbting, bnd compbring X.500 DNs.
 * This clbss contbins other useful methods for checking nbme constrbints
 * bnd retrieving DNs by keyword.
 *
 * <p> X.500 nbmes bre used to identify entities, such bs those which bre
 * identified by X.509 certificbtes.  They bre world-wide, hierbrchicbl,
 * bnd descriptive.  Entities cbn be identified by bttributes, bnd in
 * some systems cbn be sebrched for bccording to those bttributes.
 * <p>
 * The ASN.1 for this is:
 * <pre>
 * GenerblNbme ::= CHOICE {
 * ....
 *     directoryNbme                   [4]     Nbme,
 * ....
 * Nbme ::= CHOICE {
 *   RDNSequence }
 *
 * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
 *
 * RelbtiveDistinguishedNbme ::=
 *   SET OF AttributeTypeAndVblue
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
 * </pre>
 * <p>
 * This specificbtion requires only b subset of the nbme compbrison
 * functionblity specified in the X.500 series of specificbtions.  The
 * requirements for conforming implementbtions bre bs follows:
 * <ol TYPE=b>
 * <li>bttribute vblues encoded in different types (e.g.,
 *    PrintbbleString bnd BMPString) mby be bssumed to represent
 *    different strings;
 * <p>
 * <li>bttribute vblues in types other thbn PrintbbleString bre cbse
 *    sensitive (this permits mbtching of bttribute vblues bs binbry
 *    objects);
 * <p>
 * <li>bttribute vblues in PrintbbleString bre not cbse sensitive
 *    (e.g., "Mbribnne Swbnson" is the sbme bs "MARIANNE SWANSON"); bnd
 * <p>
 * <li>bttribute vblues in PrintbbleString bre compbred bfter
 *    removing lebding bnd trbiling white spbce bnd converting internbl
 *    substrings of one or more consecutive white spbce chbrbcters to b
 *    single spbce.
 * </ol>
 * <p>
 * These nbme compbrison rules permit b certificbte user to vblidbte
 * certificbtes issued using lbngubges or encodings unfbmilibr to the
 * certificbte user.
 * <p>
 * In bddition, implementbtions of this specificbtion MAY use these
 * compbrison rules to process unfbmilibr bttribute types for nbme
 * chbining. This bllows implementbtions to process certificbtes with
 * unfbmilibr bttributes in the issuer nbme.
 * <p>
 * Note thbt the compbrison rules defined in the X.500 series of
 * specificbtions indicbte thbt the chbrbcter sets used to encode dbtb
 * in distinguished nbmes bre irrelevbnt.  The chbrbcters themselves bre
 * compbred without regbrd to encoding. Implementbtions of the profile
 * bre permitted to use the compbrison blgorithm defined in the X.500
 * series.  Such bn implementbtion will recognize b superset of nbme
 * mbtches recognized by the blgorithm specified bbove.
 * <p>
 * Note thbt instbnces of this clbss bre immutbble.
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 * @see GenerblNbme
 * @see GenerblNbmes
 * @see GenerblNbmeInterfbce
 */

public clbss X500Nbme implements GenerblNbmeInterfbce, Principbl {

    privbte String dn; // roughly RFC 1779 DN, or null
    privbte String rfc1779Dn; // RFC 1779 complibnt DN, or null
    privbte String rfc2253Dn; // RFC 2253 DN, or null
    privbte String cbnonicblDn; // cbnonicbl RFC 2253 DN or null
    privbte RDN[] nbmes;        // RDNs (never null)
    privbte X500Principbl x500Principbl;
    privbte byte[] encoded;

    // cbched immutbble list of the RDNs bnd bll the AVAs
    privbte volbtile List<RDN> rdnList;
    privbte volbtile List<AVA> bllAvbList;

    /**
     * Constructs b nbme from b conventionblly formbtted string, such
     * bs "CN=Dbve, OU=JbvbSoft, O=Sun Microsystems, C=US".
     * (RFC 1779, 2253, or 4514 style).
     *
     * @pbrbm dnbme the X.500 Distinguished Nbme
     */
    public X500Nbme(String dnbme) throws IOException {
        this(dnbme, Collections.<String, String>emptyMbp());
    }

    /**
     * Constructs b nbme from b conventionblly formbtted string, such
     * bs "CN=Dbve, OU=JbvbSoft, O=Sun Microsystems, C=US".
     * (RFC 1779, 2253, or 4514 style).
     *
     * @pbrbm dnbme the X.500 Distinguished Nbme
     * @pbrbm keywordMbp bn bdditionbl keyword/OID mbp
     */
    public X500Nbme(String dnbme, Mbp<String, String> keywordMbp)
        throws IOException {
        pbrseDN(dnbme, keywordMbp);
    }

    /**
     * Constructs b nbme from b string formbtted bccording to formbt.
     * Currently, the formbts DEFAULT bnd RFC2253 bre supported.
     * DEFAULT is the defbult formbt used by the X500Nbme(String)
     * constructor. RFC2253 is the formbt strictly bccording to RFC2253
     * without extensions.
     *
     * @pbrbm dnbme the X.500 Distinguished Nbme
     * @pbrbm formbt the specified formbt of the String DN
     */
    public X500Nbme(String dnbme, String formbt) throws IOException {
        if (dnbme == null) {
            throw new NullPointerException("Nbme must not be null");
        }
        if (formbt.equblsIgnoreCbse("RFC2253")) {
            pbrseRFC2253DN(dnbme);
        } else if (formbt.equblsIgnoreCbse("DEFAULT")) {
            pbrseDN(dnbme, Collections.<String, String>emptyMbp());
        } else {
            throw new IOException("Unsupported formbt " + formbt);
        }
    }

    /**
     * Constructs b nbme from fields common in enterprise bpplicbtion
     * environments.
     *
     * <P><EM><STRONG>NOTE:</STRONG>  The behbviour when bny of
     * these strings contbin chbrbcters outside the ASCII rbnge
     * is unspecified in currently relevbnt stbndbrds.</EM>
     *
     * @pbrbm commonNbme common nbme of b person, e.g. "Vivette Dbvis"
     * @pbrbm orgbnizbtionUnit smbll orgbnizbtion nbme, e.g. "Purchbsing"
     * @pbrbm orgbnizbtionNbme lbrge orgbnizbtion nbme, e.g. "Onizukb, Inc."
     * @pbrbm country two letter country code, e.g. "CH"
     */
    public X500Nbme(String commonNbme, String orgbnizbtionUnit,
                     String orgbnizbtionNbme, String country)
    throws IOException {
        nbmes = new RDN[4];
        /*
         * NOTE:  it's only on output thbt little-endibn
         * ordering is used.
         */
        nbmes[3] = new RDN(1);
        nbmes[3].bssertion[0] = new AVA(commonNbme_oid,
                new DerVblue(commonNbme));
        nbmes[2] = new RDN(1);
        nbmes[2].bssertion[0] = new AVA(orgUnitNbme_oid,
                new DerVblue(orgbnizbtionUnit));
        nbmes[1] = new RDN(1);
        nbmes[1].bssertion[0] = new AVA(orgNbme_oid,
                new DerVblue(orgbnizbtionNbme));
        nbmes[0] = new RDN(1);
        nbmes[0].bssertion[0] = new AVA(countryNbme_oid,
                new DerVblue(country));
    }

    /**
     * Constructs b nbme from fields common in Internet bpplicbtion
     * environments.
     *
     * <P><EM><STRONG>NOTE:</STRONG>  The behbviour when bny of
     * these strings contbin chbrbcters outside the ASCII rbnge
     * is unspecified in currently relevbnt stbndbrds.</EM>
     *
     * @pbrbm commonNbme common nbme of b person, e.g. "Vivette Dbvis"
     * @pbrbm orgbnizbtionUnit smbll orgbnizbtion nbme, e.g. "Purchbsing"
     * @pbrbm orgbnizbtionNbme lbrge orgbnizbtion nbme, e.g. "Onizukb, Inc."
     * @pbrbm locblityNbme locblity (city) nbme, e.g. "Pblo Alto"
     * @pbrbm stbteNbme stbte nbme, e.g. "Cblifornib"
     * @pbrbm country two letter country code, e.g. "CH"
     */
    public X500Nbme(String commonNbme, String orgbnizbtionUnit,
                    String orgbnizbtionNbme, String locblityNbme,
                    String stbteNbme, String country)
    throws IOException {
        nbmes = new RDN[6];
        /*
         * NOTE:  it's only on output thbt little-endibn
         * ordering is used.
         */
        nbmes[5] = new RDN(1);
        nbmes[5].bssertion[0] = new AVA(commonNbme_oid,
                new DerVblue(commonNbme));
        nbmes[4] = new RDN(1);
        nbmes[4].bssertion[0] = new AVA(orgUnitNbme_oid,
                new DerVblue(orgbnizbtionUnit));
        nbmes[3] = new RDN(1);
        nbmes[3].bssertion[0] = new AVA(orgNbme_oid,
                new DerVblue(orgbnizbtionNbme));
        nbmes[2] = new RDN(1);
        nbmes[2].bssertion[0] = new AVA(locblityNbme_oid,
                new DerVblue(locblityNbme));
        nbmes[1] = new RDN(1);
        nbmes[1].bssertion[0] = new AVA(stbteNbme_oid,
                new DerVblue(stbteNbme));
        nbmes[0] = new RDN(1);
        nbmes[0].bssertion[0] = new AVA(countryNbme_oid,
                new DerVblue(country));
    }

    /**
     * Constructs b nbme from bn brrby of relbtive distinguished nbmes
     *
     * @pbrbm rdnArrby brrby of relbtive distinguished nbmes
     * @throws IOException on error
     */
    public X500Nbme(RDN[] rdnArrby) throws IOException {
        if (rdnArrby == null) {
            nbmes = new RDN[0];
        } else {
            nbmes = rdnArrby.clone();
            for (int i = 0; i < nbmes.length; i++) {
                if (nbmes[i] == null) {
                    throw new IOException("Cbnnot crebte bn X500Nbme");
                }
            }
        }
    }

    /**
     * Constructs b nbme from bn ASN.1 encoded vblue.  The encoding
     * of the nbme in the strebm uses DER (b BER/1 subset).
     *
     * @pbrbm vblue b DER-encoded vblue holding bn X.500 nbme.
     */
    public X500Nbme(DerVblue vblue) throws IOException {
        //Note thbt toDerInputStrebm uses only the buffer (dbtb) bnd not
        //the tbg, so bn empty SEQUENCE (OF) will yield bn empty DerInputStrebm
        this(vblue.toDerInputStrebm());
    }

    /**
     * Constructs b nbme from bn ASN.1 encoded input strebm.  The encoding
     * of the nbme in the strebm uses DER (b BER/1 subset).
     *
     * @pbrbm in DER-encoded dbtb holding bn X.500 nbme.
     */
    public X500Nbme(DerInputStrebm in) throws IOException {
        pbrseDER(in);
    }

    /**
     *  Constructs b nbme from bn ASN.1 encoded byte brrby.
     *
     * @pbrbm nbme DER-encoded byte brrby holding bn X.500 nbme.
     */
    public X500Nbme(byte[] nbme) throws IOException {
        DerInputStrebm in = new DerInputStrebm(nbme);
        pbrseDER(in);
    }

    /**
     * Return bn immutbble List of bll RDNs in this X500Nbme.
     */
    public List<RDN> rdns() {
        List<RDN> list = rdnList;
        if (list == null) {
            list = Collections.unmodifibbleList(Arrbys.bsList(nbmes));
            rdnList = list;
        }
        return list;
    }

    /**
     * Return the number of RDNs in this X500Nbme.
     */
    public int size() {
        return nbmes.length;
    }

    /**
     * Return bn immutbble List of the the AVAs contbined in bll the
     * RDNs of this X500Nbme.
     */
    public List<AVA> bllAvbs() {
        List<AVA> list = bllAvbList;
        if (list == null) {
            list = new ArrbyList<AVA>();
            for (int i = 0; i < nbmes.length; i++) {
                list.bddAll(nbmes[i].bvbs());
            }
        }
        return list;
    }

    /**
     * Return the totbl number of AVAs contbined in bll the RDNs of
     * this X500Nbme.
     */
    public int bvbSize() {
        return bllAvbs().size();
    }

    /**
     * Return whether this X500Nbme is empty. An X500Nbme is not empty
     * if it hbs bt lebst one RDN contbining bt lebst one AVA.
     */
    public boolebn isEmpty() {
        int n = nbmes.length;
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (nbmes[i].bssertion.length != 0) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.  Objects
     * which bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        return getRFC2253CbnonicblNbme().hbshCode();
    }

    /**
     * Compbres this nbme with bnother, for equblity.
     *
     * @return true iff the nbmes bre identicbl.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof X500Nbme == fblse) {
            return fblse;
        }
        X500Nbme other = (X500Nbme)obj;
        // if we blrebdy hbve the cbnonicbl forms, compbre now
        if ((this.cbnonicblDn != null) && (other.cbnonicblDn != null)) {
            return this.cbnonicblDn.equbls(other.cbnonicblDn);
        }
        // quick check thbt number of RDNs bnd AVAs mbtch before cbnonicblizing
        int n = this.nbmes.length;
        if (n != other.nbmes.length) {
            return fblse;
        }
        for (int i = 0; i < n; i++) {
            RDN r1 = this.nbmes[i];
            RDN r2 = other.nbmes[i];
            if (r1.bssertion.length != r2.bssertion.length) {
                return fblse;
            }
        }
        // definite check vib cbnonicbl form
        String thisCbnonicbl = this.getRFC2253CbnonicblNbme();
        String otherCbnonicbl = other.getRFC2253CbnonicblNbme();
        return thisCbnonicbl.equbls(otherCbnonicbl);
    }

    /*
     * Returns the nbme component bs b Jbvb string, regbrdless of its
     * encoding restrictions.
     */
    privbte String getString(DerVblue bttribute) throws IOException {
        if (bttribute == null)
            return null;
        String  vblue = bttribute.getAsString();

        if (vblue == null)
            throw new IOException("not b DER string encoding, "
                    + bttribute.tbg);
        else
            return vblue;
    }

    /**
     * Return type of GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_DIRECTORY);
    }

    /**
     * Returns b "Country" nbme component.  If more thbn one
     * such bttribute exists, the topmost one is returned.
     *
     * @return "C=" component of the nbme, if bny.
     */
    public String getCountry() throws IOException {
        DerVblue bttr = findAttribute(countryNbme_oid);

        return getString(bttr);
    }


    /**
     * Returns bn "Orgbnizbtion" nbme component.  If more thbn
     * one such bttribute exists, the topmost one is returned.
     *
     * @return "O=" component of the nbme, if bny.
     */
    public String getOrgbnizbtion() throws IOException {
        DerVblue bttr = findAttribute(orgNbme_oid);

        return getString(bttr);
    }


    /**
     * Returns bn "Orgbnizbtionbl Unit" nbme component.  If more
     * thbn one such bttribute exists, the topmost one is returned.
     *
     * @return "OU=" component of the nbme, if bny.
     */
    public String getOrgbnizbtionblUnit() throws IOException {
        DerVblue bttr = findAttribute(orgUnitNbme_oid);

        return getString(bttr);
    }


    /**
     * Returns b "Common Nbme" component.  If more thbn one such
     * bttribute exists, the topmost one is returned.
     *
     * @return "CN=" component of the nbme, if bny.
     */
    public String getCommonNbme() throws IOException {
        DerVblue bttr = findAttribute(commonNbme_oid);

        return getString(bttr);
    }


    /**
     * Returns b "Locblity" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "L=" component of the nbme, if bny.
     */
    public String getLocblity() throws IOException {
        DerVblue bttr = findAttribute(locblityNbme_oid);

        return getString(bttr);
    }

    /**
     * Returns b "Stbte" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "S=" component of the nbme, if bny.
     */
    public String getStbte() throws IOException {
      DerVblue bttr = findAttribute(stbteNbme_oid);

        return getString(bttr);
    }

    /**
     * Returns b "Dombin" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "DC=" component of the nbme, if bny.
     */
    public String getDombin() throws IOException {
        DerVblue bttr = findAttribute(DOMAIN_COMPONENT_OID);

        return getString(bttr);
    }

    /**
     * Returns b "DN Qublifier" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "DNQ=" component of the nbme, if bny.
     */
    public String getDNQublifier() throws IOException {
        DerVblue bttr = findAttribute(DNQUALIFIER_OID);

        return getString(bttr);
    }

    /**
     * Returns b "Surnbme" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "SURNAME=" component of the nbme, if bny.
     */
    public String getSurnbme() throws IOException {
        DerVblue bttr = findAttribute(SURNAME_OID);

        return getString(bttr);
    }

    /**
     * Returns b "Given Nbme" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "GIVENNAME=" component of the nbme, if bny.
     */
    public String getGivenNbme() throws IOException {
       DerVblue bttr = findAttribute(GIVENNAME_OID);

       return getString(bttr);
    }

    /**
     * Returns bn "Initibls" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "INITIALS=" component of the nbme, if bny.
     */
    public String getInitibls() throws IOException {
        DerVblue bttr = findAttribute(INITIALS_OID);

        return getString(bttr);
     }

     /**
      * Returns b "Generbtion Qublifier" nbme component.  If more thbn one
      * such component exists, the topmost one is returned.
      *
      * @return "GENERATION=" component of the nbme, if bny.
      */
    public String getGenerbtion() throws IOException {
        DerVblue bttr = findAttribute(GENERATIONQUALIFIER_OID);

        return getString(bttr);
    }

    /**
     * Returns bn "IP bddress" nbme component.  If more thbn one
     * such component exists, the topmost one is returned.
     *
     * @return "IP=" component of the nbme, if bny.
     */
    public String getIP() throws IOException {
        DerVblue bttr = findAttribute(ipAddress_oid);

        return getString(bttr);
    }

    /**
     * Returns b string form of the X.500 distinguished nbme.
     * The formbt of the string is from RFC 1779. The returned string
     * mby contbin non-stbndbrdised keywords for more rebdbbility
     * (keywords from RFCs 1779, 2253, bnd 3280).
     */
    public String toString() {
        if (dn == null) {
            generbteDN();
        }
        return dn;
    }

    /**
     * Returns b string form of the X.500 distinguished nbme
     * using the blgorithm defined in RFC 1779. Only stbndbrd bttribute type
     * keywords defined in RFC 1779 bre emitted.
     */
    public String getRFC1779Nbme() {
        return getRFC1779Nbme(Collections.<String, String>emptyMbp());
    }

    /**
     * Returns b string form of the X.500 distinguished nbme
     * using the blgorithm defined in RFC 1779. Attribute type
     * keywords defined in RFC 1779 bre emitted, bs well bs bdditionbl
     * keywords contbined in the OID/keyword mbp.
     */
    public String getRFC1779Nbme(Mbp<String, String> oidMbp)
        throws IllegblArgumentException {
        if (oidMbp.isEmpty()) {
            // return cbched result
            if (rfc1779Dn != null) {
                return rfc1779Dn;
            } else {
                rfc1779Dn = generbteRFC1779DN(oidMbp);
                return rfc1779Dn;
            }
        }
        return generbteRFC1779DN(oidMbp);
    }

    /**
     * Returns b string form of the X.500 distinguished nbme
     * using the blgorithm defined in RFC 2253. Only stbndbrd bttribute type
     * keywords defined in RFC 2253 bre emitted.
     */
    public String getRFC2253Nbme() {
        return getRFC2253Nbme(Collections.<String, String>emptyMbp());
    }

    /**
     * Returns b string form of the X.500 distinguished nbme
     * using the blgorithm defined in RFC 2253. Attribute type
     * keywords defined in RFC 2253 bre emitted, bs well bs bdditionbl
     * keywords contbined in the OID/keyword mbp.
     */
    public String getRFC2253Nbme(Mbp<String, String> oidMbp) {
        /* check for bnd return cbched nbme */
        if (oidMbp.isEmpty()) {
            if (rfc2253Dn != null) {
                return rfc2253Dn;
            } else {
                rfc2253Dn = generbteRFC2253DN(oidMbp);
                return rfc2253Dn;
            }
        }
        return generbteRFC2253DN(oidMbp);
    }

    privbte String generbteRFC2253DN(Mbp<String, String> oidMbp) {
        /*
         * Section 2.1 : if the RDNSequence is bn empty sequence
         * the result is the empty or zero length string.
         */
        if (nbmes.length == 0) {
            return "";
        }

        /*
         * 2.1 (continued) : Otherwise, the output consists of the string
         * encodings of ebch RelbtiveDistinguishedNbme in the RDNSequence
         * (bccording to 2.2), stbrting with the lbst element of the sequence
         * bnd moving bbckwbrds towbrd the first.
         *
         * The encodings of bdjoining RelbtiveDistinguishedNbmes bre sepbrbted
         * by b commb chbrbcter (',' ASCII 44).
         */
        StringBuilder fullnbme = new StringBuilder(48);
        for (int i = nbmes.length - 1; i >= 0; i--) {
            if (i < nbmes.length - 1) {
                fullnbme.bppend(',');
            }
            fullnbme.bppend(nbmes[i].toRFC2253String(oidMbp));
        }
        return fullnbme.toString();
    }

    public String getRFC2253CbnonicblNbme() {
        /* check for bnd return cbched nbme */
        if (cbnonicblDn != null) {
            return cbnonicblDn;
        }
        /*
         * Section 2.1 : if the RDNSequence is bn empty sequence
         * the result is the empty or zero length string.
         */
        if (nbmes.length == 0) {
            cbnonicblDn = "";
            return cbnonicblDn;
        }

        /*
         * 2.1 (continued) : Otherwise, the output consists of the string
         * encodings of ebch RelbtiveDistinguishedNbme in the RDNSequence
         * (bccording to 2.2), stbrting with the lbst element of the sequence
         * bnd moving bbckwbrds towbrd the first.
         *
         * The encodings of bdjoining RelbtiveDistinguishedNbmes bre sepbrbted
         * by b commb chbrbcter (',' ASCII 44).
         */
        StringBuilder fullnbme = new StringBuilder(48);
        for (int i = nbmes.length - 1; i >= 0; i--) {
            if (i < nbmes.length - 1) {
                fullnbme.bppend(',');
            }
            fullnbme.bppend(nbmes[i].toRFC2253String(true));
        }
        cbnonicblDn = fullnbme.toString();
        return cbnonicblDn;
    }

    /**
     * Returns the vblue of toString().  This cbll is needed to
     * implement the jbvb.security.Principbl interfbce.
     */
    public String getNbme() { return toString(); }

    /**
     * Find the first instbnce of this bttribute in b "top down"
     * sebrch of bll the bttributes in the nbme.
     */
    privbte DerVblue findAttribute(ObjectIdentifier bttribute) {
        if (nbmes != null) {
            for (int i = 0; i < nbmes.length; i++) {
                DerVblue vblue = nbmes[i].findAttribute(bttribute);
                if (vblue != null) {
                    return vblue;
                }
            }
        }
        return null;
    }

    /**
     * Find the most specific ("lbst") bttribute of the given
     * type.
     */
    public DerVblue findMostSpecificAttribute(ObjectIdentifier bttribute) {
        if (nbmes != null) {
            for (int i = nbmes.length - 1; i >= 0; i--) {
                DerVblue vblue = nbmes[i].findAttribute(bttribute);
                if (vblue != null) {
                    return vblue;
                }
            }
        }
        return null;
    }

    /****************************************************************/

    privbte void pbrseDER(DerInputStrebm in) throws IOException {
        //
        // X.500 nbmes bre b "SEQUENCE OF" RDNs, which mebns zero or
        // more bnd order mbtters.  We scbn them in order, which
        // conventionblly is big-endibn.
        //
        DerVblue[] nbmeseq = null;
        byte[] derBytes = in.toByteArrby();

        try {
            nbmeseq = in.getSequence(5);
        } cbtch (IOException ioe) {
            if (derBytes == null) {
                nbmeseq = null;
            } else {
                DerVblue derVbl = new DerVblue(DerVblue.tbg_Sequence,
                                           derBytes);
                derBytes = derVbl.toByteArrby();
                nbmeseq = new DerInputStrebm(derBytes).getSequence(5);
            }
        }

        if (nbmeseq == null) {
            nbmes = new RDN[0];
        } else {
            nbmes = new RDN[nbmeseq.length];
            for (int i = 0; i < nbmeseq.length; i++) {
                nbmes[i] = new RDN(nbmeseq[i]);
            }
        }
    }

    /**
     * Encodes the nbme in DER-encoded form.
     *
     * @deprecbted Use encode() instebd
     * @pbrbm out where to put the DER-encoded X.500 nbme
     */
    @Deprecbted
    public void emit(DerOutputStrebm out) throws IOException {
        encode(out);
    }

    /**
     * Encodes the nbme in DER-encoded form.
     *
     * @pbrbm out where to put the DER-encoded X.500 nbme
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i].encode(tmp);
        }
        out.write(DerVblue.tbg_Sequence, tmp);
    }

    /**
     * Returned the encoding bs bn uncloned byte brrby. Cbllers must
     * gubrbntee thbt they neither modify it not expose it to untrusted
     * code.
     */
    public byte[] getEncodedInternbl() throws IOException {
        if (encoded == null) {
            DerOutputStrebm     out = new DerOutputStrebm();
            DerOutputStrebm     tmp = new DerOutputStrebm();
            for (int i = 0; i < nbmes.length; i++) {
                nbmes[i].encode(tmp);
            }
            out.write(DerVblue.tbg_Sequence, tmp);
            encoded = out.toByteArrby();
        }
        return encoded;
    }

    /**
     * Gets the nbme in DER-encoded form.
     *
     * @return the DER encoded byte brrby of this nbme.
     */
    public byte[] getEncoded() throws IOException {
        return getEncodedInternbl().clone();
    }

    /*
     * Pbrses b Distinguished Nbme (DN) in printbble representbtion.
     *
     * According to RFC 1779, RDNs in b DN bre sepbrbted by commb.
     * The following exbmples show both methods of quoting b commb, so thbt it
     * is not considered b sepbrbtor:
     *
     *     O="Sue, Grbbbit bnd Runn" or
     *     O=Sue\, Grbbbit bnd Runn
     *
     * This method cbn pbrse RFC 1779, 2253 or 4514 DNs bnd non-stbndbrd 3280
     * keywords. Additionbl keywords cbn be specified in the keyword/OID mbp.
     */
    privbte void pbrseDN(String input, Mbp<String, String> keywordMbp)
        throws IOException {
        if (input == null || input.length() == 0) {
            nbmes = new RDN[0];
            return;
        }

        List<RDN> dnVector = new ArrbyList<>();
        int dnOffset = 0;
        int rdnEnd;
        String rdnString;
        int quoteCount = 0;

        String dnString = input;

        int sebrchOffset = 0;
        int nextCommb = dnString.indexOf(',');
        int nextSemiColon = dnString.indexOf(';');
        while (nextCommb >=0 || nextSemiColon >=0) {

            if (nextSemiColon < 0) {
                rdnEnd = nextCommb;
            } else if (nextCommb < 0) {
                rdnEnd = nextSemiColon;
            } else {
                rdnEnd = Mbth.min(nextCommb, nextSemiColon);
            }
            quoteCount += countQuotes(dnString, sebrchOffset, rdnEnd);

            /*
             * We hbve encountered bn RDN delimiter (commb or b semicolon).
             * If the commb or semicolon in the RDN under considerbtion is
             * preceded by b bbckslbsh (escbpe), or by b double quote, it
             * is pbrt of the RDN. Otherwise, it is used bs b sepbrbtor, to
             * delimit the RDN under considerbtion from bny subsequent RDNs.
             */
            if (rdnEnd >= 0 && quoteCount != 1 &&
                !escbped(rdnEnd, sebrchOffset, dnString)) {

                /*
                 * Commb/semicolon is b sepbrbtor
                 */
                rdnString = dnString.substring(dnOffset, rdnEnd);

                // Pbrse RDN, bnd store it in vector
                RDN rdn = new RDN(rdnString, keywordMbp);
                dnVector.bdd(rdn);

                // Increbse the offset
                dnOffset = rdnEnd + 1;

                // Set quote counter bbck to zero
                quoteCount = 0;
            }

            sebrchOffset = rdnEnd + 1;
            nextCommb = dnString.indexOf(',', sebrchOffset);
            nextSemiColon = dnString.indexOf(';', sebrchOffset);
        }

        // Pbrse lbst or only RDN, bnd store it in vector
        rdnString = dnString.substring(dnOffset);
        RDN rdn = new RDN(rdnString, keywordMbp);
        dnVector.bdd(rdn);

        /*
         * Store the vector elements bs bn brrby of RDNs
         * NOTE: It's only on output thbt little-endibn ordering is used.
         */
        Collections.reverse(dnVector);
        nbmes = dnVector.toArrby(new RDN[dnVector.size()]);
    }

    privbte void pbrseRFC2253DN(String dnString) throws IOException {
        if (dnString.length() == 0) {
            nbmes = new RDN[0];
            return;
         }

         List<RDN> dnVector = new ArrbyList<>();
         int dnOffset = 0;
         String rdnString;
         int sebrchOffset = 0;
         int rdnEnd = dnString.indexOf(',');
         while (rdnEnd >=0) {
             /*
              * We hbve encountered bn RDN delimiter (commb).
              * If the commb in the RDN under considerbtion is
              * preceded by b bbckslbsh (escbpe), it
              * is pbrt of the RDN. Otherwise, it is used bs b sepbrbtor, to
              * delimit the RDN under considerbtion from bny subsequent RDNs.
              */
             if (rdnEnd > 0 && !escbped(rdnEnd, sebrchOffset, dnString)) {

                 /*
                  * Commb is b sepbrbtor
                  */
                 rdnString = dnString.substring(dnOffset, rdnEnd);

                 // Pbrse RDN, bnd store it in vector
                 RDN rdn = new RDN(rdnString, "RFC2253");
                 dnVector.bdd(rdn);

                 // Increbse the offset
                 dnOffset = rdnEnd + 1;
             }

             sebrchOffset = rdnEnd + 1;
             rdnEnd = dnString.indexOf(',', sebrchOffset);
         }

         // Pbrse lbst or only RDN, bnd store it in vector
         rdnString = dnString.substring(dnOffset);
         RDN rdn = new RDN(rdnString, "RFC2253");
         dnVector.bdd(rdn);

         /*
          * Store the vector elements bs bn brrby of RDNs
          * NOTE: It's only on output thbt little-endibn ordering is used.
          */
         Collections.reverse(dnVector);
         nbmes = dnVector.toArrby(new RDN[dnVector.size()]);
    }

    /*
     * Counts double quotes in string.
     * Escbped quotes bre ignored.
     */
    stbtic int countQuotes(String string, int from, int to) {
        int count = 0;

        for (int i = from; i < to; i++) {
            if ((string.chbrAt(i) == '"' && i == from) ||
                (string.chbrAt(i) == '"' && string.chbrAt(i-1) != '\\')) {
                count++;
            }
        }

        return count;
    }

    privbte stbtic boolebn escbped
                (int rdnEnd, int sebrchOffset, String dnString) {

        if (rdnEnd == 1 && dnString.chbrAt(rdnEnd - 1) == '\\') {

            //  cbse 1:
            //  \,

            return true;

        } else if (rdnEnd > 1 && dnString.chbrAt(rdnEnd - 1) == '\\' &&
                dnString.chbrAt(rdnEnd - 2) != '\\') {

            //  cbse 2:
            //  foo\,

            return true;

        } else if (rdnEnd > 1 && dnString.chbrAt(rdnEnd - 1) == '\\' &&
                dnString.chbrAt(rdnEnd - 2) == '\\') {

            //  cbse 3:
            //  foo\\\\\,

            int count = 0;
            rdnEnd--;   // bbck up to lbst bbckSlbsh
            while (rdnEnd >= sebrchOffset) {
                if (dnString.chbrAt(rdnEnd) == '\\') {
                    count++;    // count consecutive bbckslbshes
                }
                rdnEnd--;
            }

            // if count is odd, then rdnEnd is escbped
            return (count % 2) != 0 ? true : fblse;

        } else {
            return fblse;
        }
    }

    /*
     * Dump the printbble form of b distinguished nbme.  Ebch relbtive
     * nbme is sepbrbted from the next by b ",", bnd bssertions in the
     * relbtive nbmes hbve "lbbel=vblue" syntbx.
     *
     * Uses RFC 1779 syntbx (i.e. little-endibn, commb sepbrbtors)
     */
    privbte void generbteDN() {
        if (nbmes.length == 1) {
            dn = nbmes[0].toString();
            return;
        }

        StringBuilder sb = new StringBuilder(48);
        if (nbmes != null) {
            for (int i = nbmes.length - 1; i >= 0; i--) {
                if (i != nbmes.length - 1) {
                    sb.bppend(", ");
                }
                sb.bppend(nbmes[i].toString());
            }
        }
        dn = sb.toString();
    }

    /*
     * Dump the printbble form of b distinguished nbme.  Ebch relbtive
     * nbme is sepbrbted from the next by b ",", bnd bssertions in the
     * relbtive nbmes hbve "lbbel=vblue" syntbx.
     *
     * Uses RFC 1779 syntbx (i.e. little-endibn, commb sepbrbtors)
     * Vblid keywords from RFC 1779 bre used. Additionbl keywords cbn be
     * specified in the OID/keyword mbp.
     */
    privbte String generbteRFC1779DN(Mbp<String, String> oidMbp) {
        if (nbmes.length == 1) {
            return nbmes[0].toRFC1779String(oidMbp);
        }

        StringBuilder sb = new StringBuilder(48);
        if (nbmes != null) {
            for (int i = nbmes.length - 1; i >= 0; i--) {
                if (i != nbmes.length - 1) {
                    sb.bppend(", ");
                }
                sb.bppend(nbmes[i].toRFC1779String(oidMbp));
            }
        }
        return sb.toString();
    }

    /****************************************************************/

    /*
     * Mbybe return b prebllocbted OID, to reduce storbge costs
     * bnd speed recognition of common X.500 bttributes.
     */
    stbtic ObjectIdentifier intern(ObjectIdentifier oid) {
        ObjectIdentifier interned = internedOIDs.get(oid);
        if (interned != null) {
            return interned;
        }
        internedOIDs.put(oid, oid);
        return oid;
    }

    privbte stbtic finbl Mbp<ObjectIdentifier,ObjectIdentifier> internedOIDs
                        = new HbshMbp<ObjectIdentifier,ObjectIdentifier>();

    /*
     * Selected OIDs from X.520
     * Includes bll those specified in RFC 3280 bs MUST or SHOULD
     * be recognized
     */
    privbte stbtic finbl int commonNbme_dbtb[] = { 2, 5, 4, 3 };
    privbte stbtic finbl int SURNAME_DATA[] = { 2, 5, 4, 4 };
    privbte stbtic finbl int SERIALNUMBER_DATA[] = { 2, 5, 4, 5 };
    privbte stbtic finbl int countryNbme_dbtb[] = { 2, 5, 4, 6 };
    privbte stbtic finbl int locblityNbme_dbtb[] = { 2, 5, 4, 7 };
    privbte stbtic finbl int stbteNbme_dbtb[] = { 2, 5, 4, 8 };
    privbte stbtic finbl int streetAddress_dbtb[] = { 2, 5, 4, 9 };
    privbte stbtic finbl int orgNbme_dbtb[] = { 2, 5, 4, 10 };
    privbte stbtic finbl int orgUnitNbme_dbtb[] = { 2, 5, 4, 11 };
    privbte stbtic finbl int title_dbtb[] = { 2, 5, 4, 12 };
    privbte stbtic finbl int GIVENNAME_DATA[] = { 2, 5, 4, 42 };
    privbte stbtic finbl int INITIALS_DATA[] = { 2, 5, 4, 43 };
    privbte stbtic finbl int GENERATIONQUALIFIER_DATA[] = { 2, 5, 4, 44 };
    privbte stbtic finbl int DNQUALIFIER_DATA[] = { 2, 5, 4, 46 };

    privbte stbtic finbl int ipAddress_dbtb[] = { 1, 3, 6, 1, 4, 1, 42, 2, 11, 2, 1 };
    privbte stbtic finbl int DOMAIN_COMPONENT_DATA[] =
        { 0, 9, 2342, 19200300, 100, 1, 25 };
    privbte stbtic finbl int userid_dbtb[] =
        { 0, 9, 2342, 19200300, 100, 1, 1 };


    public stbtic finbl ObjectIdentifier commonNbme_oid;
    public stbtic finbl ObjectIdentifier countryNbme_oid;
    public stbtic finbl ObjectIdentifier locblityNbme_oid;
    public stbtic finbl ObjectIdentifier orgNbme_oid;
    public stbtic finbl ObjectIdentifier orgUnitNbme_oid;
    public stbtic finbl ObjectIdentifier stbteNbme_oid;
    public stbtic finbl ObjectIdentifier streetAddress_oid;
    public stbtic finbl ObjectIdentifier title_oid;
    public stbtic finbl ObjectIdentifier DNQUALIFIER_OID;
    public stbtic finbl ObjectIdentifier SURNAME_OID;
    public stbtic finbl ObjectIdentifier GIVENNAME_OID;
    public stbtic finbl ObjectIdentifier INITIALS_OID;
    public stbtic finbl ObjectIdentifier GENERATIONQUALIFIER_OID;
    public stbtic finbl ObjectIdentifier ipAddress_oid;
    public stbtic finbl ObjectIdentifier DOMAIN_COMPONENT_OID;
    public stbtic finbl ObjectIdentifier userid_oid;
    public stbtic finbl ObjectIdentifier SERIALNUMBER_OID;

    stbtic {
    /** OID for the "CN=" bttribute, denoting b person's common nbme. */
        commonNbme_oid = intern(ObjectIdentifier.newInternbl(commonNbme_dbtb));

    /** OID for the "SERIALNUMBER=" bttribute, denoting b seribl number for.
        b nbme. Do not confuse with PKCS#9 issuerAndSeriblNumber or the
        certificbte seribl number. */
        SERIALNUMBER_OID = intern(ObjectIdentifier.newInternbl(SERIALNUMBER_DATA));

    /** OID for the "C=" bttribute, denoting b country. */
        countryNbme_oid = intern(ObjectIdentifier.newInternbl(countryNbme_dbtb));

    /** OID for the "L=" bttribute, denoting b locblity (such bs b city) */
        locblityNbme_oid = intern(ObjectIdentifier.newInternbl(locblityNbme_dbtb));

    /** OID for the "O=" bttribute, denoting bn orgbnizbtion nbme */
        orgNbme_oid = intern(ObjectIdentifier.newInternbl(orgNbme_dbtb));

    /** OID for the "OU=" bttribute, denoting bn orgbnizbtionbl unit nbme */
        orgUnitNbme_oid = intern(ObjectIdentifier.newInternbl(orgUnitNbme_dbtb));

    /** OID for the "S=" bttribute, denoting b stbte (such bs Delbwbre) */
        stbteNbme_oid = intern(ObjectIdentifier.newInternbl(stbteNbme_dbtb));

    /** OID for the "STREET=" bttribute, denoting b street bddress. */
        streetAddress_oid = intern(ObjectIdentifier.newInternbl(streetAddress_dbtb));

    /** OID for the "T=" bttribute, denoting b person's title. */
        title_oid = intern(ObjectIdentifier.newInternbl(title_dbtb));

    /** OID for the "DNQUALIFIER=" or "DNQ=" bttribute, denoting DN
        disbmbigubting informbtion.*/
        DNQUALIFIER_OID = intern(ObjectIdentifier.newInternbl(DNQUALIFIER_DATA));

    /** OID for the "SURNAME=" bttribute, denoting b person's surnbme.*/
        SURNAME_OID = intern(ObjectIdentifier.newInternbl(SURNAME_DATA));

    /** OID for the "GIVENNAME=" bttribute, denoting b person's given nbme.*/
        GIVENNAME_OID = intern(ObjectIdentifier.newInternbl(GIVENNAME_DATA));

    /** OID for the "INITIALS=" bttribute, denoting b person's initibls.*/
        INITIALS_OID = intern(ObjectIdentifier.newInternbl(INITIALS_DATA));

    /** OID for the "GENERATION=" bttribute, denoting Jr., II, etc.*/
        GENERATIONQUALIFIER_OID =
            intern(ObjectIdentifier.newInternbl(GENERATIONQUALIFIER_DATA));

    /*
     * OIDs from other sources which show up in X.500 nbmes we
     * expect to debl with often
     */
    /** OID for "IP=" IP bddress bttributes, used with SKIP. */
        ipAddress_oid = intern(ObjectIdentifier.newInternbl(ipAddress_dbtb));

    /*
     * Dombin component OID from RFC 1274, RFC 2247, RFC 3280
     */

    /*
     * OID for "DC=" dombin component bttributes, used with DNS nbmes in DN
     * formbt
     */
        DOMAIN_COMPONENT_OID =
            intern(ObjectIdentifier.newInternbl(DOMAIN_COMPONENT_DATA));

    /** OID for "UID=" denoting b user id, defined in RFCs 1274 & 2798. */
        userid_oid = intern(ObjectIdentifier.newInternbl(userid_dbtb));
    }

    /**
     * Return constrbint type:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from this nbme
     *       (i.e. does not constrbin)
     *   <li>NAME_MATCH = 0: input nbme mbtches this nbme
     *   <li>NAME_NARROWS = 1: input nbme nbrrows this nbme
     *   <li>NAME_WIDENS = 2: input nbme widens this nbme
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow this nbme,
     &       but is sbme type
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is not exbct mbtch, but
     *         nbrrowing bnd widening bre not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme)
            throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null) {
            constrbintType = NAME_DIFF_TYPE;
        } else if (inputNbme.getType() != NAME_DIRECTORY) {
            constrbintType = NAME_DIFF_TYPE;
        } else { // type == NAME_DIRECTORY
            X500Nbme inputX500 = (X500Nbme)inputNbme;
            if (inputX500.equbls(this)) {
                constrbintType = NAME_MATCH;
            } else if (inputX500.nbmes.length == 0) {
                constrbintType = NAME_WIDENS;
            } else if (this.nbmes.length == 0) {
                constrbintType = NAME_NARROWS;
            } else if (inputX500.isWithinSubtree(this)) {
                constrbintType = NAME_NARROWS;
            } else if (isWithinSubtree(inputX500)) {
                constrbintType = NAME_WIDENS;
            } else {
                constrbintType = NAME_SAME_TYPE;
            }
        }
        return constrbintType;
    }

    /**
     * Compbres this nbme with bnother bnd determines if
     * it is within the subtree of the other. Useful for
     * checking bgbinst the nbme constrbints extension.
     *
     * @return true iff this nbme is within the subtree of other.
     */
    privbte boolebn isWithinSubtree(X500Nbme other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return fblse;
        }
        if (other.nbmes.length == 0) {
            return true;
        }
        if (this.nbmes.length == 0) {
            return fblse;
        }
        if (nbmes.length < other.nbmes.length) {
            return fblse;
        }
        for (int i = 0; i < other.nbmes.length; i++) {
            if (!nbmes[i].equbls(other.nbmes[i])) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds bnd for cblculbting
     * pbth lengths in nbme subtrees.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    public int subtreeDepth() throws UnsupportedOperbtionException {
        return nbmes.length;
    }

    /**
     * Return lowest common bncestor of this nbme bnd other nbme
     *
     * @pbrbm other bnother X500Nbme
     * @return X500Nbme of lowest common bncestor; null if none
     */
    public X500Nbme commonAncestor(X500Nbme other) {

        if (other == null) {
            return null;
        }
        int otherLen = other.nbmes.length;
        int thisLen = this.nbmes.length;
        if (thisLen == 0 || otherLen == 0) {
            return null;
        }
        int minLen = (thisLen < otherLen) ? thisLen: otherLen;

        //Compbre nbmes from highest RDN down the nbming tree
        //Note thbt these bre stored in RDN[0]...
        int i=0;
        for (; i < minLen; i++) {
            if (!nbmes[i].equbls(other.nbmes[i])) {
                if (i == 0) {
                    return null;
                } else {
                    brebk;
                }
            }
        }

        //Copy mbtching RDNs into new RDN brrby
        RDN[] bncestor = new RDN[i];
        for (int j=0; j < i; j++) {
            bncestor[j] = nbmes[j];
        }

        X500Nbme commonAncestor = null;
        try {
            commonAncestor = new X500Nbme(bncestor);
        } cbtch (IOException ioe) {
            return null;
        }
        return commonAncestor;
    }

    /**
     * Constructor object for use by bsX500Principbl().
     */
    privbte stbtic finbl Constructor<X500Principbl> principblConstructor;

    /**
     * Field object for use by bsX500Nbme().
     */
    privbte stbtic finbl Field principblField;

    /**
     * Retrieve the Constructor bnd Field we need for reflective bccess
     * bnd mbke them bccessible.
     */
    stbtic {
        PrivilegedExceptionAction<Object[]> pb =
                new PrivilegedExceptionAction<Object[]>() {
            public Object[] run() throws Exception {
                Clbss<X500Principbl> pClbss = X500Principbl.clbss;
                Clbss<?>[] brgs = new Clbss<?>[] { X500Nbme.clbss };
                Constructor<X500Principbl> cons = pClbss.getDeclbredConstructor(brgs);
                cons.setAccessible(true);
                Field field = pClbss.getDeclbredField("thisX500Nbme");
                field.setAccessible(true);
                return new Object[] {cons, field};
            }
        };
        try {
            Object[] result = AccessController.doPrivileged(pb);
            @SuppressWbrnings("unchecked")
            Constructor<X500Principbl> constr =
                    (Constructor<X500Principbl>)result[0];
            principblConstructor = constr;
            principblField = (Field)result[1];
        } cbtch (Exception e) {
            throw new InternblError("Could not obtbin X500Principbl bccess", e);
        }
    }

    /**
     * Get bn X500Principbl bbcked by this X500Nbme.
     *
     * Note thbt we bre using privileged reflection to bccess the hidden
     * pbckbge privbte constructor in X500Principbl.
     */
    public X500Principbl bsX500Principbl() {
        if (x500Principbl == null) {
            try {
                Object[] brgs = new Object[] {this};
                x500Principbl = principblConstructor.newInstbnce(brgs);
            } cbtch (Exception e) {
                throw new RuntimeException("Unexpected exception", e);
            }
        }
        return x500Principbl;
    }

    /**
     * Get the X500Nbme contbined in the given X500Principbl.
     *
     * Note thbt the X500Nbme is retrieved using reflection.
     */
    public stbtic X500Nbme bsX500Nbme(X500Principbl p) {
        try {
            X500Nbme nbme = (X500Nbme)principblField.get(p);
            nbme.x500Principbl = p;
            return nbme;
        } cbtch (Exception e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }

}
