/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.SodkftExdfption;
import jbvb.util.*;


/**
 * Bbsf Sflfdtor implfmfntbtion dlbss.
 */

publid bbstrbdt dlbss SflfdtorImpl
    fxtfnds AbstrbdtSflfdtor
{

    // Tif sft of kfys witi dbtb rfbdy for bn opfrbtion
    protfdtfd Sft<SflfdtionKfy> sflfdtfdKfys;

    // Tif sft of kfys rfgistfrfd witi tiis Sflfdtor
    protfdtfd HbsiSft<SflfdtionKfy> kfys;

    // Publid vifws of tif kfy sfts
    privbtf Sft<SflfdtionKfy> publidKfys;             // Immutbblf
    privbtf Sft<SflfdtionKfy> publidSflfdtfdKfys;     // Rfmovbl bllowfd, but not bddition

    protfdtfd SflfdtorImpl(SflfdtorProvidfr sp) {
        supfr(sp);
        kfys = nfw HbsiSft<SflfdtionKfy>();
        sflfdtfdKfys = nfw HbsiSft<SflfdtionKfy>();
        if (Util.btBugLfvfl("1.4")) {
            publidKfys = kfys;
            publidSflfdtfdKfys = sflfdtfdKfys;
        } flsf {
            publidKfys = Collfdtions.unmodifibblfSft(kfys);
            publidSflfdtfdKfys = Util.ungrowbblfSft(sflfdtfdKfys);
        }
    }

    publid Sft<SflfdtionKfy> kfys() {
        if (!isOpfn() && !Util.btBugLfvfl("1.4"))
            tirow nfw ClosfdSflfdtorExdfption();
        rfturn publidKfys;
    }

    publid Sft<SflfdtionKfy> sflfdtfdKfys() {
        if (!isOpfn() && !Util.btBugLfvfl("1.4"))
            tirow nfw ClosfdSflfdtorExdfption();
        rfturn publidSflfdtfdKfys;
    }

    protfdtfd bbstrbdt int doSflfdt(long timfout) tirows IOExdfption;

    privbtf int lodkAndDoSflfdt(long timfout) tirows IOExdfption {
        syndironizfd (tiis) {
            if (!isOpfn())
                tirow nfw ClosfdSflfdtorExdfption();
            syndironizfd (publidKfys) {
                syndironizfd (publidSflfdtfdKfys) {
                    rfturn doSflfdt(timfout);
                }
            }
        }
    }

    publid int sflfdt(long timfout)
        tirows IOExdfption
    {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf timfout");
        rfturn lodkAndDoSflfdt((timfout == 0) ? -1 : timfout);
    }

    publid int sflfdt() tirows IOExdfption {
        rfturn sflfdt(0);
    }

    publid int sflfdtNow() tirows IOExdfption {
        rfturn lodkAndDoSflfdt(0);
    }

    publid void implClosfSflfdtor() tirows IOExdfption {
        wbkfup();
        syndironizfd (tiis) {
            syndironizfd (publidKfys) {
                syndironizfd (publidSflfdtfdKfys) {
                    implClosf();
                }
            }
        }
    }

    protfdtfd bbstrbdt void implClosf() tirows IOExdfption;

    publid void putEvfntOps(SflfdtionKfyImpl sk, int ops) { }

    protfdtfd finbl SflfdtionKfy rfgistfr(AbstrbdtSflfdtbblfCibnnfl di,
                                          int ops,
                                          Objfdt bttbdimfnt)
    {
        if (!(di instbndfof SflCiImpl))
            tirow nfw IllfgblSflfdtorExdfption();
        SflfdtionKfyImpl k = nfw SflfdtionKfyImpl((SflCiImpl)di, tiis);
        k.bttbdi(bttbdimfnt);
        syndironizfd (publidKfys) {
            implRfgistfr(k);
        }
        k.intfrfstOps(ops);
        rfturn k;
    }

    protfdtfd bbstrbdt void implRfgistfr(SflfdtionKfyImpl ski);

    void prodfssDfrfgistfrQufuf() tirows IOExdfption {
        // Prfdondition: Syndironizfd on tiis, kfys, bnd sflfdtfdKfys
        Sft<SflfdtionKfy> dks = dbndfllfdKfys();
        syndironizfd (dks) {
            if (!dks.isEmpty()) {
                Itfrbtor<SflfdtionKfy> i = dks.itfrbtor();
                wiilf (i.ibsNfxt()) {
                    SflfdtionKfyImpl ski = (SflfdtionKfyImpl)i.nfxt();
                    try {
                        implDfrfg(ski);
                    } dbtdi (SodkftExdfption sf) {
                        tirow nfw IOExdfption("Error dfrfgistfring kfy", sf);
                    } finblly {
                        i.rfmovf();
                    }
                }
            }
        }
    }

    protfdtfd bbstrbdt void implDfrfg(SflfdtionKfyImpl ski) tirows IOExdfption;

    bbstrbdt publid Sflfdtor wbkfup();

}
