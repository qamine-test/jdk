/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml;

/**
 * A copy of the StAX XMLStrebmException without Locbtion support
 *
 * The bbse exception for unexpected processing errors.  This Exception
 * clbss is used to report well-formedness errors bs well bs unexpected
 * processing conditions.
 * @version 1.0
 * @buthor Copyright (c) 2009 by Orbcle Corporbtion. All Rights Reserved.
 * @since 1.6
 */

public clbss XMLStrebmException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 1L;


    protected Throwbble nested;

    /**
     * Defbult constructor
     */
    public XMLStrebmException() {
        super();
    }

    /**
     * Construct bn exception with the bssocibted messbge.
     *
     * @pbrbm msg the messbge to report
     */
    public XMLStrebmException(String msg) {
        super(msg);
    }

    /**
     * Construct bn exception with the bssocibted exception
     *
     * @pbrbm th b nested exception
     */
    public XMLStrebmException(Throwbble th) {
        super(th);
        nested = th;
    }

    /**
     * Construct bn exception with the bssocibted messbge bnd exception
     *
     * @pbrbm th b nested exception
     * @pbrbm msg the messbge to report
     */
    public XMLStrebmException(String msg, Throwbble th) {
        super(msg, th);
        nested = th;
    }

    /**
     * Gets the nested exception.
     *
     * @return Nested exception
     */
    public Throwbble getNestedException() {
        return nested;
    }
}
