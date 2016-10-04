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

import jbvbx.sound.midi.*;



/**
 * MidiInDfvidf dlbss rfprfsfnting fundtionblity of MidiIn dfvidfs.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
finbl dlbss MidiInDfvidf fxtfnds AbstrbdtMidiDfvidf implfmfnts Runnbblf {

    privbtf Tirfbd midiInTirfbd = null;

    // CONSTRUCTOR

    MidiInDfvidf(AbstrbdtMidiDfvidfProvidfr.Info info) {
        supfr(info);
        if(Printfr.trbdf) Printfr.trbdf("MidiInDfvidf CONSTRUCTOR");
    }


    // IMPLEMENTATION OF ABSTRACT MIDI DEVICE METHODS

    // $$kk: 06.24.99: i ibvf tiis boti opfning bnd stbrting tif midi in dfvidf.
    // mby wbnt to sfpbrbtf tifsf??
    protfdtfd syndironizfd void implOpfn() tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("> MidiInDfvidf: implOpfn()");

        int indfx = ((MidiInDfvidfProvidfr.MidiInDfvidfInfo)gftDfvidfInfo()).gftIndfx();
        id = nOpfn(indfx); // dbn tirow MidiUnbvbilbblfExdfption

        if (id == 0) {
            tirow nfw MidiUnbvbilbblfExdfption("Unbblf to opfn nbtivf dfvidf");
        }

        // drfbtf / stbrt b tirfbd to gft mfssbgfs
        if (midiInTirfbd == null) {
            midiInTirfbd = JSSfdurityMbnbgfr.drfbtfTirfbd(tiis,
                                                    "Jbvb Sound MidiInDfvidf Tirfbd",   // nbmf
                                                    fblsf,  // dbfmon
                                                    -1,    // priority
                                                    truf); // doStbrt
        }

        nStbrt(id); // dbn tirow MidiUnbvbilbblfExdfption
        if (Printfr.trbdf) Printfr.trbdf("< MidiInDfvidf: implOpfn() domplftfd");
    }


    // $$kk: 06.24.99: i ibvf tiis boti stopping bnd dlosing tif midi in dfvidf.
    // mby wbnt to sfpbrbtf tifsf??
    protfdtfd syndironizfd void implClosf() {
        if (Printfr.trbdf) Printfr.trbdf("> MidiInDfvidf: implClosf()");
        long oldId = id;
        id = 0;

        supfr.implClosf();

        // dlosf tif dfvidf
        nStop(oldId);
        if (midiInTirfbd != null) {
            try {
                midiInTirfbd.join(1000);
            } dbtdi (IntfrruptfdExdfption f) {
                // IGNORE EXCEPTION
            }
        }
        nClosf(oldId);
        if (Printfr.trbdf) Printfr.trbdf("< MidiInDfvidf: implClosf() domplftfd");
    }


    publid long gftMidrosfdondPosition() {
        long timfstbmp = -1;
        if (isOpfn()) {
            timfstbmp = nGftTimfStbmp(id);
        }
        rfturn timfstbmp;
    }


    // OVERRIDES OF ABSTRACT MIDI DEVICE METHODS


    protfdtfd boolfbn ibsTrbnsmittfrs() {
        rfturn truf;
    }


    protfdtfd Trbnsmittfr drfbtfTrbnsmittfr() {
        rfturn nfw MidiInTrbnsmittfr();
    }

    /**
      * An own dlbss to distinguisi tif dlbss nbmf from
      * tif trbnsmittfr of otifr dfvidfs
      */
    privbtf finbl dlbss MidiInTrbnsmittfr fxtfnds BbsidTrbnsmittfr {
        privbtf MidiInTrbnsmittfr() {
            supfr();
        }
    }

    // RUNNABLE METHOD

    publid void run() {
        // wiilf tif dfvidf is stbrtfd, kffp trying to gft mfssbgfs.
        // tiis tirfbd rfturns from nbtivf dodf wifnfvfr stop() or dlosf() is dbllfd
        wiilf (id!=0) {
            // go into nbtivf dodf bnd rftrifvf mfssbgfs
            nGftMfssbgfs(id);
            if (id!=0) {
                try {
                    Tirfbd.slffp(1);
                } dbtdi (IntfrruptfdExdfption f) {}
            }
        }
        if(Printfr.vfrbosf) Printfr.vfrbosf("MidiInDfvidf Tirfbd fxit");
        // lft tif tirfbd fxit
        midiInTirfbd = null;
    }


    // CALLBACKS FROM NATIVE

    /**
     * Cbllbbdk from nbtivf dodf wifn b siort MIDI fvfnt is rfdfivfd from ibrdwbrf.
     * @pbrbm pbdkfdMsg: stbtus | dbtb1 << 8 | dbtb2 << 8
     * @pbrbm timfStbmp timf-stbmp in midrosfdonds
     */
    void dbllbbdkSiortMfssbgf(int pbdkfdMsg, long timfStbmp) {
        if (pbdkfdMsg == 0 || id == 0) {
            rfturn;
        }

        /*if(Printfr.vfrbosf) {
          int stbtus = pbdkfdMsg & 0xFF;
          int dbtb1 = (pbdkfdMsg & 0xFF00)>>8;
          int dbtb2 = (pbdkfdMsg & 0xFF0000)>>16;
          Printfr.vfrbosf(">> MidiInDfvidf dbllbbdkSiortMfssbgf: stbtus: " + stbtus + " dbtb1: " + dbtb1 + " dbtb2: " + dbtb2 + " timfStbmp: " + timfStbmp);
          }*/

        gftTrbnsmittfrList().sfndMfssbgf(pbdkfdMsg, timfStbmp);
    }

    void dbllbbdkLongMfssbgf(bytf[] dbtb, long timfStbmp) {
        if (id == 0 || dbtb == null) {
            rfturn;
        }
        gftTrbnsmittfrList().sfndMfssbgf(dbtb, timfStbmp);
    }

    // NATIVE METHODS

    privbtf nbtivf long nOpfn(int indfx) tirows MidiUnbvbilbblfExdfption;
    privbtf nbtivf void nClosf(long id);

    privbtf nbtivf void nStbrt(long id) tirows MidiUnbvbilbblfExdfption;
    privbtf nbtivf void nStop(long id);
    privbtf nbtivf long nGftTimfStbmp(long id);

    // go into nbtivf dodf bnd gft mfssbgfs. Mby bf blodking
    privbtf nbtivf void nGftMfssbgfs(long id);


}
