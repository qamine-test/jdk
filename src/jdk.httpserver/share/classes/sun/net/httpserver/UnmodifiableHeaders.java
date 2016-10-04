/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.httpserver;

import jbvb.util.*;
import com.sun.net.httpserver.*;

clbss UnmodifibbleHebders extends Hebders {

        Hebders mbp;

        UnmodifibbleHebders(Hebders mbp) {
            this.mbp = mbp;
        }

        public int size() {return mbp.size();}

        public boolebn isEmpty() {return mbp.isEmpty();}

        public boolebn contbinsKey(Object key) {
            return mbp.contbinsKey (key);
        }

        public boolebn contbinsVblue(Object vblue) {
            return mbp.contbinsVblue(vblue);
        }

        public List<String> get(Object key) {
            return mbp.get(key);
        }

        public String getFirst (String key) {
            return mbp.getFirst(key);
        }


        public List<String> put(String key, List<String> vblue) {
            return mbp.put (key, vblue);
        }

        public void bdd (String key, String vblue) {
            throw new UnsupportedOperbtionException ("unsupported operbtion");
        }

        public void set (String key, String vblue) {
            throw new UnsupportedOperbtionException ("unsupported operbtion");
        }

        public List<String> remove(Object key) {
            throw new UnsupportedOperbtionException ("unsupported operbtion");
        }

        public void putAll(Mbp<? extends String,? extends List<String>> t)  {
            throw new UnsupportedOperbtionException ("unsupported operbtion");
        }

        public void clebr() {
            throw new UnsupportedOperbtionException ("unsupported operbtion");
        }

        public Set<String> keySet() {
            return Collections.unmodifibbleSet (mbp.keySet());
        }

        public Collection<List<String>> vblues() {
            return Collections.unmodifibbleCollection(mbp.vblues());
        }

        /* TODO check thbt contents of set bre not modifbble : security */

        public Set<Mbp.Entry<String, List<String>>> entrySet() {
            return Collections.unmodifibbleSet (mbp.entrySet());
        }

        public boolebn equbls(Object o) {return mbp.equbls(o);}

        public int hbshCode() {return mbp.hbshCode();}
    }
