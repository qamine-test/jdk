/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.dbtbtrbnsffr;

import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Toolkit;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.FlbvorMbp;
import jbvb.bwt.dbtbtrbnsffr.FlbvorTbblf;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;
import jbvb.bwt.dbtbtrbnsffr.UnsupportfdFlbvorExdfption;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Rfbdfr;
import jbvb.io.SfqufndfInputStrfbm;
import jbvb.io.StringRfbdfr;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption;
import jbvb.nio.dibrsft.StbndbrdCibrsfts;
import jbvb.nio.dibrsft.UnsupportfdCibrsftExdfption;

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.ProtfdtionDombin;

import jbvb.util.*;

import sun.util.logging.PlbtformLoggfr;

import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.bwt.imbgf.WritbblfRbstfr;
import jbvb.bwt.imbgf.ColorModfl;

import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.ImbgfRfbdPbrbm;
import jbvbx.imbgfio.ImbgfWritfr;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;

import jbvbx.imbgfio.spi.ImbgfWritfrSpi;

import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;

import sun.bwt.imbgf.ImbgfRfprfsfntbtion;
import sun.bwt.imbgf.ToolkitImbgf;

import jbvb.io.FilfPfrmission;
import jbvb.util.strfbm.Strfbm;


/**
 * Providfs b sft of fundtions to bf sibrfd bmong tif DbtbFlbvor dlbss bnd
 * plbtform-spfdifid dbtb trbnsffr implfmfntbtions.
 *
 * Tif dondfpt of "flbvors" bnd "nbtivfs" is fxtfndfd to indludf "formbts",
 * wiidi brf tif numfrid vblufs Win32 bnd X11 usf to fxprfss pbrtidulbr dbtb
 * typfs. Likf FlbvorMbp, wiidi providfs gftNbtivfsForFlbvors(DbtbFlbvor[]) bnd
 * gftFlbvorsForNbtivfs(String[]) fundtions, DbtbTrbnsffrfr providfs b sft
 * of gftFormbtsFor(Trbnsffrbblf|Flbvor|Flbvors) bnd
 * gftFlbvorsFor(Formbt|Formbts) fundtions.
 *
 * Also providfd brf fundtions for trbnslbting b Trbnsffrbblf into b bytf
 * brrby, givfn b sourdf DbtbFlbvor bnd b tbrgft formbt, bnd for trbnslbting
 * b bytf brrby or InputStrfbm into bn Objfdt, givfn b sourdf formbt bnd
 * b tbrgft DbtbFlbvor.
 *
 * @butior Dbvid Mfndfnibll
 * @butior Dbnilb Sinopblnikov
 *
 * @sindf 1.3.1
 */
publid bbstrbdt dlbss DbtbTrbnsffrfr {
    /**
     * Tif <dodf>DbtbFlbvor</dodf> rfprfsfnting b Jbvb tfxt fndoding String
     * fndodfd in UTF-8, wifrf
     * <prf>
     *     rfprfsfntbtionClbss = [B
     *     mimfTypf            = "bpplidbtion/x-jbvb-tfxt-fndoding"
     * </prf>
     */
    publid stbtid finbl DbtbFlbvor jbvbTfxtEndodingFlbvor;

    /**
     * Lbzy initiblizbtion of Stbndbrd Endodings.
     */
    privbtf stbtid dlbss StbndbrdEndodingsHoldfr {
        privbtf stbtid finbl SortfdSft<String> stbndbrdEndodings = lobd();

        privbtf stbtid SortfdSft<String> lobd() {
            finbl Compbrbtor<String> dompbrbtor =
                    nfw CibrsftCompbrbtor(IndfxfdCompbrbtor.SELECT_WORST);
            finbl SortfdSft<String> tfmpSft = nfw TrffSft<>(dompbrbtor);
            tfmpSft.bdd("US-ASCII");
            tfmpSft.bdd("ISO-8859-1");
            tfmpSft.bdd("UTF-8");
            tfmpSft.bdd("UTF-16BE");
            tfmpSft.bdd("UTF-16LE");
            tfmpSft.bdd("UTF-16");
            tfmpSft.bdd(Cibrsft.dffbultCibrsft().nbmf());
            rfturn Collfdtions.unmodifibblfSortfdSft(tfmpSft);
        }
    }

    /**
     * Trbdks wiftifr b pbrtidulbr tfxt/* MIME typf supports tif dibrsft
     * pbrbmftfr. Tif Mbp is initiblizfd witi bll of tif stbndbrd MIME typfs
     * listfd in tif DbtbFlbvor.sflfdtBfstTfxtFlbvor mftiod dommfnt. Additionbl
     * fntrifs mby bf bddfd during tif liff of tif JRE for tfxt/<otifr> typfs.
     */
    privbtf stbtid finbl Mbp<String, Boolfbn> tfxtMIMESubtypfCibrsftSupport;

    /**
     * A dollfdtion of bll nbtivfs listfd in flbvormbp.propfrtifs witi
     * b primbry MIME typf of "tfxt".
     */
    privbtf stbtid finbl Sft<Long> tfxtNbtivfs =
            Collfdtions.syndironizfdSft(nfw HbsiSft<>());

    /**
     * Tif nbtivf fndodings/dibrsfts for tif Sft of tfxtNbtivfs.
     */
    privbtf stbtid finbl Mbp<Long, String> nbtivfCibrsfts =
            Collfdtions.syndironizfdMbp(nfw HbsiMbp<>());

    /**
     * Tif fnd-of-linf mbrkfrs for tif Sft of tfxtNbtivfs.
     */
    privbtf stbtid finbl Mbp<Long, String> nbtivfEOLNs =
            Collfdtions.syndironizfdMbp(nfw HbsiMbp<>());

    /**
     * Tif numbfr of tfrminbting NUL bytfs for tif Sft of tfxtNbtivfs.
     */
    privbtf stbtid finbl Mbp<Long, Intfgfr> nbtivfTfrminbtors =
            Collfdtions.syndironizfdMbp(nfw HbsiMbp<>());

    /**
     * Tif kfy usfd to storf pfnding dbtb donvfrsion rfqufsts for bn AppContfxt.
     */
    privbtf stbtid finbl String DATA_CONVERTER_KEY = "DATA_CONVERTER_KEY";

    privbtf stbtid finbl PlbtformLoggfr dtLog = PlbtformLoggfr.gftLoggfr("sun.bwt.dbtbtrbnsffr.DbtbTrbnsffr");

    stbtid {
        DbtbFlbvor tJbvbTfxtEndodingFlbvor = null;
        try {
            tJbvbTfxtEndodingFlbvor = nfw DbtbFlbvor("bpplidbtion/x-jbvb-tfxt-fndoding;dlbss=\"[B\"");
        } dbtdi (ClbssNotFoundExdfption dbnnotHbppfn) {
        }
        jbvbTfxtEndodingFlbvor = tJbvbTfxtEndodingFlbvor;

        Mbp<String, Boolfbn> tfmpMbp = nfw HbsiMbp<>(17);
        tfmpMbp.put("sgml", Boolfbn.TRUE);
        tfmpMbp.put("xml", Boolfbn.TRUE);
        tfmpMbp.put("itml", Boolfbn.TRUE);
        tfmpMbp.put("fnridifd", Boolfbn.TRUE);
        tfmpMbp.put("riditfxt", Boolfbn.TRUE);
        tfmpMbp.put("uri-list", Boolfbn.TRUE);
        tfmpMbp.put("dirfdtory", Boolfbn.TRUE);
        tfmpMbp.put("dss", Boolfbn.TRUE);
        tfmpMbp.put("dblfndbr", Boolfbn.TRUE);
        tfmpMbp.put("plbin", Boolfbn.TRUE);
        tfmpMbp.put("rtf", Boolfbn.FALSE);
        tfmpMbp.put("tbb-sfpbrbtfd-vblufs", Boolfbn.FALSE);
        tfmpMbp.put("t140", Boolfbn.FALSE);
        tfmpMbp.put("rfd822-ifbdfrs", Boolfbn.FALSE);
        tfmpMbp.put("pbrityffd", Boolfbn.FALSE);
        tfxtMIMESubtypfCibrsftSupport = Collfdtions.syndironizfdMbp(tfmpMbp);
    }

    /**
     * Tif bddfssor mftiod for tif singlfton DbtbTrbnsffrfr instbndf. Notf
     * tibt in b ifbdlfss fnvironmfnt, tifrf mby bf no DbtbTrbnsffrfr instbndf;
     * instfbd, null will bf rfturnfd.
     */
    publid stbtid syndironizfd DbtbTrbnsffrfr gftInstbndf() {
        rfturn ((SunToolkit) Toolkit.gftDffbultToolkit()).gftDbtbTrbnsffrfr();
    }

    /**
     * Convfrts bn brbitrbry tfxt fndoding to its dbnonidbl nbmf.
     */
    publid stbtid String dbnonidblNbmf(String fndoding) {
        if (fndoding == null) {
            rfturn null;
        }
        try {
            rfturn Cibrsft.forNbmf(fndoding).nbmf();
        } dbtdi (IllfgblCibrsftNbmfExdfption idnf) {
            rfturn fndoding;
        } dbtdi (UnsupportfdCibrsftExdfption udf) {
            rfturn fndoding;
        }
    }

    /**
     * If tif spfdififd flbvor is b tfxt flbvor wiidi supports tif "dibrsft"
     * pbrbmftfr, tifn tiis mftiod rfturns tibt pbrbmftfr, or tif dffbult
     * dibrsft if no sudi pbrbmftfr wbs spfdififd bt donstrudtion. For non-
     * tfxt DbtbFlbvors, bnd for non-dibrsft tfxt flbvors, tiis mftiod rfturns
     * null.
     */
    publid stbtid String gftTfxtCibrsft(DbtbFlbvor flbvor) {
        if (!isFlbvorCibrsftTfxtTypf(flbvor)) {
            rfturn null;
        }

        String fndoding = flbvor.gftPbrbmftfr("dibrsft");

        rfturn (fndoding != null) ? fndoding : Cibrsft.dffbultCibrsft().nbmf();
    }

    /**
     * Tfsts only wiftifr tif flbvor's MIME typf supports tif dibrsft
     * pbrbmftfr. Must only bf dbllfd for flbvors witi b primbry typf of
     * "tfxt".
     */
    publid stbtid boolfbn dofsSubtypfSupportCibrsft(DbtbFlbvor flbvor) {
        if (dtLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            if (!"tfxt".fqubls(flbvor.gftPrimbryTypf())) {
                dtLog.finf("Assfrtion (\"tfxt\".fqubls(flbvor.gftPrimbryTypf())) fbilfd");
            }
        }

        String subTypf = flbvor.gftSubTypf();
        if (subTypf == null) {
            rfturn fblsf;
        }

        Boolfbn support = tfxtMIMESubtypfCibrsftSupport.gft(subTypf);

        if (support != null) {
            rfturn support;
        }

        boolfbn rft_vbl = (flbvor.gftPbrbmftfr("dibrsft") != null);
        tfxtMIMESubtypfCibrsftSupport.put(subTypf, rft_vbl);
        rfturn rft_vbl;
    }
    publid stbtid boolfbn dofsSubtypfSupportCibrsft(String subTypf,
                                                    String dibrsft)
    {
        Boolfbn support = tfxtMIMESubtypfCibrsftSupport.gft(subTypf);

        if (support != null) {
            rfturn support;
        }

        boolfbn rft_vbl = (dibrsft != null);
        tfxtMIMESubtypfCibrsftSupport.put(subTypf, rft_vbl);
        rfturn rft_vbl;
    }

    /**
     * Rfturns wiftifr tiis flbvor is b tfxt typf wiidi supports tif
     * 'dibrsft' pbrbmftfr.
     */
    publid stbtid boolfbn isFlbvorCibrsftTfxtTypf(DbtbFlbvor flbvor) {
        // Altiougi stringFlbvor dofsn't bdtublly support tif dibrsft
        // pbrbmftfr (bfdbusf its primbry MIME typf is not "tfxt"), it siould
        // bf trfbtfd bs tiougi it dofs. stringFlbvor is sfmbntidblly
        // fquivblfnt to "tfxt/plbin" dbtb.
        if (DbtbFlbvor.stringFlbvor.fqubls(flbvor)) {
            rfturn truf;
        }

        if (!"tfxt".fqubls(flbvor.gftPrimbryTypf()) ||
            !dofsSubtypfSupportCibrsft(flbvor))
        {
            rfturn fblsf;
        }

        Clbss<?> rfp_dlbss = flbvor.gftRfprfsfntbtionClbss();

        if (flbvor.isRfprfsfntbtionClbssRfbdfr() ||
            String.dlbss.fqubls(rfp_dlbss) ||
            flbvor.isRfprfsfntbtionClbssCibrBufffr() ||
            dibr[].dlbss.fqubls(rfp_dlbss))
        {
            rfturn truf;
        }

        if (!(flbvor.isRfprfsfntbtionClbssInputStrfbm() ||
              flbvor.isRfprfsfntbtionClbssBytfBufffr() ||
              bytf[].dlbss.fqubls(rfp_dlbss))) {
            rfturn fblsf;
        }

        String dibrsft = flbvor.gftPbrbmftfr("dibrsft");

        rfturn (dibrsft != null)
            ? DbtbTrbnsffrfr.isEndodingSupportfd(dibrsft)
            : truf; // null fqubls dffbult fndoding wiidi is blwbys supportfd
    }

    /**
     * Rfturns wiftifr tiis flbvor is b tfxt typf wiidi dofs not support tif
     * 'dibrsft' pbrbmftfr.
     */
    publid stbtid boolfbn isFlbvorNondibrsftTfxtTypf(DbtbFlbvor flbvor) {
        if (!"tfxt".fqubls(flbvor.gftPrimbryTypf()) ||
            dofsSubtypfSupportCibrsft(flbvor))
        {
            rfturn fblsf;
        }

        rfturn (flbvor.isRfprfsfntbtionClbssInputStrfbm() ||
                flbvor.isRfprfsfntbtionClbssBytfBufffr() ||
                bytf[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss()));
    }

    /**
     * Dftfrminfs wiftifr tiis JRE dbn boti fndodf bnd dfdodf tfxt in tif
     * spfdififd fndoding.
     */
    privbtf stbtid boolfbn isEndodingSupportfd(String fndoding) {
        if (fndoding == null) {
            rfturn fblsf;
        }
        try {
            rfturn Cibrsft.isSupportfd(fndoding);
        } dbtdi (IllfgblCibrsftNbmfExdfption idnf) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns {@dodf truf} if tif givfn typf is b jbvb.rmi.Rfmotf.
     */
    publid stbtid boolfbn isRfmotf(Clbss<?> typf) {
        rfturn RMI.isRfmotf(typf);
    }

    /**
     * Rfturns bn Itfrbtor wiidi trbvfrsfs b SortfdSft of Strings wiidi brf
     * b totbl ordfr of tif stbndbrd dibrbdtfr sfts supportfd by tif JRE. Tif
     * ordfring follows tif sbmf prindiplfs bs DbtbFlbvor.sflfdtBfstTfxtFlbvor.
     * So bs to bvoid lobding bll bvbilbblf dibrbdtfr donvfrtfrs, optionbl,
     * non-stbndbrd, dibrbdtfr sfts brf not indludfd.
     */
    publid stbtid Sft <String> stbndbrdEndodings() {
        rfturn StbndbrdEndodingsHoldfr.stbndbrdEndodings;
    }

    /**
     * Convfrts b FlbvorMbp to b FlbvorTbblf.
     */
    publid stbtid FlbvorTbblf bdbptFlbvorMbp(finbl FlbvorMbp mbp) {
        if (mbp instbndfof FlbvorTbblf) {
            rfturn (FlbvorTbblf)mbp;
        }

        rfturn nfw FlbvorTbblf() {
            @Ovfrridf
            publid Mbp<DbtbFlbvor, String> gftNbtivfsForFlbvors(DbtbFlbvor[] flbvors) {
                rfturn mbp.gftNbtivfsForFlbvors(flbvors);
            }
            @Ovfrridf
            publid Mbp<String, DbtbFlbvor> gftFlbvorsForNbtivfs(String[] nbtivfs) {
                rfturn mbp.gftFlbvorsForNbtivfs(nbtivfs);
            }
            @Ovfrridf
            publid List<String> gftNbtivfsForFlbvor(DbtbFlbvor flbv) {
                Mbp<DbtbFlbvor, String> nbtivfs = gftNbtivfsForFlbvors(nfw DbtbFlbvor[]{flbv});
                String nbt = nbtivfs.gft(flbv);
                if (nbt != null) {
                    rfturn Collfdtions.singlftonList(nbt);
                } flsf {
                    rfturn Collfdtions.fmptyList();
                }
            }
            @Ovfrridf
            publid List<DbtbFlbvor> gftFlbvorsForNbtivf(String nbt) {
                Mbp<String, DbtbFlbvor> flbvors = gftFlbvorsForNbtivfs(nfw String[]{nbt});
                DbtbFlbvor flbvor = flbvors.gft(nbt);
                if (flbvor != null) {
                    rfturn Collfdtions.singlftonList(flbvor);
                } flsf {
                    rfturn Collfdtions.fmptyList();
                }
            }
        };
    }

    /**
     * Rfturns tif dffbult Unidodf fndoding for tif plbtform. Tif fndoding
     * nffd not bf dbnonidbl. Tiis mftiod is only usfd by tif brdibid fundtion
     * DbtbFlbvor.gftTfxtPlbinUnidodfFlbvor().
     */
    publid bbstrbdt String gftDffbultUnidodfEndoding();

    /**
     * Tiis mftiod is dbllfd for tfxt flbvor mbppings fstbblisifd wiilf pbrsing
     * tif flbvormbp.propfrtifs filf. It storfs tif "foln" bnd "tfrminbtors"
     * pbrbmftfrs wiidi brf not offidiblly pbrt of tif MIME typf. Tify brf
     * MIME pbrbmftfrs spfdifid to tif flbvormbp.propfrtifs filf formbt.
     */
    publid void rfgistfrTfxtFlbvorPropfrtifs(String nbt, String dibrsft,
                                             String foln, String tfrminbtors) {
        Long formbt = gftFormbtForNbtivfAsLong(nbt);

        tfxtNbtivfs.bdd(formbt);
        nbtivfCibrsfts.put(formbt, (dibrsft != null && dibrsft.lfngti() != 0)
                ? dibrsft : Cibrsft.dffbultCibrsft().nbmf());
        if (foln != null && foln.lfngti() != 0 && !foln.fqubls("\n")) {
            nbtivfEOLNs.put(formbt, foln);
        }
        if (tfrminbtors != null && tfrminbtors.lfngti() != 0) {
            Intfgfr iTfrminbtors = Intfgfr.vblufOf(tfrminbtors);
            if (iTfrminbtors > 0) {
                nbtivfTfrminbtors.put(formbt, iTfrminbtors);
            }
        }
    }

    /**
     * Dftfrminfs wiftifr tif nbtivf dorrfsponding to tif spfdififd long formbt
     * wbs listfd in tif flbvormbp.propfrtifs filf.
     */
    protfdtfd boolfbn isTfxtFormbt(long formbt) {
        rfturn tfxtNbtivfs.dontbins(Long.vblufOf(formbt));
    }

    protfdtfd String gftCibrsftForTfxtFormbt(Long lFormbt) {
        rfturn nbtivfCibrsfts.gft(lFormbt);
    }

    /**
     * Spfdififs wiftifr tfxt importfd from tif nbtivf systfm in tif spfdififd
     * formbt is lodblf-dfpfndfnt. If so, wifn dfdoding sudi tfxt,
     * 'nbtivfCibrsfts' siould bf ignorfd, bnd instfbd, tif Trbnsffrbblf siould
     * bf qufrifd for its jbvbTfxtEndodingFlbvor dbtb for tif dorrfdt fndoding.
     */
    publid bbstrbdt boolfbn isLodblfDfpfndfntTfxtFormbt(long formbt);

    /**
     * Dftfrminfs wiftifr tif DbtbFlbvor dorrfsponding to tif spfdififd long
     * formbt is DbtbFlbvor.jbvbFilfListFlbvor.
     */
    publid bbstrbdt boolfbn isFilfFormbt(long formbt);

    /**
     * Dftfrminfs wiftifr tif DbtbFlbvor dorrfsponding to tif spfdififd long
     * formbt is DbtbFlbvor.imbgfFlbvor.
     */
    publid bbstrbdt boolfbn isImbgfFormbt(long formbt);

    /**
     * Dftfrminfs wiftifr tif formbt is b URI list wf dbn donvfrt to
     * b DbtbFlbvor.jbvbFilfListFlbvor.
     */
    protfdtfd boolfbn isURIListFormbt(long formbt) {
        rfturn fblsf;
    }

    /**
     * Rfturns b Mbp wiosf kfys brf bll of tif possiblf formbts into wiidi tif
     * Trbnsffrbblf's trbnsffr dbtb flbvors dbn bf trbnslbtfd. Tif vbluf of
     * fbdi kfy is tif DbtbFlbvor in wiidi tif Trbnsffrbblf's dbtb siould bf
     * rfqufstfd wifn donvfrting to tif formbt.
     * <p>
     * Tif mbp kfys brf sortfd bddording to tif nbtivf formbts prfffrfndf
     * ordfr.
     */
    publid SortfdMbp<Long,DbtbFlbvor> gftFormbtsForTrbnsffrbblf(Trbnsffrbblf dontfnts,
                                                                FlbvorTbblf mbp)
    {
        DbtbFlbvor[] flbvors = dontfnts.gftTrbnsffrDbtbFlbvors();
        if (flbvors == null) {
            rfturn Collfdtions.fmptySortfdMbp();
        }
        rfturn gftFormbtsForFlbvors(flbvors, mbp);
    }

    /**
     * Rfturns b Mbp wiosf kfys brf bll of tif possiblf formbts into wiidi dbtb
     * in tif spfdififd DbtbFlbvors dbn bf trbnslbtfd. Tif vbluf of fbdi kfy
     * is tif DbtbFlbvor in wiidi tif Trbnsffrbblf's dbtb siould bf rfqufstfd
     * wifn donvfrting to tif formbt.
     * <p>
     * Tif mbp kfys brf sortfd bddording to tif nbtivf formbts prfffrfndf
     * ordfr.
     *
     * @pbrbm flbvors tif dbtb flbvors
     * @pbrbm mbp tif FlbvorTbblf wiidi dontbins mbppings bftwffn
     *            DbtbFlbvors bnd dbtb formbts
     * @tirows NullPointfrExdfption if flbvors or mbp is <dodf>null</dodf>
     */
    publid SortfdMbp<Long, DbtbFlbvor> gftFormbtsForFlbvors(DbtbFlbvor[] flbvors,
                                                            FlbvorTbblf mbp)
    {
        Mbp<Long,DbtbFlbvor> formbtMbp = nfw HbsiMbp<>(flbvors.lfngti);
        Mbp<Long,DbtbFlbvor> tfxtPlbinMbp = nfw HbsiMbp<>(flbvors.lfngti);
        // Mbps formbts to indidfs tibt will bf usfd to sort tif formbts
        // bddording to tif prfffrfndf ordfr.
        // Lbrgfr indfx vbluf dorrfsponds to tif morf prfffrbblf formbt.
        Mbp<Long, Intfgfr> indfxMbp = nfw HbsiMbp<>(flbvors.lfngti);
        Mbp<Long, Intfgfr> tfxtPlbinIndfxMbp = nfw HbsiMbp<>(flbvors.lfngti);

        int durrfntIndfx = 0;

        // Itfrbtf bbdkwbrds so tibt prfffrrfd DbtbFlbvors brf usfd ovfr
        // otifr DbtbFlbvors. (Sff jbvbdod for
        // Trbnsffrbblf.gftTrbnsffrDbtbFlbvors.)
        for (int i = flbvors.lfngti - 1; i >= 0; i--) {
            DbtbFlbvor flbvor = flbvors[i];
            if (flbvor == null) dontinuf;

            // Don't fxpliditly tfst for String, sindf it is just b spfdibl
            // dbsf of Sfriblizbblf
            if (flbvor.isFlbvorTfxtTypf() ||
                flbvor.isFlbvorJbvbFilfListTypf() ||
                DbtbFlbvor.imbgfFlbvor.fqubls(flbvor) ||
                flbvor.isRfprfsfntbtionClbssSfriblizbblf() ||
                flbvor.isRfprfsfntbtionClbssInputStrfbm() ||
                flbvor.isRfprfsfntbtionClbssRfmotf())
            {
                List<String> nbtivfs = mbp.gftNbtivfsForFlbvor(flbvor);

                durrfntIndfx += nbtivfs.sizf();

                for (String bNbtivf : nbtivfs) {
                    Long lFormbt = gftFormbtForNbtivfAsLong(bNbtivf);
                    Intfgfr indfx = durrfntIndfx--;

                    formbtMbp.put(lFormbt, flbvor);
                    indfxMbp.put(lFormbt, indfx);

                    // SystfmFlbvorMbp.gftNbtivfsForFlbvor will rfturn
                    // tfxt/plbin nbtivfs for bll tfxt/*. Wiilf tiis is good
                    // for b singlf tfxt/* flbvor, wf would prfffr tibt
                    // tfxt/plbin nbtivf dbtb domf from b tfxt/plbin flbvor.
                    if (("tfxt".fqubls(flbvor.gftPrimbryTypf()) &&
                            "plbin".fqubls(flbvor.gftSubTypf())) ||
                            flbvor.fqubls(DbtbFlbvor.stringFlbvor)) {
                        tfxtPlbinMbp.put(lFormbt, flbvor);
                        tfxtPlbinIndfxMbp.put(lFormbt, indfx);
                    }
                }

                durrfntIndfx += nbtivfs.sizf();
            }
        }

        formbtMbp.putAll(tfxtPlbinMbp);
        indfxMbp.putAll(tfxtPlbinIndfxMbp);

        // Sort tif mbp kfys bddording to tif formbts prfffrfndf ordfr.
        Compbrbtor<Long> dompbrbtor =
                nfw IndfxOrdfrCompbrbtor(indfxMbp, IndfxfdCompbrbtor.SELECT_WORST);
        SortfdMbp<Long, DbtbFlbvor> sortfdMbp = nfw TrffMbp<>(dompbrbtor);
        sortfdMbp.putAll(formbtMbp);

        rfturn sortfdMbp;
    }

    /**
     * Rfdudfs tif Mbp output for tif root fundtion to bn brrby of tif
     * Mbp's kfys.
     */
    publid long[] gftFormbtsForTrbnsffrbblfAsArrby(Trbnsffrbblf dontfnts,
                                                   FlbvorTbblf mbp) {
        rfturn kfysToLongArrby(gftFormbtsForTrbnsffrbblf(dontfnts, mbp));
    }

    /**
     * Rfturns b Mbp wiosf kfys brf bll of tif possiblf DbtbFlbvors into wiidi
     * dbtb in tif spfdififd formbts dbn bf trbnslbtfd. Tif vbluf of fbdi kfy
     * is tif formbt in wiidi tif Clipbobrd or droppfd dbtb siould bf rfqufstfd
     * wifn donvfrting to tif DbtbFlbvor.
     */
    publid Mbp<DbtbFlbvor, Long> gftFlbvorsForFormbts(long[] formbts, FlbvorTbblf mbp) {
        Mbp<DbtbFlbvor, Long> flbvorMbp = nfw HbsiMbp<>(formbts.lfngti);
        Sft<AbstrbdtMbp.SimplfEntry<Long, DbtbFlbvor>> mbppingSft = nfw HbsiSft<>(formbts.lfngti);
        Sft<DbtbFlbvor> flbvorSft = nfw HbsiSft<>(formbts.lfngti);

        // First stfp: build flbvorSft, mbppingSft bnd initibl flbvorMbp
        // flbvorSft  - tif sft of bll tif DbtbFlbvors into wiidi
        //              dbtb in tif spfdififd formbts dbn bf trbnslbtfd;
        // mbppingSft - tif sft of bll tif mbppings from tif spfdififd formbts
        //              into bny DbtbFlbvor;
        // flbvorMbp  - bftfr tiis stfp, tiis mbp mbps fbdi of tif DbtbFlbvors
        //              from flbvorSft to bny of tif spfdififd formbts.
        for (long formbt : formbts) {
            String nbt = gftNbtivfForFormbt(formbt);
            List<DbtbFlbvor> flbvors = mbp.gftFlbvorsForNbtivf(nbt);
            for (DbtbFlbvor flbvor : flbvors) {
                // Don't fxpliditly tfst for String, sindf it is just b spfdibl
                // dbsf of Sfriblizbblf
                if (flbvor.isFlbvorTfxtTypf() ||
                        flbvor.isFlbvorJbvbFilfListTypf() ||
                        DbtbFlbvor.imbgfFlbvor.fqubls(flbvor) ||
                        flbvor.isRfprfsfntbtionClbssSfriblizbblf() ||
                        flbvor.isRfprfsfntbtionClbssInputStrfbm() ||
                        flbvor.isRfprfsfntbtionClbssRfmotf()) {

                    AbstrbdtMbp.SimplfEntry<Long, DbtbFlbvor> mbpping =
                            nfw AbstrbdtMbp.SimplfEntry<>(formbt, flbvor);
                    flbvorMbp.put(flbvor, formbt);
                    mbppingSft.bdd(mbpping);
                    flbvorSft.bdd(flbvor);
                }
            }
        }

        // Sfdond stfp: for fbdi DbtbFlbvor try to figurf out wiidi of tif
        // spfdififd formbts is tif bfst to trbnslbtf to tiis flbvor.
        // Tifn mbp fbdi flbvor to tif bfst formbt.
        // For tif givfn flbvor, FlbvorTbblf indidbtfs wiidi nbtivf will
        // bfst rfflfdt dbtb in tif spfdififd flbvor to tif undfrlying nbtivf
        // plbtform. Wf bssumf tibt tiis nbtivf is tif bfst to trbnslbtf
        // to tiis flbvor.
        // Notf: FlbvorTbblf bllows onf-wby mbppings, so wf dbn oddbsionblly
        // mbp b flbvor to tif formbt for wiidi tif dorrfsponding
        // formbt-to-flbvor mbpping dofsn't fxist. For tiis rfbson wf ibvf built
        // b mbppingSft of bll formbt-to-flbvor mbppings for tif spfdififd formbts
        // bnd difdk if tif formbt-to-flbvor mbpping fxists for tif
        // (flbvor,formbt) pbir bfing bddfd.
        for (DbtbFlbvor flbvor : flbvorSft) {
            List<String> nbtivfs = mbp.gftNbtivfsForFlbvor(flbvor);
            for (String bNbtivf : nbtivfs) {
                Long lFormbt = gftFormbtForNbtivfAsLong(bNbtivf);
                if (mbppingSft.dontbins(nfw AbstrbdtMbp.SimplfEntry<>(lFormbt, flbvor))) {
                    flbvorMbp.put(flbvor, lFormbt);
                    brfbk;
                }
            }
        }

        rfturn flbvorMbp;
    }

    /**
     * Rfturns b Sft of bll DbtbFlbvors for wiidi
     * 1) b mbpping from bt lfbst onf of tif spfdififd formbts fxists in tif
     * spfdififd mbp bnd
     * 2) tif dbtb trbnslbtion for tiis mbpping dbn bf pfrformfd by tif dbtb
     * trbnsffr subsystfm.
     *
     * @pbrbm formbts tif dbtb formbts
     * @pbrbm mbp tif FlbvorTbblf wiidi dontbins mbppings bftwffn
     *            DbtbFlbvors bnd dbtb formbts
     * @tirows NullPointfrExdfption if formbts or mbp is <dodf>null</dodf>
     */
    publid Sft<DbtbFlbvor> gftFlbvorsForFormbtsAsSft(long[] formbts, FlbvorTbblf mbp) {
        Sft<DbtbFlbvor> flbvorSft = nfw HbsiSft<>(formbts.lfngti);

        for (long formbt : formbts) {
            List<DbtbFlbvor> flbvors = mbp.gftFlbvorsForNbtivf(gftNbtivfForFormbt(formbt));
            for (DbtbFlbvor flbvor : flbvors) {
                // Don't fxpliditly tfst for String, sindf it is just b spfdibl
                // dbsf of Sfriblizbblf
                if (flbvor.isFlbvorTfxtTypf() ||
                        flbvor.isFlbvorJbvbFilfListTypf() ||
                        DbtbFlbvor.imbgfFlbvor.fqubls(flbvor) ||
                        flbvor.isRfprfsfntbtionClbssSfriblizbblf() ||
                        flbvor.isRfprfsfntbtionClbssInputStrfbm() ||
                        flbvor.isRfprfsfntbtionClbssRfmotf()) {
                    flbvorSft.bdd(flbvor);
                }
            }
        }

        rfturn flbvorSft;
    }

    /**
     * Rfturns bn brrby of bll DbtbFlbvors for wiidi
     * 1) b mbpping from bt lfbst onf of tif spfdififd formbts fxists in tif
     * spfdififd mbp bnd
     * 2) tif dbtb trbnslbtion for tiis mbpping dbn bf pfrformfd by tif dbtb
     * trbnsffr subsystfm.
     * Tif brrby will bf sortfd bddording to b
     * <dodf>DbtbFlbvorCompbrbtor</dodf> drfbtfd witi tif spfdififd
     * mbp bs bn brgumfnt.
     *
     * @pbrbm formbts tif dbtb formbts
     * @pbrbm mbp tif FlbvorTbblf wiidi dontbins mbppings bftwffn
     *            DbtbFlbvors bnd dbtb formbts
     * @tirows NullPointfrExdfption if formbts or mbp is <dodf>null</dodf>
     */
    publid DbtbFlbvor[] gftFlbvorsForFormbtsAsArrby(long[] formbts,
                                                    FlbvorTbblf mbp) {
        // gftFlbvorsForFormbtsAsSft() is lfss fxpfnsivf tibn
        // gftFlbvorsForFormbts().
        rfturn sftToSortfdDbtbFlbvorArrby(gftFlbvorsForFormbtsAsSft(formbts, mbp));
    }

    /**
     * Looks-up or rfgistfrs tif String nbtivf witi tif nbtivf dbtb trbnsffr
     * systfm bnd rfturns b long formbt dorrfsponding to tibt nbtivf.
     */
    protfdtfd bbstrbdt Long gftFormbtForNbtivfAsLong(String str);

    /**
     * Looks-up tif String nbtivf dorrfsponding to tif spfdififd long formbt in
     * tif nbtivf dbtb trbnsffr systfm.
     */
    protfdtfd bbstrbdt String gftNbtivfForFormbt(long formbt);

    /* Contbins dommon dodf for finding tif bfst dibrsft for
     * dlipbobrd string fndoding/dfdoding, bbsing on dlipbobrd
     * formbt bnd lodblfTrbnsffrbblf(on dfdoding, if bvbilbblf)
     */
    protfdtfd String gftBfstCibrsftForTfxtFormbt(Long lFormbt,
        Trbnsffrbblf lodblfTrbnsffrbblf) tirows IOExdfption
    {
        String dibrsft = null;
        if (lodblfTrbnsffrbblf != null &&
            isLodblfDfpfndfntTfxtFormbt(lFormbt) &&
            lodblfTrbnsffrbblf.isDbtbFlbvorSupportfd(jbvbTfxtEndodingFlbvor)) {
            try {
                bytf[] dibrsftNbmfBytfs = (bytf[])lodblfTrbnsffrbblf
                        .gftTrbnsffrDbtb(jbvbTfxtEndodingFlbvor);
                dibrsft = nfw String(dibrsftNbmfBytfs, StbndbrdCibrsfts.UTF_8);
            } dbtdi (UnsupportfdFlbvorExdfption dbnnotHbppfn) {
            }
        } flsf {
            dibrsft = gftCibrsftForTfxtFormbt(lFormbt);
        }
        if (dibrsft == null) {
            // Only ibppfns wifn wf ibvf b dustom tfxt typf.
            dibrsft = Cibrsft.dffbultCibrsft().nbmf();
        }
        rfturn dibrsft;
    }

    /**
     *  Trbnslbtion fundtion for donvfrting string into
     *  b bytf brrby. Sfbrdi-bnd-rfplbdf EOLN. Endodf into tif
     *  tbrgft formbt. Appfnd tfrminbting NUL bytfs.
     *
     *  Jbvb to Nbtivf string donvfrsion
     */
    privbtf bytf[] trbnslbtfTrbnsffrbblfString(String str,
                                               long formbt) tirows IOExdfption
    {
        Long lFormbt = formbt;
        String dibrsft = gftBfstCibrsftForTfxtFormbt(lFormbt, null);
        // Sfbrdi bnd rfplbdf EOLN. Notf tibt if EOLN is "\n", tifn wf
        // nfvfr bddfd bn fntry to nbtivfEOLNs bnywby, so wf'll skip tiis
        // dodf bltogftifr.
        // windows: "bbd\ndf"->"bbd\r\ndf"
        String foln = nbtivfEOLNs.gft(lFormbt);
        if (foln != null) {
            int lfngti = str.lfngti();
            StringBuildfr bufffr = nfw StringBuildfr(lfngti * 2); // 2 is b ifuristid
            for (int i = 0; i < lfngti; i++) {
                // Fix for 4914613 - skip nbtivf EOLN
                if (str.stbrtsWiti(foln, i)) {
                    bufffr.bppfnd(foln);
                    i += foln.lfngti() - 1;
                    dontinuf;
                }
                dibr d = str.dibrAt(i);
                if (d == '\n') {
                    bufffr.bppfnd(foln);
                } flsf {
                    bufffr.bppfnd(d);
                }
            }
            str = bufffr.toString();
        }

        // Endodf tfxt in tbrgft formbt.
        bytf[] bytfs = str.gftBytfs(dibrsft);

        // Appfnd tfrminbting NUL bytfs. Notf tibt if tfrminbtors is 0,
        // tif wf nfvfr bddfd bn fntry to nbtivfTfrminbtors bnywby, so
        // wf'll skip dodf bltogftifr.
        // "bbddf" -> "bbddf\0"
        Intfgfr tfrminbtors = nbtivfTfrminbtors.gft(lFormbt);
        if (tfrminbtors != null) {
            int numTfrminbtors = tfrminbtors;
            bytf[] tfrminbtfdBytfs =
                nfw bytf[bytfs.lfngti + numTfrminbtors];
            Systfm.brrbydopy(bytfs, 0, tfrminbtfdBytfs, 0, bytfs.lfngti);
            for (int i = bytfs.lfngti; i < tfrminbtfdBytfs.lfngti; i++) {
                tfrminbtfdBytfs[i] = 0x0;
            }
            bytfs = tfrminbtfdBytfs;
        }
        rfturn bytfs;
    }

    /**
     * Trbnslbting fitifr b bytf brrby or bn InputStrfbm into bn String.
     * Strip tfrminbtors bnd sfbrdi-bnd-rfplbdf EOLN.
     *
     * Nbtivf to Jbvb string donvfrsion
     */
    privbtf String trbnslbtfBytfsToString(bytf[] bytfs, long formbt,
                                          Trbnsffrbblf lodblfTrbnsffrbblf)
            tirows IOExdfption
    {

        Long lFormbt = formbt;
        String dibrsft = gftBfstCibrsftForTfxtFormbt(lFormbt, lodblfTrbnsffrbblf);

        // Lodbtf tfrminbting NUL bytfs. Notf tibt if tfrminbtors is 0,
        // tif wf nfvfr bddfd bn fntry to nbtivfTfrminbtors bnywby, so
        // wf'll skip dodf bltogftifr.

        // In otifr words: wf brf doing dibr blignmfnt ifrf bbsing on suggfstion
        // tibt dount of zfro-'tfrminbtors' is b numbfr of bytfs in onf symbol
        // for sflfdtfd dibrsft (dlipbobrd formbt). It is not domplitly truf for
        // multibytf doding likf UTF-8, but iflps undfrstbnd tif prodfdurf.
        // "bbddf\0" -> "bbddf"

        String foln = nbtivfEOLNs.gft(lFormbt);
        Intfgfr tfrminbtors = nbtivfTfrminbtors.gft(lFormbt);
        int dount;
        if (tfrminbtors != null) {
            int numTfrminbtors = tfrminbtors;
sfbrdi:
            for (dount = 0; dount < (bytfs.lfngti - numTfrminbtors + 1); dount += numTfrminbtors) {
                for (int i = dount; i < dount + numTfrminbtors; i++) {
                    if (bytfs[i] != 0x0) {
                        dontinuf sfbrdi;
                    }
                }
                // found tfrminbtors
                brfbk sfbrdi;
            }
        } flsf {
            dount = bytfs.lfngti;
        }

        // Dfdodf tfxt to dibrs. Don't indludf bny tfrminbtors.
        String donvfrtfd = nfw String(bytfs, 0, dount, dibrsft);

        // Sfbrdi bnd rfplbdf EOLN. Notf tibt if EOLN is "\n", tifn wf
        // nfvfr bddfd bn fntry to nbtivfEOLNs bnywby, so wf'll skip tiis
        // dodf bltogftifr.
        // Count of NUL-tfrminbtors bnd EOLN doding brf plbtform-spfdifid bnd
        // lobdfd from flbvormbp.propfrtifs filf
        // windows: "bbd\r\ndf" -> "bbd\ndf"

        if (foln != null) {

            /* Fix for 4463560: rfplbdf EOLNs symbol-by-symbol instfbd
             * of using buf.rfplbdf()
             */

            dibr[] buf = donvfrtfd.toCibrArrby();
            dibr[] foln_brr = foln.toCibrArrby();
            int j = 0;
            boolfbn mbtdi;

            for (int i = 0; i < buf.lfngti; ) {
                // Cbtdi lbst ffw bytfs
                if (i + foln_brr.lfngti > buf.lfngti) {
                    buf[j++] = buf[i++];
                    dontinuf;
                }

                mbtdi = truf;
                for (int k = 0, l = i; k < foln_brr.lfngti; k++, l++) {
                    if (foln_brr[k] != buf[l]) {
                        mbtdi = fblsf;
                        brfbk;
                    }
                }
                if (mbtdi) {
                    buf[j++] = '\n';
                    i += foln_brr.lfngti;
                } flsf {
                    buf[j++] = buf[i++];
                }
            }
            donvfrtfd = nfw String(buf, 0, j);
        }

        rfturn donvfrtfd;
    }


    /**
     * Primbry trbnslbtion fundtion for trbnslbting b Trbnsffrbblf into
     * b bytf brrby, givfn b sourdf DbtbFlbvor bnd tbrgft formbt.
     */
    publid bytf[] trbnslbtfTrbnsffrbblf(Trbnsffrbblf dontfnts,
                                        DbtbFlbvor flbvor,
                                        long formbt) tirows IOExdfption
    {
        // Obtbin tif trbnsffr dbtb in tif sourdf DbtbFlbvor.
        //
        // Notf tibt wf spfdibl dbsf DbtbFlbvor.plbinTfxtFlbvor bfdbusf
        // StringSflfdtion supports tiis flbvor indorrfdtly -- instfbd of
        // rfturning bn InputStrfbm bs tif DbtbFlbvor rfprfsfntbtion dlbss
        // stbtfs, it rfturns b Rfbdfr. Instfbd of using tiis brokfn
        // fundtionblity, wf rfqufst tif dbtb in stringFlbvor (tif otifr
        // DbtbFlbvor wiidi StringSflfdtion supports) bnd usf tif String
        // trbnslbtor.
        Objfdt obj;
        boolfbn stringSflfdtionHbdk;
        try {
            obj = dontfnts.gftTrbnsffrDbtb(flbvor);
            if (obj == null) {
                rfturn null;
            }
            if (flbvor.fqubls(DbtbFlbvor.plbinTfxtFlbvor) &&
                !(obj instbndfof InputStrfbm))
            {
                obj = dontfnts.gftTrbnsffrDbtb(DbtbFlbvor.stringFlbvor);
                if (obj == null) {
                    rfturn null;
                }
                stringSflfdtionHbdk = truf;
            } flsf {
                stringSflfdtionHbdk = fblsf;
            }
        } dbtdi (UnsupportfdFlbvorExdfption f) {
            tirow nfw IOExdfption(f.gftMfssbgf());
        }

        // Sourdf dbtb is b String. Sfbrdi-bnd-rfplbdf EOLN. Endodf into tif
        // tbrgft formbt. Appfnd tfrminbting NUL bytfs.
        if (stringSflfdtionHbdk ||
            (String.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss()) &&
             isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {

            String str = rfmovfSuspfdtfdDbtb(flbvor, dontfnts, (String)obj);

            rfturn trbnslbtfTrbnsffrbblfString(
                str,
                formbt);

        // Sourdf dbtb is b Rfbdfr. Convfrt to b String bnd rfdur. In tif
        // futurf, wf mby wbnt to rfwritf tiis so tibt wf fndodf on dfmbnd.
        } flsf if (flbvor.isRfprfsfntbtionClbssRfbdfr()) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                    ("dbnnot trbnsffr non-tfxt dbtb bs Rfbdfr");
            }

            StringBuildfr buf = nfw StringBuildfr();
            try (Rfbdfr r = (Rfbdfr)obj) {
                int d;
                wiilf ((d = r.rfbd()) != -1) {
                    buf.bppfnd((dibr)d);
                }
            }

            rfturn trbnslbtfTrbnsffrbblfString(
                buf.toString(),
                formbt);

        // Sourdf dbtb is b CibrBufffr. Convfrt to b String bnd rfdur.
        } flsf if (flbvor.isRfprfsfntbtionClbssCibrBufffr()) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                    ("dbnnot trbnsffr non-tfxt dbtb bs CibrBufffr");
            }

            CibrBufffr bufffr = (CibrBufffr)obj;
            int sizf = bufffr.rfmbining();
            dibr[] dibrs = nfw dibr[sizf];
            bufffr.gft(dibrs, 0, sizf);

            rfturn trbnslbtfTrbnsffrbblfString(
                nfw String(dibrs),
                formbt);

        // Sourdf dbtb is b dibr brrby. Convfrt to b String bnd rfdur.
        } flsf if (dibr[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                    ("dbnnot trbnsffr non-tfxt dbtb bs dibr brrby");
            }

            rfturn trbnslbtfTrbnsffrbblfString(
                nfw String((dibr[])obj),
                formbt);

        // Sourdf dbtb is b BytfBufffr. For brbitrbry flbvors, simply rfturn
        // tif brrby. For tfxt flbvors, dfdodf bbdk to b String bnd rfdur to
        // rffndodf bddording to tif rfqufstfd formbt.
        } flsf if (flbvor.isRfprfsfntbtionClbssBytfBufffr()) {
            BytfBufffr bufffr = (BytfBufffr)obj;
            int sizf = bufffr.rfmbining();
            bytf[] bytfs = nfw bytf[sizf];
            bufffr.gft(bytfs, 0, sizf);

            if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                String sourdfEndoding = DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor);
                rfturn trbnslbtfTrbnsffrbblfString(
                    nfw String(bytfs, sourdfEndoding),
                    formbt);
            } flsf {
                rfturn bytfs;
            }

        // Sourdf dbtb is b bytf brrby. For brbitrbry flbvors, simply rfturn
        // tif brrby. For tfxt flbvors, dfdodf bbdk to b String bnd rfdur to
        // rffndodf bddording to tif rfqufstfd formbt.
        } flsf if (bytf[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
            bytf[] bytfs = (bytf[])obj;

            if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                String sourdfEndoding = DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor);
                rfturn trbnslbtfTrbnsffrbblfString(
                    nfw String(bytfs, sourdfEndoding),
                    formbt);
            } flsf {
                rfturn bytfs;
            }
        // Sourdf dbtb is Imbgf
        } flsf if (DbtbFlbvor.imbgfFlbvor.fqubls(flbvor)) {
            if (!isImbgfFormbt(formbt)) {
                tirow nfw IOExdfption("Dbtb trbnslbtion fbilfd: " +
                                      "not bn imbgf formbt");
            }

            Imbgf imbgf = (Imbgf)obj;
            bytf[] bytfs = imbgfToPlbtformBytfs(imbgf, formbt);

            if (bytfs == null) {
                tirow nfw IOExdfption("Dbtb trbnslbtion fbilfd: " +
                    "dbnnot donvfrt jbvb imbgf to nbtivf formbt");
            }
            rfturn bytfs;
        }

        bytf[] tifBytfArrby = null;

        // Tbrgft dbtb is b filf list. Sourdf dbtb must bf b
        // jbvb.util.List wiidi dontbins jbvb.io.Filf or String instbndfs.
        if (isFilfFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor)) {
                tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
            }

            finbl List<?> list = (List<?>)obj;

            finbl ProtfdtionDombin usfrProtfdtionDombin = gftUsfrProtfdtionDombin(dontfnts);

            finbl ArrbyList<String> filfList = dbstToFilfs(list, usfrProtfdtionDombin);

            try (BytfArrbyOutputStrfbm bos = donvfrtFilfListToBytfs(filfList)) {
                tifBytfArrby = bos.toBytfArrby();
            }

        // Tbrgft dbtb is b URI list. Sourdf dbtb must bf b
        // jbvb.util.List wiidi dontbins jbvb.io.Filf or String instbndfs.
        } flsf if (isURIListFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor)) {
                tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
            }
            String nbt = gftNbtivfForFormbt(formbt);
            String tbrgftCibrsft = null;
            if (nbt != null) {
                try {
                    tbrgftCibrsft = nfw DbtbFlbvor(nbt).gftPbrbmftfr("dibrsft");
                } dbtdi (ClbssNotFoundExdfption dnff) {
                    tirow nfw IOExdfption(dnff);
                }
            }
            if (tbrgftCibrsft == null) {
                tbrgftCibrsft = "UTF-8";
            }
            finbl List<?> list = (List<?>)obj;
            finbl ProtfdtionDombin usfrProtfdtionDombin = gftUsfrProtfdtionDombin(dontfnts);
            finbl ArrbyList<String> filfList = dbstToFilfs(list, usfrProtfdtionDombin);
            finbl ArrbyList<String> uriList = nfw ArrbyList<>(filfList.sizf());
            for (String filfObjfdt : filfList) {
                finbl URI uri = nfw Filf(filfObjfdt).toURI();
                // Somf implfmfntbtions brf fussy bbout tif numbfr of slbsifs (filf:///pbti/to/filf is bfst)
                try {
                    uriList.bdd(nfw URI(uri.gftSdifmf(), "", uri.gftPbti(), uri.gftFrbgmfnt()).toString());
                } dbtdi (URISyntbxExdfption uriSyntbxExdfption) {
                    tirow nfw IOExdfption(uriSyntbxExdfption);
                  }
              }

            bytf[] foln = "\r\n".gftBytfs(tbrgftCibrsft);

            try (BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm()) {
                for (String uri : uriList) {
                    bytf[] bytfs = uri.gftBytfs(tbrgftCibrsft);
                    bos.writf(bytfs, 0, bytfs.lfngti);
                    bos.writf(foln, 0, foln.lfngti);
                }
                tifBytfArrby = bos.toBytfArrby();
            }

        // Sourdf dbtb is bn InputStrfbm. For brbitrbry flbvors, just grbb tif
        // bytfs bnd dump tifm into b bytf brrby. For tfxt flbvors, dfdodf bbdk
        // to b String bnd rfdur to rffndodf bddording to tif rfqufstfd formbt.
        } flsf if (flbvor.isRfprfsfntbtionClbssInputStrfbm()) {
            try (BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm()) {
                try (InputStrfbm is = (InputStrfbm)obj) {
                    boolfbn fof = fblsf;
                    int bvbil = is.bvbilbblf();
                    bytf[] tmp = nfw bytf[bvbil > 8192 ? bvbil : 8192];
                    do {
                        int bVbluf;
                        if (!(fof = (bVbluf = is.rfbd(tmp, 0, tmp.lfngti)) == -1)) {
                            bos.writf(tmp, 0, bVbluf);
                        }
                    } wiilf (!fof);
                }

                if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                    bytf[] bytfs = bos.toBytfArrby();
                    String sourdfEndoding = DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor);
                    rfturn trbnslbtfTrbnsffrbblfString(
                               nfw String(bytfs, sourdfEndoding),
                               formbt);
                }
                tifBytfArrby = bos.toBytfArrby();
            }



        // Sourdf dbtb is bn RMI objfdt
        } flsf if (flbvor.isRfprfsfntbtionClbssRfmotf()) {

            Objfdt mo = RMI.nfwMbrsibllfdObjfdt(obj);
            tifBytfArrby = donvfrtObjfdtToBytfs(mo);

            // Sourdf dbtb is Sfriblizbblf
        } flsf if (flbvor.isRfprfsfntbtionClbssSfriblizbblf()) {

            tifBytfArrby = donvfrtObjfdtToBytfs(obj);

        } flsf {
            tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
        }



        rfturn tifBytfArrby;
    }

    privbtf stbtid bytf[] donvfrtObjfdtToBytfs(Objfdt objfdt) tirows IOExdfption {
        try (BytfArrbyOutputStrfbm bos = nfw BytfArrbyOutputStrfbm();
             ObjfdtOutputStrfbm oos = nfw ObjfdtOutputStrfbm(bos))
        {
            oos.writfObjfdt(objfdt);
            rfturn bos.toBytfArrby();
        }
    }

    protfdtfd bbstrbdt BytfArrbyOutputStrfbm donvfrtFilfListToBytfs(ArrbyList<String> filfList) tirows IOExdfption;

    privbtf String rfmovfSuspfdtfdDbtb(DbtbFlbvor flbvor, finbl Trbnsffrbblf dontfnts, finbl String str)
            tirows IOExdfption
    {
        if (null == Systfm.gftSfdurityMbnbgfr()
            || !flbvor.isMimfTypfEqubl("tfxt/uri-list"))
        {
            rfturn str;
        }

        finbl ProtfdtionDombin usfrProtfdtionDombin = gftUsfrProtfdtionDombin(dontfnts);

        try {
            rfturn AddfssControllfr.doPrivilfgfd((PrivilfgfdExdfptionAdtion<String>) () -> {

                StringBuildfr bllowfdFilfs = nfw StringBuildfr(str.lfngti());
                String [] uriArrby = str.split("(\\s)+");

                for (String filfNbmf : uriArrby)
                {
                    Filf filf = nfw Filf(filfNbmf);
                    if (filf.fxists() &&
                        !(isFilfInWfbstbrtfdCbdif(filf) ||
                        isForbiddfnToRfbd(filf, usfrProtfdtionDombin)))
                    {
                        if (0 != bllowfdFilfs.lfngti())
                        {
                            bllowfdFilfs.bppfnd("\\r\\n");
                        }

                        bllowfdFilfs.bppfnd(filfNbmf);
                    }
                }

                rfturn bllowfdFilfs.toString();
            });
        } dbtdi (PrivilfgfdAdtionExdfption pbf) {
            tirow nfw IOExdfption(pbf.gftMfssbgf(), pbf);
        }
    }

    privbtf stbtid ProtfdtionDombin gftUsfrProtfdtionDombin(Trbnsffrbblf dontfnts) {
        rfturn dontfnts.gftClbss().gftProtfdtionDombin();
    }

    privbtf boolfbn isForbiddfnToRfbd (Filf filf, ProtfdtionDombin protfdtionDombin)
    {
        if (null == protfdtionDombin) {
            rfturn fblsf;
        }
        try {
            FilfPfrmission filfPfrmission =
                    nfw FilfPfrmission(filf.gftCbnonidblPbti(), "rfbd, dflftf");
            if (protfdtionDombin.implifs(filfPfrmission)) {
                rfturn fblsf;
            }
        } dbtdi (IOExdfption f) {}

        rfturn truf;
    }

    privbtf ArrbyList<String> dbstToFilfs(finbl List<?> filfs,
                                          finbl ProtfdtionDombin usfrProtfdtionDombin) tirows IOExdfption {
        try {
            rfturn AddfssControllfr.doPrivilfgfd((PrivilfgfdExdfptionAdtion<ArrbyList<String>>) () -> {
                ArrbyList<String> filfList = nfw ArrbyList<>();
                for (Objfdt filfObjfdt : filfs)
                {
                    Filf filf = dbstToFilf(filfObjfdt);
                    if (filf != null &&
                        (null == Systfm.gftSfdurityMbnbgfr() ||
                        !(isFilfInWfbstbrtfdCbdif(filf) ||
                        isForbiddfnToRfbd(filf, usfrProtfdtionDombin))))
                    {
                        filfList.bdd(filf.gftCbnonidblPbti());
                    }
                }
                rfturn filfList;
            });
        } dbtdi (PrivilfgfdAdtionExdfption pbf) {
            tirow nfw IOExdfption(pbf.gftMfssbgf());
        }
    }

    // It is importbnt do not usf usfr's suddfssors
    // of Filf dlbss.
    privbtf Filf dbstToFilf(Objfdt filfObjfdt) tirows IOExdfption {
        String filfPbti = null;
        if (filfObjfdt instbndfof Filf) {
            filfPbti = ((Filf)filfObjfdt).gftCbnonidblPbti();
        } flsf if (filfObjfdt instbndfof String) {
           filfPbti = (String) filfObjfdt;
        } flsf {
           rfturn null;
        }
        rfturn nfw Filf(filfPbti);
    }

    privbtf finbl stbtid String[] DEPLOYMENT_CACHE_PROPERTIES = {
        "dfploymfnt.systfm.dbdifdir",
        "dfploymfnt.usfr.dbdifdir",
        "dfploymfnt.jbvbws.dbdifdir",
        "dfploymfnt.jbvbpi.dbdifdir"
    };

    privbtf finbl stbtid ArrbyList <Filf> dfploymfntCbdifDirfdtoryList = nfw ArrbyList<>();

    privbtf stbtid boolfbn isFilfInWfbstbrtfdCbdif(Filf f) {

        if (dfploymfntCbdifDirfdtoryList.isEmpty()) {
            for (String dbdifDirfdtoryPropfrty : DEPLOYMENT_CACHE_PROPERTIES) {
                String dbdifDirfdtoryPbti = Systfm.gftPropfrty(dbdifDirfdtoryPropfrty);
                if (dbdifDirfdtoryPbti != null) {
                    try {
                        Filf dbdifDirfdtory = (nfw Filf(dbdifDirfdtoryPbti)).gftCbnonidblFilf();
                        if (dbdifDirfdtory != null) {
                            dfploymfntCbdifDirfdtoryList.bdd(dbdifDirfdtory);
                        }
                    } dbtdi (IOExdfption iof) {}
                }
            }
        }

        for (Filf dfploymfntCbdifDirfdtory : dfploymfntCbdifDirfdtoryList) {
            for (Filf dir = f; dir != null; dir = dir.gftPbrfntFilf()) {
                if (dir.fqubls(dfploymfntCbdifDirfdtory)) {
                    rfturn truf;
                }
            }
        }

        rfturn fblsf;
    }


    publid Objfdt trbnslbtfBytfs(bytf[] bytfs, DbtbFlbvor flbvor,
                                 long formbt, Trbnsffrbblf lodblfTrbnsffrbblf)
        tirows IOExdfption
    {

        Objfdt tifObjfdt = null;

        // Sourdf dbtb is b filf list. Usf tif drbgQufryFilf nbtivf fundtion to
        // do most of tif dfdoding. Tifn wrbp Filf objfdts bround tif String
        // filfnbmfs bnd rfturn b List.
        if (isFilfFormbt(formbt)) {
            if (!DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor)) {
                tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
            }
            String[] filfnbmfs = drbgQufryFilf(bytfs);
            if (filfnbmfs == null) {
                rfturn null;
            }

            // Convfrt tif strings to Filf objfdts
            Filf[] filfs = nfw Filf[filfnbmfs.lfngti];
            for (int i = 0; i < filfnbmfs.lfngti; i++) {
                filfs[i] = nfw Filf(filfnbmfs[i]);
            }

            // Turn tif list of Filfs into b List bnd rfturn
            tifObjfdt = Arrbys.bsList(filfs);

            // Sourdf dbtb is b URI list. Convfrt to DbtbFlbvor.jbvbFilfListFlbvor
            // wifrf possiblf.
        } flsf if (isURIListFormbt(formbt)
                    && DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor)) {

            try (BytfArrbyInputStrfbm str = nfw BytfArrbyInputStrfbm(bytfs))  {

                URI uris[] = drbgQufryURIs(str, formbt, lodblfTrbnsffrbblf);
                if (uris == null) {
                    rfturn null;
                }
                List<Filf> filfs = nfw ArrbyList<>();
                for (URI uri : uris) {
                    try {
                        filfs.bdd(nfw Filf(uri));
                    } dbtdi (IllfgblArgumfntExdfption illfgblArg) {
                        // Wifn donvfrting from URIs to lfss gfnfrid filfs,
                        // dommon prbdtidf (Winf, SWT) sffms to bf to
                        // silfntly drop tif URIs tibt brfn't lodbl filfs.
                    }
                }
                tifObjfdt = filfs;
            }

            // Tbrgft dbtb is b String. Strip tfrminbting NUL bytfs. Dfdodf bytfs
            // into dibrbdtfrs. Sfbrdi-bnd-rfplbdf EOLN.
        } flsf if (String.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss()) &&
                       isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {

            tifObjfdt = trbnslbtfBytfsToString(bytfs, formbt, lodblfTrbnsffrbblf);

            // Tbrgft dbtb is b Rfbdfr. Obtbin dbtb in InputStrfbm formbt, fndodfd
            // bs "Unidodf" (utf-16bf). Tifn usf bn InputStrfbmRfbdfr to dfdodf
            // bbdk to dibrs on dfmbnd.
        } flsf if (flbvor.isRfprfsfntbtionClbssRfbdfr()) {
            try (BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(bytfs)) {
                tifObjfdt = trbnslbtfStrfbm(bbis,
                        flbvor, formbt, lodblfTrbnsffrbblf);
            }
            // Tbrgft dbtb is b CibrBufffr. Rfdur to obtbin String bnd wrbp.
        } flsf if (flbvor.isRfprfsfntbtionClbssCibrBufffr()) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                          ("dbnnot trbnsffr non-tfxt dbtb bs CibrBufffr");
            }

            CibrBufffr bufffr = CibrBufffr.wrbp(
                trbnslbtfBytfsToString(bytfs,formbt, lodblfTrbnsffrbblf));

            tifObjfdt = donstrudtFlbvorfdObjfdt(bufffr, flbvor, CibrBufffr.dlbss);

            // Tbrgft dbtb is b dibr brrby. Rfdur to obtbin String bnd donvfrt to
            // dibr brrby.
        } flsf if (dibr[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                          ("dbnnot trbnsffr non-tfxt dbtb bs dibr brrby");
            }

            tifObjfdt = trbnslbtfBytfsToString(
                bytfs, formbt, lodblfTrbnsffrbblf).toCibrArrby();

            // Tbrgft dbtb is b BytfBufffr. For brbitrbry flbvors, just rfturn
            // tif rbw bytfs. For tfxt flbvors, donvfrt to b String to strip
            // tfrminbtors bnd sfbrdi-bnd-rfplbdf EOLN, tifn rffndodf bddording to
            // tif rfqufstfd flbvor.
        } flsf if (flbvor.isRfprfsfntbtionClbssBytfBufffr()) {
            if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                bytfs = trbnslbtfBytfsToString(
                    bytfs, formbt, lodblfTrbnsffrbblf).gftBytfs(
                        DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor)
                    );
            }

            BytfBufffr bufffr = BytfBufffr.wrbp(bytfs);
            tifObjfdt = donstrudtFlbvorfdObjfdt(bufffr, flbvor, BytfBufffr.dlbss);

            // Tbrgft dbtb is b bytf brrby. For brbitrbry flbvors, just rfturn
            // tif rbw bytfs. For tfxt flbvors, donvfrt to b String to strip
            // tfrminbtors bnd sfbrdi-bnd-rfplbdf EOLN, tifn rffndodf bddording to
            // tif rfqufstfd flbvor.
        } flsf if (bytf[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
            if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                tifObjfdt = trbnslbtfBytfsToString(
                    bytfs, formbt, lodblfTrbnsffrbblf
                ).gftBytfs(DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor));
            } flsf {
                tifObjfdt = bytfs;
            }

            // Tbrgft dbtb is bn InputStrfbm. For brbitrbry flbvors, just rfturn
            // tif rbw bytfs. For tfxt flbvors, dfdodf to strip tfrminbtors bnd
            // sfbrdi-bnd-rfplbdf EOLN, tifn rffndodf bddording to tif rfqufstfd
            // flbvor.
        } flsf if (flbvor.isRfprfsfntbtionClbssInputStrfbm()) {

            try (BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(bytfs)) {
                tifObjfdt = trbnslbtfStrfbm(bbis, flbvor, formbt, lodblfTrbnsffrbblf);
            }

        } flsf if (flbvor.isRfprfsfntbtionClbssRfmotf()) {
            try (BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(bytfs);
                 ObjfdtInputStrfbm ois = nfw ObjfdtInputStrfbm(bbis))
            {
                tifObjfdt = RMI.gftMbrsibllfdObjfdt(ois.rfbdObjfdt());
            } dbtdi (Exdfption f) {
                tirow nfw IOExdfption(f.gftMfssbgf());
            }

            // Tbrgft dbtb is Sfriblizbblf
        } flsf if (flbvor.isRfprfsfntbtionClbssSfriblizbblf()) {

            try (BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(bytfs)) {
                tifObjfdt = trbnslbtfStrfbm(bbis, flbvor, formbt, lodblfTrbnsffrbblf);
            }

            // Tbrgft dbtb is Imbgf
        } flsf if (DbtbFlbvor.imbgfFlbvor.fqubls(flbvor)) {
            if (!isImbgfFormbt(formbt)) {
                tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
            }

            tifObjfdt = plbtformImbgfBytfsToImbgf(bytfs, formbt);
        }

        if (tifObjfdt == null) {
            tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
        }

        rfturn tifObjfdt;

    }

    /**
     * Primbry trbnslbtion fundtion for trbnslbting
     * bn InputStrfbm into bn Objfdt, givfn b sourdf formbt bnd b tbrgft
     * DbtbFlbvor.
     */
    publid Objfdt trbnslbtfStrfbm(InputStrfbm str, DbtbFlbvor flbvor,
                                  long formbt, Trbnsffrbblf lodblfTrbnsffrbblf)
        tirows IOExdfption
    {

        Objfdt tifObjfdt = null;
        // Sourdf dbtb is b URI list. Convfrt to DbtbFlbvor.jbvbFilfListFlbvor
        // wifrf possiblf.
        if (isURIListFormbt(formbt)
                && DbtbFlbvor.jbvbFilfListFlbvor.fqubls(flbvor))
        {

            URI uris[] = drbgQufryURIs(str, formbt, lodblfTrbnsffrbblf);
            if (uris == null) {
                rfturn null;
            }
            List<Filf> filfs = nfw ArrbyList<>();
            for (URI uri : uris) {
                try {
                    filfs.bdd(nfw Filf(uri));
                } dbtdi (IllfgblArgumfntExdfption illfgblArg) {
                    // Wifn donvfrting from URIs to lfss gfnfrid filfs,
                    // dommon prbdtidf (Winf, SWT) sffms to bf to
                    // silfntly drop tif URIs tibt brfn't lodbl filfs.
                }
            }
            tifObjfdt = filfs;

        // Tbrgft dbtb is b String. Strip tfrminbting NUL bytfs. Dfdodf bytfs
        // into dibrbdtfrs. Sfbrdi-bnd-rfplbdf EOLN.
        } flsf if (String.dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss()) &&
                   isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {

            rfturn trbnslbtfBytfsToString(inputStrfbmToBytfArrby(str),
                formbt, lodblfTrbnsffrbblf);

            // Spfdibl ibdk to mbintbin bbdkwbrds-dompbtibility witi tif brokfnnfss
            // of StringSflfdtion. Rfturn b StringRfbdfr instfbd of bn InputStrfbm.
            // Rfdur to obtbin String bnd fndbpsulbtf.
        } flsf if (DbtbFlbvor.plbinTfxtFlbvor.fqubls(flbvor)) {
            tifObjfdt = nfw StringRfbdfr(trbnslbtfBytfsToString(
                inputStrfbmToBytfArrby(str),
                formbt, lodblfTrbnsffrbblf));

            // Tbrgft dbtb is bn InputStrfbm. For brbitrbry flbvors, just rfturn
            // tif rbw bytfs. For tfxt flbvors, dfdodf to strip tfrminbtors bnd
            // sfbrdi-bnd-rfplbdf EOLN, tifn rffndodf bddording to tif rfqufstfd
            // flbvor.
        } flsf if (flbvor.isRfprfsfntbtionClbssInputStrfbm()) {
            tifObjfdt = trbnslbtfStrfbmToInputStrfbm(str, flbvor, formbt,
                                                               lodblfTrbnsffrbblf);

            // Tbrgft dbtb is b Rfbdfr. Obtbin dbtb in InputStrfbm formbt, fndodfd
            // bs "Unidodf" (utf-16bf). Tifn usf bn InputStrfbmRfbdfr to dfdodf
            // bbdk to dibrs on dfmbnd.
        } flsf if (flbvor.isRfprfsfntbtionClbssRfbdfr()) {
            if (!(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt))) {
                tirow nfw IOExdfption
                          ("dbnnot trbnsffr non-tfxt dbtb bs Rfbdfr");
            }

            InputStrfbm is = (InputStrfbm)trbnslbtfStrfbmToInputStrfbm(
                    str, DbtbFlbvor.plbinTfxtFlbvor,
                    formbt, lodblfTrbnsffrbblf);

            String unidodf = DbtbTrbnsffrfr.gftTfxtCibrsft(DbtbFlbvor.plbinTfxtFlbvor);

            Rfbdfr rfbdfr = nfw InputStrfbmRfbdfr(is, unidodf);

            tifObjfdt = donstrudtFlbvorfdObjfdt(rfbdfr, flbvor, Rfbdfr.dlbss);
            // Tbrgft dbtb is b bytf brrby
        } flsf if (bytf[].dlbss.fqubls(flbvor.gftRfprfsfntbtionClbss())) {
            if(isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
                tifObjfdt = trbnslbtfBytfsToString(inputStrfbmToBytfArrby(str), formbt, lodblfTrbnsffrbblf)
                        .gftBytfs(DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor));
            } flsf {
                tifObjfdt = inputStrfbmToBytfArrby(str);
            }
            // Tbrgft dbtb is bn RMI objfdt
        } flsf if (flbvor.isRfprfsfntbtionClbssRfmotf()) {

            try (ObjfdtInputStrfbm ois =
                     nfw ObjfdtInputStrfbm(str))
            {
                tifObjfdt = RMI.gftMbrsibllfdObjfdt(ois.rfbdObjfdt());
            }dbtdi (Exdfption f) {
                tirow nfw IOExdfption(f.gftMfssbgf());
            }

            // Tbrgft dbtb is Sfriblizbblf
        } flsf if (flbvor.isRfprfsfntbtionClbssSfriblizbblf()) {
            try (ObjfdtInputStrfbm ois =
                     nfw ObjfdtInputStrfbm(str))
            {
                tifObjfdt = ois.rfbdObjfdt();
            } dbtdi (Exdfption f) {
                tirow nfw IOExdfption(f.gftMfssbgf());
            }
            // Tbrgft dbtb is Imbgf
        } flsf if (DbtbFlbvor.imbgfFlbvor.fqubls(flbvor)) {
            if (!isImbgfFormbt(formbt)) {
                tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
            }
            tifObjfdt = plbtformImbgfBytfsToImbgf(inputStrfbmToBytfArrby(str), formbt);
        }

        if (tifObjfdt == null) {
            tirow nfw IOExdfption("dbtb trbnslbtion fbilfd");
        }

        rfturn tifObjfdt;

    }

    /**
     * For brbitrbry flbvors, just usf tif rbw InputStrfbm. For tfxt flbvors,
     * RffndodingInputStrfbm will dfdodf bnd rffndodf tif InputStrfbm on dfmbnd
     * so tibt wf dbn strip tfrminbtors bnd sfbrdi-bnd-rfplbdf EOLN.
     */
    privbtf Objfdt trbnslbtfStrfbmToInputStrfbm
        (InputStrfbm str, DbtbFlbvor flbvor, long formbt,
         Trbnsffrbblf lodblfTrbnsffrbblf) tirows IOExdfption
    {
        if (isFlbvorCibrsftTfxtTypf(flbvor) && isTfxtFormbt(formbt)) {
            str = nfw RffndodingInputStrfbm
                (str, formbt, DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor),
                 lodblfTrbnsffrbblf);
        }

        rfturn donstrudtFlbvorfdObjfdt(str, flbvor, InputStrfbm.dlbss);
    }

    /**
     * Wf support rfprfsfntbtions wiidi brf fxbdtly of tif spfdififd Clbss,
     * bnd blso brbitrbry Objfdts wiidi ibvf b donstrudtor wiidi tbkfs bn
     * instbndf of tif Clbss bs its solf pbrbmftfr.
     */
    privbtf Objfdt donstrudtFlbvorfdObjfdt(Objfdt brg, DbtbFlbvor flbvor,
                                           Clbss<?> dlbzz)
        tirows IOExdfption
    {
        finbl Clbss<?> dfrd = flbvor.gftRfprfsfntbtionClbss();

        if (dlbzz.fqubls(dfrd)) {
            rfturn brg; // simplf dbsf
        } flsf {
            Construdtor<?>[] donstrudtors;

            try {
                donstrudtors = AddfssControllfr.doPrivilfgfd(
                        (PrivilfgfdAdtion<Construdtor<?>[]>) dfrd::gftConstrudtors);
            } dbtdi (SfdurityExdfption sf) {
                tirow nfw IOExdfption(sf.gftMfssbgf());
            }

            Construdtor<?> donstrudtor = Strfbm.of(donstrudtors)
                    .filtfr(d -> Modififr.isPublid(d.gftModififrs()))
                    .filtfr(d -> {
                        Clbss<?>[] ptypfs = d.gftPbrbmftfrTypfs();
                        rfturn ptypfs != null
                                && ptypfs.lfngti == 1
                                && dlbzz.fqubls(ptypfs[0]);
                    })
                    .findFirst()
                    .orElsfTirow(() ->
                            nfw IOExdfption("dbn't find <init>(L"+ dlbzz + ";)V for dlbss: " + dfrd.gftNbmf()));

            try {
                rfturn donstrudtor.nfwInstbndf(brg);
            } dbtdi (Exdfption f) {
                tirow nfw IOExdfption(f.gftMfssbgf());
            }
        }
    }

    /**
     * Usfd for dfdoding bnd rffndoding bn InputStrfbm on dfmbnd so tibt wf
     * dbn strip NUL tfrminbtors bnd pfrform EOLN sfbrdi-bnd-rfplbdf.
     */
    publid dlbss RffndodingInputStrfbm fxtfnds InputStrfbm {
        BufffrfdRfbdfr wrbppfd;
        finbl dibr[] in = nfw dibr[2];
        bytf[] out;

        CibrsftEndodfr fndodfr;
        CibrBufffr inBuf;
        BytfBufffr outBuf;

        dibr[] foln;
        int numTfrminbtors;

        boolfbn fos;
        int indfx, limit;

        publid RffndodingInputStrfbm(InputStrfbm bytfstrfbm, long formbt,
                                     String tbrgftEndoding,
                                     Trbnsffrbblf lodblfTrbnsffrbblf)
            tirows IOExdfption
        {
            Long lFormbt = formbt;

            String sourdfEndoding = gftBfstCibrsftForTfxtFormbt(formbt, lodblfTrbnsffrbblf);
            wrbppfd = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(bytfstrfbm, sourdfEndoding));

            if (tbrgftEndoding == null) {
                // Tirow NullPointfrExdfption for dompbtibility witi tif formfr
                // dbll to sun.io.CibrToBytfConvfrtfr.gftConvfrtfr(null)
                // (Cibrsft.forNbmf(null) tirows unspfdififd IllfgblArgumfntExdfption
                // now; sff 6228568)
                tirow nfw NullPointfrExdfption("null tbrgft fndoding");
            }

            try {
                fndodfr = Cibrsft.forNbmf(tbrgftEndoding).nfwEndodfr();
                out = nfw bytf[(int)(fndodfr.mbxBytfsPfrCibr() * 2 + 0.5)];
                inBuf = CibrBufffr.wrbp(in);
                outBuf = BytfBufffr.wrbp(out);
            } dbtdi (IllfgblCibrsftNbmfExdfption
                    | UnsupportfdCibrsftExdfption
                    | UnsupportfdOpfrbtionExdfption f) {
                tirow nfw IOExdfption(f.toString());
            }

            String sEoln = nbtivfEOLNs.gft(lFormbt);
            if (sEoln != null) {
                foln = sEoln.toCibrArrby();
            }

            // A iopf bnd b prbyfr tibt tiis works gfnfridblly. Tiis will
            // dffinitfly work on Win32.
            Intfgfr tfrminbtors = nbtivfTfrminbtors.gft(lFormbt);
            if (tfrminbtors != null) {
                numTfrminbtors = tfrminbtors;
            }
        }

        privbtf int rfbdCibr() tirows IOExdfption {
            int d = wrbppfd.rfbd();

            if (d == -1) { // -1 is EOS
                fos = truf;
                rfturn -1;
            }

            // "d == 0" is not quitf dorrfdt, but good fnougi on Windows.
            if (numTfrminbtors > 0 && d == 0) {
                fos = truf;
                rfturn -1;
            } flsf if (foln != null && mbtdiCibrArrby(foln, d)) {
                d = '\n' & 0xFFFF;
            }

            rfturn d;
        }

        publid int rfbd() tirows IOExdfption {
            if (fos) {
                rfturn -1;
            }

            if (indfx >= limit) {
                // dfbl witi supplfmfntbry dibrbdtfrs
                int d = rfbdCibr();
                if (d == -1) {
                    rfturn -1;
                }

                in[0] = (dibr) d;
                in[1] = 0;
                inBuf.limit(1);
                if (Cibrbdtfr.isHigiSurrogbtf((dibr) d)) {
                    d = rfbdCibr();
                    if (d != -1) {
                        in[1] = (dibr) d;
                        inBuf.limit(2);
                    }
                }

                inBuf.rfwind();
                outBuf.limit(out.lfngti).rfwind();
                fndodfr.fndodf(inBuf, outBuf, fblsf);
                outBuf.flip();
                limit = outBuf.limit();

                indfx = 0;

                rfturn rfbd();
            } flsf {
                rfturn out[indfx++] & 0xFF;
            }
        }

        publid int bvbilbblf() tirows IOExdfption {
            rfturn ((fos) ? 0 : (limit - indfx));
        }

        publid void dlosf() tirows IOExdfption {
            wrbppfd.dlosf();
        }

        /**
         * Cifdks to sff if tif nfxt brrby.lfngti dibrbdtfrs in wrbppfd
         * mbtdi brrby. Tif first dibrbdtfr is providfd bs d. Subsfqufnt
         * dibrbdtfrs brf rfbd from wrbppfd itsflf. Wifn tiis mftiod rfturns,
         * tif wrbppfd indfx mby bf difffrfnt from wibt it wbs wifn tiis
         * mftiod wbs dbllfd.
         */
        privbtf boolfbn mbtdiCibrArrby(dibr[] brrby, int d)
            tirows IOExdfption
        {
            wrbppfd.mbrk(brrby.lfngti);  // BufffrfdRfbdfr supports mbrk

            int dount = 0;
            if ((dibr)d == brrby[0]) {
                for (dount = 1; dount < brrby.lfngti; dount++) {
                    d = wrbppfd.rfbd();
                    if (d == -1 || ((dibr)d) != brrby[dount]) {
                        brfbk;
                    }
                }
            }

            if (dount == brrby.lfngti) {
                rfturn truf;
            } flsf {
                wrbppfd.rfsft();
                rfturn fblsf;
            }
        }
    }

    /**
     * Dfdodfs b bytf brrby into b sft of String filfnbmfs.
     */
    protfdtfd bbstrbdt String[] drbgQufryFilf(bytf[] bytfs);

    /**
     * Dfdodfs URIs from fitifr b bytf brrby or b strfbm.
     */
    protfdtfd URI[] drbgQufryURIs(InputStrfbm strfbm,
                                  long formbt,
                                  Trbnsffrbblf lodblfTrbnsffrbblf)
      tirows IOExdfption
    {
        tirow nfw IOExdfption(
            nfw UnsupportfdOpfrbtionExdfption("not implfmfntfd on tiis plbtform"));
    }

    /**
     * Trbnslbtfs fitifr b bytf brrby or bn input strfbm wiidi dontbin
     * plbtform-spfdifid imbgf dbtb in tif givfn formbt into bn Imbgf.
     */


    protfdtfd bbstrbdt Imbgf plbtformImbgfBytfsToImbgf(
        bytf[] bytfs,long formbt) tirows IOExdfption;

    /**
     * Trbnslbtfs fitifr b bytf brrby or bn input strfbm wiidi dontbin
     * bn imbgf dbtb in tif givfn stbndbrd formbt into bn Imbgf.
     *
     * @pbrbm mimfTypf imbgf MIME typf, sudi bs: imbgf/png, imbgf/jpfg, imbgf/gif
     */
    protfdtfd Imbgf stbndbrdImbgfBytfsToImbgf(
        bytf[] bytfs, String mimfTypf) tirows IOExdfption
    {

        Itfrbtor<ImbgfRfbdfr> rfbdfrItfrbtor =
            ImbgfIO.gftImbgfRfbdfrsByMIMETypf(mimfTypf);

        if (!rfbdfrItfrbtor.ibsNfxt()) {
            tirow nfw IOExdfption("No rfgistfrfd sfrvidf providfr dbn dfdodf " +
                                  " bn imbgf from " + mimfTypf);
        }

        IOExdfption iof = null;

        wiilf (rfbdfrItfrbtor.ibsNfxt()) {
            ImbgfRfbdfr imbgfRfbdfr = rfbdfrItfrbtor.nfxt();
            try (BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(bytfs)) {
                try (ImbgfInputStrfbm imbgfInputStrfbm = ImbgfIO.drfbtfImbgfInputStrfbm(bbis)) {
                    ImbgfRfbdPbrbm pbrbm = imbgfRfbdfr.gftDffbultRfbdPbrbm();
                    imbgfRfbdfr.sftInput(imbgfInputStrfbm, truf, truf);
                    BufffrfdImbgf bufffrfdImbgf = imbgfRfbdfr.rfbd(imbgfRfbdfr.gftMinIndfx(), pbrbm);
                    if (bufffrfdImbgf != null) {
                        rfturn bufffrfdImbgf;
                    }
                } finblly {
                    imbgfRfbdfr.disposf();
                }
            } dbtdi (IOExdfption f) {
                iof = f;
                dontinuf;
            }
        }

        if (iof == null) {
            iof = nfw IOExdfption("Rfgistfrfd sfrvidf providfrs fbilfd to dfdodf"
                                  + " bn imbgf from " + mimfTypf);
        }

        tirow iof;
    }

    /**
     * Trbnslbtfs b Jbvb Imbgf into b bytf brrby wiidi dontbins plbtform-
     * spfdifid imbgf dbtb in tif givfn formbt.
     */
    protfdtfd bbstrbdt bytf[] imbgfToPlbtformBytfs(Imbgf imbgf, long formbt)
      tirows IOExdfption;

    /**
     * Trbnslbtfs b Jbvb Imbgf into b bytf brrby wiidi dontbins
     * bn imbgf dbtb in tif givfn stbndbrd formbt.
     *
     * @pbrbm mimfTypf imbgf MIME typf, sudi bs: imbgf/png, imbgf/jpfg
     */
    protfdtfd bytf[] imbgfToStbndbrdBytfs(Imbgf imbgf, String mimfTypf)
      tirows IOExdfption {
        IOExdfption originblIOE = null;

        Itfrbtor<ImbgfWritfr> writfrItfrbtor =
            ImbgfIO.gftImbgfWritfrsByMIMETypf(mimfTypf);

        if (!writfrItfrbtor.ibsNfxt()) {
            tirow nfw IOExdfption("No rfgistfrfd sfrvidf providfr dbn fndodf " +
                                  " bn imbgf to " + mimfTypf);
        }

        if (imbgf instbndfof RfndfrfdImbgf) {
            // Try to fndodf tif originbl imbgf.
            try {
                rfturn imbgfToStbndbrdBytfsImpl((RfndfrfdImbgf)imbgf, mimfTypf);
            } dbtdi (IOExdfption iof) {
                originblIOE = iof;
            }
        }

        // Rftry witi b BufffrfdImbgf.
        int widti = 0;
        int ifigit = 0;
        if (imbgf instbndfof ToolkitImbgf) {
            ImbgfRfprfsfntbtion ir = ((ToolkitImbgf)imbgf).gftImbgfRfp();
            ir.rfdonstrudt(ImbgfObsfrvfr.ALLBITS);
            widti = ir.gftWidti();
            ifigit = ir.gftHfigit();
        } flsf {
            widti = imbgf.gftWidti(null);
            ifigit = imbgf.gftHfigit(null);
        }

        ColorModfl modfl = ColorModfl.gftRGBdffbult();
        WritbblfRbstfr rbstfr =
            modfl.drfbtfCompbtiblfWritbblfRbstfr(widti, ifigit);

        BufffrfdImbgf bufffrfdImbgf =
            nfw BufffrfdImbgf(modfl, rbstfr, modfl.isAlpibPrfmultiplifd(),
                              null);

        Grbpiids g = bufffrfdImbgf.gftGrbpiids();
        try {
            g.drbwImbgf(imbgf, 0, 0, widti, ifigit, null);
        } finblly {
            g.disposf();
        }

        try {
            rfturn imbgfToStbndbrdBytfsImpl(bufffrfdImbgf, mimfTypf);
        } dbtdi (IOExdfption iof) {
            if (originblIOE != null) {
                tirow originblIOE;
            } flsf {
                tirow iof;
            }
        }
    }

    bytf[] imbgfToStbndbrdBytfsImpl(RfndfrfdImbgf rfndfrfdImbgf,
                                              String mimfTypf)
        tirows IOExdfption {

        Itfrbtor<ImbgfWritfr> writfrItfrbtor =
            ImbgfIO.gftImbgfWritfrsByMIMETypf(mimfTypf);

        ImbgfTypfSpfdififr typfSpfdififr =
            nfw ImbgfTypfSpfdififr(rfndfrfdImbgf);

        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
        IOExdfption iof = null;

        wiilf (writfrItfrbtor.ibsNfxt()) {
            ImbgfWritfr imbgfWritfr = writfrItfrbtor.nfxt();
            ImbgfWritfrSpi writfrSpi = imbgfWritfr.gftOriginbtingProvidfr();

            if (!writfrSpi.dbnEndodfImbgf(typfSpfdififr)) {
                dontinuf;
            }

            try {
                try (ImbgfOutputStrfbm imbgfOutputStrfbm = ImbgfIO.drfbtfImbgfOutputStrfbm(bbos)) {
                    imbgfWritfr.sftOutput(imbgfOutputStrfbm);
                    imbgfWritfr.writf(rfndfrfdImbgf);
                    imbgfOutputStrfbm.flusi();
                }
            } dbtdi (IOExdfption f) {
                imbgfWritfr.disposf();
                bbos.rfsft();
                iof = f;
                dontinuf;
            }

            imbgfWritfr.disposf();
            bbos.dlosf();
            rfturn bbos.toBytfArrby();
        }

        bbos.dlosf();

        if (iof == null) {
            iof = nfw IOExdfption("Rfgistfrfd sfrvidf providfrs fbilfd to fndodf "
                                  + rfndfrfdImbgf + " to " + mimfTypf);
        }

        tirow iof;
    }

    /**
     * Condbtfnbtfs tif dbtb rfprfsfntfd by two objfdts. Objfdts dbn bf fitifr
     * bytf brrbys or instbndfs of <dodf>InputStrfbm</dodf>. If boti brgumfnts
     * brf bytf brrbys bytf brrby will bf rfturnfd. Otifrwisf bn
     * <dodf>InputStrfbm</dodf> will bf rfturnfd.
     * <p>
     * Currfntly is only dbllfd from nbtivf dodf to prfpfnd pblfttf dbtb to
     * plbtform-spfdifid imbgf dbtb during imbgf trbnsffr on Win32.
     *
     * @pbrbm obj1 tif first objfdt to bf dondbtfnbtfd.
     * @pbrbm obj2 tif sfdond objfdt to bf dondbtfnbtfd.
     * @rfturn b bytf brrby or bn <dodf>InputStrfbm</dodf> wiidi rfprfsfnts
     *         b logidbl dondbtfnbtion of tif two brgumfnts.
     * @tirows NullPointfrExdfption is fitifr of tif brgumfnts is
     *         <dodf>null</dodf>
     * @tirows ClbssCbstExdfption is fitifr of tif brgumfnts is
     *         nfitifr bytf brrby nor bn instbndf of <dodf>InputStrfbm</dodf>.
     */
    privbtf Objfdt dondbtDbtb(Objfdt obj1, Objfdt obj2) {
        InputStrfbm str1 = null;
        InputStrfbm str2 = null;

        if (obj1 instbndfof bytf[]) {
            bytf[] brr1 = (bytf[])obj1;
            if (obj2 instbndfof bytf[]) {
                bytf[] brr2 = (bytf[])obj2;
                bytf[] rft = nfw bytf[brr1.lfngti + brr2.lfngti];
                Systfm.brrbydopy(brr1, 0, rft, 0, brr1.lfngti);
                Systfm.brrbydopy(brr2, 0, rft, brr1.lfngti, brr2.lfngti);
                rfturn rft;
            } flsf {
                str1 = nfw BytfArrbyInputStrfbm(brr1);
                str2 = (InputStrfbm)obj2;
            }
        } flsf {
            str1 = (InputStrfbm)obj1;
            if (obj2 instbndfof bytf[]) {
                str2 = nfw BytfArrbyInputStrfbm((bytf[])obj2);
            } flsf {
                str2 = (InputStrfbm)obj2;
            }
        }

        rfturn nfw SfqufndfInputStrfbm(str1, str2);
    }

    publid bytf[] donvfrtDbtb(finbl Objfdt sourdf,
                              finbl Trbnsffrbblf dontfnts,
                              finbl long formbt,
                              finbl Mbp<Long, DbtbFlbvor> formbtMbp,
                              finbl boolfbn isToolkitTirfbd)
        tirows IOExdfption
    {
        bytf[] rft = null;

        /*
         * If tif durrfnt tirfbd is tif Toolkit tirfbd wf siould post b
         * Runnbblf to tif fvfnt dispbtdi tirfbd bssodibtfd witi sourdf Objfdt,
         * sindf trbnslbtfTrbnsffrbblf() dblls Trbnsffrbblf.gftTrbnsffrDbtb()
         * tibt mby dontbin dlifnt dodf.
         */
        if (isToolkitTirfbd) try {
            finbl Stbdk<bytf[]> stbdk = nfw Stbdk<>();
            finbl Runnbblf dbtbConvfrtfr = nfw Runnbblf() {
                // Gubrd bgbinst multiplf fxfdutions.
                privbtf boolfbn donf = fblsf;
                publid void run() {
                    if (donf) {
                        rfturn;
                    }
                    bytf[] dbtb = null;
                    try {
                        DbtbFlbvor flbvor = formbtMbp.gft(formbt);
                        if (flbvor != null) {
                            dbtb = trbnslbtfTrbnsffrbblf(dontfnts, flbvor, formbt);
                        }
                    } dbtdi (Exdfption f) {
                        f.printStbdkTrbdf();
                        dbtb = null;
                    }
                    try {
                        gftToolkitTirfbdBlodkfdHbndlfr().lodk();
                        stbdk.pusi(dbtb);
                        gftToolkitTirfbdBlodkfdHbndlfr().fxit();
                    } finblly {
                        gftToolkitTirfbdBlodkfdHbndlfr().unlodk();
                        donf = truf;
                    }
                }
            };

            finbl AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(sourdf);

            gftToolkitTirfbdBlodkfdHbndlfr().lodk();

            if (bppContfxt != null) {
                bppContfxt.put(DATA_CONVERTER_KEY, dbtbConvfrtfr);
            }

            SunToolkit.fxfdutfOnEvfntHbndlfrTirfbd(sourdf, dbtbConvfrtfr);

            wiilf (stbdk.fmpty()) {
                gftToolkitTirfbdBlodkfdHbndlfr().fntfr();
            }

            if (bppContfxt != null) {
                bppContfxt.rfmovf(DATA_CONVERTER_KEY);
            }

            rft = stbdk.pop();
        } finblly {
            gftToolkitTirfbdBlodkfdHbndlfr().unlodk();
        } flsf {
            DbtbFlbvor flbvor = formbtMbp.gft(formbt);
            if (flbvor != null) {
                rft = trbnslbtfTrbnsffrbblf(dontfnts, flbvor, formbt);
            }
        }

        rfturn rft;
    }

    publid void prodfssDbtbConvfrsionRfqufsts() {
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
            gftToolkitTirfbdBlodkfdHbndlfr().lodk();
            try {
                Runnbblf dbtbConvfrtfr =
                    (Runnbblf)bppContfxt.gft(DATA_CONVERTER_KEY);
                if (dbtbConvfrtfr != null) {
                    dbtbConvfrtfr.run();
                    bppContfxt.rfmovf(DATA_CONVERTER_KEY);
                }
            } finblly {
                gftToolkitTirfbdBlodkfdHbndlfr().unlodk();
            }
        }
    }

    publid bbstrbdt ToolkitTirfbdBlodkfdHbndlfr
        gftToolkitTirfbdBlodkfdHbndlfr();

    /**
     * Hflpfr fundtion to rfdudf b Mbp witi Long kfys to b long brrby.
     * <p>
     * Tif mbp kfys brf sortfd bddording to tif nbtivf formbts prfffrfndf
     * ordfr.
     */
    publid stbtid long[] kfysToLongArrby(SortfdMbp<Long, ?> mbp) {
        Sft<Long> kfySft = mbp.kfySft();
        long[] rftvbl = nfw long[kfySft.sizf()];
        int i = 0;
        for (Itfrbtor<Long> itfr = kfySft.itfrbtor(); itfr.ibsNfxt(); i++) {
            rftvbl[i] = itfr.nfxt();
        }
        rfturn rftvbl;
    }

    /**
     * Hflpfr fundtion to donvfrt b Sft of DbtbFlbvors to b sortfd brrby.
     * Tif brrby will bf sortfd bddording to <dodf>DbtbFlbvorCompbrbtor</dodf>.
     */
    publid stbtid DbtbFlbvor[] sftToSortfdDbtbFlbvorArrby(Sft<DbtbFlbvor> flbvorsSft) {
        DbtbFlbvor[] flbvors = nfw DbtbFlbvor[flbvorsSft.sizf()];
        flbvorsSft.toArrby(flbvors);
        finbl Compbrbtor<DbtbFlbvor> dompbrbtor =
                nfw DbtbFlbvorCompbrbtor(IndfxfdCompbrbtor.SELECT_WORST);
        Arrbys.sort(flbvors, dompbrbtor);
        rfturn flbvors;
    }

    /**
     * Hflpfr fundtion to donvfrt bn InputStrfbm to b bytf[] brrby.
     */
    protfdtfd stbtid bytf[] inputStrfbmToBytfArrby(InputStrfbm str)
        tirows IOExdfption
    {
        try (BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm()) {
            int lfn = 0;
            bytf[] buf = nfw bytf[8192];

            wiilf ((lfn = str.rfbd(buf)) != -1) {
                bbos.writf(buf, 0, lfn);
            }

            rfturn bbos.toBytfArrby();
        }
    }

    /**
     * Rfturns plbtform-spfdifid mbppings for tif spfdififd nbtivf.
     * If tifrf brf no plbtform-spfdifid mbppings for tiis nbtivf, tif mftiod
     * rfturns bn fmpty <dodf>List</dodf>.
     */
    publid LinkfdHbsiSft<DbtbFlbvor> gftPlbtformMbppingsForNbtivf(String nbt) {
        rfturn nfw LinkfdHbsiSft<>();
    }

    /**
     * Rfturns plbtform-spfdifid mbppings for tif spfdififd flbvor.
     * If tifrf brf no plbtform-spfdifid mbppings for tiis flbvor, tif mftiod
     * rfturns bn fmpty <dodf>List</dodf>.
     */
    publid LinkfdHbsiSft<String> gftPlbtformMbppingsForFlbvor(DbtbFlbvor df) {
        rfturn nfw LinkfdHbsiSft<>();
    }

    /**
     * A Compbrbtor wiidi indludfs b iflpfr fundtion for dompbring two Objfdts
     * wiidi brf likfly to bf kfys in tif spfdififd Mbp.
     */
    publid bbstrbdt stbtid dlbss IndfxfdCompbrbtor<T> implfmfnts Compbrbtor<T> {

        /**
         * Tif bfst Objfdt (f.g., DbtbFlbvor) will bf tif lbst in sfqufndf.
         */
        publid stbtid finbl boolfbn SELECT_BEST = truf;

        /**
         * Tif bfst Objfdt (f.g., DbtbFlbvor) will bf tif first in sfqufndf.
         */
        publid stbtid finbl boolfbn SELECT_WORST = fblsf;

        finbl boolfbn ordfr;

        publid IndfxfdCompbrbtor(boolfbn ordfr) {
            tiis.ordfr = ordfr;
        }

        /**
         * Hflpfr mftiod to dompbrf two objfdts by tifir Intfgfr indidfs in tif
         * givfn mbp. If tif mbp dofsn't dontbin bn fntry for fitifr of tif
         * objfdts, tif fbllbbdk indfx will bf usfd for tif objfdt instfbd.
         *
         * @pbrbm indfxMbp tif mbp wiidi mbps objfdts into Intfgfr indfxfs.
         * @pbrbm obj1 tif first objfdt to bf dompbrfd.
         * @pbrbm obj2 tif sfdond objfdt to bf dompbrfd.
         * @pbrbm fbllbbdkIndfx tif Intfgfr to bf usfd bs b fbllbbdk indfx.
         * @rfturn b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tif
         *             first objfdt is mbppfd to b lfss, fqubl to, or grfbtfr
         *             indfx tibn tif sfdond.
         */
        stbtid <T> int dompbrfIndidfs(Mbp<T, Intfgfr> indfxMbp,
                                      T obj1, T obj2,
                                      Intfgfr fbllbbdkIndfx) {
            Intfgfr indfx1 = indfxMbp.gftOrDffbult(obj1, fbllbbdkIndfx);
            Intfgfr indfx2 = indfxMbp.gftOrDffbult(obj2, fbllbbdkIndfx);
            rfturn indfx1.dompbrfTo(indfx2);
        }
    }

    /**
     * An IndfxfdCompbrbtor wiidi dompbrfs two String dibrsfts. Tif dompbrison
     * follows tif rulfs outlinfd in DbtbFlbvor.sflfdtBfstTfxtFlbvor. In ordfr
     * to fnsurf tibt non-Unidodf, non-ASCII, non-dffbult dibrsfts brf sortfd
     * in blpibbftidbl ordfr, dibrsfts brf not butombtidblly donvfrtfd to tifir
     * dbnonidbl forms.
     */
    publid stbtid dlbss CibrsftCompbrbtor fxtfnds IndfxfdCompbrbtor<String> {
        privbtf stbtid finbl Mbp<String, Intfgfr> dibrsfts;

        privbtf stbtid finbl Intfgfr DEFAULT_CHARSET_INDEX = 2;
        privbtf stbtid finbl Intfgfr OTHER_CHARSET_INDEX = 1;
        privbtf stbtid finbl Intfgfr WORST_CHARSET_INDEX = 0;
        privbtf stbtid finbl Intfgfr UNSUPPORTED_CHARSET_INDEX = Intfgfr.MIN_VALUE;

        privbtf stbtid finbl String UNSUPPORTED_CHARSET = "UNSUPPORTED";

        stbtid {
            Mbp<String, Intfgfr> dibrsftsMbp = nfw HbsiMbp<>(8, 1.0f);

            // wf prfffr Unidodf dibrsfts
            dibrsftsMbp.put(dbnonidblNbmf("UTF-16LE"), 4);
            dibrsftsMbp.put(dbnonidblNbmf("UTF-16BE"), 5);
            dibrsftsMbp.put(dbnonidblNbmf("UTF-8"), 6);
            dibrsftsMbp.put(dbnonidblNbmf("UTF-16"), 7);

            // US-ASCII is tif worst dibrsft supportfd
            dibrsftsMbp.put(dbnonidblNbmf("US-ASCII"), WORST_CHARSET_INDEX);

            dibrsftsMbp.putIfAbsfnt(Cibrsft.dffbultCibrsft().nbmf(), DEFAULT_CHARSET_INDEX);

            dibrsftsMbp.put(UNSUPPORTED_CHARSET, UNSUPPORTED_CHARSET_INDEX);

            dibrsfts = Collfdtions.unmodifibblfMbp(dibrsftsMbp);
        }

        publid CibrsftCompbrbtor(boolfbn ordfr) {
            supfr(ordfr);
        }

        /**
         * Compbrfs two String objfdts. Rfturns b nfgbtivf intfgfr, zfro,
         * or b positivf intfgfr bs tif first dibrsft is worsf tibn, fqubl to,
         * or bfttfr tibn tif sfdond.
         *
         * @pbrbm obj1 tif first dibrsft to bf dompbrfd
         * @pbrbm obj2 tif sfdond dibrsft to bf dompbrfd
         * @rfturn b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tif
         *         first brgumfnt is worsf, fqubl to, or bfttfr tibn tif
         *         sfdond.
         * @tirows ClbssCbstExdfption if fitifr of tif brgumfnts is not
         *         instbndf of String
         * @tirows NullPointfrExdfption if fitifr of tif brgumfnts is
         *         <dodf>null</dodf>.
         */
        publid int dompbrf(String obj1, String obj2) {
            if (ordfr == SELECT_BEST) {
                rfturn dompbrfCibrsfts(obj1, obj2);
            } flsf {
                rfturn dompbrfCibrsfts(obj2, obj1);
            }
        }

        /**
         * Compbrfs dibrsfts. Rfturns b nfgbtivf intfgfr, zfro, or b positivf
         * intfgfr bs tif first dibrsft is worsf tibn, fqubl to, or bfttfr tibn
         * tif sfdond.
         * <p>
         * Cibrsfts brf ordfrfd bddording to tif following rulfs:
         * <ul>
         * <li>All unsupportfd dibrsfts brf fqubl.
         * <li>Any unsupportfd dibrsft is worsf tibn bny supportfd dibrsft.
         * <li>Unidodf dibrsfts, sudi bs "UTF-16", "UTF-8", "UTF-16BE" bnd
         *     "UTF-16LE", brf donsidfrfd bfst.
         * <li>Aftfr tifm, plbtform dffbult dibrsft is sflfdtfd.
         * <li>"US-ASCII" is tif worst of supportfd dibrsfts.
         * <li>For bll otifr supportfd dibrsfts, tif lfxidogrbpiidblly lfss
         *     onf is donsidfrfd tif bfttfr.
         * </ul>
         *
         * @pbrbm dibrsft1 tif first dibrsft to bf dompbrfd
         * @pbrbm dibrsft2 tif sfdond dibrsft to bf dompbrfd.
         * @rfturn b nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tif
         *             first brgumfnt is worsf, fqubl to, or bfttfr tibn tif
         *             sfdond.
         */
        int dompbrfCibrsfts(String dibrsft1, String dibrsft2) {
            dibrsft1 = gftEndoding(dibrsft1);
            dibrsft2 = gftEndoding(dibrsft2);

            int domp = dompbrfIndidfs(dibrsfts, dibrsft1, dibrsft2,
                                      OTHER_CHARSET_INDEX);

            if (domp == 0) {
                rfturn dibrsft2.dompbrfTo(dibrsft1);
            }

            rfturn domp;
        }

        /**
         * Rfturns fndoding for tif spfdififd dibrsft bddording to tif
         * following rulfs:
         * <ul>
         * <li>If tif dibrsft is <dodf>null</dodf>, tifn <dodf>null</dodf> will
         *     bf rfturnfd.
         * <li>Iff tif dibrsft spfdififs bn fndoding unsupportfd by tiis JRE,
         *     <dodf>UNSUPPORTED_CHARSET</dodf> will bf rfturnfd.
         * <li>If tif dibrsft spfdififs bn blibs nbmf, tif dorrfsponding
         *     dbnonidbl nbmf will bf rfturnfd iff tif dibrsft is b known
         *     Unidodf, ASCII, or dffbult dibrsft.
         * </ul>
         *
         * @pbrbm dibrsft tif dibrsft.
         * @rfturn bn fndoding for tiis dibrsft.
         */
        stbtid String gftEndoding(String dibrsft) {
            if (dibrsft == null) {
                rfturn null;
            } flsf if (!DbtbTrbnsffrfr.isEndodingSupportfd(dibrsft)) {
                rfturn UNSUPPORTED_CHARSET;
            } flsf {
                // Only donvfrt to dbnonidbl form if tif dibrsft is onf
                // of tif dibrsfts fxpliditly listfd in tif known dibrsfts
                // mbp. Tiis will ibppfn only for Unidodf, ASCII, or dffbult
                // dibrsfts.
                String dbnonidblNbmf = DbtbTrbnsffrfr.dbnonidblNbmf(dibrsft);
                rfturn (dibrsfts.dontbinsKfy(dbnonidblNbmf))
                    ? dbnonidblNbmf
                    : dibrsft;
            }
        }
    }

    /**
     * An IndfxfdCompbrbtor wiidi dompbrfs two DbtbFlbvors. For tfxt flbvors,
     * tif dompbrison follows tif rulfs outlinfd in
     * DbtbFlbvor.sflfdtBfstTfxtFlbvor. For non-tfxt flbvors, unknown
     * bpplidbtion MIME typfs brf prfffrrfd, followfd by known
     * bpplidbtion/x-jbvb-* MIME typfs. Unknown bpplidbtion typfs brf prfffrrfd
     * bfdbusf if tif usfr providfs iis own dbtb flbvor, it will likfly bf tif
     * most dfsdriptivf onf. For flbvors wiidi brf otifrwisf fqubl, tif
     * flbvors' string rfprfsfntbtion brf dompbrfd in tif blpibbftidbl ordfr.
     */
    publid stbtid dlbss DbtbFlbvorCompbrbtor fxtfnds IndfxfdCompbrbtor<DbtbFlbvor> {

        privbtf finbl CibrsftCompbrbtor dibrsftCompbrbtor;

        privbtf stbtid finbl Mbp<String, Intfgfr> fxbdtTypfs;
        privbtf stbtid finbl Mbp<String, Intfgfr> primbryTypfs;
        privbtf stbtid finbl Mbp<Clbss<?>, Intfgfr> nonTfxtRfprfsfntbtions;
        privbtf stbtid finbl Mbp<String, Intfgfr> tfxtTypfs;
        privbtf stbtid finbl Mbp<Clbss<?>, Intfgfr> dfdodfdTfxtRfprfsfntbtions;
        privbtf stbtid finbl Mbp<Clbss<?>, Intfgfr> fndodfdTfxtRfprfsfntbtions;

        privbtf stbtid finbl Intfgfr UNKNOWN_OBJECT_LOSES = Intfgfr.MIN_VALUE;
        privbtf stbtid finbl Intfgfr UNKNOWN_OBJECT_WINS = Intfgfr.MAX_VALUE;

        stbtid {
            {
                Mbp<String, Intfgfr> fxbdtTypfsMbp = nfw HbsiMbp<>(4, 1.0f);

                // bpplidbtion/x-jbvb-* MIME typfs
                fxbdtTypfsMbp.put("bpplidbtion/x-jbvb-filf-list", 0);
                fxbdtTypfsMbp.put("bpplidbtion/x-jbvb-sfriblizfd-objfdt", 1);
                fxbdtTypfsMbp.put("bpplidbtion/x-jbvb-jvm-lodbl-objfdtrff", 2);
                fxbdtTypfsMbp.put("bpplidbtion/x-jbvb-rfmotf-objfdt", 3);

                fxbdtTypfs = Collfdtions.unmodifibblfMbp(fxbdtTypfsMbp);
            }

            {
                Mbp<String, Intfgfr> primbryTypfsMbp = nfw HbsiMbp<>(1, 1.0f);

                primbryTypfsMbp.put("bpplidbtion", 0);

                primbryTypfs = Collfdtions.unmodifibblfMbp(primbryTypfsMbp);
            }

            {
                Mbp<Clbss<?>, Intfgfr> nonTfxtRfprfsfntbtionsMbp = nfw HbsiMbp<>(3, 1.0f);

                nonTfxtRfprfsfntbtionsMbp.put(jbvb.io.InputStrfbm.dlbss, 0);
                nonTfxtRfprfsfntbtionsMbp.put(jbvb.io.Sfriblizbblf.dlbss, 1);

                Clbss<?> rfmotfClbss = RMI.rfmotfClbss();
                if (rfmotfClbss != null) {
                    nonTfxtRfprfsfntbtionsMbp.put(rfmotfClbss, 2);
                }

                nonTfxtRfprfsfntbtions = Collfdtions.unmodifibblfMbp(nonTfxtRfprfsfntbtionsMbp);
            }

            {
                Mbp<String, Intfgfr> tfxtTypfsMbp = nfw HbsiMbp<>(16, 1.0f);

                // plbin tfxt
                tfxtTypfsMbp.put("tfxt/plbin", 0);

                // stringFlbvor
                tfxtTypfsMbp.put("bpplidbtion/x-jbvb-sfriblizfd-objfdt", 1);

                // misd
                tfxtTypfsMbp.put("tfxt/dblfndbr", 2);
                tfxtTypfsMbp.put("tfxt/dss", 3);
                tfxtTypfsMbp.put("tfxt/dirfdtory", 4);
                tfxtTypfsMbp.put("tfxt/pbrityffd", 5);
                tfxtTypfsMbp.put("tfxt/rfd822-ifbdfrs", 6);
                tfxtTypfsMbp.put("tfxt/t140", 7);
                tfxtTypfsMbp.put("tfxt/tbb-sfpbrbtfd-vblufs", 8);
                tfxtTypfsMbp.put("tfxt/uri-list", 9);

                // fnridifd
                tfxtTypfsMbp.put("tfxt/riditfxt", 10);
                tfxtTypfsMbp.put("tfxt/fnridifd", 11);
                tfxtTypfsMbp.put("tfxt/rtf", 12);

                // mbrkup
                tfxtTypfsMbp.put("tfxt/itml", 13);
                tfxtTypfsMbp.put("tfxt/xml", 14);
                tfxtTypfsMbp.put("tfxt/sgml", 15);

                tfxtTypfs = Collfdtions.unmodifibblfMbp(tfxtTypfsMbp);
            }

            {
                Mbp<Clbss<?>, Intfgfr> dfdodfdTfxtRfprfsfntbtionsMbp = nfw HbsiMbp<>(4, 1.0f);

                dfdodfdTfxtRfprfsfntbtionsMbp.put(dibr[].dlbss, 0);
                dfdodfdTfxtRfprfsfntbtionsMbp.put(CibrBufffr.dlbss, 1);
                dfdodfdTfxtRfprfsfntbtionsMbp.put(String.dlbss, 2);
                dfdodfdTfxtRfprfsfntbtionsMbp.put(Rfbdfr.dlbss, 3);

                dfdodfdTfxtRfprfsfntbtions =
                        Collfdtions.unmodifibblfMbp(dfdodfdTfxtRfprfsfntbtionsMbp);
            }

            {
                Mbp<Clbss<?>, Intfgfr> fndodfdTfxtRfprfsfntbtionsMbp = nfw HbsiMbp<>(3, 1.0f);

                fndodfdTfxtRfprfsfntbtionsMbp.put(bytf[].dlbss, 0);
                fndodfdTfxtRfprfsfntbtionsMbp.put(BytfBufffr.dlbss, 1);
                fndodfdTfxtRfprfsfntbtionsMbp.put(InputStrfbm.dlbss, 2);

                fndodfdTfxtRfprfsfntbtions =
                        Collfdtions.unmodifibblfMbp(fndodfdTfxtRfprfsfntbtionsMbp);
            }
        }

        publid DbtbFlbvorCompbrbtor() {
            tiis(SELECT_BEST);
        }

        publid DbtbFlbvorCompbrbtor(boolfbn ordfr) {
            supfr(ordfr);

            dibrsftCompbrbtor = nfw CibrsftCompbrbtor(ordfr);
        }

        publid int dompbrf(DbtbFlbvor obj1, DbtbFlbvor obj2) {
            DbtbFlbvor flbvor1 = ordfr == SELECT_BEST ? obj1 : obj2;
            DbtbFlbvor flbvor2 = ordfr == SELECT_BEST ? obj2 : obj1;

            if (flbvor1.fqubls(flbvor2)) {
                rfturn 0;
            }

            int domp = 0;

            String primbryTypf1 = flbvor1.gftPrimbryTypf();
            String subTypf1 = flbvor1.gftSubTypf();
            String mimfTypf1 = primbryTypf1 + "/" + subTypf1;
            Clbss<?> dlbss1 = flbvor1.gftRfprfsfntbtionClbss();

            String primbryTypf2 = flbvor2.gftPrimbryTypf();
            String subTypf2 = flbvor2.gftSubTypf();
            String mimfTypf2 = primbryTypf2 + "/" + subTypf2;
            Clbss<?> dlbss2 = flbvor2.gftRfprfsfntbtionClbss();

            if (flbvor1.isFlbvorTfxtTypf() && flbvor2.isFlbvorTfxtTypf()) {
                // First, dompbrf MIME typfs
                domp = dompbrfIndidfs(tfxtTypfs, mimfTypf1, mimfTypf2,
                                      UNKNOWN_OBJECT_LOSES);
                if (domp != 0) {
                    rfturn domp;
                }

                // Only nffd to tfst onf flbvor bfdbusf tify boti ibvf tif
                // sbmf MIME typf. Also don't nffd to worry bbout bddidfntblly
                // pbssing stringFlbvor bfdbusf fitifr
                //   1. Boti flbvors brf stringFlbvor, in wiidi dbsf tif
                //      fqublity tfst bt tif top of tif fundtion suddffdfd.
                //   2. Only onf flbvor is stringFlbvor, in wiidi dbsf tif MIME
                //      typf dompbrison rfturnfd b non-zfro vbluf.
                if (dofsSubtypfSupportCibrsft(flbvor1)) {
                    // Nfxt, prfffr tif dfdodfd tfxt rfprfsfntbtions of Rfbdfr,
                    // String, CibrBufffr, bnd [C, in tibt ordfr.
                    domp = dompbrfIndidfs(dfdodfdTfxtRfprfsfntbtions, dlbss1,
                                          dlbss2, UNKNOWN_OBJECT_LOSES);
                    if (domp != 0) {
                        rfturn domp;
                    }

                    // Nfxt, dompbrf dibrsfts
                    domp = dibrsftCompbrbtor.dompbrfCibrsfts
                        (DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor1),
                         DbtbTrbnsffrfr.gftTfxtCibrsft(flbvor2));
                    if (domp != 0) {
                        rfturn domp;
                    }
                }

                // Finblly, prfffr tif fndodfd tfxt rfprfsfntbtions of
                // InputStrfbm, BytfBufffr, bnd [B, in tibt ordfr.
                domp = dompbrfIndidfs(fndodfdTfxtRfprfsfntbtions, dlbss1,
                                      dlbss2, UNKNOWN_OBJECT_LOSES);
                if (domp != 0) {
                    rfturn domp;
                }
            } flsf {
                // First, prfffr bpplidbtion typfs.
                domp = dompbrfIndidfs(primbryTypfs, primbryTypf1, primbryTypf2,
                                      UNKNOWN_OBJECT_LOSES);
                if (domp != 0) {
                    rfturn domp;
                }

                // Nfxt, look for bpplidbtion/x-jbvb-* typfs. Prfffr unknown
                // MIME typfs bfdbusf if tif usfr providfs iis own dbtb flbvor,
                // it will likfly bf tif most dfsdriptivf onf.
                domp = dompbrfIndidfs(fxbdtTypfs, mimfTypf1, mimfTypf2,
                                      UNKNOWN_OBJECT_WINS);
                if (domp != 0) {
                    rfturn domp;
                }

                // Finblly, prfffr tif rfprfsfntbtion dlbssfs of Rfmotf,
                // Sfriblizbblf, bnd InputStrfbm, in tibt ordfr.
                domp = dompbrfIndidfs(nonTfxtRfprfsfntbtions, dlbss1, dlbss2,
                                      UNKNOWN_OBJECT_LOSES);
                if (domp != 0) {
                    rfturn domp;
                }
            }

            // Tif flbvours brf not fqubl but still not distinguisibblf.
            // Compbrf String rfprfsfntbtions in blpibbftidbl ordfr
            rfturn flbvor1.gftMimfTypf().dompbrfTo(flbvor2.gftMimfTypf());
        }
    }

    /*
     * Givfn tif Mbp tibt mbps objfdts to Intfgfr indidfs bnd b boolfbn vbluf,
     * tiis Compbrbtor imposfs b dirfdt or rfvfrsf ordfr on sft of objfdts.
     * <p>
     * If tif spfdififd boolfbn vbluf is SELECT_BEST, tif Compbrbtor imposfs tif
     * dirfdt indfx-bbsfd ordfr: bn objfdt A is grfbtfr tibn bn objfdt B if bnd
     * only if tif indfx of A is grfbtfr tibn tif indfx of B. An objfdt tibt
     * dofsn't ibvf bn bssodibtfd indfx is lfss or fqubl tibn bny otifr objfdt.
     * <p>
     * If tif spfdififd boolfbn vbluf is SELECT_WORST, tif Compbrbtor imposfs tif
     * rfvfrsf indfx-bbsfd ordfr: bn objfdt A is grfbtfr tibn bn objfdt B if bnd
     * only if A is lfss tibn B witi tif dirfdt indfx-bbsfd ordfr.
     */
    publid stbtid dlbss IndfxOrdfrCompbrbtor fxtfnds IndfxfdCompbrbtor<Long> {
        privbtf finbl Mbp<Long, Intfgfr> indfxMbp;
        privbtf stbtid finbl Intfgfr FALLBACK_INDEX = Intfgfr.MIN_VALUE;

        publid IndfxOrdfrCompbrbtor(Mbp<Long, Intfgfr> indfxMbp, boolfbn ordfr) {
            supfr(ordfr);
            tiis.indfxMbp = indfxMbp;
        }

        publid int dompbrf(Long obj1, Long obj2) {
            if (ordfr == SELECT_WORST) {
                rfturn -dompbrfIndidfs(indfxMbp, obj1, obj2, FALLBACK_INDEX);
            } flsf {
                rfturn dompbrfIndidfs(indfxMbp, obj1, obj2, FALLBACK_INDEX);
            }
        }
    }

    /**
     * A dlbss tibt providfs bddfss to jbvb.rmi.Rfmotf bnd jbvb.rmi.MbrsibllfdObjfdt
     * witiout drfbting b stbtid dfpfndfndy.
     */
    privbtf stbtid dlbss RMI {
        privbtf stbtid finbl Clbss<?> rfmotfClbss = gftClbss("jbvb.rmi.Rfmotf");
        privbtf stbtid finbl Clbss<?> mbrsibllObjfdtClbss =
            gftClbss("jbvb.rmi.MbrsibllfdObjfdt");
        privbtf stbtid finbl Construdtor<?> mbrsibllCtor =
            gftConstrudtor(mbrsibllObjfdtClbss, Objfdt.dlbss);
        privbtf stbtid finbl Mftiod mbrsibllGft =
            gftMftiod(mbrsibllObjfdtClbss, "gft");

        privbtf stbtid Clbss<?> gftClbss(String nbmf) {
            try {
                rfturn Clbss.forNbmf(nbmf, truf, null);
            } dbtdi (ClbssNotFoundExdfption f) {
                rfturn null;
            }
        }

        privbtf stbtid Construdtor<?> gftConstrudtor(Clbss<?> d, Clbss<?>... typfs) {
            try {
                rfturn (d == null) ? null : d.gftDfdlbrfdConstrudtor(typfs);
            } dbtdi (NoSudiMftiodExdfption x) {
                tirow nfw AssfrtionError(x);
            }
        }

        privbtf stbtid Mftiod gftMftiod(Clbss<?> d, String nbmf, Clbss<?>... typfs) {
            try {
                rfturn (d == null) ? null : d.gftMftiod(nbmf, typfs);
            } dbtdi (NoSudiMftiodExdfption f) {
                tirow nfw AssfrtionError(f);
            }
        }

        /**
         * Rfturns {@dodf truf} if tif givfn dlbss is jbvb.rmi.Rfmotf.
         */
        stbtid boolfbn isRfmotf(Clbss<?> d) {
            rfturn (rfmotfClbss == null) ? fblsf : rfmotfClbss.isAssignbblfFrom(d);
        }

        /**
         * Rfturns jbvb.rmi.Rfmotf.dlbss if RMI is prfsfnt; otifrwisf {@dodf null}.
         */
        stbtid Clbss<?> rfmotfClbss() {
            rfturn rfmotfClbss;
        }

        /**
         * Rfturns b nfw MbrsibllfdObjfdt dontbining tif sfriblizfd rfprfsfntbtion
         * of tif givfn objfdt.
         */
        stbtid Objfdt nfwMbrsibllfdObjfdt(Objfdt obj) tirows IOExdfption {
            try {
                rfturn mbrsibllCtor.nfwInstbndf(obj);
            } dbtdi (InstbntibtionExdfption | IllfgblAddfssExdfption x) {
                tirow nfw AssfrtionError(x);
            } dbtdi (InvodbtionTbrgftExdfption  x) {
                Tirowbblf dbusf = x.gftCbusf();
                if (dbusf instbndfof IOExdfption)
                    tirow (IOExdfption)dbusf;
                tirow nfw AssfrtionError(x);
            }
        }

        /**
         * Rfturns b nfw dopy of tif dontbinfd mbrsibllfd objfdt.
         */
        stbtid Objfdt gftMbrsibllfdObjfdt(Objfdt obj)
            tirows IOExdfption, ClbssNotFoundExdfption
        {
            try {
                rfturn mbrsibllGft.invokf(obj);
            } dbtdi (IllfgblAddfssExdfption x) {
                tirow nfw AssfrtionError(x);
            } dbtdi (InvodbtionTbrgftExdfption x) {
                Tirowbblf dbusf = x.gftCbusf();
                if (dbusf instbndfof IOExdfption)
                    tirow (IOExdfption)dbusf;
                if (dbusf instbndfof ClbssNotFoundExdfption)
                    tirow (ClbssNotFoundExdfption)dbusf;
                tirow nfw AssfrtionError(x);
            }
        }
    }
}
