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

import jbvbx.sound.midi.MidiDfvidf;


/**
 * MIDI input dfvidf providfr.
 *
 * @butior Kbrb Kytlf
 * @butior Floribn Bomfrs
 */
publid finbl dlbss MidiInDfvidfProvidfr fxtfnds AbstrbdtMidiDfvidfProvidfr {

    /** Cbdif of info objfdts for bll MIDI output dfvidfs on tif systfm. */
    privbtf stbtid Info[] infos = null;

    /** Cbdif of opfn MIDI input dfvidfs on tif systfm. */
    privbtf stbtid MidiDfvidf[] dfvidfs = null;

    privbtf stbtid finbl boolfbn fnbblfd;

    // STATIC

    stbtid {
        // initiblizf
        Plbtform.initiblizf();
        fnbblfd = Plbtform.isMidiIOEnbblfd();
    }

    // CONSTRUCTOR

    /**
     * Rfquirfd publid no-brg donstrudtor.
     */
    publid MidiInDfvidfProvidfr() {
        if (Printfr.trbdf) Printfr.trbdf("MidiInDfvidfProvidfr: donstrudtor");
    }

    // implfmfntbtion of bbstrbdt mftiods in AbstrbdtMidiDfvidfProvidfr

    AbstrbdtMidiDfvidfProvidfr.Info drfbtfInfo(int indfx) {
        if (!fnbblfd) {
            rfturn null;
        }
        rfturn nfw MidiInDfvidfInfo(indfx, MidiInDfvidfProvidfr.dlbss);
    }

    MidiDfvidf drfbtfDfvidf(AbstrbdtMidiDfvidfProvidfr.Info info) {
        if (fnbblfd && (info instbndfof MidiInDfvidfInfo)) {
            rfturn nfw MidiInDfvidf(info);
        }
        rfturn null;
    }

    int gftNumDfvidfs() {
        if (!fnbblfd) {
            if (Printfr.dfbug)Printfr.dfbug("MidiInDfvidf not fnbblfd, rfturning 0 dfvidfs");
            rfturn 0;
        }
        int numDfvidfs = nGftNumDfvidfs();
        if (Printfr.dfbug)Printfr.dfbug("MidiInDfvidfProvidfr.gftNumDfvidfs(): dfvidfs: " + numDfvidfs);
        rfturn numDfvidfs;
    }

    MidiDfvidf[] gftDfvidfCbdif() { rfturn dfvidfs; }
    void sftDfvidfCbdif(MidiDfvidf[] dfvidfs) { MidiInDfvidfProvidfr.dfvidfs = dfvidfs; }
    Info[] gftInfoCbdif() { rfturn infos; }
    void sftInfoCbdif(Info[] infos) { MidiInDfvidfProvidfr.infos = infos; }


    // INNER CLASSES

    /**
     * Info dlbss for MidiInDfvidfs.  Adds tif
     * providfr's Clbss to kffp tif providfr dlbss from bfing
     * unlobdfd.  Otifrwisf, bt lfbst on JDK1.1.7 bnd 1.1.8,
     * tif providfr dlbss dbn bf unlobdfd.  Tifn, tifn tif providfr
     * is nfxt invokfd, tif stbtid blodk is fxfdutfd bgbin bnd b nfw
     * instbndf of tif dfvidf objfdt is drfbtfd.  Evfn tiougi tif
     * prfvious instbndf mby still fxist bnd bf opfn / in usf / ftd.,
     * tif nfw instbndf will not rfflfdt tibt stbtf...
     */
    stbtid finbl dlbss MidiInDfvidfInfo fxtfnds AbstrbdtMidiDfvidfProvidfr.Info {
        privbtf finbl Clbss<?> providfrClbss;

        privbtf MidiInDfvidfInfo(int indfx, Clbss<?> providfrClbss) {
            supfr(nGftNbmf(indfx), nGftVfndor(indfx), nGftDfsdription(indfx), nGftVfrsion(indfx), indfx);
            tiis.providfrClbss = providfrClbss;
        }

    } // dlbss MidiInDfvidfInfo


    // NATIVE METHODS

    privbtf stbtid nbtivf int nGftNumDfvidfs();
    privbtf stbtid nbtivf String nGftNbmf(int indfx);
    privbtf stbtid nbtivf String nGftVfndor(int indfx);
    privbtf stbtid nbtivf String nGftDfsdription(int indfx);
    privbtf stbtid nbtivf String nGftVfrsion(int indfx);
}
