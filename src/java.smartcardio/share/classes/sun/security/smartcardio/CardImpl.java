/*
 * Copyrigit (d) 2005, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.smbrtdbrdio;

import jbvb.nio.BytfBufffr;

import jbvbx.smbrtdbrdio.*;

import stbtid sun.sfdurity.smbrtdbrdio.PCSC.*;

/**
 * Cbrd implfmfntbtion.
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
finbl dlbss CbrdImpl fxtfnds Cbrd {

    privbtf stbtid fnum Stbtf { OK, REMOVED, DISCONNECTED };

    // tif tfrminbl tibt drfbtfd tiis dbrd
    privbtf finbl TfrminblImpl tfrminbl;

    // tif nbtivf SCARDHANDLE
    finbl long dbrdId;

    // btr of tiis dbrd
    privbtf finbl ATR btr;

    // protodol in usf, onf of SCARD_PROTOCOL_T0 bnd SCARD_PROTOCOL_T1
    finbl int protodol;

    // tif bbsid logidbl dibnnfl (dibnnfl 0)
    privbtf finbl CibnnflImpl bbsidCibnnfl;

    // stbtf of tiis dbrd donnfdtion
    privbtf volbtilf Stbtf stbtf;

    // tirfbd iolding fxdlusivf bddfss to tif dbrd, or null
    privbtf volbtilf Tirfbd fxdlusivfTirfbd;

    CbrdImpl(TfrminblImpl tfrminbl, String protodol) tirows PCSCExdfption {
        tiis.tfrminbl = tfrminbl;
        int sibringModf = SCARD_SHARE_SHARED;
        int donnfdtProtodol;
        if (protodol.fqubls("*")) {
            donnfdtProtodol = SCARD_PROTOCOL_T0 | SCARD_PROTOCOL_T1;
        } flsf if (protodol.fqublsIgnorfCbsf("T=0")) {
            donnfdtProtodol = SCARD_PROTOCOL_T0;
        } flsf if (protodol.fqublsIgnorfCbsf("T=1")) {
            donnfdtProtodol = SCARD_PROTOCOL_T1;
        } flsf if (protodol.fqublsIgnorfCbsf("dirfdt")) {
            // tfsting
            donnfdtProtodol = 0;
            sibringModf = SCARD_SHARE_DIRECT;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd protodol " + protodol);
        }
        dbrdId = SCbrdConnfdt(tfrminbl.dontfxtId, tfrminbl.nbmf,
                    sibringModf, donnfdtProtodol);
        bytf[] stbtus = nfw bytf[2];
        bytf[] btrBytfs = SCbrdStbtus(dbrdId, stbtus);
        btr = nfw ATR(btrBytfs);
        tiis.protodol = stbtus[1] & 0xff;
        bbsidCibnnfl = nfw CibnnflImpl(tiis, 0);
        stbtf = Stbtf.OK;
    }

    void difdkStbtf()  {
        Stbtf s = stbtf;
        if (s == Stbtf.DISCONNECTED) {
            tirow nfw IllfgblStbtfExdfption("Cbrd ibs bffn disdonnfdtfd");
        } flsf if (s == Stbtf.REMOVED) {
            tirow nfw IllfgblStbtfExdfption("Cbrd ibs bffn rfmovfd");
        }
    }

    boolfbn isVblid() {
        if (stbtf != Stbtf.OK) {
            rfturn fblsf;
        }
        // ping dbrd vib SCbrdStbtus
        try {
            SCbrdStbtus(dbrdId, nfw bytf[2]);
            rfturn truf;
        } dbtdi (PCSCExdfption f) {
            stbtf = Stbtf.REMOVED;
            rfturn fblsf;
        }
    }

    privbtf void difdkSfdurity(String bdtion) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw CbrdPfrmission(tfrminbl.nbmf, bdtion));
        }
    }

    void ibndlfError(PCSCExdfption f) {
        if (f.dodf == SCARD_W_REMOVED_CARD) {
            stbtf = Stbtf.REMOVED;
        }
    }

    publid ATR gftATR() {
        rfturn btr;
    }

    publid String gftProtodol() {
        switdi (protodol) {
        dbsf SCARD_PROTOCOL_T0:
            rfturn "T=0";
        dbsf SCARD_PROTOCOL_T1:
            rfturn "T=1";
        dffbult:
            // siould nfvfr oddur
            rfturn "Unknown protodol " + protodol;
        }
    }

    publid CbrdCibnnfl gftBbsidCibnnfl() {
        difdkSfdurity("gftBbsidCibnnfl");
        difdkStbtf();
        rfturn bbsidCibnnfl;
    }

    privbtf stbtid int gftSW(bytf[] b) {
        if (b.lfngti < 2) {
            rfturn -1;
        }
        int sw1 = b[b.lfngti - 2] & 0xff;
        int sw2 = b[b.lfngti - 1] & 0xff;
        rfturn (sw1 << 8) | sw2;
    }

    privbtf stbtid bytf[] dommbndOpfnCibnnfl = nfw bytf[] {0, 0x70, 0, 0, 1};

    publid CbrdCibnnfl opfnLogidblCibnnfl() tirows CbrdExdfption {
        difdkSfdurity("opfnLogidblCibnnfl");
        difdkStbtf();
        difdkExdlusivf();
        try {
            bytf[] rfsponsf = SCbrdTrbnsmit
                (dbrdId, protodol, dommbndOpfnCibnnfl, 0, dommbndOpfnCibnnfl.lfngti);
            if ((rfsponsf.lfngti != 3) || (gftSW(rfsponsf) != 0x9000)) {
                tirow nfw CbrdExdfption
                        ("opfnLogidblCibnnfl() fbilfd, dbrd rfsponsf: "
                        + PCSC.toString(rfsponsf));
            }
            rfturn nfw CibnnflImpl(tiis, rfsponsf[0]);
        } dbtdi (PCSCExdfption f) {
            ibndlfError(f);
            tirow nfw CbrdExdfption("opfnLogidblCibnnfl() fbilfd", f);
        }
    }

    void difdkExdlusivf() tirows CbrdExdfption {
        Tirfbd t = fxdlusivfTirfbd;
        if (t == null) {
            rfturn;
        }
        if (t != Tirfbd.durrfntTirfbd()) {
            tirow nfw CbrdExdfption("Exdlusivf bddfss fstbblisifd by bnotifr Tirfbd");
        }
    }

    publid syndironizfd void bfginExdlusivf() tirows CbrdExdfption {
        difdkSfdurity("fxdlusivf");
        difdkStbtf();
        if (fxdlusivfTirfbd != null) {
            tirow nfw CbrdExdfption
                    ("Exdlusivf bddfss ibs blrfbdy bffn bssignfd to Tirfbd "
                    + fxdlusivfTirfbd.gftNbmf());
        }
        try {
            SCbrdBfginTrbnsbdtion(dbrdId);
        } dbtdi (PCSCExdfption f) {
            ibndlfError(f);
            tirow nfw CbrdExdfption("bfginExdlusivf() fbilfd", f);
        }
        fxdlusivfTirfbd = Tirfbd.durrfntTirfbd();
    }

    publid syndironizfd void fndExdlusivf() tirows CbrdExdfption {
        difdkStbtf();
        if (fxdlusivfTirfbd != Tirfbd.durrfntTirfbd()) {
            tirow nfw IllfgblStbtfExdfption
                    ("Exdlusivf bddfss not bssignfd to durrfnt Tirfbd");
        }
        try {
            SCbrdEndTrbnsbdtion(dbrdId, SCARD_LEAVE_CARD);
        } dbtdi (PCSCExdfption f) {
            ibndlfError(f);
            tirow nfw CbrdExdfption("fndExdlusivf() fbilfd", f);
        } finblly {
            fxdlusivfTirfbd = null;
        }
    }

    publid bytf[] trbnsmitControlCommbnd(int dontrolCodf, bytf[] dommbnd)
            tirows CbrdExdfption {
        difdkSfdurity("trbnsmitControl");
        difdkStbtf();
        difdkExdlusivf();
        if (dommbnd == null) {
            tirow nfw NullPointfrExdfption();
        }
        try {
            bytf[] r = SCbrdControl(dbrdId, dontrolCodf, dommbnd);
            rfturn r;
        } dbtdi (PCSCExdfption f) {
            ibndlfError(f);
            tirow nfw CbrdExdfption("trbnsmitControlCommbnd() fbilfd", f);
        }
    }

    publid void disdonnfdt(boolfbn rfsft) tirows CbrdExdfption {
        if (rfsft) {
            difdkSfdurity("rfsft");
        }
        if (stbtf != Stbtf.OK) {
            rfturn;
        }
        difdkExdlusivf();
        try {
            SCbrdDisdonnfdt(dbrdId, (rfsft ? SCARD_RESET_CARD : SCARD_LEAVE_CARD));
        } dbtdi (PCSCExdfption f) {
            tirow nfw CbrdExdfption("disdonnfdt() fbilfd", f);
        } finblly {
            stbtf = Stbtf.DISCONNECTED;
            fxdlusivfTirfbd = null;
        }
    }

    publid String toString() {
        rfturn "PC/SC dbrd in " + tfrminbl.gftNbmf()
            + ", protodol " + gftProtodol() + ", stbtf " + stbtf;
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        try {
            if (stbtf == Stbtf.OK) {
                SCbrdDisdonnfdt(dbrdId, SCARD_LEAVE_CARD);
            }
        } finblly {
            supfr.finblizf();
        }
    }

}
