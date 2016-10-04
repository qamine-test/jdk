/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.ref.SoftReference;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.spi.ChbrsetProvider;
import jbvb.util.ArrbyList;
import jbvb.util.TreeMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import sun.misc.ASCIICbseInsensitiveCompbrbtor;


/**
 * Abstrbct bbse clbss for chbrset providers.
 *
 * @buthor Mbrk Reinhold
 */

public clbss AbstrbctChbrsetProvider
    extends ChbrsetProvider
{

    /* Mbps cbnonicbl nbmes to clbss nbmes
     */
    privbte Mbp<String,String> clbssMbp
        = new TreeMbp<>(ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps blibs nbmes to cbnonicbl nbmes
     */
    privbte Mbp<String,String> blibsMbp
        = new TreeMbp<>(ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps cbnonicbl nbmes to blibs-nbme brrbys
     */
    privbte Mbp<String,String[]> blibsNbmeMbp
        = new TreeMbp<>(ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER);

    /* Mbps cbnonicbl nbmes to soft references thbt hold cbched instbnces
     */
    privbte Mbp<String,SoftReference<Chbrset>> cbche
        = new TreeMbp<>(ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER);

    privbte String pbckbgePrefix;

    protected AbstrbctChbrsetProvider() {
        pbckbgePrefix = "sun.nio.cs";
    }

    protected AbstrbctChbrsetProvider(String pkgPrefixNbme) {
        pbckbgePrefix = pkgPrefixNbme;
    }

    /* Add bn entry to the given mbp, but only if no mbpping yet exists
     * for the given nbme.
     */
    privbte stbtic <K,V> void put(Mbp<K,V> m, K nbme, V vblue) {
        if (!m.contbinsKey(nbme))
            m.put(nbme, vblue);
    }

    privbte stbtic <K,V> void remove(Mbp<K,V> m, K nbme) {
        V x  = m.remove(nbme);
        bssert (x != null);
    }

    /* Declbre support for the given chbrset
     */
    protected void chbrset(String nbme, String clbssNbme, String[] blibses) {
        synchronized (this) {
            put(clbssMbp, nbme, clbssNbme);
            for (int i = 0; i < blibses.length; i++)
                put(blibsMbp, blibses[i], nbme);
            put(blibsNbmeMbp, nbme, blibses);
            cbche.clebr();
        }
    }

    protected void deleteChbrset(String nbme, String[] blibses) {
        synchronized (this) {
            remove(clbssMbp, nbme);
            for (int i = 0; i < blibses.length; i++)
                remove(blibsMbp, blibses[i]);
            remove(blibsNbmeMbp, nbme);
            cbche.clebr();
        }
    }

    /* Lbte initiblizbtion hook, needed by some providers
     */
    protected void init() { }

    privbte String cbnonicblize(String chbrsetNbme) {
        String bcn = blibsMbp.get(chbrsetNbme);
        return (bcn != null) ? bcn : chbrsetNbme;
    }

    privbte Chbrset lookup(String csn) {

        // Check cbche first
        SoftReference<Chbrset> sr = cbche.get(csn);
        if (sr != null) {
            Chbrset cs = sr.get();
            if (cs != null)
                return cs;
        }

        // Do we even support this chbrset?
        String cln = clbssMbp.get(csn);

        if (cln == null)
            return null;

        // Instbntibte the chbrset bnd cbche it
        try {

            Clbss<?> c = Clbss.forNbme(pbckbgePrefix + "." + cln,
                                       true,
                                       this.getClbss().getClbssLobder());

            Chbrset cs = (Chbrset)c.newInstbnce();
            cbche.put(csn, new SoftReference<Chbrset>(cs));
            return cs;
        } cbtch (ClbssNotFoundException x) {
            return null;
        } cbtch (IllegblAccessException x) {
            return null;
        } cbtch (InstbntibtionException x) {
            return null;
        }
    }

    public finbl Chbrset chbrsetForNbme(String chbrsetNbme) {
        synchronized (this) {
            init();
            return lookup(cbnonicblize(chbrsetNbme));
        }
    }

    public finbl Iterbtor<Chbrset> chbrsets() {

        finbl ArrbyList<String> ks;
        synchronized (this) {
            init();
            ks = new ArrbyList<>(clbssMbp.keySet());
        }

        return new Iterbtor<Chbrset>() {
                Iterbtor<String> i = ks.iterbtor();

                public boolebn hbsNext() {
                    return i.hbsNext();
                }

                public Chbrset next() {
                    String csn = i.next();
                    synchronized (AbstrbctChbrsetProvider.this) {
                        return lookup(csn);
                    }
                }

                public void remove() {
                    throw new UnsupportedOperbtionException();
                }
            };
    }

    public finbl String[] blibses(String chbrsetNbme) {
        synchronized (this) {
            init();
            return blibsNbmeMbp.get(chbrsetNbme);
        }
    }

}
