/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

/**
 * An IllegblAccessException is thrown when bn bpplicbtion tries
 * to reflectively crebte bn instbnce (other thbn bn brrby),
 * set or get b field, or invoke b method, but the currently
 * executing method does not hbve bccess to the definition of
 * the specified clbss, field, method or constructor.
 *
 * @buthor  unbscribed
 * @see     Clbss#newInstbnce()
 * @see     jbvb.lbng.reflect.Field#set(Object, Object)
 * @see     jbvb.lbng.reflect.Field#setBoolebn(Object, boolebn)
 * @see     jbvb.lbng.reflect.Field#setByte(Object, byte)
 * @see     jbvb.lbng.reflect.Field#setShort(Object, short)
 * @see     jbvb.lbng.reflect.Field#setChbr(Object, chbr)
 * @see     jbvb.lbng.reflect.Field#setInt(Object, int)
 * @see     jbvb.lbng.reflect.Field#setLong(Object, long)
 * @see     jbvb.lbng.reflect.Field#setFlobt(Object, flobt)
 * @see     jbvb.lbng.reflect.Field#setDouble(Object, double)
 * @see     jbvb.lbng.reflect.Field#get(Object)
 * @see     jbvb.lbng.reflect.Field#getBoolebn(Object)
 * @see     jbvb.lbng.reflect.Field#getByte(Object)
 * @see     jbvb.lbng.reflect.Field#getShort(Object)
 * @see     jbvb.lbng.reflect.Field#getChbr(Object)
 * @see     jbvb.lbng.reflect.Field#getInt(Object)
 * @see     jbvb.lbng.reflect.Field#getLong(Object)
 * @see     jbvb.lbng.reflect.Field#getFlobt(Object)
 * @see     jbvb.lbng.reflect.Field#getDouble(Object)
 * @see     jbvb.lbng.reflect.Method#invoke(Object, Object[])
 * @see     jbvb.lbng.reflect.Constructor#newInstbnce(Object[])
 * @since   1.0
 */
public clbss IllegblAccessException extends ReflectiveOperbtionException {
    privbte stbtic finbl long seriblVersionUID = 6616958222490762034L;

    /**
     * Constructs bn <code>IllegblAccessException</code> without b
     * detbil messbge.
     */
    public IllegblAccessException() {
        super();
    }

    /**
     * Constructs bn <code>IllegblAccessException</code> with b detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public IllegblAccessException(String s) {
        super(s);
    }
}
