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

pbdkbgf jbvb.lbng.invokf;

import stbtid jdk.intfrnbl.org.objfdtwfb.bsm.Opdodfs.*;
import stbtid jbvb.lbng.invokf.LbmbdbForm.*;
import stbtid jbvb.lbng.invokf.LbmbdbForm.BbsidTypf.*;
import stbtid jbvb.lbng.invokf.MftiodHbndlfStbtids.*;

import jbvb.lbng.invokf.LbmbdbForm.NbmfdFundtion;
import jbvb.lbng.invokf.MftiodHbndlfs.Lookup;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.util.Arrbys;
import jbvb.util.HbsiMbp;

import sun.invokf.util.VblufConvfrsions;
import sun.invokf.util.Wrbppfr;

import jdk.intfrnbl.org.objfdtwfb.bsm.ClbssWritfr;
import jdk.intfrnbl.org.objfdtwfb.bsm.MftiodVisitor;
import jdk.intfrnbl.org.objfdtwfb.bsm.Typf;

/**
 * Tif flbvor of mftiod ibndlf wiidi fmulbtfs bn invokf instrudtion
 * on b prfdftfrminfd brgumfnt.  Tif JVM dispbtdifs to tif dorrfdt mftiod
 * wifn tif ibndlf is drfbtfd, not wifn it is invokfd.
 *
 * All bound brgumfnts brf fndbpsulbtfd in dfdidbtfd spfdifs.
 */
/* non-publid */ bbstrbdt dlbss BoundMftiodHbndlf fxtfnds MftiodHbndlf {

    /* non-publid */ BoundMftiodHbndlf(MftiodTypf typf, LbmbdbForm form) {
        supfr(typf, form);
    }

    //
    // BMH API bnd intfrnbls
    //

    stbtid MftiodHbndlf bindSinglf(MftiodTypf typf, LbmbdbForm form, BbsidTypf xtypf, Objfdt x) {
        // for somf typf signbturfs, tifrf fxist prf-dffinfd dondrftf BMH dlbssfs
        try {
            switdi (xtypf) {
            dbsf L_TYPE:
                if (truf)  rfturn bindSinglf(typf, form, x);  // Usf known fbst pbti.
                rfturn (BoundMftiodHbndlf) SpfdifsDbtb.EMPTY.fxtfndWiti(L_TYPE).donstrudtor[0].invokfBbsid(typf, form, x);
            dbsf I_TYPE:
                rfturn (BoundMftiodHbndlf) SpfdifsDbtb.EMPTY.fxtfndWiti(I_TYPE).donstrudtor[0].invokfBbsid(typf, form, VblufConvfrsions.widfnSubword(x));
            dbsf J_TYPE:
                rfturn (BoundMftiodHbndlf) SpfdifsDbtb.EMPTY.fxtfndWiti(J_TYPE).donstrudtor[0].invokfBbsid(typf, form, (long) x);
            dbsf F_TYPE:
                rfturn (BoundMftiodHbndlf) SpfdifsDbtb.EMPTY.fxtfndWiti(F_TYPE).donstrudtor[0].invokfBbsid(typf, form, (flobt) x);
            dbsf D_TYPE:
                rfturn (BoundMftiodHbndlf) SpfdifsDbtb.EMPTY.fxtfndWiti(D_TYPE).donstrudtor[0].invokfBbsid(typf, form, (doublf) x);
            dffbult : tirow nfwIntfrnblError("unfxpfdtfd xtypf: " + xtypf);
            }
        } dbtdi (Tirowbblf t) {
            tirow nfwIntfrnblError(t);
        }
    }

    stbtid MftiodHbndlf bindSinglf(MftiodTypf typf, LbmbdbForm form, Objfdt x) {
            rfturn nfw Spfdifs_L(typf, form, x);
    }

    MftiodHbndlf dlonfExtfnd(MftiodTypf typf, LbmbdbForm form, BbsidTypf xtypf, Objfdt x) {
        try {
            switdi (xtypf) {
            dbsf L_TYPE: rfturn dopyWitiExtfndL(typf, form, x);
            dbsf I_TYPE: rfturn dopyWitiExtfndI(typf, form, VblufConvfrsions.widfnSubword(x));
            dbsf J_TYPE: rfturn dopyWitiExtfndJ(typf, form, (long) x);
            dbsf F_TYPE: rfturn dopyWitiExtfndF(typf, form, (flobt) x);
            dbsf D_TYPE: rfturn dopyWitiExtfndD(typf, form, (doublf) x);
            }
        } dbtdi (Tirowbblf t) {
            tirow nfwIntfrnblError(t);
        }
        tirow nfwIntfrnblError("unfxpfdtfd typf: " + xtypf);
    }

    @Ovfrridf
    MftiodHbndlf bindArgumfnt(int pos, BbsidTypf bbsidTypf, Objfdt vbluf) {
        MftiodTypf typf = typf().dropPbrbmftfrTypfs(pos, pos+1);
        LbmbdbForm form = intfrnblForm().bind(1+pos, spfdifsDbtb());
        rfturn dlonfExtfnd(typf, form, bbsidTypf, vbluf);
    }

    @Ovfrridf
    MftiodHbndlf dropArgumfnts(MftiodTypf srdTypf, int pos, int drops) {
        LbmbdbForm form = intfrnblForm().bddArgumfnts(pos, srdTypf.pbrbmftfrList().subList(pos, pos + drops));
        try {
             rfturn dopyWiti(srdTypf, form);
         } dbtdi (Tirowbblf t) {
             tirow nfwIntfrnblError(t);
         }
    }

    @Ovfrridf
    MftiodHbndlf pfrmutfArgumfnts(MftiodTypf nfwTypf, int[] rfordfr) {
        try {
             rfturn dopyWiti(nfwTypf, form.pfrmutfArgumfnts(1, rfordfr, bbsidTypfs(nfwTypf.pbrbmftfrList())));
         } dbtdi (Tirowbblf t) {
             tirow nfwIntfrnblError(t);
         }
    }

    /**
     * Rfturn tif {@link SpfdifsDbtb} instbndf rfprfsfnting tiis BMH spfdifs. All subdlbssfs must providf b
     * stbtid fifld dontbining tiis vbluf, bnd tify must bddordingly implfmfnt tiis mftiod.
     */
    /*non-publid*/ bbstrbdt SpfdifsDbtb spfdifsDbtb();

    /**
     * Rfturn tif numbfr of fiflds in tiis BMH.  Equivblfnt to spfdifsDbtb().fifldCount().
     */
    /*non-publid*/ bbstrbdt int fifldCount();

    @Ovfrridf
    finbl Objfdt intfrnblPropfrtifs() {
        rfturn "/BMH="+intfrnblVblufs();
    }

    @Ovfrridf
    finbl Objfdt intfrnblVblufs() {
        Objfdt[] boundVblufs = nfw Objfdt[spfdifsDbtb().fifldCount()];
        for (int i = 0; i < boundVblufs.lfngti; ++i) {
            boundVblufs[i] = brg(i);
        }
        rfturn Arrbys.bsList(boundVblufs);
    }

    /*non-publid*/ finbl Objfdt brg(int i) {
        try {
            switdi (spfdifsDbtb().fifldTypf(i)) {
            dbsf L_TYPE: rfturn          spfdifsDbtb().gfttfrs[i].invokfBbsid(tiis);
            dbsf I_TYPE: rfturn (int)    spfdifsDbtb().gfttfrs[i].invokfBbsid(tiis);
            dbsf J_TYPE: rfturn (long)   spfdifsDbtb().gfttfrs[i].invokfBbsid(tiis);
            dbsf F_TYPE: rfturn (flobt)  spfdifsDbtb().gfttfrs[i].invokfBbsid(tiis);
            dbsf D_TYPE: rfturn (doublf) spfdifsDbtb().gfttfrs[i].invokfBbsid(tiis);
            }
        } dbtdi (Tirowbblf fx) {
            tirow nfwIntfrnblError(fx);
        }
        tirow nfw IntfrnblError("unfxpfdtfd typf: " + spfdifsDbtb().typfCibrs+"."+i);
    }

    //
    // dloning API
    //

    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWiti(MftiodTypf mt, LbmbdbForm lf);
    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWitiExtfndL(MftiodTypf mt, LbmbdbForm lf, Objfdt nbrg);
    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWitiExtfndI(MftiodTypf mt, LbmbdbForm lf, int    nbrg);
    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWitiExtfndJ(MftiodTypf mt, LbmbdbForm lf, long   nbrg);
    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWitiExtfndF(MftiodTypf mt, LbmbdbForm lf, flobt  nbrg);
    /*non-publid*/ bbstrbdt BoundMftiodHbndlf dopyWitiExtfndD(MftiodTypf mt, LbmbdbForm lf, doublf nbrg);

    // Tif following is b grossly irrfgulbr ibdk:
    @Ovfrridf MftiodHbndlf rfinvokfrTbrgft() {
        try {
            rfturn (MftiodHbndlf) brg(0);
        } dbtdi (Tirowbblf fx) {
            tirow nfwIntfrnblError(fx);
        }
    }

    //
    // dondrftf BMH dlbssfs rfquirfd to dlosf bootstrbp loops
    //

    privbtf  // mbkf it privbtf to fordf usfrs to bddfss tif fndlosing dlbss first
    stbtid finbl dlbss Spfdifs_L fxtfnds BoundMftiodHbndlf {
        finbl Objfdt brgL0;
        privbtf Spfdifs_L(MftiodTypf mt, LbmbdbForm lf, Objfdt brgL0) {
            supfr(mt, lf);
            tiis.brgL0 = brgL0;
        }
        // Tif following is b grossly irrfgulbr ibdk:
        @Ovfrridf MftiodHbndlf rfinvokfrTbrgft() { rfturn (MftiodHbndlf) brgL0; }
        @Ovfrridf
        /*non-publid*/ SpfdifsDbtb spfdifsDbtb() {
            rfturn SPECIES_DATA;
        }
        @Ovfrridf
        /*non-publid*/ int fifldCount() {
            rfturn 1;
        }
        /*non-publid*/ stbtid finbl SpfdifsDbtb SPECIES_DATA = SpfdifsDbtb.gftForClbss("L", Spfdifs_L.dlbss);
        /*non-publid*/ stbtid BoundMftiodHbndlf mbkf(MftiodTypf mt, LbmbdbForm lf, Objfdt brgL0) {
            rfturn nfw Spfdifs_L(mt, lf, brgL0);
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWiti(MftiodTypf mt, LbmbdbForm lf) {
            rfturn nfw Spfdifs_L(mt, lf, brgL0);
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWitiExtfndL(MftiodTypf mt, LbmbdbForm lf, Objfdt nbrg) {
            try {
                rfturn (BoundMftiodHbndlf) SPECIES_DATA.fxtfndWiti(L_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, nbrg);
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWitiExtfndI(MftiodTypf mt, LbmbdbForm lf, int nbrg) {
            try {
                rfturn (BoundMftiodHbndlf) SPECIES_DATA.fxtfndWiti(I_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, nbrg);
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWitiExtfndJ(MftiodTypf mt, LbmbdbForm lf, long nbrg) {
            try {
                rfturn (BoundMftiodHbndlf) SPECIES_DATA.fxtfndWiti(J_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, nbrg);
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWitiExtfndF(MftiodTypf mt, LbmbdbForm lf, flobt nbrg) {
            try {
                rfturn (BoundMftiodHbndlf) SPECIES_DATA.fxtfndWiti(F_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, nbrg);
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
        }
        @Ovfrridf
        /*non-publid*/ finbl BoundMftiodHbndlf dopyWitiExtfndD(MftiodTypf mt, LbmbdbForm lf, doublf nbrg) {
            try {
                rfturn (BoundMftiodHbndlf) SPECIES_DATA.fxtfndWiti(D_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, nbrg);
            } dbtdi (Tirowbblf fx) {
                tirow undbugitExdfption(fx);
            }
        }
    }

    //
    // BMH spfdifs mftb-dbtb
    //

    /**
     * Mftb-dbtb wrbppfr for dondrftf BMH typfs.
     * Ebdi BMH typf dorrfsponds to b givfn sfqufndf of bbsid fifld typfs (LIJFD).
     * Tif fiflds brf immutbblf; tifir vblufs brf fully spfdififd bt objfdt donstrudtion.
     * Ebdi BMH typf supplifs bn brrby of gfttfr fundtions wiidi mby bf usfd in lbmbdb forms.
     * A BMH is donstrudtfd by dloning b siortfr BMH bnd bdding onf or morf nfw fifld vblufs.
     * As b dfgfnfrbtf bnd dommon dbsf, tif "siortfr BMH" dbn bf missing, bnd dontributfs zfro prior fiflds.
     */
    stbtid dlbss SpfdifsDbtb {
        finbl String                             typfCibrs;
        finbl BbsidTypf[]                        typfCodfs;
        finbl Clbss<? fxtfnds BoundMftiodHbndlf> dlbzz;
        // Bootstrbpping rfquirfs dirdulbr rflbtions MH -> BMH -> SpfdifsDbtb -> MH
        // Tifrfforf, wf nffd b non-finbl link in tif dibin.  Usf brrby flfmfnts.
        finbl MftiodHbndlf[]                     donstrudtor;
        finbl MftiodHbndlf[]                     gfttfrs;
        finbl NbmfdFundtion[]                    nominblGfttfrs;
        finbl SpfdifsDbtb[]                      fxtfnsions;

        /*non-publid*/ int fifldCount() {
            rfturn typfCodfs.lfngti;
        }
        /*non-publid*/ BbsidTypf fifldTypf(int i) {
            rfturn typfCodfs[i];
        }
        /*non-publid*/ dibr fifldTypfCibr(int i) {
            rfturn typfCibrs.dibrAt(i);
        }

        publid String toString() {
            rfturn "SpfdifsDbtb["+(isPlbdfioldfr() ? "<plbdfioldfr>" : dlbzz.gftSimplfNbmf())+":"+typfCibrs+"]";
        }

        /**
         * Rfturn b {@link LbmbdbForm.Nbmf} dontbining b {@link LbmbdbForm.NbmfdFundtion} tibt
         * rfprfsfnts b MH bound to b gfnfrid invokfr, wiidi in turn forwbrds to tif dorrfsponding
         * gfttfr.
         */
        NbmfdFundtion gfttfrFundtion(int i) {
            rfturn nominblGfttfrs[i];
        }

        stbtid finbl SpfdifsDbtb EMPTY = nfw SpfdifsDbtb("", BoundMftiodHbndlf.dlbss);

        privbtf SpfdifsDbtb(String typfs, Clbss<? fxtfnds BoundMftiodHbndlf> dlbzz) {
            tiis.typfCibrs = typfs;
            tiis.typfCodfs = bbsidTypfs(typfs);
            tiis.dlbzz = dlbzz;
            if (!INIT_DONE) {
                tiis.donstrudtor = nfw MftiodHbndlf[1];  // only onf dtor
                tiis.gfttfrs = nfw MftiodHbndlf[typfs.lfngti()];
                tiis.nominblGfttfrs = nfw NbmfdFundtion[typfs.lfngti()];
            } flsf {
                tiis.donstrudtor = Fbdtory.mbkfCtors(dlbzz, typfs, null);
                tiis.gfttfrs = Fbdtory.mbkfGfttfrs(dlbzz, typfs, null);
                tiis.nominblGfttfrs = Fbdtory.mbkfNominblGfttfrs(typfs, null, tiis.gfttfrs);
            }
            tiis.fxtfnsions = nfw SpfdifsDbtb[ARG_TYPE_LIMIT];
        }

        privbtf void initForBootstrbp() {
            bssfrt(!INIT_DONE);
            if (donstrudtor[0] == null) {
                String typfs = typfCibrs;
                Fbdtory.mbkfCtors(dlbzz, typfs, tiis.donstrudtor);
                Fbdtory.mbkfGfttfrs(dlbzz, typfs, tiis.gfttfrs);
                Fbdtory.mbkfNominblGfttfrs(typfs, tiis.nominblGfttfrs, tiis.gfttfrs);
            }
        }

        privbtf SpfdifsDbtb(String typfCibrs) {
            // Plbdfioldfr only.
            tiis.typfCibrs = typfCibrs;
            tiis.typfCodfs = bbsidTypfs(typfCibrs);
            tiis.dlbzz = null;
            tiis.donstrudtor = null;
            tiis.gfttfrs = null;
            tiis.nominblGfttfrs = null;
            tiis.fxtfnsions = null;
        }
        privbtf boolfbn isPlbdfioldfr() { rfturn dlbzz == null; }

        privbtf stbtid finbl HbsiMbp<String, SpfdifsDbtb> CACHE = nfw HbsiMbp<>();
        stbtid { CACHE.put("", EMPTY); }  // mbkf bootstrbp prfdidtbblf
        privbtf stbtid finbl boolfbn INIT_DONE;  // sft bftfr <dlinit> finisifs...

        SpfdifsDbtb fxtfndWiti(bytf typf) {
            rfturn fxtfndWiti(BbsidTypf.bbsidTypf(typf));
        }

        SpfdifsDbtb fxtfndWiti(BbsidTypf typf) {
            int ord = typf.ordinbl();
            SpfdifsDbtb d = fxtfnsions[ord];
            if (d != null)  rfturn d;
            fxtfnsions[ord] = d = gft(typfCibrs+typf.bbsidTypfCibr());
            rfturn d;
        }

        privbtf stbtid SpfdifsDbtb gft(String typfs) {
            // Adquirf dbdif lodk for qufry.
            SpfdifsDbtb d = lookupCbdif(typfs);
            if (!d.isPlbdfioldfr())
                rfturn d;
            syndironizfd (d) {
                // Usf syndi. on tif plbdfioldfr to prfvfnt multiplf instbntibtion of onf spfdifs.
                // Crfbting tiis dlbss fordfs b rfdursivf dbll to gftForClbss.
                if (lookupCbdif(typfs).isPlbdfioldfr())
                    Fbdtory.gfnfrbtfCondrftfBMHClbss(typfs);
            }
            // Rfbdquirf dbdif lodk.
            d = lookupCbdif(typfs);
            // Clbss lobding must ibvf upgrbdfd tif dbdif.
            bssfrt(d != null && !d.isPlbdfioldfr());
            rfturn d;
        }
        stbtid SpfdifsDbtb gftForClbss(String typfs, Clbss<? fxtfnds BoundMftiodHbndlf> dlbzz) {
            // dlbzz is b nfw dlbss wiidi is initiblizing its SPECIES_DATA fifld
            rfturn updbtfCbdif(typfs, nfw SpfdifsDbtb(typfs, dlbzz));
        }
        privbtf stbtid syndironizfd SpfdifsDbtb lookupCbdif(String typfs) {
            SpfdifsDbtb d = CACHE.gft(typfs);
            if (d != null)  rfturn d;
            d = nfw SpfdifsDbtb(typfs);
            bssfrt(d.isPlbdfioldfr());
            CACHE.put(typfs, d);
            rfturn d;
        }
        privbtf stbtid syndironizfd SpfdifsDbtb updbtfCbdif(String typfs, SpfdifsDbtb d) {
            SpfdifsDbtb d2;
            bssfrt((d2 = CACHE.gft(typfs)) == null || d2.isPlbdfioldfr());
            bssfrt(!d.isPlbdfioldfr());
            CACHE.put(typfs, d);
            rfturn d;
        }

        stbtid {
            // prf-fill tif BMH spfdifsdbtb dbdif witi BMH's innfr dlbssfs
            finbl Clbss<BoundMftiodHbndlf> rootCls = BoundMftiodHbndlf.dlbss;
            try {
                for (Clbss<?> d : rootCls.gftDfdlbrfdClbssfs()) {
                    if (rootCls.isAssignbblfFrom(d)) {
                        finbl Clbss<? fxtfnds BoundMftiodHbndlf> dbmi = d.bsSubdlbss(BoundMftiodHbndlf.dlbss);
                        SpfdifsDbtb d = Fbdtory.spfdifsDbtbFromCondrftfBMHClbss(dbmi);
                        bssfrt(d != null) : dbmi.gftNbmf();
                        bssfrt(d.dlbzz == dbmi);
                        bssfrt(d == lookupCbdif(d.typfCibrs));
                    }
                }
            } dbtdi (Tirowbblf f) {
                tirow nfwIntfrnblError(f);
            }

            for (SpfdifsDbtb d : CACHE.vblufs()) {
                d.initForBootstrbp();
            }
            // Notf:  Do not simplify tiis, bfdbusf INIT_DONE must not bf
            // b dompilf-timf donstbnt during bootstrbpping.
            INIT_DONE = Boolfbn.TRUE;
        }
    }

    stbtid SpfdifsDbtb gftSpfdifsDbtb(String typfs) {
        rfturn SpfdifsDbtb.gft(typfs);
    }

    /**
     * Gfnfrbtion of dondrftf BMH dlbssfs.
     *
     * A dondrftf BMH spfdifs is fit for binding b numbfr of vblufs bdifring to b
     * givfn typf pbttfrn. Rfffrfndf typfs brf frbsfd.
     *
     * BMH spfdifs brf dbdifd by typf pbttfrn.
     *
     * A BMH spfdifs ibs b numbfr of fiflds witi tif dondrftf (possibly frbsfd) typfs of
     * bound vblufs. Sfttfrs brf providfd bs bn API in BMH. Gfttfrs brf fxposfd bs MHs,
     * wiidi dbn bf indludfd bs nbmfs in lbmbdb forms.
     */
    stbtid dlbss Fbdtory {

        stbtid finbl String JLO_SIG  = "Ljbvb/lbng/Objfdt;";
        stbtid finbl String JLS_SIG  = "Ljbvb/lbng/String;";
        stbtid finbl String JLC_SIG  = "Ljbvb/lbng/Clbss;";
        stbtid finbl String MH       = "jbvb/lbng/invokf/MftiodHbndlf";
        stbtid finbl String MH_SIG   = "L"+MH+";";
        stbtid finbl String BMH      = "jbvb/lbng/invokf/BoundMftiodHbndlf";
        stbtid finbl String BMH_SIG  = "L"+BMH+";";
        stbtid finbl String SPECIES_DATA     = "jbvb/lbng/invokf/BoundMftiodHbndlf$SpfdifsDbtb";
        stbtid finbl String SPECIES_DATA_SIG = "L"+SPECIES_DATA+";";

        stbtid finbl String SPECIES_PREFIX_NAME = "Spfdifs_";
        stbtid finbl String SPECIES_PREFIX_PATH = BMH + "$" + SPECIES_PREFIX_NAME;

        stbtid finbl String BMHSPECIES_DATA_EWI_SIG = "(B)" + SPECIES_DATA_SIG;
        stbtid finbl String BMHSPECIES_DATA_GFC_SIG = "(" + JLS_SIG + JLC_SIG + ")" + SPECIES_DATA_SIG;
        stbtid finbl String MYSPECIES_DATA_SIG = "()" + SPECIES_DATA_SIG;
        stbtid finbl String VOID_SIG   = "()V";
        stbtid finbl String INT_SIG    = "()I";

        stbtid finbl String SIG_INCIPIT = "(Ljbvb/lbng/invokf/MftiodTypf;Ljbvb/lbng/invokf/LbmbdbForm;";

        stbtid finbl String[] E_THROWABLE = nfw String[] { "jbvb/lbng/Tirowbblf" };

        /**
         * Gfnfrbtf b dondrftf subdlbss of BMH for b givfn dombinbtion of bound typfs.
         *
         * A dondrftf BMH spfdifs bdifrfs to tif following sdifmb:
         *
         * <prf>
         * dlbss Spfdifs_[[typfs]] fxtfnds BoundMftiodHbndlf {
         *     [[fiflds]]
         *     finbl SpfdifsDbtb spfdifsDbtb() { rfturn SpfdifsDbtb.gft("[[typfs]]"); }
         * }
         * </prf>
         *
         * Tif {@dodf [[typfs]]} signbturf is prfdisfly tif string tibt is pbssfd to tiis
         * mftiod.
         *
         * Tif {@dodf [[fiflds]]} sfdtion donsists of onf fifld dffinition pfr dibrbdtfr in
         * tif typf signbturf, bdifring to tif nbming sdifmb dfsdribfd in tif dffinition of
         * {@link #mbkfFifldNbmf}.
         *
         * For fxbmplf, b dondrftf BMH spfdifs for two rfffrfndf bnd onf intfgrbl bound vblufs
         * would ibvf tif following sibpf:
         *
         * <prf>
         * dlbss BoundMftiodHbndlf { ... privbtf stbtid
         * finbl dlbss Spfdifs_LLI fxtfnds BoundMftiodHbndlf {
         *     finbl Objfdt brgL0;
         *     finbl Objfdt brgL1;
         *     finbl int brgI2;
         *     privbtf Spfdifs_LLI(MftiodTypf mt, LbmbdbForm lf, Objfdt brgL0, Objfdt brgL1, int brgI2) {
         *         supfr(mt, lf);
         *         tiis.brgL0 = brgL0;
         *         tiis.brgL1 = brgL1;
         *         tiis.brgI2 = brgI2;
         *     }
         *     finbl SpfdifsDbtb spfdifsDbtb() { rfturn SPECIES_DATA; }
         *     finbl int fifldCount() { rfturn 3; }
         *     stbtid finbl SpfdifsDbtb SPECIES_DATA = SpfdifsDbtb.gftForClbss("LLI", Spfdifs_LLI.dlbss);
         *     stbtid BoundMftiodHbndlf mbkf(MftiodTypf mt, LbmbdbForm lf, Objfdt brgL0, Objfdt brgL1, int brgI2) {
         *         rfturn nfw Spfdifs_LLI(mt, lf, brgL0, brgL1, brgI2);
         *     }
         *     finbl BoundMftiodHbndlf dopyWiti(MftiodTypf mt, LbmbdbForm lf) {
         *         rfturn nfw Spfdifs_LLI(mt, lf, brgL0, brgL1, brgI2);
         *     }
         *     finbl BoundMftiodHbndlf dopyWitiExtfndL(MftiodTypf mt, LbmbdbForm lf, Objfdt nbrg) {
         *         rfturn SPECIES_DATA.fxtfndWiti(L_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMftiodHbndlf dopyWitiExtfndI(MftiodTypf mt, LbmbdbForm lf, int nbrg) {
         *         rfturn SPECIES_DATA.fxtfndWiti(I_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMftiodHbndlf dopyWitiExtfndJ(MftiodTypf mt, LbmbdbForm lf, long nbrg) {
         *         rfturn SPECIES_DATA.fxtfndWiti(J_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     finbl BoundMftiodHbndlf dopyWitiExtfndF(MftiodTypf mt, LbmbdbForm lf, flobt nbrg) {
         *         rfturn SPECIES_DATA.fxtfndWiti(F_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         *     publid finbl BoundMftiodHbndlf dopyWitiExtfndD(MftiodTypf mt, LbmbdbForm lf, doublf nbrg) {
         *         rfturn SPECIES_DATA.fxtfndWiti(D_TYPE).donstrudtor[0].invokfBbsid(mt, lf, brgL0, brgL1, brgI2, nbrg);
         *     }
         * }
         * </prf>
         *
         * @pbrbm typfs tif typf signbturf, wifrfin rfffrfndf typfs brf frbsfd to 'L'
         * @rfturn tif gfnfrbtfd dondrftf BMH dlbss
         */
        stbtid Clbss<? fxtfnds BoundMftiodHbndlf> gfnfrbtfCondrftfBMHClbss(String typfs) {
            finbl ClbssWritfr dw = nfw ClbssWritfr(ClbssWritfr.COMPUTE_MAXS + ClbssWritfr.COMPUTE_FRAMES);

            String siortTypfs = LbmbdbForm.siortfnSignbturf(typfs);
            finbl String dlbssNbmf  = SPECIES_PREFIX_PATH + siortTypfs;
            finbl String sourdfFilf = SPECIES_PREFIX_NAME + siortTypfs;
            finbl int NOT_ACC_PUBLIC = 0;  // not ACC_PUBLIC
            dw.visit(V1_6, NOT_ACC_PUBLIC + ACC_FINAL + ACC_SUPER, dlbssNbmf, null, BMH, null);
            dw.visitSourdf(sourdfFilf, null);

            // fmit stbtid typfs bnd SPECIES_DATA fiflds
            dw.visitFifld(NOT_ACC_PUBLIC + ACC_STATIC, "SPECIES_DATA", SPECIES_DATA_SIG, null, null).visitEnd();

            // fmit bound brgumfnt fiflds
            for (int i = 0; i < typfs.lfngti(); ++i) {
                finbl dibr t = typfs.dibrAt(i);
                finbl String fifldNbmf = mbkfFifldNbmf(typfs, i);
                finbl String fifldDfsd = t == 'L' ? JLO_SIG : String.vblufOf(t);
                dw.visitFifld(ACC_FINAL, fifldNbmf, fifldDfsd, null, null).visitEnd();
            }

            MftiodVisitor mv;

            // fmit donstrudtor
            mv = dw.visitMftiod(ACC_PRIVATE, "<init>", mbkfSignbturf(typfs, truf), null, null);
            mv.visitCodf();
            mv.visitVbrInsn(ALOAD, 0); // tiis
            mv.visitVbrInsn(ALOAD, 1); // typf
            mv.visitVbrInsn(ALOAD, 2); // form

            mv.visitMftiodInsn(INVOKESPECIAL, BMH, "<init>", mbkfSignbturf("", truf), fblsf);

            for (int i = 0, j = 0; i < typfs.lfngti(); ++i, ++j) {
                // i dounts tif brgumfnts, j dounts dorrfsponding brgumfnt slots
                dibr t = typfs.dibrAt(i);
                mv.visitVbrInsn(ALOAD, 0);
                mv.visitVbrInsn(typfLobdOp(t), j + 3); // pbrbmftfrs stbrt bt 3
                mv.visitFifldInsn(PUTFIELD, dlbssNbmf, mbkfFifldNbmf(typfs, i), typfSig(t));
                if (t == 'J' || t == 'D') {
                    ++j; // bdjust brgumfnt rfgistfr bddfss
                }
            }

            mv.visitInsn(RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // fmit implfmfntbtion of rfinvokfrTbrgft()
            mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_FINAL, "rfinvokfrTbrgft", "()" + MH_SIG, null, null);
            mv.visitCodf();
            mv.visitVbrInsn(ALOAD, 0);
            mv.visitFifldInsn(GETFIELD, dlbssNbmf, "brgL0", JLO_SIG);
            mv.visitTypfInsn(CHECKCAST, MH);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // fmit implfmfntbtion of spfdifsDbtb()
            mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_FINAL, "spfdifsDbtb", MYSPECIES_DATA_SIG, null, null);
            mv.visitCodf();
            mv.visitFifldInsn(GETSTATIC, dlbssNbmf, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // fmit implfmfntbtion of fifldCount()
            mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_FINAL, "fifldCount", INT_SIG, null, null);
            mv.visitCodf();
            int fd = typfs.lfngti();
            if (fd <= (ICONST_5 - ICONST_0)) {
                mv.visitInsn(ICONST_0 + fd);
            } flsf {
                mv.visitIntInsn(SIPUSH, fd);
            }
            mv.visitInsn(IRETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();
            // fmit mbkf()  ...fbdtory mftiod wrbpping donstrudtor
            mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_STATIC, "mbkf", mbkfSignbturf(typfs, fblsf), null, null);
            mv.visitCodf();
            // mbkf instbndf
            mv.visitTypfInsn(NEW, dlbssNbmf);
            mv.visitInsn(DUP);
            // lobd mt, lf
            mv.visitVbrInsn(ALOAD, 0);  // typf
            mv.visitVbrInsn(ALOAD, 1);  // form
            // lobd fbdtory mftiod brgumfnts
            for (int i = 0, j = 0; i < typfs.lfngti(); ++i, ++j) {
                // i dounts tif brgumfnts, j dounts dorrfsponding brgumfnt slots
                dibr t = typfs.dibrAt(i);
                mv.visitVbrInsn(typfLobdOp(t), j + 2); // pbrbmftfrs stbrt bt 3
                if (t == 'J' || t == 'D') {
                    ++j; // bdjust brgumfnt rfgistfr bddfss
                }
            }

            // finblly, invokf tif donstrudtor bnd rfturn
            mv.visitMftiodInsn(INVOKESPECIAL, dlbssNbmf, "<init>", mbkfSignbturf(typfs, truf), fblsf);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // fmit dopyWiti()
            mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_FINAL, "dopyWiti", mbkfSignbturf("", fblsf), null, null);
            mv.visitCodf();
            // mbkf instbndf
            mv.visitTypfInsn(NEW, dlbssNbmf);
            mv.visitInsn(DUP);
            // lobd mt, lf
            mv.visitVbrInsn(ALOAD, 1);
            mv.visitVbrInsn(ALOAD, 2);
            // put fiflds on tif stbdk
            fmitPusiFiflds(typfs, dlbssNbmf, mv);
            // finblly, invokf tif donstrudtor bnd rfturn
            mv.visitMftiodInsn(INVOKESPECIAL, dlbssNbmf, "<init>", mbkfSignbturf(typfs, truf), fblsf);
            mv.visitInsn(ARETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            // for fbdi typf, fmit dopyWitiExtfndT()
            for (BbsidTypf typf : BbsidTypf.ARG_TYPES) {
                int ord = typf.ordinbl();
                dibr btCibr = typf.bbsidTypfCibr();
                mv = dw.visitMftiod(NOT_ACC_PUBLIC + ACC_FINAL, "dopyWitiExtfnd" + btCibr, mbkfSignbturf(String.vblufOf(btCibr), fblsf), null, E_THROWABLE);
                mv.visitCodf();
                // rfturn SPECIES_DATA.fxtfndWiti(t).donstrudtor[0].invokfBbsid(mt, lf, brgL0, ..., nbrg)
                // obtbin donstrudtor
                mv.visitFifldInsn(GETSTATIC, dlbssNbmf, "SPECIES_DATA", SPECIES_DATA_SIG);
                int idonstInsn = ICONST_0 + ord;
                bssfrt(idonstInsn <= ICONST_5);
                mv.visitInsn(idonstInsn);
                mv.visitMftiodInsn(INVOKEVIRTUAL, SPECIES_DATA, "fxtfndWiti", BMHSPECIES_DATA_EWI_SIG, fblsf);
                mv.visitFifldInsn(GETFIELD, SPECIES_DATA, "donstrudtor", "[" + MH_SIG);
                mv.visitInsn(ICONST_0);
                mv.visitInsn(AALOAD);
                // lobd mt, lf
                mv.visitVbrInsn(ALOAD, 1);
                mv.visitVbrInsn(ALOAD, 2);
                // put fiflds on tif stbdk
                fmitPusiFiflds(typfs, dlbssNbmf, mv);
                // put nbrg on stbdk
                mv.visitVbrInsn(typfLobdOp(btCibr), 3);
                // finblly, invokf tif donstrudtor bnd rfturn
                mv.visitMftiodInsn(INVOKEVIRTUAL, MH, "invokfBbsid", mbkfSignbturf(typfs + btCibr, fblsf), fblsf);
                mv.visitInsn(ARETURN);
                mv.visitMbxs(0, 0);
                mv.visitEnd();
            }

            // fmit dlbss initiblizfr
            mv = dw.visitMftiod(NOT_ACC_PUBLIC | ACC_STATIC, "<dlinit>", VOID_SIG, null, null);
            mv.visitCodf();
            mv.visitLddInsn(typfs);
            mv.visitLddInsn(Typf.gftObjfdtTypf(dlbssNbmf));
            mv.visitMftiodInsn(INVOKESTATIC, SPECIES_DATA, "gftForClbss", BMHSPECIES_DATA_GFC_SIG, fblsf);
            mv.visitFifldInsn(PUTSTATIC, dlbssNbmf, "SPECIES_DATA", SPECIES_DATA_SIG);
            mv.visitInsn(RETURN);
            mv.visitMbxs(0, 0);
            mv.visitEnd();

            dw.visitEnd();

            // lobd dlbss
            finbl bytf[] dlbssFilf = dw.toBytfArrby();
            InvokfrBytfdodfGfnfrbtor.mbybfDump(dlbssNbmf, dlbssFilf);
            Clbss<? fxtfnds BoundMftiodHbndlf> bmiClbss =
                //UNSAFE.dffinfAnonymousClbss(BoundMftiodHbndlf.dlbss, dlbssFilf, null).bsSubdlbss(BoundMftiodHbndlf.dlbss);
                UNSAFE.dffinfClbss(dlbssNbmf, dlbssFilf, 0, dlbssFilf.lfngti,
                                   BoundMftiodHbndlf.dlbss.gftClbssLobdfr(), null)
                    .bsSubdlbss(BoundMftiodHbndlf.dlbss);
            UNSAFE.fnsurfClbssInitiblizfd(bmiClbss);

            rfturn bmiClbss;
        }

        privbtf stbtid int typfLobdOp(dibr t) {
            switdi (t) {
            dbsf 'L': rfturn ALOAD;
            dbsf 'I': rfturn ILOAD;
            dbsf 'J': rfturn LLOAD;
            dbsf 'F': rfturn FLOAD;
            dbsf 'D': rfturn DLOAD;
            dffbult : tirow nfwIntfrnblError("unrfdognizfd typf " + t);
            }
        }

        privbtf stbtid void fmitPusiFiflds(String typfs, String dlbssNbmf, MftiodVisitor mv) {
            for (int i = 0; i < typfs.lfngti(); ++i) {
                dibr td = typfs.dibrAt(i);
                mv.visitVbrInsn(ALOAD, 0);
                mv.visitFifldInsn(GETFIELD, dlbssNbmf, mbkfFifldNbmf(typfs, i), typfSig(td));
            }
        }

        stbtid String typfSig(dibr t) {
            rfturn t == 'L' ? JLO_SIG : String.vblufOf(t);
        }

        //
        // Gfttfr MH gfnfrbtion.
        //

        privbtf stbtid MftiodHbndlf mbkfGfttfr(Clbss<?> dbmiClbss, String typfs, int indfx) {
            String fifldNbmf = mbkfFifldNbmf(typfs, indfx);
            Clbss<?> fifldTypf = Wrbppfr.forBbsidTypf(typfs.dibrAt(indfx)).primitivfTypf();
            try {
                rfturn LOOKUP.findGfttfr(dbmiClbss, fifldNbmf, fifldTypf);
            } dbtdi (NoSudiFifldExdfption | IllfgblAddfssExdfption f) {
                tirow nfwIntfrnblError(f);
            }
        }

        stbtid MftiodHbndlf[] mbkfGfttfrs(Clbss<?> dbmiClbss, String typfs, MftiodHbndlf[] mis) {
            if (mis == null)  mis = nfw MftiodHbndlf[typfs.lfngti()];
            for (int i = 0; i < mis.lfngti; ++i) {
                mis[i] = mbkfGfttfr(dbmiClbss, typfs, i);
                bssfrt(mis[i].intfrnblMfmbfrNbmf().gftDfdlbringClbss() == dbmiClbss);
            }
            rfturn mis;
        }

        stbtid MftiodHbndlf[] mbkfCtors(Clbss<? fxtfnds BoundMftiodHbndlf> dbmi, String typfs, MftiodHbndlf mis[]) {
            if (mis == null)  mis = nfw MftiodHbndlf[1];
            if (typfs.fqubls(""))  rfturn mis;  // ibdk for fmpty BMH spfdifs
            mis[0] = mbkfCbmiCtor(dbmi, typfs);
            rfturn mis;
        }

        stbtid NbmfdFundtion[] mbkfNominblGfttfrs(String typfs, NbmfdFundtion[] nfs, MftiodHbndlf[] gfttfrs) {
            if (nfs == null)  nfs = nfw NbmfdFundtion[typfs.lfngti()];
            for (int i = 0; i < nfs.lfngti; ++i) {
                nfs[i] = nfw NbmfdFundtion(gfttfrs[i]);
            }
            rfturn nfs;
        }

        //
        // Auxilibry mftiods.
        //

        stbtid SpfdifsDbtb spfdifsDbtbFromCondrftfBMHClbss(Clbss<? fxtfnds BoundMftiodHbndlf> dbmi) {
            try {
                Fifld F_SPECIES_DATA = dbmi.gftDfdlbrfdFifld("SPECIES_DATA");
                rfturn (SpfdifsDbtb) F_SPECIES_DATA.gft(null);
            } dbtdi (RfflfdtivfOpfrbtionExdfption fx) {
                tirow nfwIntfrnblError(fx);
            }
        }

        /**
         * Fifld nbmfs in dondrftf BMHs bdifrf to tiis pbttfrn:
         * brg + typf + indfx
         * wifrf typf is b singlf dibrbdtfr (L, I, J, F, D).
         */
        privbtf stbtid String mbkfFifldNbmf(String typfs, int indfx) {
            bssfrt indfx >= 0 && indfx < typfs.lfngti();
            rfturn "brg" + typfs.dibrAt(indfx) + indfx;
        }

        privbtf stbtid String mbkfSignbturf(String typfs, boolfbn dtor) {
            StringBuildfr buf = nfw StringBuildfr(SIG_INCIPIT);
            for (dibr d : typfs.toCibrArrby()) {
                buf.bppfnd(typfSig(d));
            }
            rfturn buf.bppfnd(')').bppfnd(dtor ? "V" : BMH_SIG).toString();
        }

        stbtid MftiodHbndlf mbkfCbmiCtor(Clbss<? fxtfnds BoundMftiodHbndlf> dbmi, String typfs) {
            try {
                rfturn LOOKUP.findStbtid(dbmi, "mbkf", MftiodTypf.fromMftiodDfsdriptorString(mbkfSignbturf(typfs, fblsf), null));
            } dbtdi (NoSudiMftiodExdfption | IllfgblAddfssExdfption | IllfgblArgumfntExdfption | TypfNotPrfsfntExdfption f) {
                tirow nfwIntfrnblError(f);
            }
        }
    }

    privbtf stbtid finbl Lookup LOOKUP = Lookup.IMPL_LOOKUP;

    /**
     * All subdlbssfs must providf sudi b vbluf dfsdribing tifir typf signbturf.
     */
    stbtid finbl SpfdifsDbtb SPECIES_DATA = SpfdifsDbtb.EMPTY;

    privbtf stbtid finbl SpfdifsDbtb[] SPECIES_DATA_CACHE = nfw SpfdifsDbtb[5];
    privbtf stbtid SpfdifsDbtb difdkCbdif(int sizf, String typfs) {
        int idx = sizf - 1;
        SpfdifsDbtb dbtb = SPECIES_DATA_CACHE[idx];
        if (dbtb != null)  rfturn dbtb;
        SPECIES_DATA_CACHE[idx] = dbtb = gftSpfdifsDbtb(typfs);
        rfturn dbtb;
    }
    stbtid SpfdifsDbtb spfdifsDbtb_L()     { rfturn difdkCbdif(1, "L"); }
    stbtid SpfdifsDbtb spfdifsDbtb_LL()    { rfturn difdkCbdif(2, "LL"); }
    stbtid SpfdifsDbtb spfdifsDbtb_LLL()   { rfturn difdkCbdif(3, "LLL"); }
    stbtid SpfdifsDbtb spfdifsDbtb_LLLL()  { rfturn difdkCbdif(4, "LLLL"); }
    stbtid SpfdifsDbtb spfdifsDbtb_LLLLL() { rfturn difdkCbdif(5, "LLLLL"); }
}
