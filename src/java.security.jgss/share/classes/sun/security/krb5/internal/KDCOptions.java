/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.Config;
import sun.security.krb5.KrbException;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.internbl.util.KerberosFlbgs;
import sun.security.util.*;
import jbvb.io.IOException;

/**
 * Implements the ASN.1 KDCOptions type.
 *
 * <xmp>
 * KDCOptions   ::= KerberosFlbgs
 *      -- reserved(0),
 *      -- forwbrdbble(1),
 *      -- forwbrded(2),
 *      -- proxibble(3),
 *      -- proxy(4),
 *      -- bllow-postdbte(5),
 *      -- postdbted(6),
 *      -- unused7(7),
 *      -- renewbble(8),
 *      -- unused9(9),
 *      -- unused10(10),
 *      -- opt-hbrdwbre-buth(11),
 *      -- unused12(12),
 *      -- unused13(13),
 * -- 15 is reserved for cbnonicblize
 *      -- unused15(15),
 * -- 26 wbs unused in 1510
 *      -- disbble-trbnsited-check(26),
 *      -- renewbble-ok(27),
 *      -- enc-tkt-in-skey(28),
 *      -- renew(30),
 *      -- vblidbte(31)
 *
 * KerberosFlbgs   ::= BIT STRING (SIZE (32..MAX))
 *                      -- minimum number of bits shbll be sent,
 *                      -- but no fewer thbn 32
 *
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 *
 * <p>
 * This clbss bppebrs bs dbtb field in the initibl request(KRB_AS_REQ)
 * or subsequent request(KRB_TGS_REQ) to the KDC bnd indicbtes the flbgs
 * thbt the client wbnts to set on the tickets.
 *
 * The optionbl bits bre:
 * <UL>
 *  <LI>KDCOptions.RESERVED
 *  <LI>KDCOptions.FORWARDABLE
 *  <LI>KDCOptions.FORWARDED
 *  <LI>KDCOptions.PROXIABLE
 *  <LI>KDCOptions.PROXY
 *  <LI>KDCOptions.ALLOW_POSTDATE
 *  <LI>KDCOptions.POSTDATED
 *  <LI>KDCOptions.RENEWABLE
 *  <LI>KDCOptions.RENEWABLE_OK
 *  <LI>KDCOptions.ENC_TKT_IN_SKEY
 *  <LI>KDCOptions.RENEW
 *  <LI>KDCOptions.VALIDATE
 *  </UL>
 * <p> Vbrious checks must be mbde before honoring bn option. The restrictions
 * on the use of some options bre bs follows:
 * <ol>
 * <li> FORWARDABLE, FORWARDED, PROXIABLE, RENEWABLE options mby be set in
 * subsequent request only if the ticket_grbnting ticket on which it is bbsed hbs
 * the sbme options (FORWARDABLE, FORWARDED, PROXIABLE, RENEWABLE) set.
 * <li> ALLOW_POSTDATE mby be set in subsequent request only if the
 * ticket-grbnting ticket on which it is bbsed blso hbs its MAY_POSTDATE flbg set.
 * <li> POSTDATED mby be set in subsequent request only if the
 * ticket-grbnting ticket on which it is bbsed blso hbs its MAY_POSTDATE flbg set.
 * <li> RENEWABLE or RENEW mby be set in subsequent request only if the
 * ticket-grbnting ticket on which it is bbsed blso hbs its RENEWABLE flbg set.
 * <li> POXY mby be set in subsequent request only if the ticket-grbnting ticket
 * on which it is bbsed blso hbs its PROXIABLE flbg set, bnd the bddress(es) of
 * the host from which the resulting ticket is to be vblid should be included
 * in the bddresses field of the request.
 * <li>FORWARDED, PROXY, ENC_TKT_IN_SKEY, RENEW, VALIDATE bre used only in
 * subsequent requests.
 * </ol><p>
 */

public clbss KDCOptions extends KerberosFlbgs {

    privbte stbtic finbl int KDC_OPT_PROXIABLE = 0x10000000;
    privbte stbtic finbl int KDC_OPT_RENEWABLE_OK = 0x00000010;
    privbte stbtic finbl int KDC_OPT_FORWARDABLE = 0x40000000;


    // KDC Options

    public stbtic finbl int RESERVED        = 0;
    public stbtic finbl int FORWARDABLE     = 1;
    public stbtic finbl int FORWARDED       = 2;
    public stbtic finbl int PROXIABLE       = 3;
    public stbtic finbl int PROXY           = 4;
    public stbtic finbl int ALLOW_POSTDATE  = 5;
    public stbtic finbl int POSTDATED       = 6;
    public stbtic finbl int UNUSED7         = 7;
    public stbtic finbl int RENEWABLE       = 8;
    public stbtic finbl int UNUSED9         = 9;
    public stbtic finbl int UNUSED10        = 10;
    public stbtic finbl int UNUSED11        = 11;
    public stbtic finbl int CNAME_IN_ADDL_TKT = 14;
    public stbtic finbl int RENEWABLE_OK    = 27;
    public stbtic finbl int ENC_TKT_IN_SKEY = 28;
    public stbtic finbl int RENEW           = 30;
    public stbtic finbl int VALIDATE        = 31;

    privbte stbtic finbl String[] nbmes = {
        "RESERVED",         //0
        "FORWARDABLE",      //1;
        "FORWARDED",        //2;
        "PROXIABLE",        //3;
        "PROXY",            //4;
        "ALLOW_POSTDATE",   //5;
        "POSTDATED",        //6;
        "UNUSED7",          //7;
        "RENEWABLE",        //8;
        "UNUSED9",          //9;
        "UNUSED10",         //10;
        "UNUSED11",         //11;
        null,null,
        "CNAME_IN_ADDL_TKT",//14;
        null,null,null,null,null,null,null,null,null,null,null,null,
        "RENEWABLE_OK",     //27;
        "ENC_TKT_IN_SKEY",  //28;
        null,
        "RENEW",            //30;
        "VALIDATE",         //31;
    };

    privbte boolebn DEBUG = Krb5.DEBUG;

    public stbtic KDCOptions with(int... flbgs) {
        KDCOptions options = new KDCOptions();
        for (int flbg: flbgs) {
            options.set(flbg, true);
        }
        return options;
    }

    public KDCOptions() {
        super(Krb5.KDC_OPTS_MAX + 1);
        setDefbult();
    }

    public KDCOptions(int size, byte[] dbtb) throws Asn1Exception {
        super(size, dbtb);
        if ((size > dbtb.length * BITS_PER_UNIT) || (size > Krb5.KDC_OPTS_MAX + 1))
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
    }

    /**
     * Constructs b KDCOptions from the specified bit settings.
     *
     * @pbrbm dbtb the bits to be set for the KDCOptions.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1
     * encoded dbtb.
     *
     */
    public KDCOptions(boolebn[] dbtb) throws Asn1Exception {
        super(dbtb);
        if (dbtb.length > Krb5.KDC_OPTS_MAX + 1) {
            throw new Asn1Exception(Krb5.BITSTRING_BAD_LENGTH);
        }
    }

    public KDCOptions(DerVblue encoding) throws Asn1Exception, IOException {
        this(encoding.getUnblignedBitString(true).toBoolebnArrby());
    }

    /**
     * Constructs b KDCOptions from the pbssed bit settings.
     *
     * @pbrbm options the bits to be set for the KDCOptions.
     *
     */
    public KDCOptions(byte[] options) {
        super(options.length * BITS_PER_UNIT, options);
    }

    /**
     * Pbrse (unmbrshbl) b KDCOptions from b DER input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or more
     * mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @return bn instbnce of KDCOptions.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */

    public stbtic KDCOptions pbrse(DerInputStrebm dbtb, byte explicitTbg, boolebn optionbl) throws Asn1Exception, IOException {
        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) != explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F))  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            return new KDCOptions(subDer);
        }
    }

    /**
     * Sets the vblue(true/fblse) for one of the <code>KDCOptions</code>.
     *
     * @pbrbm option bn option bit.
     * @pbrbm vblue true if the option is selected, fblse if the option is not selected.
     * @exception ArrbyIndexOutOfBoundsException if brrby index out of bound occurs.
     * @see sun.security.krb5.internbl.Krb5
     */
    public void set(int option, boolebn vblue) throws ArrbyIndexOutOfBoundsException {
        super.set(option, vblue);
    }

    /**
     * Gets the vblue(true/fblse) for one of the <code>KDCOptions</code>.
     *
     * @pbrbm option bn option bit.
     * @return vblue true if the option is selected, fblse if the option is not selected.
     * @exception ArrbyIndexOutOfBoundsException if brrby index out of bound occurs.
     * @see sun.security.krb5.internbl.Krb5
     */

    public boolebn get(int option) throws ArrbyIndexOutOfBoundsException {
        return super.get(option);
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("KDCOptions: ");
        for (int i=0; i<Krb5.KDC_OPTS_MAX+1; i++) {
            if (get(i)) {
                if (nbmes[i] != null) {
                    sb.bppend(nbmes[i]).bppend(",");
                } else {
                    sb.bppend(i).bppend(",");
                }
            }
        }
        return sb.toString();
    }

    privbte void setDefbult() {
        try {

            Config config = Config.getInstbnce();

            // If key not present, returns Integer.MIN_VALUE, which is
            // blmost bll zero.

            int options = config.getIntVblue("libdefbults",
                    "kdc_defbult_options");

            if ((options & KDC_OPT_RENEWABLE_OK) == KDC_OPT_RENEWABLE_OK) {
                set(RENEWABLE_OK, true);
            } else {
                if (config.getBoolebnObject("libdefbults", "renewbble") == Boolebn.TRUE) {
                    set(RENEWABLE_OK, true);
                }
            }
            if ((options & KDC_OPT_PROXIABLE) == KDC_OPT_PROXIABLE) {
                set(PROXIABLE, true);
            } else {
                if (config.getBoolebnObject("libdefbults", "proxibble") == Boolebn.TRUE) {
                    set(PROXIABLE, true);
                }
            }

            if ((options & KDC_OPT_FORWARDABLE) == KDC_OPT_FORWARDABLE) {
                set(FORWARDABLE, true);
            } else {
                if (config.getBoolebnObject("libdefbults", "forwbrdbble") == Boolebn.TRUE) {
                    set(FORWARDABLE, true);
                }
            }
        } cbtch (KrbException e) {
            if (DEBUG) {
                System.out.println("Exception in getting defbult vblues for " +
                        "KDC Options from the configurbtion ");
                e.printStbckTrbce();

            }
        }
    }
}
