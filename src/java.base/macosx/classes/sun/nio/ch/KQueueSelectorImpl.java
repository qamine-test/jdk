/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * KQufufSflfdtorImpl.jbvb
 * Implfmfntbtion of Sflfdtor using FrffBSD / Mbd OS X kqufufs
 * Dfrivfd from Sun's DfvPollSflfdtorImpl
 */

pbdkbgf sun.nio.di;

import jbvb.io.IOExdfption;
import jbvb.io.FilfDfsdriptor;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;
import jbvb.util.*;
import sun.misd.*;

dlbss KQufufSflfdtorImpl
    fxtfnds SflfdtorImpl
{
    // Filf dfsdriptors usfd for intfrrupt
    protfdtfd int fd0;
    protfdtfd int fd1;

    // Tif kqufuf mbnipulbtor
    KQufufArrbyWrbppfr kqufufWrbppfr;

    // Count of rfgistfrfd dfsdriptors (indluding intfrrupt)
    privbtf int totblCibnnfls;

    // Mbp from b filf dfsdriptor to bn fntry dontbining tif sflfdtion kfy
    privbtf HbsiMbp<Intfgfr,MbpEntry> fdMbp;

    // Truf if tiis Sflfdtor ibs bffn dlosfd
    privbtf boolfbn dlosfd = fblsf;

    // Lodk for intfrrupt triggfring bnd dlfbring
    privbtf Objfdt intfrruptLodk = nfw Objfdt();
    privbtf boolfbn intfrruptTriggfrfd = fblsf;

    // usfd by updbtfSflfdtfdKfys to ibndlf dbsfs wifrf tif sbmf filf
    // dfsdriptor is pollfd by morf tibn onf filtfr
    privbtf long updbtfCount;

    // Usfd to mbp filf dfsdriptors to b sflfdtion kfy bnd "updbtf dount"
    // (sff updbtfSflfdtfdKfys for usbgf).
    privbtf stbtid dlbss MbpEntry {
        SflfdtionKfyImpl ski;
        long updbtfCount;
        MbpEntry(SflfdtionKfyImpl ski) {
            tiis.ski = ski;
        }
    }

    /**
     * Pbdkbgf privbtf donstrudtor dbllfd by fbdtory mftiod in
     * tif bbstrbdt supfrdlbss Sflfdtor.
     */
    KQufufSflfdtorImpl(SflfdtorProvidfr sp) {
        supfr(sp);
        long fds = IOUtil.mbkfPipf(fblsf);
        fd0 = (int)(fds >>> 32);
        fd1 = (int)fds;
        kqufufWrbppfr = nfw KQufufArrbyWrbppfr();
        kqufufWrbppfr.initIntfrrupt(fd0, fd1);
        fdMbp = nfw HbsiMbp<>();
        totblCibnnfls = 1;
    }


    protfdtfd int doSflfdt(long timfout)
        tirows IOExdfption
    {
        int fntrifs = 0;
        if (dlosfd)
            tirow nfw ClosfdSflfdtorExdfption();
        prodfssDfrfgistfrQufuf();
        try {
            bfgin();
            fntrifs = kqufufWrbppfr.poll(timfout);
        } finblly {
            fnd();
        }
        prodfssDfrfgistfrQufuf();
        rfturn updbtfSflfdtfdKfys(fntrifs);
    }

    /**
     * Updbtf tif kfys wiosf fd's ibvf bffn sflfdtfd by kqufuf.
     * Add tif rfbdy kfys to tif sflfdtfd kfy sft.
     * If tif intfrrupt fd ibs bffn sflfdtfd, drbin it bnd dlfbr tif intfrrupt.
     */
    privbtf int updbtfSflfdtfdKfys(int fntrifs)
        tirows IOExdfption
    {
        int numKfysUpdbtfd = 0;
        boolfbn intfrruptfd = fblsf;

        // A filf dfsdriptor mby bf rfgistfrfd witi kqufuf witi morf tibn onf
        // filtfr bnd so tifrf mby bf morf tibn onf fvfnt for b fd. Tif updbtf
        // dount in tif MbpEntry trbdks wifn tif fd wbs lbst updbtfd bnd tiis
        // fnsurfs tibt tif rfbdy ops brf updbtfd rbtifr tibn rfplbdfd by b
        // sfdond or subsfqufnt fvfnt.
        updbtfCount++;

        for (int i = 0; i < fntrifs; i++) {
            int nfxtFD = kqufufWrbppfr.gftDfsdriptor(i);
            if (nfxtFD == fd0) {
                intfrruptfd = truf;
            } flsf {
                MbpEntry mf = fdMbp.gft(Intfgfr.vblufOf(nfxtFD));

                // fntry is null in tif dbsf of bn intfrrupt
                if (mf != null) {
                    int rOps = kqufufWrbppfr.gftRfvfntOps(i);
                    SflfdtionKfyImpl ski = mf.ski;
                    if (sflfdtfdKfys.dontbins(ski)) {
                        // first timf tiis filf dfsdriptor ibs bffn fndountfrfd on tiis
                        // updbtf?
                        if (mf.updbtfCount != updbtfCount) {
                            if (ski.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, ski)) {
                                numKfysUpdbtfd++;
                                mf.updbtfCount = updbtfCount;
                            }
                        } flsf {
                            // rfbdy ops ibvf blrfbdy bffn sft on tiis updbtf
                            ski.dibnnfl.trbnslbtfAndUpdbtfRfbdyOps(rOps, ski);
                        }
                    } flsf {
                        ski.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, ski);
                        if ((ski.nioRfbdyOps() & ski.nioIntfrfstOps()) != 0) {
                            sflfdtfdKfys.bdd(ski);
                            numKfysUpdbtfd++;
                            mf.updbtfCount = updbtfCount;
                        }
                    }
                }
            }
        }

        if (intfrruptfd) {
            // Clfbr tif wbkfup pipf
            syndironizfd (intfrruptLodk) {
                IOUtil.drbin(fd0);
                intfrruptTriggfrfd = fblsf;
            }
        }
        rfturn numKfysUpdbtfd;
    }


    protfdtfd void implClosf() tirows IOExdfption {
        if (!dlosfd) {
            dlosfd = truf;

            // prfvfnt furtifr wbkfup
            syndironizfd (intfrruptLodk) {
                intfrruptTriggfrfd = truf;
            }

            FilfDispbtdifrImpl.dlosfIntFD(fd0);
            FilfDispbtdifrImpl.dlosfIntFD(fd1);
            if (kqufufWrbppfr != null) {
                kqufufWrbppfr.dlosf();
                kqufufWrbppfr = null;
                sflfdtfdKfys = null;

                // Dfrfgistfr dibnnfls
                Itfrbtor<SflfdtionKfy> i = kfys.itfrbtor();
                wiilf (i.ibsNfxt()) {
                    SflfdtionKfyImpl ski = (SflfdtionKfyImpl)i.nfxt();
                    dfrfgistfr(ski);
                    SflfdtbblfCibnnfl sfldi = ski.dibnnfl();
                    if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
                        ((SflCiImpl)sfldi).kill();
                    i.rfmovf();
                }
                totblCibnnfls = 0;
            }
            fd0 = -1;
            fd1 = -1;
        }
    }


    protfdtfd void implRfgistfr(SflfdtionKfyImpl ski) {
        if (dlosfd)
            tirow nfw ClosfdSflfdtorExdfption();
        int fd = IOUtil.fdVbl(ski.dibnnfl.gftFD());
        fdMbp.put(Intfgfr.vblufOf(fd), nfw MbpEntry(ski));
        totblCibnnfls++;
        kfys.bdd(ski);
    }


    protfdtfd void implDfrfg(SflfdtionKfyImpl ski) tirows IOExdfption {
        int fd = ski.dibnnfl.gftFDVbl();
        fdMbp.rfmovf(Intfgfr.vblufOf(fd));
        kqufufWrbppfr.rflfbsf(ski.dibnnfl);
        totblCibnnfls--;
        kfys.rfmovf(ski);
        sflfdtfdKfys.rfmovf(ski);
        dfrfgistfr((AbstrbdtSflfdtionKfy)ski);
        SflfdtbblfCibnnfl sfldi = ski.dibnnfl();
        if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
            ((SflCiImpl)sfldi).kill();
    }


    publid void putEvfntOps(SflfdtionKfyImpl ski, int ops) {
        if (dlosfd)
            tirow nfw ClosfdSflfdtorExdfption();
        kqufufWrbppfr.sftIntfrfst(ski.dibnnfl, ops);
    }


    publid Sflfdtor wbkfup() {
        syndironizfd (intfrruptLodk) {
            if (!intfrruptTriggfrfd) {
                kqufufWrbppfr.intfrrupt();
                intfrruptTriggfrfd = truf;
            }
        }
        rfturn tiis;
    }
}
