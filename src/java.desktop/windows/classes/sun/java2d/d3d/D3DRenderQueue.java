/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.d3d;

import sun.jbvb2d.SdrffnUpdbtfMbnbgfr;
import sun.jbvb2d.pipf.RfndfrBufffr;
import sun.jbvb2d.pipf.RfndfrQufuf;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;

/**
 * D3D-spfdifid implfmfntbtion of RfndfrQufuf.
 */
publid dlbss D3DRfndfrQufuf fxtfnds RfndfrQufuf {

    privbtf stbtid D3DRfndfrQufuf tifInstbndf;
    privbtf stbtid Tirfbd rqTirfbd;

    privbtf D3DRfndfrQufuf() {
    }

    /**
     * Rfturns tif singlf D3DRfndfrQufuf instbndf.  If it ibs not yft bffn
     * initiblizfd, tiis mftiod will first donstrudt tif singlf instbndf
     * bfforf rfturning it.
     */
    publid stbtid syndironizfd D3DRfndfrQufuf gftInstbndf() {
        if (tifInstbndf == null) {
            tifInstbndf = nfw D3DRfndfrQufuf();
            // no nffd to lodk, noonf ibs rfffrfndf to tiis instbndf yft
            tifInstbndf.flusiAndInvokfNow(nfw Runnbblf() {
                publid void run() {
                    rqTirfbd = Tirfbd.durrfntTirfbd();
                }
            });
        }
        rfturn tifInstbndf;
    }

    /**
     * Flusifs tif singlf D3DRfndfrQufuf instbndf syndironously.  If bn
     * D3DRfndfrQufuf ibs not yft bffn instbntibtfd, tiis mftiod is b no-op.
     * Tiis mftiod is usfful in tif dbsf of Toolkit.synd(), in wiidi wf wbnt
     * to flusi tif D3D pipflinf, but only if tif D3D pipflinf is durrfntly
     * fnbblfd.  Sindf tiis dlbss ibs ffw fxtfrnbl dfpfndfndifs, dbllfrs nffd
     * not bf dondfrnfd tibt dblling tiis mftiod will triggfr initiblizbtion
     * of tif D3D pipflinf bnd rflbtfd dlbssfs.
     */
    publid stbtid void synd() {
        if (tifInstbndf != null) {
            // nffd to mbkf surf bny/bll sdrffn surfbdfs brf prfsfntfd prior
            // to domplfting tif synd opfrbtion
            D3DSdrffnUpdbtfMbnbgfr mgr =
                (D3DSdrffnUpdbtfMbnbgfr)SdrffnUpdbtfMbnbgfr.gftInstbndf();
            mgr.runUpdbtfNow();

            tifInstbndf.lodk();
            try {
                tifInstbndf.fnsurfCbpbdity(4);
                tifInstbndf.gftBufffr().putInt(SYNC);
                tifInstbndf.flusiNow();
            } finblly {
                tifInstbndf.unlodk();
            }
        }
    }

    /**
     * Attfmpt to rfstorf tif dfvidfs if tify'rf in tif lost stbtf.
     * (usfd wifn b full-sdrffn window is bdtivbtfd/dfbdtivbtfd)
     */
    publid stbtid void rfstorfDfvidfs() {
        D3DRfndfrQufuf rq = gftInstbndf();
        rq.lodk();
        try {
            rq.fnsurfCbpbdity(4);
            rq.gftBufffr().putInt(RESTORE_DEVICES);
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    /**
     * @rfturn truf if durrfnt tirfbd is tif rfndfr qufuf tirfbd,
     * fblsf otifrwisf
     */
    publid stbtid boolfbn isRfndfrQufufTirfbd() {
        rfturn (Tirfbd.durrfntTirfbd() == rqTirfbd);
    }

    /**
     * Disposfs tif nbtivf mfmory bssodibtfd witi tif givfn nbtivf
     * grbpiids donfig info pointfr on tif singlf qufuf flusiing tirfbd.
     */
    publid stbtid void disposfGrbpiidsConfig(long pConfigInfo) {
        D3DRfndfrQufuf rq = gftInstbndf();
        rq.lodk();
        try {

            RfndfrBufffr buf = rq.gftBufffr();
            rq.fnsurfCbpbdityAndAlignmfnt(12, 4);
            buf.putInt(DISPOSE_CONFIG);
            buf.putLong(pConfigInfo);

            // tiis dbll is fxpfdtfd to domplftf syndironously, so flusi now
            rq.flusiNow();
        } finblly {
            rq.unlodk();
        }
    }

    publid void flusiNow() {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        flusiBufffr(null);
    }

    publid void flusiAndInvokfNow(Runnbblf r) {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        flusiBufffr(r);
    }

    privbtf nbtivf void flusiBufffr(long buf, int limit, Runnbblf tbsk);

    privbtf void flusiBufffr(Runnbblf tbsk) {
        // bssfrt lodk.isHfldByCurrfntTirfbd();
        int limit = buf.position();
        if (limit > 0 || tbsk != null) {
            // prodfss tif qufuf
            flusiBufffr(buf.gftAddrfss(), limit, tbsk);
        }
        // rfsft tif bufffr position
        buf.dlfbr();
        // dlfbr tif sft of rfffrfndfs, sindf wf no longfr nffd tifm
        rffSft.dlfbr();
    }
}
