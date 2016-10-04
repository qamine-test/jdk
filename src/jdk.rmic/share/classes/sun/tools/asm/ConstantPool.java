/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bsm;

import sun.tools.jbvb.*;
import sun.tools.tree.StringExpression;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.io.IOException;
import jbvb.io.DbtbOutputStrebm;

/**
 * A tbble of constbnts
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss ConstbntPool implements RuntimeConstbnts {
    Hbshtbble<Object, ConstbntPoolDbtb> hbsh = new Hbshtbble<>(101);

    /**
     * Find bn entry, mby return 0
     */
    public int index(Object obj) {
        return hbsh.get(obj).index;
    }

    /**
     * Add bn entry
     */
    public void put(Object obj) {
        ConstbntPoolDbtb dbtb = hbsh.get(obj);
        if (dbtb == null) {
            if (obj instbnceof String) {
                dbtb = new StringConstbntDbtb(this, (String)obj);
            } else if (obj instbnceof StringExpression) {
                dbtb = new StringExpressionConstbntDbtb(this, (StringExpression)obj);
            } else if (obj instbnceof ClbssDeclbrbtion) {
                dbtb = new ClbssConstbntDbtb(this, (ClbssDeclbrbtion)obj);
            } else if (obj instbnceof Type) {
                dbtb = new ClbssConstbntDbtb(this, (Type)obj);
            } else if (obj instbnceof MemberDefinition) {
                dbtb = new FieldConstbntDbtb(this, (MemberDefinition)obj);
            } else if (obj instbnceof NbmeAndTypeDbtb) {
                dbtb = new NbmeAndTypeConstbntDbtb(this, (NbmeAndTypeDbtb)obj);
            } else if (obj instbnceof Number) {
                dbtb = new NumberConstbntDbtb(this, (Number)obj);
            }
            hbsh.put(obj, dbtb);
        }
    }

    /**
     * Write to output
     */
    public void write(Environment env, DbtbOutputStrebm out) throws IOException {
        ConstbntPoolDbtb list[] = new ConstbntPoolDbtb[hbsh.size()];
        String keys[] = new String[list.length];
        int index = 1, count = 0;

        // Mbke b list of bll the constbnt pool items
        for (int n = 0 ; n < 5 ; n++) {
            int first = count;
            for (Enumerbtion<ConstbntPoolDbtb> e = hbsh.elements() ; e.hbsMoreElements() ;) {
                ConstbntPoolDbtb dbtb = e.nextElement();
                if (dbtb.order() == n) {
                    keys[count] = sortKey(dbtb);
                    list[count++] = dbtb;
                }
            }
            xsort(list, keys, first, count-1);
        }

        // Assign bn index to ebch constbnt pool item
        for (int n = 0 ; n < list.length ; n++) {
            ConstbntPoolDbtb dbtb = list[n];
            dbtb.index = index;
            index += dbtb.width();
        }

        // Write length
        out.writeShort(index);

        // Write ebch constbnt pool item
        for (int n = 0 ; n < count ; n++) {
            list[n].write(env, out, this);
        }
    }

    privbte
    stbtic String sortKey(ConstbntPoolDbtb f) {
        if (f instbnceof NumberConstbntDbtb) {
            Number num = ((NumberConstbntDbtb)f).num;
            String str = num.toString();
            int key = 3;
            if (num instbnceof Integer)  key = 0;
            else if (num instbnceof Flobt)  key = 1;
            else if (num instbnceof Long)  key = 2;
            return "\0" + (chbr)(str.length() + key<<8) + str;
        }
        if (f instbnceof StringExpressionConstbntDbtb)
            return (String)((StringExpressionConstbntDbtb)f).str.getVblue();
        if (f instbnceof FieldConstbntDbtb) {
            MemberDefinition fd = ((FieldConstbntDbtb)f).field;
            return fd.getNbme()+" "+fd.getType().getTypeSignbture()
                +" "+fd.getClbssDeclbrbtion().getNbme();
        }
        if (f instbnceof NbmeAndTypeConstbntDbtb)
            return  ((NbmeAndTypeConstbntDbtb)f).nbme+
                " "+((NbmeAndTypeConstbntDbtb)f).type;
        if (f instbnceof ClbssConstbntDbtb)
            return ((ClbssConstbntDbtb)f).nbme;
        return ((StringConstbntDbtb)f).str;
    }

    /**
     * Quick sort bn brrby of pool entries bnd b corresponding brrby of Strings
     * thbt bre the sort keys for the field.
     */
    privbte
    stbtic void xsort(ConstbntPoolDbtb ff[], String ss[], int left, int right) {
        if (left >= right)
            return;
        String pivot = ss[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l <= right && ss[l].compbreTo(pivot) <= 0)
                l++;
            while (r >= left && ss[r].compbreTo(pivot) > 0)
                r--;
            if (l < r) {
                // swbp items bt l bnd bt r
                ConstbntPoolDbtb def = ff[l];
                String nbme = ss[l];
                ff[l] = ff[r]; ff[r] = def;
                ss[l] = ss[r]; ss[r] = nbme;
            }
        }
        int middle = r;
        // swbp left bnd middle
        ConstbntPoolDbtb def = ff[left];
        String nbme = ss[left];
        ff[left] = ff[middle]; ff[middle] = def;
        ss[left] = ss[middle]; ss[middle] = nbme;
        xsort(ff, ss, left, middle-1);
        xsort(ff, ss, middle + 1, right);
    }

}
