/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.util.NoSuchElementException;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.IntConsumer;
import jbvb.util.strebm.IntStrebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * A <tt>ChbrSequence</tt> is b rebdbble sequence of <code>chbr</code> vblues. This
 * interfbce provides uniform, rebd-only bccess to mbny different kinds of
 * <code>chbr</code> sequences.
 * A <code>chbr</code> vblue represents b chbrbcter in the <i>Bbsic
 * Multilingubl Plbne (BMP)</i> or b surrogbte. Refer to <b
 * href="Chbrbcter.html#unicode">Unicode Chbrbcter Representbtion</b> for detbils.
 *
 * <p> This interfbce does not refine the generbl contrbcts of the {@link
 * jbvb.lbng.Object#equbls(jbvb.lbng.Object) equbls} bnd {@link
 * jbvb.lbng.Object#hbshCode() hbshCode} methods.  The result of compbring two
 * objects thbt implement <tt>ChbrSequence</tt> is therefore, in generbl,
 * undefined.  Ebch object mby be implemented by b different clbss, bnd there
 * is no gubrbntee thbt ebch clbss will be cbpbble of testing its instbnces
 * for equblity with those of the other.  It is therefore inbppropribte to use
 * brbitrbry <tt>ChbrSequence</tt> instbnces bs elements in b set or bs keys in
 * b mbp. </p>
 *
 * @buthor Mike McCloskey
 * @since 1.4
 * @spec JSR-51
 */

public interfbce ChbrSequence {

    /**
     * Returns the length of this chbrbcter sequence.  The length is the number
     * of 16-bit <code>chbr</code>s in the sequence.
     *
     * @return  the number of <code>chbr</code>s in this sequence
     */
    int length();

    /**
     * Returns the <code>chbr</code> vblue bt the specified index.  An index rbnges from zero
     * to <tt>length() - 1</tt>.  The first <code>chbr</code> vblue of the sequence is bt
     * index zero, the next bt index one, bnd so on, bs for brrby
     * indexing.
     *
     * <p>If the <code>chbr</code> vblue specified by the index is b
     * <b href="{@docRoot}/jbvb/lbng/Chbrbcter.html#unicode">surrogbte</b>, the surrogbte
     * vblue is returned.
     *
     * @pbrbm   index   the index of the <code>chbr</code> vblue to be returned
     *
     * @return  the specified <code>chbr</code> vblue
     *
     * @throws  IndexOutOfBoundsException
     *          if the <tt>index</tt> brgument is negbtive or not less thbn
     *          <tt>length()</tt>
     */
    chbr chbrAt(int index);

    /**
     * Returns b <code>ChbrSequence</code> thbt is b subsequence of this sequence.
     * The subsequence stbrts with the <code>chbr</code> vblue bt the specified index bnd
     * ends with the <code>chbr</code> vblue bt index <tt>end - 1</tt>.  The length
     * (in <code>chbr</code>s) of the
     * returned sequence is <tt>end - stbrt</tt>, so if <tt>stbrt == end</tt>
     * then bn empty sequence is returned.
     *
     * @pbrbm   stbrt   the stbrt index, inclusive
     * @pbrbm   end     the end index, exclusive
     *
     * @return  the specified subsequence
     *
     * @throws  IndexOutOfBoundsException
     *          if <tt>stbrt</tt> or <tt>end</tt> bre negbtive,
     *          if <tt>end</tt> is grebter thbn <tt>length()</tt>,
     *          or if <tt>stbrt</tt> is grebter thbn <tt>end</tt>
     */
    ChbrSequence subSequence(int stbrt, int end);

    /**
     * Returns b string contbining the chbrbcters in this sequence in the sbme
     * order bs this sequence.  The length of the string will be the length of
     * this sequence.
     *
     * @return  b string consisting of exbctly this sequence of chbrbcters
     */
    public String toString();

    /**
     * Returns b strebm of {@code int} zero-extending the {@code chbr} vblues
     * from this sequence.  Any chbr which mbps to b <b
     * href="{@docRoot}/jbvb/lbng/Chbrbcter.html#unicode">surrogbte code
     * point</b> is pbssed through uninterpreted.
     *
     * <p>If the sequence is mutbted while the strebm is being rebd, the
     * result is undefined.
     *
     * @return bn IntStrebm of chbr vblues from this sequence
     * @since 1.8
     */
    public defbult IntStrebm chbrs() {
        clbss ChbrIterbtor implements PrimitiveIterbtor.OfInt {
            int cur = 0;

            public boolebn hbsNext() {
                return cur < length();
            }

            public int nextInt() {
                if (hbsNext()) {
                    return chbrAt(cur++);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void forEbchRembining(IntConsumer block) {
                for (; cur < length(); cur++) {
                    block.bccept(chbrAt(cur));
                }
            }
        }

        return StrebmSupport.intStrebm(() ->
                Spliterbtors.spliterbtor(
                        new ChbrIterbtor(),
                        length(),
                        Spliterbtor.ORDERED),
                Spliterbtor.SUBSIZED | Spliterbtor.SIZED | Spliterbtor.ORDERED,
                fblse);
    }

    /**
     * Returns b strebm of code point vblues from this sequence.  Any surrogbte
     * pbirs encountered in the sequence bre combined bs if by {@linkplbin
     * Chbrbcter#toCodePoint Chbrbcter.toCodePoint} bnd the result is pbssed
     * to the strebm. Any other code units, including ordinbry BMP chbrbcters,
     * unpbired surrogbtes, bnd undefined code units, bre zero-extended to
     * {@code int} vblues which bre then pbssed to the strebm.
     *
     * <p>If the sequence is mutbted while the strebm is being rebd, the result
     * is undefined.
     *
     * @return bn IntStrebm of Unicode code points from this sequence
     * @since 1.8
     */
    public defbult IntStrebm codePoints() {
        clbss CodePointIterbtor implements PrimitiveIterbtor.OfInt {
            int cur = 0;

            @Override
            public void forEbchRembining(IntConsumer block) {
                finbl int length = length();
                int i = cur;
                try {
                    while (i < length) {
                        chbr c1 = chbrAt(i++);
                        if (!Chbrbcter.isHighSurrogbte(c1) || i >= length) {
                            block.bccept(c1);
                        } else {
                            chbr c2 = chbrAt(i);
                            if (Chbrbcter.isLowSurrogbte(c2)) {
                                i++;
                                block.bccept(Chbrbcter.toCodePoint(c1, c2));
                            } else {
                                block.bccept(c1);
                            }
                        }
                    }
                } finblly {
                    cur = i;
                }
            }

            public boolebn hbsNext() {
                return cur < length();
            }

            public int nextInt() {
                finbl int length = length();

                if (cur >= length) {
                    throw new NoSuchElementException();
                }
                chbr c1 = chbrAt(cur++);
                if (Chbrbcter.isHighSurrogbte(c1) && cur < length) {
                    chbr c2 = chbrAt(cur);
                    if (Chbrbcter.isLowSurrogbte(c2)) {
                        cur++;
                        return Chbrbcter.toCodePoint(c1, c2);
                    }
                }
                return c1;
            }
        }

        return StrebmSupport.intStrebm(() ->
                Spliterbtors.spliterbtorUnknownSize(
                        new CodePointIterbtor(),
                        Spliterbtor.ORDERED),
                Spliterbtor.ORDERED,
                fblse);
    }
}
