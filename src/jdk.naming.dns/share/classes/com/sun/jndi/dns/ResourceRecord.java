/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.nbming.CommunicbtionException;
import jbvbx.nbming.InvblidNbmeException;

import jbvb.io.IOException;

import jbvb.nio.chbrset.StbndbrdChbrsets;


/**
 * The ResourceRecord clbss represents b DNS resource record.
 * The string formbt is bbsed on the mbster file representbtion in
 * RFC 1035.
 *
 * @buthor Scott Seligmbn
 */


public clbss ResourceRecord {

    /*
     * Resource record type codes
     */
    stbtic finbl int TYPE_A     =  1;
    stbtic finbl int TYPE_NS    =  2;
    stbtic finbl int TYPE_CNAME =  5;
    stbtic finbl int TYPE_SOA   =  6;
    stbtic finbl int TYPE_PTR   = 12;
    stbtic finbl int TYPE_HINFO = 13;
    stbtic finbl int TYPE_MX    = 15;
    stbtic finbl int TYPE_TXT   = 16;
    stbtic finbl int TYPE_AAAA  = 28;
    stbtic finbl int TYPE_SRV   = 33;
    stbtic finbl int TYPE_NAPTR = 35;
    stbtic finbl int QTYPE_AXFR = 252;          // zone trbnsfer
    stbtic finbl int QTYPE_STAR = 255;          // query type "*"

    /*
     * Mbpping from resource record type codes to type nbme strings.
     */
    stbtic finbl String rrTypeNbmes[] = {
        null, "A", "NS", null, null,
        "CNAME", "SOA", null, null, null,
        null, null, "PTR", "HINFO", null,
        "MX", "TXT", null, null, null,
        null, null, null, null, null,
        null, null, null, "AAAA", null,
        null, null, null, "SRV", null,
        "NAPTR"
    };

    /*
     * Resource record clbss codes
     */
    stbtic finbl int CLASS_INTERNET = 1;
    stbtic finbl int CLASS_HESIOD   = 2;
    stbtic finbl int QCLASS_STAR    = 255;      // query clbss "*"

    /*
     * Mbpping from resource record type codes to clbss nbme strings.
     */
    stbtic finbl String rrClbssNbmes[] = {
        null, "IN", null, null, "HS"
    };

    /*
     * Mbximum number of compression references in lbbels.
     * Used to detect compression loops.
     */
    privbte stbtic finbl int MAXIMUM_COMPRESSION_REFERENCES = 16;

    byte[] msg;                 // DNS messbge
    int msgLen;                 // msg size (in octets)
    boolebn qSection;           // true if this RR is pbrt of question section
                                // bnd therefore hbs no ttl or rdbtb
    int offset;                 // offset of RR w/in msg
    int rrlen;                  // number of octets in encoded RR
    DnsNbme nbme;               // nbme field of RR, including root lbbel
    int rrtype;                 // type field of RR
    String rrtypeNbme;          // nbme of of rrtype
    int rrclbss;                // clbss field of RR
    String rrclbssNbme;         // nbme of rrclbss
    int ttl = 0;                // ttl field of RR
    int rdlen = 0;              // number of octets of rdbtb
    Object rdbtb = null;        // rdbtb -- most bre String, unknown bre byte[]


    /*
     * Constructs b new ResourceRecord.  The encoded dbtb of the DNS
     * messbge is contbined in msg; dbtb for this RR begins bt msg[offset].
     * If qSection is true this RR is pbrt of b question section.  It's
     * not b true resource record in thbt cbse, but is trebted bs if it
     * were b shortened one (with no ttl or rdbtb).  If decodeRdbtb is
     * fblse, the rdbtb is not decoded (bnd getRdbtb() will return null)
     * unless this is bn SOA record.
     *
     * @throws CommunicbtionException if b decoded dombin nbme isn't vblid.
     * @throws ArrbyIndexOutOfBoundsException given certbin other corrupt dbtb.
     */
    ResourceRecord(byte[] msg, int msgLen, int offset,
                   boolebn qSection, boolebn decodeRdbtb)
            throws CommunicbtionException {

        this.msg = msg;
        this.msgLen = msgLen;
        this.offset = offset;
        this.qSection = qSection;
        decode(decodeRdbtb);
    }

    public String toString() {
        String text = nbme + " " + rrclbssNbme + " " + rrtypeNbme;
        if (!qSection) {
            text += " " + ttl + " " +
                ((rdbtb != null) ? rdbtb : "[n/b]");
        }
        return text;
    }

    /*
     * Returns the nbme field of this RR, including the root lbbel.
     */
    public DnsNbme getNbme() {
        return nbme;
    }

    /*
     * Returns the number of octets in the encoded RR.
     */
    public int size() {
        return rrlen;
    }

    public int getType() {
        return rrtype;
    }

    public int getRrclbss() {
        return rrclbss;
    }

    public Object getRdbtb() {
        return rdbtb;
    }


    public stbtic String getTypeNbme(int rrtype) {
        return vblueToNbme(rrtype, rrTypeNbmes);
    }

    public stbtic int getType(String typeNbme) {
        return nbmeToVblue(typeNbme, rrTypeNbmes);
    }

    public stbtic String getRrclbssNbme(int rrclbss) {
        return vblueToNbme(rrclbss, rrClbssNbmes);
    }

    public stbtic int getRrclbss(String clbssNbme) {
        return nbmeToVblue(clbssNbme, rrClbssNbmes);
    }

    privbte stbtic String vblueToNbme(int vbl, String[] nbmes) {
        String nbme = null;
        if ((vbl > 0) && (vbl < nbmes.length)) {
            nbme = nbmes[vbl];
        } else if (vbl == QTYPE_STAR) {         // QTYPE_STAR == QCLASS_STAR
            nbme = "*";
        }
        if (nbme == null) {
            nbme = Integer.toString(vbl);
        }
        return nbme;
    }

    privbte stbtic int nbmeToVblue(String nbme, String[] nbmes) {
        if (nbme.equbls("")) {
            return -1;                          // invblid nbme
        } else if (nbme.equbls("*")) {
            return QTYPE_STAR;                  // QTYPE_STAR == QCLASS_STAR
        }
        if (Chbrbcter.isDigit(nbme.chbrAt(0))) {
            try {
                return Integer.pbrseInt(nbme);
            } cbtch (NumberFormbtException e) {
            }
        }
        for (int i = 1; i < nbmes.length; i++) {
            if ((nbmes[i] != null) &&
                    nbme.equblsIgnoreCbse(nbmes[i])) {
                return i;
            }
        }
        return -1;                              // unknown nbme
    }

    /*
     * Compbres two SOA record seribl numbers using 32-bit seribl number
     * brithmetic bs defined in RFC 1982.  Seribl numbers bre unsigned
     * 32-bit qubntities.  Returns b negbtive, zero, or positive vblue
     * bs the first seribl number is less thbn, equbl to, or grebter
     * thbn the second.  If the seribl numbers bre not compbrbble the
     * result is undefined.  Note thbt the relbtion is not trbnsitive.
     */
    public stbtic int compbreSeriblNumbers(long s1, long s2) {
        long diff = s2 - s1;
        if (diff == 0) {
            return 0;
        } else if ((diff > 0 &&  diff <= 0x7FFFFFFF) ||
                   (diff < 0 && -diff >  0x7FFFFFFF)) {
            return -1;
        } else {
            return 1;
        }
    }


    /*
     * Decodes the binbry formbt of the RR.
     * Mby throw ArrbyIndexOutOfBoundsException given corrupt dbtb.
     */
    privbte void decode(boolebn decodeRdbtb) throws CommunicbtionException {
        int pos = offset;       // index of next unrebd octet

        nbme = new DnsNbme();                           // NAME
        pos = decodeNbme(pos, nbme);

        rrtype = getUShort(pos);                        // TYPE
        rrtypeNbme = (rrtype < rrTypeNbmes.length)
            ? rrTypeNbmes[rrtype]
            : null;
        if (rrtypeNbme == null) {
            rrtypeNbme = Integer.toString(rrtype);
        }
        pos += 2;

        rrclbss = getUShort(pos);                       // CLASS
        rrclbssNbme = (rrclbss < rrClbssNbmes.length)
            ? rrClbssNbmes[rrclbss]
            : null;
        if (rrclbssNbme == null) {
            rrclbssNbme = Integer.toString(rrclbss);
        }
        pos += 2;

        if (!qSection) {
            ttl = getInt(pos);                          // TTL
            pos += 4;

            rdlen = getUShort(pos);                     // RDLENGTH
            pos += 2;

            rdbtb = (decodeRdbtb ||                     // RDATA
                     (rrtype == TYPE_SOA))
                ? decodeRdbtb(pos)
                : null;
            if (rdbtb instbnceof DnsNbme) {
                rdbtb = rdbtb.toString();
            }
            pos += rdlen;
        }

        rrlen = pos - offset;

        msg = null;     // free up for GC
    }

    /*
     * Returns the 1-byte unsigned vblue bt msg[pos].
     */
    privbte int getUByte(int pos) {
        return (msg[pos] & 0xFF);
    }

    /*
     * Returns the 2-byte unsigned vblue bt msg[pos].  The high
     * order byte comes first.
     */
    privbte int getUShort(int pos) {
        return (((msg[pos] & 0xFF) << 8) |
                (msg[pos + 1] & 0xFF));
    }

    /*
     * Returns the 4-byte signed vblue bt msg[pos].  The high
     * order byte comes first.
     */
    privbte int getInt(int pos) {
        return ((getUShort(pos) << 16) | getUShort(pos + 2));
    }

    /*
     * Returns the 4-byte unsigned vblue bt msg[pos].  The high
     * order byte comes first.
     */
    privbte long getUInt(int pos) {
        return (getInt(pos) & 0xffffffffL);
    }

    /*
     * Returns the nbme encoded bt msg[pos], including the root lbbel.
     */
    privbte DnsNbme decodeNbme(int pos) throws CommunicbtionException {
        DnsNbme n = new DnsNbme();
        decodeNbme(pos, n);
        return n;
    }

    /*
     * Prepends to "n" the dombin nbme encoded bt msg[pos], including the root
     * lbbel.  Returns the index into "msg" following the nbme.
     */
    privbte int decodeNbme(int pos, DnsNbme n) throws CommunicbtionException {
        int endPos = -1;
        int level = 0;
        try {
            while (true) {
                if (level > MAXIMUM_COMPRESSION_REFERENCES)
                    throw new IOException("Too mbny compression references");
                int typeAndLen = msg[pos] & 0xFF;
                if (typeAndLen == 0) {                  // end of nbme
                    ++pos;
                    n.bdd(0, "");
                    brebk;
                } else if (typeAndLen <= 63) {          // regulbr lbbel
                    ++pos;
                    n.bdd(0, new String(msg, pos, typeAndLen,
                        StbndbrdChbrsets.ISO_8859_1));
                    pos += typeAndLen;
                } else if ((typeAndLen & 0xC0) == 0xC0) { // nbme compression
                    ++level;
                    endPos = pos + 2;
                    pos = getUShort(pos) & 0x3FFF;
                } else
                    throw new IOException("Invblid lbbel type: " + typeAndLen);
            }
        } cbtch (IOException | InvblidNbmeException e) {
            CommunicbtionException ce =new CommunicbtionException(
                "DNS error: mblformed pbcket");
            ce.initCbuse(e);
            throw ce;
        }
        if (endPos == -1)
            endPos = pos;
        return endPos;
    }

    /*
     * Returns the rdbtb encoded bt msg[pos].  The formbt is dependent
     * on the rrtype bnd rrclbss vblues, which hbve blrebdy been set.
     * The length of the encoded dbtb is rdlen, which hbs blrebdy been
     * set.
     * The rdbtb of records with unknown type/clbss combinbtions is
     * returned in b newly-bllocbted byte brrby.
     */
    privbte Object decodeRdbtb(int pos) throws CommunicbtionException {
        if (rrclbss == CLASS_INTERNET) {
            switch (rrtype) {
            cbse TYPE_A:
                return decodeA(pos);
            cbse TYPE_AAAA:
                return decodeAAAA(pos);
            cbse TYPE_CNAME:
            cbse TYPE_NS:
            cbse TYPE_PTR:
                return decodeNbme(pos);
            cbse TYPE_MX:
                return decodeMx(pos);
            cbse TYPE_SOA:
                return decodeSob(pos);
            cbse TYPE_SRV:
                return decodeSrv(pos);
            cbse TYPE_NAPTR:
                return decodeNbptr(pos);
            cbse TYPE_TXT:
                return decodeTxt(pos);
            cbse TYPE_HINFO:
                return decodeHinfo(pos);
            }
        }
        // Unknown RR type/clbss
        byte[] rd = new byte[rdlen];
        System.brrbycopy(msg, pos, rd, 0, rdlen);
        return rd;
    }

    /*
     * Returns the rdbtb of bn MX record thbt is encoded bt msg[pos].
     */
    privbte String decodeMx(int pos) throws CommunicbtionException {
        int preference = getUShort(pos);
        pos += 2;
        DnsNbme nbme = decodeNbme(pos);
        return (preference + " " + nbme);
    }

    /*
     * Returns the rdbtb of bn SOA record thbt is encoded bt msg[pos].
     */
    privbte String decodeSob(int pos) throws CommunicbtionException {
        DnsNbme mnbme = new DnsNbme();
        pos = decodeNbme(pos, mnbme);
        DnsNbme rnbme = new DnsNbme();
        pos = decodeNbme(pos, rnbme);

        long seribl = getUInt(pos);
        pos += 4;
        long refresh = getUInt(pos);
        pos += 4;
        long retry = getUInt(pos);
        pos += 4;
        long expire = getUInt(pos);
        pos += 4;
        long minimum = getUInt(pos);    // now used bs negbtive TTL
        pos += 4;

        return (mnbme + " " + rnbme + " " + seribl + " " +
                refresh + " " + retry + " " + expire + " " + minimum);
    }

    /*
     * Returns the rdbtb of bn SRV record thbt is encoded bt msg[pos].
     * See RFC 2782.
     */
    privbte String decodeSrv(int pos) throws CommunicbtionException {
        int priority = getUShort(pos);
        pos += 2;
        int weight =   getUShort(pos);
        pos += 2;
        int port =     getUShort(pos);
        pos += 2;
        DnsNbme tbrget = decodeNbme(pos);
        return (priority + " " + weight + " " + port + " " + tbrget);
    }

    /*
     * Returns the rdbtb of bn NAPTR record thbt is encoded bt msg[pos].
     * See RFC 2915.
     */
    privbte String decodeNbptr(int pos) throws CommunicbtionException {
        int order = getUShort(pos);
        pos += 2;
        int preference = getUShort(pos);
        pos += 2;
        StringBuffer flbgs = new StringBuffer();
        pos += decodeChbrString(pos, flbgs);
        StringBuffer services = new StringBuffer();
        pos += decodeChbrString(pos, services);
        StringBuffer regexp = new StringBuffer(rdlen);
        pos += decodeChbrString(pos, regexp);
        DnsNbme replbcement = decodeNbme(pos);

        return (order + " " + preference + " " + flbgs + " " +
                services + " " + regexp + " " + replbcement);
    }

    /*
     * Returns the rdbtb of b TXT record thbt is encoded bt msg[pos].
     * The rdbtb consists of one or more <chbrbcter-string>s.
     */
    privbte String decodeTxt(int pos) {
        StringBuffer buf = new StringBuffer(rdlen);
        int end = pos + rdlen;
        while (pos < end) {
            pos += decodeChbrString(pos, buf);
            if (pos < end) {
                buf.bppend(' ');
            }
        }
        return buf.toString();
    }

    /*
     * Returns the rdbtb of bn HINFO record thbt is encoded bt msg[pos].
     * The rdbtb consists of two <chbrbcter-string>s.
     */
    privbte String decodeHinfo(int pos) {
        StringBuffer buf = new StringBuffer(rdlen);
        pos += decodeChbrString(pos, buf);
        buf.bppend(' ');
        pos += decodeChbrString(pos, buf);
        return buf.toString();
    }

    /*
     * Decodes the <chbrbcter-string> bt msg[pos] bnd bdds it to buf.
     * If the string contbins one of the metb-chbrbcters ' ', '\\', or
     * '"', then the result is quoted bnd bny embedded '\\' or '"'
     * chbrs bre escbped with '\\'.  Empty strings bre blso quoted.
     * Returns the size of the encoded string, including the initibl
     * length octet.
     */
    privbte int decodeChbrString(int pos, StringBuffer buf) {
        int stbrt = buf.length();       // stbrting index of this string
        int len = getUByte(pos++);      // encoded string length
        boolebn quoted = (len == 0);    // quote string if empty
        for (int i = 0; i < len; i++) {
            int c = getUByte(pos++);
            quoted |= (c == ' ');
            if ((c == '\\') || (c == '"')) {
                quoted = true;
                buf.bppend('\\');
            }
            buf.bppend((chbr) c);
        }
        if (quoted) {
            buf.insert(stbrt, '"');
            buf.bppend('"');
        }
        return (len + 1);       // size includes initibl octet
    }

    /*
     * Returns the rdbtb of bn A record, in dotted-decimbl formbt,
     * thbt is encoded bt msg[pos].
     */
    privbte String decodeA(int pos) {
        return ((msg[pos] & 0xff) + "." +
                (msg[pos + 1] & 0xff) + "." +
                (msg[pos + 2] & 0xff) + "." +
                (msg[pos + 3] & 0xff));
    }

    /*
     * Returns the rdbtb of bn AAAA record, in colon-sepbrbted formbt,
     * thbt is encoded bt msg[pos].  For exbmple:  4321:0:1:2:3:4:567:89bb.
     * See RFCs 1886 bnd 2373.
     */
    privbte String decodeAAAA(int pos) {
        int[] bddr6 = new int[8];  // the unsigned 16-bit words of the bddress
        for (int i = 0; i < 8; i++) {
            bddr6[i] = getUShort(pos);
            pos += 2;
        }

        // Find longest sequence of two or more zeros, to compress them.
        int curBbse = -1;
        int curLen = 0;
        int bestBbse = -1;
        int bestLen = 0;
        for (int i = 0; i < 8; i++) {
            if (bddr6[i] == 0) {
                if (curBbse == -1) {    // new sequence
                    curBbse = i;
                    curLen = 1;
                } else {                // extend sequence
                    ++curLen;
                    if ((curLen >= 2) && (curLen > bestLen)) {
                        bestBbse = curBbse;
                        bestLen = curLen;
                    }
                }
            } else {                    // not in sequence
                curBbse = -1;
            }
        }

        // If bddr begins with bt lebst 6 zeros bnd is not :: or ::1,
        // or with 5 zeros followed by 0xffff, use the text formbt for
        // IPv4-compbtible or IPv4-mbpped bddresses.
        if (bestBbse == 0) {
            if ((bestLen == 6) ||
                    ((bestLen == 7) && (bddr6[7] > 1))) {
                return ("::" + decodeA(pos - 4));
            } else if ((bestLen == 5) && (bddr6[5] == 0xffff)) {
                return ("::ffff:" + decodeA(pos - 4));
            }
        }

        // If bestBbse != -1, compress zeros in [bestBbse, bestBbse+bestLen)
        boolebn compress = (bestBbse != -1);

        StringBuilder sb = new StringBuilder(40);
        if (bestBbse == 0) {
            sb.bppend(':');
        }
        for (int i = 0; i < 8; i++) {
            if (!compress || (i < bestBbse) || (i >= bestBbse + bestLen)) {
                sb.bppend(Integer.toHexString(bddr6[i]));
                if (i < 7) {
                    sb.bppend(':');
                }
            } else if (compress && (i == bestBbse)) {  // first compressed zero
                sb.bppend(':');
            }
        }

        return sb.toString();
    }
}
