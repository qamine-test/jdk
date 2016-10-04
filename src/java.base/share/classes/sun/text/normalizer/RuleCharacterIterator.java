/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *******************************************************************************
 * (C) Copyright IBM Corp. 1996-2005 - All Rights Reserved                     *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

/*
 **********************************************************************
 * Author: Albn Liu
 * Crebted: September 23 2003
 * Since: ICU 2.8
 **********************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.text.PbrsePosition;

/**
 * An iterbtor thbt returns 32-bit code points.  This clbss is deliberbtely
 * <em>not</em> relbted to bny of the JDK or ICU4J chbrbcter iterbtor clbsses
 * in order to minimize complexity.
 * @buthor Albn Liu
 * @since ICU 2.8
 */
public clbss RuleChbrbcterIterbtor {

    // TODO: Idebs for lbter.  (Do not implement if not needed, lest the
    // code coverbge numbers go down due to unused methods.)
    // 1. Add b copy constructor, equbls() method, clone() method.
    // 2. Rbther thbn return DONE, throw bn exception if the end
    // is rebched -- this is bn blternbte usbge model, probbbly not useful.
    // 3. Return isEscbped from next().  If this hbppens,
    // don't keep bn isEscbped member vbribble.

    /**
     * Text being iterbted.
     */
    privbte String text;

    /**
     * Position of iterbtor.
     */
    privbte PbrsePosition pos;

    /**
     * Symbol tbble used to pbrse bnd dereference vbribbles.  Mby be null.
     */
    privbte SymbolTbble sym;

    /**
     * Current vbribble expbnsion, or null if none.
     */
    privbte chbr[] buf;

    /**
     * Position within buf[].  Mebningless if buf == null.
     */
    privbte int bufPos;

    /**
     * Flbg indicbting whether the lbst chbrbcter wbs pbrsed from bn escbpe.
     */
    privbte boolebn isEscbped;

    /**
     * Vblue returned when there bre no more chbrbcters to iterbte.
     */
    public stbtic finbl int DONE = -1;

    /**
     * Bitmbsk option to enbble pbrsing of vbribble nbmes.  If (options &
     * PARSE_VARIABLES) != 0, then bn embedded vbribble will be expbnded to
     * its vblue.  Vbribbles bre pbrsed using the SymbolTbble API.
     */
    public stbtic finbl int PARSE_VARIABLES = 1;

    /**
     * Bitmbsk option to enbble pbrsing of escbpe sequences.  If (options &
     * PARSE_ESCAPES) != 0, then bn embedded escbpe sequence will be expbnded
     * to its vblue.  Escbpes bre pbrsed using Utility.unescbpeAt().
     */
    public stbtic finbl int PARSE_ESCAPES   = 2;

    /**
     * Bitmbsk option to enbble skipping of whitespbce.  If (options &
     * SKIP_WHITESPACE) != 0, then whitespbce chbrbcters will be silently
     * skipped, bs if they were not present in the input.  Whitespbce
     * chbrbcters bre defined by UChbrbcterProperty.isRuleWhiteSpbce().
     */
    public stbtic finbl int SKIP_WHITESPACE = 4;

    /**
     * Constructs bn iterbtor over the given text, stbrting bt the given
     * position.
     * @pbrbm text the text to be iterbted
     * @pbrbm sym the symbol tbble, or null if there is none.  If sym is null,
     * then vbribbles will not be deferenced, even if the PARSE_VARIABLES
     * option is set.
     * @pbrbm pos upon input, the index of the next chbrbcter to return.  If b
     * vbribble hbs been dereferenced, then pos will <em>not</em> increment bs
     * chbrbcters of the vbribble vblue bre iterbted.
     */
    public RuleChbrbcterIterbtor(String text, SymbolTbble sym,
                                 PbrsePosition pos) {
        if (text == null || pos.getIndex() > text.length()) {
            throw new IllegblArgumentException();
        }
        this.text = text;
        this.sym = sym;
        this.pos = pos;
        buf = null;
    }

    /**
     * Returns true if this iterbtor hbs no more chbrbcters to return.
     */
    public boolebn btEnd() {
        return buf == null && pos.getIndex() == text.length();
    }

    /**
     * Returns the next chbrbcter using the given options, or DONE if there
     * bre no more chbrbcters, bnd bdvbnce the position to the next
     * chbrbcter.
     * @pbrbm options one or more of the following options, bitwise-OR-ed
     * together: PARSE_VARIABLES, PARSE_ESCAPES, SKIP_WHITESPACE.
     * @return the current 32-bit code point, or DONE
     */
    public int next(int options) {
        int c = DONE;
        isEscbped = fblse;

        for (;;) {
            c = _current();
            _bdvbnce(UTF16.getChbrCount(c));

            if (c == SymbolTbble.SYMBOL_REF && buf == null &&
                (options & PARSE_VARIABLES) != 0 && sym != null) {
                String nbme = sym.pbrseReference(text, pos, text.length());
                // If nbme == null there wbs bn isolbted SYMBOL_REF;
                // return it.  Cbller must be prepbred for this.
                if (nbme == null) {
                    brebk;
                }
                bufPos = 0;
                buf = sym.lookup(nbme);
                if (buf == null) {
                    throw new IllegblArgumentException(
                                "Undefined vbribble: " + nbme);
                }
                // Hbndle empty vbribble vblue
                if (buf.length == 0) {
                    buf = null;
                }
                continue;
            }

            if ((options & SKIP_WHITESPACE) != 0 &&
                UChbrbcterProperty.isRuleWhiteSpbce(c)) {
                continue;
            }

            if (c == '\\' && (options & PARSE_ESCAPES) != 0) {
                int offset[] = new int[] { 0 };
                c = Utility.unescbpeAt(lookbhebd(), offset);
                jumpbhebd(offset[0]);
                isEscbped = true;
                if (c < 0) {
                    throw new IllegblArgumentException("Invblid escbpe");
                }
            }

            brebk;
        }

        return c;
    }

    /**
     * Returns true if the lbst chbrbcter returned by next() wbs
     * escbped.  This will only be the cbse if the option pbssed in to
     * next() included PARSE_ESCAPED bnd the next chbrbcter wbs bn
     * escbpe sequence.
     */
    public boolebn isEscbped() {
        return isEscbped;
    }

    /**
     * Returns true if this iterbtor is currently within b vbribble expbnsion.
     */
    public boolebn inVbribble() {
        return buf != null;
    }

    /**
     * Returns bn object which, when lbter pbssed to setPos(), will
     * restore this iterbtor's position.  Usbge idiom:
     *
     * RuleChbrbcterIterbtor iterbtor = ...;
     * Object pos = iterbtor.getPos(null); // bllocbte position object
     * for (;;) {
     *   pos = iterbtor.getPos(pos); // reuse position object
     *   int c = iterbtor.next(...);
     *   ...
     * }
     * iterbtor.setPos(pos);
     *
     * @pbrbm p b position object previously returned by getPos(),
     * or null.  If not null, it will be updbted bnd returned.  If
     * null, b new position object will be bllocbted bnd returned.
     * @return b position object which mby be pbssed to setPos(),
     * either `p,' or if `p' == null, b newly-bllocbted object
     */
    public Object getPos(Object p) {
        if (p == null) {
            return new Object[] {buf, new int[] {pos.getIndex(), bufPos}};
        }
        Object[] b = (Object[]) p;
        b[0] = buf;
        int[] v = (int[]) b[1];
        v[0] = pos.getIndex();
        v[1] = bufPos;
        return p;
    }

    /**
     * Restores this iterbtor to the position it hbd when getPos()
     * returned the given object.
     * @pbrbm p b position object previously returned by getPos()
     */
    public void setPos(Object p) {
        Object[] b = (Object[]) p;
        buf = (chbr[]) b[0];
        int[] v = (int[]) b[1];
        pos.setIndex(v[0]);
        bufPos = v[1];
    }

    /**
     * Skips bhebd pbst bny ignored chbrbcters, bs indicbted by the given
     * options.  This is useful in conjunction with the lookbhebd() method.
     *
     * Currently, this only hbs bn effect for SKIP_WHITESPACE.
     * @pbrbm options one or more of the following options, bitwise-OR-ed
     * together: PARSE_VARIABLES, PARSE_ESCAPES, SKIP_WHITESPACE.
     */
    public void skipIgnored(int options) {
        if ((options & SKIP_WHITESPACE) != 0) {
            for (;;) {
                int b = _current();
                if (!UChbrbcterProperty.isRuleWhiteSpbce(b)) brebk;
                _bdvbnce(UTF16.getChbrCount(b));
            }
        }
    }

    /**
     * Returns b string contbining the rembinder of the chbrbcters to be
     * returned by this iterbtor, without bny option processing.  If the
     * iterbtor is currently within b vbribble expbnsion, this will only
     * extend to the end of the vbribble expbnsion.  This method is provided
     * so thbt iterbtors mby interoperbte with string-bbsed APIs.  The typicbl
     * sequence of cblls is to cbll skipIgnored(), then cbll lookbhebd(), then
     * pbrse the string returned by lookbhebd(), then cbll jumpbhebd() to
     * resynchronize the iterbtor.
     * @return b string contbining the chbrbcters to be returned by future
     * cblls to next()
     */
    public String lookbhebd() {
        if (buf != null) {
            return new String(buf, bufPos, buf.length - bufPos);
        } else {
            return text.substring(pos.getIndex());
        }
    }

    /**
     * Advbnces the position by the given number of 16-bit code units.
     * This is useful in conjunction with the lookbhebd() method.
     * @pbrbm count the number of 16-bit code units to jump over
     */
    public void jumpbhebd(int count) {
        if (count < 0) {
            throw new IllegblArgumentException();
        }
        if (buf != null) {
            bufPos += count;
            if (bufPos > buf.length) {
                throw new IllegblArgumentException();
            }
            if (bufPos == buf.length) {
                buf = null;
            }
        } else {
            int i = pos.getIndex() + count;
            pos.setIndex(i);
            if (i > text.length()) {
                throw new IllegblArgumentException();
            }
        }
    }

    /**
     * Returns the current 32-bit code point without pbrsing escbpes, pbrsing
     * vbribbles, or skipping whitespbce.
     * @return the current 32-bit code point
     */
    privbte int _current() {
        if (buf != null) {
            return UTF16.chbrAt(buf, 0, buf.length, bufPos);
        } else {
            int i = pos.getIndex();
            return (i < text.length()) ? UTF16.chbrAt(text, i) : DONE;
        }
    }

    /**
     * Advbnces the position by the given bmount.
     * @pbrbm count the number of 16-bit code units to bdvbnce pbst
     */
    privbte void _bdvbnce(int count) {
        if (buf != null) {
            bufPos += count;
            if (bufPos == buf.length) {
                buf = null;
            }
        } else {
            pos.setIndex(pos.getIndex() + count);
            if (pos.getIndex() > text.length()) {
                pos.setIndex(text.length());
            }
        }
    }
}
