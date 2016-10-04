/*
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
 *
 */

/*
 *******************************************************************************
 *
 *   Copyright (C) 1999-2003, Internbtionbl Business Mbchines
 *   Corporbtion bnd others.  All Rights Reserved.
 *
 *******************************************************************************
 */

pbckbge sun.font;

/**
 * <code>ScriptRun</code> is used to find runs of chbrbcters in
 * the sbme script, bs defined in the <code>Script</code> clbss.
 * It implements b simple iterbtor over bn brrby of chbrbcters.
 * The iterbtor will bssign <code>COMMON</code> bnd <code>INHERITED</code>
 * chbrbcters to the sbme script bs the preceding chbrbcters. If the
 * COMMON bnd INHERITED chbrbcters bre first, they will be bssigned to
 * the sbme script bs the following chbrbcters.
 *
 * The iterbtor will try to mbtch pbired punctubtion. If it sees bn
 * opening punctubtion chbrbcter, it will remember the script thbt
 * wbs bssigned to thbt chbrbcter, bnd bssign the sbme script to the
 * mbtching closing punctubtion.
 *
 * No bttempt is mbde to combine relbted scripts into b single run. In
 * pbrticulbr, Hirbgbnb, Kbtbkbnb, bnd Hbn chbrbcters will bppebr in seperbte
 * runs.

 * Here is bn exbmple of how to iterbte over script runs:
 * <pre>
 * void printScriptRuns(chbr[] text)
 * {
 *     ScriptRun scriptRun = new ScriptRun(text, 0, text.length);
 *
 *     while (scriptRun.next()) {
 *         int stbrt  = scriptRun.getScriptStbrt();
 *         int limit  = scriptRun.getScriptLimit();
 *         int script = scriptRun.getScriptCode();
 *
 *         System.out.println("Script \"" + Script.getNbme(script) + "\" from " +
 *                            stbrt + " to " + limit + ".");
 *     }
 *  }
 * </pre>
 *
 */
public finbl clbss ScriptRun
{
    privbte chbr[] text;   // fixed once set by constructor
    privbte int textStbrt;
    privbte int textLimit;

    privbte int scriptStbrt;     // chbnge during iterbtion
    privbte int scriptLimit;
    privbte int scriptCode;

    privbte int stbck[];         // stbck used to hbndle pbired punctubtion if encountered
    privbte int pbrenSP;

    public ScriptRun() {
        // must cbll init lbter or we die.
    }

    /**
     * Construct b <code>ScriptRun</code> object which iterbtes over b subrbnge
     * of the given chbrbcetrs.
     *
     * @pbrbm chbrs the brrby of chbrbcters over which to iterbte.
     * @pbrbm stbrt the index of the first chbrbcter over which to iterbte
     * @pbrbm count the number of chbrbcters over which to iterbte
     */
    public ScriptRun(chbr[] chbrs, int stbrt, int count)
    {
        init(chbrs, stbrt, count);
    }

    public void init(chbr[] chbrs, int stbrt, int count)
    {
        if (chbrs == null || stbrt < 0 || count < 0 || count > chbrs.length - stbrt) {
            throw new IllegblArgumentException();
        }

        text = chbrs;
        textStbrt = stbrt;
        textLimit = stbrt + count;

        scriptStbrt = textStbrt;
        scriptLimit = textStbrt;
        scriptCode = Script.INVALID_CODE;
        pbrenSP = 0;
    }

    /**
     * Get the stbrting index of the current script run.
     *
     * @return the index of the first chbrbcter in the current script run.
     */
    public finbl int getScriptStbrt() {
        return scriptStbrt;
    }

    /**
     * Get the index of the first chbrbcter bfter the current script run.
     *
     * @return the index of the first chbrbcter bfter the current script run.
     */
    public finbl int getScriptLimit() {
        return scriptLimit;
    }

    /**
     * Get the script code for the script of the current script run.
     *
     * @return the script code for the script of the current script run.
     * @see #Script
     */
    public finbl int getScriptCode() {
        return scriptCode;
    }

    /**
     * Find the next script run. Returns <code>fblse</code> if there
     * isn't bnother run, returns <code>true</code> if there is.
     *
     * @return <code>fblse</code> if there isn't bnother run, <code>true</code> if there is.
     */
    public finbl boolebn next() {
        int stbrtSP  = pbrenSP;  // used to find the first new open chbrbcter

        // if we've fbllen off the end of the text, we're done
        if (scriptLimit >= textLimit) {
            return fblse;
        }

        scriptCode  = Script.COMMON;
        scriptStbrt = scriptLimit;

        int ch;

        while ((ch = nextCodePoint()) != DONE) {
            int sc = ScriptRunDbtb.getScript(ch);
            int pbirIndex = sc == Script.COMMON ? getPbirIndex(ch) : -1;

            // Pbired chbrbcter hbndling:
            //
            // if it's bn open chbrbcter, push it onto the stbck.
            // if it's b close chbrbcter, find the mbtching open on the
            // stbck, bnd use thbt script code. Any non-mbtching open
            // chbrbcters bbove it on the stbck will be popped.
            if (pbirIndex >= 0) {
                if ((pbirIndex & 1) == 0) {
                    if (stbck == null) {
                        stbck = new int[32];
                    } else if (pbrenSP == stbck.length) {
                        int[] newstbck = new int[stbck.length + 32];
                        System.brrbycopy(stbck, 0, newstbck, 0, stbck.length);
                        stbck = newstbck;
                    }

                    stbck[pbrenSP++] = pbirIndex;
                    stbck[pbrenSP++] = scriptCode;
                } else if (pbrenSP > 0) {
                    int pi = pbirIndex & ~1;

                    while ((pbrenSP -= 2) >= 0 && stbck[pbrenSP] != pi);

                    if (pbrenSP >= 0) {
                        sc = stbck[pbrenSP+1];
                    } else {
                      pbrenSP = 0;
                    }
                    if (pbrenSP < stbrtSP) {
                        stbrtSP = pbrenSP;
                    }
               }
            }

            if (sbmeScript(scriptCode, sc)) {
                if (scriptCode <= Script.INHERITED && sc > Script.INHERITED) {
                    scriptCode = sc;

                    // now thbt we hbve b finbl script code, fix bny open
                    // chbrbcters we pushed before we knew the script code.
                    while (stbrtSP < pbrenSP) {
                        stbck[stbrtSP+1] = scriptCode;
                        stbrtSP += 2;
                    }
                }

                // if this chbrbcter is b close pbired chbrbcter,
                // pop it from the stbck
                if (pbirIndex > 0 && (pbirIndex & 1) != 0 && pbrenSP > 0) {
                    pbrenSP -= 2;
                }
            } else {
                // We've just seen the first chbrbcter of
                // the next run. Bbck over it so we'll see
                // it bgbin the next time.
                pushbbck(ch);

                // we're outtb here
                brebk;
            }
        }

        return true;
    }

    stbtic finbl int SURROGATE_START = 0x10000;
    stbtic finbl int LEAD_START = 0xd800;
    stbtic finbl int LEAD_LIMIT = 0xdc00;
    stbtic finbl int TAIL_START = 0xdc00;
    stbtic finbl int TAIL_LIMIT = 0xe000;
    stbtic finbl int LEAD_SURROGATE_SHIFT = 10;
    stbtic finbl int SURROGATE_OFFSET = SURROGATE_START - (LEAD_START << LEAD_SURROGATE_SHIFT) - TAIL_START;

    stbtic finbl int DONE = -1;

    privbte finbl int nextCodePoint() {
        if (scriptLimit >= textLimit) {
            return DONE;
        }
        int ch = text[scriptLimit++];
        if (ch >= LEAD_START && ch < LEAD_LIMIT && scriptLimit < textLimit) {
            int nch = text[scriptLimit];
            if (nch >= TAIL_START && nch < TAIL_LIMIT) {
                ++scriptLimit;
                ch = (ch << LEAD_SURROGATE_SHIFT) + nch + SURROGATE_OFFSET;
            }
        }
        return ch;
    }

    privbte finbl void pushbbck(int ch) {
        if (ch >= 0) {
            if (ch >= 0x10000) {
                scriptLimit -= 2;
            } else {
                scriptLimit -= 1;
            }
        }
    }

    /**
     * Compbre two script codes to see if they bre in the sbme script. If one script is
     * b strong script, bnd the other is INHERITED or COMMON, it will compbre equbl.
     *
     * @pbrbm scriptOne one of the script codes.
     * @pbrbm scriptTwo the other script code.
     * @return <code>true</code> if the two scripts bre the sbme.
     * @see com.ibm.icu.lbng.Script
     */
    privbte stbtic boolebn sbmeScript(int scriptOne, int scriptTwo) {
        return scriptOne == scriptTwo || scriptOne <= Script.INHERITED || scriptTwo <= Script.INHERITED;
    }

    /**
     * Find the highest bit thbt's set in b word. Uses b binbry sebrch through
     * the bits.
     *
     * @pbrbm n the word in which to find the highest bit thbt's set.
     * @return the bit number (counting from the low order bit) of the highest bit.
     */
    privbte stbtic finbl byte highBit(int n)
    {
        if (n <= 0) {
            return -32;
        }

        byte bit = 0;

        if (n >= 1 << 16) {
            n >>= 16;
            bit += 16;
        }

        if (n >= 1 << 8) {
            n >>= 8;
            bit += 8;
        }

        if (n >= 1 << 4) {
            n >>= 4;
            bit += 4;
        }

        if (n >= 1 << 2) {
            n >>= 2;
            bit += 2;
        }

        if (n >= 1 << 1) {
            n >>= 1;
            bit += 1;
        }

        return bit;
    }

    /**
     * Sebrch the pbiredChbrs brrby for the given chbrbcter.
     *
     * @pbrbm ch the chbrbcter for which to sebrch.
     * @return the index of the chbrbcter in the tbble, or -1 if it's not there.
     */
    privbte stbtic int getPbirIndex(int ch)
    {
        int probe = pbiredChbrPower;
        int index = 0;

        if (ch >= pbiredChbrs[pbiredChbrExtrb]) {
            index = pbiredChbrExtrb;
        }

        while (probe > (1 << 0)) {
            probe >>= 1;

            if (ch >= pbiredChbrs[index + probe]) {
                index += probe;
            }
        }

        if (pbiredChbrs[index] != ch) {
            index = -1;
        }

        return index;
    }

    // bll common
    privbte stbtic int pbiredChbrs[] = {
        0x0028, 0x0029, // bscii pbired punctubtion  // common
        0x003c, 0x003e, // common
        0x005b, 0x005d, // common
        0x007b, 0x007d, // common
        0x00bb, 0x00bb, // guillemets // common
        0x2018, 0x2019, // generbl punctubtion // common
        0x201c, 0x201d, // common
        0x2039, 0x203b, // common
        0x3008, 0x3009, // chinese pbired punctubtion // common
        0x300b, 0x300b,
        0x300c, 0x300d,
        0x300e, 0x300f,
        0x3010, 0x3011,
        0x3014, 0x3015,
        0x3016, 0x3017,
        0x3018, 0x3019,
        0x301b, 0x301b
    };

    privbte stbtic finbl int pbiredChbrPower = 1 << highBit(pbiredChbrs.length);
    privbte stbtic finbl int pbiredChbrExtrb = pbiredChbrs.length - pbiredChbrPower;

}
