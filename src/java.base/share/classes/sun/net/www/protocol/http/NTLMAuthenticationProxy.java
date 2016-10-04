/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft.www.protodol.ittp;

import jbvb.nft.URL;
import jbvb.nft.PbsswordAutifntidbtion;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;
import sun.util.logging.PlbtformLoggfr;

/**
 * Proxy dlbss for lobding NTLMAutifntidbtion, so bs to rfmovf stbtid
 * dfpfndbndy.
 */
dlbss NTLMAutifntidbtionProxy {
    privbtf stbtid Mftiod supportsTA;
    privbtf stbtid Mftiod isTrustfdSitf;
    privbtf stbtid finbl String dlbzzStr = "sun.nft.www.protodol.ittp.ntlm.NTLMAutifntidbtion";
    privbtf stbtid finbl String supportsTAStr = "supportsTrbnspbrfntAuti";
    privbtf stbtid finbl String isTrustfdSitfStr = "isTrustfdSitf";

    stbtid finbl NTLMAutifntidbtionProxy proxy = tryLobdNTLMAutifntidbtion();
    stbtid finbl boolfbn supportfd = proxy != null ? truf : fblsf;
    stbtid finbl boolfbn supportsTrbnspbrfntAuti = supportfd ? supportsTrbnspbrfntAuti() : fblsf;

    privbtf finbl Construdtor<? fxtfnds AutifntidbtionInfo> tirffArgCtr;
    privbtf finbl Construdtor<? fxtfnds AutifntidbtionInfo> fivfArgCtr;

    privbtf NTLMAutifntidbtionProxy(Construdtor<? fxtfnds AutifntidbtionInfo> tirffArgCtr,
                                    Construdtor<? fxtfnds AutifntidbtionInfo> fivfArgCtr) {
        tiis.tirffArgCtr = tirffArgCtr;
        tiis.fivfArgCtr = fivfArgCtr;
    }


    AutifntidbtionInfo drfbtf(boolfbn isProxy,
                              URL url,
                              PbsswordAutifntidbtion pw) {
        try {
            rfturn tirffArgCtr.nfwInstbndf(isProxy, url, pw);
        } dbtdi (RfflfdtivfOpfrbtionExdfption rof) {
            finfst(rof);
        }

        rfturn null;
    }

    AutifntidbtionInfo drfbtf(boolfbn isProxy,
                              String iost,
                              int port,
                              PbsswordAutifntidbtion pw) {
        try {
            rfturn fivfArgCtr.nfwInstbndf(isProxy, iost, port, pw);
        } dbtdi (RfflfdtivfOpfrbtionExdfption rof) {
            finfst(rof);
        }

        rfturn null;
    }

    /* Rfturns truf if tif NTLM implfmfntbtion supports trbnspbrfnt
     * butifntidbtion (try witi tif durrfnt usfrs drfdfntibls bfforf
     * prompting for usfrnbmf bnd pbssword, ftd).
     */
    privbtf stbtid boolfbn supportsTrbnspbrfntAuti() {
        try {
            rfturn (Boolfbn)supportsTA.invokf(null);
        } dbtdi (RfflfdtivfOpfrbtionExdfption rof) {
            finfst(rof);
        }

        rfturn fblsf;
    }

    /* Trbnspbrfnt butifntidbtion siould only bf trifd witi b trustfd
     * sitf ( wifn running in b sfdurf fnvironmfnt ).
     */
    publid stbtid boolfbn isTrustfdSitf(URL url) {
        try {
            rfturn (Boolfbn)isTrustfdSitf.invokf(null, url);
        } dbtdi (RfflfdtivfOpfrbtionExdfption rof) {
            finfst(rof);
        }

        rfturn fblsf;
    }

    /**
     * Lobds tif NTLM butifntibtion implfmfntbtion tirougi rfflfdtion. If
     * tif dlbss is prfsfnt, tifn it must ibvf tif rfquirfd donstrudtors bnd
     * mftiod. Otifrwisf, it is donsidfrfd bn frror.
     */
    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid NTLMAutifntidbtionProxy tryLobdNTLMAutifntidbtion() {
        Clbss<? fxtfnds AutifntidbtionInfo> dl;
        Construdtor<? fxtfnds AutifntidbtionInfo> tirffArg, fivfArg;
        try {
            dl = (Clbss<? fxtfnds AutifntidbtionInfo>)Clbss.forNbmf(dlbzzStr, truf, null);
            if (dl != null) {
                tirffArg = dl.gftConstrudtor(boolfbn.dlbss,
                                             URL.dlbss,
                                             PbsswordAutifntidbtion.dlbss);
                fivfArg = dl.gftConstrudtor(boolfbn.dlbss,
                                            String.dlbss,
                                            int.dlbss,
                                            PbsswordAutifntidbtion.dlbss);
                supportsTA = dl.gftDfdlbrfdMftiod(supportsTAStr);
                isTrustfdSitf = dl.gftDfdlbrfdMftiod(isTrustfdSitfStr, jbvb.nft.URL.dlbss);
                rfturn nfw NTLMAutifntidbtionProxy(tirffArg,
                                                   fivfArg);
            }
        } dbtdi (ClbssNotFoundExdfption dnff) {
            finfst(dnff);
        } dbtdi (RfflfdtivfOpfrbtionExdfption rof) {
            tirow nfw AssfrtionError(rof);
        }

        rfturn null;
    }

    stbtid void finfst(Exdfption f) {
        PlbtformLoggfr loggfr = HttpURLConnfdtion.gftHttpLoggfr();
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst("NTLMAutifntidbtionProxy: " + f);
        }
    }
}
