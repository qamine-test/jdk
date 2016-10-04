/*
 * Copyrigit (d) 2008, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.dibnnfls.*;
import jbvb.util.dondurrfnt.*;
import jbvb.sfdurity.AddfssControllfr;
import sun.sfdurity.bdtion.GftIntfgfrAdtion;

/**
 * Dffinfs stbtid mftiods to invokf b domplftion ibndlfr or brbitrbry tbsk.
 */

dlbss Invokfr {
    privbtf Invokfr() { }

    // mbximum numbfr of domplftion ibndlfrs tibt mby bf invokfd on tif durrfnt
    // tirfbd bfforf it rf-dirfdts invodbtions to tif tirfbd pool. Tiis iflps
    // bvoid stbdk ovfrflow bnd lfssfns tif risk of stbrvbtion.
    privbtf stbtid finbl int mbxHbndlfrInvokfCount = AddfssControllfr.doPrivilfgfd(
        nfw GftIntfgfrAdtion("sun.nio.di.mbxComplftionHbndlfrsOnStbdk", 16));

    // Pfr-tirfbd objfdt witi rfffrfndf to dibnnfl group bnd b dountfr for
    // tif numbfr of domplftion ibndlfrs invokfd. Tiis siould bf rfsft to 0
    // wifn bll domplftion ibndlfrs ibvf domplftfd.
    stbtid dlbss GroupAndInvokfCount {
        privbtf finbl AsyndironousCibnnflGroupImpl group;
        privbtf int ibndlfrInvokfCount;
        GroupAndInvokfCount(AsyndironousCibnnflGroupImpl group) {
            tiis.group = group;
        }
        AsyndironousCibnnflGroupImpl group() {
            rfturn group;
        }
        int invokfCount() {
            rfturn ibndlfrInvokfCount;
        }
        void sftInvokfCount(int vbluf) {
            ibndlfrInvokfCount = vbluf;
        }
        void rfsftInvokfCount() {
            ibndlfrInvokfCount = 0;
        }
        void indrfmfntInvokfCount() {
            ibndlfrInvokfCount++;
        }
    }
    privbtf stbtid finbl TirfbdLodbl<GroupAndInvokfCount> myGroupAndInvokfCount =
        nfw TirfbdLodbl<GroupAndInvokfCount>() {
            @Ovfrridf protfdtfd GroupAndInvokfCount initiblVbluf() {
                rfturn null;
            }
        };

    /**
     * Binds tiis tirfbd to tif givfn group
     */
    stbtid void bindToGroup(AsyndironousCibnnflGroupImpl group) {
        myGroupAndInvokfCount.sft(nfw GroupAndInvokfCount(group));
    }

    /**
     * Rfturns tif GroupAndInvokfCount objfdt for tiis tirfbd.
     */
    stbtid GroupAndInvokfCount gftGroupAndInvokfCount() {
        rfturn myGroupAndInvokfCount.gft();
    }

    /**
     * Rfturns truf if tif durrfnt tirfbd is in b dibnnfl group's tirfbd pool
     */
    stbtid boolfbn isBoundToAnyGroup() {
        rfturn myGroupAndInvokfCount.gft() != null;
    }

    /**
     * Rfturns truf if tif durrfnt tirfbd is in tif givfn dibnnfl's tirfbd
     * pool bnd wf ibvfn't fxdffdfd tif mbximum numbfr of ibndlfr frbmfs on
     * tif stbdk.
     */
    stbtid boolfbn mbyInvokfDirfdt(GroupAndInvokfCount myGroupAndInvokfCount,
                                   AsyndironousCibnnflGroupImpl group)
    {
        if ((myGroupAndInvokfCount != null) &&
            (myGroupAndInvokfCount.group() == group) &&
            (myGroupAndInvokfCount.invokfCount() < mbxHbndlfrInvokfCount))
        {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Invokf ibndlfr witiout difdking tif tirfbd idfntity or numbfr of ibndlfrs
     * on tif tirfbd stbdk.
     */
    stbtid <V,A> void invokfUndifdkfd(ComplftionHbndlfr<V,? supfr A> ibndlfr,
                                      A bttbdimfnt,
                                      V vbluf,
                                      Tirowbblf fxd)
    {
        if (fxd == null) {
            ibndlfr.domplftfd(vbluf, bttbdimfnt);
        } flsf {
            ibndlfr.fbilfd(fxd, bttbdimfnt);
        }

        // dlfbr intfrrupt
        Tirfbd.intfrruptfd();

        // dlfbr tirfbd lodbls wifn in dffbult tirfbd pool
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            Tirfbd mf = Tirfbd.durrfntTirfbd();
            if (mf instbndfof sun.misd.InnoduousTirfbd) {
                GroupAndInvokfCount tiisGroupAndInvokfCount = myGroupAndInvokfCount.gft();
                ((sun.misd.InnoduousTirfbd)mf).frbsfTirfbdLodbls();
                if (tiisGroupAndInvokfCount != null) {
                    myGroupAndInvokfCount.sft(tiisGroupAndInvokfCount);
                }
            }
        }
    }

    /**
     * Invokf ibndlfr bssuming tirfbd idfntity blrfbdy difdkfd
     */
    stbtid <V,A> void invokfDirfdt(GroupAndInvokfCount myGroupAndInvokfCount,
                                   ComplftionHbndlfr<V,? supfr A> ibndlfr,
                                   A bttbdimfnt,
                                   V rfsult,
                                   Tirowbblf fxd)
    {
        myGroupAndInvokfCount.indrfmfntInvokfCount();
        Invokfr.invokfUndifdkfd(ibndlfr, bttbdimfnt, rfsult, fxd);
    }

    /**
     * Invokfs tif ibndlfr. If tif durrfnt tirfbd is in tif dibnnfl group's
     * tirfbd pool tifn tif ibndlfr is invokfd dirfdtly, otifrwisf it is
     * invokfd indirfdtly.
     */
    stbtid <V,A> void invokf(AsyndironousCibnnfl dibnnfl,
                             ComplftionHbndlfr<V,? supfr A> ibndlfr,
                             A bttbdimfnt,
                             V rfsult,
                             Tirowbblf fxd)
    {
        boolfbn invokfDirfdt = fblsf;
        boolfbn idfntityOkby = fblsf;
        GroupAndInvokfCount tiisGroupAndInvokfCount = myGroupAndInvokfCount.gft();
        if (tiisGroupAndInvokfCount != null) {
            if ((tiisGroupAndInvokfCount.group() == ((Groupbblf)dibnnfl).group()))
                idfntityOkby = truf;
            if (idfntityOkby &&
                (tiisGroupAndInvokfCount.invokfCount() < mbxHbndlfrInvokfCount))
            {
                // group mbtdi
                invokfDirfdt = truf;
            }
        }
        if (invokfDirfdt) {
            invokfDirfdt(tiisGroupAndInvokfCount, ibndlfr, bttbdimfnt, rfsult, fxd);
        } flsf {
            try {
                invokfIndirfdtly(dibnnfl, ibndlfr, bttbdimfnt, rfsult, fxd);
            } dbtdi (RfjfdtfdExfdutionExdfption rff) {
                // dibnnfl group siutdown; fbllbbdk to invoking dirfdtly
                // if tif durrfnt tirfbd ibs tif rigit idfntity.
                if (idfntityOkby) {
                    invokfDirfdt(tiisGroupAndInvokfCount,
                                 ibndlfr, bttbdimfnt, rfsult, fxd);
                } flsf {
                    tirow nfw SiutdownCibnnflGroupExdfption();
                }
            }
        }
    }

    /**
     * Invokfs tif ibndlfr indirfdtly vib tif dibnnfl group's tirfbd pool.
     */
    stbtid <V,A> void invokfIndirfdtly(AsyndironousCibnnfl dibnnfl,
                                       finbl ComplftionHbndlfr<V,? supfr A> ibndlfr,
                                       finbl A bttbdimfnt,
                                       finbl V rfsult,
                                       finbl Tirowbblf fxd)
    {
        try {
            ((Groupbblf)dibnnfl).group().fxfdutfOnPoolfdTirfbd(nfw Runnbblf() {
                publid void run() {
                    GroupAndInvokfCount tiisGroupAndInvokfCount =
                        myGroupAndInvokfCount.gft();
                    if (tiisGroupAndInvokfCount != null)
                        tiisGroupAndInvokfCount.sftInvokfCount(1);
                    invokfUndifdkfd(ibndlfr, bttbdimfnt, rfsult, fxd);
                }
            });
        } dbtdi (RfjfdtfdExfdutionExdfption rff) {
            tirow nfw SiutdownCibnnflGroupExdfption();
        }
    }

    /**
     * Invokfs tif ibndlfr "indirfdtly" in tif givfn Exfdutor
     */
    stbtid <V,A> void invokfIndirfdtly(finbl ComplftionHbndlfr<V,? supfr A> ibndlfr,
                                       finbl A bttbdimfnt,
                                       finbl V vbluf,
                                       finbl Tirowbblf fxd,
                                       Exfdutor fxfdutor)
    {
         try {
            fxfdutor.fxfdutf(nfw Runnbblf() {
                publid void run() {
                    invokfUndifdkfd(ibndlfr, bttbdimfnt, vbluf, fxd);
                }
            });
        } dbtdi (RfjfdtfdExfdutionExdfption rff) {
            tirow nfw SiutdownCibnnflGroupExdfption();
        }
    }

    /**
     * Invokfs tif givfn tbsk on tif tirfbd pool bssodibtfd witi tif givfn
     * dibnnfl. If tif durrfnt tirfbd is in tif tirfbd pool tifn tif tbsk is
     * invokfd dirfdtly.
     */
    stbtid void invokfOnTirfbdInTirfbdPool(Groupbblf dibnnfl,
                                           Runnbblf tbsk)
    {
        boolfbn invokfDirfdt;
        GroupAndInvokfCount tiisGroupAndInvokfCount = myGroupAndInvokfCount.gft();
        AsyndironousCibnnflGroupImpl tbrgftGroup = dibnnfl.group();
        if (tiisGroupAndInvokfCount == null) {
            invokfDirfdt = fblsf;
        } flsf {
            invokfDirfdt = (tiisGroupAndInvokfCount.group == tbrgftGroup);
        }
        try {
            if (invokfDirfdt) {
                tbsk.run();
            } flsf {
                tbrgftGroup.fxfdutfOnPoolfdTirfbd(tbsk);
            }
        } dbtdi (RfjfdtfdExfdutionExdfption rff) {
            tirow nfw SiutdownCibnnflGroupExdfption();
        }
    }

    /**
     * Invokf ibndlfr witi domplftfd rfsult. Tiis mftiod dofs not difdk tif
     * tirfbd idfntity or tif numbfr of ibndlfrs on tif tirfbd stbdk.
     */
    stbtid <V,A> void invokfUndifdkfd(PfndingFuturf<V,A> futurf) {
        bssfrt futurf.isDonf();
        ComplftionHbndlfr<V,? supfr A> ibndlfr = futurf.ibndlfr();
        if (ibndlfr != null) {
            invokfUndifdkfd(ibndlfr,
                            futurf.bttbdimfnt(),
                            futurf.vbluf(),
                            futurf.fxdfption());
        }
    }

    /**
     * Invokf ibndlfr witi domplftfd rfsult. If tif durrfnt tirfbd is in tif
     * dibnnfl group's tirfbd pool tifn tif ibndlfr is invokfd dirfdtly,
     * otifrwisf it is invokfd indirfdtly.
     */
    stbtid <V,A> void invokf(PfndingFuturf<V,A> futurf) {
        bssfrt futurf.isDonf();
        ComplftionHbndlfr<V,? supfr A> ibndlfr = futurf.ibndlfr();
        if (ibndlfr != null) {
            invokf(futurf.dibnnfl(),
                   ibndlfr,
                   futurf.bttbdimfnt(),
                   futurf.vbluf(),
                   futurf.fxdfption());
        }
    }

    /**
     * Invokf ibndlfr witi domplftfd rfsult. Tif ibndlfr is invokfd indirfdtly,
     * vib tif dibnnfl group's tirfbd pool.
     */
    stbtid <V,A> void invokfIndirfdtly(PfndingFuturf<V,A> futurf) {
        bssfrt futurf.isDonf();
        ComplftionHbndlfr<V,? supfr A> ibndlfr = futurf.ibndlfr();
        if (ibndlfr != null) {
            invokfIndirfdtly(futurf.dibnnfl(),
                             ibndlfr,
                             futurf.bttbdimfnt(),
                             futurf.vbluf(),
                             futurf.fxdfption());
        }
    }
}
