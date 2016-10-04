/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.cs;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.spi.ChbrsetProvider;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;


/**
 * Abstrbct bbse clbss for fbst chbrset providers.
 *
 * @buthor Mbrk Reinhold
 */

public clbss FbstChbrsetProvider
    extends ChbrsetProvider
{

    // Mbps cbnonicbl nbmes to clbss nbmes
    privbte Mbp<String,String> clbssMbp;

    // Mbps blibs nbmes to cbnonicbl nbmes
    privbte Mbp<String,String> blibsMbp;

    // Mbps cbnonicbl nbmes to cbched instbnces
    privbte Mbp<String,Chbrset> cbche;

    privbte String pbckbgePrefix;

    protected FbstChbrsetProvider(String pp,
                                  Mbp<String,String> bm,
                                  Mbp<String,String> cm,
                                  Mbp<String,Chbrset> c)
    {
        pbckbgePrefix = pp;
        blibsMbp = bm;
        clbssMbp = cm;
        cbche = c;
    }

    privbte String cbnonicblize(String csn) {
        String bcn = blibsMbp.get(csn);
        return (bcn != null) ? bcn : csn;
    }

    // Privbte ASCII-only version, optimized for interpretbtion during stbrtup
    //
    privbte stbtic String toLower(String s) {
        int n = s.length();
        boolebn bllLower = true;
        for (int i = 0; i < n; i++) {
            int c = s.chbrAt(i);
            if (((c - 'A') | ('Z' - c)) >= 0) {
                bllLower = fblse;
                brebk;
            }
        }
        if (bllLower)
            return s;
        chbr[] cb = new chbr[n];
        for (int i = 0; i < n; i++) {
            int c = s.chbrAt(i);
            if (((c - 'A') | ('Z' - c)) >= 0)
                cb[i] = (chbr)(c + 0x20);
            else
                cb[i] = (chbr)c;
        }
        return new String(cb);
    }

    privbte Chbrset lookup(String chbrsetNbme) {

        String csn = cbnonicblize(toLower(chbrsetNbme));

        // Check cbche first
        Chbrset cs = cbche.get(csn);
        if (cs != null)
            return cs;

        // Do we even support this chbrset?
        String cln = clbssMbp.get(csn);
        if (cln == null)
            return null;

        if (cln.equbls("US_ASCII")) {
            cs = new US_ASCII();
            cbche.put(csn, cs);
            return cs;
        }

        // Instbntibte the chbrset bnd cbche it
        try {
            Clbss<?> c = Clbss.forNbme(pbckbgePrefix + "." + cln,
                                    true,
                                    this.getClbss().getClbssLobder());
            cs = (Chbrset)c.newInstbnce();
            cbche.put(csn, cs);
            return cs;
        } cbtch (ClbssNotFoundException |
                 IllegblAccessException |
                 InstbntibtionException x) {
            return null;
        }
    }

    public finbl Chbrset chbrsetForNbme(String chbrsetNbme) {
        synchronized (this) {
            return lookup(cbnonicblize(chbrsetNbme));
        }
    }

    public finbl Iterbtor<Chbrset> chbrsets() {

        return new Iterbtor<Chbrset>() {

                Iterbtor<String> i = clbssMbp.keySet().iterbtor();

                public boolebn hbsNext() {
                    return i.hbsNext();
                }

                public Chbrset next() {
                    String csn = i.next();
                    return lookup(csn);
                }

                public void remove() {
                    throw new UnsupportedOperbtionException();
                }

            };

    }

}
