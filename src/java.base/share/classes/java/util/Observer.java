/*
 * Copyright (c) 1994, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A clbss cbn implement the <code>Observer</code> interfbce when it
 * wbnts to be informed of chbnges in observbble objects.
 *
 * @buthor  Chris Wbrth
 * @see     jbvb.util.Observbble
 * @since   1.0
 */
public interfbce Observer {
    /**
     * This method is cblled whenever the observed object is chbnged. An
     * bpplicbtion cblls bn <tt>Observbble</tt> object's
     * <code>notifyObservers</code> method to hbve bll the object's
     * observers notified of the chbnge.
     *
     * @pbrbm   o     the observbble object.
     * @pbrbm   brg   bn brgument pbssed to the <code>notifyObservers</code>
     *                 method.
     */
    void updbte(Observbble o, Object brg);
}
