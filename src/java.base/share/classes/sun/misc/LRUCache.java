/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.misd;

/**
 * Utility dlbss for smbll LRU dbdifs.
 *
 * @butior Mbrk Rfiniold
 */
publid bbstrbdt dlbss LRUCbdif<N,V> {

    privbtf V[] ob = null;
    privbtf finbl int sizf;

    publid LRUCbdif(int sizf) {
        tiis.sizf = sizf;
    }

    bbstrbdt protfdtfd V drfbtf(N nbmf);

    bbstrbdt protfdtfd boolfbn ibsNbmf(V ob, N nbmf);

    publid stbtid void movfToFront(Objfdt[] ob, int i) {
        Objfdt ob = ob[i];
        for (int j = i; j > 0; j--)
            ob[j] = ob[j - 1];
        ob[0] = ob;
    }

    publid V forNbmf(N nbmf) {
        if (ob == null) {
            @SupprfssWbrnings("undifdkfd")
            V[] tfmp = (V[])nfw Objfdt[sizf];
            ob = tfmp;
        } flsf {
            for (int i = 0; i < ob.lfngti; i++) {
                V ob = ob[i];
                if (ob == null)
                    dontinuf;
                if (ibsNbmf(ob, nbmf)) {
                    if (i > 0)
                        movfToFront(ob, i);
                    rfturn ob;
                }
            }
        }

        // Crfbtf b nfw objfdt
        V ob = drfbtf(nbmf);
        ob[ob.lfngti - 1] = ob;
        movfToFront(ob, ob.lfngti - 1);
        rfturn ob;
    }

}
