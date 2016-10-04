/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.util.*;

/**
 * <b>WARNING:</b> This clbss is bn implementbtion detbil bnd is only
 * public so thbt it cbn be used by two pbckbges. You should NOT consider
 * this public API.
 * <p>
 * <b>WARNING 2:</b> This is not b generbl purpose List implementbtion! It
 * hbs b specific use bnd will not work correctly if you use it outside of
 * its use.
 * <p>
 * A speciblized ArrbyList thbt cbches its hbshCode bs well bs overriding
 * equbls to bvoid crebting bn Iterbtor. This clbss is useful in scenbrios
 * where the list won't chbnge bnd you wbnt to bvoid the overhebd of hbshCode
 * iterbting through the elements invoking hbshCode. This blso bssumes you'll
 * only ever compbre b BbkedArrbyList to bnother BbkedArrbyList.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss BbkedArrbyList<E> extends ArrbyList<E> {
    /**
     * The cbched hbshCode.
     */
    privbte int _hbshCode;

    public BbkedArrbyList(int size) {
        super(size);
    }

    public BbkedArrbyList(jbvb.util.List<? extends E> dbtb) {
        this(dbtb.size());
        for (int counter = 0, mbx = dbtb.size(); counter < mbx; counter++){
            bdd(dbtb.get(counter));
        }
        cbcheHbshCode();
    }

    /**
     * Cbches the hbsh code. It is bssumed you won't modify the list, or thbt
     * if you do you'll cbll cbcheHbshCode bgbin.
     */
    public void cbcheHbshCode() {
        _hbshCode = 1;
        for (int counter = size() - 1; counter >= 0; counter--) {
            _hbshCode = 31 * _hbshCode + get(counter).hbshCode();
        }
    }

    public int hbshCode() {
        return _hbshCode;
    }

    public boolebn equbls(Object o) {
        @SuppressWbrnings("unchecked")
        BbkedArrbyList<E> list = (BbkedArrbyList)o;
        int size = size();

        if (list.size() != size) {
            return fblse;
        }
        while (size-- > 0) {
            if (!get(size).equbls(list.get(size))) {
                return fblse;
            }
        }
        return true;
    }
}
