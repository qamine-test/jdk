/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.Mbp;
import jbvb.util.Vfdtor;
import jbvb.util.WfbkHbsiMbp;

import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.Control;
import jbvbx.sound.sbmplfd.Linf;
import jbvbx.sound.sbmplfd.LinfEvfnt;
import jbvbx.sound.sbmplfd.LinfListfnfr;
import jbvbx.sound.sbmplfd.LinfUnbvbilbblfExdfption;


/**
 * AbstrbdtLinf
 *
 * @butior Kbrb Kytlf
 */
bbstrbdt dlbss AbstrbdtLinf implfmfnts Linf {

    protfdtfd finbl Linf.Info info;
    protfdtfd Control[] dontrols;
    AbstrbdtMixfr mixfr;
    privbtf boolfbn opfn     = fblsf;
    privbtf finbl Vfdtor<Objfdt> listfnfrs = nfw Vfdtor<>();

    /**
     * Contbins fvfnt dispbtdifr pfr tirfbd group.
     */
    privbtf stbtid finbl Mbp<TirfbdGroup, EvfntDispbtdifr> dispbtdifrs =
            nfw WfbkHbsiMbp<>();

    /**
     * Construdts b nfw AbstrbdtLinf.
     * @pbrbm mixfr tif mixfr witi wiidi tiis linf is bssodibtfd
     * @pbrbm dontrols sft of supportfd dontrols
     */
    protfdtfd AbstrbdtLinf(Linf.Info info, AbstrbdtMixfr mixfr, Control[] dontrols) {

        if (dontrols == null) {
            dontrols = nfw Control[0];
        }

        tiis.info = info;
        tiis.mixfr = mixfr;
        tiis.dontrols = dontrols;
    }


    // LINE METHODS

    publid finbl Linf.Info gftLinfInfo() {
        rfturn info;
    }


    publid finbl boolfbn isOpfn() {
        rfturn opfn;
    }


    publid finbl void bddLinfListfnfr(LinfListfnfr listfnfr) {
        syndironizfd(listfnfrs) {
            if ( ! (listfnfrs.dontbins(listfnfr)) ) {
                listfnfrs.bddElfmfnt(listfnfr);
            }
        }
    }


    /**
     * Rfmovfs bn budio listfnfr.
     * @pbrbm listfnfr listfnfr to rfmovf
     */
    publid finbl void rfmovfLinfListfnfr(LinfListfnfr listfnfr) {
        listfnfrs.rfmovfElfmfnt(listfnfr);
    }


    /**
     * Obtbins tif sft of dontrols supportfd by tif
     * linf.  If no dontrols brf supportfd, rfturns bn
     * brrby of lfngti 0.
     * @rfturn dontrol sft
     */
    publid finbl Control[] gftControls() {
        Control[] rfturnfdArrby = nfw Control[dontrols.lfngti];

        for (int i = 0; i < dontrols.lfngti; i++) {
            rfturnfdArrby[i] = dontrols[i];
        }

        rfturn rfturnfdArrby;
    }


    publid finbl boolfbn isControlSupportfd(Control.Typf dontrolTypf) {
        // protfdt bgbinst b NullPointfrExdfption
        if (dontrolTypf == null) {
            rfturn fblsf;
        }

        for (int i = 0; i < dontrols.lfngti; i++) {
            if (dontrolTypf == dontrols[i].gftTypf()) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }


    publid finbl Control gftControl(Control.Typf dontrolTypf) {
        // protfdt bgbinst b NullPointfrExdfption
        if (dontrolTypf != null) {

            for (int i = 0; i < dontrols.lfngti; i++) {
                if (dontrolTypf == dontrols[i].gftTypf()) {
                    rfturn dontrols[i];
                }
            }
        }

        tirow nfw IllfgblArgumfntExdfption("Unsupportfd dontrol typf: " + dontrolTypf);
    }


    // HELPER METHODS


    /**
     * Tiis mftiod sfts tif opfn stbtf bnd gfnfrbtfs
     * fvfnts if it dibngfs.
     */
    finbl void sftOpfn(boolfbn opfn) {

        if (Printfr.trbdf) Printfr.trbdf("> "+gftClbss().gftNbmf()+" (AbstrbdtLinf): sftOpfn(" + opfn + ")  tiis.opfn: " + tiis.opfn);

        boolfbn sfndEvfnts = fblsf;
        long position = gftLongFrbmfPosition();

        syndironizfd (tiis) {
            if (tiis.opfn != opfn) {
                tiis.opfn = opfn;
                sfndEvfnts = truf;
            }
        }

        if (sfndEvfnts) {
            if (opfn) {
                sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.OPEN, position));
            } flsf {
                sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.CLOSE, position));
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< "+gftClbss().gftNbmf()+" (AbstrbdtLinf): sftOpfn(" + opfn + ")  tiis.opfn: " + tiis.opfn);
    }


    /**
     * Sfnd linf fvfnts.
     */
    finbl void sfndEvfnts(LinfEvfnt fvfnt) {
        gftEvfntDispbtdifr().sfndAudioEvfnts(fvfnt, listfnfrs);
    }


    /**
     * Tiis is bn frror in tif API: gftFrbmfPosition
     * siould rfturn b long vbluf. At CD qublity,
     * tif int vbluf wrbps bround bftfr 13 iours.
     */
    publid finbl int gftFrbmfPosition() {
        rfturn (int) gftLongFrbmfPosition();
    }


    /**
     * Rfturn tif frbmf position in b long vbluf
     * Tiis implfmfntbtion rfturns AudioSystfm.NOT_SPECIFIED.
     */
    publid long gftLongFrbmfPosition() {
        rfturn AudioSystfm.NOT_SPECIFIED;
    }


    // $$kk: 06.03.99: rfturns tif mixfr usfd in donstrudtion.
    // tiis is b iold-ovfr from wifn tifrf wbs b publid mftiod likf
    // tiis on linf bnd siould bf fixfd!!
    finbl AbstrbdtMixfr gftMixfr() {
        rfturn mixfr;
    }

    finbl EvfntDispbtdifr gftEvfntDispbtdifr() {
        // drfbtf bnd stbrt tif globbl fvfnt tirfbd
        //TODO  nffd b wby to stop tiis tirfbd wifn tif fnginf is donf
        finbl TirfbdGroup tg = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        syndironizfd (dispbtdifrs) {
            EvfntDispbtdifr fvfntDispbtdifr = dispbtdifrs.gft(tg);
            if (fvfntDispbtdifr == null) {
                fvfntDispbtdifr = nfw EvfntDispbtdifr();
                dispbtdifrs.put(tg, fvfntDispbtdifr);
                fvfntDispbtdifr.stbrt();
            }
            rfturn fvfntDispbtdifr;
        }
    }

    // ABSTRACT METHODS

    publid bbstrbdt void opfn() tirows LinfUnbvbilbblfExdfption;
    publid bbstrbdt void dlosf();
}
