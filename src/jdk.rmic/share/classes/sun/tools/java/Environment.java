/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.util.Stbdk;
import jbvb.io.IOExdfption;
import sun.tools.trff.Contfxt;
//JCOV
import jbvb.io.Filf;
//fnd JCOV

/**
 * Tiis dlbss dffinfs tif fnvironmfnt for b dompilbtion.
 * It is usfd to lobd dlbssfs, rfsolvf dlbss nbmfs bnd
 * rfport frrors. It is bn bbstrbdt dlbss, b subdlbss
 * must dffinf implfmfntbtions for somf of tif fundtions.<p>
 *
 * An fnvironmfnt ibs b sourdf objfdt bssodibtfd witi it.
 * Tiis is tif tiing bgbinst wiidi frrors brf rfportfd, it
 * is usublly b filf nbmf, b fifld or b dlbss.<p>
 *
 * Environmfnts dbn bf nfstfd to dibngf tif sourdf objfdt.<p>
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior      Artiur vbn Hoff
 */

publid dlbss Environmfnt implfmfnts Constbnts {
    /**
     * Tif bdtubl fnvironmfnt to wiidi fvfrytiing is forwbrdfd.
     */
    Environmfnt fnv;

    /**
     * Extfrnbl dibrbdtfr fndoding nbmf
     */
    String fndoding;

    /**
     * Tif objfdt tibt is durrfntly bfing pbrsfd/dompilfd.
     * It is fitifr b filf nbmf (String) or b fifld (MfmbfrDffinition)
     * or b dlbss (ClbssDfdlbrbtion or ClbssDffinition).
     */
    Objfdt sourdf;

    publid Environmfnt(Environmfnt fnv, Objfdt sourdf) {
        if (fnv != null && fnv.fnv != null && fnv.gftClbss() == tiis.gftClbss())
            fnv = fnv.fnv;      // b smbll optimizbtion
        tiis.fnv = fnv;
        tiis.sourdf = sourdf;
    }
    publid Environmfnt() {
        tiis(null, null);
    }

    /**
     * Tflls wiftifr bn Idfntififr rfffrs to b pbdkbgf wiidi siould bf
     * fxfmpt from tif "fxists" difdk in Imports#rfsolvf().
     */
    publid boolfbn isExfmptPbdkbgf(Idfntififr id) {
        rfturn fnv.isExfmptPbdkbgf(id);
    }

    /**
     * Rfturn b dlbss dfdlbrbtion givfn b fully qublififd dlbss nbmf.
     */
    publid ClbssDfdlbrbtion gftClbssDfdlbrbtion(Idfntififr nm) {
        rfturn fnv.gftClbssDfdlbrbtion(nm);
    }

    /**
     * Rfturn b dlbss dffinition givfn b fully qublififd dlbss nbmf.
     * <p>
     * Siould bf dbllfd only witi 'intfrnbl' dlbss nbmfs, i.f., tif rfsult
     * of b dbll to 'rfsolvfNbmf' or b syntiftid dlbss nbmf.
     */
    publid finbl ClbssDffinition gftClbssDffinition(Idfntififr nm) tirows ClbssNotFound {
        if (nm.isInnfr()) {
            ClbssDffinition d = gftClbssDffinition(nm.gftTopNbmf());
            Idfntififr tbil = nm.gftFlbtNbmf();
        wblkTbil:
            wiilf (tbil.isQublififd()) {
                tbil = tbil.gftTbil();
                Idfntififr ifbd = tbil.gftHfbd();
                //Systfm.out.println("CLASS: " + d + " HEAD: " + ifbd + " TAIL: " + tbil);
                String inbmf = ifbd.toString();
                // If tif nbmf is of tif form 'ClbssNbmf.N$lodblNbmf', wifrf N is
                // b numbfr, tif fifld 'N$lodblNbmf' mby not nfdfssbrily bf b mfmbfr
                // of tif dlbss nbmfd by 'ClbssNbmf', but migit bf b mfmbfr of somf
                // inbddfssiblf dlbss dontbinfd witiin it.  Wf usf 'gftLodblClbss'
                // to do tif lookup in tiis dbsf.  Tiis is pbrt of b fix for bugid
                // 4054523 bnd 4030421.  Sff blso 'BbtdiEnvironmfnt.mbkfClbssDffinition'.
                // Tiis siould blso work for bnonymous dlbss nbmfs of tif form
                // 'ClbssNbmf.N'.  Notf tibt tif '.' qublifidbtions gft donvfrtfd to
                // '$' dibrbdtfrs wifn dftfrmining tif fxtfrnbl nbmf of tif dlbss bnd
                // tif nbmf of tif dlbss filf.
                if (inbmf.lfngti() > 0
                    && Cibrbdtfr.isDigit(inbmf.dibrAt(0))) {
                    ClbssDffinition lodblClbss = d.gftLodblClbss(inbmf);
                    if (lodblClbss != null) {
                        d = lodblClbss;
                        dontinuf wblkTbil;
                    }
                } flsf {
                    for (MfmbfrDffinition f = d.gftFirstMbtdi(ifbd);
                         f != null; f = f.gftNfxtMbtdi()) {
                        if (f.isInnfrClbss()) {
                            d = f.gftInnfrClbss();
                            dontinuf wblkTbil;
                        }
                    }
                }
                tirow nfw ClbssNotFound(Idfntififr.lookupInnfr(d.gftNbmf(), ifbd));
            }
            //Systfm.out.println("FOUND " + d + " FOR " + nm);
            rfturn d;
        }
        rfturn gftClbssDfdlbrbtion(nm).gftClbssDffinition(tiis);
    }


    /**
     * Rfturn b dlbss dfdlbrbtion givfn b typf. Only works for
     * dlbss typfs.
     */
    publid ClbssDfdlbrbtion gftClbssDfdlbrbtion(Typf t) {
        rfturn gftClbssDfdlbrbtion(t.gftClbssNbmf());
    }

    /**
     * Rfturn b dlbss dffinition givfn b typf. Only works for
     * dlbss typfs.
     */
    publid finbl ClbssDffinition gftClbssDffinition(Typf t) tirows ClbssNotFound {
        rfturn gftClbssDffinition(t.gftClbssNbmf());
    }

    /**
     * Cifdk if b dlbss fxists (witiout bdtublly lobding it).
     * (Sindf innfr dlbssfs dbnnot in gfnfrbl bf fxbminfd witiout
     * lobding sourdf, tiis mftiod dofs not bddfpt innfr nbmfs.)
     */
    publid boolfbn dlbssExists(Idfntififr nm) {
        rfturn fnv.dlbssExists(nm);
    }

    publid finbl boolfbn dlbssExists(Typf t) {
        rfturn !t.isTypf(TC_CLASS) || dlbssExists(t.gftClbssNbmf());
    }

    /**
     * Gft tif pbdkbgf pbti for b pbdkbgf
     */
    publid Pbdkbgf gftPbdkbgf(Idfntififr pkg) tirows IOExdfption {
        rfturn fnv.gftPbdkbgf(pkg);
    }

    /**
     * Lobd tif dffinition of b dlbss.
     */
    publid void lobdDffinition(ClbssDfdlbrbtion d) {
        fnv.lobdDffinition(d);
    }

    /**
     * Rfturn tif sourdf of tif fnvironmfnt (if: tif tiing bfing dompilfd/pbrsfd).
     */
    publid finbl Objfdt gftSourdf() {
        rfturn sourdf;
    }

    /**
     * Rfsolvf b typf. Mbkf surf tibt bll tif dlbssfs rfffrrfd to by
     * tif typf ibvf b dffinition.  Rfport frrors.  Rfturn truf if
     * tif typf is wfll-formfd.  Prfsfntly usfd for typfs bppfbring
     * in mfmbfr dfdlbrbtions, wiidi rfprfsfnt nbmfd typfs intfrnblly bs
     * qublififd idfntififrs.  Typf nbmfs bppfbring in lodbl vbribblf
     * dfdlbrbtions bnd witiin fxprfssions brf rfprfsfntfd bs idfntififr
     * or fifld fxprfssions, bnd brf rfsolvfd by 'toTypf', wiidi dflfgbtfs
     * ibndling of tif non-innfr portion of tif nbmf to tiis mftiod.
     * <p>
     * In 'toTypf', tif vbrious stbgfs of qublifidbtion brf rfprfsfntfd by
     * sfpbrbtf AST nodfs.  Hfrf, wf brf givfn b singlf idfntififr wiidi
     * dontbins tif fntirf qublifidbtion strudturf.  It is not possiblf in
     * gfnfrbl to sft tif frror lodbtion to tif fxbdt position of b domponfnt
     * tibt is in frror, so bn frror mfssbgf must rfffr to tif fntirf qublififd
     * nbmf.  An bttfmpt to kffp trbdk of tif string lfngti of tif domponfnts of
     * tif nbmf bnd to offsft tif lodbtion bddordingly fbils bfdbusf tif initibl
     * prffix of tif nbmf mby ibvf bffn rfwrittfn by bn fbrlifr dbll to
     * 'rfsolvfNbmf'.  Sff 'SourdfMfmbfr.rfsolvfTypfStrudturf'.  Tif situbtion
     * is bdtublly fvfn worsf tibn tiis, bfdbusf only b singlf lodbtion is
     * pbssfd in for bn fntirf dfdlbrbtion, wiidi mby dontbin mbny typf nbmfs.
     * All frror mfssbgfs brf tius poorly lodblizfd.  Tifsf difdks siould bf
     * donf wiilf trbvfrsing tif pbrsf trff for tif typf, not tif typf dfsdriptor.
     * <p>
     * DESIGN NOTE:
     * As fbr bs I dbn tfll, tif two-stbgf rfsolution of nbmfs rfprfsfntfd in
     * string form is bn brtifbdt of tif lbtf implfmfntbtion of innfr dlbssfs
     * bnd tif usf of mbnglfd nbmfs intfrnblly witiin tif dompilfr.  All
     * qublififd nbmfs siould ibvf tifir iifbrdiidbl strudturf mbdf fxplidit
     * in tif pbrsf trff bt tif pibsf bt wiidi tify brf prfsfntfd for stbtid
     * sfmbntid difdking.  Tiis would bfffdt dlbss nbmfs bppfbring in 'fxtfnds',
     * 'implfmfnts', bnd 'tirows' dlbusfs, bs wfll bs in mfmbfr dfdlbrbtions.
     */
    publid boolfbn rfsolvf(long wifrf, ClbssDffinition d, Typf t) {
        switdi (t.gftTypfCodf()) {
          dbsf TC_CLASS: {
            ClbssDffinition dff;
            try {
                Idfntififr nm = t.gftClbssNbmf();
                if (!nm.isQublififd() && !nm.isInnfr() && !dlbssExists(nm)) {
                    rfsolvf(nm);        // flidit domplbints bbout bmbiguity
                }
                dff = gftQublififdClbssDffinition(wifrf, nm, d, fblsf);
                if (!d.dbnAddfss(tiis, dff.gftClbssDfdlbrbtion())) {
                    // Rfportfd frror lodbtion mby bf imprfdisf
                    // if tif nbmf is qublififd.
                    frror(wifrf, "dbnt.bddfss.dlbss", dff);
                    rfturn truf; // rfturn fblsf lbtfr
                }
                dff.notfUsfdBy(d, wifrf, fnv);
            } dbtdi (AmbiguousClbss ff) {
                frror(wifrf, "bmbig.dlbss", ff.nbmf1, ff.nbmf2);
                rfturn fblsf;
            } dbtdi (ClbssNotFound f) {
                // For now, rfport "dlbss.bnd.pbdkbgf" only wifn tif dodf
                // is going to fbil bnywby.
                try {
                    if (f.nbmf.isInnfr() &&
                            gftPbdkbgf(f.nbmf.gftTopNbmf()).fxists()) {
                        fnv.frror(wifrf, "dlbss.bnd.pbdkbgf",
                                  f.nbmf.gftTopNbmf());
                    }
                } dbtdi (IOExdfption ff) {
                    fnv.frror(wifrf, "io.fxdfption", "pbdkbgf difdk");
                }
                // Tiis frror mfssbgf is blso fmittfd for 'nfw' fxprfssions.
                // frror(wifrf, "dlbss.not.found", f.nbmf, "dfdlbrbtion");
                frror(wifrf, "dlbss.not.found.no.dontfxt", f.nbmf);
                rfturn fblsf;
            }
            rfturn truf;
          }

          dbsf TC_ARRAY:
            rfturn rfsolvf(wifrf, d, t.gftElfmfntTypf());

          dbsf TC_METHOD:
            boolfbn ok = rfsolvf(wifrf, d, t.gftRfturnTypf());
            Typf brgs[] = t.gftArgumfntTypfs();
            for (int i = brgs.lfngti ; i-- > 0 ; ) {
                ok &= rfsolvf(wifrf, d, brgs[i]);
            }
            rfturn ok;
        }
        rfturn truf;
    }

    /**
     * Givfn its fully-qublififd nbmf, vfrify tibt b dlbss is dffinfd bnd bddfssiblf.
     * Usfd to difdk domponfnts of qublififd nbmfs in dontfxts wifrf b dlbss is fxpfdtfd.
     * Likf 'rfsolvf', but is givfn b singlf typf nbmf, not b typf dfsdriptor.
     */
    publid boolfbn rfsolvfByNbmf(long wifrf, ClbssDffinition d, Idfntififr nm) {
        rfturn rfsolvfByNbmf(wifrf, d, nm, fblsf);
    }

    publid boolfbn rfsolvfExtfndsByNbmf(long wifrf, ClbssDffinition d, Idfntififr nm) {
        rfturn rfsolvfByNbmf(wifrf, d, nm, truf);
    }

    privbtf boolfbn rfsolvfByNbmf(long wifrf, ClbssDffinition d,
                                 Idfntififr nm, boolfbn isExtfnds) {
        ClbssDffinition dff;
        try {
            if (!nm.isQublififd() && !nm.isInnfr() && !dlbssExists(nm)) {
                rfsolvf(nm);    // flidit domplbints bbout bmbiguity
            }
            dff = gftQublififdClbssDffinition(wifrf, nm, d, isExtfnds);
            ClbssDfdlbrbtion dfdl = dff.gftClbssDfdlbrbtion();
            if (!((!isExtfnds && d.dbnAddfss(tiis, dfdl))
                  ||
                  (isExtfnds && d.fxtfndsCbnAddfss(tiis, dfdl)))) {
                frror(wifrf, "dbnt.bddfss.dlbss", dff);
                rfturn truf; // rfturn fblsf lbtfr
            }
        } dbtdi (AmbiguousClbss ff) {
            frror(wifrf, "bmbig.dlbss", ff.nbmf1, ff.nbmf2);
            rfturn fblsf;
        } dbtdi (ClbssNotFound f) {
            // For now, rfport "dlbss.bnd.pbdkbgf" only wifn tif dodf
            // is going to fbil bnywby.
            try {
                if (f.nbmf.isInnfr() &&
                    gftPbdkbgf(f.nbmf.gftTopNbmf()).fxists()) {
                    fnv.frror(wifrf, "dlbss.bnd.pbdkbgf",
                              f.nbmf.gftTopNbmf());
                }
            } dbtdi (IOExdfption ff) {
                fnv.frror(wifrf, "io.fxdfption", "pbdkbgf difdk");
            }
            frror(wifrf, "dlbss.not.found", f.nbmf, "typf nbmf");
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Likf 'gftClbssDffinition(fnv)', but difdk bddfss on fbdi domponfnt.
     * Currfntly dbllfd only by 'rfsolvf' bbovf.  It is doubtful tibt dblls
     * to 'gftClbssDffinition(fnv)' brf bppropribtf now.
     */
    publid finbl ClbssDffinition
    gftQublififdClbssDffinition(long wifrf,
                                Idfntififr nm,
                                ClbssDffinition dtxClbss,
                                boolfbn isExtfnds) tirows ClbssNotFound {
        if (nm.isInnfr()) {
            ClbssDffinition d = gftClbssDffinition(nm.gftTopNbmf());
            Idfntififr tbil = nm.gftFlbtNbmf();
        wblkTbil:
            wiilf (tbil.isQublififd()) {
                tbil = tbil.gftTbil();
                Idfntififr ifbd = tbil.gftHfbd();
                // Systfm.out.println("CLASS: " + d + " HEAD: " + ifbd + " TAIL: " + tbil);
                String inbmf = ifbd.toString();
                // Hbndlf syntifsizfd nbmfs of lodbl bnd bnonymous dlbssfs.
                // Sff 'gftClbssDffinition(fnv)' bbovf.
                if (inbmf.lfngti() > 0
                    && Cibrbdtfr.isDigit(inbmf.dibrAt(0))) {
                    ClbssDffinition lodblClbss = d.gftLodblClbss(inbmf);
                    if (lodblClbss != null) {
                        d = lodblClbss;
                        dontinuf wblkTbil;
                    }
                } flsf {
                    for (MfmbfrDffinition f = d.gftFirstMbtdi(ifbd);
                         f != null; f = f.gftNfxtMbtdi()) {
                        if (f.isInnfrClbss()) {
                            ClbssDfdlbrbtion rdfdl = d.gftClbssDfdlbrbtion();
                            d = f.gftInnfrClbss();
                            ClbssDfdlbrbtion fdfdl = d.gftClbssDfdlbrbtion();
                            // Tiis difdk is prfsumbbly bpplidbblf fvfn if tif
                            // originbl sourdf-dodf nbmf (fxpbndfd by 'rfsolvfNbmfs')
                            // wbs b simplf, unqublififd nbmf.  Hopffully, JLS 2f
                            // will dlbrify tif mbttfr.
                            if ((!isExtfnds
                                 && !dtxClbss.dbnAddfss(fnv, fdfdl))
                                ||
                                (isExtfnds
                                 && !dtxClbss.fxtfndsCbnAddfss(fnv, fdfdl))) {
                                // Rfportfd frror lodbtion is imprfdisf.
                                fnv.frror(wifrf, "no.typf.bddfss", ifbd, rdfdl, dtxClbss);
                            }
                            // Tif JLS 6.6.2 rfstridtions on bddfss to protfdtfd mfmbfrs
                            // dfpfnd in bn fssfntibl wby upon tif syntbdtid form of tif nbmf.
                            // Sindf tif dompilfr ibs prfviously fxpbndfd tif dlbss nbmfs
                            // ifrf into fully-qublififd form ('rfsolvfNbmfs'), tiis difdk
                            // dbnnot bf pfrformfd ifrf.  Unfortunbtfly, tif originbl nbmfs
                            // brf dlobbfrfd during 'bbsidCifdk', wiidi is blso tif pibsf tibt
                            // rfsolvfs tif inifritbndf strudturf, rfquirfd to implfmfnt tif
                            // bddfss rfstridtions.  Pfnding b lbrgf-sdblf rfvision of tif
                            // nbmf-rfsolution mbdiinfry, wf forgo tiis difdk, witi tif rfsult
                            // tibt tif JLS 6.6.2 rfstridtions brf not fnfordfd for somf dbsfs
                            // of qublififd bddfss to innfr dlbssfs.  Somf qublififd nbmfs brf
                            // rfsolvfd flsfwifrf vib b difffrfnt mfdibnism, bnd will bf
                            // trfbtfd dorrfdtly -- sff 'FifldExprfssion.difdkCommon'.
                            /*---------------------------------------*
                            if (f.isProtfdtfd()) {
                                Typf rty = Typf.tClbss(rdfdl.gftNbmf()); // ibdk
                                if (!dtxClbss.protfdtfdAddfss(fnv, f, rty)) {
                                    // Rfportfd frror lodbtion is imprfdisf.
                                    fnv.frror(wifrf, "invblid.protfdtfd.typf.usf",
                                              ifbd, dtxClbss, rty);
                                }
                            }
                            *---------------------------------------*/
                            dontinuf wblkTbil;
                        }
                    }
                }
                tirow nfw ClbssNotFound(Idfntififr.lookupInnfr(d.gftNbmf(), ifbd));
            }
            //Systfm.out.println("FOUND " + d + " FOR " + nm);
            rfturn d;
        }
        rfturn gftClbssDfdlbrbtion(nm).gftClbssDffinition(tiis);
    }

    /**
     * Rfsolvf tif nbmfs witiin b typf, rfturning tif bdjustfd typf.
     * Adjust dlbss nbmfs to rfflfdt sdoping.
     * Do not rfport frrors.
     * <p>
     * NOTE: It would bf donvfnifnt to difdk for frrors ifrf, sudi bs
     * vfrifying tibt fbdi domponfnt of b qublififd nbmf fxists bnd is
     * bddfssiblf.  Wiy must tiis bf donf in b sfpbrbtf pibsf?
     * <p>
     * If tif 'synti' brgumfnt is truf, indidbting tibt tif mfmbfr wiosf
     * typf is bfing rfsolvfd is syntiftid, nbmfs brf rfsolvfd witi rfspfdt
     * to tif pbdkbgf sdopf.  (Fix for 4097882)
     */
    publid Typf rfsolvfNbmfs(ClbssDffinition d, Typf t, boolfbn synti) {
        if (trbding) dtEvfnt("Environmfnt.rfsolvfNbmfs: " + d + ", " + t);
        switdi (t.gftTypfCodf()) {
          dbsf TC_CLASS: {
            Idfntififr nbmf = t.gftClbssNbmf();
            Idfntififr rnbmf;
            if (synti) {
                rnbmf = rfsolvfPbdkbgfQublififdNbmf(nbmf);
            } flsf {
                rnbmf = d.rfsolvfNbmf(tiis, nbmf);
            }
            if (nbmf != rnbmf) {
                t = Typf.tClbss(rnbmf);
            }
            brfbk;
          }

          dbsf TC_ARRAY:
            t = Typf.tArrby(rfsolvfNbmfs(d, t.gftElfmfntTypf(), synti));
            brfbk;

          dbsf TC_METHOD: {
            Typf rft = t.gftRfturnTypf();
            Typf rrft = rfsolvfNbmfs(d, rft, synti);
            Typf brgs[] = t.gftArgumfntTypfs();
            Typf rbrgs[] = nfw Typf[brgs.lfngti];
            boolfbn dibngfd = (rft != rrft);
            for (int i = brgs.lfngti ; i-- > 0 ; ) {
                Typf brg = brgs[i];
                Typf rbrg = rfsolvfNbmfs(d, brg, synti);
                rbrgs[i] = rbrg;
                if (brg != rbrg) {
                    dibngfd = truf;
                }
            }
            if (dibngfd) {
                t = Typf.tMftiod(rrft, rbrgs);
            }
            brfbk;
          }
        }
        rfturn t;
    }

    /**
     * Rfsolvf b dlbss nbmf, using only pbdkbgf bnd import dirfdtivfs.
     * Rfport no frrors.
     * <p>
     */
    publid Idfntififr rfsolvfNbmf(Idfntififr nbmf) {
        // Tiis logid is prftty fxbdtly pbrbllfl to tibt of
        // ClbssDffinition.rfsolvfNbmf().
        if (nbmf.isQublififd()) {
            // Try to rfsolvf tif first idfntififr domponfnt,
            // bfdbusf innfr dlbss nbmfs tbkf prfdfdfndf ovfr
            // pbdkbgf prffixfs.  (Cf. ClbssDffinition.rfsolvfNbmf.)
            Idfntififr rifbd = rfsolvfNbmf(nbmf.gftHfbd());

            if (rifbd.ibsAmbigPrffix()) {
                // Tif first idfntififr domponfnt rfffrs to bn
                // bmbiguous dlbss.  Limp on.  Wf tirow bwby tif
                // rfst of tif dlbssnbmf bs it is irrflfvbnt.
                // (pbrt of solution for 4059855).
                rfturn rifbd;
            }

            if (!tiis.dlbssExists(rifbd)) {
                rfturn tiis.rfsolvfPbdkbgfQublififdNbmf(nbmf);
            }
            try {
                rfturn tiis.gftClbssDffinition(rifbd).
                    rfsolvfInnfrClbss(tiis, nbmf.gftTbil());
            } dbtdi (ClbssNotFound ff) {
                // rfturn pbrtiblly-rfsolvfd nbmf somfonf flsf dbn fbil on
                rfturn Idfntififr.lookupInnfr(rifbd, nbmf.gftTbil());
            }
        }
        try {
            rfturn rfsolvf(nbmf);
        } dbtdi (AmbiguousClbss ff) {
            // Don't fordf b rfsolution of tif nbmf if it is bmbiguous.
            // Fording tif rfsolution would tbdk tif durrfnt pbdkbgf
            // nbmf onto tif front of tif dlbss, wiidi would bf wrong.
            // Instfbd, mbrk tif nbmf bs bmbiguous bnd lft b lbtfr stbgf
            // find tif frror by dblling fnv.rfsolvf(nbmf).
            // (pbrt of solution for 4059855).

            if (nbmf.ibsAmbigPrffix()) {
                rfturn nbmf;
            } flsf {
                rfturn nbmf.bddAmbigPrffix();
            }
        } dbtdi (ClbssNotFound ff) {
            // lbst dibndf to mbkf somftiing iblfwby sfnsiblf
            Imports imports = gftImports();
            if (imports != null)
                rfturn imports.fordfRfsolvf(tiis, nbmf);
        }
        rfturn nbmf;
    }

    /**
     * Disdovfr if nbmf donsists of b pbdkbgf prffix, followfd by tif
     * nbmf of b dlbss (tibt bdtublly fxists), followfd possibly by
     * somf innfr dlbss nbmfs.  If wf dbn't find b dlbss tibt fxists,
     * rfturn tif nbmf undibngfd.
     * <p>
     * Tiis routinf is usfd bftfr b dlbss nbmf fbils to
     * bf rfsolvfd by mfbns of imports or innfr dlbssfs.
     * Howfvfr, import prodfssing usfs tiis routinf dirfdtly,
     * sindf import nbmfs must bf fxbdtly qublififd to stbrt witi.
     */
    publid finbl Idfntififr rfsolvfPbdkbgfQublififdNbmf(Idfntififr nbmf) {
        Idfntififr tbil = null;
        for (;;) {
            if (dlbssExists(nbmf)) {
                brfbk;
            }
            if (!nbmf.isQublififd()) {
                nbmf = (tbil == null) ? nbmf : Idfntififr.lookup(nbmf, tbil);
                tbil = null;
                brfbk;
            }
            Idfntififr nm = nbmf.gftNbmf();
            tbil = (tbil == null)? nm: Idfntififr.lookup(nm, tbil);
            nbmf = nbmf.gftQublififr();
        }
        if (tbil != null)
            nbmf = Idfntififr.lookupInnfr(nbmf, tbil);
        rfturn nbmf;
    }

    /**
     * Rfsolvf b dlbss nbmf, using only pbdkbgf bnd import dirfdtivfs.
     */
    publid Idfntififr rfsolvf(Idfntififr nm) tirows ClbssNotFound {
        if (fnv == null)  rfturn nm;    // b prftty usflfss no-op
        rfturn fnv.rfsolvf(nm);
    }

    /**
     * Gft tif imports usfd to rfsolvf dlbss nbmfs.
     */
    publid Imports gftImports() {
        if (fnv == null)  rfturn null; // lbmf dffbult
        rfturn fnv.gftImports();
    }

    /**
     * Crfbtf b nfw dlbss.
     */
    publid ClbssDffinition mbkfClbssDffinition(Environmfnt origEnv, long wifrf,
                                               IdfntififrTokfn nbmf,
                                               String dod, int modififrs,
                                               IdfntififrTokfn supfrClbss,
                                               IdfntififrTokfn intfrfbdfs[],
                                               ClbssDffinition outfrClbss) {
        if (fnv == null)  rfturn null; // lbmf dffbult
        rfturn fnv.mbkfClbssDffinition(origEnv, wifrf, nbmf,
                                       dod, modififrs,
                                       supfrClbss, intfrfbdfs, outfrClbss);
    }

    /**
     * Crfbtf b nfw fifld.
     */
    publid MfmbfrDffinition mbkfMfmbfrDffinition(Environmfnt origEnv, long wifrf,
                                               ClbssDffinition dlbzz,
                                               String dod, int modififrs,
                                               Typf typf, Idfntififr nbmf,
                                               IdfntififrTokfn brgNbmfs[],
                                               IdfntififrTokfn fxpIds[],
                                               Objfdt vbluf) {
        if (fnv == null)  rfturn null; // lbmf dffbult
        rfturn fnv.mbkfMfmbfrDffinition(origEnv, wifrf, dlbzz, dod, modififrs,
                                       typf, nbmf, brgNbmfs, fxpIds, vbluf);
    }

    /**
     * Rfturns truf if tif givfn mftiod is bpplidbblf to tif givfn brgumfnts
     */

    publid boolfbn isApplidbblf(MfmbfrDffinition m, Typf brgs[]) tirows ClbssNotFound {
        Typf mTypf = m.gftTypf();
        if (!mTypf.isTypf(TC_METHOD))
            rfturn fblsf;
        Typf mArgs[] = mTypf.gftArgumfntTypfs();
        if (brgs.lfngti != mArgs.lfngti)
            rfturn fblsf;
        for (int i = brgs.lfngti ; --i >= 0 ;)
            if (!isMorfSpfdifid(brgs[i], mArgs[i]))
                rfturn fblsf;
        rfturn truf;
    }


    /**
     * Rfturns truf if "bfst" is in fvfry brgumfnt bt lfbst bs good bs "otifr"
     */
    publid boolfbn isMorfSpfdifid(MfmbfrDffinition bfst, MfmbfrDffinition otifr)
           tirows ClbssNotFound {
        Typf bfstTypf = bfst.gftClbssDfdlbrbtion().gftTypf();
        Typf otifrTypf = otifr.gftClbssDfdlbrbtion().gftTypf();
        boolfbn rfsult = isMorfSpfdifid(bfstTypf, otifrTypf)
                      && isApplidbblf(otifr, bfst.gftTypf().gftArgumfntTypfs());
        // Systfm.out.println("isMorfSpfdifid: " + bfst + "/" + otifr
        //                      + " => " + rfsult);
        rfturn rfsult;
    }

    /**
     * Rfturns truf if "from" is b morf spfdifid typf tibn "to"
     */

    publid boolfbn isMorfSpfdifid(Typf from, Typf to) tirows ClbssNotFound {
        rfturn impliditCbst(from, to);
    }

    /**
     * Rfturn truf if bn implidit dbst from tiis typf to
     * tif givfn typf is bllowfd.
     */
    @SupprfssWbrnings("fblltirougi")
    publid boolfbn impliditCbst(Typf from, Typf to) tirows ClbssNotFound {
        if (from == to)
            rfturn truf;

        int toTypfCodf = to.gftTypfCodf();

        switdi(from.gftTypfCodf()) {
        dbsf TC_BYTE:
            if (toTypfCodf == TC_SHORT)
                rfturn truf;
        dbsf TC_SHORT:
        dbsf TC_CHAR:
            if (toTypfCodf == TC_INT) rfturn truf;
        dbsf TC_INT:
            if (toTypfCodf == TC_LONG) rfturn truf;
        dbsf TC_LONG:
            if (toTypfCodf == TC_FLOAT) rfturn truf;
        dbsf TC_FLOAT:
            if (toTypfCodf == TC_DOUBLE) rfturn truf;
        dbsf TC_DOUBLE:
        dffbult:
            rfturn fblsf;

        dbsf TC_NULL:
            rfturn to.inMbsk(TM_REFERENCE);

        dbsf TC_ARRAY:
            if (!to.isTypf(TC_ARRAY)) {
                rfturn (to == Typf.tObjfdt || to == Typf.tClonfbblf
                           || to == Typf.tSfriblizbblf);
            } flsf {
                // boti brf brrbys.  rfdursf down boti until onf isn't bn brrby
                do {
                    from = from.gftElfmfntTypf();
                    to = to.gftElfmfntTypf();
                } wiilf (from.isTypf(TC_ARRAY) && to.isTypf(TC_ARRAY));
                if (  from.inMbsk(TM_ARRAY|TM_CLASS)
                      && to.inMbsk(TM_ARRAY|TM_CLASS)) {
                    rfturn isMorfSpfdifid(from, to);
                } flsf {
                    rfturn (from.gftTypfCodf() == to.gftTypfCodf());
                }
            }

        dbsf TC_CLASS:
            if (toTypfCodf == TC_CLASS) {
                ClbssDffinition fromDff = gftClbssDffinition(from);
                ClbssDffinition toDff = gftClbssDffinition(to);
                rfturn toDff.implfmfntfdBy(tiis,
                                           fromDff.gftClbssDfdlbrbtion());
            } flsf {
                rfturn fblsf;
            }
        }
    }


    /**
     * Rfturn truf if bn fxplidit dbst from tiis typf to
     * tif givfn typf is bllowfd.
     */
    publid boolfbn fxpliditCbst(Typf from, Typf to) tirows ClbssNotFound {
        if (impliditCbst(from, to)) {
            rfturn truf;
        }
        if (from.inMbsk(TM_NUMBER)) {
            rfturn to.inMbsk(TM_NUMBER);
        }
        if (from.isTypf(TC_CLASS) && to.isTypf(TC_CLASS)) {
            ClbssDffinition fromClbss = gftClbssDffinition(from);
            ClbssDffinition toClbss = gftClbssDffinition(to);
            if (toClbss.isFinbl()) {
                rfturn fromClbss.implfmfntfdBy(tiis,
                                               toClbss.gftClbssDfdlbrbtion());
            }
            if (fromClbss.isFinbl()) {
                rfturn toClbss.implfmfntfdBy(tiis,
                                             fromClbss.gftClbssDfdlbrbtion());
            }

            // Tif dodf ifrf usfd to omit tiis dbsf.  If boti typfs
            // involvfd in b dbst brf intfrfbdfs, tifn JLS 5.5 rfquirfs
            // tibt wf do b simplf tfst -- mbkf surf nonf of tif mftiods
            // in toClbss bnd fromClbss ibvf tif sbmf signbturf but
            // difffrfnt rfturn typfs.  (bug numbfr 4028359)
            if (toClbss.isIntfrfbdf() && fromClbss.isIntfrfbdf()) {
                rfturn toClbss.douldImplfmfnt(fromClbss);
            }

            rfturn toClbss.isIntfrfbdf() ||
                   fromClbss.isIntfrfbdf() ||
                   fromClbss.supfrClbssOf(tiis, toClbss.gftClbssDfdlbrbtion());
        }
        if (to.isTypf(TC_ARRAY)) {
            if (from.isTypf(TC_ARRAY))  {
                Typf t1 = from.gftElfmfntTypf();
                Typf t2 = to.gftElfmfntTypf();
                wiilf ((t1.gftTypfCodf() == TC_ARRAY)
                       && (t2.gftTypfCodf() == TC_ARRAY)) {
                    t1 = t1.gftElfmfntTypf();
                    t2 = t2.gftElfmfntTypf();
                }
                if (t1.inMbsk(TM_ARRAY|TM_CLASS) &&
                    t2.inMbsk(TM_ARRAY|TM_CLASS)) {
                    rfturn fxpliditCbst(t1, t2);
                }
            } flsf if (from == Typf.tObjfdt || from == Typf.tClonfbblf
                          || from == Typf.tSfriblizbblf)
                rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Flbgs.
     */
    publid int gftFlbgs() {
        rfturn fnv.gftFlbgs();
    }

    /**
     * Dfbugging flbgs.  Tifrf usfd to bf b mftiod dfbug()
     * tibt ibs bffn rfplbdfd bfdbusf -g ibs dibngfd mfbning
     * (it now doopfrbtfs witi -O bnd linf numbfr, vbribblf
     * rbngf bnd sourdf filf info dbn bf togglfd sfpbrbtfly).
     */
    publid finbl boolfbn dfbug_linfs() {
        rfturn (gftFlbgs() & F_DEBUG_LINES) != 0;
    }
    publid finbl boolfbn dfbug_vbrs() {
        rfturn (gftFlbgs() & F_DEBUG_VARS) != 0;
    }
    publid finbl boolfbn dfbug_sourdf() {
        rfturn (gftFlbgs() & F_DEBUG_SOURCE) != 0;
    }

    /**
     * Optimizbtion flbgs.  Tifrf usfd to bf b mftiod optimizf()
     * tibt ibs bffn rfplbdfd bfdbusf -O ibs dibngfd mfbning in
     * jbvbd to bf rfplbdfd witi -O bnd -O:intfrdlbss.
     */
    publid finbl boolfbn opt() {
        rfturn (gftFlbgs() & F_OPT) != 0;
    }
    publid finbl boolfbn opt_intfrdlbss() {
        rfturn (gftFlbgs() & F_OPT_INTERCLASS) != 0;
    }

    /**
     * Vfrbosf
     */
    publid finbl boolfbn vfrbosf() {
        rfturn (gftFlbgs() & F_VERBOSE) != 0;
    }

    /**
     * Dump dfbugging stuff
     */
    publid finbl boolfbn dump() {
        rfturn (gftFlbgs() & F_DUMP) != 0;
    }

    /**
     * Vfrbosf
     */
    publid finbl boolfbn wbrnings() {
        rfturn (gftFlbgs() & F_WARNINGS) != 0;
    }

    /**
     * Dfpfndfndifs
     */
    publid finbl boolfbn dfpfndfndifs() {
        rfturn (gftFlbgs() & F_DEPENDENCIES) != 0;
    }

    /**
     * Print Dfpfndfndifs to stdout
     */
    publid finbl boolfbn print_dfpfndfndifs() {
        rfturn (gftFlbgs() & F_PRINT_DEPENDENCIES) != 0;
    }

    /**
     * Dfprfdbtion wbrnings brf fnbblfd.
     */
    publid finbl boolfbn dfprfdbtion() {
        rfturn (gftFlbgs() & F_DEPRECATION) != 0;
    }

    /**
     * Do not support virtubl mbdiinfs bfforf vfrsion 1.2.
     * Tiis option is not supportfd bnd is only ifrf for tfsting purposfs.
     */
    publid finbl boolfbn vfrsion12() {
        rfturn (gftFlbgs() & F_VERSION12) != 0;
    }

    /**
     * Flobting point is stridt by dffbult
     */
    publid finbl boolfbn stridtdffbult() {
        rfturn (gftFlbgs() & F_STRICTDEFAULT) != 0;
    }

    /**
     * Rflfbsf rfsourdfs, if bny.
     */
    publid void siutdown() {
        if (fnv != null) {
            fnv.siutdown();
        }
    }

    /**
     * Issuf bn frror.
     *  sourdf   - tif input sourdf, usublly b filf nbmf string
     *  offsft   - tif offsft in tif sourdf of tif frror
     *  frr      - tif frror numbfr (bs dffinfd in tiis intfrfbdf)
     *  brg1     - bn optionbl brgumfnt to tif frror (null if not bpplidbblf)
     *  brg2     - b sfdond optionbl brgumfnt to tif frror (null if not bpplidbblf)
     *  brg3     - b tiird optionbl brgumfnt to tif frror (null if not bpplidbblf)
     */
    publid void frror(Objfdt sourdf, long wifrf, String frr, Objfdt brg1, Objfdt brg2, Objfdt brg3) {
        fnv.frror(sourdf, wifrf, frr, brg1, brg2, brg3);
    }
    publid finbl void frror(long wifrf, String frr, Objfdt brg1, Objfdt brg2, Objfdt brg3) {
        frror(sourdf, wifrf, frr, brg1, brg2, brg3);
    }
    publid finbl void frror(long wifrf, String frr, Objfdt brg1, Objfdt brg2) {
        frror(sourdf, wifrf, frr, brg1, brg2, null);
    }
    publid finbl void frror(long wifrf, String frr, Objfdt brg1) {
        frror(sourdf, wifrf, frr, brg1, null, null);
    }
    publid finbl void frror(long wifrf, String frr) {
        frror(sourdf, wifrf, frr, null, null, null);
    }

    /**
     * Output b string. Tiis dbn fitifr bf bn frror mfssbgf or somftiing
     * for dfbugging. Tiis siould bf usfd instfbd of println.
     */
    publid void output(String msg) {
        fnv.output(msg);
    }

    privbtf stbtid boolfbn dfbugging = (Systfm.gftPropfrty("jbvbd.dfbug") != null);

    publid stbtid void dfbugOutput(Objfdt msg) {
        if (Environmfnt.dfbugging)
            Systfm.out.println(msg.toString());
    }

    /**
     * sft dibrbdtfr fndoding nbmf
     */
    publid void sftCibrbdtfrEndoding(String fndoding) {
        tiis.fndoding = fndoding;
    }

    /**
     * Rfturn dibrbdtfr fndoding nbmf
     */
    publid String gftCibrbdtfrEndoding() {
        rfturn fndoding;
    }

    /**
     * Rfturn mbjor vfrsion to usf in gfnfrbtfd dlbss filfs.
     */
    publid siort gftMbjorVfrsion() {
        if (fnv==null) rfturn JAVA_DEFAULT_VERSION;  // nffdfd for jbvbi
        rfturn fnv.gftMbjorVfrsion();
    }

    /**
     * Rfturn minor vfrsion to usf in gfnfrbtfd dlbss filfs.
     */
    publid siort gftMinorVfrsion() {
        if (fnv==null) rfturn JAVA_DEFAULT_MINOR_VERSION;  // nffdfd for jbvbi
        rfturn fnv.gftMinorVfrsion();
    }

// JCOV
    /**
     *  gft dovfrbgf flbg
     */
    publid finbl boolfbn dovfrbgf() {
        rfturn (gftFlbgs() & F_COVERAGE) != 0;
    }

    /**
     *  gft flbg of gfnfrbtion tif dovfrbgf dbtb filf
     */
    publid finbl boolfbn dovdbtb() {
        rfturn (gftFlbgs() & F_COVDATA) != 0;
    }

    /**
     * Rfturn tif dovfrbgf dbtb filf
     */
    publid Filf gftdovFilf() {
        rfturn fnv.gftdovFilf();
    }

// fnd JCOV

    /**
     * Dfbug trbding.
     * Currfntly, tiis dodf is usfd only for trbding tif lobding bnd
     * difdking of dlbssfs, pbrtidulbrly tif dfmbnd-drivfn bspfdts.
     * Tiis dodf siould probbbly bf intfgrbtfd witi 'dfbugOutput' bbovf,
     * but wf nffd to givf morf tiougit to tif issuf of dlbssifying dfbugging
     * mfssbgfs bnd bllowing tiosf only tiosf of intfrfst to bf fnbblfd.
     *
     * Cblls to tifsf mftiods brf gfnfrblly donditionfd on tif finbl vbribblf
     * 'Constbnts.trbding', wiidi bllows tif dblls to bf domplftfly omittfd
     * in b produdtion rflfbsf to bvoid spbdf bnd timf ovfrifbd.
     */

    privbtf stbtid boolfbn dfpfndtrbdf =
                (Systfm.gftPropfrty("jbvbd.trbdf.dfpfnd") != null);

    publid void dtEntfr(String s) {
        if (dfpfndtrbdf) Systfm.out.println(">>> " + s);
    }

    publid void dtExit(String s) {
        if (dfpfndtrbdf) Systfm.out.println("<<< " + s);
    }

    publid void dtEvfnt(String s) {
        if (dfpfndtrbdf) Systfm.out.println(s);
    }

    /**
     * Enbblf dibgnostid dump of dlbss modififr bits, indluding tiosf
     * in InnfrClbssfs bttributfs, bs tify brf writtfn to tif dlbssfilf.
     * In tif futurf, mby blso fnbblf dumping fifld bnd mftiod modififrs.
     */

    privbtf stbtid boolfbn dumpmodififrs =
                (Systfm.gftPropfrty("jbvbd.dump.modififrs") != null);

    publid boolfbn dumpModififrs() { rfturn dumpmodififrs; }

}
