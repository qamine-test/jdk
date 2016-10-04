/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.Providfr.Sfrvidf;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvb.nio.BytfBufffr;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.jdb.*;
import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tiis dlbss providfs tif fundtionblity of b "Mfssbgf Autifntidbtion Codf"
 * (MAC) blgoritim.
 *
 * <p> A MAC providfs b wby to difdk
 * tif intfgrity of informbtion trbnsmittfd ovfr or storfd in bn unrflibblf
 * mfdium, bbsfd on b sfdrft kfy. Typidblly, mfssbgf
 * butifntidbtion dodfs brf usfd bftwffn two pbrtifs tibt sibrf b sfdrft
 * kfy in ordfr to vblidbtf informbtion trbnsmittfd bftwffn tifsf
 * pbrtifs.
 *
 * <p> A MAC mfdibnism tibt is bbsfd on dryptogrbpiid ibsi fundtions is
 * rfffrrfd to bs HMAC. HMAC dbn bf usfd witi bny dryptogrbpiid ibsi fundtion,
 * f.g., MD5 or SHA-1, in dombinbtion witi b sfdrft sibrfd kfy. HMAC is
 * spfdififd in RFC 2104.
 *
 * <p> Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support
 * tif following stbndbrd <dodf>Mbd</dodf> blgoritims:
 * <ul>
 * <li><tt>HmbdMD5</tt></li>
 * <li><tt>HmbdSHA1</tt></li>
 * <li><tt>HmbdSHA256</tt></li>
 * </ul>
 * Tifsf blgoritims brf dfsdribfd in tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Mbd">
 * Mbd sfdtion</b> of tif
 * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion.
 * Consult tif rflfbsf dodumfntbtion for your implfmfntbtion to sff if bny
 * otifr blgoritims brf supportfd.
 *
 * @butior Jbn Lufif
 *
 * @sindf 1.4
 */

publid dlbss Mbd implfmfnts Clonfbblf {

    privbtf stbtid finbl Dfbug dfbug =
                        Dfbug.gftInstbndf("jdb", "Mbd");

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf MbdSpi spi;

    // Tif nbmf of tif MAC blgoritim.
    privbtf finbl String blgoritim;

    // Hbs tiis objfdt bffn initiblizfd?
    privbtf boolfbn initiblizfd = fblsf;

    // nfxt sfrvidf to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf Sfrvidf firstSfrvidf;

    // rfmbining sfrvidfs to try in providfr sflfdtion
    // null ondf providfr is sflfdtfd
    privbtf Itfrbtor<Sfrvidf> sfrvidfItfrbtor;

    privbtf finbl Objfdt lodk;

    /**
     * Crfbtfs b MAC objfdt.
     *
     * @pbrbm mbdSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm blgoritim tif blgoritim
     */
    protfdtfd Mbd(MbdSpi mbdSpi, Providfr providfr, String blgoritim) {
        tiis.spi = mbdSpi;
        tiis.providfr = providfr;
        tiis.blgoritim = blgoritim;
        sfrvidfItfrbtor = null;
        lodk = null;
    }

    privbtf Mbd(Sfrvidf s, Itfrbtor<Sfrvidf> t, String blgoritim) {
        firstSfrvidf = s;
        sfrvidfItfrbtor = t;
        tiis.blgoritim = blgoritim;
        lodk = nfw Objfdt();
    }

    /**
     * Rfturns tif blgoritim nbmf of tiis <dodf>Mbd</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>Mbd</dodf> objfdt.
     *
     * @rfturn tif blgoritim nbmf of tiis <dodf>Mbd</dodf> objfdt.
     */
    publid finbl String gftAlgoritim() {
        rfturn tiis.blgoritim;
    }

    /**
     * Rfturns b <dodf>Mbd</dodf> objfdt tibt implfmfnts tif
     * spfdififd MAC blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw Mbd objfdt fndbpsulbting tif
     * MbdSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd MAC blgoritim.
     * Sff tif Mbd sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Mbd">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @rfturn tif nfw <dodf>Mbd</dodf> objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b
     *          MbdSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Mbd gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        List<Sfrvidf> sfrvidfs = GftInstbndf.gftSfrvidfs("Mbd", blgoritim);
        // mbkf surf tifrf is bt lfbst onf sfrvidf from b signfd providfr
        Itfrbtor<Sfrvidf> t = sfrvidfs.itfrbtor();
        wiilf (t.ibsNfxt()) {
            Sfrvidf s = t.nfxt();
            if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                dontinuf;
            }
            rfturn nfw Mbd(s, t, blgoritim);
        }
        tirow nfw NoSudiAlgoritimExdfption
                                ("Algoritim " + blgoritim + " not bvbilbblf");
    }

    /**
     * Rfturns b <dodf>Mbd</dodf> objfdt tibt implfmfnts tif
     * spfdififd MAC blgoritim.
     *
     * <p> A nfw Mbd objfdt fndbpsulbting tif
     * MbdSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd MAC blgoritim.
     * Sff tif Mbd sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Mbd">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw <dodf>Mbd</dodf> objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b MbdSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Mbd gftInstbndf(String blgoritim, String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        Instbndf instbndf = JdfSfdurity.gftInstbndf
                ("Mbd", MbdSpi.dlbss, blgoritim, providfr);
        rfturn nfw Mbd((MbdSpi)instbndf.impl, instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns b <dodf>Mbd</dodf> objfdt tibt implfmfnts tif
     * spfdififd MAC blgoritim.
     *
     * <p> A nfw Mbd objfdt fndbpsulbting tif
     * MbdSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd MAC blgoritim.
     * Sff tif Mbd sfdtion in tif <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Mbd">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd blgoritim nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw <dodf>Mbd</dodf> objfdt.
     *
     * @fxdfption NoSudiAlgoritimExdfption if b MbdSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl Mbd gftInstbndf(String blgoritim, Providfr providfr)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = JdfSfdurity.gftInstbndf
                ("Mbd", MbdSpi.dlbss, blgoritim, providfr);
        rfturn nfw Mbd((MbdSpi)instbndf.impl, instbndf.providfr, blgoritim);
    }

    // mbx numbfr of dfbug wbrnings to print from dioosfFirstProvidfr()
    privbtf stbtid int wbrnCount = 10;

    /**
     * Cioosf tif Spi from tif first providfr bvbilbblf. Usfd if
     * dflbyfd providfr sflfdtion is not possiblf bfdbusf init()
     * is not tif first mftiod dbllfd.
     */
    void dioosfFirstProvidfr() {
        if ((spi != null) || (sfrvidfItfrbtor == null)) {
            rfturn;
        }
        syndironizfd (lodk) {
            if (spi != null) {
                rfturn;
            }
            if (dfbug != null) {
                int w = --wbrnCount;
                if (w >= 0) {
                    dfbug.println("Mbd.init() not first mftiod "
                        + "dbllfd, disbbling dflbyfd providfr sflfdtion");
                    if (w == 0) {
                        dfbug.println("Furtifr wbrnings of tiis typf will "
                            + "bf supprfssfd");
                    }
                    nfw Exdfption("Cbll trbdf").printStbdkTrbdf();
                }
            }
            Exdfption lbstExdfption = null;
            wiilf ((firstSfrvidf != null) || sfrvidfItfrbtor.ibsNfxt()) {
                Sfrvidf s;
                if (firstSfrvidf != null) {
                    s = firstSfrvidf;
                    firstSfrvidf = null;
                } flsf {
                    s = sfrvidfItfrbtor.nfxt();
                }
                if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                    dontinuf;
                }
                try {
                    Objfdt obj = s.nfwInstbndf(null);
                    if (obj instbndfof MbdSpi == fblsf) {
                        dontinuf;
                    }
                    spi = (MbdSpi)obj;
                    providfr = s.gftProvidfr();
                    // not nffdfd bny morf
                    firstSfrvidf = null;
                    sfrvidfItfrbtor = null;
                    rfturn;
                } dbtdi (NoSudiAlgoritimExdfption f) {
                    lbstExdfption = f;
                }
            }
            ProvidfrExdfption f = nfw ProvidfrExdfption
                    ("Could not donstrudt MbdSpi instbndf");
            if (lbstExdfption != null) {
                f.initCbusf(lbstExdfption);
            }
            tirow f;
        }
    }

    privbtf void dioosfProvidfr(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        syndironizfd (lodk) {
            if (spi != null) {
                spi.fnginfInit(kfy, pbrbms);
                rfturn;
            }
            Exdfption lbstExdfption = null;
            wiilf ((firstSfrvidf != null) || sfrvidfItfrbtor.ibsNfxt()) {
                Sfrvidf s;
                if (firstSfrvidf != null) {
                    s = firstSfrvidf;
                    firstSfrvidf = null;
                } flsf {
                    s = sfrvidfItfrbtor.nfxt();
                }
                // if providfr sbys it dofs not support tiis kfy, ignorf it
                if (s.supportsPbrbmftfr(kfy) == fblsf) {
                    dontinuf;
                }
                if (JdfSfdurity.dbnUsfProvidfr(s.gftProvidfr()) == fblsf) {
                    dontinuf;
                }
                try {
                    MbdSpi spi = (MbdSpi)s.nfwInstbndf(null);
                    spi.fnginfInit(kfy, pbrbms);
                    providfr = s.gftProvidfr();
                    tiis.spi = spi;
                    firstSfrvidf = null;
                    sfrvidfItfrbtor = null;
                    rfturn;
                } dbtdi (Exdfption f) {
                    // NoSudiAlgoritimExdfption from nfwInstbndf()
                    // InvblidKfyExdfption from init()
                    // RuntimfExdfption (ProvidfrExdfption) from init()
                    if (lbstExdfption == null) {
                        lbstExdfption = f;
                    }
                }
            }
            // no working providfr found, fbil
            if (lbstExdfption instbndfof InvblidKfyExdfption) {
                tirow (InvblidKfyExdfption)lbstExdfption;
            }
            if (lbstExdfption instbndfof InvblidAlgoritimPbrbmftfrExdfption) {
                tirow (InvblidAlgoritimPbrbmftfrExdfption)lbstExdfption;
            }
            if (lbstExdfption instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)lbstExdfption;
            }
            String kNbmf = (kfy != null) ? kfy.gftClbss().gftNbmf() : "(null)";
            tirow nfw InvblidKfyExdfption
                ("No instbllfd providfr supports tiis kfy: "
                + kNbmf, lbstExdfption);
        }
    }

    /**
     * Rfturns tif providfr of tiis <dodf>Mbd</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>Mbd</dodf> objfdt.
     */
    publid finbl Providfr gftProvidfr() {
        dioosfFirstProvidfr();
        rfturn tiis.providfr;
    }

    /**
     * Rfturns tif lfngti of tif MAC in bytfs.
     *
     * @rfturn tif MAC lfngti in bytfs.
     */
    publid finbl int gftMbdLfngti() {
        dioosfFirstProvidfr();
        rfturn spi.fnginfGftMbdLfngti();
    }

    /**
     * Initiblizfs tiis <dodf>Mbd</dodf> objfdt witi tif givfn kfy.
     *
     * @pbrbm kfy tif kfy.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis MAC.
     */
    publid finbl void init(Kfy kfy) tirows InvblidKfyExdfption {
        try {
            if (spi != null) {
                spi.fnginfInit(kfy, null);
            } flsf {
                dioosfProvidfr(kfy, null);
            }
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption f) {
            tirow nfw InvblidKfyExdfption("init() fbilfd", f);
        }
        initiblizfd = truf;
    }

    /**
     * Initiblizfs tiis <dodf>Mbd</dodf> objfdt witi tif givfn kfy bnd
     * blgoritim pbrbmftfrs.
     *
     * @pbrbm kfy tif kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis MAC.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis MAC.
     */
    publid finbl void init(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        if (spi != null) {
            spi.fnginfInit(kfy, pbrbms);
        } flsf {
            dioosfProvidfr(kfy, pbrbms);
        }
        initiblizfd = truf;
    }

    /**
     * Prodfssfs tif givfn bytf.
     *
     * @pbrbm input tif input bytf to bf prodfssfd.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl void updbtf(bytf input) tirows IllfgblStbtfExdfption {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        spi.fnginfUpdbtf(input);
    }

    /**
     * Prodfssfs tif givfn brrby of bytfs.
     *
     * @pbrbm input tif brrby of bytfs to bf prodfssfd.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl void updbtf(bytf[] input) tirows IllfgblStbtfExdfption {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        if (input != null) {
            spi.fnginfUpdbtf(input, 0, input.lfngti);
        }
    }

    /**
     * Prodfssfs tif first <dodf>lfn</dodf> bytfs in <dodf>input</dodf>,
     * stbrting bt <dodf>offsft</dodf> indlusivf.
     *
     * @pbrbm input tif input bufffr.
     * @pbrbm offsft tif offsft in <dodf>input</dodf> wifrf tif input stbrts.
     * @pbrbm lfn tif numbfr of bytfs to prodfss.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl void updbtf(bytf[] input, int offsft, int lfn)
            tirows IllfgblStbtfExdfption {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }

        if (input != null) {
            if ((offsft < 0) || (lfn > (input.lfngti - offsft)) || (lfn < 0))
                tirow nfw IllfgblArgumfntExdfption("Bbd brgumfnts");
            spi.fnginfUpdbtf(input, offsft, lfn);
        }
    }

    /**
     * Prodfssfs <dodf>input.rfmbining()</dodf> bytfs in tif BytfBufffr
     * <dodf>input</dodf>, stbrting bt <dodf>input.position()</dodf>.
     * Upon rfturn, tif bufffr's position will bf fqubl to its limit;
     * its limit will not ibvf dibngfd.
     *
     * @pbrbm input tif BytfBufffr
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     * @sindf 1.5
     */
    publid finbl void updbtf(BytfBufffr input) {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("Bufffr must not bf null");
        }
        spi.fnginfUpdbtf(input);
    }

    /**
     * Finisifs tif MAC opfrbtion.
     *
     * <p>A dbll to tiis mftiod rfsfts tiis <dodf>Mbd</dodf> objfdt to tif
     * stbtf it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to gfnfrbtf bnotifr MAC from
     * tif sbmf kfy, if dfsirfd, vib nfw dblls to <dodf>updbtf</dodf> bnd
     * <dodf>doFinbl</dodf>.
     * (In ordfr to rfusf tiis <dodf>Mbd</dodf> objfdt witi b difffrfnt kfy,
     * it must bf rfinitiblizfd vib b dbll to <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     *
     * @rfturn tif MAC rfsult.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl bytf[] doFinbl() tirows IllfgblStbtfExdfption {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        bytf[] mbd = spi.fnginfDoFinbl();
        spi.fnginfRfsft();
        rfturn mbd;
    }

    /**
     * Finisifs tif MAC opfrbtion.
     *
     * <p>A dbll to tiis mftiod rfsfts tiis <dodf>Mbd</dodf> objfdt to tif
     * stbtf it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to gfnfrbtf bnotifr MAC from
     * tif sbmf kfy, if dfsirfd, vib nfw dblls to <dodf>updbtf</dodf> bnd
     * <dodf>doFinbl</dodf>.
     * (In ordfr to rfusf tiis <dodf>Mbd</dodf> objfdt witi b difffrfnt kfy,
     * it must bf rfinitiblizfd vib b dbll to <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     *
     * <p>Tif MAC rfsult is storfd in <dodf>output</dodf>, stbrting bt
     * <dodf>outOffsft</dodf> indlusivf.
     *
     * @pbrbm output tif bufffr wifrf tif MAC rfsult is storfd
     * @pbrbm outOffsft tif offsft in <dodf>output</dodf> wifrf tif MAC is
     * storfd
     *
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl void doFinbl(bytf[] output, int outOffsft)
        tirows SiortBufffrExdfption, IllfgblStbtfExdfption
    {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        int mbdLfn = gftMbdLfngti();
        if (output == null || output.lfngti-outOffsft < mbdLfn) {
            tirow nfw SiortBufffrExdfption
                ("Cbnnot storf MAC in output bufffr");
        }
        bytf[] mbd = doFinbl();
        Systfm.brrbydopy(mbd, 0, output, outOffsft, mbdLfn);
        rfturn;
    }

    /**
     * Prodfssfs tif givfn brrby of bytfs bnd finisifs tif MAC opfrbtion.
     *
     * <p>A dbll to tiis mftiod rfsfts tiis <dodf>Mbd</dodf> objfdt to tif
     * stbtf it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to gfnfrbtf bnotifr MAC from
     * tif sbmf kfy, if dfsirfd, vib nfw dblls to <dodf>updbtf</dodf> bnd
     * <dodf>doFinbl</dodf>.
     * (In ordfr to rfusf tiis <dodf>Mbd</dodf> objfdt witi b difffrfnt kfy,
     * it must bf rfinitiblizfd vib b dbll to <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     *
     * @pbrbm input dbtb in bytfs
     * @rfturn tif MAC rfsult.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis <dodf>Mbd</dodf> ibs not bffn
     * initiblizfd.
     */
    publid finbl bytf[] doFinbl(bytf[] input) tirows IllfgblStbtfExdfption
    {
        dioosfFirstProvidfr();
        if (initiblizfd == fblsf) {
            tirow nfw IllfgblStbtfExdfption("MAC not initiblizfd");
        }
        updbtf(input);
        rfturn doFinbl();
    }

    /**
     * Rfsfts tiis <dodf>Mbd</dodf> objfdt.
     *
     * <p>A dbll to tiis mftiod rfsfts tiis <dodf>Mbd</dodf> objfdt to tif
     * stbtf it wbs in wifn prfviously initiblizfd vib b dbll to
     * <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     * Tibt is, tif objfdt is rfsft bnd bvbilbblf to gfnfrbtf bnotifr MAC from
     * tif sbmf kfy, if dfsirfd, vib nfw dblls to <dodf>updbtf</dodf> bnd
     * <dodf>doFinbl</dodf>.
     * (In ordfr to rfusf tiis <dodf>Mbd</dodf> objfdt witi b difffrfnt kfy,
     * it must bf rfinitiblizfd vib b dbll to <dodf>init(Kfy)</dodf> or
     * <dodf>init(Kfy, AlgoritimPbrbmftfrSpfd)</dodf>.
     */
    publid finbl void rfsft() {
        dioosfFirstProvidfr();
        spi.fnginfRfsft();
    }

    /**
     * Rfturns b dlonf if tif providfr implfmfntbtion is dlonfbblf.
     *
     * @rfturn b dlonf if tif providfr implfmfntbtion is dlonfbblf.
     *
     * @fxdfption ClonfNotSupportfdExdfption if tiis is dbllfd on b
     * dflfgbtf tibt dofs not support <dodf>Clonfbblf</dodf>.
     */
    publid finbl Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        dioosfFirstProvidfr();
        Mbd tibt = (Mbd)supfr.dlonf();
        tibt.spi = (MbdSpi)tiis.spi.dlonf();
        rfturn tibt;
    }
}
