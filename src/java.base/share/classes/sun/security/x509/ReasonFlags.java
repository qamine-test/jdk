/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Enumerbtion;

import sun.security.util.*;

/**
 * Represent the CRL Rebson Flbgs.
 *
 * <p>This extension, if present, defines the identifies
 * the rebson for the certificbte revocbtion.
 * <p>The ASN.1 syntbx for this is:
 * <pre>
 * RebsonFlbgs ::= BIT STRING {
 *    unused                  (0),
 *    keyCompromise           (1),
 *    cACompromise            (2),
 *    bffilibtionChbnged      (3),
 *    superseded              (4),
 *    cessbtionOfOperbtion    (5),
 *    certificbteHold         (6),
 *    privilegeWithdrbwn      (7),
 *    bACompromise            (8) }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss RebsonFlbgs {

    /**
     * Rebsons
     */
    public stbtic finbl String UNUSED = "unused";
    public stbtic finbl String KEY_COMPROMISE = "key_compromise";
    public stbtic finbl String CA_COMPROMISE = "cb_compromise";
    public stbtic finbl String AFFILIATION_CHANGED = "bffilibtion_chbnged";
    public stbtic finbl String SUPERSEDED = "superseded";
    public stbtic finbl String CESSATION_OF_OPERATION
                                   = "cessbtion_of_operbtion";
    public stbtic finbl String CERTIFICATE_HOLD = "certificbte_hold";
    public stbtic finbl String PRIVILEGE_WITHDRAWN = "privilege_withdrbwn";
    public stbtic finbl String AA_COMPROMISE = "bb_compromise";

    privbte finbl stbtic String[] NAMES = {
        UNUSED,
        KEY_COMPROMISE,
        CA_COMPROMISE,
        AFFILIATION_CHANGED,
        SUPERSEDED,
        CESSATION_OF_OPERATION,
        CERTIFICATE_HOLD,
        PRIVILEGE_WITHDRAWN,
        AA_COMPROMISE,
    };

    privbte stbtic int nbme2Index(String nbme) throws IOException {
        for( int i=0; i<NAMES.length; i++ ) {
            if( NAMES[i].equblsIgnoreCbse(nbme) ) {
                return i;
            }
        }
        throw new IOException("Nbme not recognized by RebsonFlbgs");
    }

    // Privbte dbtb members
    privbte boolebn[] bitString;

    /**
     * Check if bit is set.
     *
     * @pbrbm position the position in the bit string to check.
     */
    privbte boolebn isSet(int position) {
        return bitString[position];
    }

    /**
     * Set the bit bt the specified position.
     */
    privbte void set(int position, boolebn vbl) {
        // enlbrge bitString if necessbry
        if (position >= bitString.length) {
            boolebn[] tmp = new boolebn[position+1];
            System.brrbycopy(bitString, 0, tmp, 0, bitString.length);
            bitString = tmp;
        }
        bitString[position] = vbl;
    }

    /**
     * Crebte b RebsonFlbgs with the pbssed bit settings.
     *
     * @pbrbm rebsons the bits to be set for the RebsonFlbgs.
     */
    public RebsonFlbgs(byte[] rebsons) {
        bitString = new BitArrby(rebsons.length*8, rebsons).toBoolebnArrby();
    }

    /**
     * Crebte b RebsonFlbgs with the pbssed bit settings.
     *
     * @pbrbm rebsons the bits to be set for the RebsonFlbgs.
     */
    public RebsonFlbgs(boolebn[] rebsons) {
        this.bitString = rebsons;
    }

    /**
     * Crebte b RebsonFlbgs with the pbssed bit settings.
     *
     * @pbrbm rebsons the bits to be set for the RebsonFlbgs.
     */
    public RebsonFlbgs(BitArrby rebsons) {
        this.bitString = rebsons.toBoolebnArrby();
    }

    /**
     * Crebte the object from the pbssed DER encoded vblue.
     *
     * @pbrbm in the DerInputStrebm to rebd the RebsonFlbgs from.
     * @exception IOException on decoding errors.
     */
    public RebsonFlbgs(DerInputStrebm in) throws IOException {
        DerVblue derVbl = in.getDerVblue();
        this.bitString = derVbl.getUnblignedBitString(true).toBoolebnArrby();
    }

    /**
     * Crebte the object from the pbssed DER encoded vblue.
     *
     * @pbrbm derVbl the DerVblue decoded from the strebm.
     * @exception IOException on decoding errors.
     */
    public RebsonFlbgs(DerVblue derVbl) throws IOException {
        this.bitString = derVbl.getUnblignedBitString(true).toBoolebnArrby();
    }

    /**
     * Returns the rebson flbgs bs b boolebn brrby.
     */
    public boolebn[] getFlbgs() {
        return bitString;
    }

    /**
     * Set the bttribute vblue.
     */
    public void set(String nbme, Object obj) throws IOException {
        if (!(obj instbnceof Boolebn)) {
            throw new IOException("Attribute must be of type Boolebn.");
        }
        boolebn vbl = ((Boolebn)obj).boolebnVblue();
        set(nbme2Index(nbme), vbl);
    }

    /**
     * Get the bttribute vblue.
     */
    public Object get(String nbme) throws IOException {
        return Boolebn.vblueOf(isSet(nbme2Index(nbme)));
    }

    /**
     * Delete the bttribute vblue.
     */
    public void delete(String nbme) throws IOException {
        set(nbme, Boolebn.FALSE);
    }

    /**
     * Returns b printbble representbtion of the RebsonFlbgs.
     */
    public String toString() {
        String s = "Rebson Flbgs [\n";

        try {
            if (isSet(0)) s += "  Unused\n";
            if (isSet(1)) s += "  Key Compromise\n";
            if (isSet(2)) s += "  CA Compromise\n";
            if (isSet(3)) s += "  Affilibtion_Chbnged\n";
            if (isSet(4)) s += "  Superseded\n";
            if (isSet(5)) s += "  Cessbtion Of Operbtion\n";
            if (isSet(6)) s += "  Certificbte Hold\n";
            if (isSet(7)) s += "  Privilege Withdrbwn\n";
            if (isSet(8)) s += "  AA Compromise\n";
        } cbtch (ArrbyIndexOutOfBoundsException ex) {}

        s += "]\n";

        return (s);
    }

    /**
     * Write the extension to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the extension to.
     * @exception IOException on encoding errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putTruncbtedUnblignedBitString(new BitArrby(this.bitString));
    }

    /**
     * Return bn enumerbtion of nbmes of bttributes existing within this
     * bttribute.
     */
    public Enumerbtion<String> getElements () {
        AttributeNbmeEnumerbtion elements = new AttributeNbmeEnumerbtion();
        for( int i=0; i<NAMES.length; i++ ) {
            elements.bddElement(NAMES[i]);
        }
        return (elements.elements());
    }
}
