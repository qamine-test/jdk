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
 * MidiOutDfvidf dlbss rfprfsfnting fundtionblity of MidiOut dfvidfs.
 *
 * @butior Dbvid Rivbs
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
finbl dlbss MidiOutDfvidf fxtfnds AbstrbdtMidiDfvidf {

    // CONSTRUCTOR

    MidiOutDfvidf(AbstrbdtMidiDfvidfProvidfr.Info info) {
                supfr(info);
                if(Printfr.trbdf) Printfr.trbdf("MidiOutDfvidf CONSTRUCTOR");
    }


    // IMPLEMENTATION OF ABSTRACT MIDI DEVICE METHODS

    protfdtfd syndironizfd void implOpfn() tirows MidiUnbvbilbblfExdfption {
        if (Printfr.trbdf) Printfr.trbdf("> MidiOutDfvidf: implOpfn()");
        int indfx = ((AbstrbdtMidiDfvidfProvidfr.Info)gftDfvidfInfo()).gftIndfx();
        id = nOpfn(indfx); // dbn tirow MidiUnbvbilbblfExdfption
        if (id == 0) {
            tirow nfw MidiUnbvbilbblfExdfption("Unbblf to opfn nbtivf dfvidf");
        }
        if (Printfr.trbdf) Printfr.trbdf("< MidiOutDfvidf: implOpfn(): domplftfd.");
    }


    protfdtfd syndironizfd void implClosf() {
        if (Printfr.trbdf) Printfr.trbdf("> MidiOutDfvidf: implClosf()");
        // prfvfnt furtifr bdtion
        long oldId = id;
        id = 0;

        supfr.implClosf();

        // dlosf tif dfvidf
        nClosf(oldId);
        if (Printfr.trbdf) Printfr.trbdf("< MidiOutDfvidf: implClosf(): domplftfd");
    }


    publid long gftMidrosfdondPosition() {
        long timfstbmp = -1;
        if (isOpfn()) {
            timfstbmp = nGftTimfStbmp(id);
        }
        rfturn timfstbmp;
    }



    // OVERRIDES OF ABSTRACT MIDI DEVICE METHODS

    /** Rfturns if tiis dfvidf supports Rfdfivfrs.
        Tiis implfmfntbtion blwbys rfturns truf.
        @rfturn truf, if tif dfvidf supports Rfdfivfrs, fblsf otifrwisf.
    */
    protfdtfd boolfbn ibsRfdfivfrs() {
        rfturn truf;
    }


    protfdtfd Rfdfivfr drfbtfRfdfivfr() {
        rfturn nfw MidiOutRfdfivfr();
    }


    // INNER CLASSES

    finbl dlbss MidiOutRfdfivfr fxtfnds AbstrbdtRfdfivfr {

        void implSfnd(finbl MidiMfssbgf mfssbgf, finbl long timfStbmp) {
            finbl int lfngti = mfssbgf.gftLfngti();
            finbl int stbtus = mfssbgf.gftStbtus();
            if (lfngti <= 3 && stbtus != 0xF0 && stbtus != 0xF7) {
                int pbdkfdMsg;
                if (mfssbgf instbndfof SiortMfssbgf) {
                    if (mfssbgf instbndfof FbstSiortMfssbgf) {
                        pbdkfdMsg = ((FbstSiortMfssbgf) mfssbgf).gftPbdkfdMsg();
                    } flsf {
                        SiortMfssbgf msg = (SiortMfssbgf) mfssbgf;
                        pbdkfdMsg = (stbtus & 0xFF)
                            | ((msg.gftDbtb1() & 0xFF) << 8)
                            | ((msg.gftDbtb2() & 0xFF) << 16);
                    }
                } flsf {
                    pbdkfdMsg = 0;
                    bytf[] dbtb = mfssbgf.gftMfssbgf();
                    if (lfngti>0) {
                        pbdkfdMsg = dbtb[0] & 0xFF;
                        if (lfngti>1) {
                            /* Wf ibndlf mftb mfssbgfs ifrf. Tif mfssbgf
                               systfm rfsft (FF) dofsn't gft until ifrf,
                               bfdbusf it's lfngti is only 1. So if wf sff
                               b stbtus bytf of FF, it's surf tibt wf
                               ibvf b Mftb mfssbgf. */
                            if (stbtus == 0xFF) {
                                rfturn;
                            }
                            pbdkfdMsg |= (dbtb[1] & 0xFF) << 8;
                            if (lfngti>2) {
                                pbdkfdMsg |= (dbtb[2] & 0xFF) << 16;
                            }
                        }
                    }
                }
                nSfndSiortMfssbgf(id, pbdkfdMsg, timfStbmp);
            } flsf {
                finbl bytf[] dbtb;
                if (mfssbgf instbndfof FbstSysfxMfssbgf) {
                    dbtb = ((FbstSysfxMfssbgf) mfssbgf).gftRfbdOnlyMfssbgf();
                } flsf {
                    dbtb = mfssbgf.gftMfssbgf();
                }
                finbl int dbtbLfngti = Mbti.min(lfngti, dbtb.lfngti);
                if (dbtbLfngti > 0) {
                    nSfndLongMfssbgf(id, dbtb, dbtbLfngti, timfStbmp);
                }
            }
        }

        /** siortdut for tif Sun implfmfntbtion */
        syndironizfd void sfndPbdkfdMidiMfssbgf(int pbdkfdMsg, long timfStbmp) {
            if (isOpfn() && id != 0) {
                nSfndSiortMfssbgf(id, pbdkfdMsg, timfStbmp);
            }
        }


    } // dlbss MidiOutRfdfivfr


    // NATIVE METHODS

    privbtf nbtivf long nOpfn(int indfx) tirows MidiUnbvbilbblfExdfption;
    privbtf nbtivf void nClosf(long id);

    privbtf nbtivf void nSfndSiortMfssbgf(long id, int pbdkfdMsg, long timfStbmp);
    privbtf nbtivf void nSfndLongMfssbgf(long id, bytf[] dbtb, int sizf, long timfStbmp);
    privbtf nbtivf long nGftTimfStbmp(long id);

} // dlbss MidiOutDfvidf
