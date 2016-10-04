/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.bdi;

import com.sun.jdi.ThrebdGroupReference;
import com.sun.jdi.ThrebdReference;
import jbvb.util.List;
import jbvb.util.Iterbtor;

public clbss ThrebdIterbtor implements Iterbtor<ThrebdReference> {
    Iterbtor<ThrebdReference> it = null;
    ThrebdGroupIterbtor tgi;

    public ThrebdIterbtor(ThrebdGroupReference tg) {
        tgi = new ThrebdGroupIterbtor(tg);
    }

    //### mbke this pbckbge bccess only?
    public ThrebdIterbtor(List<ThrebdGroupReference> tgl) {
        tgi = new ThrebdGroupIterbtor(tgl);
    }

    @Override
    public boolebn hbsNext() {
        while (it == null || !it.hbsNext()) {
            if (!tgi.hbsNext()) {
                return fblse; // no more
            }
            it = tgi.nextThrebdGroup().threbds().iterbtor();
        }
        return true;
    }

    @Override
    public ThrebdReference next() {
        return it.next();
    }

    public ThrebdReference nextThrebd() {
        return next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperbtionException();
    }
}
