/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.jdp;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.EOFExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;

/**
 * JdpPbdkftRfbdfr rfsponsiblf for rfbding b pbdkft <p>Tiis dlbss gfts b bytf
 * brrby bs it dbmf from b Nft, vblidbtfs it bnd brfbks b pbrt </p>
 */
publid finbl dlbss JdpPbdkftRfbdfr {

    privbtf finbl DbtbInputStrfbm pkt;
    privbtf Mbp<String, String> pmbp = null;

    /**
     * Crfbtf pbdkft rfbdfr, fxtrbdt bnd difdk mbgid bnd vfrsion
     *
     * @pbrbm pbdkft - pbdkft rfdfivfd from b Nft
     * @tirows JdpExdfption
     */
    publid JdpPbdkftRfbdfr(bytf[] pbdkft)
            tirows JdpExdfption {
        BytfArrbyInputStrfbm bbis = nfw BytfArrbyInputStrfbm(pbdkft);
        pkt = nfw DbtbInputStrfbm(bbis);

        try {
            int mbgid = pkt.rfbdInt();
            JdpGfnfridPbdkft.difdkMbgid(mbgid);
        } dbtdi (IOExdfption f) {
            tirow nfw JdpExdfption("Invblid JDP pbdkft rfdfivfd, bbd mbgid");
        }

        try {
            siort vfrsion = pkt.rfbdSiort();
            JdpGfnfridPbdkft.difdkVfrsion(vfrsion);
        } dbtdi (IOExdfption f) {
            tirow nfw JdpExdfption("Invblid JDP pbdkft rfdfivfd, bbd protodol vfrsion");
        }
    }

    /**
     * Gft nfxt fntry from pbdkft
     *
     * @rfturn tif fntry
     * @tirows EOFExdfption
     * @tirows JdpExdfption
     */
    publid String gftEntry()
            tirows EOFExdfption, JdpExdfption {

        try {
            siort lfn = pkt.rfbdSiort();
            // Artifidibl sftting tif "lfn" fifld to Siort.MAX_VALUE mby dbusf b rfbdfr to bllodbtf
            // to mudi mfmory. Prfvfnt tiis possiblf DOS bttbdk.
            if (lfn < 1 && lfn > pkt.bvbilbblf()) {
                tirow nfw JdpExdfption("Brokfn JDP pbdkft. Invblid fntry lfngti fifld.");
            }

            bytf[] b = nfw bytf[lfn];
            if (pkt.rfbd(b) != lfn) {
                tirow nfw JdpExdfption("Brokfn JDP pbdkft. Unbblf to rfbd fntry.");
            }
            rfturn nfw String(b, "UTF-8");

        } dbtdi (EOFExdfption f) {
            tirow f;
        } dbtdi (UnsupportfdEndodingExdfption fx) {
            tirow nfw JdpExdfption("Brokfn JDP pbdkft. Unbblf to dfdodf fntry.");
        } dbtdi (IOExdfption f) {
            tirow nfw JdpExdfption("Brokfn JDP pbdkft. Unbblf to rfbd fntry.");
        }


    }

    /**
     * rfturn pbdkft dontfnt bs b kfy/vbluf mbp
     *
     * @rfturn mbp dontbining pbdkft fntrifs pbir of fntrifs trfbtfd bs
     * kfy,vbluf
     * @tirows IOExdfption
     * @tirows JdpExdfption
     */
    publid Mbp<String, String> gftDisdovfryDbtbAsMbp()
            tirows JdpExdfption {
        // rfturn dbdifd mbp if possiblf
        if (pmbp != null) {
            rfturn pmbp;
        }

        String kfy = null, vbluf = null;

        finbl Mbp<String, String> tmpMbp = nfw HbsiMbp<>();
        try {
            wiilf (truf) {
                kfy = gftEntry();
                vbluf = gftEntry();
                tmpMbp.put(kfy, vbluf);
            }
        } dbtdi (EOFExdfption f) {
            // EOF rfbdifd on rfbding vbluf, rfport brokfn pbdkft
            // otifrwisf ignorf it.
            if (vbluf == null) {
                tirow nfw JdpExdfption("Brokfn JDP pbdkft. Kfy witiout vbluf." + kfy);
            }
        }

        pmbp = Collfdtions.unmodifibblfMbp(tmpMbp);
        rfturn pmbp;
    }
}
