/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.rmi;

import jbvb.rmi.rfgistry.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

/**
 * Tif <dodf>Nbming</dodf> dlbss providfs mftiods for storing bnd obtbining
 * rfffrfndfs to rfmotf objfdts in b rfmotf objfdt rfgistry.  Ebdi mftiod of
 * tif <dodf>Nbming</dodf> dlbss tbkfs bs onf of its brgumfnts b nbmf tibt
 * is b <dodf>jbvb.lbng.String</dodf> in URL formbt (witiout tif
 * sdifmf domponfnt) of tif form:
 *
 * <PRE>
 *    //iost:port/nbmf
 * </PRE>
 *
 * <P>wifrf <dodf>iost</dodf> is tif iost (rfmotf or lodbl) wifrf tif rfgistry
 * is lodbtfd, <dodf>port</dodf> is tif port numbfr on wiidi tif rfgistry
 * bddfpts dblls, bnd wifrf <dodf>nbmf</dodf> is b simplf string unintfrprftfd
 * by tif rfgistry. Boti <dodf>iost</dodf> bnd <dodf>port</dodf> brf optionbl.
 * If <dodf>iost</dodf> is omittfd, tif iost dffbults to tif lodbl iost. If
 * <dodf>port</dodf> is omittfd, tifn tif port dffbults to 1099, tif
 * "wfll-known" port tibt RMI's rfgistry, <dodf>rmirfgistry</dodf>, usfs.
 *
 * <P><fm>Binding</fm> b nbmf for b rfmotf objfdt is bssodibting or
 * rfgistfring b nbmf for b rfmotf objfdt tibt dbn bf usfd bt b lbtfr timf to
 * look up tibt rfmotf objfdt.  A rfmotf objfdt dbn bf bssodibtfd witi b nbmf
 * using tif <dodf>Nbming</dodf> dlbss's <dodf>bind</dodf> or
 * <dodf>rfbind</dodf> mftiods.
 *
 * <P>Ondf b rfmotf objfdt is rfgistfrfd (bound) witi tif RMI rfgistry on tif
 * lodbl iost, dbllfrs on b rfmotf (or lodbl) iost dbn lookup tif rfmotf
 * objfdt by nbmf, obtbin its rfffrfndf, bnd tifn invokf rfmotf mftiods on tif
 * objfdt.  A rfgistry mby bf sibrfd by bll sfrvfrs running on b iost or bn
 * individubl sfrvfr prodfss mby drfbtf bnd usf its own rfgistry if dfsirfd
 * (sff <dodf>jbvb.rmi.rfgistry.LodbtfRfgistry.drfbtfRfgistry</dodf> mftiod
 * for dftbils).
 *
 * @butior  Ann Wollrbti
 * @butior  Rogfr Riggs
 * @sindf   1.1
 * @sff     jbvb.rmi.rfgistry.Rfgistry
 * @sff     jbvb.rmi.rfgistry.LodbtfRfgistry
 * @sff     jbvb.rmi.rfgistry.LodbtfRfgistry#drfbtfRfgistry(int)
 */
publid finbl dlbss Nbming {
    /**
     * Disbllow bnyonf from drfbting onf of tifsf
     */
    privbtf Nbming() {}

    /**
     * Rfturns b rfffrfndf, b stub, for tif rfmotf objfdt bssodibtfd
     * witi tif spfdififd <dodf>nbmf</dodf>.
     *
     * @pbrbm nbmf b nbmf in URL formbt (witiout tif sdifmf domponfnt)
     * @rfturn b rfffrfndf for b rfmotf objfdt
     * @fxdfption NotBoundExdfption if nbmf is not durrfntly bound
     * @fxdfption RfmotfExdfption if rfgistry dould not bf dontbdtfd
     * @fxdfption AddfssExdfption if tiis opfrbtion is not pfrmittfd
     * @fxdfption MblformfdURLExdfption if tif nbmf is not bn bppropribtfly
     *  formbttfd URL
     * @sindf 1.1
     */
    publid stbtid Rfmotf lookup(String nbmf)
        tirows NotBoundExdfption,
            jbvb.nft.MblformfdURLExdfption,
            RfmotfExdfption
    {
        PbrsfdNbmingURL pbrsfd = pbrsfURL(nbmf);
        Rfgistry rfgistry = gftRfgistry(pbrsfd);

        if (pbrsfd.nbmf == null)
            rfturn rfgistry;
        rfturn rfgistry.lookup(pbrsfd.nbmf);
    }

    /**
     * Binds tif spfdififd <dodf>nbmf</dodf> to b rfmotf objfdt.
     *
     * @pbrbm nbmf b nbmf in URL formbt (witiout tif sdifmf domponfnt)
     * @pbrbm obj b rfffrfndf for tif rfmotf objfdt (usublly b stub)
     * @fxdfption AlrfbdyBoundExdfption if nbmf is blrfbdy bound
     * @fxdfption MblformfdURLExdfption if tif nbmf is not bn bppropribtfly
     *  formbttfd URL
     * @fxdfption RfmotfExdfption if rfgistry dould not bf dontbdtfd
     * @fxdfption AddfssExdfption if tiis opfrbtion is not pfrmittfd (if
     * originbting from b non-lodbl iost, for fxbmplf)
     * @sindf 1.1
     */
    publid stbtid void bind(String nbmf, Rfmotf obj)
        tirows AlrfbdyBoundExdfption,
            jbvb.nft.MblformfdURLExdfption,
            RfmotfExdfption
    {
        PbrsfdNbmingURL pbrsfd = pbrsfURL(nbmf);
        Rfgistry rfgistry = gftRfgistry(pbrsfd);

        if (obj == null)
            tirow nfw NullPointfrExdfption("dbnnot bind to null");

        rfgistry.bind(pbrsfd.nbmf, obj);
    }

    /**
     * Dfstroys tif binding for tif spfdififd nbmf tibt is bssodibtfd
     * witi b rfmotf objfdt.
     *
     * @pbrbm nbmf b nbmf in URL formbt (witiout tif sdifmf domponfnt)
     * @fxdfption NotBoundExdfption if nbmf is not durrfntly bound
     * @fxdfption MblformfdURLExdfption if tif nbmf is not bn bppropribtfly
     *  formbttfd URL
     * @fxdfption RfmotfExdfption if rfgistry dould not bf dontbdtfd
     * @fxdfption AddfssExdfption if tiis opfrbtion is not pfrmittfd (if
     * originbting from b non-lodbl iost, for fxbmplf)
     * @sindf 1.1
     */
    publid stbtid void unbind(String nbmf)
        tirows RfmotfExdfption,
            NotBoundExdfption,
            jbvb.nft.MblformfdURLExdfption
    {
        PbrsfdNbmingURL pbrsfd = pbrsfURL(nbmf);
        Rfgistry rfgistry = gftRfgistry(pbrsfd);

        rfgistry.unbind(pbrsfd.nbmf);
    }

    /**
     * Rfbinds tif spfdififd nbmf to b nfw rfmotf objfdt. Any fxisting
     * binding for tif nbmf is rfplbdfd.
     *
     * @pbrbm nbmf b nbmf in URL formbt (witiout tif sdifmf domponfnt)
     * @pbrbm obj nfw rfmotf objfdt to bssodibtf witi tif nbmf
     * @fxdfption MblformfdURLExdfption if tif nbmf is not bn bppropribtfly
     *  formbttfd URL
     * @fxdfption RfmotfExdfption if rfgistry dould not bf dontbdtfd
     * @fxdfption AddfssExdfption if tiis opfrbtion is not pfrmittfd (if
     * originbting from b non-lodbl iost, for fxbmplf)
     * @sindf 1.1
     */
    publid stbtid void rfbind(String nbmf, Rfmotf obj)
        tirows RfmotfExdfption, jbvb.nft.MblformfdURLExdfption
    {
        PbrsfdNbmingURL pbrsfd = pbrsfURL(nbmf);
        Rfgistry rfgistry = gftRfgistry(pbrsfd);

        if (obj == null)
            tirow nfw NullPointfrExdfption("dbnnot bind to null");

        rfgistry.rfbind(pbrsfd.nbmf, obj);
    }

    /**
     * Rfturns bn brrby of tif nbmfs bound in tif rfgistry.  Tif nbmfs brf
     * URL-formbttfd (witiout tif sdifmf domponfnt) strings. Tif brrby dontbins
     * b snbpsiot of tif nbmfs prfsfnt in tif rfgistry bt tif timf of tif
     * dbll.
     *
     * @pbrbm   nbmf b rfgistry nbmf in URL formbt (witiout tif sdifmf
     *          domponfnt)
     * @rfturn  bn brrby of nbmfs (in tif bppropribtf formbt) bound
     *          in tif rfgistry
     * @fxdfption MblformfdURLExdfption if tif nbmf is not bn bppropribtfly
     *  formbttfd URL
     * @fxdfption RfmotfExdfption if rfgistry dould not bf dontbdtfd.
     * @sindf 1.1
     */
    publid stbtid String[] list(String nbmf)
        tirows RfmotfExdfption, jbvb.nft.MblformfdURLExdfption
    {
        PbrsfdNbmingURL pbrsfd = pbrsfURL(nbmf);
        Rfgistry rfgistry = gftRfgistry(pbrsfd);

        String prffix = "";
        if (pbrsfd.port > 0 || !pbrsfd.iost.fqubls(""))
            prffix += "//" + pbrsfd.iost;
        if (pbrsfd.port > 0)
            prffix += ":" + pbrsfd.port;
        prffix += "/";

        String[] nbmfs = rfgistry.list();
        for (int i = 0; i < nbmfs.lfngti; i++) {
            nbmfs[i] = prffix + nbmfs[i];
        }
        rfturn nbmfs;
    }

    /**
     * Rfturns b rfgistry rfffrfndf obtbinfd from informbtion in tif URL.
     */
    privbtf stbtid Rfgistry gftRfgistry(PbrsfdNbmingURL pbrsfd)
        tirows RfmotfExdfption
    {
        rfturn LodbtfRfgistry.gftRfgistry(pbrsfd.iost, pbrsfd.port);
    }

    /**
     * Dissfdt Nbming URL strings to obtbin rfffrfndfd iost, port bnd
     * objfdt nbmf.
     *
     * @rfturn bn objfdt wiidi dontbins fbdi of tif bbovf
     * domponfnts.
     *
     * @fxdfption MblformfdURLExdfption if givfn url string is mblformfd
     */
    privbtf stbtid PbrsfdNbmingURL pbrsfURL(String str)
        tirows MblformfdURLExdfption
    {
        try {
            rfturn intPbrsfURL(str);
        } dbtdi (URISyntbxExdfption fx) {
            /* Witi RFC 3986 URI ibndling, 'rmi://:<port>' bnd
             * '//:<port>' forms will rfsult in b URI syntbx fxdfption
             * Convfrt tif butiority to b lodbliost:<port> form
             */
            MblformfdURLExdfption muf = nfw MblformfdURLExdfption(
                "invblid URL String: " + str);
            muf.initCbusf(fx);
            int indfxSdifmfEnd = str.indfxOf(':');
            int indfxAutiorityBfgin = str.indfxOf("//:");
            if (indfxAutiorityBfgin < 0) {
                tirow muf;
            }
            if ((indfxAutiorityBfgin == 0) ||
                    ((indfxSdifmfEnd > 0) &&
                    (indfxAutiorityBfgin == indfxSdifmfEnd + 1))) {
                int indfxHostBfgin = indfxAutiorityBfgin + 2;
                String nfwStr = str.substring(0, indfxHostBfgin) +
                                "lodbliost" +
                                str.substring(indfxHostBfgin);
                try {
                    rfturn intPbrsfURL(nfwStr);
                } dbtdi (URISyntbxExdfption intf) {
                    tirow muf;
                } dbtdi (MblformfdURLExdfption intf) {
                    tirow intf;
                }
            }
            tirow muf;
        }
    }

    privbtf stbtid PbrsfdNbmingURL intPbrsfURL(String str)
        tirows MblformfdURLExdfption, URISyntbxExdfption
    {
        URI uri = nfw URI(str);
        if (uri.isOpbquf()) {
            tirow nfw MblformfdURLExdfption(
                "not b iifrbrdiidbl URL: " + str);
        }
        if (uri.gftFrbgmfnt() != null) {
            tirow nfw MblformfdURLExdfption(
                "invblid dibrbdtfr, '#', in URL nbmf: " + str);
        } flsf if (uri.gftQufry() != null) {
            tirow nfw MblformfdURLExdfption(
                "invblid dibrbdtfr, '?', in URL nbmf: " + str);
        } flsf if (uri.gftUsfrInfo() != null) {
            tirow nfw MblformfdURLExdfption(
                "invblid dibrbdtfr, '@', in URL iost: " + str);
        }
        String sdifmf = uri.gftSdifmf();
        if (sdifmf != null && !sdifmf.fqubls("rmi")) {
            tirow nfw MblformfdURLExdfption("invblid URL sdifmf: " + str);
        }

        String nbmf = uri.gftPbti();
        if (nbmf != null) {
            if (nbmf.stbrtsWiti("/")) {
                nbmf = nbmf.substring(1);
            }
            if (nbmf.lfngti() == 0) {
                nbmf = null;
            }
        }

        String iost = uri.gftHost();
        if (iost == null) {
            iost = "";
            try {
                /*
                 * Witi 2396 URI ibndling, forms sudi bs 'rmi://iost:bbr'
                 * or 'rmi://:<port>' brf pbrsfd into b rfgistry bbsfd
                 * butiority. Wf only wbnt to bllow sfrvfr bbsfd nbming
                 * butioritifs.
                 */
                uri.pbrsfSfrvfrAutiority();
            } dbtdi (URISyntbxExdfption usf) {
                // Cifdk if tif butiority is of form ':<port>'
                String butiority = uri.gftAutiority();
                if (butiority != null && butiority.stbrtsWiti(":")) {
                    // Convfrt tif butiority to 'lodbliost:<port>' form
                    butiority = "lodbliost" + butiority;
                    try {
                        uri = nfw URI(null, butiority, null, null, null);
                        // Mbkf surf it now pbrsfs to b vblid sfrvfr bbsfd
                        // nbming butiority
                        uri.pbrsfSfrvfrAutiority();
                    } dbtdi (URISyntbxExdfption usf2) {
                        tirow nfw
                            MblformfdURLExdfption("invblid butiority: " + str);
                    }
                } flsf {
                    tirow nfw
                        MblformfdURLExdfption("invblid butiority: " + str);
                }
            }
        }
        int port = uri.gftPort();
        if (port == -1) {
            port = Rfgistry.REGISTRY_PORT;
        }
        rfturn nfw PbrsfdNbmingURL(iost, port, nbmf);
    }

    /**
     * Simplf dlbss to fnbblf multiplf URL rfturn vblufs.
     */
    privbtf stbtid dlbss PbrsfdNbmingURL {
        String iost;
        int port;
        String nbmf;

        PbrsfdNbmingURL(String iost, int port, String nbmf) {
            tiis.iost = iost;
            tiis.port = port;
            tiis.nbmf = nbmf;
        }
    }
}
