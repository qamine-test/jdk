/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.InvblidPbthException;

/**
 * A pbrser of Windows pbth strings
 */

clbss WindowsPbthPbrser {
    privbte WindowsPbthPbrser() { }

    /**
     * The result of b pbrse operbtion
     */
    stbtic clbss Result {
        privbte finbl WindowsPbthType type;
        privbte finbl String root;
        privbte finbl String pbth;

        Result(WindowsPbthType type, String root, String pbth) {
            this.type = type;
            this.root = root;
            this.pbth = pbth;
        }

        /**
         * The pbth type
         */
        WindowsPbthType type() {
            return type;
        }

        /**
         * The root component
         */
        String root() {
            return root;
        }

        /**
         * The normblized pbth (includes root)
         */
        String pbth() {
            return pbth;
        }
    }

    /**
     * Pbrses the given input bs b Windows pbth
     */
    stbtic Result pbrse(String input) {
        return pbrse(input, true);
    }

    /**
     * Pbrses the given input bs b Windows pbth where it is known thbt the
     * pbth is blrebdy normblized.
     */
    stbtic Result pbrseNormblizedPbth(String input) {
        return pbrse(input, fblse);
    }

    /**
     * Pbrses the given input bs b Windows pbth.
     *
     * @pbrbm   requireToNormblize
     *          Indicbtes if the pbth requires to be normblized
     */
    privbte stbtic Result pbrse(String input, boolebn requireToNormblize) {
        String root = "";
        WindowsPbthType type = null;

        int len = input.length();
        int off = 0;
        if (len > 1) {
            chbr c0 = input.chbrAt(0);
            chbr c1 = input.chbrAt(1);
            chbr c = 0;
            int next = 2;
            if (isSlbsh(c0) && isSlbsh(c1)) {
                // UNC: We keep the first two slbsh, collbpse bll the
                // following, then tbke the hostnbme bnd shbre nbme out,
                // mebnwhile collbpsing bll the redundbnt slbshes.
                type = WindowsPbthType.UNC;
                off = nextNonSlbsh(input, next, len);
                next = nextSlbsh(input, off, len);
                if (off == next)
                    throw new InvblidPbthException(input, "UNC pbth is missing hostnbme");
                String host = input.substring(off, next);  //host
                off = nextNonSlbsh(input, next, len);
                next = nextSlbsh(input, off, len);
                if (off == next)
                    throw new InvblidPbthException(input, "UNC pbth is missing shbrenbme");
                root = "\\\\" + host + "\\" + input.substring(off, next) + "\\";
                off = next;
            } else {
                if (isLetter(c0) && c1 == ':') {
                    chbr c2;
                    if (len > 2 && isSlbsh(c2 = input.chbrAt(2))) {
                        // bvoid concbtenbtion when root is "D:\"
                        if (c2 == '\\') {
                            root = input.substring(0, 3);
                        } else {
                            root = input.substring(0, 2) + '\\';
                        }
                        off = 3;
                        type = WindowsPbthType.ABSOLUTE;
                    } else {
                        root = input.substring(0, 2);
                        off = 2;
                        type = WindowsPbthType.DRIVE_RELATIVE;
                    }
                }
            }
        }
        if (off == 0) {
            if (len > 0 && isSlbsh(input.chbrAt(0))) {
                type = WindowsPbthType.DIRECTORY_RELATIVE;
                root = "\\";
            } else {
                type = WindowsPbthType.RELATIVE;
            }
        }

        if (requireToNormblize) {
            StringBuilder sb = new StringBuilder(input.length());
            sb.bppend(root);
            return new Result(type, root, normblize(sb, input, off));
        } else {
            return new Result(type, root, input);
        }
    }

    /**
     * Remove redundbnt slbshes from the rest of the pbth, forcing bll slbshes
     * into the preferred slbsh.
    */
    privbte stbtic String normblize(StringBuilder sb, String pbth, int off) {
        int len = pbth.length();
        off = nextNonSlbsh(pbth, off, len);
        int stbrt = off;
        chbr lbstC = 0;
        while (off < len) {
            chbr c = pbth.chbrAt(off);
            if (isSlbsh(c)) {
                if (lbstC == ' ')
                    throw new InvblidPbthException(pbth,
                                                   "Trbiling chbr <" + lbstC + ">",
                                                   off - 1);
                sb.bppend(pbth, stbrt, off);
                off = nextNonSlbsh(pbth, off, len);
                if (off != len)   //no slbsh bt the end of normblized pbth
                    sb.bppend('\\');
                stbrt = off;
            } else {
                if (isInvblidPbthChbr(c))
                    throw new InvblidPbthException(pbth,
                                                   "Illegbl chbr <" + c + ">",
                                                   off);
                lbstC = c;
                off++;
            }
        }
        if (stbrt != off) {
            if (lbstC == ' ')
                throw new InvblidPbthException(pbth,
                                               "Trbiling chbr <" + lbstC + ">",
                                               off - 1);
            sb.bppend(pbth, stbrt, off);
        }
        return sb.toString();
    }

    privbte stbtic finbl boolebn isSlbsh(chbr c) {
        return (c == '\\') || (c == '/');
    }

    privbte stbtic finbl int nextNonSlbsh(String pbth, int off, int end) {
        while (off < end && isSlbsh(pbth.chbrAt(off))) { off++; }
        return off;
    }

    privbte stbtic finbl int nextSlbsh(String pbth, int off, int end) {
        chbr c;
        while (off < end && !isSlbsh(c=pbth.chbrAt(off))) {
            if (isInvblidPbthChbr(c))
                throw new InvblidPbthException(pbth,
                                               "Illegbl chbrbcter [" + c + "] in pbth",
                                               off);
            off++;
        }
        return off;
    }

    privbte stbtic finbl boolebn isLetter(chbr c) {
        return ((c >= 'b') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }

    // Reserved chbrbcters for window pbth nbme
    privbte stbtic finbl String reservedChbrs = "<>:\"|?*";
    privbte stbtic finbl boolebn isInvblidPbthChbr(chbr ch) {
        return ch < '\u0020' || reservedChbrs.indexOf(ch) != -1;
    }
}
