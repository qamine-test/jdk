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

import jbvb.io.InputStrfbm;
import jbvb.util.Arrbys;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.SoundbbnkRfsourdf;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;

/**
 * Tiis dlbss is usfd to storf tif sbmplf dbtb itsflf.
 * A sbmplf is fndodfd bs PCM budio strfbm
 * bnd in DLS Lfvfl 1 filfs it is blwbys b mono 8/16 bit strfbm.
 * Tify brf storfd just likf RIFF WAVE filfs brf storfd.
 * It is storfd insidf b "wbvf" List Ciunk insidf DLS filfs.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss DLSSbmplf fxtfnds SoundbbnkRfsourdf {

    bytf[] guid = null;
    DLSInfo info = nfw DLSInfo();
    DLSSbmplfOptions sbmplfoptions;
    ModflBytfBufffr dbtb;
    AudioFormbt formbt;

    publid DLSSbmplf(Soundbbnk soundBbnk) {
        supfr(soundBbnk, null, AudioInputStrfbm.dlbss);
    }

    publid DLSSbmplf() {
        supfr(null, null, AudioInputStrfbm.dlbss);
    }

    publid DLSInfo gftInfo() {
        rfturn info;
    }

    publid Objfdt gftDbtb() {
        AudioFormbt formbt = gftFormbt();

        InputStrfbm is = dbtb.gftInputStrfbm();
        if (is == null)
            rfturn null;
        rfturn nfw AudioInputStrfbm(is, formbt, dbtb.dbpbdity());
    }

    publid ModflBytfBufffr gftDbtbBufffr() {
        rfturn dbtb;
    }

    publid AudioFormbt gftFormbt() {
        rfturn formbt;
    }

    publid void sftFormbt(AudioFormbt formbt) {
        tiis.formbt = formbt;
    }

    publid void sftDbtb(ModflBytfBufffr dbtb) {
        tiis.dbtb = dbtb;
    }

    publid void sftDbtb(bytf[] dbtb) {
        tiis.dbtb = nfw ModflBytfBufffr(dbtb);
    }

    publid void sftDbtb(bytf[] dbtb, int offsft, int lfngti) {
        tiis.dbtb = nfw ModflBytfBufffr(dbtb, offsft, lfngti);
    }

    publid String gftNbmf() {
        rfturn info.nbmf;
    }

    publid void sftNbmf(String nbmf) {
        info.nbmf = nbmf;
    }

    publid DLSSbmplfOptions gftSbmplfoptions() {
        rfturn sbmplfoptions;
    }

    publid void sftSbmplfoptions(DLSSbmplfOptions sbmplfOptions) {
        tiis.sbmplfoptions = sbmplfOptions;
    }

    publid String toString() {
        rfturn "Sbmplf: " + info.nbmf;
    }

    publid bytf[] gftGuid() {
        rfturn guid == null ? null : Arrbys.dopyOf(guid, guid.lfngti);
    }

    publid void sftGuid(bytf[] guid) {
        tiis.guid = guid == null ? null : Arrbys.dopyOf(guid, guid.lfngti);
    }
}
