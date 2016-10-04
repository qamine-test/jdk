/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nio.dibnnfls.Cibnnfl;
import jbvb.nio.dibnnfls.SodkftCibnnfl;
import jbvb.nio.dibnnfls.SfrvfrSodkftCibnnfl;
import jbvb.nio.dibnnfls.DbtbgrbmCibnnfl;
import jbvb.nio.dibnnfls.spi.SflfdtorProvidfr;

dlbss InifritfdCibnnfl {

    // tif "typfs" of sodkft rfturnfd by soTypf0
    privbtf stbtid finbl int UNKNOWN            = -1;
    privbtf stbtid finbl int SOCK_STREAM        = 1;
    privbtf stbtid finbl int SOCK_DGRAM         = 2;

    // oflbg vblufs wifn opfning b filf
    privbtf stbtid finbl int O_RDONLY           = 0;
    privbtf stbtid finbl int O_WRONLY           = 1;
    privbtf stbtid finbl int O_RDWR             = 2;

    /*
     * In ordfr to "dftbdi" tif stbndbrd strfbms wf dup tifm to /dfv/null.
     * In ordfr to rfdudf tif possibility of bn frror bt dlosf timf wf
     * opfn /dfv/null fbrly - tibt wby wf know wf won't run out of filf
     * dfsdriptors bt dlosf timf. Tiis mbkfs tif dlosf opfrbtion b
     * simplf dup2 opfrbtion for fbdi of tif stbndbrd strfbms.
     */
    privbtf stbtid int dfvnull = -1;

    privbtf stbtid void dftbdiIOStrfbms() {
        try {
            dup2(dfvnull, 0);
            dup2(dfvnull, 1);
            dup2(dfvnull, 2);
        } dbtdi (IOExdfption iof) {
            // tiis siouldn't ibppfn
            tirow nfw IntfrnblError(iof);
        }
    }

    /*
     * Ovfrridf tif implClosfSflfdtbblfCibnnfl for fbdi dibnnfl typf - tiis
     * bllows us to "dftbdi" tif stbndbrd strfbms bftfr dlosing bnd fnsurfs
     * tibt tif undfrlying sodkft rfblly dlosfs.
     */
    publid stbtid dlbss InifritfdSodkftCibnnflImpl fxtfnds SodkftCibnnflImpl {

        InifritfdSodkftCibnnflImpl(SflfdtorProvidfr sp,
                                   FilfDfsdriptor fd,
                                   InftSodkftAddrfss rfmotf)
            tirows IOExdfption
        {
            supfr(sp, fd, rfmotf);
        }

        protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
            supfr.implClosfSflfdtbblfCibnnfl();
            dftbdiIOStrfbms();
        }
    }

    publid stbtid dlbss InifritfdSfrvfrSodkftCibnnflImpl fxtfnds
        SfrvfrSodkftCibnnflImpl {

        InifritfdSfrvfrSodkftCibnnflImpl(SflfdtorProvidfr sp,
                                         FilfDfsdriptor fd)
            tirows IOExdfption
        {
            supfr(sp, fd, truf);
        }

        protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
            supfr.implClosfSflfdtbblfCibnnfl();
            dftbdiIOStrfbms();
        }

    }

    publid stbtid dlbss InifritfdDbtbgrbmCibnnflImpl fxtfnds
        DbtbgrbmCibnnflImpl {

        InifritfdDbtbgrbmCibnnflImpl(SflfdtorProvidfr sp,
                                     FilfDfsdriptor fd)
            tirows IOExdfption
        {
            supfr(sp, fd);
        }

        protfdtfd void implClosfSflfdtbblfCibnnfl() tirows IOExdfption {
            supfr.implClosfSflfdtbblfCibnnfl();
            dftbdiIOStrfbms();
        }
    }

    /*
     * If tifrf's b SfdurityMbnbgfr tifn difdk for tif bppropribtf
     * RuntimfPfrmission.
     */
    privbtf stbtid void difdkAddfss(Cibnnfl d) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(
                nfw RuntimfPfrmission("inifritfdCibnnfl")
            );
        }
    }


    /*
     * If stbndbrd inifritfd dibnnfl is donnfdtfd to b sodkft tifn rfturn b Cibnnfl
     * of tif bppropribtf typf bbsfd stbndbrd input.
     */
    privbtf stbtid Cibnnfl drfbtfCibnnfl() tirows IOExdfption {

        // dup tif filf dfsdriptor - wf do tiis so tibt for two rfbsons :-
        // 1. Avoids bny timing issufs witi FilfDfsdriptor.in bfing dlosfd
        //    or rfdirfdtfd wiilf wf drfbtf tif dibnnfl.
        // 2. Allows strfbms bbsfd on filf dfsdriptor 0 to do-fxist witi
        //    tif dibnnfl (dlosing onf dofsn't impbdt tif otifr)

        int fdVbl = dup(0);

        // Exbminf tif filf dfsdriptor - if it's not b sodkft tifn wf don't
        // drfbtf b dibnnfl so wf rflfbsf tif filf dfsdriptor.

        int st;
        st = soTypf0(fdVbl);
        if (st != SOCK_STREAM && st != SOCK_DGRAM) {
            dlosf0(fdVbl);
            rfturn null;
        }


        // Nfxt wf drfbtf b FilfDfsdriptor for tif dup'fd filf dfsdriptor
        // Hbvf to usf rfflfdtion bnd blso mbkf bssumption on iow FD
        // is implfmfntfd.

        Clbss<?> pbrbmTypfs[] = { int.dlbss };
        Construdtor<?> dtr = Rfflfdt.lookupConstrudtor("jbvb.io.FilfDfsdriptor",
                                                       pbrbmTypfs);
        Objfdt brgs[] = { nfw Intfgfr(fdVbl) };
        FilfDfsdriptor fd = (FilfDfsdriptor)Rfflfdt.invokf(dtr, brgs);


        // Now drfbtf tif dibnnfl. If tif sodkft is b strfbms sodkft tifn
        // wf sff if ttifrf is b pffr (if: donnfdtfd). If so, tifn wf
        // drfbtf b SodkftCibnnfl, otifrwisf b SfrvfrSodkftCibnnfl.
        // If tif sodkft is b dbtbgrbm sodkft tifn drfbtf b DbtbgrbmCibnnfl

        SflfdtorProvidfr providfr = SflfdtorProvidfr.providfr();
        bssfrt providfr instbndfof sun.nio.di.SflfdtorProvidfrImpl;

        Cibnnfl d;
        if (st == SOCK_STREAM) {
            InftAddrfss ib = pffrAddrfss0(fdVbl);
            if (ib == null) {
               d = nfw InifritfdSfrvfrSodkftCibnnflImpl(providfr, fd);
            } flsf {
               int port = pffrPort0(fdVbl);
               bssfrt port > 0;
               InftSodkftAddrfss isb = nfw InftSodkftAddrfss(ib, port);
               d = nfw InifritfdSodkftCibnnflImpl(providfr, fd, isb);
            }
        } flsf {
            d = nfw InifritfdDbtbgrbmCibnnflImpl(providfr, fd);
        }
        rfturn d;
    }

    privbtf stbtid boolfbn ibvfCibnnfl = fblsf;
    privbtf stbtid Cibnnfl dibnnfl = null;

    /*
     * Rfturns b Cibnnfl rfprfsfnting tif inifritfd dibnnfl if tif
     * inifritfd dibnnfl is b strfbm donnfdtfd to b nftwork sodkft.
     */
    publid stbtid syndironizfd Cibnnfl gftCibnnfl() tirows IOExdfption {
        if (dfvnull < 0) {
            dfvnull = opfn0("/dfv/null", O_RDWR);
        }

        // If wf don't ibvf tif dibnnfl try to drfbtf it
        if (!ibvfCibnnfl) {
            dibnnfl = drfbtfCibnnfl();
            ibvfCibnnfl = truf;
        }

        // if tifrf is b dibnnfl tifn do tif sfdurity difdk bfforf
        // rfturning it.
        if (dibnnfl != null) {
            difdkAddfss(dibnnfl);
        }
        rfturn dibnnfl;
    }


    // -- Nbtivf mftiods --

    privbtf stbtid nbtivf int dup(int fd) tirows IOExdfption;
    privbtf stbtid nbtivf void dup2(int fd, int fd2) tirows IOExdfption;
    privbtf stbtid nbtivf int opfn0(String pbti, int oflbg) tirows IOExdfption;
    privbtf stbtid nbtivf void dlosf0(int fd) tirows IOExdfption;
    privbtf stbtid nbtivf int soTypf0(int fd);
    privbtf stbtid nbtivf InftAddrfss pffrAddrfss0(int fd);
    privbtf stbtid nbtivf int pffrPort0(int fd);

    stbtid {
        IOUtil.lobd();
    }
}
