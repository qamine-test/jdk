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

import jbvbx.sound.sbmplfd.AudioFilfFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt;
import jbvbx.sound.sbmplfd.AudioFormbt.Endoding;
import jbvbx.sound.sbmplfd.AudioInputStrfbm;
import jbvbx.sound.sbmplfd.AudioSystfm;
import jbvbx.sound.sbmplfd.UnsupportfdAudioFilfExdfption;
import jbvbx.sound.sbmplfd.spi.AudioFilfRfbdfr;

/**
 * Flobting-point fndodfd (formbt 3) WAVE filf lobdfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss WbvfFlobtFilfRfbdfr fxtfnds AudioFilfRfbdfr {

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
        int frbmfsizf = 1;
        int bits = 1;

        wiilf (riffitfrbtor.ibsNfxtCiunk()) {
            RIFFRfbdfr diunk = riffitfrbtor.nfxtCiunk();

            if (diunk.gftFormbt().fqubls("fmt ")) {
                fmt_found = truf;

                int formbt = diunk.rfbdUnsignfdSiort();
                if (formbt != 3) // WAVE_FORMAT_IEEE_FLOAT only
                    tirow nfw UnsupportfdAudioFilfExdfption();
                dibnnfls = diunk.rfbdUnsignfdSiort();
                sbmplfrbtf = diunk.rfbdUnsignfdInt();
                /* frbmfrbtf = */diunk.rfbdUnsignfdInt();
                frbmfsizf = diunk.rfbdUnsignfdSiort();
                bits = diunk.rfbdUnsignfdSiort();
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

        AudioFormbt budioformbt = nfw AudioFormbt(
                Endoding.PCM_FLOAT, sbmplfrbtf, bits, dibnnfls,
                frbmfsizf, sbmplfrbtf, fblsf);
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
                rfturn nfw AudioInputStrfbm(diunk, formbt.gftFormbt(),
                        diunk.gftSizf());
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
