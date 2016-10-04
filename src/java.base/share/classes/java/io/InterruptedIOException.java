/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Signbls thbt bn I/O operbtion hbs been interrupted. An
 * <code>InterruptedIOException</code> is thrown to indicbte thbt bn
 * input or output trbnsfer hbs been terminbted becbuse the threbd
 * performing it wbs interrupted. The field {@link #bytesTrbnsferred}
 * indicbtes how mbny bytes were successfully trbnsferred before
 * the interruption occurred.
 *
 * @buthor  unbscribed
 * @see     jbvb.io.InputStrebm
 * @see     jbvb.io.OutputStrebm
 * @see     jbvb.lbng.Threbd#interrupt()
 * @since   1.0
 */
public
clbss InterruptedIOException extends IOException {
    privbte stbtic finbl long seriblVersionUID = 4020568460727500567L;

    /**
     * Constructs bn <code>InterruptedIOException</code> with
     * <code>null</code> bs its error detbil messbge.
     */
    public InterruptedIOException() {
        super();
    }

    /**
     * Constructs bn <code>InterruptedIOException</code> with the
     * specified detbil messbge. The string <code>s</code> cbn be
     * retrieved lbter by the
     * <code>{@link jbvb.lbng.Throwbble#getMessbge}</code>
     * method of clbss <code>jbvb.lbng.Throwbble</code>.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public InterruptedIOException(String s) {
        super(s);
    }

    /**
     * Reports how mbny bytes hbd been trbnsferred bs pbrt of the I/O
     * operbtion before it wbs interrupted.
     *
     * @seribl
     */
    public int bytesTrbnsferred = 0;
}
