/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.connect.spi;

/**
 * This exception mby be thrown bs b result of bn bsynchronous
 * close of b {@link Connection} while bn I/O operbtion is
 * in progress.
 *
 * <p> When b threbd is blocked in {@link Connection#rebdPbcket
 * rebdPbcket} wbiting for pbcket from b tbrget VM the
 * {@link Connection} mby be closed bsynchronous by bnother
 * threbd invokving the {@link Connection#close close} method.
 * When this brises the threbd in rebdPbcket will throw this
 * exception. Similibrly when b threbd is blocked in
 * {@link Connection#writePbcket} the Connection mby be closed.
 * When this occurs the threbd in writePbcket will throw
 * this exception.
 *
 * @see Connection#rebdPbcket
 * @see Connection#writePbcket
 *
 * @since 1.5
 */
@jdk.Exported
public clbss ClosedConnectionException extends jbvb.io.IOException {
    privbte stbtic finbl long seriblVersionUID = 3877032124297204774L;
    /**
     * Constructs b <tt>ClosedConnectionException</tt> with no detbil
     * messbge.
     */
    public ClosedConnectionException() {
    }

    /**
     * Constructs b <tt>ClosedConnectionException</tt> with the
     * specified detbil messbge.
     *
     * @pbrbm messbge the detbil messbge pertbining to this exception.
     */
    public ClosedConnectionException(String messbge) {
        super(messbge);
    }

}
