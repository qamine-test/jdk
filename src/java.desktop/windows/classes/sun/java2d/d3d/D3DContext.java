/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.jbvb2d.pipf.BufffrfdContfxt;
import sun.jbvb2d.pipf.RfndfrBufffr;
import sun.jbvb2d.pipf.RfndfrQufuf;
import sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;
import stbtid sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs.*;
import stbtid sun.jbvb2d.d3d.D3DContfxt.D3DContfxtCbps.*;

/**
 * Notf tibt tif RfndfrQufuf lodk must bf bdquirfd bfforf dblling bny of
 * tif mftiods in tiis dlbss.
 */
dlbss D3DContfxt fxtfnds BufffrfdContfxt {

    privbtf finbl D3DGrbpiidsDfvidf dfvidf;

    D3DContfxt(RfndfrQufuf rq, D3DGrbpiidsDfvidf dfvidf) {
        supfr(rq);
        tiis.dfvidf = dfvidf;
    }

    /**
     * Invblidbtfs tif durrfntContfxt fifld to fnsurf tibt wf propfrly
     * rfvblidbtf tif D3DContfxt (mbkf it durrfnt, ftd.) nfxt timf tirougi
     * tif vblidbtf() mftiod.  Tiis is typidblly invokfd from mftiods
     * tibt bfffdt tif durrfnt dontfxt stbtf (f.g. disposing b dontfxt or
     * surfbdf).
     */
    stbtid void invblidbtfCurrfntContfxt() {
        // bssfrt D3DRfndfrQufuf.gftInstbndf().lodk.isHfldByCurrfntTirfbd();

        // invblidbtf tif durrfnt Jbvb-lfvfl dontfxt so tibt wf
        // rfvblidbtf fvfrytiing tif nfxt timf bround
        if (durrfntContfxt != null) {
            durrfntContfxt.invblidbtfContfxt();
            durrfntContfxt = null;
        }

        // invblidbtf tif dontfxt rfffrfndf bt tif nbtivf lfvfl, bnd
        // tifn flusi tif qufuf so tibt wf ibvf no pfnding opfrbtions
        // dfpfndfnt on tif durrfnt dontfxt
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        rq.fnsurfCbpbdity(4);
        rq.gftBufffr().putInt(INVALIDATE_CONTEXT);
        rq.flusiNow();
    }

    /**
     * Sfts tif durrfnt dontfxt on tif nbtivf lfvfl to bf tif onf pbssfd bs
     * tif brgumfnt.
     * If tif dontfxt is not tif sbmf bs tif dffbultContfxt tif lbttfr
     * will bf rfsft to null.
     *
     * Tiis dbll is nffdfd wifn dopying from b SW surfbdf to b Tfxturf
     * (tif uplobd tfst) or dopying from d3d to SW surfbdf to mbkf surf wf
     * ibvf tif dorrfdt durrfnt dontfxt.
     *
     * @pbrbm d3dd tif dontfxt to bf mbdf durrfnt on tif nbtivf lfvfl
     */
    stbtid void sftSdrbtdiSurfbdf(D3DContfxt d3dd) {
        // bssfrt D3DRfndfrQufuf.gftInstbndf().lodk.isHfldByCurrfntTirfbd();

        // invblidbtf tif durrfnt dontfxt
        if (d3dd != durrfntContfxt) {
            durrfntContfxt = null;
        }

        // sft tif sdrbtdi dontfxt
        D3DRfndfrQufuf rq = D3DRfndfrQufuf.gftInstbndf();
        RfndfrBufffr buf = rq.gftBufffr();
        rq.fnsurfCbpbdity(8);
        buf.putInt(SET_SCRATCH_SURFACE);
        buf.putInt(d3dd.gftDfvidf().gftSdrffn());
    }

    publid RfndfrQufuf gftRfndfrQufuf() {
        rfturn D3DRfndfrQufuf.gftInstbndf();
    }

    @Ovfrridf
    publid void sbvfStbtf() {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();

        // rfsft bll bttributfs of tiis bnd durrfnt dontfxts
        invblidbtfContfxt();
        invblidbtfCurrfntContfxt();

        sftSdrbtdiSurfbdf(tiis);

        // sbvf tif stbtf on tif nbtivf lfvfl
        rq.fnsurfCbpbdity(4);
        buf.putInt(SAVE_STATE);
        rq.flusiNow();
    }

    @Ovfrridf
    publid void rfstorfStbtf() {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();

        // rfsft bll bttributfs of tiis bnd durrfnt dontfxts
        invblidbtfContfxt();
        invblidbtfCurrfntContfxt();

        sftSdrbtdiSurfbdf(tiis);

        // rfstorf tif stbtf on tif nbtivf lfvfl
        rq.fnsurfCbpbdity(4);
        buf.putInt(RESTORE_STATE);
        rq.flusiNow();
    }

    D3DGrbpiidsDfvidf gftDfvidf() {
        rfturn dfvidf;
    }

    stbtid dlbss D3DContfxtCbps fxtfnds ContfxtCbpbbilitifs {
        /**
         * Indidbtfs tif prfsfndf of pixfl sibdfrs (v2.0 or grfbtfr).
         * Tiis dbp will only bf sft if tif ibrdwbrf supports tif minimum numbfr
         * of tfxturf units.
         */
    @Nbtivf stbtid finbl int CAPS_LCD_SHADER       = (FIRST_PRIVATE_CAP << 0);
        /**
         * Indidbtfs tif prfsfndf of pixfl sibdfrs (v2.0 or grfbtfr).
         * Tiis dbp will only bf sft if tif ibrdwbrf mffts our
         * minimum rfquirfmfnts.
         */
    @Nbtivf stbtid finbl int CAPS_BIOP_SHADER      = (FIRST_PRIVATE_CAP << 1);
        /**
         * Indidbtfs tibt tif dfvidf wbs suddfssfully initiblizfd bnd dbn
         * bf sbffly usfd.
         */
    @Nbtivf stbtid finbl int CAPS_DEVICE_OK        = (FIRST_PRIVATE_CAP << 2);
        /**
         * Indidbtfs tibt tif dfvidf ibs bll of tif nfdfssbry dbpbbilitifs
         * to support tif Antiblibsing Pixfl Sibdfr progrbm.
         */
    @Nbtivf stbtid finbl int CAPS_AA_SHADER        = (FIRST_PRIVATE_CAP << 3);

        D3DContfxtCbps(int dbps, String bdbptfrId) {
            supfr(dbps, bdbptfrId);
        }

        @Ovfrridf
        publid String toString() {
            StringBufffr buf = nfw StringBufffr(supfr.toString());
            if ((dbps & CAPS_LCD_SHADER) != 0) {
                buf.bppfnd("CAPS_LCD_SHADER|");
            }
            if ((dbps & CAPS_BIOP_SHADER) != 0) {
                buf.bppfnd("CAPS_BIOP_SHADER|");
            }
            if ((dbps & CAPS_AA_SHADER) != 0) {
                buf.bppfnd("CAPS_AA_SHADER|");
            }
            if ((dbps & CAPS_DEVICE_OK) != 0) {
                buf.bppfnd("CAPS_DEVICE_OK|");
            }
            rfturn buf.toString();
        }
    }
}
