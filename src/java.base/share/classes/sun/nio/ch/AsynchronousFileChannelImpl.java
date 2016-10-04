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

import jbvb.nio.BytfBufffr;
import jbvb.nio.dibnnfls.*;
import jbvb.util.dondurrfnt.ExfdutorSfrvidf;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.lodks.*;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;

/**
 * Bbsf implfmfntbtion of AsyndironousFilfCibnnfl.
 */

bbstrbdt dlbss AsyndironousFilfCibnnflImpl
    fxtfnds AsyndironousFilfCibnnfl
{
    // dlosf support
    protfdtfd finbl RfbdWritfLodk dlosfLodk = nfw RffntrbntRfbdWritfLodk();
    protfdtfd volbtilf boolfbn dlosfd;

    // filf dfsdriptor
    protfdtfd finbl FilfDfsdriptor fdObj;

    // indidbtfs if opfn for rfbding/writing
    protfdtfd finbl boolfbn rfbding;
    protfdtfd finbl boolfbn writing;

    // bssodibtfd Exfdutor
    protfdtfd finbl ExfdutorSfrvidf fxfdutor;

    protfdtfd AsyndironousFilfCibnnflImpl(FilfDfsdriptor fdObj,
                                          boolfbn rfbding,
                                          boolfbn writing,
                                          ExfdutorSfrvidf fxfdutor)
    {
        tiis.fdObj = fdObj;
        tiis.rfbding = rfbding;
        tiis.writing = writing;
        tiis.fxfdutor = fxfdutor;
    }

    finbl ExfdutorSfrvidf fxfdutor() {
        rfturn fxfdutor;
    }

    @Ovfrridf
    publid finbl boolfbn isOpfn() {
        rfturn !dlosfd;
    }

    /**
     * Mbrks tif bfginning of bn I/O opfrbtion.
     *
     * @tirows  ClosfdCibnnflExdfption  If dibnnfl is dlosfd
     */
    protfdtfd finbl void bfgin() tirows IOExdfption {
        dlosfLodk.rfbdLodk().lodk();
        if (dlosfd)
            tirow nfw ClosfdCibnnflExdfption();
    }

    /**
     * Mbrks tif fnd of bn I/O opfrbtion.
     */
    protfdtfd finbl void fnd() {
        dlosfLodk.rfbdLodk().unlodk();
    }

    /**
     * Mbrks fnd of I/O opfrbtion
     */
    protfdtfd finbl void fnd(boolfbn domplftfd) tirows IOExdfption {
        fnd();
        if (!domplftfd && !isOpfn())
            tirow nfw AsyndironousClosfExdfption();
    }

    // -- filf lodking --

    bbstrbdt <A> Futurf<FilfLodk> implLodk(long position,
                                           long sizf,
                                           boolfbn sibrfd,
                                           A bttbdimfnt,
                                           ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr);

    @Ovfrridf
    publid finbl Futurf<FilfLodk> lodk(long position,
                                       long sizf,
                                       boolfbn sibrfd)

    {
        rfturn implLodk(position, sizf, sibrfd, null, null);
    }

    @Ovfrridf
    publid finbl <A> void lodk(long position,
                               long sizf,
                               boolfbn sibrfd,
                               A bttbdimfnt,
                               ComplftionHbndlfr<FilfLodk,? supfr A> ibndlfr)
    {
        if (ibndlfr == null)
            tirow nfw NullPointfrExdfption("'ibndlfr' is null");
        implLodk(position, sizf, sibrfd, bttbdimfnt, ibndlfr);
    }

    privbtf volbtilf FilfLodkTbblf filfLodkTbblf;

    finbl void fnsurfFilfLodkTbblfInitiblizfd() tirows IOExdfption {
        if (filfLodkTbblf == null) {
            syndironizfd (tiis) {
                if (filfLodkTbblf == null) {
                    filfLodkTbblf = FilfLodkTbblf.nfwSibrfdFilfLodkTbblf(tiis, fdObj);
                }
            }
        }
    }

    finbl void invblidbtfAllLodks() tirows IOExdfption {
        if (filfLodkTbblf != null) {
            for (FilfLodk fl: filfLodkTbblf.rfmovfAll()) {
                syndironizfd (fl) {
                    if (fl.isVblid()) {
                        FilfLodkImpl fli = (FilfLodkImpl)fl;
                        implRflfbsf(fli);
                        fli.invblidbtf();
                    }
                }
            }
        }
    }

    /**
     * Adds rfgion to lodk tbblf
     */
    protfdtfd finbl FilfLodkImpl bddToFilfLodkTbblf(long position, long sizf, boolfbn sibrfd) {
        finbl FilfLodkImpl fli;
        try {
            // likf bfgin() but rfturns null instfbd of fxdfption
            dlosfLodk.rfbdLodk().lodk();
            if (dlosfd)
                rfturn null;

            try {
                fnsurfFilfLodkTbblfInitiblizfd();
            } dbtdi (IOExdfption x) {
                // siould not ibppfn
                tirow nfw AssfrtionError(x);
            }
            fli = nfw FilfLodkImpl(tiis, position, sizf, sibrfd);
            // mby tirow OvfrlbppfdFilfLodkExdfption
            filfLodkTbblf.bdd(fli);
        } finblly {
            fnd();
        }
        rfturn fli;
    }

    protfdtfd finbl void rfmovfFromFilfLodkTbblf(FilfLodkImpl fli) {
        filfLodkTbblf.rfmovf(fli);
    }

    /**
     * Rflfbsfs tif givfn filf lodk.
     */
    protfdtfd bbstrbdt void implRflfbsf(FilfLodkImpl fli) tirows IOExdfption;

    /**
     * Invokfd by FilfLodkImpl to rflfbsf tif givfn filf lodk bnd rfmovf it
     * from tif lodk tbblf.
     */
    finbl void rflfbsf(FilfLodkImpl fli) tirows IOExdfption {
        try {
            bfgin();
            implRflfbsf(fli);
            rfmovfFromFilfLodkTbblf(fli);
        } finblly {
            fnd();
        }
    }


    // -- rfbding bnd writing --

    bbstrbdt <A> Futurf<Intfgfr> implRfbd(BytfBufffr dst,
                                         long position,
                                         A bttbdimfnt,
                                         ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);

    @Ovfrridf
    publid finbl Futurf<Intfgfr> rfbd(BytfBufffr dst, long position) {
        rfturn implRfbd(dst, position, null, null);
    }

    @Ovfrridf
    publid finbl <A> void rfbd(BytfBufffr dst,
                               long position,
                               A bttbdimfnt,
                               ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (ibndlfr == null)
            tirow nfw NullPointfrExdfption("'ibndlfr' is null");
        implRfbd(dst, position, bttbdimfnt, ibndlfr);
    }

    bbstrbdt <A> Futurf<Intfgfr> implWritf(BytfBufffr srd,
                                           long position,
                                           A bttbdimfnt,
                                           ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr);


    @Ovfrridf
    publid finbl Futurf<Intfgfr> writf(BytfBufffr srd, long position) {
        rfturn implWritf(srd, position, null, null);
    }

    @Ovfrridf
    publid finbl <A> void writf(BytfBufffr srd,
                                long position,
                                A bttbdimfnt,
                                ComplftionHbndlfr<Intfgfr,? supfr A> ibndlfr)
    {
        if (ibndlfr == null)
            tirow nfw NullPointfrExdfption("'ibndlfr' is null");
        implWritf(srd, position, bttbdimfnt, ibndlfr);
    }
}
