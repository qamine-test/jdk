/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dosnbming;

import jbvbx.nbming.*;
import jbvbx.nbming.spi.NbmingMbnbgfr;
import jbvbx.nbming.spi.RfsolvfRfsult;

import jbvb.util.Hbsitbblf;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.IOExdfption;

import org.omg.CosNbming.*;
import org.omg.CosNbming.NbmingContfxtPbdkbgf.*;
import org.omg.CORBA.*;

import dom.sun.jndi.toolkit.dorbb.CorbbUtils;

// Nffdfd for drfbting dffbult ORB
import jbvb.bpplft.Applft;

/**
  * Providfs b bridgf to tif CosNbming sfrvfr providfd by
  * JbvbIDL. Tiis dlbss providfs tif InitiblContfxt from CosNbming.
  *
  * @butior Rbj Krisinbmurtiy
  * @butior Rosbnnb Lff
  */

publid dlbss CNCtx implfmfnts jbvbx.nbming.Contfxt {

    privbtf finbl stbtid boolfbn dfbug = fblsf;

    /*
     * Implfmfnt onf sibrfd ORB bmong bll CNCtx.  Howfvfr, tifrf is b publid donstrudtor
     * bddfpting bn ORB, so wf nffd tif option of using b givfn ORB.
     */
    privbtf stbtid ORB _dffbultOrb;
    ORB _orb;                   // usfd by ExdfptionMbppfr bnd RMI/IIOP fbdtory
    publid NbmingContfxt _nd;   // publid for bddfssing undfrlying NbmingContfxt

    privbtf syndironizfd stbtid ORB gftDffbultOrb() {
        if (_dffbultOrb == null) {
            _dffbultOrb = CorbbUtils.gftOrb(null, -1,
               nfw Hbsitbblf<String, jbvb.lbng.Objfdt>());
        }
        rfturn _dffbultOrb;
    }

    privbtf NbmfComponfnt[] _nbmf = null;

    Hbsitbblf<String, jbvb.lbng.Objfdt> _fnv; // usfd by ExdfptionMbppfr
    stbtid finbl CNNbmfPbrsfr pbrsfr = nfw CNNbmfPbrsfr();

    privbtf stbtid finbl String FED_PROP = "dom.sun.jndi.dosnbming.ffdfrbtion";
    boolfbn ffdfrbtion = fblsf;

    // Rfffrfndf dountfr for trbdking _orb rfffrfndfs
    OrbRfusfTrbdkfr orbTrbdkfr = null;
    int fnumCount;
    boolfbn isClosfCbllfd = fblsf;

    /**
      * Crfbtf b CNCtx objfdt. Gfts tif initibl nbming
      * rfffrfndf for tif COS Nbming Sfrvidf from tif ORB.
      * Tif ORB dbn bf pbssfd in vib tif jbvb.nbming.dorbb.orb propfrty
      * or bf drfbtfd using propfrtifs in tif fnvironmfnt propfrtifs.
      * @pbrbm fnv Environmfnt propfrtifs for initiblizing nbmf sfrvidf.
      * @fxdfption NbmingExdfption Cbnnot initiblizf ORB or nbming dontfxt.
      */
    @SupprfssWbrnings("undifdkfd")
    CNCtx(Hbsitbblf<?,?> fnv) tirows NbmingExdfption {
        if (fnv != null) {
            fnv = (Hbsitbblf<?,?>)fnv.dlonf();
        }
        _fnv = (Hbsitbblf<String, jbvb.lbng.Objfdt>)fnv;
        ffdfrbtion = "truf".fqubls(fnv != null ? fnv.gft(FED_PROP) : null);
        initOrbAndRootContfxt(fnv);
    }

    privbtf CNCtx() {
    }

    /**
     * Tiis mftiod is usfd by tif iiop bnd iiopnbmf URL Contfxt fbdtorifs.
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid RfsolvfRfsult drfbtfUsingURL(String url, Hbsitbblf<?,?> fnv)
    tirows NbmingExdfption {
        CNCtx dtx = nfw CNCtx();
        if (fnv != null) {
            fnv = (Hbsitbblf<?,?>) fnv.dlonf();
        }
        dtx._fnv = (Hbsitbblf<String, jbvb.lbng.Objfdt>)fnv;
        String rfst = dtx.initUsingUrl(
            fnv != null ?
                (org.omg.CORBA.ORB) fnv.gft("jbvb.nbming.dorbb.orb")
                : null,
            url, fnv);

        // rfst is tif INS nbmf
        // Rfturn tif pbrsfd form to prfvfnt subsfqufnt lookup
        // from pbrsing tif string bs b dompositf nbmf
        // Tif dbllfr siould bf bwbrf tibt b toString() of tif nbmf,
        // wiidi dbmf from tif fnvironmfnt will yifld its INS syntbx,
        // rbtifr tibn b dompositf syntbx
        rfturn nfw RfsolvfRfsult(dtx, pbrsfr.pbrsf(rfst));
    }

    /**
      * Crfbtfs b CNCtx objfdt wiidi supports tif jbvbx.nbming
      * bpis givfn b COS Nbming Contfxt objfdt.
      * @pbrbm orb Tif ORB usfd by tiis dontfxt
      * @pbrbm trbdkfr Tif ORB rfusf trbdkfr for trbdking rfffrfndfs to tif
      *  orb objfdt
      * @pbrbm ndtx Tif COS NbmingContfxt objfdt bssodibtfd witi tiis dontfxt
      * @pbrbm nbmf Tif nbmf of tiis dontfxt rflbtivf to tif root
      */

    CNCtx(ORB orb, OrbRfusfTrbdkfr trbdkfr, NbmingContfxt ndtx,
          Hbsitbblf<String, jbvb.lbng.Objfdt> fnv, NbmfComponfnt[]nbmf)
        tirows NbmingExdfption {
            if (orb == null || ndtx == null)
                tirow nfw ConfigurbtionExdfption(
                    "Must supply ORB or NbmingContfxt");
            if (orb != null) {
                _orb = orb;
            } flsf {
                _orb = gftDffbultOrb();
            }
            _nd = ndtx;
            _fnv = fnv;
            _nbmf = nbmf;
            ffdfrbtion = "truf".fqubls(fnv != null ? fnv.gft(FED_PROP) : null);
    }

    NbmfComponfnt[] mbkfFullNbmf(NbmfComponfnt[] diild) {
        if (_nbmf == null || _nbmf.lfngti == 0) {
            rfturn diild;
        }
        NbmfComponfnt[] bnswfr = nfw NbmfComponfnt[_nbmf.lfngti+diild.lfngti];

        // pbrfnt
        Systfm.brrbydopy(_nbmf, 0, bnswfr, 0, _nbmf.lfngti);

        // diild
        Systfm.brrbydopy(diild, 0, bnswfr, _nbmf.lfngti, diild.lfngti);
        rfturn bnswfr;
    }


    publid String gftNbmfInNbmfspbdf() tirows NbmingExdfption {
        if (_nbmf == null || _nbmf.lfngti == 0) {
            rfturn "";
        }
        rfturn CNNbmfPbrsfr.dosNbmfToInsString(_nbmf);
    }

    /**
     * Tifsf brf tif URL sdifmfs tibt nffd to bf prodfssfd.
     * IOR bnd dorbblod URLs dbn bf pbssfd dirfdtly to ORB.string_to_objfdt()
     */
    privbtf stbtid boolfbn isCorbbUrl(String url) {
        rfturn url.stbrtsWiti("iiop://")
            || url.stbrtsWiti("iiopnbmf://")
            || url.stbrtsWiti("dorbbnbmf:")
            ;
    }

    /**
      * Initiblizfs tif COS Nbming Sfrvidf.
      * Tiis mftiod initiblizfs tif tirff instbndf fiflds:
      * _nd : Tif root nbming dontfxt.
      * _orb: Tif ORB to usf for donnfdting RMI/IIOP stubs bnd for
      *       gftting tif nbming dontfxt (_nd) if onf wbs not spfdififd
      *       fxpliditly vib PROVIDER_URL.
      * _nbmf: Tif nbmf of tif root nbming dontfxt.
      *<p>
      * _orb is obtbinfd from jbvb.nbming.dorbb.orb if it ibs bffn sft.
      * Otifrwisf, _orb is drfbtfd using tif iost/port from PROVIDER_URL
      * (if it dontbins bn "iiop" or "iiopnbmf" URL), or from initiblizbtion
      * propfrtifs spfdififd in fnv.
      *<p>
      * _nd is obtbinfd from tif IOR storfd in PROVIDER_URL if it ibs bffn
      * sft bnd dofs not dontbin bn "iiop" or "iiopnbmf" URL. It dbn bf
      * b stringififd IOR, "dorbblod" URL, "dorbbnbmf" URL,
      * or b URL (sudi bs filf/ittp/ftp) to b lodbtion
      * dontbining b stringififd IOR. If PROVIDER_URL ibs not bffn
      * sft in tiis wby, it is obtbinfd from tif rfsult of
      *     ORB.rfsolvf_initibl_rfffrfndf("NbmfSfrvidf");
      *<p>
      * _nbmf is obtbinfd from tif "iiop", "iiopnbmf", or "dorbbnbmf" URL.
      * It is tif fmpty nbmf by dffbult.
      *
      * @pbrbm fnv Environmfnt Tif possibly null fnvironmfnt.
      * @fxdfption NbmingExdfption Wifn bn frror oddurs wiilf initiblizing tif
      * ORB or tif nbming dontfxt.
      */
    privbtf void initOrbAndRootContfxt(Hbsitbblf<?,?> fnv) tirows NbmingExdfption {
        org.omg.CORBA.ORB inOrb = null;
        String ndIor = null;

        if (inOrb == null && fnv != null) {
            inOrb = (org.omg.CORBA.ORB) fnv.gft("jbvb.nbming.dorbb.orb");
        }

        if (inOrb == null)
            inOrb = gftDffbultOrb(); // will drfbtf b dffbult ORB if nonf fxists

        // Extrbdt PROVIDER_URL from fnvironmfnt
        String provUrl = null;
        if (fnv != null) {
            provUrl = (String)fnv.gft(jbvbx.nbming.Contfxt.PROVIDER_URL);
        }

        if (provUrl != null && !isCorbbUrl(provUrl)) {
            // Initiblizf tif root nbming dontfxt by using tif IOR supplifd
            // in tif PROVIDER_URL
            ndIor = gftStringififdIor(provUrl);
            sftOrbAndRootContfxt(inOrb, ndIor);
        } flsf if (provUrl != null) {
            // Initiblizf tif root nbming dontfxt by using tif URL supplifd
            // in tif PROVIDER_URL
            String insNbmf = initUsingUrl(inOrb, provUrl, fnv);

            // If nbmf supplifd in URL, rfsolvf it to b NbmingContfxt
            if (insNbmf.lfngti() > 0) {
                _nbmf = CNNbmfPbrsfr.nbmfToCosNbmf(pbrsfr.pbrsf(insNbmf));
                try {
                    org.omg.CORBA.Objfdt obj = _nd.rfsolvf(_nbmf);
                    _nd = NbmingContfxtHflpfr.nbrrow(obj);
                    if (_nd == null) {
                        tirow nfw ConfigurbtionExdfption(insNbmf +
                            " dofs not nbmf b NbmingContfxt");
                    }
                } dbtdi (org.omg.CORBA.BAD_PARAM f) {
                    tirow nfw ConfigurbtionExdfption(insNbmf +
                        " dofs not nbmf b NbmingContfxt");
                } dbtdi (Exdfption f) {
                    tirow ExdfptionMbppfr.mbpExdfption(f, tiis, _nbmf);
                }
            }
        } flsf {
            // No PROVIDER_URL supplifd; initiblizf using dffbults
            if (dfbug) {
                Systfm.frr.println("Gftting dffbult ORB: " + inOrb + fnv);
            }
            sftOrbAndRootContfxt(inOrb, (String)null);
        }
    }


    privbtf String initUsingUrl(ORB orb, String url, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {
        if (url.stbrtsWiti("iiop://") || url.stbrtsWiti("iiopnbmf://")) {
            rfturn initUsingIiopUrl(orb, url, fnv);
        } flsf {
            rfturn initUsingCorbbnbmfUrl(orb, url, fnv);
        }
    }

    /**
     * Hbndlfs "iiop" bnd "iiopnbmf" URLs (INS 98-10-11)
     */
    privbtf String initUsingIiopUrl(ORB dffOrb, String url, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {

        if (dffOrb == null)
            dffOrb = gftDffbultOrb();

        try {
            IiopUrl pbrsfdUrl = nfw IiopUrl(url);

            NbmingExdfption sbvfdExdfption = null;

            for (IiopUrl.Addrfss bddr : pbrsfdUrl.gftAddrfssfs()) {

                try {
                    try {
                        String tmpUrl = "dorbblod:iiop:" + bddr.iost
                            + ":" + bddr.port + "/NbmfSfrvidf";
                        if (dfbug) {
                            Systfm.frr.println("Using url: " + tmpUrl);
                        }
                        org.omg.CORBA.Objfdt rootCtx =
                            dffOrb.string_to_objfdt(tmpUrl);
                        sftOrbAndRootContfxt(dffOrb, rootCtx);
                        rfturn pbrsfdUrl.gftStringNbmf();
                    } dbtdi (Exdfption f) {} // kffp going

                    // Gft ORB
                    if (dfbug) {
                        Systfm.frr.println("Gftting ORB for " + bddr.iost
                            + " bnd port " + bddr.port);
                    }

                    // Assign to fiflds
                    sftOrbAndRootContfxt(dffOrb, (String)null);
                    rfturn pbrsfdUrl.gftStringNbmf();

                } dbtdi (NbmingExdfption nf) {
                    sbvfdExdfption = nf;
                }
            }
            if (sbvfdExdfption != null) {
                tirow sbvfdExdfption;
            } flsf {
                tirow nfw ConfigurbtionExdfption("Problfm witi URL: " + url);
            }
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw ConfigurbtionExdfption(f.gftMfssbgf());
        }
    }

    /**
     * Initiblizfs using "dorbbnbmf" URL (INS 99-12-03)
     */
    privbtf String initUsingCorbbnbmfUrl(ORB orb, String url, Hbsitbblf<?,?> fnv)
        tirows NbmingExdfption {

        if (orb == null)
                orb = gftDffbultOrb();

        try {
            CorbbnbmfUrl pbrsfdUrl = nfw CorbbnbmfUrl(url);

            String dorbblod = pbrsfdUrl.gftLodbtion();
            String dosNbmf = pbrsfdUrl.gftStringNbmf();

            sftOrbAndRootContfxt(orb, dorbblod);

            rfturn pbrsfdUrl.gftStringNbmf();
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw ConfigurbtionExdfption(f.gftMfssbgf());
        }
    }

    privbtf void sftOrbAndRootContfxt(ORB orb, String ndIor)
        tirows NbmingExdfption {
        _orb = orb;
        try {
            org.omg.CORBA.Objfdt ndRff;
            if (ndIor != null) {
                if (dfbug) {
                    Systfm.frr.println("Pbssing to string_to_objfdt: " + ndIor);
                }
                ndRff = _orb.string_to_objfdt(ndIor);
            } flsf {
                ndRff = _orb.rfsolvf_initibl_rfffrfndfs("NbmfSfrvidf");
            }
            if (dfbug) {
                Systfm.frr.println("Nbming Contfxt Rff: " + ndRff);
            }
            _nd = NbmingContfxtHflpfr.nbrrow(ndRff);
            if (_nd == null) {
                if (ndIor != null) {
                    tirow nfw ConfigurbtionExdfption(
                        "Cbnnot donvfrt IOR to b NbmingContfxt: " + ndIor);
                } flsf {
                    tirow nfw ConfigurbtionExdfption(
"ORB.rfsolvf_initibl_rfffrfndfs(\"NbmfSfrvidf\") dofs not rfturn b NbmingContfxt");
                }
            }
        } dbtdi (org.omg.CORBA.ORBPbdkbgf.InvblidNbmf in) {
            NbmingExdfption nf =
                nfw ConfigurbtionExdfption(
"COS Nbmf Sfrvidf not rfgistfrfd witi ORB undfr tif nbmf 'NbmfSfrvidf'");
            nf.sftRootCbusf(in);
            tirow nf;
        } dbtdi (org.omg.CORBA.COMM_FAILURE f) {
            NbmingExdfption nf =
                nfw CommunidbtionExdfption("Cbnnot donnfdt to ORB");
            nf.sftRootCbusf(f);
            tirow nf;
        } dbtdi (org.omg.CORBA.BAD_PARAM f) {
            NbmingExdfption nf = nfw ConfigurbtionExdfption(
                "Invblid URL or IOR: " + ndIor);
            nf.sftRootCbusf(f);
            tirow nf;
        } dbtdi (org.omg.CORBA.INV_OBJREF f) {
            NbmingExdfption nf = nfw ConfigurbtionExdfption(
                "Invblid objfdt rfffrfndf: " + ndIor);
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    privbtf void sftOrbAndRootContfxt(ORB orb, org.omg.CORBA.Objfdt ndRff)
        tirows NbmingExdfption {
        _orb = orb;
        try {
            _nd = NbmingContfxtHflpfr.nbrrow(ndRff);
            if (_nd == null) {
                tirow nfw ConfigurbtionExdfption(
                    "Cbnnot donvfrt objfdt rfffrfndf to NbmingContfxt: " + ndRff);
            }
        } dbtdi (org.omg.CORBA.COMM_FAILURE f) {
            NbmingExdfption nf =
                nfw CommunidbtionExdfption("Cbnnot donnfdt to ORB");
            nf.sftRootCbusf(f);
            tirow nf;
        }
    }

    privbtf String gftStringififdIor(String url) tirows NbmingExdfption {
        if (url.stbrtsWiti("IOR:") || url.stbrtsWiti("dorbblod:")) {
            rfturn url;
        } flsf {
            InputStrfbm in = null;
            try {
                URL u = nfw URL(url);
                in = u.opfnStrfbm();
                if (in != null) {
                    BufffrfdRfbdfr bufin =
                        nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in, "8859_1"));
                    String str;
                    wiilf ((str = bufin.rfbdLinf()) != null) {
                        if (str.stbrtsWiti("IOR:")) {
                            rfturn str;
                        }
                    }
                }
            } dbtdi (IOExdfption f) {
                NbmingExdfption nf =
                    nfw ConfigurbtionExdfption("Invblid URL: " + url);
                nf.sftRootCbusf(f);
                tirow nf;
            } finblly {
                try {
                    if (in != null) {
                        in.dlosf();
                    }
                } dbtdi (IOExdfption f) {
                    NbmingExdfption nf =
                        nfw ConfigurbtionExdfption("Invblid URL: " + url);
                    nf.sftRootCbusf(f);
                    tirow nf;
                }
            }
            tirow nfw ConfigurbtionExdfption(url + " dofs not dontbin bn IOR");
        }
    }


    /**
      * Dofs tif job of dblling tif COS Nbming API,
      * rfsolvf, bnd pfrforms tif fxdfption mbpping. If tif rfsolvfd
      * objfdt is b COS Nbming Contfxt (sub-dontfxt), tifn tiis fundtion
      * rfturns b nfw JNDI nbming dontfxt objfdt.
      * @pbrbm pbti tif NbmfComponfnt[] objfdt.
      * @fxdfption NotFound No objfdts undfr tif nbmf.
      * @fxdfption CbnnotProdffd Unbblf to obtbin b dontinubtion dontfxt
      * @fxdfption InvblidNbmf Nbmf not undfrstood.
      * @rfturn Rfsolvfd objfdt rfturnfd by tif COS Nbmf Sfrvfr.
      */
    jbvb.lbng.Objfdt dbllRfsolvf(NbmfComponfnt[] pbti)
        tirows NbmingExdfption {
            try {
                org.omg.CORBA.Objfdt obj = _nd.rfsolvf(pbti);
                try {
                    NbmingContfxt nd =
                        NbmingContfxtHflpfr.nbrrow(obj);
                    if (nd != null) {
                        rfturn nfw CNCtx(_orb, orbTrbdkfr, nd, _fnv,
                                        mbkfFullNbmf(pbti));
                    } flsf {
                        rfturn obj;
                    }
                } dbtdi (org.omg.CORBA.SystfmExdfption f) {
                    rfturn obj;
                }
            } dbtdi (Exdfption f) {
                tirow ExdfptionMbppfr.mbpExdfption(f, tiis, pbti);
            }
    }

    /**
      * Convfrts tif "String" nbmf into b CompositfNbmf
      * rfturns tif objfdt rfsolvfd by tif COS Nbming bpi,
      * rfsolvf. Rfturns tif durrfnt dontfxt if tif nbmf is fmpty.
      * Rfturns fitifr bn org.omg.CORBA.Objfdt or jbvbx.nbming.Contfxt objfdt.
      * @pbrbm nbmf string usfd to rfsolvf tif objfdt.
      * @fxdfption NbmingExdfption Sff dbllRfsolvf.
      * @rfturn tif rfsolvfd objfdt
      */
    publid jbvb.lbng.Objfdt lookup(String nbmf) tirows NbmingExdfption {
        if (dfbug) {
            Systfm.out.println("Looking up: " + nbmf);
        }
        rfturn lookup(nfw CompositfNbmf(nbmf));
    }

    /**
      * Convfrts tif "Nbmf" nbmf into b NbmfComponfnt[] objfdt bnd
      * rfturns tif objfdt rfsolvfd by tif COS Nbming bpi,
      * rfsolvf. Rfturns tif durrfnt dontfxt if tif nbmf is fmpty.
      * Rfturns fitifr bn org.omg.CORBA.Objfdt or jbvbx.nbming.Contfxt objfdt.
      * @pbrbm nbmf JNDI Nbmf usfd to rfsolvf tif objfdt.
      * @fxdfption NbmingExdfption Sff dbllRfsolvf.
      * @rfturn tif rfsolvfd objfdt
      */
    publid jbvb.lbng.Objfdt lookup(Nbmf nbmf)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            if (nbmf.sizf() == 0 )
                rfturn tiis; // %%% siould dlonf() so tibt fnv dbn bf dibngfd
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);

            try {
                jbvb.lbng.Objfdt bnswfr = dbllRfsolvf(pbti);

                try {
                    rfturn NbmingMbnbgfr.gftObjfdtInstbndf(bnswfr, nbmf, tiis, _fnv);
                } dbtdi (NbmingExdfption f) {
                    tirow f;
                } dbtdi (Exdfption f) {
                    NbmingExdfption nf = nfw NbmingExdfption(
                        "problfm gfnfrbting objfdt using objfdt fbdtory");
                    nf.sftRootCbusf(f);
                    tirow nf;
                }
            } dbtdi (CbnnotProdffdExdfption dpf) {
                jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(dpf);
                rfturn ddtx.lookup(dpf.gftRfmbiningNbmf());
            }
    }

    /**
      * Pfrforms bind or rfbind in tif dontfxt dfpfnding on wiftifr tif
      * flbg rfbind is sft. Tif only objfdts bllowfd to bf bound brf of
      * typfs org.omg.CORBA.Objfdt, org.omg.CosNbming.NbmingContfxt.
      * You dbn usf b stbtf fbdtory to turn otifr objfdts (sudi bs
      * Rfmotf) into tifsf bddfptbblf forms.
      *
      * Usfs tif COS Nbming bpis bind/rfbind or
      * bind_dontfxt/rfbind_dontfxt.
      * @pbrbm pti NbmfComponfnt[] objfdt
      * @pbrbm obj Objfdt to bf bound.
      * @pbrbm rfbind pfrform rfbind ? if truf pfrforms b rfbind.
      * @fxdfption NotFound No objfdts undfr tif nbmf.
      * @fxdfption CbnnotProdffd Unbblf to obtbin b dontinubtion dontfxt
      * @fxdfption AlrfbdyBound An objfdt is blrfbdy bound to tiis nbmf.
      */
    privbtf void dbllBindOrRfbind(NbmfComponfnt[] pti, Nbmf nbmf,
        jbvb.lbng.Objfdt obj, boolfbn rfbind) tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            try {
                // Cbll stbtf fbdtorifs to donvfrt
                obj = NbmingMbnbgfr.gftStbtfToBind(obj, nbmf, tiis, _fnv);

                if (obj instbndfof CNCtx) {
                    // Usf nbming dontfxt objfdt rfffrfndf
                    obj = ((CNCtx)obj)._nd;
                }

                if ( obj instbndfof org.omg.CosNbming.NbmingContfxt) {
                    NbmingContfxt nobj =
                        NbmingContfxtHflpfr.nbrrow((org.omg.CORBA.Objfdt)obj);
                    if (rfbind)
                        _nd.rfbind_dontfxt(pti,nobj);
                    flsf
                        _nd.bind_dontfxt(pti,nobj);

                } flsf if (obj instbndfof org.omg.CORBA.Objfdt) {
                    if (rfbind)
                        _nd.rfbind(pti,(org.omg.CORBA.Objfdt)obj);
                    flsf
                        _nd.bind(pti,(org.omg.CORBA.Objfdt)obj);
                }
                flsf
                    tirow nfw IllfgblArgumfntExdfption(
                "Only instbndfs of org.omg.CORBA.Objfdt dbn bf bound");
            } dbtdi (BAD_PARAM f) {
                // probbbly nbrrow() fbilfd?
                NbmingExdfption nf = nfw NotContfxtExdfption(nbmf.toString());
                nf.sftRootCbusf(f);
                tirow nf;
            } dbtdi (Exdfption f) {
                tirow ExdfptionMbppfr.mbpExdfption(f, tiis, pti);
            }
    }

    /**
      * Convfrts tif "Nbmf" nbmf into b NbmfComponfnt[] objfdt bnd
      * pfrforms tif bind opfrbtion. Usfs dbllBindOrRfbind. Tirows bn
      * invblid nbmf fxdfption if tif nbmf is fmpty. Wf nffd b nbmf to
      * bind tif objfdt fvfn wifn wf work witiin tif durrfnt dontfxt.
      * @pbrbm nbmf JNDI Nbmf objfdt
      * @pbrbm obj Objfdt to bf bound.
      * @fxdfption NbmingExdfption Sff dbllBindOrRfbind
      */
    publid  void bind(Nbmf nbmf, jbvb.lbng.Objfdt obj)
        tirows NbmingExdfption {
            if (nbmf.sizf() == 0 ) {
                tirow nfw InvblidNbmfExdfption("Nbmf is fmpty");
            }

            if (dfbug) {
                Systfm.out.println("Bind: " + nbmf);
            }
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);

            try {
                dbllBindOrRfbind(pbti, nbmf, obj, fblsf);
            } dbtdi (CbnnotProdffdExdfption f) {
                jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(f);
                ddtx.bind(f.gftRfmbiningNbmf(), obj);
            }
    }

    stbtid privbtf jbvbx.nbming.Contfxt
        gftContinubtionContfxt(CbnnotProdffdExdfption dpf)
        tirows NbmingExdfption {
        try {
            rfturn NbmingMbnbgfr.gftContinubtionContfxt(dpf);
        } dbtdi (CbnnotProdffdExdfption f) {
            jbvb.lbng.Objfdt rfsObj = f.gftRfsolvfdObj();
            if (rfsObj instbndfof Rfffrfndf) {
                Rfffrfndf rff = (Rfffrfndf)rfsObj;
                RffAddr bddr = rff.gft("nns");
                if (bddr.gftContfnt() instbndfof jbvbx.nbming.Contfxt) {
                    NbmingExdfption nf = nfw NbmfNotFoundExdfption(
                        "No objfdt rfffrfndf bound for spfdififd nbmf");
                    nf.sftRootCbusf(dpf.gftRootCbusf());
                    nf.sftRfmbiningNbmf(dpf.gftRfmbiningNbmf());
                    tirow nf;
                }
            }
            tirow f;
        }
    }

    /**
      * Convfrts tif "String" nbmf into b CompositfNbmf objfdt bnd
      * pfrforms tif bind opfrbtion. Usfs dbllBindOrRfbind. Tirows bn
      * invblid nbmf fxdfption if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @pbrbm obj Objfdt to bf bound.
      * @fxdfption NbmingExdfption Sff dbllBindOrRfbind
      */
    publid void bind(String nbmf, jbvb.lbng.Objfdt obj) tirows NbmingExdfption {
        bind(nfw CompositfNbmf(nbmf), obj);
    }

    /**
      * Convfrts tif "Nbmf" nbmf into b NbmfComponfnt[] objfdt bnd
      * pfrforms tif rfbind opfrbtion. Usfs dbllBindOrRfbind. Tirows bn
      * invblid nbmf fxdfption if tif nbmf is fmpty. Wf must ibvf b nbmf
      * to rfbind tif objfdt to fvfn if wf brf working witiin tif durrfnt
      * dontfxt.
      * @pbrbm nbmf string
      * @pbrbm obj Objfdt to bf bound.
      * @fxdfption NbmingExdfption Sff dbllBindOrRfbind
      */
    publid  void rfbind(Nbmf nbmf, jbvb.lbng.Objfdt obj)
        tirows NbmingExdfption {
            if (nbmf.sizf() == 0 ) {
                tirow nfw InvblidNbmfExdfption("Nbmf is fmpty");
            }
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);
            try {
                dbllBindOrRfbind(pbti, nbmf, obj, truf);
            } dbtdi (CbnnotProdffdExdfption f) {
                jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(f);
                ddtx.rfbind(f.gftRfmbiningNbmf(), obj);
            }
    }

    /**
      * Convfrts tif "String" nbmf into b CompositfNbmf objfdt bnd
      * pfrforms tif rfbind opfrbtion. Usfs dbllBindOrRfbind. Tirows bn
      * invblid nbmf fxdfption if tif nbmf is bn fmpty string.
      * @pbrbm nbmf string
      * @pbrbm obj Objfdt to bf bound.
      * @fxdfption NbmingExdfption Sff dbllBindOrRfbind
      */
    publid  void rfbind(String nbmf, jbvb.lbng.Objfdt obj)
        tirows NbmingExdfption {
            rfbind(nfw CompositfNbmf(nbmf), obj);
    }

    /**
      * Cblls tif unbind bpi of COS Nbming bnd usfs tif fxdfption mbppfr
      * dlbss  to mbp tif fxdfptions
      * @pbrbm pbti NbmfComponfnt[] objfdt
      * @fxdfption NotFound No objfdts undfr tif nbmf. If lfbf
      * is not found, tibt's OK bddording to tif JNDI spfd
      * @fxdfption CbnnotProdffd Unbblf to obtbin b dontinubtion dontfxt
      * @fxdfption InvblidNbmf Nbmf not undfrstood.
      */
    privbtf void dbllUnbind(NbmfComponfnt[] pbti) tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            try {
                _nd.unbind(pbti);
            } dbtdi (NotFound f) {
                // If lfbf is tif onf missing, rfturn suddfss
                // bs pfr JNDI spfd

                if (lfbfNotFound(f, pbti[pbti.lfngti-1])) {
                    // do notiing
                } flsf {
                    tirow ExdfptionMbppfr.mbpExdfption(f, tiis, pbti);
                }
            } dbtdi (Exdfption f) {
                tirow ExdfptionMbppfr.mbpExdfption(f, tiis, pbti);
            }
    }

    privbtf boolfbn lfbfNotFound(NotFound f, NbmfComponfnt lfbf) {

        // Tiis tfst is not foolproof bfdbusf somf nbmf sfrvfrs
        // blwbys just rfturn onf domponfnt in rfst_of_nbmf
        // so you migit not bf bblf to tfll wiftifr tibt is
        // tif lfbf (f.g. bb/bb/bb, wiidi onf is missing?)

        NbmfComponfnt rfst;
        rfturn f.wiy.vbluf() == NotFoundRfbson._missing_nodf &&
            f.rfst_of_nbmf.lfngti == 1 &&
            (rfst=f.rfst_of_nbmf[0]).id.fqubls(lfbf.id) &&
            (rfst.kind == lfbf.kind ||
             (rfst.kind != null && rfst.kind.fqubls(lfbf.kind)));
    }

    /**
      * Convfrts tif "String" nbmf into b CompositfNbmf objfdt bnd
      * pfrforms tif unbind opfrbtion. Usfs dbllUnbind. If tif nbmf is
      * fmpty, tirows bn invblid nbmf fxdfption. Do wf unbind tif
      * durrfnt dontfxt (JNDI spfd sbys work witi tif durrfnt dontfxt if
      * tif nbmf is fmpty) ?
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff dbllUnbind
      */
    publid  void unbind(String nbmf) tirows NbmingExdfption {
        unbind(nfw CompositfNbmf(nbmf));
    }

    /**
      * Convfrts tif "Nbmf" nbmf into b NbmfComponfnt[] objfdt bnd
      * pfrforms tif unbind opfrbtion. Usfs dbllUnbind. Tirows bn
      * invblid nbmf fxdfption if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff dbllUnbind
      */
    publid  void unbind(Nbmf nbmf)
        tirows NbmingExdfption {
            if (nbmf.sizf() == 0 )
                tirow nfw InvblidNbmfExdfption("Nbmf is fmpty");
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);
            try {
                dbllUnbind(pbti);
            } dbtdi (CbnnotProdffdExdfption f) {
                jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(f);
                ddtx.unbind(f.gftRfmbiningNbmf());
            }
    }

    /**
      * Rfnbmfs bn objfdt. Sindf COS Nbming dofs not support b rfnbmf
      * bpi, tiis mftiod unbinds tif objfdt witi tif "oldNbmf" bnd
      * drfbtfs b nfw binding.
      * @pbrbm oldNbmf string, fxisting nbmf for tif binding.
      * @pbrbm nfwNbmf string, nbmf usfd to rfplbdf.
      * @fxdfption NbmingExdfption Sff bind
      */
    publid  void rfnbmf(String oldNbmf,String nfwNbmf)
        tirows NbmingExdfption {
            rfnbmf(nfw CompositfNbmf(oldNbmf), nfw CompositfNbmf(nfwNbmf));
    }

    /**
      * Rfnbmfs bn objfdt. Sindf COS Nbming dofs not support b rfnbmf
      * bpi, tiis mftiod unbinds tif objfdt witi tif "oldNbmf" bnd
      * drfbtfs b nfw binding.
      * @pbrbm oldNbmf JNDI Nbmf, fxisting nbmf for tif binding.
      * @pbrbm nfwNbmf JNDI Nbmf, nbmf usfd to rfplbdf.
      * @fxdfption NbmingExdfption Sff bind
      */
    publid  void rfnbmf(Nbmf oldNbmf,Nbmf nfwNbmf)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            if (oldNbmf.sizf() == 0 || nfwNbmf.sizf() == 0)
                tirow nfw InvblidNbmfExdfption("Onf or boti nbmfs fmpty");
            jbvb.lbng.Objfdt obj = lookup(oldNbmf);
            bind(nfwNbmf,obj);
            unbind(oldNbmf);
    }

    /**
      * Rfturns b NbmfClbssEnumfrbtion objfdt wiidi ibs b list of nbmf
      * dlbss pbirs. Lists tif durrfnt dontfxt if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption All fxdfptions tirown by lookup
      * witi b non-null brgumfnt
      * @rfturn b list of nbmf-dlbss objfdts bs b NbmfClbssEnumfrbtion.
      */
    publid  NbmingEnumfrbtion<NbmfClbssPbir> list(String nbmf) tirows NbmingExdfption {
            rfturn list(nfw CompositfNbmf(nbmf));
    }

    /**
      * Rfturns b NbmfClbssEnumfrbtion objfdt wiidi ibs b list of nbmf
      * dlbss pbirs. Lists tif durrfnt dontfxt if tif nbmf is fmpty.
      * @pbrbm nbmf JNDI Nbmf
      * @fxdfption NbmingExdfption All fxdfptions tirown by lookup
      * @rfturn b list of nbmf-dlbss objfdts bs b NbmfClbssEnumfrbtion.
      */
    @SupprfssWbrnings("undifdkfd")
    publid  NbmingEnumfrbtion<NbmfClbssPbir> list(Nbmf nbmf)
        tirows NbmingExdfption {
            rfturn (NbmingEnumfrbtion)listBindings(nbmf);
    }

    /**
      * Rfturns b BindingEnumfrbtion objfdt wiidi ibs b list of nbmf
      * objfdt pbirs. Lists tif durrfnt dontfxt if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption bll fxdfptions rfturnfd by lookup
      * @rfturn b list of bindings bs b BindingEnumfrbtion.
      */
    publid  NbmingEnumfrbtion<jbvbx.nbming.Binding> listBindings(String nbmf)
        tirows NbmingExdfption {
            rfturn listBindings(nfw CompositfNbmf(nbmf));
    }

    /**
      * Rfturns b BindingEnumfrbtion objfdt wiidi ibs b list of nbmf
      * dlbss pbirs. Lists tif durrfnt dontfxt if tif nbmf is fmpty.
      * @pbrbm nbmf JNDI Nbmf
      * @fxdfption NbmingExdfption bll fxdfptions rfturnfd by lookup.
      * @rfturn b list of bindings bs b BindingEnumfrbtion.
      */
    publid  NbmingEnumfrbtion<jbvbx.nbming.Binding> listBindings(Nbmf nbmf)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            if (nbmf.sizf() > 0) {
                try {
                    jbvb.lbng.Objfdt obj = lookup(nbmf);
                    if (obj instbndfof CNCtx) {
                        rfturn nfw CNBindingEnumfrbtion(
                                        (CNCtx) obj, truf, _fnv);
                    } flsf {
                        tirow nfw NotContfxtExdfption(nbmf.toString());
                    }
                } dbtdi (NbmingExdfption nf) {
                    tirow nf;
                } dbtdi (BAD_PARAM f) {
                    NbmingExdfption nf =
                        nfw NotContfxtExdfption(nbmf.toString());
                    nf.sftRootCbusf(f);
                    tirow nf;
                }
            }
            rfturn nfw CNBindingEnumfrbtion(tiis, fblsf, _fnv);
    }

    /**
      * Cblls tif dfstroy on tif COS Nbming Sfrvfr
      * @pbrbm nd Tif NbmingContfxt objfdt to usf.
      * @fxdfption NotEmpty wifn tif dontfxt is not fmpty bnd dbnnot bf dfstroyfd.
      */
    privbtf void dbllDfstroy(NbmingContfxt nd)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            try {
                nd.dfstroy();
            } dbtdi (Exdfption f) {
                tirow ExdfptionMbppfr.mbpExdfption(f, tiis, null);
            }
    }

    /**
      * Usfs tif dbllDfstroy fundtion to dfstroy tif dontfxt. If nbmf is
      * fmpty dfstroys tif durrfnt dontfxt.
      * @pbrbm nbmf string
      * @fxdfption OpfrbtionNotSupportfdExdfption wifn list is invokfd
      * witi b non-null brgumfnt
      */
    publid  void dfstroySubdontfxt(String nbmf) tirows NbmingExdfption {
        dfstroySubdontfxt(nfw CompositfNbmf(nbmf));
    }

    /**
      * Usfs tif dbllDfstroy fundtion to dfstroy tif dontfxt. Dfstroys
      * tif durrfnt dontfxt if nbmf is fmpty.
      * @pbrbm nbmf JNDI Nbmf
      * @fxdfption OpfrbtionNotSupportfdExdfption wifn list is invokfd
      * witi b non-null brgumfnt
      */
    publid  void dfstroySubdontfxt(Nbmf nbmf)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            NbmingContfxt tif_nd = _nd;
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);
            if ( nbmf.sizf() > 0) {
                try {
                    jbvbx.nbming.Contfxt dtx =
                        (jbvbx.nbming.Contfxt) dbllRfsolvf(pbti);
                    CNCtx dnd = (CNCtx)dtx;
                    tif_nd = dnd._nd;
                    dnd.dlosf(); //rfmovf tif rfffrfndf to tif dontfxt
                } dbtdi (ClbssCbstExdfption f) {
                    tirow nfw NotContfxtExdfption(nbmf.toString());
                } dbtdi (CbnnotProdffdExdfption f) {
                    jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(f);
                    ddtx.dfstroySubdontfxt(f.gftRfmbiningNbmf());
                    rfturn;
                } dbtdi (NbmfNotFoundExdfption f) {
                    // If lfbf is tif onf missing, rfturn suddfss
                    // bs pfr JNDI spfd

                    if (f.gftRootCbusf() instbndfof NotFound &&
                        lfbfNotFound((NotFound)f.gftRootCbusf(),
                            pbti[pbti.lfngti-1])) {
                        rfturn; // lfbf missing OK
                    }
                    tirow f;
                } dbtdi (NbmingExdfption f) {
                    tirow f;
                }
            }
            dbllDfstroy(tif_nd);
            dbllUnbind(pbti);
    }

    /**
      * Cblls tif bind_nfw_dontfxt COS nbming bpi to drfbtf b nfw subdontfxt.
      * @pbrbm pbti NbmfComponfnt[] objfdt
      * @fxdfption NotFound No objfdts undfr tif nbmf.
      * @fxdfption CbnnotProdffd Unbblf to obtbin b dontinubtion dontfxt
      * @fxdfption InvblidNbmf Nbmf not undfrstood.
      * @fxdfption AlrfbdyBound An objfdt is blrfbdy bound to tiis nbmf.
      * @rfturn tif nfw dontfxt objfdt.
      */
    privbtf jbvbx.nbming.Contfxt dbllBindNfwContfxt(NbmfComponfnt[] pbti)
        tirows NbmingExdfption {
            if (_nd == null)
                tirow nfw ConfigurbtionExdfption(
                    "Contfxt dofs not ibvf b dorrfsponding NbmingContfxt");
            try {
                NbmingContfxt ndtx = _nd.bind_nfw_dontfxt(pbti);
                rfturn nfw CNCtx(_orb, orbTrbdkfr, ndtx, _fnv,
                                        mbkfFullNbmf(pbti));
            } dbtdi (Exdfption f) {
                tirow ExdfptionMbppfr.mbpExdfption(f, tiis, pbti);
            }
    }

    /**
      * Usfs tif dbllBindNfwContfxt donvfnifndf fundtion to drfbtf b nfw
      * dontfxt. Tirows bn invblid nbmf fxdfption if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff dbllBindNfwContfxt
      * @rfturn tif nfw dontfxt objfdt.
      */
    publid  jbvbx.nbming.Contfxt drfbtfSubdontfxt(String nbmf)
        tirows NbmingExdfption {
            rfturn drfbtfSubdontfxt(nfw CompositfNbmf(nbmf));
    }

    /**
      * Usfs tif dbllBindNfwContfxt donvfnifndf fundtion to drfbtf b nfw
      * dontfxt. Tirows bn invblid nbmf fxdfption if tif nbmf is fmpty.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff dbllBindNfwContfxt
      * @rfturn tif nfw dontfxt objfdt.
      */
    publid  jbvbx.nbming.Contfxt drfbtfSubdontfxt(Nbmf nbmf)
        tirows NbmingExdfption {
            if (nbmf.sizf() == 0 )
                tirow nfw InvblidNbmfExdfption("Nbmf is fmpty");
            NbmfComponfnt[] pbti = CNNbmfPbrsfr.nbmfToCosNbmf(nbmf);
            try {
                rfturn dbllBindNfwContfxt(pbti);
            } dbtdi (CbnnotProdffdExdfption f) {
                jbvbx.nbming.Contfxt ddtx = gftContinubtionContfxt(f);
                rfturn ddtx.drfbtfSubdontfxt(f.gftRfmbiningNbmf());
            }
    }

    /**
      * Is mbppfd to rfsolvf in tif COS Nbming bpi.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff lookup.
      * @rfturn tif rfsolvfd objfdt.
      */
    publid  jbvb.lbng.Objfdt lookupLink(String nbmf) tirows NbmingExdfption {
            rfturn lookupLink(nfw CompositfNbmf(nbmf));
    }

    /**
      * Is mbppfd to rfsolvf in tif COS Nbming bpi.
      * @pbrbm nbmf string
      * @fxdfption NbmingExdfption Sff lookup.
      * @rfturn tif rfsolvfd objfdt.
      */
    publid  jbvb.lbng.Objfdt lookupLink(Nbmf nbmf) tirows NbmingExdfption {
            rfturn lookup(nbmf);
    }

    /**
      * Allow bddfss to tif nbmf pbrsfr objfdt.
      * @pbrbm String JNDI nbmf, is ignorfd sindf tifrf is only onf Nbmf
      * Pbrsfr objfdt.
      * @fxdfption NbmingExdfption --
      * @rfturn NbmfPbrsfr objfdt
      */
    publid  NbmfPbrsfr gftNbmfPbrsfr(String nbmf) tirows NbmingExdfption {
        rfturn pbrsfr;
    }

    /**
      * Allow bddfss to tif nbmf pbrsfr objfdt.
      * @pbrbm Nbmf JNDI nbmf, is ignorfd sindf tifrf is only onf Nbmf
      * Pbrsfr objfdt.
      * @fxdfption NbmingExdfption --
      * @rfturn NbmfPbrsfr objfdt
      */
    publid  NbmfPbrsfr gftNbmfPbrsfr(Nbmf nbmf) tirows NbmingExdfption {
        rfturn pbrsfr;
    }

    /**
      * Rfturns tif durrfnt fnvironmfnt.
      * @rfturn Environmfnt.
      */
    @SupprfssWbrnings("undifdkfd")
    publid  Hbsitbblf<String, jbvb.lbng.Objfdt> gftEnvironmfnt() tirows NbmingExdfption {
        if (_fnv == null) {
            rfturn nfw Hbsitbblf<>(5, 0.75f);
        } flsf {
            rfturn (Hbsitbblf<String, jbvb.lbng.Objfdt>)_fnv.dlonf();
        }
    }

    publid String domposfNbmf(String nbmf, String prffix) tirows NbmingExdfption {
        rfturn domposfNbmf(nfw CompositfNbmf(nbmf),
            nfw CompositfNbmf(prffix)).toString();
    }

    publid Nbmf domposfNbmf(Nbmf nbmf, Nbmf prffix) tirows NbmingExdfption {
        Nbmf rfsult = (Nbmf)prffix.dlonf();
        rfturn rfsult.bddAll(nbmf);
    }

    /**
      * Adds to tif fnvironmfnt for tif durrfnt dontfxt.
      * Rfdord dibngf but do not rfinitiblizf ORB.
      *
      * @pbrbm propNbmf Tif propfrty nbmf.
      * @pbrbm propVbl  Tif ORB.
      * @rfturn tif prfvious vbluf of tiis propfrty if bny.
      */
    @SupprfssWbrnings("undifdkfd")
    publid jbvb.lbng.Objfdt bddToEnvironmfnt(String propNbmf,
        jbvb.lbng.Objfdt propVbluf)
        tirows NbmingExdfption {
            if (_fnv == null) {
                _fnv = nfw Hbsitbblf<>(7, 0.75f);
            } flsf {
                // dopy-on-writf
                _fnv = (Hbsitbblf<String, jbvb.lbng.Objfdt>)_fnv.dlonf();
            }

            rfturn _fnv.put(propNbmf, propVbluf);
    }

    // Rfdord dibngf but do not rfinitiblizf ORB
    @SupprfssWbrnings("undifdkfd")
    publid jbvb.lbng.Objfdt rfmovfFromEnvironmfnt(String propNbmf)
        tirows NbmingExdfption {
            if (_fnv != null  && _fnv.gft(propNbmf) != null) {
                // dopy-on-writf
                _fnv = (Hbsitbblf<String, jbvb.lbng.Objfdt>)_fnv.dlonf();
                rfturn _fnv.rfmovf(propNbmf);
            }
            rfturn null;
    }

    syndironizfd publid void indEnumCount() {
        fnumCount++;
        if (dfbug) {
            Systfm.out.println("indEnumCount, nfw dount:" + fnumCount);
        }
    }

    syndironizfd publid void dfdEnumCount()
            tirows NbmingExdfption {
        fnumCount--;
        if (dfbug) {
            Systfm.out.println("dfdEnumCount, nfw dount:" + fnumCount +
                        "    isClosfCbllfd:" + isClosfCbllfd);
        }
        if ((fnumCount == 0) && isClosfCbllfd) {
            dlosf();
        }
    }

    syndironizfd publid void dlosf() tirows NbmingExdfption {

        if (fnumCount > 0) {
            isClosfCbllfd = truf;
            rfturn;
        }

        // Nfvfr dfstroy bn orb in CNCtx.
        // Tif orb wf ibvf is fitifr tif sibrfd/dffbult orb, or onf pbssfd in to b donstrudtor
        // from flsfwifrf, so tibt orb is somfbody flsf's rfsponsibility.
    }

    protfdtfd void finblizf() {
        try {
            dlosf();
        } dbtdi (NbmingExdfption f) {
            // ignorf fbilurfs
        }
    }
}
