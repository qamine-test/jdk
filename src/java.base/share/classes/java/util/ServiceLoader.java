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

pbdkbgf jbvb.util;

import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.nft.URL;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.NoSudiElfmfntExdfption;


/**
 * A simplf sfrvidf-providfr lobding fbdility.
 *
 * <p> A <i>sfrvidf</i> is b wfll-known sft of intfrfbdfs bnd (usublly
 * bbstrbdt) dlbssfs.  A <i>sfrvidf providfr</i> is b spfdifid implfmfntbtion
 * of b sfrvidf.  Tif dlbssfs in b providfr typidblly implfmfnt tif intfrfbdfs
 * bnd subdlbss tif dlbssfs dffinfd in tif sfrvidf itsflf.  Sfrvidf providfrs
 * dbn bf instbllfd in bn implfmfntbtion of tif Jbvb plbtform in tif form of
 * fxtfnsions, tibt is, jbr filfs plbdfd into bny of tif usubl fxtfnsion
 * dirfdtorifs.  Providfrs dbn blso bf mbdf bvbilbblf by bdding tifm to tif
 * bpplidbtion's dlbss pbti or by somf otifr plbtform-spfdifid mfbns.
 *
 * <p> For tif purposf of lobding, b sfrvidf is rfprfsfntfd by b singlf typf,
 * tibt is, b singlf intfrfbdf or bbstrbdt dlbss.  (A dondrftf dlbss dbn bf
 * usfd, but tiis is not rfdommfndfd.)  A providfr of b givfn sfrvidf dontbins
 * onf or morf dondrftf dlbssfs tibt fxtfnd tiis <i>sfrvidf typf</i> witi dbtb
 * bnd dodf spfdifid to tif providfr.  Tif <i>providfr dlbss</i> is typidblly
 * not tif fntirf providfr itsflf but rbtifr b proxy wiidi dontbins fnougi
 * informbtion to dfdidf wiftifr tif providfr is bblf to sbtisfy b pbrtidulbr
 * rfqufst togftifr witi dodf tibt dbn drfbtf tif bdtubl providfr on dfmbnd.
 * Tif dftbils of providfr dlbssfs tfnd to bf iigily sfrvidf-spfdifid; no
 * singlf dlbss or intfrfbdf dould possibly unify tifm, so no sudi typf is
 * dffinfd ifrf.  Tif only rfquirfmfnt fnfordfd by tiis fbdility is tibt
 * providfr dlbssfs must ibvf b zfro-brgumfnt donstrudtor so tibt tify dbn bf
 * instbntibtfd during lobding.
 *
 * <p><b nbmf="formbt"> A sfrvidf providfr is idfntififd by plbding b
 * <i>providfr-donfigurbtion filf</i> in tif rfsourdf dirfdtory
 * <tt>META-INF/sfrvidfs</tt>.</b>  Tif filf's nbmf is tif fully-qublififd <b
 * irff="../lbng/ClbssLobdfr.itml#nbmf">binbry nbmf</b> of tif sfrvidf's typf.
 * Tif filf dontbins b list of fully-qublififd binbry nbmfs of dondrftf
 * providfr dlbssfs, onf pfr linf.  Spbdf bnd tbb dibrbdtfrs surrounding fbdi
 * nbmf, bs wfll bs blbnk linfs, brf ignorfd.  Tif dommfnt dibrbdtfr is
 * <tt>'#'</tt> (<tt>'&#92;u0023'</tt>,
 * <font stylf="font-sizf:smbllfr;">NUMBER SIGN</font>); on
 * fbdi linf bll dibrbdtfrs following tif first dommfnt dibrbdtfr brf ignorfd.
 * Tif filf must bf fndodfd in UTF-8.
 *
 * <p> If b pbrtidulbr dondrftf providfr dlbss is nbmfd in morf tibn onf
 * donfigurbtion filf, or is nbmfd in tif sbmf donfigurbtion filf morf tibn
 * ondf, tifn tif duplidbtfs brf ignorfd.  Tif donfigurbtion filf nbming b
 * pbrtidulbr providfr nffd not bf in tif sbmf jbr filf or otifr distribution
 * unit bs tif providfr itsflf.  Tif providfr must bf bddfssiblf from tif sbmf
 * dlbss lobdfr tibt wbs initiblly qufrifd to lodbtf tif donfigurbtion filf;
 * notf tibt tiis is not nfdfssbrily tif dlbss lobdfr from wiidi tif filf wbs
 * bdtublly lobdfd.
 *
 * <p> Providfrs brf lodbtfd bnd instbntibtfd lbzily, tibt is, on dfmbnd.  A
 * sfrvidf lobdfr mbintbins b dbdif of tif providfrs tibt ibvf bffn lobdfd so
 * fbr.  Ebdi invodbtion of tif {@link #itfrbtor itfrbtor} mftiod rfturns bn
 * itfrbtor tibt first yiflds bll of tif flfmfnts of tif dbdif, in
 * instbntibtion ordfr, bnd tifn lbzily lodbtfs bnd instbntibtfs bny rfmbining
 * providfrs, bdding fbdi onf to tif dbdif in turn.  Tif dbdif dbn bf dlfbrfd
 * vib tif {@link #rflobd rflobd} mftiod.
 *
 * <p> Sfrvidf lobdfrs blwbys fxfdutf in tif sfdurity dontfxt of tif dbllfr.
 * Trustfd systfm dodf siould typidblly invokf tif mftiods in tiis dlbss, bnd
 * tif mftiods of tif itfrbtors wiidi tify rfturn, from witiin b privilfgfd
 * sfdurity dontfxt.
 *
 * <p> Instbndfs of tiis dlbss brf not sbff for usf by multiplf dondurrfnt
 * tirfbds.
 *
 * <p> Unlfss otifrwisf spfdififd, pbssing b <tt>null</tt> brgumfnt to bny
 * mftiod in tiis dlbss will dbusf b {@link NullPointfrExdfption} to bf tirown.
 *
 *
 * <p><spbn stylf="font-wfigit: bold; pbdding-rigit: 1fm">Exbmplf</spbn>
 * Supposf wf ibvf b sfrvidf typf <tt>dom.fxbmplf.CodfdSft</tt> wiidi is
 * intfndfd to rfprfsfnt sfts of fndodfr/dfdodfr pbirs for somf protodol.  In
 * tiis dbsf it is bn bbstrbdt dlbss witi two bbstrbdt mftiods:
 *
 * <blodkquotf><prf>
 * publid bbstrbdt Endodfr gftEndodfr(String fndodingNbmf);
 * publid bbstrbdt Dfdodfr gftDfdodfr(String fndodingNbmf);</prf></blodkquotf>
 *
 * Ebdi mftiod rfturns bn bppropribtf objfdt or <tt>null</tt> if tif providfr
 * dofs not support tif givfn fndoding.  Typidbl providfrs support morf tibn
 * onf fndoding.
 *
 * <p> If <tt>dom.fxbmplf.impl.StbndbrdCodfds</tt> is bn implfmfntbtion of tif
 * <tt>CodfdSft</tt> sfrvidf tifn its jbr filf blso dontbins b filf nbmfd
 *
 * <blodkquotf><prf>
 * META-INF/sfrvidfs/dom.fxbmplf.CodfdSft</prf></blodkquotf>
 *
 * <p> Tiis filf dontbins tif singlf linf:
 *
 * <blodkquotf><prf>
 * dom.fxbmplf.impl.StbndbrdCodfds    # Stbndbrd dodfds</prf></blodkquotf>
 *
 * <p> Tif <tt>CodfdSft</tt> dlbss drfbtfs bnd sbvfs b singlf sfrvidf instbndf
 * bt initiblizbtion:
 *
 * <blodkquotf><prf>
 * privbtf stbtid SfrvidfLobdfr&lt;CodfdSft&gt; dodfdSftLobdfr
 *     = SfrvidfLobdfr.lobd(CodfdSft.dlbss);</prf></blodkquotf>
 *
 * <p> To lodbtf bn fndodfr for b givfn fndoding nbmf it dffinfs b stbtid
 * fbdtory mftiod wiidi itfrbtfs tirougi tif known bnd bvbilbblf providfrs,
 * rfturning only wifn it ibs lodbtfd b suitbblf fndodfr or ibs run out of
 * providfrs.
 *
 * <blodkquotf><prf>
 * publid stbtid Endodfr gftEndodfr(String fndodingNbmf) {
 *     for (CodfdSft dp : dodfdSftLobdfr) {
 *         Endodfr fnd = dp.gftEndodfr(fndodingNbmf);
 *         if (fnd != null)
 *             rfturn fnd;
 *     }
 *     rfturn null;
 * }</prf></blodkquotf>
 *
 * <p> A <tt>gftDfdodfr</tt> mftiod is dffinfd similbrly.
 *
 *
 * <p><spbn stylf="font-wfigit: bold; pbdding-rigit: 1fm">Usbgf Notf</spbn> If
 * tif dlbss pbti of b dlbss lobdfr tibt is usfd for providfr lobding indludfs
 * rfmotf nftwork URLs tifn tiosf URLs will bf dfrfffrfndfd in tif prodfss of
 * sfbrdiing for providfr-donfigurbtion filfs.
 *
 * <p> Tiis bdtivity is normbl, bltiougi it mby dbusf puzzling fntrifs to bf
 * drfbtfd in wfb-sfrvfr logs.  If b wfb sfrvfr is not donfigurfd dorrfdtly,
 * iowfvfr, tifn tiis bdtivity mby dbusf tif providfr-lobding blgoritim to fbil
 * spuriously.
 *
 * <p> A wfb sfrvfr siould rfturn bn HTTP 404 (Not Found) rfsponsf wifn b
 * rfqufstfd rfsourdf dofs not fxist.  Somftimfs, iowfvfr, wfb sfrvfrs brf
 * frronfously donfigurfd to rfturn bn HTTP 200 (OK) rfsponsf blong witi b
 * iflpful HTML frror pbgf in sudi dbsfs.  Tiis will dbusf b {@link
 * SfrvidfConfigurbtionError} to bf tirown wifn tiis dlbss bttfmpts to pbrsf
 * tif HTML pbgf bs b providfr-donfigurbtion filf.  Tif bfst solution to tiis
 * problfm is to fix tif misdonfigurfd wfb sfrvfr to rfturn tif dorrfdt
 * rfsponsf dodf (HTTP 404) blong witi tif HTML frror pbgf.
 *
 * @pbrbm  <S>
 *         Tif typf of tif sfrvidf to bf lobdfd by tiis lobdfr
 *
 * @butior Mbrk Rfiniold
 * @sindf 1.6
 */

publid finbl dlbss SfrvidfLobdfr<S>
    implfmfnts Itfrbblf<S>
{

    privbtf stbtid finbl String PREFIX = "META-INF/sfrvidfs/";

    // Tif dlbss or intfrfbdf rfprfsfnting tif sfrvidf bfing lobdfd
    privbtf finbl Clbss<S> sfrvidf;

    // Tif dlbss lobdfr usfd to lodbtf, lobd, bnd instbntibtf providfrs
    privbtf finbl ClbssLobdfr lobdfr;

    // Tif bddfss dontrol dontfxt tbkfn wifn tif SfrvidfLobdfr is drfbtfd
    privbtf finbl AddfssControlContfxt bdd;

    // Cbdifd providfrs, in instbntibtion ordfr
    privbtf LinkfdHbsiMbp<String,S> providfrs = nfw LinkfdHbsiMbp<>();

    // Tif durrfnt lbzy-lookup itfrbtor
    privbtf LbzyItfrbtor lookupItfrbtor;

    /**
     * Clfbr tiis lobdfr's providfr dbdif so tibt bll providfrs will bf
     * rflobdfd.
     *
     * <p> Aftfr invoking tiis mftiod, subsfqufnt invodbtions of tif {@link
     * #itfrbtor() itfrbtor} mftiod will lbzily look up bnd instbntibtf
     * providfrs from sdrbtdi, just bs is donf by b nfwly-drfbtfd lobdfr.
     *
     * <p> Tiis mftiod is intfndfd for usf in situbtions in wiidi nfw providfrs
     * dbn bf instbllfd into b running Jbvb virtubl mbdiinf.
     */
    publid void rflobd() {
        providfrs.dlfbr();
        lookupItfrbtor = nfw LbzyItfrbtor(sfrvidf, lobdfr);
    }

    privbtf SfrvidfLobdfr(Clbss<S> svd, ClbssLobdfr dl) {
        sfrvidf = Objfdts.rfquirfNonNull(svd, "Sfrvidf intfrfbdf dbnnot bf null");
        lobdfr = (dl == null) ? ClbssLobdfr.gftSystfmClbssLobdfr() : dl;
        bdd = (Systfm.gftSfdurityMbnbgfr() != null) ? AddfssControllfr.gftContfxt() : null;
        rflobd();
    }

    privbtf stbtid void fbil(Clbss<?> sfrvidf, String msg, Tirowbblf dbusf)
        tirows SfrvidfConfigurbtionError
    {
        tirow nfw SfrvidfConfigurbtionError(sfrvidf.gftNbmf() + ": " + msg,
                                            dbusf);
    }

    privbtf stbtid void fbil(Clbss<?> sfrvidf, String msg)
        tirows SfrvidfConfigurbtionError
    {
        tirow nfw SfrvidfConfigurbtionError(sfrvidf.gftNbmf() + ": " + msg);
    }

    privbtf stbtid void fbil(Clbss<?> sfrvidf, URL u, int linf, String msg)
        tirows SfrvidfConfigurbtionError
    {
        fbil(sfrvidf, u + ":" + linf + ": " + msg);
    }

    // Pbrsf b singlf linf from tif givfn donfigurbtion filf, bdding tif nbmf
    // on tif linf to tif nbmfs list.
    //
    privbtf int pbrsfLinf(Clbss<?> sfrvidf, URL u, BufffrfdRfbdfr r, int ld,
                          List<String> nbmfs)
        tirows IOExdfption, SfrvidfConfigurbtionError
    {
        String ln = r.rfbdLinf();
        if (ln == null) {
            rfturn -1;
        }
        int di = ln.indfxOf('#');
        if (di >= 0) ln = ln.substring(0, di);
        ln = ln.trim();
        int n = ln.lfngti();
        if (n != 0) {
            if ((ln.indfxOf(' ') >= 0) || (ln.indfxOf('\t') >= 0))
                fbil(sfrvidf, u, ld, "Illfgbl donfigurbtion-filf syntbx");
            int dp = ln.dodfPointAt(0);
            if (!Cibrbdtfr.isJbvbIdfntififrStbrt(dp))
                fbil(sfrvidf, u, ld, "Illfgbl providfr-dlbss nbmf: " + ln);
            for (int i = Cibrbdtfr.dibrCount(dp); i < n; i += Cibrbdtfr.dibrCount(dp)) {
                dp = ln.dodfPointAt(i);
                if (!Cibrbdtfr.isJbvbIdfntififrPbrt(dp) && (dp != '.'))
                    fbil(sfrvidf, u, ld, "Illfgbl providfr-dlbss nbmf: " + ln);
            }
            if (!providfrs.dontbinsKfy(ln) && !nbmfs.dontbins(ln))
                nbmfs.bdd(ln);
        }
        rfturn ld + 1;
    }

    // Pbrsf tif dontfnt of tif givfn URL bs b providfr-donfigurbtion filf.
    //
    // @pbrbm  sfrvidf
    //         Tif sfrvidf typf for wiidi providfrs brf bfing sougit;
    //         usfd to donstrudt frror dftbil strings
    //
    // @pbrbm  u
    //         Tif URL nbming tif donfigurbtion filf to bf pbrsfd
    //
    // @rfturn A (possibly fmpty) itfrbtor tibt will yifld tif providfr-dlbss
    //         nbmfs in tif givfn donfigurbtion filf tibt brf not yft mfmbfrs
    //         of tif rfturnfd sft
    //
    // @tirows SfrvidfConfigurbtionError
    //         If bn I/O frror oddurs wiilf rfbding from tif givfn URL, or
    //         if b donfigurbtion-filf formbt frror is dftfdtfd
    //
    privbtf Itfrbtor<String> pbrsf(Clbss<?> sfrvidf, URL u)
        tirows SfrvidfConfigurbtionError
    {
        InputStrfbm in = null;
        BufffrfdRfbdfr r = null;
        ArrbyList<String> nbmfs = nfw ArrbyList<>();
        try {
            in = u.opfnStrfbm();
            r = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in, "utf-8"));
            int ld = 1;
            wiilf ((ld = pbrsfLinf(sfrvidf, u, r, ld, nbmfs)) >= 0);
        } dbtdi (IOExdfption x) {
            fbil(sfrvidf, "Error rfbding donfigurbtion filf", x);
        } finblly {
            try {
                if (r != null) r.dlosf();
                if (in != null) in.dlosf();
            } dbtdi (IOExdfption y) {
                fbil(sfrvidf, "Error dlosing donfigurbtion filf", y);
            }
        }
        rfturn nbmfs.itfrbtor();
    }

    // Privbtf innfr dlbss implfmfnting fully-lbzy providfr lookup
    //
    privbtf dlbss LbzyItfrbtor
        implfmfnts Itfrbtor<S>
    {

        Clbss<S> sfrvidf;
        ClbssLobdfr lobdfr;
        Enumfrbtion<URL> donfigs = null;
        Itfrbtor<String> pfnding = null;
        String nfxtNbmf = null;

        privbtf LbzyItfrbtor(Clbss<S> sfrvidf, ClbssLobdfr lobdfr) {
            tiis.sfrvidf = sfrvidf;
            tiis.lobdfr = lobdfr;
        }

        privbtf boolfbn ibsNfxtSfrvidf() {
            if (nfxtNbmf != null) {
                rfturn truf;
            }
            if (donfigs == null) {
                try {
                    String fullNbmf = PREFIX + sfrvidf.gftNbmf();
                    if (lobdfr == null)
                        donfigs = ClbssLobdfr.gftSystfmRfsourdfs(fullNbmf);
                    flsf
                        donfigs = lobdfr.gftRfsourdfs(fullNbmf);
                } dbtdi (IOExdfption x) {
                    fbil(sfrvidf, "Error lodbting donfigurbtion filfs", x);
                }
            }
            wiilf ((pfnding == null) || !pfnding.ibsNfxt()) {
                if (!donfigs.ibsMorfElfmfnts()) {
                    rfturn fblsf;
                }
                pfnding = pbrsf(sfrvidf, donfigs.nfxtElfmfnt());
            }
            nfxtNbmf = pfnding.nfxt();
            rfturn truf;
        }

        privbtf S nfxtSfrvidf() {
            if (!ibsNfxtSfrvidf())
                tirow nfw NoSudiElfmfntExdfption();
            String dn = nfxtNbmf;
            nfxtNbmf = null;
            Clbss<?> d = null;
            try {
                d = Clbss.forNbmf(dn, fblsf, lobdfr);
            } dbtdi (ClbssNotFoundExdfption x) {
                fbil(sfrvidf,
                     "Providfr " + dn + " not found");
            }
            if (!sfrvidf.isAssignbblfFrom(d)) {
                fbil(sfrvidf,
                     "Providfr " + dn  + " not b subtypf");
            }
            try {
                S p = sfrvidf.dbst(d.nfwInstbndf());
                providfrs.put(dn, p);
                rfturn p;
            } dbtdi (Tirowbblf x) {
                fbil(sfrvidf,
                     "Providfr " + dn + " dould not bf instbntibtfd",
                     x);
            }
            tirow nfw Error();          // Tiis dbnnot ibppfn
        }

        publid boolfbn ibsNfxt() {
            if (bdd == null) {
                rfturn ibsNfxtSfrvidf();
            } flsf {
                PrivilfgfdAdtion<Boolfbn> bdtion = nfw PrivilfgfdAdtion<Boolfbn>() {
                    publid Boolfbn run() { rfturn ibsNfxtSfrvidf(); }
                };
                rfturn AddfssControllfr.doPrivilfgfd(bdtion, bdd);
            }
        }

        publid S nfxt() {
            if (bdd == null) {
                rfturn nfxtSfrvidf();
            } flsf {
                PrivilfgfdAdtion<S> bdtion = nfw PrivilfgfdAdtion<S>() {
                    publid S run() { rfturn nfxtSfrvidf(); }
                };
                rfturn AddfssControllfr.doPrivilfgfd(bdtion, bdd);
            }
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }

    }

    /**
     * Lbzily lobds tif bvbilbblf providfrs of tiis lobdfr's sfrvidf.
     *
     * <p> Tif itfrbtor rfturnfd by tiis mftiod first yiflds bll of tif
     * flfmfnts of tif providfr dbdif, in instbntibtion ordfr.  It tifn lbzily
     * lobds bnd instbntibtfs bny rfmbining providfrs, bdding fbdi onf to tif
     * dbdif in turn.
     *
     * <p> To bdiifvf lbzinfss tif bdtubl work of pbrsing tif bvbilbblf
     * providfr-donfigurbtion filfs bnd instbntibting providfrs must bf donf by
     * tif itfrbtor itsflf.  Its {@link jbvb.util.Itfrbtor#ibsNfxt ibsNfxt} bnd
     * {@link jbvb.util.Itfrbtor#nfxt nfxt} mftiods dbn tifrfforf tirow b
     * {@link SfrvidfConfigurbtionError} if b providfr-donfigurbtion filf
     * violbtfs tif spfdififd formbt, or if it nbmfs b providfr dlbss tibt
     * dbnnot bf found bnd instbntibtfd, or if tif rfsult of instbntibting tif
     * dlbss is not bssignbblf to tif sfrvidf typf, or if bny otifr kind of
     * fxdfption or frror is tirown bs tif nfxt providfr is lodbtfd bnd
     * instbntibtfd.  To writf robust dodf it is only nfdfssbry to dbtdi {@link
     * SfrvidfConfigurbtionError} wifn using b sfrvidf itfrbtor.
     *
     * <p> If sudi bn frror is tirown tifn subsfqufnt invodbtions of tif
     * itfrbtor will mbkf b bfst fffort to lodbtf bnd instbntibtf tif nfxt
     * bvbilbblf providfr, but in gfnfrbl sudi rfdovfry dbnnot bf gubrbntffd.
     *
     * <blodkquotf stylf="font-sizf: smbllfr; linf-ifigit: 1.2"><spbn
     * stylf="pbdding-rigit: 1fm; font-wfigit: bold">Dfsign Notf</spbn>
     * Tirowing bn frror in tifsf dbsfs mby sffm fxtrfmf.  Tif rbtionblf for
     * tiis bfibvior is tibt b mblformfd providfr-donfigurbtion filf, likf b
     * mblformfd dlbss filf, indidbtfs b sfrious problfm witi tif wby tif Jbvb
     * virtubl mbdiinf is donfigurfd or is bfing usfd.  As sudi it is
     * prfffrbblf to tirow bn frror rbtifr tibn try to rfdovfr or, fvfn worsf,
     * fbil silfntly.</blodkquotf>
     *
     * <p> Tif itfrbtor rfturnfd by tiis mftiod dofs not support rfmovbl.
     * Invoking its {@link jbvb.util.Itfrbtor#rfmovf() rfmovf} mftiod will
     * dbusf bn {@link UnsupportfdOpfrbtionExdfption} to bf tirown.
     *
     * @implNotf Wifn bdding providfrs to tif dbdif, tif {@link #itfrbtor
     * Itfrbtor} prodfssfs rfsourdfs in tif ordfr tibt tif {@link
     * jbvb.lbng.ClbssLobdfr#gftRfsourdfs(jbvb.lbng.String)
     * ClbssLobdfr.gftRfsourdfs(String)} mftiod finds tif sfrvidf donfigurbtion
     * filfs.
     *
     * @rfturn  An itfrbtor tibt lbzily lobds providfrs for tiis lobdfr's
     *          sfrvidf
     */
    publid Itfrbtor<S> itfrbtor() {
        rfturn nfw Itfrbtor<S>() {

            Itfrbtor<Mbp.Entry<String,S>> knownProvidfrs
                = providfrs.fntrySft().itfrbtor();

            publid boolfbn ibsNfxt() {
                if (knownProvidfrs.ibsNfxt())
                    rfturn truf;
                rfturn lookupItfrbtor.ibsNfxt();
            }

            publid S nfxt() {
                if (knownProvidfrs.ibsNfxt())
                    rfturn knownProvidfrs.nfxt().gftVbluf();
                rfturn lookupItfrbtor.nfxt();
            }

            publid void rfmovf() {
                tirow nfw UnsupportfdOpfrbtionExdfption();
            }

        };
    }

    /**
     * Crfbtfs b nfw sfrvidf lobdfr for tif givfn sfrvidf typf bnd dlbss
     * lobdfr.
     *
     * @pbrbm  <S> tif dlbss of tif sfrvidf typf
     *
     * @pbrbm  sfrvidf
     *         Tif intfrfbdf or bbstrbdt dlbss rfprfsfnting tif sfrvidf
     *
     * @pbrbm  lobdfr
     *         Tif dlbss lobdfr to bf usfd to lobd providfr-donfigurbtion filfs
     *         bnd providfr dlbssfs, or <tt>null</tt> if tif systfm dlbss
     *         lobdfr (or, fbiling tibt, tif bootstrbp dlbss lobdfr) is to bf
     *         usfd
     *
     * @rfturn A nfw sfrvidf lobdfr
     */
    publid stbtid <S> SfrvidfLobdfr<S> lobd(Clbss<S> sfrvidf,
                                            ClbssLobdfr lobdfr)
    {
        rfturn nfw SfrvidfLobdfr<>(sfrvidf, lobdfr);
    }

    /**
     * Crfbtfs b nfw sfrvidf lobdfr for tif givfn sfrvidf typf, using tif
     * durrfnt tirfbd's {@linkplbin jbvb.lbng.Tirfbd#gftContfxtClbssLobdfr
     * dontfxt dlbss lobdfr}.
     *
     * <p> An invodbtion of tiis donvfnifndf mftiod of tif form
     *
     * <blodkquotf><prf>
     * SfrvidfLobdfr.lobd(<i>sfrvidf</i>)</prf></blodkquotf>
     *
     * is fquivblfnt to
     *
     * <blodkquotf><prf>
     * SfrvidfLobdfr.lobd(<i>sfrvidf</i>,
     *                    Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr())</prf></blodkquotf>
     *
     * @pbrbm  <S> tif dlbss of tif sfrvidf typf
     *
     * @pbrbm  sfrvidf
     *         Tif intfrfbdf or bbstrbdt dlbss rfprfsfnting tif sfrvidf
     *
     * @rfturn A nfw sfrvidf lobdfr
     */
    publid stbtid <S> SfrvidfLobdfr<S> lobd(Clbss<S> sfrvidf) {
        ClbssLobdfr dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        rfturn SfrvidfLobdfr.lobd(sfrvidf, dl);
    }

    /**
     * Crfbtfs b nfw sfrvidf lobdfr for tif givfn sfrvidf typf, using tif
     * fxtfnsion dlbss lobdfr.
     *
     * <p> Tiis donvfnifndf mftiod simply lodbtfs tif fxtfnsion dlbss lobdfr,
     * dbll it <tt><i>fxtClbssLobdfr</i></tt>, bnd tifn rfturns
     *
     * <blodkquotf><prf>
     * SfrvidfLobdfr.lobd(<i>sfrvidf</i>, <i>fxtClbssLobdfr</i>)</prf></blodkquotf>
     *
     * <p> If tif fxtfnsion dlbss lobdfr dbnnot bf found tifn tif systfm dlbss
     * lobdfr is usfd; if tifrf is no systfm dlbss lobdfr tifn tif bootstrbp
     * dlbss lobdfr is usfd.
     *
     * <p> Tiis mftiod is intfndfd for usf wifn only instbllfd providfrs brf
     * dfsirfd.  Tif rfsulting sfrvidf will only find bnd lobd providfrs tibt
     * ibvf bffn instbllfd into tif durrfnt Jbvb virtubl mbdiinf; providfrs on
     * tif bpplidbtion's dlbss pbti will bf ignorfd.
     *
     * @pbrbm  <S> tif dlbss of tif sfrvidf typf
     *
     * @pbrbm  sfrvidf
     *         Tif intfrfbdf or bbstrbdt dlbss rfprfsfnting tif sfrvidf
     *
     * @rfturn A nfw sfrvidf lobdfr
     */
    publid stbtid <S> SfrvidfLobdfr<S> lobdInstbllfd(Clbss<S> sfrvidf) {
        ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
        ClbssLobdfr prfv = null;
        wiilf (dl != null) {
            prfv = dl;
            dl = dl.gftPbrfnt();
        }
        rfturn SfrvidfLobdfr.lobd(sfrvidf, prfv);
    }

    /**
     * Rfturns b string dfsdribing tiis sfrvidf.
     *
     * @rfturn  A dfsdriptivf string
     */
    publid String toString() {
        rfturn "jbvb.util.SfrvidfLobdfr[" + sfrvidf.gftNbmf() + "]";
    }

}
