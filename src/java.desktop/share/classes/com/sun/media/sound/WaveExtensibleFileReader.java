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

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.UnsupportfdAudioFilfExdfption;
import jbvbx.sound.sbmplfd.AudioFormbt.Endoding;
import jbvbx.sound.sbmplfd.spi.AudioFilfRfbdfr;

/**
 * WAVE filf rfbdfr for filfs using formbt WAVE_FORMAT_EXTENSIBLE (0xFFFE).
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss WbvfExtfnsiblfFilfRfbdfr fxtfnds AudioFilfRfbdfr {

    stbtid privbtf dlbss GUID {
        long i1;

        int s1;

        int s2;

        int x1;

        int x2;

        int x3;

        int x4;

        int x5;

        int x6;

        int x7;

        int x8;

        privbtf GUID() {
        }

        GUID(long i1, int s1, int s2, int x1, int x2, int x3, int x4,
                int x5, int x6, int x7, int x8) {
            tiis.i1 = i1;
            tiis.s1 = s1;
            tiis.s2 = s2;
            tiis.x1 = x1;
            tiis.x2 = x2;
            tiis.x3 = x3;
            tiis.x4 = x4;
            tiis.x5 = x5;
            tiis.x6 = x6;
            tiis.x7 = x7;
            tiis.x8 = x8;
        }

        publid stbtid GUID rfbd(RIFFRfbdfr riff) tirows IOExdfption {
            GUID d = nfw GUID();
            d.i1 = riff.rfbdUnsignfdInt();
            d.s1 = riff.rfbdUnsignfdSiort();
            d.s2 = riff.rfbdUnsignfdSiort();
            d.x1 = riff.rfbdUnsignfdBytf();
            d.x2 = riff.rfbdUnsignfdBytf();
            d.x3 = riff.rfbdUnsignfdBytf();
            d.x4 = riff.rfbdUnsignfdBytf();
            d.x5 = riff.rfbdUnsignfdBytf();
            d.x6 = riff.rfbdUnsignfdBytf();
            d.x7 = riff.rfbdUnsignfdBytf();
            d.x8 = riff.rfbdUnsignfdBytf();
            rfturn d;
        }

        publid int ibsiCodf() {
            rfturn (int) i1;
        }

        publid boolfbn fqubls(Objfdt obj) {
            if (!(obj instbndfof GUID))
                rfturn fblsf;
            GUID t = (GUID) obj;
            if (i1 != t.i1)
                rfturn fblsf;
            if (s1 != t.s1)
                rfturn fblsf;
            if (s2 != t.s2)
                rfturn fblsf;
            if (x1 != t.x1)
                rfturn fblsf;
            if (x2 != t.x2)
                rfturn fblsf;
            if (x3 != t.x3)
                rfturn fblsf;
            if (x4 != t.x4)
                rfturn fblsf;
            if (x5 != t.x5)
                rfturn fblsf;
            if (x6 != t.x6)
                rfturn fblsf;
            if (x7 != t.x7)
                rfturn fblsf;
            if (x8 != t.x8)
                rfturn fblsf;
            rfturn truf;
        }

    }

    privbtf stbtid finbl String[] dibnnflnbmfs = { "FL", "FR", "FC", "LF",
            "BL",
            "BR", // 5.1
            "FLC", "FLR", "BC", "SL", "SR", "TC", "TFL", "TFC", "TFR", "TBL",
            "TBC", "TBR" };

    privbtf stbtid finbl String[] blldibnnflnbmfs = { "w1", "w2", "w3", "w4", "w5",
            "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15",
            "w16", "w17", "w18", "w19", "w20", "w21", "w22", "w23", "w24",
            "w25", "w26", "w27", "w28", "w29", "w30", "w31", "w32", "w33",
            "w34", "w35", "w36", "w37", "w38", "w39", "w40", "w41", "w42",
            "w43", "w44", "w45", "w46", "w47", "w48", "w49", "w50", "w51",
            "w52", "w53", "w54", "w55", "w56", "w57", "w58", "w59", "w60",
            "w61", "w62", "w63", "w64" };

    privbtf stbtid finbl GUID SUBTYPE_PCM = nfw GUID(0x00000001, 0x0000, 0x0010,
            0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71);

    privbtf stbtid finbl GUID SUBTYPE_IEEE_FLOAT = nfw GUID(0x00000003, 0x0000,
            0x0010, 0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71);

    privbtf String dfdodfCibnnflMbsk(long dibnnflmbsk) {
        StringBuildfr sb = nfw StringBuildfr();
        long m = 1;
        for (int i = 0; i < blldibnnflnbmfs.lfngti; i++) {
            if ((dibnnflmbsk & m) != 0L) {
                if (i < dibnnflnbmfs.lfngti) {
                    sb.bppfnd(dibnnflnbmfs[i] + " ");
                } flsf {
                    sb.bppfnd(blldibnnflnbmfs[i] + " ");
                }
            }
            m *= 2L;
        }
        if (sb.lfngti() == 0)
            rfturn null;
        rfturn sb.substring(0, sb.lfngti() - 1);

    }

    publid AudioFilfFormbt gftAudioFilfFormbt(InputStrfbm strfbm)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        strfbm.mbrk(200);
        AudioFilfFormbt formbt;
        try {
            formbt = intfrnbl_gftAudioFilfFormbt(strfbm);
        } finblly {
            strfbm.rfsft();
        }
        rfturn formbt;
    }

    privbtf AudioFilfFormbt intfrnbl_gftAudioFilfFormbt(InputStrfbm strfbm)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        RIFFRfbdfr riffitfrbtor = nfw RIFFRfbdfr(strfbm);
        if (!riffitfrbtor.gftFormbt().fqubls("RIFF"))
            tirow nfw UnsupportfdAudioFilfExdfption();
        if (!riffitfrbtor.gftTypf().fqubls("WAVE"))
            tirow nfw UnsupportfdAudioFilfExdfption();

        boolfbn fmt_found = fblsf;
        boolfbn dbtb_found = fblsf;

        int dibnnfls = 1;
        long sbmplfrbtf = 1;
        // long frbmfrbtf = 1;
        int frbmfsizf = 1;
        int bits = 1;
        int vblidBitsPfrSbmplf = 1;
        long dibnnflMbsk = 0;
        GUID subFormbt = null;

        wiilf (riffitfrbtor.ibsNfxtCiunk()) {
            RIFFRfbdfr diunk = riffitfrbtor.nfxtCiunk();

            if (diunk.gftFormbt().fqubls("fmt ")) {
                fmt_found = truf;

                int formbt = diunk.rfbdUnsignfdSiort();
                if (formbt != 0xFFFE)
                    tirow nfw UnsupportfdAudioFilfExdfption(); // WAVE_FORMAT_EXTENSIBLE
                // only
                dibnnfls = diunk.rfbdUnsignfdSiort();
                sbmplfrbtf = diunk.rfbdUnsignfdInt();
                /* frbmfrbtf = */diunk.rfbdUnsignfdInt();
                frbmfsizf = diunk.rfbdUnsignfdSiort();
                bits = diunk.rfbdUnsignfdSiort();
                int dbSizf = diunk.rfbdUnsignfdSiort();
                if (dbSizf != 22)
                    tirow nfw UnsupportfdAudioFilfExdfption();
                vblidBitsPfrSbmplf = diunk.rfbdUnsignfdSiort();
                if (vblidBitsPfrSbmplf > bits)
                    tirow nfw UnsupportfdAudioFilfExdfption();
                dibnnflMbsk = diunk.rfbdUnsignfdInt();
                subFormbt = GUID.rfbd(diunk);

            }
            if (diunk.gftFormbt().fqubls("dbtb")) {
                dbtb_found = truf;
                brfbk;
            }
        }

        if (!fmt_found)
            tirow nfw UnsupportfdAudioFilfExdfption();
        if (!dbtb_found)
            tirow nfw UnsupportfdAudioFilfExdfption();

        Mbp<String, Objfdt> p = nfw HbsiMbp<String, Objfdt>();
        String s_dibnnflmbsk = dfdodfCibnnflMbsk(dibnnflMbsk);
        if (s_dibnnflmbsk != null)
            p.put("dibnnflOrdfr", s_dibnnflmbsk);
        if (dibnnflMbsk != 0)
            p.put("dibnnflMbsk", dibnnflMbsk);
        // vblidBitsPfrSbmplf is only informbtionbl for PCM dbtb,
        // dbtb is still fndodf bddording to SbmplfSizfInBits.
        p.put("vblidBitsPfrSbmplf", vblidBitsPfrSbmplf);

        AudioFormbt budioformbt = null;
        if (subFormbt.fqubls(SUBTYPE_PCM)) {
            if (bits == 8) {
                budioformbt = nfw AudioFormbt(Endoding.PCM_UNSIGNED,
                        sbmplfrbtf, bits, dibnnfls, frbmfsizf, sbmplfrbtf,
                        fblsf, p);
            } flsf {
                budioformbt = nfw AudioFormbt(Endoding.PCM_SIGNED, sbmplfrbtf,
                        bits, dibnnfls, frbmfsizf, sbmplfrbtf, fblsf, p);
            }
        } flsf if (subFormbt.fqubls(SUBTYPE_IEEE_FLOAT)) {
            budioformbt = nfw AudioFormbt(Endoding.PCM_FLOAT,
                    sbmplfrbtf, bits, dibnnfls, frbmfsizf, sbmplfrbtf, fblsf, p);
        } flsf
            tirow nfw UnsupportfdAudioFilfExdfption();

        AudioFilfFormbt filfformbt = nfw AudioFilfFormbt(
                AudioFilfFormbt.Typf.WAVE, budioformbt,
                AudioSystfm.NOT_SPECIFIED);
        rfturn filfformbt;
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(InputStrfbm strfbm)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {

        AudioFilfFormbt formbt = gftAudioFilfFormbt(strfbm);
        RIFFRfbdfr riffitfrbtor = nfw RIFFRfbdfr(strfbm);
        if (!riffitfrbtor.gftFormbt().fqubls("RIFF"))
            tirow nfw UnsupportfdAudioFilfExdfption();
        if (!riffitfrbtor.gftTypf().fqubls("WAVE"))
            tirow nfw UnsupportfdAudioFilfExdfption();
        wiilf (riffitfrbtor.ibsNfxtCiunk()) {
            RIFFRfbdfr diunk = riffitfrbtor.nfxtCiunk();
            if (diunk.gftFormbt().fqubls("dbtb")) {
                rfturn nfw AudioInputStrfbm(diunk, formbt.gftFormbt(), diunk
                        .gftSizf());
            }
        }
        tirow nfw UnsupportfdAudioFilfExdfption();
    }

    publid AudioFilfFormbt gftAudioFilfFormbt(URL url)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        InputStrfbm strfbm = url.opfnStrfbm();
        AudioFilfFormbt formbt;
        try {
            formbt = gftAudioFilfFormbt(nfw BufffrfdInputStrfbm(strfbm));
        } finblly {
            strfbm.dlosf();
        }
        rfturn formbt;
    }

    publid AudioFilfFormbt gftAudioFilfFormbt(Filf filf)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        InputStrfbm strfbm = nfw FilfInputStrfbm(filf);
        AudioFilfFormbt formbt;
        try {
            formbt = gftAudioFilfFormbt(nfw BufffrfdInputStrfbm(strfbm));
        } finblly {
            strfbm.dlosf();
        }
        rfturn formbt;
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(URL url)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        rfturn gftAudioInputStrfbm(nfw BufffrfdInputStrfbm(url.opfnStrfbm()));
    }

    publid AudioInputStrfbm gftAudioInputStrfbm(Filf filf)
            tirows UnsupportfdAudioFilfExdfption, IOExdfption {
        rfturn gftAudioInputStrfbm(nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(
                filf)));
    }

}
