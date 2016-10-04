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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.IOException;
import jbvb.io.Closebble;


/**
 * A nexus for I/O operbtions.
 *
 * <p> A chbnnel represents bn open connection to bn entity such bs b hbrdwbre
 * device, b file, b network socket, or b progrbm component thbt is cbpbble of
 * performing one or more distinct I/O operbtions, for exbmple rebding or
 * writing.
 *
 * <p> A chbnnel is either open or closed.  A chbnnel is open upon crebtion,
 * bnd once closed it rembins closed.  Once b chbnnel is closed, bny bttempt to
 * invoke bn I/O operbtion upon it will cbuse b {@link ClosedChbnnelException}
 * to be thrown.  Whether or not b chbnnel is open mby be tested by invoking
 * its {@link #isOpen isOpen} method.
 *
 * <p> Chbnnels bre, in generbl, intended to be sbfe for multithrebded bccess
 * bs described in the specificbtions of the interfbces bnd clbsses thbt extend
 * bnd implement this interfbce.
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public interfbce Chbnnel extends Closebble {

    /**
     * Tells whether or not this chbnnel is open.
     *
     * @return <tt>true</tt> if, bnd only if, this chbnnel is open
     */
    public boolebn isOpen();

    /**
     * Closes this chbnnel.
     *
     * <p> After b chbnnel is closed, bny further bttempt to invoke I/O
     * operbtions upon it will cbuse b {@link ClosedChbnnelException} to be
     * thrown.
     *
     * <p> If this chbnnel is blrebdy closed then invoking this method hbs no
     * effect.
     *
     * <p> This method mby be invoked bt bny time.  If some other threbd hbs
     * blrebdy invoked it, however, then bnother invocbtion will block until
     * the first invocbtion is complete, bfter which it will return without
     * effect. </p>
     *
     * @throws  IOException  If bn I/O error occurs
     */
    public void close() throws IOException;

}
