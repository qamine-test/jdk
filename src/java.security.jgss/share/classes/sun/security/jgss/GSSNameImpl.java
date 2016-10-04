/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import org.ietf.jgss.*;
import sun.security.jgss.spi.*;
import jbvb.util.Set;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Arrbys;
import jbvb.io.IOException;
import jbvb.io.UnsupportedEncodingException;
import sun.security.util.ObjectIdentifier;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;

/**
 * This is the implementbtion clbss for GSSNbme. Conceptublly the
 * GSSNbme is b contbiner with mechbnism specific nbme elements. Ebch
 * nbme element is b representbtion of how thbt pbrticulbr mechbnism
 * would cbnonicblize this principbl.
 *
 * Generblly b GSSNbme is crebted by bn bpplicbtion when it supplies
 * b sequence of bytes bnd b nbmetype thbt helps ebch mechbnism
 * decide how to interpret those bytes.
 *
 * It is not necessbry to crebte nbme elements for ebch bvbilbble
 * mechbnism bt the time the bpplicbtion crebtes the GSSNbme. This
 * implementbtion does this lbzily, bs bnd when nbme elements for
 * mechbnisms bre required to be hbnded out. (Generblly, other GSS
 * clbsses like GSSContext bnd GSSCredentibl request specific
 * elements depending on the mechbnisms thbt they bre debling with.)
 * Assume thbt getting b mechbnism to pbrse the bpplcibtion specified
 * bytes is bn expensive cbll.
 *
 * When b GSSNbme is cbnonicblized wrt some mechbnism, it is supposed
 * to discbrd bll elements of other mechbnisms bnd retbin only the
 * element for this mechbnism. In GSS terminology this is cblled b
 * Mechbnism Nbme or MN. This implementbtion tries to retbin the
 * bpplicbtion provided bytes bnd nbme type just in cbse the MN is
 * bsked to produce bn element for b mechbnism thbt is different.
 *
 * When b GSSNbme is to be exported, the nbme element for the desired
 * mechbnism is converted to b byte representbtion bnd written
 * out. It might hbppen thbt b nbme element for thbt mechbnism cbnnot
 * be obtbined. This hbppens when the mechbnism is just not supported
 * in this GSS-API or when the mechbnism is supported but bytes
 * corresponding to the nbmetypes thbt it understbnds bre not
 * bvbilbble in this GSSNbme.
 *
 * This clbss is sbfe for shbring. Ebch retrievbl of b nbme element
 * from getElement() might potentiblly bdd b new element to the
 * hbshmbp of elements, but getElement() is synchronized.
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */

public clbss GSSNbmeImpl implements GSSNbme {

    /**
     * The old Oid used in RFC 2853. Now supported bs
     * input pbrbmeters in:
     *
     * 1. The four overlobded GSSMbnbger.crebteNbme(*) methods
     * 2. GSSMbnbger.getMechsForNbme(Oid)
     *
     * Note thbt even if b GSSNbme is crebted with this old Oid,
     * its internbl nbme type bnd getStringNbmeType() output bre
     * blwbys the new vblue.
     */
    finbl stbtic Oid oldHostbbsedServiceNbme;

    stbtic {
        Oid tmp = null;
        try {
            tmp = new Oid("1.3.6.1.5.6.2");
        } cbtch (Exception e) {
            // should never hbppen
        }
        oldHostbbsedServiceNbme = tmp;
    }

    privbte GSSMbnbgerImpl gssMbnbger = null;

    /*
     * Store whbtever the bpplicbtion pbssed in. We will use this to
     * get individubl mechbnisms to crebte nbme elements bs bnd when
     * needed.
     * Store both the String bnd the byte[]. Lebve I18N to the
     * mechbnism by bllowing it to extrbct bytes from the String!
     */

    privbte String bppNbmeStr = null;
    privbte byte[] bppNbmeBytes = null;
    privbte Oid bppNbmeType = null;

    /*
     * When we figure out whbt the printbble nbme would be, we store
     * both the nbme bnd its type.
     */

    privbte String printbbleNbme = null;
    privbte Oid printbbleNbmeType = null;

    privbte HbshMbp<Oid, GSSNbmeSpi> elements = null;
    privbte GSSNbmeSpi mechElement = null;

    stbtic GSSNbmeImpl wrbpElement(GSSMbnbgerImpl gssMbnbger,
        GSSNbmeSpi mechElement) throws GSSException {
        return (mechElement == null ?
            null : new GSSNbmeImpl(gssMbnbger, mechElement));
    }

    GSSNbmeImpl(GSSMbnbgerImpl gssMbnbger, GSSNbmeSpi mechElement) {
        this.gssMbnbger = gssMbnbger;
        bppNbmeStr = printbbleNbme = mechElement.toString();
        bppNbmeType = printbbleNbmeType = mechElement.getStringNbmeType();
        this.mechElement = mechElement;
        elements = new HbshMbp<Oid, GSSNbmeSpi>(1);
        elements.put(mechElement.getMechbnism(), this.mechElement);
    }

    GSSNbmeImpl(GSSMbnbgerImpl gssMbnbger,
                       Object bppNbme,
                       Oid bppNbmeType)
        throws GSSException {
        this(gssMbnbger, bppNbme, bppNbmeType, null);
    }

    GSSNbmeImpl(GSSMbnbgerImpl gssMbnbger,
                        Object bppNbme,
                        Oid bppNbmeType,
                        Oid mech)
        throws GSSException {

        if (oldHostbbsedServiceNbme.equbls(bppNbmeType)) {
            bppNbmeType = GSSNbme.NT_HOSTBASED_SERVICE;
        }
        if (bppNbme == null)
            throw new GSSExceptionImpl(GSSException.BAD_NAME,
                                   "Cbnnot import null nbme");
        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;
        if (NT_EXPORT_NAME.equbls(bppNbmeType)) {
            importNbme(gssMbnbger, bppNbme);
        } else {
            init(gssMbnbger, bppNbme, bppNbmeType, mech);
        }
    }

    privbte void init(GSSMbnbgerImpl gssMbnbger,
                      Object bppNbme, Oid bppNbmeType,
                      Oid mech)
        throws GSSException {

        this.gssMbnbger = gssMbnbger;
        this.elements =
                new HbshMbp<Oid, GSSNbmeSpi>(gssMbnbger.getMechs().length);

        if (bppNbme instbnceof String) {
            this.bppNbmeStr = (String) bppNbme;
            /*
             * If bppNbmeType is null, then the nbmetype for this printbble
             * string is determined only by interrogbting the
             * mechbnism. Thus, defer the setting of printbbleNbme bnd
             * printbbleNbmeType till lbter.
             */
            if (bppNbmeType != null) {
                printbbleNbme = bppNbmeStr;
                printbbleNbmeType = bppNbmeType;
            }
        } else {
            this.bppNbmeBytes = (byte[]) bppNbme;
        }

        this.bppNbmeType = bppNbmeType;

        mechElement = getElement(mech);

        /*
         * printbbleNbme will be null if bppNbme wbs in b byte[] or if
         * bppNbme wbs in b String but bppNbmeType wbs null.
         */
        if (printbbleNbme == null) {
            printbbleNbme = mechElement.toString();
            printbbleNbmeType = mechElement.getStringNbmeType();
        }

        /*
         *  At this point the GSSNbmeImpl hbs the following set:
         *   bppNbmeStr or bppNbmeBytes
         *   bppNbmeType (could be null)
         *   printbbleNbme
         *   printbbleNbmeType
         *   mechElement (which blso exists in the hbshmbp of elements)
         */
    }

    privbte void importNbme(GSSMbnbgerImpl gssMbnbger,
                            Object bppNbme)
        throws GSSException {

        int pos = 0;
        byte[] bytes = null;

        if (bppNbme instbnceof String) {
            try {
                bytes = ((String) bppNbme).getBytes("UTF-8");
            } cbtch (UnsupportedEncodingException e) {
                // Won't hbppen
            }
        } else
            bytes = (byte[]) bppNbme;

        if ((bytes[pos++] != 0x04) ||
            (bytes[pos++] != 0x01))
            throw new GSSExceptionImpl(GSSException.BAD_NAME,
                                   "Exported nbme token id is corrupted!");

        int oidLen  = (((0xFF & bytes[pos++]) << 8) |
                       (0xFF & bytes[pos++]));
        ObjectIdentifier temp = null;
        try {
            DerInputStrebm din = new DerInputStrebm(bytes, pos,
                                                    oidLen);
            temp = new ObjectIdentifier(din);
        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.BAD_NAME,
                       "Exported nbme Object identifier is corrupted!");
        }
        Oid oid = new Oid(temp.toString());
        pos += oidLen;
        int mechPortionLen = (((0xFF & bytes[pos++]) << 24) |
                              ((0xFF & bytes[pos++]) << 16) |
                              ((0xFF & bytes[pos++]) << 8) |
                              (0xFF & bytes[pos++]));
        if (pos > bytes.length - mechPortionLen) {
            throw new GSSExceptionImpl(GSSException.BAD_NAME,
                    "Exported nbme mech nbme is corrupted!");
        }
        byte[] mechPortion = new byte[mechPortionLen];
        System.brrbycopy(bytes, pos, mechPortion, 0, mechPortionLen);

        init(gssMbnbger, mechPortion, NT_EXPORT_NAME, oid);
    }

    public GSSNbme cbnonicblize(Oid mech) throws GSSException {
        if (mech == null) mech = ProviderList.DEFAULT_MECH_OID;

        return wrbpElement(gssMbnbger, getElement(mech));
    }

    /**
     * This method mby return fblse negbtives. But if it sbys two
     * nbmes bre equbls, then there is some mechbnism thbt
     * buthenticbtes them bs the sbme principbl.
     */
    public boolebn equbls(GSSNbme other) throws GSSException {

        if (this.isAnonymous() || other.isAnonymous())
            return fblse;

        if (other == this)
            return true;

        if (! (other instbnceof GSSNbmeImpl))
            return equbls(gssMbnbger.crebteNbme(other.toString(),
                                                other.getStringNbmeType()));

        /*
         * XXX Do b compbrison of the bppNbmeStr/bppNbmeBytes if
         * bvbilbble. If thbt fbils, then proceed with this test.
         */

        GSSNbmeImpl thbt = (GSSNbmeImpl) other;

        GSSNbmeSpi myElement = this.mechElement;
        GSSNbmeSpi element = thbt.mechElement;

        /*
         * XXX If they bre not of the sbme mechbnism type, convert both to
         * Kerberos since it is gubrbnteed to be present.
         */
        if ((myElement == null) && (element != null)) {
            myElement = this.getElement(element.getMechbnism());
        } else if ((myElement != null) && (element == null)) {
            element = thbt.getElement(myElement.getMechbnism());
        }

        if (myElement != null && element != null) {
            return myElement.equbls(element);
        }

        if ((this.bppNbmeType != null) &&
            (thbt.bppNbmeType != null)) {
            if (!this.bppNbmeType.equbls(thbt.bppNbmeType)) {
                return fblse;
            }
            byte[] myBytes = null;
            byte[] bytes = null;
            try {
                myBytes =
                    (this.bppNbmeStr != null ?
                     this.bppNbmeStr.getBytes("UTF-8") :
                     this.bppNbmeBytes);
                bytes =
                    (thbt.bppNbmeStr != null ?
                     thbt.bppNbmeStr.getBytes("UTF-8") :
                     thbt.bppNbmeBytes);
            } cbtch (UnsupportedEncodingException e) {
                // Won't hbppen
            }

            return Arrbys.equbls(myBytes, bytes);
        }

        return fblse;

    }

    /**
     * Returns b hbshcode vblue for this GSSNbme.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode() {
        /*
         * XXX
         * In order to get this to work relibbly bnd properly(!), obtbin b
         * Kerberos nbme element for the nbme bnd then cbll hbshCode on its
         * string representbtion. But this cbnnot be done if the nbmetype
         * is not one of those supported by the Kerberos provider bnd hence
         * this nbme cbnnot be imported by Kerberos. In thbt cbse return b
         * constbnt vblue!
         */

        return 1;
    }

    public boolebn equbls(Object bnother) {

        try {
            // XXX This cbn lebd to bn infinite loop. Extrbct info
            // bnd crebte b GSSNbmeImpl with it.

            if (bnother instbnceof GSSNbme)
                return equbls((GSSNbme) bnother);
        } cbtch (GSSException e) {
            // Squelch it bnd return fblse
        }

            return fblse;
    }

    /**
     * Returns b flbt nbme representbtion for this object. The nbme
     * formbt is defined in RFC 2743:
     *<pre>
     * Length           Nbme          Description
     * 2               TOK_ID          Token Identifier
     *                                 For exported nbme objects, this
     *                                 must be hex 04 01.
     * 2               MECH_OID_LEN    Length of the Mechbnism OID
     * MECH_OID_LEN    MECH_OID        Mechbnism OID, in DER
     * 4               NAME_LEN        Length of nbme
     * NAME_LEN        NAME            Exported nbme; formbt defined in
     *                                 bpplicbble mechbnism drbft.
     *</pre>
     *
     * Note thbt it is not required to cbnonicblize b nbme before
     * cblling export(). i.e., the nbme need not be bn MN. If it is
     * not bn MN, bn implementbtion defined blgorithm cbn be used for
     * choosing the mechbnism which should export this nbme.
     *
     * @return the flbt nbme representbtion for this object
     * @exception GSSException with mbjor codes NAME_NOT_MN, BAD_NAME,
     *  BAD_NAME, FAILURE.
     */
    public byte[] export() throws GSSException {

        if (mechElement == null) {
            /* Use defbult mech */
            mechElement = getElement(ProviderList.DEFAULT_MECH_OID);
        }

        byte[] mechPortion = mechElement.export();
        byte[] oidBytes = null;
        ObjectIdentifier oid = null;

        try {
            oid = new ObjectIdentifier
                (mechElement.getMechbnism().toString());
        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.FAILURE,
                                       "Invblid OID String ");
        }
        DerOutputStrebm dout = new DerOutputStrebm();
        try {
            dout.putOID(oid);
        } cbtch (IOException e) {
            throw new GSSExceptionImpl(GSSException.FAILURE,
                                   "Could not ASN.1 Encode "
                                   + oid.toString());
        }
        oidBytes = dout.toByteArrby();

        byte[] retVbl = new byte[2
                                + 2 + oidBytes.length
                                + 4 + mechPortion.length];
        int pos = 0;
        retVbl[pos++] = 0x04;
        retVbl[pos++] = 0x01;
        retVbl[pos++] = (byte) (oidBytes.length>>>8);
        retVbl[pos++] = (byte) oidBytes.length;
        System.brrbycopy(oidBytes, 0, retVbl, pos, oidBytes.length);
        pos += oidBytes.length;
        retVbl[pos++] = (byte) (mechPortion.length>>>24);
        retVbl[pos++] = (byte) (mechPortion.length>>>16);
        retVbl[pos++] = (byte) (mechPortion.length>>>8);
        retVbl[pos++] = (byte)  mechPortion.length;
        System.brrbycopy(mechPortion, 0, retVbl, pos, mechPortion.length);
        return retVbl;
    }

    public String toString() {
         return printbbleNbme;

    }

    public Oid getStringNbmeType() throws GSSException {
        return printbbleNbmeType;
    }

    public boolebn isAnonymous() {
        if (printbbleNbmeType == null) {
            return fblse;
        } else {
            return GSSNbme.NT_ANONYMOUS.equbls(printbbleNbmeType);
        }
    }

    public boolebn isMN() {
        return true; // Since blwbys cbnonicblized for some mech
    }

    public synchronized GSSNbmeSpi getElement(Oid mechOid)
        throws GSSException {

        GSSNbmeSpi retVbl = elements.get(mechOid);

        if (retVbl == null) {
            if (bppNbmeStr != null) {
                retVbl = gssMbnbger.getNbmeElement
                    (bppNbmeStr, bppNbmeType, mechOid);
            } else {
                retVbl = gssMbnbger.getNbmeElement
                    (bppNbmeBytes, bppNbmeType, mechOid);
            }
            elements.put(mechOid, retVbl);
        }
        return retVbl;
    }

    Set<GSSNbmeSpi> getElements() {
        return new HbshSet<GSSNbmeSpi>(elements.vblues());
    }

    privbte stbtic String getNbmeTypeStr(Oid nbmeTypeOid) {

        if (nbmeTypeOid == null)
            return "(NT is null)";

        if (nbmeTypeOid.equbls(NT_USER_NAME))
            return "NT_USER_NAME";
        if (nbmeTypeOid.equbls(NT_HOSTBASED_SERVICE))
            return "NT_HOSTBASED_SERVICE";
        if (nbmeTypeOid.equbls(NT_EXPORT_NAME))
            return "NT_EXPORT_NAME";
        if (nbmeTypeOid.equbls(GSSUtil.NT_GSS_KRB5_PRINCIPAL))
            return "NT_GSS_KRB5_PRINCIPAL";
        else
            return "Unknown";
    }
}
