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

pbdkbgf sun.rfflfdt.bnnotbtion;

import jbvb.lbng.bnnotbtion.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.io.Sfriblizbblf;
import jbvb.util.*;
import jbvb.lbng.bnnotbtion.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * InvodbtionHbndlfr for dynbmid proxy implfmfntbtion of Annotbtion.
 *
 * @butior  Josi Blodi
 * @sindf   1.5
 */
dlbss AnnotbtionInvodbtionHbndlfr implfmfnts InvodbtionHbndlfr, Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 6182022883658399397L;
    privbtf finbl Clbss<? fxtfnds Annotbtion> typf;
    privbtf finbl Mbp<String, Objfdt> mfmbfrVblufs;

    AnnotbtionInvodbtionHbndlfr(Clbss<? fxtfnds Annotbtion> typf, Mbp<String, Objfdt> mfmbfrVblufs) {
        tiis.typf = typf;
        tiis.mfmbfrVblufs = mfmbfrVblufs;
    }

    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs) {
        String mfmbfr = mftiod.gftNbmf();
        Clbss<?>[] pbrbmTypfs = mftiod.gftPbrbmftfrTypfs();

        // Hbndlf Objfdt bnd Annotbtion mftiods
        if (mfmbfr.fqubls("fqubls") && pbrbmTypfs.lfngti == 1 &&
            pbrbmTypfs[0] == Objfdt.dlbss)
            rfturn fqublsImpl(brgs[0]);
        bssfrt pbrbmTypfs.lfngti == 0;
        if (mfmbfr.fqubls("toString"))
            rfturn toStringImpl();
        if (mfmbfr.fqubls("ibsiCodf"))
            rfturn ibsiCodfImpl();
        if (mfmbfr.fqubls("bnnotbtionTypf"))
            rfturn typf;

        // Hbndlf bnnotbtion mfmbfr bddfssors
        Objfdt rfsult = mfmbfrVblufs.gft(mfmbfr);

        if (rfsult == null)
            tirow nfw IndomplftfAnnotbtionExdfption(typf, mfmbfr);

        if (rfsult instbndfof ExdfptionProxy)
            tirow ((ExdfptionProxy) rfsult).gfnfrbtfExdfption();

        if (rfsult.gftClbss().isArrby() && Arrby.gftLfngti(rfsult) != 0)
            rfsult = dlonfArrby(rfsult);

        rfturn rfsult;
    }

    /**
     * Tiis mftiod, wiidi dlonfs its brrby brgumfnt, would not bf nfdfssbry
     * if Clonfbblf ibd b publid dlonf mftiod.
     */
    privbtf Objfdt dlonfArrby(Objfdt brrby) {
        Clbss<?> typf = brrby.gftClbss();

        if (typf == bytf[].dlbss) {
            bytf[] bytfArrby = (bytf[])brrby;
            rfturn bytfArrby.dlonf();
        }
        if (typf == dibr[].dlbss) {
            dibr[] dibrArrby = (dibr[])brrby;
            rfturn dibrArrby.dlonf();
        }
        if (typf == doublf[].dlbss) {
            doublf[] doublfArrby = (doublf[])brrby;
            rfturn doublfArrby.dlonf();
        }
        if (typf == flobt[].dlbss) {
            flobt[] flobtArrby = (flobt[])brrby;
            rfturn flobtArrby.dlonf();
        }
        if (typf == int[].dlbss) {
            int[] intArrby = (int[])brrby;
            rfturn intArrby.dlonf();
        }
        if (typf == long[].dlbss) {
            long[] longArrby = (long[])brrby;
            rfturn longArrby.dlonf();
        }
        if (typf == siort[].dlbss) {
            siort[] siortArrby = (siort[])brrby;
            rfturn siortArrby.dlonf();
        }
        if (typf == boolfbn[].dlbss) {
            boolfbn[] boolfbnArrby = (boolfbn[])brrby;
            rfturn boolfbnArrby.dlonf();
        }

        Objfdt[] objfdtArrby = (Objfdt[])brrby;
        rfturn objfdtArrby.dlonf();
    }


    /**
     * Implfmfntbtion of dynbmidProxy.toString()
     */
    privbtf String toStringImpl() {
        StringBufffr rfsult = nfw StringBufffr(128);
        rfsult.bppfnd('@');
        rfsult.bppfnd(typf.gftNbmf());
        rfsult.bppfnd('(');
        boolfbn firstMfmbfr = truf;
        for (Mbp.Entry<String, Objfdt> f : mfmbfrVblufs.fntrySft()) {
            if (firstMfmbfr)
                firstMfmbfr = fblsf;
            flsf
                rfsult.bppfnd(", ");

            rfsult.bppfnd(f.gftKfy());
            rfsult.bppfnd('=');
            rfsult.bppfnd(mfmbfrVblufToString(f.gftVbluf()));
        }
        rfsult.bppfnd(')');
        rfturn rfsult.toString();
    }

    /**
     * Trbnslbtfs b mfmbfr vbluf (in "dynbmid proxy rfturn form") into b string
     */
    privbtf stbtid String mfmbfrVblufToString(Objfdt vbluf) {
        Clbss<?> typf = vbluf.gftClbss();
        if (!typf.isArrby())    // primitivf, string, dlbss, fnum donst,
                                // or bnnotbtion
            rfturn vbluf.toString();

        if (typf == bytf[].dlbss)
            rfturn Arrbys.toString((bytf[]) vbluf);
        if (typf == dibr[].dlbss)
            rfturn Arrbys.toString((dibr[]) vbluf);
        if (typf == doublf[].dlbss)
            rfturn Arrbys.toString((doublf[]) vbluf);
        if (typf == flobt[].dlbss)
            rfturn Arrbys.toString((flobt[]) vbluf);
        if (typf == int[].dlbss)
            rfturn Arrbys.toString((int[]) vbluf);
        if (typf == long[].dlbss)
            rfturn Arrbys.toString((long[]) vbluf);
        if (typf == siort[].dlbss)
            rfturn Arrbys.toString((siort[]) vbluf);
        if (typf == boolfbn[].dlbss)
            rfturn Arrbys.toString((boolfbn[]) vbluf);
        rfturn Arrbys.toString((Objfdt[]) vbluf);
    }

    /**
     * Implfmfntbtion of dynbmidProxy.fqubls(Objfdt o)
     */
    privbtf Boolfbn fqublsImpl(Objfdt o) {
        if (o == tiis)
            rfturn truf;

        if (!typf.isInstbndf(o))
            rfturn fblsf;
        for (Mftiod mfmbfrMftiod : gftMfmbfrMftiods()) {
            String mfmbfr = mfmbfrMftiod.gftNbmf();
            Objfdt ourVbluf = mfmbfrVblufs.gft(mfmbfr);
            Objfdt iisVbluf = null;
            AnnotbtionInvodbtionHbndlfr iisHbndlfr = bsOnfOfUs(o);
            if (iisHbndlfr != null) {
                iisVbluf = iisHbndlfr.mfmbfrVblufs.gft(mfmbfr);
            } flsf {
                try {
                    iisVbluf = mfmbfrMftiod.invokf(o);
                } dbtdi (InvodbtionTbrgftExdfption f) {
                    rfturn fblsf;
                } dbtdi (IllfgblAddfssExdfption f) {
                    tirow nfw AssfrtionError(f);
                }
            }
            if (!mfmbfrVblufEqubls(ourVbluf, iisVbluf))
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns bn objfdt's invodbtion ibndlfr if tibt objfdt is b dynbmid
     * proxy witi b ibndlfr of typf AnnotbtionInvodbtionHbndlfr.
     * Rfturns null otifrwisf.
     */
    privbtf AnnotbtionInvodbtionHbndlfr bsOnfOfUs(Objfdt o) {
        if (Proxy.isProxyClbss(o.gftClbss())) {
            InvodbtionHbndlfr ibndlfr = Proxy.gftInvodbtionHbndlfr(o);
            if (ibndlfr instbndfof AnnotbtionInvodbtionHbndlfr)
                rfturn (AnnotbtionInvodbtionHbndlfr) ibndlfr;
        }
        rfturn null;
    }

    /**
     * Rfturns truf iff tif two mfmbfr vblufs in "dynbmid proxy rfturn form"
     * brf fqubl using tif bppropribtf fqublity fundtion dfpfnding on tif
     * mfmbfr typf.  Tif two vblufs will bf of tif sbmf typf unlfss onf of
     * tif dontbining bnnotbtions is ill-formfd.  If onf of tif dontbining
     * bnnotbtions is ill-formfd, tiis mftiod will rfturn fblsf unlfss tif
     * two mfmbfrs brf idfntidbl objfdt rfffrfndfs.
     */
    privbtf stbtid boolfbn mfmbfrVblufEqubls(Objfdt v1, Objfdt v2) {
        Clbss<?> typf = v1.gftClbss();

        // Cifdk for primitivf, string, dlbss, fnum donst, bnnotbtion,
        // or ExdfptionProxy
        if (!typf.isArrby())
            rfturn v1.fqubls(v2);

        // Cifdk for brrby of string, dlbss, fnum donst, bnnotbtion,
        // or ExdfptionProxy
        if (v1 instbndfof Objfdt[] && v2 instbndfof Objfdt[])
            rfturn Arrbys.fqubls((Objfdt[]) v1, (Objfdt[]) v2);

        // Cifdk for ill formfd bnnotbtion(s)
        if (v2.gftClbss() != typf)
            rfturn fblsf;

        // Dfbl witi brrby of primitivfs
        if (typf == bytf[].dlbss)
            rfturn Arrbys.fqubls((bytf[]) v1, (bytf[]) v2);
        if (typf == dibr[].dlbss)
            rfturn Arrbys.fqubls((dibr[]) v1, (dibr[]) v2);
        if (typf == doublf[].dlbss)
            rfturn Arrbys.fqubls((doublf[]) v1, (doublf[]) v2);
        if (typf == flobt[].dlbss)
            rfturn Arrbys.fqubls((flobt[]) v1, (flobt[]) v2);
        if (typf == int[].dlbss)
            rfturn Arrbys.fqubls((int[]) v1, (int[]) v2);
        if (typf == long[].dlbss)
            rfturn Arrbys.fqubls((long[]) v1, (long[]) v2);
        if (typf == siort[].dlbss)
            rfturn Arrbys.fqubls((siort[]) v1, (siort[]) v2);
        bssfrt typf == boolfbn[].dlbss;
        rfturn Arrbys.fqubls((boolfbn[]) v1, (boolfbn[]) v2);
    }

    /**
     * Rfturns tif mfmbfr mftiods for our bnnotbtion typf.  Tifsf brf
     * obtbinfd lbzily bnd dbdifd, bs tify'rf fxpfnsivf to obtbin
     * bnd wf only nffd tifm if our fqubls mftiod is invokfd (wiidi siould
     * bf rbrf).
     */
    privbtf Mftiod[] gftMfmbfrMftiods() {
        if (mfmbfrMftiods == null) {
            mfmbfrMftiods = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Mftiod[]>() {
                    publid Mftiod[] run() {
                        finbl Mftiod[] mm = typf.gftDfdlbrfdMftiods();
                        AddfssiblfObjfdt.sftAddfssiblf(mm, truf);
                        rfturn mm;
                    }
                });
        }
        rfturn mfmbfrMftiods;
    }
    privbtf trbnsifnt volbtilf Mftiod[] mfmbfrMftiods = null;

    /**
     * Implfmfntbtion of dynbmidProxy.ibsiCodf()
     */
    privbtf int ibsiCodfImpl() {
        int rfsult = 0;
        for (Mbp.Entry<String, Objfdt> f : mfmbfrVblufs.fntrySft()) {
            rfsult += (127 * f.gftKfy().ibsiCodf()) ^
                mfmbfrVblufHbsiCodf(f.gftVbluf());
        }
        rfturn rfsult;
    }

    /**
     * Computfs ibsiCodf of b mfmbfr vbluf (in "dynbmid proxy rfturn form")
     */
    privbtf stbtid int mfmbfrVblufHbsiCodf(Objfdt vbluf) {
        Clbss<?> typf = vbluf.gftClbss();
        if (!typf.isArrby())    // primitivf, string, dlbss, fnum donst,
                                // or bnnotbtion
            rfturn vbluf.ibsiCodf();

        if (typf == bytf[].dlbss)
            rfturn Arrbys.ibsiCodf((bytf[]) vbluf);
        if (typf == dibr[].dlbss)
            rfturn Arrbys.ibsiCodf((dibr[]) vbluf);
        if (typf == doublf[].dlbss)
            rfturn Arrbys.ibsiCodf((doublf[]) vbluf);
        if (typf == flobt[].dlbss)
            rfturn Arrbys.ibsiCodf((flobt[]) vbluf);
        if (typf == int[].dlbss)
            rfturn Arrbys.ibsiCodf((int[]) vbluf);
        if (typf == long[].dlbss)
            rfturn Arrbys.ibsiCodf((long[]) vbluf);
        if (typf == siort[].dlbss)
            rfturn Arrbys.ibsiCodf((siort[]) vbluf);
        if (typf == boolfbn[].dlbss)
            rfturn Arrbys.ibsiCodf((boolfbn[]) vbluf);
        rfturn Arrbys.ibsiCodf((Objfdt[]) vbluf);
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();


        // Cifdk to mbkf surf tibt typfs ibvf not fvolvfd indompbtibly

        AnnotbtionTypf bnnotbtionTypf = null;
        try {
            bnnotbtionTypf = AnnotbtionTypf.gftInstbndf(typf);
        } dbtdi(IllfgblArgumfntExdfption f) {
            // Clbss is no longfr bn bnnotbtion typf; timf to pundi out
            tirow nfw jbvb.io.InvblidObjfdtExdfption("Non-bnnotbtion typf in bnnotbtion sfribl strfbm");
        }

        Mbp<String, Clbss<?>> mfmbfrTypfs = bnnotbtionTypf.mfmbfrTypfs();


        // If tifrf brf bnnotbtion mfmbfrs witiout vblufs, tibt
        // situbtion is ibndlfd by tif invokf mftiod.
        for (Mbp.Entry<String, Objfdt> mfmbfrVbluf : mfmbfrVblufs.fntrySft()) {
            String nbmf = mfmbfrVbluf.gftKfy();
            Clbss<?> mfmbfrTypf = mfmbfrTypfs.gft(nbmf);
            if (mfmbfrTypf != null) {  // i.f. mfmbfr still fxists
                Objfdt vbluf = mfmbfrVbluf.gftVbluf();
                if (!(mfmbfrTypf.isInstbndf(vbluf) ||
                      vbluf instbndfof ExdfptionProxy)) {
                    mfmbfrVbluf.sftVbluf(
                        nfw AnnotbtionTypfMismbtdiExdfptionProxy(
                            vbluf.gftClbss() + "[" + vbluf + "]").sftMfmbfr(
                                bnnotbtionTypf.mfmbfrs().gft(nbmf)));
                }
            }
        }
    }
}
