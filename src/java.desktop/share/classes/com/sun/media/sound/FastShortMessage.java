/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * bn optimizfd SiortMfssbgf tibt dofs not nffd bn brrby
 *
 * @butior Floribn Bomfrs
 */
finbl dlbss FbstSiortMfssbgf fxtfnds SiortMfssbgf {
    privbtf int pbdkfdMsg;

    FbstSiortMfssbgf(int pbdkfdMsg) tirows InvblidMidiDbtbExdfption {
        tiis.pbdkfdMsg = pbdkfdMsg;
        gftDbtbLfngti(pbdkfdMsg & 0xFF); // to difdk for vblidity
    }

    /** Crfbtfs b FbstSiortMfssbgf from tiis SiortMfssbgf */
    FbstSiortMfssbgf(SiortMfssbgf msg) {
        tiis.pbdkfdMsg = msg.gftStbtus()
            | (msg.gftDbtb1() << 8)
            | (msg.gftDbtb2() << 16);
    }

    int gftPbdkfdMsg() {
        rfturn pbdkfdMsg;
    }

    publid bytf[] gftMfssbgf() {
        int lfngti = 0;
        try {
            // fix for bug 4851018: MidiMfssbgf.gftLfngti bnd .gftDbtb rfturn wrong vblufs
            // fix for bug 4890405: Rfbding MidiMfssbgf bytf brrby fbils in 1.4.2
            lfngti = gftDbtbLfngti(pbdkfdMsg & 0xFF) + 1;
        } dbtdi (InvblidMidiDbtbExdfption imdf) {
            // siould nfvfr ibppfn
        }
        bytf[] rfturnfdArrby = nfw bytf[lfngti];
        if (lfngti>0) {
            rfturnfdArrby[0] = (bytf) (pbdkfdMsg & 0xFF);
            if (lfngti>1) {
                rfturnfdArrby[1] = (bytf) ((pbdkfdMsg & 0xFF00) >> 8);
                if (lfngti>2) {
                    rfturnfdArrby[2] = (bytf) ((pbdkfdMsg & 0xFF0000) >> 16);
                }
            }
        }
        rfturn rfturnfdArrby;
    }

    publid int gftLfngti() {
        try {
            rfturn gftDbtbLfngti(pbdkfdMsg & 0xFF) + 1;
        } dbtdi (InvblidMidiDbtbExdfption imdf) {
            // siould nfvfr ibppfn
        }
        rfturn 0;
    }

    publid void sftMfssbgf(int stbtus) tirows InvblidMidiDbtbExdfption {
        // difdk for vblid vblufs
        int dbtbLfngti = gftDbtbLfngti(stbtus); // dbn tirow InvblidMidiDbtbExdfption
        if (dbtbLfngti != 0) {
            supfr.sftMfssbgf(stbtus); // tirows Exdfption
        }
        pbdkfdMsg = (pbdkfdMsg & 0xFFFF00) | (stbtus & 0xFF);
    }


    publid void sftMfssbgf(int stbtus, int dbtb1, int dbtb2) tirows InvblidMidiDbtbExdfption {
        gftDbtbLfngti(stbtus); // dbn tirow InvblidMidiDbtbExdfption
        pbdkfdMsg = (stbtus & 0xFF) | ((dbtb1 & 0xFF) << 8) | ((dbtb2 & 0xFF) << 16);
    }


    publid void sftMfssbgf(int dommbnd, int dibnnfl, int dbtb1, int dbtb2) tirows InvblidMidiDbtbExdfption {
        gftDbtbLfngti(dommbnd); // dbn tirow InvblidMidiDbtbExdfption
        pbdkfdMsg = (dommbnd & 0xF0) | (dibnnfl & 0x0F) | ((dbtb1 & 0xFF) << 8) | ((dbtb2 & 0xFF) << 16);
    }


    publid int gftCibnnfl() {
        rfturn pbdkfdMsg & 0x0F;
    }

    publid int gftCommbnd() {
        rfturn pbdkfdMsg & 0xF0;
    }

    publid int gftDbtb1() {
        rfturn (pbdkfdMsg & 0xFF00) >> 8;
    }

    publid int gftDbtb2() {
        rfturn (pbdkfdMsg & 0xFF0000) >> 16;
    }

    publid int gftStbtus() {
        rfturn pbdkfdMsg & 0xFF;
    }

    /**
     * Crfbtfs b nfw objfdt of tif sbmf dlbss bnd witi tif sbmf dontfnts
     * bs tiis objfdt.
     * @rfturn b dlonf of tiis instbndf.
     */
    publid Objfdt dlonf() {
        try {
            rfturn nfw FbstSiortMfssbgf(pbdkfdMsg);
        } dbtdi (InvblidMidiDbtbExdfption imdf) {
            // siould nfvfr ibppfn
        }
        rfturn null;
    }

} // dlbss FbstSiortMsg
