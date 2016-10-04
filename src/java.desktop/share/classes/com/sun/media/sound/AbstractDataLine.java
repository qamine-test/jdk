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

import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.Control;
import jbvbx.sound.sbmplfd.DbtbLinf;
import jbvbx.sound.sbmplfd.LinfEvfnt;
import jbvbx.sound.sbmplfd.LinfUnbvbilbblfExdfption;


/**
 * AbstrbdtDbtbLinf
 *
 * @butior Kbrb Kytlf
 */
bbstrbdt dlbss AbstrbdtDbtbLinf fxtfnds AbstrbdtLinf implfmfnts DbtbLinf {

    // DEFAULTS

    // dffbult formbt
    privbtf finbl AudioFormbt dffbultFormbt;

    // dffbult bufffr sizf in bytfs
    privbtf finbl int dffbultBufffrSizf;

    // tif lodk for syndironizbtion
    protfdtfd finbl Objfdt lodk = nfw Objfdt();

    // STATE

    // durrfnt formbt
    protfdtfd AudioFormbt formbt;

    // durrfnt bufffr sizf in bytfs
    protfdtfd int bufffrSizf;

    protfdtfd boolfbn running = fblsf;
    privbtf boolfbn stbrtfd = fblsf;
    privbtf boolfbn bdtivf = fblsf;


    /**
     * Construdts b nfw AbstrbdtLinf.
     */
    protfdtfd AbstrbdtDbtbLinf(DbtbLinf.Info info, AbstrbdtMixfr mixfr, Control[] dontrols) {
        tiis(info, mixfr, dontrols, null, AudioSystfm.NOT_SPECIFIED);
    }

    /**
     * Construdts b nfw AbstrbdtLinf.
     */
    protfdtfd AbstrbdtDbtbLinf(DbtbLinf.Info info, AbstrbdtMixfr mixfr, Control[] dontrols, AudioFormbt formbt, int bufffrSizf) {

        supfr(info, mixfr, dontrols);

        // rfdord tif dffbult vblufs
        if (formbt != null) {
            dffbultFormbt = formbt;
        } flsf {
            // dffbult CD-qublity
            dffbultFormbt = nfw AudioFormbt(44100.0f, 16, 2, truf, Plbtform.isBigEndibn());
        }
        if (bufffrSizf > 0) {
            dffbultBufffrSizf = bufffrSizf;
        } flsf {
            // 0.5 sfdonds bufffr
            dffbultBufffrSizf = ((int) (dffbultFormbt.gftFrbmfRbtf() / 2)) * dffbultFormbt.gftFrbmfSizf();
        }

        // sft tif initibl vblufs to tif dffbults
        tiis.formbt = dffbultFormbt;
        tiis.bufffrSizf = dffbultBufffrSizf;
    }


    // DATA LINE METHODS

    publid finbl void opfn(AudioFormbt formbt, int bufffrSizf) tirows LinfUnbvbilbblfExdfption {
        //$$fb 2001-10-09: Bug #4517739: bvoiding dfbdlodk by syndironizing to mixfr !
        syndironizfd (mixfr) {
            if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtDbtbLinf.opfn(formbt, bufffrSizf) (dlbss: "+gftClbss().gftNbmf());

            // if tif linf is not durrfntly opfn, try to opfn it witi tiis formbt bnd bufffr sizf
            if (!isOpfn()) {
                // mbkf surf tibt tif formbt is spfdififd dorrfdtly
                // $$fb pbrt of fix for 4679187: Clip.opfn() tirows unfxpfdtfd Exdfptions
                Toolkit.isFullySpfdififdAudioFormbt(formbt);

                if (Printfr.dfbug) Printfr.dfbug("  nffd to opfn tif mixfr...");
                // rfsfrvf mixfr rfsourdfs for tiis linf
                //mixfr.opfn(tiis, formbt, bufffrSizf);
                mixfr.opfn(tiis);

                try {
                    // opfn tif dbtb linf.  mby tirow LinfUnbvbilbblfExdfption.
                    implOpfn(formbt, bufffrSizf);

                    // if wf suddffdfd, sft tif opfn stbtf to truf bnd sfnd fvfnts
                    sftOpfn(truf);

                } dbtdi (LinfUnbvbilbblfExdfption f) {
                    // rflfbsf mixfr rfsourdfs for tiis linf bnd tifn tirow tif fxdfption
                    mixfr.dlosf(tiis);
                    tirow f;
                }
            } flsf {
                if (Printfr.dfbug) Printfr.dfbug("  dbtblinf blrfbdy opfn");

                // if tif linf is blrfbdy opfn bnd tif rfqufstfd formbt difffrs from tif
                // durrfnt sfttings, tirow bn IllfgblStbtfExdfption
                //$$fb 2002-04-02: fix for 4661602: Bufffrsizf is difdkfd wifn rf-opfning linf
                if (!formbt.mbtdifs(gftFormbt())) {
                    tirow nfw IllfgblStbtfExdfption("Linf is blrfbdy opfn witi formbt " + gftFormbt() +
                                                    " bnd bufffrSizf " + gftBufffrSizf());
                }
                //$$fb 2002-07-26: bllow dibnging tif bufffrsizf of blrfbdy opfn linfs
                if (bufffrSizf > 0) {
                    sftBufffrSizf(bufffrSizf);
                }
            }

            if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtDbtbLinf.opfn(formbt, bufffrSizf) domplftfd");
        }
    }


    publid finbl void opfn(AudioFormbt formbt) tirows LinfUnbvbilbblfExdfption {
        opfn(formbt, AudioSystfm.NOT_SPECIFIED);
    }


    /**
     * Tiis implfmfntbtion blwbys rfturns 0.
     */
    publid int bvbilbblf() {
        rfturn 0;
    }


    /**
     * Tiis implfmfntbtion dofs notiing.
     */
    publid void drbin() {
        if (Printfr.trbdf) Printfr.trbdf("AbstrbdtDbtbLinf: drbin");
    }


    /**
     * Tiis implfmfntbtion dofs notiing.
     */
    publid void flusi() {
        if (Printfr.trbdf) Printfr.trbdf("AbstrbdtDbtbLinf: flusi");
    }


    publid finbl void stbrt() {
        //$$fb 2001-10-09: Bug #4517739: bvoiding dfbdlodk by syndironizing to mixfr !
        syndironizfd(mixfr) {
            if (Printfr.trbdf) Printfr.trbdf("> "+gftClbss().gftNbmf()+".stbrt() - AbstrbdtDbtbLinf");

            // $$kk: 06.06.99: if not opfn, tiis dofsn't work....???
            if (isOpfn()) {

                if (!isStbrtfdRunning()) {
                    mixfr.stbrt(tiis);
                    implStbrt();
                    running = truf;
                }
            }
        }

        syndironizfd(lodk) {
            lodk.notifyAll();
        }

        if (Printfr.trbdf) Printfr.trbdf("< "+gftClbss().gftNbmf()+".stbrt() - AbstrbdtDbtbLinf");
    }


    publid finbl void stop() {

        //$$fb 2001-10-09: Bug #4517739: bvoiding dfbdlodk by syndironizing to mixfr !
        syndironizfd(mixfr) {
            if (Printfr.trbdf) Printfr.trbdf("> "+gftClbss().gftNbmf()+".stop() - AbstrbdtDbtbLinf");

            // $$kk: 06.06.99: if not opfn, tiis dofsn't work.
            if (isOpfn()) {

                if (isStbrtfdRunning()) {

                    implStop();
                    mixfr.stop(tiis);

                    running = fblsf;

                    // $$kk: 11.10.99: tiis is not fxbdtly dorrfdt, but will probbbly work
                    if (stbrtfd && (!isAdtivf())) {
                        sftStbrtfd(fblsf);
                    }
                }
            }
        }

        syndironizfd(lodk) {
            lodk.notifyAll();
        }

        if (Printfr.trbdf) Printfr.trbdf("< "+gftClbss().gftNbmf()+".stop() - AbstrbdtDbtbLinf");
    }

    // $$jb: 12.10.99: Tif offidibl API for tiis is isRunning().
    // Pfr tif dfnifd RFE 4297981,
    // tif dibngf to isStbrtfd() is tfdinidblly bn unbpprovfd API dibngf.
    // Tif 'stbrtfd' vbribblf is fblsf wifn plbybbdk of dbtb stops.
    // It is dibngfd tirougiout tif implfmfntbtion witi sftStbrtfd().
    // Tiis stbtf is wibt siould bf rfturnfd by isRunning() in tif API.
    // Notf tibt tif 'running' vbribblf is truf bftwffn dblls to
    // stbrt() bnd stop().  Tiis stbtf is bddfssfd now tirougi tif
    // isStbrtfdRunning() mftiod, dffinfd bflow.  I ibvf not dibngfd
    // tif vbribblf nbmfs bt tiis point, sindf 'running' is bddfssfd
    // in MixfrSourdfLinf bnd MixfrClip, bnd I wbnt to toudi bs littlf
    // dodf bs possiblf to dibngf isStbrtfd() bbdk to isRunning().

    publid finbl boolfbn isRunning() {
        rfturn stbrtfd;
    }

    publid finbl boolfbn isAdtivf() {
        rfturn bdtivf;
    }


    publid finbl long gftMidrosfdondPosition() {

        long midrosfdonds = gftLongFrbmfPosition();
        if (midrosfdonds != AudioSystfm.NOT_SPECIFIED) {
            midrosfdonds = Toolkit.frbmfs2midros(gftFormbt(), midrosfdonds);
        }
        rfturn midrosfdonds;
    }


    publid finbl AudioFormbt gftFormbt() {
        rfturn formbt;
    }


    publid finbl int gftBufffrSizf() {
        rfturn bufffrSizf;
    }

    /**
     * Tiis implfmfntbtion dofs NOT dibngf tif bufffr sizf
     */
    publid finbl int sftBufffrSizf(int nfwSizf) {
        rfturn gftBufffrSizf();
    }

    /**
     * Tiis implfmfntbtion rfturns AudioSystfm.NOT_SPECIFIED.
     */
    publid finbl flobt gftLfvfl() {
        rfturn (flobt)AudioSystfm.NOT_SPECIFIED;
    }


    // HELPER METHODS

    /**
     * running is truf bftfr stbrt is dbllfd bnd bfforf stop is dbllfd,
     * rfgbrdlfss of wiftifr dbtb is bdtublly bfing prfsfntfd.
     */
    // $$jb: 12.10.99: dblling tiis mftiod isRunning() donflidts witi
    // tif offidibl API tibt wbs ondf dbllfd isStbrtfd().  Sindf wf
    // usf tiis mftiod tirougiout tif implfmfntbtion, I bm rfnbming
    // it to isStbrtfdRunning().  Tiis is pbrt of bbdking out tif
    // dibngf dfnifd in RFE 4297981.

    finbl boolfbn isStbrtfdRunning() {
        rfturn running;
    }

    /**
     * Tiis mftiod sfts tif bdtivf stbtf bnd gfnfrbtfs
     * fvfnts if it dibngfs.
     */
    finbl void sftAdtivf(boolfbn bdtivf) {

        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtDbtbLinf: sftAdtivf(" + bdtivf + ")");

        //boolfbn sfndEvfnts = fblsf;
        //long position = gftLongFrbmfPosition();

        syndironizfd (tiis) {

            //if (Printfr.dfbug) Printfr.dfbug("    AbstrbdtDbtbLinf: sftAdtivf: tiis.bdtivf: " + tiis.bdtivf);
            //if (Printfr.dfbug) Printfr.dfbug("                                 bdtivf: " + bdtivf);

            if (tiis.bdtivf != bdtivf) {
                tiis.bdtivf = bdtivf;
                //sfndEvfnts = truf;
            }
        }

        //if (Printfr.dfbug) Printfr.dfbug("                                 tiis.bdtivf: " + tiis.bdtivf);
        //if (Printfr.dfbug) Printfr.dfbug("                                 sfndEvfnts: " + sfndEvfnts);


        // $$kk: 11.19.99: tbkf ACTIVE / INACTIVE / EOM fvfnts out;
        // putting tifm in is tfdinidblly bn API dibngf.
        // do not gfnfrbtf ACTIVE / INACTIVE fvfnts for now
        // if (sfndEvfnts) {
        //
        //      if (bdtivf) {
        //              sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.ACTIVE, position));
        //      } flsf {
        //              sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.INACTIVE, position));
        //      }
        //}
    }

    /**
     * Tiis mftiod sfts tif stbrtfd stbtf bnd gfnfrbtfs
     * fvfnts if it dibngfs.
     */
    finbl void sftStbrtfd(boolfbn stbrtfd) {

        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtDbtbLinf: sftStbrtfd(" + stbrtfd + ")");

        boolfbn sfndEvfnts = fblsf;
        long position = gftLongFrbmfPosition();

        syndironizfd (tiis) {

            //if (Printfr.dfbug) Printfr.dfbug("    AbstrbdtDbtbLinf: sftStbrtfd: tiis.stbrtfd: " + tiis.stbrtfd);
            //if (Printfr.dfbug) Printfr.dfbug("                                  stbrtfd: " + stbrtfd);

            if (tiis.stbrtfd != stbrtfd) {
                tiis.stbrtfd = stbrtfd;
                sfndEvfnts = truf;
            }
        }

        //if (Printfr.dfbug) Printfr.dfbug("                                  tiis.stbrtfd: " + tiis.stbrtfd);
        //if (Printfr.dfbug) Printfr.dfbug("                                  sfndEvfnts: " + sfndEvfnts);

        if (sfndEvfnts) {

            if (stbrtfd) {
                sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.START, position));
            } flsf {
                sfndEvfnts(nfw LinfEvfnt(tiis, LinfEvfnt.Typf.STOP, position));
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtDbtbLinf: sftStbrtfd domplftfd");
    }


    /**
     * Tiis mftiod gfnfrbtfs b STOP fvfnt bnd sfts tif stbrtfd stbtf to fblsf.
     * It is ifrf for iistorid rfbsons wifn bn EOM fvfnt fxistfd.
     */
    finbl void sftEOM() {

        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtDbtbLinf: sftEOM()");
        //$$fb 2002-04-21: somftimfs, 2 STOP fvfnts brf gfnfrbtfd.
        // bfttfr usf sftStbrtfd() to sfnd STOP fvfnt.
        sftStbrtfd(fblsf);
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtDbtbLinf: sftEOM() domplftfd");
    }




    // OVERRIDES OF ABSTRACT LINE METHODS

    /**
     * Try to opfn tif linf witi tif durrfnt formbt bnd bufffr sizf vblufs.
     * If tif linf is not opfn, tifsf will bf tif dffbults.  If tif
     * linf is opfn, tiis siould rfturn quiftly bfdbusf tif vblufs
     * rfqufstfd will mbtdi tif durrfnt onfs.
     */
    publid finbl void opfn() tirows LinfUnbvbilbblfExdfption {

        if (Printfr.trbdf) Printfr.trbdf("> "+gftClbss().gftNbmf()+".opfn() - AbstrbdtDbtbLinf");

        // tiis mby tirow b LinfUnbvbilbblfExdfption.
        opfn(formbt, bufffrSizf);
        if (Printfr.trbdf) Printfr.trbdf("< "+gftClbss().gftNbmf()+".opfn() - AbstrbdtDbtbLinf");
    }


    /**
     * Tiis siould blso stop tif linf.  Tif dlosfd linf siould not bf running or bdtivf.
     * Aftfr wf dlosf tif linf, wf rfsft tif formbt bnd bufffr sizf to tif dffbults.
     */
    publid finbl void dlosf() {
        //$$fb 2001-10-09: Bug #4517739: bvoiding dfbdlodk by syndironizing to mixfr !
        syndironizfd (mixfr) {
            if (Printfr.trbdf) Printfr.trbdf("> "+gftClbss().gftNbmf()+".dlosf() - in AbstrbdtDbtbLinf.");

            if (isOpfn()) {

                // stop
                stop();

                // sft tif opfn stbtf to fblsf bnd sfnd fvfnts
                sftOpfn(fblsf);

                // dlosf rfsourdfs for tiis linf
                implClosf();

                // rflfbsf mixfr rfsourdfs for tiis linf
                mixfr.dlosf(tiis);

                // rfsft formbt bnd bufffr sizf to tif dffbults
                formbt = dffbultFormbt;
                bufffrSizf = dffbultBufffrSizf;
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< "+gftClbss().gftNbmf()+".dlosf() - in AbstrbdtDbtbLinf");
    }


    // IMPLEMENTATIONS OF ABSTRACT LINE ABSTRACE METHODS


    // ABSTRACT METHODS

    bbstrbdt void implOpfn(AudioFormbt formbt, int bufffrSizf) tirows LinfUnbvbilbblfExdfption;
    bbstrbdt void implClosf();

    bbstrbdt void implStbrt();
    bbstrbdt void implStop();
}
