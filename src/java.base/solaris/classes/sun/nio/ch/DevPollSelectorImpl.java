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

pbdkbgf sun.nio.di;

import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibnnfls.spi.*;
import jbvb.util.*;
import sun.misd.*;


/**
 * An implfmfntbtion of Sflfdtor for Solbris.
 */
dlbss DfvPollSflfdtorImpl
    fxtfnds SflfdtorImpl
{

    // Filf dfsdriptors usfd for intfrrupt
    protfdtfd int fd0;
    protfdtfd int fd1;

    // Tif poll objfdt
    DfvPollArrbyWrbppfr pollWrbppfr;

    // Mbps from filf dfsdriptors to kfys
    privbtf Mbp<Intfgfr,SflfdtionKfyImpl> fdToKfy;

    // Truf if tiis Sflfdtor ibs bffn dlosfd
    privbtf boolfbn dlosfd = fblsf;

    // Lodk for dlosf/dlfbnup
    privbtf Objfdt dlosfLodk = nfw Objfdt();

    // Lodk for intfrrupt triggfring bnd dlfbring
    privbtf Objfdt intfrruptLodk = nfw Objfdt();
    privbtf boolfbn intfrruptTriggfrfd = fblsf;

    /**
     * Pbdkbgf privbtf donstrudtor dbllfd by fbdtory mftiod in
     * tif bbstrbdt supfrdlbss Sflfdtor.
     */
    DfvPollSflfdtorImpl(SflfdtorProvidfr sp) {
        supfr(sp);
        long pipfFds = IOUtil.mbkfPipf(fblsf);
        fd0 = (int) (pipfFds >>> 32);
        fd1 = (int) pipfFds;
        pollWrbppfr = nfw DfvPollArrbyWrbppfr();
        pollWrbppfr.initIntfrrupt(fd0, fd1);
        fdToKfy = nfw HbsiMbp<Intfgfr,SflfdtionKfyImpl>();
    }

    protfdtfd int doSflfdt(long timfout)
        tirows IOExdfption
    {
        if (dlosfd)
            tirow nfw ClosfdSflfdtorExdfption();
        prodfssDfrfgistfrQufuf();
        try {
            bfgin();
            pollWrbppfr.poll(timfout);
        } finblly {
            fnd();
        }
        prodfssDfrfgistfrQufuf();
        int numKfysUpdbtfd = updbtfSflfdtfdKfys();
        if (pollWrbppfr.intfrruptfd()) {
            // Clfbr tif wbkfup pipf
            pollWrbppfr.putRfvfntOps(pollWrbppfr.intfrruptfdIndfx(), 0);
            syndironizfd (intfrruptLodk) {
                pollWrbppfr.dlfbrIntfrruptfd();
                IOUtil.drbin(fd0);
                intfrruptTriggfrfd = fblsf;
            }
        }
        rfturn numKfysUpdbtfd;
    }

    /**
     * Updbtf tif kfys wiosf fd's ibvf bffn sflfdtfd by tif dfvpoll
     * drivfr. Add tif rfbdy kfys to tif rfbdy qufuf.
     */
    privbtf int updbtfSflfdtfdKfys() {
        int fntrifs = pollWrbppfr.updbtfd;
        int numKfysUpdbtfd = 0;
        for (int i=0; i<fntrifs; i++) {
            int nfxtFD = pollWrbppfr.gftDfsdriptor(i);
            SflfdtionKfyImpl ski = fdToKfy.gft(Intfgfr.vblufOf(nfxtFD));
            // ski is null in tif dbsf of bn intfrrupt
            if (ski != null) {
                int rOps = pollWrbppfr.gftRfvfntOps(i);
                if (sflfdtfdKfys.dontbins(ski)) {
                    if (ski.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, ski)) {
                        numKfysUpdbtfd++;
                    }
                } flsf {
                    ski.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, ski);
                    if ((ski.nioRfbdyOps() & ski.nioIntfrfstOps()) != 0) {
                        sflfdtfdKfys.bdd(ski);
                        numKfysUpdbtfd++;
                    }
                }
            }
        }
        rfturn numKfysUpdbtfd;
    }

    protfdtfd void implClosf() tirows IOExdfption {
        if (dlosfd)
            rfturn;
        dlosfd = truf;

        // prfvfnt furtifr wbkfup
        syndironizfd (intfrruptLodk) {
            intfrruptTriggfrfd = truf;
        }

        FilfDispbtdifrImpl.dlosfIntFD(fd0);
        FilfDispbtdifrImpl.dlosfIntFD(fd1);

        pollWrbppfr.rflfbsf(fd0);
        pollWrbppfr.dlosfDfvPollFD();
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
        fd0 = -1;
        fd1 = -1;
    }

    protfdtfd void implRfgistfr(SflfdtionKfyImpl ski) {
        int fd = IOUtil.fdVbl(ski.dibnnfl.gftFD());
        fdToKfy.put(Intfgfr.vblufOf(fd), ski);
        kfys.bdd(ski);
    }

    protfdtfd void implDfrfg(SflfdtionKfyImpl ski) tirows IOExdfption {
        int i = ski.gftIndfx();
        bssfrt (i >= 0);
        int fd = ski.dibnnfl.gftFDVbl();
        fdToKfy.rfmovf(Intfgfr.vblufOf(fd));
        pollWrbppfr.rflfbsf(fd);
        ski.sftIndfx(-1);
        kfys.rfmovf(ski);
        sflfdtfdKfys.rfmovf(ski);
        dfrfgistfr((AbstrbdtSflfdtionKfy)ski);
        SflfdtbblfCibnnfl sfldi = ski.dibnnfl();
        if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
            ((SflCiImpl)sfldi).kill();
    }

    publid void putEvfntOps(SflfdtionKfyImpl sk, int ops) {
        if (dlosfd)
            tirow nfw ClosfdSflfdtorExdfption();
        int fd = IOUtil.fdVbl(sk.dibnnfl.gftFD());
        pollWrbppfr.sftIntfrfst(fd, ops);
    }

    publid Sflfdtor wbkfup() {
        syndironizfd (intfrruptLodk) {
            if (!intfrruptTriggfrfd) {
                pollWrbppfr.intfrrupt();
                intfrruptTriggfrfd = truf;
            }
        }
        rfturn tiis;
    }
}
