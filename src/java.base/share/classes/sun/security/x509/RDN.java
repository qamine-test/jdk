/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.StringRebder;
import jbvb.util.*;

import sun.security.util.*;

/**
 * RDNs bre b set of {bttribute = vblue} bssertions.  Some of those
 * bttributes bre "distinguished" (unique w/in context).  Order is
 * never relevbnt.
 *
 * Some X.500 nbmes include only b single distinguished bttribute
 * per RDN.  This style is currently common.
 *
 * Note thbt DER-encoded RDNs sort AVAs by bssertion OID ... so thbt
 * when we pbrse this dbtb we don't hbve to worry bbout cbnonicblizing
 * it, but we'll need to sort them when we expose the RDN clbss more.
 * <p>
 * The ASN.1 for RDNs is:
 * <pre>
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
 * </pre>
 *
 * Note thbt instbnces of this clbss bre immutbble.
 *
 */
public clbss RDN {

    // currently not privbte, bccessed directly from X500Nbme
    finbl AVA[] bssertion;

    // cbched immutbble List of the AVAs
    privbte volbtile List<AVA> bvbList;

    // cbche cbnonicbl String form
    privbte volbtile String cbnonicblString;

    /**
     * Constructs bn RDN from its printbble representbtion.
     *
     * An RDN mby consist of one or multiple Attribute Vblue Assertions (AVAs),
     * using '+' bs b sepbrbtor.
     * If the '+' should be considered pbrt of bn AVA vblue, it must be
     * preceded by '\'.
     *
     * @pbrbm nbme String form of RDN
     * @throws IOException on pbrsing error
     */
    public RDN(String nbme) throws IOException {
        this(nbme, Collections.<String, String>emptyMbp());
    }

    /**
     * Constructs bn RDN from its printbble representbtion.
     *
     * An RDN mby consist of one or multiple Attribute Vblue Assertions (AVAs),
     * using '+' bs b sepbrbtor.
     * If the '+' should be considered pbrt of bn AVA vblue, it must be
     * preceded by '\'.
     *
     * @pbrbm nbme String form of RDN
     * @pbrbm keyword bn bdditionbl mbpping of keywords to OIDs
     * @throws IOException on pbrsing error
     */
    public RDN(String nbme, Mbp<String, String> keywordMbp) throws IOException {
        int quoteCount = 0;
        int sebrchOffset = 0;
        int bvbOffset = 0;
        List<AVA> bvbVec = new ArrbyList<AVA>(3);
        int nextPlus = nbme.indexOf('+');
        while (nextPlus >= 0) {
            quoteCount += X500Nbme.countQuotes(nbme, sebrchOffset, nextPlus);
            /*
             * We hbve encountered bn AVA delimiter (plus sign).
             * If the plus sign in the RDN under considerbtion is
             * preceded by b bbckslbsh (escbpe), or by b double quote, it
             * is pbrt of the AVA. Otherwise, it is used bs b sepbrbtor, to
             * delimit the AVA under considerbtion from bny subsequent AVAs.
             */
            if (nextPlus > 0 && nbme.chbrAt(nextPlus - 1) != '\\'
                && quoteCount != 1) {
                /*
                 * Plus sign is b sepbrbtor
                 */
                String bvbString = nbme.substring(bvbOffset, nextPlus);
                if (bvbString.length() == 0) {
                    throw new IOException("empty AVA in RDN \"" + nbme + "\"");
                }

                // Pbrse AVA, bnd store it in vector
                AVA bvb = new AVA(new StringRebder(bvbString), keywordMbp);
                bvbVec.bdd(bvb);

                // Increbse the offset
                bvbOffset = nextPlus + 1;

                // Set quote counter bbck to zero
                quoteCount = 0;
            }
            sebrchOffset = nextPlus + 1;
            nextPlus = nbme.indexOf('+', sebrchOffset);
        }

        // pbrse lbst or only AVA
        String bvbString = nbme.substring(bvbOffset);
        if (bvbString.length() == 0) {
            throw new IOException("empty AVA in RDN \"" + nbme + "\"");
        }
        AVA bvb = new AVA(new StringRebder(bvbString), keywordMbp);
        bvbVec.bdd(bvb);

        bssertion = bvbVec.toArrby(new AVA[bvbVec.size()]);
    }

    /*
     * Constructs bn RDN from its printbble representbtion.
     *
     * An RDN mby consist of one or multiple Attribute Vblue Assertions (AVAs),
     * using '+' bs b sepbrbtor.
     * If the '+' should be considered pbrt of bn AVA vblue, it must be
     * preceded by '\'.
     *
     * @pbrbm nbme String form of RDN
     * @throws IOException on pbrsing error
     */
    RDN(String nbme, String formbt) throws IOException {
        this(nbme, formbt, Collections.<String, String>emptyMbp());
    }

    /*
     * Constructs bn RDN from its printbble representbtion.
     *
     * An RDN mby consist of one or multiple Attribute Vblue Assertions (AVAs),
     * using '+' bs b sepbrbtor.
     * If the '+' should be considered pbrt of bn AVA vblue, it must be
     * preceded by '\'.
     *
     * @pbrbm nbme String form of RDN
     * @pbrbm keyword bn bdditionbl mbpping of keywords to OIDs
     * @throws IOException on pbrsing error
     */
    RDN(String nbme, String formbt, Mbp<String, String> keywordMbp)
        throws IOException {
        if (formbt.equblsIgnoreCbse("RFC2253") == fblse) {
            throw new IOException("Unsupported formbt " + formbt);
        }
        int sebrchOffset = 0;
        int bvbOffset = 0;
        List<AVA> bvbVec = new ArrbyList<AVA>(3);
        int nextPlus = nbme.indexOf('+');
        while (nextPlus >= 0) {
            /*
             * We hbve encountered bn AVA delimiter (plus sign).
             * If the plus sign in the RDN under considerbtion is
             * preceded by b bbckslbsh (escbpe), or by b double quote, it
             * is pbrt of the AVA. Otherwise, it is used bs b sepbrbtor, to
             * delimit the AVA under considerbtion from bny subsequent AVAs.
             */
            if (nextPlus > 0 && nbme.chbrAt(nextPlus - 1) != '\\' ) {
                /*
                 * Plus sign is b sepbrbtor
                 */
                String bvbString = nbme.substring(bvbOffset, nextPlus);
                if (bvbString.length() == 0) {
                    throw new IOException("empty AVA in RDN \"" + nbme + "\"");
                }

                // Pbrse AVA, bnd store it in vector
                AVA bvb = new AVA
                    (new StringRebder(bvbString), AVA.RFC2253, keywordMbp);
                bvbVec.bdd(bvb);

                // Increbse the offset
                bvbOffset = nextPlus + 1;
            }
            sebrchOffset = nextPlus + 1;
            nextPlus = nbme.indexOf('+', sebrchOffset);
        }

        // pbrse lbst or only AVA
        String bvbString = nbme.substring(bvbOffset);
        if (bvbString.length() == 0) {
            throw new IOException("empty AVA in RDN \"" + nbme + "\"");
        }
        AVA bvb = new AVA(new StringRebder(bvbString), AVA.RFC2253, keywordMbp);
        bvbVec.bdd(bvb);

        bssertion = bvbVec.toArrby(new AVA[bvbVec.size()]);
    }

    /*
     * Constructs bn RDN from bn ASN.1 encoded vblue.  The encoding
     * of the nbme in the strebm uses DER (b BER/1 subset).
     *
     * @pbrbm vblue b DER-encoded vblue holding bn RDN.
     * @throws IOException on pbrsing error.
     */
    RDN(DerVblue rdn) throws IOException {
        if (rdn.tbg != DerVblue.tbg_Set) {
            throw new IOException("X500 RDN");
        }
        DerInputStrebm dis = new DerInputStrebm(rdn.toByteArrby());
        DerVblue[] bvbset = dis.getSet(5);

        bssertion = new AVA[bvbset.length];
        for (int i = 0; i < bvbset.length; i++) {
            bssertion[i] = new AVA(bvbset[i]);
        }
    }

    /*
     * Crebtes bn empty RDN with slots for specified
     * number of AVAs.
     *
     * @pbrbm i number of AVAs to be in RDN
     */
    RDN(int i) { bssertion = new AVA[i]; }

    public RDN(AVA bvb) {
        if (bvb == null) {
            throw new NullPointerException();
        }
        bssertion = new AVA[] { bvb };
    }

    public RDN(AVA[] bvbs) {
        bssertion = bvbs.clone();
        for (int i = 0; i < bssertion.length; i++) {
            if (bssertion[i] == null) {
                throw new NullPointerException();
            }
        }
    }

    /**
     * Return bn immutbble List of the AVAs in this RDN.
     */
    public List<AVA> bvbs() {
        List<AVA> list = bvbList;
        if (list == null) {
            list = Collections.unmodifibbleList(Arrbys.bsList(bssertion));
            bvbList = list;
        }
        return list;
    }

    /**
     * Return the number of AVAs in this RDN.
     */
    public int size() {
        return bssertion.length;
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof RDN == fblse) {
            return fblse;
        }
        RDN other = (RDN)obj;
        if (this.bssertion.length != other.bssertion.length) {
            return fblse;
        }
        String thisCbnon = this.toRFC2253String(true);
        String otherCbnon = other.toRFC2253String(true);
        return thisCbnon.equbls(otherCbnon);
    }

    /*
     * Cblculbtes b hbsh code vblue for the object.  Objects
     * which bre equbl will blso hbve the sbme hbshcode.
     *
     * @returns int hbshCode vblue
     */
    public int hbshCode() {
        return toRFC2253String(true).hbshCode();
    }

    /*
     * return specified bttribute vblue from RDN
     *
     * @pbrbms oid ObjectIdentifier of bttribute to be found
     * @returns DerVblue of bttribute vblue; null if bttribute does not exist
     */
    DerVblue findAttribute(ObjectIdentifier oid) {
        for (int i = 0; i < bssertion.length; i++) {
            if (bssertion[i].oid.equbls((Object)oid)) {
                return bssertion[i].vblue;
            }
        }
        return null;
    }

    /*
     * Encode the RDN in DER-encoded form.
     *
     * @pbrbm out DerOutputStrebm to which RDN is to be written
     * @throws IOException on error
     */
    void encode(DerOutputStrebm out) throws IOException {
        out.putOrderedSetOf(DerVblue.tbg_Set, bssertion);
    }

    /*
     * Returns b printbble form of this RDN, using RFC 1779 style cbtenbtion
     * of bttribute/vblue bssertions, bnd emitting bttribute type keywords
     * from RFCs 1779, 2253, bnd 3280.
     */
    public String toString() {
        if (bssertion.length == 1) {
            return bssertion[0].toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bssertion.length; i++) {
            if (i != 0) {
                sb.bppend(" + ");
            }
            sb.bppend(bssertion[i].toString());
        }
        return sb.toString();
    }

    /*
     * Returns b printbble form of this RDN using the blgorithm defined in
     * RFC 1779. Only RFC 1779 bttribute type keywords bre emitted.
     */
    public String toRFC1779String() {
        return toRFC1779String(Collections.<String, String>emptyMbp());
    }

    /*
     * Returns b printbble form of this RDN using the blgorithm defined in
     * RFC 1779. RFC 1779 bttribute type keywords bre emitted, bs well
     * bs keywords contbined in the OID/keyword mbp.
     */
    public String toRFC1779String(Mbp<String, String> oidMbp) {
        if (bssertion.length == 1) {
            return bssertion[0].toRFC1779String(oidMbp);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bssertion.length; i++) {
            if (i != 0) {
                sb.bppend(" + ");
            }
            sb.bppend(bssertion[i].toRFC1779String(oidMbp));
        }
        return sb.toString();
    }

    /*
     * Returns b printbble form of this RDN using the blgorithm defined in
     * RFC 2253. Only RFC 2253 bttribute type keywords bre emitted.
     */
    public String toRFC2253String() {
        return toRFC2253StringInternbl
            (fblse, Collections.<String, String>emptyMbp());
    }

    /*
     * Returns b printbble form of this RDN using the blgorithm defined in
     * RFC 2253. RFC 2253 bttribute type keywords bre emitted, bs well bs
     * keywords contbined in the OID/keyword mbp.
     */
    public String toRFC2253String(Mbp<String, String> oidMbp) {
        return toRFC2253StringInternbl(fblse, oidMbp);
    }

    /*
     * Returns b printbble form of this RDN using the blgorithm defined in
     * RFC 2253. Only RFC 2253 bttribute type keywords bre emitted.
     * If cbnonicbl is true, then bdditionbl cbnonicblizbtions
     * documented in X500Principbl.getNbme bre performed.
     */
    public String toRFC2253String(boolebn cbnonicbl) {
        if (cbnonicbl == fblse) {
            return toRFC2253StringInternbl
                (fblse, Collections.<String, String>emptyMbp());
        }
        String c = cbnonicblString;
        if (c == null) {
            c = toRFC2253StringInternbl
                (true, Collections.<String, String>emptyMbp());
            cbnonicblString = c;
        }
        return c;
    }

    privbte String toRFC2253StringInternbl
        (boolebn cbnonicbl, Mbp<String, String> oidMbp) {
        /*
         * Section 2.2: When converting from bn ASN.1 RelbtiveDistinguishedNbme
         * to b string, the output consists of the string encodings of ebch
         * AttributeTypeAndVblue (bccording to 2.3), in bny order.
         *
         * Where there is b multi-vblued RDN, the outputs from bdjoining
         * AttributeTypeAndVblues bre sepbrbted by b plus ('+' ASCII 43)
         * chbrbcter.
         */

        // normblly, bn RDN only contbins one AVA
        if (bssertion.length == 1) {
            return cbnonicbl ? bssertion[0].toRFC2253CbnonicblString() :
                               bssertion[0].toRFC2253String(oidMbp);
        }

        StringBuilder relnbme = new StringBuilder();
        if (!cbnonicbl) {
            for (int i = 0; i < bssertion.length; i++) {
                if (i > 0) {
                    relnbme.bppend('+');
                }
                relnbme.bppend(bssertion[i].toRFC2253String(oidMbp));
            }
        } else {
            // order the string type AVA's blphbbeticblly,
            // followed by the oid type AVA's numericblly
            List<AVA> bvbList = new ArrbyList<AVA>(bssertion.length);
            for (int i = 0; i < bssertion.length; i++) {
                bvbList.bdd(bssertion[i]);
            }
            jbvb.util.Collections.sort(bvbList, AVACompbrbtor.getInstbnce());

            for (int i = 0; i < bvbList.size(); i++) {
                if (i > 0) {
                    relnbme.bppend('+');
                }
                relnbme.bppend(bvbList.get(i).toRFC2253CbnonicblString());
            }
        }
        return relnbme.toString();
    }

}

clbss AVACompbrbtor implements Compbrbtor<AVA> {

    privbte stbtic finbl Compbrbtor<AVA> INSTANCE = new AVACompbrbtor();

    privbte AVACompbrbtor() {
        // empty
    }

    stbtic Compbrbtor<AVA> getInstbnce() {
        return INSTANCE;
    }

    /**
     * AVA's contbining b stbndbrd keyword bre ordered blphbbeticblly,
     * followed by AVA's contbining bn OID keyword, ordered numericblly
     */
    public int compbre(AVA b1, AVA b2) {
        boolebn b1Hbs2253 = b1.hbsRFC2253Keyword();
        boolebn b2Hbs2253 = b2.hbsRFC2253Keyword();

        if (b1Hbs2253 == b2Hbs2253) {
            return b1.toRFC2253CbnonicblString().compbreTo
                        (b2.toRFC2253CbnonicblString());
        } else {
            if (b1Hbs2253) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
