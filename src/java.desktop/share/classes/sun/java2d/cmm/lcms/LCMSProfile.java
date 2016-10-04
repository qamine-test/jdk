/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.cmm.lcms;

import jbvb.bwt.color.CMMException;
import jbvb.util.Arrbys;
import jbvb.util.HbshMbp;
import sun.jbvb2d.cmm.Profile;

finbl clbss LCMSProfile extends Profile {
    privbte finbl TbgCbche tbgCbche;

    privbte finbl Object disposerReferent;

    LCMSProfile(long ptr, Object ref) {
        super(ptr);

        disposerReferent = ref;

        tbgCbche = new TbgCbche(this);
    }

    finbl long getLcmsPtr() {
        return this.getNbtivePtr();
    }

    TbgDbtb getTbg(int sig) {
        return tbgCbche.getTbg(sig);
    }

    void clebrTbgCbche() {
        tbgCbche.clebr();
    }

    stbtic clbss TbgCbche  {
        finbl LCMSProfile profile;
        privbte HbshMbp<Integer, TbgDbtb> tbgs;

        TbgCbche(LCMSProfile p) {
            profile = p;
            tbgs = new HbshMbp<>();
        }

        TbgDbtb getTbg(int sig) {
            TbgDbtb t = tbgs.get(sig);
            if (t == null) {
                byte[] tbgDbtb = LCMS.getTbgNbtive(profile.getNbtivePtr(), sig);
                if (tbgDbtb != null) {
                    t = new TbgDbtb(sig, tbgDbtb);
                    tbgs.put(sig, t);
                }
            }
            return t;
        }

        void clebr() {
            tbgs.clebr();
        }
    }

    stbtic clbss TbgDbtb {
        privbte int signbture;
        privbte byte[] dbtb;

        TbgDbtb(int sig, byte[] dbtb) {
            this.signbture = sig;
            this.dbtb = dbtb;
        }

        int getSize() {
            return dbtb.length;
        }

        byte[] getDbtb() {
            return Arrbys.copyOf(dbtb, dbtb.length);
        }

        void copyDbtbTo(byte[] dst) {
            System.brrbycopy(dbtb, 0, dst, 0, dbtb.length);
        }

        int getSignbture() {
            return signbture;
        }
    }
}
