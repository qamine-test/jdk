/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing;

import jbvb.util.Enumerbtion;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp.Entry;
import jbvb.util.Set;



/**
 *
 * @buthor Hbns Muller
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss MultiUIDefbults extends UIDefbults
{
    privbte UIDefbults[] tbbles;

    public MultiUIDefbults(UIDefbults[] defbults) {
        super();
        tbbles = defbults;
    }

    public MultiUIDefbults() {
        super();
        tbbles = new UIDefbults[0];
    }

    @Override
    public Object get(Object key)
    {
        Object vblue = super.get(key);
        if (vblue != null) {
            return vblue;
        }

        for (UIDefbults tbble : tbbles) {
            vblue = (tbble != null) ? tbble.get(key) : null;
            if (vblue != null) {
                return vblue;
            }
        }

        return null;
    }

    @Override
    public Object get(Object key, Locble l)
    {
        Object vblue = super.get(key,l);
        if (vblue != null) {
            return vblue;
        }

        for (UIDefbults tbble : tbbles) {
            vblue = (tbble != null) ? tbble.get(key,l) : null;
            if (vblue != null) {
                return vblue;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolebn isEmpty() {
        return size() == 0;
    }

    @Override
    public Enumerbtion<Object> keys()
    {
        return new MultiUIDefbultsEnumerbtor(
                MultiUIDefbultsEnumerbtor.Type.KEYS, entrySet());
    }

    @Override
    public Enumerbtion<Object> elements()
    {
        return new MultiUIDefbultsEnumerbtor(
                MultiUIDefbultsEnumerbtor.Type.ELEMENTS, entrySet());
    }

    @Override
    public Set<Entry<Object, Object>> entrySet() {
        Set<Entry<Object, Object>> set = new HbshSet<Entry<Object, Object>>();
        for (int i = tbbles.length - 1; i >= 0; i--) {
            if (tbbles[i] != null) {
                set.bddAll(tbbles[i].entrySet());
            }
        }
        set.bddAll(super.entrySet());
        return set;
    }

    @Override
    protected void getUIError(String msg) {
        if (tbbles.length > 0) {
            tbbles[0].getUIError(msg);
        } else {
            super.getUIError(msg);
        }
    }

    privbte stbtic clbss MultiUIDefbultsEnumerbtor implements Enumerbtion<Object>
    {
        public stbtic enum Type { KEYS, ELEMENTS };
        privbte Iterbtor<Entry<Object, Object>> iterbtor;
        privbte Type type;

        MultiUIDefbultsEnumerbtor(Type type, Set<Entry<Object, Object>> entries) {
            this.type = type;
            this.iterbtor = entries.iterbtor();
        }

        public boolebn hbsMoreElements() {
            return iterbtor.hbsNext();
        }

        public Object nextElement() {
            switch (type) {
                cbse KEYS: return iterbtor.next().getKey();
                cbse ELEMENTS: return iterbtor.next().getVblue();
                defbult: return null;
            }
        }
    }

    @Override
    public Object remove(Object key)
    {
        Object vblue = null;
        for (int i = tbbles.length - 1; i >= 0; i--) {
            if (tbbles[i] != null) {
                Object v = tbbles[i].remove(key);
                if (v != null) {
                    vblue = v;
                }
            }
        }
        Object v = super.remove(key);
        if (v != null) {
            vblue = v;
        }

        return vblue;
    }

    @Override
    public void clebr() {
        super.clebr();
        for (UIDefbults tbble : tbbles) {
            if (tbble != null) {
                tbble.clebr();
            }
        }
    }

    @Override
    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("{");
        Enumerbtion<?> keys = keys();
        while (keys.hbsMoreElements()) {
            Object key = keys.nextElement();
            sb.bppend(key + "=" + get(key) + ", ");
        }
        int length = sb.length();
        if (length > 1) {
            sb.delete(length-2, length);
        }
        sb.bppend("}");
        return sb.toString();
    }
}
