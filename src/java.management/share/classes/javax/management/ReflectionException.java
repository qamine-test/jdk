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
 * Represents exceptions thrown in the MBebn server when using the
 * jbvb.lbng.reflect clbsses to invoke methods on MBebns. It "wrbps" the
 * bctubl jbvb.lbng.Exception thrown.
 *
 * @since 1.5
 */
public clbss ReflectionException extends JMException   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 9170809325636915553L;

    /**
     * @seribl The wrbpped {@link Exception}
     */
    privbte jbvb.lbng.Exception exception ;


    /**
     * Crebtes b <CODE>ReflectionException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.Exception</CODE>.
     *
     * @pbrbm e the wrbpped exception.
     */
    public ReflectionException(jbvb.lbng.Exception e) {
        super() ;
        exception = e ;
    }

    /**
     * Crebtes b <CODE>ReflectionException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.Exception</CODE> with
     * b detbil messbge.
     *
     * @pbrbm e the wrbpped exception.
     * @pbrbm messbge the detbil messbge.
     */
    public ReflectionException(jbvb.lbng.Exception e, String messbge) {
        super(messbge) ;
        exception = e ;
    }

    /**
     * Returns the bctubl {@link Exception} thrown.
     *
     * @return the wrbpped {@link Exception}.
     */
    public jbvb.lbng.Exception getTbrgetException()  {
        return exception ;
    }

    /**
     * Returns the bctubl {@link Exception} thrown.
     *
     * @return the wrbpped {@link Exception}.
     */
    public Throwbble getCbuse() {
        return exception;
    }
}
