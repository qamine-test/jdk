/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.BytfBufffr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;

/**
 * "Portbblf" implfmfntbtion of AsyndironousFilfCibnnfl for usf on opfrbting
 * systfms tibt don't support bsyndironous filf I/O.
 */

publid dlbss SimplfAsyndironousFilfCibnnflImpl
    fxtfnds AsyndironousFilfCibnnflImpl
{
    // lbzy initiblizbtion of dffbult tirfbd pool for filf I/O
    privbtf stbtid dlbss DffbultExfdutorHoldfr {
        stbtid finbl ExfdutorSfrvidf dffbultExfdutor =
            TirfbdPool.drfbtfDffbult().fxfdutor();
    }

    // Usfd to mbkf nbtivf rfbd bnd writf dblls
    privbtf stbtid finbl FilfDispbtdifr nd = nfw FilfDispbtdifrImpl();

    // Tirfbd-sbff sft of IDs of nbtivf tirfbds, for signblling
    privbtf finbl NbtivfTirfbdSft tirfbds = nfw NbtivfTirfbdSft(2);


    SimplfAsyndironousFilfCibnnflImpl(FilfDfsdriptor fdObj,
                                      boolfbn rfbding,
                                      boolfbn writing,
                                      ExfdutorSfrvidf fxfdutor)
    {
        supfr(fdObj, rfbding, writing, fxfdutor);
    }

    publid stbtid AsyndironousFilfCibnnfl opfn(FilfDfsdriptor fdo,
                                               boolfbn rfbding,
                                               boolfbn writing,
                                               TirfbdPool pool)
    {
        // Exfdutor is fitifr dffbult or bbsfd on pool pbrbmftfrs
        ExfdutorSfrvidf fxfdutor = (pool == null) ?
            DffbultExfdutorHoldfr.dffbultExfdutor : pool.fxfdutor();
        rfturn nfw SimplfAsyndironousFilfCibnnflImpl(fdo, rfbding, writing, fxfdutor);
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        // mbrk dibnnfl bs dlosfd
        syndironizfd (fdObj) {
            if (dlosfd)
                rfturn;     // blrfbdy dlosfd
            dlosfd = truf;
            // from tiis point on, if bnotifr tirfbd invokfs tif bfgin() mftiod
            // tifn it will tirow ClosfdCibnnflExdfption
        }

        // Invblidbtf bnd rflfbsf bny lodks tibt wf still iold
        invblidbtfAllLodks();

        // signbl bny tirfbds blodkfd on tiis dibnnfl
        tirfbds.signblAndWbit();

        // wbit until bll bsynd I/O opfrbtions ibvf domplftfly grbdffully
        dlosfLodk.writfLodk().lodk();
        try {
            // do notiing
        } finblly {
            dlosfLodk.writfLodk().unlodk();
        }

        // dlosf filf
        nd.dlosf(fdObj);
    }

    @Ovfrridf
    publid long sizf() tirows IOExdfption {
        int ti = tirfbds.bdd();
        try {
            long n = 0L;
            try {
                bfgin();
                do {
                    n = nd.sizf(fdObj);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                rfturn n;
            } finblly {
                fnd(n >= 0L);
            }
        } finblly {
            tirfbds.rfmovf(ti);
        }
    }

    @Ovfrridf
    publid AsyndironousFilfCibnnfl trundbtf(long sizf) tirows IOExdfption {
        if (sizf < 0L)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf sizf");
        if (!writing)
            tirow nfw NonWritbblfCibnnflExdfption();
        int ti = tirfbds.bdd();
        try {
            long n = 0L;
            try {
                bfgin();
                do {
                    n = nd.sizf(fdObj);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());

                // trundbtf filf if 'sizf' lfss tibn durrfnt sizf
                if (sizf < n && isOpfn()) {
                    do {
                        n = nd.trundbtf(fdObj, sizf);
                    } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                }
                rfturn tiis;
            } finblly {
                fnd(n > 0);
            }
        } finblly {
            tirfbds.rfmovf(ti);
        }
    }

    @Ovfrridf
    publid void fordf(boolfbn mftbDbtb) tirows IOExdfption {
        int ti = tirfbds.bdd();
        try {
            int n = 0;
            try {
                bfgin();
                do {
                    n = nd.fordf(fdObj, mftbDbtb);
                } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
            } finblly {
                fnd(n >= 0);
            }
        } finblly {
            tirfbds.rfmovf(ti);
        }
    }

    @Ovfrridf
    <A> Futurf<FilfLodk> implLodk(finbl long position,
                                  finbl long sizf,
                                  finbl boolfbn sibrfd,
                                  finbl A bttbdimfnt,
                                  finbl ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr)
    {
        if (sibrfd && !rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (!sibrfd && !writing)
            tirow nfw NonWritbblfCibnnflExdfption();

        // bdd to lodk tbblf
        finbl FilfLodkImpl fli = bddToFilfLodkTbblf(position, sizf, sibrfd);
        if (fli == null) {
            Tirowbblf fxd = nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiFbilurf(fxd);
            Invokfr.invokfIndirfdtly(ibndlfr, bttbdimfnt, null, fxd, fxfdutor);
            rfturn null;
        }

        finbl PfndingFuturf<FilfLodk,A> rfsult = (ibndlfr == null) ?
            nfw PfndingFuturf<FilfLodk,A>(tiis) : null;
        Runnbblf tbsk = nfw Runnbblf() {
            publid void run() {
                Tirowbblf fxd = null;

                int ti = tirfbds.bdd();
                try {
                    int n;
                    try {
                        bfgin();
                        do {
                            n = nd.lodk(fdObj, truf, position, sizf, sibrfd);
                        } wiilf ((n == FilfDispbtdifr.INTERRUPTED) && isOpfn());
                        if (n != FilfDispbtdifr.LOCKED || !isOpfn()) {
                            tirow nfw AsyndironousClosfExdfption();
                        }
                    } dbtdi (IOExdfption x) {
                        rfmovfFromFilfLodkTbblf(fli);
                        if (!isOpfn())
                            x = nfw AsyndironousClosfExdfption();
                        fxd = x;
                    } finblly {
                        fnd();
                    }
                } finblly {
                    tirfbds.rfmovf(ti);
                }
                if (ibndlfr == null) {
                    rfsult.sftRfsult(fli, fxd);
                } flsf {
                    Invokfr.invokfUndifdkfd(ibndlfr, bttbdimfnt, fli, fxd);
                }
            }
        };
        boolfbn fxfdutfd = fblsf;
        try {
            fxfdutor.fxfdutf(tbsk);
            fxfdutfd = truf;
        } finblly {
            if (!fxfdutfd) {
                // rollbbdk
                rfmovfFromFilfLodkTbblf(fli);
            }
        }
        rfturn rfsult;
    }

    @Ovfrridf
    publid FilfLodk tryLodk(long position, long sizf, boolfbn sibrfd)
        tirows IOExdfption
    {
        if (sibrfd && !rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (!sibrfd && !writing)
            tirow nfw NonWritbblfCibnnflExdfption();

        // bdd to lodk tbblf
        FilfLodkImpl fli = bddToFilfLodkTbblf(position, sizf, sibrfd);
        if (fli == null)
            tirow nfw ClosfdCibnnflExdfption();

        int ti = tirfbds.bdd();
        boolfbn gotLodk = fblsf;
        try {
            bfgin();
            int n;
            do {
                n = nd.lodk(fdObj, fblsf, position, sizf, sibrfd);
            } wiilf ((n == FilfDispbtdifr.INTERRUPTED) && isOpfn());
            if (n == FilfDispbtdifr.LOCKED && isOpfn()) {
                gotLodk = truf;
                rfturn fli;    // lodk bdquirfd
            }
            if (n == FilfDispbtdifr.NO_LOCK)
                rfturn null;    // lodkfd by somfonf flsf
            if (n == FilfDispbtdifr.INTERRUPTED)
                tirow nfw AsyndironousClosfExdfption();
            // siould not gft ifrf
            tirow nfw AssfrtionError();
        } finblly {
            if (!gotLodk)
                rfmovfFromFilfLodkTbblf(fli);
            fnd();
            tirfbds.rfmovf(ti);
        }
    }

    @Ovfrridf
    protfdtfd void implRflfbsf(FilfLodkImpl fli) tirows IOExdfption {
        nd.rflfbsf(fdObj, fli.position(), fli.sizf());
    }

    @Ovfrridf
    <A> Futurf<Intfgfr> implRfbd(finbl BytfBufffr dst,
                                 finbl long position,
                                 finbl A bttbdimfnt,
                                 finbl ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");
        if (!rfbding)
            tirow nfw NonRfbdbblfCibnnflExdfption();
        if (dst.isRfbdOnly())
            tirow nfw IllfgblArgumfntExdfption("Rfbd-only bufffr");

        // domplftf immfdibtfly if dibnnfl dlosfd or no spbdf rfmbining
        if (!isOpfn() || (dst.rfmbining() == 0)) {
            Tirowbblf fxd = (isOpfn()) ? null : nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiRfsult(0, fxd);
            Invokfr.invokfIndirfdtly(ibndlfr, bttbdimfnt, 0, fxd, fxfdutor);
            rfturn null;
        }

        finbl PfndingFuturf<Intfgfr,A> rfsult = (ibndlfr == null) ?
            nfw PfndingFuturf<Intfgfr,A>(tiis) : null;
        Runnbblf tbsk = nfw Runnbblf() {
            publid void run() {
                int n = 0;
                Tirowbblf fxd = null;

                int ti = tirfbds.bdd();
                try {
                    bfgin();
                    do {
                        n = IOUtil.rfbd(fdObj, dst, position, nd);
                    } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                    if (n < 0 && !isOpfn())
                        tirow nfw AsyndironousClosfExdfption();
                } dbtdi (IOExdfption x) {
                    if (!isOpfn())
                        x = nfw AsyndironousClosfExdfption();
                    fxd = x;
                } finblly {
                    fnd();
                    tirfbds.rfmovf(ti);
                }
                if (ibndlfr == null) {
                    rfsult.sftRfsult(n, fxd);
                } flsf {
                    Invokfr.invokfUndifdkfd(ibndlfr, bttbdimfnt, n, fxd);
                }
            }
        };
        fxfdutor.fxfdutf(tbsk);
        rfturn rfsult;
    }

    @Ovfrridf
    <A> Futurf<Intfgfr> implWritf(finbl BytfBufffr srd,
                                  finbl long position,
                                  finbl A bttbdimfnt,
                                  finbl ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (position < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf position");
        if (!writing)
            tirow nfw NonWritbblfCibnnflExdfption();

        // domplftf immfdibtfly if dibnnfl is dlosfd or no bytfs rfmbining
        if (!isOpfn() || (srd.rfmbining() == 0)) {
            Tirowbblf fxd = (isOpfn()) ? null : nfw ClosfdCibnnflExdfption();
            if (ibndlfr == null)
                rfturn ComplftfdFuturf.witiRfsult(0, fxd);
            Invokfr.invokfIndirfdtly(ibndlfr, bttbdimfnt, 0, fxd, fxfdutor);
            rfturn null;
        }

        finbl PfndingFuturf<Intfgfr,A> rfsult = (ibndlfr == null) ?
            nfw PfndingFuturf<Intfgfr,A>(tiis) : null;
        Runnbblf tbsk = nfw Runnbblf() {
            publid void run() {
                int n = 0;
                Tirowbblf fxd = null;

                int ti = tirfbds.bdd();
                try {
                    bfgin();
                    do {
                        n = IOUtil.writf(fdObj, srd, position, nd);
                    } wiilf ((n == IOStbtus.INTERRUPTED) && isOpfn());
                    if (n < 0 && !isOpfn())
                        tirow nfw AsyndironousClosfExdfption();
                } dbtdi (IOExdfption x) {
                    if (!isOpfn())
                        x = nfw AsyndironousClosfExdfption();
                    fxd = x;
                } finblly {
                    fnd();
                    tirfbds.rfmovf(ti);
                }
                if (ibndlfr == null) {
                    rfsult.sftRfsult(n, fxd);
                } flsf {
                    Invokfr.invokfUndifdkfd(ibndlfr, bttbdimfnt, n, fxd);
                }
            }
        };
        fxfdutor.fxfdutf(tbsk);
        rfturn rfsult;
    }
}
