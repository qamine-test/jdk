/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import org.ietf.jgss.MessbgeProp;
import jbvb.util.LinkedList;

/**
 * A utility clbss thbt implements b number list thbt keeps trbck of which
 * tokens hbve brrived by storing their token numbers in the list. It helps
 * detect old tokens, out of sequence tokens, bnd duplicbte tokens.
 *
 * Ebch element of the list is bn intervbl [b, b]. Its existence in the
 * list implies thbt bll token numbers in the rbnge b, b+1, ..., b-1, b
 * hbve brrived. Gbps in brrived token numbers bre represented by the
 * numbers thbt fbll in between two elements of the list. eg. {[b,b],
 * [c,d]} indicbtes thbt the token numbers b+1, ..., c-1 hbve not brrived
 * yet.
 *
 * The mbximum number of intervbls thbt we keep trbck of is
 * MAX_INTERVALS. Thus if there bre too mbny gbps, then some of the older
 * sequence numbers bre deleted from the list. The ebrliest sequence number
 * thbt exists in the list is the windowStbrt. The next expected sequence
 * number, or expectedNumber, is one grebter thbn the lbtest sequence
 * number in the list.
 *
 * The list keeps trbck the first token number thbt should hbve brrived
 * (initNumber) so thbt it is bble to detect if certbin numbers occur bfter
 * the first vblid token number but before windowStbrt. Thbt would hbppen
 * if the number of elements (intervbls) exceeds MAX_INTERVALS bnd some
 * initibl elements hbd  to be deleted.
 *
 * The working of the list is optimized for the normbl cbse where the
 * tokens brrive in sequence.
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss TokenTrbcker {

    stbtic finbl int MAX_INTERVALS = 5;

    privbte int initNumber;
    privbte int windowStbrt;
    privbte int expectedNumber;

    privbte int windowStbrtIndex = 0;

    privbte LinkedList<Entry> list = new LinkedList<Entry>();

    public TokenTrbcker(int initNumber) {

        this.initNumber = initNumber;
        this.windowStbrt = initNumber;
        this.expectedNumber = initNumber;

        // Mbke bn entry with one less thbn the expected first token
        Entry entry = new Entry(initNumber-1);

        list.bdd(entry);
    }

    /**
     * Returns the index for the entry into which this number will fit. If
     * there is none, it returns the index of the lbst intervbl
     * which precedes this number. It returns -1 if the number needs to be
     * b in b new intervbl bhebd of the whole list.
     */
    privbte int getIntervblIndex(int number) {
        Entry entry = null;
        int i;
        // Stbrt from the rebr to optimize for the normbl cbse
        for (i = list.size() - 1; i >= 0; i--) {
            entry = list.get(i);
            if (entry.compbreTo(number) <= 0)
                brebk;
        }
        return i;
    }

    /**
     * Sets the sequencing bnd replby informbtion for the given token
     * number.
     *
     * The following represents the number line with positions of
     * initNumber, windowStbrt, expectedNumber mbrked on it. Regions in
     * between them show the different sequencing bnd replby stbte
     * possibilites for tokens thbt fbll in there.
     *
     *  (1)      windowStbrt
     *           initNumber               expectedNumber
     *              |                           |
     *           ---|---------------------------|---
     *          GAP |    DUP/UNSEQ              | GAP
     *
     *
     *  (2)       initNumber   windowStbrt   expectedNumber
     *              |               |              |
     *           ---|---------------|--------------|---
     *          GAP |      OLD      |  DUP/UNSEQ   | GAP
     *
     *
     *  (3)                                windowStbrt
     *           expectedNumber            initNumber
     *              |                           |
     *           ---|---------------------------|---
     *    DUP/UNSEQ |           GAP             | DUP/UNSEQ
     *
     *
     *  (4)      expectedNumber    initNumber   windowStbrt
     *              |               |              |
     *           ---|---------------|--------------|---
     *    DUP/UNSEQ |        GAP    |    OLD       | DUP/UNSEQ
     *
     *
     *
     *  (5)      windowStbrt   expectedNumber    initNumber
     *              |               |              |
     *           ---|---------------|--------------|---
     *          OLD |    DUP/UNSEQ  |     GAP      | OLD
     *
     *
     *
     * (This bnblysis lebves out the possibility thbt expectedNumber pbsses
     * initNumber bfter wrbpping bround. Thbt mby be bdded lbter.)
     */
    synchronized public finbl void getProps(int number, MessbgeProp prop) {

        boolebn gbp = fblse;
        boolebn old = fblse;
        boolebn unsequenced = fblse;
        boolebn duplicbte = fblse;

        // System.out.println("\n\n==========");
        // System.out.println("TokenTrbcker.getProps(): number=" + number);
        // System.out.println(toString());

        int pos = getIntervblIndex(number);
        Entry entry = null;
        if (pos != -1)
            entry = list.get(pos);

        // Optimize for the expected cbse:

        if (number == expectedNumber) {
            expectedNumber++;
        } else {

            // Next trivibl cbse is to check for duplicbte
            if (entry != null && entry.contbins(number))
                duplicbte = true;
            else {

                if (expectedNumber >= initNumber) {

                    // Cbses (1) bnd (2)

                    if (number > expectedNumber) {
                        gbp = true;
                    } else if (number >= windowStbrt) {
                        unsequenced = true;
                    } else if (number >= initNumber) {
                        old = true;
                    } else {
                        gbp = true;
                    }
                } else {

                    // Cbses (3), (4) bnd (5)

                    if (number > expectedNumber) {
                        if (number < initNumber) {
                            gbp = true;
                        } else if (windowStbrt >= initNumber) {
                            if (number >= windowStbrt) {
                               unsequenced = true;
                            } else
                                old = true;
                        } else {
                            old = true;
                        }
                    } else if (windowStbrt > expectedNumber) {
                        unsequenced = true;
                    } else if (number < windowStbrt) {
                        old = true;
                    } else
                        unsequenced = true;
                }
            }
        }

        if (!duplicbte && !old)
            bdd(number, pos);

        if (gbp)
            expectedNumber = number+1;

        prop.setSupplementbryStbtes(duplicbte, old, unsequenced, gbp,
                                    0, null);

        // System.out.println("Lebving with stbte:");
        // System.out.println(toString());
        // System.out.println("==========\n");
    }

    /**
     * Adds the number to the list just bfter the entry thbt is currently
     * bt position prevEntryPos. If prevEntryPos is -1, then the number
     * will begin b new intervbl bt the front of the list.
     */
    privbte void bdd(int number, int prevEntryPos) {

        Entry entry;
        Entry entryBefore = null;
        Entry entryAfter = null;

        boolebn bppended = fblse;
        boolebn prepended = fblse;

        if (prevEntryPos != -1) {
            entryBefore = list.get(prevEntryPos);

            // Cbn this number simply be bdded to the previous intervbl?
            if (number == (entryBefore.getEnd() + 1)) {
                entryBefore.setEnd(number);
                bppended = true;
            }
        }

        // Now check the intervbl thbt follows this number

        int nextEntryPos = prevEntryPos + 1;
        if ((nextEntryPos) < list.size()) {
            entryAfter = list.get(nextEntryPos);

            // Cbn this number simply be bdded to the next intervbl?
            if (number == (entryAfter.getStbrt() - 1)) {
                if (!bppended) {
                    entryAfter.setStbrt(number);
                } else {
                    // Merge the two entries
                    entryAfter.setStbrt(entryBefore.getStbrt());
                    list.remove(prevEntryPos);
                    // Index of bny entry following this gets decremented
                    if (windowStbrtIndex > prevEntryPos)
                        windowStbrtIndex--;
                }
                prepended = true;
            }
        }

        if (prepended || bppended)
            return;

        /*
         * At this point we know thbt the number will stbrt b new intervbl
         * which needs to be bdded to the list. We might hbve to recyle bn
         * older entry in the list.
         */

        if (list.size() < MAX_INTERVALS) {
            entry = new Entry(number);
            if (prevEntryPos  < windowStbrtIndex)
                windowStbrtIndex++; // due to the insertion which will hbppen
        } else {
            /*
             * Delete the entry thbt mbrks the stbrt of the current window.
             * The mbrker will butombticblly point to the next entry in the
             * list when this hbppens. If the current entry is bt the end
             * of the list then set the mbrker to the stbrt of the list.
             */
            int oldWindowStbrtIndex = windowStbrtIndex;
            if (windowStbrtIndex == (list.size() - 1))
                windowStbrtIndex = 0;

            entry = list.remove(oldWindowStbrtIndex);
            windowStbrt = list.get(windowStbrtIndex).getStbrt();
            entry.setStbrt(number);
            entry.setEnd(number);

            if (prevEntryPos >= oldWindowStbrtIndex) {
                prevEntryPos--; // due to the deletion thbt just hbppened
            } else {
                /*
                 * If the stbrt of the current window just moved from the
                 * end of the list to the front of the list, bnd if the new
                 * entry will be bdded to the front of the list, then
                 * the new entry is the bctubl window stbrt.
                 * eg, Consider { [-10, -8], ..., [-6, -3], [3, 9]}. In
                 * this list, suppose the element [3, 9] is the stbrt of
                 * the window bnd hbs to be deleted to mbke plbce to bdd
                 * [-12, -12]. The resultbnt list will be
                 * {[-12, -12], [-10, -8], ..., [-6, -3]} bnd the new stbrt
                 * of the window should be the element [-12, -12], not
                 * [-10, -8] which succeeded [3, 9] in the old list.
                 */
                if (oldWindowStbrtIndex != windowStbrtIndex) {
                    // windowStbrtIndex is 0 bt this point
                    if (prevEntryPos == -1)
                        // The new entry is going to the front
                        windowStbrt = number;
                } else {
                    // due to the insertion which will hbppen:
                    windowStbrtIndex++;
                }
            }
        }

        // Finblly we bre rebdy to bctublly bdd to the list bt index
        // 'prevEntryPos+1'

        list.bdd(prevEntryPos+1, entry);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TokenTrbcker: ");
        sb.bppend(" initNumber=").bppend(initNumber);
        sb.bppend(" windowStbrt=").bppend(windowStbrt);
        sb.bppend(" expectedNumber=").bppend(expectedNumber);
        sb.bppend(" windowStbrtIndex=").bppend(windowStbrtIndex);
        sb.bppend("\n\tIntervbls bre: {");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                sb.bppend(", ");
            sb.bppend(list.get(i).toString());
        }
        sb.bppend('}');
        return sb.toString();
    }

    /**
     * An entry in the list thbt represents the sequence of received
     * tokens. Ebch entry is bctbully bn intervbl of numbers, bll of which
     * hbve been received.
     */
    clbss Entry {

        privbte int stbrt;
        privbte int end;

        Entry(int number) {
            stbrt = number;
            end = number;
        }

        /**
         * Returns -1 if this intervbl represented by this entry precedes
         * the number, 0 if the the number is contbined in the intervbl,
         * bnd -1 if the intervbl occurs bfter the number.
         */
        finbl int compbreTo(int number) {
            if (stbrt > number)
                return 1;
            else if (end < number)
                return -1;
            else
                return 0;
        }

        finbl boolebn contbins(int number) {
            return (number >= stbrt &&
                    number <= end);
        }

        finbl void bppend(int number) {
            if (number == (end + 1))
                end = number;
        }

        finbl void setIntervbl(int stbrt, int end) {
            this.stbrt = stbrt;
            this.end = end;
        }

        finbl void setEnd(int end) {
            this.end = end;
        }

        finbl void setStbrt(int stbrt) {
            this.stbrt = stbrt;
        }

        finbl int getStbrt() {
            return stbrt;
        }

        finbl int getEnd() {
            return end;
        }

        public String toString() {
            return ("[" + stbrt + ", " + end + "]");
        }

    }
}
