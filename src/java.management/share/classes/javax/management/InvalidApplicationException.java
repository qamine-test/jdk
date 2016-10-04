/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown when bn bttempt is mbde to bpply either of the following: A
 * subquery expression to bn MBebn or b qublified bttribute expression
 * to bn MBebn of the wrong clbss.  This exception is used internblly
 * by JMX during the evblubtion of b query.  User code does not
 * usublly see it.
 *
 * @since 1.5
 */
public clbss InvblidApplicbtionException extends Exception   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -3048022274675537269L;

    /**
     * @seribl The object representing the clbss of the MBebn
     */
    privbte Object vbl;


    /**
     * Constructs bn <CODE>InvblidApplicbtionException</CODE> with the specified <CODE>Object</CODE>.
     *
     * @pbrbm vbl the detbil messbge of this exception.
     */
    public InvblidApplicbtionException(Object vbl) {
        this.vbl = vbl;
    }
}
