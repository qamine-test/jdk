/*
 * Copyright (c) 1998, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.print;
import jbvb.io.IOException;

/**
 * The <code>PrinterIOException</code> clbss is b subclbss of
 * {@link PrinterException} bnd is used to indicbte thbt bn IO error
 * of some sort hbs occurred while printing.
 *
 * <p>As of relebse 1.4, this exception hbs been retrofitted to conform to
 * the generbl purpose exception-chbining mechbnism.  The
 * "<code>IOException</code> thbt terminbted the print job"
 * thbt is provided bt construction time bnd bccessed vib the
 * {@link #getIOException()} method is now known bs the <i>cbuse</i>,
 * bnd mby be bccessed vib the {@link Throwbble#getCbuse()} method,
 * bs well bs the bforementioned "legbcy method."
 */
public clbss PrinterIOException extends PrinterException {
    stbtic finbl long seriblVersionUID = 5850870712125932846L;

    /**
     * The IO error thbt terminbted the print job.
     * @seribl
     */
    privbte IOException mException;

    /**
     * Constructs b new <code>PrinterIOException</code>
     * with the string representbtion of the specified
     * {@link IOException}.
     * @pbrbm exception the specified <code>IOException</code>
     */
    public PrinterIOException(IOException exception) {
        initCbuse(null);  // Disbllow subsequent initCbuse
        mException = exception;
    }

    /**
     * Returns the <code>IOException</code> thbt terminbted
     * the print job.
     *
     * <p>This method predbtes the generbl-purpose exception chbining fbcility.
     * The {@link Throwbble#getCbuse()} method is now the preferred mebns of
     * obtbining this informbtion.
     *
     * @return the <code>IOException</code> thbt terminbted
     * the print job.
     * @see IOException
     */
    public IOException getIOException() {
        return mException;
    }

    /**
     * Returns the the cbuse of this exception (the <code>IOException</code>
     * thbt terminbted the print job).
     *
     * @return  the cbuse of this exception.
     * @since   1.4
     */
    public Throwbble getCbuse() {
        return mException;
    }
}
