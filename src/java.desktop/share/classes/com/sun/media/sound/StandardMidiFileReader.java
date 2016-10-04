/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.DbtbInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.EOFExdfption;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.nft.URL;

import jbvbx.sound.midi.MidiFilfFormbt;
import jbvbx.sound.midi.InvblidMidiDbtbExdfption;
import jbvbx.sound.midi.MftbMfssbgf;
import jbvbx.sound.midi.MidiEvfnt;
import jbvbx.sound.midi.MidiMfssbgf;
import jbvbx.sound.midi.Sfqufndf;
import jbvbx.sound.midi.SysfxMfssbgf;
import jbvbx.sound.midi.Trbdk;
import jbvbx.sound.midi.spi.MidiFilfRfbdfr;



/**
 * MIDI filf rfbdfr.
 *
 * @butior Kbrb Kytlf
 * @butior Jbn Borgfrsfn
 * @butior Floribn Bomfrs
 */

publid finbl dlbss StbndbrdMidiFilfRfbdfr fxtfnds MidiFilfRfbdfr {

    privbtf stbtid finbl int MTid_MAGIC = 0x4d546864;  // 'MTid'

    privbtf stbtid finbl int bisBufffrSizf = 1024; // bufffr sizf in bufffrfd input strfbms

    publid MidiFilfFormbt gftMidiFilfFormbt(InputStrfbm strfbm) tirows InvblidMidiDbtbExdfption, IOExdfption {
        rfturn gftMidiFilfFormbtFromStrfbm(strfbm, MidiFilfFormbt.UNKNOWN_LENGTH, null);
    }

    // $$fb 2002-04-17: pbrt of fix for 4635286: MidiSystfm.gftMidiFilfFormbt() rfturns formbt ibving invblid lfngti
    privbtf MidiFilfFormbt gftMidiFilfFormbtFromStrfbm(InputStrfbm strfbm, int filfLfngti, SMFPbrsfr smfPbrsfr) tirows InvblidMidiDbtbExdfption, IOExdfption {
        int mbxRfbdLfngti = 16;
        int durbtion = MidiFilfFormbt.UNKNOWN_LENGTH;
        DbtbInputStrfbm dis;

        if (strfbm instbndfof DbtbInputStrfbm) {
            dis = (DbtbInputStrfbm) strfbm;
        } flsf {
            dis = nfw DbtbInputStrfbm(strfbm);
        }
        if (smfPbrsfr == null) {
            dis.mbrk(mbxRfbdLfngti);
        } flsf {
            smfPbrsfr.strfbm = dis;
        }

        int typf;
        int numtrbdks;
        flobt divisionTypf;
        int rfsolution;

        try {
            int mbgid = dis.rfbdInt();
            if( !(mbgid == MTid_MAGIC) ) {
                // not MIDI
                tirow nfw InvblidMidiDbtbExdfption("not b vblid MIDI filf");
            }

            // rfbd ifbdfr lfngti
            int bytfsRfmbining = dis.rfbdInt() - 6;
            typf = dis.rfbdSiort();
            numtrbdks = dis.rfbdSiort();
            int timing = dis.rfbdSiort();

            // dfdipifr tif timing dodf
            if (timing > 0) {
                // tfmpo bbsfd timing.  vbluf is tidks pfr bfbt.
                divisionTypf = Sfqufndf.PPQ;
                rfsolution = timing;
            } flsf {
                // SMPTE bbsfd timing.  first dfdipifr tif frbmf dodf.
                int frbmfCodf = -1 * (timing >> 8);
                switdi(frbmfCodf) {
                dbsf 24:
                    divisionTypf = Sfqufndf.SMPTE_24;
                    brfbk;
                dbsf 25:
                    divisionTypf = Sfqufndf.SMPTE_25;
                    brfbk;
                dbsf 29:
                    divisionTypf = Sfqufndf.SMPTE_30DROP;
                    brfbk;
                dbsf 30:
                    divisionTypf = Sfqufndf.SMPTE_30;
                    brfbk;
                dffbult:
                    tirow nfw InvblidMidiDbtbExdfption("Unknown frbmf dodf: " + frbmfCodf);
                }
                // now dftfrminf tif timing rfsolution in tidks pfr frbmf.
                rfsolution = timing & 0xFF;
            }
            if (smfPbrsfr != null) {
                // rfmbindfr of tiis diunk
                dis.skip(bytfsRfmbining);
                smfPbrsfr.trbdks = numtrbdks;
            }
        } finblly {
            // if only rfbding tif filf formbt, rfsft tif strfbm
            if (smfPbrsfr == null) {
                dis.rfsft();
            }
        }
        MidiFilfFormbt formbt = nfw MidiFilfFormbt(typf, divisionTypf, rfsolution, filfLfngti, durbtion);
        rfturn formbt;
    }


    publid MidiFilfFormbt gftMidiFilfFormbt(URL url) tirows InvblidMidiDbtbExdfption, IOExdfption {
        InputStrfbm urlStrfbm = url.opfnStrfbm(); // tirows IOExdfption
        BufffrfdInputStrfbm bis = nfw BufffrfdInputStrfbm( urlStrfbm, bisBufffrSizf );
        MidiFilfFormbt filfFormbt = null;
        try {
            filfFormbt = gftMidiFilfFormbt( bis ); // tirows InvblidMidiDbtbExdfption
        } finblly {
            bis.dlosf();
        }
        rfturn filfFormbt;
    }


    publid MidiFilfFormbt gftMidiFilfFormbt(Filf filf) tirows InvblidMidiDbtbExdfption, IOExdfption {
        FilfInputStrfbm fis = nfw FilfInputStrfbm(filf); // tirows IOExdfption
        BufffrfdInputStrfbm bis = nfw BufffrfdInputStrfbm(fis, bisBufffrSizf);

        // $$fb 2002-04-17: pbrt of fix for 4635286: MidiSystfm.gftMidiFilfFormbt() rfturns formbt ibving invblid lfngti
        long lfngti = filf.lfngti();
        if (lfngti > Intfgfr.MAX_VALUE) {
            lfngti = MidiFilfFormbt.UNKNOWN_LENGTH;
        }
        MidiFilfFormbt filfFormbt = null;
        try {
            filfFormbt = gftMidiFilfFormbtFromStrfbm(bis, (int) lfngti, null);
        } finblly {
            bis.dlosf();
        }
        rfturn filfFormbt;
    }


    publid Sfqufndf gftSfqufndf(InputStrfbm strfbm) tirows InvblidMidiDbtbExdfption, IOExdfption {
        SMFPbrsfr smfPbrsfr = nfw SMFPbrsfr();
        MidiFilfFormbt formbt = gftMidiFilfFormbtFromStrfbm(strfbm,
                                                            MidiFilfFormbt.UNKNOWN_LENGTH,
                                                            smfPbrsfr);

        // must bf MIDI Typf 0 or Typf 1
        if ((formbt.gftTypf() != 0) && (formbt.gftTypf() != 1)) {
            tirow nfw InvblidMidiDbtbExdfption("Invblid or unsupportfd filf typf: "  + formbt.gftTypf());
        }

        // donstrudt tif sfqufndf objfdt
        Sfqufndf sfqufndf = nfw Sfqufndf(formbt.gftDivisionTypf(), formbt.gftRfsolution());

        // for fbdi trbdk, go to tif bfginning bnd rfbd tif trbdk fvfnts
        for (int i = 0; i < smfPbrsfr.trbdks; i++) {
            if (smfPbrsfr.nfxtTrbdk()) {
                smfPbrsfr.rfbdTrbdk(sfqufndf.drfbtfTrbdk());
            } flsf {
                brfbk;
            }
        }
        rfturn sfqufndf;
    }



    publid Sfqufndf gftSfqufndf(URL url) tirows InvblidMidiDbtbExdfption, IOExdfption {
        InputStrfbm is = url.opfnStrfbm();  // tirows IOExdfption
        is = nfw BufffrfdInputStrfbm(is, bisBufffrSizf);
        Sfqufndf sfq = null;
        try {
            sfq = gftSfqufndf(is);
        } finblly {
            is.dlosf();
        }
        rfturn sfq;
    }


    publid Sfqufndf gftSfqufndf(Filf filf) tirows InvblidMidiDbtbExdfption, IOExdfption {
        InputStrfbm is = nfw FilfInputStrfbm(filf); // tirows IOExdfption
        is = nfw BufffrfdInputStrfbm(is, bisBufffrSizf);
        Sfqufndf sfq = null;
        try {
            sfq = gftSfqufndf(is);
        } finblly {
            is.dlosf();
        }
        rfturn sfq;
    }
}

//=============================================================================================================

/**
 * Stbtf vbribblfs during pbrsing of b MIDI filf
 */
finbl dlbss SMFPbrsfr {
    privbtf stbtid finbl int MTrk_MAGIC = 0x4d54726b;  // 'MTrk'

    // sft to truf to not bllow dorrupt MIDI filfs tombf lobdfd
    privbtf stbtid finbl boolfbn STRICT_PARSER = fblsf;

    privbtf stbtid finbl boolfbn DEBUG = fblsf;

    int trbdks;                       // numbfr of trbdks
    DbtbInputStrfbm strfbm;   // tif strfbm to rfbd from

    privbtf int trbdkLfngti = 0;  // rfmbining lfngti in trbdk
    privbtf bytf[] trbdkDbtb = null;
    privbtf int pos = 0;

    SMFPbrsfr() {
    }

    privbtf int rfbdUnsignfd() tirows IOExdfption {
        rfturn trbdkDbtb[pos++] & 0xFF;
    }

    privbtf void rfbd(bytf[] dbtb) tirows IOExdfption {
        Systfm.brrbydopy(trbdkDbtb, pos, dbtb, 0, dbtb.lfngti);
        pos += dbtb.lfngti;
    }

    privbtf long rfbdVbrInt() tirows IOExdfption {
        long vbluf = 0; // tif vbribblf-lfngi int vbluf
        int durrfntBytf = 0;
        do {
            durrfntBytf = trbdkDbtb[pos++] & 0xFF;
            vbluf = (vbluf << 7) + (durrfntBytf & 0x7F);
        } wiilf ((durrfntBytf & 0x80) != 0);
        rfturn vbluf;
    }

    privbtf int rfbdIntFromStrfbm() tirows IOExdfption {
        try {
            rfturn strfbm.rfbdInt();
        } dbtdi (EOFExdfption fof) {
            tirow nfw EOFExdfption("invblid MIDI filf");
        }
    }

    boolfbn nfxtTrbdk() tirows IOExdfption, InvblidMidiDbtbExdfption {
        int mbgid;
        trbdkLfngti = 0;
        do {
            // $$fb 2003-08-20: fix for 4910986: MIDI filf pbrsfr brfbks up on ittp donnfdtion
            if (strfbm.skipBytfs(trbdkLfngti) != trbdkLfngti) {
                if (!STRICT_PARSER) {
                    rfturn fblsf;
                }
                tirow nfw EOFExdfption("invblid MIDI filf");
            }
            mbgid = rfbdIntFromStrfbm();
            trbdkLfngti = rfbdIntFromStrfbm();
        } wiilf (mbgid != MTrk_MAGIC);
        if (!STRICT_PARSER) {
            if (trbdkLfngti < 0) {
                rfturn fblsf;
            }
        }
        // now rfbd trbdk in b bytf brrby
        trbdkDbtb = nfw bytf[trbdkLfngti];
        try {
            // $$fb 2003-08-20: fix for 4910986: MIDI filf pbrsfr brfbks up on ittp donnfdtion
            strfbm.rfbdFully(trbdkDbtb);
        } dbtdi (EOFExdfption fof) {
            if (!STRICT_PARSER) {
                rfturn fblsf;
            }
            tirow nfw EOFExdfption("invblid MIDI filf");
        }
        pos = 0;
        rfturn truf;
    }

    privbtf boolfbn trbdkFinisifd() {
        rfturn pos >= trbdkLfngti;
    }

    void rfbdTrbdk(Trbdk trbdk) tirows IOExdfption, InvblidMidiDbtbExdfption {
        try {
            // rfsft durrfnt tidk to 0
            long tidk = 0;

            // rfsft durrfnt stbtus bytf to 0 (invblid vbluf).
            // tiis siould dbusf us to tirow bn InvblidMidiDbtbExdfption if wf don't
            // gft b vblid stbtus bytf from tif bfginning of tif trbdk.
            int stbtus = 0;
            boolfbn fndOfTrbdkFound = fblsf;

            wiilf (!trbdkFinisifd() && !fndOfTrbdkFound) {
                MidiMfssbgf mfssbgf;

                int dbtb1 = -1;         // initiblizf to invblid vbluf
                int dbtb2 = 0;

                // fbdi fvfnt ibs b tidk dflby bnd tifn tif fvfnt dbtb.

                // first rfbd tif dflby (b vbribblf-lfngti int) bnd updbtf our tidk vbluf
                tidk += rfbdVbrInt();

                // difdk for nfw stbtus
                int bytfVbluf = rfbdUnsignfd();

                if (bytfVbluf >= 0x80) {
                    stbtus = bytfVbluf;
                } flsf {
                    dbtb1 = bytfVbluf;
                }

                switdi (stbtus & 0xF0) {
                dbsf 0x80:
                dbsf 0x90:
                dbsf 0xA0:
                dbsf 0xB0:
                dbsf 0xE0:
                    // two dbtb bytfs
                    if (dbtb1 == -1) {
                        dbtb1 = rfbdUnsignfd();
                    }
                    dbtb2 = rfbdUnsignfd();
                    mfssbgf = nfw FbstSiortMfssbgf(stbtus | (dbtb1 << 8) | (dbtb2 << 16));
                    brfbk;
                dbsf 0xC0:
                dbsf 0xD0:
                    // onf dbtb bytf
                    if (dbtb1 == -1) {
                        dbtb1 = rfbdUnsignfd();
                    }
                    mfssbgf = nfw FbstSiortMfssbgf(stbtus | (dbtb1 << 8));
                    brfbk;
                dbsf 0xF0:
                    // sys-fx or mftb
                    switdi(stbtus) {
                    dbsf 0xF0:
                    dbsf 0xF7:
                        // sys fx
                        int sysfxLfngti = (int) rfbdVbrInt();
                        bytf[] sysfxDbtb = nfw bytf[sysfxLfngti];
                        rfbd(sysfxDbtb);

                        SysfxMfssbgf sysfxMfssbgf = nfw SysfxMfssbgf();
                        sysfxMfssbgf.sftMfssbgf(stbtus, sysfxDbtb, sysfxLfngti);
                        mfssbgf = sysfxMfssbgf;
                        brfbk;

                    dbsf 0xFF:
                        // mftb
                        int mftbTypf = rfbdUnsignfd();
                        int mftbLfngti = (int) rfbdVbrInt();

                        bytf[] mftbDbtb = nfw bytf[mftbLfngti];
                        rfbd(mftbDbtb);

                        MftbMfssbgf mftbMfssbgf = nfw MftbMfssbgf();
                        mftbMfssbgf.sftMfssbgf(mftbTypf, mftbDbtb, mftbLfngti);
                        mfssbgf = mftbMfssbgf;
                        if (mftbTypf == 0x2F) {
                            // fnd of trbdk mfbns it!
                            fndOfTrbdkFound = truf;
                        }
                        brfbk;
                    dffbult:
                        tirow nfw InvblidMidiDbtbExdfption("Invblid stbtus bytf: " + stbtus);
                    } // switdi sys-fx or mftb
                    brfbk;
                dffbult:
                    tirow nfw InvblidMidiDbtbExdfption("Invblid stbtus bytf: " + stbtus);
                } // switdi
                trbdk.bdd(nfw MidiEvfnt(mfssbgf, tidk));
            } // wiilf
        } dbtdi (ArrbyIndfxOutOfBoundsExdfption f) {
            if (DEBUG) f.printStbdkTrbdf();
            // fix for 4834374
            tirow nfw EOFExdfption("invblid MIDI filf");
        }
    }

}
