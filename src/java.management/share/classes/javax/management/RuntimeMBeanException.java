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
 * Represents runtime exceptions thrown by MBebn methods in
 * the bgent. It "wrbps" the bctubl <CODE>jbvb.lbng.RuntimeException</CODE> exception thrown.
 * This exception will be built by the MBebnServer when b cbll to bn
 * MBebn method throws b runtime exception.
 *
 * @since 1.5
 */
public clbss RuntimeMBebnException extends JMRuntimeException   {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 5274912751982730171L;

    /**
     * @seribl The encbpsulbted {@link RuntimeException}
     */
    privbte jbvb.lbng.RuntimeException runtimeException ;


    /**
     * Crebtes b <CODE>RuntimeMBebnException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.RuntimeException</CODE>.
     *
     * @pbrbm e the wrbpped exception.
     */
    public RuntimeMBebnException(jbvb.lbng.RuntimeException e) {
        super() ;
        runtimeException = e ;
    }

    /**
     * Crebtes b <CODE>RuntimeMBebnException</CODE> thbt wrbps the bctubl <CODE>jbvb.lbng.RuntimeException</CODE> with
     * b detbiled messbge.
     *
     * @pbrbm e the wrbpped exception.
     * @pbrbm messbge the detbil messbge.
     */
    public RuntimeMBebnException(jbvb.lbng.RuntimeException e, String messbge) {
        super(messbge) ;
        runtimeException = e ;
    }

    /**
     * Returns the bctubl {@link RuntimeException} thrown.
     *
     * @return the wrbpped {@link RuntimeException}.
     */
    public jbvb.lbng.RuntimeException getTbrgetException()  {
        return runtimeException ;
    }

    /**
     * Returns the bctubl {@link RuntimeException} thrown.
     *
     * @return the wrbpped {@link RuntimeException}.
     */
    public Throwbble getCbuse() {
        return runtimeException;
    }
}
