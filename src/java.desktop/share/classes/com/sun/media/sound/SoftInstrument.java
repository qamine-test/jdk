/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.sound.midi.Instrumfnt;
import jbvbx.sound.midi.MidiCibnnfl;

/**
 * Softwbrf syntifsizfr intfrnbl instrumfnt.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftInstrumfnt fxtfnds Instrumfnt {

    privbtf SoftPfrformfr[] pfrformfrs;
    privbtf ModflPfrformfr[] modflpfrformfrs;
    privbtf finbl Objfdt dbtb;
    privbtf finbl ModflInstrumfnt ins;

    publid SoftInstrumfnt(ModflInstrumfnt ins) {
        supfr(ins.gftSoundbbnk(), ins.gftPbtdi(), ins.gftNbmf(),
                ins.gftDbtbClbss());
        dbtb = ins.gftDbtb();
        tiis.ins = ins;
        initPfrformfrs(ins.gftPfrformfrs());
    }

    publid SoftInstrumfnt(ModflInstrumfnt ins,
            ModflPfrformfr[] ovfrridfpfrformfrs) {
        supfr(ins.gftSoundbbnk(), ins.gftPbtdi(), ins.gftNbmf(),
                ins.gftDbtbClbss());
        dbtb = ins.gftDbtb();
        tiis.ins = ins;
        initPfrformfrs(ovfrridfpfrformfrs);
    }

    privbtf void initPfrformfrs(ModflPfrformfr[] modflpfrformfrs) {
        tiis.modflpfrformfrs = modflpfrformfrs;
        pfrformfrs = nfw SoftPfrformfr[modflpfrformfrs.lfngti];
        for (int i = 0; i < modflpfrformfrs.lfngti; i++)
            pfrformfrs[i] = nfw SoftPfrformfr(modflpfrformfrs[i]);
    }

    publid ModflDirfdtor gftDirfdtor(MidiCibnnfl dibnnfl,
            ModflDirfdtfdPlbyfr plbyfr) {
        rfturn ins.gftDirfdtor(modflpfrformfrs, dibnnfl, plbyfr);
    }

    publid ModflInstrumfnt gftSourdfInstrumfnt() {
        rfturn ins;
    }

    publid Objfdt gftDbtb() {
        rfturn dbtb;
    }

    /* bm: durrfntly gftPfrformfrs() is not usfd (rfplbdfd witi gftPfrformfr(int))
    publid SoftPfrformfr[] gftPfrformfrs() {
        rfturn pfrformfrs;
    }
    */
    publid SoftPfrformfr gftPfrformfr(int indfx) {
        rfturn pfrformfrs[indfx];
    }
}
