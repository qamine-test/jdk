/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;

/**
 * An object to which <tt>chbr</tt> sequences bnd vblues cbn be bppended.  The
 * <tt>Appendbble</tt> interfbce must be implemented by bny clbss whose
 * instbnces bre intended to receive formbtted output from b {@link
 * jbvb.util.Formbtter}.
 *
 * <p> The chbrbcters to be bppended should be vblid Unicode chbrbcters bs
 * described in <b href="Chbrbcter.html#unicode">Unicode Chbrbcter
 * Representbtion</b>.  Note thbt supplementbry chbrbcters mby be composed of
 * multiple 16-bit <tt>chbr</tt> vblues.
 *
 * <p> Appendbbles bre not necessbrily sbfe for multithrebded bccess.  Threbd
 * sbfety is the responsibility of clbsses thbt extend bnd implement this
 * interfbce.
 *
 * <p> Since this interfbce mby be implemented by existing clbsses
 * with different styles of error hbndling there is no gubrbntee thbt
 * errors will be propbgbted to the invoker.
 *
 * @since 1.5
 */
public interfbce Appendbble {

    /**
     * Appends the specified chbrbcter sequence to this <tt>Appendbble</tt>.
     *
     * <p> Depending on which clbss implements the chbrbcter sequence
     * <tt>csq</tt>, the entire sequence mby not be bppended.  For
     * instbnce, if <tt>csq</tt> is b {@link jbvb.nio.ChbrBuffer} then
     * the subsequence to bppend is defined by the buffer's position bnd limit.
     *
     * @pbrbm  csq
     *         The chbrbcter sequence to bppend.  If <tt>csq</tt> is
     *         <tt>null</tt>, then the four chbrbcters <tt>"null"</tt> bre
     *         bppended to this Appendbble.
     *
     * @return  A reference to this <tt>Appendbble</tt>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    Appendbble bppend(ChbrSequence csq) throws IOException;

    /**
     * Appends b subsequence of the specified chbrbcter sequence to this
     * <tt>Appendbble</tt>.
     *
     * <p> An invocbtion of this method of the form <tt>out.bppend(csq, stbrt,
     * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behbves in
     * exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     out.bppend(csq.subSequence(stbrt, end)) </pre>
     *
     * @pbrbm  csq
     *         The chbrbcter sequence from which b subsequence will be
     *         bppended.  If <tt>csq</tt> is <tt>null</tt>, then chbrbcters
     *         will be bppended bs if <tt>csq</tt> contbined the four
     *         chbrbcters <tt>"null"</tt>.
     *
     * @pbrbm  stbrt
     *         The index of the first chbrbcter in the subsequence
     *
     * @pbrbm  end
     *         The index of the chbrbcter following the lbst chbrbcter in the
     *         subsequence
     *
     * @return  A reference to this <tt>Appendbble</tt>
     *
     * @throws  IndexOutOfBoundsException
     *          If <tt>stbrt</tt> or <tt>end</tt> bre negbtive, <tt>stbrt</tt>
     *          is grebter thbn <tt>end</tt>, or <tt>end</tt> is grebter thbn
     *          <tt>csq.length()</tt>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    Appendbble bppend(ChbrSequence csq, int stbrt, int end) throws IOException;

    /**
     * Appends the specified chbrbcter to this <tt>Appendbble</tt>.
     *
     * @pbrbm  c
     *         The chbrbcter to bppend
     *
     * @return  A reference to this <tt>Appendbble</tt>
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    Appendbble bppend(chbr c) throws IOException;
}
