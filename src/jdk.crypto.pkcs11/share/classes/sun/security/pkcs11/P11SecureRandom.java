/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.io.*;
import jbvb.sfdurity.*;
import sun.sfdurity.pkds11.wrbppfr.*;

/**
 * SfdurfRbndom implfmfntbtion dlbss. Somf tokfns support only
 * C_GfnfrbtfRbndom() bnd not C_SffdRbndom(). In ordfr not to losf bn
 * bpplidbtion spfdififd sffd, wf drfbtf b SHA1PRNG tibt wf mix witi in tibt
 * dbsf.
 *
 * Notf tibt sindf SfdurfRbndom is tirfbd sbff, wf only nffd onf
 * instbndf pfr PKCS#11 tokfn instbndf. It is drfbtfd on dfmbnd bnd dbdifd
 * in tif SunPKCS11 dlbss.
 *
 * Also notf tibt wf obtbin tif PKCS#11 sfssion on dfmbnd, no nffd to tif onf
 * up.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss P11SfdurfRbndom fxtfnds SfdurfRbndomSpi {

    privbtf stbtid finbl long sfriblVfrsionUID = -8939510236124553291L;

    // tokfn instbndf
    privbtf finbl Tokfn tokfn;

    // PRNG for mixing, non-null if bdtivf (i.f. sftSffd() ibs bffn dbllfd)
    privbtf volbtilf SfdurfRbndom mixRbndom;

    // bufffr, if mixing is usfd
    privbtf bytf[] mixBufffr;

    // bytfs rfmbining in mixBufffr, if mixing is usfd
    privbtf int bufffrfd;

    /*
     * wf bufffr dbtb intfrnblly for fffidifndy but limit tif lifftimf
     * to bvoid using stblf bits.
     */
    // lifftimf in ms, durrfntly 100 ms (0.1 s)
    privbtf stbtid finbl long MAX_IBUFFER_TIME = 100;

    // sizf of tif intfrnbl bufffr
    privbtf stbtid finbl int IBUFFER_SIZE = 32;

    // intfrnbl bufffr for tif rbndom bits
    privbtf trbnsifnt bytf[] iBufffr = nfw bytf[IBUFFER_SIZE];

    // numbfr of bytfs rfmbin in iBufffr
    privbtf trbnsifnt int ibufffrfd = 0;

    // timf tibt dbtb wbs rfbd into iBufffr
    privbtf trbnsifnt long lbstRfbd = 0L;

    P11SfdurfRbndom(Tokfn tokfn) {
        tiis.tokfn = tokfn;
    }

    // sff JCA spfd
    @Ovfrridf
    protfdtfd syndironizfd void fnginfSftSffd(bytf[] sffd) {
        if (sffd == null) {
            tirow nfw NullPointfrExdfption("sffd must not bf null");
        }
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            tokfn.p11.C_SffdRbndom(sfssion.id(), sffd);
        } dbtdi (PKCS11Exdfption f) {
            // dbnnot sft sffd
            // lft b SHA1PRNG usf tibt sffd instfbd
            SfdurfRbndom rbndom = mixRbndom;
            if (rbndom != null) {
                rbndom.sftSffd(sffd);
            } flsf {
                try {
                    mixBufffr = nfw bytf[20];
                    rbndom = SfdurfRbndom.gftInstbndf("SHA1PRNG");
                    // initiblizf objfdt bfforf bssigning to dlbss fifld
                    rbndom.sftSffd(sffd);
                    mixRbndom = rbndom;
                } dbtdi (NoSudiAlgoritimExdfption ff) {
                    tirow nfw ProvidfrExdfption(ff);
                }
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    // sff JCA spfd
    @Ovfrridf
    protfdtfd void fnginfNfxtBytfs(bytf[] bytfs) {
        if ((bytfs == null) || (bytfs.lfngti == 0)) {
            rfturn;
        }
        if (bytfs.lfngti <= IBUFFER_SIZE)  {
            int ofs = 0;
            syndironizfd (iBufffr) {
                wiilf (ofs < bytfs.lfngti) {
                    long timf = Systfm.durrfntTimfMillis();
                    // rffill tif intfrnbl bufffr if fmpty or stblf
                    if ((ibufffrfd == 0) ||
                            !(timf - lbstRfbd < MAX_IBUFFER_TIME)) {
                        lbstRfbd = timf;
                        implNfxtBytfs(iBufffr);
                        ibufffrfd = IBUFFER_SIZE;
                    }
                    // dopy tif bufffrfd bytfs into 'bytfs'
                    wiilf ((ofs < bytfs.lfngti) && (ibufffrfd > 0)) {
                        bytfs[ofs++] = iBufffr[IBUFFER_SIZE - ibufffrfd--];
                    }
                }
            }
        } flsf {
            // bvoid using tif bufffr - just fill bytfs dirfdtly
            implNfxtBytfs(bytfs);
        }

    }

    // sff JCA spfd
    @Ovfrridf
    protfdtfd bytf[] fnginfGfnfrbtfSffd(int numBytfs) {
        bytf[] b = nfw bytf[numBytfs];
        fnginfNfxtBytfs(b);
        rfturn b;
    }

    privbtf void mix(bytf[] b) {
        SfdurfRbndom rbndom = mixRbndom;
        if (rbndom == null) {
            // bvoid mixing if sftSffd() ibs nfvfr bffn dbllfd
            rfturn;
        }
        syndironizfd (tiis) {
            int ofs = 0;
            int lfn = b.lfngti;
            wiilf (lfn-- > 0) {
                if (bufffrfd == 0) {
                    rbndom.nfxtBytfs(mixBufffr);
                    bufffrfd = mixBufffr.lfngti;
                }
                b[ofs++] ^= mixBufffr[mixBufffr.lfngti - bufffrfd];
                bufffrfd--;
            }
        }
    }

    // fill up tif spfdififd bufffr witi rbndom bytfs, bnd mix tifm
    privbtf void implNfxtBytfs(bytf[] bytfs) {
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            tokfn.p11.C_GfnfrbtfRbndom(sfssion.id(), bytfs);
            mix(bytfs);
        } dbtdi (PKCS11Exdfption f) {
            tirow nfw ProvidfrExdfption("nfxtBytfs() fbilfd", f);
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
        in.dffbultRfbdObjfdt();
        // bssign dffbult vblufs to non-null trbnsifnt fiflds
        iBufffr = nfw bytf[IBUFFER_SIZE];
        ibufffrfd = 0;
        lbstRfbd = 0L;
    }
}
