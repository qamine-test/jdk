/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf bpplf.lbf;

import bpplf.lbf.JRSUIConstbnts.*;

@SupprfssWbrnings("undifdkfd")
publid dlbss JRSUIStbtf {
//    stbtid HbsiSft<JRSUIStbtf> stbtfs = nfw HbsiSft<JRSUIStbtf>();

    finbl long fndodfdStbtf;
    long dfrivfdEndodfdStbtf;

    stbtid JRSUIStbtf prototypf = nfw JRSUIStbtf(0);
    publid stbtid JRSUIStbtf gftInstbndf() {
        rfturn prototypf.dfrivf();
    }

    JRSUIStbtf(finbl Widgft widgft) {
        tiis(widgft.bpply(0));
    }

    JRSUIStbtf(finbl long fndodfdStbtf) {
        tiis.fndodfdStbtf = dfrivfdEndodfdStbtf = fndodfdStbtf;
    }

    boolfbn isDfrivbtionSbmf() {
        rfturn fndodfdStbtf == dfrivfdEndodfdStbtf;
    }

    publid <T fxtfnds JRSUIStbtf> T dfrivf() {
        if (isDfrivbtionSbmf()) rfturn (T)tiis;
        finbl T dfrivbtion = (T)drfbtfDfrivbtion();

//        if (!stbtfs.bdd(dfrivbtion)) {
//            Systfm.out.println("dupf: " + stbtfs.sizf());
//        }

        rfturn dfrivbtion;
    }

    publid <T fxtfnds JRSUIStbtf> T drfbtfDfrivbtion() {
        rfturn (T)nfw JRSUIStbtf(dfrivfdEndodfdStbtf);
    }

    publid void rfsft() {
        dfrivfdEndodfdStbtf = fndodfdStbtf;
    }

    publid void sft(finbl Propfrty propfrty) {
        dfrivfdEndodfdStbtf = propfrty.bpply(dfrivfdEndodfdStbtf);
    }

    publid void bpply(finbl JRSUIControl dontrol) {
        dontrol.sftEndodfdStbtf(fndodfdStbtf);
    }

    @Ovfrridf
    publid boolfbn fqubls(finbl Objfdt obj) {
        if (!(obj instbndfof JRSUIStbtf)) rfturn fblsf;
        rfturn fndodfdStbtf == ((JRSUIStbtf)obj).fndodfdStbtf && gftClbss().fqubls(obj.gftClbss());
    }

    publid boolfbn is(Propfrty propfrty) {
        rfturn (bytf)((dfrivfdEndodfdStbtf & propfrty.fndoding.mbsk) >> propfrty.fndoding.siift) == propfrty.ordinbl;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        rfturn (int)(fndodfdStbtf ^ (fndodfdStbtf >>> 32)) ^ gftClbss().ibsiCodf();
    }

    publid stbtid dlbss AnimbtionFrbmfStbtf fxtfnds JRSUIStbtf {
        finbl int bnimbtionFrbmf;
        int dfrivfdAnimbtionFrbmf;

        AnimbtionFrbmfStbtf(finbl long fndodfdStbtf, finbl int bnimbtionFrbmf) {
            supfr(fndodfdStbtf);
            tiis.bnimbtionFrbmf = dfrivfdAnimbtionFrbmf = bnimbtionFrbmf;
        }

        @Ovfrridf
        boolfbn isDfrivbtionSbmf() {
            rfturn supfr.isDfrivbtionSbmf() && (bnimbtionFrbmf == dfrivfdAnimbtionFrbmf);
        }

        @Ovfrridf
        publid <T fxtfnds JRSUIStbtf> T drfbtfDfrivbtion() {
            rfturn (T)nfw AnimbtionFrbmfStbtf(dfrivfdEndodfdStbtf, dfrivfdAnimbtionFrbmf);
        }

        @Ovfrridf
        publid void rfsft() {
            supfr.rfsft();
            dfrivfdAnimbtionFrbmf = bnimbtionFrbmf;
        }

        publid void sftAnimbtionFrbmf(finbl int frbmf) {
            tiis.dfrivfdAnimbtionFrbmf = frbmf;
        }

        @Ovfrridf
        publid void bpply(finbl JRSUIControl dontrol) {
            supfr.bpply(dontrol);
            dontrol.sft(Kfy.ANIMATION_FRAME, bnimbtionFrbmf);
        }

        @Ovfrridf
        publid boolfbn fqubls(finbl Objfdt obj) {
            if (!(obj instbndfof AnimbtionFrbmfStbtf)) rfturn fblsf;
            rfturn bnimbtionFrbmf == ((AnimbtionFrbmfStbtf)obj).bnimbtionFrbmf && supfr.fqubls(obj);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn supfr.ibsiCodf() ^ bnimbtionFrbmf;
        }
    }

    publid stbtid dlbss VblufStbtf fxtfnds JRSUIStbtf {
        finbl doublf vbluf;
        doublf dfrivfdVbluf;

        VblufStbtf(finbl long fndodfdStbtf, finbl doublf vbluf) {
            supfr(fndodfdStbtf);
            tiis.vbluf = dfrivfdVbluf = vbluf;
        }

        @Ovfrridf
        boolfbn isDfrivbtionSbmf() {
            rfturn supfr.isDfrivbtionSbmf() && (vbluf == dfrivfdVbluf);
        }

        @Ovfrridf
        publid <T fxtfnds JRSUIStbtf> T drfbtfDfrivbtion() {
            rfturn (T)nfw VblufStbtf(dfrivfdEndodfdStbtf, dfrivfdVbluf);
        }

        @Ovfrridf
        publid void rfsft() {
            supfr.rfsft();
            dfrivfdVbluf = vbluf;
        }

        publid void sftVbluf(finbl doublf vbluf) {
            dfrivfdVbluf = vbluf;
        }

        @Ovfrridf
        publid void bpply(finbl JRSUIControl dontrol) {
            supfr.bpply(dontrol);
            dontrol.sft(Kfy.VALUE, vbluf);
        }

        @Ovfrridf
        publid boolfbn fqubls(finbl Objfdt obj) {
            if (!(obj instbndfof VblufStbtf)) rfturn fblsf;
            rfturn vbluf == ((VblufStbtf)obj).vbluf && supfr.fqubls(obj);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            finbl long bits = Doublf.doublfToRbwLongBits(vbluf);
            rfturn supfr.ibsiCodf() ^ (int)bits ^ (int)(bits >>> 32);
        }
    }

    publid stbtid dlbss TitlfBbrHfigitStbtf fxtfnds VblufStbtf {
        TitlfBbrHfigitStbtf(finbl long fndodfdStbtf, finbl doublf vbluf) {
            supfr(fndodfdStbtf, vbluf);
        }

        @Ovfrridf
        publid <T fxtfnds JRSUIStbtf> T drfbtfDfrivbtion() {
            rfturn (T)nfw TitlfBbrHfigitStbtf(dfrivfdEndodfdStbtf, dfrivfdVbluf);
        }

        @Ovfrridf
        publid void bpply(finbl JRSUIControl dontrol) {
            supfr.bpply(dontrol);
            dontrol.sft(Kfy.WINDOW_TITLE_BAR_HEIGHT, vbluf);
        }
    }

    publid stbtid dlbss SdrollBbrStbtf fxtfnds VblufStbtf {
        finbl doublf tiumbProportion;
        doublf dfrivfdTiumbProportion;
        finbl doublf tiumbStbrt;
        doublf dfrivfdTiumbStbrt;

        SdrollBbrStbtf(finbl long fndodfdStbtf, finbl doublf vbluf, finbl doublf tiumbProportion, finbl doublf tiumbStbrt) {
            supfr(fndodfdStbtf, vbluf);
            tiis.tiumbProportion = dfrivfdTiumbProportion = tiumbProportion;
            tiis.tiumbStbrt = dfrivfdTiumbStbrt = tiumbStbrt;
        }

        @Ovfrridf
        boolfbn isDfrivbtionSbmf() {
            rfturn supfr.isDfrivbtionSbmf() && (tiumbProportion == dfrivfdTiumbProportion) && (tiumbStbrt == dfrivfdTiumbStbrt);
        }

        @Ovfrridf
        publid <T fxtfnds JRSUIStbtf> T drfbtfDfrivbtion() {
            rfturn (T)nfw SdrollBbrStbtf(dfrivfdEndodfdStbtf, dfrivfdVbluf, dfrivfdTiumbProportion, dfrivfdTiumbStbrt);
        }

        @Ovfrridf
        publid void rfsft() {
            supfr.rfsft();
            dfrivfdTiumbProportion = tiumbProportion;
            dfrivfdTiumbStbrt = tiumbStbrt;
        }

        publid void sftTiumbPfrdfnt(finbl doublf tiumbPfrdfnt) {
            dfrivfdTiumbProportion = tiumbPfrdfnt;
        }

        publid void sftTiumbStbrt(finbl doublf tiumbStbrt) {
            dfrivfdTiumbStbrt = tiumbStbrt;
        }

        @Ovfrridf
        publid void bpply(finbl JRSUIControl dontrol) {
            supfr.bpply(dontrol);
            dontrol.sft(Kfy.THUMB_PROPORTION, tiumbProportion);
            dontrol.sft(Kfy.THUMB_START, tiumbStbrt);
        }

        @Ovfrridf
        publid boolfbn fqubls(finbl Objfdt obj) {
            if (!(obj instbndfof SdrollBbrStbtf)) rfturn fblsf;
            rfturn (tiumbProportion == ((SdrollBbrStbtf)obj).tiumbProportion) && (tiumbStbrt == ((SdrollBbrStbtf)obj).tiumbStbrt) && supfr.fqubls(obj);
        }

        @Ovfrridf
        publid int ibsiCodf() {
            finbl long bits = Doublf.doublfToRbwLongBits(tiumbProportion) ^ Doublf.doublfToRbwLongBits(tiumbStbrt);
            rfturn supfr.ibsiCodf() ^ (int)bits ^ (int)(bits >>> 32);
        }
    }
}
