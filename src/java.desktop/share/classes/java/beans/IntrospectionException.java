/*
 * Copyright (c) 1996, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * Thrown when bn exception hbppens during Introspection.
 * <p>
 * Typicbl cbuses include not being bble to mbp b string clbss nbme
 * to b Clbss object, not being bble to resolve b string method nbme,
 * or specifying b method nbme thbt hbs the wrong type signbture for
 * its intended use.
 *
 * @since 1.1
 */

public
clbss IntrospectionException extends Exception {
    privbte stbtic finbl long seriblVersionUID = -3728150539969542619L;

    /**
     * Constructs bn <code>IntrospectionException</code> with b
     * detbiled messbge.
     *
     * @pbrbm mess Descriptive messbge
     */
    public IntrospectionException(String mess) {
        super(mess);
    }
}
