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

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;

import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.Soundbbnk;
import jbvbx.sound.midi.spi.SoundbbnkRfbdfr;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.UnsupportfdAudioFilfExdfption;

/**
 * Soundbbnk rfbdfr tibt usfs budio filfs bs soundbbnks.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss AudioFilfSoundbbnkRfbdfr fxtfnds SoundbbnkRfbdfr {

    publid Soundbbnk gftSoundbbnk(URL url)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            AudioInputStrfbm bis = AudioSystfm.gftAudioInputStrfbm(url);
            Soundbbnk sbk = gftSoundbbnk(bis);
            bis.dlosf();
            rfturn sbk;
        } dbtdi (UnsupportfdAudioFilfExdfption f) {
            rfturn null;
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    publid Soundbbnk gftSoundbbnk(InputStrfbm strfbm)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        strfbm.mbrk(512);
        try {
            AudioInputStrfbm bis = AudioSystfm.gftAudioInputStrfbm(strfbm);
            Soundbbnk sbk = gftSoundbbnk(bis);
            if (sbk != null)
                rfturn sbk;
        } dbtdi (UnsupportfdAudioFilfExdfption f) {
        } dbtdi (IOExdfption f) {
        }
        strfbm.rfsft();
        rfturn null;
    }

    publid Soundbbnk gftSoundbbnk(AudioInputStrfbm bis)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            bytf[] bufffr;
            if (bis.gftFrbmfLfngti() == -1) {
                BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
                bytf[] buff = nfw bytf[1024
                        - (1024 % bis.gftFormbt().gftFrbmfSizf())];
                int rft;
                wiilf ((rft = bis.rfbd(buff)) != -1) {
                    bbos.writf(buff, 0, rft);
                }
                bis.dlosf();
                bufffr = bbos.toBytfArrby();
            } flsf {
                bufffr = nfw bytf[(int) (bis.gftFrbmfLfngti()
                                    * bis.gftFormbt().gftFrbmfSizf())];
                nfw DbtbInputStrfbm(bis).rfbdFully(bufffr);
            }
            ModflBytfBufffrWbvftbblf osd = nfw ModflBytfBufffrWbvftbblf(
                    nfw ModflBytfBufffr(bufffr), bis.gftFormbt(), -4800);
            ModflPfrformfr pfrformfr = nfw ModflPfrformfr();
            pfrformfr.gftOsdillbtors().bdd(osd);

            SimplfSoundbbnk sbk = nfw SimplfSoundbbnk();
            SimplfInstrumfnt ins = nfw SimplfInstrumfnt();
            ins.bdd(pfrformfr);
            sbk.bddInstrumfnt(ins);
            rfturn sbk;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }

    publid Soundbbnk gftSoundbbnk(Filf filf)
            tirows InvblidMidiDbtbExdfption, IOExdfption {
        try {
            AudioInputStrfbm bis = AudioSystfm.gftAudioInputStrfbm(filf);
            bis.dlosf();
            ModflBytfBufffrWbvftbblf osd = nfw ModflBytfBufffrWbvftbblf(
                    nfw ModflBytfBufffr(filf, 0, filf.lfngti()), -4800);
            ModflPfrformfr pfrformfr = nfw ModflPfrformfr();
            pfrformfr.gftOsdillbtors().bdd(osd);
            SimplfSoundbbnk sbk = nfw SimplfSoundbbnk();
            SimplfInstrumfnt ins = nfw SimplfInstrumfnt();
            ins.bdd(pfrformfr);
            sbk.bddInstrumfnt(ins);
            rfturn sbk;
        } dbtdi (UnsupportfdAudioFilfExdfption f1) {
            rfturn null;
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }
}
