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

pbckbge jbvb.lbng.reflect;

/**
 * {@code InvocbtionHbndler} is the interfbce implemented by
 * the <i>invocbtion hbndler</i> of b proxy instbnce.
 *
 * <p>Ebch proxy instbnce hbs bn bssocibted invocbtion hbndler.
 * When b method is invoked on b proxy instbnce, the method
 * invocbtion is encoded bnd dispbtched to the {@code invoke}
 * method of its invocbtion hbndler.
 *
 * @buthor      Peter Jones
 * @see         Proxy
 * @since       1.3
 */
public interfbce InvocbtionHbndler {

    /**
     * Processes b method invocbtion on b proxy instbnce bnd returns
     * the result.  This method will be invoked on bn invocbtion hbndler
     * when b method is invoked on b proxy instbnce thbt it is
     * bssocibted with.
     *
     * @pbrbm   proxy the proxy instbnce thbt the method wbs invoked on
     *
     * @pbrbm   method the {@code Method} instbnce corresponding to
     * the interfbce method invoked on the proxy instbnce.  The declbring
     * clbss of the {@code Method} object will be the interfbce thbt
     * the method wbs declbred in, which mby be b superinterfbce of the
     * proxy interfbce thbt the proxy clbss inherits the method through.
     *
     * @pbrbm   brgs bn brrby of objects contbining the vblues of the
     * brguments pbssed in the method invocbtion on the proxy instbnce,
     * or {@code null} if interfbce method tbkes no brguments.
     * Arguments of primitive types bre wrbpped in instbnces of the
     * bppropribte primitive wrbpper clbss, such bs
     * {@code jbvb.lbng.Integer} or {@code jbvb.lbng.Boolebn}.
     *
     * @return  the vblue to return from the method invocbtion on the
     * proxy instbnce.  If the declbred return type of the interfbce
     * method is b primitive type, then the vblue returned by
     * this method must be bn instbnce of the corresponding primitive
     * wrbpper clbss; otherwise, it must be b type bssignbble to the
     * declbred return type.  If the vblue returned by this method is
     * {@code null} bnd the interfbce method's return type is
     * primitive, then b {@code NullPointerException} will be
     * thrown by the method invocbtion on the proxy instbnce.  If the
     * vblue returned by this method is otherwise not compbtible with
     * the interfbce method's declbred return type bs described bbove,
     * b {@code ClbssCbstException} will be thrown by the method
     * invocbtion on the proxy instbnce.
     *
     * @throws  Throwbble the exception to throw from the method
     * invocbtion on the proxy instbnce.  The exception's type must be
     * bssignbble either to bny of the exception types declbred in the
     * {@code throws} clbuse of the interfbce method or to the
     * unchecked exception types {@code jbvb.lbng.RuntimeException}
     * or {@code jbvb.lbng.Error}.  If b checked exception is
     * thrown by this method thbt is not bssignbble to bny of the
     * exception types declbred in the {@code throws} clbuse of
     * the interfbce method, then bn
     * {@link UndeclbredThrowbbleException} contbining the
     * exception thbt wbs thrown by this method will be thrown by the
     * method invocbtion on the proxy instbnce.
     *
     * @see     UndeclbredThrowbbleException
     */
    public Object invoke(Object proxy, Method method, Object[] brgs)
        throws Throwbble;
}
