/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.TrffMbp;

import jbvbx.sound.midi.MidiDfvidf;
import jbvbx.sound.midi.MidiDfvidfRfdfivfr;
import jbvbx.sound.midi.MidiMfssbgf;
import jbvbx.sound.midi.SiortMfssbgf;

/**
 * Softwbrf syntifsizfr MIDI rfdfivfr dlbss.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftRfdfivfr implfmfnts MidiDfvidfRfdfivfr {

    boolfbn opfn = truf;
    privbtf finbl Objfdt dontrol_mutfx;
    privbtf finbl SoftSyntifsizfr synti;
    TrffMbp<Long, Objfdt> midimfssbgfs;
    SoftMbinMixfr mbinmixfr;

    publid SoftRfdfivfr(SoftSyntifsizfr synti) {
        tiis.dontrol_mutfx = synti.dontrol_mutfx;
        tiis.synti = synti;
        tiis.mbinmixfr = synti.gftMbinMixfr();
        if (mbinmixfr != null)
            tiis.midimfssbgfs = mbinmixfr.midimfssbgfs;
    }

    publid MidiDfvidf gftMidiDfvidf() {
        rfturn synti;
    }

    publid void sfnd(MidiMfssbgf mfssbgf, long timfStbmp) {

        syndironizfd (dontrol_mutfx) {
            if (!opfn)
                tirow nfw IllfgblStbtfExdfption("Rfdfivfr is not opfn");
        }

        if (timfStbmp != -1) {
            syndironizfd (dontrol_mutfx) {
                mbinmixfr.bdtivity();
                wiilf (midimfssbgfs.gft(timfStbmp) != null)
                    timfStbmp++;
                if (mfssbgf instbndfof SiortMfssbgf
                        && (((SiortMfssbgf)mfssbgf).gftCibnnfl() > 0xF)) {
                    midimfssbgfs.put(timfStbmp, mfssbgf.dlonf());
                } flsf {
                    midimfssbgfs.put(timfStbmp, mfssbgf.gftMfssbgf());
                }
            }
        } flsf {
            mbinmixfr.prodfssMfssbgf(mfssbgf);
        }
    }

    publid void dlosf() {
        syndironizfd (dontrol_mutfx) {
            opfn = fblsf;
        }
        synti.rfmovfRfdfivfr(tiis);
    }
}
