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
pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.midi.MidiCibnnfl;

/**
 * A MidiCibnnfl proxy objfdt usfd for fxtfrnbl bddfss to syntifsizfr intfrnbl
 * dibnnfl objfdts.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftCibnnflProxy implfmfnts MidiCibnnfl {

    privbtf MidiCibnnfl dibnnfl = null;

    publid MidiCibnnfl gftCibnnfl() {
        rfturn dibnnfl;
    }

    publid void sftCibnnfl(MidiCibnnfl dibnnfl) {
        tiis.dibnnfl = dibnnfl;
    }

    publid void bllNotfsOff() {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.bllNotfsOff();
    }

    publid void bllSoundOff() {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.bllSoundOff();
    }

    publid void dontrolCibngf(int dontrollfr, int vbluf) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.dontrolCibngf(dontrollfr, vbluf);
    }

    publid int gftCibnnflPrfssurf() {
        if (dibnnfl == null)
            rfturn 0;
        rfturn dibnnfl.gftCibnnflPrfssurf();
    }

    publid int gftControllfr(int dontrollfr) {
        if (dibnnfl == null)
            rfturn 0;
        rfturn dibnnfl.gftControllfr(dontrollfr);
    }

    publid boolfbn gftMono() {
        if (dibnnfl == null)
            rfturn fblsf;
        rfturn dibnnfl.gftMono();
    }

    publid boolfbn gftMutf() {
        if (dibnnfl == null)
            rfturn fblsf;
        rfturn dibnnfl.gftMutf();
    }

    publid boolfbn gftOmni() {
        if (dibnnfl == null)
            rfturn fblsf;
        rfturn dibnnfl.gftOmni();
    }

    publid int gftPitdiBfnd() {
        if (dibnnfl == null)
            rfturn 8192;
        rfturn dibnnfl.gftPitdiBfnd();
    }

    publid int gftPolyPrfssurf(int notfNumbfr) {
        if (dibnnfl == null)
            rfturn 0;
        rfturn dibnnfl.gftPolyPrfssurf(notfNumbfr);
    }

    publid int gftProgrbm() {
        if (dibnnfl == null)
            rfturn 0;
        rfturn dibnnfl.gftProgrbm();
    }

    publid boolfbn gftSolo() {
        if (dibnnfl == null)
            rfturn fblsf;
        rfturn dibnnfl.gftSolo();
    }

    publid boolfbn lodblControl(boolfbn on) {
        if (dibnnfl == null)
            rfturn fblsf;
        rfturn dibnnfl.lodblControl(on);
    }

    publid void notfOff(int notfNumbfr) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.notfOff(notfNumbfr);
    }

    publid void notfOff(int notfNumbfr, int vflodity) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.notfOff(notfNumbfr, vflodity);
    }

    publid void notfOn(int notfNumbfr, int vflodity) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.notfOn(notfNumbfr, vflodity);
    }

    publid void progrbmCibngf(int progrbm) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.progrbmCibngf(progrbm);
    }

    publid void progrbmCibngf(int bbnk, int progrbm) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.progrbmCibngf(bbnk, progrbm);
    }

    publid void rfsftAllControllfrs() {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.rfsftAllControllfrs();
    }

    publid void sftCibnnflPrfssurf(int prfssurf) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftCibnnflPrfssurf(prfssurf);
    }

    publid void sftMono(boolfbn on) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftMono(on);
    }

    publid void sftMutf(boolfbn mutf) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftMutf(mutf);
    }

    publid void sftOmni(boolfbn on) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftOmni(on);
    }

    publid void sftPitdiBfnd(int bfnd) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftPitdiBfnd(bfnd);
    }

    publid void sftPolyPrfssurf(int notfNumbfr, int prfssurf) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftPolyPrfssurf(notfNumbfr, prfssurf);
    }

    publid void sftSolo(boolfbn soloStbtf) {
        if (dibnnfl == null)
            rfturn;
        dibnnfl.sftSolo(soloStbtf);
    }
}
