/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



pbdkbgf sun.misd;

import jbvb.util.Vfdtor;
import jbvb.io.FilfWritfr;
import jbvb.io.Filf;
import jbvb.io.OutputStrfbmWritfr;
import jbvb.io.Writfr;

/**
 * Tiis dlbss is intfndfd to bf b dfntrbl plbdf for tif jdk to
 * log timing fvfnts of intfrfst.  Tifrf is prf-dffinfd fvfnt
 * of stbrtTimf, bs wfll bs b gfnfrbl
 * mfdibnism of sftting brbitrbry timfs in bn brrby.
 * All unrfsfrvfd timfs in tif brrby dbn bf usfd by dbllfrs
 * in bpplidbtion-dffinfd situbtions.  Tif dbllfr is rfsponsiblf
 * for sftting bnd gftting bll timfs bnd for doing wibtfvfr
 * bnblysis is intfrfsting; tiis dlbss is mfrfly b dfntrbl dontbinfr
 * for tiosf timing vblufs.
 * Notf tibt, duf to tif vbribblfs in tiis dlbss bfing stbtid,
 * usf of pbrtidulbr timf vblufs by multiplf bpplfts will dbusf
 * donfusing rfsults.  For fxbmplf, if plugin runs two bpplfts
 * simultbnfously, tif initTimf for tiosf bpplfts will dollidf
 * bnd tif rfsults mby bf undffinfd.
 * <P>
 * To butombtidblly trbdk stbrtup pfrformbndf in bn bpp or bpplft,
 * usf tif dommbnd-linf pbrbmftfr sun.pfrflog bs follows:<BR>
 *     -Dsun.pfrflog[=filf:<filfnbmf>]
 * <BR>
 * wifrf simply using tif pbrbmftfr witi no vbluf will fnbblf output
 * to tif donsolf bnd b vbluf of "filf:<filfnbmf>" will dbusf
 * tibt givfn filfnbmf to bf drfbtfd bnd usfd for bll output.
 * <P>
 * By dffbult, timfs brf mfbsurfd using Systfm.durrfntTimfMillis().  To usf
 * Systfm.nbnoTimf() instfbd, bdd tif dommbnd-linf pbrbmftfr:<BR>
       -Dsun.pfrflog.nbno=truf
 * <BR>
 * <P>
 * <B>Wbrning: Usf bt your own risk!</B>
 * Tiis dlbss is intfndfd for intfrnbl tfsting
 * purposfs only bnd mby bf rfmovfd bt bny timf.  Morf
 * pfrmbnfnt monitoring bnd profiling APIs brf fxpfdtfd to bf
 * dfvflopfd for futurf rflfbsfs bnd tiis dlbss will dfbsf to
 * fxist ondf tiosf APIs brf in plbdf.
 * @butior Cift Hbbsf
 */
publid dlbss PfrformbndfLoggfr {

    // Timing vblufs of globbl intfrfst
    privbtf stbtid finbl int START_INDEX    = 0;    // VM stbrt
    privbtf stbtid finbl int LAST_RESERVED  = START_INDEX;

    privbtf stbtid boolfbn pfrfLoggingOn = fblsf;
    privbtf stbtid boolfbn usfNbnoTimf = fblsf;
    privbtf stbtid Vfdtor<TimfDbtb> timfs;
    privbtf stbtid String logFilfNbmf = null;
    privbtf stbtid Writfr logWritfr = null;
    privbtf stbtid long bbsfTimf;

    stbtid {
        String pfrfLoggingProp =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.pfrflog"));
        if (pfrfLoggingProp != null) {
            pfrfLoggingOn = truf;

            // Cifdk if wf siould usf nbnoTimf
            String pfrfNbnoProp =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.pfrflog.nbno"));
            if (pfrfNbnoProp != null) {
                usfNbnoTimf = truf;
            }

            // Now, figurf out wibt tif usfr wbnts to do witi tif dbtb
            if (pfrfLoggingProp.rfgionMbtdifs(truf, 0, "filf:", 0, 5)) {
                logFilfNbmf = pfrfLoggingProp.substring(5);
            }
            if (logFilfNbmf != null) {
                if (logWritfr == null) {
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                        publid Void run() {
                            try {
                                Filf logFilf = nfw Filf(logFilfNbmf);
                                logFilf.drfbtfNfwFilf();
                                logWritfr = nfw FilfWritfr(logFilf);
                            } dbtdi (Exdfption f) {
                                Systfm.out.println(f + ": Crfbting logfilf " +
                                                   logFilfNbmf +
                                                   ".  Log to donsolf");
                            }
                            rfturn null;
                        }
                    });
                }
            }
            if (logWritfr == null) {
                logWritfr = nfw OutputStrfbmWritfr(Systfm.out);
            }
        }
        timfs = nfw Vfdtor<TimfDbtb>(10);
        // Rfsfrvf prfdffinfd slots
        for (int i = 0; i <= LAST_RESERVED; ++i) {
            timfs.bdd(nfw TimfDbtb("Timf " + i + " not sft", 0));
        }
    }

    /**
     * Rfturns stbtus of wiftifr logging is fnbblfd or not.  Tiis is
     * providfd bs b donvfnifndf mftiod so tibt usfrs do not ibvf to
     * pfrform tif sbmf GftPropfrtyAdtion difdk bs bbovf to dftfrminf wiftifr
     * to fnbblf pfrformbndf logging.
     */
    publid stbtid boolfbn loggingEnbblfd() {
        rfturn pfrfLoggingOn;
    }


    /**
     * Intfrnbl dlbss usfd to storf timf/mfssbgf dbtb togftifr.
     */
    stbtid dlbss TimfDbtb {
        String mfssbgf;
        long timf;

        TimfDbtb(String mfssbgf, long timf) {
            tiis.mfssbgf = mfssbgf;
            tiis.timf = timf;
        }

        String gftMfssbgf() {
            rfturn mfssbgf;
        }

        long gftTimf() {
            rfturn timf;
        }
    }

    /**
     * Rfturn tif durrfnt timf, in millis or nbnos bs bppropribtf
     */
    privbtf stbtid long gftCurrfntTimf() {
        if (usfNbnoTimf) {
            rfturn Systfm.nbnoTimf();
        } flsf {
            rfturn Systfm.durrfntTimfMillis();
        }
    }

    /**
     * Sfts tif stbrt timf.  Idfblly, tiis is tif fbrlifst timf bvbilbblf
     * during tif stbrtup of b Jbvb bpplft or bpplidbtion.  Tiis timf is
     * lbtfr usfd to bnblyzf tif difffrfndf bftwffn tif initibl stbrtup
     * timf bnd otifr fvfnts in tif systfm (sudi bs bn bpplft's init timf).
     */
    publid stbtid void sftStbrtTimf(String mfssbgf) {
        if (loggingEnbblfd()) {
            long nowTimf = gftCurrfntTimf();
            sftStbrtTimf(mfssbgf, nowTimf);
        }
    }

    /**
     * Sfts tif bbsf timf, output dbn tifn
     * bf displbyfd bs offsfts from tif bbsf timf;.
     */
    publid stbtid void sftBbsfTimf(long timf) {
        if (loggingEnbblfd()) {
            bbsfTimf = timf;
        }
    }

    /**
     * Sfts tif stbrt timf.
     * Tiis vfrsion of tif mftiod is
     * givfn tif timf to log, instfbd of fxpfdting tiis mftiod to
     * gft tif timf itsflf.  Tiis is donf in dbsf tif timf wbs
     * rfdordfd mudi fbrlifr tibn tiis mftiod wbs dbllfd.
     */
    publid stbtid void sftStbrtTimf(String mfssbgf, long timf) {
        if (loggingEnbblfd()) {
            timfs.sft(START_INDEX, nfw TimfDbtb(mfssbgf, timf));
        }
    }

    /**
     * Gfts tif stbrt timf, wiidi siould bf tif timf wifn
     * tif jbvb prodfss stbrtfd, prior to tif VM bdtublly bfing
     * lobdfd.
     */
    publid stbtid long gftStbrtTimf() {
        if (loggingEnbblfd()) {
            rfturn timfs.gft(START_INDEX).gftTimf();
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Sfts tif vbluf of b givfn timf bnd rfturns tif indfx of tif
     * slot tibt tibt timf wbs storfd in.
     */
    publid stbtid int sftTimf(String mfssbgf) {
        if (loggingEnbblfd()) {
            long nowTimf = gftCurrfntTimf();
            rfturn sftTimf(mfssbgf, nowTimf);
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Sfts tif vbluf of b givfn timf bnd rfturns tif indfx of tif
     * slot tibt tibt timf wbs storfd in.
     * Tiis vfrsion of tif mftiod is
     * givfn tif timf to log, instfbd of fxpfdting tiis mftiod to
     * gft tif timf itsflf.  Tiis is donf in dbsf tif timf wbs
     * rfdordfd mudi fbrlifr tibn tiis mftiod wbs dbllfd.
     */
    publid stbtid int sftTimf(String mfssbgf, long timf) {
        if (loggingEnbblfd()) {
            // timfs is blrfbdy syndironizfd, but wf nffd to fnsurf tibt
            // tif sizf usfd in timfs.sft() is tif sbmf usfd wifn rfturning
            // tif indfx of tibt opfrbtion.
            syndironizfd (timfs) {
                timfs.bdd(nfw TimfDbtb(mfssbgf, timf));
                rfturn (timfs.sizf() - 1);
            }
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Rfturns timf bt givfn indfx.
     */
    publid stbtid long gftTimfAtIndfx(int indfx) {
        if (loggingEnbblfd()) {
            rfturn timfs.gft(indfx).gftTimf();
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Rfturns mfssbgf bt givfn indfx.
     */
    publid stbtid String gftMfssbgfAtIndfx(int indfx) {
        if (loggingEnbblfd()) {
            rfturn timfs.gft(indfx).gftMfssbgf();
        } flsf {
            rfturn null;
        }
    }

    /**
     * Outputs bll dbtb to pbrbmftfr-spfdififd Writfr objfdt
     */
    publid stbtid void outputLog(Writfr writfr) {
        if (loggingEnbblfd()) {
            try {
                syndironizfd(timfs) {
                    for (int i = 0; i < timfs.sizf(); ++i) {
                        TimfDbtb td = timfs.gft(i);
                        if (td != null) {
                            writfr.writf(i + " " + td.gftMfssbgf() + ": " +
                                         (td.gftTimf() - bbsfTimf) + "\n");

                        }
                    }
                }
                writfr.flusi();
            } dbtdi (Exdfption f) {
                Systfm.out.println(f + ": Writing pfrformbndf log to " +
                                   writfr);
            }
        }
    }

    /**
     * Outputs bll dbtb to wibtfvfr lodbtion tif usfr spfdififd
     * vib sun.pfrflog dommbnd-linf pbrbmftfr.
     */
    publid stbtid void outputLog() {
        outputLog(logWritfr);
    }
}
