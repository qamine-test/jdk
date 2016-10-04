/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import sun.security.util.DerVblue;
import sun.security.util.DerOutputStrebm;

/**
 * This clbss defines the X400Address of the GenerblNbme choice.
 * <p>
 * The ASN.1 syntbx for this is:
 * <pre>
 * ORAddress ::= SEQUENCE {
 *    built-in-stbndbrd-bttributes BuiltInStbndbrdAttributes,
 *    built-in-dombin-defined-bttributes
 *                         BuiltInDombinDefinedAttributes OPTIONAL,
 *    -- see blso teletex-dombin-defined-bttributes
 *    extension-bttributes ExtensionAttributes OPTIONAL }
 * --      The OR-bddress is sembnticblly bbsent from the OR-nbme if the
 * --      built-in-stbndbrd-bttribute sequence is empty bnd the
 * --      built-in-dombin-defined-bttributes bnd extension-bttributes bre
 * --      both omitted.
 *
 * --      Built-in Stbndbrd Attributes
 *
 * BuiltInStbndbrdAttributes ::= SEQUENCE {
 *    country-nbme CountryNbme OPTIONAL,
 *    bdministrbtion-dombin-nbme AdministrbtionDombinNbme OPTIONAL,
 *    network-bddress      [0] NetworkAddress OPTIONAL,
 *    -- see blso extended-network-bddress
 *    terminbl-identifier  [1] TerminblIdentifier OPTIONAL,
 *    privbte-dombin-nbme  [2] PrivbteDombinNbme OPTIONAL,
 *    orgbnizbtion-nbme    [3] OrgbnizbtionNbme OPTIONAL,
 *    -- see blso teletex-orgbnizbtion-nbme
 *    numeric-user-identifier      [4] NumericUserIdentifier OPTIONAL,
 *    personbl-nbme        [5] PersonblNbme OPTIONAL,
 *    -- see blso teletex-personbl-nbme
 *    orgbnizbtionbl-unit-nbmes    [6] OrgbnizbtionblUnitNbmes OPTIONAL
 *    -- see blso teletex-orgbnizbtionbl-unit-nbmes -- }
 *
 * CountryNbme ::= [APPLICATION 1] CHOICE {
 *    x121-dcc-code NumericString
 *                 (SIZE (ub-country-nbme-numeric-length)),
 *    iso-3166-blphb2-code PrintbbleString
 *                 (SIZE (ub-country-nbme-blphb-length)) }
 *
 * AdministrbtionDombinNbme ::= [APPLICATION 2] CHOICE {
 *    numeric NumericString (SIZE (0..ub-dombin-nbme-length)),
 *    printbble PrintbbleString (SIZE (0..ub-dombin-nbme-length)) }
 *
 * NetworkAddress ::= X121Address  -- see blso extended-network-bddress
 *
 * X121Address ::= NumericString (SIZE (1..ub-x121-bddress-length))
 *
 * TerminblIdentifier ::= PrintbbleString (SIZE (1..ub-terminbl-id-length))
 *
 * PrivbteDombinNbme ::= CHOICE {
 *    numeric NumericString (SIZE (1..ub-dombin-nbme-length)),
 *    printbble PrintbbleString (SIZE (1..ub-dombin-nbme-length)) }
 *
 * OrgbnizbtionNbme ::= PrintbbleString
 *                             (SIZE (1..ub-orgbnizbtion-nbme-length))
 * -- see blso teletex-orgbnizbtion-nbme
 *
 * NumericUserIdentifier ::= NumericString
 *                             (SIZE (1..ub-numeric-user-id-length))
 *
 * PersonblNbme ::= SET {
 *    surnbme [0] PrintbbleString (SIZE (1..ub-surnbme-length)),
 *    given-nbme [1] PrintbbleString
 *                         (SIZE (1..ub-given-nbme-length)) OPTIONAL,
 *    initibls [2] PrintbbleString (SIZE (1..ub-initibls-length)) OPTIONAL,
 *    generbtion-qublifier [3] PrintbbleString
 *                 (SIZE (1..ub-generbtion-qublifier-length)) OPTIONAL }
 * -- see blso teletex-personbl-nbme
 *
 * OrgbnizbtionblUnitNbmes ::= SEQUENCE SIZE (1..ub-orgbnizbtionbl-units)
 *                                         OF OrgbnizbtionblUnitNbme
 * -- see blso teletex-orgbnizbtionbl-unit-nbmes
 *
 * OrgbnizbtionblUnitNbme ::= PrintbbleString (SIZE
 *                         (1..ub-orgbnizbtionbl-unit-nbme-length))
 *
 * --      Built-in Dombin-defined Attributes
 *
 * BuiltInDombinDefinedAttributes ::= SEQUENCE SIZE
 *                                 (1..ub-dombin-defined-bttributes) OF
 *                                 BuiltInDombinDefinedAttribute
 *
 * BuiltInDombinDefinedAttribute ::= SEQUENCE {
 *    type PrintbbleString (SIZE
 *                         (1..ub-dombin-defined-bttribute-type-length)),
 *    vblue PrintbbleString (SIZE
 *                         (1..ub-dombin-defined-bttribute-vblue-length))}
 *
 * --      Extension Attributes
 *
 * ExtensionAttributes ::= SET SIZE (1..ub-extension-bttributes) OF
 *                         ExtensionAttribute
 *
 * ExtensionAttribute ::=  SEQUENCE {
 *    extension-bttribute-type [0] INTEGER (0..ub-extension-bttributes),
 *    extension-bttribute-vblue [1]
 *                         ANY DEFINED BY extension-bttribute-type }
 *
 * -- Extension types bnd bttribute vblues
 * --
 *
 * common-nbme INTEGER ::= 1
 *
 * CommonNbme ::= PrintbbleString (SIZE (1..ub-common-nbme-length))
 *
 * teletex-common-nbme INTEGER ::= 2
 *
 * TeletexCommonNbme ::= TeletexString (SIZE (1..ub-common-nbme-length))
 *
 * teletex-orgbnizbtion-nbme INTEGER ::= 3
 *
 * TeletexOrgbnizbtionNbme ::=
 *                 TeletexString (SIZE (1..ub-orgbnizbtion-nbme-length))
 *
 * teletex-personbl-nbme INTEGER ::= 4
 *
 * TeletexPersonblNbme ::= SET {
 *    surnbme [0] TeletexString (SIZE (1..ub-surnbme-length)),
 *    given-nbme [1] TeletexString
 *                 (SIZE (1..ub-given-nbme-length)) OPTIONAL,
 *    initibls [2] TeletexString (SIZE (1..ub-initibls-length)) OPTIONAL,
 *    generbtion-qublifier [3] TeletexString (SIZE
 *                 (1..ub-generbtion-qublifier-length)) OPTIONAL }
 *
 * teletex-orgbnizbtionbl-unit-nbmes INTEGER ::= 5
 *
 * TeletexOrgbnizbtionblUnitNbmes ::= SEQUENCE SIZE
 *         (1..ub-orgbnizbtionbl-units) OF TeletexOrgbnizbtionblUnitNbme
 *
 * TeletexOrgbnizbtionblUnitNbme ::= TeletexString
 *                         (SIZE (1..ub-orgbnizbtionbl-unit-nbme-length))
 *
 * pds-nbme INTEGER ::= 7
 *
 * PDSNbme ::= PrintbbleString (SIZE (1..ub-pds-nbme-length))
 *
 * physicbl-delivery-country-nbme INTEGER ::= 8
 *
 * PhysicblDeliveryCountryNbme ::= CHOICE {
 *    x121-dcc-code NumericString (SIZE (ub-country-nbme-numeric-length)),
 *    iso-3166-blphb2-code PrintbbleString
 *                         (SIZE (ub-country-nbme-blphb-length)) }
 *
 * postbl-code INTEGER ::= 9
 *
 * PostblCode ::= CHOICE {
 *    numeric-code NumericString (SIZE (1..ub-postbl-code-length)),
 *    printbble-code PrintbbleString (SIZE (1..ub-postbl-code-length)) }
 *
 * physicbl-delivery-office-nbme INTEGER ::= 10
 *
 * PhysicblDeliveryOfficeNbme ::= PDSPbrbmeter
 *
 * physicbl-delivery-office-number INTEGER ::= 11
 *
 * PhysicblDeliveryOfficeNumber ::= PDSPbrbmeter
 *
 * extension-OR-bddress-components INTEGER ::= 12
 *
 * ExtensionORAddressComponents ::= PDSPbrbmeter
 *
 * physicbl-delivery-personbl-nbme INTEGER ::= 13
 *
 * PhysicblDeliveryPersonblNbme ::= PDSPbrbmeter
 *
 * physicbl-delivery-orgbnizbtion-nbme INTEGER ::= 14
 *
 * PhysicblDeliveryOrgbnizbtionNbme ::= PDSPbrbmeter
 *
 * extension-physicbl-delivery-bddress-components INTEGER ::= 15
 *
 * ExtensionPhysicblDeliveryAddressComponents ::= PDSPbrbmeter
 *
 * unformbtted-postbl-bddress INTEGER ::= 16
 *
 * UnformbttedPostblAddress ::= SET {
 *    printbble-bddress SEQUENCE SIZE (1..ub-pds-physicbl-bddress-lines) OF
 *            PrintbbleString (SIZE (1..ub-pds-pbrbmeter-length)) OPTIONAL,
 *    teletex-string TeletexString
 *          (SIZE (1..ub-unformbtted-bddress-length)) OPTIONAL }
 *
 * street-bddress INTEGER ::= 17
 *
 * StreetAddress ::= PDSPbrbmeter
 *
 * post-office-box-bddress INTEGER ::= 18
 *
 * PostOfficeBoxAddress ::= PDSPbrbmeter
 *
 * poste-restbnte-bddress INTEGER ::= 19
 *
 * PosteRestbnteAddress ::= PDSPbrbmeter
 *
 * unique-postbl-nbme INTEGER ::= 20
 *
 * UniquePostblNbme ::= PDSPbrbmeter
 *
 * locbl-postbl-bttributes INTEGER ::= 21
 *
 * LocblPostblAttributes ::= PDSPbrbmeter
 *
 * PDSPbrbmeter ::= SET {
 *    printbble-string PrintbbleString
 *                 (SIZE(1..ub-pds-pbrbmeter-length)) OPTIONAL,
 *    teletex-string TeletexString
 *                 (SIZE(1..ub-pds-pbrbmeter-length)) OPTIONAL }
 *
 * extended-network-bddress INTEGER ::= 22
 *
 * ExtendedNetworkAddress ::= CHOICE {
 *    e163-4-bddress SEQUENCE {
 *         number [0] NumericString (SIZE (1..ub-e163-4-number-length)),
 *         sub-bddress [1] NumericString
 *                 (SIZE (1..ub-e163-4-sub-bddress-length)) OPTIONAL },
 *    psbp-bddress [0] PresentbtionAddress }
 *
 * PresentbtionAddress ::= SEQUENCE {
 *         pSelector       [0] EXPLICIT OCTET STRING OPTIONAL,
 *         sSelector       [1] EXPLICIT OCTET STRING OPTIONAL,
 *         tSelector       [2] EXPLICIT OCTET STRING OPTIONAL,
 *         nAddresses      [3] EXPLICIT SET SIZE (1..MAX) OF OCTET STRING }
 *
 * terminbl-type  INTEGER ::= 23
 *
 * TerminblType ::= INTEGER {
 *    telex (3),
 *    teletex (4),
 *    g3-fbcsimile (5),
 *    g4-fbcsimile (6),
 *    ib5-terminbl (7),
 *    videotex (8) } (0..ub-integer-options)
 *
 * --      Extension Dombin-defined Attributes
 *
 * teletex-dombin-defined-bttributes INTEGER ::= 6
 *
 * TeletexDombinDefinedAttributes ::= SEQUENCE SIZE
 *    (1..ub-dombin-defined-bttributes) OF TeletexDombinDefinedAttribute
 *
 * TeletexDombinDefinedAttribute ::= SEQUENCE {
 *         type TeletexString
 *                (SIZE (1..ub-dombin-defined-bttribute-type-length)),
 *         vblue TeletexString
 *                (SIZE (1..ub-dombin-defined-bttribute-vblue-length)) }
 *
 * --  specificbtions of Upper Bounds shbll be regbrded bs mbndbtory
 * --  from Annex B of ITU-T X.411 Reference Definition of MTS Pbrbmeter
 * --  Upper Bounds
 *
 * --      Upper Bounds
 * ub-nbme INTEGER ::=     32768
 * ub-common-nbme  INTEGER ::=     64
 * ub-locblity-nbme        INTEGER ::=     128
 * ub-stbte-nbme   INTEGER ::=     128
 * ub-orgbnizbtion-nbme    INTEGER ::=     64
 * ub-orgbnizbtionbl-unit-nbme     INTEGER ::=     64
 * ub-title        INTEGER ::=     64
 * ub-mbtch        INTEGER ::=     128
 *
 * ub-embilbddress-length INTEGER ::= 128
 *
 * ub-common-nbme-length INTEGER ::= 64
 * ub-country-nbme-blphb-length INTEGER ::= 2
 * ub-country-nbme-numeric-length INTEGER ::= 3
 * ub-dombin-defined-bttributes INTEGER ::= 4
 * ub-dombin-defined-bttribute-type-length INTEGER ::= 8
 * ub-dombin-defined-bttribute-vblue-length INTEGER ::= 128
 * ub-dombin-nbme-length INTEGER ::= 16
 * ub-extension-bttributes INTEGER ::= 256
 * ub-e163-4-number-length INTEGER ::= 15
 * ub-e163-4-sub-bddress-length INTEGER ::= 40
 * ub-generbtion-qublifier-length INTEGER ::= 3
 * ub-given-nbme-length INTEGER ::= 16
 * ub-initibls-length INTEGER ::= 5
 * ub-integer-options INTEGER ::= 256
 * ub-numeric-user-id-length INTEGER ::= 32
 * ub-orgbnizbtion-nbme-length INTEGER ::= 64
 * ub-orgbnizbtionbl-unit-nbme-length INTEGER ::= 32
 * ub-orgbnizbtionbl-units INTEGER ::= 4
 * ub-pds-nbme-length INTEGER ::= 16
 * ub-pds-pbrbmeter-length INTEGER ::= 30
 * ub-pds-physicbl-bddress-lines INTEGER ::= 6
 * ub-postbl-code-length INTEGER ::= 16
 * ub-surnbme-length INTEGER ::= 40
 * ub-terminbl-id-length INTEGER ::= 24
 * ub-unformbtted-bddress-length INTEGER ::= 180
 * ub-x121-bddress-length INTEGER ::= 16
 *
 * -- Note - upper bounds on string types, such bs TeletexString, bre
 * -- mebsured in chbrbcters.  Excepting PrintbbleString or IA5String, b
 * -- significbntly grebter number of octets will be required to hold
 * -- such b vblue.  As b minimum, 16 octets, or twice the specified upper
 * -- bound, whichever is the lbrger, should be bllowed for TeletexString.
 * -- For UTF8String or UniversblString bt lebst four times the upper
 * -- bound should be bllowed.
 * </pre>
 *
 * @buthor Anne Anderson
 * @since       1.4
 * @see GenerblNbme
 * @see GenerblNbmes
 * @see GenerblNbmeInterfbce
 */
public clbss X400Address implements GenerblNbmeInterfbce {

    // Privbte dbtb members
    byte[] nbmeVblue = null;

    /**
     * Crebte the X400Address object from the specified byte brrby
     *
     * @pbrbm nbmeVblue vblue of the nbme bs b byte brrby
     */
    public X400Address(byte[] vblue) {
        nbmeVblue = vblue;
    }

    /**
     * Crebte the X400Address object from the pbssed encoded Der vblue.
     *
     * @pbrbm derVblue the encoded DER X400Address.
     * @exception IOException on error.
     */
    public X400Address(DerVblue derVblue) throws IOException {
        nbmeVblue = derVblue.toByteArrby();
    }

    /**
     * Return the type of the GenerblNbme.
     */
    public int getType() {
        return (GenerblNbmeInterfbce.NAME_X400);
    }

    /**
     * Encode the X400 nbme into the DerOutputStrebm.
     *
     * @pbrbm out the DER strebm to encode the X400Address to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerVblue derVblue = new DerVblue(nbmeVblue);
        out.putDerVblue(derVblue);
    }

    /**
     * Return the printbble string.
     */
    public String toString() {
        return ("X400Address: <DER-encoded vblue>");
    }

    /**
     * Return type of constrbint inputNbme plbces on this nbme:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme (i.e. does not constrbin).
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme.
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme (is lower in the nbming subtree)
     *   <li>NAME_WIDENS = 2: input nbme widens nbme (is higher in the nbming subtree)
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme, but is sbme type.
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is sbme type, but compbrison operbtions bre
     *          not supported for this nbme type.
     */
    public int constrbins(GenerblNbmeInterfbce inputNbme) throws UnsupportedOperbtionException {
        int constrbintType;
        if (inputNbme == null)
            constrbintType = NAME_DIFF_TYPE;
        else if (inputNbme.getType() != NAME_X400)
            constrbintType = NAME_DIFF_TYPE;
        else
            //Nbrrowing, widening, bnd mbtch constrbints not defined in rfc2459 for X400Address
            throw new UnsupportedOperbtionException("Nbrrowing, widening, bnd mbtch bre not supported for X400Address.");
        return constrbintType;
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
        throw new UnsupportedOperbtionException("subtreeDepth not supported for X400Address");
    }

}
