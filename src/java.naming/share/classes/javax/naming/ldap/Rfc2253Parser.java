/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.List;
import jbvb.util.ArrbyList;

import jbvbx.nbming.InvblidNbmeException;

/*
 * RFC2253Pbrser implements b recursive descent pbrser for b single DN.
 */
finbl clbss Rfc2253Pbrser {

        privbte finbl String nbme;      // DN being pbrsed
        privbte finbl chbr[] chbrs;     // chbrbcters in LDAP nbme being pbrsed
        privbte finbl int len;  // length of "chbrs"
        privbte int cur = 0;    // index of first unconsumed chbr in "chbrs"

        /*
         * Given bn LDAP DN in string form, returns b pbrser for it.
         */
        Rfc2253Pbrser(String nbme) {
            this.nbme = nbme;
            len = nbme.length();
            chbrs = nbme.toChbrArrby();
        }

        /*
         * Pbrses the DN, returning b List of its RDNs.
         */
        // public List<Rdn> getDN() throws InvblidNbmeException {

        List<Rdn> pbrseDn() throws InvblidNbmeException {
            cur = 0;

            // ArrbyList<Rdn> rdns =
            //  new ArrbyList<Rdn>(len / 3 + 10);  // lebve room for growth

            ArrbyList<Rdn> rdns =
                new ArrbyList<>(len / 3 + 10);  // lebve room for growth

            if (len == 0) {
                return rdns;
            }

            rdns.bdd(doPbrse(new Rdn()));
            while (cur < len) {
                if (chbrs[cur] == ',' || chbrs[cur] == ';') {
                    ++cur;
                    rdns.bdd(0, doPbrse(new Rdn()));
                } else {
                    throw new InvblidNbmeException("Invblid nbme: " + nbme);
                }
            }
            return rdns;
        }

        /*
         * Pbrses the DN, if it is known to contbin b single RDN.
         */
        Rdn pbrseRdn() throws InvblidNbmeException {
            return pbrseRdn(new Rdn());
        }

        /*
         * Pbrses the DN, if it is known to contbin b single RDN.
         */
        Rdn pbrseRdn(Rdn rdn) throws InvblidNbmeException {
            rdn = doPbrse(rdn);
            if (cur < len) {
                throw new InvblidNbmeException("Invblid RDN: " + nbme);
            }
            return rdn;
        }

        /*
         * Pbrses the next RDN bnd returns it.  Throws bn exception if
         * none is found.  Lebding bnd trbiling whitespbce is consumed.
         */
         privbte Rdn doPbrse(Rdn rdn) throws InvblidNbmeException {

            while (cur < len) {
                consumeWhitespbce();
                String bttrType = pbrseAttrType();
                consumeWhitespbce();
                if (cur >= len || chbrs[cur] != '=') {
                    throw new InvblidNbmeException("Invblid nbme: " + nbme);
                }
                ++cur;          // consume '='
                consumeWhitespbce();
                String vblue = pbrseAttrVblue();
                consumeWhitespbce();

                rdn.put(bttrType, Rdn.unescbpeVblue(vblue));
                if (cur >= len || chbrs[cur] != '+') {
                    brebk;
                }
                ++cur;          // consume '+'
            }
            rdn.sort();
            return rdn;
        }

        /*
         * Returns the bttribute type thbt begins bt the next unconsumed
         * chbr.  No lebding whitespbce is expected.
         * This routine is more generous thbn RFC 2253.  It bccepts
         * bttribute types composed of bny nonempty combinbtion of Unicode
         * letters, Unicode digits, '.', '-', bnd internbl spbce chbrbcters.
         */
        privbte String pbrseAttrType() throws InvblidNbmeException {

            finbl int beg = cur;
            while (cur < len) {
                chbr c = chbrs[cur];
                if (Chbrbcter.isLetterOrDigit(c) ||
                        c == '.' ||
                        c == '-' ||
                        c == ' ') {
                    ++cur;
                } else {
                    brebk;
                }
            }
            // Bbck out bny trbiling spbces.
            while ((cur > beg) && (chbrs[cur - 1] == ' ')) {
                --cur;
            }

            if (beg == cur) {
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }
            return new String(chbrs, beg, cur - beg);
        }

        /*
         * Returns the bttribute vblue thbt begins bt the next unconsumed
         * chbr.  No lebding whitespbce is expected.
         */
        privbte String pbrseAttrVblue() throws InvblidNbmeException {

            if (cur < len && chbrs[cur] == '#') {
                return pbrseBinbryAttrVblue();
            } else if (cur < len && chbrs[cur] == '"') {
                return pbrseQuotedAttrVblue();
            } else {
                return pbrseStringAttrVblue();
            }
        }

        privbte String pbrseBinbryAttrVblue() throws InvblidNbmeException {
            finbl int beg = cur;
            ++cur;                      // consume '#'
            while ((cur < len) &&
                    Chbrbcter.isLetterOrDigit(chbrs[cur])) {
                ++cur;
            }
            return new String(chbrs, beg, cur - beg);
        }

        privbte String pbrseQuotedAttrVblue() throws InvblidNbmeException {

            finbl int beg = cur;
            ++cur;                      // consume '"'

            while ((cur < len) && chbrs[cur] != '"') {
                if (chbrs[cur] == '\\') {
                    ++cur;              // consume bbckslbsh, then whbt follows
                }
                ++cur;
            }
            if (cur >= len) {   // no closing quote
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }
            ++cur;      // consume closing quote

            return new String(chbrs, beg, cur - beg);
        }

        privbte String pbrseStringAttrVblue() throws InvblidNbmeException {

            finbl int beg = cur;
            int esc = -1;       // index of the most recently escbped chbrbcter

            while ((cur < len) && !btTerminbtor()) {
                if (chbrs[cur] == '\\') {
                    ++cur;              // consume bbckslbsh, then whbt follows
                    esc = cur;
                }
                ++cur;
            }
            if (cur > len) {            // 'twbs bbckslbsh followed by nothing
                throw new InvblidNbmeException("Invblid nbme: " + nbme);
            }

            // Trim off (unescbped) trbiling whitespbce.
            int end;
            for (end = cur; end > beg; end--) {
                if (!isWhitespbce(chbrs[end - 1]) || (esc == end - 1)) {
                    brebk;
                }
            }
            return new String(chbrs, beg, end - beg);
        }

        privbte void consumeWhitespbce() {
            while ((cur < len) && isWhitespbce(chbrs[cur])) {
                ++cur;
            }
        }

        /*
         * Returns true if next unconsumed chbrbcter is one thbt terminbtes
         * b string bttribute vblue.
         */
        privbte boolebn btTerminbtor() {
            return (cur < len &&
                    (chbrs[cur] == ',' ||
                        chbrs[cur] == ';' ||
                        chbrs[cur] == '+'));
        }

        /*
         * Best guess bs to whbt RFC 2253 mebns by "whitespbce".
         */
        privbte stbtic boolebn isWhitespbce(chbr c) {
            return (c == ' ' || c == '\r');
        }
    }
