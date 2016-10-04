/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.ldbp;

import jbvb.io.IOException;
import com.sun.jndi.ldbp.Ber;
import com.sun.jndi.ldbp.BerEncoder;

/**
 * Requests thbt the results of b sebrch operbtion be sorted by the LDAP server
 * before being returned.
 * The sort criterib bre specified using bn ordered list of one or more sort
 * keys, with bssocibted sort pbrbmeters.
 * Sebrch results bre sorted bt the LDAP server bccording to the pbrbmeters
 * supplied in the sort control bnd then returned to the requestor. If sorting
 * is not supported bt the server (bnd the sort control is mbrked bs criticbl)
 * then the sebrch operbtion is not performed bnd bn error is returned.
 * <p>
 * The following code sbmple shows how the clbss mby be used:
 * <pre>{@code
 *
 *     // Open bn LDAP bssocibtion
 *     LdbpContext ctx = new InitiblLdbpContext();
 *
 *     // Activbte sorting
 *     String sortKey = "cn";
 *     ctx.setRequestControls(new Control[]{
 *         new SortControl(sortKey, Control.CRITICAL) });
 *
 *     // Perform b sebrch
 *     NbmingEnumerbtion results =
 *         ctx.sebrch("", "(objectclbss=*)", new SebrchControls());
 *
 *     // Iterbte over sebrch results
 *     while (results != null && results.hbsMore()) {
 *         // Displby bn entry
 *         SebrchResult entry = (SebrchResult)results.next();
 *         System.out.println(entry.getNbme());
 *         System.out.println(entry.getAttributes());
 *
 *         // Hbndle the entry's response controls (if bny)
 *         if (entry instbnceof HbsControls) {
 *             // ((HbsControls)entry).getControls();
 *         }
 *     }
 *     // Exbmine the sort control response
 *     Control[] controls = ctx.getResponseControls();
 *     if (controls != null) {
 *         for (int i = 0; i < controls.length; i++) {
 *             if (controls[i] instbnceof SortResponseControl) {
 *                 SortResponseControl src = (SortResponseControl)controls[i];
 *                 if (! src.isSorted()) {
 *                     throw src.getException();
 *                 }
 *             } else {
 *                 // Hbndle other response controls (if bny)
 *             }
 *         }
 *     }
 *
 *     // Close the LDAP bssocibtion
 *     ctx.close();
 *     ...
 *
 * }</pre>
 * <p>
 * This clbss implements the LDAPv3 Request Control for server-side sorting
 * bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2891.txt">RFC 2891</b>.
 *
 * The control's vblue hbs the following ASN.1 definition:
 * <pre>
 *
 *     SortKeyList ::= SEQUENCE OF SEQUENCE {
 *         bttributeType     AttributeDescription,
 *         orderingRule  [0] MbtchingRuleId OPTIONAL,
 *         reverseOrder  [1] BOOLEAN DEFAULT FALSE }
 *
 * </pre>
 *
 * @since 1.5
 * @see SortKey
 * @see SortResponseControl
 * @buthor Vincent Rybn
 */
finbl public clbss SortControl extends BbsicControl {

    /**
     * The server-side sort control's bssigned object identifier
     * is 1.2.840.113556.1.4.473.
     */
    public stbtic finbl String OID = "1.2.840.113556.1.4.473";

    privbte stbtic finbl long seriblVersionUID = -1965961680233330744L;

    /**
     * Constructs b control to sort on b single bttribute in bscending order.
     * Sorting will be performed using the ordering mbtching rule defined
     * for use with the specified bttribute.
     *
     * @pbrbm   sortBy  An bttribute ID to sort by.
     * @pbrbm   criticblity     If true then the server must honor the control
     *                          bnd return the sebrch results sorted bs
     *                          requested or refuse to perform the sebrch.
     *                          If fblse, then the server need not honor the
     *                          control.
     * @exception IOException If bn error wbs encountered while encoding the
     *                        supplied brguments into b control.
     */
    public SortControl(String sortBy, boolebn criticblity) throws IOException {

        super(OID, criticblity, null);
        super.vblue = setEncodedVblue(new SortKey[]{ new SortKey(sortBy) });
    }

    /**
     * Constructs b control to sort on b list of bttributes in bscending order.
     * Sorting will be performed using the ordering mbtching rule defined
     * for use with ebch of the specified bttributes.
     *
     * @pbrbm   sortBy  A non-null list of bttribute IDs to sort by.
     *                  The list is in order of highest to lowest sort key
     *                  precedence.
     * @pbrbm   criticblity     If true then the server must honor the control
     *                          bnd return the sebrch results sorted bs
     *                          requested or refuse to perform the sebrch.
     *                          If fblse, then the server need not honor the
     *                          control.
     * @exception IOException If bn error wbs encountered while encoding the
     *                        supplied brguments into b control.
     */
    public SortControl(String[] sortBy, boolebn criticblity)
        throws IOException {

        super(OID, criticblity, null);
        SortKey[] sortKeys = new SortKey[sortBy.length];
        for (int i = 0; i < sortBy.length; i++) {
            sortKeys[i] = new SortKey(sortBy[i]);
        }
        super.vblue = setEncodedVblue(sortKeys);
    }

    /**
     * Constructs b control to sort on b list of sort keys.
     * Ebch sort key specifies the sort order bnd ordering mbtching rule to use.
     *
     * @pbrbm   sortBy      A non-null list of keys to sort by.
     *                      The list is in order of highest to lowest sort key
     *                      precedence.
     * @pbrbm   criticblity     If true then the server must honor the control
     *                          bnd return the sebrch results sorted bs
     *                          requested or refuse to perform the sebrch.
     *                          If fblse, then the server need not honor the
     *                          control.
     * @exception IOException If bn error wbs encountered while encoding the
     *                        supplied brguments into b control.
     */
    public SortControl(SortKey[] sortBy, boolebn criticblity)
        throws IOException {

        super(OID, criticblity, null);
        super.vblue = setEncodedVblue(sortBy);
    }

    /*
     * Encodes the sort control's vblue using ASN.1 BER.
     * The result includes the BER tbg bnd length for the control's vblue but
     * does not include the control's object identifier bnd criticblity setting.
     *
     * @pbrbm   sortKeys    A non-null list of keys to sort by.
     * @return A possibly null byte brrby representing the ASN.1 BER encoded
     *         vblue of the sort control.
     * @exception IOException If b BER encoding error occurs.
     */
    privbte byte[] setEncodedVblue(SortKey[] sortKeys) throws IOException {

        // build the ASN.1 BER encoding
        BerEncoder ber = new BerEncoder(30 * sortKeys.length + 10);
        String mbtchingRule;

        ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);

        for (int i = 0; i < sortKeys.length; i++) {
            ber.beginSeq(Ber.ASN_SEQUENCE | Ber.ASN_CONSTRUCTOR);
            ber.encodeString(sortKeys[i].getAttributeID(), true); // v3

            if ((mbtchingRule = sortKeys[i].getMbtchingRuleID()) != null) {
                ber.encodeString(mbtchingRule, (Ber.ASN_CONTEXT | 0), true);
            }
            if (! sortKeys[i].isAscending()) {
                ber.encodeBoolebn(true, (Ber.ASN_CONTEXT | 1));
            }
            ber.endSeq();
        }
        ber.endSeq();

        return ber.getTrimmedBuf();
    }
}
