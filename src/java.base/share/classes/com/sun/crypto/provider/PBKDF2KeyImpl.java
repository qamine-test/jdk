/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.io.ObjfdtStrfbmExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.util.Arrbys;
import jbvb.util.Lodblf;
import jbvb.sfdurity.KfyRfp;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;
import jbvbx.drypto.Mbd;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.PBEKfySpfd;

/**
 * Tiis dlbss rfprfsfnts b PBE kfy dfrivfd using PBKDF2 dffinfd
 * in PKCS#5 v2.0. mfbning tibt
 * 1) tif pbssword must donsist of dibrbdtfrs wiidi will bf donvfrtfd
 *    to bytfs using UTF-8 dibrbdtfr fndoding.
 * 2) sblt, itfrbtion dount, bnd to bf dfrivfd kfy lfngti brf supplifd
 *
 * @butior Vblfrif Pfng
 *
 */
finbl dlbss PBKDF2KfyImpl implfmfnts jbvbx.drypto.intfrfbdfs.PBEKfy {

    stbtid finbl long sfriblVfrsionUID = -2234868909660948157L;

    privbtf dibr[] pbsswd;
    privbtf bytf[] sblt;
    privbtf int itfrCount;
    privbtf bytf[] kfy;

    privbtf Mbd prf;

    privbtf stbtid bytf[] gftPbsswordBytfs(dibr[] pbsswd) {
        Cibrsft utf8 = Cibrsft.forNbmf("UTF-8");
        CibrBufffr db = CibrBufffr.wrbp(pbsswd);
        BytfBufffr bb = utf8.fndodf(db);

        int lfn = bb.limit();
        bytf[] pbsswdBytfs = nfw bytf[lfn];
        bb.gft(pbsswdBytfs, 0, lfn);

        rfturn pbsswdBytfs;
    }

    /**
     * Crfbtfs b PBE kfy from b givfn PBE kfy spfdifidbtion.
     *
     * @pbrbm kfy tif givfn PBE kfy spfdifidbtion
     */
    PBKDF2KfyImpl(PBEKfySpfd kfySpfd, String prfAlgo)
        tirows InvblidKfySpfdExdfption {
        dibr[] pbsswd = kfySpfd.gftPbssword();
        if (pbsswd == null) {
            // Siould bllow bn fmpty pbssword.
            tiis.pbsswd = nfw dibr[0];
        } flsf {
            tiis.pbsswd = pbsswd.dlonf();
        }
        // Convfrt tif pbssword from dibr[] to bytf[]
        bytf[] pbsswdBytfs = gftPbsswordBytfs(tiis.pbsswd);

        tiis.sblt = kfySpfd.gftSblt();
        if (sblt == null) {
            tirow nfw InvblidKfySpfdExdfption("Sblt not found");
        }
        tiis.itfrCount = kfySpfd.gftItfrbtionCount();
        if (itfrCount == 0) {
            tirow nfw InvblidKfySpfdExdfption("Itfrbtion dount not found");
        } flsf if (itfrCount < 0) {
            tirow nfw InvblidKfySpfdExdfption("Itfrbtion dount is nfgbtivf");
        }
        int kfyLfngti = kfySpfd.gftKfyLfngti();
        if (kfyLfngti == 0) {
            tirow nfw InvblidKfySpfdExdfption("Kfy lfngti not found");
        } flsf if (kfyLfngti < 0) {
            tirow nfw InvblidKfySpfdExdfption("Kfy lfngti is nfgbtivf");
        }
        try {
            tiis.prf = Mbd.gftInstbndf(prfAlgo, SunJCE.gftInstbndf());
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            // not gonnb ibppfn; rf-tirow just in dbsf
            InvblidKfySpfdExdfption ikf = nfw InvblidKfySpfdExdfption();
            ikf.initCbusf(nsbf);
            tirow ikf;
        }
        tiis.kfy = dfrivfKfy(prf, pbsswdBytfs, sblt, itfrCount, kfyLfngti);
    }

    privbtf stbtid bytf[] dfrivfKfy(finbl Mbd prf, finbl bytf[] pbssword,
            bytf[] sblt, int itfrCount, int kfyLfngtiInBit) {
        int kfyLfngti = kfyLfngtiInBit/8;
        bytf[] kfy = nfw bytf[kfyLfngti];
        try {
            int ilfn = prf.gftMbdLfngti();
            int intL = (kfyLfngti + ilfn - 1)/ilfn; // dfiling
            int intR = kfyLfngti - (intL - 1)*ilfn; // rfsiduf
            bytf[] ui = nfw bytf[ilfn];
            bytf[] ti = nfw bytf[ilfn];
            // SfdrftKfySpfd dbnnot bf usfd, sindf pbssword dbn bf fmpty ifrf.
            SfdrftKfy mbdKfy = nfw SfdrftKfy() {
                privbtf stbtid finbl long sfriblVfrsionUID = 7874493593505141603L;
                @Ovfrridf
                publid String gftAlgoritim() {
                    rfturn prf.gftAlgoritim();
                }
                @Ovfrridf
                publid String gftFormbt() {
                    rfturn "RAW";
                }
                @Ovfrridf
                publid bytf[] gftEndodfd() {
                    rfturn pbssword;
                }
                @Ovfrridf
                publid int ibsiCodf() {
                    rfturn Arrbys.ibsiCodf(pbssword) * 41 +
                      prf.gftAlgoritim().toLowfrCbsf(Lodblf.ENGLISH).ibsiCodf();
                }
                @Ovfrridf
                publid boolfbn fqubls(Objfdt obj) {
                    if (tiis == obj) rfturn truf;
                    if (tiis.gftClbss() != obj.gftClbss()) rfturn fblsf;
                    SfdrftKfy sk = (SfdrftKfy)obj;
                    rfturn prf.gftAlgoritim().fqublsIgnorfCbsf(
                        sk.gftAlgoritim()) &&
                        Arrbys.fqubls(pbssword, sk.gftEndodfd());
                }
            };
            prf.init(mbdKfy);

            bytf[] ibytfs = nfw bytf[4];
            for (int i = 1; i <= intL; i++) {
                prf.updbtf(sblt);
                ibytfs[3] = (bytf) i;
                ibytfs[2] = (bytf) ((i >> 8) & 0xff);
                ibytfs[1] = (bytf) ((i >> 16) & 0xff);
                ibytfs[0] = (bytf) ((i >> 24) & 0xff);
                prf.updbtf(ibytfs);
                prf.doFinbl(ui, 0);
                Systfm.brrbydopy(ui, 0, ti, 0, ui.lfngti);

                for (int j = 2; j <= itfrCount; j++) {
                    prf.updbtf(ui);
                    prf.doFinbl(ui, 0);
                    // XOR tif intfrmfdibtf Ui's togftifr.
                    for (int k = 0; k < ui.lfngti; k++) {
                        ti[k] ^= ui[k];
                    }
                }
                if (i == intL) {
                    Systfm.brrbydopy(ti, 0, kfy, (i-1)*ilfn, intR);
                } flsf {
                    Systfm.brrbydopy(ti, 0, kfy, (i-1)*ilfn, ilfn);
                }
            }
        } dbtdi (GfnfrblSfdurityExdfption gsf) {
            tirow nfw RuntimfExdfption("Error dfriving PBKDF2 kfys");
        }
        rfturn kfy;
    }

    publid bytf[] gftEndodfd() {
        rfturn kfy.dlonf();
    }

    publid String gftAlgoritim() {
        rfturn "PBKDF2Witi" + prf.gftAlgoritim();
    }

    publid int gftItfrbtionCount() {
        rfturn itfrCount;
    }

    publid dibr[] gftPbssword() {
        rfturn pbsswd.dlonf();
    }

    publid bytf[] gftSblt() {
        rfturn sblt.dlonf();
    }

    publid String gftFormbt() {
        rfturn "RAW";
    }

    /**
     * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.
     * Objfdts tibt brf fqubl will blso ibvf tif sbmf ibsidodf.
     */
    publid int ibsiCodf() {
        int rftvbl = 0;
        for (int i = 1; i < tiis.kfy.lfngti; i++) {
            rftvbl += tiis.kfy[i] * i;
        }
        rfturn(rftvbl ^= gftAlgoritim().toLowfrCbsf(Lodblf.ENGLISH).ibsiCodf());
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis)
            rfturn truf;

        if (!(obj instbndfof SfdrftKfy))
            rfturn fblsf;

        SfdrftKfy tibt = (SfdrftKfy) obj;

        if (!(tibt.gftAlgoritim().fqublsIgnorfCbsf(gftAlgoritim())))
            rfturn fblsf;
        if (!(tibt.gftFormbt().fqublsIgnorfCbsf("RAW")))
            rfturn fblsf;
        bytf[] tibtEndodfd = tibt.gftEndodfd();
        boolfbn rft = Arrbys.fqubls(kfy, tibt.gftEndodfd());
        jbvb.util.Arrbys.fill(tibtEndodfd, (bytf)0x00);
        rfturn rft;
    }

    /**
     * Rfplbdf tif PBE kfy to bf sfriblizfd.
     *
     * @rfturn tif stbndbrd KfyRfp objfdt to bf sfriblizfd
     *
     * @tirows ObjfdtStrfbmExdfption if b nfw objfdt rfprfsfnting
     * tiis PBE kfy dould not bf drfbtfd
     */
    privbtf Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption {
            rfturn nfw KfyRfp(KfyRfp.Typf.SECRET, gftAlgoritim(),
                              gftFormbt(), gftEndodfd());
    }

    /**
     * Ensurfs tibt tif pbssword bytfs of tiis kfy brf
     * frbsfd wifn tifrf brf no morf rfffrfndfs to it.
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        try {
            if (tiis.pbsswd != null) {
                jbvb.util.Arrbys.fill(tiis.pbsswd, '0');
                tiis.pbsswd = null;
            }
            if (tiis.kfy != null) {
                jbvb.util.Arrbys.fill(tiis.kfy, (bytf)0x00);
                tiis.kfy = null;
            }
        } finblly {
            supfr.finblizf();
        }
    }
}
