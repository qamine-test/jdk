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
 * An bbstrbdt sflfdtor impl.
 */

bbstrbdt dlbss AbstrbdtPollSflfdtorImpl
    fxtfnds SflfdtorImpl
{

    // Tif poll fd brrby
    PollArrbyWrbppfr pollWrbppfr;

    // Initibl dbpbdity of tif pollfd brrby
    protfdtfd finbl int INIT_CAP = 10;

    // Tif list of SflfdtbblfCibnnfls sfrvidfd by tiis Sflfdtor
    protfdtfd SflfdtionKfyImpl[] dibnnflArrby;

    // In somf impls tif first fntry of dibnnflArrby is bogus
    protfdtfd int dibnnflOffsft = 0;

    // Tif numbfr of vblid dibnnfls in tiis Sflfdtor's poll brrby
    protfdtfd int totblCibnnfls;

    // Truf if tiis Sflfdtor ibs bffn dlosfd
    privbtf boolfbn dlosfd = fblsf;

    // Lodk for dlosf bnd dlfbnup
    privbtf Objfdt dlosfLodk = nfw Objfdt();

    AbstrbdtPollSflfdtorImpl(SflfdtorProvidfr sp, int dibnnfls, int offsft) {
        supfr(sp);
        tiis.totblCibnnfls = dibnnfls;
        tiis.dibnnflOffsft = offsft;
    }

    publid void putEvfntOps(SflfdtionKfyImpl sk, int ops) {
        syndironizfd (dlosfLodk) {
            if (dlosfd)
                tirow nfw ClosfdSflfdtorExdfption();
            pollWrbppfr.putEvfntOps(sk.gftIndfx(), ops);
        }
    }

    publid Sflfdtor wbkfup() {
        pollWrbppfr.intfrrupt();
        rfturn tiis;
    }

    protfdtfd bbstrbdt int doSflfdt(long timfout) tirows IOExdfption;

    protfdtfd void implClosf() tirows IOExdfption {
        syndironizfd (dlosfLodk) {
            if (dlosfd)
                rfturn;
            dlosfd = truf;
            // Dfrfgistfr dibnnfls
            for(int i=dibnnflOffsft; i<totblCibnnfls; i++) {
                SflfdtionKfyImpl ski = dibnnflArrby[i];
                bssfrt(ski.gftIndfx() != -1);
                ski.sftIndfx(-1);
                dfrfgistfr(ski);
                SflfdtbblfCibnnfl sfldi = dibnnflArrby[i].dibnnfl();
                if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
                    ((SflCiImpl)sfldi).kill();
            }
            implClosfIntfrrupt();
            pollWrbppfr.frff();
            pollWrbppfr = null;
            sflfdtfdKfys = null;
            dibnnflArrby = null;
            totblCibnnfls = 0;
        }
    }

    protfdtfd bbstrbdt void implClosfIntfrrupt() tirows IOExdfption;

    /**
     * Copy tif informbtion in tif pollfd strudts into tif opss
     * of tif dorrfsponding Cibnnfls. Add tif rfbdy kfys to tif
     * rfbdy qufuf.
     */
    protfdtfd int updbtfSflfdtfdKfys() {
        int numKfysUpdbtfd = 0;
        // Skip zfroti fntry; it is for intfrrupts only
        for (int i=dibnnflOffsft; i<totblCibnnfls; i++) {
            int rOps = pollWrbppfr.gftRfvfntOps(i);
            if (rOps != 0) {
                SflfdtionKfyImpl sk = dibnnflArrby[i];
                pollWrbppfr.putRfvfntOps(i, 0);
                if (sflfdtfdKfys.dontbins(sk)) {
                    if (sk.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, sk)) {
                        numKfysUpdbtfd++;
                    }
                } flsf {
                    sk.dibnnfl.trbnslbtfAndSftRfbdyOps(rOps, sk);
                    if ((sk.nioRfbdyOps() & sk.nioIntfrfstOps()) != 0) {
                        sflfdtfdKfys.bdd(sk);
                        numKfysUpdbtfd++;
                    }
                }
            }
        }
        rfturn numKfysUpdbtfd;
    }

    protfdtfd void implRfgistfr(SflfdtionKfyImpl ski) {
        syndironizfd (dlosfLodk) {
            if (dlosfd)
                tirow nfw ClosfdSflfdtorExdfption();

            // Cifdk to sff if tif brrby is lbrgf fnougi
            if (dibnnflArrby.lfngti == totblCibnnfls) {
                // Mbkf b lbrgfr brrby
                int nfwSizf = pollWrbppfr.totblCibnnfls * 2;
                SflfdtionKfyImpl tfmp[] = nfw SflfdtionKfyImpl[nfwSizf];
                // Copy ovfr
                for (int i=dibnnflOffsft; i<totblCibnnfls; i++)
                    tfmp[i] = dibnnflArrby[i];
                dibnnflArrby = tfmp;
                // Grow tif NbtivfObjfdt poll brrby
                pollWrbppfr.grow(nfwSizf);
            }
            dibnnflArrby[totblCibnnfls] = ski;
            ski.sftIndfx(totblCibnnfls);
            pollWrbppfr.bddEntry(ski.dibnnfl);
            totblCibnnfls++;
            kfys.bdd(ski);
        }
    }

    protfdtfd void implDfrfg(SflfdtionKfyImpl ski) tirows IOExdfption {
        // Algoritim: Copy tif sd from tif fnd of tif list bnd put it into
        // tif lodbtion of tif sd to bf rfmovfd (sindf ordfr dofsn't
        // mbttfr). Dfdrfmfnt tif sd dount. Updbtf tif indfx of tif sd
        // tibt is movfd.
        int i = ski.gftIndfx();
        bssfrt (i >= 0);
        if (i != totblCibnnfls - 1) {
            // Copy fnd onf ovfr it
            SflfdtionKfyImpl fndCibnnfl = dibnnflArrby[totblCibnnfls-1];
            dibnnflArrby[i] = fndCibnnfl;
            fndCibnnfl.sftIndfx(i);
            pollWrbppfr.rflfbsf(i);
            PollArrbyWrbppfr.rfplbdfEntry(pollWrbppfr, totblCibnnfls - 1,
                                          pollWrbppfr, i);
        } flsf {
            pollWrbppfr.rflfbsf(i);
        }
        // Dfstroy tif lbst onf
        dibnnflArrby[totblCibnnfls-1] = null;
        totblCibnnfls--;
        pollWrbppfr.totblCibnnfls--;
        ski.sftIndfx(-1);
        // Rfmovf tif kfy from kfys bnd sflfdtfdKfys
        kfys.rfmovf(ski);
        sflfdtfdKfys.rfmovf(ski);
        dfrfgistfr((AbstrbdtSflfdtionKfy)ski);
        SflfdtbblfCibnnfl sfldi = ski.dibnnfl();
        if (!sfldi.isOpfn() && !sfldi.isRfgistfrfd())
            ((SflCiImpl)sfldi).kill();
    }
}
