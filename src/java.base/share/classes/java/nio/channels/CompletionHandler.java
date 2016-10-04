/*
 * Copyright (c) 2007, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

/**
 * A hbndler for consuming the result of bn bsynchronous I/O operbtion.
 *
 * <p> The bsynchronous chbnnels defined in this pbckbge bllow b completion
 * hbndler to be specified to consume the result of bn bsynchronous operbtion.
 * The {@link #completed completed} method is invoked when the I/O operbtion
 * completes successfully. The {@link #fbiled fbiled} method is invoked if the
 * I/O operbtions fbils. The implementbtions of these methods should complete
 * in b timely mbnner so bs to bvoid keeping the invoking threbd from dispbtching
 * to other completion hbndlers.
 *
 * @pbrbm   <V>     The result type of the I/O operbtion
 * @pbrbm   <A>     The type of the object bttbched to the I/O operbtion
 *
 * @since 1.7
 */

public interfbce CompletionHbndler<V,A> {

    /**
     * Invoked when bn operbtion hbs completed.
     *
     * @pbrbm   result
     *          The result of the I/O operbtion.
     * @pbrbm   bttbchment
     *          The object bttbched to the I/O operbtion when it wbs initibted.
     */
    void completed(V result, A bttbchment);

    /**
     * Invoked when bn operbtion fbils.
     *
     * @pbrbm   exc
     *          The exception to indicbte why the I/O operbtion fbiled
     * @pbrbm   bttbchment
     *          The object bttbched to the I/O operbtion when it wbs initibted.
     */
    void fbiled(Throwbble exc, A bttbchment);
}
