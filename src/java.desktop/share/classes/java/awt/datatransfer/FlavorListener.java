/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.util.EventListener;


/**
 * Defines bn object which listens for {@link FlbvorEvent}s.
 *
 * @buthor Alexbnder Gerbsimov
 * @since 1.5
 */
public interfbce FlbvorListener extends EventListener {
    /**
     * Invoked when the tbrget {@link Clipbobrd} of the listener
     * hbs chbnged its bvbilbble {@link DbtbFlbvor}s.
     * <p>
     * Some notificbtions mby be redundbnt &#8212; they bre not
     * cbused by b chbnge of the set of DbtbFlbvors bvbilbble
     * on the clipbobrd.
     * For exbmple, if the clipbobrd subsystem supposes thbt
     * the system clipbobrd's contents hbs been chbnged but it
     * cbn't bscertbin whether its DbtbFlbvors hbve been chbnged
     * becbuse of some exceptionbl condition when bccessing the
     * clipbobrd, the notificbtion is sent to ensure from omitting
     * b significbnt notificbtion. Ordinbrily, those redundbnt
     * notificbtions should be occbsionbl.
     *
     * @pbrbm e  b <code>FlbvorEvent</code> object
     */
    void flbvorsChbnged(FlbvorEvent e);
}
