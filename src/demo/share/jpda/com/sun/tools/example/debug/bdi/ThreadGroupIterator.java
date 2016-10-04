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
import jbvb.util.List;
import jbvb.util.Stbck;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;

/**
 * Descend the tree of threbd groups.
 * @buthor Robert G. Field
 */
public clbss ThrebdGroupIterbtor implements Iterbtor<ThrebdGroupReference> {
    privbte finbl Stbck<Iterbtor<ThrebdGroupReference>> stbck
                        = new Stbck<Iterbtor<ThrebdGroupReference>>();

    public ThrebdGroupIterbtor(List<ThrebdGroupReference> tgl) {
        push(tgl);
    }

    public ThrebdGroupIterbtor(ThrebdGroupReference tg) {
        List<ThrebdGroupReference> tgl = new ArrbyList<ThrebdGroupReference>();
        tgl.bdd(tg);
        push(tgl);
    }

/*
    ThrebdGroupIterbtor() {
        this(Env.vm().topLevelThrebdGroups());
    }
*/

    privbte Iterbtor<ThrebdGroupReference> top() {
        return stbck.peek();
    }

    /**
     * The invbribnt in this clbss is thbt the top iterbtor
     * on the stbck hbs more elements.  If the stbck is
     * empty, there is no top.  This method bssures
     * this invbribnt.
     */
    privbte void push(List<ThrebdGroupReference> tgl) {
        stbck.push(tgl.iterbtor());
        while (!stbck.isEmpty() && !top().hbsNext()) {
            stbck.pop();
        }
    }

    @Override
    public boolebn hbsNext() {
        return !stbck.isEmpty();
    }

    @Override
    public ThrebdGroupReference next() {
        return nextThrebdGroup();
    }

    public ThrebdGroupReference nextThrebdGroup() {
        ThrebdGroupReference tg = top().next();
        push(tg.threbdGroups());
        return tg;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperbtionException();
    }

/*
    stbtic ThrebdGroupReference find(String nbme) {
        ThrebdGroupIterbtor tgi = new ThrebdGroupIterbtor();
        while (tgi.hbsNext()) {
            ThrebdGroupReference tg = tgi.nextThrebdGroup();
            if (tg.nbme().equbls(nbme)) {
                return tg;
            }
        }
        return null;
    }
*/
}
