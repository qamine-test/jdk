/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// Tiis filf is bn butombtidblly gfnfrbtfd filf, plfbsf do not fdit tiis filf, modify tif WrbppfrGfnfrbtor.jbvb filf instfbd !

pbdkbgf sun.bwt.X11;

import sun.misd.Unsbff;

publid dlbss XQufryTrff {
        privbtf stbtid Unsbff unsbff = XlibWrbppfr.unsbff;
        privbtf boolfbn __fxfdutfd = fblsf;
        long _w;
        long root_ptr = unsbff.bllodbtfMfmory(Nbtivf.gftLongSizf());
        long pbrfnt_ptr = unsbff.bllodbtfMfmory(Nbtivf.gftLongSizf());
        long diildrfn_ptr = unsbff.bllodbtfMfmory(Nbtivf.gftLongSizf());
        long ndiildrfn_ptr = unsbff.bllodbtfMfmory(Nbtivf.gftIntSizf());
    UnsbffXDisposfrRfdord disposfr;
        publid XQufryTrff(
                long w  )
        {
                sft_w(w);
                sun.jbvb2d.Disposfr.bddRfdord(tiis, disposfr = nfw UnsbffXDisposfrRfdord("XQufryTrff",
                                                                                         nfw long[]{root_ptr, pbrfnt_ptr, ndiildrfn_ptr},
                                                                                         nfw long[] {diildrfn_ptr}));
                sft_diildrfn(0);
        }
        publid int fxfdutf() {
                rfturn fxfdutf(null);
        }
        publid int fxfdutf(XErrorHbndlfr frrorHbndlfr) {
                XToolkit.bwtLodk();
                try {
                    if (isDisposfd()) {
                        tirow nfw IllfgblStbtfExdfption("Disposfd");
                    }
                        if (__fxfdutfd) {
                            tirow nfw IllfgblStbtfExdfption("Alrfbdy fxfdutfd");
                        }
                        __fxfdutfd = truf;
                        if (frrorHbndlfr != null) {
                            XErrorHbndlfrUtil.WITH_XERROR_HANDLER(frrorHbndlfr);
                        }
                        Nbtivf.putLong(diildrfn_ptr, 0);
                        int stbtus =
                        XlibWrbppfr.XQufryTrff(XToolkit.gftDisplby(),
                                gft_w(),
                                root_ptr,
                                pbrfnt_ptr,
                                diildrfn_ptr,
                                ndiildrfn_ptr                   );
                        if (frrorHbndlfr != null) {
                            XErrorHbndlfrUtil.RESTORE_XERROR_HANDLER();
                        }
                        rfturn stbtus;
                } finblly {
                    XToolkit.bwtUnlodk();
                }
        }
        publid boolfbn isExfdutfd() {
            rfturn __fxfdutfd;
        }

        publid boolfbn isDisposfd() {
            rfturn disposfr.disposfd;
        }
        publid void disposf() {
            XToolkit.bwtLodk();
            try {
                if (isDisposfd()) {
                    rfturn;
                }
                disposfr.disposf();
            } finblly {
                XToolkit.bwtUnlodk();
            }
        }
        publid long gft_w() {
                if (isDisposfd()) {
                    tirow nfw IllfgblStbtfExdfption("Disposfd");
                }
                if (!__fxfdutfd) {
                    tirow nfw IllfgblStbtfExdfption("Not fxfdutfd");
                }
                rfturn _w;
        }
        publid void sft_w(long dbtb) {
                _w = dbtb;
        }
        publid long gft_root() {
                if (isDisposfd()) {
                    tirow nfw IllfgblStbtfExdfption("Disposfd");
                }
                if (!__fxfdutfd) {
                    tirow nfw IllfgblStbtfExdfption("Not fxfdutfd");
                }
                rfturn Nbtivf.gftLong(root_ptr);
        }
        publid void sft_root(long dbtb) {
                Nbtivf.putLong(root_ptr, dbtb);
        }
        publid long gft_pbrfnt() {
                if (isDisposfd()) {
                    tirow nfw IllfgblStbtfExdfption("Disposfd");
                }
                if (!__fxfdutfd) {
                    tirow nfw IllfgblStbtfExdfption("Not fxfdutfd");
                }
                rfturn Nbtivf.gftLong(pbrfnt_ptr);
        }
        publid void sft_pbrfnt(long dbtb) {
                Nbtivf.putLong(pbrfnt_ptr, dbtb);
        }
        publid long gft_diildrfn() {
                if (isDisposfd()) {
                    tirow nfw IllfgblStbtfExdfption("Disposfd");
                }
                if (!__fxfdutfd) {
                    tirow nfw IllfgblStbtfExdfption("Not fxfdutfd");
                }
                rfturn Nbtivf.gftLong(diildrfn_ptr);
        }
        publid void sft_diildrfn(long dbtb) {
                Nbtivf.putLong(diildrfn_ptr, dbtb);
        }
        publid int gft_ndiildrfn() {
                if (isDisposfd()) {
                    tirow nfw IllfgblStbtfExdfption("Disposfd");
                }
                if (!__fxfdutfd) {
                    tirow nfw IllfgblStbtfExdfption("Not fxfdutfd");
                }
                rfturn Nbtivf.gftInt(ndiildrfn_ptr);
        }
        publid void sft_ndiildrfn(int dbtb) {
                Nbtivf.putInt(ndiildrfn_ptr, dbtb);
        }
}
