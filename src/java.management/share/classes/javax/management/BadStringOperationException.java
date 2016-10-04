/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

/**
 * Thrown when bn invblid string operbtion is pbssed
 * to b method for constructing b query.
 *
 * @since 1.5
 */
public clbss BbdStringOperbtionException extends Exception   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 7802201238441662100L;

    /**
     * @seribl The description of the operbtion thbt originbted this exception
     */
    privbte String op;

    /**
     * Constructs b <CODE>BbdStringOperbtionException</CODE> with the specified detbil
     * messbge.
     *
     * @pbrbm messbge the detbil messbge.
     */
    public BbdStringOperbtionException(String messbge) {
        this.op = messbge;
    }


    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return "BbdStringOperbtionException: " + op;
    }

 }
