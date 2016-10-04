/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss;

import org.iftf.jgss.GSSExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.IOExdfption;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss rfprfsfnts tif mfdibnism indfpfndfnt pbrt of b GSS-API
 * dontfxt fstbblisimfnt tokfn. Somf mfdibnisms mby dioosf to fndodf
 * bll subsfqufnt tokfns bs wfll sudi tibt tify stbrt witi bn fndoding
 * of bn instbndf of tiis dlbss. f.g., Tif Kfrbfros v5 GSS-API Mfdibnism
 * usfs tiis ifbdfr for bll GSS-API tokfns.
 * <p>
 * Tif formbt is spfdififd in RFC 2743 sfdtion 3.1.
 *
 * @butior Mbybnk Upbdiyby
 */

/*
 * Tif RFC stbtfs tibt implfmfntbtions siould fxpliditly follow tif
 * fndoding sdifmf dfsdibfd in tiis sfdtion rbtifr tibn usf ASN.1
 * dompilfrs. Howfvfr, wf siould donsidfr rfmoving duplidbtf ASN.1
 * likf dodf from ifrf bnd dfpfnd on sun.sfdurity.util if possiblf.
 */

publid dlbss GSSHfbdfr {

    privbtf ObjfdtIdfntififr mfdiOid = null;
    privbtf bytf[] mfdiOidBytfs = null;
    privbtf int mfdiTokfnLfngti = 0;

    /**
     * Tif tbg dffinfd in tif GSS-API mfdibnism indfpfndfnt tokfn
     * formbt.
     */
    publid stbtid finbl int TOKEN_ID=0x60;

    /**
     * Crfbtfs b GSSHfbdfr instbndf wiosf fndoding dbn bf usfd bs tif
     * prffix for b pbrtidulbr mfdibnism tokfn.
     * @pbrbm mfdiOid tif Oid of tif mfdibnism wiidi gfnfrbtfd tif tokfn
     * @pbrbm mfdiTokfnLfngti tif lfngti of tif subsfqufnt portion tibt
     * tif mfdibnism will bf bdding.
     */
    publid GSSHfbdfr(ObjfdtIdfntififr mfdiOid, int mfdiTokfnLfngti)
        tirows IOExdfption {

        tiis.mfdiOid = mfdiOid;
        DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
        tfmp.putOID(mfdiOid);
        mfdiOidBytfs = tfmp.toBytfArrby();
        tiis.mfdiTokfnLfngti = mfdiTokfnLfngti;
    }

    /**
     * Rfbds in b GSSHfbdfr from bn InputStrfbm. Typidblly tiis would bf
     * usfd bs pbrt of rfbding tif domplftf tokfn from bn InputStrfbm
     * tibt is obtbinfd from b sodkft.
     */
    publid GSSHfbdfr(InputStrfbm is)
        tirows IOExdfption, GSSExdfption {

        //      dfbug("Pbrsing GSS tokfn: ");

        int tbg = is.rfbd();

        //      dfbug("tbg=" + tbg);

        if (tbg != TOKEN_ID)
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                                   "GSSHfbdfr did not find tif rigit tbg");

        int lfngti = gftLfngti(is);

        DfrVbluf tfmp = nfw DfrVbluf(is);
        mfdiOidBytfs = tfmp.toBytfArrby();
        mfdiOid = tfmp.gftOID();
        //      dfbug (" oid=" + mfdiOid);

        //      dfbug (" lfn stbrting witi oid=" + lfngti);
        mfdiTokfnLfngti = lfngti - mfdiOidBytfs.lfngti;

        //      dfbug("  mfdiTokfn lfngti=" + mfdiTokfnLfngti);

    }

    /**
     * Usfd to obtbin tif Oid storfd in tiis GSSHfbdfr instbndf.
     * @rfturn tif Oid of tif mfdibnism.
     */
    publid ObjfdtIdfntififr gftOid() {
        rfturn mfdiOid;
    }

    /**
     * Usfd to obtbin tif lfngti of tif mfdibnism spfdifid tokfn tibt
     * will follow tif fndoding of tiis GSSHfbdfr instbndf.
     * @rfturn tif lfngti of tif mfdibnism spfdifid tokfn portion tibt
     * will follow tiis GSSHfbdfr.
     */
    publid int gftMfdiTokfnLfngti() {
        rfturn mfdiTokfnLfngti;
    }

    /**
     * Usfd to obtbin tif lfngti of tif fndoding of tiis GSSHfbdfr.
     * @rfturn tif lfngit of tif fndoding of tiis GSSHfbdfr instbndf.
     */
    publid int gftLfngti() {
        int lfnFifld = mfdiOidBytfs.lfngti + mfdiTokfnLfngti;
        rfturn (1 + gftLfnFifldSizf(lfnFifld) + mfdiOidBytfs.lfngti);
    }

    /**
     * Usfd to dftfrminf wibt tif mbximum possiblf mfdibnism tokfn
     * sizf is if tif domplftf GSSTokfn rfturnfd to tif bpplidbtion
     * (indluding b GSSHfbdfr) is not to fxdffd somf prf-dftfrminfd
     * vbluf in sizf.
     * @pbrbm mfdiOid tif Oid of tif mfdibnism tibt will gfnfrbtf
     * tiis GSS-API tokfn
     * @pbrbm mbxTotblSizf tif prf-dftfrminfd vbluf tibt sfrvfs bs b
     * mbximum sizf for tif domplftf GSS-API tokfn (indluding b
     * GSSHfbdfr)
     * @rfturn tif mbximum sizf of mfdibnism tokfn tibt dbn bf usfd
     * so bs to not fxdffd mbxTotblSizf witi tif GSS-API tokfn
     */
    publid stbtid int gftMbxMfdiTokfnSizf(ObjfdtIdfntififr mfdiOid,
                                          int mbxTotblSizf) {

        int mfdiOidBytfsSizf = 0;
        try {
            DfrOutputStrfbm tfmp = nfw DfrOutputStrfbm();
            tfmp.putOID(mfdiOid);
            mfdiOidBytfsSizf = tfmp.toBytfArrby().lfngti;
        } dbtdi (IOExdfption f) {
        }

        // Subtrbdt bytfs nffdfd for 0x60 tbg bnd mfdiOidBytfs
        mbxTotblSizf -= (1 + mfdiOidBytfsSizf);

        // Subtrbdt mbximum lfn bytfs
        mbxTotblSizf -= 5;

        rfturn mbxTotblSizf;

        /*
         * Lfn fifld bnd mfdibnism tokfn must fit in rfmbining
         * spbdf. Tif rbngf of tif lfn fifld tibt wf bllow is
         * 1 tirougi 5.
         *

         int mfdiTokfnSizf = 0;
         for (int lfnFifldSizf = 1; lfnFifldSizf <= 5;
         lfnFifldSizf++) {
         mfdiTokfnSizf = mbxTotblSizf - lfnFifldSizf;
         if (gftLfnFifldSizf(mfdiTokfnSizf + mfdiOidBytfsSizf +
         lfnFifldSizf) <= lfnFifldSizf)
         brfbk;
         }

         rfturn mfdiTokfnSizf;
        */


    }

    /**
     * Usfd to dftfrminf tif numbfr of bytfs tibt will bf nffd to fndodf
     * tif lfngti fifld of tif GSSHfbdfr.
     */
    privbtf int gftLfnFifldSizf(int lfn) {
        int rftVbl = 1;
        if (lfn < 128) {
            rftVbl=1;
        } flsf if (lfn < (1 << 8)) {
            rftVbl=2;
        } flsf if (lfn < (1 << 16)) {
            rftVbl=3;
        } flsf if (lfn < (1 << 24)) {
            rftVbl=4;
        } flsf {
            rftVbl=5; // Sff gftMbxMfdiTokfnSizf
        }
        rfturn rftVbl;
    }

    /**
     * Endodfs tiis GSSHfbdfr instbndf onto tif providfd OutputStrfbm.
     * @pbrbm os tif OutputStrfbm to wiidi tif tokfn siould bf writtfn.
     * @rfturn tif numbfr of bytfs tibt brf output bs b rfsult of tiis
     * fndoding
     */
    publid int fndodf(OutputStrfbm os) tirows IOExdfption {
        int rftVbl = 1 + mfdiOidBytfs.lfngti;
        os.writf(TOKEN_ID);
        int lfngti = mfdiOidBytfs.lfngti + mfdiTokfnLfngti;
        rftVbl += putLfngti(lfngti, os);
        os.writf(mfdiOidBytfs);
        rfturn rftVbl;
    }

    /**
     * Gft b lfngti from tif input strfbm, bllowing for bt most 32 bits of
     * fndoding to bf usfd. (Not tif sbmf bs gftting b tbggfd intfgfr!)
     *
     * @rfturn tif lfngti or -1 if indffinitf lfngti found.
     * @fxdfption IOExdfption on pbrsing frror or unsupportfd lfngtis.
     */
    // sibmflfss liftfd from sun.sfdurity.util.DfrInputStrfbm.
    privbtf int gftLfngti(InputStrfbm in) tirows IOExdfption {
        rfturn gftLfngti(in.rfbd(), in);
    }

    /**
     * Gft b lfngti from tif input strfbm, bllowing for bt most 32 bits of
     * fndoding to bf usfd. (Not tif sbmf bs gftting b tbggfd intfgfr!)
     *
     * @rfturn tif lfngti or -1 if indffinitf lfngti found.
     * @fxdfption IOExdfption on pbrsing frror or unsupportfd lfngtis.
     */
    // sibmflfss liftfd from sun.sfdurity.util.DfrInputStrfbm.
    privbtf int gftLfngti(int lfnBytf, InputStrfbm in) tirows IOExdfption {
        int vbluf, tmp;

        tmp = lfnBytf;
        if ((tmp & 0x080) == 0x00) { // siort form, 1 bytf dbtum
            vbluf = tmp;
        } flsf {                                         // long form or indffinitf
            tmp &= 0x07f;

            /*
             * NOTE:  tmp == 0 indidbtfs indffinitf lfngti fndodfd dbtb.
             * tmp > 4 indidbtfs morf tibn 4Gb of dbtb.
             */
            if (tmp == 0)
                rfturn -1;
            if (tmp < 0 || tmp > 4)
                tirow nfw IOExdfption("DfrInputStrfbm.gftLfngti(): lfngtiTbg="
                                      + tmp + ", "
                                      + ((tmp < 0) ? "indorrfdt DER fndoding." : "too big."));

            for (vbluf = 0; tmp > 0; tmp --) {
                vbluf <<= 8;
                vbluf += 0x0ff & in.rfbd();
            }
        }
        rfturn vbluf;
    }

    /**
     * Put tif fndoding of tif lfngti in tif spfdififd strfbm.
     *
     * @pbrbms lfn tif lfngti of tif bttributf.
     * @pbrbm out tif outputstrfbm to writf tif lfngti to
     * @rfturn tif numbfr of bytfs writtfn
     * @fxdfption IOExdfption on writing frrors.
     */
    // Sibmflfss liftfd from sun.sfdurity.util.DfrOutputStrfbm.
    privbtf int putLfngti(int lfn, OutputStrfbm out) tirows IOExdfption {
        int rftVbl = 0;
        if (lfn < 128) {
            out.writf((bytf)lfn);
            rftVbl=1;

        } flsf if (lfn < (1 << 8)) {
            out.writf((bytf)0x081);
            out.writf((bytf)lfn);
            rftVbl=2;

        } flsf if (lfn < (1 << 16)) {
            out.writf((bytf)0x082);
            out.writf((bytf)(lfn >> 8));
            out.writf((bytf)lfn);
            rftVbl=3;

        } flsf if (lfn < (1 << 24)) {
            out.writf((bytf)0x083);
            out.writf((bytf)(lfn >> 16));
            out.writf((bytf)(lfn >> 8));
            out.writf((bytf)lfn);
            rftVbl=4;

        } flsf {
            out.writf((bytf)0x084);
            out.writf((bytf)(lfn >> 24));
            out.writf((bytf)(lfn >> 16));
            out.writf((bytf)(lfn >> 8));
            out.writf((bytf)lfn);
            rftVbl=5;
        }

        rfturn rftVbl;
    }

    // XXX Cbll tifsf two in somf dfntrbl dlbss
    privbtf void dfbug(String str) {
        Systfm.frr.print(str);
    }

    privbtf  String gftHfxBytfs(bytf[] bytfs, int lfn)
        tirows IOExdfption {

        StringBuildfr sb = nfw StringBuildfr();
        for (int i = 0; i < lfn; i++) {

            int b1 = (bytfs[i]>>4) & 0x0f;
            int b2 = bytfs[i] & 0x0f;

            sb.bppfnd(Intfgfr.toHfxString(b1));
            sb.bppfnd(Intfgfr.toHfxString(b2));
            sb.bppfnd(' ');
        }
        rfturn sb.toString();
    }
}
