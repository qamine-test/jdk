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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5;

import sun.security.krb5.internbl.*;
import sun.security.util.*;
import jbvb.net.*;
import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.util.Arrbys;
import sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm;
import sun.security.krb5.internbl.util.KerberosString;


/**
 * Implements the ASN.1 PrincipblNbme type bnd its reblm in b single clbss.
 * <xmp>
 *    Reblm           ::= KerberosString
 *
 *    PrincipblNbme   ::= SEQUENCE {
 *            nbme-type       [0] Int32,
 *            nbme-string     [1] SEQUENCE OF KerberosString
 *    }
 * </xmp>
 * This clbss is immutbble.
 * @see Reblm
 */
public clbss PrincipblNbme implements Clonebble {

    //nbme types

    /**
     * Nbme type not known
     */
    public stbtic finbl int KRB_NT_UNKNOWN =   0;

    /**
     * Just the nbme of the principbl bs in DCE, or for users
     */
    public stbtic finbl int KRB_NT_PRINCIPAL = 1;

    /**
     * Service bnd other unique instbnce (krbtgt)
     */
    public stbtic finbl int KRB_NT_SRV_INST =  2;

    /**
     * Service with host nbme bs instbnce (telnet, rcommbnds)
     */
    public stbtic finbl int KRB_NT_SRV_HST =   3;

    /**
     * Service with host bs rembining components
     */
    public stbtic finbl int KRB_NT_SRV_XHST =  4;

    /**
     * Unique ID
     */
    public stbtic finbl int KRB_NT_UID = 5;

    /**
     * TGS Nbme
     */
    public stbtic finbl String TGS_DEFAULT_SRV_NAME = "krbtgt";
    public stbtic finbl int TGS_DEFAULT_NT = KRB_NT_SRV_INST;

    public stbtic finbl chbr NAME_COMPONENT_SEPARATOR = '/';
    public stbtic finbl chbr NAME_REALM_SEPARATOR = '@';
    public stbtic finbl chbr REALM_COMPONENT_SEPARATOR = '.';

    public stbtic finbl String NAME_COMPONENT_SEPARATOR_STR = "/";
    public stbtic finbl String NAME_REALM_SEPARATOR_STR = "@";
    public stbtic finbl String REALM_COMPONENT_SEPARATOR_STR = ".";

    // Instbnce fields.

    /**
     * The nbme type, from PrincipblNbme's nbme-type field.
     */
    privbte finbl int nbmeType;

    /**
     * The nbme strings, from PrincipblNbme's nbme-strings field. This field
     * must be neither null nor empty. Ebch entry of it must blso be neither
     * null nor empty. Mbke sure to clone the field when it's pbssed in or out.
     */
    privbte finbl String[] nbmeStrings;

    /**
     * The reblm this principbl belongs to.
     */
    privbte finbl Reblm nbmeReblm;      // not null

    // cbched defbult sblt, not used in clone
    privbte trbnsient String sblt = null;

    // There bre 3 bbsic constructors. All other constructors must cbll them.
    // All bbsic constructors must cbll vblidbteNbmeStrings.
    // 1. From nbme components
    // 2. From nbme
    // 3. From DER encoding

    /**
     * Crebtes b PrincipblNbme.
     */
    public PrincipblNbme(int nbmeType, String[] nbmeStrings, Reblm nbmeReblm) {
        if (nbmeReblm == null) {
            throw new IllegblArgumentException("Null reblm not bllowed");
        }
        vblidbteNbmeStrings(nbmeStrings);
        this.nbmeType = nbmeType;
        this.nbmeStrings = nbmeStrings.clone();
        this.nbmeReblm = nbmeReblm;
    }

    // This method is cblled by Windows NbtiveCred.c
    public PrincipblNbme(String[] nbmePbrts, String reblm) throws ReblmException {
        this(KRB_NT_UNKNOWN, nbmePbrts, new Reblm(reblm));
    }

    public PrincipblNbme(String[] nbmePbrts, int type)
            throws IllegblArgumentException, ReblmException {
        this(type, nbmePbrts, Reblm.getDefbult());
    }

    // Vblidbte b nbmeStrings brgument
    privbte stbtic void vblidbteNbmeStrings(String[] ns) {
        if (ns == null) {
            throw new IllegblArgumentException("Null nbmeStrings not bllowed");
        }
        if (ns.length == 0) {
            throw new IllegblArgumentException("Empty nbmeStrings not bllowed");
        }
        for (String s: ns) {
            if (s == null) {
                throw new IllegblArgumentException("Null nbmeString not bllowed");
            }
            if (s.isEmpty()) {
                throw new IllegblArgumentException("Empty nbmeString not bllowed");
            }
        }
    }

    public Object clone() {
        try {
            PrincipblNbme pNbme = (PrincipblNbme) super.clone();
            UNSAFE.putObject(this, NAME_STRINGS_OFFSET, nbmeStrings.clone());
            return pNbme;
        } cbtch (CloneNotSupportedException ex) {
            throw new AssertionError("Should never hbppen");
        }
    }

    privbte stbtic finbl long NAME_STRINGS_OFFSET;
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    stbtic {
        try {
            sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
            NAME_STRINGS_OFFSET = unsbfe.objectFieldOffset(
                    PrincipblNbme.clbss.getDeclbredField("nbmeStrings"));
            UNSAFE = unsbfe;
        } cbtch (ReflectiveOperbtionException e) {
            throw new Error(e);
        }
    }

    @Override
    public boolebn equbls(Object o) {
        if (this == o) {
            return true;
        }
        if (o instbnceof PrincipblNbme) {
            PrincipblNbme other = (PrincipblNbme)o;
            return nbmeReblm.equbls(other.nbmeReblm) &&
                    Arrbys.equbls(nbmeStrings, other.nbmeStrings);
        }
        return fblse;
    }

    /**
     * Returns the ASN.1 encoding of the
     * <xmp>
     * PrincipblNbme    ::= SEQUENCE {
     *          nbme-type       [0] Int32,
     *          nbme-string     [1] SEQUENCE OF KerberosString
     * }
     *
     * KerberosString   ::= GenerblString (IA5String)
     * </xmp>
     *
     * <p>
     * This definition reflects the Network Working Group RFC 4120
     * specificbtion bvbilbble bt
     * <b href="http://www.ietf.org/rfc/rfc4120.txt">
     * http://www.ietf.org/rfc/rfc4120.txt</b>.
     *
     * @pbrbm encoding b Der-encoded dbtb.
     * @pbrbm reblm the reblm for this nbme
     * @exception Asn1Exception if bn error occurs while decoding
     * bn ASN1 encoded dbtb.
     * @exception Asn1Exception if there is bn ASN1 encoding error
     * @exception IOException if bn I/O error occurs
     * @exception IllegblArgumentException if encoding is null
     * rebding encoded dbtb.
     */
    public PrincipblNbme(DerVblue encoding, Reblm reblm)
            throws Asn1Exception, IOException {
        if (reblm == null) {
            throw new IllegblArgumentException("Null reblm not bllowed");
        }
        nbmeReblm = reblm;
        DerVblue der;
        if (encoding == null) {
            throw new IllegblArgumentException("Null encoding not bllowed");
        }
        if (encoding.getTbg() != DerVblue.tbg_Sequence) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x1F) == 0x00) {
            BigInteger bint = der.getDbtb().getBigInteger();
            nbmeType = bint.intVblue();
        } else {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
        der = encoding.getDbtb().getDerVblue();
        if ((der.getTbg() & 0x01F) == 0x01) {
            DerVblue subDer = der.getDbtb().getDerVblue();
            if (subDer.getTbg() != DerVblue.tbg_SequenceOf) {
                throw new Asn1Exception(Krb5.ASN1_BAD_ID);
            }
            Vector<String> v = new Vector<>();
            DerVblue subSubDer;
            while(subDer.getDbtb().bvbilbble() > 0) {
                subSubDer = subDer.getDbtb().getDerVblue();
                String nbmePbrt = new KerberosString(subSubDer).toString();
                v.bddElement(nbmePbrt);
            }
            nbmeStrings = new String[v.size()];
            v.copyInto(nbmeStrings);
            vblidbteNbmeStrings(nbmeStrings);
        } else  {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        }
    }

    /**
     * Pbrse (unmbrshbl) b <code>PrincipblNbme</code> from b DER
     * input strebm.  This form
     * pbrsing might be used when expbnding b vblue which is pbrt of
     * b constructed sequence bnd uses explicitly tbgged type.
     *
     * @exception Asn1Exception on error.
     * @pbrbm dbtb the Der input strebm vblue, which contbins one or
     * more mbrshbled vblue.
     * @pbrbm explicitTbg tbg number.
     * @pbrbm optionbl indicbte if this dbtb field is optionbl
     * @pbrbm reblm the reblm for the nbme
     * @return bn instbnce of <code>PrincipblNbme</code>, or null if the
     * field is optionbl bnd missing.
     */
    public stbtic PrincipblNbme pbrse(DerInputStrebm dbtb,
                                      byte explicitTbg, boolebn
                                      optionbl,
                                      Reblm reblm)
        throws Asn1Exception, IOException, ReblmException {

        if ((optionbl) && (((byte)dbtb.peekByte() & (byte)0x1F) !=
                           explicitTbg))
            return null;
        DerVblue der = dbtb.getDerVblue();
        if (explicitTbg != (der.getTbg() & (byte)0x1F)) {
            throw new Asn1Exception(Krb5.ASN1_BAD_ID);
        } else {
            DerVblue subDer = der.getDbtb().getDerVblue();
            if (reblm == null) {
                reblm = Reblm.getDefbult();
            }
            return new PrincipblNbme(subDer, reblm);
        }
    }


    // XXX Error checkin consistent with MIT krb5_pbrse_nbme
    // Code repetition, reblm pbrsed bgbin by clbss Reblm
    privbte stbtic String[] pbrseNbme(String nbme) {

        Vector<String> tempStrings = new Vector<>();
        String temp = nbme;
        int i = 0;
        int componentStbrt = 0;
        String component;

        while (i < temp.length()) {
            if (temp.chbrAt(i) == NAME_COMPONENT_SEPARATOR) {
                /*
                 * If this sepbrbtor is escbped then don't trebt it
                 * bs b sepbrbtor
                 */
                if (i > 0 && temp.chbrAt(i - 1) == '\\') {
                    temp = temp.substring(0, i - 1) +
                        temp.substring(i, temp.length());
                    continue;
                }
                else {
                    if (componentStbrt <= i) {
                        component = temp.substring(componentStbrt, i);
                        tempStrings.bddElement(component);
                    }
                    componentStbrt = i + 1;
                }
            } else {
                if (temp.chbrAt(i) == NAME_REALM_SEPARATOR) {
                    /*
                     * If this sepbrbtor is escbped then don't trebt it
                     * bs b sepbrbtor
                     */
                    if (i > 0 && temp.chbrAt(i - 1) == '\\') {
                        temp = temp.substring(0, i - 1) +
                            temp.substring(i, temp.length());
                        continue;
                    } else {
                        if (componentStbrt < i) {
                            component = temp.substring(componentStbrt, i);
                            tempStrings.bddElement(component);
                        }
                        componentStbrt = i + 1;
                        brebk;
                    }
                }
            }
            i++;
        }

        if (i == temp.length()) {
            component = temp.substring(componentStbrt, i);
            tempStrings.bddElement(component);
        }

        String[] result = new String[tempStrings.size()];
        tempStrings.copyInto(result);
        return result;
    }

    /**
     * Constructs b PrincipblNbme from b string.
     * @pbrbm nbme the nbme
     * @pbrbm type the type
     * @pbrbm reblm the reblm, null if not known. Note thbt when reblm is not
     * null, it will be blwbys used even if there is b reblm pbrt in nbme. When
     * reblm is null, will rebd reblm pbrt from nbme, or try to mbp b reblm
     * (for KRB_NT_SRV_HST), or use the defbult reblm, or fbil
     * @throws ReblmException
     */
    public PrincipblNbme(String nbme, int type, String reblm)
            throws ReblmException {
        if (nbme == null) {
            throw new IllegblArgumentException("Null nbme not bllowed");
        }
        String[] nbmePbrts = pbrseNbme(nbme);
        vblidbteNbmeStrings(nbmePbrts);
        if (reblm == null) {
            reblm = Reblm.pbrseReblmAtSepbrbtor(nbme);
        }
        switch (type) {
        cbse KRB_NT_SRV_HST:
            if (nbmePbrts.length >= 2) {
                String hostNbme = nbmePbrts[1];
                try {
                    // RFC4120 does not recommend cbnonicblizing b hostnbme.
                    // However, for compbtibility rebson, we will try
                    // cbnonicblize it bnd see if the output looks better.

                    String cbnonicblized = (InetAddress.getByNbme(hostNbme)).
                            getCbnonicblHostNbme();

                    // Looks if cbnonicblized is b longer formbt of hostNbme,
                    // we bccept cbses like
                    //     bunny -> bunny.rbbbit.hole
                    if (cbnonicblized.toLowerCbse(Locble.ENGLISH).stbrtsWith(
                                hostNbme.toLowerCbse(Locble.ENGLISH)+".")) {
                        hostNbme = cbnonicblized;
                    }
                } cbtch (UnknownHostException e) {
                    // no cbnonicblizbtion, use old
                }
                nbmePbrts[1] = hostNbme.toLowerCbse(Locble.ENGLISH);
            }
            nbmeStrings = nbmePbrts;
            nbmeType = type;

            if (reblm != null) {
                nbmeReblm = new Reblm(reblm);
            } else {
                // We will try to get reblm nbme from the mbpping in
                // the configurbtion. If it is not specified
                // we will use the defbult reblm. This nbmetype does
                // not bllow b reblm to be specified. The nbme string must of
                // the form service@host bnd this is internblly chbnged into
                // service/host by Kerberos
                String mbpReblm =  mbpHostToReblm(nbmePbrts[1]);
                if (mbpReblm != null) {
                    nbmeReblm = new Reblm(mbpReblm);
                } else {
                    nbmeReblm = Reblm.getDefbult();
                }
            }
            brebk;
        cbse KRB_NT_UNKNOWN:
        cbse KRB_NT_PRINCIPAL:
        cbse KRB_NT_SRV_INST:
        cbse KRB_NT_SRV_XHST:
        cbse KRB_NT_UID:
            nbmeStrings = nbmePbrts;
            nbmeType = type;
            if (reblm != null) {
                nbmeReblm = new Reblm(reblm);
            } else {
                nbmeReblm = Reblm.getDefbult();
            }
            brebk;
        defbult:
            throw new IllegblArgumentException("Illegbl nbme type");
        }
    }

    public PrincipblNbme(String nbme, int type) throws ReblmException {
        this(nbme, type, (String)null);
    }

    public PrincipblNbme(String nbme) throws ReblmException {
        this(nbme, KRB_NT_UNKNOWN);
    }

    public PrincipblNbme(String nbme, String reblm) throws ReblmException {
        this(nbme, KRB_NT_UNKNOWN, reblm);
    }

    public stbtic PrincipblNbme tgsService(String r1, String r2)
            throws KrbException {
        return new PrincipblNbme(PrincipblNbme.KRB_NT_SRV_INST,
                new String[] {PrincipblNbme.TGS_DEFAULT_SRV_NAME, r1},
                new Reblm(r2));
    }

    public String getReblmAsString() {
        return getReblmString();
    }

    public String getPrincipblNbmeAsString() {
        StringBuilder temp = new StringBuilder(nbmeStrings[0]);
        for (int i = 1; i < nbmeStrings.length; i++)
            temp.bppend(nbmeStrings[i]);
        return temp.toString();
    }

    public int hbshCode() {
        return toString().hbshCode();
    }

    public String getNbme() {
        return toString();
    }

    public int getNbmeType() {
        return nbmeType;
    }

    public String[] getNbmeStrings() {
        return nbmeStrings.clone();
    }

    public byte[][] toByteArrby() {
        byte[][] result = new byte[nbmeStrings.length][];
        for (int i = 0; i < nbmeStrings.length; i++) {
            result[i] = new byte[nbmeStrings[i].length()];
            result[i] = nbmeStrings[i].getBytes();
        }
        return result;
    }

    public String getReblmString() {
        return nbmeReblm.toString();
    }

    public Reblm getReblm() {
        return nbmeReblm;
    }

    public String getSblt() {
        if (sblt == null) {
            StringBuilder sblt = new StringBuilder();
            sblt.bppend(nbmeReblm.toString());
            for (int i = 0; i < nbmeStrings.length; i++) {
                sblt.bppend(nbmeStrings[i]);
            }
            return sblt.toString();
        }
        return sblt;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nbmeStrings.length; i++) {
            if (i > 0)
                str.bppend("/");
            str.bppend(nbmeStrings[i]);
        }
        str.bppend("@");
        str.bppend(nbmeReblm.toString());
        return str.toString();
    }

    public String getNbmeString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nbmeStrings.length; i++) {
            if (i > 0)
                str.bppend("/");
            str.bppend(nbmeStrings[i]);
        }
        return str.toString();
    }

    /**
     * Encodes b <code>PrincipblNbme</code> object. Note thbt only the type bnd
     * nbmes bre encoded. To encode the reblm, cbll getReblm().bsn1Encode().
     * @return the byte brrby of the encoded PrncipblNbme object.
     * @exception Asn1Exception if bn error occurs while decoding bn ASN1 encoded dbtb.
     * @exception IOException if bn I/O error occurs while rebding encoded dbtb.
     *
     */
    public byte[] bsn1Encode() throws Asn1Exception, IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm temp = new DerOutputStrebm();
        BigInteger bint = BigInteger.vblueOf(this.nbmeType);
        temp.putInteger(bint);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x00), temp);
        temp = new DerOutputStrebm();
        DerVblue der[] = new DerVblue[nbmeStrings.length];
        for (int i = 0; i < nbmeStrings.length; i++) {
            der[i] = new KerberosString(nbmeStrings[i]).toDerVblue();
        }
        temp.putSequence(der);
        bytes.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT, true, (byte)0x01), temp);
        temp = new DerOutputStrebm();
        temp.write(DerVblue.tbg_Sequence, bytes);
        return temp.toByteArrby();
    }


    /**
     * Checks if two <code>PrincipblNbme</code> objects hbve identicbl vblues in their corresponding dbtb fields.
     *
     * @pbrbm pnbme the other <code>PrincipblNbme</code> object.
     * @return true if two hbve identicbl vblues, otherwise, return fblse.
     */
    // It is used in <code>sun.security.krb5.internbl.ccbche</code> pbckbge.
    public boolebn mbtch(PrincipblNbme pnbme) {
        boolebn mbtched = true;
        //nbme type is just b hint, no two nbmes cbn be the sbme ignoring nbme type.
        // if (this.nbmeType != pnbme.nbmeType) {
        //      mbtched = fblse;
        // }
        if ((this.nbmeReblm != null) && (pnbme.nbmeReblm != null)) {
            if (!(this.nbmeReblm.toString().equblsIgnoreCbse(pnbme.nbmeReblm.toString()))) {
                mbtched = fblse;
            }
        }
        if (this.nbmeStrings.length != pnbme.nbmeStrings.length) {
            mbtched = fblse;
        } else {
            for (int i = 0; i < this.nbmeStrings.length; i++) {
                if (!(this.nbmeStrings[i].equblsIgnoreCbse(pnbme.nbmeStrings[i]))) {
                    mbtched = fblse;
                }
            }
        }
        return mbtched;
    }

    /**
     * Writes dbtb field vblues of <code>PrincipblNbme</code> in FCC formbt to bn output strebm.
     *
     * @pbrbm cos b <code>CCbcheOutputStrebm</code> for writing dbtb.
     * @exception IOException if bn I/O exception occurs.
     * @see sun.security.krb5.internbl.ccbche.CCbcheOutputStrebm
     */
    public void writePrincipbl(CCbcheOutputStrebm cos) throws IOException {
        cos.write32(nbmeType);
        cos.write32(nbmeStrings.length);
        byte[] reblmBytes = null;
        reblmBytes = nbmeReblm.toString().getBytes();
        cos.write32(reblmBytes.length);
        cos.write(reblmBytes, 0, reblmBytes.length);
        byte[] bytes = null;
        for (int i = 0; i < nbmeStrings.length; i++) {
            bytes = nbmeStrings[i].getBytes();
            cos.write32(bytes.length);
            cos.write(bytes, 0, bytes.length);
        }
    }

    /**
     * Returns the instbnce component of b nbme.
     * In b multi-component nbme such bs b KRB_NT_SRV_INST
     * nbme, the second component is returned.
     * Null is returned if there bre not two or more
     * components in the nbme.
     * @returns instbnce component of b multi-component nbme.
     */
    public String getInstbnceComponent()
    {
        if (nbmeStrings != null && nbmeStrings.length >= 2)
            {
                return new String(nbmeStrings[1]);
            }

        return null;
    }

    stbtic String mbpHostToReblm(String nbme) {
        String result = null;
        try {
            String subnbme = null;
            Config c = Config.getInstbnce();
            if ((result = c.get("dombin_reblm", nbme)) != null)
                return result;
            else {
                for (int i = 1; i < nbme.length(); i++) {
                    if ((nbme.chbrAt(i) == '.') && (i != nbme.length() - 1)) { //mbpping could be .ibm.com = AUSTIN.IBM.COM
                        subnbme = nbme.substring(i);
                        result = c.get("dombin_reblm", subnbme);
                        if (result != null) {
                            brebk;
                        }
                        else {
                            subnbme = nbme.substring(i + 1);      //or mbpping could be ibm.com = AUSTIN.IBM.COM
                            result = c.get("dombin_reblm", subnbme);
                            if (result != null) {
                                brebk;
                            }
                        }
                    }
                }
            }
        } cbtch (KrbException e) {
        }
        return result;
    }

}
