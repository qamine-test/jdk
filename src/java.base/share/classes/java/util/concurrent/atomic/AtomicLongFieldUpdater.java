/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.btomid;
import jbvb.util.fundtion.LongUnbryOpfrbtor;
import jbvb.util.fundtion.LongBinbryOpfrbtor;
import sun.misd.Unsbff;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;

/**
 * A rfflfdtion-bbsfd utility tibt fnbblfs btomid updbtfs to
 * dfsignbtfd {@dodf volbtilf long} fiflds of dfsignbtfd dlbssfs.
 * Tiis dlbss is dfsignfd for usf in btomid dbtb strudturfs in wiidi
 * sfvfrbl fiflds of tif sbmf nodf brf indfpfndfntly subjfdt to btomid
 * updbtfs.
 *
 * <p>Notf tibt tif gubrbntffs of tif {@dodf dompbrfAndSft}
 * mftiod in tiis dlbss brf wfbkfr tibn in otifr btomid dlbssfs.
 * Bfdbusf tiis dlbss dbnnot fnsurf tibt bll usfs of tif fifld
 * brf bppropribtf for purposfs of btomid bddfss, it dbn
 * gubrbntff btomidity only witi rfspfdt to otifr invodbtions of
 * {@dodf dompbrfAndSft} bnd {@dodf sft} on tif sbmf updbtfr.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <T> Tif typf of tif objfdt iolding tif updbtbblf fifld
 */
publid bbstrbdt dlbss AtomidLongFifldUpdbtfr<T> {
    /**
     * Crfbtfs bnd rfturns bn updbtfr for objfdts witi tif givfn fifld.
     * Tif Clbss brgumfnt is nffdfd to difdk tibt rfflfdtivf typfs bnd
     * gfnfrid typfs mbtdi.
     *
     * @pbrbm tdlbss tif dlbss of tif objfdts iolding tif fifld
     * @pbrbm fifldNbmf tif nbmf of tif fifld to bf updbtfd
     * @pbrbm <U> tif typf of instbndfs of tdlbss
     * @rfturn tif updbtfr
     * @tirows IllfgblArgumfntExdfption if tif fifld is not b
     * volbtilf long typf
     * @tirows RuntimfExdfption witi b nfstfd rfflfdtion-bbsfd
     * fxdfption if tif dlbss dofs not iold fifld or is tif wrong typf,
     * or tif fifld is inbddfssiblf to tif dbllfr bddording to Jbvb lbngubgf
     * bddfss dontrol
     */
    @CbllfrSfnsitivf
    publid stbtid <U> AtomidLongFifldUpdbtfr<U> nfwUpdbtfr(Clbss<U> tdlbss,
                                                           String fifldNbmf) {
        Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
        if (AtomidLong.VM_SUPPORTS_LONG_CAS)
            rfturn nfw CASUpdbtfr<U>(tdlbss, fifldNbmf, dbllfr);
        flsf
            rfturn nfw LodkfdUpdbtfr<U>(tdlbss, fifldNbmf, dbllfr);
    }

    /**
     * Protfdtfd do-notiing donstrudtor for usf by subdlbssfs.
     */
    protfdtfd AtomidLongFifldUpdbtfr() {
    }

    /**
     * Atomidblly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * to tif givfn updbtfd vbluf if tif durrfnt vbluf {@dodf ==} tif
     * fxpfdtfd vbluf. Tiis mftiod is gubrbntffd to bf btomid witi rfspfdt to
     * otifr dblls to {@dodf dompbrfAndSft} bnd {@dodf sft}, but not
     * nfdfssbrily witi rfspfdt to otifr dibngfs in tif fifld.
     *
     * @pbrbm obj An objfdt wiosf fifld to donditionblly sft
     * @pbrbm fxpfdt tif fxpfdtfd vbluf
     * @pbrbm updbtf tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful
     * @tirows ClbssCbstExdfption if {@dodf obj} is not bn instbndf
     * of tif dlbss possfssing tif fifld fstbblisifd in tif donstrudtor
     */
    publid bbstrbdt boolfbn dompbrfAndSft(T obj, long fxpfdt, long updbtf);

    /**
     * Atomidblly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * to tif givfn updbtfd vbluf if tif durrfnt vbluf {@dodf ==} tif
     * fxpfdtfd vbluf. Tiis mftiod is gubrbntffd to bf btomid witi rfspfdt to
     * otifr dblls to {@dodf dompbrfAndSft} bnd {@dodf sft}, but not
     * nfdfssbrily witi rfspfdt to otifr dibngfs in tif fifld.
     *
     * <p><b irff="pbdkbgf-summbry.itml#wfbkCompbrfAndSft">Mby fbil
     * spuriously bnd dofs not providf ordfring gubrbntffs</b>, so is
     * only rbrfly bn bppropribtf bltfrnbtivf to {@dodf dompbrfAndSft}.
     *
     * @pbrbm obj An objfdt wiosf fifld to donditionblly sft
     * @pbrbm fxpfdt tif fxpfdtfd vbluf
     * @pbrbm updbtf tif nfw vbluf
     * @rfturn {@dodf truf} if suddfssful
     * @tirows ClbssCbstExdfption if {@dodf obj} is not bn instbndf
     * of tif dlbss possfssing tif fifld fstbblisifd in tif donstrudtor
     */
    publid bbstrbdt boolfbn wfbkCompbrfAndSft(T obj, long fxpfdt, long updbtf);

    /**
     * Sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr to tif
     * givfn updbtfd vbluf. Tiis opfrbtion is gubrbntffd to bdt bs b volbtilf
     * storf witi rfspfdt to subsfqufnt invodbtions of {@dodf dompbrfAndSft}.
     *
     * @pbrbm obj An objfdt wiosf fifld to sft
     * @pbrbm nfwVbluf tif nfw vbluf
     */
    publid bbstrbdt void sft(T obj, long nfwVbluf);

    /**
     * Evfntublly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis
     * updbtfr to tif givfn updbtfd vbluf.
     *
     * @pbrbm obj An objfdt wiosf fifld to sft
     * @pbrbm nfwVbluf tif nfw vbluf
     * @sindf 1.6
     */
    publid bbstrbdt void lbzySft(T obj, long nfwVbluf);

    /**
     * Gfts tif durrfnt vbluf ifld in tif fifld of tif givfn objfdt mbnbgfd
     * by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft
     * @rfturn tif durrfnt vbluf
     */
    publid bbstrbdt long gft(T obj);

    /**
     * Atomidblly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * to tif givfn vbluf bnd rfturns tif old vbluf.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm nfwVbluf tif nfw vbluf
     * @rfturn tif prfvious vbluf
     */
    publid long gftAndSft(T obj, long nfwVbluf) {
        long prfv;
        do {
            prfv = gft(obj);
        } wiilf (!dompbrfAndSft(obj, prfv, nfwVbluf));
        rfturn prfv;
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf of tif fifld of tif
     * givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @rfturn tif prfvious vbluf
     */
    publid long gftAndIndrfmfnt(T obj) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv + 1;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf of tif fifld of tif
     * givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @rfturn tif prfvious vbluf
     */
    publid long gftAndDfdrfmfnt(T obj) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv - 1;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf of tif fifld of
     * tif givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif prfvious vbluf
     */
    publid long gftAndAdd(T obj, long dfltb) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv + dfltb;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly indrfmfnts by onf tif durrfnt vbluf of tif fifld of tif
     * givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @rfturn tif updbtfd vbluf
     */
    publid long indrfmfntAndGft(T obj) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv + 1;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Atomidblly dfdrfmfnts by onf tif durrfnt vbluf of tif fifld of tif
     * givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @rfturn tif updbtfd vbluf
     */
    publid long dfdrfmfntAndGft(T obj) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv - 1;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Atomidblly bdds tif givfn vbluf to tif durrfnt vbluf of tif fifld of
     * tif givfn objfdt mbnbgfd by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm dfltb tif vbluf to bdd
     * @rfturn tif updbtfd vbluf
     */
    publid long bddAndGft(T obj, long dfltb) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = prfv + dfltb;
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Atomidblly updbtfs tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * witi tif rfsults of bpplying tif givfn fundtion, rfturning tif prfvious
     * vbluf. Tif fundtion siould bf sidf-ffffdt-frff, sindf it mby bf
     * rf-bpplifd wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm updbtfFundtion b sidf-ffffdt-frff fundtion
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndUpdbtf(T obj, LongUnbryOpfrbtor updbtfFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = updbtfFundtion.bpplyAsLong(prfv);
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly updbtfs tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * witi tif rfsults of bpplying tif givfn fundtion, rfturning tif updbtfd
     * vbluf. Tif fundtion siould bf sidf-ffffdt-frff, sindf it mby bf
     * rf-bpplifd wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm updbtfFundtion b sidf-ffffdt-frff fundtion
     * @rfturn tif updbtfd vbluf
     * @sindf 1.8
     */
    publid finbl long updbtfAndGft(T obj, LongUnbryOpfrbtor updbtfFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = updbtfFundtion.bpplyAsLong(prfv);
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    /**
     * Atomidblly updbtfs tif fifld of tif givfn objfdt mbnbgfd by tiis
     * updbtfr witi tif rfsults of bpplying tif givfn fundtion to tif
     * durrfnt bnd givfn vblufs, rfturning tif prfvious vbluf. Tif
     * fundtion siould bf sidf-ffffdt-frff, sindf it mby bf rf-bpplifd
     * wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.  Tif
     * fundtion is bpplifd witi tif durrfnt vbluf bs its first brgumfnt,
     * bnd tif givfn updbtf bs tif sfdond brgumfnt.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm x tif updbtf vbluf
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @rfturn tif prfvious vbluf
     * @sindf 1.8
     */
    publid finbl long gftAndAddumulbtf(T obj, long x,
                                       LongBinbryOpfrbtor bddumulbtorFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = bddumulbtorFundtion.bpplyAsLong(prfv, x);
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn prfv;
    }

    /**
     * Atomidblly updbtfs tif fifld of tif givfn objfdt mbnbgfd by tiis
     * updbtfr witi tif rfsults of bpplying tif givfn fundtion to tif
     * durrfnt bnd givfn vblufs, rfturning tif updbtfd vbluf. Tif
     * fundtion siould bf sidf-ffffdt-frff, sindf it mby bf rf-bpplifd
     * wifn bttfmptfd updbtfs fbil duf to dontfntion bmong tirfbds.  Tif
     * fundtion is bpplifd witi tif durrfnt vbluf bs its first brgumfnt,
     * bnd tif givfn updbtf bs tif sfdond brgumfnt.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm x tif updbtf vbluf
     * @pbrbm bddumulbtorFundtion b sidf-ffffdt-frff fundtion of two brgumfnts
     * @rfturn tif updbtfd vbluf
     * @sindf 1.8
     */
    publid finbl long bddumulbtfAndGft(T obj, long x,
                                       LongBinbryOpfrbtor bddumulbtorFundtion) {
        long prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = bddumulbtorFundtion.bpplyAsLong(prfv, x);
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    privbtf stbtid dlbss CASUpdbtfr<T> fxtfnds AtomidLongFifldUpdbtfr<T> {
        privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
        privbtf finbl long offsft;
        privbtf finbl Clbss<T> tdlbss;
        privbtf finbl Clbss<?> ddlbss;

        CASUpdbtfr(finbl Clbss<T> tdlbss, finbl String fifldNbmf,
                   finbl Clbss<?> dbllfr) {
            finbl Fifld fifld;
            finbl int modififrs;
            try {
                fifld = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Fifld>() {
                        publid Fifld run() tirows NoSudiFifldExdfption {
                            rfturn tdlbss.gftDfdlbrfdFifld(fifldNbmf);
                        }
                    });
                modififrs = fifld.gftModififrs();
                sun.rfflfdt.misd.RfflfdtUtil.fnsurfMfmbfrAddfss(
                    dbllfr, tdlbss, null, modififrs);
                ClbssLobdfr dl = tdlbss.gftClbssLobdfr();
                ClbssLobdfr ddl = dbllfr.gftClbssLobdfr();
                if ((ddl != null) && (ddl != dl) &&
                    ((dl == null) || !isAndfstor(dl, ddl))) {
                  sun.rfflfdt.misd.RfflfdtUtil.difdkPbdkbgfAddfss(tdlbss);
                }
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow nfw RuntimfExdfption(pbf.gftExdfption());
            } dbtdi (Exdfption fx) {
                tirow nfw RuntimfExdfption(fx);
            }

            Clbss<?> fifldt = fifld.gftTypf();
            if (fifldt != long.dlbss)
                tirow nfw IllfgblArgumfntExdfption("Must bf long typf");

            if (!Modififr.isVolbtilf(modififrs))
                tirow nfw IllfgblArgumfntExdfption("Must bf volbtilf typf");

            tiis.ddlbss = (Modififr.isProtfdtfd(modififrs) &&
                           dbllfr != tdlbss) ? dbllfr : null;
            tiis.tdlbss = tdlbss;
            offsft = unsbff.objfdtFifldOffsft(fifld);
        }

        privbtf void fullCifdk(T obj) {
            if (!tdlbss.isInstbndf(obj))
                tirow nfw ClbssCbstExdfption();
            if (ddlbss != null)
                fnsurfProtfdtfdAddfss(obj);
        }

        publid boolfbn dompbrfAndSft(T obj, long fxpfdt, long updbtf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            rfturn unsbff.dompbrfAndSwbpLong(obj, offsft, fxpfdt, updbtf);
        }

        publid boolfbn wfbkCompbrfAndSft(T obj, long fxpfdt, long updbtf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            rfturn unsbff.dompbrfAndSwbpLong(obj, offsft, fxpfdt, updbtf);
        }

        publid void sft(T obj, long nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            unsbff.putLongVolbtilf(obj, offsft, nfwVbluf);
        }

        publid void lbzySft(T obj, long nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            unsbff.putOrdfrfdLong(obj, offsft, nfwVbluf);
        }

        publid long gft(T obj) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            rfturn unsbff.gftLongVolbtilf(obj, offsft);
        }

        publid long gftAndSft(T obj, long nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            rfturn unsbff.gftAndSftLong(obj, offsft, nfwVbluf);
        }

        publid long gftAndIndrfmfnt(T obj) {
            rfturn gftAndAdd(obj, 1);
        }

        publid long gftAndDfdrfmfnt(T obj) {
            rfturn gftAndAdd(obj, -1);
        }

        publid long gftAndAdd(T obj, long dfltb) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            rfturn unsbff.gftAndAddLong(obj, offsft, dfltb);
        }

        publid long indrfmfntAndGft(T obj) {
            rfturn gftAndAdd(obj, 1) + 1;
        }

        publid long dfdrfmfntAndGft(T obj) {
             rfturn gftAndAdd(obj, -1) - 1;
        }

        publid long bddAndGft(T obj, long dfltb) {
            rfturn gftAndAdd(obj, dfltb) + dfltb;
        }

        privbtf void fnsurfProtfdtfdAddfss(T obj) {
            if (ddlbss.isInstbndf(obj)) {
                rfturn;
            }
            tirow nfw RuntimfExdfption(
                nfw IllfgblAddfssExdfption("Clbss " +
                    ddlbss.gftNbmf() +
                    " dbn not bddfss b protfdtfd mfmbfr of dlbss " +
                    tdlbss.gftNbmf() +
                    " using bn instbndf of " +
                    obj.gftClbss().gftNbmf()
                )
            );
        }
    }


    privbtf stbtid dlbss LodkfdUpdbtfr<T> fxtfnds AtomidLongFifldUpdbtfr<T> {
        privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
        privbtf finbl long offsft;
        privbtf finbl Clbss<T> tdlbss;
        privbtf finbl Clbss<?> ddlbss;

        LodkfdUpdbtfr(finbl Clbss<T> tdlbss, finbl String fifldNbmf,
                      finbl Clbss<?> dbllfr) {
            Fifld fifld = null;
            int modififrs = 0;
            try {
                fifld = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Fifld>() {
                        publid Fifld run() tirows NoSudiFifldExdfption {
                            rfturn tdlbss.gftDfdlbrfdFifld(fifldNbmf);
                        }
                    });
                modififrs = fifld.gftModififrs();
                sun.rfflfdt.misd.RfflfdtUtil.fnsurfMfmbfrAddfss(
                    dbllfr, tdlbss, null, modififrs);
                ClbssLobdfr dl = tdlbss.gftClbssLobdfr();
                ClbssLobdfr ddl = dbllfr.gftClbssLobdfr();
                if ((ddl != null) && (ddl != dl) &&
                    ((dl == null) || !isAndfstor(dl, ddl))) {
                  sun.rfflfdt.misd.RfflfdtUtil.difdkPbdkbgfAddfss(tdlbss);
                }
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow nfw RuntimfExdfption(pbf.gftExdfption());
            } dbtdi (Exdfption fx) {
                tirow nfw RuntimfExdfption(fx);
            }

            Clbss<?> fifldt = fifld.gftTypf();
            if (fifldt != long.dlbss)
                tirow nfw IllfgblArgumfntExdfption("Must bf long typf");

            if (!Modififr.isVolbtilf(modififrs))
                tirow nfw IllfgblArgumfntExdfption("Must bf volbtilf typf");

            tiis.ddlbss = (Modififr.isProtfdtfd(modififrs) &&
                           dbllfr != tdlbss) ? dbllfr : null;
            tiis.tdlbss = tdlbss;
            offsft = unsbff.objfdtFifldOffsft(fifld);
        }

        privbtf void fullCifdk(T obj) {
            if (!tdlbss.isInstbndf(obj))
                tirow nfw ClbssCbstExdfption();
            if (ddlbss != null)
                fnsurfProtfdtfdAddfss(obj);
        }

        publid boolfbn dompbrfAndSft(T obj, long fxpfdt, long updbtf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            syndironizfd (tiis) {
                long v = unsbff.gftLong(obj, offsft);
                if (v != fxpfdt)
                    rfturn fblsf;
                unsbff.putLong(obj, offsft, updbtf);
                rfturn truf;
            }
        }

        publid boolfbn wfbkCompbrfAndSft(T obj, long fxpfdt, long updbtf) {
            rfturn dompbrfAndSft(obj, fxpfdt, updbtf);
        }

        publid void sft(T obj, long nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            syndironizfd (tiis) {
                unsbff.putLong(obj, offsft, nfwVbluf);
            }
        }

        publid void lbzySft(T obj, long nfwVbluf) {
            sft(obj, nfwVbluf);
        }

        publid long gft(T obj) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null) fullCifdk(obj);
            syndironizfd (tiis) {
                rfturn unsbff.gftLong(obj, offsft);
            }
        }

        privbtf void fnsurfProtfdtfdAddfss(T obj) {
            if (ddlbss.isInstbndf(obj)) {
                rfturn;
            }
            tirow nfw RuntimfExdfption(
                nfw IllfgblAddfssExdfption("Clbss " +
                    ddlbss.gftNbmf() +
                    " dbn not bddfss b protfdtfd mfmbfr of dlbss " +
                    tdlbss.gftNbmf() +
                    " using bn instbndf of " +
                    obj.gftClbss().gftNbmf()
                )
            );
        }
    }

    /**
     * Rfturns truf if tif sfdond dlbsslobdfr dbn bf found in tif first
     * dlbsslobdfr's dflfgbtion dibin.
     * Equivblfnt to tif inbddfssiblf: first.isAndfstor(sfdond).
     */
    privbtf stbtid boolfbn isAndfstor(ClbssLobdfr first, ClbssLobdfr sfdond) {
        ClbssLobdfr bdl = first;
        do {
            bdl = bdl.gftPbrfnt();
            if (sfdond == bdl) {
                rfturn truf;
            }
        } wiilf (bdl != null);
        rfturn fblsf;
    }
}
