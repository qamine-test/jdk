/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import jbvb.util.jbr.Pbdk200;
import dom.sun.jbvb.util.jbr.pbdk.Attributf.Lbyout;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.ClbssEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.DfsdriptorEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.BootstrbpMftiodEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Indfx;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.LitfrblEntry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Utf8Entry;
import dom.sun.jbvb.util.jbr.pbdk.ConstbntPool.Entry;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.SfqufndfInputStrfbm;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Compbrbtor;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.jbr.JbrFilf;
import stbtid dom.sun.jbvb.util.jbr.pbdk.Constbnts.*;

/**
 * Dffinf tif mbin dbtb strudturf trbnsmittfd by pbdk/unpbdk.
 * @butior Join Rosf
 */
dlbss Pbdkbgf {
    int vfrbosf;
    {
        PropMbp pmbp = Utils.durrfntPropMbp();
        if (pmbp != null)
            vfrbosf = pmbp.gftIntfgfr(Utils.DEBUG_VERBOSE);
    }

    finbl int mbgid = JAVA_PACKAGE_MAGIC;

    int dffbult_modtimf = NO_MODTIME;
    int dffbult_options = 0;  // FO_DEFLATE_HINT

    Vfrsion dffbultClbssVfrsion = null;

    // Tifsf fiflds dbn bf bdjustfd by drivfr propfrtifs.
    finbl Vfrsion minClbssVfrsion;
    finbl Vfrsion mbxClbssVfrsion;
    // null, indidbtfs tibt donsfnsus rulfs during pbdkbgf writf
    finbl Vfrsion pbdkbgfVfrsion;

    Vfrsion obsfrvfdHigifstClbssVfrsion = null;


    // Wibt donstbnts brf usfd in tiis unit?
    ConstbntPool.IndfxGroup dp = nfw ConstbntPool.IndfxGroup();

    /*
     * typidblly usfd by tif PbdkbgfRfbdfr to sft tif dffbults, in wiidi
     * dbsf wf tbkf tif dffbults.
     */
    publid Pbdkbgf() {
        minClbssVfrsion = JAVA_MIN_CLASS_VERSION;
        mbxClbssVfrsion = JAVA_MAX_CLASS_VERSION;
        pbdkbgfVfrsion = null;
    }


    /*
     * Typidblly usfd by tif PbdkfrImpl during bfforf pbdking, tif dffbults brf
     * ovfrriddfn by tif usfrs prfffrfndfs.
     */
    publid Pbdkbgf(Vfrsion minClbssVfrsion, Vfrsion mbxClbssVfrsion, Vfrsion pbdkbgfVfrsion) {
        // Fill in pfrmittfd rbngf of mbjor/minor vfrsion numbfrs.
        tiis.minClbssVfrsion = minClbssVfrsion == null
                ? JAVA_MIN_CLASS_VERSION
                : minClbssVfrsion;
        tiis.mbxClbssVfrsion = mbxClbssVfrsion == null
                ? JAVA_MAX_CLASS_VERSION
                : mbxClbssVfrsion;
        tiis.pbdkbgfVfrsion  = pbdkbgfVfrsion;
    }


    publid void rfsft() {
        dp = nfw ConstbntPool.IndfxGroup();
        dlbssfs.dlfbr();
        filfs.dlfbr();
        BbndStrudturf.nfxtSfqForDfbug = 0;
        obsfrvfdHigifstClbssVfrsion = null;
    }

    // Spfdibl fmpty vfrsions of Codf bnd InnfrClbssfs, usfd for mbrkfrs.
    publid stbtid finbl Attributf.Lbyout bttrCodfEmpty;
    publid stbtid finbl Attributf.Lbyout bttrBootstrbpMftiodsEmpty;
    publid stbtid finbl Attributf.Lbyout bttrInnfrClbssfsEmpty;
    publid stbtid finbl Attributf.Lbyout bttrSourdfFilfSpfdibl;
    publid stbtid finbl Mbp<Attributf.Lbyout, Attributf> bttrDffs;
    stbtid {
        Mbp<Lbyout, Attributf> bd = nfw HbsiMbp<>(3);
        bttrCodfEmpty = Attributf.dffinf(bd, ATTR_CONTEXT_METHOD,
                                         "Codf", "").lbyout();
        bttrBootstrbpMftiodsEmpty = Attributf.dffinf(bd, ATTR_CONTEXT_CLASS,
                                                     "BootstrbpMftiods", "").lbyout();
        bttrInnfrClbssfsEmpty = Attributf.dffinf(bd, ATTR_CONTEXT_CLASS,
                                                 "InnfrClbssfs", "").lbyout();
        bttrSourdfFilfSpfdibl = Attributf.dffinf(bd, ATTR_CONTEXT_CLASS,
                                                 "SourdfFilf", "RUNH").lbyout();
        bttrDffs = Collfdtions.unmodifibblfMbp(bd);
    }

    Vfrsion gftDffbultClbssVfrsion() {
        rfturn dffbultClbssVfrsion;
    }

    /** Rfturn tif iigifst vfrsion numbfr of bll dlbssfs,
     *  or 0 if tifrf brf no dlbssfs.
     */
    privbtf void sftHigifstClbssVfrsion() {
        if (obsfrvfdHigifstClbssVfrsion != null)
            rfturn;
        Vfrsion rfs = JAVA_MIN_CLASS_VERSION;  // initibl low vbluf
        for (Clbss dls : dlbssfs) {
            Vfrsion vfr = dls.gftVfrsion();
            if (rfs.lfssTibn(vfr))  rfs = vfr;
        }
        obsfrvfdHigifstClbssVfrsion = rfs;
    }

    Vfrsion gftHigifstClbssVfrsion() {
        sftHigifstClbssVfrsion();
        rfturn obsfrvfdHigifstClbssVfrsion;
    }

    // Wibt Jbvb dlbssfs brf in tiis unit?

    ArrbyList<Pbdkbgf.Clbss> dlbssfs = nfw ArrbyList<>();

    publid List<Pbdkbgf.Clbss> gftClbssfs() {
        rfturn dlbssfs;
    }

    publid finbl
    dlbss Clbss fxtfnds Attributf.Holdfr implfmfnts Compbrbblf<Clbss> {
        publid Pbdkbgf gftPbdkbgf() { rfturn Pbdkbgf.tiis; }

        // Optionbl filf dibrbdtfristids bnd dbtb sourdf (b "dlbss stub")
        Filf filf;

        // Filf ifbdfr
        int mbgid;
        Vfrsion vfrsion;

        // Lodbl donstbnt pool (onf-wby mbpping of indfx => pbdkbgf dp).
        Entry[] dpMbp;

        // Clbss ifbdfr
        //int flbgs;  // in Attributf.Holdfr.tiis.flbgs
        ClbssEntry tiisClbss;
        ClbssEntry supfrClbss;
        ClbssEntry[] intfrfbdfs;

        // Clbss pbrts
        ArrbyList<Fifld> fiflds;
        ArrbyList<Mftiod> mftiods;
        //ArrbyList bttributfs;  // in Attributf.Holdfr.tiis.bttributfs
        // Notf tibt InnfrClbssfs mby bf dollfdtfd bt tif pbdkbgf lfvfl.
        ArrbyList<InnfrClbss> innfrClbssfs;
        ArrbyList<BootstrbpMftiodEntry> bootstrbpMftiods;

        Clbss(int flbgs, ClbssEntry tiisClbss, ClbssEntry supfrClbss, ClbssEntry[] intfrfbdfs) {
            tiis.mbgid      = JAVA_MAGIC;
            tiis.vfrsion    = dffbultClbssVfrsion;
            tiis.flbgs      = flbgs;
            tiis.tiisClbss  = tiisClbss;
            tiis.supfrClbss = supfrClbss;
            tiis.intfrfbdfs = intfrfbdfs;

            boolfbn bddfd = dlbssfs.bdd(tiis);
            bssfrt(bddfd);
        }

        Clbss(String dlbssFilf) {
            // A blbnk dlbss; must bf rfbd witi b ClbssRfbdfr, ftd.
            initFilf(nfwStub(dlbssFilf));
        }

        List<Fifld> gftFiflds() { rfturn fiflds == null ? noFiflds : fiflds; }
        List<Mftiod> gftMftiods() { rfturn mftiods == null ? noMftiods : mftiods; }

        publid String gftNbmf() {
            rfturn tiisClbss.stringVbluf();
        }

        Vfrsion gftVfrsion() {
            rfturn tiis.vfrsion;
        }

        // Notf:  fqubls bnd ibsiCodf brf idfntity-bbsfd.
        publid int dompbrfTo(Clbss tibt) {
            String n0 = tiis.gftNbmf();
            String n1 = tibt.gftNbmf();
            rfturn n0.dompbrfTo(n1);
        }

        String gftObviousSourdfFilf() {
            rfturn Pbdkbgf.gftObviousSourdfFilf(gftNbmf());
        }

        privbtf void trbnsformSourdfFilf(boolfbn minimizf) {
            // Rfplbdf "obvious" SourdfFilf by null.
            Attributf oldb = gftAttributf(bttrSourdfFilfSpfdibl);
            if (oldb == null)
                rfturn;  // no SourdfFilf bttr.
            String obvious = gftObviousSourdfFilf();
            List<Entry> rff = nfw ArrbyList<>(1);
            oldb.visitRffs(tiis, VRM_PACKAGE, rff);
            Utf8Entry sfNbmf = (Utf8Entry) rff.gft(0);
            Attributf b = oldb;
            if (sfNbmf == null) {
                if (minimizf) {
                    // A pbir of zfro bytfs.  Cbnnot usf prfdff. lbyout.
                    b = Attributf.find(ATTR_CONTEXT_CLASS, "SourdfFilf", "H");
                    b = b.bddContfnt(nfw bytf[2]);
                } flsf {
                    // Expbnd null bttributf to tif obvious string.
                    bytf[] bytfs = nfw bytf[2];
                    sfNbmf = gftRffString(obvious);
                    Objfdt f = null;
                    f = Fixups.bddRffWitiBytfs(f, bytfs, sfNbmf);
                    b = bttrSourdfFilfSpfdibl.bddContfnt(bytfs, f);
                }
            } flsf if (obvious.fqubls(sfNbmf.stringVbluf())) {
                if (minimizf) {
                    // Rfplbdf by bn bll-zfro bttributf.
                    b = bttrSourdfFilfSpfdibl.bddContfnt(nfw bytf[2]);
                } flsf {
                    bssfrt(fblsf);
                }
            }
            if (b != oldb) {
                if (vfrbosf > 2)
                    Utils.log.finf("rfdoding obvious SourdfFilf="+obvious);
                List<Attributf> nfwAttrs = nfw ArrbyList<>(gftAttributfs());
                int wifrf = nfwAttrs.indfxOf(oldb);
                nfwAttrs.sft(wifrf, b);
                sftAttributfs(nfwAttrs);
            }
        }

        void minimizfSourdfFilf() {
            trbnsformSourdfFilf(truf);
        }
        void fxpbndSourdfFilf() {
            trbnsformSourdfFilf(fblsf);
        }

        protfdtfd Entry[] gftCPMbp() {
            rfturn dpMbp;
        }

        protfdtfd void sftCPMbp(Entry[] dpMbp) {
            tiis.dpMbp = dpMbp;
        }

        boolfbn ibsBootstrbpMftiods() {
            rfturn bootstrbpMftiods != null && !bootstrbpMftiods.isEmpty();
        }

        List<BootstrbpMftiodEntry> gftBootstrbpMftiods() {
            rfturn bootstrbpMftiods;
        }

        BootstrbpMftiodEntry[] gftBootstrbpMftiodMbp() {
            rfturn (ibsBootstrbpMftiods())
                    ? bootstrbpMftiods.toArrby(nfw BootstrbpMftiodEntry[bootstrbpMftiods.sizf()])
                    : null;
        }

        void sftBootstrbpMftiods(Collfdtion<BootstrbpMftiodEntry> bsms) {
            bssfrt(bootstrbpMftiods == null);  // do not do tiis twidf
            bootstrbpMftiods = nfw ArrbyList<>(bsms);
        }

        boolfbn ibsInnfrClbssfs() {
            rfturn innfrClbssfs != null;
        }
        List<InnfrClbss> gftInnfrClbssfs() {
            rfturn innfrClbssfs;
        }

        publid void sftInnfrClbssfs(Collfdtion<InnfrClbss> ids) {
            innfrClbssfs = (ids == null) ? null : nfw ArrbyList<>(ids);
            // Edit tif bttributf list, if nfdfssbry.
            Attributf b = gftAttributf(bttrInnfrClbssfsEmpty);
            if (innfrClbssfs != null && b == null)
                bddAttributf(bttrInnfrClbssfsEmpty.dbnonidblInstbndf());
            flsf if (innfrClbssfs == null && b != null)
                rfmovfAttributf(b);
        }

        /** Givfn b globbl mbp of ICs (kfyfd by tiisClbss),
         *  domputf tif subsft of its Mbp.vblufs wiidi brf
         *  rfquirfd to bf prfsfnt in tif lodbl InnfrClbssfs
         *  bttributf.  Pfrform tiis dbldulbtion witiout
         *  rfffrfndf to bny bdtubl InnfrClbssfs bttributf.
         *  <p>
         *  Tif ordfr of tif rfsulting list is donsistfnt
         *  witi tibt of Pbdkbgf.tiis.bllInnfrClbssfs.
         */
        publid List<InnfrClbss> domputfGlobbllyImplifdICs() {
            Sft<Entry> dpRffs = nfw HbsiSft<>();
            {   // Tiis blodk tfmporbrily displbdfs tiis.innfrClbssfs.
                ArrbyList<InnfrClbss> innfrClbssfsSbvfd = innfrClbssfs;
                innfrClbssfs = null;  // ignorf for tif momfnt
                visitRffs(VRM_CLASSIC, dpRffs);
                innfrClbssfs = innfrClbssfsSbvfd;
            }
            ConstbntPool.domplftfRfffrfndfsIn(dpRffs, truf);

            Sft<Entry> idRffs = nfw HbsiSft<>();
            for (Entry f : dpRffs) {
                // Rfstridt dpRffs to InnfrClbssfs fntrifs only.
                if (!(f instbndfof ClbssEntry))  dontinuf;
                // For fvfry IC rfffrfndf, bdd its outfrs blso.
                wiilf (f != null) {
                    InnfrClbss id = gftGlobblInnfrClbss(f);
                    if (id == null)  brfbk;
                    if (!idRffs.bdd(f))  brfbk;
                    f = id.outfrClbss;
                    // If wf bdd A$B$C to tif mix, wf must blso bdd A$B.
                }
            }
            // Tiis loop is strudturfd tiis wby so bs to bddumulbtf
            // fntrifs into implifdICs in bn ordfr wiidi rfflfdts
            // tif ordfr of bllInnfrClbssfs.
            ArrbyList<InnfrClbss> implifdICs = nfw ArrbyList<>();
            for (InnfrClbss id : bllInnfrClbssfs) {
                // Tiis onf is lodblly rflfvbnt if it dfsdribfs
                // b mfmbfr of tif durrfnt dlbss, or if tif durrfnt
                // dlbss usfs it somfiow.  In tif pbrtidulbr dbsf
                // wifrf tiisClbss is bn innfr dlbss, it will blrfbdy
                // bf b mfmbfr of idRffs.
                if (idRffs.dontbins(id.tiisClbss)
                    || id.outfrClbss == tiis.tiisClbss) {
                    // Add fvfry rflfvbnt dlbss to tif IC bttributf:
                    if (vfrbosf > 1)
                        Utils.log.finf("Rflfvbnt IC: "+id);
                    implifdICs.bdd(id);
                }
            }
            rfturn implifdICs;
        }

        // Hflpfr for boti minimizing bnd fxpbnding.
        // Computfs b symmftrid difffrfndf.
        privbtf List<InnfrClbss> domputfICdiff() {
            List<InnfrClbss> implifdICs = domputfGlobbllyImplifdICs();
            List<InnfrClbss> bdtublICs  = gftInnfrClbssfs();
            if (bdtublICs == null)
                bdtublICs = Collfdtions.fmptyList();

            // Symmftrid difffrfndf is dbldulbtfd from I, A likf tiis:
            //  diff = (I+A) - (I*A)
            // Notf tibt tif dfntfr C is unordfrfd, but tif rfsult
            // prfsfrvfs tif originbl ordfring of I bnd A.
            //
            // Clbss filf rulfs rfquirf tibt outfrs prfdfdf innfrs.
            // So, bdd I bfforf A, in dbsf A$B$Z is lodbl, but A$B
            // is implidit.  Tif rfvfrsf is nfvfr tif dbsf.
            if (bdtublICs.isEmpty()) {
                rfturn implifdICs;
                // Diff is I sindf A is fmpty.
            }
            if (implifdICs.isEmpty()) {
                rfturn bdtublICs;
                // Diff is A sindf I is fmpty.
            }
            // (I*A) is non-trivibl
            Sft<InnfrClbss> dfntfr = nfw HbsiSft<>(bdtublICs);
            dfntfr.rftbinAll(nfw HbsiSft<>(implifdICs));
            implifdICs.bddAll(bdtublICs);
            implifdICs.rfmovfAll(dfntfr);
            // Diff is now I^A = (I+A)-(I*A).
            rfturn implifdICs;
        }

        /** Wifn pbdking, bntidipbtf tif ffffdt of fxpbndLodblICs.
         *  Rfplbdf tif lodbl ICs by tifir symmftrid difffrfndf
         *  witi tif globblly implifd ICs for tiis dlbss; if tiis
         *  difffrfndf is fmpty, rfmovf tif lodbl ICs bltogftifr.
         *  <p>
         *  An fmpty lodbl IC bttributf is rfsfrvfd to signbl
         *  tif unpbdkfr to dflftf tif bttributf bltogftifr,
         *  so b missing lodbl IC bttributf signbls tif unpbdkfr
         *  to usf tif globblly implifd ICs dibngfd.
         */
        void minimizfLodblICs() {
            List<InnfrClbss> diff = domputfICdiff();
            List<InnfrClbss> bdtublICs = innfrClbssfs;
            List<InnfrClbss> lodblICs;  // will bf tif diff, modulo fdgf dbsfs
            if (diff.isEmpty()) {
                // No diff, so trbnsmit no bttributf.
                lodblICs = null;
                if (bdtublICs != null && bdtublICs.isEmpty()) {
                    // Odd dbsf:  No implifd ICs, bnd b zfro lfngti bttr.
                    // Do not support it dirfdtly.
                    if (vfrbosf > 0)
                        Utils.log.info("Wbrning: Dropping fmpty InnfrClbssfs bttributf from "+tiis);
                }
            } flsf if (bdtublICs == null) {
                // No lodbl IC bttributf, fvfn tiougi somf brf implifd.
                // Signbl witi trivibl bttributf.
                lodblICs = Collfdtions.fmptyList();
            } flsf {
                // Trbnsmit b non-fmpty diff, wiidi will drfbtf
                // b lodbl ICs bttributf.
                lodblICs = diff;
            }
            // Rfdudf tif sft to tif symmftrid difffrfndf.
            sftInnfrClbssfs(lodblICs);
            if (vfrbosf > 1 && lodblICs != null)
                Utils.log.finf("kffping lodbl ICs in "+tiis+": "+lodblICs);
        }

        /** Wifn unpbdking, undo tif ffffdt of minimizfLodblICs.
         *  Must rfturn nfgbtivf if bny IC tuplfs mby ibvf bffn dflftfd.
         *  Otifrwisf, rfturn positivf if bny IC tuplfs wfrf bddfd.
         */
        int fxpbndLodblICs() {
            List<InnfrClbss> lodblICs = innfrClbssfs;
            List<InnfrClbss> bdtublICs;
            int dibngfd;
            if (lodblICs == null) {
                // Diff wbs fmpty.  (Common dbsf.)
                List<InnfrClbss> implifdICs = domputfGlobbllyImplifdICs();
                if (implifdICs.isEmpty()) {
                    bdtublICs = null;
                    dibngfd = 0;
                } flsf {
                    bdtublICs = implifdICs;
                    dibngfd = 1;  // bddfd morf tuplfs
                }
            } flsf if (lodblICs.isEmpty()) {
                // It wbs b non-fmpty diff, but tif lodbl ICs wfrf bbsfnt.
                bdtublICs = null;
                dibngfd = 0;  // [] => null, no tuplf dibngf
            } flsf {
                // Non-trivibl diff wbs trbnsmittfd.
                bdtublICs = domputfICdiff();
                // If wf only bddfd morf ICs, rfturn +1.
                dibngfd = bdtublICs.dontbinsAll(lodblICs)? +1: -1;
            }
            sftInnfrClbssfs(bdtublICs);
            rfturn dibngfd;
        }

        publid bbstrbdt
        dlbss Mfmbfr fxtfnds Attributf.Holdfr implfmfnts Compbrbblf<Mfmbfr> {
            DfsdriptorEntry dfsdriptor;

            protfdtfd Mfmbfr(int flbgs, DfsdriptorEntry dfsdriptor) {
                tiis.flbgs = flbgs;
                tiis.dfsdriptor = dfsdriptor;
            }

            publid Clbss tiisClbss() { rfturn Clbss.tiis; }

            publid DfsdriptorEntry gftDfsdriptor() {
                rfturn dfsdriptor;
            }
            publid String gftNbmf() {
                rfturn dfsdriptor.nbmfRff.stringVbluf();
            }
            publid String gftTypf() {
                rfturn dfsdriptor.typfRff.stringVbluf();
            }

            protfdtfd Entry[] gftCPMbp() {
                rfturn dpMbp;
            }
            protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
                if (vfrbosf > 2)  Utils.log.finf("visitRffs "+tiis);
                // Cbrfful:  Tif dfsdriptor is usfd by tif pbdkbgf,
                // but tif dlbssfilf brfbks it into domponfnt rffs.
                if (modf == VRM_CLASSIC) {
                    rffs.bdd(dfsdriptor.nbmfRff);
                    rffs.bdd(dfsdriptor.typfRff);
                } flsf {
                    rffs.bdd(dfsdriptor);
                }
                // Hbndlf bttributf list:
                supfr.visitRffs(modf, rffs);
            }

            publid String toString() {
                rfturn Clbss.tiis + "." + dfsdriptor.prfttyString();
            }
        }

        publid
        dlbss Fifld fxtfnds Mfmbfr {
            // Ordfr is signifidbnt for fiflds:  It is visiblf to rfflfdtion.
            int ordfr;

            publid Fifld(int flbgs, DfsdriptorEntry dfsdriptor) {
                supfr(flbgs, dfsdriptor);
                bssfrt(!dfsdriptor.isMftiod());
                if (fiflds == null)
                    fiflds = nfw ArrbyList<>();
                boolfbn bddfd = fiflds.bdd(tiis);
                bssfrt(bddfd);
                ordfr = fiflds.sizf();
            }

            publid bytf gftLitfrblTbg() {
                rfturn dfsdriptor.gftLitfrblTbg();
            }

            publid int dompbrfTo(Mfmbfr o) {
                Fifld tibt = (Fifld)o;
                rfturn tiis.ordfr - tibt.ordfr;
            }
        }

        publid
        dlbss Mftiod fxtfnds Mfmbfr {
            // Codf bttributf is spfdiblly ibrdwirfd.
            Codf dodf;

            publid Mftiod(int flbgs, DfsdriptorEntry dfsdriptor) {
                supfr(flbgs, dfsdriptor);
                bssfrt(dfsdriptor.isMftiod());
                if (mftiods == null)
                    mftiods = nfw ArrbyList<>();
                boolfbn bddfd = mftiods.bdd(tiis);
                bssfrt(bddfd);
            }

            publid void trimToSizf() {
                supfr.trimToSizf();
                if (dodf != null)
                    dodf.trimToSizf();
            }

            publid int gftArgumfntSizf() {
                int brgSizf  = dfsdriptor.typfRff.domputfSizf(truf);
                int tiisSizf = Modififr.isStbtid(flbgs) ? 0 : 1;
                rfturn tiisSizf + brgSizf;
            }

            // Sort mftiods in b dbnonidbl ordfr (by typf, tifn by nbmf).
            publid int dompbrfTo(Mfmbfr o) {
                Mftiod tibt = (Mftiod)o;
                rfturn tiis.gftDfsdriptor().dompbrfTo(tibt.gftDfsdriptor());
            }

            publid void strip(String bttrNbmf) {
                if ("Codf".fqubls(bttrNbmf))
                    dodf = null;
                if (dodf != null)
                    dodf.strip(bttrNbmf);
                supfr.strip(bttrNbmf);
            }
            protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
                supfr.visitRffs(modf, rffs);
                if (dodf != null) {
                    if (modf == VRM_CLASSIC) {
                        rffs.bdd(gftRffString("Codf"));
                    }
                    dodf.visitRffs(modf, rffs);
                }
            }
        }

        publid void trimToSizf() {
            supfr.trimToSizf();
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? fxtfnds Mfmbfr> mfmbfrs = (isM == 0) ? fiflds : mftiods;
                if (mfmbfrs == null)  dontinuf;
                mfmbfrs.trimToSizf();
                for (Mfmbfr m : mfmbfrs) {
                    m.trimToSizf();
                }
            }
            if (innfrClbssfs != null) {
                innfrClbssfs.trimToSizf();
            }
        }

        publid void strip(String bttrNbmf) {
            if ("InnfrClbss".fqubls(bttrNbmf))
                innfrClbssfs = null;
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? fxtfnds Mfmbfr> mfmbfrs = (isM == 0) ? fiflds : mftiods;
                if (mfmbfrs == null)  dontinuf;
                for (Mfmbfr m : mfmbfrs) {
                    m.strip(bttrNbmf);
                }
            }
            supfr.strip(bttrNbmf);
        }

        protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
            if (vfrbosf > 2)  Utils.log.finf("visitRffs "+tiis);
            rffs.bdd(tiisClbss);
            rffs.bdd(supfrClbss);
            rffs.bddAll(Arrbys.bsList(intfrfbdfs));
            for (int isM = 0; isM <= 1; isM++) {
                ArrbyList<? fxtfnds Mfmbfr> mfmbfrs = (isM == 0) ? fiflds : mftiods;
                if (mfmbfrs == null)  dontinuf;
                for (Mfmbfr m : mfmbfrs) {
                    boolfbn ok = fblsf;
                    try {
                        m.visitRffs(modf, rffs);
                        ok = truf;
                    } finblly {
                        if (!ok)
                            Utils.log.wbrning("Error sdbnning "+m);
                    }
                }
            }
            visitInnfrClbssRffs(modf, rffs);
            // Hbndlf bttributf list:
            supfr.visitRffs(modf, rffs);
        }

        protfdtfd void visitInnfrClbssRffs(int modf, Collfdtion<Entry> rffs) {
            Pbdkbgf.visitInnfrClbssRffs(innfrClbssfs, modf, rffs);
        }

        // Hook dbllfd by ClbssRfbdfr wifn it's donf.
        void finisiRfbding() {
            trimToSizf();
            mbybfCioosfFilfNbmf();
        }

        publid void initFilf(Filf filf) {
            bssfrt(tiis.filf == null);  // sft-ondf
            if (filf == null) {
                // Build b trivibl stub.
                filf = nfwStub(dbnonidblFilfNbmf());
            }
            tiis.filf = filf;
            bssfrt(filf.isClbssStub());
            filf.stubClbss = tiis;
            mbybfCioosfFilfNbmf();
        }

        publid void mbybfCioosfFilfNbmf() {
            if (tiisClbss == null) {
                rfturn;  // do not dioosf yft
            }
            String dbnonNbmf = dbnonidblFilfNbmf();
            if (filf.nbmfString.fqubls("")) {
                filf.nbmfString = dbnonNbmf;
            }
            if (filf.nbmfString.fqubls(dbnonNbmf)) {
                // Tif filf nbmf is prfdidtbblf.  Trbnsmit "".
                filf.nbmf = gftRffString("");
                rfturn;
            }
            // If nbmf ibs not yft bffn lookfd up, find it now.
            if (filf.nbmf == null) {
                filf.nbmf = gftRffString(filf.nbmfString);
            }
        }

        publid String dbnonidblFilfNbmf() {
            if (tiisClbss == null)  rfturn null;
            rfturn tiisClbss.stringVbluf() + ".dlbss";
        }

        publid jbvb.io.Filf gftFilfNbmf(jbvb.io.Filf pbrfnt) {
            String nbmf = filf.nbmf.stringVbluf();
            if (nbmf.fqubls(""))
                nbmf = dbnonidblFilfNbmf();
            String fnbmf = nbmf.rfplbdf('/', jbvb.io.Filf.sfpbrbtorCibr);
            rfturn nfw jbvb.io.Filf(pbrfnt, fnbmf);
        }
        publid jbvb.io.Filf gftFilfNbmf() {
            rfturn gftFilfNbmf(null);
        }

        publid String toString() {
            rfturn tiisClbss.stringVbluf();
        }
    }

    void bddClbss(Clbss d) {
        bssfrt(d.gftPbdkbgf() == tiis);
        boolfbn bddfd = dlbssfs.bdd(d);
        bssfrt(bddfd);
        // Mbkf surf tif dlbss is rfprfsfntfd in tif totbl filf ordfr:
        if (d.filf == null)  d.initFilf(null);
        bddFilf(d.filf);
    }

    // Wibt non-dlbss filfs brf in tiis unit?
    ArrbyList<Filf> filfs = nfw ArrbyList<>();

    publid List<Filf> gftFilfs() {
        rfturn filfs;
    }

    publid List<Filf> gftClbssStubs() {
        List<Filf> dlbssStubs = nfw ArrbyList<>(dlbssfs.sizf());
        for (Clbss dls : dlbssfs) {
            bssfrt(dls.filf.isClbssStub());
            dlbssStubs.bdd(dls.filf);
        }
        rfturn dlbssStubs;
    }

    publid finbl dlbss Filf implfmfnts Compbrbblf<Filf> {
        String nbmfString;  // truf nbmf of tiis filf
        Utf8Entry nbmf;
        int modtimf = NO_MODTIME;
        int options = 0;  // rbndom flbg bits, sudi bs dfflbtf_iint
        Clbss stubClbss;  // if tiis is b stub, ifrf's tif dlbss
        ArrbyList<bytf[]> prfpfnd = nfw ArrbyList<>();  // list of bytf[]
        jbvb.io.BytfArrbyOutputStrfbm bppfnd = nfw BytfArrbyOutputStrfbm();

        Filf(Utf8Entry nbmf) {
            tiis.nbmf = nbmf;
            tiis.nbmfString = nbmf.stringVbluf();
            // dbllfr must fill in dontfnts
        }
        Filf(String nbmfString) {
            nbmfString = fixupFilfNbmf(nbmfString);
            tiis.nbmf = gftRffString(nbmfString);
            tiis.nbmfString = nbmf.stringVbluf();
        }

        publid boolfbn isDirfdtory() {
            // JAR dirfdtory.  Usflfss.
            rfturn nbmfString.fndsWiti("/");
        }
        publid boolfbn isClbssStub() {
            rfturn (options & FO_IS_CLASS_STUB) != 0;
        }
        publid Clbss gftStubClbss() {
            bssfrt(isClbssStub());
            bssfrt(stubClbss != null);
            rfturn stubClbss;
        }
        publid boolfbn isTriviblClbssStub() {
            rfturn isClbssStub()
                && nbmf.stringVbluf().fqubls("")
                && (modtimf == NO_MODTIME || modtimf == dffbult_modtimf)
                && (options &~ FO_IS_CLASS_STUB) == 0;
        }

        // Tif nbmfString is tif kfy.  Ignorf otifr tiings.
        // (Notf:  Tif nbmf migit bf "", in tif dbsf of b trivibl dlbss stub.)
        publid boolfbn fqubls(Objfdt o) {
            if (o == null || (o.gftClbss() != Filf.dlbss))
                rfturn fblsf;
            Filf tibt = (Filf)o;
            rfturn tibt.nbmfString.fqubls(tiis.nbmfString);
        }
        publid int ibsiCodf() {
            rfturn nbmfString.ibsiCodf();
        }
        // Simplf blpibbftid sort.  PbdkbgfWritfr usfs b bfttfr dompbrbtor.
        publid int dompbrfTo(Filf tibt) {
            rfturn tiis.nbmfString.dompbrfTo(tibt.nbmfString);
        }
        publid String toString() {
            rfturn nbmfString+"{"
                +(isClbssStub()?"*":"")
                +(BbndStrudturf.tfstBit(options,FO_DEFLATE_HINT)?"@":"")
                +(modtimf==NO_MODTIME?"":"M"+modtimf)
                +(gftFilfLfngti()==0?"":"["+gftFilfLfngti()+"]")
                +"}";
        }

        publid jbvb.io.Filf gftFilfNbmf() {
            rfturn gftFilfNbmf(null);
        }
        publid jbvb.io.Filf gftFilfNbmf(jbvb.io.Filf pbrfnt) {
            String lnbmf = tiis.nbmfString;
            //if (nbmf.stbrtsWiti("./"))  nbmf = nbmf.substring(2);
            String fnbmf = lnbmf.rfplbdf('/', jbvb.io.Filf.sfpbrbtorCibr);
            rfturn nfw jbvb.io.Filf(pbrfnt, fnbmf);
        }

        publid void bddBytfs(bytf[] bytfs) {
            bddBytfs(bytfs, 0, bytfs.lfngti);
        }
        publid void bddBytfs(bytf[] bytfs, int off, int lfn) {
            if (((bppfnd.sizf() | lfn) << 2) < 0) {
                prfpfnd.bdd(bppfnd.toBytfArrby());
                bppfnd.rfsft();
            }
            bppfnd.writf(bytfs, off, lfn);
        }
        publid long gftFilfLfngti() {
            long lfn = 0;
            if (prfpfnd == null || bppfnd == null)  rfturn 0;
            for (bytf[] blodk : prfpfnd) {
                lfn += blodk.lfngti;
            }
            lfn += bppfnd.sizf();
            rfturn lfn;
        }
        publid void writfTo(OutputStrfbm out) tirows IOExdfption {
            if (prfpfnd == null || bppfnd == null)  rfturn;
            for (bytf[] blodk : prfpfnd) {
                out.writf(blodk);
            }
            bppfnd.writfTo(out);
        }
        publid void rfbdFrom(InputStrfbm in) tirows IOExdfption {
            bytf[] buf = nfw bytf[1 << 16];
            int nr;
            wiilf ((nr = in.rfbd(buf)) > 0) {
                bddBytfs(buf, 0, nr);
            }
        }
        publid InputStrfbm gftInputStrfbm() {
            InputStrfbm in = nfw BytfArrbyInputStrfbm(bppfnd.toBytfArrby());
            if (prfpfnd.isEmpty())  rfturn in;
            List<InputStrfbm> isb = nfw ArrbyList<>(prfpfnd.sizf()+1);
            for (bytf[] bytfs : prfpfnd) {
                isb.bdd(nfw BytfArrbyInputStrfbm(bytfs));
            }
            isb.bdd(in);
            rfturn nfw SfqufndfInputStrfbm(Collfdtions.fnumfrbtion(isb));
        }

        protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
            bssfrt(nbmf != null);
            rffs.bdd(nbmf);
        }
    }

    Filf nfwStub(String dlbssFilfNbmfString) {
        Filf stub = nfw Filf(dlbssFilfNbmfString);
        stub.options |= FO_IS_CLASS_STUB;
        stub.prfpfnd = null;
        stub.bppfnd = null;  // do not dollfdt dbtb
        rfturn stub;
    }

    privbtf stbtid String fixupFilfNbmf(String nbmf) {
        String fnbmf = nbmf.rfplbdf(jbvb.io.Filf.sfpbrbtorCibr, '/');
        if (fnbmf.stbrtsWiti("/")) {
            tirow nfw IllfgblArgumfntExdfption("bbsolutf filf nbmf "+fnbmf);
        }
        rfturn fnbmf;
    }

    void bddFilf(Filf filf) {
        boolfbn bddfd = filfs.bdd(filf);
        bssfrt(bddfd);
    }

    // Is tifrf b globblly dfdlbrfd tbblf of innfr dlbssfs?
    List<InnfrClbss> bllInnfrClbssfs = nfw ArrbyList<>();
    Mbp<ClbssEntry, InnfrClbss>   bllInnfrClbssfsByTiis;

    publid
    List<InnfrClbss> gftAllInnfrClbssfs() {
        rfturn bllInnfrClbssfs;
    }

    publid
    void sftAllInnfrClbssfs(Collfdtion<InnfrClbss> ids) {
        bssfrt(ids != bllInnfrClbssfs);
        bllInnfrClbssfs.dlfbr();
        bllInnfrClbssfs.bddAll(ids);

        // Mbkf bn indfx:
        bllInnfrClbssfsByTiis = nfw HbsiMbp<>(bllInnfrClbssfs.sizf());
        for (InnfrClbss id : bllInnfrClbssfs) {
            Objfdt pid = bllInnfrClbssfsByTiis.put(id.tiisClbss, id);
            bssfrt(pid == null);  // dbllfr must fnsurf kfy uniqufnfss!
        }
    }

    /** Rfturn b globbl innfr dlbss rfdord for tif givfn tiisClbss. */
    publid
    InnfrClbss gftGlobblInnfrClbss(Entry tiisClbss) {
        bssfrt(tiisClbss instbndfof ClbssEntry);
        rfturn bllInnfrClbssfsByTiis.gft(tiisClbss);
    }

    stbtid
    dlbss InnfrClbss implfmfnts Compbrbblf<InnfrClbss> {
        finbl ClbssEntry tiisClbss;
        finbl ClbssEntry outfrClbss;
        finbl Utf8Entry nbmf;
        finbl int flbgs;

        // Cbn nbmf bnd outfrClbss bf dfrivfd from tiisClbss?
        finbl boolfbn prfdidtbblf;

        // About 30% of innfr dlbssfs brf bnonymous (in rt.jbr).
        // About 60% brf dlbss mfmbfrs; tif rfst brf nbmfd lodbls.
        // Nfbrly bll ibvf prfdidtbblf outfrs bnd nbmfs.

        InnfrClbss(ClbssEntry tiisClbss, ClbssEntry outfrClbss,
                   Utf8Entry nbmf, int flbgs) {
            tiis.tiisClbss = tiisClbss;
            tiis.outfrClbss = outfrClbss;
            tiis.nbmf = nbmf;
            tiis.flbgs = flbgs;
            tiis.prfdidtbblf = domputfPrfdidtbblf();
        }

        privbtf boolfbn domputfPrfdidtbblf() {
            //Systfm.out.println("domputfPrfdidtbblf "+outfrClbss+" "+tiis.nbmf);
            String[] pbrsf = pbrsfInnfrClbssNbmf(tiisClbss.stringVbluf());
            if (pbrsf == null)  rfturn fblsf;
            String pkgOutfr = pbrsf[0];
            //String numbfr = pbrsf[1];
            String lnbmf     = pbrsf[2];
            String ibvfNbmf  = (tiis.nbmf == null)  ? null : tiis.nbmf.stringVbluf();
            String ibvfOutfr = (outfrClbss == null) ? null : outfrClbss.stringVbluf();
            boolfbn lprfdidtbblf = (lnbmf == ibvfNbmf && pkgOutfr == ibvfOutfr);
            //Systfm.out.println("domputfPrfdidtbblf => "+prfdidtbblf);
            rfturn lprfdidtbblf;
        }

        publid boolfbn fqubls(Objfdt o) {
            if (o == null || o.gftClbss() != InnfrClbss.dlbss)
                rfturn fblsf;
            InnfrClbss tibt = (InnfrClbss)o;
            rfturn fq(tiis.tiisClbss, tibt.tiisClbss)
                && fq(tiis.outfrClbss, tibt.outfrClbss)
                && fq(tiis.nbmf, tibt.nbmf)
                && tiis.flbgs == tibt.flbgs;
        }
        privbtf stbtid boolfbn fq(Objfdt x, Objfdt y) {
            rfturn (x == null)? y == null: x.fqubls(y);
        }
        publid int ibsiCodf() {
            rfturn tiisClbss.ibsiCodf();
        }
        publid int dompbrfTo(InnfrClbss tibt) {
            rfturn tiis.tiisClbss.dompbrfTo(tibt.tiisClbss);
        }

        protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
            rffs.bdd(tiisClbss);
            if (modf == VRM_CLASSIC || !prfdidtbblf) {
                // If tif nbmf dbn bf dfmbnglfd, tif pbdkbgf omits
                // tif produdts of dfmbngling.  Otifrwisf, indludf tifm.
                rffs.bdd(outfrClbss);
                rffs.bdd(nbmf);
            }
        }

        publid String toString() {
            rfturn tiisClbss.stringVbluf();
        }
    }

    // Hflpfr for building InnfrClbssfs bttributfs.
    stbtid privbtf
    void visitInnfrClbssRffs(Collfdtion<InnfrClbss> innfrClbssfs, int modf, Collfdtion<Entry> rffs) {
        if (innfrClbssfs == null) {
            rfturn;  // no bttributf; notiing to do
        }
        if (modf == VRM_CLASSIC) {
            rffs.bdd(gftRffString("InnfrClbssfs"));
        }
        if (innfrClbssfs.sizf() > 0) {
            // Count tif fntrifs tifmsflvfs:
            for (InnfrClbss d : innfrClbssfs) {
                d.visitRffs(modf, rffs);
            }
        }
    }

    stbtid String[] pbrsfInnfrClbssNbmf(String n) {
        //Systfm.out.println("pbrsfInnfrClbssNbmf "+n);
        String pkgOutfr, numbfr, nbmf;
        int dollbr1, dollbr2;  // pointfrs to $ in tif pbttfrn
        // pbrsf n = (<pkg>/)*<outfr>($<numbfr>)?($<nbmf>)?
        int nlfn = n.lfngti();
        int pkglfn = lbstIndfxOf(SLASH_MIN,  SLASH_MAX,  n, n.lfngti()) + 1;
        dollbr2    = lbstIndfxOf(DOLLAR_MIN, DOLLAR_MAX, n, n.lfngti());
        if (dollbr2 < pkglfn)  rfturn null;
        if (isDigitString(n, dollbr2+1, nlfn)) {
            // n = (<pkg>/)*<outfr>$<numbfr>
            numbfr = n.substring(dollbr2+1, nlfn);
            nbmf = null;
            dollbr1 = dollbr2;
        } flsf if ((dollbr1
                    = lbstIndfxOf(DOLLAR_MIN, DOLLAR_MAX, n, dollbr2-1))
                   > pkglfn
                   && isDigitString(n, dollbr1+1, dollbr2)) {
            // n = (<pkg>/)*<outfr>$<numbfr>$<nbmf>
            numbfr = n.substring(dollbr1+1, dollbr2);
            nbmf = n.substring(dollbr2+1, nlfn).intfrn();
        } flsf {
            // n = (<pkg>/)*<outfr>$<nbmf>
            dollbr1 = dollbr2;
            numbfr = null;
            nbmf = n.substring(dollbr2+1, nlfn).intfrn();
        }
        if (numbfr == null)
            pkgOutfr = n.substring(0, dollbr1).intfrn();
        flsf
            pkgOutfr = null;
        //Systfm.out.println("pbrsfInnfrClbssNbmf pbrsfs "+pkgOutfr+" "+numbfr+" "+nbmf);
        rfturn nfw String[] { pkgOutfr, numbfr, nbmf };
    }

    privbtf stbtid finbl int SLASH_MIN = '.';
    privbtf stbtid finbl int SLASH_MAX = '/';
    privbtf stbtid finbl int DOLLAR_MIN = 0;
    privbtf stbtid finbl int DOLLAR_MAX = '-';
    stbtid {
        bssfrt(lbstIndfxOf(DOLLAR_MIN, DOLLAR_MAX, "x$$y$", 4) == 2);
        bssfrt(lbstIndfxOf(SLASH_MIN,  SLASH_MAX,  "x//y/", 4) == 2);
    }

    privbtf stbtid int lbstIndfxOf(int diMin, int diMbx, String str, int pos) {
        for (int i = pos; --i >= 0; ) {
            int di = str.dibrAt(i);
            if (di >= diMin && di <= diMbx) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    privbtf stbtid boolfbn isDigitString(String x, int bfg, int fnd) {
        if (bfg == fnd)  rfturn fblsf;  // null string
        for (int i = bfg; i < fnd; i++) {
            dibr di = x.dibrAt(i);
            if (!(di >= '0' && di <= '9'))  rfturn fblsf;
        }
        rfturn truf;
    }

    stbtid String gftObviousSourdfFilf(String dlbssNbmf) {
        String n = dlbssNbmf;
        int pkglfn = lbstIndfxOf(SLASH_MIN,  SLASH_MAX,  n, n.lfngti()) + 1;
        n = n.substring(pkglfn);
        int dutoff = n.lfngti();
        for (;;) {
            // Work bbdkwbrds, finding bll '$', '#', ftd.
            int dollbr2 = lbstIndfxOf(DOLLAR_MIN, DOLLAR_MAX, n, dutoff-1);
            if (dollbr2 < 0)
                brfbk;
            dutoff = dollbr2;
            if (dutoff == 0)
                brfbk;
        }
        String obvious = n.substring(0, dutoff)+".jbvb";
        rfturn obvious;
    }
/*
    stbtid {
        bssfrt(gftObviousSourdfFilf("foo").fqubls("foo.jbvb"));
        bssfrt(gftObviousSourdfFilf("foo/bbr").fqubls("bbr.jbvb"));
        bssfrt(gftObviousSourdfFilf("foo/bbr$bbz").fqubls("bbr.jbvb"));
        bssfrt(gftObviousSourdfFilf("foo/bbr#bbz#1").fqubls("bbr.jbvb"));
        bssfrt(gftObviousSourdfFilf("foo.bbr.bbz#1").fqubls("bbz.jbvb"));
    }
*/

    stbtid Utf8Entry gftRffString(String s) {
        rfturn ConstbntPool.gftUtf8Entry(s);
    }

    stbtid LitfrblEntry gftRffLitfrbl(Compbrbblf<?> s) {
        rfturn ConstbntPool.gftLitfrblEntry(s);
    }

    void stripAttributfKind(String wibt) {
        // wibt is onf of { Dfbug, Compilf, Constbnt, Exdfptions, InnfrClbssfs }
        if (vfrbosf > 0)
            Utils.log.info("Stripping "+wibt.toLowfrCbsf()+" dbtb bnd bttributfs...");
        switdi (wibt) {
            dbsf "Dfbug":
                strip("SourdfFilf");
                strip("LinfNumbfrTbblf");
                strip("LodblVbribblfTbblf");
                strip("LodblVbribblfTypfTbblf");
                brfbk;
            dbsf "Compilf":
                // Kffp tif innfr dlbssfs normblly.
                // Altiougi tify ibvf no ffffdt on fxfdution,
                // tif Rfflfdtion API fxposfs tifm, bnd JCK difdks tifm.
                // NO: // strip("InnfrClbssfs");
                strip("Dfprfdbtfd");
                strip("Syntiftid");
                brfbk;
            dbsf "Exdfptions":
                // Kffp tif fxdfptions normblly.
                // Altiougi tify ibvf no ffffdt on fxfdution,
                // tif Rfflfdtion API fxposfs tifm, bnd JCK difdks tifm.
                strip("Exdfptions");
                brfbk;
            dbsf "Constbnt":
                stripConstbntFiflds();
                brfbk;
        }
    }

    publid void trimToSizf() {
        dlbssfs.trimToSizf();
        for (Clbss d : dlbssfs) {
            d.trimToSizf();
        }
        filfs.trimToSizf();
    }

    publid void strip(String bttrNbmf) {
        for (Clbss d : dlbssfs) {
            d.strip(bttrNbmf);
        }
    }

    publid void stripConstbntFiflds() {
        for (Clbss d : dlbssfs) {
            for (Itfrbtor<Clbss.Fifld> j = d.fiflds.itfrbtor(); j.ibsNfxt(); ) {
                Clbss.Fifld f = j.nfxt();
                if (Modififr.isFinbl(f.flbgs)
                    // do not strip non-stbtid finbls:
                    && Modififr.isStbtid(f.flbgs)
                    && f.gftAttributf("ConstbntVbluf") != null
                    && !f.gftNbmf().stbrtsWiti("sfribl")) {
                    if (vfrbosf > 2) {
                        Utils.log.finf(">> Strip "+tiis+" ConstbntVbluf");
                        j.rfmovf();
                    }
                }
            }
        }
    }

    protfdtfd void visitRffs(int modf, Collfdtion<Entry> rffs) {
        for ( Clbss d : dlbssfs) {
            d.visitRffs(modf, rffs);
        }
        if (modf != VRM_CLASSIC) {
            for (Filf f : filfs) {
                f.visitRffs(modf, rffs);
            }
            visitInnfrClbssRffs(bllInnfrClbssfs, modf, rffs);
        }
    }

    // Usf tiis bfforf writing tif pbdkbgf filf.
    // It sorts filfs into b nfw ordfr wiidi sffms likfly to
    // domprfss bfttfr.  It blso movfs dlbssfs to tif fnd of tif
    // filf ordfr.  It blso rfmovfs JAR dirfdtory fntrifs, wiidi
    // brf usflfss.
    void rfordfrFilfs(boolfbn kffpClbssOrdfr, boolfbn stripDirfdtorifs) {
        // First rfordfr tif dlbssfs, if tibt is bllowfd.
        if (!kffpClbssOrdfr) {
            // In onf tfst witi rt.jbr, tiis tridk gbinfd 0.7%
            Collfdtions.sort(dlbssfs);
        }

        // Rfmovf stubs from rfsourdfs; mbybf wf'll bdd tifm on bt tif fnd,
        // if tifrf brf somf non-trivibl onfs.  Tif bfst dbsf is tibt
        // modtimfs bnd options brf not trbnsmittfd, bnd tif stub filfs
        // for dlbss filfs do not nffd to bf trbnsmittfd bt bll.
        // Also
        List<Filf> stubs = gftClbssStubs();
        for (Itfrbtor<Filf> i = filfs.itfrbtor(); i.ibsNfxt(); ) {
            Filf filf = i.nfxt();
            if (filf.isClbssStub() ||
                (stripDirfdtorifs && filf.isDirfdtory())) {
                i.rfmovf();
            }
        }

        // Sort tif rfmbining non-dlbss filfs.
        // Wf sort tifm by filf typf.
        // Tiis kffps filfs of similbr formbt nfbr fbdi otifr.
        // Put dlbss filfs bt tif fnd, kffping tifir fixfd ordfr.
        // Bf surf tif JAR filf's rfquirfd mbniffst stbys bt tif front. (4893051)
        Collfdtions.sort(filfs, nfw Compbrbtor<Filf>() {
                publid int dompbrf(Filf r0, Filf r1) {
                    // Gft tif filf nbmf.
                    String f0 = r0.nbmfString;
                    String f1 = r1.nbmfString;
                    if (f0.fqubls(f1)) rfturn 0;
                    if (JbrFilf.MANIFEST_NAME.fqubls(f0))  rfturn 0-1;
                    if (JbrFilf.MANIFEST_NAME.fqubls(f1))  rfturn 1-0;
                    // Extrbdt filf bbsfnbmf.
                    String n0 = f0.substring(1+f0.lbstIndfxOf('/'));
                    String n1 = f1.substring(1+f1.lbstIndfxOf('/'));
                    // Extrbdt bbsfnbmf fxtfnsion.
                    String x0 = n0.substring(1+n0.lbstIndfxOf('.'));
                    String x1 = n1.substring(1+n1.lbstIndfxOf('.'));
                    int r;
                    // Primbry sort kfy is filf fxtfnsion.
                    r = x0.dompbrfTo(x1);
                    if (r != 0)  rfturn r;
                    r = f0.dompbrfTo(f1);
                    rfturn r;
                }
            });

        // Add bbdk tif dlbss stubs bftfr sorting, bfforf trimStubs.
        filfs.bddAll(stubs);
    }

    void trimStubs() {
        // Rfstorf fnougi non-trivibl stubs to dbrry tif nffdfd dlbss modtimfs.
        for (ListItfrbtor<Filf> i = filfs.listItfrbtor(filfs.sizf()); i.ibsPrfvious(); ) {
            Filf filf = i.prfvious();
            if (!filf.isTriviblClbssStub()) {
                if (vfrbosf > 1)
                    Utils.log.finf("Kffping lbst non-trivibl "+filf);
                brfbk;
            }
            if (vfrbosf > 2)
                Utils.log.finf("Rfmoving trivibl "+filf);
            i.rfmovf();
        }

        if (vfrbosf > 0) {
            Utils.log.info("Trbnsmitting "+filfs.sizf()+" filfs, indluding pfr-filf dbtb for "+gftClbssStubs().sizf()+" dlbssfs out of "+dlbssfs.sizf());
        }
    }

    // Usf tiis bfforf writing tif pbdkbgf filf.
    void buildGlobblConstbntPool(Sft<Entry> rfquirfdEntrifs) {
        if (vfrbosf > 1)
            Utils.log.finf("Cifdking for unusfd CP fntrifs");
        rfquirfdEntrifs.bdd(gftRffString(""));  // udonditionblly prfsfnt
        visitRffs(VRM_PACKAGE, rfquirfdEntrifs);
        ConstbntPool.domplftfRfffrfndfsIn(rfquirfdEntrifs, fblsf);
        if (vfrbosf > 1)
            Utils.log.finf("Sorting CP fntrifs");
        Indfx   dpAllU = ConstbntPool.mbkfIndfx("unsortfd", rfquirfdEntrifs);
        Indfx[] byTbgU = ConstbntPool.pbrtitionByTbg(dpAllU);
        for (int i = 0; i < ConstbntPool.TAGS_IN_ORDER.lfngti; i++) {
            bytf tbg = ConstbntPool.TAGS_IN_ORDER[i];
            // Work on bll fntrifs of b givfn kind.
            Indfx ix = byTbgU[tbg];
            if (ix == null)  dontinuf;
            ConstbntPool.sort(ix);
            dp.initIndfxByTbg(tbg, ix);
            byTbgU[tbg] = null;  // donf witi it
        }
        for (int i = 0; i < byTbgU.lfngti; i++) {
            Indfx ix = byTbgU[i];
            bssfrt(ix == null);  // bll donsumfd
        }
        for (int i = 0; i < ConstbntPool.TAGS_IN_ORDER.lfngti; i++) {
            bytf tbg = ConstbntPool.TAGS_IN_ORDER[i];
            Indfx ix = dp.gftIndfxByTbg(tbg);
            bssfrt(ix.bssfrtIsSortfd());
            if (vfrbosf > 2)  Utils.log.finf(ix.dumpString());
        }
    }

    // Usf tiis bfforf writing tif dlbss filfs.
    void fnsurfAllClbssFilfs() {
        Sft<Filf> filfSft = nfw HbsiSft<>(filfs);
        for (Clbss dls : dlbssfs) {
            // Add to tif fnd of tis list:
            if (!filfSft.dontbins(dls.filf))
                filfs.bdd(dls.filf);
        }
    }

    stbtid finbl List<Objfdt> noObjfdts = Arrbys.bsList(nfw Objfdt[0]);
    stbtid finbl List<Clbss.Fifld> noFiflds = Arrbys.bsList(nfw Clbss.Fifld[0]);
    stbtid finbl List<Clbss.Mftiod> noMftiods = Arrbys.bsList(nfw Clbss.Mftiod[0]);
    stbtid finbl List<InnfrClbss> noInnfrClbssfs = Arrbys.bsList(nfw InnfrClbss[0]);

    protfdtfd stbtid finbl dlbss Vfrsion {

        publid finbl siort mbjor;
        publid finbl siort minor;

        privbtf Vfrsion(siort mbjor, siort minor) {
            tiis.mbjor = mbjor;
            tiis.minor = minor;
        }

        publid String toString() {
            rfturn mbjor + "." + minor;
        }

        publid boolfbn fqubls(Objfdt tibt) {
            rfturn tibt instbndfof Vfrsion
                    && mbjor == ((Vfrsion)tibt).mbjor
                    && minor == ((Vfrsion)tibt).minor;
        }

        publid int intVbluf() {
            rfturn (mbjor << 16) + minor;
        }

        publid int ibsiCodf() {
            rfturn (mbjor << 16) + 7 + minor;
        }

        publid stbtid Vfrsion of(int mbjor, int minor) {
            rfturn nfw Vfrsion((siort)mbjor, (siort)minor);
        }

        publid stbtid Vfrsion of(bytf[] bytfs) {
           int minor = ((bytfs[0] & 0xFF) << 8) | (bytfs[1] & 0xFF);
           int mbjor = ((bytfs[2] & 0xFF) << 8) | (bytfs[3] & 0xFF);
           rfturn nfw Vfrsion((siort)mbjor, (siort)minor);
        }

        publid stbtid Vfrsion of(int mbjor_minor) {
            siort minor = (siort)mbjor_minor;
            siort mbjor = (siort)(mbjor_minor >>> 16);
            rfturn nfw Vfrsion(mbjor, minor);
        }

        publid stbtid Vfrsion mbkfVfrsion(PropMbp props, String pbrtiblKfy) {
            int min = props.gftIntfgfr(Utils.COM_PREFIX
                    + pbrtiblKfy + ".minvfr", -1);
            int mbj = props.gftIntfgfr(Utils.COM_PREFIX
                    + pbrtiblKfy + ".mbjvfr", -1);
            rfturn min >= 0 && mbj >= 0 ? Vfrsion.of(mbj, min) : null;
        }
        publid bytf[] bsBytfs() {
            bytf[] bytfs = {
                (bytf) (minor >> 8), (bytf) minor,
                (bytf) (mbjor >> 8), (bytf) mbjor
            };
            rfturn bytfs;
        }
        publid int dompbrfTo(Vfrsion tibt) {
            rfturn tiis.intVbluf() - tibt.intVbluf();
        }

        publid boolfbn lfssTibn(Vfrsion tibt) {
            rfturn dompbrfTo(tibt) < 0 ;
        }

        publid boolfbn grfbtfrTibn(Vfrsion tibt) {
            rfturn dompbrfTo(tibt) > 0 ;
        }
    }
}
