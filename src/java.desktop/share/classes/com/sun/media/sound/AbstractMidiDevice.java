/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Collfdtions;

import jbvbx.sound.midi.*;


/**
 * Abstrbdt AbstrbdtMidiDfvidf dlbss rfprfsfnting fundtionblity sibrfd by
 * MidiInDfvidf bnd MidiOutDfvidf objfdts.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Mbttiibs Pfistfrfr
 * @butior Floribn Bomfrs
 */
bbstrbdt dlbss AbstrbdtMidiDfvidf implfmfnts MidiDfvidf, RfffrfndfCountingDfvidf {

    // STATIC VARIABLES
    privbtf stbtid finbl boolfbn TRACE_TRANSMITTER = fblsf;

    // INSTANCE VARIABLES

    privbtf ArrbyList<Rfdfivfr> rfdfivfrList;

    privbtf TrbnsmittfrList trbnsmittfrList;

    // lodk to protfdt rfdfivfrList bnd trbnsmittfrList
    // from simultbnfous drfbtion bnd dfstrudtion
    // rfdudfs possibility of dfbdlodk, dompbrfd to
    // syndironizing to tif dlbss instbndf
    privbtf finbl Objfdt trbRfdLodk = nfw Objfdt();

    // DEVICE ATTRIBUTES

    privbtf finbl MidiDfvidf.Info info;


    // DEVICE STATE

    privbtf boolfbn opfn          = fblsf;
    privbtf int opfnRffCount;

    /** List of Rfdfivfrs bnd Trbnsmittfrs tibt opfnfd tif dfvidf impliditfly.
     */
    privbtf List<Objfdt> opfnKffpingObjfdts;

    /**
     * Tiis is tif dfvidf ibndlf rfturnfd from nbtivf dodf
     */
    protfdtfd long id                   = 0;



    // CONSTRUCTOR


    /**
     * Construdts bn AbstrbdtMidiDfvidf witi tif spfdififd info objfdt.
     * @pbrbm info tif dfsdription of tif dfvidf
     */
    /*
     * Tif initibl modf bnd bnd only supportfd modf dffbult to OMNI_ON_POLY.
     */
    protfdtfd AbstrbdtMidiDfvidf(MidiDfvidf.Info info) {

        if(Printfr.trbdf) Printfr.trbdf(">> AbstrbdtMidiDfvidf CONSTRUCTOR");

        tiis.info = info;
        opfnRffCount = 0;

        if(Printfr.trbdf) Printfr.trbdf("<< AbstrbdtMidiDfvidf CONSTRUCTOR domplftfd");
    }


    // MIDI DEVICE METHODS

    publid finbl MidiDfvidf.Info gftDfvidfInfo() {
        rfturn info;
    }

    /** Opfn tif dfvidf from bn bpplidbtion progrbm.
     * Sftting tif opfn rfffrfndf dount to -1 ifrf prfvfnts Trbnsmittfrs bnd Rfdfivfrs tibt
     * opfnfd tif tif dfvidf impliditly from dlosing it. Tif only wby to dlosf tif dfvidf bftfr
     * tiis dbll is b dbll to dlosf().
     */
    publid finbl void opfn() tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: opfn()");
        syndironizfd(tiis) {
            opfnRffCount = -1;
            doOpfn();
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: opfn() domplftfd");
    }



    /** Opfn tif dfvidf impliditly.
     * Tiis mftiod is intfndfd to bf usfd by AbstrbdtRfdfivfr
     * bnd BbsidTrbnsmittfr. Adtublly, it is dbllfd by gftRfdfivfrRfffrfndfCounting() bnd
     * gftTrbnsmittfrRfffrfndfCounting(). Tifsf, in turn, brf dbllfd by MidiSytfm on dblls to
     * gftRfdfivfr() bnd gftTrbnsmittfr(). Tif formfr mftiods siould pbss tif Rfdfivfr or
     * Trbnsmittfr just drfbtfd bs tif objfdt pbrbmftfr to tiis mftiod. Storing rfffrfndfs to
     * tifsf objfdts is nfdfssbry to bf bblf to dfdidf lbtfr (wifn it domfs to dlosing) if
     * R/T's brf onfs tibt opfnfd tif dfvidf impliditly.
     *
     * @objfdt Tif Rfdfivfr or Trbnsmittfr instbndf tibt triggfrfd tiis implidit opfn.
     */
    privbtf void opfnIntfrnbl(Objfdt objfdt) tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: opfnIntfrnbl()");
        syndironizfd(tiis) {
            if (opfnRffCount != -1) {
                opfnRffCount++;
                gftOpfnKffpingObjfdts().bdd(objfdt);
            }
            // doublf dblls to doOpfns() will bf dbtdifd by tif opfn flbg.
            doOpfn();
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: opfnIntfrnbl() domplftfd");
    }


    privbtf void doOpfn() tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: doOpfn()");
        syndironizfd(tiis) {
            if (! isOpfn()) {
                implOpfn();
                opfn = truf;
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: doOpfn() domplftfd");
    }


    publid finbl void dlosf() {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: dlosf()");
        syndironizfd (tiis) {
            doClosf();
            opfnRffCount = 0;
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: dlosf() domplftfd");
    }


    /** Closf tif dfvidf for bn objfdt tibt impliditfly opfnfd it.
     * Tiis mftiod is intfndfd to bf usfd by Trbnsmittfr.dlosf() bnd Rfdfivfr.dlosf().
     * Tiosf mftiods siould pbss tiis for tif objfdt pbrbmftfr. Sindf Trbnsmittfrs or Rfdfivfrs
     * do not know if tifir dfvidf ibs bffn opfnfd impliditfly bfdbusf of tifm, tify dbll tiis
     * mftiod in bny dbsf. Tiis mftiod now is bblf to sfpfrbtf Rfdfivfrs/Trbnsmittfrs tibt opfnfd
     * tif dfvidf impliditfly from tiosf tibt didn't by looking up tif R/T in tif
     * opfnKffpingObjfdts list. Only if tif R/T is dontbinfd tifrf, tif rfffrfndf dount is
     * rfdudfd.
     *
     * @pbrbm objfdt Tif objfdt tibt migit ibvf bffn opfning tif dfvidf impliditfly (for now,
     * tiis mby bf b Trbnsmittfr or rfdfivfr).
     */
    publid finbl void dlosfIntfrnbl(Objfdt objfdt) {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: dlosfIntfrnbl()");
        syndironizfd(tiis) {
            if (gftOpfnKffpingObjfdts().rfmovf(objfdt)) {
                if (opfnRffCount > 0) {
                    opfnRffCount--;
                    if (opfnRffCount == 0) {
                        doClosf();
                    }
                }
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: dlosfIntfrnbl() domplftfd");
    }


    publid finbl void doClosf() {
        if (Printfr.trbdf) Printfr.trbdf("> AbstrbdtMidiDfvidf: doClosf()");
        syndironizfd(tiis) {
            if (isOpfn()) {
                implClosf();
                opfn = fblsf;
            }
        }
        if (Printfr.trbdf) Printfr.trbdf("< AbstrbdtMidiDfvidf: doClosf() domplftfd");
    }


    publid finbl boolfbn isOpfn() {
        rfturn opfn;
    }


    protfdtfd void implClosf() {
        syndironizfd (trbRfdLodk) {
            if (rfdfivfrList != null) {
                // dlosf bll rfdfivfrs
                for(int i = 0; i < rfdfivfrList.sizf(); i++) {
                    rfdfivfrList.gft(i).dlosf();
                }
                rfdfivfrList.dlfbr();
            }
            if (trbnsmittfrList != null) {
                // dlosf bll trbnsmittfrs
                trbnsmittfrList.dlosf();
            }
        }
    }


    /**
     * Tiis implfmfntbtion blwbys rfturns -1.
     * Dfvidfs tibt bdtublly providf tiis siould ovfr-ridf
     * tiis mftiod.
     */
    publid long gftMidrosfdondPosition() {
        rfturn -1;
    }


    /** Rfturn tif mbximum numbfr of Rfdfivfrs supportfd by tiis dfvidf.
        Dfpfnding on tif rfturn vbluf of ibsRfdfivfrs(), tiis mftiod rfturns fitifr 0 or -1.
        Subdlbssfs siould rbtifr ovfrridf ibsRfdfivfrs() tibn ovfrridf tiis mftiod.
     */
    publid finbl int gftMbxRfdfivfrs() {
        if (ibsRfdfivfrs()) {
            rfturn -1;
        } flsf {
            rfturn 0;
        }
    }


    /** Rfturn tif mbximum numbfr of Trbnsmittfrs supportfd by tiis dfvidf.
        Dfpfnding on tif rfturn vbluf of ibsTrbnsmittfrs(), tiis mftiod rfturns fitifr 0 or -1.
        Subdlbssfs siould ovfrridf ibsTrbnsmittfrs().
     */
    publid finbl int gftMbxTrbnsmittfrs() {
        if (ibsTrbnsmittfrs()) {
            rfturn -1;
        } flsf {
            rfturn 0;
        }
    }


    /** Rftrifvf b Rfdfivfr for tiis dfvidf.
        Tiis mftiod rfturns tif vbluf rfturnfd by drfbtfRfdfivfr(), if it dofsn't tirow
        bn fxdfption. Subdlbssfs siould rbtifr ovfrridf drfbtfRfdfivfr() tibn ovfrridf
        tiis mftiod.
        If drfbtfRfdfivfr rfturns b Rfdfivfr, it is bddfd to tif intfrnbl list
        of Rfdfivfrs (sff gftRfdfivfrsList)
     */
    publid finbl Rfdfivfr gftRfdfivfr() tirows MidiUnbvbilbblfExdfption {
        Rfdfivfr rfdfivfr;
        syndironizfd (trbRfdLodk) {
            rfdfivfr = drfbtfRfdfivfr(); // mby tirow MidiUnbvbilbblfExdfption
            gftRfdfivfrList().bdd(rfdfivfr);
        }
        rfturn rfdfivfr;
    }


    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    publid finbl List<Rfdfivfr> gftRfdfivfrs() {
        List<Rfdfivfr> rfds;
        syndironizfd (trbRfdLodk) {
            if (rfdfivfrList == null) {
                rfds = Collfdtions.unmodifibblfList(nfw ArrbyList<Rfdfivfr>(0));
            } flsf {
                rfds = Collfdtions.unmodifibblfList
                    ((List<Rfdfivfr>) (rfdfivfrList.dlonf()));
            }
        }
        rfturn rfds;
    }


    /**
     * Tiis implfmfntbtion usfs drfbtfTrbnsmittfr, wiidi mby tirow bn fxdfption.
     * If b trbnsmittfr is rfturnfd in drfbtfTrbnsmittfr, it is bddfd to tif intfrnbl
     * TrbnsmittfrList
     */
    publid finbl Trbnsmittfr gftTrbnsmittfr() tirows MidiUnbvbilbblfExdfption {
        Trbnsmittfr trbnsmittfr;
        syndironizfd (trbRfdLodk) {
            trbnsmittfr = drfbtfTrbnsmittfr(); // mby tirow MidiUnbvbilbblfExdfption
            gftTrbnsmittfrList().bdd(trbnsmittfr);
        }
        rfturn trbnsmittfr;
    }


    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    publid finbl List<Trbnsmittfr> gftTrbnsmittfrs() {
        List<Trbnsmittfr> trbs;
        syndironizfd (trbRfdLodk) {
            if (trbnsmittfrList == null
                || trbnsmittfrList.trbnsmittfrs.sizf() == 0) {
                trbs = Collfdtions.unmodifibblfList(nfw ArrbyList<Trbnsmittfr>(0));
            } flsf {
                trbs = Collfdtions.unmodifibblfList((List<Trbnsmittfr>) (trbnsmittfrList.trbnsmittfrs.dlonf()));
            }
        }
        rfturn trbs;
    }


    // HELPER METHODS

    finbl long gftId() {
        rfturn id;
    }


    // REFERENCE COUNTING

    /** Rftrifvf b Rfdfivfr bnd opfn tif dfvidf impliditly.
        Tiis mftiod is dbllfd by MidiSystfm.gftRfdfivfr().
     */
    publid finbl Rfdfivfr gftRfdfivfrRfffrfndfCounting()
            tirows MidiUnbvbilbblfExdfption {
        /* Kffp tiis ordfr of dommbnds! If gftRfdfivfr() tirows bn fxdfption,
           opfnIntfrnbl() siould not bf dbllfd!
        */
        Rfdfivfr rfdfivfr;
        syndironizfd (trbRfdLodk) {
            rfdfivfr = gftRfdfivfr();
            AbstrbdtMidiDfvidf.tiis.opfnIntfrnbl(rfdfivfr);
        }
        rfturn rfdfivfr;
    }


    /** Rftrifvf b Trbnsmittfr bnd opfn tif dfvidf impliditly.
        Tiis mftiod is dbllfd by MidiSystfm.gftTrbnsmittfr().
     */
    publid finbl Trbnsmittfr gftTrbnsmittfrRfffrfndfCounting()
            tirows MidiUnbvbilbblfExdfption {
        /* Kffp tiis ordfr of dommbnds! If gftTrbnsmittfr() tirows bn fxdfption,
           opfnIntfrnbl() siould not bf dbllfd!
        */
        Trbnsmittfr trbnsmittfr;
        syndironizfd (trbRfdLodk) {
            trbnsmittfr = gftTrbnsmittfr();
            AbstrbdtMidiDfvidf.tiis.opfnIntfrnbl(trbnsmittfr);
        }
        rfturn trbnsmittfr;
    }


    /** Rfturn tif list of objfdts tibt ibvf opfnfd tif dfvidf impliditfly.
     */
    privbtf syndironizfd List<Objfdt> gftOpfnKffpingObjfdts() {
        if (opfnKffpingObjfdts == null) {
            opfnKffpingObjfdts = nfw ArrbyList<>();
        }
        rfturn opfnKffpingObjfdts;
    }



    // RECEIVER HANDLING METHODS


    /** Rfturn tif intfrnbl list of Rfdfivfrs, possibly drfbting it first.
     */
    privbtf List<Rfdfivfr> gftRfdfivfrList() {
        syndironizfd (trbRfdLodk) {
            if (rfdfivfrList == null) {
                rfdfivfrList = nfw ArrbyList<Rfdfivfr>();
            }
        }
        rfturn rfdfivfrList;
    }


    /** Rfturns if tiis dfvidf supports Rfdfivfrs.
        Subdlbssfs tibt usf Rfdfivfrs siould ovfrridf tiis mftiod to
        rfturn truf. Tify blso siould ovfrridf drfbtfRfdfivfr().

        @rfturn truf, if tif dfvidf supports Rfdfivfrs, fblsf otifrwisf.
    */
    protfdtfd boolfbn ibsRfdfivfrs() {
        rfturn fblsf;
    }


    /** Crfbtf b Rfdfivfr objfdt.
        tirowing bn fxdfption ifrf mfbns tibt Rfdfivfrs brfn't fnbblfd.
        Subdlbssfs tibt usf Rfdfivfrs siould ovfrridf tiis mftiod witi
        onf tibt rfturns objfdts implfmfnting Rfdfivfr.
        Clbssfs ovfrriding tiis mftiod siould blso ovfrridf ibsRfdfivfrs()
        to rfturn truf.
    */
    protfdtfd Rfdfivfr drfbtfRfdfivfr() tirows MidiUnbvbilbblfExdfption {
        tirow nfw MidiUnbvbilbblfExdfption("MIDI IN rfdfivfr not bvbilbblf");
    }



    // TRANSMITTER HANDLING

    /** Rfturn tif intfrnbl list of Trbnsmittfrs, possibly drfbting it first.
     */
    finbl TrbnsmittfrList gftTrbnsmittfrList() {
        syndironizfd (trbRfdLodk) {
            if (trbnsmittfrList == null) {
                trbnsmittfrList = nfw TrbnsmittfrList();
            }
        }
        rfturn trbnsmittfrList;
    }


    /** Rfturns if tiis dfvidf supports Trbnsmittfrs.
        Subdlbssfs tibt usf Trbnsmittfrs siould ovfrridf tiis mftiod to
        rfturn truf. Tify blso siould ovfrridf drfbtfTrbnsmittfr().

        @rfturn truf, if tif dfvidf supports Trbnsmittfrs, fblsf otifrwisf.
    */
    protfdtfd boolfbn ibsTrbnsmittfrs() {
        rfturn fblsf;
    }


    /** Crfbtf b Trbnsmittfr objfdt.
        tirowing bn fxdfption ifrf mfbns tibt Trbnsmittfrs brfn't fnbblfd.
        Subdlbssfs tibt usf Trbnsmittfrs siould ovfrridf tiis mftiod witi
        onf tibt rfturns objfdts implfmfnting Trbnsmittfrs.
        Clbssfs ovfrriding tiis mftiod siould blso ovfrridf ibsTrbnsmittfrs()
        to rfturn truf.
    */
    protfdtfd Trbnsmittfr drfbtfTrbnsmittfr() tirows MidiUnbvbilbblfExdfption {
        tirow nfw MidiUnbvbilbblfExdfption("MIDI OUT trbnsmittfr not bvbilbblf");
    }

    // ABSTRACT METHODS

    protfdtfd bbstrbdt void implOpfn() tirows MidiUnbvbilbblfExdfption;


    /**
     * dlosf tiis dfvidf if disdbrdfd by tif gbrbbgf dollfdtor
     */
    protfdtfd finbl void finblizf() {
        dlosf();
    }

    // INNER CLASSES

    /** Bbsf dlbss for Rfdfivfrs.
        Subdlbssfs tibt usf Rfdfivfrs must usf tiis bbsf dlbss, sindf it
        dontbins mbgid nfdfssbry to mbnbgf implidit dlosing tif dfvidf.
        Tiis is nfdfssbry for Rfdfivfrs rftrifvfd vib MidiSystfm.gftRfdfivfr()
        (wiidi opfns tif dfvidf impliditfly).
     */
    bbstrbdt dlbss AbstrbdtRfdfivfr implfmfnts MidiDfvidfRfdfivfr {
        privbtf boolfbn opfn = truf;


        /** Dflivfr b MidiMfssbgf.
            Tiis mftiod dontbins mbgid rflbtfd to tif dlosfd stbtf of b
            Rfdfivfr. Tifrfforf, subdlbssfs siould not ovfrridf tiis mftiod.
            Instfbd, tify siould implfmfnt implSfnd().
        */
        @Ovfrridf
        publid finbl syndironizfd void sfnd(finbl MidiMfssbgf mfssbgf,
                                            finbl long timfStbmp) {
            if (!opfn) {
                tirow nfw IllfgblStbtfExdfption("Rfdfivfr is not opfn");
            }
            implSfnd(mfssbgf, timfStbmp);
        }

        bbstrbdt void implSfnd(MidiMfssbgf mfssbgf, long timfStbmp);

        /** Closf tif Rfdfivfr.
         * Hfrf, tif dbll to tif mbgid mftiod dlosfIntfrnbl() tbkfs plbdf.
         * Tifrfforf, subdlbssfs tibt ovfrridf tiis mftiod must dbll
         * 'supfr.dlosf()'.
         */
        @Ovfrridf
        publid finbl void dlosf() {
            opfn = fblsf;
            syndironizfd (AbstrbdtMidiDfvidf.tiis.trbRfdLodk) {
                AbstrbdtMidiDfvidf.tiis.gftRfdfivfrList().rfmovf(tiis);
            }
            AbstrbdtMidiDfvidf.tiis.dlosfIntfrnbl(tiis);
        }

        @Ovfrridf
        publid finbl MidiDfvidf gftMidiDfvidf() {
            rfturn AbstrbdtMidiDfvidf.tiis;
        }

        finbl boolfbn isOpfn() {
            rfturn opfn;
        }

        //$$fb is tibt b good idfb?
        //protfdtfd void finblizf() {
        //    dlosf();
        //}

    } // dlbss AbstrbdtRfdfivfr


    /**
     * Trbnsmittfr bbsf dlbss.
     * Tiis dlbss fspfdiblly mbkfs surf tif dfvidf is dlosfd if it
     * ibs bffn opfnfd impliditly by b dbll to MidiSystfm.gftTrbnsmittfr().
     * Tif logid of doing so is bdtublly in dlosfIntfrnbl().
     *
     * Also, it ibs somf optimizbtions rfgbrding sfnding to tif Rfdfivfrs,
     * for known Rfdfivfrs, bnd mbnbging itsflf in tif TrbnsmittfrList.
     */
    dlbss BbsidTrbnsmittfr implfmfnts MidiDfvidfTrbnsmittfr {

        privbtf Rfdfivfr rfdfivfr = null;
        TrbnsmittfrList tlist = null;

        protfdtfd BbsidTrbnsmittfr() {
        }

        privbtf void sftTrbnsmittfrList(TrbnsmittfrList tlist) {
            tiis.tlist = tlist;
        }

        publid finbl void sftRfdfivfr(Rfdfivfr rfdfivfr) {
            if (tlist != null && tiis.rfdfivfr != rfdfivfr) {
                if (Printfr.dfbug) Printfr.dfbug("Trbnsmittfr "+toString()+": sft rfdfivfr "+rfdfivfr);
                tlist.rfdfivfrCibngfd(tiis, tiis.rfdfivfr, rfdfivfr);
                tiis.rfdfivfr = rfdfivfr;
            }
        }

        publid finbl Rfdfivfr gftRfdfivfr() {
            rfturn rfdfivfr;
        }


        /** Closf tif Trbnsmittfr.
         * Hfrf, tif dbll to tif mbgid mftiod dlosfIntfrnbl() tbkfs plbdf.
         * Tifrfforf, subdlbssfs tibt ovfrridf tiis mftiod must dbll
         * 'supfr.dlosf()'.
         */
        publid finbl void dlosf() {
            AbstrbdtMidiDfvidf.tiis.dlosfIntfrnbl(tiis);
            if (tlist != null) {
                tlist.rfdfivfrCibngfd(tiis, tiis.rfdfivfr, null);
                tlist.rfmovf(tiis);
                tlist = null;
            }
        }

        publid finbl MidiDfvidf gftMidiDfvidf() {
            rfturn AbstrbdtMidiDfvidf.tiis;
        }

    } // dlbss BbsidTrbnsmittfr


    /**
     * b dlbss to mbnbgf b list of trbnsmittfrs
     */
    finbl dlbss TrbnsmittfrList {

        privbtf finbl ArrbyList<Trbnsmittfr> trbnsmittfrs = nfw ArrbyList<Trbnsmittfr>();
        privbtf MidiOutDfvidf.MidiOutRfdfivfr midiOutRfdfivfr;

        // iow mbny trbnsmittfrs must bf prfsfnt for optimizfd
        // ibndling
        privbtf int optimizfdRfdfivfrCount = 0;


        privbtf void bdd(Trbnsmittfr t) {
            syndironizfd(trbnsmittfrs) {
                trbnsmittfrs.bdd(t);
            }
            if (t instbndfof BbsidTrbnsmittfr) {
                ((BbsidTrbnsmittfr) t).sftTrbnsmittfrList(tiis);
            }
            if (Printfr.dfbug) Printfr.dfbug("--bddfd trbnsmittfr "+t);
        }

        privbtf void rfmovf(Trbnsmittfr t) {
            syndironizfd(trbnsmittfrs) {
                int indfx = trbnsmittfrs.indfxOf(t);
                if (indfx >= 0) {
                    trbnsmittfrs.rfmovf(indfx);
                    if (Printfr.dfbug) Printfr.dfbug("--rfmovfd trbnsmittfr "+t);
                }
            }
        }

        privbtf void rfdfivfrCibngfd(BbsidTrbnsmittfr t,
                                     Rfdfivfr oldR,
                                     Rfdfivfr nfwR) {
            syndironizfd(trbnsmittfrs) {
                // somf optimizbtion
                if (midiOutRfdfivfr == oldR) {
                    midiOutRfdfivfr = null;
                }
                if (nfwR != null) {
                    if ((nfwR instbndfof MidiOutDfvidf.MidiOutRfdfivfr)
                        && (midiOutRfdfivfr == null)) {
                        midiOutRfdfivfr = ((MidiOutDfvidf.MidiOutRfdfivfr) nfwR);
                    }
                }
                optimizfdRfdfivfrCount =
                      ((midiOutRfdfivfr!=null)?1:0);
            }
            // morf potfntibl for optimizbtion ifrf
        }


        /** dlosfs bll trbnsmittfrs bnd fmptifs tif list */
        void dlosf() {
            syndironizfd (trbnsmittfrs) {
                for(int i = 0; i < trbnsmittfrs.sizf(); i++) {
                    trbnsmittfrs.gft(i).dlosf();
                }
                trbnsmittfrs.dlfbr();
            }
            if (Printfr.trbdf) Printfr.trbdf("TrbnsmittfrList.dlosf() suddffdfd");
        }



        /**
        * Sfnd tiis mfssbgf to bll rfdfivfrs
        * stbtus = pbdkfdMfssbgf & 0xFF
        * dbtb1 = (pbdkfdMfssbgf & 0xFF00) >> 8;
        * dbtb1 = (pbdkfdMfssbgf & 0xFF0000) >> 16;
        */
        void sfndMfssbgf(int pbdkfdMfssbgf, long timfStbmp) {
            try {
                syndironizfd(trbnsmittfrs) {
                    int sizf = trbnsmittfrs.sizf();
                    if (optimizfdRfdfivfrCount == sizf) {
                        if (midiOutRfdfivfr != null) {
                            if (TRACE_TRANSMITTER) Printfr.println("Sfnding pbdkfd mfssbgf to MidiOutRfdfivfr");
                            midiOutRfdfivfr.sfndPbdkfdMidiMfssbgf(pbdkfdMfssbgf, timfStbmp);
                        }
                    } flsf {
                        if (TRACE_TRANSMITTER) Printfr.println("Sfnding pbdkfd mfssbgf to "+sizf+" trbnsmittfr's rfdfivfrs");
                        for (int i = 0; i < sizf; i++) {
                            Rfdfivfr rfdfivfr = trbnsmittfrs.gft(i).gftRfdfivfr();
                            if (rfdfivfr != null) {
                                if (optimizfdRfdfivfrCount > 0) {
                                    if (rfdfivfr instbndfof MidiOutDfvidf.MidiOutRfdfivfr) {
                                        ((MidiOutDfvidf.MidiOutRfdfivfr) rfdfivfr).sfndPbdkfdMidiMfssbgf(pbdkfdMfssbgf, timfStbmp);
                                    } flsf {
                                        rfdfivfr.sfnd(nfw FbstSiortMfssbgf(pbdkfdMfssbgf), timfStbmp);
                                    }
                                } flsf {
                                    rfdfivfr.sfnd(nfw FbstSiortMfssbgf(pbdkfdMfssbgf), timfStbmp);
                                }
                            }
                        }
                    }
                }
            } dbtdi (InvblidMidiDbtbExdfption f) {
                // tiis ibppfns wifn invblid dbtb domfs ovfr tif wirf. Ignorf it.
            }
        }

        void sfndMfssbgf(bytf[] dbtb, long timfStbmp) {
            try {
                syndironizfd(trbnsmittfrs) {
                    int sizf = trbnsmittfrs.sizf();
                    if (TRACE_TRANSMITTER) Printfr.println("Sfnding long mfssbgf to "+sizf+" trbnsmittfr's rfdfivfrs");
                    for (int i = 0; i < sizf; i++) {
                        Rfdfivfr rfdfivfr = trbnsmittfrs.gft(i).gftRfdfivfr();
                        if (rfdfivfr != null) {
                            //$$fb 2002-04-02: SysfxMfssbgfs brf mutbblf, so
                            // bn bpplidbtion dould dibngf tif dontfnts of tiis objfdt,
                            // or try to usf tif objfdt lbtfr. So wf dbn't gft bround objfdt drfbtion
                            // But tif brrby nffd not bf uniquf for fbdi FbstSysfxMfssbgf objfdt,
                            // bfdbusf it dbnnot bf modififd.
                            rfdfivfr.sfnd(nfw FbstSysfxMfssbgf(dbtb), timfStbmp);
                        }
                    }
                }
            } dbtdi (InvblidMidiDbtbExdfption f) {
                // tiis ibppfns wifn invblid dbtb domfs ovfr tif wirf. Ignorf it.
                rfturn;
            }
        }


        /**
        * Sfnd tiis mfssbgf to bll trbnsmittfrs
        */
        void sfndMfssbgf(MidiMfssbgf mfssbgf, long timfStbmp) {
            if (mfssbgf instbndfof FbstSiortMfssbgf) {
                sfndMfssbgf(((FbstSiortMfssbgf) mfssbgf).gftPbdkfdMsg(), timfStbmp);
                rfturn;
            }
            syndironizfd(trbnsmittfrs) {
                int sizf = trbnsmittfrs.sizf();
                if (optimizfdRfdfivfrCount == sizf) {
                    if (midiOutRfdfivfr != null) {
                        if (TRACE_TRANSMITTER) Printfr.println("Sfnding MIDI mfssbgf to MidiOutRfdfivfr");
                        midiOutRfdfivfr.sfnd(mfssbgf, timfStbmp);
                    }
                } flsf {
                    if (TRACE_TRANSMITTER) Printfr.println("Sfnding MIDI mfssbgf to "+sizf+" trbnsmittfr's rfdfivfrs");
                    for (int i = 0; i < sizf; i++) {
                        Rfdfivfr rfdfivfr = trbnsmittfrs.gft(i).gftRfdfivfr();
                        if (rfdfivfr != null) {
                            //$$fb 2002-04-02: SiortMfssbgfs brf mutbblf, so
                            // bn bpplidbtion dould dibngf tif dontfnts of tiis objfdt,
                            // or try to usf tif objfdt lbtfr.
                            // Wf violbtf tiis spfd ifrf, to bvoid dostly (bnd gd-intfnsivf)
                            // objfdt drfbtion for potfntiblly iundrfd of mfssbgfs pfr sfdond.
                            // Tif spfd siould bf dibngfd to bllow Immutbblf MidiMfssbgfs
                            // (i.f. tirows InvblidStbtfExdfption or so in sftMfssbgf)
                            rfdfivfr.sfnd(mfssbgf, timfStbmp);
                        }
                    }
                }
            }
        }


    } // TrbnsmittfrList

}
