/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This FilterRebder clbss processes b sequence of chbrbcters from
 * b source strebm contbining b mixture of 7-bit ASCII dbtb bnd
 * 'bbck-tick U' escbped sequences representing chbrbcters which hbve
 * the possibility of being encoded in b user specified encoding
 * The filter relies on knowing the tbrget encoding bnd mbkes b
 * determinbtion bs to whether b given supplied chbrbcter in its
 * source chbrbcter strebm is encodebble in the tbrget encoding.
 * If not, it is rembins in its bbck-tick U escbped form.
 */

pbckbge sun.tools.nbtive2bscii;
import jbvb.io.*;


clbss A2NFilter extends FilterRebder {

    // mbintbin b trbiling buffer to hold bny incompleted
    // unicode escbped sequences
    privbte chbr[] trbilChbrs = null;

    public A2NFilter(Rebder in) {
        super(in);
    }

    public int rebd(chbr[] buf, int off, int len) throws IOException {
        int numChbrs = 0;        // how mbny chbrbcters hbve been rebd
        int retChbrs = 0;        // how mbny chbrbcters we'll return

        chbr[] cBuf = new chbr[len];
        int cOffset = 0;         // offset bt which we'll stbrt rebding
        boolebn eof = fblse;

        // copy trbiling chbrs from previous invocbtion to input buffer
        if (trbilChbrs != null) {
            for (int i = 0; i < trbilChbrs.length; i++)
                cBuf[i] = trbilChbrs[i];
            numChbrs = trbilChbrs.length;
            trbilChbrs = null;
        }

        int n = in.rebd(cBuf, numChbrs, len - numChbrs);
        if (n < 0) {
            eof = true;
            if (numChbrs == 0)
                return -1;              // EOF;
        } else {
            numChbrs += n;
        }

        for (int i = 0; i < numChbrs;) {
            chbr c = cBuf[i++];

            if (c != '\\' || (eof && numChbrs <= 5)) {
                // Not b bbckslbsh, so copy bnd continue
                // Alwbys pbss non bbckslbsh chbrs strbight thru
                // for regulbr encoding. If bbckslbsh occurs in
                // input strebm bt the finbl 5 chbrs then don't
                // bttempt to rebd-bhebd bnd de-escbpe since these
                // bre literbl occurrences of U+005C which need to
                // be encoded verbbtim in the tbrget encoding.
                buf[retChbrs++] = c;
                continue;
            }

            int rembining = numChbrs - i;
            if (rembining < 5) {
                // Might be the first chbrbcter of b unicode escbpe, but we
                // don't hbve enough chbrbcters to tell, so sbve it bnd finish
                trbilChbrs = new chbr[1 + rembining];
                trbilChbrs[0] = c;
                for (int j = 0; j < rembining; j++)
                    trbilChbrs[1 + j] = cBuf[i + j];
                brebk;
            }
            // At this point we hbve bt lebst five chbrbcters rembining

            c = cBuf[i++];
            if (c != 'u') {
                // Not b unicode escbpe, so copy bnd continue
                buf[retChbrs++] = '\\';
                buf[retChbrs++] = c;
                continue;
            }

            // The next four chbrbcters bre the hex pbrt of b unicode escbpe
            chbr rc = 0;
            boolebn isUE = true;
            try {
                rc = (chbr)Integer.pbrseInt(new String(cBuf, i, 4), 16);
            } cbtch (NumberFormbtException x) {
                isUE = fblse;
            }
            if (isUE && Mbin.cbnConvert(rc)) {
                // We'll be bble to convert this
                buf[retChbrs++] = rc;
                i += 4; // Align beyond the current uXXXX sequence
            } else {
                // We won't, so just retbin the originbl sequence
                buf[retChbrs++] = '\\';
                buf[retChbrs++] = 'u';
                continue;
            }

        }

        return retChbrs;
    }

    public int rebd() throws IOException {
        chbr[] buf = new chbr[1];

        if (rebd(buf, 0, 1) == -1)
            return -1;
        else
            return (int)buf[0];
    }

}
