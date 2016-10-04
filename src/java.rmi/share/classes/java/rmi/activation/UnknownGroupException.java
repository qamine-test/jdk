/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

/**
 * An <code>UnknownGroupException</code> is thrown by methods of clbsses bnd
 * interfbces in the <code>jbvb.rmi.bctivbtion</code> pbckbge when the
 * <code>ActivbtionGroupID</code> pbrbmeter to the method is determined to be
 * invblid, i.e., not known by the <code>ActivbtionSystem</code>.  An
 * <code>UnknownGroupException</code> is blso thrown if the
 * <code>ActivbtionGroupID</code> in bn <code>ActivbtionDesc</code> refers to
 * b group thbt is not registered with the <code>ActivbtionSystem</code>
 *
 * @buthor  Ann Wollrbth
 * @since   1.2
 * @see     jbvb.rmi.bctivbtion.Activbtbble
 * @see     jbvb.rmi.bctivbtion.ActivbtionGroup
 * @see     jbvb.rmi.bctivbtion.ActivbtionGroupID
 * @see     jbvb.rmi.bctivbtion.ActivbtionMonitor
 * @see     jbvb.rmi.bctivbtion.ActivbtionSystem
 */
public clbss UnknownGroupException extends ActivbtionException {

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = 7056094974750002460L;

    /**
     * Constructs bn <code>UnknownGroupException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.2
     */
    public UnknownGroupException(String s) {
        super(s);
    }
}
