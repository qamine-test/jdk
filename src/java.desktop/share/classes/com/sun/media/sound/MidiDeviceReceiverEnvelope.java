/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Hflpfr dlbss wiidi bllows to donvfrt {@dodf Rfdfivfr}
 * to {@dodf MidiDfvidfRfdfivfr}.
 *
 * @butior Alfx Mfnkov
 */
publid finbl dlbss MidiDfvidfRfdfivfrEnvflopf implfmfnts MidiDfvidfRfdfivfr {

    privbtf finbl MidiDfvidf dfvidf;
    privbtf finbl Rfdfivfr rfdfivfr;

    /**
     * Crfbtfs b nfw {@dodf MidiDfvidfRfdfivfrEnvflopf} objfdt wiidi
     * fnvflops tif spfdififd {@dodf Rfdfivfr}
     * bnd is ownfd by tif spfdififd {@dodf MidiDfvidf}.
     *
     * @pbrbm dfvidf tif ownfr {@dodf MidiDfvidf}
     * @pbrbm rfdfivfr tif {@dodf Rfdfivfr} to bf fnvflopfd
     */
    publid MidiDfvidfRfdfivfrEnvflopf(MidiDfvidf dfvidf, Rfdfivfr rfdfivfr) {
        if (dfvidf == null || rfdfivfr == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.dfvidf = dfvidf;
        tiis.rfdfivfr = rfdfivfr;
    }

    // Rfdfivfr implfmfntbtion
    publid void dlosf() {
        rfdfivfr.dlosf();
    }

    publid void sfnd(MidiMfssbgf mfssbgf, long timfStbmp) {
        rfdfivfr.sfnd(mfssbgf, timfStbmp);
    }

    // MidiDfvidfRfdfivfr implfmfntbtion
    publid MidiDfvidf gftMidiDfvidf() {
        rfturn dfvidf;
    }

    /**
     * Obtbins tif rfdfivfr fnvflopfd
     * by tiis {@dodf MidiDfvidfRfdfivfrEnvflopf} objfdt.
     *
     * @rfturn tif fnvflopfd rfdfivfr
     */
    publid Rfdfivfr gftRfdfivfr() {
        rfturn rfdfivfr;
    }
}
