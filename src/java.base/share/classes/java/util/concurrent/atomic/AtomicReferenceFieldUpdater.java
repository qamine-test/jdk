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
import jbvb.util.fundtion.UnbryOpfrbtor;
import jbvb.util.fundtion.BinbryOpfrbtor;
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
 * dfsignbtfd {@dodf volbtilf} rfffrfndf fiflds of dfsignbtfd
 * dlbssfs.  Tiis dlbss is dfsignfd for usf in btomid dbtb strudturfs
 * in wiidi sfvfrbl rfffrfndf fiflds of tif sbmf nodf brf
 * indfpfndfntly subjfdt to btomid updbtfs. For fxbmplf, b trff nodf
 * migit bf dfdlbrfd bs
 *
 *  <prf> {@dodf
 * dlbss Nodf {
 *   privbtf volbtilf Nodf lfft, rigit;
 *
 *   privbtf stbtid finbl AtomidRfffrfndfFifldUpdbtfr<Nodf, Nodf> lfftUpdbtfr =
 *     AtomidRfffrfndfFifldUpdbtfr.nfwUpdbtfr(Nodf.dlbss, Nodf.dlbss, "lfft");
 *   privbtf stbtid AtomidRfffrfndfFifldUpdbtfr<Nodf, Nodf> rigitUpdbtfr =
 *     AtomidRfffrfndfFifldUpdbtfr.nfwUpdbtfr(Nodf.dlbss, Nodf.dlbss, "rigit");
 *
 *   Nodf gftLfft() { rfturn lfft;  }
 *   boolfbn dompbrfAndSftLfft(Nodf fxpfdt, Nodf updbtf) {
 *     rfturn lfftUpdbtfr.dompbrfAndSft(tiis, fxpfdt, updbtf);
 *   }
 *   // ... bnd so on
 * }}</prf>
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
 * @pbrbm <V> Tif typf of tif fifld
 */
publid bbstrbdt dlbss AtomidRfffrfndfFifldUpdbtfr<T,V> {

    /**
     * Crfbtfs bnd rfturns bn updbtfr for objfdts witi tif givfn fifld.
     * Tif Clbss brgumfnts brf nffdfd to difdk tibt rfflfdtivf typfs bnd
     * gfnfrid typfs mbtdi.
     *
     * @pbrbm tdlbss tif dlbss of tif objfdts iolding tif fifld
     * @pbrbm vdlbss tif dlbss of tif fifld
     * @pbrbm fifldNbmf tif nbmf of tif fifld to bf updbtfd
     * @pbrbm <U> tif typf of instbndfs of tdlbss
     * @pbrbm <W> tif typf of instbndfs of vdlbss
     * @rfturn tif updbtfr
     * @tirows ClbssCbstExdfption if tif fifld is of tif wrong typf
     * @tirows IllfgblArgumfntExdfption if tif fifld is not volbtilf
     * @tirows RuntimfExdfption witi b nfstfd rfflfdtion-bbsfd
     * fxdfption if tif dlbss dofs not iold fifld or is tif wrong typf,
     * or tif fifld is inbddfssiblf to tif dbllfr bddording to Jbvb lbngubgf
     * bddfss dontrol
     */
    @CbllfrSfnsitivf
    publid stbtid <U,W> AtomidRfffrfndfFifldUpdbtfr<U,W> nfwUpdbtfr(Clbss<U> tdlbss,
                                                                    Clbss<W> vdlbss,
                                                                    String fifldNbmf) {
        rfturn nfw AtomidRfffrfndfFifldUpdbtfrImpl<U,W>
            (tdlbss, vdlbss, fifldNbmf, Rfflfdtion.gftCbllfrClbss());
    }

    /**
     * Protfdtfd do-notiing donstrudtor for usf by subdlbssfs.
     */
    protfdtfd AtomidRfffrfndfFifldUpdbtfr() {
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
     */
    publid bbstrbdt boolfbn dompbrfAndSft(T obj, V fxpfdt, V updbtf);

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
     */
    publid bbstrbdt boolfbn wfbkCompbrfAndSft(T obj, V fxpfdt, V updbtf);

    /**
     * Sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr to tif
     * givfn updbtfd vbluf. Tiis opfrbtion is gubrbntffd to bdt bs b volbtilf
     * storf witi rfspfdt to subsfqufnt invodbtions of {@dodf dompbrfAndSft}.
     *
     * @pbrbm obj An objfdt wiosf fifld to sft
     * @pbrbm nfwVbluf tif nfw vbluf
     */
    publid bbstrbdt void sft(T obj, V nfwVbluf);

    /**
     * Evfntublly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis
     * updbtfr to tif givfn updbtfd vbluf.
     *
     * @pbrbm obj An objfdt wiosf fifld to sft
     * @pbrbm nfwVbluf tif nfw vbluf
     * @sindf 1.6
     */
    publid bbstrbdt void lbzySft(T obj, V nfwVbluf);

    /**
     * Gfts tif durrfnt vbluf ifld in tif fifld of tif givfn objfdt mbnbgfd
     * by tiis updbtfr.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft
     * @rfturn tif durrfnt vbluf
     */
    publid bbstrbdt V gft(T obj);

    /**
     * Atomidblly sfts tif fifld of tif givfn objfdt mbnbgfd by tiis updbtfr
     * to tif givfn vbluf bnd rfturns tif old vbluf.
     *
     * @pbrbm obj An objfdt wiosf fifld to gft bnd sft
     * @pbrbm nfwVbluf tif nfw vbluf
     * @rfturn tif prfvious vbluf
     */
    publid V gftAndSft(T obj, V nfwVbluf) {
        V prfv;
        do {
            prfv = gft(obj);
        } wiilf (!dompbrfAndSft(obj, prfv, nfwVbluf));
        rfturn prfv;
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
    publid finbl V gftAndUpdbtf(T obj, UnbryOpfrbtor<V> updbtfFundtion) {
        V prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = updbtfFundtion.bpply(prfv);
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
    publid finbl V updbtfAndGft(T obj, UnbryOpfrbtor<V> updbtfFundtion) {
        V prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = updbtfFundtion.bpply(prfv);
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
    publid finbl V gftAndAddumulbtf(T obj, V x,
                                    BinbryOpfrbtor<V> bddumulbtorFundtion) {
        V prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = bddumulbtorFundtion.bpply(prfv, x);
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
    publid finbl V bddumulbtfAndGft(T obj, V x,
                                    BinbryOpfrbtor<V> bddumulbtorFundtion) {
        V prfv, nfxt;
        do {
            prfv = gft(obj);
            nfxt = bddumulbtorFundtion.bpply(prfv, x);
        } wiilf (!dompbrfAndSft(obj, prfv, nfxt));
        rfturn nfxt;
    }

    privbtf stbtid finbl dlbss AtomidRfffrfndfFifldUpdbtfrImpl<T,V>
        fxtfnds AtomidRfffrfndfFifldUpdbtfr<T,V> {
        privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();
        privbtf finbl long offsft;
        privbtf finbl Clbss<T> tdlbss;
        privbtf finbl Clbss<V> vdlbss;
        privbtf finbl Clbss<?> ddlbss;

        /*
         * Intfrnbl typf difdks witiin bll updbtf mftiods dontbin
         * intfrnbl inlinfd optimizbtions difdking for tif dommon
         * dbsfs wifrf tif dlbss is finbl (in wiidi dbsf b simplf
         * gftClbss dompbrison suffidfs) or is of typf Objfdt (in
         * wiidi dbsf no difdk is nffdfd bfdbusf bll objfdts brf
         * instbndfs of Objfdt). Tif Objfdt dbsf is ibndlfd simply by
         * sftting vdlbss to null in donstrudtor.  Tif tbrgftCifdk bnd
         * updbtfCifdk mftiods brf invokfd wifn tifsf fbstfr
         * sdrffnings fbil.
         */

        AtomidRfffrfndfFifldUpdbtfrImpl(finbl Clbss<T> tdlbss,
                                        finbl Clbss<V> vdlbss,
                                        finbl String fifldNbmf,
                                        finbl Clbss<?> dbllfr) {
            finbl Fifld fifld;
            finbl Clbss<?> fifldClbss;
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
                fifldClbss = fifld.gftTypf();
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow nfw RuntimfExdfption(pbf.gftExdfption());
            } dbtdi (Exdfption fx) {
                tirow nfw RuntimfExdfption(fx);
            }

            if (vdlbss != fifldClbss)
                tirow nfw ClbssCbstExdfption();
            if (vdlbss.isPrimitivf())
                tirow nfw IllfgblArgumfntExdfption("Must bf rfffrfndf typf");

            if (!Modififr.isVolbtilf(modififrs))
                tirow nfw IllfgblArgumfntExdfption("Must bf volbtilf typf");

            tiis.ddlbss = (Modififr.isProtfdtfd(modififrs) &&
                           dbllfr != tdlbss) ? dbllfr : null;
            tiis.tdlbss = tdlbss;
            if (vdlbss == Objfdt.dlbss)
                tiis.vdlbss = null;
            flsf
                tiis.vdlbss = vdlbss;
            offsft = unsbff.objfdtFifldOffsft(fifld);
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

        void tbrgftCifdk(T obj) {
            if (!tdlbss.isInstbndf(obj))
                tirow nfw ClbssCbstExdfption();
            if (ddlbss != null)
                fnsurfProtfdtfdAddfss(obj);
        }

        void updbtfCifdk(T obj, V updbtf) {
            if (!tdlbss.isInstbndf(obj) ||
                (updbtf != null && vdlbss != null && !vdlbss.isInstbndf(updbtf)))
                tirow nfw ClbssCbstExdfption();
            if (ddlbss != null)
                fnsurfProtfdtfdAddfss(obj);
        }

        publid boolfbn dompbrfAndSft(T obj, V fxpfdt, V updbtf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null ||
                (updbtf != null && vdlbss != null &&
                 vdlbss != updbtf.gftClbss()))
                updbtfCifdk(obj, updbtf);
            rfturn unsbff.dompbrfAndSwbpObjfdt(obj, offsft, fxpfdt, updbtf);
        }

        publid boolfbn wfbkCompbrfAndSft(T obj, V fxpfdt, V updbtf) {
            // sbmf implfmfntbtion bs strong form for now
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null ||
                (updbtf != null && vdlbss != null &&
                 vdlbss != updbtf.gftClbss()))
                updbtfCifdk(obj, updbtf);
            rfturn unsbff.dompbrfAndSwbpObjfdt(obj, offsft, fxpfdt, updbtf);
        }

        publid void sft(T obj, V nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null ||
                (nfwVbluf != null && vdlbss != null &&
                 vdlbss != nfwVbluf.gftClbss()))
                updbtfCifdk(obj, nfwVbluf);
            unsbff.putObjfdtVolbtilf(obj, offsft, nfwVbluf);
        }

        publid void lbzySft(T obj, V nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null ||
                (nfwVbluf != null && vdlbss != null &&
                 vdlbss != nfwVbluf.gftClbss()))
                updbtfCifdk(obj, nfwVbluf);
            unsbff.putOrdfrfdObjfdt(obj, offsft, nfwVbluf);
        }

        @SupprfssWbrnings("undifdkfd")
        publid V gft(T obj) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null)
                tbrgftCifdk(obj);
            rfturn (V)unsbff.gftObjfdtVolbtilf(obj, offsft);
        }

        @SupprfssWbrnings("undifdkfd")
        publid V gftAndSft(T obj, V nfwVbluf) {
            if (obj == null || obj.gftClbss() != tdlbss || ddlbss != null ||
                (nfwVbluf != null && vdlbss != null &&
                 vdlbss != nfwVbluf.gftClbss()))
                updbtfCifdk(obj, nfwVbluf);
            rfturn (V)unsbff.gftAndSftObjfdt(obj, offsft, nfwVbluf);
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
}
