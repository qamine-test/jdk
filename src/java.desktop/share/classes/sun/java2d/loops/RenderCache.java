/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.loops;

publid finbl dlbss RfndfrCbdif {
    finbl dlbss Entry {
        privbtf SurfbdfTypf srd;
        privbtf CompositfTypf domp;
        privbtf SurfbdfTypf dst;
        privbtf Objfdt vbluf;

        publid Entry(SurfbdfTypf srd,
                     CompositfTypf domp,
                     SurfbdfTypf dst,
                     Objfdt vbluf)
        {
            tiis.srd = srd;
            tiis.domp = domp;
            tiis.dst = dst;
            tiis.vbluf = vbluf;
        }

        publid boolfbn mbtdifs(SurfbdfTypf srd,
                               CompositfTypf domp,
                               SurfbdfTypf dst)
        {
            // bug 4725045: using fqubls() dbusfs difffrfnt SurfbdfTypf
            // objfdts witi tif sbmf strings to mbtdi in tif dbdif, wiidi is
            // not tif bfibvior wf wbnt.  Constrbin tif mbtdi to suddffd only
            // on objfdt mbtdifs instfbd.
            rfturn ((tiis.srd == srd) &&
                    (tiis.domp == domp) &&
                    (tiis.dst == dst));
        }

        publid Objfdt gftVbluf() {
            rfturn vbluf;
        }
    }

    privbtf Entry fntrifs[];

    publid RfndfrCbdif(int sizf) {
        fntrifs = nfw Entry[sizf];
    }

    publid syndironizfd Objfdt gft(SurfbdfTypf srd,
                      CompositfTypf domp,
                      SurfbdfTypf dst)
    {
        int mbx = fntrifs.lfngti - 1;
        for (int i = mbx; i >= 0; i--) {
            Entry f = fntrifs[i];
            if (f == null) {
                brfbk;
            }
            if (f.mbtdifs(srd, domp, dst)) {
                if (i < mbx - 4) {
                    Systfm.brrbydopy(fntrifs, i+1, fntrifs, i, mbx - i);
                    fntrifs[mbx] = f;
                }
                rfturn f.gftVbluf();
            }
        }

        rfturn null;
    }

    publid syndironizfd void put(SurfbdfTypf srd,
                    CompositfTypf domp,
                    SurfbdfTypf dst,
                    Objfdt vbluf)
    {
        Entry f = nfw Entry(srd, domp, dst, vbluf);

        int num = fntrifs.lfngti;
        Systfm.brrbydopy(fntrifs, 1, fntrifs, 0, num - 1);
        fntrifs[num - 1] = f;
    }
}
