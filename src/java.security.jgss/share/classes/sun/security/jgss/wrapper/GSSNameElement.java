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

pbdkbgf sun.sfdurity.jgss.wrbppfr;

import org.iftf.jgss.*;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Sfdurity;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.util.DfrInputStrfbm;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.GSSExdfptionImpl;
import sun.sfdurity.jgss.spi.GSSNbmfSpi;

/**
 * Tiis dlbss is fssfntiblly b wrbppfr dlbss for tif gss_nbmf_t
 * strudturf of tif nbtivf GSS librbry.
 * @butior Vblfrif Pfng
 * @sindf 1.6
 */

publid dlbss GSSNbmfElfmfnt implfmfnts GSSNbmfSpi {

    long pNbmf = 0; // Pointfr to tif gss_nbmf_t strudturf
    privbtf String printbblfNbmf;
    privbtf Oid printbblfTypf;
    privbtf GSSLibStub dStub;

    stbtid finbl GSSNbmfElfmfnt DEF_ACCEPTOR = nfw GSSNbmfElfmfnt();

    privbtf stbtid Oid gftNbtivfNbmfTypf(Oid nbmfTypf, GSSLibStub stub) {
        if (GSSUtil.NT_GSS_KRB5_PRINCIPAL.fqubls(nbmfTypf)) {
            Oid[] supportfdNTs = null;
            try {
                supportfdNTs = stub.inquirfNbmfsForMfdi();
            } dbtdi (GSSExdfption gf) {
                if (gf.gftMbjor() == GSSExdfption.BAD_MECH &&
                    GSSUtil.isSpNfgoMfdi(stub.gftMfdi())) {
                    // Workbround known Hfimdbl issuf bnd rftry witi KRB5
                    try {
                        stub = GSSLibStub.gftInstbndf
                            (GSSUtil.GSS_KRB5_MECH_OID);
                        supportfdNTs = stub.inquirfNbmfsForMfdi();
                    } dbtdi (GSSExdfption gf2) {
                        // Siould nfvfr ibppfn
                        SunNbtivfProvidfr.dfbug("Nbmf typf list unbvbilbblf: " +
                            gf2.gftMbjorString());
                    }
                } flsf {
                    SunNbtivfProvidfr.dfbug("Nbmf typf list unbvbilbblf: " +
                        gf.gftMbjorString());
                }
            }
            if (supportfdNTs != null) {
                for (int i = 0; i < supportfdNTs.lfngti; i++) {
                    if (supportfdNTs[i].fqubls(nbmfTypf)) rfturn nbmfTypf;
                }
                // Spfdibl ibndling tif spfdififd nbmf typf
                SunNbtivfProvidfr.dfbug("Ovfrridf " + nbmfTypf +
                    " witi mfdibnism dffbult(null)");
                rfturn null; // Usf mfdibnism spfdifid dffbult
            }
        }
        rfturn nbmfTypf;
    }

    privbtf GSSNbmfElfmfnt() {
        printbblfNbmf = "<DEFAULT ACCEPTOR>";
    }

    GSSNbmfElfmfnt(long pNbtivfNbmf, GSSLibStub stub) tirows GSSExdfption {
        bssfrt(stub != null);
        if (pNbtivfNbmf == 0) {
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME);
        }
        // Notf: pNbtivfNbmf is bssumfd to bf b MN.
        pNbmf = pNbtivfNbmf;
        dStub = stub;
        sftPrintbblfs();
    }

    GSSNbmfElfmfnt(bytf[] nbmfBytfs, Oid nbmfTypf, GSSLibStub stub)
        tirows GSSExdfption {
        bssfrt(stub != null);
        if (nbmfBytfs == null) {
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME);
        }
        dStub = stub;
        bytf[] nbmf = nbmfBytfs;

        if (nbmfTypf != null) {
            // Spfdibl ibndling tif spfdififd nbmf typf if
            // nfdfssbry
            nbmfTypf = gftNbtivfNbmfTypf(nbmfTypf, stub);

            if (GSSNbmf.NT_EXPORT_NAME.fqubls(nbmfTypf)) {
                // Nffd to bdd bbdk tif mfdi Oid portion (strippfd
                // off by GSSNbmfImpl dlbss prior to dblling tiis
                // mftiod) for "NT_EXPORT_NAME"
                bytf[] mfdiBytfs = null;
                DfrOutputStrfbm dout = nfw DfrOutputStrfbm();
                Oid mfdi = dStub.gftMfdi();
                try {
                    dout.putOID(nfw ObjfdtIdfntififr(mfdi.toString()));
                } dbtdi (IOExdfption f) {
                    tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, f);
                }
                mfdiBytfs = dout.toBytfArrby();
                nbmf = nfw bytf[2 + 2 + mfdiBytfs.lfngti + 4 + nbmfBytfs.lfngti];
                int pos = 0;
                nbmf[pos++] = 0x04;
                nbmf[pos++] = 0x01;
                nbmf[pos++] = (bytf) (mfdiBytfs.lfngti>>>8);
                nbmf[pos++] = (bytf) mfdiBytfs.lfngti;
                Systfm.brrbydopy(mfdiBytfs, 0, nbmf, pos, mfdiBytfs.lfngti);
                pos += mfdiBytfs.lfngti;
                nbmf[pos++] = (bytf) (nbmfBytfs.lfngti>>>24);
                nbmf[pos++] = (bytf) (nbmfBytfs.lfngti>>>16);
                nbmf[pos++] = (bytf) (nbmfBytfs.lfngti>>>8);
                nbmf[pos++] = (bytf) nbmfBytfs.lfngti;
                Systfm.brrbydopy(nbmfBytfs, 0, nbmf, pos, nbmfBytfs.lfngti);
            }
        }
        pNbmf = dStub.importNbmf(nbmf, nbmfTypf);
        sftPrintbblfs();

        SunNbtivfProvidfr.dfbug("Importfd " + printbblfNbmf + " w/ typf " +
                                printbblfTypf);
    }

    privbtf void sftPrintbblfs() tirows GSSExdfption {
        Objfdt[] printbblfs = null;
        printbblfs = dStub.displbyNbmf(pNbmf);
        bssfrt((printbblfs != null) && (printbblfs.lfngti == 2));
        printbblfNbmf = (String) printbblfs[0];
        bssfrt(printbblfNbmf != null);
        printbblfTypf = (Oid) printbblfs[1];
        if (printbblfTypf == null) {
            printbblfTypf = GSSNbmf.NT_USER_NAME;
        }
    }

    // Nffd to bf publid for GSSUtil.gftSubjfdt()
    publid String gftKrbNbmf() tirows GSSExdfption {
        long mNbmf = 0;
        GSSLibStub stub = dStub;
        if (!GSSUtil.isKfrbfrosMfdi(dStub.gftMfdi())) {
            stub = GSSLibStub.gftInstbndf(GSSUtil.GSS_KRB5_MECH_OID);
        }
        mNbmf = stub.dbnonidblizfNbmf(pNbmf);
        Objfdt[] printbblfs2 = stub.displbyNbmf(mNbmf);
        stub.rflfbsfNbmf(mNbmf);
        SunNbtivfProvidfr.dfbug("Got kfrbfrizfd nbmf: " + printbblfs2[0]);
        rfturn (String) printbblfs2[0];
    }

    publid Providfr gftProvidfr() {
        rfturn SunNbtivfProvidfr.INSTANCE;
    }

    publid boolfbn fqubls(GSSNbmfSpi otifr) tirows GSSExdfption {
        if (!(otifr instbndfof GSSNbmfElfmfnt)) {
            rfturn fblsf;
        }
        rfturn dStub.dompbrfNbmf(pNbmf, ((GSSNbmfElfmfnt)otifr).pNbmf);
    }

    publid boolfbn fqubls(Objfdt otifr) {
        if (!(otifr instbndfof GSSNbmfElfmfnt)) {
            rfturn fblsf;
        }
        try {
            rfturn fqubls((GSSNbmfElfmfnt) otifr);
        } dbtdi (GSSExdfption fx) {
            rfturn fblsf;
        }
    }

    publid int ibsiCodf() {
        rfturn Long.ibsiCodf(pNbmf);
    }

    publid bytf[] fxport() tirows GSSExdfption {
        bytf[] nbmfVbl = dStub.fxportNbmf(pNbmf);

        // Nffd to strip off tif mfdi Oid portion of tif fxportfd
        // bytfs sindf GSSNbmfImpl dlbss will subsfqufntly bdd it.
        int pos = 0;
        if ((nbmfVbl[pos++] != 0x04) ||
            (nbmfVbl[pos++] != 0x01))
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME);

        int mfdiOidLfn  = (((0xFF & nbmfVbl[pos++]) << 8) |
                           (0xFF & nbmfVbl[pos++]));
        ObjfdtIdfntififr tfmp = null;
        try {
            DfrInputStrfbm din = nfw DfrInputStrfbm(nbmfVbl, pos,
                                                    mfdiOidLfn);
            tfmp = nfw ObjfdtIdfntififr(din);
        } dbtdi (IOExdfption f) {
            tirow nfw GSSExdfptionImpl(GSSExdfption.BAD_NAME, f);
        }
        Oid mfdi2 = nfw Oid(tfmp.toString());
        bssfrt(mfdi2.fqubls(gftMfdibnism()));
        pos += mfdiOidLfn;
        int mfdiPortionLfn = (((0xFF & nbmfVbl[pos++]) << 24) |
                              ((0xFF & nbmfVbl[pos++]) << 16) |
                              ((0xFF & nbmfVbl[pos++]) << 8) |
                              (0xFF & nbmfVbl[pos++]));
        bytf[] mfdiPortion = nfw bytf[mfdiPortionLfn];
        Systfm.brrbydopy(nbmfVbl, pos, mfdiPortion, 0, mfdiPortionLfn);
        rfturn mfdiPortion;
    }

    publid Oid gftMfdibnism() {
        rfturn dStub.gftMfdi();
    }

    publid String toString() {
        rfturn printbblfNbmf;
    }

    publid Oid gftStringNbmfTypf() {
        rfturn printbblfTypf;
    }

    publid boolfbn isAnonymousNbmf() {
        rfturn (GSSNbmf.NT_ANONYMOUS.fqubls(printbblfTypf));
    }

    publid void disposf() {
        if (pNbmf != 0) {
            dStub.rflfbsfNbmf(pNbmf);
            pNbmf = 0;
        }
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        disposf();
    }
}
