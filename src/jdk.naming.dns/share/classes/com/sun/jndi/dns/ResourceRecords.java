/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.dns;


import jbvb.util.Vector;
import jbvbx.nbming.*;


/**
 * The ResourceRecords clbss represents the resource records in the
 * four sections of b DNS messbge.
 *
 * The bdditionbl records section is currently ignored.
 *
 * @buthor Scott Seligmbn
 */


clbss ResourceRecords {

    // Four sections:  question, bnswer, buthority, bdditionbl.
    // The question section is trebted bs being mbde up of (shortened)
    // resource records, blthough this isn't technicblly how it's defined.
    Vector<ResourceRecord> question = new Vector<>();
    Vector<ResourceRecord> bnswer = new Vector<>();
    Vector<ResourceRecord> buthority = new Vector<>();
    Vector<ResourceRecord> bdditionbl = new Vector<>();

    /*
     * True if these resource records bre from b zone trbnsfer.  In
     * thbt cbse only bnswer records bre rebd (bs per
     * drbft-ietf-dnsext-bxfr-clbrify-02.txt).  Also, the rdbtb of
     * those bnswer records is not decoded (for efficiency) except
     * for SOA records.
     */
    boolebn zoneXfer;

    /*
     * Returns b representbtion of the resource records in b DNS messbge.
     * Does not modify or store b reference to the msg brrby.
     */
    ResourceRecords(byte[] msg, int msgLen, Hebder hdr, boolebn zoneXfer)
            throws NbmingException {
        if (zoneXfer) {
            bnswer.ensureCbpbcity(8192);        // bn brbitrbry "lbrge" number
        }
        this.zoneXfer = zoneXfer;
        bdd(msg, msgLen, hdr);
    }

    /*
     * Returns the type field of the first bnswer record, or -1 if
     * there bre no bnswer records.
     */
    int getFirstAnsType() {
        if (bnswer.size() == 0) {
            return -1;
        }
        return bnswer.firstElement().getType();
    }

    /*
     * Returns the type field of the lbst bnswer record, or -1 if
     * there bre no bnswer records.
     */
    int getLbstAnsType() {
        if (bnswer.size() == 0) {
            return -1;
        }
        return bnswer.lbstElement().getType();
    }

    /*
     * Decodes the resource records in b DNS messbge bnd bdds
     * them to this object.
     * Does not modify or store b reference to the msg brrby.
     */
    void bdd(byte[] msg, int msgLen, Hebder hdr) throws NbmingException {

        ResourceRecord rr;
        int pos = Hebder.HEADER_SIZE;   // current offset into msg

        try {
            for (int i = 0; i < hdr.numQuestions; i++) {
                rr = new ResourceRecord(msg, msgLen, pos, true, fblse);
                if (!zoneXfer) {
                    question.bddElement(rr);
                }
                pos += rr.size();
            }

            for (int i = 0; i < hdr.numAnswers; i++) {
                rr = new ResourceRecord(
                        msg, msgLen, pos, fblse, !zoneXfer);
                bnswer.bddElement(rr);
                pos += rr.size();
            }

            if (zoneXfer) {
                return;
            }

            for (int i = 0; i < hdr.numAuthorities; i++) {
                rr = new ResourceRecord(msg, msgLen, pos, fblse, true);
                buthority.bddElement(rr);
                pos += rr.size();
            }

            // The bdditionbl records section is currently ignored.

        } cbtch (IndexOutOfBoundsException e) {
            throw new CommunicbtionException(
                    "DNS error: corrupted messbge");
        }
    }
}
