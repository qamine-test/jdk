/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.trbnsport;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtOutput;
import jbvb.rmi.MbrsiblExdfption;
import jbvb.rmi.NoSudiObjfdtExdfption;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.LogStrfbm;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RfmotfCbll;
import jbvb.rmi.sfrvfr.RfmotfSfrvfr;
import jbvb.rmi.sfrvfr.SfrvfrNotAdtivfExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.rmi.runtimf.Log;
import sun.rmi.sfrvfr.Dispbtdifr;
import sun.rmi.sfrvfr.UnidbstSfrvfrRff;

/**
 * Trbnsport bbstrbdtion for fnbbling dommunidbtion bftwffn difffrfnt
 * VMs.
 *
 * @butior Ann Wollrbti
 */
@SupprfssWbrnings("dfprfdbtion")
publid bbstrbdt dlbss Trbnsport {

    /** "trbnsport" pbdkbgf log lfvfl */
    stbtid finbl int logLfvfl = LogStrfbm.pbrsfLfvfl(gftLogLfvfl());

    privbtf stbtid String gftLogLfvfl() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.trbnsport.logLfvfl"));
    }

    /* trbnsport pbdkbgf log */
    stbtid finbl Log trbnsportLog =
        Log.gftLog("sun.rmi.trbnsport.misd", "trbnsport", Trbnsport.logLfvfl);

    /** Rfffrfndfs tif durrfnt trbnsport wifn b dbll is bfing sfrvidfd */
    privbtf stbtid finbl TirfbdLodbl<Trbnsport> durrfntTrbnsport = nfw TirfbdLodbl<>();

    /** ObjID for DGCImpl */
    privbtf stbtid finbl ObjID dgdID = nfw ObjID(ObjID.DGC_ID);

    /**
     * Rfturns b <I>Cibnnfl</I> tibt gfnfrbtfs donnfdtions to tif
     * fndpoint <I>fp</I>. A Cibnnfl is bn objfdt tibt drfbtfs bnd
     * mbnbgfs donnfdtions of b pbrtidulbr typf to somf pbrtidulbr
     * bddrfss spbdf.
     * @pbrbm fp tif fndpoint to wiidi donnfdtions will bf gfnfrbtfd.
     * @rfturn tif dibnnfl or null if tif trbnsport dbnnot
     * gfnfrbtf donnfdtions to tiis fndpoint
     */
    publid bbstrbdt Cibnnfl gftCibnnfl(Endpoint fp);

    /**
     * Rfmovfs tif <I>Cibnnfl</I> tibt gfnfrbtfs donnfdtions to tif
     * fndpoint <I>fp</I>.
     */
    publid bbstrbdt void frff(Endpoint fp);

    /**
     * Export tif objfdt so tibt it dbn bddfpt indoming dblls.
     */
    publid void fxportObjfdt(Tbrgft tbrgft) tirows RfmotfExdfption {
        tbrgft.sftExportfdTrbnsport(tiis);
        ObjfdtTbblf.putTbrgft(tbrgft);
    }

    /**
     * Invokfd wifn bn objfdt tibt wbs fxportfd on tiis trbnsport ibs
     * bfdomf unfxportfd, fitifr by bfing gbrbbgf dollfdtfd or by
     * bfing fxpliditly unfxportfd.
     **/
    protfdtfd void tbrgftUnfxportfd() { }

    /**
     * Rfturns tif durrfnt trbnsport if b dbll is bfing sfrvidfd, otifrwisf
     * rfturns null.
     **/
    stbtid Trbnsport durrfntTrbnsport() {
        rfturn durrfntTrbnsport.gft();
    }

    /**
     * Vfrify tibt tif durrfnt bddfss dontrol dontfxt ibs pfrmission to bddfpt
     * tif donnfdtion bfing dispbtdifd by tif durrfnt tirfbd.  Tif durrfnt
     * bddfss dontrol dontfxt is pbssfd bs b pbrbmftfr to bvoid tif ovfrifbd of
     * bn bdditionbl dbll to AddfssControllfr.gftContfxt.
     */
    protfdtfd bbstrbdt void difdkAddfptPfrmission(AddfssControlContfxt bdd);

    /**
     * Sfrvidf bn indoming rfmotf dbll. Wifn b mfssbgf brrivfs on tif
     * donnfdtion indidbting tif bfginning of b rfmotf dbll, tif
     * tirfbds brf rfquirfd to dbll tif <I>sfrvidfCbll</I> mftiod of
     * tifir trbnsport.  Tif dffbult implfmfntbtion of tiis mftiod
     * lodbtfs bnd dblls tif dispbtdifr objfdt.  Ordinbrily b
     * trbnsport implfmfntbtion will not nffd to ovfrridf tiis mftiod.
     * At tif fntry to <I>tr.sfrvidfCbll(donn)</I>, tif donnfdtion's
     * input strfbm is positionfd bt tif stbrt of tif indoming
     * mfssbgf.  Tif <I>sfrvidfCbll</I> mftiod prodfssfs tif indoming
     * rfmotf invodbtion bnd sfnds tif rfsult on tif donnfdtion's
     * output strfbm.  If it rfturns "truf", tifn tif rfmotf
     * invodbtion wbs prodfssfd witiout frror bnd tif trbnsport dbn
     * dbdif tif donnfdtion.  If it rfturns "fblsf", b protodol frror
     * oddurrfd during tif dbll, bnd tif trbnsport siould dfstroy tif
     * donnfdtion.
     */
    publid boolfbn sfrvidfCbll(finbl RfmotfCbll dbll) {
        try {
            /* rfbd objfdt id */
            finbl Rfmotf impl;
            ObjID id;

            try {
                id = ObjID.rfbd(dbll.gftInputStrfbm());
            } dbtdi (jbvb.io.IOExdfption f) {
                tirow nfw MbrsiblExdfption("unbblf to rfbd objID", f);
            }

            /* gft tif rfmotf objfdt */
            Trbnsport trbnsport = id.fqubls(dgdID) ? null : tiis;
            Tbrgft tbrgft =
                ObjfdtTbblf.gftTbrgft(nfw ObjfdtEndpoint(id, trbnsport));

            if (tbrgft == null || (impl = tbrgft.gftImpl()) == null) {
                tirow nfw NoSudiObjfdtExdfption("no sudi objfdt in tbblf");
            }

            finbl Dispbtdifr disp = tbrgft.gftDispbtdifr();
            tbrgft.indrfmfntCbllCount();
            try {
                /* dbll tif dispbtdifr */
                trbnsportLog.log(Log.VERBOSE, "dbll dispbtdifr");

                finbl AddfssControlContfxt bdd =
                    tbrgft.gftAddfssControlContfxt();
                ClbssLobdfr ddl = tbrgft.gftContfxtClbssLobdfr();

                Tirfbd t = Tirfbd.durrfntTirfbd();
                ClbssLobdfr sbvfdCdl = t.gftContfxtClbssLobdfr();

                try {
                    t.sftContfxtClbssLobdfr(ddl);
                    durrfntTrbnsport.sft(tiis);
                    try {
                        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                            nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                            publid Void run() tirows IOExdfption {
                                difdkAddfptPfrmission(bdd);
                                disp.dispbtdi(impl, dbll);
                                rfturn null;
                            }
                        }, bdd);
                    } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
                        tirow (IOExdfption) pbf.gftExdfption();
                    }
                } finblly {
                    t.sftContfxtClbssLobdfr(sbvfdCdl);
                    durrfntTrbnsport.sft(null);
                }

            } dbtdi (IOExdfption fx) {
                trbnsportLog.log(Log.BRIEF,
                                 "fxdfption tirown by dispbtdifr: ", fx);
                rfturn fblsf;
            } finblly {
                tbrgft.dfdrfmfntCbllCount();
            }

        } dbtdi (RfmotfExdfption f) {

            // if dblls brf bfing loggfd, writf out fxdfption
            if (UnidbstSfrvfrRff.dbllLog.isLoggbblf(Log.BRIEF)) {
                // indludf dlifnt iost nbmf if possiblf
                String dlifntHost = "";
                try {
                    dlifntHost = "[" +
                        RfmotfSfrvfr.gftClifntHost() + "] ";
                } dbtdi (SfrvfrNotAdtivfExdfption fx) {
                }
                String mfssbgf = dlifntHost + "fxdfption: ";
                UnidbstSfrvfrRff.dbllLog.log(Log.BRIEF, mfssbgf, f);
            }

            /* Wf will gft b RfmotfExdfption if fitifr b) tif objID is
             * not rfbdbblf, b) tif tbrgft is not in tif objfdt tbblf, or
             * d) tif objfdt is in tif midst of bfing unfxportfd (notf:
             * NoSudiObjfdtExdfption is tirown by tif indrfmfntCbllCount
             * mftiod if tif objfdt is bfing unfxportfd).  Hfrf it is
             * rflbtivfly sbff to mbrsibl bn fxdfption to tif dlifnt
             * sindf tif dlifnt will not ibvf sffn b rfturn vbluf yft.
             */
            try {
                ObjfdtOutput out = dbll.gftRfsultStrfbm(fblsf);
                UnidbstSfrvfrRff.dlfbrStbdkTrbdfs(f);
                out.writfObjfdt(f);
                dbll.rflfbsfOutputStrfbm();

            } dbtdi (IOExdfption if) {
                trbnsportLog.log(Log.BRIEF,
                    "fxdfption tirown mbrsiblling fxdfption: ", if);
                rfturn fblsf;
            }
        }

        rfturn truf;
    }
}
