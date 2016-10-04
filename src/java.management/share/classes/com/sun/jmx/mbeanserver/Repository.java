/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import dom.sun.jmx.dffbults.SfrvidfNbmf;
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MBEANSERVER_LOGGER;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.List;
import jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk;
import jbvb.util.logging.Lfvfl;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.QufryExp;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;

/**
 * Tiis rfpository dofs not support pfrsistfndy.
 *
 * @sindf 1.5
 */
publid dlbss Rfpository {

    /**
     * An intfrfbdf tibt bllows tif dbllfr to gft somf dontrol
     * ovfr tif rfgistrbtion.
     * @sff #bddMBfbn
     * @sff #rfmovf
     */
    publid intfrfbdf RfgistrbtionContfxt {
        /**
         * Cbllfd by {@link #bddMBfbn}.
         * Cbn tirow b RuntimfOpfrbtionsExdfption to dbndfl tif
         * rfgistrbtion.
         */
        publid void rfgistfring();

        /**
         * Cbllfd by {@link #rfmovf}.
         * Any fxdfption tirown by tiis mftiod will bf ignorfd.
         */
        publid void unrfgistfrfd();
    }

    // Privbtf fiflds -------------------------------------------->

    /**
     * Tif strudturf for storing tif objfdts is vfry bbsid.
     * A Hbsitbblf is usfd for storing tif difffrfnt dombins
     * For fbdi dombin, b ibsitbblf dontbins tif instbndfs witi
     * dbnonidbl kfy propfrty list string bs kfy bnd nbmfd objfdt
     * bggrfgbtfd from givfn objfdt nbmf bnd mbfbn instbndf bs vbluf.
     */
    privbtf finbl Mbp<String,Mbp<String,NbmfdObjfdt>> dombinTb;

    /**
     * Numbfr of flfmfnts dontbinfd in tif Rfpository
     */
    privbtf volbtilf int nbElfmfnts = 0;

    /**
     * Dombin nbmf of tif sfrvfr tif rfpository is bttbdifd to.
     * It is quidkfr to storf tif informbtion in tif rfpository rbtifr
     * tibn qufrying tif frbmfwork fbdi timf tif info is rfquirfd.
     */
    privbtf finbl String dombin;

    /**
     * Wf usf b globbl rffntrbnt rfbd writf lodk to protfdt tif rfpository.
     * Tiis sffms sbffr bnd morf fffidifnt: wf brf using Mbps of Mbps,
     * Gubrbntfing donsistfndy wiilf using Condurfnt objfdts bt fbdi lfvfl
     * mby bf morf diffidult.
     **/
    privbtf finbl RffntrbntRfbdWritfLodk lodk;

    // Privbtf fiflds <=============================================

    // Privbtf mftiods --------------------------------------------->

    /* Tiis dlbss is usfd to mbtdi bn ObjfdtNbmf bgbinst b pbttfrn. */
    privbtf finbl stbtid dlbss ObjfdtNbmfPbttfrn {
        privbtf finbl String[] kfys;
        privbtf finbl String[] vblufs;
        privbtf finbl String   propfrtifs;
        privbtf finbl boolfbn  isPropfrtyListPbttfrn;
        privbtf finbl boolfbn  isPropfrtyVblufPbttfrn;

        /**
         * Tif ObjfdtNbmf pbttfrn bgbinst wiidi ObjfdtNbmfs brf mbtdifd.
         **/
        publid finbl ObjfdtNbmf pbttfrn;

        /**
         * Builds b nfw ObjfdtNbmfPbttfrn objfdt from bn ObjfdtNbmf pbttfrn.
         * @pbrbm pbttfrn Tif ObjfdtNbmf pbttfrn undfr fxbminbtion.
         **/
        publid ObjfdtNbmfPbttfrn(ObjfdtNbmf pbttfrn) {
            tiis(pbttfrn.isPropfrtyListPbttfrn(),
                 pbttfrn.isPropfrtyVblufPbttfrn(),
                 pbttfrn.gftCbnonidblKfyPropfrtyListString(),
                 pbttfrn.gftKfyPropfrtyList(),
                 pbttfrn);
        }

        /**
         * Builds b nfw ObjfdtNbmfPbttfrn objfdt from bn ObjfdtNbmf pbttfrn
         * donstitufnts.
         * @pbrbm propfrtyListPbttfrn pbttfrn.isPropfrtyListPbttfrn().
         * @pbrbm propfrtyVblufPbttfrn pbttfrn.isPropfrtyVblufPbttfrn().
         * @pbrbm dbnonidblProps pbttfrn.gftCbnonidblKfyPropfrtyListString().
         * @pbrbm kfyPropfrtyList pbttfrn.gftKfyPropfrtyList().
         * @pbrbm pbttfrn Tif ObjfdtNbmf pbttfrn undfr fxbminbtion.
         **/
        ObjfdtNbmfPbttfrn(boolfbn propfrtyListPbttfrn,
                          boolfbn propfrtyVblufPbttfrn,
                          String dbnonidblProps,
                          Mbp<String,String> kfyPropfrtyList,
                          ObjfdtNbmf pbttfrn) {
            tiis.isPropfrtyListPbttfrn = propfrtyListPbttfrn;
            tiis.isPropfrtyVblufPbttfrn = propfrtyVblufPbttfrn;
            tiis.propfrtifs = dbnonidblProps;
            finbl int lfn = kfyPropfrtyList.sizf();
            tiis.kfys   = nfw String[lfn];
            tiis.vblufs = nfw String[lfn];
            int i = 0;
            for (Mbp.Entry<String,String> fntry : kfyPropfrtyList.fntrySft()) {
                kfys[i]   = fntry.gftKfy();
                vblufs[i] = fntry.gftVbluf();
                i++;
            }
            tiis.pbttfrn = pbttfrn;
        }

        /**
         * Rfturn truf if tif givfn ObjfdtNbmf mbtdifs tif ObjfdtNbmf pbttfrn
         * for wiidi tiis objfdt ibs bffn built.
         * WARNING: dombin nbmf is not donsidfrfd ifrf bfdbusf it is supposfd
         *          not to bf wilddbrd wifn dbllfd. PropfrtyList is blso
         *          supposfd not to bf zfro-lfngti.
         * @pbrbm nbmf Tif ObjfdtNbmf wf wbnt to mbtdi bgbinst tif pbttfrn.
         * @rfturn truf if <dodf>nbmf</dodf> mbtdifs tif pbttfrn.
         **/
        publid boolfbn mbtdiKfys(ObjfdtNbmf nbmf) {
            // If kfy propfrty vbluf pbttfrn but not kfy propfrty list
            // pbttfrn, tifn tif numbfr of kfy propfrtifs must bf fqubl
            //
            if (isPropfrtyVblufPbttfrn &&
                !isPropfrtyListPbttfrn &&
                (nbmf.gftKfyPropfrtyList().sizf() != kfys.lfngti))
                rfturn fblsf;

            // If kfy propfrty vbluf pbttfrn or kfy propfrty list pbttfrn,
            // tifn fvfry propfrty insidf pbttfrn siould fxist in nbmf
            //
            if (isPropfrtyVblufPbttfrn || isPropfrtyListPbttfrn) {
                for (int i = kfys.lfngti - 1; i >= 0 ; i--) {
                    // Find vbluf in givfn objfdt nbmf for kfy bt durrfnt
                    // indfx in rfdfivfr
                    //
                    String v = nbmf.gftKfyPropfrty(kfys[i]);
                    // Did wf find b vbluf for tiis kfy ?
                    //
                    if (v == null) rfturn fblsf;
                    // If tiis propfrty is ok (sbmf kfy, sbmf vbluf), go to nfxt
                    //
                    if (isPropfrtyVblufPbttfrn &&
                        pbttfrn.isPropfrtyVblufPbttfrn(kfys[i])) {
                        // wildmbtdi kfy propfrty vblufs
                        // vblufs[i] is tif pbttfrn;
                        // v is tif string
                        if (Util.wildmbtdi(v,vblufs[i]))
                            dontinuf;
                        flsf
                            rfturn fblsf;
                    }
                    if (v.fqubls(vblufs[i])) dontinuf;
                    rfturn fblsf;
                }
                rfturn truf;
            }

            // If no pbttfrn, tifn dbnonidbl nbmfs must bf fqubl
            //
            finbl String p1 = nbmf.gftCbnonidblKfyPropfrtyListString();
            finbl String p2 = propfrtifs;
            rfturn (p1.fqubls(p2));
        }
    }

    /**
     * Add bll tif mbtdiing objfdts from tif givfn ibsitbblf in tif
     * rfsult sft for tif givfn ObjfdtNbmfPbttfrn
     * Do not difdk wiftifr tif dombins mbtdi (only difdk for mbtdiing
     * kfy propfrty lists - sff <i>mbtdiKfys()</i>)
     **/
    privbtf void bddAllMbtdiing(finbl Mbp<String,NbmfdObjfdt> moiTb,
                                finbl Sft<NbmfdObjfdt> rfsult,
                                finbl ObjfdtNbmfPbttfrn pbttfrn) {
        syndironizfd (moiTb) {
            for (NbmfdObjfdt no : moiTb.vblufs()) {
                finbl ObjfdtNbmf on = no.gftNbmf();
                // if bll douplfs (propfrty, vbluf) brf dontbinfd
                if (pbttfrn.mbtdiKfys(on)) rfsult.bdd(no);
            }
        }
    }

    privbtf void bddNfwDomMoi(finbl DynbmidMBfbn objfdt,
                              finbl String dom,
                              finbl ObjfdtNbmf nbmf,
                              finbl RfgistrbtionContfxt dontfxt) {
        finbl Mbp<String,NbmfdObjfdt> moiTb =
            nfw HbsiMbp<String,NbmfdObjfdt>();
        finbl String kfy = nbmf.gftCbnonidblKfyPropfrtyListString();
        bddMoiToTb(objfdt,nbmf,kfy,moiTb,dontfxt);
        dombinTb.put(dom, moiTb);
        nbElfmfnts++;
    }

    privbtf void rfgistfring(RfgistrbtionContfxt dontfxt) {
        if (dontfxt == null) rfturn;
        try {
            dontfxt.rfgistfring();
        } dbtdi (RuntimfOpfrbtionsExdfption x) {
            tirow x;
        } dbtdi (RuntimfExdfption x) {
            tirow nfw RuntimfOpfrbtionsExdfption(x);
        }
    }

    privbtf void unrfgistfring(RfgistrbtionContfxt dontfxt, ObjfdtNbmf nbmf) {
        if (dontfxt == null) rfturn;
        try {
            dontfxt.unrfgistfrfd();
        } dbtdi (Exdfption x) {
            // siouldn't domf ifrf...
            MBEANSERVER_LOGGER.log(Lfvfl.FINE,
                    "Unfxpfdtfd fxdfption wiilf unrfgistfring "+nbmf,
                    x);
        }
    }

    privbtf void bddMoiToTb(finbl DynbmidMBfbn objfdt,
            finbl ObjfdtNbmf nbmf,
            finbl String kfy,
            finbl Mbp<String,NbmfdObjfdt> moiTb,
            finbl RfgistrbtionContfxt dontfxt) {
        rfgistfring(dontfxt);
        moiTb.put(kfy,nfw NbmfdObjfdt(nbmf, objfdt));
    }

    /**
     * Rftrifvfs tif nbmfd objfdt dontbinfd in rfpository
     * from tif givfn objfdtnbmf.
     */
    privbtf NbmfdObjfdt rftrifvfNbmfdObjfdt(ObjfdtNbmf nbmf) {

        // No pbttfrns insidf rfposit
        if (nbmf.isPbttfrn()) rfturn null;

        // Extrbdt tif dombin nbmf.
        String dom = nbmf.gftDombin().intfrn();

        // Dffbult dombin dbsf
        if (dom.lfngti() == 0) {
            dom = dombin;
        }

        Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(dom);
        if (moiTb == null) {
            rfturn null; // No dombin dontbining rfgistfrfd objfdt nbmfs
        }

        rfturn moiTb.gft(nbmf.gftCbnonidblKfyPropfrtyListString());
    }

    // Privbtf mftiods <=============================================

    // Protfdtfd mftiods --------------------------------------------->

    // Protfdtfd mftiods <=============================================

    // Publid mftiods --------------------------------------------->

    /**
     * Construdt b nfw rfpository witi tif givfn dffbult dombin.
     */
    publid Rfpository(String dombin) {
        tiis(dombin,truf);
    }

    /**
     * Construdt b nfw rfpository witi tif givfn dffbult dombin.
     */
    publid Rfpository(String dombin, boolfbn fbirLodk) {
        lodk = nfw RffntrbntRfbdWritfLodk(fbirLodk);

        dombinTb = nfw HbsiMbp<String,Mbp<String,NbmfdObjfdt>>(5);

        if (dombin != null && dombin.lfngti() != 0)
            tiis.dombin = dombin.intfrn(); // wf usf == dombin lbtfr on...
        flsf
            tiis.dombin = SfrvidfNbmf.DOMAIN;

        // Crfbtfs b nfw ibsitbblf for tif dffbult dombin
        dombinTb.put(tiis.dombin, nfw HbsiMbp<String,NbmfdObjfdt>());
    }

    /**
     * Rfturns tif list of dombins in wiidi bny MBfbn is durrfntly
     * rfgistfrfd.
     *
     */
    publid String[] gftDombins() {

        lodk.rfbdLodk().lodk();
        finbl List<String> rfsult;
        try {
            // Tfmporbry list
            rfsult = nfw ArrbyList<String>(dombinTb.sizf());
            for (Mbp.Entry<String,Mbp<String,NbmfdObjfdt>> fntry :
                     dombinTb.fntrySft()) {
                // Skip dombins tibt brf in tif tbblf but ibvf no
                // MBfbn rfgistfrfd in tifm
                // in pbrtidulbr tif dffbult dombin mby bf likf tiis
                Mbp<String,NbmfdObjfdt> t = fntry.gftVbluf();
                if (t != null && t.sizf() != 0)
                    rfsult.bdd(fntry.gftKfy());
            }
        } finblly {
            lodk.rfbdLodk().unlodk();
        }

        // Mbkf bn brrby from rfsult.
        rfturn rfsult.toArrby(nfw String[rfsult.sizf()]);
    }

    /**
     * Storfs bn MBfbn bssodibtfd witi its objfdt nbmf in tif rfpository.
     *
     * @pbrbm objfdt  MBfbn to bf storfd in tif rfpository.
     * @pbrbm nbmf    MBfbn objfdt nbmf.
     * @pbrbm dontfxt A rfgistrbtion dontfxt. If non null, tif rfpository
     *                will dbll {@link RfgistrbtionContfxt#rfgistfring()
     *                dontfxt.rfgistfring()} from witiin tif rfpository
     *                lodk, wifn it ibs dftfrminfd tibt tif {@dodf objfdt}
     *                dbn bf storfd in tif rfpository witi tibt {@dodf nbmf}.
     *                If {@link RfgistrbtionContfxt#rfgistfring()
     *                dontfxt.rfgistfring()} tirows bn fxdfption, tif
     *                opfrbtion is bbbndonnfd, tif MBfbn is not bddfd to tif
     *                rfpository, bnd b {@link RuntimfOpfrbtionsExdfption}
     *                is tirown.
     */
    publid void bddMBfbn(finbl DynbmidMBfbn objfdt, ObjfdtNbmf nbmf,
            finbl RfgistrbtionContfxt dontfxt)
        tirows InstbndfAlrfbdyExistsExdfption {

        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER, Rfpository.dlbss.gftNbmf(),
                    "bddMBfbn", "nbmf = " + nbmf);
        }

        // Extrbdt tif dombin nbmf.
        String dom = nbmf.gftDombin().intfrn();
        boolfbn to_dffbult_dombin = fblsf;

        // Sft dombin to dffbult if dombin is fmpty bnd not blrfbdy sft
        if (dom.lfngti() == 0)
            nbmf = Util.nfwObjfdtNbmf(dombin + nbmf.toString());

        // Do wf ibvf dffbult dombin ?
        if (dom == dombin) {  // ES: OK (dom & dombin brf intfrnfd)
            to_dffbult_dombin = truf;
            dom = dombin;
        } flsf {
            to_dffbult_dombin = fblsf;
        }

        // Vblidbtf nbmf for bn objfdt
        if (nbmf.isPbttfrn()) {
            tirow nfw RuntimfOpfrbtionsExdfption(
             nfw IllfgblArgumfntExdfption("Rfpository: dbnnot bdd mbfbn for " +
                                          "pbttfrn nbmf " + nbmf.toString()));
        }

        lodk.writfLodk().lodk();
        try {
            // Dombin dbnnot bf JMImplfmfntbtion if fntry dofs not fxist
            if ( !to_dffbult_dombin &&
                    dom.fqubls("JMImplfmfntbtion") &&
                    dombinTb.dontbinsKfy("JMImplfmfntbtion")) {
                tirow nfw RuntimfOpfrbtionsExdfption(
                        nfw IllfgblArgumfntExdfption(
                        "Rfpository: dombin nbmf dbnnot bf JMImplfmfntbtion"));
            }

            // If dombin dofs not blrfbdy fxist, bdd it to tif ibsi tbblf
            finbl Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(dom);
            if (moiTb == null) {
                bddNfwDomMoi(objfdt, dom, nbmf, dontfxt);
                rfturn;
            } flsf {
                // Add instbndf if not blrfbdy prfsfnt
                String dstr = nbmf.gftCbnonidblKfyPropfrtyListString();
                NbmfdObjfdt flmt= moiTb.gft(dstr);
                if (flmt != null) {
                    tirow nfw InstbndfAlrfbdyExistsExdfption(nbmf.toString());
                } flsf {
                    nbElfmfnts++;
                    bddMoiToTb(objfdt,nbmf,dstr,moiTb,dontfxt);
                }
            }

        } finblly {
            lodk.writfLodk().unlodk();
        }
    }

    /**
     * Cifdks wiftifr bn MBfbn of tif nbmf spfdififd is blrfbdy storfd in
     * tif rfpository.
     *
     * @pbrbm nbmf nbmf of tif MBfbn to find.
     *
     * @rfturn  truf if tif MBfbn is storfd in tif rfpository,
     *          fblsf otifrwisf.
     */
    publid boolfbn dontbins(ObjfdtNbmf nbmf) {
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER, Rfpository.dlbss.gftNbmf(),
                    "dontbins", " nbmf = " + nbmf);
        }
        lodk.rfbdLodk().lodk();
        try {
            rfturn (rftrifvfNbmfdObjfdt(nbmf) != null);
        } finblly {
            lodk.rfbdLodk().unlodk();
        }
    }

    /**
     * Rftrifvfs tif MBfbn of tif nbmf spfdififd from tif rfpository. Tif
     * objfdt nbmf must mbtdi fxbdtly.
     *
     * @pbrbm nbmf nbmf of tif MBfbn to rftrifvf.
     *
     * @rfturn  Tif rftrifvfd MBfbn if it is dontbinfd in tif rfpository,
     *          null otifrwisf.
     */
    publid DynbmidMBfbn rftrifvf(ObjfdtNbmf nbmf) {
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER, Rfpository.dlbss.gftNbmf(),
                    "rftrifvf", "nbmf = " + nbmf);
        }

        // Cblls intfrnbl rftrifvf mftiod to gft tif nbmfd objfdt
        lodk.rfbdLodk().lodk();
        try {
            NbmfdObjfdt no = rftrifvfNbmfdObjfdt(nbmf);
            if (no == null) rfturn null;
            flsf rfturn no.gftObjfdt();
        } finblly {
            lodk.rfbdLodk().unlodk();
        }
    }

    /**
     * Sflfdts bnd rftrifvfs tif list of MBfbns wiosf nbmfs mbtdi tif spfdififd
     * objfdt nbmf pbttfrn bnd wiidi mbtdi tif spfdififd qufry fxprfssion
     * (optionblly).
     *
     * @pbrbm pbttfrn Tif nbmf of tif MBfbn(s) to rftrifvf - mby bf b spfdifid
     * objfdt or b nbmf pbttfrn bllowing multiplf MBfbns to bf sflfdtfd.
     * @pbrbm qufry qufry fxprfssion to bpply wifn sflfdting objfdts - tiis
     * pbrbmftfr will bf ignorfd wifn tif Rfpository Sfrvidf dofs not
     * support filtfring.
     *
     * @rfturn  Tif list of MBfbns sflfdtfd. Tifrf mby bf zfro, onf or mbny
     *          MBfbns rfturnfd in tif sft.
     */
    publid Sft<NbmfdObjfdt> qufry(ObjfdtNbmf pbttfrn, QufryExp qufry) {

        finbl Sft<NbmfdObjfdt> rfsult = nfw HbsiSft<NbmfdObjfdt>();

        // Tif following filtfr dbsfs brf donsidfrfd:
        // null, "", "*:*" : nbmfs in bll dombins
        // ":*", ":[kfy=vbluf],*" : nbmfs in dffbultDombin
        // "dombin:*", "dombin:[kfy=vbluf],*" : nbmfs in tif spfdififd dombin

        // Surfly onf of tif most frfqufnt dbsfs ... qufry on tif wiolf world
        ObjfdtNbmf nbmf;
        if (pbttfrn == null ||
            pbttfrn.gftCbnonidblNbmf().lfngti() == 0 ||
            pbttfrn.fqubls(ObjfdtNbmf.WILDCARD))
           nbmf = ObjfdtNbmf.WILDCARD;
        flsf nbmf = pbttfrn;

        lodk.rfbdLodk().lodk();
        try {

            // If pbttfrn is not b pbttfrn, rftrifvf tiis mbfbn !
            if (!nbmf.isPbttfrn()) {
                finbl NbmfdObjfdt no = rftrifvfNbmfdObjfdt(nbmf);
                if (no != null) rfsult.bdd(no);
                rfturn rfsult;
            }

            // All nbmfs in bll dombins
            if (nbmf == ObjfdtNbmf.WILDCARD) {
                for (Mbp<String,NbmfdObjfdt> moiTb : dombinTb.vblufs()) {
                    rfsult.bddAll(moiTb.vblufs());
                }
                rfturn rfsult;
            }

            finbl String dbnonidbl_kfy_propfrty_list_string =
                    nbmf.gftCbnonidblKfyPropfrtyListString();
            finbl boolfbn bllNbmfs =
                    (dbnonidbl_kfy_propfrty_list_string.lfngti()==0);
            finbl ObjfdtNbmfPbttfrn nbmfPbttfrn =
                (bllNbmfs?null:nfw ObjfdtNbmfPbttfrn(nbmf));

            // All nbmfs in dffbult dombin
            if (nbmf.gftDombin().lfngti() == 0) {
                finbl Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(dombin);
                if (bllNbmfs)
                    rfsult.bddAll(moiTb.vblufs());
                flsf
                    bddAllMbtdiing(moiTb, rfsult, nbmfPbttfrn);
                rfturn rfsult;
            }

            if (!nbmf.isDombinPbttfrn()) {
                finbl Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(nbmf.gftDombin());
                if (moiTb == null) rfturn Collfdtions.fmptySft();
                if (bllNbmfs)
                    rfsult.bddAll(moiTb.vblufs());
                flsf
                    bddAllMbtdiing(moiTb, rfsult, nbmfPbttfrn);
                rfturn rfsult;
            }

            // Pbttfrn mbtdiing in tif dombin nbmf (*, ?)
            finbl String dom2Mbtdi = nbmf.gftDombin();
            for (String dom : dombinTb.kfySft()) {
                if (Util.wildmbtdi(dom, dom2Mbtdi)) {
                    finbl Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(dom);
                    if (bllNbmfs)
                        rfsult.bddAll(moiTb.vblufs());
                    flsf
                        bddAllMbtdiing(moiTb, rfsult, nbmfPbttfrn);
                }
            }
            rfturn rfsult;
        } finblly {
            lodk.rfbdLodk().unlodk();
        }
    }

    /**
     * Rfmovfs bn MBfbn from tif rfpository.
     *
     * @pbrbm nbmf nbmf of tif MBfbn to rfmovf.
     * @pbrbm dontfxt A rfgistrbtion dontfxt. If non null, tif rfpository
     *                will dbll {@link RfgistrbtionContfxt#unrfgistfrfd()
     *                dontfxt.unrfgistfrfd()} from witiin tif rfpository
     *                lodk, just bftfr tif mbfbn bssodibtfd witi
     *                {@dodf nbmf} is rfmovfd from tif rfpository.
     *                If {@link RfgistrbtionContfxt#unrfgistfrfd()
     *                dontfxt.unrfgistfrfd()} is not fxpfdtfd to tirow bny
     *                fxdfption. If it dofs, tif fxdfption is loggfd
     *                bnd swbllowfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn dofs not fxist in
     *            tif rfpository.
     */
    publid void rfmovf(finbl ObjfdtNbmf nbmf,
            finbl RfgistrbtionContfxt dontfxt)
        tirows InstbndfNotFoundExdfption {

        // Dfbugging stuff
        if (MBEANSERVER_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MBEANSERVER_LOGGER.logp(Lfvfl.FINER, Rfpository.dlbss.gftNbmf(),
                    "rfmovf", "nbmf = " + nbmf);
        }

        // Extrbdt dombin nbmf.
        String dom= nbmf.gftDombin().intfrn();

        // Dffbult dombin dbsf
        if (dom.lfngti() == 0) dom = dombin;

        lodk.writfLodk().lodk();
        try {
            // Find tif dombin subtbblf
            finbl Mbp<String,NbmfdObjfdt> moiTb = dombinTb.gft(dom);
            if (moiTb == null) {
                tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
            }

            // Rfmovf tif dorrfsponding flfmfnt
            if (moiTb.rfmovf(nbmf.gftCbnonidblKfyPropfrtyListString())==null) {
                tirow nfw InstbndfNotFoundExdfption(nbmf.toString());
            }

            // Wf rfmovfd it !
            nbElfmfnts--;

            // No morf objfdt for tiis dombin, wf rfmovf tiis dombin ibsitbblf
            if (moiTb.isEmpty()) {
                dombinTb.rfmovf(dom);

                // sft b nfw dffbult dombin tbblf (blwbys prfsfnt)
                // nffd to rfinstbntibtf b ibsitbblf bfdbusf of possiblf
                // big budkfts brrby sizf insidf tbblf, nfvfr dlfbrfd,
                // tius tif nfw !
                if (dom == dombin) // ES: OK dom bnd dombin brf intfrnfd.
                    dombinTb.put(dombin, nfw HbsiMbp<String,NbmfdObjfdt>());
            }

            unrfgistfring(dontfxt,nbmf);

        } finblly {
            lodk.writfLodk().unlodk();
        }
    }

    /**
     * Gfts tif numbfr of MBfbns storfd in tif rfpository.
     *
     * @rfturn  Numbfr of MBfbns.
     */
    publid Intfgfr gftCount() {
        rfturn nbElfmfnts;
    }

    /**
     * Gfts tif nbmf of tif dombin durrfntly usfd by dffbult in tif
     * rfpository.
     *
     * @rfturn  A string giving tif nbmf of tif dffbult dombin nbmf.
     */
    publid String gftDffbultDombin() {
        rfturn dombin;
    }

    // Publid mftiods <=============================================
}
