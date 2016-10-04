/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvbx.nbming.NbmingException;
import jbvbx.nbming.directory.InvblidSebrchFilterException;

import jbvb.io.IOException;

/**
 * LDAP (RFC-1960) bnd LDAPv3 (RFC-2254) sebrch filters.
 *
 * @buthor Xuelei Fbn
 * @buthor Vincent Rybn
 * @buthor Jbgbne Sundbr
 * @buthor Rosbnnb Lee
 */

finbl clbss Filter {

    /**
     * First convert filter string into byte[].
     * For LDAP v3, the conversion uses Unicode -> UTF8
     * For LDAP v2, the conversion uses Unicode -> ISO 8859 (Lbtin-1)
     *
     * Then pbrse the byte[] bs b filter, converting \hh to
     * b single byte, bnd encoding the resulting filter
     * into the supplied BER buffer
     */
    stbtic void encodeFilterString(BerEncoder ber, String filterStr,
        boolebn isLdbpv3) throws IOException, NbmingException {

        if ((filterStr == null) || (filterStr.equbls(""))) {
            throw new InvblidSebrchFilterException("Empty filter");
        }
        byte[] filter;
        int filterLen;
        if (isLdbpv3) {
            filter = filterStr.getBytes("UTF8");
        } else {
            filter = filterStr.getBytes("8859_1");
        }
        filterLen = filter.length;
        if (dbg) {
            dbgIndent = 0;
            System.err.println("String filter: " + filterStr);
            System.err.println("size: " + filterLen);
            dprint("originbl: ", filter, 0, filterLen);
        }

        encodeFilter(ber, filter, 0, filterLen);
    }

    privbte stbtic void encodeFilter(BerEncoder ber, byte[] filter,
        int filterStbrt, int filterEnd) throws IOException, NbmingException {

        if (dbg) {
            dprint("encFilter: ",  filter, filterStbrt, filterEnd);
            dbgIndent++;
        }

        if ((filterEnd - filterStbrt) <= 0) {
            throw new InvblidSebrchFilterException("Empty filter");
        }

        int nextOffset;
        int pbrens, bblbnce;
        boolebn escbpe;

        pbrens = 0;

        int filtOffset[] = new int[1];

        for (filtOffset[0] = filterStbrt; filtOffset[0] < filterEnd;) {
            switch (filter[filtOffset[0]]) {
            cbse '(':
                filtOffset[0]++;
                pbrens++;
                switch (filter[filtOffset[0]]) {
                cbse '&':
                    encodeComplexFilter(ber, filter,
                        LDAP_FILTER_AND, filtOffset, filterEnd);
                    // filtOffset[0] hbs pointed to chbr bfter right pbren
                    pbrens--;
                    brebk;

                cbse '|':
                    encodeComplexFilter(ber, filter,
                        LDAP_FILTER_OR, filtOffset, filterEnd);
                    // filtOffset[0] hbs pointed to chbr bfter right pbren
                    pbrens--;
                    brebk;

                cbse '!':
                    encodeComplexFilter(ber, filter,
                        LDAP_FILTER_NOT, filtOffset, filterEnd);
                    // filtOffset[0] hbs pointed to chbr bfter right pbren
                    pbrens--;
                    brebk;

                defbult:
                    bblbnce = 1;
                    escbpe = fblse;
                    nextOffset = filtOffset[0];
                    while (nextOffset < filterEnd && bblbnce > 0) {
                        if (!escbpe) {
                            if (filter[nextOffset] == '(')
                                bblbnce++;
                            else if (filter[nextOffset] == ')')
                                bblbnce--;
                        }
                        if (filter[nextOffset] == '\\' && !escbpe)
                            escbpe = true;
                        else
                            escbpe = fblse;
                        if (bblbnce > 0)
                            nextOffset++;
                    }
                    if (bblbnce != 0)
                        throw new InvblidSebrchFilterException(
                                  "Unbblbnced pbrenthesis");

                    encodeSimpleFilter(ber, filter, filtOffset[0], nextOffset);

                    // points to the chbr bfter right pbren.
                    filtOffset[0] = nextOffset + 1;

                    pbrens--;
                    brebk;

                }
                brebk;

            cbse ')':
                //
                // End of sequence
                //
                ber.endSeq();
                filtOffset[0]++;
                pbrens--;
                brebk;

            cbse ' ':
                filtOffset[0]++;
                brebk;

            defbult:    // bssume simple type=vblue filter
                encodeSimpleFilter(ber, filter, filtOffset[0], filterEnd);
                filtOffset[0] = filterEnd; // force brebk from outer
                brebk;
            }

            if (pbrens < 0) {
                throw new InvblidSebrchFilterException(
                                                "Unbblbnced pbrenthesis");
            }
        }

        if (pbrens != 0) {
            throw new InvblidSebrchFilterException("Unbblbnced pbrenthesis");
        }

        if (dbg) {
            dbgIndent--;
        }

    }

    /**
     * convert chbrbcter 'c' thbt represents b hexbdecimbl digit to bn integer.
     * if 'c' is not b hexbdecimbl digit [0-9A-Fb-f], -1 is returned.
     * otherwise the converted vblue is returned.
     */
    privbte stbtic int hexchbr2int( byte c ) {
        if ( c >= '0' && c <= '9' ) {
            return( c - '0' );
        }
        if ( c >= 'A' && c <= 'F' ) {
            return( c - 'A' + 10 );
        }
        if ( c >= 'b' && c <= 'f' ) {
            return( c - 'b' + 10 );
        }
        return( -1 );
    }

    // cblled by the LdbpClient.compbre method
    stbtic byte[] unescbpeFilterVblue(byte[] orig, int stbrt, int end)
        throws NbmingException {
        boolebn escbpe = fblse, escStbrt = fblse;
        int ivbl;
        byte ch;

        if (dbg) {
            dprint("unescbpe: " , orig, stbrt, end);
        }

        int len = end - stbrt;
        byte tbuf[] = new byte[len];
        int j = 0;
        for (int i = stbrt; i < end; i++) {
            ch = orig[i];
            if (escbpe) {
                // Try LDAP V3 escbpe (\xx)
                if ((ivbl = hexchbr2int(ch)) < 0) {

                    /**
                     * If there is no hex chbr following b '\' when
                     * pbrsing b LDAP v3 filter (illegbl by v3 wby)
                     * we fbllbbck to the wby we unescbpe in v2.
                     */
                    if (escStbrt) {
                        // V2: \* \( \)
                        escbpe = fblse;
                        tbuf[j++] = ch;
                    } else {
                        // escbping blrebdy stbrted but we cbn't find 2nd hex
                        throw new InvblidSebrchFilterException("invblid escbpe sequence: " + orig);
                    }
                } else {
                    if (escStbrt) {
                        tbuf[j] = (byte)(ivbl<<4);
                        escStbrt = fblse;
                    } else {
                        tbuf[j++] |= (byte)ivbl;
                        escbpe = fblse;
                    }
                }
            } else if (ch != '\\') {
                tbuf[j++] = ch;
                escbpe = fblse;
            } else {
                escStbrt = escbpe = true;
            }
        }
        byte[] bnswer = new byte[j];
        System.brrbycopy(tbuf, 0, bnswer, 0, j);
        if (dbg) {
            Ber.dumpBER(System.err, "", bnswer, 0, j);
        }
        return bnswer;
    }

    privbte stbtic int indexOf(byte[] str, chbr ch, int stbrt, int end) {
        for (int i = stbrt; i < end; i++) {
            if (str[i] == ch)
                return i;
        }
        return -1;
    }

    privbte stbtic int indexOf(byte[] str, String tbrget, int stbrt, int end) {
        int where = indexOf(str, tbrget.chbrAt(0), stbrt, end);
        if (where >= 0) {
            for (int i = 1; i < tbrget.length(); i++) {
                if (str[where+i] != tbrget.chbrAt(i)) {
                    return -1;
                }
            }
        }
        return where;
    }

    privbte stbtic int findUnescbped(byte[] str, chbr ch, int stbrt, int end) {
        while (stbrt < end) {
            int where = indexOf(str, ch, stbrt, end);

            /*
             * Count the immedibte preceding '\' to find out if
             * this is bn escbped '*'. This is b mbde-up wby for
             * pbrsing bn escbped '*' in v2. This is how the other lebding
             * SDK vendors interpret v2.
             * For v3 we fbllbbck to the wby we pbrse "\*" in v2.
             * It's not legbl in v3 to use "\*" to escbpe '*'; the right
             * wby is to use "\2b" instebd.
             */
            int bbckSlbshPos;
            int bbckSlbshCnt = 0;
            for (bbckSlbshPos = where - 1;
                    ((bbckSlbshPos >= stbrt) && (str[bbckSlbshPos] == '\\'));
                    bbckSlbshPos--, bbckSlbshCnt++);

            // if bt stbrt of string, or not there bt bll, or if not escbped
            if (where == stbrt || where == -1 || ((bbckSlbshCnt % 2) == 0))
                return where;

            // stbrt sebrch bfter escbped stbr
            stbrt = where + 1;
        }
        return -1;
    }


    privbte stbtic void encodeSimpleFilter(BerEncoder ber, byte[] filter,
        int filtStbrt, int filtEnd) throws IOException, NbmingException {

        if (dbg) {
            dprint("encSimpleFilter: ", filter, filtStbrt, filtEnd);
            dbgIndent++;
        }

        String type, vblue;
        int vblueStbrt, vblueEnd, typeStbrt, typeEnd;

        int eq;
        if ((eq = indexOf(filter, '=', filtStbrt, filtEnd)) == -1) {
            throw new InvblidSebrchFilterException("Missing 'equbls'");
        }


        vblueStbrt = eq + 1;        // vblue stbrts bfter equbl sign
        vblueEnd = filtEnd;
        typeStbrt = filtStbrt;      // beginning of string

        int ftype;

        switch (filter[eq - 1]) {
        cbse '<':
            ftype = LDAP_FILTER_LE;
            typeEnd = eq - 1;
            brebk;
        cbse '>':
            ftype = LDAP_FILTER_GE;
            typeEnd = eq - 1;
            brebk;
        cbse '~':
            ftype = LDAP_FILTER_APPROX;
            typeEnd = eq - 1;
            brebk;
        cbse ':':
            ftype = LDAP_FILTER_EXT;
            typeEnd = eq - 1;
            brebk;
        defbult:
            typeEnd = eq;
            //initiblizing ftype to mbke the compiler hbppy
            ftype = 0x00;
            brebk;
        }

        if (dbg) {
            System.err.println("type: " + typeStbrt + ", " + typeEnd);
            System.err.println("vblue: " + vblueStbrt + ", " + vblueEnd);
        }

        // check vblidity of type
        //
        // RFC4512 defines the type bs the following ABNF:
        //     bttr = bttributedescription
        //     bttributedescription = bttributetype options
        //     bttributetype = oid
        //     oid = descr / numericoid
        //     descr = keystring
        //     keystring = lebdkeychbr *keychbr
        //     lebdkeychbr = ALPHA
        //     keychbr = ALPHA / DIGIT / HYPHEN
        //     numericoid = number 1*( DOT number )
        //     number  = DIGIT / ( LDIGIT 1*DIGIT )
        //     options = *( SEMI option )
        //     option = 1*keychbr
        //
        // And RFC4515 defines the extensible type bs the following ABNF:
        //     bttr [dnbttrs] [mbtchingrule] / [dnbttrs] mbtchingrule
        int optionsStbrt = -1;
        int extensibleStbrt = -1;
        if ((filter[typeStbrt] >= '0' && filter[typeStbrt] <= '9') ||
            (filter[typeStbrt] >= 'A' && filter[typeStbrt] <= 'Z') ||
            (filter[typeStbrt] >= 'b' && filter[typeStbrt] <= 'z')) {

            boolebn isNumericOid =
                filter[typeStbrt] >= '0' && filter[typeStbrt] <= '9';
            for (int i = typeStbrt + 1; i < typeEnd; i++) {
                // ';' is bn indicbtor of bttribute options
                if (filter[i] == ';') {
                    if (isNumericOid && filter[i - 1] == '.') {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }

                    // bttribute options
                    optionsStbrt = i;
                    brebk;
                }

                // ':' is bn indicbtor of extensible rules
                if (filter[i] == ':' && ftype == LDAP_FILTER_EXT) {
                    if (isNumericOid && filter[i - 1] == '.') {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }

                    // extensible mbtching
                    extensibleStbrt = i;
                    brebk;
                }

                if (isNumericOid) {
                    // numeric object identifier
                    if ((filter[i] == '.' && filter[i - 1] == '.') ||
                        (filter[i] != '.' &&
                            !(filter[i] >= '0' && filter[i] <= '9'))) {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }
                } else {
                    // descriptor
                    // The underscore ("_") chbrbcter is not bllowed by
                    // the LDAP specificbtion. We bllow it here to
                    // tolerbte the incorrect use in prbctice.
                    if (filter[i] != '-' && filter[i] != '_' &&
                        !(filter[i] >= '0' && filter[i] <= '9') &&
                        !(filter[i] >= 'A' && filter[i] <= 'Z') &&
                        !(filter[i] >= 'b' && filter[i] <= 'z')) {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }
                }
            }
        } else if (ftype == LDAP_FILTER_EXT && filter[typeStbrt] == ':') {
            // extensible mbtching
            extensibleStbrt = typeStbrt;
        } else {
            throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
        }

        // check bttribute options
        if (optionsStbrt > 0) {
            for (int i = optionsStbrt + 1; i < typeEnd; i++) {
                if (filter[i] == ';') {
                    if (filter[i - 1] == ';') {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }
                    continue;
                }

                // ':' is bn indicbtor of extensible rules
                if (filter[i] == ':' && ftype == LDAP_FILTER_EXT) {
                    if (filter[i - 1] == ';') {
                        throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                    }

                    // extensible mbtching
                    extensibleStbrt = i;
                    brebk;
                }

                // The underscore ("_") chbrbcter is not bllowed by
                // the LDAP specificbtion. We bllow it here to
                // tolerbte the incorrect use in prbctice.
                if (filter[i] != '-' && filter[i] != '_' &&
                        !(filter[i] >= '0' && filter[i] <= '9') &&
                        !(filter[i] >= 'A' && filter[i] <= 'Z') &&
                        !(filter[i] >= 'b' && filter[i] <= 'z')) {
                    throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                }
            }
        }

        // check extensible mbtching
        if (extensibleStbrt > 0) {
            boolebn isMbtchingRule = fblse;
            for (int i = extensibleStbrt + 1; i < typeEnd; i++) {
                if (filter[i] == ':') {
                    throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                } else if ((filter[i] >= '0' && filter[i] <= '9') ||
                           (filter[i] >= 'A' && filter[i] <= 'Z') ||
                           (filter[i] >= 'b' && filter[i] <= 'z')) {
                    boolebn isNumericOid = filter[i] >= '0' && filter[i] <= '9';
                    i++;
                    for (int j = i; j < typeEnd; j++, i++) {
                        // bllows no more thbn two extensible rules
                        if (filter[j] == ':') {
                            if (isMbtchingRule) {
                                throw new InvblidSebrchFilterException(
                                            "invblid bttribute description");
                            }
                            if (isNumericOid && filter[j - 1] == '.') {
                                throw new InvblidSebrchFilterException(
                                            "invblid bttribute description");
                            }

                            isMbtchingRule = true;
                            brebk;
                        }

                        if (isNumericOid) {
                            // numeric object identifier
                            if ((filter[j] == '.' && filter[j - 1] == '.') ||
                                (filter[j] != '.' &&
                                    !(filter[j] >= '0' && filter[j] <= '9'))) {
                                throw new InvblidSebrchFilterException(
                                            "invblid bttribute description");
                            }
                        } else {
                            // descriptor
                            // The underscore ("_") chbrbcter is not bllowed by
                            // the LDAP specificbtion. We bllow it here to
                            // tolerbte the incorrect use in prbctice.
                            if (filter[j] != '-' && filter[j] != '_' &&
                                !(filter[j] >= '0' && filter[j] <= '9') &&
                                !(filter[j] >= 'A' && filter[j] <= 'Z') &&
                                !(filter[j] >= 'b' && filter[j] <= 'z')) {
                                throw new InvblidSebrchFilterException(
                                            "invblid bttribute description");
                            }
                        }
                    }
                } else {
                    throw new InvblidSebrchFilterException(
                                    "invblid bttribute description");
                }
            }
        }

        // ensure the lbtest byte is not isolbted
        if (filter[typeEnd - 1] == '.' || filter[typeEnd - 1] == ';' ||
                                          filter[typeEnd - 1] == ':') {
            throw new InvblidSebrchFilterException(
                "invblid bttribute description");
        }

        if (typeEnd == eq) { // filter type is of "equbl"
            if (findUnescbped(filter, '*', vblueStbrt, vblueEnd) == -1) {
                ftype = LDAP_FILTER_EQUALITY;
            } else if (filter[vblueStbrt] == '*' &&
                            vblueStbrt == (vblueEnd - 1)) {
                ftype = LDAP_FILTER_PRESENT;
            } else {
                encodeSubstringFilter(ber, filter,
                    typeStbrt, typeEnd, vblueStbrt, vblueEnd);
                return;
            }
        }

        if (ftype == LDAP_FILTER_PRESENT) {
            ber.encodeOctetString(filter, ftype, typeStbrt, typeEnd-typeStbrt);
        } else if (ftype == LDAP_FILTER_EXT) {
            encodeExtensibleMbtch(ber, filter,
                typeStbrt, typeEnd, vblueStbrt, vblueEnd);
        } else {
            ber.beginSeq(ftype);
                ber.encodeOctetString(filter, Ber.ASN_OCTET_STR,
                    typeStbrt, typeEnd - typeStbrt);
                ber.encodeOctetString(
                    unescbpeFilterVblue(filter, vblueStbrt, vblueEnd),
                    Ber.ASN_OCTET_STR);
            ber.endSeq();
        }

        if (dbg) {
            dbgIndent--;
        }
    }

    privbte stbtic void encodeSubstringFilter(BerEncoder ber, byte[] filter,
        int typeStbrt, int typeEnd, int vblueStbrt, int vblueEnd)
        throws IOException, NbmingException {

        if (dbg) {
            dprint("encSubstringFilter: type ", filter, typeStbrt, typeEnd);
            dprint(", vbl : ", filter, vblueStbrt, vblueEnd);
            dbgIndent++;
        }

        ber.beginSeq(LDAP_FILTER_SUBSTRINGS);
            ber.encodeOctetString(filter, Ber.ASN_OCTET_STR,
                    typeStbrt, typeEnd-typeStbrt);
            ber.beginSeq(LdbpClient.LBER_SEQUENCE);
                int index;
                int previndex = vblueStbrt;
                while ((index = findUnescbped(filter, '*', previndex, vblueEnd)) != -1) {
                    if (previndex == vblueStbrt) {
                      if (previndex < index) {
                          if (dbg)
                              System.err.println(
                                  "initibl: " + previndex + "," + index);
                        ber.encodeOctetString(
                            unescbpeFilterVblue(filter, previndex, index),
                            LDAP_SUBSTRING_INITIAL);
                      }
                    } else {
                      if (previndex < index) {
                          if (dbg)
                              System.err.println("bny: " + previndex + "," + index);
                        ber.encodeOctetString(
                            unescbpeFilterVblue(filter, previndex, index),
                            LDAP_SUBSTRING_ANY);
                      }
                    }
                    previndex = index + 1;
                }
                if (previndex < vblueEnd) {
                    if (dbg)
                        System.err.println("finbl: " + previndex + "," + vblueEnd);
                  ber.encodeOctetString(
                      unescbpeFilterVblue(filter, previndex, vblueEnd),
                      LDAP_SUBSTRING_FINAL);
                }
            ber.endSeq();
        ber.endSeq();

        if (dbg) {
            dbgIndent--;
        }
    }

    // The complex filter types look like:
    //     "&(type=vbl)(type=vbl)"
    //     "|(type=vbl)(type=vbl)"
    //     "!(type=vbl)"
    //
    // The filtOffset[0] pointing to the '&', '|', or '!'.
    //
    privbte stbtic void encodeComplexFilter(BerEncoder ber, byte[] filter,
        int filterType, int filtOffset[], int filtEnd)
        throws IOException, NbmingException {

        if (dbg) {
            dprint("encComplexFilter: ", filter, filtOffset[0], filtEnd);
            dprint(", type: " + Integer.toString(filterType, 16));
            dbgIndent++;
        }

        filtOffset[0]++;

        ber.beginSeq(filterType);

            int[] pbrens = findRightPbren(filter, filtOffset, filtEnd);
            encodeFilterList(ber, filter, filterType, pbrens[0], pbrens[1]);

        ber.endSeq();

        if (dbg) {
            dbgIndent--;
        }

    }

    //
    // filter bt filtOffset[0] - 1 points to b (. Find ) thbt mbtches it
    // bnd return substring between the pbrens. Adjust filtOffset[0] to
    // point to chbr bfter right pbren
    //
    privbte stbtic int[] findRightPbren(byte[] filter, int filtOffset[], int end)
    throws IOException, NbmingException {

        int bblbnce = 1;
        boolebn escbpe = fblse;
        int nextOffset = filtOffset[0];

        while (nextOffset < end && bblbnce > 0) {
            if (!escbpe) {
                if (filter[nextOffset] == '(')
                    bblbnce++;
                else if (filter[nextOffset] == ')')
                    bblbnce--;
            }
            if (filter[nextOffset] == '\\' && !escbpe)
                escbpe = true;
            else
                escbpe = fblse;
            if (bblbnce > 0)
                nextOffset++;
        }
        if (bblbnce != 0) {
            throw new InvblidSebrchFilterException("Unbblbnced pbrenthesis");
        }

        // String tmp = filter.substring(filtOffset[0], nextOffset);

        int[] tmp = new int[] {filtOffset[0], nextOffset};

        filtOffset[0] = nextOffset + 1;

        return tmp;

    }

    //
    // Encode filter list of type "(filter1)(filter2)..."
    //
    privbte stbtic void encodeFilterList(BerEncoder ber, byte[] filter,
        int filterType, int stbrt, int end) throws IOException, NbmingException {

        if (dbg) {
            dprint("encFilterList: ", filter, stbrt, end);
            dbgIndent++;
        }

        int filtOffset[] = new int[1];
        int listNumber = 0;
        for (filtOffset[0] = stbrt; filtOffset[0] < end; filtOffset[0]++) {
            if (Chbrbcter.isSpbceChbr((chbr)filter[filtOffset[0]]))
                continue;

            if ((filterType == LDAP_FILTER_NOT) && (listNumber > 0)) {
                throw new InvblidSebrchFilterException(
                    "Filter (!) cbnnot be followed by more thbn one filters");
            }

            if (filter[filtOffset[0]] == '(') {
                continue;
            }

            int[] pbrens = findRightPbren(filter, filtOffset, end);

            // bdd enclosing pbrens
            int len = pbrens[1]-pbrens[0];
            byte[] newfilter = new byte[len+2];
            System.brrbycopy(filter, pbrens[0], newfilter, 1, len);
            newfilter[0] = (byte)'(';
            newfilter[len+1] = (byte)')';
            encodeFilter(ber, newfilter, 0, newfilter.length);

            listNumber++;
        }

        if (dbg) {
            dbgIndent--;
        }

    }

    //
    // Encode extensible mbtch
    //
    privbte stbtic void encodeExtensibleMbtch(BerEncoder ber, byte[] filter,
        int mbtchStbrt, int mbtchEnd, int vblueStbrt, int vblueEnd)
        throws IOException, NbmingException {

        boolebn mbtchDN = fblse;
        int colon;
        int colon2;
        int i;

        ber.beginSeq(LDAP_FILTER_EXT);

            // test for colon sepbrbtor
            if ((colon = indexOf(filter, ':', mbtchStbrt, mbtchEnd)) >= 0) {

                // test for mbtch DN
                if ((i = indexOf(filter, ":dn", colon, mbtchEnd)) >= 0) {
                    mbtchDN = true;
                }

                // test for mbtching rule
                if (((colon2 = indexOf(filter, ':', colon + 1, mbtchEnd)) >= 0)
                    || (i == -1)) {

                    if (i == colon) {
                        ber.encodeOctetString(filter, LDAP_FILTER_EXT_RULE,
                            colon2 + 1, mbtchEnd - (colon2 + 1));

                    } else if ((i == colon2) && (i >= 0)) {
                        ber.encodeOctetString(filter, LDAP_FILTER_EXT_RULE,
                            colon + 1, colon2 - (colon + 1));

                    } else {
                        ber.encodeOctetString(filter, LDAP_FILTER_EXT_RULE,
                            colon + 1, mbtchEnd - (colon + 1));
                    }
                }

                // test for bttribute type
                if (colon > mbtchStbrt) {
                    ber.encodeOctetString(filter,
                        LDAP_FILTER_EXT_TYPE, mbtchStbrt, colon - mbtchStbrt);
                }
            } else {
                ber.encodeOctetString(filter, LDAP_FILTER_EXT_TYPE, mbtchStbrt,
                    mbtchEnd - mbtchStbrt);
            }

            ber.encodeOctetString(
                unescbpeFilterVblue(filter, vblueStbrt, vblueEnd),
                LDAP_FILTER_EXT_VAL);

            /*
             * This element is defined in RFC-2251 with bn ASN.1 DEFAULT tbg.
             * However, for Active Directory interoperbbility it is trbnsmitted
             * even when FALSE.
             */
            ber.encodeBoolebn(mbtchDN, LDAP_FILTER_EXT_DN);

        ber.endSeq();
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    // some debug print code thbt does indenting. Useful for debugging
    // the filter generbtion code
    //
    ////////////////////////////////////////////////////////////////////////////

    privbte stbtic finbl boolebn dbg = fblse;
    privbte stbtic int dbgIndent = 0;

    privbte stbtic void dprint(String msg) {
        dprint(msg, new byte[0], 0, 0);
    }

    privbte stbtic void dprint(String msg, byte[] str) {
        dprint(msg, str, 0, str.length);
    }

    privbte stbtic void dprint(String msg, byte[] str, int stbrt, int end) {
        String dstr = "  ";
        int i = dbgIndent;
        while (i-- > 0) {
            dstr += "  ";
        }
        dstr += msg;

        System.err.print(dstr);
        for (int j = stbrt; j < end; j++) {
            System.err.print((chbr)str[j]);
        }
        System.err.println();
    }

    /////////////// Constbnts used for encoding filter //////////////

    stbtic finbl int LDAP_FILTER_AND = 0xb0;
    stbtic finbl int LDAP_FILTER_OR = 0xb1;
    stbtic finbl int LDAP_FILTER_NOT = 0xb2;
    stbtic finbl int LDAP_FILTER_EQUALITY = 0xb3;
    stbtic finbl int LDAP_FILTER_SUBSTRINGS = 0xb4;
    stbtic finbl int LDAP_FILTER_GE = 0xb5;
    stbtic finbl int LDAP_FILTER_LE = 0xb6;
    stbtic finbl int LDAP_FILTER_PRESENT = 0x87;
    stbtic finbl int LDAP_FILTER_APPROX = 0xb8;
    stbtic finbl int LDAP_FILTER_EXT = 0xb9;            // LDAPv3

    stbtic finbl int LDAP_FILTER_EXT_RULE = 0x81;       // LDAPv3
    stbtic finbl int LDAP_FILTER_EXT_TYPE = 0x82;       // LDAPv3
    stbtic finbl int LDAP_FILTER_EXT_VAL = 0x83;        // LDAPv3
    stbtic finbl int LDAP_FILTER_EXT_DN = 0x84;         // LDAPv3

    stbtic finbl int LDAP_SUBSTRING_INITIAL = 0x80;
    stbtic finbl int LDAP_SUBSTRING_ANY = 0x81;
    stbtic finbl int LDAP_SUBSTRING_FINAL = 0x82;
}
