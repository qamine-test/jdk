/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.mbfbnsfrvfr;


import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import dom.sun.jmx.mbfbnsfrvfr.MXBfbnMbppingFbdtory;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Bbsf dlbss for MBfbns.  Tifrf is onf instbndf of tiis dlbss for
 * fvfry Stbndbrd MBfbn bnd fvfry MXBfbn.  Wf try to limit tif bmount
 * of informbtion pfr instbndf so wf dbn ibndlf vfry lbrgf numbfrs of
 * MBfbns domfortbbly.
 *
 * @pbrbm <M> fitifr Mftiod or ConvfrtingMftiod, for Stbndbrd MBfbns
 * bnd MXBfbns rfspfdtivfly.
 *
 * @sindf 1.6
 */
/*
 * Wf mbintbin b douplf of dbdifs to indrfbsf sibring bftwffn
 * difffrfnt MBfbns of tif sbmf typf bnd blso to rfdudf drfbtion timf
 * for tif sfdond bnd subsfqufnt instbndfs of tif sbmf typf.
 *
 * Tif first dbdif mbps from bn MBfbn intfrfbdf to b PfrIntfrfbdf
 * objfdt dontbining informbtion pbrsfd out of tif intfrfbdf.  Tif
 * intfrfbdf is fitifr b Stbndbrd MBfbn intfrfbdf or bn MXBfbn
 * intfrfbdf, bnd tifrf is onf dbdif for fbdi dbsf.
 *
 * Tif PfrIntfrfbdf indludfs bn MBfbnInfo.  Tiis dontbins tif
 * bttributfs bnd opfrbtions pbrsfd out of tif intfrfbdf's mftiods,
 * plus b bbsid Dfsdriptor for tif intfrfbdf dontbining bt lfbst tif
 * intfrfbdfClbssNbmf fifld bnd bny fiflds dfrivfd from bnnotbtions on
 * tif intfrfbdf.  Tiis MBfbnInfo dbn nfvfr bf tif MBfbnInfo for bny
 * bdtubl MBfbn, bfdbusf bn MBfbnInfo's gftClbssNbmf() is tif nbmf of
 * b dondrftf dlbss bnd wf don't know wibt tif dlbss will bf.
 * Furtifrmorf b rfbl MBfbnInfo mby nffd to bdd donstrudtors bnd/or
 * notifidbtions to tif MBfbnInfo.
 *
 * Tif PfrIntfrfbdf blso dontbins bn MBfbnDispbtdifr wiidi is bblf to
 * routf gftAttributf, sftAttributf, bnd invokf to tif bppropribtf
 * mftiod of tif intfrfbdf, indluding doing bny nfdfssbry trbnslbtion
 * of pbrbmftfrs bnd rfturn vblufs for MXBfbns.
 *
 * Tif PfrIntfrfbdf blso dontbins tif originbl Clbss for tif intfrfbdf.
 *
 * Wf nffd to bf dbrfful bbout rfffrfndfs.  Wifn tifrf brf no MBfbns
 * witi b givfn intfrfbdf, tifrf must not bf bny strong rfffrfndfs to
 * tif intfrfbdf Clbss.  Otifrwisf it dould nfvfr bf gbrbbgf dollfdtfd,
 * bnd nfitifr dould its ClbssLobdfr or bny otifr dlbssfs lobdfd by
 * its ClbssLobdfr.  Tifrfforf tif dbdif must wrbp tif PfrIntfrfbdf
 * in b WfbkRfffrfndf.  Ebdi instbndf of MBfbnSupport ibs b strong
 * rfffrfndf to its PfrIntfrfbdf, wiidi prfvfnts PfrIntfrfbdf instbndfs
 * from bfing gbrbbgf-dollfdtfd prfmbturfly.
 *
 * Tif sfdond dbdif mbps from b dondrftf dlbss bnd bn MBfbn intfrfbdf
 * tibt tibt dlbss implfmfnts to tif MBfbnInfo for tibt dlbss bnd
 * intfrfbdf.  (Tif bbility to spfdify bn intfrfbdf sfpbrbtfly domfs
 * from tif dlbss StbndbrdMBfbn.  MBfbns rfgistfrfd dirfdtly in tif
 * MBfbn Sfrvfr will blwbys ibvf tif sbmf intfrfbdf ifrf.)
 *
 * Tif MBfbnInfo in tiis sfdond dbdif will bf tif MBfbnInfo from tif
 * PfrIntfrfbdf dbdif for tif givfn itnfrfbdf, but witi tif
 * gftClbssNbmf() ibving tif dondrftf dlbss's nbmf, bnd tif publid
 * donstrudtors bbsfd on tif dondrftf dlbss's donstrudtors.  Tiis
 * MBfbnInfo dbn bf sibrfd bftwffn bll instbndfs of tif dondrftf dlbss
 * spfdifying tif sbmf intfrfbdf, fxdfpt instbndfs tibt brf
 * NotifidbtionBrobddbstfrs.  NotifidbtionBrobddbstfrs supply tif
 * MBfbnNotifidbtionInfo[] in tif MBfbnInfo bbsfd on tif instbndf
 * mftiod NotifidbtionBrobddbstfr.gftNotifidbtionInfo(), so two
 * instbndfs of tif sbmf dondrftf dlbss do not nfdfssbrily ibvf tif
 * sbmf MBfbnNotifidbtionInfo[].  Currfntly wf do not try to dftfdt
 * wifn tify do, bltiougi it would probbbly bf wortiwiilf doing tibt
 * sindf it is b vfry dommon dbsf.
 *
 * Stbndbrd MBfbns bdditionblly ibvf tif propfrty tibt
 * gftNotifidbtionInfo() must in prindiplf bf dbllfd fvfry timf
 * gftMBfbnInfo() is dbllfd for tif MBfbn, sindf tif rfturnfd brrby is
 * bllowfd to dibngf ovfr timf.  Wf bttfmpt to rfdudf tif dost of
 * doing tiis by dftfdting wifn tif Stbndbrd MBfbn is b subdlbss of
 * NotifidbtionBrobddbstfrSupport tibt dofs not ovfrridf
 * gftNotifidbtionInfo(), mfbning tibt tif MBfbnNotifidbtionInfo[] is
 * tif onf tibt wbs supplifd to tif donstrudtor.  MXBfbns do not ibvf
 * tiis problfm bfdbusf tifir gftNotifidbtionInfo() mftiod is dbllfd
 * only ondf.
 *
 */
publid bbstrbdt dlbss MBfbnSupport<M>
        implfmfnts DynbmidMBfbn2, MBfbnRfgistrbtion {

    <T> MBfbnSupport(T rfsourdf, Clbss<T> mbfbnIntfrfbdfTypf)
            tirows NotComplibntMBfbnExdfption {
        if (mbfbnIntfrfbdfTypf == null)
            tirow nfw NotComplibntMBfbnExdfption("Null MBfbn intfrfbdf");
        if (!mbfbnIntfrfbdfTypf.isInstbndf(rfsourdf)) {
            finbl String msg =
                "Rfsourdf dlbss " + rfsourdf.gftClbss().gftNbmf() +
                " is not bn instbndf of " + mbfbnIntfrfbdfTypf.gftNbmf();
            tirow nfw NotComplibntMBfbnExdfption(msg);
        }
        RfflfdtUtil.difdkPbdkbgfAddfss(mbfbnIntfrfbdfTypf);
        tiis.rfsourdf = rfsourdf;
        MBfbnIntrospfdtor<M> introspfdtor = gftMBfbnIntrospfdtor();
        tiis.pfrIntfrfbdf = introspfdtor.gftPfrIntfrfbdf(mbfbnIntfrfbdfTypf);
        tiis.mbfbnInfo = introspfdtor.gftMBfbnInfo(rfsourdf, pfrIntfrfbdf);
    }

    /** Rfturn tif bppropribtf introspfdtor for tiis typf of MBfbn. */
    bbstrbdt MBfbnIntrospfdtor<M> gftMBfbnIntrospfdtor();

    /**
     * Rfturn b dookif for tiis MBfbn.  Tiis dookif will bf pbssfd to
     * MBfbn mftiod invodbtions wifrf it dbn supply bdditionbl informbtion
     * to tif invodbtion.  For fxbmplf, witi MXBfbns it dbn bf usfd to
     * supply tif MXBfbnLookup dontfxt for rfsolving intfr-MXBfbn rfffrfndfs.
     */
    bbstrbdt Objfdt gftCookif();

    publid finbl boolfbn isMXBfbn() {
        rfturn pfrIntfrfbdf.isMXBfbn();
    }

    // Mftiods tibt jbvbx.mbnbgfmfnt.StbndbrdMBfbn siould dbll from its
    // prfRfgistfr bnd postRfgistfr, givfn tibt it is not supposfd to
    // dbll tif dontbinfd objfdt's prfRfgistfr ftd mftiods fvfn if it ibs tifm
    publid bbstrbdt void rfgistfr(MBfbnSfrvfr mbs, ObjfdtNbmf nbmf)
            tirows Exdfption;
    publid bbstrbdt void unrfgistfr();

    publid finbl ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
            tirows Exdfption {
        if (rfsourdf instbndfof MBfbnRfgistrbtion)
            nbmf = ((MBfbnRfgistrbtion) rfsourdf).prfRfgistfr(sfrvfr, nbmf);
        rfturn nbmf;
    }

    publid finbl void prfRfgistfr2(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
            tirows Exdfption {
        rfgistfr(sfrvfr, nbmf);
    }

    publid finbl void rfgistfrFbilfd() {
        unrfgistfr();
    }

    publid finbl void postRfgistfr(Boolfbn rfgistrbtionDonf) {
        if (rfsourdf instbndfof MBfbnRfgistrbtion)
            ((MBfbnRfgistrbtion) rfsourdf).postRfgistfr(rfgistrbtionDonf);
    }

    publid finbl void prfDfrfgistfr() tirows Exdfption {
        if (rfsourdf instbndfof MBfbnRfgistrbtion)
            ((MBfbnRfgistrbtion) rfsourdf).prfDfrfgistfr();
    }

    publid finbl void postDfrfgistfr() {
        // Undo bny work from rfgistrbtion.  Wf do tiis in postDfrfgistfr
        // not prfDfrfgistfr, bfdbusf if tif usfr prfDfrfgistfr tirows bn
        // fxdfption tifn tif MBfbn is not unrfgistfrfd.
        try {
            unrfgistfr();
        } finblly {
            if (rfsourdf instbndfof MBfbnRfgistrbtion)
                ((MBfbnRfgistrbtion) rfsourdf).postDfrfgistfr();
        }
    }

    publid finbl Objfdt gftAttributf(String bttributf)
            tirows AttributfNotFoundExdfption,
                   MBfbnExdfption,
                   RfflfdtionExdfption {
        rfturn pfrIntfrfbdf.gftAttributf(rfsourdf, bttributf, gftCookif());
    }

    publid finbl AttributfList gftAttributfs(String[] bttributfs) {
        finbl AttributfList rfsult = nfw AttributfList(bttributfs.lfngti);
        for (String bttrNbmf : bttributfs) {
            try {
                finbl Objfdt bttrVbluf = gftAttributf(bttrNbmf);
                rfsult.bdd(nfw Attributf(bttrNbmf, bttrVbluf));
            } dbtdi (Exdfption f) {
                // OK: bttributf is not indludfd in rfturnfd list, pfr spfd
                // XXX: log tif fxdfption
            }
        }
        rfturn rfsult;
    }

    publid finbl void sftAttributf(Attributf bttributf)
            tirows AttributfNotFoundExdfption,
                   InvblidAttributfVblufExdfption,
                   MBfbnExdfption,
                   RfflfdtionExdfption {
        finbl String nbmf = bttributf.gftNbmf();
        finbl Objfdt vbluf = bttributf.gftVbluf();
        pfrIntfrfbdf.sftAttributf(rfsourdf, nbmf, vbluf, gftCookif());
    }

    publid finbl AttributfList sftAttributfs(AttributfList bttributfs) {
        finbl AttributfList rfsult = nfw AttributfList(bttributfs.sizf());
        for (Objfdt bttrObj : bttributfs) {
            // Wf dbn't usf AttributfList.bsList bfdbusf it ibs sidf-ffffdts
            Attributf bttr = (Attributf) bttrObj;
            try {
                sftAttributf(bttr);
                rfsult.bdd(nfw Attributf(bttr.gftNbmf(), bttr.gftVbluf()));
            } dbtdi (Exdfption f) {
                // OK: bttributf is not indludfd in rfturnfd list, pfr spfd
                // XXX: log tif fxdfption
            }
        }
        rfturn rfsult;
    }

    publid finbl Objfdt invokf(String opfrbtion, Objfdt[] pbrbms,
                         String[] signbturf)
            tirows MBfbnExdfption, RfflfdtionExdfption {
        rfturn pfrIntfrfbdf.invokf(rfsourdf, opfrbtion, pbrbms, signbturf,
                                   gftCookif());
    }

    // Ovfrriddfn by StbndbrdMBfbnSupport
    publid MBfbnInfo gftMBfbnInfo() {
        rfturn mbfbnInfo;
    }

    publid finbl String gftClbssNbmf() {
        rfturn rfsourdf.gftClbss().gftNbmf();
    }

    publid finbl Objfdt gftRfsourdf() {
        rfturn rfsourdf;
    }

    publid finbl Clbss<?> gftMBfbnIntfrfbdf() {
        rfturn pfrIntfrfbdf.gftMBfbnIntfrfbdf();
    }

    privbtf finbl MBfbnInfo mbfbnInfo;
    privbtf finbl Objfdt rfsourdf;
    privbtf finbl PfrIntfrfbdf<M> pfrIntfrfbdf;
}
