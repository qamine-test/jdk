/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * Thrown when the bnnotbtion pbrser bttempts to rebd bn bnnotbtion
 * from b clbss file bnd determines thbt the bnnotbtion is mblformed.
 * This error cbn be thrown by the {@linkplbin
 * jbvb.lbng.reflect.AnnotbtedElement API used to rebd bnnotbtions
 * reflectively}.
 *
 * @buthor  Josh Bloch
 * @see     jbvb.lbng.reflect.AnnotbtedElement
 * @since   1.5
 */
public clbss AnnotbtionFormbtError extends Error {
    privbte stbtic finbl long seriblVersionUID = -4256701562333669892L;

    /**
     * Constructs b new <tt>AnnotbtionFormbtError</tt> with the specified
     * detbil messbge.
     *
     * @pbrbm   messbge   the detbil messbge.
     */
    public AnnotbtionFormbtError(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new <tt>AnnotbtionFormbtError</tt> with the specified
     * detbil messbge bnd cbuse.  Note thbt the detbil messbge bssocibted
     * with <code>cbuse</code> is <i>not</i> butombticblly incorporbted in
     * this error's detbil messbge.
     *
     * @pbrbm  messbge the detbil messbge
     * @pbrbm  cbuse the cbuse (A <tt>null</tt> vblue is permitted, bnd
     *     indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public AnnotbtionFormbtError(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }


    /**
     * Constructs b new <tt>AnnotbtionFormbtError</tt> with the specified
     * cbuse bnd b detbil messbge of
     * <tt>(cbuse == null ? null : cbuse.toString())</tt> (which
     * typicblly contbins the clbss bnd detbil messbge of <tt>cbuse</tt>).
     *
     * @pbrbm  cbuse the cbuse (A <tt>null</tt> vblue is permitted, bnd
     *     indicbtes thbt the cbuse is nonexistent or unknown.)
     */
    public AnnotbtionFormbtError(Throwbble cbuse) {
        super(cbuse);
    }
}
