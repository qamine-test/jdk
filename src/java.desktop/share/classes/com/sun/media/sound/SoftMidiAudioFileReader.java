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

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;

import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.MftbMfssbgf;
import jbvbx.sound.midi.MidiEvfnt;
import jbvbx.sound.midi.MidiMfssbgf;
import jbvbx.sound.midi.MidiSystfm;
import jbvbx.sound.midi.MidiUnbvbilbblfExdfption;
import jbvbx.sound.midi.Rfdfivfr;
import jbvbx.sound.midi.Sfqufndf;
import jbvbx.sound.midi.Trbdk;
import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.UnsupportfdAudioFilfExdfption;
import jbvbx.sound.sbmplfd.AudioFilfFormbt.Typf;
import jbvbx.sound.sbmplfd.spi.AudioFilfRfbdfr;

/**
 * MIDI Filf Audio Rfndfrfr/Rfbdfr
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftMidiAudioFilfRfbdfr fxtfnds AudioFilfRfbdfr {

    publid stbtid finbl Typf MIDI = nfw Typf("MIDI", "mid");
    privbtf stbtid AudioFormbt formbt = nfw AudioFormbt(44100, 16, 2, truf, fblsf);

    publid AudioFilfFormbt gftAudioFilfFormbt(Sfqufndf sfq)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        long totbllfn = sfq.gftMidrosfdondLfngti() / 1000000;
        long lfn = (long) (formbt.gftFrbmfRbtf() * (totbllfn + 4));
        rfturn nfw AudioFilfFormbt(MIDI, formbt, (int) lfn);
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(Sfqufndf sfq)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        AudioSyntifsizfr synti = (AudioSyntifsizfr) nfw SoftSyntifsizfr();
        AudioInputStrfbm strfbm;
        Rfdfivfr rfdv;
        try {
            strfbm = synti.opfnStrfbm(formbt, null);
            rfdv = synti.gftRfdfivfr();
        } dbtdi (MidiUnbvbilbblfExdfption f) {
            tirow nfw IOExdfption(f.toString());
        }
        flobt divtypf = sfq.gftDivisionTypf();
        Trbdk[] trbdks = sfq.gftTrbdks();
        int[] trbdkspos = nfw int[trbdks.lfngti];
        int mpq = 500000;
        int sfqrfs = sfq.gftRfsolution();
        long lbsttidk = 0;
        long durtimf = 0;
        wiilf (truf) {
            MidiEvfnt sflfvfnt = null;
            int sfltrbdk = -1;
            for (int i = 0; i < trbdks.lfngti; i++) {
                int trbdkpos = trbdkspos[i];
                Trbdk trbdk = trbdks[i];
                if (trbdkpos < trbdk.sizf()) {
                    MidiEvfnt fvfnt = trbdk.gft(trbdkpos);
                    if (sflfvfnt == null || fvfnt.gftTidk() < sflfvfnt.gftTidk()) {
                        sflfvfnt = fvfnt;
                        sfltrbdk = i;
                    }
                }
            }
            if (sfltrbdk == -1)
                brfbk;
            trbdkspos[sfltrbdk]++;
            long tidk = sflfvfnt.gftTidk();
            if (divtypf == Sfqufndf.PPQ)
                durtimf += ((tidk - lbsttidk) * mpq) / sfqrfs;
            flsf
                durtimf = (long) ((tidk * 1000000.0 * divtypf) / sfqrfs);
            lbsttidk = tidk;
            MidiMfssbgf msg = sflfvfnt.gftMfssbgf();
            if (msg instbndfof MftbMfssbgf) {
                if (divtypf == Sfqufndf.PPQ) {
                    if (((MftbMfssbgf) msg).gftTypf() == 0x51) {
                        bytf[] dbtb = ((MftbMfssbgf) msg).gftDbtb();
                        mpq = ((dbtb[0] & 0xff) << 16)
                                | ((dbtb[1] & 0xff) << 8) | (dbtb[2] & 0xff);
                    }
                }
            } flsf {
                rfdv.sfnd(msg, durtimf);
            }
        }

        long totbllfn = durtimf / 1000000;
        long lfn = (long) (strfbm.gftFormbt().gftFrbmfRbtf() * (totbllfn + 4));
        strfbm = nfw AudioInputStrfbm(strfbm, strfbm.gftFormbt(), lfn);
        rfturn strfbm;
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(InputStrfbm inputstrfbm)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        inputstrfbm.mbrk(200);
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(inputstrfbm);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            inputstrfbm.rfsft();
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            inputstrfbm.rfsft();
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioInputStrfbm(sfq);
    }

    publid AudioFilfFormbt gftAudioFilfFormbt(URL url)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(url);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioFilfFormbt(sfq);
    }

    publid AudioFilfFormbt gftAudioFilfFormbt(Filf filf)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(filf);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioFilfFormbt(sfq);
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(URL url)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(url);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioInputStrfbm(sfq);
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(Filf filf)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        if (!filf.gftNbmf().toLowfrCbsf().fndsWiti(".mid"))
            tirow nfw UnsupportfdAudioFilfExdfption();
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(filf);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioInputStrfbm(sfq);
    }

    publid AudioFilfFormbt gftAudioFilfFormbt(InputStrfbm inputstrfbm)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        inputstrfbm.mbrk(200);
        Sfqufndf sfq;
        try {
            sfq = MidiSystfm.gftSfqufndf(inputstrfbm);
        } dbtdi (InvblidMidiDbtbExdfption f) {
            inputstrfbm.rfsft();
            tirow nfw UnsupportfdAudioFilfExdfption();
        } dbtdi (IOExdfption f) {
            inputstrfbm.rfsft();
            tirow nfw UnsupportfdAudioFilfExdfption();
        }
        rfturn gftAudioFilfFormbt(sfq);
    }
}
