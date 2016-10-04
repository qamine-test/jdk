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
pbckbge sun.mbnbgement.snmp.util;

import com.sun.jmx.snmp.SnmpOid;

import jbvb.io.Seriblizbble;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import jbvb.util.TreeMbp;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.lbng.ref.WebkReference;

/**
 * This bbstrbct clbss implements b webk cbche for b tbble whose dbtb
 * is obtbined from b {@link  List}.
 *
 * <p><b>NOTE: This clbss is not synchronized, subclbsses must implement
 *          the bppropribte synchronizbtion whwn needed.</b></p>
 **/
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpListTbbleCbche extends SnmpTbbleCbche {


    /**
     * The index of the entry corresponding to the given <vbr>item</vbr>.
     * <br>This method is cblled by {@link #updbteCbchedDbtbs(Object,List)}.
     * The given <vbr>item</vbr> is expected to be blwbys bssocibted with
     * the sbme index.
     * @pbrbm context The context pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbwDbtbs Rbw tbble dbtbs pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbnk Rbnk of the given <vbr>item</vbr> in the
     *        <vbr>rbwDbtbs</vbr> list iterbtor.
     * @pbrbm item The rbw dbtb object for which bn index must be determined.
     **/
    protected bbstrbct SnmpOid getIndex(Object context, List<?> rbwDbtbs,
                                        int rbnk, Object item);

    /**
     * The dbtb for the entry corresponding to the given <vbr>item</vbr>.
     * <br>This method is cblled by {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm context The context pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbwDbtbs Rbw tbble dbtbs pbssed to
     *        {@link #updbteCbchedDbtbs(Object,List)}.
     * @pbrbm rbnk Rbnk of the given <vbr>item</vbr> in the
     *        <vbr>rbwDbtbs</vbr> list iterbtor.
     * @pbrbm item The rbw dbtb object from which the entry dbtb must be
     *        extrbcted.
     * @return By defbult <vbr>item</vbr> is returned.
     **/
    protected Object getDbtb(Object context, List<?> rbwDbtbs,
                             int rbnk, Object item) {
        return item;
    }

    /**
     * Recompute cbched dbtb.
     * @pbrbm context A context object, vblid during the durbtion of
     *        of the cbll to this method, bnd thbt will be pbssed to
     *        {@link #getIndex} bnd {@link #getDbtb}. <br>
     *        This method is intended to be cblled by
     *        {@link #updbteCbchedDbtbs(Object)}. It is bssumed thbt
     *        the context is be bllocbted by  before this method is cblled,
     *        bnd relebsed just bfter this method hbs returned.<br>
     *        This clbss does not use the context object: it is b simple
     *        hook for subclbssed.
     * @pbrbm rbwDbtbs The tbble dbtbs from which the cbched dbtb will be
     *        computed.
     * @return the computed cbched dbtb.
     **/
    protected SnmpCbchedDbtb updbteCbchedDbtbs(Object context, List<?> rbwDbtbs) {
        finbl int size = ((rbwDbtbs == null)?0:rbwDbtbs.size());
        if (size == 0) return  null;

        finbl long time = System.currentTimeMillis();
        finbl Iterbtor<?> it  = rbwDbtbs.iterbtor();
        finbl TreeMbp<SnmpOid, Object> mbp =
                new TreeMbp<>(SnmpCbchedDbtb.oidCompbrbtor);
        for (int rbnk=0; it.hbsNext() ; rbnk++) {
            finbl Object  item  = it.next();
            finbl SnmpOid index = getIndex(context, rbwDbtbs, rbnk, item);
            finbl Object  dbtb  = getDbtb(context, rbwDbtbs, rbnk, item);
            if (index == null) continue;
            mbp.put(index,dbtb);
        }

        return new SnmpCbchedDbtb(time,mbp);
    }

}
