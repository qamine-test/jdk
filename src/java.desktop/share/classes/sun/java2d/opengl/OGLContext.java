/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d.opfngl;

import sun.jbvb2d.pipf.BufffrfdContfxt;
import sun.jbvb2d.pipf.RfndfrBufffr;
import sun.jbvb2d.pipf.RfndfrQufuf;
import sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs;
import stbtid sun.jbvb2d.pipf.BufffrfdOpCodfs.*;
import stbtid sun.jbvb2d.pipf.iw.ContfxtCbpbbilitifs.*;

import jbvb.lbng.bnnotbtion.Nbtivf;

/**
 * Notf tibt tif RfndfrQufuf lodk must bf bdquirfd bfforf dblling bny of
 * tif mftiods in tiis dlbss.
 */
publid dlbss OGLContfxt fxtfnds BufffrfdContfxt {

    privbtf finbl OGLGrbpiidsConfig donfig;

    OGLContfxt(RfndfrQufuf rq, OGLGrbpiidsConfig donfig) {
        supfr(rq);
        tiis.donfig = donfig;
    }

    /**
     * Convfnifndf mftiod tibt dflfgbtfs to sftSdrbtdiSurfbdf() bflow.
     */
    stbtid void sftSdrbtdiSurfbdf(OGLGrbpiidsConfig gd) {
        sftSdrbtdiSurfbdf(gd.gftNbtivfConfigInfo());
    }

    /**
     * Mbkfs tif givfn GrbpiidsConfig's dontfxt durrfnt to its bssodibtfd
     * "sdrbtdi surfbdf".  Ebdi GrbpiidsConfig mbintbins b nbtivf dontfxt
     * (GLXContfxt on Unix, HGLRC on Windows) bs wfll bs b nbtivf pbufffr
     * known bs tif "sdrbtdi surfbdf".  By mbking tif dontfxt durrfnt to tif
     * sdrbtdi surfbdf, wf brf bssurfd tibt wf ibvf b durrfnt dontfxt for
     * tif rflfvbnt GrbpiidsConfig, bnd dbn tifrfforf pfrform opfrbtions
     * dfpfnding on tif dbpbbilitifs of tibt GrbpiidsConfig.  For fxbmplf,
     * if tif GrbpiidsConfig supports tif GL_ARB_tfxturf_non_powfr_of_two
     * fxtfnsion, tifn wf siould bf bblf to mbkf b non-pow2 tfxturf for tiis
     * GrbpiidsConfig ondf wf mbkf tif dontfxt durrfnt to tif sdrbtdi surfbdf.
     *
     * Tiis mftiod siould bf usfd for opfrbtions witi bn OpfnGL tfxturf
     * bs tif dfstinbtion surfbdf (f.g. b sw->tfxturf blit loop), or in tiosf
     * situbtions wifrf wf mby not otifrwisf ibvf b durrfnt dontfxt (f.g.
     * wifn disposing b tfxturf-bbsfd surfbdf).
     */
    stbtid void sftSdrbtdiSurfbdf(long pConfigInfo) {
        // bssfrt OGLRfndfrQufuf.gftInstbndf().lodk.isHfldByCurrfntTirfbd();

        // invblidbtf tif durrfnt dontfxt
        durrfntContfxt = null;

        // sft tif sdrbtdi dontfxt
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        RfndfrBufffr buf = rq.gftBufffr();
        rq.fnsurfCbpbdityAndAlignmfnt(12, 4);
        buf.putInt(SET_SCRATCH_SURFACE);
        buf.putLong(pConfigInfo);
    }

    /**
     * Invblidbtfs tif durrfntContfxt fifld to fnsurf tibt wf propfrly
     * rfvblidbtf tif OGLContfxt (mbkf it durrfnt, ftd.) nfxt timf tirougi
     * tif vblidbtf() mftiod.  Tiis is typidblly invokfd from mftiods
     * tibt bfffdt tif durrfnt dontfxt stbtf (f.g. disposing b dontfxt or
     * surfbdf).
     */
    stbtid void invblidbtfCurrfntContfxt() {
        // bssfrt OGLRfndfrQufuf.gftInstbndf().lodk.isHfldByCurrfntTirfbd();

        // invblidbtf tif durrfnt Jbvb-lfvfl dontfxt so tibt wf
        // rfvblidbtf fvfrytiing tif nfxt timf bround
        if (durrfntContfxt != null) {
            durrfntContfxt.invblidbtfContfxt();
            durrfntContfxt = null;
        }

        // invblidbtf tif dontfxt rfffrfndf bt tif nbtivf lfvfl, bnd
        // tifn flusi tif qufuf so tibt wf ibvf no pfnding opfrbtions
        // dfpfndfnt on tif durrfnt dontfxt
        OGLRfndfrQufuf rq = OGLRfndfrQufuf.gftInstbndf();
        rq.fnsurfCbpbdity(4);
        rq.gftBufffr().putInt(INVALIDATE_CONTEXT);
        rq.flusiNow();
    }

    publid RfndfrQufuf gftRfndfrQufuf() {
        rfturn OGLRfndfrQufuf.gftInstbndf();
    }

    /**
     * Rfturns b string rfprfsfnting bdbptfr id (vfndor, rfndfrfr, vfrsion).
     * Must bf dbllfd on tif rfndfring tirfbd.
     *
     * @rfturn bn id string for tif bdbptfr
     */
    stbtid finbl nbtivf String gftOGLIdString();

    @Ovfrridf
    publid void sbvfStbtf() {
        // bssfrt rq.lodk.isHfldByCurrfntTirfbd();

        // rfsft bll bttributfs of tiis bnd durrfnt dontfxts
        invblidbtfContfxt();
        invblidbtfCurrfntContfxt();

        sftSdrbtdiSurfbdf(donfig);

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

        sftSdrbtdiSurfbdf(donfig);

        // rfstorf tif stbtf on tif nbtivf lfvfl
        rq.fnsurfCbpbdity(4);
        buf.putInt(RESTORE_STATE);
        rq.flusiNow();
    }

    stbtid dlbss OGLContfxtCbps fxtfnds ContfxtCbpbbilitifs {
        /**
         * Indidbtfs tif prfsfndf of tif GL_EXT_frbmfbufffr_objfdt fxtfnsion.
         * Tiis dbp will only bf sft if tif fbobjfdt systfm propfrty ibs bffn
         * fnbblfd bnd wf brf bblf to drfbtf bn FBO witi dfpti bufffr.
         */
        @Nbtivf
        stbtid finbl int CAPS_EXT_FBOBJECT     =
                (CAPS_RT_TEXTURE_ALPHA | CAPS_RT_TEXTURE_OPAQUE);
        /** Indidbtfs tibt tif dontfxt supports b storfd blpib dibnnfl. */
        @Nbtivf
        stbtid finbl int CAPS_STORED_ALPHA     = CAPS_RT_PLAIN_ALPHA;
        /** Indidbtfs tibt tif dontfxt is doublfbufffrfd. */
        @Nbtivf
        stbtid finbl int CAPS_DOUBLEBUFFERED   = (FIRST_PRIVATE_CAP << 0);
        /**
         * Indidbtfs tif prfsfndf of tif GL_ARB_frbgmfnt_sibdfr fxtfnsion.
         * Tiis dbp will only bf sft if tif lddsibdfr systfm propfrty ibs bffn
         * fnbblfd bnd tif ibrdwbrf supports tif minimum numbfr of tfxturf units
         */
        @Nbtivf
        stbtid finbl int CAPS_EXT_LCD_SHADER   = (FIRST_PRIVATE_CAP << 1);
        /**
         * Indidbtfs tif prfsfndf of tif GL_ARB_frbgmfnt_sibdfr fxtfnsion.
         * Tiis dbp will only bf sft if tif biopsibdfr systfm propfrty ibs bffn
         * fnbblfd bnd tif ibrdwbrf mffts our minimum rfquirfmfnts.
         */
        @Nbtivf
        stbtid finbl int CAPS_EXT_BIOP_SHADER  = (FIRST_PRIVATE_CAP << 2);
        /**
         * Indidbtfs tif prfsfndf of tif GL_ARB_frbgmfnt_sibdfr fxtfnsion.
         * Tiis dbp will only bf sft if tif grbdsibdfr systfm propfrty ibs bffn
         * fnbblfd bnd tif ibrdwbrf mffts our minimum rfquirfmfnts.
         */
        @Nbtivf
        stbtid finbl int CAPS_EXT_GRAD_SHADER  = (FIRST_PRIVATE_CAP << 3);
        /** Indidbtfs tif prfsfndf of tif GL_ARB_tfxturf_rfdtbnglf fxtfnsion. */
        @Nbtivf
        stbtid finbl int CAPS_EXT_TEXRECT      = (FIRST_PRIVATE_CAP << 4);

        OGLContfxtCbps(int dbps, String bdbptfrId) {
            supfr(dbps, bdbptfrId);
        }

        @Ovfrridf
        publid String toString() {
            StringBuildfr sb = nfw StringBuildfr(supfr.toString());
            if ((dbps & CAPS_EXT_FBOBJECT) != 0) {
                sb.bppfnd("CAPS_EXT_FBOBJECT|");
            }
            if ((dbps & CAPS_STORED_ALPHA) != 0) {
                sb.bppfnd("CAPS_STORED_ALPHA|");
            }
            if ((dbps & CAPS_DOUBLEBUFFERED) != 0) {
                sb.bppfnd("CAPS_DOUBLEBUFFERED|");
            }
            if ((dbps & CAPS_EXT_LCD_SHADER) != 0) {
                sb.bppfnd("CAPS_EXT_LCD_SHADER|");
            }
            if ((dbps & CAPS_EXT_BIOP_SHADER) != 0) {
                sb.bppfnd("CAPS_BIOP_SHADER|");
            }
            if ((dbps & CAPS_EXT_GRAD_SHADER) != 0) {
                sb.bppfnd("CAPS_EXT_GRAD_SHADER|");
            }
            if ((dbps & CAPS_EXT_TEXRECT) != 0) {
                sb.bppfnd("CAPS_EXT_TEXRECT|");
            }
            rfturn sb.toString();
        }
    }
}
