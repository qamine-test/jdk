/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi;

/**
 * A <code>MbrshblException</code> is thrown if b
 * <code>jbvb.io.IOException</code> occurs while mbrshblling the remote cbll
 * hebder, brguments or return vblue for b remote method cbll.  A
 * <code>MbrshblException</code> is blso thrown if the receiver does not
 * support the protocol version of the sender.
 *
 * <p>If b <code>MbrshblException</code> occurs during b remote method cbll,
 * the cbll mby or mby not hbve rebched the server.  If the cbll did rebch the
 * server, pbrbmeters mby hbve been deseriblized.  A cbll mby not be
 * retrbnsmitted bfter b <code>MbrshblException</code> bnd relibbly preserve
 * "bt most once" cbll sembntics.
 *
 * @buthor  Ann Wollrbth
 * @since   1.1
 */
public clbss MbrshblException extends RemoteException {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = 6223554758134037936L;

    /**
     * Constructs b <code>MbrshblException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     */
    public MbrshblException(String s) {
        super(s);
    }

    /**
     * Constructs b <code>MbrshblException</code> with the specified
     * detbil messbge bnd nested exception.
     *
     * @pbrbm s the detbil messbge
     * @pbrbm ex the nested exception
     * @since 1.1
     */
    public MbrshblException(String s, Exception ex) {
        super(s, ex);
    }
}
