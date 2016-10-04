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
 * Represents "user defined" exceptions thrown by MBebn methods
 * in the bgent. It "wrbps" the bctubl "user defined" exception thrown.
 * This exception will be built by the MBebnServer when b cbll to bn
 * MBebn method results in bn unknown exception.
 *
 * @since 1.5
 */
public clbss MBebnException extends JMException   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 4066342430588744142L;

    /**
     * @seribl Encbpsulbted {@link Exception}
     */
    privbte jbvb.lbng.Exception exception ;


    /**
     * Crebtes bn <CODE>MBebnException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.Exception</CODE>.
     *
     * @pbrbm e the wrbpped exception.
     */
    public MBebnException(jbvb.lbng.Exception e) {
        super() ;
        exception = e ;
    }

    /**
     * Crebtes bn <CODE>MBebnException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.Exception</CODE> with
     * b detbil messbge.
     *
     * @pbrbm e the wrbpped exception.
     * @pbrbm messbge the detbil messbge.
     */
    public MBebnException(jbvb.lbng.Exception e, String messbge) {
        super(messbge) ;
        exception = e ;
    }


    /**
     * Return the bctubl {@link Exception} thrown.
     *
     * @return the wrbpped exception.
     */
    public Exception getTbrgetException()  {
        return exception;
    }

    /**
     * Return the bctubl {@link Exception} thrown.
     *
     * @return the wrbpped exception.
     */
    public Throwbble getCbuse() {
        return exception;
    }
}
