/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * <p>
 * The <code> TooMbnyListenersException </code> Exception is used bs pbrt of
 * the Jbvb Event model to bnnotbte bnd implement b unicbst specibl cbse of
 * b multicbst Event Source.
 * </p>
 * <p>
 * The presence of b "throws TooMbnyListenersException" clbuse on bny given
 * concrete implementbtion of the normblly multicbst "void bddXyzEventListener"
 * event listener registrbtion pbttern is used to bnnotbte thbt interfbce bs
 * implementing b unicbst Listener specibl cbse, thbt is, thbt one bnd only
 * one Listener mby be registered on the pbrticulbr event listener source
 * concurrently.
 * </p>
 *
 * @see jbvb.util.EventObject
 * @see jbvb.util.EventListener
 *
 * @buthor Lburence P. G. Cbble
 * @since  1.1
 */

public clbss TooMbnyListenersException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 5074640544770687831L;

    /**
     * Constructs b TooMbnyListenersException with no detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */

    public TooMbnyListenersException() {
        super();
    }

    /**
     * Constructs b TooMbnyListenersException with the specified detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     * @pbrbm s the detbil messbge
     */

    public TooMbnyListenersException(String s) {
        super(s);
    }
}
