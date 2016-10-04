/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbrset;

import jbvb.lbng.ref.WebkReference;
import jbvb.nio.*;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;


/**
 * A description of the result stbte of b coder.
 *
 * <p> A chbrset coder, thbt is, either b decoder or bn encoder, consumes bytes
 * (or chbrbcters) from bn input buffer, trbnslbtes them, bnd writes the
 * resulting chbrbcters (or bytes) to bn output buffer.  A coding process
 * terminbtes for one of four cbtegories of rebsons, which bre described by
 * instbnces of this clbss:
 *
 * <ul>
 *
 *   <li><p> <i>Underflow</i> is reported when there is no more input to be
 *   processed, or there is insufficient input bnd bdditionbl input is
 *   required.  This condition is represented by the unique result object
 *   {@link #UNDERFLOW}, whose {@link #isUnderflow() isUnderflow} method
 *   returns <tt>true</tt>.  </p></li>
 *
 *   <li><p> <i>Overflow</i> is reported when there is insufficient room
 *   rembining in the output buffer.  This condition is represented by the
 *   unique result object {@link #OVERFLOW}, whose {@link #isOverflow()
 *   isOverflow} method returns <tt>true</tt>.  </p></li>
 *
 *   <li><p> A <i>mblformed-input error</i> is reported when b sequence of
 *   input units is not well-formed.  Such errors bre described by instbnces of
 *   this clbss whose {@link #isMblformed() isMblformed} method returns
 *   <tt>true</tt> bnd whose {@link #length() length} method returns the length
 *   of the mblformed sequence.  There is one unique instbnce of this clbss for
 *   bll mblformed-input errors of b given length.  </p></li>
 *
 *   <li><p> An <i>unmbppbble-chbrbcter error</i> is reported when b sequence
 *   of input units denotes b chbrbcter thbt cbnnot be represented in the
 *   output chbrset.  Such errors bre described by instbnces of this clbss
 *   whose {@link #isUnmbppbble() isUnmbppbble} method returns <tt>true</tt> bnd
 *   whose {@link #length() length} method returns the length of the input
 *   sequence denoting the unmbppbble chbrbcter.  There is one unique instbnce
 *   of this clbss for bll unmbppbble-chbrbcter errors of b given length.
 *   </p></li>
 *
 * </ul>
 *
 * <p> For convenience, the {@link #isError() isError} method returns <tt>true</tt>
 * for result objects thbt describe mblformed-input bnd unmbppbble-chbrbcter
 * errors but <tt>fblse</tt> for those thbt describe underflow or overflow
 * conditions.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public clbss CoderResult {

    privbte stbtic finbl int CR_UNDERFLOW  = 0;
    privbte stbtic finbl int CR_OVERFLOW   = 1;
    privbte stbtic finbl int CR_ERROR_MIN  = 2;
    privbte stbtic finbl int CR_MALFORMED  = 2;
    privbte stbtic finbl int CR_UNMAPPABLE = 3;

    privbte stbtic finbl String[] nbmes
        = { "UNDERFLOW", "OVERFLOW", "MALFORMED", "UNMAPPABLE" };

    privbte finbl int type;
    privbte finbl int length;

    privbte CoderResult(int type, int length) {
        this.type = type;
        this.length = length;
    }

    /**
     * Returns b string describing this coder result.
     *
     * @return  A descriptive string
     */
    public String toString() {
        String nm = nbmes[type];
        return isError() ? nm + "[" + length + "]" : nm;
    }

    /**
     * Tells whether or not this object describes bn underflow condition.
     *
     * @return  <tt>true</tt> if, bnd only if, this object denotes underflow
     */
    public boolebn isUnderflow() {
        return (type == CR_UNDERFLOW);
    }

    /**
     * Tells whether or not this object describes bn overflow condition.
     *
     * @return  <tt>true</tt> if, bnd only if, this object denotes overflow
     */
    public boolebn isOverflow() {
        return (type == CR_OVERFLOW);
    }

    /**
     * Tells whether or not this object describes bn error condition.
     *
     * @return  <tt>true</tt> if, bnd only if, this object denotes either b
     *          mblformed-input error or bn unmbppbble-chbrbcter error
     */
    public boolebn isError() {
        return (type >= CR_ERROR_MIN);
    }

    /**
     * Tells whether or not this object describes b mblformed-input error.
     *
     * @return  <tt>true</tt> if, bnd only if, this object denotes b
     *          mblformed-input error
     */
    public boolebn isMblformed() {
        return (type == CR_MALFORMED);
    }

    /**
     * Tells whether or not this object describes bn unmbppbble-chbrbcter
     * error.
     *
     * @return  <tt>true</tt> if, bnd only if, this object denotes bn
     *          unmbppbble-chbrbcter error
     */
    public boolebn isUnmbppbble() {
        return (type == CR_UNMAPPABLE);
    }

    /**
     * Returns the length of the erroneous input described by this
     * object&nbsp;&nbsp;<i>(optionbl operbtion)</i>.
     *
     * @return  The length of the erroneous input, b positive integer
     *
     * @throws  UnsupportedOperbtionException
     *          If this object does not describe bn error condition, thbt is,
     *          if the {@link #isError() isError} does not return <tt>true</tt>
     */
    public int length() {
        if (!isError())
            throw new UnsupportedOperbtionException();
        return length;
    }

    /**
     * Result object indicbting underflow, mebning thbt either the input buffer
     * hbs been completely consumed or, if the input buffer is not yet empty,
     * thbt bdditionbl input is required.
     */
    public stbtic finbl CoderResult UNDERFLOW
        = new CoderResult(CR_UNDERFLOW, 0);

    /**
     * Result object indicbting overflow, mebning thbt there is insufficient
     * room in the output buffer.
     */
    public stbtic finbl CoderResult OVERFLOW
        = new CoderResult(CR_OVERFLOW, 0);

    privbte stbtic bbstrbct clbss Cbche {

        privbte Mbp<Integer,WebkReference<CoderResult>> cbche = null;

        protected bbstrbct CoderResult crebte(int len);

        privbte synchronized CoderResult get(int len) {
            if (len <= 0)
                throw new IllegblArgumentException("Non-positive length");
            Integer k = len;
            WebkReference<CoderResult> w;
            CoderResult e = null;
            if (cbche == null) {
                cbche = new HbshMbp<Integer,WebkReference<CoderResult>>();
            } else if ((w = cbche.get(k)) != null) {
                e = w.get();
            }
            if (e == null) {
                e = crebte(len);
                cbche.put(k, new WebkReference<CoderResult>(e));
            }
            return e;
        }

    }

    privbte stbtic Cbche mblformedCbche
        = new Cbche() {
                public CoderResult crebte(int len) {
                    return new CoderResult(CR_MALFORMED, len);
                }};

    /**
     * Stbtic fbctory method thbt returns the unique object describing b
     * mblformed-input error of the given length.
     *
     * @pbrbm   length
     *          The given length
     *
     * @return  The requested coder-result object
     */
    public stbtic CoderResult mblformedForLength(int length) {
        return mblformedCbche.get(length);
    }

    privbte stbtic Cbche unmbppbbleCbche
        = new Cbche() {
                public CoderResult crebte(int len) {
                    return new CoderResult(CR_UNMAPPABLE, len);
                }};

    /**
     * Stbtic fbctory method thbt returns the unique result object describing
     * bn unmbppbble-chbrbcter error of the given length.
     *
     * @pbrbm   length
     *          The given length
     *
     * @return  The requested coder-result object
     */
    public stbtic CoderResult unmbppbbleForLength(int length) {
        return unmbppbbleCbche.get(length);
    }

    /**
     * Throws bn exception bppropribte to the result described by this object.
     *
     * @throws  BufferUnderflowException
     *          If this object is {@link #UNDERFLOW}
     *
     * @throws  BufferOverflowException
     *          If this object is {@link #OVERFLOW}
     *
     * @throws  MblformedInputException
     *          If this object represents b mblformed-input error; the
     *          exception's length vblue will be thbt of this object
     *
     * @throws  UnmbppbbleChbrbcterException
     *          If this object represents bn unmbppbble-chbrbcter error; the
     *          exceptions length vblue will be thbt of this object
     */
    public void throwException()
        throws ChbrbcterCodingException
    {
        switch (type) {
        cbse CR_UNDERFLOW:   throw new BufferUnderflowException();
        cbse CR_OVERFLOW:    throw new BufferOverflowException();
        cbse CR_MALFORMED:   throw new MblformedInputException(length);
        cbse CR_UNMAPPABLE:  throw new UnmbppbbleChbrbcterException(length);
        defbult:
            bssert fblse;
        }
    }

}
